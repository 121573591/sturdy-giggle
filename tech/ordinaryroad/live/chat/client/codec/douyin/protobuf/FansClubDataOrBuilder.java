package tech.ordinaryroad.live.chat.client.codec.douyin.protobuf;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import java.util.List;

public interface FansClubDataOrBuilder extends MessageOrBuilder {
  String getClubName();
  
  ByteString getClubNameBytes();
  
  int getLevel();
  
  int getUserFansClubStatus();
  
  boolean hasBadge();
  
  UserBadge getBadge();
  
  UserBadgeOrBuilder getBadgeOrBuilder();
  
  List<Long> getAvailableGiftIdsList();
  
  int getAvailableGiftIdsCount();
  
  long getAvailableGiftIds(int paramInt);
  
  long getAnchorId();
}
