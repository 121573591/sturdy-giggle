package tech.ordinaryroad.live.chat.client.tiktok.netty.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import tech.ordinaryroad.live.chat.client.servers.netty.client.base.BaseNettyClient;
import tech.ordinaryroad.live.chat.client.servers.netty.client.handler.BaseNettyClientChannelInitializer;
import tech.ordinaryroad.live.chat.client.tiktok.client.TiktokLiveChatClient;

public class TiktokLiveChatClientChannelInitializer extends BaseNettyClientChannelInitializer<TiktokLiveChatClient> {
  public TiktokLiveChatClientChannelInitializer(TiktokLiveChatClient client) {
    super((BaseNettyClient)client);
  }
  
  protected void initChannel(SocketChannel ch) throws Exception {
    ChannelPipeline pipeline = ch.pipeline();
    pipeline.addLast(new ChannelHandler[] { (ChannelHandler)new TiktokCodecHandler() });
    pipeline.addLast(new ChannelHandler[] { (ChannelHandler)new TiktokBinaryFrameHandler(((TiktokLiveChatClient)this.client).getMsgListeners(), (TiktokLiveChatClient)this.client) });
  }
}
