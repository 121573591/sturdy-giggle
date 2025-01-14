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

public final class SCWebBetChangedOuterClass {
  private static final Descriptors.Descriptor internal_static_SCWebBetChanged_descriptor;
  
  private static final GeneratedMessageV3.FieldAccessorTable internal_static_SCWebBetChanged_fieldAccessorTable;
  
  private static Descriptors.FileDescriptor descriptor;
  
  public static void registerAllExtensions(ExtensionRegistryLite registry) {}
  
  public static void registerAllExtensions(ExtensionRegistry registry) {
    registerAllExtensions((ExtensionRegistryLite)registry);
  }
  
  public static final class SCWebBetChanged extends GeneratedMessageV3 implements SCWebBetChangedOrBuilder {
    private static final long serialVersionUID = 0L;
    
    public static final int MAXDELAYMILLIS_FIELD_NUMBER = 1;
    
    private long maxDelayMillis_;
    
    private byte memoizedIsInitialized;
    
    private SCWebBetChanged(GeneratedMessageV3.Builder<?> builder) {
      super(builder);
      this.maxDelayMillis_ = 0L;
      this.memoizedIsInitialized = -1;
    }
    
    private SCWebBetChanged() {
      this.maxDelayMillis_ = 0L;
      this.memoizedIsInitialized = -1;
    }
    
    protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
      return new SCWebBetChanged();
    }
    
    public static final Descriptors.Descriptor getDescriptor() {
      return SCWebBetChangedOuterClass.internal_static_SCWebBetChanged_descriptor;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return SCWebBetChangedOuterClass.internal_static_SCWebBetChanged_fieldAccessorTable.ensureFieldAccessorsInitialized(SCWebBetChanged.class, Builder.class);
    }
    
    public long getMaxDelayMillis() {
      return this.maxDelayMillis_;
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
      if (this.maxDelayMillis_ != 0L)
        output.writeUInt64(1, this.maxDelayMillis_); 
      getUnknownFields().writeTo(output);
    }
    
    public int getSerializedSize() {
      int size = this.memoizedSize;
      if (size != -1)
        return size; 
      size = 0;
      if (this.maxDelayMillis_ != 0L)
        size += 
          CodedOutputStream.computeUInt64Size(1, this.maxDelayMillis_); 
      size += getUnknownFields().getSerializedSize();
      this.memoizedSize = size;
      return size;
    }
    
    public boolean equals(Object obj) {
      if (obj == this)
        return true; 
      if (!(obj instanceof SCWebBetChanged))
        return super.equals(obj); 
      SCWebBetChanged other = (SCWebBetChanged)obj;
      if (getMaxDelayMillis() != other
        .getMaxDelayMillis())
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
          getMaxDelayMillis());
      hash = 29 * hash + getUnknownFields().hashCode();
      this.memoizedHashCode = hash;
      return hash;
    }
    
    public static SCWebBetChanged parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (SCWebBetChanged)PARSER.parseFrom(data);
    }
    
    public static SCWebBetChanged parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (SCWebBetChanged)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static SCWebBetChanged parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (SCWebBetChanged)PARSER.parseFrom(data);
    }
    
    public static SCWebBetChanged parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (SCWebBetChanged)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static SCWebBetChanged parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (SCWebBetChanged)PARSER.parseFrom(data);
    }
    
    public static SCWebBetChanged parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (SCWebBetChanged)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static SCWebBetChanged parseFrom(InputStream input) throws IOException {
      return 
        (SCWebBetChanged)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static SCWebBetChanged parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (SCWebBetChanged)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static SCWebBetChanged parseDelimitedFrom(InputStream input) throws IOException {
      return 
        (SCWebBetChanged)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }
    
    public static SCWebBetChanged parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (SCWebBetChanged)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static SCWebBetChanged parseFrom(CodedInputStream input) throws IOException {
      return 
        (SCWebBetChanged)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static SCWebBetChanged parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (SCWebBetChanged)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public Builder newBuilderForType() {
      return newBuilder();
    }
    
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    
    public static Builder newBuilder(SCWebBetChanged prototype) {
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
    
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements SCWebBetChangedOuterClass.SCWebBetChangedOrBuilder {
      private int bitField0_;
      
      private long maxDelayMillis_;
      
      public static final Descriptors.Descriptor getDescriptor() {
        return SCWebBetChangedOuterClass.internal_static_SCWebBetChanged_descriptor;
      }
      
      protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return SCWebBetChangedOuterClass.internal_static_SCWebBetChanged_fieldAccessorTable
          .ensureFieldAccessorsInitialized(SCWebBetChangedOuterClass.SCWebBetChanged.class, Builder.class);
      }
      
      private Builder() {}
      
      private Builder(GeneratedMessageV3.BuilderParent parent) {
        super(parent);
      }
      
      public Builder clear() {
        super.clear();
        this.bitField0_ = 0;
        this.maxDelayMillis_ = 0L;
        return this;
      }
      
      public Descriptors.Descriptor getDescriptorForType() {
        return SCWebBetChangedOuterClass.internal_static_SCWebBetChanged_descriptor;
      }
      
      public SCWebBetChangedOuterClass.SCWebBetChanged getDefaultInstanceForType() {
        return SCWebBetChangedOuterClass.SCWebBetChanged.getDefaultInstance();
      }
      
      public SCWebBetChangedOuterClass.SCWebBetChanged build() {
        SCWebBetChangedOuterClass.SCWebBetChanged result = buildPartial();
        if (!result.isInitialized())
          throw newUninitializedMessageException(result); 
        return result;
      }
      
      public SCWebBetChangedOuterClass.SCWebBetChanged buildPartial() {
        SCWebBetChangedOuterClass.SCWebBetChanged result = new SCWebBetChangedOuterClass.SCWebBetChanged(this);
        if (this.bitField0_ != 0)
          buildPartial0(result); 
        onBuilt();
        return result;
      }
      
      private void buildPartial0(SCWebBetChangedOuterClass.SCWebBetChanged result) {
        int from_bitField0_ = this.bitField0_;
        if ((from_bitField0_ & 0x1) != 0)
          result.maxDelayMillis_ = this.maxDelayMillis_; 
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
        if (other instanceof SCWebBetChangedOuterClass.SCWebBetChanged)
          return mergeFrom((SCWebBetChangedOuterClass.SCWebBetChanged)other); 
        super.mergeFrom(other);
        return this;
      }
      
      public Builder mergeFrom(SCWebBetChangedOuterClass.SCWebBetChanged other) {
        if (other == SCWebBetChangedOuterClass.SCWebBetChanged.getDefaultInstance())
          return this; 
        if (other.getMaxDelayMillis() != 0L)
          setMaxDelayMillis(other.getMaxDelayMillis()); 
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
                this.maxDelayMillis_ = input.readUInt64();
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
      
      public long getMaxDelayMillis() {
        return this.maxDelayMillis_;
      }
      
      public Builder setMaxDelayMillis(long value) {
        this.maxDelayMillis_ = value;
        this.bitField0_ |= 0x1;
        onChanged();
        return this;
      }
      
      public Builder clearMaxDelayMillis() {
        this.bitField0_ &= 0xFFFFFFFE;
        this.maxDelayMillis_ = 0L;
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
    
    private static final SCWebBetChanged DEFAULT_INSTANCE = new SCWebBetChanged();
    
    public static SCWebBetChanged getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }
    
    private static final Parser<SCWebBetChanged> PARSER = (Parser<SCWebBetChanged>)new AbstractParser<SCWebBetChanged>() {
        public SCWebBetChangedOuterClass.SCWebBetChanged parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
          SCWebBetChangedOuterClass.SCWebBetChanged.Builder builder = SCWebBetChangedOuterClass.SCWebBetChanged.newBuilder();
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
    
    public static Parser<SCWebBetChanged> parser() {
      return PARSER;
    }
    
    public Parser<SCWebBetChanged> getParserForType() {
      return PARSER;
    }
    
    public SCWebBetChanged getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }
  }
  
  public static Descriptors.FileDescriptor getDescriptor() {
    return descriptor;
  }
  
  static {
    String[] descriptorData = { "\n\025SCWebBetChanged.proto\")\n\017SCWebBetChanged\022\026\n\016maxDelayMillis\030\001 \001(\004B<\n:tech.ordinaryroad.live.chat.client.codec.kuaishou.protobufb\006proto3" };
    descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[0]);
    internal_static_SCWebBetChanged_descriptor = getDescriptor().getMessageTypes().get(0);
    internal_static_SCWebBetChanged_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_SCWebBetChanged_descriptor, new String[] { "MaxDelayMillis" });
  }
  
  public static interface SCWebBetChangedOrBuilder extends MessageOrBuilder {
    long getMaxDelayMillis();
  }
}
