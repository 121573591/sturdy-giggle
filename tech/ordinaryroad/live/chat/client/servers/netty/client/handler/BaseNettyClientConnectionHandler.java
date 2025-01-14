package tech.ordinaryroad.live.chat.client.servers.netty.client.handler;

import io.netty.handler.codec.http.websocketx.WebSocketClientProtocolHandler;
import java.util.function.Supplier;
import tech.ordinaryroad.live.chat.client.commons.base.listener.IBaseConnectionListener;
import tech.ordinaryroad.live.chat.client.servers.netty.client.base.BaseNettyClient;
import tech.ordinaryroad.live.chat.client.servers.netty.client.config.BaseNettyClientConfig;
import tech.ordinaryroad.live.chat.client.servers.netty.handler.base.BaseConnectionHandler;

public abstract class BaseNettyClientConnectionHandler<Client extends BaseNettyClient<?, ?, ?, ?, ?, ?>, ConnectionHandler extends BaseConnectionHandler<ConnectionHandler>> extends BaseConnectionHandler<ConnectionHandler> {
  protected final Client client;
  
  public Client getClient() {
    return this.client;
  }
  
  public BaseNettyClientConnectionHandler(Supplier<WebSocketClientProtocolHandler> webSocketClientProtocolHandler, Client client, IBaseConnectionListener<ConnectionHandler> listener) {
    super(webSocketClientProtocolHandler, listener);
    this.client = client;
  }
  
  public BaseNettyClientConnectionHandler(Supplier<WebSocketClientProtocolHandler> webSocketClientProtocolHandler, Client client) {
    this(webSocketClientProtocolHandler, client, null);
  }
  
  public BaseNettyClientConnectionHandler(Supplier<WebSocketClientProtocolHandler> webSocketClientProtocolHandler, IBaseConnectionListener<ConnectionHandler> listener) {
    super(webSocketClientProtocolHandler, listener);
    this.client = null;
  }
  
  public BaseNettyClientConnectionHandler(Supplier<WebSocketClientProtocolHandler> webSocketClientProtocolHandler, long roomId) {
    super(webSocketClientProtocolHandler, null);
    this.client = null;
  }
  
  public long getHeartbeatPeriod() {
    if (this.client == null)
      return 25L; 
    return ((BaseNettyClientConfig)this.client.getConfig()).getHeartbeatPeriod();
  }
  
  public long getHeartbeatInitialDelay() {
    if (this.client == null)
      return 15L; 
    return ((BaseNettyClientConfig)this.client.getConfig()).getHeartbeatInitialDelay();
  }
}
