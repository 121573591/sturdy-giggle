package tech.ordinaryroad.live.chat.client.websocket.netty.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.handler.codec.http.websocketx.WebSocketClientProtocolHandler;
import java.util.function.Supplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.ordinaryroad.live.chat.client.commons.base.listener.IBaseConnectionListener;
import tech.ordinaryroad.live.chat.client.servers.netty.client.base.BaseNettyClient;
import tech.ordinaryroad.live.chat.client.servers.netty.client.handler.BaseNettyClientConnectionHandler;
import tech.ordinaryroad.live.chat.client.servers.netty.handler.base.IBaseConnectionHandler;
import tech.ordinaryroad.live.chat.client.websocket.client.WebSocketLiveChatClient;

@Sharable
public class WebSocketConnectionHandler extends BaseNettyClientConnectionHandler<WebSocketLiveChatClient, WebSocketConnectionHandler> {
  private static final Logger log = LoggerFactory.getLogger(WebSocketConnectionHandler.class);
  
  private final IBaseConnectionHandler connectionHandler;
  
  public WebSocketConnectionHandler(Supplier<WebSocketClientProtocolHandler> webSocketProtocolHandler, IBaseConnectionHandler connectionHandler, WebSocketLiveChatClient client, IBaseConnectionListener<WebSocketConnectionHandler> listener) {
    super(webSocketProtocolHandler, (BaseNettyClient)client, listener);
    this.connectionHandler = connectionHandler;
  }
  
  public WebSocketConnectionHandler(Supplier<WebSocketClientProtocolHandler> webSocketProtocolHandler, IBaseConnectionHandler connectionHandler, WebSocketLiveChatClient client) {
    this(webSocketProtocolHandler, connectionHandler, client, null);
  }
  
  public WebSocketConnectionHandler(Supplier<WebSocketClientProtocolHandler> webSocketProtocolHandler, IBaseConnectionHandler connectionHandler) {
    this(webSocketProtocolHandler, connectionHandler, null);
  }
  
  public WebSocketConnectionHandler(Supplier<WebSocketClientProtocolHandler> webSocketProtocolHandler) {
    this(webSocketProtocolHandler, null);
  }
  
  public void sendHeartbeat(Channel channel) {
    if (this.connectionHandler == null)
      return; 
    this.connectionHandler.sendHeartbeat(channel);
  }
  
  public void sendAuthRequest(Channel channel) {
    if (this.connectionHandler == null)
      return; 
    this.connectionHandler.sendAuthRequest(channel);
  }
}
