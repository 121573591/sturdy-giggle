package tech.ordinaryroad.live.chat.client.codec.tiktok.msg;

import cn.hutool.core.collection.CollUtil;
import tech.ordinaryroad.live.chat.client.codec.tiktok.msg.base.ITiktokMsg;
import tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.LikeMessage;
import tech.ordinaryroad.live.chat.client.commons.base.msg.ILikeMsg;

public class TiktokLikeMsg implements ITiktokMsg, ILikeMsg {
  private LikeMessage msg;
  
  public void setMsg(LikeMessage msg) {
    this.msg = msg;
  }
  
  public TiktokLikeMsg(LikeMessage msg) {
    this.msg = msg;
  }
  
  public TiktokLikeMsg() {}
  
  public LikeMessage getMsg() {
    return this.msg;
  }
  
  public String getBadgeName() {
    return "";
  }
  
  public byte getBadgeLevel() {
    return 0;
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
