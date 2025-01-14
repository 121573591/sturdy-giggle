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
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class UserInfoOuterClass {
  private static final Descriptors.Descriptor internal_static_UserInfo_descriptor;
  
  private static final GeneratedMessageV3.FieldAccessorTable internal_static_UserInfo_fieldAccessorTable;
  
  private static Descriptors.FileDescriptor descriptor;
  
  public static void registerAllExtensions(ExtensionRegistryLite registry) {}
  
  public static void registerAllExtensions(ExtensionRegistry registry) {
    registerAllExtensions((ExtensionRegistryLite)registry);
  }
  
  public static final class UserInfo extends GeneratedMessageV3 implements UserInfoOrBuilder {
    private static final long serialVersionUID = 0L;
    
    public static final int USERID_FIELD_NUMBER = 1;
    
    private long userId_;
    
    public static final int USERNAME_FIELD_NUMBER = 2;
    
    private volatile Object userName_;
    
    public static final int USERGENDER_FIELD_NUMBER = 3;
    
    private volatile Object userGender_;
    
    public static final int USERTEXT_FIELD_NUMBER = 4;
    
    private volatile Object userText_;
    
    public static final int HEADURLS_FIELD_NUMBER = 5;
    
    private List<PicUrlOuterClass.PicUrl> headUrls_;
    
    public static final int VERIFIED_FIELD_NUMBER = 6;
    
    private boolean verified_;
    
    public static final int SUSERID_FIELD_NUMBER = 7;
    
    private volatile Object sUserId_;
    
    public static final int HTTPSHEADURLS_FIELD_NUMBER = 8;
    
    private List<PicUrlOuterClass.PicUrl> httpsHeadUrls_;
    
    public static final int KWAIID_FIELD_NUMBER = 9;
    
    private volatile Object kwaiId_;
    
    private byte memoizedIsInitialized;
    
    private UserInfo(GeneratedMessageV3.Builder<?> builder) {
      super(builder);
      this.userId_ = 0L;
      this.userName_ = "";
      this.userGender_ = "";
      this.userText_ = "";
      this.verified_ = false;
      this.sUserId_ = "";
      this.kwaiId_ = "";
      this.memoizedIsInitialized = -1;
    }
    
    private UserInfo() {
      this.userId_ = 0L;
      this.userName_ = "";
      this.userGender_ = "";
      this.userText_ = "";
      this.verified_ = false;
      this.sUserId_ = "";
      this.kwaiId_ = "";
      this.memoizedIsInitialized = -1;
      this.userName_ = "";
      this.userGender_ = "";
      this.userText_ = "";
      this.headUrls_ = Collections.emptyList();
      this.sUserId_ = "";
      this.httpsHeadUrls_ = Collections.emptyList();
      this.kwaiId_ = "";
    }
    
    protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
      return new UserInfo();
    }
    
    public static final Descriptors.Descriptor getDescriptor() {
      return UserInfoOuterClass.internal_static_UserInfo_descriptor;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return UserInfoOuterClass.internal_static_UserInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(UserInfo.class, Builder.class);
    }
    
    public long getUserId() {
      return this.userId_;
    }
    
    public String getUserName() {
      Object ref = this.userName_;
      if (ref instanceof String)
        return (String)ref; 
      ByteString bs = (ByteString)ref;
      String s = bs.toStringUtf8();
      this.userName_ = s;
      return s;
    }
    
    public ByteString getUserNameBytes() {
      Object ref = this.userName_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.userName_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public String getUserGender() {
      Object ref = this.userGender_;
      if (ref instanceof String)
        return (String)ref; 
      ByteString bs = (ByteString)ref;
      String s = bs.toStringUtf8();
      this.userGender_ = s;
      return s;
    }
    
    public ByteString getUserGenderBytes() {
      Object ref = this.userGender_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.userGender_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public String getUserText() {
      Object ref = this.userText_;
      if (ref instanceof String)
        return (String)ref; 
      ByteString bs = (ByteString)ref;
      String s = bs.toStringUtf8();
      this.userText_ = s;
      return s;
    }
    
    public ByteString getUserTextBytes() {
      Object ref = this.userText_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.userText_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public List<PicUrlOuterClass.PicUrl> getHeadUrlsList() {
      return this.headUrls_;
    }
    
    public List<? extends PicUrlOuterClass.PicUrlOrBuilder> getHeadUrlsOrBuilderList() {
      return (List)this.headUrls_;
    }
    
    public int getHeadUrlsCount() {
      return this.headUrls_.size();
    }
    
    public PicUrlOuterClass.PicUrl getHeadUrls(int index) {
      return this.headUrls_.get(index);
    }
    
    public PicUrlOuterClass.PicUrlOrBuilder getHeadUrlsOrBuilder(int index) {
      return this.headUrls_.get(index);
    }
    
    public boolean getVerified() {
      return this.verified_;
    }
    
    public String getSUserId() {
      Object ref = this.sUserId_;
      if (ref instanceof String)
        return (String)ref; 
      ByteString bs = (ByteString)ref;
      String s = bs.toStringUtf8();
      this.sUserId_ = s;
      return s;
    }
    
    public ByteString getSUserIdBytes() {
      Object ref = this.sUserId_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.sUserId_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public List<PicUrlOuterClass.PicUrl> getHttpsHeadUrlsList() {
      return this.httpsHeadUrls_;
    }
    
    public List<? extends PicUrlOuterClass.PicUrlOrBuilder> getHttpsHeadUrlsOrBuilderList() {
      return (List)this.httpsHeadUrls_;
    }
    
    public int getHttpsHeadUrlsCount() {
      return this.httpsHeadUrls_.size();
    }
    
    public PicUrlOuterClass.PicUrl getHttpsHeadUrls(int index) {
      return this.httpsHeadUrls_.get(index);
    }
    
    public PicUrlOuterClass.PicUrlOrBuilder getHttpsHeadUrlsOrBuilder(int index) {
      return this.httpsHeadUrls_.get(index);
    }
    
    public String getKwaiId() {
      Object ref = this.kwaiId_;
      if (ref instanceof String)
        return (String)ref; 
      ByteString bs = (ByteString)ref;
      String s = bs.toStringUtf8();
      this.kwaiId_ = s;
      return s;
    }
    
    public ByteString getKwaiIdBytes() {
      Object ref = this.kwaiId_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.kwaiId_ = b;
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
      if (this.userId_ != 0L)
        output.writeUInt64(1, this.userId_); 
      if (!GeneratedMessageV3.isStringEmpty(this.userName_))
        GeneratedMessageV3.writeString(output, 2, this.userName_); 
      if (!GeneratedMessageV3.isStringEmpty(this.userGender_))
        GeneratedMessageV3.writeString(output, 3, this.userGender_); 
      if (!GeneratedMessageV3.isStringEmpty(this.userText_))
        GeneratedMessageV3.writeString(output, 4, this.userText_); 
      int i;
      for (i = 0; i < this.headUrls_.size(); i++)
        output.writeMessage(5, (MessageLite)this.headUrls_.get(i)); 
      if (this.verified_)
        output.writeBool(6, this.verified_); 
      if (!GeneratedMessageV3.isStringEmpty(this.sUserId_))
        GeneratedMessageV3.writeString(output, 7, this.sUserId_); 
      for (i = 0; i < this.httpsHeadUrls_.size(); i++)
        output.writeMessage(8, (MessageLite)this.httpsHeadUrls_.get(i)); 
      if (!GeneratedMessageV3.isStringEmpty(this.kwaiId_))
        GeneratedMessageV3.writeString(output, 9, this.kwaiId_); 
      getUnknownFields().writeTo(output);
    }
    
    public int getSerializedSize() {
      int size = this.memoizedSize;
      if (size != -1)
        return size; 
      size = 0;
      if (this.userId_ != 0L)
        size += 
          CodedOutputStream.computeUInt64Size(1, this.userId_); 
      if (!GeneratedMessageV3.isStringEmpty(this.userName_))
        size += GeneratedMessageV3.computeStringSize(2, this.userName_); 
      if (!GeneratedMessageV3.isStringEmpty(this.userGender_))
        size += GeneratedMessageV3.computeStringSize(3, this.userGender_); 
      if (!GeneratedMessageV3.isStringEmpty(this.userText_))
        size += GeneratedMessageV3.computeStringSize(4, this.userText_); 
      int i;
      for (i = 0; i < this.headUrls_.size(); i++)
        size += 
          CodedOutputStream.computeMessageSize(5, (MessageLite)this.headUrls_.get(i)); 
      if (this.verified_)
        size += 
          CodedOutputStream.computeBoolSize(6, this.verified_); 
      if (!GeneratedMessageV3.isStringEmpty(this.sUserId_))
        size += GeneratedMessageV3.computeStringSize(7, this.sUserId_); 
      for (i = 0; i < this.httpsHeadUrls_.size(); i++)
        size += 
          CodedOutputStream.computeMessageSize(8, (MessageLite)this.httpsHeadUrls_.get(i)); 
      if (!GeneratedMessageV3.isStringEmpty(this.kwaiId_))
        size += GeneratedMessageV3.computeStringSize(9, this.kwaiId_); 
      size += getUnknownFields().getSerializedSize();
      this.memoizedSize = size;
      return size;
    }
    
    public boolean equals(Object obj) {
      if (obj == this)
        return true; 
      if (!(obj instanceof UserInfo))
        return super.equals(obj); 
      UserInfo other = (UserInfo)obj;
      if (getUserId() != other
        .getUserId())
        return false; 
      if (!getUserName().equals(other.getUserName()))
        return false; 
      if (!getUserGender().equals(other.getUserGender()))
        return false; 
      if (!getUserText().equals(other.getUserText()))
        return false; 
      if (!getHeadUrlsList().equals(other.getHeadUrlsList()))
        return false; 
      if (getVerified() != other
        .getVerified())
        return false; 
      if (!getSUserId().equals(other.getSUserId()))
        return false; 
      if (!getHttpsHeadUrlsList().equals(other.getHttpsHeadUrlsList()))
        return false; 
      if (!getKwaiId().equals(other.getKwaiId()))
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
          getUserId());
      hash = 37 * hash + 2;
      hash = 53 * hash + getUserName().hashCode();
      hash = 37 * hash + 3;
      hash = 53 * hash + getUserGender().hashCode();
      hash = 37 * hash + 4;
      hash = 53 * hash + getUserText().hashCode();
      if (getHeadUrlsCount() > 0) {
        hash = 37 * hash + 5;
        hash = 53 * hash + getHeadUrlsList().hashCode();
      } 
      hash = 37 * hash + 6;
      hash = 53 * hash + Internal.hashBoolean(
          getVerified());
      hash = 37 * hash + 7;
      hash = 53 * hash + getSUserId().hashCode();
      if (getHttpsHeadUrlsCount() > 0) {
        hash = 37 * hash + 8;
        hash = 53 * hash + getHttpsHeadUrlsList().hashCode();
      } 
      hash = 37 * hash + 9;
      hash = 53 * hash + getKwaiId().hashCode();
      hash = 29 * hash + getUnknownFields().hashCode();
      this.memoizedHashCode = hash;
      return hash;
    }
    
    public static UserInfo parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (UserInfo)PARSER.parseFrom(data);
    }
    
    public static UserInfo parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (UserInfo)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static UserInfo parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (UserInfo)PARSER.parseFrom(data);
    }
    
    public static UserInfo parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (UserInfo)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static UserInfo parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (UserInfo)PARSER.parseFrom(data);
    }
    
    public static UserInfo parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (UserInfo)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static UserInfo parseFrom(InputStream input) throws IOException {
      return 
        (UserInfo)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static UserInfo parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (UserInfo)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static UserInfo parseDelimitedFrom(InputStream input) throws IOException {
      return 
        (UserInfo)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }
    
    public static UserInfo parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (UserInfo)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static UserInfo parseFrom(CodedInputStream input) throws IOException {
      return 
        (UserInfo)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static UserInfo parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (UserInfo)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public Builder newBuilderForType() {
      return newBuilder();
    }
    
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    
    public static Builder newBuilder(UserInfo prototype) {
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
    
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements UserInfoOuterClass.UserInfoOrBuilder {
      private int bitField0_;
      
      private long userId_;
      
      private Object userName_;
      
      private Object userGender_;
      
      private Object userText_;
      
      private List<PicUrlOuterClass.PicUrl> headUrls_;
      
      private RepeatedFieldBuilderV3<PicUrlOuterClass.PicUrl, PicUrlOuterClass.PicUrl.Builder, PicUrlOuterClass.PicUrlOrBuilder> headUrlsBuilder_;
      
      private boolean verified_;
      
      private Object sUserId_;
      
      private List<PicUrlOuterClass.PicUrl> httpsHeadUrls_;
      
      private RepeatedFieldBuilderV3<PicUrlOuterClass.PicUrl, PicUrlOuterClass.PicUrl.Builder, PicUrlOuterClass.PicUrlOrBuilder> httpsHeadUrlsBuilder_;
      
      private Object kwaiId_;
      
      public static final Descriptors.Descriptor getDescriptor() {
        return UserInfoOuterClass.internal_static_UserInfo_descriptor;
      }
      
      protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return UserInfoOuterClass.internal_static_UserInfo_fieldAccessorTable
          .ensureFieldAccessorsInitialized(UserInfoOuterClass.UserInfo.class, Builder.class);
      }
      
      private Builder() {
        this.userName_ = "";
        this.userGender_ = "";
        this.userText_ = "";
        this
          .headUrls_ = Collections.emptyList();
        this.sUserId_ = "";
        this
          .httpsHeadUrls_ = Collections.emptyList();
        this.kwaiId_ = "";
      }
      
      private Builder(GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        this.userName_ = "";
        this.userGender_ = "";
        this.userText_ = "";
        this.headUrls_ = Collections.emptyList();
        this.sUserId_ = "";
        this.httpsHeadUrls_ = Collections.emptyList();
        this.kwaiId_ = "";
      }
      
      public Builder clear() {
        super.clear();
        this.bitField0_ = 0;
        this.userId_ = 0L;
        this.userName_ = "";
        this.userGender_ = "";
        this.userText_ = "";
        if (this.headUrlsBuilder_ == null) {
          this.headUrls_ = Collections.emptyList();
        } else {
          this.headUrls_ = null;
          this.headUrlsBuilder_.clear();
        } 
        this.bitField0_ &= 0xFFFFFFEF;
        this.verified_ = false;
        this.sUserId_ = "";
        if (this.httpsHeadUrlsBuilder_ == null) {
          this.httpsHeadUrls_ = Collections.emptyList();
        } else {
          this.httpsHeadUrls_ = null;
          this.httpsHeadUrlsBuilder_.clear();
        } 
        this.bitField0_ &= 0xFFFFFF7F;
        this.kwaiId_ = "";
        return this;
      }
      
      public Descriptors.Descriptor getDescriptorForType() {
        return UserInfoOuterClass.internal_static_UserInfo_descriptor;
      }
      
      public UserInfoOuterClass.UserInfo getDefaultInstanceForType() {
        return UserInfoOuterClass.UserInfo.getDefaultInstance();
      }
      
      public UserInfoOuterClass.UserInfo build() {
        UserInfoOuterClass.UserInfo result = buildPartial();
        if (!result.isInitialized())
          throw newUninitializedMessageException(result); 
        return result;
      }
      
      public UserInfoOuterClass.UserInfo buildPartial() {
        UserInfoOuterClass.UserInfo result = new UserInfoOuterClass.UserInfo(this);
        buildPartialRepeatedFields(result);
        if (this.bitField0_ != 0)
          buildPartial0(result); 
        onBuilt();
        return result;
      }
      
      private void buildPartialRepeatedFields(UserInfoOuterClass.UserInfo result) {
        if (this.headUrlsBuilder_ == null) {
          if ((this.bitField0_ & 0x10) != 0) {
            this.headUrls_ = Collections.unmodifiableList(this.headUrls_);
            this.bitField0_ &= 0xFFFFFFEF;
          } 
          result.headUrls_ = this.headUrls_;
        } else {
          result.headUrls_ = this.headUrlsBuilder_.build();
        } 
        if (this.httpsHeadUrlsBuilder_ == null) {
          if ((this.bitField0_ & 0x80) != 0) {
            this.httpsHeadUrls_ = Collections.unmodifiableList(this.httpsHeadUrls_);
            this.bitField0_ &= 0xFFFFFF7F;
          } 
          result.httpsHeadUrls_ = this.httpsHeadUrls_;
        } else {
          result.httpsHeadUrls_ = this.httpsHeadUrlsBuilder_.build();
        } 
      }
      
      private void buildPartial0(UserInfoOuterClass.UserInfo result) {
        int from_bitField0_ = this.bitField0_;
        if ((from_bitField0_ & 0x1) != 0)
          result.userId_ = this.userId_; 
        if ((from_bitField0_ & 0x2) != 0)
          result.userName_ = this.userName_; 
        if ((from_bitField0_ & 0x4) != 0)
          result.userGender_ = this.userGender_; 
        if ((from_bitField0_ & 0x8) != 0)
          result.userText_ = this.userText_; 
        if ((from_bitField0_ & 0x20) != 0)
          result.verified_ = this.verified_; 
        if ((from_bitField0_ & 0x40) != 0)
          result.sUserId_ = this.sUserId_; 
        if ((from_bitField0_ & 0x100) != 0)
          result.kwaiId_ = this.kwaiId_; 
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
        if (other instanceof UserInfoOuterClass.UserInfo)
          return mergeFrom((UserInfoOuterClass.UserInfo)other); 
        super.mergeFrom(other);
        return this;
      }
      
      public Builder mergeFrom(UserInfoOuterClass.UserInfo other) {
        if (other == UserInfoOuterClass.UserInfo.getDefaultInstance())
          return this; 
        if (other.getUserId() != 0L)
          setUserId(other.getUserId()); 
        if (!other.getUserName().isEmpty()) {
          this.userName_ = other.userName_;
          this.bitField0_ |= 0x2;
          onChanged();
        } 
        if (!other.getUserGender().isEmpty()) {
          this.userGender_ = other.userGender_;
          this.bitField0_ |= 0x4;
          onChanged();
        } 
        if (!other.getUserText().isEmpty()) {
          this.userText_ = other.userText_;
          this.bitField0_ |= 0x8;
          onChanged();
        } 
        if (this.headUrlsBuilder_ == null) {
          if (!other.headUrls_.isEmpty()) {
            if (this.headUrls_.isEmpty()) {
              this.headUrls_ = other.headUrls_;
              this.bitField0_ &= 0xFFFFFFEF;
            } else {
              ensureHeadUrlsIsMutable();
              this.headUrls_.addAll(other.headUrls_);
            } 
            onChanged();
          } 
        } else if (!other.headUrls_.isEmpty()) {
          if (this.headUrlsBuilder_.isEmpty()) {
            this.headUrlsBuilder_.dispose();
            this.headUrlsBuilder_ = null;
            this.headUrls_ = other.headUrls_;
            this.bitField0_ &= 0xFFFFFFEF;
            this.headUrlsBuilder_ = UserInfoOuterClass.UserInfo.alwaysUseFieldBuilders ? getHeadUrlsFieldBuilder() : null;
          } else {
            this.headUrlsBuilder_.addAllMessages(other.headUrls_);
          } 
        } 
        if (other.getVerified())
          setVerified(other.getVerified()); 
        if (!other.getSUserId().isEmpty()) {
          this.sUserId_ = other.sUserId_;
          this.bitField0_ |= 0x40;
          onChanged();
        } 
        if (this.httpsHeadUrlsBuilder_ == null) {
          if (!other.httpsHeadUrls_.isEmpty()) {
            if (this.httpsHeadUrls_.isEmpty()) {
              this.httpsHeadUrls_ = other.httpsHeadUrls_;
              this.bitField0_ &= 0xFFFFFF7F;
            } else {
              ensureHttpsHeadUrlsIsMutable();
              this.httpsHeadUrls_.addAll(other.httpsHeadUrls_);
            } 
            onChanged();
          } 
        } else if (!other.httpsHeadUrls_.isEmpty()) {
          if (this.httpsHeadUrlsBuilder_.isEmpty()) {
            this.httpsHeadUrlsBuilder_.dispose();
            this.httpsHeadUrlsBuilder_ = null;
            this.httpsHeadUrls_ = other.httpsHeadUrls_;
            this.bitField0_ &= 0xFFFFFF7F;
            this.httpsHeadUrlsBuilder_ = UserInfoOuterClass.UserInfo.alwaysUseFieldBuilders ? getHttpsHeadUrlsFieldBuilder() : null;
          } else {
            this.httpsHeadUrlsBuilder_.addAllMessages(other.httpsHeadUrls_);
          } 
        } 
        if (!other.getKwaiId().isEmpty()) {
          this.kwaiId_ = other.kwaiId_;
          this.bitField0_ |= 0x100;
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
            PicUrlOuterClass.PicUrl m;
            int tag = input.readTag();
            switch (tag) {
              case 0:
                done = true;
                continue;
              case 8:
                this.userId_ = input.readUInt64();
                this.bitField0_ |= 0x1;
                continue;
              case 18:
                this.userName_ = input.readStringRequireUtf8();
                this.bitField0_ |= 0x2;
                continue;
              case 26:
                this.userGender_ = input.readStringRequireUtf8();
                this.bitField0_ |= 0x4;
                continue;
              case 34:
                this.userText_ = input.readStringRequireUtf8();
                this.bitField0_ |= 0x8;
                continue;
              case 42:
                m = (PicUrlOuterClass.PicUrl)input.readMessage(PicUrlOuterClass.PicUrl.parser(), extensionRegistry);
                if (this.headUrlsBuilder_ == null) {
                  ensureHeadUrlsIsMutable();
                  this.headUrls_.add(m);
                  continue;
                } 
                this.headUrlsBuilder_.addMessage((AbstractMessage)m);
                continue;
              case 48:
                this.verified_ = input.readBool();
                this.bitField0_ |= 0x20;
                continue;
              case 58:
                this.sUserId_ = input.readStringRequireUtf8();
                this.bitField0_ |= 0x40;
                continue;
              case 66:
                m = (PicUrlOuterClass.PicUrl)input.readMessage(PicUrlOuterClass.PicUrl.parser(), extensionRegistry);
                if (this.httpsHeadUrlsBuilder_ == null) {
                  ensureHttpsHeadUrlsIsMutable();
                  this.httpsHeadUrls_.add(m);
                  continue;
                } 
                this.httpsHeadUrlsBuilder_.addMessage((AbstractMessage)m);
                continue;
              case 74:
                this.kwaiId_ = input.readStringRequireUtf8();
                this.bitField0_ |= 0x100;
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
      
      public long getUserId() {
        return this.userId_;
      }
      
      public Builder setUserId(long value) {
        this.userId_ = value;
        this.bitField0_ |= 0x1;
        onChanged();
        return this;
      }
      
      public Builder clearUserId() {
        this.bitField0_ &= 0xFFFFFFFE;
        this.userId_ = 0L;
        onChanged();
        return this;
      }
      
      public String getUserName() {
        Object ref = this.userName_;
        if (!(ref instanceof String)) {
          ByteString bs = (ByteString)ref;
          String s = bs.toStringUtf8();
          this.userName_ = s;
          return s;
        } 
        return (String)ref;
      }
      
      public ByteString getUserNameBytes() {
        Object ref = this.userName_;
        if (ref instanceof String) {
          ByteString b = ByteString.copyFromUtf8((String)ref);
          this.userName_ = b;
          return b;
        } 
        return (ByteString)ref;
      }
      
      public Builder setUserName(String value) {
        if (value == null)
          throw new NullPointerException(); 
        this.userName_ = value;
        this.bitField0_ |= 0x2;
        onChanged();
        return this;
      }
      
      public Builder clearUserName() {
        this.userName_ = UserInfoOuterClass.UserInfo.getDefaultInstance().getUserName();
        this.bitField0_ &= 0xFFFFFFFD;
        onChanged();
        return this;
      }
      
      public Builder setUserNameBytes(ByteString value) {
        if (value == null)
          throw new NullPointerException(); 
        UserInfoOuterClass.UserInfo.checkByteStringIsUtf8(value);
        this.userName_ = value;
        this.bitField0_ |= 0x2;
        onChanged();
        return this;
      }
      
      public String getUserGender() {
        Object ref = this.userGender_;
        if (!(ref instanceof String)) {
          ByteString bs = (ByteString)ref;
          String s = bs.toStringUtf8();
          this.userGender_ = s;
          return s;
        } 
        return (String)ref;
      }
      
      public ByteString getUserGenderBytes() {
        Object ref = this.userGender_;
        if (ref instanceof String) {
          ByteString b = ByteString.copyFromUtf8((String)ref);
          this.userGender_ = b;
          return b;
        } 
        return (ByteString)ref;
      }
      
      public Builder setUserGender(String value) {
        if (value == null)
          throw new NullPointerException(); 
        this.userGender_ = value;
        this.bitField0_ |= 0x4;
        onChanged();
        return this;
      }
      
      public Builder clearUserGender() {
        this.userGender_ = UserInfoOuterClass.UserInfo.getDefaultInstance().getUserGender();
        this.bitField0_ &= 0xFFFFFFFB;
        onChanged();
        return this;
      }
      
      public Builder setUserGenderBytes(ByteString value) {
        if (value == null)
          throw new NullPointerException(); 
        UserInfoOuterClass.UserInfo.checkByteStringIsUtf8(value);
        this.userGender_ = value;
        this.bitField0_ |= 0x4;
        onChanged();
        return this;
      }
      
      public String getUserText() {
        Object ref = this.userText_;
        if (!(ref instanceof String)) {
          ByteString bs = (ByteString)ref;
          String s = bs.toStringUtf8();
          this.userText_ = s;
          return s;
        } 
        return (String)ref;
      }
      
      public ByteString getUserTextBytes() {
        Object ref = this.userText_;
        if (ref instanceof String) {
          ByteString b = ByteString.copyFromUtf8((String)ref);
          this.userText_ = b;
          return b;
        } 
        return (ByteString)ref;
      }
      
      public Builder setUserText(String value) {
        if (value == null)
          throw new NullPointerException(); 
        this.userText_ = value;
        this.bitField0_ |= 0x8;
        onChanged();
        return this;
      }
      
      public Builder clearUserText() {
        this.userText_ = UserInfoOuterClass.UserInfo.getDefaultInstance().getUserText();
        this.bitField0_ &= 0xFFFFFFF7;
        onChanged();
        return this;
      }
      
      public Builder setUserTextBytes(ByteString value) {
        if (value == null)
          throw new NullPointerException(); 
        UserInfoOuterClass.UserInfo.checkByteStringIsUtf8(value);
        this.userText_ = value;
        this.bitField0_ |= 0x8;
        onChanged();
        return this;
      }
      
      private void ensureHeadUrlsIsMutable() {
        if ((this.bitField0_ & 0x10) == 0) {
          this.headUrls_ = new ArrayList<>(this.headUrls_);
          this.bitField0_ |= 0x10;
        } 
      }
      
      public List<PicUrlOuterClass.PicUrl> getHeadUrlsList() {
        if (this.headUrlsBuilder_ == null)
          return Collections.unmodifiableList(this.headUrls_); 
        return this.headUrlsBuilder_.getMessageList();
      }
      
      public int getHeadUrlsCount() {
        if (this.headUrlsBuilder_ == null)
          return this.headUrls_.size(); 
        return this.headUrlsBuilder_.getCount();
      }
      
      public PicUrlOuterClass.PicUrl getHeadUrls(int index) {
        if (this.headUrlsBuilder_ == null)
          return this.headUrls_.get(index); 
        return (PicUrlOuterClass.PicUrl)this.headUrlsBuilder_.getMessage(index);
      }
      
      public Builder setHeadUrls(int index, PicUrlOuterClass.PicUrl value) {
        if (this.headUrlsBuilder_ == null) {
          if (value == null)
            throw new NullPointerException(); 
          ensureHeadUrlsIsMutable();
          this.headUrls_.set(index, value);
          onChanged();
        } else {
          this.headUrlsBuilder_.setMessage(index, (AbstractMessage)value);
        } 
        return this;
      }
      
      public Builder setHeadUrls(int index, PicUrlOuterClass.PicUrl.Builder builderForValue) {
        if (this.headUrlsBuilder_ == null) {
          ensureHeadUrlsIsMutable();
          this.headUrls_.set(index, builderForValue.build());
          onChanged();
        } else {
          this.headUrlsBuilder_.setMessage(index, (AbstractMessage)builderForValue.build());
        } 
        return this;
      }
      
      public Builder addHeadUrls(PicUrlOuterClass.PicUrl value) {
        if (this.headUrlsBuilder_ == null) {
          if (value == null)
            throw new NullPointerException(); 
          ensureHeadUrlsIsMutable();
          this.headUrls_.add(value);
          onChanged();
        } else {
          this.headUrlsBuilder_.addMessage((AbstractMessage)value);
        } 
        return this;
      }
      
      public Builder addHeadUrls(int index, PicUrlOuterClass.PicUrl value) {
        if (this.headUrlsBuilder_ == null) {
          if (value == null)
            throw new NullPointerException(); 
          ensureHeadUrlsIsMutable();
          this.headUrls_.add(index, value);
          onChanged();
        } else {
          this.headUrlsBuilder_.addMessage(index, (AbstractMessage)value);
        } 
        return this;
      }
      
      public Builder addHeadUrls(PicUrlOuterClass.PicUrl.Builder builderForValue) {
        if (this.headUrlsBuilder_ == null) {
          ensureHeadUrlsIsMutable();
          this.headUrls_.add(builderForValue.build());
          onChanged();
        } else {
          this.headUrlsBuilder_.addMessage((AbstractMessage)builderForValue.build());
        } 
        return this;
      }
      
      public Builder addHeadUrls(int index, PicUrlOuterClass.PicUrl.Builder builderForValue) {
        if (this.headUrlsBuilder_ == null) {
          ensureHeadUrlsIsMutable();
          this.headUrls_.add(index, builderForValue.build());
          onChanged();
        } else {
          this.headUrlsBuilder_.addMessage(index, (AbstractMessage)builderForValue.build());
        } 
        return this;
      }
      
      public Builder addAllHeadUrls(Iterable<? extends PicUrlOuterClass.PicUrl> values) {
        if (this.headUrlsBuilder_ == null) {
          ensureHeadUrlsIsMutable();
          AbstractMessageLite.Builder.addAll(values, this.headUrls_);
          onChanged();
        } else {
          this.headUrlsBuilder_.addAllMessages(values);
        } 
        return this;
      }
      
      public Builder clearHeadUrls() {
        if (this.headUrlsBuilder_ == null) {
          this.headUrls_ = Collections.emptyList();
          this.bitField0_ &= 0xFFFFFFEF;
          onChanged();
        } else {
          this.headUrlsBuilder_.clear();
        } 
        return this;
      }
      
      public Builder removeHeadUrls(int index) {
        if (this.headUrlsBuilder_ == null) {
          ensureHeadUrlsIsMutable();
          this.headUrls_.remove(index);
          onChanged();
        } else {
          this.headUrlsBuilder_.remove(index);
        } 
        return this;
      }
      
      public PicUrlOuterClass.PicUrl.Builder getHeadUrlsBuilder(int index) {
        return (PicUrlOuterClass.PicUrl.Builder)getHeadUrlsFieldBuilder().getBuilder(index);
      }
      
      public PicUrlOuterClass.PicUrlOrBuilder getHeadUrlsOrBuilder(int index) {
        if (this.headUrlsBuilder_ == null)
          return this.headUrls_.get(index); 
        return (PicUrlOuterClass.PicUrlOrBuilder)this.headUrlsBuilder_.getMessageOrBuilder(index);
      }
      
      public List<? extends PicUrlOuterClass.PicUrlOrBuilder> getHeadUrlsOrBuilderList() {
        if (this.headUrlsBuilder_ != null)
          return this.headUrlsBuilder_.getMessageOrBuilderList(); 
        return Collections.unmodifiableList((List)this.headUrls_);
      }
      
      public PicUrlOuterClass.PicUrl.Builder addHeadUrlsBuilder() {
        return (PicUrlOuterClass.PicUrl.Builder)getHeadUrlsFieldBuilder().addBuilder((AbstractMessage)PicUrlOuterClass.PicUrl.getDefaultInstance());
      }
      
      public PicUrlOuterClass.PicUrl.Builder addHeadUrlsBuilder(int index) {
        return (PicUrlOuterClass.PicUrl.Builder)getHeadUrlsFieldBuilder().addBuilder(index, (AbstractMessage)PicUrlOuterClass.PicUrl.getDefaultInstance());
      }
      
      public List<PicUrlOuterClass.PicUrl.Builder> getHeadUrlsBuilderList() {
        return getHeadUrlsFieldBuilder().getBuilderList();
      }
      
      private RepeatedFieldBuilderV3<PicUrlOuterClass.PicUrl, PicUrlOuterClass.PicUrl.Builder, PicUrlOuterClass.PicUrlOrBuilder> getHeadUrlsFieldBuilder() {
        if (this.headUrlsBuilder_ == null) {
          this.headUrlsBuilder_ = new RepeatedFieldBuilderV3(this.headUrls_, ((this.bitField0_ & 0x10) != 0), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
          this.headUrls_ = null;
        } 
        return this.headUrlsBuilder_;
      }
      
      public boolean getVerified() {
        return this.verified_;
      }
      
      public Builder setVerified(boolean value) {
        this.verified_ = value;
        this.bitField0_ |= 0x20;
        onChanged();
        return this;
      }
      
      public Builder clearVerified() {
        this.bitField0_ &= 0xFFFFFFDF;
        this.verified_ = false;
        onChanged();
        return this;
      }
      
      public String getSUserId() {
        Object ref = this.sUserId_;
        if (!(ref instanceof String)) {
          ByteString bs = (ByteString)ref;
          String s = bs.toStringUtf8();
          this.sUserId_ = s;
          return s;
        } 
        return (String)ref;
      }
      
      public ByteString getSUserIdBytes() {
        Object ref = this.sUserId_;
        if (ref instanceof String) {
          ByteString b = ByteString.copyFromUtf8((String)ref);
          this.sUserId_ = b;
          return b;
        } 
        return (ByteString)ref;
      }
      
      public Builder setSUserId(String value) {
        if (value == null)
          throw new NullPointerException(); 
        this.sUserId_ = value;
        this.bitField0_ |= 0x40;
        onChanged();
        return this;
      }
      
      public Builder clearSUserId() {
        this.sUserId_ = UserInfoOuterClass.UserInfo.getDefaultInstance().getSUserId();
        this.bitField0_ &= 0xFFFFFFBF;
        onChanged();
        return this;
      }
      
      public Builder setSUserIdBytes(ByteString value) {
        if (value == null)
          throw new NullPointerException(); 
        UserInfoOuterClass.UserInfo.checkByteStringIsUtf8(value);
        this.sUserId_ = value;
        this.bitField0_ |= 0x40;
        onChanged();
        return this;
      }
      
      private void ensureHttpsHeadUrlsIsMutable() {
        if ((this.bitField0_ & 0x80) == 0) {
          this.httpsHeadUrls_ = new ArrayList<>(this.httpsHeadUrls_);
          this.bitField0_ |= 0x80;
        } 
      }
      
      public List<PicUrlOuterClass.PicUrl> getHttpsHeadUrlsList() {
        if (this.httpsHeadUrlsBuilder_ == null)
          return Collections.unmodifiableList(this.httpsHeadUrls_); 
        return this.httpsHeadUrlsBuilder_.getMessageList();
      }
      
      public int getHttpsHeadUrlsCount() {
        if (this.httpsHeadUrlsBuilder_ == null)
          return this.httpsHeadUrls_.size(); 
        return this.httpsHeadUrlsBuilder_.getCount();
      }
      
      public PicUrlOuterClass.PicUrl getHttpsHeadUrls(int index) {
        if (this.httpsHeadUrlsBuilder_ == null)
          return this.httpsHeadUrls_.get(index); 
        return (PicUrlOuterClass.PicUrl)this.httpsHeadUrlsBuilder_.getMessage(index);
      }
      
      public Builder setHttpsHeadUrls(int index, PicUrlOuterClass.PicUrl value) {
        if (this.httpsHeadUrlsBuilder_ == null) {
          if (value == null)
            throw new NullPointerException(); 
          ensureHttpsHeadUrlsIsMutable();
          this.httpsHeadUrls_.set(index, value);
          onChanged();
        } else {
          this.httpsHeadUrlsBuilder_.setMessage(index, (AbstractMessage)value);
        } 
        return this;
      }
      
      public Builder setHttpsHeadUrls(int index, PicUrlOuterClass.PicUrl.Builder builderForValue) {
        if (this.httpsHeadUrlsBuilder_ == null) {
          ensureHttpsHeadUrlsIsMutable();
          this.httpsHeadUrls_.set(index, builderForValue.build());
          onChanged();
        } else {
          this.httpsHeadUrlsBuilder_.setMessage(index, (AbstractMessage)builderForValue.build());
        } 
        return this;
      }
      
      public Builder addHttpsHeadUrls(PicUrlOuterClass.PicUrl value) {
        if (this.httpsHeadUrlsBuilder_ == null) {
          if (value == null)
            throw new NullPointerException(); 
          ensureHttpsHeadUrlsIsMutable();
          this.httpsHeadUrls_.add(value);
          onChanged();
        } else {
          this.httpsHeadUrlsBuilder_.addMessage((AbstractMessage)value);
        } 
        return this;
      }
      
      public Builder addHttpsHeadUrls(int index, PicUrlOuterClass.PicUrl value) {
        if (this.httpsHeadUrlsBuilder_ == null) {
          if (value == null)
            throw new NullPointerException(); 
          ensureHttpsHeadUrlsIsMutable();
          this.httpsHeadUrls_.add(index, value);
          onChanged();
        } else {
          this.httpsHeadUrlsBuilder_.addMessage(index, (AbstractMessage)value);
        } 
        return this;
      }
      
      public Builder addHttpsHeadUrls(PicUrlOuterClass.PicUrl.Builder builderForValue) {
        if (this.httpsHeadUrlsBuilder_ == null) {
          ensureHttpsHeadUrlsIsMutable();
          this.httpsHeadUrls_.add(builderForValue.build());
          onChanged();
        } else {
          this.httpsHeadUrlsBuilder_.addMessage((AbstractMessage)builderForValue.build());
        } 
        return this;
      }
      
      public Builder addHttpsHeadUrls(int index, PicUrlOuterClass.PicUrl.Builder builderForValue) {
        if (this.httpsHeadUrlsBuilder_ == null) {
          ensureHttpsHeadUrlsIsMutable();
          this.httpsHeadUrls_.add(index, builderForValue.build());
          onChanged();
        } else {
          this.httpsHeadUrlsBuilder_.addMessage(index, (AbstractMessage)builderForValue.build());
        } 
        return this;
      }
      
      public Builder addAllHttpsHeadUrls(Iterable<? extends PicUrlOuterClass.PicUrl> values) {
        if (this.httpsHeadUrlsBuilder_ == null) {
          ensureHttpsHeadUrlsIsMutable();
          AbstractMessageLite.Builder.addAll(values, this.httpsHeadUrls_);
          onChanged();
        } else {
          this.httpsHeadUrlsBuilder_.addAllMessages(values);
        } 
        return this;
      }
      
      public Builder clearHttpsHeadUrls() {
        if (this.httpsHeadUrlsBuilder_ == null) {
          this.httpsHeadUrls_ = Collections.emptyList();
          this.bitField0_ &= 0xFFFFFF7F;
          onChanged();
        } else {
          this.httpsHeadUrlsBuilder_.clear();
        } 
        return this;
      }
      
      public Builder removeHttpsHeadUrls(int index) {
        if (this.httpsHeadUrlsBuilder_ == null) {
          ensureHttpsHeadUrlsIsMutable();
          this.httpsHeadUrls_.remove(index);
          onChanged();
        } else {
          this.httpsHeadUrlsBuilder_.remove(index);
        } 
        return this;
      }
      
      public PicUrlOuterClass.PicUrl.Builder getHttpsHeadUrlsBuilder(int index) {
        return (PicUrlOuterClass.PicUrl.Builder)getHttpsHeadUrlsFieldBuilder().getBuilder(index);
      }
      
      public PicUrlOuterClass.PicUrlOrBuilder getHttpsHeadUrlsOrBuilder(int index) {
        if (this.httpsHeadUrlsBuilder_ == null)
          return this.httpsHeadUrls_.get(index); 
        return (PicUrlOuterClass.PicUrlOrBuilder)this.httpsHeadUrlsBuilder_.getMessageOrBuilder(index);
      }
      
      public List<? extends PicUrlOuterClass.PicUrlOrBuilder> getHttpsHeadUrlsOrBuilderList() {
        if (this.httpsHeadUrlsBuilder_ != null)
          return this.httpsHeadUrlsBuilder_.getMessageOrBuilderList(); 
        return Collections.unmodifiableList((List)this.httpsHeadUrls_);
      }
      
      public PicUrlOuterClass.PicUrl.Builder addHttpsHeadUrlsBuilder() {
        return (PicUrlOuterClass.PicUrl.Builder)getHttpsHeadUrlsFieldBuilder().addBuilder((AbstractMessage)PicUrlOuterClass.PicUrl.getDefaultInstance());
      }
      
      public PicUrlOuterClass.PicUrl.Builder addHttpsHeadUrlsBuilder(int index) {
        return (PicUrlOuterClass.PicUrl.Builder)getHttpsHeadUrlsFieldBuilder().addBuilder(index, (AbstractMessage)PicUrlOuterClass.PicUrl.getDefaultInstance());
      }
      
      public List<PicUrlOuterClass.PicUrl.Builder> getHttpsHeadUrlsBuilderList() {
        return getHttpsHeadUrlsFieldBuilder().getBuilderList();
      }
      
      private RepeatedFieldBuilderV3<PicUrlOuterClass.PicUrl, PicUrlOuterClass.PicUrl.Builder, PicUrlOuterClass.PicUrlOrBuilder> getHttpsHeadUrlsFieldBuilder() {
        if (this.httpsHeadUrlsBuilder_ == null) {
          this.httpsHeadUrlsBuilder_ = new RepeatedFieldBuilderV3(this.httpsHeadUrls_, ((this.bitField0_ & 0x80) != 0), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
          this.httpsHeadUrls_ = null;
        } 
        return this.httpsHeadUrlsBuilder_;
      }
      
      public String getKwaiId() {
        Object ref = this.kwaiId_;
        if (!(ref instanceof String)) {
          ByteString bs = (ByteString)ref;
          String s = bs.toStringUtf8();
          this.kwaiId_ = s;
          return s;
        } 
        return (String)ref;
      }
      
      public ByteString getKwaiIdBytes() {
        Object ref = this.kwaiId_;
        if (ref instanceof String) {
          ByteString b = ByteString.copyFromUtf8((String)ref);
          this.kwaiId_ = b;
          return b;
        } 
        return (ByteString)ref;
      }
      
      public Builder setKwaiId(String value) {
        if (value == null)
          throw new NullPointerException(); 
        this.kwaiId_ = value;
        this.bitField0_ |= 0x100;
        onChanged();
        return this;
      }
      
      public Builder clearKwaiId() {
        this.kwaiId_ = UserInfoOuterClass.UserInfo.getDefaultInstance().getKwaiId();
        this.bitField0_ &= 0xFFFFFEFF;
        onChanged();
        return this;
      }
      
      public Builder setKwaiIdBytes(ByteString value) {
        if (value == null)
          throw new NullPointerException(); 
        UserInfoOuterClass.UserInfo.checkByteStringIsUtf8(value);
        this.kwaiId_ = value;
        this.bitField0_ |= 0x100;
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
    
    private static final UserInfo DEFAULT_INSTANCE = new UserInfo();
    
    public static UserInfo getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }
    
    private static final Parser<UserInfo> PARSER = (Parser<UserInfo>)new AbstractParser<UserInfo>() {
        public UserInfoOuterClass.UserInfo parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
          UserInfoOuterClass.UserInfo.Builder builder = UserInfoOuterClass.UserInfo.newBuilder();
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
    
    public static Parser<UserInfo> parser() {
      return PARSER;
    }
    
    public Parser<UserInfo> getParserForType() {
      return PARSER;
    }
    
    public UserInfo getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }
  }
  
  public static Descriptors.FileDescriptor getDescriptor() {
    return descriptor;
  }
  
  static {
    String[] descriptorData = { "\n\016UserInfo.proto\032\fPicUrl.proto\"À\001\n\bUserInfo\022\016\n\006userId\030\001 \001(\004\022\020\n\buserName\030\002 \001(\t\022\022\n\nuserGender\030\003 \001(\t\022\020\n\buserText\030\004 \001(\t\022\031\n\bheadUrls\030\005 \003(\0132\007.PicUrl\022\020\n\bverified\030\006 \001(\b\022\017\n\007sUserId\030\007 \001(\t\022\036\n\rhttpsHeadUrls\030\b \003(\0132\007.PicUrl\022\016\n\006kwaiId\030\t \001(\tB<\n:tech.ordinaryroad.live.chat.client.codec.kuaishou.protobufb\006proto3" };
    descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[] { PicUrlOuterClass.getDescriptor() });
    internal_static_UserInfo_descriptor = getDescriptor().getMessageTypes().get(0);
    internal_static_UserInfo_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_UserInfo_descriptor, new String[] { "UserId", "UserName", "UserGender", "UserText", "HeadUrls", "Verified", "SUserId", "HttpsHeadUrls", "KwaiId" });
    PicUrlOuterClass.getDescriptor();
  }
  
  public static interface UserInfoOrBuilder extends MessageOrBuilder {
    long getUserId();
    
    String getUserName();
    
    ByteString getUserNameBytes();
    
    String getUserGender();
    
    ByteString getUserGenderBytes();
    
    String getUserText();
    
    ByteString getUserTextBytes();
    
    List<PicUrlOuterClass.PicUrl> getHeadUrlsList();
    
    PicUrlOuterClass.PicUrl getHeadUrls(int param1Int);
    
    int getHeadUrlsCount();
    
    List<? extends PicUrlOuterClass.PicUrlOrBuilder> getHeadUrlsOrBuilderList();
    
    PicUrlOuterClass.PicUrlOrBuilder getHeadUrlsOrBuilder(int param1Int);
    
    boolean getVerified();
    
    String getSUserId();
    
    ByteString getSUserIdBytes();
    
    List<PicUrlOuterClass.PicUrl> getHttpsHeadUrlsList();
    
    PicUrlOuterClass.PicUrl getHttpsHeadUrls(int param1Int);
    
    int getHttpsHeadUrlsCount();
    
    List<? extends PicUrlOuterClass.PicUrlOrBuilder> getHttpsHeadUrlsOrBuilderList();
    
    PicUrlOuterClass.PicUrlOrBuilder getHttpsHeadUrlsOrBuilder(int param1Int);
    
    String getKwaiId();
    
    ByteString getKwaiIdBytes();
  }
}
