package tech.ordinaryroad.live.chat.client.douyin.listener.impl;

import cn.hutool.core.util.StrUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import java.nio.charset.StandardCharsets;
import tech.ordinaryroad.live.chat.client.codec.douyin.msg.DouyinControlMsg;
import tech.ordinaryroad.live.chat.client.codec.douyin.msg.DouyinDanmuMsg;
import tech.ordinaryroad.live.chat.client.codec.douyin.msg.DouyinEnterRoomMsg;
import tech.ordinaryroad.live.chat.client.codec.douyin.msg.DouyinGiftMsg;
import tech.ordinaryroad.live.chat.client.codec.douyin.msg.DouyinLikeMsg;
import tech.ordinaryroad.live.chat.client.codec.douyin.msg.DouyinRoomStatsMsg;
import tech.ordinaryroad.live.chat.client.codec.douyin.msg.DouyinSocialMsg;
import tech.ordinaryroad.live.chat.client.commons.base.exception.BaseException;
import tech.ordinaryroad.live.chat.client.commons.base.msg.IMsg;
import tech.ordinaryroad.live.chat.client.douyin.listener.IDouyinMsgListener;
import tech.ordinaryroad.live.chat.client.websocket.client.WebSocketLiveChatClient;
import tech.ordinaryroad.live.chat.client.websocket.config.WebSocketLiveChatClientConfig;

public class DouyinForwardMsgListener implements IDouyinMsgListener {
  private final WebSocketLiveChatClient webSocketLiveChatClient;
  
  public DouyinForwardMsgListener(String webSocketUri) {
    if (StrUtil.isBlank(webSocketUri))
      throw new BaseException("转发地址不能为空"); 
    this
      
      .webSocketLiveChatClient = new WebSocketLiveChatClient(((WebSocketLiveChatClientConfig.WebSocketLiveChatClientConfigBuilder)WebSocketLiveChatClientConfig.builder().websocketUri(webSocketUri)).build());
    this.webSocketLiveChatClient.connect();
  }
  
  public void onDanmuMsg(DouyinDanmuMsg msg) {
    forward((IMsg)msg);
  }
  
  public void onGiftMsg(DouyinGiftMsg msg) {
    forward((IMsg)msg);
  }
  
  public void onEnterRoomMsg(DouyinEnterRoomMsg msg) {
    forward((IMsg)msg);
  }
  
  public void onLikeMsg(DouyinLikeMsg msg) {
    forward((IMsg)msg);
  }
  
  public void onLiveStatusMsg(DouyinControlMsg msg) {
    forward((IMsg)msg);
  }
  
  public void onRoomStatsMsg(DouyinRoomStatsMsg msg) {
    forward((IMsg)msg);
  }
  
  public void onSocialMsg(DouyinSocialMsg msg) {
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
