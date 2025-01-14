package tech.ordinaryroad.live.chat.client.codec.douyin.protobuf;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

public interface TextEffectDetailOrBuilder extends MessageOrBuilder {
  boolean hasText();
  
  Text getText();
  
  TextOrBuilder getTextOrBuilder();
  
  int getTextFontSize();
  
  boolean hasBackground();
  
  Image getBackground();
  
  ImageOrBuilder getBackgroundOrBuilder();
  
  int getStart();
  
  int getDuration();
  
  int getX();
  
  int getY();
  
  int getWidth();
  
  int getHeight();
  
  int getShadowDx();
  
  int getShadowDy();
  
  int getShadowRadius();
  
  String getShadowColor();
  
  ByteString getShadowColorBytes();
  
  String getStrokeColor();
  
  ByteString getStrokeColorBytes();
  
  int getStrokeWidth();
}
