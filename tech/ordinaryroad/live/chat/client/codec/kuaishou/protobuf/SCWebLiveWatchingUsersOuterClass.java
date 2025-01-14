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

public final class SCWebLiveWatchingUsersOuterClass {
  private static final Descriptors.Descriptor internal_static_SCWebLiveWatchingUsers_descriptor;
  
  private static final GeneratedMessageV3.FieldAccessorTable internal_static_SCWebLiveWatchingUsers_fieldAccessorTable;
  
  private static Descriptors.FileDescriptor descriptor;
  
  public static void registerAllExtensions(ExtensionRegistryLite registry) {}
  
  public static void registerAllExtensions(ExtensionRegistry registry) {
    registerAllExtensions((ExtensionRegistryLite)registry);
  }
  
  public static final class SCWebLiveWatchingUsers extends GeneratedMessageV3 implements SCWebLiveWatchingUsersOrBuilder {
    private static final long serialVersionUID = 0L;
    
    public static final int WATCHINGUSER_FIELD_NUMBER = 1;
    
    private List<WebWatchingUserInfoOuterClass.WebWatchingUserInfo> watchingUser_;
    
    public static final int DISPLAYWATCHINGCOUNT_FIELD_NUMBER = 2;
    
    private volatile Object displayWatchingCount_;
    
    public static final int PENDINGDURATION_FIELD_NUMBER = 3;
    
    private long pendingDuration_;
    
    private byte memoizedIsInitialized;
    
    private SCWebLiveWatchingUsers(GeneratedMessageV3.Builder<?> builder) {
      super(builder);
      this.displayWatchingCount_ = "";
      this.pendingDuration_ = 0L;
      this.memoizedIsInitialized = -1;
    }
    
    private SCWebLiveWatchingUsers() {
      this.displayWatchingCount_ = "";
      this.pendingDuration_ = 0L;
      this.memoizedIsInitialized = -1;
      this.watchingUser_ = Collections.emptyList();
      this.displayWatchingCount_ = "";
    }
    
    protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
      return new SCWebLiveWatchingUsers();
    }
    
    public static final Descriptors.Descriptor getDescriptor() {
      return SCWebLiveWatchingUsersOuterClass.internal_static_SCWebLiveWatchingUsers_descriptor;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return SCWebLiveWatchingUsersOuterClass.internal_static_SCWebLiveWatchingUsers_fieldAccessorTable.ensureFieldAccessorsInitialized(SCWebLiveWatchingUsers.class, Builder.class);
    }
    
    public List<WebWatchingUserInfoOuterClass.WebWatchingUserInfo> getWatchingUserList() {
      return this.watchingUser_;
    }
    
    public List<? extends WebWatchingUserInfoOuterClass.WebWatchingUserInfoOrBuilder> getWatchingUserOrBuilderList() {
      return (List)this.watchingUser_;
    }
    
    public int getWatchingUserCount() {
      return this.watchingUser_.size();
    }
    
    public WebWatchingUserInfoOuterClass.WebWatchingUserInfo getWatchingUser(int index) {
      return this.watchingUser_.get(index);
    }
    
    public WebWatchingUserInfoOuterClass.WebWatchingUserInfoOrBuilder getWatchingUserOrBuilder(int index) {
      return this.watchingUser_.get(index);
    }
    
    public String getDisplayWatchingCount() {
      Object ref = this.displayWatchingCount_;
      if (ref instanceof String)
        return (String)ref; 
      ByteString bs = (ByteString)ref;
      String s = bs.toStringUtf8();
      this.displayWatchingCount_ = s;
      return s;
    }
    
    public ByteString getDisplayWatchingCountBytes() {
      Object ref = this.displayWatchingCount_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.displayWatchingCount_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public long getPendingDuration() {
      return this.pendingDuration_;
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
      for (int i = 0; i < this.watchingUser_.size(); i++)
        output.writeMessage(1, (MessageLite)this.watchingUser_.get(i)); 
      if (!GeneratedMessageV3.isStringEmpty(this.displayWatchingCount_))
        GeneratedMessageV3.writeString(output, 2, this.displayWatchingCount_); 
      if (this.pendingDuration_ != 0L)
        output.writeUInt64(3, this.pendingDuration_); 
      getUnknownFields().writeTo(output);
    }
    
    public int getSerializedSize() {
      int size = this.memoizedSize;
      if (size != -1)
        return size; 
      size = 0;
      for (int i = 0; i < this.watchingUser_.size(); i++)
        size += 
          CodedOutputStream.computeMessageSize(1, (MessageLite)this.watchingUser_.get(i)); 
      if (!GeneratedMessageV3.isStringEmpty(this.displayWatchingCount_))
        size += GeneratedMessageV3.computeStringSize(2, this.displayWatchingCount_); 
      if (this.pendingDuration_ != 0L)
        size += 
          CodedOutputStream.computeUInt64Size(3, this.pendingDuration_); 
      size += getUnknownFields().getSerializedSize();
      this.memoizedSize = size;
      return size;
    }
    
    public boolean equals(Object obj) {
      if (obj == this)
        return true; 
      if (!(obj instanceof SCWebLiveWatchingUsers))
        return super.equals(obj); 
      SCWebLiveWatchingUsers other = (SCWebLiveWatchingUsers)obj;
      if (!getWatchingUserList().equals(other.getWatchingUserList()))
        return false; 
      if (!getDisplayWatchingCount().equals(other.getDisplayWatchingCount()))
        return false; 
      if (getPendingDuration() != other
        .getPendingDuration())
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
      if (getWatchingUserCount() > 0) {
        hash = 37 * hash + 1;
        hash = 53 * hash + getWatchingUserList().hashCode();
      } 
      hash = 37 * hash + 2;
      hash = 53 * hash + getDisplayWatchingCount().hashCode();
      hash = 37 * hash + 3;
      hash = 53 * hash + Internal.hashLong(
          getPendingDuration());
      hash = 29 * hash + getUnknownFields().hashCode();
      this.memoizedHashCode = hash;
      return hash;
    }
    
    public static SCWebLiveWatchingUsers parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (SCWebLiveWatchingUsers)PARSER.parseFrom(data);
    }
    
    public static SCWebLiveWatchingUsers parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (SCWebLiveWatchingUsers)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static SCWebLiveWatchingUsers parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (SCWebLiveWatchingUsers)PARSER.parseFrom(data);
    }
    
    public static SCWebLiveWatchingUsers parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (SCWebLiveWatchingUsers)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static SCWebLiveWatchingUsers parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (SCWebLiveWatchingUsers)PARSER.parseFrom(data);
    }
    
    public static SCWebLiveWatchingUsers parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (SCWebLiveWatchingUsers)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static SCWebLiveWatchingUsers parseFrom(InputStream input) throws IOException {
      return 
        (SCWebLiveWatchingUsers)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static SCWebLiveWatchingUsers parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (SCWebLiveWatchingUsers)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static SCWebLiveWatchingUsers parseDelimitedFrom(InputStream input) throws IOException {
      return 
        (SCWebLiveWatchingUsers)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }
    
    public static SCWebLiveWatchingUsers parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (SCWebLiveWatchingUsers)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static SCWebLiveWatchingUsers parseFrom(CodedInputStream input) throws IOException {
      return 
        (SCWebLiveWatchingUsers)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static SCWebLiveWatchingUsers parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (SCWebLiveWatchingUsers)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public Builder newBuilderForType() {
      return newBuilder();
    }
    
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    
    public static Builder newBuilder(SCWebLiveWatchingUsers prototype) {
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
    
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements SCWebLiveWatchingUsersOuterClass.SCWebLiveWatchingUsersOrBuilder {
      private int bitField0_;
      
      private List<WebWatchingUserInfoOuterClass.WebWatchingUserInfo> watchingUser_;
      
      private RepeatedFieldBuilderV3<WebWatchingUserInfoOuterClass.WebWatchingUserInfo, WebWatchingUserInfoOuterClass.WebWatchingUserInfo.Builder, WebWatchingUserInfoOuterClass.WebWatchingUserInfoOrBuilder> watchingUserBuilder_;
      
      private Object displayWatchingCount_;
      
      private long pendingDuration_;
      
      public static final Descriptors.Descriptor getDescriptor() {
        return SCWebLiveWatchingUsersOuterClass.internal_static_SCWebLiveWatchingUsers_descriptor;
      }
      
      protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return SCWebLiveWatchingUsersOuterClass.internal_static_SCWebLiveWatchingUsers_fieldAccessorTable
          .ensureFieldAccessorsInitialized(SCWebLiveWatchingUsersOuterClass.SCWebLiveWatchingUsers.class, Builder.class);
      }
      
      private Builder() {
        this
          .watchingUser_ = Collections.emptyList();
        this.displayWatchingCount_ = "";
      }
      
      private Builder(GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        this.watchingUser_ = Collections.emptyList();
        this.displayWatchingCount_ = "";
      }
      
      public Builder clear() {
        super.clear();
        this.bitField0_ = 0;
        if (this.watchingUserBuilder_ == null) {
          this.watchingUser_ = Collections.emptyList();
        } else {
          this.watchingUser_ = null;
          this.watchingUserBuilder_.clear();
        } 
        this.bitField0_ &= 0xFFFFFFFE;
        this.displayWatchingCount_ = "";
        this.pendingDuration_ = 0L;
        return this;
      }
      
      public Descriptors.Descriptor getDescriptorForType() {
        return SCWebLiveWatchingUsersOuterClass.internal_static_SCWebLiveWatchingUsers_descriptor;
      }
      
      public SCWebLiveWatchingUsersOuterClass.SCWebLiveWatchingUsers getDefaultInstanceForType() {
        return SCWebLiveWatchingUsersOuterClass.SCWebLiveWatchingUsers.getDefaultInstance();
      }
      
      public SCWebLiveWatchingUsersOuterClass.SCWebLiveWatchingUsers build() {
        SCWebLiveWatchingUsersOuterClass.SCWebLiveWatchingUsers result = buildPartial();
        if (!result.isInitialized())
          throw newUninitializedMessageException(result); 
        return result;
      }
      
      public SCWebLiveWatchingUsersOuterClass.SCWebLiveWatchingUsers buildPartial() {
        SCWebLiveWatchingUsersOuterClass.SCWebLiveWatchingUsers result = new SCWebLiveWatchingUsersOuterClass.SCWebLiveWatchingUsers(this);
        buildPartialRepeatedFields(result);
        if (this.bitField0_ != 0)
          buildPartial0(result); 
        onBuilt();
        return result;
      }
      
      private void buildPartialRepeatedFields(SCWebLiveWatchingUsersOuterClass.SCWebLiveWatchingUsers result) {
        if (this.watchingUserBuilder_ == null) {
          if ((this.bitField0_ & 0x1) != 0) {
            this.watchingUser_ = Collections.unmodifiableList(this.watchingUser_);
            this.bitField0_ &= 0xFFFFFFFE;
          } 
          result.watchingUser_ = this.watchingUser_;
        } else {
          result.watchingUser_ = this.watchingUserBuilder_.build();
        } 
      }
      
      private void buildPartial0(SCWebLiveWatchingUsersOuterClass.SCWebLiveWatchingUsers result) {
        int from_bitField0_ = this.bitField0_;
        if ((from_bitField0_ & 0x2) != 0)
          result.displayWatchingCount_ = this.displayWatchingCount_; 
        if ((from_bitField0_ & 0x4) != 0)
          result.pendingDuration_ = this.pendingDuration_; 
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
        if (other instanceof SCWebLiveWatchingUsersOuterClass.SCWebLiveWatchingUsers)
          return mergeFrom((SCWebLiveWatchingUsersOuterClass.SCWebLiveWatchingUsers)other); 
        super.mergeFrom(other);
        return this;
      }
      
      public Builder mergeFrom(SCWebLiveWatchingUsersOuterClass.SCWebLiveWatchingUsers other) {
        if (other == SCWebLiveWatchingUsersOuterClass.SCWebLiveWatchingUsers.getDefaultInstance())
          return this; 
        if (this.watchingUserBuilder_ == null) {
          if (!other.watchingUser_.isEmpty()) {
            if (this.watchingUser_.isEmpty()) {
              this.watchingUser_ = other.watchingUser_;
              this.bitField0_ &= 0xFFFFFFFE;
            } else {
              ensureWatchingUserIsMutable();
              this.watchingUser_.addAll(other.watchingUser_);
            } 
            onChanged();
          } 
        } else if (!other.watchingUser_.isEmpty()) {
          if (this.watchingUserBuilder_.isEmpty()) {
            this.watchingUserBuilder_.dispose();
            this.watchingUserBuilder_ = null;
            this.watchingUser_ = other.watchingUser_;
            this.bitField0_ &= 0xFFFFFFFE;
            this.watchingUserBuilder_ = SCWebLiveWatchingUsersOuterClass.SCWebLiveWatchingUsers.alwaysUseFieldBuilders ? getWatchingUserFieldBuilder() : null;
          } else {
            this.watchingUserBuilder_.addAllMessages(other.watchingUser_);
          } 
        } 
        if (!other.getDisplayWatchingCount().isEmpty()) {
          this.displayWatchingCount_ = other.displayWatchingCount_;
          this.bitField0_ |= 0x2;
          onChanged();
        } 
        if (other.getPendingDuration() != 0L)
          setPendingDuration(other.getPendingDuration()); 
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
            WebWatchingUserInfoOuterClass.WebWatchingUserInfo m;
            int tag = input.readTag();
            switch (tag) {
              case 0:
                done = true;
                continue;
              case 10:
                m = (WebWatchingUserInfoOuterClass.WebWatchingUserInfo)input.readMessage(WebWatchingUserInfoOuterClass.WebWatchingUserInfo.parser(), extensionRegistry);
                if (this.watchingUserBuilder_ == null) {
                  ensureWatchingUserIsMutable();
                  this.watchingUser_.add(m);
                  continue;
                } 
                this.watchingUserBuilder_.addMessage((AbstractMessage)m);
                continue;
              case 18:
                this.displayWatchingCount_ = input.readStringRequireUtf8();
                this.bitField0_ |= 0x2;
                continue;
              case 24:
                this.pendingDuration_ = input.readUInt64();
                this.bitField0_ |= 0x4;
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
      
      private void ensureWatchingUserIsMutable() {
        if ((this.bitField0_ & 0x1) == 0) {
          this.watchingUser_ = new ArrayList<>(this.watchingUser_);
          this.bitField0_ |= 0x1;
        } 
      }
      
      public List<WebWatchingUserInfoOuterClass.WebWatchingUserInfo> getWatchingUserList() {
        if (this.watchingUserBuilder_ == null)
          return Collections.unmodifiableList(this.watchingUser_); 
        return this.watchingUserBuilder_.getMessageList();
      }
      
      public int getWatchingUserCount() {
        if (this.watchingUserBuilder_ == null)
          return this.watchingUser_.size(); 
        return this.watchingUserBuilder_.getCount();
      }
      
      public WebWatchingUserInfoOuterClass.WebWatchingUserInfo getWatchingUser(int index) {
        if (this.watchingUserBuilder_ == null)
          return this.watchingUser_.get(index); 
        return (WebWatchingUserInfoOuterClass.WebWatchingUserInfo)this.watchingUserBuilder_.getMessage(index);
      }
      
      public Builder setWatchingUser(int index, WebWatchingUserInfoOuterClass.WebWatchingUserInfo value) {
        if (this.watchingUserBuilder_ == null) {
          if (value == null)
            throw new NullPointerException(); 
          ensureWatchingUserIsMutable();
          this.watchingUser_.set(index, value);
          onChanged();
        } else {
          this.watchingUserBuilder_.setMessage(index, (AbstractMessage)value);
        } 
        return this;
      }
      
      public Builder setWatchingUser(int index, WebWatchingUserInfoOuterClass.WebWatchingUserInfo.Builder builderForValue) {
        if (this.watchingUserBuilder_ == null) {
          ensureWatchingUserIsMutable();
          this.watchingUser_.set(index, builderForValue.build());
          onChanged();
        } else {
          this.watchingUserBuilder_.setMessage(index, (AbstractMessage)builderForValue.build());
        } 
        return this;
      }
      
      public Builder addWatchingUser(WebWatchingUserInfoOuterClass.WebWatchingUserInfo value) {
        if (this.watchingUserBuilder_ == null) {
          if (value == null)
            throw new NullPointerException(); 
          ensureWatchingUserIsMutable();
          this.watchingUser_.add(value);
          onChanged();
        } else {
          this.watchingUserBuilder_.addMessage((AbstractMessage)value);
        } 
        return this;
      }
      
      public Builder addWatchingUser(int index, WebWatchingUserInfoOuterClass.WebWatchingUserInfo value) {
        if (this.watchingUserBuilder_ == null) {
          if (value == null)
            throw new NullPointerException(); 
          ensureWatchingUserIsMutable();
          this.watchingUser_.add(index, value);
          onChanged();
        } else {
          this.watchingUserBuilder_.addMessage(index, (AbstractMessage)value);
        } 
        return this;
      }
      
      public Builder addWatchingUser(WebWatchingUserInfoOuterClass.WebWatchingUserInfo.Builder builderForValue) {
        if (this.watchingUserBuilder_ == null) {
          ensureWatchingUserIsMutable();
          this.watchingUser_.add(builderForValue.build());
          onChanged();
        } else {
          this.watchingUserBuilder_.addMessage((AbstractMessage)builderForValue.build());
        } 
        return this;
      }
      
      public Builder addWatchingUser(int index, WebWatchingUserInfoOuterClass.WebWatchingUserInfo.Builder builderForValue) {
        if (this.watchingUserBuilder_ == null) {
          ensureWatchingUserIsMutable();
          this.watchingUser_.add(index, builderForValue.build());
          onChanged();
        } else {
          this.watchingUserBuilder_.addMessage(index, (AbstractMessage)builderForValue.build());
        } 
        return this;
      }
      
      public Builder addAllWatchingUser(Iterable<? extends WebWatchingUserInfoOuterClass.WebWatchingUserInfo> values) {
        if (this.watchingUserBuilder_ == null) {
          ensureWatchingUserIsMutable();
          AbstractMessageLite.Builder.addAll(values, this.watchingUser_);
          onChanged();
        } else {
          this.watchingUserBuilder_.addAllMessages(values);
        } 
        return this;
      }
      
      public Builder clearWatchingUser() {
        if (this.watchingUserBuilder_ == null) {
          this.watchingUser_ = Collections.emptyList();
          this.bitField0_ &= 0xFFFFFFFE;
          onChanged();
        } else {
          this.watchingUserBuilder_.clear();
        } 
        return this;
      }
      
      public Builder removeWatchingUser(int index) {
        if (this.watchingUserBuilder_ == null) {
          ensureWatchingUserIsMutable();
          this.watchingUser_.remove(index);
          onChanged();
        } else {
          this.watchingUserBuilder_.remove(index);
        } 
        return this;
      }
      
      public WebWatchingUserInfoOuterClass.WebWatchingUserInfo.Builder getWatchingUserBuilder(int index) {
        return (WebWatchingUserInfoOuterClass.WebWatchingUserInfo.Builder)getWatchingUserFieldBuilder().getBuilder(index);
      }
      
      public WebWatchingUserInfoOuterClass.WebWatchingUserInfoOrBuilder getWatchingUserOrBuilder(int index) {
        if (this.watchingUserBuilder_ == null)
          return this.watchingUser_.get(index); 
        return (WebWatchingUserInfoOuterClass.WebWatchingUserInfoOrBuilder)this.watchingUserBuilder_.getMessageOrBuilder(index);
      }
      
      public List<? extends WebWatchingUserInfoOuterClass.WebWatchingUserInfoOrBuilder> getWatchingUserOrBuilderList() {
        if (this.watchingUserBuilder_ != null)
          return this.watchingUserBuilder_.getMessageOrBuilderList(); 
        return Collections.unmodifiableList((List)this.watchingUser_);
      }
      
      public WebWatchingUserInfoOuterClass.WebWatchingUserInfo.Builder addWatchingUserBuilder() {
        return (WebWatchingUserInfoOuterClass.WebWatchingUserInfo.Builder)getWatchingUserFieldBuilder().addBuilder((AbstractMessage)WebWatchingUserInfoOuterClass.WebWatchingUserInfo.getDefaultInstance());
      }
      
      public WebWatchingUserInfoOuterClass.WebWatchingUserInfo.Builder addWatchingUserBuilder(int index) {
        return (WebWatchingUserInfoOuterClass.WebWatchingUserInfo.Builder)getWatchingUserFieldBuilder().addBuilder(index, (AbstractMessage)WebWatchingUserInfoOuterClass.WebWatchingUserInfo.getDefaultInstance());
      }
      
      public List<WebWatchingUserInfoOuterClass.WebWatchingUserInfo.Builder> getWatchingUserBuilderList() {
        return getWatchingUserFieldBuilder().getBuilderList();
      }
      
      private RepeatedFieldBuilderV3<WebWatchingUserInfoOuterClass.WebWatchingUserInfo, WebWatchingUserInfoOuterClass.WebWatchingUserInfo.Builder, WebWatchingUserInfoOuterClass.WebWatchingUserInfoOrBuilder> getWatchingUserFieldBuilder() {
        if (this.watchingUserBuilder_ == null) {
          this.watchingUserBuilder_ = new RepeatedFieldBuilderV3(this.watchingUser_, ((this.bitField0_ & 0x1) != 0), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
          this.watchingUser_ = null;
        } 
        return this.watchingUserBuilder_;
      }
      
      public String getDisplayWatchingCount() {
        Object ref = this.displayWatchingCount_;
        if (!(ref instanceof String)) {
          ByteString bs = (ByteString)ref;
          String s = bs.toStringUtf8();
          this.displayWatchingCount_ = s;
          return s;
        } 
        return (String)ref;
      }
      
      public ByteString getDisplayWatchingCountBytes() {
        Object ref = this.displayWatchingCount_;
        if (ref instanceof String) {
          ByteString b = ByteString.copyFromUtf8((String)ref);
          this.displayWatchingCount_ = b;
          return b;
        } 
        return (ByteString)ref;
      }
      
      public Builder setDisplayWatchingCount(String value) {
        if (value == null)
          throw new NullPointerException(); 
        this.displayWatchingCount_ = value;
        this.bitField0_ |= 0x2;
        onChanged();
        return this;
      }
      
      public Builder clearDisplayWatchingCount() {
        this.displayWatchingCount_ = SCWebLiveWatchingUsersOuterClass.SCWebLiveWatchingUsers.getDefaultInstance().getDisplayWatchingCount();
        this.bitField0_ &= 0xFFFFFFFD;
        onChanged();
        return this;
      }
      
      public Builder setDisplayWatchingCountBytes(ByteString value) {
        if (value == null)
          throw new NullPointerException(); 
        SCWebLiveWatchingUsersOuterClass.SCWebLiveWatchingUsers.checkByteStringIsUtf8(value);
        this.displayWatchingCount_ = value;
        this.bitField0_ |= 0x2;
        onChanged();
        return this;
      }
      
      public long getPendingDuration() {
        return this.pendingDuration_;
      }
      
      public Builder setPendingDuration(long value) {
        this.pendingDuration_ = value;
        this.bitField0_ |= 0x4;
        onChanged();
        return this;
      }
      
      public Builder clearPendingDuration() {
        this.bitField0_ &= 0xFFFFFFFB;
        this.pendingDuration_ = 0L;
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
    
    private static final SCWebLiveWatchingUsers DEFAULT_INSTANCE = new SCWebLiveWatchingUsers();
    
    public static SCWebLiveWatchingUsers getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }
    
    private static final Parser<SCWebLiveWatchingUsers> PARSER = (Parser<SCWebLiveWatchingUsers>)new AbstractParser<SCWebLiveWatchingUsers>() {
        public SCWebLiveWatchingUsersOuterClass.SCWebLiveWatchingUsers parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
          SCWebLiveWatchingUsersOuterClass.SCWebLiveWatchingUsers.Builder builder = SCWebLiveWatchingUsersOuterClass.SCWebLiveWatchingUsers.newBuilder();
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
    
    public static Parser<SCWebLiveWatchingUsers> parser() {
      return PARSER;
    }
    
    public Parser<SCWebLiveWatchingUsers> getParserForType() {
      return PARSER;
    }
    
    public SCWebLiveWatchingUsers getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }
  }
  
  public static Descriptors.FileDescriptor getDescriptor() {
    return descriptor;
  }
  
  static {
    String[] descriptorData = { "\n\034SCWebLiveWatchingUsers.proto\032\031WebWatchingUserInfo.proto\"{\n\026SCWebLiveWatchingUsers\022*\n\fwatchingUser\030\001 \003(\0132\024.WebWatchingUserInfo\022\034\n\024displayWatchingCount\030\002 \001(\t\022\027\n\017pendingDuration\030\003 \001(\004B<\n:tech.ordinaryroad.live.chat.client.codec.kuaishou.protobufb\006proto3" };
    descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[] { WebWatchingUserInfoOuterClass.getDescriptor() });
    internal_static_SCWebLiveWatchingUsers_descriptor = getDescriptor().getMessageTypes().get(0);
    internal_static_SCWebLiveWatchingUsers_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_SCWebLiveWatchingUsers_descriptor, new String[] { "WatchingUser", "DisplayWatchingCount", "PendingDuration" });
    WebWatchingUserInfoOuterClass.getDescriptor();
  }
  
  public static interface SCWebLiveWatchingUsersOrBuilder extends MessageOrBuilder {
    List<WebWatchingUserInfoOuterClass.WebWatchingUserInfo> getWatchingUserList();
    
    WebWatchingUserInfoOuterClass.WebWatchingUserInfo getWatchingUser(int param1Int);
    
    int getWatchingUserCount();
    
    List<? extends WebWatchingUserInfoOuterClass.WebWatchingUserInfoOrBuilder> getWatchingUserOrBuilderList();
    
    WebWatchingUserInfoOuterClass.WebWatchingUserInfoOrBuilder getWatchingUserOrBuilder(int param1Int);
    
    String getDisplayWatchingCount();
    
    ByteString getDisplayWatchingCountBytes();
    
    long getPendingDuration();
  }
}
