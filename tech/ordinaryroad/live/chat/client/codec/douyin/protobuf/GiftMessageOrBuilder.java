package tech.ordinaryroad.live.chat.client.codec.douyin.protobuf;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import java.util.List;

public interface GiftMessageOrBuilder extends MessageOrBuilder {
  boolean hasCommon();
  
  Common getCommon();
  
  CommonOrBuilder getCommonOrBuilder();
  
  long getGiftId();
  
  long getFanTicketCount();
  
  long getGroupCount();
  
  long getRepeatCount();
  
  long getComboCount();
  
  boolean hasUser();
  
  User getUser();
  
  UserOrBuilder getUserOrBuilder();
  
  boolean hasToUser();
  
  User getToUser();
  
  UserOrBuilder getToUserOrBuilder();
  
  int getRepeatEnd();
  
  boolean hasTextEffect();
  
  TextEffect getTextEffect();
  
  TextEffectOrBuilder getTextEffectOrBuilder();
  
  long getGroupId();
  
  long getIncomeTaskgifts();
  
  long getRoomFanTicketCount();
  
  boolean hasPriority();
  
  GiftIMPriority getPriority();
  
  GiftIMPriorityOrBuilder getPriorityOrBuilder();
  
  boolean hasGift();
  
  GiftStruct getGift();
  
  GiftStructOrBuilder getGiftOrBuilder();
  
  String getLogId();
  
  ByteString getLogIdBytes();
  
  long getSendType();
  
  boolean hasPublicAreaCommon();
  
  PublicAreaCommon getPublicAreaCommon();
  
  PublicAreaCommonOrBuilder getPublicAreaCommonOrBuilder();
  
  boolean hasTrayDisplayText();
  
  Text getTrayDisplayText();
  
  TextOrBuilder getTrayDisplayTextOrBuilder();
  
  long getBannedDisplayEffects();
  
  boolean getDisplayForSelf();
  
  String getInteractGiftInfo();
  
  ByteString getInteractGiftInfoBytes();
  
  String getDiyItemInfo();
  
  ByteString getDiyItemInfoBytes();
  
  List<Long> getMinAssetSetListList();
  
  int getMinAssetSetListCount();
  
  long getMinAssetSetList(int paramInt);
  
  long getTotalCount();
  
  int getClientGiftSource();
  
  List<Long> getToUserIdsListList();
  
  int getToUserIdsListCount();
  
  long getToUserIdsList(int paramInt);
  
  long getSendTime();
  
  long getForceDisplayEffects();
  
  String getTraceId();
  
  ByteString getTraceIdBytes();
  
  long getEffectDisplayTs();
}
