// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: tiktok.proto

// Protobuf Java Version: 3.25.5
package tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf;

public interface LikeMessageOrBuilder extends
    // @@protoc_insertion_point(interface_extends:LikeMessage)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>.Common common = 1;</code>
   * @return Whether the common field is set.
   */
  boolean hasCommon();
  /**
   * <code>.Common common = 1;</code>
   * @return The common.
   */
  tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.Common getCommon();
  /**
   * <code>.Common common = 1;</code>
   */
  tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.CommonOrBuilder getCommonOrBuilder();

  /**
   * <code>uint64 count = 2;</code>
   * @return The count.
   */
  long getCount();

  /**
   * <code>uint64 total = 3;</code>
   * @return The total.
   */
  long getTotal();

  /**
   * <pre>
   *  uint64 color = 4;
   * </pre>
   *
   * <code>.User user = 5;</code>
   * @return Whether the user field is set.
   */
  boolean hasUser();
  /**
   * <pre>
   *  uint64 color = 4;
   * </pre>
   *
   * <code>.User user = 5;</code>
   * @return The user.
   */
  tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.User getUser();
  /**
   * <pre>
   *  uint64 color = 4;
   * </pre>
   *
   * <code>.User user = 5;</code>
   */
  tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.UserOrBuilder getUserOrBuilder();

  /**
   * <code>string icon = 6;</code>
   * @return The icon.
   */
  java.lang.String getIcon();
  /**
   * <code>string icon = 6;</code>
   * @return The bytes for icon.
   */
  com.google.protobuf.ByteString
      getIconBytes();

  /**
   * <pre>
   *  DoubleLikeDetail doubleLikeDetail = 7;
   * </pre>
   *
   * <code>.SuffixText suffix_text = 8;</code>
   * @return Whether the suffixText field is set.
   */
  boolean hasSuffixText();
  /**
   * <pre>
   *  DoubleLikeDetail doubleLikeDetail = 7;
   * </pre>
   *
   * <code>.SuffixText suffix_text = 8;</code>
   * @return The suffixText.
   */
  tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.SuffixText getSuffixText();
  /**
   * <pre>
   *  DoubleLikeDetail doubleLikeDetail = 7;
   * </pre>
   *
   * <code>.SuffixText suffix_text = 8;</code>
   */
  tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.SuffixTextOrBuilder getSuffixTextOrBuilder();

  /**
   * <code>uint64 linkmicGuestUid = 9;</code>
   * @return The linkmicGuestUid.
   */
  long getLinkmicGuestUid();
}
