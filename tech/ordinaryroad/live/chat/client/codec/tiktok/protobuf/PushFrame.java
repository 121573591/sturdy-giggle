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
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class PushFrame extends GeneratedMessageV3 implements PushFrameOrBuilder {
  private static final long serialVersionUID = 0L;
  
  public static final int SEQID_FIELD_NUMBER = 1;
  
  private long seqId_;
  
  public static final int LOGID_FIELD_NUMBER = 2;
  
  private long logId_;
  
  public static final int SERVICE_FIELD_NUMBER = 3;
  
  private long service_;
  
  public static final int METHOD_FIELD_NUMBER = 4;
  
  private long method_;
  
  public static final int HEADERSLIST_FIELD_NUMBER = 5;
  
  private List<HeadersList> headersList_;
  
  public static final int PAYLOADENCODING_FIELD_NUMBER = 6;
  
  private volatile Object payloadEncoding_;
  
  public static final int PAYLOADTYPE_FIELD_NUMBER = 7;
  
  private volatile Object payloadType_;
  
  public static final int PAYLOAD_FIELD_NUMBER = 8;
  
  private ByteString payload_;
  
  private byte memoizedIsInitialized;
  
  private PushFrame(GeneratedMessageV3.Builder<?> builder) {
    super(builder);
    this.seqId_ = 0L;
    this.logId_ = 0L;
    this.service_ = 0L;
    this.method_ = 0L;
    this.payloadEncoding_ = "";
    this.payloadType_ = "";
    this.payload_ = ByteString.EMPTY;
    this.memoizedIsInitialized = -1;
  }
  
  private PushFrame() {
    this.seqId_ = 0L;
    this.logId_ = 0L;
    this.service_ = 0L;
    this.method_ = 0L;
    this.payloadEncoding_ = "";
    this.payloadType_ = "";
    this.payload_ = ByteString.EMPTY;
    this.memoizedIsInitialized = -1;
    this.headersList_ = Collections.emptyList();
    this.payloadEncoding_ = "";
    this.payloadType_ = "";
    this.payload_ = ByteString.EMPTY;
  }
  
  protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
    return new PushFrame();
  }
  
  public static final Descriptors.Descriptor getDescriptor() {
    return Tiktok.internal_static_PushFrame_descriptor;
  }
  
  protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
    return Tiktok.internal_static_PushFrame_fieldAccessorTable.ensureFieldAccessorsInitialized(PushFrame.class, Builder.class);
  }
  
  public long getSeqId() {
    return this.seqId_;
  }
  
  public long getLogId() {
    return this.logId_;
  }
  
  public long getService() {
    return this.service_;
  }
  
  public long getMethod() {
    return this.method_;
  }
  
  public List<HeadersList> getHeadersListList() {
    return this.headersList_;
  }
  
  public List<? extends HeadersListOrBuilder> getHeadersListOrBuilderList() {
    return (List)this.headersList_;
  }
  
  public int getHeadersListCount() {
    return this.headersList_.size();
  }
  
  public HeadersList getHeadersList(int index) {
    return this.headersList_.get(index);
  }
  
  public HeadersListOrBuilder getHeadersListOrBuilder(int index) {
    return this.headersList_.get(index);
  }
  
  public String getPayloadEncoding() {
    Object ref = this.payloadEncoding_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.payloadEncoding_ = s;
    return s;
  }
  
  public ByteString getPayloadEncodingBytes() {
    Object ref = this.payloadEncoding_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.payloadEncoding_ = b;
      return b;
    } 
    return (ByteString)ref;
  }
  
  public String getPayloadType() {
    Object ref = this.payloadType_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.payloadType_ = s;
    return s;
  }
  
  public ByteString getPayloadTypeBytes() {
    Object ref = this.payloadType_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.payloadType_ = b;
      return b;
    } 
    return (ByteString)ref;
  }
  
  public ByteString getPayload() {
    return this.payload_;
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
    if (this.seqId_ != 0L)
      output.writeUInt64(1, this.seqId_); 
    if (this.logId_ != 0L)
      output.writeUInt64(2, this.logId_); 
    if (this.service_ != 0L)
      output.writeUInt64(3, this.service_); 
    if (this.method_ != 0L)
      output.writeUInt64(4, this.method_); 
    for (int i = 0; i < this.headersList_.size(); i++)
      output.writeMessage(5, (MessageLite)this.headersList_.get(i)); 
    if (!GeneratedMessageV3.isStringEmpty(this.payloadEncoding_))
      GeneratedMessageV3.writeString(output, 6, this.payloadEncoding_); 
    if (!GeneratedMessageV3.isStringEmpty(this.payloadType_))
      GeneratedMessageV3.writeString(output, 7, this.payloadType_); 
    if (!this.payload_.isEmpty())
      output.writeBytes(8, this.payload_); 
    getUnknownFields().writeTo(output);
  }
  
  public int getSerializedSize() {
    int size = this.memoizedSize;
    if (size != -1)
      return size; 
    size = 0;
    if (this.seqId_ != 0L)
      size += 
        CodedOutputStream.computeUInt64Size(1, this.seqId_); 
    if (this.logId_ != 0L)
      size += 
        CodedOutputStream.computeUInt64Size(2, this.logId_); 
    if (this.service_ != 0L)
      size += 
        CodedOutputStream.computeUInt64Size(3, this.service_); 
    if (this.method_ != 0L)
      size += 
        CodedOutputStream.computeUInt64Size(4, this.method_); 
    for (int i = 0; i < this.headersList_.size(); i++)
      size += 
        CodedOutputStream.computeMessageSize(5, (MessageLite)this.headersList_.get(i)); 
    if (!GeneratedMessageV3.isStringEmpty(this.payloadEncoding_))
      size += GeneratedMessageV3.computeStringSize(6, this.payloadEncoding_); 
    if (!GeneratedMessageV3.isStringEmpty(this.payloadType_))
      size += GeneratedMessageV3.computeStringSize(7, this.payloadType_); 
    if (!this.payload_.isEmpty())
      size += 
        CodedOutputStream.computeBytesSize(8, this.payload_); 
    size += getUnknownFields().getSerializedSize();
    this.memoizedSize = size;
    return size;
  }
  
  public boolean equals(Object obj) {
    if (obj == this)
      return true; 
    if (!(obj instanceof PushFrame))
      return super.equals(obj); 
    PushFrame other = (PushFrame)obj;
    if (getSeqId() != other
      .getSeqId())
      return false; 
    if (getLogId() != other
      .getLogId())
      return false; 
    if (getService() != other
      .getService())
      return false; 
    if (getMethod() != other
      .getMethod())
      return false; 
    if (!getHeadersListList().equals(other.getHeadersListList()))
      return false; 
    if (!getPayloadEncoding().equals(other.getPayloadEncoding()))
      return false; 
    if (!getPayloadType().equals(other.getPayloadType()))
      return false; 
    if (!getPayload().equals(other.getPayload()))
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
        getSeqId());
    hash = 37 * hash + 2;
    hash = 53 * hash + Internal.hashLong(
        getLogId());
    hash = 37 * hash + 3;
    hash = 53 * hash + Internal.hashLong(
        getService());
    hash = 37 * hash + 4;
    hash = 53 * hash + Internal.hashLong(
        getMethod());
    if (getHeadersListCount() > 0) {
      hash = 37 * hash + 5;
      hash = 53 * hash + getHeadersListList().hashCode();
    } 
    hash = 37 * hash + 6;
    hash = 53 * hash + getPayloadEncoding().hashCode();
    hash = 37 * hash + 7;
    hash = 53 * hash + getPayloadType().hashCode();
    hash = 37 * hash + 8;
    hash = 53 * hash + getPayload().hashCode();
    hash = 29 * hash + getUnknownFields().hashCode();
    this.memoizedHashCode = hash;
    return hash;
  }
  
  public static PushFrame parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
    return (PushFrame)PARSER.parseFrom(data);
  }
  
  public static PushFrame parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (PushFrame)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static PushFrame parseFrom(ByteString data) throws InvalidProtocolBufferException {
    return (PushFrame)PARSER.parseFrom(data);
  }
  
  public static PushFrame parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (PushFrame)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static PushFrame parseFrom(byte[] data) throws InvalidProtocolBufferException {
    return (PushFrame)PARSER.parseFrom(data);
  }
  
  public static PushFrame parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (PushFrame)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static PushFrame parseFrom(InputStream input) throws IOException {
    return 
      (PushFrame)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static PushFrame parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (PushFrame)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static PushFrame parseDelimitedFrom(InputStream input) throws IOException {
    return 
      (PushFrame)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
  }
  
  public static PushFrame parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (PushFrame)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static PushFrame parseFrom(CodedInputStream input) throws IOException {
    return 
      (PushFrame)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static PushFrame parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (PushFrame)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public Builder newBuilderForType() {
    return newBuilder();
  }
  
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  
  public static Builder newBuilder(PushFrame prototype) {
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
  
  public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements PushFrameOrBuilder {
    private int bitField0_;
    
    private long seqId_;
    
    private long logId_;
    
    private long service_;
    
    private long method_;
    
    private List<HeadersList> headersList_;
    
    private RepeatedFieldBuilderV3<HeadersList, HeadersList.Builder, HeadersListOrBuilder> headersListBuilder_;
    
    private Object payloadEncoding_;
    
    private Object payloadType_;
    
    private ByteString payload_;
    
    public static final Descriptors.Descriptor getDescriptor() {
      return Tiktok.internal_static_PushFrame_descriptor;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return Tiktok.internal_static_PushFrame_fieldAccessorTable
        .ensureFieldAccessorsInitialized(PushFrame.class, Builder.class);
    }
    
    private Builder() {
      this
        .headersList_ = Collections.emptyList();
      this.payloadEncoding_ = "";
      this.payloadType_ = "";
      this.payload_ = ByteString.EMPTY;
    }
    
    private Builder(GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      this.headersList_ = Collections.emptyList();
      this.payloadEncoding_ = "";
      this.payloadType_ = "";
      this.payload_ = ByteString.EMPTY;
    }
    
    public Builder clear() {
      super.clear();
      this.bitField0_ = 0;
      this.seqId_ = 0L;
      this.logId_ = 0L;
      this.service_ = 0L;
      this.method_ = 0L;
      if (this.headersListBuilder_ == null) {
        this.headersList_ = Collections.emptyList();
      } else {
        this.headersList_ = null;
        this.headersListBuilder_.clear();
      } 
      this.bitField0_ &= 0xFFFFFFEF;
      this.payloadEncoding_ = "";
      this.payloadType_ = "";
      this.payload_ = ByteString.EMPTY;
      return this;
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
      return Tiktok.internal_static_PushFrame_descriptor;
    }
    
    public PushFrame getDefaultInstanceForType() {
      return PushFrame.getDefaultInstance();
    }
    
    public PushFrame build() {
      PushFrame result = buildPartial();
      if (!result.isInitialized())
        throw newUninitializedMessageException(result); 
      return result;
    }
    
    public PushFrame buildPartial() {
      PushFrame result = new PushFrame(this);
      buildPartialRepeatedFields(result);
      if (this.bitField0_ != 0)
        buildPartial0(result); 
      onBuilt();
      return result;
    }
    
    private void buildPartialRepeatedFields(PushFrame result) {
      if (this.headersListBuilder_ == null) {
        if ((this.bitField0_ & 0x10) != 0) {
          this.headersList_ = Collections.unmodifiableList(this.headersList_);
          this.bitField0_ &= 0xFFFFFFEF;
        } 
        result.headersList_ = this.headersList_;
      } else {
        result.headersList_ = this.headersListBuilder_.build();
      } 
    }
    
    private void buildPartial0(PushFrame result) {
      int from_bitField0_ = this.bitField0_;
      if ((from_bitField0_ & 0x1) != 0)
        result.seqId_ = this.seqId_; 
      if ((from_bitField0_ & 0x2) != 0)
        result.logId_ = this.logId_; 
      if ((from_bitField0_ & 0x4) != 0)
        result.service_ = this.service_; 
      if ((from_bitField0_ & 0x8) != 0)
        result.method_ = this.method_; 
      if ((from_bitField0_ & 0x20) != 0)
        result.payloadEncoding_ = this.payloadEncoding_; 
      if ((from_bitField0_ & 0x40) != 0)
        result.payloadType_ = this.payloadType_; 
      if ((from_bitField0_ & 0x80) != 0)
        result.payload_ = this.payload_; 
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
      if (other instanceof PushFrame)
        return mergeFrom((PushFrame)other); 
      super.mergeFrom(other);
      return this;
    }
    
    public Builder mergeFrom(PushFrame other) {
      if (other == PushFrame.getDefaultInstance())
        return this; 
      if (other.getSeqId() != 0L)
        setSeqId(other.getSeqId()); 
      if (other.getLogId() != 0L)
        setLogId(other.getLogId()); 
      if (other.getService() != 0L)
        setService(other.getService()); 
      if (other.getMethod() != 0L)
        setMethod(other.getMethod()); 
      if (this.headersListBuilder_ == null) {
        if (!other.headersList_.isEmpty()) {
          if (this.headersList_.isEmpty()) {
            this.headersList_ = other.headersList_;
            this.bitField0_ &= 0xFFFFFFEF;
          } else {
            ensureHeadersListIsMutable();
            this.headersList_.addAll(other.headersList_);
          } 
          onChanged();
        } 
      } else if (!other.headersList_.isEmpty()) {
        if (this.headersListBuilder_.isEmpty()) {
          this.headersListBuilder_.dispose();
          this.headersListBuilder_ = null;
          this.headersList_ = other.headersList_;
          this.bitField0_ &= 0xFFFFFFEF;
          this.headersListBuilder_ = PushFrame.alwaysUseFieldBuilders ? getHeadersListFieldBuilder() : null;
        } else {
          this.headersListBuilder_.addAllMessages(other.headersList_);
        } 
      } 
      if (!other.getPayloadEncoding().isEmpty()) {
        this.payloadEncoding_ = other.payloadEncoding_;
        this.bitField0_ |= 0x20;
        onChanged();
      } 
      if (!other.getPayloadType().isEmpty()) {
        this.payloadType_ = other.payloadType_;
        this.bitField0_ |= 0x40;
        onChanged();
      } 
      if (other.getPayload() != ByteString.EMPTY)
        setPayload(other.getPayload()); 
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
          HeadersList m;
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              continue;
            case 8:
              this.seqId_ = input.readUInt64();
              this.bitField0_ |= 0x1;
              continue;
            case 16:
              this.logId_ = input.readUInt64();
              this.bitField0_ |= 0x2;
              continue;
            case 24:
              this.service_ = input.readUInt64();
              this.bitField0_ |= 0x4;
              continue;
            case 32:
              this.method_ = input.readUInt64();
              this.bitField0_ |= 0x8;
              continue;
            case 42:
              m = (HeadersList)input.readMessage(HeadersList.parser(), extensionRegistry);
              if (this.headersListBuilder_ == null) {
                ensureHeadersListIsMutable();
                this.headersList_.add(m);
                continue;
              } 
              this.headersListBuilder_.addMessage((AbstractMessage)m);
              continue;
            case 50:
              this.payloadEncoding_ = input.readStringRequireUtf8();
              this.bitField0_ |= 0x20;
              continue;
            case 58:
              this.payloadType_ = input.readStringRequireUtf8();
              this.bitField0_ |= 0x40;
              continue;
            case 66:
              this.payload_ = input.readBytes();
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
    
    public long getSeqId() {
      return this.seqId_;
    }
    
    public Builder setSeqId(long value) {
      this.seqId_ = value;
      this.bitField0_ |= 0x1;
      onChanged();
      return this;
    }
    
    public Builder clearSeqId() {
      this.bitField0_ &= 0xFFFFFFFE;
      this.seqId_ = 0L;
      onChanged();
      return this;
    }
    
    public long getLogId() {
      return this.logId_;
    }
    
    public Builder setLogId(long value) {
      this.logId_ = value;
      this.bitField0_ |= 0x2;
      onChanged();
      return this;
    }
    
    public Builder clearLogId() {
      this.bitField0_ &= 0xFFFFFFFD;
      this.logId_ = 0L;
      onChanged();
      return this;
    }
    
    public long getService() {
      return this.service_;
    }
    
    public Builder setService(long value) {
      this.service_ = value;
      this.bitField0_ |= 0x4;
      onChanged();
      return this;
    }
    
    public Builder clearService() {
      this.bitField0_ &= 0xFFFFFFFB;
      this.service_ = 0L;
      onChanged();
      return this;
    }
    
    public long getMethod() {
      return this.method_;
    }
    
    public Builder setMethod(long value) {
      this.method_ = value;
      this.bitField0_ |= 0x8;
      onChanged();
      return this;
    }
    
    public Builder clearMethod() {
      this.bitField0_ &= 0xFFFFFFF7;
      this.method_ = 0L;
      onChanged();
      return this;
    }
    
    private void ensureHeadersListIsMutable() {
      if ((this.bitField0_ & 0x10) == 0) {
        this.headersList_ = new ArrayList<>(this.headersList_);
        this.bitField0_ |= 0x10;
      } 
    }
    
    public List<HeadersList> getHeadersListList() {
      if (this.headersListBuilder_ == null)
        return Collections.unmodifiableList(this.headersList_); 
      return this.headersListBuilder_.getMessageList();
    }
    
    public int getHeadersListCount() {
      if (this.headersListBuilder_ == null)
        return this.headersList_.size(); 
      return this.headersListBuilder_.getCount();
    }
    
    public HeadersList getHeadersList(int index) {
      if (this.headersListBuilder_ == null)
        return this.headersList_.get(index); 
      return (HeadersList)this.headersListBuilder_.getMessage(index);
    }
    
    public Builder setHeadersList(int index, HeadersList value) {
      if (this.headersListBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        ensureHeadersListIsMutable();
        this.headersList_.set(index, value);
        onChanged();
      } else {
        this.headersListBuilder_.setMessage(index, (AbstractMessage)value);
      } 
      return this;
    }
    
    public Builder setHeadersList(int index, HeadersList.Builder builderForValue) {
      if (this.headersListBuilder_ == null) {
        ensureHeadersListIsMutable();
        this.headersList_.set(index, builderForValue.build());
        onChanged();
      } else {
        this.headersListBuilder_.setMessage(index, (AbstractMessage)builderForValue.build());
      } 
      return this;
    }
    
    public Builder addHeadersList(HeadersList value) {
      if (this.headersListBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        ensureHeadersListIsMutable();
        this.headersList_.add(value);
        onChanged();
      } else {
        this.headersListBuilder_.addMessage((AbstractMessage)value);
      } 
      return this;
    }
    
    public Builder addHeadersList(int index, HeadersList value) {
      if (this.headersListBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        ensureHeadersListIsMutable();
        this.headersList_.add(index, value);
        onChanged();
      } else {
        this.headersListBuilder_.addMessage(index, (AbstractMessage)value);
      } 
      return this;
    }
    
    public Builder addHeadersList(HeadersList.Builder builderForValue) {
      if (this.headersListBuilder_ == null) {
        ensureHeadersListIsMutable();
        this.headersList_.add(builderForValue.build());
        onChanged();
      } else {
        this.headersListBuilder_.addMessage((AbstractMessage)builderForValue.build());
      } 
      return this;
    }
    
    public Builder addHeadersList(int index, HeadersList.Builder builderForValue) {
      if (this.headersListBuilder_ == null) {
        ensureHeadersListIsMutable();
        this.headersList_.add(index, builderForValue.build());
        onChanged();
      } else {
        this.headersListBuilder_.addMessage(index, (AbstractMessage)builderForValue.build());
      } 
      return this;
    }
    
    public Builder addAllHeadersList(Iterable<? extends HeadersList> values) {
      if (this.headersListBuilder_ == null) {
        ensureHeadersListIsMutable();
        AbstractMessageLite.Builder.addAll(values, this.headersList_);
        onChanged();
      } else {
        this.headersListBuilder_.addAllMessages(values);
      } 
      return this;
    }
    
    public Builder clearHeadersList() {
      if (this.headersListBuilder_ == null) {
        this.headersList_ = Collections.emptyList();
        this.bitField0_ &= 0xFFFFFFEF;
        onChanged();
      } else {
        this.headersListBuilder_.clear();
      } 
      return this;
    }
    
    public Builder removeHeadersList(int index) {
      if (this.headersListBuilder_ == null) {
        ensureHeadersListIsMutable();
        this.headersList_.remove(index);
        onChanged();
      } else {
        this.headersListBuilder_.remove(index);
      } 
      return this;
    }
    
    public HeadersList.Builder getHeadersListBuilder(int index) {
      return (HeadersList.Builder)getHeadersListFieldBuilder().getBuilder(index);
    }
    
    public HeadersListOrBuilder getHeadersListOrBuilder(int index) {
      if (this.headersListBuilder_ == null)
        return this.headersList_.get(index); 
      return (HeadersListOrBuilder)this.headersListBuilder_.getMessageOrBuilder(index);
    }
    
    public List<? extends HeadersListOrBuilder> getHeadersListOrBuilderList() {
      if (this.headersListBuilder_ != null)
        return this.headersListBuilder_.getMessageOrBuilderList(); 
      return Collections.unmodifiableList((List)this.headersList_);
    }
    
    public HeadersList.Builder addHeadersListBuilder() {
      return (HeadersList.Builder)getHeadersListFieldBuilder().addBuilder((AbstractMessage)HeadersList.getDefaultInstance());
    }
    
    public HeadersList.Builder addHeadersListBuilder(int index) {
      return (HeadersList.Builder)getHeadersListFieldBuilder().addBuilder(index, (AbstractMessage)HeadersList.getDefaultInstance());
    }
    
    public List<HeadersList.Builder> getHeadersListBuilderList() {
      return getHeadersListFieldBuilder().getBuilderList();
    }
    
    private RepeatedFieldBuilderV3<HeadersList, HeadersList.Builder, HeadersListOrBuilder> getHeadersListFieldBuilder() {
      if (this.headersListBuilder_ == null) {
        this.headersListBuilder_ = new RepeatedFieldBuilderV3(this.headersList_, ((this.bitField0_ & 0x10) != 0), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.headersList_ = null;
      } 
      return this.headersListBuilder_;
    }
    
    public String getPayloadEncoding() {
      Object ref = this.payloadEncoding_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.payloadEncoding_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getPayloadEncodingBytes() {
      Object ref = this.payloadEncoding_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.payloadEncoding_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setPayloadEncoding(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.payloadEncoding_ = value;
      this.bitField0_ |= 0x20;
      onChanged();
      return this;
    }
    
    public Builder clearPayloadEncoding() {
      this.payloadEncoding_ = PushFrame.getDefaultInstance().getPayloadEncoding();
      this.bitField0_ &= 0xFFFFFFDF;
      onChanged();
      return this;
    }
    
    public Builder setPayloadEncodingBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      PushFrame.checkByteStringIsUtf8(value);
      this.payloadEncoding_ = value;
      this.bitField0_ |= 0x20;
      onChanged();
      return this;
    }
    
    public String getPayloadType() {
      Object ref = this.payloadType_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.payloadType_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getPayloadTypeBytes() {
      Object ref = this.payloadType_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.payloadType_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setPayloadType(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.payloadType_ = value;
      this.bitField0_ |= 0x40;
      onChanged();
      return this;
    }
    
    public Builder clearPayloadType() {
      this.payloadType_ = PushFrame.getDefaultInstance().getPayloadType();
      this.bitField0_ &= 0xFFFFFFBF;
      onChanged();
      return this;
    }
    
    public Builder setPayloadTypeBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      PushFrame.checkByteStringIsUtf8(value);
      this.payloadType_ = value;
      this.bitField0_ |= 0x40;
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
      this.bitField0_ |= 0x80;
      onChanged();
      return this;
    }
    
    public Builder clearPayload() {
      this.bitField0_ &= 0xFFFFFF7F;
      this.payload_ = PushFrame.getDefaultInstance().getPayload();
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
  
  private static final PushFrame DEFAULT_INSTANCE = new PushFrame();
  
  public static PushFrame getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }
  
  private static final Parser<PushFrame> PARSER = (Parser<PushFrame>)new AbstractParser<PushFrame>() {
      public PushFrame parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        PushFrame.Builder builder = PushFrame.newBuilder();
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
  
  public static Parser<PushFrame> parser() {
    return PARSER;
  }
  
  public Parser<PushFrame> getParserForType() {
    return PARSER;
  }
  
  public PushFrame getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }
}
