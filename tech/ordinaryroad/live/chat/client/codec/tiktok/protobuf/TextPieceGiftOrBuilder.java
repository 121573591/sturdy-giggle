package tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf;

import com.google.protobuf.MessageOrBuilder;

public interface TextPieceGiftOrBuilder extends MessageOrBuilder {
  long getGiftId();
  
  boolean hasNameRef();
  
  PatternRef getNameRef();
  
  PatternRefOrBuilder getNameRefOrBuilder();
}
