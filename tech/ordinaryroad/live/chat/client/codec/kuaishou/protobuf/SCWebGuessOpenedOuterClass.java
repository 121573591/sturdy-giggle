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

public final class SCWebGuessOpenedOuterClass {
  private static final Descriptors.Descriptor internal_static_SCWebGuessOpened_descriptor;
  
  private static final GeneratedMessageV3.FieldAccessorTable internal_static_SCWebGuessOpened_fieldAccessorTable;
  
  private static Descriptors.FileDescriptor descriptor;
  
  public static void registerAllExtensions(ExtensionRegistryLite registry) {}
  
  public static void registerAllExtensions(ExtensionRegistry registry) {
    registerAllExtensions((ExtensionRegistryLite)registry);
  }
  
  public static final class SCWebGuessOpened extends GeneratedMessageV3 implements SCWebGuessOpenedOrBuilder {
    private static final long serialVersionUID = 0L;
    
    public static final int TIME_FIELD_NUMBER = 1;
    
    private long time_;
    
    public static final int GUESSID_FIELD_NUMBER = 2;
    
    private volatile Object guessId_;
    
    public static final int SUBMITDEADLINE_FIELD_NUMBER = 3;
    
    private long submitDeadline_;
    
    public static final int DISPLAYMAXDELAYMILLIS_FIELD_NUMBER = 4;
    
    private long displayMaxDelayMillis_;
    
    private byte memoizedIsInitialized;
    
    private SCWebGuessOpened(GeneratedMessageV3.Builder<?> builder) {
      super(builder);
      this.time_ = 0L;
      this.guessId_ = "";
      this.submitDeadline_ = 0L;
      this.displayMaxDelayMillis_ = 0L;
      this.memoizedIsInitialized = -1;
    }
    
    private SCWebGuessOpened() {
      this.time_ = 0L;
      this.guessId_ = "";
      this.submitDeadline_ = 0L;
      this.displayMaxDelayMillis_ = 0L;
      this.memoizedIsInitialized = -1;
      this.guessId_ = "";
    }
    
    protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
      return new SCWebGuessOpened();
    }
    
    public static final Descriptors.Descriptor getDescriptor() {
      return SCWebGuessOpenedOuterClass.internal_static_SCWebGuessOpened_descriptor;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return SCWebGuessOpenedOuterClass.internal_static_SCWebGuessOpened_fieldAccessorTable.ensureFieldAccessorsInitialized(SCWebGuessOpened.class, Builder.class);
    }
    
    public long getTime() {
      return this.time_;
    }
    
    public String getGuessId() {
      Object ref = this.guessId_;
      if (ref instanceof String)
        return (String)ref; 
      ByteString bs = (ByteString)ref;
      String s = bs.toStringUtf8();
      this.guessId_ = s;
      return s;
    }
    
    public ByteString getGuessIdBytes() {
      Object ref = this.guessId_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.guessId_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public long getSubmitDeadline() {
      return this.submitDeadline_;
    }
    
    public long getDisplayMaxDelayMillis() {
      return this.displayMaxDelayMillis_;
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
      if (!GeneratedMessageV3.isStringEmpty(this.guessId_))
        GeneratedMessageV3.writeString(output, 2, this.guessId_); 
      if (this.submitDeadline_ != 0L)
        output.writeUInt64(3, this.submitDeadline_); 
      if (this.displayMaxDelayMillis_ != 0L)
        output.writeUInt64(4, this.displayMaxDelayMillis_); 
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
      if (!GeneratedMessageV3.isStringEmpty(this.guessId_))
        size += GeneratedMessageV3.computeStringSize(2, this.guessId_); 
      if (this.submitDeadline_ != 0L)
        size += 
          CodedOutputStream.computeUInt64Size(3, this.submitDeadline_); 
      if (this.displayMaxDelayMillis_ != 0L)
        size += 
          CodedOutputStream.computeUInt64Size(4, this.displayMaxDelayMillis_); 
      size += getUnknownFields().getSerializedSize();
      this.memoizedSize = size;
      return size;
    }
    
    public boolean equals(Object obj) {
      if (obj == this)
        return true; 
      if (!(obj instanceof SCWebGuessOpened))
        return super.equals(obj); 
      SCWebGuessOpened other = (SCWebGuessOpened)obj;
      if (getTime() != other
        .getTime())
        return false; 
      if (!getGuessId().equals(other.getGuessId()))
        return false; 
      if (getSubmitDeadline() != other
        .getSubmitDeadline())
        return false; 
      if (getDisplayMaxDelayMillis() != other
        .getDisplayMaxDelayMillis())
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
      hash = 53 * hash + getGuessId().hashCode();
      hash = 37 * hash + 3;
      hash = 53 * hash + Internal.hashLong(
          getSubmitDeadline());
      hash = 37 * hash + 4;
      hash = 53 * hash + Internal.hashLong(
          getDisplayMaxDelayMillis());
      hash = 29 * hash + getUnknownFields().hashCode();
      this.memoizedHashCode = hash;
      return hash;
    }
    
    public static SCWebGuessOpened parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (SCWebGuessOpened)PARSER.parseFrom(data);
    }
    
    public static SCWebGuessOpened parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (SCWebGuessOpened)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static SCWebGuessOpened parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (SCWebGuessOpened)PARSER.parseFrom(data);
    }
    
    public static SCWebGuessOpened parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (SCWebGuessOpened)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static SCWebGuessOpened parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (SCWebGuessOpened)PARSER.parseFrom(data);
    }
    
    public static SCWebGuessOpened parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (SCWebGuessOpened)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static SCWebGuessOpened parseFrom(InputStream input) throws IOException {
      return 
        (SCWebGuessOpened)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static SCWebGuessOpened parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (SCWebGuessOpened)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static SCWebGuessOpened parseDelimitedFrom(InputStream input) throws IOException {
      return 
        (SCWebGuessOpened)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }
    
    public static SCWebGuessOpened parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (SCWebGuessOpened)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static SCWebGuessOpened parseFrom(CodedInputStream input) throws IOException {
      return 
        (SCWebGuessOpened)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static SCWebGuessOpened parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (SCWebGuessOpened)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public Builder newBuilderForType() {
      return newBuilder();
    }
    
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    
    public static Builder newBuilder(SCWebGuessOpened prototype) {
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
    
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements SCWebGuessOpenedOuterClass.SCWebGuessOpenedOrBuilder {
      private int bitField0_;
      
      private long time_;
      
      private Object guessId_;
      
      private long submitDeadline_;
      
      private long displayMaxDelayMillis_;
      
      public static final Descriptors.Descriptor getDescriptor() {
        return SCWebGuessOpenedOuterClass.internal_static_SCWebGuessOpened_descriptor;
      }
      
      protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return SCWebGuessOpenedOuterClass.internal_static_SCWebGuessOpened_fieldAccessorTable
          .ensureFieldAccessorsInitialized(SCWebGuessOpenedOuterClass.SCWebGuessOpened.class, Builder.class);
      }
      
      private Builder() {
        this.guessId_ = "";
      }
      
      private Builder(GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        this.guessId_ = "";
      }
      
      public Builder clear() {
        super.clear();
        this.bitField0_ = 0;
        this.time_ = 0L;
        this.guessId_ = "";
        this.submitDeadline_ = 0L;
        this.displayMaxDelayMillis_ = 0L;
        return this;
      }
      
      public Descriptors.Descriptor getDescriptorForType() {
        return SCWebGuessOpenedOuterClass.internal_static_SCWebGuessOpened_descriptor;
      }
      
      public SCWebGuessOpenedOuterClass.SCWebGuessOpened getDefaultInstanceForType() {
        return SCWebGuessOpenedOuterClass.SCWebGuessOpened.getDefaultInstance();
      }
      
      public SCWebGuessOpenedOuterClass.SCWebGuessOpened build() {
        SCWebGuessOpenedOuterClass.SCWebGuessOpened result = buildPartial();
        if (!result.isInitialized())
          throw newUninitializedMessageException(result); 
        return result;
      }
      
      public SCWebGuessOpenedOuterClass.SCWebGuessOpened buildPartial() {
        SCWebGuessOpenedOuterClass.SCWebGuessOpened result = new SCWebGuessOpenedOuterClass.SCWebGuessOpened(this);
        if (this.bitField0_ != 0)
          buildPartial0(result); 
        onBuilt();
        return result;
      }
      
      private void buildPartial0(SCWebGuessOpenedOuterClass.SCWebGuessOpened result) {
        int from_bitField0_ = this.bitField0_;
        if ((from_bitField0_ & 0x1) != 0)
          result.time_ = this.time_; 
        if ((from_bitField0_ & 0x2) != 0)
          result.guessId_ = this.guessId_; 
        if ((from_bitField0_ & 0x4) != 0)
          result.submitDeadline_ = this.submitDeadline_; 
        if ((from_bitField0_ & 0x8) != 0)
          result.displayMaxDelayMillis_ = this.displayMaxDelayMillis_; 
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
        if (other instanceof SCWebGuessOpenedOuterClass.SCWebGuessOpened)
          return mergeFrom((SCWebGuessOpenedOuterClass.SCWebGuessOpened)other); 
        super.mergeFrom(other);
        return this;
      }
      
      public Builder mergeFrom(SCWebGuessOpenedOuterClass.SCWebGuessOpened other) {
        if (other == SCWebGuessOpenedOuterClass.SCWebGuessOpened.getDefaultInstance())
          return this; 
        if (other.getTime() != 0L)
          setTime(other.getTime()); 
        if (!other.getGuessId().isEmpty()) {
          this.guessId_ = other.guessId_;
          this.bitField0_ |= 0x2;
          onChanged();
        } 
        if (other.getSubmitDeadline() != 0L)
          setSubmitDeadline(other.getSubmitDeadline()); 
        if (other.getDisplayMaxDelayMillis() != 0L)
          setDisplayMaxDelayMillis(other.getDisplayMaxDelayMillis()); 
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
              case 18:
                this.guessId_ = input.readStringRequireUtf8();
                this.bitField0_ |= 0x2;
                continue;
              case 24:
                this.submitDeadline_ = input.readUInt64();
                this.bitField0_ |= 0x4;
                continue;
              case 32:
                this.displayMaxDelayMillis_ = input.readUInt64();
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
      
      public String getGuessId() {
        Object ref = this.guessId_;
        if (!(ref instanceof String)) {
          ByteString bs = (ByteString)ref;
          String s = bs.toStringUtf8();
          this.guessId_ = s;
          return s;
        } 
        return (String)ref;
      }
      
      public ByteString getGuessIdBytes() {
        Object ref = this.guessId_;
        if (ref instanceof String) {
          ByteString b = ByteString.copyFromUtf8((String)ref);
          this.guessId_ = b;
          return b;
        } 
        return (ByteString)ref;
      }
      
      public Builder setGuessId(String value) {
        if (value == null)
          throw new NullPointerException(); 
        this.guessId_ = value;
        this.bitField0_ |= 0x2;
        onChanged();
        return this;
      }
      
      public Builder clearGuessId() {
        this.guessId_ = SCWebGuessOpenedOuterClass.SCWebGuessOpened.getDefaultInstance().getGuessId();
        this.bitField0_ &= 0xFFFFFFFD;
        onChanged();
        return this;
      }
      
      public Builder setGuessIdBytes(ByteString value) {
        if (value == null)
          throw new NullPointerException(); 
        SCWebGuessOpenedOuterClass.SCWebGuessOpened.checkByteStringIsUtf8(value);
        this.guessId_ = value;
        this.bitField0_ |= 0x2;
        onChanged();
        return this;
      }
      
      public long getSubmitDeadline() {
        return this.submitDeadline_;
      }
      
      public Builder setSubmitDeadline(long value) {
        this.submitDeadline_ = value;
        this.bitField0_ |= 0x4;
        onChanged();
        return this;
      }
      
      public Builder clearSubmitDeadline() {
        this.bitField0_ &= 0xFFFFFFFB;
        this.submitDeadline_ = 0L;
        onChanged();
        return this;
      }
      
      public long getDisplayMaxDelayMillis() {
        return this.displayMaxDelayMillis_;
      }
      
      public Builder setDisplayMaxDelayMillis(long value) {
        this.displayMaxDelayMillis_ = value;
        this.bitField0_ |= 0x8;
        onChanged();
        return this;
      }
      
      public Builder clearDisplayMaxDelayMillis() {
        this.bitField0_ &= 0xFFFFFFF7;
        this.displayMaxDelayMillis_ = 0L;
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
    
    private static final SCWebGuessOpened DEFAULT_INSTANCE = new SCWebGuessOpened();
    
    public static SCWebGuessOpened getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }
    
    private static final Parser<SCWebGuessOpened> PARSER = (Parser<SCWebGuessOpened>)new AbstractParser<SCWebGuessOpened>() {
        public SCWebGuessOpenedOuterClass.SCWebGuessOpened parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
          SCWebGuessOpenedOuterClass.SCWebGuessOpened.Builder builder = SCWebGuessOpenedOuterClass.SCWebGuessOpened.newBuilder();
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
    
    public static Parser<SCWebGuessOpened> parser() {
      return PARSER;
    }
    
    public Parser<SCWebGuessOpened> getParserForType() {
      return PARSER;
    }
    
    public SCWebGuessOpened getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }
  }
  
  public static Descriptors.FileDescriptor getDescriptor() {
    return descriptor;
  }
  
  static {
    String[] descriptorData = { "\n\026SCWebGuessOpened.proto\"h\n\020SCWebGuessOpened\022\f\n\004time\030\001 \001(\004\022\017\n\007guessId\030\002 \001(\t\022\026\n\016submitDeadline\030\003 \001(\004\022\035\n\025displayMaxDelayMillis\030\004 \001(\004B<\n:tech.ordinaryroad.live.chat.client.codec.kuaishou.protobufb\006proto3" };
    descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[0]);
    internal_static_SCWebGuessOpened_descriptor = getDescriptor().getMessageTypes().get(0);
    internal_static_SCWebGuessOpened_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_SCWebGuessOpened_descriptor, new String[] { "Time", "GuessId", "SubmitDeadline", "DisplayMaxDelayMillis" });
  }
  
  public static interface SCWebGuessOpenedOrBuilder extends MessageOrBuilder {
    long getTime();
    
    String getGuessId();
    
    ByteString getGuessIdBytes();
    
    long getSubmitDeadline();
    
    long getDisplayMaxDelayMillis();
  }
}
