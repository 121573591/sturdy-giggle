package tech.ordinaryroad.live.chat.client.codec.douyin.protobuf;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import java.util.List;

public interface RanklistHourEntrance_DetailOrBuilder extends MessageOrBuilder {
  List<RanklistHourEntrance_Page> getPagesList();
  
  RanklistHourEntrance_Page getPages(int paramInt);
  
  int getPagesCount();
  
  List<? extends RanklistHourEntrance_PageOrBuilder> getPagesOrBuilderList();
  
  RanklistHourEntrance_PageOrBuilder getPagesOrBuilder(int paramInt);
  
  int getRanklistType();
  
  String getTitle();
  
  ByteString getTitleBytes();
  
  String getRanklistExtra();
  
  ByteString getRanklistExtraBytes();
  
  String getEntranceExtra();
  
  ByteString getEntranceExtraBytes();
  
  String getSchema();
  
  ByteString getSchemaBytes();
}
