package tech.ordinaryroad.live.chat.client.codec.douyin.protobuf;

import com.google.protobuf.MessageOrBuilder;

public interface SuffixTextOrBuilder extends MessageOrBuilder {
  long getBizType();
  
  boolean hasText();
  
  Text getText();
  
  TextOrBuilder getTextOrBuilder();
}
