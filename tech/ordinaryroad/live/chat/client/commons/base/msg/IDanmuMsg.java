package tech.ordinaryroad.live.chat.client.commons.base.msg;

public interface IDanmuMsg extends IMsg {
  String getBadgeName();
  
  byte getBadgeLevel();
  
  String getUid();
  
  String getUsername();
  
  default String getUserAvatar() {
    return null;
  }
  
  String getContent();
}
