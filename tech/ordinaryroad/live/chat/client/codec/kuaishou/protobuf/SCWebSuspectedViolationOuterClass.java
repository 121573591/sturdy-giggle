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

public final class SCWebSuspectedViolationOuterClass {
  private static final Descriptors.Descriptor internal_static_SCWebSuspectedViolation_descriptor;
  
  private static final GeneratedMessageV3.FieldAccessorTable internal_static_SCWebSuspectedViolation_fieldAccessorTable;
  
  private static Descriptors.FileDescriptor descriptor;
  
  public static void registerAllExtensions(ExtensionRegistryLite registry) {}
  
  public static void registerAllExtensions(ExtensionRegistry registry) {
    registerAllExtensions((ExtensionRegistryLite)registry);
  }
  
  public static final class SCWebSuspectedViolation extends GeneratedMessageV3 implements SCWebSuspectedViolationOrBuilder {
    private static final long serialVersionUID = 0L;
    
    public static final int SUSPECTEDVIOLATION_FIELD_NUMBER = 1;
    
    private boolean suspectedViolation_;
    
    private byte memoizedIsInitialized;
    
    private SCWebSuspectedViolation(GeneratedMessageV3.Builder<?> builder) {
      super(builder);
      this.suspectedViolation_ = false;
      this.memoizedIsInitialized = -1;
    }
    
    private SCWebSuspectedViolation() {
      this.suspectedViolation_ = false;
      this.memoizedIsInitialized = -1;
    }
    
    protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
      return new SCWebSuspectedViolation();
    }
    
    public static final Descriptors.Descriptor getDescriptor() {
      return SCWebSuspectedViolationOuterClass.internal_static_SCWebSuspectedViolation_descriptor;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return SCWebSuspectedViolationOuterClass.internal_static_SCWebSuspectedViolation_fieldAccessorTable.ensureFieldAccessorsInitialized(SCWebSuspectedViolation.class, Builder.class);
    }
    
    public boolean getSuspectedViolation() {
      return this.suspectedViolation_;
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
      if (this.suspectedViolation_)
        output.writeBool(1, this.suspectedViolation_); 
      getUnknownFields().writeTo(output);
    }
    
    public int getSerializedSize() {
      int size = this.memoizedSize;
      if (size != -1)
        return size; 
      size = 0;
      if (this.suspectedViolation_)
        size += 
          CodedOutputStream.computeBoolSize(1, this.suspectedViolation_); 
      size += getUnknownFields().getSerializedSize();
      this.memoizedSize = size;
      return size;
    }
    
    public boolean equals(Object obj) {
      if (obj == this)
        return true; 
      if (!(obj instanceof SCWebSuspectedViolation))
        return super.equals(obj); 
      SCWebSuspectedViolation other = (SCWebSuspectedViolation)obj;
      if (getSuspectedViolation() != other
        .getSuspectedViolation())
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
      hash = 53 * hash + Internal.hashBoolean(
          getSuspectedViolation());
      hash = 29 * hash + getUnknownFields().hashCode();
      this.memoizedHashCode = hash;
      return hash;
    }
    
    public static SCWebSuspectedViolation parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (SCWebSuspectedViolation)PARSER.parseFrom(data);
    }
    
    public static SCWebSuspectedViolation parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (SCWebSuspectedViolation)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static SCWebSuspectedViolation parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (SCWebSuspectedViolation)PARSER.parseFrom(data);
    }
    
    public static SCWebSuspectedViolation parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (SCWebSuspectedViolation)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static SCWebSuspectedViolation parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (SCWebSuspectedViolation)PARSER.parseFrom(data);
    }
    
    public static SCWebSuspectedViolation parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (SCWebSuspectedViolation)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static SCWebSuspectedViolation parseFrom(InputStream input) throws IOException {
      return 
        (SCWebSuspectedViolation)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static SCWebSuspectedViolation parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (SCWebSuspectedViolation)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static SCWebSuspectedViolation parseDelimitedFrom(InputStream input) throws IOException {
      return 
        (SCWebSuspectedViolation)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }
    
    public static SCWebSuspectedViolation parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (SCWebSuspectedViolation)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static SCWebSuspectedViolation parseFrom(CodedInputStream input) throws IOException {
      return 
        (SCWebSuspectedViolation)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static SCWebSuspectedViolation parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (SCWebSuspectedViolation)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public Builder newBuilderForType() {
      return newBuilder();
    }
    
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    
    public static Builder newBuilder(SCWebSuspectedViolation prototype) {
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
    
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements SCWebSuspectedViolationOuterClass.SCWebSuspectedViolationOrBuilder {
      private int bitField0_;
      
      private boolean suspectedViolation_;
      
      public static final Descriptors.Descriptor getDescriptor() {
        return SCWebSuspectedViolationOuterClass.internal_static_SCWebSuspectedViolation_descriptor;
      }
      
      protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return SCWebSuspectedViolationOuterClass.internal_static_SCWebSuspectedViolation_fieldAccessorTable
          .ensureFieldAccessorsInitialized(SCWebSuspectedViolationOuterClass.SCWebSuspectedViolation.class, Builder.class);
      }
      
      private Builder() {}
      
      private Builder(GeneratedMessageV3.BuilderParent parent) {
        super(parent);
      }
      
      public Builder clear() {
        super.clear();
        this.bitField0_ = 0;
        this.suspectedViolation_ = false;
        return this;
      }
      
      public Descriptors.Descriptor getDescriptorForType() {
        return SCWebSuspectedViolationOuterClass.internal_static_SCWebSuspectedViolation_descriptor;
      }
      
      public SCWebSuspectedViolationOuterClass.SCWebSuspectedViolation getDefaultInstanceForType() {
        return SCWebSuspectedViolationOuterClass.SCWebSuspectedViolation.getDefaultInstance();
      }
      
      public SCWebSuspectedViolationOuterClass.SCWebSuspectedViolation build() {
        SCWebSuspectedViolationOuterClass.SCWebSuspectedViolation result = buildPartial();
        if (!result.isInitialized())
          throw newUninitializedMessageException(result); 
        return result;
      }
      
      public SCWebSuspectedViolationOuterClass.SCWebSuspectedViolation buildPartial() {
        SCWebSuspectedViolationOuterClass.SCWebSuspectedViolation result = new SCWebSuspectedViolationOuterClass.SCWebSuspectedViolation(this);
        if (this.bitField0_ != 0)
          buildPartial0(result); 
        onBuilt();
        return result;
      }
      
      private void buildPartial0(SCWebSuspectedViolationOuterClass.SCWebSuspectedViolation result) {
        int from_bitField0_ = this.bitField0_;
        if ((from_bitField0_ & 0x1) != 0)
          result.suspectedViolation_ = this.suspectedViolation_; 
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
        if (other instanceof SCWebSuspectedViolationOuterClass.SCWebSuspectedViolation)
          return mergeFrom((SCWebSuspectedViolationOuterClass.SCWebSuspectedViolation)other); 
        super.mergeFrom(other);
        return this;
      }
      
      public Builder mergeFrom(SCWebSuspectedViolationOuterClass.SCWebSuspectedViolation other) {
        if (other == SCWebSuspectedViolationOuterClass.SCWebSuspectedViolation.getDefaultInstance())
          return this; 
        if (other.getSuspectedViolation())
          setSuspectedViolation(other.getSuspectedViolation()); 
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
                this.suspectedViolation_ = input.readBool();
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
      
      public boolean getSuspectedViolation() {
        return this.suspectedViolation_;
      }
      
      public Builder setSuspectedViolation(boolean value) {
        this.suspectedViolation_ = value;
        this.bitField0_ |= 0x1;
        onChanged();
        return this;
      }
      
      public Builder clearSuspectedViolation() {
        this.bitField0_ &= 0xFFFFFFFE;
        this.suspectedViolation_ = false;
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
    
    private static final SCWebSuspectedViolation DEFAULT_INSTANCE = new SCWebSuspectedViolation();
    
    public static SCWebSuspectedViolation getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }
    
    private static final Parser<SCWebSuspectedViolation> PARSER = (Parser<SCWebSuspectedViolation>)new AbstractParser<SCWebSuspectedViolation>() {
        public SCWebSuspectedViolationOuterClass.SCWebSuspectedViolation parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
          SCWebSuspectedViolationOuterClass.SCWebSuspectedViolation.Builder builder = SCWebSuspectedViolationOuterClass.SCWebSuspectedViolation.newBuilder();
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
    
    public static Parser<SCWebSuspectedViolation> parser() {
      return PARSER;
    }
    
    public Parser<SCWebSuspectedViolation> getParserForType() {
      return PARSER;
    }
    
    public SCWebSuspectedViolation getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }
  }
  
  public static Descriptors.FileDescriptor getDescriptor() {
    return descriptor;
  }
  
  static {
    String[] descriptorData = { "\n\035SCWebSuspectedViolation.proto\"5\n\027SCWebSuspectedViolation\022\032\n\022suspectedViolation\030\001 \001(\bB<\n:tech.ordinaryroad.live.chat.client.codec.kuaishou.protobufb\006proto3" };
    descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[0]);
    internal_static_SCWebSuspectedViolation_descriptor = getDescriptor().getMessageTypes().get(0);
    internal_static_SCWebSuspectedViolation_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_SCWebSuspectedViolation_descriptor, new String[] { "SuspectedViolation" });
  }
  
  public static interface SCWebSuspectedViolationOrBuilder extends MessageOrBuilder {
    boolean getSuspectedViolation();
  }
}
