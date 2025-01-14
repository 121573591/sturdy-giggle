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

public final class SCWebHeartbeatAckOuterClass {
  private static final Descriptors.Descriptor internal_static_SCWebHeartbeatAck_descriptor;
  
  private static final GeneratedMessageV3.FieldAccessorTable internal_static_SCWebHeartbeatAck_fieldAccessorTable;
  
  private static Descriptors.FileDescriptor descriptor;
  
  public static void registerAllExtensions(ExtensionRegistryLite registry) {}
  
  public static void registerAllExtensions(ExtensionRegistry registry) {
    registerAllExtensions((ExtensionRegistryLite)registry);
  }
  
  public static final class SCWebHeartbeatAck extends GeneratedMessageV3 implements SCWebHeartbeatAckOrBuilder {
    private static final long serialVersionUID = 0L;
    
    public static final int TIMESTAMP_FIELD_NUMBER = 1;
    
    private long timestamp_;
    
    public static final int CLIENTTIMESTAMP_FIELD_NUMBER = 2;
    
    private long clientTimestamp_;
    
    private byte memoizedIsInitialized;
    
    private SCWebHeartbeatAck(GeneratedMessageV3.Builder<?> builder) {
      super(builder);
      this.timestamp_ = 0L;
      this.clientTimestamp_ = 0L;
      this.memoizedIsInitialized = -1;
    }
    
    private SCWebHeartbeatAck() {
      this.timestamp_ = 0L;
      this.clientTimestamp_ = 0L;
      this.memoizedIsInitialized = -1;
    }
    
    protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
      return new SCWebHeartbeatAck();
    }
    
    public static final Descriptors.Descriptor getDescriptor() {
      return SCWebHeartbeatAckOuterClass.internal_static_SCWebHeartbeatAck_descriptor;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return SCWebHeartbeatAckOuterClass.internal_static_SCWebHeartbeatAck_fieldAccessorTable.ensureFieldAccessorsInitialized(SCWebHeartbeatAck.class, Builder.class);
    }
    
    public long getTimestamp() {
      return this.timestamp_;
    }
    
    public long getClientTimestamp() {
      return this.clientTimestamp_;
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
      if (this.timestamp_ != 0L)
        output.writeUInt64(1, this.timestamp_); 
      if (this.clientTimestamp_ != 0L)
        output.writeUInt64(2, this.clientTimestamp_); 
      getUnknownFields().writeTo(output);
    }
    
    public int getSerializedSize() {
      int size = this.memoizedSize;
      if (size != -1)
        return size; 
      size = 0;
      if (this.timestamp_ != 0L)
        size += 
          CodedOutputStream.computeUInt64Size(1, this.timestamp_); 
      if (this.clientTimestamp_ != 0L)
        size += 
          CodedOutputStream.computeUInt64Size(2, this.clientTimestamp_); 
      size += getUnknownFields().getSerializedSize();
      this.memoizedSize = size;
      return size;
    }
    
    public boolean equals(Object obj) {
      if (obj == this)
        return true; 
      if (!(obj instanceof SCWebHeartbeatAck))
        return super.equals(obj); 
      SCWebHeartbeatAck other = (SCWebHeartbeatAck)obj;
      if (getTimestamp() != other
        .getTimestamp())
        return false; 
      if (getClientTimestamp() != other
        .getClientTimestamp())
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
          getTimestamp());
      hash = 37 * hash + 2;
      hash = 53 * hash + Internal.hashLong(
          getClientTimestamp());
      hash = 29 * hash + getUnknownFields().hashCode();
      this.memoizedHashCode = hash;
      return hash;
    }
    
    public static SCWebHeartbeatAck parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (SCWebHeartbeatAck)PARSER.parseFrom(data);
    }
    
    public static SCWebHeartbeatAck parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (SCWebHeartbeatAck)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static SCWebHeartbeatAck parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (SCWebHeartbeatAck)PARSER.parseFrom(data);
    }
    
    public static SCWebHeartbeatAck parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (SCWebHeartbeatAck)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static SCWebHeartbeatAck parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (SCWebHeartbeatAck)PARSER.parseFrom(data);
    }
    
    public static SCWebHeartbeatAck parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (SCWebHeartbeatAck)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static SCWebHeartbeatAck parseFrom(InputStream input) throws IOException {
      return 
        (SCWebHeartbeatAck)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static SCWebHeartbeatAck parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (SCWebHeartbeatAck)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static SCWebHeartbeatAck parseDelimitedFrom(InputStream input) throws IOException {
      return 
        (SCWebHeartbeatAck)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }
    
    public static SCWebHeartbeatAck parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (SCWebHeartbeatAck)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static SCWebHeartbeatAck parseFrom(CodedInputStream input) throws IOException {
      return 
        (SCWebHeartbeatAck)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static SCWebHeartbeatAck parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (SCWebHeartbeatAck)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public Builder newBuilderForType() {
      return newBuilder();
    }
    
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    
    public static Builder newBuilder(SCWebHeartbeatAck prototype) {
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
    
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements SCWebHeartbeatAckOuterClass.SCWebHeartbeatAckOrBuilder {
      private int bitField0_;
      
      private long timestamp_;
      
      private long clientTimestamp_;
      
      public static final Descriptors.Descriptor getDescriptor() {
        return SCWebHeartbeatAckOuterClass.internal_static_SCWebHeartbeatAck_descriptor;
      }
      
      protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return SCWebHeartbeatAckOuterClass.internal_static_SCWebHeartbeatAck_fieldAccessorTable
          .ensureFieldAccessorsInitialized(SCWebHeartbeatAckOuterClass.SCWebHeartbeatAck.class, Builder.class);
      }
      
      private Builder() {}
      
      private Builder(GeneratedMessageV3.BuilderParent parent) {
        super(parent);
      }
      
      public Builder clear() {
        super.clear();
        this.bitField0_ = 0;
        this.timestamp_ = 0L;
        this.clientTimestamp_ = 0L;
        return this;
      }
      
      public Descriptors.Descriptor getDescriptorForType() {
        return SCWebHeartbeatAckOuterClass.internal_static_SCWebHeartbeatAck_descriptor;
      }
      
      public SCWebHeartbeatAckOuterClass.SCWebHeartbeatAck getDefaultInstanceForType() {
        return SCWebHeartbeatAckOuterClass.SCWebHeartbeatAck.getDefaultInstance();
      }
      
      public SCWebHeartbeatAckOuterClass.SCWebHeartbeatAck build() {
        SCWebHeartbeatAckOuterClass.SCWebHeartbeatAck result = buildPartial();
        if (!result.isInitialized())
          throw newUninitializedMessageException(result); 
        return result;
      }
      
      public SCWebHeartbeatAckOuterClass.SCWebHeartbeatAck buildPartial() {
        SCWebHeartbeatAckOuterClass.SCWebHeartbeatAck result = new SCWebHeartbeatAckOuterClass.SCWebHeartbeatAck(this);
        if (this.bitField0_ != 0)
          buildPartial0(result); 
        onBuilt();
        return result;
      }
      
      private void buildPartial0(SCWebHeartbeatAckOuterClass.SCWebHeartbeatAck result) {
        int from_bitField0_ = this.bitField0_;
        if ((from_bitField0_ & 0x1) != 0)
          result.timestamp_ = this.timestamp_; 
        if ((from_bitField0_ & 0x2) != 0)
          result.clientTimestamp_ = this.clientTimestamp_; 
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
        if (other instanceof SCWebHeartbeatAckOuterClass.SCWebHeartbeatAck)
          return mergeFrom((SCWebHeartbeatAckOuterClass.SCWebHeartbeatAck)other); 
        super.mergeFrom(other);
        return this;
      }
      
      public Builder mergeFrom(SCWebHeartbeatAckOuterClass.SCWebHeartbeatAck other) {
        if (other == SCWebHeartbeatAckOuterClass.SCWebHeartbeatAck.getDefaultInstance())
          return this; 
        if (other.getTimestamp() != 0L)
          setTimestamp(other.getTimestamp()); 
        if (other.getClientTimestamp() != 0L)
          setClientTimestamp(other.getClientTimestamp()); 
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
                this.timestamp_ = input.readUInt64();
                this.bitField0_ |= 0x1;
                continue;
              case 16:
                this.clientTimestamp_ = input.readUInt64();
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
      
      public long getTimestamp() {
        return this.timestamp_;
      }
      
      public Builder setTimestamp(long value) {
        this.timestamp_ = value;
        this.bitField0_ |= 0x1;
        onChanged();
        return this;
      }
      
      public Builder clearTimestamp() {
        this.bitField0_ &= 0xFFFFFFFE;
        this.timestamp_ = 0L;
        onChanged();
        return this;
      }
      
      public long getClientTimestamp() {
        return this.clientTimestamp_;
      }
      
      public Builder setClientTimestamp(long value) {
        this.clientTimestamp_ = value;
        this.bitField0_ |= 0x2;
        onChanged();
        return this;
      }
      
      public Builder clearClientTimestamp() {
        this.bitField0_ &= 0xFFFFFFFD;
        this.clientTimestamp_ = 0L;
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
    
    private static final SCWebHeartbeatAck DEFAULT_INSTANCE = new SCWebHeartbeatAck();
    
    public static SCWebHeartbeatAck getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }
    
    private static final Parser<SCWebHeartbeatAck> PARSER = (Parser<SCWebHeartbeatAck>)new AbstractParser<SCWebHeartbeatAck>() {
        public SCWebHeartbeatAckOuterClass.SCWebHeartbeatAck parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
          SCWebHeartbeatAckOuterClass.SCWebHeartbeatAck.Builder builder = SCWebHeartbeatAckOuterClass.SCWebHeartbeatAck.newBuilder();
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
    
    public static Parser<SCWebHeartbeatAck> parser() {
      return PARSER;
    }
    
    public Parser<SCWebHeartbeatAck> getParserForType() {
      return PARSER;
    }
    
    public SCWebHeartbeatAck getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }
  }
  
  public static Descriptors.FileDescriptor getDescriptor() {
    return descriptor;
  }
  
  static {
    String[] descriptorData = { "\n\027SCWebHeartbeatAck.proto\"?\n\021SCWebHeartbeatAck\022\021\n\ttimestamp\030\001 \001(\004\022\027\n\017clientTimestamp\030\002 \001(\004B<\n:tech.ordinaryroad.live.chat.client.codec.kuaishou.protobufb\006proto3" };
    descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[0]);
    internal_static_SCWebHeartbeatAck_descriptor = getDescriptor().getMessageTypes().get(0);
    internal_static_SCWebHeartbeatAck_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_SCWebHeartbeatAck_descriptor, new String[] { "Timestamp", "ClientTimestamp" });
  }
  
  public static interface SCWebHeartbeatAckOrBuilder extends MessageOrBuilder {
    long getTimestamp();
    
    long getClientTimestamp();
  }
}
