package tech.ordinaryroad.live.chat.client.codec.douyin.protobuf;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

public interface ImageContentOrBuilder extends MessageOrBuilder {
  String getName();
  
  ByteString getNameBytes();
  
  String getFontColor();
  
  ByteString getFontColorBytes();
  
  long getLevel();
  
  String getAlternativeText();
  
  ByteString getAlternativeTextBytes();
}
