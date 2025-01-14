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

public final class TextPieceUser extends GeneratedMessageV3 implements TextPieceUserOrBuilder {
  private static final long serialVersionUID = 0L;
  
  private int bitField0_;
  
  public static final int USER_FIELD_NUMBER = 1;
  
  private User user_;
  
  public static final int WITHCOLON_FIELD_NUMBER = 2;
  
  private boolean withColon_;
  
  public static final int SELF_SHOW_REAL_NAME_FIELD_NUMBER = 3;
  
  private boolean selfShowRealName_;
  
  public static final int LEFT_SHOW_EXTENSION_FIELD_NUMBER = 4;
  
  private int leftShowExtension_;
  
  public static final int LEFT_ADDITIONAL_CONTENT_FIELD_NUMBER = 5;
  
  private volatile Object leftAdditionalContent_;
  
  public static final int RIGHT_ADDITIONAL_CONTENT_FIELD_NUMBER = 6;
  
  private volatile Object rightAdditionalContent_;
  
  private byte memoizedIsInitialized;
  
  private TextPieceUser(GeneratedMessageV3.Builder<?> builder) {
    super(builder);
    this.withColon_ = false;
    this.selfShowRealName_ = false;
    this.leftShowExtension_ = 0;
    this.leftAdditionalContent_ = "";
    this.rightAdditionalContent_ = "";
    this.memoizedIsInitialized = -1;
  }
  
  private TextPieceUser() {
    this.withColon_ = false;
    this.selfShowRealName_ = false;
    this.leftShowExtension_ = 0;
    this.leftAdditionalContent_ = "";
    this.rightAdditionalContent_ = "";
    this.memoizedIsInitialized = -1;
    this.leftAdditionalContent_ = "";
    this.rightAdditionalContent_ = "";
  }
  
  protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
    return new TextPieceUser();
  }
  
  public static final Descriptors.Descriptor getDescriptor() {
    return Douyin.internal_static_TextPieceUser_descriptor;
  }
  
  protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
    return Douyin.internal_static_TextPieceUser_fieldAccessorTable.ensureFieldAccessorsInitialized(TextPieceUser.class, Builder.class);
  }
  
  public boolean hasUser() {
    return ((this.bitField0_ & 0x1) != 0);
  }
  
  public User getUser() {
    return (this.user_ == null) ? User.getDefaultInstance() : this.user_;
  }
  
  public UserOrBuilder getUserOrBuilder() {
    return (this.user_ == null) ? User.getDefaultInstance() : this.user_;
  }
  
  public boolean getWithColon() {
    return this.withColon_;
  }
  
  public boolean getSelfShowRealName() {
    return this.selfShowRealName_;
  }
  
  public int getLeftShowExtension() {
    return this.leftShowExtension_;
  }
  
  public String getLeftAdditionalContent() {
    Object ref = this.leftAdditionalContent_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.leftAdditionalContent_ = s;
    return s;
  }
  
  public ByteString getLeftAdditionalContentBytes() {
    Object ref = this.leftAdditionalContent_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.leftAdditionalContent_ = b;
      return b;
    } 
    return (ByteString)ref;
  }
  
  public String getRightAdditionalContent() {
    Object ref = this.rightAdditionalContent_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.rightAdditionalContent_ = s;
    return s;
  }
  
  public ByteString getRightAdditionalContentBytes() {
    Object ref = this.rightAdditionalContent_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.rightAdditionalContent_ = b;
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
    if ((this.bitField0_ & 0x1) != 0)
      output.writeMessage(1, (MessageLite)getUser()); 
    if (this.withColon_)
      output.writeBool(2, this.withColon_); 
    if (this.selfShowRealName_)
      output.writeBool(3, this.selfShowRealName_); 
    if (this.leftShowExtension_ != 0)
      output.writeUInt32(4, this.leftShowExtension_); 
    if (!GeneratedMessageV3.isStringEmpty(this.leftAdditionalContent_))
      GeneratedMessageV3.writeString(output, 5, this.leftAdditionalContent_); 
    if (!GeneratedMessageV3.isStringEmpty(this.rightAdditionalContent_))
      GeneratedMessageV3.writeString(output, 6, this.rightAdditionalContent_); 
    getUnknownFields().writeTo(output);
  }
  
  public int getSerializedSize() {
    int size = this.memoizedSize;
    if (size != -1)
      return size; 
    size = 0;
    if ((this.bitField0_ & 0x1) != 0)
      size += 
        CodedOutputStream.computeMessageSize(1, (MessageLite)getUser()); 
    if (this.withColon_)
      size += 
        CodedOutputStream.computeBoolSize(2, this.withColon_); 
    if (this.selfShowRealName_)
      size += 
        CodedOutputStream.computeBoolSize(3, this.selfShowRealName_); 
    if (this.leftShowExtension_ != 0)
      size += 
        CodedOutputStream.computeUInt32Size(4, this.leftShowExtension_); 
    if (!GeneratedMessageV3.isStringEmpty(this.leftAdditionalContent_))
      size += GeneratedMessageV3.computeStringSize(5, this.leftAdditionalContent_); 
    if (!GeneratedMessageV3.isStringEmpty(this.rightAdditionalContent_))
      size += GeneratedMessageV3.computeStringSize(6, this.rightAdditionalContent_); 
    size += getUnknownFields().getSerializedSize();
    this.memoizedSize = size;
    return size;
  }
  
  public boolean equals(Object obj) {
    if (obj == this)
      return true; 
    if (!(obj instanceof TextPieceUser))
      return super.equals(obj); 
    TextPieceUser other = (TextPieceUser)obj;
    if (hasUser() != other.hasUser())
      return false; 
    if (hasUser() && 
      
      !getUser().equals(other.getUser()))
      return false; 
    if (getWithColon() != other
      .getWithColon())
      return false; 
    if (getSelfShowRealName() != other
      .getSelfShowRealName())
      return false; 
    if (getLeftShowExtension() != other
      .getLeftShowExtension())
      return false; 
    if (!getLeftAdditionalContent().equals(other.getLeftAdditionalContent()))
      return false; 
    if (!getRightAdditionalContent().equals(other.getRightAdditionalContent()))
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
    if (hasUser()) {
      hash = 37 * hash + 1;
      hash = 53 * hash + getUser().hashCode();
    } 
    hash = 37 * hash + 2;
    hash = 53 * hash + Internal.hashBoolean(
        getWithColon());
    hash = 37 * hash + 3;
    hash = 53 * hash + Internal.hashBoolean(
        getSelfShowRealName());
    hash = 37 * hash + 4;
    hash = 53 * hash + getLeftShowExtension();
    hash = 37 * hash + 5;
    hash = 53 * hash + getLeftAdditionalContent().hashCode();
    hash = 37 * hash + 6;
    hash = 53 * hash + getRightAdditionalContent().hashCode();
    hash = 29 * hash + getUnknownFields().hashCode();
    this.memoizedHashCode = hash;
    return hash;
  }
  
  public static TextPieceUser parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
    return (TextPieceUser)PARSER.parseFrom(data);
  }
  
  public static TextPieceUser parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (TextPieceUser)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static TextPieceUser parseFrom(ByteString data) throws InvalidProtocolBufferException {
    return (TextPieceUser)PARSER.parseFrom(data);
  }
  
  public static TextPieceUser parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (TextPieceUser)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static TextPieceUser parseFrom(byte[] data) throws InvalidProtocolBufferException {
    return (TextPieceUser)PARSER.parseFrom(data);
  }
  
  public static TextPieceUser parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (TextPieceUser)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static TextPieceUser parseFrom(InputStream input) throws IOException {
    return 
      (TextPieceUser)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static TextPieceUser parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (TextPieceUser)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static TextPieceUser parseDelimitedFrom(InputStream input) throws IOException {
    return 
      (TextPieceUser)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
  }
  
  public static TextPieceUser parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (TextPieceUser)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static TextPieceUser parseFrom(CodedInputStream input) throws IOException {
    return 
      (TextPieceUser)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static TextPieceUser parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (TextPieceUser)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public Builder newBuilderForType() {
    return newBuilder();
  }
  
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  
  public static Builder newBuilder(TextPieceUser prototype) {
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
  
  public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements TextPieceUserOrBuilder {
    private int bitField0_;
    
    private User user_;
    
    private SingleFieldBuilderV3<User, User.Builder, UserOrBuilder> userBuilder_;
    
    private boolean withColon_;
    
    private boolean selfShowRealName_;
    
    private int leftShowExtension_;
    
    private Object leftAdditionalContent_;
    
    private Object rightAdditionalContent_;
    
    public static final Descriptors.Descriptor getDescriptor() {
      return Douyin.internal_static_TextPieceUser_descriptor;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return Douyin.internal_static_TextPieceUser_fieldAccessorTable
        .ensureFieldAccessorsInitialized(TextPieceUser.class, Builder.class);
    }
    
    private Builder() {
      this.leftAdditionalContent_ = "";
      this.rightAdditionalContent_ = "";
      maybeForceBuilderInitialization();
    }
    
    private Builder(GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      this.leftAdditionalContent_ = "";
      this.rightAdditionalContent_ = "";
      maybeForceBuilderInitialization();
    }
    
    private void maybeForceBuilderInitialization() {
      if (TextPieceUser.alwaysUseFieldBuilders)
        getUserFieldBuilder(); 
    }
    
    public Builder clear() {
      super.clear();
      this.bitField0_ = 0;
      this.user_ = null;
      if (this.userBuilder_ != null) {
        this.userBuilder_.dispose();
        this.userBuilder_ = null;
      } 
      this.withColon_ = false;
      this.selfShowRealName_ = false;
      this.leftShowExtension_ = 0;
      this.leftAdditionalContent_ = "";
      this.rightAdditionalContent_ = "";
      return this;
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
      return Douyin.internal_static_TextPieceUser_descriptor;
    }
    
    public TextPieceUser getDefaultInstanceForType() {
      return TextPieceUser.getDefaultInstance();
    }
    
    public TextPieceUser build() {
      TextPieceUser result = buildPartial();
      if (!result.isInitialized())
        throw newUninitializedMessageException(result); 
      return result;
    }
    
    public TextPieceUser buildPartial() {
      TextPieceUser result = new TextPieceUser(this);
      if (this.bitField0_ != 0)
        buildPartial0(result); 
      onBuilt();
      return result;
    }
    
    private void buildPartial0(TextPieceUser result) {
      int from_bitField0_ = this.bitField0_;
      int to_bitField0_ = 0;
      if ((from_bitField0_ & 0x1) != 0) {
        result.user_ = (this.userBuilder_ == null) ? this.user_ : (User)this.userBuilder_.build();
        to_bitField0_ |= 0x1;
      } 
      if ((from_bitField0_ & 0x2) != 0)
        result.withColon_ = this.withColon_; 
      if ((from_bitField0_ & 0x4) != 0)
        result.selfShowRealName_ = this.selfShowRealName_; 
      if ((from_bitField0_ & 0x8) != 0)
        result.leftShowExtension_ = this.leftShowExtension_; 
      if ((from_bitField0_ & 0x10) != 0)
        result.leftAdditionalContent_ = this.leftAdditionalContent_; 
      if ((from_bitField0_ & 0x20) != 0)
        result.rightAdditionalContent_ = this.rightAdditionalContent_; 
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
      if (other instanceof TextPieceUser)
        return mergeFrom((TextPieceUser)other); 
      super.mergeFrom(other);
      return this;
    }
    
    public Builder mergeFrom(TextPieceUser other) {
      if (other == TextPieceUser.getDefaultInstance())
        return this; 
      if (other.hasUser())
        mergeUser(other.getUser()); 
      if (other.getWithColon())
        setWithColon(other.getWithColon()); 
      if (other.getSelfShowRealName())
        setSelfShowRealName(other.getSelfShowRealName()); 
      if (other.getLeftShowExtension() != 0)
        setLeftShowExtension(other.getLeftShowExtension()); 
      if (!other.getLeftAdditionalContent().isEmpty()) {
        this.leftAdditionalContent_ = other.leftAdditionalContent_;
        this.bitField0_ |= 0x10;
        onChanged();
      } 
      if (!other.getRightAdditionalContent().isEmpty()) {
        this.rightAdditionalContent_ = other.rightAdditionalContent_;
        this.bitField0_ |= 0x20;
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
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              continue;
            case 10:
              input.readMessage((MessageLite.Builder)getUserFieldBuilder().getBuilder(), extensionRegistry);
              this.bitField0_ |= 0x1;
              continue;
            case 16:
              this.withColon_ = input.readBool();
              this.bitField0_ |= 0x2;
              continue;
            case 24:
              this.selfShowRealName_ = input.readBool();
              this.bitField0_ |= 0x4;
              continue;
            case 32:
              this.leftShowExtension_ = input.readUInt32();
              this.bitField0_ |= 0x8;
              continue;
            case 42:
              this.leftAdditionalContent_ = input.readStringRequireUtf8();
              this.bitField0_ |= 0x10;
              continue;
            case 50:
              this.rightAdditionalContent_ = input.readStringRequireUtf8();
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
    
    public boolean hasUser() {
      return ((this.bitField0_ & 0x1) != 0);
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
      this.bitField0_ |= 0x1;
      onChanged();
      return this;
    }
    
    public Builder setUser(User.Builder builderForValue) {
      if (this.userBuilder_ == null) {
        this.user_ = builderForValue.build();
      } else {
        this.userBuilder_.setMessage((AbstractMessage)builderForValue.build());
      } 
      this.bitField0_ |= 0x1;
      onChanged();
      return this;
    }
    
    public Builder mergeUser(User value) {
      if (this.userBuilder_ == null) {
        if ((this.bitField0_ & 0x1) != 0 && this.user_ != null && this.user_ != User.getDefaultInstance()) {
          getUserBuilder().mergeFrom(value);
        } else {
          this.user_ = value;
        } 
      } else {
        this.userBuilder_.mergeFrom((AbstractMessage)value);
      } 
      if (this.user_ != null) {
        this.bitField0_ |= 0x1;
        onChanged();
      } 
      return this;
    }
    
    public Builder clearUser() {
      this.bitField0_ &= 0xFFFFFFFE;
      this.user_ = null;
      if (this.userBuilder_ != null) {
        this.userBuilder_.dispose();
        this.userBuilder_ = null;
      } 
      onChanged();
      return this;
    }
    
    public User.Builder getUserBuilder() {
      this.bitField0_ |= 0x1;
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
    
    public boolean getWithColon() {
      return this.withColon_;
    }
    
    public Builder setWithColon(boolean value) {
      this.withColon_ = value;
      this.bitField0_ |= 0x2;
      onChanged();
      return this;
    }
    
    public Builder clearWithColon() {
      this.bitField0_ &= 0xFFFFFFFD;
      this.withColon_ = false;
      onChanged();
      return this;
    }
    
    public boolean getSelfShowRealName() {
      return this.selfShowRealName_;
    }
    
    public Builder setSelfShowRealName(boolean value) {
      this.selfShowRealName_ = value;
      this.bitField0_ |= 0x4;
      onChanged();
      return this;
    }
    
    public Builder clearSelfShowRealName() {
      this.bitField0_ &= 0xFFFFFFFB;
      this.selfShowRealName_ = false;
      onChanged();
      return this;
    }
    
    public int getLeftShowExtension() {
      return this.leftShowExtension_;
    }
    
    public Builder setLeftShowExtension(int value) {
      this.leftShowExtension_ = value;
      this.bitField0_ |= 0x8;
      onChanged();
      return this;
    }
    
    public Builder clearLeftShowExtension() {
      this.bitField0_ &= 0xFFFFFFF7;
      this.leftShowExtension_ = 0;
      onChanged();
      return this;
    }
    
    public String getLeftAdditionalContent() {
      Object ref = this.leftAdditionalContent_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.leftAdditionalContent_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getLeftAdditionalContentBytes() {
      Object ref = this.leftAdditionalContent_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.leftAdditionalContent_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setLeftAdditionalContent(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.leftAdditionalContent_ = value;
      this.bitField0_ |= 0x10;
      onChanged();
      return this;
    }
    
    public Builder clearLeftAdditionalContent() {
      this.leftAdditionalContent_ = TextPieceUser.getDefaultInstance().getLeftAdditionalContent();
      this.bitField0_ &= 0xFFFFFFEF;
      onChanged();
      return this;
    }
    
    public Builder setLeftAdditionalContentBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      TextPieceUser.checkByteStringIsUtf8(value);
      this.leftAdditionalContent_ = value;
      this.bitField0_ |= 0x10;
      onChanged();
      return this;
    }
    
    public String getRightAdditionalContent() {
      Object ref = this.rightAdditionalContent_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.rightAdditionalContent_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getRightAdditionalContentBytes() {
      Object ref = this.rightAdditionalContent_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.rightAdditionalContent_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setRightAdditionalContent(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.rightAdditionalContent_ = value;
      this.bitField0_ |= 0x20;
      onChanged();
      return this;
    }
    
    public Builder clearRightAdditionalContent() {
      this.rightAdditionalContent_ = TextPieceUser.getDefaultInstance().getRightAdditionalContent();
      this.bitField0_ &= 0xFFFFFFDF;
      onChanged();
      return this;
    }
    
    public Builder setRightAdditionalContentBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      TextPieceUser.checkByteStringIsUtf8(value);
      this.rightAdditionalContent_ = value;
      this.bitField0_ |= 0x20;
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
  
  private static final TextPieceUser DEFAULT_INSTANCE = new TextPieceUser();
  
  public static TextPieceUser getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }
  
  private static final Parser<TextPieceUser> PARSER = (Parser<TextPieceUser>)new AbstractParser<TextPieceUser>() {
      public TextPieceUser parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        TextPieceUser.Builder builder = TextPieceUser.newBuilder();
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
  
  public static Parser<TextPieceUser> parser() {
    return PARSER;
  }
  
  public Parser<TextPieceUser> getParserForType() {
    return PARSER;
  }
  
  public TextPieceUser getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }
}
