package tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import java.util.List;
import java.util.Map;

public interface ResponseOrBuilder extends MessageOrBuilder {
  List<Message> getMessagesListList();
  
  Message getMessagesList(int paramInt);
  
  int getMessagesListCount();
  
  List<? extends MessageOrBuilder> getMessagesListOrBuilderList();
  
  MessageOrBuilder getMessagesListOrBuilder(int paramInt);
  
  String getCursor();
  
  ByteString getCursorBytes();
  
  long getFetchInterval();
  
  long getNow();
  
  String getInternalExt();
  
  ByteString getInternalExtBytes();
  
  int getFetchType();
  
  int getRouteParamsCount();
  
  boolean containsRouteParams(String paramString);
  
  @Deprecated
  Map<String, String> getRouteParams();
  
  Map<String, String> getRouteParamsMap();
  
  String getRouteParamsOrDefault(String paramString1, String paramString2);
  
  String getRouteParamsOrThrow(String paramString);
  
  long getHeartbeatDuration();
  
  boolean getNeedAck();
  
  String getPushServer();
  
  ByteString getPushServerBytes();
  
  String getLiveCursor();
  
  ByteString getLiveCursorBytes();
}
