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

public final class CSWebErrorOuterClass {
  private static final Descriptors.Descriptor internal_static_CSWebError_descriptor;
  
  private static final GeneratedMessageV3.FieldAccessorTable internal_static_CSWebError_fieldAccessorTable;
  
  private static Descriptors.FileDescriptor descriptor;
  
  public static void registerAllExtensions(ExtensionRegistryLite registry) {}
  
  public static void registerAllExtensions(ExtensionRegistry registry) {
    registerAllExtensions((ExtensionRegistryLite)registry);
  }
  
  public static final class CSWebError extends GeneratedMessageV3 implements CSWebErrorOrBuilder {
    private static final long serialVersionUID = 0L;
    
    public static final int CODE_FIELD_NUMBER = 1;
    
    private int code_;
    
    public static final int MSG_FIELD_NUMBER = 2;
    
    private volatile Object msg_;
    
    private byte memoizedIsInitialized;
    
    private CSWebError(GeneratedMessageV3.Builder<?> builder) {
      super(builder);
      this.code_ = 0;
      this.msg_ = "";
      this.memoizedIsInitialized = -1;
    }
    
    private CSWebError() {
      this.code_ = 0;
      this.msg_ = "";
      this.memoizedIsInitialized = -1;
      this.msg_ = "";
    }
    
    protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
      return new CSWebError();
    }
    
    public static final Descriptors.Descriptor getDescriptor() {
      return CSWebErrorOuterClass.internal_static_CSWebError_descriptor;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return CSWebErrorOuterClass.internal_static_CSWebError_fieldAccessorTable.ensureFieldAccessorsInitialized(CSWebError.class, Builder.class);
    }
    
    public int getCode() {
      return this.code_;
    }
    
    public String getMsg() {
      Object ref = this.msg_;
      if (ref instanceof String)
        return (String)ref; 
      ByteString bs = (ByteString)ref;
      String s = bs.toStringUtf8();
      this.msg_ = s;
      return s;
    }
    
    public ByteString getMsgBytes() {
      Object ref = this.msg_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.msg_ = b;
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
      if (this.code_ != 0)
        output.writeUInt32(1, this.code_); 
      if (!GeneratedMessageV3.isStringEmpty(this.msg_))
        GeneratedMessageV3.writeString(output, 2, this.msg_); 
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
      if (!GeneratedMessageV3.isStringEmpty(this.msg_))
        size += GeneratedMessageV3.computeStringSize(2, this.msg_); 
      size += getUnknownFields().getSerializedSize();
      this.memoizedSize = size;
      return size;
    }
    
    public boolean equals(Object obj) {
      if (obj == this)
        return true; 
      if (!(obj instanceof CSWebError))
        return super.equals(obj); 
      CSWebError other = (CSWebError)obj;
      if (getCode() != other
        .getCode())
        return false; 
      if (!getMsg().equals(other.getMsg()))
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
      hash = 37 * hash + 2;
      hash = 53 * hash + getMsg().hashCode();
      hash = 29 * hash + getUnknownFields().hashCode();
      this.memoizedHashCode = hash;
      return hash;
    }
    
    public static CSWebError parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (CSWebError)PARSER.parseFrom(data);
    }
    
    public static CSWebError parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (CSWebError)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static CSWebError parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (CSWebError)PARSER.parseFrom(data);
    }
    
    public static CSWebError parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (CSWebError)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static CSWebError parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (CSWebError)PARSER.parseFrom(data);
    }
    
    public static CSWebError parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (CSWebError)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static CSWebError parseFrom(InputStream input) throws IOException {
      return 
        (CSWebError)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static CSWebError parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (CSWebError)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static CSWebError parseDelimitedFrom(InputStream input) throws IOException {
      return 
        (CSWebError)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }
    
    public static CSWebError parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (CSWebError)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static CSWebError parseFrom(CodedInputStream input) throws IOException {
      return 
        (CSWebError)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static CSWebError parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (CSWebError)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public Builder newBuilderForType() {
      return newBuilder();
    }
    
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    
    public static Builder newBuilder(CSWebError prototype) {
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
    
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements CSWebErrorOuterClass.CSWebErrorOrBuilder {
      private int bitField0_;
      
      private int code_;
      
      private Object msg_;
      
      public static final Descriptors.Descriptor getDescriptor() {
        return CSWebErrorOuterClass.internal_static_CSWebError_descriptor;
      }
      
      protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return CSWebErrorOuterClass.internal_static_CSWebError_fieldAccessorTable
          .ensureFieldAccessorsInitialized(CSWebErrorOuterClass.CSWebError.class, Builder.class);
      }
      
      private Builder() {
        this.msg_ = "";
      }
      
      private Builder(GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        this.msg_ = "";
      }
      
      public Builder clear() {
        super.clear();
        this.bitField0_ = 0;
        this.code_ = 0;
        this.msg_ = "";
        return this;
      }
      
      public Descriptors.Descriptor getDescriptorForType() {
        return CSWebErrorOuterClass.internal_static_CSWebError_descriptor;
      }
      
      public CSWebErrorOuterClass.CSWebError getDefaultInstanceForType() {
        return CSWebErrorOuterClass.CSWebError.getDefaultInstance();
      }
      
      public CSWebErrorOuterClass.CSWebError build() {
        CSWebErrorOuterClass.CSWebError result = buildPartial();
        if (!result.isInitialized())
          throw newUninitializedMessageException(result); 
        return result;
      }
      
      public CSWebErrorOuterClass.CSWebError buildPartial() {
        CSWebErrorOuterClass.CSWebError result = new CSWebErrorOuterClass.CSWebError(this);
        if (this.bitField0_ != 0)
          buildPartial0(result); 
        onBuilt();
        return result;
      }
      
      private void buildPartial0(CSWebErrorOuterClass.CSWebError result) {
        int from_bitField0_ = this.bitField0_;
        if ((from_bitField0_ & 0x1) != 0)
          result.code_ = this.code_; 
        if ((from_bitField0_ & 0x2) != 0)
          result.msg_ = this.msg_; 
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
        if (other instanceof CSWebErrorOuterClass.CSWebError)
          return mergeFrom((CSWebErrorOuterClass.CSWebError)other); 
        super.mergeFrom(other);
        return this;
      }
      
      public Builder mergeFrom(CSWebErrorOuterClass.CSWebError other) {
        if (other == CSWebErrorOuterClass.CSWebError.getDefaultInstance())
          return this; 
        if (other.getCode() != 0)
          setCode(other.getCode()); 
        if (!other.getMsg().isEmpty()) {
          this.msg_ = other.msg_;
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
              case 8:
                this.code_ = input.readUInt32();
                this.bitField0_ |= 0x1;
                continue;
              case 18:
                this.msg_ = input.readStringRequireUtf8();
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
      
      public String getMsg() {
        Object ref = this.msg_;
        if (!(ref instanceof String)) {
          ByteString bs = (ByteString)ref;
          String s = bs.toStringUtf8();
          this.msg_ = s;
          return s;
        } 
        return (String)ref;
      }
      
      public ByteString getMsgBytes() {
        Object ref = this.msg_;
        if (ref instanceof String) {
          ByteString b = ByteString.copyFromUtf8((String)ref);
          this.msg_ = b;
          return b;
        } 
        return (ByteString)ref;
      }
      
      public Builder setMsg(String value) {
        if (value == null)
          throw new NullPointerException(); 
        this.msg_ = value;
        this.bitField0_ |= 0x2;
        onChanged();
        return this;
      }
      
      public Builder clearMsg() {
        this.msg_ = CSWebErrorOuterClass.CSWebError.getDefaultInstance().getMsg();
        this.bitField0_ &= 0xFFFFFFFD;
        onChanged();
        return this;
      }
      
      public Builder setMsgBytes(ByteString value) {
        if (value == null)
          throw new NullPointerException(); 
        CSWebErrorOuterClass.CSWebError.checkByteStringIsUtf8(value);
        this.msg_ = value;
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
    
    private static final CSWebError DEFAULT_INSTANCE = new CSWebError();
    
    public static CSWebError getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }
    
    private static final Parser<CSWebError> PARSER = (Parser<CSWebError>)new AbstractParser<CSWebError>() {
        public CSWebErrorOuterClass.CSWebError parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
          CSWebErrorOuterClass.CSWebError.Builder builder = CSWebErrorOuterClass.CSWebError.newBuilder();
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
    
    public static Parser<CSWebError> parser() {
      return PARSER;
    }
    
    public Parser<CSWebError> getParserForType() {
      return PARSER;
    }
    
    public CSWebError getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }
  }
  
  public static Descriptors.FileDescriptor getDescriptor() {
    return descriptor;
  }
  
  static {
    String[] descriptorData = { "\n\020CSWebError.proto\"'\n\nCSWebError\022\f\n\004code\030\001 \001(\r\022\013\n\003msg\030\002 \001(\tB<\n:tech.ordinaryroad.live.chat.client.codec.kuaishou.protobufb\006proto3" };
    descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[0]);
    internal_static_CSWebError_descriptor = getDescriptor().getMessageTypes().get(0);
    internal_static_CSWebError_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_CSWebError_descriptor, new String[] { "Code", "Msg" });
  }
  
  public static interface CSWebErrorOrBuilder extends MessageOrBuilder {
    int getCode();
    
    String getMsg();
    
    ByteString getMsgBytes();
  }
}
