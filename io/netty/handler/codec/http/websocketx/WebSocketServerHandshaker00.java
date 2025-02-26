package io.netty.handler.codec.http.websocketx;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import java.util.regex.Pattern;

public class WebSocketServerHandshaker00 extends WebSocketServerHandshaker {
  private static final Pattern BEGINNING_DIGIT = Pattern.compile("[^0-9]");
  
  private static final Pattern BEGINNING_SPACE = Pattern.compile("[^ ]");
  
  public WebSocketServerHandshaker00(String webSocketURL, String subprotocols, int maxFramePayloadLength) {
    this(webSocketURL, subprotocols, WebSocketDecoderConfig.newBuilder()
        .maxFramePayloadLength(maxFramePayloadLength)
        .build());
  }
  
  public WebSocketServerHandshaker00(String webSocketURL, String subprotocols, WebSocketDecoderConfig decoderConfig) {
    super(WebSocketVersion.V00, webSocketURL, subprotocols, decoderConfig);
  }
  
  protected FullHttpResponse newHandshakeResponse(FullHttpRequest req, HttpHeaders headers) {
    HttpMethod method = req.method();
    if (!HttpMethod.GET.equals(method))
      throw new WebSocketServerHandshakeException("Invalid WebSocket handshake method: " + method, req); 
    if (!req.headers().containsValue((CharSequence)HttpHeaderNames.CONNECTION, (CharSequence)HttpHeaderValues.UPGRADE, true) || 
      !HttpHeaderValues.WEBSOCKET.contentEqualsIgnoreCase(req.headers().get((CharSequence)HttpHeaderNames.UPGRADE)))
      throw new WebSocketServerHandshakeException("not a WebSocket handshake request: missing upgrade", req); 
    boolean isHixie76 = (req.headers().contains((CharSequence)HttpHeaderNames.SEC_WEBSOCKET_KEY1) && req.headers().contains((CharSequence)HttpHeaderNames.SEC_WEBSOCKET_KEY2));
    String origin = req.headers().get((CharSequence)HttpHeaderNames.ORIGIN);
    if (origin == null && !isHixie76)
      throw new WebSocketServerHandshakeException("Missing origin header, got only " + req.headers().names(), req); 
    DefaultFullHttpResponse defaultFullHttpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, new HttpResponseStatus(101, isHixie76 ? "WebSocket Protocol Handshake" : "Web Socket Protocol Handshake"), req.content().alloc().buffer(0));
    if (headers != null)
      defaultFullHttpResponse.headers().add(headers); 
    defaultFullHttpResponse.headers().set((CharSequence)HttpHeaderNames.UPGRADE, HttpHeaderValues.WEBSOCKET)
      .set((CharSequence)HttpHeaderNames.CONNECTION, HttpHeaderValues.UPGRADE);
    if (isHixie76) {
      defaultFullHttpResponse.headers().add((CharSequence)HttpHeaderNames.SEC_WEBSOCKET_ORIGIN, origin);
      defaultFullHttpResponse.headers().add((CharSequence)HttpHeaderNames.SEC_WEBSOCKET_LOCATION, uri());
      String subprotocols = req.headers().get((CharSequence)HttpHeaderNames.SEC_WEBSOCKET_PROTOCOL);
      if (subprotocols != null) {
        String selectedSubprotocol = selectSubprotocol(subprotocols);
        if (selectedSubprotocol == null) {
          if (logger.isDebugEnabled())
            logger.debug("Requested subprotocol(s) not supported: {}", subprotocols); 
        } else {
          defaultFullHttpResponse.headers().set((CharSequence)HttpHeaderNames.SEC_WEBSOCKET_PROTOCOL, selectedSubprotocol);
        } 
      } 
      String key1 = req.headers().get((CharSequence)HttpHeaderNames.SEC_WEBSOCKET_KEY1);
      String key2 = req.headers().get((CharSequence)HttpHeaderNames.SEC_WEBSOCKET_KEY2);
      int a = (int)(Long.parseLong(BEGINNING_DIGIT.matcher(key1).replaceAll("")) / BEGINNING_SPACE.matcher(key1).replaceAll("").length());
      int b = (int)(Long.parseLong(BEGINNING_DIGIT.matcher(key2).replaceAll("")) / BEGINNING_SPACE.matcher(key2).replaceAll("").length());
      long c = req.content().readLong();
      ByteBuf input = Unpooled.wrappedBuffer(new byte[16]).setIndex(0, 0);
      input.writeInt(a);
      input.writeInt(b);
      input.writeLong(c);
      defaultFullHttpResponse.content().writeBytes(WebSocketUtil.md5(input.array()));
    } else {
      defaultFullHttpResponse.headers().add((CharSequence)HttpHeaderNames.WEBSOCKET_ORIGIN, origin);
      defaultFullHttpResponse.headers().add((CharSequence)HttpHeaderNames.WEBSOCKET_LOCATION, uri());
      String protocol = req.headers().get((CharSequence)HttpHeaderNames.WEBSOCKET_PROTOCOL);
      if (protocol != null)
        defaultFullHttpResponse.headers().set((CharSequence)HttpHeaderNames.WEBSOCKET_PROTOCOL, selectSubprotocol(protocol)); 
    } 
    return (FullHttpResponse)defaultFullHttpResponse;
  }
  
  public ChannelFuture close(Channel channel, CloseWebSocketFrame frame, ChannelPromise promise) {
    return channel.writeAndFlush(frame, promise);
  }
  
  public ChannelFuture close(ChannelHandlerContext ctx, CloseWebSocketFrame frame, ChannelPromise promise) {
    return ctx.writeAndFlush(frame, promise);
  }
  
  protected WebSocketFrameDecoder newWebsocketDecoder() {
    return new WebSocket00FrameDecoder(decoderConfig());
  }
  
  protected WebSocketFrameEncoder newWebSocketEncoder() {
    return new WebSocket00FrameEncoder();
  }
}
