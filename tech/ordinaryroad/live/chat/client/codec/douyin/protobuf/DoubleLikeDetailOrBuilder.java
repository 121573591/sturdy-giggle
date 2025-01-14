package tech.ordinaryroad.live.chat.client.codec.douyin.protobuf;

import com.google.protobuf.MessageOrBuilder;

public interface DoubleLikeDetailOrBuilder extends MessageOrBuilder {
  boolean getDoubleFlag();
  
  int getSeqId();
  
  int getRenewalsNum();
  
  int getTriggersNum();
}
