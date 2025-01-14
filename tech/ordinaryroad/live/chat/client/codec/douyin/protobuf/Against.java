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

public final class Against extends GeneratedMessageV3 implements AgainstOrBuilder {
  private static final long serialVersionUID = 0L;
  
  private int bitField0_;
  
  public static final int LEFTNAME_FIELD_NUMBER = 1;
  
  private volatile Object leftName_;
  
  public static final int LEFTLOGO_FIELD_NUMBER = 2;
  
  private Image leftLogo_;
  
  public static final int LEFTGOAL_FIELD_NUMBER = 3;
  
  private volatile Object leftGoal_;
  
  public static final int RIGHTNAME_FIELD_NUMBER = 6;
  
  private volatile Object rightName_;
  
  public static final int RIGHTLOGO_FIELD_NUMBER = 7;
  
  private Image rightLogo_;
  
  public static final int RIGHTGOAL_FIELD_NUMBER = 8;
  
  private volatile Object rightGoal_;
  
  public static final int TIMESTAMP_FIELD_NUMBER = 11;
  
  private long timestamp_;
  
  public static final int VERSION_FIELD_NUMBER = 12;
  
  private long version_;
  
  public static final int LEFTTEAMID_FIELD_NUMBER = 13;
  
  private long leftTeamId_;
  
  public static final int RIGHTTEAMID_FIELD_NUMBER = 14;
  
  private long rightTeamId_;
  
  public static final int DIFFSEI2ABSSECOND_FIELD_NUMBER = 15;
  
  private long diffSei2AbsSecond_;
  
  public static final int FINALGOALSTAGE_FIELD_NUMBER = 16;
  
  private int finalGoalStage_;
  
  public static final int CURRENTGOALSTAGE_FIELD_NUMBER = 17;
  
  private int currentGoalStage_;
  
  public static final int LEFTSCOREADDITION_FIELD_NUMBER = 18;
  
  private int leftScoreAddition_;
  
  public static final int RIGHTSCOREADDITION_FIELD_NUMBER = 19;
  
  private int rightScoreAddition_;
  
  public static final int LEFTGOALINT_FIELD_NUMBER = 20;
  
  private long leftGoalInt_;
  
  public static final int RIGHTGOALINT_FIELD_NUMBER = 21;
  
  private long rightGoalInt_;
  
  private byte memoizedIsInitialized;
  
  private Against(GeneratedMessageV3.Builder<?> builder) {
    super(builder);
    this.leftName_ = "";
    this.leftGoal_ = "";
    this.rightName_ = "";
    this.rightGoal_ = "";
    this.timestamp_ = 0L;
    this.version_ = 0L;
    this.leftTeamId_ = 0L;
    this.rightTeamId_ = 0L;
    this.diffSei2AbsSecond_ = 0L;
    this.finalGoalStage_ = 0;
    this.currentGoalStage_ = 0;
    this.leftScoreAddition_ = 0;
    this.rightScoreAddition_ = 0;
    this.leftGoalInt_ = 0L;
    this.rightGoalInt_ = 0L;
    this.memoizedIsInitialized = -1;
  }
  
  private Against() {
    this.leftName_ = "";
    this.leftGoal_ = "";
    this.rightName_ = "";
    this.rightGoal_ = "";
    this.timestamp_ = 0L;
    this.version_ = 0L;
    this.leftTeamId_ = 0L;
    this.rightTeamId_ = 0L;
    this.diffSei2AbsSecond_ = 0L;
    this.finalGoalStage_ = 0;
    this.currentGoalStage_ = 0;
    this.leftScoreAddition_ = 0;
    this.rightScoreAddition_ = 0;
    this.leftGoalInt_ = 0L;
    this.rightGoalInt_ = 0L;
    this.memoizedIsInitialized = -1;
    this.leftName_ = "";
    this.leftGoal_ = "";
    this.rightName_ = "";
    this.rightGoal_ = "";
  }
  
  protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
    return new Against();
  }
  
  public static final Descriptors.Descriptor getDescriptor() {
    return Douyin.internal_static_Against_descriptor;
  }
  
  protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
    return Douyin.internal_static_Against_fieldAccessorTable.ensureFieldAccessorsInitialized(Against.class, Builder.class);
  }
  
  public String getLeftName() {
    Object ref = this.leftName_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.leftName_ = s;
    return s;
  }
  
  public ByteString getLeftNameBytes() {
    Object ref = this.leftName_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.leftName_ = b;
      return b;
    } 
    return (ByteString)ref;
  }
  
  public boolean hasLeftLogo() {
    return ((this.bitField0_ & 0x1) != 0);
  }
  
  public Image getLeftLogo() {
    return (this.leftLogo_ == null) ? Image.getDefaultInstance() : this.leftLogo_;
  }
  
  public ImageOrBuilder getLeftLogoOrBuilder() {
    return (this.leftLogo_ == null) ? Image.getDefaultInstance() : this.leftLogo_;
  }
  
  public String getLeftGoal() {
    Object ref = this.leftGoal_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.leftGoal_ = s;
    return s;
  }
  
  public ByteString getLeftGoalBytes() {
    Object ref = this.leftGoal_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.leftGoal_ = b;
      return b;
    } 
    return (ByteString)ref;
  }
  
  public String getRightName() {
    Object ref = this.rightName_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.rightName_ = s;
    return s;
  }
  
  public ByteString getRightNameBytes() {
    Object ref = this.rightName_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.rightName_ = b;
      return b;
    } 
    return (ByteString)ref;
  }
  
  public boolean hasRightLogo() {
    return ((this.bitField0_ & 0x2) != 0);
  }
  
  public Image getRightLogo() {
    return (this.rightLogo_ == null) ? Image.getDefaultInstance() : this.rightLogo_;
  }
  
  public ImageOrBuilder getRightLogoOrBuilder() {
    return (this.rightLogo_ == null) ? Image.getDefaultInstance() : this.rightLogo_;
  }
  
  public String getRightGoal() {
    Object ref = this.rightGoal_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.rightGoal_ = s;
    return s;
  }
  
  public ByteString getRightGoalBytes() {
    Object ref = this.rightGoal_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.rightGoal_ = b;
      return b;
    } 
    return (ByteString)ref;
  }
  
  public long getTimestamp() {
    return this.timestamp_;
  }
  
  public long getVersion() {
    return this.version_;
  }
  
  public long getLeftTeamId() {
    return this.leftTeamId_;
  }
  
  public long getRightTeamId() {
    return this.rightTeamId_;
  }
  
  public long getDiffSei2AbsSecond() {
    return this.diffSei2AbsSecond_;
  }
  
  public int getFinalGoalStage() {
    return this.finalGoalStage_;
  }
  
  public int getCurrentGoalStage() {
    return this.currentGoalStage_;
  }
  
  public int getLeftScoreAddition() {
    return this.leftScoreAddition_;
  }
  
  public int getRightScoreAddition() {
    return this.rightScoreAddition_;
  }
  
  public long getLeftGoalInt() {
    return this.leftGoalInt_;
  }
  
  public long getRightGoalInt() {
    return this.rightGoalInt_;
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
    if (!GeneratedMessageV3.isStringEmpty(this.leftName_))
      GeneratedMessageV3.writeString(output, 1, this.leftName_); 
    if ((this.bitField0_ & 0x1) != 0)
      output.writeMessage(2, (MessageLite)getLeftLogo()); 
    if (!GeneratedMessageV3.isStringEmpty(this.leftGoal_))
      GeneratedMessageV3.writeString(output, 3, this.leftGoal_); 
    if (!GeneratedMessageV3.isStringEmpty(this.rightName_))
      GeneratedMessageV3.writeString(output, 6, this.rightName_); 
    if ((this.bitField0_ & 0x2) != 0)
      output.writeMessage(7, (MessageLite)getRightLogo()); 
    if (!GeneratedMessageV3.isStringEmpty(this.rightGoal_))
      GeneratedMessageV3.writeString(output, 8, this.rightGoal_); 
    if (this.timestamp_ != 0L)
      output.writeUInt64(11, this.timestamp_); 
    if (this.version_ != 0L)
      output.writeUInt64(12, this.version_); 
    if (this.leftTeamId_ != 0L)
      output.writeUInt64(13, this.leftTeamId_); 
    if (this.rightTeamId_ != 0L)
      output.writeUInt64(14, this.rightTeamId_); 
    if (this.diffSei2AbsSecond_ != 0L)
      output.writeUInt64(15, this.diffSei2AbsSecond_); 
    if (this.finalGoalStage_ != 0)
      output.writeUInt32(16, this.finalGoalStage_); 
    if (this.currentGoalStage_ != 0)
      output.writeUInt32(17, this.currentGoalStage_); 
    if (this.leftScoreAddition_ != 0)
      output.writeUInt32(18, this.leftScoreAddition_); 
    if (this.rightScoreAddition_ != 0)
      output.writeUInt32(19, this.rightScoreAddition_); 
    if (this.leftGoalInt_ != 0L)
      output.writeUInt64(20, this.leftGoalInt_); 
    if (this.rightGoalInt_ != 0L)
      output.writeUInt64(21, this.rightGoalInt_); 
    getUnknownFields().writeTo(output);
  }
  
  public int getSerializedSize() {
    int size = this.memoizedSize;
    if (size != -1)
      return size; 
    size = 0;
    if (!GeneratedMessageV3.isStringEmpty(this.leftName_))
      size += GeneratedMessageV3.computeStringSize(1, this.leftName_); 
    if ((this.bitField0_ & 0x1) != 0)
      size += 
        CodedOutputStream.computeMessageSize(2, (MessageLite)getLeftLogo()); 
    if (!GeneratedMessageV3.isStringEmpty(this.leftGoal_))
      size += GeneratedMessageV3.computeStringSize(3, this.leftGoal_); 
    if (!GeneratedMessageV3.isStringEmpty(this.rightName_))
      size += GeneratedMessageV3.computeStringSize(6, this.rightName_); 
    if ((this.bitField0_ & 0x2) != 0)
      size += 
        CodedOutputStream.computeMessageSize(7, (MessageLite)getRightLogo()); 
    if (!GeneratedMessageV3.isStringEmpty(this.rightGoal_))
      size += GeneratedMessageV3.computeStringSize(8, this.rightGoal_); 
    if (this.timestamp_ != 0L)
      size += 
        CodedOutputStream.computeUInt64Size(11, this.timestamp_); 
    if (this.version_ != 0L)
      size += 
        CodedOutputStream.computeUInt64Size(12, this.version_); 
    if (this.leftTeamId_ != 0L)
      size += 
        CodedOutputStream.computeUInt64Size(13, this.leftTeamId_); 
    if (this.rightTeamId_ != 0L)
      size += 
        CodedOutputStream.computeUInt64Size(14, this.rightTeamId_); 
    if (this.diffSei2AbsSecond_ != 0L)
      size += 
        CodedOutputStream.computeUInt64Size(15, this.diffSei2AbsSecond_); 
    if (this.finalGoalStage_ != 0)
      size += 
        CodedOutputStream.computeUInt32Size(16, this.finalGoalStage_); 
    if (this.currentGoalStage_ != 0)
      size += 
        CodedOutputStream.computeUInt32Size(17, this.currentGoalStage_); 
    if (this.leftScoreAddition_ != 0)
      size += 
        CodedOutputStream.computeUInt32Size(18, this.leftScoreAddition_); 
    if (this.rightScoreAddition_ != 0)
      size += 
        CodedOutputStream.computeUInt32Size(19, this.rightScoreAddition_); 
    if (this.leftGoalInt_ != 0L)
      size += 
        CodedOutputStream.computeUInt64Size(20, this.leftGoalInt_); 
    if (this.rightGoalInt_ != 0L)
      size += 
        CodedOutputStream.computeUInt64Size(21, this.rightGoalInt_); 
    size += getUnknownFields().getSerializedSize();
    this.memoizedSize = size;
    return size;
  }
  
  public boolean equals(Object obj) {
    if (obj == this)
      return true; 
    if (!(obj instanceof Against))
      return super.equals(obj); 
    Against other = (Against)obj;
    if (!getLeftName().equals(other.getLeftName()))
      return false; 
    if (hasLeftLogo() != other.hasLeftLogo())
      return false; 
    if (hasLeftLogo() && 
      
      !getLeftLogo().equals(other.getLeftLogo()))
      return false; 
    if (!getLeftGoal().equals(other.getLeftGoal()))
      return false; 
    if (!getRightName().equals(other.getRightName()))
      return false; 
    if (hasRightLogo() != other.hasRightLogo())
      return false; 
    if (hasRightLogo() && 
      
      !getRightLogo().equals(other.getRightLogo()))
      return false; 
    if (!getRightGoal().equals(other.getRightGoal()))
      return false; 
    if (getTimestamp() != other
      .getTimestamp())
      return false; 
    if (getVersion() != other
      .getVersion())
      return false; 
    if (getLeftTeamId() != other
      .getLeftTeamId())
      return false; 
    if (getRightTeamId() != other
      .getRightTeamId())
      return false; 
    if (getDiffSei2AbsSecond() != other
      .getDiffSei2AbsSecond())
      return false; 
    if (getFinalGoalStage() != other
      .getFinalGoalStage())
      return false; 
    if (getCurrentGoalStage() != other
      .getCurrentGoalStage())
      return false; 
    if (getLeftScoreAddition() != other
      .getLeftScoreAddition())
      return false; 
    if (getRightScoreAddition() != other
      .getRightScoreAddition())
      return false; 
    if (getLeftGoalInt() != other
      .getLeftGoalInt())
      return false; 
    if (getRightGoalInt() != other
      .getRightGoalInt())
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
    hash = 53 * hash + getLeftName().hashCode();
    if (hasLeftLogo()) {
      hash = 37 * hash + 2;
      hash = 53 * hash + getLeftLogo().hashCode();
    } 
    hash = 37 * hash + 3;
    hash = 53 * hash + getLeftGoal().hashCode();
    hash = 37 * hash + 6;
    hash = 53 * hash + getRightName().hashCode();
    if (hasRightLogo()) {
      hash = 37 * hash + 7;
      hash = 53 * hash + getRightLogo().hashCode();
    } 
    hash = 37 * hash + 8;
    hash = 53 * hash + getRightGoal().hashCode();
    hash = 37 * hash + 11;
    hash = 53 * hash + Internal.hashLong(
        getTimestamp());
    hash = 37 * hash + 12;
    hash = 53 * hash + Internal.hashLong(
        getVersion());
    hash = 37 * hash + 13;
    hash = 53 * hash + Internal.hashLong(
        getLeftTeamId());
    hash = 37 * hash + 14;
    hash = 53 * hash + Internal.hashLong(
        getRightTeamId());
    hash = 37 * hash + 15;
    hash = 53 * hash + Internal.hashLong(
        getDiffSei2AbsSecond());
    hash = 37 * hash + 16;
    hash = 53 * hash + getFinalGoalStage();
    hash = 37 * hash + 17;
    hash = 53 * hash + getCurrentGoalStage();
    hash = 37 * hash + 18;
    hash = 53 * hash + getLeftScoreAddition();
    hash = 37 * hash + 19;
    hash = 53 * hash + getRightScoreAddition();
    hash = 37 * hash + 20;
    hash = 53 * hash + Internal.hashLong(
        getLeftGoalInt());
    hash = 37 * hash + 21;
    hash = 53 * hash + Internal.hashLong(
        getRightGoalInt());
    hash = 29 * hash + getUnknownFields().hashCode();
    this.memoizedHashCode = hash;
    return hash;
  }
  
  public static Against parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
    return (Against)PARSER.parseFrom(data);
  }
  
  public static Against parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (Against)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static Against parseFrom(ByteString data) throws InvalidProtocolBufferException {
    return (Against)PARSER.parseFrom(data);
  }
  
  public static Against parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (Against)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static Against parseFrom(byte[] data) throws InvalidProtocolBufferException {
    return (Against)PARSER.parseFrom(data);
  }
  
  public static Against parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (Against)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static Against parseFrom(InputStream input) throws IOException {
    return 
      (Against)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static Against parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (Against)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static Against parseDelimitedFrom(InputStream input) throws IOException {
    return 
      (Against)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
  }
  
  public static Against parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (Against)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static Against parseFrom(CodedInputStream input) throws IOException {
    return 
      (Against)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static Against parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (Against)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public Builder newBuilderForType() {
    return newBuilder();
  }
  
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  
  public static Builder newBuilder(Against prototype) {
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
  
  public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements AgainstOrBuilder {
    private int bitField0_;
    
    private Object leftName_;
    
    private Image leftLogo_;
    
    private SingleFieldBuilderV3<Image, Image.Builder, ImageOrBuilder> leftLogoBuilder_;
    
    private Object leftGoal_;
    
    private Object rightName_;
    
    private Image rightLogo_;
    
    private SingleFieldBuilderV3<Image, Image.Builder, ImageOrBuilder> rightLogoBuilder_;
    
    private Object rightGoal_;
    
    private long timestamp_;
    
    private long version_;
    
    private long leftTeamId_;
    
    private long rightTeamId_;
    
    private long diffSei2AbsSecond_;
    
    private int finalGoalStage_;
    
    private int currentGoalStage_;
    
    private int leftScoreAddition_;
    
    private int rightScoreAddition_;
    
    private long leftGoalInt_;
    
    private long rightGoalInt_;
    
    public static final Descriptors.Descriptor getDescriptor() {
      return Douyin.internal_static_Against_descriptor;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return Douyin.internal_static_Against_fieldAccessorTable
        .ensureFieldAccessorsInitialized(Against.class, Builder.class);
    }
    
    private Builder() {
      this.leftName_ = "";
      this.leftGoal_ = "";
      this.rightName_ = "";
      this.rightGoal_ = "";
      maybeForceBuilderInitialization();
    }
    
    private Builder(GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      this.leftName_ = "";
      this.leftGoal_ = "";
      this.rightName_ = "";
      this.rightGoal_ = "";
      maybeForceBuilderInitialization();
    }
    
    private void maybeForceBuilderInitialization() {
      if (Against.alwaysUseFieldBuilders) {
        getLeftLogoFieldBuilder();
        getRightLogoFieldBuilder();
      } 
    }
    
    public Builder clear() {
      super.clear();
      this.bitField0_ = 0;
      this.leftName_ = "";
      this.leftLogo_ = null;
      if (this.leftLogoBuilder_ != null) {
        this.leftLogoBuilder_.dispose();
        this.leftLogoBuilder_ = null;
      } 
      this.leftGoal_ = "";
      this.rightName_ = "";
      this.rightLogo_ = null;
      if (this.rightLogoBuilder_ != null) {
        this.rightLogoBuilder_.dispose();
        this.rightLogoBuilder_ = null;
      } 
      this.rightGoal_ = "";
      this.timestamp_ = 0L;
      this.version_ = 0L;
      this.leftTeamId_ = 0L;
      this.rightTeamId_ = 0L;
      this.diffSei2AbsSecond_ = 0L;
      this.finalGoalStage_ = 0;
      this.currentGoalStage_ = 0;
      this.leftScoreAddition_ = 0;
      this.rightScoreAddition_ = 0;
      this.leftGoalInt_ = 0L;
      this.rightGoalInt_ = 0L;
      return this;
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
      return Douyin.internal_static_Against_descriptor;
    }
    
    public Against getDefaultInstanceForType() {
      return Against.getDefaultInstance();
    }
    
    public Against build() {
      Against result = buildPartial();
      if (!result.isInitialized())
        throw newUninitializedMessageException(result); 
      return result;
    }
    
    public Against buildPartial() {
      Against result = new Against(this);
      if (this.bitField0_ != 0)
        buildPartial0(result); 
      onBuilt();
      return result;
    }
    
    private void buildPartial0(Against result) {
      int from_bitField0_ = this.bitField0_;
      if ((from_bitField0_ & 0x1) != 0)
        result.leftName_ = this.leftName_; 
      int to_bitField0_ = 0;
      if ((from_bitField0_ & 0x2) != 0) {
        result.leftLogo_ = (this.leftLogoBuilder_ == null) ? this.leftLogo_ : (Image)this.leftLogoBuilder_.build();
        to_bitField0_ |= 0x1;
      } 
      if ((from_bitField0_ & 0x4) != 0)
        result.leftGoal_ = this.leftGoal_; 
      if ((from_bitField0_ & 0x8) != 0)
        result.rightName_ = this.rightName_; 
      if ((from_bitField0_ & 0x10) != 0) {
        result.rightLogo_ = (this.rightLogoBuilder_ == null) ? this.rightLogo_ : (Image)this.rightLogoBuilder_.build();
        to_bitField0_ |= 0x2;
      } 
      if ((from_bitField0_ & 0x20) != 0)
        result.rightGoal_ = this.rightGoal_; 
      if ((from_bitField0_ & 0x40) != 0)
        result.timestamp_ = this.timestamp_; 
      if ((from_bitField0_ & 0x80) != 0)
        result.version_ = this.version_; 
      if ((from_bitField0_ & 0x100) != 0)
        result.leftTeamId_ = this.leftTeamId_; 
      if ((from_bitField0_ & 0x200) != 0)
        result.rightTeamId_ = this.rightTeamId_; 
      if ((from_bitField0_ & 0x400) != 0)
        result.diffSei2AbsSecond_ = this.diffSei2AbsSecond_; 
      if ((from_bitField0_ & 0x800) != 0)
        result.finalGoalStage_ = this.finalGoalStage_; 
      if ((from_bitField0_ & 0x1000) != 0)
        result.currentGoalStage_ = this.currentGoalStage_; 
      if ((from_bitField0_ & 0x2000) != 0)
        result.leftScoreAddition_ = this.leftScoreAddition_; 
      if ((from_bitField0_ & 0x4000) != 0)
        result.rightScoreAddition_ = this.rightScoreAddition_; 
      if ((from_bitField0_ & 0x8000) != 0)
        result.leftGoalInt_ = this.leftGoalInt_; 
      if ((from_bitField0_ & 0x10000) != 0)
        result.rightGoalInt_ = this.rightGoalInt_; 
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
      if (other instanceof Against)
        return mergeFrom((Against)other); 
      super.mergeFrom(other);
      return this;
    }
    
    public Builder mergeFrom(Against other) {
      if (other == Against.getDefaultInstance())
        return this; 
      if (!other.getLeftName().isEmpty()) {
        this.leftName_ = other.leftName_;
        this.bitField0_ |= 0x1;
        onChanged();
      } 
      if (other.hasLeftLogo())
        mergeLeftLogo(other.getLeftLogo()); 
      if (!other.getLeftGoal().isEmpty()) {
        this.leftGoal_ = other.leftGoal_;
        this.bitField0_ |= 0x4;
        onChanged();
      } 
      if (!other.getRightName().isEmpty()) {
        this.rightName_ = other.rightName_;
        this.bitField0_ |= 0x8;
        onChanged();
      } 
      if (other.hasRightLogo())
        mergeRightLogo(other.getRightLogo()); 
      if (!other.getRightGoal().isEmpty()) {
        this.rightGoal_ = other.rightGoal_;
        this.bitField0_ |= 0x20;
        onChanged();
      } 
      if (other.getTimestamp() != 0L)
        setTimestamp(other.getTimestamp()); 
      if (other.getVersion() != 0L)
        setVersion(other.getVersion()); 
      if (other.getLeftTeamId() != 0L)
        setLeftTeamId(other.getLeftTeamId()); 
      if (other.getRightTeamId() != 0L)
        setRightTeamId(other.getRightTeamId()); 
      if (other.getDiffSei2AbsSecond() != 0L)
        setDiffSei2AbsSecond(other.getDiffSei2AbsSecond()); 
      if (other.getFinalGoalStage() != 0)
        setFinalGoalStage(other.getFinalGoalStage()); 
      if (other.getCurrentGoalStage() != 0)
        setCurrentGoalStage(other.getCurrentGoalStage()); 
      if (other.getLeftScoreAddition() != 0)
        setLeftScoreAddition(other.getLeftScoreAddition()); 
      if (other.getRightScoreAddition() != 0)
        setRightScoreAddition(other.getRightScoreAddition()); 
      if (other.getLeftGoalInt() != 0L)
        setLeftGoalInt(other.getLeftGoalInt()); 
      if (other.getRightGoalInt() != 0L)
        setRightGoalInt(other.getRightGoalInt()); 
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
              this.leftName_ = input.readStringRequireUtf8();
              this.bitField0_ |= 0x1;
              continue;
            case 18:
              input.readMessage((MessageLite.Builder)getLeftLogoFieldBuilder().getBuilder(), extensionRegistry);
              this.bitField0_ |= 0x2;
              continue;
            case 26:
              this.leftGoal_ = input.readStringRequireUtf8();
              this.bitField0_ |= 0x4;
              continue;
            case 50:
              this.rightName_ = input.readStringRequireUtf8();
              this.bitField0_ |= 0x8;
              continue;
            case 58:
              input.readMessage((MessageLite.Builder)getRightLogoFieldBuilder().getBuilder(), extensionRegistry);
              this.bitField0_ |= 0x10;
              continue;
            case 66:
              this.rightGoal_ = input.readStringRequireUtf8();
              this.bitField0_ |= 0x20;
              continue;
            case 88:
              this.timestamp_ = input.readUInt64();
              this.bitField0_ |= 0x40;
              continue;
            case 96:
              this.version_ = input.readUInt64();
              this.bitField0_ |= 0x80;
              continue;
            case 104:
              this.leftTeamId_ = input.readUInt64();
              this.bitField0_ |= 0x100;
              continue;
            case 112:
              this.rightTeamId_ = input.readUInt64();
              this.bitField0_ |= 0x200;
              continue;
            case 120:
              this.diffSei2AbsSecond_ = input.readUInt64();
              this.bitField0_ |= 0x400;
              continue;
            case 128:
              this.finalGoalStage_ = input.readUInt32();
              this.bitField0_ |= 0x800;
              continue;
            case 136:
              this.currentGoalStage_ = input.readUInt32();
              this.bitField0_ |= 0x1000;
              continue;
            case 144:
              this.leftScoreAddition_ = input.readUInt32();
              this.bitField0_ |= 0x2000;
              continue;
            case 152:
              this.rightScoreAddition_ = input.readUInt32();
              this.bitField0_ |= 0x4000;
              continue;
            case 160:
              this.leftGoalInt_ = input.readUInt64();
              this.bitField0_ |= 0x8000;
              continue;
            case 168:
              this.rightGoalInt_ = input.readUInt64();
              this.bitField0_ |= 0x10000;
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
    
    public String getLeftName() {
      Object ref = this.leftName_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.leftName_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getLeftNameBytes() {
      Object ref = this.leftName_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.leftName_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setLeftName(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.leftName_ = value;
      this.bitField0_ |= 0x1;
      onChanged();
      return this;
    }
    
    public Builder clearLeftName() {
      this.leftName_ = Against.getDefaultInstance().getLeftName();
      this.bitField0_ &= 0xFFFFFFFE;
      onChanged();
      return this;
    }
    
    public Builder setLeftNameBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      Against.checkByteStringIsUtf8(value);
      this.leftName_ = value;
      this.bitField0_ |= 0x1;
      onChanged();
      return this;
    }
    
    public boolean hasLeftLogo() {
      return ((this.bitField0_ & 0x2) != 0);
    }
    
    public Image getLeftLogo() {
      if (this.leftLogoBuilder_ == null)
        return (this.leftLogo_ == null) ? Image.getDefaultInstance() : this.leftLogo_; 
      return (Image)this.leftLogoBuilder_.getMessage();
    }
    
    public Builder setLeftLogo(Image value) {
      if (this.leftLogoBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        this.leftLogo_ = value;
      } else {
        this.leftLogoBuilder_.setMessage((AbstractMessage)value);
      } 
      this.bitField0_ |= 0x2;
      onChanged();
      return this;
    }
    
    public Builder setLeftLogo(Image.Builder builderForValue) {
      if (this.leftLogoBuilder_ == null) {
        this.leftLogo_ = builderForValue.build();
      } else {
        this.leftLogoBuilder_.setMessage((AbstractMessage)builderForValue.build());
      } 
      this.bitField0_ |= 0x2;
      onChanged();
      return this;
    }
    
    public Builder mergeLeftLogo(Image value) {
      if (this.leftLogoBuilder_ == null) {
        if ((this.bitField0_ & 0x2) != 0 && this.leftLogo_ != null && this.leftLogo_ != Image.getDefaultInstance()) {
          getLeftLogoBuilder().mergeFrom(value);
        } else {
          this.leftLogo_ = value;
        } 
      } else {
        this.leftLogoBuilder_.mergeFrom((AbstractMessage)value);
      } 
      if (this.leftLogo_ != null) {
        this.bitField0_ |= 0x2;
        onChanged();
      } 
      return this;
    }
    
    public Builder clearLeftLogo() {
      this.bitField0_ &= 0xFFFFFFFD;
      this.leftLogo_ = null;
      if (this.leftLogoBuilder_ != null) {
        this.leftLogoBuilder_.dispose();
        this.leftLogoBuilder_ = null;
      } 
      onChanged();
      return this;
    }
    
    public Image.Builder getLeftLogoBuilder() {
      this.bitField0_ |= 0x2;
      onChanged();
      return (Image.Builder)getLeftLogoFieldBuilder().getBuilder();
    }
    
    public ImageOrBuilder getLeftLogoOrBuilder() {
      if (this.leftLogoBuilder_ != null)
        return (ImageOrBuilder)this.leftLogoBuilder_.getMessageOrBuilder(); 
      return (this.leftLogo_ == null) ? Image.getDefaultInstance() : this.leftLogo_;
    }
    
    private SingleFieldBuilderV3<Image, Image.Builder, ImageOrBuilder> getLeftLogoFieldBuilder() {
      if (this.leftLogoBuilder_ == null) {
        this.leftLogoBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getLeftLogo(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.leftLogo_ = null;
      } 
      return this.leftLogoBuilder_;
    }
    
    public String getLeftGoal() {
      Object ref = this.leftGoal_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.leftGoal_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getLeftGoalBytes() {
      Object ref = this.leftGoal_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.leftGoal_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setLeftGoal(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.leftGoal_ = value;
      this.bitField0_ |= 0x4;
      onChanged();
      return this;
    }
    
    public Builder clearLeftGoal() {
      this.leftGoal_ = Against.getDefaultInstance().getLeftGoal();
      this.bitField0_ &= 0xFFFFFFFB;
      onChanged();
      return this;
    }
    
    public Builder setLeftGoalBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      Against.checkByteStringIsUtf8(value);
      this.leftGoal_ = value;
      this.bitField0_ |= 0x4;
      onChanged();
      return this;
    }
    
    public String getRightName() {
      Object ref = this.rightName_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.rightName_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getRightNameBytes() {
      Object ref = this.rightName_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.rightName_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setRightName(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.rightName_ = value;
      this.bitField0_ |= 0x8;
      onChanged();
      return this;
    }
    
    public Builder clearRightName() {
      this.rightName_ = Against.getDefaultInstance().getRightName();
      this.bitField0_ &= 0xFFFFFFF7;
      onChanged();
      return this;
    }
    
    public Builder setRightNameBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      Against.checkByteStringIsUtf8(value);
      this.rightName_ = value;
      this.bitField0_ |= 0x8;
      onChanged();
      return this;
    }
    
    public boolean hasRightLogo() {
      return ((this.bitField0_ & 0x10) != 0);
    }
    
    public Image getRightLogo() {
      if (this.rightLogoBuilder_ == null)
        return (this.rightLogo_ == null) ? Image.getDefaultInstance() : this.rightLogo_; 
      return (Image)this.rightLogoBuilder_.getMessage();
    }
    
    public Builder setRightLogo(Image value) {
      if (this.rightLogoBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        this.rightLogo_ = value;
      } else {
        this.rightLogoBuilder_.setMessage((AbstractMessage)value);
      } 
      this.bitField0_ |= 0x10;
      onChanged();
      return this;
    }
    
    public Builder setRightLogo(Image.Builder builderForValue) {
      if (this.rightLogoBuilder_ == null) {
        this.rightLogo_ = builderForValue.build();
      } else {
        this.rightLogoBuilder_.setMessage((AbstractMessage)builderForValue.build());
      } 
      this.bitField0_ |= 0x10;
      onChanged();
      return this;
    }
    
    public Builder mergeRightLogo(Image value) {
      if (this.rightLogoBuilder_ == null) {
        if ((this.bitField0_ & 0x10) != 0 && this.rightLogo_ != null && this.rightLogo_ != Image.getDefaultInstance()) {
          getRightLogoBuilder().mergeFrom(value);
        } else {
          this.rightLogo_ = value;
        } 
      } else {
        this.rightLogoBuilder_.mergeFrom((AbstractMessage)value);
      } 
      if (this.rightLogo_ != null) {
        this.bitField0_ |= 0x10;
        onChanged();
      } 
      return this;
    }
    
    public Builder clearRightLogo() {
      this.bitField0_ &= 0xFFFFFFEF;
      this.rightLogo_ = null;
      if (this.rightLogoBuilder_ != null) {
        this.rightLogoBuilder_.dispose();
        this.rightLogoBuilder_ = null;
      } 
      onChanged();
      return this;
    }
    
    public Image.Builder getRightLogoBuilder() {
      this.bitField0_ |= 0x10;
      onChanged();
      return (Image.Builder)getRightLogoFieldBuilder().getBuilder();
    }
    
    public ImageOrBuilder getRightLogoOrBuilder() {
      if (this.rightLogoBuilder_ != null)
        return (ImageOrBuilder)this.rightLogoBuilder_.getMessageOrBuilder(); 
      return (this.rightLogo_ == null) ? Image.getDefaultInstance() : this.rightLogo_;
    }
    
    private SingleFieldBuilderV3<Image, Image.Builder, ImageOrBuilder> getRightLogoFieldBuilder() {
      if (this.rightLogoBuilder_ == null) {
        this.rightLogoBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getRightLogo(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.rightLogo_ = null;
      } 
      return this.rightLogoBuilder_;
    }
    
    public String getRightGoal() {
      Object ref = this.rightGoal_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.rightGoal_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getRightGoalBytes() {
      Object ref = this.rightGoal_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.rightGoal_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setRightGoal(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.rightGoal_ = value;
      this.bitField0_ |= 0x20;
      onChanged();
      return this;
    }
    
    public Builder clearRightGoal() {
      this.rightGoal_ = Against.getDefaultInstance().getRightGoal();
      this.bitField0_ &= 0xFFFFFFDF;
      onChanged();
      return this;
    }
    
    public Builder setRightGoalBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      Against.checkByteStringIsUtf8(value);
      this.rightGoal_ = value;
      this.bitField0_ |= 0x20;
      onChanged();
      return this;
    }
    
    public long getTimestamp() {
      return this.timestamp_;
    }
    
    public Builder setTimestamp(long value) {
      this.timestamp_ = value;
      this.bitField0_ |= 0x40;
      onChanged();
      return this;
    }
    
    public Builder clearTimestamp() {
      this.bitField0_ &= 0xFFFFFFBF;
      this.timestamp_ = 0L;
      onChanged();
      return this;
    }
    
    public long getVersion() {
      return this.version_;
    }
    
    public Builder setVersion(long value) {
      this.version_ = value;
      this.bitField0_ |= 0x80;
      onChanged();
      return this;
    }
    
    public Builder clearVersion() {
      this.bitField0_ &= 0xFFFFFF7F;
      this.version_ = 0L;
      onChanged();
      return this;
    }
    
    public long getLeftTeamId() {
      return this.leftTeamId_;
    }
    
    public Builder setLeftTeamId(long value) {
      this.leftTeamId_ = value;
      this.bitField0_ |= 0x100;
      onChanged();
      return this;
    }
    
    public Builder clearLeftTeamId() {
      this.bitField0_ &= 0xFFFFFEFF;
      this.leftTeamId_ = 0L;
      onChanged();
      return this;
    }
    
    public long getRightTeamId() {
      return this.rightTeamId_;
    }
    
    public Builder setRightTeamId(long value) {
      this.rightTeamId_ = value;
      this.bitField0_ |= 0x200;
      onChanged();
      return this;
    }
    
    public Builder clearRightTeamId() {
      this.bitField0_ &= 0xFFFFFDFF;
      this.rightTeamId_ = 0L;
      onChanged();
      return this;
    }
    
    public long getDiffSei2AbsSecond() {
      return this.diffSei2AbsSecond_;
    }
    
    public Builder setDiffSei2AbsSecond(long value) {
      this.diffSei2AbsSecond_ = value;
      this.bitField0_ |= 0x400;
      onChanged();
      return this;
    }
    
    public Builder clearDiffSei2AbsSecond() {
      this.bitField0_ &= 0xFFFFFBFF;
      this.diffSei2AbsSecond_ = 0L;
      onChanged();
      return this;
    }
    
    public int getFinalGoalStage() {
      return this.finalGoalStage_;
    }
    
    public Builder setFinalGoalStage(int value) {
      this.finalGoalStage_ = value;
      this.bitField0_ |= 0x800;
      onChanged();
      return this;
    }
    
    public Builder clearFinalGoalStage() {
      this.bitField0_ &= 0xFFFFF7FF;
      this.finalGoalStage_ = 0;
      onChanged();
      return this;
    }
    
    public int getCurrentGoalStage() {
      return this.currentGoalStage_;
    }
    
    public Builder setCurrentGoalStage(int value) {
      this.currentGoalStage_ = value;
      this.bitField0_ |= 0x1000;
      onChanged();
      return this;
    }
    
    public Builder clearCurrentGoalStage() {
      this.bitField0_ &= 0xFFFFEFFF;
      this.currentGoalStage_ = 0;
      onChanged();
      return this;
    }
    
    public int getLeftScoreAddition() {
      return this.leftScoreAddition_;
    }
    
    public Builder setLeftScoreAddition(int value) {
      this.leftScoreAddition_ = value;
      this.bitField0_ |= 0x2000;
      onChanged();
      return this;
    }
    
    public Builder clearLeftScoreAddition() {
      this.bitField0_ &= 0xFFFFDFFF;
      this.leftScoreAddition_ = 0;
      onChanged();
      return this;
    }
    
    public int getRightScoreAddition() {
      return this.rightScoreAddition_;
    }
    
    public Builder setRightScoreAddition(int value) {
      this.rightScoreAddition_ = value;
      this.bitField0_ |= 0x4000;
      onChanged();
      return this;
    }
    
    public Builder clearRightScoreAddition() {
      this.bitField0_ &= 0xFFFFBFFF;
      this.rightScoreAddition_ = 0;
      onChanged();
      return this;
    }
    
    public long getLeftGoalInt() {
      return this.leftGoalInt_;
    }
    
    public Builder setLeftGoalInt(long value) {
      this.leftGoalInt_ = value;
      this.bitField0_ |= 0x8000;
      onChanged();
      return this;
    }
    
    public Builder clearLeftGoalInt() {
      this.bitField0_ &= 0xFFFF7FFF;
      this.leftGoalInt_ = 0L;
      onChanged();
      return this;
    }
    
    public long getRightGoalInt() {
      return this.rightGoalInt_;
    }
    
    public Builder setRightGoalInt(long value) {
      this.rightGoalInt_ = value;
      this.bitField0_ |= 0x10000;
      onChanged();
      return this;
    }
    
    public Builder clearRightGoalInt() {
      this.bitField0_ &= 0xFFFEFFFF;
      this.rightGoalInt_ = 0L;
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
  
  private static final Against DEFAULT_INSTANCE = new Against();
  
  public static Against getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }
  
  private static final Parser<Against> PARSER = (Parser<Against>)new AbstractParser<Against>() {
      public Against parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        Against.Builder builder = Against.newBuilder();
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
  
  public static Parser<Against> parser() {
    return PARSER;
  }
  
  public Parser<Against> getParserForType() {
    return PARSER;
  }
  
  public Against getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }
}
