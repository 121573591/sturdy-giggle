package tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import java.util.List;

public interface UserOrBuilder extends MessageOrBuilder {
  long getId();
  
  String getNickName();
  
  ByteString getNickNameBytes();
  
  boolean hasAvatarThumb();
  
  Image getAvatarThumb();
  
  ImageOrBuilder getAvatarThumbOrBuilder();
  
  boolean hasAvatarMedium();
  
  Image getAvatarMedium();
  
  ImageOrBuilder getAvatarMediumOrBuilder();
  
  boolean hasAvatarLarge();
  
  Image getAvatarLarge();
  
  ImageOrBuilder getAvatarLargeOrBuilder();
  
  List<Image> getBadgeImageListList();
  
  Image getBadgeImageList(int paramInt);
  
  int getBadgeImageListCount();
  
  List<? extends ImageOrBuilder> getBadgeImageListOrBuilderList();
  
  ImageOrBuilder getBadgeImageListOrBuilder(int paramInt);
  
  boolean hasFollowInfo();
  
  FollowInfo getFollowInfo();
  
  FollowInfoOrBuilder getFollowInfoOrBuilder();
  
  boolean hasPayGrade();
  
  PayGrade getPayGrade();
  
  PayGradeOrBuilder getPayGradeOrBuilder();
  
  String getDisplayId();
  
  ByteString getDisplayIdBytes();
  
  String getSecUid();
  
  ByteString getSecUidBytes();
  
  String getIdStr();
  
  ByteString getIdStrBytes();
}
