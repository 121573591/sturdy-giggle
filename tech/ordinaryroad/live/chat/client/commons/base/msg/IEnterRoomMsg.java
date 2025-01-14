package tech.ordinaryroad.live.chat.client.commons.base.msg;

public interface IEnterRoomMsg extends IMsg {
  String getBadgeName();
  
  byte getBadgeLevel();
  
  String getUid();
  
  String getUsername();
  
  default String getUserAvatar() {
    return null;
  }
}
