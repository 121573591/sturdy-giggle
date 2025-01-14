package tech.ordinaryroad.live.chat.client.commons.base.listener;

public interface ILiveStatusChangeListener<T, LiveStatusMsg> {
  default void onLiveStatusMsg(T t, LiveStatusMsg msg) {
    onLiveStatusMsg(msg);
  }
  
  default void onLiveStatusMsg(LiveStatusMsg msg) {}
}
