package tech.ordinaryroad.live.chat.client.commons.base.listener;

public interface IDanmuMsgListener<T, DanmuMsg> {
  default void onDanmuMsg(T t, DanmuMsg msg) {
    onDanmuMsg(msg);
  }
  
  default void onDanmuMsg(DanmuMsg msg) {}
}
