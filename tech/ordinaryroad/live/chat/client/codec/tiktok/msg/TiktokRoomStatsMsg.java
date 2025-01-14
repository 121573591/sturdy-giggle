package tech.ordinaryroad.live.chat.client.codec.tiktok.msg;

import tech.ordinaryroad.live.chat.client.codec.tiktok.msg.base.ITiktokMsg;
import tech.ordinaryroad.live.chat.client.commons.base.msg.IRoomStatsMsg;

public class TiktokRoomStatsMsg implements ITiktokMsg, IRoomStatsMsg {
  private String likedCount;
  
  private String watchingCount;
  
  public void setLikedCount(String likedCount) {
    this.likedCount = likedCount;
  }
  
  public void setWatchingCount(String watchingCount) {
    this.watchingCount = watchingCount;
  }
  
  public TiktokRoomStatsMsg(String likedCount, String watchingCount) {
    this.likedCount = likedCount;
    this.watchingCount = watchingCount;
  }
  
  public TiktokRoomStatsMsg() {}
  
  public String getLikedCount() {
    return this.likedCount;
  }
  
  public String getWatchingCount() {
    return this.watchingCount;
  }
}
