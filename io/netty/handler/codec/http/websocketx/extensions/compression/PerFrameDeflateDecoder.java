package io.netty.handler.codec.http.websocketx.extensions.compression;

import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.extensions.WebSocketExtensionFilter;

class PerFrameDeflateDecoder extends DeflateDecoder {
  PerFrameDeflateDecoder(boolean noContext) {
    super(noContext, WebSocketExtensionFilter.NEVER_SKIP);
  }
  
  PerFrameDeflateDecoder(boolean noContext, WebSocketExtensionFilter extensionDecoderFilter) {
    super(noContext, extensionDecoderFilter);
  }
  
  public boolean acceptInboundMessage(Object msg) throws Exception {
    if (!super.acceptInboundMessage(msg))
      return false; 
    WebSocketFrame wsFrame = (WebSocketFrame)msg;
    if (extensionDecoderFilter().mustSkip(wsFrame))
      return false; 
    return ((msg instanceof io.netty.handler.codec.http.websocketx.TextWebSocketFrame || msg instanceof io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame || msg instanceof io.netty.handler.codec.http.websocketx.ContinuationWebSocketFrame) && (wsFrame
      
      .rsv() & 0x4) > 0);
  }
  
  protected int newRsv(WebSocketFrame msg) {
    return msg.rsv() ^ 0x4;
  }
  
  protected boolean appendFrameTail(WebSocketFrame msg) {
    return true;
  }
}
