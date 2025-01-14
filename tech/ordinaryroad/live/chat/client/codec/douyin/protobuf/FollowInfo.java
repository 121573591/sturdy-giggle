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

public final class FollowInfo extends GeneratedMessageV3 implements FollowInfoOrBuilder {
  private static final long serialVersionUID = 0L;
  
  public static final int FOLLOWINGCOUNT_FIELD_NUMBER = 1;
  
  private long followingCount_;
  
  public static final int FOLLOWERCOUNT_FIELD_NUMBER = 2;
  
  private long followerCount_;
  
  public static final int FOLLOWSTATUS_FIELD_NUMBER = 3;
  
  private long followStatus_;
  
  public static final int PUSHSTATUS_FIELD_NUMBER = 4;
  
  private long pushStatus_;
  
  public static final int REMARKNAME_FIELD_NUMBER = 5;
  
  private volatile Object remarkName_;
  
  public static final int FOLLOWERCOUNTSTR_FIELD_NUMBER = 6;
  
  private volatile Object followerCountStr_;
  
  public static final int FOLLOWINGCOUNTSTR_FIELD_NUMBER = 7;
  
  private volatile Object followingCountStr_;
  
  private byte memoizedIsInitialized;
  
  private FollowInfo(GeneratedMessageV3.Builder<?> builder) {
    super(builder);
    this.followingCount_ = 0L;
    this.followerCount_ = 0L;
    this.followStatus_ = 0L;
    this.pushStatus_ = 0L;
    this.remarkName_ = "";
    this.followerCountStr_ = "";
    this.followingCountStr_ = "";
    this.memoizedIsInitialized = -1;
  }
  
  private FollowInfo() {
    this.followingCount_ = 0L;
    this.followerCount_ = 0L;
    this.followStatus_ = 0L;
    this.pushStatus_ = 0L;
    this.remarkName_ = "";
    this.followerCountStr_ = "";
    this.followingCountStr_ = "";
    this.memoizedIsInitialized = -1;
    this.remarkName_ = "";
    this.followerCountStr_ = "";
    this.followingCountStr_ = "";
  }
  
  protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
    return new FollowInfo();
  }
  
  public static final Descriptors.Descriptor getDescriptor() {
    return Douyin.internal_static_FollowInfo_descriptor;
  }
  
  protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
    return Douyin.internal_static_FollowInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(FollowInfo.class, Builder.class);
  }
  
  public long getFollowingCount() {
    return this.followingCount_;
  }
  
  public long getFollowerCount() {
    return this.followerCount_;
  }
  
  public long getFollowStatus() {
    return this.followStatus_;
  }
  
  public long getPushStatus() {
    return this.pushStatus_;
  }
  
  public String getRemarkName() {
    Object ref = this.remarkName_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.remarkName_ = s;
    return s;
  }
  
  public ByteString getRemarkNameBytes() {
    Object ref = this.remarkName_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.remarkName_ = b;
      return b;
    } 
    return (ByteString)ref;
  }
  
  public String getFollowerCountStr() {
    Object ref = this.followerCountStr_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.followerCountStr_ = s;
    return s;
  }
  
  public ByteString getFollowerCountStrBytes() {
    Object ref = this.followerCountStr_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.followerCountStr_ = b;
      return b;
    } 
    return (ByteString)ref;
  }
  
  public String getFollowingCountStr() {
    Object ref = this.followingCountStr_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.followingCountStr_ = s;
    return s;
  }
  
  public ByteString getFollowingCountStrBytes() {
    Object ref = this.followingCountStr_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.followingCountStr_ = b;
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
    if (this.followingCount_ != 0L)
      output.writeUInt64(1, this.followingCount_); 
    if (this.followerCount_ != 0L)
      output.writeUInt64(2, this.followerCount_); 
    if (this.followStatus_ != 0L)
      output.writeUInt64(3, this.followStatus_); 
    if (this.pushStatus_ != 0L)
      output.writeUInt64(4, this.pushStatus_); 
    if (!GeneratedMessageV3.isStringEmpty(this.remarkName_))
      GeneratedMessageV3.writeString(output, 5, this.remarkName_); 
    if (!GeneratedMessageV3.isStringEmpty(this.followerCountStr_))
      GeneratedMessageV3.writeString(output, 6, this.followerCountStr_); 
    if (!GeneratedMessageV3.isStringEmpty(this.followingCountStr_))
      GeneratedMessageV3.writeString(output, 7, this.followingCountStr_); 
    getUnknownFields().writeTo(output);
  }
  
  public int getSerializedSize() {
    int size = this.memoizedSize;
    if (size != -1)
      return size; 
    size = 0;
    if (this.followingCount_ != 0L)
      size += 
        CodedOutputStream.computeUInt64Size(1, this.followingCount_); 
    if (this.followerCount_ != 0L)
      size += 
        CodedOutputStream.computeUInt64Size(2, this.followerCount_); 
    if (this.followStatus_ != 0L)
      size += 
        CodedOutputStream.computeUInt64Size(3, this.followStatus_); 
    if (this.pushStatus_ != 0L)
      size += 
        CodedOutputStream.computeUInt64Size(4, this.pushStatus_); 
    if (!GeneratedMessageV3.isStringEmpty(this.remarkName_))
      size += GeneratedMessageV3.computeStringSize(5, this.remarkName_); 
    if (!GeneratedMessageV3.isStringEmpty(this.followerCountStr_))
      size += GeneratedMessageV3.computeStringSize(6, this.followerCountStr_); 
    if (!GeneratedMessageV3.isStringEmpty(this.followingCountStr_))
      size += GeneratedMessageV3.computeStringSize(7, this.followingCountStr_); 
    size += getUnknownFields().getSerializedSize();
    this.memoizedSize = size;
    return size;
  }
  
  public boolean equals(Object obj) {
    if (obj == this)
      return true; 
    if (!(obj instanceof FollowInfo))
      return super.equals(obj); 
    FollowInfo other = (FollowInfo)obj;
    if (getFollowingCount() != other
      .getFollowingCount())
      return false; 
    if (getFollowerCount() != other
      .getFollowerCount())
      return false; 
    if (getFollowStatus() != other
      .getFollowStatus())
      return false; 
    if (getPushStatus() != other
      .getPushStatus())
      return false; 
    if (!getRemarkName().equals(other.getRemarkName()))
      return false; 
    if (!getFollowerCountStr().equals(other.getFollowerCountStr()))
      return false; 
    if (!getFollowingCountStr().equals(other.getFollowingCountStr()))
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
        getFollowingCount());
    hash = 37 * hash + 2;
    hash = 53 * hash + Internal.hashLong(
        getFollowerCount());
    hash = 37 * hash + 3;
    hash = 53 * hash + Internal.hashLong(
        getFollowStatus());
    hash = 37 * hash + 4;
    hash = 53 * hash + Internal.hashLong(
        getPushStatus());
    hash = 37 * hash + 5;
    hash = 53 * hash + getRemarkName().hashCode();
    hash = 37 * hash + 6;
    hash = 53 * hash + getFollowerCountStr().hashCode();
    hash = 37 * hash + 7;
    hash = 53 * hash + getFollowingCountStr().hashCode();
    hash = 29 * hash + getUnknownFields().hashCode();
    this.memoizedHashCode = hash;
    return hash;
  }
  
  public static FollowInfo parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
    return (FollowInfo)PARSER.parseFrom(data);
  }
  
  public static FollowInfo parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (FollowInfo)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static FollowInfo parseFrom(ByteString data) throws InvalidProtocolBufferException {
    return (FollowInfo)PARSER.parseFrom(data);
  }
  
  public static FollowInfo parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (FollowInfo)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static FollowInfo parseFrom(byte[] data) throws InvalidProtocolBufferException {
    return (FollowInfo)PARSER.parseFrom(data);
  }
  
  public static FollowInfo parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (FollowInfo)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static FollowInfo parseFrom(InputStream input) throws IOException {
    return 
      (FollowInfo)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static FollowInfo parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (FollowInfo)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static FollowInfo parseDelimitedFrom(InputStream input) throws IOException {
    return 
      (FollowInfo)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
  }
  
  public static FollowInfo parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (FollowInfo)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static FollowInfo parseFrom(CodedInputStream input) throws IOException {
    return 
      (FollowInfo)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static FollowInfo parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (FollowInfo)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public Builder newBuilderForType() {
    return newBuilder();
  }
  
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  
  public static Builder newBuilder(FollowInfo prototype) {
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
  
  public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements FollowInfoOrBuilder {
    private int bitField0_;
    
    private long followingCount_;
    
    private long followerCount_;
    
    private long followStatus_;
    
    private long pushStatus_;
    
    private Object remarkName_;
    
    private Object followerCountStr_;
    
    private Object followingCountStr_;
    
    public static final Descriptors.Descriptor getDescriptor() {
      return Douyin.internal_static_FollowInfo_descriptor;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return Douyin.internal_static_FollowInfo_fieldAccessorTable
        .ensureFieldAccessorsInitialized(FollowInfo.class, Builder.class);
    }
    
    private Builder() {
      this.remarkName_ = "";
      this.followerCountStr_ = "";
      this.followingCountStr_ = "";
    }
    
    private Builder(GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      this.remarkName_ = "";
      this.followerCountStr_ = "";
      this.followingCountStr_ = "";
    }
    
    public Builder clear() {
      super.clear();
      this.bitField0_ = 0;
      this.followingCount_ = 0L;
      this.followerCount_ = 0L;
      this.followStatus_ = 0L;
      this.pushStatus_ = 0L;
      this.remarkName_ = "";
      this.followerCountStr_ = "";
      this.followingCountStr_ = "";
      return this;
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
      return Douyin.internal_static_FollowInfo_descriptor;
    }
    
    public FollowInfo getDefaultInstanceForType() {
      return FollowInfo.getDefaultInstance();
    }
    
    public FollowInfo build() {
      FollowInfo result = buildPartial();
      if (!result.isInitialized())
        throw newUninitializedMessageException(result); 
      return result;
    }
    
    public FollowInfo buildPartial() {
      FollowInfo result = new FollowInfo(this);
      if (this.bitField0_ != 0)
        buildPartial0(result); 
      onBuilt();
      return result;
    }
    
    private void buildPartial0(FollowInfo result) {
      int from_bitField0_ = this.bitField0_;
      if ((from_bitField0_ & 0x1) != 0)
        result.followingCount_ = this.followingCount_; 
      if ((from_bitField0_ & 0x2) != 0)
        result.followerCount_ = this.followerCount_; 
      if ((from_bitField0_ & 0x4) != 0)
        result.followStatus_ = this.followStatus_; 
      if ((from_bitField0_ & 0x8) != 0)
        result.pushStatus_ = this.pushStatus_; 
      if ((from_bitField0_ & 0x10) != 0)
        result.remarkName_ = this.remarkName_; 
      if ((from_bitField0_ & 0x20) != 0)
        result.followerCountStr_ = this.followerCountStr_; 
      if ((from_bitField0_ & 0x40) != 0)
        result.followingCountStr_ = this.followingCountStr_; 
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
      if (other instanceof FollowInfo)
        return mergeFrom((FollowInfo)other); 
      super.mergeFrom(other);
      return this;
    }
    
    public Builder mergeFrom(FollowInfo other) {
      if (other == FollowInfo.getDefaultInstance())
        return this; 
      if (other.getFollowingCount() != 0L)
        setFollowingCount(other.getFollowingCount()); 
      if (other.getFollowerCount() != 0L)
        setFollowerCount(other.getFollowerCount()); 
      if (other.getFollowStatus() != 0L)
        setFollowStatus(other.getFollowStatus()); 
      if (other.getPushStatus() != 0L)
        setPushStatus(other.getPushStatus()); 
      if (!other.getRemarkName().isEmpty()) {
        this.remarkName_ = other.remarkName_;
        this.bitField0_ |= 0x10;
        onChanged();
      } 
      if (!other.getFollowerCountStr().isEmpty()) {
        this.followerCountStr_ = other.followerCountStr_;
        this.bitField0_ |= 0x20;
        onChanged();
      } 
      if (!other.getFollowingCountStr().isEmpty()) {
        this.followingCountStr_ = other.followingCountStr_;
        this.bitField0_ |= 0x40;
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
              this.followingCount_ = input.readUInt64();
              this.bitField0_ |= 0x1;
              continue;
            case 16:
              this.followerCount_ = input.readUInt64();
              this.bitField0_ |= 0x2;
              continue;
            case 24:
              this.followStatus_ = input.readUInt64();
              this.bitField0_ |= 0x4;
              continue;
            case 32:
              this.pushStatus_ = input.readUInt64();
              this.bitField0_ |= 0x8;
              continue;
            case 42:
              this.remarkName_ = input.readStringRequireUtf8();
              this.bitField0_ |= 0x10;
              continue;
            case 50:
              this.followerCountStr_ = input.readStringRequireUtf8();
              this.bitField0_ |= 0x20;
              continue;
            case 58:
              this.followingCountStr_ = input.readStringRequireUtf8();
              this.bitField0_ |= 0x40;
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
    
    public long getFollowingCount() {
      return this.followingCount_;
    }
    
    public Builder setFollowingCount(long value) {
      this.followingCount_ = value;
      this.bitField0_ |= 0x1;
      onChanged();
      return this;
    }
    
    public Builder clearFollowingCount() {
      this.bitField0_ &= 0xFFFFFFFE;
      this.followingCount_ = 0L;
      onChanged();
      return this;
    }
    
    public long getFollowerCount() {
      return this.followerCount_;
    }
    
    public Builder setFollowerCount(long value) {
      this.followerCount_ = value;
      this.bitField0_ |= 0x2;
      onChanged();
      return this;
    }
    
    public Builder clearFollowerCount() {
      this.bitField0_ &= 0xFFFFFFFD;
      this.followerCount_ = 0L;
      onChanged();
      return this;
    }
    
    public long getFollowStatus() {
      return this.followStatus_;
    }
    
    public Builder setFollowStatus(long value) {
      this.followStatus_ = value;
      this.bitField0_ |= 0x4;
      onChanged();
      return this;
    }
    
    public Builder clearFollowStatus() {
      this.bitField0_ &= 0xFFFFFFFB;
      this.followStatus_ = 0L;
      onChanged();
      return this;
    }
    
    public long getPushStatus() {
      return this.pushStatus_;
    }
    
    public Builder setPushStatus(long value) {
      this.pushStatus_ = value;
      this.bitField0_ |= 0x8;
      onChanged();
      return this;
    }
    
    public Builder clearPushStatus() {
      this.bitField0_ &= 0xFFFFFFF7;
      this.pushStatus_ = 0L;
      onChanged();
      return this;
    }
    
    public String getRemarkName() {
      Object ref = this.remarkName_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.remarkName_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getRemarkNameBytes() {
      Object ref = this.remarkName_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.remarkName_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setRemarkName(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.remarkName_ = value;
      this.bitField0_ |= 0x10;
      onChanged();
      return this;
    }
    
    public Builder clearRemarkName() {
      this.remarkName_ = FollowInfo.getDefaultInstance().getRemarkName();
      this.bitField0_ &= 0xFFFFFFEF;
      onChanged();
      return this;
    }
    
    public Builder setRemarkNameBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      FollowInfo.checkByteStringIsUtf8(value);
      this.remarkName_ = value;
      this.bitField0_ |= 0x10;
      onChanged();
      return this;
    }
    
    public String getFollowerCountStr() {
      Object ref = this.followerCountStr_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.followerCountStr_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getFollowerCountStrBytes() {
      Object ref = this.followerCountStr_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.followerCountStr_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setFollowerCountStr(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.followerCountStr_ = value;
      this.bitField0_ |= 0x20;
      onChanged();
      return this;
    }
    
    public Builder clearFollowerCountStr() {
      this.followerCountStr_ = FollowInfo.getDefaultInstance().getFollowerCountStr();
      this.bitField0_ &= 0xFFFFFFDF;
      onChanged();
      return this;
    }
    
    public Builder setFollowerCountStrBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      FollowInfo.checkByteStringIsUtf8(value);
      this.followerCountStr_ = value;
      this.bitField0_ |= 0x20;
      onChanged();
      return this;
    }
    
    public String getFollowingCountStr() {
      Object ref = this.followingCountStr_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.followingCountStr_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getFollowingCountStrBytes() {
      Object ref = this.followingCountStr_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.followingCountStr_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setFollowingCountStr(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.followingCountStr_ = value;
      this.bitField0_ |= 0x40;
      onChanged();
      return this;
    }
    
    public Builder clearFollowingCountStr() {
      this.followingCountStr_ = FollowInfo.getDefaultInstance().getFollowingCountStr();
      this.bitField0_ &= 0xFFFFFFBF;
      onChanged();
      return this;
    }
    
    public Builder setFollowingCountStrBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      FollowInfo.checkByteStringIsUtf8(value);
      this.followingCountStr_ = value;
      this.bitField0_ |= 0x40;
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
  
  private static final FollowInfo DEFAULT_INSTANCE = new FollowInfo();
  
  public static FollowInfo getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }
  
  private static final Parser<FollowInfo> PARSER = (Parser<FollowInfo>)new AbstractParser<FollowInfo>() {
      public FollowInfo parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        FollowInfo.Builder builder = FollowInfo.newBuilder();
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
  
  public static Parser<FollowInfo> parser() {
    return PARSER;
  }
  
  public Parser<FollowInfo> getParserForType() {
    return PARSER;
  }
  
  public FollowInfo getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }
}
