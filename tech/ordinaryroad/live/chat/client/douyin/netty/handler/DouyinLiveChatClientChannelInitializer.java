package tech.ordinaryroad.live.chat.client.douyin.netty.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import tech.ordinaryroad.live.chat.client.douyin.client.DouyinLiveChatClient;
import tech.ordinaryroad.live.chat.client.servers.netty.client.base.BaseNettyClient;
import tech.ordinaryroad.live.chat.client.servers.netty.client.handler.BaseNettyClientChannelInitializer;

public class DouyinLiveChatClientChannelInitializer extends BaseNettyClientChannelInitializer<DouyinLiveChatClient> {
  public DouyinLiveChatClientChannelInitializer(DouyinLiveChatClient client) {
    super((BaseNettyClient)client);
  }
  
  protected void initChannel(SocketChannel ch) throws Exception {
    ChannelPipeline pipeline = ch.pipeline();
    pipeline.addLast(new ChannelHandler[] { (ChannelHandler)new DouyinCodecHandler() });
    pipeline.addLast(new ChannelHandler[] { (ChannelHandler)new DouyinBinaryFrameHandler(((DouyinLiveChatClient)this.client).getMsgListeners(), (DouyinLiveChatClient)this.client) });
  }
}
