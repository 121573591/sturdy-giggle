package tech.ordinaryroad.live.chat.client.codec.douyin.msg;

import cn.hutool.core.collection.CollUtil;
import tech.ordinaryroad.live.chat.client.codec.douyin.msg.base.IDouyinMsg;
import tech.ordinaryroad.live.chat.client.codec.douyin.protobuf.LikeMessage;
import tech.ordinaryroad.live.chat.client.commons.base.msg.ILikeMsg;

public class DouyinLikeMsg implements IDouyinMsg, ILikeMsg {
  private LikeMessage msg;
  
  public void setMsg(LikeMessage msg) {
    this.msg = msg;
  }
  
  public DouyinLikeMsg(LikeMessage msg) {
    this.msg = msg;
  }
  
  public DouyinLikeMsg() {}
  
  public LikeMessage getMsg() {
    return this.msg;
  }
  
  public String getBadgeName() {
    return this.msg.getUser().getFansClub().getData().getClubName();
  }
  
  public byte getBadgeLevel() {
    return (byte)this.msg.getUser().getFansClub().getData().getLevel();
  }
  
  public String getUid() {
    return Long.toString(this.msg.getUser().getId());
  }
  
  public String getUsername() {
    return this.msg.getUser().getNickName();
  }
  
  public String getUserAvatar() {
    return (String)CollUtil.getFirst((Iterable)this.msg.getUser().getAvatarThumb().getUrlListListList());
  }
  
  public int getClickCount() {
    return (int)this.msg.getCount();
  }
  
  public String toString() {
    return this.msg.toString();
  }
}
