// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: tiktok.proto

// Protobuf Java Version: 3.25.5
package tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf;

public interface RoomStatsMessageOrBuilder extends
    // @@protoc_insertion_point(interface_extends:RoomStatsMessage)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *  string displayShort = 2;
   *  string displayMiddle = 3;
   *  string displayLong = 4;
   *  int64  displayValue = 5;
   *  int64  displayVersion = 6;
   *  bool incremental = 7;
   *  bool isHidden = 8;
   *  int64 total = 9;
   *  int64 displayType = 10;
   * </pre>
   *
   * <code>.Common common = 1;</code>
   * @return Whether the common field is set.
   */
  boolean hasCommon();
  /**
   * <pre>
   *  string displayShort = 2;
   *  string displayMiddle = 3;
   *  string displayLong = 4;
   *  int64  displayValue = 5;
   *  int64  displayVersion = 6;
   *  bool incremental = 7;
   *  bool isHidden = 8;
   *  int64 total = 9;
   *  int64 displayType = 10;
   * </pre>
   *
   * <code>.Common common = 1;</code>
   * @return The common.
   */
  tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.Common getCommon();
  /**
   * <pre>
   *  string displayShort = 2;
   *  string displayMiddle = 3;
   *  string displayLong = 4;
   *  int64  displayValue = 5;
   *  int64  displayVersion = 6;
   *  bool incremental = 7;
   *  bool isHidden = 8;
   *  int64 total = 9;
   *  int64 displayType = 10;
   * </pre>
   *
   * <code>.Common common = 1;</code>
   */
  tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.CommonOrBuilder getCommonOrBuilder();
}
