package io.netty.handler.codec.http.websocketx.extensions.compression;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.CodecException;
import io.netty.handler.codec.compression.ZlibCodecFactory;
import io.netty.handler.codec.compression.ZlibWrapper;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.ContinuationWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.extensions.WebSocketExtensionEncoder;
import io.netty.handler.codec.http.websocketx.extensions.WebSocketExtensionFilter;
import io.netty.util.internal.ObjectUtil;
import java.util.List;

abstract class DeflateEncoder extends WebSocketExtensionEncoder {
  private final int compressionLevel;
  
  private final int windowSize;
  
  private final boolean noContext;
  
  private final WebSocketExtensionFilter extensionEncoderFilter;
  
  private EmbeddedChannel encoder;
  
  DeflateEncoder(int compressionLevel, int windowSize, boolean noContext, WebSocketExtensionFilter extensionEncoderFilter) {
    this.compressionLevel = compressionLevel;
    this.windowSize = windowSize;
    this.noContext = noContext;
    this.extensionEncoderFilter = (WebSocketExtensionFilter)ObjectUtil.checkNotNull(extensionEncoderFilter, "extensionEncoderFilter");
  }
  
  protected WebSocketExtensionFilter extensionEncoderFilter() {
    return this.extensionEncoderFilter;
  }
  
  protected void encode(ChannelHandlerContext ctx, WebSocketFrame msg, List<Object> out) throws Exception {
    ByteBuf compressedContent;
    ContinuationWebSocketFrame continuationWebSocketFrame;
    if (msg.content().isReadable()) {
      compressedContent = compressContent(ctx, msg);
    } else if (msg.isFinalFragment()) {
      compressedContent = PerMessageDeflateDecoder.EMPTY_DEFLATE_BLOCK.duplicate();
    } else {
      throw new CodecException("cannot compress content buffer");
    } 
    if (msg instanceof TextWebSocketFrame) {
      TextWebSocketFrame textWebSocketFrame = new TextWebSocketFrame(msg.isFinalFragment(), rsv(msg), compressedContent);
    } else if (msg instanceof BinaryWebSocketFrame) {
      BinaryWebSocketFrame binaryWebSocketFrame = new BinaryWebSocketFrame(msg.isFinalFragment(), rsv(msg), compressedContent);
    } else if (msg instanceof ContinuationWebSocketFrame) {
      continuationWebSocketFrame = new ContinuationWebSocketFrame(msg.isFinalFragment(), rsv(msg), compressedContent);
    } else {
      throw new CodecException("unexpected frame type: " + msg.getClass().getName());
    } 
    out.add(continuationWebSocketFrame);
  }
  
  public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
    cleanup();
    super.handlerRemoved(ctx);
  }
  
  private ByteBuf compressContent(ChannelHandlerContext ctx, WebSocketFrame msg) {
    CompositeByteBuf compositeByteBuf1;
    if (this.encoder == null)
      this.encoder = new EmbeddedChannel(new ChannelHandler[] { (ChannelHandler)ZlibCodecFactory.newZlibEncoder(ZlibWrapper.NONE, this.compressionLevel, this.windowSize, 8) }); 
    this.encoder.writeOutbound(new Object[] { msg.content().retain() });
    CompositeByteBuf fullCompressedContent = ctx.alloc().compositeBuffer();
    while (true) {
      ByteBuf partCompressedContent = (ByteBuf)this.encoder.readOutbound();
      if (partCompressedContent == null)
        break; 
      if (!partCompressedContent.isReadable()) {
        partCompressedContent.release();
        continue;
      } 
      fullCompressedContent.addComponent(true, partCompressedContent);
    } 
    if (fullCompressedContent.numComponents() <= 0) {
      fullCompressedContent.release();
      throw new CodecException("cannot read compressed buffer");
    } 
    if (msg.isFinalFragment() && this.noContext)
      cleanup(); 
    if (removeFrameTail(msg)) {
      int realLength = fullCompressedContent.readableBytes() - PerMessageDeflateDecoder.FRAME_TAIL.readableBytes();
      ByteBuf compressedContent = fullCompressedContent.slice(0, realLength);
    } else {
      compositeByteBuf1 = fullCompressedContent;
    } 
    return (ByteBuf)compositeByteBuf1;
  }
  
  private void cleanup() {
    if (this.encoder != null) {
      this.encoder.finishAndReleaseAll();
      this.encoder = null;
    } 
  }
  
  protected abstract int rsv(WebSocketFrame paramWebSocketFrame);
  
  protected abstract boolean removeFrameTail(WebSocketFrame paramWebSocketFrame);
}
