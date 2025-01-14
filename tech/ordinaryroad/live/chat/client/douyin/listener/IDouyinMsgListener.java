package tech.ordinaryroad.live.chat.client.douyin.listener;

import tech.ordinaryroad.live.chat.client.codec.douyin.constant.DouyinCmdEnum;
import tech.ordinaryroad.live.chat.client.codec.douyin.msg.DouyinControlMsg;
import tech.ordinaryroad.live.chat.client.codec.douyin.msg.DouyinDanmuMsg;
import tech.ordinaryroad.live.chat.client.codec.douyin.msg.DouyinEnterRoomMsg;
import tech.ordinaryroad.live.chat.client.codec.douyin.msg.DouyinGiftMsg;
import tech.ordinaryroad.live.chat.client.codec.douyin.msg.DouyinLikeMsg;
import tech.ordinaryroad.live.chat.client.codec.douyin.msg.DouyinRoomStatsMsg;
import tech.ordinaryroad.live.chat.client.codec.douyin.msg.DouyinSocialMsg;
import tech.ordinaryroad.live.chat.client.commons.base.listener.IBaseMsgListener;
import tech.ordinaryroad.live.chat.client.commons.base.listener.IDanmuMsgListener;
import tech.ordinaryroad.live.chat.client.commons.base.listener.IEnterRoomMsgListener;
import tech.ordinaryroad.live.chat.client.commons.base.listener.IGiftMsgListener;
import tech.ordinaryroad.live.chat.client.commons.base.listener.ILikeMsgListener;
import tech.ordinaryroad.live.chat.client.commons.base.listener.ILiveStatusChangeListener;
import tech.ordinaryroad.live.chat.client.commons.base.listener.IRoomStatsMsgListener;
import tech.ordinaryroad.live.chat.client.commons.base.listener.ISocialMsgListener;
import tech.ordinaryroad.live.chat.client.douyin.netty.handler.DouyinBinaryFrameHandler;

public interface IDouyinMsgListener extends IBaseMsgListener<DouyinBinaryFrameHandler, DouyinCmdEnum>, IDanmuMsgListener<DouyinBinaryFrameHandler, DouyinDanmuMsg>, IGiftMsgListener<DouyinBinaryFrameHandler, DouyinGiftMsg>, IEnterRoomMsgListener<DouyinBinaryFrameHandler, DouyinEnterRoomMsg>, ILikeMsgListener<DouyinBinaryFrameHandler, DouyinLikeMsg>, ILiveStatusChangeListener<DouyinBinaryFrameHandler, DouyinControlMsg>, IRoomStatsMsgListener<DouyinBinaryFrameHandler, DouyinRoomStatsMsg>, ISocialMsgListener<DouyinBinaryFrameHandler, DouyinSocialMsg> {}
