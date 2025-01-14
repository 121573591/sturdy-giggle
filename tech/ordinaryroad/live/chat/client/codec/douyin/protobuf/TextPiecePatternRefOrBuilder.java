package tech.ordinaryroad.live.chat.client.codec.douyin.protobuf;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

public interface TextPiecePatternRefOrBuilder extends MessageOrBuilder {
  String getKey();
  
  ByteString getKeyBytes();
  
  String getDefaultPattern();
  
  ByteString getDefaultPatternBytes();
}
