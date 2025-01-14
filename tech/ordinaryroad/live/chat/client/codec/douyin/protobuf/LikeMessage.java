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

public final class LikeMessage extends GeneratedMessageV3 implements LikeMessageOrBuilder {
  private static final long serialVersionUID = 0L;
  
  private int bitField0_;
  
  public static final int COMMON_FIELD_NUMBER = 1;
  
  private Common common_;
  
  public static final int COUNT_FIELD_NUMBER = 2;
  
  private long count_;
  
  public static final int TOTAL_FIELD_NUMBER = 3;
  
  private long total_;
  
  public static final int COLOR_FIELD_NUMBER = 4;
  
  private long color_;
  
  public static final int USER_FIELD_NUMBER = 5;
  
  private User user_;
  
  public static final int ICON_FIELD_NUMBER = 6;
  
  private volatile Object icon_;
  
  public static final int DOUBLELIKEDETAIL_FIELD_NUMBER = 7;
  
  private DoubleLikeDetail doubleLikeDetail_;
  
  public static final int DISPLAYCONTROLINFO_FIELD_NUMBER = 8;
  
  private DisplayControlInfo displayControlInfo_;
  
  public static final int LINKMICGUESTUID_FIELD_NUMBER = 9;
  
  private long linkmicGuestUid_;
  
  public static final int SCENE_FIELD_NUMBER = 10;
  
  private volatile Object scene_;
  
  public static final int PICODISPLAYINFO_FIELD_NUMBER = 11;
  
  private PicoDisplayInfo picoDisplayInfo_;
  
  private byte memoizedIsInitialized;
  
  private LikeMessage(GeneratedMessageV3.Builder<?> builder) {
    super(builder);
    this.count_ = 0L;
    this.total_ = 0L;
    this.color_ = 0L;
    this.icon_ = "";
    this.linkmicGuestUid_ = 0L;
    this.scene_ = "";
    this.memoizedIsInitialized = -1;
  }
  
  private LikeMessage() {
    this.count_ = 0L;
    this.total_ = 0L;
    this.color_ = 0L;
    this.icon_ = "";
    this.linkmicGuestUid_ = 0L;
    this.scene_ = "";
    this.memoizedIsInitialized = -1;
    this.icon_ = "";
    this.scene_ = "";
  }
  
  protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
    return new LikeMessage();
  }
  
  public static final Descriptors.Descriptor getDescriptor() {
    return Douyin.internal_static_LikeMessage_descriptor;
  }
  
  protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
    return Douyin.internal_static_LikeMessage_fieldAccessorTable.ensureFieldAccessorsInitialized(LikeMessage.class, Builder.class);
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
  
  public long getCount() {
    return this.count_;
  }
  
  public long getTotal() {
    return this.total_;
  }
  
  public long getColor() {
    return this.color_;
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
  
  public String getIcon() {
    Object ref = this.icon_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.icon_ = s;
    return s;
  }
  
  public ByteString getIconBytes() {
    Object ref = this.icon_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.icon_ = b;
      return b;
    } 
    return (ByteString)ref;
  }
  
  public boolean hasDoubleLikeDetail() {
    return ((this.bitField0_ & 0x4) != 0);
  }
  
  public DoubleLikeDetail getDoubleLikeDetail() {
    return (this.doubleLikeDetail_ == null) ? DoubleLikeDetail.getDefaultInstance() : this.doubleLikeDetail_;
  }
  
  public DoubleLikeDetailOrBuilder getDoubleLikeDetailOrBuilder() {
    return (this.doubleLikeDetail_ == null) ? DoubleLikeDetail.getDefaultInstance() : this.doubleLikeDetail_;
  }
  
  public boolean hasDisplayControlInfo() {
    return ((this.bitField0_ & 0x8) != 0);
  }
  
  public DisplayControlInfo getDisplayControlInfo() {
    return (this.displayControlInfo_ == null) ? DisplayControlInfo.getDefaultInstance() : this.displayControlInfo_;
  }
  
  public DisplayControlInfoOrBuilder getDisplayControlInfoOrBuilder() {
    return (this.displayControlInfo_ == null) ? DisplayControlInfo.getDefaultInstance() : this.displayControlInfo_;
  }
  
  public long getLinkmicGuestUid() {
    return this.linkmicGuestUid_;
  }
  
  public String getScene() {
    Object ref = this.scene_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.scene_ = s;
    return s;
  }
  
  public ByteString getSceneBytes() {
    Object ref = this.scene_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.scene_ = b;
      return b;
    } 
    return (ByteString)ref;
  }
  
  public boolean hasPicoDisplayInfo() {
    return ((this.bitField0_ & 0x10) != 0);
  }
  
  public PicoDisplayInfo getPicoDisplayInfo() {
    return (this.picoDisplayInfo_ == null) ? PicoDisplayInfo.getDefaultInstance() : this.picoDisplayInfo_;
  }
  
  public PicoDisplayInfoOrBuilder getPicoDisplayInfoOrBuilder() {
    return (this.picoDisplayInfo_ == null) ? PicoDisplayInfo.getDefaultInstance() : this.picoDisplayInfo_;
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
    if (this.count_ != 0L)
      output.writeUInt64(2, this.count_); 
    if (this.total_ != 0L)
      output.writeUInt64(3, this.total_); 
    if (this.color_ != 0L)
      output.writeUInt64(4, this.color_); 
    if ((this.bitField0_ & 0x2) != 0)
      output.writeMessage(5, (MessageLite)getUser()); 
    if (!GeneratedMessageV3.isStringEmpty(this.icon_))
      GeneratedMessageV3.writeString(output, 6, this.icon_); 
    if ((this.bitField0_ & 0x4) != 0)
      output.writeMessage(7, (MessageLite)getDoubleLikeDetail()); 
    if ((this.bitField0_ & 0x8) != 0)
      output.writeMessage(8, (MessageLite)getDisplayControlInfo()); 
    if (this.linkmicGuestUid_ != 0L)
      output.writeUInt64(9, this.linkmicGuestUid_); 
    if (!GeneratedMessageV3.isStringEmpty(this.scene_))
      GeneratedMessageV3.writeString(output, 10, this.scene_); 
    if ((this.bitField0_ & 0x10) != 0)
      output.writeMessage(11, (MessageLite)getPicoDisplayInfo()); 
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
    if (this.count_ != 0L)
      size += 
        CodedOutputStream.computeUInt64Size(2, this.count_); 
    if (this.total_ != 0L)
      size += 
        CodedOutputStream.computeUInt64Size(3, this.total_); 
    if (this.color_ != 0L)
      size += 
        CodedOutputStream.computeUInt64Size(4, this.color_); 
    if ((this.bitField0_ & 0x2) != 0)
      size += 
        CodedOutputStream.computeMessageSize(5, (MessageLite)getUser()); 
    if (!GeneratedMessageV3.isStringEmpty(this.icon_))
      size += GeneratedMessageV3.computeStringSize(6, this.icon_); 
    if ((this.bitField0_ & 0x4) != 0)
      size += 
        CodedOutputStream.computeMessageSize(7, (MessageLite)getDoubleLikeDetail()); 
    if ((this.bitField0_ & 0x8) != 0)
      size += 
        CodedOutputStream.computeMessageSize(8, (MessageLite)getDisplayControlInfo()); 
    if (this.linkmicGuestUid_ != 0L)
      size += 
        CodedOutputStream.computeUInt64Size(9, this.linkmicGuestUid_); 
    if (!GeneratedMessageV3.isStringEmpty(this.scene_))
      size += GeneratedMessageV3.computeStringSize(10, this.scene_); 
    if ((this.bitField0_ & 0x10) != 0)
      size += 
        CodedOutputStream.computeMessageSize(11, (MessageLite)getPicoDisplayInfo()); 
    size += getUnknownFields().getSerializedSize();
    this.memoizedSize = size;
    return size;
  }
  
  public boolean equals(Object obj) {
    if (obj == this)
      return true; 
    if (!(obj instanceof LikeMessage))
      return super.equals(obj); 
    LikeMessage other = (LikeMessage)obj;
    if (hasCommon() != other.hasCommon())
      return false; 
    if (hasCommon() && 
      
      !getCommon().equals(other.getCommon()))
      return false; 
    if (getCount() != other
      .getCount())
      return false; 
    if (getTotal() != other
      .getTotal())
      return false; 
    if (getColor() != other
      .getColor())
      return false; 
    if (hasUser() != other.hasUser())
      return false; 
    if (hasUser() && 
      
      !getUser().equals(other.getUser()))
      return false; 
    if (!getIcon().equals(other.getIcon()))
      return false; 
    if (hasDoubleLikeDetail() != other.hasDoubleLikeDetail())
      return false; 
    if (hasDoubleLikeDetail() && 
      
      !getDoubleLikeDetail().equals(other.getDoubleLikeDetail()))
      return false; 
    if (hasDisplayControlInfo() != other.hasDisplayControlInfo())
      return false; 
    if (hasDisplayControlInfo() && 
      
      !getDisplayControlInfo().equals(other.getDisplayControlInfo()))
      return false; 
    if (getLinkmicGuestUid() != other
      .getLinkmicGuestUid())
      return false; 
    if (!getScene().equals(other.getScene()))
      return false; 
    if (hasPicoDisplayInfo() != other.hasPicoDisplayInfo())
      return false; 
    if (hasPicoDisplayInfo() && 
      
      !getPicoDisplayInfo().equals(other.getPicoDisplayInfo()))
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
        getCount());
    hash = 37 * hash + 3;
    hash = 53 * hash + Internal.hashLong(
        getTotal());
    hash = 37 * hash + 4;
    hash = 53 * hash + Internal.hashLong(
        getColor());
    if (hasUser()) {
      hash = 37 * hash + 5;
      hash = 53 * hash + getUser().hashCode();
    } 
    hash = 37 * hash + 6;
    hash = 53 * hash + getIcon().hashCode();
    if (hasDoubleLikeDetail()) {
      hash = 37 * hash + 7;
      hash = 53 * hash + getDoubleLikeDetail().hashCode();
    } 
    if (hasDisplayControlInfo()) {
      hash = 37 * hash + 8;
      hash = 53 * hash + getDisplayControlInfo().hashCode();
    } 
    hash = 37 * hash + 9;
    hash = 53 * hash + Internal.hashLong(
        getLinkmicGuestUid());
    hash = 37 * hash + 10;
    hash = 53 * hash + getScene().hashCode();
    if (hasPicoDisplayInfo()) {
      hash = 37 * hash + 11;
      hash = 53 * hash + getPicoDisplayInfo().hashCode();
    } 
    hash = 29 * hash + getUnknownFields().hashCode();
    this.memoizedHashCode = hash;
    return hash;
  }
  
  public static LikeMessage parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
    return (LikeMessage)PARSER.parseFrom(data);
  }
  
  public static LikeMessage parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (LikeMessage)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static LikeMessage parseFrom(ByteString data) throws InvalidProtocolBufferException {
    return (LikeMessage)PARSER.parseFrom(data);
  }
  
  public static LikeMessage parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (LikeMessage)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static LikeMessage parseFrom(byte[] data) throws InvalidProtocolBufferException {
    return (LikeMessage)PARSER.parseFrom(data);
  }
  
  public static LikeMessage parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (LikeMessage)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static LikeMessage parseFrom(InputStream input) throws IOException {
    return 
      (LikeMessage)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static LikeMessage parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (LikeMessage)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static LikeMessage parseDelimitedFrom(InputStream input) throws IOException {
    return 
      (LikeMessage)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
  }
  
  public static LikeMessage parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (LikeMessage)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static LikeMessage parseFrom(CodedInputStream input) throws IOException {
    return 
      (LikeMessage)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static LikeMessage parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (LikeMessage)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public Builder newBuilderForType() {
    return newBuilder();
  }
  
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  
  public static Builder newBuilder(LikeMessage prototype) {
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
  
  public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements LikeMessageOrBuilder {
    private int bitField0_;
    
    private Common common_;
    
    private SingleFieldBuilderV3<Common, Common.Builder, CommonOrBuilder> commonBuilder_;
    
    private long count_;
    
    private long total_;
    
    private long color_;
    
    private User user_;
    
    private SingleFieldBuilderV3<User, User.Builder, UserOrBuilder> userBuilder_;
    
    private Object icon_;
    
    private DoubleLikeDetail doubleLikeDetail_;
    
    private SingleFieldBuilderV3<DoubleLikeDetail, DoubleLikeDetail.Builder, DoubleLikeDetailOrBuilder> doubleLikeDetailBuilder_;
    
    private DisplayControlInfo displayControlInfo_;
    
    private SingleFieldBuilderV3<DisplayControlInfo, DisplayControlInfo.Builder, DisplayControlInfoOrBuilder> displayControlInfoBuilder_;
    
    private long linkmicGuestUid_;
    
    private Object scene_;
    
    private PicoDisplayInfo picoDisplayInfo_;
    
    private SingleFieldBuilderV3<PicoDisplayInfo, PicoDisplayInfo.Builder, PicoDisplayInfoOrBuilder> picoDisplayInfoBuilder_;
    
    public static final Descriptors.Descriptor getDescriptor() {
      return Douyin.internal_static_LikeMessage_descriptor;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return Douyin.internal_static_LikeMessage_fieldAccessorTable
        .ensureFieldAccessorsInitialized(LikeMessage.class, Builder.class);
    }
    
    private Builder() {
      this.icon_ = "";
      this.scene_ = "";
      maybeForceBuilderInitialization();
    }
    
    private Builder(GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      this.icon_ = "";
      this.scene_ = "";
      maybeForceBuilderInitialization();
    }
    
    private void maybeForceBuilderInitialization() {
      if (LikeMessage.alwaysUseFieldBuilders) {
        getCommonFieldBuilder();
        getUserFieldBuilder();
        getDoubleLikeDetailFieldBuilder();
        getDisplayControlInfoFieldBuilder();
        getPicoDisplayInfoFieldBuilder();
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
      this.count_ = 0L;
      this.total_ = 0L;
      this.color_ = 0L;
      this.user_ = null;
      if (this.userBuilder_ != null) {
        this.userBuilder_.dispose();
        this.userBuilder_ = null;
      } 
      this.icon_ = "";
      this.doubleLikeDetail_ = null;
      if (this.doubleLikeDetailBuilder_ != null) {
        this.doubleLikeDetailBuilder_.dispose();
        this.doubleLikeDetailBuilder_ = null;
      } 
      this.displayControlInfo_ = null;
      if (this.displayControlInfoBuilder_ != null) {
        this.displayControlInfoBuilder_.dispose();
        this.displayControlInfoBuilder_ = null;
      } 
      this.linkmicGuestUid_ = 0L;
      this.scene_ = "";
      this.picoDisplayInfo_ = null;
      if (this.picoDisplayInfoBuilder_ != null) {
        this.picoDisplayInfoBuilder_.dispose();
        this.picoDisplayInfoBuilder_ = null;
      } 
      return this;
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
      return Douyin.internal_static_LikeMessage_descriptor;
    }
    
    public LikeMessage getDefaultInstanceForType() {
      return LikeMessage.getDefaultInstance();
    }
    
    public LikeMessage build() {
      LikeMessage result = buildPartial();
      if (!result.isInitialized())
        throw newUninitializedMessageException(result); 
      return result;
    }
    
    public LikeMessage buildPartial() {
      LikeMessage result = new LikeMessage(this);
      if (this.bitField0_ != 0)
        buildPartial0(result); 
      onBuilt();
      return result;
    }
    
    private void buildPartial0(LikeMessage result) {
      int from_bitField0_ = this.bitField0_;
      int to_bitField0_ = 0;
      if ((from_bitField0_ & 0x1) != 0) {
        result.common_ = (this.commonBuilder_ == null) ? this.common_ : (Common)this.commonBuilder_.build();
        to_bitField0_ |= 0x1;
      } 
      if ((from_bitField0_ & 0x2) != 0)
        result.count_ = this.count_; 
      if ((from_bitField0_ & 0x4) != 0)
        result.total_ = this.total_; 
      if ((from_bitField0_ & 0x8) != 0)
        result.color_ = this.color_; 
      if ((from_bitField0_ & 0x10) != 0) {
        result.user_ = (this.userBuilder_ == null) ? this.user_ : (User)this.userBuilder_.build();
        to_bitField0_ |= 0x2;
      } 
      if ((from_bitField0_ & 0x20) != 0)
        result.icon_ = this.icon_; 
      if ((from_bitField0_ & 0x40) != 0) {
        result.doubleLikeDetail_ = (this.doubleLikeDetailBuilder_ == null) ? this.doubleLikeDetail_ : (DoubleLikeDetail)this.doubleLikeDetailBuilder_.build();
        to_bitField0_ |= 0x4;
      } 
      if ((from_bitField0_ & 0x80) != 0) {
        result.displayControlInfo_ = (this.displayControlInfoBuilder_ == null) ? this.displayControlInfo_ : (DisplayControlInfo)this.displayControlInfoBuilder_.build();
        to_bitField0_ |= 0x8;
      } 
      if ((from_bitField0_ & 0x100) != 0)
        result.linkmicGuestUid_ = this.linkmicGuestUid_; 
      if ((from_bitField0_ & 0x200) != 0)
        result.scene_ = this.scene_; 
      if ((from_bitField0_ & 0x400) != 0) {
        result.picoDisplayInfo_ = (this.picoDisplayInfoBuilder_ == null) ? this.picoDisplayInfo_ : (PicoDisplayInfo)this.picoDisplayInfoBuilder_.build();
        to_bitField0_ |= 0x10;
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
      if (other instanceof LikeMessage)
        return mergeFrom((LikeMessage)other); 
      super.mergeFrom(other);
      return this;
    }
    
    public Builder mergeFrom(LikeMessage other) {
      if (other == LikeMessage.getDefaultInstance())
        return this; 
      if (other.hasCommon())
        mergeCommon(other.getCommon()); 
      if (other.getCount() != 0L)
        setCount(other.getCount()); 
      if (other.getTotal() != 0L)
        setTotal(other.getTotal()); 
      if (other.getColor() != 0L)
        setColor(other.getColor()); 
      if (other.hasUser())
        mergeUser(other.getUser()); 
      if (!other.getIcon().isEmpty()) {
        this.icon_ = other.icon_;
        this.bitField0_ |= 0x20;
        onChanged();
      } 
      if (other.hasDoubleLikeDetail())
        mergeDoubleLikeDetail(other.getDoubleLikeDetail()); 
      if (other.hasDisplayControlInfo())
        mergeDisplayControlInfo(other.getDisplayControlInfo()); 
      if (other.getLinkmicGuestUid() != 0L)
        setLinkmicGuestUid(other.getLinkmicGuestUid()); 
      if (!other.getScene().isEmpty()) {
        this.scene_ = other.scene_;
        this.bitField0_ |= 0x200;
        onChanged();
      } 
      if (other.hasPicoDisplayInfo())
        mergePicoDisplayInfo(other.getPicoDisplayInfo()); 
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
              this.count_ = input.readUInt64();
              this.bitField0_ |= 0x2;
              continue;
            case 24:
              this.total_ = input.readUInt64();
              this.bitField0_ |= 0x4;
              continue;
            case 32:
              this.color_ = input.readUInt64();
              this.bitField0_ |= 0x8;
              continue;
            case 42:
              input.readMessage((MessageLite.Builder)getUserFieldBuilder().getBuilder(), extensionRegistry);
              this.bitField0_ |= 0x10;
              continue;
            case 50:
              this.icon_ = input.readStringRequireUtf8();
              this.bitField0_ |= 0x20;
              continue;
            case 58:
              input.readMessage((MessageLite.Builder)getDoubleLikeDetailFieldBuilder().getBuilder(), extensionRegistry);
              this.bitField0_ |= 0x40;
              continue;
            case 66:
              input.readMessage((MessageLite.Builder)getDisplayControlInfoFieldBuilder().getBuilder(), extensionRegistry);
              this.bitField0_ |= 0x80;
              continue;
            case 72:
              this.linkmicGuestUid_ = input.readUInt64();
              this.bitField0_ |= 0x100;
              continue;
            case 82:
              this.scene_ = input.readStringRequireUtf8();
              this.bitField0_ |= 0x200;
              continue;
            case 90:
              input.readMessage((MessageLite.Builder)getPicoDisplayInfoFieldBuilder().getBuilder(), extensionRegistry);
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
    
    public long getCount() {
      return this.count_;
    }
    
    public Builder setCount(long value) {
      this.count_ = value;
      this.bitField0_ |= 0x2;
      onChanged();
      return this;
    }
    
    public Builder clearCount() {
      this.bitField0_ &= 0xFFFFFFFD;
      this.count_ = 0L;
      onChanged();
      return this;
    }
    
    public long getTotal() {
      return this.total_;
    }
    
    public Builder setTotal(long value) {
      this.total_ = value;
      this.bitField0_ |= 0x4;
      onChanged();
      return this;
    }
    
    public Builder clearTotal() {
      this.bitField0_ &= 0xFFFFFFFB;
      this.total_ = 0L;
      onChanged();
      return this;
    }
    
    public long getColor() {
      return this.color_;
    }
    
    public Builder setColor(long value) {
      this.color_ = value;
      this.bitField0_ |= 0x8;
      onChanged();
      return this;
    }
    
    public Builder clearColor() {
      this.bitField0_ &= 0xFFFFFFF7;
      this.color_ = 0L;
      onChanged();
      return this;
    }
    
    public boolean hasUser() {
      return ((this.bitField0_ & 0x10) != 0);
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
      this.bitField0_ |= 0x10;
      onChanged();
      return this;
    }
    
    public Builder setUser(User.Builder builderForValue) {
      if (this.userBuilder_ == null) {
        this.user_ = builderForValue.build();
      } else {
        this.userBuilder_.setMessage((AbstractMessage)builderForValue.build());
      } 
      this.bitField0_ |= 0x10;
      onChanged();
      return this;
    }
    
    public Builder mergeUser(User value) {
      if (this.userBuilder_ == null) {
        if ((this.bitField0_ & 0x10) != 0 && this.user_ != null && this.user_ != User.getDefaultInstance()) {
          getUserBuilder().mergeFrom(value);
        } else {
          this.user_ = value;
        } 
      } else {
        this.userBuilder_.mergeFrom((AbstractMessage)value);
      } 
      if (this.user_ != null) {
        this.bitField0_ |= 0x10;
        onChanged();
      } 
      return this;
    }
    
    public Builder clearUser() {
      this.bitField0_ &= 0xFFFFFFEF;
      this.user_ = null;
      if (this.userBuilder_ != null) {
        this.userBuilder_.dispose();
        this.userBuilder_ = null;
      } 
      onChanged();
      return this;
    }
    
    public User.Builder getUserBuilder() {
      this.bitField0_ |= 0x10;
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
    
    public String getIcon() {
      Object ref = this.icon_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.icon_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getIconBytes() {
      Object ref = this.icon_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.icon_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setIcon(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.icon_ = value;
      this.bitField0_ |= 0x20;
      onChanged();
      return this;
    }
    
    public Builder clearIcon() {
      this.icon_ = LikeMessage.getDefaultInstance().getIcon();
      this.bitField0_ &= 0xFFFFFFDF;
      onChanged();
      return this;
    }
    
    public Builder setIconBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      LikeMessage.checkByteStringIsUtf8(value);
      this.icon_ = value;
      this.bitField0_ |= 0x20;
      onChanged();
      return this;
    }
    
    public boolean hasDoubleLikeDetail() {
      return ((this.bitField0_ & 0x40) != 0);
    }
    
    public DoubleLikeDetail getDoubleLikeDetail() {
      if (this.doubleLikeDetailBuilder_ == null)
        return (this.doubleLikeDetail_ == null) ? DoubleLikeDetail.getDefaultInstance() : this.doubleLikeDetail_; 
      return (DoubleLikeDetail)this.doubleLikeDetailBuilder_.getMessage();
    }
    
    public Builder setDoubleLikeDetail(DoubleLikeDetail value) {
      if (this.doubleLikeDetailBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        this.doubleLikeDetail_ = value;
      } else {
        this.doubleLikeDetailBuilder_.setMessage((AbstractMessage)value);
      } 
      this.bitField0_ |= 0x40;
      onChanged();
      return this;
    }
    
    public Builder setDoubleLikeDetail(DoubleLikeDetail.Builder builderForValue) {
      if (this.doubleLikeDetailBuilder_ == null) {
        this.doubleLikeDetail_ = builderForValue.build();
      } else {
        this.doubleLikeDetailBuilder_.setMessage((AbstractMessage)builderForValue.build());
      } 
      this.bitField0_ |= 0x40;
      onChanged();
      return this;
    }
    
    public Builder mergeDoubleLikeDetail(DoubleLikeDetail value) {
      if (this.doubleLikeDetailBuilder_ == null) {
        if ((this.bitField0_ & 0x40) != 0 && this.doubleLikeDetail_ != null && this.doubleLikeDetail_ != DoubleLikeDetail.getDefaultInstance()) {
          getDoubleLikeDetailBuilder().mergeFrom(value);
        } else {
          this.doubleLikeDetail_ = value;
        } 
      } else {
        this.doubleLikeDetailBuilder_.mergeFrom((AbstractMessage)value);
      } 
      if (this.doubleLikeDetail_ != null) {
        this.bitField0_ |= 0x40;
        onChanged();
      } 
      return this;
    }
    
    public Builder clearDoubleLikeDetail() {
      this.bitField0_ &= 0xFFFFFFBF;
      this.doubleLikeDetail_ = null;
      if (this.doubleLikeDetailBuilder_ != null) {
        this.doubleLikeDetailBuilder_.dispose();
        this.doubleLikeDetailBuilder_ = null;
      } 
      onChanged();
      return this;
    }
    
    public DoubleLikeDetail.Builder getDoubleLikeDetailBuilder() {
      this.bitField0_ |= 0x40;
      onChanged();
      return (DoubleLikeDetail.Builder)getDoubleLikeDetailFieldBuilder().getBuilder();
    }
    
    public DoubleLikeDetailOrBuilder getDoubleLikeDetailOrBuilder() {
      if (this.doubleLikeDetailBuilder_ != null)
        return (DoubleLikeDetailOrBuilder)this.doubleLikeDetailBuilder_.getMessageOrBuilder(); 
      return (this.doubleLikeDetail_ == null) ? DoubleLikeDetail.getDefaultInstance() : this.doubleLikeDetail_;
    }
    
    private SingleFieldBuilderV3<DoubleLikeDetail, DoubleLikeDetail.Builder, DoubleLikeDetailOrBuilder> getDoubleLikeDetailFieldBuilder() {
      if (this.doubleLikeDetailBuilder_ == null) {
        this.doubleLikeDetailBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getDoubleLikeDetail(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.doubleLikeDetail_ = null;
      } 
      return this.doubleLikeDetailBuilder_;
    }
    
    public boolean hasDisplayControlInfo() {
      return ((this.bitField0_ & 0x80) != 0);
    }
    
    public DisplayControlInfo getDisplayControlInfo() {
      if (this.displayControlInfoBuilder_ == null)
        return (this.displayControlInfo_ == null) ? DisplayControlInfo.getDefaultInstance() : this.displayControlInfo_; 
      return (DisplayControlInfo)this.displayControlInfoBuilder_.getMessage();
    }
    
    public Builder setDisplayControlInfo(DisplayControlInfo value) {
      if (this.displayControlInfoBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        this.displayControlInfo_ = value;
      } else {
        this.displayControlInfoBuilder_.setMessage((AbstractMessage)value);
      } 
      this.bitField0_ |= 0x80;
      onChanged();
      return this;
    }
    
    public Builder setDisplayControlInfo(DisplayControlInfo.Builder builderForValue) {
      if (this.displayControlInfoBuilder_ == null) {
        this.displayControlInfo_ = builderForValue.build();
      } else {
        this.displayControlInfoBuilder_.setMessage((AbstractMessage)builderForValue.build());
      } 
      this.bitField0_ |= 0x80;
      onChanged();
      return this;
    }
    
    public Builder mergeDisplayControlInfo(DisplayControlInfo value) {
      if (this.displayControlInfoBuilder_ == null) {
        if ((this.bitField0_ & 0x80) != 0 && this.displayControlInfo_ != null && this.displayControlInfo_ != DisplayControlInfo.getDefaultInstance()) {
          getDisplayControlInfoBuilder().mergeFrom(value);
        } else {
          this.displayControlInfo_ = value;
        } 
      } else {
        this.displayControlInfoBuilder_.mergeFrom((AbstractMessage)value);
      } 
      if (this.displayControlInfo_ != null) {
        this.bitField0_ |= 0x80;
        onChanged();
      } 
      return this;
    }
    
    public Builder clearDisplayControlInfo() {
      this.bitField0_ &= 0xFFFFFF7F;
      this.displayControlInfo_ = null;
      if (this.displayControlInfoBuilder_ != null) {
        this.displayControlInfoBuilder_.dispose();
        this.displayControlInfoBuilder_ = null;
      } 
      onChanged();
      return this;
    }
    
    public DisplayControlInfo.Builder getDisplayControlInfoBuilder() {
      this.bitField0_ |= 0x80;
      onChanged();
      return (DisplayControlInfo.Builder)getDisplayControlInfoFieldBuilder().getBuilder();
    }
    
    public DisplayControlInfoOrBuilder getDisplayControlInfoOrBuilder() {
      if (this.displayControlInfoBuilder_ != null)
        return (DisplayControlInfoOrBuilder)this.displayControlInfoBuilder_.getMessageOrBuilder(); 
      return (this.displayControlInfo_ == null) ? DisplayControlInfo.getDefaultInstance() : this.displayControlInfo_;
    }
    
    private SingleFieldBuilderV3<DisplayControlInfo, DisplayControlInfo.Builder, DisplayControlInfoOrBuilder> getDisplayControlInfoFieldBuilder() {
      if (this.displayControlInfoBuilder_ == null) {
        this.displayControlInfoBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getDisplayControlInfo(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.displayControlInfo_ = null;
      } 
      return this.displayControlInfoBuilder_;
    }
    
    public long getLinkmicGuestUid() {
      return this.linkmicGuestUid_;
    }
    
    public Builder setLinkmicGuestUid(long value) {
      this.linkmicGuestUid_ = value;
      this.bitField0_ |= 0x100;
      onChanged();
      return this;
    }
    
    public Builder clearLinkmicGuestUid() {
      this.bitField0_ &= 0xFFFFFEFF;
      this.linkmicGuestUid_ = 0L;
      onChanged();
      return this;
    }
    
    public String getScene() {
      Object ref = this.scene_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.scene_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getSceneBytes() {
      Object ref = this.scene_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.scene_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setScene(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.scene_ = value;
      this.bitField0_ |= 0x200;
      onChanged();
      return this;
    }
    
    public Builder clearScene() {
      this.scene_ = LikeMessage.getDefaultInstance().getScene();
      this.bitField0_ &= 0xFFFFFDFF;
      onChanged();
      return this;
    }
    
    public Builder setSceneBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      LikeMessage.checkByteStringIsUtf8(value);
      this.scene_ = value;
      this.bitField0_ |= 0x200;
      onChanged();
      return this;
    }
    
    public boolean hasPicoDisplayInfo() {
      return ((this.bitField0_ & 0x400) != 0);
    }
    
    public PicoDisplayInfo getPicoDisplayInfo() {
      if (this.picoDisplayInfoBuilder_ == null)
        return (this.picoDisplayInfo_ == null) ? PicoDisplayInfo.getDefaultInstance() : this.picoDisplayInfo_; 
      return (PicoDisplayInfo)this.picoDisplayInfoBuilder_.getMessage();
    }
    
    public Builder setPicoDisplayInfo(PicoDisplayInfo value) {
      if (this.picoDisplayInfoBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        this.picoDisplayInfo_ = value;
      } else {
        this.picoDisplayInfoBuilder_.setMessage((AbstractMessage)value);
      } 
      this.bitField0_ |= 0x400;
      onChanged();
      return this;
    }
    
    public Builder setPicoDisplayInfo(PicoDisplayInfo.Builder builderForValue) {
      if (this.picoDisplayInfoBuilder_ == null) {
        this.picoDisplayInfo_ = builderForValue.build();
      } else {
        this.picoDisplayInfoBuilder_.setMessage((AbstractMessage)builderForValue.build());
      } 
      this.bitField0_ |= 0x400;
      onChanged();
      return this;
    }
    
    public Builder mergePicoDisplayInfo(PicoDisplayInfo value) {
      if (this.picoDisplayInfoBuilder_ == null) {
        if ((this.bitField0_ & 0x400) != 0 && this.picoDisplayInfo_ != null && this.picoDisplayInfo_ != 
          
          PicoDisplayInfo.getDefaultInstance()) {
          getPicoDisplayInfoBuilder().mergeFrom(value);
        } else {
          this.picoDisplayInfo_ = value;
        } 
      } else {
        this.picoDisplayInfoBuilder_.mergeFrom((AbstractMessage)value);
      } 
      if (this.picoDisplayInfo_ != null) {
        this.bitField0_ |= 0x400;
        onChanged();
      } 
      return this;
    }
    
    public Builder clearPicoDisplayInfo() {
      this.bitField0_ &= 0xFFFFFBFF;
      this.picoDisplayInfo_ = null;
      if (this.picoDisplayInfoBuilder_ != null) {
        this.picoDisplayInfoBuilder_.dispose();
        this.picoDisplayInfoBuilder_ = null;
      } 
      onChanged();
      return this;
    }
    
    public PicoDisplayInfo.Builder getPicoDisplayInfoBuilder() {
      this.bitField0_ |= 0x400;
      onChanged();
      return (PicoDisplayInfo.Builder)getPicoDisplayInfoFieldBuilder().getBuilder();
    }
    
    public PicoDisplayInfoOrBuilder getPicoDisplayInfoOrBuilder() {
      if (this.picoDisplayInfoBuilder_ != null)
        return (PicoDisplayInfoOrBuilder)this.picoDisplayInfoBuilder_.getMessageOrBuilder(); 
      return (this.picoDisplayInfo_ == null) ? 
        PicoDisplayInfo.getDefaultInstance() : this.picoDisplayInfo_;
    }
    
    private SingleFieldBuilderV3<PicoDisplayInfo, PicoDisplayInfo.Builder, PicoDisplayInfoOrBuilder> getPicoDisplayInfoFieldBuilder() {
      if (this.picoDisplayInfoBuilder_ == null) {
        this
          
          .picoDisplayInfoBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getPicoDisplayInfo(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.picoDisplayInfo_ = null;
      } 
      return this.picoDisplayInfoBuilder_;
    }
    
    public final Builder setUnknownFields(UnknownFieldSet unknownFields) {
      return (Builder)super.setUnknownFields(unknownFields);
    }
    
    public final Builder mergeUnknownFields(UnknownFieldSet unknownFields) {
      return (Builder)super.mergeUnknownFields(unknownFields);
    }
  }
  
  private static final LikeMessage DEFAULT_INSTANCE = new LikeMessage();
  
  public static LikeMessage getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }
  
  private static final Parser<LikeMessage> PARSER = (Parser<LikeMessage>)new AbstractParser<LikeMessage>() {
      public LikeMessage parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        LikeMessage.Builder builder = LikeMessage.newBuilder();
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
  
  public static Parser<LikeMessage> parser() {
    return PARSER;
  }
  
  public Parser<LikeMessage> getParserForType() {
    return PARSER;
  }
  
  public LikeMessage getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }
}
