package tech.ordinaryroad.live.chat.client.douyin.client;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
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
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import javax.script.ScriptEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.ordinaryroad.live.chat.client.codec.douyin.api.DouyinApis;
import tech.ordinaryroad.live.chat.client.codec.douyin.constant.DouyinCmdEnum;
import tech.ordinaryroad.live.chat.client.codec.douyin.msg.base.IDouyinMsg;
import tech.ordinaryroad.live.chat.client.commons.base.exception.BaseException;
import tech.ordinaryroad.live.chat.client.commons.base.listener.IBaseConnectionListener;
import tech.ordinaryroad.live.chat.client.commons.base.listener.IBaseMsgListener;
import tech.ordinaryroad.live.chat.client.commons.client.enums.ClientStatusEnums;
import tech.ordinaryroad.live.chat.client.commons.util.OrLiveChatCollUtil;
import tech.ordinaryroad.live.chat.client.commons.util.OrLiveChatHttpUtil;
import tech.ordinaryroad.live.chat.client.douyin.config.DouyinLiveChatClientConfig;
import tech.ordinaryroad.live.chat.client.douyin.listener.IDouyinConnectionListener;
import tech.ordinaryroad.live.chat.client.douyin.listener.IDouyinMsgListener;
import tech.ordinaryroad.live.chat.client.douyin.listener.impl.DouyinForwardMsgListener;
import tech.ordinaryroad.live.chat.client.douyin.netty.handler.DouyinBinaryFrameHandler;
import tech.ordinaryroad.live.chat.client.douyin.netty.handler.DouyinConnectionHandler;
import tech.ordinaryroad.live.chat.client.douyin.netty.handler.DouyinLiveChatClientChannelInitializer;
import tech.ordinaryroad.live.chat.client.servers.netty.client.base.BaseNettyClient;
import tech.ordinaryroad.live.chat.client.servers.netty.client.config.BaseNettyClientConfig;
import tech.ordinaryroad.live.chat.client.servers.netty.handler.base.BaseConnectionHandler;

public class DouyinLiveChatClient extends BaseNettyClient<DouyinLiveChatClientConfig, DouyinCmdEnum, IDouyinMsg, IDouyinMsgListener, DouyinConnectionHandler, DouyinBinaryFrameHandler> {
  private static final Logger log = LoggerFactory.getLogger(DouyinLiveChatClient.class);
  
  private DouyinApis.RoomInitResult roomInitResult = new DouyinApis.RoomInitResult();
  
  public static String JS_SDK;
  
  public DouyinApis.RoomInitResult getRoomInitResult() {
    return this.roomInitResult;
  }
  
  public DouyinLiveChatClient(DouyinLiveChatClientConfig config, List<IDouyinMsgListener> msgListeners, IDouyinConnectionListener connectionListener, EventLoopGroup workerGroup) {
    super((BaseNettyClientConfig)config, workerGroup, (IBaseConnectionListener)connectionListener);
    addMsgListeners(msgListeners);
    init();
  }
  
  public DouyinLiveChatClient(DouyinLiveChatClientConfig config, IDouyinMsgListener msgListener, IDouyinConnectionListener connectionListener, EventLoopGroup workerGroup) {
    super((BaseNettyClientConfig)config, workerGroup, (IBaseConnectionListener)connectionListener);
    addMsgListener((IBaseMsgListener)msgListener);
    init();
  }
  
  public DouyinLiveChatClient(DouyinLiveChatClientConfig config, IDouyinMsgListener msgListener, IDouyinConnectionListener connectionListener) {
    this(config, msgListener, connectionListener, (EventLoopGroup)new NioEventLoopGroup());
  }
  
  public DouyinLiveChatClient(DouyinLiveChatClientConfig config, IDouyinMsgListener msgListener) {
    this(config, msgListener, (IDouyinConnectionListener)null, (EventLoopGroup)new NioEventLoopGroup());
  }
  
  public DouyinLiveChatClient(DouyinLiveChatClientConfig config) {
    this(config, (IDouyinMsgListener)null);
  }
  
  public void init() {
    if (StrUtil.isNotBlank(((DouyinLiveChatClientConfig)getConfig()).getForwardWebsocketUri())) {
      DouyinForwardMsgListener forwardMsgListener = new DouyinForwardMsgListener(((DouyinLiveChatClientConfig)getConfig()).getForwardWebsocketUri());
      addMsgListener((IBaseMsgListener)forwardMsgListener);
      addStatusChangeListener((evt, oldStatus, newStatus) -> {
            if (newStatus == ClientStatusEnums.DESTROYED)
              forwardMsgListener.destroyForwardClient(); 
          });
    } 
    super.init();
  }
  
  public DouyinConnectionHandler initConnectionHandler(IBaseConnectionListener<DouyinConnectionHandler> clientConnectionListener) {
    return new DouyinConnectionHandler(() -> {
          DefaultHttpHeaders headers = new DefaultHttpHeaders();
          headers.add(Header.COOKIE.name(), "ttwid=" + this.roomInitResult.getTtwid());
          headers.add(Header.USER_AGENT.name(), ((DouyinLiveChatClientConfig)getConfig()).getUserAgent());
          return new WebSocketClientProtocolHandler(WebSocketClientProtocolConfig.newBuilder().webSocketUri(getWebsocketUri()).version(WebSocketVersion.V13).subprotocol(null).allowExtensions(true).customHeaders((HttpHeaders)headers).maxFramePayloadLength(((DouyinLiveChatClientConfig)getConfig()).getMaxFramePayloadLength()).handshakeTimeoutMillis(((DouyinLiveChatClientConfig)getConfig()).getHandshakeTimeoutMillis()).build());
        }this, clientConnectionListener);
  }
  
  protected void initChannel(SocketChannel channel) {
    channel.pipeline().addLast(new ChannelHandler[] { (ChannelHandler)new DouyinLiveChatClientChannelInitializer(this) });
  }
  
  public void connect(Runnable success, Consumer<Throwable> failed) {
    this.roomInitResult = DouyinApis.roomInit(((DouyinLiveChatClientConfig)getConfig()).getRoomId(), ((DouyinLiveChatClientConfig)getConfig()).getCookie(), this.roomInitResult);
    super.connect(success, failed);
  }
  
  protected String getWebSocketUriString() {
    String webSocketUriString = super.getWebSocketUriString();
    if (StrUtil.isBlank(webSocketUriString))
      webSocketUriString = (String)OrLiveChatCollUtil.getRandom(DouyinLiveChatClientConfig.WEB_SOCKET_URIS); 
    long realRoomId = this.roomInitResult.getRealRoomId();
    String userUniqueId = this.roomInitResult.getUserUniqueId();
    Map<String, String> queryParams = new LinkedHashMap<>();
    queryParams.put("app_name", "douyin_web");
    queryParams.put("version_code", ((DouyinLiveChatClientConfig)getConfig()).getVersionCode());
    queryParams.put("webcast_sdk_version", ((DouyinLiveChatClientConfig)getConfig()).getWebcastSdkVersion());
    queryParams.put("update_version_code", ((DouyinLiveChatClientConfig)getConfig()).getUpdateVersionCode());
    queryParams.put("compress", "gzip");
    queryParams.put("device_platform", "web");
    queryParams.put("cookie_enabled", "true");
    queryParams.put("screen_width", "1280");
    queryParams.put("screen_height", "800");
    queryParams.put("browser_language", "zh-CN");
    queryParams.put("browser_platform", "MacIntel");
    queryParams.put("browser_name", "Mozilla");
    queryParams.put("browser_version", ((DouyinLiveChatClientConfig)getConfig()).getBrowserVersion());
    queryParams.put("browser_online", "true");
    queryParams.put("tz_name", "Asia/Shanghai");
    queryParams.put("cursor", "t-" + System.currentTimeMillis() + "_r-1_d-1_u-1_fh-743192" + RandomUtil.randomNumbers(13));
    queryParams.put("internal_ext", "internal_src:dim|wss_push_room_id:" + realRoomId + "|wss_push_did:" + userUniqueId + "|first_req_ms:" + 
        
        System.currentTimeMillis() + "|fetch_time:" + 
        System.currentTimeMillis() + "|seq:1|wss_info:0-" + 
        
        System.currentTimeMillis() + "-0-0|wrds_v:743192" + 
        
        RandomUtil.randomNumbers(13));
    queryParams.put("host", "https://live.douyin.com");
    queryParams.put("aid", "6383");
    queryParams.put("live_id", "1");
    queryParams.put("did_rule", "3");
    queryParams.put("endpoint", "live_pc");
    queryParams.put("support_wrds", "1");
    queryParams.put("user_unique_id", userUniqueId);
    queryParams.put("im_path", "/webcast/im/fetch/");
    queryParams.put("identity", "audience");
    queryParams.put("need_persist_msg_count", "15");
    queryParams.put("insert_task_id", "");
    queryParams.put("live_reason", "");
    queryParams.put("room_id", Long.toString(realRoomId));
    queryParams.put("heartbeatDuration ", "0");
    queryParams.put("signature", getSignature(((DouyinLiveChatClientConfig)getConfig()).getUserAgent(), this.roomInitResult.getRealRoomId(), this.roomInitResult.getUserUniqueId()));
    return webSocketUriString + "?" + OrLiveChatHttpUtil.toParams(queryParams);
  }
  
  public void sendDanmu(Object danmu, Runnable success, Consumer<Throwable> failed) {
    super.sendDanmu(danmu, success, failed);
  }
  
  static {
    InputStream resourceAsStream = DouyinLiveChatClient.class.getResourceAsStream("/js/douyin-webmssdk.js");
    JS_SDK = IoUtil.readUtf8(resourceAsStream);
  }
  
  public String getSignature(String userAgent, long roomId, String userUniqueId) {
    try {
      String JS_ENV = " document = {};\nwindow = {};\nnavigator = {\nuserAgent: '" + userAgent + "'\n};\n";
      ScriptEngine engineFactory = ((DouyinLiveChatClientConfig)getConfig()).getScriptEngine();
      engineFactory.eval(JS_ENV + JS_SDK);
      String signPram = "live_id=1,aid=6383,version_code=$version_code$,webcast_sdk_version=$webcast_sdk_version$,room_id=$roomId$,sub_room_id=,sub_channel_id=,did_rule=3,user_unique_id=$userId$,device_platform=web,device_type=,ac=,identity=audience".replace("$webcast_sdk_version$", ((DouyinLiveChatClientConfig)getConfig()).getWebcastSdkVersion()).replace("$version_code$", ((DouyinLiveChatClientConfig)getConfig()).getVersionCode()).replace("$roomId$", String.valueOf(roomId)).replace("$userId$", userUniqueId);
      String md5Hex = DigestUtil.md5Hex(signPram.getBytes(StandardCharsets.UTF_8));
      try {
        Object eval = engineFactory.eval("get_sign('" + md5Hex + "')");
        return eval.toString();
      } catch (Exception e) {
        throw new BaseException("Execution failed: getSignature", e);
      } 
    } catch (Throwable $ex) {
      throw $ex;
    } 
  }
}
