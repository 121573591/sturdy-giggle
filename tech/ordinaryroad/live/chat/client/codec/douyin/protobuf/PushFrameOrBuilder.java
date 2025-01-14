package tech.ordinaryroad.live.chat.client.codec.douyin.protobuf;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import java.util.List;

public interface PushFrameOrBuilder extends MessageOrBuilder {
  long getSeqId();
  
  long getLogId();
  
  long getService();
  
  long getMethod();
  
  List<HeadersList> getHeadersListList();
  
  HeadersList getHeadersList(int paramInt);
  
  int getHeadersListCount();
  
  List<? extends HeadersListOrBuilder> getHeadersListOrBuilderList();
  
  HeadersListOrBuilder getHeadersListOrBuilder(int paramInt);
  
  String getPayloadEncoding();
  
  ByteString getPayloadEncodingBytes();
  
  String getPayloadType();
  
  ByteString getPayloadTypeBytes();
  
  ByteString getPayload();
}
