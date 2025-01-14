package tech.ordinaryroad.live.chat.client.kuaishou.listener;

import tech.ordinaryroad.live.chat.client.codec.kuaishou.msg.KuaishouDanmuMsg;
import tech.ordinaryroad.live.chat.client.codec.kuaishou.msg.KuaishouGiftMsg;
import tech.ordinaryroad.live.chat.client.codec.kuaishou.msg.KuaishouLikeMsg;
import tech.ordinaryroad.live.chat.client.codec.kuaishou.msg.KuaishouRoomStatsMsg;
import tech.ordinaryroad.live.chat.client.codec.kuaishou.protobuf.PayloadTypeOuterClass;
import tech.ordinaryroad.live.chat.client.commons.base.listener.IBaseMsgListener;
import tech.ordinaryroad.live.chat.client.commons.base.listener.IDanmuMsgListener;
import tech.ordinaryroad.live.chat.client.commons.base.listener.IGiftMsgListener;
import tech.ordinaryroad.live.chat.client.commons.base.listener.ILikeMsgListener;
import tech.ordinaryroad.live.chat.client.commons.base.listener.IRoomStatsMsgListener;
import tech.ordinaryroad.live.chat.client.kuaishou.netty.handler.KuaishouBinaryFrameHandler;

public interface IKuaishouMsgListener extends IBaseMsgListener<KuaishouBinaryFrameHandler, PayloadTypeOuterClass.PayloadType>, IDanmuMsgListener<KuaishouBinaryFrameHandler, KuaishouDanmuMsg>, IGiftMsgListener<KuaishouBinaryFrameHandler, KuaishouGiftMsg>, ILikeMsgListener<KuaishouBinaryFrameHandler, KuaishouLikeMsg>, IRoomStatsMsgListener<KuaishouBinaryFrameHandler, KuaishouRoomStatsMsg> {}
