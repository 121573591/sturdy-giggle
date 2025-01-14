package tech.ordinaryroad.live.chat.client.codec.douyin.protobuf;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

public interface TextPieceHeartOrBuilder extends MessageOrBuilder {
  String getColor();
  
  ByteString getColorBytes();
}
