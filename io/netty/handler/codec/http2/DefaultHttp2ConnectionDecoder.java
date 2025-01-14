package io.netty.handler.codec.http2;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpStatusClass;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DefaultHttp2ConnectionDecoder implements Http2ConnectionDecoder {
  private static final InternalLogger logger = InternalLoggerFactory.getInstance(DefaultHttp2ConnectionDecoder.class);
  
  private Http2FrameListener internalFrameListener = new PrefaceFrameListener();
  
  private final Http2Connection connection;
  
  private Http2LifecycleManager lifecycleManager;
  
  private final Http2ConnectionEncoder encoder;
  
  private final Http2FrameReader frameReader;
  
  private Http2FrameListener listener;
  
  private final Http2PromisedRequestVerifier requestVerifier;
  
  private final Http2SettingsReceivedConsumer settingsReceivedConsumer;
  
  private final boolean autoAckPing;
  
  private final Http2Connection.PropertyKey contentLengthKey;
  
  private final boolean validateHeaders;
  
  public DefaultHttp2ConnectionDecoder(Http2Connection connection, Http2ConnectionEncoder encoder, Http2FrameReader frameReader) {
    this(connection, encoder, frameReader, Http2PromisedRequestVerifier.ALWAYS_VERIFY);
  }
  
  public DefaultHttp2ConnectionDecoder(Http2Connection connection, Http2ConnectionEncoder encoder, Http2FrameReader frameReader, Http2PromisedRequestVerifier requestVerifier) {
    this(connection, encoder, frameReader, requestVerifier, true);
  }
  
  public DefaultHttp2ConnectionDecoder(Http2Connection connection, Http2ConnectionEncoder encoder, Http2FrameReader frameReader, Http2PromisedRequestVerifier requestVerifier, boolean autoAckSettings) {
    this(connection, encoder, frameReader, requestVerifier, autoAckSettings, true);
  }
  
  @Deprecated
  public DefaultHttp2ConnectionDecoder(Http2Connection connection, Http2ConnectionEncoder encoder, Http2FrameReader frameReader, Http2PromisedRequestVerifier requestVerifier, boolean autoAckSettings, boolean autoAckPing) {
    this(connection, encoder, frameReader, requestVerifier, autoAckSettings, true, true);
  }
  
  public DefaultHttp2ConnectionDecoder(Http2Connection connection, Http2ConnectionEncoder encoder, Http2FrameReader frameReader, Http2PromisedRequestVerifier requestVerifier, boolean autoAckSettings, boolean autoAckPing, boolean validateHeaders) {
    this.validateHeaders = validateHeaders;
    this.autoAckPing = autoAckPing;
    if (autoAckSettings) {
      this.settingsReceivedConsumer = null;
    } else {
      if (!(encoder instanceof Http2SettingsReceivedConsumer))
        throw new IllegalArgumentException("disabling autoAckSettings requires the encoder to be a " + Http2SettingsReceivedConsumer.class); 
      this.settingsReceivedConsumer = (Http2SettingsReceivedConsumer)encoder;
    } 
    this.connection = (Http2Connection)ObjectUtil.checkNotNull(connection, "connection");
    this.contentLengthKey = this.connection.newKey();
    this.frameReader = (Http2FrameReader)ObjectUtil.checkNotNull(frameReader, "frameReader");
    this.encoder = (Http2ConnectionEncoder)ObjectUtil.checkNotNull(encoder, "encoder");
    this.requestVerifier = (Http2PromisedRequestVerifier)ObjectUtil.checkNotNull(requestVerifier, "requestVerifier");
    if (connection.local().flowController() == null)
      connection.local().flowController(new DefaultHttp2LocalFlowController(connection)); 
    ((Http2LocalFlowController)connection.local().flowController()).frameWriter(encoder.frameWriter());
  }
  
  public void lifecycleManager(Http2LifecycleManager lifecycleManager) {
    this.lifecycleManager = (Http2LifecycleManager)ObjectUtil.checkNotNull(lifecycleManager, "lifecycleManager");
  }
  
  public Http2Connection connection() {
    return this.connection;
  }
  
  public final Http2LocalFlowController flowController() {
    return this.connection.local().flowController();
  }
  
  public void frameListener(Http2FrameListener listener) {
    this.listener = (Http2FrameListener)ObjectUtil.checkNotNull(listener, "listener");
  }
  
  public Http2FrameListener frameListener() {
    return this.listener;
  }
  
  public boolean prefaceReceived() {
    return (FrameReadListener.class == this.internalFrameListener.getClass());
  }
  
  public void decodeFrame(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Http2Exception {
    this.frameReader.readFrame(ctx, in, this.internalFrameListener);
  }
  
  public Http2Settings localSettings() {
    Http2Settings settings = new Http2Settings();
    Http2FrameReader.Configuration config = this.frameReader.configuration();
    Http2HeadersDecoder.Configuration headersConfig = config.headersConfiguration();
    Http2FrameSizePolicy frameSizePolicy = config.frameSizePolicy();
    settings.initialWindowSize(flowController().initialWindowSize());
    settings.maxConcurrentStreams(this.connection.remote().maxActiveStreams());
    settings.headerTableSize(headersConfig.maxHeaderTableSize());
    settings.maxFrameSize(frameSizePolicy.maxFrameSize());
    settings.maxHeaderListSize(headersConfig.maxHeaderListSize());
    if (!this.connection.isServer())
      settings.pushEnabled(this.connection.local().allowPushTo()); 
    return settings;
  }
  
  public void close() {
    this.frameReader.close();
  }
  
  protected long calculateMaxHeaderListSizeGoAway(long maxHeaderListSize) {
    return Http2CodecUtil.calculateMaxHeaderListSizeGoAway(maxHeaderListSize);
  }
  
  private int unconsumedBytes(Http2Stream stream) {
    return flowController().unconsumedBytes(stream);
  }
  
  void onGoAwayRead0(ChannelHandlerContext ctx, int lastStreamId, long errorCode, ByteBuf debugData) throws Http2Exception {
    this.listener.onGoAwayRead(ctx, lastStreamId, errorCode, debugData);
    this.connection.goAwayReceived(lastStreamId, errorCode, debugData);
  }
  
  void onUnknownFrame0(ChannelHandlerContext ctx, byte frameType, int streamId, Http2Flags flags, ByteBuf payload) throws Http2Exception {
    this.listener.onUnknownFrame(ctx, frameType, streamId, flags, payload);
  }
  
  private void verifyContentLength(Http2Stream stream, int data, boolean isEnd) throws Http2Exception {
    ContentLength contentLength = stream.<ContentLength>getProperty(this.contentLengthKey);
    if (contentLength != null)
      try {
        contentLength.increaseReceivedBytes(this.connection.isServer(), stream.id(), data, isEnd);
      } finally {
        if (isEnd)
          stream.removeProperty(this.contentLengthKey); 
      }  
  }
  
  private final class FrameReadListener implements Http2FrameListener {
    private FrameReadListener() {}
    
    public int onDataRead(ChannelHandlerContext ctx, int streamId, ByteBuf data, int padding, boolean endOfStream) throws Http2Exception {
      boolean shouldIgnore;
      Http2Stream stream = DefaultHttp2ConnectionDecoder.this.connection.stream(streamId);
      Http2LocalFlowController flowController = DefaultHttp2ConnectionDecoder.this.flowController();
      int readable = data.readableBytes();
      int bytesToReturn = readable + padding;
      try {
        shouldIgnore = shouldIgnoreHeadersOrDataFrame(ctx, streamId, stream, endOfStream, "DATA");
      } catch (Http2Exception e) {
        flowController.receiveFlowControlledFrame(stream, data, padding, endOfStream);
        flowController.consumeBytes(stream, bytesToReturn);
        throw e;
      } catch (Throwable t) {
        throw Http2Exception.connectionError(Http2Error.INTERNAL_ERROR, t, "Unhandled error on data stream id %d", new Object[] { Integer.valueOf(streamId) });
      } 
      if (shouldIgnore) {
        flowController.receiveFlowControlledFrame(stream, data, padding, endOfStream);
        flowController.consumeBytes(stream, bytesToReturn);
        verifyStreamMayHaveExisted(streamId, endOfStream, "DATA");
        return bytesToReturn;
      } 
      Http2Exception error = null;
      switch (stream.state()) {
        case OPEN:
        case HALF_CLOSED_LOCAL:
          break;
        case HALF_CLOSED_REMOTE:
        case CLOSED:
          error = Http2Exception.streamError(stream.id(), Http2Error.STREAM_CLOSED, "Stream %d in unexpected state: %s", new Object[] { Integer.valueOf(stream.id()), stream.state() });
          break;
        default:
          error = Http2Exception.streamError(stream.id(), Http2Error.PROTOCOL_ERROR, "Stream %d in unexpected state: %s", new Object[] { Integer.valueOf(stream.id()), stream.state() });
          break;
      } 
      int unconsumedBytes = DefaultHttp2ConnectionDecoder.this.unconsumedBytes(stream);
      try {
        flowController.receiveFlowControlledFrame(stream, data, padding, endOfStream);
        unconsumedBytes = DefaultHttp2ConnectionDecoder.this.unconsumedBytes(stream);
        if (error != null)
          throw error; 
        DefaultHttp2ConnectionDecoder.this.verifyContentLength(stream, readable, endOfStream);
        bytesToReturn = DefaultHttp2ConnectionDecoder.this.listener.onDataRead(ctx, streamId, data, padding, endOfStream);
        if (endOfStream)
          DefaultHttp2ConnectionDecoder.this.lifecycleManager.closeStreamRemote(stream, ctx.newSucceededFuture()); 
        return bytesToReturn;
      } catch (Http2Exception e) {
        int delta = unconsumedBytes - DefaultHttp2ConnectionDecoder.this.unconsumedBytes(stream);
        bytesToReturn -= delta;
        throw e;
      } catch (RuntimeException e) {
        int delta = unconsumedBytes - DefaultHttp2ConnectionDecoder.this.unconsumedBytes(stream);
        bytesToReturn -= delta;
        throw e;
      } finally {
        flowController.consumeBytes(stream, bytesToReturn);
      } 
    }
    
    public void onHeadersRead(ChannelHandlerContext ctx, int streamId, Http2Headers headers, int padding, boolean endOfStream) throws Http2Exception {
      onHeadersRead(ctx, streamId, headers, 0, (short)16, false, padding, endOfStream);
    }
    
    public void onHeadersRead(ChannelHandlerContext ctx, int streamId, Http2Headers headers, int streamDependency, short weight, boolean exclusive, int padding, boolean endOfStream) throws Http2Exception {
      Http2Stream stream = DefaultHttp2ConnectionDecoder.this.connection.stream(streamId);
      boolean allowHalfClosedRemote = false;
      boolean isTrailers = false;
      if (stream == null && !DefaultHttp2ConnectionDecoder.this.connection.streamMayHaveExisted(streamId)) {
        stream = DefaultHttp2ConnectionDecoder.this.connection.remote().createStream(streamId, endOfStream);
        allowHalfClosedRemote = (stream.state() == Http2Stream.State.HALF_CLOSED_REMOTE);
      } else if (stream != null) {
        isTrailers = stream.isHeadersReceived();
      } 
      if (shouldIgnoreHeadersOrDataFrame(ctx, streamId, stream, endOfStream, "HEADERS"))
        return; 
      boolean isInformational = (!DefaultHttp2ConnectionDecoder.this.connection.isServer() && HttpStatusClass.valueOf(headers.status()) == HttpStatusClass.INFORMATIONAL);
      if (((isInformational || !endOfStream) && stream.isHeadersReceived()) || stream.isTrailersReceived())
        throw Http2Exception.streamError(streamId, Http2Error.PROTOCOL_ERROR, "Stream %d received too many headers EOS: %s state: %s", new Object[] { Integer.valueOf(streamId), Boolean.valueOf(endOfStream), stream.state() }); 
      switch (stream.state()) {
        case RESERVED_REMOTE:
          stream.open(endOfStream);
          break;
        case OPEN:
        case HALF_CLOSED_LOCAL:
          break;
        case HALF_CLOSED_REMOTE:
          if (!allowHalfClosedRemote)
            throw Http2Exception.streamError(stream.id(), Http2Error.STREAM_CLOSED, "Stream %d in unexpected state: %s", new Object[] { Integer.valueOf(stream.id()), stream.state() }); 
          break;
        case CLOSED:
          throw Http2Exception.streamError(stream.id(), Http2Error.STREAM_CLOSED, "Stream %d in unexpected state: %s", new Object[] { Integer.valueOf(stream.id()), stream.state() });
        default:
          throw Http2Exception.connectionError(Http2Error.PROTOCOL_ERROR, "Stream %d in unexpected state: %s", new Object[] { Integer.valueOf(stream.id()), stream
                .state() });
      } 
      if (!isTrailers) {
        List<? extends CharSequence> contentLength = headers.getAll(HttpHeaderNames.CONTENT_LENGTH);
        if (contentLength != null && !contentLength.isEmpty())
          try {
            long cLength = HttpUtil.normalizeAndGetContentLength(contentLength, false, true);
            if (cLength != -1L) {
              headers.setLong(HttpHeaderNames.CONTENT_LENGTH, cLength);
              stream.setProperty(DefaultHttp2ConnectionDecoder.this.contentLengthKey, new DefaultHttp2ConnectionDecoder.ContentLength(cLength));
            } 
          } catch (IllegalArgumentException e) {
            throw Http2Exception.streamError(stream.id(), Http2Error.PROTOCOL_ERROR, e, "Multiple content-length headers received", new Object[0]);
          }  
      } else if (DefaultHttp2ConnectionDecoder.this.validateHeaders && headers.size() > 0) {
        for (Iterator<Map.Entry<CharSequence, CharSequence>> iterator = headers.iterator(); iterator.hasNext(); ) {
          CharSequence name = (CharSequence)((Map.Entry)iterator.next()).getKey();
          if (Http2Headers.PseudoHeaderName.hasPseudoHeaderFormat(name))
            throw Http2Exception.streamError(stream.id(), Http2Error.PROTOCOL_ERROR, "Found invalid Pseudo-Header in trailers: %s", new Object[] { name }); 
        } 
      } 
      stream.headersReceived(isInformational);
      DefaultHttp2ConnectionDecoder.this.verifyContentLength(stream, 0, endOfStream);
      DefaultHttp2ConnectionDecoder.this.encoder.flowController().updateDependencyTree(streamId, streamDependency, weight, exclusive);
      DefaultHttp2ConnectionDecoder.this.listener.onHeadersRead(ctx, streamId, headers, streamDependency, weight, exclusive, padding, endOfStream);
      if (endOfStream)
        DefaultHttp2ConnectionDecoder.this.lifecycleManager.closeStreamRemote(stream, ctx.newSucceededFuture()); 
    }
    
    public void onPriorityRead(ChannelHandlerContext ctx, int streamId, int streamDependency, short weight, boolean exclusive) throws Http2Exception {
      DefaultHttp2ConnectionDecoder.this.encoder.flowController().updateDependencyTree(streamId, streamDependency, weight, exclusive);
      DefaultHttp2ConnectionDecoder.this.listener.onPriorityRead(ctx, streamId, streamDependency, weight, exclusive);
    }
    
    public void onRstStreamRead(ChannelHandlerContext ctx, int streamId, long errorCode) throws Http2Exception {
      Http2Stream stream = DefaultHttp2ConnectionDecoder.this.connection.stream(streamId);
      if (stream == null) {
        verifyStreamMayHaveExisted(streamId, false, "RST_STREAM");
        return;
      } 
      switch (stream.state()) {
        case IDLE:
          throw Http2Exception.connectionError(Http2Error.PROTOCOL_ERROR, "RST_STREAM received for IDLE stream %d", new Object[] { Integer.valueOf(streamId) });
        case CLOSED:
          return;
      } 
      DefaultHttp2ConnectionDecoder.this.listener.onRstStreamRead(ctx, streamId, errorCode);
      DefaultHttp2ConnectionDecoder.this.lifecycleManager.closeStream(stream, ctx.newSucceededFuture());
    }
    
    public void onSettingsAckRead(ChannelHandlerContext ctx) throws Http2Exception {
      Http2Settings settings = DefaultHttp2ConnectionDecoder.this.encoder.pollSentSettings();
      if (settings != null)
        applyLocalSettings(settings); 
      DefaultHttp2ConnectionDecoder.this.listener.onSettingsAckRead(ctx);
    }
    
    private void applyLocalSettings(Http2Settings settings) throws Http2Exception {
      Boolean pushEnabled = settings.pushEnabled();
      Http2FrameReader.Configuration config = DefaultHttp2ConnectionDecoder.this.frameReader.configuration();
      Http2HeadersDecoder.Configuration headerConfig = config.headersConfiguration();
      Http2FrameSizePolicy frameSizePolicy = config.frameSizePolicy();
      if (pushEnabled != null) {
        if (DefaultHttp2ConnectionDecoder.this.connection.isServer())
          throw Http2Exception.connectionError(Http2Error.PROTOCOL_ERROR, "Server sending SETTINGS frame with ENABLE_PUSH specified", new Object[0]); 
        DefaultHttp2ConnectionDecoder.this.connection.local().allowPushTo(pushEnabled.booleanValue());
      } 
      Long maxConcurrentStreams = settings.maxConcurrentStreams();
      if (maxConcurrentStreams != null)
        DefaultHttp2ConnectionDecoder.this.connection.remote().maxActiveStreams((int)Math.min(maxConcurrentStreams.longValue(), 2147483647L)); 
      Long headerTableSize = settings.headerTableSize();
      if (headerTableSize != null)
        headerConfig.maxHeaderTableSize(headerTableSize.longValue()); 
      Long maxHeaderListSize = settings.maxHeaderListSize();
      if (maxHeaderListSize != null)
        headerConfig.maxHeaderListSize(maxHeaderListSize.longValue(), DefaultHttp2ConnectionDecoder.this.calculateMaxHeaderListSizeGoAway(maxHeaderListSize.longValue())); 
      Integer maxFrameSize = settings.maxFrameSize();
      if (maxFrameSize != null)
        frameSizePolicy.maxFrameSize(maxFrameSize.intValue()); 
      Integer initialWindowSize = settings.initialWindowSize();
      if (initialWindowSize != null)
        DefaultHttp2ConnectionDecoder.this.flowController().initialWindowSize(initialWindowSize.intValue()); 
    }
    
    public void onSettingsRead(ChannelHandlerContext ctx, Http2Settings settings) throws Http2Exception {
      if (DefaultHttp2ConnectionDecoder.this.settingsReceivedConsumer == null) {
        DefaultHttp2ConnectionDecoder.this.encoder.writeSettingsAck(ctx, ctx.newPromise());
        DefaultHttp2ConnectionDecoder.this.encoder.remoteSettings(settings);
      } else {
        DefaultHttp2ConnectionDecoder.this.settingsReceivedConsumer.consumeReceivedSettings(settings);
      } 
      DefaultHttp2ConnectionDecoder.this.listener.onSettingsRead(ctx, settings);
    }
    
    public void onPingRead(ChannelHandlerContext ctx, long data) throws Http2Exception {
      if (DefaultHttp2ConnectionDecoder.this.autoAckPing)
        DefaultHttp2ConnectionDecoder.this.encoder.writePing(ctx, true, data, ctx.newPromise()); 
      DefaultHttp2ConnectionDecoder.this.listener.onPingRead(ctx, data);
    }
    
    public void onPingAckRead(ChannelHandlerContext ctx, long data) throws Http2Exception {
      DefaultHttp2ConnectionDecoder.this.listener.onPingAckRead(ctx, data);
    }
    
    public void onPushPromiseRead(ChannelHandlerContext ctx, int streamId, int promisedStreamId, Http2Headers headers, int padding) throws Http2Exception {
      if (DefaultHttp2ConnectionDecoder.this.connection().isServer())
        throw Http2Exception.connectionError(Http2Error.PROTOCOL_ERROR, "A client cannot push.", new Object[0]); 
      Http2Stream parentStream = DefaultHttp2ConnectionDecoder.this.connection.stream(streamId);
      if (shouldIgnoreHeadersOrDataFrame(ctx, streamId, parentStream, false, "PUSH_PROMISE"))
        return; 
      switch (parentStream.state()) {
        case OPEN:
        case HALF_CLOSED_LOCAL:
          break;
        default:
          throw Http2Exception.connectionError(Http2Error.PROTOCOL_ERROR, "Stream %d in unexpected state for receiving push promise: %s", new Object[] { Integer.valueOf(parentStream.id()), parentStream.state() });
      } 
      if (!DefaultHttp2ConnectionDecoder.this.requestVerifier.isAuthoritative(ctx, headers))
        throw Http2Exception.streamError(promisedStreamId, Http2Error.PROTOCOL_ERROR, "Promised request on stream %d for promised stream %d is not authoritative", new Object[] { Integer.valueOf(streamId), Integer.valueOf(promisedStreamId) }); 
      if (!DefaultHttp2ConnectionDecoder.this.requestVerifier.isCacheable(headers))
        throw Http2Exception.streamError(promisedStreamId, Http2Error.PROTOCOL_ERROR, "Promised request on stream %d for promised stream %d is not known to be cacheable", new Object[] { Integer.valueOf(streamId), Integer.valueOf(promisedStreamId) }); 
      if (!DefaultHttp2ConnectionDecoder.this.requestVerifier.isSafe(headers))
        throw Http2Exception.streamError(promisedStreamId, Http2Error.PROTOCOL_ERROR, "Promised request on stream %d for promised stream %d is not known to be safe", new Object[] { Integer.valueOf(streamId), Integer.valueOf(promisedStreamId) }); 
      DefaultHttp2ConnectionDecoder.this.connection.remote().reservePushStream(promisedStreamId, parentStream);
      DefaultHttp2ConnectionDecoder.this.listener.onPushPromiseRead(ctx, streamId, promisedStreamId, headers, padding);
    }
    
    public void onGoAwayRead(ChannelHandlerContext ctx, int lastStreamId, long errorCode, ByteBuf debugData) throws Http2Exception {
      DefaultHttp2ConnectionDecoder.this.onGoAwayRead0(ctx, lastStreamId, errorCode, debugData);
    }
    
    public void onWindowUpdateRead(ChannelHandlerContext ctx, int streamId, int windowSizeIncrement) throws Http2Exception {
      Http2Stream stream = DefaultHttp2ConnectionDecoder.this.connection.stream(streamId);
      if (stream == null || stream.state() == Http2Stream.State.CLOSED || streamCreatedAfterGoAwaySent(streamId)) {
        verifyStreamMayHaveExisted(streamId, false, "WINDOW_UPDATE");
        return;
      } 
      DefaultHttp2ConnectionDecoder.this.encoder.flowController().incrementWindowSize(stream, windowSizeIncrement);
      DefaultHttp2ConnectionDecoder.this.listener.onWindowUpdateRead(ctx, streamId, windowSizeIncrement);
    }
    
    public void onUnknownFrame(ChannelHandlerContext ctx, byte frameType, int streamId, Http2Flags flags, ByteBuf payload) throws Http2Exception {
      DefaultHttp2ConnectionDecoder.this.onUnknownFrame0(ctx, frameType, streamId, flags, payload);
    }
    
    private boolean shouldIgnoreHeadersOrDataFrame(ChannelHandlerContext ctx, int streamId, Http2Stream stream, boolean endOfStream, String frameName) throws Http2Exception {
      if (stream == null) {
        if (streamCreatedAfterGoAwaySent(streamId)) {
          DefaultHttp2ConnectionDecoder.logger.info("{} ignoring {} frame for stream {}. Stream sent after GOAWAY sent", new Object[] { ctx
                .channel(), frameName, Integer.valueOf(streamId) });
          return true;
        } 
        verifyStreamMayHaveExisted(streamId, endOfStream, frameName);
        throw Http2Exception.streamError(streamId, Http2Error.STREAM_CLOSED, "Received %s frame for an unknown stream %d", new Object[] { frameName, 
              Integer.valueOf(streamId) });
      } 
      if (stream.isResetSent() || streamCreatedAfterGoAwaySent(streamId)) {
        if (DefaultHttp2ConnectionDecoder.logger.isInfoEnabled())
          DefaultHttp2ConnectionDecoder.logger.info("{} ignoring {} frame for stream {}", new Object[] { ctx.channel(), frameName, 
                stream.isResetSent() ? "RST_STREAM sent." : ("Stream created after GOAWAY sent. Last known stream by peer " + 
                
                DefaultHttp2ConnectionDecoder.access$100(this.this$0).remote().lastStreamKnownByPeer()) }); 
        return true;
      } 
      return false;
    }
    
    private boolean streamCreatedAfterGoAwaySent(int streamId) {
      Http2Connection.Endpoint<?> remote = DefaultHttp2ConnectionDecoder.this.connection.remote();
      return (DefaultHttp2ConnectionDecoder.this.connection.goAwaySent() && remote.isValidStreamId(streamId) && streamId > remote
        .lastStreamKnownByPeer());
    }
    
    private void verifyStreamMayHaveExisted(int streamId, boolean endOfStream, String frameName) throws Http2Exception {
      if (!DefaultHttp2ConnectionDecoder.this.connection.streamMayHaveExisted(streamId))
        throw Http2Exception.connectionError(Http2Error.PROTOCOL_ERROR, "Stream %d does not exist for inbound frame %s, endOfStream = %b", new Object[] { Integer.valueOf(streamId), frameName, Boolean.valueOf(endOfStream) }); 
    }
  }
  
  private final class PrefaceFrameListener implements Http2FrameListener {
    private PrefaceFrameListener() {}
    
    private void verifyPrefaceReceived() throws Http2Exception {
      if (!DefaultHttp2ConnectionDecoder.this.prefaceReceived())
        throw Http2Exception.connectionError(Http2Error.PROTOCOL_ERROR, "Received non-SETTINGS as first frame.", new Object[0]); 
    }
    
    public int onDataRead(ChannelHandlerContext ctx, int streamId, ByteBuf data, int padding, boolean endOfStream) throws Http2Exception {
      verifyPrefaceReceived();
      return DefaultHttp2ConnectionDecoder.this.internalFrameListener.onDataRead(ctx, streamId, data, padding, endOfStream);
    }
    
    public void onHeadersRead(ChannelHandlerContext ctx, int streamId, Http2Headers headers, int padding, boolean endOfStream) throws Http2Exception {
      verifyPrefaceReceived();
      DefaultHttp2ConnectionDecoder.this.internalFrameListener.onHeadersRead(ctx, streamId, headers, padding, endOfStream);
    }
    
    public void onHeadersRead(ChannelHandlerContext ctx, int streamId, Http2Headers headers, int streamDependency, short weight, boolean exclusive, int padding, boolean endOfStream) throws Http2Exception {
      verifyPrefaceReceived();
      DefaultHttp2ConnectionDecoder.this.internalFrameListener.onHeadersRead(ctx, streamId, headers, streamDependency, weight, exclusive, padding, endOfStream);
    }
    
    public void onPriorityRead(ChannelHandlerContext ctx, int streamId, int streamDependency, short weight, boolean exclusive) throws Http2Exception {
      verifyPrefaceReceived();
      DefaultHttp2ConnectionDecoder.this.internalFrameListener.onPriorityRead(ctx, streamId, streamDependency, weight, exclusive);
    }
    
    public void onRstStreamRead(ChannelHandlerContext ctx, int streamId, long errorCode) throws Http2Exception {
      verifyPrefaceReceived();
      DefaultHttp2ConnectionDecoder.this.internalFrameListener.onRstStreamRead(ctx, streamId, errorCode);
    }
    
    public void onSettingsAckRead(ChannelHandlerContext ctx) throws Http2Exception {
      verifyPrefaceReceived();
      DefaultHttp2ConnectionDecoder.this.internalFrameListener.onSettingsAckRead(ctx);
    }
    
    public void onSettingsRead(ChannelHandlerContext ctx, Http2Settings settings) throws Http2Exception {
      if (!DefaultHttp2ConnectionDecoder.this.prefaceReceived())
        DefaultHttp2ConnectionDecoder.this.internalFrameListener = new DefaultHttp2ConnectionDecoder.FrameReadListener(); 
      DefaultHttp2ConnectionDecoder.this.internalFrameListener.onSettingsRead(ctx, settings);
    }
    
    public void onPingRead(ChannelHandlerContext ctx, long data) throws Http2Exception {
      verifyPrefaceReceived();
      DefaultHttp2ConnectionDecoder.this.internalFrameListener.onPingRead(ctx, data);
    }
    
    public void onPingAckRead(ChannelHandlerContext ctx, long data) throws Http2Exception {
      verifyPrefaceReceived();
      DefaultHttp2ConnectionDecoder.this.internalFrameListener.onPingAckRead(ctx, data);
    }
    
    public void onPushPromiseRead(ChannelHandlerContext ctx, int streamId, int promisedStreamId, Http2Headers headers, int padding) throws Http2Exception {
      verifyPrefaceReceived();
      DefaultHttp2ConnectionDecoder.this.internalFrameListener.onPushPromiseRead(ctx, streamId, promisedStreamId, headers, padding);
    }
    
    public void onGoAwayRead(ChannelHandlerContext ctx, int lastStreamId, long errorCode, ByteBuf debugData) throws Http2Exception {
      DefaultHttp2ConnectionDecoder.this.onGoAwayRead0(ctx, lastStreamId, errorCode, debugData);
    }
    
    public void onWindowUpdateRead(ChannelHandlerContext ctx, int streamId, int windowSizeIncrement) throws Http2Exception {
      verifyPrefaceReceived();
      DefaultHttp2ConnectionDecoder.this.internalFrameListener.onWindowUpdateRead(ctx, streamId, windowSizeIncrement);
    }
    
    public void onUnknownFrame(ChannelHandlerContext ctx, byte frameType, int streamId, Http2Flags flags, ByteBuf payload) throws Http2Exception {
      DefaultHttp2ConnectionDecoder.this.onUnknownFrame0(ctx, frameType, streamId, flags, payload);
    }
  }
  
  private static final class ContentLength {
    private final long expected;
    
    private long seen;
    
    ContentLength(long expected) {
      this.expected = expected;
    }
    
    void increaseReceivedBytes(boolean server, int streamId, int bytes, boolean isEnd) throws Http2Exception {
      this.seen += bytes;
      if (this.seen < 0L)
        throw Http2Exception.streamError(streamId, Http2Error.PROTOCOL_ERROR, "Received amount of data did overflow and so not match content-length header %d", new Object[] { Long.valueOf(this.expected) }); 
      if (this.seen > this.expected)
        throw Http2Exception.streamError(streamId, Http2Error.PROTOCOL_ERROR, "Received amount of data %d does not match content-length header %d", new Object[] { Long.valueOf(this.seen), Long.valueOf(this.expected) }); 
      if (isEnd) {
        if (this.seen == 0L && !server)
          return; 
        if (this.expected > this.seen)
          throw Http2Exception.streamError(streamId, Http2Error.PROTOCOL_ERROR, "Received amount of data %d does not match content-length header %d", new Object[] { Long.valueOf(this.seen), Long.valueOf(this.expected) }); 
      } 
    }
  }
}
