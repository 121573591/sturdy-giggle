package tech.ordinaryroad.live.chat.client.commons.base.listener;

public interface IEnterRoomMsgListener<T, EnterRoomMsg> {
  default void onEnterRoomMsg(T t, EnterRoomMsg msg) {
    onEnterRoomMsg(msg);
  }
  
  default void onEnterRoomMsg(EnterRoomMsg msg) {}
}
