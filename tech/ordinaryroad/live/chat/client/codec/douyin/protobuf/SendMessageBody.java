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
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class SendMessageBody extends GeneratedMessageV3 implements SendMessageBodyOrBuilder {
  private static final long serialVersionUID = 0L;
  
  public static final int CONVERSATIONID_FIELD_NUMBER = 1;
  
  private volatile Object conversationId_;
  
  public static final int CONVERSATIONTYPE_FIELD_NUMBER = 2;
  
  private int conversationType_;
  
  public static final int CONVERSATIONSHORTID_FIELD_NUMBER = 3;
  
  private long conversationShortId_;
  
  public static final int CONTENT_FIELD_NUMBER = 4;
  
  private volatile Object content_;
  
  public static final int EXT_FIELD_NUMBER = 5;
  
  private List<ExtList> ext_;
  
  public static final int MESSAGETYPE_FIELD_NUMBER = 6;
  
  private int messageType_;
  
  public static final int TICKET_FIELD_NUMBER = 7;
  
  private volatile Object ticket_;
  
  public static final int CLIENTMESSAGEID_FIELD_NUMBER = 8;
  
  private volatile Object clientMessageId_;
  
  private byte memoizedIsInitialized;
  
  private SendMessageBody(GeneratedMessageV3.Builder<?> builder) {
    super(builder);
    this.conversationId_ = "";
    this.conversationType_ = 0;
    this.conversationShortId_ = 0L;
    this.content_ = "";
    this.messageType_ = 0;
    this.ticket_ = "";
    this.clientMessageId_ = "";
    this.memoizedIsInitialized = -1;
  }
  
  private SendMessageBody() {
    this.conversationId_ = "";
    this.conversationType_ = 0;
    this.conversationShortId_ = 0L;
    this.content_ = "";
    this.messageType_ = 0;
    this.ticket_ = "";
    this.clientMessageId_ = "";
    this.memoizedIsInitialized = -1;
    this.conversationId_ = "";
    this.content_ = "";
    this.ext_ = Collections.emptyList();
    this.ticket_ = "";
    this.clientMessageId_ = "";
  }
  
  protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
    return new SendMessageBody();
  }
  
  public static final Descriptors.Descriptor getDescriptor() {
    return Douyin.internal_static_SendMessageBody_descriptor;
  }
  
  protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
    return Douyin.internal_static_SendMessageBody_fieldAccessorTable.ensureFieldAccessorsInitialized(SendMessageBody.class, Builder.class);
  }
  
  public String getConversationId() {
    Object ref = this.conversationId_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.conversationId_ = s;
    return s;
  }
  
  public ByteString getConversationIdBytes() {
    Object ref = this.conversationId_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.conversationId_ = b;
      return b;
    } 
    return (ByteString)ref;
  }
  
  public int getConversationType() {
    return this.conversationType_;
  }
  
  public long getConversationShortId() {
    return this.conversationShortId_;
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
  
  public List<ExtList> getExtList() {
    return this.ext_;
  }
  
  public List<? extends ExtListOrBuilder> getExtOrBuilderList() {
    return (List)this.ext_;
  }
  
  public int getExtCount() {
    return this.ext_.size();
  }
  
  public ExtList getExt(int index) {
    return this.ext_.get(index);
  }
  
  public ExtListOrBuilder getExtOrBuilder(int index) {
    return this.ext_.get(index);
  }
  
  public int getMessageType() {
    return this.messageType_;
  }
  
  public String getTicket() {
    Object ref = this.ticket_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.ticket_ = s;
    return s;
  }
  
  public ByteString getTicketBytes() {
    Object ref = this.ticket_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.ticket_ = b;
      return b;
    } 
    return (ByteString)ref;
  }
  
  public String getClientMessageId() {
    Object ref = this.clientMessageId_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.clientMessageId_ = s;
    return s;
  }
  
  public ByteString getClientMessageIdBytes() {
    Object ref = this.clientMessageId_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.clientMessageId_ = b;
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
    if (!GeneratedMessageV3.isStringEmpty(this.conversationId_))
      GeneratedMessageV3.writeString(output, 1, this.conversationId_); 
    if (this.conversationType_ != 0)
      output.writeUInt32(2, this.conversationType_); 
    if (this.conversationShortId_ != 0L)
      output.writeUInt64(3, this.conversationShortId_); 
    if (!GeneratedMessageV3.isStringEmpty(this.content_))
      GeneratedMessageV3.writeString(output, 4, this.content_); 
    for (int i = 0; i < this.ext_.size(); i++)
      output.writeMessage(5, (MessageLite)this.ext_.get(i)); 
    if (this.messageType_ != 0)
      output.writeUInt32(6, this.messageType_); 
    if (!GeneratedMessageV3.isStringEmpty(this.ticket_))
      GeneratedMessageV3.writeString(output, 7, this.ticket_); 
    if (!GeneratedMessageV3.isStringEmpty(this.clientMessageId_))
      GeneratedMessageV3.writeString(output, 8, this.clientMessageId_); 
    getUnknownFields().writeTo(output);
  }
  
  public int getSerializedSize() {
    int size = this.memoizedSize;
    if (size != -1)
      return size; 
    size = 0;
    if (!GeneratedMessageV3.isStringEmpty(this.conversationId_))
      size += GeneratedMessageV3.computeStringSize(1, this.conversationId_); 
    if (this.conversationType_ != 0)
      size += 
        CodedOutputStream.computeUInt32Size(2, this.conversationType_); 
    if (this.conversationShortId_ != 0L)
      size += 
        CodedOutputStream.computeUInt64Size(3, this.conversationShortId_); 
    if (!GeneratedMessageV3.isStringEmpty(this.content_))
      size += GeneratedMessageV3.computeStringSize(4, this.content_); 
    for (int i = 0; i < this.ext_.size(); i++)
      size += 
        CodedOutputStream.computeMessageSize(5, (MessageLite)this.ext_.get(i)); 
    if (this.messageType_ != 0)
      size += 
        CodedOutputStream.computeUInt32Size(6, this.messageType_); 
    if (!GeneratedMessageV3.isStringEmpty(this.ticket_))
      size += GeneratedMessageV3.computeStringSize(7, this.ticket_); 
    if (!GeneratedMessageV3.isStringEmpty(this.clientMessageId_))
      size += GeneratedMessageV3.computeStringSize(8, this.clientMessageId_); 
    size += getUnknownFields().getSerializedSize();
    this.memoizedSize = size;
    return size;
  }
  
  public boolean equals(Object obj) {
    if (obj == this)
      return true; 
    if (!(obj instanceof SendMessageBody))
      return super.equals(obj); 
    SendMessageBody other = (SendMessageBody)obj;
    if (!getConversationId().equals(other.getConversationId()))
      return false; 
    if (getConversationType() != other
      .getConversationType())
      return false; 
    if (getConversationShortId() != other
      .getConversationShortId())
      return false; 
    if (!getContent().equals(other.getContent()))
      return false; 
    if (!getExtList().equals(other.getExtList()))
      return false; 
    if (getMessageType() != other
      .getMessageType())
      return false; 
    if (!getTicket().equals(other.getTicket()))
      return false; 
    if (!getClientMessageId().equals(other.getClientMessageId()))
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
    hash = 53 * hash + getConversationId().hashCode();
    hash = 37 * hash + 2;
    hash = 53 * hash + getConversationType();
    hash = 37 * hash + 3;
    hash = 53 * hash + Internal.hashLong(
        getConversationShortId());
    hash = 37 * hash + 4;
    hash = 53 * hash + getContent().hashCode();
    if (getExtCount() > 0) {
      hash = 37 * hash + 5;
      hash = 53 * hash + getExtList().hashCode();
    } 
    hash = 37 * hash + 6;
    hash = 53 * hash + getMessageType();
    hash = 37 * hash + 7;
    hash = 53 * hash + getTicket().hashCode();
    hash = 37 * hash + 8;
    hash = 53 * hash + getClientMessageId().hashCode();
    hash = 29 * hash + getUnknownFields().hashCode();
    this.memoizedHashCode = hash;
    return hash;
  }
  
  public static SendMessageBody parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
    return (SendMessageBody)PARSER.parseFrom(data);
  }
  
  public static SendMessageBody parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (SendMessageBody)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static SendMessageBody parseFrom(ByteString data) throws InvalidProtocolBufferException {
    return (SendMessageBody)PARSER.parseFrom(data);
  }
  
  public static SendMessageBody parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (SendMessageBody)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static SendMessageBody parseFrom(byte[] data) throws InvalidProtocolBufferException {
    return (SendMessageBody)PARSER.parseFrom(data);
  }
  
  public static SendMessageBody parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (SendMessageBody)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static SendMessageBody parseFrom(InputStream input) throws IOException {
    return 
      (SendMessageBody)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static SendMessageBody parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (SendMessageBody)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static SendMessageBody parseDelimitedFrom(InputStream input) throws IOException {
    return 
      (SendMessageBody)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
  }
  
  public static SendMessageBody parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (SendMessageBody)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static SendMessageBody parseFrom(CodedInputStream input) throws IOException {
    return 
      (SendMessageBody)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static SendMessageBody parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (SendMessageBody)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public Builder newBuilderForType() {
    return newBuilder();
  }
  
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  
  public static Builder newBuilder(SendMessageBody prototype) {
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
  
  public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements SendMessageBodyOrBuilder {
    private int bitField0_;
    
    private Object conversationId_;
    
    private int conversationType_;
    
    private long conversationShortId_;
    
    private Object content_;
    
    private List<ExtList> ext_;
    
    private RepeatedFieldBuilderV3<ExtList, ExtList.Builder, ExtListOrBuilder> extBuilder_;
    
    private int messageType_;
    
    private Object ticket_;
    
    private Object clientMessageId_;
    
    public static final Descriptors.Descriptor getDescriptor() {
      return Douyin.internal_static_SendMessageBody_descriptor;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return Douyin.internal_static_SendMessageBody_fieldAccessorTable
        .ensureFieldAccessorsInitialized(SendMessageBody.class, Builder.class);
    }
    
    private Builder() {
      this.conversationId_ = "";
      this.content_ = "";
      this
        .ext_ = Collections.emptyList();
      this.ticket_ = "";
      this.clientMessageId_ = "";
    }
    
    private Builder(GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      this.conversationId_ = "";
      this.content_ = "";
      this.ext_ = Collections.emptyList();
      this.ticket_ = "";
      this.clientMessageId_ = "";
    }
    
    public Builder clear() {
      super.clear();
      this.bitField0_ = 0;
      this.conversationId_ = "";
      this.conversationType_ = 0;
      this.conversationShortId_ = 0L;
      this.content_ = "";
      if (this.extBuilder_ == null) {
        this.ext_ = Collections.emptyList();
      } else {
        this.ext_ = null;
        this.extBuilder_.clear();
      } 
      this.bitField0_ &= 0xFFFFFFEF;
      this.messageType_ = 0;
      this.ticket_ = "";
      this.clientMessageId_ = "";
      return this;
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
      return Douyin.internal_static_SendMessageBody_descriptor;
    }
    
    public SendMessageBody getDefaultInstanceForType() {
      return SendMessageBody.getDefaultInstance();
    }
    
    public SendMessageBody build() {
      SendMessageBody result = buildPartial();
      if (!result.isInitialized())
        throw newUninitializedMessageException(result); 
      return result;
    }
    
    public SendMessageBody buildPartial() {
      SendMessageBody result = new SendMessageBody(this);
      buildPartialRepeatedFields(result);
      if (this.bitField0_ != 0)
        buildPartial0(result); 
      onBuilt();
      return result;
    }
    
    private void buildPartialRepeatedFields(SendMessageBody result) {
      if (this.extBuilder_ == null) {
        if ((this.bitField0_ & 0x10) != 0) {
          this.ext_ = Collections.unmodifiableList(this.ext_);
          this.bitField0_ &= 0xFFFFFFEF;
        } 
        result.ext_ = this.ext_;
      } else {
        result.ext_ = this.extBuilder_.build();
      } 
    }
    
    private void buildPartial0(SendMessageBody result) {
      int from_bitField0_ = this.bitField0_;
      if ((from_bitField0_ & 0x1) != 0)
        result.conversationId_ = this.conversationId_; 
      if ((from_bitField0_ & 0x2) != 0)
        result.conversationType_ = this.conversationType_; 
      if ((from_bitField0_ & 0x4) != 0)
        result.conversationShortId_ = this.conversationShortId_; 
      if ((from_bitField0_ & 0x8) != 0)
        result.content_ = this.content_; 
      if ((from_bitField0_ & 0x20) != 0)
        result.messageType_ = this.messageType_; 
      if ((from_bitField0_ & 0x40) != 0)
        result.ticket_ = this.ticket_; 
      if ((from_bitField0_ & 0x80) != 0)
        result.clientMessageId_ = this.clientMessageId_; 
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
      if (other instanceof SendMessageBody)
        return mergeFrom((SendMessageBody)other); 
      super.mergeFrom(other);
      return this;
    }
    
    public Builder mergeFrom(SendMessageBody other) {
      if (other == SendMessageBody.getDefaultInstance())
        return this; 
      if (!other.getConversationId().isEmpty()) {
        this.conversationId_ = other.conversationId_;
        this.bitField0_ |= 0x1;
        onChanged();
      } 
      if (other.getConversationType() != 0)
        setConversationType(other.getConversationType()); 
      if (other.getConversationShortId() != 0L)
        setConversationShortId(other.getConversationShortId()); 
      if (!other.getContent().isEmpty()) {
        this.content_ = other.content_;
        this.bitField0_ |= 0x8;
        onChanged();
      } 
      if (this.extBuilder_ == null) {
        if (!other.ext_.isEmpty()) {
          if (this.ext_.isEmpty()) {
            this.ext_ = other.ext_;
            this.bitField0_ &= 0xFFFFFFEF;
          } else {
            ensureExtIsMutable();
            this.ext_.addAll(other.ext_);
          } 
          onChanged();
        } 
      } else if (!other.ext_.isEmpty()) {
        if (this.extBuilder_.isEmpty()) {
          this.extBuilder_.dispose();
          this.extBuilder_ = null;
          this.ext_ = other.ext_;
          this.bitField0_ &= 0xFFFFFFEF;
          this.extBuilder_ = SendMessageBody.alwaysUseFieldBuilders ? getExtFieldBuilder() : null;
        } else {
          this.extBuilder_.addAllMessages(other.ext_);
        } 
      } 
      if (other.getMessageType() != 0)
        setMessageType(other.getMessageType()); 
      if (!other.getTicket().isEmpty()) {
        this.ticket_ = other.ticket_;
        this.bitField0_ |= 0x40;
        onChanged();
      } 
      if (!other.getClientMessageId().isEmpty()) {
        this.clientMessageId_ = other.clientMessageId_;
        this.bitField0_ |= 0x80;
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
          ExtList m;
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              continue;
            case 10:
              this.conversationId_ = input.readStringRequireUtf8();
              this.bitField0_ |= 0x1;
              continue;
            case 16:
              this.conversationType_ = input.readUInt32();
              this.bitField0_ |= 0x2;
              continue;
            case 24:
              this.conversationShortId_ = input.readUInt64();
              this.bitField0_ |= 0x4;
              continue;
            case 34:
              this.content_ = input.readStringRequireUtf8();
              this.bitField0_ |= 0x8;
              continue;
            case 42:
              m = (ExtList)input.readMessage(ExtList.parser(), extensionRegistry);
              if (this.extBuilder_ == null) {
                ensureExtIsMutable();
                this.ext_.add(m);
                continue;
              } 
              this.extBuilder_.addMessage((AbstractMessage)m);
              continue;
            case 48:
              this.messageType_ = input.readUInt32();
              this.bitField0_ |= 0x20;
              continue;
            case 58:
              this.ticket_ = input.readStringRequireUtf8();
              this.bitField0_ |= 0x40;
              continue;
            case 66:
              this.clientMessageId_ = input.readStringRequireUtf8();
              this.bitField0_ |= 0x80;
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
    
    public String getConversationId() {
      Object ref = this.conversationId_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.conversationId_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getConversationIdBytes() {
      Object ref = this.conversationId_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.conversationId_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setConversationId(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.conversationId_ = value;
      this.bitField0_ |= 0x1;
      onChanged();
      return this;
    }
    
    public Builder clearConversationId() {
      this.conversationId_ = SendMessageBody.getDefaultInstance().getConversationId();
      this.bitField0_ &= 0xFFFFFFFE;
      onChanged();
      return this;
    }
    
    public Builder setConversationIdBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      SendMessageBody.checkByteStringIsUtf8(value);
      this.conversationId_ = value;
      this.bitField0_ |= 0x1;
      onChanged();
      return this;
    }
    
    public int getConversationType() {
      return this.conversationType_;
    }
    
    public Builder setConversationType(int value) {
      this.conversationType_ = value;
      this.bitField0_ |= 0x2;
      onChanged();
      return this;
    }
    
    public Builder clearConversationType() {
      this.bitField0_ &= 0xFFFFFFFD;
      this.conversationType_ = 0;
      onChanged();
      return this;
    }
    
    public long getConversationShortId() {
      return this.conversationShortId_;
    }
    
    public Builder setConversationShortId(long value) {
      this.conversationShortId_ = value;
      this.bitField0_ |= 0x4;
      onChanged();
      return this;
    }
    
    public Builder clearConversationShortId() {
      this.bitField0_ &= 0xFFFFFFFB;
      this.conversationShortId_ = 0L;
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
      this.bitField0_ |= 0x8;
      onChanged();
      return this;
    }
    
    public Builder clearContent() {
      this.content_ = SendMessageBody.getDefaultInstance().getContent();
      this.bitField0_ &= 0xFFFFFFF7;
      onChanged();
      return this;
    }
    
    public Builder setContentBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      SendMessageBody.checkByteStringIsUtf8(value);
      this.content_ = value;
      this.bitField0_ |= 0x8;
      onChanged();
      return this;
    }
    
    private void ensureExtIsMutable() {
      if ((this.bitField0_ & 0x10) == 0) {
        this.ext_ = new ArrayList<>(this.ext_);
        this.bitField0_ |= 0x10;
      } 
    }
    
    public List<ExtList> getExtList() {
      if (this.extBuilder_ == null)
        return Collections.unmodifiableList(this.ext_); 
      return this.extBuilder_.getMessageList();
    }
    
    public int getExtCount() {
      if (this.extBuilder_ == null)
        return this.ext_.size(); 
      return this.extBuilder_.getCount();
    }
    
    public ExtList getExt(int index) {
      if (this.extBuilder_ == null)
        return this.ext_.get(index); 
      return (ExtList)this.extBuilder_.getMessage(index);
    }
    
    public Builder setExt(int index, ExtList value) {
      if (this.extBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        ensureExtIsMutable();
        this.ext_.set(index, value);
        onChanged();
      } else {
        this.extBuilder_.setMessage(index, (AbstractMessage)value);
      } 
      return this;
    }
    
    public Builder setExt(int index, ExtList.Builder builderForValue) {
      if (this.extBuilder_ == null) {
        ensureExtIsMutable();
        this.ext_.set(index, builderForValue.build());
        onChanged();
      } else {
        this.extBuilder_.setMessage(index, (AbstractMessage)builderForValue.build());
      } 
      return this;
    }
    
    public Builder addExt(ExtList value) {
      if (this.extBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        ensureExtIsMutable();
        this.ext_.add(value);
        onChanged();
      } else {
        this.extBuilder_.addMessage((AbstractMessage)value);
      } 
      return this;
    }
    
    public Builder addExt(int index, ExtList value) {
      if (this.extBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        ensureExtIsMutable();
        this.ext_.add(index, value);
        onChanged();
      } else {
        this.extBuilder_.addMessage(index, (AbstractMessage)value);
      } 
      return this;
    }
    
    public Builder addExt(ExtList.Builder builderForValue) {
      if (this.extBuilder_ == null) {
        ensureExtIsMutable();
        this.ext_.add(builderForValue.build());
        onChanged();
      } else {
        this.extBuilder_.addMessage((AbstractMessage)builderForValue.build());
      } 
      return this;
    }
    
    public Builder addExt(int index, ExtList.Builder builderForValue) {
      if (this.extBuilder_ == null) {
        ensureExtIsMutable();
        this.ext_.add(index, builderForValue.build());
        onChanged();
      } else {
        this.extBuilder_.addMessage(index, (AbstractMessage)builderForValue.build());
      } 
      return this;
    }
    
    public Builder addAllExt(Iterable<? extends ExtList> values) {
      if (this.extBuilder_ == null) {
        ensureExtIsMutable();
        AbstractMessageLite.Builder.addAll(values, this.ext_);
        onChanged();
      } else {
        this.extBuilder_.addAllMessages(values);
      } 
      return this;
    }
    
    public Builder clearExt() {
      if (this.extBuilder_ == null) {
        this.ext_ = Collections.emptyList();
        this.bitField0_ &= 0xFFFFFFEF;
        onChanged();
      } else {
        this.extBuilder_.clear();
      } 
      return this;
    }
    
    public Builder removeExt(int index) {
      if (this.extBuilder_ == null) {
        ensureExtIsMutable();
        this.ext_.remove(index);
        onChanged();
      } else {
        this.extBuilder_.remove(index);
      } 
      return this;
    }
    
    public ExtList.Builder getExtBuilder(int index) {
      return (ExtList.Builder)getExtFieldBuilder().getBuilder(index);
    }
    
    public ExtListOrBuilder getExtOrBuilder(int index) {
      if (this.extBuilder_ == null)
        return this.ext_.get(index); 
      return (ExtListOrBuilder)this.extBuilder_.getMessageOrBuilder(index);
    }
    
    public List<? extends ExtListOrBuilder> getExtOrBuilderList() {
      if (this.extBuilder_ != null)
        return this.extBuilder_.getMessageOrBuilderList(); 
      return Collections.unmodifiableList((List)this.ext_);
    }
    
    public ExtList.Builder addExtBuilder() {
      return (ExtList.Builder)getExtFieldBuilder().addBuilder((AbstractMessage)ExtList.getDefaultInstance());
    }
    
    public ExtList.Builder addExtBuilder(int index) {
      return (ExtList.Builder)getExtFieldBuilder().addBuilder(index, (AbstractMessage)ExtList.getDefaultInstance());
    }
    
    public List<ExtList.Builder> getExtBuilderList() {
      return getExtFieldBuilder().getBuilderList();
    }
    
    private RepeatedFieldBuilderV3<ExtList, ExtList.Builder, ExtListOrBuilder> getExtFieldBuilder() {
      if (this.extBuilder_ == null) {
        this.extBuilder_ = new RepeatedFieldBuilderV3(this.ext_, ((this.bitField0_ & 0x10) != 0), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.ext_ = null;
      } 
      return this.extBuilder_;
    }
    
    public int getMessageType() {
      return this.messageType_;
    }
    
    public Builder setMessageType(int value) {
      this.messageType_ = value;
      this.bitField0_ |= 0x20;
      onChanged();
      return this;
    }
    
    public Builder clearMessageType() {
      this.bitField0_ &= 0xFFFFFFDF;
      this.messageType_ = 0;
      onChanged();
      return this;
    }
    
    public String getTicket() {
      Object ref = this.ticket_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.ticket_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getTicketBytes() {
      Object ref = this.ticket_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.ticket_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setTicket(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.ticket_ = value;
      this.bitField0_ |= 0x40;
      onChanged();
      return this;
    }
    
    public Builder clearTicket() {
      this.ticket_ = SendMessageBody.getDefaultInstance().getTicket();
      this.bitField0_ &= 0xFFFFFFBF;
      onChanged();
      return this;
    }
    
    public Builder setTicketBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      SendMessageBody.checkByteStringIsUtf8(value);
      this.ticket_ = value;
      this.bitField0_ |= 0x40;
      onChanged();
      return this;
    }
    
    public String getClientMessageId() {
      Object ref = this.clientMessageId_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.clientMessageId_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getClientMessageIdBytes() {
      Object ref = this.clientMessageId_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.clientMessageId_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setClientMessageId(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.clientMessageId_ = value;
      this.bitField0_ |= 0x80;
      onChanged();
      return this;
    }
    
    public Builder clearClientMessageId() {
      this.clientMessageId_ = SendMessageBody.getDefaultInstance().getClientMessageId();
      this.bitField0_ &= 0xFFFFFF7F;
      onChanged();
      return this;
    }
    
    public Builder setClientMessageIdBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      SendMessageBody.checkByteStringIsUtf8(value);
      this.clientMessageId_ = value;
      this.bitField0_ |= 0x80;
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
  
  private static final SendMessageBody DEFAULT_INSTANCE = new SendMessageBody();
  
  public static SendMessageBody getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }
  
  private static final Parser<SendMessageBody> PARSER = (Parser<SendMessageBody>)new AbstractParser<SendMessageBody>() {
      public SendMessageBody parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        SendMessageBody.Builder builder = SendMessageBody.newBuilder();
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
  
  public static Parser<SendMessageBody> parser() {
    return PARSER;
  }
  
  public Parser<SendMessageBody> getParserForType() {
    return PARSER;
  }
  
  public SendMessageBody getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }
}
