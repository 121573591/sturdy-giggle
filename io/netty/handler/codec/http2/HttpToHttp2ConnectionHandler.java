package io.netty.handler.codec.http2;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.http.EmptyHttpHeaders;
import io.netty.handler.codec.http.FullHttpMessage;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMessage;
import io.netty.handler.codec.http.HttpScheme;
import io.netty.handler.codec.http.LastHttpContent;
import io.netty.util.ReferenceCountUtil;

public class HttpToHttp2ConnectionHandler extends Http2ConnectionHandler {
  private final boolean validateHeaders;
  
  private int currentStreamId;
  
  private HttpScheme httpScheme;
  
  protected HttpToHttp2ConnectionHandler(Http2ConnectionDecoder decoder, Http2ConnectionEncoder encoder, Http2Settings initialSettings, boolean validateHeaders) {
    super(decoder, encoder, initialSettings);
    this.validateHeaders = validateHeaders;
  }
  
  protected HttpToHttp2ConnectionHandler(Http2ConnectionDecoder decoder, Http2ConnectionEncoder encoder, Http2Settings initialSettings, boolean validateHeaders, boolean decoupleCloseAndGoAway) {
    this(decoder, encoder, initialSettings, validateHeaders, decoupleCloseAndGoAway, (HttpScheme)null);
  }
  
  protected HttpToHttp2ConnectionHandler(Http2ConnectionDecoder decoder, Http2ConnectionEncoder encoder, Http2Settings initialSettings, boolean validateHeaders, boolean decoupleCloseAndGoAway, HttpScheme httpScheme) {
    super(decoder, encoder, initialSettings, decoupleCloseAndGoAway);
    this.validateHeaders = validateHeaders;
    this.httpScheme = httpScheme;
  }
  
  protected HttpToHttp2ConnectionHandler(Http2ConnectionDecoder decoder, Http2ConnectionEncoder encoder, Http2Settings initialSettings, boolean validateHeaders, boolean decoupleCloseAndGoAway, boolean flushPreface, HttpScheme httpScheme) {
    super(decoder, encoder, initialSettings, decoupleCloseAndGoAway, flushPreface);
    this.validateHeaders = validateHeaders;
    this.httpScheme = httpScheme;
  }
  
  private int getStreamId(HttpHeaders httpHeaders) throws Exception {
    return httpHeaders.getInt((CharSequence)HttpConversionUtil.ExtensionHeaderNames.STREAM_ID.text(), 
        connection().local().incrementAndGetNextStreamId());
  }
  
  public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) {
    if (!(msg instanceof HttpMessage) && !(msg instanceof HttpContent)) {
      ctx.write(msg, promise);
      return;
    } 
    boolean release = true;
    Http2CodecUtil.SimpleChannelPromiseAggregator promiseAggregator = new Http2CodecUtil.SimpleChannelPromiseAggregator(promise, ctx.channel(), ctx.executor());
    try {
      Http2ConnectionEncoder encoder = encoder();
      boolean endStream = false;
      if (msg instanceof HttpMessage) {
        HttpMessage httpMsg = (HttpMessage)msg;
        this.currentStreamId = getStreamId(httpMsg.headers());
        if (this.httpScheme != null && 
          !httpMsg.headers().contains((CharSequence)HttpConversionUtil.ExtensionHeaderNames.SCHEME.text()))
          httpMsg.headers().set((CharSequence)HttpConversionUtil.ExtensionHeaderNames.SCHEME.text(), this.httpScheme.name()); 
        Http2Headers http2Headers = HttpConversionUtil.toHttp2Headers(httpMsg, this.validateHeaders);
        endStream = (msg instanceof FullHttpMessage && !((FullHttpMessage)msg).content().isReadable());
        writeHeaders(ctx, encoder, this.currentStreamId, httpMsg.headers(), http2Headers, endStream, promiseAggregator);
      } 
      if (!endStream && msg instanceof HttpContent) {
        HttpHeaders httpHeaders;
        boolean isLastContent = false;
        EmptyHttpHeaders emptyHttpHeaders = EmptyHttpHeaders.INSTANCE;
        Http2Headers http2Trailers = EmptyHttp2Headers.INSTANCE;
        if (msg instanceof LastHttpContent) {
          isLastContent = true;
          LastHttpContent lastContent = (LastHttpContent)msg;
          httpHeaders = lastContent.trailingHeaders();
          http2Trailers = HttpConversionUtil.toHttp2Headers(httpHeaders, this.validateHeaders);
        } 
        ByteBuf content = ((HttpContent)msg).content();
        endStream = (isLastContent && httpHeaders.isEmpty());
        encoder.writeData(ctx, this.currentStreamId, content, 0, endStream, promiseAggregator.newPromise());
        release = false;
        if (!httpHeaders.isEmpty())
          writeHeaders(ctx, encoder, this.currentStreamId, httpHeaders, http2Trailers, true, promiseAggregator); 
      } 
    } catch (Throwable t) {
      onError(ctx, true, t);
      promiseAggregator.setFailure(t);
    } finally {
      if (release)
        ReferenceCountUtil.release(msg); 
      promiseAggregator.doneAllocatingPromises();
    } 
  }
  
  private static void writeHeaders(ChannelHandlerContext ctx, Http2ConnectionEncoder encoder, int streamId, HttpHeaders headers, Http2Headers http2Headers, boolean endStream, Http2CodecUtil.SimpleChannelPromiseAggregator promiseAggregator) {
    int dependencyId = headers.getInt((CharSequence)HttpConversionUtil.ExtensionHeaderNames.STREAM_DEPENDENCY_ID
        .text(), 0);
    short weight = headers.getShort((CharSequence)HttpConversionUtil.ExtensionHeaderNames.STREAM_WEIGHT
        .text(), (short)16);
    encoder.writeHeaders(ctx, streamId, http2Headers, dependencyId, weight, false, 0, endStream, promiseAggregator
        .newPromise());
  }
}
