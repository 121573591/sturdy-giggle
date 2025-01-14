package tech.ordinaryroad.live.chat.client.commons.base.listener;

public interface IRoomStatsMsgListener<T, RoomStatsMsg> {
  default void onRoomStatsMsg(T t, RoomStatsMsg msg) {
    onRoomStatsMsg(msg);
  }
  
  default void onRoomStatsMsg(RoomStatsMsg msg) {}
}
