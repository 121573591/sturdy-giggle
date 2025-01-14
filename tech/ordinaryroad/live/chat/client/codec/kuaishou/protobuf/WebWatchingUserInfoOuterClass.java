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

public final class WebWatchingUserInfoOuterClass {
  private static final Descriptors.Descriptor internal_static_WebWatchingUserInfo_descriptor;
  
  private static final GeneratedMessageV3.FieldAccessorTable internal_static_WebWatchingUserInfo_fieldAccessorTable;
  
  private static Descriptors.FileDescriptor descriptor;
  
  public static void registerAllExtensions(ExtensionRegistryLite registry) {}
  
  public static void registerAllExtensions(ExtensionRegistry registry) {
    registerAllExtensions((ExtensionRegistryLite)registry);
  }
  
  public static final class WebWatchingUserInfo extends GeneratedMessageV3 implements WebWatchingUserInfoOrBuilder {
    private static final long serialVersionUID = 0L;
    
    private int bitField0_;
    
    public static final int USER_FIELD_NUMBER = 1;
    
    private SimpleUserInfoOuterClass.SimpleUserInfo user_;
    
    public static final int OFFLINE_FIELD_NUMBER = 2;
    
    private boolean offline_;
    
    public static final int TUHAO_FIELD_NUMBER = 3;
    
    private boolean tuhao_;
    
    public static final int LIVEASSISTANTTYPE_FIELD_NUMBER = 4;
    
    private int liveAssistantType_;
    
    public static final int DISPLAYKSCOIN_FIELD_NUMBER = 5;
    
    private volatile Object displayKsCoin_;
    
    private byte memoizedIsInitialized;
    
    private WebWatchingUserInfo(GeneratedMessageV3.Builder<?> builder) {
      super(builder);
      this.offline_ = false;
      this.tuhao_ = false;
      this.liveAssistantType_ = 0;
      this.displayKsCoin_ = "";
      this.memoizedIsInitialized = -1;
    }
    
    private WebWatchingUserInfo() {
      this.offline_ = false;
      this.tuhao_ = false;
      this.liveAssistantType_ = 0;
      this.displayKsCoin_ = "";
      this.memoizedIsInitialized = -1;
      this.liveAssistantType_ = 0;
      this.displayKsCoin_ = "";
    }
    
    protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
      return new WebWatchingUserInfo();
    }
    
    public static final Descriptors.Descriptor getDescriptor() {
      return WebWatchingUserInfoOuterClass.internal_static_WebWatchingUserInfo_descriptor;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return WebWatchingUserInfoOuterClass.internal_static_WebWatchingUserInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(WebWatchingUserInfo.class, Builder.class);
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
    
    public boolean getOffline() {
      return this.offline_;
    }
    
    public boolean getTuhao() {
      return this.tuhao_;
    }
    
    public int getLiveAssistantTypeValue() {
      return this.liveAssistantType_;
    }
    
    public WebLiveAssistantTypeOuterClass.WebLiveAssistantType getLiveAssistantType() {
      WebLiveAssistantTypeOuterClass.WebLiveAssistantType result = WebLiveAssistantTypeOuterClass.WebLiveAssistantType.forNumber(this.liveAssistantType_);
      return (result == null) ? WebLiveAssistantTypeOuterClass.WebLiveAssistantType.UNRECOGNIZED : result;
    }
    
    public String getDisplayKsCoin() {
      Object ref = this.displayKsCoin_;
      if (ref instanceof String)
        return (String)ref; 
      ByteString bs = (ByteString)ref;
      String s = bs.toStringUtf8();
      this.displayKsCoin_ = s;
      return s;
    }
    
    public ByteString getDisplayKsCoinBytes() {
      Object ref = this.displayKsCoin_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.displayKsCoin_ = b;
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
      if ((this.bitField0_ & 0x1) != 0)
        output.writeMessage(1, (MessageLite)getUser()); 
      if (this.offline_)
        output.writeBool(2, this.offline_); 
      if (this.tuhao_)
        output.writeBool(3, this.tuhao_); 
      if (this.liveAssistantType_ != WebLiveAssistantTypeOuterClass.WebLiveAssistantType.UNKNOWN_ASSISTANT_TYPE.getNumber())
        output.writeEnum(4, this.liveAssistantType_); 
      if (!GeneratedMessageV3.isStringEmpty(this.displayKsCoin_))
        GeneratedMessageV3.writeString(output, 5, this.displayKsCoin_); 
      getUnknownFields().writeTo(output);
    }
    
    public int getSerializedSize() {
      int size = this.memoizedSize;
      if (size != -1)
        return size; 
      size = 0;
      if ((this.bitField0_ & 0x1) != 0)
        size += 
          CodedOutputStream.computeMessageSize(1, (MessageLite)getUser()); 
      if (this.offline_)
        size += 
          CodedOutputStream.computeBoolSize(2, this.offline_); 
      if (this.tuhao_)
        size += 
          CodedOutputStream.computeBoolSize(3, this.tuhao_); 
      if (this.liveAssistantType_ != WebLiveAssistantTypeOuterClass.WebLiveAssistantType.UNKNOWN_ASSISTANT_TYPE.getNumber())
        size += 
          CodedOutputStream.computeEnumSize(4, this.liveAssistantType_); 
      if (!GeneratedMessageV3.isStringEmpty(this.displayKsCoin_))
        size += GeneratedMessageV3.computeStringSize(5, this.displayKsCoin_); 
      size += getUnknownFields().getSerializedSize();
      this.memoizedSize = size;
      return size;
    }
    
    public boolean equals(Object obj) {
      if (obj == this)
        return true; 
      if (!(obj instanceof WebWatchingUserInfo))
        return super.equals(obj); 
      WebWatchingUserInfo other = (WebWatchingUserInfo)obj;
      if (hasUser() != other.hasUser())
        return false; 
      if (hasUser() && 
        
        !getUser().equals(other.getUser()))
        return false; 
      if (getOffline() != other
        .getOffline())
        return false; 
      if (getTuhao() != other
        .getTuhao())
        return false; 
      if (this.liveAssistantType_ != other.liveAssistantType_)
        return false; 
      if (!getDisplayKsCoin().equals(other.getDisplayKsCoin()))
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
      if (hasUser()) {
        hash = 37 * hash + 1;
        hash = 53 * hash + getUser().hashCode();
      } 
      hash = 37 * hash + 2;
      hash = 53 * hash + Internal.hashBoolean(
          getOffline());
      hash = 37 * hash + 3;
      hash = 53 * hash + Internal.hashBoolean(
          getTuhao());
      hash = 37 * hash + 4;
      hash = 53 * hash + this.liveAssistantType_;
      hash = 37 * hash + 5;
      hash = 53 * hash + getDisplayKsCoin().hashCode();
      hash = 29 * hash + getUnknownFields().hashCode();
      this.memoizedHashCode = hash;
      return hash;
    }
    
    public static WebWatchingUserInfo parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (WebWatchingUserInfo)PARSER.parseFrom(data);
    }
    
    public static WebWatchingUserInfo parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (WebWatchingUserInfo)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static WebWatchingUserInfo parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (WebWatchingUserInfo)PARSER.parseFrom(data);
    }
    
    public static WebWatchingUserInfo parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (WebWatchingUserInfo)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static WebWatchingUserInfo parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (WebWatchingUserInfo)PARSER.parseFrom(data);
    }
    
    public static WebWatchingUserInfo parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (WebWatchingUserInfo)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static WebWatchingUserInfo parseFrom(InputStream input) throws IOException {
      return 
        (WebWatchingUserInfo)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static WebWatchingUserInfo parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (WebWatchingUserInfo)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static WebWatchingUserInfo parseDelimitedFrom(InputStream input) throws IOException {
      return 
        (WebWatchingUserInfo)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }
    
    public static WebWatchingUserInfo parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (WebWatchingUserInfo)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static WebWatchingUserInfo parseFrom(CodedInputStream input) throws IOException {
      return 
        (WebWatchingUserInfo)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static WebWatchingUserInfo parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (WebWatchingUserInfo)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public Builder newBuilderForType() {
      return newBuilder();
    }
    
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    
    public static Builder newBuilder(WebWatchingUserInfo prototype) {
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
    
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements WebWatchingUserInfoOuterClass.WebWatchingUserInfoOrBuilder {
      private int bitField0_;
      
      private SimpleUserInfoOuterClass.SimpleUserInfo user_;
      
      private SingleFieldBuilderV3<SimpleUserInfoOuterClass.SimpleUserInfo, SimpleUserInfoOuterClass.SimpleUserInfo.Builder, SimpleUserInfoOuterClass.SimpleUserInfoOrBuilder> userBuilder_;
      
      private boolean offline_;
      
      private boolean tuhao_;
      
      private int liveAssistantType_;
      
      private Object displayKsCoin_;
      
      public static final Descriptors.Descriptor getDescriptor() {
        return WebWatchingUserInfoOuterClass.internal_static_WebWatchingUserInfo_descriptor;
      }
      
      protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return WebWatchingUserInfoOuterClass.internal_static_WebWatchingUserInfo_fieldAccessorTable
          .ensureFieldAccessorsInitialized(WebWatchingUserInfoOuterClass.WebWatchingUserInfo.class, Builder.class);
      }
      
      private Builder() {
        this.liveAssistantType_ = 0;
        this.displayKsCoin_ = "";
        maybeForceBuilderInitialization();
      }
      
      private Builder(GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        this.liveAssistantType_ = 0;
        this.displayKsCoin_ = "";
        maybeForceBuilderInitialization();
      }
      
      private void maybeForceBuilderInitialization() {
        if (WebWatchingUserInfoOuterClass.WebWatchingUserInfo.alwaysUseFieldBuilders)
          getUserFieldBuilder(); 
      }
      
      public Builder clear() {
        super.clear();
        this.bitField0_ = 0;
        this.user_ = null;
        if (this.userBuilder_ != null) {
          this.userBuilder_.dispose();
          this.userBuilder_ = null;
        } 
        this.offline_ = false;
        this.tuhao_ = false;
        this.liveAssistantType_ = 0;
        this.displayKsCoin_ = "";
        return this;
      }
      
      public Descriptors.Descriptor getDescriptorForType() {
        return WebWatchingUserInfoOuterClass.internal_static_WebWatchingUserInfo_descriptor;
      }
      
      public WebWatchingUserInfoOuterClass.WebWatchingUserInfo getDefaultInstanceForType() {
        return WebWatchingUserInfoOuterClass.WebWatchingUserInfo.getDefaultInstance();
      }
      
      public WebWatchingUserInfoOuterClass.WebWatchingUserInfo build() {
        WebWatchingUserInfoOuterClass.WebWatchingUserInfo result = buildPartial();
        if (!result.isInitialized())
          throw newUninitializedMessageException(result); 
        return result;
      }
      
      public WebWatchingUserInfoOuterClass.WebWatchingUserInfo buildPartial() {
        WebWatchingUserInfoOuterClass.WebWatchingUserInfo result = new WebWatchingUserInfoOuterClass.WebWatchingUserInfo(this);
        if (this.bitField0_ != 0)
          buildPartial0(result); 
        onBuilt();
        return result;
      }
      
      private void buildPartial0(WebWatchingUserInfoOuterClass.WebWatchingUserInfo result) {
        int from_bitField0_ = this.bitField0_;
        int to_bitField0_ = 0;
        if ((from_bitField0_ & 0x1) != 0) {
          result.user_ = (this.userBuilder_ == null) ? this.user_ : (SimpleUserInfoOuterClass.SimpleUserInfo)this.userBuilder_.build();
          to_bitField0_ |= 0x1;
        } 
        if ((from_bitField0_ & 0x2) != 0)
          result.offline_ = this.offline_; 
        if ((from_bitField0_ & 0x4) != 0)
          result.tuhao_ = this.tuhao_; 
        if ((from_bitField0_ & 0x8) != 0)
          result.liveAssistantType_ = this.liveAssistantType_; 
        if ((from_bitField0_ & 0x10) != 0)
          result.displayKsCoin_ = this.displayKsCoin_; 
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
        if (other instanceof WebWatchingUserInfoOuterClass.WebWatchingUserInfo)
          return mergeFrom((WebWatchingUserInfoOuterClass.WebWatchingUserInfo)other); 
        super.mergeFrom(other);
        return this;
      }
      
      public Builder mergeFrom(WebWatchingUserInfoOuterClass.WebWatchingUserInfo other) {
        if (other == WebWatchingUserInfoOuterClass.WebWatchingUserInfo.getDefaultInstance())
          return this; 
        if (other.hasUser())
          mergeUser(other.getUser()); 
        if (other.getOffline())
          setOffline(other.getOffline()); 
        if (other.getTuhao())
          setTuhao(other.getTuhao()); 
        if (other.liveAssistantType_ != 0)
          setLiveAssistantTypeValue(other.getLiveAssistantTypeValue()); 
        if (!other.getDisplayKsCoin().isEmpty()) {
          this.displayKsCoin_ = other.displayKsCoin_;
          this.bitField0_ |= 0x10;
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
                input.readMessage((MessageLite.Builder)getUserFieldBuilder().getBuilder(), extensionRegistry);
                this.bitField0_ |= 0x1;
                continue;
              case 16:
                this.offline_ = input.readBool();
                this.bitField0_ |= 0x2;
                continue;
              case 24:
                this.tuhao_ = input.readBool();
                this.bitField0_ |= 0x4;
                continue;
              case 32:
                this.liveAssistantType_ = input.readEnum();
                this.bitField0_ |= 0x8;
                continue;
              case 42:
                this.displayKsCoin_ = input.readStringRequireUtf8();
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
      
      public boolean hasUser() {
        return ((this.bitField0_ & 0x1) != 0);
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
        this.bitField0_ |= 0x1;
        onChanged();
        return this;
      }
      
      public Builder setUser(SimpleUserInfoOuterClass.SimpleUserInfo.Builder builderForValue) {
        if (this.userBuilder_ == null) {
          this.user_ = builderForValue.build();
        } else {
          this.userBuilder_.setMessage((AbstractMessage)builderForValue.build());
        } 
        this.bitField0_ |= 0x1;
        onChanged();
        return this;
      }
      
      public Builder mergeUser(SimpleUserInfoOuterClass.SimpleUserInfo value) {
        if (this.userBuilder_ == null) {
          if ((this.bitField0_ & 0x1) != 0 && this.user_ != null && this.user_ != SimpleUserInfoOuterClass.SimpleUserInfo.getDefaultInstance()) {
            getUserBuilder().mergeFrom(value);
          } else {
            this.user_ = value;
          } 
        } else {
          this.userBuilder_.mergeFrom((AbstractMessage)value);
        } 
        if (this.user_ != null) {
          this.bitField0_ |= 0x1;
          onChanged();
        } 
        return this;
      }
      
      public Builder clearUser() {
        this.bitField0_ &= 0xFFFFFFFE;
        this.user_ = null;
        if (this.userBuilder_ != null) {
          this.userBuilder_.dispose();
          this.userBuilder_ = null;
        } 
        onChanged();
        return this;
      }
      
      public SimpleUserInfoOuterClass.SimpleUserInfo.Builder getUserBuilder() {
        this.bitField0_ |= 0x1;
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
      
      public boolean getOffline() {
        return this.offline_;
      }
      
      public Builder setOffline(boolean value) {
        this.offline_ = value;
        this.bitField0_ |= 0x2;
        onChanged();
        return this;
      }
      
      public Builder clearOffline() {
        this.bitField0_ &= 0xFFFFFFFD;
        this.offline_ = false;
        onChanged();
        return this;
      }
      
      public boolean getTuhao() {
        return this.tuhao_;
      }
      
      public Builder setTuhao(boolean value) {
        this.tuhao_ = value;
        this.bitField0_ |= 0x4;
        onChanged();
        return this;
      }
      
      public Builder clearTuhao() {
        this.bitField0_ &= 0xFFFFFFFB;
        this.tuhao_ = false;
        onChanged();
        return this;
      }
      
      public int getLiveAssistantTypeValue() {
        return this.liveAssistantType_;
      }
      
      public Builder setLiveAssistantTypeValue(int value) {
        this.liveAssistantType_ = value;
        this.bitField0_ |= 0x8;
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
        this.bitField0_ |= 0x8;
        this.liveAssistantType_ = value.getNumber();
        onChanged();
        return this;
      }
      
      public Builder clearLiveAssistantType() {
        this.bitField0_ &= 0xFFFFFFF7;
        this.liveAssistantType_ = 0;
        onChanged();
        return this;
      }
      
      public String getDisplayKsCoin() {
        Object ref = this.displayKsCoin_;
        if (!(ref instanceof String)) {
          ByteString bs = (ByteString)ref;
          String s = bs.toStringUtf8();
          this.displayKsCoin_ = s;
          return s;
        } 
        return (String)ref;
      }
      
      public ByteString getDisplayKsCoinBytes() {
        Object ref = this.displayKsCoin_;
        if (ref instanceof String) {
          ByteString b = ByteString.copyFromUtf8((String)ref);
          this.displayKsCoin_ = b;
          return b;
        } 
        return (ByteString)ref;
      }
      
      public Builder setDisplayKsCoin(String value) {
        if (value == null)
          throw new NullPointerException(); 
        this.displayKsCoin_ = value;
        this.bitField0_ |= 0x10;
        onChanged();
        return this;
      }
      
      public Builder clearDisplayKsCoin() {
        this.displayKsCoin_ = WebWatchingUserInfoOuterClass.WebWatchingUserInfo.getDefaultInstance().getDisplayKsCoin();
        this.bitField0_ &= 0xFFFFFFEF;
        onChanged();
        return this;
      }
      
      public Builder setDisplayKsCoinBytes(ByteString value) {
        if (value == null)
          throw new NullPointerException(); 
        WebWatchingUserInfoOuterClass.WebWatchingUserInfo.checkByteStringIsUtf8(value);
        this.displayKsCoin_ = value;
        this.bitField0_ |= 0x10;
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
    
    private static final WebWatchingUserInfo DEFAULT_INSTANCE = new WebWatchingUserInfo();
    
    public static WebWatchingUserInfo getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }
    
    private static final Parser<WebWatchingUserInfo> PARSER = (Parser<WebWatchingUserInfo>)new AbstractParser<WebWatchingUserInfo>() {
        public WebWatchingUserInfoOuterClass.WebWatchingUserInfo parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
          WebWatchingUserInfoOuterClass.WebWatchingUserInfo.Builder builder = WebWatchingUserInfoOuterClass.WebWatchingUserInfo.newBuilder();
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
    
    public static Parser<WebWatchingUserInfo> parser() {
      return PARSER;
    }
    
    public Parser<WebWatchingUserInfo> getParserForType() {
      return PARSER;
    }
    
    public WebWatchingUserInfo getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }
  }
  
  public static Descriptors.FileDescriptor getDescriptor() {
    return descriptor;
  }
  
  static {
    String[] descriptorData = { "\n\031WebWatchingUserInfo.proto\032\024SimpleUserInfo.proto\032\032WebLiveAssistantType.proto\"\001\n\023WebWatchingUserInfo\022\035\n\004user\030\001 \001(\0132\017.SimpleUserInfo\022\017\n\007offline\030\002 \001(\b\022\r\n\005tuhao\030\003 \001(\b\0220\n\021liveAssistantType\030\004 \001(\0162\025.WebLiveAssistantType\022\025\n\rdisplayKsCoin\030\005 \001(\tB<\n:tech.ordinaryroad.live.chat.client.codec.kuaishou.protobufb\006proto3" };
    descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[] { SimpleUserInfoOuterClass.getDescriptor(), 
          WebLiveAssistantTypeOuterClass.getDescriptor() });
    internal_static_WebWatchingUserInfo_descriptor = getDescriptor().getMessageTypes().get(0);
    internal_static_WebWatchingUserInfo_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_WebWatchingUserInfo_descriptor, new String[] { "User", "Offline", "Tuhao", "LiveAssistantType", "DisplayKsCoin" });
    SimpleUserInfoOuterClass.getDescriptor();
    WebLiveAssistantTypeOuterClass.getDescriptor();
  }
  
  public static interface WebWatchingUserInfoOrBuilder extends MessageOrBuilder {
    boolean hasUser();
    
    SimpleUserInfoOuterClass.SimpleUserInfo getUser();
    
    SimpleUserInfoOuterClass.SimpleUserInfoOrBuilder getUserOrBuilder();
    
    boolean getOffline();
    
    boolean getTuhao();
    
    int getLiveAssistantTypeValue();
    
    WebLiveAssistantTypeOuterClass.WebLiveAssistantType getLiveAssistantType();
    
    String getDisplayKsCoin();
    
    ByteString getDisplayKsCoinBytes();
  }
}
