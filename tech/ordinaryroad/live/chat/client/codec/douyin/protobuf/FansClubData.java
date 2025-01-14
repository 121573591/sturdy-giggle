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
import java.util.Collection;
import java.util.List;

public final class FansClubData extends GeneratedMessageV3 implements FansClubDataOrBuilder {
  private static final long serialVersionUID = 0L;
  
  private int bitField0_;
  
  public static final int CLUBNAME_FIELD_NUMBER = 1;
  
  private volatile Object clubName_;
  
  public static final int LEVEL_FIELD_NUMBER = 2;
  
  private int level_;
  
  public static final int USERFANSCLUBSTATUS_FIELD_NUMBER = 3;
  
  private int userFansClubStatus_;
  
  public static final int BADGE_FIELD_NUMBER = 4;
  
  private UserBadge badge_;
  
  public static final int AVAILABLEGIFTIDS_FIELD_NUMBER = 5;
  
  private Internal.LongList availableGiftIds_;
  
  private int availableGiftIdsMemoizedSerializedSize;
  
  public static final int ANCHORID_FIELD_NUMBER = 6;
  
  private long anchorId_;
  
  private byte memoizedIsInitialized;
  
  private FansClubData(GeneratedMessageV3.Builder<?> builder) {
    super(builder);
    this.clubName_ = "";
    this.level_ = 0;
    this.userFansClubStatus_ = 0;
    this
      
      .availableGiftIds_ = emptyLongList();
    this.availableGiftIdsMemoizedSerializedSize = -1;
    this.anchorId_ = 0L;
    this.memoizedIsInitialized = -1;
  }
  
  private FansClubData() {
    this.clubName_ = "";
    this.level_ = 0;
    this.userFansClubStatus_ = 0;
    this.availableGiftIds_ = emptyLongList();
    this.availableGiftIdsMemoizedSerializedSize = -1;
    this.anchorId_ = 0L;
    this.memoizedIsInitialized = -1;
    this.clubName_ = "";
    this.availableGiftIds_ = emptyLongList();
  }
  
  protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
    return new FansClubData();
  }
  
  public static final Descriptors.Descriptor getDescriptor() {
    return Douyin.internal_static_FansClubData_descriptor;
  }
  
  protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
    return Douyin.internal_static_FansClubData_fieldAccessorTable.ensureFieldAccessorsInitialized(FansClubData.class, Builder.class);
  }
  
  public String getClubName() {
    Object ref = this.clubName_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.clubName_ = s;
    return s;
  }
  
  public ByteString getClubNameBytes() {
    Object ref = this.clubName_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.clubName_ = b;
      return b;
    } 
    return (ByteString)ref;
  }
  
  public int getLevel() {
    return this.level_;
  }
  
  public int getUserFansClubStatus() {
    return this.userFansClubStatus_;
  }
  
  public boolean hasBadge() {
    return ((this.bitField0_ & 0x1) != 0);
  }
  
  public UserBadge getBadge() {
    return (this.badge_ == null) ? UserBadge.getDefaultInstance() : this.badge_;
  }
  
  public UserBadgeOrBuilder getBadgeOrBuilder() {
    return (this.badge_ == null) ? UserBadge.getDefaultInstance() : this.badge_;
  }
  
  public List<Long> getAvailableGiftIdsList() {
    return (List<Long>)this.availableGiftIds_;
  }
  
  public int getAvailableGiftIdsCount() {
    return this.availableGiftIds_.size();
  }
  
  public long getAvailableGiftIds(int index) {
    return this.availableGiftIds_.getLong(index);
  }
  
  public long getAnchorId() {
    return this.anchorId_;
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
    if (!GeneratedMessageV3.isStringEmpty(this.clubName_))
      GeneratedMessageV3.writeString(output, 1, this.clubName_); 
    if (this.level_ != 0)
      output.writeInt32(2, this.level_); 
    if (this.userFansClubStatus_ != 0)
      output.writeInt32(3, this.userFansClubStatus_); 
    if ((this.bitField0_ & 0x1) != 0)
      output.writeMessage(4, (MessageLite)getBadge()); 
    if (getAvailableGiftIdsList().size() > 0) {
      output.writeUInt32NoTag(42);
      output.writeUInt32NoTag(this.availableGiftIdsMemoizedSerializedSize);
    } 
    for (int i = 0; i < this.availableGiftIds_.size(); i++)
      output.writeInt64NoTag(this.availableGiftIds_.getLong(i)); 
    if (this.anchorId_ != 0L)
      output.writeInt64(6, this.anchorId_); 
    getUnknownFields().writeTo(output);
  }
  
  public int getSerializedSize() {
    int size = this.memoizedSize;
    if (size != -1)
      return size; 
    size = 0;
    if (!GeneratedMessageV3.isStringEmpty(this.clubName_))
      size += GeneratedMessageV3.computeStringSize(1, this.clubName_); 
    if (this.level_ != 0)
      size += 
        CodedOutputStream.computeInt32Size(2, this.level_); 
    if (this.userFansClubStatus_ != 0)
      size += 
        CodedOutputStream.computeInt32Size(3, this.userFansClubStatus_); 
    if ((this.bitField0_ & 0x1) != 0)
      size += 
        CodedOutputStream.computeMessageSize(4, (MessageLite)getBadge()); 
    int dataSize = 0;
    for (int i = 0; i < this.availableGiftIds_.size(); i++)
      dataSize += 
        CodedOutputStream.computeInt64SizeNoTag(this.availableGiftIds_.getLong(i)); 
    size += dataSize;
    if (!getAvailableGiftIdsList().isEmpty()) {
      size++;
      size += 
        CodedOutputStream.computeInt32SizeNoTag(dataSize);
    } 
    this.availableGiftIdsMemoizedSerializedSize = dataSize;
    if (this.anchorId_ != 0L)
      size += 
        CodedOutputStream.computeInt64Size(6, this.anchorId_); 
    size += getUnknownFields().getSerializedSize();
    this.memoizedSize = size;
    return size;
  }
  
  public boolean equals(Object obj) {
    if (obj == this)
      return true; 
    if (!(obj instanceof FansClubData))
      return super.equals(obj); 
    FansClubData other = (FansClubData)obj;
    if (!getClubName().equals(other.getClubName()))
      return false; 
    if (getLevel() != other
      .getLevel())
      return false; 
    if (getUserFansClubStatus() != other
      .getUserFansClubStatus())
      return false; 
    if (hasBadge() != other.hasBadge())
      return false; 
    if (hasBadge() && 
      
      !getBadge().equals(other.getBadge()))
      return false; 
    if (!getAvailableGiftIdsList().equals(other.getAvailableGiftIdsList()))
      return false; 
    if (getAnchorId() != other
      .getAnchorId())
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
    hash = 53 * hash + getClubName().hashCode();
    hash = 37 * hash + 2;
    hash = 53 * hash + getLevel();
    hash = 37 * hash + 3;
    hash = 53 * hash + getUserFansClubStatus();
    if (hasBadge()) {
      hash = 37 * hash + 4;
      hash = 53 * hash + getBadge().hashCode();
    } 
    if (getAvailableGiftIdsCount() > 0) {
      hash = 37 * hash + 5;
      hash = 53 * hash + getAvailableGiftIdsList().hashCode();
    } 
    hash = 37 * hash + 6;
    hash = 53 * hash + Internal.hashLong(
        getAnchorId());
    hash = 29 * hash + getUnknownFields().hashCode();
    this.memoizedHashCode = hash;
    return hash;
  }
  
  public static FansClubData parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
    return (FansClubData)PARSER.parseFrom(data);
  }
  
  public static FansClubData parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (FansClubData)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static FansClubData parseFrom(ByteString data) throws InvalidProtocolBufferException {
    return (FansClubData)PARSER.parseFrom(data);
  }
  
  public static FansClubData parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (FansClubData)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static FansClubData parseFrom(byte[] data) throws InvalidProtocolBufferException {
    return (FansClubData)PARSER.parseFrom(data);
  }
  
  public static FansClubData parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (FansClubData)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static FansClubData parseFrom(InputStream input) throws IOException {
    return 
      (FansClubData)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static FansClubData parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (FansClubData)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static FansClubData parseDelimitedFrom(InputStream input) throws IOException {
    return 
      (FansClubData)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
  }
  
  public static FansClubData parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (FansClubData)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static FansClubData parseFrom(CodedInputStream input) throws IOException {
    return 
      (FansClubData)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static FansClubData parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (FansClubData)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public Builder newBuilderForType() {
    return newBuilder();
  }
  
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  
  public static Builder newBuilder(FansClubData prototype) {
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
  
  public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements FansClubDataOrBuilder {
    private int bitField0_;
    
    private Object clubName_;
    
    private int level_;
    
    private int userFansClubStatus_;
    
    private UserBadge badge_;
    
    private SingleFieldBuilderV3<UserBadge, UserBadge.Builder, UserBadgeOrBuilder> badgeBuilder_;
    
    private Internal.LongList availableGiftIds_;
    
    private long anchorId_;
    
    public static final Descriptors.Descriptor getDescriptor() {
      return Douyin.internal_static_FansClubData_descriptor;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return Douyin.internal_static_FansClubData_fieldAccessorTable
        .ensureFieldAccessorsInitialized(FansClubData.class, Builder.class);
    }
    
    private Builder() {
      this.clubName_ = "";
      this.availableGiftIds_ = FansClubData.emptyLongList();
      maybeForceBuilderInitialization();
    }
    
    private Builder(GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      this.clubName_ = "";
      this.availableGiftIds_ = FansClubData.emptyLongList();
      maybeForceBuilderInitialization();
    }
    
    private void maybeForceBuilderInitialization() {
      if (FansClubData.alwaysUseFieldBuilders)
        getBadgeFieldBuilder(); 
    }
    
    public Builder clear() {
      super.clear();
      this.bitField0_ = 0;
      this.clubName_ = "";
      this.level_ = 0;
      this.userFansClubStatus_ = 0;
      this.badge_ = null;
      if (this.badgeBuilder_ != null) {
        this.badgeBuilder_.dispose();
        this.badgeBuilder_ = null;
      } 
      this.availableGiftIds_ = FansClubData.emptyLongList();
      this.anchorId_ = 0L;
      return this;
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
      return Douyin.internal_static_FansClubData_descriptor;
    }
    
    public FansClubData getDefaultInstanceForType() {
      return FansClubData.getDefaultInstance();
    }
    
    public FansClubData build() {
      FansClubData result = buildPartial();
      if (!result.isInitialized())
        throw newUninitializedMessageException(result); 
      return result;
    }
    
    public FansClubData buildPartial() {
      FansClubData result = new FansClubData(this);
      if (this.bitField0_ != 0)
        buildPartial0(result); 
      onBuilt();
      return result;
    }
    
    private void buildPartial0(FansClubData result) {
      int from_bitField0_ = this.bitField0_;
      if ((from_bitField0_ & 0x1) != 0)
        result.clubName_ = this.clubName_; 
      if ((from_bitField0_ & 0x2) != 0)
        result.level_ = this.level_; 
      if ((from_bitField0_ & 0x4) != 0)
        result.userFansClubStatus_ = this.userFansClubStatus_; 
      int to_bitField0_ = 0;
      if ((from_bitField0_ & 0x8) != 0) {
        result.badge_ = (this.badgeBuilder_ == null) ? this.badge_ : (UserBadge)this.badgeBuilder_.build();
        to_bitField0_ |= 0x1;
      } 
      if ((from_bitField0_ & 0x10) != 0) {
        this.availableGiftIds_.makeImmutable();
        result.availableGiftIds_ = this.availableGiftIds_;
      } 
      if ((from_bitField0_ & 0x20) != 0)
        result.anchorId_ = this.anchorId_; 
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
      if (other instanceof FansClubData)
        return mergeFrom((FansClubData)other); 
      super.mergeFrom(other);
      return this;
    }
    
    public Builder mergeFrom(FansClubData other) {
      if (other == FansClubData.getDefaultInstance())
        return this; 
      if (!other.getClubName().isEmpty()) {
        this.clubName_ = other.clubName_;
        this.bitField0_ |= 0x1;
        onChanged();
      } 
      if (other.getLevel() != 0)
        setLevel(other.getLevel()); 
      if (other.getUserFansClubStatus() != 0)
        setUserFansClubStatus(other.getUserFansClubStatus()); 
      if (other.hasBadge())
        mergeBadge(other.getBadge()); 
      if (!other.availableGiftIds_.isEmpty()) {
        if (this.availableGiftIds_.isEmpty()) {
          this.availableGiftIds_ = other.availableGiftIds_;
          this.availableGiftIds_.makeImmutable();
          this.bitField0_ |= 0x10;
        } else {
          ensureAvailableGiftIdsIsMutable();
          this.availableGiftIds_.addAll((Collection)other.availableGiftIds_);
        } 
        onChanged();
      } 
      if (other.getAnchorId() != 0L)
        setAnchorId(other.getAnchorId()); 
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
          long v;
          int length, limit, tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              continue;
            case 10:
              this.clubName_ = input.readStringRequireUtf8();
              this.bitField0_ |= 0x1;
              continue;
            case 16:
              this.level_ = input.readInt32();
              this.bitField0_ |= 0x2;
              continue;
            case 24:
              this.userFansClubStatus_ = input.readInt32();
              this.bitField0_ |= 0x4;
              continue;
            case 34:
              input.readMessage((MessageLite.Builder)getBadgeFieldBuilder().getBuilder(), extensionRegistry);
              this.bitField0_ |= 0x8;
              continue;
            case 40:
              v = input.readInt64();
              ensureAvailableGiftIdsIsMutable();
              this.availableGiftIds_.addLong(v);
              continue;
            case 42:
              length = input.readRawVarint32();
              limit = input.pushLimit(length);
              ensureAvailableGiftIdsIsMutable();
              while (input.getBytesUntilLimit() > 0)
                this.availableGiftIds_.addLong(input.readInt64()); 
              input.popLimit(limit);
              continue;
            case 48:
              this.anchorId_ = input.readInt64();
              this.bitField0_ |= 0x20;
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
    
    public String getClubName() {
      Object ref = this.clubName_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.clubName_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getClubNameBytes() {
      Object ref = this.clubName_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.clubName_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setClubName(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.clubName_ = value;
      this.bitField0_ |= 0x1;
      onChanged();
      return this;
    }
    
    public Builder clearClubName() {
      this.clubName_ = FansClubData.getDefaultInstance().getClubName();
      this.bitField0_ &= 0xFFFFFFFE;
      onChanged();
      return this;
    }
    
    public Builder setClubNameBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      FansClubData.checkByteStringIsUtf8(value);
      this.clubName_ = value;
      this.bitField0_ |= 0x1;
      onChanged();
      return this;
    }
    
    public int getLevel() {
      return this.level_;
    }
    
    public Builder setLevel(int value) {
      this.level_ = value;
      this.bitField0_ |= 0x2;
      onChanged();
      return this;
    }
    
    public Builder clearLevel() {
      this.bitField0_ &= 0xFFFFFFFD;
      this.level_ = 0;
      onChanged();
      return this;
    }
    
    public int getUserFansClubStatus() {
      return this.userFansClubStatus_;
    }
    
    public Builder setUserFansClubStatus(int value) {
      this.userFansClubStatus_ = value;
      this.bitField0_ |= 0x4;
      onChanged();
      return this;
    }
    
    public Builder clearUserFansClubStatus() {
      this.bitField0_ &= 0xFFFFFFFB;
      this.userFansClubStatus_ = 0;
      onChanged();
      return this;
    }
    
    public boolean hasBadge() {
      return ((this.bitField0_ & 0x8) != 0);
    }
    
    public UserBadge getBadge() {
      if (this.badgeBuilder_ == null)
        return (this.badge_ == null) ? UserBadge.getDefaultInstance() : this.badge_; 
      return (UserBadge)this.badgeBuilder_.getMessage();
    }
    
    public Builder setBadge(UserBadge value) {
      if (this.badgeBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        this.badge_ = value;
      } else {
        this.badgeBuilder_.setMessage((AbstractMessage)value);
      } 
      this.bitField0_ |= 0x8;
      onChanged();
      return this;
    }
    
    public Builder setBadge(UserBadge.Builder builderForValue) {
      if (this.badgeBuilder_ == null) {
        this.badge_ = builderForValue.build();
      } else {
        this.badgeBuilder_.setMessage((AbstractMessage)builderForValue.build());
      } 
      this.bitField0_ |= 0x8;
      onChanged();
      return this;
    }
    
    public Builder mergeBadge(UserBadge value) {
      if (this.badgeBuilder_ == null) {
        if ((this.bitField0_ & 0x8) != 0 && this.badge_ != null && this.badge_ != UserBadge.getDefaultInstance()) {
          getBadgeBuilder().mergeFrom(value);
        } else {
          this.badge_ = value;
        } 
      } else {
        this.badgeBuilder_.mergeFrom((AbstractMessage)value);
      } 
      if (this.badge_ != null) {
        this.bitField0_ |= 0x8;
        onChanged();
      } 
      return this;
    }
    
    public Builder clearBadge() {
      this.bitField0_ &= 0xFFFFFFF7;
      this.badge_ = null;
      if (this.badgeBuilder_ != null) {
        this.badgeBuilder_.dispose();
        this.badgeBuilder_ = null;
      } 
      onChanged();
      return this;
    }
    
    public UserBadge.Builder getBadgeBuilder() {
      this.bitField0_ |= 0x8;
      onChanged();
      return (UserBadge.Builder)getBadgeFieldBuilder().getBuilder();
    }
    
    public UserBadgeOrBuilder getBadgeOrBuilder() {
      if (this.badgeBuilder_ != null)
        return (UserBadgeOrBuilder)this.badgeBuilder_.getMessageOrBuilder(); 
      return (this.badge_ == null) ? UserBadge.getDefaultInstance() : this.badge_;
    }
    
    private SingleFieldBuilderV3<UserBadge, UserBadge.Builder, UserBadgeOrBuilder> getBadgeFieldBuilder() {
      if (this.badgeBuilder_ == null) {
        this.badgeBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getBadge(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.badge_ = null;
      } 
      return this.badgeBuilder_;
    }
    
    private void ensureAvailableGiftIdsIsMutable() {
      if (!this.availableGiftIds_.isModifiable())
        this.availableGiftIds_ = (Internal.LongList)FansClubData.makeMutableCopy((Internal.ProtobufList)this.availableGiftIds_); 
      this.bitField0_ |= 0x10;
    }
    
    public List<Long> getAvailableGiftIdsList() {
      this.availableGiftIds_.makeImmutable();
      return (List<Long>)this.availableGiftIds_;
    }
    
    public int getAvailableGiftIdsCount() {
      return this.availableGiftIds_.size();
    }
    
    public long getAvailableGiftIds(int index) {
      return this.availableGiftIds_.getLong(index);
    }
    
    public Builder setAvailableGiftIds(int index, long value) {
      ensureAvailableGiftIdsIsMutable();
      this.availableGiftIds_.setLong(index, value);
      this.bitField0_ |= 0x10;
      onChanged();
      return this;
    }
    
    public Builder addAvailableGiftIds(long value) {
      ensureAvailableGiftIdsIsMutable();
      this.availableGiftIds_.addLong(value);
      this.bitField0_ |= 0x10;
      onChanged();
      return this;
    }
    
    public Builder addAllAvailableGiftIds(Iterable<? extends Long> values) {
      ensureAvailableGiftIdsIsMutable();
      AbstractMessageLite.Builder.addAll(values, (List)this.availableGiftIds_);
      this.bitField0_ |= 0x10;
      onChanged();
      return this;
    }
    
    public Builder clearAvailableGiftIds() {
      this.availableGiftIds_ = FansClubData.emptyLongList();
      this.bitField0_ &= 0xFFFFFFEF;
      onChanged();
      return this;
    }
    
    public long getAnchorId() {
      return this.anchorId_;
    }
    
    public Builder setAnchorId(long value) {
      this.anchorId_ = value;
      this.bitField0_ |= 0x20;
      onChanged();
      return this;
    }
    
    public Builder clearAnchorId() {
      this.bitField0_ &= 0xFFFFFFDF;
      this.anchorId_ = 0L;
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
  
  private static final FansClubData DEFAULT_INSTANCE = new FansClubData();
  
  public static FansClubData getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }
  
  private static final Parser<FansClubData> PARSER = (Parser<FansClubData>)new AbstractParser<FansClubData>() {
      public FansClubData parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        FansClubData.Builder builder = FansClubData.newBuilder();
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
  
  public static Parser<FansClubData> parser() {
    return PARSER;
  }
  
  public Parser<FansClubData> getParserForType() {
    return PARSER;
  }
  
  public FansClubData getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }
}
