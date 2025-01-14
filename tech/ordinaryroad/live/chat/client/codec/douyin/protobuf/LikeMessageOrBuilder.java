package tech.ordinaryroad.live.chat.client.codec.douyin.protobuf;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

public interface LikeMessageOrBuilder extends MessageOrBuilder {
  boolean hasCommon();
  
  Common getCommon();
  
  CommonOrBuilder getCommonOrBuilder();
  
  long getCount();
  
  long getTotal();
  
  long getColor();
  
  boolean hasUser();
  
  User getUser();
  
  UserOrBuilder getUserOrBuilder();
  
  String getIcon();
  
  ByteString getIconBytes();
  
  boolean hasDoubleLikeDetail();
  
  DoubleLikeDetail getDoubleLikeDetail();
  
  DoubleLikeDetailOrBuilder getDoubleLikeDetailOrBuilder();
  
  boolean hasDisplayControlInfo();
  
  DisplayControlInfo getDisplayControlInfo();
  
  DisplayControlInfoOrBuilder getDisplayControlInfoOrBuilder();
  
  long getLinkmicGuestUid();
  
  String getScene();
  
  ByteString getSceneBytes();
  
  boolean hasPicoDisplayInfo();
  
  PicoDisplayInfo getPicoDisplayInfo();
  
  PicoDisplayInfoOrBuilder getPicoDisplayInfoOrBuilder();
}
