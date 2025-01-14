package tech.ordinaryroad.live.chat.client.codec.tiktok.msg;

import cn.hutool.core.collection.CollUtil;
import tech.ordinaryroad.live.chat.client.codec.tiktok.msg.base.ITiktokMsg;
import tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.SocialMessage;
import tech.ordinaryroad.live.chat.client.commons.base.constant.SocialActionEnum;
import tech.ordinaryroad.live.chat.client.commons.base.msg.ISocialMsg;

public class TiktokSocialMsg implements ITiktokMsg, ISocialMsg {
  private SocialMessage msg;
  
  public void setMsg(SocialMessage msg) {
    this.msg = msg;
  }
  
  public TiktokSocialMsg(SocialMessage msg) {
    this.msg = msg;
  }
  
  public TiktokSocialMsg() {}
  
  public SocialMessage getMsg() {
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
  
  public SocialActionEnum getSocialAction() {
    if (this.msg == null)
      return null; 
    if (this.msg.getAction() == 1L)
      return SocialActionEnum.SUBSCRIBE; 
    if (this.msg.getAction() == 3L)
      return SocialActionEnum.SHARE; 
    return null;
  }
}
