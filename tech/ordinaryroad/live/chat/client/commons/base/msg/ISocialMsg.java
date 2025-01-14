package tech.ordinaryroad.live.chat.client.commons.base.msg;

import tech.ordinaryroad.live.chat.client.commons.base.constant.SocialActionEnum;

public interface ISocialMsg extends IMsg {
  String getBadgeName();
  
  byte getBadgeLevel();
  
  String getUid();
  
  String getUsername();
  
  default String getUserAvatar() {
    return null;
  }
  
  SocialActionEnum getSocialAction();
}
