package tech.ordinaryroad.live.chat.client.codec.douyin.protobuf;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

public interface GradeIconOrBuilder extends MessageOrBuilder {
  boolean hasIcon();
  
  Image getIcon();
  
  ImageOrBuilder getIconOrBuilder();
  
  long getIconDiamond();
  
  long getLevel();
  
  String getLevelStr();
  
  ByteString getLevelStrBytes();
}
