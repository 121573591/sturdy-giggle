package tech.ordinaryroad.live.chat.client.tiktok.netty.handler;

import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import java.io.IOException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.ordinaryroad.live.chat.client.codec.tiktok.api.TiktokApis;
import tech.ordinaryroad.live.chat.client.codec.tiktok.constant.TiktokCmdEnum;
import tech.ordinaryroad.live.chat.client.codec.tiktok.msg.TiktokCmdMsg;
import tech.ordinaryroad.live.chat.client.codec.tiktok.msg.TiktokControlMsg;
import tech.ordinaryroad.live.chat.client.codec.tiktok.msg.TiktokDanmuMsg;
import tech.ordinaryroad.live.chat.client.codec.tiktok.msg.TiktokEnterRoomMsg;
import tech.ordinaryroad.live.chat.client.codec.tiktok.msg.TiktokGiftMsg;
import tech.ordinaryroad.live.chat.client.codec.tiktok.msg.TiktokLikeMsg;
import tech.ordinaryroad.live.chat.client.codec.tiktok.msg.TiktokRoomStatsMsg;
import tech.ordinaryroad.live.chat.client.codec.tiktok.msg.TiktokSocialMsg;
import tech.ordinaryroad.live.chat.client.codec.tiktok.msg.base.ITiktokMsg;
import tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.ChatMessage;
import tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.ControlMessage;
import tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.GiftMessage;
import tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.LikeMessage;
import tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.MemberMessage;
import tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.SocialMessage;
import tech.ordinaryroad.live.chat.client.commons.base.exception.BaseException;
import tech.ordinaryroad.live.chat.client.commons.base.msg.ICmdMsg;
import tech.ordinaryroad.live.chat.client.servers.netty.client.base.BaseNettyClient;
import tech.ordinaryroad.live.chat.client.servers.netty.client.handler.BaseNettyClientBinaryFrameHandler;
import tech.ordinaryroad.live.chat.client.tiktok.client.TiktokLiveChatClient;
import tech.ordinaryroad.live.chat.client.tiktok.config.TiktokLiveChatClientConfig;
import tech.ordinaryroad.live.chat.client.tiktok.listener.ITiktokMsgListener;

@Sharable
public class TiktokBinaryFrameHandler extends BaseNettyClientBinaryFrameHandler<TiktokLiveChatClient, TiktokBinaryFrameHandler, TiktokCmdEnum, ITiktokMsg, ITiktokMsgListener> {
  private static final Logger log = LoggerFactory.getLogger(TiktokBinaryFrameHandler.class);
  
  private ChannelHandlerContext channelHandlerContext;
  
  public TiktokBinaryFrameHandler(List<ITiktokMsgListener> iTiktokMsgListeners, TiktokLiveChatClient client) {
    super(iTiktokMsgListeners, (BaseNettyClient)client);
  }
  
  public TiktokBinaryFrameHandler(List<ITiktokMsgListener> iTiktokMsgListeners, long roomId) {
    super(iTiktokMsgListeners, roomId);
  }
  
  public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
    super.handlerAdded(ctx);
    this.channelHandlerContext = ctx;
  }
  
  public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
    super.handlerRemoved(ctx);
    this.channelHandlerContext = null;
  }
  
  public void onCmdMsg(TiktokCmdEnum cmd, ICmdMsg<TiktokCmdEnum> cmdMsg) {
    if (this.msgListeners.isEmpty())
      return; 
    ByteString payload = ((TiktokCmdMsg)cmdMsg).getMsg().getPayload();
    switch (cmd) {
      case WebcastChatMessage:
        try {
          ChatMessage chatMessage = ChatMessage.parseFrom(payload);
          TiktokDanmuMsg msg = new TiktokDanmuMsg(chatMessage);
          iteratorMsgListeners(msgListener -> msgListener.onDanmuMsg(this, msg));
        } catch (IOException e) {
          throw new BaseException(e);
        } 
        return;
      case WebcastGiftMessage:
        try {
          GiftMessage giftMessage = GiftMessage.parseFrom(payload);
          TiktokGiftMsg msg = new TiktokGiftMsg(giftMessage);
          TiktokApis.calculateGiftCount(msg, ((TiktokLiveChatClientConfig)((TiktokLiveChatClient)getClient()).getConfig()).getGiftCountCalculationTime());
          iteratorMsgListeners(msgListener -> msgListener.onGiftMsg(this, msg));
        } catch (InvalidProtocolBufferException e) {
          throw new BaseException(e);
        } 
        return;
      case WebcastMemberMessage:
        try {
          MemberMessage memberMessage = MemberMessage.parseFrom(payload);
          TiktokEnterRoomMsg msg = new TiktokEnterRoomMsg(memberMessage);
          TiktokRoomStatsMsg roomStatsMsg = new TiktokRoomStatsMsg();
          roomStatsMsg.setWatchingCount(String.valueOf(msg.getMsg().getMemberCount()));
          iteratorMsgListeners(msgListener -> {
                msgListener.onEnterRoomMsg(this, msg);
                msgListener.onRoomStatsMsg(this, roomStatsMsg);
              });
        } catch (InvalidProtocolBufferException e) {
          throw new BaseException(e);
        } 
        return;
      case WebcastLikeMessage:
        try {
          LikeMessage likeMessage = LikeMessage.parseFrom(payload);
          TiktokLikeMsg msg = new TiktokLikeMsg(likeMessage);
          TiktokRoomStatsMsg roomStatsMsg = new TiktokRoomStatsMsg();
          roomStatsMsg.setLikedCount(String.valueOf(msg.getMsg().getTotal()));
          iteratorMsgListeners(msgListener -> {
                msgListener.onLikeMsg(this, msg);
                msgListener.onRoomStatsMsg(this, roomStatsMsg);
              });
        } catch (InvalidProtocolBufferException e) {
          throw new BaseException(e);
        } 
        return;
      case WebcastControlMessage:
        try {
          ControlMessage controlMessage = ControlMessage.parseFrom(payload);
          TiktokControlMsg msg = new TiktokControlMsg(controlMessage);
          iteratorMsgListeners(msgListener -> msgListener.onLiveStatusMsg(this, msg));
        } catch (InvalidProtocolBufferException e) {
          throw new BaseException(e);
        } 
        return;
      case WebcastSocialMessage:
        try {
          SocialMessage socialMessage = SocialMessage.parseFrom(payload);
          TiktokSocialMsg msg = new TiktokSocialMsg(socialMessage);
          iteratorMsgListeners(msgListener -> msgListener.onSocialMsg(this, msg));
        } catch (InvalidProtocolBufferException e) {
          throw new BaseException(e);
        } 
        return;
    } 
    iteratorMsgListeners(msgListener -> msgListener.onOtherCmdMsg(this, (Enum)cmd, cmdMsg));
  }
}
