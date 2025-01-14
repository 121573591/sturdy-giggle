package tech.ordinaryroad.live.chat.client.codec.kuaishou.protobuf;

import com.google.protobuf.AbstractMessage;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class CSErrorOuterClass {
  private static final Descriptors.Descriptor internal_static_CSError_descriptor;
  
  private static final GeneratedMessageV3.FieldAccessorTable internal_static_CSError_fieldAccessorTable;
  
  private static Descriptors.FileDescriptor descriptor;
  
  public static void registerAllExtensions(ExtensionRegistryLite registry) {}
  
  public static void registerAllExtensions(ExtensionRegistry registry) {
    registerAllExtensions((ExtensionRegistryLite)registry);
  }
  
  public static final class CSError extends GeneratedMessageV3 implements CSErrorOrBuilder {
    private static final long serialVersionUID = 0L;
    
    public static final int CODE_FIELD_NUMBER = 1;
    
    private int code_;
    
    private byte memoizedIsInitialized;
    
    private CSError(GeneratedMessageV3.Builder<?> builder) {
      super(builder);
      this.code_ = 0;
      this.memoizedIsInitialized = -1;
    }
    
    private CSError() {
      this.code_ = 0;
      this.memoizedIsInitialized = -1;
    }
    
    protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
      return new CSError();
    }
    
    public static final Descriptors.Descriptor getDescriptor() {
      return CSErrorOuterClass.internal_static_CSError_descriptor;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return CSErrorOuterClass.internal_static_CSError_fieldAccessorTable.ensureFieldAccessorsInitialized(CSError.class, Builder.class);
    }
    
    public int getCode() {
      return this.code_;
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
      if (this.code_ != 0)
        output.writeUInt32(1, this.code_); 
      getUnknownFields().writeTo(output);
    }
    
    public int getSerializedSize() {
      int size = this.memoizedSize;
      if (size != -1)
        return size; 
      size = 0;
      if (this.code_ != 0)
        size += 
          CodedOutputStream.computeUInt32Size(1, this.code_); 
      size += getUnknownFields().getSerializedSize();
      this.memoizedSize = size;
      return size;
    }
    
    public boolean equals(Object obj) {
      if (obj == this)
        return true; 
      if (!(obj instanceof CSError))
        return super.equals(obj); 
      CSError other = (CSError)obj;
      if (getCode() != other
        .getCode())
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
      hash = 53 * hash + getCode();
      hash = 29 * hash + getUnknownFields().hashCode();
      this.memoizedHashCode = hash;
      return hash;
    }
    
    public static CSError parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (CSError)PARSER.parseFrom(data);
    }
    
    public static CSError parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (CSError)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static CSError parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (CSError)PARSER.parseFrom(data);
    }
    
    public static CSError parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (CSError)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static CSError parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (CSError)PARSER.parseFrom(data);
    }
    
    public static CSError parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (CSError)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static CSError parseFrom(InputStream input) throws IOException {
      return 
        (CSError)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static CSError parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (CSError)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static CSError parseDelimitedFrom(InputStream input) throws IOException {
      return 
        (CSError)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }
    
    public static CSError parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (CSError)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static CSError parseFrom(CodedInputStream input) throws IOException {
      return 
        (CSError)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static CSError parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (CSError)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public Builder newBuilderForType() {
      return newBuilder();
    }
    
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    
    public static Builder newBuilder(CSError prototype) {
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
    
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements CSErrorOuterClass.CSErrorOrBuilder {
      private int bitField0_;
      
      private int code_;
      
      public static final Descriptors.Descriptor getDescriptor() {
        return CSErrorOuterClass.internal_static_CSError_descriptor;
      }
      
      protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return CSErrorOuterClass.internal_static_CSError_fieldAccessorTable
          .ensureFieldAccessorsInitialized(CSErrorOuterClass.CSError.class, Builder.class);
      }
      
      private Builder() {}
      
      private Builder(GeneratedMessageV3.BuilderParent parent) {
        super(parent);
      }
      
      public Builder clear() {
        super.clear();
        this.bitField0_ = 0;
        this.code_ = 0;
        return this;
      }
      
      public Descriptors.Descriptor getDescriptorForType() {
        return CSErrorOuterClass.internal_static_CSError_descriptor;
      }
      
      public CSErrorOuterClass.CSError getDefaultInstanceForType() {
        return CSErrorOuterClass.CSError.getDefaultInstance();
      }
      
      public CSErrorOuterClass.CSError build() {
        CSErrorOuterClass.CSError result = buildPartial();
        if (!result.isInitialized())
          throw newUninitializedMessageException(result); 
        return result;
      }
      
      public CSErrorOuterClass.CSError buildPartial() {
        CSErrorOuterClass.CSError result = new CSErrorOuterClass.CSError(this);
        if (this.bitField0_ != 0)
          buildPartial0(result); 
        onBuilt();
        return result;
      }
      
      private void buildPartial0(CSErrorOuterClass.CSError result) {
        int from_bitField0_ = this.bitField0_;
        if ((from_bitField0_ & 0x1) != 0)
          result.code_ = this.code_; 
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
        if (other instanceof CSErrorOuterClass.CSError)
          return mergeFrom((CSErrorOuterClass.CSError)other); 
        super.mergeFrom(other);
        return this;
      }
      
      public Builder mergeFrom(CSErrorOuterClass.CSError other) {
        if (other == CSErrorOuterClass.CSError.getDefaultInstance())
          return this; 
        if (other.getCode() != 0)
          setCode(other.getCode()); 
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
              case 8:
                this.code_ = input.readUInt32();
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
      
      public int getCode() {
        return this.code_;
      }
      
      public Builder setCode(int value) {
        this.code_ = value;
        this.bitField0_ |= 0x1;
        onChanged();
        return this;
      }
      
      public Builder clearCode() {
        this.bitField0_ &= 0xFFFFFFFE;
        this.code_ = 0;
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
    
    private static final CSError DEFAULT_INSTANCE = new CSError();
    
    public static CSError getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }
    
    private static final Parser<CSError> PARSER = (Parser<CSError>)new AbstractParser<CSError>() {
        public CSErrorOuterClass.CSError parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
          CSErrorOuterClass.CSError.Builder builder = CSErrorOuterClass.CSError.newBuilder();
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
    
    public static Parser<CSError> parser() {
      return PARSER;
    }
    
    public Parser<CSError> getParserForType() {
      return PARSER;
    }
    
    public CSError getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }
  }
  
  public static Descriptors.FileDescriptor getDescriptor() {
    return descriptor;
  }
  
  static {
    String[] descriptorData = { "\n\rCSError.proto\"\027\n\007CSError\022\f\n\004code\030\001 \001(\rB<\n:tech.ordinaryroad.live.chat.client.codec.kuaishou.protobufb\006proto3" };
    descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[0]);
    internal_static_CSError_descriptor = getDescriptor().getMessageTypes().get(0);
    internal_static_CSError_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_CSError_descriptor, new String[] { "Code" });
  }
  
  public static interface CSErrorOrBuilder extends MessageOrBuilder {
    int getCode();
  }
}
