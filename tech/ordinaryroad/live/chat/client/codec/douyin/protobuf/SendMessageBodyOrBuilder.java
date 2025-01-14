package tech.ordinaryroad.live.chat.client.codec.douyin.protobuf;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import java.util.List;

public interface SendMessageBodyOrBuilder extends MessageOrBuilder {
  String getConversationId();
  
  ByteString getConversationIdBytes();
  
  int getConversationType();
  
  long getConversationShortId();
  
  String getContent();
  
  ByteString getContentBytes();
  
  List<ExtList> getExtList();
  
  ExtList getExt(int paramInt);
  
  int getExtCount();
  
  List<? extends ExtListOrBuilder> getExtOrBuilderList();
  
  ExtListOrBuilder getExtOrBuilder(int paramInt);
  
  int getMessageType();
  
  String getTicket();
  
  ByteString getTicketBytes();
  
  String getClientMessageId();
  
  ByteString getClientMessageIdBytes();
}
