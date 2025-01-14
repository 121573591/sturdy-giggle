package tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf;

import com.google.protobuf.MessageOrBuilder;

public interface MemberMessageOrBuilder extends MessageOrBuilder {
  boolean hasCommon();
  
  Common getCommon();
  
  CommonOrBuilder getCommonOrBuilder();
  
  boolean hasUser();
  
  User getUser();
  
  UserOrBuilder getUserOrBuilder();
  
  long getMemberCount();
  
  long getAction();
  
  boolean hasAnchorDisplayText();
  
  Text getAnchorDisplayText();
  
  TextOrBuilder getAnchorDisplayTextOrBuilder();
}
