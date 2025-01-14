package tech.ordinaryroad.live.chat.client.codec.douyin.protobuf;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

public interface CommonOrBuilder extends MessageOrBuilder {
  String getMethod();
  
  ByteString getMethodBytes();
  
  long getMsgId();
  
  long getRoomId();
  
  long getCreateTime();
  
  int getMonitor();
  
  boolean getIsShowMsg();
  
  String getDescribe();
  
  ByteString getDescribeBytes();
  
  boolean hasDisplayText();
  
  Text getDisplayText();
  
  TextOrBuilder getDisplayTextOrBuilder();
  
  long getFoldType();
  
  long getAnchorFoldType();
  
  long getPriorityScore();
  
  String getLogId();
  
  ByteString getLogIdBytes();
  
  String getMsgProcessFilterK();
  
  ByteString getMsgProcessFilterKBytes();
  
  String getMsgProcessFilterV();
  
  ByteString getMsgProcessFilterVBytes();
  
  boolean hasUser();
  
  User getUser();
  
  UserOrBuilder getUserOrBuilder();
  
  long getAnchorFoldTypeV2();
  
  long getProcessAtSeiTimeMs();
  
  long getRandomDispatchMs();
  
  boolean getIsDispatch();
  
  long getChannelId();
  
  long getDiffSei2AbsSecond();
  
  long getAnchorFoldDuration();
  
  long getAppId();
}
