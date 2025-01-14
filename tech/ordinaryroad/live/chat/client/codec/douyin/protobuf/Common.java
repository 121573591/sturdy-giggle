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

public final class Common extends GeneratedMessageV3 implements CommonOrBuilder {
  private static final long serialVersionUID = 0L;
  
  private int bitField0_;
  
  public static final int METHOD_FIELD_NUMBER = 1;
  
  private volatile Object method_;
  
  public static final int MSG_ID_FIELD_NUMBER = 2;
  
  private long msgId_;
  
  public static final int ROOM_ID_FIELD_NUMBER = 3;
  
  private long roomId_;
  
  public static final int CREATE_TIME_FIELD_NUMBER = 4;
  
  private long createTime_;
  
  public static final int MONITOR_FIELD_NUMBER = 5;
  
  private int monitor_;
  
  public static final int IS_SHOW_MSG_FIELD_NUMBER = 6;
  
  private boolean isShowMsg_;
  
  public static final int DESCRIBE_FIELD_NUMBER = 7;
  
  private volatile Object describe_;
  
  public static final int DISPLAY_TEXT_FIELD_NUMBER = 8;
  
  private Text displayText_;
  
  public static final int FOLD_TYPE_FIELD_NUMBER = 9;
  
  private long foldType_;
  
  public static final int ANCHOR_FOLD_TYPE_FIELD_NUMBER = 10;
  
  private long anchorFoldType_;
  
  public static final int PRIORITYSCORE_FIELD_NUMBER = 11;
  
  private long priorityScore_;
  
  public static final int LOGID_FIELD_NUMBER = 12;
  
  private volatile Object logId_;
  
  public static final int MSGPROCESSFILTERK_FIELD_NUMBER = 13;
  
  private volatile Object msgProcessFilterK_;
  
  public static final int MSGPROCESSFILTERV_FIELD_NUMBER = 14;
  
  private volatile Object msgProcessFilterV_;
  
  public static final int USER_FIELD_NUMBER = 15;
  
  private User user_;
  
  public static final int ANCHOR_FOLD_TYPE_V2_FIELD_NUMBER = 17;
  
  private long anchorFoldTypeV2_;
  
  public static final int PROCESS_AT_SEI_TIME_MS_FIELD_NUMBER = 18;
  
  private long processAtSeiTimeMs_;
  
  public static final int RANDOM_DISPATCH_MS_FIELD_NUMBER = 19;
  
  private long randomDispatchMs_;
  
  public static final int IS_DISPATCH_FIELD_NUMBER = 20;
  
  private boolean isDispatch_;
  
  public static final int CHANNEL_ID_FIELD_NUMBER = 21;
  
  private long channelId_;
  
  public static final int DIFF_SEI2ABS_SECOND_FIELD_NUMBER = 22;
  
  private long diffSei2AbsSecond_;
  
  public static final int ANCHOR_FOLD_DURATION_FIELD_NUMBER = 23;
  
  private long anchorFoldDuration_;
  
  public static final int APP_ID_FIELD_NUMBER = 24;
  
  private long appId_;
  
  private byte memoizedIsInitialized;
  
  private Common(GeneratedMessageV3.Builder<?> builder) {
    super(builder);
    this.method_ = "";
    this.msgId_ = 0L;
    this.roomId_ = 0L;
    this.createTime_ = 0L;
    this.monitor_ = 0;
    this.isShowMsg_ = false;
    this.describe_ = "";
    this.foldType_ = 0L;
    this.anchorFoldType_ = 0L;
    this.priorityScore_ = 0L;
    this.logId_ = "";
    this.msgProcessFilterK_ = "";
    this.msgProcessFilterV_ = "";
    this.anchorFoldTypeV2_ = 0L;
    this.processAtSeiTimeMs_ = 0L;
    this.randomDispatchMs_ = 0L;
    this.isDispatch_ = false;
    this.channelId_ = 0L;
    this.diffSei2AbsSecond_ = 0L;
    this.anchorFoldDuration_ = 0L;
    this.appId_ = 0L;
    this.memoizedIsInitialized = -1;
  }
  
  private Common() {
    this.method_ = "";
    this.msgId_ = 0L;
    this.roomId_ = 0L;
    this.createTime_ = 0L;
    this.monitor_ = 0;
    this.isShowMsg_ = false;
    this.describe_ = "";
    this.foldType_ = 0L;
    this.anchorFoldType_ = 0L;
    this.priorityScore_ = 0L;
    this.logId_ = "";
    this.msgProcessFilterK_ = "";
    this.msgProcessFilterV_ = "";
    this.anchorFoldTypeV2_ = 0L;
    this.processAtSeiTimeMs_ = 0L;
    this.randomDispatchMs_ = 0L;
    this.isDispatch_ = false;
    this.channelId_ = 0L;
    this.diffSei2AbsSecond_ = 0L;
    this.anchorFoldDuration_ = 0L;
    this.appId_ = 0L;
    this.memoizedIsInitialized = -1;
    this.method_ = "";
    this.describe_ = "";
    this.logId_ = "";
    this.msgProcessFilterK_ = "";
    this.msgProcessFilterV_ = "";
  }
  
  protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
    return new Common();
  }
  
  public static final Descriptors.Descriptor getDescriptor() {
    return Douyin.internal_static_Common_descriptor;
  }
  
  protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
    return Douyin.internal_static_Common_fieldAccessorTable.ensureFieldAccessorsInitialized(Common.class, Builder.class);
  }
  
  public String getMethod() {
    Object ref = this.method_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.method_ = s;
    return s;
  }
  
  public ByteString getMethodBytes() {
    Object ref = this.method_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.method_ = b;
      return b;
    } 
    return (ByteString)ref;
  }
  
  public long getMsgId() {
    return this.msgId_;
  }
  
  public long getRoomId() {
    return this.roomId_;
  }
  
  public long getCreateTime() {
    return this.createTime_;
  }
  
  public int getMonitor() {
    return this.monitor_;
  }
  
  public boolean getIsShowMsg() {
    return this.isShowMsg_;
  }
  
  public String getDescribe() {
    Object ref = this.describe_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.describe_ = s;
    return s;
  }
  
  public ByteString getDescribeBytes() {
    Object ref = this.describe_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.describe_ = b;
      return b;
    } 
    return (ByteString)ref;
  }
  
  public boolean hasDisplayText() {
    return ((this.bitField0_ & 0x1) != 0);
  }
  
  public Text getDisplayText() {
    return (this.displayText_ == null) ? Text.getDefaultInstance() : this.displayText_;
  }
  
  public TextOrBuilder getDisplayTextOrBuilder() {
    return (this.displayText_ == null) ? Text.getDefaultInstance() : this.displayText_;
  }
  
  public long getFoldType() {
    return this.foldType_;
  }
  
  public long getAnchorFoldType() {
    return this.anchorFoldType_;
  }
  
  public long getPriorityScore() {
    return this.priorityScore_;
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
  
  public String getMsgProcessFilterK() {
    Object ref = this.msgProcessFilterK_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.msgProcessFilterK_ = s;
    return s;
  }
  
  public ByteString getMsgProcessFilterKBytes() {
    Object ref = this.msgProcessFilterK_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.msgProcessFilterK_ = b;
      return b;
    } 
    return (ByteString)ref;
  }
  
  public String getMsgProcessFilterV() {
    Object ref = this.msgProcessFilterV_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.msgProcessFilterV_ = s;
    return s;
  }
  
  public ByteString getMsgProcessFilterVBytes() {
    Object ref = this.msgProcessFilterV_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.msgProcessFilterV_ = b;
      return b;
    } 
    return (ByteString)ref;
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
  
  public long getAnchorFoldTypeV2() {
    return this.anchorFoldTypeV2_;
  }
  
  public long getProcessAtSeiTimeMs() {
    return this.processAtSeiTimeMs_;
  }
  
  public long getRandomDispatchMs() {
    return this.randomDispatchMs_;
  }
  
  public boolean getIsDispatch() {
    return this.isDispatch_;
  }
  
  public long getChannelId() {
    return this.channelId_;
  }
  
  public long getDiffSei2AbsSecond() {
    return this.diffSei2AbsSecond_;
  }
  
  public long getAnchorFoldDuration() {
    return this.anchorFoldDuration_;
  }
  
  public long getAppId() {
    return this.appId_;
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
    if (!GeneratedMessageV3.isStringEmpty(this.method_))
      GeneratedMessageV3.writeString(output, 1, this.method_); 
    if (this.msgId_ != 0L)
      output.writeUInt64(2, this.msgId_); 
    if (this.roomId_ != 0L)
      output.writeUInt64(3, this.roomId_); 
    if (this.createTime_ != 0L)
      output.writeUInt64(4, this.createTime_); 
    if (this.monitor_ != 0)
      output.writeUInt32(5, this.monitor_); 
    if (this.isShowMsg_)
      output.writeBool(6, this.isShowMsg_); 
    if (!GeneratedMessageV3.isStringEmpty(this.describe_))
      GeneratedMessageV3.writeString(output, 7, this.describe_); 
    if ((this.bitField0_ & 0x1) != 0)
      output.writeMessage(8, (MessageLite)getDisplayText()); 
    if (this.foldType_ != 0L)
      output.writeUInt64(9, this.foldType_); 
    if (this.anchorFoldType_ != 0L)
      output.writeUInt64(10, this.anchorFoldType_); 
    if (this.priorityScore_ != 0L)
      output.writeUInt64(11, this.priorityScore_); 
    if (!GeneratedMessageV3.isStringEmpty(this.logId_))
      GeneratedMessageV3.writeString(output, 12, this.logId_); 
    if (!GeneratedMessageV3.isStringEmpty(this.msgProcessFilterK_))
      GeneratedMessageV3.writeString(output, 13, this.msgProcessFilterK_); 
    if (!GeneratedMessageV3.isStringEmpty(this.msgProcessFilterV_))
      GeneratedMessageV3.writeString(output, 14, this.msgProcessFilterV_); 
    if ((this.bitField0_ & 0x2) != 0)
      output.writeMessage(15, (MessageLite)getUser()); 
    if (this.anchorFoldTypeV2_ != 0L)
      output.writeUInt64(17, this.anchorFoldTypeV2_); 
    if (this.processAtSeiTimeMs_ != 0L)
      output.writeUInt64(18, this.processAtSeiTimeMs_); 
    if (this.randomDispatchMs_ != 0L)
      output.writeUInt64(19, this.randomDispatchMs_); 
    if (this.isDispatch_)
      output.writeBool(20, this.isDispatch_); 
    if (this.channelId_ != 0L)
      output.writeUInt64(21, this.channelId_); 
    if (this.diffSei2AbsSecond_ != 0L)
      output.writeUInt64(22, this.diffSei2AbsSecond_); 
    if (this.anchorFoldDuration_ != 0L)
      output.writeUInt64(23, this.anchorFoldDuration_); 
    if (this.appId_ != 0L)
      output.writeUInt64(24, this.appId_); 
    getUnknownFields().writeTo(output);
  }
  
  public int getSerializedSize() {
    int size = this.memoizedSize;
    if (size != -1)
      return size; 
    size = 0;
    if (!GeneratedMessageV3.isStringEmpty(this.method_))
      size += GeneratedMessageV3.computeStringSize(1, this.method_); 
    if (this.msgId_ != 0L)
      size += 
        CodedOutputStream.computeUInt64Size(2, this.msgId_); 
    if (this.roomId_ != 0L)
      size += 
        CodedOutputStream.computeUInt64Size(3, this.roomId_); 
    if (this.createTime_ != 0L)
      size += 
        CodedOutputStream.computeUInt64Size(4, this.createTime_); 
    if (this.monitor_ != 0)
      size += 
        CodedOutputStream.computeUInt32Size(5, this.monitor_); 
    if (this.isShowMsg_)
      size += 
        CodedOutputStream.computeBoolSize(6, this.isShowMsg_); 
    if (!GeneratedMessageV3.isStringEmpty(this.describe_))
      size += GeneratedMessageV3.computeStringSize(7, this.describe_); 
    if ((this.bitField0_ & 0x1) != 0)
      size += 
        CodedOutputStream.computeMessageSize(8, (MessageLite)getDisplayText()); 
    if (this.foldType_ != 0L)
      size += 
        CodedOutputStream.computeUInt64Size(9, this.foldType_); 
    if (this.anchorFoldType_ != 0L)
      size += 
        CodedOutputStream.computeUInt64Size(10, this.anchorFoldType_); 
    if (this.priorityScore_ != 0L)
      size += 
        CodedOutputStream.computeUInt64Size(11, this.priorityScore_); 
    if (!GeneratedMessageV3.isStringEmpty(this.logId_))
      size += GeneratedMessageV3.computeStringSize(12, this.logId_); 
    if (!GeneratedMessageV3.isStringEmpty(this.msgProcessFilterK_))
      size += GeneratedMessageV3.computeStringSize(13, this.msgProcessFilterK_); 
    if (!GeneratedMessageV3.isStringEmpty(this.msgProcessFilterV_))
      size += GeneratedMessageV3.computeStringSize(14, this.msgProcessFilterV_); 
    if ((this.bitField0_ & 0x2) != 0)
      size += 
        CodedOutputStream.computeMessageSize(15, (MessageLite)getUser()); 
    if (this.anchorFoldTypeV2_ != 0L)
      size += 
        CodedOutputStream.computeUInt64Size(17, this.anchorFoldTypeV2_); 
    if (this.processAtSeiTimeMs_ != 0L)
      size += 
        CodedOutputStream.computeUInt64Size(18, this.processAtSeiTimeMs_); 
    if (this.randomDispatchMs_ != 0L)
      size += 
        CodedOutputStream.computeUInt64Size(19, this.randomDispatchMs_); 
    if (this.isDispatch_)
      size += 
        CodedOutputStream.computeBoolSize(20, this.isDispatch_); 
    if (this.channelId_ != 0L)
      size += 
        CodedOutputStream.computeUInt64Size(21, this.channelId_); 
    if (this.diffSei2AbsSecond_ != 0L)
      size += 
        CodedOutputStream.computeUInt64Size(22, this.diffSei2AbsSecond_); 
    if (this.anchorFoldDuration_ != 0L)
      size += 
        CodedOutputStream.computeUInt64Size(23, this.anchorFoldDuration_); 
    if (this.appId_ != 0L)
      size += 
        CodedOutputStream.computeUInt64Size(24, this.appId_); 
    size += getUnknownFields().getSerializedSize();
    this.memoizedSize = size;
    return size;
  }
  
  public boolean equals(Object obj) {
    if (obj == this)
      return true; 
    if (!(obj instanceof Common))
      return super.equals(obj); 
    Common other = (Common)obj;
    if (!getMethod().equals(other.getMethod()))
      return false; 
    if (getMsgId() != other
      .getMsgId())
      return false; 
    if (getRoomId() != other
      .getRoomId())
      return false; 
    if (getCreateTime() != other
      .getCreateTime())
      return false; 
    if (getMonitor() != other
      .getMonitor())
      return false; 
    if (getIsShowMsg() != other
      .getIsShowMsg())
      return false; 
    if (!getDescribe().equals(other.getDescribe()))
      return false; 
    if (hasDisplayText() != other.hasDisplayText())
      return false; 
    if (hasDisplayText() && 
      
      !getDisplayText().equals(other.getDisplayText()))
      return false; 
    if (getFoldType() != other
      .getFoldType())
      return false; 
    if (getAnchorFoldType() != other
      .getAnchorFoldType())
      return false; 
    if (getPriorityScore() != other
      .getPriorityScore())
      return false; 
    if (!getLogId().equals(other.getLogId()))
      return false; 
    if (!getMsgProcessFilterK().equals(other.getMsgProcessFilterK()))
      return false; 
    if (!getMsgProcessFilterV().equals(other.getMsgProcessFilterV()))
      return false; 
    if (hasUser() != other.hasUser())
      return false; 
    if (hasUser() && 
      
      !getUser().equals(other.getUser()))
      return false; 
    if (getAnchorFoldTypeV2() != other
      .getAnchorFoldTypeV2())
      return false; 
    if (getProcessAtSeiTimeMs() != other
      .getProcessAtSeiTimeMs())
      return false; 
    if (getRandomDispatchMs() != other
      .getRandomDispatchMs())
      return false; 
    if (getIsDispatch() != other
      .getIsDispatch())
      return false; 
    if (getChannelId() != other
      .getChannelId())
      return false; 
    if (getDiffSei2AbsSecond() != other
      .getDiffSei2AbsSecond())
      return false; 
    if (getAnchorFoldDuration() != other
      .getAnchorFoldDuration())
      return false; 
    if (getAppId() != other
      .getAppId())
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
    hash = 53 * hash + getMethod().hashCode();
    hash = 37 * hash + 2;
    hash = 53 * hash + Internal.hashLong(
        getMsgId());
    hash = 37 * hash + 3;
    hash = 53 * hash + Internal.hashLong(
        getRoomId());
    hash = 37 * hash + 4;
    hash = 53 * hash + Internal.hashLong(
        getCreateTime());
    hash = 37 * hash + 5;
    hash = 53 * hash + getMonitor();
    hash = 37 * hash + 6;
    hash = 53 * hash + Internal.hashBoolean(
        getIsShowMsg());
    hash = 37 * hash + 7;
    hash = 53 * hash + getDescribe().hashCode();
    if (hasDisplayText()) {
      hash = 37 * hash + 8;
      hash = 53 * hash + getDisplayText().hashCode();
    } 
    hash = 37 * hash + 9;
    hash = 53 * hash + Internal.hashLong(
        getFoldType());
    hash = 37 * hash + 10;
    hash = 53 * hash + Internal.hashLong(
        getAnchorFoldType());
    hash = 37 * hash + 11;
    hash = 53 * hash + Internal.hashLong(
        getPriorityScore());
    hash = 37 * hash + 12;
    hash = 53 * hash + getLogId().hashCode();
    hash = 37 * hash + 13;
    hash = 53 * hash + getMsgProcessFilterK().hashCode();
    hash = 37 * hash + 14;
    hash = 53 * hash + getMsgProcessFilterV().hashCode();
    if (hasUser()) {
      hash = 37 * hash + 15;
      hash = 53 * hash + getUser().hashCode();
    } 
    hash = 37 * hash + 17;
    hash = 53 * hash + Internal.hashLong(
        getAnchorFoldTypeV2());
    hash = 37 * hash + 18;
    hash = 53 * hash + Internal.hashLong(
        getProcessAtSeiTimeMs());
    hash = 37 * hash + 19;
    hash = 53 * hash + Internal.hashLong(
        getRandomDispatchMs());
    hash = 37 * hash + 20;
    hash = 53 * hash + Internal.hashBoolean(
        getIsDispatch());
    hash = 37 * hash + 21;
    hash = 53 * hash + Internal.hashLong(
        getChannelId());
    hash = 37 * hash + 22;
    hash = 53 * hash + Internal.hashLong(
        getDiffSei2AbsSecond());
    hash = 37 * hash + 23;
    hash = 53 * hash + Internal.hashLong(
        getAnchorFoldDuration());
    hash = 37 * hash + 24;
    hash = 53 * hash + Internal.hashLong(
        getAppId());
    hash = 29 * hash + getUnknownFields().hashCode();
    this.memoizedHashCode = hash;
    return hash;
  }
  
  public static Common parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
    return (Common)PARSER.parseFrom(data);
  }
  
  public static Common parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (Common)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static Common parseFrom(ByteString data) throws InvalidProtocolBufferException {
    return (Common)PARSER.parseFrom(data);
  }
  
  public static Common parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (Common)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static Common parseFrom(byte[] data) throws InvalidProtocolBufferException {
    return (Common)PARSER.parseFrom(data);
  }
  
  public static Common parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (Common)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static Common parseFrom(InputStream input) throws IOException {
    return 
      (Common)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static Common parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (Common)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static Common parseDelimitedFrom(InputStream input) throws IOException {
    return 
      (Common)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
  }
  
  public static Common parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (Common)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static Common parseFrom(CodedInputStream input) throws IOException {
    return 
      (Common)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static Common parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (Common)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public Builder newBuilderForType() {
    return newBuilder();
  }
  
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  
  public static Builder newBuilder(Common prototype) {
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
  
  public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements CommonOrBuilder {
    private int bitField0_;
    
    private Object method_;
    
    private long msgId_;
    
    private long roomId_;
    
    private long createTime_;
    
    private int monitor_;
    
    private boolean isShowMsg_;
    
    private Object describe_;
    
    private Text displayText_;
    
    private SingleFieldBuilderV3<Text, Text.Builder, TextOrBuilder> displayTextBuilder_;
    
    private long foldType_;
    
    private long anchorFoldType_;
    
    private long priorityScore_;
    
    private Object logId_;
    
    private Object msgProcessFilterK_;
    
    private Object msgProcessFilterV_;
    
    private User user_;
    
    private SingleFieldBuilderV3<User, User.Builder, UserOrBuilder> userBuilder_;
    
    private long anchorFoldTypeV2_;
    
    private long processAtSeiTimeMs_;
    
    private long randomDispatchMs_;
    
    private boolean isDispatch_;
    
    private long channelId_;
    
    private long diffSei2AbsSecond_;
    
    private long anchorFoldDuration_;
    
    private long appId_;
    
    public static final Descriptors.Descriptor getDescriptor() {
      return Douyin.internal_static_Common_descriptor;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return Douyin.internal_static_Common_fieldAccessorTable
        .ensureFieldAccessorsInitialized(Common.class, Builder.class);
    }
    
    private Builder() {
      this.method_ = "";
      this.describe_ = "";
      this.logId_ = "";
      this.msgProcessFilterK_ = "";
      this.msgProcessFilterV_ = "";
      maybeForceBuilderInitialization();
    }
    
    private Builder(GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      this.method_ = "";
      this.describe_ = "";
      this.logId_ = "";
      this.msgProcessFilterK_ = "";
      this.msgProcessFilterV_ = "";
      maybeForceBuilderInitialization();
    }
    
    private void maybeForceBuilderInitialization() {
      if (Common.alwaysUseFieldBuilders) {
        getDisplayTextFieldBuilder();
        getUserFieldBuilder();
      } 
    }
    
    public Builder clear() {
      super.clear();
      this.bitField0_ = 0;
      this.method_ = "";
      this.msgId_ = 0L;
      this.roomId_ = 0L;
      this.createTime_ = 0L;
      this.monitor_ = 0;
      this.isShowMsg_ = false;
      this.describe_ = "";
      this.displayText_ = null;
      if (this.displayTextBuilder_ != null) {
        this.displayTextBuilder_.dispose();
        this.displayTextBuilder_ = null;
      } 
      this.foldType_ = 0L;
      this.anchorFoldType_ = 0L;
      this.priorityScore_ = 0L;
      this.logId_ = "";
      this.msgProcessFilterK_ = "";
      this.msgProcessFilterV_ = "";
      this.user_ = null;
      if (this.userBuilder_ != null) {
        this.userBuilder_.dispose();
        this.userBuilder_ = null;
      } 
      this.anchorFoldTypeV2_ = 0L;
      this.processAtSeiTimeMs_ = 0L;
      this.randomDispatchMs_ = 0L;
      this.isDispatch_ = false;
      this.channelId_ = 0L;
      this.diffSei2AbsSecond_ = 0L;
      this.anchorFoldDuration_ = 0L;
      this.appId_ = 0L;
      return this;
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
      return Douyin.internal_static_Common_descriptor;
    }
    
    public Common getDefaultInstanceForType() {
      return Common.getDefaultInstance();
    }
    
    public Common build() {
      Common result = buildPartial();
      if (!result.isInitialized())
        throw newUninitializedMessageException(result); 
      return result;
    }
    
    public Common buildPartial() {
      Common result = new Common(this);
      if (this.bitField0_ != 0)
        buildPartial0(result); 
      onBuilt();
      return result;
    }
    
    private void buildPartial0(Common result) {
      int from_bitField0_ = this.bitField0_;
      if ((from_bitField0_ & 0x1) != 0)
        result.method_ = this.method_; 
      if ((from_bitField0_ & 0x2) != 0)
        result.msgId_ = this.msgId_; 
      if ((from_bitField0_ & 0x4) != 0)
        result.roomId_ = this.roomId_; 
      if ((from_bitField0_ & 0x8) != 0)
        result.createTime_ = this.createTime_; 
      if ((from_bitField0_ & 0x10) != 0)
        result.monitor_ = this.monitor_; 
      if ((from_bitField0_ & 0x20) != 0)
        result.isShowMsg_ = this.isShowMsg_; 
      if ((from_bitField0_ & 0x40) != 0)
        result.describe_ = this.describe_; 
      int to_bitField0_ = 0;
      if ((from_bitField0_ & 0x80) != 0) {
        result.displayText_ = (this.displayTextBuilder_ == null) ? this.displayText_ : (Text)this.displayTextBuilder_.build();
        to_bitField0_ |= 0x1;
      } 
      if ((from_bitField0_ & 0x100) != 0)
        result.foldType_ = this.foldType_; 
      if ((from_bitField0_ & 0x200) != 0)
        result.anchorFoldType_ = this.anchorFoldType_; 
      if ((from_bitField0_ & 0x400) != 0)
        result.priorityScore_ = this.priorityScore_; 
      if ((from_bitField0_ & 0x800) != 0)
        result.logId_ = this.logId_; 
      if ((from_bitField0_ & 0x1000) != 0)
        result.msgProcessFilterK_ = this.msgProcessFilterK_; 
      if ((from_bitField0_ & 0x2000) != 0)
        result.msgProcessFilterV_ = this.msgProcessFilterV_; 
      if ((from_bitField0_ & 0x4000) != 0) {
        result.user_ = (this.userBuilder_ == null) ? this.user_ : (User)this.userBuilder_.build();
        to_bitField0_ |= 0x2;
      } 
      if ((from_bitField0_ & 0x8000) != 0)
        result.anchorFoldTypeV2_ = this.anchorFoldTypeV2_; 
      if ((from_bitField0_ & 0x10000) != 0)
        result.processAtSeiTimeMs_ = this.processAtSeiTimeMs_; 
      if ((from_bitField0_ & 0x20000) != 0)
        result.randomDispatchMs_ = this.randomDispatchMs_; 
      if ((from_bitField0_ & 0x40000) != 0)
        result.isDispatch_ = this.isDispatch_; 
      if ((from_bitField0_ & 0x80000) != 0)
        result.channelId_ = this.channelId_; 
      if ((from_bitField0_ & 0x100000) != 0)
        result.diffSei2AbsSecond_ = this.diffSei2AbsSecond_; 
      if ((from_bitField0_ & 0x200000) != 0)
        result.anchorFoldDuration_ = this.anchorFoldDuration_; 
      if ((from_bitField0_ & 0x400000) != 0)
        result.appId_ = this.appId_; 
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
      if (other instanceof Common)
        return mergeFrom((Common)other); 
      super.mergeFrom(other);
      return this;
    }
    
    public Builder mergeFrom(Common other) {
      if (other == Common.getDefaultInstance())
        return this; 
      if (!other.getMethod().isEmpty()) {
        this.method_ = other.method_;
        this.bitField0_ |= 0x1;
        onChanged();
      } 
      if (other.getMsgId() != 0L)
        setMsgId(other.getMsgId()); 
      if (other.getRoomId() != 0L)
        setRoomId(other.getRoomId()); 
      if (other.getCreateTime() != 0L)
        setCreateTime(other.getCreateTime()); 
      if (other.getMonitor() != 0)
        setMonitor(other.getMonitor()); 
      if (other.getIsShowMsg())
        setIsShowMsg(other.getIsShowMsg()); 
      if (!other.getDescribe().isEmpty()) {
        this.describe_ = other.describe_;
        this.bitField0_ |= 0x40;
        onChanged();
      } 
      if (other.hasDisplayText())
        mergeDisplayText(other.getDisplayText()); 
      if (other.getFoldType() != 0L)
        setFoldType(other.getFoldType()); 
      if (other.getAnchorFoldType() != 0L)
        setAnchorFoldType(other.getAnchorFoldType()); 
      if (other.getPriorityScore() != 0L)
        setPriorityScore(other.getPriorityScore()); 
      if (!other.getLogId().isEmpty()) {
        this.logId_ = other.logId_;
        this.bitField0_ |= 0x800;
        onChanged();
      } 
      if (!other.getMsgProcessFilterK().isEmpty()) {
        this.msgProcessFilterK_ = other.msgProcessFilterK_;
        this.bitField0_ |= 0x1000;
        onChanged();
      } 
      if (!other.getMsgProcessFilterV().isEmpty()) {
        this.msgProcessFilterV_ = other.msgProcessFilterV_;
        this.bitField0_ |= 0x2000;
        onChanged();
      } 
      if (other.hasUser())
        mergeUser(other.getUser()); 
      if (other.getAnchorFoldTypeV2() != 0L)
        setAnchorFoldTypeV2(other.getAnchorFoldTypeV2()); 
      if (other.getProcessAtSeiTimeMs() != 0L)
        setProcessAtSeiTimeMs(other.getProcessAtSeiTimeMs()); 
      if (other.getRandomDispatchMs() != 0L)
        setRandomDispatchMs(other.getRandomDispatchMs()); 
      if (other.getIsDispatch())
        setIsDispatch(other.getIsDispatch()); 
      if (other.getChannelId() != 0L)
        setChannelId(other.getChannelId()); 
      if (other.getDiffSei2AbsSecond() != 0L)
        setDiffSei2AbsSecond(other.getDiffSei2AbsSecond()); 
      if (other.getAnchorFoldDuration() != 0L)
        setAnchorFoldDuration(other.getAnchorFoldDuration()); 
      if (other.getAppId() != 0L)
        setAppId(other.getAppId()); 
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
              this.method_ = input.readStringRequireUtf8();
              this.bitField0_ |= 0x1;
              continue;
            case 16:
              this.msgId_ = input.readUInt64();
              this.bitField0_ |= 0x2;
              continue;
            case 24:
              this.roomId_ = input.readUInt64();
              this.bitField0_ |= 0x4;
              continue;
            case 32:
              this.createTime_ = input.readUInt64();
              this.bitField0_ |= 0x8;
              continue;
            case 40:
              this.monitor_ = input.readUInt32();
              this.bitField0_ |= 0x10;
              continue;
            case 48:
              this.isShowMsg_ = input.readBool();
              this.bitField0_ |= 0x20;
              continue;
            case 58:
              this.describe_ = input.readStringRequireUtf8();
              this.bitField0_ |= 0x40;
              continue;
            case 66:
              input.readMessage((MessageLite.Builder)getDisplayTextFieldBuilder().getBuilder(), extensionRegistry);
              this.bitField0_ |= 0x80;
              continue;
            case 72:
              this.foldType_ = input.readUInt64();
              this.bitField0_ |= 0x100;
              continue;
            case 80:
              this.anchorFoldType_ = input.readUInt64();
              this.bitField0_ |= 0x200;
              continue;
            case 88:
              this.priorityScore_ = input.readUInt64();
              this.bitField0_ |= 0x400;
              continue;
            case 98:
              this.logId_ = input.readStringRequireUtf8();
              this.bitField0_ |= 0x800;
              continue;
            case 106:
              this.msgProcessFilterK_ = input.readStringRequireUtf8();
              this.bitField0_ |= 0x1000;
              continue;
            case 114:
              this.msgProcessFilterV_ = input.readStringRequireUtf8();
              this.bitField0_ |= 0x2000;
              continue;
            case 122:
              input.readMessage((MessageLite.Builder)getUserFieldBuilder().getBuilder(), extensionRegistry);
              this.bitField0_ |= 0x4000;
              continue;
            case 136:
              this.anchorFoldTypeV2_ = input.readUInt64();
              this.bitField0_ |= 0x8000;
              continue;
            case 144:
              this.processAtSeiTimeMs_ = input.readUInt64();
              this.bitField0_ |= 0x10000;
              continue;
            case 152:
              this.randomDispatchMs_ = input.readUInt64();
              this.bitField0_ |= 0x20000;
              continue;
            case 160:
              this.isDispatch_ = input.readBool();
              this.bitField0_ |= 0x40000;
              continue;
            case 168:
              this.channelId_ = input.readUInt64();
              this.bitField0_ |= 0x80000;
              continue;
            case 176:
              this.diffSei2AbsSecond_ = input.readUInt64();
              this.bitField0_ |= 0x100000;
              continue;
            case 184:
              this.anchorFoldDuration_ = input.readUInt64();
              this.bitField0_ |= 0x200000;
              continue;
            case 192:
              this.appId_ = input.readUInt64();
              this.bitField0_ |= 0x400000;
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
    
    public String getMethod() {
      Object ref = this.method_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.method_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getMethodBytes() {
      Object ref = this.method_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.method_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setMethod(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.method_ = value;
      this.bitField0_ |= 0x1;
      onChanged();
      return this;
    }
    
    public Builder clearMethod() {
      this.method_ = Common.getDefaultInstance().getMethod();
      this.bitField0_ &= 0xFFFFFFFE;
      onChanged();
      return this;
    }
    
    public Builder setMethodBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      Common.checkByteStringIsUtf8(value);
      this.method_ = value;
      this.bitField0_ |= 0x1;
      onChanged();
      return this;
    }
    
    public long getMsgId() {
      return this.msgId_;
    }
    
    public Builder setMsgId(long value) {
      this.msgId_ = value;
      this.bitField0_ |= 0x2;
      onChanged();
      return this;
    }
    
    public Builder clearMsgId() {
      this.bitField0_ &= 0xFFFFFFFD;
      this.msgId_ = 0L;
      onChanged();
      return this;
    }
    
    public long getRoomId() {
      return this.roomId_;
    }
    
    public Builder setRoomId(long value) {
      this.roomId_ = value;
      this.bitField0_ |= 0x4;
      onChanged();
      return this;
    }
    
    public Builder clearRoomId() {
      this.bitField0_ &= 0xFFFFFFFB;
      this.roomId_ = 0L;
      onChanged();
      return this;
    }
    
    public long getCreateTime() {
      return this.createTime_;
    }
    
    public Builder setCreateTime(long value) {
      this.createTime_ = value;
      this.bitField0_ |= 0x8;
      onChanged();
      return this;
    }
    
    public Builder clearCreateTime() {
      this.bitField0_ &= 0xFFFFFFF7;
      this.createTime_ = 0L;
      onChanged();
      return this;
    }
    
    public int getMonitor() {
      return this.monitor_;
    }
    
    public Builder setMonitor(int value) {
      this.monitor_ = value;
      this.bitField0_ |= 0x10;
      onChanged();
      return this;
    }
    
    public Builder clearMonitor() {
      this.bitField0_ &= 0xFFFFFFEF;
      this.monitor_ = 0;
      onChanged();
      return this;
    }
    
    public boolean getIsShowMsg() {
      return this.isShowMsg_;
    }
    
    public Builder setIsShowMsg(boolean value) {
      this.isShowMsg_ = value;
      this.bitField0_ |= 0x20;
      onChanged();
      return this;
    }
    
    public Builder clearIsShowMsg() {
      this.bitField0_ &= 0xFFFFFFDF;
      this.isShowMsg_ = false;
      onChanged();
      return this;
    }
    
    public String getDescribe() {
      Object ref = this.describe_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.describe_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getDescribeBytes() {
      Object ref = this.describe_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.describe_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setDescribe(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.describe_ = value;
      this.bitField0_ |= 0x40;
      onChanged();
      return this;
    }
    
    public Builder clearDescribe() {
      this.describe_ = Common.getDefaultInstance().getDescribe();
      this.bitField0_ &= 0xFFFFFFBF;
      onChanged();
      return this;
    }
    
    public Builder setDescribeBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      Common.checkByteStringIsUtf8(value);
      this.describe_ = value;
      this.bitField0_ |= 0x40;
      onChanged();
      return this;
    }
    
    public boolean hasDisplayText() {
      return ((this.bitField0_ & 0x80) != 0);
    }
    
    public Text getDisplayText() {
      if (this.displayTextBuilder_ == null)
        return (this.displayText_ == null) ? Text.getDefaultInstance() : this.displayText_; 
      return (Text)this.displayTextBuilder_.getMessage();
    }
    
    public Builder setDisplayText(Text value) {
      if (this.displayTextBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        this.displayText_ = value;
      } else {
        this.displayTextBuilder_.setMessage((AbstractMessage)value);
      } 
      this.bitField0_ |= 0x80;
      onChanged();
      return this;
    }
    
    public Builder setDisplayText(Text.Builder builderForValue) {
      if (this.displayTextBuilder_ == null) {
        this.displayText_ = builderForValue.build();
      } else {
        this.displayTextBuilder_.setMessage((AbstractMessage)builderForValue.build());
      } 
      this.bitField0_ |= 0x80;
      onChanged();
      return this;
    }
    
    public Builder mergeDisplayText(Text value) {
      if (this.displayTextBuilder_ == null) {
        if ((this.bitField0_ & 0x80) != 0 && this.displayText_ != null && this.displayText_ != Text.getDefaultInstance()) {
          getDisplayTextBuilder().mergeFrom(value);
        } else {
          this.displayText_ = value;
        } 
      } else {
        this.displayTextBuilder_.mergeFrom((AbstractMessage)value);
      } 
      if (this.displayText_ != null) {
        this.bitField0_ |= 0x80;
        onChanged();
      } 
      return this;
    }
    
    public Builder clearDisplayText() {
      this.bitField0_ &= 0xFFFFFF7F;
      this.displayText_ = null;
      if (this.displayTextBuilder_ != null) {
        this.displayTextBuilder_.dispose();
        this.displayTextBuilder_ = null;
      } 
      onChanged();
      return this;
    }
    
    public Text.Builder getDisplayTextBuilder() {
      this.bitField0_ |= 0x80;
      onChanged();
      return (Text.Builder)getDisplayTextFieldBuilder().getBuilder();
    }
    
    public TextOrBuilder getDisplayTextOrBuilder() {
      if (this.displayTextBuilder_ != null)
        return (TextOrBuilder)this.displayTextBuilder_.getMessageOrBuilder(); 
      return (this.displayText_ == null) ? Text.getDefaultInstance() : this.displayText_;
    }
    
    private SingleFieldBuilderV3<Text, Text.Builder, TextOrBuilder> getDisplayTextFieldBuilder() {
      if (this.displayTextBuilder_ == null) {
        this.displayTextBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getDisplayText(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.displayText_ = null;
      } 
      return this.displayTextBuilder_;
    }
    
    public long getFoldType() {
      return this.foldType_;
    }
    
    public Builder setFoldType(long value) {
      this.foldType_ = value;
      this.bitField0_ |= 0x100;
      onChanged();
      return this;
    }
    
    public Builder clearFoldType() {
      this.bitField0_ &= 0xFFFFFEFF;
      this.foldType_ = 0L;
      onChanged();
      return this;
    }
    
    public long getAnchorFoldType() {
      return this.anchorFoldType_;
    }
    
    public Builder setAnchorFoldType(long value) {
      this.anchorFoldType_ = value;
      this.bitField0_ |= 0x200;
      onChanged();
      return this;
    }
    
    public Builder clearAnchorFoldType() {
      this.bitField0_ &= 0xFFFFFDFF;
      this.anchorFoldType_ = 0L;
      onChanged();
      return this;
    }
    
    public long getPriorityScore() {
      return this.priorityScore_;
    }
    
    public Builder setPriorityScore(long value) {
      this.priorityScore_ = value;
      this.bitField0_ |= 0x400;
      onChanged();
      return this;
    }
    
    public Builder clearPriorityScore() {
      this.bitField0_ &= 0xFFFFFBFF;
      this.priorityScore_ = 0L;
      onChanged();
      return this;
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
      this.bitField0_ |= 0x800;
      onChanged();
      return this;
    }
    
    public Builder clearLogId() {
      this.logId_ = Common.getDefaultInstance().getLogId();
      this.bitField0_ &= 0xFFFFF7FF;
      onChanged();
      return this;
    }
    
    public Builder setLogIdBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      Common.checkByteStringIsUtf8(value);
      this.logId_ = value;
      this.bitField0_ |= 0x800;
      onChanged();
      return this;
    }
    
    public String getMsgProcessFilterK() {
      Object ref = this.msgProcessFilterK_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.msgProcessFilterK_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getMsgProcessFilterKBytes() {
      Object ref = this.msgProcessFilterK_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.msgProcessFilterK_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setMsgProcessFilterK(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.msgProcessFilterK_ = value;
      this.bitField0_ |= 0x1000;
      onChanged();
      return this;
    }
    
    public Builder clearMsgProcessFilterK() {
      this.msgProcessFilterK_ = Common.getDefaultInstance().getMsgProcessFilterK();
      this.bitField0_ &= 0xFFFFEFFF;
      onChanged();
      return this;
    }
    
    public Builder setMsgProcessFilterKBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      Common.checkByteStringIsUtf8(value);
      this.msgProcessFilterK_ = value;
      this.bitField0_ |= 0x1000;
      onChanged();
      return this;
    }
    
    public String getMsgProcessFilterV() {
      Object ref = this.msgProcessFilterV_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.msgProcessFilterV_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getMsgProcessFilterVBytes() {
      Object ref = this.msgProcessFilterV_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.msgProcessFilterV_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setMsgProcessFilterV(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.msgProcessFilterV_ = value;
      this.bitField0_ |= 0x2000;
      onChanged();
      return this;
    }
    
    public Builder clearMsgProcessFilterV() {
      this.msgProcessFilterV_ = Common.getDefaultInstance().getMsgProcessFilterV();
      this.bitField0_ &= 0xFFFFDFFF;
      onChanged();
      return this;
    }
    
    public Builder setMsgProcessFilterVBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      Common.checkByteStringIsUtf8(value);
      this.msgProcessFilterV_ = value;
      this.bitField0_ |= 0x2000;
      onChanged();
      return this;
    }
    
    public boolean hasUser() {
      return ((this.bitField0_ & 0x4000) != 0);
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
      this.bitField0_ |= 0x4000;
      onChanged();
      return this;
    }
    
    public Builder setUser(User.Builder builderForValue) {
      if (this.userBuilder_ == null) {
        this.user_ = builderForValue.build();
      } else {
        this.userBuilder_.setMessage((AbstractMessage)builderForValue.build());
      } 
      this.bitField0_ |= 0x4000;
      onChanged();
      return this;
    }
    
    public Builder mergeUser(User value) {
      if (this.userBuilder_ == null) {
        if ((this.bitField0_ & 0x4000) != 0 && this.user_ != null && this.user_ != 
          
          User.getDefaultInstance()) {
          getUserBuilder().mergeFrom(value);
        } else {
          this.user_ = value;
        } 
      } else {
        this.userBuilder_.mergeFrom((AbstractMessage)value);
      } 
      if (this.user_ != null) {
        this.bitField0_ |= 0x4000;
        onChanged();
      } 
      return this;
    }
    
    public Builder clearUser() {
      this.bitField0_ &= 0xFFFFBFFF;
      this.user_ = null;
      if (this.userBuilder_ != null) {
        this.userBuilder_.dispose();
        this.userBuilder_ = null;
      } 
      onChanged();
      return this;
    }
    
    public User.Builder getUserBuilder() {
      this.bitField0_ |= 0x4000;
      onChanged();
      return (User.Builder)getUserFieldBuilder().getBuilder();
    }
    
    public UserOrBuilder getUserOrBuilder() {
      if (this.userBuilder_ != null)
        return (UserOrBuilder)this.userBuilder_.getMessageOrBuilder(); 
      return (this.user_ == null) ? 
        User.getDefaultInstance() : this.user_;
    }
    
    private SingleFieldBuilderV3<User, User.Builder, UserOrBuilder> getUserFieldBuilder() {
      if (this.userBuilder_ == null) {
        this
          
          .userBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getUser(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.user_ = null;
      } 
      return this.userBuilder_;
    }
    
    public long getAnchorFoldTypeV2() {
      return this.anchorFoldTypeV2_;
    }
    
    public Builder setAnchorFoldTypeV2(long value) {
      this.anchorFoldTypeV2_ = value;
      this.bitField0_ |= 0x8000;
      onChanged();
      return this;
    }
    
    public Builder clearAnchorFoldTypeV2() {
      this.bitField0_ &= 0xFFFF7FFF;
      this.anchorFoldTypeV2_ = 0L;
      onChanged();
      return this;
    }
    
    public long getProcessAtSeiTimeMs() {
      return this.processAtSeiTimeMs_;
    }
    
    public Builder setProcessAtSeiTimeMs(long value) {
      this.processAtSeiTimeMs_ = value;
      this.bitField0_ |= 0x10000;
      onChanged();
      return this;
    }
    
    public Builder clearProcessAtSeiTimeMs() {
      this.bitField0_ &= 0xFFFEFFFF;
      this.processAtSeiTimeMs_ = 0L;
      onChanged();
      return this;
    }
    
    public long getRandomDispatchMs() {
      return this.randomDispatchMs_;
    }
    
    public Builder setRandomDispatchMs(long value) {
      this.randomDispatchMs_ = value;
      this.bitField0_ |= 0x20000;
      onChanged();
      return this;
    }
    
    public Builder clearRandomDispatchMs() {
      this.bitField0_ &= 0xFFFDFFFF;
      this.randomDispatchMs_ = 0L;
      onChanged();
      return this;
    }
    
    public boolean getIsDispatch() {
      return this.isDispatch_;
    }
    
    public Builder setIsDispatch(boolean value) {
      this.isDispatch_ = value;
      this.bitField0_ |= 0x40000;
      onChanged();
      return this;
    }
    
    public Builder clearIsDispatch() {
      this.bitField0_ &= 0xFFFBFFFF;
      this.isDispatch_ = false;
      onChanged();
      return this;
    }
    
    public long getChannelId() {
      return this.channelId_;
    }
    
    public Builder setChannelId(long value) {
      this.channelId_ = value;
      this.bitField0_ |= 0x80000;
      onChanged();
      return this;
    }
    
    public Builder clearChannelId() {
      this.bitField0_ &= 0xFFF7FFFF;
      this.channelId_ = 0L;
      onChanged();
      return this;
    }
    
    public long getDiffSei2AbsSecond() {
      return this.diffSei2AbsSecond_;
    }
    
    public Builder setDiffSei2AbsSecond(long value) {
      this.diffSei2AbsSecond_ = value;
      this.bitField0_ |= 0x100000;
      onChanged();
      return this;
    }
    
    public Builder clearDiffSei2AbsSecond() {
      this.bitField0_ &= 0xFFEFFFFF;
      this.diffSei2AbsSecond_ = 0L;
      onChanged();
      return this;
    }
    
    public long getAnchorFoldDuration() {
      return this.anchorFoldDuration_;
    }
    
    public Builder setAnchorFoldDuration(long value) {
      this.anchorFoldDuration_ = value;
      this.bitField0_ |= 0x200000;
      onChanged();
      return this;
    }
    
    public Builder clearAnchorFoldDuration() {
      this.bitField0_ &= 0xFFDFFFFF;
      this.anchorFoldDuration_ = 0L;
      onChanged();
      return this;
    }
    
    public long getAppId() {
      return this.appId_;
    }
    
    public Builder setAppId(long value) {
      this.appId_ = value;
      this.bitField0_ |= 0x400000;
      onChanged();
      return this;
    }
    
    public Builder clearAppId() {
      this.bitField0_ &= 0xFFBFFFFF;
      this.appId_ = 0L;
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
  
  private static final Common DEFAULT_INSTANCE = new Common();
  
  public static Common getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }
  
  private static final Parser<Common> PARSER = (Parser<Common>)new AbstractParser<Common>() {
      public Common parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        Common.Builder builder = Common.newBuilder();
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
  
  public static Parser<Common> parser() {
    return PARSER;
  }
  
  public Parser<Common> getParserForType() {
    return PARSER;
  }
  
  public Common getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }
}
