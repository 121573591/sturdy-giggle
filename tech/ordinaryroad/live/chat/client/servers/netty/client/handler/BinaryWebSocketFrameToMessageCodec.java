package tech.ordinaryroad.live.chat.client.servers.netty.client.handler;

import io.netty.handler.codec.MessageToMessageCodec;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import tech.ordinaryroad.live.chat.client.commons.base.msg.IMsg;

public abstract class BinaryWebSocketFrameToMessageCodec<MSG extends IMsg> extends MessageToMessageCodec<BinaryWebSocketFrame, MSG> {}
