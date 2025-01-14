package tech.ordinaryroad.live.chat.client.codec.douyin.protobuf;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import java.util.List;
import java.util.Map;

public interface EffectConfigOrBuilder extends MessageOrBuilder {
  long getType();
  
  boolean hasIcon();
  
  Image getIcon();
  
  ImageOrBuilder getIconOrBuilder();
  
  long getAvatarPos();
  
  boolean hasText();
  
  Text getText();
  
  TextOrBuilder getTextOrBuilder();
  
  boolean hasTextIcon();
  
  Image getTextIcon();
  
  ImageOrBuilder getTextIconOrBuilder();
  
  int getStayTime();
  
  long getAnimAssetId();
  
  boolean hasBadge();
  
  Image getBadge();
  
  ImageOrBuilder getBadgeOrBuilder();
  
  List<Long> getFlexSettingArrayListList();
  
  int getFlexSettingArrayListCount();
  
  long getFlexSettingArrayList(int paramInt);
  
  boolean hasTextIconOverlay();
  
  Image getTextIconOverlay();
  
  ImageOrBuilder getTextIconOverlayOrBuilder();
  
  boolean hasAnimatedBadge();
  
  Image getAnimatedBadge();
  
  ImageOrBuilder getAnimatedBadgeOrBuilder();
  
  boolean getHasSweepLight();
  
  List<Long> getTextFlexSettingArrayListList();
  
  int getTextFlexSettingArrayListCount();
  
  long getTextFlexSettingArrayList(int paramInt);
  
  long getCenterAnimAssetId();
  
  boolean hasDynamicImage();
  
  Image getDynamicImage();
  
  ImageOrBuilder getDynamicImageOrBuilder();
  
  int getExtraMapCount();
  
  boolean containsExtraMap(String paramString);
  
  @Deprecated
  Map<String, String> getExtraMap();
  
  Map<String, String> getExtraMapMap();
  
  String getExtraMapOrDefault(String paramString1, String paramString2);
  
  String getExtraMapOrThrow(String paramString);
  
  long getMp4AnimAssetId();
  
  long getPriority();
  
  long getMaxWaitTime();
  
  String getDressId();
  
  ByteString getDressIdBytes();
  
  long getAlignment();
  
  long getAlignmentOffset();
}
