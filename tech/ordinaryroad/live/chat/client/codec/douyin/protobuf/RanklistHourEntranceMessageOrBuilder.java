package tech.ordinaryroad.live.chat.client.codec.douyin.protobuf;

import com.google.protobuf.MessageOrBuilder;

public interface RanklistHourEntranceMessageOrBuilder extends MessageOrBuilder {
  boolean hasCommon();
  
  Common getCommon();
  
  CommonOrBuilder getCommonOrBuilder();
  
  boolean hasInfo();
  
  RanklistHourEntrance getInfo();
  
  RanklistHourEntranceOrBuilder getInfoOrBuilder();
}
