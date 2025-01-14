package tech.ordinaryroad.live.chat.client.servers.netty.handler.base;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.http.websocketx.WebSocketClientProtocolHandler;
import io.netty.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.ordinaryroad.live.chat.client.commons.base.exception.BaseException;
import tech.ordinaryroad.live.chat.client.commons.base.listener.IBaseConnectionListener;

public abstract class BaseConnectionHandler<ConnectionHandler extends BaseConnectionHandler<?>> extends ChannelInboundHandlerAdapter implements IBaseConnectionHandler {
  private static final Logger log = LoggerFactory.getLogger(BaseConnectionHandler.class);
  
  private final Supplier<WebSocketClientProtocolHandler> webSocketProtocolHandler;
  
  private ChannelPromise handshakePromise;
  
  private final IBaseConnectionListener<ConnectionHandler> listener;
  
  public Supplier<WebSocketClientProtocolHandler> getWebSocketProtocolHandler() {
    return this.webSocketProtocolHandler;
  }
  
  public ChannelPromise getHandshakePromise() {
    return this.handshakePromise;
  }
  
  private ScheduledFuture<?> scheduledFuture = null;
  
  public BaseConnectionHandler(Supplier<WebSocketClientProtocolHandler> webSocketProtocolHandler, IBaseConnectionListener<ConnectionHandler> listener) {
    this.webSocketProtocolHandler = webSocketProtocolHandler;
    this.listener = listener;
  }
  
  public BaseConnectionHandler(Supplier<WebSocketClientProtocolHandler> webSocketProtocolHandler) {
    this(webSocketProtocolHandler, null);
  }
  
  public void handlerAdded(ChannelHandlerContext ctx) {
    this.handshakePromise = ctx.newPromise();
  }
  
  public void channelActive(ChannelHandlerContext ctx) {
    if (log.isDebugEnabled())
      log.debug("channelActive"); 
  }
  
  public void channelInactive(ChannelHandlerContext ctx) {
    if (log.isDebugEnabled())
      log.debug("channelInactive"); 
    heartbeatCancel();
    if (this.listener != null)
      this.listener.onDisconnected(this); 
  }
  
  private void heartbeatStart(Channel channel) {
    if (getHeartbeatPeriod() > 0L) {
      this.scheduledFuture = channel.eventLoop().scheduleAtFixedRate(() -> sendHeartbeat(channel), 
          
          getHeartbeatInitialDelay(), getHeartbeatPeriod(), TimeUnit.SECONDS);
    } else {
      this.scheduledFuture = channel.eventLoop().schedule(() -> sendHeartbeat(channel), 
          
          getHeartbeatInitialDelay(), TimeUnit.SECONDS);
    } 
  }
  
  private void heartbeatCancel() {
    if (null != this.scheduledFuture && !this.scheduledFuture.isCancelled()) {
      this.scheduledFuture.cancel(true);
      this.scheduledFuture = null;
    } 
  }
  
  private void handshakeSuccessfully(Channel channel) {
    if (log.isDebugEnabled())
      log.debug("握手完成!"); 
    this.handshakePromise.setSuccess();
    heartbeatCancel();
    heartbeatStart(channel);
    if (this.listener != null)
      this.listener.onConnected(this); 
  }
  
  private void handshakeFailed(ChannelHandlerContext ctx, WebSocketClientProtocolHandler.ClientHandshakeStateEvent evt) {
    log.error("握手失败！ {}", evt);
    this.handshakePromise.setFailure((Throwable)new BaseException(evt.name()));
    if (this.listener != null)
      this.listener.onConnectFailed(this); 
  }
  
  public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
    if (log.isDebugEnabled())
      log.debug("userEventTriggered {}", evt); 
    if (evt == WebSocketClientProtocolHandler.ClientHandshakeStateEvent.HANDSHAKE_COMPLETE) {
      handshakeSuccessfully(ctx.channel());
    } else if (evt == WebSocketClientProtocolHandler.ClientHandshakeStateEvent.HANDSHAKE_TIMEOUT) {
      handshakeFailed(ctx, (WebSocketClientProtocolHandler.ClientHandshakeStateEvent)evt);
    } else {
      super.userEventTriggered(ctx, evt);
    } 
  }
  
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    log.error("exceptionCaught", cause);
    if (!this.handshakePromise.isDone())
      this.handshakePromise.setFailure(cause); 
    ctx.close();
  }
  
  public abstract long getHeartbeatPeriod();
  
  public abstract long getHeartbeatInitialDelay();
}
