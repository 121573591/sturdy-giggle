package io.netty.handler.codec.http;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.handler.codec.compression.Brotli;
import io.netty.handler.codec.compression.BrotliEncoder;
import io.netty.handler.codec.compression.BrotliOptions;
import io.netty.handler.codec.compression.CompressionOptions;
import io.netty.handler.codec.compression.DeflateOptions;
import io.netty.handler.codec.compression.GzipOptions;
import io.netty.handler.codec.compression.SnappyFrameEncoder;
import io.netty.handler.codec.compression.SnappyOptions;
import io.netty.handler.codec.compression.StandardCompressionOptions;
import io.netty.handler.codec.compression.ZlibCodecFactory;
import io.netty.handler.codec.compression.ZlibWrapper;
import io.netty.handler.codec.compression.Zstd;
import io.netty.handler.codec.compression.ZstdEncoder;
import io.netty.handler.codec.compression.ZstdOptions;
import io.netty.util.internal.ObjectUtil;
import java.util.HashMap;
import java.util.Map;

public class HttpContentCompressor extends HttpContentEncoder {
  private final boolean supportsCompressionOptions;
  
  private final BrotliOptions brotliOptions;
  
  private final GzipOptions gzipOptions;
  
  private final DeflateOptions deflateOptions;
  
  private final ZstdOptions zstdOptions;
  
  private final SnappyOptions snappyOptions;
  
  private final int compressionLevel;
  
  private final int windowBits;
  
  private final int memLevel;
  
  private final int contentSizeThreshold;
  
  private ChannelHandlerContext ctx;
  
  private final Map<String, CompressionEncoderFactory> factories;
  
  public HttpContentCompressor() {
    this(6);
  }
  
  @Deprecated
  public HttpContentCompressor(int compressionLevel) {
    this(compressionLevel, 15, 8, 0);
  }
  
  @Deprecated
  public HttpContentCompressor(int compressionLevel, int windowBits, int memLevel) {
    this(compressionLevel, windowBits, memLevel, 0);
  }
  
  @Deprecated
  public HttpContentCompressor(int compressionLevel, int windowBits, int memLevel, int contentSizeThreshold) {
    this.compressionLevel = ObjectUtil.checkInRange(compressionLevel, 0, 9, "compressionLevel");
    this.windowBits = ObjectUtil.checkInRange(windowBits, 9, 15, "windowBits");
    this.memLevel = ObjectUtil.checkInRange(memLevel, 1, 9, "memLevel");
    this.contentSizeThreshold = ObjectUtil.checkPositiveOrZero(contentSizeThreshold, "contentSizeThreshold");
    this.brotliOptions = null;
    this.gzipOptions = null;
    this.deflateOptions = null;
    this.zstdOptions = null;
    this.snappyOptions = null;
    this.factories = null;
    this.supportsCompressionOptions = false;
  }
  
  public HttpContentCompressor(CompressionOptions... compressionOptions) {
    this(0, compressionOptions);
  }
  
  public HttpContentCompressor(int contentSizeThreshold, CompressionOptions... compressionOptions) {
    this.contentSizeThreshold = ObjectUtil.checkPositiveOrZero(contentSizeThreshold, "contentSizeThreshold");
    BrotliOptions brotliOptions = null;
    GzipOptions gzipOptions = null;
    DeflateOptions deflateOptions = null;
    ZstdOptions zstdOptions = null;
    SnappyOptions snappyOptions = null;
    if (compressionOptions == null || compressionOptions.length == 0) {
      brotliOptions = Brotli.isAvailable() ? StandardCompressionOptions.brotli() : null;
      gzipOptions = StandardCompressionOptions.gzip();
      deflateOptions = StandardCompressionOptions.deflate();
      zstdOptions = Zstd.isAvailable() ? StandardCompressionOptions.zstd() : null;
      snappyOptions = StandardCompressionOptions.snappy();
    } else {
      ObjectUtil.deepCheckNotNull("compressionOptions", (Object[])compressionOptions);
      for (CompressionOptions compressionOption : compressionOptions) {
        if (Brotli.isAvailable() && compressionOption instanceof BrotliOptions) {
          brotliOptions = (BrotliOptions)compressionOption;
        } else if (compressionOption instanceof GzipOptions) {
          gzipOptions = (GzipOptions)compressionOption;
        } else if (compressionOption instanceof DeflateOptions) {
          deflateOptions = (DeflateOptions)compressionOption;
        } else if (compressionOption instanceof ZstdOptions) {
          zstdOptions = (ZstdOptions)compressionOption;
        } else if (compressionOption instanceof SnappyOptions) {
          snappyOptions = (SnappyOptions)compressionOption;
        } else {
          throw new IllegalArgumentException("Unsupported " + CompressionOptions.class.getSimpleName() + ": " + compressionOption);
        } 
      } 
    } 
    this.gzipOptions = gzipOptions;
    this.deflateOptions = deflateOptions;
    this.brotliOptions = brotliOptions;
    this.zstdOptions = zstdOptions;
    this.snappyOptions = snappyOptions;
    this.factories = new HashMap<String, CompressionEncoderFactory>();
    if (this.gzipOptions != null)
      this.factories.put("gzip", new GzipEncoderFactory()); 
    if (this.deflateOptions != null)
      this.factories.put("deflate", new DeflateEncoderFactory()); 
    if (Brotli.isAvailable() && this.brotliOptions != null)
      this.factories.put("br", new BrEncoderFactory()); 
    if (this.zstdOptions != null)
      this.factories.put("zstd", new ZstdEncoderFactory()); 
    if (this.snappyOptions != null)
      this.factories.put("snappy", new SnappyEncoderFactory()); 
    this.compressionLevel = -1;
    this.windowBits = -1;
    this.memLevel = -1;
    this.supportsCompressionOptions = true;
  }
  
  public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
    this.ctx = ctx;
  }
  
  protected HttpContentEncoder.Result beginEncode(HttpResponse httpResponse, String acceptEncoding) throws Exception {
    String targetContentEncoding;
    if (this.contentSizeThreshold > 0 && 
      httpResponse instanceof HttpContent && ((HttpContent)httpResponse)
      .content().readableBytes() < this.contentSizeThreshold)
      return null; 
    String contentEncoding = httpResponse.headers().get((CharSequence)HttpHeaderNames.CONTENT_ENCODING);
    if (contentEncoding != null)
      return null; 
    if (this.supportsCompressionOptions) {
      String str = determineEncoding(acceptEncoding);
      if (str == null)
        return null; 
      CompressionEncoderFactory encoderFactory = this.factories.get(str);
      if (encoderFactory == null)
        throw new Error(); 
      return new HttpContentEncoder.Result(str, new EmbeddedChannel(this.ctx
            .channel().id(), this.ctx.channel().metadata().hasDisconnect(), this.ctx
            .channel().config(), new ChannelHandler[] { (ChannelHandler)encoderFactory.createEncoder() }));
    } 
    ZlibWrapper wrapper = determineWrapper(acceptEncoding);
    if (wrapper == null)
      return null; 
    switch (wrapper) {
      case GZIP:
        targetContentEncoding = "gzip";
        break;
      case ZLIB:
        targetContentEncoding = "deflate";
        break;
      default:
        throw new Error();
    } 
    return new HttpContentEncoder.Result(targetContentEncoding, new EmbeddedChannel(this.ctx
          
          .channel().id(), this.ctx.channel().metadata().hasDisconnect(), this.ctx
          .channel().config(), new ChannelHandler[] { (ChannelHandler)ZlibCodecFactory.newZlibEncoder(wrapper, this.compressionLevel, this.windowBits, this.memLevel) }));
  }
  
  protected String determineEncoding(String acceptEncoding) {
    float starQ = -1.0F;
    float brQ = -1.0F;
    float zstdQ = -1.0F;
    float snappyQ = -1.0F;
    float gzipQ = -1.0F;
    float deflateQ = -1.0F;
    for (String encoding : acceptEncoding.split(",")) {
      float q = 1.0F;
      int equalsPos = encoding.indexOf('=');
      if (equalsPos != -1)
        try {
          q = Float.parseFloat(encoding.substring(equalsPos + 1));
        } catch (NumberFormatException e) {
          q = 0.0F;
        }  
      if (encoding.contains("*")) {
        starQ = q;
      } else if (encoding.contains("br") && q > brQ) {
        brQ = q;
      } else if (encoding.contains("zstd") && q > zstdQ) {
        zstdQ = q;
      } else if (encoding.contains("snappy") && q > snappyQ) {
        snappyQ = q;
      } else if (encoding.contains("gzip") && q > gzipQ) {
        gzipQ = q;
      } else if (encoding.contains("deflate") && q > deflateQ) {
        deflateQ = q;
      } 
    } 
    if (brQ > 0.0F || zstdQ > 0.0F || snappyQ > 0.0F || gzipQ > 0.0F || deflateQ > 0.0F) {
      if (brQ != -1.0F && brQ >= zstdQ && this.brotliOptions != null)
        return "br"; 
      if (zstdQ != -1.0F && zstdQ >= snappyQ && this.zstdOptions != null)
        return "zstd"; 
      if (snappyQ != -1.0F && snappyQ >= gzipQ && this.snappyOptions != null)
        return "snappy"; 
      if (gzipQ != -1.0F && gzipQ >= deflateQ && this.gzipOptions != null)
        return "gzip"; 
      if (deflateQ != -1.0F && this.deflateOptions != null)
        return "deflate"; 
    } 
    if (starQ > 0.0F) {
      if (brQ == -1.0F && this.brotliOptions != null)
        return "br"; 
      if (zstdQ == -1.0F && this.zstdOptions != null)
        return "zstd"; 
      if (snappyQ == -1.0F && this.snappyOptions != null)
        return "snappy"; 
      if (gzipQ == -1.0F && this.gzipOptions != null)
        return "gzip"; 
      if (deflateQ == -1.0F && this.deflateOptions != null)
        return "deflate"; 
    } 
    return null;
  }
  
  @Deprecated
  protected ZlibWrapper determineWrapper(String acceptEncoding) {
    float starQ = -1.0F;
    float gzipQ = -1.0F;
    float deflateQ = -1.0F;
    for (String encoding : acceptEncoding.split(",")) {
      float q = 1.0F;
      int equalsPos = encoding.indexOf('=');
      if (equalsPos != -1)
        try {
          q = Float.parseFloat(encoding.substring(equalsPos + 1));
        } catch (NumberFormatException e) {
          q = 0.0F;
        }  
      if (encoding.contains("*")) {
        starQ = q;
      } else if (encoding.contains("gzip") && q > gzipQ) {
        gzipQ = q;
      } else if (encoding.contains("deflate") && q > deflateQ) {
        deflateQ = q;
      } 
    } 
    if (gzipQ > 0.0F || deflateQ > 0.0F) {
      if (gzipQ >= deflateQ)
        return ZlibWrapper.GZIP; 
      return ZlibWrapper.ZLIB;
    } 
    if (starQ > 0.0F) {
      if (gzipQ == -1.0F)
        return ZlibWrapper.GZIP; 
      if (deflateQ == -1.0F)
        return ZlibWrapper.ZLIB; 
    } 
    return null;
  }
  
  private final class GzipEncoderFactory implements CompressionEncoderFactory {
    private GzipEncoderFactory() {}
    
    public MessageToByteEncoder<ByteBuf> createEncoder() {
      return (MessageToByteEncoder<ByteBuf>)ZlibCodecFactory.newZlibEncoder(ZlibWrapper.GZIP, HttpContentCompressor.this
          .gzipOptions.compressionLevel(), HttpContentCompressor.this
          .gzipOptions.windowBits(), HttpContentCompressor.this.gzipOptions.memLevel());
    }
  }
  
  private final class DeflateEncoderFactory implements CompressionEncoderFactory {
    private DeflateEncoderFactory() {}
    
    public MessageToByteEncoder<ByteBuf> createEncoder() {
      return (MessageToByteEncoder<ByteBuf>)ZlibCodecFactory.newZlibEncoder(ZlibWrapper.ZLIB, HttpContentCompressor.this
          .deflateOptions.compressionLevel(), HttpContentCompressor.this
          .deflateOptions.windowBits(), HttpContentCompressor.this.deflateOptions.memLevel());
    }
  }
  
  private final class BrEncoderFactory implements CompressionEncoderFactory {
    private BrEncoderFactory() {}
    
    public MessageToByteEncoder<ByteBuf> createEncoder() {
      return (MessageToByteEncoder<ByteBuf>)new BrotliEncoder(HttpContentCompressor.this.brotliOptions.parameters());
    }
  }
  
  private final class ZstdEncoderFactory implements CompressionEncoderFactory {
    private ZstdEncoderFactory() {}
    
    public MessageToByteEncoder<ByteBuf> createEncoder() {
      return (MessageToByteEncoder<ByteBuf>)new ZstdEncoder(HttpContentCompressor.this.zstdOptions.compressionLevel(), HttpContentCompressor.this
          .zstdOptions.blockSize(), HttpContentCompressor.this.zstdOptions.maxEncodeSize());
    }
  }
  
  private static final class SnappyEncoderFactory implements CompressionEncoderFactory {
    private SnappyEncoderFactory() {}
    
    public MessageToByteEncoder<ByteBuf> createEncoder() {
      return (MessageToByteEncoder<ByteBuf>)new SnappyFrameEncoder();
    }
  }
}
