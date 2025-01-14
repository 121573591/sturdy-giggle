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

public final class UpdateFanTicketMessage extends GeneratedMessageV3 implements UpdateFanTicketMessageOrBuilder {
  private static final long serialVersionUID = 0L;
  
  private int bitField0_;
  
  public static final int COMMON_FIELD_NUMBER = 1;
  
  private Common common_;
  
  public static final int ROOMFANTICKETCOUNTTEXT_FIELD_NUMBER = 2;
  
  private volatile Object roomFanTicketCountText_;
  
  public static final int ROOMFANTICKETCOUNT_FIELD_NUMBER = 3;
  
  private long roomFanTicketCount_;
  
  public static final int FORCEUPDATE_FIELD_NUMBER = 4;
  
  private boolean forceUpdate_;
  
  private byte memoizedIsInitialized;
  
  private UpdateFanTicketMessage(GeneratedMessageV3.Builder<?> builder) {
    super(builder);
    this.roomFanTicketCountText_ = "";
    this.roomFanTicketCount_ = 0L;
    this.forceUpdate_ = false;
    this.memoizedIsInitialized = -1;
  }
  
  private UpdateFanTicketMessage() {
    this.roomFanTicketCountText_ = "";
    this.roomFanTicketCount_ = 0L;
    this.forceUpdate_ = false;
    this.memoizedIsInitialized = -1;
    this.roomFanTicketCountText_ = "";
  }
  
  protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
    return new UpdateFanTicketMessage();
  }
  
  public static final Descriptors.Descriptor getDescriptor() {
    return Douyin.internal_static_UpdateFanTicketMessage_descriptor;
  }
  
  protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
    return Douyin.internal_static_UpdateFanTicketMessage_fieldAccessorTable.ensureFieldAccessorsInitialized(UpdateFanTicketMessage.class, Builder.class);
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
  
  public String getRoomFanTicketCountText() {
    Object ref = this.roomFanTicketCountText_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.roomFanTicketCountText_ = s;
    return s;
  }
  
  public ByteString getRoomFanTicketCountTextBytes() {
    Object ref = this.roomFanTicketCountText_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.roomFanTicketCountText_ = b;
      return b;
    } 
    return (ByteString)ref;
  }
  
  public long getRoomFanTicketCount() {
    return this.roomFanTicketCount_;
  }
  
  public boolean getForceUpdate() {
    return this.forceUpdate_;
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
    if (!GeneratedMessageV3.isStringEmpty(this.roomFanTicketCountText_))
      GeneratedMessageV3.writeString(output, 2, this.roomFanTicketCountText_); 
    if (this.roomFanTicketCount_ != 0L)
      output.writeUInt64(3, this.roomFanTicketCount_); 
    if (this.forceUpdate_)
      output.writeBool(4, this.forceUpdate_); 
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
    if (!GeneratedMessageV3.isStringEmpty(this.roomFanTicketCountText_))
      size += GeneratedMessageV3.computeStringSize(2, this.roomFanTicketCountText_); 
    if (this.roomFanTicketCount_ != 0L)
      size += 
        CodedOutputStream.computeUInt64Size(3, this.roomFanTicketCount_); 
    if (this.forceUpdate_)
      size += 
        CodedOutputStream.computeBoolSize(4, this.forceUpdate_); 
    size += getUnknownFields().getSerializedSize();
    this.memoizedSize = size;
    return size;
  }
  
  public boolean equals(Object obj) {
    if (obj == this)
      return true; 
    if (!(obj instanceof UpdateFanTicketMessage))
      return super.equals(obj); 
    UpdateFanTicketMessage other = (UpdateFanTicketMessage)obj;
    if (hasCommon() != other.hasCommon())
      return false; 
    if (hasCommon() && 
      
      !getCommon().equals(other.getCommon()))
      return false; 
    if (!getRoomFanTicketCountText().equals(other.getRoomFanTicketCountText()))
      return false; 
    if (getRoomFanTicketCount() != other
      .getRoomFanTicketCount())
      return false; 
    if (getForceUpdate() != other
      .getForceUpdate())
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
    hash = 53 * hash + getRoomFanTicketCountText().hashCode();
    hash = 37 * hash + 3;
    hash = 53 * hash + Internal.hashLong(
        getRoomFanTicketCount());
    hash = 37 * hash + 4;
    hash = 53 * hash + Internal.hashBoolean(
        getForceUpdate());
    hash = 29 * hash + getUnknownFields().hashCode();
    this.memoizedHashCode = hash;
    return hash;
  }
  
  public static UpdateFanTicketMessage parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
    return (UpdateFanTicketMessage)PARSER.parseFrom(data);
  }
  
  public static UpdateFanTicketMessage parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (UpdateFanTicketMessage)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static UpdateFanTicketMessage parseFrom(ByteString data) throws InvalidProtocolBufferException {
    return (UpdateFanTicketMessage)PARSER.parseFrom(data);
  }
  
  public static UpdateFanTicketMessage parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (UpdateFanTicketMessage)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static UpdateFanTicketMessage parseFrom(byte[] data) throws InvalidProtocolBufferException {
    return (UpdateFanTicketMessage)PARSER.parseFrom(data);
  }
  
  public static UpdateFanTicketMessage parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (UpdateFanTicketMessage)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static UpdateFanTicketMessage parseFrom(InputStream input) throws IOException {
    return 
      (UpdateFanTicketMessage)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static UpdateFanTicketMessage parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (UpdateFanTicketMessage)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static UpdateFanTicketMessage parseDelimitedFrom(InputStream input) throws IOException {
    return 
      (UpdateFanTicketMessage)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
  }
  
  public static UpdateFanTicketMessage parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (UpdateFanTicketMessage)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static UpdateFanTicketMessage parseFrom(CodedInputStream input) throws IOException {
    return 
      (UpdateFanTicketMessage)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static UpdateFanTicketMessage parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (UpdateFanTicketMessage)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public Builder newBuilderForType() {
    return newBuilder();
  }
  
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  
  public static Builder newBuilder(UpdateFanTicketMessage prototype) {
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
  
  public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements UpdateFanTicketMessageOrBuilder {
    private int bitField0_;
    
    private Common common_;
    
    private SingleFieldBuilderV3<Common, Common.Builder, CommonOrBuilder> commonBuilder_;
    
    private Object roomFanTicketCountText_;
    
    private long roomFanTicketCount_;
    
    private boolean forceUpdate_;
    
    public static final Descriptors.Descriptor getDescriptor() {
      return Douyin.internal_static_UpdateFanTicketMessage_descriptor;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return Douyin.internal_static_UpdateFanTicketMessage_fieldAccessorTable
        .ensureFieldAccessorsInitialized(UpdateFanTicketMessage.class, Builder.class);
    }
    
    private Builder() {
      this.roomFanTicketCountText_ = "";
      maybeForceBuilderInitialization();
    }
    
    private Builder(GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      this.roomFanTicketCountText_ = "";
      maybeForceBuilderInitialization();
    }
    
    private void maybeForceBuilderInitialization() {
      if (UpdateFanTicketMessage.alwaysUseFieldBuilders)
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
      this.roomFanTicketCountText_ = "";
      this.roomFanTicketCount_ = 0L;
      this.forceUpdate_ = false;
      return this;
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
      return Douyin.internal_static_UpdateFanTicketMessage_descriptor;
    }
    
    public UpdateFanTicketMessage getDefaultInstanceForType() {
      return UpdateFanTicketMessage.getDefaultInstance();
    }
    
    public UpdateFanTicketMessage build() {
      UpdateFanTicketMessage result = buildPartial();
      if (!result.isInitialized())
        throw newUninitializedMessageException(result); 
      return result;
    }
    
    public UpdateFanTicketMessage buildPartial() {
      UpdateFanTicketMessage result = new UpdateFanTicketMessage(this);
      if (this.bitField0_ != 0)
        buildPartial0(result); 
      onBuilt();
      return result;
    }
    
    private void buildPartial0(UpdateFanTicketMessage result) {
      int from_bitField0_ = this.bitField0_;
      int to_bitField0_ = 0;
      if ((from_bitField0_ & 0x1) != 0) {
        result.common_ = (this.commonBuilder_ == null) ? this.common_ : (Common)this.commonBuilder_.build();
        to_bitField0_ |= 0x1;
      } 
      if ((from_bitField0_ & 0x2) != 0)
        result.roomFanTicketCountText_ = this.roomFanTicketCountText_; 
      if ((from_bitField0_ & 0x4) != 0)
        result.roomFanTicketCount_ = this.roomFanTicketCount_; 
      if ((from_bitField0_ & 0x8) != 0)
        result.forceUpdate_ = this.forceUpdate_; 
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
      if (other instanceof UpdateFanTicketMessage)
        return mergeFrom((UpdateFanTicketMessage)other); 
      super.mergeFrom(other);
      return this;
    }
    
    public Builder mergeFrom(UpdateFanTicketMessage other) {
      if (other == UpdateFanTicketMessage.getDefaultInstance())
        return this; 
      if (other.hasCommon())
        mergeCommon(other.getCommon()); 
      if (!other.getRoomFanTicketCountText().isEmpty()) {
        this.roomFanTicketCountText_ = other.roomFanTicketCountText_;
        this.bitField0_ |= 0x2;
        onChanged();
      } 
      if (other.getRoomFanTicketCount() != 0L)
        setRoomFanTicketCount(other.getRoomFanTicketCount()); 
      if (other.getForceUpdate())
        setForceUpdate(other.getForceUpdate()); 
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
              input.readMessage((MessageLite.Builder)getCommonFieldBuilder().getBuilder(), extensionRegistry);
              this.bitField0_ |= 0x1;
              continue;
            case 18:
              this.roomFanTicketCountText_ = input.readStringRequireUtf8();
              this.bitField0_ |= 0x2;
              continue;
            case 24:
              this.roomFanTicketCount_ = input.readUInt64();
              this.bitField0_ |= 0x4;
              continue;
            case 32:
              this.forceUpdate_ = input.readBool();
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
        if ((this.bitField0_ & 0x1) != 0 && this.common_ != null && this.common_ != Common.getDefaultInstance()) {
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
      return (this.common_ == null) ? Common.getDefaultInstance() : this.common_;
    }
    
    private SingleFieldBuilderV3<Common, Common.Builder, CommonOrBuilder> getCommonFieldBuilder() {
      if (this.commonBuilder_ == null) {
        this.commonBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getCommon(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.common_ = null;
      } 
      return this.commonBuilder_;
    }
    
    public String getRoomFanTicketCountText() {
      Object ref = this.roomFanTicketCountText_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.roomFanTicketCountText_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getRoomFanTicketCountTextBytes() {
      Object ref = this.roomFanTicketCountText_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.roomFanTicketCountText_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setRoomFanTicketCountText(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.roomFanTicketCountText_ = value;
      this.bitField0_ |= 0x2;
      onChanged();
      return this;
    }
    
    public Builder clearRoomFanTicketCountText() {
      this.roomFanTicketCountText_ = UpdateFanTicketMessage.getDefaultInstance().getRoomFanTicketCountText();
      this.bitField0_ &= 0xFFFFFFFD;
      onChanged();
      return this;
    }
    
    public Builder setRoomFanTicketCountTextBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      UpdateFanTicketMessage.checkByteStringIsUtf8(value);
      this.roomFanTicketCountText_ = value;
      this.bitField0_ |= 0x2;
      onChanged();
      return this;
    }
    
    public long getRoomFanTicketCount() {
      return this.roomFanTicketCount_;
    }
    
    public Builder setRoomFanTicketCount(long value) {
      this.roomFanTicketCount_ = value;
      this.bitField0_ |= 0x4;
      onChanged();
      return this;
    }
    
    public Builder clearRoomFanTicketCount() {
      this.bitField0_ &= 0xFFFFFFFB;
      this.roomFanTicketCount_ = 0L;
      onChanged();
      return this;
    }
    
    public boolean getForceUpdate() {
      return this.forceUpdate_;
    }
    
    public Builder setForceUpdate(boolean value) {
      this.forceUpdate_ = value;
      this.bitField0_ |= 0x8;
      onChanged();
      return this;
    }
    
    public Builder clearForceUpdate() {
      this.bitField0_ &= 0xFFFFFFF7;
      this.forceUpdate_ = false;
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
  
  private static final UpdateFanTicketMessage DEFAULT_INSTANCE = new UpdateFanTicketMessage();
  
  public static UpdateFanTicketMessage getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }
  
  private static final Parser<UpdateFanTicketMessage> PARSER = (Parser<UpdateFanTicketMessage>)new AbstractParser<UpdateFanTicketMessage>() {
      public UpdateFanTicketMessage parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        UpdateFanTicketMessage.Builder builder = UpdateFanTicketMessage.newBuilder();
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
  
  public static Parser<UpdateFanTicketMessage> parser() {
    return PARSER;
  }
  
  public Parser<UpdateFanTicketMessage> getParserForType() {
    return PARSER;
  }
  
  public UpdateFanTicketMessage getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }
}
