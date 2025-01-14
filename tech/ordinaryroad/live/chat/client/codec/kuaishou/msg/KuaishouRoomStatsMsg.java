package tech.ordinaryroad.live.chat.client.codec.kuaishou.msg;

import tech.ordinaryroad.live.chat.client.codec.kuaishou.msg.base.IKuaishouMsg;
import tech.ordinaryroad.live.chat.client.commons.base.msg.IRoomStatsMsg;

public class KuaishouRoomStatsMsg implements IKuaishouMsg, IRoomStatsMsg {
  private String likedCount;
  
  private String watchingCount;
  
  public void setLikedCount(String likedCount) {
    this.likedCount = likedCount;
  }
  
  public void setWatchingCount(String watchingCount) {
    this.watchingCount = watchingCount;
  }
  
  public KuaishouRoomStatsMsg(String likedCount, String watchingCount) {
    this.likedCount = likedCount;
    this.watchingCount = watchingCount;
  }
  
  public KuaishouRoomStatsMsg() {}
  
  public String getLikedCount() {
    return this.likedCount;
  }
  
  public String getWatchingCount() {
    return this.watchingCount;
  }
}
