package tech.ordinaryroad.live.chat.client.codec.douyin.protobuf;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

public interface ChatReplyRespInfoOrBuilder extends MessageOrBuilder {
  long getReplyMsgId();
  
  long getReplyId();
  
  boolean hasReplyText();
  
  Text getReplyText();
  
  TextOrBuilder getReplyTextOrBuilder();
  
  long getReplyUid();
  
  String getReplyWebcastUid();
  
  ByteString getReplyWebcastUidBytes();
}
