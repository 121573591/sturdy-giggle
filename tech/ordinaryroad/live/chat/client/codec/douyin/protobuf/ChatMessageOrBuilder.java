package tech.ordinaryroad.live.chat.client.codec.douyin.protobuf;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

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
  
  long getAgreeMsgId();
  
  int getPriorityLevel();
  
  boolean hasLandscapeAreaCommon();
  
  LandscapeAreaCommon getLandscapeAreaCommon();
  
  LandscapeAreaCommonOrBuilder getLandscapeAreaCommonOrBuilder();
  
  long getEventTime();
  
  boolean getSendReview();
  
  boolean getFromIntercom();
  
  boolean getIntercomHideUserCard();
  
  String getChatBy();
  
  ByteString getChatByBytes();
  
  int getIndividualChatPriority();
  
  boolean hasRtfContent();
  
  Text getRtfContent();
  
  TextOrBuilder getRtfContentOrBuilder();
}
