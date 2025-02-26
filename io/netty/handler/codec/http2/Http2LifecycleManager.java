package io.netty.handler.codec.http2;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;

public interface Http2LifecycleManager {
  void closeStreamLocal(Http2Stream paramHttp2Stream, ChannelFuture paramChannelFuture);
  
  void closeStreamRemote(Http2Stream paramHttp2Stream, ChannelFuture paramChannelFuture);
  
  void closeStream(Http2Stream paramHttp2Stream, ChannelFuture paramChannelFuture);
  
  ChannelFuture resetStream(ChannelHandlerContext paramChannelHandlerContext, int paramInt, long paramLong, ChannelPromise paramChannelPromise);
  
  ChannelFuture goAway(ChannelHandlerContext paramChannelHandlerContext, int paramInt, long paramLong, ByteBuf paramByteBuf, ChannelPromise paramChannelPromise);
  
  void onError(ChannelHandlerContext paramChannelHandlerContext, boolean paramBoolean, Throwable paramThrowable);
}
