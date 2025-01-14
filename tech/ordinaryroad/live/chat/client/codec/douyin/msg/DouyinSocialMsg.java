package tech.ordinaryroad.live.chat.client.codec.douyin.msg;

import cn.hutool.core.collection.CollUtil;
import tech.ordinaryroad.live.chat.client.codec.douyin.msg.base.IDouyinMsg;
import tech.ordinaryroad.live.chat.client.codec.douyin.protobuf.SocialMessage;
import tech.ordinaryroad.live.chat.client.commons.base.constant.SocialActionEnum;
import tech.ordinaryroad.live.chat.client.commons.base.msg.ISocialMsg;

public class DouyinSocialMsg implements IDouyinMsg, ISocialMsg {
  private SocialMessage msg;
  
  public void setMsg(SocialMessage msg) {
    this.msg = msg;
  }
  
  public DouyinSocialMsg(SocialMessage msg) {
    this.msg = msg;
  }
  
  public DouyinSocialMsg() {}
  
  public SocialMessage getMsg() {
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
