package io.netty.handler.codec.http2;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.util.ReferenceCountUtil;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

public class StreamBufferingEncoder extends DecoratingHttp2ConnectionEncoder {
  public static final class Http2ChannelClosedException extends Http2Exception {
    private static final long serialVersionUID = 4768543442094476971L;
    
    public Http2ChannelClosedException() {
      super(Http2Error.REFUSED_STREAM, "Connection closed");
    }
  }
  
  private static final class GoAwayDetail {
    private final int lastStreamId;
    
    private final long errorCode;
    
    private final byte[] debugData;
    
    GoAwayDetail(int lastStreamId, long errorCode, byte[] debugData) {
      this.lastStreamId = lastStreamId;
      this.errorCode = errorCode;
      this.debugData = (byte[])debugData.clone();
    }
  }
  
  public static final class Http2GoAwayException extends Http2Exception {
    private static final long serialVersionUID = 1326785622777291198L;
    
    private final StreamBufferingEncoder.GoAwayDetail goAwayDetail;
    
    public Http2GoAwayException(int lastStreamId, long errorCode, byte[] debugData) {
      this(new StreamBufferingEncoder.GoAwayDetail(lastStreamId, errorCode, debugData));
    }
    
    Http2GoAwayException(StreamBufferingEncoder.GoAwayDetail goAwayDetail) {
      super(Http2Error.STREAM_CLOSED);
      this.goAwayDetail = goAwayDetail;
    }
    
    public int lastStreamId() {
      return this.goAwayDetail.lastStreamId;
    }
    
    public long errorCode() {
      return this.goAwayDetail.errorCode;
    }
    
    public byte[] debugData() {
      return (byte[])this.goAwayDetail.debugData.clone();
    }
  }
  
  private final TreeMap<Integer, PendingStream> pendingStreams = new TreeMap<Integer, PendingStream>();
  
  private int maxConcurrentStreams;
  
  private boolean closed;
  
  private GoAwayDetail goAwayDetail;
  
  public StreamBufferingEncoder(Http2ConnectionEncoder delegate) {
    this(delegate, 100);
  }
  
  public StreamBufferingEncoder(Http2ConnectionEncoder delegate, int initialMaxConcurrentStreams) {
    super(delegate);
    this.maxConcurrentStreams = initialMaxConcurrentStreams;
    connection().addListener(new Http2ConnectionAdapter() {
          public void onGoAwayReceived(int lastStreamId, long errorCode, ByteBuf debugData) {
            StreamBufferingEncoder.this.goAwayDetail = new StreamBufferingEncoder.GoAwayDetail(lastStreamId, errorCode, 
                
                ByteBufUtil.getBytes(debugData, debugData.readerIndex(), debugData.readableBytes(), false));
            StreamBufferingEncoder.this.cancelGoAwayStreams(StreamBufferingEncoder.this.goAwayDetail);
          }
          
          public void onStreamClosed(Http2Stream stream) {
            StreamBufferingEncoder.this.tryCreatePendingStreams();
          }
        });
  }
  
  public int numBufferedStreams() {
    return this.pendingStreams.size();
  }
  
  public ChannelFuture writeHeaders(ChannelHandlerContext ctx, int streamId, Http2Headers headers, int padding, boolean endStream, ChannelPromise promise) {
    return writeHeaders(ctx, streamId, headers, 0, (short)16, false, padding, endStream, promise);
  }
  
  public ChannelFuture writeHeaders(ChannelHandlerContext ctx, int streamId, Http2Headers headers, int streamDependency, short weight, boolean exclusive, int padding, boolean endOfStream, ChannelPromise promise) {
    if (this.closed)
      return (ChannelFuture)promise.setFailure(new Http2ChannelClosedException()); 
    if (isExistingStream(streamId) || canCreateStream())
      return super.writeHeaders(ctx, streamId, headers, streamDependency, weight, exclusive, padding, endOfStream, promise); 
    if (this.goAwayDetail != null)
      return (ChannelFuture)promise.setFailure(new Http2GoAwayException(this.goAwayDetail)); 
    PendingStream pendingStream = this.pendingStreams.get(Integer.valueOf(streamId));
    if (pendingStream == null) {
      pendingStream = new PendingStream(ctx, streamId);
      this.pendingStreams.put(Integer.valueOf(streamId), pendingStream);
    } 
    pendingStream.frames.add(new HeadersFrame(headers, streamDependency, weight, exclusive, padding, endOfStream, promise));
    return (ChannelFuture)promise;
  }
  
  public ChannelFuture writeRstStream(ChannelHandlerContext ctx, int streamId, long errorCode, ChannelPromise promise) {
    if (isExistingStream(streamId))
      return super.writeRstStream(ctx, streamId, errorCode, promise); 
    PendingStream stream = this.pendingStreams.remove(Integer.valueOf(streamId));
    if (stream != null) {
      stream.close(null);
      promise.setSuccess();
    } else {
      promise.setFailure(Http2Exception.connectionError(Http2Error.PROTOCOL_ERROR, "Stream does not exist %d", new Object[] { Integer.valueOf(streamId) }));
    } 
    return (ChannelFuture)promise;
  }
  
  public ChannelFuture writeData(ChannelHandlerContext ctx, int streamId, ByteBuf data, int padding, boolean endOfStream, ChannelPromise promise) {
    if (isExistingStream(streamId))
      return super.writeData(ctx, streamId, data, padding, endOfStream, promise); 
    PendingStream pendingStream = this.pendingStreams.get(Integer.valueOf(streamId));
    if (pendingStream != null) {
      pendingStream.frames.add(new DataFrame(data, padding, endOfStream, promise));
    } else {
      ReferenceCountUtil.safeRelease(data);
      promise.setFailure(Http2Exception.connectionError(Http2Error.PROTOCOL_ERROR, "Stream does not exist %d", new Object[] { Integer.valueOf(streamId) }));
    } 
    return (ChannelFuture)promise;
  }
  
  public void remoteSettings(Http2Settings settings) throws Http2Exception {
    super.remoteSettings(settings);
    this.maxConcurrentStreams = connection().local().maxActiveStreams();
    tryCreatePendingStreams();
  }
  
  public void close() {
    try {
      if (!this.closed) {
        this.closed = true;
        Http2ChannelClosedException e = new Http2ChannelClosedException();
        while (!this.pendingStreams.isEmpty()) {
          PendingStream stream = (PendingStream)this.pendingStreams.pollFirstEntry().getValue();
          stream.close(e);
        } 
      } 
    } finally {
      super.close();
    } 
  }
  
  private void tryCreatePendingStreams() {
    while (!this.pendingStreams.isEmpty() && canCreateStream()) {
      Map.Entry<Integer, PendingStream> entry = this.pendingStreams.pollFirstEntry();
      PendingStream pendingStream = entry.getValue();
      try {
        pendingStream.sendFrames();
      } catch (Throwable t) {
        pendingStream.close(t);
      } 
    } 
  }
  
  private void cancelGoAwayStreams(GoAwayDetail goAwayDetail) {
    Iterator<PendingStream> iter = this.pendingStreams.values().iterator();
    Exception e = new Http2GoAwayException(goAwayDetail);
    while (iter.hasNext()) {
      PendingStream stream = iter.next();
      if (stream.streamId > goAwayDetail.lastStreamId) {
        iter.remove();
        stream.close(e);
      } 
    } 
  }
  
  private boolean canCreateStream() {
    return (connection().local().numActiveStreams() < this.maxConcurrentStreams);
  }
  
  private boolean isExistingStream(int streamId) {
    return (streamId <= connection().local().lastStreamCreated());
  }
  
  private static final class PendingStream {
    final ChannelHandlerContext ctx;
    
    final int streamId;
    
    final Queue<StreamBufferingEncoder.Frame> frames = new ArrayDeque<StreamBufferingEncoder.Frame>(2);
    
    PendingStream(ChannelHandlerContext ctx, int streamId) {
      this.ctx = ctx;
      this.streamId = streamId;
    }
    
    void sendFrames() {
      for (StreamBufferingEncoder.Frame frame : this.frames)
        frame.send(this.ctx, this.streamId); 
    }
    
    void close(Throwable t) {
      for (StreamBufferingEncoder.Frame frame : this.frames)
        frame.release(t); 
    }
  }
  
  private static abstract class Frame {
    final ChannelPromise promise;
    
    Frame(ChannelPromise promise) {
      this.promise = promise;
    }
    
    void release(Throwable t) {
      if (t == null) {
        this.promise.setSuccess();
      } else {
        this.promise.setFailure(t);
      } 
    }
    
    abstract void send(ChannelHandlerContext param1ChannelHandlerContext, int param1Int);
  }
  
  private final class HeadersFrame extends Frame {
    final Http2Headers headers;
    
    final int streamDependency;
    
    final short weight;
    
    final boolean exclusive;
    
    final int padding;
    
    final boolean endOfStream;
    
    HeadersFrame(Http2Headers headers, int streamDependency, short weight, boolean exclusive, int padding, boolean endOfStream, ChannelPromise promise) {
      super(promise);
      this.headers = headers;
      this.streamDependency = streamDependency;
      this.weight = weight;
      this.exclusive = exclusive;
      this.padding = padding;
      this.endOfStream = endOfStream;
    }
    
    void send(ChannelHandlerContext ctx, int streamId) {
      StreamBufferingEncoder.this.writeHeaders(ctx, streamId, this.headers, this.streamDependency, this.weight, this.exclusive, this.padding, this.endOfStream, this.promise);
    }
  }
  
  private final class DataFrame extends Frame {
    final ByteBuf data;
    
    final int padding;
    
    final boolean endOfStream;
    
    DataFrame(ByteBuf data, int padding, boolean endOfStream, ChannelPromise promise) {
      super(promise);
      this.data = data;
      this.padding = padding;
      this.endOfStream = endOfStream;
    }
    
    void release(Throwable t) {
      super.release(t);
      ReferenceCountUtil.safeRelease(this.data);
    }
    
    void send(ChannelHandlerContext ctx, int streamId) {
      StreamBufferingEncoder.this.writeData(ctx, streamId, this.data, this.padding, this.endOfStream, this.promise);
    }
  }
}
