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

public final class MemberMessage extends GeneratedMessageV3 implements MemberMessageOrBuilder {
  private static final long serialVersionUID = 0L;
  
  private int bitField0_;
  
  public static final int COMMON_FIELD_NUMBER = 1;
  
  private Common common_;
  
  public static final int USER_FIELD_NUMBER = 2;
  
  private User user_;
  
  public static final int MEMBERCOUNT_FIELD_NUMBER = 3;
  
  private long memberCount_;
  
  public static final int OPERATOR_FIELD_NUMBER = 4;
  
  private User operator_;
  
  public static final int ISSETTOADMIN_FIELD_NUMBER = 5;
  
  private boolean isSetToAdmin_;
  
  public static final int ISTOPUSER_FIELD_NUMBER = 6;
  
  private boolean isTopUser_;
  
  public static final int RANKSCORE_FIELD_NUMBER = 7;
  
  private long rankScore_;
  
  public static final int TOPUSERNO_FIELD_NUMBER = 8;
  
  private long topUserNo_;
  
  public static final int ENTERTYPE_FIELD_NUMBER = 9;
  
  private long enterType_;
  
  public static final int ACTION_FIELD_NUMBER = 10;
  
  private long action_;
  
  public static final int ACTIONDESCRIPTION_FIELD_NUMBER = 11;
  
  private volatile Object actionDescription_;
  
  public static final int USERID_FIELD_NUMBER = 12;
  
  private long userId_;
  
  public static final int EFFECTCONFIG_FIELD_NUMBER = 13;
  
  private EffectConfig effectConfig_;
  
  public static final int POPSTR_FIELD_NUMBER = 14;
  
  private volatile Object popStr_;
  
  public static final int ENTEREFFECTCONFIG_FIELD_NUMBER = 15;
  
  private EffectConfig enterEffectConfig_;
  
  public static final int BACKGROUNDIMAGE_FIELD_NUMBER = 16;
  
  private Image backgroundImage_;
  
  public static final int BACKGROUNDIMAGEV2_FIELD_NUMBER = 17;
  
  private Image backgroundImageV2_;
  
  public static final int ANCHORDISPLAYTEXT_FIELD_NUMBER = 18;
  
  private Text anchorDisplayText_;
  
  public static final int PUBLICAREACOMMON_FIELD_NUMBER = 19;
  
  private PublicAreaCommon publicAreaCommon_;
  
  public static final int USERENTERTIPTYPE_FIELD_NUMBER = 20;
  
  private long userEnterTipType_;
  
  public static final int ANCHORENTERTIPTYPE_FIELD_NUMBER = 21;
  
  private long anchorEnterTipType_;
  
  private byte memoizedIsInitialized;
  
  private MemberMessage(GeneratedMessageV3.Builder<?> builder) {
    super(builder);
    this.memberCount_ = 0L;
    this.isSetToAdmin_ = false;
    this.isTopUser_ = false;
    this.rankScore_ = 0L;
    this.topUserNo_ = 0L;
    this.enterType_ = 0L;
    this.action_ = 0L;
    this.actionDescription_ = "";
    this.userId_ = 0L;
    this.popStr_ = "";
    this.userEnterTipType_ = 0L;
    this.anchorEnterTipType_ = 0L;
    this.memoizedIsInitialized = -1;
  }
  
  private MemberMessage() {
    this.memberCount_ = 0L;
    this.isSetToAdmin_ = false;
    this.isTopUser_ = false;
    this.rankScore_ = 0L;
    this.topUserNo_ = 0L;
    this.enterType_ = 0L;
    this.action_ = 0L;
    this.actionDescription_ = "";
    this.userId_ = 0L;
    this.popStr_ = "";
    this.userEnterTipType_ = 0L;
    this.anchorEnterTipType_ = 0L;
    this.memoizedIsInitialized = -1;
    this.actionDescription_ = "";
    this.popStr_ = "";
  }
  
  protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
    return new MemberMessage();
  }
  
  public static final Descriptors.Descriptor getDescriptor() {
    return Douyin.internal_static_MemberMessage_descriptor;
  }
  
  protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
    return Douyin.internal_static_MemberMessage_fieldAccessorTable.ensureFieldAccessorsInitialized(MemberMessage.class, Builder.class);
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
  
  public boolean hasUser() {
    return ((this.bitField0_ & 0x2) != 0);
  }
  
  public User getUser() {
    return (this.user_ == null) ? User.getDefaultInstance() : this.user_;
  }
  
  public UserOrBuilder getUserOrBuilder() {
    return (this.user_ == null) ? User.getDefaultInstance() : this.user_;
  }
  
  public long getMemberCount() {
    return this.memberCount_;
  }
  
  public boolean hasOperator() {
    return ((this.bitField0_ & 0x4) != 0);
  }
  
  public User getOperator() {
    return (this.operator_ == null) ? User.getDefaultInstance() : this.operator_;
  }
  
  public UserOrBuilder getOperatorOrBuilder() {
    return (this.operator_ == null) ? User.getDefaultInstance() : this.operator_;
  }
  
  public boolean getIsSetToAdmin() {
    return this.isSetToAdmin_;
  }
  
  public boolean getIsTopUser() {
    return this.isTopUser_;
  }
  
  public long getRankScore() {
    return this.rankScore_;
  }
  
  public long getTopUserNo() {
    return this.topUserNo_;
  }
  
  public long getEnterType() {
    return this.enterType_;
  }
  
  public long getAction() {
    return this.action_;
  }
  
  public String getActionDescription() {
    Object ref = this.actionDescription_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.actionDescription_ = s;
    return s;
  }
  
  public ByteString getActionDescriptionBytes() {
    Object ref = this.actionDescription_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.actionDescription_ = b;
      return b;
    } 
    return (ByteString)ref;
  }
  
  public long getUserId() {
    return this.userId_;
  }
  
  public boolean hasEffectConfig() {
    return ((this.bitField0_ & 0x8) != 0);
  }
  
  public EffectConfig getEffectConfig() {
    return (this.effectConfig_ == null) ? EffectConfig.getDefaultInstance() : this.effectConfig_;
  }
  
  public EffectConfigOrBuilder getEffectConfigOrBuilder() {
    return (this.effectConfig_ == null) ? EffectConfig.getDefaultInstance() : this.effectConfig_;
  }
  
  public String getPopStr() {
    Object ref = this.popStr_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.popStr_ = s;
    return s;
  }
  
  public ByteString getPopStrBytes() {
    Object ref = this.popStr_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.popStr_ = b;
      return b;
    } 
    return (ByteString)ref;
  }
  
  public boolean hasEnterEffectConfig() {
    return ((this.bitField0_ & 0x10) != 0);
  }
  
  public EffectConfig getEnterEffectConfig() {
    return (this.enterEffectConfig_ == null) ? EffectConfig.getDefaultInstance() : this.enterEffectConfig_;
  }
  
  public EffectConfigOrBuilder getEnterEffectConfigOrBuilder() {
    return (this.enterEffectConfig_ == null) ? EffectConfig.getDefaultInstance() : this.enterEffectConfig_;
  }
  
  public boolean hasBackgroundImage() {
    return ((this.bitField0_ & 0x20) != 0);
  }
  
  public Image getBackgroundImage() {
    return (this.backgroundImage_ == null) ? Image.getDefaultInstance() : this.backgroundImage_;
  }
  
  public ImageOrBuilder getBackgroundImageOrBuilder() {
    return (this.backgroundImage_ == null) ? Image.getDefaultInstance() : this.backgroundImage_;
  }
  
  public boolean hasBackgroundImageV2() {
    return ((this.bitField0_ & 0x40) != 0);
  }
  
  public Image getBackgroundImageV2() {
    return (this.backgroundImageV2_ == null) ? Image.getDefaultInstance() : this.backgroundImageV2_;
  }
  
  public ImageOrBuilder getBackgroundImageV2OrBuilder() {
    return (this.backgroundImageV2_ == null) ? Image.getDefaultInstance() : this.backgroundImageV2_;
  }
  
  public boolean hasAnchorDisplayText() {
    return ((this.bitField0_ & 0x80) != 0);
  }
  
  public Text getAnchorDisplayText() {
    return (this.anchorDisplayText_ == null) ? Text.getDefaultInstance() : this.anchorDisplayText_;
  }
  
  public TextOrBuilder getAnchorDisplayTextOrBuilder() {
    return (this.anchorDisplayText_ == null) ? Text.getDefaultInstance() : this.anchorDisplayText_;
  }
  
  public boolean hasPublicAreaCommon() {
    return ((this.bitField0_ & 0x100) != 0);
  }
  
  public PublicAreaCommon getPublicAreaCommon() {
    return (this.publicAreaCommon_ == null) ? PublicAreaCommon.getDefaultInstance() : this.publicAreaCommon_;
  }
  
  public PublicAreaCommonOrBuilder getPublicAreaCommonOrBuilder() {
    return (this.publicAreaCommon_ == null) ? PublicAreaCommon.getDefaultInstance() : this.publicAreaCommon_;
  }
  
  public long getUserEnterTipType() {
    return this.userEnterTipType_;
  }
  
  public long getAnchorEnterTipType() {
    return this.anchorEnterTipType_;
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
      output.writeMessage(2, (MessageLite)getUser()); 
    if (this.memberCount_ != 0L)
      output.writeUInt64(3, this.memberCount_); 
    if ((this.bitField0_ & 0x4) != 0)
      output.writeMessage(4, (MessageLite)getOperator()); 
    if (this.isSetToAdmin_)
      output.writeBool(5, this.isSetToAdmin_); 
    if (this.isTopUser_)
      output.writeBool(6, this.isTopUser_); 
    if (this.rankScore_ != 0L)
      output.writeUInt64(7, this.rankScore_); 
    if (this.topUserNo_ != 0L)
      output.writeUInt64(8, this.topUserNo_); 
    if (this.enterType_ != 0L)
      output.writeUInt64(9, this.enterType_); 
    if (this.action_ != 0L)
      output.writeUInt64(10, this.action_); 
    if (!GeneratedMessageV3.isStringEmpty(this.actionDescription_))
      GeneratedMessageV3.writeString(output, 11, this.actionDescription_); 
    if (this.userId_ != 0L)
      output.writeUInt64(12, this.userId_); 
    if ((this.bitField0_ & 0x8) != 0)
      output.writeMessage(13, (MessageLite)getEffectConfig()); 
    if (!GeneratedMessageV3.isStringEmpty(this.popStr_))
      GeneratedMessageV3.writeString(output, 14, this.popStr_); 
    if ((this.bitField0_ & 0x10) != 0)
      output.writeMessage(15, (MessageLite)getEnterEffectConfig()); 
    if ((this.bitField0_ & 0x20) != 0)
      output.writeMessage(16, (MessageLite)getBackgroundImage()); 
    if ((this.bitField0_ & 0x40) != 0)
      output.writeMessage(17, (MessageLite)getBackgroundImageV2()); 
    if ((this.bitField0_ & 0x80) != 0)
      output.writeMessage(18, (MessageLite)getAnchorDisplayText()); 
    if ((this.bitField0_ & 0x100) != 0)
      output.writeMessage(19, (MessageLite)getPublicAreaCommon()); 
    if (this.userEnterTipType_ != 0L)
      output.writeUInt64(20, this.userEnterTipType_); 
    if (this.anchorEnterTipType_ != 0L)
      output.writeUInt64(21, this.anchorEnterTipType_); 
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
        CodedOutputStream.computeMessageSize(2, (MessageLite)getUser()); 
    if (this.memberCount_ != 0L)
      size += 
        CodedOutputStream.computeUInt64Size(3, this.memberCount_); 
    if ((this.bitField0_ & 0x4) != 0)
      size += 
        CodedOutputStream.computeMessageSize(4, (MessageLite)getOperator()); 
    if (this.isSetToAdmin_)
      size += 
        CodedOutputStream.computeBoolSize(5, this.isSetToAdmin_); 
    if (this.isTopUser_)
      size += 
        CodedOutputStream.computeBoolSize(6, this.isTopUser_); 
    if (this.rankScore_ != 0L)
      size += 
        CodedOutputStream.computeUInt64Size(7, this.rankScore_); 
    if (this.topUserNo_ != 0L)
      size += 
        CodedOutputStream.computeUInt64Size(8, this.topUserNo_); 
    if (this.enterType_ != 0L)
      size += 
        CodedOutputStream.computeUInt64Size(9, this.enterType_); 
    if (this.action_ != 0L)
      size += 
        CodedOutputStream.computeUInt64Size(10, this.action_); 
    if (!GeneratedMessageV3.isStringEmpty(this.actionDescription_))
      size += GeneratedMessageV3.computeStringSize(11, this.actionDescription_); 
    if (this.userId_ != 0L)
      size += 
        CodedOutputStream.computeUInt64Size(12, this.userId_); 
    if ((this.bitField0_ & 0x8) != 0)
      size += 
        CodedOutputStream.computeMessageSize(13, (MessageLite)getEffectConfig()); 
    if (!GeneratedMessageV3.isStringEmpty(this.popStr_))
      size += GeneratedMessageV3.computeStringSize(14, this.popStr_); 
    if ((this.bitField0_ & 0x10) != 0)
      size += 
        CodedOutputStream.computeMessageSize(15, (MessageLite)getEnterEffectConfig()); 
    if ((this.bitField0_ & 0x20) != 0)
      size += 
        CodedOutputStream.computeMessageSize(16, (MessageLite)getBackgroundImage()); 
    if ((this.bitField0_ & 0x40) != 0)
      size += 
        CodedOutputStream.computeMessageSize(17, (MessageLite)getBackgroundImageV2()); 
    if ((this.bitField0_ & 0x80) != 0)
      size += 
        CodedOutputStream.computeMessageSize(18, (MessageLite)getAnchorDisplayText()); 
    if ((this.bitField0_ & 0x100) != 0)
      size += 
        CodedOutputStream.computeMessageSize(19, (MessageLite)getPublicAreaCommon()); 
    if (this.userEnterTipType_ != 0L)
      size += 
        CodedOutputStream.computeUInt64Size(20, this.userEnterTipType_); 
    if (this.anchorEnterTipType_ != 0L)
      size += 
        CodedOutputStream.computeUInt64Size(21, this.anchorEnterTipType_); 
    size += getUnknownFields().getSerializedSize();
    this.memoizedSize = size;
    return size;
  }
  
  public boolean equals(Object obj) {
    if (obj == this)
      return true; 
    if (!(obj instanceof MemberMessage))
      return super.equals(obj); 
    MemberMessage other = (MemberMessage)obj;
    if (hasCommon() != other.hasCommon())
      return false; 
    if (hasCommon() && 
      
      !getCommon().equals(other.getCommon()))
      return false; 
    if (hasUser() != other.hasUser())
      return false; 
    if (hasUser() && 
      
      !getUser().equals(other.getUser()))
      return false; 
    if (getMemberCount() != other
      .getMemberCount())
      return false; 
    if (hasOperator() != other.hasOperator())
      return false; 
    if (hasOperator() && 
      
      !getOperator().equals(other.getOperator()))
      return false; 
    if (getIsSetToAdmin() != other
      .getIsSetToAdmin())
      return false; 
    if (getIsTopUser() != other
      .getIsTopUser())
      return false; 
    if (getRankScore() != other
      .getRankScore())
      return false; 
    if (getTopUserNo() != other
      .getTopUserNo())
      return false; 
    if (getEnterType() != other
      .getEnterType())
      return false; 
    if (getAction() != other
      .getAction())
      return false; 
    if (!getActionDescription().equals(other.getActionDescription()))
      return false; 
    if (getUserId() != other
      .getUserId())
      return false; 
    if (hasEffectConfig() != other.hasEffectConfig())
      return false; 
    if (hasEffectConfig() && 
      
      !getEffectConfig().equals(other.getEffectConfig()))
      return false; 
    if (!getPopStr().equals(other.getPopStr()))
      return false; 
    if (hasEnterEffectConfig() != other.hasEnterEffectConfig())
      return false; 
    if (hasEnterEffectConfig() && 
      
      !getEnterEffectConfig().equals(other.getEnterEffectConfig()))
      return false; 
    if (hasBackgroundImage() != other.hasBackgroundImage())
      return false; 
    if (hasBackgroundImage() && 
      
      !getBackgroundImage().equals(other.getBackgroundImage()))
      return false; 
    if (hasBackgroundImageV2() != other.hasBackgroundImageV2())
      return false; 
    if (hasBackgroundImageV2() && 
      
      !getBackgroundImageV2().equals(other.getBackgroundImageV2()))
      return false; 
    if (hasAnchorDisplayText() != other.hasAnchorDisplayText())
      return false; 
    if (hasAnchorDisplayText() && 
      
      !getAnchorDisplayText().equals(other.getAnchorDisplayText()))
      return false; 
    if (hasPublicAreaCommon() != other.hasPublicAreaCommon())
      return false; 
    if (hasPublicAreaCommon() && 
      
      !getPublicAreaCommon().equals(other.getPublicAreaCommon()))
      return false; 
    if (getUserEnterTipType() != other
      .getUserEnterTipType())
      return false; 
    if (getAnchorEnterTipType() != other
      .getAnchorEnterTipType())
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
    if (hasUser()) {
      hash = 37 * hash + 2;
      hash = 53 * hash + getUser().hashCode();
    } 
    hash = 37 * hash + 3;
    hash = 53 * hash + Internal.hashLong(
        getMemberCount());
    if (hasOperator()) {
      hash = 37 * hash + 4;
      hash = 53 * hash + getOperator().hashCode();
    } 
    hash = 37 * hash + 5;
    hash = 53 * hash + Internal.hashBoolean(
        getIsSetToAdmin());
    hash = 37 * hash + 6;
    hash = 53 * hash + Internal.hashBoolean(
        getIsTopUser());
    hash = 37 * hash + 7;
    hash = 53 * hash + Internal.hashLong(
        getRankScore());
    hash = 37 * hash + 8;
    hash = 53 * hash + Internal.hashLong(
        getTopUserNo());
    hash = 37 * hash + 9;
    hash = 53 * hash + Internal.hashLong(
        getEnterType());
    hash = 37 * hash + 10;
    hash = 53 * hash + Internal.hashLong(
        getAction());
    hash = 37 * hash + 11;
    hash = 53 * hash + getActionDescription().hashCode();
    hash = 37 * hash + 12;
    hash = 53 * hash + Internal.hashLong(
        getUserId());
    if (hasEffectConfig()) {
      hash = 37 * hash + 13;
      hash = 53 * hash + getEffectConfig().hashCode();
    } 
    hash = 37 * hash + 14;
    hash = 53 * hash + getPopStr().hashCode();
    if (hasEnterEffectConfig()) {
      hash = 37 * hash + 15;
      hash = 53 * hash + getEnterEffectConfig().hashCode();
    } 
    if (hasBackgroundImage()) {
      hash = 37 * hash + 16;
      hash = 53 * hash + getBackgroundImage().hashCode();
    } 
    if (hasBackgroundImageV2()) {
      hash = 37 * hash + 17;
      hash = 53 * hash + getBackgroundImageV2().hashCode();
    } 
    if (hasAnchorDisplayText()) {
      hash = 37 * hash + 18;
      hash = 53 * hash + getAnchorDisplayText().hashCode();
    } 
    if (hasPublicAreaCommon()) {
      hash = 37 * hash + 19;
      hash = 53 * hash + getPublicAreaCommon().hashCode();
    } 
    hash = 37 * hash + 20;
    hash = 53 * hash + Internal.hashLong(
        getUserEnterTipType());
    hash = 37 * hash + 21;
    hash = 53 * hash + Internal.hashLong(
        getAnchorEnterTipType());
    hash = 29 * hash + getUnknownFields().hashCode();
    this.memoizedHashCode = hash;
    return hash;
  }
  
  public static MemberMessage parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
    return (MemberMessage)PARSER.parseFrom(data);
  }
  
  public static MemberMessage parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (MemberMessage)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static MemberMessage parseFrom(ByteString data) throws InvalidProtocolBufferException {
    return (MemberMessage)PARSER.parseFrom(data);
  }
  
  public static MemberMessage parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (MemberMessage)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static MemberMessage parseFrom(byte[] data) throws InvalidProtocolBufferException {
    return (MemberMessage)PARSER.parseFrom(data);
  }
  
  public static MemberMessage parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (MemberMessage)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static MemberMessage parseFrom(InputStream input) throws IOException {
    return 
      (MemberMessage)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static MemberMessage parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (MemberMessage)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static MemberMessage parseDelimitedFrom(InputStream input) throws IOException {
    return 
      (MemberMessage)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
  }
  
  public static MemberMessage parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (MemberMessage)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static MemberMessage parseFrom(CodedInputStream input) throws IOException {
    return 
      (MemberMessage)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static MemberMessage parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (MemberMessage)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public Builder newBuilderForType() {
    return newBuilder();
  }
  
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  
  public static Builder newBuilder(MemberMessage prototype) {
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
  
  public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements MemberMessageOrBuilder {
    private int bitField0_;
    
    private Common common_;
    
    private SingleFieldBuilderV3<Common, Common.Builder, CommonOrBuilder> commonBuilder_;
    
    private User user_;
    
    private SingleFieldBuilderV3<User, User.Builder, UserOrBuilder> userBuilder_;
    
    private long memberCount_;
    
    private User operator_;
    
    private SingleFieldBuilderV3<User, User.Builder, UserOrBuilder> operatorBuilder_;
    
    private boolean isSetToAdmin_;
    
    private boolean isTopUser_;
    
    private long rankScore_;
    
    private long topUserNo_;
    
    private long enterType_;
    
    private long action_;
    
    private Object actionDescription_;
    
    private long userId_;
    
    private EffectConfig effectConfig_;
    
    private SingleFieldBuilderV3<EffectConfig, EffectConfig.Builder, EffectConfigOrBuilder> effectConfigBuilder_;
    
    private Object popStr_;
    
    private EffectConfig enterEffectConfig_;
    
    private SingleFieldBuilderV3<EffectConfig, EffectConfig.Builder, EffectConfigOrBuilder> enterEffectConfigBuilder_;
    
    private Image backgroundImage_;
    
    private SingleFieldBuilderV3<Image, Image.Builder, ImageOrBuilder> backgroundImageBuilder_;
    
    private Image backgroundImageV2_;
    
    private SingleFieldBuilderV3<Image, Image.Builder, ImageOrBuilder> backgroundImageV2Builder_;
    
    private Text anchorDisplayText_;
    
    private SingleFieldBuilderV3<Text, Text.Builder, TextOrBuilder> anchorDisplayTextBuilder_;
    
    private PublicAreaCommon publicAreaCommon_;
    
    private SingleFieldBuilderV3<PublicAreaCommon, PublicAreaCommon.Builder, PublicAreaCommonOrBuilder> publicAreaCommonBuilder_;
    
    private long userEnterTipType_;
    
    private long anchorEnterTipType_;
    
    public static final Descriptors.Descriptor getDescriptor() {
      return Douyin.internal_static_MemberMessage_descriptor;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return Douyin.internal_static_MemberMessage_fieldAccessorTable
        .ensureFieldAccessorsInitialized(MemberMessage.class, Builder.class);
    }
    
    private Builder() {
      this.actionDescription_ = "";
      this.popStr_ = "";
      maybeForceBuilderInitialization();
    }
    
    private Builder(GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      this.actionDescription_ = "";
      this.popStr_ = "";
      maybeForceBuilderInitialization();
    }
    
    private void maybeForceBuilderInitialization() {
      if (MemberMessage.alwaysUseFieldBuilders) {
        getCommonFieldBuilder();
        getUserFieldBuilder();
        getOperatorFieldBuilder();
        getEffectConfigFieldBuilder();
        getEnterEffectConfigFieldBuilder();
        getBackgroundImageFieldBuilder();
        getBackgroundImageV2FieldBuilder();
        getAnchorDisplayTextFieldBuilder();
        getPublicAreaCommonFieldBuilder();
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
      this.user_ = null;
      if (this.userBuilder_ != null) {
        this.userBuilder_.dispose();
        this.userBuilder_ = null;
      } 
      this.memberCount_ = 0L;
      this.operator_ = null;
      if (this.operatorBuilder_ != null) {
        this.operatorBuilder_.dispose();
        this.operatorBuilder_ = null;
      } 
      this.isSetToAdmin_ = false;
      this.isTopUser_ = false;
      this.rankScore_ = 0L;
      this.topUserNo_ = 0L;
      this.enterType_ = 0L;
      this.action_ = 0L;
      this.actionDescription_ = "";
      this.userId_ = 0L;
      this.effectConfig_ = null;
      if (this.effectConfigBuilder_ != null) {
        this.effectConfigBuilder_.dispose();
        this.effectConfigBuilder_ = null;
      } 
      this.popStr_ = "";
      this.enterEffectConfig_ = null;
      if (this.enterEffectConfigBuilder_ != null) {
        this.enterEffectConfigBuilder_.dispose();
        this.enterEffectConfigBuilder_ = null;
      } 
      this.backgroundImage_ = null;
      if (this.backgroundImageBuilder_ != null) {
        this.backgroundImageBuilder_.dispose();
        this.backgroundImageBuilder_ = null;
      } 
      this.backgroundImageV2_ = null;
      if (this.backgroundImageV2Builder_ != null) {
        this.backgroundImageV2Builder_.dispose();
        this.backgroundImageV2Builder_ = null;
      } 
      this.anchorDisplayText_ = null;
      if (this.anchorDisplayTextBuilder_ != null) {
        this.anchorDisplayTextBuilder_.dispose();
        this.anchorDisplayTextBuilder_ = null;
      } 
      this.publicAreaCommon_ = null;
      if (this.publicAreaCommonBuilder_ != null) {
        this.publicAreaCommonBuilder_.dispose();
        this.publicAreaCommonBuilder_ = null;
      } 
      this.userEnterTipType_ = 0L;
      this.anchorEnterTipType_ = 0L;
      return this;
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
      return Douyin.internal_static_MemberMessage_descriptor;
    }
    
    public MemberMessage getDefaultInstanceForType() {
      return MemberMessage.getDefaultInstance();
    }
    
    public MemberMessage build() {
      MemberMessage result = buildPartial();
      if (!result.isInitialized())
        throw newUninitializedMessageException(result); 
      return result;
    }
    
    public MemberMessage buildPartial() {
      MemberMessage result = new MemberMessage(this);
      if (this.bitField0_ != 0)
        buildPartial0(result); 
      onBuilt();
      return result;
    }
    
    private void buildPartial0(MemberMessage result) {
      int from_bitField0_ = this.bitField0_;
      int to_bitField0_ = 0;
      if ((from_bitField0_ & 0x1) != 0) {
        result.common_ = (this.commonBuilder_ == null) ? this.common_ : (Common)this.commonBuilder_.build();
        to_bitField0_ |= 0x1;
      } 
      if ((from_bitField0_ & 0x2) != 0) {
        result.user_ = (this.userBuilder_ == null) ? this.user_ : (User)this.userBuilder_.build();
        to_bitField0_ |= 0x2;
      } 
      if ((from_bitField0_ & 0x4) != 0)
        result.memberCount_ = this.memberCount_; 
      if ((from_bitField0_ & 0x8) != 0) {
        result.operator_ = (this.operatorBuilder_ == null) ? this.operator_ : (User)this.operatorBuilder_.build();
        to_bitField0_ |= 0x4;
      } 
      if ((from_bitField0_ & 0x10) != 0)
        result.isSetToAdmin_ = this.isSetToAdmin_; 
      if ((from_bitField0_ & 0x20) != 0)
        result.isTopUser_ = this.isTopUser_; 
      if ((from_bitField0_ & 0x40) != 0)
        result.rankScore_ = this.rankScore_; 
      if ((from_bitField0_ & 0x80) != 0)
        result.topUserNo_ = this.topUserNo_; 
      if ((from_bitField0_ & 0x100) != 0)
        result.enterType_ = this.enterType_; 
      if ((from_bitField0_ & 0x200) != 0)
        result.action_ = this.action_; 
      if ((from_bitField0_ & 0x400) != 0)
        result.actionDescription_ = this.actionDescription_; 
      if ((from_bitField0_ & 0x800) != 0)
        result.userId_ = this.userId_; 
      if ((from_bitField0_ & 0x1000) != 0) {
        result.effectConfig_ = (this.effectConfigBuilder_ == null) ? this.effectConfig_ : (EffectConfig)this.effectConfigBuilder_.build();
        to_bitField0_ |= 0x8;
      } 
      if ((from_bitField0_ & 0x2000) != 0)
        result.popStr_ = this.popStr_; 
      if ((from_bitField0_ & 0x4000) != 0) {
        result.enterEffectConfig_ = (this.enterEffectConfigBuilder_ == null) ? this.enterEffectConfig_ : (EffectConfig)this.enterEffectConfigBuilder_.build();
        to_bitField0_ |= 0x10;
      } 
      if ((from_bitField0_ & 0x8000) != 0) {
        result.backgroundImage_ = (this.backgroundImageBuilder_ == null) ? this.backgroundImage_ : (Image)this.backgroundImageBuilder_.build();
        to_bitField0_ |= 0x20;
      } 
      if ((from_bitField0_ & 0x10000) != 0) {
        result.backgroundImageV2_ = (this.backgroundImageV2Builder_ == null) ? this.backgroundImageV2_ : (Image)this.backgroundImageV2Builder_.build();
        to_bitField0_ |= 0x40;
      } 
      if ((from_bitField0_ & 0x20000) != 0) {
        result.anchorDisplayText_ = (this.anchorDisplayTextBuilder_ == null) ? this.anchorDisplayText_ : (Text)this.anchorDisplayTextBuilder_.build();
        to_bitField0_ |= 0x80;
      } 
      if ((from_bitField0_ & 0x40000) != 0) {
        result.publicAreaCommon_ = (this.publicAreaCommonBuilder_ == null) ? this.publicAreaCommon_ : (PublicAreaCommon)this.publicAreaCommonBuilder_.build();
        to_bitField0_ |= 0x100;
      } 
      if ((from_bitField0_ & 0x80000) != 0)
        result.userEnterTipType_ = this.userEnterTipType_; 
      if ((from_bitField0_ & 0x100000) != 0)
        result.anchorEnterTipType_ = this.anchorEnterTipType_; 
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
      if (other instanceof MemberMessage)
        return mergeFrom((MemberMessage)other); 
      super.mergeFrom(other);
      return this;
    }
    
    public Builder mergeFrom(MemberMessage other) {
      if (other == MemberMessage.getDefaultInstance())
        return this; 
      if (other.hasCommon())
        mergeCommon(other.getCommon()); 
      if (other.hasUser())
        mergeUser(other.getUser()); 
      if (other.getMemberCount() != 0L)
        setMemberCount(other.getMemberCount()); 
      if (other.hasOperator())
        mergeOperator(other.getOperator()); 
      if (other.getIsSetToAdmin())
        setIsSetToAdmin(other.getIsSetToAdmin()); 
      if (other.getIsTopUser())
        setIsTopUser(other.getIsTopUser()); 
      if (other.getRankScore() != 0L)
        setRankScore(other.getRankScore()); 
      if (other.getTopUserNo() != 0L)
        setTopUserNo(other.getTopUserNo()); 
      if (other.getEnterType() != 0L)
        setEnterType(other.getEnterType()); 
      if (other.getAction() != 0L)
        setAction(other.getAction()); 
      if (!other.getActionDescription().isEmpty()) {
        this.actionDescription_ = other.actionDescription_;
        this.bitField0_ |= 0x400;
        onChanged();
      } 
      if (other.getUserId() != 0L)
        setUserId(other.getUserId()); 
      if (other.hasEffectConfig())
        mergeEffectConfig(other.getEffectConfig()); 
      if (!other.getPopStr().isEmpty()) {
        this.popStr_ = other.popStr_;
        this.bitField0_ |= 0x2000;
        onChanged();
      } 
      if (other.hasEnterEffectConfig())
        mergeEnterEffectConfig(other.getEnterEffectConfig()); 
      if (other.hasBackgroundImage())
        mergeBackgroundImage(other.getBackgroundImage()); 
      if (other.hasBackgroundImageV2())
        mergeBackgroundImageV2(other.getBackgroundImageV2()); 
      if (other.hasAnchorDisplayText())
        mergeAnchorDisplayText(other.getAnchorDisplayText()); 
      if (other.hasPublicAreaCommon())
        mergePublicAreaCommon(other.getPublicAreaCommon()); 
      if (other.getUserEnterTipType() != 0L)
        setUserEnterTipType(other.getUserEnterTipType()); 
      if (other.getAnchorEnterTipType() != 0L)
        setAnchorEnterTipType(other.getAnchorEnterTipType()); 
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
              input.readMessage((MessageLite.Builder)getUserFieldBuilder().getBuilder(), extensionRegistry);
              this.bitField0_ |= 0x2;
              continue;
            case 24:
              this.memberCount_ = input.readUInt64();
              this.bitField0_ |= 0x4;
              continue;
            case 34:
              input.readMessage((MessageLite.Builder)getOperatorFieldBuilder().getBuilder(), extensionRegistry);
              this.bitField0_ |= 0x8;
              continue;
            case 40:
              this.isSetToAdmin_ = input.readBool();
              this.bitField0_ |= 0x10;
              continue;
            case 48:
              this.isTopUser_ = input.readBool();
              this.bitField0_ |= 0x20;
              continue;
            case 56:
              this.rankScore_ = input.readUInt64();
              this.bitField0_ |= 0x40;
              continue;
            case 64:
              this.topUserNo_ = input.readUInt64();
              this.bitField0_ |= 0x80;
              continue;
            case 72:
              this.enterType_ = input.readUInt64();
              this.bitField0_ |= 0x100;
              continue;
            case 80:
              this.action_ = input.readUInt64();
              this.bitField0_ |= 0x200;
              continue;
            case 90:
              this.actionDescription_ = input.readStringRequireUtf8();
              this.bitField0_ |= 0x400;
              continue;
            case 96:
              this.userId_ = input.readUInt64();
              this.bitField0_ |= 0x800;
              continue;
            case 106:
              input.readMessage((MessageLite.Builder)getEffectConfigFieldBuilder().getBuilder(), extensionRegistry);
              this.bitField0_ |= 0x1000;
              continue;
            case 114:
              this.popStr_ = input.readStringRequireUtf8();
              this.bitField0_ |= 0x2000;
              continue;
            case 122:
              input.readMessage((MessageLite.Builder)getEnterEffectConfigFieldBuilder().getBuilder(), extensionRegistry);
              this.bitField0_ |= 0x4000;
              continue;
            case 130:
              input.readMessage((MessageLite.Builder)getBackgroundImageFieldBuilder().getBuilder(), extensionRegistry);
              this.bitField0_ |= 0x8000;
              continue;
            case 138:
              input.readMessage((MessageLite.Builder)getBackgroundImageV2FieldBuilder().getBuilder(), extensionRegistry);
              this.bitField0_ |= 0x10000;
              continue;
            case 146:
              input.readMessage((MessageLite.Builder)getAnchorDisplayTextFieldBuilder().getBuilder(), extensionRegistry);
              this.bitField0_ |= 0x20000;
              continue;
            case 154:
              input.readMessage((MessageLite.Builder)getPublicAreaCommonFieldBuilder().getBuilder(), extensionRegistry);
              this.bitField0_ |= 0x40000;
              continue;
            case 160:
              this.userEnterTipType_ = input.readUInt64();
              this.bitField0_ |= 0x80000;
              continue;
            case 168:
              this.anchorEnterTipType_ = input.readUInt64();
              this.bitField0_ |= 0x100000;
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
    
    public boolean hasUser() {
      return ((this.bitField0_ & 0x2) != 0);
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
      this.bitField0_ |= 0x2;
      onChanged();
      return this;
    }
    
    public Builder setUser(User.Builder builderForValue) {
      if (this.userBuilder_ == null) {
        this.user_ = builderForValue.build();
      } else {
        this.userBuilder_.setMessage((AbstractMessage)builderForValue.build());
      } 
      this.bitField0_ |= 0x2;
      onChanged();
      return this;
    }
    
    public Builder mergeUser(User value) {
      if (this.userBuilder_ == null) {
        if ((this.bitField0_ & 0x2) != 0 && this.user_ != null && this.user_ != User.getDefaultInstance()) {
          getUserBuilder().mergeFrom(value);
        } else {
          this.user_ = value;
        } 
      } else {
        this.userBuilder_.mergeFrom((AbstractMessage)value);
      } 
      if (this.user_ != null) {
        this.bitField0_ |= 0x2;
        onChanged();
      } 
      return this;
    }
    
    public Builder clearUser() {
      this.bitField0_ &= 0xFFFFFFFD;
      this.user_ = null;
      if (this.userBuilder_ != null) {
        this.userBuilder_.dispose();
        this.userBuilder_ = null;
      } 
      onChanged();
      return this;
    }
    
    public User.Builder getUserBuilder() {
      this.bitField0_ |= 0x2;
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
    
    public long getMemberCount() {
      return this.memberCount_;
    }
    
    public Builder setMemberCount(long value) {
      this.memberCount_ = value;
      this.bitField0_ |= 0x4;
      onChanged();
      return this;
    }
    
    public Builder clearMemberCount() {
      this.bitField0_ &= 0xFFFFFFFB;
      this.memberCount_ = 0L;
      onChanged();
      return this;
    }
    
    public boolean hasOperator() {
      return ((this.bitField0_ & 0x8) != 0);
    }
    
    public User getOperator() {
      if (this.operatorBuilder_ == null)
        return (this.operator_ == null) ? User.getDefaultInstance() : this.operator_; 
      return (User)this.operatorBuilder_.getMessage();
    }
    
    public Builder setOperator(User value) {
      if (this.operatorBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        this.operator_ = value;
      } else {
        this.operatorBuilder_.setMessage((AbstractMessage)value);
      } 
      this.bitField0_ |= 0x8;
      onChanged();
      return this;
    }
    
    public Builder setOperator(User.Builder builderForValue) {
      if (this.operatorBuilder_ == null) {
        this.operator_ = builderForValue.build();
      } else {
        this.operatorBuilder_.setMessage((AbstractMessage)builderForValue.build());
      } 
      this.bitField0_ |= 0x8;
      onChanged();
      return this;
    }
    
    public Builder mergeOperator(User value) {
      if (this.operatorBuilder_ == null) {
        if ((this.bitField0_ & 0x8) != 0 && this.operator_ != null && this.operator_ != User.getDefaultInstance()) {
          getOperatorBuilder().mergeFrom(value);
        } else {
          this.operator_ = value;
        } 
      } else {
        this.operatorBuilder_.mergeFrom((AbstractMessage)value);
      } 
      if (this.operator_ != null) {
        this.bitField0_ |= 0x8;
        onChanged();
      } 
      return this;
    }
    
    public Builder clearOperator() {
      this.bitField0_ &= 0xFFFFFFF7;
      this.operator_ = null;
      if (this.operatorBuilder_ != null) {
        this.operatorBuilder_.dispose();
        this.operatorBuilder_ = null;
      } 
      onChanged();
      return this;
    }
    
    public User.Builder getOperatorBuilder() {
      this.bitField0_ |= 0x8;
      onChanged();
      return (User.Builder)getOperatorFieldBuilder().getBuilder();
    }
    
    public UserOrBuilder getOperatorOrBuilder() {
      if (this.operatorBuilder_ != null)
        return (UserOrBuilder)this.operatorBuilder_.getMessageOrBuilder(); 
      return (this.operator_ == null) ? User.getDefaultInstance() : this.operator_;
    }
    
    private SingleFieldBuilderV3<User, User.Builder, UserOrBuilder> getOperatorFieldBuilder() {
      if (this.operatorBuilder_ == null) {
        this.operatorBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getOperator(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.operator_ = null;
      } 
      return this.operatorBuilder_;
    }
    
    public boolean getIsSetToAdmin() {
      return this.isSetToAdmin_;
    }
    
    public Builder setIsSetToAdmin(boolean value) {
      this.isSetToAdmin_ = value;
      this.bitField0_ |= 0x10;
      onChanged();
      return this;
    }
    
    public Builder clearIsSetToAdmin() {
      this.bitField0_ &= 0xFFFFFFEF;
      this.isSetToAdmin_ = false;
      onChanged();
      return this;
    }
    
    public boolean getIsTopUser() {
      return this.isTopUser_;
    }
    
    public Builder setIsTopUser(boolean value) {
      this.isTopUser_ = value;
      this.bitField0_ |= 0x20;
      onChanged();
      return this;
    }
    
    public Builder clearIsTopUser() {
      this.bitField0_ &= 0xFFFFFFDF;
      this.isTopUser_ = false;
      onChanged();
      return this;
    }
    
    public long getRankScore() {
      return this.rankScore_;
    }
    
    public Builder setRankScore(long value) {
      this.rankScore_ = value;
      this.bitField0_ |= 0x40;
      onChanged();
      return this;
    }
    
    public Builder clearRankScore() {
      this.bitField0_ &= 0xFFFFFFBF;
      this.rankScore_ = 0L;
      onChanged();
      return this;
    }
    
    public long getTopUserNo() {
      return this.topUserNo_;
    }
    
    public Builder setTopUserNo(long value) {
      this.topUserNo_ = value;
      this.bitField0_ |= 0x80;
      onChanged();
      return this;
    }
    
    public Builder clearTopUserNo() {
      this.bitField0_ &= 0xFFFFFF7F;
      this.topUserNo_ = 0L;
      onChanged();
      return this;
    }
    
    public long getEnterType() {
      return this.enterType_;
    }
    
    public Builder setEnterType(long value) {
      this.enterType_ = value;
      this.bitField0_ |= 0x100;
      onChanged();
      return this;
    }
    
    public Builder clearEnterType() {
      this.bitField0_ &= 0xFFFFFEFF;
      this.enterType_ = 0L;
      onChanged();
      return this;
    }
    
    public long getAction() {
      return this.action_;
    }
    
    public Builder setAction(long value) {
      this.action_ = value;
      this.bitField0_ |= 0x200;
      onChanged();
      return this;
    }
    
    public Builder clearAction() {
      this.bitField0_ &= 0xFFFFFDFF;
      this.action_ = 0L;
      onChanged();
      return this;
    }
    
    public String getActionDescription() {
      Object ref = this.actionDescription_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.actionDescription_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getActionDescriptionBytes() {
      Object ref = this.actionDescription_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.actionDescription_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setActionDescription(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.actionDescription_ = value;
      this.bitField0_ |= 0x400;
      onChanged();
      return this;
    }
    
    public Builder clearActionDescription() {
      this.actionDescription_ = MemberMessage.getDefaultInstance().getActionDescription();
      this.bitField0_ &= 0xFFFFFBFF;
      onChanged();
      return this;
    }
    
    public Builder setActionDescriptionBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      MemberMessage.checkByteStringIsUtf8(value);
      this.actionDescription_ = value;
      this.bitField0_ |= 0x400;
      onChanged();
      return this;
    }
    
    public long getUserId() {
      return this.userId_;
    }
    
    public Builder setUserId(long value) {
      this.userId_ = value;
      this.bitField0_ |= 0x800;
      onChanged();
      return this;
    }
    
    public Builder clearUserId() {
      this.bitField0_ &= 0xFFFFF7FF;
      this.userId_ = 0L;
      onChanged();
      return this;
    }
    
    public boolean hasEffectConfig() {
      return ((this.bitField0_ & 0x1000) != 0);
    }
    
    public EffectConfig getEffectConfig() {
      if (this.effectConfigBuilder_ == null)
        return (this.effectConfig_ == null) ? EffectConfig.getDefaultInstance() : this.effectConfig_; 
      return (EffectConfig)this.effectConfigBuilder_.getMessage();
    }
    
    public Builder setEffectConfig(EffectConfig value) {
      if (this.effectConfigBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        this.effectConfig_ = value;
      } else {
        this.effectConfigBuilder_.setMessage((AbstractMessage)value);
      } 
      this.bitField0_ |= 0x1000;
      onChanged();
      return this;
    }
    
    public Builder setEffectConfig(EffectConfig.Builder builderForValue) {
      if (this.effectConfigBuilder_ == null) {
        this.effectConfig_ = builderForValue.build();
      } else {
        this.effectConfigBuilder_.setMessage((AbstractMessage)builderForValue.build());
      } 
      this.bitField0_ |= 0x1000;
      onChanged();
      return this;
    }
    
    public Builder mergeEffectConfig(EffectConfig value) {
      if (this.effectConfigBuilder_ == null) {
        if ((this.bitField0_ & 0x1000) != 0 && this.effectConfig_ != null && this.effectConfig_ != EffectConfig.getDefaultInstance()) {
          getEffectConfigBuilder().mergeFrom(value);
        } else {
          this.effectConfig_ = value;
        } 
      } else {
        this.effectConfigBuilder_.mergeFrom((AbstractMessage)value);
      } 
      if (this.effectConfig_ != null) {
        this.bitField0_ |= 0x1000;
        onChanged();
      } 
      return this;
    }
    
    public Builder clearEffectConfig() {
      this.bitField0_ &= 0xFFFFEFFF;
      this.effectConfig_ = null;
      if (this.effectConfigBuilder_ != null) {
        this.effectConfigBuilder_.dispose();
        this.effectConfigBuilder_ = null;
      } 
      onChanged();
      return this;
    }
    
    public EffectConfig.Builder getEffectConfigBuilder() {
      this.bitField0_ |= 0x1000;
      onChanged();
      return (EffectConfig.Builder)getEffectConfigFieldBuilder().getBuilder();
    }
    
    public EffectConfigOrBuilder getEffectConfigOrBuilder() {
      if (this.effectConfigBuilder_ != null)
        return (EffectConfigOrBuilder)this.effectConfigBuilder_.getMessageOrBuilder(); 
      return (this.effectConfig_ == null) ? EffectConfig.getDefaultInstance() : this.effectConfig_;
    }
    
    private SingleFieldBuilderV3<EffectConfig, EffectConfig.Builder, EffectConfigOrBuilder> getEffectConfigFieldBuilder() {
      if (this.effectConfigBuilder_ == null) {
        this.effectConfigBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getEffectConfig(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.effectConfig_ = null;
      } 
      return this.effectConfigBuilder_;
    }
    
    public String getPopStr() {
      Object ref = this.popStr_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.popStr_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getPopStrBytes() {
      Object ref = this.popStr_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.popStr_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setPopStr(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.popStr_ = value;
      this.bitField0_ |= 0x2000;
      onChanged();
      return this;
    }
    
    public Builder clearPopStr() {
      this.popStr_ = MemberMessage.getDefaultInstance().getPopStr();
      this.bitField0_ &= 0xFFFFDFFF;
      onChanged();
      return this;
    }
    
    public Builder setPopStrBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      MemberMessage.checkByteStringIsUtf8(value);
      this.popStr_ = value;
      this.bitField0_ |= 0x2000;
      onChanged();
      return this;
    }
    
    public boolean hasEnterEffectConfig() {
      return ((this.bitField0_ & 0x4000) != 0);
    }
    
    public EffectConfig getEnterEffectConfig() {
      if (this.enterEffectConfigBuilder_ == null)
        return (this.enterEffectConfig_ == null) ? EffectConfig.getDefaultInstance() : this.enterEffectConfig_; 
      return (EffectConfig)this.enterEffectConfigBuilder_.getMessage();
    }
    
    public Builder setEnterEffectConfig(EffectConfig value) {
      if (this.enterEffectConfigBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        this.enterEffectConfig_ = value;
      } else {
        this.enterEffectConfigBuilder_.setMessage((AbstractMessage)value);
      } 
      this.bitField0_ |= 0x4000;
      onChanged();
      return this;
    }
    
    public Builder setEnterEffectConfig(EffectConfig.Builder builderForValue) {
      if (this.enterEffectConfigBuilder_ == null) {
        this.enterEffectConfig_ = builderForValue.build();
      } else {
        this.enterEffectConfigBuilder_.setMessage((AbstractMessage)builderForValue.build());
      } 
      this.bitField0_ |= 0x4000;
      onChanged();
      return this;
    }
    
    public Builder mergeEnterEffectConfig(EffectConfig value) {
      if (this.enterEffectConfigBuilder_ == null) {
        if ((this.bitField0_ & 0x4000) != 0 && this.enterEffectConfig_ != null && this.enterEffectConfig_ != 
          
          EffectConfig.getDefaultInstance()) {
          getEnterEffectConfigBuilder().mergeFrom(value);
        } else {
          this.enterEffectConfig_ = value;
        } 
      } else {
        this.enterEffectConfigBuilder_.mergeFrom((AbstractMessage)value);
      } 
      if (this.enterEffectConfig_ != null) {
        this.bitField0_ |= 0x4000;
        onChanged();
      } 
      return this;
    }
    
    public Builder clearEnterEffectConfig() {
      this.bitField0_ &= 0xFFFFBFFF;
      this.enterEffectConfig_ = null;
      if (this.enterEffectConfigBuilder_ != null) {
        this.enterEffectConfigBuilder_.dispose();
        this.enterEffectConfigBuilder_ = null;
      } 
      onChanged();
      return this;
    }
    
    public EffectConfig.Builder getEnterEffectConfigBuilder() {
      this.bitField0_ |= 0x4000;
      onChanged();
      return (EffectConfig.Builder)getEnterEffectConfigFieldBuilder().getBuilder();
    }
    
    public EffectConfigOrBuilder getEnterEffectConfigOrBuilder() {
      if (this.enterEffectConfigBuilder_ != null)
        return (EffectConfigOrBuilder)this.enterEffectConfigBuilder_.getMessageOrBuilder(); 
      return (this.enterEffectConfig_ == null) ? 
        EffectConfig.getDefaultInstance() : this.enterEffectConfig_;
    }
    
    private SingleFieldBuilderV3<EffectConfig, EffectConfig.Builder, EffectConfigOrBuilder> getEnterEffectConfigFieldBuilder() {
      if (this.enterEffectConfigBuilder_ == null) {
        this
          
          .enterEffectConfigBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getEnterEffectConfig(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.enterEffectConfig_ = null;
      } 
      return this.enterEffectConfigBuilder_;
    }
    
    public boolean hasBackgroundImage() {
      return ((this.bitField0_ & 0x8000) != 0);
    }
    
    public Image getBackgroundImage() {
      if (this.backgroundImageBuilder_ == null)
        return (this.backgroundImage_ == null) ? Image.getDefaultInstance() : this.backgroundImage_; 
      return (Image)this.backgroundImageBuilder_.getMessage();
    }
    
    public Builder setBackgroundImage(Image value) {
      if (this.backgroundImageBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        this.backgroundImage_ = value;
      } else {
        this.backgroundImageBuilder_.setMessage((AbstractMessage)value);
      } 
      this.bitField0_ |= 0x8000;
      onChanged();
      return this;
    }
    
    public Builder setBackgroundImage(Image.Builder builderForValue) {
      if (this.backgroundImageBuilder_ == null) {
        this.backgroundImage_ = builderForValue.build();
      } else {
        this.backgroundImageBuilder_.setMessage((AbstractMessage)builderForValue.build());
      } 
      this.bitField0_ |= 0x8000;
      onChanged();
      return this;
    }
    
    public Builder mergeBackgroundImage(Image value) {
      if (this.backgroundImageBuilder_ == null) {
        if ((this.bitField0_ & 0x8000) != 0 && this.backgroundImage_ != null && this.backgroundImage_ != 
          
          Image.getDefaultInstance()) {
          getBackgroundImageBuilder().mergeFrom(value);
        } else {
          this.backgroundImage_ = value;
        } 
      } else {
        this.backgroundImageBuilder_.mergeFrom((AbstractMessage)value);
      } 
      if (this.backgroundImage_ != null) {
        this.bitField0_ |= 0x8000;
        onChanged();
      } 
      return this;
    }
    
    public Builder clearBackgroundImage() {
      this.bitField0_ &= 0xFFFF7FFF;
      this.backgroundImage_ = null;
      if (this.backgroundImageBuilder_ != null) {
        this.backgroundImageBuilder_.dispose();
        this.backgroundImageBuilder_ = null;
      } 
      onChanged();
      return this;
    }
    
    public Image.Builder getBackgroundImageBuilder() {
      this.bitField0_ |= 0x8000;
      onChanged();
      return (Image.Builder)getBackgroundImageFieldBuilder().getBuilder();
    }
    
    public ImageOrBuilder getBackgroundImageOrBuilder() {
      if (this.backgroundImageBuilder_ != null)
        return (ImageOrBuilder)this.backgroundImageBuilder_.getMessageOrBuilder(); 
      return (this.backgroundImage_ == null) ? 
        Image.getDefaultInstance() : this.backgroundImage_;
    }
    
    private SingleFieldBuilderV3<Image, Image.Builder, ImageOrBuilder> getBackgroundImageFieldBuilder() {
      if (this.backgroundImageBuilder_ == null) {
        this
          
          .backgroundImageBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getBackgroundImage(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.backgroundImage_ = null;
      } 
      return this.backgroundImageBuilder_;
    }
    
    public boolean hasBackgroundImageV2() {
      return ((this.bitField0_ & 0x10000) != 0);
    }
    
    public Image getBackgroundImageV2() {
      if (this.backgroundImageV2Builder_ == null)
        return (this.backgroundImageV2_ == null) ? Image.getDefaultInstance() : this.backgroundImageV2_; 
      return (Image)this.backgroundImageV2Builder_.getMessage();
    }
    
    public Builder setBackgroundImageV2(Image value) {
      if (this.backgroundImageV2Builder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        this.backgroundImageV2_ = value;
      } else {
        this.backgroundImageV2Builder_.setMessage((AbstractMessage)value);
      } 
      this.bitField0_ |= 0x10000;
      onChanged();
      return this;
    }
    
    public Builder setBackgroundImageV2(Image.Builder builderForValue) {
      if (this.backgroundImageV2Builder_ == null) {
        this.backgroundImageV2_ = builderForValue.build();
      } else {
        this.backgroundImageV2Builder_.setMessage((AbstractMessage)builderForValue.build());
      } 
      this.bitField0_ |= 0x10000;
      onChanged();
      return this;
    }
    
    public Builder mergeBackgroundImageV2(Image value) {
      if (this.backgroundImageV2Builder_ == null) {
        if ((this.bitField0_ & 0x10000) != 0 && this.backgroundImageV2_ != null && this.backgroundImageV2_ != 
          
          Image.getDefaultInstance()) {
          getBackgroundImageV2Builder().mergeFrom(value);
        } else {
          this.backgroundImageV2_ = value;
        } 
      } else {
        this.backgroundImageV2Builder_.mergeFrom((AbstractMessage)value);
      } 
      if (this.backgroundImageV2_ != null) {
        this.bitField0_ |= 0x10000;
        onChanged();
      } 
      return this;
    }
    
    public Builder clearBackgroundImageV2() {
      this.bitField0_ &= 0xFFFEFFFF;
      this.backgroundImageV2_ = null;
      if (this.backgroundImageV2Builder_ != null) {
        this.backgroundImageV2Builder_.dispose();
        this.backgroundImageV2Builder_ = null;
      } 
      onChanged();
      return this;
    }
    
    public Image.Builder getBackgroundImageV2Builder() {
      this.bitField0_ |= 0x10000;
      onChanged();
      return (Image.Builder)getBackgroundImageV2FieldBuilder().getBuilder();
    }
    
    public ImageOrBuilder getBackgroundImageV2OrBuilder() {
      if (this.backgroundImageV2Builder_ != null)
        return (ImageOrBuilder)this.backgroundImageV2Builder_.getMessageOrBuilder(); 
      return (this.backgroundImageV2_ == null) ? 
        Image.getDefaultInstance() : this.backgroundImageV2_;
    }
    
    private SingleFieldBuilderV3<Image, Image.Builder, ImageOrBuilder> getBackgroundImageV2FieldBuilder() {
      if (this.backgroundImageV2Builder_ == null) {
        this
          
          .backgroundImageV2Builder_ = new SingleFieldBuilderV3((AbstractMessage)getBackgroundImageV2(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.backgroundImageV2_ = null;
      } 
      return this.backgroundImageV2Builder_;
    }
    
    public boolean hasAnchorDisplayText() {
      return ((this.bitField0_ & 0x20000) != 0);
    }
    
    public Text getAnchorDisplayText() {
      if (this.anchorDisplayTextBuilder_ == null)
        return (this.anchorDisplayText_ == null) ? Text.getDefaultInstance() : this.anchorDisplayText_; 
      return (Text)this.anchorDisplayTextBuilder_.getMessage();
    }
    
    public Builder setAnchorDisplayText(Text value) {
      if (this.anchorDisplayTextBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        this.anchorDisplayText_ = value;
      } else {
        this.anchorDisplayTextBuilder_.setMessage((AbstractMessage)value);
      } 
      this.bitField0_ |= 0x20000;
      onChanged();
      return this;
    }
    
    public Builder setAnchorDisplayText(Text.Builder builderForValue) {
      if (this.anchorDisplayTextBuilder_ == null) {
        this.anchorDisplayText_ = builderForValue.build();
      } else {
        this.anchorDisplayTextBuilder_.setMessage((AbstractMessage)builderForValue.build());
      } 
      this.bitField0_ |= 0x20000;
      onChanged();
      return this;
    }
    
    public Builder mergeAnchorDisplayText(Text value) {
      if (this.anchorDisplayTextBuilder_ == null) {
        if ((this.bitField0_ & 0x20000) != 0 && this.anchorDisplayText_ != null && this.anchorDisplayText_ != 
          
          Text.getDefaultInstance()) {
          getAnchorDisplayTextBuilder().mergeFrom(value);
        } else {
          this.anchorDisplayText_ = value;
        } 
      } else {
        this.anchorDisplayTextBuilder_.mergeFrom((AbstractMessage)value);
      } 
      if (this.anchorDisplayText_ != null) {
        this.bitField0_ |= 0x20000;
        onChanged();
      } 
      return this;
    }
    
    public Builder clearAnchorDisplayText() {
      this.bitField0_ &= 0xFFFDFFFF;
      this.anchorDisplayText_ = null;
      if (this.anchorDisplayTextBuilder_ != null) {
        this.anchorDisplayTextBuilder_.dispose();
        this.anchorDisplayTextBuilder_ = null;
      } 
      onChanged();
      return this;
    }
    
    public Text.Builder getAnchorDisplayTextBuilder() {
      this.bitField0_ |= 0x20000;
      onChanged();
      return (Text.Builder)getAnchorDisplayTextFieldBuilder().getBuilder();
    }
    
    public TextOrBuilder getAnchorDisplayTextOrBuilder() {
      if (this.anchorDisplayTextBuilder_ != null)
        return (TextOrBuilder)this.anchorDisplayTextBuilder_.getMessageOrBuilder(); 
      return (this.anchorDisplayText_ == null) ? 
        Text.getDefaultInstance() : this.anchorDisplayText_;
    }
    
    private SingleFieldBuilderV3<Text, Text.Builder, TextOrBuilder> getAnchorDisplayTextFieldBuilder() {
      if (this.anchorDisplayTextBuilder_ == null) {
        this
          
          .anchorDisplayTextBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getAnchorDisplayText(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.anchorDisplayText_ = null;
      } 
      return this.anchorDisplayTextBuilder_;
    }
    
    public boolean hasPublicAreaCommon() {
      return ((this.bitField0_ & 0x40000) != 0);
    }
    
    public PublicAreaCommon getPublicAreaCommon() {
      if (this.publicAreaCommonBuilder_ == null)
        return (this.publicAreaCommon_ == null) ? PublicAreaCommon.getDefaultInstance() : this.publicAreaCommon_; 
      return (PublicAreaCommon)this.publicAreaCommonBuilder_.getMessage();
    }
    
    public Builder setPublicAreaCommon(PublicAreaCommon value) {
      if (this.publicAreaCommonBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        this.publicAreaCommon_ = value;
      } else {
        this.publicAreaCommonBuilder_.setMessage((AbstractMessage)value);
      } 
      this.bitField0_ |= 0x40000;
      onChanged();
      return this;
    }
    
    public Builder setPublicAreaCommon(PublicAreaCommon.Builder builderForValue) {
      if (this.publicAreaCommonBuilder_ == null) {
        this.publicAreaCommon_ = builderForValue.build();
      } else {
        this.publicAreaCommonBuilder_.setMessage((AbstractMessage)builderForValue.build());
      } 
      this.bitField0_ |= 0x40000;
      onChanged();
      return this;
    }
    
    public Builder mergePublicAreaCommon(PublicAreaCommon value) {
      if (this.publicAreaCommonBuilder_ == null) {
        if ((this.bitField0_ & 0x40000) != 0 && this.publicAreaCommon_ != null && this.publicAreaCommon_ != 
          
          PublicAreaCommon.getDefaultInstance()) {
          getPublicAreaCommonBuilder().mergeFrom(value);
        } else {
          this.publicAreaCommon_ = value;
        } 
      } else {
        this.publicAreaCommonBuilder_.mergeFrom((AbstractMessage)value);
      } 
      if (this.publicAreaCommon_ != null) {
        this.bitField0_ |= 0x40000;
        onChanged();
      } 
      return this;
    }
    
    public Builder clearPublicAreaCommon() {
      this.bitField0_ &= 0xFFFBFFFF;
      this.publicAreaCommon_ = null;
      if (this.publicAreaCommonBuilder_ != null) {
        this.publicAreaCommonBuilder_.dispose();
        this.publicAreaCommonBuilder_ = null;
      } 
      onChanged();
      return this;
    }
    
    public PublicAreaCommon.Builder getPublicAreaCommonBuilder() {
      this.bitField0_ |= 0x40000;
      onChanged();
      return (PublicAreaCommon.Builder)getPublicAreaCommonFieldBuilder().getBuilder();
    }
    
    public PublicAreaCommonOrBuilder getPublicAreaCommonOrBuilder() {
      if (this.publicAreaCommonBuilder_ != null)
        return (PublicAreaCommonOrBuilder)this.publicAreaCommonBuilder_.getMessageOrBuilder(); 
      return (this.publicAreaCommon_ == null) ? 
        PublicAreaCommon.getDefaultInstance() : this.publicAreaCommon_;
    }
    
    private SingleFieldBuilderV3<PublicAreaCommon, PublicAreaCommon.Builder, PublicAreaCommonOrBuilder> getPublicAreaCommonFieldBuilder() {
      if (this.publicAreaCommonBuilder_ == null) {
        this
          
          .publicAreaCommonBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getPublicAreaCommon(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.publicAreaCommon_ = null;
      } 
      return this.publicAreaCommonBuilder_;
    }
    
    public long getUserEnterTipType() {
      return this.userEnterTipType_;
    }
    
    public Builder setUserEnterTipType(long value) {
      this.userEnterTipType_ = value;
      this.bitField0_ |= 0x80000;
      onChanged();
      return this;
    }
    
    public Builder clearUserEnterTipType() {
      this.bitField0_ &= 0xFFF7FFFF;
      this.userEnterTipType_ = 0L;
      onChanged();
      return this;
    }
    
    public long getAnchorEnterTipType() {
      return this.anchorEnterTipType_;
    }
    
    public Builder setAnchorEnterTipType(long value) {
      this.anchorEnterTipType_ = value;
      this.bitField0_ |= 0x100000;
      onChanged();
      return this;
    }
    
    public Builder clearAnchorEnterTipType() {
      this.bitField0_ &= 0xFFEFFFFF;
      this.anchorEnterTipType_ = 0L;
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
  
  private static final MemberMessage DEFAULT_INSTANCE = new MemberMessage();
  
  public static MemberMessage getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }
  
  private static final Parser<MemberMessage> PARSER = (Parser<MemberMessage>)new AbstractParser<MemberMessage>() {
      public MemberMessage parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        MemberMessage.Builder builder = MemberMessage.newBuilder();
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
  
  public static Parser<MemberMessage> parser() {
    return PARSER;
  }
  
  public Parser<MemberMessage> getParserForType() {
    return PARSER;
  }
  
  public MemberMessage getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }
}
