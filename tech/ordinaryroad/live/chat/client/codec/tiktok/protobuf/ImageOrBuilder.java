package tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import java.util.List;

public interface ImageOrBuilder extends MessageOrBuilder {
  List<String> getUrlListListList();
  
  int getUrlListListCount();
  
  String getUrlListList(int paramInt);
  
  ByteString getUrlListListBytes(int paramInt);
  
  String getUri();
  
  ByteString getUriBytes();
  
  String getAvgColor();
  
  ByteString getAvgColorBytes();
  
  int getImageType();
  
  String getOpenWebUrl();
  
  ByteString getOpenWebUrlBytes();
  
  boolean hasContent();
  
  ImageContent getContent();
  
  ImageContentOrBuilder getContentOrBuilder();
  
  boolean getIsAnimated();
}
