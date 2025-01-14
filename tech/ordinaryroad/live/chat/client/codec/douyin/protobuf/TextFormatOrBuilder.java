package tech.ordinaryroad.live.chat.client.codec.douyin.protobuf;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

public interface TextFormatOrBuilder extends MessageOrBuilder {
  String getColor();
  
  ByteString getColorBytes();
  
  boolean getBold();
  
  boolean getItalic();
  
  int getWeight();
  
  int getItalicAngle();
  
  int getFontSize();
  
  boolean getUseHeighLightColor();
  
  boolean getUseRemoteClor();
}
