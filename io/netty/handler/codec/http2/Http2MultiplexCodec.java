package io.netty.handler.codec.http2;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.channel.socket.ChannelInputShutdownReadComplete;
import io.netty.channel.socket.ChannelOutputShutdownEvent;
import io.netty.handler.ssl.SslCloseCompletionEvent;
import io.netty.util.concurrent.GenericFutureListener;
import java.util.ArrayDeque;
import java.util.Queue;

@Deprecated
public class Http2MultiplexCodec extends Http2FrameCodec {
  private final ChannelHandler inboundStreamHandler;
  
  private final ChannelHandler upgradeStreamHandler;
  
  private final Queue<AbstractHttp2StreamChannel> readCompletePendingQueue = new MaxCapacityQueue<AbstractHttp2StreamChannel>(new ArrayDeque<AbstractHttp2StreamChannel>(8), 100);
  
  private boolean parentReadInProgress;
  
  private int idCount;
  
  volatile ChannelHandlerContext ctx;
  
  Http2MultiplexCodec(Http2ConnectionEncoder encoder, Http2ConnectionDecoder decoder, Http2Settings initialSettings, ChannelHandler inboundStreamHandler, ChannelHandler upgradeStreamHandler, boolean decoupleCloseAndGoAway, boolean flushPreface) {
    super(encoder, decoder, initialSettings, decoupleCloseAndGoAway, flushPreface);
    this.inboundStreamHandler = inboundStreamHandler;
    this.upgradeStreamHandler = upgradeStreamHandler;
  }
  
  public void onHttpClientUpgrade() throws Http2Exception {
    if (this.upgradeStreamHandler == null)
      throw Http2Exception.connectionError(Http2Error.INTERNAL_ERROR, "Client is misconfigured for upgrade requests", new Object[0]); 
    super.onHttpClientUpgrade();
  }
  
  public final void handlerAdded0(ChannelHandlerContext ctx) throws Exception {
    if (ctx.executor() != ctx.channel().eventLoop())
      throw new IllegalStateException("EventExecutor must be EventLoop of Channel"); 
    this.ctx = ctx;
  }
  
  public final void handlerRemoved0(ChannelHandlerContext ctx) throws Exception {
    super.handlerRemoved0(ctx);
    this.readCompletePendingQueue.clear();
  }
  
  final void onHttp2Frame(ChannelHandlerContext ctx, Http2Frame frame) {
    if (frame instanceof Http2StreamFrame) {
      Http2StreamFrame streamFrame = (Http2StreamFrame)frame;
      AbstractHttp2StreamChannel channel = (AbstractHttp2StreamChannel)((Http2FrameCodec.DefaultHttp2FrameStream)streamFrame.stream()).attachment;
      channel.fireChildRead(streamFrame);
      return;
    } 
    if (frame instanceof Http2GoAwayFrame)
      onHttp2GoAwayFrame(ctx, (Http2GoAwayFrame)frame); 
    ctx.fireChannelRead(frame);
  }
  
  final void onHttp2StreamStateChanged(ChannelHandlerContext ctx, Http2FrameCodec.DefaultHttp2FrameStream stream) {
    Http2MultiplexCodecStreamChannel streamChannel;
    ChannelFuture future;
    AbstractHttp2StreamChannel channel;
    switch (stream.state()) {
      case HALF_CLOSED_LOCAL:
        if (stream.id() != 1)
          break; 
      case HALF_CLOSED_REMOTE:
      case OPEN:
        if (stream.attachment != null)
          break; 
        if (stream.id() == 1 && !connection().isServer()) {
          assert this.upgradeStreamHandler != null;
          streamChannel = new Http2MultiplexCodecStreamChannel(stream, this.upgradeStreamHandler);
          streamChannel.closeOutbound();
        } else {
          streamChannel = new Http2MultiplexCodecStreamChannel(stream, this.inboundStreamHandler);
        } 
        future = ctx.channel().eventLoop().register(streamChannel);
        if (future.isDone()) {
          Http2MultiplexHandler.registerDone(future);
          break;
        } 
        future.addListener((GenericFutureListener)Http2MultiplexHandler.CHILD_CHANNEL_REGISTRATION_LISTENER);
        break;
      case CLOSED:
        channel = (AbstractHttp2StreamChannel)stream.attachment;
        if (channel != null)
          channel.streamClosed(); 
        break;
    } 
  }
  
  final Http2StreamChannel newOutboundStream() {
    return new Http2MultiplexCodecStreamChannel(newStream(), null);
  }
  
  final void onHttp2FrameStreamException(ChannelHandlerContext ctx, Http2FrameStreamException cause) {
    Http2FrameStream stream = cause.stream();
    AbstractHttp2StreamChannel channel = (AbstractHttp2StreamChannel)((Http2FrameCodec.DefaultHttp2FrameStream)stream).attachment;
    try {
      channel.pipeline().fireExceptionCaught(cause.getCause());
    } finally {
      channel.closeWithError(cause.error());
    } 
  }
  
  private void onHttp2GoAwayFrame(ChannelHandlerContext ctx, final Http2GoAwayFrame goAwayFrame) {
    if (goAwayFrame.lastStreamId() == Integer.MAX_VALUE)
      return; 
    try {
      forEachActiveStream(new Http2FrameStreamVisitor() {
            public boolean visit(Http2FrameStream stream) {
              int streamId = stream.id();
              AbstractHttp2StreamChannel channel = (AbstractHttp2StreamChannel)((Http2FrameCodec.DefaultHttp2FrameStream)stream).attachment;
              if (streamId > goAwayFrame.lastStreamId() && Http2MultiplexCodec.this.connection().local().isValidStreamId(streamId))
                channel.pipeline().fireUserEventTriggered(goAwayFrame.retainedDuplicate()); 
              return true;
            }
          });
    } catch (Http2Exception e) {
      ctx.fireExceptionCaught(e);
      ctx.close();
    } 
  }
  
  public final void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
    processPendingReadCompleteQueue();
    channelReadComplete0(ctx);
  }
  
  private void processPendingReadCompleteQueue() {
    this.parentReadInProgress = true;
    try {
      while (true) {
        AbstractHttp2StreamChannel childChannel = this.readCompletePendingQueue.poll();
        if (childChannel == null)
          break; 
        childChannel.fireChildReadComplete();
      } 
    } finally {
      this.parentReadInProgress = false;
      this.readCompletePendingQueue.clear();
      flush0(this.ctx);
    } 
  }
  
  public final void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    this.parentReadInProgress = true;
    super.channelRead(ctx, msg);
  }
  
  public final void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
    if (ctx.channel().isWritable())
      forEachActiveStream(AbstractHttp2StreamChannel.WRITABLE_VISITOR); 
    super.channelWritabilityChanged(ctx);
  }
  
  final void onUserEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
    if (evt == ChannelInputShutdownReadComplete.INSTANCE) {
      forEachActiveStream(AbstractHttp2StreamChannel.CHANNEL_INPUT_SHUTDOWN_READ_COMPLETE_VISITOR);
    } else if (evt == ChannelOutputShutdownEvent.INSTANCE) {
      forEachActiveStream(AbstractHttp2StreamChannel.CHANNEL_OUTPUT_SHUTDOWN_EVENT_VISITOR);
    } else if (evt == SslCloseCompletionEvent.SUCCESS) {
      forEachActiveStream(AbstractHttp2StreamChannel.SSL_CLOSE_COMPLETION_EVENT_VISITOR);
    } 
    super.onUserEventTriggered(ctx, evt);
  }
  
  final void flush0(ChannelHandlerContext ctx) {
    flush(ctx);
  }
  
  private final class Http2MultiplexCodecStreamChannel extends AbstractHttp2StreamChannel {
    Http2MultiplexCodecStreamChannel(Http2FrameCodec.DefaultHttp2FrameStream stream, ChannelHandler inboundHandler) {
      super(stream, ++Http2MultiplexCodec.this.idCount, inboundHandler);
    }
    
    protected boolean isParentReadInProgress() {
      return Http2MultiplexCodec.this.parentReadInProgress;
    }
    
    protected void addChannelToReadCompletePendingQueue() {
      while (!Http2MultiplexCodec.this.readCompletePendingQueue.offer(this))
        Http2MultiplexCodec.this.processPendingReadCompleteQueue(); 
    }
    
    protected ChannelHandlerContext parentContext() {
      return Http2MultiplexCodec.this.ctx;
    }
    
    protected ChannelFuture write0(ChannelHandlerContext ctx, Object msg) {
      ChannelPromise promise = ctx.newPromise();
      Http2MultiplexCodec.this.write(ctx, msg, promise);
      return (ChannelFuture)promise;
    }
    
    protected void flush0(ChannelHandlerContext ctx) {
      Http2MultiplexCodec.this.flush0(ctx);
    }
  }
}
