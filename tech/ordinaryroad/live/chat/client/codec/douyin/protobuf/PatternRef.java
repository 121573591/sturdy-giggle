package tech.ordinaryroad.live.chat.client.codec.douyin.protobuf;

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

public final class PatternRef extends GeneratedMessageV3 implements PatternRefOrBuilder {
  private static final long serialVersionUID = 0L;
  
  public static final int KEY_FIELD_NUMBER = 1;
  
  private volatile Object key_;
  
  public static final int DEFAULTPATTERN_FIELD_NUMBER = 2;
  
  private volatile Object defaultPattern_;
  
  private byte memoizedIsInitialized;
  
  private PatternRef(GeneratedMessageV3.Builder<?> builder) {
    super(builder);
    this.key_ = "";
    this.defaultPattern_ = "";
    this.memoizedIsInitialized = -1;
  }
  
  private PatternRef() {
    this.key_ = "";
    this.defaultPattern_ = "";
    this.memoizedIsInitialized = -1;
    this.key_ = "";
    this.defaultPattern_ = "";
  }
  
  protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
    return new PatternRef();
  }
  
  public static final Descriptors.Descriptor getDescriptor() {
    return Douyin.internal_static_PatternRef_descriptor;
  }
  
  protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
    return Douyin.internal_static_PatternRef_fieldAccessorTable.ensureFieldAccessorsInitialized(PatternRef.class, Builder.class);
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
  
  public String getDefaultPattern() {
    Object ref = this.defaultPattern_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.defaultPattern_ = s;
    return s;
  }
  
  public ByteString getDefaultPatternBytes() {
    Object ref = this.defaultPattern_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.defaultPattern_ = b;
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
    if (!GeneratedMessageV3.isStringEmpty(this.defaultPattern_))
      GeneratedMessageV3.writeString(output, 2, this.defaultPattern_); 
    getUnknownFields().writeTo(output);
  }
  
  public int getSerializedSize() {
    int size = this.memoizedSize;
    if (size != -1)
      return size; 
    size = 0;
    if (!GeneratedMessageV3.isStringEmpty(this.key_))
      size += GeneratedMessageV3.computeStringSize(1, this.key_); 
    if (!GeneratedMessageV3.isStringEmpty(this.defaultPattern_))
      size += GeneratedMessageV3.computeStringSize(2, this.defaultPattern_); 
    size += getUnknownFields().getSerializedSize();
    this.memoizedSize = size;
    return size;
  }
  
  public boolean equals(Object obj) {
    if (obj == this)
      return true; 
    if (!(obj instanceof PatternRef))
      return super.equals(obj); 
    PatternRef other = (PatternRef)obj;
    if (!getKey().equals(other.getKey()))
      return false; 
    if (!getDefaultPattern().equals(other.getDefaultPattern()))
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
    hash = 53 * hash + getDefaultPattern().hashCode();
    hash = 29 * hash + getUnknownFields().hashCode();
    this.memoizedHashCode = hash;
    return hash;
  }
  
  public static PatternRef parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
    return (PatternRef)PARSER.parseFrom(data);
  }
  
  public static PatternRef parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (PatternRef)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static PatternRef parseFrom(ByteString data) throws InvalidProtocolBufferException {
    return (PatternRef)PARSER.parseFrom(data);
  }
  
  public static PatternRef parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (PatternRef)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static PatternRef parseFrom(byte[] data) throws InvalidProtocolBufferException {
    return (PatternRef)PARSER.parseFrom(data);
  }
  
  public static PatternRef parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (PatternRef)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static PatternRef parseFrom(InputStream input) throws IOException {
    return 
      (PatternRef)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static PatternRef parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (PatternRef)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static PatternRef parseDelimitedFrom(InputStream input) throws IOException {
    return 
      (PatternRef)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
  }
  
  public static PatternRef parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (PatternRef)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static PatternRef parseFrom(CodedInputStream input) throws IOException {
    return 
      (PatternRef)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static PatternRef parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (PatternRef)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public Builder newBuilderForType() {
    return newBuilder();
  }
  
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  
  public static Builder newBuilder(PatternRef prototype) {
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
  
  public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements PatternRefOrBuilder {
    private int bitField0_;
    
    private Object key_;
    
    private Object defaultPattern_;
    
    public static final Descriptors.Descriptor getDescriptor() {
      return Douyin.internal_static_PatternRef_descriptor;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return Douyin.internal_static_PatternRef_fieldAccessorTable
        .ensureFieldAccessorsInitialized(PatternRef.class, Builder.class);
    }
    
    private Builder() {
      this.key_ = "";
      this.defaultPattern_ = "";
    }
    
    private Builder(GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      this.key_ = "";
      this.defaultPattern_ = "";
    }
    
    public Builder clear() {
      super.clear();
      this.bitField0_ = 0;
      this.key_ = "";
      this.defaultPattern_ = "";
      return this;
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
      return Douyin.internal_static_PatternRef_descriptor;
    }
    
    public PatternRef getDefaultInstanceForType() {
      return PatternRef.getDefaultInstance();
    }
    
    public PatternRef build() {
      PatternRef result = buildPartial();
      if (!result.isInitialized())
        throw newUninitializedMessageException(result); 
      return result;
    }
    
    public PatternRef buildPartial() {
      PatternRef result = new PatternRef(this);
      if (this.bitField0_ != 0)
        buildPartial0(result); 
      onBuilt();
      return result;
    }
    
    private void buildPartial0(PatternRef result) {
      int from_bitField0_ = this.bitField0_;
      if ((from_bitField0_ & 0x1) != 0)
        result.key_ = this.key_; 
      if ((from_bitField0_ & 0x2) != 0)
        result.defaultPattern_ = this.defaultPattern_; 
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
      if (other instanceof PatternRef)
        return mergeFrom((PatternRef)other); 
      super.mergeFrom(other);
      return this;
    }
    
    public Builder mergeFrom(PatternRef other) {
      if (other == PatternRef.getDefaultInstance())
        return this; 
      if (!other.getKey().isEmpty()) {
        this.key_ = other.key_;
        this.bitField0_ |= 0x1;
        onChanged();
      } 
      if (!other.getDefaultPattern().isEmpty()) {
        this.defaultPattern_ = other.defaultPattern_;
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
              this.defaultPattern_ = input.readStringRequireUtf8();
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
      this.key_ = PatternRef.getDefaultInstance().getKey();
      this.bitField0_ &= 0xFFFFFFFE;
      onChanged();
      return this;
    }
    
    public Builder setKeyBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      PatternRef.checkByteStringIsUtf8(value);
      this.key_ = value;
      this.bitField0_ |= 0x1;
      onChanged();
      return this;
    }
    
    public String getDefaultPattern() {
      Object ref = this.defaultPattern_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.defaultPattern_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getDefaultPatternBytes() {
      Object ref = this.defaultPattern_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.defaultPattern_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setDefaultPattern(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.defaultPattern_ = value;
      this.bitField0_ |= 0x2;
      onChanged();
      return this;
    }
    
    public Builder clearDefaultPattern() {
      this.defaultPattern_ = PatternRef.getDefaultInstance().getDefaultPattern();
      this.bitField0_ &= 0xFFFFFFFD;
      onChanged();
      return this;
    }
    
    public Builder setDefaultPatternBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      PatternRef.checkByteStringIsUtf8(value);
      this.defaultPattern_ = value;
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
  
  private static final PatternRef DEFAULT_INSTANCE = new PatternRef();
  
  public static PatternRef getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }
  
  private static final Parser<PatternRef> PARSER = (Parser<PatternRef>)new AbstractParser<PatternRef>() {
      public PatternRef parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        PatternRef.Builder builder = PatternRef.newBuilder();
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
  
  public static Parser<PatternRef> parser() {
    return PARSER;
  }
  
  public Parser<PatternRef> getParserForType() {
    return PARSER;
  }
  
  public PatternRef getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }
}
