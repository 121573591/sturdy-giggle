package tech.ordinaryroad.live.chat.client.codec.douyin.protobuf;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import java.util.List;

public interface LandscapeAreaCommonOrBuilder extends MessageOrBuilder {
  boolean getShowHead();
  
  boolean getShowNickname();
  
  boolean getShowFontColor();
  
  List<String> getColorValueListList();
  
  int getColorValueListCount();
  
  String getColorValueList(int paramInt);
  
  ByteString getColorValueListBytes(int paramInt);
  
  List<CommentTypeTag> getCommentTypeTagsListList();
  
  int getCommentTypeTagsListCount();
  
  CommentTypeTag getCommentTypeTagsList(int paramInt);
  
  List<Integer> getCommentTypeTagsListValueList();
  
  int getCommentTypeTagsListValue(int paramInt);
}
