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

public final class GiftStruct extends GeneratedMessageV3 implements GiftStructOrBuilder {
  private static final long serialVersionUID = 0L;
  
  private int bitField0_;
  
  public static final int IMAGE_FIELD_NUMBER = 1;
  
  private Image image_;
  
  public static final int DESCRIBE_FIELD_NUMBER = 2;
  
  private volatile Object describe_;
  
  public static final int NOTIFY_FIELD_NUMBER = 3;
  
  private boolean notify_;
  
  public static final int DURATION_FIELD_NUMBER = 4;
  
  private long duration_;
  
  public static final int ID_FIELD_NUMBER = 5;
  
  private long id_;
  
  public static final int FORLINKMIC_FIELD_NUMBER = 7;
  
  private boolean forLinkmic_;
  
  public static final int DOODLE_FIELD_NUMBER = 8;
  
  private boolean doodle_;
  
  public static final int FORFANSCLUB_FIELD_NUMBER = 9;
  
  private boolean forFansclub_;
  
  public static final int COMBO_FIELD_NUMBER = 10;
  
  private boolean combo_;
  
  public static final int TYPE_FIELD_NUMBER = 11;
  
  private int type_;
  
  public static final int DIAMONDCOUNT_FIELD_NUMBER = 12;
  
  private int diamondCount_;
  
  public static final int ISDISPLAYEDONPANEL_FIELD_NUMBER = 13;
  
  private boolean isDisplayedOnPanel_;
  
  public static final int PRIMARYEFFECTID_FIELD_NUMBER = 14;
  
  private long primaryEffectId_;
  
  public static final int GIFTLABELICON_FIELD_NUMBER = 15;
  
  private Image giftLabelIcon_;
  
  public static final int NAME_FIELD_NUMBER = 16;
  
  private volatile Object name_;
  
  public static final int REGION_FIELD_NUMBER = 17;
  
  private volatile Object region_;
  
  public static final int MANUAL_FIELD_NUMBER = 18;
  
  private volatile Object manual_;
  
  public static final int FORCUSTOM_FIELD_NUMBER = 19;
  
  private boolean forCustom_;
  
  public static final int ICON_FIELD_NUMBER = 21;
  
  private Image icon_;
  
  public static final int ACTIONTYPE_FIELD_NUMBER = 22;
  
  private int actionType_;
  
  private byte memoizedIsInitialized;
  
  private GiftStruct(GeneratedMessageV3.Builder<?> builder) {
    super(builder);
    this.describe_ = "";
    this.notify_ = false;
    this.duration_ = 0L;
    this.id_ = 0L;
    this.forLinkmic_ = false;
    this.doodle_ = false;
    this.forFansclub_ = false;
    this.combo_ = false;
    this.type_ = 0;
    this.diamondCount_ = 0;
    this.isDisplayedOnPanel_ = false;
    this.primaryEffectId_ = 0L;
    this.name_ = "";
    this.region_ = "";
    this.manual_ = "";
    this.forCustom_ = false;
    this.actionType_ = 0;
    this.memoizedIsInitialized = -1;
  }
  
  private GiftStruct() {
    this.describe_ = "";
    this.notify_ = false;
    this.duration_ = 0L;
    this.id_ = 0L;
    this.forLinkmic_ = false;
    this.doodle_ = false;
    this.forFansclub_ = false;
    this.combo_ = false;
    this.type_ = 0;
    this.diamondCount_ = 0;
    this.isDisplayedOnPanel_ = false;
    this.primaryEffectId_ = 0L;
    this.name_ = "";
    this.region_ = "";
    this.manual_ = "";
    this.forCustom_ = false;
    this.actionType_ = 0;
    this.memoizedIsInitialized = -1;
    this.describe_ = "";
    this.name_ = "";
    this.region_ = "";
    this.manual_ = "";
  }
  
  protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
    return new GiftStruct();
  }
  
  public static final Descriptors.Descriptor getDescriptor() {
    return Douyin.internal_static_GiftStruct_descriptor;
  }
  
  protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
    return Douyin.internal_static_GiftStruct_fieldAccessorTable.ensureFieldAccessorsInitialized(GiftStruct.class, Builder.class);
  }
  
  public boolean hasImage() {
    return ((this.bitField0_ & 0x1) != 0);
  }
  
  public Image getImage() {
    return (this.image_ == null) ? Image.getDefaultInstance() : this.image_;
  }
  
  public ImageOrBuilder getImageOrBuilder() {
    return (this.image_ == null) ? Image.getDefaultInstance() : this.image_;
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
  
  public boolean getNotify() {
    return this.notify_;
  }
  
  public long getDuration() {
    return this.duration_;
  }
  
  public long getId() {
    return this.id_;
  }
  
  public boolean getForLinkmic() {
    return this.forLinkmic_;
  }
  
  public boolean getDoodle() {
    return this.doodle_;
  }
  
  public boolean getForFansclub() {
    return this.forFansclub_;
  }
  
  public boolean getCombo() {
    return this.combo_;
  }
  
  public int getType() {
    return this.type_;
  }
  
  public int getDiamondCount() {
    return this.diamondCount_;
  }
  
  public boolean getIsDisplayedOnPanel() {
    return this.isDisplayedOnPanel_;
  }
  
  public long getPrimaryEffectId() {
    return this.primaryEffectId_;
  }
  
  public boolean hasGiftLabelIcon() {
    return ((this.bitField0_ & 0x2) != 0);
  }
  
  public Image getGiftLabelIcon() {
    return (this.giftLabelIcon_ == null) ? Image.getDefaultInstance() : this.giftLabelIcon_;
  }
  
  public ImageOrBuilder getGiftLabelIconOrBuilder() {
    return (this.giftLabelIcon_ == null) ? Image.getDefaultInstance() : this.giftLabelIcon_;
  }
  
  public String getName() {
    Object ref = this.name_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.name_ = s;
    return s;
  }
  
  public ByteString getNameBytes() {
    Object ref = this.name_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.name_ = b;
      return b;
    } 
    return (ByteString)ref;
  }
  
  public String getRegion() {
    Object ref = this.region_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.region_ = s;
    return s;
  }
  
  public ByteString getRegionBytes() {
    Object ref = this.region_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.region_ = b;
      return b;
    } 
    return (ByteString)ref;
  }
  
  public String getManual() {
    Object ref = this.manual_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.manual_ = s;
    return s;
  }
  
  public ByteString getManualBytes() {
    Object ref = this.manual_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.manual_ = b;
      return b;
    } 
    return (ByteString)ref;
  }
  
  public boolean getForCustom() {
    return this.forCustom_;
  }
  
  public boolean hasIcon() {
    return ((this.bitField0_ & 0x4) != 0);
  }
  
  public Image getIcon() {
    return (this.icon_ == null) ? Image.getDefaultInstance() : this.icon_;
  }
  
  public ImageOrBuilder getIconOrBuilder() {
    return (this.icon_ == null) ? Image.getDefaultInstance() : this.icon_;
  }
  
  public int getActionType() {
    return this.actionType_;
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
      output.writeMessage(1, (MessageLite)getImage()); 
    if (!GeneratedMessageV3.isStringEmpty(this.describe_))
      GeneratedMessageV3.writeString(output, 2, this.describe_); 
    if (this.notify_)
      output.writeBool(3, this.notify_); 
    if (this.duration_ != 0L)
      output.writeUInt64(4, this.duration_); 
    if (this.id_ != 0L)
      output.writeUInt64(5, this.id_); 
    if (this.forLinkmic_)
      output.writeBool(7, this.forLinkmic_); 
    if (this.doodle_)
      output.writeBool(8, this.doodle_); 
    if (this.forFansclub_)
      output.writeBool(9, this.forFansclub_); 
    if (this.combo_)
      output.writeBool(10, this.combo_); 
    if (this.type_ != 0)
      output.writeUInt32(11, this.type_); 
    if (this.diamondCount_ != 0)
      output.writeUInt32(12, this.diamondCount_); 
    if (this.isDisplayedOnPanel_)
      output.writeBool(13, this.isDisplayedOnPanel_); 
    if (this.primaryEffectId_ != 0L)
      output.writeUInt64(14, this.primaryEffectId_); 
    if ((this.bitField0_ & 0x2) != 0)
      output.writeMessage(15, (MessageLite)getGiftLabelIcon()); 
    if (!GeneratedMessageV3.isStringEmpty(this.name_))
      GeneratedMessageV3.writeString(output, 16, this.name_); 
    if (!GeneratedMessageV3.isStringEmpty(this.region_))
      GeneratedMessageV3.writeString(output, 17, this.region_); 
    if (!GeneratedMessageV3.isStringEmpty(this.manual_))
      GeneratedMessageV3.writeString(output, 18, this.manual_); 
    if (this.forCustom_)
      output.writeBool(19, this.forCustom_); 
    if ((this.bitField0_ & 0x4) != 0)
      output.writeMessage(21, (MessageLite)getIcon()); 
    if (this.actionType_ != 0)
      output.writeUInt32(22, this.actionType_); 
    getUnknownFields().writeTo(output);
  }
  
  public int getSerializedSize() {
    int size = this.memoizedSize;
    if (size != -1)
      return size; 
    size = 0;
    if ((this.bitField0_ & 0x1) != 0)
      size += 
        CodedOutputStream.computeMessageSize(1, (MessageLite)getImage()); 
    if (!GeneratedMessageV3.isStringEmpty(this.describe_))
      size += GeneratedMessageV3.computeStringSize(2, this.describe_); 
    if (this.notify_)
      size += 
        CodedOutputStream.computeBoolSize(3, this.notify_); 
    if (this.duration_ != 0L)
      size += 
        CodedOutputStream.computeUInt64Size(4, this.duration_); 
    if (this.id_ != 0L)
      size += 
        CodedOutputStream.computeUInt64Size(5, this.id_); 
    if (this.forLinkmic_)
      size += 
        CodedOutputStream.computeBoolSize(7, this.forLinkmic_); 
    if (this.doodle_)
      size += 
        CodedOutputStream.computeBoolSize(8, this.doodle_); 
    if (this.forFansclub_)
      size += 
        CodedOutputStream.computeBoolSize(9, this.forFansclub_); 
    if (this.combo_)
      size += 
        CodedOutputStream.computeBoolSize(10, this.combo_); 
    if (this.type_ != 0)
      size += 
        CodedOutputStream.computeUInt32Size(11, this.type_); 
    if (this.diamondCount_ != 0)
      size += 
        CodedOutputStream.computeUInt32Size(12, this.diamondCount_); 
    if (this.isDisplayedOnPanel_)
      size += 
        CodedOutputStream.computeBoolSize(13, this.isDisplayedOnPanel_); 
    if (this.primaryEffectId_ != 0L)
      size += 
        CodedOutputStream.computeUInt64Size(14, this.primaryEffectId_); 
    if ((this.bitField0_ & 0x2) != 0)
      size += 
        CodedOutputStream.computeMessageSize(15, (MessageLite)getGiftLabelIcon()); 
    if (!GeneratedMessageV3.isStringEmpty(this.name_))
      size += GeneratedMessageV3.computeStringSize(16, this.name_); 
    if (!GeneratedMessageV3.isStringEmpty(this.region_))
      size += GeneratedMessageV3.computeStringSize(17, this.region_); 
    if (!GeneratedMessageV3.isStringEmpty(this.manual_))
      size += GeneratedMessageV3.computeStringSize(18, this.manual_); 
    if (this.forCustom_)
      size += 
        CodedOutputStream.computeBoolSize(19, this.forCustom_); 
    if ((this.bitField0_ & 0x4) != 0)
      size += 
        CodedOutputStream.computeMessageSize(21, (MessageLite)getIcon()); 
    if (this.actionType_ != 0)
      size += 
        CodedOutputStream.computeUInt32Size(22, this.actionType_); 
    size += getUnknownFields().getSerializedSize();
    this.memoizedSize = size;
    return size;
  }
  
  public boolean equals(Object obj) {
    if (obj == this)
      return true; 
    if (!(obj instanceof GiftStruct))
      return super.equals(obj); 
    GiftStruct other = (GiftStruct)obj;
    if (hasImage() != other.hasImage())
      return false; 
    if (hasImage() && 
      
      !getImage().equals(other.getImage()))
      return false; 
    if (!getDescribe().equals(other.getDescribe()))
      return false; 
    if (getNotify() != other
      .getNotify())
      return false; 
    if (getDuration() != other
      .getDuration())
      return false; 
    if (getId() != other
      .getId())
      return false; 
    if (getForLinkmic() != other
      .getForLinkmic())
      return false; 
    if (getDoodle() != other
      .getDoodle())
      return false; 
    if (getForFansclub() != other
      .getForFansclub())
      return false; 
    if (getCombo() != other
      .getCombo())
      return false; 
    if (getType() != other
      .getType())
      return false; 
    if (getDiamondCount() != other
      .getDiamondCount())
      return false; 
    if (getIsDisplayedOnPanel() != other
      .getIsDisplayedOnPanel())
      return false; 
    if (getPrimaryEffectId() != other
      .getPrimaryEffectId())
      return false; 
    if (hasGiftLabelIcon() != other.hasGiftLabelIcon())
      return false; 
    if (hasGiftLabelIcon() && 
      
      !getGiftLabelIcon().equals(other.getGiftLabelIcon()))
      return false; 
    if (!getName().equals(other.getName()))
      return false; 
    if (!getRegion().equals(other.getRegion()))
      return false; 
    if (!getManual().equals(other.getManual()))
      return false; 
    if (getForCustom() != other
      .getForCustom())
      return false; 
    if (hasIcon() != other.hasIcon())
      return false; 
    if (hasIcon() && 
      
      !getIcon().equals(other.getIcon()))
      return false; 
    if (getActionType() != other
      .getActionType())
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
    if (hasImage()) {
      hash = 37 * hash + 1;
      hash = 53 * hash + getImage().hashCode();
    } 
    hash = 37 * hash + 2;
    hash = 53 * hash + getDescribe().hashCode();
    hash = 37 * hash + 3;
    hash = 53 * hash + Internal.hashBoolean(
        getNotify());
    hash = 37 * hash + 4;
    hash = 53 * hash + Internal.hashLong(
        getDuration());
    hash = 37 * hash + 5;
    hash = 53 * hash + Internal.hashLong(
        getId());
    hash = 37 * hash + 7;
    hash = 53 * hash + Internal.hashBoolean(
        getForLinkmic());
    hash = 37 * hash + 8;
    hash = 53 * hash + Internal.hashBoolean(
        getDoodle());
    hash = 37 * hash + 9;
    hash = 53 * hash + Internal.hashBoolean(
        getForFansclub());
    hash = 37 * hash + 10;
    hash = 53 * hash + Internal.hashBoolean(
        getCombo());
    hash = 37 * hash + 11;
    hash = 53 * hash + getType();
    hash = 37 * hash + 12;
    hash = 53 * hash + getDiamondCount();
    hash = 37 * hash + 13;
    hash = 53 * hash + Internal.hashBoolean(
        getIsDisplayedOnPanel());
    hash = 37 * hash + 14;
    hash = 53 * hash + Internal.hashLong(
        getPrimaryEffectId());
    if (hasGiftLabelIcon()) {
      hash = 37 * hash + 15;
      hash = 53 * hash + getGiftLabelIcon().hashCode();
    } 
    hash = 37 * hash + 16;
    hash = 53 * hash + getName().hashCode();
    hash = 37 * hash + 17;
    hash = 53 * hash + getRegion().hashCode();
    hash = 37 * hash + 18;
    hash = 53 * hash + getManual().hashCode();
    hash = 37 * hash + 19;
    hash = 53 * hash + Internal.hashBoolean(
        getForCustom());
    if (hasIcon()) {
      hash = 37 * hash + 21;
      hash = 53 * hash + getIcon().hashCode();
    } 
    hash = 37 * hash + 22;
    hash = 53 * hash + getActionType();
    hash = 29 * hash + getUnknownFields().hashCode();
    this.memoizedHashCode = hash;
    return hash;
  }
  
  public static GiftStruct parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
    return (GiftStruct)PARSER.parseFrom(data);
  }
  
  public static GiftStruct parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (GiftStruct)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static GiftStruct parseFrom(ByteString data) throws InvalidProtocolBufferException {
    return (GiftStruct)PARSER.parseFrom(data);
  }
  
  public static GiftStruct parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (GiftStruct)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static GiftStruct parseFrom(byte[] data) throws InvalidProtocolBufferException {
    return (GiftStruct)PARSER.parseFrom(data);
  }
  
  public static GiftStruct parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (GiftStruct)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static GiftStruct parseFrom(InputStream input) throws IOException {
    return 
      (GiftStruct)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static GiftStruct parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (GiftStruct)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static GiftStruct parseDelimitedFrom(InputStream input) throws IOException {
    return 
      (GiftStruct)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
  }
  
  public static GiftStruct parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (GiftStruct)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static GiftStruct parseFrom(CodedInputStream input) throws IOException {
    return 
      (GiftStruct)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static GiftStruct parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (GiftStruct)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public Builder newBuilderForType() {
    return newBuilder();
  }
  
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  
  public static Builder newBuilder(GiftStruct prototype) {
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
  
  public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements GiftStructOrBuilder {
    private int bitField0_;
    
    private Image image_;
    
    private SingleFieldBuilderV3<Image, Image.Builder, ImageOrBuilder> imageBuilder_;
    
    private Object describe_;
    
    private boolean notify_;
    
    private long duration_;
    
    private long id_;
    
    private boolean forLinkmic_;
    
    private boolean doodle_;
    
    private boolean forFansclub_;
    
    private boolean combo_;
    
    private int type_;
    
    private int diamondCount_;
    
    private boolean isDisplayedOnPanel_;
    
    private long primaryEffectId_;
    
    private Image giftLabelIcon_;
    
    private SingleFieldBuilderV3<Image, Image.Builder, ImageOrBuilder> giftLabelIconBuilder_;
    
    private Object name_;
    
    private Object region_;
    
    private Object manual_;
    
    private boolean forCustom_;
    
    private Image icon_;
    
    private SingleFieldBuilderV3<Image, Image.Builder, ImageOrBuilder> iconBuilder_;
    
    private int actionType_;
    
    public static final Descriptors.Descriptor getDescriptor() {
      return Douyin.internal_static_GiftStruct_descriptor;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return Douyin.internal_static_GiftStruct_fieldAccessorTable
        .ensureFieldAccessorsInitialized(GiftStruct.class, Builder.class);
    }
    
    private Builder() {
      this.describe_ = "";
      this.name_ = "";
      this.region_ = "";
      this.manual_ = "";
      maybeForceBuilderInitialization();
    }
    
    private Builder(GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      this.describe_ = "";
      this.name_ = "";
      this.region_ = "";
      this.manual_ = "";
      maybeForceBuilderInitialization();
    }
    
    private void maybeForceBuilderInitialization() {
      if (GiftStruct.alwaysUseFieldBuilders) {
        getImageFieldBuilder();
        getGiftLabelIconFieldBuilder();
        getIconFieldBuilder();
      } 
    }
    
    public Builder clear() {
      super.clear();
      this.bitField0_ = 0;
      this.image_ = null;
      if (this.imageBuilder_ != null) {
        this.imageBuilder_.dispose();
        this.imageBuilder_ = null;
      } 
      this.describe_ = "";
      this.notify_ = false;
      this.duration_ = 0L;
      this.id_ = 0L;
      this.forLinkmic_ = false;
      this.doodle_ = false;
      this.forFansclub_ = false;
      this.combo_ = false;
      this.type_ = 0;
      this.diamondCount_ = 0;
      this.isDisplayedOnPanel_ = false;
      this.primaryEffectId_ = 0L;
      this.giftLabelIcon_ = null;
      if (this.giftLabelIconBuilder_ != null) {
        this.giftLabelIconBuilder_.dispose();
        this.giftLabelIconBuilder_ = null;
      } 
      this.name_ = "";
      this.region_ = "";
      this.manual_ = "";
      this.forCustom_ = false;
      this.icon_ = null;
      if (this.iconBuilder_ != null) {
        this.iconBuilder_.dispose();
        this.iconBuilder_ = null;
      } 
      this.actionType_ = 0;
      return this;
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
      return Douyin.internal_static_GiftStruct_descriptor;
    }
    
    public GiftStruct getDefaultInstanceForType() {
      return GiftStruct.getDefaultInstance();
    }
    
    public GiftStruct build() {
      GiftStruct result = buildPartial();
      if (!result.isInitialized())
        throw newUninitializedMessageException(result); 
      return result;
    }
    
    public GiftStruct buildPartial() {
      GiftStruct result = new GiftStruct(this);
      if (this.bitField0_ != 0)
        buildPartial0(result); 
      onBuilt();
      return result;
    }
    
    private void buildPartial0(GiftStruct result) {
      int from_bitField0_ = this.bitField0_;
      int to_bitField0_ = 0;
      if ((from_bitField0_ & 0x1) != 0) {
        result.image_ = (this.imageBuilder_ == null) ? this.image_ : (Image)this.imageBuilder_.build();
        to_bitField0_ |= 0x1;
      } 
      if ((from_bitField0_ & 0x2) != 0)
        result.describe_ = this.describe_; 
      if ((from_bitField0_ & 0x4) != 0)
        result.notify_ = this.notify_; 
      if ((from_bitField0_ & 0x8) != 0)
        result.duration_ = this.duration_; 
      if ((from_bitField0_ & 0x10) != 0)
        result.id_ = this.id_; 
      if ((from_bitField0_ & 0x20) != 0)
        result.forLinkmic_ = this.forLinkmic_; 
      if ((from_bitField0_ & 0x40) != 0)
        result.doodle_ = this.doodle_; 
      if ((from_bitField0_ & 0x80) != 0)
        result.forFansclub_ = this.forFansclub_; 
      if ((from_bitField0_ & 0x100) != 0)
        result.combo_ = this.combo_; 
      if ((from_bitField0_ & 0x200) != 0)
        result.type_ = this.type_; 
      if ((from_bitField0_ & 0x400) != 0)
        result.diamondCount_ = this.diamondCount_; 
      if ((from_bitField0_ & 0x800) != 0)
        result.isDisplayedOnPanel_ = this.isDisplayedOnPanel_; 
      if ((from_bitField0_ & 0x1000) != 0)
        result.primaryEffectId_ = this.primaryEffectId_; 
      if ((from_bitField0_ & 0x2000) != 0) {
        result.giftLabelIcon_ = (this.giftLabelIconBuilder_ == null) ? this.giftLabelIcon_ : (Image)this.giftLabelIconBuilder_.build();
        to_bitField0_ |= 0x2;
      } 
      if ((from_bitField0_ & 0x4000) != 0)
        result.name_ = this.name_; 
      if ((from_bitField0_ & 0x8000) != 0)
        result.region_ = this.region_; 
      if ((from_bitField0_ & 0x10000) != 0)
        result.manual_ = this.manual_; 
      if ((from_bitField0_ & 0x20000) != 0)
        result.forCustom_ = this.forCustom_; 
      if ((from_bitField0_ & 0x40000) != 0) {
        result.icon_ = (this.iconBuilder_ == null) ? this.icon_ : (Image)this.iconBuilder_.build();
        to_bitField0_ |= 0x4;
      } 
      if ((from_bitField0_ & 0x80000) != 0)
        result.actionType_ = this.actionType_; 
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
      if (other instanceof GiftStruct)
        return mergeFrom((GiftStruct)other); 
      super.mergeFrom(other);
      return this;
    }
    
    public Builder mergeFrom(GiftStruct other) {
      if (other == GiftStruct.getDefaultInstance())
        return this; 
      if (other.hasImage())
        mergeImage(other.getImage()); 
      if (!other.getDescribe().isEmpty()) {
        this.describe_ = other.describe_;
        this.bitField0_ |= 0x2;
        onChanged();
      } 
      if (other.getNotify())
        setNotify(other.getNotify()); 
      if (other.getDuration() != 0L)
        setDuration(other.getDuration()); 
      if (other.getId() != 0L)
        setId(other.getId()); 
      if (other.getForLinkmic())
        setForLinkmic(other.getForLinkmic()); 
      if (other.getDoodle())
        setDoodle(other.getDoodle()); 
      if (other.getForFansclub())
        setForFansclub(other.getForFansclub()); 
      if (other.getCombo())
        setCombo(other.getCombo()); 
      if (other.getType() != 0)
        setType(other.getType()); 
      if (other.getDiamondCount() != 0)
        setDiamondCount(other.getDiamondCount()); 
      if (other.getIsDisplayedOnPanel())
        setIsDisplayedOnPanel(other.getIsDisplayedOnPanel()); 
      if (other.getPrimaryEffectId() != 0L)
        setPrimaryEffectId(other.getPrimaryEffectId()); 
      if (other.hasGiftLabelIcon())
        mergeGiftLabelIcon(other.getGiftLabelIcon()); 
      if (!other.getName().isEmpty()) {
        this.name_ = other.name_;
        this.bitField0_ |= 0x4000;
        onChanged();
      } 
      if (!other.getRegion().isEmpty()) {
        this.region_ = other.region_;
        this.bitField0_ |= 0x8000;
        onChanged();
      } 
      if (!other.getManual().isEmpty()) {
        this.manual_ = other.manual_;
        this.bitField0_ |= 0x10000;
        onChanged();
      } 
      if (other.getForCustom())
        setForCustom(other.getForCustom()); 
      if (other.hasIcon())
        mergeIcon(other.getIcon()); 
      if (other.getActionType() != 0)
        setActionType(other.getActionType()); 
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
              input.readMessage((MessageLite.Builder)getImageFieldBuilder().getBuilder(), extensionRegistry);
              this.bitField0_ |= 0x1;
              continue;
            case 18:
              this.describe_ = input.readStringRequireUtf8();
              this.bitField0_ |= 0x2;
              continue;
            case 24:
              this.notify_ = input.readBool();
              this.bitField0_ |= 0x4;
              continue;
            case 32:
              this.duration_ = input.readUInt64();
              this.bitField0_ |= 0x8;
              continue;
            case 40:
              this.id_ = input.readUInt64();
              this.bitField0_ |= 0x10;
              continue;
            case 56:
              this.forLinkmic_ = input.readBool();
              this.bitField0_ |= 0x20;
              continue;
            case 64:
              this.doodle_ = input.readBool();
              this.bitField0_ |= 0x40;
              continue;
            case 72:
              this.forFansclub_ = input.readBool();
              this.bitField0_ |= 0x80;
              continue;
            case 80:
              this.combo_ = input.readBool();
              this.bitField0_ |= 0x100;
              continue;
            case 88:
              this.type_ = input.readUInt32();
              this.bitField0_ |= 0x200;
              continue;
            case 96:
              this.diamondCount_ = input.readUInt32();
              this.bitField0_ |= 0x400;
              continue;
            case 104:
              this.isDisplayedOnPanel_ = input.readBool();
              this.bitField0_ |= 0x800;
              continue;
            case 112:
              this.primaryEffectId_ = input.readUInt64();
              this.bitField0_ |= 0x1000;
              continue;
            case 122:
              input.readMessage((MessageLite.Builder)getGiftLabelIconFieldBuilder().getBuilder(), extensionRegistry);
              this.bitField0_ |= 0x2000;
              continue;
            case 130:
              this.name_ = input.readStringRequireUtf8();
              this.bitField0_ |= 0x4000;
              continue;
            case 138:
              this.region_ = input.readStringRequireUtf8();
              this.bitField0_ |= 0x8000;
              continue;
            case 146:
              this.manual_ = input.readStringRequireUtf8();
              this.bitField0_ |= 0x10000;
              continue;
            case 152:
              this.forCustom_ = input.readBool();
              this.bitField0_ |= 0x20000;
              continue;
            case 170:
              input.readMessage((MessageLite.Builder)getIconFieldBuilder().getBuilder(), extensionRegistry);
              this.bitField0_ |= 0x40000;
              continue;
            case 176:
              this.actionType_ = input.readUInt32();
              this.bitField0_ |= 0x80000;
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
    
    public boolean hasImage() {
      return ((this.bitField0_ & 0x1) != 0);
    }
    
    public Image getImage() {
      if (this.imageBuilder_ == null)
        return (this.image_ == null) ? Image.getDefaultInstance() : this.image_; 
      return (Image)this.imageBuilder_.getMessage();
    }
    
    public Builder setImage(Image value) {
      if (this.imageBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        this.image_ = value;
      } else {
        this.imageBuilder_.setMessage((AbstractMessage)value);
      } 
      this.bitField0_ |= 0x1;
      onChanged();
      return this;
    }
    
    public Builder setImage(Image.Builder builderForValue) {
      if (this.imageBuilder_ == null) {
        this.image_ = builderForValue.build();
      } else {
        this.imageBuilder_.setMessage((AbstractMessage)builderForValue.build());
      } 
      this.bitField0_ |= 0x1;
      onChanged();
      return this;
    }
    
    public Builder mergeImage(Image value) {
      if (this.imageBuilder_ == null) {
        if ((this.bitField0_ & 0x1) != 0 && this.image_ != null && this.image_ != Image.getDefaultInstance()) {
          getImageBuilder().mergeFrom(value);
        } else {
          this.image_ = value;
        } 
      } else {
        this.imageBuilder_.mergeFrom((AbstractMessage)value);
      } 
      if (this.image_ != null) {
        this.bitField0_ |= 0x1;
        onChanged();
      } 
      return this;
    }
    
    public Builder clearImage() {
      this.bitField0_ &= 0xFFFFFFFE;
      this.image_ = null;
      if (this.imageBuilder_ != null) {
        this.imageBuilder_.dispose();
        this.imageBuilder_ = null;
      } 
      onChanged();
      return this;
    }
    
    public Image.Builder getImageBuilder() {
      this.bitField0_ |= 0x1;
      onChanged();
      return (Image.Builder)getImageFieldBuilder().getBuilder();
    }
    
    public ImageOrBuilder getImageOrBuilder() {
      if (this.imageBuilder_ != null)
        return (ImageOrBuilder)this.imageBuilder_.getMessageOrBuilder(); 
      return (this.image_ == null) ? Image.getDefaultInstance() : this.image_;
    }
    
    private SingleFieldBuilderV3<Image, Image.Builder, ImageOrBuilder> getImageFieldBuilder() {
      if (this.imageBuilder_ == null) {
        this.imageBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getImage(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.image_ = null;
      } 
      return this.imageBuilder_;
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
      this.bitField0_ |= 0x2;
      onChanged();
      return this;
    }
    
    public Builder clearDescribe() {
      this.describe_ = GiftStruct.getDefaultInstance().getDescribe();
      this.bitField0_ &= 0xFFFFFFFD;
      onChanged();
      return this;
    }
    
    public Builder setDescribeBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      GiftStruct.checkByteStringIsUtf8(value);
      this.describe_ = value;
      this.bitField0_ |= 0x2;
      onChanged();
      return this;
    }
    
    public boolean getNotify() {
      return this.notify_;
    }
    
    public Builder setNotify(boolean value) {
      this.notify_ = value;
      this.bitField0_ |= 0x4;
      onChanged();
      return this;
    }
    
    public Builder clearNotify() {
      this.bitField0_ &= 0xFFFFFFFB;
      this.notify_ = false;
      onChanged();
      return this;
    }
    
    public long getDuration() {
      return this.duration_;
    }
    
    public Builder setDuration(long value) {
      this.duration_ = value;
      this.bitField0_ |= 0x8;
      onChanged();
      return this;
    }
    
    public Builder clearDuration() {
      this.bitField0_ &= 0xFFFFFFF7;
      this.duration_ = 0L;
      onChanged();
      return this;
    }
    
    public long getId() {
      return this.id_;
    }
    
    public Builder setId(long value) {
      this.id_ = value;
      this.bitField0_ |= 0x10;
      onChanged();
      return this;
    }
    
    public Builder clearId() {
      this.bitField0_ &= 0xFFFFFFEF;
      this.id_ = 0L;
      onChanged();
      return this;
    }
    
    public boolean getForLinkmic() {
      return this.forLinkmic_;
    }
    
    public Builder setForLinkmic(boolean value) {
      this.forLinkmic_ = value;
      this.bitField0_ |= 0x20;
      onChanged();
      return this;
    }
    
    public Builder clearForLinkmic() {
      this.bitField0_ &= 0xFFFFFFDF;
      this.forLinkmic_ = false;
      onChanged();
      return this;
    }
    
    public boolean getDoodle() {
      return this.doodle_;
    }
    
    public Builder setDoodle(boolean value) {
      this.doodle_ = value;
      this.bitField0_ |= 0x40;
      onChanged();
      return this;
    }
    
    public Builder clearDoodle() {
      this.bitField0_ &= 0xFFFFFFBF;
      this.doodle_ = false;
      onChanged();
      return this;
    }
    
    public boolean getForFansclub() {
      return this.forFansclub_;
    }
    
    public Builder setForFansclub(boolean value) {
      this.forFansclub_ = value;
      this.bitField0_ |= 0x80;
      onChanged();
      return this;
    }
    
    public Builder clearForFansclub() {
      this.bitField0_ &= 0xFFFFFF7F;
      this.forFansclub_ = false;
      onChanged();
      return this;
    }
    
    public boolean getCombo() {
      return this.combo_;
    }
    
    public Builder setCombo(boolean value) {
      this.combo_ = value;
      this.bitField0_ |= 0x100;
      onChanged();
      return this;
    }
    
    public Builder clearCombo() {
      this.bitField0_ &= 0xFFFFFEFF;
      this.combo_ = false;
      onChanged();
      return this;
    }
    
    public int getType() {
      return this.type_;
    }
    
    public Builder setType(int value) {
      this.type_ = value;
      this.bitField0_ |= 0x200;
      onChanged();
      return this;
    }
    
    public Builder clearType() {
      this.bitField0_ &= 0xFFFFFDFF;
      this.type_ = 0;
      onChanged();
      return this;
    }
    
    public int getDiamondCount() {
      return this.diamondCount_;
    }
    
    public Builder setDiamondCount(int value) {
      this.diamondCount_ = value;
      this.bitField0_ |= 0x400;
      onChanged();
      return this;
    }
    
    public Builder clearDiamondCount() {
      this.bitField0_ &= 0xFFFFFBFF;
      this.diamondCount_ = 0;
      onChanged();
      return this;
    }
    
    public boolean getIsDisplayedOnPanel() {
      return this.isDisplayedOnPanel_;
    }
    
    public Builder setIsDisplayedOnPanel(boolean value) {
      this.isDisplayedOnPanel_ = value;
      this.bitField0_ |= 0x800;
      onChanged();
      return this;
    }
    
    public Builder clearIsDisplayedOnPanel() {
      this.bitField0_ &= 0xFFFFF7FF;
      this.isDisplayedOnPanel_ = false;
      onChanged();
      return this;
    }
    
    public long getPrimaryEffectId() {
      return this.primaryEffectId_;
    }
    
    public Builder setPrimaryEffectId(long value) {
      this.primaryEffectId_ = value;
      this.bitField0_ |= 0x1000;
      onChanged();
      return this;
    }
    
    public Builder clearPrimaryEffectId() {
      this.bitField0_ &= 0xFFFFEFFF;
      this.primaryEffectId_ = 0L;
      onChanged();
      return this;
    }
    
    public boolean hasGiftLabelIcon() {
      return ((this.bitField0_ & 0x2000) != 0);
    }
    
    public Image getGiftLabelIcon() {
      if (this.giftLabelIconBuilder_ == null)
        return (this.giftLabelIcon_ == null) ? Image.getDefaultInstance() : this.giftLabelIcon_; 
      return (Image)this.giftLabelIconBuilder_.getMessage();
    }
    
    public Builder setGiftLabelIcon(Image value) {
      if (this.giftLabelIconBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        this.giftLabelIcon_ = value;
      } else {
        this.giftLabelIconBuilder_.setMessage((AbstractMessage)value);
      } 
      this.bitField0_ |= 0x2000;
      onChanged();
      return this;
    }
    
    public Builder setGiftLabelIcon(Image.Builder builderForValue) {
      if (this.giftLabelIconBuilder_ == null) {
        this.giftLabelIcon_ = builderForValue.build();
      } else {
        this.giftLabelIconBuilder_.setMessage((AbstractMessage)builderForValue.build());
      } 
      this.bitField0_ |= 0x2000;
      onChanged();
      return this;
    }
    
    public Builder mergeGiftLabelIcon(Image value) {
      if (this.giftLabelIconBuilder_ == null) {
        if ((this.bitField0_ & 0x2000) != 0 && this.giftLabelIcon_ != null && this.giftLabelIcon_ != Image.getDefaultInstance()) {
          getGiftLabelIconBuilder().mergeFrom(value);
        } else {
          this.giftLabelIcon_ = value;
        } 
      } else {
        this.giftLabelIconBuilder_.mergeFrom((AbstractMessage)value);
      } 
      if (this.giftLabelIcon_ != null) {
        this.bitField0_ |= 0x2000;
        onChanged();
      } 
      return this;
    }
    
    public Builder clearGiftLabelIcon() {
      this.bitField0_ &= 0xFFFFDFFF;
      this.giftLabelIcon_ = null;
      if (this.giftLabelIconBuilder_ != null) {
        this.giftLabelIconBuilder_.dispose();
        this.giftLabelIconBuilder_ = null;
      } 
      onChanged();
      return this;
    }
    
    public Image.Builder getGiftLabelIconBuilder() {
      this.bitField0_ |= 0x2000;
      onChanged();
      return (Image.Builder)getGiftLabelIconFieldBuilder().getBuilder();
    }
    
    public ImageOrBuilder getGiftLabelIconOrBuilder() {
      if (this.giftLabelIconBuilder_ != null)
        return (ImageOrBuilder)this.giftLabelIconBuilder_.getMessageOrBuilder(); 
      return (this.giftLabelIcon_ == null) ? Image.getDefaultInstance() : this.giftLabelIcon_;
    }
    
    private SingleFieldBuilderV3<Image, Image.Builder, ImageOrBuilder> getGiftLabelIconFieldBuilder() {
      if (this.giftLabelIconBuilder_ == null) {
        this.giftLabelIconBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getGiftLabelIcon(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.giftLabelIcon_ = null;
      } 
      return this.giftLabelIconBuilder_;
    }
    
    public String getName() {
      Object ref = this.name_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.name_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getNameBytes() {
      Object ref = this.name_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.name_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setName(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.name_ = value;
      this.bitField0_ |= 0x4000;
      onChanged();
      return this;
    }
    
    public Builder clearName() {
      this.name_ = GiftStruct.getDefaultInstance().getName();
      this.bitField0_ &= 0xFFFFBFFF;
      onChanged();
      return this;
    }
    
    public Builder setNameBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      GiftStruct.checkByteStringIsUtf8(value);
      this.name_ = value;
      this.bitField0_ |= 0x4000;
      onChanged();
      return this;
    }
    
    public String getRegion() {
      Object ref = this.region_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.region_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getRegionBytes() {
      Object ref = this.region_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.region_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setRegion(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.region_ = value;
      this.bitField0_ |= 0x8000;
      onChanged();
      return this;
    }
    
    public Builder clearRegion() {
      this.region_ = GiftStruct.getDefaultInstance().getRegion();
      this.bitField0_ &= 0xFFFF7FFF;
      onChanged();
      return this;
    }
    
    public Builder setRegionBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      GiftStruct.checkByteStringIsUtf8(value);
      this.region_ = value;
      this.bitField0_ |= 0x8000;
      onChanged();
      return this;
    }
    
    public String getManual() {
      Object ref = this.manual_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.manual_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getManualBytes() {
      Object ref = this.manual_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.manual_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setManual(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.manual_ = value;
      this.bitField0_ |= 0x10000;
      onChanged();
      return this;
    }
    
    public Builder clearManual() {
      this.manual_ = GiftStruct.getDefaultInstance().getManual();
      this.bitField0_ &= 0xFFFEFFFF;
      onChanged();
      return this;
    }
    
    public Builder setManualBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      GiftStruct.checkByteStringIsUtf8(value);
      this.manual_ = value;
      this.bitField0_ |= 0x10000;
      onChanged();
      return this;
    }
    
    public boolean getForCustom() {
      return this.forCustom_;
    }
    
    public Builder setForCustom(boolean value) {
      this.forCustom_ = value;
      this.bitField0_ |= 0x20000;
      onChanged();
      return this;
    }
    
    public Builder clearForCustom() {
      this.bitField0_ &= 0xFFFDFFFF;
      this.forCustom_ = false;
      onChanged();
      return this;
    }
    
    public boolean hasIcon() {
      return ((this.bitField0_ & 0x40000) != 0);
    }
    
    public Image getIcon() {
      if (this.iconBuilder_ == null)
        return (this.icon_ == null) ? Image.getDefaultInstance() : this.icon_; 
      return (Image)this.iconBuilder_.getMessage();
    }
    
    public Builder setIcon(Image value) {
      if (this.iconBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        this.icon_ = value;
      } else {
        this.iconBuilder_.setMessage((AbstractMessage)value);
      } 
      this.bitField0_ |= 0x40000;
      onChanged();
      return this;
    }
    
    public Builder setIcon(Image.Builder builderForValue) {
      if (this.iconBuilder_ == null) {
        this.icon_ = builderForValue.build();
      } else {
        this.iconBuilder_.setMessage((AbstractMessage)builderForValue.build());
      } 
      this.bitField0_ |= 0x40000;
      onChanged();
      return this;
    }
    
    public Builder mergeIcon(Image value) {
      if (this.iconBuilder_ == null) {
        if ((this.bitField0_ & 0x40000) != 0 && this.icon_ != null && this.icon_ != 
          
          Image.getDefaultInstance()) {
          getIconBuilder().mergeFrom(value);
        } else {
          this.icon_ = value;
        } 
      } else {
        this.iconBuilder_.mergeFrom((AbstractMessage)value);
      } 
      if (this.icon_ != null) {
        this.bitField0_ |= 0x40000;
        onChanged();
      } 
      return this;
    }
    
    public Builder clearIcon() {
      this.bitField0_ &= 0xFFFBFFFF;
      this.icon_ = null;
      if (this.iconBuilder_ != null) {
        this.iconBuilder_.dispose();
        this.iconBuilder_ = null;
      } 
      onChanged();
      return this;
    }
    
    public Image.Builder getIconBuilder() {
      this.bitField0_ |= 0x40000;
      onChanged();
      return (Image.Builder)getIconFieldBuilder().getBuilder();
    }
    
    public ImageOrBuilder getIconOrBuilder() {
      if (this.iconBuilder_ != null)
        return (ImageOrBuilder)this.iconBuilder_.getMessageOrBuilder(); 
      return (this.icon_ == null) ? 
        Image.getDefaultInstance() : this.icon_;
    }
    
    private SingleFieldBuilderV3<Image, Image.Builder, ImageOrBuilder> getIconFieldBuilder() {
      if (this.iconBuilder_ == null) {
        this
          
          .iconBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getIcon(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.icon_ = null;
      } 
      return this.iconBuilder_;
    }
    
    public int getActionType() {
      return this.actionType_;
    }
    
    public Builder setActionType(int value) {
      this.actionType_ = value;
      this.bitField0_ |= 0x80000;
      onChanged();
      return this;
    }
    
    public Builder clearActionType() {
      this.bitField0_ &= 0xFFF7FFFF;
      this.actionType_ = 0;
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
  
  private static final GiftStruct DEFAULT_INSTANCE = new GiftStruct();
  
  public static GiftStruct getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }
  
  private static final Parser<GiftStruct> PARSER = (Parser<GiftStruct>)new AbstractParser<GiftStruct>() {
      public GiftStruct parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        GiftStruct.Builder builder = GiftStruct.newBuilder();
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
  
  public static Parser<GiftStruct> parser() {
    return PARSER;
  }
  
  public Parser<GiftStruct> getParserForType() {
    return PARSER;
  }
  
  public GiftStruct getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }
}
