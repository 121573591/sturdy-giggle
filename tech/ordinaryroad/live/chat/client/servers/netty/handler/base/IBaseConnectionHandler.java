package tech.ordinaryroad.live.chat.client.servers.netty.handler.base;

import io.netty.channel.Channel;

public interface IBaseConnectionHandler {
  default void sendHeartbeat(Channel channel) {}
  
  default void sendAuthRequest(Channel channel) {}
}
