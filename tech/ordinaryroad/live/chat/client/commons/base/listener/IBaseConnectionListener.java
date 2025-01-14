package tech.ordinaryroad.live.chat.client.commons.base.listener;

public interface IBaseConnectionListener<T> {
  default void onConnected(T t) {}
  
  default void onConnectFailed(T t) {}
  
  default void onDisconnected(T t) {}
}
