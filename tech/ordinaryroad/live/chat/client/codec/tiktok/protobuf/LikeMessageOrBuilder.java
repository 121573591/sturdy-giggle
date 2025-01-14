package tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

public interface LikeMessageOrBuilder extends MessageOrBuilder {
  boolean hasCommon();
  
  Common getCommon();
  
  CommonOrBuilder getCommonOrBuilder();
  
  long getCount();
  
  long getTotal();
  
  boolean hasUser();
  
  User getUser();
  
  UserOrBuilder getUserOrBuilder();
  
  String getIcon();
  
  ByteString getIconBytes();
  
  boolean hasSuffixText();
  
  SuffixText getSuffixText();
  
  SuffixTextOrBuilder getSuffixTextOrBuilder();
  
  long getLinkmicGuestUid();
}
