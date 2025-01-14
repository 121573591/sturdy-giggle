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
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class PreMessage extends GeneratedMessageV3 implements PreMessageOrBuilder {
  private static final long serialVersionUID = 0L;
  
  private int bitField0_;
  
  public static final int CMD_FIELD_NUMBER = 1;
  
  private int cmd_;
  
  public static final int SEQUENCEID_FIELD_NUMBER = 2;
  
  private int sequenceId_;
  
  public static final int SDKVERSION_FIELD_NUMBER = 3;
  
  private volatile Object sdkVersion_;
  
  public static final int TOKEN_FIELD_NUMBER = 4;
  
  private volatile Object token_;
  
  public static final int REFER_FIELD_NUMBER = 5;
  
  private int refer_;
  
  public static final int INBOXTYPE_FIELD_NUMBER = 6;
  
  private int inboxType_;
  
  public static final int BUILDNUMBER_FIELD_NUMBER = 7;
  
  private volatile Object buildNumber_;
  
  public static final int SENDMESSAGEBODY_FIELD_NUMBER = 8;
  
  private SendMessageBody sendMessageBody_;
  
  public static final int AA_FIELD_NUMBER = 9;
  
  private volatile Object aa_;
  
  public static final int DEVICEPLATFORM_FIELD_NUMBER = 11;
  
  private volatile Object devicePlatform_;
  
  public static final int HEADERS_FIELD_NUMBER = 15;
  
  private List<HeadersList> headers_;
  
  public static final int AUTHTYPE_FIELD_NUMBER = 18;
  
  private int authType_;
  
  public static final int BIZ_FIELD_NUMBER = 21;
  
  private volatile Object biz_;
  
  public static final int ACCESS_FIELD_NUMBER = 22;
  
  private volatile Object access_;
  
  private byte memoizedIsInitialized;
  
  private PreMessage(GeneratedMessageV3.Builder<?> builder) {
    super(builder);
    this.cmd_ = 0;
    this.sequenceId_ = 0;
    this.sdkVersion_ = "";
    this.token_ = "";
    this.refer_ = 0;
    this.inboxType_ = 0;
    this.buildNumber_ = "";
    this.aa_ = "";
    this.devicePlatform_ = "";
    this.authType_ = 0;
    this.biz_ = "";
    this.access_ = "";
    this.memoizedIsInitialized = -1;
  }
  
  private PreMessage() {
    this.cmd_ = 0;
    this.sequenceId_ = 0;
    this.sdkVersion_ = "";
    this.token_ = "";
    this.refer_ = 0;
    this.inboxType_ = 0;
    this.buildNumber_ = "";
    this.aa_ = "";
    this.devicePlatform_ = "";
    this.authType_ = 0;
    this.biz_ = "";
    this.access_ = "";
    this.memoizedIsInitialized = -1;
    this.sdkVersion_ = "";
    this.token_ = "";
    this.buildNumber_ = "";
    this.aa_ = "";
    this.devicePlatform_ = "";
    this.headers_ = Collections.emptyList();
    this.biz_ = "";
    this.access_ = "";
  }
  
  protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
    return new PreMessage();
  }
  
  public static final Descriptors.Descriptor getDescriptor() {
    return Douyin.internal_static_PreMessage_descriptor;
  }
  
  protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
    return Douyin.internal_static_PreMessage_fieldAccessorTable.ensureFieldAccessorsInitialized(PreMessage.class, Builder.class);
  }
  
  public int getCmd() {
    return this.cmd_;
  }
  
  public int getSequenceId() {
    return this.sequenceId_;
  }
  
  public String getSdkVersion() {
    Object ref = this.sdkVersion_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.sdkVersion_ = s;
    return s;
  }
  
  public ByteString getSdkVersionBytes() {
    Object ref = this.sdkVersion_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.sdkVersion_ = b;
      return b;
    } 
    return (ByteString)ref;
  }
  
  public String getToken() {
    Object ref = this.token_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.token_ = s;
    return s;
  }
  
  public ByteString getTokenBytes() {
    Object ref = this.token_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.token_ = b;
      return b;
    } 
    return (ByteString)ref;
  }
  
  public int getRefer() {
    return this.refer_;
  }
  
  public int getInboxType() {
    return this.inboxType_;
  }
  
  public String getBuildNumber() {
    Object ref = this.buildNumber_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.buildNumber_ = s;
    return s;
  }
  
  public ByteString getBuildNumberBytes() {
    Object ref = this.buildNumber_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.buildNumber_ = b;
      return b;
    } 
    return (ByteString)ref;
  }
  
  public boolean hasSendMessageBody() {
    return ((this.bitField0_ & 0x1) != 0);
  }
  
  public SendMessageBody getSendMessageBody() {
    return (this.sendMessageBody_ == null) ? SendMessageBody.getDefaultInstance() : this.sendMessageBody_;
  }
  
  public SendMessageBodyOrBuilder getSendMessageBodyOrBuilder() {
    return (this.sendMessageBody_ == null) ? SendMessageBody.getDefaultInstance() : this.sendMessageBody_;
  }
  
  public String getAa() {
    Object ref = this.aa_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.aa_ = s;
    return s;
  }
  
  public ByteString getAaBytes() {
    Object ref = this.aa_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.aa_ = b;
      return b;
    } 
    return (ByteString)ref;
  }
  
  public String getDevicePlatform() {
    Object ref = this.devicePlatform_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.devicePlatform_ = s;
    return s;
  }
  
  public ByteString getDevicePlatformBytes() {
    Object ref = this.devicePlatform_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.devicePlatform_ = b;
      return b;
    } 
    return (ByteString)ref;
  }
  
  public List<HeadersList> getHeadersList() {
    return this.headers_;
  }
  
  public List<? extends HeadersListOrBuilder> getHeadersOrBuilderList() {
    return (List)this.headers_;
  }
  
  public int getHeadersCount() {
    return this.headers_.size();
  }
  
  public HeadersList getHeaders(int index) {
    return this.headers_.get(index);
  }
  
  public HeadersListOrBuilder getHeadersOrBuilder(int index) {
    return this.headers_.get(index);
  }
  
  public int getAuthType() {
    return this.authType_;
  }
  
  public String getBiz() {
    Object ref = this.biz_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.biz_ = s;
    return s;
  }
  
  public ByteString getBizBytes() {
    Object ref = this.biz_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.biz_ = b;
      return b;
    } 
    return (ByteString)ref;
  }
  
  public String getAccess() {
    Object ref = this.access_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.access_ = s;
    return s;
  }
  
  public ByteString getAccessBytes() {
    Object ref = this.access_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.access_ = b;
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
    if (this.cmd_ != 0)
      output.writeUInt32(1, this.cmd_); 
    if (this.sequenceId_ != 0)
      output.writeUInt32(2, this.sequenceId_); 
    if (!GeneratedMessageV3.isStringEmpty(this.sdkVersion_))
      GeneratedMessageV3.writeString(output, 3, this.sdkVersion_); 
    if (!GeneratedMessageV3.isStringEmpty(this.token_))
      GeneratedMessageV3.writeString(output, 4, this.token_); 
    if (this.refer_ != 0)
      output.writeUInt32(5, this.refer_); 
    if (this.inboxType_ != 0)
      output.writeUInt32(6, this.inboxType_); 
    if (!GeneratedMessageV3.isStringEmpty(this.buildNumber_))
      GeneratedMessageV3.writeString(output, 7, this.buildNumber_); 
    if ((this.bitField0_ & 0x1) != 0)
      output.writeMessage(8, (MessageLite)getSendMessageBody()); 
    if (!GeneratedMessageV3.isStringEmpty(this.aa_))
      GeneratedMessageV3.writeString(output, 9, this.aa_); 
    if (!GeneratedMessageV3.isStringEmpty(this.devicePlatform_))
      GeneratedMessageV3.writeString(output, 11, this.devicePlatform_); 
    for (int i = 0; i < this.headers_.size(); i++)
      output.writeMessage(15, (MessageLite)this.headers_.get(i)); 
    if (this.authType_ != 0)
      output.writeUInt32(18, this.authType_); 
    if (!GeneratedMessageV3.isStringEmpty(this.biz_))
      GeneratedMessageV3.writeString(output, 21, this.biz_); 
    if (!GeneratedMessageV3.isStringEmpty(this.access_))
      GeneratedMessageV3.writeString(output, 22, this.access_); 
    getUnknownFields().writeTo(output);
  }
  
  public int getSerializedSize() {
    int size = this.memoizedSize;
    if (size != -1)
      return size; 
    size = 0;
    if (this.cmd_ != 0)
      size += 
        CodedOutputStream.computeUInt32Size(1, this.cmd_); 
    if (this.sequenceId_ != 0)
      size += 
        CodedOutputStream.computeUInt32Size(2, this.sequenceId_); 
    if (!GeneratedMessageV3.isStringEmpty(this.sdkVersion_))
      size += GeneratedMessageV3.computeStringSize(3, this.sdkVersion_); 
    if (!GeneratedMessageV3.isStringEmpty(this.token_))
      size += GeneratedMessageV3.computeStringSize(4, this.token_); 
    if (this.refer_ != 0)
      size += 
        CodedOutputStream.computeUInt32Size(5, this.refer_); 
    if (this.inboxType_ != 0)
      size += 
        CodedOutputStream.computeUInt32Size(6, this.inboxType_); 
    if (!GeneratedMessageV3.isStringEmpty(this.buildNumber_))
      size += GeneratedMessageV3.computeStringSize(7, this.buildNumber_); 
    if ((this.bitField0_ & 0x1) != 0)
      size += 
        CodedOutputStream.computeMessageSize(8, (MessageLite)getSendMessageBody()); 
    if (!GeneratedMessageV3.isStringEmpty(this.aa_))
      size += GeneratedMessageV3.computeStringSize(9, this.aa_); 
    if (!GeneratedMessageV3.isStringEmpty(this.devicePlatform_))
      size += GeneratedMessageV3.computeStringSize(11, this.devicePlatform_); 
    for (int i = 0; i < this.headers_.size(); i++)
      size += 
        CodedOutputStream.computeMessageSize(15, (MessageLite)this.headers_.get(i)); 
    if (this.authType_ != 0)
      size += 
        CodedOutputStream.computeUInt32Size(18, this.authType_); 
    if (!GeneratedMessageV3.isStringEmpty(this.biz_))
      size += GeneratedMessageV3.computeStringSize(21, this.biz_); 
    if (!GeneratedMessageV3.isStringEmpty(this.access_))
      size += GeneratedMessageV3.computeStringSize(22, this.access_); 
    size += getUnknownFields().getSerializedSize();
    this.memoizedSize = size;
    return size;
  }
  
  public boolean equals(Object obj) {
    if (obj == this)
      return true; 
    if (!(obj instanceof PreMessage))
      return super.equals(obj); 
    PreMessage other = (PreMessage)obj;
    if (getCmd() != other
      .getCmd())
      return false; 
    if (getSequenceId() != other
      .getSequenceId())
      return false; 
    if (!getSdkVersion().equals(other.getSdkVersion()))
      return false; 
    if (!getToken().equals(other.getToken()))
      return false; 
    if (getRefer() != other
      .getRefer())
      return false; 
    if (getInboxType() != other
      .getInboxType())
      return false; 
    if (!getBuildNumber().equals(other.getBuildNumber()))
      return false; 
    if (hasSendMessageBody() != other.hasSendMessageBody())
      return false; 
    if (hasSendMessageBody() && 
      
      !getSendMessageBody().equals(other.getSendMessageBody()))
      return false; 
    if (!getAa().equals(other.getAa()))
      return false; 
    if (!getDevicePlatform().equals(other.getDevicePlatform()))
      return false; 
    if (!getHeadersList().equals(other.getHeadersList()))
      return false; 
    if (getAuthType() != other
      .getAuthType())
      return false; 
    if (!getBiz().equals(other.getBiz()))
      return false; 
    if (!getAccess().equals(other.getAccess()))
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
    hash = 53 * hash + getCmd();
    hash = 37 * hash + 2;
    hash = 53 * hash + getSequenceId();
    hash = 37 * hash + 3;
    hash = 53 * hash + getSdkVersion().hashCode();
    hash = 37 * hash + 4;
    hash = 53 * hash + getToken().hashCode();
    hash = 37 * hash + 5;
    hash = 53 * hash + getRefer();
    hash = 37 * hash + 6;
    hash = 53 * hash + getInboxType();
    hash = 37 * hash + 7;
    hash = 53 * hash + getBuildNumber().hashCode();
    if (hasSendMessageBody()) {
      hash = 37 * hash + 8;
      hash = 53 * hash + getSendMessageBody().hashCode();
    } 
    hash = 37 * hash + 9;
    hash = 53 * hash + getAa().hashCode();
    hash = 37 * hash + 11;
    hash = 53 * hash + getDevicePlatform().hashCode();
    if (getHeadersCount() > 0) {
      hash = 37 * hash + 15;
      hash = 53 * hash + getHeadersList().hashCode();
    } 
    hash = 37 * hash + 18;
    hash = 53 * hash + getAuthType();
    hash = 37 * hash + 21;
    hash = 53 * hash + getBiz().hashCode();
    hash = 37 * hash + 22;
    hash = 53 * hash + getAccess().hashCode();
    hash = 29 * hash + getUnknownFields().hashCode();
    this.memoizedHashCode = hash;
    return hash;
  }
  
  public static PreMessage parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
    return (PreMessage)PARSER.parseFrom(data);
  }
  
  public static PreMessage parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (PreMessage)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static PreMessage parseFrom(ByteString data) throws InvalidProtocolBufferException {
    return (PreMessage)PARSER.parseFrom(data);
  }
  
  public static PreMessage parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (PreMessage)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static PreMessage parseFrom(byte[] data) throws InvalidProtocolBufferException {
    return (PreMessage)PARSER.parseFrom(data);
  }
  
  public static PreMessage parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (PreMessage)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static PreMessage parseFrom(InputStream input) throws IOException {
    return 
      (PreMessage)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static PreMessage parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (PreMessage)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static PreMessage parseDelimitedFrom(InputStream input) throws IOException {
    return 
      (PreMessage)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
  }
  
  public static PreMessage parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (PreMessage)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static PreMessage parseFrom(CodedInputStream input) throws IOException {
    return 
      (PreMessage)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static PreMessage parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (PreMessage)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public Builder newBuilderForType() {
    return newBuilder();
  }
  
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  
  public static Builder newBuilder(PreMessage prototype) {
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
  
  public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements PreMessageOrBuilder {
    private int bitField0_;
    
    private int cmd_;
    
    private int sequenceId_;
    
    private Object sdkVersion_;
    
    private Object token_;
    
    private int refer_;
    
    private int inboxType_;
    
    private Object buildNumber_;
    
    private SendMessageBody sendMessageBody_;
    
    private SingleFieldBuilderV3<SendMessageBody, SendMessageBody.Builder, SendMessageBodyOrBuilder> sendMessageBodyBuilder_;
    
    private Object aa_;
    
    private Object devicePlatform_;
    
    private List<HeadersList> headers_;
    
    private RepeatedFieldBuilderV3<HeadersList, HeadersList.Builder, HeadersListOrBuilder> headersBuilder_;
    
    private int authType_;
    
    private Object biz_;
    
    private Object access_;
    
    public static final Descriptors.Descriptor getDescriptor() {
      return Douyin.internal_static_PreMessage_descriptor;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return Douyin.internal_static_PreMessage_fieldAccessorTable
        .ensureFieldAccessorsInitialized(PreMessage.class, Builder.class);
    }
    
    private Builder() {
      this.sdkVersion_ = "";
      this.token_ = "";
      this.buildNumber_ = "";
      this.aa_ = "";
      this.devicePlatform_ = "";
      this
        .headers_ = Collections.emptyList();
      this.biz_ = "";
      this.access_ = "";
      maybeForceBuilderInitialization();
    }
    
    private Builder(GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      this.sdkVersion_ = "";
      this.token_ = "";
      this.buildNumber_ = "";
      this.aa_ = "";
      this.devicePlatform_ = "";
      this.headers_ = Collections.emptyList();
      this.biz_ = "";
      this.access_ = "";
      maybeForceBuilderInitialization();
    }
    
    private void maybeForceBuilderInitialization() {
      if (PreMessage.alwaysUseFieldBuilders) {
        getSendMessageBodyFieldBuilder();
        getHeadersFieldBuilder();
      } 
    }
    
    public Builder clear() {
      super.clear();
      this.bitField0_ = 0;
      this.cmd_ = 0;
      this.sequenceId_ = 0;
      this.sdkVersion_ = "";
      this.token_ = "";
      this.refer_ = 0;
      this.inboxType_ = 0;
      this.buildNumber_ = "";
      this.sendMessageBody_ = null;
      if (this.sendMessageBodyBuilder_ != null) {
        this.sendMessageBodyBuilder_.dispose();
        this.sendMessageBodyBuilder_ = null;
      } 
      this.aa_ = "";
      this.devicePlatform_ = "";
      if (this.headersBuilder_ == null) {
        this.headers_ = Collections.emptyList();
      } else {
        this.headers_ = null;
        this.headersBuilder_.clear();
      } 
      this.bitField0_ &= 0xFFFFFBFF;
      this.authType_ = 0;
      this.biz_ = "";
      this.access_ = "";
      return this;
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
      return Douyin.internal_static_PreMessage_descriptor;
    }
    
    public PreMessage getDefaultInstanceForType() {
      return PreMessage.getDefaultInstance();
    }
    
    public PreMessage build() {
      PreMessage result = buildPartial();
      if (!result.isInitialized())
        throw newUninitializedMessageException(result); 
      return result;
    }
    
    public PreMessage buildPartial() {
      PreMessage result = new PreMessage(this);
      buildPartialRepeatedFields(result);
      if (this.bitField0_ != 0)
        buildPartial0(result); 
      onBuilt();
      return result;
    }
    
    private void buildPartialRepeatedFields(PreMessage result) {
      if (this.headersBuilder_ == null) {
        if ((this.bitField0_ & 0x400) != 0) {
          this.headers_ = Collections.unmodifiableList(this.headers_);
          this.bitField0_ &= 0xFFFFFBFF;
        } 
        result.headers_ = this.headers_;
      } else {
        result.headers_ = this.headersBuilder_.build();
      } 
    }
    
    private void buildPartial0(PreMessage result) {
      int from_bitField0_ = this.bitField0_;
      if ((from_bitField0_ & 0x1) != 0)
        result.cmd_ = this.cmd_; 
      if ((from_bitField0_ & 0x2) != 0)
        result.sequenceId_ = this.sequenceId_; 
      if ((from_bitField0_ & 0x4) != 0)
        result.sdkVersion_ = this.sdkVersion_; 
      if ((from_bitField0_ & 0x8) != 0)
        result.token_ = this.token_; 
      if ((from_bitField0_ & 0x10) != 0)
        result.refer_ = this.refer_; 
      if ((from_bitField0_ & 0x20) != 0)
        result.inboxType_ = this.inboxType_; 
      if ((from_bitField0_ & 0x40) != 0)
        result.buildNumber_ = this.buildNumber_; 
      int to_bitField0_ = 0;
      if ((from_bitField0_ & 0x80) != 0) {
        result.sendMessageBody_ = (this.sendMessageBodyBuilder_ == null) ? this.sendMessageBody_ : (SendMessageBody)this.sendMessageBodyBuilder_.build();
        to_bitField0_ |= 0x1;
      } 
      if ((from_bitField0_ & 0x100) != 0)
        result.aa_ = this.aa_; 
      if ((from_bitField0_ & 0x200) != 0)
        result.devicePlatform_ = this.devicePlatform_; 
      if ((from_bitField0_ & 0x800) != 0)
        result.authType_ = this.authType_; 
      if ((from_bitField0_ & 0x1000) != 0)
        result.biz_ = this.biz_; 
      if ((from_bitField0_ & 0x2000) != 0)
        result.access_ = this.access_; 
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
      if (other instanceof PreMessage)
        return mergeFrom((PreMessage)other); 
      super.mergeFrom(other);
      return this;
    }
    
    public Builder mergeFrom(PreMessage other) {
      if (other == PreMessage.getDefaultInstance())
        return this; 
      if (other.getCmd() != 0)
        setCmd(other.getCmd()); 
      if (other.getSequenceId() != 0)
        setSequenceId(other.getSequenceId()); 
      if (!other.getSdkVersion().isEmpty()) {
        this.sdkVersion_ = other.sdkVersion_;
        this.bitField0_ |= 0x4;
        onChanged();
      } 
      if (!other.getToken().isEmpty()) {
        this.token_ = other.token_;
        this.bitField0_ |= 0x8;
        onChanged();
      } 
      if (other.getRefer() != 0)
        setRefer(other.getRefer()); 
      if (other.getInboxType() != 0)
        setInboxType(other.getInboxType()); 
      if (!other.getBuildNumber().isEmpty()) {
        this.buildNumber_ = other.buildNumber_;
        this.bitField0_ |= 0x40;
        onChanged();
      } 
      if (other.hasSendMessageBody())
        mergeSendMessageBody(other.getSendMessageBody()); 
      if (!other.getAa().isEmpty()) {
        this.aa_ = other.aa_;
        this.bitField0_ |= 0x100;
        onChanged();
      } 
      if (!other.getDevicePlatform().isEmpty()) {
        this.devicePlatform_ = other.devicePlatform_;
        this.bitField0_ |= 0x200;
        onChanged();
      } 
      if (this.headersBuilder_ == null) {
        if (!other.headers_.isEmpty()) {
          if (this.headers_.isEmpty()) {
            this.headers_ = other.headers_;
            this.bitField0_ &= 0xFFFFFBFF;
          } else {
            ensureHeadersIsMutable();
            this.headers_.addAll(other.headers_);
          } 
          onChanged();
        } 
      } else if (!other.headers_.isEmpty()) {
        if (this.headersBuilder_.isEmpty()) {
          this.headersBuilder_.dispose();
          this.headersBuilder_ = null;
          this.headers_ = other.headers_;
          this.bitField0_ &= 0xFFFFFBFF;
          this.headersBuilder_ = PreMessage.alwaysUseFieldBuilders ? getHeadersFieldBuilder() : null;
        } else {
          this.headersBuilder_.addAllMessages(other.headers_);
        } 
      } 
      if (other.getAuthType() != 0)
        setAuthType(other.getAuthType()); 
      if (!other.getBiz().isEmpty()) {
        this.biz_ = other.biz_;
        this.bitField0_ |= 0x1000;
        onChanged();
      } 
      if (!other.getAccess().isEmpty()) {
        this.access_ = other.access_;
        this.bitField0_ |= 0x2000;
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
          HeadersList m;
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              continue;
            case 8:
              this.cmd_ = input.readUInt32();
              this.bitField0_ |= 0x1;
              continue;
            case 16:
              this.sequenceId_ = input.readUInt32();
              this.bitField0_ |= 0x2;
              continue;
            case 26:
              this.sdkVersion_ = input.readStringRequireUtf8();
              this.bitField0_ |= 0x4;
              continue;
            case 34:
              this.token_ = input.readStringRequireUtf8();
              this.bitField0_ |= 0x8;
              continue;
            case 40:
              this.refer_ = input.readUInt32();
              this.bitField0_ |= 0x10;
              continue;
            case 48:
              this.inboxType_ = input.readUInt32();
              this.bitField0_ |= 0x20;
              continue;
            case 58:
              this.buildNumber_ = input.readStringRequireUtf8();
              this.bitField0_ |= 0x40;
              continue;
            case 66:
              input.readMessage((MessageLite.Builder)getSendMessageBodyFieldBuilder().getBuilder(), extensionRegistry);
              this.bitField0_ |= 0x80;
              continue;
            case 74:
              this.aa_ = input.readStringRequireUtf8();
              this.bitField0_ |= 0x100;
              continue;
            case 90:
              this.devicePlatform_ = input.readStringRequireUtf8();
              this.bitField0_ |= 0x200;
              continue;
            case 122:
              m = (HeadersList)input.readMessage(HeadersList.parser(), extensionRegistry);
              if (this.headersBuilder_ == null) {
                ensureHeadersIsMutable();
                this.headers_.add(m);
                continue;
              } 
              this.headersBuilder_.addMessage((AbstractMessage)m);
              continue;
            case 144:
              this.authType_ = input.readUInt32();
              this.bitField0_ |= 0x800;
              continue;
            case 170:
              this.biz_ = input.readStringRequireUtf8();
              this.bitField0_ |= 0x1000;
              continue;
            case 178:
              this.access_ = input.readStringRequireUtf8();
              this.bitField0_ |= 0x2000;
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
    
    public int getCmd() {
      return this.cmd_;
    }
    
    public Builder setCmd(int value) {
      this.cmd_ = value;
      this.bitField0_ |= 0x1;
      onChanged();
      return this;
    }
    
    public Builder clearCmd() {
      this.bitField0_ &= 0xFFFFFFFE;
      this.cmd_ = 0;
      onChanged();
      return this;
    }
    
    public int getSequenceId() {
      return this.sequenceId_;
    }
    
    public Builder setSequenceId(int value) {
      this.sequenceId_ = value;
      this.bitField0_ |= 0x2;
      onChanged();
      return this;
    }
    
    public Builder clearSequenceId() {
      this.bitField0_ &= 0xFFFFFFFD;
      this.sequenceId_ = 0;
      onChanged();
      return this;
    }
    
    public String getSdkVersion() {
      Object ref = this.sdkVersion_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.sdkVersion_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getSdkVersionBytes() {
      Object ref = this.sdkVersion_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.sdkVersion_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setSdkVersion(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.sdkVersion_ = value;
      this.bitField0_ |= 0x4;
      onChanged();
      return this;
    }
    
    public Builder clearSdkVersion() {
      this.sdkVersion_ = PreMessage.getDefaultInstance().getSdkVersion();
      this.bitField0_ &= 0xFFFFFFFB;
      onChanged();
      return this;
    }
    
    public Builder setSdkVersionBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      PreMessage.checkByteStringIsUtf8(value);
      this.sdkVersion_ = value;
      this.bitField0_ |= 0x4;
      onChanged();
      return this;
    }
    
    public String getToken() {
      Object ref = this.token_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.token_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getTokenBytes() {
      Object ref = this.token_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.token_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setToken(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.token_ = value;
      this.bitField0_ |= 0x8;
      onChanged();
      return this;
    }
    
    public Builder clearToken() {
      this.token_ = PreMessage.getDefaultInstance().getToken();
      this.bitField0_ &= 0xFFFFFFF7;
      onChanged();
      return this;
    }
    
    public Builder setTokenBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      PreMessage.checkByteStringIsUtf8(value);
      this.token_ = value;
      this.bitField0_ |= 0x8;
      onChanged();
      return this;
    }
    
    public int getRefer() {
      return this.refer_;
    }
    
    public Builder setRefer(int value) {
      this.refer_ = value;
      this.bitField0_ |= 0x10;
      onChanged();
      return this;
    }
    
    public Builder clearRefer() {
      this.bitField0_ &= 0xFFFFFFEF;
      this.refer_ = 0;
      onChanged();
      return this;
    }
    
    public int getInboxType() {
      return this.inboxType_;
    }
    
    public Builder setInboxType(int value) {
      this.inboxType_ = value;
      this.bitField0_ |= 0x20;
      onChanged();
      return this;
    }
    
    public Builder clearInboxType() {
      this.bitField0_ &= 0xFFFFFFDF;
      this.inboxType_ = 0;
      onChanged();
      return this;
    }
    
    public String getBuildNumber() {
      Object ref = this.buildNumber_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.buildNumber_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getBuildNumberBytes() {
      Object ref = this.buildNumber_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.buildNumber_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setBuildNumber(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.buildNumber_ = value;
      this.bitField0_ |= 0x40;
      onChanged();
      return this;
    }
    
    public Builder clearBuildNumber() {
      this.buildNumber_ = PreMessage.getDefaultInstance().getBuildNumber();
      this.bitField0_ &= 0xFFFFFFBF;
      onChanged();
      return this;
    }
    
    public Builder setBuildNumberBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      PreMessage.checkByteStringIsUtf8(value);
      this.buildNumber_ = value;
      this.bitField0_ |= 0x40;
      onChanged();
      return this;
    }
    
    public boolean hasSendMessageBody() {
      return ((this.bitField0_ & 0x80) != 0);
    }
    
    public SendMessageBody getSendMessageBody() {
      if (this.sendMessageBodyBuilder_ == null)
        return (this.sendMessageBody_ == null) ? SendMessageBody.getDefaultInstance() : this.sendMessageBody_; 
      return (SendMessageBody)this.sendMessageBodyBuilder_.getMessage();
    }
    
    public Builder setSendMessageBody(SendMessageBody value) {
      if (this.sendMessageBodyBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        this.sendMessageBody_ = value;
      } else {
        this.sendMessageBodyBuilder_.setMessage((AbstractMessage)value);
      } 
      this.bitField0_ |= 0x80;
      onChanged();
      return this;
    }
    
    public Builder setSendMessageBody(SendMessageBody.Builder builderForValue) {
      if (this.sendMessageBodyBuilder_ == null) {
        this.sendMessageBody_ = builderForValue.build();
      } else {
        this.sendMessageBodyBuilder_.setMessage((AbstractMessage)builderForValue.build());
      } 
      this.bitField0_ |= 0x80;
      onChanged();
      return this;
    }
    
    public Builder mergeSendMessageBody(SendMessageBody value) {
      if (this.sendMessageBodyBuilder_ == null) {
        if ((this.bitField0_ & 0x80) != 0 && this.sendMessageBody_ != null && this.sendMessageBody_ != SendMessageBody.getDefaultInstance()) {
          getSendMessageBodyBuilder().mergeFrom(value);
        } else {
          this.sendMessageBody_ = value;
        } 
      } else {
        this.sendMessageBodyBuilder_.mergeFrom((AbstractMessage)value);
      } 
      if (this.sendMessageBody_ != null) {
        this.bitField0_ |= 0x80;
        onChanged();
      } 
      return this;
    }
    
    public Builder clearSendMessageBody() {
      this.bitField0_ &= 0xFFFFFF7F;
      this.sendMessageBody_ = null;
      if (this.sendMessageBodyBuilder_ != null) {
        this.sendMessageBodyBuilder_.dispose();
        this.sendMessageBodyBuilder_ = null;
      } 
      onChanged();
      return this;
    }
    
    public SendMessageBody.Builder getSendMessageBodyBuilder() {
      this.bitField0_ |= 0x80;
      onChanged();
      return (SendMessageBody.Builder)getSendMessageBodyFieldBuilder().getBuilder();
    }
    
    public SendMessageBodyOrBuilder getSendMessageBodyOrBuilder() {
      if (this.sendMessageBodyBuilder_ != null)
        return (SendMessageBodyOrBuilder)this.sendMessageBodyBuilder_.getMessageOrBuilder(); 
      return (this.sendMessageBody_ == null) ? SendMessageBody.getDefaultInstance() : this.sendMessageBody_;
    }
    
    private SingleFieldBuilderV3<SendMessageBody, SendMessageBody.Builder, SendMessageBodyOrBuilder> getSendMessageBodyFieldBuilder() {
      if (this.sendMessageBodyBuilder_ == null) {
        this.sendMessageBodyBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getSendMessageBody(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.sendMessageBody_ = null;
      } 
      return this.sendMessageBodyBuilder_;
    }
    
    public String getAa() {
      Object ref = this.aa_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.aa_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getAaBytes() {
      Object ref = this.aa_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.aa_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setAa(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.aa_ = value;
      this.bitField0_ |= 0x100;
      onChanged();
      return this;
    }
    
    public Builder clearAa() {
      this.aa_ = PreMessage.getDefaultInstance().getAa();
      this.bitField0_ &= 0xFFFFFEFF;
      onChanged();
      return this;
    }
    
    public Builder setAaBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      PreMessage.checkByteStringIsUtf8(value);
      this.aa_ = value;
      this.bitField0_ |= 0x100;
      onChanged();
      return this;
    }
    
    public String getDevicePlatform() {
      Object ref = this.devicePlatform_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.devicePlatform_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getDevicePlatformBytes() {
      Object ref = this.devicePlatform_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.devicePlatform_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setDevicePlatform(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.devicePlatform_ = value;
      this.bitField0_ |= 0x200;
      onChanged();
      return this;
    }
    
    public Builder clearDevicePlatform() {
      this.devicePlatform_ = PreMessage.getDefaultInstance().getDevicePlatform();
      this.bitField0_ &= 0xFFFFFDFF;
      onChanged();
      return this;
    }
    
    public Builder setDevicePlatformBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      PreMessage.checkByteStringIsUtf8(value);
      this.devicePlatform_ = value;
      this.bitField0_ |= 0x200;
      onChanged();
      return this;
    }
    
    private void ensureHeadersIsMutable() {
      if ((this.bitField0_ & 0x400) == 0) {
        this.headers_ = new ArrayList<>(this.headers_);
        this.bitField0_ |= 0x400;
      } 
    }
    
    public List<HeadersList> getHeadersList() {
      if (this.headersBuilder_ == null)
        return Collections.unmodifiableList(this.headers_); 
      return this.headersBuilder_.getMessageList();
    }
    
    public int getHeadersCount() {
      if (this.headersBuilder_ == null)
        return this.headers_.size(); 
      return this.headersBuilder_.getCount();
    }
    
    public HeadersList getHeaders(int index) {
      if (this.headersBuilder_ == null)
        return this.headers_.get(index); 
      return (HeadersList)this.headersBuilder_.getMessage(index);
    }
    
    public Builder setHeaders(int index, HeadersList value) {
      if (this.headersBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        ensureHeadersIsMutable();
        this.headers_.set(index, value);
        onChanged();
      } else {
        this.headersBuilder_.setMessage(index, (AbstractMessage)value);
      } 
      return this;
    }
    
    public Builder setHeaders(int index, HeadersList.Builder builderForValue) {
      if (this.headersBuilder_ == null) {
        ensureHeadersIsMutable();
        this.headers_.set(index, builderForValue.build());
        onChanged();
      } else {
        this.headersBuilder_.setMessage(index, (AbstractMessage)builderForValue.build());
      } 
      return this;
    }
    
    public Builder addHeaders(HeadersList value) {
      if (this.headersBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        ensureHeadersIsMutable();
        this.headers_.add(value);
        onChanged();
      } else {
        this.headersBuilder_.addMessage((AbstractMessage)value);
      } 
      return this;
    }
    
    public Builder addHeaders(int index, HeadersList value) {
      if (this.headersBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        ensureHeadersIsMutable();
        this.headers_.add(index, value);
        onChanged();
      } else {
        this.headersBuilder_.addMessage(index, (AbstractMessage)value);
      } 
      return this;
    }
    
    public Builder addHeaders(HeadersList.Builder builderForValue) {
      if (this.headersBuilder_ == null) {
        ensureHeadersIsMutable();
        this.headers_.add(builderForValue.build());
        onChanged();
      } else {
        this.headersBuilder_.addMessage((AbstractMessage)builderForValue.build());
      } 
      return this;
    }
    
    public Builder addHeaders(int index, HeadersList.Builder builderForValue) {
      if (this.headersBuilder_ == null) {
        ensureHeadersIsMutable();
        this.headers_.add(index, builderForValue.build());
        onChanged();
      } else {
        this.headersBuilder_.addMessage(index, (AbstractMessage)builderForValue.build());
      } 
      return this;
    }
    
    public Builder addAllHeaders(Iterable<? extends HeadersList> values) {
      if (this.headersBuilder_ == null) {
        ensureHeadersIsMutable();
        AbstractMessageLite.Builder.addAll(values, this.headers_);
        onChanged();
      } else {
        this.headersBuilder_.addAllMessages(values);
      } 
      return this;
    }
    
    public Builder clearHeaders() {
      if (this.headersBuilder_ == null) {
        this.headers_ = Collections.emptyList();
        this.bitField0_ &= 0xFFFFFBFF;
        onChanged();
      } else {
        this.headersBuilder_.clear();
      } 
      return this;
    }
    
    public Builder removeHeaders(int index) {
      if (this.headersBuilder_ == null) {
        ensureHeadersIsMutable();
        this.headers_.remove(index);
        onChanged();
      } else {
        this.headersBuilder_.remove(index);
      } 
      return this;
    }
    
    public HeadersList.Builder getHeadersBuilder(int index) {
      return (HeadersList.Builder)getHeadersFieldBuilder().getBuilder(index);
    }
    
    public HeadersListOrBuilder getHeadersOrBuilder(int index) {
      if (this.headersBuilder_ == null)
        return this.headers_.get(index); 
      return (HeadersListOrBuilder)this.headersBuilder_.getMessageOrBuilder(index);
    }
    
    public List<? extends HeadersListOrBuilder> getHeadersOrBuilderList() {
      if (this.headersBuilder_ != null)
        return this.headersBuilder_.getMessageOrBuilderList(); 
      return Collections.unmodifiableList((List)this.headers_);
    }
    
    public HeadersList.Builder addHeadersBuilder() {
      return (HeadersList.Builder)getHeadersFieldBuilder().addBuilder((AbstractMessage)HeadersList.getDefaultInstance());
    }
    
    public HeadersList.Builder addHeadersBuilder(int index) {
      return (HeadersList.Builder)getHeadersFieldBuilder().addBuilder(index, (AbstractMessage)HeadersList.getDefaultInstance());
    }
    
    public List<HeadersList.Builder> getHeadersBuilderList() {
      return getHeadersFieldBuilder().getBuilderList();
    }
    
    private RepeatedFieldBuilderV3<HeadersList, HeadersList.Builder, HeadersListOrBuilder> getHeadersFieldBuilder() {
      if (this.headersBuilder_ == null) {
        this.headersBuilder_ = new RepeatedFieldBuilderV3(this.headers_, ((this.bitField0_ & 0x400) != 0), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.headers_ = null;
      } 
      return this.headersBuilder_;
    }
    
    public int getAuthType() {
      return this.authType_;
    }
    
    public Builder setAuthType(int value) {
      this.authType_ = value;
      this.bitField0_ |= 0x800;
      onChanged();
      return this;
    }
    
    public Builder clearAuthType() {
      this.bitField0_ &= 0xFFFFF7FF;
      this.authType_ = 0;
      onChanged();
      return this;
    }
    
    public String getBiz() {
      Object ref = this.biz_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.biz_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getBizBytes() {
      Object ref = this.biz_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.biz_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setBiz(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.biz_ = value;
      this.bitField0_ |= 0x1000;
      onChanged();
      return this;
    }
    
    public Builder clearBiz() {
      this.biz_ = PreMessage.getDefaultInstance().getBiz();
      this.bitField0_ &= 0xFFFFEFFF;
      onChanged();
      return this;
    }
    
    public Builder setBizBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      PreMessage.checkByteStringIsUtf8(value);
      this.biz_ = value;
      this.bitField0_ |= 0x1000;
      onChanged();
      return this;
    }
    
    public String getAccess() {
      Object ref = this.access_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.access_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getAccessBytes() {
      Object ref = this.access_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.access_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setAccess(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.access_ = value;
      this.bitField0_ |= 0x2000;
      onChanged();
      return this;
    }
    
    public Builder clearAccess() {
      this.access_ = PreMessage.getDefaultInstance().getAccess();
      this.bitField0_ &= 0xFFFFDFFF;
      onChanged();
      return this;
    }
    
    public Builder setAccessBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      PreMessage.checkByteStringIsUtf8(value);
      this.access_ = value;
      this.bitField0_ |= 0x2000;
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
  
  private static final PreMessage DEFAULT_INSTANCE = new PreMessage();
  
  public static PreMessage getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }
  
  private static final Parser<PreMessage> PARSER = (Parser<PreMessage>)new AbstractParser<PreMessage>() {
      public PreMessage parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        PreMessage.Builder builder = PreMessage.newBuilder();
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
  
  public static Parser<PreMessage> parser() {
    return PARSER;
  }
  
  public Parser<PreMessage> getParserForType() {
    return PARSER;
  }
  
  public PreMessage getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }
}
