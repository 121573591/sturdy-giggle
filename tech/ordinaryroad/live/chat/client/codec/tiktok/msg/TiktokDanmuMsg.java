package tech.ordinaryroad.live.chat.client.codec.tiktok.msg;

import cn.hutool.core.collection.CollUtil;
import tech.ordinaryroad.live.chat.client.codec.tiktok.msg.base.ITiktokMsg;
import tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.ChatMessage;
import tech.ordinaryroad.live.chat.client.commons.base.msg.IDanmuMsg;

public class TiktokDanmuMsg implements ITiktokMsg, IDanmuMsg {
  private ChatMessage msg;
  
  public void setMsg(ChatMessage msg) {
    this.msg = msg;
  }
  
  public TiktokDanmuMsg(ChatMessage msg) {
    this.msg = msg;
  }
  
  public TiktokDanmuMsg() {}
  
  public ChatMessage getMsg() {
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
  
  public String getContent() {
    return this.msg.getContent();
  }
  
  public String toString() {
    return this.msg.toString();
  }
}
