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

public final class kk extends GeneratedMessageV3 implements kkOrBuilder {
  private static final long serialVersionUID = 0L;
  
  public static final int K_FIELD_NUMBER = 14;
  
  private int k_;
  
  private byte memoizedIsInitialized;
  
  private kk(GeneratedMessageV3.Builder<?> builder) {
    super(builder);
    this.k_ = 0;
    this.memoizedIsInitialized = -1;
  }
  
  private kk() {
    this.k_ = 0;
    this.memoizedIsInitialized = -1;
  }
  
  protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
    return new kk();
  }
  
  public static final Descriptors.Descriptor getDescriptor() {
    return Douyin.internal_static_kk_descriptor;
  }
  
  protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
    return Douyin.internal_static_kk_fieldAccessorTable.ensureFieldAccessorsInitialized(kk.class, Builder.class);
  }
  
  public int getK() {
    return this.k_;
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
    if (this.k_ != 0)
      output.writeUInt32(14, this.k_); 
    getUnknownFields().writeTo(output);
  }
  
  public int getSerializedSize() {
    int size = this.memoizedSize;
    if (size != -1)
      return size; 
    size = 0;
    if (this.k_ != 0)
      size += 
        CodedOutputStream.computeUInt32Size(14, this.k_); 
    size += getUnknownFields().getSerializedSize();
    this.memoizedSize = size;
    return size;
  }
  
  public boolean equals(Object obj) {
    if (obj == this)
      return true; 
    if (!(obj instanceof kk))
      return super.equals(obj); 
    kk other = (kk)obj;
    if (getK() != other
      .getK())
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
    hash = 37 * hash + 14;
    hash = 53 * hash + getK();
    hash = 29 * hash + getUnknownFields().hashCode();
    this.memoizedHashCode = hash;
    return hash;
  }
  
  public static kk parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
    return (kk)PARSER.parseFrom(data);
  }
  
  public static kk parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (kk)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static kk parseFrom(ByteString data) throws InvalidProtocolBufferException {
    return (kk)PARSER.parseFrom(data);
  }
  
  public static kk parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (kk)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static kk parseFrom(byte[] data) throws InvalidProtocolBufferException {
    return (kk)PARSER.parseFrom(data);
  }
  
  public static kk parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (kk)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static kk parseFrom(InputStream input) throws IOException {
    return 
      (kk)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static kk parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (kk)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static kk parseDelimitedFrom(InputStream input) throws IOException {
    return 
      (kk)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
  }
  
  public static kk parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (kk)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static kk parseFrom(CodedInputStream input) throws IOException {
    return 
      (kk)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static kk parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (kk)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public Builder newBuilderForType() {
    return newBuilder();
  }
  
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  
  public static Builder newBuilder(kk prototype) {
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
  
  public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements kkOrBuilder {
    private int bitField0_;
    
    private int k_;
    
    public static final Descriptors.Descriptor getDescriptor() {
      return Douyin.internal_static_kk_descriptor;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return Douyin.internal_static_kk_fieldAccessorTable
        .ensureFieldAccessorsInitialized(kk.class, Builder.class);
    }
    
    private Builder() {}
    
    private Builder(GeneratedMessageV3.BuilderParent parent) {
      super(parent);
    }
    
    public Builder clear() {
      super.clear();
      this.bitField0_ = 0;
      this.k_ = 0;
      return this;
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
      return Douyin.internal_static_kk_descriptor;
    }
    
    public kk getDefaultInstanceForType() {
      return kk.getDefaultInstance();
    }
    
    public kk build() {
      kk result = buildPartial();
      if (!result.isInitialized())
        throw newUninitializedMessageException(result); 
      return result;
    }
    
    public kk buildPartial() {
      kk result = new kk(this);
      if (this.bitField0_ != 0)
        buildPartial0(result); 
      onBuilt();
      return result;
    }
    
    private void buildPartial0(kk result) {
      int from_bitField0_ = this.bitField0_;
      if ((from_bitField0_ & 0x1) != 0)
        result.k_ = this.k_; 
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
      if (other instanceof kk)
        return mergeFrom((kk)other); 
      super.mergeFrom(other);
      return this;
    }
    
    public Builder mergeFrom(kk other) {
      if (other == kk.getDefaultInstance())
        return this; 
      if (other.getK() != 0)
        setK(other.getK()); 
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
            case 112:
              this.k_ = input.readUInt32();
              this.bitField0_ |= 0x1;
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
    
    public int getK() {
      return this.k_;
    }
    
    public Builder setK(int value) {
      this.k_ = value;
      this.bitField0_ |= 0x1;
      onChanged();
      return this;
    }
    
    public Builder clearK() {
      this.bitField0_ &= 0xFFFFFFFE;
      this.k_ = 0;
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
  
  private static final kk DEFAULT_INSTANCE = new kk();
  
  public static kk getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }
  
  private static final Parser<kk> PARSER = (Parser<kk>)new AbstractParser<kk>() {
      public kk parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        kk.Builder builder = kk.newBuilder();
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
  
  public static Parser<kk> parser() {
    return PARSER;
  }
  
  public Parser<kk> getParserForType() {
    return PARSER;
  }
  
  public kk getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }
}
