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

public final class CategoryInfo extends GeneratedMessageV3 implements CategoryInfoOrBuilder {
  private static final long serialVersionUID = 0L;
  
  public static final int ID_FIELD_NUMBER = 1;
  
  private int id_;
  
  public static final int NAME_FIELD_NUMBER = 2;
  
  private volatile Object name_;
  
  public static final int PROMOTIONIDSLIST_FIELD_NUMBER = 3;
  
  private Internal.LongList promotionIdsList_;
  
  private int promotionIdsListMemoizedSerializedSize;
  
  public static final int TYPE_FIELD_NUMBER = 4;
  
  private volatile Object type_;
  
  public static final int UNIQUEINDEX_FIELD_NUMBER = 5;
  
  private volatile Object uniqueIndex_;
  
  private byte memoizedIsInitialized;
  
  private CategoryInfo(GeneratedMessageV3.Builder<?> builder) {
    super(builder);
    this.id_ = 0;
    this.name_ = "";
    this
      
      .promotionIdsList_ = emptyLongList();
    this.promotionIdsListMemoizedSerializedSize = -1;
    this.type_ = "";
    this.uniqueIndex_ = "";
    this.memoizedIsInitialized = -1;
  }
  
  private CategoryInfo() {
    this.id_ = 0;
    this.name_ = "";
    this.promotionIdsList_ = emptyLongList();
    this.promotionIdsListMemoizedSerializedSize = -1;
    this.type_ = "";
    this.uniqueIndex_ = "";
    this.memoizedIsInitialized = -1;
    this.name_ = "";
    this.promotionIdsList_ = emptyLongList();
    this.type_ = "";
    this.uniqueIndex_ = "";
  }
  
  protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
    return new CategoryInfo();
  }
  
  public static final Descriptors.Descriptor getDescriptor() {
    return Douyin.internal_static_CategoryInfo_descriptor;
  }
  
  protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
    return Douyin.internal_static_CategoryInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(CategoryInfo.class, Builder.class);
  }
  
  public int getId() {
    return this.id_;
  }
  
  public String getName() {
    Object ref = this.name_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.name_ = s;
    return s;
  }
  
  public ByteString getNameBytes() {
    Object ref = this.name_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.name_ = b;
      return b;
    } 
    return (ByteString)ref;
  }
  
  public List<Long> getPromotionIdsListList() {
    return (List<Long>)this.promotionIdsList_;
  }
  
  public int getPromotionIdsListCount() {
    return this.promotionIdsList_.size();
  }
  
  public long getPromotionIdsList(int index) {
    return this.promotionIdsList_.getLong(index);
  }
  
  public String getType() {
    Object ref = this.type_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.type_ = s;
    return s;
  }
  
  public ByteString getTypeBytes() {
    Object ref = this.type_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.type_ = b;
      return b;
    } 
    return (ByteString)ref;
  }
  
  public String getUniqueIndex() {
    Object ref = this.uniqueIndex_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.uniqueIndex_ = s;
    return s;
  }
  
  public ByteString getUniqueIndexBytes() {
    Object ref = this.uniqueIndex_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.uniqueIndex_ = b;
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
    getSerializedSize();
    if (this.id_ != 0)
      output.writeInt32(1, this.id_); 
    if (!GeneratedMessageV3.isStringEmpty(this.name_))
      GeneratedMessageV3.writeString(output, 2, this.name_); 
    if (getPromotionIdsListList().size() > 0) {
      output.writeUInt32NoTag(26);
      output.writeUInt32NoTag(this.promotionIdsListMemoizedSerializedSize);
    } 
    for (int i = 0; i < this.promotionIdsList_.size(); i++)
      output.writeInt64NoTag(this.promotionIdsList_.getLong(i)); 
    if (!GeneratedMessageV3.isStringEmpty(this.type_))
      GeneratedMessageV3.writeString(output, 4, this.type_); 
    if (!GeneratedMessageV3.isStringEmpty(this.uniqueIndex_))
      GeneratedMessageV3.writeString(output, 5, this.uniqueIndex_); 
    getUnknownFields().writeTo(output);
  }
  
  public int getSerializedSize() {
    int size = this.memoizedSize;
    if (size != -1)
      return size; 
    size = 0;
    if (this.id_ != 0)
      size += 
        CodedOutputStream.computeInt32Size(1, this.id_); 
    if (!GeneratedMessageV3.isStringEmpty(this.name_))
      size += GeneratedMessageV3.computeStringSize(2, this.name_); 
    int dataSize = 0;
    for (int i = 0; i < this.promotionIdsList_.size(); i++)
      dataSize += 
        CodedOutputStream.computeInt64SizeNoTag(this.promotionIdsList_.getLong(i)); 
    size += dataSize;
    if (!getPromotionIdsListList().isEmpty()) {
      size++;
      size += 
        CodedOutputStream.computeInt32SizeNoTag(dataSize);
    } 
    this.promotionIdsListMemoizedSerializedSize = dataSize;
    if (!GeneratedMessageV3.isStringEmpty(this.type_))
      size += GeneratedMessageV3.computeStringSize(4, this.type_); 
    if (!GeneratedMessageV3.isStringEmpty(this.uniqueIndex_))
      size += GeneratedMessageV3.computeStringSize(5, this.uniqueIndex_); 
    size += getUnknownFields().getSerializedSize();
    this.memoizedSize = size;
    return size;
  }
  
  public boolean equals(Object obj) {
    if (obj == this)
      return true; 
    if (!(obj instanceof CategoryInfo))
      return super.equals(obj); 
    CategoryInfo other = (CategoryInfo)obj;
    if (getId() != other
      .getId())
      return false; 
    if (!getName().equals(other.getName()))
      return false; 
    if (!getPromotionIdsListList().equals(other.getPromotionIdsListList()))
      return false; 
    if (!getType().equals(other.getType()))
      return false; 
    if (!getUniqueIndex().equals(other.getUniqueIndex()))
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
    hash = 53 * hash + getId();
    hash = 37 * hash + 2;
    hash = 53 * hash + getName().hashCode();
    if (getPromotionIdsListCount() > 0) {
      hash = 37 * hash + 3;
      hash = 53 * hash + getPromotionIdsListList().hashCode();
    } 
    hash = 37 * hash + 4;
    hash = 53 * hash + getType().hashCode();
    hash = 37 * hash + 5;
    hash = 53 * hash + getUniqueIndex().hashCode();
    hash = 29 * hash + getUnknownFields().hashCode();
    this.memoizedHashCode = hash;
    return hash;
  }
  
  public static CategoryInfo parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
    return (CategoryInfo)PARSER.parseFrom(data);
  }
  
  public static CategoryInfo parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (CategoryInfo)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static CategoryInfo parseFrom(ByteString data) throws InvalidProtocolBufferException {
    return (CategoryInfo)PARSER.parseFrom(data);
  }
  
  public static CategoryInfo parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (CategoryInfo)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static CategoryInfo parseFrom(byte[] data) throws InvalidProtocolBufferException {
    return (CategoryInfo)PARSER.parseFrom(data);
  }
  
  public static CategoryInfo parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (CategoryInfo)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static CategoryInfo parseFrom(InputStream input) throws IOException {
    return 
      (CategoryInfo)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static CategoryInfo parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (CategoryInfo)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static CategoryInfo parseDelimitedFrom(InputStream input) throws IOException {
    return 
      (CategoryInfo)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
  }
  
  public static CategoryInfo parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (CategoryInfo)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static CategoryInfo parseFrom(CodedInputStream input) throws IOException {
    return 
      (CategoryInfo)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static CategoryInfo parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (CategoryInfo)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public Builder newBuilderForType() {
    return newBuilder();
  }
  
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  
  public static Builder newBuilder(CategoryInfo prototype) {
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
  
  public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements CategoryInfoOrBuilder {
    private int bitField0_;
    
    private int id_;
    
    private Object name_;
    
    private Internal.LongList promotionIdsList_;
    
    private Object type_;
    
    private Object uniqueIndex_;
    
    public static final Descriptors.Descriptor getDescriptor() {
      return Douyin.internal_static_CategoryInfo_descriptor;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return Douyin.internal_static_CategoryInfo_fieldAccessorTable
        .ensureFieldAccessorsInitialized(CategoryInfo.class, Builder.class);
    }
    
    private Builder() {
      this.name_ = "";
      this.promotionIdsList_ = CategoryInfo.emptyLongList();
      this.type_ = "";
      this.uniqueIndex_ = "";
    }
    
    private Builder(GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      this.name_ = "";
      this.promotionIdsList_ = CategoryInfo.emptyLongList();
      this.type_ = "";
      this.uniqueIndex_ = "";
    }
    
    public Builder clear() {
      super.clear();
      this.bitField0_ = 0;
      this.id_ = 0;
      this.name_ = "";
      this.promotionIdsList_ = CategoryInfo.emptyLongList();
      this.type_ = "";
      this.uniqueIndex_ = "";
      return this;
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
      return Douyin.internal_static_CategoryInfo_descriptor;
    }
    
    public CategoryInfo getDefaultInstanceForType() {
      return CategoryInfo.getDefaultInstance();
    }
    
    public CategoryInfo build() {
      CategoryInfo result = buildPartial();
      if (!result.isInitialized())
        throw newUninitializedMessageException(result); 
      return result;
    }
    
    public CategoryInfo buildPartial() {
      CategoryInfo result = new CategoryInfo(this);
      if (this.bitField0_ != 0)
        buildPartial0(result); 
      onBuilt();
      return result;
    }
    
    private void buildPartial0(CategoryInfo result) {
      int from_bitField0_ = this.bitField0_;
      if ((from_bitField0_ & 0x1) != 0)
        result.id_ = this.id_; 
      if ((from_bitField0_ & 0x2) != 0)
        result.name_ = this.name_; 
      if ((from_bitField0_ & 0x4) != 0) {
        this.promotionIdsList_.makeImmutable();
        result.promotionIdsList_ = this.promotionIdsList_;
      } 
      if ((from_bitField0_ & 0x8) != 0)
        result.type_ = this.type_; 
      if ((from_bitField0_ & 0x10) != 0)
        result.uniqueIndex_ = this.uniqueIndex_; 
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
      if (other instanceof CategoryInfo)
        return mergeFrom((CategoryInfo)other); 
      super.mergeFrom(other);
      return this;
    }
    
    public Builder mergeFrom(CategoryInfo other) {
      if (other == CategoryInfo.getDefaultInstance())
        return this; 
      if (other.getId() != 0)
        setId(other.getId()); 
      if (!other.getName().isEmpty()) {
        this.name_ = other.name_;
        this.bitField0_ |= 0x2;
        onChanged();
      } 
      if (!other.promotionIdsList_.isEmpty()) {
        if (this.promotionIdsList_.isEmpty()) {
          this.promotionIdsList_ = other.promotionIdsList_;
          this.promotionIdsList_.makeImmutable();
          this.bitField0_ |= 0x4;
        } else {
          ensurePromotionIdsListIsMutable();
          this.promotionIdsList_.addAll((Collection)other.promotionIdsList_);
        } 
        onChanged();
      } 
      if (!other.getType().isEmpty()) {
        this.type_ = other.type_;
        this.bitField0_ |= 0x8;
        onChanged();
      } 
      if (!other.getUniqueIndex().isEmpty()) {
        this.uniqueIndex_ = other.uniqueIndex_;
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
          long v;
          int length, limit, tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              continue;
            case 8:
              this.id_ = input.readInt32();
              this.bitField0_ |= 0x1;
              continue;
            case 18:
              this.name_ = input.readStringRequireUtf8();
              this.bitField0_ |= 0x2;
              continue;
            case 24:
              v = input.readInt64();
              ensurePromotionIdsListIsMutable();
              this.promotionIdsList_.addLong(v);
              continue;
            case 26:
              length = input.readRawVarint32();
              limit = input.pushLimit(length);
              ensurePromotionIdsListIsMutable();
              while (input.getBytesUntilLimit() > 0)
                this.promotionIdsList_.addLong(input.readInt64()); 
              input.popLimit(limit);
              continue;
            case 34:
              this.type_ = input.readStringRequireUtf8();
              this.bitField0_ |= 0x8;
              continue;
            case 42:
              this.uniqueIndex_ = input.readStringRequireUtf8();
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
    
    public int getId() {
      return this.id_;
    }
    
    public Builder setId(int value) {
      this.id_ = value;
      this.bitField0_ |= 0x1;
      onChanged();
      return this;
    }
    
    public Builder clearId() {
      this.bitField0_ &= 0xFFFFFFFE;
      this.id_ = 0;
      onChanged();
      return this;
    }
    
    public String getName() {
      Object ref = this.name_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.name_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getNameBytes() {
      Object ref = this.name_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.name_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setName(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.name_ = value;
      this.bitField0_ |= 0x2;
      onChanged();
      return this;
    }
    
    public Builder clearName() {
      this.name_ = CategoryInfo.getDefaultInstance().getName();
      this.bitField0_ &= 0xFFFFFFFD;
      onChanged();
      return this;
    }
    
    public Builder setNameBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      CategoryInfo.checkByteStringIsUtf8(value);
      this.name_ = value;
      this.bitField0_ |= 0x2;
      onChanged();
      return this;
    }
    
    private void ensurePromotionIdsListIsMutable() {
      if (!this.promotionIdsList_.isModifiable())
        this.promotionIdsList_ = (Internal.LongList)CategoryInfo.makeMutableCopy((Internal.ProtobufList)this.promotionIdsList_); 
      this.bitField0_ |= 0x4;
    }
    
    public List<Long> getPromotionIdsListList() {
      this.promotionIdsList_.makeImmutable();
      return (List<Long>)this.promotionIdsList_;
    }
    
    public int getPromotionIdsListCount() {
      return this.promotionIdsList_.size();
    }
    
    public long getPromotionIdsList(int index) {
      return this.promotionIdsList_.getLong(index);
    }
    
    public Builder setPromotionIdsList(int index, long value) {
      ensurePromotionIdsListIsMutable();
      this.promotionIdsList_.setLong(index, value);
      this.bitField0_ |= 0x4;
      onChanged();
      return this;
    }
    
    public Builder addPromotionIdsList(long value) {
      ensurePromotionIdsListIsMutable();
      this.promotionIdsList_.addLong(value);
      this.bitField0_ |= 0x4;
      onChanged();
      return this;
    }
    
    public Builder addAllPromotionIdsList(Iterable<? extends Long> values) {
      ensurePromotionIdsListIsMutable();
      AbstractMessageLite.Builder.addAll(values, (List)this.promotionIdsList_);
      this.bitField0_ |= 0x4;
      onChanged();
      return this;
    }
    
    public Builder clearPromotionIdsList() {
      this.promotionIdsList_ = CategoryInfo.emptyLongList();
      this.bitField0_ &= 0xFFFFFFFB;
      onChanged();
      return this;
    }
    
    public String getType() {
      Object ref = this.type_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.type_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getTypeBytes() {
      Object ref = this.type_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.type_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setType(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.type_ = value;
      this.bitField0_ |= 0x8;
      onChanged();
      return this;
    }
    
    public Builder clearType() {
      this.type_ = CategoryInfo.getDefaultInstance().getType();
      this.bitField0_ &= 0xFFFFFFF7;
      onChanged();
      return this;
    }
    
    public Builder setTypeBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      CategoryInfo.checkByteStringIsUtf8(value);
      this.type_ = value;
      this.bitField0_ |= 0x8;
      onChanged();
      return this;
    }
    
    public String getUniqueIndex() {
      Object ref = this.uniqueIndex_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.uniqueIndex_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getUniqueIndexBytes() {
      Object ref = this.uniqueIndex_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.uniqueIndex_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setUniqueIndex(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.uniqueIndex_ = value;
      this.bitField0_ |= 0x10;
      onChanged();
      return this;
    }
    
    public Builder clearUniqueIndex() {
      this.uniqueIndex_ = CategoryInfo.getDefaultInstance().getUniqueIndex();
      this.bitField0_ &= 0xFFFFFFEF;
      onChanged();
      return this;
    }
    
    public Builder setUniqueIndexBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      CategoryInfo.checkByteStringIsUtf8(value);
      this.uniqueIndex_ = value;
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
  
  private static final CategoryInfo DEFAULT_INSTANCE = new CategoryInfo();
  
  public static CategoryInfo getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }
  
  private static final Parser<CategoryInfo> PARSER = (Parser<CategoryInfo>)new AbstractParser<CategoryInfo>() {
      public CategoryInfo parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        CategoryInfo.Builder builder = CategoryInfo.newBuilder();
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
  
  public static Parser<CategoryInfo> parser() {
    return PARSER;
  }
  
  public Parser<CategoryInfo> getParserForType() {
    return PARSER;
  }
  
  public CategoryInfo getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }
}
