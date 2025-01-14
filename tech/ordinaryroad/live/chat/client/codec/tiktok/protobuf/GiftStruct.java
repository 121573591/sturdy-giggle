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

public final class GiftStruct extends GeneratedMessageV3 implements GiftStructOrBuilder {
  private static final long serialVersionUID = 0L;
  
  private int bitField0_;
  
  public static final int IMAGE_FIELD_NUMBER = 1;
  
  private Image image_;
  
  public static final int DESCRIBE_FIELD_NUMBER = 2;
  
  private volatile Object describe_;
  
  public static final int DURATION_FIELD_NUMBER = 4;
  
  private long duration_;
  
  public static final int ID_FIELD_NUMBER = 5;
  
  private long id_;
  
  public static final int FORLINKMIC_FIELD_NUMBER = 7;
  
  private boolean forLinkmic_;
  
  public static final int COMBO_FIELD_NUMBER = 10;
  
  private boolean combo_;
  
  public static final int TYPE_FIELD_NUMBER = 11;
  
  private int type_;
  
  public static final int DIAMONDCOUNT_FIELD_NUMBER = 12;
  
  private int diamondCount_;
  
  public static final int ISDISPLAYEDONPANEL_FIELD_NUMBER = 13;
  
  private boolean isDisplayedOnPanel_;
  
  public static final int NAME_FIELD_NUMBER = 16;
  
  private volatile Object name_;
  
  public static final int ICON_FIELD_NUMBER = 21;
  
  private Image icon_;
  
  private byte memoizedIsInitialized;
  
  private GiftStruct(GeneratedMessageV3.Builder<?> builder) {
    super(builder);
    this.describe_ = "";
    this.duration_ = 0L;
    this.id_ = 0L;
    this.forLinkmic_ = false;
    this.combo_ = false;
    this.type_ = 0;
    this.diamondCount_ = 0;
    this.isDisplayedOnPanel_ = false;
    this.name_ = "";
    this.memoizedIsInitialized = -1;
  }
  
  private GiftStruct() {
    this.describe_ = "";
    this.duration_ = 0L;
    this.id_ = 0L;
    this.forLinkmic_ = false;
    this.combo_ = false;
    this.type_ = 0;
    this.diamondCount_ = 0;
    this.isDisplayedOnPanel_ = false;
    this.name_ = "";
    this.memoizedIsInitialized = -1;
    this.describe_ = "";
    this.name_ = "";
  }
  
  protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
    return new GiftStruct();
  }
  
  public static final Descriptors.Descriptor getDescriptor() {
    return Tiktok.internal_static_GiftStruct_descriptor;
  }
  
  protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
    return Tiktok.internal_static_GiftStruct_fieldAccessorTable.ensureFieldAccessorsInitialized(GiftStruct.class, Builder.class);
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
  
  public long getDuration() {
    return this.duration_;
  }
  
  public long getId() {
    return this.id_;
  }
  
  public boolean getForLinkmic() {
    return this.forLinkmic_;
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
  
  public boolean hasIcon() {
    return ((this.bitField0_ & 0x2) != 0);
  }
  
  public Image getIcon() {
    return (this.icon_ == null) ? Image.getDefaultInstance() : this.icon_;
  }
  
  public ImageOrBuilder getIconOrBuilder() {
    return (this.icon_ == null) ? Image.getDefaultInstance() : this.icon_;
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
    if (this.duration_ != 0L)
      output.writeUInt64(4, this.duration_); 
    if (this.id_ != 0L)
      output.writeUInt64(5, this.id_); 
    if (this.forLinkmic_)
      output.writeBool(7, this.forLinkmic_); 
    if (this.combo_)
      output.writeBool(10, this.combo_); 
    if (this.type_ != 0)
      output.writeUInt32(11, this.type_); 
    if (this.diamondCount_ != 0)
      output.writeUInt32(12, this.diamondCount_); 
    if (this.isDisplayedOnPanel_)
      output.writeBool(13, this.isDisplayedOnPanel_); 
    if (!GeneratedMessageV3.isStringEmpty(this.name_))
      GeneratedMessageV3.writeString(output, 16, this.name_); 
    if ((this.bitField0_ & 0x2) != 0)
      output.writeMessage(21, (MessageLite)getIcon()); 
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
    if (this.duration_ != 0L)
      size += 
        CodedOutputStream.computeUInt64Size(4, this.duration_); 
    if (this.id_ != 0L)
      size += 
        CodedOutputStream.computeUInt64Size(5, this.id_); 
    if (this.forLinkmic_)
      size += 
        CodedOutputStream.computeBoolSize(7, this.forLinkmic_); 
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
    if (!GeneratedMessageV3.isStringEmpty(this.name_))
      size += GeneratedMessageV3.computeStringSize(16, this.name_); 
    if ((this.bitField0_ & 0x2) != 0)
      size += 
        CodedOutputStream.computeMessageSize(21, (MessageLite)getIcon()); 
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
    if (getDuration() != other
      .getDuration())
      return false; 
    if (getId() != other
      .getId())
      return false; 
    if (getForLinkmic() != other
      .getForLinkmic())
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
    if (!getName().equals(other.getName()))
      return false; 
    if (hasIcon() != other.hasIcon())
      return false; 
    if (hasIcon() && 
      
      !getIcon().equals(other.getIcon()))
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
    hash = 37 * hash + 4;
    hash = 53 * hash + Internal.hashLong(
        getDuration());
    hash = 37 * hash + 5;
    hash = 53 * hash + Internal.hashLong(
        getId());
    hash = 37 * hash + 7;
    hash = 53 * hash + Internal.hashBoolean(
        getForLinkmic());
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
    hash = 37 * hash + 16;
    hash = 53 * hash + getName().hashCode();
    if (hasIcon()) {
      hash = 37 * hash + 21;
      hash = 53 * hash + getIcon().hashCode();
    } 
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
    
    private long duration_;
    
    private long id_;
    
    private boolean forLinkmic_;
    
    private boolean combo_;
    
    private int type_;
    
    private int diamondCount_;
    
    private boolean isDisplayedOnPanel_;
    
    private Object name_;
    
    private Image icon_;
    
    private SingleFieldBuilderV3<Image, Image.Builder, ImageOrBuilder> iconBuilder_;
    
    public static final Descriptors.Descriptor getDescriptor() {
      return Tiktok.internal_static_GiftStruct_descriptor;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return Tiktok.internal_static_GiftStruct_fieldAccessorTable
        .ensureFieldAccessorsInitialized(GiftStruct.class, Builder.class);
    }
    
    private Builder() {
      this.describe_ = "";
      this.name_ = "";
      maybeForceBuilderInitialization();
    }
    
    private Builder(GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      this.describe_ = "";
      this.name_ = "";
      maybeForceBuilderInitialization();
    }
    
    private void maybeForceBuilderInitialization() {
      if (GiftStruct.alwaysUseFieldBuilders) {
        getImageFieldBuilder();
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
      this.duration_ = 0L;
      this.id_ = 0L;
      this.forLinkmic_ = false;
      this.combo_ = false;
      this.type_ = 0;
      this.diamondCount_ = 0;
      this.isDisplayedOnPanel_ = false;
      this.name_ = "";
      this.icon_ = null;
      if (this.iconBuilder_ != null) {
        this.iconBuilder_.dispose();
        this.iconBuilder_ = null;
      } 
      return this;
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
      return Tiktok.internal_static_GiftStruct_descriptor;
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
        result.duration_ = this.duration_; 
      if ((from_bitField0_ & 0x8) != 0)
        result.id_ = this.id_; 
      if ((from_bitField0_ & 0x10) != 0)
        result.forLinkmic_ = this.forLinkmic_; 
      if ((from_bitField0_ & 0x20) != 0)
        result.combo_ = this.combo_; 
      if ((from_bitField0_ & 0x40) != 0)
        result.type_ = this.type_; 
      if ((from_bitField0_ & 0x80) != 0)
        result.diamondCount_ = this.diamondCount_; 
      if ((from_bitField0_ & 0x100) != 0)
        result.isDisplayedOnPanel_ = this.isDisplayedOnPanel_; 
      if ((from_bitField0_ & 0x200) != 0)
        result.name_ = this.name_; 
      if ((from_bitField0_ & 0x400) != 0) {
        result.icon_ = (this.iconBuilder_ == null) ? this.icon_ : (Image)this.iconBuilder_.build();
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
      if (other.getDuration() != 0L)
        setDuration(other.getDuration()); 
      if (other.getId() != 0L)
        setId(other.getId()); 
      if (other.getForLinkmic())
        setForLinkmic(other.getForLinkmic()); 
      if (other.getCombo())
        setCombo(other.getCombo()); 
      if (other.getType() != 0)
        setType(other.getType()); 
      if (other.getDiamondCount() != 0)
        setDiamondCount(other.getDiamondCount()); 
      if (other.getIsDisplayedOnPanel())
        setIsDisplayedOnPanel(other.getIsDisplayedOnPanel()); 
      if (!other.getName().isEmpty()) {
        this.name_ = other.name_;
        this.bitField0_ |= 0x200;
        onChanged();
      } 
      if (other.hasIcon())
        mergeIcon(other.getIcon()); 
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
            case 32:
              this.duration_ = input.readUInt64();
              this.bitField0_ |= 0x4;
              continue;
            case 40:
              this.id_ = input.readUInt64();
              this.bitField0_ |= 0x8;
              continue;
            case 56:
              this.forLinkmic_ = input.readBool();
              this.bitField0_ |= 0x10;
              continue;
            case 80:
              this.combo_ = input.readBool();
              this.bitField0_ |= 0x20;
              continue;
            case 88:
              this.type_ = input.readUInt32();
              this.bitField0_ |= 0x40;
              continue;
            case 96:
              this.diamondCount_ = input.readUInt32();
              this.bitField0_ |= 0x80;
              continue;
            case 104:
              this.isDisplayedOnPanel_ = input.readBool();
              this.bitField0_ |= 0x100;
              continue;
            case 130:
              this.name_ = input.readStringRequireUtf8();
              this.bitField0_ |= 0x200;
              continue;
            case 170:
              input.readMessage((MessageLite.Builder)getIconFieldBuilder().getBuilder(), extensionRegistry);
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
    
    public long getDuration() {
      return this.duration_;
    }
    
    public Builder setDuration(long value) {
      this.duration_ = value;
      this.bitField0_ |= 0x4;
      onChanged();
      return this;
    }
    
    public Builder clearDuration() {
      this.bitField0_ &= 0xFFFFFFFB;
      this.duration_ = 0L;
      onChanged();
      return this;
    }
    
    public long getId() {
      return this.id_;
    }
    
    public Builder setId(long value) {
      this.id_ = value;
      this.bitField0_ |= 0x8;
      onChanged();
      return this;
    }
    
    public Builder clearId() {
      this.bitField0_ &= 0xFFFFFFF7;
      this.id_ = 0L;
      onChanged();
      return this;
    }
    
    public boolean getForLinkmic() {
      return this.forLinkmic_;
    }
    
    public Builder setForLinkmic(boolean value) {
      this.forLinkmic_ = value;
      this.bitField0_ |= 0x10;
      onChanged();
      return this;
    }
    
    public Builder clearForLinkmic() {
      this.bitField0_ &= 0xFFFFFFEF;
      this.forLinkmic_ = false;
      onChanged();
      return this;
    }
    
    public boolean getCombo() {
      return this.combo_;
    }
    
    public Builder setCombo(boolean value) {
      this.combo_ = value;
      this.bitField0_ |= 0x20;
      onChanged();
      return this;
    }
    
    public Builder clearCombo() {
      this.bitField0_ &= 0xFFFFFFDF;
      this.combo_ = false;
      onChanged();
      return this;
    }
    
    public int getType() {
      return this.type_;
    }
    
    public Builder setType(int value) {
      this.type_ = value;
      this.bitField0_ |= 0x40;
      onChanged();
      return this;
    }
    
    public Builder clearType() {
      this.bitField0_ &= 0xFFFFFFBF;
      this.type_ = 0;
      onChanged();
      return this;
    }
    
    public int getDiamondCount() {
      return this.diamondCount_;
    }
    
    public Builder setDiamondCount(int value) {
      this.diamondCount_ = value;
      this.bitField0_ |= 0x80;
      onChanged();
      return this;
    }
    
    public Builder clearDiamondCount() {
      this.bitField0_ &= 0xFFFFFF7F;
      this.diamondCount_ = 0;
      onChanged();
      return this;
    }
    
    public boolean getIsDisplayedOnPanel() {
      return this.isDisplayedOnPanel_;
    }
    
    public Builder setIsDisplayedOnPanel(boolean value) {
      this.isDisplayedOnPanel_ = value;
      this.bitField0_ |= 0x100;
      onChanged();
      return this;
    }
    
    public Builder clearIsDisplayedOnPanel() {
      this.bitField0_ &= 0xFFFFFEFF;
      this.isDisplayedOnPanel_ = false;
      onChanged();
      return this;
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
      this.bitField0_ |= 0x200;
      onChanged();
      return this;
    }
    
    public Builder clearName() {
      this.name_ = GiftStruct.getDefaultInstance().getName();
      this.bitField0_ &= 0xFFFFFDFF;
      onChanged();
      return this;
    }
    
    public Builder setNameBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      GiftStruct.checkByteStringIsUtf8(value);
      this.name_ = value;
      this.bitField0_ |= 0x200;
      onChanged();
      return this;
    }
    
    public boolean hasIcon() {
      return ((this.bitField0_ & 0x400) != 0);
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
      this.bitField0_ |= 0x400;
      onChanged();
      return this;
    }
    
    public Builder setIcon(Image.Builder builderForValue) {
      if (this.iconBuilder_ == null) {
        this.icon_ = builderForValue.build();
      } else {
        this.iconBuilder_.setMessage((AbstractMessage)builderForValue.build());
      } 
      this.bitField0_ |= 0x400;
      onChanged();
      return this;
    }
    
    public Builder mergeIcon(Image value) {
      if (this.iconBuilder_ == null) {
        if ((this.bitField0_ & 0x400) != 0 && this.icon_ != null && this.icon_ != 
          
          Image.getDefaultInstance()) {
          getIconBuilder().mergeFrom(value);
        } else {
          this.icon_ = value;
        } 
      } else {
        this.iconBuilder_.mergeFrom((AbstractMessage)value);
      } 
      if (this.icon_ != null) {
        this.bitField0_ |= 0x400;
        onChanged();
      } 
      return this;
    }
    
    public Builder clearIcon() {
      this.bitField0_ &= 0xFFFFFBFF;
      this.icon_ = null;
      if (this.iconBuilder_ != null) {
        this.iconBuilder_.dispose();
        this.iconBuilder_ = null;
      } 
      onChanged();
      return this;
    }
    
    public Image.Builder getIconBuilder() {
      this.bitField0_ |= 0x400;
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
