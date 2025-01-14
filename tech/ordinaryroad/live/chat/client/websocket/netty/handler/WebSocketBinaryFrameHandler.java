package tech.ordinaryroad.live.chat.client.websocket.netty.handler;

import io.netty.channel.ChannelHandler.Sharable;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.ordinaryroad.live.chat.client.servers.netty.client.base.BaseNettyClient;
import tech.ordinaryroad.live.chat.client.servers.netty.client.handler.BaseNettyClientBinaryFrameHandler;
import tech.ordinaryroad.live.chat.client.websocket.client.WebSocketLiveChatClient;
import tech.ordinaryroad.live.chat.client.websocket.constant.WebSocketCmdEnum;
import tech.ordinaryroad.live.chat.client.websocket.listener.IWebSocketMsgListener;
import tech.ordinaryroad.live.chat.client.websocket.msg.base.IWebSocketMsg;

@Sharable
public class WebSocketBinaryFrameHandler extends BaseNettyClientBinaryFrameHandler<WebSocketLiveChatClient, WebSocketBinaryFrameHandler, WebSocketCmdEnum, IWebSocketMsg, IWebSocketMsgListener> {
  private static final Logger log = LoggerFactory.getLogger(WebSocketBinaryFrameHandler.class);
  
  public WebSocketBinaryFrameHandler(List<IWebSocketMsgListener> msgListeners, WebSocketLiveChatClient client) {
    super(msgListeners, (BaseNettyClient)client);
  }
  
  public WebSocketBinaryFrameHandler(List<IWebSocketMsgListener> msgListeners, long roomId) {
    super(msgListeners, roomId);
  }
}
