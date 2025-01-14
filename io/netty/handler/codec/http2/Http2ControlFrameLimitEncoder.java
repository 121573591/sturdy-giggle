package io.netty.handler.codec.http2;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

final class Http2ControlFrameLimitEncoder extends DecoratingHttp2ConnectionEncoder {
  private static final InternalLogger logger = InternalLoggerFactory.getInstance(Http2ControlFrameLimitEncoder.class);
  
  private final int maxOutstandingControlFrames;
  
  private final ChannelFutureListener outstandingControlFramesListener = new ChannelFutureListener() {
      public void operationComplete(ChannelFuture future) {
        Http2ControlFrameLimitEncoder.this.outstandingControlFrames--;
      }
    };
  
  private Http2LifecycleManager lifecycleManager;
  
  private int outstandingControlFrames;
  
  private boolean limitReached;
  
  Http2ControlFrameLimitEncoder(Http2ConnectionEncoder delegate, int maxOutstandingControlFrames) {
    super(delegate);
    this.maxOutstandingControlFrames = ObjectUtil.checkPositive(maxOutstandingControlFrames, "maxOutstandingControlFrames");
  }
  
  public void lifecycleManager(Http2LifecycleManager lifecycleManager) {
    this.lifecycleManager = lifecycleManager;
    super.lifecycleManager(lifecycleManager);
  }
  
  public ChannelFuture writeSettingsAck(ChannelHandlerContext ctx, ChannelPromise promise) {
    ChannelPromise newPromise = handleOutstandingControlFrames(ctx, promise);
    if (newPromise == null)
      return (ChannelFuture)promise; 
    return super.writeSettingsAck(ctx, newPromise);
  }
  
  public ChannelFuture writePing(ChannelHandlerContext ctx, boolean ack, long data, ChannelPromise promise) {
    if (ack) {
      ChannelPromise newPromise = handleOutstandingControlFrames(ctx, promise);
      if (newPromise == null)
        return (ChannelFuture)promise; 
      return super.writePing(ctx, ack, data, newPromise);
    } 
    return super.writePing(ctx, ack, data, promise);
  }
  
  public ChannelFuture writeRstStream(ChannelHandlerContext ctx, int streamId, long errorCode, ChannelPromise promise) {
    ChannelPromise newPromise = handleOutstandingControlFrames(ctx, promise);
    if (newPromise == null)
      return (ChannelFuture)promise; 
    return super.writeRstStream(ctx, streamId, errorCode, newPromise);
  }
  
  private ChannelPromise handleOutstandingControlFrames(ChannelHandlerContext ctx, ChannelPromise promise) {
    if (!this.limitReached) {
      if (this.outstandingControlFrames == this.maxOutstandingControlFrames)
        ctx.flush(); 
      if (this.outstandingControlFrames == this.maxOutstandingControlFrames) {
        this.limitReached = true;
        Http2Exception exception = Http2Exception.connectionError(Http2Error.ENHANCE_YOUR_CALM, "Maximum number %d of outstanding control frames reached", new Object[] { Integer.valueOf(this.maxOutstandingControlFrames) });
        logger.info("Maximum number {} of outstanding control frames reached. Closing channel {}", new Object[] { Integer.valueOf(this.maxOutstandingControlFrames), ctx.channel(), exception });
        this.lifecycleManager.onError(ctx, true, exception);
        ctx.close();
      } 
      this.outstandingControlFrames++;
      return promise.unvoid().addListener((GenericFutureListener)this.outstandingControlFramesListener);
    } 
    return promise;
  }
}
