package tech.ordinaryroad.live.chat.client.douyin.netty.handler;

import cn.hutool.core.util.NumberUtil;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import java.io.IOException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.ordinaryroad.live.chat.client.codec.douyin.api.DouyinApis;
import tech.ordinaryroad.live.chat.client.codec.douyin.constant.DouyinCmdEnum;
import tech.ordinaryroad.live.chat.client.codec.douyin.msg.DouyinCmdMsg;
import tech.ordinaryroad.live.chat.client.codec.douyin.msg.DouyinControlMsg;
import tech.ordinaryroad.live.chat.client.codec.douyin.msg.DouyinDanmuMsg;
import tech.ordinaryroad.live.chat.client.codec.douyin.msg.DouyinEnterRoomMsg;
import tech.ordinaryroad.live.chat.client.codec.douyin.msg.DouyinGiftMsg;
import tech.ordinaryroad.live.chat.client.codec.douyin.msg.DouyinLikeMsg;
import tech.ordinaryroad.live.chat.client.codec.douyin.msg.DouyinRoomStatsMsg;
import tech.ordinaryroad.live.chat.client.codec.douyin.msg.DouyinSocialMsg;
import tech.ordinaryroad.live.chat.client.codec.douyin.msg.base.IDouyinMsg;
import tech.ordinaryroad.live.chat.client.codec.douyin.protobuf.ChatMessage;
import tech.ordinaryroad.live.chat.client.codec.douyin.protobuf.ControlMessage;
import tech.ordinaryroad.live.chat.client.codec.douyin.protobuf.GiftMessage;
import tech.ordinaryroad.live.chat.client.codec.douyin.protobuf.LikeMessage;
import tech.ordinaryroad.live.chat.client.codec.douyin.protobuf.MemberMessage;
import tech.ordinaryroad.live.chat.client.codec.douyin.protobuf.RoomStatsMessage;
import tech.ordinaryroad.live.chat.client.codec.douyin.protobuf.SocialMessage;
import tech.ordinaryroad.live.chat.client.commons.base.exception.BaseException;
import tech.ordinaryroad.live.chat.client.commons.base.msg.ICmdMsg;
import tech.ordinaryroad.live.chat.client.douyin.client.DouyinLiveChatClient;
import tech.ordinaryroad.live.chat.client.douyin.config.DouyinLiveChatClientConfig;
import tech.ordinaryroad.live.chat.client.douyin.listener.IDouyinMsgListener;
import tech.ordinaryroad.live.chat.client.servers.netty.client.base.BaseNettyClient;
import tech.ordinaryroad.live.chat.client.servers.netty.client.handler.BaseNettyClientBinaryFrameHandler;

@Sharable
public class DouyinBinaryFrameHandler extends BaseNettyClientBinaryFrameHandler<DouyinLiveChatClient, DouyinBinaryFrameHandler, DouyinCmdEnum, IDouyinMsg, IDouyinMsgListener> {
  private static final Logger log = LoggerFactory.getLogger(DouyinBinaryFrameHandler.class);
  
  private ChannelHandlerContext channelHandlerContext;
  
  public DouyinBinaryFrameHandler(List<IDouyinMsgListener> iDouyinMsgListeners, DouyinLiveChatClient client) {
    super(iDouyinMsgListeners, (BaseNettyClient)client);
  }
  
  public DouyinBinaryFrameHandler(List<IDouyinMsgListener> iDouyinMsgListeners, long roomId) {
    super(iDouyinMsgListeners, roomId);
  }
  
  public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
    super.handlerAdded(ctx);
    this.channelHandlerContext = ctx;
  }
  
  public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
    super.handlerRemoved(ctx);
    this.channelHandlerContext = null;
  }
  
  public void onCmdMsg(DouyinCmdEnum cmd, ICmdMsg<DouyinCmdEnum> cmdMsg) {
    if (this.msgListeners.isEmpty())
      return; 
    ByteString payload = ((DouyinCmdMsg)cmdMsg).getMsg().getPayload();
    switch (cmd) {
      case WebcastChatMessage:
        try {
          ChatMessage chatMessage = ChatMessage.parseFrom(payload);
          DouyinDanmuMsg msg = new DouyinDanmuMsg(chatMessage);
          iteratorMsgListeners(msgListener -> msgListener.onDanmuMsg(this, msg));
        } catch (IOException e) {
          throw new BaseException(e);
        } 
        return;
      case WebcastGiftMessage:
        try {
          GiftMessage giftMessage = GiftMessage.parseFrom(payload);
          DouyinGiftMsg msg = new DouyinGiftMsg(giftMessage);
          DouyinApis.calculateGiftCount(msg, ((DouyinLiveChatClientConfig)((DouyinLiveChatClient)getClient()).getConfig()).getGiftCountCalculationTime());
          iteratorMsgListeners(msgListener -> msgListener.onGiftMsg(this, msg));
        } catch (InvalidProtocolBufferException e) {
          throw new BaseException(e);
        } 
        return;
      case WebcastMemberMessage:
        try {
          MemberMessage memberMessage = MemberMessage.parseFrom(payload);
          DouyinEnterRoomMsg msg = new DouyinEnterRoomMsg(memberMessage);
          iteratorMsgListeners(msgListener -> msgListener.onEnterRoomMsg(this, msg));
        } catch (InvalidProtocolBufferException e) {
          throw new BaseException(e);
        } 
        return;
      case WebcastLikeMessage:
        try {
          LikeMessage likeMessage = LikeMessage.parseFrom(payload);
          DouyinLikeMsg msg = new DouyinLikeMsg(likeMessage);
          DouyinRoomStatsMsg douyinRoomStatsMsg = new DouyinRoomStatsMsg();
          douyinRoomStatsMsg.setLikedCount(NumberUtil.toStr(Long.valueOf(likeMessage.getTotal())));
          iteratorMsgListeners(msgListener -> {
                msgListener.onLikeMsg(this, msg);
                msgListener.onRoomStatsMsg(this, douyinRoomStatsMsg);
              });
        } catch (InvalidProtocolBufferException e) {
          throw new BaseException(e);
        } 
        return;
      case WebcastControlMessage:
        try {
          ControlMessage controlMessage = ControlMessage.parseFrom(payload);
          DouyinControlMsg msg = new DouyinControlMsg(controlMessage);
          iteratorMsgListeners(msgListener -> msgListener.onLiveStatusMsg(this, msg));
        } catch (InvalidProtocolBufferException e) {
          throw new BaseException(e);
        } 
        return;
      case WebcastSocialMessage:
        try {
          SocialMessage socialMessage = SocialMessage.parseFrom(payload);
          DouyinSocialMsg msg = new DouyinSocialMsg(socialMessage);
          iteratorMsgListeners(msgListener -> msgListener.onSocialMsg(this, msg));
        } catch (InvalidProtocolBufferException e) {
          throw new BaseException(e);
        } 
        return;
      case WebcastRoomStatsMessage:
        try {
          RoomStatsMessage roomStatsMessage = RoomStatsMessage.parseFrom(payload);
          DouyinRoomStatsMsg douyinRoomStatsMsg = new DouyinRoomStatsMsg();
          douyinRoomStatsMsg.setMsg(roomStatsMessage);
          iteratorMsgListeners(msgListener -> msgListener.onRoomStatsMsg(this, douyinRoomStatsMsg));
        } catch (InvalidProtocolBufferException e) {
          throw new BaseException(e);
        } 
        return;
    } 
    iteratorMsgListeners(msgListener -> msgListener.onOtherCmdMsg(this, (Enum)cmd, cmdMsg));
  }
}
