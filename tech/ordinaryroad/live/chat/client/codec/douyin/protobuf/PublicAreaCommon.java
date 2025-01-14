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

public final class PublicAreaCommon extends GeneratedMessageV3 implements PublicAreaCommonOrBuilder {
  private static final long serialVersionUID = 0L;
  
  private int bitField0_;
  
  public static final int USERLABEL_FIELD_NUMBER = 1;
  
  private Image userLabel_;
  
  public static final int USERCONSUMEINROOM_FIELD_NUMBER = 2;
  
  private long userConsumeInRoom_;
  
  public static final int USERSENDGIFTCNTINROOM_FIELD_NUMBER = 3;
  
  private long userSendGiftCntInRoom_;
  
  public static final int INDIVIDUAL_PRIORITY_FIELD_NUMBER = 4;
  
  private long individualPriority_;
  
  public static final int SUPPORT_PIN_FIELD_NUMBER = 6;
  
  private long supportPin_;
  
  public static final int SUFFIX_TEXT_FIELD_NUMBER = 7;
  
  private SuffixText suffixText_;
  
  public static final int IM_ACTION_FIELD_NUMBER = 8;
  
  private int imAction_;
  
  public static final int FORBIDDEN_PROFILE_FIELD_NUMBER = 9;
  
  private boolean forbiddenProfile_;
  
  public static final int REPLY_RESP_FIELD_NUMBER = 10;
  
  private ChatReplyRespInfo replyResp_;
  
  public static final int IS_FEATURED_FIELD_NUMBER = 12;
  
  private long isFeatured_;
  
  public static final int NEED_FILTER_DISPLAY_FIELD_NUMBER = 13;
  
  private boolean needFilterDisplay_;
  
  private byte memoizedIsInitialized;
  
  private PublicAreaCommon(GeneratedMessageV3.Builder<?> builder) {
    super(builder);
    this.userConsumeInRoom_ = 0L;
    this.userSendGiftCntInRoom_ = 0L;
    this.individualPriority_ = 0L;
    this.supportPin_ = 0L;
    this.imAction_ = 0;
    this.forbiddenProfile_ = false;
    this.isFeatured_ = 0L;
    this.needFilterDisplay_ = false;
    this.memoizedIsInitialized = -1;
  }
  
  private PublicAreaCommon() {
    this.userConsumeInRoom_ = 0L;
    this.userSendGiftCntInRoom_ = 0L;
    this.individualPriority_ = 0L;
    this.supportPin_ = 0L;
    this.imAction_ = 0;
    this.forbiddenProfile_ = false;
    this.isFeatured_ = 0L;
    this.needFilterDisplay_ = false;
    this.memoizedIsInitialized = -1;
  }
  
  protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
    return new PublicAreaCommon();
  }
  
  public static final Descriptors.Descriptor getDescriptor() {
    return Douyin.internal_static_PublicAreaCommon_descriptor;
  }
  
  protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
    return Douyin.internal_static_PublicAreaCommon_fieldAccessorTable.ensureFieldAccessorsInitialized(PublicAreaCommon.class, Builder.class);
  }
  
  public boolean hasUserLabel() {
    return ((this.bitField0_ & 0x1) != 0);
  }
  
  public Image getUserLabel() {
    return (this.userLabel_ == null) ? Image.getDefaultInstance() : this.userLabel_;
  }
  
  public ImageOrBuilder getUserLabelOrBuilder() {
    return (this.userLabel_ == null) ? Image.getDefaultInstance() : this.userLabel_;
  }
  
  public long getUserConsumeInRoom() {
    return this.userConsumeInRoom_;
  }
  
  public long getUserSendGiftCntInRoom() {
    return this.userSendGiftCntInRoom_;
  }
  
  public long getIndividualPriority() {
    return this.individualPriority_;
  }
  
  public long getSupportPin() {
    return this.supportPin_;
  }
  
  public boolean hasSuffixText() {
    return ((this.bitField0_ & 0x2) != 0);
  }
  
  public SuffixText getSuffixText() {
    return (this.suffixText_ == null) ? SuffixText.getDefaultInstance() : this.suffixText_;
  }
  
  public SuffixTextOrBuilder getSuffixTextOrBuilder() {
    return (this.suffixText_ == null) ? SuffixText.getDefaultInstance() : this.suffixText_;
  }
  
  public int getImAction() {
    return this.imAction_;
  }
  
  public boolean getForbiddenProfile() {
    return this.forbiddenProfile_;
  }
  
  public boolean hasReplyResp() {
    return ((this.bitField0_ & 0x4) != 0);
  }
  
  public ChatReplyRespInfo getReplyResp() {
    return (this.replyResp_ == null) ? ChatReplyRespInfo.getDefaultInstance() : this.replyResp_;
  }
  
  public ChatReplyRespInfoOrBuilder getReplyRespOrBuilder() {
    return (this.replyResp_ == null) ? ChatReplyRespInfo.getDefaultInstance() : this.replyResp_;
  }
  
  public long getIsFeatured() {
    return this.isFeatured_;
  }
  
  public boolean getNeedFilterDisplay() {
    return this.needFilterDisplay_;
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
      output.writeMessage(1, (MessageLite)getUserLabel()); 
    if (this.userConsumeInRoom_ != 0L)
      output.writeUInt64(2, this.userConsumeInRoom_); 
    if (this.userSendGiftCntInRoom_ != 0L)
      output.writeUInt64(3, this.userSendGiftCntInRoom_); 
    if (this.individualPriority_ != 0L)
      output.writeUInt64(4, this.individualPriority_); 
    if (this.supportPin_ != 0L)
      output.writeUInt64(6, this.supportPin_); 
    if ((this.bitField0_ & 0x2) != 0)
      output.writeMessage(7, (MessageLite)getSuffixText()); 
    if (this.imAction_ != 0)
      output.writeInt32(8, this.imAction_); 
    if (this.forbiddenProfile_)
      output.writeBool(9, this.forbiddenProfile_); 
    if ((this.bitField0_ & 0x4) != 0)
      output.writeMessage(10, (MessageLite)getReplyResp()); 
    if (this.isFeatured_ != 0L)
      output.writeUInt64(12, this.isFeatured_); 
    if (this.needFilterDisplay_)
      output.writeBool(13, this.needFilterDisplay_); 
    getUnknownFields().writeTo(output);
  }
  
  public int getSerializedSize() {
    int size = this.memoizedSize;
    if (size != -1)
      return size; 
    size = 0;
    if ((this.bitField0_ & 0x1) != 0)
      size += 
        CodedOutputStream.computeMessageSize(1, (MessageLite)getUserLabel()); 
    if (this.userConsumeInRoom_ != 0L)
      size += 
        CodedOutputStream.computeUInt64Size(2, this.userConsumeInRoom_); 
    if (this.userSendGiftCntInRoom_ != 0L)
      size += 
        CodedOutputStream.computeUInt64Size(3, this.userSendGiftCntInRoom_); 
    if (this.individualPriority_ != 0L)
      size += 
        CodedOutputStream.computeUInt64Size(4, this.individualPriority_); 
    if (this.supportPin_ != 0L)
      size += 
        CodedOutputStream.computeUInt64Size(6, this.supportPin_); 
    if ((this.bitField0_ & 0x2) != 0)
      size += 
        CodedOutputStream.computeMessageSize(7, (MessageLite)getSuffixText()); 
    if (this.imAction_ != 0)
      size += 
        CodedOutputStream.computeInt32Size(8, this.imAction_); 
    if (this.forbiddenProfile_)
      size += 
        CodedOutputStream.computeBoolSize(9, this.forbiddenProfile_); 
    if ((this.bitField0_ & 0x4) != 0)
      size += 
        CodedOutputStream.computeMessageSize(10, (MessageLite)getReplyResp()); 
    if (this.isFeatured_ != 0L)
      size += 
        CodedOutputStream.computeUInt64Size(12, this.isFeatured_); 
    if (this.needFilterDisplay_)
      size += 
        CodedOutputStream.computeBoolSize(13, this.needFilterDisplay_); 
    size += getUnknownFields().getSerializedSize();
    this.memoizedSize = size;
    return size;
  }
  
  public boolean equals(Object obj) {
    if (obj == this)
      return true; 
    if (!(obj instanceof PublicAreaCommon))
      return super.equals(obj); 
    PublicAreaCommon other = (PublicAreaCommon)obj;
    if (hasUserLabel() != other.hasUserLabel())
      return false; 
    if (hasUserLabel() && 
      
      !getUserLabel().equals(other.getUserLabel()))
      return false; 
    if (getUserConsumeInRoom() != other
      .getUserConsumeInRoom())
      return false; 
    if (getUserSendGiftCntInRoom() != other
      .getUserSendGiftCntInRoom())
      return false; 
    if (getIndividualPriority() != other
      .getIndividualPriority())
      return false; 
    if (getSupportPin() != other
      .getSupportPin())
      return false; 
    if (hasSuffixText() != other.hasSuffixText())
      return false; 
    if (hasSuffixText() && 
      
      !getSuffixText().equals(other.getSuffixText()))
      return false; 
    if (getImAction() != other
      .getImAction())
      return false; 
    if (getForbiddenProfile() != other
      .getForbiddenProfile())
      return false; 
    if (hasReplyResp() != other.hasReplyResp())
      return false; 
    if (hasReplyResp() && 
      
      !getReplyResp().equals(other.getReplyResp()))
      return false; 
    if (getIsFeatured() != other
      .getIsFeatured())
      return false; 
    if (getNeedFilterDisplay() != other
      .getNeedFilterDisplay())
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
    if (hasUserLabel()) {
      hash = 37 * hash + 1;
      hash = 53 * hash + getUserLabel().hashCode();
    } 
    hash = 37 * hash + 2;
    hash = 53 * hash + Internal.hashLong(
        getUserConsumeInRoom());
    hash = 37 * hash + 3;
    hash = 53 * hash + Internal.hashLong(
        getUserSendGiftCntInRoom());
    hash = 37 * hash + 4;
    hash = 53 * hash + Internal.hashLong(
        getIndividualPriority());
    hash = 37 * hash + 6;
    hash = 53 * hash + Internal.hashLong(
        getSupportPin());
    if (hasSuffixText()) {
      hash = 37 * hash + 7;
      hash = 53 * hash + getSuffixText().hashCode();
    } 
    hash = 37 * hash + 8;
    hash = 53 * hash + getImAction();
    hash = 37 * hash + 9;
    hash = 53 * hash + Internal.hashBoolean(
        getForbiddenProfile());
    if (hasReplyResp()) {
      hash = 37 * hash + 10;
      hash = 53 * hash + getReplyResp().hashCode();
    } 
    hash = 37 * hash + 12;
    hash = 53 * hash + Internal.hashLong(
        getIsFeatured());
    hash = 37 * hash + 13;
    hash = 53 * hash + Internal.hashBoolean(
        getNeedFilterDisplay());
    hash = 29 * hash + getUnknownFields().hashCode();
    this.memoizedHashCode = hash;
    return hash;
  }
  
  public static PublicAreaCommon parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
    return (PublicAreaCommon)PARSER.parseFrom(data);
  }
  
  public static PublicAreaCommon parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (PublicAreaCommon)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static PublicAreaCommon parseFrom(ByteString data) throws InvalidProtocolBufferException {
    return (PublicAreaCommon)PARSER.parseFrom(data);
  }
  
  public static PublicAreaCommon parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (PublicAreaCommon)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static PublicAreaCommon parseFrom(byte[] data) throws InvalidProtocolBufferException {
    return (PublicAreaCommon)PARSER.parseFrom(data);
  }
  
  public static PublicAreaCommon parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (PublicAreaCommon)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static PublicAreaCommon parseFrom(InputStream input) throws IOException {
    return 
      (PublicAreaCommon)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static PublicAreaCommon parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (PublicAreaCommon)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static PublicAreaCommon parseDelimitedFrom(InputStream input) throws IOException {
    return 
      (PublicAreaCommon)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
  }
  
  public static PublicAreaCommon parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (PublicAreaCommon)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static PublicAreaCommon parseFrom(CodedInputStream input) throws IOException {
    return 
      (PublicAreaCommon)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static PublicAreaCommon parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (PublicAreaCommon)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public Builder newBuilderForType() {
    return newBuilder();
  }
  
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  
  public static Builder newBuilder(PublicAreaCommon prototype) {
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
  
  public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements PublicAreaCommonOrBuilder {
    private int bitField0_;
    
    private Image userLabel_;
    
    private SingleFieldBuilderV3<Image, Image.Builder, ImageOrBuilder> userLabelBuilder_;
    
    private long userConsumeInRoom_;
    
    private long userSendGiftCntInRoom_;
    
    private long individualPriority_;
    
    private long supportPin_;
    
    private SuffixText suffixText_;
    
    private SingleFieldBuilderV3<SuffixText, SuffixText.Builder, SuffixTextOrBuilder> suffixTextBuilder_;
    
    private int imAction_;
    
    private boolean forbiddenProfile_;
    
    private ChatReplyRespInfo replyResp_;
    
    private SingleFieldBuilderV3<ChatReplyRespInfo, ChatReplyRespInfo.Builder, ChatReplyRespInfoOrBuilder> replyRespBuilder_;
    
    private long isFeatured_;
    
    private boolean needFilterDisplay_;
    
    public static final Descriptors.Descriptor getDescriptor() {
      return Douyin.internal_static_PublicAreaCommon_descriptor;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return Douyin.internal_static_PublicAreaCommon_fieldAccessorTable
        .ensureFieldAccessorsInitialized(PublicAreaCommon.class, Builder.class);
    }
    
    private Builder() {
      maybeForceBuilderInitialization();
    }
    
    private Builder(GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    
    private void maybeForceBuilderInitialization() {
      if (PublicAreaCommon.alwaysUseFieldBuilders) {
        getUserLabelFieldBuilder();
        getSuffixTextFieldBuilder();
        getReplyRespFieldBuilder();
      } 
    }
    
    public Builder clear() {
      super.clear();
      this.bitField0_ = 0;
      this.userLabel_ = null;
      if (this.userLabelBuilder_ != null) {
        this.userLabelBuilder_.dispose();
        this.userLabelBuilder_ = null;
      } 
      this.userConsumeInRoom_ = 0L;
      this.userSendGiftCntInRoom_ = 0L;
      this.individualPriority_ = 0L;
      this.supportPin_ = 0L;
      this.suffixText_ = null;
      if (this.suffixTextBuilder_ != null) {
        this.suffixTextBuilder_.dispose();
        this.suffixTextBuilder_ = null;
      } 
      this.imAction_ = 0;
      this.forbiddenProfile_ = false;
      this.replyResp_ = null;
      if (this.replyRespBuilder_ != null) {
        this.replyRespBuilder_.dispose();
        this.replyRespBuilder_ = null;
      } 
      this.isFeatured_ = 0L;
      this.needFilterDisplay_ = false;
      return this;
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
      return Douyin.internal_static_PublicAreaCommon_descriptor;
    }
    
    public PublicAreaCommon getDefaultInstanceForType() {
      return PublicAreaCommon.getDefaultInstance();
    }
    
    public PublicAreaCommon build() {
      PublicAreaCommon result = buildPartial();
      if (!result.isInitialized())
        throw newUninitializedMessageException(result); 
      return result;
    }
    
    public PublicAreaCommon buildPartial() {
      PublicAreaCommon result = new PublicAreaCommon(this);
      if (this.bitField0_ != 0)
        buildPartial0(result); 
      onBuilt();
      return result;
    }
    
    private void buildPartial0(PublicAreaCommon result) {
      int from_bitField0_ = this.bitField0_;
      int to_bitField0_ = 0;
      if ((from_bitField0_ & 0x1) != 0) {
        result.userLabel_ = (this.userLabelBuilder_ == null) ? 
          this.userLabel_ : 
          (Image)this.userLabelBuilder_.build();
        to_bitField0_ |= 0x1;
      } 
      if ((from_bitField0_ & 0x2) != 0)
        result.userConsumeInRoom_ = this.userConsumeInRoom_; 
      if ((from_bitField0_ & 0x4) != 0)
        result.userSendGiftCntInRoom_ = this.userSendGiftCntInRoom_; 
      if ((from_bitField0_ & 0x8) != 0)
        result.individualPriority_ = this.individualPriority_; 
      if ((from_bitField0_ & 0x10) != 0)
        result.supportPin_ = this.supportPin_; 
      if ((from_bitField0_ & 0x20) != 0) {
        result.suffixText_ = (this.suffixTextBuilder_ == null) ? 
          this.suffixText_ : 
          (SuffixText)this.suffixTextBuilder_.build();
        to_bitField0_ |= 0x2;
      } 
      if ((from_bitField0_ & 0x40) != 0)
        result.imAction_ = this.imAction_; 
      if ((from_bitField0_ & 0x80) != 0)
        result.forbiddenProfile_ = this.forbiddenProfile_; 
      if ((from_bitField0_ & 0x100) != 0) {
        result.replyResp_ = (this.replyRespBuilder_ == null) ? 
          this.replyResp_ : 
          (ChatReplyRespInfo)this.replyRespBuilder_.build();
        to_bitField0_ |= 0x4;
      } 
      if ((from_bitField0_ & 0x200) != 0)
        result.isFeatured_ = this.isFeatured_; 
      if ((from_bitField0_ & 0x400) != 0)
        result.needFilterDisplay_ = this.needFilterDisplay_; 
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
      if (other instanceof PublicAreaCommon)
        return mergeFrom((PublicAreaCommon)other); 
      super.mergeFrom(other);
      return this;
    }
    
    public Builder mergeFrom(PublicAreaCommon other) {
      if (other == PublicAreaCommon.getDefaultInstance())
        return this; 
      if (other.hasUserLabel())
        mergeUserLabel(other.getUserLabel()); 
      if (other.getUserConsumeInRoom() != 0L)
        setUserConsumeInRoom(other.getUserConsumeInRoom()); 
      if (other.getUserSendGiftCntInRoom() != 0L)
        setUserSendGiftCntInRoom(other.getUserSendGiftCntInRoom()); 
      if (other.getIndividualPriority() != 0L)
        setIndividualPriority(other.getIndividualPriority()); 
      if (other.getSupportPin() != 0L)
        setSupportPin(other.getSupportPin()); 
      if (other.hasSuffixText())
        mergeSuffixText(other.getSuffixText()); 
      if (other.getImAction() != 0)
        setImAction(other.getImAction()); 
      if (other.getForbiddenProfile())
        setForbiddenProfile(other.getForbiddenProfile()); 
      if (other.hasReplyResp())
        mergeReplyResp(other.getReplyResp()); 
      if (other.getIsFeatured() != 0L)
        setIsFeatured(other.getIsFeatured()); 
      if (other.getNeedFilterDisplay())
        setNeedFilterDisplay(other.getNeedFilterDisplay()); 
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
                  getUserLabelFieldBuilder().getBuilder(), extensionRegistry);
              this.bitField0_ |= 0x1;
              continue;
            case 16:
              this.userConsumeInRoom_ = input.readUInt64();
              this.bitField0_ |= 0x2;
              continue;
            case 24:
              this.userSendGiftCntInRoom_ = input.readUInt64();
              this.bitField0_ |= 0x4;
              continue;
            case 32:
              this.individualPriority_ = input.readUInt64();
              this.bitField0_ |= 0x8;
              continue;
            case 48:
              this.supportPin_ = input.readUInt64();
              this.bitField0_ |= 0x10;
              continue;
            case 58:
              input.readMessage((MessageLite.Builder)
                  getSuffixTextFieldBuilder().getBuilder(), extensionRegistry);
              this.bitField0_ |= 0x20;
              continue;
            case 64:
              this.imAction_ = input.readInt32();
              this.bitField0_ |= 0x40;
              continue;
            case 72:
              this.forbiddenProfile_ = input.readBool();
              this.bitField0_ |= 0x80;
              continue;
            case 82:
              input.readMessage((MessageLite.Builder)
                  getReplyRespFieldBuilder().getBuilder(), extensionRegistry);
              this.bitField0_ |= 0x100;
              continue;
            case 96:
              this.isFeatured_ = input.readUInt64();
              this.bitField0_ |= 0x200;
              continue;
            case 104:
              this.needFilterDisplay_ = input.readBool();
              this.bitField0_ |= 0x400;
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
    
    public boolean hasUserLabel() {
      return ((this.bitField0_ & 0x1) != 0);
    }
    
    public Image getUserLabel() {
      if (this.userLabelBuilder_ == null)
        return (this.userLabel_ == null) ? Image.getDefaultInstance() : this.userLabel_; 
      return (Image)this.userLabelBuilder_.getMessage();
    }
    
    public Builder setUserLabel(Image value) {
      if (this.userLabelBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        this.userLabel_ = value;
      } else {
        this.userLabelBuilder_.setMessage((AbstractMessage)value);
      } 
      this.bitField0_ |= 0x1;
      onChanged();
      return this;
    }
    
    public Builder setUserLabel(Image.Builder builderForValue) {
      if (this.userLabelBuilder_ == null) {
        this.userLabel_ = builderForValue.build();
      } else {
        this.userLabelBuilder_.setMessage((AbstractMessage)builderForValue.build());
      } 
      this.bitField0_ |= 0x1;
      onChanged();
      return this;
    }
    
    public Builder mergeUserLabel(Image value) {
      if (this.userLabelBuilder_ == null) {
        if ((this.bitField0_ & 0x1) != 0 && this.userLabel_ != null && this.userLabel_ != 
          
          Image.getDefaultInstance()) {
          getUserLabelBuilder().mergeFrom(value);
        } else {
          this.userLabel_ = value;
        } 
      } else {
        this.userLabelBuilder_.mergeFrom((AbstractMessage)value);
      } 
      if (this.userLabel_ != null) {
        this.bitField0_ |= 0x1;
        onChanged();
      } 
      return this;
    }
    
    public Builder clearUserLabel() {
      this.bitField0_ &= 0xFFFFFFFE;
      this.userLabel_ = null;
      if (this.userLabelBuilder_ != null) {
        this.userLabelBuilder_.dispose();
        this.userLabelBuilder_ = null;
      } 
      onChanged();
      return this;
    }
    
    public Image.Builder getUserLabelBuilder() {
      this.bitField0_ |= 0x1;
      onChanged();
      return (Image.Builder)getUserLabelFieldBuilder().getBuilder();
    }
    
    public ImageOrBuilder getUserLabelOrBuilder() {
      if (this.userLabelBuilder_ != null)
        return (ImageOrBuilder)this.userLabelBuilder_.getMessageOrBuilder(); 
      return (this.userLabel_ == null) ? 
        Image.getDefaultInstance() : this.userLabel_;
    }
    
    private SingleFieldBuilderV3<Image, Image.Builder, ImageOrBuilder> getUserLabelFieldBuilder() {
      if (this.userLabelBuilder_ == null) {
        this
          
          .userLabelBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getUserLabel(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.userLabel_ = null;
      } 
      return this.userLabelBuilder_;
    }
    
    public long getUserConsumeInRoom() {
      return this.userConsumeInRoom_;
    }
    
    public Builder setUserConsumeInRoom(long value) {
      this.userConsumeInRoom_ = value;
      this.bitField0_ |= 0x2;
      onChanged();
      return this;
    }
    
    public Builder clearUserConsumeInRoom() {
      this.bitField0_ &= 0xFFFFFFFD;
      this.userConsumeInRoom_ = 0L;
      onChanged();
      return this;
    }
    
    public long getUserSendGiftCntInRoom() {
      return this.userSendGiftCntInRoom_;
    }
    
    public Builder setUserSendGiftCntInRoom(long value) {
      this.userSendGiftCntInRoom_ = value;
      this.bitField0_ |= 0x4;
      onChanged();
      return this;
    }
    
    public Builder clearUserSendGiftCntInRoom() {
      this.bitField0_ &= 0xFFFFFFFB;
      this.userSendGiftCntInRoom_ = 0L;
      onChanged();
      return this;
    }
    
    public long getIndividualPriority() {
      return this.individualPriority_;
    }
    
    public Builder setIndividualPriority(long value) {
      this.individualPriority_ = value;
      this.bitField0_ |= 0x8;
      onChanged();
      return this;
    }
    
    public Builder clearIndividualPriority() {
      this.bitField0_ &= 0xFFFFFFF7;
      this.individualPriority_ = 0L;
      onChanged();
      return this;
    }
    
    public long getSupportPin() {
      return this.supportPin_;
    }
    
    public Builder setSupportPin(long value) {
      this.supportPin_ = value;
      this.bitField0_ |= 0x10;
      onChanged();
      return this;
    }
    
    public Builder clearSupportPin() {
      this.bitField0_ &= 0xFFFFFFEF;
      this.supportPin_ = 0L;
      onChanged();
      return this;
    }
    
    public boolean hasSuffixText() {
      return ((this.bitField0_ & 0x20) != 0);
    }
    
    public SuffixText getSuffixText() {
      if (this.suffixTextBuilder_ == null)
        return (this.suffixText_ == null) ? SuffixText.getDefaultInstance() : this.suffixText_; 
      return (SuffixText)this.suffixTextBuilder_.getMessage();
    }
    
    public Builder setSuffixText(SuffixText value) {
      if (this.suffixTextBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        this.suffixText_ = value;
      } else {
        this.suffixTextBuilder_.setMessage((AbstractMessage)value);
      } 
      this.bitField0_ |= 0x20;
      onChanged();
      return this;
    }
    
    public Builder setSuffixText(SuffixText.Builder builderForValue) {
      if (this.suffixTextBuilder_ == null) {
        this.suffixText_ = builderForValue.build();
      } else {
        this.suffixTextBuilder_.setMessage((AbstractMessage)builderForValue.build());
      } 
      this.bitField0_ |= 0x20;
      onChanged();
      return this;
    }
    
    public Builder mergeSuffixText(SuffixText value) {
      if (this.suffixTextBuilder_ == null) {
        if ((this.bitField0_ & 0x20) != 0 && this.suffixText_ != null && this.suffixText_ != 
          
          SuffixText.getDefaultInstance()) {
          getSuffixTextBuilder().mergeFrom(value);
        } else {
          this.suffixText_ = value;
        } 
      } else {
        this.suffixTextBuilder_.mergeFrom((AbstractMessage)value);
      } 
      if (this.suffixText_ != null) {
        this.bitField0_ |= 0x20;
        onChanged();
      } 
      return this;
    }
    
    public Builder clearSuffixText() {
      this.bitField0_ &= 0xFFFFFFDF;
      this.suffixText_ = null;
      if (this.suffixTextBuilder_ != null) {
        this.suffixTextBuilder_.dispose();
        this.suffixTextBuilder_ = null;
      } 
      onChanged();
      return this;
    }
    
    public SuffixText.Builder getSuffixTextBuilder() {
      this.bitField0_ |= 0x20;
      onChanged();
      return (SuffixText.Builder)getSuffixTextFieldBuilder().getBuilder();
    }
    
    public SuffixTextOrBuilder getSuffixTextOrBuilder() {
      if (this.suffixTextBuilder_ != null)
        return (SuffixTextOrBuilder)this.suffixTextBuilder_.getMessageOrBuilder(); 
      return (this.suffixText_ == null) ? 
        SuffixText.getDefaultInstance() : this.suffixText_;
    }
    
    private SingleFieldBuilderV3<SuffixText, SuffixText.Builder, SuffixTextOrBuilder> getSuffixTextFieldBuilder() {
      if (this.suffixTextBuilder_ == null) {
        this
          
          .suffixTextBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getSuffixText(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.suffixText_ = null;
      } 
      return this.suffixTextBuilder_;
    }
    
    public int getImAction() {
      return this.imAction_;
    }
    
    public Builder setImAction(int value) {
      this.imAction_ = value;
      this.bitField0_ |= 0x40;
      onChanged();
      return this;
    }
    
    public Builder clearImAction() {
      this.bitField0_ &= 0xFFFFFFBF;
      this.imAction_ = 0;
      onChanged();
      return this;
    }
    
    public boolean getForbiddenProfile() {
      return this.forbiddenProfile_;
    }
    
    public Builder setForbiddenProfile(boolean value) {
      this.forbiddenProfile_ = value;
      this.bitField0_ |= 0x80;
      onChanged();
      return this;
    }
    
    public Builder clearForbiddenProfile() {
      this.bitField0_ &= 0xFFFFFF7F;
      this.forbiddenProfile_ = false;
      onChanged();
      return this;
    }
    
    public boolean hasReplyResp() {
      return ((this.bitField0_ & 0x100) != 0);
    }
    
    public ChatReplyRespInfo getReplyResp() {
      if (this.replyRespBuilder_ == null)
        return (this.replyResp_ == null) ? ChatReplyRespInfo.getDefaultInstance() : this.replyResp_; 
      return (ChatReplyRespInfo)this.replyRespBuilder_.getMessage();
    }
    
    public Builder setReplyResp(ChatReplyRespInfo value) {
      if (this.replyRespBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        this.replyResp_ = value;
      } else {
        this.replyRespBuilder_.setMessage((AbstractMessage)value);
      } 
      this.bitField0_ |= 0x100;
      onChanged();
      return this;
    }
    
    public Builder setReplyResp(ChatReplyRespInfo.Builder builderForValue) {
      if (this.replyRespBuilder_ == null) {
        this.replyResp_ = builderForValue.build();
      } else {
        this.replyRespBuilder_.setMessage((AbstractMessage)builderForValue.build());
      } 
      this.bitField0_ |= 0x100;
      onChanged();
      return this;
    }
    
    public Builder mergeReplyResp(ChatReplyRespInfo value) {
      if (this.replyRespBuilder_ == null) {
        if ((this.bitField0_ & 0x100) != 0 && this.replyResp_ != null && this.replyResp_ != 
          
          ChatReplyRespInfo.getDefaultInstance()) {
          getReplyRespBuilder().mergeFrom(value);
        } else {
          this.replyResp_ = value;
        } 
      } else {
        this.replyRespBuilder_.mergeFrom((AbstractMessage)value);
      } 
      if (this.replyResp_ != null) {
        this.bitField0_ |= 0x100;
        onChanged();
      } 
      return this;
    }
    
    public Builder clearReplyResp() {
      this.bitField0_ &= 0xFFFFFEFF;
      this.replyResp_ = null;
      if (this.replyRespBuilder_ != null) {
        this.replyRespBuilder_.dispose();
        this.replyRespBuilder_ = null;
      } 
      onChanged();
      return this;
    }
    
    public ChatReplyRespInfo.Builder getReplyRespBuilder() {
      this.bitField0_ |= 0x100;
      onChanged();
      return (ChatReplyRespInfo.Builder)getReplyRespFieldBuilder().getBuilder();
    }
    
    public ChatReplyRespInfoOrBuilder getReplyRespOrBuilder() {
      if (this.replyRespBuilder_ != null)
        return (ChatReplyRespInfoOrBuilder)this.replyRespBuilder_.getMessageOrBuilder(); 
      return (this.replyResp_ == null) ? 
        ChatReplyRespInfo.getDefaultInstance() : this.replyResp_;
    }
    
    private SingleFieldBuilderV3<ChatReplyRespInfo, ChatReplyRespInfo.Builder, ChatReplyRespInfoOrBuilder> getReplyRespFieldBuilder() {
      if (this.replyRespBuilder_ == null) {
        this
          
          .replyRespBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getReplyResp(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.replyResp_ = null;
      } 
      return this.replyRespBuilder_;
    }
    
    public long getIsFeatured() {
      return this.isFeatured_;
    }
    
    public Builder setIsFeatured(long value) {
      this.isFeatured_ = value;
      this.bitField0_ |= 0x200;
      onChanged();
      return this;
    }
    
    public Builder clearIsFeatured() {
      this.bitField0_ &= 0xFFFFFDFF;
      this.isFeatured_ = 0L;
      onChanged();
      return this;
    }
    
    public boolean getNeedFilterDisplay() {
      return this.needFilterDisplay_;
    }
    
    public Builder setNeedFilterDisplay(boolean value) {
      this.needFilterDisplay_ = value;
      this.bitField0_ |= 0x400;
      onChanged();
      return this;
    }
    
    public Builder clearNeedFilterDisplay() {
      this.bitField0_ &= 0xFFFFFBFF;
      this.needFilterDisplay_ = false;
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
  
  private static final PublicAreaCommon DEFAULT_INSTANCE = new PublicAreaCommon();
  
  public static PublicAreaCommon getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }
  
  private static final Parser<PublicAreaCommon> PARSER = (Parser<PublicAreaCommon>)new AbstractParser<PublicAreaCommon>() {
      public PublicAreaCommon parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        PublicAreaCommon.Builder builder = PublicAreaCommon.newBuilder();
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
  
  public static Parser<PublicAreaCommon> parser() {
    return PARSER;
  }
  
  public Parser<PublicAreaCommon> getParserForType() {
    return PARSER;
  }
  
  public PublicAreaCommon getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }
}
