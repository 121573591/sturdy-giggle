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

public final class MemberMessage extends GeneratedMessageV3 implements MemberMessageOrBuilder {
  private static final long serialVersionUID = 0L;
  
  private int bitField0_;
  
  public static final int COMMON_FIELD_NUMBER = 1;
  
  private Common common_;
  
  public static final int USER_FIELD_NUMBER = 2;
  
  private User user_;
  
  public static final int MEMBERCOUNT_FIELD_NUMBER = 3;
  
  private long memberCount_;
  
  public static final int ACTION_FIELD_NUMBER = 10;
  
  private long action_;
  
  public static final int ANCHORDISPLAYTEXT_FIELD_NUMBER = 18;
  
  private Text anchorDisplayText_;
  
  private byte memoizedIsInitialized;
  
  private MemberMessage(GeneratedMessageV3.Builder<?> builder) {
    super(builder);
    this.memberCount_ = 0L;
    this.action_ = 0L;
    this.memoizedIsInitialized = -1;
  }
  
  private MemberMessage() {
    this.memberCount_ = 0L;
    this.action_ = 0L;
    this.memoizedIsInitialized = -1;
  }
  
  protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
    return new MemberMessage();
  }
  
  public static final Descriptors.Descriptor getDescriptor() {
    return Tiktok.internal_static_MemberMessage_descriptor;
  }
  
  protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
    return Tiktok.internal_static_MemberMessage_fieldAccessorTable.ensureFieldAccessorsInitialized(MemberMessage.class, Builder.class);
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
  
  public long getMemberCount() {
    return this.memberCount_;
  }
  
  public long getAction() {
    return this.action_;
  }
  
  public boolean hasAnchorDisplayText() {
    return ((this.bitField0_ & 0x4) != 0);
  }
  
  public Text getAnchorDisplayText() {
    return (this.anchorDisplayText_ == null) ? Text.getDefaultInstance() : this.anchorDisplayText_;
  }
  
  public TextOrBuilder getAnchorDisplayTextOrBuilder() {
    return (this.anchorDisplayText_ == null) ? Text.getDefaultInstance() : this.anchorDisplayText_;
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
    if (this.memberCount_ != 0L)
      output.writeUInt64(3, this.memberCount_); 
    if (this.action_ != 0L)
      output.writeUInt64(10, this.action_); 
    if ((this.bitField0_ & 0x4) != 0)
      output.writeMessage(18, (MessageLite)getAnchorDisplayText()); 
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
    if (this.memberCount_ != 0L)
      size += 
        CodedOutputStream.computeUInt64Size(3, this.memberCount_); 
    if (this.action_ != 0L)
      size += 
        CodedOutputStream.computeUInt64Size(10, this.action_); 
    if ((this.bitField0_ & 0x4) != 0)
      size += 
        CodedOutputStream.computeMessageSize(18, (MessageLite)getAnchorDisplayText()); 
    size += getUnknownFields().getSerializedSize();
    this.memoizedSize = size;
    return size;
  }
  
  public boolean equals(Object obj) {
    if (obj == this)
      return true; 
    if (!(obj instanceof MemberMessage))
      return super.equals(obj); 
    MemberMessage other = (MemberMessage)obj;
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
    if (getMemberCount() != other
      .getMemberCount())
      return false; 
    if (getAction() != other
      .getAction())
      return false; 
    if (hasAnchorDisplayText() != other.hasAnchorDisplayText())
      return false; 
    if (hasAnchorDisplayText() && 
      
      !getAnchorDisplayText().equals(other.getAnchorDisplayText()))
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
        getMemberCount());
    hash = 37 * hash + 10;
    hash = 53 * hash + Internal.hashLong(
        getAction());
    if (hasAnchorDisplayText()) {
      hash = 37 * hash + 18;
      hash = 53 * hash + getAnchorDisplayText().hashCode();
    } 
    hash = 29 * hash + getUnknownFields().hashCode();
    this.memoizedHashCode = hash;
    return hash;
  }
  
  public static MemberMessage parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
    return (MemberMessage)PARSER.parseFrom(data);
  }
  
  public static MemberMessage parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (MemberMessage)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static MemberMessage parseFrom(ByteString data) throws InvalidProtocolBufferException {
    return (MemberMessage)PARSER.parseFrom(data);
  }
  
  public static MemberMessage parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (MemberMessage)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static MemberMessage parseFrom(byte[] data) throws InvalidProtocolBufferException {
    return (MemberMessage)PARSER.parseFrom(data);
  }
  
  public static MemberMessage parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (MemberMessage)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static MemberMessage parseFrom(InputStream input) throws IOException {
    return 
      (MemberMessage)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static MemberMessage parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (MemberMessage)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static MemberMessage parseDelimitedFrom(InputStream input) throws IOException {
    return 
      (MemberMessage)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
  }
  
  public static MemberMessage parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (MemberMessage)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static MemberMessage parseFrom(CodedInputStream input) throws IOException {
    return 
      (MemberMessage)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static MemberMessage parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (MemberMessage)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public Builder newBuilderForType() {
    return newBuilder();
  }
  
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  
  public static Builder newBuilder(MemberMessage prototype) {
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
  
  public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements MemberMessageOrBuilder {
    private int bitField0_;
    
    private Common common_;
    
    private SingleFieldBuilderV3<Common, Common.Builder, CommonOrBuilder> commonBuilder_;
    
    private User user_;
    
    private SingleFieldBuilderV3<User, User.Builder, UserOrBuilder> userBuilder_;
    
    private long memberCount_;
    
    private long action_;
    
    private Text anchorDisplayText_;
    
    private SingleFieldBuilderV3<Text, Text.Builder, TextOrBuilder> anchorDisplayTextBuilder_;
    
    public static final Descriptors.Descriptor getDescriptor() {
      return Tiktok.internal_static_MemberMessage_descriptor;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return Tiktok.internal_static_MemberMessage_fieldAccessorTable
        .ensureFieldAccessorsInitialized(MemberMessage.class, Builder.class);
    }
    
    private Builder() {
      maybeForceBuilderInitialization();
    }
    
    private Builder(GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    
    private void maybeForceBuilderInitialization() {
      if (MemberMessage.alwaysUseFieldBuilders) {
        getCommonFieldBuilder();
        getUserFieldBuilder();
        getAnchorDisplayTextFieldBuilder();
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
      this.memberCount_ = 0L;
      this.action_ = 0L;
      this.anchorDisplayText_ = null;
      if (this.anchorDisplayTextBuilder_ != null) {
        this.anchorDisplayTextBuilder_.dispose();
        this.anchorDisplayTextBuilder_ = null;
      } 
      return this;
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
      return Tiktok.internal_static_MemberMessage_descriptor;
    }
    
    public MemberMessage getDefaultInstanceForType() {
      return MemberMessage.getDefaultInstance();
    }
    
    public MemberMessage build() {
      MemberMessage result = buildPartial();
      if (!result.isInitialized())
        throw newUninitializedMessageException(result); 
      return result;
    }
    
    public MemberMessage buildPartial() {
      MemberMessage result = new MemberMessage(this);
      if (this.bitField0_ != 0)
        buildPartial0(result); 
      onBuilt();
      return result;
    }
    
    private void buildPartial0(MemberMessage result) {
      int from_bitField0_ = this.bitField0_;
      int to_bitField0_ = 0;
      if ((from_bitField0_ & 0x1) != 0) {
        result.common_ = (this.commonBuilder_ == null) ? 
          this.common_ : 
          (Common)this.commonBuilder_.build();
        to_bitField0_ |= 0x1;
      } 
      if ((from_bitField0_ & 0x2) != 0) {
        result.user_ = (this.userBuilder_ == null) ? 
          this.user_ : 
          (User)this.userBuilder_.build();
        to_bitField0_ |= 0x2;
      } 
      if ((from_bitField0_ & 0x4) != 0)
        result.memberCount_ = this.memberCount_; 
      if ((from_bitField0_ & 0x8) != 0)
        result.action_ = this.action_; 
      if ((from_bitField0_ & 0x10) != 0) {
        result.anchorDisplayText_ = (this.anchorDisplayTextBuilder_ == null) ? 
          this.anchorDisplayText_ : 
          (Text)this.anchorDisplayTextBuilder_.build();
        to_bitField0_ |= 0x4;
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
      if (other instanceof MemberMessage)
        return mergeFrom((MemberMessage)other); 
      super.mergeFrom(other);
      return this;
    }
    
    public Builder mergeFrom(MemberMessage other) {
      if (other == MemberMessage.getDefaultInstance())
        return this; 
      if (other.hasCommon())
        mergeCommon(other.getCommon()); 
      if (other.hasUser())
        mergeUser(other.getUser()); 
      if (other.getMemberCount() != 0L)
        setMemberCount(other.getMemberCount()); 
      if (other.getAction() != 0L)
        setAction(other.getAction()); 
      if (other.hasAnchorDisplayText())
        mergeAnchorDisplayText(other.getAnchorDisplayText()); 
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
              input.readMessage((MessageLite.Builder)
                  getCommonFieldBuilder().getBuilder(), extensionRegistry);
              this.bitField0_ |= 0x1;
              continue;
            case 18:
              input.readMessage((MessageLite.Builder)
                  getUserFieldBuilder().getBuilder(), extensionRegistry);
              this.bitField0_ |= 0x2;
              continue;
            case 24:
              this.memberCount_ = input.readUInt64();
              this.bitField0_ |= 0x4;
              continue;
            case 80:
              this.action_ = input.readUInt64();
              this.bitField0_ |= 0x8;
              continue;
            case 146:
              input.readMessage((MessageLite.Builder)
                  getAnchorDisplayTextFieldBuilder().getBuilder(), extensionRegistry);
              this.bitField0_ |= 0x10;
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
        if ((this.bitField0_ & 0x1) != 0 && this.common_ != null && this.common_ != 
          
          Common.getDefaultInstance()) {
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
      return (this.common_ == null) ? 
        Common.getDefaultInstance() : this.common_;
    }
    
    private SingleFieldBuilderV3<Common, Common.Builder, CommonOrBuilder> getCommonFieldBuilder() {
      if (this.commonBuilder_ == null) {
        this
          
          .commonBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getCommon(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
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
        if ((this.bitField0_ & 0x2) != 0 && this.user_ != null && this.user_ != 
          
          User.getDefaultInstance()) {
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
    
    public long getMemberCount() {
      return this.memberCount_;
    }
    
    public Builder setMemberCount(long value) {
      this.memberCount_ = value;
      this.bitField0_ |= 0x4;
      onChanged();
      return this;
    }
    
    public Builder clearMemberCount() {
      this.bitField0_ &= 0xFFFFFFFB;
      this.memberCount_ = 0L;
      onChanged();
      return this;
    }
    
    public long getAction() {
      return this.action_;
    }
    
    public Builder setAction(long value) {
      this.action_ = value;
      this.bitField0_ |= 0x8;
      onChanged();
      return this;
    }
    
    public Builder clearAction() {
      this.bitField0_ &= 0xFFFFFFF7;
      this.action_ = 0L;
      onChanged();
      return this;
    }
    
    public boolean hasAnchorDisplayText() {
      return ((this.bitField0_ & 0x10) != 0);
    }
    
    public Text getAnchorDisplayText() {
      if (this.anchorDisplayTextBuilder_ == null)
        return (this.anchorDisplayText_ == null) ? Text.getDefaultInstance() : this.anchorDisplayText_; 
      return (Text)this.anchorDisplayTextBuilder_.getMessage();
    }
    
    public Builder setAnchorDisplayText(Text value) {
      if (this.anchorDisplayTextBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        this.anchorDisplayText_ = value;
      } else {
        this.anchorDisplayTextBuilder_.setMessage((AbstractMessage)value);
      } 
      this.bitField0_ |= 0x10;
      onChanged();
      return this;
    }
    
    public Builder setAnchorDisplayText(Text.Builder builderForValue) {
      if (this.anchorDisplayTextBuilder_ == null) {
        this.anchorDisplayText_ = builderForValue.build();
      } else {
        this.anchorDisplayTextBuilder_.setMessage((AbstractMessage)builderForValue.build());
      } 
      this.bitField0_ |= 0x10;
      onChanged();
      return this;
    }
    
    public Builder mergeAnchorDisplayText(Text value) {
      if (this.anchorDisplayTextBuilder_ == null) {
        if ((this.bitField0_ & 0x10) != 0 && this.anchorDisplayText_ != null && this.anchorDisplayText_ != 
          
          Text.getDefaultInstance()) {
          getAnchorDisplayTextBuilder().mergeFrom(value);
        } else {
          this.anchorDisplayText_ = value;
        } 
      } else {
        this.anchorDisplayTextBuilder_.mergeFrom((AbstractMessage)value);
      } 
      if (this.anchorDisplayText_ != null) {
        this.bitField0_ |= 0x10;
        onChanged();
      } 
      return this;
    }
    
    public Builder clearAnchorDisplayText() {
      this.bitField0_ &= 0xFFFFFFEF;
      this.anchorDisplayText_ = null;
      if (this.anchorDisplayTextBuilder_ != null) {
        this.anchorDisplayTextBuilder_.dispose();
        this.anchorDisplayTextBuilder_ = null;
      } 
      onChanged();
      return this;
    }
    
    public Text.Builder getAnchorDisplayTextBuilder() {
      this.bitField0_ |= 0x10;
      onChanged();
      return (Text.Builder)getAnchorDisplayTextFieldBuilder().getBuilder();
    }
    
    public TextOrBuilder getAnchorDisplayTextOrBuilder() {
      if (this.anchorDisplayTextBuilder_ != null)
        return (TextOrBuilder)this.anchorDisplayTextBuilder_.getMessageOrBuilder(); 
      return (this.anchorDisplayText_ == null) ? 
        Text.getDefaultInstance() : this.anchorDisplayText_;
    }
    
    private SingleFieldBuilderV3<Text, Text.Builder, TextOrBuilder> getAnchorDisplayTextFieldBuilder() {
      if (this.anchorDisplayTextBuilder_ == null) {
        this
          
          .anchorDisplayTextBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getAnchorDisplayText(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.anchorDisplayText_ = null;
      } 
      return this.anchorDisplayTextBuilder_;
    }
    
    public final Builder setUnknownFields(UnknownFieldSet unknownFields) {
      return (Builder)super.setUnknownFields(unknownFields);
    }
    
    public final Builder mergeUnknownFields(UnknownFieldSet unknownFields) {
      return (Builder)super.mergeUnknownFields(unknownFields);
    }
  }
  
  private static final MemberMessage DEFAULT_INSTANCE = new MemberMessage();
  
  public static MemberMessage getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }
  
  private static final Parser<MemberMessage> PARSER = (Parser<MemberMessage>)new AbstractParser<MemberMessage>() {
      public MemberMessage parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        MemberMessage.Builder builder = MemberMessage.newBuilder();
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
  
  public static Parser<MemberMessage> parser() {
    return PARSER;
  }
  
  public Parser<MemberMessage> getParserForType() {
    return PARSER;
  }
  
  public MemberMessage getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }
}
