package tech.ordinaryroad.live.chat.client.servers.netty.client.base;

import cn.hutool.core.util.StrUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.proxy.Socks5ProxyHandler;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.SslHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.ordinaryroad.live.chat.client.commons.base.exception.BaseException;
import tech.ordinaryroad.live.chat.client.commons.base.listener.IBaseConnectionListener;
import tech.ordinaryroad.live.chat.client.commons.base.listener.IBaseMsgListener;
import tech.ordinaryroad.live.chat.client.commons.base.msg.IMsg;
import tech.ordinaryroad.live.chat.client.commons.client.BaseLiveChatClient;
import tech.ordinaryroad.live.chat.client.commons.client.config.BaseLiveChatClientConfig;
import tech.ordinaryroad.live.chat.client.commons.client.enums.ClientStatusEnums;
import tech.ordinaryroad.live.chat.client.commons.util.OrLiveChatUrlUtil;
import tech.ordinaryroad.live.chat.client.servers.netty.client.config.BaseNettyClientConfig;
import tech.ordinaryroad.live.chat.client.servers.netty.handler.base.BaseBinaryFrameHandler;
import tech.ordinaryroad.live.chat.client.servers.netty.handler.base.BaseConnectionHandler;

public abstract class BaseNettyClient<Config extends BaseNettyClientConfig, CmdEnum extends Enum<CmdEnum>, Msg extends IMsg, MsgListener extends IBaseMsgListener<BinaryFrameHandler, CmdEnum>, ConnectionHandler extends BaseConnectionHandler<ConnectionHandler>, BinaryFrameHandler extends BaseBinaryFrameHandler<BinaryFrameHandler, CmdEnum, Msg, MsgListener>> extends BaseLiveChatClient<Config, MsgListener> implements IBaseConnectionListener<ConnectionHandler> {
  private static final Logger log = LoggerFactory.getLogger(BaseNettyClient.class);
  
  private final EventLoopGroup workerGroup;
  
  public EventLoopGroup getWorkerGroup() {
    return this.workerGroup;
  }
  
  private final Bootstrap bootstrap = new Bootstrap();
  
  private ConnectionHandler connectionHandler;
  
  private IBaseConnectionListener<ConnectionHandler> connectionListener;
  
  private Channel channel;
  
  private URI websocketUri;
  
  public Bootstrap getBootstrap() {
    return this.bootstrap;
  }
  
  public URI getWebsocketUri() {
    return this.websocketUri;
  }
  
  protected IBaseConnectionListener<ConnectionHandler> clientConnectionListener = this;
  
  private volatile long lastSendDanmuTimeInMillis;
  
  protected void initChannel(SocketChannel channel) {}
  
  protected BaseNettyClient(Config config, EventLoopGroup workerGroup, IBaseConnectionListener<ConnectionHandler> connectionListener) {
    super((BaseLiveChatClientConfig)config);
    this.workerGroup = workerGroup;
    this.connectionListener = connectionListener;
  }
  
  public void onConnected(ConnectionHandler connectionHandler) {
    setStatus(ClientStatusEnums.CONNECTED);
    if (this.connectionListener != null)
      this.connectionListener.onConnected(connectionHandler); 
  }
  
  public void onConnectFailed(ConnectionHandler connectionHandler) {
    setStatus(ClientStatusEnums.CONNECT_FAILED);
    if (this.connectionListener != null)
      this.connectionListener.onConnectFailed(connectionHandler); 
  }
  
  public void onDisconnected(ConnectionHandler connectionHandler) {
    setStatus(ClientStatusEnums.DISCONNECTED);
    tryReconnect();
    if (this.connectionListener != null)
      this.connectionListener.onDisconnected(connectionHandler); 
  }
  
  public void init() {
    if (checkStatus(ClientStatusEnums.INITIALIZED))
      return; 
    this.connectionHandler = initConnectionHandler(this);
    ((Bootstrap)((Bootstrap)((Bootstrap)this.bootstrap.group(this.workerGroup))
      
      .channel(NioSocketChannel.class))
      .option(ChannelOption.TCP_NODELAY, Boolean.valueOf(true)))
      .option(ChannelOption.SO_KEEPALIVE, Boolean.valueOf(true));
    setStatus(ClientStatusEnums.INITIALIZED);
  }
  
  public void connect(Runnable success, Consumer<Throwable> failed) {
    if (this.cancelReconnect)
      this.cancelReconnect = false; 
    if (!checkStatus(ClientStatusEnums.INITIALIZED))
      return; 
    if (getStatus() == ClientStatusEnums.CONNECTED)
      return; 
    if (getStatus() != ClientStatusEnums.RECONNECTING)
      setStatus(ClientStatusEnums.CONNECTING); 
    String webSocketUriString = getWebSocketUriString();
    final int port = OrLiveChatUrlUtil.getWebSocketUriPort(webSocketUriString);
    try {
      this.websocketUri = new URI(webSocketUriString);
    } catch (URISyntaxException e) {
      log.error("WebSocket地址解析失败 " + webSocketUriString, e);
      failed.accept(e);
      return;
    } 
    ((Bootstrap)this.bootstrap.handler((ChannelHandler)new ChannelInitializer<SocketChannel>() {
          protected void initChannel(SocketChannel ch) throws Exception {
            ChannelPipeline pipeline = ch.pipeline();
            String socks5ProxyHost = ((BaseNettyClientConfig)BaseNettyClient.this.getConfig()).getSocks5ProxyHost();
            if (StrUtil.isNotBlank(socks5ProxyHost)) {
              int socks5ProxyPort = ((BaseNettyClientConfig)BaseNettyClient.this.getConfig()).getSocks5ProxyPort();
              pipeline.addFirst(new ChannelHandler[] { (ChannelHandler)new Socks5ProxyHandler(new InetSocketAddress(socks5ProxyHost, socks5ProxyPort), ((BaseNettyClientConfig)this.this$0.getConfig()).getSocks5ProxyUsername(), ((BaseNettyClientConfig)this.this$0.getConfig()).getSocks5ProxyPassword()) });
              if (BaseNettyClient.log.isDebugEnabled())
                BaseNettyClient.log.debug("已启用Socks5代理"); 
            } 
            if (pipeline.get(SslHandler.class) != null)
              pipeline.remove(SslHandler.class); 
            if ("wss".equalsIgnoreCase(OrLiveChatUrlUtil.getScheme(BaseNettyClient.this.getWebSocketUriString())))
              pipeline.addLast(new ChannelHandler[] { (ChannelHandler)SslContextBuilder.forClient()
                    .build()
                    .newHandler(ch.alloc(), BaseNettyClient.access$100(this.this$0).getHost(), this.val$port) }); 
            pipeline.addLast(new ChannelHandler[] { (ChannelHandler)new HttpClientCodec() });
            pipeline.addLast(new ChannelHandler[] { (ChannelHandler)new ChunkedWriteHandler() });
            pipeline.addLast(new ChannelHandler[] { (ChannelHandler)new HttpObjectAggregator(((BaseNettyClientConfig)this.this$0.getConfig()).getAggregatorMaxContentLength()) });
            pipeline.addLast(new ChannelHandler[] { BaseNettyClient.access$200(this.this$0).getWebSocketProtocolHandler().get() });
            pipeline.addLast(new ChannelHandler[] { (ChannelHandler)BaseNettyClient.access$200(this.this$0) });
            BaseNettyClient.this.initChannel(ch);
          }
        })).connect(this.websocketUri.getHost(), port).addListener((GenericFutureListener)(connectFuture -> {
          if (connectFuture.isSuccess()) {
            if (log.isDebugEnabled())
              log.debug("连接建立成功！"); 
            this.channel = connectFuture.channel();
            this.connectionHandler.getHandshakePromise().addListener((GenericFutureListener)(()));
          } else {
            log.error("连接建立失败", connectFuture.cause());
            onConnectFailed(this.connectionHandler);
            if (failed != null)
              failed.accept(connectFuture.cause()); 
          } 
        }));
  }
  
  public void disconnect() {
    if (this.channel == null)
      return; 
    this.channel.close();
  }
  
  protected void tryReconnect() {
    if (this.cancelReconnect) {
      this.cancelReconnect = false;
      return;
    } 
    if (!((BaseNettyClientConfig)getConfig()).isAutoReconnect())
      return; 
    if (log.isWarnEnabled()) {
      Object roomId = ((BaseNettyClientConfig)getConfig()).getRoomId();
      log.warn("{}s后将重新连接 {}", Integer.valueOf(((BaseNettyClientConfig)getConfig()).getReconnectDelay()), (roomId == null) ? ((BaseNettyClientConfig)getConfig()).getWebsocketUri() : roomId);
    } 
    this.workerGroup.schedule(() -> {
          setStatus(ClientStatusEnums.RECONNECTING);
          connect();
        }((BaseNettyClientConfig)
        
        getConfig()).getReconnectDelay(), TimeUnit.SECONDS);
  }
  
  public void send(Object msg, Runnable success, Consumer<Throwable> failed) {
    if (getStatus() != ClientStatusEnums.CONNECTED)
      return; 
    ChannelFuture future = this.channel.writeAndFlush(msg);
    if (success != null || failed != null)
      future.addListener((GenericFutureListener)(channelFuture -> {
            if (channelFuture.isSuccess()) {
              if (success != null)
                channelFuture.channel().eventLoop().execute(success); 
            } else if (failed != null) {
              channelFuture.channel().eventLoop().execute(());
            } 
          })); 
  }
  
  public void destroy() {
    this.cancelReconnect = true;
    this.workerGroup.shutdownGracefully().addListener(future -> {
          if (future.isSuccess()) {
            setStatus(ClientStatusEnums.DESTROYED);
            super.destroy();
          } else {
            throw new BaseException("client销毁失败", future.cause());
          } 
        });
  }
  
  protected String getWebSocketUriString() {
    return ((BaseNettyClientConfig)getConfig()).getWebsocketUri();
  }
  
  protected void setStatus(ClientStatusEnums status) {
    if (log.isDebugEnabled() && 
      getStatus() != status)
      log.debug("{} 状态变化 {} => {}\n", new Object[] { getClass().getSimpleName(), getStatus(), status }); 
    super.setStatus(status);
  }
  
  public void sendDanmu(Object danmu, Runnable success, Consumer<Throwable> failed) {
    throw new BaseException("暂未支持该功能");
  }
  
  public void clickLike(int count, Runnable success, Consumer<Throwable> failed) {
    throw new BaseException("暂未支持该功能");
  }
  
  protected boolean checkCanSendDanmu(boolean checkConnected) {
    if (checkConnected && getStatus() != ClientStatusEnums.CONNECTED)
      throw new BaseException("连接未建立，无法发送弹幕"); 
    if (System.currentTimeMillis() - this.lastSendDanmuTimeInMillis <= ((BaseNettyClientConfig)getConfig()).getMinSendDanmuPeriod()) {
      if (log.isWarnEnabled())
        log.warn("发送弹幕频率过快，忽略该次发送"); 
      return false;
    } 
    return true;
  }
  
  protected boolean checkCanSendDanmu() {
    return checkCanSendDanmu(true);
  }
  
  protected void finishSendDanmu() {
    this.lastSendDanmuTimeInMillis = System.currentTimeMillis();
    if (log.isDebugEnabled())
      log.debug("弹幕发送完成"); 
  }
  
  public abstract ConnectionHandler initConnectionHandler(IBaseConnectionListener<ConnectionHandler> paramIBaseConnectionListener);
}
