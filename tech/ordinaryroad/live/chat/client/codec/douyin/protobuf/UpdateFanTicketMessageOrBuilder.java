package tech.ordinaryroad.live.chat.client.codec.douyin.protobuf;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

public interface UpdateFanTicketMessageOrBuilder extends MessageOrBuilder {
  boolean hasCommon();
  
  Common getCommon();
  
  CommonOrBuilder getCommonOrBuilder();
  
  String getRoomFanTicketCountText();
  
  ByteString getRoomFanTicketCountTextBytes();
  
  long getRoomFanTicketCount();
  
  boolean getForceUpdate();
}
