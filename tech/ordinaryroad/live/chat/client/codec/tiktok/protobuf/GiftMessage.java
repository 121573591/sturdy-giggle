package tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf;

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

public final class GiftMessage extends GeneratedMessageV3 implements GiftMessageOrBuilder {
  private static final long serialVersionUID = 0L;
  
  private int bitField0_;
  
  public static final int COMMON_FIELD_NUMBER = 1;
  
  private Common common_;
  
  public static final int GIFTID_FIELD_NUMBER = 2;
  
  private long giftId_;
  
  public static final int FANTICKETCOUNT_FIELD_NUMBER = 3;
  
  private long fanTicketCount_;
  
  public static final int GROUPCOUNT_FIELD_NUMBER = 4;
  
  private long groupCount_;
  
  public static final int REPEATCOUNT_FIELD_NUMBER = 5;
  
  private long repeatCount_;
  
  public static final int COMBOCOUNT_FIELD_NUMBER = 6;
  
  private long comboCount_;
  
  public static final int USER_FIELD_NUMBER = 7;
  
  private User user_;
  
  public static final int TOUSER_FIELD_NUMBER = 8;
  
  private User toUser_;
  
  public static final int REPEATEND_FIELD_NUMBER = 9;
  
  private int repeatEnd_;
  
  public static final int GROUPID_FIELD_NUMBER = 11;
  
  private long groupId_;
  
  public static final int ROOMFANTICKETCOUNT_FIELD_NUMBER = 13;
  
  private long roomFanTicketCount_;
  
  public static final int GIFT_FIELD_NUMBER = 15;
  
  private GiftStruct gift_;
  
  public static final int LOGID_FIELD_NUMBER = 16;
  
  private volatile Object logId_;
  
  public static final int SENDTYPE_FIELD_NUMBER = 17;
  
  private long sendType_;
  
  public static final int TRAYDISPLAYTEXT_FIELD_NUMBER = 19;
  
  private Text trayDisplayText_;
  
  public static final int FORCEDISPLAYEFFECTS_FIELD_NUMBER = 34;
  
  private long forceDisplayEffects_;
  
  private byte memoizedIsInitialized;
  
  private GiftMessage(GeneratedMessageV3.Builder<?> builder) {
    super(builder);
    this.giftId_ = 0L;
    this.fanTicketCount_ = 0L;
    this.groupCount_ = 0L;
    this.repeatCount_ = 0L;
    this.comboCount_ = 0L;
    this.repeatEnd_ = 0;
    this.groupId_ = 0L;
    this.roomFanTicketCount_ = 0L;
    this.logId_ = "";
    this.sendType_ = 0L;
    this.forceDisplayEffects_ = 0L;
    this.memoizedIsInitialized = -1;
  }
  
  private GiftMessage() {
    this.giftId_ = 0L;
    this.fanTicketCount_ = 0L;
    this.groupCount_ = 0L;
    this.repeatCount_ = 0L;
    this.comboCount_ = 0L;
    this.repeatEnd_ = 0;
    this.groupId_ = 0L;
    this.roomFanTicketCount_ = 0L;
    this.logId_ = "";
    this.sendType_ = 0L;
    this.forceDisplayEffects_ = 0L;
    this.memoizedIsInitialized = -1;
    this.logId_ = "";
  }
  
  protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
    return new GiftMessage();
  }
  
  public static final Descriptors.Descriptor getDescriptor() {
    return Tiktok.internal_static_GiftMessage_descriptor;
  }
  
  protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
    return Tiktok.internal_static_GiftMessage_fieldAccessorTable.ensureFieldAccessorsInitialized(GiftMessage.class, Builder.class);
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
  
  public long getGiftId() {
    return this.giftId_;
  }
  
  public long getFanTicketCount() {
    return this.fanTicketCount_;
  }
  
  public long getGroupCount() {
    return this.groupCount_;
  }
  
  public long getRepeatCount() {
    return this.repeatCount_;
  }
  
  public long getComboCount() {
    return this.comboCount_;
  }
  
  public boolean hasUser() {
    return ((this.bitField0_ & 0x2) != 0);
  }
  
  public User getUser() {
    return (this.user_ == null) ? User.getDefaultInstance() : this.user_;
  }
  
  public UserOrBuilder getUserOrBuilder() {
    return (this.user_ == null) ? User.getDefaultInstance() : this.user_;
  }
  
  public boolean hasToUser() {
    return ((this.bitField0_ & 0x4) != 0);
  }
  
  public User getToUser() {
    return (this.toUser_ == null) ? User.getDefaultInstance() : this.toUser_;
  }
  
  public UserOrBuilder getToUserOrBuilder() {
    return (this.toUser_ == null) ? User.getDefaultInstance() : this.toUser_;
  }
  
  public int getRepeatEnd() {
    return this.repeatEnd_;
  }
  
  public long getGroupId() {
    return this.groupId_;
  }
  
  public long getRoomFanTicketCount() {
    return this.roomFanTicketCount_;
  }
  
  public boolean hasGift() {
    return ((this.bitField0_ & 0x8) != 0);
  }
  
  public GiftStruct getGift() {
    return (this.gift_ == null) ? GiftStruct.getDefaultInstance() : this.gift_;
  }
  
  public GiftStructOrBuilder getGiftOrBuilder() {
    return (this.gift_ == null) ? GiftStruct.getDefaultInstance() : this.gift_;
  }
  
  public String getLogId() {
    Object ref = this.logId_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.logId_ = s;
    return s;
  }
  
  public ByteString getLogIdBytes() {
    Object ref = this.logId_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.logId_ = b;
      return b;
    } 
    return (ByteString)ref;
  }
  
  public long getSendType() {
    return this.sendType_;
  }
  
  public boolean hasTrayDisplayText() {
    return ((this.bitField0_ & 0x10) != 0);
  }
  
  public Text getTrayDisplayText() {
    return (this.trayDisplayText_ == null) ? Text.getDefaultInstance() : this.trayDisplayText_;
  }
  
  public TextOrBuilder getTrayDisplayTextOrBuilder() {
    return (this.trayDisplayText_ == null) ? Text.getDefaultInstance() : this.trayDisplayText_;
  }
  
  public long getForceDisplayEffects() {
    return this.forceDisplayEffects_;
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
    if (this.giftId_ != 0L)
      output.writeUInt64(2, this.giftId_); 
    if (this.fanTicketCount_ != 0L)
      output.writeUInt64(3, this.fanTicketCount_); 
    if (this.groupCount_ != 0L)
      output.writeUInt64(4, this.groupCount_); 
    if (this.repeatCount_ != 0L)
      output.writeUInt64(5, this.repeatCount_); 
    if (this.comboCount_ != 0L)
      output.writeUInt64(6, this.comboCount_); 
    if ((this.bitField0_ & 0x2) != 0)
      output.writeMessage(7, (MessageLite)getUser()); 
    if ((this.bitField0_ & 0x4) != 0)
      output.writeMessage(8, (MessageLite)getToUser()); 
    if (this.repeatEnd_ != 0)
      output.writeUInt32(9, this.repeatEnd_); 
    if (this.groupId_ != 0L)
      output.writeUInt64(11, this.groupId_); 
    if (this.roomFanTicketCount_ != 0L)
      output.writeUInt64(13, this.roomFanTicketCount_); 
    if ((this.bitField0_ & 0x8) != 0)
      output.writeMessage(15, (MessageLite)getGift()); 
    if (!GeneratedMessageV3.isStringEmpty(this.logId_))
      GeneratedMessageV3.writeString(output, 16, this.logId_); 
    if (this.sendType_ != 0L)
      output.writeUInt64(17, this.sendType_); 
    if ((this.bitField0_ & 0x10) != 0)
      output.writeMessage(19, (MessageLite)getTrayDisplayText()); 
    if (this.forceDisplayEffects_ != 0L)
      output.writeUInt64(34, this.forceDisplayEffects_); 
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
    if (this.giftId_ != 0L)
      size += 
        CodedOutputStream.computeUInt64Size(2, this.giftId_); 
    if (this.fanTicketCount_ != 0L)
      size += 
        CodedOutputStream.computeUInt64Size(3, this.fanTicketCount_); 
    if (this.groupCount_ != 0L)
      size += 
        CodedOutputStream.computeUInt64Size(4, this.groupCount_); 
    if (this.repeatCount_ != 0L)
      size += 
        CodedOutputStream.computeUInt64Size(5, this.repeatCount_); 
    if (this.comboCount_ != 0L)
      size += 
        CodedOutputStream.computeUInt64Size(6, this.comboCount_); 
    if ((this.bitField0_ & 0x2) != 0)
      size += 
        CodedOutputStream.computeMessageSize(7, (MessageLite)getUser()); 
    if ((this.bitField0_ & 0x4) != 0)
      size += 
        CodedOutputStream.computeMessageSize(8, (MessageLite)getToUser()); 
    if (this.repeatEnd_ != 0)
      size += 
        CodedOutputStream.computeUInt32Size(9, this.repeatEnd_); 
    if (this.groupId_ != 0L)
      size += 
        CodedOutputStream.computeUInt64Size(11, this.groupId_); 
    if (this.roomFanTicketCount_ != 0L)
      size += 
        CodedOutputStream.computeUInt64Size(13, this.roomFanTicketCount_); 
    if ((this.bitField0_ & 0x8) != 0)
      size += 
        CodedOutputStream.computeMessageSize(15, (MessageLite)getGift()); 
    if (!GeneratedMessageV3.isStringEmpty(this.logId_))
      size += GeneratedMessageV3.computeStringSize(16, this.logId_); 
    if (this.sendType_ != 0L)
      size += 
        CodedOutputStream.computeUInt64Size(17, this.sendType_); 
    if ((this.bitField0_ & 0x10) != 0)
      size += 
        CodedOutputStream.computeMessageSize(19, (MessageLite)getTrayDisplayText()); 
    if (this.forceDisplayEffects_ != 0L)
      size += 
        CodedOutputStream.computeUInt64Size(34, this.forceDisplayEffects_); 
    size += getUnknownFields().getSerializedSize();
    this.memoizedSize = size;
    return size;
  }
  
  public boolean equals(Object obj) {
    if (obj == this)
      return true; 
    if (!(obj instanceof GiftMessage))
      return super.equals(obj); 
    GiftMessage other = (GiftMessage)obj;
    if (hasCommon() != other.hasCommon())
      return false; 
    if (hasCommon() && 
      
      !getCommon().equals(other.getCommon()))
      return false; 
    if (getGiftId() != other
      .getGiftId())
      return false; 
    if (getFanTicketCount() != other
      .getFanTicketCount())
      return false; 
    if (getGroupCount() != other
      .getGroupCount())
      return false; 
    if (getRepeatCount() != other
      .getRepeatCount())
      return false; 
    if (getComboCount() != other
      .getComboCount())
      return false; 
    if (hasUser() != other.hasUser())
      return false; 
    if (hasUser() && 
      
      !getUser().equals(other.getUser()))
      return false; 
    if (hasToUser() != other.hasToUser())
      return false; 
    if (hasToUser() && 
      
      !getToUser().equals(other.getToUser()))
      return false; 
    if (getRepeatEnd() != other
      .getRepeatEnd())
      return false; 
    if (getGroupId() != other
      .getGroupId())
      return false; 
    if (getRoomFanTicketCount() != other
      .getRoomFanTicketCount())
      return false; 
    if (hasGift() != other.hasGift())
      return false; 
    if (hasGift() && 
      
      !getGift().equals(other.getGift()))
      return false; 
    if (!getLogId().equals(other.getLogId()))
      return false; 
    if (getSendType() != other
      .getSendType())
      return false; 
    if (hasTrayDisplayText() != other.hasTrayDisplayText())
      return false; 
    if (hasTrayDisplayText() && 
      
      !getTrayDisplayText().equals(other.getTrayDisplayText()))
      return false; 
    if (getForceDisplayEffects() != other
      .getForceDisplayEffects())
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
    hash = 53 * hash + Internal.hashLong(
        getGiftId());
    hash = 37 * hash + 3;
    hash = 53 * hash + Internal.hashLong(
        getFanTicketCount());
    hash = 37 * hash + 4;
    hash = 53 * hash + Internal.hashLong(
        getGroupCount());
    hash = 37 * hash + 5;
    hash = 53 * hash + Internal.hashLong(
        getRepeatCount());
    hash = 37 * hash + 6;
    hash = 53 * hash + Internal.hashLong(
        getComboCount());
    if (hasUser()) {
      hash = 37 * hash + 7;
      hash = 53 * hash + getUser().hashCode();
    } 
    if (hasToUser()) {
      hash = 37 * hash + 8;
      hash = 53 * hash + getToUser().hashCode();
    } 
    hash = 37 * hash + 9;
    hash = 53 * hash + getRepeatEnd();
    hash = 37 * hash + 11;
    hash = 53 * hash + Internal.hashLong(
        getGroupId());
    hash = 37 * hash + 13;
    hash = 53 * hash + Internal.hashLong(
        getRoomFanTicketCount());
    if (hasGift()) {
      hash = 37 * hash + 15;
      hash = 53 * hash + getGift().hashCode();
    } 
    hash = 37 * hash + 16;
    hash = 53 * hash + getLogId().hashCode();
    hash = 37 * hash + 17;
    hash = 53 * hash + Internal.hashLong(
        getSendType());
    if (hasTrayDisplayText()) {
      hash = 37 * hash + 19;
      hash = 53 * hash + getTrayDisplayText().hashCode();
    } 
    hash = 37 * hash + 34;
    hash = 53 * hash + Internal.hashLong(
        getForceDisplayEffects());
    hash = 29 * hash + getUnknownFields().hashCode();
    this.memoizedHashCode = hash;
    return hash;
  }
  
  public static GiftMessage parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
    return (GiftMessage)PARSER.parseFrom(data);
  }
  
  public static GiftMessage parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (GiftMessage)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static GiftMessage parseFrom(ByteString data) throws InvalidProtocolBufferException {
    return (GiftMessage)PARSER.parseFrom(data);
  }
  
  public static GiftMessage parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (GiftMessage)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static GiftMessage parseFrom(byte[] data) throws InvalidProtocolBufferException {
    return (GiftMessage)PARSER.parseFrom(data);
  }
  
  public static GiftMessage parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (GiftMessage)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static GiftMessage parseFrom(InputStream input) throws IOException {
    return 
      (GiftMessage)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static GiftMessage parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (GiftMessage)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static GiftMessage parseDelimitedFrom(InputStream input) throws IOException {
    return 
      (GiftMessage)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
  }
  
  public static GiftMessage parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (GiftMessage)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static GiftMessage parseFrom(CodedInputStream input) throws IOException {
    return 
      (GiftMessage)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static GiftMessage parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (GiftMessage)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public Builder newBuilderForType() {
    return newBuilder();
  }
  
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  
  public static Builder newBuilder(GiftMessage prototype) {
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
  
  public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements GiftMessageOrBuilder {
    private int bitField0_;
    
    private Common common_;
    
    private SingleFieldBuilderV3<Common, Common.Builder, CommonOrBuilder> commonBuilder_;
    
    private long giftId_;
    
    private long fanTicketCount_;
    
    private long groupCount_;
    
    private long repeatCount_;
    
    private long comboCount_;
    
    private User user_;
    
    private SingleFieldBuilderV3<User, User.Builder, UserOrBuilder> userBuilder_;
    
    private User toUser_;
    
    private SingleFieldBuilderV3<User, User.Builder, UserOrBuilder> toUserBuilder_;
    
    private int repeatEnd_;
    
    private long groupId_;
    
    private long roomFanTicketCount_;
    
    private GiftStruct gift_;
    
    private SingleFieldBuilderV3<GiftStruct, GiftStruct.Builder, GiftStructOrBuilder> giftBuilder_;
    
    private Object logId_;
    
    private long sendType_;
    
    private Text trayDisplayText_;
    
    private SingleFieldBuilderV3<Text, Text.Builder, TextOrBuilder> trayDisplayTextBuilder_;
    
    private long forceDisplayEffects_;
    
    public static final Descriptors.Descriptor getDescriptor() {
      return Tiktok.internal_static_GiftMessage_descriptor;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return Tiktok.internal_static_GiftMessage_fieldAccessorTable
        .ensureFieldAccessorsInitialized(GiftMessage.class, Builder.class);
    }
    
    private Builder() {
      this.logId_ = "";
      maybeForceBuilderInitialization();
    }
    
    private Builder(GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      this.logId_ = "";
      maybeForceBuilderInitialization();
    }
    
    private void maybeForceBuilderInitialization() {
      if (GiftMessage.alwaysUseFieldBuilders) {
        getCommonFieldBuilder();
        getUserFieldBuilder();
        getToUserFieldBuilder();
        getGiftFieldBuilder();
        getTrayDisplayTextFieldBuilder();
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
      this.giftId_ = 0L;
      this.fanTicketCount_ = 0L;
      this.groupCount_ = 0L;
      this.repeatCount_ = 0L;
      this.comboCount_ = 0L;
      this.user_ = null;
      if (this.userBuilder_ != null) {
        this.userBuilder_.dispose();
        this.userBuilder_ = null;
      } 
      this.toUser_ = null;
      if (this.toUserBuilder_ != null) {
        this.toUserBuilder_.dispose();
        this.toUserBuilder_ = null;
      } 
      this.repeatEnd_ = 0;
      this.groupId_ = 0L;
      this.roomFanTicketCount_ = 0L;
      this.gift_ = null;
      if (this.giftBuilder_ != null) {
        this.giftBuilder_.dispose();
        this.giftBuilder_ = null;
      } 
      this.logId_ = "";
      this.sendType_ = 0L;
      this.trayDisplayText_ = null;
      if (this.trayDisplayTextBuilder_ != null) {
        this.trayDisplayTextBuilder_.dispose();
        this.trayDisplayTextBuilder_ = null;
      } 
      this.forceDisplayEffects_ = 0L;
      return this;
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
      return Tiktok.internal_static_GiftMessage_descriptor;
    }
    
    public GiftMessage getDefaultInstanceForType() {
      return GiftMessage.getDefaultInstance();
    }
    
    public GiftMessage build() {
      GiftMessage result = buildPartial();
      if (!result.isInitialized())
        throw newUninitializedMessageException(result); 
      return result;
    }
    
    public GiftMessage buildPartial() {
      GiftMessage result = new GiftMessage(this);
      if (this.bitField0_ != 0)
        buildPartial0(result); 
      onBuilt();
      return result;
    }
    
    private void buildPartial0(GiftMessage result) {
      int from_bitField0_ = this.bitField0_;
      int to_bitField0_ = 0;
      if ((from_bitField0_ & 0x1) != 0) {
        result.common_ = (this.commonBuilder_ == null) ? this.common_ : (Common)this.commonBuilder_.build();
        to_bitField0_ |= 0x1;
      } 
      if ((from_bitField0_ & 0x2) != 0)
        result.giftId_ = this.giftId_; 
      if ((from_bitField0_ & 0x4) != 0)
        result.fanTicketCount_ = this.fanTicketCount_; 
      if ((from_bitField0_ & 0x8) != 0)
        result.groupCount_ = this.groupCount_; 
      if ((from_bitField0_ & 0x10) != 0)
        result.repeatCount_ = this.repeatCount_; 
      if ((from_bitField0_ & 0x20) != 0)
        result.comboCount_ = this.comboCount_; 
      if ((from_bitField0_ & 0x40) != 0) {
        result.user_ = (this.userBuilder_ == null) ? this.user_ : (User)this.userBuilder_.build();
        to_bitField0_ |= 0x2;
      } 
      if ((from_bitField0_ & 0x80) != 0) {
        result.toUser_ = (this.toUserBuilder_ == null) ? this.toUser_ : (User)this.toUserBuilder_.build();
        to_bitField0_ |= 0x4;
      } 
      if ((from_bitField0_ & 0x100) != 0)
        result.repeatEnd_ = this.repeatEnd_; 
      if ((from_bitField0_ & 0x200) != 0)
        result.groupId_ = this.groupId_; 
      if ((from_bitField0_ & 0x400) != 0)
        result.roomFanTicketCount_ = this.roomFanTicketCount_; 
      if ((from_bitField0_ & 0x800) != 0) {
        result.gift_ = (this.giftBuilder_ == null) ? this.gift_ : (GiftStruct)this.giftBuilder_.build();
        to_bitField0_ |= 0x8;
      } 
      if ((from_bitField0_ & 0x1000) != 0)
        result.logId_ = this.logId_; 
      if ((from_bitField0_ & 0x2000) != 0)
        result.sendType_ = this.sendType_; 
      if ((from_bitField0_ & 0x4000) != 0) {
        result.trayDisplayText_ = (this.trayDisplayTextBuilder_ == null) ? this.trayDisplayText_ : (Text)this.trayDisplayTextBuilder_.build();
        to_bitField0_ |= 0x10;
      } 
      if ((from_bitField0_ & 0x8000) != 0)
        result.forceDisplayEffects_ = this.forceDisplayEffects_; 
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
      if (other instanceof GiftMessage)
        return mergeFrom((GiftMessage)other); 
      super.mergeFrom(other);
      return this;
    }
    
    public Builder mergeFrom(GiftMessage other) {
      if (other == GiftMessage.getDefaultInstance())
        return this; 
      if (other.hasCommon())
        mergeCommon(other.getCommon()); 
      if (other.getGiftId() != 0L)
        setGiftId(other.getGiftId()); 
      if (other.getFanTicketCount() != 0L)
        setFanTicketCount(other.getFanTicketCount()); 
      if (other.getGroupCount() != 0L)
        setGroupCount(other.getGroupCount()); 
      if (other.getRepeatCount() != 0L)
        setRepeatCount(other.getRepeatCount()); 
      if (other.getComboCount() != 0L)
        setComboCount(other.getComboCount()); 
      if (other.hasUser())
        mergeUser(other.getUser()); 
      if (other.hasToUser())
        mergeToUser(other.getToUser()); 
      if (other.getRepeatEnd() != 0)
        setRepeatEnd(other.getRepeatEnd()); 
      if (other.getGroupId() != 0L)
        setGroupId(other.getGroupId()); 
      if (other.getRoomFanTicketCount() != 0L)
        setRoomFanTicketCount(other.getRoomFanTicketCount()); 
      if (other.hasGift())
        mergeGift(other.getGift()); 
      if (!other.getLogId().isEmpty()) {
        this.logId_ = other.logId_;
        this.bitField0_ |= 0x1000;
        onChanged();
      } 
      if (other.getSendType() != 0L)
        setSendType(other.getSendType()); 
      if (other.hasTrayDisplayText())
        mergeTrayDisplayText(other.getTrayDisplayText()); 
      if (other.getForceDisplayEffects() != 0L)
        setForceDisplayEffects(other.getForceDisplayEffects()); 
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
            case 16:
              this.giftId_ = input.readUInt64();
              this.bitField0_ |= 0x2;
              continue;
            case 24:
              this.fanTicketCount_ = input.readUInt64();
              this.bitField0_ |= 0x4;
              continue;
            case 32:
              this.groupCount_ = input.readUInt64();
              this.bitField0_ |= 0x8;
              continue;
            case 40:
              this.repeatCount_ = input.readUInt64();
              this.bitField0_ |= 0x10;
              continue;
            case 48:
              this.comboCount_ = input.readUInt64();
              this.bitField0_ |= 0x20;
              continue;
            case 58:
              input.readMessage((MessageLite.Builder)getUserFieldBuilder().getBuilder(), extensionRegistry);
              this.bitField0_ |= 0x40;
              continue;
            case 66:
              input.readMessage((MessageLite.Builder)getToUserFieldBuilder().getBuilder(), extensionRegistry);
              this.bitField0_ |= 0x80;
              continue;
            case 72:
              this.repeatEnd_ = input.readUInt32();
              this.bitField0_ |= 0x100;
              continue;
            case 88:
              this.groupId_ = input.readUInt64();
              this.bitField0_ |= 0x200;
              continue;
            case 104:
              this.roomFanTicketCount_ = input.readUInt64();
              this.bitField0_ |= 0x400;
              continue;
            case 122:
              input.readMessage((MessageLite.Builder)getGiftFieldBuilder().getBuilder(), extensionRegistry);
              this.bitField0_ |= 0x800;
              continue;
            case 130:
              this.logId_ = input.readStringRequireUtf8();
              this.bitField0_ |= 0x1000;
              continue;
            case 136:
              this.sendType_ = input.readUInt64();
              this.bitField0_ |= 0x2000;
              continue;
            case 154:
              input.readMessage((MessageLite.Builder)getTrayDisplayTextFieldBuilder().getBuilder(), extensionRegistry);
              this.bitField0_ |= 0x4000;
              continue;
            case 272:
              this.forceDisplayEffects_ = input.readUInt64();
              this.bitField0_ |= 0x8000;
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
    
    public long getGiftId() {
      return this.giftId_;
    }
    
    public Builder setGiftId(long value) {
      this.giftId_ = value;
      this.bitField0_ |= 0x2;
      onChanged();
      return this;
    }
    
    public Builder clearGiftId() {
      this.bitField0_ &= 0xFFFFFFFD;
      this.giftId_ = 0L;
      onChanged();
      return this;
    }
    
    public long getFanTicketCount() {
      return this.fanTicketCount_;
    }
    
    public Builder setFanTicketCount(long value) {
      this.fanTicketCount_ = value;
      this.bitField0_ |= 0x4;
      onChanged();
      return this;
    }
    
    public Builder clearFanTicketCount() {
      this.bitField0_ &= 0xFFFFFFFB;
      this.fanTicketCount_ = 0L;
      onChanged();
      return this;
    }
    
    public long getGroupCount() {
      return this.groupCount_;
    }
    
    public Builder setGroupCount(long value) {
      this.groupCount_ = value;
      this.bitField0_ |= 0x8;
      onChanged();
      return this;
    }
    
    public Builder clearGroupCount() {
      this.bitField0_ &= 0xFFFFFFF7;
      this.groupCount_ = 0L;
      onChanged();
      return this;
    }
    
    public long getRepeatCount() {
      return this.repeatCount_;
    }
    
    public Builder setRepeatCount(long value) {
      this.repeatCount_ = value;
      this.bitField0_ |= 0x10;
      onChanged();
      return this;
    }
    
    public Builder clearRepeatCount() {
      this.bitField0_ &= 0xFFFFFFEF;
      this.repeatCount_ = 0L;
      onChanged();
      return this;
    }
    
    public long getComboCount() {
      return this.comboCount_;
    }
    
    public Builder setComboCount(long value) {
      this.comboCount_ = value;
      this.bitField0_ |= 0x20;
      onChanged();
      return this;
    }
    
    public Builder clearComboCount() {
      this.bitField0_ &= 0xFFFFFFDF;
      this.comboCount_ = 0L;
      onChanged();
      return this;
    }
    
    public boolean hasUser() {
      return ((this.bitField0_ & 0x40) != 0);
    }
    
    public User getUser() {
      if (this.userBuilder_ == null)
        return (this.user_ == null) ? User.getDefaultInstance() : this.user_; 
      return (User)this.userBuilder_.getMessage();
    }
    
    public Builder setUser(User value) {
      if (this.userBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        this.user_ = value;
      } else {
        this.userBuilder_.setMessage((AbstractMessage)value);
      } 
      this.bitField0_ |= 0x40;
      onChanged();
      return this;
    }
    
    public Builder setUser(User.Builder builderForValue) {
      if (this.userBuilder_ == null) {
        this.user_ = builderForValue.build();
      } else {
        this.userBuilder_.setMessage((AbstractMessage)builderForValue.build());
      } 
      this.bitField0_ |= 0x40;
      onChanged();
      return this;
    }
    
    public Builder mergeUser(User value) {
      if (this.userBuilder_ == null) {
        if ((this.bitField0_ & 0x40) != 0 && this.user_ != null && this.user_ != User.getDefaultInstance()) {
          getUserBuilder().mergeFrom(value);
        } else {
          this.user_ = value;
        } 
      } else {
        this.userBuilder_.mergeFrom((AbstractMessage)value);
      } 
      if (this.user_ != null) {
        this.bitField0_ |= 0x40;
        onChanged();
      } 
      return this;
    }
    
    public Builder clearUser() {
      this.bitField0_ &= 0xFFFFFFBF;
      this.user_ = null;
      if (this.userBuilder_ != null) {
        this.userBuilder_.dispose();
        this.userBuilder_ = null;
      } 
      onChanged();
      return this;
    }
    
    public User.Builder getUserBuilder() {
      this.bitField0_ |= 0x40;
      onChanged();
      return (User.Builder)getUserFieldBuilder().getBuilder();
    }
    
    public UserOrBuilder getUserOrBuilder() {
      if (this.userBuilder_ != null)
        return (UserOrBuilder)this.userBuilder_.getMessageOrBuilder(); 
      return (this.user_ == null) ? User.getDefaultInstance() : this.user_;
    }
    
    private SingleFieldBuilderV3<User, User.Builder, UserOrBuilder> getUserFieldBuilder() {
      if (this.userBuilder_ == null) {
        this.userBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getUser(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.user_ = null;
      } 
      return this.userBuilder_;
    }
    
    public boolean hasToUser() {
      return ((this.bitField0_ & 0x80) != 0);
    }
    
    public User getToUser() {
      if (this.toUserBuilder_ == null)
        return (this.toUser_ == null) ? User.getDefaultInstance() : this.toUser_; 
      return (User)this.toUserBuilder_.getMessage();
    }
    
    public Builder setToUser(User value) {
      if (this.toUserBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        this.toUser_ = value;
      } else {
        this.toUserBuilder_.setMessage((AbstractMessage)value);
      } 
      this.bitField0_ |= 0x80;
      onChanged();
      return this;
    }
    
    public Builder setToUser(User.Builder builderForValue) {
      if (this.toUserBuilder_ == null) {
        this.toUser_ = builderForValue.build();
      } else {
        this.toUserBuilder_.setMessage((AbstractMessage)builderForValue.build());
      } 
      this.bitField0_ |= 0x80;
      onChanged();
      return this;
    }
    
    public Builder mergeToUser(User value) {
      if (this.toUserBuilder_ == null) {
        if ((this.bitField0_ & 0x80) != 0 && this.toUser_ != null && this.toUser_ != User.getDefaultInstance()) {
          getToUserBuilder().mergeFrom(value);
        } else {
          this.toUser_ = value;
        } 
      } else {
        this.toUserBuilder_.mergeFrom((AbstractMessage)value);
      } 
      if (this.toUser_ != null) {
        this.bitField0_ |= 0x80;
        onChanged();
      } 
      return this;
    }
    
    public Builder clearToUser() {
      this.bitField0_ &= 0xFFFFFF7F;
      this.toUser_ = null;
      if (this.toUserBuilder_ != null) {
        this.toUserBuilder_.dispose();
        this.toUserBuilder_ = null;
      } 
      onChanged();
      return this;
    }
    
    public User.Builder getToUserBuilder() {
      this.bitField0_ |= 0x80;
      onChanged();
      return (User.Builder)getToUserFieldBuilder().getBuilder();
    }
    
    public UserOrBuilder getToUserOrBuilder() {
      if (this.toUserBuilder_ != null)
        return (UserOrBuilder)this.toUserBuilder_.getMessageOrBuilder(); 
      return (this.toUser_ == null) ? User.getDefaultInstance() : this.toUser_;
    }
    
    private SingleFieldBuilderV3<User, User.Builder, UserOrBuilder> getToUserFieldBuilder() {
      if (this.toUserBuilder_ == null) {
        this.toUserBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getToUser(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.toUser_ = null;
      } 
      return this.toUserBuilder_;
    }
    
    public int getRepeatEnd() {
      return this.repeatEnd_;
    }
    
    public Builder setRepeatEnd(int value) {
      this.repeatEnd_ = value;
      this.bitField0_ |= 0x100;
      onChanged();
      return this;
    }
    
    public Builder clearRepeatEnd() {
      this.bitField0_ &= 0xFFFFFEFF;
      this.repeatEnd_ = 0;
      onChanged();
      return this;
    }
    
    public long getGroupId() {
      return this.groupId_;
    }
    
    public Builder setGroupId(long value) {
      this.groupId_ = value;
      this.bitField0_ |= 0x200;
      onChanged();
      return this;
    }
    
    public Builder clearGroupId() {
      this.bitField0_ &= 0xFFFFFDFF;
      this.groupId_ = 0L;
      onChanged();
      return this;
    }
    
    public long getRoomFanTicketCount() {
      return this.roomFanTicketCount_;
    }
    
    public Builder setRoomFanTicketCount(long value) {
      this.roomFanTicketCount_ = value;
      this.bitField0_ |= 0x400;
      onChanged();
      return this;
    }
    
    public Builder clearRoomFanTicketCount() {
      this.bitField0_ &= 0xFFFFFBFF;
      this.roomFanTicketCount_ = 0L;
      onChanged();
      return this;
    }
    
    public boolean hasGift() {
      return ((this.bitField0_ & 0x800) != 0);
    }
    
    public GiftStruct getGift() {
      if (this.giftBuilder_ == null)
        return (this.gift_ == null) ? GiftStruct.getDefaultInstance() : this.gift_; 
      return (GiftStruct)this.giftBuilder_.getMessage();
    }
    
    public Builder setGift(GiftStruct value) {
      if (this.giftBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        this.gift_ = value;
      } else {
        this.giftBuilder_.setMessage((AbstractMessage)value);
      } 
      this.bitField0_ |= 0x800;
      onChanged();
      return this;
    }
    
    public Builder setGift(GiftStruct.Builder builderForValue) {
      if (this.giftBuilder_ == null) {
        this.gift_ = builderForValue.build();
      } else {
        this.giftBuilder_.setMessage((AbstractMessage)builderForValue.build());
      } 
      this.bitField0_ |= 0x800;
      onChanged();
      return this;
    }
    
    public Builder mergeGift(GiftStruct value) {
      if (this.giftBuilder_ == null) {
        if ((this.bitField0_ & 0x800) != 0 && this.gift_ != null && this.gift_ != GiftStruct.getDefaultInstance()) {
          getGiftBuilder().mergeFrom(value);
        } else {
          this.gift_ = value;
        } 
      } else {
        this.giftBuilder_.mergeFrom((AbstractMessage)value);
      } 
      if (this.gift_ != null) {
        this.bitField0_ |= 0x800;
        onChanged();
      } 
      return this;
    }
    
    public Builder clearGift() {
      this.bitField0_ &= 0xFFFFF7FF;
      this.gift_ = null;
      if (this.giftBuilder_ != null) {
        this.giftBuilder_.dispose();
        this.giftBuilder_ = null;
      } 
      onChanged();
      return this;
    }
    
    public GiftStruct.Builder getGiftBuilder() {
      this.bitField0_ |= 0x800;
      onChanged();
      return (GiftStruct.Builder)getGiftFieldBuilder().getBuilder();
    }
    
    public GiftStructOrBuilder getGiftOrBuilder() {
      if (this.giftBuilder_ != null)
        return (GiftStructOrBuilder)this.giftBuilder_.getMessageOrBuilder(); 
      return (this.gift_ == null) ? GiftStruct.getDefaultInstance() : this.gift_;
    }
    
    private SingleFieldBuilderV3<GiftStruct, GiftStruct.Builder, GiftStructOrBuilder> getGiftFieldBuilder() {
      if (this.giftBuilder_ == null) {
        this.giftBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getGift(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.gift_ = null;
      } 
      return this.giftBuilder_;
    }
    
    public String getLogId() {
      Object ref = this.logId_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.logId_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getLogIdBytes() {
      Object ref = this.logId_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.logId_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setLogId(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.logId_ = value;
      this.bitField0_ |= 0x1000;
      onChanged();
      return this;
    }
    
    public Builder clearLogId() {
      this.logId_ = GiftMessage.getDefaultInstance().getLogId();
      this.bitField0_ &= 0xFFFFEFFF;
      onChanged();
      return this;
    }
    
    public Builder setLogIdBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      GiftMessage.checkByteStringIsUtf8(value);
      this.logId_ = value;
      this.bitField0_ |= 0x1000;
      onChanged();
      return this;
    }
    
    public long getSendType() {
      return this.sendType_;
    }
    
    public Builder setSendType(long value) {
      this.sendType_ = value;
      this.bitField0_ |= 0x2000;
      onChanged();
      return this;
    }
    
    public Builder clearSendType() {
      this.bitField0_ &= 0xFFFFDFFF;
      this.sendType_ = 0L;
      onChanged();
      return this;
    }
    
    public boolean hasTrayDisplayText() {
      return ((this.bitField0_ & 0x4000) != 0);
    }
    
    public Text getTrayDisplayText() {
      if (this.trayDisplayTextBuilder_ == null)
        return (this.trayDisplayText_ == null) ? Text.getDefaultInstance() : this.trayDisplayText_; 
      return (Text)this.trayDisplayTextBuilder_.getMessage();
    }
    
    public Builder setTrayDisplayText(Text value) {
      if (this.trayDisplayTextBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        this.trayDisplayText_ = value;
      } else {
        this.trayDisplayTextBuilder_.setMessage((AbstractMessage)value);
      } 
      this.bitField0_ |= 0x4000;
      onChanged();
      return this;
    }
    
    public Builder setTrayDisplayText(Text.Builder builderForValue) {
      if (this.trayDisplayTextBuilder_ == null) {
        this.trayDisplayText_ = builderForValue.build();
      } else {
        this.trayDisplayTextBuilder_.setMessage((AbstractMessage)builderForValue.build());
      } 
      this.bitField0_ |= 0x4000;
      onChanged();
      return this;
    }
    
    public Builder mergeTrayDisplayText(Text value) {
      if (this.trayDisplayTextBuilder_ == null) {
        if ((this.bitField0_ & 0x4000) != 0 && this.trayDisplayText_ != null && this.trayDisplayText_ != 
          
          Text.getDefaultInstance()) {
          getTrayDisplayTextBuilder().mergeFrom(value);
        } else {
          this.trayDisplayText_ = value;
        } 
      } else {
        this.trayDisplayTextBuilder_.mergeFrom((AbstractMessage)value);
      } 
      if (this.trayDisplayText_ != null) {
        this.bitField0_ |= 0x4000;
        onChanged();
      } 
      return this;
    }
    
    public Builder clearTrayDisplayText() {
      this.bitField0_ &= 0xFFFFBFFF;
      this.trayDisplayText_ = null;
      if (this.trayDisplayTextBuilder_ != null) {
        this.trayDisplayTextBuilder_.dispose();
        this.trayDisplayTextBuilder_ = null;
      } 
      onChanged();
      return this;
    }
    
    public Text.Builder getTrayDisplayTextBuilder() {
      this.bitField0_ |= 0x4000;
      onChanged();
      return (Text.Builder)getTrayDisplayTextFieldBuilder().getBuilder();
    }
    
    public TextOrBuilder getTrayDisplayTextOrBuilder() {
      if (this.trayDisplayTextBuilder_ != null)
        return (TextOrBuilder)this.trayDisplayTextBuilder_.getMessageOrBuilder(); 
      return (this.trayDisplayText_ == null) ? 
        Text.getDefaultInstance() : this.trayDisplayText_;
    }
    
    private SingleFieldBuilderV3<Text, Text.Builder, TextOrBuilder> getTrayDisplayTextFieldBuilder() {
      if (this.trayDisplayTextBuilder_ == null) {
        this
          
          .trayDisplayTextBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getTrayDisplayText(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.trayDisplayText_ = null;
      } 
      return this.trayDisplayTextBuilder_;
    }
    
    public long getForceDisplayEffects() {
      return this.forceDisplayEffects_;
    }
    
    public Builder setForceDisplayEffects(long value) {
      this.forceDisplayEffects_ = value;
      this.bitField0_ |= 0x8000;
      onChanged();
      return this;
    }
    
    public Builder clearForceDisplayEffects() {
      this.bitField0_ &= 0xFFFF7FFF;
      this.forceDisplayEffects_ = 0L;
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
  
  private static final GiftMessage DEFAULT_INSTANCE = new GiftMessage();
  
  public static GiftMessage getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }
  
  private static final Parser<GiftMessage> PARSER = (Parser<GiftMessage>)new AbstractParser<GiftMessage>() {
      public GiftMessage parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        GiftMessage.Builder builder = GiftMessage.newBuilder();
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
  
  public static Parser<GiftMessage> parser() {
    return PARSER;
  }
  
  public Parser<GiftMessage> getParserForType() {
    return PARSER;
  }
  
  public GiftMessage getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }
}
