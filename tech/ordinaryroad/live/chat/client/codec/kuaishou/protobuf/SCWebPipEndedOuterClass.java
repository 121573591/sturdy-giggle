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

public final class SCWebPipEndedOuterClass {
  private static final Descriptors.Descriptor internal_static_SCWebPipEnded_descriptor;
  
  private static final GeneratedMessageV3.FieldAccessorTable internal_static_SCWebPipEnded_fieldAccessorTable;
  
  private static Descriptors.FileDescriptor descriptor;
  
  public static void registerAllExtensions(ExtensionRegistryLite registry) {}
  
  public static void registerAllExtensions(ExtensionRegistry registry) {
    registerAllExtensions((ExtensionRegistryLite)registry);
  }
  
  public static final class SCWebPipEnded extends GeneratedMessageV3 implements SCWebPipEndedOrBuilder {
    private static final long serialVersionUID = 0L;
    
    public static final int TIME_FIELD_NUMBER = 1;
    
    private long time_;
    
    private byte memoizedIsInitialized;
    
    private SCWebPipEnded(GeneratedMessageV3.Builder<?> builder) {
      super(builder);
      this.time_ = 0L;
      this.memoizedIsInitialized = -1;
    }
    
    private SCWebPipEnded() {
      this.time_ = 0L;
      this.memoizedIsInitialized = -1;
    }
    
    protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
      return new SCWebPipEnded();
    }
    
    public static final Descriptors.Descriptor getDescriptor() {
      return SCWebPipEndedOuterClass.internal_static_SCWebPipEnded_descriptor;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return SCWebPipEndedOuterClass.internal_static_SCWebPipEnded_fieldAccessorTable.ensureFieldAccessorsInitialized(SCWebPipEnded.class, Builder.class);
    }
    
    public long getTime() {
      return this.time_;
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
      if (this.time_ != 0L)
        output.writeUInt64(1, this.time_); 
      getUnknownFields().writeTo(output);
    }
    
    public int getSerializedSize() {
      int size = this.memoizedSize;
      if (size != -1)
        return size; 
      size = 0;
      if (this.time_ != 0L)
        size += 
          CodedOutputStream.computeUInt64Size(1, this.time_); 
      size += getUnknownFields().getSerializedSize();
      this.memoizedSize = size;
      return size;
    }
    
    public boolean equals(Object obj) {
      if (obj == this)
        return true; 
      if (!(obj instanceof SCWebPipEnded))
        return super.equals(obj); 
      SCWebPipEnded other = (SCWebPipEnded)obj;
      if (getTime() != other
        .getTime())
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
          getTime());
      hash = 29 * hash + getUnknownFields().hashCode();
      this.memoizedHashCode = hash;
      return hash;
    }
    
    public static SCWebPipEnded parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (SCWebPipEnded)PARSER.parseFrom(data);
    }
    
    public static SCWebPipEnded parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (SCWebPipEnded)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static SCWebPipEnded parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (SCWebPipEnded)PARSER.parseFrom(data);
    }
    
    public static SCWebPipEnded parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (SCWebPipEnded)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static SCWebPipEnded parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (SCWebPipEnded)PARSER.parseFrom(data);
    }
    
    public static SCWebPipEnded parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (SCWebPipEnded)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static SCWebPipEnded parseFrom(InputStream input) throws IOException {
      return 
        (SCWebPipEnded)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static SCWebPipEnded parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (SCWebPipEnded)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static SCWebPipEnded parseDelimitedFrom(InputStream input) throws IOException {
      return 
        (SCWebPipEnded)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }
    
    public static SCWebPipEnded parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (SCWebPipEnded)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static SCWebPipEnded parseFrom(CodedInputStream input) throws IOException {
      return 
        (SCWebPipEnded)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static SCWebPipEnded parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (SCWebPipEnded)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public Builder newBuilderForType() {
      return newBuilder();
    }
    
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    
    public static Builder newBuilder(SCWebPipEnded prototype) {
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
    
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements SCWebPipEndedOuterClass.SCWebPipEndedOrBuilder {
      private int bitField0_;
      
      private long time_;
      
      public static final Descriptors.Descriptor getDescriptor() {
        return SCWebPipEndedOuterClass.internal_static_SCWebPipEnded_descriptor;
      }
      
      protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return SCWebPipEndedOuterClass.internal_static_SCWebPipEnded_fieldAccessorTable
          .ensureFieldAccessorsInitialized(SCWebPipEndedOuterClass.SCWebPipEnded.class, Builder.class);
      }
      
      private Builder() {}
      
      private Builder(GeneratedMessageV3.BuilderParent parent) {
        super(parent);
      }
      
      public Builder clear() {
        super.clear();
        this.bitField0_ = 0;
        this.time_ = 0L;
        return this;
      }
      
      public Descriptors.Descriptor getDescriptorForType() {
        return SCWebPipEndedOuterClass.internal_static_SCWebPipEnded_descriptor;
      }
      
      public SCWebPipEndedOuterClass.SCWebPipEnded getDefaultInstanceForType() {
        return SCWebPipEndedOuterClass.SCWebPipEnded.getDefaultInstance();
      }
      
      public SCWebPipEndedOuterClass.SCWebPipEnded build() {
        SCWebPipEndedOuterClass.SCWebPipEnded result = buildPartial();
        if (!result.isInitialized())
          throw newUninitializedMessageException(result); 
        return result;
      }
      
      public SCWebPipEndedOuterClass.SCWebPipEnded buildPartial() {
        SCWebPipEndedOuterClass.SCWebPipEnded result = new SCWebPipEndedOuterClass.SCWebPipEnded(this);
        if (this.bitField0_ != 0)
          buildPartial0(result); 
        onBuilt();
        return result;
      }
      
      private void buildPartial0(SCWebPipEndedOuterClass.SCWebPipEnded result) {
        int from_bitField0_ = this.bitField0_;
        if ((from_bitField0_ & 0x1) != 0)
          result.time_ = this.time_; 
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
        if (other instanceof SCWebPipEndedOuterClass.SCWebPipEnded)
          return mergeFrom((SCWebPipEndedOuterClass.SCWebPipEnded)other); 
        super.mergeFrom(other);
        return this;
      }
      
      public Builder mergeFrom(SCWebPipEndedOuterClass.SCWebPipEnded other) {
        if (other == SCWebPipEndedOuterClass.SCWebPipEnded.getDefaultInstance())
          return this; 
        if (other.getTime() != 0L)
          setTime(other.getTime()); 
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
                this.time_ = input.readUInt64();
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
      
      public long getTime() {
        return this.time_;
      }
      
      public Builder setTime(long value) {
        this.time_ = value;
        this.bitField0_ |= 0x1;
        onChanged();
        return this;
      }
      
      public Builder clearTime() {
        this.bitField0_ &= 0xFFFFFFFE;
        this.time_ = 0L;
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
    
    private static final SCWebPipEnded DEFAULT_INSTANCE = new SCWebPipEnded();
    
    public static SCWebPipEnded getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }
    
    private static final Parser<SCWebPipEnded> PARSER = (Parser<SCWebPipEnded>)new AbstractParser<SCWebPipEnded>() {
        public SCWebPipEndedOuterClass.SCWebPipEnded parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
          SCWebPipEndedOuterClass.SCWebPipEnded.Builder builder = SCWebPipEndedOuterClass.SCWebPipEnded.newBuilder();
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
    
    public static Parser<SCWebPipEnded> parser() {
      return PARSER;
    }
    
    public Parser<SCWebPipEnded> getParserForType() {
      return PARSER;
    }
    
    public SCWebPipEnded getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }
  }
  
  public static Descriptors.FileDescriptor getDescriptor() {
    return descriptor;
  }
  
  static {
    String[] descriptorData = { "\n\023SCWebPipEnded.proto\"\035\n\rSCWebPipEnded\022\f\n\004time\030\001 \001(\004B<\n:tech.ordinaryroad.live.chat.client.codec.kuaishou.protobufb\006proto3" };
    descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[0]);
    internal_static_SCWebPipEnded_descriptor = getDescriptor().getMessageTypes().get(0);
    internal_static_SCWebPipEnded_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_SCWebPipEnded_descriptor, new String[] { "Time" });
  }
  
  public static interface SCWebPipEndedOrBuilder extends MessageOrBuilder {
    long getTime();
  }
}
