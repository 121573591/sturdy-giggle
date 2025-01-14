package tech.ordinaryroad.live.chat.client.servers.netty.client.handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import tech.ordinaryroad.live.chat.client.servers.netty.client.base.BaseNettyClient;

public abstract class BaseNettyClientChannelInitializer<CLIENT extends BaseNettyClient<?, ?, ?, ?, ?, ?>> extends ChannelInitializer<SocketChannel> {
  protected final CLIENT client;
  
  public CLIENT getClient() {
    return this.client;
  }
  
  public BaseNettyClientChannelInitializer(CLIENT client) {
    this.client = client;
  }
}
