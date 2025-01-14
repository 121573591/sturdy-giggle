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

public final class WebLikeFeedOuterClass {
  private static final Descriptors.Descriptor internal_static_WebLikeFeed_descriptor;
  
  private static final GeneratedMessageV3.FieldAccessorTable internal_static_WebLikeFeed_fieldAccessorTable;
  
  private static Descriptors.FileDescriptor descriptor;
  
  public static void registerAllExtensions(ExtensionRegistryLite registry) {}
  
  public static void registerAllExtensions(ExtensionRegistry registry) {
    registerAllExtensions((ExtensionRegistryLite)registry);
  }
  
  public static final class WebLikeFeed extends GeneratedMessageV3 implements WebLikeFeedOrBuilder {
    private static final long serialVersionUID = 0L;
    
    private int bitField0_;
    
    public static final int ID_FIELD_NUMBER = 1;
    
    private volatile Object id_;
    
    public static final int USER_FIELD_NUMBER = 2;
    
    private SimpleUserInfoOuterClass.SimpleUserInfo user_;
    
    public static final int SORTRANK_FIELD_NUMBER = 3;
    
    private long sortRank_;
    
    public static final int DEVICEHASH_FIELD_NUMBER = 4;
    
    private volatile Object deviceHash_;
    
    public static final int LIVEAUDIENCESTATE_FIELD_NUMBER = 5;
    
    private LiveAudienceStateOuterClass.LiveAudienceState liveAudienceState_;
    
    private byte memoizedIsInitialized;
    
    private WebLikeFeed(GeneratedMessageV3.Builder<?> builder) {
      super(builder);
      this.id_ = "";
      this.sortRank_ = 0L;
      this.deviceHash_ = "";
      this.memoizedIsInitialized = -1;
    }
    
    private WebLikeFeed() {
      this.id_ = "";
      this.sortRank_ = 0L;
      this.deviceHash_ = "";
      this.memoizedIsInitialized = -1;
      this.id_ = "";
      this.deviceHash_ = "";
    }
    
    protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
      return new WebLikeFeed();
    }
    
    public static final Descriptors.Descriptor getDescriptor() {
      return WebLikeFeedOuterClass.internal_static_WebLikeFeed_descriptor;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return WebLikeFeedOuterClass.internal_static_WebLikeFeed_fieldAccessorTable.ensureFieldAccessorsInitialized(WebLikeFeed.class, Builder.class);
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
    
    public long getSortRank() {
      return this.sortRank_;
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
    
    public boolean hasLiveAudienceState() {
      return ((this.bitField0_ & 0x2) != 0);
    }
    
    public LiveAudienceStateOuterClass.LiveAudienceState getLiveAudienceState() {
      return (this.liveAudienceState_ == null) ? LiveAudienceStateOuterClass.LiveAudienceState.getDefaultInstance() : this.liveAudienceState_;
    }
    
    public LiveAudienceStateOuterClass.LiveAudienceStateOrBuilder getLiveAudienceStateOrBuilder() {
      return (this.liveAudienceState_ == null) ? LiveAudienceStateOuterClass.LiveAudienceState.getDefaultInstance() : this.liveAudienceState_;
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
      if (this.sortRank_ != 0L)
        output.writeUInt64(3, this.sortRank_); 
      if (!GeneratedMessageV3.isStringEmpty(this.deviceHash_))
        GeneratedMessageV3.writeString(output, 4, this.deviceHash_); 
      if ((this.bitField0_ & 0x2) != 0)
        output.writeMessage(5, (MessageLite)getLiveAudienceState()); 
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
      if (this.sortRank_ != 0L)
        size += 
          CodedOutputStream.computeUInt64Size(3, this.sortRank_); 
      if (!GeneratedMessageV3.isStringEmpty(this.deviceHash_))
        size += GeneratedMessageV3.computeStringSize(4, this.deviceHash_); 
      if ((this.bitField0_ & 0x2) != 0)
        size += 
          CodedOutputStream.computeMessageSize(5, (MessageLite)getLiveAudienceState()); 
      size += getUnknownFields().getSerializedSize();
      this.memoizedSize = size;
      return size;
    }
    
    public boolean equals(Object obj) {
      if (obj == this)
        return true; 
      if (!(obj instanceof WebLikeFeed))
        return super.equals(obj); 
      WebLikeFeed other = (WebLikeFeed)obj;
      if (!getId().equals(other.getId()))
        return false; 
      if (hasUser() != other.hasUser())
        return false; 
      if (hasUser() && 
        
        !getUser().equals(other.getUser()))
        return false; 
      if (getSortRank() != other
        .getSortRank())
        return false; 
      if (!getDeviceHash().equals(other.getDeviceHash()))
        return false; 
      if (hasLiveAudienceState() != other.hasLiveAudienceState())
        return false; 
      if (hasLiveAudienceState() && 
        
        !getLiveAudienceState().equals(other.getLiveAudienceState()))
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
          getSortRank());
      hash = 37 * hash + 4;
      hash = 53 * hash + getDeviceHash().hashCode();
      if (hasLiveAudienceState()) {
        hash = 37 * hash + 5;
        hash = 53 * hash + getLiveAudienceState().hashCode();
      } 
      hash = 29 * hash + getUnknownFields().hashCode();
      this.memoizedHashCode = hash;
      return hash;
    }
    
    public static WebLikeFeed parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (WebLikeFeed)PARSER.parseFrom(data);
    }
    
    public static WebLikeFeed parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (WebLikeFeed)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static WebLikeFeed parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (WebLikeFeed)PARSER.parseFrom(data);
    }
    
    public static WebLikeFeed parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (WebLikeFeed)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static WebLikeFeed parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (WebLikeFeed)PARSER.parseFrom(data);
    }
    
    public static WebLikeFeed parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (WebLikeFeed)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static WebLikeFeed parseFrom(InputStream input) throws IOException {
      return 
        (WebLikeFeed)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static WebLikeFeed parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (WebLikeFeed)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static WebLikeFeed parseDelimitedFrom(InputStream input) throws IOException {
      return 
        (WebLikeFeed)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }
    
    public static WebLikeFeed parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (WebLikeFeed)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static WebLikeFeed parseFrom(CodedInputStream input) throws IOException {
      return 
        (WebLikeFeed)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static WebLikeFeed parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (WebLikeFeed)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public Builder newBuilderForType() {
      return newBuilder();
    }
    
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    
    public static Builder newBuilder(WebLikeFeed prototype) {
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
    
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements WebLikeFeedOuterClass.WebLikeFeedOrBuilder {
      private int bitField0_;
      
      private Object id_;
      
      private SimpleUserInfoOuterClass.SimpleUserInfo user_;
      
      private SingleFieldBuilderV3<SimpleUserInfoOuterClass.SimpleUserInfo, SimpleUserInfoOuterClass.SimpleUserInfo.Builder, SimpleUserInfoOuterClass.SimpleUserInfoOrBuilder> userBuilder_;
      
      private long sortRank_;
      
      private Object deviceHash_;
      
      private LiveAudienceStateOuterClass.LiveAudienceState liveAudienceState_;
      
      private SingleFieldBuilderV3<LiveAudienceStateOuterClass.LiveAudienceState, LiveAudienceStateOuterClass.LiveAudienceState.Builder, LiveAudienceStateOuterClass.LiveAudienceStateOrBuilder> liveAudienceStateBuilder_;
      
      public static final Descriptors.Descriptor getDescriptor() {
        return WebLikeFeedOuterClass.internal_static_WebLikeFeed_descriptor;
      }
      
      protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return WebLikeFeedOuterClass.internal_static_WebLikeFeed_fieldAccessorTable
          .ensureFieldAccessorsInitialized(WebLikeFeedOuterClass.WebLikeFeed.class, Builder.class);
      }
      
      private Builder() {
        this.id_ = "";
        this.deviceHash_ = "";
        maybeForceBuilderInitialization();
      }
      
      private Builder(GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        this.id_ = "";
        this.deviceHash_ = "";
        maybeForceBuilderInitialization();
      }
      
      private void maybeForceBuilderInitialization() {
        if (WebLikeFeedOuterClass.WebLikeFeed.alwaysUseFieldBuilders) {
          getUserFieldBuilder();
          getLiveAudienceStateFieldBuilder();
        } 
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
        this.sortRank_ = 0L;
        this.deviceHash_ = "";
        this.liveAudienceState_ = null;
        if (this.liveAudienceStateBuilder_ != null) {
          this.liveAudienceStateBuilder_.dispose();
          this.liveAudienceStateBuilder_ = null;
        } 
        return this;
      }
      
      public Descriptors.Descriptor getDescriptorForType() {
        return WebLikeFeedOuterClass.internal_static_WebLikeFeed_descriptor;
      }
      
      public WebLikeFeedOuterClass.WebLikeFeed getDefaultInstanceForType() {
        return WebLikeFeedOuterClass.WebLikeFeed.getDefaultInstance();
      }
      
      public WebLikeFeedOuterClass.WebLikeFeed build() {
        WebLikeFeedOuterClass.WebLikeFeed result = buildPartial();
        if (!result.isInitialized())
          throw newUninitializedMessageException(result); 
        return result;
      }
      
      public WebLikeFeedOuterClass.WebLikeFeed buildPartial() {
        WebLikeFeedOuterClass.WebLikeFeed result = new WebLikeFeedOuterClass.WebLikeFeed(this);
        if (this.bitField0_ != 0)
          buildPartial0(result); 
        onBuilt();
        return result;
      }
      
      private void buildPartial0(WebLikeFeedOuterClass.WebLikeFeed result) {
        int from_bitField0_ = this.bitField0_;
        if ((from_bitField0_ & 0x1) != 0)
          result.id_ = this.id_; 
        int to_bitField0_ = 0;
        if ((from_bitField0_ & 0x2) != 0) {
          result.user_ = (this.userBuilder_ == null) ? this.user_ : (SimpleUserInfoOuterClass.SimpleUserInfo)this.userBuilder_.build();
          to_bitField0_ |= 0x1;
        } 
        if ((from_bitField0_ & 0x4) != 0)
          result.sortRank_ = this.sortRank_; 
        if ((from_bitField0_ & 0x8) != 0)
          result.deviceHash_ = this.deviceHash_; 
        if ((from_bitField0_ & 0x10) != 0) {
          result.liveAudienceState_ = (this.liveAudienceStateBuilder_ == null) ? this.liveAudienceState_ : (LiveAudienceStateOuterClass.LiveAudienceState)this.liveAudienceStateBuilder_.build();
          to_bitField0_ |= 0x2;
        } 
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
        if (other instanceof WebLikeFeedOuterClass.WebLikeFeed)
          return mergeFrom((WebLikeFeedOuterClass.WebLikeFeed)other); 
        super.mergeFrom(other);
        return this;
      }
      
      public Builder mergeFrom(WebLikeFeedOuterClass.WebLikeFeed other) {
        if (other == WebLikeFeedOuterClass.WebLikeFeed.getDefaultInstance())
          return this; 
        if (!other.getId().isEmpty()) {
          this.id_ = other.id_;
          this.bitField0_ |= 0x1;
          onChanged();
        } 
        if (other.hasUser())
          mergeUser(other.getUser()); 
        if (other.getSortRank() != 0L)
          setSortRank(other.getSortRank()); 
        if (!other.getDeviceHash().isEmpty()) {
          this.deviceHash_ = other.deviceHash_;
          this.bitField0_ |= 0x8;
          onChanged();
        } 
        if (other.hasLiveAudienceState())
          mergeLiveAudienceState(other.getLiveAudienceState()); 
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
                this.sortRank_ = input.readUInt64();
                this.bitField0_ |= 0x4;
                continue;
              case 34:
                this.deviceHash_ = input.readStringRequireUtf8();
                this.bitField0_ |= 0x8;
                continue;
              case 42:
                input.readMessage((MessageLite.Builder)getLiveAudienceStateFieldBuilder().getBuilder(), extensionRegistry);
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
        this.id_ = WebLikeFeedOuterClass.WebLikeFeed.getDefaultInstance().getId();
        this.bitField0_ &= 0xFFFFFFFE;
        onChanged();
        return this;
      }
      
      public Builder setIdBytes(ByteString value) {
        if (value == null)
          throw new NullPointerException(); 
        WebLikeFeedOuterClass.WebLikeFeed.checkByteStringIsUtf8(value);
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
      
      public long getSortRank() {
        return this.sortRank_;
      }
      
      public Builder setSortRank(long value) {
        this.sortRank_ = value;
        this.bitField0_ |= 0x4;
        onChanged();
        return this;
      }
      
      public Builder clearSortRank() {
        this.bitField0_ &= 0xFFFFFFFB;
        this.sortRank_ = 0L;
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
        this.bitField0_ |= 0x8;
        onChanged();
        return this;
      }
      
      public Builder clearDeviceHash() {
        this.deviceHash_ = WebLikeFeedOuterClass.WebLikeFeed.getDefaultInstance().getDeviceHash();
        this.bitField0_ &= 0xFFFFFFF7;
        onChanged();
        return this;
      }
      
      public Builder setDeviceHashBytes(ByteString value) {
        if (value == null)
          throw new NullPointerException(); 
        WebLikeFeedOuterClass.WebLikeFeed.checkByteStringIsUtf8(value);
        this.deviceHash_ = value;
        this.bitField0_ |= 0x8;
        onChanged();
        return this;
      }
      
      public boolean hasLiveAudienceState() {
        return ((this.bitField0_ & 0x10) != 0);
      }
      
      public LiveAudienceStateOuterClass.LiveAudienceState getLiveAudienceState() {
        if (this.liveAudienceStateBuilder_ == null)
          return (this.liveAudienceState_ == null) ? LiveAudienceStateOuterClass.LiveAudienceState.getDefaultInstance() : this.liveAudienceState_; 
        return (LiveAudienceStateOuterClass.LiveAudienceState)this.liveAudienceStateBuilder_.getMessage();
      }
      
      public Builder setLiveAudienceState(LiveAudienceStateOuterClass.LiveAudienceState value) {
        if (this.liveAudienceStateBuilder_ == null) {
          if (value == null)
            throw new NullPointerException(); 
          this.liveAudienceState_ = value;
        } else {
          this.liveAudienceStateBuilder_.setMessage((AbstractMessage)value);
        } 
        this.bitField0_ |= 0x10;
        onChanged();
        return this;
      }
      
      public Builder setLiveAudienceState(LiveAudienceStateOuterClass.LiveAudienceState.Builder builderForValue) {
        if (this.liveAudienceStateBuilder_ == null) {
          this.liveAudienceState_ = builderForValue.build();
        } else {
          this.liveAudienceStateBuilder_.setMessage((AbstractMessage)builderForValue.build());
        } 
        this.bitField0_ |= 0x10;
        onChanged();
        return this;
      }
      
      public Builder mergeLiveAudienceState(LiveAudienceStateOuterClass.LiveAudienceState value) {
        if (this.liveAudienceStateBuilder_ == null) {
          if ((this.bitField0_ & 0x10) != 0 && this.liveAudienceState_ != null && this.liveAudienceState_ != 
            
            LiveAudienceStateOuterClass.LiveAudienceState.getDefaultInstance()) {
            getLiveAudienceStateBuilder().mergeFrom(value);
          } else {
            this.liveAudienceState_ = value;
          } 
        } else {
          this.liveAudienceStateBuilder_.mergeFrom((AbstractMessage)value);
        } 
        if (this.liveAudienceState_ != null) {
          this.bitField0_ |= 0x10;
          onChanged();
        } 
        return this;
      }
      
      public Builder clearLiveAudienceState() {
        this.bitField0_ &= 0xFFFFFFEF;
        this.liveAudienceState_ = null;
        if (this.liveAudienceStateBuilder_ != null) {
          this.liveAudienceStateBuilder_.dispose();
          this.liveAudienceStateBuilder_ = null;
        } 
        onChanged();
        return this;
      }
      
      public LiveAudienceStateOuterClass.LiveAudienceState.Builder getLiveAudienceStateBuilder() {
        this.bitField0_ |= 0x10;
        onChanged();
        return (LiveAudienceStateOuterClass.LiveAudienceState.Builder)getLiveAudienceStateFieldBuilder().getBuilder();
      }
      
      public LiveAudienceStateOuterClass.LiveAudienceStateOrBuilder getLiveAudienceStateOrBuilder() {
        if (this.liveAudienceStateBuilder_ != null)
          return (LiveAudienceStateOuterClass.LiveAudienceStateOrBuilder)this.liveAudienceStateBuilder_.getMessageOrBuilder(); 
        return (this.liveAudienceState_ == null) ? 
          LiveAudienceStateOuterClass.LiveAudienceState.getDefaultInstance() : this.liveAudienceState_;
      }
      
      private SingleFieldBuilderV3<LiveAudienceStateOuterClass.LiveAudienceState, LiveAudienceStateOuterClass.LiveAudienceState.Builder, LiveAudienceStateOuterClass.LiveAudienceStateOrBuilder> getLiveAudienceStateFieldBuilder() {
        if (this.liveAudienceStateBuilder_ == null) {
          this
            
            .liveAudienceStateBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getLiveAudienceState(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
          this.liveAudienceState_ = null;
        } 
        return this.liveAudienceStateBuilder_;
      }
      
      public final Builder setUnknownFields(UnknownFieldSet unknownFields) {
        return (Builder)super.setUnknownFields(unknownFields);
      }
      
      public final Builder mergeUnknownFields(UnknownFieldSet unknownFields) {
        return (Builder)super.mergeUnknownFields(unknownFields);
      }
    }
    
    private static final WebLikeFeed DEFAULT_INSTANCE = new WebLikeFeed();
    
    public static WebLikeFeed getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }
    
    private static final Parser<WebLikeFeed> PARSER = (Parser<WebLikeFeed>)new AbstractParser<WebLikeFeed>() {
        public WebLikeFeedOuterClass.WebLikeFeed parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
          WebLikeFeedOuterClass.WebLikeFeed.Builder builder = WebLikeFeedOuterClass.WebLikeFeed.newBuilder();
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
    
    public static Parser<WebLikeFeed> parser() {
      return PARSER;
    }
    
    public Parser<WebLikeFeed> getParserForType() {
      return PARSER;
    }
    
    public WebLikeFeed getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }
  }
  
  public static Descriptors.FileDescriptor getDescriptor() {
    return descriptor;
  }
  
  static {
    String[] descriptorData = { "\n\021WebLikeFeed.proto\032\024SimpleUserInfo.proto\032\027LiveAudienceState.proto\"\001\n\013WebLikeFeed\022\n\n\002id\030\001 \001(\t\022\035\n\004user\030\002 \001(\0132\017.SimpleUserInfo\022\020\n\bsortRank\030\003 \001(\004\022\022\n\ndeviceHash\030\004 \001(\t\022-\n\021liveAudienceState\030\005 \001(\0132\022.LiveAudienceStateB<\n:tech.ordinaryroad.live.chat.client.codec.kuaishou.protobufb\006proto3" };
    descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[] { SimpleUserInfoOuterClass.getDescriptor(), 
          LiveAudienceStateOuterClass.getDescriptor() });
    internal_static_WebLikeFeed_descriptor = getDescriptor().getMessageTypes().get(0);
    internal_static_WebLikeFeed_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_WebLikeFeed_descriptor, new String[] { "Id", "User", "SortRank", "DeviceHash", "LiveAudienceState" });
    SimpleUserInfoOuterClass.getDescriptor();
    LiveAudienceStateOuterClass.getDescriptor();
  }
  
  public static interface WebLikeFeedOrBuilder extends MessageOrBuilder {
    String getId();
    
    ByteString getIdBytes();
    
    boolean hasUser();
    
    SimpleUserInfoOuterClass.SimpleUserInfo getUser();
    
    SimpleUserInfoOuterClass.SimpleUserInfoOrBuilder getUserOrBuilder();
    
    long getSortRank();
    
    String getDeviceHash();
    
    ByteString getDeviceHashBytes();
    
    boolean hasLiveAudienceState();
    
    LiveAudienceStateOuterClass.LiveAudienceState getLiveAudienceState();
    
    LiveAudienceStateOuterClass.LiveAudienceStateOrBuilder getLiveAudienceStateOrBuilder();
  }
}
