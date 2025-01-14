package tech.ordinaryroad.live.chat.client.codec.douyin.protobuf;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

public interface PicoDisplayInfoOrBuilder extends MessageOrBuilder {
  long getComboSumCount();
  
  String getEmoji();
  
  ByteString getEmojiBytes();
  
  boolean hasEmojiIcon();
  
  Image getEmojiIcon();
  
  ImageOrBuilder getEmojiIconOrBuilder();
  
  String getEmojiText();
  
  ByteString getEmojiTextBytes();
}
