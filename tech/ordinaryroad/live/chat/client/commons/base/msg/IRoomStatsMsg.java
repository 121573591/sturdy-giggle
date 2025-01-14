package tech.ordinaryroad.live.chat.client.commons.base.msg;

public interface IRoomStatsMsg extends IMsg {
  default String getLikedCount() {
    return null;
  }
  
  default String getWatchingCount() {
    return null;
  }
  
  default String getWatchedCount() {
    return null;
  }
}
