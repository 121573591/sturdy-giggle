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

public final class ProductChangeMessage extends GeneratedMessageV3 implements ProductChangeMessageOrBuilder {
  private static final long serialVersionUID = 0L;
  
  private int bitField0_;
  
  public static final int COMMON_FIELD_NUMBER = 1;
  
  private Common common_;
  
  public static final int UPDATETIMESTAMP_FIELD_NUMBER = 2;
  
  private long updateTimestamp_;
  
  public static final int UPDATETOAST_FIELD_NUMBER = 3;
  
  private volatile Object updateToast_;
  
  public static final int UPDATEPRODUCTINFOLIST_FIELD_NUMBER = 4;
  
  private List<ProductInfo> updateProductInfoList_;
  
  public static final int TOTAL_FIELD_NUMBER = 5;
  
  private long total_;
  
  public static final int UPDATECATEGORYINFOLIST_FIELD_NUMBER = 8;
  
  private List<CategoryInfo> updateCategoryInfoList_;
  
  private byte memoizedIsInitialized;
  
  private ProductChangeMessage(GeneratedMessageV3.Builder<?> builder) {
    super(builder);
    this.updateTimestamp_ = 0L;
    this.updateToast_ = "";
    this.total_ = 0L;
    this.memoizedIsInitialized = -1;
  }
  
  private ProductChangeMessage() {
    this.updateTimestamp_ = 0L;
    this.updateToast_ = "";
    this.total_ = 0L;
    this.memoizedIsInitialized = -1;
    this.updateToast_ = "";
    this.updateProductInfoList_ = Collections.emptyList();
    this.updateCategoryInfoList_ = Collections.emptyList();
  }
  
  protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
    return new ProductChangeMessage();
  }
  
  public static final Descriptors.Descriptor getDescriptor() {
    return Douyin.internal_static_ProductChangeMessage_descriptor;
  }
  
  protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
    return Douyin.internal_static_ProductChangeMessage_fieldAccessorTable.ensureFieldAccessorsInitialized(ProductChangeMessage.class, Builder.class);
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
  
  public long getUpdateTimestamp() {
    return this.updateTimestamp_;
  }
  
  public String getUpdateToast() {
    Object ref = this.updateToast_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.updateToast_ = s;
    return s;
  }
  
  public ByteString getUpdateToastBytes() {
    Object ref = this.updateToast_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.updateToast_ = b;
      return b;
    } 
    return (ByteString)ref;
  }
  
  public List<ProductInfo> getUpdateProductInfoListList() {
    return this.updateProductInfoList_;
  }
  
  public List<? extends ProductInfoOrBuilder> getUpdateProductInfoListOrBuilderList() {
    return (List)this.updateProductInfoList_;
  }
  
  public int getUpdateProductInfoListCount() {
    return this.updateProductInfoList_.size();
  }
  
  public ProductInfo getUpdateProductInfoList(int index) {
    return this.updateProductInfoList_.get(index);
  }
  
  public ProductInfoOrBuilder getUpdateProductInfoListOrBuilder(int index) {
    return this.updateProductInfoList_.get(index);
  }
  
  public long getTotal() {
    return this.total_;
  }
  
  public List<CategoryInfo> getUpdateCategoryInfoListList() {
    return this.updateCategoryInfoList_;
  }
  
  public List<? extends CategoryInfoOrBuilder> getUpdateCategoryInfoListOrBuilderList() {
    return (List)this.updateCategoryInfoList_;
  }
  
  public int getUpdateCategoryInfoListCount() {
    return this.updateCategoryInfoList_.size();
  }
  
  public CategoryInfo getUpdateCategoryInfoList(int index) {
    return this.updateCategoryInfoList_.get(index);
  }
  
  public CategoryInfoOrBuilder getUpdateCategoryInfoListOrBuilder(int index) {
    return this.updateCategoryInfoList_.get(index);
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
    if (this.updateTimestamp_ != 0L)
      output.writeInt64(2, this.updateTimestamp_); 
    if (!GeneratedMessageV3.isStringEmpty(this.updateToast_))
      GeneratedMessageV3.writeString(output, 3, this.updateToast_); 
    int i;
    for (i = 0; i < this.updateProductInfoList_.size(); i++)
      output.writeMessage(4, (MessageLite)this.updateProductInfoList_.get(i)); 
    if (this.total_ != 0L)
      output.writeInt64(5, this.total_); 
    for (i = 0; i < this.updateCategoryInfoList_.size(); i++)
      output.writeMessage(8, (MessageLite)this.updateCategoryInfoList_.get(i)); 
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
    if (this.updateTimestamp_ != 0L)
      size += 
        CodedOutputStream.computeInt64Size(2, this.updateTimestamp_); 
    if (!GeneratedMessageV3.isStringEmpty(this.updateToast_))
      size += GeneratedMessageV3.computeStringSize(3, this.updateToast_); 
    int i;
    for (i = 0; i < this.updateProductInfoList_.size(); i++)
      size += 
        CodedOutputStream.computeMessageSize(4, (MessageLite)this.updateProductInfoList_.get(i)); 
    if (this.total_ != 0L)
      size += 
        CodedOutputStream.computeInt64Size(5, this.total_); 
    for (i = 0; i < this.updateCategoryInfoList_.size(); i++)
      size += 
        CodedOutputStream.computeMessageSize(8, (MessageLite)this.updateCategoryInfoList_.get(i)); 
    size += getUnknownFields().getSerializedSize();
    this.memoizedSize = size;
    return size;
  }
  
  public boolean equals(Object obj) {
    if (obj == this)
      return true; 
    if (!(obj instanceof ProductChangeMessage))
      return super.equals(obj); 
    ProductChangeMessage other = (ProductChangeMessage)obj;
    if (hasCommon() != other.hasCommon())
      return false; 
    if (hasCommon() && 
      
      !getCommon().equals(other.getCommon()))
      return false; 
    if (getUpdateTimestamp() != other
      .getUpdateTimestamp())
      return false; 
    if (!getUpdateToast().equals(other.getUpdateToast()))
      return false; 
    if (!getUpdateProductInfoListList().equals(other.getUpdateProductInfoListList()))
      return false; 
    if (getTotal() != other
      .getTotal())
      return false; 
    if (!getUpdateCategoryInfoListList().equals(other.getUpdateCategoryInfoListList()))
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
    hash = 37 * hash + 2;
    hash = 53 * hash + Internal.hashLong(
        getUpdateTimestamp());
    hash = 37 * hash + 3;
    hash = 53 * hash + getUpdateToast().hashCode();
    if (getUpdateProductInfoListCount() > 0) {
      hash = 37 * hash + 4;
      hash = 53 * hash + getUpdateProductInfoListList().hashCode();
    } 
    hash = 37 * hash + 5;
    hash = 53 * hash + Internal.hashLong(
        getTotal());
    if (getUpdateCategoryInfoListCount() > 0) {
      hash = 37 * hash + 8;
      hash = 53 * hash + getUpdateCategoryInfoListList().hashCode();
    } 
    hash = 29 * hash + getUnknownFields().hashCode();
    this.memoizedHashCode = hash;
    return hash;
  }
  
  public static ProductChangeMessage parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
    return (ProductChangeMessage)PARSER.parseFrom(data);
  }
  
  public static ProductChangeMessage parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (ProductChangeMessage)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static ProductChangeMessage parseFrom(ByteString data) throws InvalidProtocolBufferException {
    return (ProductChangeMessage)PARSER.parseFrom(data);
  }
  
  public static ProductChangeMessage parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (ProductChangeMessage)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static ProductChangeMessage parseFrom(byte[] data) throws InvalidProtocolBufferException {
    return (ProductChangeMessage)PARSER.parseFrom(data);
  }
  
  public static ProductChangeMessage parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (ProductChangeMessage)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static ProductChangeMessage parseFrom(InputStream input) throws IOException {
    return 
      (ProductChangeMessage)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static ProductChangeMessage parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (ProductChangeMessage)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static ProductChangeMessage parseDelimitedFrom(InputStream input) throws IOException {
    return 
      (ProductChangeMessage)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
  }
  
  public static ProductChangeMessage parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (ProductChangeMessage)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static ProductChangeMessage parseFrom(CodedInputStream input) throws IOException {
    return 
      (ProductChangeMessage)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static ProductChangeMessage parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (ProductChangeMessage)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public Builder newBuilderForType() {
    return newBuilder();
  }
  
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  
  public static Builder newBuilder(ProductChangeMessage prototype) {
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
  
  public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ProductChangeMessageOrBuilder {
    private int bitField0_;
    
    private Common common_;
    
    private SingleFieldBuilderV3<Common, Common.Builder, CommonOrBuilder> commonBuilder_;
    
    private long updateTimestamp_;
    
    private Object updateToast_;
    
    private List<ProductInfo> updateProductInfoList_;
    
    private RepeatedFieldBuilderV3<ProductInfo, ProductInfo.Builder, ProductInfoOrBuilder> updateProductInfoListBuilder_;
    
    private long total_;
    
    private List<CategoryInfo> updateCategoryInfoList_;
    
    private RepeatedFieldBuilderV3<CategoryInfo, CategoryInfo.Builder, CategoryInfoOrBuilder> updateCategoryInfoListBuilder_;
    
    public static final Descriptors.Descriptor getDescriptor() {
      return Douyin.internal_static_ProductChangeMessage_descriptor;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return Douyin.internal_static_ProductChangeMessage_fieldAccessorTable
        .ensureFieldAccessorsInitialized(ProductChangeMessage.class, Builder.class);
    }
    
    private Builder() {
      this.updateToast_ = "";
      this
        .updateProductInfoList_ = Collections.emptyList();
      this
        .updateCategoryInfoList_ = Collections.emptyList();
      maybeForceBuilderInitialization();
    }
    
    private Builder(GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      this.updateToast_ = "";
      this.updateProductInfoList_ = Collections.emptyList();
      this.updateCategoryInfoList_ = Collections.emptyList();
      maybeForceBuilderInitialization();
    }
    
    private void maybeForceBuilderInitialization() {
      if (ProductChangeMessage.alwaysUseFieldBuilders) {
        getCommonFieldBuilder();
        getUpdateProductInfoListFieldBuilder();
        getUpdateCategoryInfoListFieldBuilder();
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
      this.updateTimestamp_ = 0L;
      this.updateToast_ = "";
      if (this.updateProductInfoListBuilder_ == null) {
        this.updateProductInfoList_ = Collections.emptyList();
      } else {
        this.updateProductInfoList_ = null;
        this.updateProductInfoListBuilder_.clear();
      } 
      this.bitField0_ &= 0xFFFFFFF7;
      this.total_ = 0L;
      if (this.updateCategoryInfoListBuilder_ == null) {
        this.updateCategoryInfoList_ = Collections.emptyList();
      } else {
        this.updateCategoryInfoList_ = null;
        this.updateCategoryInfoListBuilder_.clear();
      } 
      this.bitField0_ &= 0xFFFFFFDF;
      return this;
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
      return Douyin.internal_static_ProductChangeMessage_descriptor;
    }
    
    public ProductChangeMessage getDefaultInstanceForType() {
      return ProductChangeMessage.getDefaultInstance();
    }
    
    public ProductChangeMessage build() {
      ProductChangeMessage result = buildPartial();
      if (!result.isInitialized())
        throw newUninitializedMessageException(result); 
      return result;
    }
    
    public ProductChangeMessage buildPartial() {
      ProductChangeMessage result = new ProductChangeMessage(this);
      buildPartialRepeatedFields(result);
      if (this.bitField0_ != 0)
        buildPartial0(result); 
      onBuilt();
      return result;
    }
    
    private void buildPartialRepeatedFields(ProductChangeMessage result) {
      if (this.updateProductInfoListBuilder_ == null) {
        if ((this.bitField0_ & 0x8) != 0) {
          this.updateProductInfoList_ = Collections.unmodifiableList(this.updateProductInfoList_);
          this.bitField0_ &= 0xFFFFFFF7;
        } 
        result.updateProductInfoList_ = this.updateProductInfoList_;
      } else {
        result.updateProductInfoList_ = this.updateProductInfoListBuilder_.build();
      } 
      if (this.updateCategoryInfoListBuilder_ == null) {
        if ((this.bitField0_ & 0x20) != 0) {
          this.updateCategoryInfoList_ = Collections.unmodifiableList(this.updateCategoryInfoList_);
          this.bitField0_ &= 0xFFFFFFDF;
        } 
        result.updateCategoryInfoList_ = this.updateCategoryInfoList_;
      } else {
        result.updateCategoryInfoList_ = this.updateCategoryInfoListBuilder_.build();
      } 
    }
    
    private void buildPartial0(ProductChangeMessage result) {
      int from_bitField0_ = this.bitField0_;
      int to_bitField0_ = 0;
      if ((from_bitField0_ & 0x1) != 0) {
        result.common_ = (this.commonBuilder_ == null) ? this.common_ : (Common)this.commonBuilder_.build();
        to_bitField0_ |= 0x1;
      } 
      if ((from_bitField0_ & 0x2) != 0)
        result.updateTimestamp_ = this.updateTimestamp_; 
      if ((from_bitField0_ & 0x4) != 0)
        result.updateToast_ = this.updateToast_; 
      if ((from_bitField0_ & 0x10) != 0)
        result.total_ = this.total_; 
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
      if (other instanceof ProductChangeMessage)
        return mergeFrom((ProductChangeMessage)other); 
      super.mergeFrom(other);
      return this;
    }
    
    public Builder mergeFrom(ProductChangeMessage other) {
      if (other == ProductChangeMessage.getDefaultInstance())
        return this; 
      if (other.hasCommon())
        mergeCommon(other.getCommon()); 
      if (other.getUpdateTimestamp() != 0L)
        setUpdateTimestamp(other.getUpdateTimestamp()); 
      if (!other.getUpdateToast().isEmpty()) {
        this.updateToast_ = other.updateToast_;
        this.bitField0_ |= 0x4;
        onChanged();
      } 
      if (this.updateProductInfoListBuilder_ == null) {
        if (!other.updateProductInfoList_.isEmpty()) {
          if (this.updateProductInfoList_.isEmpty()) {
            this.updateProductInfoList_ = other.updateProductInfoList_;
            this.bitField0_ &= 0xFFFFFFF7;
          } else {
            ensureUpdateProductInfoListIsMutable();
            this.updateProductInfoList_.addAll(other.updateProductInfoList_);
          } 
          onChanged();
        } 
      } else if (!other.updateProductInfoList_.isEmpty()) {
        if (this.updateProductInfoListBuilder_.isEmpty()) {
          this.updateProductInfoListBuilder_.dispose();
          this.updateProductInfoListBuilder_ = null;
          this.updateProductInfoList_ = other.updateProductInfoList_;
          this.bitField0_ &= 0xFFFFFFF7;
          this.updateProductInfoListBuilder_ = ProductChangeMessage.alwaysUseFieldBuilders ? getUpdateProductInfoListFieldBuilder() : null;
        } else {
          this.updateProductInfoListBuilder_.addAllMessages(other.updateProductInfoList_);
        } 
      } 
      if (other.getTotal() != 0L)
        setTotal(other.getTotal()); 
      if (this.updateCategoryInfoListBuilder_ == null) {
        if (!other.updateCategoryInfoList_.isEmpty()) {
          if (this.updateCategoryInfoList_.isEmpty()) {
            this.updateCategoryInfoList_ = other.updateCategoryInfoList_;
            this.bitField0_ &= 0xFFFFFFDF;
          } else {
            ensureUpdateCategoryInfoListIsMutable();
            this.updateCategoryInfoList_.addAll(other.updateCategoryInfoList_);
          } 
          onChanged();
        } 
      } else if (!other.updateCategoryInfoList_.isEmpty()) {
        if (this.updateCategoryInfoListBuilder_.isEmpty()) {
          this.updateCategoryInfoListBuilder_.dispose();
          this.updateCategoryInfoListBuilder_ = null;
          this.updateCategoryInfoList_ = other.updateCategoryInfoList_;
          this.bitField0_ &= 0xFFFFFFDF;
          this.updateCategoryInfoListBuilder_ = ProductChangeMessage.alwaysUseFieldBuilders ? getUpdateCategoryInfoListFieldBuilder() : null;
        } else {
          this.updateCategoryInfoListBuilder_.addAllMessages(other.updateCategoryInfoList_);
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
          ProductInfo productInfo;
          CategoryInfo m;
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              continue;
            case 10:
              input.readMessage((MessageLite.Builder)getCommonFieldBuilder().getBuilder(), extensionRegistry);
              this.bitField0_ |= 0x1;
              continue;
            case 16:
              this.updateTimestamp_ = input.readInt64();
              this.bitField0_ |= 0x2;
              continue;
            case 26:
              this.updateToast_ = input.readStringRequireUtf8();
              this.bitField0_ |= 0x4;
              continue;
            case 34:
              productInfo = (ProductInfo)input.readMessage(ProductInfo.parser(), extensionRegistry);
              if (this.updateProductInfoListBuilder_ == null) {
                ensureUpdateProductInfoListIsMutable();
                this.updateProductInfoList_.add(productInfo);
                continue;
              } 
              this.updateProductInfoListBuilder_.addMessage((AbstractMessage)productInfo);
              continue;
            case 40:
              this.total_ = input.readInt64();
              this.bitField0_ |= 0x10;
              continue;
            case 66:
              m = (CategoryInfo)input.readMessage(CategoryInfo.parser(), extensionRegistry);
              if (this.updateCategoryInfoListBuilder_ == null) {
                ensureUpdateCategoryInfoListIsMutable();
                this.updateCategoryInfoList_.add(m);
                continue;
              } 
              this.updateCategoryInfoListBuilder_.addMessage((AbstractMessage)m);
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
    
    public long getUpdateTimestamp() {
      return this.updateTimestamp_;
    }
    
    public Builder setUpdateTimestamp(long value) {
      this.updateTimestamp_ = value;
      this.bitField0_ |= 0x2;
      onChanged();
      return this;
    }
    
    public Builder clearUpdateTimestamp() {
      this.bitField0_ &= 0xFFFFFFFD;
      this.updateTimestamp_ = 0L;
      onChanged();
      return this;
    }
    
    public String getUpdateToast() {
      Object ref = this.updateToast_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.updateToast_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getUpdateToastBytes() {
      Object ref = this.updateToast_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.updateToast_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setUpdateToast(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.updateToast_ = value;
      this.bitField0_ |= 0x4;
      onChanged();
      return this;
    }
    
    public Builder clearUpdateToast() {
      this.updateToast_ = ProductChangeMessage.getDefaultInstance().getUpdateToast();
      this.bitField0_ &= 0xFFFFFFFB;
      onChanged();
      return this;
    }
    
    public Builder setUpdateToastBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      ProductChangeMessage.checkByteStringIsUtf8(value);
      this.updateToast_ = value;
      this.bitField0_ |= 0x4;
      onChanged();
      return this;
    }
    
    private void ensureUpdateProductInfoListIsMutable() {
      if ((this.bitField0_ & 0x8) == 0) {
        this.updateProductInfoList_ = new ArrayList<>(this.updateProductInfoList_);
        this.bitField0_ |= 0x8;
      } 
    }
    
    public List<ProductInfo> getUpdateProductInfoListList() {
      if (this.updateProductInfoListBuilder_ == null)
        return Collections.unmodifiableList(this.updateProductInfoList_); 
      return this.updateProductInfoListBuilder_.getMessageList();
    }
    
    public int getUpdateProductInfoListCount() {
      if (this.updateProductInfoListBuilder_ == null)
        return this.updateProductInfoList_.size(); 
      return this.updateProductInfoListBuilder_.getCount();
    }
    
    public ProductInfo getUpdateProductInfoList(int index) {
      if (this.updateProductInfoListBuilder_ == null)
        return this.updateProductInfoList_.get(index); 
      return (ProductInfo)this.updateProductInfoListBuilder_.getMessage(index);
    }
    
    public Builder setUpdateProductInfoList(int index, ProductInfo value) {
      if (this.updateProductInfoListBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        ensureUpdateProductInfoListIsMutable();
        this.updateProductInfoList_.set(index, value);
        onChanged();
      } else {
        this.updateProductInfoListBuilder_.setMessage(index, (AbstractMessage)value);
      } 
      return this;
    }
    
    public Builder setUpdateProductInfoList(int index, ProductInfo.Builder builderForValue) {
      if (this.updateProductInfoListBuilder_ == null) {
        ensureUpdateProductInfoListIsMutable();
        this.updateProductInfoList_.set(index, builderForValue.build());
        onChanged();
      } else {
        this.updateProductInfoListBuilder_.setMessage(index, (AbstractMessage)builderForValue.build());
      } 
      return this;
    }
    
    public Builder addUpdateProductInfoList(ProductInfo value) {
      if (this.updateProductInfoListBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        ensureUpdateProductInfoListIsMutable();
        this.updateProductInfoList_.add(value);
        onChanged();
      } else {
        this.updateProductInfoListBuilder_.addMessage((AbstractMessage)value);
      } 
      return this;
    }
    
    public Builder addUpdateProductInfoList(int index, ProductInfo value) {
      if (this.updateProductInfoListBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        ensureUpdateProductInfoListIsMutable();
        this.updateProductInfoList_.add(index, value);
        onChanged();
      } else {
        this.updateProductInfoListBuilder_.addMessage(index, (AbstractMessage)value);
      } 
      return this;
    }
    
    public Builder addUpdateProductInfoList(ProductInfo.Builder builderForValue) {
      if (this.updateProductInfoListBuilder_ == null) {
        ensureUpdateProductInfoListIsMutable();
        this.updateProductInfoList_.add(builderForValue.build());
        onChanged();
      } else {
        this.updateProductInfoListBuilder_.addMessage((AbstractMessage)builderForValue.build());
      } 
      return this;
    }
    
    public Builder addUpdateProductInfoList(int index, ProductInfo.Builder builderForValue) {
      if (this.updateProductInfoListBuilder_ == null) {
        ensureUpdateProductInfoListIsMutable();
        this.updateProductInfoList_.add(index, builderForValue.build());
        onChanged();
      } else {
        this.updateProductInfoListBuilder_.addMessage(index, (AbstractMessage)builderForValue.build());
      } 
      return this;
    }
    
    public Builder addAllUpdateProductInfoList(Iterable<? extends ProductInfo> values) {
      if (this.updateProductInfoListBuilder_ == null) {
        ensureUpdateProductInfoListIsMutable();
        AbstractMessageLite.Builder.addAll(values, this.updateProductInfoList_);
        onChanged();
      } else {
        this.updateProductInfoListBuilder_.addAllMessages(values);
      } 
      return this;
    }
    
    public Builder clearUpdateProductInfoList() {
      if (this.updateProductInfoListBuilder_ == null) {
        this.updateProductInfoList_ = Collections.emptyList();
        this.bitField0_ &= 0xFFFFFFF7;
        onChanged();
      } else {
        this.updateProductInfoListBuilder_.clear();
      } 
      return this;
    }
    
    public Builder removeUpdateProductInfoList(int index) {
      if (this.updateProductInfoListBuilder_ == null) {
        ensureUpdateProductInfoListIsMutable();
        this.updateProductInfoList_.remove(index);
        onChanged();
      } else {
        this.updateProductInfoListBuilder_.remove(index);
      } 
      return this;
    }
    
    public ProductInfo.Builder getUpdateProductInfoListBuilder(int index) {
      return (ProductInfo.Builder)getUpdateProductInfoListFieldBuilder().getBuilder(index);
    }
    
    public ProductInfoOrBuilder getUpdateProductInfoListOrBuilder(int index) {
      if (this.updateProductInfoListBuilder_ == null)
        return this.updateProductInfoList_.get(index); 
      return (ProductInfoOrBuilder)this.updateProductInfoListBuilder_.getMessageOrBuilder(index);
    }
    
    public List<? extends ProductInfoOrBuilder> getUpdateProductInfoListOrBuilderList() {
      if (this.updateProductInfoListBuilder_ != null)
        return this.updateProductInfoListBuilder_.getMessageOrBuilderList(); 
      return Collections.unmodifiableList((List)this.updateProductInfoList_);
    }
    
    public ProductInfo.Builder addUpdateProductInfoListBuilder() {
      return (ProductInfo.Builder)getUpdateProductInfoListFieldBuilder().addBuilder((AbstractMessage)ProductInfo.getDefaultInstance());
    }
    
    public ProductInfo.Builder addUpdateProductInfoListBuilder(int index) {
      return (ProductInfo.Builder)getUpdateProductInfoListFieldBuilder().addBuilder(index, (AbstractMessage)ProductInfo.getDefaultInstance());
    }
    
    public List<ProductInfo.Builder> getUpdateProductInfoListBuilderList() {
      return getUpdateProductInfoListFieldBuilder().getBuilderList();
    }
    
    private RepeatedFieldBuilderV3<ProductInfo, ProductInfo.Builder, ProductInfoOrBuilder> getUpdateProductInfoListFieldBuilder() {
      if (this.updateProductInfoListBuilder_ == null) {
        this.updateProductInfoListBuilder_ = new RepeatedFieldBuilderV3(this.updateProductInfoList_, ((this.bitField0_ & 0x8) != 0), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.updateProductInfoList_ = null;
      } 
      return this.updateProductInfoListBuilder_;
    }
    
    public long getTotal() {
      return this.total_;
    }
    
    public Builder setTotal(long value) {
      this.total_ = value;
      this.bitField0_ |= 0x10;
      onChanged();
      return this;
    }
    
    public Builder clearTotal() {
      this.bitField0_ &= 0xFFFFFFEF;
      this.total_ = 0L;
      onChanged();
      return this;
    }
    
    private void ensureUpdateCategoryInfoListIsMutable() {
      if ((this.bitField0_ & 0x20) == 0) {
        this.updateCategoryInfoList_ = new ArrayList<>(this.updateCategoryInfoList_);
        this.bitField0_ |= 0x20;
      } 
    }
    
    public List<CategoryInfo> getUpdateCategoryInfoListList() {
      if (this.updateCategoryInfoListBuilder_ == null)
        return Collections.unmodifiableList(this.updateCategoryInfoList_); 
      return this.updateCategoryInfoListBuilder_.getMessageList();
    }
    
    public int getUpdateCategoryInfoListCount() {
      if (this.updateCategoryInfoListBuilder_ == null)
        return this.updateCategoryInfoList_.size(); 
      return this.updateCategoryInfoListBuilder_.getCount();
    }
    
    public CategoryInfo getUpdateCategoryInfoList(int index) {
      if (this.updateCategoryInfoListBuilder_ == null)
        return this.updateCategoryInfoList_.get(index); 
      return (CategoryInfo)this.updateCategoryInfoListBuilder_.getMessage(index);
    }
    
    public Builder setUpdateCategoryInfoList(int index, CategoryInfo value) {
      if (this.updateCategoryInfoListBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        ensureUpdateCategoryInfoListIsMutable();
        this.updateCategoryInfoList_.set(index, value);
        onChanged();
      } else {
        this.updateCategoryInfoListBuilder_.setMessage(index, (AbstractMessage)value);
      } 
      return this;
    }
    
    public Builder setUpdateCategoryInfoList(int index, CategoryInfo.Builder builderForValue) {
      if (this.updateCategoryInfoListBuilder_ == null) {
        ensureUpdateCategoryInfoListIsMutable();
        this.updateCategoryInfoList_.set(index, builderForValue.build());
        onChanged();
      } else {
        this.updateCategoryInfoListBuilder_.setMessage(index, (AbstractMessage)builderForValue.build());
      } 
      return this;
    }
    
    public Builder addUpdateCategoryInfoList(CategoryInfo value) {
      if (this.updateCategoryInfoListBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        ensureUpdateCategoryInfoListIsMutable();
        this.updateCategoryInfoList_.add(value);
        onChanged();
      } else {
        this.updateCategoryInfoListBuilder_.addMessage((AbstractMessage)value);
      } 
      return this;
    }
    
    public Builder addUpdateCategoryInfoList(int index, CategoryInfo value) {
      if (this.updateCategoryInfoListBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        ensureUpdateCategoryInfoListIsMutable();
        this.updateCategoryInfoList_.add(index, value);
        onChanged();
      } else {
        this.updateCategoryInfoListBuilder_.addMessage(index, (AbstractMessage)value);
      } 
      return this;
    }
    
    public Builder addUpdateCategoryInfoList(CategoryInfo.Builder builderForValue) {
      if (this.updateCategoryInfoListBuilder_ == null) {
        ensureUpdateCategoryInfoListIsMutable();
        this.updateCategoryInfoList_.add(builderForValue.build());
        onChanged();
      } else {
        this.updateCategoryInfoListBuilder_.addMessage((AbstractMessage)builderForValue.build());
      } 
      return this;
    }
    
    public Builder addUpdateCategoryInfoList(int index, CategoryInfo.Builder builderForValue) {
      if (this.updateCategoryInfoListBuilder_ == null) {
        ensureUpdateCategoryInfoListIsMutable();
        this.updateCategoryInfoList_.add(index, builderForValue.build());
        onChanged();
      } else {
        this.updateCategoryInfoListBuilder_.addMessage(index, (AbstractMessage)builderForValue.build());
      } 
      return this;
    }
    
    public Builder addAllUpdateCategoryInfoList(Iterable<? extends CategoryInfo> values) {
      if (this.updateCategoryInfoListBuilder_ == null) {
        ensureUpdateCategoryInfoListIsMutable();
        AbstractMessageLite.Builder.addAll(values, this.updateCategoryInfoList_);
        onChanged();
      } else {
        this.updateCategoryInfoListBuilder_.addAllMessages(values);
      } 
      return this;
    }
    
    public Builder clearUpdateCategoryInfoList() {
      if (this.updateCategoryInfoListBuilder_ == null) {
        this.updateCategoryInfoList_ = Collections.emptyList();
        this.bitField0_ &= 0xFFFFFFDF;
        onChanged();
      } else {
        this.updateCategoryInfoListBuilder_.clear();
      } 
      return this;
    }
    
    public Builder removeUpdateCategoryInfoList(int index) {
      if (this.updateCategoryInfoListBuilder_ == null) {
        ensureUpdateCategoryInfoListIsMutable();
        this.updateCategoryInfoList_.remove(index);
        onChanged();
      } else {
        this.updateCategoryInfoListBuilder_.remove(index);
      } 
      return this;
    }
    
    public CategoryInfo.Builder getUpdateCategoryInfoListBuilder(int index) {
      return (CategoryInfo.Builder)getUpdateCategoryInfoListFieldBuilder().getBuilder(index);
    }
    
    public CategoryInfoOrBuilder getUpdateCategoryInfoListOrBuilder(int index) {
      if (this.updateCategoryInfoListBuilder_ == null)
        return this.updateCategoryInfoList_.get(index); 
      return (CategoryInfoOrBuilder)this.updateCategoryInfoListBuilder_.getMessageOrBuilder(index);
    }
    
    public List<? extends CategoryInfoOrBuilder> getUpdateCategoryInfoListOrBuilderList() {
      if (this.updateCategoryInfoListBuilder_ != null)
        return this.updateCategoryInfoListBuilder_.getMessageOrBuilderList(); 
      return Collections.unmodifiableList((List)this.updateCategoryInfoList_);
    }
    
    public CategoryInfo.Builder addUpdateCategoryInfoListBuilder() {
      return (CategoryInfo.Builder)getUpdateCategoryInfoListFieldBuilder().addBuilder(
          (AbstractMessage)CategoryInfo.getDefaultInstance());
    }
    
    public CategoryInfo.Builder addUpdateCategoryInfoListBuilder(int index) {
      return (CategoryInfo.Builder)getUpdateCategoryInfoListFieldBuilder().addBuilder(index, 
          (AbstractMessage)CategoryInfo.getDefaultInstance());
    }
    
    public List<CategoryInfo.Builder> getUpdateCategoryInfoListBuilderList() {
      return getUpdateCategoryInfoListFieldBuilder().getBuilderList();
    }
    
    private RepeatedFieldBuilderV3<CategoryInfo, CategoryInfo.Builder, CategoryInfoOrBuilder> getUpdateCategoryInfoListFieldBuilder() {
      if (this.updateCategoryInfoListBuilder_ == null) {
        this
          
          .updateCategoryInfoListBuilder_ = new RepeatedFieldBuilderV3(this.updateCategoryInfoList_, ((this.bitField0_ & 0x20) != 0), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.updateCategoryInfoList_ = null;
      } 
      return this.updateCategoryInfoListBuilder_;
    }
    
    public final Builder setUnknownFields(UnknownFieldSet unknownFields) {
      return (Builder)super.setUnknownFields(unknownFields);
    }
    
    public final Builder mergeUnknownFields(UnknownFieldSet unknownFields) {
      return (Builder)super.mergeUnknownFields(unknownFields);
    }
  }
  
  private static final ProductChangeMessage DEFAULT_INSTANCE = new ProductChangeMessage();
  
  public static ProductChangeMessage getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }
  
  private static final Parser<ProductChangeMessage> PARSER = (Parser<ProductChangeMessage>)new AbstractParser<ProductChangeMessage>() {
      public ProductChangeMessage parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        ProductChangeMessage.Builder builder = ProductChangeMessage.newBuilder();
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
  
  public static Parser<ProductChangeMessage> parser() {
    return PARSER;
  }
  
  public Parser<ProductChangeMessage> getParserForType() {
    return PARSER;
  }
  
  public ProductChangeMessage getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }
}
