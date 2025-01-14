package tech.ordinaryroad.live.chat.client.codec.douyin.protobuf;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

public interface RoomUserSeqMessageContributorOrBuilder extends MessageOrBuilder {
  long getScore();
  
  boolean hasUser();
  
  User getUser();
  
  UserOrBuilder getUserOrBuilder();
  
  long getRank();
  
  long getDelta();
  
  boolean getIsHidden();
  
  String getScoreDescription();
  
  ByteString getScoreDescriptionBytes();
  
  String getExactlyScore();
  
  ByteString getExactlyScoreBytes();
}
