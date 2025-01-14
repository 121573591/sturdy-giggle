package tech.ordinaryroad.live.chat.client.codec.douyin.protobuf;

import com.google.protobuf.MessageOrBuilder;
import java.util.List;

public interface RoomRankMessageOrBuilder extends MessageOrBuilder {
  boolean hasCommon();
  
  Common getCommon();
  
  CommonOrBuilder getCommonOrBuilder();
  
  List<RoomRankMessage.RoomRank> getRanksListList();
  
  RoomRankMessage.RoomRank getRanksList(int paramInt);
  
  int getRanksListCount();
  
  List<? extends RoomRankMessage.RoomRankOrBuilder> getRanksListOrBuilderList();
  
  RoomRankMessage.RoomRankOrBuilder getRanksListOrBuilder(int paramInt);
}
