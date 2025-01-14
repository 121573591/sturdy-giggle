package tech.ordinaryroad.live.chat.client.kuaishou.netty.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import tech.ordinaryroad.live.chat.client.kuaishou.client.KuaishouLiveChatClient;
import tech.ordinaryroad.live.chat.client.servers.netty.client.base.BaseNettyClient;
import tech.ordinaryroad.live.chat.client.servers.netty.client.handler.BaseNettyClientChannelInitializer;

public class KuaishouLiveChatClientChannelInitializer extends BaseNettyClientChannelInitializer<KuaishouLiveChatClient> {
  public KuaishouLiveChatClientChannelInitializer(KuaishouLiveChatClient client) {
    super((BaseNettyClient)client);
  }
  
  protected void initChannel(SocketChannel ch) throws Exception {
    ChannelPipeline pipeline = ch.pipeline();
    pipeline.addLast(new ChannelHandler[] { (ChannelHandler)new KuaishouCodecHandler() });
    pipeline.addLast(new ChannelHandler[] { (ChannelHandler)new KuaishouBinaryFrameHandler(((KuaishouLiveChatClient)this.client).getMsgListeners(), (KuaishouLiveChatClient)this.client) });
  }
}
