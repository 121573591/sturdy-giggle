package tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

public interface SocialMessageOrBuilder extends MessageOrBuilder {
  boolean hasCommon();
  
  Common getCommon();
  
  CommonOrBuilder getCommonOrBuilder();
  
  boolean hasUser();
  
  User getUser();
  
  UserOrBuilder getUserOrBuilder();
  
  long getShareType();
  
  long getAction();
  
  String getShareTarget();
  
  ByteString getShareTargetBytes();
  
  long getFollowCount();
}
