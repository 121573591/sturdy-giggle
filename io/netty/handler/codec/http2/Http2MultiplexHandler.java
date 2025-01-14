package io.netty.handler.codec.http2;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.ChannelInputShutdownReadComplete;
import io.netty.channel.socket.ChannelOutputShutdownEvent;
import io.netty.handler.ssl.SslCloseCompletionEvent;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import io.netty.util.internal.ObjectUtil;
import java.util.ArrayDeque;
import java.util.Queue;

public final class Http2MultiplexHandler extends Http2ChannelDuplexHandler {
  static final ChannelFutureListener CHILD_CHANNEL_REGISTRATION_LISTENER = new ChannelFutureListener() {
      public void operationComplete(ChannelFuture future) {
        Http2MultiplexHandler.registerDone(future);
      }
    };
  
  private final ChannelHandler inboundStreamHandler;
  
  private final ChannelHandler upgradeStreamHandler;
  
  private final Queue<AbstractHttp2StreamChannel> readCompletePendingQueue = new MaxCapacityQueue<AbstractHttp2StreamChannel>(new ArrayDeque<AbstractHttp2StreamChannel>(8), 100);
  
  private boolean parentReadInProgress;
  
  private int idCount;
  
  private volatile ChannelHandlerContext ctx;
  
  public Http2MultiplexHandler(ChannelHandler inboundStreamHandler) {
    this(inboundStreamHandler, (ChannelHandler)null);
  }
  
  public Http2MultiplexHandler(ChannelHandler inboundStreamHandler, ChannelHandler upgradeStreamHandler) {
    this.inboundStreamHandler = (ChannelHandler)ObjectUtil.checkNotNull(inboundStreamHandler, "inboundStreamHandler");
    this.upgradeStreamHandler = upgradeStreamHandler;
  }
  
  static void registerDone(ChannelFuture future) {
    if (!future.isSuccess()) {
      Channel childChannel = future.channel();
      if (childChannel.isRegistered()) {
        childChannel.close();
      } else {
        childChannel.unsafe().closeForcibly();
      } 
    } 
  }
  
  protected void handlerAdded0(ChannelHandlerContext ctx) {
    if (ctx.executor() != ctx.channel().eventLoop())
      throw new IllegalStateException("EventExecutor must be EventLoop of Channel"); 
    this.ctx = ctx;
  }
  
  protected void handlerRemoved0(ChannelHandlerContext ctx) {
    this.readCompletePendingQueue.clear();
  }
  
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    this.parentReadInProgress = true;
    if (msg instanceof Http2StreamFrame) {
      if (msg instanceof Http2WindowUpdateFrame)
        return; 
      Http2StreamFrame streamFrame = (Http2StreamFrame)msg;
      Http2FrameCodec.DefaultHttp2FrameStream s = (Http2FrameCodec.DefaultHttp2FrameStream)streamFrame.stream();
      AbstractHttp2StreamChannel channel = (AbstractHttp2StreamChannel)s.attachment;
      if (msg instanceof Http2ResetFrame) {
        channel.pipeline().fireUserEventTriggered(msg);
      } else {
        channel.fireChildRead(streamFrame);
      } 
      return;
    } 
    if (msg instanceof Http2GoAwayFrame)
      onHttp2GoAwayFrame(ctx, (Http2GoAwayFrame)msg); 
    ctx.fireChannelRead(msg);
  }
  
  public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
    if (ctx.channel().isWritable())
      forEachActiveStream(AbstractHttp2StreamChannel.WRITABLE_VISITOR); 
    ctx.fireChannelWritabilityChanged();
  }
  
  public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
    if (evt instanceof Http2FrameStreamEvent) {
      Http2FrameStreamEvent event = (Http2FrameStreamEvent)evt;
      Http2FrameCodec.DefaultHttp2FrameStream stream = (Http2FrameCodec.DefaultHttp2FrameStream)event.stream();
      if (event.type() == Http2FrameStreamEvent.Type.State) {
        AbstractHttp2StreamChannel ch;
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
            if (stream.id() == 1 && !isServer(ctx)) {
              if (this.upgradeStreamHandler == null)
                throw Http2Exception.connectionError(Http2Error.INTERNAL_ERROR, "Client is misconfigured for upgrade requests", new Object[0]); 
              ch = new Http2MultiplexHandlerStreamChannel(stream, this.upgradeStreamHandler);
              ch.closeOutbound();
            } else {
              ch = new Http2MultiplexHandlerStreamChannel(stream, this.inboundStreamHandler);
            } 
            future = ctx.channel().eventLoop().register(ch);
            if (future.isDone()) {
              registerDone(future);
              break;
            } 
            future.addListener((GenericFutureListener)CHILD_CHANNEL_REGISTRATION_LISTENER);
            break;
          case CLOSED:
            channel = (AbstractHttp2StreamChannel)stream.attachment;
            if (channel != null)
              channel.streamClosed(); 
            break;
        } 
      } 
      return;
    } 
    if (evt == ChannelInputShutdownReadComplete.INSTANCE) {
      forEachActiveStream(AbstractHttp2StreamChannel.CHANNEL_INPUT_SHUTDOWN_READ_COMPLETE_VISITOR);
    } else if (evt == ChannelOutputShutdownEvent.INSTANCE) {
      forEachActiveStream(AbstractHttp2StreamChannel.CHANNEL_OUTPUT_SHUTDOWN_EVENT_VISITOR);
    } else if (evt == SslCloseCompletionEvent.SUCCESS) {
      forEachActiveStream(AbstractHttp2StreamChannel.SSL_CLOSE_COMPLETION_EVENT_VISITOR);
    } 
    ctx.fireUserEventTriggered(evt);
  }
  
  Http2StreamChannel newOutboundStream() {
    return new Http2MultiplexHandlerStreamChannel((Http2FrameCodec.DefaultHttp2FrameStream)newStream(), null);
  }
  
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    if (cause instanceof Http2FrameStreamException) {
      Http2FrameStreamException exception = (Http2FrameStreamException)cause;
      Http2FrameStream stream = exception.stream();
      AbstractHttp2StreamChannel childChannel = (AbstractHttp2StreamChannel)((Http2FrameCodec.DefaultHttp2FrameStream)stream).attachment;
      try {
        childChannel.pipeline().fireExceptionCaught(cause.getCause());
      } finally {
        childChannel.closeWithError(exception.error());
      } 
      return;
    } 
    if (cause instanceof Http2MultiplexActiveStreamsException) {
      fireExceptionCaughtForActiveStream(cause.getCause());
      return;
    } 
    if (cause.getCause() instanceof javax.net.ssl.SSLException)
      fireExceptionCaughtForActiveStream(cause); 
    ctx.fireExceptionCaught(cause);
  }
  
  private void fireExceptionCaughtForActiveStream(final Throwable cause) throws Http2Exception {
    forEachActiveStream(new Http2FrameStreamVisitor() {
          public boolean visit(Http2FrameStream stream) {
            AbstractHttp2StreamChannel childChannel = (AbstractHttp2StreamChannel)((Http2FrameCodec.DefaultHttp2FrameStream)stream).attachment;
            childChannel.pipeline().fireExceptionCaught(cause);
            return true;
          }
        });
  }
  
  private static boolean isServer(ChannelHandlerContext ctx) {
    return ctx.channel().parent() instanceof io.netty.channel.ServerChannel;
  }
  
  private void onHttp2GoAwayFrame(ChannelHandlerContext ctx, final Http2GoAwayFrame goAwayFrame) {
    if (goAwayFrame.lastStreamId() == Integer.MAX_VALUE)
      return; 
    try {
      final boolean server = isServer(ctx);
      forEachActiveStream(new Http2FrameStreamVisitor() {
            public boolean visit(Http2FrameStream stream) {
              int streamId = stream.id();
              if (streamId > goAwayFrame.lastStreamId() && Http2CodecUtil.isStreamIdValid(streamId, server)) {
                AbstractHttp2StreamChannel childChannel = (AbstractHttp2StreamChannel)((Http2FrameCodec.DefaultHttp2FrameStream)stream).attachment;
                childChannel.pipeline().fireUserEventTriggered(goAwayFrame.retainedDuplicate());
              } 
              return true;
            }
          });
    } catch (Http2Exception e) {
      ctx.fireExceptionCaught(e);
      ctx.close();
    } 
  }
  
  public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
    processPendingReadCompleteQueue();
    ctx.fireChannelReadComplete();
  }
  
  private void processPendingReadCompleteQueue() {
    this.parentReadInProgress = true;
    AbstractHttp2StreamChannel childChannel = this.readCompletePendingQueue.poll();
    if (childChannel != null) {
      try {
        do {
          childChannel.fireChildReadComplete();
          childChannel = this.readCompletePendingQueue.poll();
        } while (childChannel != null);
      } finally {
        this.parentReadInProgress = false;
        this.readCompletePendingQueue.clear();
        this.ctx.flush();
      } 
    } else {
      this.parentReadInProgress = false;
    } 
  }
  
  private final class Http2MultiplexHandlerStreamChannel extends AbstractHttp2StreamChannel {
    Http2MultiplexHandlerStreamChannel(Http2FrameCodec.DefaultHttp2FrameStream stream, ChannelHandler inboundHandler) {
      super(stream, ++Http2MultiplexHandler.this.idCount, inboundHandler);
    }
    
    protected boolean isParentReadInProgress() {
      return Http2MultiplexHandler.this.parentReadInProgress;
    }
    
    protected void addChannelToReadCompletePendingQueue() {
      while (!Http2MultiplexHandler.this.readCompletePendingQueue.offer(this))
        Http2MultiplexHandler.this.processPendingReadCompleteQueue(); 
    }
    
    protected ChannelHandlerContext parentContext() {
      return Http2MultiplexHandler.this.ctx;
    }
  }
}
