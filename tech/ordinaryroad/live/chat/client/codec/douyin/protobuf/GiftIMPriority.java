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

public final class GiftIMPriority extends GeneratedMessageV3 implements GiftIMPriorityOrBuilder {
  private static final long serialVersionUID = 0L;
  
  public static final int QUEUESIZESLIST_FIELD_NUMBER = 1;
  
  private Internal.LongList queueSizesList_;
  
  private int queueSizesListMemoizedSerializedSize;
  
  public static final int SELFQUEUEPRIORITY_FIELD_NUMBER = 2;
  
  private long selfQueuePriority_;
  
  public static final int PRIORITY_FIELD_NUMBER = 3;
  
  private long priority_;
  
  private byte memoizedIsInitialized;
  
  private GiftIMPriority(GeneratedMessageV3.Builder<?> builder) {
    super(builder);
    this
      
      .queueSizesList_ = emptyLongList();
    this.queueSizesListMemoizedSerializedSize = -1;
    this.selfQueuePriority_ = 0L;
    this.priority_ = 0L;
    this.memoizedIsInitialized = -1;
  }
  
  private GiftIMPriority() {
    this.queueSizesList_ = emptyLongList();
    this.queueSizesListMemoizedSerializedSize = -1;
    this.selfQueuePriority_ = 0L;
    this.priority_ = 0L;
    this.memoizedIsInitialized = -1;
    this.queueSizesList_ = emptyLongList();
  }
  
  protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
    return new GiftIMPriority();
  }
  
  public static final Descriptors.Descriptor getDescriptor() {
    return Douyin.internal_static_GiftIMPriority_descriptor;
  }
  
  protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
    return Douyin.internal_static_GiftIMPriority_fieldAccessorTable.ensureFieldAccessorsInitialized(GiftIMPriority.class, Builder.class);
  }
  
  public List<Long> getQueueSizesListList() {
    return (List<Long>)this.queueSizesList_;
  }
  
  public int getQueueSizesListCount() {
    return this.queueSizesList_.size();
  }
  
  public long getQueueSizesList(int index) {
    return this.queueSizesList_.getLong(index);
  }
  
  public long getSelfQueuePriority() {
    return this.selfQueuePriority_;
  }
  
  public long getPriority() {
    return this.priority_;
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
    if (getQueueSizesListList().size() > 0) {
      output.writeUInt32NoTag(10);
      output.writeUInt32NoTag(this.queueSizesListMemoizedSerializedSize);
    } 
    for (int i = 0; i < this.queueSizesList_.size(); i++)
      output.writeUInt64NoTag(this.queueSizesList_.getLong(i)); 
    if (this.selfQueuePriority_ != 0L)
      output.writeUInt64(2, this.selfQueuePriority_); 
    if (this.priority_ != 0L)
      output.writeUInt64(3, this.priority_); 
    getUnknownFields().writeTo(output);
  }
  
  public int getSerializedSize() {
    int size = this.memoizedSize;
    if (size != -1)
      return size; 
    size = 0;
    int dataSize = 0;
    for (int i = 0; i < this.queueSizesList_.size(); i++)
      dataSize += 
        CodedOutputStream.computeUInt64SizeNoTag(this.queueSizesList_.getLong(i)); 
    size += dataSize;
    if (!getQueueSizesListList().isEmpty()) {
      size++;
      size += 
        CodedOutputStream.computeInt32SizeNoTag(dataSize);
    } 
    this.queueSizesListMemoizedSerializedSize = dataSize;
    if (this.selfQueuePriority_ != 0L)
      size += 
        CodedOutputStream.computeUInt64Size(2, this.selfQueuePriority_); 
    if (this.priority_ != 0L)
      size += 
        CodedOutputStream.computeUInt64Size(3, this.priority_); 
    size += getUnknownFields().getSerializedSize();
    this.memoizedSize = size;
    return size;
  }
  
  public boolean equals(Object obj) {
    if (obj == this)
      return true; 
    if (!(obj instanceof GiftIMPriority))
      return super.equals(obj); 
    GiftIMPriority other = (GiftIMPriority)obj;
    if (!getQueueSizesListList().equals(other.getQueueSizesListList()))
      return false; 
    if (getSelfQueuePriority() != other
      .getSelfQueuePriority())
      return false; 
    if (getPriority() != other
      .getPriority())
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
    if (getQueueSizesListCount() > 0) {
      hash = 37 * hash + 1;
      hash = 53 * hash + getQueueSizesListList().hashCode();
    } 
    hash = 37 * hash + 2;
    hash = 53 * hash + Internal.hashLong(
        getSelfQueuePriority());
    hash = 37 * hash + 3;
    hash = 53 * hash + Internal.hashLong(
        getPriority());
    hash = 29 * hash + getUnknownFields().hashCode();
    this.memoizedHashCode = hash;
    return hash;
  }
  
  public static GiftIMPriority parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
    return (GiftIMPriority)PARSER.parseFrom(data);
  }
  
  public static GiftIMPriority parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (GiftIMPriority)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static GiftIMPriority parseFrom(ByteString data) throws InvalidProtocolBufferException {
    return (GiftIMPriority)PARSER.parseFrom(data);
  }
  
  public static GiftIMPriority parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (GiftIMPriority)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static GiftIMPriority parseFrom(byte[] data) throws InvalidProtocolBufferException {
    return (GiftIMPriority)PARSER.parseFrom(data);
  }
  
  public static GiftIMPriority parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (GiftIMPriority)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static GiftIMPriority parseFrom(InputStream input) throws IOException {
    return 
      (GiftIMPriority)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static GiftIMPriority parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (GiftIMPriority)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static GiftIMPriority parseDelimitedFrom(InputStream input) throws IOException {
    return 
      (GiftIMPriority)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
  }
  
  public static GiftIMPriority parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (GiftIMPriority)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static GiftIMPriority parseFrom(CodedInputStream input) throws IOException {
    return 
      (GiftIMPriority)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static GiftIMPriority parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (GiftIMPriority)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public Builder newBuilderForType() {
    return newBuilder();
  }
  
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  
  public static Builder newBuilder(GiftIMPriority prototype) {
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
  
  public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements GiftIMPriorityOrBuilder {
    private int bitField0_;
    
    private Internal.LongList queueSizesList_;
    
    private long selfQueuePriority_;
    
    private long priority_;
    
    public static final Descriptors.Descriptor getDescriptor() {
      return Douyin.internal_static_GiftIMPriority_descriptor;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return Douyin.internal_static_GiftIMPriority_fieldAccessorTable
        .ensureFieldAccessorsInitialized(GiftIMPriority.class, Builder.class);
    }
    
    private Builder() {
      this.queueSizesList_ = GiftIMPriority.emptyLongList();
    }
    
    private Builder(GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      this.queueSizesList_ = GiftIMPriority.emptyLongList();
    }
    
    public Builder clear() {
      super.clear();
      this.bitField0_ = 0;
      this.queueSizesList_ = GiftIMPriority.emptyLongList();
      this.selfQueuePriority_ = 0L;
      this.priority_ = 0L;
      return this;
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
      return Douyin.internal_static_GiftIMPriority_descriptor;
    }
    
    public GiftIMPriority getDefaultInstanceForType() {
      return GiftIMPriority.getDefaultInstance();
    }
    
    public GiftIMPriority build() {
      GiftIMPriority result = buildPartial();
      if (!result.isInitialized())
        throw newUninitializedMessageException(result); 
      return result;
    }
    
    public GiftIMPriority buildPartial() {
      GiftIMPriority result = new GiftIMPriority(this);
      if (this.bitField0_ != 0)
        buildPartial0(result); 
      onBuilt();
      return result;
    }
    
    private void buildPartial0(GiftIMPriority result) {
      int from_bitField0_ = this.bitField0_;
      if ((from_bitField0_ & 0x1) != 0) {
        this.queueSizesList_.makeImmutable();
        result.queueSizesList_ = this.queueSizesList_;
      } 
      if ((from_bitField0_ & 0x2) != 0)
        result.selfQueuePriority_ = this.selfQueuePriority_; 
      if ((from_bitField0_ & 0x4) != 0)
        result.priority_ = this.priority_; 
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
      if (other instanceof GiftIMPriority)
        return mergeFrom((GiftIMPriority)other); 
      super.mergeFrom(other);
      return this;
    }
    
    public Builder mergeFrom(GiftIMPriority other) {
      if (other == GiftIMPriority.getDefaultInstance())
        return this; 
      if (!other.queueSizesList_.isEmpty()) {
        if (this.queueSizesList_.isEmpty()) {
          this.queueSizesList_ = other.queueSizesList_;
          this.queueSizesList_.makeImmutable();
          this.bitField0_ |= 0x1;
        } else {
          ensureQueueSizesListIsMutable();
          this.queueSizesList_.addAll((Collection)other.queueSizesList_);
        } 
        onChanged();
      } 
      if (other.getSelfQueuePriority() != 0L)
        setSelfQueuePriority(other.getSelfQueuePriority()); 
      if (other.getPriority() != 0L)
        setPriority(other.getPriority()); 
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
              v = input.readUInt64();
              ensureQueueSizesListIsMutable();
              this.queueSizesList_.addLong(v);
              continue;
            case 10:
              length = input.readRawVarint32();
              limit = input.pushLimit(length);
              ensureQueueSizesListIsMutable();
              while (input.getBytesUntilLimit() > 0)
                this.queueSizesList_.addLong(input.readUInt64()); 
              input.popLimit(limit);
              continue;
            case 16:
              this.selfQueuePriority_ = input.readUInt64();
              this.bitField0_ |= 0x2;
              continue;
            case 24:
              this.priority_ = input.readUInt64();
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
    
    private void ensureQueueSizesListIsMutable() {
      if (!this.queueSizesList_.isModifiable())
        this.queueSizesList_ = (Internal.LongList)GiftIMPriority.makeMutableCopy((Internal.ProtobufList)this.queueSizesList_); 
      this.bitField0_ |= 0x1;
    }
    
    public List<Long> getQueueSizesListList() {
      this.queueSizesList_.makeImmutable();
      return (List<Long>)this.queueSizesList_;
    }
    
    public int getQueueSizesListCount() {
      return this.queueSizesList_.size();
    }
    
    public long getQueueSizesList(int index) {
      return this.queueSizesList_.getLong(index);
    }
    
    public Builder setQueueSizesList(int index, long value) {
      ensureQueueSizesListIsMutable();
      this.queueSizesList_.setLong(index, value);
      this.bitField0_ |= 0x1;
      onChanged();
      return this;
    }
    
    public Builder addQueueSizesList(long value) {
      ensureQueueSizesListIsMutable();
      this.queueSizesList_.addLong(value);
      this.bitField0_ |= 0x1;
      onChanged();
      return this;
    }
    
    public Builder addAllQueueSizesList(Iterable<? extends Long> values) {
      ensureQueueSizesListIsMutable();
      AbstractMessageLite.Builder.addAll(values, (List)this.queueSizesList_);
      this.bitField0_ |= 0x1;
      onChanged();
      return this;
    }
    
    public Builder clearQueueSizesList() {
      this.queueSizesList_ = GiftIMPriority.emptyLongList();
      this.bitField0_ &= 0xFFFFFFFE;
      onChanged();
      return this;
    }
    
    public long getSelfQueuePriority() {
      return this.selfQueuePriority_;
    }
    
    public Builder setSelfQueuePriority(long value) {
      this.selfQueuePriority_ = value;
      this.bitField0_ |= 0x2;
      onChanged();
      return this;
    }
    
    public Builder clearSelfQueuePriority() {
      this.bitField0_ &= 0xFFFFFFFD;
      this.selfQueuePriority_ = 0L;
      onChanged();
      return this;
    }
    
    public long getPriority() {
      return this.priority_;
    }
    
    public Builder setPriority(long value) {
      this.priority_ = value;
      this.bitField0_ |= 0x4;
      onChanged();
      return this;
    }
    
    public Builder clearPriority() {
      this.bitField0_ &= 0xFFFFFFFB;
      this.priority_ = 0L;
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
  
  private static final GiftIMPriority DEFAULT_INSTANCE = new GiftIMPriority();
  
  public static GiftIMPriority getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }
  
  private static final Parser<GiftIMPriority> PARSER = (Parser<GiftIMPriority>)new AbstractParser<GiftIMPriority>() {
      public GiftIMPriority parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        GiftIMPriority.Builder builder = GiftIMPriority.newBuilder();
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
  
  public static Parser<GiftIMPriority> parser() {
    return PARSER;
  }
  
  public Parser<GiftIMPriority> getParserForType() {
    return PARSER;
  }
  
  public GiftIMPriority getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }
}
