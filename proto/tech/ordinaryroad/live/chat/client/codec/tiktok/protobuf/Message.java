// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: tiktok.proto

// Protobuf Java Version: 3.25.5
package tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf;

/**
 * Protobuf type {@code Message}
 */
public final class Message extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:Message)
    MessageOrBuilder {
private static final long serialVersionUID = 0L;
  // Use Message.newBuilder() to construct.
  private Message(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private Message() {
    method_ = "";
    payload_ = com.google.protobuf.ByteString.EMPTY;
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new Message();
  }

  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.Tiktok.internal_static_Message_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.Tiktok.internal_static_Message_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.Message.class, tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.Message.Builder.class);
  }

  public static final int METHOD_FIELD_NUMBER = 1;
  @SuppressWarnings("serial")
  private volatile java.lang.Object method_ = "";
  /**
   * <code>string method = 1;</code>
   * @return The method.
   */
  @java.lang.Override
  public java.lang.String getMethod() {
    java.lang.Object ref = method_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      method_ = s;
      return s;
    }
  }
  /**
   * <code>string method = 1;</code>
   * @return The bytes for method.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getMethodBytes() {
    java.lang.Object ref = method_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      method_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int PAYLOAD_FIELD_NUMBER = 2;
  private com.google.protobuf.ByteString payload_ = com.google.protobuf.ByteString.EMPTY;
  /**
   * <code>bytes payload = 2;</code>
   * @return The payload.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString getPayload() {
    return payload_;
  }

  public static final int MSGID_FIELD_NUMBER = 3;
  private long msgId_ = 0L;
  /**
   * <code>int64 msgId = 3;</code>
   * @return The msgId.
   */
  @java.lang.Override
  public long getMsgId() {
    return msgId_;
  }

  public static final int MSGTYPE_FIELD_NUMBER = 4;
  private int msgType_ = 0;
  /**
   * <code>int32 msgType = 4;</code>
   * @return The msgType.
   */
  @java.lang.Override
  public int getMsgType() {
    return msgType_;
  }

  public static final int OFFSET_FIELD_NUMBER = 5;
  private long offset_ = 0L;
  /**
   * <code>int64 offset = 5;</code>
   * @return The offset.
   */
  @java.lang.Override
  public long getOffset() {
    return offset_;
  }

  public static final int NEEDWRDSSTORE_FIELD_NUMBER = 6;
  private boolean needWrdsStore_ = false;
  /**
   * <pre>
   *  int64 wrdsVersion = 7;
   *  string wrdsSubKey = 8;
   * </pre>
   *
   * <code>bool needWrdsStore = 6;</code>
   * @return The needWrdsStore.
   */
  @java.lang.Override
  public boolean getNeedWrdsStore() {
    return needWrdsStore_;
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
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(method_)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, method_);
    }
    if (!payload_.isEmpty()) {
      output.writeBytes(2, payload_);
    }
    if (msgId_ != 0L) {
      output.writeInt64(3, msgId_);
    }
    if (msgType_ != 0) {
      output.writeInt32(4, msgType_);
    }
    if (offset_ != 0L) {
      output.writeInt64(5, offset_);
    }
    if (needWrdsStore_ != false) {
      output.writeBool(6, needWrdsStore_);
    }
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(method_)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, method_);
    }
    if (!payload_.isEmpty()) {
      size += com.google.protobuf.CodedOutputStream
        .computeBytesSize(2, payload_);
    }
    if (msgId_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(3, msgId_);
    }
    if (msgType_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(4, msgType_);
    }
    if (offset_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(5, offset_);
    }
    if (needWrdsStore_ != false) {
      size += com.google.protobuf.CodedOutputStream
        .computeBoolSize(6, needWrdsStore_);
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
    if (!(obj instanceof tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.Message)) {
      return super.equals(obj);
    }
    tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.Message other = (tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.Message) obj;

    if (!getMethod()
        .equals(other.getMethod())) return false;
    if (!getPayload()
        .equals(other.getPayload())) return false;
    if (getMsgId()
        != other.getMsgId()) return false;
    if (getMsgType()
        != other.getMsgType()) return false;
    if (getOffset()
        != other.getOffset()) return false;
    if (getNeedWrdsStore()
        != other.getNeedWrdsStore()) return false;
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
    hash = (37 * hash) + METHOD_FIELD_NUMBER;
    hash = (53 * hash) + getMethod().hashCode();
    hash = (37 * hash) + PAYLOAD_FIELD_NUMBER;
    hash = (53 * hash) + getPayload().hashCode();
    hash = (37 * hash) + MSGID_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getMsgId());
    hash = (37 * hash) + MSGTYPE_FIELD_NUMBER;
    hash = (53 * hash) + getMsgType();
    hash = (37 * hash) + OFFSET_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getOffset());
    hash = (37 * hash) + NEEDWRDSSTORE_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(
        getNeedWrdsStore());
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.Message parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.Message parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.Message parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.Message parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.Message parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.Message parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.Message parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.Message parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public static tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.Message parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }

  public static tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.Message parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.Message parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.Message parseFrom(
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
  public static Builder newBuilder(tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.Message prototype) {
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
   * Protobuf type {@code Message}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:Message)
      tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.MessageOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.Tiktok.internal_static_Message_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.Tiktok.internal_static_Message_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.Message.class, tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.Message.Builder.class);
    }

    // Construct using tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.Message.newBuilder()
    private Builder() {

    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);

    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      bitField0_ = 0;
      method_ = "";
      payload_ = com.google.protobuf.ByteString.EMPTY;
      msgId_ = 0L;
      msgType_ = 0;
      offset_ = 0L;
      needWrdsStore_ = false;
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.Tiktok.internal_static_Message_descriptor;
    }

    @java.lang.Override
    public tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.Message getDefaultInstanceForType() {
      return tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.Message.getDefaultInstance();
    }

    @java.lang.Override
    public tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.Message build() {
      tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.Message result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.Message buildPartial() {
      tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.Message result = new tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.Message(this);
      if (bitField0_ != 0) { buildPartial0(result); }
      onBuilt();
      return result;
    }

    private void buildPartial0(tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.Message result) {
      int from_bitField0_ = bitField0_;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.method_ = method_;
      }
      if (((from_bitField0_ & 0x00000002) != 0)) {
        result.payload_ = payload_;
      }
      if (((from_bitField0_ & 0x00000004) != 0)) {
        result.msgId_ = msgId_;
      }
      if (((from_bitField0_ & 0x00000008) != 0)) {
        result.msgType_ = msgType_;
      }
      if (((from_bitField0_ & 0x00000010) != 0)) {
        result.offset_ = offset_;
      }
      if (((from_bitField0_ & 0x00000020) != 0)) {
        result.needWrdsStore_ = needWrdsStore_;
      }
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
      if (other instanceof tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.Message) {
        return mergeFrom((tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.Message)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.Message other) {
      if (other == tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.Message.getDefaultInstance()) return this;
      if (!other.getMethod().isEmpty()) {
        method_ = other.method_;
        bitField0_ |= 0x00000001;
        onChanged();
      }
      if (other.getPayload() != com.google.protobuf.ByteString.EMPTY) {
        setPayload(other.getPayload());
      }
      if (other.getMsgId() != 0L) {
        setMsgId(other.getMsgId());
      }
      if (other.getMsgType() != 0) {
        setMsgType(other.getMsgType());
      }
      if (other.getOffset() != 0L) {
        setOffset(other.getOffset());
      }
      if (other.getNeedWrdsStore() != false) {
        setNeedWrdsStore(other.getNeedWrdsStore());
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
              method_ = input.readStringRequireUtf8();
              bitField0_ |= 0x00000001;
              break;
            } // case 10
            case 18: {
              payload_ = input.readBytes();
              bitField0_ |= 0x00000002;
              break;
            } // case 18
            case 24: {
              msgId_ = input.readInt64();
              bitField0_ |= 0x00000004;
              break;
            } // case 24
            case 32: {
              msgType_ = input.readInt32();
              bitField0_ |= 0x00000008;
              break;
            } // case 32
            case 40: {
              offset_ = input.readInt64();
              bitField0_ |= 0x00000010;
              break;
            } // case 40
            case 48: {
              needWrdsStore_ = input.readBool();
              bitField0_ |= 0x00000020;
              break;
            } // case 48
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

    private java.lang.Object method_ = "";
    /**
     * <code>string method = 1;</code>
     * @return The method.
     */
    public java.lang.String getMethod() {
      java.lang.Object ref = method_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        method_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string method = 1;</code>
     * @return The bytes for method.
     */
    public com.google.protobuf.ByteString
        getMethodBytes() {
      java.lang.Object ref = method_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        method_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string method = 1;</code>
     * @param value The method to set.
     * @return This builder for chaining.
     */
    public Builder setMethod(
        java.lang.String value) {
      if (value == null) { throw new NullPointerException(); }
      method_ = value;
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <code>string method = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearMethod() {
      method_ = getDefaultInstance().getMethod();
      bitField0_ = (bitField0_ & ~0x00000001);
      onChanged();
      return this;
    }
    /**
     * <code>string method = 1;</code>
     * @param value The bytes for method to set.
     * @return This builder for chaining.
     */
    public Builder setMethodBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) { throw new NullPointerException(); }
      checkByteStringIsUtf8(value);
      method_ = value;
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }

    private com.google.protobuf.ByteString payload_ = com.google.protobuf.ByteString.EMPTY;
    /**
     * <code>bytes payload = 2;</code>
     * @return The payload.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getPayload() {
      return payload_;
    }
    /**
     * <code>bytes payload = 2;</code>
     * @param value The payload to set.
     * @return This builder for chaining.
     */
    public Builder setPayload(com.google.protobuf.ByteString value) {
      if (value == null) { throw new NullPointerException(); }
      payload_ = value;
      bitField0_ |= 0x00000002;
      onChanged();
      return this;
    }
    /**
     * <code>bytes payload = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearPayload() {
      bitField0_ = (bitField0_ & ~0x00000002);
      payload_ = getDefaultInstance().getPayload();
      onChanged();
      return this;
    }

    private long msgId_ ;
    /**
     * <code>int64 msgId = 3;</code>
     * @return The msgId.
     */
    @java.lang.Override
    public long getMsgId() {
      return msgId_;
    }
    /**
     * <code>int64 msgId = 3;</code>
     * @param value The msgId to set.
     * @return This builder for chaining.
     */
    public Builder setMsgId(long value) {

      msgId_ = value;
      bitField0_ |= 0x00000004;
      onChanged();
      return this;
    }
    /**
     * <code>int64 msgId = 3;</code>
     * @return This builder for chaining.
     */
    public Builder clearMsgId() {
      bitField0_ = (bitField0_ & ~0x00000004);
      msgId_ = 0L;
      onChanged();
      return this;
    }

    private int msgType_ ;
    /**
     * <code>int32 msgType = 4;</code>
     * @return The msgType.
     */
    @java.lang.Override
    public int getMsgType() {
      return msgType_;
    }
    /**
     * <code>int32 msgType = 4;</code>
     * @param value The msgType to set.
     * @return This builder for chaining.
     */
    public Builder setMsgType(int value) {

      msgType_ = value;
      bitField0_ |= 0x00000008;
      onChanged();
      return this;
    }
    /**
     * <code>int32 msgType = 4;</code>
     * @return This builder for chaining.
     */
    public Builder clearMsgType() {
      bitField0_ = (bitField0_ & ~0x00000008);
      msgType_ = 0;
      onChanged();
      return this;
    }

    private long offset_ ;
    /**
     * <code>int64 offset = 5;</code>
     * @return The offset.
     */
    @java.lang.Override
    public long getOffset() {
      return offset_;
    }
    /**
     * <code>int64 offset = 5;</code>
     * @param value The offset to set.
     * @return This builder for chaining.
     */
    public Builder setOffset(long value) {

      offset_ = value;
      bitField0_ |= 0x00000010;
      onChanged();
      return this;
    }
    /**
     * <code>int64 offset = 5;</code>
     * @return This builder for chaining.
     */
    public Builder clearOffset() {
      bitField0_ = (bitField0_ & ~0x00000010);
      offset_ = 0L;
      onChanged();
      return this;
    }

    private boolean needWrdsStore_ ;
    /**
     * <pre>
     *  int64 wrdsVersion = 7;
     *  string wrdsSubKey = 8;
     * </pre>
     *
     * <code>bool needWrdsStore = 6;</code>
     * @return The needWrdsStore.
     */
    @java.lang.Override
    public boolean getNeedWrdsStore() {
      return needWrdsStore_;
    }
    /**
     * <pre>
     *  int64 wrdsVersion = 7;
     *  string wrdsSubKey = 8;
     * </pre>
     *
     * <code>bool needWrdsStore = 6;</code>
     * @param value The needWrdsStore to set.
     * @return This builder for chaining.
     */
    public Builder setNeedWrdsStore(boolean value) {

      needWrdsStore_ = value;
      bitField0_ |= 0x00000020;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *  int64 wrdsVersion = 7;
     *  string wrdsSubKey = 8;
     * </pre>
     *
     * <code>bool needWrdsStore = 6;</code>
     * @return This builder for chaining.
     */
    public Builder clearNeedWrdsStore() {
      bitField0_ = (bitField0_ & ~0x00000020);
      needWrdsStore_ = false;
      onChanged();
      return this;
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


    // @@protoc_insertion_point(builder_scope:Message)
  }

  // @@protoc_insertion_point(class_scope:Message)
  private static final tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.Message DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.Message();
  }

  public static tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.Message getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<Message>
      PARSER = new com.google.protobuf.AbstractParser<Message>() {
    @java.lang.Override
    public Message parsePartialFrom(
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

  public static com.google.protobuf.Parser<Message> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<Message> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.Message getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

