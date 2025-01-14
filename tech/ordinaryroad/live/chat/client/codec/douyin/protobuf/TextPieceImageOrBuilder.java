package tech.ordinaryroad.live.chat.client.codec.douyin.protobuf;

import com.google.protobuf.MessageOrBuilder;

public interface TextPieceImageOrBuilder extends MessageOrBuilder {
  boolean hasImage();
  
  Image getImage();
  
  ImageOrBuilder getImageOrBuilder();
  
  float getScalingRate();
}
