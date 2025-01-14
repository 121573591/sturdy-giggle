package tech.ordinaryroad.live.chat.client.codec.douyin.protobuf;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

public interface TextPieceOrBuilder extends MessageOrBuilder {
  int getType();
  
  boolean hasFormat();
  
  TextFormat getFormat();
  
  TextFormatOrBuilder getFormatOrBuilder();
  
  String getValueRef();
  
  ByteString getValueRefBytes();
  
  String getStringValue();
  
  ByteString getStringValueBytes();
  
  boolean hasUservalue();
  
  TextPieceUser getUservalue();
  
  TextPieceUserOrBuilder getUservalueOrBuilder();
  
  boolean hasGiftvalue();
  
  TextPieceGift getGiftvalue();
  
  TextPieceGiftOrBuilder getGiftvalueOrBuilder();
  
  boolean hasHeartvalue();
  
  TextPieceHeart getHeartvalue();
  
  TextPieceHeartOrBuilder getHeartvalueOrBuilder();
  
  boolean hasPatternrefvalue();
  
  TextPiecePatternRef getPatternrefvalue();
  
  TextPiecePatternRefOrBuilder getPatternrefvalueOrBuilder();
  
  boolean hasImagevalue();
  
  TextPieceImage getImagevalue();
  
  TextPieceImageOrBuilder getImagevalueOrBuilder();
  
  String getSchemaKey();
  
  ByteString getSchemaKeyBytes();
}
