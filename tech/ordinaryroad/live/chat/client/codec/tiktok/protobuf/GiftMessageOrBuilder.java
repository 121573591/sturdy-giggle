package tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

public interface GiftMessageOrBuilder extends MessageOrBuilder {
  boolean hasCommon();
  
  Common getCommon();
  
  CommonOrBuilder getCommonOrBuilder();
  
  long getGiftId();
  
  long getFanTicketCount();
  
  long getGroupCount();
  
  long getRepeatCount();
  
  long getComboCount();
  
  boolean hasUser();
  
  User getUser();
  
  UserOrBuilder getUserOrBuilder();
  
  boolean hasToUser();
  
  User getToUser();
  
  UserOrBuilder getToUserOrBuilder();
  
  int getRepeatEnd();
  
  long getGroupId();
  
  long getRoomFanTicketCount();
  
  boolean hasGift();
  
  GiftStruct getGift();
  
  GiftStructOrBuilder getGiftOrBuilder();
  
  String getLogId();
  
  ByteString getLogIdBytes();
  
  long getSendType();
  
  boolean hasTrayDisplayText();
  
  Text getTrayDisplayText();
  
  TextOrBuilder getTrayDisplayTextOrBuilder();
  
  long getForceDisplayEffects();
}
