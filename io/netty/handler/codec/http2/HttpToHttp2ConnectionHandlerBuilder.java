package io.netty.handler.codec.http2;

import io.netty.handler.codec.http.HttpScheme;

public final class HttpToHttp2ConnectionHandlerBuilder extends AbstractHttp2ConnectionHandlerBuilder<HttpToHttp2ConnectionHandler, HttpToHttp2ConnectionHandlerBuilder> {
  private HttpScheme httpScheme;
  
  public HttpToHttp2ConnectionHandlerBuilder validateHeaders(boolean validateHeaders) {
    return super.validateHeaders(validateHeaders);
  }
  
  public HttpToHttp2ConnectionHandlerBuilder initialSettings(Http2Settings settings) {
    return super.initialSettings(settings);
  }
  
  public HttpToHttp2ConnectionHandlerBuilder frameListener(Http2FrameListener frameListener) {
    return super.frameListener(frameListener);
  }
  
  public HttpToHttp2ConnectionHandlerBuilder gracefulShutdownTimeoutMillis(long gracefulShutdownTimeoutMillis) {
    return super.gracefulShutdownTimeoutMillis(gracefulShutdownTimeoutMillis);
  }
  
  public HttpToHttp2ConnectionHandlerBuilder server(boolean isServer) {
    return super.server(isServer);
  }
  
  public HttpToHttp2ConnectionHandlerBuilder connection(Http2Connection connection) {
    return super.connection(connection);
  }
  
  public HttpToHttp2ConnectionHandlerBuilder codec(Http2ConnectionDecoder decoder, Http2ConnectionEncoder encoder) {
    return super.codec(decoder, encoder);
  }
  
  public HttpToHttp2ConnectionHandlerBuilder frameLogger(Http2FrameLogger frameLogger) {
    return super.frameLogger(frameLogger);
  }
  
  public HttpToHttp2ConnectionHandlerBuilder encoderEnforceMaxConcurrentStreams(boolean encoderEnforceMaxConcurrentStreams) {
    return super.encoderEnforceMaxConcurrentStreams(encoderEnforceMaxConcurrentStreams);
  }
  
  public HttpToHttp2ConnectionHandlerBuilder headerSensitivityDetector(Http2HeadersEncoder.SensitivityDetector headerSensitivityDetector) {
    return super.headerSensitivityDetector(headerSensitivityDetector);
  }
  
  @Deprecated
  public HttpToHttp2ConnectionHandlerBuilder initialHuffmanDecodeCapacity(int initialHuffmanDecodeCapacity) {
    return super.initialHuffmanDecodeCapacity(initialHuffmanDecodeCapacity);
  }
  
  public HttpToHttp2ConnectionHandlerBuilder decoupleCloseAndGoAway(boolean decoupleCloseAndGoAway) {
    return super.decoupleCloseAndGoAway(decoupleCloseAndGoAway);
  }
  
  public HttpToHttp2ConnectionHandlerBuilder flushPreface(boolean flushPreface) {
    return super.flushPreface(flushPreface);
  }
  
  public HttpToHttp2ConnectionHandlerBuilder httpScheme(HttpScheme httpScheme) {
    this.httpScheme = httpScheme;
    return self();
  }
  
  public HttpToHttp2ConnectionHandler build() {
    return super.build();
  }
  
  protected HttpToHttp2ConnectionHandler build(Http2ConnectionDecoder decoder, Http2ConnectionEncoder encoder, Http2Settings initialSettings) {
    return new HttpToHttp2ConnectionHandler(decoder, encoder, initialSettings, isValidateHeaders(), 
        decoupleCloseAndGoAway(), flushPreface(), this.httpScheme);
  }
}
