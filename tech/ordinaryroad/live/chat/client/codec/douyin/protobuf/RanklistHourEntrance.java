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
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class RanklistHourEntrance extends GeneratedMessageV3 implements RanklistHourEntranceOrBuilder {
  private static final long serialVersionUID = 0L;
  
  public static final int GLOBAL_INFOS_FIELD_NUMBER = 1;
  
  private List<RanklistHourEntrance_Info> globalInfos_;
  
  public static final int DEFAULT_GLOBAL_INFOS_FIELD_NUMBER = 2;
  
  private List<RanklistHourEntrance_Info> defaultGlobalInfos_;
  
  public static final int VERTICAL_INFOS_FIELD_NUMBER = 3;
  
  private List<RanklistHourEntrance_Info> verticalInfos_;
  
  public static final int DEFAULT_VERTICAL_INFOS_FIELD_NUMBER = 4;
  
  private List<RanklistHourEntrance_Info> defaultVerticalInfos_;
  
  private byte memoizedIsInitialized;
  
  private RanklistHourEntrance(GeneratedMessageV3.Builder<?> builder) {
    super(builder);
    this.memoizedIsInitialized = -1;
  }
  
  private RanklistHourEntrance() {
    this.memoizedIsInitialized = -1;
    this.globalInfos_ = Collections.emptyList();
    this.defaultGlobalInfos_ = Collections.emptyList();
    this.verticalInfos_ = Collections.emptyList();
    this.defaultVerticalInfos_ = Collections.emptyList();
  }
  
  protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
    return new RanklistHourEntrance();
  }
  
  public static final Descriptors.Descriptor getDescriptor() {
    return Douyin.internal_static_RanklistHourEntrance_descriptor;
  }
  
  protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
    return Douyin.internal_static_RanklistHourEntrance_fieldAccessorTable.ensureFieldAccessorsInitialized(RanklistHourEntrance.class, Builder.class);
  }
  
  public List<RanklistHourEntrance_Info> getGlobalInfosList() {
    return this.globalInfos_;
  }
  
  public List<? extends RanklistHourEntrance_InfoOrBuilder> getGlobalInfosOrBuilderList() {
    return (List)this.globalInfos_;
  }
  
  public int getGlobalInfosCount() {
    return this.globalInfos_.size();
  }
  
  public RanklistHourEntrance_Info getGlobalInfos(int index) {
    return this.globalInfos_.get(index);
  }
  
  public RanklistHourEntrance_InfoOrBuilder getGlobalInfosOrBuilder(int index) {
    return this.globalInfos_.get(index);
  }
  
  public List<RanklistHourEntrance_Info> getDefaultGlobalInfosList() {
    return this.defaultGlobalInfos_;
  }
  
  public List<? extends RanklistHourEntrance_InfoOrBuilder> getDefaultGlobalInfosOrBuilderList() {
    return (List)this.defaultGlobalInfos_;
  }
  
  public int getDefaultGlobalInfosCount() {
    return this.defaultGlobalInfos_.size();
  }
  
  public RanklistHourEntrance_Info getDefaultGlobalInfos(int index) {
    return this.defaultGlobalInfos_.get(index);
  }
  
  public RanklistHourEntrance_InfoOrBuilder getDefaultGlobalInfosOrBuilder(int index) {
    return this.defaultGlobalInfos_.get(index);
  }
  
  public List<RanklistHourEntrance_Info> getVerticalInfosList() {
    return this.verticalInfos_;
  }
  
  public List<? extends RanklistHourEntrance_InfoOrBuilder> getVerticalInfosOrBuilderList() {
    return (List)this.verticalInfos_;
  }
  
  public int getVerticalInfosCount() {
    return this.verticalInfos_.size();
  }
  
  public RanklistHourEntrance_Info getVerticalInfos(int index) {
    return this.verticalInfos_.get(index);
  }
  
  public RanklistHourEntrance_InfoOrBuilder getVerticalInfosOrBuilder(int index) {
    return this.verticalInfos_.get(index);
  }
  
  public List<RanklistHourEntrance_Info> getDefaultVerticalInfosList() {
    return this.defaultVerticalInfos_;
  }
  
  public List<? extends RanklistHourEntrance_InfoOrBuilder> getDefaultVerticalInfosOrBuilderList() {
    return (List)this.defaultVerticalInfos_;
  }
  
  public int getDefaultVerticalInfosCount() {
    return this.defaultVerticalInfos_.size();
  }
  
  public RanklistHourEntrance_Info getDefaultVerticalInfos(int index) {
    return this.defaultVerticalInfos_.get(index);
  }
  
  public RanklistHourEntrance_InfoOrBuilder getDefaultVerticalInfosOrBuilder(int index) {
    return this.defaultVerticalInfos_.get(index);
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
    int i;
    for (i = 0; i < this.globalInfos_.size(); i++)
      output.writeMessage(1, (MessageLite)this.globalInfos_.get(i)); 
    for (i = 0; i < this.defaultGlobalInfos_.size(); i++)
      output.writeMessage(2, (MessageLite)this.defaultGlobalInfos_.get(i)); 
    for (i = 0; i < this.verticalInfos_.size(); i++)
      output.writeMessage(3, (MessageLite)this.verticalInfos_.get(i)); 
    for (i = 0; i < this.defaultVerticalInfos_.size(); i++)
      output.writeMessage(4, (MessageLite)this.defaultVerticalInfos_.get(i)); 
    getUnknownFields().writeTo(output);
  }
  
  public int getSerializedSize() {
    int size = this.memoizedSize;
    if (size != -1)
      return size; 
    size = 0;
    int i;
    for (i = 0; i < this.globalInfos_.size(); i++)
      size += 
        CodedOutputStream.computeMessageSize(1, (MessageLite)this.globalInfos_.get(i)); 
    for (i = 0; i < this.defaultGlobalInfos_.size(); i++)
      size += 
        CodedOutputStream.computeMessageSize(2, (MessageLite)this.defaultGlobalInfos_.get(i)); 
    for (i = 0; i < this.verticalInfos_.size(); i++)
      size += 
        CodedOutputStream.computeMessageSize(3, (MessageLite)this.verticalInfos_.get(i)); 
    for (i = 0; i < this.defaultVerticalInfos_.size(); i++)
      size += 
        CodedOutputStream.computeMessageSize(4, (MessageLite)this.defaultVerticalInfos_.get(i)); 
    size += getUnknownFields().getSerializedSize();
    this.memoizedSize = size;
    return size;
  }
  
  public boolean equals(Object obj) {
    if (obj == this)
      return true; 
    if (!(obj instanceof RanklistHourEntrance))
      return super.equals(obj); 
    RanklistHourEntrance other = (RanklistHourEntrance)obj;
    if (!getGlobalInfosList().equals(other.getGlobalInfosList()))
      return false; 
    if (!getDefaultGlobalInfosList().equals(other.getDefaultGlobalInfosList()))
      return false; 
    if (!getVerticalInfosList().equals(other.getVerticalInfosList()))
      return false; 
    if (!getDefaultVerticalInfosList().equals(other.getDefaultVerticalInfosList()))
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
    if (getGlobalInfosCount() > 0) {
      hash = 37 * hash + 1;
      hash = 53 * hash + getGlobalInfosList().hashCode();
    } 
    if (getDefaultGlobalInfosCount() > 0) {
      hash = 37 * hash + 2;
      hash = 53 * hash + getDefaultGlobalInfosList().hashCode();
    } 
    if (getVerticalInfosCount() > 0) {
      hash = 37 * hash + 3;
      hash = 53 * hash + getVerticalInfosList().hashCode();
    } 
    if (getDefaultVerticalInfosCount() > 0) {
      hash = 37 * hash + 4;
      hash = 53 * hash + getDefaultVerticalInfosList().hashCode();
    } 
    hash = 29 * hash + getUnknownFields().hashCode();
    this.memoizedHashCode = hash;
    return hash;
  }
  
  public static RanklistHourEntrance parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
    return (RanklistHourEntrance)PARSER.parseFrom(data);
  }
  
  public static RanklistHourEntrance parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (RanklistHourEntrance)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static RanklistHourEntrance parseFrom(ByteString data) throws InvalidProtocolBufferException {
    return (RanklistHourEntrance)PARSER.parseFrom(data);
  }
  
  public static RanklistHourEntrance parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (RanklistHourEntrance)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static RanklistHourEntrance parseFrom(byte[] data) throws InvalidProtocolBufferException {
    return (RanklistHourEntrance)PARSER.parseFrom(data);
  }
  
  public static RanklistHourEntrance parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (RanklistHourEntrance)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static RanklistHourEntrance parseFrom(InputStream input) throws IOException {
    return 
      (RanklistHourEntrance)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static RanklistHourEntrance parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (RanklistHourEntrance)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static RanklistHourEntrance parseDelimitedFrom(InputStream input) throws IOException {
    return 
      (RanklistHourEntrance)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
  }
  
  public static RanklistHourEntrance parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (RanklistHourEntrance)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static RanklistHourEntrance parseFrom(CodedInputStream input) throws IOException {
    return 
      (RanklistHourEntrance)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static RanklistHourEntrance parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (RanklistHourEntrance)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public Builder newBuilderForType() {
    return newBuilder();
  }
  
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  
  public static Builder newBuilder(RanklistHourEntrance prototype) {
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
  
  public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements RanklistHourEntranceOrBuilder {
    private int bitField0_;
    
    private List<RanklistHourEntrance_Info> globalInfos_;
    
    private RepeatedFieldBuilderV3<RanklistHourEntrance_Info, RanklistHourEntrance_Info.Builder, RanklistHourEntrance_InfoOrBuilder> globalInfosBuilder_;
    
    private List<RanklistHourEntrance_Info> defaultGlobalInfos_;
    
    private RepeatedFieldBuilderV3<RanklistHourEntrance_Info, RanklistHourEntrance_Info.Builder, RanklistHourEntrance_InfoOrBuilder> defaultGlobalInfosBuilder_;
    
    private List<RanklistHourEntrance_Info> verticalInfos_;
    
    private RepeatedFieldBuilderV3<RanklistHourEntrance_Info, RanklistHourEntrance_Info.Builder, RanklistHourEntrance_InfoOrBuilder> verticalInfosBuilder_;
    
    private List<RanklistHourEntrance_Info> defaultVerticalInfos_;
    
    private RepeatedFieldBuilderV3<RanklistHourEntrance_Info, RanklistHourEntrance_Info.Builder, RanklistHourEntrance_InfoOrBuilder> defaultVerticalInfosBuilder_;
    
    public static final Descriptors.Descriptor getDescriptor() {
      return Douyin.internal_static_RanklistHourEntrance_descriptor;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return Douyin.internal_static_RanklistHourEntrance_fieldAccessorTable
        .ensureFieldAccessorsInitialized(RanklistHourEntrance.class, Builder.class);
    }
    
    private Builder() {
      this
        .globalInfos_ = Collections.emptyList();
      this
        .defaultGlobalInfos_ = Collections.emptyList();
      this
        .verticalInfos_ = Collections.emptyList();
      this
        .defaultVerticalInfos_ = Collections.emptyList();
    }
    
    private Builder(GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      this.globalInfos_ = Collections.emptyList();
      this.defaultGlobalInfos_ = Collections.emptyList();
      this.verticalInfos_ = Collections.emptyList();
      this.defaultVerticalInfos_ = Collections.emptyList();
    }
    
    public Builder clear() {
      super.clear();
      this.bitField0_ = 0;
      if (this.globalInfosBuilder_ == null) {
        this.globalInfos_ = Collections.emptyList();
      } else {
        this.globalInfos_ = null;
        this.globalInfosBuilder_.clear();
      } 
      this.bitField0_ &= 0xFFFFFFFE;
      if (this.defaultGlobalInfosBuilder_ == null) {
        this.defaultGlobalInfos_ = Collections.emptyList();
      } else {
        this.defaultGlobalInfos_ = null;
        this.defaultGlobalInfosBuilder_.clear();
      } 
      this.bitField0_ &= 0xFFFFFFFD;
      if (this.verticalInfosBuilder_ == null) {
        this.verticalInfos_ = Collections.emptyList();
      } else {
        this.verticalInfos_ = null;
        this.verticalInfosBuilder_.clear();
      } 
      this.bitField0_ &= 0xFFFFFFFB;
      if (this.defaultVerticalInfosBuilder_ == null) {
        this.defaultVerticalInfos_ = Collections.emptyList();
      } else {
        this.defaultVerticalInfos_ = null;
        this.defaultVerticalInfosBuilder_.clear();
      } 
      this.bitField0_ &= 0xFFFFFFF7;
      return this;
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
      return Douyin.internal_static_RanklistHourEntrance_descriptor;
    }
    
    public RanklistHourEntrance getDefaultInstanceForType() {
      return RanklistHourEntrance.getDefaultInstance();
    }
    
    public RanklistHourEntrance build() {
      RanklistHourEntrance result = buildPartial();
      if (!result.isInitialized())
        throw newUninitializedMessageException(result); 
      return result;
    }
    
    public RanklistHourEntrance buildPartial() {
      RanklistHourEntrance result = new RanklistHourEntrance(this);
      buildPartialRepeatedFields(result);
      if (this.bitField0_ != 0)
        buildPartial0(result); 
      onBuilt();
      return result;
    }
    
    private void buildPartialRepeatedFields(RanklistHourEntrance result) {
      if (this.globalInfosBuilder_ == null) {
        if ((this.bitField0_ & 0x1) != 0) {
          this.globalInfos_ = Collections.unmodifiableList(this.globalInfos_);
          this.bitField0_ &= 0xFFFFFFFE;
        } 
        result.globalInfos_ = this.globalInfos_;
      } else {
        result.globalInfos_ = this.globalInfosBuilder_.build();
      } 
      if (this.defaultGlobalInfosBuilder_ == null) {
        if ((this.bitField0_ & 0x2) != 0) {
          this.defaultGlobalInfos_ = Collections.unmodifiableList(this.defaultGlobalInfos_);
          this.bitField0_ &= 0xFFFFFFFD;
        } 
        result.defaultGlobalInfos_ = this.defaultGlobalInfos_;
      } else {
        result.defaultGlobalInfos_ = this.defaultGlobalInfosBuilder_.build();
      } 
      if (this.verticalInfosBuilder_ == null) {
        if ((this.bitField0_ & 0x4) != 0) {
          this.verticalInfos_ = Collections.unmodifiableList(this.verticalInfos_);
          this.bitField0_ &= 0xFFFFFFFB;
        } 
        result.verticalInfos_ = this.verticalInfos_;
      } else {
        result.verticalInfos_ = this.verticalInfosBuilder_.build();
      } 
      if (this.defaultVerticalInfosBuilder_ == null) {
        if ((this.bitField0_ & 0x8) != 0) {
          this.defaultVerticalInfos_ = Collections.unmodifiableList(this.defaultVerticalInfos_);
          this.bitField0_ &= 0xFFFFFFF7;
        } 
        result.defaultVerticalInfos_ = this.defaultVerticalInfos_;
      } else {
        result.defaultVerticalInfos_ = this.defaultVerticalInfosBuilder_.build();
      } 
    }
    
    private void buildPartial0(RanklistHourEntrance result) {
      int from_bitField0_ = this.bitField0_;
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
      if (other instanceof RanklistHourEntrance)
        return mergeFrom((RanklistHourEntrance)other); 
      super.mergeFrom(other);
      return this;
    }
    
    public Builder mergeFrom(RanklistHourEntrance other) {
      if (other == RanklistHourEntrance.getDefaultInstance())
        return this; 
      if (this.globalInfosBuilder_ == null) {
        if (!other.globalInfos_.isEmpty()) {
          if (this.globalInfos_.isEmpty()) {
            this.globalInfos_ = other.globalInfos_;
            this.bitField0_ &= 0xFFFFFFFE;
          } else {
            ensureGlobalInfosIsMutable();
            this.globalInfos_.addAll(other.globalInfos_);
          } 
          onChanged();
        } 
      } else if (!other.globalInfos_.isEmpty()) {
        if (this.globalInfosBuilder_.isEmpty()) {
          this.globalInfosBuilder_.dispose();
          this.globalInfosBuilder_ = null;
          this.globalInfos_ = other.globalInfos_;
          this.bitField0_ &= 0xFFFFFFFE;
          this.globalInfosBuilder_ = RanklistHourEntrance.alwaysUseFieldBuilders ? getGlobalInfosFieldBuilder() : null;
        } else {
          this.globalInfosBuilder_.addAllMessages(other.globalInfos_);
        } 
      } 
      if (this.defaultGlobalInfosBuilder_ == null) {
        if (!other.defaultGlobalInfos_.isEmpty()) {
          if (this.defaultGlobalInfos_.isEmpty()) {
            this.defaultGlobalInfos_ = other.defaultGlobalInfos_;
            this.bitField0_ &= 0xFFFFFFFD;
          } else {
            ensureDefaultGlobalInfosIsMutable();
            this.defaultGlobalInfos_.addAll(other.defaultGlobalInfos_);
          } 
          onChanged();
        } 
      } else if (!other.defaultGlobalInfos_.isEmpty()) {
        if (this.defaultGlobalInfosBuilder_.isEmpty()) {
          this.defaultGlobalInfosBuilder_.dispose();
          this.defaultGlobalInfosBuilder_ = null;
          this.defaultGlobalInfos_ = other.defaultGlobalInfos_;
          this.bitField0_ &= 0xFFFFFFFD;
          this.defaultGlobalInfosBuilder_ = RanklistHourEntrance.alwaysUseFieldBuilders ? getDefaultGlobalInfosFieldBuilder() : null;
        } else {
          this.defaultGlobalInfosBuilder_.addAllMessages(other.defaultGlobalInfos_);
        } 
      } 
      if (this.verticalInfosBuilder_ == null) {
        if (!other.verticalInfos_.isEmpty()) {
          if (this.verticalInfos_.isEmpty()) {
            this.verticalInfos_ = other.verticalInfos_;
            this.bitField0_ &= 0xFFFFFFFB;
          } else {
            ensureVerticalInfosIsMutable();
            this.verticalInfos_.addAll(other.verticalInfos_);
          } 
          onChanged();
        } 
      } else if (!other.verticalInfos_.isEmpty()) {
        if (this.verticalInfosBuilder_.isEmpty()) {
          this.verticalInfosBuilder_.dispose();
          this.verticalInfosBuilder_ = null;
          this.verticalInfos_ = other.verticalInfos_;
          this.bitField0_ &= 0xFFFFFFFB;
          this.verticalInfosBuilder_ = RanklistHourEntrance.alwaysUseFieldBuilders ? getVerticalInfosFieldBuilder() : null;
        } else {
          this.verticalInfosBuilder_.addAllMessages(other.verticalInfos_);
        } 
      } 
      if (this.defaultVerticalInfosBuilder_ == null) {
        if (!other.defaultVerticalInfos_.isEmpty()) {
          if (this.defaultVerticalInfos_.isEmpty()) {
            this.defaultVerticalInfos_ = other.defaultVerticalInfos_;
            this.bitField0_ &= 0xFFFFFFF7;
          } else {
            ensureDefaultVerticalInfosIsMutable();
            this.defaultVerticalInfos_.addAll(other.defaultVerticalInfos_);
          } 
          onChanged();
        } 
      } else if (!other.defaultVerticalInfos_.isEmpty()) {
        if (this.defaultVerticalInfosBuilder_.isEmpty()) {
          this.defaultVerticalInfosBuilder_.dispose();
          this.defaultVerticalInfosBuilder_ = null;
          this.defaultVerticalInfos_ = other.defaultVerticalInfos_;
          this.bitField0_ &= 0xFFFFFFF7;
          this.defaultVerticalInfosBuilder_ = RanklistHourEntrance.alwaysUseFieldBuilders ? getDefaultVerticalInfosFieldBuilder() : null;
        } else {
          this.defaultVerticalInfosBuilder_.addAllMessages(other.defaultVerticalInfos_);
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
          RanklistHourEntrance_Info m;
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              continue;
            case 10:
              m = (RanklistHourEntrance_Info)input.readMessage(RanklistHourEntrance_Info.parser(), extensionRegistry);
              if (this.globalInfosBuilder_ == null) {
                ensureGlobalInfosIsMutable();
                this.globalInfos_.add(m);
                continue;
              } 
              this.globalInfosBuilder_.addMessage((AbstractMessage)m);
              continue;
            case 18:
              m = (RanklistHourEntrance_Info)input.readMessage(RanklistHourEntrance_Info.parser(), extensionRegistry);
              if (this.defaultGlobalInfosBuilder_ == null) {
                ensureDefaultGlobalInfosIsMutable();
                this.defaultGlobalInfos_.add(m);
                continue;
              } 
              this.defaultGlobalInfosBuilder_.addMessage((AbstractMessage)m);
              continue;
            case 26:
              m = (RanklistHourEntrance_Info)input.readMessage(RanklistHourEntrance_Info.parser(), extensionRegistry);
              if (this.verticalInfosBuilder_ == null) {
                ensureVerticalInfosIsMutable();
                this.verticalInfos_.add(m);
                continue;
              } 
              this.verticalInfosBuilder_.addMessage((AbstractMessage)m);
              continue;
            case 34:
              m = (RanklistHourEntrance_Info)input.readMessage(RanklistHourEntrance_Info.parser(), extensionRegistry);
              if (this.defaultVerticalInfosBuilder_ == null) {
                ensureDefaultVerticalInfosIsMutable();
                this.defaultVerticalInfos_.add(m);
                continue;
              } 
              this.defaultVerticalInfosBuilder_.addMessage((AbstractMessage)m);
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
    
    private void ensureGlobalInfosIsMutable() {
      if ((this.bitField0_ & 0x1) == 0) {
        this.globalInfos_ = new ArrayList<>(this.globalInfos_);
        this.bitField0_ |= 0x1;
      } 
    }
    
    public List<RanklistHourEntrance_Info> getGlobalInfosList() {
      if (this.globalInfosBuilder_ == null)
        return Collections.unmodifiableList(this.globalInfos_); 
      return this.globalInfosBuilder_.getMessageList();
    }
    
    public int getGlobalInfosCount() {
      if (this.globalInfosBuilder_ == null)
        return this.globalInfos_.size(); 
      return this.globalInfosBuilder_.getCount();
    }
    
    public RanklistHourEntrance_Info getGlobalInfos(int index) {
      if (this.globalInfosBuilder_ == null)
        return this.globalInfos_.get(index); 
      return (RanklistHourEntrance_Info)this.globalInfosBuilder_.getMessage(index);
    }
    
    public Builder setGlobalInfos(int index, RanklistHourEntrance_Info value) {
      if (this.globalInfosBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        ensureGlobalInfosIsMutable();
        this.globalInfos_.set(index, value);
        onChanged();
      } else {
        this.globalInfosBuilder_.setMessage(index, (AbstractMessage)value);
      } 
      return this;
    }
    
    public Builder setGlobalInfos(int index, RanklistHourEntrance_Info.Builder builderForValue) {
      if (this.globalInfosBuilder_ == null) {
        ensureGlobalInfosIsMutable();
        this.globalInfos_.set(index, builderForValue.build());
        onChanged();
      } else {
        this.globalInfosBuilder_.setMessage(index, (AbstractMessage)builderForValue.build());
      } 
      return this;
    }
    
    public Builder addGlobalInfos(RanklistHourEntrance_Info value) {
      if (this.globalInfosBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        ensureGlobalInfosIsMutable();
        this.globalInfos_.add(value);
        onChanged();
      } else {
        this.globalInfosBuilder_.addMessage((AbstractMessage)value);
      } 
      return this;
    }
    
    public Builder addGlobalInfos(int index, RanklistHourEntrance_Info value) {
      if (this.globalInfosBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        ensureGlobalInfosIsMutable();
        this.globalInfos_.add(index, value);
        onChanged();
      } else {
        this.globalInfosBuilder_.addMessage(index, (AbstractMessage)value);
      } 
      return this;
    }
    
    public Builder addGlobalInfos(RanklistHourEntrance_Info.Builder builderForValue) {
      if (this.globalInfosBuilder_ == null) {
        ensureGlobalInfosIsMutable();
        this.globalInfos_.add(builderForValue.build());
        onChanged();
      } else {
        this.globalInfosBuilder_.addMessage((AbstractMessage)builderForValue.build());
      } 
      return this;
    }
    
    public Builder addGlobalInfos(int index, RanklistHourEntrance_Info.Builder builderForValue) {
      if (this.globalInfosBuilder_ == null) {
        ensureGlobalInfosIsMutable();
        this.globalInfos_.add(index, builderForValue.build());
        onChanged();
      } else {
        this.globalInfosBuilder_.addMessage(index, (AbstractMessage)builderForValue.build());
      } 
      return this;
    }
    
    public Builder addAllGlobalInfos(Iterable<? extends RanklistHourEntrance_Info> values) {
      if (this.globalInfosBuilder_ == null) {
        ensureGlobalInfosIsMutable();
        AbstractMessageLite.Builder.addAll(values, this.globalInfos_);
        onChanged();
      } else {
        this.globalInfosBuilder_.addAllMessages(values);
      } 
      return this;
    }
    
    public Builder clearGlobalInfos() {
      if (this.globalInfosBuilder_ == null) {
        this.globalInfos_ = Collections.emptyList();
        this.bitField0_ &= 0xFFFFFFFE;
        onChanged();
      } else {
        this.globalInfosBuilder_.clear();
      } 
      return this;
    }
    
    public Builder removeGlobalInfos(int index) {
      if (this.globalInfosBuilder_ == null) {
        ensureGlobalInfosIsMutable();
        this.globalInfos_.remove(index);
        onChanged();
      } else {
        this.globalInfosBuilder_.remove(index);
      } 
      return this;
    }
    
    public RanklistHourEntrance_Info.Builder getGlobalInfosBuilder(int index) {
      return (RanklistHourEntrance_Info.Builder)getGlobalInfosFieldBuilder().getBuilder(index);
    }
    
    public RanklistHourEntrance_InfoOrBuilder getGlobalInfosOrBuilder(int index) {
      if (this.globalInfosBuilder_ == null)
        return this.globalInfos_.get(index); 
      return (RanklistHourEntrance_InfoOrBuilder)this.globalInfosBuilder_.getMessageOrBuilder(index);
    }
    
    public List<? extends RanklistHourEntrance_InfoOrBuilder> getGlobalInfosOrBuilderList() {
      if (this.globalInfosBuilder_ != null)
        return this.globalInfosBuilder_.getMessageOrBuilderList(); 
      return Collections.unmodifiableList((List)this.globalInfos_);
    }
    
    public RanklistHourEntrance_Info.Builder addGlobalInfosBuilder() {
      return (RanklistHourEntrance_Info.Builder)getGlobalInfosFieldBuilder().addBuilder((AbstractMessage)RanklistHourEntrance_Info.getDefaultInstance());
    }
    
    public RanklistHourEntrance_Info.Builder addGlobalInfosBuilder(int index) {
      return (RanklistHourEntrance_Info.Builder)getGlobalInfosFieldBuilder().addBuilder(index, (AbstractMessage)RanklistHourEntrance_Info.getDefaultInstance());
    }
    
    public List<RanklistHourEntrance_Info.Builder> getGlobalInfosBuilderList() {
      return getGlobalInfosFieldBuilder().getBuilderList();
    }
    
    private RepeatedFieldBuilderV3<RanklistHourEntrance_Info, RanklistHourEntrance_Info.Builder, RanklistHourEntrance_InfoOrBuilder> getGlobalInfosFieldBuilder() {
      if (this.globalInfosBuilder_ == null) {
        this.globalInfosBuilder_ = new RepeatedFieldBuilderV3(this.globalInfos_, ((this.bitField0_ & 0x1) != 0), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.globalInfos_ = null;
      } 
      return this.globalInfosBuilder_;
    }
    
    private void ensureDefaultGlobalInfosIsMutable() {
      if ((this.bitField0_ & 0x2) == 0) {
        this.defaultGlobalInfos_ = new ArrayList<>(this.defaultGlobalInfos_);
        this.bitField0_ |= 0x2;
      } 
    }
    
    public List<RanklistHourEntrance_Info> getDefaultGlobalInfosList() {
      if (this.defaultGlobalInfosBuilder_ == null)
        return Collections.unmodifiableList(this.defaultGlobalInfos_); 
      return this.defaultGlobalInfosBuilder_.getMessageList();
    }
    
    public int getDefaultGlobalInfosCount() {
      if (this.defaultGlobalInfosBuilder_ == null)
        return this.defaultGlobalInfos_.size(); 
      return this.defaultGlobalInfosBuilder_.getCount();
    }
    
    public RanklistHourEntrance_Info getDefaultGlobalInfos(int index) {
      if (this.defaultGlobalInfosBuilder_ == null)
        return this.defaultGlobalInfos_.get(index); 
      return (RanklistHourEntrance_Info)this.defaultGlobalInfosBuilder_.getMessage(index);
    }
    
    public Builder setDefaultGlobalInfos(int index, RanklistHourEntrance_Info value) {
      if (this.defaultGlobalInfosBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        ensureDefaultGlobalInfosIsMutable();
        this.defaultGlobalInfos_.set(index, value);
        onChanged();
      } else {
        this.defaultGlobalInfosBuilder_.setMessage(index, (AbstractMessage)value);
      } 
      return this;
    }
    
    public Builder setDefaultGlobalInfos(int index, RanklistHourEntrance_Info.Builder builderForValue) {
      if (this.defaultGlobalInfosBuilder_ == null) {
        ensureDefaultGlobalInfosIsMutable();
        this.defaultGlobalInfos_.set(index, builderForValue.build());
        onChanged();
      } else {
        this.defaultGlobalInfosBuilder_.setMessage(index, (AbstractMessage)builderForValue.build());
      } 
      return this;
    }
    
    public Builder addDefaultGlobalInfos(RanklistHourEntrance_Info value) {
      if (this.defaultGlobalInfosBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        ensureDefaultGlobalInfosIsMutable();
        this.defaultGlobalInfos_.add(value);
        onChanged();
      } else {
        this.defaultGlobalInfosBuilder_.addMessage((AbstractMessage)value);
      } 
      return this;
    }
    
    public Builder addDefaultGlobalInfos(int index, RanklistHourEntrance_Info value) {
      if (this.defaultGlobalInfosBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        ensureDefaultGlobalInfosIsMutable();
        this.defaultGlobalInfos_.add(index, value);
        onChanged();
      } else {
        this.defaultGlobalInfosBuilder_.addMessage(index, (AbstractMessage)value);
      } 
      return this;
    }
    
    public Builder addDefaultGlobalInfos(RanklistHourEntrance_Info.Builder builderForValue) {
      if (this.defaultGlobalInfosBuilder_ == null) {
        ensureDefaultGlobalInfosIsMutable();
        this.defaultGlobalInfos_.add(builderForValue.build());
        onChanged();
      } else {
        this.defaultGlobalInfosBuilder_.addMessage((AbstractMessage)builderForValue.build());
      } 
      return this;
    }
    
    public Builder addDefaultGlobalInfos(int index, RanklistHourEntrance_Info.Builder builderForValue) {
      if (this.defaultGlobalInfosBuilder_ == null) {
        ensureDefaultGlobalInfosIsMutable();
        this.defaultGlobalInfos_.add(index, builderForValue.build());
        onChanged();
      } else {
        this.defaultGlobalInfosBuilder_.addMessage(index, (AbstractMessage)builderForValue.build());
      } 
      return this;
    }
    
    public Builder addAllDefaultGlobalInfos(Iterable<? extends RanklistHourEntrance_Info> values) {
      if (this.defaultGlobalInfosBuilder_ == null) {
        ensureDefaultGlobalInfosIsMutable();
        AbstractMessageLite.Builder.addAll(values, this.defaultGlobalInfos_);
        onChanged();
      } else {
        this.defaultGlobalInfosBuilder_.addAllMessages(values);
      } 
      return this;
    }
    
    public Builder clearDefaultGlobalInfos() {
      if (this.defaultGlobalInfosBuilder_ == null) {
        this.defaultGlobalInfos_ = Collections.emptyList();
        this.bitField0_ &= 0xFFFFFFFD;
        onChanged();
      } else {
        this.defaultGlobalInfosBuilder_.clear();
      } 
      return this;
    }
    
    public Builder removeDefaultGlobalInfos(int index) {
      if (this.defaultGlobalInfosBuilder_ == null) {
        ensureDefaultGlobalInfosIsMutable();
        this.defaultGlobalInfos_.remove(index);
        onChanged();
      } else {
        this.defaultGlobalInfosBuilder_.remove(index);
      } 
      return this;
    }
    
    public RanklistHourEntrance_Info.Builder getDefaultGlobalInfosBuilder(int index) {
      return (RanklistHourEntrance_Info.Builder)getDefaultGlobalInfosFieldBuilder().getBuilder(index);
    }
    
    public RanklistHourEntrance_InfoOrBuilder getDefaultGlobalInfosOrBuilder(int index) {
      if (this.defaultGlobalInfosBuilder_ == null)
        return this.defaultGlobalInfos_.get(index); 
      return (RanklistHourEntrance_InfoOrBuilder)this.defaultGlobalInfosBuilder_.getMessageOrBuilder(index);
    }
    
    public List<? extends RanklistHourEntrance_InfoOrBuilder> getDefaultGlobalInfosOrBuilderList() {
      if (this.defaultGlobalInfosBuilder_ != null)
        return this.defaultGlobalInfosBuilder_.getMessageOrBuilderList(); 
      return Collections.unmodifiableList((List)this.defaultGlobalInfos_);
    }
    
    public RanklistHourEntrance_Info.Builder addDefaultGlobalInfosBuilder() {
      return (RanklistHourEntrance_Info.Builder)getDefaultGlobalInfosFieldBuilder().addBuilder((AbstractMessage)RanklistHourEntrance_Info.getDefaultInstance());
    }
    
    public RanklistHourEntrance_Info.Builder addDefaultGlobalInfosBuilder(int index) {
      return (RanklistHourEntrance_Info.Builder)getDefaultGlobalInfosFieldBuilder().addBuilder(index, (AbstractMessage)RanklistHourEntrance_Info.getDefaultInstance());
    }
    
    public List<RanklistHourEntrance_Info.Builder> getDefaultGlobalInfosBuilderList() {
      return getDefaultGlobalInfosFieldBuilder().getBuilderList();
    }
    
    private RepeatedFieldBuilderV3<RanklistHourEntrance_Info, RanklistHourEntrance_Info.Builder, RanklistHourEntrance_InfoOrBuilder> getDefaultGlobalInfosFieldBuilder() {
      if (this.defaultGlobalInfosBuilder_ == null) {
        this.defaultGlobalInfosBuilder_ = new RepeatedFieldBuilderV3(this.defaultGlobalInfos_, ((this.bitField0_ & 0x2) != 0), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.defaultGlobalInfos_ = null;
      } 
      return this.defaultGlobalInfosBuilder_;
    }
    
    private void ensureVerticalInfosIsMutable() {
      if ((this.bitField0_ & 0x4) == 0) {
        this.verticalInfos_ = new ArrayList<>(this.verticalInfos_);
        this.bitField0_ |= 0x4;
      } 
    }
    
    public List<RanklistHourEntrance_Info> getVerticalInfosList() {
      if (this.verticalInfosBuilder_ == null)
        return Collections.unmodifiableList(this.verticalInfos_); 
      return this.verticalInfosBuilder_.getMessageList();
    }
    
    public int getVerticalInfosCount() {
      if (this.verticalInfosBuilder_ == null)
        return this.verticalInfos_.size(); 
      return this.verticalInfosBuilder_.getCount();
    }
    
    public RanklistHourEntrance_Info getVerticalInfos(int index) {
      if (this.verticalInfosBuilder_ == null)
        return this.verticalInfos_.get(index); 
      return (RanklistHourEntrance_Info)this.verticalInfosBuilder_.getMessage(index);
    }
    
    public Builder setVerticalInfos(int index, RanklistHourEntrance_Info value) {
      if (this.verticalInfosBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        ensureVerticalInfosIsMutable();
        this.verticalInfos_.set(index, value);
        onChanged();
      } else {
        this.verticalInfosBuilder_.setMessage(index, (AbstractMessage)value);
      } 
      return this;
    }
    
    public Builder setVerticalInfos(int index, RanklistHourEntrance_Info.Builder builderForValue) {
      if (this.verticalInfosBuilder_ == null) {
        ensureVerticalInfosIsMutable();
        this.verticalInfos_.set(index, builderForValue.build());
        onChanged();
      } else {
        this.verticalInfosBuilder_.setMessage(index, (AbstractMessage)builderForValue.build());
      } 
      return this;
    }
    
    public Builder addVerticalInfos(RanklistHourEntrance_Info value) {
      if (this.verticalInfosBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        ensureVerticalInfosIsMutable();
        this.verticalInfos_.add(value);
        onChanged();
      } else {
        this.verticalInfosBuilder_.addMessage((AbstractMessage)value);
      } 
      return this;
    }
    
    public Builder addVerticalInfos(int index, RanklistHourEntrance_Info value) {
      if (this.verticalInfosBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        ensureVerticalInfosIsMutable();
        this.verticalInfos_.add(index, value);
        onChanged();
      } else {
        this.verticalInfosBuilder_.addMessage(index, (AbstractMessage)value);
      } 
      return this;
    }
    
    public Builder addVerticalInfos(RanklistHourEntrance_Info.Builder builderForValue) {
      if (this.verticalInfosBuilder_ == null) {
        ensureVerticalInfosIsMutable();
        this.verticalInfos_.add(builderForValue.build());
        onChanged();
      } else {
        this.verticalInfosBuilder_.addMessage((AbstractMessage)builderForValue.build());
      } 
      return this;
    }
    
    public Builder addVerticalInfos(int index, RanklistHourEntrance_Info.Builder builderForValue) {
      if (this.verticalInfosBuilder_ == null) {
        ensureVerticalInfosIsMutable();
        this.verticalInfos_.add(index, builderForValue.build());
        onChanged();
      } else {
        this.verticalInfosBuilder_.addMessage(index, (AbstractMessage)builderForValue.build());
      } 
      return this;
    }
    
    public Builder addAllVerticalInfos(Iterable<? extends RanklistHourEntrance_Info> values) {
      if (this.verticalInfosBuilder_ == null) {
        ensureVerticalInfosIsMutable();
        AbstractMessageLite.Builder.addAll(values, this.verticalInfos_);
        onChanged();
      } else {
        this.verticalInfosBuilder_.addAllMessages(values);
      } 
      return this;
    }
    
    public Builder clearVerticalInfos() {
      if (this.verticalInfosBuilder_ == null) {
        this.verticalInfos_ = Collections.emptyList();
        this.bitField0_ &= 0xFFFFFFFB;
        onChanged();
      } else {
        this.verticalInfosBuilder_.clear();
      } 
      return this;
    }
    
    public Builder removeVerticalInfos(int index) {
      if (this.verticalInfosBuilder_ == null) {
        ensureVerticalInfosIsMutable();
        this.verticalInfos_.remove(index);
        onChanged();
      } else {
        this.verticalInfosBuilder_.remove(index);
      } 
      return this;
    }
    
    public RanklistHourEntrance_Info.Builder getVerticalInfosBuilder(int index) {
      return (RanklistHourEntrance_Info.Builder)getVerticalInfosFieldBuilder().getBuilder(index);
    }
    
    public RanklistHourEntrance_InfoOrBuilder getVerticalInfosOrBuilder(int index) {
      if (this.verticalInfosBuilder_ == null)
        return this.verticalInfos_.get(index); 
      return (RanklistHourEntrance_InfoOrBuilder)this.verticalInfosBuilder_.getMessageOrBuilder(index);
    }
    
    public List<? extends RanklistHourEntrance_InfoOrBuilder> getVerticalInfosOrBuilderList() {
      if (this.verticalInfosBuilder_ != null)
        return this.verticalInfosBuilder_.getMessageOrBuilderList(); 
      return Collections.unmodifiableList((List)this.verticalInfos_);
    }
    
    public RanklistHourEntrance_Info.Builder addVerticalInfosBuilder() {
      return (RanklistHourEntrance_Info.Builder)getVerticalInfosFieldBuilder().addBuilder((AbstractMessage)RanklistHourEntrance_Info.getDefaultInstance());
    }
    
    public RanklistHourEntrance_Info.Builder addVerticalInfosBuilder(int index) {
      return (RanklistHourEntrance_Info.Builder)getVerticalInfosFieldBuilder().addBuilder(index, (AbstractMessage)RanklistHourEntrance_Info.getDefaultInstance());
    }
    
    public List<RanklistHourEntrance_Info.Builder> getVerticalInfosBuilderList() {
      return getVerticalInfosFieldBuilder().getBuilderList();
    }
    
    private RepeatedFieldBuilderV3<RanklistHourEntrance_Info, RanklistHourEntrance_Info.Builder, RanklistHourEntrance_InfoOrBuilder> getVerticalInfosFieldBuilder() {
      if (this.verticalInfosBuilder_ == null) {
        this.verticalInfosBuilder_ = new RepeatedFieldBuilderV3(this.verticalInfos_, ((this.bitField0_ & 0x4) != 0), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.verticalInfos_ = null;
      } 
      return this.verticalInfosBuilder_;
    }
    
    private void ensureDefaultVerticalInfosIsMutable() {
      if ((this.bitField0_ & 0x8) == 0) {
        this.defaultVerticalInfos_ = new ArrayList<>(this.defaultVerticalInfos_);
        this.bitField0_ |= 0x8;
      } 
    }
    
    public List<RanklistHourEntrance_Info> getDefaultVerticalInfosList() {
      if (this.defaultVerticalInfosBuilder_ == null)
        return Collections.unmodifiableList(this.defaultVerticalInfos_); 
      return this.defaultVerticalInfosBuilder_.getMessageList();
    }
    
    public int getDefaultVerticalInfosCount() {
      if (this.defaultVerticalInfosBuilder_ == null)
        return this.defaultVerticalInfos_.size(); 
      return this.defaultVerticalInfosBuilder_.getCount();
    }
    
    public RanklistHourEntrance_Info getDefaultVerticalInfos(int index) {
      if (this.defaultVerticalInfosBuilder_ == null)
        return this.defaultVerticalInfos_.get(index); 
      return (RanklistHourEntrance_Info)this.defaultVerticalInfosBuilder_.getMessage(index);
    }
    
    public Builder setDefaultVerticalInfos(int index, RanklistHourEntrance_Info value) {
      if (this.defaultVerticalInfosBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        ensureDefaultVerticalInfosIsMutable();
        this.defaultVerticalInfos_.set(index, value);
        onChanged();
      } else {
        this.defaultVerticalInfosBuilder_.setMessage(index, (AbstractMessage)value);
      } 
      return this;
    }
    
    public Builder setDefaultVerticalInfos(int index, RanklistHourEntrance_Info.Builder builderForValue) {
      if (this.defaultVerticalInfosBuilder_ == null) {
        ensureDefaultVerticalInfosIsMutable();
        this.defaultVerticalInfos_.set(index, builderForValue.build());
        onChanged();
      } else {
        this.defaultVerticalInfosBuilder_.setMessage(index, (AbstractMessage)builderForValue.build());
      } 
      return this;
    }
    
    public Builder addDefaultVerticalInfos(RanklistHourEntrance_Info value) {
      if (this.defaultVerticalInfosBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        ensureDefaultVerticalInfosIsMutable();
        this.defaultVerticalInfos_.add(value);
        onChanged();
      } else {
        this.defaultVerticalInfosBuilder_.addMessage((AbstractMessage)value);
      } 
      return this;
    }
    
    public Builder addDefaultVerticalInfos(int index, RanklistHourEntrance_Info value) {
      if (this.defaultVerticalInfosBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        ensureDefaultVerticalInfosIsMutable();
        this.defaultVerticalInfos_.add(index, value);
        onChanged();
      } else {
        this.defaultVerticalInfosBuilder_.addMessage(index, (AbstractMessage)value);
      } 
      return this;
    }
    
    public Builder addDefaultVerticalInfos(RanklistHourEntrance_Info.Builder builderForValue) {
      if (this.defaultVerticalInfosBuilder_ == null) {
        ensureDefaultVerticalInfosIsMutable();
        this.defaultVerticalInfos_.add(builderForValue.build());
        onChanged();
      } else {
        this.defaultVerticalInfosBuilder_.addMessage((AbstractMessage)builderForValue.build());
      } 
      return this;
    }
    
    public Builder addDefaultVerticalInfos(int index, RanklistHourEntrance_Info.Builder builderForValue) {
      if (this.defaultVerticalInfosBuilder_ == null) {
        ensureDefaultVerticalInfosIsMutable();
        this.defaultVerticalInfos_.add(index, builderForValue.build());
        onChanged();
      } else {
        this.defaultVerticalInfosBuilder_.addMessage(index, (AbstractMessage)builderForValue.build());
      } 
      return this;
    }
    
    public Builder addAllDefaultVerticalInfos(Iterable<? extends RanklistHourEntrance_Info> values) {
      if (this.defaultVerticalInfosBuilder_ == null) {
        ensureDefaultVerticalInfosIsMutable();
        AbstractMessageLite.Builder.addAll(values, this.defaultVerticalInfos_);
        onChanged();
      } else {
        this.defaultVerticalInfosBuilder_.addAllMessages(values);
      } 
      return this;
    }
    
    public Builder clearDefaultVerticalInfos() {
      if (this.defaultVerticalInfosBuilder_ == null) {
        this.defaultVerticalInfos_ = Collections.emptyList();
        this.bitField0_ &= 0xFFFFFFF7;
        onChanged();
      } else {
        this.defaultVerticalInfosBuilder_.clear();
      } 
      return this;
    }
    
    public Builder removeDefaultVerticalInfos(int index) {
      if (this.defaultVerticalInfosBuilder_ == null) {
        ensureDefaultVerticalInfosIsMutable();
        this.defaultVerticalInfos_.remove(index);
        onChanged();
      } else {
        this.defaultVerticalInfosBuilder_.remove(index);
      } 
      return this;
    }
    
    public RanklistHourEntrance_Info.Builder getDefaultVerticalInfosBuilder(int index) {
      return (RanklistHourEntrance_Info.Builder)getDefaultVerticalInfosFieldBuilder().getBuilder(index);
    }
    
    public RanklistHourEntrance_InfoOrBuilder getDefaultVerticalInfosOrBuilder(int index) {
      if (this.defaultVerticalInfosBuilder_ == null)
        return this.defaultVerticalInfos_.get(index); 
      return (RanklistHourEntrance_InfoOrBuilder)this.defaultVerticalInfosBuilder_.getMessageOrBuilder(index);
    }
    
    public List<? extends RanklistHourEntrance_InfoOrBuilder> getDefaultVerticalInfosOrBuilderList() {
      if (this.defaultVerticalInfosBuilder_ != null)
        return this.defaultVerticalInfosBuilder_.getMessageOrBuilderList(); 
      return Collections.unmodifiableList((List)this.defaultVerticalInfos_);
    }
    
    public RanklistHourEntrance_Info.Builder addDefaultVerticalInfosBuilder() {
      return (RanklistHourEntrance_Info.Builder)getDefaultVerticalInfosFieldBuilder().addBuilder(
          (AbstractMessage)RanklistHourEntrance_Info.getDefaultInstance());
    }
    
    public RanklistHourEntrance_Info.Builder addDefaultVerticalInfosBuilder(int index) {
      return (RanklistHourEntrance_Info.Builder)getDefaultVerticalInfosFieldBuilder().addBuilder(index, 
          (AbstractMessage)RanklistHourEntrance_Info.getDefaultInstance());
    }
    
    public List<RanklistHourEntrance_Info.Builder> getDefaultVerticalInfosBuilderList() {
      return getDefaultVerticalInfosFieldBuilder().getBuilderList();
    }
    
    private RepeatedFieldBuilderV3<RanklistHourEntrance_Info, RanklistHourEntrance_Info.Builder, RanklistHourEntrance_InfoOrBuilder> getDefaultVerticalInfosFieldBuilder() {
      if (this.defaultVerticalInfosBuilder_ == null) {
        this
          
          .defaultVerticalInfosBuilder_ = new RepeatedFieldBuilderV3(this.defaultVerticalInfos_, ((this.bitField0_ & 0x8) != 0), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.defaultVerticalInfos_ = null;
      } 
      return this.defaultVerticalInfosBuilder_;
    }
    
    public final Builder setUnknownFields(UnknownFieldSet unknownFields) {
      return (Builder)super.setUnknownFields(unknownFields);
    }
    
    public final Builder mergeUnknownFields(UnknownFieldSet unknownFields) {
      return (Builder)super.mergeUnknownFields(unknownFields);
    }
  }
  
  private static final RanklistHourEntrance DEFAULT_INSTANCE = new RanklistHourEntrance();
  
  public static RanklistHourEntrance getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }
  
  private static final Parser<RanklistHourEntrance> PARSER = (Parser<RanklistHourEntrance>)new AbstractParser<RanklistHourEntrance>() {
      public RanklistHourEntrance parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        RanklistHourEntrance.Builder builder = RanklistHourEntrance.newBuilder();
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
  
  public static Parser<RanklistHourEntrance> parser() {
    return PARSER;
  }
  
  public Parser<RanklistHourEntrance> getParserForType() {
    return PARSER;
  }
  
  public RanklistHourEntrance getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }
}
