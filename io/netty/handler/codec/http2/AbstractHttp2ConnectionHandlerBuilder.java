package io.netty.handler.codec.http2;

import io.netty.util.internal.ObjectUtil;

public abstract class AbstractHttp2ConnectionHandlerBuilder<T extends Http2ConnectionHandler, B extends AbstractHttp2ConnectionHandlerBuilder<T, B>> {
  private static final Http2HeadersEncoder.SensitivityDetector DEFAULT_HEADER_SENSITIVITY_DETECTOR = Http2HeadersEncoder.NEVER_SENSITIVE;
  
  private static final int DEFAULT_MAX_RST_FRAMES_PER_CONNECTION_FOR_SERVER = 200;
  
  private Http2Settings initialSettings = Http2Settings.defaultSettings();
  
  private Http2FrameListener frameListener;
  
  private long gracefulShutdownTimeoutMillis = Http2CodecUtil.DEFAULT_GRACEFUL_SHUTDOWN_TIMEOUT_MILLIS;
  
  private boolean decoupleCloseAndGoAway;
  
  private boolean flushPreface = true;
  
  private Boolean isServer;
  
  private Integer maxReservedStreams;
  
  private Http2Connection connection;
  
  private Http2ConnectionDecoder decoder;
  
  private Http2ConnectionEncoder encoder;
  
  private Boolean validateHeaders;
  
  private Http2FrameLogger frameLogger;
  
  private Http2HeadersEncoder.SensitivityDetector headerSensitivityDetector;
  
  private Boolean encoderEnforceMaxConcurrentStreams;
  
  private Boolean encoderIgnoreMaxHeaderListSize;
  
  private Http2PromisedRequestVerifier promisedRequestVerifier = Http2PromisedRequestVerifier.ALWAYS_VERIFY;
  
  private boolean autoAckSettingsFrame = true;
  
  private boolean autoAckPingFrame = true;
  
  private int maxQueuedControlFrames = 10000;
  
  private int maxConsecutiveEmptyFrames = 2;
  
  private Integer maxRstFramesPerWindow;
  
  private int secondsPerWindow = 30;
  
  protected Http2Settings initialSettings() {
    return this.initialSettings;
  }
  
  protected B initialSettings(Http2Settings settings) {
    this.initialSettings = (Http2Settings)ObjectUtil.checkNotNull(settings, "settings");
    return self();
  }
  
  protected Http2FrameListener frameListener() {
    return this.frameListener;
  }
  
  protected B frameListener(Http2FrameListener frameListener) {
    this.frameListener = (Http2FrameListener)ObjectUtil.checkNotNull(frameListener, "frameListener");
    return self();
  }
  
  protected long gracefulShutdownTimeoutMillis() {
    return this.gracefulShutdownTimeoutMillis;
  }
  
  protected B gracefulShutdownTimeoutMillis(long gracefulShutdownTimeoutMillis) {
    if (gracefulShutdownTimeoutMillis < -1L)
      throw new IllegalArgumentException("gracefulShutdownTimeoutMillis: " + gracefulShutdownTimeoutMillis + " (expected: -1 for indefinite or >= 0)"); 
    this.gracefulShutdownTimeoutMillis = gracefulShutdownTimeoutMillis;
    return self();
  }
  
  protected boolean isServer() {
    return (this.isServer != null) ? this.isServer.booleanValue() : true;
  }
  
  protected B server(boolean isServer) {
    enforceConstraint("server", "connection", this.connection);
    enforceConstraint("server", "codec", this.decoder);
    enforceConstraint("server", "codec", this.encoder);
    this.isServer = Boolean.valueOf(isServer);
    return self();
  }
  
  protected int maxReservedStreams() {
    return (this.maxReservedStreams != null) ? this.maxReservedStreams.intValue() : 100;
  }
  
  protected B maxReservedStreams(int maxReservedStreams) {
    enforceConstraint("server", "connection", this.connection);
    enforceConstraint("server", "codec", this.decoder);
    enforceConstraint("server", "codec", this.encoder);
    this.maxReservedStreams = Integer.valueOf(ObjectUtil.checkPositiveOrZero(maxReservedStreams, "maxReservedStreams"));
    return self();
  }
  
  protected Http2Connection connection() {
    return this.connection;
  }
  
  protected B connection(Http2Connection connection) {
    enforceConstraint("connection", "maxReservedStreams", this.maxReservedStreams);
    enforceConstraint("connection", "server", this.isServer);
    enforceConstraint("connection", "codec", this.decoder);
    enforceConstraint("connection", "codec", this.encoder);
    this.connection = (Http2Connection)ObjectUtil.checkNotNull(connection, "connection");
    return self();
  }
  
  protected Http2ConnectionDecoder decoder() {
    return this.decoder;
  }
  
  protected Http2ConnectionEncoder encoder() {
    return this.encoder;
  }
  
  protected B codec(Http2ConnectionDecoder decoder, Http2ConnectionEncoder encoder) {
    enforceConstraint("codec", "server", this.isServer);
    enforceConstraint("codec", "maxReservedStreams", this.maxReservedStreams);
    enforceConstraint("codec", "connection", this.connection);
    enforceConstraint("codec", "frameLogger", this.frameLogger);
    enforceConstraint("codec", "validateHeaders", this.validateHeaders);
    enforceConstraint("codec", "headerSensitivityDetector", this.headerSensitivityDetector);
    enforceConstraint("codec", "encoderEnforceMaxConcurrentStreams", this.encoderEnforceMaxConcurrentStreams);
    ObjectUtil.checkNotNull(decoder, "decoder");
    ObjectUtil.checkNotNull(encoder, "encoder");
    if (decoder.connection() != encoder.connection())
      throw new IllegalArgumentException("The specified encoder and decoder have different connections."); 
    this.decoder = decoder;
    this.encoder = encoder;
    return self();
  }
  
  protected boolean isValidateHeaders() {
    return (this.validateHeaders != null) ? this.validateHeaders.booleanValue() : true;
  }
  
  protected B validateHeaders(boolean validateHeaders) {
    enforceNonCodecConstraints("validateHeaders");
    this.validateHeaders = Boolean.valueOf(validateHeaders);
    return self();
  }
  
  protected Http2FrameLogger frameLogger() {
    return this.frameLogger;
  }
  
  protected B frameLogger(Http2FrameLogger frameLogger) {
    enforceNonCodecConstraints("frameLogger");
    this.frameLogger = (Http2FrameLogger)ObjectUtil.checkNotNull(frameLogger, "frameLogger");
    return self();
  }
  
  protected boolean encoderEnforceMaxConcurrentStreams() {
    return (this.encoderEnforceMaxConcurrentStreams != null) ? this.encoderEnforceMaxConcurrentStreams.booleanValue() : false;
  }
  
  protected B encoderEnforceMaxConcurrentStreams(boolean encoderEnforceMaxConcurrentStreams) {
    enforceNonCodecConstraints("encoderEnforceMaxConcurrentStreams");
    this.encoderEnforceMaxConcurrentStreams = Boolean.valueOf(encoderEnforceMaxConcurrentStreams);
    return self();
  }
  
  protected int encoderEnforceMaxQueuedControlFrames() {
    return this.maxQueuedControlFrames;
  }
  
  protected B encoderEnforceMaxQueuedControlFrames(int maxQueuedControlFrames) {
    enforceNonCodecConstraints("encoderEnforceMaxQueuedControlFrames");
    this.maxQueuedControlFrames = ObjectUtil.checkPositiveOrZero(maxQueuedControlFrames, "maxQueuedControlFrames");
    return self();
  }
  
  protected Http2HeadersEncoder.SensitivityDetector headerSensitivityDetector() {
    return (this.headerSensitivityDetector != null) ? this.headerSensitivityDetector : DEFAULT_HEADER_SENSITIVITY_DETECTOR;
  }
  
  protected B headerSensitivityDetector(Http2HeadersEncoder.SensitivityDetector headerSensitivityDetector) {
    enforceNonCodecConstraints("headerSensitivityDetector");
    this.headerSensitivityDetector = (Http2HeadersEncoder.SensitivityDetector)ObjectUtil.checkNotNull(headerSensitivityDetector, "headerSensitivityDetector");
    return self();
  }
  
  protected B encoderIgnoreMaxHeaderListSize(boolean ignoreMaxHeaderListSize) {
    enforceNonCodecConstraints("encoderIgnoreMaxHeaderListSize");
    this.encoderIgnoreMaxHeaderListSize = Boolean.valueOf(ignoreMaxHeaderListSize);
    return self();
  }
  
  @Deprecated
  protected B initialHuffmanDecodeCapacity(int initialHuffmanDecodeCapacity) {
    return self();
  }
  
  protected B promisedRequestVerifier(Http2PromisedRequestVerifier promisedRequestVerifier) {
    enforceNonCodecConstraints("promisedRequestVerifier");
    this.promisedRequestVerifier = (Http2PromisedRequestVerifier)ObjectUtil.checkNotNull(promisedRequestVerifier, "promisedRequestVerifier");
    return self();
  }
  
  protected Http2PromisedRequestVerifier promisedRequestVerifier() {
    return this.promisedRequestVerifier;
  }
  
  protected int decoderEnforceMaxConsecutiveEmptyDataFrames() {
    return this.maxConsecutiveEmptyFrames;
  }
  
  protected B decoderEnforceMaxConsecutiveEmptyDataFrames(int maxConsecutiveEmptyFrames) {
    enforceNonCodecConstraints("maxConsecutiveEmptyFrames");
    this.maxConsecutiveEmptyFrames = ObjectUtil.checkPositiveOrZero(maxConsecutiveEmptyFrames, "maxConsecutiveEmptyFrames");
    return self();
  }
  
  protected B decoderEnforceMaxRstFramesPerWindow(int maxRstFramesPerWindow, int secondsPerWindow) {
    enforceNonCodecConstraints("decoderEnforceMaxRstFramesPerWindow");
    this.maxRstFramesPerWindow = Integer.valueOf(ObjectUtil.checkPositiveOrZero(maxRstFramesPerWindow, "maxRstFramesPerWindow"));
    this.secondsPerWindow = ObjectUtil.checkPositiveOrZero(secondsPerWindow, "secondsPerWindow");
    return self();
  }
  
  protected B autoAckSettingsFrame(boolean autoAckSettings) {
    enforceNonCodecConstraints("autoAckSettingsFrame");
    this.autoAckSettingsFrame = autoAckSettings;
    return self();
  }
  
  protected boolean isAutoAckSettingsFrame() {
    return this.autoAckSettingsFrame;
  }
  
  protected B autoAckPingFrame(boolean autoAckPingFrame) {
    enforceNonCodecConstraints("autoAckPingFrame");
    this.autoAckPingFrame = autoAckPingFrame;
    return self();
  }
  
  protected boolean isAutoAckPingFrame() {
    return this.autoAckPingFrame;
  }
  
  protected B decoupleCloseAndGoAway(boolean decoupleCloseAndGoAway) {
    this.decoupleCloseAndGoAway = decoupleCloseAndGoAway;
    return self();
  }
  
  protected boolean decoupleCloseAndGoAway() {
    return this.decoupleCloseAndGoAway;
  }
  
  protected B flushPreface(boolean flushPreface) {
    this.flushPreface = flushPreface;
    return self();
  }
  
  protected boolean flushPreface() {
    return this.flushPreface;
  }
  
  protected T build() {
    if (this.encoder != null) {
      assert this.decoder != null;
      return buildFromCodec(this.decoder, this.encoder);
    } 
    Http2Connection connection = this.connection;
    if (connection == null)
      connection = new DefaultHttp2Connection(isServer(), maxReservedStreams()); 
    return buildFromConnection(connection);
  }
  
  private T buildFromConnection(Http2Connection connection) {
    Long maxHeaderListSize = this.initialSettings.maxHeaderListSize();
    Http2FrameReader reader = new DefaultHttp2FrameReader(new DefaultHttp2HeadersDecoder(isValidateHeaders(), (maxHeaderListSize == null) ? 8192L : maxHeaderListSize.longValue(), -1));
    Http2FrameWriter writer = (this.encoderIgnoreMaxHeaderListSize == null) ? new DefaultHttp2FrameWriter(headerSensitivityDetector()) : new DefaultHttp2FrameWriter(headerSensitivityDetector(), this.encoderIgnoreMaxHeaderListSize.booleanValue());
    if (this.frameLogger != null) {
      reader = new Http2InboundFrameLogger(reader, this.frameLogger);
      writer = new Http2OutboundFrameLogger(writer, this.frameLogger);
    } 
    Http2ConnectionEncoder encoder = new DefaultHttp2ConnectionEncoder(connection, writer);
    boolean encoderEnforceMaxConcurrentStreams = encoderEnforceMaxConcurrentStreams();
    if (this.maxQueuedControlFrames != 0)
      encoder = new Http2ControlFrameLimitEncoder(encoder, this.maxQueuedControlFrames); 
    if (encoderEnforceMaxConcurrentStreams) {
      if (connection.isServer()) {
        encoder.close();
        reader.close();
        throw new IllegalArgumentException("encoderEnforceMaxConcurrentStreams: " + encoderEnforceMaxConcurrentStreams + " not supported for server");
      } 
      encoder = new StreamBufferingEncoder(encoder);
    } 
    DefaultHttp2ConnectionDecoder decoder = new DefaultHttp2ConnectionDecoder(connection, encoder, reader, promisedRequestVerifier(), isAutoAckSettingsFrame(), isAutoAckPingFrame(), isValidateHeaders());
    return buildFromCodec(decoder, encoder);
  }
  
  private T buildFromCodec(Http2ConnectionDecoder decoder, Http2ConnectionEncoder encoder) {
    int maxRstFrames;
    T handler;
    int maxConsecutiveEmptyDataFrames = decoderEnforceMaxConsecutiveEmptyDataFrames();
    if (maxConsecutiveEmptyDataFrames > 0)
      decoder = new Http2EmptyDataFrameConnectionDecoder(decoder, maxConsecutiveEmptyDataFrames); 
    if (this.maxRstFramesPerWindow == null) {
      if (isServer()) {
        maxRstFrames = 200;
      } else {
        maxRstFrames = 0;
      } 
    } else {
      maxRstFrames = this.maxRstFramesPerWindow.intValue();
    } 
    if (maxRstFrames > 0 && this.secondsPerWindow > 0)
      decoder = new Http2MaxRstFrameDecoder(decoder, maxRstFrames, this.secondsPerWindow); 
    try {
      handler = build(decoder, encoder, this.initialSettings);
    } catch (Throwable t) {
      encoder.close();
      decoder.close();
      throw new IllegalStateException("failed to build an Http2ConnectionHandler", t);
    } 
    handler.gracefulShutdownTimeoutMillis(this.gracefulShutdownTimeoutMillis);
    if (handler.decoder().frameListener() == null)
      handler.decoder().frameListener(this.frameListener); 
    return handler;
  }
  
  protected abstract T build(Http2ConnectionDecoder paramHttp2ConnectionDecoder, Http2ConnectionEncoder paramHttp2ConnectionEncoder, Http2Settings paramHttp2Settings) throws Exception;
  
  protected final B self() {
    return (B)this;
  }
  
  private void enforceNonCodecConstraints(String rejected) {
    enforceConstraint(rejected, "server/connection", this.decoder);
    enforceConstraint(rejected, "server/connection", this.encoder);
  }
  
  private static void enforceConstraint(String methodName, String rejectorName, Object value) {
    if (value != null)
      throw new IllegalStateException(methodName + "() cannot be called because " + rejectorName + "() has been called already."); 
  }
}
