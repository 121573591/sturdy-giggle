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
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.ProtocolMessageEnum;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class SocketMessageOuterClass {
  private static final Descriptors.Descriptor internal_static_SocketMessage_descriptor;
  
  private static final GeneratedMessageV3.FieldAccessorTable internal_static_SocketMessage_fieldAccessorTable;
  
  private static Descriptors.FileDescriptor descriptor;
  
  public static void registerAllExtensions(ExtensionRegistryLite registry) {}
  
  public static void registerAllExtensions(ExtensionRegistry registry) {
    registerAllExtensions((ExtensionRegistryLite)registry);
  }
  
  public static final class SocketMessage extends GeneratedMessageV3 implements SocketMessageOrBuilder {
    private static final long serialVersionUID = 0L;
    
    public static final int PAYLOADTYPE_FIELD_NUMBER = 1;
    
    private int payloadType_;
    
    public static final int COMPRESSIONTYPE_FIELD_NUMBER = 2;
    
    private int compressionType_;
    
    public static final int PAYLOAD_FIELD_NUMBER = 3;
    
    private ByteString payload_;
    
    private byte memoizedIsInitialized;
    
    private SocketMessage(GeneratedMessageV3.Builder<?> builder) {
      super(builder);
      this.payloadType_ = 0;
      this.compressionType_ = 0;
      this.payload_ = ByteString.EMPTY;
      this.memoizedIsInitialized = -1;
    }
    
    private SocketMessage() {
      this.payloadType_ = 0;
      this.compressionType_ = 0;
      this.payload_ = ByteString.EMPTY;
      this.memoizedIsInitialized = -1;
      this.payloadType_ = 0;
      this.compressionType_ = 0;
      this.payload_ = ByteString.EMPTY;
    }
    
    protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
      return new SocketMessage();
    }
    
    public static final Descriptors.Descriptor getDescriptor() {
      return SocketMessageOuterClass.internal_static_SocketMessage_descriptor;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return SocketMessageOuterClass.internal_static_SocketMessage_fieldAccessorTable.ensureFieldAccessorsInitialized(SocketMessage.class, Builder.class);
    }
    
    public enum CompressionType implements ProtocolMessageEnum {
      UNKNOWN(0),
      NONE(1),
      GZIP(2),
      AES(3),
      UNRECOGNIZED(-1);
      
      public static final int UNKNOWN_VALUE = 0;
      
      public static final int NONE_VALUE = 1;
      
      public static final int GZIP_VALUE = 2;
      
      public static final int AES_VALUE = 3;
      
      private static final Internal.EnumLiteMap<CompressionType> internalValueMap = new Internal.EnumLiteMap<CompressionType>() {
          public SocketMessageOuterClass.SocketMessage.CompressionType findValueByNumber(int number) {
            return SocketMessageOuterClass.SocketMessage.CompressionType.forNumber(number);
          }
        };
      
      private static final CompressionType[] VALUES = values();
      
      private final int value;
      
      public final int getNumber() {
        if (this == UNRECOGNIZED)
          throw new IllegalArgumentException("Can't get the number of an unknown enum value."); 
        return this.value;
      }
      
      public static CompressionType forNumber(int value) {
        switch (value) {
          case 0:
            return UNKNOWN;
          case 1:
            return NONE;
          case 2:
            return GZIP;
          case 3:
            return AES;
        } 
        return null;
      }
      
      public static Internal.EnumLiteMap<CompressionType> internalGetValueMap() {
        return internalValueMap;
      }
      
      static {
      
      }
      
      public final Descriptors.EnumValueDescriptor getValueDescriptor() {
        if (this == UNRECOGNIZED)
          throw new IllegalStateException("Can't get the descriptor of an unrecognized enum value."); 
        return getDescriptor().getValues().get(ordinal());
      }
      
      public final Descriptors.EnumDescriptor getDescriptorForType() {
        return getDescriptor();
      }
      
      public static final Descriptors.EnumDescriptor getDescriptor() {
        return SocketMessageOuterClass.SocketMessage.getDescriptor().getEnumTypes().get(0);
      }
      
      CompressionType(int value) {
        this.value = value;
      }
    }
    
    public int getPayloadTypeValue() {
      return this.payloadType_;
    }
    
    public PayloadTypeOuterClass.PayloadType getPayloadType() {
      PayloadTypeOuterClass.PayloadType result = PayloadTypeOuterClass.PayloadType.forNumber(this.payloadType_);
      return (result == null) ? PayloadTypeOuterClass.PayloadType.UNRECOGNIZED : result;
    }
    
    public int getCompressionTypeValue() {
      return this.compressionType_;
    }
    
    public CompressionType getCompressionType() {
      CompressionType result = CompressionType.forNumber(this.compressionType_);
      return (result == null) ? CompressionType.UNRECOGNIZED : result;
    }
    
    public ByteString getPayload() {
      return this.payload_;
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
      if (this.payloadType_ != PayloadTypeOuterClass.PayloadType.UNKNOWN.getNumber())
        output.writeEnum(1, this.payloadType_); 
      if (this.compressionType_ != CompressionType.UNKNOWN.getNumber())
        output.writeEnum(2, this.compressionType_); 
      if (!this.payload_.isEmpty())
        output.writeBytes(3, this.payload_); 
      getUnknownFields().writeTo(output);
    }
    
    public int getSerializedSize() {
      int size = this.memoizedSize;
      if (size != -1)
        return size; 
      size = 0;
      if (this.payloadType_ != PayloadTypeOuterClass.PayloadType.UNKNOWN.getNumber())
        size += 
          CodedOutputStream.computeEnumSize(1, this.payloadType_); 
      if (this.compressionType_ != CompressionType.UNKNOWN.getNumber())
        size += 
          CodedOutputStream.computeEnumSize(2, this.compressionType_); 
      if (!this.payload_.isEmpty())
        size += 
          CodedOutputStream.computeBytesSize(3, this.payload_); 
      size += getUnknownFields().getSerializedSize();
      this.memoizedSize = size;
      return size;
    }
    
    public boolean equals(Object obj) {
      if (obj == this)
        return true; 
      if (!(obj instanceof SocketMessage))
        return super.equals(obj); 
      SocketMessage other = (SocketMessage)obj;
      if (this.payloadType_ != other.payloadType_)
        return false; 
      if (this.compressionType_ != other.compressionType_)
        return false; 
      if (!getPayload().equals(other.getPayload()))
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
      hash = 53 * hash + this.payloadType_;
      hash = 37 * hash + 2;
      hash = 53 * hash + this.compressionType_;
      hash = 37 * hash + 3;
      hash = 53 * hash + getPayload().hashCode();
      hash = 29 * hash + getUnknownFields().hashCode();
      this.memoizedHashCode = hash;
      return hash;
    }
    
    public static SocketMessage parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (SocketMessage)PARSER.parseFrom(data);
    }
    
    public static SocketMessage parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (SocketMessage)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static SocketMessage parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (SocketMessage)PARSER.parseFrom(data);
    }
    
    public static SocketMessage parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (SocketMessage)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static SocketMessage parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (SocketMessage)PARSER.parseFrom(data);
    }
    
    public static SocketMessage parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (SocketMessage)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static SocketMessage parseFrom(InputStream input) throws IOException {
      return 
        (SocketMessage)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static SocketMessage parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (SocketMessage)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static SocketMessage parseDelimitedFrom(InputStream input) throws IOException {
      return 
        (SocketMessage)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }
    
    public static SocketMessage parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (SocketMessage)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static SocketMessage parseFrom(CodedInputStream input) throws IOException {
      return 
        (SocketMessage)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static SocketMessage parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (SocketMessage)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public Builder newBuilderForType() {
      return newBuilder();
    }
    
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    
    public static Builder newBuilder(SocketMessage prototype) {
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
    
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements SocketMessageOuterClass.SocketMessageOrBuilder {
      private int bitField0_;
      
      private int payloadType_;
      
      private int compressionType_;
      
      private ByteString payload_;
      
      public static final Descriptors.Descriptor getDescriptor() {
        return SocketMessageOuterClass.internal_static_SocketMessage_descriptor;
      }
      
      protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return SocketMessageOuterClass.internal_static_SocketMessage_fieldAccessorTable
          .ensureFieldAccessorsInitialized(SocketMessageOuterClass.SocketMessage.class, Builder.class);
      }
      
      private Builder() {
        this.payloadType_ = 0;
        this.compressionType_ = 0;
        this.payload_ = ByteString.EMPTY;
      }
      
      private Builder(GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        this.payloadType_ = 0;
        this.compressionType_ = 0;
        this.payload_ = ByteString.EMPTY;
      }
      
      public Builder clear() {
        super.clear();
        this.bitField0_ = 0;
        this.payloadType_ = 0;
        this.compressionType_ = 0;
        this.payload_ = ByteString.EMPTY;
        return this;
      }
      
      public Descriptors.Descriptor getDescriptorForType() {
        return SocketMessageOuterClass.internal_static_SocketMessage_descriptor;
      }
      
      public SocketMessageOuterClass.SocketMessage getDefaultInstanceForType() {
        return SocketMessageOuterClass.SocketMessage.getDefaultInstance();
      }
      
      public SocketMessageOuterClass.SocketMessage build() {
        SocketMessageOuterClass.SocketMessage result = buildPartial();
        if (!result.isInitialized())
          throw newUninitializedMessageException(result); 
        return result;
      }
      
      public SocketMessageOuterClass.SocketMessage buildPartial() {
        SocketMessageOuterClass.SocketMessage result = new SocketMessageOuterClass.SocketMessage(this);
        if (this.bitField0_ != 0)
          buildPartial0(result); 
        onBuilt();
        return result;
      }
      
      private void buildPartial0(SocketMessageOuterClass.SocketMessage result) {
        int from_bitField0_ = this.bitField0_;
        if ((from_bitField0_ & 0x1) != 0)
          result.payloadType_ = this.payloadType_; 
        if ((from_bitField0_ & 0x2) != 0)
          result.compressionType_ = this.compressionType_; 
        if ((from_bitField0_ & 0x4) != 0)
          result.payload_ = this.payload_; 
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
        if (other instanceof SocketMessageOuterClass.SocketMessage)
          return mergeFrom((SocketMessageOuterClass.SocketMessage)other); 
        super.mergeFrom(other);
        return this;
      }
      
      public Builder mergeFrom(SocketMessageOuterClass.SocketMessage other) {
        if (other == SocketMessageOuterClass.SocketMessage.getDefaultInstance())
          return this; 
        if (other.payloadType_ != 0)
          setPayloadTypeValue(other.getPayloadTypeValue()); 
        if (other.compressionType_ != 0)
          setCompressionTypeValue(other.getCompressionTypeValue()); 
        if (other.getPayload() != ByteString.EMPTY)
          setPayload(other.getPayload()); 
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
                this.payloadType_ = input.readEnum();
                this.bitField0_ |= 0x1;
                continue;
              case 16:
                this.compressionType_ = input.readEnum();
                this.bitField0_ |= 0x2;
                continue;
              case 26:
                this.payload_ = input.readBytes();
                this.bitField0_ |= 0x4;
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
      
      public int getPayloadTypeValue() {
        return this.payloadType_;
      }
      
      public Builder setPayloadTypeValue(int value) {
        this.payloadType_ = value;
        this.bitField0_ |= 0x1;
        onChanged();
        return this;
      }
      
      public PayloadTypeOuterClass.PayloadType getPayloadType() {
        PayloadTypeOuterClass.PayloadType result = PayloadTypeOuterClass.PayloadType.forNumber(this.payloadType_);
        return (result == null) ? PayloadTypeOuterClass.PayloadType.UNRECOGNIZED : result;
      }
      
      public Builder setPayloadType(PayloadTypeOuterClass.PayloadType value) {
        if (value == null)
          throw new NullPointerException(); 
        this.bitField0_ |= 0x1;
        this.payloadType_ = value.getNumber();
        onChanged();
        return this;
      }
      
      public Builder clearPayloadType() {
        this.bitField0_ &= 0xFFFFFFFE;
        this.payloadType_ = 0;
        onChanged();
        return this;
      }
      
      public int getCompressionTypeValue() {
        return this.compressionType_;
      }
      
      public Builder setCompressionTypeValue(int value) {
        this.compressionType_ = value;
        this.bitField0_ |= 0x2;
        onChanged();
        return this;
      }
      
      public SocketMessageOuterClass.SocketMessage.CompressionType getCompressionType() {
        SocketMessageOuterClass.SocketMessage.CompressionType result = SocketMessageOuterClass.SocketMessage.CompressionType.forNumber(this.compressionType_);
        return (result == null) ? SocketMessageOuterClass.SocketMessage.CompressionType.UNRECOGNIZED : result;
      }
      
      public Builder setCompressionType(SocketMessageOuterClass.SocketMessage.CompressionType value) {
        if (value == null)
          throw new NullPointerException(); 
        this.bitField0_ |= 0x2;
        this.compressionType_ = value.getNumber();
        onChanged();
        return this;
      }
      
      public Builder clearCompressionType() {
        this.bitField0_ &= 0xFFFFFFFD;
        this.compressionType_ = 0;
        onChanged();
        return this;
      }
      
      public ByteString getPayload() {
        return this.payload_;
      }
      
      public Builder setPayload(ByteString value) {
        if (value == null)
          throw new NullPointerException(); 
        this.payload_ = value;
        this.bitField0_ |= 0x4;
        onChanged();
        return this;
      }
      
      public Builder clearPayload() {
        this.bitField0_ &= 0xFFFFFFFB;
        this.payload_ = SocketMessageOuterClass.SocketMessage.getDefaultInstance().getPayload();
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
    
    private static final SocketMessage DEFAULT_INSTANCE = new SocketMessage();
    
    public static SocketMessage getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }
    
    private static final Parser<SocketMessage> PARSER = (Parser<SocketMessage>)new AbstractParser<SocketMessage>() {
        public SocketMessageOuterClass.SocketMessage parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
          SocketMessageOuterClass.SocketMessage.Builder builder = SocketMessageOuterClass.SocketMessage.newBuilder();
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
    
    public static Parser<SocketMessage> parser() {
      return PARSER;
    }
    
    public Parser<SocketMessage> getParserForType() {
      return PARSER;
    }
    
    public SocketMessage getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }
  }
  
  public static Descriptors.FileDescriptor getDescriptor() {
    return descriptor;
  }
  
  static {
    String[] descriptorData = { "\n\023SocketMessage.proto\032\021PayloadType.proto\"¹\001\n\rSocketMessage\022!\n\013payloadType\030\001 \001(\0162\f.PayloadType\0227\n\017compressionType\030\002 \001(\0162\036.SocketMessage.CompressionType\022\017\n\007payload\030\003 \001(\f\";\n\017CompressionType\022\013\n\007UNKNOWN\020\000\022\b\n\004NONE\020\001\022\b\n\004GZIP\020\002\022\007\n\003AES\020\003B<\n:tech.ordinaryroad.live.chat.client.codec.kuaishou.protobufb\006proto3" };
    descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[] { PayloadTypeOuterClass.getDescriptor() });
    internal_static_SocketMessage_descriptor = getDescriptor().getMessageTypes().get(0);
    internal_static_SocketMessage_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_SocketMessage_descriptor, new String[] { "PayloadType", "CompressionType", "Payload" });
    PayloadTypeOuterClass.getDescriptor();
  }
  
  public static interface SocketMessageOrBuilder extends MessageOrBuilder {
    int getPayloadTypeValue();
    
    PayloadTypeOuterClass.PayloadType getPayloadType();
    
    int getCompressionTypeValue();
    
    SocketMessageOuterClass.SocketMessage.CompressionType getCompressionType();
    
    ByteString getPayload();
  }
}
