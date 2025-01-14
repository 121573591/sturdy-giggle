package tech.ordinaryroad.live.chat.client.tiktok.listener;

import tech.ordinaryroad.live.chat.client.codec.tiktok.constant.TiktokCmdEnum;
import tech.ordinaryroad.live.chat.client.codec.tiktok.msg.TiktokControlMsg;
import tech.ordinaryroad.live.chat.client.codec.tiktok.msg.TiktokDanmuMsg;
import tech.ordinaryroad.live.chat.client.codec.tiktok.msg.TiktokEnterRoomMsg;
import tech.ordinaryroad.live.chat.client.codec.tiktok.msg.TiktokGiftMsg;
import tech.ordinaryroad.live.chat.client.codec.tiktok.msg.TiktokLikeMsg;
import tech.ordinaryroad.live.chat.client.codec.tiktok.msg.TiktokRoomStatsMsg;
import tech.ordinaryroad.live.chat.client.codec.tiktok.msg.TiktokSocialMsg;
import tech.ordinaryroad.live.chat.client.commons.base.listener.IBaseMsgListener;
import tech.ordinaryroad.live.chat.client.commons.base.listener.IDanmuMsgListener;
import tech.ordinaryroad.live.chat.client.commons.base.listener.IEnterRoomMsgListener;
import tech.ordinaryroad.live.chat.client.commons.base.listener.IGiftMsgListener;
import tech.ordinaryroad.live.chat.client.commons.base.listener.ILikeMsgListener;
import tech.ordinaryroad.live.chat.client.commons.base.listener.ILiveStatusChangeListener;
import tech.ordinaryroad.live.chat.client.commons.base.listener.IRoomStatsMsgListener;
import tech.ordinaryroad.live.chat.client.commons.base.listener.ISocialMsgListener;
import tech.ordinaryroad.live.chat.client.tiktok.netty.handler.TiktokBinaryFrameHandler;

public interface ITiktokMsgListener extends IBaseMsgListener<TiktokBinaryFrameHandler, TiktokCmdEnum>, IDanmuMsgListener<TiktokBinaryFrameHandler, TiktokDanmuMsg>, IGiftMsgListener<TiktokBinaryFrameHandler, TiktokGiftMsg>, IEnterRoomMsgListener<TiktokBinaryFrameHandler, TiktokEnterRoomMsg>, ILikeMsgListener<TiktokBinaryFrameHandler, TiktokLikeMsg>, ILiveStatusChangeListener<TiktokBinaryFrameHandler, TiktokControlMsg>, IRoomStatsMsgListener<TiktokBinaryFrameHandler, TiktokRoomStatsMsg>, ISocialMsgListener<TiktokBinaryFrameHandler, TiktokSocialMsg> {}
