package tech.ordinaryroad.live.chat.client.kuaishou.netty.handler;

import com.google.protobuf.ByteString;
import io.netty.channel.ChannelHandler.Sharable;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.ordinaryroad.live.chat.client.codec.kuaishou.api.KuaishouApis;
import tech.ordinaryroad.live.chat.client.codec.kuaishou.msg.KuaishouCmdMsg;
import tech.ordinaryroad.live.chat.client.codec.kuaishou.msg.KuaishouDanmuMsg;
import tech.ordinaryroad.live.chat.client.codec.kuaishou.msg.KuaishouGiftMsg;
import tech.ordinaryroad.live.chat.client.codec.kuaishou.msg.KuaishouLikeMsg;
import tech.ordinaryroad.live.chat.client.codec.kuaishou.msg.KuaishouRoomStatsMsg;
import tech.ordinaryroad.live.chat.client.codec.kuaishou.msg.base.IKuaishouMsg;
import tech.ordinaryroad.live.chat.client.codec.kuaishou.protobuf.PayloadTypeOuterClass;
import tech.ordinaryroad.live.chat.client.codec.kuaishou.protobuf.SCWebFeedPushOuterClass;
import tech.ordinaryroad.live.chat.client.codec.kuaishou.protobuf.SocketMessageOuterClass;
import tech.ordinaryroad.live.chat.client.codec.kuaishou.protobuf.WebCommentFeedOuterClass;
import tech.ordinaryroad.live.chat.client.codec.kuaishou.protobuf.WebGiftFeedOuterClass;
import tech.ordinaryroad.live.chat.client.codec.kuaishou.protobuf.WebLikeFeedOuterClass;
import tech.ordinaryroad.live.chat.client.commons.base.msg.ICmdMsg;
import tech.ordinaryroad.live.chat.client.kuaishou.client.KuaishouLiveChatClient;
import tech.ordinaryroad.live.chat.client.kuaishou.listener.IKuaishouMsgListener;
import tech.ordinaryroad.live.chat.client.servers.netty.client.base.BaseNettyClient;
import tech.ordinaryroad.live.chat.client.servers.netty.client.handler.BaseNettyClientBinaryFrameHandler;

@Sharable
public class KuaishouBinaryFrameHandler extends BaseNettyClientBinaryFrameHandler<KuaishouLiveChatClient, KuaishouBinaryFrameHandler, PayloadTypeOuterClass.PayloadType, IKuaishouMsg, IKuaishouMsgListener> {
  private static final Logger log = LoggerFactory.getLogger(KuaishouBinaryFrameHandler.class);
  
  public KuaishouBinaryFrameHandler(List<IKuaishouMsgListener> iKuaishouMsgListeners, KuaishouLiveChatClient client) {
    super(iKuaishouMsgListeners, (BaseNettyClient)client);
  }
  
  public KuaishouBinaryFrameHandler(List<IKuaishouMsgListener> iKuaishouMsgListeners, long roomId) {
    super(iKuaishouMsgListeners, roomId);
  }
  
  public void onCmdMsg(PayloadTypeOuterClass.PayloadType cmd, ICmdMsg<PayloadTypeOuterClass.PayloadType> cmdMsg) {
    try {
      SCWebFeedPushOuterClass.SCWebFeedPush scWebFeedPush;
      String displayLikeCount, displayWatchingCount;
      KuaishouRoomStatsMsg kuaishouRoomStatsMsg;
      if (this.msgListeners.isEmpty())
        return; 
      KuaishouCmdMsg kuaishouCmdMsg = (KuaishouCmdMsg)cmdMsg;
      SocketMessageOuterClass.SocketMessage socketMessage = kuaishouCmdMsg.getMsg();
      ByteString payloadByteString = socketMessage.getPayload();
      switch (socketMessage.getPayloadType()) {
        case SC_FEED_PUSH:
          scWebFeedPush = SCWebFeedPushOuterClass.SCWebFeedPush.parseFrom(payloadByteString);
          if (scWebFeedPush.getCommentFeedsCount() > 0)
            for (WebCommentFeedOuterClass.WebCommentFeed webCommentFeed : scWebFeedPush.getCommentFeedsList()) {
              KuaishouDanmuMsg msg = new KuaishouDanmuMsg(webCommentFeed);
              iteratorMsgListeners(msgListener -> msgListener.onDanmuMsg(this, msg));
            }  
          if (scWebFeedPush.getGiftFeedsCount() > 0)
            for (WebGiftFeedOuterClass.WebGiftFeed webGiftFeed : scWebFeedPush.getGiftFeedsList()) {
              KuaishouGiftMsg msg = new KuaishouGiftMsg(webGiftFeed);
              KuaishouApis.calculateGiftCount(msg);
              iteratorMsgListeners(msgListener -> msgListener.onGiftMsg(this, msg));
            }  
          if (scWebFeedPush.getLikeFeedsCount() > 0)
            for (WebLikeFeedOuterClass.WebLikeFeed webLikeFeed : scWebFeedPush.getLikeFeedsList()) {
              KuaishouLikeMsg msg = new KuaishouLikeMsg(webLikeFeed);
              iteratorMsgListeners(msgListener -> msgListener.onLikeMsg(this, msg));
            }  
          displayLikeCount = scWebFeedPush.getDisplayLikeCount();
          displayWatchingCount = scWebFeedPush.getDisplayWatchingCount();
          kuaishouRoomStatsMsg = new KuaishouRoomStatsMsg();
          kuaishouRoomStatsMsg.setLikedCount(displayLikeCount);
          kuaishouRoomStatsMsg.setWatchingCount(displayWatchingCount);
          iteratorMsgListeners(msgListener -> msgListener.onRoomStatsMsg(this, kuaishouRoomStatsMsg));
          return;
      } 
      iteratorMsgListeners(msgListener -> msgListener.onOtherCmdMsg(this, (Enum)cmd, (ICmdMsg)kuaishouCmdMsg));
    } catch (Throwable $ex) {
      throw $ex;
    } 
  }
}
