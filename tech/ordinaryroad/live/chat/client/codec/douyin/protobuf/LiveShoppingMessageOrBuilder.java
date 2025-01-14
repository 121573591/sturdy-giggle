package tech.ordinaryroad.live.chat.client.codec.douyin.protobuf;

import com.google.protobuf.MessageOrBuilder;

public interface LiveShoppingMessageOrBuilder extends MessageOrBuilder {
  boolean hasCommon();
  
  Common getCommon();
  
  CommonOrBuilder getCommonOrBuilder();
  
  int getMsgType();
  
  long getPromotionId();
}
