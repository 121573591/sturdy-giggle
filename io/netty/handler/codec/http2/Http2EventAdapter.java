package io.netty.handler.codec.http2;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

public class Http2EventAdapter implements Http2Connection.Listener, Http2FrameListener {
  public int onDataRead(ChannelHandlerContext ctx, int streamId, ByteBuf data, int padding, boolean endOfStream) throws Http2Exception {
    return data.readableBytes() + padding;
  }
  
  public void onHeadersRead(ChannelHandlerContext ctx, int streamId, Http2Headers headers, int padding, boolean endStream) throws Http2Exception {}
  
  public void onHeadersRead(ChannelHandlerContext ctx, int streamId, Http2Headers headers, int streamDependency, short weight, boolean exclusive, int padding, boolean endStream) throws Http2Exception {}
  
  public void onPriorityRead(ChannelHandlerContext ctx, int streamId, int streamDependency, short weight, boolean exclusive) throws Http2Exception {}
  
  public void onRstStreamRead(ChannelHandlerContext ctx, int streamId, long errorCode) throws Http2Exception {}
  
  public void onSettingsAckRead(ChannelHandlerContext ctx) throws Http2Exception {}
  
  public void onSettingsRead(ChannelHandlerContext ctx, Http2Settings settings) throws Http2Exception {}
  
  public void onPingRead(ChannelHandlerContext ctx, long data) throws Http2Exception {}
  
  public void onPingAckRead(ChannelHandlerContext ctx, long data) throws Http2Exception {}
  
  public void onPushPromiseRead(ChannelHandlerContext ctx, int streamId, int promisedStreamId, Http2Headers headers, int padding) throws Http2Exception {}
  
  public void onGoAwayRead(ChannelHandlerContext ctx, int lastStreamId, long errorCode, ByteBuf debugData) throws Http2Exception {}
  
  public void onWindowUpdateRead(ChannelHandlerContext ctx, int streamId, int windowSizeIncrement) throws Http2Exception {}
  
  public void onUnknownFrame(ChannelHandlerContext ctx, byte frameType, int streamId, Http2Flags flags, ByteBuf payload) throws Http2Exception {}
  
  public void onStreamAdded(Http2Stream stream) {}
  
  public void onStreamActive(Http2Stream stream) {}
  
  public void onStreamHalfClosed(Http2Stream stream) {}
  
  public void onStreamClosed(Http2Stream stream) {}
  
  public void onStreamRemoved(Http2Stream stream) {}
  
  public void onGoAwaySent(int lastStreamId, long errorCode, ByteBuf debugData) {}
  
  public void onGoAwayReceived(int lastStreamId, long errorCode, ByteBuf debugData) {}
}
