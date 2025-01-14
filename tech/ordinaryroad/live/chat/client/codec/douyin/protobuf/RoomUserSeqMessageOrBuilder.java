package tech.ordinaryroad.live.chat.client.codec.douyin.protobuf;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import java.util.List;

public interface RoomUserSeqMessageOrBuilder extends MessageOrBuilder {
  boolean hasCommon();
  
  Common getCommon();
  
  CommonOrBuilder getCommonOrBuilder();
  
  List<RoomUserSeqMessageContributor> getRanksListList();
  
  RoomUserSeqMessageContributor getRanksList(int paramInt);
  
  int getRanksListCount();
  
  List<? extends RoomUserSeqMessageContributorOrBuilder> getRanksListOrBuilderList();
  
  RoomUserSeqMessageContributorOrBuilder getRanksListOrBuilder(int paramInt);
  
  long getTotal();
  
  String getPopStr();
  
  ByteString getPopStrBytes();
  
  List<RoomUserSeqMessageContributor> getSeatsListList();
  
  RoomUserSeqMessageContributor getSeatsList(int paramInt);
  
  int getSeatsListCount();
  
  List<? extends RoomUserSeqMessageContributorOrBuilder> getSeatsListOrBuilderList();
  
  RoomUserSeqMessageContributorOrBuilder getSeatsListOrBuilder(int paramInt);
  
  long getPopularity();
  
  long getTotalUser();
  
  String getTotalUserStr();
  
  ByteString getTotalUserStrBytes();
  
  String getTotalStr();
  
  ByteString getTotalStrBytes();
  
  String getOnlineUserForAnchor();
  
  ByteString getOnlineUserForAnchorBytes();
  
  String getTotalPvForAnchor();
  
  ByteString getTotalPvForAnchorBytes();
  
  String getUpRightStatsStr();
  
  ByteString getUpRightStatsStrBytes();
  
  String getUpRightStatsStrComplete();
  
  ByteString getUpRightStatsStrCompleteBytes();
}
