package tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf;

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
  
  long getChannelId();
  
  long getDiffSei2AbsSecond();
  
  long getAnchorFoldDuration();
  
  long getAppId();
  
  long getTimestampMs();
}
