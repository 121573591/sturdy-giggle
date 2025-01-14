package tech.ordinaryroad.live.chat.client.commons.base.msg;

public interface IGiftMsg extends IMsg {
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
  
  String getGiftName();
  
  String getGiftImg();
  
  String getGiftId();
  
  int getGiftCount();
  
  int getGiftPrice();
  
  String getReceiveUid();
  
  String getReceiveUsername();
}
