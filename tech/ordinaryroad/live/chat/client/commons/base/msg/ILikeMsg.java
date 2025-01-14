package tech.ordinaryroad.live.chat.client.commons.base.msg;

public interface ILikeMsg extends IMsg {
  default String getBadgeName() {
    return "";
  }
  
  default byte getBadgeLevel() {
    return 0;
  }
  
  String getUid();
  
  String getUsername();
  
  default String getUserAvatar() {
    return null;
  }
  
  default int getClickCount() {
    return 1;
  }
}
