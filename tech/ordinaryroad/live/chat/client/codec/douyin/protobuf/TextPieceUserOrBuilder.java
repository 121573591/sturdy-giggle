package tech.ordinaryroad.live.chat.client.codec.douyin.protobuf;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

public interface TextPieceUserOrBuilder extends MessageOrBuilder {
  boolean hasUser();
  
  User getUser();
  
  UserOrBuilder getUserOrBuilder();
  
  boolean getWithColon();
  
  boolean getSelfShowRealName();
  
  int getLeftShowExtension();
  
  String getLeftAdditionalContent();
  
  ByteString getLeftAdditionalContentBytes();
  
  String getRightAdditionalContent();
  
  ByteString getRightAdditionalContentBytes();
}
