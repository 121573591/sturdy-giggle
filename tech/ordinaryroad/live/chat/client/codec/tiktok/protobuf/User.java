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
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class User extends GeneratedMessageV3 implements UserOrBuilder {
  private static final long serialVersionUID = 0L;
  
  private int bitField0_;
  
  public static final int ID_FIELD_NUMBER = 1;
  
  private long id_;
  
  public static final int NICKNAME_FIELD_NUMBER = 3;
  
  private volatile Object nickName_;
  
  public static final int AVATARTHUMB_FIELD_NUMBER = 9;
  
  private Image avatarThumb_;
  
  public static final int AVATARMEDIUM_FIELD_NUMBER = 10;
  
  private Image avatarMedium_;
  
  public static final int AVATARLARGE_FIELD_NUMBER = 11;
  
  private Image avatarLarge_;
  
  public static final int BADGEIMAGELIST_FIELD_NUMBER = 21;
  
  private List<Image> badgeImageList_;
  
  public static final int FOLLOWINFO_FIELD_NUMBER = 22;
  
  private FollowInfo followInfo_;
  
  public static final int PAYGRADE_FIELD_NUMBER = 23;
  
  private PayGrade payGrade_;
  
  public static final int DISPLAYID_FIELD_NUMBER = 38;
  
  private volatile Object displayId_;
  
  public static final int SECUID_FIELD_NUMBER = 46;
  
  private volatile Object secUid_;
  
  public static final int IDSTR_FIELD_NUMBER = 1028;
  
  private volatile Object idStr_;
  
  private byte memoizedIsInitialized;
  
  private User(GeneratedMessageV3.Builder<?> builder) {
    super(builder);
    this.id_ = 0L;
    this.nickName_ = "";
    this.displayId_ = "";
    this.secUid_ = "";
    this.idStr_ = "";
    this.memoizedIsInitialized = -1;
  }
  
  private User() {
    this.id_ = 0L;
    this.nickName_ = "";
    this.displayId_ = "";
    this.secUid_ = "";
    this.idStr_ = "";
    this.memoizedIsInitialized = -1;
    this.nickName_ = "";
    this.badgeImageList_ = Collections.emptyList();
    this.displayId_ = "";
    this.secUid_ = "";
    this.idStr_ = "";
  }
  
  protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
    return new User();
  }
  
  public static final Descriptors.Descriptor getDescriptor() {
    return Tiktok.internal_static_User_descriptor;
  }
  
  protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
    return Tiktok.internal_static_User_fieldAccessorTable.ensureFieldAccessorsInitialized(User.class, Builder.class);
  }
  
  public long getId() {
    return this.id_;
  }
  
  public String getNickName() {
    Object ref = this.nickName_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.nickName_ = s;
    return s;
  }
  
  public ByteString getNickNameBytes() {
    Object ref = this.nickName_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.nickName_ = b;
      return b;
    } 
    return (ByteString)ref;
  }
  
  public boolean hasAvatarThumb() {
    return ((this.bitField0_ & 0x1) != 0);
  }
  
  public Image getAvatarThumb() {
    return (this.avatarThumb_ == null) ? Image.getDefaultInstance() : this.avatarThumb_;
  }
  
  public ImageOrBuilder getAvatarThumbOrBuilder() {
    return (this.avatarThumb_ == null) ? Image.getDefaultInstance() : this.avatarThumb_;
  }
  
  public boolean hasAvatarMedium() {
    return ((this.bitField0_ & 0x2) != 0);
  }
  
  public Image getAvatarMedium() {
    return (this.avatarMedium_ == null) ? Image.getDefaultInstance() : this.avatarMedium_;
  }
  
  public ImageOrBuilder getAvatarMediumOrBuilder() {
    return (this.avatarMedium_ == null) ? Image.getDefaultInstance() : this.avatarMedium_;
  }
  
  public boolean hasAvatarLarge() {
    return ((this.bitField0_ & 0x4) != 0);
  }
  
  public Image getAvatarLarge() {
    return (this.avatarLarge_ == null) ? Image.getDefaultInstance() : this.avatarLarge_;
  }
  
  public ImageOrBuilder getAvatarLargeOrBuilder() {
    return (this.avatarLarge_ == null) ? Image.getDefaultInstance() : this.avatarLarge_;
  }
  
  public List<Image> getBadgeImageListList() {
    return this.badgeImageList_;
  }
  
  public List<? extends ImageOrBuilder> getBadgeImageListOrBuilderList() {
    return (List)this.badgeImageList_;
  }
  
  public int getBadgeImageListCount() {
    return this.badgeImageList_.size();
  }
  
  public Image getBadgeImageList(int index) {
    return this.badgeImageList_.get(index);
  }
  
  public ImageOrBuilder getBadgeImageListOrBuilder(int index) {
    return this.badgeImageList_.get(index);
  }
  
  public boolean hasFollowInfo() {
    return ((this.bitField0_ & 0x8) != 0);
  }
  
  public FollowInfo getFollowInfo() {
    return (this.followInfo_ == null) ? FollowInfo.getDefaultInstance() : this.followInfo_;
  }
  
  public FollowInfoOrBuilder getFollowInfoOrBuilder() {
    return (this.followInfo_ == null) ? FollowInfo.getDefaultInstance() : this.followInfo_;
  }
  
  public boolean hasPayGrade() {
    return ((this.bitField0_ & 0x10) != 0);
  }
  
  public PayGrade getPayGrade() {
    return (this.payGrade_ == null) ? PayGrade.getDefaultInstance() : this.payGrade_;
  }
  
  public PayGradeOrBuilder getPayGradeOrBuilder() {
    return (this.payGrade_ == null) ? PayGrade.getDefaultInstance() : this.payGrade_;
  }
  
  public String getDisplayId() {
    Object ref = this.displayId_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.displayId_ = s;
    return s;
  }
  
  public ByteString getDisplayIdBytes() {
    Object ref = this.displayId_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.displayId_ = b;
      return b;
    } 
    return (ByteString)ref;
  }
  
  public String getSecUid() {
    Object ref = this.secUid_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.secUid_ = s;
    return s;
  }
  
  public ByteString getSecUidBytes() {
    Object ref = this.secUid_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.secUid_ = b;
      return b;
    } 
    return (ByteString)ref;
  }
  
  public String getIdStr() {
    Object ref = this.idStr_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.idStr_ = s;
    return s;
  }
  
  public ByteString getIdStrBytes() {
    Object ref = this.idStr_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.idStr_ = b;
      return b;
    } 
    return (ByteString)ref;
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
    if (this.id_ != 0L)
      output.writeUInt64(1, this.id_); 
    if (!GeneratedMessageV3.isStringEmpty(this.nickName_))
      GeneratedMessageV3.writeString(output, 3, this.nickName_); 
    if ((this.bitField0_ & 0x1) != 0)
      output.writeMessage(9, (MessageLite)getAvatarThumb()); 
    if ((this.bitField0_ & 0x2) != 0)
      output.writeMessage(10, (MessageLite)getAvatarMedium()); 
    if ((this.bitField0_ & 0x4) != 0)
      output.writeMessage(11, (MessageLite)getAvatarLarge()); 
    for (int i = 0; i < this.badgeImageList_.size(); i++)
      output.writeMessage(21, (MessageLite)this.badgeImageList_.get(i)); 
    if ((this.bitField0_ & 0x8) != 0)
      output.writeMessage(22, (MessageLite)getFollowInfo()); 
    if ((this.bitField0_ & 0x10) != 0)
      output.writeMessage(23, (MessageLite)getPayGrade()); 
    if (!GeneratedMessageV3.isStringEmpty(this.displayId_))
      GeneratedMessageV3.writeString(output, 38, this.displayId_); 
    if (!GeneratedMessageV3.isStringEmpty(this.secUid_))
      GeneratedMessageV3.writeString(output, 46, this.secUid_); 
    if (!GeneratedMessageV3.isStringEmpty(this.idStr_))
      GeneratedMessageV3.writeString(output, 1028, this.idStr_); 
    getUnknownFields().writeTo(output);
  }
  
  public int getSerializedSize() {
    int size = this.memoizedSize;
    if (size != -1)
      return size; 
    size = 0;
    if (this.id_ != 0L)
      size += 
        CodedOutputStream.computeUInt64Size(1, this.id_); 
    if (!GeneratedMessageV3.isStringEmpty(this.nickName_))
      size += GeneratedMessageV3.computeStringSize(3, this.nickName_); 
    if ((this.bitField0_ & 0x1) != 0)
      size += 
        CodedOutputStream.computeMessageSize(9, (MessageLite)getAvatarThumb()); 
    if ((this.bitField0_ & 0x2) != 0)
      size += 
        CodedOutputStream.computeMessageSize(10, (MessageLite)getAvatarMedium()); 
    if ((this.bitField0_ & 0x4) != 0)
      size += 
        CodedOutputStream.computeMessageSize(11, (MessageLite)getAvatarLarge()); 
    for (int i = 0; i < this.badgeImageList_.size(); i++)
      size += 
        CodedOutputStream.computeMessageSize(21, (MessageLite)this.badgeImageList_.get(i)); 
    if ((this.bitField0_ & 0x8) != 0)
      size += 
        CodedOutputStream.computeMessageSize(22, (MessageLite)getFollowInfo()); 
    if ((this.bitField0_ & 0x10) != 0)
      size += 
        CodedOutputStream.computeMessageSize(23, (MessageLite)getPayGrade()); 
    if (!GeneratedMessageV3.isStringEmpty(this.displayId_))
      size += GeneratedMessageV3.computeStringSize(38, this.displayId_); 
    if (!GeneratedMessageV3.isStringEmpty(this.secUid_))
      size += GeneratedMessageV3.computeStringSize(46, this.secUid_); 
    if (!GeneratedMessageV3.isStringEmpty(this.idStr_))
      size += GeneratedMessageV3.computeStringSize(1028, this.idStr_); 
    size += getUnknownFields().getSerializedSize();
    this.memoizedSize = size;
    return size;
  }
  
  public boolean equals(Object obj) {
    if (obj == this)
      return true; 
    if (!(obj instanceof User))
      return super.equals(obj); 
    User other = (User)obj;
    if (getId() != other
      .getId())
      return false; 
    if (!getNickName().equals(other.getNickName()))
      return false; 
    if (hasAvatarThumb() != other.hasAvatarThumb())
      return false; 
    if (hasAvatarThumb() && 
      
      !getAvatarThumb().equals(other.getAvatarThumb()))
      return false; 
    if (hasAvatarMedium() != other.hasAvatarMedium())
      return false; 
    if (hasAvatarMedium() && 
      
      !getAvatarMedium().equals(other.getAvatarMedium()))
      return false; 
    if (hasAvatarLarge() != other.hasAvatarLarge())
      return false; 
    if (hasAvatarLarge() && 
      
      !getAvatarLarge().equals(other.getAvatarLarge()))
      return false; 
    if (!getBadgeImageListList().equals(other.getBadgeImageListList()))
      return false; 
    if (hasFollowInfo() != other.hasFollowInfo())
      return false; 
    if (hasFollowInfo() && 
      
      !getFollowInfo().equals(other.getFollowInfo()))
      return false; 
    if (hasPayGrade() != other.hasPayGrade())
      return false; 
    if (hasPayGrade() && 
      
      !getPayGrade().equals(other.getPayGrade()))
      return false; 
    if (!getDisplayId().equals(other.getDisplayId()))
      return false; 
    if (!getSecUid().equals(other.getSecUid()))
      return false; 
    if (!getIdStr().equals(other.getIdStr()))
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
        getId());
    hash = 37 * hash + 3;
    hash = 53 * hash + getNickName().hashCode();
    if (hasAvatarThumb()) {
      hash = 37 * hash + 9;
      hash = 53 * hash + getAvatarThumb().hashCode();
    } 
    if (hasAvatarMedium()) {
      hash = 37 * hash + 10;
      hash = 53 * hash + getAvatarMedium().hashCode();
    } 
    if (hasAvatarLarge()) {
      hash = 37 * hash + 11;
      hash = 53 * hash + getAvatarLarge().hashCode();
    } 
    if (getBadgeImageListCount() > 0) {
      hash = 37 * hash + 21;
      hash = 53 * hash + getBadgeImageListList().hashCode();
    } 
    if (hasFollowInfo()) {
      hash = 37 * hash + 22;
      hash = 53 * hash + getFollowInfo().hashCode();
    } 
    if (hasPayGrade()) {
      hash = 37 * hash + 23;
      hash = 53 * hash + getPayGrade().hashCode();
    } 
    hash = 37 * hash + 38;
    hash = 53 * hash + getDisplayId().hashCode();
    hash = 37 * hash + 46;
    hash = 53 * hash + getSecUid().hashCode();
    hash = 37 * hash + 1028;
    hash = 53 * hash + getIdStr().hashCode();
    hash = 29 * hash + getUnknownFields().hashCode();
    this.memoizedHashCode = hash;
    return hash;
  }
  
  public static User parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
    return (User)PARSER.parseFrom(data);
  }
  
  public static User parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (User)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static User parseFrom(ByteString data) throws InvalidProtocolBufferException {
    return (User)PARSER.parseFrom(data);
  }
  
  public static User parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (User)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static User parseFrom(byte[] data) throws InvalidProtocolBufferException {
    return (User)PARSER.parseFrom(data);
  }
  
  public static User parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (User)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static User parseFrom(InputStream input) throws IOException {
    return 
      (User)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static User parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (User)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static User parseDelimitedFrom(InputStream input) throws IOException {
    return 
      (User)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
  }
  
  public static User parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (User)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static User parseFrom(CodedInputStream input) throws IOException {
    return 
      (User)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static User parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (User)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public Builder newBuilderForType() {
    return newBuilder();
  }
  
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  
  public static Builder newBuilder(User prototype) {
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
  
  public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements UserOrBuilder {
    private int bitField0_;
    
    private long id_;
    
    private Object nickName_;
    
    private Image avatarThumb_;
    
    private SingleFieldBuilderV3<Image, Image.Builder, ImageOrBuilder> avatarThumbBuilder_;
    
    private Image avatarMedium_;
    
    private SingleFieldBuilderV3<Image, Image.Builder, ImageOrBuilder> avatarMediumBuilder_;
    
    private Image avatarLarge_;
    
    private SingleFieldBuilderV3<Image, Image.Builder, ImageOrBuilder> avatarLargeBuilder_;
    
    private List<Image> badgeImageList_;
    
    private RepeatedFieldBuilderV3<Image, Image.Builder, ImageOrBuilder> badgeImageListBuilder_;
    
    private FollowInfo followInfo_;
    
    private SingleFieldBuilderV3<FollowInfo, FollowInfo.Builder, FollowInfoOrBuilder> followInfoBuilder_;
    
    private PayGrade payGrade_;
    
    private SingleFieldBuilderV3<PayGrade, PayGrade.Builder, PayGradeOrBuilder> payGradeBuilder_;
    
    private Object displayId_;
    
    private Object secUid_;
    
    private Object idStr_;
    
    public static final Descriptors.Descriptor getDescriptor() {
      return Tiktok.internal_static_User_descriptor;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return Tiktok.internal_static_User_fieldAccessorTable
        .ensureFieldAccessorsInitialized(User.class, Builder.class);
    }
    
    private Builder() {
      this.nickName_ = "";
      this
        .badgeImageList_ = Collections.emptyList();
      this.displayId_ = "";
      this.secUid_ = "";
      this.idStr_ = "";
      maybeForceBuilderInitialization();
    }
    
    private Builder(GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      this.nickName_ = "";
      this.badgeImageList_ = Collections.emptyList();
      this.displayId_ = "";
      this.secUid_ = "";
      this.idStr_ = "";
      maybeForceBuilderInitialization();
    }
    
    private void maybeForceBuilderInitialization() {
      if (User.alwaysUseFieldBuilders) {
        getAvatarThumbFieldBuilder();
        getAvatarMediumFieldBuilder();
        getAvatarLargeFieldBuilder();
        getBadgeImageListFieldBuilder();
        getFollowInfoFieldBuilder();
        getPayGradeFieldBuilder();
      } 
    }
    
    public Builder clear() {
      super.clear();
      this.bitField0_ = 0;
      this.id_ = 0L;
      this.nickName_ = "";
      this.avatarThumb_ = null;
      if (this.avatarThumbBuilder_ != null) {
        this.avatarThumbBuilder_.dispose();
        this.avatarThumbBuilder_ = null;
      } 
      this.avatarMedium_ = null;
      if (this.avatarMediumBuilder_ != null) {
        this.avatarMediumBuilder_.dispose();
        this.avatarMediumBuilder_ = null;
      } 
      this.avatarLarge_ = null;
      if (this.avatarLargeBuilder_ != null) {
        this.avatarLargeBuilder_.dispose();
        this.avatarLargeBuilder_ = null;
      } 
      if (this.badgeImageListBuilder_ == null) {
        this.badgeImageList_ = Collections.emptyList();
      } else {
        this.badgeImageList_ = null;
        this.badgeImageListBuilder_.clear();
      } 
      this.bitField0_ &= 0xFFFFFFDF;
      this.followInfo_ = null;
      if (this.followInfoBuilder_ != null) {
        this.followInfoBuilder_.dispose();
        this.followInfoBuilder_ = null;
      } 
      this.payGrade_ = null;
      if (this.payGradeBuilder_ != null) {
        this.payGradeBuilder_.dispose();
        this.payGradeBuilder_ = null;
      } 
      this.displayId_ = "";
      this.secUid_ = "";
      this.idStr_ = "";
      return this;
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
      return Tiktok.internal_static_User_descriptor;
    }
    
    public User getDefaultInstanceForType() {
      return User.getDefaultInstance();
    }
    
    public User build() {
      User result = buildPartial();
      if (!result.isInitialized())
        throw newUninitializedMessageException(result); 
      return result;
    }
    
    public User buildPartial() {
      User result = new User(this);
      buildPartialRepeatedFields(result);
      if (this.bitField0_ != 0)
        buildPartial0(result); 
      onBuilt();
      return result;
    }
    
    private void buildPartialRepeatedFields(User result) {
      if (this.badgeImageListBuilder_ == null) {
        if ((this.bitField0_ & 0x20) != 0) {
          this.badgeImageList_ = Collections.unmodifiableList(this.badgeImageList_);
          this.bitField0_ &= 0xFFFFFFDF;
        } 
        result.badgeImageList_ = this.badgeImageList_;
      } else {
        result.badgeImageList_ = this.badgeImageListBuilder_.build();
      } 
    }
    
    private void buildPartial0(User result) {
      int from_bitField0_ = this.bitField0_;
      if ((from_bitField0_ & 0x1) != 0)
        result.id_ = this.id_; 
      if ((from_bitField0_ & 0x2) != 0)
        result.nickName_ = this.nickName_; 
      int to_bitField0_ = 0;
      if ((from_bitField0_ & 0x4) != 0) {
        result.avatarThumb_ = (this.avatarThumbBuilder_ == null) ? this.avatarThumb_ : (Image)this.avatarThumbBuilder_.build();
        to_bitField0_ |= 0x1;
      } 
      if ((from_bitField0_ & 0x8) != 0) {
        result.avatarMedium_ = (this.avatarMediumBuilder_ == null) ? this.avatarMedium_ : (Image)this.avatarMediumBuilder_.build();
        to_bitField0_ |= 0x2;
      } 
      if ((from_bitField0_ & 0x10) != 0) {
        result.avatarLarge_ = (this.avatarLargeBuilder_ == null) ? this.avatarLarge_ : (Image)this.avatarLargeBuilder_.build();
        to_bitField0_ |= 0x4;
      } 
      if ((from_bitField0_ & 0x40) != 0) {
        result.followInfo_ = (this.followInfoBuilder_ == null) ? this.followInfo_ : (FollowInfo)this.followInfoBuilder_.build();
        to_bitField0_ |= 0x8;
      } 
      if ((from_bitField0_ & 0x80) != 0) {
        result.payGrade_ = (this.payGradeBuilder_ == null) ? this.payGrade_ : (PayGrade)this.payGradeBuilder_.build();
        to_bitField0_ |= 0x10;
      } 
      if ((from_bitField0_ & 0x100) != 0)
        result.displayId_ = this.displayId_; 
      if ((from_bitField0_ & 0x200) != 0)
        result.secUid_ = this.secUid_; 
      if ((from_bitField0_ & 0x400) != 0)
        result.idStr_ = this.idStr_; 
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
      if (other instanceof User)
        return mergeFrom((User)other); 
      super.mergeFrom(other);
      return this;
    }
    
    public Builder mergeFrom(User other) {
      if (other == User.getDefaultInstance())
        return this; 
      if (other.getId() != 0L)
        setId(other.getId()); 
      if (!other.getNickName().isEmpty()) {
        this.nickName_ = other.nickName_;
        this.bitField0_ |= 0x2;
        onChanged();
      } 
      if (other.hasAvatarThumb())
        mergeAvatarThumb(other.getAvatarThumb()); 
      if (other.hasAvatarMedium())
        mergeAvatarMedium(other.getAvatarMedium()); 
      if (other.hasAvatarLarge())
        mergeAvatarLarge(other.getAvatarLarge()); 
      if (this.badgeImageListBuilder_ == null) {
        if (!other.badgeImageList_.isEmpty()) {
          if (this.badgeImageList_.isEmpty()) {
            this.badgeImageList_ = other.badgeImageList_;
            this.bitField0_ &= 0xFFFFFFDF;
          } else {
            ensureBadgeImageListIsMutable();
            this.badgeImageList_.addAll(other.badgeImageList_);
          } 
          onChanged();
        } 
      } else if (!other.badgeImageList_.isEmpty()) {
        if (this.badgeImageListBuilder_.isEmpty()) {
          this.badgeImageListBuilder_.dispose();
          this.badgeImageListBuilder_ = null;
          this.badgeImageList_ = other.badgeImageList_;
          this.bitField0_ &= 0xFFFFFFDF;
          this.badgeImageListBuilder_ = User.alwaysUseFieldBuilders ? getBadgeImageListFieldBuilder() : null;
        } else {
          this.badgeImageListBuilder_.addAllMessages(other.badgeImageList_);
        } 
      } 
      if (other.hasFollowInfo())
        mergeFollowInfo(other.getFollowInfo()); 
      if (other.hasPayGrade())
        mergePayGrade(other.getPayGrade()); 
      if (!other.getDisplayId().isEmpty()) {
        this.displayId_ = other.displayId_;
        this.bitField0_ |= 0x100;
        onChanged();
      } 
      if (!other.getSecUid().isEmpty()) {
        this.secUid_ = other.secUid_;
        this.bitField0_ |= 0x200;
        onChanged();
      } 
      if (!other.getIdStr().isEmpty()) {
        this.idStr_ = other.idStr_;
        this.bitField0_ |= 0x400;
        onChanged();
      } 
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
          Image m;
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              continue;
            case 8:
              this.id_ = input.readUInt64();
              this.bitField0_ |= 0x1;
              continue;
            case 26:
              this.nickName_ = input.readStringRequireUtf8();
              this.bitField0_ |= 0x2;
              continue;
            case 74:
              input.readMessage((MessageLite.Builder)getAvatarThumbFieldBuilder().getBuilder(), extensionRegistry);
              this.bitField0_ |= 0x4;
              continue;
            case 82:
              input.readMessage((MessageLite.Builder)getAvatarMediumFieldBuilder().getBuilder(), extensionRegistry);
              this.bitField0_ |= 0x8;
              continue;
            case 90:
              input.readMessage((MessageLite.Builder)getAvatarLargeFieldBuilder().getBuilder(), extensionRegistry);
              this.bitField0_ |= 0x10;
              continue;
            case 170:
              m = (Image)input.readMessage(Image.parser(), extensionRegistry);
              if (this.badgeImageListBuilder_ == null) {
                ensureBadgeImageListIsMutable();
                this.badgeImageList_.add(m);
                continue;
              } 
              this.badgeImageListBuilder_.addMessage((AbstractMessage)m);
              continue;
            case 178:
              input.readMessage((MessageLite.Builder)getFollowInfoFieldBuilder().getBuilder(), extensionRegistry);
              this.bitField0_ |= 0x40;
              continue;
            case 186:
              input.readMessage((MessageLite.Builder)getPayGradeFieldBuilder().getBuilder(), extensionRegistry);
              this.bitField0_ |= 0x80;
              continue;
            case 306:
              this.displayId_ = input.readStringRequireUtf8();
              this.bitField0_ |= 0x100;
              continue;
            case 370:
              this.secUid_ = input.readStringRequireUtf8();
              this.bitField0_ |= 0x200;
              continue;
            case 8226:
              this.idStr_ = input.readStringRequireUtf8();
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
    
    public long getId() {
      return this.id_;
    }
    
    public Builder setId(long value) {
      this.id_ = value;
      this.bitField0_ |= 0x1;
      onChanged();
      return this;
    }
    
    public Builder clearId() {
      this.bitField0_ &= 0xFFFFFFFE;
      this.id_ = 0L;
      onChanged();
      return this;
    }
    
    public String getNickName() {
      Object ref = this.nickName_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.nickName_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getNickNameBytes() {
      Object ref = this.nickName_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.nickName_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setNickName(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.nickName_ = value;
      this.bitField0_ |= 0x2;
      onChanged();
      return this;
    }
    
    public Builder clearNickName() {
      this.nickName_ = User.getDefaultInstance().getNickName();
      this.bitField0_ &= 0xFFFFFFFD;
      onChanged();
      return this;
    }
    
    public Builder setNickNameBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      User.checkByteStringIsUtf8(value);
      this.nickName_ = value;
      this.bitField0_ |= 0x2;
      onChanged();
      return this;
    }
    
    public boolean hasAvatarThumb() {
      return ((this.bitField0_ & 0x4) != 0);
    }
    
    public Image getAvatarThumb() {
      if (this.avatarThumbBuilder_ == null)
        return (this.avatarThumb_ == null) ? Image.getDefaultInstance() : this.avatarThumb_; 
      return (Image)this.avatarThumbBuilder_.getMessage();
    }
    
    public Builder setAvatarThumb(Image value) {
      if (this.avatarThumbBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        this.avatarThumb_ = value;
      } else {
        this.avatarThumbBuilder_.setMessage((AbstractMessage)value);
      } 
      this.bitField0_ |= 0x4;
      onChanged();
      return this;
    }
    
    public Builder setAvatarThumb(Image.Builder builderForValue) {
      if (this.avatarThumbBuilder_ == null) {
        this.avatarThumb_ = builderForValue.build();
      } else {
        this.avatarThumbBuilder_.setMessage((AbstractMessage)builderForValue.build());
      } 
      this.bitField0_ |= 0x4;
      onChanged();
      return this;
    }
    
    public Builder mergeAvatarThumb(Image value) {
      if (this.avatarThumbBuilder_ == null) {
        if ((this.bitField0_ & 0x4) != 0 && this.avatarThumb_ != null && this.avatarThumb_ != Image.getDefaultInstance()) {
          getAvatarThumbBuilder().mergeFrom(value);
        } else {
          this.avatarThumb_ = value;
        } 
      } else {
        this.avatarThumbBuilder_.mergeFrom((AbstractMessage)value);
      } 
      if (this.avatarThumb_ != null) {
        this.bitField0_ |= 0x4;
        onChanged();
      } 
      return this;
    }
    
    public Builder clearAvatarThumb() {
      this.bitField0_ &= 0xFFFFFFFB;
      this.avatarThumb_ = null;
      if (this.avatarThumbBuilder_ != null) {
        this.avatarThumbBuilder_.dispose();
        this.avatarThumbBuilder_ = null;
      } 
      onChanged();
      return this;
    }
    
    public Image.Builder getAvatarThumbBuilder() {
      this.bitField0_ |= 0x4;
      onChanged();
      return (Image.Builder)getAvatarThumbFieldBuilder().getBuilder();
    }
    
    public ImageOrBuilder getAvatarThumbOrBuilder() {
      if (this.avatarThumbBuilder_ != null)
        return (ImageOrBuilder)this.avatarThumbBuilder_.getMessageOrBuilder(); 
      return (this.avatarThumb_ == null) ? Image.getDefaultInstance() : this.avatarThumb_;
    }
    
    private SingleFieldBuilderV3<Image, Image.Builder, ImageOrBuilder> getAvatarThumbFieldBuilder() {
      if (this.avatarThumbBuilder_ == null) {
        this.avatarThumbBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getAvatarThumb(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.avatarThumb_ = null;
      } 
      return this.avatarThumbBuilder_;
    }
    
    public boolean hasAvatarMedium() {
      return ((this.bitField0_ & 0x8) != 0);
    }
    
    public Image getAvatarMedium() {
      if (this.avatarMediumBuilder_ == null)
        return (this.avatarMedium_ == null) ? Image.getDefaultInstance() : this.avatarMedium_; 
      return (Image)this.avatarMediumBuilder_.getMessage();
    }
    
    public Builder setAvatarMedium(Image value) {
      if (this.avatarMediumBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        this.avatarMedium_ = value;
      } else {
        this.avatarMediumBuilder_.setMessage((AbstractMessage)value);
      } 
      this.bitField0_ |= 0x8;
      onChanged();
      return this;
    }
    
    public Builder setAvatarMedium(Image.Builder builderForValue) {
      if (this.avatarMediumBuilder_ == null) {
        this.avatarMedium_ = builderForValue.build();
      } else {
        this.avatarMediumBuilder_.setMessage((AbstractMessage)builderForValue.build());
      } 
      this.bitField0_ |= 0x8;
      onChanged();
      return this;
    }
    
    public Builder mergeAvatarMedium(Image value) {
      if (this.avatarMediumBuilder_ == null) {
        if ((this.bitField0_ & 0x8) != 0 && this.avatarMedium_ != null && this.avatarMedium_ != Image.getDefaultInstance()) {
          getAvatarMediumBuilder().mergeFrom(value);
        } else {
          this.avatarMedium_ = value;
        } 
      } else {
        this.avatarMediumBuilder_.mergeFrom((AbstractMessage)value);
      } 
      if (this.avatarMedium_ != null) {
        this.bitField0_ |= 0x8;
        onChanged();
      } 
      return this;
    }
    
    public Builder clearAvatarMedium() {
      this.bitField0_ &= 0xFFFFFFF7;
      this.avatarMedium_ = null;
      if (this.avatarMediumBuilder_ != null) {
        this.avatarMediumBuilder_.dispose();
        this.avatarMediumBuilder_ = null;
      } 
      onChanged();
      return this;
    }
    
    public Image.Builder getAvatarMediumBuilder() {
      this.bitField0_ |= 0x8;
      onChanged();
      return (Image.Builder)getAvatarMediumFieldBuilder().getBuilder();
    }
    
    public ImageOrBuilder getAvatarMediumOrBuilder() {
      if (this.avatarMediumBuilder_ != null)
        return (ImageOrBuilder)this.avatarMediumBuilder_.getMessageOrBuilder(); 
      return (this.avatarMedium_ == null) ? Image.getDefaultInstance() : this.avatarMedium_;
    }
    
    private SingleFieldBuilderV3<Image, Image.Builder, ImageOrBuilder> getAvatarMediumFieldBuilder() {
      if (this.avatarMediumBuilder_ == null) {
        this.avatarMediumBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getAvatarMedium(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.avatarMedium_ = null;
      } 
      return this.avatarMediumBuilder_;
    }
    
    public boolean hasAvatarLarge() {
      return ((this.bitField0_ & 0x10) != 0);
    }
    
    public Image getAvatarLarge() {
      if (this.avatarLargeBuilder_ == null)
        return (this.avatarLarge_ == null) ? Image.getDefaultInstance() : this.avatarLarge_; 
      return (Image)this.avatarLargeBuilder_.getMessage();
    }
    
    public Builder setAvatarLarge(Image value) {
      if (this.avatarLargeBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        this.avatarLarge_ = value;
      } else {
        this.avatarLargeBuilder_.setMessage((AbstractMessage)value);
      } 
      this.bitField0_ |= 0x10;
      onChanged();
      return this;
    }
    
    public Builder setAvatarLarge(Image.Builder builderForValue) {
      if (this.avatarLargeBuilder_ == null) {
        this.avatarLarge_ = builderForValue.build();
      } else {
        this.avatarLargeBuilder_.setMessage((AbstractMessage)builderForValue.build());
      } 
      this.bitField0_ |= 0x10;
      onChanged();
      return this;
    }
    
    public Builder mergeAvatarLarge(Image value) {
      if (this.avatarLargeBuilder_ == null) {
        if ((this.bitField0_ & 0x10) != 0 && this.avatarLarge_ != null && this.avatarLarge_ != Image.getDefaultInstance()) {
          getAvatarLargeBuilder().mergeFrom(value);
        } else {
          this.avatarLarge_ = value;
        } 
      } else {
        this.avatarLargeBuilder_.mergeFrom((AbstractMessage)value);
      } 
      if (this.avatarLarge_ != null) {
        this.bitField0_ |= 0x10;
        onChanged();
      } 
      return this;
    }
    
    public Builder clearAvatarLarge() {
      this.bitField0_ &= 0xFFFFFFEF;
      this.avatarLarge_ = null;
      if (this.avatarLargeBuilder_ != null) {
        this.avatarLargeBuilder_.dispose();
        this.avatarLargeBuilder_ = null;
      } 
      onChanged();
      return this;
    }
    
    public Image.Builder getAvatarLargeBuilder() {
      this.bitField0_ |= 0x10;
      onChanged();
      return (Image.Builder)getAvatarLargeFieldBuilder().getBuilder();
    }
    
    public ImageOrBuilder getAvatarLargeOrBuilder() {
      if (this.avatarLargeBuilder_ != null)
        return (ImageOrBuilder)this.avatarLargeBuilder_.getMessageOrBuilder(); 
      return (this.avatarLarge_ == null) ? Image.getDefaultInstance() : this.avatarLarge_;
    }
    
    private SingleFieldBuilderV3<Image, Image.Builder, ImageOrBuilder> getAvatarLargeFieldBuilder() {
      if (this.avatarLargeBuilder_ == null) {
        this.avatarLargeBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getAvatarLarge(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.avatarLarge_ = null;
      } 
      return this.avatarLargeBuilder_;
    }
    
    private void ensureBadgeImageListIsMutable() {
      if ((this.bitField0_ & 0x20) == 0) {
        this.badgeImageList_ = new ArrayList<>(this.badgeImageList_);
        this.bitField0_ |= 0x20;
      } 
    }
    
    public List<Image> getBadgeImageListList() {
      if (this.badgeImageListBuilder_ == null)
        return Collections.unmodifiableList(this.badgeImageList_); 
      return this.badgeImageListBuilder_.getMessageList();
    }
    
    public int getBadgeImageListCount() {
      if (this.badgeImageListBuilder_ == null)
        return this.badgeImageList_.size(); 
      return this.badgeImageListBuilder_.getCount();
    }
    
    public Image getBadgeImageList(int index) {
      if (this.badgeImageListBuilder_ == null)
        return this.badgeImageList_.get(index); 
      return (Image)this.badgeImageListBuilder_.getMessage(index);
    }
    
    public Builder setBadgeImageList(int index, Image value) {
      if (this.badgeImageListBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        ensureBadgeImageListIsMutable();
        this.badgeImageList_.set(index, value);
        onChanged();
      } else {
        this.badgeImageListBuilder_.setMessage(index, (AbstractMessage)value);
      } 
      return this;
    }
    
    public Builder setBadgeImageList(int index, Image.Builder builderForValue) {
      if (this.badgeImageListBuilder_ == null) {
        ensureBadgeImageListIsMutable();
        this.badgeImageList_.set(index, builderForValue.build());
        onChanged();
      } else {
        this.badgeImageListBuilder_.setMessage(index, (AbstractMessage)builderForValue.build());
      } 
      return this;
    }
    
    public Builder addBadgeImageList(Image value) {
      if (this.badgeImageListBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        ensureBadgeImageListIsMutable();
        this.badgeImageList_.add(value);
        onChanged();
      } else {
        this.badgeImageListBuilder_.addMessage((AbstractMessage)value);
      } 
      return this;
    }
    
    public Builder addBadgeImageList(int index, Image value) {
      if (this.badgeImageListBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        ensureBadgeImageListIsMutable();
        this.badgeImageList_.add(index, value);
        onChanged();
      } else {
        this.badgeImageListBuilder_.addMessage(index, (AbstractMessage)value);
      } 
      return this;
    }
    
    public Builder addBadgeImageList(Image.Builder builderForValue) {
      if (this.badgeImageListBuilder_ == null) {
        ensureBadgeImageListIsMutable();
        this.badgeImageList_.add(builderForValue.build());
        onChanged();
      } else {
        this.badgeImageListBuilder_.addMessage((AbstractMessage)builderForValue.build());
      } 
      return this;
    }
    
    public Builder addBadgeImageList(int index, Image.Builder builderForValue) {
      if (this.badgeImageListBuilder_ == null) {
        ensureBadgeImageListIsMutable();
        this.badgeImageList_.add(index, builderForValue.build());
        onChanged();
      } else {
        this.badgeImageListBuilder_.addMessage(index, (AbstractMessage)builderForValue.build());
      } 
      return this;
    }
    
    public Builder addAllBadgeImageList(Iterable<? extends Image> values) {
      if (this.badgeImageListBuilder_ == null) {
        ensureBadgeImageListIsMutable();
        AbstractMessageLite.Builder.addAll(values, this.badgeImageList_);
        onChanged();
      } else {
        this.badgeImageListBuilder_.addAllMessages(values);
      } 
      return this;
    }
    
    public Builder clearBadgeImageList() {
      if (this.badgeImageListBuilder_ == null) {
        this.badgeImageList_ = Collections.emptyList();
        this.bitField0_ &= 0xFFFFFFDF;
        onChanged();
      } else {
        this.badgeImageListBuilder_.clear();
      } 
      return this;
    }
    
    public Builder removeBadgeImageList(int index) {
      if (this.badgeImageListBuilder_ == null) {
        ensureBadgeImageListIsMutable();
        this.badgeImageList_.remove(index);
        onChanged();
      } else {
        this.badgeImageListBuilder_.remove(index);
      } 
      return this;
    }
    
    public Image.Builder getBadgeImageListBuilder(int index) {
      return (Image.Builder)getBadgeImageListFieldBuilder().getBuilder(index);
    }
    
    public ImageOrBuilder getBadgeImageListOrBuilder(int index) {
      if (this.badgeImageListBuilder_ == null)
        return this.badgeImageList_.get(index); 
      return (ImageOrBuilder)this.badgeImageListBuilder_.getMessageOrBuilder(index);
    }
    
    public List<? extends ImageOrBuilder> getBadgeImageListOrBuilderList() {
      if (this.badgeImageListBuilder_ != null)
        return this.badgeImageListBuilder_.getMessageOrBuilderList(); 
      return Collections.unmodifiableList((List)this.badgeImageList_);
    }
    
    public Image.Builder addBadgeImageListBuilder() {
      return (Image.Builder)getBadgeImageListFieldBuilder().addBuilder((AbstractMessage)Image.getDefaultInstance());
    }
    
    public Image.Builder addBadgeImageListBuilder(int index) {
      return (Image.Builder)getBadgeImageListFieldBuilder().addBuilder(index, (AbstractMessage)Image.getDefaultInstance());
    }
    
    public List<Image.Builder> getBadgeImageListBuilderList() {
      return getBadgeImageListFieldBuilder().getBuilderList();
    }
    
    private RepeatedFieldBuilderV3<Image, Image.Builder, ImageOrBuilder> getBadgeImageListFieldBuilder() {
      if (this.badgeImageListBuilder_ == null) {
        this.badgeImageListBuilder_ = new RepeatedFieldBuilderV3(this.badgeImageList_, ((this.bitField0_ & 0x20) != 0), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.badgeImageList_ = null;
      } 
      return this.badgeImageListBuilder_;
    }
    
    public boolean hasFollowInfo() {
      return ((this.bitField0_ & 0x40) != 0);
    }
    
    public FollowInfo getFollowInfo() {
      if (this.followInfoBuilder_ == null)
        return (this.followInfo_ == null) ? FollowInfo.getDefaultInstance() : this.followInfo_; 
      return (FollowInfo)this.followInfoBuilder_.getMessage();
    }
    
    public Builder setFollowInfo(FollowInfo value) {
      if (this.followInfoBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        this.followInfo_ = value;
      } else {
        this.followInfoBuilder_.setMessage((AbstractMessage)value);
      } 
      this.bitField0_ |= 0x40;
      onChanged();
      return this;
    }
    
    public Builder setFollowInfo(FollowInfo.Builder builderForValue) {
      if (this.followInfoBuilder_ == null) {
        this.followInfo_ = builderForValue.build();
      } else {
        this.followInfoBuilder_.setMessage((AbstractMessage)builderForValue.build());
      } 
      this.bitField0_ |= 0x40;
      onChanged();
      return this;
    }
    
    public Builder mergeFollowInfo(FollowInfo value) {
      if (this.followInfoBuilder_ == null) {
        if ((this.bitField0_ & 0x40) != 0 && this.followInfo_ != null && this.followInfo_ != FollowInfo.getDefaultInstance()) {
          getFollowInfoBuilder().mergeFrom(value);
        } else {
          this.followInfo_ = value;
        } 
      } else {
        this.followInfoBuilder_.mergeFrom((AbstractMessage)value);
      } 
      if (this.followInfo_ != null) {
        this.bitField0_ |= 0x40;
        onChanged();
      } 
      return this;
    }
    
    public Builder clearFollowInfo() {
      this.bitField0_ &= 0xFFFFFFBF;
      this.followInfo_ = null;
      if (this.followInfoBuilder_ != null) {
        this.followInfoBuilder_.dispose();
        this.followInfoBuilder_ = null;
      } 
      onChanged();
      return this;
    }
    
    public FollowInfo.Builder getFollowInfoBuilder() {
      this.bitField0_ |= 0x40;
      onChanged();
      return (FollowInfo.Builder)getFollowInfoFieldBuilder().getBuilder();
    }
    
    public FollowInfoOrBuilder getFollowInfoOrBuilder() {
      if (this.followInfoBuilder_ != null)
        return (FollowInfoOrBuilder)this.followInfoBuilder_.getMessageOrBuilder(); 
      return (this.followInfo_ == null) ? FollowInfo.getDefaultInstance() : this.followInfo_;
    }
    
    private SingleFieldBuilderV3<FollowInfo, FollowInfo.Builder, FollowInfoOrBuilder> getFollowInfoFieldBuilder() {
      if (this.followInfoBuilder_ == null) {
        this.followInfoBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getFollowInfo(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.followInfo_ = null;
      } 
      return this.followInfoBuilder_;
    }
    
    public boolean hasPayGrade() {
      return ((this.bitField0_ & 0x80) != 0);
    }
    
    public PayGrade getPayGrade() {
      if (this.payGradeBuilder_ == null)
        return (this.payGrade_ == null) ? PayGrade.getDefaultInstance() : this.payGrade_; 
      return (PayGrade)this.payGradeBuilder_.getMessage();
    }
    
    public Builder setPayGrade(PayGrade value) {
      if (this.payGradeBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        this.payGrade_ = value;
      } else {
        this.payGradeBuilder_.setMessage((AbstractMessage)value);
      } 
      this.bitField0_ |= 0x80;
      onChanged();
      return this;
    }
    
    public Builder setPayGrade(PayGrade.Builder builderForValue) {
      if (this.payGradeBuilder_ == null) {
        this.payGrade_ = builderForValue.build();
      } else {
        this.payGradeBuilder_.setMessage((AbstractMessage)builderForValue.build());
      } 
      this.bitField0_ |= 0x80;
      onChanged();
      return this;
    }
    
    public Builder mergePayGrade(PayGrade value) {
      if (this.payGradeBuilder_ == null) {
        if ((this.bitField0_ & 0x80) != 0 && this.payGrade_ != null && this.payGrade_ != PayGrade.getDefaultInstance()) {
          getPayGradeBuilder().mergeFrom(value);
        } else {
          this.payGrade_ = value;
        } 
      } else {
        this.payGradeBuilder_.mergeFrom((AbstractMessage)value);
      } 
      if (this.payGrade_ != null) {
        this.bitField0_ |= 0x80;
        onChanged();
      } 
      return this;
    }
    
    public Builder clearPayGrade() {
      this.bitField0_ &= 0xFFFFFF7F;
      this.payGrade_ = null;
      if (this.payGradeBuilder_ != null) {
        this.payGradeBuilder_.dispose();
        this.payGradeBuilder_ = null;
      } 
      onChanged();
      return this;
    }
    
    public PayGrade.Builder getPayGradeBuilder() {
      this.bitField0_ |= 0x80;
      onChanged();
      return (PayGrade.Builder)getPayGradeFieldBuilder().getBuilder();
    }
    
    public PayGradeOrBuilder getPayGradeOrBuilder() {
      if (this.payGradeBuilder_ != null)
        return (PayGradeOrBuilder)this.payGradeBuilder_.getMessageOrBuilder(); 
      return (this.payGrade_ == null) ? PayGrade.getDefaultInstance() : this.payGrade_;
    }
    
    private SingleFieldBuilderV3<PayGrade, PayGrade.Builder, PayGradeOrBuilder> getPayGradeFieldBuilder() {
      if (this.payGradeBuilder_ == null) {
        this.payGradeBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getPayGrade(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.payGrade_ = null;
      } 
      return this.payGradeBuilder_;
    }
    
    public String getDisplayId() {
      Object ref = this.displayId_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.displayId_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getDisplayIdBytes() {
      Object ref = this.displayId_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.displayId_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setDisplayId(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.displayId_ = value;
      this.bitField0_ |= 0x100;
      onChanged();
      return this;
    }
    
    public Builder clearDisplayId() {
      this.displayId_ = User.getDefaultInstance().getDisplayId();
      this.bitField0_ &= 0xFFFFFEFF;
      onChanged();
      return this;
    }
    
    public Builder setDisplayIdBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      User.checkByteStringIsUtf8(value);
      this.displayId_ = value;
      this.bitField0_ |= 0x100;
      onChanged();
      return this;
    }
    
    public String getSecUid() {
      Object ref = this.secUid_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.secUid_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getSecUidBytes() {
      Object ref = this.secUid_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.secUid_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setSecUid(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.secUid_ = value;
      this.bitField0_ |= 0x200;
      onChanged();
      return this;
    }
    
    public Builder clearSecUid() {
      this.secUid_ = User.getDefaultInstance().getSecUid();
      this.bitField0_ &= 0xFFFFFDFF;
      onChanged();
      return this;
    }
    
    public Builder setSecUidBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      User.checkByteStringIsUtf8(value);
      this.secUid_ = value;
      this.bitField0_ |= 0x200;
      onChanged();
      return this;
    }
    
    public String getIdStr() {
      Object ref = this.idStr_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.idStr_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getIdStrBytes() {
      Object ref = this.idStr_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.idStr_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setIdStr(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.idStr_ = value;
      this.bitField0_ |= 0x400;
      onChanged();
      return this;
    }
    
    public Builder clearIdStr() {
      this.idStr_ = User.getDefaultInstance().getIdStr();
      this.bitField0_ &= 0xFFFFFBFF;
      onChanged();
      return this;
    }
    
    public Builder setIdStrBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      User.checkByteStringIsUtf8(value);
      this.idStr_ = value;
      this.bitField0_ |= 0x400;
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
  
  private static final User DEFAULT_INSTANCE = new User();
  
  public static User getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }
  
  private static final Parser<User> PARSER = (Parser<User>)new AbstractParser<User>() {
      public User parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        User.Builder builder = User.newBuilder();
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
  
  public static Parser<User> parser() {
    return PARSER;
  }
  
  public Parser<User> getParserForType() {
    return PARSER;
  }
  
  public User getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }
}
