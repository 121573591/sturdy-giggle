// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: tiktok.proto

// Protobuf Java Version: 3.25.5
package tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf;

public interface TextPieceOrBuilder extends
    // @@protoc_insertion_point(interface_extends:TextPiece)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>uint32 type = 1;</code>
   * @return The type.
   */
  int getType();

  /**
   * <code>.TextFormat format = 2;</code>
   * @return Whether the format field is set.
   */
  boolean hasFormat();
  /**
   * <code>.TextFormat format = 2;</code>
   * @return The format.
   */
  tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.TextFormat getFormat();
  /**
   * <code>.TextFormat format = 2;</code>
   */
  tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.TextFormatOrBuilder getFormatOrBuilder();

  /**
   * <pre>
   *  string value_ref = 3;
   * </pre>
   *
   * <code>.TextPieceUser uservalue = 21;</code>
   * @return Whether the uservalue field is set.
   */
  boolean hasUservalue();
  /**
   * <pre>
   *  string value_ref = 3;
   * </pre>
   *
   * <code>.TextPieceUser uservalue = 21;</code>
   * @return The uservalue.
   */
  tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.TextPieceUser getUservalue();
  /**
   * <pre>
   *  string value_ref = 3;
   * </pre>
   *
   * <code>.TextPieceUser uservalue = 21;</code>
   */
  tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.TextPieceUserOrBuilder getUservalueOrBuilder();

  /**
   * <pre>
   *  TextPieceHeart heartvalue = 23;
   *  TextPiecePatternRef patternrefvalue = 24;
   *  TextPieceImage imagevalue = 25;
   *  string schema_key = 100;
   * </pre>
   *
   * <code>.TextPieceGift giftvalue = 22;</code>
   * @return Whether the giftvalue field is set.
   */
  boolean hasGiftvalue();
  /**
   * <pre>
   *  TextPieceHeart heartvalue = 23;
   *  TextPiecePatternRef patternrefvalue = 24;
   *  TextPieceImage imagevalue = 25;
   *  string schema_key = 100;
   * </pre>
   *
   * <code>.TextPieceGift giftvalue = 22;</code>
   * @return The giftvalue.
   */
  tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.TextPieceGift getGiftvalue();
  /**
   * <pre>
   *  TextPieceHeart heartvalue = 23;
   *  TextPiecePatternRef patternrefvalue = 24;
   *  TextPieceImage imagevalue = 25;
   *  string schema_key = 100;
   * </pre>
   *
   * <code>.TextPieceGift giftvalue = 22;</code>
   */
  tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.TextPieceGiftOrBuilder getGiftvalueOrBuilder();
}
