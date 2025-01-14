package tech.ordinaryroad.live.chat.client.codec.douyin.protobuf;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

public interface RanklistHourEntrance_PageOrBuilder extends MessageOrBuilder {
  String getContent();
  
  ByteString getContentBytes();
  
  String getBackgroundColor();
  
  ByteString getBackgroundColorBytes();
  
  long getShowTimes();
  
  int getContentType();
}
