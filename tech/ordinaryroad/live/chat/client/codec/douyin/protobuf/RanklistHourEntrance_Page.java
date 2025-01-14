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
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class RanklistHourEntrance_Page extends GeneratedMessageV3 implements RanklistHourEntrance_PageOrBuilder {
  private static final long serialVersionUID = 0L;
  
  public static final int CONTENT_FIELD_NUMBER = 1;
  
  private volatile Object content_;
  
  public static final int BACKGROUND_COLOR_FIELD_NUMBER = 2;
  
  private volatile Object backgroundColor_;
  
  public static final int SHOW_TIMES_FIELD_NUMBER = 3;
  
  private long showTimes_;
  
  public static final int CONTENT_TYPE_FIELD_NUMBER = 4;
  
  private int contentType_;
  
  private byte memoizedIsInitialized;
  
  private RanklistHourEntrance_Page(GeneratedMessageV3.Builder<?> builder) {
    super(builder);
    this.content_ = "";
    this.backgroundColor_ = "";
    this.showTimes_ = 0L;
    this.contentType_ = 0;
    this.memoizedIsInitialized = -1;
  }
  
  private RanklistHourEntrance_Page() {
    this.content_ = "";
    this.backgroundColor_ = "";
    this.showTimes_ = 0L;
    this.contentType_ = 0;
    this.memoizedIsInitialized = -1;
    this.content_ = "";
    this.backgroundColor_ = "";
  }
  
  protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
    return new RanklistHourEntrance_Page();
  }
  
  public static final Descriptors.Descriptor getDescriptor() {
    return Douyin.internal_static_RanklistHourEntrance_Page_descriptor;
  }
  
  protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
    return Douyin.internal_static_RanklistHourEntrance_Page_fieldAccessorTable.ensureFieldAccessorsInitialized(RanklistHourEntrance_Page.class, Builder.class);
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
  
  public String getBackgroundColor() {
    Object ref = this.backgroundColor_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.backgroundColor_ = s;
    return s;
  }
  
  public ByteString getBackgroundColorBytes() {
    Object ref = this.backgroundColor_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.backgroundColor_ = b;
      return b;
    } 
    return (ByteString)ref;
  }
  
  public long getShowTimes() {
    return this.showTimes_;
  }
  
  public int getContentType() {
    return this.contentType_;
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
    if (!GeneratedMessageV3.isStringEmpty(this.content_))
      GeneratedMessageV3.writeString(output, 1, this.content_); 
    if (!GeneratedMessageV3.isStringEmpty(this.backgroundColor_))
      GeneratedMessageV3.writeString(output, 2, this.backgroundColor_); 
    if (this.showTimes_ != 0L)
      output.writeInt64(3, this.showTimes_); 
    if (this.contentType_ != 0)
      output.writeInt32(4, this.contentType_); 
    getUnknownFields().writeTo(output);
  }
  
  public int getSerializedSize() {
    int size = this.memoizedSize;
    if (size != -1)
      return size; 
    size = 0;
    if (!GeneratedMessageV3.isStringEmpty(this.content_))
      size += GeneratedMessageV3.computeStringSize(1, this.content_); 
    if (!GeneratedMessageV3.isStringEmpty(this.backgroundColor_))
      size += GeneratedMessageV3.computeStringSize(2, this.backgroundColor_); 
    if (this.showTimes_ != 0L)
      size += 
        CodedOutputStream.computeInt64Size(3, this.showTimes_); 
    if (this.contentType_ != 0)
      size += 
        CodedOutputStream.computeInt32Size(4, this.contentType_); 
    size += getUnknownFields().getSerializedSize();
    this.memoizedSize = size;
    return size;
  }
  
  public boolean equals(Object obj) {
    if (obj == this)
      return true; 
    if (!(obj instanceof RanklistHourEntrance_Page))
      return super.equals(obj); 
    RanklistHourEntrance_Page other = (RanklistHourEntrance_Page)obj;
    if (!getContent().equals(other.getContent()))
      return false; 
    if (!getBackgroundColor().equals(other.getBackgroundColor()))
      return false; 
    if (getShowTimes() != other
      .getShowTimes())
      return false; 
    if (getContentType() != other
      .getContentType())
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
    hash = 53 * hash + getContent().hashCode();
    hash = 37 * hash + 2;
    hash = 53 * hash + getBackgroundColor().hashCode();
    hash = 37 * hash + 3;
    hash = 53 * hash + Internal.hashLong(
        getShowTimes());
    hash = 37 * hash + 4;
    hash = 53 * hash + getContentType();
    hash = 29 * hash + getUnknownFields().hashCode();
    this.memoizedHashCode = hash;
    return hash;
  }
  
  public static RanklistHourEntrance_Page parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
    return (RanklistHourEntrance_Page)PARSER.parseFrom(data);
  }
  
  public static RanklistHourEntrance_Page parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (RanklistHourEntrance_Page)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static RanklistHourEntrance_Page parseFrom(ByteString data) throws InvalidProtocolBufferException {
    return (RanklistHourEntrance_Page)PARSER.parseFrom(data);
  }
  
  public static RanklistHourEntrance_Page parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (RanklistHourEntrance_Page)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static RanklistHourEntrance_Page parseFrom(byte[] data) throws InvalidProtocolBufferException {
    return (RanklistHourEntrance_Page)PARSER.parseFrom(data);
  }
  
  public static RanklistHourEntrance_Page parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (RanklistHourEntrance_Page)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static RanklistHourEntrance_Page parseFrom(InputStream input) throws IOException {
    return 
      (RanklistHourEntrance_Page)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static RanklistHourEntrance_Page parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (RanklistHourEntrance_Page)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static RanklistHourEntrance_Page parseDelimitedFrom(InputStream input) throws IOException {
    return 
      (RanklistHourEntrance_Page)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
  }
  
  public static RanklistHourEntrance_Page parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (RanklistHourEntrance_Page)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static RanklistHourEntrance_Page parseFrom(CodedInputStream input) throws IOException {
    return 
      (RanklistHourEntrance_Page)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static RanklistHourEntrance_Page parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (RanklistHourEntrance_Page)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public Builder newBuilderForType() {
    return newBuilder();
  }
  
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  
  public static Builder newBuilder(RanklistHourEntrance_Page prototype) {
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
  
  public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements RanklistHourEntrance_PageOrBuilder {
    private int bitField0_;
    
    private Object content_;
    
    private Object backgroundColor_;
    
    private long showTimes_;
    
    private int contentType_;
    
    public static final Descriptors.Descriptor getDescriptor() {
      return Douyin.internal_static_RanklistHourEntrance_Page_descriptor;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return Douyin.internal_static_RanklistHourEntrance_Page_fieldAccessorTable
        .ensureFieldAccessorsInitialized(RanklistHourEntrance_Page.class, Builder.class);
    }
    
    private Builder() {
      this.content_ = "";
      this.backgroundColor_ = "";
    }
    
    private Builder(GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      this.content_ = "";
      this.backgroundColor_ = "";
    }
    
    public Builder clear() {
      super.clear();
      this.bitField0_ = 0;
      this.content_ = "";
      this.backgroundColor_ = "";
      this.showTimes_ = 0L;
      this.contentType_ = 0;
      return this;
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
      return Douyin.internal_static_RanklistHourEntrance_Page_descriptor;
    }
    
    public RanklistHourEntrance_Page getDefaultInstanceForType() {
      return RanklistHourEntrance_Page.getDefaultInstance();
    }
    
    public RanklistHourEntrance_Page build() {
      RanklistHourEntrance_Page result = buildPartial();
      if (!result.isInitialized())
        throw newUninitializedMessageException(result); 
      return result;
    }
    
    public RanklistHourEntrance_Page buildPartial() {
      RanklistHourEntrance_Page result = new RanklistHourEntrance_Page(this);
      if (this.bitField0_ != 0)
        buildPartial0(result); 
      onBuilt();
      return result;
    }
    
    private void buildPartial0(RanklistHourEntrance_Page result) {
      int from_bitField0_ = this.bitField0_;
      if ((from_bitField0_ & 0x1) != 0)
        result.content_ = this.content_; 
      if ((from_bitField0_ & 0x2) != 0)
        result.backgroundColor_ = this.backgroundColor_; 
      if ((from_bitField0_ & 0x4) != 0)
        result.showTimes_ = this.showTimes_; 
      if ((from_bitField0_ & 0x8) != 0)
        result.contentType_ = this.contentType_; 
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
      if (other instanceof RanklistHourEntrance_Page)
        return mergeFrom((RanklistHourEntrance_Page)other); 
      super.mergeFrom(other);
      return this;
    }
    
    public Builder mergeFrom(RanklistHourEntrance_Page other) {
      if (other == RanklistHourEntrance_Page.getDefaultInstance())
        return this; 
      if (!other.getContent().isEmpty()) {
        this.content_ = other.content_;
        this.bitField0_ |= 0x1;
        onChanged();
      } 
      if (!other.getBackgroundColor().isEmpty()) {
        this.backgroundColor_ = other.backgroundColor_;
        this.bitField0_ |= 0x2;
        onChanged();
      } 
      if (other.getShowTimes() != 0L)
        setShowTimes(other.getShowTimes()); 
      if (other.getContentType() != 0)
        setContentType(other.getContentType()); 
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
              this.content_ = input.readStringRequireUtf8();
              this.bitField0_ |= 0x1;
              continue;
            case 18:
              this.backgroundColor_ = input.readStringRequireUtf8();
              this.bitField0_ |= 0x2;
              continue;
            case 24:
              this.showTimes_ = input.readInt64();
              this.bitField0_ |= 0x4;
              continue;
            case 32:
              this.contentType_ = input.readInt32();
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
      this.bitField0_ |= 0x1;
      onChanged();
      return this;
    }
    
    public Builder clearContent() {
      this.content_ = RanklistHourEntrance_Page.getDefaultInstance().getContent();
      this.bitField0_ &= 0xFFFFFFFE;
      onChanged();
      return this;
    }
    
    public Builder setContentBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      RanklistHourEntrance_Page.checkByteStringIsUtf8(value);
      this.content_ = value;
      this.bitField0_ |= 0x1;
      onChanged();
      return this;
    }
    
    public String getBackgroundColor() {
      Object ref = this.backgroundColor_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.backgroundColor_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getBackgroundColorBytes() {
      Object ref = this.backgroundColor_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.backgroundColor_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setBackgroundColor(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.backgroundColor_ = value;
      this.bitField0_ |= 0x2;
      onChanged();
      return this;
    }
    
    public Builder clearBackgroundColor() {
      this.backgroundColor_ = RanklistHourEntrance_Page.getDefaultInstance().getBackgroundColor();
      this.bitField0_ &= 0xFFFFFFFD;
      onChanged();
      return this;
    }
    
    public Builder setBackgroundColorBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      RanklistHourEntrance_Page.checkByteStringIsUtf8(value);
      this.backgroundColor_ = value;
      this.bitField0_ |= 0x2;
      onChanged();
      return this;
    }
    
    public long getShowTimes() {
      return this.showTimes_;
    }
    
    public Builder setShowTimes(long value) {
      this.showTimes_ = value;
      this.bitField0_ |= 0x4;
      onChanged();
      return this;
    }
    
    public Builder clearShowTimes() {
      this.bitField0_ &= 0xFFFFFFFB;
      this.showTimes_ = 0L;
      onChanged();
      return this;
    }
    
    public int getContentType() {
      return this.contentType_;
    }
    
    public Builder setContentType(int value) {
      this.contentType_ = value;
      this.bitField0_ |= 0x8;
      onChanged();
      return this;
    }
    
    public Builder clearContentType() {
      this.bitField0_ &= 0xFFFFFFF7;
      this.contentType_ = 0;
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
  
  private static final RanklistHourEntrance_Page DEFAULT_INSTANCE = new RanklistHourEntrance_Page();
  
  public static RanklistHourEntrance_Page getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }
  
  private static final Parser<RanklistHourEntrance_Page> PARSER = (Parser<RanklistHourEntrance_Page>)new AbstractParser<RanklistHourEntrance_Page>() {
      public RanklistHourEntrance_Page parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        RanklistHourEntrance_Page.Builder builder = RanklistHourEntrance_Page.newBuilder();
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
  
  public static Parser<RanklistHourEntrance_Page> parser() {
    return PARSER;
  }
  
  public Parser<RanklistHourEntrance_Page> getParserForType() {
    return PARSER;
  }
  
  public RanklistHourEntrance_Page getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }
}
