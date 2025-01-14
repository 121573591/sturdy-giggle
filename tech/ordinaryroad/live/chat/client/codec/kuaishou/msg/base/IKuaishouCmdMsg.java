package tech.ordinaryroad.live.chat.client.codec.kuaishou.msg.base;

import tech.ordinaryroad.live.chat.client.codec.kuaishou.protobuf.PayloadTypeOuterClass;
import tech.ordinaryroad.live.chat.client.commons.base.msg.ICmdMsg;

public interface IKuaishouCmdMsg extends IKuaishouMsg, ICmdMsg<PayloadTypeOuterClass.PayloadType> {}
