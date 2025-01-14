package tech.ordinaryroad.live.chat.client.codec.douyin.protobuf;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import java.util.List;

public interface NinePatchSettingOrBuilder extends MessageOrBuilder {
  List<String> getSettingListListList();
  
  int getSettingListListCount();
  
  String getSettingListList(int paramInt);
  
  ByteString getSettingListListBytes(int paramInt);
}
