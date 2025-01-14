package tech.ordinaryroad.live.chat.client.codec.douyin.protobuf;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import java.util.List;

public interface TextOrBuilder extends MessageOrBuilder {
  String getKey();
  
  ByteString getKeyBytes();
  
  String getDefaultPattern();
  
  ByteString getDefaultPatternBytes();
  
  boolean hasDefaultFormat();
  
  TextFormat getDefaultFormat();
  
  TextFormatOrBuilder getDefaultFormatOrBuilder();
  
  List<TextPiece> getPiecesList();
  
  TextPiece getPieces(int paramInt);
  
  int getPiecesCount();
  
  List<? extends TextPieceOrBuilder> getPiecesOrBuilderList();
  
  TextPieceOrBuilder getPiecesOrBuilder(int paramInt);
}
