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
import com.google.protobuf.ProtocolMessageEnum;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class WebSystemNoticeFeedOuterClass {
  private static final Descriptors.Descriptor internal_static_WebSystemNoticeFeed_descriptor;
  
  private static final GeneratedMessageV3.FieldAccessorTable internal_static_WebSystemNoticeFeed_fieldAccessorTable;
  
  private static Descriptors.FileDescriptor descriptor;
  
  public static void registerAllExtensions(ExtensionRegistryLite registry) {}
  
  public static void registerAllExtensions(ExtensionRegistry registry) {
    registerAllExtensions((ExtensionRegistryLite)registry);
  }
  
  public static final class WebSystemNoticeFeed extends GeneratedMessageV3 implements WebSystemNoticeFeedOrBuilder {
    private static final long serialVersionUID = 0L;
    
    private int bitField0_;
    
    public static final int ID_FIELD_NUMBER = 1;
    
    private volatile Object id_;
    
    public static final int USER_FIELD_NUMBER = 2;
    
    private SimpleUserInfoOuterClass.SimpleUserInfo user_;
    
    public static final int TIME_FIELD_NUMBER = 3;
    
    private long time_;
    
    public static final int CONTENT_FIELD_NUMBER = 4;
    
    private volatile Object content_;
    
    public static final int DISPLAYDURATION_FIELD_NUMBER = 5;
    
    private long displayDuration_;
    
    public static final int SORTRANK_FIELD_NUMBER = 6;
    
    private long sortRank_;
    
    public static final int DISPLAYTYPE_FIELD_NUMBER = 7;
    
    private int displayType_;
    
    private byte memoizedIsInitialized;
    
    private WebSystemNoticeFeed(GeneratedMessageV3.Builder<?> builder) {
      super(builder);
      this.id_ = "";
      this.time_ = 0L;
      this.content_ = "";
      this.displayDuration_ = 0L;
      this.sortRank_ = 0L;
      this.displayType_ = 0;
      this.memoizedIsInitialized = -1;
    }
    
    private WebSystemNoticeFeed() {
      this.id_ = "";
      this.time_ = 0L;
      this.content_ = "";
      this.displayDuration_ = 0L;
      this.sortRank_ = 0L;
      this.displayType_ = 0;
      this.memoizedIsInitialized = -1;
      this.id_ = "";
      this.content_ = "";
      this.displayType_ = 0;
    }
    
    protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
      return new WebSystemNoticeFeed();
    }
    
    public static final Descriptors.Descriptor getDescriptor() {
      return WebSystemNoticeFeedOuterClass.internal_static_WebSystemNoticeFeed_descriptor;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return WebSystemNoticeFeedOuterClass.internal_static_WebSystemNoticeFeed_fieldAccessorTable.ensureFieldAccessorsInitialized(WebSystemNoticeFeed.class, Builder.class);
    }
    
    public enum DisplayType implements ProtocolMessageEnum {
      UNKNOWN_DISPLAY_TYPE(0),
      COMMENT(1),
      ALERT(2),
      TOAST(3),
      UNRECOGNIZED(-1);
      
      public static final int UNKNOWN_DISPLAY_TYPE_VALUE = 0;
      
      public static final int COMMENT_VALUE = 1;
      
      public static final int ALERT_VALUE = 2;
      
      public static final int TOAST_VALUE = 3;
      
      private static final Internal.EnumLiteMap<DisplayType> internalValueMap = new Internal.EnumLiteMap<DisplayType>() {
          public WebSystemNoticeFeedOuterClass.WebSystemNoticeFeed.DisplayType findValueByNumber(int number) {
            return WebSystemNoticeFeedOuterClass.WebSystemNoticeFeed.DisplayType.forNumber(number);
          }
        };
      
      private static final DisplayType[] VALUES = values();
      
      private final int value;
      
      public final int getNumber() {
        if (this == UNRECOGNIZED)
          throw new IllegalArgumentException("Can't get the number of an unknown enum value."); 
        return this.value;
      }
      
      public static DisplayType forNumber(int value) {
        switch (value) {
          case 0:
            return UNKNOWN_DISPLAY_TYPE;
          case 1:
            return COMMENT;
          case 2:
            return ALERT;
          case 3:
            return TOAST;
        } 
        return null;
      }
      
      public static Internal.EnumLiteMap<DisplayType> internalGetValueMap() {
        return internalValueMap;
      }
      
      static {
      
      }
      
      public final Descriptors.EnumValueDescriptor getValueDescriptor() {
        if (this == UNRECOGNIZED)
          throw new IllegalStateException("Can't get the descriptor of an unrecognized enum value."); 
        return getDescriptor().getValues().get(ordinal());
      }
      
      public final Descriptors.EnumDescriptor getDescriptorForType() {
        return getDescriptor();
      }
      
      public static final Descriptors.EnumDescriptor getDescriptor() {
        return WebSystemNoticeFeedOuterClass.WebSystemNoticeFeed.getDescriptor().getEnumTypes().get(0);
      }
      
      DisplayType(int value) {
        this.value = value;
      }
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
    
    public long getDisplayDuration() {
      return this.displayDuration_;
    }
    
    public long getSortRank() {
      return this.sortRank_;
    }
    
    public int getDisplayTypeValue() {
      return this.displayType_;
    }
    
    public DisplayType getDisplayType() {
      DisplayType result = DisplayType.forNumber(this.displayType_);
      return (result == null) ? DisplayType.UNRECOGNIZED : result;
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
      if (!GeneratedMessageV3.isStringEmpty(this.content_))
        GeneratedMessageV3.writeString(output, 4, this.content_); 
      if (this.displayDuration_ != 0L)
        output.writeUInt64(5, this.displayDuration_); 
      if (this.sortRank_ != 0L)
        output.writeUInt64(6, this.sortRank_); 
      if (this.displayType_ != DisplayType.UNKNOWN_DISPLAY_TYPE.getNumber())
        output.writeEnum(7, this.displayType_); 
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
      if (!GeneratedMessageV3.isStringEmpty(this.content_))
        size += GeneratedMessageV3.computeStringSize(4, this.content_); 
      if (this.displayDuration_ != 0L)
        size += 
          CodedOutputStream.computeUInt64Size(5, this.displayDuration_); 
      if (this.sortRank_ != 0L)
        size += 
          CodedOutputStream.computeUInt64Size(6, this.sortRank_); 
      if (this.displayType_ != DisplayType.UNKNOWN_DISPLAY_TYPE.getNumber())
        size += 
          CodedOutputStream.computeEnumSize(7, this.displayType_); 
      size += getUnknownFields().getSerializedSize();
      this.memoizedSize = size;
      return size;
    }
    
    public boolean equals(Object obj) {
      if (obj == this)
        return true; 
      if (!(obj instanceof WebSystemNoticeFeed))
        return super.equals(obj); 
      WebSystemNoticeFeed other = (WebSystemNoticeFeed)obj;
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
      if (!getContent().equals(other.getContent()))
        return false; 
      if (getDisplayDuration() != other
        .getDisplayDuration())
        return false; 
      if (getSortRank() != other
        .getSortRank())
        return false; 
      if (this.displayType_ != other.displayType_)
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
      hash = 53 * hash + getContent().hashCode();
      hash = 37 * hash + 5;
      hash = 53 * hash + Internal.hashLong(
          getDisplayDuration());
      hash = 37 * hash + 6;
      hash = 53 * hash + Internal.hashLong(
          getSortRank());
      hash = 37 * hash + 7;
      hash = 53 * hash + this.displayType_;
      hash = 29 * hash + getUnknownFields().hashCode();
      this.memoizedHashCode = hash;
      return hash;
    }
    
    public static WebSystemNoticeFeed parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (WebSystemNoticeFeed)PARSER.parseFrom(data);
    }
    
    public static WebSystemNoticeFeed parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (WebSystemNoticeFeed)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static WebSystemNoticeFeed parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (WebSystemNoticeFeed)PARSER.parseFrom(data);
    }
    
    public static WebSystemNoticeFeed parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (WebSystemNoticeFeed)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static WebSystemNoticeFeed parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (WebSystemNoticeFeed)PARSER.parseFrom(data);
    }
    
    public static WebSystemNoticeFeed parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (WebSystemNoticeFeed)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static WebSystemNoticeFeed parseFrom(InputStream input) throws IOException {
      return 
        (WebSystemNoticeFeed)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static WebSystemNoticeFeed parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (WebSystemNoticeFeed)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static WebSystemNoticeFeed parseDelimitedFrom(InputStream input) throws IOException {
      return 
        (WebSystemNoticeFeed)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }
    
    public static WebSystemNoticeFeed parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (WebSystemNoticeFeed)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static WebSystemNoticeFeed parseFrom(CodedInputStream input) throws IOException {
      return 
        (WebSystemNoticeFeed)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static WebSystemNoticeFeed parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (WebSystemNoticeFeed)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public Builder newBuilderForType() {
      return newBuilder();
    }
    
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    
    public static Builder newBuilder(WebSystemNoticeFeed prototype) {
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
    
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements WebSystemNoticeFeedOuterClass.WebSystemNoticeFeedOrBuilder {
      private int bitField0_;
      
      private Object id_;
      
      private SimpleUserInfoOuterClass.SimpleUserInfo user_;
      
      private SingleFieldBuilderV3<SimpleUserInfoOuterClass.SimpleUserInfo, SimpleUserInfoOuterClass.SimpleUserInfo.Builder, SimpleUserInfoOuterClass.SimpleUserInfoOrBuilder> userBuilder_;
      
      private long time_;
      
      private Object content_;
      
      private long displayDuration_;
      
      private long sortRank_;
      
      private int displayType_;
      
      public static final Descriptors.Descriptor getDescriptor() {
        return WebSystemNoticeFeedOuterClass.internal_static_WebSystemNoticeFeed_descriptor;
      }
      
      protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return WebSystemNoticeFeedOuterClass.internal_static_WebSystemNoticeFeed_fieldAccessorTable
          .ensureFieldAccessorsInitialized(WebSystemNoticeFeedOuterClass.WebSystemNoticeFeed.class, Builder.class);
      }
      
      private Builder() {
        this.id_ = "";
        this.content_ = "";
        this.displayType_ = 0;
        maybeForceBuilderInitialization();
      }
      
      private Builder(GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        this.id_ = "";
        this.content_ = "";
        this.displayType_ = 0;
        maybeForceBuilderInitialization();
      }
      
      private void maybeForceBuilderInitialization() {
        if (WebSystemNoticeFeedOuterClass.WebSystemNoticeFeed.alwaysUseFieldBuilders)
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
        this.content_ = "";
        this.displayDuration_ = 0L;
        this.sortRank_ = 0L;
        this.displayType_ = 0;
        return this;
      }
      
      public Descriptors.Descriptor getDescriptorForType() {
        return WebSystemNoticeFeedOuterClass.internal_static_WebSystemNoticeFeed_descriptor;
      }
      
      public WebSystemNoticeFeedOuterClass.WebSystemNoticeFeed getDefaultInstanceForType() {
        return WebSystemNoticeFeedOuterClass.WebSystemNoticeFeed.getDefaultInstance();
      }
      
      public WebSystemNoticeFeedOuterClass.WebSystemNoticeFeed build() {
        WebSystemNoticeFeedOuterClass.WebSystemNoticeFeed result = buildPartial();
        if (!result.isInitialized())
          throw newUninitializedMessageException(result); 
        return result;
      }
      
      public WebSystemNoticeFeedOuterClass.WebSystemNoticeFeed buildPartial() {
        WebSystemNoticeFeedOuterClass.WebSystemNoticeFeed result = new WebSystemNoticeFeedOuterClass.WebSystemNoticeFeed(this);
        if (this.bitField0_ != 0)
          buildPartial0(result); 
        onBuilt();
        return result;
      }
      
      private void buildPartial0(WebSystemNoticeFeedOuterClass.WebSystemNoticeFeed result) {
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
          result.content_ = this.content_; 
        if ((from_bitField0_ & 0x10) != 0)
          result.displayDuration_ = this.displayDuration_; 
        if ((from_bitField0_ & 0x20) != 0)
          result.sortRank_ = this.sortRank_; 
        if ((from_bitField0_ & 0x40) != 0)
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
        if (other instanceof WebSystemNoticeFeedOuterClass.WebSystemNoticeFeed)
          return mergeFrom((WebSystemNoticeFeedOuterClass.WebSystemNoticeFeed)other); 
        super.mergeFrom(other);
        return this;
      }
      
      public Builder mergeFrom(WebSystemNoticeFeedOuterClass.WebSystemNoticeFeed other) {
        if (other == WebSystemNoticeFeedOuterClass.WebSystemNoticeFeed.getDefaultInstance())
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
        if (!other.getContent().isEmpty()) {
          this.content_ = other.content_;
          this.bitField0_ |= 0x8;
          onChanged();
        } 
        if (other.getDisplayDuration() != 0L)
          setDisplayDuration(other.getDisplayDuration()); 
        if (other.getSortRank() != 0L)
          setSortRank(other.getSortRank()); 
        if (other.displayType_ != 0)
          setDisplayTypeValue(other.getDisplayTypeValue()); 
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
              case 34:
                this.content_ = input.readStringRequireUtf8();
                this.bitField0_ |= 0x8;
                continue;
              case 40:
                this.displayDuration_ = input.readUInt64();
                this.bitField0_ |= 0x10;
                continue;
              case 48:
                this.sortRank_ = input.readUInt64();
                this.bitField0_ |= 0x20;
                continue;
              case 56:
                this.displayType_ = input.readEnum();
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
        this.id_ = WebSystemNoticeFeedOuterClass.WebSystemNoticeFeed.getDefaultInstance().getId();
        this.bitField0_ &= 0xFFFFFFFE;
        onChanged();
        return this;
      }
      
      public Builder setIdBytes(ByteString value) {
        if (value == null)
          throw new NullPointerException(); 
        WebSystemNoticeFeedOuterClass.WebSystemNoticeFeed.checkByteStringIsUtf8(value);
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
        this.bitField0_ |= 0x8;
        onChanged();
        return this;
      }
      
      public Builder clearContent() {
        this.content_ = WebSystemNoticeFeedOuterClass.WebSystemNoticeFeed.getDefaultInstance().getContent();
        this.bitField0_ &= 0xFFFFFFF7;
        onChanged();
        return this;
      }
      
      public Builder setContentBytes(ByteString value) {
        if (value == null)
          throw new NullPointerException(); 
        WebSystemNoticeFeedOuterClass.WebSystemNoticeFeed.checkByteStringIsUtf8(value);
        this.content_ = value;
        this.bitField0_ |= 0x8;
        onChanged();
        return this;
      }
      
      public long getDisplayDuration() {
        return this.displayDuration_;
      }
      
      public Builder setDisplayDuration(long value) {
        this.displayDuration_ = value;
        this.bitField0_ |= 0x10;
        onChanged();
        return this;
      }
      
      public Builder clearDisplayDuration() {
        this.bitField0_ &= 0xFFFFFFEF;
        this.displayDuration_ = 0L;
        onChanged();
        return this;
      }
      
      public long getSortRank() {
        return this.sortRank_;
      }
      
      public Builder setSortRank(long value) {
        this.sortRank_ = value;
        this.bitField0_ |= 0x20;
        onChanged();
        return this;
      }
      
      public Builder clearSortRank() {
        this.bitField0_ &= 0xFFFFFFDF;
        this.sortRank_ = 0L;
        onChanged();
        return this;
      }
      
      public int getDisplayTypeValue() {
        return this.displayType_;
      }
      
      public Builder setDisplayTypeValue(int value) {
        this.displayType_ = value;
        this.bitField0_ |= 0x40;
        onChanged();
        return this;
      }
      
      public WebSystemNoticeFeedOuterClass.WebSystemNoticeFeed.DisplayType getDisplayType() {
        WebSystemNoticeFeedOuterClass.WebSystemNoticeFeed.DisplayType result = WebSystemNoticeFeedOuterClass.WebSystemNoticeFeed.DisplayType.forNumber(this.displayType_);
        return (result == null) ? WebSystemNoticeFeedOuterClass.WebSystemNoticeFeed.DisplayType.UNRECOGNIZED : result;
      }
      
      public Builder setDisplayType(WebSystemNoticeFeedOuterClass.WebSystemNoticeFeed.DisplayType value) {
        if (value == null)
          throw new NullPointerException(); 
        this.bitField0_ |= 0x40;
        this.displayType_ = value.getNumber();
        onChanged();
        return this;
      }
      
      public Builder clearDisplayType() {
        this.bitField0_ &= 0xFFFFFFBF;
        this.displayType_ = 0;
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
    
    private static final WebSystemNoticeFeed DEFAULT_INSTANCE = new WebSystemNoticeFeed();
    
    public static WebSystemNoticeFeed getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }
    
    private static final Parser<WebSystemNoticeFeed> PARSER = (Parser<WebSystemNoticeFeed>)new AbstractParser<WebSystemNoticeFeed>() {
        public WebSystemNoticeFeedOuterClass.WebSystemNoticeFeed parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
          WebSystemNoticeFeedOuterClass.WebSystemNoticeFeed.Builder builder = WebSystemNoticeFeedOuterClass.WebSystemNoticeFeed.newBuilder();
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
    
    public static Parser<WebSystemNoticeFeed> parser() {
      return PARSER;
    }
    
    public Parser<WebSystemNoticeFeed> getParserForType() {
      return PARSER;
    }
    
    public WebSystemNoticeFeed getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }
  }
  
  public static Descriptors.FileDescriptor getDescriptor() {
    return descriptor;
  }
  
  static {
    String[] descriptorData = { "\n\031WebSystemNoticeFeed.proto\032\024SimpleUserInfo.proto\"\002\n\023WebSystemNoticeFeed\022\n\n\002id\030\001 \001(\t\022\035\n\004user\030\002 \001(\0132\017.SimpleUserInfo\022\f\n\004time\030\003 \001(\004\022\017\n\007content\030\004 \001(\t\022\027\n\017displayDuration\030\005 \001(\004\022\020\n\bsortRank\030\006 \001(\004\0225\n\013displayType\030\007 \001(\0162 .WebSystemNoticeFeed.DisplayType\"J\n\013DisplayType\022\030\n\024UNKNOWN_DISPLAY_TYPE\020\000\022\013\n\007COMMENT\020\001\022\t\n\005ALERT\020\002\022\t\n\005TOAST\020\003B<\n:tech.ordinaryroad.live.chat.client.codec.kuaishou.protobufb\006proto3" };
    descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[] { SimpleUserInfoOuterClass.getDescriptor() });
    internal_static_WebSystemNoticeFeed_descriptor = getDescriptor().getMessageTypes().get(0);
    internal_static_WebSystemNoticeFeed_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_WebSystemNoticeFeed_descriptor, new String[] { "Id", "User", "Time", "Content", "DisplayDuration", "SortRank", "DisplayType" });
    SimpleUserInfoOuterClass.getDescriptor();
  }
  
  public static interface WebSystemNoticeFeedOrBuilder extends MessageOrBuilder {
    String getId();
    
    ByteString getIdBytes();
    
    boolean hasUser();
    
    SimpleUserInfoOuterClass.SimpleUserInfo getUser();
    
    SimpleUserInfoOuterClass.SimpleUserInfoOrBuilder getUserOrBuilder();
    
    long getTime();
    
    String getContent();
    
    ByteString getContentBytes();
    
    long getDisplayDuration();
    
    long getSortRank();
    
    int getDisplayTypeValue();
    
    WebSystemNoticeFeedOuterClass.WebSystemNoticeFeed.DisplayType getDisplayType();
  }
}
