package tech.ordinaryroad.live.chat.client.codec.douyin.protobuf;

import com.google.protobuf.MessageOrBuilder;

public interface MatchAgainstScoreMessageOrBuilder extends MessageOrBuilder {
  boolean hasCommon();
  
  Common getCommon();
  
  CommonOrBuilder getCommonOrBuilder();
  
  boolean hasAgainst();
  
  Against getAgainst();
  
  AgainstOrBuilder getAgainstOrBuilder();
  
  int getMatchStatus();
  
  int getDisplayStatus();
}
