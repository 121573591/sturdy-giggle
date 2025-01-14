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

public final class SCWebAuthorPauseOuterClass {
  private static final Descriptors.Descriptor internal_static_SCWebAuthorPause_descriptor;
  
  private static final GeneratedMessageV3.FieldAccessorTable internal_static_SCWebAuthorPause_fieldAccessorTable;
  
  private static Descriptors.FileDescriptor descriptor;
  
  public static void registerAllExtensions(ExtensionRegistryLite registry) {}
  
  public static void registerAllExtensions(ExtensionRegistry registry) {
    registerAllExtensions((ExtensionRegistryLite)registry);
  }
  
  public static final class SCWebAuthorPause extends GeneratedMessageV3 implements SCWebAuthorPauseOrBuilder {
    private static final long serialVersionUID = 0L;
    
    public static final int TIME_FIELD_NUMBER = 1;
    
    private long time_;
    
    public static final int PAUSETYPE_FIELD_NUMBER = 2;
    
    private int pauseType_;
    
    private byte memoizedIsInitialized;
    
    private SCWebAuthorPause(GeneratedMessageV3.Builder<?> builder) {
      super(builder);
      this.time_ = 0L;
      this.pauseType_ = 0;
      this.memoizedIsInitialized = -1;
    }
    
    private SCWebAuthorPause() {
      this.time_ = 0L;
      this.pauseType_ = 0;
      this.memoizedIsInitialized = -1;
      this.pauseType_ = 0;
    }
    
    protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
      return new SCWebAuthorPause();
    }
    
    public static final Descriptors.Descriptor getDescriptor() {
      return SCWebAuthorPauseOuterClass.internal_static_SCWebAuthorPause_descriptor;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return SCWebAuthorPauseOuterClass.internal_static_SCWebAuthorPause_fieldAccessorTable.ensureFieldAccessorsInitialized(SCWebAuthorPause.class, Builder.class);
    }
    
    public long getTime() {
      return this.time_;
    }
    
    public int getPauseTypeValue() {
      return this.pauseType_;
    }
    
    public WebPauseTypeOuterClass.WebPauseType getPauseType() {
      WebPauseTypeOuterClass.WebPauseType result = WebPauseTypeOuterClass.WebPauseType.forNumber(this.pauseType_);
      return (result == null) ? WebPauseTypeOuterClass.WebPauseType.UNRECOGNIZED : result;
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
      if (this.pauseType_ != WebPauseTypeOuterClass.WebPauseType.UNKNOWN_PAUSE_TYPE.getNumber())
        output.writeEnum(2, this.pauseType_); 
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
      if (this.pauseType_ != WebPauseTypeOuterClass.WebPauseType.UNKNOWN_PAUSE_TYPE.getNumber())
        size += 
          CodedOutputStream.computeEnumSize(2, this.pauseType_); 
      size += getUnknownFields().getSerializedSize();
      this.memoizedSize = size;
      return size;
    }
    
    public boolean equals(Object obj) {
      if (obj == this)
        return true; 
      if (!(obj instanceof SCWebAuthorPause))
        return super.equals(obj); 
      SCWebAuthorPause other = (SCWebAuthorPause)obj;
      if (getTime() != other
        .getTime())
        return false; 
      if (this.pauseType_ != other.pauseType_)
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
      hash = 37 * hash + 2;
      hash = 53 * hash + this.pauseType_;
      hash = 29 * hash + getUnknownFields().hashCode();
      this.memoizedHashCode = hash;
      return hash;
    }
    
    public static SCWebAuthorPause parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (SCWebAuthorPause)PARSER.parseFrom(data);
    }
    
    public static SCWebAuthorPause parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (SCWebAuthorPause)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static SCWebAuthorPause parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (SCWebAuthorPause)PARSER.parseFrom(data);
    }
    
    public static SCWebAuthorPause parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (SCWebAuthorPause)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static SCWebAuthorPause parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (SCWebAuthorPause)PARSER.parseFrom(data);
    }
    
    public static SCWebAuthorPause parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (SCWebAuthorPause)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static SCWebAuthorPause parseFrom(InputStream input) throws IOException {
      return 
        (SCWebAuthorPause)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static SCWebAuthorPause parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (SCWebAuthorPause)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static SCWebAuthorPause parseDelimitedFrom(InputStream input) throws IOException {
      return 
        (SCWebAuthorPause)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }
    
    public static SCWebAuthorPause parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (SCWebAuthorPause)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static SCWebAuthorPause parseFrom(CodedInputStream input) throws IOException {
      return 
        (SCWebAuthorPause)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static SCWebAuthorPause parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (SCWebAuthorPause)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public Builder newBuilderForType() {
      return newBuilder();
    }
    
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    
    public static Builder newBuilder(SCWebAuthorPause prototype) {
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
    
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements SCWebAuthorPauseOuterClass.SCWebAuthorPauseOrBuilder {
      private int bitField0_;
      
      private long time_;
      
      private int pauseType_;
      
      public static final Descriptors.Descriptor getDescriptor() {
        return SCWebAuthorPauseOuterClass.internal_static_SCWebAuthorPause_descriptor;
      }
      
      protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return SCWebAuthorPauseOuterClass.internal_static_SCWebAuthorPause_fieldAccessorTable
          .ensureFieldAccessorsInitialized(SCWebAuthorPauseOuterClass.SCWebAuthorPause.class, Builder.class);
      }
      
      private Builder() {
        this.pauseType_ = 0;
      }
      
      private Builder(GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        this.pauseType_ = 0;
      }
      
      public Builder clear() {
        super.clear();
        this.bitField0_ = 0;
        this.time_ = 0L;
        this.pauseType_ = 0;
        return this;
      }
      
      public Descriptors.Descriptor getDescriptorForType() {
        return SCWebAuthorPauseOuterClass.internal_static_SCWebAuthorPause_descriptor;
      }
      
      public SCWebAuthorPauseOuterClass.SCWebAuthorPause getDefaultInstanceForType() {
        return SCWebAuthorPauseOuterClass.SCWebAuthorPause.getDefaultInstance();
      }
      
      public SCWebAuthorPauseOuterClass.SCWebAuthorPause build() {
        SCWebAuthorPauseOuterClass.SCWebAuthorPause result = buildPartial();
        if (!result.isInitialized())
          throw newUninitializedMessageException(result); 
        return result;
      }
      
      public SCWebAuthorPauseOuterClass.SCWebAuthorPause buildPartial() {
        SCWebAuthorPauseOuterClass.SCWebAuthorPause result = new SCWebAuthorPauseOuterClass.SCWebAuthorPause(this);
        if (this.bitField0_ != 0)
          buildPartial0(result); 
        onBuilt();
        return result;
      }
      
      private void buildPartial0(SCWebAuthorPauseOuterClass.SCWebAuthorPause result) {
        int from_bitField0_ = this.bitField0_;
        if ((from_bitField0_ & 0x1) != 0)
          result.time_ = this.time_; 
        if ((from_bitField0_ & 0x2) != 0)
          result.pauseType_ = this.pauseType_; 
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
        if (other instanceof SCWebAuthorPauseOuterClass.SCWebAuthorPause)
          return mergeFrom((SCWebAuthorPauseOuterClass.SCWebAuthorPause)other); 
        super.mergeFrom(other);
        return this;
      }
      
      public Builder mergeFrom(SCWebAuthorPauseOuterClass.SCWebAuthorPause other) {
        if (other == SCWebAuthorPauseOuterClass.SCWebAuthorPause.getDefaultInstance())
          return this; 
        if (other.getTime() != 0L)
          setTime(other.getTime()); 
        if (other.pauseType_ != 0)
          setPauseTypeValue(other.getPauseTypeValue()); 
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
              case 16:
                this.pauseType_ = input.readEnum();
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
      
      public int getPauseTypeValue() {
        return this.pauseType_;
      }
      
      public Builder setPauseTypeValue(int value) {
        this.pauseType_ = value;
        this.bitField0_ |= 0x2;
        onChanged();
        return this;
      }
      
      public WebPauseTypeOuterClass.WebPauseType getPauseType() {
        WebPauseTypeOuterClass.WebPauseType result = WebPauseTypeOuterClass.WebPauseType.forNumber(this.pauseType_);
        return (result == null) ? WebPauseTypeOuterClass.WebPauseType.UNRECOGNIZED : result;
      }
      
      public Builder setPauseType(WebPauseTypeOuterClass.WebPauseType value) {
        if (value == null)
          throw new NullPointerException(); 
        this.bitField0_ |= 0x2;
        this.pauseType_ = value.getNumber();
        onChanged();
        return this;
      }
      
      public Builder clearPauseType() {
        this.bitField0_ &= 0xFFFFFFFD;
        this.pauseType_ = 0;
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
    
    private static final SCWebAuthorPause DEFAULT_INSTANCE = new SCWebAuthorPause();
    
    public static SCWebAuthorPause getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }
    
    private static final Parser<SCWebAuthorPause> PARSER = (Parser<SCWebAuthorPause>)new AbstractParser<SCWebAuthorPause>() {
        public SCWebAuthorPauseOuterClass.SCWebAuthorPause parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
          SCWebAuthorPauseOuterClass.SCWebAuthorPause.Builder builder = SCWebAuthorPauseOuterClass.SCWebAuthorPause.newBuilder();
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
    
    public static Parser<SCWebAuthorPause> parser() {
      return PARSER;
    }
    
    public Parser<SCWebAuthorPause> getParserForType() {
      return PARSER;
    }
    
    public SCWebAuthorPause getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }
  }
  
  public static Descriptors.FileDescriptor getDescriptor() {
    return descriptor;
  }
  
  static {
    String[] descriptorData = { "\n\026SCWebAuthorPause.proto\032\022WebPauseType.proto\"B\n\020SCWebAuthorPause\022\f\n\004time\030\001 \001(\004\022 \n\tpauseType\030\002 \001(\0162\r.WebPauseTypeB<\n:tech.ordinaryroad.live.chat.client.codec.kuaishou.protobufb\006proto3" };
    descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[] { WebPauseTypeOuterClass.getDescriptor() });
    internal_static_SCWebAuthorPause_descriptor = getDescriptor().getMessageTypes().get(0);
    internal_static_SCWebAuthorPause_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_SCWebAuthorPause_descriptor, new String[] { "Time", "PauseType" });
    WebPauseTypeOuterClass.getDescriptor();
  }
  
  public static interface SCWebAuthorPauseOrBuilder extends MessageOrBuilder {
    long getTime();
    
    int getPauseTypeValue();
    
    WebPauseTypeOuterClass.WebPauseType getPauseType();
  }
}
