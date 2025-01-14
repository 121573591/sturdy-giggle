package tech.ordinaryroad.live.chat.client.codec.douyin.protobuf;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

public interface AgainstOrBuilder extends MessageOrBuilder {
  String getLeftName();
  
  ByteString getLeftNameBytes();
  
  boolean hasLeftLogo();
  
  Image getLeftLogo();
  
  ImageOrBuilder getLeftLogoOrBuilder();
  
  String getLeftGoal();
  
  ByteString getLeftGoalBytes();
  
  String getRightName();
  
  ByteString getRightNameBytes();
  
  boolean hasRightLogo();
  
  Image getRightLogo();
  
  ImageOrBuilder getRightLogoOrBuilder();
  
  String getRightGoal();
  
  ByteString getRightGoalBytes();
  
  long getTimestamp();
  
  long getVersion();
  
  long getLeftTeamId();
  
  long getRightTeamId();
  
  long getDiffSei2AbsSecond();
  
  int getFinalGoalStage();
  
  int getCurrentGoalStage();
  
  int getLeftScoreAddition();
  
  int getRightScoreAddition();
  
  long getLeftGoalInt();
  
  long getRightGoalInt();
}
