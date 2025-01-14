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

public final class WebCommentFeedOuterClass {
  private static final Descriptors.Descriptor internal_static_WebCommentFeed_descriptor;
  
  private static final GeneratedMessageV3.FieldAccessorTable internal_static_WebCommentFeed_fieldAccessorTable;
  
  private static Descriptors.FileDescriptor descriptor;
  
  public static void registerAllExtensions(ExtensionRegistryLite registry) {}
  
  public static void registerAllExtensions(ExtensionRegistry registry) {
    registerAllExtensions((ExtensionRegistryLite)registry);
  }
  
  public static final class WebCommentFeed extends GeneratedMessageV3 implements WebCommentFeedOrBuilder {
    private static final long serialVersionUID = 0L;
    
    private int bitField0_;
    
    public static final int ID_FIELD_NUMBER = 1;
    
    private volatile Object id_;
    
    public static final int USER_FIELD_NUMBER = 2;
    
    private SimpleUserInfoOuterClass.SimpleUserInfo user_;
    
    public static final int CONTENT_FIELD_NUMBER = 3;
    
    private volatile Object content_;
    
    public static final int DEVICEHASH_FIELD_NUMBER = 4;
    
    private volatile Object deviceHash_;
    
    public static final int SORTRANK_FIELD_NUMBER = 5;
    
    private long sortRank_;
    
    public static final int COLOR_FIELD_NUMBER = 6;
    
    private volatile Object color_;
    
    public static final int SHOWTYPE_FIELD_NUMBER = 7;
    
    private int showType_;
    
    public static final int SENDERSTATE_FIELD_NUMBER = 8;
    
    private LiveAudienceStateOuterClass.LiveAudienceState senderState_;
    
    private byte memoizedIsInitialized;
    
    private WebCommentFeed(GeneratedMessageV3.Builder<?> builder) {
      super(builder);
      this.id_ = "";
      this.content_ = "";
      this.deviceHash_ = "";
      this.sortRank_ = 0L;
      this.color_ = "";
      this.showType_ = 0;
      this.memoizedIsInitialized = -1;
    }
    
    private WebCommentFeed() {
      this.id_ = "";
      this.content_ = "";
      this.deviceHash_ = "";
      this.sortRank_ = 0L;
      this.color_ = "";
      this.showType_ = 0;
      this.memoizedIsInitialized = -1;
      this.id_ = "";
      this.content_ = "";
      this.deviceHash_ = "";
      this.color_ = "";
      this.showType_ = 0;
    }
    
    protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
      return new WebCommentFeed();
    }
    
    public static final Descriptors.Descriptor getDescriptor() {
      return WebCommentFeedOuterClass.internal_static_WebCommentFeed_descriptor;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return WebCommentFeedOuterClass.internal_static_WebCommentFeed_fieldAccessorTable.ensureFieldAccessorsInitialized(WebCommentFeed.class, Builder.class);
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
    
    public long getSortRank() {
      return this.sortRank_;
    }
    
    public String getColor() {
      Object ref = this.color_;
      if (ref instanceof String)
        return (String)ref; 
      ByteString bs = (ByteString)ref;
      String s = bs.toStringUtf8();
      this.color_ = s;
      return s;
    }
    
    public ByteString getColorBytes() {
      Object ref = this.color_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.color_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public int getShowTypeValue() {
      return this.showType_;
    }
    
    public WebCommentFeedShowTypeOuterClass.WebCommentFeedShowType getShowType() {
      WebCommentFeedShowTypeOuterClass.WebCommentFeedShowType result = WebCommentFeedShowTypeOuterClass.WebCommentFeedShowType.forNumber(this.showType_);
      return (result == null) ? WebCommentFeedShowTypeOuterClass.WebCommentFeedShowType.UNRECOGNIZED : result;
    }
    
    public boolean hasSenderState() {
      return ((this.bitField0_ & 0x2) != 0);
    }
    
    public LiveAudienceStateOuterClass.LiveAudienceState getSenderState() {
      return (this.senderState_ == null) ? LiveAudienceStateOuterClass.LiveAudienceState.getDefaultInstance() : this.senderState_;
    }
    
    public LiveAudienceStateOuterClass.LiveAudienceStateOrBuilder getSenderStateOrBuilder() {
      return (this.senderState_ == null) ? LiveAudienceStateOuterClass.LiveAudienceState.getDefaultInstance() : this.senderState_;
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
      if (!GeneratedMessageV3.isStringEmpty(this.content_))
        GeneratedMessageV3.writeString(output, 3, this.content_); 
      if (!GeneratedMessageV3.isStringEmpty(this.deviceHash_))
        GeneratedMessageV3.writeString(output, 4, this.deviceHash_); 
      if (this.sortRank_ != 0L)
        output.writeUInt64(5, this.sortRank_); 
      if (!GeneratedMessageV3.isStringEmpty(this.color_))
        GeneratedMessageV3.writeString(output, 6, this.color_); 
      if (this.showType_ != WebCommentFeedShowTypeOuterClass.WebCommentFeedShowType.FEED_SHOW_UNKNOWN.getNumber())
        output.writeEnum(7, this.showType_); 
      if ((this.bitField0_ & 0x2) != 0)
        output.writeMessage(8, (MessageLite)getSenderState()); 
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
      if (!GeneratedMessageV3.isStringEmpty(this.content_))
        size += GeneratedMessageV3.computeStringSize(3, this.content_); 
      if (!GeneratedMessageV3.isStringEmpty(this.deviceHash_))
        size += GeneratedMessageV3.computeStringSize(4, this.deviceHash_); 
      if (this.sortRank_ != 0L)
        size += 
          CodedOutputStream.computeUInt64Size(5, this.sortRank_); 
      if (!GeneratedMessageV3.isStringEmpty(this.color_))
        size += GeneratedMessageV3.computeStringSize(6, this.color_); 
      if (this.showType_ != WebCommentFeedShowTypeOuterClass.WebCommentFeedShowType.FEED_SHOW_UNKNOWN.getNumber())
        size += 
          CodedOutputStream.computeEnumSize(7, this.showType_); 
      if ((this.bitField0_ & 0x2) != 0)
        size += 
          CodedOutputStream.computeMessageSize(8, (MessageLite)getSenderState()); 
      size += getUnknownFields().getSerializedSize();
      this.memoizedSize = size;
      return size;
    }
    
    public boolean equals(Object obj) {
      if (obj == this)
        return true; 
      if (!(obj instanceof WebCommentFeed))
        return super.equals(obj); 
      WebCommentFeed other = (WebCommentFeed)obj;
      if (!getId().equals(other.getId()))
        return false; 
      if (hasUser() != other.hasUser())
        return false; 
      if (hasUser() && 
        
        !getUser().equals(other.getUser()))
        return false; 
      if (!getContent().equals(other.getContent()))
        return false; 
      if (!getDeviceHash().equals(other.getDeviceHash()))
        return false; 
      if (getSortRank() != other
        .getSortRank())
        return false; 
      if (!getColor().equals(other.getColor()))
        return false; 
      if (this.showType_ != other.showType_)
        return false; 
      if (hasSenderState() != other.hasSenderState())
        return false; 
      if (hasSenderState() && 
        
        !getSenderState().equals(other.getSenderState()))
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
      hash = 53 * hash + getContent().hashCode();
      hash = 37 * hash + 4;
      hash = 53 * hash + getDeviceHash().hashCode();
      hash = 37 * hash + 5;
      hash = 53 * hash + Internal.hashLong(
          getSortRank());
      hash = 37 * hash + 6;
      hash = 53 * hash + getColor().hashCode();
      hash = 37 * hash + 7;
      hash = 53 * hash + this.showType_;
      if (hasSenderState()) {
        hash = 37 * hash + 8;
        hash = 53 * hash + getSenderState().hashCode();
      } 
      hash = 29 * hash + getUnknownFields().hashCode();
      this.memoizedHashCode = hash;
      return hash;
    }
    
    public static WebCommentFeed parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (WebCommentFeed)PARSER.parseFrom(data);
    }
    
    public static WebCommentFeed parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (WebCommentFeed)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static WebCommentFeed parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (WebCommentFeed)PARSER.parseFrom(data);
    }
    
    public static WebCommentFeed parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (WebCommentFeed)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static WebCommentFeed parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (WebCommentFeed)PARSER.parseFrom(data);
    }
    
    public static WebCommentFeed parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (WebCommentFeed)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static WebCommentFeed parseFrom(InputStream input) throws IOException {
      return 
        (WebCommentFeed)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static WebCommentFeed parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (WebCommentFeed)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static WebCommentFeed parseDelimitedFrom(InputStream input) throws IOException {
      return 
        (WebCommentFeed)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }
    
    public static WebCommentFeed parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (WebCommentFeed)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static WebCommentFeed parseFrom(CodedInputStream input) throws IOException {
      return 
        (WebCommentFeed)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static WebCommentFeed parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (WebCommentFeed)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public Builder newBuilderForType() {
      return newBuilder();
    }
    
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    
    public static Builder newBuilder(WebCommentFeed prototype) {
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
    
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements WebCommentFeedOuterClass.WebCommentFeedOrBuilder {
      private int bitField0_;
      
      private Object id_;
      
      private SimpleUserInfoOuterClass.SimpleUserInfo user_;
      
      private SingleFieldBuilderV3<SimpleUserInfoOuterClass.SimpleUserInfo, SimpleUserInfoOuterClass.SimpleUserInfo.Builder, SimpleUserInfoOuterClass.SimpleUserInfoOrBuilder> userBuilder_;
      
      private Object content_;
      
      private Object deviceHash_;
      
      private long sortRank_;
      
      private Object color_;
      
      private int showType_;
      
      private LiveAudienceStateOuterClass.LiveAudienceState senderState_;
      
      private SingleFieldBuilderV3<LiveAudienceStateOuterClass.LiveAudienceState, LiveAudienceStateOuterClass.LiveAudienceState.Builder, LiveAudienceStateOuterClass.LiveAudienceStateOrBuilder> senderStateBuilder_;
      
      public static final Descriptors.Descriptor getDescriptor() {
        return WebCommentFeedOuterClass.internal_static_WebCommentFeed_descriptor;
      }
      
      protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return WebCommentFeedOuterClass.internal_static_WebCommentFeed_fieldAccessorTable
          .ensureFieldAccessorsInitialized(WebCommentFeedOuterClass.WebCommentFeed.class, Builder.class);
      }
      
      private Builder() {
        this.id_ = "";
        this.content_ = "";
        this.deviceHash_ = "";
        this.color_ = "";
        this.showType_ = 0;
        maybeForceBuilderInitialization();
      }
      
      private Builder(GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        this.id_ = "";
        this.content_ = "";
        this.deviceHash_ = "";
        this.color_ = "";
        this.showType_ = 0;
        maybeForceBuilderInitialization();
      }
      
      private void maybeForceBuilderInitialization() {
        if (WebCommentFeedOuterClass.WebCommentFeed.alwaysUseFieldBuilders) {
          getUserFieldBuilder();
          getSenderStateFieldBuilder();
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
        this.content_ = "";
        this.deviceHash_ = "";
        this.sortRank_ = 0L;
        this.color_ = "";
        this.showType_ = 0;
        this.senderState_ = null;
        if (this.senderStateBuilder_ != null) {
          this.senderStateBuilder_.dispose();
          this.senderStateBuilder_ = null;
        } 
        return this;
      }
      
      public Descriptors.Descriptor getDescriptorForType() {
        return WebCommentFeedOuterClass.internal_static_WebCommentFeed_descriptor;
      }
      
      public WebCommentFeedOuterClass.WebCommentFeed getDefaultInstanceForType() {
        return WebCommentFeedOuterClass.WebCommentFeed.getDefaultInstance();
      }
      
      public WebCommentFeedOuterClass.WebCommentFeed build() {
        WebCommentFeedOuterClass.WebCommentFeed result = buildPartial();
        if (!result.isInitialized())
          throw newUninitializedMessageException(result); 
        return result;
      }
      
      public WebCommentFeedOuterClass.WebCommentFeed buildPartial() {
        WebCommentFeedOuterClass.WebCommentFeed result = new WebCommentFeedOuterClass.WebCommentFeed(this);
        if (this.bitField0_ != 0)
          buildPartial0(result); 
        onBuilt();
        return result;
      }
      
      private void buildPartial0(WebCommentFeedOuterClass.WebCommentFeed result) {
        int from_bitField0_ = this.bitField0_;
        if ((from_bitField0_ & 0x1) != 0)
          result.id_ = this.id_; 
        int to_bitField0_ = 0;
        if ((from_bitField0_ & 0x2) != 0) {
          result.user_ = (this.userBuilder_ == null) ? this.user_ : (SimpleUserInfoOuterClass.SimpleUserInfo)this.userBuilder_.build();
          to_bitField0_ |= 0x1;
        } 
        if ((from_bitField0_ & 0x4) != 0)
          result.content_ = this.content_; 
        if ((from_bitField0_ & 0x8) != 0)
          result.deviceHash_ = this.deviceHash_; 
        if ((from_bitField0_ & 0x10) != 0)
          result.sortRank_ = this.sortRank_; 
        if ((from_bitField0_ & 0x20) != 0)
          result.color_ = this.color_; 
        if ((from_bitField0_ & 0x40) != 0)
          result.showType_ = this.showType_; 
        if ((from_bitField0_ & 0x80) != 0) {
          result.senderState_ = (this.senderStateBuilder_ == null) ? this.senderState_ : (LiveAudienceStateOuterClass.LiveAudienceState)this.senderStateBuilder_.build();
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
        if (other instanceof WebCommentFeedOuterClass.WebCommentFeed)
          return mergeFrom((WebCommentFeedOuterClass.WebCommentFeed)other); 
        super.mergeFrom(other);
        return this;
      }
      
      public Builder mergeFrom(WebCommentFeedOuterClass.WebCommentFeed other) {
        if (other == WebCommentFeedOuterClass.WebCommentFeed.getDefaultInstance())
          return this; 
        if (!other.getId().isEmpty()) {
          this.id_ = other.id_;
          this.bitField0_ |= 0x1;
          onChanged();
        } 
        if (other.hasUser())
          mergeUser(other.getUser()); 
        if (!other.getContent().isEmpty()) {
          this.content_ = other.content_;
          this.bitField0_ |= 0x4;
          onChanged();
        } 
        if (!other.getDeviceHash().isEmpty()) {
          this.deviceHash_ = other.deviceHash_;
          this.bitField0_ |= 0x8;
          onChanged();
        } 
        if (other.getSortRank() != 0L)
          setSortRank(other.getSortRank()); 
        if (!other.getColor().isEmpty()) {
          this.color_ = other.color_;
          this.bitField0_ |= 0x20;
          onChanged();
        } 
        if (other.showType_ != 0)
          setShowTypeValue(other.getShowTypeValue()); 
        if (other.hasSenderState())
          mergeSenderState(other.getSenderState()); 
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
              case 26:
                this.content_ = input.readStringRequireUtf8();
                this.bitField0_ |= 0x4;
                continue;
              case 34:
                this.deviceHash_ = input.readStringRequireUtf8();
                this.bitField0_ |= 0x8;
                continue;
              case 40:
                this.sortRank_ = input.readUInt64();
                this.bitField0_ |= 0x10;
                continue;
              case 50:
                this.color_ = input.readStringRequireUtf8();
                this.bitField0_ |= 0x20;
                continue;
              case 56:
                this.showType_ = input.readEnum();
                this.bitField0_ |= 0x40;
                continue;
              case 66:
                input.readMessage((MessageLite.Builder)getSenderStateFieldBuilder().getBuilder(), extensionRegistry);
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
        this.id_ = WebCommentFeedOuterClass.WebCommentFeed.getDefaultInstance().getId();
        this.bitField0_ &= 0xFFFFFFFE;
        onChanged();
        return this;
      }
      
      public Builder setIdBytes(ByteString value) {
        if (value == null)
          throw new NullPointerException(); 
        WebCommentFeedOuterClass.WebCommentFeed.checkByteStringIsUtf8(value);
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
        this.bitField0_ |= 0x4;
        onChanged();
        return this;
      }
      
      public Builder clearContent() {
        this.content_ = WebCommentFeedOuterClass.WebCommentFeed.getDefaultInstance().getContent();
        this.bitField0_ &= 0xFFFFFFFB;
        onChanged();
        return this;
      }
      
      public Builder setContentBytes(ByteString value) {
        if (value == null)
          throw new NullPointerException(); 
        WebCommentFeedOuterClass.WebCommentFeed.checkByteStringIsUtf8(value);
        this.content_ = value;
        this.bitField0_ |= 0x4;
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
        this.deviceHash_ = WebCommentFeedOuterClass.WebCommentFeed.getDefaultInstance().getDeviceHash();
        this.bitField0_ &= 0xFFFFFFF7;
        onChanged();
        return this;
      }
      
      public Builder setDeviceHashBytes(ByteString value) {
        if (value == null)
          throw new NullPointerException(); 
        WebCommentFeedOuterClass.WebCommentFeed.checkByteStringIsUtf8(value);
        this.deviceHash_ = value;
        this.bitField0_ |= 0x8;
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
      
      public String getColor() {
        Object ref = this.color_;
        if (!(ref instanceof String)) {
          ByteString bs = (ByteString)ref;
          String s = bs.toStringUtf8();
          this.color_ = s;
          return s;
        } 
        return (String)ref;
      }
      
      public ByteString getColorBytes() {
        Object ref = this.color_;
        if (ref instanceof String) {
          ByteString b = ByteString.copyFromUtf8((String)ref);
          this.color_ = b;
          return b;
        } 
        return (ByteString)ref;
      }
      
      public Builder setColor(String value) {
        if (value == null)
          throw new NullPointerException(); 
        this.color_ = value;
        this.bitField0_ |= 0x20;
        onChanged();
        return this;
      }
      
      public Builder clearColor() {
        this.color_ = WebCommentFeedOuterClass.WebCommentFeed.getDefaultInstance().getColor();
        this.bitField0_ &= 0xFFFFFFDF;
        onChanged();
        return this;
      }
      
      public Builder setColorBytes(ByteString value) {
        if (value == null)
          throw new NullPointerException(); 
        WebCommentFeedOuterClass.WebCommentFeed.checkByteStringIsUtf8(value);
        this.color_ = value;
        this.bitField0_ |= 0x20;
        onChanged();
        return this;
      }
      
      public int getShowTypeValue() {
        return this.showType_;
      }
      
      public Builder setShowTypeValue(int value) {
        this.showType_ = value;
        this.bitField0_ |= 0x40;
        onChanged();
        return this;
      }
      
      public WebCommentFeedShowTypeOuterClass.WebCommentFeedShowType getShowType() {
        WebCommentFeedShowTypeOuterClass.WebCommentFeedShowType result = WebCommentFeedShowTypeOuterClass.WebCommentFeedShowType.forNumber(this.showType_);
        return (result == null) ? WebCommentFeedShowTypeOuterClass.WebCommentFeedShowType.UNRECOGNIZED : result;
      }
      
      public Builder setShowType(WebCommentFeedShowTypeOuterClass.WebCommentFeedShowType value) {
        if (value == null)
          throw new NullPointerException(); 
        this.bitField0_ |= 0x40;
        this.showType_ = value.getNumber();
        onChanged();
        return this;
      }
      
      public Builder clearShowType() {
        this.bitField0_ &= 0xFFFFFFBF;
        this.showType_ = 0;
        onChanged();
        return this;
      }
      
      public boolean hasSenderState() {
        return ((this.bitField0_ & 0x80) != 0);
      }
      
      public LiveAudienceStateOuterClass.LiveAudienceState getSenderState() {
        if (this.senderStateBuilder_ == null)
          return (this.senderState_ == null) ? LiveAudienceStateOuterClass.LiveAudienceState.getDefaultInstance() : this.senderState_; 
        return (LiveAudienceStateOuterClass.LiveAudienceState)this.senderStateBuilder_.getMessage();
      }
      
      public Builder setSenderState(LiveAudienceStateOuterClass.LiveAudienceState value) {
        if (this.senderStateBuilder_ == null) {
          if (value == null)
            throw new NullPointerException(); 
          this.senderState_ = value;
        } else {
          this.senderStateBuilder_.setMessage((AbstractMessage)value);
        } 
        this.bitField0_ |= 0x80;
        onChanged();
        return this;
      }
      
      public Builder setSenderState(LiveAudienceStateOuterClass.LiveAudienceState.Builder builderForValue) {
        if (this.senderStateBuilder_ == null) {
          this.senderState_ = builderForValue.build();
        } else {
          this.senderStateBuilder_.setMessage((AbstractMessage)builderForValue.build());
        } 
        this.bitField0_ |= 0x80;
        onChanged();
        return this;
      }
      
      public Builder mergeSenderState(LiveAudienceStateOuterClass.LiveAudienceState value) {
        if (this.senderStateBuilder_ == null) {
          if ((this.bitField0_ & 0x80) != 0 && this.senderState_ != null && this.senderState_ != 
            
            LiveAudienceStateOuterClass.LiveAudienceState.getDefaultInstance()) {
            getSenderStateBuilder().mergeFrom(value);
          } else {
            this.senderState_ = value;
          } 
        } else {
          this.senderStateBuilder_.mergeFrom((AbstractMessage)value);
        } 
        if (this.senderState_ != null) {
          this.bitField0_ |= 0x80;
          onChanged();
        } 
        return this;
      }
      
      public Builder clearSenderState() {
        this.bitField0_ &= 0xFFFFFF7F;
        this.senderState_ = null;
        if (this.senderStateBuilder_ != null) {
          this.senderStateBuilder_.dispose();
          this.senderStateBuilder_ = null;
        } 
        onChanged();
        return this;
      }
      
      public LiveAudienceStateOuterClass.LiveAudienceState.Builder getSenderStateBuilder() {
        this.bitField0_ |= 0x80;
        onChanged();
        return (LiveAudienceStateOuterClass.LiveAudienceState.Builder)getSenderStateFieldBuilder().getBuilder();
      }
      
      public LiveAudienceStateOuterClass.LiveAudienceStateOrBuilder getSenderStateOrBuilder() {
        if (this.senderStateBuilder_ != null)
          return (LiveAudienceStateOuterClass.LiveAudienceStateOrBuilder)this.senderStateBuilder_.getMessageOrBuilder(); 
        return (this.senderState_ == null) ? 
          LiveAudienceStateOuterClass.LiveAudienceState.getDefaultInstance() : this.senderState_;
      }
      
      private SingleFieldBuilderV3<LiveAudienceStateOuterClass.LiveAudienceState, LiveAudienceStateOuterClass.LiveAudienceState.Builder, LiveAudienceStateOuterClass.LiveAudienceStateOrBuilder> getSenderStateFieldBuilder() {
        if (this.senderStateBuilder_ == null) {
          this
            
            .senderStateBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getSenderState(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
          this.senderState_ = null;
        } 
        return this.senderStateBuilder_;
      }
      
      public final Builder setUnknownFields(UnknownFieldSet unknownFields) {
        return (Builder)super.setUnknownFields(unknownFields);
      }
      
      public final Builder mergeUnknownFields(UnknownFieldSet unknownFields) {
        return (Builder)super.mergeUnknownFields(unknownFields);
      }
    }
    
    private static final WebCommentFeed DEFAULT_INSTANCE = new WebCommentFeed();
    
    public static WebCommentFeed getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }
    
    private static final Parser<WebCommentFeed> PARSER = (Parser<WebCommentFeed>)new AbstractParser<WebCommentFeed>() {
        public WebCommentFeedOuterClass.WebCommentFeed parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
          WebCommentFeedOuterClass.WebCommentFeed.Builder builder = WebCommentFeedOuterClass.WebCommentFeed.newBuilder();
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
    
    public static Parser<WebCommentFeed> parser() {
      return PARSER;
    }
    
    public Parser<WebCommentFeed> getParserForType() {
      return PARSER;
    }
    
    public WebCommentFeed getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }
  }
  
  public static Descriptors.FileDescriptor getDescriptor() {
    return descriptor;
  }
  
  static {
    String[] descriptorData = { "\n\024WebCommentFeed.proto\032\024SimpleUserInfo.proto\032\034WebCommentFeedShowType.proto\032\027LiveAudienceState.proto\"Õ\001\n\016WebCommentFeed\022\n\n\002id\030\001 \001(\t\022\035\n\004user\030\002 \001(\0132\017.SimpleUserInfo\022\017\n\007content\030\003 \001(\t\022\022\n\ndeviceHash\030\004 \001(\t\022\020\n\bsortRank\030\005 \001(\004\022\r\n\005color\030\006 \001(\t\022)\n\bshowType\030\007 \001(\0162\027.WebCommentFeedShowType\022'\n\013senderState\030\b \001(\0132\022.LiveAudienceStateB<\n:tech.ordinaryroad.live.chat.client.codec.kuaishou.protobufb\006proto3" };
    descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[] { SimpleUserInfoOuterClass.getDescriptor(), 
          WebCommentFeedShowTypeOuterClass.getDescriptor(), 
          LiveAudienceStateOuterClass.getDescriptor() });
    internal_static_WebCommentFeed_descriptor = getDescriptor().getMessageTypes().get(0);
    internal_static_WebCommentFeed_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_WebCommentFeed_descriptor, new String[] { "Id", "User", "Content", "DeviceHash", "SortRank", "Color", "ShowType", "SenderState" });
    SimpleUserInfoOuterClass.getDescriptor();
    WebCommentFeedShowTypeOuterClass.getDescriptor();
    LiveAudienceStateOuterClass.getDescriptor();
  }
  
  public static interface WebCommentFeedOrBuilder extends MessageOrBuilder {
    String getId();
    
    ByteString getIdBytes();
    
    boolean hasUser();
    
    SimpleUserInfoOuterClass.SimpleUserInfo getUser();
    
    SimpleUserInfoOuterClass.SimpleUserInfoOrBuilder getUserOrBuilder();
    
    String getContent();
    
    ByteString getContentBytes();
    
    String getDeviceHash();
    
    ByteString getDeviceHashBytes();
    
    long getSortRank();
    
    String getColor();
    
    ByteString getColorBytes();
    
    int getShowTypeValue();
    
    WebCommentFeedShowTypeOuterClass.WebCommentFeedShowType getShowType();
    
    boolean hasSenderState();
    
    LiveAudienceStateOuterClass.LiveAudienceState getSenderState();
    
    LiveAudienceStateOuterClass.LiveAudienceStateOrBuilder getSenderStateOrBuilder();
  }
}
