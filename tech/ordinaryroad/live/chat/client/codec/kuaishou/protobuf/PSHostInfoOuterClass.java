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

public final class PSHostInfoOuterClass {
  private static final Descriptors.Descriptor internal_static_PSHostInfo_descriptor;
  
  private static final GeneratedMessageV3.FieldAccessorTable internal_static_PSHostInfo_fieldAccessorTable;
  
  private static Descriptors.FileDescriptor descriptor;
  
  public static void registerAllExtensions(ExtensionRegistryLite registry) {}
  
  public static void registerAllExtensions(ExtensionRegistry registry) {
    registerAllExtensions((ExtensionRegistryLite)registry);
  }
  
  public static final class PSHostInfo extends GeneratedMessageV3 implements PSHostInfoOrBuilder {
    private static final long serialVersionUID = 0L;
    
    public static final int IP_FIELD_NUMBER = 1;
    
    private volatile Object ip_;
    
    public static final int PORT_FIELD_NUMBER = 2;
    
    private int port_;
    
    private byte memoizedIsInitialized;
    
    private PSHostInfo(GeneratedMessageV3.Builder<?> builder) {
      super(builder);
      this.ip_ = "";
      this.port_ = 0;
      this.memoizedIsInitialized = -1;
    }
    
    private PSHostInfo() {
      this.ip_ = "";
      this.port_ = 0;
      this.memoizedIsInitialized = -1;
      this.ip_ = "";
    }
    
    protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
      return new PSHostInfo();
    }
    
    public static final Descriptors.Descriptor getDescriptor() {
      return PSHostInfoOuterClass.internal_static_PSHostInfo_descriptor;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return PSHostInfoOuterClass.internal_static_PSHostInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(PSHostInfo.class, Builder.class);
    }
    
    public String getIp() {
      Object ref = this.ip_;
      if (ref instanceof String)
        return (String)ref; 
      ByteString bs = (ByteString)ref;
      String s = bs.toStringUtf8();
      this.ip_ = s;
      return s;
    }
    
    public ByteString getIpBytes() {
      Object ref = this.ip_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.ip_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public int getPort() {
      return this.port_;
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
      if (!GeneratedMessageV3.isStringEmpty(this.ip_))
        GeneratedMessageV3.writeString(output, 1, this.ip_); 
      if (this.port_ != 0)
        output.writeInt32(2, this.port_); 
      getUnknownFields().writeTo(output);
    }
    
    public int getSerializedSize() {
      int size = this.memoizedSize;
      if (size != -1)
        return size; 
      size = 0;
      if (!GeneratedMessageV3.isStringEmpty(this.ip_))
        size += GeneratedMessageV3.computeStringSize(1, this.ip_); 
      if (this.port_ != 0)
        size += 
          CodedOutputStream.computeInt32Size(2, this.port_); 
      size += getUnknownFields().getSerializedSize();
      this.memoizedSize = size;
      return size;
    }
    
    public boolean equals(Object obj) {
      if (obj == this)
        return true; 
      if (!(obj instanceof PSHostInfo))
        return super.equals(obj); 
      PSHostInfo other = (PSHostInfo)obj;
      if (!getIp().equals(other.getIp()))
        return false; 
      if (getPort() != other
        .getPort())
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
      hash = 53 * hash + getIp().hashCode();
      hash = 37 * hash + 2;
      hash = 53 * hash + getPort();
      hash = 29 * hash + getUnknownFields().hashCode();
      this.memoizedHashCode = hash;
      return hash;
    }
    
    public static PSHostInfo parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (PSHostInfo)PARSER.parseFrom(data);
    }
    
    public static PSHostInfo parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (PSHostInfo)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static PSHostInfo parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (PSHostInfo)PARSER.parseFrom(data);
    }
    
    public static PSHostInfo parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (PSHostInfo)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static PSHostInfo parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (PSHostInfo)PARSER.parseFrom(data);
    }
    
    public static PSHostInfo parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (PSHostInfo)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static PSHostInfo parseFrom(InputStream input) throws IOException {
      return 
        (PSHostInfo)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static PSHostInfo parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (PSHostInfo)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static PSHostInfo parseDelimitedFrom(InputStream input) throws IOException {
      return 
        (PSHostInfo)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }
    
    public static PSHostInfo parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (PSHostInfo)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static PSHostInfo parseFrom(CodedInputStream input) throws IOException {
      return 
        (PSHostInfo)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static PSHostInfo parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (PSHostInfo)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public Builder newBuilderForType() {
      return newBuilder();
    }
    
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    
    public static Builder newBuilder(PSHostInfo prototype) {
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
    
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements PSHostInfoOuterClass.PSHostInfoOrBuilder {
      private int bitField0_;
      
      private Object ip_;
      
      private int port_;
      
      public static final Descriptors.Descriptor getDescriptor() {
        return PSHostInfoOuterClass.internal_static_PSHostInfo_descriptor;
      }
      
      protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return PSHostInfoOuterClass.internal_static_PSHostInfo_fieldAccessorTable
          .ensureFieldAccessorsInitialized(PSHostInfoOuterClass.PSHostInfo.class, Builder.class);
      }
      
      private Builder() {
        this.ip_ = "";
      }
      
      private Builder(GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        this.ip_ = "";
      }
      
      public Builder clear() {
        super.clear();
        this.bitField0_ = 0;
        this.ip_ = "";
        this.port_ = 0;
        return this;
      }
      
      public Descriptors.Descriptor getDescriptorForType() {
        return PSHostInfoOuterClass.internal_static_PSHostInfo_descriptor;
      }
      
      public PSHostInfoOuterClass.PSHostInfo getDefaultInstanceForType() {
        return PSHostInfoOuterClass.PSHostInfo.getDefaultInstance();
      }
      
      public PSHostInfoOuterClass.PSHostInfo build() {
        PSHostInfoOuterClass.PSHostInfo result = buildPartial();
        if (!result.isInitialized())
          throw newUninitializedMessageException(result); 
        return result;
      }
      
      public PSHostInfoOuterClass.PSHostInfo buildPartial() {
        PSHostInfoOuterClass.PSHostInfo result = new PSHostInfoOuterClass.PSHostInfo(this);
        if (this.bitField0_ != 0)
          buildPartial0(result); 
        onBuilt();
        return result;
      }
      
      private void buildPartial0(PSHostInfoOuterClass.PSHostInfo result) {
        int from_bitField0_ = this.bitField0_;
        if ((from_bitField0_ & 0x1) != 0)
          result.ip_ = this.ip_; 
        if ((from_bitField0_ & 0x2) != 0)
          result.port_ = this.port_; 
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
        if (other instanceof PSHostInfoOuterClass.PSHostInfo)
          return mergeFrom((PSHostInfoOuterClass.PSHostInfo)other); 
        super.mergeFrom(other);
        return this;
      }
      
      public Builder mergeFrom(PSHostInfoOuterClass.PSHostInfo other) {
        if (other == PSHostInfoOuterClass.PSHostInfo.getDefaultInstance())
          return this; 
        if (!other.getIp().isEmpty()) {
          this.ip_ = other.ip_;
          this.bitField0_ |= 0x1;
          onChanged();
        } 
        if (other.getPort() != 0)
          setPort(other.getPort()); 
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
                this.ip_ = input.readStringRequireUtf8();
                this.bitField0_ |= 0x1;
                continue;
              case 16:
                this.port_ = input.readInt32();
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
      
      public String getIp() {
        Object ref = this.ip_;
        if (!(ref instanceof String)) {
          ByteString bs = (ByteString)ref;
          String s = bs.toStringUtf8();
          this.ip_ = s;
          return s;
        } 
        return (String)ref;
      }
      
      public ByteString getIpBytes() {
        Object ref = this.ip_;
        if (ref instanceof String) {
          ByteString b = ByteString.copyFromUtf8((String)ref);
          this.ip_ = b;
          return b;
        } 
        return (ByteString)ref;
      }
      
      public Builder setIp(String value) {
        if (value == null)
          throw new NullPointerException(); 
        this.ip_ = value;
        this.bitField0_ |= 0x1;
        onChanged();
        return this;
      }
      
      public Builder clearIp() {
        this.ip_ = PSHostInfoOuterClass.PSHostInfo.getDefaultInstance().getIp();
        this.bitField0_ &= 0xFFFFFFFE;
        onChanged();
        return this;
      }
      
      public Builder setIpBytes(ByteString value) {
        if (value == null)
          throw new NullPointerException(); 
        PSHostInfoOuterClass.PSHostInfo.checkByteStringIsUtf8(value);
        this.ip_ = value;
        this.bitField0_ |= 0x1;
        onChanged();
        return this;
      }
      
      public int getPort() {
        return this.port_;
      }
      
      public Builder setPort(int value) {
        this.port_ = value;
        this.bitField0_ |= 0x2;
        onChanged();
        return this;
      }
      
      public Builder clearPort() {
        this.bitField0_ &= 0xFFFFFFFD;
        this.port_ = 0;
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
    
    private static final PSHostInfo DEFAULT_INSTANCE = new PSHostInfo();
    
    public static PSHostInfo getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }
    
    private static final Parser<PSHostInfo> PARSER = (Parser<PSHostInfo>)new AbstractParser<PSHostInfo>() {
        public PSHostInfoOuterClass.PSHostInfo parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
          PSHostInfoOuterClass.PSHostInfo.Builder builder = PSHostInfoOuterClass.PSHostInfo.newBuilder();
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
    
    public static Parser<PSHostInfo> parser() {
      return PARSER;
    }
    
    public Parser<PSHostInfo> getParserForType() {
      return PARSER;
    }
    
    public PSHostInfo getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }
  }
  
  public static Descriptors.FileDescriptor getDescriptor() {
    return descriptor;
  }
  
  static {
    String[] descriptorData = { "\n\020PSHostInfo.proto\"&\n\nPSHostInfo\022\n\n\002ip\030\001 \001(\t\022\f\n\004port\030\002 \001(\005B<\n:tech.ordinaryroad.live.chat.client.codec.kuaishou.protobufb\006proto3" };
    descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[0]);
    internal_static_PSHostInfo_descriptor = getDescriptor().getMessageTypes().get(0);
    internal_static_PSHostInfo_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_PSHostInfo_descriptor, new String[] { "Ip", "Port" });
  }
  
  public static interface PSHostInfoOrBuilder extends MessageOrBuilder {
    String getIp();
    
    ByteString getIpBytes();
    
    int getPort();
  }
}
