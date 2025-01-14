package tech.ordinaryroad.live.chat.client.commons.base.listener;

public interface ISuperChatMsgListener<T, SuperChatMsg> {
  default void onSuperChatMsg(T t, SuperChatMsg msg) {
    onSuperChatMsg(msg);
  }
  
  default void onSuperChatMsg(SuperChatMsg msg) {}
}
