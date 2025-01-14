package tech.ordinaryroad.live.chat.client.codec.tiktok.msg;

import cn.hutool.core.collection.CollUtil;
import tech.ordinaryroad.live.chat.client.codec.tiktok.msg.base.ITiktokMsg;
import tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.MemberMessage;
import tech.ordinaryroad.live.chat.client.commons.base.msg.IEnterRoomMsg;

public class TiktokEnterRoomMsg implements ITiktokMsg, IEnterRoomMsg {
  private MemberMessage msg;
  
  public void setMsg(MemberMessage msg) {
    this.msg = msg;
  }
  
  public TiktokEnterRoomMsg(MemberMessage msg) {
    this.msg = msg;
  }
  
  public TiktokEnterRoomMsg() {}
  
  public MemberMessage getMsg() {
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
  
  public String toString() {
    return this.msg.toString();
  }
}
