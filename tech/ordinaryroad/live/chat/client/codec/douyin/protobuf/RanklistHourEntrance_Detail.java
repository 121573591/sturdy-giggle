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

public final class RanklistHourEntrance_Detail extends GeneratedMessageV3 implements RanklistHourEntrance_DetailOrBuilder {
  private static final long serialVersionUID = 0L;
  
  public static final int PAGES_FIELD_NUMBER = 1;
  
  private List<RanklistHourEntrance_Page> pages_;
  
  public static final int RANKLIST_TYPE_FIELD_NUMBER = 2;
  
  private int ranklistType_;
  
  public static final int TITLE_FIELD_NUMBER = 3;
  
  private volatile Object title_;
  
  public static final int RANKLIST_EXTRA_FIELD_NUMBER = 4;
  
  private volatile Object ranklistExtra_;
  
  public static final int ENTRANCE_EXTRA_FIELD_NUMBER = 5;
  
  private volatile Object entranceExtra_;
  
  public static final int SCHEMA_FIELD_NUMBER = 6;
  
  private volatile Object schema_;
  
  private byte memoizedIsInitialized;
  
  private RanklistHourEntrance_Detail(GeneratedMessageV3.Builder<?> builder) {
    super(builder);
    this.ranklistType_ = 0;
    this.title_ = "";
    this.ranklistExtra_ = "";
    this.entranceExtra_ = "";
    this.schema_ = "";
    this.memoizedIsInitialized = -1;
  }
  
  private RanklistHourEntrance_Detail() {
    this.ranklistType_ = 0;
    this.title_ = "";
    this.ranklistExtra_ = "";
    this.entranceExtra_ = "";
    this.schema_ = "";
    this.memoizedIsInitialized = -1;
    this.pages_ = Collections.emptyList();
    this.title_ = "";
    this.ranklistExtra_ = "";
    this.entranceExtra_ = "";
    this.schema_ = "";
  }
  
  protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
    return new RanklistHourEntrance_Detail();
  }
  
  public static final Descriptors.Descriptor getDescriptor() {
    return Douyin.internal_static_RanklistHourEntrance_Detail_descriptor;
  }
  
  protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
    return Douyin.internal_static_RanklistHourEntrance_Detail_fieldAccessorTable.ensureFieldAccessorsInitialized(RanklistHourEntrance_Detail.class, Builder.class);
  }
  
  public List<RanklistHourEntrance_Page> getPagesList() {
    return this.pages_;
  }
  
  public List<? extends RanklistHourEntrance_PageOrBuilder> getPagesOrBuilderList() {
    return (List)this.pages_;
  }
  
  public int getPagesCount() {
    return this.pages_.size();
  }
  
  public RanklistHourEntrance_Page getPages(int index) {
    return this.pages_.get(index);
  }
  
  public RanklistHourEntrance_PageOrBuilder getPagesOrBuilder(int index) {
    return this.pages_.get(index);
  }
  
  public int getRanklistType() {
    return this.ranklistType_;
  }
  
  public String getTitle() {
    Object ref = this.title_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.title_ = s;
    return s;
  }
  
  public ByteString getTitleBytes() {
    Object ref = this.title_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.title_ = b;
      return b;
    } 
    return (ByteString)ref;
  }
  
  public String getRanklistExtra() {
    Object ref = this.ranklistExtra_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.ranklistExtra_ = s;
    return s;
  }
  
  public ByteString getRanklistExtraBytes() {
    Object ref = this.ranklistExtra_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.ranklistExtra_ = b;
      return b;
    } 
    return (ByteString)ref;
  }
  
  public String getEntranceExtra() {
    Object ref = this.entranceExtra_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.entranceExtra_ = s;
    return s;
  }
  
  public ByteString getEntranceExtraBytes() {
    Object ref = this.entranceExtra_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.entranceExtra_ = b;
      return b;
    } 
    return (ByteString)ref;
  }
  
  public String getSchema() {
    Object ref = this.schema_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.schema_ = s;
    return s;
  }
  
  public ByteString getSchemaBytes() {
    Object ref = this.schema_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.schema_ = b;
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
    for (int i = 0; i < this.pages_.size(); i++)
      output.writeMessage(1, (MessageLite)this.pages_.get(i)); 
    if (this.ranklistType_ != 0)
      output.writeInt32(2, this.ranklistType_); 
    if (!GeneratedMessageV3.isStringEmpty(this.title_))
      GeneratedMessageV3.writeString(output, 3, this.title_); 
    if (!GeneratedMessageV3.isStringEmpty(this.ranklistExtra_))
      GeneratedMessageV3.writeString(output, 4, this.ranklistExtra_); 
    if (!GeneratedMessageV3.isStringEmpty(this.entranceExtra_))
      GeneratedMessageV3.writeString(output, 5, this.entranceExtra_); 
    if (!GeneratedMessageV3.isStringEmpty(this.schema_))
      GeneratedMessageV3.writeString(output, 6, this.schema_); 
    getUnknownFields().writeTo(output);
  }
  
  public int getSerializedSize() {
    int size = this.memoizedSize;
    if (size != -1)
      return size; 
    size = 0;
    for (int i = 0; i < this.pages_.size(); i++)
      size += 
        CodedOutputStream.computeMessageSize(1, (MessageLite)this.pages_.get(i)); 
    if (this.ranklistType_ != 0)
      size += 
        CodedOutputStream.computeInt32Size(2, this.ranklistType_); 
    if (!GeneratedMessageV3.isStringEmpty(this.title_))
      size += GeneratedMessageV3.computeStringSize(3, this.title_); 
    if (!GeneratedMessageV3.isStringEmpty(this.ranklistExtra_))
      size += GeneratedMessageV3.computeStringSize(4, this.ranklistExtra_); 
    if (!GeneratedMessageV3.isStringEmpty(this.entranceExtra_))
      size += GeneratedMessageV3.computeStringSize(5, this.entranceExtra_); 
    if (!GeneratedMessageV3.isStringEmpty(this.schema_))
      size += GeneratedMessageV3.computeStringSize(6, this.schema_); 
    size += getUnknownFields().getSerializedSize();
    this.memoizedSize = size;
    return size;
  }
  
  public boolean equals(Object obj) {
    if (obj == this)
      return true; 
    if (!(obj instanceof RanklistHourEntrance_Detail))
      return super.equals(obj); 
    RanklistHourEntrance_Detail other = (RanklistHourEntrance_Detail)obj;
    if (!getPagesList().equals(other.getPagesList()))
      return false; 
    if (getRanklistType() != other
      .getRanklistType())
      return false; 
    if (!getTitle().equals(other.getTitle()))
      return false; 
    if (!getRanklistExtra().equals(other.getRanklistExtra()))
      return false; 
    if (!getEntranceExtra().equals(other.getEntranceExtra()))
      return false; 
    if (!getSchema().equals(other.getSchema()))
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
    if (getPagesCount() > 0) {
      hash = 37 * hash + 1;
      hash = 53 * hash + getPagesList().hashCode();
    } 
    hash = 37 * hash + 2;
    hash = 53 * hash + getRanklistType();
    hash = 37 * hash + 3;
    hash = 53 * hash + getTitle().hashCode();
    hash = 37 * hash + 4;
    hash = 53 * hash + getRanklistExtra().hashCode();
    hash = 37 * hash + 5;
    hash = 53 * hash + getEntranceExtra().hashCode();
    hash = 37 * hash + 6;
    hash = 53 * hash + getSchema().hashCode();
    hash = 29 * hash + getUnknownFields().hashCode();
    this.memoizedHashCode = hash;
    return hash;
  }
  
  public static RanklistHourEntrance_Detail parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
    return (RanklistHourEntrance_Detail)PARSER.parseFrom(data);
  }
  
  public static RanklistHourEntrance_Detail parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (RanklistHourEntrance_Detail)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static RanklistHourEntrance_Detail parseFrom(ByteString data) throws InvalidProtocolBufferException {
    return (RanklistHourEntrance_Detail)PARSER.parseFrom(data);
  }
  
  public static RanklistHourEntrance_Detail parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (RanklistHourEntrance_Detail)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static RanklistHourEntrance_Detail parseFrom(byte[] data) throws InvalidProtocolBufferException {
    return (RanklistHourEntrance_Detail)PARSER.parseFrom(data);
  }
  
  public static RanklistHourEntrance_Detail parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (RanklistHourEntrance_Detail)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static RanklistHourEntrance_Detail parseFrom(InputStream input) throws IOException {
    return 
      (RanklistHourEntrance_Detail)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static RanklistHourEntrance_Detail parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (RanklistHourEntrance_Detail)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static RanklistHourEntrance_Detail parseDelimitedFrom(InputStream input) throws IOException {
    return 
      (RanklistHourEntrance_Detail)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
  }
  
  public static RanklistHourEntrance_Detail parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (RanklistHourEntrance_Detail)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static RanklistHourEntrance_Detail parseFrom(CodedInputStream input) throws IOException {
    return 
      (RanklistHourEntrance_Detail)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static RanklistHourEntrance_Detail parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (RanklistHourEntrance_Detail)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public Builder newBuilderForType() {
    return newBuilder();
  }
  
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  
  public static Builder newBuilder(RanklistHourEntrance_Detail prototype) {
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
  
  public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements RanklistHourEntrance_DetailOrBuilder {
    private int bitField0_;
    
    private List<RanklistHourEntrance_Page> pages_;
    
    private RepeatedFieldBuilderV3<RanklistHourEntrance_Page, RanklistHourEntrance_Page.Builder, RanklistHourEntrance_PageOrBuilder> pagesBuilder_;
    
    private int ranklistType_;
    
    private Object title_;
    
    private Object ranklistExtra_;
    
    private Object entranceExtra_;
    
    private Object schema_;
    
    public static final Descriptors.Descriptor getDescriptor() {
      return Douyin.internal_static_RanklistHourEntrance_Detail_descriptor;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return Douyin.internal_static_RanklistHourEntrance_Detail_fieldAccessorTable
        .ensureFieldAccessorsInitialized(RanklistHourEntrance_Detail.class, Builder.class);
    }
    
    private Builder() {
      this
        .pages_ = Collections.emptyList();
      this.title_ = "";
      this.ranklistExtra_ = "";
      this.entranceExtra_ = "";
      this.schema_ = "";
    }
    
    private Builder(GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      this.pages_ = Collections.emptyList();
      this.title_ = "";
      this.ranklistExtra_ = "";
      this.entranceExtra_ = "";
      this.schema_ = "";
    }
    
    public Builder clear() {
      super.clear();
      this.bitField0_ = 0;
      if (this.pagesBuilder_ == null) {
        this.pages_ = Collections.emptyList();
      } else {
        this.pages_ = null;
        this.pagesBuilder_.clear();
      } 
      this.bitField0_ &= 0xFFFFFFFE;
      this.ranklistType_ = 0;
      this.title_ = "";
      this.ranklistExtra_ = "";
      this.entranceExtra_ = "";
      this.schema_ = "";
      return this;
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
      return Douyin.internal_static_RanklistHourEntrance_Detail_descriptor;
    }
    
    public RanklistHourEntrance_Detail getDefaultInstanceForType() {
      return RanklistHourEntrance_Detail.getDefaultInstance();
    }
    
    public RanklistHourEntrance_Detail build() {
      RanklistHourEntrance_Detail result = buildPartial();
      if (!result.isInitialized())
        throw newUninitializedMessageException(result); 
      return result;
    }
    
    public RanklistHourEntrance_Detail buildPartial() {
      RanklistHourEntrance_Detail result = new RanklistHourEntrance_Detail(this);
      buildPartialRepeatedFields(result);
      if (this.bitField0_ != 0)
        buildPartial0(result); 
      onBuilt();
      return result;
    }
    
    private void buildPartialRepeatedFields(RanklistHourEntrance_Detail result) {
      if (this.pagesBuilder_ == null) {
        if ((this.bitField0_ & 0x1) != 0) {
          this.pages_ = Collections.unmodifiableList(this.pages_);
          this.bitField0_ &= 0xFFFFFFFE;
        } 
        result.pages_ = this.pages_;
      } else {
        result.pages_ = this.pagesBuilder_.build();
      } 
    }
    
    private void buildPartial0(RanklistHourEntrance_Detail result) {
      int from_bitField0_ = this.bitField0_;
      if ((from_bitField0_ & 0x2) != 0)
        result.ranklistType_ = this.ranklistType_; 
      if ((from_bitField0_ & 0x4) != 0)
        result.title_ = this.title_; 
      if ((from_bitField0_ & 0x8) != 0)
        result.ranklistExtra_ = this.ranklistExtra_; 
      if ((from_bitField0_ & 0x10) != 0)
        result.entranceExtra_ = this.entranceExtra_; 
      if ((from_bitField0_ & 0x20) != 0)
        result.schema_ = this.schema_; 
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
      if (other instanceof RanklistHourEntrance_Detail)
        return mergeFrom((RanklistHourEntrance_Detail)other); 
      super.mergeFrom(other);
      return this;
    }
    
    public Builder mergeFrom(RanklistHourEntrance_Detail other) {
      if (other == RanklistHourEntrance_Detail.getDefaultInstance())
        return this; 
      if (this.pagesBuilder_ == null) {
        if (!other.pages_.isEmpty()) {
          if (this.pages_.isEmpty()) {
            this.pages_ = other.pages_;
            this.bitField0_ &= 0xFFFFFFFE;
          } else {
            ensurePagesIsMutable();
            this.pages_.addAll(other.pages_);
          } 
          onChanged();
        } 
      } else if (!other.pages_.isEmpty()) {
        if (this.pagesBuilder_.isEmpty()) {
          this.pagesBuilder_.dispose();
          this.pagesBuilder_ = null;
          this.pages_ = other.pages_;
          this.bitField0_ &= 0xFFFFFFFE;
          this.pagesBuilder_ = RanklistHourEntrance_Detail.alwaysUseFieldBuilders ? getPagesFieldBuilder() : null;
        } else {
          this.pagesBuilder_.addAllMessages(other.pages_);
        } 
      } 
      if (other.getRanklistType() != 0)
        setRanklistType(other.getRanklistType()); 
      if (!other.getTitle().isEmpty()) {
        this.title_ = other.title_;
        this.bitField0_ |= 0x4;
        onChanged();
      } 
      if (!other.getRanklistExtra().isEmpty()) {
        this.ranklistExtra_ = other.ranklistExtra_;
        this.bitField0_ |= 0x8;
        onChanged();
      } 
      if (!other.getEntranceExtra().isEmpty()) {
        this.entranceExtra_ = other.entranceExtra_;
        this.bitField0_ |= 0x10;
        onChanged();
      } 
      if (!other.getSchema().isEmpty()) {
        this.schema_ = other.schema_;
        this.bitField0_ |= 0x20;
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
          RanklistHourEntrance_Page m;
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              continue;
            case 10:
              m = (RanklistHourEntrance_Page)input.readMessage(RanklistHourEntrance_Page.parser(), extensionRegistry);
              if (this.pagesBuilder_ == null) {
                ensurePagesIsMutable();
                this.pages_.add(m);
                continue;
              } 
              this.pagesBuilder_.addMessage((AbstractMessage)m);
              continue;
            case 16:
              this.ranklistType_ = input.readInt32();
              this.bitField0_ |= 0x2;
              continue;
            case 26:
              this.title_ = input.readStringRequireUtf8();
              this.bitField0_ |= 0x4;
              continue;
            case 34:
              this.ranklistExtra_ = input.readStringRequireUtf8();
              this.bitField0_ |= 0x8;
              continue;
            case 42:
              this.entranceExtra_ = input.readStringRequireUtf8();
              this.bitField0_ |= 0x10;
              continue;
            case 50:
              this.schema_ = input.readStringRequireUtf8();
              this.bitField0_ |= 0x20;
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
    
    private void ensurePagesIsMutable() {
      if ((this.bitField0_ & 0x1) == 0) {
        this.pages_ = new ArrayList<>(this.pages_);
        this.bitField0_ |= 0x1;
      } 
    }
    
    public List<RanklistHourEntrance_Page> getPagesList() {
      if (this.pagesBuilder_ == null)
        return Collections.unmodifiableList(this.pages_); 
      return this.pagesBuilder_.getMessageList();
    }
    
    public int getPagesCount() {
      if (this.pagesBuilder_ == null)
        return this.pages_.size(); 
      return this.pagesBuilder_.getCount();
    }
    
    public RanklistHourEntrance_Page getPages(int index) {
      if (this.pagesBuilder_ == null)
        return this.pages_.get(index); 
      return (RanklistHourEntrance_Page)this.pagesBuilder_.getMessage(index);
    }
    
    public Builder setPages(int index, RanklistHourEntrance_Page value) {
      if (this.pagesBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        ensurePagesIsMutable();
        this.pages_.set(index, value);
        onChanged();
      } else {
        this.pagesBuilder_.setMessage(index, (AbstractMessage)value);
      } 
      return this;
    }
    
    public Builder setPages(int index, RanklistHourEntrance_Page.Builder builderForValue) {
      if (this.pagesBuilder_ == null) {
        ensurePagesIsMutable();
        this.pages_.set(index, builderForValue.build());
        onChanged();
      } else {
        this.pagesBuilder_.setMessage(index, (AbstractMessage)builderForValue.build());
      } 
      return this;
    }
    
    public Builder addPages(RanklistHourEntrance_Page value) {
      if (this.pagesBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        ensurePagesIsMutable();
        this.pages_.add(value);
        onChanged();
      } else {
        this.pagesBuilder_.addMessage((AbstractMessage)value);
      } 
      return this;
    }
    
    public Builder addPages(int index, RanklistHourEntrance_Page value) {
      if (this.pagesBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        ensurePagesIsMutable();
        this.pages_.add(index, value);
        onChanged();
      } else {
        this.pagesBuilder_.addMessage(index, (AbstractMessage)value);
      } 
      return this;
    }
    
    public Builder addPages(RanklistHourEntrance_Page.Builder builderForValue) {
      if (this.pagesBuilder_ == null) {
        ensurePagesIsMutable();
        this.pages_.add(builderForValue.build());
        onChanged();
      } else {
        this.pagesBuilder_.addMessage((AbstractMessage)builderForValue.build());
      } 
      return this;
    }
    
    public Builder addPages(int index, RanklistHourEntrance_Page.Builder builderForValue) {
      if (this.pagesBuilder_ == null) {
        ensurePagesIsMutable();
        this.pages_.add(index, builderForValue.build());
        onChanged();
      } else {
        this.pagesBuilder_.addMessage(index, (AbstractMessage)builderForValue.build());
      } 
      return this;
    }
    
    public Builder addAllPages(Iterable<? extends RanklistHourEntrance_Page> values) {
      if (this.pagesBuilder_ == null) {
        ensurePagesIsMutable();
        AbstractMessageLite.Builder.addAll(values, this.pages_);
        onChanged();
      } else {
        this.pagesBuilder_.addAllMessages(values);
      } 
      return this;
    }
    
    public Builder clearPages() {
      if (this.pagesBuilder_ == null) {
        this.pages_ = Collections.emptyList();
        this.bitField0_ &= 0xFFFFFFFE;
        onChanged();
      } else {
        this.pagesBuilder_.clear();
      } 
      return this;
    }
    
    public Builder removePages(int index) {
      if (this.pagesBuilder_ == null) {
        ensurePagesIsMutable();
        this.pages_.remove(index);
        onChanged();
      } else {
        this.pagesBuilder_.remove(index);
      } 
      return this;
    }
    
    public RanklistHourEntrance_Page.Builder getPagesBuilder(int index) {
      return (RanklistHourEntrance_Page.Builder)getPagesFieldBuilder().getBuilder(index);
    }
    
    public RanklistHourEntrance_PageOrBuilder getPagesOrBuilder(int index) {
      if (this.pagesBuilder_ == null)
        return this.pages_.get(index); 
      return (RanklistHourEntrance_PageOrBuilder)this.pagesBuilder_.getMessageOrBuilder(index);
    }
    
    public List<? extends RanklistHourEntrance_PageOrBuilder> getPagesOrBuilderList() {
      if (this.pagesBuilder_ != null)
        return this.pagesBuilder_.getMessageOrBuilderList(); 
      return Collections.unmodifiableList((List)this.pages_);
    }
    
    public RanklistHourEntrance_Page.Builder addPagesBuilder() {
      return (RanklistHourEntrance_Page.Builder)getPagesFieldBuilder().addBuilder((AbstractMessage)RanklistHourEntrance_Page.getDefaultInstance());
    }
    
    public RanklistHourEntrance_Page.Builder addPagesBuilder(int index) {
      return (RanklistHourEntrance_Page.Builder)getPagesFieldBuilder().addBuilder(index, (AbstractMessage)RanklistHourEntrance_Page.getDefaultInstance());
    }
    
    public List<RanklistHourEntrance_Page.Builder> getPagesBuilderList() {
      return getPagesFieldBuilder().getBuilderList();
    }
    
    private RepeatedFieldBuilderV3<RanklistHourEntrance_Page, RanklistHourEntrance_Page.Builder, RanklistHourEntrance_PageOrBuilder> getPagesFieldBuilder() {
      if (this.pagesBuilder_ == null) {
        this.pagesBuilder_ = new RepeatedFieldBuilderV3(this.pages_, ((this.bitField0_ & 0x1) != 0), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.pages_ = null;
      } 
      return this.pagesBuilder_;
    }
    
    public int getRanklistType() {
      return this.ranklistType_;
    }
    
    public Builder setRanklistType(int value) {
      this.ranklistType_ = value;
      this.bitField0_ |= 0x2;
      onChanged();
      return this;
    }
    
    public Builder clearRanklistType() {
      this.bitField0_ &= 0xFFFFFFFD;
      this.ranklistType_ = 0;
      onChanged();
      return this;
    }
    
    public String getTitle() {
      Object ref = this.title_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.title_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getTitleBytes() {
      Object ref = this.title_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.title_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setTitle(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.title_ = value;
      this.bitField0_ |= 0x4;
      onChanged();
      return this;
    }
    
    public Builder clearTitle() {
      this.title_ = RanklistHourEntrance_Detail.getDefaultInstance().getTitle();
      this.bitField0_ &= 0xFFFFFFFB;
      onChanged();
      return this;
    }
    
    public Builder setTitleBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      RanklistHourEntrance_Detail.checkByteStringIsUtf8(value);
      this.title_ = value;
      this.bitField0_ |= 0x4;
      onChanged();
      return this;
    }
    
    public String getRanklistExtra() {
      Object ref = this.ranklistExtra_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.ranklistExtra_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getRanklistExtraBytes() {
      Object ref = this.ranklistExtra_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.ranklistExtra_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setRanklistExtra(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.ranklistExtra_ = value;
      this.bitField0_ |= 0x8;
      onChanged();
      return this;
    }
    
    public Builder clearRanklistExtra() {
      this.ranklistExtra_ = RanklistHourEntrance_Detail.getDefaultInstance().getRanklistExtra();
      this.bitField0_ &= 0xFFFFFFF7;
      onChanged();
      return this;
    }
    
    public Builder setRanklistExtraBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      RanklistHourEntrance_Detail.checkByteStringIsUtf8(value);
      this.ranklistExtra_ = value;
      this.bitField0_ |= 0x8;
      onChanged();
      return this;
    }
    
    public String getEntranceExtra() {
      Object ref = this.entranceExtra_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.entranceExtra_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getEntranceExtraBytes() {
      Object ref = this.entranceExtra_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.entranceExtra_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setEntranceExtra(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.entranceExtra_ = value;
      this.bitField0_ |= 0x10;
      onChanged();
      return this;
    }
    
    public Builder clearEntranceExtra() {
      this.entranceExtra_ = RanklistHourEntrance_Detail.getDefaultInstance().getEntranceExtra();
      this.bitField0_ &= 0xFFFFFFEF;
      onChanged();
      return this;
    }
    
    public Builder setEntranceExtraBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      RanklistHourEntrance_Detail.checkByteStringIsUtf8(value);
      this.entranceExtra_ = value;
      this.bitField0_ |= 0x10;
      onChanged();
      return this;
    }
    
    public String getSchema() {
      Object ref = this.schema_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.schema_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getSchemaBytes() {
      Object ref = this.schema_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.schema_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setSchema(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.schema_ = value;
      this.bitField0_ |= 0x20;
      onChanged();
      return this;
    }
    
    public Builder clearSchema() {
      this.schema_ = RanklistHourEntrance_Detail.getDefaultInstance().getSchema();
      this.bitField0_ &= 0xFFFFFFDF;
      onChanged();
      return this;
    }
    
    public Builder setSchemaBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      RanklistHourEntrance_Detail.checkByteStringIsUtf8(value);
      this.schema_ = value;
      this.bitField0_ |= 0x20;
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
  
  private static final RanklistHourEntrance_Detail DEFAULT_INSTANCE = new RanklistHourEntrance_Detail();
  
  public static RanklistHourEntrance_Detail getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }
  
  private static final Parser<RanklistHourEntrance_Detail> PARSER = (Parser<RanklistHourEntrance_Detail>)new AbstractParser<RanklistHourEntrance_Detail>() {
      public RanklistHourEntrance_Detail parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        RanklistHourEntrance_Detail.Builder builder = RanklistHourEntrance_Detail.newBuilder();
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
  
  public static Parser<RanklistHourEntrance_Detail> parser() {
    return PARSER;
  }
  
  public Parser<RanklistHourEntrance_Detail> getParserForType() {
    return PARSER;
  }
  
  public RanklistHourEntrance_Detail getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }
}
