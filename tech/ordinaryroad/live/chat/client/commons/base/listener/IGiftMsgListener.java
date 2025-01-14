package tech.ordinaryroad.live.chat.client.commons.base.listener;

public interface IGiftMsgListener<T, GiftMsg> {
  default void onGiftMsg(T t, GiftMsg msg) {
    onGiftMsg(msg);
  }
  
  default void onGiftMsg(GiftMsg msg) {}
}
