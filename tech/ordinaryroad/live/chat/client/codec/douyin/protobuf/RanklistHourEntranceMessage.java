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

public final class RanklistHourEntranceMessage extends GeneratedMessageV3 implements RanklistHourEntranceMessageOrBuilder {
  private static final long serialVersionUID = 0L;
  
  private int bitField0_;
  
  public static final int COMMON_FIELD_NUMBER = 1;
  
  private Common common_;
  
  public static final int INFO_FIELD_NUMBER = 2;
  
  private RanklistHourEntrance info_;
  
  private byte memoizedIsInitialized;
  
  private RanklistHourEntranceMessage(GeneratedMessageV3.Builder<?> builder) {
    super(builder);
    this.memoizedIsInitialized = -1;
  }
  
  private RanklistHourEntranceMessage() {
    this.memoizedIsInitialized = -1;
  }
  
  protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
    return new RanklistHourEntranceMessage();
  }
  
  public static final Descriptors.Descriptor getDescriptor() {
    return Douyin.internal_static_RanklistHourEntranceMessage_descriptor;
  }
  
  protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
    return Douyin.internal_static_RanklistHourEntranceMessage_fieldAccessorTable.ensureFieldAccessorsInitialized(RanklistHourEntranceMessage.class, Builder.class);
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
  
  public boolean hasInfo() {
    return ((this.bitField0_ & 0x2) != 0);
  }
  
  public RanklistHourEntrance getInfo() {
    return (this.info_ == null) ? RanklistHourEntrance.getDefaultInstance() : this.info_;
  }
  
  public RanklistHourEntranceOrBuilder getInfoOrBuilder() {
    return (this.info_ == null) ? RanklistHourEntrance.getDefaultInstance() : this.info_;
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
    if ((this.bitField0_ & 0x2) != 0)
      output.writeMessage(2, (MessageLite)getInfo()); 
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
    if ((this.bitField0_ & 0x2) != 0)
      size += 
        CodedOutputStream.computeMessageSize(2, (MessageLite)getInfo()); 
    size += getUnknownFields().getSerializedSize();
    this.memoizedSize = size;
    return size;
  }
  
  public boolean equals(Object obj) {
    if (obj == this)
      return true; 
    if (!(obj instanceof RanklistHourEntranceMessage))
      return super.equals(obj); 
    RanklistHourEntranceMessage other = (RanklistHourEntranceMessage)obj;
    if (hasCommon() != other.hasCommon())
      return false; 
    if (hasCommon() && 
      
      !getCommon().equals(other.getCommon()))
      return false; 
    if (hasInfo() != other.hasInfo())
      return false; 
    if (hasInfo() && 
      
      !getInfo().equals(other.getInfo()))
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
    if (hasInfo()) {
      hash = 37 * hash + 2;
      hash = 53 * hash + getInfo().hashCode();
    } 
    hash = 29 * hash + getUnknownFields().hashCode();
    this.memoizedHashCode = hash;
    return hash;
  }
  
  public static RanklistHourEntranceMessage parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
    return (RanklistHourEntranceMessage)PARSER.parseFrom(data);
  }
  
  public static RanklistHourEntranceMessage parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (RanklistHourEntranceMessage)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static RanklistHourEntranceMessage parseFrom(ByteString data) throws InvalidProtocolBufferException {
    return (RanklistHourEntranceMessage)PARSER.parseFrom(data);
  }
  
  public static RanklistHourEntranceMessage parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (RanklistHourEntranceMessage)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static RanklistHourEntranceMessage parseFrom(byte[] data) throws InvalidProtocolBufferException {
    return (RanklistHourEntranceMessage)PARSER.parseFrom(data);
  }
  
  public static RanklistHourEntranceMessage parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (RanklistHourEntranceMessage)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static RanklistHourEntranceMessage parseFrom(InputStream input) throws IOException {
    return 
      (RanklistHourEntranceMessage)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static RanklistHourEntranceMessage parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (RanklistHourEntranceMessage)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static RanklistHourEntranceMessage parseDelimitedFrom(InputStream input) throws IOException {
    return 
      (RanklistHourEntranceMessage)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
  }
  
  public static RanklistHourEntranceMessage parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (RanklistHourEntranceMessage)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static RanklistHourEntranceMessage parseFrom(CodedInputStream input) throws IOException {
    return 
      (RanklistHourEntranceMessage)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static RanklistHourEntranceMessage parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (RanklistHourEntranceMessage)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public Builder newBuilderForType() {
    return newBuilder();
  }
  
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  
  public static Builder newBuilder(RanklistHourEntranceMessage prototype) {
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
  
  public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements RanklistHourEntranceMessageOrBuilder {
    private int bitField0_;
    
    private Common common_;
    
    private SingleFieldBuilderV3<Common, Common.Builder, CommonOrBuilder> commonBuilder_;
    
    private RanklistHourEntrance info_;
    
    private SingleFieldBuilderV3<RanklistHourEntrance, RanklistHourEntrance.Builder, RanklistHourEntranceOrBuilder> infoBuilder_;
    
    public static final Descriptors.Descriptor getDescriptor() {
      return Douyin.internal_static_RanklistHourEntranceMessage_descriptor;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return Douyin.internal_static_RanklistHourEntranceMessage_fieldAccessorTable
        .ensureFieldAccessorsInitialized(RanklistHourEntranceMessage.class, Builder.class);
    }
    
    private Builder() {
      maybeForceBuilderInitialization();
    }
    
    private Builder(GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    
    private void maybeForceBuilderInitialization() {
      if (RanklistHourEntranceMessage.alwaysUseFieldBuilders) {
        getCommonFieldBuilder();
        getInfoFieldBuilder();
      } 
    }
    
    public Builder clear() {
      super.clear();
      this.bitField0_ = 0;
      this.common_ = null;
      if (this.commonBuilder_ != null) {
        this.commonBuilder_.dispose();
        this.commonBuilder_ = null;
      } 
      this.info_ = null;
      if (this.infoBuilder_ != null) {
        this.infoBuilder_.dispose();
        this.infoBuilder_ = null;
      } 
      return this;
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
      return Douyin.internal_static_RanklistHourEntranceMessage_descriptor;
    }
    
    public RanklistHourEntranceMessage getDefaultInstanceForType() {
      return RanklistHourEntranceMessage.getDefaultInstance();
    }
    
    public RanklistHourEntranceMessage build() {
      RanklistHourEntranceMessage result = buildPartial();
      if (!result.isInitialized())
        throw newUninitializedMessageException(result); 
      return result;
    }
    
    public RanklistHourEntranceMessage buildPartial() {
      RanklistHourEntranceMessage result = new RanklistHourEntranceMessage(this);
      if (this.bitField0_ != 0)
        buildPartial0(result); 
      onBuilt();
      return result;
    }
    
    private void buildPartial0(RanklistHourEntranceMessage result) {
      int from_bitField0_ = this.bitField0_;
      int to_bitField0_ = 0;
      if ((from_bitField0_ & 0x1) != 0) {
        result.common_ = (this.commonBuilder_ == null) ? 
          this.common_ : 
          (Common)this.commonBuilder_.build();
        to_bitField0_ |= 0x1;
      } 
      if ((from_bitField0_ & 0x2) != 0) {
        result.info_ = (this.infoBuilder_ == null) ? 
          this.info_ : 
          (RanklistHourEntrance)this.infoBuilder_.build();
        to_bitField0_ |= 0x2;
      } 
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
      if (other instanceof RanklistHourEntranceMessage)
        return mergeFrom((RanklistHourEntranceMessage)other); 
      super.mergeFrom(other);
      return this;
    }
    
    public Builder mergeFrom(RanklistHourEntranceMessage other) {
      if (other == RanklistHourEntranceMessage.getDefaultInstance())
        return this; 
      if (other.hasCommon())
        mergeCommon(other.getCommon()); 
      if (other.hasInfo())
        mergeInfo(other.getInfo()); 
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
            case 18:
              input.readMessage((MessageLite.Builder)
                  getInfoFieldBuilder().getBuilder(), extensionRegistry);
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
    
    public boolean hasInfo() {
      return ((this.bitField0_ & 0x2) != 0);
    }
    
    public RanklistHourEntrance getInfo() {
      if (this.infoBuilder_ == null)
        return (this.info_ == null) ? RanklistHourEntrance.getDefaultInstance() : this.info_; 
      return (RanklistHourEntrance)this.infoBuilder_.getMessage();
    }
    
    public Builder setInfo(RanklistHourEntrance value) {
      if (this.infoBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        this.info_ = value;
      } else {
        this.infoBuilder_.setMessage((AbstractMessage)value);
      } 
      this.bitField0_ |= 0x2;
      onChanged();
      return this;
    }
    
    public Builder setInfo(RanklistHourEntrance.Builder builderForValue) {
      if (this.infoBuilder_ == null) {
        this.info_ = builderForValue.build();
      } else {
        this.infoBuilder_.setMessage((AbstractMessage)builderForValue.build());
      } 
      this.bitField0_ |= 0x2;
      onChanged();
      return this;
    }
    
    public Builder mergeInfo(RanklistHourEntrance value) {
      if (this.infoBuilder_ == null) {
        if ((this.bitField0_ & 0x2) != 0 && this.info_ != null && this.info_ != 
          
          RanklistHourEntrance.getDefaultInstance()) {
          getInfoBuilder().mergeFrom(value);
        } else {
          this.info_ = value;
        } 
      } else {
        this.infoBuilder_.mergeFrom((AbstractMessage)value);
      } 
      if (this.info_ != null) {
        this.bitField0_ |= 0x2;
        onChanged();
      } 
      return this;
    }
    
    public Builder clearInfo() {
      this.bitField0_ &= 0xFFFFFFFD;
      this.info_ = null;
      if (this.infoBuilder_ != null) {
        this.infoBuilder_.dispose();
        this.infoBuilder_ = null;
      } 
      onChanged();
      return this;
    }
    
    public RanklistHourEntrance.Builder getInfoBuilder() {
      this.bitField0_ |= 0x2;
      onChanged();
      return (RanklistHourEntrance.Builder)getInfoFieldBuilder().getBuilder();
    }
    
    public RanklistHourEntranceOrBuilder getInfoOrBuilder() {
      if (this.infoBuilder_ != null)
        return (RanklistHourEntranceOrBuilder)this.infoBuilder_.getMessageOrBuilder(); 
      return (this.info_ == null) ? 
        RanklistHourEntrance.getDefaultInstance() : this.info_;
    }
    
    private SingleFieldBuilderV3<RanklistHourEntrance, RanklistHourEntrance.Builder, RanklistHourEntranceOrBuilder> getInfoFieldBuilder() {
      if (this.infoBuilder_ == null) {
        this
          
          .infoBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getInfo(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.info_ = null;
      } 
      return this.infoBuilder_;
    }
    
    public final Builder setUnknownFields(UnknownFieldSet unknownFields) {
      return (Builder)super.setUnknownFields(unknownFields);
    }
    
    public final Builder mergeUnknownFields(UnknownFieldSet unknownFields) {
      return (Builder)super.mergeUnknownFields(unknownFields);
    }
  }
  
  private static final RanklistHourEntranceMessage DEFAULT_INSTANCE = new RanklistHourEntranceMessage();
  
  public static RanklistHourEntranceMessage getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }
  
  private static final Parser<RanklistHourEntranceMessage> PARSER = (Parser<RanklistHourEntranceMessage>)new AbstractParser<RanklistHourEntranceMessage>() {
      public RanklistHourEntranceMessage parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        RanklistHourEntranceMessage.Builder builder = RanklistHourEntranceMessage.newBuilder();
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
  
  public static Parser<RanklistHourEntranceMessage> parser() {
    return PARSER;
  }
  
  public Parser<RanklistHourEntranceMessage> getParserForType() {
    return PARSER;
  }
  
  public RanklistHourEntranceMessage getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }
}
