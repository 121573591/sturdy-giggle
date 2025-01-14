package tech.ordinaryroad.live.chat.client.codec.douyin.protobuf;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import java.util.List;

public interface PreMessageOrBuilder extends MessageOrBuilder {
  int getCmd();
  
  int getSequenceId();
  
  String getSdkVersion();
  
  ByteString getSdkVersionBytes();
  
  String getToken();
  
  ByteString getTokenBytes();
  
  int getRefer();
  
  int getInboxType();
  
  String getBuildNumber();
  
  ByteString getBuildNumberBytes();
  
  boolean hasSendMessageBody();
  
  SendMessageBody getSendMessageBody();
  
  SendMessageBodyOrBuilder getSendMessageBodyOrBuilder();
  
  String getAa();
  
  ByteString getAaBytes();
  
  String getDevicePlatform();
  
  ByteString getDevicePlatformBytes();
  
  List<HeadersList> getHeadersList();
  
  HeadersList getHeaders(int paramInt);
  
  int getHeadersCount();
  
  List<? extends HeadersListOrBuilder> getHeadersOrBuilderList();
  
  HeadersListOrBuilder getHeadersOrBuilder(int paramInt);
  
  int getAuthType();
  
  String getBiz();
  
  ByteString getBizBytes();
  
  String getAccess();
  
  ByteString getAccessBytes();
}
