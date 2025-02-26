package io.netty.handler.codec.http;

import io.netty.channel.ChannelHandler;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.compression.Brotli;
import io.netty.handler.codec.compression.BrotliDecoder;
import io.netty.handler.codec.compression.SnappyFrameDecoder;
import io.netty.handler.codec.compression.ZlibCodecFactory;
import io.netty.handler.codec.compression.ZlibWrapper;

public class HttpContentDecompressor extends HttpContentDecoder {
  private final boolean strict;
  
  public HttpContentDecompressor() {
    this(false);
  }
  
  public HttpContentDecompressor(boolean strict) {
    this.strict = strict;
  }
  
  protected EmbeddedChannel newContentDecoder(String contentEncoding) throws Exception {
    if (HttpHeaderValues.GZIP.contentEqualsIgnoreCase(contentEncoding) || HttpHeaderValues.X_GZIP
      .contentEqualsIgnoreCase(contentEncoding))
      return new EmbeddedChannel(this.ctx.channel().id(), this.ctx.channel().metadata().hasDisconnect(), this.ctx
          .channel().config(), new ChannelHandler[] { (ChannelHandler)ZlibCodecFactory.newZlibDecoder(ZlibWrapper.GZIP) }); 
    if (HttpHeaderValues.DEFLATE.contentEqualsIgnoreCase(contentEncoding) || HttpHeaderValues.X_DEFLATE
      .contentEqualsIgnoreCase(contentEncoding)) {
      ZlibWrapper wrapper = this.strict ? ZlibWrapper.ZLIB : ZlibWrapper.ZLIB_OR_NONE;
      return new EmbeddedChannel(this.ctx.channel().id(), this.ctx.channel().metadata().hasDisconnect(), this.ctx
          .channel().config(), new ChannelHandler[] { (ChannelHandler)ZlibCodecFactory.newZlibDecoder(wrapper) });
    } 
    if (Brotli.isAvailable() && HttpHeaderValues.BR.contentEqualsIgnoreCase(contentEncoding))
      return new EmbeddedChannel(this.ctx.channel().id(), this.ctx.channel().metadata().hasDisconnect(), this.ctx
          .channel().config(), new ChannelHandler[] { (ChannelHandler)new BrotliDecoder() }); 
    if (HttpHeaderValues.SNAPPY.contentEqualsIgnoreCase(contentEncoding))
      return new EmbeddedChannel(this.ctx.channel().id(), this.ctx.channel().metadata().hasDisconnect(), this.ctx
          .channel().config(), new ChannelHandler[] { (ChannelHandler)new SnappyFrameDecoder() }); 
    return null;
  }
}
