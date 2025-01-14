package tech.ordinaryroad.live.chat.client.codec.douyin.protobuf;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import java.util.List;

public interface ProductChangeMessageOrBuilder extends MessageOrBuilder {
  boolean hasCommon();
  
  Common getCommon();
  
  CommonOrBuilder getCommonOrBuilder();
  
  long getUpdateTimestamp();
  
  String getUpdateToast();
  
  ByteString getUpdateToastBytes();
  
  List<ProductInfo> getUpdateProductInfoListList();
  
  ProductInfo getUpdateProductInfoList(int paramInt);
  
  int getUpdateProductInfoListCount();
  
  List<? extends ProductInfoOrBuilder> getUpdateProductInfoListOrBuilderList();
  
  ProductInfoOrBuilder getUpdateProductInfoListOrBuilder(int paramInt);
  
  long getTotal();
  
  List<CategoryInfo> getUpdateCategoryInfoListList();
  
  CategoryInfo getUpdateCategoryInfoList(int paramInt);
  
  int getUpdateCategoryInfoListCount();
  
  List<? extends CategoryInfoOrBuilder> getUpdateCategoryInfoListOrBuilderList();
  
  CategoryInfoOrBuilder getUpdateCategoryInfoListOrBuilder(int paramInt);
}
