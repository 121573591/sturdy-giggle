package tech.ordinaryroad.live.chat.client.codec.douyin.protobuf;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

public interface MessageOrBuilder extends MessageOrBuilder {
  String getMethod();
  
  ByteString getMethodBytes();
  
  ByteString getPayload();
  
  long getMsgId();
  
  int getMsgType();
  
  long getOffset();
  
  boolean getNeedWrdsStore();
  
  long getWrdsVersion();
  
  String getWrdsSubKey();
  
  ByteString getWrdsSubKeyBytes();
}
