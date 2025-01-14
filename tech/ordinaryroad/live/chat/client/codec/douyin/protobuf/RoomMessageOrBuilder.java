package tech.ordinaryroad.live.chat.client.codec.douyin.protobuf;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import java.util.Map;

public interface RoomMessageOrBuilder extends MessageOrBuilder {
  boolean hasCommon();
  
  Common getCommon();
  
  CommonOrBuilder getCommonOrBuilder();
  
  String getContent();
  
  ByteString getContentBytes();
  
  boolean getSupprotLandscape();
  
  int getRoommessagetypeValue();
  
  RoomMsgTypeEnum getRoommessagetype();
  
  boolean getSystemTopMsg();
  
  boolean getForcedGuarantee();
  
  String getBizScene();
  
  ByteString getBizSceneBytes();
  
  int getBuriedPointMapCount();
  
  boolean containsBuriedPointMap(String paramString);
  
  @Deprecated
  Map<String, String> getBuriedPointMap();
  
  Map<String, String> getBuriedPointMapMap();
  
  String getBuriedPointMapOrDefault(String paramString1, String paramString2);
  
  String getBuriedPointMapOrThrow(String paramString);
}
