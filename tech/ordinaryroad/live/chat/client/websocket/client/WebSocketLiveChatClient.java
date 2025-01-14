package tech.ordinaryroad.live.chat.client.websocket.client;

import cn.hutool.core.util.StrUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelHandler;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketClientProtocolConfig;
import io.netty.handler.codec.http.websocketx.WebSocketClientProtocolHandler;
import io.netty.handler.codec.http.websocketx.WebSocketVersion;
import java.nio.charset.StandardCharsets;
import java.util.function.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.ordinaryroad.live.chat.client.commons.base.exception.BaseException;
import tech.ordinaryroad.live.chat.client.commons.base.listener.IBaseConnectionListener;
import tech.ordinaryroad.live.chat.client.commons.base.listener.IBaseMsgListener;
import tech.ordinaryroad.live.chat.client.commons.base.msg.IMsg;
import tech.ordinaryroad.live.chat.client.servers.netty.client.base.BaseNettyClient;
import tech.ordinaryroad.live.chat.client.servers.netty.client.config.BaseNettyClientConfig;
import tech.ordinaryroad.live.chat.client.servers.netty.handler.base.BaseConnectionHandler;
import tech.ordinaryroad.live.chat.client.servers.netty.handler.base.IBaseConnectionHandler;
import tech.ordinaryroad.live.chat.client.websocket.config.WebSocketLiveChatClientConfig;
import tech.ordinaryroad.live.chat.client.websocket.constant.WebSocketCmdEnum;
import tech.ordinaryroad.live.chat.client.websocket.listener.IWebSocketConnectionListener;
import tech.ordinaryroad.live.chat.client.websocket.listener.IWebSocketMsgListener;
import tech.ordinaryroad.live.chat.client.websocket.msg.base.IWebSocketMsg;
import tech.ordinaryroad.live.chat.client.websocket.netty.handler.WebSocketBinaryFrameHandler;
import tech.ordinaryroad.live.chat.client.websocket.netty.handler.WebSocketChannelInitializer;
import tech.ordinaryroad.live.chat.client.websocket.netty.handler.WebSocketConnectionHandler;

public class WebSocketLiveChatClient extends BaseNettyClient<WebSocketLiveChatClientConfig, WebSocketCmdEnum, IWebSocketMsg, IWebSocketMsgListener, WebSocketConnectionHandler, WebSocketBinaryFrameHandler> {
  private static final Logger log = LoggerFactory.getLogger(WebSocketLiveChatClient.class);
  
  private WebSocketLiveChatClient forwardClient;
  
  private final IBaseConnectionHandler connectionHandler;
  
  public WebSocketLiveChatClient(WebSocketLiveChatClientConfig config, IBaseConnectionHandler connectionHandler, IWebSocketMsgListener msgListener, IWebSocketConnectionListener connectionListener, EventLoopGroup workerGroup) {
    super((BaseNettyClientConfig)config, workerGroup, (IBaseConnectionListener)connectionListener);
    addMsgListener((IBaseMsgListener)msgListener);
    this.connectionHandler = connectionHandler;
    init();
  }
  
  public WebSocketLiveChatClient(WebSocketLiveChatClientConfig config, IBaseConnectionHandler connectionHandler, IWebSocketMsgListener msgListener, IWebSocketConnectionListener connectionListener) {
    this(config, connectionHandler, msgListener, connectionListener, (EventLoopGroup)new NioEventLoopGroup());
  }
  
  public WebSocketLiveChatClient(WebSocketLiveChatClientConfig config, IBaseConnectionHandler connectionHandler, IWebSocketMsgListener msgListener) {
    this(config, connectionHandler, msgListener, (IWebSocketConnectionListener)null, (EventLoopGroup)new NioEventLoopGroup());
  }
  
  public WebSocketLiveChatClient(WebSocketLiveChatClientConfig config, IBaseConnectionHandler connectionHandler) {
    this(config, connectionHandler, (IWebSocketMsgListener)null);
  }
  
  public WebSocketLiveChatClient(WebSocketLiveChatClientConfig config) {
    this(config, (IBaseConnectionHandler)null, (IWebSocketMsgListener)null);
  }
  
  public WebSocketConnectionHandler initConnectionHandler(IBaseConnectionListener<WebSocketConnectionHandler> clientConnectionListener) {
    return new WebSocketConnectionHandler(() -> new WebSocketClientProtocolHandler(WebSocketClientProtocolConfig.newBuilder().webSocketUri(getWebsocketUri()).version(WebSocketVersion.V13).subprotocol(null).allowExtensions(true).customHeaders((HttpHeaders)new DefaultHttpHeaders()).maxFramePayloadLength(((WebSocketLiveChatClientConfig)getConfig()).getMaxFramePayloadLength()).handshakeTimeoutMillis(((WebSocketLiveChatClientConfig)getConfig()).getHandshakeTimeoutMillis()).build()), this.connectionHandler, this, clientConnectionListener);
  }
  
  protected void initChannel(SocketChannel channel) {
    channel.pipeline().addLast(new ChannelHandler[] { (ChannelHandler)new WebSocketChannelInitializer(this) });
  }
  
  public void init() {
    if (StrUtil.isNotBlank(((WebSocketLiveChatClientConfig)getConfig()).getForwardWebsocketUri())) {
      this
        
        .forwardClient = new WebSocketLiveChatClient(((WebSocketLiveChatClientConfig.WebSocketLiveChatClientConfigBuilder)WebSocketLiveChatClientConfig.builder().websocketUri(((WebSocketLiveChatClientConfig)getConfig()).getForwardWebsocketUri())).build());
      this.forwardClient.connect();
      addMsgListener((IBaseMsgListener)new IWebSocketMsgListener() {
            public void onMsg(IMsg msg) {
              ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer();
              buffer.writeCharSequence(msg.toString(), StandardCharsets.UTF_8);
              WebSocketLiveChatClient.this.forwardClient.send(new BinaryWebSocketFrame(buffer));
            }
          });
    } 
    super.init();
  }
  
  public void send(Object msg, Runnable success, Consumer<Throwable> failed) {
    if (!(msg instanceof BinaryWebSocketFrame))
      throw new BaseException("WebSocketLiveChatClient.send 仅支持 BinaryWebSocketFrame类型的消息"); 
    super.send(msg, success, failed);
  }
  
  public void destroy() {
    if (this.forwardClient != null)
      this.forwardClient.destroy(); 
    super.destroy();
  }
}
