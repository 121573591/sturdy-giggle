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
import com.google.protobuf.MessageLite;
import com.google.protobuf.Parser;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class Message extends GeneratedMessageV3 implements MessageOrBuilder {
  private static final long serialVersionUID = 0L;
  
  public static final int METHOD_FIELD_NUMBER = 1;
  
  private volatile Object method_;
  
  public static final int PAYLOAD_FIELD_NUMBER = 2;
  
  private ByteString payload_;
  
  public static final int MSGID_FIELD_NUMBER = 3;
  
  private long msgId_;
  
  public static final int MSGTYPE_FIELD_NUMBER = 4;
  
  private int msgType_;
  
  public static final int OFFSET_FIELD_NUMBER = 5;
  
  private long offset_;
  
  public static final int NEEDWRDSSTORE_FIELD_NUMBER = 6;
  
  private boolean needWrdsStore_;
  
  public static final int WRDSVERSION_FIELD_NUMBER = 7;
  
  private long wrdsVersion_;
  
  public static final int WRDSSUBKEY_FIELD_NUMBER = 8;
  
  private volatile Object wrdsSubKey_;
  
  private byte memoizedIsInitialized;
  
  private Message(GeneratedMessageV3.Builder<?> builder) {
    super(builder);
    this.method_ = "";
    this.payload_ = ByteString.EMPTY;
    this.msgId_ = 0L;
    this.msgType_ = 0;
    this.offset_ = 0L;
    this.needWrdsStore_ = false;
    this.wrdsVersion_ = 0L;
    this.wrdsSubKey_ = "";
    this.memoizedIsInitialized = -1;
  }
  
  private Message() {
    this.method_ = "";
    this.payload_ = ByteString.EMPTY;
    this.msgId_ = 0L;
    this.msgType_ = 0;
    this.offset_ = 0L;
    this.needWrdsStore_ = false;
    this.wrdsVersion_ = 0L;
    this.wrdsSubKey_ = "";
    this.memoizedIsInitialized = -1;
    this.method_ = "";
    this.payload_ = ByteString.EMPTY;
    this.wrdsSubKey_ = "";
  }
  
  protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
    return new Message();
  }
  
  public static final Descriptors.Descriptor getDescriptor() {
    return Douyin.internal_static_Message_descriptor;
  }
  
  protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
    return Douyin.internal_static_Message_fieldAccessorTable.ensureFieldAccessorsInitialized(Message.class, Builder.class);
  }
  
  public String getMethod() {
    Object ref = this.method_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.method_ = s;
    return s;
  }
  
  public ByteString getMethodBytes() {
    Object ref = this.method_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.method_ = b;
      return b;
    } 
    return (ByteString)ref;
  }
  
  public ByteString getPayload() {
    return this.payload_;
  }
  
  public long getMsgId() {
    return this.msgId_;
  }
  
  public int getMsgType() {
    return this.msgType_;
  }
  
  public long getOffset() {
    return this.offset_;
  }
  
  public boolean getNeedWrdsStore() {
    return this.needWrdsStore_;
  }
  
  public long getWrdsVersion() {
    return this.wrdsVersion_;
  }
  
  public String getWrdsSubKey() {
    Object ref = this.wrdsSubKey_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.wrdsSubKey_ = s;
    return s;
  }
  
  public ByteString getWrdsSubKeyBytes() {
    Object ref = this.wrdsSubKey_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.wrdsSubKey_ = b;
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
    if (!GeneratedMessageV3.isStringEmpty(this.method_))
      GeneratedMessageV3.writeString(output, 1, this.method_); 
    if (!this.payload_.isEmpty())
      output.writeBytes(2, this.payload_); 
    if (this.msgId_ != 0L)
      output.writeInt64(3, this.msgId_); 
    if (this.msgType_ != 0)
      output.writeInt32(4, this.msgType_); 
    if (this.offset_ != 0L)
      output.writeInt64(5, this.offset_); 
    if (this.needWrdsStore_)
      output.writeBool(6, this.needWrdsStore_); 
    if (this.wrdsVersion_ != 0L)
      output.writeInt64(7, this.wrdsVersion_); 
    if (!GeneratedMessageV3.isStringEmpty(this.wrdsSubKey_))
      GeneratedMessageV3.writeString(output, 8, this.wrdsSubKey_); 
    getUnknownFields().writeTo(output);
  }
  
  public int getSerializedSize() {
    int size = this.memoizedSize;
    if (size != -1)
      return size; 
    size = 0;
    if (!GeneratedMessageV3.isStringEmpty(this.method_))
      size += GeneratedMessageV3.computeStringSize(1, this.method_); 
    if (!this.payload_.isEmpty())
      size += 
        CodedOutputStream.computeBytesSize(2, this.payload_); 
    if (this.msgId_ != 0L)
      size += 
        CodedOutputStream.computeInt64Size(3, this.msgId_); 
    if (this.msgType_ != 0)
      size += 
        CodedOutputStream.computeInt32Size(4, this.msgType_); 
    if (this.offset_ != 0L)
      size += 
        CodedOutputStream.computeInt64Size(5, this.offset_); 
    if (this.needWrdsStore_)
      size += 
        CodedOutputStream.computeBoolSize(6, this.needWrdsStore_); 
    if (this.wrdsVersion_ != 0L)
      size += 
        CodedOutputStream.computeInt64Size(7, this.wrdsVersion_); 
    if (!GeneratedMessageV3.isStringEmpty(this.wrdsSubKey_))
      size += GeneratedMessageV3.computeStringSize(8, this.wrdsSubKey_); 
    size += getUnknownFields().getSerializedSize();
    this.memoizedSize = size;
    return size;
  }
  
  public boolean equals(Object obj) {
    if (obj == this)
      return true; 
    if (!(obj instanceof Message))
      return super.equals(obj); 
    Message other = (Message)obj;
    if (!getMethod().equals(other.getMethod()))
      return false; 
    if (!getPayload().equals(other.getPayload()))
      return false; 
    if (getMsgId() != other
      .getMsgId())
      return false; 
    if (getMsgType() != other
      .getMsgType())
      return false; 
    if (getOffset() != other
      .getOffset())
      return false; 
    if (getNeedWrdsStore() != other
      .getNeedWrdsStore())
      return false; 
    if (getWrdsVersion() != other
      .getWrdsVersion())
      return false; 
    if (!getWrdsSubKey().equals(other.getWrdsSubKey()))
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
    hash = 53 * hash + getMethod().hashCode();
    hash = 37 * hash + 2;
    hash = 53 * hash + getPayload().hashCode();
    hash = 37 * hash + 3;
    hash = 53 * hash + Internal.hashLong(
        getMsgId());
    hash = 37 * hash + 4;
    hash = 53 * hash + getMsgType();
    hash = 37 * hash + 5;
    hash = 53 * hash + Internal.hashLong(
        getOffset());
    hash = 37 * hash + 6;
    hash = 53 * hash + Internal.hashBoolean(
        getNeedWrdsStore());
    hash = 37 * hash + 7;
    hash = 53 * hash + Internal.hashLong(
        getWrdsVersion());
    hash = 37 * hash + 8;
    hash = 53 * hash + getWrdsSubKey().hashCode();
    hash = 29 * hash + getUnknownFields().hashCode();
    this.memoizedHashCode = hash;
    return hash;
  }
  
  public static Message parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
    return (Message)PARSER.parseFrom(data);
  }
  
  public static Message parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (Message)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static Message parseFrom(ByteString data) throws InvalidProtocolBufferException {
    return (Message)PARSER.parseFrom(data);
  }
  
  public static Message parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (Message)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static Message parseFrom(byte[] data) throws InvalidProtocolBufferException {
    return (Message)PARSER.parseFrom(data);
  }
  
  public static Message parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (Message)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static Message parseFrom(InputStream input) throws IOException {
    return 
      (Message)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static Message parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (Message)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static Message parseDelimitedFrom(InputStream input) throws IOException {
    return 
      (Message)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
  }
  
  public static Message parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (Message)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static Message parseFrom(CodedInputStream input) throws IOException {
    return 
      (Message)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static Message parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (Message)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public Builder newBuilderForType() {
    return newBuilder();
  }
  
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  
  public static Builder newBuilder(Message prototype) {
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
  
  public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements MessageOrBuilder {
    private int bitField0_;
    
    private Object method_;
    
    private ByteString payload_;
    
    private long msgId_;
    
    private int msgType_;
    
    private long offset_;
    
    private boolean needWrdsStore_;
    
    private long wrdsVersion_;
    
    private Object wrdsSubKey_;
    
    public static final Descriptors.Descriptor getDescriptor() {
      return Douyin.internal_static_Message_descriptor;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return Douyin.internal_static_Message_fieldAccessorTable
        .ensureFieldAccessorsInitialized(Message.class, Builder.class);
    }
    
    private Builder() {
      this.method_ = "";
      this.payload_ = ByteString.EMPTY;
      this.wrdsSubKey_ = "";
    }
    
    private Builder(GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      this.method_ = "";
      this.payload_ = ByteString.EMPTY;
      this.wrdsSubKey_ = "";
    }
    
    public Builder clear() {
      super.clear();
      this.bitField0_ = 0;
      this.method_ = "";
      this.payload_ = ByteString.EMPTY;
      this.msgId_ = 0L;
      this.msgType_ = 0;
      this.offset_ = 0L;
      this.needWrdsStore_ = false;
      this.wrdsVersion_ = 0L;
      this.wrdsSubKey_ = "";
      return this;
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
      return Douyin.internal_static_Message_descriptor;
    }
    
    public Message getDefaultInstanceForType() {
      return Message.getDefaultInstance();
    }
    
    public Message build() {
      Message result = buildPartial();
      if (!result.isInitialized())
        throw newUninitializedMessageException(result); 
      return result;
    }
    
    public Message buildPartial() {
      Message result = new Message(this);
      if (this.bitField0_ != 0)
        buildPartial0(result); 
      onBuilt();
      return result;
    }
    
    private void buildPartial0(Message result) {
      int from_bitField0_ = this.bitField0_;
      if ((from_bitField0_ & 0x1) != 0)
        result.method_ = this.method_; 
      if ((from_bitField0_ & 0x2) != 0)
        result.payload_ = this.payload_; 
      if ((from_bitField0_ & 0x4) != 0)
        result.msgId_ = this.msgId_; 
      if ((from_bitField0_ & 0x8) != 0)
        result.msgType_ = this.msgType_; 
      if ((from_bitField0_ & 0x10) != 0)
        result.offset_ = this.offset_; 
      if ((from_bitField0_ & 0x20) != 0)
        result.needWrdsStore_ = this.needWrdsStore_; 
      if ((from_bitField0_ & 0x40) != 0)
        result.wrdsVersion_ = this.wrdsVersion_; 
      if ((from_bitField0_ & 0x80) != 0)
        result.wrdsSubKey_ = this.wrdsSubKey_; 
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
    
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof Message)
        return mergeFrom((Message)other); 
      super.mergeFrom(other);
      return this;
    }
    
    public Builder mergeFrom(Message other) {
      if (other == Message.getDefaultInstance())
        return this; 
      if (!other.getMethod().isEmpty()) {
        this.method_ = other.method_;
        this.bitField0_ |= 0x1;
        onChanged();
      } 
      if (other.getPayload() != ByteString.EMPTY)
        setPayload(other.getPayload()); 
      if (other.getMsgId() != 0L)
        setMsgId(other.getMsgId()); 
      if (other.getMsgType() != 0)
        setMsgType(other.getMsgType()); 
      if (other.getOffset() != 0L)
        setOffset(other.getOffset()); 
      if (other.getNeedWrdsStore())
        setNeedWrdsStore(other.getNeedWrdsStore()); 
      if (other.getWrdsVersion() != 0L)
        setWrdsVersion(other.getWrdsVersion()); 
      if (!other.getWrdsSubKey().isEmpty()) {
        this.wrdsSubKey_ = other.wrdsSubKey_;
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
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              continue;
            case 10:
              this.method_ = input.readStringRequireUtf8();
              this.bitField0_ |= 0x1;
              continue;
            case 18:
              this.payload_ = input.readBytes();
              this.bitField0_ |= 0x2;
              continue;
            case 24:
              this.msgId_ = input.readInt64();
              this.bitField0_ |= 0x4;
              continue;
            case 32:
              this.msgType_ = input.readInt32();
              this.bitField0_ |= 0x8;
              continue;
            case 40:
              this.offset_ = input.readInt64();
              this.bitField0_ |= 0x10;
              continue;
            case 48:
              this.needWrdsStore_ = input.readBool();
              this.bitField0_ |= 0x20;
              continue;
            case 56:
              this.wrdsVersion_ = input.readInt64();
              this.bitField0_ |= 0x40;
              continue;
            case 66:
              this.wrdsSubKey_ = input.readStringRequireUtf8();
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
    
    public String getMethod() {
      Object ref = this.method_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.method_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getMethodBytes() {
      Object ref = this.method_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.method_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setMethod(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.method_ = value;
      this.bitField0_ |= 0x1;
      onChanged();
      return this;
    }
    
    public Builder clearMethod() {
      this.method_ = Message.getDefaultInstance().getMethod();
      this.bitField0_ &= 0xFFFFFFFE;
      onChanged();
      return this;
    }
    
    public Builder setMethodBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      Message.checkByteStringIsUtf8(value);
      this.method_ = value;
      this.bitField0_ |= 0x1;
      onChanged();
      return this;
    }
    
    public ByteString getPayload() {
      return this.payload_;
    }
    
    public Builder setPayload(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      this.payload_ = value;
      this.bitField0_ |= 0x2;
      onChanged();
      return this;
    }
    
    public Builder clearPayload() {
      this.bitField0_ &= 0xFFFFFFFD;
      this.payload_ = Message.getDefaultInstance().getPayload();
      onChanged();
      return this;
    }
    
    public long getMsgId() {
      return this.msgId_;
    }
    
    public Builder setMsgId(long value) {
      this.msgId_ = value;
      this.bitField0_ |= 0x4;
      onChanged();
      return this;
    }
    
    public Builder clearMsgId() {
      this.bitField0_ &= 0xFFFFFFFB;
      this.msgId_ = 0L;
      onChanged();
      return this;
    }
    
    public int getMsgType() {
      return this.msgType_;
    }
    
    public Builder setMsgType(int value) {
      this.msgType_ = value;
      this.bitField0_ |= 0x8;
      onChanged();
      return this;
    }
    
    public Builder clearMsgType() {
      this.bitField0_ &= 0xFFFFFFF7;
      this.msgType_ = 0;
      onChanged();
      return this;
    }
    
    public long getOffset() {
      return this.offset_;
    }
    
    public Builder setOffset(long value) {
      this.offset_ = value;
      this.bitField0_ |= 0x10;
      onChanged();
      return this;
    }
    
    public Builder clearOffset() {
      this.bitField0_ &= 0xFFFFFFEF;
      this.offset_ = 0L;
      onChanged();
      return this;
    }
    
    public boolean getNeedWrdsStore() {
      return this.needWrdsStore_;
    }
    
    public Builder setNeedWrdsStore(boolean value) {
      this.needWrdsStore_ = value;
      this.bitField0_ |= 0x20;
      onChanged();
      return this;
    }
    
    public Builder clearNeedWrdsStore() {
      this.bitField0_ &= 0xFFFFFFDF;
      this.needWrdsStore_ = false;
      onChanged();
      return this;
    }
    
    public long getWrdsVersion() {
      return this.wrdsVersion_;
    }
    
    public Builder setWrdsVersion(long value) {
      this.wrdsVersion_ = value;
      this.bitField0_ |= 0x40;
      onChanged();
      return this;
    }
    
    public Builder clearWrdsVersion() {
      this.bitField0_ &= 0xFFFFFFBF;
      this.wrdsVersion_ = 0L;
      onChanged();
      return this;
    }
    
    public String getWrdsSubKey() {
      Object ref = this.wrdsSubKey_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.wrdsSubKey_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getWrdsSubKeyBytes() {
      Object ref = this.wrdsSubKey_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.wrdsSubKey_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setWrdsSubKey(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.wrdsSubKey_ = value;
      this.bitField0_ |= 0x80;
      onChanged();
      return this;
    }
    
    public Builder clearWrdsSubKey() {
      this.wrdsSubKey_ = Message.getDefaultInstance().getWrdsSubKey();
      this.bitField0_ &= 0xFFFFFF7F;
      onChanged();
      return this;
    }
    
    public Builder setWrdsSubKeyBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      Message.checkByteStringIsUtf8(value);
      this.wrdsSubKey_ = value;
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
  
  private static final Message DEFAULT_INSTANCE = new Message();
  
  public static Message getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }
  
  private static final Parser<Message> PARSER = (Parser<Message>)new AbstractParser<Message>() {
      public Message parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        Message.Builder builder = Message.newBuilder();
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
  
  public static Parser<Message> parser() {
    return PARSER;
  }
  
  public Parser<Message> getParserForType() {
    return PARSER;
  }
  
  public Message getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }
}
