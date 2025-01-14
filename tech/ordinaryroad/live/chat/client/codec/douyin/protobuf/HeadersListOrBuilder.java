package tech.ordinaryroad.live.chat.client.codec.douyin.protobuf;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

public interface HeadersListOrBuilder extends MessageOrBuilder {
  String getKey();
  
  ByteString getKeyBytes();
  
  String getValue();
  
  ByteString getValueBytes();
}
