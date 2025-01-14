package tech.ordinaryroad.live.chat.client.codec.douyin.protobuf;

import com.google.protobuf.MessageOrBuilder;
import java.util.List;

public interface RanklistHourEntrance_InfoOrBuilder extends MessageOrBuilder {
  List<RanklistHourEntrance_Detail> getDetailsList();
  
  RanklistHourEntrance_Detail getDetails(int paramInt);
  
  int getDetailsCount();
  
  List<? extends RanklistHourEntrance_DetailOrBuilder> getDetailsOrBuilderList();
  
  RanklistHourEntrance_DetailOrBuilder getDetailsOrBuilder(int paramInt);
}
