package tech.ordinaryroad.live.chat.client.kuaishou.client;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.databind.JsonNode;
import io.netty.channel.ChannelHandler;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.websocketx.WebSocketClientProtocolConfig;
import io.netty.handler.codec.http.websocketx.WebSocketClientProtocolHandler;
import io.netty.handler.codec.http.websocketx.WebSocketVersion;
import java.beans.PropertyChangeEvent;
import java.util.List;
import java.util.function.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.ordinaryroad.live.chat.client.codec.kuaishou.api.KuaishouApis;
import tech.ordinaryroad.live.chat.client.codec.kuaishou.msg.base.IKuaishouMsg;
import tech.ordinaryroad.live.chat.client.codec.kuaishou.protobuf.PayloadTypeOuterClass;
import tech.ordinaryroad.live.chat.client.commons.base.exception.BaseException;
import tech.ordinaryroad.live.chat.client.commons.base.listener.IBaseConnectionListener;
import tech.ordinaryroad.live.chat.client.commons.base.listener.IBaseMsgListener;
import tech.ordinaryroad.live.chat.client.commons.client.enums.ClientStatusEnums;
import tech.ordinaryroad.live.chat.client.commons.util.OrLiveChatCollUtil;
import tech.ordinaryroad.live.chat.client.kuaishou.config.KuaishouLiveChatClientConfig;
import tech.ordinaryroad.live.chat.client.kuaishou.listener.IKuaishouConnectionListener;
import tech.ordinaryroad.live.chat.client.kuaishou.listener.IKuaishouMsgListener;
import tech.ordinaryroad.live.chat.client.kuaishou.listener.impl.KuaishouForwardMsgListener;
import tech.ordinaryroad.live.chat.client.kuaishou.netty.handler.KuaishouBinaryFrameHandler;
import tech.ordinaryroad.live.chat.client.kuaishou.netty.handler.KuaishouConnectionHandler;
import tech.ordinaryroad.live.chat.client.kuaishou.netty.handler.KuaishouLiveChatClientChannelInitializer;
import tech.ordinaryroad.live.chat.client.servers.netty.client.base.BaseNettyClient;
import tech.ordinaryroad.live.chat.client.servers.netty.client.config.BaseNettyClientConfig;
import tech.ordinaryroad.live.chat.client.servers.netty.handler.base.BaseConnectionHandler;

public class KuaishouLiveChatClient extends BaseNettyClient<KuaishouLiveChatClientConfig, PayloadTypeOuterClass.PayloadType, IKuaishouMsg, IKuaishouMsgListener, KuaishouConnectionHandler, KuaishouBinaryFrameHandler> {
  private static final Logger log = LoggerFactory.getLogger(KuaishouLiveChatClient.class);
  
  KuaishouApis.RoomInitResult roomInitResult = new KuaishouApis.RoomInitResult();
  
  public KuaishouApis.RoomInitResult getRoomInitResult() {
    return this.roomInitResult;
  }
  
  public KuaishouLiveChatClient(KuaishouLiveChatClientConfig config, List<IKuaishouMsgListener> msgListeners, IKuaishouConnectionListener connectionListener, EventLoopGroup workerGroup) {
    super((BaseNettyClientConfig)config, workerGroup, (IBaseConnectionListener)connectionListener);
    addMsgListeners(msgListeners);
    init();
  }
  
  public KuaishouLiveChatClient(KuaishouLiveChatClientConfig config, IKuaishouMsgListener msgListener, IKuaishouConnectionListener connectionListener, EventLoopGroup workerGroup) {
    super((BaseNettyClientConfig)config, workerGroup, (IBaseConnectionListener)connectionListener);
    addMsgListener((IBaseMsgListener)msgListener);
    init();
  }
  
  public KuaishouLiveChatClient(KuaishouLiveChatClientConfig config, IKuaishouMsgListener msgListener, IKuaishouConnectionListener connectionListener) {
    this(config, msgListener, connectionListener, (EventLoopGroup)new NioEventLoopGroup());
  }
  
  public KuaishouLiveChatClient(KuaishouLiveChatClientConfig config, IKuaishouMsgListener msgListener) {
    this(config, msgListener, (IKuaishouConnectionListener)null, (EventLoopGroup)new NioEventLoopGroup());
  }
  
  public KuaishouLiveChatClient(KuaishouLiveChatClientConfig config) {
    this(config, (IKuaishouMsgListener)null);
  }
  
  public void init() {
    if (StrUtil.isNotBlank(((KuaishouLiveChatClientConfig)getConfig()).getForwardWebsocketUri())) {
      KuaishouForwardMsgListener forwardMsgListener = new KuaishouForwardMsgListener(((KuaishouLiveChatClientConfig)getConfig()).getForwardWebsocketUri());
      addMsgListener((IBaseMsgListener)forwardMsgListener);
      addStatusChangeListener((evt, oldStatus, newStatus) -> {
            if (newStatus == ClientStatusEnums.DESTROYED)
              forwardMsgListener.destroyForwardClient(); 
          });
    } 
    super.init();
  }
  
  protected String getWebSocketUriString() {
    String webSocketUriString = super.getWebSocketUriString();
    if (StrUtil.isNotBlank(webSocketUriString))
      return webSocketUriString; 
    return (String)OrLiveChatCollUtil.getRandom(this.roomInitResult.getWebsocketUrls());
  }
  
  public KuaishouConnectionHandler initConnectionHandler(IBaseConnectionListener<KuaishouConnectionHandler> clientConnectionListener) {
    return new KuaishouConnectionHandler(() -> new WebSocketClientProtocolHandler(WebSocketClientProtocolConfig.newBuilder().webSocketUri(getWebsocketUri()).version(WebSocketVersion.V13).subprotocol(null).allowExtensions(true).customHeaders((HttpHeaders)new DefaultHttpHeaders()).maxFramePayloadLength(((KuaishouLiveChatClientConfig)getConfig()).getMaxFramePayloadLength()).handshakeTimeoutMillis(((KuaishouLiveChatClientConfig)getConfig()).getHandshakeTimeoutMillis()).build()), this, clientConnectionListener);
  }
  
  protected void initChannel(SocketChannel channel) {
    channel.pipeline().addLast(new ChannelHandler[] { (ChannelHandler)new KuaishouLiveChatClientChannelInitializer(this) });
  }
  
  public void connect(Runnable success, Consumer<Throwable> failed) {
    this.roomInitResult = KuaishouApis.roomInit(((KuaishouLiveChatClientConfig)getConfig()).getRoomId(), ((KuaishouLiveChatClientConfig)getConfig()).getRoomInfoGetType(), ((KuaishouLiveChatClientConfig)getConfig()).getCookie(), this.roomInitResult);
    super.connect(success, failed);
  }
  
  public void sendDanmu(Object danmu, Runnable success, Consumer<Throwable> failed) {
    if (!checkCanSendDanmu())
      return; 
    if (danmu instanceof String) {
      String msg = (String)danmu;
      try {
        if (log.isDebugEnabled())
          log.debug("{} kuaishou发送弹幕 {}", ((KuaishouLiveChatClientConfig)getConfig()).getRoomId(), danmu); 
        boolean sendSuccess = false;
        try {
          KuaishouApis.sendComment(((KuaishouLiveChatClientConfig)getConfig()).getCookie(), ((KuaishouLiveChatClientConfig)
              getConfig()).getRoomId(), 
              KuaishouApis.SendCommentRequest.builder()
              .liveStreamId(this.roomInitResult.getLiveStreamId())
              .content(msg)
              .build());
          sendSuccess = true;
        } catch (Exception e) {
          log.error("kuaishou弹幕发送失败", e);
          if (failed != null)
            failed.accept(e); 
        } 
        if (!sendSuccess)
          return; 
        if (log.isDebugEnabled())
          log.debug("kuaishou弹幕发送成功 {}", danmu); 
        if (success != null)
          success.run(); 
        finishSendDanmu();
      } catch (Exception e) {
        log.error("kuaishou弹幕发送失败", e);
        if (failed != null)
          failed.accept(e); 
      } 
    } else {
      super.sendDanmu(danmu, success, failed);
    } 
  }
  
  public void clickLike(int count, Runnable success, Consumer<Throwable> failed) {
    if (count <= 0)
      throw new BaseException("点赞次数必须大于0"); 
    boolean successfullyClicked = false;
    try {
      JsonNode jsonNode = KuaishouApis.clickLike(((KuaishouLiveChatClientConfig)getConfig()).getCookie(), ((KuaishouLiveChatClientConfig)getConfig()).getRoomId(), this.roomInitResult.getLiveStreamId(), count);
      if (jsonNode.asBoolean())
        successfullyClicked = true; 
    } catch (Exception e) {
      log.error("kuaishou为直播间点赞失败", e);
      if (failed != null)
        failed.accept(e); 
    } 
    if (!successfullyClicked)
      return; 
    if (log.isDebugEnabled())
      log.debug("kuaishou为直播间点赞成功"); 
    if (success != null)
      success.run(); 
  }
}
