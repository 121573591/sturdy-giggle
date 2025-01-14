package tech.ordinaryroad.live.chat.client.codec.douyin.protobuf;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

public interface RoomStatsMessageOrBuilder extends MessageOrBuilder {
  boolean hasCommon();
  
  Common getCommon();
  
  CommonOrBuilder getCommonOrBuilder();
  
  String getDisplayShort();
  
  ByteString getDisplayShortBytes();
  
  String getDisplayMiddle();
  
  ByteString getDisplayMiddleBytes();
  
  String getDisplayLong();
  
  ByteString getDisplayLongBytes();
  
  long getDisplayValue();
  
  long getDisplayVersion();
  
  boolean getIncremental();
  
  boolean getIsHidden();
  
  long getTotal();
  
  long getDisplayType();
}
