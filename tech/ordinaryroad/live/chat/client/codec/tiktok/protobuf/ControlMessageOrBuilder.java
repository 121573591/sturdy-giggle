package tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf;

import com.google.protobuf.MessageOrBuilder;

public interface ControlMessageOrBuilder extends MessageOrBuilder {
  boolean hasCommon();
  
  Common getCommon();
  
  CommonOrBuilder getCommonOrBuilder();
  
  int getStatus();
}
