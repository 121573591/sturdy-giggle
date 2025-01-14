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

public final class FansclubMessage extends GeneratedMessageV3 implements FansclubMessageOrBuilder {
  private static final long serialVersionUID = 0L;
  
  private int bitField0_;
  
  public static final int COMMONINFO_FIELD_NUMBER = 1;
  
  private Common commonInfo_;
  
  public static final int TYPE_FIELD_NUMBER = 2;
  
  private int type_;
  
  public static final int CONTENT_FIELD_NUMBER = 3;
  
  private volatile Object content_;
  
  public static final int USER_FIELD_NUMBER = 4;
  
  private User user_;
  
  private byte memoizedIsInitialized;
  
  private FansclubMessage(GeneratedMessageV3.Builder<?> builder) {
    super(builder);
    this.type_ = 0;
    this.content_ = "";
    this.memoizedIsInitialized = -1;
  }
  
  private FansclubMessage() {
    this.type_ = 0;
    this.content_ = "";
    this.memoizedIsInitialized = -1;
    this.content_ = "";
  }
  
  protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
    return new FansclubMessage();
  }
  
  public static final Descriptors.Descriptor getDescriptor() {
    return Douyin.internal_static_FansclubMessage_descriptor;
  }
  
  protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
    return Douyin.internal_static_FansclubMessage_fieldAccessorTable.ensureFieldAccessorsInitialized(FansclubMessage.class, Builder.class);
  }
  
  public boolean hasCommonInfo() {
    return ((this.bitField0_ & 0x1) != 0);
  }
  
  public Common getCommonInfo() {
    return (this.commonInfo_ == null) ? Common.getDefaultInstance() : this.commonInfo_;
  }
  
  public CommonOrBuilder getCommonInfoOrBuilder() {
    return (this.commonInfo_ == null) ? Common.getDefaultInstance() : this.commonInfo_;
  }
  
  public int getType() {
    return this.type_;
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
  
  public boolean hasUser() {
    return ((this.bitField0_ & 0x2) != 0);
  }
  
  public User getUser() {
    return (this.user_ == null) ? User.getDefaultInstance() : this.user_;
  }
  
  public UserOrBuilder getUserOrBuilder() {
    return (this.user_ == null) ? User.getDefaultInstance() : this.user_;
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
      output.writeMessage(1, (MessageLite)getCommonInfo()); 
    if (this.type_ != 0)
      output.writeInt32(2, this.type_); 
    if (!GeneratedMessageV3.isStringEmpty(this.content_))
      GeneratedMessageV3.writeString(output, 3, this.content_); 
    if ((this.bitField0_ & 0x2) != 0)
      output.writeMessage(4, (MessageLite)getUser()); 
    getUnknownFields().writeTo(output);
  }
  
  public int getSerializedSize() {
    int size = this.memoizedSize;
    if (size != -1)
      return size; 
    size = 0;
    if ((this.bitField0_ & 0x1) != 0)
      size += 
        CodedOutputStream.computeMessageSize(1, (MessageLite)getCommonInfo()); 
    if (this.type_ != 0)
      size += 
        CodedOutputStream.computeInt32Size(2, this.type_); 
    if (!GeneratedMessageV3.isStringEmpty(this.content_))
      size += GeneratedMessageV3.computeStringSize(3, this.content_); 
    if ((this.bitField0_ & 0x2) != 0)
      size += 
        CodedOutputStream.computeMessageSize(4, (MessageLite)getUser()); 
    size += getUnknownFields().getSerializedSize();
    this.memoizedSize = size;
    return size;
  }
  
  public boolean equals(Object obj) {
    if (obj == this)
      return true; 
    if (!(obj instanceof FansclubMessage))
      return super.equals(obj); 
    FansclubMessage other = (FansclubMessage)obj;
    if (hasCommonInfo() != other.hasCommonInfo())
      return false; 
    if (hasCommonInfo() && 
      
      !getCommonInfo().equals(other.getCommonInfo()))
      return false; 
    if (getType() != other
      .getType())
      return false; 
    if (!getContent().equals(other.getContent()))
      return false; 
    if (hasUser() != other.hasUser())
      return false; 
    if (hasUser() && 
      
      !getUser().equals(other.getUser()))
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
    if (hasCommonInfo()) {
      hash = 37 * hash + 1;
      hash = 53 * hash + getCommonInfo().hashCode();
    } 
    hash = 37 * hash + 2;
    hash = 53 * hash + getType();
    hash = 37 * hash + 3;
    hash = 53 * hash + getContent().hashCode();
    if (hasUser()) {
      hash = 37 * hash + 4;
      hash = 53 * hash + getUser().hashCode();
    } 
    hash = 29 * hash + getUnknownFields().hashCode();
    this.memoizedHashCode = hash;
    return hash;
  }
  
  public static FansclubMessage parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
    return (FansclubMessage)PARSER.parseFrom(data);
  }
  
  public static FansclubMessage parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (FansclubMessage)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static FansclubMessage parseFrom(ByteString data) throws InvalidProtocolBufferException {
    return (FansclubMessage)PARSER.parseFrom(data);
  }
  
  public static FansclubMessage parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (FansclubMessage)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static FansclubMessage parseFrom(byte[] data) throws InvalidProtocolBufferException {
    return (FansclubMessage)PARSER.parseFrom(data);
  }
  
  public static FansclubMessage parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (FansclubMessage)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static FansclubMessage parseFrom(InputStream input) throws IOException {
    return 
      (FansclubMessage)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static FansclubMessage parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (FansclubMessage)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static FansclubMessage parseDelimitedFrom(InputStream input) throws IOException {
    return 
      (FansclubMessage)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
  }
  
  public static FansclubMessage parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (FansclubMessage)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static FansclubMessage parseFrom(CodedInputStream input) throws IOException {
    return 
      (FansclubMessage)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static FansclubMessage parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (FansclubMessage)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public Builder newBuilderForType() {
    return newBuilder();
  }
  
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  
  public static Builder newBuilder(FansclubMessage prototype) {
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
  
  public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements FansclubMessageOrBuilder {
    private int bitField0_;
    
    private Common commonInfo_;
    
    private SingleFieldBuilderV3<Common, Common.Builder, CommonOrBuilder> commonInfoBuilder_;
    
    private int type_;
    
    private Object content_;
    
    private User user_;
    
    private SingleFieldBuilderV3<User, User.Builder, UserOrBuilder> userBuilder_;
    
    public static final Descriptors.Descriptor getDescriptor() {
      return Douyin.internal_static_FansclubMessage_descriptor;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return Douyin.internal_static_FansclubMessage_fieldAccessorTable
        .ensureFieldAccessorsInitialized(FansclubMessage.class, Builder.class);
    }
    
    private Builder() {
      this.content_ = "";
      maybeForceBuilderInitialization();
    }
    
    private Builder(GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      this.content_ = "";
      maybeForceBuilderInitialization();
    }
    
    private void maybeForceBuilderInitialization() {
      if (FansclubMessage.alwaysUseFieldBuilders) {
        getCommonInfoFieldBuilder();
        getUserFieldBuilder();
      } 
    }
    
    public Builder clear() {
      super.clear();
      this.bitField0_ = 0;
      this.commonInfo_ = null;
      if (this.commonInfoBuilder_ != null) {
        this.commonInfoBuilder_.dispose();
        this.commonInfoBuilder_ = null;
      } 
      this.type_ = 0;
      this.content_ = "";
      this.user_ = null;
      if (this.userBuilder_ != null) {
        this.userBuilder_.dispose();
        this.userBuilder_ = null;
      } 
      return this;
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
      return Douyin.internal_static_FansclubMessage_descriptor;
    }
    
    public FansclubMessage getDefaultInstanceForType() {
      return FansclubMessage.getDefaultInstance();
    }
    
    public FansclubMessage build() {
      FansclubMessage result = buildPartial();
      if (!result.isInitialized())
        throw newUninitializedMessageException(result); 
      return result;
    }
    
    public FansclubMessage buildPartial() {
      FansclubMessage result = new FansclubMessage(this);
      if (this.bitField0_ != 0)
        buildPartial0(result); 
      onBuilt();
      return result;
    }
    
    private void buildPartial0(FansclubMessage result) {
      int from_bitField0_ = this.bitField0_;
      int to_bitField0_ = 0;
      if ((from_bitField0_ & 0x1) != 0) {
        result.commonInfo_ = (this.commonInfoBuilder_ == null) ? this.commonInfo_ : (Common)this.commonInfoBuilder_.build();
        to_bitField0_ |= 0x1;
      } 
      if ((from_bitField0_ & 0x2) != 0)
        result.type_ = this.type_; 
      if ((from_bitField0_ & 0x4) != 0)
        result.content_ = this.content_; 
      if ((from_bitField0_ & 0x8) != 0) {
        result.user_ = (this.userBuilder_ == null) ? this.user_ : (User)this.userBuilder_.build();
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
      if (other instanceof FansclubMessage)
        return mergeFrom((FansclubMessage)other); 
      super.mergeFrom(other);
      return this;
    }
    
    public Builder mergeFrom(FansclubMessage other) {
      if (other == FansclubMessage.getDefaultInstance())
        return this; 
      if (other.hasCommonInfo())
        mergeCommonInfo(other.getCommonInfo()); 
      if (other.getType() != 0)
        setType(other.getType()); 
      if (!other.getContent().isEmpty()) {
        this.content_ = other.content_;
        this.bitField0_ |= 0x4;
        onChanged();
      } 
      if (other.hasUser())
        mergeUser(other.getUser()); 
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
              input.readMessage((MessageLite.Builder)getCommonInfoFieldBuilder().getBuilder(), extensionRegistry);
              this.bitField0_ |= 0x1;
              continue;
            case 16:
              this.type_ = input.readInt32();
              this.bitField0_ |= 0x2;
              continue;
            case 26:
              this.content_ = input.readStringRequireUtf8();
              this.bitField0_ |= 0x4;
              continue;
            case 34:
              input.readMessage((MessageLite.Builder)getUserFieldBuilder().getBuilder(), extensionRegistry);
              this.bitField0_ |= 0x8;
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
    
    public boolean hasCommonInfo() {
      return ((this.bitField0_ & 0x1) != 0);
    }
    
    public Common getCommonInfo() {
      if (this.commonInfoBuilder_ == null)
        return (this.commonInfo_ == null) ? Common.getDefaultInstance() : this.commonInfo_; 
      return (Common)this.commonInfoBuilder_.getMessage();
    }
    
    public Builder setCommonInfo(Common value) {
      if (this.commonInfoBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        this.commonInfo_ = value;
      } else {
        this.commonInfoBuilder_.setMessage((AbstractMessage)value);
      } 
      this.bitField0_ |= 0x1;
      onChanged();
      return this;
    }
    
    public Builder setCommonInfo(Common.Builder builderForValue) {
      if (this.commonInfoBuilder_ == null) {
        this.commonInfo_ = builderForValue.build();
      } else {
        this.commonInfoBuilder_.setMessage((AbstractMessage)builderForValue.build());
      } 
      this.bitField0_ |= 0x1;
      onChanged();
      return this;
    }
    
    public Builder mergeCommonInfo(Common value) {
      if (this.commonInfoBuilder_ == null) {
        if ((this.bitField0_ & 0x1) != 0 && this.commonInfo_ != null && this.commonInfo_ != Common.getDefaultInstance()) {
          getCommonInfoBuilder().mergeFrom(value);
        } else {
          this.commonInfo_ = value;
        } 
      } else {
        this.commonInfoBuilder_.mergeFrom((AbstractMessage)value);
      } 
      if (this.commonInfo_ != null) {
        this.bitField0_ |= 0x1;
        onChanged();
      } 
      return this;
    }
    
    public Builder clearCommonInfo() {
      this.bitField0_ &= 0xFFFFFFFE;
      this.commonInfo_ = null;
      if (this.commonInfoBuilder_ != null) {
        this.commonInfoBuilder_.dispose();
        this.commonInfoBuilder_ = null;
      } 
      onChanged();
      return this;
    }
    
    public Common.Builder getCommonInfoBuilder() {
      this.bitField0_ |= 0x1;
      onChanged();
      return (Common.Builder)getCommonInfoFieldBuilder().getBuilder();
    }
    
    public CommonOrBuilder getCommonInfoOrBuilder() {
      if (this.commonInfoBuilder_ != null)
        return (CommonOrBuilder)this.commonInfoBuilder_.getMessageOrBuilder(); 
      return (this.commonInfo_ == null) ? Common.getDefaultInstance() : this.commonInfo_;
    }
    
    private SingleFieldBuilderV3<Common, Common.Builder, CommonOrBuilder> getCommonInfoFieldBuilder() {
      if (this.commonInfoBuilder_ == null) {
        this.commonInfoBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getCommonInfo(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.commonInfo_ = null;
      } 
      return this.commonInfoBuilder_;
    }
    
    public int getType() {
      return this.type_;
    }
    
    public Builder setType(int value) {
      this.type_ = value;
      this.bitField0_ |= 0x2;
      onChanged();
      return this;
    }
    
    public Builder clearType() {
      this.bitField0_ &= 0xFFFFFFFD;
      this.type_ = 0;
      onChanged();
      return this;
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
      this.content_ = FansclubMessage.getDefaultInstance().getContent();
      this.bitField0_ &= 0xFFFFFFFB;
      onChanged();
      return this;
    }
    
    public Builder setContentBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      FansclubMessage.checkByteStringIsUtf8(value);
      this.content_ = value;
      this.bitField0_ |= 0x4;
      onChanged();
      return this;
    }
    
    public boolean hasUser() {
      return ((this.bitField0_ & 0x8) != 0);
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
      this.bitField0_ |= 0x8;
      onChanged();
      return this;
    }
    
    public Builder setUser(User.Builder builderForValue) {
      if (this.userBuilder_ == null) {
        this.user_ = builderForValue.build();
      } else {
        this.userBuilder_.setMessage((AbstractMessage)builderForValue.build());
      } 
      this.bitField0_ |= 0x8;
      onChanged();
      return this;
    }
    
    public Builder mergeUser(User value) {
      if (this.userBuilder_ == null) {
        if ((this.bitField0_ & 0x8) != 0 && this.user_ != null && this.user_ != 
          
          User.getDefaultInstance()) {
          getUserBuilder().mergeFrom(value);
        } else {
          this.user_ = value;
        } 
      } else {
        this.userBuilder_.mergeFrom((AbstractMessage)value);
      } 
      if (this.user_ != null) {
        this.bitField0_ |= 0x8;
        onChanged();
      } 
      return this;
    }
    
    public Builder clearUser() {
      this.bitField0_ &= 0xFFFFFFF7;
      this.user_ = null;
      if (this.userBuilder_ != null) {
        this.userBuilder_.dispose();
        this.userBuilder_ = null;
      } 
      onChanged();
      return this;
    }
    
    public User.Builder getUserBuilder() {
      this.bitField0_ |= 0x8;
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
    
    public final Builder setUnknownFields(UnknownFieldSet unknownFields) {
      return (Builder)super.setUnknownFields(unknownFields);
    }
    
    public final Builder mergeUnknownFields(UnknownFieldSet unknownFields) {
      return (Builder)super.mergeUnknownFields(unknownFields);
    }
  }
  
  private static final FansclubMessage DEFAULT_INSTANCE = new FansclubMessage();
  
  public static FansclubMessage getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }
  
  private static final Parser<FansclubMessage> PARSER = (Parser<FansclubMessage>)new AbstractParser<FansclubMessage>() {
      public FansclubMessage parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        FansclubMessage.Builder builder = FansclubMessage.newBuilder();
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
  
  public static Parser<FansclubMessage> parser() {
    return PARSER;
  }
  
  public Parser<FansclubMessage> getParserForType() {
    return PARSER;
  }
  
  public FansclubMessage getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }
}
