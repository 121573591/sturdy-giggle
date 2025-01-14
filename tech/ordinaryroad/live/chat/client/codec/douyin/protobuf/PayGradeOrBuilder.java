package tech.ordinaryroad.live.chat.client.codec.douyin.protobuf;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import java.util.List;

public interface PayGradeOrBuilder extends MessageOrBuilder {
  long getTotalDiamondCount();
  
  boolean hasDiamondIcon();
  
  Image getDiamondIcon();
  
  ImageOrBuilder getDiamondIconOrBuilder();
  
  String getName();
  
  ByteString getNameBytes();
  
  boolean hasIcon();
  
  Image getIcon();
  
  ImageOrBuilder getIconOrBuilder();
  
  String getNextName();
  
  ByteString getNextNameBytes();
  
  long getLevel();
  
  boolean hasNextIcon();
  
  Image getNextIcon();
  
  ImageOrBuilder getNextIconOrBuilder();
  
  long getNextDiamond();
  
  long getNowDiamond();
  
  long getThisGradeMinDiamond();
  
  long getThisGradeMaxDiamond();
  
  long getPayDiamondBak();
  
  String getGradeDescribe();
  
  ByteString getGradeDescribeBytes();
  
  List<GradeIcon> getGradeIconListList();
  
  GradeIcon getGradeIconList(int paramInt);
  
  int getGradeIconListCount();
  
  List<? extends GradeIconOrBuilder> getGradeIconListOrBuilderList();
  
  GradeIconOrBuilder getGradeIconListOrBuilder(int paramInt);
  
  long getScreenChatType();
  
  boolean hasImIcon();
  
  Image getImIcon();
  
  ImageOrBuilder getImIconOrBuilder();
  
  boolean hasImIconWithLevel();
  
  Image getImIconWithLevel();
  
  ImageOrBuilder getImIconWithLevelOrBuilder();
  
  boolean hasLiveIcon();
  
  Image getLiveIcon();
  
  ImageOrBuilder getLiveIconOrBuilder();
  
  boolean hasNewImIconWithLevel();
  
  Image getNewImIconWithLevel();
  
  ImageOrBuilder getNewImIconWithLevelOrBuilder();
  
  boolean hasNewLiveIcon();
  
  Image getNewLiveIcon();
  
  ImageOrBuilder getNewLiveIconOrBuilder();
  
  long getUpgradeNeedConsume();
  
  String getNextPrivileges();
  
  ByteString getNextPrivilegesBytes();
  
  boolean hasBackground();
  
  Image getBackground();
  
  ImageOrBuilder getBackgroundOrBuilder();
  
  boolean hasBackgroundBack();
  
  Image getBackgroundBack();
  
  ImageOrBuilder getBackgroundBackOrBuilder();
  
  long getScore();
  
  boolean hasBuffInfo();
  
  GradeBuffInfo getBuffInfo();
  
  GradeBuffInfoOrBuilder getBuffInfoOrBuilder();
  
  String getGradeBanner();
  
  ByteString getGradeBannerBytes();
  
  boolean hasProfileDialogBg();
  
  Image getProfileDialogBg();
  
  ImageOrBuilder getProfileDialogBgOrBuilder();
  
  boolean hasProfileDialogBgBack();
  
  Image getProfileDialogBgBack();
  
  ImageOrBuilder getProfileDialogBgBackOrBuilder();
}
