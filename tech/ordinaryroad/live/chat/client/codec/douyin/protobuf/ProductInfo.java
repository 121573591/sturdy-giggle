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
import com.google.protobuf.Parser;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.List;

public final class ProductInfo extends GeneratedMessageV3 implements ProductInfoOrBuilder {
  private static final long serialVersionUID = 0L;
  
  public static final int PROMOTIONID_FIELD_NUMBER = 1;
  
  private long promotionId_;
  
  public static final int INDEX_FIELD_NUMBER = 2;
  
  private int index_;
  
  public static final int TARGETFLASHUIDSLIST_FIELD_NUMBER = 3;
  
  private Internal.LongList targetFlashUidsList_;
  
  private int targetFlashUidsListMemoizedSerializedSize;
  
  public static final int EXPLAINTYPE_FIELD_NUMBER = 4;
  
  private long explainType_;
  
  private byte memoizedIsInitialized;
  
  private ProductInfo(GeneratedMessageV3.Builder<?> builder) {
    super(builder);
    this.promotionId_ = 0L;
    this.index_ = 0;
    this
      
      .targetFlashUidsList_ = emptyLongList();
    this.targetFlashUidsListMemoizedSerializedSize = -1;
    this.explainType_ = 0L;
    this.memoizedIsInitialized = -1;
  }
  
  private ProductInfo() {
    this.promotionId_ = 0L;
    this.index_ = 0;
    this.targetFlashUidsList_ = emptyLongList();
    this.targetFlashUidsListMemoizedSerializedSize = -1;
    this.explainType_ = 0L;
    this.memoizedIsInitialized = -1;
    this.targetFlashUidsList_ = emptyLongList();
  }
  
  protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
    return new ProductInfo();
  }
  
  public static final Descriptors.Descriptor getDescriptor() {
    return Douyin.internal_static_ProductInfo_descriptor;
  }
  
  protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
    return Douyin.internal_static_ProductInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(ProductInfo.class, Builder.class);
  }
  
  public long getPromotionId() {
    return this.promotionId_;
  }
  
  public int getIndex() {
    return this.index_;
  }
  
  public List<Long> getTargetFlashUidsListList() {
    return (List<Long>)this.targetFlashUidsList_;
  }
  
  public int getTargetFlashUidsListCount() {
    return this.targetFlashUidsList_.size();
  }
  
  public long getTargetFlashUidsList(int index) {
    return this.targetFlashUidsList_.getLong(index);
  }
  
  public long getExplainType() {
    return this.explainType_;
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
    getSerializedSize();
    if (this.promotionId_ != 0L)
      output.writeInt64(1, this.promotionId_); 
    if (this.index_ != 0)
      output.writeInt32(2, this.index_); 
    if (getTargetFlashUidsListList().size() > 0) {
      output.writeUInt32NoTag(26);
      output.writeUInt32NoTag(this.targetFlashUidsListMemoizedSerializedSize);
    } 
    for (int i = 0; i < this.targetFlashUidsList_.size(); i++)
      output.writeInt64NoTag(this.targetFlashUidsList_.getLong(i)); 
    if (this.explainType_ != 0L)
      output.writeInt64(4, this.explainType_); 
    getUnknownFields().writeTo(output);
  }
  
  public int getSerializedSize() {
    int size = this.memoizedSize;
    if (size != -1)
      return size; 
    size = 0;
    if (this.promotionId_ != 0L)
      size += 
        CodedOutputStream.computeInt64Size(1, this.promotionId_); 
    if (this.index_ != 0)
      size += 
        CodedOutputStream.computeInt32Size(2, this.index_); 
    int dataSize = 0;
    for (int i = 0; i < this.targetFlashUidsList_.size(); i++)
      dataSize += 
        CodedOutputStream.computeInt64SizeNoTag(this.targetFlashUidsList_.getLong(i)); 
    size += dataSize;
    if (!getTargetFlashUidsListList().isEmpty()) {
      size++;
      size += 
        CodedOutputStream.computeInt32SizeNoTag(dataSize);
    } 
    this.targetFlashUidsListMemoizedSerializedSize = dataSize;
    if (this.explainType_ != 0L)
      size += 
        CodedOutputStream.computeInt64Size(4, this.explainType_); 
    size += getUnknownFields().getSerializedSize();
    this.memoizedSize = size;
    return size;
  }
  
  public boolean equals(Object obj) {
    if (obj == this)
      return true; 
    if (!(obj instanceof ProductInfo))
      return super.equals(obj); 
    ProductInfo other = (ProductInfo)obj;
    if (getPromotionId() != other
      .getPromotionId())
      return false; 
    if (getIndex() != other
      .getIndex())
      return false; 
    if (!getTargetFlashUidsListList().equals(other.getTargetFlashUidsListList()))
      return false; 
    if (getExplainType() != other
      .getExplainType())
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
        getPromotionId());
    hash = 37 * hash + 2;
    hash = 53 * hash + getIndex();
    if (getTargetFlashUidsListCount() > 0) {
      hash = 37 * hash + 3;
      hash = 53 * hash + getTargetFlashUidsListList().hashCode();
    } 
    hash = 37 * hash + 4;
    hash = 53 * hash + Internal.hashLong(
        getExplainType());
    hash = 29 * hash + getUnknownFields().hashCode();
    this.memoizedHashCode = hash;
    return hash;
  }
  
  public static ProductInfo parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
    return (ProductInfo)PARSER.parseFrom(data);
  }
  
  public static ProductInfo parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (ProductInfo)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static ProductInfo parseFrom(ByteString data) throws InvalidProtocolBufferException {
    return (ProductInfo)PARSER.parseFrom(data);
  }
  
  public static ProductInfo parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (ProductInfo)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static ProductInfo parseFrom(byte[] data) throws InvalidProtocolBufferException {
    return (ProductInfo)PARSER.parseFrom(data);
  }
  
  public static ProductInfo parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (ProductInfo)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static ProductInfo parseFrom(InputStream input) throws IOException {
    return 
      (ProductInfo)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static ProductInfo parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (ProductInfo)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static ProductInfo parseDelimitedFrom(InputStream input) throws IOException {
    return 
      (ProductInfo)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
  }
  
  public static ProductInfo parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (ProductInfo)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static ProductInfo parseFrom(CodedInputStream input) throws IOException {
    return 
      (ProductInfo)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static ProductInfo parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (ProductInfo)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public Builder newBuilderForType() {
    return newBuilder();
  }
  
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  
  public static Builder newBuilder(ProductInfo prototype) {
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
  
  public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ProductInfoOrBuilder {
    private int bitField0_;
    
    private long promotionId_;
    
    private int index_;
    
    private Internal.LongList targetFlashUidsList_;
    
    private long explainType_;
    
    public static final Descriptors.Descriptor getDescriptor() {
      return Douyin.internal_static_ProductInfo_descriptor;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return Douyin.internal_static_ProductInfo_fieldAccessorTable
        .ensureFieldAccessorsInitialized(ProductInfo.class, Builder.class);
    }
    
    private Builder() {
      this.targetFlashUidsList_ = ProductInfo.emptyLongList();
    }
    
    private Builder(GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      this.targetFlashUidsList_ = ProductInfo.emptyLongList();
    }
    
    public Builder clear() {
      super.clear();
      this.bitField0_ = 0;
      this.promotionId_ = 0L;
      this.index_ = 0;
      this.targetFlashUidsList_ = ProductInfo.emptyLongList();
      this.explainType_ = 0L;
      return this;
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
      return Douyin.internal_static_ProductInfo_descriptor;
    }
    
    public ProductInfo getDefaultInstanceForType() {
      return ProductInfo.getDefaultInstance();
    }
    
    public ProductInfo build() {
      ProductInfo result = buildPartial();
      if (!result.isInitialized())
        throw newUninitializedMessageException(result); 
      return result;
    }
    
    public ProductInfo buildPartial() {
      ProductInfo result = new ProductInfo(this);
      if (this.bitField0_ != 0)
        buildPartial0(result); 
      onBuilt();
      return result;
    }
    
    private void buildPartial0(ProductInfo result) {
      int from_bitField0_ = this.bitField0_;
      if ((from_bitField0_ & 0x1) != 0)
        result.promotionId_ = this.promotionId_; 
      if ((from_bitField0_ & 0x2) != 0)
        result.index_ = this.index_; 
      if ((from_bitField0_ & 0x4) != 0) {
        this.targetFlashUidsList_.makeImmutable();
        result.targetFlashUidsList_ = this.targetFlashUidsList_;
      } 
      if ((from_bitField0_ & 0x8) != 0)
        result.explainType_ = this.explainType_; 
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
      if (other instanceof ProductInfo)
        return mergeFrom((ProductInfo)other); 
      super.mergeFrom(other);
      return this;
    }
    
    public Builder mergeFrom(ProductInfo other) {
      if (other == ProductInfo.getDefaultInstance())
        return this; 
      if (other.getPromotionId() != 0L)
        setPromotionId(other.getPromotionId()); 
      if (other.getIndex() != 0)
        setIndex(other.getIndex()); 
      if (!other.targetFlashUidsList_.isEmpty()) {
        if (this.targetFlashUidsList_.isEmpty()) {
          this.targetFlashUidsList_ = other.targetFlashUidsList_;
          this.targetFlashUidsList_.makeImmutable();
          this.bitField0_ |= 0x4;
        } else {
          ensureTargetFlashUidsListIsMutable();
          this.targetFlashUidsList_.addAll((Collection)other.targetFlashUidsList_);
        } 
        onChanged();
      } 
      if (other.getExplainType() != 0L)
        setExplainType(other.getExplainType()); 
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
          long v;
          int length, limit, tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              continue;
            case 8:
              this.promotionId_ = input.readInt64();
              this.bitField0_ |= 0x1;
              continue;
            case 16:
              this.index_ = input.readInt32();
              this.bitField0_ |= 0x2;
              continue;
            case 24:
              v = input.readInt64();
              ensureTargetFlashUidsListIsMutable();
              this.targetFlashUidsList_.addLong(v);
              continue;
            case 26:
              length = input.readRawVarint32();
              limit = input.pushLimit(length);
              ensureTargetFlashUidsListIsMutable();
              while (input.getBytesUntilLimit() > 0)
                this.targetFlashUidsList_.addLong(input.readInt64()); 
              input.popLimit(limit);
              continue;
            case 32:
              this.explainType_ = input.readInt64();
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
    
    public long getPromotionId() {
      return this.promotionId_;
    }
    
    public Builder setPromotionId(long value) {
      this.promotionId_ = value;
      this.bitField0_ |= 0x1;
      onChanged();
      return this;
    }
    
    public Builder clearPromotionId() {
      this.bitField0_ &= 0xFFFFFFFE;
      this.promotionId_ = 0L;
      onChanged();
      return this;
    }
    
    public int getIndex() {
      return this.index_;
    }
    
    public Builder setIndex(int value) {
      this.index_ = value;
      this.bitField0_ |= 0x2;
      onChanged();
      return this;
    }
    
    public Builder clearIndex() {
      this.bitField0_ &= 0xFFFFFFFD;
      this.index_ = 0;
      onChanged();
      return this;
    }
    
    private void ensureTargetFlashUidsListIsMutable() {
      if (!this.targetFlashUidsList_.isModifiable())
        this.targetFlashUidsList_ = (Internal.LongList)ProductInfo.makeMutableCopy((Internal.ProtobufList)this.targetFlashUidsList_); 
      this.bitField0_ |= 0x4;
    }
    
    public List<Long> getTargetFlashUidsListList() {
      this.targetFlashUidsList_.makeImmutable();
      return (List<Long>)this.targetFlashUidsList_;
    }
    
    public int getTargetFlashUidsListCount() {
      return this.targetFlashUidsList_.size();
    }
    
    public long getTargetFlashUidsList(int index) {
      return this.targetFlashUidsList_.getLong(index);
    }
    
    public Builder setTargetFlashUidsList(int index, long value) {
      ensureTargetFlashUidsListIsMutable();
      this.targetFlashUidsList_.setLong(index, value);
      this.bitField0_ |= 0x4;
      onChanged();
      return this;
    }
    
    public Builder addTargetFlashUidsList(long value) {
      ensureTargetFlashUidsListIsMutable();
      this.targetFlashUidsList_.addLong(value);
      this.bitField0_ |= 0x4;
      onChanged();
      return this;
    }
    
    public Builder addAllTargetFlashUidsList(Iterable<? extends Long> values) {
      ensureTargetFlashUidsListIsMutable();
      AbstractMessageLite.Builder.addAll(values, (List)this.targetFlashUidsList_);
      this.bitField0_ |= 0x4;
      onChanged();
      return this;
    }
    
    public Builder clearTargetFlashUidsList() {
      this.targetFlashUidsList_ = ProductInfo.emptyLongList();
      this.bitField0_ &= 0xFFFFFFFB;
      onChanged();
      return this;
    }
    
    public long getExplainType() {
      return this.explainType_;
    }
    
    public Builder setExplainType(long value) {
      this.explainType_ = value;
      this.bitField0_ |= 0x8;
      onChanged();
      return this;
    }
    
    public Builder clearExplainType() {
      this.bitField0_ &= 0xFFFFFFF7;
      this.explainType_ = 0L;
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
  
  private static final ProductInfo DEFAULT_INSTANCE = new ProductInfo();
  
  public static ProductInfo getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }
  
  private static final Parser<ProductInfo> PARSER = (Parser<ProductInfo>)new AbstractParser<ProductInfo>() {
      public ProductInfo parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        ProductInfo.Builder builder = ProductInfo.newBuilder();
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
  
  public static Parser<ProductInfo> parser() {
    return PARSER;
  }
  
  public Parser<ProductInfo> getParserForType() {
    return PARSER;
  }
  
  public ProductInfo getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }
}
