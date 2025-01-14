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

public final class SCPingAckOuterClass {
  private static final Descriptors.Descriptor internal_static_SCPingAck_descriptor;
  
  private static final GeneratedMessageV3.FieldAccessorTable internal_static_SCPingAck_fieldAccessorTable;
  
  private static Descriptors.FileDescriptor descriptor;
  
  public static void registerAllExtensions(ExtensionRegistryLite registry) {}
  
  public static void registerAllExtensions(ExtensionRegistry registry) {
    registerAllExtensions((ExtensionRegistryLite)registry);
  }
  
  public static final class SCPingAck extends GeneratedMessageV3 implements SCPingAckOrBuilder {
    private static final long serialVersionUID = 0L;
    
    public static final int ECHODATA_FIELD_NUMBER = 1;
    
    private volatile Object echoData_;
    
    private byte memoizedIsInitialized;
    
    private SCPingAck(GeneratedMessageV3.Builder<?> builder) {
      super(builder);
      this.echoData_ = "";
      this.memoizedIsInitialized = -1;
    }
    
    private SCPingAck() {
      this.echoData_ = "";
      this.memoizedIsInitialized = -1;
      this.echoData_ = "";
    }
    
    protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
      return new SCPingAck();
    }
    
    public static final Descriptors.Descriptor getDescriptor() {
      return SCPingAckOuterClass.internal_static_SCPingAck_descriptor;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return SCPingAckOuterClass.internal_static_SCPingAck_fieldAccessorTable.ensureFieldAccessorsInitialized(SCPingAck.class, Builder.class);
    }
    
    public String getEchoData() {
      Object ref = this.echoData_;
      if (ref instanceof String)
        return (String)ref; 
      ByteString bs = (ByteString)ref;
      String s = bs.toStringUtf8();
      this.echoData_ = s;
      return s;
    }
    
    public ByteString getEchoDataBytes() {
      Object ref = this.echoData_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.echoData_ = b;
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
      if (!GeneratedMessageV3.isStringEmpty(this.echoData_))
        GeneratedMessageV3.writeString(output, 1, this.echoData_); 
      getUnknownFields().writeTo(output);
    }
    
    public int getSerializedSize() {
      int size = this.memoizedSize;
      if (size != -1)
        return size; 
      size = 0;
      if (!GeneratedMessageV3.isStringEmpty(this.echoData_))
        size += GeneratedMessageV3.computeStringSize(1, this.echoData_); 
      size += getUnknownFields().getSerializedSize();
      this.memoizedSize = size;
      return size;
    }
    
    public boolean equals(Object obj) {
      if (obj == this)
        return true; 
      if (!(obj instanceof SCPingAck))
        return super.equals(obj); 
      SCPingAck other = (SCPingAck)obj;
      if (!getEchoData().equals(other.getEchoData()))
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
      hash = 53 * hash + getEchoData().hashCode();
      hash = 29 * hash + getUnknownFields().hashCode();
      this.memoizedHashCode = hash;
      return hash;
    }
    
    public static SCPingAck parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (SCPingAck)PARSER.parseFrom(data);
    }
    
    public static SCPingAck parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (SCPingAck)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static SCPingAck parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (SCPingAck)PARSER.parseFrom(data);
    }
    
    public static SCPingAck parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (SCPingAck)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static SCPingAck parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (SCPingAck)PARSER.parseFrom(data);
    }
    
    public static SCPingAck parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (SCPingAck)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static SCPingAck parseFrom(InputStream input) throws IOException {
      return 
        (SCPingAck)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static SCPingAck parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (SCPingAck)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static SCPingAck parseDelimitedFrom(InputStream input) throws IOException {
      return 
        (SCPingAck)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }
    
    public static SCPingAck parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (SCPingAck)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static SCPingAck parseFrom(CodedInputStream input) throws IOException {
      return 
        (SCPingAck)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static SCPingAck parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (SCPingAck)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public Builder newBuilderForType() {
      return newBuilder();
    }
    
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    
    public static Builder newBuilder(SCPingAck prototype) {
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
    
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements SCPingAckOuterClass.SCPingAckOrBuilder {
      private int bitField0_;
      
      private Object echoData_;
      
      public static final Descriptors.Descriptor getDescriptor() {
        return SCPingAckOuterClass.internal_static_SCPingAck_descriptor;
      }
      
      protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return SCPingAckOuterClass.internal_static_SCPingAck_fieldAccessorTable
          .ensureFieldAccessorsInitialized(SCPingAckOuterClass.SCPingAck.class, Builder.class);
      }
      
      private Builder() {
        this.echoData_ = "";
      }
      
      private Builder(GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        this.echoData_ = "";
      }
      
      public Builder clear() {
        super.clear();
        this.bitField0_ = 0;
        this.echoData_ = "";
        return this;
      }
      
      public Descriptors.Descriptor getDescriptorForType() {
        return SCPingAckOuterClass.internal_static_SCPingAck_descriptor;
      }
      
      public SCPingAckOuterClass.SCPingAck getDefaultInstanceForType() {
        return SCPingAckOuterClass.SCPingAck.getDefaultInstance();
      }
      
      public SCPingAckOuterClass.SCPingAck build() {
        SCPingAckOuterClass.SCPingAck result = buildPartial();
        if (!result.isInitialized())
          throw newUninitializedMessageException(result); 
        return result;
      }
      
      public SCPingAckOuterClass.SCPingAck buildPartial() {
        SCPingAckOuterClass.SCPingAck result = new SCPingAckOuterClass.SCPingAck(this);
        if (this.bitField0_ != 0)
          buildPartial0(result); 
        onBuilt();
        return result;
      }
      
      private void buildPartial0(SCPingAckOuterClass.SCPingAck result) {
        int from_bitField0_ = this.bitField0_;
        if ((from_bitField0_ & 0x1) != 0)
          result.echoData_ = this.echoData_; 
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
        if (other instanceof SCPingAckOuterClass.SCPingAck)
          return mergeFrom((SCPingAckOuterClass.SCPingAck)other); 
        super.mergeFrom(other);
        return this;
      }
      
      public Builder mergeFrom(SCPingAckOuterClass.SCPingAck other) {
        if (other == SCPingAckOuterClass.SCPingAck.getDefaultInstance())
          return this; 
        if (!other.getEchoData().isEmpty()) {
          this.echoData_ = other.echoData_;
          this.bitField0_ |= 0x1;
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
                this.echoData_ = input.readStringRequireUtf8();
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
      
      public String getEchoData() {
        Object ref = this.echoData_;
        if (!(ref instanceof String)) {
          ByteString bs = (ByteString)ref;
          String s = bs.toStringUtf8();
          this.echoData_ = s;
          return s;
        } 
        return (String)ref;
      }
      
      public ByteString getEchoDataBytes() {
        Object ref = this.echoData_;
        if (ref instanceof String) {
          ByteString b = ByteString.copyFromUtf8((String)ref);
          this.echoData_ = b;
          return b;
        } 
        return (ByteString)ref;
      }
      
      public Builder setEchoData(String value) {
        if (value == null)
          throw new NullPointerException(); 
        this.echoData_ = value;
        this.bitField0_ |= 0x1;
        onChanged();
        return this;
      }
      
      public Builder clearEchoData() {
        this.echoData_ = SCPingAckOuterClass.SCPingAck.getDefaultInstance().getEchoData();
        this.bitField0_ &= 0xFFFFFFFE;
        onChanged();
        return this;
      }
      
      public Builder setEchoDataBytes(ByteString value) {
        if (value == null)
          throw new NullPointerException(); 
        SCPingAckOuterClass.SCPingAck.checkByteStringIsUtf8(value);
        this.echoData_ = value;
        this.bitField0_ |= 0x1;
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
    
    private static final SCPingAck DEFAULT_INSTANCE = new SCPingAck();
    
    public static SCPingAck getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }
    
    private static final Parser<SCPingAck> PARSER = (Parser<SCPingAck>)new AbstractParser<SCPingAck>() {
        public SCPingAckOuterClass.SCPingAck parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
          SCPingAckOuterClass.SCPingAck.Builder builder = SCPingAckOuterClass.SCPingAck.newBuilder();
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
    
    public static Parser<SCPingAck> parser() {
      return PARSER;
    }
    
    public Parser<SCPingAck> getParserForType() {
      return PARSER;
    }
    
    public SCPingAck getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }
  }
  
  public static Descriptors.FileDescriptor getDescriptor() {
    return descriptor;
  }
  
  static {
    String[] descriptorData = { "\n\017SCPingAck.proto\"\035\n\tSCPingAck\022\020\n\bechoData\030\001 \001(\tB<\n:tech.ordinaryroad.live.chat.client.codec.kuaishou.protobufb\006proto3" };
    descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[0]);
    internal_static_SCPingAck_descriptor = getDescriptor().getMessageTypes().get(0);
    internal_static_SCPingAck_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_SCPingAck_descriptor, new String[] { "EchoData" });
  }
  
  public static interface SCPingAckOrBuilder extends MessageOrBuilder {
    String getEchoData();
    
    ByteString getEchoDataBytes();
  }
}
