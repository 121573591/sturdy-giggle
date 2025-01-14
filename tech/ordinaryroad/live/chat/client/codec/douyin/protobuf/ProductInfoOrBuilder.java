package tech.ordinaryroad.live.chat.client.codec.douyin.protobuf;

import com.google.protobuf.MessageOrBuilder;
import java.util.List;

public interface ProductInfoOrBuilder extends MessageOrBuilder {
  long getPromotionId();
  
  int getIndex();
  
  List<Long> getTargetFlashUidsListList();
  
  int getTargetFlashUidsListCount();
  
  long getTargetFlashUidsList(int paramInt);
  
  long getExplainType();
}
