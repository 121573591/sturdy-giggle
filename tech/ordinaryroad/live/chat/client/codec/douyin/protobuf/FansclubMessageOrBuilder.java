package tech.ordinaryroad.live.chat.client.codec.douyin.protobuf;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

public interface FansclubMessageOrBuilder extends MessageOrBuilder {
  boolean hasCommonInfo();
  
  Common getCommonInfo();
  
  CommonOrBuilder getCommonInfoOrBuilder();
  
  int getType();
  
  String getContent();
  
  ByteString getContentBytes();
  
  boolean hasUser();
  
  User getUser();
  
  UserOrBuilder getUserOrBuilder();
}
