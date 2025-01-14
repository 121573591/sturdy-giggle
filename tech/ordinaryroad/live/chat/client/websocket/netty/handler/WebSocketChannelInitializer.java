package tech.ordinaryroad.live.chat.client.websocket.netty.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import tech.ordinaryroad.live.chat.client.servers.netty.client.base.BaseNettyClient;
import tech.ordinaryroad.live.chat.client.servers.netty.client.handler.BaseNettyClientChannelInitializer;
import tech.ordinaryroad.live.chat.client.websocket.client.WebSocketLiveChatClient;

public class WebSocketChannelInitializer extends BaseNettyClientChannelInitializer<WebSocketLiveChatClient> {
  public WebSocketChannelInitializer(WebSocketLiveChatClient client) {
    super((BaseNettyClient)client);
  }
  
  protected void initChannel(SocketChannel ch) throws Exception {
    ChannelPipeline pipeline = ch.pipeline();
    pipeline.addLast(new ChannelHandler[] { (ChannelHandler)new WebSocketCodecHandler() });
    pipeline.addLast(new ChannelHandler[] { (ChannelHandler)new WebSocketBinaryFrameHandler(((WebSocketLiveChatClient)this.client).getMsgListeners(), (WebSocketLiveChatClient)this.client) });
  }
}
