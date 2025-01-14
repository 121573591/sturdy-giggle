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

public final class CSHeartbeatOuterClass {
  private static final Descriptors.Descriptor internal_static_CSHeartbeat_descriptor;
  
  private static final GeneratedMessageV3.FieldAccessorTable internal_static_CSHeartbeat_fieldAccessorTable;
  
  private static Descriptors.FileDescriptor descriptor;
  
  public static void registerAllExtensions(ExtensionRegistryLite registry) {}
  
  public static void registerAllExtensions(ExtensionRegistry registry) {
    registerAllExtensions((ExtensionRegistryLite)registry);
  }
  
  public static final class CSHeartbeat extends GeneratedMessageV3 implements CSHeartbeatOrBuilder {
    private static final long serialVersionUID = 0L;
    
    public static final int TIMESTAMP_FIELD_NUMBER = 1;
    
    private long timestamp_;
    
    private byte memoizedIsInitialized;
    
    private CSHeartbeat(GeneratedMessageV3.Builder<?> builder) {
      super(builder);
      this.timestamp_ = 0L;
      this.memoizedIsInitialized = -1;
    }
    
    private CSHeartbeat() {
      this.timestamp_ = 0L;
      this.memoizedIsInitialized = -1;
    }
    
    protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
      return new CSHeartbeat();
    }
    
    public static final Descriptors.Descriptor getDescriptor() {
      return CSHeartbeatOuterClass.internal_static_CSHeartbeat_descriptor;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return CSHeartbeatOuterClass.internal_static_CSHeartbeat_fieldAccessorTable.ensureFieldAccessorsInitialized(CSHeartbeat.class, Builder.class);
    }
    
    public long getTimestamp() {
      return this.timestamp_;
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
      size += getUnknownFields().getSerializedSize();
      this.memoizedSize = size;
      return size;
    }
    
    public boolean equals(Object obj) {
      if (obj == this)
        return true; 
      if (!(obj instanceof CSHeartbeat))
        return super.equals(obj); 
      CSHeartbeat other = (CSHeartbeat)obj;
      if (getTimestamp() != other
        .getTimestamp())
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
      hash = 29 * hash + getUnknownFields().hashCode();
      this.memoizedHashCode = hash;
      return hash;
    }
    
    public static CSHeartbeat parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (CSHeartbeat)PARSER.parseFrom(data);
    }
    
    public static CSHeartbeat parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (CSHeartbeat)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static CSHeartbeat parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (CSHeartbeat)PARSER.parseFrom(data);
    }
    
    public static CSHeartbeat parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (CSHeartbeat)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static CSHeartbeat parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (CSHeartbeat)PARSER.parseFrom(data);
    }
    
    public static CSHeartbeat parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (CSHeartbeat)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static CSHeartbeat parseFrom(InputStream input) throws IOException {
      return 
        (CSHeartbeat)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static CSHeartbeat parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (CSHeartbeat)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static CSHeartbeat parseDelimitedFrom(InputStream input) throws IOException {
      return 
        (CSHeartbeat)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }
    
    public static CSHeartbeat parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (CSHeartbeat)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static CSHeartbeat parseFrom(CodedInputStream input) throws IOException {
      return 
        (CSHeartbeat)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static CSHeartbeat parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (CSHeartbeat)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public Builder newBuilderForType() {
      return newBuilder();
    }
    
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    
    public static Builder newBuilder(CSHeartbeat prototype) {
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
    
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements CSHeartbeatOuterClass.CSHeartbeatOrBuilder {
      private int bitField0_;
      
      private long timestamp_;
      
      public static final Descriptors.Descriptor getDescriptor() {
        return CSHeartbeatOuterClass.internal_static_CSHeartbeat_descriptor;
      }
      
      protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return CSHeartbeatOuterClass.internal_static_CSHeartbeat_fieldAccessorTable
          .ensureFieldAccessorsInitialized(CSHeartbeatOuterClass.CSHeartbeat.class, Builder.class);
      }
      
      private Builder() {}
      
      private Builder(GeneratedMessageV3.BuilderParent parent) {
        super(parent);
      }
      
      public Builder clear() {
        super.clear();
        this.bitField0_ = 0;
        this.timestamp_ = 0L;
        return this;
      }
      
      public Descriptors.Descriptor getDescriptorForType() {
        return CSHeartbeatOuterClass.internal_static_CSHeartbeat_descriptor;
      }
      
      public CSHeartbeatOuterClass.CSHeartbeat getDefaultInstanceForType() {
        return CSHeartbeatOuterClass.CSHeartbeat.getDefaultInstance();
      }
      
      public CSHeartbeatOuterClass.CSHeartbeat build() {
        CSHeartbeatOuterClass.CSHeartbeat result = buildPartial();
        if (!result.isInitialized())
          throw newUninitializedMessageException(result); 
        return result;
      }
      
      public CSHeartbeatOuterClass.CSHeartbeat buildPartial() {
        CSHeartbeatOuterClass.CSHeartbeat result = new CSHeartbeatOuterClass.CSHeartbeat(this);
        if (this.bitField0_ != 0)
          buildPartial0(result); 
        onBuilt();
        return result;
      }
      
      private void buildPartial0(CSHeartbeatOuterClass.CSHeartbeat result) {
        int from_bitField0_ = this.bitField0_;
        if ((from_bitField0_ & 0x1) != 0)
          result.timestamp_ = this.timestamp_; 
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
        if (other instanceof CSHeartbeatOuterClass.CSHeartbeat)
          return mergeFrom((CSHeartbeatOuterClass.CSHeartbeat)other); 
        super.mergeFrom(other);
        return this;
      }
      
      public Builder mergeFrom(CSHeartbeatOuterClass.CSHeartbeat other) {
        if (other == CSHeartbeatOuterClass.CSHeartbeat.getDefaultInstance())
          return this; 
        if (other.getTimestamp() != 0L)
          setTimestamp(other.getTimestamp()); 
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
      
      public final Builder setUnknownFields(UnknownFieldSet unknownFields) {
        return (Builder)super.setUnknownFields(unknownFields);
      }
      
      public final Builder mergeUnknownFields(UnknownFieldSet unknownFields) {
        return (Builder)super.mergeUnknownFields(unknownFields);
      }
    }
    
    private static final CSHeartbeat DEFAULT_INSTANCE = new CSHeartbeat();
    
    public static CSHeartbeat getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }
    
    private static final Parser<CSHeartbeat> PARSER = (Parser<CSHeartbeat>)new AbstractParser<CSHeartbeat>() {
        public CSHeartbeatOuterClass.CSHeartbeat parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
          CSHeartbeatOuterClass.CSHeartbeat.Builder builder = CSHeartbeatOuterClass.CSHeartbeat.newBuilder();
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
    
    public static Parser<CSHeartbeat> parser() {
      return PARSER;
    }
    
    public Parser<CSHeartbeat> getParserForType() {
      return PARSER;
    }
    
    public CSHeartbeat getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }
  }
  
  public static Descriptors.FileDescriptor getDescriptor() {
    return descriptor;
  }
  
  static {
    String[] descriptorData = { "\n\021CSHeartbeat.proto\" \n\013CSHeartbeat\022\021\n\ttimestamp\030\001 \001(\004B<\n:tech.ordinaryroad.live.chat.client.codec.kuaishou.protobufb\006proto3" };
    descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[0]);
    internal_static_CSHeartbeat_descriptor = getDescriptor().getMessageTypes().get(0);
    internal_static_CSHeartbeat_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_CSHeartbeat_descriptor, new String[] { "Timestamp" });
  }
  
  public static interface CSHeartbeatOrBuilder extends MessageOrBuilder {
    long getTimestamp();
  }
}
