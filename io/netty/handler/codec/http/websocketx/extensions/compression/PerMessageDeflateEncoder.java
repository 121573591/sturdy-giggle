package io.netty.handler.codec.http.websocketx.extensions.compression;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.extensions.WebSocketExtensionFilter;
import java.util.List;

class PerMessageDeflateEncoder extends DeflateEncoder {
  private boolean compressing;
  
  PerMessageDeflateEncoder(int compressionLevel, int windowSize, boolean noContext) {
    super(compressionLevel, windowSize, noContext, WebSocketExtensionFilter.NEVER_SKIP);
  }
  
  PerMessageDeflateEncoder(int compressionLevel, int windowSize, boolean noContext, WebSocketExtensionFilter extensionEncoderFilter) {
    super(compressionLevel, windowSize, noContext, extensionEncoderFilter);
  }
  
  public boolean acceptOutboundMessage(Object msg) throws Exception {
    if (!super.acceptOutboundMessage(msg))
      return false; 
    WebSocketFrame wsFrame = (WebSocketFrame)msg;
    if (extensionEncoderFilter().mustSkip(wsFrame)) {
      if (this.compressing)
        throw new IllegalStateException("Cannot skip per message deflate encoder, compression in progress"); 
      return false;
    } 
    return (((wsFrame instanceof io.netty.handler.codec.http.websocketx.TextWebSocketFrame || wsFrame instanceof io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame) && (wsFrame
      .rsv() & 0x4) == 0) || (wsFrame instanceof io.netty.handler.codec.http.websocketx.ContinuationWebSocketFrame && this.compressing));
  }
  
  protected int rsv(WebSocketFrame msg) {
    return (msg instanceof io.netty.handler.codec.http.websocketx.TextWebSocketFrame || msg instanceof io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame) ? (msg
      .rsv() | 0x4) : msg.rsv();
  }
  
  protected boolean removeFrameTail(WebSocketFrame msg) {
    return msg.isFinalFragment();
  }
  
  protected void encode(ChannelHandlerContext ctx, WebSocketFrame msg, List<Object> out) throws Exception {
    super.encode(ctx, msg, out);
    if (msg.isFinalFragment()) {
      this.compressing = false;
    } else if (msg instanceof io.netty.handler.codec.http.websocketx.TextWebSocketFrame || msg instanceof io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame) {
      this.compressing = true;
    } 
  }
}
