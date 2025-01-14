package tech.ordinaryroad.live.chat.client.codec.douyin.msg;

import cn.hutool.core.util.NumberUtil;
import tech.ordinaryroad.live.chat.client.codec.douyin.msg.base.IDouyinMsg;
import tech.ordinaryroad.live.chat.client.codec.douyin.protobuf.RoomStatsMessage;
import tech.ordinaryroad.live.chat.client.commons.base.msg.IRoomStatsMsg;

public class DouyinRoomStatsMsg implements IDouyinMsg, IRoomStatsMsg {
  private String likedCount;
  
  private RoomStatsMessage msg;
  
  public void setLikedCount(String likedCount) {
    this.likedCount = likedCount;
  }
  
  public void setMsg(RoomStatsMessage msg) {
    this.msg = msg;
  }
  
  public DouyinRoomStatsMsg(String likedCount, RoomStatsMessage msg) {
    this.likedCount = likedCount;
    this.msg = msg;
  }
  
  public DouyinRoomStatsMsg() {}
  
  public RoomStatsMessage getMsg() {
    return this.msg;
  }
  
  public String getLikedCount() {
    return this.likedCount;
  }
  
  public String getWatchingCount() {
    if (this.msg != null)
      return NumberUtil.toStr(Long.valueOf(this.msg.getTotal())); 
    return null;
  }
}
