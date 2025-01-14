package tech.ordinaryroad.live.chat.client.codec.douyin.protobuf;

import com.google.protobuf.MessageOrBuilder;
import java.util.List;

public interface RanklistHourEntranceOrBuilder extends MessageOrBuilder {
  List<RanklistHourEntrance_Info> getGlobalInfosList();
  
  RanklistHourEntrance_Info getGlobalInfos(int paramInt);
  
  int getGlobalInfosCount();
  
  List<? extends RanklistHourEntrance_InfoOrBuilder> getGlobalInfosOrBuilderList();
  
  RanklistHourEntrance_InfoOrBuilder getGlobalInfosOrBuilder(int paramInt);
  
  List<RanklistHourEntrance_Info> getDefaultGlobalInfosList();
  
  RanklistHourEntrance_Info getDefaultGlobalInfos(int paramInt);
  
  int getDefaultGlobalInfosCount();
  
  List<? extends RanklistHourEntrance_InfoOrBuilder> getDefaultGlobalInfosOrBuilderList();
  
  RanklistHourEntrance_InfoOrBuilder getDefaultGlobalInfosOrBuilder(int paramInt);
  
  List<RanklistHourEntrance_Info> getVerticalInfosList();
  
  RanklistHourEntrance_Info getVerticalInfos(int paramInt);
  
  int getVerticalInfosCount();
  
  List<? extends RanklistHourEntrance_InfoOrBuilder> getVerticalInfosOrBuilderList();
  
  RanklistHourEntrance_InfoOrBuilder getVerticalInfosOrBuilder(int paramInt);
  
  List<RanklistHourEntrance_Info> getDefaultVerticalInfosList();
  
  RanklistHourEntrance_Info getDefaultVerticalInfos(int paramInt);
  
  int getDefaultVerticalInfosCount();
  
  List<? extends RanklistHourEntrance_InfoOrBuilder> getDefaultVerticalInfosOrBuilderList();
  
  RanklistHourEntrance_InfoOrBuilder getDefaultVerticalInfosOrBuilder(int paramInt);
}
