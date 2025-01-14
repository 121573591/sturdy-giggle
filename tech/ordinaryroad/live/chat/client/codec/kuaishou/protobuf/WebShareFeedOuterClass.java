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

public final class WebShareFeedOuterClass {
  private static final Descriptors.Descriptor internal_static_WebShareFeed_descriptor;
  
  private static final GeneratedMessageV3.FieldAccessorTable internal_static_WebShareFeed_fieldAccessorTable;
  
  private static Descriptors.FileDescriptor descriptor;
  
  public static void registerAllExtensions(ExtensionRegistryLite registry) {}
  
  public static void registerAllExtensions(ExtensionRegistry registry) {
    registerAllExtensions((ExtensionRegistryLite)registry);
  }
  
  public static final class WebShareFeed extends GeneratedMessageV3 implements WebShareFeedOrBuilder {
    private static final long serialVersionUID = 0L;
    
    private int bitField0_;
    
    public static final int ID_FIELD_NUMBER = 1;
    
    private volatile Object id_;
    
    public static final int USER_FIELD_NUMBER = 2;
    
    private SimpleUserInfoOuterClass.SimpleUserInfo user_;
    
    public static final int TIME_FIELD_NUMBER = 3;
    
    private long time_;
    
    public static final int THIRDPARTYPLATFORM_FIELD_NUMBER = 4;
    
    private int thirdPartyPlatform_;
    
    public static final int SORTRANK_FIELD_NUMBER = 5;
    
    private long sortRank_;
    
    public static final int LIVEASSISTANTTYPE_FIELD_NUMBER = 6;
    
    private int liveAssistantType_;
    
    public static final int DEVICEHASH_FIELD_NUMBER = 7;
    
    private volatile Object deviceHash_;
    
    private byte memoizedIsInitialized;
    
    private WebShareFeed(GeneratedMessageV3.Builder<?> builder) {
      super(builder);
      this.id_ = "";
      this.time_ = 0L;
      this.thirdPartyPlatform_ = 0;
      this.sortRank_ = 0L;
      this.liveAssistantType_ = 0;
      this.deviceHash_ = "";
      this.memoizedIsInitialized = -1;
    }
    
    private WebShareFeed() {
      this.id_ = "";
      this.time_ = 0L;
      this.thirdPartyPlatform_ = 0;
      this.sortRank_ = 0L;
      this.liveAssistantType_ = 0;
      this.deviceHash_ = "";
      this.memoizedIsInitialized = -1;
      this.id_ = "";
      this.liveAssistantType_ = 0;
      this.deviceHash_ = "";
    }
    
    protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
      return new WebShareFeed();
    }
    
    public static final Descriptors.Descriptor getDescriptor() {
      return WebShareFeedOuterClass.internal_static_WebShareFeed_descriptor;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return WebShareFeedOuterClass.internal_static_WebShareFeed_fieldAccessorTable.ensureFieldAccessorsInitialized(WebShareFeed.class, Builder.class);
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
    
    public boolean hasUser() {
      return ((this.bitField0_ & 0x1) != 0);
    }
    
    public SimpleUserInfoOuterClass.SimpleUserInfo getUser() {
      return (this.user_ == null) ? SimpleUserInfoOuterClass.SimpleUserInfo.getDefaultInstance() : this.user_;
    }
    
    public SimpleUserInfoOuterClass.SimpleUserInfoOrBuilder getUserOrBuilder() {
      return (this.user_ == null) ? SimpleUserInfoOuterClass.SimpleUserInfo.getDefaultInstance() : this.user_;
    }
    
    public long getTime() {
      return this.time_;
    }
    
    public int getThirdPartyPlatform() {
      return this.thirdPartyPlatform_;
    }
    
    public long getSortRank() {
      return this.sortRank_;
    }
    
    public int getLiveAssistantTypeValue() {
      return this.liveAssistantType_;
    }
    
    public WebLiveAssistantTypeOuterClass.WebLiveAssistantType getLiveAssistantType() {
      WebLiveAssistantTypeOuterClass.WebLiveAssistantType result = WebLiveAssistantTypeOuterClass.WebLiveAssistantType.forNumber(this.liveAssistantType_);
      return (result == null) ? WebLiveAssistantTypeOuterClass.WebLiveAssistantType.UNRECOGNIZED : result;
    }
    
    public String getDeviceHash() {
      Object ref = this.deviceHash_;
      if (ref instanceof String)
        return (String)ref; 
      ByteString bs = (ByteString)ref;
      String s = bs.toStringUtf8();
      this.deviceHash_ = s;
      return s;
    }
    
    public ByteString getDeviceHashBytes() {
      Object ref = this.deviceHash_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.deviceHash_ = b;
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
      if (!GeneratedMessageV3.isStringEmpty(this.id_))
        GeneratedMessageV3.writeString(output, 1, this.id_); 
      if ((this.bitField0_ & 0x1) != 0)
        output.writeMessage(2, (MessageLite)getUser()); 
      if (this.time_ != 0L)
        output.writeUInt64(3, this.time_); 
      if (this.thirdPartyPlatform_ != 0)
        output.writeUInt32(4, this.thirdPartyPlatform_); 
      if (this.sortRank_ != 0L)
        output.writeUInt64(5, this.sortRank_); 
      if (this.liveAssistantType_ != WebLiveAssistantTypeOuterClass.WebLiveAssistantType.UNKNOWN_ASSISTANT_TYPE.getNumber())
        output.writeEnum(6, this.liveAssistantType_); 
      if (!GeneratedMessageV3.isStringEmpty(this.deviceHash_))
        GeneratedMessageV3.writeString(output, 7, this.deviceHash_); 
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
          CodedOutputStream.computeMessageSize(2, (MessageLite)getUser()); 
      if (this.time_ != 0L)
        size += 
          CodedOutputStream.computeUInt64Size(3, this.time_); 
      if (this.thirdPartyPlatform_ != 0)
        size += 
          CodedOutputStream.computeUInt32Size(4, this.thirdPartyPlatform_); 
      if (this.sortRank_ != 0L)
        size += 
          CodedOutputStream.computeUInt64Size(5, this.sortRank_); 
      if (this.liveAssistantType_ != WebLiveAssistantTypeOuterClass.WebLiveAssistantType.UNKNOWN_ASSISTANT_TYPE.getNumber())
        size += 
          CodedOutputStream.computeEnumSize(6, this.liveAssistantType_); 
      if (!GeneratedMessageV3.isStringEmpty(this.deviceHash_))
        size += GeneratedMessageV3.computeStringSize(7, this.deviceHash_); 
      size += getUnknownFields().getSerializedSize();
      this.memoizedSize = size;
      return size;
    }
    
    public boolean equals(Object obj) {
      if (obj == this)
        return true; 
      if (!(obj instanceof WebShareFeed))
        return super.equals(obj); 
      WebShareFeed other = (WebShareFeed)obj;
      if (!getId().equals(other.getId()))
        return false; 
      if (hasUser() != other.hasUser())
        return false; 
      if (hasUser() && 
        
        !getUser().equals(other.getUser()))
        return false; 
      if (getTime() != other
        .getTime())
        return false; 
      if (getThirdPartyPlatform() != other
        .getThirdPartyPlatform())
        return false; 
      if (getSortRank() != other
        .getSortRank())
        return false; 
      if (this.liveAssistantType_ != other.liveAssistantType_)
        return false; 
      if (!getDeviceHash().equals(other.getDeviceHash()))
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
      if (hasUser()) {
        hash = 37 * hash + 2;
        hash = 53 * hash + getUser().hashCode();
      } 
      hash = 37 * hash + 3;
      hash = 53 * hash + Internal.hashLong(
          getTime());
      hash = 37 * hash + 4;
      hash = 53 * hash + getThirdPartyPlatform();
      hash = 37 * hash + 5;
      hash = 53 * hash + Internal.hashLong(
          getSortRank());
      hash = 37 * hash + 6;
      hash = 53 * hash + this.liveAssistantType_;
      hash = 37 * hash + 7;
      hash = 53 * hash + getDeviceHash().hashCode();
      hash = 29 * hash + getUnknownFields().hashCode();
      this.memoizedHashCode = hash;
      return hash;
    }
    
    public static WebShareFeed parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (WebShareFeed)PARSER.parseFrom(data);
    }
    
    public static WebShareFeed parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (WebShareFeed)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static WebShareFeed parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (WebShareFeed)PARSER.parseFrom(data);
    }
    
    public static WebShareFeed parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (WebShareFeed)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static WebShareFeed parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (WebShareFeed)PARSER.parseFrom(data);
    }
    
    public static WebShareFeed parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (WebShareFeed)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static WebShareFeed parseFrom(InputStream input) throws IOException {
      return 
        (WebShareFeed)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static WebShareFeed parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (WebShareFeed)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static WebShareFeed parseDelimitedFrom(InputStream input) throws IOException {
      return 
        (WebShareFeed)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }
    
    public static WebShareFeed parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (WebShareFeed)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static WebShareFeed parseFrom(CodedInputStream input) throws IOException {
      return 
        (WebShareFeed)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static WebShareFeed parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (WebShareFeed)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public Builder newBuilderForType() {
      return newBuilder();
    }
    
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    
    public static Builder newBuilder(WebShareFeed prototype) {
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
    
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements WebShareFeedOuterClass.WebShareFeedOrBuilder {
      private int bitField0_;
      
      private Object id_;
      
      private SimpleUserInfoOuterClass.SimpleUserInfo user_;
      
      private SingleFieldBuilderV3<SimpleUserInfoOuterClass.SimpleUserInfo, SimpleUserInfoOuterClass.SimpleUserInfo.Builder, SimpleUserInfoOuterClass.SimpleUserInfoOrBuilder> userBuilder_;
      
      private long time_;
      
      private int thirdPartyPlatform_;
      
      private long sortRank_;
      
      private int liveAssistantType_;
      
      private Object deviceHash_;
      
      public static final Descriptors.Descriptor getDescriptor() {
        return WebShareFeedOuterClass.internal_static_WebShareFeed_descriptor;
      }
      
      protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return WebShareFeedOuterClass.internal_static_WebShareFeed_fieldAccessorTable
          .ensureFieldAccessorsInitialized(WebShareFeedOuterClass.WebShareFeed.class, Builder.class);
      }
      
      private Builder() {
        this.id_ = "";
        this.liveAssistantType_ = 0;
        this.deviceHash_ = "";
        maybeForceBuilderInitialization();
      }
      
      private Builder(GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        this.id_ = "";
        this.liveAssistantType_ = 0;
        this.deviceHash_ = "";
        maybeForceBuilderInitialization();
      }
      
      private void maybeForceBuilderInitialization() {
        if (WebShareFeedOuterClass.WebShareFeed.alwaysUseFieldBuilders)
          getUserFieldBuilder(); 
      }
      
      public Builder clear() {
        super.clear();
        this.bitField0_ = 0;
        this.id_ = "";
        this.user_ = null;
        if (this.userBuilder_ != null) {
          this.userBuilder_.dispose();
          this.userBuilder_ = null;
        } 
        this.time_ = 0L;
        this.thirdPartyPlatform_ = 0;
        this.sortRank_ = 0L;
        this.liveAssistantType_ = 0;
        this.deviceHash_ = "";
        return this;
      }
      
      public Descriptors.Descriptor getDescriptorForType() {
        return WebShareFeedOuterClass.internal_static_WebShareFeed_descriptor;
      }
      
      public WebShareFeedOuterClass.WebShareFeed getDefaultInstanceForType() {
        return WebShareFeedOuterClass.WebShareFeed.getDefaultInstance();
      }
      
      public WebShareFeedOuterClass.WebShareFeed build() {
        WebShareFeedOuterClass.WebShareFeed result = buildPartial();
        if (!result.isInitialized())
          throw newUninitializedMessageException(result); 
        return result;
      }
      
      public WebShareFeedOuterClass.WebShareFeed buildPartial() {
        WebShareFeedOuterClass.WebShareFeed result = new WebShareFeedOuterClass.WebShareFeed(this);
        if (this.bitField0_ != 0)
          buildPartial0(result); 
        onBuilt();
        return result;
      }
      
      private void buildPartial0(WebShareFeedOuterClass.WebShareFeed result) {
        int from_bitField0_ = this.bitField0_;
        if ((from_bitField0_ & 0x1) != 0)
          result.id_ = this.id_; 
        int to_bitField0_ = 0;
        if ((from_bitField0_ & 0x2) != 0) {
          result.user_ = (this.userBuilder_ == null) ? this.user_ : (SimpleUserInfoOuterClass.SimpleUserInfo)this.userBuilder_.build();
          to_bitField0_ |= 0x1;
        } 
        if ((from_bitField0_ & 0x4) != 0)
          result.time_ = this.time_; 
        if ((from_bitField0_ & 0x8) != 0)
          result.thirdPartyPlatform_ = this.thirdPartyPlatform_; 
        if ((from_bitField0_ & 0x10) != 0)
          result.sortRank_ = this.sortRank_; 
        if ((from_bitField0_ & 0x20) != 0)
          result.liveAssistantType_ = this.liveAssistantType_; 
        if ((from_bitField0_ & 0x40) != 0)
          result.deviceHash_ = this.deviceHash_; 
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
        if (other instanceof WebShareFeedOuterClass.WebShareFeed)
          return mergeFrom((WebShareFeedOuterClass.WebShareFeed)other); 
        super.mergeFrom(other);
        return this;
      }
      
      public Builder mergeFrom(WebShareFeedOuterClass.WebShareFeed other) {
        if (other == WebShareFeedOuterClass.WebShareFeed.getDefaultInstance())
          return this; 
        if (!other.getId().isEmpty()) {
          this.id_ = other.id_;
          this.bitField0_ |= 0x1;
          onChanged();
        } 
        if (other.hasUser())
          mergeUser(other.getUser()); 
        if (other.getTime() != 0L)
          setTime(other.getTime()); 
        if (other.getThirdPartyPlatform() != 0)
          setThirdPartyPlatform(other.getThirdPartyPlatform()); 
        if (other.getSortRank() != 0L)
          setSortRank(other.getSortRank()); 
        if (other.liveAssistantType_ != 0)
          setLiveAssistantTypeValue(other.getLiveAssistantTypeValue()); 
        if (!other.getDeviceHash().isEmpty()) {
          this.deviceHash_ = other.deviceHash_;
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
              case 10:
                this.id_ = input.readStringRequireUtf8();
                this.bitField0_ |= 0x1;
                continue;
              case 18:
                input.readMessage((MessageLite.Builder)getUserFieldBuilder().getBuilder(), extensionRegistry);
                this.bitField0_ |= 0x2;
                continue;
              case 24:
                this.time_ = input.readUInt64();
                this.bitField0_ |= 0x4;
                continue;
              case 32:
                this.thirdPartyPlatform_ = input.readUInt32();
                this.bitField0_ |= 0x8;
                continue;
              case 40:
                this.sortRank_ = input.readUInt64();
                this.bitField0_ |= 0x10;
                continue;
              case 48:
                this.liveAssistantType_ = input.readEnum();
                this.bitField0_ |= 0x20;
                continue;
              case 58:
                this.deviceHash_ = input.readStringRequireUtf8();
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
        this.id_ = WebShareFeedOuterClass.WebShareFeed.getDefaultInstance().getId();
        this.bitField0_ &= 0xFFFFFFFE;
        onChanged();
        return this;
      }
      
      public Builder setIdBytes(ByteString value) {
        if (value == null)
          throw new NullPointerException(); 
        WebShareFeedOuterClass.WebShareFeed.checkByteStringIsUtf8(value);
        this.id_ = value;
        this.bitField0_ |= 0x1;
        onChanged();
        return this;
      }
      
      public boolean hasUser() {
        return ((this.bitField0_ & 0x2) != 0);
      }
      
      public SimpleUserInfoOuterClass.SimpleUserInfo getUser() {
        if (this.userBuilder_ == null)
          return (this.user_ == null) ? SimpleUserInfoOuterClass.SimpleUserInfo.getDefaultInstance() : this.user_; 
        return (SimpleUserInfoOuterClass.SimpleUserInfo)this.userBuilder_.getMessage();
      }
      
      public Builder setUser(SimpleUserInfoOuterClass.SimpleUserInfo value) {
        if (this.userBuilder_ == null) {
          if (value == null)
            throw new NullPointerException(); 
          this.user_ = value;
        } else {
          this.userBuilder_.setMessage((AbstractMessage)value);
        } 
        this.bitField0_ |= 0x2;
        onChanged();
        return this;
      }
      
      public Builder setUser(SimpleUserInfoOuterClass.SimpleUserInfo.Builder builderForValue) {
        if (this.userBuilder_ == null) {
          this.user_ = builderForValue.build();
        } else {
          this.userBuilder_.setMessage((AbstractMessage)builderForValue.build());
        } 
        this.bitField0_ |= 0x2;
        onChanged();
        return this;
      }
      
      public Builder mergeUser(SimpleUserInfoOuterClass.SimpleUserInfo value) {
        if (this.userBuilder_ == null) {
          if ((this.bitField0_ & 0x2) != 0 && this.user_ != null && this.user_ != SimpleUserInfoOuterClass.SimpleUserInfo.getDefaultInstance()) {
            getUserBuilder().mergeFrom(value);
          } else {
            this.user_ = value;
          } 
        } else {
          this.userBuilder_.mergeFrom((AbstractMessage)value);
        } 
        if (this.user_ != null) {
          this.bitField0_ |= 0x2;
          onChanged();
        } 
        return this;
      }
      
      public Builder clearUser() {
        this.bitField0_ &= 0xFFFFFFFD;
        this.user_ = null;
        if (this.userBuilder_ != null) {
          this.userBuilder_.dispose();
          this.userBuilder_ = null;
        } 
        onChanged();
        return this;
      }
      
      public SimpleUserInfoOuterClass.SimpleUserInfo.Builder getUserBuilder() {
        this.bitField0_ |= 0x2;
        onChanged();
        return (SimpleUserInfoOuterClass.SimpleUserInfo.Builder)getUserFieldBuilder().getBuilder();
      }
      
      public SimpleUserInfoOuterClass.SimpleUserInfoOrBuilder getUserOrBuilder() {
        if (this.userBuilder_ != null)
          return (SimpleUserInfoOuterClass.SimpleUserInfoOrBuilder)this.userBuilder_.getMessageOrBuilder(); 
        return (this.user_ == null) ? SimpleUserInfoOuterClass.SimpleUserInfo.getDefaultInstance() : this.user_;
      }
      
      private SingleFieldBuilderV3<SimpleUserInfoOuterClass.SimpleUserInfo, SimpleUserInfoOuterClass.SimpleUserInfo.Builder, SimpleUserInfoOuterClass.SimpleUserInfoOrBuilder> getUserFieldBuilder() {
        if (this.userBuilder_ == null) {
          this.userBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getUser(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
          this.user_ = null;
        } 
        return this.userBuilder_;
      }
      
      public long getTime() {
        return this.time_;
      }
      
      public Builder setTime(long value) {
        this.time_ = value;
        this.bitField0_ |= 0x4;
        onChanged();
        return this;
      }
      
      public Builder clearTime() {
        this.bitField0_ &= 0xFFFFFFFB;
        this.time_ = 0L;
        onChanged();
        return this;
      }
      
      public int getThirdPartyPlatform() {
        return this.thirdPartyPlatform_;
      }
      
      public Builder setThirdPartyPlatform(int value) {
        this.thirdPartyPlatform_ = value;
        this.bitField0_ |= 0x8;
        onChanged();
        return this;
      }
      
      public Builder clearThirdPartyPlatform() {
        this.bitField0_ &= 0xFFFFFFF7;
        this.thirdPartyPlatform_ = 0;
        onChanged();
        return this;
      }
      
      public long getSortRank() {
        return this.sortRank_;
      }
      
      public Builder setSortRank(long value) {
        this.sortRank_ = value;
        this.bitField0_ |= 0x10;
        onChanged();
        return this;
      }
      
      public Builder clearSortRank() {
        this.bitField0_ &= 0xFFFFFFEF;
        this.sortRank_ = 0L;
        onChanged();
        return this;
      }
      
      public int getLiveAssistantTypeValue() {
        return this.liveAssistantType_;
      }
      
      public Builder setLiveAssistantTypeValue(int value) {
        this.liveAssistantType_ = value;
        this.bitField0_ |= 0x20;
        onChanged();
        return this;
      }
      
      public WebLiveAssistantTypeOuterClass.WebLiveAssistantType getLiveAssistantType() {
        WebLiveAssistantTypeOuterClass.WebLiveAssistantType result = WebLiveAssistantTypeOuterClass.WebLiveAssistantType.forNumber(this.liveAssistantType_);
        return (result == null) ? WebLiveAssistantTypeOuterClass.WebLiveAssistantType.UNRECOGNIZED : result;
      }
      
      public Builder setLiveAssistantType(WebLiveAssistantTypeOuterClass.WebLiveAssistantType value) {
        if (value == null)
          throw new NullPointerException(); 
        this.bitField0_ |= 0x20;
        this.liveAssistantType_ = value.getNumber();
        onChanged();
        return this;
      }
      
      public Builder clearLiveAssistantType() {
        this.bitField0_ &= 0xFFFFFFDF;
        this.liveAssistantType_ = 0;
        onChanged();
        return this;
      }
      
      public String getDeviceHash() {
        Object ref = this.deviceHash_;
        if (!(ref instanceof String)) {
          ByteString bs = (ByteString)ref;
          String s = bs.toStringUtf8();
          this.deviceHash_ = s;
          return s;
        } 
        return (String)ref;
      }
      
      public ByteString getDeviceHashBytes() {
        Object ref = this.deviceHash_;
        if (ref instanceof String) {
          ByteString b = ByteString.copyFromUtf8((String)ref);
          this.deviceHash_ = b;
          return b;
        } 
        return (ByteString)ref;
      }
      
      public Builder setDeviceHash(String value) {
        if (value == null)
          throw new NullPointerException(); 
        this.deviceHash_ = value;
        this.bitField0_ |= 0x40;
        onChanged();
        return this;
      }
      
      public Builder clearDeviceHash() {
        this.deviceHash_ = WebShareFeedOuterClass.WebShareFeed.getDefaultInstance().getDeviceHash();
        this.bitField0_ &= 0xFFFFFFBF;
        onChanged();
        return this;
      }
      
      public Builder setDeviceHashBytes(ByteString value) {
        if (value == null)
          throw new NullPointerException(); 
        WebShareFeedOuterClass.WebShareFeed.checkByteStringIsUtf8(value);
        this.deviceHash_ = value;
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
    
    private static final WebShareFeed DEFAULT_INSTANCE = new WebShareFeed();
    
    public static WebShareFeed getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }
    
    private static final Parser<WebShareFeed> PARSER = (Parser<WebShareFeed>)new AbstractParser<WebShareFeed>() {
        public WebShareFeedOuterClass.WebShareFeed parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
          WebShareFeedOuterClass.WebShareFeed.Builder builder = WebShareFeedOuterClass.WebShareFeed.newBuilder();
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
    
    public static Parser<WebShareFeed> parser() {
      return PARSER;
    }
    
    public Parser<WebShareFeed> getParserForType() {
      return PARSER;
    }
    
    public WebShareFeed getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }
  }
  
  public static Descriptors.FileDescriptor getDescriptor() {
    return descriptor;
  }
  
  static {
    String[] descriptorData = { "\n\022WebShareFeed.proto\032\024SimpleUserInfo.proto\032\032WebLiveAssistantType.proto\"»\001\n\fWebShareFeed\022\n\n\002id\030\001 \001(\t\022\035\n\004user\030\002 \001(\0132\017.SimpleUserInfo\022\f\n\004time\030\003 \001(\004\022\032\n\022thirdPartyPlatform\030\004 \001(\r\022\020\n\bsortRank\030\005 \001(\004\0220\n\021liveAssistantType\030\006 \001(\0162\025.WebLiveAssistantType\022\022\n\ndeviceHash\030\007 \001(\tB<\n:tech.ordinaryroad.live.chat.client.codec.kuaishou.protobufb\006proto3" };
    descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[] { SimpleUserInfoOuterClass.getDescriptor(), 
          WebLiveAssistantTypeOuterClass.getDescriptor() });
    internal_static_WebShareFeed_descriptor = getDescriptor().getMessageTypes().get(0);
    internal_static_WebShareFeed_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_WebShareFeed_descriptor, new String[] { "Id", "User", "Time", "ThirdPartyPlatform", "SortRank", "LiveAssistantType", "DeviceHash" });
    SimpleUserInfoOuterClass.getDescriptor();
    WebLiveAssistantTypeOuterClass.getDescriptor();
  }
  
  public static interface WebShareFeedOrBuilder extends MessageOrBuilder {
    String getId();
    
    ByteString getIdBytes();
    
    boolean hasUser();
    
    SimpleUserInfoOuterClass.SimpleUserInfo getUser();
    
    SimpleUserInfoOuterClass.SimpleUserInfoOrBuilder getUserOrBuilder();
    
    long getTime();
    
    int getThirdPartyPlatform();
    
    long getSortRank();
    
    int getLiveAssistantTypeValue();
    
    WebLiveAssistantTypeOuterClass.WebLiveAssistantType getLiveAssistantType();
    
    String getDeviceHash();
    
    ByteString getDeviceHashBytes();
  }
}
