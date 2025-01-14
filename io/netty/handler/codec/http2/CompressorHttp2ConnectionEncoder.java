package io.netty.handler.codec.http2;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.channel.embedded.EmbeddedChannel;
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
import io.netty.handler.codec.compression.ZstdEncoder;
import io.netty.handler.codec.compression.ZstdOptions;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.util.AsciiString;
import io.netty.util.concurrent.Promise;
import io.netty.util.concurrent.PromiseCombiner;
import io.netty.util.internal.ObjectUtil;

public class CompressorHttp2ConnectionEncoder extends DecoratingHttp2ConnectionEncoder {
  public static final int DEFAULT_COMPRESSION_LEVEL = 6;
  
  public static final int DEFAULT_WINDOW_BITS = 15;
  
  public static final int DEFAULT_MEM_LEVEL = 8;
  
  private int compressionLevel;
  
  private int windowBits;
  
  private int memLevel;
  
  private final Http2Connection.PropertyKey propertyKey;
  
  private final boolean supportsCompressionOptions;
  
  private BrotliOptions brotliOptions;
  
  private GzipOptions gzipCompressionOptions;
  
  private DeflateOptions deflateOptions;
  
  private ZstdOptions zstdOptions;
  
  private SnappyOptions snappyOptions;
  
  public CompressorHttp2ConnectionEncoder(Http2ConnectionEncoder delegate) {
    this(delegate, defaultCompressionOptions());
  }
  
  private static CompressionOptions[] defaultCompressionOptions() {
    if (Brotli.isAvailable())
      return new CompressionOptions[] { (CompressionOptions)StandardCompressionOptions.brotli(), 
          (CompressionOptions)StandardCompressionOptions.snappy(), 
          (CompressionOptions)StandardCompressionOptions.gzip(), 
          (CompressionOptions)StandardCompressionOptions.deflate() }; 
    return new CompressionOptions[] { (CompressionOptions)StandardCompressionOptions.snappy(), 
        (CompressionOptions)StandardCompressionOptions.gzip(), (CompressionOptions)StandardCompressionOptions.deflate() };
  }
  
  @Deprecated
  public CompressorHttp2ConnectionEncoder(Http2ConnectionEncoder delegate, int compressionLevel, int windowBits, int memLevel) {
    super(delegate);
    this.compressionLevel = ObjectUtil.checkInRange(compressionLevel, 0, 9, "compressionLevel");
    this.windowBits = ObjectUtil.checkInRange(windowBits, 9, 15, "windowBits");
    this.memLevel = ObjectUtil.checkInRange(memLevel, 1, 9, "memLevel");
    this.propertyKey = connection().newKey();
    connection().addListener(new Http2ConnectionAdapter() {
          public void onStreamRemoved(Http2Stream stream) {
            EmbeddedChannel compressor = stream.<EmbeddedChannel>getProperty(CompressorHttp2ConnectionEncoder.this.propertyKey);
            if (compressor != null)
              CompressorHttp2ConnectionEncoder.this.cleanup(stream, compressor); 
          }
        });
    this.supportsCompressionOptions = false;
  }
  
  public CompressorHttp2ConnectionEncoder(Http2ConnectionEncoder delegate, CompressionOptions... compressionOptionsArgs) {
    super(delegate);
    ObjectUtil.checkNotNull(compressionOptionsArgs, "CompressionOptions");
    ObjectUtil.deepCheckNotNull("CompressionOptions", (Object[])compressionOptionsArgs);
    for (CompressionOptions compressionOptions : compressionOptionsArgs) {
      if (Brotli.isAvailable() && compressionOptions instanceof BrotliOptions) {
        this.brotliOptions = (BrotliOptions)compressionOptions;
      } else if (compressionOptions instanceof GzipOptions) {
        this.gzipCompressionOptions = (GzipOptions)compressionOptions;
      } else if (compressionOptions instanceof DeflateOptions) {
        this.deflateOptions = (DeflateOptions)compressionOptions;
      } else if (compressionOptions instanceof ZstdOptions) {
        this.zstdOptions = (ZstdOptions)compressionOptions;
      } else if (compressionOptions instanceof SnappyOptions) {
        this.snappyOptions = (SnappyOptions)compressionOptions;
      } else {
        throw new IllegalArgumentException("Unsupported " + CompressionOptions.class.getSimpleName() + ": " + compressionOptions);
      } 
    } 
    this.supportsCompressionOptions = true;
    this.propertyKey = connection().newKey();
    connection().addListener(new Http2ConnectionAdapter() {
          public void onStreamRemoved(Http2Stream stream) {
            EmbeddedChannel compressor = stream.<EmbeddedChannel>getProperty(CompressorHttp2ConnectionEncoder.this.propertyKey);
            if (compressor != null)
              CompressorHttp2ConnectionEncoder.this.cleanup(stream, compressor); 
          }
        });
  }
  
  public ChannelFuture writeData(ChannelHandlerContext ctx, int streamId, ByteBuf data, int padding, boolean endOfStream, ChannelPromise promise) {
    Http2Stream stream = connection().stream(streamId);
    EmbeddedChannel channel = (stream == null) ? null : stream.<EmbeddedChannel>getProperty(this.propertyKey);
    if (channel == null)
      return super.writeData(ctx, streamId, data, padding, endOfStream, promise); 
    try {
      channel.writeOutbound(new Object[] { data });
      ByteBuf buf = nextReadableBuf(channel);
      if (buf == null) {
        if (endOfStream) {
          if (channel.finish())
            buf = nextReadableBuf(channel); 
          return super.writeData(ctx, streamId, (buf == null) ? Unpooled.EMPTY_BUFFER : buf, padding, true, promise);
        } 
        promise.setSuccess();
        return (ChannelFuture)promise;
      } 
      PromiseCombiner combiner = new PromiseCombiner(ctx.executor());
      while (true) {
        ByteBuf nextBuf = nextReadableBuf(channel);
        boolean compressedEndOfStream = (nextBuf == null && endOfStream);
        if (compressedEndOfStream && channel.finish()) {
          nextBuf = nextReadableBuf(channel);
          compressedEndOfStream = (nextBuf == null);
        } 
        ChannelPromise bufPromise = ctx.newPromise();
        combiner.add((Promise)bufPromise);
        super.writeData(ctx, streamId, buf, padding, compressedEndOfStream, bufPromise);
        if (nextBuf == null)
          break; 
        padding = 0;
        buf = nextBuf;
      } 
      combiner.finish((Promise)promise);
    } catch (Throwable cause) {
      promise.tryFailure(cause);
    } finally {
      if (endOfStream)
        cleanup(stream, channel); 
    } 
    return (ChannelFuture)promise;
  }
  
  public ChannelFuture writeHeaders(ChannelHandlerContext ctx, int streamId, Http2Headers headers, int padding, boolean endStream, ChannelPromise promise) {
    try {
      EmbeddedChannel compressor = newCompressor(ctx, headers, endStream);
      ChannelFuture future = super.writeHeaders(ctx, streamId, headers, padding, endStream, promise);
      bindCompressorToStream(compressor, streamId);
      return future;
    } catch (Throwable e) {
      promise.tryFailure(e);
      return (ChannelFuture)promise;
    } 
  }
  
  public ChannelFuture writeHeaders(ChannelHandlerContext ctx, int streamId, Http2Headers headers, int streamDependency, short weight, boolean exclusive, int padding, boolean endOfStream, ChannelPromise promise) {
    try {
      EmbeddedChannel compressor = newCompressor(ctx, headers, endOfStream);
      ChannelFuture future = super.writeHeaders(ctx, streamId, headers, streamDependency, weight, exclusive, padding, endOfStream, promise);
      bindCompressorToStream(compressor, streamId);
      return future;
    } catch (Throwable e) {
      promise.tryFailure(e);
      return (ChannelFuture)promise;
    } 
  }
  
  protected EmbeddedChannel newContentCompressor(ChannelHandlerContext ctx, CharSequence contentEncoding) throws Http2Exception {
    if (HttpHeaderValues.GZIP.contentEqualsIgnoreCase(contentEncoding) || HttpHeaderValues.X_GZIP.contentEqualsIgnoreCase(contentEncoding))
      return newCompressionChannel(ctx, ZlibWrapper.GZIP); 
    if (HttpHeaderValues.DEFLATE.contentEqualsIgnoreCase(contentEncoding) || HttpHeaderValues.X_DEFLATE.contentEqualsIgnoreCase(contentEncoding))
      return newCompressionChannel(ctx, ZlibWrapper.ZLIB); 
    if (Brotli.isAvailable() && this.brotliOptions != null && HttpHeaderValues.BR.contentEqualsIgnoreCase(contentEncoding))
      return new EmbeddedChannel(ctx.channel().id(), ctx.channel().metadata().hasDisconnect(), ctx
          .channel().config(), new ChannelHandler[] { (ChannelHandler)new BrotliEncoder(this.brotliOptions.parameters()) }); 
    if (this.zstdOptions != null && HttpHeaderValues.ZSTD.contentEqualsIgnoreCase(contentEncoding))
      return new EmbeddedChannel(ctx.channel().id(), ctx.channel().metadata().hasDisconnect(), ctx
          .channel().config(), new ChannelHandler[] { (ChannelHandler)new ZstdEncoder(this.zstdOptions.compressionLevel(), this.zstdOptions
              .blockSize(), this.zstdOptions.maxEncodeSize()) }); 
    if (this.snappyOptions != null && HttpHeaderValues.SNAPPY.contentEqualsIgnoreCase(contentEncoding))
      return new EmbeddedChannel(ctx.channel().id(), ctx.channel().metadata().hasDisconnect(), ctx
          .channel().config(), new ChannelHandler[] { (ChannelHandler)new SnappyFrameEncoder() }); 
    return null;
  }
  
  protected CharSequence getTargetContentEncoding(CharSequence contentEncoding) throws Http2Exception {
    return contentEncoding;
  }
  
  private EmbeddedChannel newCompressionChannel(ChannelHandlerContext ctx, ZlibWrapper wrapper) {
    if (this.supportsCompressionOptions) {
      if (wrapper == ZlibWrapper.GZIP && this.gzipCompressionOptions != null)
        return new EmbeddedChannel(ctx.channel().id(), ctx.channel().metadata().hasDisconnect(), ctx
            .channel().config(), new ChannelHandler[] { (ChannelHandler)ZlibCodecFactory.newZlibEncoder(wrapper, this.gzipCompressionOptions
                .compressionLevel(), this.gzipCompressionOptions.windowBits(), this.gzipCompressionOptions
                .memLevel()) }); 
      if (wrapper == ZlibWrapper.ZLIB && this.deflateOptions != null)
        return new EmbeddedChannel(ctx.channel().id(), ctx.channel().metadata().hasDisconnect(), ctx
            .channel().config(), new ChannelHandler[] { (ChannelHandler)ZlibCodecFactory.newZlibEncoder(wrapper, this.deflateOptions
                .compressionLevel(), this.deflateOptions.windowBits(), this.deflateOptions
                .memLevel()) }); 
      throw new IllegalArgumentException("Unsupported ZlibWrapper: " + wrapper);
    } 
    return new EmbeddedChannel(ctx.channel().id(), ctx.channel().metadata().hasDisconnect(), ctx
        .channel().config(), new ChannelHandler[] { (ChannelHandler)ZlibCodecFactory.newZlibEncoder(wrapper, this.compressionLevel, this.windowBits, this.memLevel) });
  }
  
  private EmbeddedChannel newCompressor(ChannelHandlerContext ctx, Http2Headers headers, boolean endOfStream) throws Http2Exception {
    AsciiString asciiString;
    if (endOfStream)
      return null; 
    CharSequence encoding = (CharSequence)headers.get(HttpHeaderNames.CONTENT_ENCODING);
    if (encoding == null)
      asciiString = HttpHeaderValues.IDENTITY; 
    EmbeddedChannel compressor = newContentCompressor(ctx, (CharSequence)asciiString);
    if (compressor != null) {
      CharSequence targetContentEncoding = getTargetContentEncoding((CharSequence)asciiString);
      if (HttpHeaderValues.IDENTITY.contentEqualsIgnoreCase(targetContentEncoding)) {
        headers.remove(HttpHeaderNames.CONTENT_ENCODING);
      } else {
        headers.set(HttpHeaderNames.CONTENT_ENCODING, targetContentEncoding);
      } 
      headers.remove(HttpHeaderNames.CONTENT_LENGTH);
    } 
    return compressor;
  }
  
  private void bindCompressorToStream(EmbeddedChannel compressor, int streamId) {
    if (compressor != null) {
      Http2Stream stream = connection().stream(streamId);
      if (stream != null)
        stream.setProperty(this.propertyKey, compressor); 
    } 
  }
  
  void cleanup(Http2Stream stream, EmbeddedChannel compressor) {
    compressor.finishAndReleaseAll();
    stream.removeProperty(this.propertyKey);
  }
  
  private static ByteBuf nextReadableBuf(EmbeddedChannel compressor) {
    ByteBuf buf;
    while (true) {
      buf = (ByteBuf)compressor.readOutbound();
      if (buf == null)
        return null; 
      if (!buf.isReadable()) {
        buf.release();
        continue;
      } 
      break;
    } 
    return buf;
  }
}
