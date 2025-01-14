package tech.ordinaryroad.live.chat.client.codec.douyin.protobuf;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

public interface InRoomBannerMessageOrBuilder extends MessageOrBuilder {
  boolean hasCommon();
  
  Common getCommon();
  
  CommonOrBuilder getCommonOrBuilder();
  
  String getExtra();
  
  ByteString getExtraBytes();
  
  int getPosition();
  
  int getActionType();
  
  String getContainerUrl();
  
  ByteString getContainerUrlBytes();
  
  String getLynxContainerUrl();
  
  ByteString getLynxContainerUrlBytes();
  
  int getContainerType();
  
  int getOpType();
}
