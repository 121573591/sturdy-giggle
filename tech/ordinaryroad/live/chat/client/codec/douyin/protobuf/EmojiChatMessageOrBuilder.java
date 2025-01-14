package tech.ordinaryroad.live.chat.client.codec.douyin.protobuf;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

public interface EmojiChatMessageOrBuilder extends MessageOrBuilder {
  boolean hasCommon();
  
  Common getCommon();
  
  CommonOrBuilder getCommonOrBuilder();
  
  boolean hasUser();
  
  User getUser();
  
  UserOrBuilder getUserOrBuilder();
  
  long getEmojiId();
  
  boolean hasEmojiContent();
  
  Text getEmojiContent();
  
  TextOrBuilder getEmojiContentOrBuilder();
  
  String getDefaultContent();
  
  ByteString getDefaultContentBytes();
  
  boolean hasBackgroundImage();
  
  Image getBackgroundImage();
  
  ImageOrBuilder getBackgroundImageOrBuilder();
  
  boolean getFromIntercom();
  
  boolean getIntercomHideUserCard();
  
  boolean hasPublicAreaCommon();
  
  PublicAreaCommon getPublicAreaCommon();
  
  PublicAreaCommonOrBuilder getPublicAreaCommonOrBuilder();
}
