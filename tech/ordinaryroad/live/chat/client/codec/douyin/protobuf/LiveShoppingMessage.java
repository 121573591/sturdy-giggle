package tech.ordinaryroad.live.chat.client.codec.douyin.protobuf;

import com.google.protobuf.AbstractMessage;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.MessageLite;
import com.google.protobuf.Parser;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class LiveShoppingMessage extends GeneratedMessageV3 implements LiveShoppingMessageOrBuilder {
  private static final long serialVersionUID = 0L;
  
  private int bitField0_;
  
  public static final int COMMON_FIELD_NUMBER = 1;
  
  private Common common_;
  
  public static final int MSGTYPE_FIELD_NUMBER = 2;
  
  private int msgType_;
  
  public static final int PROMOTIONID_FIELD_NUMBER = 4;
  
  private long promotionId_;
  
  private byte memoizedIsInitialized;
  
  private LiveShoppingMessage(GeneratedMessageV3.Builder<?> builder) {
    super(builder);
    this.msgType_ = 0;
    this.promotionId_ = 0L;
    this.memoizedIsInitialized = -1;
  }
  
  private LiveShoppingMessage() {
    this.msgType_ = 0;
    this.promotionId_ = 0L;
    this.memoizedIsInitialized = -1;
  }
  
  protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
    return new LiveShoppingMessage();
  }
  
  public static final Descriptors.Descriptor getDescriptor() {
    return Douyin.internal_static_LiveShoppingMessage_descriptor;
  }
  
  protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
    return Douyin.internal_static_LiveShoppingMessage_fieldAccessorTable.ensureFieldAccessorsInitialized(LiveShoppingMessage.class, Builder.class);
  }
  
  public boolean hasCommon() {
    return ((this.bitField0_ & 0x1) != 0);
  }
  
  public Common getCommon() {
    return (this.common_ == null) ? Common.getDefaultInstance() : this.common_;
  }
  
  public CommonOrBuilder getCommonOrBuilder() {
    return (this.common_ == null) ? Common.getDefaultInstance() : this.common_;
  }
  
  public int getMsgType() {
    return this.msgType_;
  }
  
  public long getPromotionId() {
    return this.promotionId_;
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
    if ((this.bitField0_ & 0x1) != 0)
      output.writeMessage(1, (MessageLite)getCommon()); 
    if (this.msgType_ != 0)
      output.writeInt32(2, this.msgType_); 
    if (this.promotionId_ != 0L)
      output.writeInt64(4, this.promotionId_); 
    getUnknownFields().writeTo(output);
  }
  
  public int getSerializedSize() {
    int size = this.memoizedSize;
    if (size != -1)
      return size; 
    size = 0;
    if ((this.bitField0_ & 0x1) != 0)
      size += 
        CodedOutputStream.computeMessageSize(1, (MessageLite)getCommon()); 
    if (this.msgType_ != 0)
      size += 
        CodedOutputStream.computeInt32Size(2, this.msgType_); 
    if (this.promotionId_ != 0L)
      size += 
        CodedOutputStream.computeInt64Size(4, this.promotionId_); 
    size += getUnknownFields().getSerializedSize();
    this.memoizedSize = size;
    return size;
  }
  
  public boolean equals(Object obj) {
    if (obj == this)
      return true; 
    if (!(obj instanceof LiveShoppingMessage))
      return super.equals(obj); 
    LiveShoppingMessage other = (LiveShoppingMessage)obj;
    if (hasCommon() != other.hasCommon())
      return false; 
    if (hasCommon() && 
      
      !getCommon().equals(other.getCommon()))
      return false; 
    if (getMsgType() != other
      .getMsgType())
      return false; 
    if (getPromotionId() != other
      .getPromotionId())
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
    if (hasCommon()) {
      hash = 37 * hash + 1;
      hash = 53 * hash + getCommon().hashCode();
    } 
    hash = 37 * hash + 2;
    hash = 53 * hash + getMsgType();
    hash = 37 * hash + 4;
    hash = 53 * hash + Internal.hashLong(
        getPromotionId());
    hash = 29 * hash + getUnknownFields().hashCode();
    this.memoizedHashCode = hash;
    return hash;
  }
  
  public static LiveShoppingMessage parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
    return (LiveShoppingMessage)PARSER.parseFrom(data);
  }
  
  public static LiveShoppingMessage parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (LiveShoppingMessage)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static LiveShoppingMessage parseFrom(ByteString data) throws InvalidProtocolBufferException {
    return (LiveShoppingMessage)PARSER.parseFrom(data);
  }
  
  public static LiveShoppingMessage parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (LiveShoppingMessage)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static LiveShoppingMessage parseFrom(byte[] data) throws InvalidProtocolBufferException {
    return (LiveShoppingMessage)PARSER.parseFrom(data);
  }
  
  public static LiveShoppingMessage parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (LiveShoppingMessage)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static LiveShoppingMessage parseFrom(InputStream input) throws IOException {
    return 
      (LiveShoppingMessage)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static LiveShoppingMessage parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (LiveShoppingMessage)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static LiveShoppingMessage parseDelimitedFrom(InputStream input) throws IOException {
    return 
      (LiveShoppingMessage)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
  }
  
  public static LiveShoppingMessage parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (LiveShoppingMessage)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static LiveShoppingMessage parseFrom(CodedInputStream input) throws IOException {
    return 
      (LiveShoppingMessage)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static LiveShoppingMessage parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (LiveShoppingMessage)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public Builder newBuilderForType() {
    return newBuilder();
  }
  
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  
  public static Builder newBuilder(LiveShoppingMessage prototype) {
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
  
  public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements LiveShoppingMessageOrBuilder {
    private int bitField0_;
    
    private Common common_;
    
    private SingleFieldBuilderV3<Common, Common.Builder, CommonOrBuilder> commonBuilder_;
    
    private int msgType_;
    
    private long promotionId_;
    
    public static final Descriptors.Descriptor getDescriptor() {
      return Douyin.internal_static_LiveShoppingMessage_descriptor;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return Douyin.internal_static_LiveShoppingMessage_fieldAccessorTable
        .ensureFieldAccessorsInitialized(LiveShoppingMessage.class, Builder.class);
    }
    
    private Builder() {
      maybeForceBuilderInitialization();
    }
    
    private Builder(GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    
    private void maybeForceBuilderInitialization() {
      if (LiveShoppingMessage.alwaysUseFieldBuilders)
        getCommonFieldBuilder(); 
    }
    
    public Builder clear() {
      super.clear();
      this.bitField0_ = 0;
      this.common_ = null;
      if (this.commonBuilder_ != null) {
        this.commonBuilder_.dispose();
        this.commonBuilder_ = null;
      } 
      this.msgType_ = 0;
      this.promotionId_ = 0L;
      return this;
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
      return Douyin.internal_static_LiveShoppingMessage_descriptor;
    }
    
    public LiveShoppingMessage getDefaultInstanceForType() {
      return LiveShoppingMessage.getDefaultInstance();
    }
    
    public LiveShoppingMessage build() {
      LiveShoppingMessage result = buildPartial();
      if (!result.isInitialized())
        throw newUninitializedMessageException(result); 
      return result;
    }
    
    public LiveShoppingMessage buildPartial() {
      LiveShoppingMessage result = new LiveShoppingMessage(this);
      if (this.bitField0_ != 0)
        buildPartial0(result); 
      onBuilt();
      return result;
    }
    
    private void buildPartial0(LiveShoppingMessage result) {
      int from_bitField0_ = this.bitField0_;
      int to_bitField0_ = 0;
      if ((from_bitField0_ & 0x1) != 0) {
        result.common_ = (this.commonBuilder_ == null) ? 
          this.common_ : 
          (Common)this.commonBuilder_.build();
        to_bitField0_ |= 0x1;
      } 
      if ((from_bitField0_ & 0x2) != 0)
        result.msgType_ = this.msgType_; 
      if ((from_bitField0_ & 0x4) != 0)
        result.promotionId_ = this.promotionId_; 
      result.bitField0_ |= to_bitField0_;
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
      if (other instanceof LiveShoppingMessage)
        return mergeFrom((LiveShoppingMessage)other); 
      super.mergeFrom(other);
      return this;
    }
    
    public Builder mergeFrom(LiveShoppingMessage other) {
      if (other == LiveShoppingMessage.getDefaultInstance())
        return this; 
      if (other.hasCommon())
        mergeCommon(other.getCommon()); 
      if (other.getMsgType() != 0)
        setMsgType(other.getMsgType()); 
      if (other.getPromotionId() != 0L)
        setPromotionId(other.getPromotionId()); 
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
              input.readMessage((MessageLite.Builder)
                  getCommonFieldBuilder().getBuilder(), extensionRegistry);
              this.bitField0_ |= 0x1;
              continue;
            case 16:
              this.msgType_ = input.readInt32();
              this.bitField0_ |= 0x2;
              continue;
            case 32:
              this.promotionId_ = input.readInt64();
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
    
    public boolean hasCommon() {
      return ((this.bitField0_ & 0x1) != 0);
    }
    
    public Common getCommon() {
      if (this.commonBuilder_ == null)
        return (this.common_ == null) ? Common.getDefaultInstance() : this.common_; 
      return (Common)this.commonBuilder_.getMessage();
    }
    
    public Builder setCommon(Common value) {
      if (this.commonBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        this.common_ = value;
      } else {
        this.commonBuilder_.setMessage((AbstractMessage)value);
      } 
      this.bitField0_ |= 0x1;
      onChanged();
      return this;
    }
    
    public Builder setCommon(Common.Builder builderForValue) {
      if (this.commonBuilder_ == null) {
        this.common_ = builderForValue.build();
      } else {
        this.commonBuilder_.setMessage((AbstractMessage)builderForValue.build());
      } 
      this.bitField0_ |= 0x1;
      onChanged();
      return this;
    }
    
    public Builder mergeCommon(Common value) {
      if (this.commonBuilder_ == null) {
        if ((this.bitField0_ & 0x1) != 0 && this.common_ != null && this.common_ != 
          
          Common.getDefaultInstance()) {
          getCommonBuilder().mergeFrom(value);
        } else {
          this.common_ = value;
        } 
      } else {
        this.commonBuilder_.mergeFrom((AbstractMessage)value);
      } 
      if (this.common_ != null) {
        this.bitField0_ |= 0x1;
        onChanged();
      } 
      return this;
    }
    
    public Builder clearCommon() {
      this.bitField0_ &= 0xFFFFFFFE;
      this.common_ = null;
      if (this.commonBuilder_ != null) {
        this.commonBuilder_.dispose();
        this.commonBuilder_ = null;
      } 
      onChanged();
      return this;
    }
    
    public Common.Builder getCommonBuilder() {
      this.bitField0_ |= 0x1;
      onChanged();
      return (Common.Builder)getCommonFieldBuilder().getBuilder();
    }
    
    public CommonOrBuilder getCommonOrBuilder() {
      if (this.commonBuilder_ != null)
        return (CommonOrBuilder)this.commonBuilder_.getMessageOrBuilder(); 
      return (this.common_ == null) ? 
        Common.getDefaultInstance() : this.common_;
    }
    
    private SingleFieldBuilderV3<Common, Common.Builder, CommonOrBuilder> getCommonFieldBuilder() {
      if (this.commonBuilder_ == null) {
        this
          
          .commonBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getCommon(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.common_ = null;
      } 
      return this.commonBuilder_;
    }
    
    public int getMsgType() {
      return this.msgType_;
    }
    
    public Builder setMsgType(int value) {
      this.msgType_ = value;
      this.bitField0_ |= 0x2;
      onChanged();
      return this;
    }
    
    public Builder clearMsgType() {
      this.bitField0_ &= 0xFFFFFFFD;
      this.msgType_ = 0;
      onChanged();
      return this;
    }
    
    public long getPromotionId() {
      return this.promotionId_;
    }
    
    public Builder setPromotionId(long value) {
      this.promotionId_ = value;
      this.bitField0_ |= 0x4;
      onChanged();
      return this;
    }
    
    public Builder clearPromotionId() {
      this.bitField0_ &= 0xFFFFFFFB;
      this.promotionId_ = 0L;
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
  
  private static final LiveShoppingMessage DEFAULT_INSTANCE = new LiveShoppingMessage();
  
  public static LiveShoppingMessage getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }
  
  private static final Parser<LiveShoppingMessage> PARSER = (Parser<LiveShoppingMessage>)new AbstractParser<LiveShoppingMessage>() {
      public LiveShoppingMessage parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        LiveShoppingMessage.Builder builder = LiveShoppingMessage.newBuilder();
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
  
  public static Parser<LiveShoppingMessage> parser() {
    return PARSER;
  }
  
  public Parser<LiveShoppingMessage> getParserForType() {
    return PARSER;
  }
  
  public LiveShoppingMessage getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }
}
