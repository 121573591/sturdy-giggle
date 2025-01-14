package tech.ordinaryroad.live.chat.client.websocket.listener;

import tech.ordinaryroad.live.chat.client.commons.base.listener.IBaseMsgListener;
import tech.ordinaryroad.live.chat.client.websocket.constant.WebSocketCmdEnum;
import tech.ordinaryroad.live.chat.client.websocket.netty.handler.WebSocketBinaryFrameHandler;

public interface IWebSocketMsgListener extends IBaseMsgListener<WebSocketBinaryFrameHandler, WebSocketCmdEnum> {}
