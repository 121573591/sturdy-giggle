package tech.ordinaryroad.live.chat.client.codec.douyin.protobuf;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import java.util.List;

public interface EpisodeChatMessageOrBuilder extends MessageOrBuilder {
  boolean hasCommon();
  
  Message getCommon();
  
  MessageOrBuilder getCommonOrBuilder();
  
  boolean hasUser();
  
  User getUser();
  
  UserOrBuilder getUserOrBuilder();
  
  String getContent();
  
  ByteString getContentBytes();
  
  boolean getVisibleToSende();
  
  boolean hasGiftImage();
  
  Image getGiftImage();
  
  ImageOrBuilder getGiftImageOrBuilder();
  
  long getAgreeMsgId();
  
  List<String> getColorValueListList();
  
  int getColorValueListCount();
  
  String getColorValueList(int paramInt);
  
  ByteString getColorValueListBytes(int paramInt);
}
