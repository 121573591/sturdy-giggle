package tech.ordinaryroad.live.chat.client.tiktok.client;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.Header;
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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.ordinaryroad.live.chat.client.codec.tiktok.api.TiktokApis;
import tech.ordinaryroad.live.chat.client.codec.tiktok.constant.TiktokCmdEnum;
import tech.ordinaryroad.live.chat.client.codec.tiktok.msg.base.ITiktokMsg;
import tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.Response;
import tech.ordinaryroad.live.chat.client.commons.base.listener.IBaseConnectionListener;
import tech.ordinaryroad.live.chat.client.commons.base.listener.IBaseMsgListener;
import tech.ordinaryroad.live.chat.client.commons.client.enums.ClientStatusEnums;
import tech.ordinaryroad.live.chat.client.commons.util.OrLiveChatHttpUtil;
import tech.ordinaryroad.live.chat.client.servers.netty.client.base.BaseNettyClient;
import tech.ordinaryroad.live.chat.client.servers.netty.client.config.BaseNettyClientConfig;
import tech.ordinaryroad.live.chat.client.servers.netty.handler.base.BaseConnectionHandler;
import tech.ordinaryroad.live.chat.client.tiktok.config.TiktokLiveChatClientConfig;
import tech.ordinaryroad.live.chat.client.tiktok.listener.ITiktokConnectionListener;
import tech.ordinaryroad.live.chat.client.tiktok.listener.ITiktokMsgListener;
import tech.ordinaryroad.live.chat.client.tiktok.listener.impl.TiktokForwardMsgListener;
import tech.ordinaryroad.live.chat.client.tiktok.netty.handler.TiktokBinaryFrameHandler;
import tech.ordinaryroad.live.chat.client.tiktok.netty.handler.TiktokConnectionHandler;
import tech.ordinaryroad.live.chat.client.tiktok.netty.handler.TiktokLiveChatClientChannelInitializer;

public class TiktokLiveChatClient extends BaseNettyClient<TiktokLiveChatClientConfig, TiktokCmdEnum, ITiktokMsg, ITiktokMsgListener, TiktokConnectionHandler, TiktokBinaryFrameHandler> {
  private static final Logger log = LoggerFactory.getLogger(TiktokLiveChatClient.class);
  
  private TiktokApis.RoomInitResult roomInitResult = new TiktokApis.RoomInitResult();
  
  public TiktokApis.RoomInitResult getRoomInitResult() {
    return this.roomInitResult;
  }
  
  public TiktokLiveChatClient(TiktokLiveChatClientConfig config, List<ITiktokMsgListener> msgListeners, ITiktokConnectionListener connectionListener, EventLoopGroup workerGroup) {
    super((BaseNettyClientConfig)config, workerGroup, (IBaseConnectionListener)connectionListener);
    addMsgListeners(msgListeners);
    init();
  }
  
  public TiktokLiveChatClient(TiktokLiveChatClientConfig config, ITiktokMsgListener msgListener, ITiktokConnectionListener connectionListener, EventLoopGroup workerGroup) {
    super((BaseNettyClientConfig)config, workerGroup, (IBaseConnectionListener)connectionListener);
    addMsgListener((IBaseMsgListener)msgListener);
    init();
  }
  
  public TiktokLiveChatClient(TiktokLiveChatClientConfig config, ITiktokMsgListener msgListener, ITiktokConnectionListener connectionListener) {
    this(config, msgListener, connectionListener, (EventLoopGroup)new NioEventLoopGroup());
  }
  
  public TiktokLiveChatClient(TiktokLiveChatClientConfig config, ITiktokMsgListener msgListener) {
    this(config, msgListener, (ITiktokConnectionListener)null, (EventLoopGroup)new NioEventLoopGroup());
  }
  
  public TiktokLiveChatClient(TiktokLiveChatClientConfig config) {
    this(config, (ITiktokMsgListener)null);
  }
  
  public void init() {
    if (StrUtil.isNotBlank(((TiktokLiveChatClientConfig)getConfig()).getForwardWebsocketUri())) {
      TiktokForwardMsgListener forwardMsgListener = new TiktokForwardMsgListener(((TiktokLiveChatClientConfig)getConfig()).getForwardWebsocketUri());
      addMsgListener((IBaseMsgListener)forwardMsgListener);
      addStatusChangeListener((evt, oldStatus, newStatus) -> {
            if (newStatus == ClientStatusEnums.DESTROYED)
              forwardMsgListener.destroyForwardClient(); 
          });
    } 
    super.init();
  }
  
  public TiktokConnectionHandler initConnectionHandler(IBaseConnectionListener<TiktokConnectionHandler> clientConnectionListener) {
    return new TiktokConnectionHandler(() -> {
          DefaultHttpHeaders headers = new DefaultHttpHeaders();
          headers.add(Header.COOKIE.name(), "ttwid=" + this.roomInitResult.getTtwid() + "; " + ((TiktokLiveChatClientConfig)getConfig()).getCookie());
          headers.add(Header.USER_AGENT.name(), ((TiktokLiveChatClientConfig)getConfig()).getUserAgent());
          return new WebSocketClientProtocolHandler(WebSocketClientProtocolConfig.newBuilder().webSocketUri(getWebsocketUri()).version(WebSocketVersion.V13).subprotocol(null).allowExtensions(true).customHeaders((HttpHeaders)headers).maxFramePayloadLength(((TiktokLiveChatClientConfig)getConfig()).getMaxFramePayloadLength()).handshakeTimeoutMillis(((TiktokLiveChatClientConfig)getConfig()).getHandshakeTimeoutMillis()).build());
        }this, clientConnectionListener);
  }
  
  protected void initChannel(SocketChannel channel) {
    channel.pipeline().addLast(new ChannelHandler[] { (ChannelHandler)new TiktokLiveChatClientChannelInitializer(this) });
  }
  
  public void connect(Runnable success, Consumer<Throwable> failed) {
    this.roomInitResult = TiktokApis.roomInit(((TiktokLiveChatClientConfig)getConfig()).getBrowserVersion(), String.valueOf(((TiktokLiveChatClientConfig)getConfig()).getRoomId()), ((TiktokLiveChatClientConfig)getConfig()).getCookie(), this.roomInitResult);
    super.connect(success, failed);
  }
  
  protected String getWebSocketUriString() {
    String webSocketUriString = super.getWebSocketUriString();
    if (StrUtil.isNotBlank(webSocketUriString))
      return webSocketUriString; 
    Response webcastResponse = this.roomInitResult.getWebcastResponse();
    long realRoomId = this.roomInitResult.getRealRoomId();
    String pushServer = webcastResponse.getPushServer();
    Map<String, String> pushServerParams = new LinkedHashMap<>();
    pushServerParams.put("aid", "1988");
    pushServerParams.put("app_language", "en");
    pushServerParams.put("app_name", "tiktok_web");
    pushServerParams.put("browser_language", "en-US");
    pushServerParams.put("browser_name", "Mozilla");
    pushServerParams.put("browser_online", "true");
    pushServerParams.put("browser_platform", "MacIntel");
    pushServerParams.put("browser_version", ((TiktokLiveChatClientConfig)getConfig()).getBrowserVersion());
    pushServerParams.put("compress", "gzip");
    pushServerParams.put("cookie_enabled", "true");
    pushServerParams.put("cursor", webcastResponse.getCursor());
    pushServerParams.put("debug", "false");
    pushServerParams.put("device_platform", "web");
    pushServerParams.put("heartbeatDuration ", "0");
    pushServerParams.put("host", "https://webcast.tiktok.com");
    pushServerParams.put("identity", "audience");
    pushServerParams.put("imprp", webcastResponse.getRouteParamsOrThrow("imprp"));
    pushServerParams.put("internal_ext", webcastResponse.getInternalExt());
    pushServerParams.put("live_id", "12");
    pushServerParams.put("room_id", Long.toString(realRoomId));
    pushServerParams.put("screen_height", "800");
    pushServerParams.put("screen_width", "1280");
    pushServerParams.put("sup_ws_ds_opt", "1");
    pushServerParams.put("tz_name", "Asia/Shanghai");
    pushServerParams.put("update_version_code", ((TiktokLiveChatClientConfig)getConfig()).getUpdateVersionCode());
    pushServerParams.put("version_code", ((TiktokLiveChatClientConfig)getConfig()).getVersionCode());
    pushServerParams.put("webcast_sdk_version", ((TiktokLiveChatClientConfig)getConfig()).getWebcastSdkVersion());
    pushServerParams.put("wrss", webcastResponse.getRouteParamsOrThrow("wrss"));
    String wss = StrUtil.format("{}?{}", new Object[] { pushServer, OrLiveChatHttpUtil.toParams(pushServerParams) });
    log.debug("wss uri: {}", wss);
    return wss;
  }
}
