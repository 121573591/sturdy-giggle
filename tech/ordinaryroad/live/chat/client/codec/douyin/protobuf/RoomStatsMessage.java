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

public final class RoomStatsMessage extends GeneratedMessageV3 implements RoomStatsMessageOrBuilder {
  private static final long serialVersionUID = 0L;
  
  private int bitField0_;
  
  public static final int COMMON_FIELD_NUMBER = 1;
  
  private Common common_;
  
  public static final int DISPLAYSHORT_FIELD_NUMBER = 2;
  
  private volatile Object displayShort_;
  
  public static final int DISPLAYMIDDLE_FIELD_NUMBER = 3;
  
  private volatile Object displayMiddle_;
  
  public static final int DISPLAYLONG_FIELD_NUMBER = 4;
  
  private volatile Object displayLong_;
  
  public static final int DISPLAYVALUE_FIELD_NUMBER = 5;
  
  private long displayValue_;
  
  public static final int DISPLAYVERSION_FIELD_NUMBER = 6;
  
  private long displayVersion_;
  
  public static final int INCREMENTAL_FIELD_NUMBER = 7;
  
  private boolean incremental_;
  
  public static final int ISHIDDEN_FIELD_NUMBER = 8;
  
  private boolean isHidden_;
  
  public static final int TOTAL_FIELD_NUMBER = 9;
  
  private long total_;
  
  public static final int DISPLAYTYPE_FIELD_NUMBER = 10;
  
  private long displayType_;
  
  private byte memoizedIsInitialized;
  
  private RoomStatsMessage(GeneratedMessageV3.Builder<?> builder) {
    super(builder);
    this.displayShort_ = "";
    this.displayMiddle_ = "";
    this.displayLong_ = "";
    this.displayValue_ = 0L;
    this.displayVersion_ = 0L;
    this.incremental_ = false;
    this.isHidden_ = false;
    this.total_ = 0L;
    this.displayType_ = 0L;
    this.memoizedIsInitialized = -1;
  }
  
  private RoomStatsMessage() {
    this.displayShort_ = "";
    this.displayMiddle_ = "";
    this.displayLong_ = "";
    this.displayValue_ = 0L;
    this.displayVersion_ = 0L;
    this.incremental_ = false;
    this.isHidden_ = false;
    this.total_ = 0L;
    this.displayType_ = 0L;
    this.memoizedIsInitialized = -1;
    this.displayShort_ = "";
    this.displayMiddle_ = "";
    this.displayLong_ = "";
  }
  
  protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
    return new RoomStatsMessage();
  }
  
  public static final Descriptors.Descriptor getDescriptor() {
    return Douyin.internal_static_RoomStatsMessage_descriptor;
  }
  
  protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
    return Douyin.internal_static_RoomStatsMessage_fieldAccessorTable.ensureFieldAccessorsInitialized(RoomStatsMessage.class, Builder.class);
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
  
  public String getDisplayShort() {
    Object ref = this.displayShort_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.displayShort_ = s;
    return s;
  }
  
  public ByteString getDisplayShortBytes() {
    Object ref = this.displayShort_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.displayShort_ = b;
      return b;
    } 
    return (ByteString)ref;
  }
  
  public String getDisplayMiddle() {
    Object ref = this.displayMiddle_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.displayMiddle_ = s;
    return s;
  }
  
  public ByteString getDisplayMiddleBytes() {
    Object ref = this.displayMiddle_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.displayMiddle_ = b;
      return b;
    } 
    return (ByteString)ref;
  }
  
  public String getDisplayLong() {
    Object ref = this.displayLong_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.displayLong_ = s;
    return s;
  }
  
  public ByteString getDisplayLongBytes() {
    Object ref = this.displayLong_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.displayLong_ = b;
      return b;
    } 
    return (ByteString)ref;
  }
  
  public long getDisplayValue() {
    return this.displayValue_;
  }
  
  public long getDisplayVersion() {
    return this.displayVersion_;
  }
  
  public boolean getIncremental() {
    return this.incremental_;
  }
  
  public boolean getIsHidden() {
    return this.isHidden_;
  }
  
  public long getTotal() {
    return this.total_;
  }
  
  public long getDisplayType() {
    return this.displayType_;
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
    if (!GeneratedMessageV3.isStringEmpty(this.displayShort_))
      GeneratedMessageV3.writeString(output, 2, this.displayShort_); 
    if (!GeneratedMessageV3.isStringEmpty(this.displayMiddle_))
      GeneratedMessageV3.writeString(output, 3, this.displayMiddle_); 
    if (!GeneratedMessageV3.isStringEmpty(this.displayLong_))
      GeneratedMessageV3.writeString(output, 4, this.displayLong_); 
    if (this.displayValue_ != 0L)
      output.writeInt64(5, this.displayValue_); 
    if (this.displayVersion_ != 0L)
      output.writeInt64(6, this.displayVersion_); 
    if (this.incremental_)
      output.writeBool(7, this.incremental_); 
    if (this.isHidden_)
      output.writeBool(8, this.isHidden_); 
    if (this.total_ != 0L)
      output.writeInt64(9, this.total_); 
    if (this.displayType_ != 0L)
      output.writeInt64(10, this.displayType_); 
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
    if (!GeneratedMessageV3.isStringEmpty(this.displayShort_))
      size += GeneratedMessageV3.computeStringSize(2, this.displayShort_); 
    if (!GeneratedMessageV3.isStringEmpty(this.displayMiddle_))
      size += GeneratedMessageV3.computeStringSize(3, this.displayMiddle_); 
    if (!GeneratedMessageV3.isStringEmpty(this.displayLong_))
      size += GeneratedMessageV3.computeStringSize(4, this.displayLong_); 
    if (this.displayValue_ != 0L)
      size += 
        CodedOutputStream.computeInt64Size(5, this.displayValue_); 
    if (this.displayVersion_ != 0L)
      size += 
        CodedOutputStream.computeInt64Size(6, this.displayVersion_); 
    if (this.incremental_)
      size += 
        CodedOutputStream.computeBoolSize(7, this.incremental_); 
    if (this.isHidden_)
      size += 
        CodedOutputStream.computeBoolSize(8, this.isHidden_); 
    if (this.total_ != 0L)
      size += 
        CodedOutputStream.computeInt64Size(9, this.total_); 
    if (this.displayType_ != 0L)
      size += 
        CodedOutputStream.computeInt64Size(10, this.displayType_); 
    size += getUnknownFields().getSerializedSize();
    this.memoizedSize = size;
    return size;
  }
  
  public boolean equals(Object obj) {
    if (obj == this)
      return true; 
    if (!(obj instanceof RoomStatsMessage))
      return super.equals(obj); 
    RoomStatsMessage other = (RoomStatsMessage)obj;
    if (hasCommon() != other.hasCommon())
      return false; 
    if (hasCommon() && 
      
      !getCommon().equals(other.getCommon()))
      return false; 
    if (!getDisplayShort().equals(other.getDisplayShort()))
      return false; 
    if (!getDisplayMiddle().equals(other.getDisplayMiddle()))
      return false; 
    if (!getDisplayLong().equals(other.getDisplayLong()))
      return false; 
    if (getDisplayValue() != other
      .getDisplayValue())
      return false; 
    if (getDisplayVersion() != other
      .getDisplayVersion())
      return false; 
    if (getIncremental() != other
      .getIncremental())
      return false; 
    if (getIsHidden() != other
      .getIsHidden())
      return false; 
    if (getTotal() != other
      .getTotal())
      return false; 
    if (getDisplayType() != other
      .getDisplayType())
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
    hash = 37 * hash + 2;
    hash = 53 * hash + getDisplayShort().hashCode();
    hash = 37 * hash + 3;
    hash = 53 * hash + getDisplayMiddle().hashCode();
    hash = 37 * hash + 4;
    hash = 53 * hash + getDisplayLong().hashCode();
    hash = 37 * hash + 5;
    hash = 53 * hash + Internal.hashLong(
        getDisplayValue());
    hash = 37 * hash + 6;
    hash = 53 * hash + Internal.hashLong(
        getDisplayVersion());
    hash = 37 * hash + 7;
    hash = 53 * hash + Internal.hashBoolean(
        getIncremental());
    hash = 37 * hash + 8;
    hash = 53 * hash + Internal.hashBoolean(
        getIsHidden());
    hash = 37 * hash + 9;
    hash = 53 * hash + Internal.hashLong(
        getTotal());
    hash = 37 * hash + 10;
    hash = 53 * hash + Internal.hashLong(
        getDisplayType());
    hash = 29 * hash + getUnknownFields().hashCode();
    this.memoizedHashCode = hash;
    return hash;
  }
  
  public static RoomStatsMessage parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
    return (RoomStatsMessage)PARSER.parseFrom(data);
  }
  
  public static RoomStatsMessage parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (RoomStatsMessage)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static RoomStatsMessage parseFrom(ByteString data) throws InvalidProtocolBufferException {
    return (RoomStatsMessage)PARSER.parseFrom(data);
  }
  
  public static RoomStatsMessage parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (RoomStatsMessage)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static RoomStatsMessage parseFrom(byte[] data) throws InvalidProtocolBufferException {
    return (RoomStatsMessage)PARSER.parseFrom(data);
  }
  
  public static RoomStatsMessage parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (RoomStatsMessage)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static RoomStatsMessage parseFrom(InputStream input) throws IOException {
    return 
      (RoomStatsMessage)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static RoomStatsMessage parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (RoomStatsMessage)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static RoomStatsMessage parseDelimitedFrom(InputStream input) throws IOException {
    return 
      (RoomStatsMessage)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
  }
  
  public static RoomStatsMessage parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (RoomStatsMessage)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static RoomStatsMessage parseFrom(CodedInputStream input) throws IOException {
    return 
      (RoomStatsMessage)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static RoomStatsMessage parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (RoomStatsMessage)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public Builder newBuilderForType() {
    return newBuilder();
  }
  
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  
  public static Builder newBuilder(RoomStatsMessage prototype) {
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
  
  public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements RoomStatsMessageOrBuilder {
    private int bitField0_;
    
    private Common common_;
    
    private SingleFieldBuilderV3<Common, Common.Builder, CommonOrBuilder> commonBuilder_;
    
    private Object displayShort_;
    
    private Object displayMiddle_;
    
    private Object displayLong_;
    
    private long displayValue_;
    
    private long displayVersion_;
    
    private boolean incremental_;
    
    private boolean isHidden_;
    
    private long total_;
    
    private long displayType_;
    
    public static final Descriptors.Descriptor getDescriptor() {
      return Douyin.internal_static_RoomStatsMessage_descriptor;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return Douyin.internal_static_RoomStatsMessage_fieldAccessorTable
        .ensureFieldAccessorsInitialized(RoomStatsMessage.class, Builder.class);
    }
    
    private Builder() {
      this.displayShort_ = "";
      this.displayMiddle_ = "";
      this.displayLong_ = "";
      maybeForceBuilderInitialization();
    }
    
    private Builder(GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      this.displayShort_ = "";
      this.displayMiddle_ = "";
      this.displayLong_ = "";
      maybeForceBuilderInitialization();
    }
    
    private void maybeForceBuilderInitialization() {
      if (RoomStatsMessage.alwaysUseFieldBuilders)
        getCommonFieldBuilder(); 
    }
    
    public Builder clear() {
      super.clear();
      this.bitField0_ = 0;
      this.common_ = null;
      if (this.commonBuilder_ != null) {
        this.commonBuilder_.dispose();
        this.commonBuilder_ = null;
      } 
      this.displayShort_ = "";
      this.displayMiddle_ = "";
      this.displayLong_ = "";
      this.displayValue_ = 0L;
      this.displayVersion_ = 0L;
      this.incremental_ = false;
      this.isHidden_ = false;
      this.total_ = 0L;
      this.displayType_ = 0L;
      return this;
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
      return Douyin.internal_static_RoomStatsMessage_descriptor;
    }
    
    public RoomStatsMessage getDefaultInstanceForType() {
      return RoomStatsMessage.getDefaultInstance();
    }
    
    public RoomStatsMessage build() {
      RoomStatsMessage result = buildPartial();
      if (!result.isInitialized())
        throw newUninitializedMessageException(result); 
      return result;
    }
    
    public RoomStatsMessage buildPartial() {
      RoomStatsMessage result = new RoomStatsMessage(this);
      if (this.bitField0_ != 0)
        buildPartial0(result); 
      onBuilt();
      return result;
    }
    
    private void buildPartial0(RoomStatsMessage result) {
      int from_bitField0_ = this.bitField0_;
      int to_bitField0_ = 0;
      if ((from_bitField0_ & 0x1) != 0) {
        result.common_ = (this.commonBuilder_ == null) ? this.common_ : (Common)this.commonBuilder_.build();
        to_bitField0_ |= 0x1;
      } 
      if ((from_bitField0_ & 0x2) != 0)
        result.displayShort_ = this.displayShort_; 
      if ((from_bitField0_ & 0x4) != 0)
        result.displayMiddle_ = this.displayMiddle_; 
      if ((from_bitField0_ & 0x8) != 0)
        result.displayLong_ = this.displayLong_; 
      if ((from_bitField0_ & 0x10) != 0)
        result.displayValue_ = this.displayValue_; 
      if ((from_bitField0_ & 0x20) != 0)
        result.displayVersion_ = this.displayVersion_; 
      if ((from_bitField0_ & 0x40) != 0)
        result.incremental_ = this.incremental_; 
      if ((from_bitField0_ & 0x80) != 0)
        result.isHidden_ = this.isHidden_; 
      if ((from_bitField0_ & 0x100) != 0)
        result.total_ = this.total_; 
      if ((from_bitField0_ & 0x200) != 0)
        result.displayType_ = this.displayType_; 
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
      if (other instanceof RoomStatsMessage)
        return mergeFrom((RoomStatsMessage)other); 
      super.mergeFrom(other);
      return this;
    }
    
    public Builder mergeFrom(RoomStatsMessage other) {
      if (other == RoomStatsMessage.getDefaultInstance())
        return this; 
      if (other.hasCommon())
        mergeCommon(other.getCommon()); 
      if (!other.getDisplayShort().isEmpty()) {
        this.displayShort_ = other.displayShort_;
        this.bitField0_ |= 0x2;
        onChanged();
      } 
      if (!other.getDisplayMiddle().isEmpty()) {
        this.displayMiddle_ = other.displayMiddle_;
        this.bitField0_ |= 0x4;
        onChanged();
      } 
      if (!other.getDisplayLong().isEmpty()) {
        this.displayLong_ = other.displayLong_;
        this.bitField0_ |= 0x8;
        onChanged();
      } 
      if (other.getDisplayValue() != 0L)
        setDisplayValue(other.getDisplayValue()); 
      if (other.getDisplayVersion() != 0L)
        setDisplayVersion(other.getDisplayVersion()); 
      if (other.getIncremental())
        setIncremental(other.getIncremental()); 
      if (other.getIsHidden())
        setIsHidden(other.getIsHidden()); 
      if (other.getTotal() != 0L)
        setTotal(other.getTotal()); 
      if (other.getDisplayType() != 0L)
        setDisplayType(other.getDisplayType()); 
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
              this.displayShort_ = input.readStringRequireUtf8();
              this.bitField0_ |= 0x2;
              continue;
            case 26:
              this.displayMiddle_ = input.readStringRequireUtf8();
              this.bitField0_ |= 0x4;
              continue;
            case 34:
              this.displayLong_ = input.readStringRequireUtf8();
              this.bitField0_ |= 0x8;
              continue;
            case 40:
              this.displayValue_ = input.readInt64();
              this.bitField0_ |= 0x10;
              continue;
            case 48:
              this.displayVersion_ = input.readInt64();
              this.bitField0_ |= 0x20;
              continue;
            case 56:
              this.incremental_ = input.readBool();
              this.bitField0_ |= 0x40;
              continue;
            case 64:
              this.isHidden_ = input.readBool();
              this.bitField0_ |= 0x80;
              continue;
            case 72:
              this.total_ = input.readInt64();
              this.bitField0_ |= 0x100;
              continue;
            case 80:
              this.displayType_ = input.readInt64();
              this.bitField0_ |= 0x200;
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
    
    public String getDisplayShort() {
      Object ref = this.displayShort_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.displayShort_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getDisplayShortBytes() {
      Object ref = this.displayShort_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.displayShort_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setDisplayShort(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.displayShort_ = value;
      this.bitField0_ |= 0x2;
      onChanged();
      return this;
    }
    
    public Builder clearDisplayShort() {
      this.displayShort_ = RoomStatsMessage.getDefaultInstance().getDisplayShort();
      this.bitField0_ &= 0xFFFFFFFD;
      onChanged();
      return this;
    }
    
    public Builder setDisplayShortBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      RoomStatsMessage.checkByteStringIsUtf8(value);
      this.displayShort_ = value;
      this.bitField0_ |= 0x2;
      onChanged();
      return this;
    }
    
    public String getDisplayMiddle() {
      Object ref = this.displayMiddle_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.displayMiddle_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getDisplayMiddleBytes() {
      Object ref = this.displayMiddle_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.displayMiddle_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setDisplayMiddle(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.displayMiddle_ = value;
      this.bitField0_ |= 0x4;
      onChanged();
      return this;
    }
    
    public Builder clearDisplayMiddle() {
      this.displayMiddle_ = RoomStatsMessage.getDefaultInstance().getDisplayMiddle();
      this.bitField0_ &= 0xFFFFFFFB;
      onChanged();
      return this;
    }
    
    public Builder setDisplayMiddleBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      RoomStatsMessage.checkByteStringIsUtf8(value);
      this.displayMiddle_ = value;
      this.bitField0_ |= 0x4;
      onChanged();
      return this;
    }
    
    public String getDisplayLong() {
      Object ref = this.displayLong_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.displayLong_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getDisplayLongBytes() {
      Object ref = this.displayLong_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.displayLong_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setDisplayLong(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.displayLong_ = value;
      this.bitField0_ |= 0x8;
      onChanged();
      return this;
    }
    
    public Builder clearDisplayLong() {
      this.displayLong_ = RoomStatsMessage.getDefaultInstance().getDisplayLong();
      this.bitField0_ &= 0xFFFFFFF7;
      onChanged();
      return this;
    }
    
    public Builder setDisplayLongBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      RoomStatsMessage.checkByteStringIsUtf8(value);
      this.displayLong_ = value;
      this.bitField0_ |= 0x8;
      onChanged();
      return this;
    }
    
    public long getDisplayValue() {
      return this.displayValue_;
    }
    
    public Builder setDisplayValue(long value) {
      this.displayValue_ = value;
      this.bitField0_ |= 0x10;
      onChanged();
      return this;
    }
    
    public Builder clearDisplayValue() {
      this.bitField0_ &= 0xFFFFFFEF;
      this.displayValue_ = 0L;
      onChanged();
      return this;
    }
    
    public long getDisplayVersion() {
      return this.displayVersion_;
    }
    
    public Builder setDisplayVersion(long value) {
      this.displayVersion_ = value;
      this.bitField0_ |= 0x20;
      onChanged();
      return this;
    }
    
    public Builder clearDisplayVersion() {
      this.bitField0_ &= 0xFFFFFFDF;
      this.displayVersion_ = 0L;
      onChanged();
      return this;
    }
    
    public boolean getIncremental() {
      return this.incremental_;
    }
    
    public Builder setIncremental(boolean value) {
      this.incremental_ = value;
      this.bitField0_ |= 0x40;
      onChanged();
      return this;
    }
    
    public Builder clearIncremental() {
      this.bitField0_ &= 0xFFFFFFBF;
      this.incremental_ = false;
      onChanged();
      return this;
    }
    
    public boolean getIsHidden() {
      return this.isHidden_;
    }
    
    public Builder setIsHidden(boolean value) {
      this.isHidden_ = value;
      this.bitField0_ |= 0x80;
      onChanged();
      return this;
    }
    
    public Builder clearIsHidden() {
      this.bitField0_ &= 0xFFFFFF7F;
      this.isHidden_ = false;
      onChanged();
      return this;
    }
    
    public long getTotal() {
      return this.total_;
    }
    
    public Builder setTotal(long value) {
      this.total_ = value;
      this.bitField0_ |= 0x100;
      onChanged();
      return this;
    }
    
    public Builder clearTotal() {
      this.bitField0_ &= 0xFFFFFEFF;
      this.total_ = 0L;
      onChanged();
      return this;
    }
    
    public long getDisplayType() {
      return this.displayType_;
    }
    
    public Builder setDisplayType(long value) {
      this.displayType_ = value;
      this.bitField0_ |= 0x200;
      onChanged();
      return this;
    }
    
    public Builder clearDisplayType() {
      this.bitField0_ &= 0xFFFFFDFF;
      this.displayType_ = 0L;
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
  
  private static final RoomStatsMessage DEFAULT_INSTANCE = new RoomStatsMessage();
  
  public static RoomStatsMessage getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }
  
  private static final Parser<RoomStatsMessage> PARSER = (Parser<RoomStatsMessage>)new AbstractParser<RoomStatsMessage>() {
      public RoomStatsMessage parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        RoomStatsMessage.Builder builder = RoomStatsMessage.newBuilder();
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
  
  public static Parser<RoomStatsMessage> parser() {
    return PARSER;
  }
  
  public Parser<RoomStatsMessage> getParserForType() {
    return PARSER;
  }
  
  public RoomStatsMessage getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }
}
