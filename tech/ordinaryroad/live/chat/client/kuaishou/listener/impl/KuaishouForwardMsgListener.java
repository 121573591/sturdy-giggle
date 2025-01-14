package tech.ordinaryroad.live.chat.client.kuaishou.listener.impl;

import cn.hutool.core.util.StrUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import java.nio.charset.StandardCharsets;
import tech.ordinaryroad.live.chat.client.codec.kuaishou.msg.KuaishouDanmuMsg;
import tech.ordinaryroad.live.chat.client.codec.kuaishou.msg.KuaishouGiftMsg;
import tech.ordinaryroad.live.chat.client.codec.kuaishou.msg.KuaishouLikeMsg;
import tech.ordinaryroad.live.chat.client.commons.base.exception.BaseException;
import tech.ordinaryroad.live.chat.client.commons.base.msg.IMsg;
import tech.ordinaryroad.live.chat.client.kuaishou.listener.IKuaishouMsgListener;
import tech.ordinaryroad.live.chat.client.websocket.client.WebSocketLiveChatClient;
import tech.ordinaryroad.live.chat.client.websocket.config.WebSocketLiveChatClientConfig;

public class KuaishouForwardMsgListener implements IKuaishouMsgListener {
  private final WebSocketLiveChatClient webSocketLiveChatClient;
  
  public KuaishouForwardMsgListener(String webSocketUri) {
    if (StrUtil.isBlank(webSocketUri))
      throw new BaseException("转发地址不能为空"); 
    this
      
      .webSocketLiveChatClient = new WebSocketLiveChatClient(((WebSocketLiveChatClientConfig.WebSocketLiveChatClientConfigBuilder)WebSocketLiveChatClientConfig.builder().websocketUri(webSocketUri)).build());
    this.webSocketLiveChatClient.connect();
  }
  
  public void onDanmuMsg(KuaishouDanmuMsg msg) {
    forward((IMsg)msg);
  }
  
  public void onGiftMsg(KuaishouGiftMsg msg) {
    forward((IMsg)msg);
  }
  
  public void onLikeMsg(KuaishouLikeMsg msg) {
    forward((IMsg)msg);
  }
  
  private void forward(IMsg msg) {
    if (this.webSocketLiveChatClient == null)
      return; 
    ByteBuf byteBuf = ByteBufAllocator.DEFAULT.buffer();
    byteBuf.writeCharSequence(msg.toString(), StandardCharsets.UTF_8);
    this.webSocketLiveChatClient.send(new BinaryWebSocketFrame(byteBuf));
  }
  
  public void destroyForwardClient() {
    this.webSocketLiveChatClient.destroy();
  }
}
