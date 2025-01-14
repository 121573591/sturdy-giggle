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

public final class ChatReplyRespInfo extends GeneratedMessageV3 implements ChatReplyRespInfoOrBuilder {
  private static final long serialVersionUID = 0L;
  
  private int bitField0_;
  
  public static final int REPLY_MSG_ID_FIELD_NUMBER = 1;
  
  private long replyMsgId_;
  
  public static final int REPLY_ID_FIELD_NUMBER = 2;
  
  private long replyId_;
  
  public static final int REPLY_TEXT_FIELD_NUMBER = 3;
  
  private Text replyText_;
  
  public static final int REPLY_UID_FIELD_NUMBER = 4;
  
  private long replyUid_;
  
  public static final int REPLY_WEBCAST_UID_FIELD_NUMBER = 5;
  
  private volatile Object replyWebcastUid_;
  
  private byte memoizedIsInitialized;
  
  private ChatReplyRespInfo(GeneratedMessageV3.Builder<?> builder) {
    super(builder);
    this.replyMsgId_ = 0L;
    this.replyId_ = 0L;
    this.replyUid_ = 0L;
    this.replyWebcastUid_ = "";
    this.memoizedIsInitialized = -1;
  }
  
  private ChatReplyRespInfo() {
    this.replyMsgId_ = 0L;
    this.replyId_ = 0L;
    this.replyUid_ = 0L;
    this.replyWebcastUid_ = "";
    this.memoizedIsInitialized = -1;
    this.replyWebcastUid_ = "";
  }
  
  protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
    return new ChatReplyRespInfo();
  }
  
  public static final Descriptors.Descriptor getDescriptor() {
    return Douyin.internal_static_ChatReplyRespInfo_descriptor;
  }
  
  protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
    return Douyin.internal_static_ChatReplyRespInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(ChatReplyRespInfo.class, Builder.class);
  }
  
  public long getReplyMsgId() {
    return this.replyMsgId_;
  }
  
  public long getReplyId() {
    return this.replyId_;
  }
  
  public boolean hasReplyText() {
    return ((this.bitField0_ & 0x1) != 0);
  }
  
  public Text getReplyText() {
    return (this.replyText_ == null) ? Text.getDefaultInstance() : this.replyText_;
  }
  
  public TextOrBuilder getReplyTextOrBuilder() {
    return (this.replyText_ == null) ? Text.getDefaultInstance() : this.replyText_;
  }
  
  public long getReplyUid() {
    return this.replyUid_;
  }
  
  public String getReplyWebcastUid() {
    Object ref = this.replyWebcastUid_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.replyWebcastUid_ = s;
    return s;
  }
  
  public ByteString getReplyWebcastUidBytes() {
    Object ref = this.replyWebcastUid_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.replyWebcastUid_ = b;
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
    if (this.replyMsgId_ != 0L)
      output.writeUInt64(1, this.replyMsgId_); 
    if (this.replyId_ != 0L)
      output.writeUInt64(2, this.replyId_); 
    if ((this.bitField0_ & 0x1) != 0)
      output.writeMessage(3, (MessageLite)getReplyText()); 
    if (this.replyUid_ != 0L)
      output.writeUInt64(4, this.replyUid_); 
    if (!GeneratedMessageV3.isStringEmpty(this.replyWebcastUid_))
      GeneratedMessageV3.writeString(output, 5, this.replyWebcastUid_); 
    getUnknownFields().writeTo(output);
  }
  
  public int getSerializedSize() {
    int size = this.memoizedSize;
    if (size != -1)
      return size; 
    size = 0;
    if (this.replyMsgId_ != 0L)
      size += 
        CodedOutputStream.computeUInt64Size(1, this.replyMsgId_); 
    if (this.replyId_ != 0L)
      size += 
        CodedOutputStream.computeUInt64Size(2, this.replyId_); 
    if ((this.bitField0_ & 0x1) != 0)
      size += 
        CodedOutputStream.computeMessageSize(3, (MessageLite)getReplyText()); 
    if (this.replyUid_ != 0L)
      size += 
        CodedOutputStream.computeUInt64Size(4, this.replyUid_); 
    if (!GeneratedMessageV3.isStringEmpty(this.replyWebcastUid_))
      size += GeneratedMessageV3.computeStringSize(5, this.replyWebcastUid_); 
    size += getUnknownFields().getSerializedSize();
    this.memoizedSize = size;
    return size;
  }
  
  public boolean equals(Object obj) {
    if (obj == this)
      return true; 
    if (!(obj instanceof ChatReplyRespInfo))
      return super.equals(obj); 
    ChatReplyRespInfo other = (ChatReplyRespInfo)obj;
    if (getReplyMsgId() != other
      .getReplyMsgId())
      return false; 
    if (getReplyId() != other
      .getReplyId())
      return false; 
    if (hasReplyText() != other.hasReplyText())
      return false; 
    if (hasReplyText() && 
      
      !getReplyText().equals(other.getReplyText()))
      return false; 
    if (getReplyUid() != other
      .getReplyUid())
      return false; 
    if (!getReplyWebcastUid().equals(other.getReplyWebcastUid()))
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
        getReplyMsgId());
    hash = 37 * hash + 2;
    hash = 53 * hash + Internal.hashLong(
        getReplyId());
    if (hasReplyText()) {
      hash = 37 * hash + 3;
      hash = 53 * hash + getReplyText().hashCode();
    } 
    hash = 37 * hash + 4;
    hash = 53 * hash + Internal.hashLong(
        getReplyUid());
    hash = 37 * hash + 5;
    hash = 53 * hash + getReplyWebcastUid().hashCode();
    hash = 29 * hash + getUnknownFields().hashCode();
    this.memoizedHashCode = hash;
    return hash;
  }
  
  public static ChatReplyRespInfo parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
    return (ChatReplyRespInfo)PARSER.parseFrom(data);
  }
  
  public static ChatReplyRespInfo parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (ChatReplyRespInfo)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static ChatReplyRespInfo parseFrom(ByteString data) throws InvalidProtocolBufferException {
    return (ChatReplyRespInfo)PARSER.parseFrom(data);
  }
  
  public static ChatReplyRespInfo parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (ChatReplyRespInfo)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static ChatReplyRespInfo parseFrom(byte[] data) throws InvalidProtocolBufferException {
    return (ChatReplyRespInfo)PARSER.parseFrom(data);
  }
  
  public static ChatReplyRespInfo parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (ChatReplyRespInfo)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static ChatReplyRespInfo parseFrom(InputStream input) throws IOException {
    return 
      (ChatReplyRespInfo)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static ChatReplyRespInfo parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (ChatReplyRespInfo)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static ChatReplyRespInfo parseDelimitedFrom(InputStream input) throws IOException {
    return 
      (ChatReplyRespInfo)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
  }
  
  public static ChatReplyRespInfo parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (ChatReplyRespInfo)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static ChatReplyRespInfo parseFrom(CodedInputStream input) throws IOException {
    return 
      (ChatReplyRespInfo)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static ChatReplyRespInfo parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (ChatReplyRespInfo)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public Builder newBuilderForType() {
    return newBuilder();
  }
  
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  
  public static Builder newBuilder(ChatReplyRespInfo prototype) {
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
  
  public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ChatReplyRespInfoOrBuilder {
    private int bitField0_;
    
    private long replyMsgId_;
    
    private long replyId_;
    
    private Text replyText_;
    
    private SingleFieldBuilderV3<Text, Text.Builder, TextOrBuilder> replyTextBuilder_;
    
    private long replyUid_;
    
    private Object replyWebcastUid_;
    
    public static final Descriptors.Descriptor getDescriptor() {
      return Douyin.internal_static_ChatReplyRespInfo_descriptor;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return Douyin.internal_static_ChatReplyRespInfo_fieldAccessorTable
        .ensureFieldAccessorsInitialized(ChatReplyRespInfo.class, Builder.class);
    }
    
    private Builder() {
      this.replyWebcastUid_ = "";
      maybeForceBuilderInitialization();
    }
    
    private Builder(GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      this.replyWebcastUid_ = "";
      maybeForceBuilderInitialization();
    }
    
    private void maybeForceBuilderInitialization() {
      if (ChatReplyRespInfo.alwaysUseFieldBuilders)
        getReplyTextFieldBuilder(); 
    }
    
    public Builder clear() {
      super.clear();
      this.bitField0_ = 0;
      this.replyMsgId_ = 0L;
      this.replyId_ = 0L;
      this.replyText_ = null;
      if (this.replyTextBuilder_ != null) {
        this.replyTextBuilder_.dispose();
        this.replyTextBuilder_ = null;
      } 
      this.replyUid_ = 0L;
      this.replyWebcastUid_ = "";
      return this;
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
      return Douyin.internal_static_ChatReplyRespInfo_descriptor;
    }
    
    public ChatReplyRespInfo getDefaultInstanceForType() {
      return ChatReplyRespInfo.getDefaultInstance();
    }
    
    public ChatReplyRespInfo build() {
      ChatReplyRespInfo result = buildPartial();
      if (!result.isInitialized())
        throw newUninitializedMessageException(result); 
      return result;
    }
    
    public ChatReplyRespInfo buildPartial() {
      ChatReplyRespInfo result = new ChatReplyRespInfo(this);
      if (this.bitField0_ != 0)
        buildPartial0(result); 
      onBuilt();
      return result;
    }
    
    private void buildPartial0(ChatReplyRespInfo result) {
      int from_bitField0_ = this.bitField0_;
      if ((from_bitField0_ & 0x1) != 0)
        result.replyMsgId_ = this.replyMsgId_; 
      if ((from_bitField0_ & 0x2) != 0)
        result.replyId_ = this.replyId_; 
      int to_bitField0_ = 0;
      if ((from_bitField0_ & 0x4) != 0) {
        result.replyText_ = (this.replyTextBuilder_ == null) ? this.replyText_ : (Text)this.replyTextBuilder_.build();
        to_bitField0_ |= 0x1;
      } 
      if ((from_bitField0_ & 0x8) != 0)
        result.replyUid_ = this.replyUid_; 
      if ((from_bitField0_ & 0x10) != 0)
        result.replyWebcastUid_ = this.replyWebcastUid_; 
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
      if (other instanceof ChatReplyRespInfo)
        return mergeFrom((ChatReplyRespInfo)other); 
      super.mergeFrom(other);
      return this;
    }
    
    public Builder mergeFrom(ChatReplyRespInfo other) {
      if (other == ChatReplyRespInfo.getDefaultInstance())
        return this; 
      if (other.getReplyMsgId() != 0L)
        setReplyMsgId(other.getReplyMsgId()); 
      if (other.getReplyId() != 0L)
        setReplyId(other.getReplyId()); 
      if (other.hasReplyText())
        mergeReplyText(other.getReplyText()); 
      if (other.getReplyUid() != 0L)
        setReplyUid(other.getReplyUid()); 
      if (!other.getReplyWebcastUid().isEmpty()) {
        this.replyWebcastUid_ = other.replyWebcastUid_;
        this.bitField0_ |= 0x10;
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
            case 8:
              this.replyMsgId_ = input.readUInt64();
              this.bitField0_ |= 0x1;
              continue;
            case 16:
              this.replyId_ = input.readUInt64();
              this.bitField0_ |= 0x2;
              continue;
            case 26:
              input.readMessage((MessageLite.Builder)getReplyTextFieldBuilder().getBuilder(), extensionRegistry);
              this.bitField0_ |= 0x4;
              continue;
            case 32:
              this.replyUid_ = input.readUInt64();
              this.bitField0_ |= 0x8;
              continue;
            case 42:
              this.replyWebcastUid_ = input.readStringRequireUtf8();
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
    
    public long getReplyMsgId() {
      return this.replyMsgId_;
    }
    
    public Builder setReplyMsgId(long value) {
      this.replyMsgId_ = value;
      this.bitField0_ |= 0x1;
      onChanged();
      return this;
    }
    
    public Builder clearReplyMsgId() {
      this.bitField0_ &= 0xFFFFFFFE;
      this.replyMsgId_ = 0L;
      onChanged();
      return this;
    }
    
    public long getReplyId() {
      return this.replyId_;
    }
    
    public Builder setReplyId(long value) {
      this.replyId_ = value;
      this.bitField0_ |= 0x2;
      onChanged();
      return this;
    }
    
    public Builder clearReplyId() {
      this.bitField0_ &= 0xFFFFFFFD;
      this.replyId_ = 0L;
      onChanged();
      return this;
    }
    
    public boolean hasReplyText() {
      return ((this.bitField0_ & 0x4) != 0);
    }
    
    public Text getReplyText() {
      if (this.replyTextBuilder_ == null)
        return (this.replyText_ == null) ? Text.getDefaultInstance() : this.replyText_; 
      return (Text)this.replyTextBuilder_.getMessage();
    }
    
    public Builder setReplyText(Text value) {
      if (this.replyTextBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        this.replyText_ = value;
      } else {
        this.replyTextBuilder_.setMessage((AbstractMessage)value);
      } 
      this.bitField0_ |= 0x4;
      onChanged();
      return this;
    }
    
    public Builder setReplyText(Text.Builder builderForValue) {
      if (this.replyTextBuilder_ == null) {
        this.replyText_ = builderForValue.build();
      } else {
        this.replyTextBuilder_.setMessage((AbstractMessage)builderForValue.build());
      } 
      this.bitField0_ |= 0x4;
      onChanged();
      return this;
    }
    
    public Builder mergeReplyText(Text value) {
      if (this.replyTextBuilder_ == null) {
        if ((this.bitField0_ & 0x4) != 0 && this.replyText_ != null && this.replyText_ != Text.getDefaultInstance()) {
          getReplyTextBuilder().mergeFrom(value);
        } else {
          this.replyText_ = value;
        } 
      } else {
        this.replyTextBuilder_.mergeFrom((AbstractMessage)value);
      } 
      if (this.replyText_ != null) {
        this.bitField0_ |= 0x4;
        onChanged();
      } 
      return this;
    }
    
    public Builder clearReplyText() {
      this.bitField0_ &= 0xFFFFFFFB;
      this.replyText_ = null;
      if (this.replyTextBuilder_ != null) {
        this.replyTextBuilder_.dispose();
        this.replyTextBuilder_ = null;
      } 
      onChanged();
      return this;
    }
    
    public Text.Builder getReplyTextBuilder() {
      this.bitField0_ |= 0x4;
      onChanged();
      return (Text.Builder)getReplyTextFieldBuilder().getBuilder();
    }
    
    public TextOrBuilder getReplyTextOrBuilder() {
      if (this.replyTextBuilder_ != null)
        return (TextOrBuilder)this.replyTextBuilder_.getMessageOrBuilder(); 
      return (this.replyText_ == null) ? Text.getDefaultInstance() : this.replyText_;
    }
    
    private SingleFieldBuilderV3<Text, Text.Builder, TextOrBuilder> getReplyTextFieldBuilder() {
      if (this.replyTextBuilder_ == null) {
        this.replyTextBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getReplyText(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.replyText_ = null;
      } 
      return this.replyTextBuilder_;
    }
    
    public long getReplyUid() {
      return this.replyUid_;
    }
    
    public Builder setReplyUid(long value) {
      this.replyUid_ = value;
      this.bitField0_ |= 0x8;
      onChanged();
      return this;
    }
    
    public Builder clearReplyUid() {
      this.bitField0_ &= 0xFFFFFFF7;
      this.replyUid_ = 0L;
      onChanged();
      return this;
    }
    
    public String getReplyWebcastUid() {
      Object ref = this.replyWebcastUid_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.replyWebcastUid_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getReplyWebcastUidBytes() {
      Object ref = this.replyWebcastUid_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.replyWebcastUid_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setReplyWebcastUid(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.replyWebcastUid_ = value;
      this.bitField0_ |= 0x10;
      onChanged();
      return this;
    }
    
    public Builder clearReplyWebcastUid() {
      this.replyWebcastUid_ = ChatReplyRespInfo.getDefaultInstance().getReplyWebcastUid();
      this.bitField0_ &= 0xFFFFFFEF;
      onChanged();
      return this;
    }
    
    public Builder setReplyWebcastUidBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      ChatReplyRespInfo.checkByteStringIsUtf8(value);
      this.replyWebcastUid_ = value;
      this.bitField0_ |= 0x10;
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
  
  private static final ChatReplyRespInfo DEFAULT_INSTANCE = new ChatReplyRespInfo();
  
  public static ChatReplyRespInfo getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }
  
  private static final Parser<ChatReplyRespInfo> PARSER = (Parser<ChatReplyRespInfo>)new AbstractParser<ChatReplyRespInfo>() {
      public ChatReplyRespInfo parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        ChatReplyRespInfo.Builder builder = ChatReplyRespInfo.newBuilder();
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
  
  public static Parser<ChatReplyRespInfo> parser() {
    return PARSER;
  }
  
  public Parser<ChatReplyRespInfo> getParserForType() {
    return PARSER;
  }
  
  public ChatReplyRespInfo getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }
}
