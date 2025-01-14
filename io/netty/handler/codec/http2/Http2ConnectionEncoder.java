package io.netty.handler.codec.http2;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;

public interface Http2ConnectionEncoder extends Http2FrameWriter {
  void lifecycleManager(Http2LifecycleManager paramHttp2LifecycleManager);
  
  Http2Connection connection();
  
  Http2RemoteFlowController flowController();
  
  Http2FrameWriter frameWriter();
  
  Http2Settings pollSentSettings();
  
  void remoteSettings(Http2Settings paramHttp2Settings) throws Http2Exception;
  
  ChannelFuture writeFrame(ChannelHandlerContext paramChannelHandlerContext, byte paramByte, int paramInt, Http2Flags paramHttp2Flags, ByteBuf paramByteBuf, ChannelPromise paramChannelPromise);
}
