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
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class SCWebEnterRoomAckOuterClass {
  private static final Descriptors.Descriptor internal_static_SCWebEnterRoomAck_descriptor;
  
  private static final GeneratedMessageV3.FieldAccessorTable internal_static_SCWebEnterRoomAck_fieldAccessorTable;
  
  private static Descriptors.FileDescriptor descriptor;
  
  public static void registerAllExtensions(ExtensionRegistryLite registry) {}
  
  public static void registerAllExtensions(ExtensionRegistry registry) {
    registerAllExtensions((ExtensionRegistryLite)registry);
  }
  
  public static final class SCWebEnterRoomAck extends GeneratedMessageV3 implements SCWebEnterRoomAckOrBuilder {
    private static final long serialVersionUID = 0L;
    
    public static final int MINRECONNECTMS_FIELD_NUMBER = 1;
    
    private long minReconnectMs_;
    
    public static final int MAXRECONNECTMS_FIELD_NUMBER = 2;
    
    private long maxReconnectMs_;
    
    public static final int HEARTBEATINTERVALMS_FIELD_NUMBER = 3;
    
    private long heartbeatIntervalMs_;
    
    private byte memoizedIsInitialized;
    
    private SCWebEnterRoomAck(GeneratedMessageV3.Builder<?> builder) {
      super(builder);
      this.minReconnectMs_ = 0L;
      this.maxReconnectMs_ = 0L;
      this.heartbeatIntervalMs_ = 0L;
      this.memoizedIsInitialized = -1;
    }
    
    private SCWebEnterRoomAck() {
      this.minReconnectMs_ = 0L;
      this.maxReconnectMs_ = 0L;
      this.heartbeatIntervalMs_ = 0L;
      this.memoizedIsInitialized = -1;
    }
    
    protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
      return new SCWebEnterRoomAck();
    }
    
    public static final Descriptors.Descriptor getDescriptor() {
      return SCWebEnterRoomAckOuterClass.internal_static_SCWebEnterRoomAck_descriptor;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return SCWebEnterRoomAckOuterClass.internal_static_SCWebEnterRoomAck_fieldAccessorTable.ensureFieldAccessorsInitialized(SCWebEnterRoomAck.class, Builder.class);
    }
    
    public long getMinReconnectMs() {
      return this.minReconnectMs_;
    }
    
    public long getMaxReconnectMs() {
      return this.maxReconnectMs_;
    }
    
    public long getHeartbeatIntervalMs() {
      return this.heartbeatIntervalMs_;
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
      if (this.minReconnectMs_ != 0L)
        output.writeUInt64(1, this.minReconnectMs_); 
      if (this.maxReconnectMs_ != 0L)
        output.writeUInt64(2, this.maxReconnectMs_); 
      if (this.heartbeatIntervalMs_ != 0L)
        output.writeUInt64(3, this.heartbeatIntervalMs_); 
      getUnknownFields().writeTo(output);
    }
    
    public int getSerializedSize() {
      int size = this.memoizedSize;
      if (size != -1)
        return size; 
      size = 0;
      if (this.minReconnectMs_ != 0L)
        size += 
          CodedOutputStream.computeUInt64Size(1, this.minReconnectMs_); 
      if (this.maxReconnectMs_ != 0L)
        size += 
          CodedOutputStream.computeUInt64Size(2, this.maxReconnectMs_); 
      if (this.heartbeatIntervalMs_ != 0L)
        size += 
          CodedOutputStream.computeUInt64Size(3, this.heartbeatIntervalMs_); 
      size += getUnknownFields().getSerializedSize();
      this.memoizedSize = size;
      return size;
    }
    
    public boolean equals(Object obj) {
      if (obj == this)
        return true; 
      if (!(obj instanceof SCWebEnterRoomAck))
        return super.equals(obj); 
      SCWebEnterRoomAck other = (SCWebEnterRoomAck)obj;
      if (getMinReconnectMs() != other
        .getMinReconnectMs())
        return false; 
      if (getMaxReconnectMs() != other
        .getMaxReconnectMs())
        return false; 
      if (getHeartbeatIntervalMs() != other
        .getHeartbeatIntervalMs())
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
      hash = 53 * hash + Internal.hashLong(
          getMinReconnectMs());
      hash = 37 * hash + 2;
      hash = 53 * hash + Internal.hashLong(
          getMaxReconnectMs());
      hash = 37 * hash + 3;
      hash = 53 * hash + Internal.hashLong(
          getHeartbeatIntervalMs());
      hash = 29 * hash + getUnknownFields().hashCode();
      this.memoizedHashCode = hash;
      return hash;
    }
    
    public static SCWebEnterRoomAck parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (SCWebEnterRoomAck)PARSER.parseFrom(data);
    }
    
    public static SCWebEnterRoomAck parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (SCWebEnterRoomAck)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static SCWebEnterRoomAck parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (SCWebEnterRoomAck)PARSER.parseFrom(data);
    }
    
    public static SCWebEnterRoomAck parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (SCWebEnterRoomAck)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static SCWebEnterRoomAck parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (SCWebEnterRoomAck)PARSER.parseFrom(data);
    }
    
    public static SCWebEnterRoomAck parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (SCWebEnterRoomAck)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static SCWebEnterRoomAck parseFrom(InputStream input) throws IOException {
      return 
        (SCWebEnterRoomAck)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static SCWebEnterRoomAck parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (SCWebEnterRoomAck)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static SCWebEnterRoomAck parseDelimitedFrom(InputStream input) throws IOException {
      return 
        (SCWebEnterRoomAck)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }
    
    public static SCWebEnterRoomAck parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (SCWebEnterRoomAck)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static SCWebEnterRoomAck parseFrom(CodedInputStream input) throws IOException {
      return 
        (SCWebEnterRoomAck)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static SCWebEnterRoomAck parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (SCWebEnterRoomAck)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public Builder newBuilderForType() {
      return newBuilder();
    }
    
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    
    public static Builder newBuilder(SCWebEnterRoomAck prototype) {
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
    
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements SCWebEnterRoomAckOuterClass.SCWebEnterRoomAckOrBuilder {
      private int bitField0_;
      
      private long minReconnectMs_;
      
      private long maxReconnectMs_;
      
      private long heartbeatIntervalMs_;
      
      public static final Descriptors.Descriptor getDescriptor() {
        return SCWebEnterRoomAckOuterClass.internal_static_SCWebEnterRoomAck_descriptor;
      }
      
      protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return SCWebEnterRoomAckOuterClass.internal_static_SCWebEnterRoomAck_fieldAccessorTable
          .ensureFieldAccessorsInitialized(SCWebEnterRoomAckOuterClass.SCWebEnterRoomAck.class, Builder.class);
      }
      
      private Builder() {}
      
      private Builder(GeneratedMessageV3.BuilderParent parent) {
        super(parent);
      }
      
      public Builder clear() {
        super.clear();
        this.bitField0_ = 0;
        this.minReconnectMs_ = 0L;
        this.maxReconnectMs_ = 0L;
        this.heartbeatIntervalMs_ = 0L;
        return this;
      }
      
      public Descriptors.Descriptor getDescriptorForType() {
        return SCWebEnterRoomAckOuterClass.internal_static_SCWebEnterRoomAck_descriptor;
      }
      
      public SCWebEnterRoomAckOuterClass.SCWebEnterRoomAck getDefaultInstanceForType() {
        return SCWebEnterRoomAckOuterClass.SCWebEnterRoomAck.getDefaultInstance();
      }
      
      public SCWebEnterRoomAckOuterClass.SCWebEnterRoomAck build() {
        SCWebEnterRoomAckOuterClass.SCWebEnterRoomAck result = buildPartial();
        if (!result.isInitialized())
          throw newUninitializedMessageException(result); 
        return result;
      }
      
      public SCWebEnterRoomAckOuterClass.SCWebEnterRoomAck buildPartial() {
        SCWebEnterRoomAckOuterClass.SCWebEnterRoomAck result = new SCWebEnterRoomAckOuterClass.SCWebEnterRoomAck(this);
        if (this.bitField0_ != 0)
          buildPartial0(result); 
        onBuilt();
        return result;
      }
      
      private void buildPartial0(SCWebEnterRoomAckOuterClass.SCWebEnterRoomAck result) {
        int from_bitField0_ = this.bitField0_;
        if ((from_bitField0_ & 0x1) != 0)
          result.minReconnectMs_ = this.minReconnectMs_; 
        if ((from_bitField0_ & 0x2) != 0)
          result.maxReconnectMs_ = this.maxReconnectMs_; 
        if ((from_bitField0_ & 0x4) != 0)
          result.heartbeatIntervalMs_ = this.heartbeatIntervalMs_; 
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
        if (other instanceof SCWebEnterRoomAckOuterClass.SCWebEnterRoomAck)
          return mergeFrom((SCWebEnterRoomAckOuterClass.SCWebEnterRoomAck)other); 
        super.mergeFrom(other);
        return this;
      }
      
      public Builder mergeFrom(SCWebEnterRoomAckOuterClass.SCWebEnterRoomAck other) {
        if (other == SCWebEnterRoomAckOuterClass.SCWebEnterRoomAck.getDefaultInstance())
          return this; 
        if (other.getMinReconnectMs() != 0L)
          setMinReconnectMs(other.getMinReconnectMs()); 
        if (other.getMaxReconnectMs() != 0L)
          setMaxReconnectMs(other.getMaxReconnectMs()); 
        if (other.getHeartbeatIntervalMs() != 0L)
          setHeartbeatIntervalMs(other.getHeartbeatIntervalMs()); 
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
                this.minReconnectMs_ = input.readUInt64();
                this.bitField0_ |= 0x1;
                continue;
              case 16:
                this.maxReconnectMs_ = input.readUInt64();
                this.bitField0_ |= 0x2;
                continue;
              case 24:
                this.heartbeatIntervalMs_ = input.readUInt64();
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
      
      public long getMinReconnectMs() {
        return this.minReconnectMs_;
      }
      
      public Builder setMinReconnectMs(long value) {
        this.minReconnectMs_ = value;
        this.bitField0_ |= 0x1;
        onChanged();
        return this;
      }
      
      public Builder clearMinReconnectMs() {
        this.bitField0_ &= 0xFFFFFFFE;
        this.minReconnectMs_ = 0L;
        onChanged();
        return this;
      }
      
      public long getMaxReconnectMs() {
        return this.maxReconnectMs_;
      }
      
      public Builder setMaxReconnectMs(long value) {
        this.maxReconnectMs_ = value;
        this.bitField0_ |= 0x2;
        onChanged();
        return this;
      }
      
      public Builder clearMaxReconnectMs() {
        this.bitField0_ &= 0xFFFFFFFD;
        this.maxReconnectMs_ = 0L;
        onChanged();
        return this;
      }
      
      public long getHeartbeatIntervalMs() {
        return this.heartbeatIntervalMs_;
      }
      
      public Builder setHeartbeatIntervalMs(long value) {
        this.heartbeatIntervalMs_ = value;
        this.bitField0_ |= 0x4;
        onChanged();
        return this;
      }
      
      public Builder clearHeartbeatIntervalMs() {
        this.bitField0_ &= 0xFFFFFFFB;
        this.heartbeatIntervalMs_ = 0L;
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
    
    private static final SCWebEnterRoomAck DEFAULT_INSTANCE = new SCWebEnterRoomAck();
    
    public static SCWebEnterRoomAck getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }
    
    private static final Parser<SCWebEnterRoomAck> PARSER = (Parser<SCWebEnterRoomAck>)new AbstractParser<SCWebEnterRoomAck>() {
        public SCWebEnterRoomAckOuterClass.SCWebEnterRoomAck parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
          SCWebEnterRoomAckOuterClass.SCWebEnterRoomAck.Builder builder = SCWebEnterRoomAckOuterClass.SCWebEnterRoomAck.newBuilder();
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
    
    public static Parser<SCWebEnterRoomAck> parser() {
      return PARSER;
    }
    
    public Parser<SCWebEnterRoomAck> getParserForType() {
      return PARSER;
    }
    
    public SCWebEnterRoomAck getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }
  }
  
  public static Descriptors.FileDescriptor getDescriptor() {
    return descriptor;
  }
  
  static {
    String[] descriptorData = { "\n\027SCWebEnterRoomAck.proto\"`\n\021SCWebEnterRoomAck\022\026\n\016minReconnectMs\030\001 \001(\004\022\026\n\016maxReconnectMs\030\002 \001(\004\022\033\n\023heartbeatIntervalMs\030\003 \001(\004B<\n:tech.ordinaryroad.live.chat.client.codec.kuaishou.protobufb\006proto3" };
    descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[0]);
    internal_static_SCWebEnterRoomAck_descriptor = getDescriptor().getMessageTypes().get(0);
    internal_static_SCWebEnterRoomAck_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_SCWebEnterRoomAck_descriptor, new String[] { "MinReconnectMs", "MaxReconnectMs", "HeartbeatIntervalMs" });
  }
  
  public static interface SCWebEnterRoomAckOrBuilder extends MessageOrBuilder {
    long getMinReconnectMs();
    
    long getMaxReconnectMs();
    
    long getHeartbeatIntervalMs();
  }
}
