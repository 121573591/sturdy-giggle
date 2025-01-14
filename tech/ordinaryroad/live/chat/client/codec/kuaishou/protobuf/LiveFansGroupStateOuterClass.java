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

public final class LiveFansGroupStateOuterClass {
  private static final Descriptors.Descriptor internal_static_LiveFansGroupState_descriptor;
  
  private static final GeneratedMessageV3.FieldAccessorTable internal_static_LiveFansGroupState_fieldAccessorTable;
  
  private static Descriptors.FileDescriptor descriptor;
  
  public static void registerAllExtensions(ExtensionRegistryLite registry) {}
  
  public static void registerAllExtensions(ExtensionRegistry registry) {
    registerAllExtensions((ExtensionRegistryLite)registry);
  }
  
  public static final class LiveFansGroupState extends GeneratedMessageV3 implements LiveFansGroupStateOrBuilder {
    private static final long serialVersionUID = 0L;
    
    public static final int INTIMACYLEVEL_FIELD_NUMBER = 1;
    
    private int intimacyLevel_;
    
    public static final int ENTERROOMSPECIALEFFECT_FIELD_NUMBER = 2;
    
    private int enterRoomSpecialEffect_;
    
    private byte memoizedIsInitialized;
    
    private LiveFansGroupState(GeneratedMessageV3.Builder<?> builder) {
      super(builder);
      this.intimacyLevel_ = 0;
      this.enterRoomSpecialEffect_ = 0;
      this.memoizedIsInitialized = -1;
    }
    
    private LiveFansGroupState() {
      this.intimacyLevel_ = 0;
      this.enterRoomSpecialEffect_ = 0;
      this.memoizedIsInitialized = -1;
    }
    
    protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
      return new LiveFansGroupState();
    }
    
    public static final Descriptors.Descriptor getDescriptor() {
      return LiveFansGroupStateOuterClass.internal_static_LiveFansGroupState_descriptor;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return LiveFansGroupStateOuterClass.internal_static_LiveFansGroupState_fieldAccessorTable.ensureFieldAccessorsInitialized(LiveFansGroupState.class, Builder.class);
    }
    
    public int getIntimacyLevel() {
      return this.intimacyLevel_;
    }
    
    public int getEnterRoomSpecialEffect() {
      return this.enterRoomSpecialEffect_;
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
      if (this.intimacyLevel_ != 0)
        output.writeUInt32(1, this.intimacyLevel_); 
      if (this.enterRoomSpecialEffect_ != 0)
        output.writeUInt32(2, this.enterRoomSpecialEffect_); 
      getUnknownFields().writeTo(output);
    }
    
    public int getSerializedSize() {
      int size = this.memoizedSize;
      if (size != -1)
        return size; 
      size = 0;
      if (this.intimacyLevel_ != 0)
        size += 
          CodedOutputStream.computeUInt32Size(1, this.intimacyLevel_); 
      if (this.enterRoomSpecialEffect_ != 0)
        size += 
          CodedOutputStream.computeUInt32Size(2, this.enterRoomSpecialEffect_); 
      size += getUnknownFields().getSerializedSize();
      this.memoizedSize = size;
      return size;
    }
    
    public boolean equals(Object obj) {
      if (obj == this)
        return true; 
      if (!(obj instanceof LiveFansGroupState))
        return super.equals(obj); 
      LiveFansGroupState other = (LiveFansGroupState)obj;
      if (getIntimacyLevel() != other
        .getIntimacyLevel())
        return false; 
      if (getEnterRoomSpecialEffect() != other
        .getEnterRoomSpecialEffect())
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
      hash = 53 * hash + getIntimacyLevel();
      hash = 37 * hash + 2;
      hash = 53 * hash + getEnterRoomSpecialEffect();
      hash = 29 * hash + getUnknownFields().hashCode();
      this.memoizedHashCode = hash;
      return hash;
    }
    
    public static LiveFansGroupState parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (LiveFansGroupState)PARSER.parseFrom(data);
    }
    
    public static LiveFansGroupState parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (LiveFansGroupState)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static LiveFansGroupState parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (LiveFansGroupState)PARSER.parseFrom(data);
    }
    
    public static LiveFansGroupState parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (LiveFansGroupState)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static LiveFansGroupState parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (LiveFansGroupState)PARSER.parseFrom(data);
    }
    
    public static LiveFansGroupState parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (LiveFansGroupState)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static LiveFansGroupState parseFrom(InputStream input) throws IOException {
      return 
        (LiveFansGroupState)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static LiveFansGroupState parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (LiveFansGroupState)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static LiveFansGroupState parseDelimitedFrom(InputStream input) throws IOException {
      return 
        (LiveFansGroupState)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }
    
    public static LiveFansGroupState parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (LiveFansGroupState)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static LiveFansGroupState parseFrom(CodedInputStream input) throws IOException {
      return 
        (LiveFansGroupState)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static LiveFansGroupState parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (LiveFansGroupState)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public Builder newBuilderForType() {
      return newBuilder();
    }
    
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    
    public static Builder newBuilder(LiveFansGroupState prototype) {
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
    
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements LiveFansGroupStateOuterClass.LiveFansGroupStateOrBuilder {
      private int bitField0_;
      
      private int intimacyLevel_;
      
      private int enterRoomSpecialEffect_;
      
      public static final Descriptors.Descriptor getDescriptor() {
        return LiveFansGroupStateOuterClass.internal_static_LiveFansGroupState_descriptor;
      }
      
      protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return LiveFansGroupStateOuterClass.internal_static_LiveFansGroupState_fieldAccessorTable
          .ensureFieldAccessorsInitialized(LiveFansGroupStateOuterClass.LiveFansGroupState.class, Builder.class);
      }
      
      private Builder() {}
      
      private Builder(GeneratedMessageV3.BuilderParent parent) {
        super(parent);
      }
      
      public Builder clear() {
        super.clear();
        this.bitField0_ = 0;
        this.intimacyLevel_ = 0;
        this.enterRoomSpecialEffect_ = 0;
        return this;
      }
      
      public Descriptors.Descriptor getDescriptorForType() {
        return LiveFansGroupStateOuterClass.internal_static_LiveFansGroupState_descriptor;
      }
      
      public LiveFansGroupStateOuterClass.LiveFansGroupState getDefaultInstanceForType() {
        return LiveFansGroupStateOuterClass.LiveFansGroupState.getDefaultInstance();
      }
      
      public LiveFansGroupStateOuterClass.LiveFansGroupState build() {
        LiveFansGroupStateOuterClass.LiveFansGroupState result = buildPartial();
        if (!result.isInitialized())
          throw newUninitializedMessageException(result); 
        return result;
      }
      
      public LiveFansGroupStateOuterClass.LiveFansGroupState buildPartial() {
        LiveFansGroupStateOuterClass.LiveFansGroupState result = new LiveFansGroupStateOuterClass.LiveFansGroupState(this);
        if (this.bitField0_ != 0)
          buildPartial0(result); 
        onBuilt();
        return result;
      }
      
      private void buildPartial0(LiveFansGroupStateOuterClass.LiveFansGroupState result) {
        int from_bitField0_ = this.bitField0_;
        if ((from_bitField0_ & 0x1) != 0)
          result.intimacyLevel_ = this.intimacyLevel_; 
        if ((from_bitField0_ & 0x2) != 0)
          result.enterRoomSpecialEffect_ = this.enterRoomSpecialEffect_; 
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
        if (other instanceof LiveFansGroupStateOuterClass.LiveFansGroupState)
          return mergeFrom((LiveFansGroupStateOuterClass.LiveFansGroupState)other); 
        super.mergeFrom(other);
        return this;
      }
      
      public Builder mergeFrom(LiveFansGroupStateOuterClass.LiveFansGroupState other) {
        if (other == LiveFansGroupStateOuterClass.LiveFansGroupState.getDefaultInstance())
          return this; 
        if (other.getIntimacyLevel() != 0)
          setIntimacyLevel(other.getIntimacyLevel()); 
        if (other.getEnterRoomSpecialEffect() != 0)
          setEnterRoomSpecialEffect(other.getEnterRoomSpecialEffect()); 
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
                this.intimacyLevel_ = input.readUInt32();
                this.bitField0_ |= 0x1;
                continue;
              case 16:
                this.enterRoomSpecialEffect_ = input.readUInt32();
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
      
      public int getIntimacyLevel() {
        return this.intimacyLevel_;
      }
      
      public Builder setIntimacyLevel(int value) {
        this.intimacyLevel_ = value;
        this.bitField0_ |= 0x1;
        onChanged();
        return this;
      }
      
      public Builder clearIntimacyLevel() {
        this.bitField0_ &= 0xFFFFFFFE;
        this.intimacyLevel_ = 0;
        onChanged();
        return this;
      }
      
      public int getEnterRoomSpecialEffect() {
        return this.enterRoomSpecialEffect_;
      }
      
      public Builder setEnterRoomSpecialEffect(int value) {
        this.enterRoomSpecialEffect_ = value;
        this.bitField0_ |= 0x2;
        onChanged();
        return this;
      }
      
      public Builder clearEnterRoomSpecialEffect() {
        this.bitField0_ &= 0xFFFFFFFD;
        this.enterRoomSpecialEffect_ = 0;
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
    
    private static final LiveFansGroupState DEFAULT_INSTANCE = new LiveFansGroupState();
    
    public static LiveFansGroupState getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }
    
    private static final Parser<LiveFansGroupState> PARSER = (Parser<LiveFansGroupState>)new AbstractParser<LiveFansGroupState>() {
        public LiveFansGroupStateOuterClass.LiveFansGroupState parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
          LiveFansGroupStateOuterClass.LiveFansGroupState.Builder builder = LiveFansGroupStateOuterClass.LiveFansGroupState.newBuilder();
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
    
    public static Parser<LiveFansGroupState> parser() {
      return PARSER;
    }
    
    public Parser<LiveFansGroupState> getParserForType() {
      return PARSER;
    }
    
    public LiveFansGroupState getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }
  }
  
  public static Descriptors.FileDescriptor getDescriptor() {
    return descriptor;
  }
  
  static {
    String[] descriptorData = { "\n\030LiveFansGroupState.proto\"K\n\022LiveFansGroupState\022\025\n\rintimacyLevel\030\001 \001(\r\022\036\n\026enterRoomSpecialEffect\030\002 \001(\rB<\n:tech.ordinaryroad.live.chat.client.codec.kuaishou.protobufb\006proto3" };
    descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[0]);
    internal_static_LiveFansGroupState_descriptor = getDescriptor().getMessageTypes().get(0);
    internal_static_LiveFansGroupState_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_LiveFansGroupState_descriptor, new String[] { "IntimacyLevel", "EnterRoomSpecialEffect" });
  }
  
  public static interface LiveFansGroupStateOrBuilder extends MessageOrBuilder {
    int getIntimacyLevel();
    
    int getEnterRoomSpecialEffect();
  }
}
