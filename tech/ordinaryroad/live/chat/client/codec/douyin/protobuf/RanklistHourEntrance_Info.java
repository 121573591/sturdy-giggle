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

public final class RanklistHourEntrance_Info extends GeneratedMessageV3 implements RanklistHourEntrance_InfoOrBuilder {
  private static final long serialVersionUID = 0L;
  
  public static final int DETAILS_FIELD_NUMBER = 1;
  
  private List<RanklistHourEntrance_Detail> details_;
  
  private byte memoizedIsInitialized;
  
  private RanklistHourEntrance_Info(GeneratedMessageV3.Builder<?> builder) {
    super(builder);
    this.memoizedIsInitialized = -1;
  }
  
  private RanklistHourEntrance_Info() {
    this.memoizedIsInitialized = -1;
    this.details_ = Collections.emptyList();
  }
  
  protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
    return new RanklistHourEntrance_Info();
  }
  
  public static final Descriptors.Descriptor getDescriptor() {
    return Douyin.internal_static_RanklistHourEntrance_Info_descriptor;
  }
  
  protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
    return Douyin.internal_static_RanklistHourEntrance_Info_fieldAccessorTable.ensureFieldAccessorsInitialized(RanklistHourEntrance_Info.class, Builder.class);
  }
  
  public List<RanklistHourEntrance_Detail> getDetailsList() {
    return this.details_;
  }
  
  public List<? extends RanklistHourEntrance_DetailOrBuilder> getDetailsOrBuilderList() {
    return (List)this.details_;
  }
  
  public int getDetailsCount() {
    return this.details_.size();
  }
  
  public RanklistHourEntrance_Detail getDetails(int index) {
    return this.details_.get(index);
  }
  
  public RanklistHourEntrance_DetailOrBuilder getDetailsOrBuilder(int index) {
    return this.details_.get(index);
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
    for (int i = 0; i < this.details_.size(); i++)
      output.writeMessage(1, (MessageLite)this.details_.get(i)); 
    getUnknownFields().writeTo(output);
  }
  
  public int getSerializedSize() {
    int size = this.memoizedSize;
    if (size != -1)
      return size; 
    size = 0;
    for (int i = 0; i < this.details_.size(); i++)
      size += 
        CodedOutputStream.computeMessageSize(1, (MessageLite)this.details_.get(i)); 
    size += getUnknownFields().getSerializedSize();
    this.memoizedSize = size;
    return size;
  }
  
  public boolean equals(Object obj) {
    if (obj == this)
      return true; 
    if (!(obj instanceof RanklistHourEntrance_Info))
      return super.equals(obj); 
    RanklistHourEntrance_Info other = (RanklistHourEntrance_Info)obj;
    if (!getDetailsList().equals(other.getDetailsList()))
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
    if (getDetailsCount() > 0) {
      hash = 37 * hash + 1;
      hash = 53 * hash + getDetailsList().hashCode();
    } 
    hash = 29 * hash + getUnknownFields().hashCode();
    this.memoizedHashCode = hash;
    return hash;
  }
  
  public static RanklistHourEntrance_Info parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
    return (RanklistHourEntrance_Info)PARSER.parseFrom(data);
  }
  
  public static RanklistHourEntrance_Info parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (RanklistHourEntrance_Info)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static RanklistHourEntrance_Info parseFrom(ByteString data) throws InvalidProtocolBufferException {
    return (RanklistHourEntrance_Info)PARSER.parseFrom(data);
  }
  
  public static RanklistHourEntrance_Info parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (RanklistHourEntrance_Info)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static RanklistHourEntrance_Info parseFrom(byte[] data) throws InvalidProtocolBufferException {
    return (RanklistHourEntrance_Info)PARSER.parseFrom(data);
  }
  
  public static RanklistHourEntrance_Info parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (RanklistHourEntrance_Info)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static RanklistHourEntrance_Info parseFrom(InputStream input) throws IOException {
    return 
      (RanklistHourEntrance_Info)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static RanklistHourEntrance_Info parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (RanklistHourEntrance_Info)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static RanklistHourEntrance_Info parseDelimitedFrom(InputStream input) throws IOException {
    return 
      (RanklistHourEntrance_Info)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
  }
  
  public static RanklistHourEntrance_Info parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (RanklistHourEntrance_Info)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static RanklistHourEntrance_Info parseFrom(CodedInputStream input) throws IOException {
    return 
      (RanklistHourEntrance_Info)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static RanklistHourEntrance_Info parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (RanklistHourEntrance_Info)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public Builder newBuilderForType() {
    return newBuilder();
  }
  
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  
  public static Builder newBuilder(RanklistHourEntrance_Info prototype) {
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
  
  public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements RanklistHourEntrance_InfoOrBuilder {
    private int bitField0_;
    
    private List<RanklistHourEntrance_Detail> details_;
    
    private RepeatedFieldBuilderV3<RanklistHourEntrance_Detail, RanklistHourEntrance_Detail.Builder, RanklistHourEntrance_DetailOrBuilder> detailsBuilder_;
    
    public static final Descriptors.Descriptor getDescriptor() {
      return Douyin.internal_static_RanklistHourEntrance_Info_descriptor;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return Douyin.internal_static_RanklistHourEntrance_Info_fieldAccessorTable
        .ensureFieldAccessorsInitialized(RanklistHourEntrance_Info.class, Builder.class);
    }
    
    private Builder() {
      this
        .details_ = Collections.emptyList();
    }
    
    private Builder(GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      this.details_ = Collections.emptyList();
    }
    
    public Builder clear() {
      super.clear();
      this.bitField0_ = 0;
      if (this.detailsBuilder_ == null) {
        this.details_ = Collections.emptyList();
      } else {
        this.details_ = null;
        this.detailsBuilder_.clear();
      } 
      this.bitField0_ &= 0xFFFFFFFE;
      return this;
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
      return Douyin.internal_static_RanklistHourEntrance_Info_descriptor;
    }
    
    public RanklistHourEntrance_Info getDefaultInstanceForType() {
      return RanklistHourEntrance_Info.getDefaultInstance();
    }
    
    public RanklistHourEntrance_Info build() {
      RanklistHourEntrance_Info result = buildPartial();
      if (!result.isInitialized())
        throw newUninitializedMessageException(result); 
      return result;
    }
    
    public RanklistHourEntrance_Info buildPartial() {
      RanklistHourEntrance_Info result = new RanklistHourEntrance_Info(this);
      buildPartialRepeatedFields(result);
      if (this.bitField0_ != 0)
        buildPartial0(result); 
      onBuilt();
      return result;
    }
    
    private void buildPartialRepeatedFields(RanklistHourEntrance_Info result) {
      if (this.detailsBuilder_ == null) {
        if ((this.bitField0_ & 0x1) != 0) {
          this.details_ = Collections.unmodifiableList(this.details_);
          this.bitField0_ &= 0xFFFFFFFE;
        } 
        result.details_ = this.details_;
      } else {
        result.details_ = this.detailsBuilder_.build();
      } 
    }
    
    private void buildPartial0(RanklistHourEntrance_Info result) {
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
      if (other instanceof RanklistHourEntrance_Info)
        return mergeFrom((RanklistHourEntrance_Info)other); 
      super.mergeFrom(other);
      return this;
    }
    
    public Builder mergeFrom(RanklistHourEntrance_Info other) {
      if (other == RanklistHourEntrance_Info.getDefaultInstance())
        return this; 
      if (this.detailsBuilder_ == null) {
        if (!other.details_.isEmpty()) {
          if (this.details_.isEmpty()) {
            this.details_ = other.details_;
            this.bitField0_ &= 0xFFFFFFFE;
          } else {
            ensureDetailsIsMutable();
            this.details_.addAll(other.details_);
          } 
          onChanged();
        } 
      } else if (!other.details_.isEmpty()) {
        if (this.detailsBuilder_.isEmpty()) {
          this.detailsBuilder_.dispose();
          this.detailsBuilder_ = null;
          this.details_ = other.details_;
          this.bitField0_ &= 0xFFFFFFFE;
          this.detailsBuilder_ = RanklistHourEntrance_Info.alwaysUseFieldBuilders ? getDetailsFieldBuilder() : null;
        } else {
          this.detailsBuilder_.addAllMessages(other.details_);
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
          RanklistHourEntrance_Detail m;
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              continue;
            case 10:
              m = (RanklistHourEntrance_Detail)input.readMessage(RanklistHourEntrance_Detail.parser(), extensionRegistry);
              if (this.detailsBuilder_ == null) {
                ensureDetailsIsMutable();
                this.details_.add(m);
                continue;
              } 
              this.detailsBuilder_.addMessage((AbstractMessage)m);
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
    
    private void ensureDetailsIsMutable() {
      if ((this.bitField0_ & 0x1) == 0) {
        this.details_ = new ArrayList<>(this.details_);
        this.bitField0_ |= 0x1;
      } 
    }
    
    public List<RanklistHourEntrance_Detail> getDetailsList() {
      if (this.detailsBuilder_ == null)
        return Collections.unmodifiableList(this.details_); 
      return this.detailsBuilder_.getMessageList();
    }
    
    public int getDetailsCount() {
      if (this.detailsBuilder_ == null)
        return this.details_.size(); 
      return this.detailsBuilder_.getCount();
    }
    
    public RanklistHourEntrance_Detail getDetails(int index) {
      if (this.detailsBuilder_ == null)
        return this.details_.get(index); 
      return (RanklistHourEntrance_Detail)this.detailsBuilder_.getMessage(index);
    }
    
    public Builder setDetails(int index, RanklistHourEntrance_Detail value) {
      if (this.detailsBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        ensureDetailsIsMutable();
        this.details_.set(index, value);
        onChanged();
      } else {
        this.detailsBuilder_.setMessage(index, (AbstractMessage)value);
      } 
      return this;
    }
    
    public Builder setDetails(int index, RanklistHourEntrance_Detail.Builder builderForValue) {
      if (this.detailsBuilder_ == null) {
        ensureDetailsIsMutable();
        this.details_.set(index, builderForValue.build());
        onChanged();
      } else {
        this.detailsBuilder_.setMessage(index, (AbstractMessage)builderForValue.build());
      } 
      return this;
    }
    
    public Builder addDetails(RanklistHourEntrance_Detail value) {
      if (this.detailsBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        ensureDetailsIsMutable();
        this.details_.add(value);
        onChanged();
      } else {
        this.detailsBuilder_.addMessage((AbstractMessage)value);
      } 
      return this;
    }
    
    public Builder addDetails(int index, RanklistHourEntrance_Detail value) {
      if (this.detailsBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        ensureDetailsIsMutable();
        this.details_.add(index, value);
        onChanged();
      } else {
        this.detailsBuilder_.addMessage(index, (AbstractMessage)value);
      } 
      return this;
    }
    
    public Builder addDetails(RanklistHourEntrance_Detail.Builder builderForValue) {
      if (this.detailsBuilder_ == null) {
        ensureDetailsIsMutable();
        this.details_.add(builderForValue.build());
        onChanged();
      } else {
        this.detailsBuilder_.addMessage((AbstractMessage)builderForValue.build());
      } 
      return this;
    }
    
    public Builder addDetails(int index, RanklistHourEntrance_Detail.Builder builderForValue) {
      if (this.detailsBuilder_ == null) {
        ensureDetailsIsMutable();
        this.details_.add(index, builderForValue.build());
        onChanged();
      } else {
        this.detailsBuilder_.addMessage(index, (AbstractMessage)builderForValue.build());
      } 
      return this;
    }
    
    public Builder addAllDetails(Iterable<? extends RanklistHourEntrance_Detail> values) {
      if (this.detailsBuilder_ == null) {
        ensureDetailsIsMutable();
        AbstractMessageLite.Builder.addAll(values, this.details_);
        onChanged();
      } else {
        this.detailsBuilder_.addAllMessages(values);
      } 
      return this;
    }
    
    public Builder clearDetails() {
      if (this.detailsBuilder_ == null) {
        this.details_ = Collections.emptyList();
        this.bitField0_ &= 0xFFFFFFFE;
        onChanged();
      } else {
        this.detailsBuilder_.clear();
      } 
      return this;
    }
    
    public Builder removeDetails(int index) {
      if (this.detailsBuilder_ == null) {
        ensureDetailsIsMutable();
        this.details_.remove(index);
        onChanged();
      } else {
        this.detailsBuilder_.remove(index);
      } 
      return this;
    }
    
    public RanklistHourEntrance_Detail.Builder getDetailsBuilder(int index) {
      return (RanklistHourEntrance_Detail.Builder)getDetailsFieldBuilder().getBuilder(index);
    }
    
    public RanklistHourEntrance_DetailOrBuilder getDetailsOrBuilder(int index) {
      if (this.detailsBuilder_ == null)
        return this.details_.get(index); 
      return (RanklistHourEntrance_DetailOrBuilder)this.detailsBuilder_.getMessageOrBuilder(index);
    }
    
    public List<? extends RanklistHourEntrance_DetailOrBuilder> getDetailsOrBuilderList() {
      if (this.detailsBuilder_ != null)
        return this.detailsBuilder_.getMessageOrBuilderList(); 
      return Collections.unmodifiableList((List)this.details_);
    }
    
    public RanklistHourEntrance_Detail.Builder addDetailsBuilder() {
      return (RanklistHourEntrance_Detail.Builder)getDetailsFieldBuilder().addBuilder(
          (AbstractMessage)RanklistHourEntrance_Detail.getDefaultInstance());
    }
    
    public RanklistHourEntrance_Detail.Builder addDetailsBuilder(int index) {
      return (RanklistHourEntrance_Detail.Builder)getDetailsFieldBuilder().addBuilder(index, 
          (AbstractMessage)RanklistHourEntrance_Detail.getDefaultInstance());
    }
    
    public List<RanklistHourEntrance_Detail.Builder> getDetailsBuilderList() {
      return getDetailsFieldBuilder().getBuilderList();
    }
    
    private RepeatedFieldBuilderV3<RanklistHourEntrance_Detail, RanklistHourEntrance_Detail.Builder, RanklistHourEntrance_DetailOrBuilder> getDetailsFieldBuilder() {
      if (this.detailsBuilder_ == null) {
        this
          
          .detailsBuilder_ = new RepeatedFieldBuilderV3(this.details_, ((this.bitField0_ & 0x1) != 0), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.details_ = null;
      } 
      return this.detailsBuilder_;
    }
    
    public final Builder setUnknownFields(UnknownFieldSet unknownFields) {
      return (Builder)super.setUnknownFields(unknownFields);
    }
    
    public final Builder mergeUnknownFields(UnknownFieldSet unknownFields) {
      return (Builder)super.mergeUnknownFields(unknownFields);
    }
  }
  
  private static final RanklistHourEntrance_Info DEFAULT_INSTANCE = new RanklistHourEntrance_Info();
  
  public static RanklistHourEntrance_Info getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }
  
  private static final Parser<RanklistHourEntrance_Info> PARSER = (Parser<RanklistHourEntrance_Info>)new AbstractParser<RanklistHourEntrance_Info>() {
      public RanklistHourEntrance_Info parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        RanklistHourEntrance_Info.Builder builder = RanklistHourEntrance_Info.newBuilder();
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
  
  public static Parser<RanklistHourEntrance_Info> parser() {
    return PARSER;
  }
  
  public Parser<RanklistHourEntrance_Info> getParserForType() {
    return PARSER;
  }
  
  public RanklistHourEntrance_Info getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }
}
