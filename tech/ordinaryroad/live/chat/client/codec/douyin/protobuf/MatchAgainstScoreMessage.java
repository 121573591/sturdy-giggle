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
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.MessageLite;
import com.google.protobuf.Parser;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class MatchAgainstScoreMessage extends GeneratedMessageV3 implements MatchAgainstScoreMessageOrBuilder {
  private static final long serialVersionUID = 0L;
  
  private int bitField0_;
  
  public static final int COMMON_FIELD_NUMBER = 1;
  
  private Common common_;
  
  public static final int AGAINST_FIELD_NUMBER = 2;
  
  private Against against_;
  
  public static final int MATCHSTATUS_FIELD_NUMBER = 3;
  
  private int matchStatus_;
  
  public static final int DISPLAYSTATUS_FIELD_NUMBER = 4;
  
  private int displayStatus_;
  
  private byte memoizedIsInitialized;
  
  private MatchAgainstScoreMessage(GeneratedMessageV3.Builder<?> builder) {
    super(builder);
    this.matchStatus_ = 0;
    this.displayStatus_ = 0;
    this.memoizedIsInitialized = -1;
  }
  
  private MatchAgainstScoreMessage() {
    this.matchStatus_ = 0;
    this.displayStatus_ = 0;
    this.memoizedIsInitialized = -1;
  }
  
  protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
    return new MatchAgainstScoreMessage();
  }
  
  public static final Descriptors.Descriptor getDescriptor() {
    return Douyin.internal_static_MatchAgainstScoreMessage_descriptor;
  }
  
  protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
    return Douyin.internal_static_MatchAgainstScoreMessage_fieldAccessorTable.ensureFieldAccessorsInitialized(MatchAgainstScoreMessage.class, Builder.class);
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
  
  public boolean hasAgainst() {
    return ((this.bitField0_ & 0x2) != 0);
  }
  
  public Against getAgainst() {
    return (this.against_ == null) ? Against.getDefaultInstance() : this.against_;
  }
  
  public AgainstOrBuilder getAgainstOrBuilder() {
    return (this.against_ == null) ? Against.getDefaultInstance() : this.against_;
  }
  
  public int getMatchStatus() {
    return this.matchStatus_;
  }
  
  public int getDisplayStatus() {
    return this.displayStatus_;
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
    if ((this.bitField0_ & 0x2) != 0)
      output.writeMessage(2, (MessageLite)getAgainst()); 
    if (this.matchStatus_ != 0)
      output.writeUInt32(3, this.matchStatus_); 
    if (this.displayStatus_ != 0)
      output.writeUInt32(4, this.displayStatus_); 
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
    if ((this.bitField0_ & 0x2) != 0)
      size += 
        CodedOutputStream.computeMessageSize(2, (MessageLite)getAgainst()); 
    if (this.matchStatus_ != 0)
      size += 
        CodedOutputStream.computeUInt32Size(3, this.matchStatus_); 
    if (this.displayStatus_ != 0)
      size += 
        CodedOutputStream.computeUInt32Size(4, this.displayStatus_); 
    size += getUnknownFields().getSerializedSize();
    this.memoizedSize = size;
    return size;
  }
  
  public boolean equals(Object obj) {
    if (obj == this)
      return true; 
    if (!(obj instanceof MatchAgainstScoreMessage))
      return super.equals(obj); 
    MatchAgainstScoreMessage other = (MatchAgainstScoreMessage)obj;
    if (hasCommon() != other.hasCommon())
      return false; 
    if (hasCommon() && 
      
      !getCommon().equals(other.getCommon()))
      return false; 
    if (hasAgainst() != other.hasAgainst())
      return false; 
    if (hasAgainst() && 
      
      !getAgainst().equals(other.getAgainst()))
      return false; 
    if (getMatchStatus() != other
      .getMatchStatus())
      return false; 
    if (getDisplayStatus() != other
      .getDisplayStatus())
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
    if (hasAgainst()) {
      hash = 37 * hash + 2;
      hash = 53 * hash + getAgainst().hashCode();
    } 
    hash = 37 * hash + 3;
    hash = 53 * hash + getMatchStatus();
    hash = 37 * hash + 4;
    hash = 53 * hash + getDisplayStatus();
    hash = 29 * hash + getUnknownFields().hashCode();
    this.memoizedHashCode = hash;
    return hash;
  }
  
  public static MatchAgainstScoreMessage parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
    return (MatchAgainstScoreMessage)PARSER.parseFrom(data);
  }
  
  public static MatchAgainstScoreMessage parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (MatchAgainstScoreMessage)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static MatchAgainstScoreMessage parseFrom(ByteString data) throws InvalidProtocolBufferException {
    return (MatchAgainstScoreMessage)PARSER.parseFrom(data);
  }
  
  public static MatchAgainstScoreMessage parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (MatchAgainstScoreMessage)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static MatchAgainstScoreMessage parseFrom(byte[] data) throws InvalidProtocolBufferException {
    return (MatchAgainstScoreMessage)PARSER.parseFrom(data);
  }
  
  public static MatchAgainstScoreMessage parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (MatchAgainstScoreMessage)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static MatchAgainstScoreMessage parseFrom(InputStream input) throws IOException {
    return 
      (MatchAgainstScoreMessage)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static MatchAgainstScoreMessage parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (MatchAgainstScoreMessage)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static MatchAgainstScoreMessage parseDelimitedFrom(InputStream input) throws IOException {
    return 
      (MatchAgainstScoreMessage)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
  }
  
  public static MatchAgainstScoreMessage parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (MatchAgainstScoreMessage)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static MatchAgainstScoreMessage parseFrom(CodedInputStream input) throws IOException {
    return 
      (MatchAgainstScoreMessage)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static MatchAgainstScoreMessage parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (MatchAgainstScoreMessage)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public Builder newBuilderForType() {
    return newBuilder();
  }
  
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  
  public static Builder newBuilder(MatchAgainstScoreMessage prototype) {
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
  
  public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements MatchAgainstScoreMessageOrBuilder {
    private int bitField0_;
    
    private Common common_;
    
    private SingleFieldBuilderV3<Common, Common.Builder, CommonOrBuilder> commonBuilder_;
    
    private Against against_;
    
    private SingleFieldBuilderV3<Against, Against.Builder, AgainstOrBuilder> againstBuilder_;
    
    private int matchStatus_;
    
    private int displayStatus_;
    
    public static final Descriptors.Descriptor getDescriptor() {
      return Douyin.internal_static_MatchAgainstScoreMessage_descriptor;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return Douyin.internal_static_MatchAgainstScoreMessage_fieldAccessorTable
        .ensureFieldAccessorsInitialized(MatchAgainstScoreMessage.class, Builder.class);
    }
    
    private Builder() {
      maybeForceBuilderInitialization();
    }
    
    private Builder(GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    
    private void maybeForceBuilderInitialization() {
      if (MatchAgainstScoreMessage.alwaysUseFieldBuilders) {
        getCommonFieldBuilder();
        getAgainstFieldBuilder();
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
      this.against_ = null;
      if (this.againstBuilder_ != null) {
        this.againstBuilder_.dispose();
        this.againstBuilder_ = null;
      } 
      this.matchStatus_ = 0;
      this.displayStatus_ = 0;
      return this;
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
      return Douyin.internal_static_MatchAgainstScoreMessage_descriptor;
    }
    
    public MatchAgainstScoreMessage getDefaultInstanceForType() {
      return MatchAgainstScoreMessage.getDefaultInstance();
    }
    
    public MatchAgainstScoreMessage build() {
      MatchAgainstScoreMessage result = buildPartial();
      if (!result.isInitialized())
        throw newUninitializedMessageException(result); 
      return result;
    }
    
    public MatchAgainstScoreMessage buildPartial() {
      MatchAgainstScoreMessage result = new MatchAgainstScoreMessage(this);
      if (this.bitField0_ != 0)
        buildPartial0(result); 
      onBuilt();
      return result;
    }
    
    private void buildPartial0(MatchAgainstScoreMessage result) {
      int from_bitField0_ = this.bitField0_;
      int to_bitField0_ = 0;
      if ((from_bitField0_ & 0x1) != 0) {
        result.common_ = (this.commonBuilder_ == null) ? 
          this.common_ : 
          (Common)this.commonBuilder_.build();
        to_bitField0_ |= 0x1;
      } 
      if ((from_bitField0_ & 0x2) != 0) {
        result.against_ = (this.againstBuilder_ == null) ? 
          this.against_ : 
          (Against)this.againstBuilder_.build();
        to_bitField0_ |= 0x2;
      } 
      if ((from_bitField0_ & 0x4) != 0)
        result.matchStatus_ = this.matchStatus_; 
      if ((from_bitField0_ & 0x8) != 0)
        result.displayStatus_ = this.displayStatus_; 
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
      if (other instanceof MatchAgainstScoreMessage)
        return mergeFrom((MatchAgainstScoreMessage)other); 
      super.mergeFrom(other);
      return this;
    }
    
    public Builder mergeFrom(MatchAgainstScoreMessage other) {
      if (other == MatchAgainstScoreMessage.getDefaultInstance())
        return this; 
      if (other.hasCommon())
        mergeCommon(other.getCommon()); 
      if (other.hasAgainst())
        mergeAgainst(other.getAgainst()); 
      if (other.getMatchStatus() != 0)
        setMatchStatus(other.getMatchStatus()); 
      if (other.getDisplayStatus() != 0)
        setDisplayStatus(other.getDisplayStatus()); 
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
              input.readMessage((MessageLite.Builder)
                  getCommonFieldBuilder().getBuilder(), extensionRegistry);
              this.bitField0_ |= 0x1;
              continue;
            case 18:
              input.readMessage((MessageLite.Builder)
                  getAgainstFieldBuilder().getBuilder(), extensionRegistry);
              this.bitField0_ |= 0x2;
              continue;
            case 24:
              this.matchStatus_ = input.readUInt32();
              this.bitField0_ |= 0x4;
              continue;
            case 32:
              this.displayStatus_ = input.readUInt32();
              this.bitField0_ |= 0x8;
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
        if ((this.bitField0_ & 0x1) != 0 && this.common_ != null && this.common_ != 
          
          Common.getDefaultInstance()) {
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
      return (this.common_ == null) ? 
        Common.getDefaultInstance() : this.common_;
    }
    
    private SingleFieldBuilderV3<Common, Common.Builder, CommonOrBuilder> getCommonFieldBuilder() {
      if (this.commonBuilder_ == null) {
        this
          
          .commonBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getCommon(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.common_ = null;
      } 
      return this.commonBuilder_;
    }
    
    public boolean hasAgainst() {
      return ((this.bitField0_ & 0x2) != 0);
    }
    
    public Against getAgainst() {
      if (this.againstBuilder_ == null)
        return (this.against_ == null) ? Against.getDefaultInstance() : this.against_; 
      return (Against)this.againstBuilder_.getMessage();
    }
    
    public Builder setAgainst(Against value) {
      if (this.againstBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        this.against_ = value;
      } else {
        this.againstBuilder_.setMessage((AbstractMessage)value);
      } 
      this.bitField0_ |= 0x2;
      onChanged();
      return this;
    }
    
    public Builder setAgainst(Against.Builder builderForValue) {
      if (this.againstBuilder_ == null) {
        this.against_ = builderForValue.build();
      } else {
        this.againstBuilder_.setMessage((AbstractMessage)builderForValue.build());
      } 
      this.bitField0_ |= 0x2;
      onChanged();
      return this;
    }
    
    public Builder mergeAgainst(Against value) {
      if (this.againstBuilder_ == null) {
        if ((this.bitField0_ & 0x2) != 0 && this.against_ != null && this.against_ != 
          
          Against.getDefaultInstance()) {
          getAgainstBuilder().mergeFrom(value);
        } else {
          this.against_ = value;
        } 
      } else {
        this.againstBuilder_.mergeFrom((AbstractMessage)value);
      } 
      if (this.against_ != null) {
        this.bitField0_ |= 0x2;
        onChanged();
      } 
      return this;
    }
    
    public Builder clearAgainst() {
      this.bitField0_ &= 0xFFFFFFFD;
      this.against_ = null;
      if (this.againstBuilder_ != null) {
        this.againstBuilder_.dispose();
        this.againstBuilder_ = null;
      } 
      onChanged();
      return this;
    }
    
    public Against.Builder getAgainstBuilder() {
      this.bitField0_ |= 0x2;
      onChanged();
      return (Against.Builder)getAgainstFieldBuilder().getBuilder();
    }
    
    public AgainstOrBuilder getAgainstOrBuilder() {
      if (this.againstBuilder_ != null)
        return (AgainstOrBuilder)this.againstBuilder_.getMessageOrBuilder(); 
      return (this.against_ == null) ? 
        Against.getDefaultInstance() : this.against_;
    }
    
    private SingleFieldBuilderV3<Against, Against.Builder, AgainstOrBuilder> getAgainstFieldBuilder() {
      if (this.againstBuilder_ == null) {
        this
          
          .againstBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getAgainst(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.against_ = null;
      } 
      return this.againstBuilder_;
    }
    
    public int getMatchStatus() {
      return this.matchStatus_;
    }
    
    public Builder setMatchStatus(int value) {
      this.matchStatus_ = value;
      this.bitField0_ |= 0x4;
      onChanged();
      return this;
    }
    
    public Builder clearMatchStatus() {
      this.bitField0_ &= 0xFFFFFFFB;
      this.matchStatus_ = 0;
      onChanged();
      return this;
    }
    
    public int getDisplayStatus() {
      return this.displayStatus_;
    }
    
    public Builder setDisplayStatus(int value) {
      this.displayStatus_ = value;
      this.bitField0_ |= 0x8;
      onChanged();
      return this;
    }
    
    public Builder clearDisplayStatus() {
      this.bitField0_ &= 0xFFFFFFF7;
      this.displayStatus_ = 0;
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
  
  private static final MatchAgainstScoreMessage DEFAULT_INSTANCE = new MatchAgainstScoreMessage();
  
  public static MatchAgainstScoreMessage getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }
  
  private static final Parser<MatchAgainstScoreMessage> PARSER = (Parser<MatchAgainstScoreMessage>)new AbstractParser<MatchAgainstScoreMessage>() {
      public MatchAgainstScoreMessage parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        MatchAgainstScoreMessage.Builder builder = MatchAgainstScoreMessage.newBuilder();
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
  
  public static Parser<MatchAgainstScoreMessage> parser() {
    return PARSER;
  }
  
  public Parser<MatchAgainstScoreMessage> getParserForType() {
    return PARSER;
  }
  
  public MatchAgainstScoreMessage getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }
}
