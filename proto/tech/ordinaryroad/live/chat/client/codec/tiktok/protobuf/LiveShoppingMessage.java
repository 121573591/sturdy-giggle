// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: tiktok.proto

// Protobuf Java Version: 3.25.5
package tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf;

/**
 * <pre>
 *  Common common = 1;
 *  int32 msgType = 2;
 *  int64  promotionId = 4;
 * </pre>
 *
 * Protobuf type {@code LiveShoppingMessage}
 */
public final class LiveShoppingMessage extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:LiveShoppingMessage)
    LiveShoppingMessageOrBuilder {
private static final long serialVersionUID = 0L;
  // Use LiveShoppingMessage.newBuilder() to construct.
  private LiveShoppingMessage(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private LiveShoppingMessage() {
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new LiveShoppingMessage();
  }

  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.Tiktok.internal_static_LiveShoppingMessage_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.Tiktok.internal_static_LiveShoppingMessage_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.LiveShoppingMessage.class, tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.LiveShoppingMessage.Builder.class);
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
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    size += getUnknownFields().getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.LiveShoppingMessage)) {
      return super.equals(obj);
    }
    tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.LiveShoppingMessage other = (tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.LiveShoppingMessage) obj;

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
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.LiveShoppingMessage parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.LiveShoppingMessage parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.LiveShoppingMessage parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.LiveShoppingMessage parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.LiveShoppingMessage parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.LiveShoppingMessage parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.LiveShoppingMessage parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.LiveShoppingMessage parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public static tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.LiveShoppingMessage parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }

  public static tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.LiveShoppingMessage parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.LiveShoppingMessage parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.LiveShoppingMessage parseFrom(
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
  public static Builder newBuilder(tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.LiveShoppingMessage prototype) {
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
   * <pre>
   *  Common common = 1;
   *  int32 msgType = 2;
   *  int64  promotionId = 4;
   * </pre>
   *
   * Protobuf type {@code LiveShoppingMessage}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:LiveShoppingMessage)
      tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.LiveShoppingMessageOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.Tiktok.internal_static_LiveShoppingMessage_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.Tiktok.internal_static_LiveShoppingMessage_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.LiveShoppingMessage.class, tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.LiveShoppingMessage.Builder.class);
    }

    // Construct using tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.LiveShoppingMessage.newBuilder()
    private Builder() {

    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);

    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.Tiktok.internal_static_LiveShoppingMessage_descriptor;
    }

    @java.lang.Override
    public tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.LiveShoppingMessage getDefaultInstanceForType() {
      return tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.LiveShoppingMessage.getDefaultInstance();
    }

    @java.lang.Override
    public tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.LiveShoppingMessage build() {
      tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.LiveShoppingMessage result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.LiveShoppingMessage buildPartial() {
      tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.LiveShoppingMessage result = new tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.LiveShoppingMessage(this);
      onBuilt();
      return result;
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
      if (other instanceof tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.LiveShoppingMessage) {
        return mergeFrom((tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.LiveShoppingMessage)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.LiveShoppingMessage other) {
      if (other == tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.LiveShoppingMessage.getDefaultInstance()) return this;
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


    // @@protoc_insertion_point(builder_scope:LiveShoppingMessage)
  }

  // @@protoc_insertion_point(class_scope:LiveShoppingMessage)
  private static final tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.LiveShoppingMessage DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.LiveShoppingMessage();
  }

  public static tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.LiveShoppingMessage getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<LiveShoppingMessage>
      PARSER = new com.google.protobuf.AbstractParser<LiveShoppingMessage>() {
    @java.lang.Override
    public LiveShoppingMessage parsePartialFrom(
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

  public static com.google.protobuf.Parser<LiveShoppingMessage> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<LiveShoppingMessage> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.LiveShoppingMessage getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

