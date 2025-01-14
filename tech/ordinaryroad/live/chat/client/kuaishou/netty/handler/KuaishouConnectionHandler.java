package tech.ordinaryroad.live.chat.client.kuaishou.netty.handler;

import cn.hutool.core.util.RandomUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.handler.codec.http.websocketx.WebSocketClientProtocolHandler;
import io.netty.util.concurrent.GenericFutureListener;
import java.util.function.Supplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.ordinaryroad.live.chat.client.codec.kuaishou.api.KuaishouApis;
import tech.ordinaryroad.live.chat.client.codec.kuaishou.msg.KuaishouCmdMsg;
import tech.ordinaryroad.live.chat.client.codec.kuaishou.protobuf.CSHeartbeatOuterClass;
import tech.ordinaryroad.live.chat.client.codec.kuaishou.protobuf.CSWebEnterRoomOuterClass;
import tech.ordinaryroad.live.chat.client.codec.kuaishou.protobuf.PayloadTypeOuterClass;
import tech.ordinaryroad.live.chat.client.codec.kuaishou.protobuf.SocketMessageOuterClass;
import tech.ordinaryroad.live.chat.client.commons.base.listener.IBaseConnectionListener;
import tech.ordinaryroad.live.chat.client.kuaishou.client.KuaishouLiveChatClient;
import tech.ordinaryroad.live.chat.client.kuaishou.config.KuaishouLiveChatClientConfig;
import tech.ordinaryroad.live.chat.client.servers.netty.client.base.BaseNettyClient;
import tech.ordinaryroad.live.chat.client.servers.netty.client.handler.BaseNettyClientConnectionHandler;

@Sharable
public class KuaishouConnectionHandler extends BaseNettyClientConnectionHandler<KuaishouLiveChatClient, KuaishouConnectionHandler> {
  private static final Logger log = LoggerFactory.getLogger(KuaishouConnectionHandler.class);
  
  private final Object roomId;
  
  private String cookie;
  
  private final KuaishouApis.RoomInitResult roomInitResult;
  
  public KuaishouConnectionHandler(Supplier<WebSocketClientProtocolHandler> webSocketProtocolHandler, KuaishouLiveChatClient client, IBaseConnectionListener<KuaishouConnectionHandler> listener) {
    super(webSocketProtocolHandler, (BaseNettyClient)client, listener);
    this.roomId = ((KuaishouLiveChatClientConfig)client.getConfig()).getRoomId();
    this.cookie = ((KuaishouLiveChatClientConfig)client.getConfig()).getCookie();
    this.roomInitResult = client.getRoomInitResult();
  }
  
  public KuaishouConnectionHandler(Supplier<WebSocketClientProtocolHandler> webSocketProtocolHandler, KuaishouLiveChatClient client) {
    this(webSocketProtocolHandler, client, (IBaseConnectionListener<KuaishouConnectionHandler>)null);
  }
  
  public KuaishouConnectionHandler(Supplier<WebSocketClientProtocolHandler> webSocketProtocolHandler, long roomId, KuaishouApis.RoomInitResult roomInitResult, IBaseConnectionListener<KuaishouConnectionHandler> listener, String cookie) {
    super(webSocketProtocolHandler, listener);
    this.roomId = Long.valueOf(roomId);
    this.cookie = cookie;
    this.roomInitResult = roomInitResult;
  }
  
  public KuaishouConnectionHandler(Supplier<WebSocketClientProtocolHandler> webSocketProtocolHandler, long roomId, KuaishouApis.RoomInitResult roomInitResult, IBaseConnectionListener<KuaishouConnectionHandler> listener) {
    this(webSocketProtocolHandler, roomId, roomInitResult, listener, null);
  }
  
  public KuaishouConnectionHandler(Supplier<WebSocketClientProtocolHandler> webSocketProtocolHandler, long roomId, KuaishouApis.RoomInitResult roomInitResult, String cookie) {
    this(webSocketProtocolHandler, roomId, roomInitResult, null, cookie);
  }
  
  public KuaishouConnectionHandler(Supplier<WebSocketClientProtocolHandler> webSocketProtocolHandler, KuaishouApis.RoomInitResult roomInitResult, long roomId) {
    this(webSocketProtocolHandler, roomId, roomInitResult, null, null);
  }
  
  public void sendHeartbeat(Channel channel) {
    if (log.isDebugEnabled())
      log.debug("发送心跳包"); 
    channel.writeAndFlush(new KuaishouCmdMsg(
          
          SocketMessageOuterClass.SocketMessage.newBuilder()
          .setPayloadType(PayloadTypeOuterClass.PayloadType.CS_HEARTBEAT)
          .setPayload(
            CSHeartbeatOuterClass.CSHeartbeat.newBuilder()
            .setTimestamp(System.currentTimeMillis())
            .build()
            .toByteString())
          
          .build()))
      
      .addListener((GenericFutureListener)(future -> {
          if (future.isSuccess()) {
            if (log.isDebugEnabled())
              log.debug("心跳包发送完成"); 
          } else {
            log.error("心跳包发送失败", future.cause());
          } 
        }));
  }
  
  public void sendAuthRequest(Channel channel) {
    channel.writeAndFlush(new KuaishouCmdMsg(
          
          SocketMessageOuterClass.SocketMessage.newBuilder()
          .setPayloadType(PayloadTypeOuterClass.PayloadType.CS_ENTER_ROOM)
          .setPayload(
            CSWebEnterRoomOuterClass.CSWebEnterRoom.newBuilder()
            .setToken(this.roomInitResult.getToken())
            .setLiveStreamId(this.roomInitResult.getLiveStreamId())
            .setPageId(RandomUtil.randomString(16) + System.currentTimeMillis())
            .build()
            .toByteString())
          
          .build()));
  }
  
  public Object getRoomId() {
    return (this.client != null) ? ((KuaishouLiveChatClientConfig)((KuaishouLiveChatClient)this.client).getConfig()).getRoomId() : this.roomId;
  }
  
  private String getCookie() {
    return (this.client != null) ? ((KuaishouLiveChatClientConfig)((KuaishouLiveChatClient)this.client).getConfig()).getCookie() : this.cookie;
  }
}
