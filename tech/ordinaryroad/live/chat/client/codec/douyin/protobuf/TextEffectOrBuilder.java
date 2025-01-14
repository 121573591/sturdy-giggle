package tech.ordinaryroad.live.chat.client.codec.douyin.protobuf;

import com.google.protobuf.MessageOrBuilder;

public interface TextEffectOrBuilder extends MessageOrBuilder {
  boolean hasPortrait();
  
  TextEffectDetail getPortrait();
  
  TextEffectDetailOrBuilder getPortraitOrBuilder();
  
  boolean hasLandscape();
  
  TextEffectDetail getLandscape();
  
  TextEffectDetailOrBuilder getLandscapeOrBuilder();
}
