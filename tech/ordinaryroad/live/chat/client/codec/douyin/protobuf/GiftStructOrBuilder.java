package tech.ordinaryroad.live.chat.client.codec.douyin.protobuf;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

public interface GiftStructOrBuilder extends MessageOrBuilder {
  boolean hasImage();
  
  Image getImage();
  
  ImageOrBuilder getImageOrBuilder();
  
  String getDescribe();
  
  ByteString getDescribeBytes();
  
  boolean getNotify();
  
  long getDuration();
  
  long getId();
  
  boolean getForLinkmic();
  
  boolean getDoodle();
  
  boolean getForFansclub();
  
  boolean getCombo();
  
  int getType();
  
  int getDiamondCount();
  
  boolean getIsDisplayedOnPanel();
  
  long getPrimaryEffectId();
  
  boolean hasGiftLabelIcon();
  
  Image getGiftLabelIcon();
  
  ImageOrBuilder getGiftLabelIconOrBuilder();
  
  String getName();
  
  ByteString getNameBytes();
  
  String getRegion();
  
  ByteString getRegionBytes();
  
  String getManual();
  
  ByteString getManualBytes();
  
  boolean getForCustom();
  
  boolean hasIcon();
  
  Image getIcon();
  
  ImageOrBuilder getIconOrBuilder();
  
  int getActionType();
}
