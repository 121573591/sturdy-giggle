// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: tiktok.proto

// Protobuf Java Version: 3.25.5
package tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf;

/**
 * <pre>
 *  string content = 1;               // Content of the page
 *  string background_color = 2;      // Background color of the page
 *  int64 show_times = 3;             // Number of times the page is shown (or similar)
 *  int32 content_type = 4;           // Type of the content
 * </pre>
 *
 * Protobuf type {@code RanklistHourEntrance_Page}
 */
public final class RanklistHourEntrance_Page extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:RanklistHourEntrance_Page)
    RanklistHourEntrance_PageOrBuilder {
private static final long serialVersionUID = 0L;
  // Use RanklistHourEntrance_Page.newBuilder() to construct.
  private RanklistHourEntrance_Page(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private RanklistHourEntrance_Page() {
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new RanklistHourEntrance_Page();
  }

  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.Tiktok.internal_static_RanklistHourEntrance_Page_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.Tiktok.internal_static_RanklistHourEntrance_Page_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.RanklistHourEntrance_Page.class, tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.RanklistHourEntrance_Page.Builder.class);
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
    if (!(obj instanceof tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.RanklistHourEntrance_Page)) {
      return super.equals(obj);
    }
    tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.RanklistHourEntrance_Page other = (tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.RanklistHourEntrance_Page) obj;

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

  public static tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.RanklistHourEntrance_Page parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.RanklistHourEntrance_Page parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.RanklistHourEntrance_Page parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.RanklistHourEntrance_Page parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.RanklistHourEntrance_Page parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.RanklistHourEntrance_Page parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.RanklistHourEntrance_Page parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.RanklistHourEntrance_Page parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public static tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.RanklistHourEntrance_Page parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }

  public static tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.RanklistHourEntrance_Page parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.RanklistHourEntrance_Page parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.RanklistHourEntrance_Page parseFrom(
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
  public static Builder newBuilder(tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.RanklistHourEntrance_Page prototype) {
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
   *  string content = 1;               // Content of the page
   *  string background_color = 2;      // Background color of the page
   *  int64 show_times = 3;             // Number of times the page is shown (or similar)
   *  int32 content_type = 4;           // Type of the content
   * </pre>
   *
   * Protobuf type {@code RanklistHourEntrance_Page}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:RanklistHourEntrance_Page)
      tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.RanklistHourEntrance_PageOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.Tiktok.internal_static_RanklistHourEntrance_Page_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.Tiktok.internal_static_RanklistHourEntrance_Page_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.RanklistHourEntrance_Page.class, tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.RanklistHourEntrance_Page.Builder.class);
    }

    // Construct using tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.RanklistHourEntrance_Page.newBuilder()
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
      return tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.Tiktok.internal_static_RanklistHourEntrance_Page_descriptor;
    }

    @java.lang.Override
    public tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.RanklistHourEntrance_Page getDefaultInstanceForType() {
      return tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.RanklistHourEntrance_Page.getDefaultInstance();
    }

    @java.lang.Override
    public tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.RanklistHourEntrance_Page build() {
      tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.RanklistHourEntrance_Page result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.RanklistHourEntrance_Page buildPartial() {
      tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.RanklistHourEntrance_Page result = new tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.RanklistHourEntrance_Page(this);
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
      if (other instanceof tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.RanklistHourEntrance_Page) {
        return mergeFrom((tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.RanklistHourEntrance_Page)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.RanklistHourEntrance_Page other) {
      if (other == tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.RanklistHourEntrance_Page.getDefaultInstance()) return this;
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


    // @@protoc_insertion_point(builder_scope:RanklistHourEntrance_Page)
  }

  // @@protoc_insertion_point(class_scope:RanklistHourEntrance_Page)
  private static final tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.RanklistHourEntrance_Page DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.RanklistHourEntrance_Page();
  }

  public static tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.RanklistHourEntrance_Page getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<RanklistHourEntrance_Page>
      PARSER = new com.google.protobuf.AbstractParser<RanklistHourEntrance_Page>() {
    @java.lang.Override
    public RanklistHourEntrance_Page parsePartialFrom(
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

  public static com.google.protobuf.Parser<RanklistHourEntrance_Page> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<RanklistHourEntrance_Page> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.RanklistHourEntrance_Page getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

