package tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf;

import com.google.protobuf.MessageOrBuilder;

public interface TextPieceUserOrBuilder extends MessageOrBuilder {
  boolean hasUser();
  
  User getUser();
  
  UserOrBuilder getUserOrBuilder();
}
