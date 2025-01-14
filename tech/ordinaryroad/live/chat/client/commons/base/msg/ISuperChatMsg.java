package tech.ordinaryroad.live.chat.client.commons.base.msg;

public interface ISuperChatMsg extends IDanmuMsg {
  int getDuration();
  
  default String getBadgeName() {
    return "";
  }
  
  default byte getBadgeLevel() {
    return 0;
  }
}
