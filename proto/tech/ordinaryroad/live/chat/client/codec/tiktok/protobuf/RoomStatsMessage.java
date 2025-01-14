// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: tiktok.proto

// Protobuf Java Version: 3.25.5
package tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf;

/**
 * Protobuf type {@code RoomStatsMessage}
 */
public final class RoomStatsMessage extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:RoomStatsMessage)
    RoomStatsMessageOrBuilder {
private static final long serialVersionUID = 0L;
  // Use RoomStatsMessage.newBuilder() to construct.
  private RoomStatsMessage(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private RoomStatsMessage() {
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new RoomStatsMessage();
  }

  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.Tiktok.internal_static_RoomStatsMessage_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.Tiktok.internal_static_RoomStatsMessage_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.RoomStatsMessage.class, tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.RoomStatsMessage.Builder.class);
  }

  private int bitField0_;
  public static final int COMMON_FIELD_NUMBER = 1;
  private tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.Common common_;
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
  @java.lang.Override
  public boolean hasCommon() {
    return ((bitField0_ & 0x00000001) != 0);
  }
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
  @java.lang.Override
  public tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.Common getCommon() {
    return common_ == null ? tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.Common.getDefaultInstance() : common_;
  }
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
  @java.lang.Override
  public tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.CommonOrBuilder getCommonOrBuilder() {
    return common_ == null ? tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.Common.getDefaultInstance() : common_;
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) != 0)) {
      output.writeMessage(1, getCommon());
    }
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) != 0)) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, getCommon());
    }
    size += getUnknownFields().getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.RoomStatsMessage)) {
      return super.equals(obj);
    }
    tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.RoomStatsMessage other = (tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.RoomStatsMessage) obj;

    if (hasCommon() != other.hasCommon()) return false;
    if (hasCommon()) {
      if (!getCommon()
          .equals(other.getCommon())) return false;
    }
    if (!getUnknownFields().equals(other.getUnknownFields())) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    if (hasCommon()) {
      hash = (37 * hash) + COMMON_FIELD_NUMBER;
      hash = (53 * hash) + getCommon().hashCode();
    }
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.RoomStatsMessage parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.RoomStatsMessage parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.RoomStatsMessage parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.RoomStatsMessage parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.RoomStatsMessage parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.RoomStatsMessage parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.RoomStatsMessage parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.RoomStatsMessage parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public static tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.RoomStatsMessage parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }

  public static tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.RoomStatsMessage parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.RoomStatsMessage parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.RoomStatsMessage parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.RoomStatsMessage prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code RoomStatsMessage}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:RoomStatsMessage)
      tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.RoomStatsMessageOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.Tiktok.internal_static_RoomStatsMessage_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.Tiktok.internal_static_RoomStatsMessage_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.RoomStatsMessage.class, tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.RoomStatsMessage.Builder.class);
    }

    // Construct using tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.RoomStatsMessage.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
        getCommonFieldBuilder();
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      bitField0_ = 0;
      common_ = null;
      if (commonBuilder_ != null) {
        commonBuilder_.dispose();
        commonBuilder_ = null;
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.Tiktok.internal_static_RoomStatsMessage_descriptor;
    }

    @java.lang.Override
    public tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.RoomStatsMessage getDefaultInstanceForType() {
      return tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.RoomStatsMessage.getDefaultInstance();
    }

    @java.lang.Override
    public tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.RoomStatsMessage build() {
      tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.RoomStatsMessage result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.RoomStatsMessage buildPartial() {
      tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.RoomStatsMessage result = new tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.RoomStatsMessage(this);
      if (bitField0_ != 0) { buildPartial0(result); }
      onBuilt();
      return result;
    }

    private void buildPartial0(tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.RoomStatsMessage result) {
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.common_ = commonBuilder_ == null
            ? common_
            : commonBuilder_.build();
        to_bitField0_ |= 0x00000001;
      }
      result.bitField0_ |= to_bitField0_;
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.RoomStatsMessage) {
        return mergeFrom((tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.RoomStatsMessage)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.RoomStatsMessage other) {
      if (other == tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.RoomStatsMessage.getDefaultInstance()) return this;
      if (other.hasCommon()) {
        mergeCommon(other.getCommon());
      }
      this.mergeUnknownFields(other.getUnknownFields());
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            case 10: {
              input.readMessage(
                  getCommonFieldBuilder().getBuilder(),
                  extensionRegistry);
              bitField0_ |= 0x00000001;
              break;
            } // case 10
            default: {
              if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                done = true; // was an endgroup tag
              }
              break;
            } // default:
          } // switch (tag)
        } // while (!done)
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.unwrapIOException();
      } finally {
        onChanged();
      } // finally
      return this;
    }
    private int bitField0_;

    private tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.Common common_;
    private com.google.protobuf.SingleFieldBuilderV3<
        tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.Common, tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.Common.Builder, tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.CommonOrBuilder> commonBuilder_;
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
    public boolean hasCommon() {
      return ((bitField0_ & 0x00000001) != 0);
    }
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
    public tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.Common getCommon() {
      if (commonBuilder_ == null) {
        return common_ == null ? tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.Common.getDefaultInstance() : common_;
      } else {
        return commonBuilder_.getMessage();
      }
    }
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
    public Builder setCommon(tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.Common value) {
      if (commonBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        common_ = value;
      } else {
        commonBuilder_.setMessage(value);
      }
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
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
    public Builder setCommon(
        tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.Common.Builder builderForValue) {
      if (commonBuilder_ == null) {
        common_ = builderForValue.build();
      } else {
        commonBuilder_.setMessage(builderForValue.build());
      }
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
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
    public Builder mergeCommon(tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.Common value) {
      if (commonBuilder_ == null) {
        if (((bitField0_ & 0x00000001) != 0) &&
          common_ != null &&
          common_ != tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.Common.getDefaultInstance()) {
          getCommonBuilder().mergeFrom(value);
        } else {
          common_ = value;
        }
      } else {
        commonBuilder_.mergeFrom(value);
      }
      if (common_ != null) {
        bitField0_ |= 0x00000001;
        onChanged();
      }
      return this;
    }
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
    public Builder clearCommon() {
      bitField0_ = (bitField0_ & ~0x00000001);
      common_ = null;
      if (commonBuilder_ != null) {
        commonBuilder_.dispose();
        commonBuilder_ = null;
      }
      onChanged();
      return this;
    }
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
    public tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.Common.Builder getCommonBuilder() {
      bitField0_ |= 0x00000001;
      onChanged();
      return getCommonFieldBuilder().getBuilder();
    }
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
    public tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.CommonOrBuilder getCommonOrBuilder() {
      if (commonBuilder_ != null) {
        return commonBuilder_.getMessageOrBuilder();
      } else {
        return common_ == null ?
            tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.Common.getDefaultInstance() : common_;
      }
    }
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
    private com.google.protobuf.SingleFieldBuilderV3<
        tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.Common, tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.Common.Builder, tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.CommonOrBuilder> 
        getCommonFieldBuilder() {
      if (commonBuilder_ == null) {
        commonBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.Common, tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.Common.Builder, tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.CommonOrBuilder>(
                getCommon(),
                getParentForChildren(),
                isClean());
        common_ = null;
      }
      return commonBuilder_;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:RoomStatsMessage)
  }

  // @@protoc_insertion_point(class_scope:RoomStatsMessage)
  private static final tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.RoomStatsMessage DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.RoomStatsMessage();
  }

  public static tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.RoomStatsMessage getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<RoomStatsMessage>
      PARSER = new com.google.protobuf.AbstractParser<RoomStatsMessage>() {
    @java.lang.Override
    public RoomStatsMessage parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      Builder builder = newBuilder();
      try {
        builder.mergeFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(builder.buildPartial());
      } catch (com.google.protobuf.UninitializedMessageException e) {
        throw e.asInvalidProtocolBufferException().setUnfinishedMessage(builder.buildPartial());
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(e)
            .setUnfinishedMessage(builder.buildPartial());
      }
      return builder.buildPartial();
    }
  };

  public static com.google.protobuf.Parser<RoomStatsMessage> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<RoomStatsMessage> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.RoomStatsMessage getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

