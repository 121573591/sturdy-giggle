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
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class RoomRankMessage extends GeneratedMessageV3 implements RoomRankMessageOrBuilder {
  private static final long serialVersionUID = 0L;
  
  private int bitField0_;
  
  public static final int COMMON_FIELD_NUMBER = 1;
  
  private Common common_;
  
  public static final int RANKSLIST_FIELD_NUMBER = 2;
  
  private List<RoomRank> ranksList_;
  
  private byte memoizedIsInitialized;
  
  private RoomRankMessage(GeneratedMessageV3.Builder<?> builder) {
    super(builder);
    this.memoizedIsInitialized = -1;
  }
  
  private RoomRankMessage() {
    this.memoizedIsInitialized = -1;
    this.ranksList_ = Collections.emptyList();
  }
  
  protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
    return new RoomRankMessage();
  }
  
  public static final Descriptors.Descriptor getDescriptor() {
    return Douyin.internal_static_RoomRankMessage_descriptor;
  }
  
  protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
    return Douyin.internal_static_RoomRankMessage_fieldAccessorTable.ensureFieldAccessorsInitialized(RoomRankMessage.class, Builder.class);
  }
  
  public static final class RoomRank extends GeneratedMessageV3 implements RoomRankOrBuilder {
    private static final long serialVersionUID = 0L;
    
    private int bitField0_;
    
    public static final int USER_FIELD_NUMBER = 1;
    
    private User user_;
    
    public static final int SCORESTR_FIELD_NUMBER = 2;
    
    private volatile Object scoreStr_;
    
    public static final int PROFILEHIDDEN_FIELD_NUMBER = 3;
    
    private boolean profileHidden_;
    
    private byte memoizedIsInitialized;
    
    private RoomRank(GeneratedMessageV3.Builder<?> builder) {
      super(builder);
      this.scoreStr_ = "";
      this.profileHidden_ = false;
      this.memoizedIsInitialized = -1;
    }
    
    private RoomRank() {
      this.scoreStr_ = "";
      this.profileHidden_ = false;
      this.memoizedIsInitialized = -1;
      this.scoreStr_ = "";
    }
    
    protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
      return new RoomRank();
    }
    
    public static final Descriptors.Descriptor getDescriptor() {
      return Douyin.internal_static_RoomRankMessage_RoomRank_descriptor;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return Douyin.internal_static_RoomRankMessage_RoomRank_fieldAccessorTable.ensureFieldAccessorsInitialized(RoomRank.class, Builder.class);
    }
    
    public boolean hasUser() {
      return ((this.bitField0_ & 0x1) != 0);
    }
    
    public User getUser() {
      return (this.user_ == null) ? User.getDefaultInstance() : this.user_;
    }
    
    public UserOrBuilder getUserOrBuilder() {
      return (this.user_ == null) ? User.getDefaultInstance() : this.user_;
    }
    
    public String getScoreStr() {
      Object ref = this.scoreStr_;
      if (ref instanceof String)
        return (String)ref; 
      ByteString bs = (ByteString)ref;
      String s = bs.toStringUtf8();
      this.scoreStr_ = s;
      return s;
    }
    
    public ByteString getScoreStrBytes() {
      Object ref = this.scoreStr_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.scoreStr_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public boolean getProfileHidden() {
      return this.profileHidden_;
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
      if (!GeneratedMessageV3.isStringEmpty(this.scoreStr_))
        GeneratedMessageV3.writeString(output, 2, this.scoreStr_); 
      if (this.profileHidden_)
        output.writeBool(3, this.profileHidden_); 
      getUnknownFields().writeTo(output);
    }
    
    public int getSerializedSize() {
      int size = this.memoizedSize;
      if (size != -1)
        return size; 
      size = 0;
      if ((this.bitField0_ & 0x1) != 0)
        size += CodedOutputStream.computeMessageSize(1, (MessageLite)getUser()); 
      if (!GeneratedMessageV3.isStringEmpty(this.scoreStr_))
        size += GeneratedMessageV3.computeStringSize(2, this.scoreStr_); 
      if (this.profileHidden_)
        size += CodedOutputStream.computeBoolSize(3, this.profileHidden_); 
      size += getUnknownFields().getSerializedSize();
      this.memoizedSize = size;
      return size;
    }
    
    public boolean equals(Object obj) {
      if (obj == this)
        return true; 
      if (!(obj instanceof RoomRank))
        return super.equals(obj); 
      RoomRank other = (RoomRank)obj;
      if (hasUser() != other.hasUser())
        return false; 
      if (hasUser() && !getUser().equals(other.getUser()))
        return false; 
      if (!getScoreStr().equals(other.getScoreStr()))
        return false; 
      if (getProfileHidden() != other.getProfileHidden())
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
      hash = 53 * hash + getScoreStr().hashCode();
      hash = 37 * hash + 3;
      hash = 53 * hash + Internal.hashBoolean(getProfileHidden());
      hash = 29 * hash + getUnknownFields().hashCode();
      this.memoizedHashCode = hash;
      return hash;
    }
    
    public static RoomRank parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (RoomRank)PARSER.parseFrom(data);
    }
    
    public static RoomRank parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (RoomRank)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static RoomRank parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (RoomRank)PARSER.parseFrom(data);
    }
    
    public static RoomRank parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (RoomRank)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static RoomRank parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (RoomRank)PARSER.parseFrom(data);
    }
    
    public static RoomRank parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (RoomRank)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static RoomRank parseFrom(InputStream input) throws IOException {
      return (RoomRank)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static RoomRank parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (RoomRank)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static RoomRank parseDelimitedFrom(InputStream input) throws IOException {
      return (RoomRank)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }
    
    public static RoomRank parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (RoomRank)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static RoomRank parseFrom(CodedInputStream input) throws IOException {
      return (RoomRank)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static RoomRank parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (RoomRank)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public Builder newBuilderForType() {
      return newBuilder();
    }
    
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    
    public static Builder newBuilder(RoomRank prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    
    public Builder toBuilder() {
      return (this == DEFAULT_INSTANCE) ? new Builder() : (new Builder()).mergeFrom(this);
    }
    
    protected Builder newBuilderForType(GeneratedMessageV3.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements RoomRankMessage.RoomRankOrBuilder {
      private int bitField0_;
      
      private User user_;
      
      private SingleFieldBuilderV3<User, User.Builder, UserOrBuilder> userBuilder_;
      
      private Object scoreStr_;
      
      private boolean profileHidden_;
      
      public static final Descriptors.Descriptor getDescriptor() {
        return Douyin.internal_static_RoomRankMessage_RoomRank_descriptor;
      }
      
      protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return Douyin.internal_static_RoomRankMessage_RoomRank_fieldAccessorTable.ensureFieldAccessorsInitialized(RoomRankMessage.RoomRank.class, Builder.class);
      }
      
      private Builder() {
        this.scoreStr_ = "";
        maybeForceBuilderInitialization();
      }
      
      private Builder(GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        this.scoreStr_ = "";
        maybeForceBuilderInitialization();
      }
      
      private void maybeForceBuilderInitialization() {
        if (RoomRankMessage.RoomRank.alwaysUseFieldBuilders)
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
        this.scoreStr_ = "";
        this.profileHidden_ = false;
        return this;
      }
      
      public Descriptors.Descriptor getDescriptorForType() {
        return Douyin.internal_static_RoomRankMessage_RoomRank_descriptor;
      }
      
      public RoomRankMessage.RoomRank getDefaultInstanceForType() {
        return RoomRankMessage.RoomRank.getDefaultInstance();
      }
      
      public RoomRankMessage.RoomRank build() {
        RoomRankMessage.RoomRank result = buildPartial();
        if (!result.isInitialized())
          throw newUninitializedMessageException(result); 
        return result;
      }
      
      public RoomRankMessage.RoomRank buildPartial() {
        RoomRankMessage.RoomRank result = new RoomRankMessage.RoomRank(this);
        if (this.bitField0_ != 0)
          buildPartial0(result); 
        onBuilt();
        return result;
      }
      
      private void buildPartial0(RoomRankMessage.RoomRank result) {
        int from_bitField0_ = this.bitField0_;
        int to_bitField0_ = 0;
        if ((from_bitField0_ & 0x1) != 0) {
          result.user_ = (this.userBuilder_ == null) ? this.user_ : (User)this.userBuilder_.build();
          to_bitField0_ |= 0x1;
        } 
        if ((from_bitField0_ & 0x2) != 0)
          result.scoreStr_ = this.scoreStr_; 
        if ((from_bitField0_ & 0x4) != 0)
          result.profileHidden_ = this.profileHidden_; 
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
        if (other instanceof RoomRankMessage.RoomRank)
          return mergeFrom((RoomRankMessage.RoomRank)other); 
        super.mergeFrom(other);
        return this;
      }
      
      public Builder mergeFrom(RoomRankMessage.RoomRank other) {
        if (other == RoomRankMessage.RoomRank.getDefaultInstance())
          return this; 
        if (other.hasUser())
          mergeUser(other.getUser()); 
        if (!other.getScoreStr().isEmpty()) {
          this.scoreStr_ = other.scoreStr_;
          this.bitField0_ |= 0x2;
          onChanged();
        } 
        if (other.getProfileHidden())
          setProfileHidden(other.getProfileHidden()); 
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
              case 18:
                this.scoreStr_ = input.readStringRequireUtf8();
                this.bitField0_ |= 0x2;
                continue;
              case 24:
                this.profileHidden_ = input.readBool();
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
      
      public boolean hasUser() {
        return ((this.bitField0_ & 0x1) != 0);
      }
      
      public User getUser() {
        if (this.userBuilder_ == null)
          return (this.user_ == null) ? User.getDefaultInstance() : this.user_; 
        return (User)this.userBuilder_.getMessage();
      }
      
      public Builder setUser(User value) {
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
      
      public Builder setUser(User.Builder builderForValue) {
        if (this.userBuilder_ == null) {
          this.user_ = builderForValue.build();
        } else {
          this.userBuilder_.setMessage((AbstractMessage)builderForValue.build());
        } 
        this.bitField0_ |= 0x1;
        onChanged();
        return this;
      }
      
      public Builder mergeUser(User value) {
        if (this.userBuilder_ == null) {
          if ((this.bitField0_ & 0x1) != 0 && this.user_ != null && this.user_ != User.getDefaultInstance()) {
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
      
      public User.Builder getUserBuilder() {
        this.bitField0_ |= 0x1;
        onChanged();
        return (User.Builder)getUserFieldBuilder().getBuilder();
      }
      
      public UserOrBuilder getUserOrBuilder() {
        if (this.userBuilder_ != null)
          return (UserOrBuilder)this.userBuilder_.getMessageOrBuilder(); 
        return (this.user_ == null) ? User.getDefaultInstance() : this.user_;
      }
      
      private SingleFieldBuilderV3<User, User.Builder, UserOrBuilder> getUserFieldBuilder() {
        if (this.userBuilder_ == null) {
          this.userBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getUser(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
          this.user_ = null;
        } 
        return this.userBuilder_;
      }
      
      public String getScoreStr() {
        Object ref = this.scoreStr_;
        if (!(ref instanceof String)) {
          ByteString bs = (ByteString)ref;
          String s = bs.toStringUtf8();
          this.scoreStr_ = s;
          return s;
        } 
        return (String)ref;
      }
      
      public ByteString getScoreStrBytes() {
        Object ref = this.scoreStr_;
        if (ref instanceof String) {
          ByteString b = ByteString.copyFromUtf8((String)ref);
          this.scoreStr_ = b;
          return b;
        } 
        return (ByteString)ref;
      }
      
      public Builder setScoreStr(String value) {
        if (value == null)
          throw new NullPointerException(); 
        this.scoreStr_ = value;
        this.bitField0_ |= 0x2;
        onChanged();
        return this;
      }
      
      public Builder clearScoreStr() {
        this.scoreStr_ = RoomRankMessage.RoomRank.getDefaultInstance().getScoreStr();
        this.bitField0_ &= 0xFFFFFFFD;
        onChanged();
        return this;
      }
      
      public Builder setScoreStrBytes(ByteString value) {
        if (value == null)
          throw new NullPointerException(); 
        RoomRankMessage.RoomRank.checkByteStringIsUtf8(value);
        this.scoreStr_ = value;
        this.bitField0_ |= 0x2;
        onChanged();
        return this;
      }
      
      public boolean getProfileHidden() {
        return this.profileHidden_;
      }
      
      public Builder setProfileHidden(boolean value) {
        this.profileHidden_ = value;
        this.bitField0_ |= 0x4;
        onChanged();
        return this;
      }
      
      public Builder clearProfileHidden() {
        this.bitField0_ &= 0xFFFFFFFB;
        this.profileHidden_ = false;
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
    
    private static final RoomRank DEFAULT_INSTANCE = new RoomRank();
    
    public static RoomRank getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }
    
    private static final Parser<RoomRank> PARSER = (Parser<RoomRank>)new AbstractParser<RoomRank>() {
        public RoomRankMessage.RoomRank parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
          RoomRankMessage.RoomRank.Builder builder = RoomRankMessage.RoomRank.newBuilder();
          try {
            builder.mergeFrom(input, extensionRegistry);
          } catch (InvalidProtocolBufferException e) {
            throw e.setUnfinishedMessage(builder.buildPartial());
          } catch (UninitializedMessageException e) {
            throw e.asInvalidProtocolBufferException().setUnfinishedMessage(builder.buildPartial());
          } catch (IOException e) {
            throw (new InvalidProtocolBufferException(e)).setUnfinishedMessage(builder.buildPartial());
          } 
          return builder.buildPartial();
        }
      };
    
    public static Parser<RoomRank> parser() {
      return PARSER;
    }
    
    public Parser<RoomRank> getParserForType() {
      return PARSER;
    }
    
    public RoomRank getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }
  }
  
  public boolean hasCommon() {
    return ((this.bitField0_ & 0x1) != 0);
  }
  
  public Common getCommon() {
    return (this.common_ == null) ? Common.getDefaultInstance() : this.common_;
  }
  
  public CommonOrBuilder getCommonOrBuilder() {
    return (this.common_ == null) ? Common.getDefaultInstance() : this.common_;
  }
  
  public List<RoomRank> getRanksListList() {
    return this.ranksList_;
  }
  
  public List<? extends RoomRankOrBuilder> getRanksListOrBuilderList() {
    return (List)this.ranksList_;
  }
  
  public int getRanksListCount() {
    return this.ranksList_.size();
  }
  
  public RoomRank getRanksList(int index) {
    return this.ranksList_.get(index);
  }
  
  public RoomRankOrBuilder getRanksListOrBuilder(int index) {
    return this.ranksList_.get(index);
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
      output.writeMessage(1, (MessageLite)getCommon()); 
    for (int i = 0; i < this.ranksList_.size(); i++)
      output.writeMessage(2, (MessageLite)this.ranksList_.get(i)); 
    getUnknownFields().writeTo(output);
  }
  
  public int getSerializedSize() {
    int size = this.memoizedSize;
    if (size != -1)
      return size; 
    size = 0;
    if ((this.bitField0_ & 0x1) != 0)
      size += 
        CodedOutputStream.computeMessageSize(1, (MessageLite)getCommon()); 
    for (int i = 0; i < this.ranksList_.size(); i++)
      size += 
        CodedOutputStream.computeMessageSize(2, (MessageLite)this.ranksList_.get(i)); 
    size += getUnknownFields().getSerializedSize();
    this.memoizedSize = size;
    return size;
  }
  
  public boolean equals(Object obj) {
    if (obj == this)
      return true; 
    if (!(obj instanceof RoomRankMessage))
      return super.equals(obj); 
    RoomRankMessage other = (RoomRankMessage)obj;
    if (hasCommon() != other.hasCommon())
      return false; 
    if (hasCommon() && 
      
      !getCommon().equals(other.getCommon()))
      return false; 
    if (!getRanksListList().equals(other.getRanksListList()))
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
    if (hasCommon()) {
      hash = 37 * hash + 1;
      hash = 53 * hash + getCommon().hashCode();
    } 
    if (getRanksListCount() > 0) {
      hash = 37 * hash + 2;
      hash = 53 * hash + getRanksListList().hashCode();
    } 
    hash = 29 * hash + getUnknownFields().hashCode();
    this.memoizedHashCode = hash;
    return hash;
  }
  
  public static RoomRankMessage parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
    return (RoomRankMessage)PARSER.parseFrom(data);
  }
  
  public static RoomRankMessage parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (RoomRankMessage)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static RoomRankMessage parseFrom(ByteString data) throws InvalidProtocolBufferException {
    return (RoomRankMessage)PARSER.parseFrom(data);
  }
  
  public static RoomRankMessage parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (RoomRankMessage)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static RoomRankMessage parseFrom(byte[] data) throws InvalidProtocolBufferException {
    return (RoomRankMessage)PARSER.parseFrom(data);
  }
  
  public static RoomRankMessage parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (RoomRankMessage)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static RoomRankMessage parseFrom(InputStream input) throws IOException {
    return 
      (RoomRankMessage)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static RoomRankMessage parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (RoomRankMessage)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static RoomRankMessage parseDelimitedFrom(InputStream input) throws IOException {
    return 
      (RoomRankMessage)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
  }
  
  public static RoomRankMessage parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (RoomRankMessage)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static RoomRankMessage parseFrom(CodedInputStream input) throws IOException {
    return 
      (RoomRankMessage)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static RoomRankMessage parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (RoomRankMessage)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public Builder newBuilderForType() {
    return newBuilder();
  }
  
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  
  public static Builder newBuilder(RoomRankMessage prototype) {
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
  
  public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements RoomRankMessageOrBuilder {
    private int bitField0_;
    
    private Common common_;
    
    private SingleFieldBuilderV3<Common, Common.Builder, CommonOrBuilder> commonBuilder_;
    
    private List<RoomRankMessage.RoomRank> ranksList_;
    
    private RepeatedFieldBuilderV3<RoomRankMessage.RoomRank, RoomRankMessage.RoomRank.Builder, RoomRankMessage.RoomRankOrBuilder> ranksListBuilder_;
    
    public static final Descriptors.Descriptor getDescriptor() {
      return Douyin.internal_static_RoomRankMessage_descriptor;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return Douyin.internal_static_RoomRankMessage_fieldAccessorTable
        .ensureFieldAccessorsInitialized(RoomRankMessage.class, Builder.class);
    }
    
    private Builder() {
      this
        .ranksList_ = Collections.emptyList();
      maybeForceBuilderInitialization();
    }
    
    private Builder(GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      this.ranksList_ = Collections.emptyList();
      maybeForceBuilderInitialization();
    }
    
    private void maybeForceBuilderInitialization() {
      if (RoomRankMessage.alwaysUseFieldBuilders) {
        getCommonFieldBuilder();
        getRanksListFieldBuilder();
      } 
    }
    
    public Builder clear() {
      super.clear();
      this.bitField0_ = 0;
      this.common_ = null;
      if (this.commonBuilder_ != null) {
        this.commonBuilder_.dispose();
        this.commonBuilder_ = null;
      } 
      if (this.ranksListBuilder_ == null) {
        this.ranksList_ = Collections.emptyList();
      } else {
        this.ranksList_ = null;
        this.ranksListBuilder_.clear();
      } 
      this.bitField0_ &= 0xFFFFFFFD;
      return this;
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
      return Douyin.internal_static_RoomRankMessage_descriptor;
    }
    
    public RoomRankMessage getDefaultInstanceForType() {
      return RoomRankMessage.getDefaultInstance();
    }
    
    public RoomRankMessage build() {
      RoomRankMessage result = buildPartial();
      if (!result.isInitialized())
        throw newUninitializedMessageException(result); 
      return result;
    }
    
    public RoomRankMessage buildPartial() {
      RoomRankMessage result = new RoomRankMessage(this);
      buildPartialRepeatedFields(result);
      if (this.bitField0_ != 0)
        buildPartial0(result); 
      onBuilt();
      return result;
    }
    
    private void buildPartialRepeatedFields(RoomRankMessage result) {
      if (this.ranksListBuilder_ == null) {
        if ((this.bitField0_ & 0x2) != 0) {
          this.ranksList_ = Collections.unmodifiableList(this.ranksList_);
          this.bitField0_ &= 0xFFFFFFFD;
        } 
        result.ranksList_ = this.ranksList_;
      } else {
        result.ranksList_ = this.ranksListBuilder_.build();
      } 
    }
    
    private void buildPartial0(RoomRankMessage result) {
      int from_bitField0_ = this.bitField0_;
      int to_bitField0_ = 0;
      if ((from_bitField0_ & 0x1) != 0) {
        result.common_ = (this.commonBuilder_ == null) ? this.common_ : (Common)this.commonBuilder_.build();
        to_bitField0_ |= 0x1;
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
      if (other instanceof RoomRankMessage)
        return mergeFrom((RoomRankMessage)other); 
      super.mergeFrom(other);
      return this;
    }
    
    public Builder mergeFrom(RoomRankMessage other) {
      if (other == RoomRankMessage.getDefaultInstance())
        return this; 
      if (other.hasCommon())
        mergeCommon(other.getCommon()); 
      if (this.ranksListBuilder_ == null) {
        if (!other.ranksList_.isEmpty()) {
          if (this.ranksList_.isEmpty()) {
            this.ranksList_ = other.ranksList_;
            this.bitField0_ &= 0xFFFFFFFD;
          } else {
            ensureRanksListIsMutable();
            this.ranksList_.addAll(other.ranksList_);
          } 
          onChanged();
        } 
      } else if (!other.ranksList_.isEmpty()) {
        if (this.ranksListBuilder_.isEmpty()) {
          this.ranksListBuilder_.dispose();
          this.ranksListBuilder_ = null;
          this.ranksList_ = other.ranksList_;
          this.bitField0_ &= 0xFFFFFFFD;
          this.ranksListBuilder_ = RoomRankMessage.alwaysUseFieldBuilders ? getRanksListFieldBuilder() : null;
        } else {
          this.ranksListBuilder_.addAllMessages(other.ranksList_);
        } 
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
          RoomRankMessage.RoomRank m;
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              continue;
            case 10:
              input.readMessage((MessageLite.Builder)getCommonFieldBuilder().getBuilder(), extensionRegistry);
              this.bitField0_ |= 0x1;
              continue;
            case 18:
              m = (RoomRankMessage.RoomRank)input.readMessage(RoomRankMessage.RoomRank.parser(), extensionRegistry);
              if (this.ranksListBuilder_ == null) {
                ensureRanksListIsMutable();
                this.ranksList_.add(m);
                continue;
              } 
              this.ranksListBuilder_.addMessage((AbstractMessage)m);
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
    
    public boolean hasCommon() {
      return ((this.bitField0_ & 0x1) != 0);
    }
    
    public Common getCommon() {
      if (this.commonBuilder_ == null)
        return (this.common_ == null) ? Common.getDefaultInstance() : this.common_; 
      return (Common)this.commonBuilder_.getMessage();
    }
    
    public Builder setCommon(Common value) {
      if (this.commonBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        this.common_ = value;
      } else {
        this.commonBuilder_.setMessage((AbstractMessage)value);
      } 
      this.bitField0_ |= 0x1;
      onChanged();
      return this;
    }
    
    public Builder setCommon(Common.Builder builderForValue) {
      if (this.commonBuilder_ == null) {
        this.common_ = builderForValue.build();
      } else {
        this.commonBuilder_.setMessage((AbstractMessage)builderForValue.build());
      } 
      this.bitField0_ |= 0x1;
      onChanged();
      return this;
    }
    
    public Builder mergeCommon(Common value) {
      if (this.commonBuilder_ == null) {
        if ((this.bitField0_ & 0x1) != 0 && this.common_ != null && this.common_ != Common.getDefaultInstance()) {
          getCommonBuilder().mergeFrom(value);
        } else {
          this.common_ = value;
        } 
      } else {
        this.commonBuilder_.mergeFrom((AbstractMessage)value);
      } 
      if (this.common_ != null) {
        this.bitField0_ |= 0x1;
        onChanged();
      } 
      return this;
    }
    
    public Builder clearCommon() {
      this.bitField0_ &= 0xFFFFFFFE;
      this.common_ = null;
      if (this.commonBuilder_ != null) {
        this.commonBuilder_.dispose();
        this.commonBuilder_ = null;
      } 
      onChanged();
      return this;
    }
    
    public Common.Builder getCommonBuilder() {
      this.bitField0_ |= 0x1;
      onChanged();
      return (Common.Builder)getCommonFieldBuilder().getBuilder();
    }
    
    public CommonOrBuilder getCommonOrBuilder() {
      if (this.commonBuilder_ != null)
        return (CommonOrBuilder)this.commonBuilder_.getMessageOrBuilder(); 
      return (this.common_ == null) ? Common.getDefaultInstance() : this.common_;
    }
    
    private SingleFieldBuilderV3<Common, Common.Builder, CommonOrBuilder> getCommonFieldBuilder() {
      if (this.commonBuilder_ == null) {
        this.commonBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getCommon(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.common_ = null;
      } 
      return this.commonBuilder_;
    }
    
    private void ensureRanksListIsMutable() {
      if ((this.bitField0_ & 0x2) == 0) {
        this.ranksList_ = new ArrayList<>(this.ranksList_);
        this.bitField0_ |= 0x2;
      } 
    }
    
    public List<RoomRankMessage.RoomRank> getRanksListList() {
      if (this.ranksListBuilder_ == null)
        return Collections.unmodifiableList(this.ranksList_); 
      return this.ranksListBuilder_.getMessageList();
    }
    
    public int getRanksListCount() {
      if (this.ranksListBuilder_ == null)
        return this.ranksList_.size(); 
      return this.ranksListBuilder_.getCount();
    }
    
    public RoomRankMessage.RoomRank getRanksList(int index) {
      if (this.ranksListBuilder_ == null)
        return this.ranksList_.get(index); 
      return (RoomRankMessage.RoomRank)this.ranksListBuilder_.getMessage(index);
    }
    
    public Builder setRanksList(int index, RoomRankMessage.RoomRank value) {
      if (this.ranksListBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        ensureRanksListIsMutable();
        this.ranksList_.set(index, value);
        onChanged();
      } else {
        this.ranksListBuilder_.setMessage(index, (AbstractMessage)value);
      } 
      return this;
    }
    
    public Builder setRanksList(int index, RoomRankMessage.RoomRank.Builder builderForValue) {
      if (this.ranksListBuilder_ == null) {
        ensureRanksListIsMutable();
        this.ranksList_.set(index, builderForValue.build());
        onChanged();
      } else {
        this.ranksListBuilder_.setMessage(index, (AbstractMessage)builderForValue.build());
      } 
      return this;
    }
    
    public Builder addRanksList(RoomRankMessage.RoomRank value) {
      if (this.ranksListBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        ensureRanksListIsMutable();
        this.ranksList_.add(value);
        onChanged();
      } else {
        this.ranksListBuilder_.addMessage((AbstractMessage)value);
      } 
      return this;
    }
    
    public Builder addRanksList(int index, RoomRankMessage.RoomRank value) {
      if (this.ranksListBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        ensureRanksListIsMutable();
        this.ranksList_.add(index, value);
        onChanged();
      } else {
        this.ranksListBuilder_.addMessage(index, (AbstractMessage)value);
      } 
      return this;
    }
    
    public Builder addRanksList(RoomRankMessage.RoomRank.Builder builderForValue) {
      if (this.ranksListBuilder_ == null) {
        ensureRanksListIsMutable();
        this.ranksList_.add(builderForValue.build());
        onChanged();
      } else {
        this.ranksListBuilder_.addMessage((AbstractMessage)builderForValue.build());
      } 
      return this;
    }
    
    public Builder addRanksList(int index, RoomRankMessage.RoomRank.Builder builderForValue) {
      if (this.ranksListBuilder_ == null) {
        ensureRanksListIsMutable();
        this.ranksList_.add(index, builderForValue.build());
        onChanged();
      } else {
        this.ranksListBuilder_.addMessage(index, (AbstractMessage)builderForValue.build());
      } 
      return this;
    }
    
    public Builder addAllRanksList(Iterable<? extends RoomRankMessage.RoomRank> values) {
      if (this.ranksListBuilder_ == null) {
        ensureRanksListIsMutable();
        AbstractMessageLite.Builder.addAll(values, this.ranksList_);
        onChanged();
      } else {
        this.ranksListBuilder_.addAllMessages(values);
      } 
      return this;
    }
    
    public Builder clearRanksList() {
      if (this.ranksListBuilder_ == null) {
        this.ranksList_ = Collections.emptyList();
        this.bitField0_ &= 0xFFFFFFFD;
        onChanged();
      } else {
        this.ranksListBuilder_.clear();
      } 
      return this;
    }
    
    public Builder removeRanksList(int index) {
      if (this.ranksListBuilder_ == null) {
        ensureRanksListIsMutable();
        this.ranksList_.remove(index);
        onChanged();
      } else {
        this.ranksListBuilder_.remove(index);
      } 
      return this;
    }
    
    public RoomRankMessage.RoomRank.Builder getRanksListBuilder(int index) {
      return (RoomRankMessage.RoomRank.Builder)getRanksListFieldBuilder().getBuilder(index);
    }
    
    public RoomRankMessage.RoomRankOrBuilder getRanksListOrBuilder(int index) {
      if (this.ranksListBuilder_ == null)
        return this.ranksList_.get(index); 
      return (RoomRankMessage.RoomRankOrBuilder)this.ranksListBuilder_.getMessageOrBuilder(index);
    }
    
    public List<? extends RoomRankMessage.RoomRankOrBuilder> getRanksListOrBuilderList() {
      if (this.ranksListBuilder_ != null)
        return this.ranksListBuilder_.getMessageOrBuilderList(); 
      return Collections.unmodifiableList((List)this.ranksList_);
    }
    
    public RoomRankMessage.RoomRank.Builder addRanksListBuilder() {
      return (RoomRankMessage.RoomRank.Builder)getRanksListFieldBuilder().addBuilder(
          (AbstractMessage)RoomRankMessage.RoomRank.getDefaultInstance());
    }
    
    public RoomRankMessage.RoomRank.Builder addRanksListBuilder(int index) {
      return (RoomRankMessage.RoomRank.Builder)getRanksListFieldBuilder().addBuilder(index, 
          (AbstractMessage)RoomRankMessage.RoomRank.getDefaultInstance());
    }
    
    public List<RoomRankMessage.RoomRank.Builder> getRanksListBuilderList() {
      return getRanksListFieldBuilder().getBuilderList();
    }
    
    private RepeatedFieldBuilderV3<RoomRankMessage.RoomRank, RoomRankMessage.RoomRank.Builder, RoomRankMessage.RoomRankOrBuilder> getRanksListFieldBuilder() {
      if (this.ranksListBuilder_ == null) {
        this
          
          .ranksListBuilder_ = new RepeatedFieldBuilderV3(this.ranksList_, ((this.bitField0_ & 0x2) != 0), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.ranksList_ = null;
      } 
      return this.ranksListBuilder_;
    }
    
    public final Builder setUnknownFields(UnknownFieldSet unknownFields) {
      return (Builder)super.setUnknownFields(unknownFields);
    }
    
    public final Builder mergeUnknownFields(UnknownFieldSet unknownFields) {
      return (Builder)super.mergeUnknownFields(unknownFields);
    }
  }
  
  private static final RoomRankMessage DEFAULT_INSTANCE = new RoomRankMessage();
  
  public static RoomRankMessage getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }
  
  private static final Parser<RoomRankMessage> PARSER = (Parser<RoomRankMessage>)new AbstractParser<RoomRankMessage>() {
      public RoomRankMessage parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        RoomRankMessage.Builder builder = RoomRankMessage.newBuilder();
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
  
  public static Parser<RoomRankMessage> parser() {
    return PARSER;
  }
  
  public Parser<RoomRankMessage> getParserForType() {
    return PARSER;
  }
  
  public RoomRankMessage getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }
  
  public static interface RoomRankOrBuilder extends MessageOrBuilder {
    boolean hasUser();
    
    User getUser();
    
    UserOrBuilder getUserOrBuilder();
    
    String getScoreStr();
    
    ByteString getScoreStrBytes();
    
    boolean getProfileHidden();
  }
}
