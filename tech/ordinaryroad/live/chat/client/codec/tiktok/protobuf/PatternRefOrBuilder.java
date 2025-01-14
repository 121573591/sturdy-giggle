package tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

public interface PatternRefOrBuilder extends MessageOrBuilder {
  String getKey();
  
  ByteString getKeyBytes();
  
  String getDefaultPattern();
  
  ByteString getDefaultPatternBytes();
}
