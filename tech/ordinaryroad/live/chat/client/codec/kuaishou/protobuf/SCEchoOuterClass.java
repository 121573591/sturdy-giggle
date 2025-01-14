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

public final class SCEchoOuterClass {
  private static final Descriptors.Descriptor internal_static_SCEcho_descriptor;
  
  private static final GeneratedMessageV3.FieldAccessorTable internal_static_SCEcho_fieldAccessorTable;
  
  private static Descriptors.FileDescriptor descriptor;
  
  public static void registerAllExtensions(ExtensionRegistryLite registry) {}
  
  public static void registerAllExtensions(ExtensionRegistry registry) {
    registerAllExtensions((ExtensionRegistryLite)registry);
  }
  
  public static final class SCEcho extends GeneratedMessageV3 implements SCEchoOrBuilder {
    private static final long serialVersionUID = 0L;
    
    public static final int CONTENT_FIELD_NUMBER = 1;
    
    private volatile Object content_;
    
    private byte memoizedIsInitialized;
    
    private SCEcho(GeneratedMessageV3.Builder<?> builder) {
      super(builder);
      this.content_ = "";
      this.memoizedIsInitialized = -1;
    }
    
    private SCEcho() {
      this.content_ = "";
      this.memoizedIsInitialized = -1;
      this.content_ = "";
    }
    
    protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
      return new SCEcho();
    }
    
    public static final Descriptors.Descriptor getDescriptor() {
      return SCEchoOuterClass.internal_static_SCEcho_descriptor;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return SCEchoOuterClass.internal_static_SCEcho_fieldAccessorTable.ensureFieldAccessorsInitialized(SCEcho.class, Builder.class);
    }
    
    public String getContent() {
      Object ref = this.content_;
      if (ref instanceof String)
        return (String)ref; 
      ByteString bs = (ByteString)ref;
      String s = bs.toStringUtf8();
      this.content_ = s;
      return s;
    }
    
    public ByteString getContentBytes() {
      Object ref = this.content_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.content_ = b;
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
      if (!GeneratedMessageV3.isStringEmpty(this.content_))
        GeneratedMessageV3.writeString(output, 1, this.content_); 
      getUnknownFields().writeTo(output);
    }
    
    public int getSerializedSize() {
      int size = this.memoizedSize;
      if (size != -1)
        return size; 
      size = 0;
      if (!GeneratedMessageV3.isStringEmpty(this.content_))
        size += GeneratedMessageV3.computeStringSize(1, this.content_); 
      size += getUnknownFields().getSerializedSize();
      this.memoizedSize = size;
      return size;
    }
    
    public boolean equals(Object obj) {
      if (obj == this)
        return true; 
      if (!(obj instanceof SCEcho))
        return super.equals(obj); 
      SCEcho other = (SCEcho)obj;
      if (!getContent().equals(other.getContent()))
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
      hash = 53 * hash + getContent().hashCode();
      hash = 29 * hash + getUnknownFields().hashCode();
      this.memoizedHashCode = hash;
      return hash;
    }
    
    public static SCEcho parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (SCEcho)PARSER.parseFrom(data);
    }
    
    public static SCEcho parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (SCEcho)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static SCEcho parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (SCEcho)PARSER.parseFrom(data);
    }
    
    public static SCEcho parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (SCEcho)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static SCEcho parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (SCEcho)PARSER.parseFrom(data);
    }
    
    public static SCEcho parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (SCEcho)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static SCEcho parseFrom(InputStream input) throws IOException {
      return 
        (SCEcho)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static SCEcho parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (SCEcho)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static SCEcho parseDelimitedFrom(InputStream input) throws IOException {
      return 
        (SCEcho)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }
    
    public static SCEcho parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (SCEcho)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static SCEcho parseFrom(CodedInputStream input) throws IOException {
      return 
        (SCEcho)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static SCEcho parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (SCEcho)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public Builder newBuilderForType() {
      return newBuilder();
    }
    
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    
    public static Builder newBuilder(SCEcho prototype) {
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
    
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements SCEchoOuterClass.SCEchoOrBuilder {
      private int bitField0_;
      
      private Object content_;
      
      public static final Descriptors.Descriptor getDescriptor() {
        return SCEchoOuterClass.internal_static_SCEcho_descriptor;
      }
      
      protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return SCEchoOuterClass.internal_static_SCEcho_fieldAccessorTable
          .ensureFieldAccessorsInitialized(SCEchoOuterClass.SCEcho.class, Builder.class);
      }
      
      private Builder() {
        this.content_ = "";
      }
      
      private Builder(GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        this.content_ = "";
      }
      
      public Builder clear() {
        super.clear();
        this.bitField0_ = 0;
        this.content_ = "";
        return this;
      }
      
      public Descriptors.Descriptor getDescriptorForType() {
        return SCEchoOuterClass.internal_static_SCEcho_descriptor;
      }
      
      public SCEchoOuterClass.SCEcho getDefaultInstanceForType() {
        return SCEchoOuterClass.SCEcho.getDefaultInstance();
      }
      
      public SCEchoOuterClass.SCEcho build() {
        SCEchoOuterClass.SCEcho result = buildPartial();
        if (!result.isInitialized())
          throw newUninitializedMessageException(result); 
        return result;
      }
      
      public SCEchoOuterClass.SCEcho buildPartial() {
        SCEchoOuterClass.SCEcho result = new SCEchoOuterClass.SCEcho(this);
        if (this.bitField0_ != 0)
          buildPartial0(result); 
        onBuilt();
        return result;
      }
      
      private void buildPartial0(SCEchoOuterClass.SCEcho result) {
        int from_bitField0_ = this.bitField0_;
        if ((from_bitField0_ & 0x1) != 0)
          result.content_ = this.content_; 
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
        if (other instanceof SCEchoOuterClass.SCEcho)
          return mergeFrom((SCEchoOuterClass.SCEcho)other); 
        super.mergeFrom(other);
        return this;
      }
      
      public Builder mergeFrom(SCEchoOuterClass.SCEcho other) {
        if (other == SCEchoOuterClass.SCEcho.getDefaultInstance())
          return this; 
        if (!other.getContent().isEmpty()) {
          this.content_ = other.content_;
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
                this.content_ = input.readStringRequireUtf8();
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
      
      public String getContent() {
        Object ref = this.content_;
        if (!(ref instanceof String)) {
          ByteString bs = (ByteString)ref;
          String s = bs.toStringUtf8();
          this.content_ = s;
          return s;
        } 
        return (String)ref;
      }
      
      public ByteString getContentBytes() {
        Object ref = this.content_;
        if (ref instanceof String) {
          ByteString b = ByteString.copyFromUtf8((String)ref);
          this.content_ = b;
          return b;
        } 
        return (ByteString)ref;
      }
      
      public Builder setContent(String value) {
        if (value == null)
          throw new NullPointerException(); 
        this.content_ = value;
        this.bitField0_ |= 0x1;
        onChanged();
        return this;
      }
      
      public Builder clearContent() {
        this.content_ = SCEchoOuterClass.SCEcho.getDefaultInstance().getContent();
        this.bitField0_ &= 0xFFFFFFFE;
        onChanged();
        return this;
      }
      
      public Builder setContentBytes(ByteString value) {
        if (value == null)
          throw new NullPointerException(); 
        SCEchoOuterClass.SCEcho.checkByteStringIsUtf8(value);
        this.content_ = value;
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
    
    private static final SCEcho DEFAULT_INSTANCE = new SCEcho();
    
    public static SCEcho getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }
    
    private static final Parser<SCEcho> PARSER = (Parser<SCEcho>)new AbstractParser<SCEcho>() {
        public SCEchoOuterClass.SCEcho parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
          SCEchoOuterClass.SCEcho.Builder builder = SCEchoOuterClass.SCEcho.newBuilder();
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
    
    public static Parser<SCEcho> parser() {
      return PARSER;
    }
    
    public Parser<SCEcho> getParserForType() {
      return PARSER;
    }
    
    public SCEcho getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }
  }
  
  public static Descriptors.FileDescriptor getDescriptor() {
    return descriptor;
  }
  
  static {
    String[] descriptorData = { "\n\fSCEcho.proto\"\031\n\006SCEcho\022\017\n\007content\030\001 \001(\tB<\n:tech.ordinaryroad.live.chat.client.codec.kuaishou.protobufb\006proto3" };
    descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[0]);
    internal_static_SCEcho_descriptor = getDescriptor().getMessageTypes().get(0);
    internal_static_SCEcho_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_SCEcho_descriptor, new String[] { "Content" });
  }
  
  public static interface SCEchoOrBuilder extends MessageOrBuilder {
    String getContent();
    
    ByteString getContentBytes();
  }
}
