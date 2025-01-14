package tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import java.util.Map;

public interface ChatMessageOrBuilder extends MessageOrBuilder {
  boolean hasCommon();
  
  Common getCommon();
  
  CommonOrBuilder getCommonOrBuilder();
  
  boolean hasUser();
  
  User getUser();
  
  UserOrBuilder getUserOrBuilder();
  
  String getContent();
  
  ByteString getContentBytes();
  
  boolean getVisibleToSender();
  
  boolean hasBackgroundImage();
  
  Image getBackgroundImage();
  
  ImageOrBuilder getBackgroundImageOrBuilder();
  
  String getFullScreenTextColor();
  
  ByteString getFullScreenTextColorBytes();
  
  boolean hasBackgroundImageV2();
  
  Image getBackgroundImageV2();
  
  ImageOrBuilder getBackgroundImageV2OrBuilder();
  
  boolean hasPublicAreaCommon();
  
  PublicAreaCommon getPublicAreaCommon();
  
  PublicAreaCommonOrBuilder getPublicAreaCommonOrBuilder();
  
  boolean hasGiftImage();
  
  Image getGiftImage();
  
  ImageOrBuilder getGiftImageOrBuilder();
  
  String getLanguage();
  
  ByteString getLanguageBytes();
  
  int getChatTagsListCount();
  
  boolean containsChatTagsList(String paramString);
  
  @Deprecated
  Map<String, String> getChatTagsList();
  
  Map<String, String> getChatTagsListMap();
  
  String getChatTagsListOrDefault(String paramString1, String paramString2);
  
  String getChatTagsListOrThrow(String paramString);
}
