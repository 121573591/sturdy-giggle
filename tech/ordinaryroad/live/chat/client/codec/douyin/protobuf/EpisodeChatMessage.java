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
import com.google.protobuf.LazyStringArrayList;
import com.google.protobuf.LazyStringList;
import com.google.protobuf.Message;
import com.google.protobuf.MessageLite;
import com.google.protobuf.Parser;
import com.google.protobuf.ProtocolStringList;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.List;

public final class EpisodeChatMessage extends GeneratedMessageV3 implements EpisodeChatMessageOrBuilder {
  private static final long serialVersionUID = 0L;
  
  private int bitField0_;
  
  public static final int COMMON_FIELD_NUMBER = 1;
  
  private Message common_;
  
  public static final int USER_FIELD_NUMBER = 2;
  
  private User user_;
  
  public static final int CONTENT_FIELD_NUMBER = 3;
  
  private volatile Object content_;
  
  public static final int VISIBLETOSENDE_FIELD_NUMBER = 4;
  
  private boolean visibleToSende_;
  
  public static final int GIFTIMAGE_FIELD_NUMBER = 7;
  
  private Image giftImage_;
  
  public static final int AGREEMSGID_FIELD_NUMBER = 8;
  
  private long agreeMsgId_;
  
  public static final int COLORVALUELIST_FIELD_NUMBER = 9;
  
  private LazyStringArrayList colorValueList_;
  
  private byte memoizedIsInitialized;
  
  private EpisodeChatMessage(GeneratedMessageV3.Builder<?> builder) {
    super(builder);
    this.content_ = "";
    this.visibleToSende_ = false;
    this.agreeMsgId_ = 0L;
    this
      
      .colorValueList_ = LazyStringArrayList.emptyList();
    this.memoizedIsInitialized = -1;
  }
  
  private EpisodeChatMessage() {
    this.content_ = "";
    this.visibleToSende_ = false;
    this.agreeMsgId_ = 0L;
    this.colorValueList_ = LazyStringArrayList.emptyList();
    this.memoizedIsInitialized = -1;
    this.content_ = "";
    this.colorValueList_ = LazyStringArrayList.emptyList();
  }
  
  protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
    return new EpisodeChatMessage();
  }
  
  public static final Descriptors.Descriptor getDescriptor() {
    return Douyin.internal_static_EpisodeChatMessage_descriptor;
  }
  
  protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
    return Douyin.internal_static_EpisodeChatMessage_fieldAccessorTable.ensureFieldAccessorsInitialized(EpisodeChatMessage.class, Builder.class);
  }
  
  public boolean hasCommon() {
    return ((this.bitField0_ & 0x1) != 0);
  }
  
  public Message getCommon() {
    return (this.common_ == null) ? Message.getDefaultInstance() : this.common_;
  }
  
  public MessageOrBuilder getCommonOrBuilder() {
    return (this.common_ == null) ? Message.getDefaultInstance() : this.common_;
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
  
  public String getContent() {
    Object ref = this.content_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.content_ = s;
    return s;
  }
  
  public ByteString getContentBytes() {
    Object ref = this.content_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.content_ = b;
      return b;
    } 
    return (ByteString)ref;
  }
  
  public boolean getVisibleToSende() {
    return this.visibleToSende_;
  }
  
  public boolean hasGiftImage() {
    return ((this.bitField0_ & 0x4) != 0);
  }
  
  public Image getGiftImage() {
    return (this.giftImage_ == null) ? Image.getDefaultInstance() : this.giftImage_;
  }
  
  public ImageOrBuilder getGiftImageOrBuilder() {
    return (this.giftImage_ == null) ? Image.getDefaultInstance() : this.giftImage_;
  }
  
  public long getAgreeMsgId() {
    return this.agreeMsgId_;
  }
  
  public ProtocolStringList getColorValueListList() {
    return (ProtocolStringList)this.colorValueList_;
  }
  
  public int getColorValueListCount() {
    return this.colorValueList_.size();
  }
  
  public String getColorValueList(int index) {
    return this.colorValueList_.get(index);
  }
  
  public ByteString getColorValueListBytes(int index) {
    return this.colorValueList_.getByteString(index);
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
    if (!GeneratedMessageV3.isStringEmpty(this.content_))
      GeneratedMessageV3.writeString(output, 3, this.content_); 
    if (this.visibleToSende_)
      output.writeBool(4, this.visibleToSende_); 
    if ((this.bitField0_ & 0x4) != 0)
      output.writeMessage(7, (MessageLite)getGiftImage()); 
    if (this.agreeMsgId_ != 0L)
      output.writeUInt64(8, this.agreeMsgId_); 
    for (int i = 0; i < this.colorValueList_.size(); i++)
      GeneratedMessageV3.writeString(output, 9, this.colorValueList_.getRaw(i)); 
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
    if (!GeneratedMessageV3.isStringEmpty(this.content_))
      size += GeneratedMessageV3.computeStringSize(3, this.content_); 
    if (this.visibleToSende_)
      size += 
        CodedOutputStream.computeBoolSize(4, this.visibleToSende_); 
    if ((this.bitField0_ & 0x4) != 0)
      size += 
        CodedOutputStream.computeMessageSize(7, (MessageLite)getGiftImage()); 
    if (this.agreeMsgId_ != 0L)
      size += 
        CodedOutputStream.computeUInt64Size(8, this.agreeMsgId_); 
    int dataSize = 0;
    for (int i = 0; i < this.colorValueList_.size(); i++)
      dataSize += computeStringSizeNoTag(this.colorValueList_.getRaw(i)); 
    size += dataSize;
    size += 1 * getColorValueListList().size();
    size += getUnknownFields().getSerializedSize();
    this.memoizedSize = size;
    return size;
  }
  
  public boolean equals(Object obj) {
    if (obj == this)
      return true; 
    if (!(obj instanceof EpisodeChatMessage))
      return super.equals(obj); 
    EpisodeChatMessage other = (EpisodeChatMessage)obj;
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
    if (!getContent().equals(other.getContent()))
      return false; 
    if (getVisibleToSende() != other
      .getVisibleToSende())
      return false; 
    if (hasGiftImage() != other.hasGiftImage())
      return false; 
    if (hasGiftImage() && 
      
      !getGiftImage().equals(other.getGiftImage()))
      return false; 
    if (getAgreeMsgId() != other
      .getAgreeMsgId())
      return false; 
    if (!getColorValueListList().equals(other.getColorValueListList()))
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
    hash = 53 * hash + getContent().hashCode();
    hash = 37 * hash + 4;
    hash = 53 * hash + Internal.hashBoolean(
        getVisibleToSende());
    if (hasGiftImage()) {
      hash = 37 * hash + 7;
      hash = 53 * hash + getGiftImage().hashCode();
    } 
    hash = 37 * hash + 8;
    hash = 53 * hash + Internal.hashLong(
        getAgreeMsgId());
    if (getColorValueListCount() > 0) {
      hash = 37 * hash + 9;
      hash = 53 * hash + getColorValueListList().hashCode();
    } 
    hash = 29 * hash + getUnknownFields().hashCode();
    this.memoizedHashCode = hash;
    return hash;
  }
  
  public static EpisodeChatMessage parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
    return (EpisodeChatMessage)PARSER.parseFrom(data);
  }
  
  public static EpisodeChatMessage parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (EpisodeChatMessage)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static EpisodeChatMessage parseFrom(ByteString data) throws InvalidProtocolBufferException {
    return (EpisodeChatMessage)PARSER.parseFrom(data);
  }
  
  public static EpisodeChatMessage parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (EpisodeChatMessage)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static EpisodeChatMessage parseFrom(byte[] data) throws InvalidProtocolBufferException {
    return (EpisodeChatMessage)PARSER.parseFrom(data);
  }
  
  public static EpisodeChatMessage parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (EpisodeChatMessage)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static EpisodeChatMessage parseFrom(InputStream input) throws IOException {
    return 
      (EpisodeChatMessage)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static EpisodeChatMessage parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (EpisodeChatMessage)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static EpisodeChatMessage parseDelimitedFrom(InputStream input) throws IOException {
    return 
      (EpisodeChatMessage)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
  }
  
  public static EpisodeChatMessage parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (EpisodeChatMessage)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static EpisodeChatMessage parseFrom(CodedInputStream input) throws IOException {
    return 
      (EpisodeChatMessage)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static EpisodeChatMessage parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (EpisodeChatMessage)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public Builder newBuilderForType() {
    return newBuilder();
  }
  
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  
  public static Builder newBuilder(EpisodeChatMessage prototype) {
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
  
  public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements EpisodeChatMessageOrBuilder {
    private int bitField0_;
    
    private Message common_;
    
    private SingleFieldBuilderV3<Message, Message.Builder, MessageOrBuilder> commonBuilder_;
    
    private User user_;
    
    private SingleFieldBuilderV3<User, User.Builder, UserOrBuilder> userBuilder_;
    
    private Object content_;
    
    private boolean visibleToSende_;
    
    private Image giftImage_;
    
    private SingleFieldBuilderV3<Image, Image.Builder, ImageOrBuilder> giftImageBuilder_;
    
    private long agreeMsgId_;
    
    private LazyStringArrayList colorValueList_;
    
    public static final Descriptors.Descriptor getDescriptor() {
      return Douyin.internal_static_EpisodeChatMessage_descriptor;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return Douyin.internal_static_EpisodeChatMessage_fieldAccessorTable
        .ensureFieldAccessorsInitialized(EpisodeChatMessage.class, Builder.class);
    }
    
    private Builder() {
      this.content_ = "";
      this
        .colorValueList_ = LazyStringArrayList.emptyList();
      maybeForceBuilderInitialization();
    }
    
    private Builder(GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      this.content_ = "";
      this.colorValueList_ = LazyStringArrayList.emptyList();
      maybeForceBuilderInitialization();
    }
    
    private void maybeForceBuilderInitialization() {
      if (EpisodeChatMessage.alwaysUseFieldBuilders) {
        getCommonFieldBuilder();
        getUserFieldBuilder();
        getGiftImageFieldBuilder();
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
      this.content_ = "";
      this.visibleToSende_ = false;
      this.giftImage_ = null;
      if (this.giftImageBuilder_ != null) {
        this.giftImageBuilder_.dispose();
        this.giftImageBuilder_ = null;
      } 
      this.agreeMsgId_ = 0L;
      this.colorValueList_ = LazyStringArrayList.emptyList();
      return this;
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
      return Douyin.internal_static_EpisodeChatMessage_descriptor;
    }
    
    public EpisodeChatMessage getDefaultInstanceForType() {
      return EpisodeChatMessage.getDefaultInstance();
    }
    
    public EpisodeChatMessage build() {
      EpisodeChatMessage result = buildPartial();
      if (!result.isInitialized())
        throw newUninitializedMessageException(result); 
      return result;
    }
    
    public EpisodeChatMessage buildPartial() {
      EpisodeChatMessage result = new EpisodeChatMessage(this);
      if (this.bitField0_ != 0)
        buildPartial0(result); 
      onBuilt();
      return result;
    }
    
    private void buildPartial0(EpisodeChatMessage result) {
      int from_bitField0_ = this.bitField0_;
      int to_bitField0_ = 0;
      if ((from_bitField0_ & 0x1) != 0) {
        result.common_ = (this.commonBuilder_ == null) ? this.common_ : (Message)this.commonBuilder_.build();
        to_bitField0_ |= 0x1;
      } 
      if ((from_bitField0_ & 0x2) != 0) {
        result.user_ = (this.userBuilder_ == null) ? this.user_ : (User)this.userBuilder_.build();
        to_bitField0_ |= 0x2;
      } 
      if ((from_bitField0_ & 0x4) != 0)
        result.content_ = this.content_; 
      if ((from_bitField0_ & 0x8) != 0)
        result.visibleToSende_ = this.visibleToSende_; 
      if ((from_bitField0_ & 0x10) != 0) {
        result.giftImage_ = (this.giftImageBuilder_ == null) ? this.giftImage_ : (Image)this.giftImageBuilder_.build();
        to_bitField0_ |= 0x4;
      } 
      if ((from_bitField0_ & 0x20) != 0)
        result.agreeMsgId_ = this.agreeMsgId_; 
      if ((from_bitField0_ & 0x40) != 0) {
        this.colorValueList_.makeImmutable();
        result.colorValueList_ = this.colorValueList_;
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
      if (other instanceof EpisodeChatMessage)
        return mergeFrom((EpisodeChatMessage)other); 
      super.mergeFrom(other);
      return this;
    }
    
    public Builder mergeFrom(EpisodeChatMessage other) {
      if (other == EpisodeChatMessage.getDefaultInstance())
        return this; 
      if (other.hasCommon())
        mergeCommon(other.getCommon()); 
      if (other.hasUser())
        mergeUser(other.getUser()); 
      if (!other.getContent().isEmpty()) {
        this.content_ = other.content_;
        this.bitField0_ |= 0x4;
        onChanged();
      } 
      if (other.getVisibleToSende())
        setVisibleToSende(other.getVisibleToSende()); 
      if (other.hasGiftImage())
        mergeGiftImage(other.getGiftImage()); 
      if (other.getAgreeMsgId() != 0L)
        setAgreeMsgId(other.getAgreeMsgId()); 
      if (!other.colorValueList_.isEmpty()) {
        if (this.colorValueList_.isEmpty()) {
          this.colorValueList_ = other.colorValueList_;
          this.bitField0_ |= 0x40;
        } else {
          ensureColorValueListIsMutable();
          this.colorValueList_.addAll((Collection)other.colorValueList_);
        } 
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
          String s;
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
            case 26:
              this.content_ = input.readStringRequireUtf8();
              this.bitField0_ |= 0x4;
              continue;
            case 32:
              this.visibleToSende_ = input.readBool();
              this.bitField0_ |= 0x8;
              continue;
            case 58:
              input.readMessage((MessageLite.Builder)getGiftImageFieldBuilder().getBuilder(), extensionRegistry);
              this.bitField0_ |= 0x10;
              continue;
            case 64:
              this.agreeMsgId_ = input.readUInt64();
              this.bitField0_ |= 0x20;
              continue;
            case 74:
              s = input.readStringRequireUtf8();
              ensureColorValueListIsMutable();
              this.colorValueList_.add(s);
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
    
    public Message getCommon() {
      if (this.commonBuilder_ == null)
        return (this.common_ == null) ? Message.getDefaultInstance() : this.common_; 
      return (Message)this.commonBuilder_.getMessage();
    }
    
    public Builder setCommon(Message value) {
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
    
    public Builder setCommon(Message.Builder builderForValue) {
      if (this.commonBuilder_ == null) {
        this.common_ = builderForValue.build();
      } else {
        this.commonBuilder_.setMessage((AbstractMessage)builderForValue.build());
      } 
      this.bitField0_ |= 0x1;
      onChanged();
      return this;
    }
    
    public Builder mergeCommon(Message value) {
      if (this.commonBuilder_ == null) {
        if ((this.bitField0_ & 0x1) != 0 && this.common_ != null && this.common_ != Message.getDefaultInstance()) {
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
    
    public Message.Builder getCommonBuilder() {
      this.bitField0_ |= 0x1;
      onChanged();
      return (Message.Builder)getCommonFieldBuilder().getBuilder();
    }
    
    public MessageOrBuilder getCommonOrBuilder() {
      if (this.commonBuilder_ != null)
        return (MessageOrBuilder)this.commonBuilder_.getMessageOrBuilder(); 
      return (this.common_ == null) ? Message.getDefaultInstance() : this.common_;
    }
    
    private SingleFieldBuilderV3<Message, Message.Builder, MessageOrBuilder> getCommonFieldBuilder() {
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
    
    public String getContent() {
      Object ref = this.content_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.content_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getContentBytes() {
      Object ref = this.content_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.content_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setContent(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.content_ = value;
      this.bitField0_ |= 0x4;
      onChanged();
      return this;
    }
    
    public Builder clearContent() {
      this.content_ = EpisodeChatMessage.getDefaultInstance().getContent();
      this.bitField0_ &= 0xFFFFFFFB;
      onChanged();
      return this;
    }
    
    public Builder setContentBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      EpisodeChatMessage.checkByteStringIsUtf8(value);
      this.content_ = value;
      this.bitField0_ |= 0x4;
      onChanged();
      return this;
    }
    
    public boolean getVisibleToSende() {
      return this.visibleToSende_;
    }
    
    public Builder setVisibleToSende(boolean value) {
      this.visibleToSende_ = value;
      this.bitField0_ |= 0x8;
      onChanged();
      return this;
    }
    
    public Builder clearVisibleToSende() {
      this.bitField0_ &= 0xFFFFFFF7;
      this.visibleToSende_ = false;
      onChanged();
      return this;
    }
    
    public boolean hasGiftImage() {
      return ((this.bitField0_ & 0x10) != 0);
    }
    
    public Image getGiftImage() {
      if (this.giftImageBuilder_ == null)
        return (this.giftImage_ == null) ? Image.getDefaultInstance() : this.giftImage_; 
      return (Image)this.giftImageBuilder_.getMessage();
    }
    
    public Builder setGiftImage(Image value) {
      if (this.giftImageBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        this.giftImage_ = value;
      } else {
        this.giftImageBuilder_.setMessage((AbstractMessage)value);
      } 
      this.bitField0_ |= 0x10;
      onChanged();
      return this;
    }
    
    public Builder setGiftImage(Image.Builder builderForValue) {
      if (this.giftImageBuilder_ == null) {
        this.giftImage_ = builderForValue.build();
      } else {
        this.giftImageBuilder_.setMessage((AbstractMessage)builderForValue.build());
      } 
      this.bitField0_ |= 0x10;
      onChanged();
      return this;
    }
    
    public Builder mergeGiftImage(Image value) {
      if (this.giftImageBuilder_ == null) {
        if ((this.bitField0_ & 0x10) != 0 && this.giftImage_ != null && this.giftImage_ != Image.getDefaultInstance()) {
          getGiftImageBuilder().mergeFrom(value);
        } else {
          this.giftImage_ = value;
        } 
      } else {
        this.giftImageBuilder_.mergeFrom((AbstractMessage)value);
      } 
      if (this.giftImage_ != null) {
        this.bitField0_ |= 0x10;
        onChanged();
      } 
      return this;
    }
    
    public Builder clearGiftImage() {
      this.bitField0_ &= 0xFFFFFFEF;
      this.giftImage_ = null;
      if (this.giftImageBuilder_ != null) {
        this.giftImageBuilder_.dispose();
        this.giftImageBuilder_ = null;
      } 
      onChanged();
      return this;
    }
    
    public Image.Builder getGiftImageBuilder() {
      this.bitField0_ |= 0x10;
      onChanged();
      return (Image.Builder)getGiftImageFieldBuilder().getBuilder();
    }
    
    public ImageOrBuilder getGiftImageOrBuilder() {
      if (this.giftImageBuilder_ != null)
        return (ImageOrBuilder)this.giftImageBuilder_.getMessageOrBuilder(); 
      return (this.giftImage_ == null) ? Image.getDefaultInstance() : this.giftImage_;
    }
    
    private SingleFieldBuilderV3<Image, Image.Builder, ImageOrBuilder> getGiftImageFieldBuilder() {
      if (this.giftImageBuilder_ == null) {
        this.giftImageBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getGiftImage(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.giftImage_ = null;
      } 
      return this.giftImageBuilder_;
    }
    
    public long getAgreeMsgId() {
      return this.agreeMsgId_;
    }
    
    public Builder setAgreeMsgId(long value) {
      this.agreeMsgId_ = value;
      this.bitField0_ |= 0x20;
      onChanged();
      return this;
    }
    
    public Builder clearAgreeMsgId() {
      this.bitField0_ &= 0xFFFFFFDF;
      this.agreeMsgId_ = 0L;
      onChanged();
      return this;
    }
    
    private void ensureColorValueListIsMutable() {
      if (!this.colorValueList_.isModifiable())
        this.colorValueList_ = new LazyStringArrayList((LazyStringList)this.colorValueList_); 
      this.bitField0_ |= 0x40;
    }
    
    public ProtocolStringList getColorValueListList() {
      this.colorValueList_.makeImmutable();
      return (ProtocolStringList)this.colorValueList_;
    }
    
    public int getColorValueListCount() {
      return this.colorValueList_.size();
    }
    
    public String getColorValueList(int index) {
      return this.colorValueList_.get(index);
    }
    
    public ByteString getColorValueListBytes(int index) {
      return this.colorValueList_.getByteString(index);
    }
    
    public Builder setColorValueList(int index, String value) {
      if (value == null)
        throw new NullPointerException(); 
      ensureColorValueListIsMutable();
      this.colorValueList_.set(index, value);
      this.bitField0_ |= 0x40;
      onChanged();
      return this;
    }
    
    public Builder addColorValueList(String value) {
      if (value == null)
        throw new NullPointerException(); 
      ensureColorValueListIsMutable();
      this.colorValueList_.add(value);
      this.bitField0_ |= 0x40;
      onChanged();
      return this;
    }
    
    public Builder addAllColorValueList(Iterable<String> values) {
      ensureColorValueListIsMutable();
      AbstractMessageLite.Builder.addAll(values, (List)this.colorValueList_);
      this.bitField0_ |= 0x40;
      onChanged();
      return this;
    }
    
    public Builder clearColorValueList() {
      this
        .colorValueList_ = LazyStringArrayList.emptyList();
      this.bitField0_ &= 0xFFFFFFBF;
      onChanged();
      return this;
    }
    
    public Builder addColorValueListBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      EpisodeChatMessage.checkByteStringIsUtf8(value);
      ensureColorValueListIsMutable();
      this.colorValueList_.add(value);
      this.bitField0_ |= 0x40;
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
  
  private static final EpisodeChatMessage DEFAULT_INSTANCE = new EpisodeChatMessage();
  
  public static EpisodeChatMessage getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }
  
  private static final Parser<EpisodeChatMessage> PARSER = (Parser<EpisodeChatMessage>)new AbstractParser<EpisodeChatMessage>() {
      public EpisodeChatMessage parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        EpisodeChatMessage.Builder builder = EpisodeChatMessage.newBuilder();
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
  
  public static Parser<EpisodeChatMessage> parser() {
    return PARSER;
  }
  
  public Parser<EpisodeChatMessage> getParserForType() {
    return PARSER;
  }
  
  public EpisodeChatMessage getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }
}
