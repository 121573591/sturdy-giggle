package tech.ordinaryroad.live.chat.client.codec.douyin.protobuf;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import java.util.List;

public interface UserOrBuilder extends MessageOrBuilder {
  long getId();
  
  long getShortId();
  
  String getNickName();
  
  ByteString getNickNameBytes();
  
  int getGender();
  
  String getSignature();
  
  ByteString getSignatureBytes();
  
  int getLevel();
  
  long getBirthday();
  
  String getTelephone();
  
  ByteString getTelephoneBytes();
  
  boolean hasAvatarThumb();
  
  Image getAvatarThumb();
  
  ImageOrBuilder getAvatarThumbOrBuilder();
  
  boolean hasAvatarMedium();
  
  Image getAvatarMedium();
  
  ImageOrBuilder getAvatarMediumOrBuilder();
  
  boolean hasAvatarLarge();
  
  Image getAvatarLarge();
  
  ImageOrBuilder getAvatarLargeOrBuilder();
  
  boolean getVerified();
  
  int getExperience();
  
  String getCity();
  
  ByteString getCityBytes();
  
  int getStatus();
  
  long getCreateTime();
  
  long getModifyTime();
  
  int getSecret();
  
  String getShareQrcodeUri();
  
  ByteString getShareQrcodeUriBytes();
  
  int getIncomeSharePercent();
  
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
  
  boolean hasFansClub();
  
  FansClub getFansClub();
  
  FansClubOrBuilder getFansClubOrBuilder();
  
  String getSpecialId();
  
  ByteString getSpecialIdBytes();
  
  boolean hasAvatarBorder();
  
  Image getAvatarBorder();
  
  ImageOrBuilder getAvatarBorderOrBuilder();
  
  boolean hasMedal();
  
  Image getMedal();
  
  ImageOrBuilder getMedalOrBuilder();
  
  List<Image> getRealTimeIconsListList();
  
  Image getRealTimeIconsList(int paramInt);
  
  int getRealTimeIconsListCount();
  
  List<? extends ImageOrBuilder> getRealTimeIconsListOrBuilderList();
  
  ImageOrBuilder getRealTimeIconsListOrBuilder(int paramInt);
  
  String getDisplayId();
  
  ByteString getDisplayIdBytes();
  
  String getSecUid();
  
  ByteString getSecUidBytes();
  
  long getFanTicketCount();
  
  String getIdStr();
  
  ByteString getIdStrBytes();
  
  int getAgeRange();
}
