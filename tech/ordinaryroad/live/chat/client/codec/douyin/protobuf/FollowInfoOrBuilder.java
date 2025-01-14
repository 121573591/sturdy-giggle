package tech.ordinaryroad.live.chat.client.codec.douyin.protobuf;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

public interface FollowInfoOrBuilder extends MessageOrBuilder {
  long getFollowingCount();
  
  long getFollowerCount();
  
  long getFollowStatus();
  
  long getPushStatus();
  
  String getRemarkName();
  
  ByteString getRemarkNameBytes();
  
  String getFollowerCountStr();
  
  ByteString getFollowerCountStrBytes();
  
  String getFollowingCountStr();
  
  ByteString getFollowingCountStrBytes();
}
