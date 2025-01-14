package tech.ordinaryroad.live.chat.client.codec.douyin.protobuf;

import com.google.protobuf.MessageOrBuilder;
import java.util.List;

public interface GiftIMPriorityOrBuilder extends MessageOrBuilder {
  List<Long> getQueueSizesListList();
  
  int getQueueSizesListCount();
  
  long getQueueSizesList(int paramInt);
  
  long getSelfQueuePriority();
  
  long getPriority();
}
