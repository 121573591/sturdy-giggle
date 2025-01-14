package tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf;

import com.google.protobuf.AbstractMessage;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.MessageLite;
import com.google.protobuf.Parser;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class HeadersList extends GeneratedMessageV3 implements HeadersListOrBuilder {
  private static final long serialVersionUID = 0L;
  
  public static final int KEY_FIELD_NUMBER = 1;
  
  private volatile Object key_;
  
  public static final int VALUE_FIELD_NUMBER = 2;
  
  private volatile Object value_;
  
  private byte memoizedIsInitialized;
  
  private HeadersList(GeneratedMessageV3.Builder<?> builder) {
    super(builder);
    this.key_ = "";
    this.value_ = "";
    this.memoizedIsInitialized = -1;
  }
  
  private HeadersList() {
    this.key_ = "";
    this.value_ = "";
    this.memoizedIsInitialized = -1;
    this.key_ = "";
    this.value_ = "";
  }
  
  protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
    return new HeadersList();
  }
  
  public static final Descriptors.Descriptor getDescriptor() {
    return Tiktok.internal_static_HeadersList_descriptor;
  }
  
  protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
    return Tiktok.internal_static_HeadersList_fieldAccessorTable.ensureFieldAccessorsInitialized(HeadersList.class, Builder.class);
  }
  
  public String getKey() {
    Object ref = this.key_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.key_ = s;
    return s;
  }
  
  public ByteString getKeyBytes() {
    Object ref = this.key_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.key_ = b;
      return b;
    } 
    return (ByteString)ref;
  }
  
  public String getValue() {
    Object ref = this.value_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.value_ = s;
    return s;
  }
  
  public ByteString getValueBytes() {
    Object ref = this.value_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.value_ = b;
      return b;
    } 
    return (ByteString)ref;
  }
  
  public final boolean isInitialized() {
    byte isInitialized = this.memoizedIsInitialized;
    if (isInitialized == 1)
      return true; 
    if (isInitialized == 0)
      return false; 
    this.memoizedIsInitialized = 1;
    return true;
  }
  
  public void writeTo(CodedOutputStream output) throws IOException {
    if (!GeneratedMessageV3.isStringEmpty(this.key_))
      GeneratedMessageV3.writeString(output, 1, this.key_); 
    if (!GeneratedMessageV3.isStringEmpty(this.value_))
      GeneratedMessageV3.writeString(output, 2, this.value_); 
    getUnknownFields().writeTo(output);
  }
  
  public int getSerializedSize() {
    int size = this.memoizedSize;
    if (size != -1)
      return size; 
    size = 0;
    if (!GeneratedMessageV3.isStringEmpty(this.key_))
      size += GeneratedMessageV3.computeStringSize(1, this.key_); 
    if (!GeneratedMessageV3.isStringEmpty(this.value_))
      size += GeneratedMessageV3.computeStringSize(2, this.value_); 
    size += getUnknownFields().getSerializedSize();
    this.memoizedSize = size;
    return size;
  }
  
  public boolean equals(Object obj) {
    if (obj == this)
      return true; 
    if (!(obj instanceof HeadersList))
      return super.equals(obj); 
    HeadersList other = (HeadersList)obj;
    if (!getKey().equals(other.getKey()))
      return false; 
    if (!getValue().equals(other.getValue()))
      return false; 
    if (!getUnknownFields().equals(other.getUnknownFields()))
      return false; 
    return true;
  }
  
  public int hashCode() {
    if (this.memoizedHashCode != 0)
      return this.memoizedHashCode; 
    int hash = 41;
    hash = 19 * hash + getDescriptor().hashCode();
    hash = 37 * hash + 1;
    hash = 53 * hash + getKey().hashCode();
    hash = 37 * hash + 2;
    hash = 53 * hash + getValue().hashCode();
    hash = 29 * hash + getUnknownFields().hashCode();
    this.memoizedHashCode = hash;
    return hash;
  }
  
  public static HeadersList parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
    return (HeadersList)PARSER.parseFrom(data);
  }
  
  public static HeadersList parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (HeadersList)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static HeadersList parseFrom(ByteString data) throws InvalidProtocolBufferException {
    return (HeadersList)PARSER.parseFrom(data);
  }
  
  public static HeadersList parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (HeadersList)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static HeadersList parseFrom(byte[] data) throws InvalidProtocolBufferException {
    return (HeadersList)PARSER.parseFrom(data);
  }
  
  public static HeadersList parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (HeadersList)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static HeadersList parseFrom(InputStream input) throws IOException {
    return 
      (HeadersList)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static HeadersList parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (HeadersList)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static HeadersList parseDelimitedFrom(InputStream input) throws IOException {
    return 
      (HeadersList)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
  }
  
  public static HeadersList parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (HeadersList)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static HeadersList parseFrom(CodedInputStream input) throws IOException {
    return 
      (HeadersList)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static HeadersList parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (HeadersList)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public Builder newBuilderForType() {
    return newBuilder();
  }
  
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  
  public static Builder newBuilder(HeadersList prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  
  public Builder toBuilder() {
    return (this == DEFAULT_INSTANCE) ? 
      new Builder() : (new Builder()).mergeFrom(this);
  }
  
  protected Builder newBuilderForType(GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  
  public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements HeadersListOrBuilder {
    private int bitField0_;
    
    private Object key_;
    
    private Object value_;
    
    public static final Descriptors.Descriptor getDescriptor() {
      return Tiktok.internal_static_HeadersList_descriptor;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return Tiktok.internal_static_HeadersList_fieldAccessorTable
        .ensureFieldAccessorsInitialized(HeadersList.class, Builder.class);
    }
    
    private Builder() {
      this.key_ = "";
      this.value_ = "";
    }
    
    private Builder(GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      this.key_ = "";
      this.value_ = "";
    }
    
    public Builder clear() {
      super.clear();
      this.bitField0_ = 0;
      this.key_ = "";
      this.value_ = "";
      return this;
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
      return Tiktok.internal_static_HeadersList_descriptor;
    }
    
    public HeadersList getDefaultInstanceForType() {
      return HeadersList.getDefaultInstance();
    }
    
    public HeadersList build() {
      HeadersList result = buildPartial();
      if (!result.isInitialized())
        throw newUninitializedMessageException(result); 
      return result;
    }
    
    public HeadersList buildPartial() {
      HeadersList result = new HeadersList(this);
      if (this.bitField0_ != 0)
        buildPartial0(result); 
      onBuilt();
      return result;
    }
    
    private void buildPartial0(HeadersList result) {
      int from_bitField0_ = this.bitField0_;
      if ((from_bitField0_ & 0x1) != 0)
        result.key_ = this.key_; 
      if ((from_bitField0_ & 0x2) != 0)
        result.value_ = this.value_; 
    }
    
    public Builder clone() {
      return (Builder)super.clone();
    }
    
    public Builder setField(Descriptors.FieldDescriptor field, Object value) {
      return (Builder)super.setField(field, value);
    }
    
    public Builder clearField(Descriptors.FieldDescriptor field) {
      return (Builder)super.clearField(field);
    }
    
    public Builder clearOneof(Descriptors.OneofDescriptor oneof) {
      return (Builder)super.clearOneof(oneof);
    }
    
    public Builder setRepeatedField(Descriptors.FieldDescriptor field, int index, Object value) {
      return (Builder)super.setRepeatedField(field, index, value);
    }
    
    public Builder addRepeatedField(Descriptors.FieldDescriptor field, Object value) {
      return (Builder)super.addRepeatedField(field, value);
    }
    
    public Builder mergeFrom(Message other) {
      if (other instanceof HeadersList)
        return mergeFrom((HeadersList)other); 
      super.mergeFrom(other);
      return this;
    }
    
    public Builder mergeFrom(HeadersList other) {
      if (other == HeadersList.getDefaultInstance())
        return this; 
      if (!other.getKey().isEmpty()) {
        this.key_ = other.key_;
        this.bitField0_ |= 0x1;
        onChanged();
      } 
      if (!other.getValue().isEmpty()) {
        this.value_ = other.value_;
        this.bitField0_ |= 0x2;
        onChanged();
      } 
      mergeUnknownFields(other.getUnknownFields());
      onChanged();
      return this;
    }
    
    public final boolean isInitialized() {
      return true;
    }
    
    public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      if (extensionRegistry == null)
        throw new NullPointerException(); 
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              continue;
            case 10:
              this.key_ = input.readStringRequireUtf8();
              this.bitField0_ |= 0x1;
              continue;
            case 18:
              this.value_ = input.readStringRequireUtf8();
              this.bitField0_ |= 0x2;
              continue;
          } 
          if (!parseUnknownField(input, extensionRegistry, tag))
            done = true; 
        } 
      } catch (InvalidProtocolBufferException e) {
        throw e.unwrapIOException();
      } finally {
        onChanged();
      } 
      return this;
    }
    
    public String getKey() {
      Object ref = this.key_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.key_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getKeyBytes() {
      Object ref = this.key_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.key_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setKey(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.key_ = value;
      this.bitField0_ |= 0x1;
      onChanged();
      return this;
    }
    
    public Builder clearKey() {
      this.key_ = HeadersList.getDefaultInstance().getKey();
      this.bitField0_ &= 0xFFFFFFFE;
      onChanged();
      return this;
    }
    
    public Builder setKeyBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      HeadersList.checkByteStringIsUtf8(value);
      this.key_ = value;
      this.bitField0_ |= 0x1;
      onChanged();
      return this;
    }
    
    public String getValue() {
      Object ref = this.value_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.value_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getValueBytes() {
      Object ref = this.value_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.value_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setValue(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.value_ = value;
      this.bitField0_ |= 0x2;
      onChanged();
      return this;
    }
    
    public Builder clearValue() {
      this.value_ = HeadersList.getDefaultInstance().getValue();
      this.bitField0_ &= 0xFFFFFFFD;
      onChanged();
      return this;
    }
    
    public Builder setValueBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      HeadersList.checkByteStringIsUtf8(value);
      this.value_ = value;
      this.bitField0_ |= 0x2;
      onChanged();
      return this;
    }
    
    public final Builder setUnknownFields(UnknownFieldSet unknownFields) {
      return (Builder)super.setUnknownFields(unknownFields);
    }
    
    public final Builder mergeUnknownFields(UnknownFieldSet unknownFields) {
      return (Builder)super.mergeUnknownFields(unknownFields);
    }
  }
  
  private static final HeadersList DEFAULT_INSTANCE = new HeadersList();
  
  public static HeadersList getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }
  
  private static final Parser<HeadersList> PARSER = (Parser<HeadersList>)new AbstractParser<HeadersList>() {
      public HeadersList parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        HeadersList.Builder builder = HeadersList.newBuilder();
        try {
          builder.mergeFrom(input, extensionRegistry);
        } catch (InvalidProtocolBufferException e) {
          throw e.setUnfinishedMessage(builder.buildPartial());
        } catch (UninitializedMessageException e) {
          throw e.asInvalidProtocolBufferException().setUnfinishedMessage(builder.buildPartial());
        } catch (IOException e) {
          throw (new InvalidProtocolBufferException(e))
            .setUnfinishedMessage(builder.buildPartial());
        } 
        return builder.buildPartial();
      }
    };
  
  public static Parser<HeadersList> parser() {
    return PARSER;
  }
  
  public Parser<HeadersList> getParserForType() {
    return PARSER;
  }
  
  public HeadersList getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }
}
