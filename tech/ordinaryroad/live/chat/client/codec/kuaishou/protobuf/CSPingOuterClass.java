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

public final class CSPingOuterClass {
  private static final Descriptors.Descriptor internal_static_CSPing_descriptor;
  
  private static final GeneratedMessageV3.FieldAccessorTable internal_static_CSPing_fieldAccessorTable;
  
  private static Descriptors.FileDescriptor descriptor;
  
  public static void registerAllExtensions(ExtensionRegistryLite registry) {}
  
  public static void registerAllExtensions(ExtensionRegistry registry) {
    registerAllExtensions((ExtensionRegistryLite)registry);
  }
  
  public static final class CSPing extends GeneratedMessageV3 implements CSPingOrBuilder {
    private static final long serialVersionUID = 0L;
    
    public static final int ECHODATA_FIELD_NUMBER = 1;
    
    private volatile Object echoData_;
    
    public static final int CLIENTID_FIELD_NUMBER = 2;
    
    private int clientId_;
    
    public static final int DEVICEID_FIELD_NUMBER = 3;
    
    private volatile Object deviceId_;
    
    public static final int APPVER_FIELD_NUMBER = 4;
    
    private volatile Object appVer_;
    
    private byte memoizedIsInitialized;
    
    private CSPing(GeneratedMessageV3.Builder<?> builder) {
      super(builder);
      this.echoData_ = "";
      this.clientId_ = 0;
      this.deviceId_ = "";
      this.appVer_ = "";
      this.memoizedIsInitialized = -1;
    }
    
    private CSPing() {
      this.echoData_ = "";
      this.clientId_ = 0;
      this.deviceId_ = "";
      this.appVer_ = "";
      this.memoizedIsInitialized = -1;
      this.echoData_ = "";
      this.clientId_ = 0;
      this.deviceId_ = "";
      this.appVer_ = "";
    }
    
    protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
      return new CSPing();
    }
    
    public static final Descriptors.Descriptor getDescriptor() {
      return CSPingOuterClass.internal_static_CSPing_descriptor;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return CSPingOuterClass.internal_static_CSPing_fieldAccessorTable.ensureFieldAccessorsInitialized(CSPing.class, Builder.class);
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
    
    public int getClientIdValue() {
      return this.clientId_;
    }
    
    public ClientIdOuterClass.ClientId getClientId() {
      ClientIdOuterClass.ClientId result = ClientIdOuterClass.ClientId.forNumber(this.clientId_);
      return (result == null) ? ClientIdOuterClass.ClientId.UNRECOGNIZED : result;
    }
    
    public String getDeviceId() {
      Object ref = this.deviceId_;
      if (ref instanceof String)
        return (String)ref; 
      ByteString bs = (ByteString)ref;
      String s = bs.toStringUtf8();
      this.deviceId_ = s;
      return s;
    }
    
    public ByteString getDeviceIdBytes() {
      Object ref = this.deviceId_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.deviceId_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public String getAppVer() {
      Object ref = this.appVer_;
      if (ref instanceof String)
        return (String)ref; 
      ByteString bs = (ByteString)ref;
      String s = bs.toStringUtf8();
      this.appVer_ = s;
      return s;
    }
    
    public ByteString getAppVerBytes() {
      Object ref = this.appVer_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.appVer_ = b;
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
      if (this.clientId_ != ClientIdOuterClass.ClientId.NONE.getNumber())
        output.writeEnum(2, this.clientId_); 
      if (!GeneratedMessageV3.isStringEmpty(this.deviceId_))
        GeneratedMessageV3.writeString(output, 3, this.deviceId_); 
      if (!GeneratedMessageV3.isStringEmpty(this.appVer_))
        GeneratedMessageV3.writeString(output, 4, this.appVer_); 
      getUnknownFields().writeTo(output);
    }
    
    public int getSerializedSize() {
      int size = this.memoizedSize;
      if (size != -1)
        return size; 
      size = 0;
      if (!GeneratedMessageV3.isStringEmpty(this.echoData_))
        size += GeneratedMessageV3.computeStringSize(1, this.echoData_); 
      if (this.clientId_ != ClientIdOuterClass.ClientId.NONE.getNumber())
        size += 
          CodedOutputStream.computeEnumSize(2, this.clientId_); 
      if (!GeneratedMessageV3.isStringEmpty(this.deviceId_))
        size += GeneratedMessageV3.computeStringSize(3, this.deviceId_); 
      if (!GeneratedMessageV3.isStringEmpty(this.appVer_))
        size += GeneratedMessageV3.computeStringSize(4, this.appVer_); 
      size += getUnknownFields().getSerializedSize();
      this.memoizedSize = size;
      return size;
    }
    
    public boolean equals(Object obj) {
      if (obj == this)
        return true; 
      if (!(obj instanceof CSPing))
        return super.equals(obj); 
      CSPing other = (CSPing)obj;
      if (!getEchoData().equals(other.getEchoData()))
        return false; 
      if (this.clientId_ != other.clientId_)
        return false; 
      if (!getDeviceId().equals(other.getDeviceId()))
        return false; 
      if (!getAppVer().equals(other.getAppVer()))
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
      hash = 37 * hash + 2;
      hash = 53 * hash + this.clientId_;
      hash = 37 * hash + 3;
      hash = 53 * hash + getDeviceId().hashCode();
      hash = 37 * hash + 4;
      hash = 53 * hash + getAppVer().hashCode();
      hash = 29 * hash + getUnknownFields().hashCode();
      this.memoizedHashCode = hash;
      return hash;
    }
    
    public static CSPing parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (CSPing)PARSER.parseFrom(data);
    }
    
    public static CSPing parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (CSPing)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static CSPing parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (CSPing)PARSER.parseFrom(data);
    }
    
    public static CSPing parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (CSPing)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static CSPing parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (CSPing)PARSER.parseFrom(data);
    }
    
    public static CSPing parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (CSPing)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static CSPing parseFrom(InputStream input) throws IOException {
      return 
        (CSPing)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static CSPing parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (CSPing)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static CSPing parseDelimitedFrom(InputStream input) throws IOException {
      return 
        (CSPing)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }
    
    public static CSPing parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (CSPing)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static CSPing parseFrom(CodedInputStream input) throws IOException {
      return 
        (CSPing)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static CSPing parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (CSPing)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public Builder newBuilderForType() {
      return newBuilder();
    }
    
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    
    public static Builder newBuilder(CSPing prototype) {
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
    
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements CSPingOuterClass.CSPingOrBuilder {
      private int bitField0_;
      
      private Object echoData_;
      
      private int clientId_;
      
      private Object deviceId_;
      
      private Object appVer_;
      
      public static final Descriptors.Descriptor getDescriptor() {
        return CSPingOuterClass.internal_static_CSPing_descriptor;
      }
      
      protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return CSPingOuterClass.internal_static_CSPing_fieldAccessorTable
          .ensureFieldAccessorsInitialized(CSPingOuterClass.CSPing.class, Builder.class);
      }
      
      private Builder() {
        this.echoData_ = "";
        this.clientId_ = 0;
        this.deviceId_ = "";
        this.appVer_ = "";
      }
      
      private Builder(GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        this.echoData_ = "";
        this.clientId_ = 0;
        this.deviceId_ = "";
        this.appVer_ = "";
      }
      
      public Builder clear() {
        super.clear();
        this.bitField0_ = 0;
        this.echoData_ = "";
        this.clientId_ = 0;
        this.deviceId_ = "";
        this.appVer_ = "";
        return this;
      }
      
      public Descriptors.Descriptor getDescriptorForType() {
        return CSPingOuterClass.internal_static_CSPing_descriptor;
      }
      
      public CSPingOuterClass.CSPing getDefaultInstanceForType() {
        return CSPingOuterClass.CSPing.getDefaultInstance();
      }
      
      public CSPingOuterClass.CSPing build() {
        CSPingOuterClass.CSPing result = buildPartial();
        if (!result.isInitialized())
          throw newUninitializedMessageException(result); 
        return result;
      }
      
      public CSPingOuterClass.CSPing buildPartial() {
        CSPingOuterClass.CSPing result = new CSPingOuterClass.CSPing(this);
        if (this.bitField0_ != 0)
          buildPartial0(result); 
        onBuilt();
        return result;
      }
      
      private void buildPartial0(CSPingOuterClass.CSPing result) {
        int from_bitField0_ = this.bitField0_;
        if ((from_bitField0_ & 0x1) != 0)
          result.echoData_ = this.echoData_; 
        if ((from_bitField0_ & 0x2) != 0)
          result.clientId_ = this.clientId_; 
        if ((from_bitField0_ & 0x4) != 0)
          result.deviceId_ = this.deviceId_; 
        if ((from_bitField0_ & 0x8) != 0)
          result.appVer_ = this.appVer_; 
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
        if (other instanceof CSPingOuterClass.CSPing)
          return mergeFrom((CSPingOuterClass.CSPing)other); 
        super.mergeFrom(other);
        return this;
      }
      
      public Builder mergeFrom(CSPingOuterClass.CSPing other) {
        if (other == CSPingOuterClass.CSPing.getDefaultInstance())
          return this; 
        if (!other.getEchoData().isEmpty()) {
          this.echoData_ = other.echoData_;
          this.bitField0_ |= 0x1;
          onChanged();
        } 
        if (other.clientId_ != 0)
          setClientIdValue(other.getClientIdValue()); 
        if (!other.getDeviceId().isEmpty()) {
          this.deviceId_ = other.deviceId_;
          this.bitField0_ |= 0x4;
          onChanged();
        } 
        if (!other.getAppVer().isEmpty()) {
          this.appVer_ = other.appVer_;
          this.bitField0_ |= 0x8;
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
              case 16:
                this.clientId_ = input.readEnum();
                this.bitField0_ |= 0x2;
                continue;
              case 26:
                this.deviceId_ = input.readStringRequireUtf8();
                this.bitField0_ |= 0x4;
                continue;
              case 34:
                this.appVer_ = input.readStringRequireUtf8();
                this.bitField0_ |= 0x8;
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
        this.echoData_ = CSPingOuterClass.CSPing.getDefaultInstance().getEchoData();
        this.bitField0_ &= 0xFFFFFFFE;
        onChanged();
        return this;
      }
      
      public Builder setEchoDataBytes(ByteString value) {
        if (value == null)
          throw new NullPointerException(); 
        CSPingOuterClass.CSPing.checkByteStringIsUtf8(value);
        this.echoData_ = value;
        this.bitField0_ |= 0x1;
        onChanged();
        return this;
      }
      
      public int getClientIdValue() {
        return this.clientId_;
      }
      
      public Builder setClientIdValue(int value) {
        this.clientId_ = value;
        this.bitField0_ |= 0x2;
        onChanged();
        return this;
      }
      
      public ClientIdOuterClass.ClientId getClientId() {
        ClientIdOuterClass.ClientId result = ClientIdOuterClass.ClientId.forNumber(this.clientId_);
        return (result == null) ? ClientIdOuterClass.ClientId.UNRECOGNIZED : result;
      }
      
      public Builder setClientId(ClientIdOuterClass.ClientId value) {
        if (value == null)
          throw new NullPointerException(); 
        this.bitField0_ |= 0x2;
        this.clientId_ = value.getNumber();
        onChanged();
        return this;
      }
      
      public Builder clearClientId() {
        this.bitField0_ &= 0xFFFFFFFD;
        this.clientId_ = 0;
        onChanged();
        return this;
      }
      
      public String getDeviceId() {
        Object ref = this.deviceId_;
        if (!(ref instanceof String)) {
          ByteString bs = (ByteString)ref;
          String s = bs.toStringUtf8();
          this.deviceId_ = s;
          return s;
        } 
        return (String)ref;
      }
      
      public ByteString getDeviceIdBytes() {
        Object ref = this.deviceId_;
        if (ref instanceof String) {
          ByteString b = ByteString.copyFromUtf8((String)ref);
          this.deviceId_ = b;
          return b;
        } 
        return (ByteString)ref;
      }
      
      public Builder setDeviceId(String value) {
        if (value == null)
          throw new NullPointerException(); 
        this.deviceId_ = value;
        this.bitField0_ |= 0x4;
        onChanged();
        return this;
      }
      
      public Builder clearDeviceId() {
        this.deviceId_ = CSPingOuterClass.CSPing.getDefaultInstance().getDeviceId();
        this.bitField0_ &= 0xFFFFFFFB;
        onChanged();
        return this;
      }
      
      public Builder setDeviceIdBytes(ByteString value) {
        if (value == null)
          throw new NullPointerException(); 
        CSPingOuterClass.CSPing.checkByteStringIsUtf8(value);
        this.deviceId_ = value;
        this.bitField0_ |= 0x4;
        onChanged();
        return this;
      }
      
      public String getAppVer() {
        Object ref = this.appVer_;
        if (!(ref instanceof String)) {
          ByteString bs = (ByteString)ref;
          String s = bs.toStringUtf8();
          this.appVer_ = s;
          return s;
        } 
        return (String)ref;
      }
      
      public ByteString getAppVerBytes() {
        Object ref = this.appVer_;
        if (ref instanceof String) {
          ByteString b = ByteString.copyFromUtf8((String)ref);
          this.appVer_ = b;
          return b;
        } 
        return (ByteString)ref;
      }
      
      public Builder setAppVer(String value) {
        if (value == null)
          throw new NullPointerException(); 
        this.appVer_ = value;
        this.bitField0_ |= 0x8;
        onChanged();
        return this;
      }
      
      public Builder clearAppVer() {
        this.appVer_ = CSPingOuterClass.CSPing.getDefaultInstance().getAppVer();
        this.bitField0_ &= 0xFFFFFFF7;
        onChanged();
        return this;
      }
      
      public Builder setAppVerBytes(ByteString value) {
        if (value == null)
          throw new NullPointerException(); 
        CSPingOuterClass.CSPing.checkByteStringIsUtf8(value);
        this.appVer_ = value;
        this.bitField0_ |= 0x8;
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
    
    private static final CSPing DEFAULT_INSTANCE = new CSPing();
    
    public static CSPing getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }
    
    private static final Parser<CSPing> PARSER = (Parser<CSPing>)new AbstractParser<CSPing>() {
        public CSPingOuterClass.CSPing parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
          CSPingOuterClass.CSPing.Builder builder = CSPingOuterClass.CSPing.newBuilder();
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
    
    public static Parser<CSPing> parser() {
      return PARSER;
    }
    
    public Parser<CSPing> getParserForType() {
      return PARSER;
    }
    
    public CSPing getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }
  }
  
  public static Descriptors.FileDescriptor getDescriptor() {
    return descriptor;
  }
  
  static {
    String[] descriptorData = { "\n\fCSPing.proto\032\016ClientId.proto\"Y\n\006CSPing\022\020\n\bechoData\030\001 \001(\t\022\033\n\bclientId\030\002 \001(\0162\t.ClientId\022\020\n\bdeviceId\030\003 \001(\t\022\016\n\006appVer\030\004 \001(\tB<\n:tech.ordinaryroad.live.chat.client.codec.kuaishou.protobufb\006proto3" };
    descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[] { ClientIdOuterClass.getDescriptor() });
    internal_static_CSPing_descriptor = getDescriptor().getMessageTypes().get(0);
    internal_static_CSPing_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_CSPing_descriptor, new String[] { "EchoData", "ClientId", "DeviceId", "AppVer" });
    ClientIdOuterClass.getDescriptor();
  }
  
  public static interface CSPingOrBuilder extends MessageOrBuilder {
    String getEchoData();
    
    ByteString getEchoDataBytes();
    
    int getClientIdValue();
    
    ClientIdOuterClass.ClientId getClientId();
    
    String getDeviceId();
    
    ByteString getDeviceIdBytes();
    
    String getAppVer();
    
    ByteString getAppVerBytes();
  }
}
