package tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

public interface GiftStructOrBuilder extends MessageOrBuilder {
  boolean hasImage();
  
  Image getImage();
  
  ImageOrBuilder getImageOrBuilder();
  
  String getDescribe();
  
  ByteString getDescribeBytes();
  
  long getDuration();
  
  long getId();
  
  boolean getForLinkmic();
  
  boolean getCombo();
  
  int getType();
  
  int getDiamondCount();
  
  boolean getIsDisplayedOnPanel();
  
  String getName();
  
  ByteString getNameBytes();
  
  boolean hasIcon();
  
  Image getIcon();
  
  ImageOrBuilder getIconOrBuilder();
}
