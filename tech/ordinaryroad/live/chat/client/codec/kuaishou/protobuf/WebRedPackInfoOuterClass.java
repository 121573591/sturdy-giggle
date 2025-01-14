package tech.ordinaryroad.live.chat.client.codec.kuaishou.protobuf;

import com.google.protobuf.AbstractMessage;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class WebRedPackInfoOuterClass {
  private static final Descriptors.Descriptor internal_static_WebRedPackInfo_descriptor;
  
  private static final GeneratedMessageV3.FieldAccessorTable internal_static_WebRedPackInfo_fieldAccessorTable;
  
  private static Descriptors.FileDescriptor descriptor;
  
  public static void registerAllExtensions(ExtensionRegistryLite registry) {}
  
  public static void registerAllExtensions(ExtensionRegistry registry) {
    registerAllExtensions((ExtensionRegistryLite)registry);
  }
  
  public static final class WebRedPackInfo extends GeneratedMessageV3 implements WebRedPackInfoOrBuilder {
    private static final long serialVersionUID = 0L;
    
    private int bitField0_;
    
    public static final int ID_FIELD_NUMBER = 1;
    
    private volatile Object id_;
    
    public static final int AUTHOR_FIELD_NUMBER = 2;
    
    private SimpleUserInfoOuterClass.SimpleUserInfo author_;
    
    public static final int BALANCE_FIELD_NUMBER = 3;
    
    private long balance_;
    
    public static final int OPENTIME_FIELD_NUMBER = 4;
    
    private long openTime_;
    
    public static final int CURRENTTIME_FIELD_NUMBER = 5;
    
    private long currentTime_;
    
    public static final int GRABTOKEN_FIELD_NUMBER = 6;
    
    private volatile Object grabToken_;
    
    public static final int NEEDSENDREQUEST_FIELD_NUMBER = 7;
    
    private boolean needSendRequest_;
    
    public static final int REQUESTDELAYMILLIS_FIELD_NUMBER = 8;
    
    private long requestDelayMillis_;
    
    public static final int LUCKIESTDELAYMILLIS_FIELD_NUMBER = 9;
    
    private long luckiestDelayMillis_;
    
    public static final int COVERTYPE_FIELD_NUMBER = 10;
    
    private int coverType_;
    
    private byte memoizedIsInitialized;
    
    private WebRedPackInfo(GeneratedMessageV3.Builder<?> builder) {
      super(builder);
      this.id_ = "";
      this.balance_ = 0L;
      this.openTime_ = 0L;
      this.currentTime_ = 0L;
      this.grabToken_ = "";
      this.needSendRequest_ = false;
      this.requestDelayMillis_ = 0L;
      this.luckiestDelayMillis_ = 0L;
      this.coverType_ = 0;
      this.memoizedIsInitialized = -1;
    }
    
    private WebRedPackInfo() {
      this.id_ = "";
      this.balance_ = 0L;
      this.openTime_ = 0L;
      this.currentTime_ = 0L;
      this.grabToken_ = "";
      this.needSendRequest_ = false;
      this.requestDelayMillis_ = 0L;
      this.luckiestDelayMillis_ = 0L;
      this.coverType_ = 0;
      this.memoizedIsInitialized = -1;
      this.id_ = "";
      this.grabToken_ = "";
      this.coverType_ = 0;
    }
    
    protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
      return new WebRedPackInfo();
    }
    
    public static final Descriptors.Descriptor getDescriptor() {
      return WebRedPackInfoOuterClass.internal_static_WebRedPackInfo_descriptor;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return WebRedPackInfoOuterClass.internal_static_WebRedPackInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(WebRedPackInfo.class, Builder.class);
    }
    
    public String getId() {
      Object ref = this.id_;
      if (ref instanceof String)
        return (String)ref; 
      ByteString bs = (ByteString)ref;
      String s = bs.toStringUtf8();
      this.id_ = s;
      return s;
    }
    
    public ByteString getIdBytes() {
      Object ref = this.id_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.id_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public boolean hasAuthor() {
      return ((this.bitField0_ & 0x1) != 0);
    }
    
    public SimpleUserInfoOuterClass.SimpleUserInfo getAuthor() {
      return (this.author_ == null) ? SimpleUserInfoOuterClass.SimpleUserInfo.getDefaultInstance() : this.author_;
    }
    
    public SimpleUserInfoOuterClass.SimpleUserInfoOrBuilder getAuthorOrBuilder() {
      return (this.author_ == null) ? SimpleUserInfoOuterClass.SimpleUserInfo.getDefaultInstance() : this.author_;
    }
    
    public long getBalance() {
      return this.balance_;
    }
    
    public long getOpenTime() {
      return this.openTime_;
    }
    
    public long getCurrentTime() {
      return this.currentTime_;
    }
    
    public String getGrabToken() {
      Object ref = this.grabToken_;
      if (ref instanceof String)
        return (String)ref; 
      ByteString bs = (ByteString)ref;
      String s = bs.toStringUtf8();
      this.grabToken_ = s;
      return s;
    }
    
    public ByteString getGrabTokenBytes() {
      Object ref = this.grabToken_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.grabToken_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public boolean getNeedSendRequest() {
      return this.needSendRequest_;
    }
    
    public long getRequestDelayMillis() {
      return this.requestDelayMillis_;
    }
    
    public long getLuckiestDelayMillis() {
      return this.luckiestDelayMillis_;
    }
    
    public int getCoverTypeValue() {
      return this.coverType_;
    }
    
    public WebRedPackCoverTypeOuterClass.WebRedPackCoverType getCoverType() {
      WebRedPackCoverTypeOuterClass.WebRedPackCoverType result = WebRedPackCoverTypeOuterClass.WebRedPackCoverType.forNumber(this.coverType_);
      return (result == null) ? WebRedPackCoverTypeOuterClass.WebRedPackCoverType.UNRECOGNIZED : result;
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
      if (!GeneratedMessageV3.isStringEmpty(this.id_))
        GeneratedMessageV3.writeString(output, 1, this.id_); 
      if ((this.bitField0_ & 0x1) != 0)
        output.writeMessage(2, (MessageLite)getAuthor()); 
      if (this.balance_ != 0L)
        output.writeUInt64(3, this.balance_); 
      if (this.openTime_ != 0L)
        output.writeUInt64(4, this.openTime_); 
      if (this.currentTime_ != 0L)
        output.writeUInt64(5, this.currentTime_); 
      if (!GeneratedMessageV3.isStringEmpty(this.grabToken_))
        GeneratedMessageV3.writeString(output, 6, this.grabToken_); 
      if (this.needSendRequest_)
        output.writeBool(7, this.needSendRequest_); 
      if (this.requestDelayMillis_ != 0L)
        output.writeUInt64(8, this.requestDelayMillis_); 
      if (this.luckiestDelayMillis_ != 0L)
        output.writeUInt64(9, this.luckiestDelayMillis_); 
      if (this.coverType_ != WebRedPackCoverTypeOuterClass.WebRedPackCoverType.UNKNOWN_COVER_TYPE.getNumber())
        output.writeEnum(10, this.coverType_); 
      getUnknownFields().writeTo(output);
    }
    
    public int getSerializedSize() {
      int size = this.memoizedSize;
      if (size != -1)
        return size; 
      size = 0;
      if (!GeneratedMessageV3.isStringEmpty(this.id_))
        size += GeneratedMessageV3.computeStringSize(1, this.id_); 
      if ((this.bitField0_ & 0x1) != 0)
        size += 
          CodedOutputStream.computeMessageSize(2, (MessageLite)getAuthor()); 
      if (this.balance_ != 0L)
        size += 
          CodedOutputStream.computeUInt64Size(3, this.balance_); 
      if (this.openTime_ != 0L)
        size += 
          CodedOutputStream.computeUInt64Size(4, this.openTime_); 
      if (this.currentTime_ != 0L)
        size += 
          CodedOutputStream.computeUInt64Size(5, this.currentTime_); 
      if (!GeneratedMessageV3.isStringEmpty(this.grabToken_))
        size += GeneratedMessageV3.computeStringSize(6, this.grabToken_); 
      if (this.needSendRequest_)
        size += 
          CodedOutputStream.computeBoolSize(7, this.needSendRequest_); 
      if (this.requestDelayMillis_ != 0L)
        size += 
          CodedOutputStream.computeUInt64Size(8, this.requestDelayMillis_); 
      if (this.luckiestDelayMillis_ != 0L)
        size += 
          CodedOutputStream.computeUInt64Size(9, this.luckiestDelayMillis_); 
      if (this.coverType_ != WebRedPackCoverTypeOuterClass.WebRedPackCoverType.UNKNOWN_COVER_TYPE.getNumber())
        size += 
          CodedOutputStream.computeEnumSize(10, this.coverType_); 
      size += getUnknownFields().getSerializedSize();
      this.memoizedSize = size;
      return size;
    }
    
    public boolean equals(Object obj) {
      if (obj == this)
        return true; 
      if (!(obj instanceof WebRedPackInfo))
        return super.equals(obj); 
      WebRedPackInfo other = (WebRedPackInfo)obj;
      if (!getId().equals(other.getId()))
        return false; 
      if (hasAuthor() != other.hasAuthor())
        return false; 
      if (hasAuthor() && 
        
        !getAuthor().equals(other.getAuthor()))
        return false; 
      if (getBalance() != other
        .getBalance())
        return false; 
      if (getOpenTime() != other
        .getOpenTime())
        return false; 
      if (getCurrentTime() != other
        .getCurrentTime())
        return false; 
      if (!getGrabToken().equals(other.getGrabToken()))
        return false; 
      if (getNeedSendRequest() != other
        .getNeedSendRequest())
        return false; 
      if (getRequestDelayMillis() != other
        .getRequestDelayMillis())
        return false; 
      if (getLuckiestDelayMillis() != other
        .getLuckiestDelayMillis())
        return false; 
      if (this.coverType_ != other.coverType_)
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
      hash = 53 * hash + getId().hashCode();
      if (hasAuthor()) {
        hash = 37 * hash + 2;
        hash = 53 * hash + getAuthor().hashCode();
      } 
      hash = 37 * hash + 3;
      hash = 53 * hash + Internal.hashLong(
          getBalance());
      hash = 37 * hash + 4;
      hash = 53 * hash + Internal.hashLong(
          getOpenTime());
      hash = 37 * hash + 5;
      hash = 53 * hash + Internal.hashLong(
          getCurrentTime());
      hash = 37 * hash + 6;
      hash = 53 * hash + getGrabToken().hashCode();
      hash = 37 * hash + 7;
      hash = 53 * hash + Internal.hashBoolean(
          getNeedSendRequest());
      hash = 37 * hash + 8;
      hash = 53 * hash + Internal.hashLong(
          getRequestDelayMillis());
      hash = 37 * hash + 9;
      hash = 53 * hash + Internal.hashLong(
          getLuckiestDelayMillis());
      hash = 37 * hash + 10;
      hash = 53 * hash + this.coverType_;
      hash = 29 * hash + getUnknownFields().hashCode();
      this.memoizedHashCode = hash;
      return hash;
    }
    
    public static WebRedPackInfo parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (WebRedPackInfo)PARSER.parseFrom(data);
    }
    
    public static WebRedPackInfo parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (WebRedPackInfo)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static WebRedPackInfo parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (WebRedPackInfo)PARSER.parseFrom(data);
    }
    
    public static WebRedPackInfo parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (WebRedPackInfo)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static WebRedPackInfo parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (WebRedPackInfo)PARSER.parseFrom(data);
    }
    
    public static WebRedPackInfo parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (WebRedPackInfo)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static WebRedPackInfo parseFrom(InputStream input) throws IOException {
      return 
        (WebRedPackInfo)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static WebRedPackInfo parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (WebRedPackInfo)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static WebRedPackInfo parseDelimitedFrom(InputStream input) throws IOException {
      return 
        (WebRedPackInfo)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }
    
    public static WebRedPackInfo parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (WebRedPackInfo)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static WebRedPackInfo parseFrom(CodedInputStream input) throws IOException {
      return 
        (WebRedPackInfo)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static WebRedPackInfo parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (WebRedPackInfo)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public Builder newBuilderForType() {
      return newBuilder();
    }
    
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    
    public static Builder newBuilder(WebRedPackInfo prototype) {
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
    
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements WebRedPackInfoOuterClass.WebRedPackInfoOrBuilder {
      private int bitField0_;
      
      private Object id_;
      
      private SimpleUserInfoOuterClass.SimpleUserInfo author_;
      
      private SingleFieldBuilderV3<SimpleUserInfoOuterClass.SimpleUserInfo, SimpleUserInfoOuterClass.SimpleUserInfo.Builder, SimpleUserInfoOuterClass.SimpleUserInfoOrBuilder> authorBuilder_;
      
      private long balance_;
      
      private long openTime_;
      
      private long currentTime_;
      
      private Object grabToken_;
      
      private boolean needSendRequest_;
      
      private long requestDelayMillis_;
      
      private long luckiestDelayMillis_;
      
      private int coverType_;
      
      public static final Descriptors.Descriptor getDescriptor() {
        return WebRedPackInfoOuterClass.internal_static_WebRedPackInfo_descriptor;
      }
      
      protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return WebRedPackInfoOuterClass.internal_static_WebRedPackInfo_fieldAccessorTable
          .ensureFieldAccessorsInitialized(WebRedPackInfoOuterClass.WebRedPackInfo.class, Builder.class);
      }
      
      private Builder() {
        this.id_ = "";
        this.grabToken_ = "";
        this.coverType_ = 0;
        maybeForceBuilderInitialization();
      }
      
      private Builder(GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        this.id_ = "";
        this.grabToken_ = "";
        this.coverType_ = 0;
        maybeForceBuilderInitialization();
      }
      
      private void maybeForceBuilderInitialization() {
        if (WebRedPackInfoOuterClass.WebRedPackInfo.alwaysUseFieldBuilders)
          getAuthorFieldBuilder(); 
      }
      
      public Builder clear() {
        super.clear();
        this.bitField0_ = 0;
        this.id_ = "";
        this.author_ = null;
        if (this.authorBuilder_ != null) {
          this.authorBuilder_.dispose();
          this.authorBuilder_ = null;
        } 
        this.balance_ = 0L;
        this.openTime_ = 0L;
        this.currentTime_ = 0L;
        this.grabToken_ = "";
        this.needSendRequest_ = false;
        this.requestDelayMillis_ = 0L;
        this.luckiestDelayMillis_ = 0L;
        this.coverType_ = 0;
        return this;
      }
      
      public Descriptors.Descriptor getDescriptorForType() {
        return WebRedPackInfoOuterClass.internal_static_WebRedPackInfo_descriptor;
      }
      
      public WebRedPackInfoOuterClass.WebRedPackInfo getDefaultInstanceForType() {
        return WebRedPackInfoOuterClass.WebRedPackInfo.getDefaultInstance();
      }
      
      public WebRedPackInfoOuterClass.WebRedPackInfo build() {
        WebRedPackInfoOuterClass.WebRedPackInfo result = buildPartial();
        if (!result.isInitialized())
          throw newUninitializedMessageException(result); 
        return result;
      }
      
      public WebRedPackInfoOuterClass.WebRedPackInfo buildPartial() {
        WebRedPackInfoOuterClass.WebRedPackInfo result = new WebRedPackInfoOuterClass.WebRedPackInfo(this);
        if (this.bitField0_ != 0)
          buildPartial0(result); 
        onBuilt();
        return result;
      }
      
      private void buildPartial0(WebRedPackInfoOuterClass.WebRedPackInfo result) {
        int from_bitField0_ = this.bitField0_;
        if ((from_bitField0_ & 0x1) != 0)
          result.id_ = this.id_; 
        int to_bitField0_ = 0;
        if ((from_bitField0_ & 0x2) != 0) {
          result.author_ = (this.authorBuilder_ == null) ? this.author_ : (SimpleUserInfoOuterClass.SimpleUserInfo)this.authorBuilder_.build();
          to_bitField0_ |= 0x1;
        } 
        if ((from_bitField0_ & 0x4) != 0)
          result.balance_ = this.balance_; 
        if ((from_bitField0_ & 0x8) != 0)
          result.openTime_ = this.openTime_; 
        if ((from_bitField0_ & 0x10) != 0)
          result.currentTime_ = this.currentTime_; 
        if ((from_bitField0_ & 0x20) != 0)
          result.grabToken_ = this.grabToken_; 
        if ((from_bitField0_ & 0x40) != 0)
          result.needSendRequest_ = this.needSendRequest_; 
        if ((from_bitField0_ & 0x80) != 0)
          result.requestDelayMillis_ = this.requestDelayMillis_; 
        if ((from_bitField0_ & 0x100) != 0)
          result.luckiestDelayMillis_ = this.luckiestDelayMillis_; 
        if ((from_bitField0_ & 0x200) != 0)
          result.coverType_ = this.coverType_; 
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
        if (other instanceof WebRedPackInfoOuterClass.WebRedPackInfo)
          return mergeFrom((WebRedPackInfoOuterClass.WebRedPackInfo)other); 
        super.mergeFrom(other);
        return this;
      }
      
      public Builder mergeFrom(WebRedPackInfoOuterClass.WebRedPackInfo other) {
        if (other == WebRedPackInfoOuterClass.WebRedPackInfo.getDefaultInstance())
          return this; 
        if (!other.getId().isEmpty()) {
          this.id_ = other.id_;
          this.bitField0_ |= 0x1;
          onChanged();
        } 
        if (other.hasAuthor())
          mergeAuthor(other.getAuthor()); 
        if (other.getBalance() != 0L)
          setBalance(other.getBalance()); 
        if (other.getOpenTime() != 0L)
          setOpenTime(other.getOpenTime()); 
        if (other.getCurrentTime() != 0L)
          setCurrentTime(other.getCurrentTime()); 
        if (!other.getGrabToken().isEmpty()) {
          this.grabToken_ = other.grabToken_;
          this.bitField0_ |= 0x20;
          onChanged();
        } 
        if (other.getNeedSendRequest())
          setNeedSendRequest(other.getNeedSendRequest()); 
        if (other.getRequestDelayMillis() != 0L)
          setRequestDelayMillis(other.getRequestDelayMillis()); 
        if (other.getLuckiestDelayMillis() != 0L)
          setLuckiestDelayMillis(other.getLuckiestDelayMillis()); 
        if (other.coverType_ != 0)
          setCoverTypeValue(other.getCoverTypeValue()); 
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
                this.id_ = input.readStringRequireUtf8();
                this.bitField0_ |= 0x1;
                continue;
              case 18:
                input.readMessage((MessageLite.Builder)getAuthorFieldBuilder().getBuilder(), extensionRegistry);
                this.bitField0_ |= 0x2;
                continue;
              case 24:
                this.balance_ = input.readUInt64();
                this.bitField0_ |= 0x4;
                continue;
              case 32:
                this.openTime_ = input.readUInt64();
                this.bitField0_ |= 0x8;
                continue;
              case 40:
                this.currentTime_ = input.readUInt64();
                this.bitField0_ |= 0x10;
                continue;
              case 50:
                this.grabToken_ = input.readStringRequireUtf8();
                this.bitField0_ |= 0x20;
                continue;
              case 56:
                this.needSendRequest_ = input.readBool();
                this.bitField0_ |= 0x40;
                continue;
              case 64:
                this.requestDelayMillis_ = input.readUInt64();
                this.bitField0_ |= 0x80;
                continue;
              case 72:
                this.luckiestDelayMillis_ = input.readUInt64();
                this.bitField0_ |= 0x100;
                continue;
              case 80:
                this.coverType_ = input.readEnum();
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
      
      public String getId() {
        Object ref = this.id_;
        if (!(ref instanceof String)) {
          ByteString bs = (ByteString)ref;
          String s = bs.toStringUtf8();
          this.id_ = s;
          return s;
        } 
        return (String)ref;
      }
      
      public ByteString getIdBytes() {
        Object ref = this.id_;
        if (ref instanceof String) {
          ByteString b = ByteString.copyFromUtf8((String)ref);
          this.id_ = b;
          return b;
        } 
        return (ByteString)ref;
      }
      
      public Builder setId(String value) {
        if (value == null)
          throw new NullPointerException(); 
        this.id_ = value;
        this.bitField0_ |= 0x1;
        onChanged();
        return this;
      }
      
      public Builder clearId() {
        this.id_ = WebRedPackInfoOuterClass.WebRedPackInfo.getDefaultInstance().getId();
        this.bitField0_ &= 0xFFFFFFFE;
        onChanged();
        return this;
      }
      
      public Builder setIdBytes(ByteString value) {
        if (value == null)
          throw new NullPointerException(); 
        WebRedPackInfoOuterClass.WebRedPackInfo.checkByteStringIsUtf8(value);
        this.id_ = value;
        this.bitField0_ |= 0x1;
        onChanged();
        return this;
      }
      
      public boolean hasAuthor() {
        return ((this.bitField0_ & 0x2) != 0);
      }
      
      public SimpleUserInfoOuterClass.SimpleUserInfo getAuthor() {
        if (this.authorBuilder_ == null)
          return (this.author_ == null) ? SimpleUserInfoOuterClass.SimpleUserInfo.getDefaultInstance() : this.author_; 
        return (SimpleUserInfoOuterClass.SimpleUserInfo)this.authorBuilder_.getMessage();
      }
      
      public Builder setAuthor(SimpleUserInfoOuterClass.SimpleUserInfo value) {
        if (this.authorBuilder_ == null) {
          if (value == null)
            throw new NullPointerException(); 
          this.author_ = value;
        } else {
          this.authorBuilder_.setMessage((AbstractMessage)value);
        } 
        this.bitField0_ |= 0x2;
        onChanged();
        return this;
      }
      
      public Builder setAuthor(SimpleUserInfoOuterClass.SimpleUserInfo.Builder builderForValue) {
        if (this.authorBuilder_ == null) {
          this.author_ = builderForValue.build();
        } else {
          this.authorBuilder_.setMessage((AbstractMessage)builderForValue.build());
        } 
        this.bitField0_ |= 0x2;
        onChanged();
        return this;
      }
      
      public Builder mergeAuthor(SimpleUserInfoOuterClass.SimpleUserInfo value) {
        if (this.authorBuilder_ == null) {
          if ((this.bitField0_ & 0x2) != 0 && this.author_ != null && this.author_ != SimpleUserInfoOuterClass.SimpleUserInfo.getDefaultInstance()) {
            getAuthorBuilder().mergeFrom(value);
          } else {
            this.author_ = value;
          } 
        } else {
          this.authorBuilder_.mergeFrom((AbstractMessage)value);
        } 
        if (this.author_ != null) {
          this.bitField0_ |= 0x2;
          onChanged();
        } 
        return this;
      }
      
      public Builder clearAuthor() {
        this.bitField0_ &= 0xFFFFFFFD;
        this.author_ = null;
        if (this.authorBuilder_ != null) {
          this.authorBuilder_.dispose();
          this.authorBuilder_ = null;
        } 
        onChanged();
        return this;
      }
      
      public SimpleUserInfoOuterClass.SimpleUserInfo.Builder getAuthorBuilder() {
        this.bitField0_ |= 0x2;
        onChanged();
        return (SimpleUserInfoOuterClass.SimpleUserInfo.Builder)getAuthorFieldBuilder().getBuilder();
      }
      
      public SimpleUserInfoOuterClass.SimpleUserInfoOrBuilder getAuthorOrBuilder() {
        if (this.authorBuilder_ != null)
          return (SimpleUserInfoOuterClass.SimpleUserInfoOrBuilder)this.authorBuilder_.getMessageOrBuilder(); 
        return (this.author_ == null) ? SimpleUserInfoOuterClass.SimpleUserInfo.getDefaultInstance() : this.author_;
      }
      
      private SingleFieldBuilderV3<SimpleUserInfoOuterClass.SimpleUserInfo, SimpleUserInfoOuterClass.SimpleUserInfo.Builder, SimpleUserInfoOuterClass.SimpleUserInfoOrBuilder> getAuthorFieldBuilder() {
        if (this.authorBuilder_ == null) {
          this.authorBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getAuthor(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
          this.author_ = null;
        } 
        return this.authorBuilder_;
      }
      
      public long getBalance() {
        return this.balance_;
      }
      
      public Builder setBalance(long value) {
        this.balance_ = value;
        this.bitField0_ |= 0x4;
        onChanged();
        return this;
      }
      
      public Builder clearBalance() {
        this.bitField0_ &= 0xFFFFFFFB;
        this.balance_ = 0L;
        onChanged();
        return this;
      }
      
      public long getOpenTime() {
        return this.openTime_;
      }
      
      public Builder setOpenTime(long value) {
        this.openTime_ = value;
        this.bitField0_ |= 0x8;
        onChanged();
        return this;
      }
      
      public Builder clearOpenTime() {
        this.bitField0_ &= 0xFFFFFFF7;
        this.openTime_ = 0L;
        onChanged();
        return this;
      }
      
      public long getCurrentTime() {
        return this.currentTime_;
      }
      
      public Builder setCurrentTime(long value) {
        this.currentTime_ = value;
        this.bitField0_ |= 0x10;
        onChanged();
        return this;
      }
      
      public Builder clearCurrentTime() {
        this.bitField0_ &= 0xFFFFFFEF;
        this.currentTime_ = 0L;
        onChanged();
        return this;
      }
      
      public String getGrabToken() {
        Object ref = this.grabToken_;
        if (!(ref instanceof String)) {
          ByteString bs = (ByteString)ref;
          String s = bs.toStringUtf8();
          this.grabToken_ = s;
          return s;
        } 
        return (String)ref;
      }
      
      public ByteString getGrabTokenBytes() {
        Object ref = this.grabToken_;
        if (ref instanceof String) {
          ByteString b = ByteString.copyFromUtf8((String)ref);
          this.grabToken_ = b;
          return b;
        } 
        return (ByteString)ref;
      }
      
      public Builder setGrabToken(String value) {
        if (value == null)
          throw new NullPointerException(); 
        this.grabToken_ = value;
        this.bitField0_ |= 0x20;
        onChanged();
        return this;
      }
      
      public Builder clearGrabToken() {
        this.grabToken_ = WebRedPackInfoOuterClass.WebRedPackInfo.getDefaultInstance().getGrabToken();
        this.bitField0_ &= 0xFFFFFFDF;
        onChanged();
        return this;
      }
      
      public Builder setGrabTokenBytes(ByteString value) {
        if (value == null)
          throw new NullPointerException(); 
        WebRedPackInfoOuterClass.WebRedPackInfo.checkByteStringIsUtf8(value);
        this.grabToken_ = value;
        this.bitField0_ |= 0x20;
        onChanged();
        return this;
      }
      
      public boolean getNeedSendRequest() {
        return this.needSendRequest_;
      }
      
      public Builder setNeedSendRequest(boolean value) {
        this.needSendRequest_ = value;
        this.bitField0_ |= 0x40;
        onChanged();
        return this;
      }
      
      public Builder clearNeedSendRequest() {
        this.bitField0_ &= 0xFFFFFFBF;
        this.needSendRequest_ = false;
        onChanged();
        return this;
      }
      
      public long getRequestDelayMillis() {
        return this.requestDelayMillis_;
      }
      
      public Builder setRequestDelayMillis(long value) {
        this.requestDelayMillis_ = value;
        this.bitField0_ |= 0x80;
        onChanged();
        return this;
      }
      
      public Builder clearRequestDelayMillis() {
        this.bitField0_ &= 0xFFFFFF7F;
        this.requestDelayMillis_ = 0L;
        onChanged();
        return this;
      }
      
      public long getLuckiestDelayMillis() {
        return this.luckiestDelayMillis_;
      }
      
      public Builder setLuckiestDelayMillis(long value) {
        this.luckiestDelayMillis_ = value;
        this.bitField0_ |= 0x100;
        onChanged();
        return this;
      }
      
      public Builder clearLuckiestDelayMillis() {
        this.bitField0_ &= 0xFFFFFEFF;
        this.luckiestDelayMillis_ = 0L;
        onChanged();
        return this;
      }
      
      public int getCoverTypeValue() {
        return this.coverType_;
      }
      
      public Builder setCoverTypeValue(int value) {
        this.coverType_ = value;
        this.bitField0_ |= 0x200;
        onChanged();
        return this;
      }
      
      public WebRedPackCoverTypeOuterClass.WebRedPackCoverType getCoverType() {
        WebRedPackCoverTypeOuterClass.WebRedPackCoverType result = WebRedPackCoverTypeOuterClass.WebRedPackCoverType.forNumber(this.coverType_);
        return (result == null) ? WebRedPackCoverTypeOuterClass.WebRedPackCoverType.UNRECOGNIZED : result;
      }
      
      public Builder setCoverType(WebRedPackCoverTypeOuterClass.WebRedPackCoverType value) {
        if (value == null)
          throw new NullPointerException(); 
        this.bitField0_ |= 0x200;
        this.coverType_ = value.getNumber();
        onChanged();
        return this;
      }
      
      public Builder clearCoverType() {
        this.bitField0_ &= 0xFFFFFDFF;
        this.coverType_ = 0;
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
    
    private static final WebRedPackInfo DEFAULT_INSTANCE = new WebRedPackInfo();
    
    public static WebRedPackInfo getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }
    
    private static final Parser<WebRedPackInfo> PARSER = (Parser<WebRedPackInfo>)new AbstractParser<WebRedPackInfo>() {
        public WebRedPackInfoOuterClass.WebRedPackInfo parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
          WebRedPackInfoOuterClass.WebRedPackInfo.Builder builder = WebRedPackInfoOuterClass.WebRedPackInfo.newBuilder();
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
    
    public static Parser<WebRedPackInfo> parser() {
      return PARSER;
    }
    
    public Parser<WebRedPackInfo> getParserForType() {
      return PARSER;
    }
    
    public WebRedPackInfo getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }
  }
  
  public static Descriptors.FileDescriptor getDescriptor() {
    return descriptor;
  }
  
  static {
    String[] descriptorData = { "\n\024WebRedPackInfo.proto\032\024SimpleUserInfo.proto\032\031WebRedPackCoverType.proto\"\002\n\016WebRedPackInfo\022\n\n\002id\030\001 \001(\t\022\037\n\006author\030\002 \001(\0132\017.SimpleUserInfo\022\017\n\007balance\030\003 \001(\004\022\020\n\bopenTime\030\004 \001(\004\022\023\n\013currentTime\030\005 \001(\004\022\021\n\tgrabToken\030\006 \001(\t\022\027\n\017needSendRequest\030\007 \001(\b\022\032\n\022requestDelayMillis\030\b \001(\004\022\033\n\023luckiestDelayMillis\030\t \001(\004\022'\n\tcoverType\030\n \001(\0162\024.WebRedPackCoverTypeB<\n:tech.ordinaryroad.live.chat.client.codec.kuaishou.protobufb\006proto3" };
    descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[] { SimpleUserInfoOuterClass.getDescriptor(), 
          WebRedPackCoverTypeOuterClass.getDescriptor() });
    internal_static_WebRedPackInfo_descriptor = getDescriptor().getMessageTypes().get(0);
    internal_static_WebRedPackInfo_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_WebRedPackInfo_descriptor, new String[] { "Id", "Author", "Balance", "OpenTime", "CurrentTime", "GrabToken", "NeedSendRequest", "RequestDelayMillis", "LuckiestDelayMillis", "CoverType" });
    SimpleUserInfoOuterClass.getDescriptor();
    WebRedPackCoverTypeOuterClass.getDescriptor();
  }
  
  public static interface WebRedPackInfoOrBuilder extends MessageOrBuilder {
    String getId();
    
    ByteString getIdBytes();
    
    boolean hasAuthor();
    
    SimpleUserInfoOuterClass.SimpleUserInfo getAuthor();
    
    SimpleUserInfoOuterClass.SimpleUserInfoOrBuilder getAuthorOrBuilder();
    
    long getBalance();
    
    long getOpenTime();
    
    long getCurrentTime();
    
    String getGrabToken();
    
    ByteString getGrabTokenBytes();
    
    boolean getNeedSendRequest();
    
    long getRequestDelayMillis();
    
    long getLuckiestDelayMillis();
    
    int getCoverTypeValue();
    
    WebRedPackCoverTypeOuterClass.WebRedPackCoverType getCoverType();
  }
}
