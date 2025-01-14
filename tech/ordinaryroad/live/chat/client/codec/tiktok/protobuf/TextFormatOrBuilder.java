package tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

public interface TextFormatOrBuilder extends MessageOrBuilder {
  String getColor();
  
  ByteString getColorBytes();
  
  int getWeight();
}
