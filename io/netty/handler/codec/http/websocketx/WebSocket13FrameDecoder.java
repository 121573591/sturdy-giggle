package io.netty.handler.codec.http.websocketx;

public class WebSocket13FrameDecoder extends WebSocket08FrameDecoder {
  public WebSocket13FrameDecoder(boolean expectMaskedFrames, boolean allowExtensions, int maxFramePayloadLength) {
    this(expectMaskedFrames, allowExtensions, maxFramePayloadLength, false);
  }
  
  public WebSocket13FrameDecoder(boolean expectMaskedFrames, boolean allowExtensions, int maxFramePayloadLength, boolean allowMaskMismatch) {
    this(WebSocketDecoderConfig.newBuilder()
        .expectMaskedFrames(expectMaskedFrames)
        .allowExtensions(allowExtensions)
        .maxFramePayloadLength(maxFramePayloadLength)
        .allowMaskMismatch(allowMaskMismatch)
        .build());
  }
  
  public WebSocket13FrameDecoder(WebSocketDecoderConfig decoderConfig) {
    super(decoderConfig);
  }
}
