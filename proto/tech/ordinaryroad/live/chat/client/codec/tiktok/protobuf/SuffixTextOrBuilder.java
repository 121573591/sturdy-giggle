// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: tiktok.proto

// Protobuf Java Version: 3.25.5
package tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf;

public interface SuffixTextOrBuilder extends
    // @@protoc_insertion_point(interface_extends:SuffixText)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>uint64 biz_type = 1;</code>
   * @return The bizType.
   */
  long getBizType();

  /**
   * <code>.Text text = 2;</code>
   * @return Whether the text field is set.
   */
  boolean hasText();
  /**
   * <code>.Text text = 2;</code>
   * @return The text.
   */
  tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.Text getText();
  /**
   * <code>.Text text = 2;</code>
   */
  tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.TextOrBuilder getTextOrBuilder();
}
