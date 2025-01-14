package tech.ordinaryroad.live.chat.client.codec.douyin.protobuf;

import com.google.protobuf.MessageOrBuilder;
import java.util.Map;

public interface FansClubOrBuilder extends MessageOrBuilder {
  boolean hasData();
  
  FansClubData getData();
  
  FansClubDataOrBuilder getDataOrBuilder();
  
  int getPreferDataCount();
  
  boolean containsPreferData(int paramInt);
  
  @Deprecated
  Map<Integer, FansClubData> getPreferData();
  
  Map<Integer, FansClubData> getPreferDataMap();
  
  FansClubData getPreferDataOrDefault(int paramInt, FansClubData paramFansClubData);
  
  FansClubData getPreferDataOrThrow(int paramInt);
}
