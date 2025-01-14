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
import com.google.protobuf.MapEntry;
import com.google.protobuf.MapField;
import com.google.protobuf.MapFieldReflectionAccessor;
import com.google.protobuf.Message;
import com.google.protobuf.MessageLite;
import com.google.protobuf.Parser;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import com.google.protobuf.WireFormat;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public final class EffectConfig extends GeneratedMessageV3 implements EffectConfigOrBuilder {
  private static final long serialVersionUID = 0L;
  
  private int bitField0_;
  
  public static final int TYPE_FIELD_NUMBER = 1;
  
  private long type_;
  
  public static final int ICON_FIELD_NUMBER = 2;
  
  private Image icon_;
  
  public static final int AVATARPOS_FIELD_NUMBER = 3;
  
  private long avatarPos_;
  
  public static final int TEXT_FIELD_NUMBER = 4;
  
  private Text text_;
  
  public static final int TEXTICON_FIELD_NUMBER = 5;
  
  private Image textIcon_;
  
  public static final int STAYTIME_FIELD_NUMBER = 6;
  
  private int stayTime_;
  
  public static final int ANIMASSETID_FIELD_NUMBER = 7;
  
  private long animAssetId_;
  
  public static final int BADGE_FIELD_NUMBER = 8;
  
  private Image badge_;
  
  public static final int FLEXSETTINGARRAYLIST_FIELD_NUMBER = 9;
  
  private Internal.LongList flexSettingArrayList_;
  
  private int flexSettingArrayListMemoizedSerializedSize;
  
  public static final int TEXTICONOVERLAY_FIELD_NUMBER = 10;
  
  private Image textIconOverlay_;
  
  public static final int ANIMATEDBADGE_FIELD_NUMBER = 11;
  
  private Image animatedBadge_;
  
  public static final int HASSWEEPLIGHT_FIELD_NUMBER = 12;
  
  private boolean hasSweepLight_;
  
  public static final int TEXTFLEXSETTINGARRAYLIST_FIELD_NUMBER = 13;
  
  private Internal.LongList textFlexSettingArrayList_;
  
  private int textFlexSettingArrayListMemoizedSerializedSize;
  
  public static final int CENTERANIMASSETID_FIELD_NUMBER = 14;
  
  private long centerAnimAssetId_;
  
  public static final int DYNAMICIMAGE_FIELD_NUMBER = 15;
  
  private Image dynamicImage_;
  
  public static final int EXTRAMAP_FIELD_NUMBER = 16;
  
  private MapField<String, String> extraMap_;
  
  public static final int MP4ANIMASSETID_FIELD_NUMBER = 17;
  
  private long mp4AnimAssetId_;
  
  public static final int PRIORITY_FIELD_NUMBER = 18;
  
  private long priority_;
  
  public static final int MAXWAITTIME_FIELD_NUMBER = 19;
  
  private long maxWaitTime_;
  
  public static final int DRESSID_FIELD_NUMBER = 20;
  
  private volatile Object dressId_;
  
  public static final int ALIGNMENT_FIELD_NUMBER = 21;
  
  private long alignment_;
  
  public static final int ALIGNMENTOFFSET_FIELD_NUMBER = 22;
  
  private long alignmentOffset_;
  
  private byte memoizedIsInitialized;
  
  private EffectConfig(GeneratedMessageV3.Builder<?> builder) {
    super(builder);
    this.type_ = 0L;
    this.avatarPos_ = 0L;
    this.stayTime_ = 0;
    this.animAssetId_ = 0L;
    this
      
      .flexSettingArrayList_ = emptyLongList();
    this.flexSettingArrayListMemoizedSerializedSize = -1;
    this.hasSweepLight_ = false;
    this
      
      .textFlexSettingArrayList_ = emptyLongList();
    this.textFlexSettingArrayListMemoizedSerializedSize = -1;
    this.centerAnimAssetId_ = 0L;
    this.mp4AnimAssetId_ = 0L;
    this.priority_ = 0L;
    this.maxWaitTime_ = 0L;
    this.dressId_ = "";
    this.alignment_ = 0L;
    this.alignmentOffset_ = 0L;
    this.memoizedIsInitialized = -1;
  }
  
  private EffectConfig() {
    this.type_ = 0L;
    this.avatarPos_ = 0L;
    this.stayTime_ = 0;
    this.animAssetId_ = 0L;
    this.flexSettingArrayList_ = emptyLongList();
    this.flexSettingArrayListMemoizedSerializedSize = -1;
    this.hasSweepLight_ = false;
    this.textFlexSettingArrayList_ = emptyLongList();
    this.textFlexSettingArrayListMemoizedSerializedSize = -1;
    this.centerAnimAssetId_ = 0L;
    this.mp4AnimAssetId_ = 0L;
    this.priority_ = 0L;
    this.maxWaitTime_ = 0L;
    this.dressId_ = "";
    this.alignment_ = 0L;
    this.alignmentOffset_ = 0L;
    this.memoizedIsInitialized = -1;
    this.flexSettingArrayList_ = emptyLongList();
    this.textFlexSettingArrayList_ = emptyLongList();
    this.dressId_ = "";
  }
  
  protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
    return new EffectConfig();
  }
  
  public static final Descriptors.Descriptor getDescriptor() {
    return Douyin.internal_static_EffectConfig_descriptor;
  }
  
  protected MapFieldReflectionAccessor internalGetMapFieldReflection(int number) {
    switch (number) {
      case 16:
        return (MapFieldReflectionAccessor)internalGetExtraMap();
    } 
    throw new RuntimeException("Invalid map field number: " + number);
  }
  
  protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
    return Douyin.internal_static_EffectConfig_fieldAccessorTable.ensureFieldAccessorsInitialized(EffectConfig.class, Builder.class);
  }
  
  public long getType() {
    return this.type_;
  }
  
  public boolean hasIcon() {
    return ((this.bitField0_ & 0x1) != 0);
  }
  
  public Image getIcon() {
    return (this.icon_ == null) ? Image.getDefaultInstance() : this.icon_;
  }
  
  public ImageOrBuilder getIconOrBuilder() {
    return (this.icon_ == null) ? Image.getDefaultInstance() : this.icon_;
  }
  
  public long getAvatarPos() {
    return this.avatarPos_;
  }
  
  public boolean hasText() {
    return ((this.bitField0_ & 0x2) != 0);
  }
  
  public Text getText() {
    return (this.text_ == null) ? Text.getDefaultInstance() : this.text_;
  }
  
  public TextOrBuilder getTextOrBuilder() {
    return (this.text_ == null) ? Text.getDefaultInstance() : this.text_;
  }
  
  public boolean hasTextIcon() {
    return ((this.bitField0_ & 0x4) != 0);
  }
  
  public Image getTextIcon() {
    return (this.textIcon_ == null) ? Image.getDefaultInstance() : this.textIcon_;
  }
  
  public ImageOrBuilder getTextIconOrBuilder() {
    return (this.textIcon_ == null) ? Image.getDefaultInstance() : this.textIcon_;
  }
  
  public int getStayTime() {
    return this.stayTime_;
  }
  
  public long getAnimAssetId() {
    return this.animAssetId_;
  }
  
  public boolean hasBadge() {
    return ((this.bitField0_ & 0x8) != 0);
  }
  
  public Image getBadge() {
    return (this.badge_ == null) ? Image.getDefaultInstance() : this.badge_;
  }
  
  public ImageOrBuilder getBadgeOrBuilder() {
    return (this.badge_ == null) ? Image.getDefaultInstance() : this.badge_;
  }
  
  public List<Long> getFlexSettingArrayListList() {
    return (List<Long>)this.flexSettingArrayList_;
  }
  
  public int getFlexSettingArrayListCount() {
    return this.flexSettingArrayList_.size();
  }
  
  public long getFlexSettingArrayList(int index) {
    return this.flexSettingArrayList_.getLong(index);
  }
  
  public boolean hasTextIconOverlay() {
    return ((this.bitField0_ & 0x10) != 0);
  }
  
  public Image getTextIconOverlay() {
    return (this.textIconOverlay_ == null) ? Image.getDefaultInstance() : this.textIconOverlay_;
  }
  
  public ImageOrBuilder getTextIconOverlayOrBuilder() {
    return (this.textIconOverlay_ == null) ? Image.getDefaultInstance() : this.textIconOverlay_;
  }
  
  public boolean hasAnimatedBadge() {
    return ((this.bitField0_ & 0x20) != 0);
  }
  
  public Image getAnimatedBadge() {
    return (this.animatedBadge_ == null) ? Image.getDefaultInstance() : this.animatedBadge_;
  }
  
  public ImageOrBuilder getAnimatedBadgeOrBuilder() {
    return (this.animatedBadge_ == null) ? Image.getDefaultInstance() : this.animatedBadge_;
  }
  
  public boolean getHasSweepLight() {
    return this.hasSweepLight_;
  }
  
  public List<Long> getTextFlexSettingArrayListList() {
    return (List<Long>)this.textFlexSettingArrayList_;
  }
  
  public int getTextFlexSettingArrayListCount() {
    return this.textFlexSettingArrayList_.size();
  }
  
  public long getTextFlexSettingArrayList(int index) {
    return this.textFlexSettingArrayList_.getLong(index);
  }
  
  public long getCenterAnimAssetId() {
    return this.centerAnimAssetId_;
  }
  
  public boolean hasDynamicImage() {
    return ((this.bitField0_ & 0x40) != 0);
  }
  
  public Image getDynamicImage() {
    return (this.dynamicImage_ == null) ? Image.getDefaultInstance() : this.dynamicImage_;
  }
  
  public ImageOrBuilder getDynamicImageOrBuilder() {
    return (this.dynamicImage_ == null) ? Image.getDefaultInstance() : this.dynamicImage_;
  }
  
  private static final class ExtraMapDefaultEntryHolder {
    static final MapEntry<String, String> defaultEntry = MapEntry.newDefaultInstance(Douyin.internal_static_EffectConfig_ExtraMapEntry_descriptor, WireFormat.FieldType.STRING, "", WireFormat.FieldType.STRING, "");
  }
  
  private MapField<String, String> internalGetExtraMap() {
    if (this.extraMap_ == null)
      return MapField.emptyMapField(ExtraMapDefaultEntryHolder.defaultEntry); 
    return this.extraMap_;
  }
  
  public int getExtraMapCount() {
    return internalGetExtraMap().getMap().size();
  }
  
  public boolean containsExtraMap(String key) {
    if (key == null)
      throw new NullPointerException("map key"); 
    return internalGetExtraMap().getMap().containsKey(key);
  }
  
  @Deprecated
  public Map<String, String> getExtraMap() {
    return getExtraMapMap();
  }
  
  public Map<String, String> getExtraMapMap() {
    return internalGetExtraMap().getMap();
  }
  
  public String getExtraMapOrDefault(String key, String defaultValue) {
    if (key == null)
      throw new NullPointerException("map key"); 
    Map<String, String> map = internalGetExtraMap().getMap();
    return map.containsKey(key) ? map.get(key) : defaultValue;
  }
  
  public String getExtraMapOrThrow(String key) {
    if (key == null)
      throw new NullPointerException("map key"); 
    Map<String, String> map = internalGetExtraMap().getMap();
    if (!map.containsKey(key))
      throw new IllegalArgumentException(); 
    return map.get(key);
  }
  
  public long getMp4AnimAssetId() {
    return this.mp4AnimAssetId_;
  }
  
  public long getPriority() {
    return this.priority_;
  }
  
  public long getMaxWaitTime() {
    return this.maxWaitTime_;
  }
  
  public String getDressId() {
    Object ref = this.dressId_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.dressId_ = s;
    return s;
  }
  
  public ByteString getDressIdBytes() {
    Object ref = this.dressId_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.dressId_ = b;
      return b;
    } 
    return (ByteString)ref;
  }
  
  public long getAlignment() {
    return this.alignment_;
  }
  
  public long getAlignmentOffset() {
    return this.alignmentOffset_;
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
    getSerializedSize();
    if (this.type_ != 0L)
      output.writeUInt64(1, this.type_); 
    if ((this.bitField0_ & 0x1) != 0)
      output.writeMessage(2, (MessageLite)getIcon()); 
    if (this.avatarPos_ != 0L)
      output.writeUInt64(3, this.avatarPos_); 
    if ((this.bitField0_ & 0x2) != 0)
      output.writeMessage(4, (MessageLite)getText()); 
    if ((this.bitField0_ & 0x4) != 0)
      output.writeMessage(5, (MessageLite)getTextIcon()); 
    if (this.stayTime_ != 0)
      output.writeUInt32(6, this.stayTime_); 
    if (this.animAssetId_ != 0L)
      output.writeUInt64(7, this.animAssetId_); 
    if ((this.bitField0_ & 0x8) != 0)
      output.writeMessage(8, (MessageLite)getBadge()); 
    if (getFlexSettingArrayListList().size() > 0) {
      output.writeUInt32NoTag(74);
      output.writeUInt32NoTag(this.flexSettingArrayListMemoizedSerializedSize);
    } 
    int i;
    for (i = 0; i < this.flexSettingArrayList_.size(); i++)
      output.writeUInt64NoTag(this.flexSettingArrayList_.getLong(i)); 
    if ((this.bitField0_ & 0x10) != 0)
      output.writeMessage(10, (MessageLite)getTextIconOverlay()); 
    if ((this.bitField0_ & 0x20) != 0)
      output.writeMessage(11, (MessageLite)getAnimatedBadge()); 
    if (this.hasSweepLight_)
      output.writeBool(12, this.hasSweepLight_); 
    if (getTextFlexSettingArrayListList().size() > 0) {
      output.writeUInt32NoTag(106);
      output.writeUInt32NoTag(this.textFlexSettingArrayListMemoizedSerializedSize);
    } 
    for (i = 0; i < this.textFlexSettingArrayList_.size(); i++)
      output.writeUInt64NoTag(this.textFlexSettingArrayList_.getLong(i)); 
    if (this.centerAnimAssetId_ != 0L)
      output.writeUInt64(14, this.centerAnimAssetId_); 
    if ((this.bitField0_ & 0x40) != 0)
      output.writeMessage(15, (MessageLite)getDynamicImage()); 
    GeneratedMessageV3.serializeStringMapTo(output, 
        
        internalGetExtraMap(), ExtraMapDefaultEntryHolder.defaultEntry, 16);
    if (this.mp4AnimAssetId_ != 0L)
      output.writeUInt64(17, this.mp4AnimAssetId_); 
    if (this.priority_ != 0L)
      output.writeUInt64(18, this.priority_); 
    if (this.maxWaitTime_ != 0L)
      output.writeUInt64(19, this.maxWaitTime_); 
    if (!GeneratedMessageV3.isStringEmpty(this.dressId_))
      GeneratedMessageV3.writeString(output, 20, this.dressId_); 
    if (this.alignment_ != 0L)
      output.writeUInt64(21, this.alignment_); 
    if (this.alignmentOffset_ != 0L)
      output.writeUInt64(22, this.alignmentOffset_); 
    getUnknownFields().writeTo(output);
  }
  
  public int getSerializedSize() {
    int size = this.memoizedSize;
    if (size != -1)
      return size; 
    size = 0;
    if (this.type_ != 0L)
      size += 
        CodedOutputStream.computeUInt64Size(1, this.type_); 
    if ((this.bitField0_ & 0x1) != 0)
      size += 
        CodedOutputStream.computeMessageSize(2, (MessageLite)getIcon()); 
    if (this.avatarPos_ != 0L)
      size += 
        CodedOutputStream.computeUInt64Size(3, this.avatarPos_); 
    if ((this.bitField0_ & 0x2) != 0)
      size += 
        CodedOutputStream.computeMessageSize(4, (MessageLite)getText()); 
    if ((this.bitField0_ & 0x4) != 0)
      size += 
        CodedOutputStream.computeMessageSize(5, (MessageLite)getTextIcon()); 
    if (this.stayTime_ != 0)
      size += 
        CodedOutputStream.computeUInt32Size(6, this.stayTime_); 
    if (this.animAssetId_ != 0L)
      size += 
        CodedOutputStream.computeUInt64Size(7, this.animAssetId_); 
    if ((this.bitField0_ & 0x8) != 0)
      size += 
        CodedOutputStream.computeMessageSize(8, (MessageLite)getBadge()); 
    int dataSize = 0;
    int i;
    for (i = 0; i < this.flexSettingArrayList_.size(); i++)
      dataSize += 
        CodedOutputStream.computeUInt64SizeNoTag(this.flexSettingArrayList_.getLong(i)); 
    size += dataSize;
    if (!getFlexSettingArrayListList().isEmpty()) {
      size++;
      size += 
        CodedOutputStream.computeInt32SizeNoTag(dataSize);
    } 
    this.flexSettingArrayListMemoizedSerializedSize = dataSize;
    if ((this.bitField0_ & 0x10) != 0)
      size += 
        CodedOutputStream.computeMessageSize(10, (MessageLite)getTextIconOverlay()); 
    if ((this.bitField0_ & 0x20) != 0)
      size += 
        CodedOutputStream.computeMessageSize(11, (MessageLite)getAnimatedBadge()); 
    if (this.hasSweepLight_)
      size += 
        CodedOutputStream.computeBoolSize(12, this.hasSweepLight_); 
    dataSize = 0;
    for (i = 0; i < this.textFlexSettingArrayList_.size(); i++)
      dataSize += 
        CodedOutputStream.computeUInt64SizeNoTag(this.textFlexSettingArrayList_.getLong(i)); 
    size += dataSize;
    if (!getTextFlexSettingArrayListList().isEmpty()) {
      size++;
      size += 
        CodedOutputStream.computeInt32SizeNoTag(dataSize);
    } 
    this.textFlexSettingArrayListMemoizedSerializedSize = dataSize;
    if (this.centerAnimAssetId_ != 0L)
      size += 
        CodedOutputStream.computeUInt64Size(14, this.centerAnimAssetId_); 
    if ((this.bitField0_ & 0x40) != 0)
      size += 
        CodedOutputStream.computeMessageSize(15, (MessageLite)getDynamicImage()); 
    for (Map.Entry<String, String> entry : (Iterable<Map.Entry<String, String>>)internalGetExtraMap().getMap().entrySet()) {
      MapEntry<String, String> extraMap__ = ExtraMapDefaultEntryHolder.defaultEntry.newBuilderForType().setKey(entry.getKey()).setValue(entry.getValue()).build();
      size += 
        CodedOutputStream.computeMessageSize(16, (MessageLite)extraMap__);
    } 
    if (this.mp4AnimAssetId_ != 0L)
      size += 
        CodedOutputStream.computeUInt64Size(17, this.mp4AnimAssetId_); 
    if (this.priority_ != 0L)
      size += 
        CodedOutputStream.computeUInt64Size(18, this.priority_); 
    if (this.maxWaitTime_ != 0L)
      size += 
        CodedOutputStream.computeUInt64Size(19, this.maxWaitTime_); 
    if (!GeneratedMessageV3.isStringEmpty(this.dressId_))
      size += GeneratedMessageV3.computeStringSize(20, this.dressId_); 
    if (this.alignment_ != 0L)
      size += 
        CodedOutputStream.computeUInt64Size(21, this.alignment_); 
    if (this.alignmentOffset_ != 0L)
      size += 
        CodedOutputStream.computeUInt64Size(22, this.alignmentOffset_); 
    size += getUnknownFields().getSerializedSize();
    this.memoizedSize = size;
    return size;
  }
  
  public boolean equals(Object obj) {
    if (obj == this)
      return true; 
    if (!(obj instanceof EffectConfig))
      return super.equals(obj); 
    EffectConfig other = (EffectConfig)obj;
    if (getType() != other
      .getType())
      return false; 
    if (hasIcon() != other.hasIcon())
      return false; 
    if (hasIcon() && 
      
      !getIcon().equals(other.getIcon()))
      return false; 
    if (getAvatarPos() != other
      .getAvatarPos())
      return false; 
    if (hasText() != other.hasText())
      return false; 
    if (hasText() && 
      
      !getText().equals(other.getText()))
      return false; 
    if (hasTextIcon() != other.hasTextIcon())
      return false; 
    if (hasTextIcon() && 
      
      !getTextIcon().equals(other.getTextIcon()))
      return false; 
    if (getStayTime() != other
      .getStayTime())
      return false; 
    if (getAnimAssetId() != other
      .getAnimAssetId())
      return false; 
    if (hasBadge() != other.hasBadge())
      return false; 
    if (hasBadge() && 
      
      !getBadge().equals(other.getBadge()))
      return false; 
    if (!getFlexSettingArrayListList().equals(other.getFlexSettingArrayListList()))
      return false; 
    if (hasTextIconOverlay() != other.hasTextIconOverlay())
      return false; 
    if (hasTextIconOverlay() && 
      
      !getTextIconOverlay().equals(other.getTextIconOverlay()))
      return false; 
    if (hasAnimatedBadge() != other.hasAnimatedBadge())
      return false; 
    if (hasAnimatedBadge() && 
      
      !getAnimatedBadge().equals(other.getAnimatedBadge()))
      return false; 
    if (getHasSweepLight() != other
      .getHasSweepLight())
      return false; 
    if (!getTextFlexSettingArrayListList().equals(other.getTextFlexSettingArrayListList()))
      return false; 
    if (getCenterAnimAssetId() != other
      .getCenterAnimAssetId())
      return false; 
    if (hasDynamicImage() != other.hasDynamicImage())
      return false; 
    if (hasDynamicImage() && 
      
      !getDynamicImage().equals(other.getDynamicImage()))
      return false; 
    if (!internalGetExtraMap().equals(other
        .internalGetExtraMap()))
      return false; 
    if (getMp4AnimAssetId() != other
      .getMp4AnimAssetId())
      return false; 
    if (getPriority() != other
      .getPriority())
      return false; 
    if (getMaxWaitTime() != other
      .getMaxWaitTime())
      return false; 
    if (!getDressId().equals(other.getDressId()))
      return false; 
    if (getAlignment() != other
      .getAlignment())
      return false; 
    if (getAlignmentOffset() != other
      .getAlignmentOffset())
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
    hash = 53 * hash + Internal.hashLong(
        getType());
    if (hasIcon()) {
      hash = 37 * hash + 2;
      hash = 53 * hash + getIcon().hashCode();
    } 
    hash = 37 * hash + 3;
    hash = 53 * hash + Internal.hashLong(
        getAvatarPos());
    if (hasText()) {
      hash = 37 * hash + 4;
      hash = 53 * hash + getText().hashCode();
    } 
    if (hasTextIcon()) {
      hash = 37 * hash + 5;
      hash = 53 * hash + getTextIcon().hashCode();
    } 
    hash = 37 * hash + 6;
    hash = 53 * hash + getStayTime();
    hash = 37 * hash + 7;
    hash = 53 * hash + Internal.hashLong(
        getAnimAssetId());
    if (hasBadge()) {
      hash = 37 * hash + 8;
      hash = 53 * hash + getBadge().hashCode();
    } 
    if (getFlexSettingArrayListCount() > 0) {
      hash = 37 * hash + 9;
      hash = 53 * hash + getFlexSettingArrayListList().hashCode();
    } 
    if (hasTextIconOverlay()) {
      hash = 37 * hash + 10;
      hash = 53 * hash + getTextIconOverlay().hashCode();
    } 
    if (hasAnimatedBadge()) {
      hash = 37 * hash + 11;
      hash = 53 * hash + getAnimatedBadge().hashCode();
    } 
    hash = 37 * hash + 12;
    hash = 53 * hash + Internal.hashBoolean(
        getHasSweepLight());
    if (getTextFlexSettingArrayListCount() > 0) {
      hash = 37 * hash + 13;
      hash = 53 * hash + getTextFlexSettingArrayListList().hashCode();
    } 
    hash = 37 * hash + 14;
    hash = 53 * hash + Internal.hashLong(
        getCenterAnimAssetId());
    if (hasDynamicImage()) {
      hash = 37 * hash + 15;
      hash = 53 * hash + getDynamicImage().hashCode();
    } 
    if (!internalGetExtraMap().getMap().isEmpty()) {
      hash = 37 * hash + 16;
      hash = 53 * hash + internalGetExtraMap().hashCode();
    } 
    hash = 37 * hash + 17;
    hash = 53 * hash + Internal.hashLong(
        getMp4AnimAssetId());
    hash = 37 * hash + 18;
    hash = 53 * hash + Internal.hashLong(
        getPriority());
    hash = 37 * hash + 19;
    hash = 53 * hash + Internal.hashLong(
        getMaxWaitTime());
    hash = 37 * hash + 20;
    hash = 53 * hash + getDressId().hashCode();
    hash = 37 * hash + 21;
    hash = 53 * hash + Internal.hashLong(
        getAlignment());
    hash = 37 * hash + 22;
    hash = 53 * hash + Internal.hashLong(
        getAlignmentOffset());
    hash = 29 * hash + getUnknownFields().hashCode();
    this.memoizedHashCode = hash;
    return hash;
  }
  
  public static EffectConfig parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
    return (EffectConfig)PARSER.parseFrom(data);
  }
  
  public static EffectConfig parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (EffectConfig)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static EffectConfig parseFrom(ByteString data) throws InvalidProtocolBufferException {
    return (EffectConfig)PARSER.parseFrom(data);
  }
  
  public static EffectConfig parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (EffectConfig)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static EffectConfig parseFrom(byte[] data) throws InvalidProtocolBufferException {
    return (EffectConfig)PARSER.parseFrom(data);
  }
  
  public static EffectConfig parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (EffectConfig)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static EffectConfig parseFrom(InputStream input) throws IOException {
    return 
      (EffectConfig)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static EffectConfig parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (EffectConfig)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static EffectConfig parseDelimitedFrom(InputStream input) throws IOException {
    return 
      (EffectConfig)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
  }
  
  public static EffectConfig parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (EffectConfig)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static EffectConfig parseFrom(CodedInputStream input) throws IOException {
    return 
      (EffectConfig)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static EffectConfig parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (EffectConfig)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public Builder newBuilderForType() {
    return newBuilder();
  }
  
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  
  public static Builder newBuilder(EffectConfig prototype) {
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
  
  public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements EffectConfigOrBuilder {
    private int bitField0_;
    
    private long type_;
    
    private Image icon_;
    
    private SingleFieldBuilderV3<Image, Image.Builder, ImageOrBuilder> iconBuilder_;
    
    private long avatarPos_;
    
    private Text text_;
    
    private SingleFieldBuilderV3<Text, Text.Builder, TextOrBuilder> textBuilder_;
    
    private Image textIcon_;
    
    private SingleFieldBuilderV3<Image, Image.Builder, ImageOrBuilder> textIconBuilder_;
    
    private int stayTime_;
    
    private long animAssetId_;
    
    private Image badge_;
    
    private SingleFieldBuilderV3<Image, Image.Builder, ImageOrBuilder> badgeBuilder_;
    
    private Internal.LongList flexSettingArrayList_;
    
    private Image textIconOverlay_;
    
    private SingleFieldBuilderV3<Image, Image.Builder, ImageOrBuilder> textIconOverlayBuilder_;
    
    private Image animatedBadge_;
    
    private SingleFieldBuilderV3<Image, Image.Builder, ImageOrBuilder> animatedBadgeBuilder_;
    
    private boolean hasSweepLight_;
    
    private Internal.LongList textFlexSettingArrayList_;
    
    private long centerAnimAssetId_;
    
    private Image dynamicImage_;
    
    private SingleFieldBuilderV3<Image, Image.Builder, ImageOrBuilder> dynamicImageBuilder_;
    
    private MapField<String, String> extraMap_;
    
    private long mp4AnimAssetId_;
    
    private long priority_;
    
    private long maxWaitTime_;
    
    private Object dressId_;
    
    private long alignment_;
    
    private long alignmentOffset_;
    
    public static final Descriptors.Descriptor getDescriptor() {
      return Douyin.internal_static_EffectConfig_descriptor;
    }
    
    protected MapFieldReflectionAccessor internalGetMapFieldReflection(int number) {
      switch (number) {
        case 16:
          return (MapFieldReflectionAccessor)internalGetExtraMap();
      } 
      throw new RuntimeException("Invalid map field number: " + number);
    }
    
    protected MapFieldReflectionAccessor internalGetMutableMapFieldReflection(int number) {
      switch (number) {
        case 16:
          return (MapFieldReflectionAccessor)internalGetMutableExtraMap();
      } 
      throw new RuntimeException("Invalid map field number: " + number);
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return Douyin.internal_static_EffectConfig_fieldAccessorTable
        .ensureFieldAccessorsInitialized(EffectConfig.class, Builder.class);
    }
    
    private Builder() {
      this.flexSettingArrayList_ = EffectConfig.emptyLongList();
      this.textFlexSettingArrayList_ = EffectConfig.emptyLongList();
      this.dressId_ = "";
      maybeForceBuilderInitialization();
    }
    
    private Builder(GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      this.flexSettingArrayList_ = EffectConfig.emptyLongList();
      this.textFlexSettingArrayList_ = EffectConfig.emptyLongList();
      this.dressId_ = "";
      maybeForceBuilderInitialization();
    }
    
    private void maybeForceBuilderInitialization() {
      if (EffectConfig.alwaysUseFieldBuilders) {
        getIconFieldBuilder();
        getTextFieldBuilder();
        getTextIconFieldBuilder();
        getBadgeFieldBuilder();
        getTextIconOverlayFieldBuilder();
        getAnimatedBadgeFieldBuilder();
        getDynamicImageFieldBuilder();
      } 
    }
    
    public Builder clear() {
      super.clear();
      this.bitField0_ = 0;
      this.type_ = 0L;
      this.icon_ = null;
      if (this.iconBuilder_ != null) {
        this.iconBuilder_.dispose();
        this.iconBuilder_ = null;
      } 
      this.avatarPos_ = 0L;
      this.text_ = null;
      if (this.textBuilder_ != null) {
        this.textBuilder_.dispose();
        this.textBuilder_ = null;
      } 
      this.textIcon_ = null;
      if (this.textIconBuilder_ != null) {
        this.textIconBuilder_.dispose();
        this.textIconBuilder_ = null;
      } 
      this.stayTime_ = 0;
      this.animAssetId_ = 0L;
      this.badge_ = null;
      if (this.badgeBuilder_ != null) {
        this.badgeBuilder_.dispose();
        this.badgeBuilder_ = null;
      } 
      this.flexSettingArrayList_ = EffectConfig.emptyLongList();
      this.textIconOverlay_ = null;
      if (this.textIconOverlayBuilder_ != null) {
        this.textIconOverlayBuilder_.dispose();
        this.textIconOverlayBuilder_ = null;
      } 
      this.animatedBadge_ = null;
      if (this.animatedBadgeBuilder_ != null) {
        this.animatedBadgeBuilder_.dispose();
        this.animatedBadgeBuilder_ = null;
      } 
      this.hasSweepLight_ = false;
      this.textFlexSettingArrayList_ = EffectConfig.emptyLongList();
      this.centerAnimAssetId_ = 0L;
      this.dynamicImage_ = null;
      if (this.dynamicImageBuilder_ != null) {
        this.dynamicImageBuilder_.dispose();
        this.dynamicImageBuilder_ = null;
      } 
      internalGetMutableExtraMap().clear();
      this.mp4AnimAssetId_ = 0L;
      this.priority_ = 0L;
      this.maxWaitTime_ = 0L;
      this.dressId_ = "";
      this.alignment_ = 0L;
      this.alignmentOffset_ = 0L;
      return this;
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
      return Douyin.internal_static_EffectConfig_descriptor;
    }
    
    public EffectConfig getDefaultInstanceForType() {
      return EffectConfig.getDefaultInstance();
    }
    
    public EffectConfig build() {
      EffectConfig result = buildPartial();
      if (!result.isInitialized())
        throw newUninitializedMessageException(result); 
      return result;
    }
    
    public EffectConfig buildPartial() {
      EffectConfig result = new EffectConfig(this);
      if (this.bitField0_ != 0)
        buildPartial0(result); 
      onBuilt();
      return result;
    }
    
    private void buildPartial0(EffectConfig result) {
      int from_bitField0_ = this.bitField0_;
      if ((from_bitField0_ & 0x1) != 0)
        result.type_ = this.type_; 
      int to_bitField0_ = 0;
      if ((from_bitField0_ & 0x2) != 0) {
        result.icon_ = (this.iconBuilder_ == null) ? this.icon_ : (Image)this.iconBuilder_.build();
        to_bitField0_ |= 0x1;
      } 
      if ((from_bitField0_ & 0x4) != 0)
        result.avatarPos_ = this.avatarPos_; 
      if ((from_bitField0_ & 0x8) != 0) {
        result.text_ = (this.textBuilder_ == null) ? this.text_ : (Text)this.textBuilder_.build();
        to_bitField0_ |= 0x2;
      } 
      if ((from_bitField0_ & 0x10) != 0) {
        result.textIcon_ = (this.textIconBuilder_ == null) ? this.textIcon_ : (Image)this.textIconBuilder_.build();
        to_bitField0_ |= 0x4;
      } 
      if ((from_bitField0_ & 0x20) != 0)
        result.stayTime_ = this.stayTime_; 
      if ((from_bitField0_ & 0x40) != 0)
        result.animAssetId_ = this.animAssetId_; 
      if ((from_bitField0_ & 0x80) != 0) {
        result.badge_ = (this.badgeBuilder_ == null) ? this.badge_ : (Image)this.badgeBuilder_.build();
        to_bitField0_ |= 0x8;
      } 
      if ((from_bitField0_ & 0x100) != 0) {
        this.flexSettingArrayList_.makeImmutable();
        result.flexSettingArrayList_ = this.flexSettingArrayList_;
      } 
      if ((from_bitField0_ & 0x200) != 0) {
        result.textIconOverlay_ = (this.textIconOverlayBuilder_ == null) ? this.textIconOverlay_ : (Image)this.textIconOverlayBuilder_.build();
        to_bitField0_ |= 0x10;
      } 
      if ((from_bitField0_ & 0x400) != 0) {
        result.animatedBadge_ = (this.animatedBadgeBuilder_ == null) ? this.animatedBadge_ : (Image)this.animatedBadgeBuilder_.build();
        to_bitField0_ |= 0x20;
      } 
      if ((from_bitField0_ & 0x800) != 0)
        result.hasSweepLight_ = this.hasSweepLight_; 
      if ((from_bitField0_ & 0x1000) != 0) {
        this.textFlexSettingArrayList_.makeImmutable();
        result.textFlexSettingArrayList_ = this.textFlexSettingArrayList_;
      } 
      if ((from_bitField0_ & 0x2000) != 0)
        result.centerAnimAssetId_ = this.centerAnimAssetId_; 
      if ((from_bitField0_ & 0x4000) != 0) {
        result.dynamicImage_ = (this.dynamicImageBuilder_ == null) ? this.dynamicImage_ : (Image)this.dynamicImageBuilder_.build();
        to_bitField0_ |= 0x40;
      } 
      if ((from_bitField0_ & 0x8000) != 0) {
        result.extraMap_ = internalGetExtraMap();
        result.extraMap_.makeImmutable();
      } 
      if ((from_bitField0_ & 0x10000) != 0)
        result.mp4AnimAssetId_ = this.mp4AnimAssetId_; 
      if ((from_bitField0_ & 0x20000) != 0)
        result.priority_ = this.priority_; 
      if ((from_bitField0_ & 0x40000) != 0)
        result.maxWaitTime_ = this.maxWaitTime_; 
      if ((from_bitField0_ & 0x80000) != 0)
        result.dressId_ = this.dressId_; 
      if ((from_bitField0_ & 0x100000) != 0)
        result.alignment_ = this.alignment_; 
      if ((from_bitField0_ & 0x200000) != 0)
        result.alignmentOffset_ = this.alignmentOffset_; 
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
      if (other instanceof EffectConfig)
        return mergeFrom((EffectConfig)other); 
      super.mergeFrom(other);
      return this;
    }
    
    public Builder mergeFrom(EffectConfig other) {
      if (other == EffectConfig.getDefaultInstance())
        return this; 
      if (other.getType() != 0L)
        setType(other.getType()); 
      if (other.hasIcon())
        mergeIcon(other.getIcon()); 
      if (other.getAvatarPos() != 0L)
        setAvatarPos(other.getAvatarPos()); 
      if (other.hasText())
        mergeText(other.getText()); 
      if (other.hasTextIcon())
        mergeTextIcon(other.getTextIcon()); 
      if (other.getStayTime() != 0)
        setStayTime(other.getStayTime()); 
      if (other.getAnimAssetId() != 0L)
        setAnimAssetId(other.getAnimAssetId()); 
      if (other.hasBadge())
        mergeBadge(other.getBadge()); 
      if (!other.flexSettingArrayList_.isEmpty()) {
        if (this.flexSettingArrayList_.isEmpty()) {
          this.flexSettingArrayList_ = other.flexSettingArrayList_;
          this.flexSettingArrayList_.makeImmutable();
          this.bitField0_ |= 0x100;
        } else {
          ensureFlexSettingArrayListIsMutable();
          this.flexSettingArrayList_.addAll((Collection)other.flexSettingArrayList_);
        } 
        onChanged();
      } 
      if (other.hasTextIconOverlay())
        mergeTextIconOverlay(other.getTextIconOverlay()); 
      if (other.hasAnimatedBadge())
        mergeAnimatedBadge(other.getAnimatedBadge()); 
      if (other.getHasSweepLight())
        setHasSweepLight(other.getHasSweepLight()); 
      if (!other.textFlexSettingArrayList_.isEmpty()) {
        if (this.textFlexSettingArrayList_.isEmpty()) {
          this.textFlexSettingArrayList_ = other.textFlexSettingArrayList_;
          this.textFlexSettingArrayList_.makeImmutable();
          this.bitField0_ |= 0x1000;
        } else {
          ensureTextFlexSettingArrayListIsMutable();
          this.textFlexSettingArrayList_.addAll((Collection)other.textFlexSettingArrayList_);
        } 
        onChanged();
      } 
      if (other.getCenterAnimAssetId() != 0L)
        setCenterAnimAssetId(other.getCenterAnimAssetId()); 
      if (other.hasDynamicImage())
        mergeDynamicImage(other.getDynamicImage()); 
      internalGetMutableExtraMap().mergeFrom(other.internalGetExtraMap());
      this.bitField0_ |= 0x8000;
      if (other.getMp4AnimAssetId() != 0L)
        setMp4AnimAssetId(other.getMp4AnimAssetId()); 
      if (other.getPriority() != 0L)
        setPriority(other.getPriority()); 
      if (other.getMaxWaitTime() != 0L)
        setMaxWaitTime(other.getMaxWaitTime()); 
      if (!other.getDressId().isEmpty()) {
        this.dressId_ = other.dressId_;
        this.bitField0_ |= 0x80000;
        onChanged();
      } 
      if (other.getAlignment() != 0L)
        setAlignment(other.getAlignment()); 
      if (other.getAlignmentOffset() != 0L)
        setAlignmentOffset(other.getAlignmentOffset()); 
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
          long l1;
          int i;
          long v;
          int length;
          MapEntry<String, String> extraMap__;
          int limit, tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              continue;
            case 8:
              this.type_ = input.readUInt64();
              this.bitField0_ |= 0x1;
              continue;
            case 18:
              input.readMessage((MessageLite.Builder)getIconFieldBuilder().getBuilder(), extensionRegistry);
              this.bitField0_ |= 0x2;
              continue;
            case 24:
              this.avatarPos_ = input.readUInt64();
              this.bitField0_ |= 0x4;
              continue;
            case 34:
              input.readMessage((MessageLite.Builder)getTextFieldBuilder().getBuilder(), extensionRegistry);
              this.bitField0_ |= 0x8;
              continue;
            case 42:
              input.readMessage((MessageLite.Builder)getTextIconFieldBuilder().getBuilder(), extensionRegistry);
              this.bitField0_ |= 0x10;
              continue;
            case 48:
              this.stayTime_ = input.readUInt32();
              this.bitField0_ |= 0x20;
              continue;
            case 56:
              this.animAssetId_ = input.readUInt64();
              this.bitField0_ |= 0x40;
              continue;
            case 66:
              input.readMessage((MessageLite.Builder)getBadgeFieldBuilder().getBuilder(), extensionRegistry);
              this.bitField0_ |= 0x80;
              continue;
            case 72:
              l1 = input.readUInt64();
              ensureFlexSettingArrayListIsMutable();
              this.flexSettingArrayList_.addLong(l1);
              continue;
            case 74:
              i = input.readRawVarint32();
              limit = input.pushLimit(i);
              ensureFlexSettingArrayListIsMutable();
              while (input.getBytesUntilLimit() > 0)
                this.flexSettingArrayList_.addLong(input.readUInt64()); 
              input.popLimit(limit);
              continue;
            case 82:
              input.readMessage((MessageLite.Builder)getTextIconOverlayFieldBuilder().getBuilder(), extensionRegistry);
              this.bitField0_ |= 0x200;
              continue;
            case 90:
              input.readMessage((MessageLite.Builder)getAnimatedBadgeFieldBuilder().getBuilder(), extensionRegistry);
              this.bitField0_ |= 0x400;
              continue;
            case 96:
              this.hasSweepLight_ = input.readBool();
              this.bitField0_ |= 0x800;
              continue;
            case 104:
              v = input.readUInt64();
              ensureTextFlexSettingArrayListIsMutable();
              this.textFlexSettingArrayList_.addLong(v);
              continue;
            case 106:
              length = input.readRawVarint32();
              limit = input.pushLimit(length);
              ensureTextFlexSettingArrayListIsMutable();
              while (input.getBytesUntilLimit() > 0)
                this.textFlexSettingArrayList_.addLong(input.readUInt64()); 
              input.popLimit(limit);
              continue;
            case 112:
              this.centerAnimAssetId_ = input.readUInt64();
              this.bitField0_ |= 0x2000;
              continue;
            case 122:
              input.readMessage((MessageLite.Builder)getDynamicImageFieldBuilder().getBuilder(), extensionRegistry);
              this.bitField0_ |= 0x4000;
              continue;
            case 130:
              extraMap__ = (MapEntry<String, String>)input.readMessage(EffectConfig.ExtraMapDefaultEntryHolder.defaultEntry.getParserForType(), extensionRegistry);
              internalGetMutableExtraMap().getMutableMap().put((String)extraMap__.getKey(), (String)extraMap__.getValue());
              this.bitField0_ |= 0x8000;
              continue;
            case 136:
              this.mp4AnimAssetId_ = input.readUInt64();
              this.bitField0_ |= 0x10000;
              continue;
            case 144:
              this.priority_ = input.readUInt64();
              this.bitField0_ |= 0x20000;
              continue;
            case 152:
              this.maxWaitTime_ = input.readUInt64();
              this.bitField0_ |= 0x40000;
              continue;
            case 162:
              this.dressId_ = input.readStringRequireUtf8();
              this.bitField0_ |= 0x80000;
              continue;
            case 168:
              this.alignment_ = input.readUInt64();
              this.bitField0_ |= 0x100000;
              continue;
            case 176:
              this.alignmentOffset_ = input.readUInt64();
              this.bitField0_ |= 0x200000;
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
    
    public long getType() {
      return this.type_;
    }
    
    public Builder setType(long value) {
      this.type_ = value;
      this.bitField0_ |= 0x1;
      onChanged();
      return this;
    }
    
    public Builder clearType() {
      this.bitField0_ &= 0xFFFFFFFE;
      this.type_ = 0L;
      onChanged();
      return this;
    }
    
    public boolean hasIcon() {
      return ((this.bitField0_ & 0x2) != 0);
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
      this.bitField0_ |= 0x2;
      onChanged();
      return this;
    }
    
    public Builder setIcon(Image.Builder builderForValue) {
      if (this.iconBuilder_ == null) {
        this.icon_ = builderForValue.build();
      } else {
        this.iconBuilder_.setMessage((AbstractMessage)builderForValue.build());
      } 
      this.bitField0_ |= 0x2;
      onChanged();
      return this;
    }
    
    public Builder mergeIcon(Image value) {
      if (this.iconBuilder_ == null) {
        if ((this.bitField0_ & 0x2) != 0 && this.icon_ != null && this.icon_ != Image.getDefaultInstance()) {
          getIconBuilder().mergeFrom(value);
        } else {
          this.icon_ = value;
        } 
      } else {
        this.iconBuilder_.mergeFrom((AbstractMessage)value);
      } 
      if (this.icon_ != null) {
        this.bitField0_ |= 0x2;
        onChanged();
      } 
      return this;
    }
    
    public Builder clearIcon() {
      this.bitField0_ &= 0xFFFFFFFD;
      this.icon_ = null;
      if (this.iconBuilder_ != null) {
        this.iconBuilder_.dispose();
        this.iconBuilder_ = null;
      } 
      onChanged();
      return this;
    }
    
    public Image.Builder getIconBuilder() {
      this.bitField0_ |= 0x2;
      onChanged();
      return (Image.Builder)getIconFieldBuilder().getBuilder();
    }
    
    public ImageOrBuilder getIconOrBuilder() {
      if (this.iconBuilder_ != null)
        return (ImageOrBuilder)this.iconBuilder_.getMessageOrBuilder(); 
      return (this.icon_ == null) ? Image.getDefaultInstance() : this.icon_;
    }
    
    private SingleFieldBuilderV3<Image, Image.Builder, ImageOrBuilder> getIconFieldBuilder() {
      if (this.iconBuilder_ == null) {
        this.iconBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getIcon(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.icon_ = null;
      } 
      return this.iconBuilder_;
    }
    
    public long getAvatarPos() {
      return this.avatarPos_;
    }
    
    public Builder setAvatarPos(long value) {
      this.avatarPos_ = value;
      this.bitField0_ |= 0x4;
      onChanged();
      return this;
    }
    
    public Builder clearAvatarPos() {
      this.bitField0_ &= 0xFFFFFFFB;
      this.avatarPos_ = 0L;
      onChanged();
      return this;
    }
    
    public boolean hasText() {
      return ((this.bitField0_ & 0x8) != 0);
    }
    
    public Text getText() {
      if (this.textBuilder_ == null)
        return (this.text_ == null) ? Text.getDefaultInstance() : this.text_; 
      return (Text)this.textBuilder_.getMessage();
    }
    
    public Builder setText(Text value) {
      if (this.textBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        this.text_ = value;
      } else {
        this.textBuilder_.setMessage((AbstractMessage)value);
      } 
      this.bitField0_ |= 0x8;
      onChanged();
      return this;
    }
    
    public Builder setText(Text.Builder builderForValue) {
      if (this.textBuilder_ == null) {
        this.text_ = builderForValue.build();
      } else {
        this.textBuilder_.setMessage((AbstractMessage)builderForValue.build());
      } 
      this.bitField0_ |= 0x8;
      onChanged();
      return this;
    }
    
    public Builder mergeText(Text value) {
      if (this.textBuilder_ == null) {
        if ((this.bitField0_ & 0x8) != 0 && this.text_ != null && this.text_ != Text.getDefaultInstance()) {
          getTextBuilder().mergeFrom(value);
        } else {
          this.text_ = value;
        } 
      } else {
        this.textBuilder_.mergeFrom((AbstractMessage)value);
      } 
      if (this.text_ != null) {
        this.bitField0_ |= 0x8;
        onChanged();
      } 
      return this;
    }
    
    public Builder clearText() {
      this.bitField0_ &= 0xFFFFFFF7;
      this.text_ = null;
      if (this.textBuilder_ != null) {
        this.textBuilder_.dispose();
        this.textBuilder_ = null;
      } 
      onChanged();
      return this;
    }
    
    public Text.Builder getTextBuilder() {
      this.bitField0_ |= 0x8;
      onChanged();
      return (Text.Builder)getTextFieldBuilder().getBuilder();
    }
    
    public TextOrBuilder getTextOrBuilder() {
      if (this.textBuilder_ != null)
        return (TextOrBuilder)this.textBuilder_.getMessageOrBuilder(); 
      return (this.text_ == null) ? Text.getDefaultInstance() : this.text_;
    }
    
    private SingleFieldBuilderV3<Text, Text.Builder, TextOrBuilder> getTextFieldBuilder() {
      if (this.textBuilder_ == null) {
        this.textBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getText(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.text_ = null;
      } 
      return this.textBuilder_;
    }
    
    public boolean hasTextIcon() {
      return ((this.bitField0_ & 0x10) != 0);
    }
    
    public Image getTextIcon() {
      if (this.textIconBuilder_ == null)
        return (this.textIcon_ == null) ? Image.getDefaultInstance() : this.textIcon_; 
      return (Image)this.textIconBuilder_.getMessage();
    }
    
    public Builder setTextIcon(Image value) {
      if (this.textIconBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        this.textIcon_ = value;
      } else {
        this.textIconBuilder_.setMessage((AbstractMessage)value);
      } 
      this.bitField0_ |= 0x10;
      onChanged();
      return this;
    }
    
    public Builder setTextIcon(Image.Builder builderForValue) {
      if (this.textIconBuilder_ == null) {
        this.textIcon_ = builderForValue.build();
      } else {
        this.textIconBuilder_.setMessage((AbstractMessage)builderForValue.build());
      } 
      this.bitField0_ |= 0x10;
      onChanged();
      return this;
    }
    
    public Builder mergeTextIcon(Image value) {
      if (this.textIconBuilder_ == null) {
        if ((this.bitField0_ & 0x10) != 0 && this.textIcon_ != null && this.textIcon_ != Image.getDefaultInstance()) {
          getTextIconBuilder().mergeFrom(value);
        } else {
          this.textIcon_ = value;
        } 
      } else {
        this.textIconBuilder_.mergeFrom((AbstractMessage)value);
      } 
      if (this.textIcon_ != null) {
        this.bitField0_ |= 0x10;
        onChanged();
      } 
      return this;
    }
    
    public Builder clearTextIcon() {
      this.bitField0_ &= 0xFFFFFFEF;
      this.textIcon_ = null;
      if (this.textIconBuilder_ != null) {
        this.textIconBuilder_.dispose();
        this.textIconBuilder_ = null;
      } 
      onChanged();
      return this;
    }
    
    public Image.Builder getTextIconBuilder() {
      this.bitField0_ |= 0x10;
      onChanged();
      return (Image.Builder)getTextIconFieldBuilder().getBuilder();
    }
    
    public ImageOrBuilder getTextIconOrBuilder() {
      if (this.textIconBuilder_ != null)
        return (ImageOrBuilder)this.textIconBuilder_.getMessageOrBuilder(); 
      return (this.textIcon_ == null) ? Image.getDefaultInstance() : this.textIcon_;
    }
    
    private SingleFieldBuilderV3<Image, Image.Builder, ImageOrBuilder> getTextIconFieldBuilder() {
      if (this.textIconBuilder_ == null) {
        this.textIconBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getTextIcon(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.textIcon_ = null;
      } 
      return this.textIconBuilder_;
    }
    
    public int getStayTime() {
      return this.stayTime_;
    }
    
    public Builder setStayTime(int value) {
      this.stayTime_ = value;
      this.bitField0_ |= 0x20;
      onChanged();
      return this;
    }
    
    public Builder clearStayTime() {
      this.bitField0_ &= 0xFFFFFFDF;
      this.stayTime_ = 0;
      onChanged();
      return this;
    }
    
    public long getAnimAssetId() {
      return this.animAssetId_;
    }
    
    public Builder setAnimAssetId(long value) {
      this.animAssetId_ = value;
      this.bitField0_ |= 0x40;
      onChanged();
      return this;
    }
    
    public Builder clearAnimAssetId() {
      this.bitField0_ &= 0xFFFFFFBF;
      this.animAssetId_ = 0L;
      onChanged();
      return this;
    }
    
    public boolean hasBadge() {
      return ((this.bitField0_ & 0x80) != 0);
    }
    
    public Image getBadge() {
      if (this.badgeBuilder_ == null)
        return (this.badge_ == null) ? Image.getDefaultInstance() : this.badge_; 
      return (Image)this.badgeBuilder_.getMessage();
    }
    
    public Builder setBadge(Image value) {
      if (this.badgeBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        this.badge_ = value;
      } else {
        this.badgeBuilder_.setMessage((AbstractMessage)value);
      } 
      this.bitField0_ |= 0x80;
      onChanged();
      return this;
    }
    
    public Builder setBadge(Image.Builder builderForValue) {
      if (this.badgeBuilder_ == null) {
        this.badge_ = builderForValue.build();
      } else {
        this.badgeBuilder_.setMessage((AbstractMessage)builderForValue.build());
      } 
      this.bitField0_ |= 0x80;
      onChanged();
      return this;
    }
    
    public Builder mergeBadge(Image value) {
      if (this.badgeBuilder_ == null) {
        if ((this.bitField0_ & 0x80) != 0 && this.badge_ != null && this.badge_ != Image.getDefaultInstance()) {
          getBadgeBuilder().mergeFrom(value);
        } else {
          this.badge_ = value;
        } 
      } else {
        this.badgeBuilder_.mergeFrom((AbstractMessage)value);
      } 
      if (this.badge_ != null) {
        this.bitField0_ |= 0x80;
        onChanged();
      } 
      return this;
    }
    
    public Builder clearBadge() {
      this.bitField0_ &= 0xFFFFFF7F;
      this.badge_ = null;
      if (this.badgeBuilder_ != null) {
        this.badgeBuilder_.dispose();
        this.badgeBuilder_ = null;
      } 
      onChanged();
      return this;
    }
    
    public Image.Builder getBadgeBuilder() {
      this.bitField0_ |= 0x80;
      onChanged();
      return (Image.Builder)getBadgeFieldBuilder().getBuilder();
    }
    
    public ImageOrBuilder getBadgeOrBuilder() {
      if (this.badgeBuilder_ != null)
        return (ImageOrBuilder)this.badgeBuilder_.getMessageOrBuilder(); 
      return (this.badge_ == null) ? Image.getDefaultInstance() : this.badge_;
    }
    
    private SingleFieldBuilderV3<Image, Image.Builder, ImageOrBuilder> getBadgeFieldBuilder() {
      if (this.badgeBuilder_ == null) {
        this.badgeBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getBadge(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.badge_ = null;
      } 
      return this.badgeBuilder_;
    }
    
    private void ensureFlexSettingArrayListIsMutable() {
      if (!this.flexSettingArrayList_.isModifiable())
        this.flexSettingArrayList_ = (Internal.LongList)EffectConfig.makeMutableCopy((Internal.ProtobufList)this.flexSettingArrayList_); 
      this.bitField0_ |= 0x100;
    }
    
    public List<Long> getFlexSettingArrayListList() {
      this.flexSettingArrayList_.makeImmutable();
      return (List<Long>)this.flexSettingArrayList_;
    }
    
    public int getFlexSettingArrayListCount() {
      return this.flexSettingArrayList_.size();
    }
    
    public long getFlexSettingArrayList(int index) {
      return this.flexSettingArrayList_.getLong(index);
    }
    
    public Builder setFlexSettingArrayList(int index, long value) {
      ensureFlexSettingArrayListIsMutable();
      this.flexSettingArrayList_.setLong(index, value);
      this.bitField0_ |= 0x100;
      onChanged();
      return this;
    }
    
    public Builder addFlexSettingArrayList(long value) {
      ensureFlexSettingArrayListIsMutable();
      this.flexSettingArrayList_.addLong(value);
      this.bitField0_ |= 0x100;
      onChanged();
      return this;
    }
    
    public Builder addAllFlexSettingArrayList(Iterable<? extends Long> values) {
      ensureFlexSettingArrayListIsMutable();
      AbstractMessageLite.Builder.addAll(values, (List)this.flexSettingArrayList_);
      this.bitField0_ |= 0x100;
      onChanged();
      return this;
    }
    
    public Builder clearFlexSettingArrayList() {
      this.flexSettingArrayList_ = EffectConfig.emptyLongList();
      this.bitField0_ &= 0xFFFFFEFF;
      onChanged();
      return this;
    }
    
    public boolean hasTextIconOverlay() {
      return ((this.bitField0_ & 0x200) != 0);
    }
    
    public Image getTextIconOverlay() {
      if (this.textIconOverlayBuilder_ == null)
        return (this.textIconOverlay_ == null) ? Image.getDefaultInstance() : this.textIconOverlay_; 
      return (Image)this.textIconOverlayBuilder_.getMessage();
    }
    
    public Builder setTextIconOverlay(Image value) {
      if (this.textIconOverlayBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        this.textIconOverlay_ = value;
      } else {
        this.textIconOverlayBuilder_.setMessage((AbstractMessage)value);
      } 
      this.bitField0_ |= 0x200;
      onChanged();
      return this;
    }
    
    public Builder setTextIconOverlay(Image.Builder builderForValue) {
      if (this.textIconOverlayBuilder_ == null) {
        this.textIconOverlay_ = builderForValue.build();
      } else {
        this.textIconOverlayBuilder_.setMessage((AbstractMessage)builderForValue.build());
      } 
      this.bitField0_ |= 0x200;
      onChanged();
      return this;
    }
    
    public Builder mergeTextIconOverlay(Image value) {
      if (this.textIconOverlayBuilder_ == null) {
        if ((this.bitField0_ & 0x200) != 0 && this.textIconOverlay_ != null && this.textIconOverlay_ != Image.getDefaultInstance()) {
          getTextIconOverlayBuilder().mergeFrom(value);
        } else {
          this.textIconOverlay_ = value;
        } 
      } else {
        this.textIconOverlayBuilder_.mergeFrom((AbstractMessage)value);
      } 
      if (this.textIconOverlay_ != null) {
        this.bitField0_ |= 0x200;
        onChanged();
      } 
      return this;
    }
    
    public Builder clearTextIconOverlay() {
      this.bitField0_ &= 0xFFFFFDFF;
      this.textIconOverlay_ = null;
      if (this.textIconOverlayBuilder_ != null) {
        this.textIconOverlayBuilder_.dispose();
        this.textIconOverlayBuilder_ = null;
      } 
      onChanged();
      return this;
    }
    
    public Image.Builder getTextIconOverlayBuilder() {
      this.bitField0_ |= 0x200;
      onChanged();
      return (Image.Builder)getTextIconOverlayFieldBuilder().getBuilder();
    }
    
    public ImageOrBuilder getTextIconOverlayOrBuilder() {
      if (this.textIconOverlayBuilder_ != null)
        return (ImageOrBuilder)this.textIconOverlayBuilder_.getMessageOrBuilder(); 
      return (this.textIconOverlay_ == null) ? Image.getDefaultInstance() : this.textIconOverlay_;
    }
    
    private SingleFieldBuilderV3<Image, Image.Builder, ImageOrBuilder> getTextIconOverlayFieldBuilder() {
      if (this.textIconOverlayBuilder_ == null) {
        this.textIconOverlayBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getTextIconOverlay(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.textIconOverlay_ = null;
      } 
      return this.textIconOverlayBuilder_;
    }
    
    public boolean hasAnimatedBadge() {
      return ((this.bitField0_ & 0x400) != 0);
    }
    
    public Image getAnimatedBadge() {
      if (this.animatedBadgeBuilder_ == null)
        return (this.animatedBadge_ == null) ? Image.getDefaultInstance() : this.animatedBadge_; 
      return (Image)this.animatedBadgeBuilder_.getMessage();
    }
    
    public Builder setAnimatedBadge(Image value) {
      if (this.animatedBadgeBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        this.animatedBadge_ = value;
      } else {
        this.animatedBadgeBuilder_.setMessage((AbstractMessage)value);
      } 
      this.bitField0_ |= 0x400;
      onChanged();
      return this;
    }
    
    public Builder setAnimatedBadge(Image.Builder builderForValue) {
      if (this.animatedBadgeBuilder_ == null) {
        this.animatedBadge_ = builderForValue.build();
      } else {
        this.animatedBadgeBuilder_.setMessage((AbstractMessage)builderForValue.build());
      } 
      this.bitField0_ |= 0x400;
      onChanged();
      return this;
    }
    
    public Builder mergeAnimatedBadge(Image value) {
      if (this.animatedBadgeBuilder_ == null) {
        if ((this.bitField0_ & 0x400) != 0 && this.animatedBadge_ != null && this.animatedBadge_ != Image.getDefaultInstance()) {
          getAnimatedBadgeBuilder().mergeFrom(value);
        } else {
          this.animatedBadge_ = value;
        } 
      } else {
        this.animatedBadgeBuilder_.mergeFrom((AbstractMessage)value);
      } 
      if (this.animatedBadge_ != null) {
        this.bitField0_ |= 0x400;
        onChanged();
      } 
      return this;
    }
    
    public Builder clearAnimatedBadge() {
      this.bitField0_ &= 0xFFFFFBFF;
      this.animatedBadge_ = null;
      if (this.animatedBadgeBuilder_ != null) {
        this.animatedBadgeBuilder_.dispose();
        this.animatedBadgeBuilder_ = null;
      } 
      onChanged();
      return this;
    }
    
    public Image.Builder getAnimatedBadgeBuilder() {
      this.bitField0_ |= 0x400;
      onChanged();
      return (Image.Builder)getAnimatedBadgeFieldBuilder().getBuilder();
    }
    
    public ImageOrBuilder getAnimatedBadgeOrBuilder() {
      if (this.animatedBadgeBuilder_ != null)
        return (ImageOrBuilder)this.animatedBadgeBuilder_.getMessageOrBuilder(); 
      return (this.animatedBadge_ == null) ? Image.getDefaultInstance() : this.animatedBadge_;
    }
    
    private SingleFieldBuilderV3<Image, Image.Builder, ImageOrBuilder> getAnimatedBadgeFieldBuilder() {
      if (this.animatedBadgeBuilder_ == null) {
        this.animatedBadgeBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getAnimatedBadge(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.animatedBadge_ = null;
      } 
      return this.animatedBadgeBuilder_;
    }
    
    public boolean getHasSweepLight() {
      return this.hasSweepLight_;
    }
    
    public Builder setHasSweepLight(boolean value) {
      this.hasSweepLight_ = value;
      this.bitField0_ |= 0x800;
      onChanged();
      return this;
    }
    
    public Builder clearHasSweepLight() {
      this.bitField0_ &= 0xFFFFF7FF;
      this.hasSweepLight_ = false;
      onChanged();
      return this;
    }
    
    private void ensureTextFlexSettingArrayListIsMutable() {
      if (!this.textFlexSettingArrayList_.isModifiable())
        this.textFlexSettingArrayList_ = (Internal.LongList)EffectConfig.makeMutableCopy((Internal.ProtobufList)this.textFlexSettingArrayList_); 
      this.bitField0_ |= 0x1000;
    }
    
    public List<Long> getTextFlexSettingArrayListList() {
      this.textFlexSettingArrayList_.makeImmutable();
      return (List<Long>)this.textFlexSettingArrayList_;
    }
    
    public int getTextFlexSettingArrayListCount() {
      return this.textFlexSettingArrayList_.size();
    }
    
    public long getTextFlexSettingArrayList(int index) {
      return this.textFlexSettingArrayList_.getLong(index);
    }
    
    public Builder setTextFlexSettingArrayList(int index, long value) {
      ensureTextFlexSettingArrayListIsMutable();
      this.textFlexSettingArrayList_.setLong(index, value);
      this.bitField0_ |= 0x1000;
      onChanged();
      return this;
    }
    
    public Builder addTextFlexSettingArrayList(long value) {
      ensureTextFlexSettingArrayListIsMutable();
      this.textFlexSettingArrayList_.addLong(value);
      this.bitField0_ |= 0x1000;
      onChanged();
      return this;
    }
    
    public Builder addAllTextFlexSettingArrayList(Iterable<? extends Long> values) {
      ensureTextFlexSettingArrayListIsMutable();
      AbstractMessageLite.Builder.addAll(values, (List)this.textFlexSettingArrayList_);
      this.bitField0_ |= 0x1000;
      onChanged();
      return this;
    }
    
    public Builder clearTextFlexSettingArrayList() {
      this.textFlexSettingArrayList_ = EffectConfig.emptyLongList();
      this.bitField0_ &= 0xFFFFEFFF;
      onChanged();
      return this;
    }
    
    public long getCenterAnimAssetId() {
      return this.centerAnimAssetId_;
    }
    
    public Builder setCenterAnimAssetId(long value) {
      this.centerAnimAssetId_ = value;
      this.bitField0_ |= 0x2000;
      onChanged();
      return this;
    }
    
    public Builder clearCenterAnimAssetId() {
      this.bitField0_ &= 0xFFFFDFFF;
      this.centerAnimAssetId_ = 0L;
      onChanged();
      return this;
    }
    
    public boolean hasDynamicImage() {
      return ((this.bitField0_ & 0x4000) != 0);
    }
    
    public Image getDynamicImage() {
      if (this.dynamicImageBuilder_ == null)
        return (this.dynamicImage_ == null) ? Image.getDefaultInstance() : this.dynamicImage_; 
      return (Image)this.dynamicImageBuilder_.getMessage();
    }
    
    public Builder setDynamicImage(Image value) {
      if (this.dynamicImageBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        this.dynamicImage_ = value;
      } else {
        this.dynamicImageBuilder_.setMessage((AbstractMessage)value);
      } 
      this.bitField0_ |= 0x4000;
      onChanged();
      return this;
    }
    
    public Builder setDynamicImage(Image.Builder builderForValue) {
      if (this.dynamicImageBuilder_ == null) {
        this.dynamicImage_ = builderForValue.build();
      } else {
        this.dynamicImageBuilder_.setMessage((AbstractMessage)builderForValue.build());
      } 
      this.bitField0_ |= 0x4000;
      onChanged();
      return this;
    }
    
    public Builder mergeDynamicImage(Image value) {
      if (this.dynamicImageBuilder_ == null) {
        if ((this.bitField0_ & 0x4000) != 0 && this.dynamicImage_ != null && this.dynamicImage_ != Image.getDefaultInstance()) {
          getDynamicImageBuilder().mergeFrom(value);
        } else {
          this.dynamicImage_ = value;
        } 
      } else {
        this.dynamicImageBuilder_.mergeFrom((AbstractMessage)value);
      } 
      if (this.dynamicImage_ != null) {
        this.bitField0_ |= 0x4000;
        onChanged();
      } 
      return this;
    }
    
    public Builder clearDynamicImage() {
      this.bitField0_ &= 0xFFFFBFFF;
      this.dynamicImage_ = null;
      if (this.dynamicImageBuilder_ != null) {
        this.dynamicImageBuilder_.dispose();
        this.dynamicImageBuilder_ = null;
      } 
      onChanged();
      return this;
    }
    
    public Image.Builder getDynamicImageBuilder() {
      this.bitField0_ |= 0x4000;
      onChanged();
      return (Image.Builder)getDynamicImageFieldBuilder().getBuilder();
    }
    
    public ImageOrBuilder getDynamicImageOrBuilder() {
      if (this.dynamicImageBuilder_ != null)
        return (ImageOrBuilder)this.dynamicImageBuilder_.getMessageOrBuilder(); 
      return (this.dynamicImage_ == null) ? Image.getDefaultInstance() : this.dynamicImage_;
    }
    
    private SingleFieldBuilderV3<Image, Image.Builder, ImageOrBuilder> getDynamicImageFieldBuilder() {
      if (this.dynamicImageBuilder_ == null) {
        this.dynamicImageBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getDynamicImage(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.dynamicImage_ = null;
      } 
      return this.dynamicImageBuilder_;
    }
    
    private MapField<String, String> internalGetExtraMap() {
      if (this.extraMap_ == null)
        return MapField.emptyMapField(EffectConfig.ExtraMapDefaultEntryHolder.defaultEntry); 
      return this.extraMap_;
    }
    
    private MapField<String, String> internalGetMutableExtraMap() {
      if (this.extraMap_ == null)
        this.extraMap_ = MapField.newMapField(EffectConfig.ExtraMapDefaultEntryHolder.defaultEntry); 
      if (!this.extraMap_.isMutable())
        this.extraMap_ = this.extraMap_.copy(); 
      this.bitField0_ |= 0x8000;
      onChanged();
      return this.extraMap_;
    }
    
    public int getExtraMapCount() {
      return internalGetExtraMap().getMap().size();
    }
    
    public boolean containsExtraMap(String key) {
      if (key == null)
        throw new NullPointerException("map key"); 
      return internalGetExtraMap().getMap().containsKey(key);
    }
    
    @Deprecated
    public Map<String, String> getExtraMap() {
      return getExtraMapMap();
    }
    
    public Map<String, String> getExtraMapMap() {
      return internalGetExtraMap().getMap();
    }
    
    public String getExtraMapOrDefault(String key, String defaultValue) {
      if (key == null)
        throw new NullPointerException("map key"); 
      Map<String, String> map = internalGetExtraMap().getMap();
      return map.containsKey(key) ? map.get(key) : defaultValue;
    }
    
    public String getExtraMapOrThrow(String key) {
      if (key == null)
        throw new NullPointerException("map key"); 
      Map<String, String> map = internalGetExtraMap().getMap();
      if (!map.containsKey(key))
        throw new IllegalArgumentException(); 
      return map.get(key);
    }
    
    public Builder clearExtraMap() {
      this.bitField0_ &= 0xFFFF7FFF;
      internalGetMutableExtraMap().getMutableMap().clear();
      return this;
    }
    
    public Builder removeExtraMap(String key) {
      if (key == null)
        throw new NullPointerException("map key"); 
      internalGetMutableExtraMap().getMutableMap().remove(key);
      return this;
    }
    
    @Deprecated
    public Map<String, String> getMutableExtraMap() {
      this.bitField0_ |= 0x8000;
      return internalGetMutableExtraMap().getMutableMap();
    }
    
    public Builder putExtraMap(String key, String value) {
      if (key == null)
        throw new NullPointerException("map key"); 
      if (value == null)
        throw new NullPointerException("map value"); 
      internalGetMutableExtraMap().getMutableMap().put(key, value);
      this.bitField0_ |= 0x8000;
      return this;
    }
    
    public Builder putAllExtraMap(Map<String, String> values) {
      internalGetMutableExtraMap().getMutableMap().putAll(values);
      this.bitField0_ |= 0x8000;
      return this;
    }
    
    public long getMp4AnimAssetId() {
      return this.mp4AnimAssetId_;
    }
    
    public Builder setMp4AnimAssetId(long value) {
      this.mp4AnimAssetId_ = value;
      this.bitField0_ |= 0x10000;
      onChanged();
      return this;
    }
    
    public Builder clearMp4AnimAssetId() {
      this.bitField0_ &= 0xFFFEFFFF;
      this.mp4AnimAssetId_ = 0L;
      onChanged();
      return this;
    }
    
    public long getPriority() {
      return this.priority_;
    }
    
    public Builder setPriority(long value) {
      this.priority_ = value;
      this.bitField0_ |= 0x20000;
      onChanged();
      return this;
    }
    
    public Builder clearPriority() {
      this.bitField0_ &= 0xFFFDFFFF;
      this.priority_ = 0L;
      onChanged();
      return this;
    }
    
    public long getMaxWaitTime() {
      return this.maxWaitTime_;
    }
    
    public Builder setMaxWaitTime(long value) {
      this.maxWaitTime_ = value;
      this.bitField0_ |= 0x40000;
      onChanged();
      return this;
    }
    
    public Builder clearMaxWaitTime() {
      this.bitField0_ &= 0xFFFBFFFF;
      this.maxWaitTime_ = 0L;
      onChanged();
      return this;
    }
    
    public String getDressId() {
      Object ref = this.dressId_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.dressId_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getDressIdBytes() {
      Object ref = this.dressId_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.dressId_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setDressId(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.dressId_ = value;
      this.bitField0_ |= 0x80000;
      onChanged();
      return this;
    }
    
    public Builder clearDressId() {
      this.dressId_ = EffectConfig.getDefaultInstance().getDressId();
      this.bitField0_ &= 0xFFF7FFFF;
      onChanged();
      return this;
    }
    
    public Builder setDressIdBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      EffectConfig.checkByteStringIsUtf8(value);
      this.dressId_ = value;
      this.bitField0_ |= 0x80000;
      onChanged();
      return this;
    }
    
    public long getAlignment() {
      return this.alignment_;
    }
    
    public Builder setAlignment(long value) {
      this.alignment_ = value;
      this.bitField0_ |= 0x100000;
      onChanged();
      return this;
    }
    
    public Builder clearAlignment() {
      this.bitField0_ &= 0xFFEFFFFF;
      this.alignment_ = 0L;
      onChanged();
      return this;
    }
    
    public long getAlignmentOffset() {
      return this.alignmentOffset_;
    }
    
    public Builder setAlignmentOffset(long value) {
      this.alignmentOffset_ = value;
      this.bitField0_ |= 0x200000;
      onChanged();
      return this;
    }
    
    public Builder clearAlignmentOffset() {
      this.bitField0_ &= 0xFFDFFFFF;
      this.alignmentOffset_ = 0L;
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
  
  private static final EffectConfig DEFAULT_INSTANCE = new EffectConfig();
  
  public static EffectConfig getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }
  
  private static final Parser<EffectConfig> PARSER = (Parser<EffectConfig>)new AbstractParser<EffectConfig>() {
      public EffectConfig parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        EffectConfig.Builder builder = EffectConfig.newBuilder();
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
  
  public static Parser<EffectConfig> parser() {
    return PARSER;
  }
  
  public Parser<EffectConfig> getParserForType() {
    return PARSER;
  }
  
  public EffectConfig getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }
}
