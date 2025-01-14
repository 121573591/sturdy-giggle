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

public final class EmojiChatMessage extends GeneratedMessageV3 implements EmojiChatMessageOrBuilder {
  private static final long serialVersionUID = 0L;
  
  private int bitField0_;
  
  public static final int COMMON_FIELD_NUMBER = 1;
  
  private Common common_;
  
  public static final int USER_FIELD_NUMBER = 2;
  
  private User user_;
  
  public static final int EMOJIID_FIELD_NUMBER = 3;
  
  private long emojiId_;
  
  public static final int EMOJICONTENT_FIELD_NUMBER = 4;
  
  private Text emojiContent_;
  
  public static final int DEFAULTCONTENT_FIELD_NUMBER = 5;
  
  private volatile Object defaultContent_;
  
  public static final int BACKGROUNDIMAGE_FIELD_NUMBER = 6;
  
  private Image backgroundImage_;
  
  public static final int FROMINTERCOM_FIELD_NUMBER = 7;
  
  private boolean fromIntercom_;
  
  public static final int INTERCOMHIDEUSERCARD_FIELD_NUMBER = 8;
  
  private boolean intercomHideUserCard_;
  
  public static final int PUBLIC_AREA_COMMON_FIELD_NUMBER = 9;
  
  private PublicAreaCommon publicAreaCommon_;
  
  private byte memoizedIsInitialized;
  
  private EmojiChatMessage(GeneratedMessageV3.Builder<?> builder) {
    super(builder);
    this.emojiId_ = 0L;
    this.defaultContent_ = "";
    this.fromIntercom_ = false;
    this.intercomHideUserCard_ = false;
    this.memoizedIsInitialized = -1;
  }
  
  private EmojiChatMessage() {
    this.emojiId_ = 0L;
    this.defaultContent_ = "";
    this.fromIntercom_ = false;
    this.intercomHideUserCard_ = false;
    this.memoizedIsInitialized = -1;
    this.defaultContent_ = "";
  }
  
  protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
    return new EmojiChatMessage();
  }
  
  public static final Descriptors.Descriptor getDescriptor() {
    return Tiktok.internal_static_EmojiChatMessage_descriptor;
  }
  
  protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
    return Tiktok.internal_static_EmojiChatMessage_fieldAccessorTable.ensureFieldAccessorsInitialized(EmojiChatMessage.class, Builder.class);
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
  
  public long getEmojiId() {
    return this.emojiId_;
  }
  
  public boolean hasEmojiContent() {
    return ((this.bitField0_ & 0x4) != 0);
  }
  
  public Text getEmojiContent() {
    return (this.emojiContent_ == null) ? Text.getDefaultInstance() : this.emojiContent_;
  }
  
  public TextOrBuilder getEmojiContentOrBuilder() {
    return (this.emojiContent_ == null) ? Text.getDefaultInstance() : this.emojiContent_;
  }
  
  public String getDefaultContent() {
    Object ref = this.defaultContent_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.defaultContent_ = s;
    return s;
  }
  
  public ByteString getDefaultContentBytes() {
    Object ref = this.defaultContent_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.defaultContent_ = b;
      return b;
    } 
    return (ByteString)ref;
  }
  
  public boolean hasBackgroundImage() {
    return ((this.bitField0_ & 0x8) != 0);
  }
  
  public Image getBackgroundImage() {
    return (this.backgroundImage_ == null) ? Image.getDefaultInstance() : this.backgroundImage_;
  }
  
  public ImageOrBuilder getBackgroundImageOrBuilder() {
    return (this.backgroundImage_ == null) ? Image.getDefaultInstance() : this.backgroundImage_;
  }
  
  public boolean getFromIntercom() {
    return this.fromIntercom_;
  }
  
  public boolean getIntercomHideUserCard() {
    return this.intercomHideUserCard_;
  }
  
  public boolean hasPublicAreaCommon() {
    return ((this.bitField0_ & 0x10) != 0);
  }
  
  public PublicAreaCommon getPublicAreaCommon() {
    return (this.publicAreaCommon_ == null) ? PublicAreaCommon.getDefaultInstance() : this.publicAreaCommon_;
  }
  
  public PublicAreaCommonOrBuilder getPublicAreaCommonOrBuilder() {
    return (this.publicAreaCommon_ == null) ? PublicAreaCommon.getDefaultInstance() : this.publicAreaCommon_;
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
    if (this.emojiId_ != 0L)
      output.writeUInt64(3, this.emojiId_); 
    if ((this.bitField0_ & 0x4) != 0)
      output.writeMessage(4, (MessageLite)getEmojiContent()); 
    if (!GeneratedMessageV3.isStringEmpty(this.defaultContent_))
      GeneratedMessageV3.writeString(output, 5, this.defaultContent_); 
    if ((this.bitField0_ & 0x8) != 0)
      output.writeMessage(6, (MessageLite)getBackgroundImage()); 
    if (this.fromIntercom_)
      output.writeBool(7, this.fromIntercom_); 
    if (this.intercomHideUserCard_)
      output.writeBool(8, this.intercomHideUserCard_); 
    if ((this.bitField0_ & 0x10) != 0)
      output.writeMessage(9, (MessageLite)getPublicAreaCommon()); 
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
    if (this.emojiId_ != 0L)
      size += 
        CodedOutputStream.computeUInt64Size(3, this.emojiId_); 
    if ((this.bitField0_ & 0x4) != 0)
      size += 
        CodedOutputStream.computeMessageSize(4, (MessageLite)getEmojiContent()); 
    if (!GeneratedMessageV3.isStringEmpty(this.defaultContent_))
      size += GeneratedMessageV3.computeStringSize(5, this.defaultContent_); 
    if ((this.bitField0_ & 0x8) != 0)
      size += 
        CodedOutputStream.computeMessageSize(6, (MessageLite)getBackgroundImage()); 
    if (this.fromIntercom_)
      size += 
        CodedOutputStream.computeBoolSize(7, this.fromIntercom_); 
    if (this.intercomHideUserCard_)
      size += 
        CodedOutputStream.computeBoolSize(8, this.intercomHideUserCard_); 
    if ((this.bitField0_ & 0x10) != 0)
      size += 
        CodedOutputStream.computeMessageSize(9, (MessageLite)getPublicAreaCommon()); 
    size += getUnknownFields().getSerializedSize();
    this.memoizedSize = size;
    return size;
  }
  
  public boolean equals(Object obj) {
    if (obj == this)
      return true; 
    if (!(obj instanceof EmojiChatMessage))
      return super.equals(obj); 
    EmojiChatMessage other = (EmojiChatMessage)obj;
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
    if (getEmojiId() != other
      .getEmojiId())
      return false; 
    if (hasEmojiContent() != other.hasEmojiContent())
      return false; 
    if (hasEmojiContent() && 
      
      !getEmojiContent().equals(other.getEmojiContent()))
      return false; 
    if (!getDefaultContent().equals(other.getDefaultContent()))
      return false; 
    if (hasBackgroundImage() != other.hasBackgroundImage())
      return false; 
    if (hasBackgroundImage() && 
      
      !getBackgroundImage().equals(other.getBackgroundImage()))
      return false; 
    if (getFromIntercom() != other
      .getFromIntercom())
      return false; 
    if (getIntercomHideUserCard() != other
      .getIntercomHideUserCard())
      return false; 
    if (hasPublicAreaCommon() != other.hasPublicAreaCommon())
      return false; 
    if (hasPublicAreaCommon() && 
      
      !getPublicAreaCommon().equals(other.getPublicAreaCommon()))
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
        getEmojiId());
    if (hasEmojiContent()) {
      hash = 37 * hash + 4;
      hash = 53 * hash + getEmojiContent().hashCode();
    } 
    hash = 37 * hash + 5;
    hash = 53 * hash + getDefaultContent().hashCode();
    if (hasBackgroundImage()) {
      hash = 37 * hash + 6;
      hash = 53 * hash + getBackgroundImage().hashCode();
    } 
    hash = 37 * hash + 7;
    hash = 53 * hash + Internal.hashBoolean(
        getFromIntercom());
    hash = 37 * hash + 8;
    hash = 53 * hash + Internal.hashBoolean(
        getIntercomHideUserCard());
    if (hasPublicAreaCommon()) {
      hash = 37 * hash + 9;
      hash = 53 * hash + getPublicAreaCommon().hashCode();
    } 
    hash = 29 * hash + getUnknownFields().hashCode();
    this.memoizedHashCode = hash;
    return hash;
  }
  
  public static EmojiChatMessage parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
    return (EmojiChatMessage)PARSER.parseFrom(data);
  }
  
  public static EmojiChatMessage parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (EmojiChatMessage)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static EmojiChatMessage parseFrom(ByteString data) throws InvalidProtocolBufferException {
    return (EmojiChatMessage)PARSER.parseFrom(data);
  }
  
  public static EmojiChatMessage parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (EmojiChatMessage)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static EmojiChatMessage parseFrom(byte[] data) throws InvalidProtocolBufferException {
    return (EmojiChatMessage)PARSER.parseFrom(data);
  }
  
  public static EmojiChatMessage parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (EmojiChatMessage)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static EmojiChatMessage parseFrom(InputStream input) throws IOException {
    return 
      (EmojiChatMessage)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static EmojiChatMessage parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (EmojiChatMessage)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static EmojiChatMessage parseDelimitedFrom(InputStream input) throws IOException {
    return 
      (EmojiChatMessage)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
  }
  
  public static EmojiChatMessage parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (EmojiChatMessage)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static EmojiChatMessage parseFrom(CodedInputStream input) throws IOException {
    return 
      (EmojiChatMessage)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static EmojiChatMessage parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (EmojiChatMessage)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public Builder newBuilderForType() {
    return newBuilder();
  }
  
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  
  public static Builder newBuilder(EmojiChatMessage prototype) {
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
  
  public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements EmojiChatMessageOrBuilder {
    private int bitField0_;
    
    private Common common_;
    
    private SingleFieldBuilderV3<Common, Common.Builder, CommonOrBuilder> commonBuilder_;
    
    private User user_;
    
    private SingleFieldBuilderV3<User, User.Builder, UserOrBuilder> userBuilder_;
    
    private long emojiId_;
    
    private Text emojiContent_;
    
    private SingleFieldBuilderV3<Text, Text.Builder, TextOrBuilder> emojiContentBuilder_;
    
    private Object defaultContent_;
    
    private Image backgroundImage_;
    
    private SingleFieldBuilderV3<Image, Image.Builder, ImageOrBuilder> backgroundImageBuilder_;
    
    private boolean fromIntercom_;
    
    private boolean intercomHideUserCard_;
    
    private PublicAreaCommon publicAreaCommon_;
    
    private SingleFieldBuilderV3<PublicAreaCommon, PublicAreaCommon.Builder, PublicAreaCommonOrBuilder> publicAreaCommonBuilder_;
    
    public static final Descriptors.Descriptor getDescriptor() {
      return Tiktok.internal_static_EmojiChatMessage_descriptor;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return Tiktok.internal_static_EmojiChatMessage_fieldAccessorTable
        .ensureFieldAccessorsInitialized(EmojiChatMessage.class, Builder.class);
    }
    
    private Builder() {
      this.defaultContent_ = "";
      maybeForceBuilderInitialization();
    }
    
    private Builder(GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      this.defaultContent_ = "";
      maybeForceBuilderInitialization();
    }
    
    private void maybeForceBuilderInitialization() {
      if (EmojiChatMessage.alwaysUseFieldBuilders) {
        getCommonFieldBuilder();
        getUserFieldBuilder();
        getEmojiContentFieldBuilder();
        getBackgroundImageFieldBuilder();
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
      this.emojiId_ = 0L;
      this.emojiContent_ = null;
      if (this.emojiContentBuilder_ != null) {
        this.emojiContentBuilder_.dispose();
        this.emojiContentBuilder_ = null;
      } 
      this.defaultContent_ = "";
      this.backgroundImage_ = null;
      if (this.backgroundImageBuilder_ != null) {
        this.backgroundImageBuilder_.dispose();
        this.backgroundImageBuilder_ = null;
      } 
      this.fromIntercom_ = false;
      this.intercomHideUserCard_ = false;
      this.publicAreaCommon_ = null;
      if (this.publicAreaCommonBuilder_ != null) {
        this.publicAreaCommonBuilder_.dispose();
        this.publicAreaCommonBuilder_ = null;
      } 
      return this;
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
      return Tiktok.internal_static_EmojiChatMessage_descriptor;
    }
    
    public EmojiChatMessage getDefaultInstanceForType() {
      return EmojiChatMessage.getDefaultInstance();
    }
    
    public EmojiChatMessage build() {
      EmojiChatMessage result = buildPartial();
      if (!result.isInitialized())
        throw newUninitializedMessageException(result); 
      return result;
    }
    
    public EmojiChatMessage buildPartial() {
      EmojiChatMessage result = new EmojiChatMessage(this);
      if (this.bitField0_ != 0)
        buildPartial0(result); 
      onBuilt();
      return result;
    }
    
    private void buildPartial0(EmojiChatMessage result) {
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
        result.emojiId_ = this.emojiId_; 
      if ((from_bitField0_ & 0x8) != 0) {
        result.emojiContent_ = (this.emojiContentBuilder_ == null) ? this.emojiContent_ : (Text)this.emojiContentBuilder_.build();
        to_bitField0_ |= 0x4;
      } 
      if ((from_bitField0_ & 0x10) != 0)
        result.defaultContent_ = this.defaultContent_; 
      if ((from_bitField0_ & 0x20) != 0) {
        result.backgroundImage_ = (this.backgroundImageBuilder_ == null) ? this.backgroundImage_ : (Image)this.backgroundImageBuilder_.build();
        to_bitField0_ |= 0x8;
      } 
      if ((from_bitField0_ & 0x40) != 0)
        result.fromIntercom_ = this.fromIntercom_; 
      if ((from_bitField0_ & 0x80) != 0)
        result.intercomHideUserCard_ = this.intercomHideUserCard_; 
      if ((from_bitField0_ & 0x100) != 0) {
        result.publicAreaCommon_ = (this.publicAreaCommonBuilder_ == null) ? this.publicAreaCommon_ : (PublicAreaCommon)this.publicAreaCommonBuilder_.build();
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
      if (other instanceof EmojiChatMessage)
        return mergeFrom((EmojiChatMessage)other); 
      super.mergeFrom(other);
      return this;
    }
    
    public Builder mergeFrom(EmojiChatMessage other) {
      if (other == EmojiChatMessage.getDefaultInstance())
        return this; 
      if (other.hasCommon())
        mergeCommon(other.getCommon()); 
      if (other.hasUser())
        mergeUser(other.getUser()); 
      if (other.getEmojiId() != 0L)
        setEmojiId(other.getEmojiId()); 
      if (other.hasEmojiContent())
        mergeEmojiContent(other.getEmojiContent()); 
      if (!other.getDefaultContent().isEmpty()) {
        this.defaultContent_ = other.defaultContent_;
        this.bitField0_ |= 0x10;
        onChanged();
      } 
      if (other.hasBackgroundImage())
        mergeBackgroundImage(other.getBackgroundImage()); 
      if (other.getFromIntercom())
        setFromIntercom(other.getFromIntercom()); 
      if (other.getIntercomHideUserCard())
        setIntercomHideUserCard(other.getIntercomHideUserCard()); 
      if (other.hasPublicAreaCommon())
        mergePublicAreaCommon(other.getPublicAreaCommon()); 
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
              this.emojiId_ = input.readUInt64();
              this.bitField0_ |= 0x4;
              continue;
            case 34:
              input.readMessage((MessageLite.Builder)getEmojiContentFieldBuilder().getBuilder(), extensionRegistry);
              this.bitField0_ |= 0x8;
              continue;
            case 42:
              this.defaultContent_ = input.readStringRequireUtf8();
              this.bitField0_ |= 0x10;
              continue;
            case 50:
              input.readMessage((MessageLite.Builder)getBackgroundImageFieldBuilder().getBuilder(), extensionRegistry);
              this.bitField0_ |= 0x20;
              continue;
            case 56:
              this.fromIntercom_ = input.readBool();
              this.bitField0_ |= 0x40;
              continue;
            case 64:
              this.intercomHideUserCard_ = input.readBool();
              this.bitField0_ |= 0x80;
              continue;
            case 74:
              input.readMessage((MessageLite.Builder)getPublicAreaCommonFieldBuilder().getBuilder(), extensionRegistry);
              this.bitField0_ |= 0x100;
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
    
    public long getEmojiId() {
      return this.emojiId_;
    }
    
    public Builder setEmojiId(long value) {
      this.emojiId_ = value;
      this.bitField0_ |= 0x4;
      onChanged();
      return this;
    }
    
    public Builder clearEmojiId() {
      this.bitField0_ &= 0xFFFFFFFB;
      this.emojiId_ = 0L;
      onChanged();
      return this;
    }
    
    public boolean hasEmojiContent() {
      return ((this.bitField0_ & 0x8) != 0);
    }
    
    public Text getEmojiContent() {
      if (this.emojiContentBuilder_ == null)
        return (this.emojiContent_ == null) ? Text.getDefaultInstance() : this.emojiContent_; 
      return (Text)this.emojiContentBuilder_.getMessage();
    }
    
    public Builder setEmojiContent(Text value) {
      if (this.emojiContentBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        this.emojiContent_ = value;
      } else {
        this.emojiContentBuilder_.setMessage((AbstractMessage)value);
      } 
      this.bitField0_ |= 0x8;
      onChanged();
      return this;
    }
    
    public Builder setEmojiContent(Text.Builder builderForValue) {
      if (this.emojiContentBuilder_ == null) {
        this.emojiContent_ = builderForValue.build();
      } else {
        this.emojiContentBuilder_.setMessage((AbstractMessage)builderForValue.build());
      } 
      this.bitField0_ |= 0x8;
      onChanged();
      return this;
    }
    
    public Builder mergeEmojiContent(Text value) {
      if (this.emojiContentBuilder_ == null) {
        if ((this.bitField0_ & 0x8) != 0 && this.emojiContent_ != null && this.emojiContent_ != Text.getDefaultInstance()) {
          getEmojiContentBuilder().mergeFrom(value);
        } else {
          this.emojiContent_ = value;
        } 
      } else {
        this.emojiContentBuilder_.mergeFrom((AbstractMessage)value);
      } 
      if (this.emojiContent_ != null) {
        this.bitField0_ |= 0x8;
        onChanged();
      } 
      return this;
    }
    
    public Builder clearEmojiContent() {
      this.bitField0_ &= 0xFFFFFFF7;
      this.emojiContent_ = null;
      if (this.emojiContentBuilder_ != null) {
        this.emojiContentBuilder_.dispose();
        this.emojiContentBuilder_ = null;
      } 
      onChanged();
      return this;
    }
    
    public Text.Builder getEmojiContentBuilder() {
      this.bitField0_ |= 0x8;
      onChanged();
      return (Text.Builder)getEmojiContentFieldBuilder().getBuilder();
    }
    
    public TextOrBuilder getEmojiContentOrBuilder() {
      if (this.emojiContentBuilder_ != null)
        return (TextOrBuilder)this.emojiContentBuilder_.getMessageOrBuilder(); 
      return (this.emojiContent_ == null) ? Text.getDefaultInstance() : this.emojiContent_;
    }
    
    private SingleFieldBuilderV3<Text, Text.Builder, TextOrBuilder> getEmojiContentFieldBuilder() {
      if (this.emojiContentBuilder_ == null) {
        this.emojiContentBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getEmojiContent(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.emojiContent_ = null;
      } 
      return this.emojiContentBuilder_;
    }
    
    public String getDefaultContent() {
      Object ref = this.defaultContent_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.defaultContent_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getDefaultContentBytes() {
      Object ref = this.defaultContent_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.defaultContent_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setDefaultContent(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.defaultContent_ = value;
      this.bitField0_ |= 0x10;
      onChanged();
      return this;
    }
    
    public Builder clearDefaultContent() {
      this.defaultContent_ = EmojiChatMessage.getDefaultInstance().getDefaultContent();
      this.bitField0_ &= 0xFFFFFFEF;
      onChanged();
      return this;
    }
    
    public Builder setDefaultContentBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      EmojiChatMessage.checkByteStringIsUtf8(value);
      this.defaultContent_ = value;
      this.bitField0_ |= 0x10;
      onChanged();
      return this;
    }
    
    public boolean hasBackgroundImage() {
      return ((this.bitField0_ & 0x20) != 0);
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
      this.bitField0_ |= 0x20;
      onChanged();
      return this;
    }
    
    public Builder setBackgroundImage(Image.Builder builderForValue) {
      if (this.backgroundImageBuilder_ == null) {
        this.backgroundImage_ = builderForValue.build();
      } else {
        this.backgroundImageBuilder_.setMessage((AbstractMessage)builderForValue.build());
      } 
      this.bitField0_ |= 0x20;
      onChanged();
      return this;
    }
    
    public Builder mergeBackgroundImage(Image value) {
      if (this.backgroundImageBuilder_ == null) {
        if ((this.bitField0_ & 0x20) != 0 && this.backgroundImage_ != null && this.backgroundImage_ != 
          
          Image.getDefaultInstance()) {
          getBackgroundImageBuilder().mergeFrom(value);
        } else {
          this.backgroundImage_ = value;
        } 
      } else {
        this.backgroundImageBuilder_.mergeFrom((AbstractMessage)value);
      } 
      if (this.backgroundImage_ != null) {
        this.bitField0_ |= 0x20;
        onChanged();
      } 
      return this;
    }
    
    public Builder clearBackgroundImage() {
      this.bitField0_ &= 0xFFFFFFDF;
      this.backgroundImage_ = null;
      if (this.backgroundImageBuilder_ != null) {
        this.backgroundImageBuilder_.dispose();
        this.backgroundImageBuilder_ = null;
      } 
      onChanged();
      return this;
    }
    
    public Image.Builder getBackgroundImageBuilder() {
      this.bitField0_ |= 0x20;
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
    
    public boolean getFromIntercom() {
      return this.fromIntercom_;
    }
    
    public Builder setFromIntercom(boolean value) {
      this.fromIntercom_ = value;
      this.bitField0_ |= 0x40;
      onChanged();
      return this;
    }
    
    public Builder clearFromIntercom() {
      this.bitField0_ &= 0xFFFFFFBF;
      this.fromIntercom_ = false;
      onChanged();
      return this;
    }
    
    public boolean getIntercomHideUserCard() {
      return this.intercomHideUserCard_;
    }
    
    public Builder setIntercomHideUserCard(boolean value) {
      this.intercomHideUserCard_ = value;
      this.bitField0_ |= 0x80;
      onChanged();
      return this;
    }
    
    public Builder clearIntercomHideUserCard() {
      this.bitField0_ &= 0xFFFFFF7F;
      this.intercomHideUserCard_ = false;
      onChanged();
      return this;
    }
    
    public boolean hasPublicAreaCommon() {
      return ((this.bitField0_ & 0x100) != 0);
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
      this.bitField0_ |= 0x100;
      onChanged();
      return this;
    }
    
    public Builder setPublicAreaCommon(PublicAreaCommon.Builder builderForValue) {
      if (this.publicAreaCommonBuilder_ == null) {
        this.publicAreaCommon_ = builderForValue.build();
      } else {
        this.publicAreaCommonBuilder_.setMessage((AbstractMessage)builderForValue.build());
      } 
      this.bitField0_ |= 0x100;
      onChanged();
      return this;
    }
    
    public Builder mergePublicAreaCommon(PublicAreaCommon value) {
      if (this.publicAreaCommonBuilder_ == null) {
        if ((this.bitField0_ & 0x100) != 0 && this.publicAreaCommon_ != null && this.publicAreaCommon_ != 
          
          PublicAreaCommon.getDefaultInstance()) {
          getPublicAreaCommonBuilder().mergeFrom(value);
        } else {
          this.publicAreaCommon_ = value;
        } 
      } else {
        this.publicAreaCommonBuilder_.mergeFrom((AbstractMessage)value);
      } 
      if (this.publicAreaCommon_ != null) {
        this.bitField0_ |= 0x100;
        onChanged();
      } 
      return this;
    }
    
    public Builder clearPublicAreaCommon() {
      this.bitField0_ &= 0xFFFFFEFF;
      this.publicAreaCommon_ = null;
      if (this.publicAreaCommonBuilder_ != null) {
        this.publicAreaCommonBuilder_.dispose();
        this.publicAreaCommonBuilder_ = null;
      } 
      onChanged();
      return this;
    }
    
    public PublicAreaCommon.Builder getPublicAreaCommonBuilder() {
      this.bitField0_ |= 0x100;
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
    
    public final Builder setUnknownFields(UnknownFieldSet unknownFields) {
      return (Builder)super.setUnknownFields(unknownFields);
    }
    
    public final Builder mergeUnknownFields(UnknownFieldSet unknownFields) {
      return (Builder)super.mergeUnknownFields(unknownFields);
    }
  }
  
  private static final EmojiChatMessage DEFAULT_INSTANCE = new EmojiChatMessage();
  
  public static EmojiChatMessage getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }
  
  private static final Parser<EmojiChatMessage> PARSER = (Parser<EmojiChatMessage>)new AbstractParser<EmojiChatMessage>() {
      public EmojiChatMessage parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        EmojiChatMessage.Builder builder = EmojiChatMessage.newBuilder();
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
  
  public static Parser<EmojiChatMessage> parser() {
    return PARSER;
  }
  
  public Parser<EmojiChatMessage> getParserForType() {
    return PARSER;
  }
  
  public EmojiChatMessage getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }
}
