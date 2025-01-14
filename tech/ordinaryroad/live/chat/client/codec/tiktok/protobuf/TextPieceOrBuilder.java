package tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf;

import com.google.protobuf.MessageOrBuilder;

public interface TextPieceOrBuilder extends MessageOrBuilder {
  int getType();
  
  boolean hasFormat();
  
  TextFormat getFormat();
  
  TextFormatOrBuilder getFormatOrBuilder();
  
  boolean hasUservalue();
  
  TextPieceUser getUservalue();
  
  TextPieceUserOrBuilder getUservalueOrBuilder();
  
  boolean hasGiftvalue();
  
  TextPieceGift getGiftvalue();
  
  TextPieceGiftOrBuilder getGiftvalueOrBuilder();
}
