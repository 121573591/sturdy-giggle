package tech.ordinaryroad.live.chat.client.douyin.netty.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketClientProtocolHandler;
import io.netty.util.concurrent.GenericFutureListener;
import java.util.function.Supplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.ordinaryroad.live.chat.client.commons.base.listener.IBaseConnectionListener;
import tech.ordinaryroad.live.chat.client.douyin.client.DouyinLiveChatClient;
import tech.ordinaryroad.live.chat.client.douyin.config.DouyinLiveChatClientConfig;
import tech.ordinaryroad.live.chat.client.servers.netty.client.base.BaseNettyClient;
import tech.ordinaryroad.live.chat.client.servers.netty.client.handler.BaseNettyClientConnectionHandler;

@Sharable
public class DouyinConnectionHandler extends BaseNettyClientConnectionHandler<DouyinLiveChatClient, DouyinConnectionHandler> {
  private static final Logger log = LoggerFactory.getLogger(DouyinConnectionHandler.class);
  
  private static final byte[] HEARTBEAT_BYTES = new byte[] { 58, 2, 104, 98 };
  
  private final Object roomId;
  
  private String cookie;
  
  public DouyinConnectionHandler(Supplier<WebSocketClientProtocolHandler> webSocketProtocolHandler, DouyinLiveChatClient client, IBaseConnectionListener<DouyinConnectionHandler> listener) {
    super(webSocketProtocolHandler, (BaseNettyClient)client, listener);
    this.roomId = ((DouyinLiveChatClientConfig)client.getConfig()).getRoomId();
    this.cookie = ((DouyinLiveChatClientConfig)client.getConfig()).getCookie();
  }
  
  public DouyinConnectionHandler(Supplier<WebSocketClientProtocolHandler> webSocketProtocolHandler, DouyinLiveChatClient client) {
    this(webSocketProtocolHandler, client, (IBaseConnectionListener<DouyinConnectionHandler>)null);
  }
  
  public DouyinConnectionHandler(Supplier<WebSocketClientProtocolHandler> webSocketProtocolHandler, long roomId, IBaseConnectionListener<DouyinConnectionHandler> listener, String cookie) {
    super(webSocketProtocolHandler, listener);
    this.roomId = Long.valueOf(roomId);
    this.cookie = cookie;
  }
  
  public DouyinConnectionHandler(Supplier<WebSocketClientProtocolHandler> webSocketProtocolHandler, long roomId, IBaseConnectionListener<DouyinConnectionHandler> listener) {
    this(webSocketProtocolHandler, roomId, listener, null);
  }
  
  public DouyinConnectionHandler(Supplier<WebSocketClientProtocolHandler> webSocketProtocolHandler, long roomId, String cookie) {
    this(webSocketProtocolHandler, roomId, null, cookie);
  }
  
  public DouyinConnectionHandler(Supplier<WebSocketClientProtocolHandler> webSocketProtocolHandler, long roomId) {
    this(webSocketProtocolHandler, roomId, null, null);
  }
  
  public void sendHeartbeat(Channel channel) {
    if (log.isDebugEnabled())
      log.debug("发送心跳包"); 
    channel.writeAndFlush(new BinaryWebSocketFrame(channel.alloc().buffer().writeBytes(HEARTBEAT_BYTES)))
      .addListener((GenericFutureListener)(future -> {
          if (future.isSuccess()) {
            if (log.isDebugEnabled())
              log.debug("心跳包发送完成"); 
          } else {
            log.error("心跳包发送失败", future.cause());
          } 
        }));
  }
  
  public Object getRoomId() {
    return (this.client != null) ? ((DouyinLiveChatClientConfig)((DouyinLiveChatClient)this.client).getConfig()).getRoomId() : this.roomId;
  }
  
  private String getCookie() {
    return (this.client != null) ? ((DouyinLiveChatClientConfig)((DouyinLiveChatClient)this.client).getConfig()).getCookie() : this.cookie;
  }
}
