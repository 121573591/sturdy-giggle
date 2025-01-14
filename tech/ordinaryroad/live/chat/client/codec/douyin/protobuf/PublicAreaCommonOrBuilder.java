package tech.ordinaryroad.live.chat.client.codec.douyin.protobuf;

import com.google.protobuf.MessageOrBuilder;

public interface PublicAreaCommonOrBuilder extends MessageOrBuilder {
  boolean hasUserLabel();
  
  Image getUserLabel();
  
  ImageOrBuilder getUserLabelOrBuilder();
  
  long getUserConsumeInRoom();
  
  long getUserSendGiftCntInRoom();
  
  long getIndividualPriority();
  
  long getSupportPin();
  
  boolean hasSuffixText();
  
  SuffixText getSuffixText();
  
  SuffixTextOrBuilder getSuffixTextOrBuilder();
  
  int getImAction();
  
  boolean getForbiddenProfile();
  
  boolean hasReplyResp();
  
  ChatReplyRespInfo getReplyResp();
  
  ChatReplyRespInfoOrBuilder getReplyRespOrBuilder();
  
  long getIsFeatured();
  
  boolean getNeedFilterDisplay();
}
