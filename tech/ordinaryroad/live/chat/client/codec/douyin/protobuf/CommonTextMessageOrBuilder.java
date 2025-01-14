package tech.ordinaryroad.live.chat.client.codec.douyin.protobuf;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

public interface CommonTextMessageOrBuilder extends MessageOrBuilder {
  boolean hasCommon();
  
  Common getCommon();
  
  CommonOrBuilder getCommonOrBuilder();
  
  boolean hasUser();
  
  User getUser();
  
  UserOrBuilder getUserOrBuilder();
  
  String getScene();
  
  ByteString getSceneBytes();
}
