package tech.ordinaryroad.live.chat.client.codec.douyin.msg;

import cn.hutool.core.collection.CollUtil;
import tech.ordinaryroad.live.chat.client.codec.douyin.msg.base.IDouyinMsg;
import tech.ordinaryroad.live.chat.client.codec.douyin.protobuf.ChatMessage;
import tech.ordinaryroad.live.chat.client.commons.base.msg.IDanmuMsg;

public class DouyinDanmuMsg implements IDouyinMsg, IDanmuMsg {
  private ChatMessage msg;
  
  public void setMsg(ChatMessage msg) {
    this.msg = msg;
  }
  
  public DouyinDanmuMsg(ChatMessage msg) {
    this.msg = msg;
  }
  
  public DouyinDanmuMsg() {}
  
  public ChatMessage getMsg() {
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
  
  public String getContent() {
    return this.msg.getContent();
  }
  
  public String toString() {
    return this.msg.toString();
  }
}
