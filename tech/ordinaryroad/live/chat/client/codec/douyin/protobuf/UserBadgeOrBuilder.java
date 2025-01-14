package tech.ordinaryroad.live.chat.client.codec.douyin.protobuf;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import java.util.Map;

public interface UserBadgeOrBuilder extends MessageOrBuilder {
  int getIconsCount();
  
  boolean containsIcons(int paramInt);
  
  @Deprecated
  Map<Integer, Image> getIcons();
  
  Map<Integer, Image> getIconsMap();
  
  Image getIconsOrDefault(int paramInt, Image paramImage);
  
  Image getIconsOrThrow(int paramInt);
  
  String getTitle();
  
  ByteString getTitleBytes();
}
