package tech.ordinaryroad.live.chat.client.codec.douyin.protobuf;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

public interface MemberMessageOrBuilder extends MessageOrBuilder {
  boolean hasCommon();
  
  Common getCommon();
  
  CommonOrBuilder getCommonOrBuilder();
  
  boolean hasUser();
  
  User getUser();
  
  UserOrBuilder getUserOrBuilder();
  
  long getMemberCount();
  
  boolean hasOperator();
  
  User getOperator();
  
  UserOrBuilder getOperatorOrBuilder();
  
  boolean getIsSetToAdmin();
  
  boolean getIsTopUser();
  
  long getRankScore();
  
  long getTopUserNo();
  
  long getEnterType();
  
  long getAction();
  
  String getActionDescription();
  
  ByteString getActionDescriptionBytes();
  
  long getUserId();
  
  boolean hasEffectConfig();
  
  EffectConfig getEffectConfig();
  
  EffectConfigOrBuilder getEffectConfigOrBuilder();
  
  String getPopStr();
  
  ByteString getPopStrBytes();
  
  boolean hasEnterEffectConfig();
  
  EffectConfig getEnterEffectConfig();
  
  EffectConfigOrBuilder getEnterEffectConfigOrBuilder();
  
  boolean hasBackgroundImage();
  
  Image getBackgroundImage();
  
  ImageOrBuilder getBackgroundImageOrBuilder();
  
  boolean hasBackgroundImageV2();
  
  Image getBackgroundImageV2();
  
  ImageOrBuilder getBackgroundImageV2OrBuilder();
  
  boolean hasAnchorDisplayText();
  
  Text getAnchorDisplayText();
  
  TextOrBuilder getAnchorDisplayTextOrBuilder();
  
  boolean hasPublicAreaCommon();
  
  PublicAreaCommon getPublicAreaCommon();
  
  PublicAreaCommonOrBuilder getPublicAreaCommonOrBuilder();
  
  long getUserEnterTipType();
  
  long getAnchorEnterTipType();
}
