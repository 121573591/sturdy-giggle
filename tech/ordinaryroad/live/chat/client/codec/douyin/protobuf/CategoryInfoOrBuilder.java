package tech.ordinaryroad.live.chat.client.codec.douyin.protobuf;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import java.util.List;

public interface CategoryInfoOrBuilder extends MessageOrBuilder {
  int getId();
  
  String getName();
  
  ByteString getNameBytes();
  
  List<Long> getPromotionIdsListList();
  
  int getPromotionIdsListCount();
  
  long getPromotionIdsList(int paramInt);
  
  String getType();
  
  ByteString getTypeBytes();
  
  String getUniqueIndex();
  
  ByteString getUniqueIndexBytes();
}
