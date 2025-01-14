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
import com.google.protobuf.MapEntry;
import com.google.protobuf.MapField;
import com.google.protobuf.MapFieldBuilder;
import com.google.protobuf.MapFieldReflectionAccessor;
import com.google.protobuf.Message;
import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import com.google.protobuf.WireFormat;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Map;

public final class FansClub extends GeneratedMessageV3 implements FansClubOrBuilder {
  private static final long serialVersionUID = 0L;
  
  private int bitField0_;
  
  public static final int DATA_FIELD_NUMBER = 1;
  
  private FansClubData data_;
  
  public static final int PREFERDATA_FIELD_NUMBER = 2;
  
  private MapField<Integer, FansClubData> preferData_;
  
  private byte memoizedIsInitialized;
  
  private FansClub(GeneratedMessageV3.Builder<?> builder) {
    super(builder);
    this.memoizedIsInitialized = -1;
  }
  
  private FansClub() {
    this.memoizedIsInitialized = -1;
  }
  
  protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
    return new FansClub();
  }
  
  public static final Descriptors.Descriptor getDescriptor() {
    return Douyin.internal_static_FansClub_descriptor;
  }
  
  protected MapFieldReflectionAccessor internalGetMapFieldReflection(int number) {
    switch (number) {
      case 2:
        return (MapFieldReflectionAccessor)internalGetPreferData();
    } 
    throw new RuntimeException("Invalid map field number: " + number);
  }
  
  protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
    return Douyin.internal_static_FansClub_fieldAccessorTable.ensureFieldAccessorsInitialized(FansClub.class, Builder.class);
  }
  
  public boolean hasData() {
    return ((this.bitField0_ & 0x1) != 0);
  }
  
  public FansClubData getData() {
    return (this.data_ == null) ? FansClubData.getDefaultInstance() : this.data_;
  }
  
  public FansClubDataOrBuilder getDataOrBuilder() {
    return (this.data_ == null) ? FansClubData.getDefaultInstance() : this.data_;
  }
  
  private static final class PreferDataDefaultEntryHolder {
    static final MapEntry<Integer, FansClubData> defaultEntry = MapEntry.newDefaultInstance(Douyin.internal_static_FansClub_PreferDataEntry_descriptor, WireFormat.FieldType.INT32, Integer.valueOf(0), WireFormat.FieldType.MESSAGE, FansClubData.getDefaultInstance());
  }
  
  private MapField<Integer, FansClubData> internalGetPreferData() {
    if (this.preferData_ == null)
      return MapField.emptyMapField(PreferDataDefaultEntryHolder.defaultEntry); 
    return this.preferData_;
  }
  
  public int getPreferDataCount() {
    return internalGetPreferData().getMap().size();
  }
  
  public boolean containsPreferData(int key) {
    return internalGetPreferData().getMap().containsKey(Integer.valueOf(key));
  }
  
  @Deprecated
  public Map<Integer, FansClubData> getPreferData() {
    return getPreferDataMap();
  }
  
  public Map<Integer, FansClubData> getPreferDataMap() {
    return internalGetPreferData().getMap();
  }
  
  public FansClubData getPreferDataOrDefault(int key, FansClubData defaultValue) {
    Map<Integer, FansClubData> map = internalGetPreferData().getMap();
    return map.containsKey(Integer.valueOf(key)) ? map.get(Integer.valueOf(key)) : defaultValue;
  }
  
  public FansClubData getPreferDataOrThrow(int key) {
    Map<Integer, FansClubData> map = internalGetPreferData().getMap();
    if (!map.containsKey(Integer.valueOf(key)))
      throw new IllegalArgumentException(); 
    return map.get(Integer.valueOf(key));
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
      output.writeMessage(1, (MessageLite)getData()); 
    GeneratedMessageV3.serializeIntegerMapTo(output, 
        
        internalGetPreferData(), PreferDataDefaultEntryHolder.defaultEntry, 2);
    getUnknownFields().writeTo(output);
  }
  
  public int getSerializedSize() {
    int size = this.memoizedSize;
    if (size != -1)
      return size; 
    size = 0;
    if ((this.bitField0_ & 0x1) != 0)
      size += 
        CodedOutputStream.computeMessageSize(1, (MessageLite)getData()); 
    for (Map.Entry<Integer, FansClubData> entry : (Iterable<Map.Entry<Integer, FansClubData>>)internalGetPreferData().getMap().entrySet()) {
      MapEntry<Integer, FansClubData> preferData__ = PreferDataDefaultEntryHolder.defaultEntry.newBuilderForType().setKey(entry.getKey()).setValue(entry.getValue()).build();
      size += 
        CodedOutputStream.computeMessageSize(2, (MessageLite)preferData__);
    } 
    size += getUnknownFields().getSerializedSize();
    this.memoizedSize = size;
    return size;
  }
  
  public boolean equals(Object obj) {
    if (obj == this)
      return true; 
    if (!(obj instanceof FansClub))
      return super.equals(obj); 
    FansClub other = (FansClub)obj;
    if (hasData() != other.hasData())
      return false; 
    if (hasData() && 
      
      !getData().equals(other.getData()))
      return false; 
    if (!internalGetPreferData().equals(other
        .internalGetPreferData()))
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
    if (hasData()) {
      hash = 37 * hash + 1;
      hash = 53 * hash + getData().hashCode();
    } 
    if (!internalGetPreferData().getMap().isEmpty()) {
      hash = 37 * hash + 2;
      hash = 53 * hash + internalGetPreferData().hashCode();
    } 
    hash = 29 * hash + getUnknownFields().hashCode();
    this.memoizedHashCode = hash;
    return hash;
  }
  
  public static FansClub parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
    return (FansClub)PARSER.parseFrom(data);
  }
  
  public static FansClub parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (FansClub)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static FansClub parseFrom(ByteString data) throws InvalidProtocolBufferException {
    return (FansClub)PARSER.parseFrom(data);
  }
  
  public static FansClub parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (FansClub)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static FansClub parseFrom(byte[] data) throws InvalidProtocolBufferException {
    return (FansClub)PARSER.parseFrom(data);
  }
  
  public static FansClub parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (FansClub)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static FansClub parseFrom(InputStream input) throws IOException {
    return 
      (FansClub)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static FansClub parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (FansClub)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static FansClub parseDelimitedFrom(InputStream input) throws IOException {
    return 
      (FansClub)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
  }
  
  public static FansClub parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (FansClub)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static FansClub parseFrom(CodedInputStream input) throws IOException {
    return 
      (FansClub)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static FansClub parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (FansClub)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public Builder newBuilderForType() {
    return newBuilder();
  }
  
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  
  public static Builder newBuilder(FansClub prototype) {
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
  
  public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements FansClubOrBuilder {
    private int bitField0_;
    
    private FansClubData data_;
    
    private SingleFieldBuilderV3<FansClubData, FansClubData.Builder, FansClubDataOrBuilder> dataBuilder_;
    
    public static final Descriptors.Descriptor getDescriptor() {
      return Douyin.internal_static_FansClub_descriptor;
    }
    
    protected MapFieldReflectionAccessor internalGetMapFieldReflection(int number) {
      switch (number) {
        case 2:
          return (MapFieldReflectionAccessor)internalGetPreferData();
      } 
      throw new RuntimeException("Invalid map field number: " + number);
    }
    
    protected MapFieldReflectionAccessor internalGetMutableMapFieldReflection(int number) {
      switch (number) {
        case 2:
          return (MapFieldReflectionAccessor)internalGetMutablePreferData();
      } 
      throw new RuntimeException("Invalid map field number: " + number);
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return Douyin.internal_static_FansClub_fieldAccessorTable
        .ensureFieldAccessorsInitialized(FansClub.class, Builder.class);
    }
    
    private Builder() {
      maybeForceBuilderInitialization();
    }
    
    private Builder(GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    
    private void maybeForceBuilderInitialization() {
      if (FansClub.alwaysUseFieldBuilders)
        getDataFieldBuilder(); 
    }
    
    public Builder clear() {
      super.clear();
      this.bitField0_ = 0;
      this.data_ = null;
      if (this.dataBuilder_ != null) {
        this.dataBuilder_.dispose();
        this.dataBuilder_ = null;
      } 
      internalGetMutablePreferData().clear();
      return this;
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
      return Douyin.internal_static_FansClub_descriptor;
    }
    
    public FansClub getDefaultInstanceForType() {
      return FansClub.getDefaultInstance();
    }
    
    public FansClub build() {
      FansClub result = buildPartial();
      if (!result.isInitialized())
        throw newUninitializedMessageException(result); 
      return result;
    }
    
    public FansClub buildPartial() {
      FansClub result = new FansClub(this);
      if (this.bitField0_ != 0)
        buildPartial0(result); 
      onBuilt();
      return result;
    }
    
    private void buildPartial0(FansClub result) {
      int from_bitField0_ = this.bitField0_;
      int to_bitField0_ = 0;
      if ((from_bitField0_ & 0x1) != 0) {
        result.data_ = (this.dataBuilder_ == null) ? 
          this.data_ : 
          (FansClubData)this.dataBuilder_.build();
        to_bitField0_ |= 0x1;
      } 
      if ((from_bitField0_ & 0x2) != 0)
        result.preferData_ = internalGetPreferData().build(FansClub.PreferDataDefaultEntryHolder.defaultEntry); 
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
      if (other instanceof FansClub)
        return mergeFrom((FansClub)other); 
      super.mergeFrom(other);
      return this;
    }
    
    public Builder mergeFrom(FansClub other) {
      if (other == FansClub.getDefaultInstance())
        return this; 
      if (other.hasData())
        mergeData(other.getData()); 
      internalGetMutablePreferData().mergeFrom(other
          .internalGetPreferData());
      this.bitField0_ |= 0x2;
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
          MapEntry<Integer, FansClubData> preferData__;
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              continue;
            case 10:
              input.readMessage((MessageLite.Builder)
                  getDataFieldBuilder().getBuilder(), extensionRegistry);
              this.bitField0_ |= 0x1;
              continue;
            case 18:
              preferData__ = (MapEntry<Integer, FansClubData>)input.readMessage(FansClub.PreferDataDefaultEntryHolder.defaultEntry
                  .getParserForType(), extensionRegistry);
              internalGetMutablePreferData().ensureBuilderMap().put((Integer)preferData__
                  .getKey(), (FansClubDataOrBuilder)preferData__.getValue());
              this.bitField0_ |= 0x2;
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
    
    public boolean hasData() {
      return ((this.bitField0_ & 0x1) != 0);
    }
    
    public FansClubData getData() {
      if (this.dataBuilder_ == null)
        return (this.data_ == null) ? FansClubData.getDefaultInstance() : this.data_; 
      return (FansClubData)this.dataBuilder_.getMessage();
    }
    
    public Builder setData(FansClubData value) {
      if (this.dataBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        this.data_ = value;
      } else {
        this.dataBuilder_.setMessage((AbstractMessage)value);
      } 
      this.bitField0_ |= 0x1;
      onChanged();
      return this;
    }
    
    public Builder setData(FansClubData.Builder builderForValue) {
      if (this.dataBuilder_ == null) {
        this.data_ = builderForValue.build();
      } else {
        this.dataBuilder_.setMessage((AbstractMessage)builderForValue.build());
      } 
      this.bitField0_ |= 0x1;
      onChanged();
      return this;
    }
    
    public Builder mergeData(FansClubData value) {
      if (this.dataBuilder_ == null) {
        if ((this.bitField0_ & 0x1) != 0 && this.data_ != null && this.data_ != 
          
          FansClubData.getDefaultInstance()) {
          getDataBuilder().mergeFrom(value);
        } else {
          this.data_ = value;
        } 
      } else {
        this.dataBuilder_.mergeFrom((AbstractMessage)value);
      } 
      if (this.data_ != null) {
        this.bitField0_ |= 0x1;
        onChanged();
      } 
      return this;
    }
    
    public Builder clearData() {
      this.bitField0_ &= 0xFFFFFFFE;
      this.data_ = null;
      if (this.dataBuilder_ != null) {
        this.dataBuilder_.dispose();
        this.dataBuilder_ = null;
      } 
      onChanged();
      return this;
    }
    
    public FansClubData.Builder getDataBuilder() {
      this.bitField0_ |= 0x1;
      onChanged();
      return (FansClubData.Builder)getDataFieldBuilder().getBuilder();
    }
    
    public FansClubDataOrBuilder getDataOrBuilder() {
      if (this.dataBuilder_ != null)
        return (FansClubDataOrBuilder)this.dataBuilder_.getMessageOrBuilder(); 
      return (this.data_ == null) ? 
        FansClubData.getDefaultInstance() : this.data_;
    }
    
    private SingleFieldBuilderV3<FansClubData, FansClubData.Builder, FansClubDataOrBuilder> getDataFieldBuilder() {
      if (this.dataBuilder_ == null) {
        this
          
          .dataBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getData(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.data_ = null;
      } 
      return this.dataBuilder_;
    }
    
    private static final class PreferDataConverter implements MapFieldBuilder.Converter<Integer, FansClubDataOrBuilder, FansClubData> {
      private PreferDataConverter() {}
      
      public FansClubData build(FansClubDataOrBuilder val) {
        if (val instanceof FansClubData)
          return (FansClubData)val; 
        return ((FansClubData.Builder)val).build();
      }
      
      public MapEntry<Integer, FansClubData> defaultEntry() {
        return FansClub.PreferDataDefaultEntryHolder.defaultEntry;
      }
    }
    
    private static final PreferDataConverter preferDataConverter = new PreferDataConverter();
    
    private MapFieldBuilder<Integer, FansClubDataOrBuilder, FansClubData, FansClubData.Builder> preferData_;
    
    private MapFieldBuilder<Integer, FansClubDataOrBuilder, FansClubData, FansClubData.Builder> internalGetPreferData() {
      if (this.preferData_ == null)
        return new MapFieldBuilder(preferDataConverter); 
      return this.preferData_;
    }
    
    private MapFieldBuilder<Integer, FansClubDataOrBuilder, FansClubData, FansClubData.Builder> internalGetMutablePreferData() {
      if (this.preferData_ == null)
        this.preferData_ = new MapFieldBuilder(preferDataConverter); 
      this.bitField0_ |= 0x2;
      onChanged();
      return this.preferData_;
    }
    
    public int getPreferDataCount() {
      return internalGetPreferData().ensureBuilderMap().size();
    }
    
    public boolean containsPreferData(int key) {
      return internalGetPreferData().ensureBuilderMap().containsKey(Integer.valueOf(key));
    }
    
    @Deprecated
    public Map<Integer, FansClubData> getPreferData() {
      return getPreferDataMap();
    }
    
    public Map<Integer, FansClubData> getPreferDataMap() {
      return internalGetPreferData().getImmutableMap();
    }
    
    public FansClubData getPreferDataOrDefault(int key, FansClubData defaultValue) {
      Map<Integer, FansClubDataOrBuilder> map = internalGetMutablePreferData().ensureBuilderMap();
      return map.containsKey(Integer.valueOf(key)) ? preferDataConverter.build(map.get(Integer.valueOf(key))) : defaultValue;
    }
    
    public FansClubData getPreferDataOrThrow(int key) {
      Map<Integer, FansClubDataOrBuilder> map = internalGetMutablePreferData().ensureBuilderMap();
      if (!map.containsKey(Integer.valueOf(key)))
        throw new IllegalArgumentException(); 
      return preferDataConverter.build(map.get(Integer.valueOf(key)));
    }
    
    public Builder clearPreferData() {
      this.bitField0_ &= 0xFFFFFFFD;
      internalGetMutablePreferData().clear();
      return this;
    }
    
    public Builder removePreferData(int key) {
      internalGetMutablePreferData().ensureBuilderMap()
        .remove(Integer.valueOf(key));
      return this;
    }
    
    @Deprecated
    public Map<Integer, FansClubData> getMutablePreferData() {
      this.bitField0_ |= 0x2;
      return internalGetMutablePreferData().ensureMessageMap();
    }
    
    public Builder putPreferData(int key, FansClubData value) {
      if (value == null)
        throw new NullPointerException("map value"); 
      internalGetMutablePreferData().ensureBuilderMap()
        .put(Integer.valueOf(key), value);
      this.bitField0_ |= 0x2;
      return this;
    }
    
    public Builder putAllPreferData(Map<Integer, FansClubData> values) {
      for (Map.Entry<Integer, FansClubData> e : values.entrySet()) {
        if (e.getKey() == null || e.getValue() == null)
          throw new NullPointerException(); 
      } 
      internalGetMutablePreferData().ensureBuilderMap()
        .putAll(values);
      this.bitField0_ |= 0x2;
      return this;
    }
    
    public FansClubData.Builder putPreferDataBuilderIfAbsent(int key) {
      Map<Integer, FansClubDataOrBuilder> builderMap = internalGetMutablePreferData().ensureBuilderMap();
      FansClubDataOrBuilder entry = builderMap.get(Integer.valueOf(key));
      if (entry == null) {
        entry = FansClubData.newBuilder();
        builderMap.put(Integer.valueOf(key), entry);
      } 
      if (entry instanceof FansClubData) {
        entry = ((FansClubData)entry).toBuilder();
        builderMap.put(Integer.valueOf(key), entry);
      } 
      return (FansClubData.Builder)entry;
    }
    
    public final Builder setUnknownFields(UnknownFieldSet unknownFields) {
      return (Builder)super.setUnknownFields(unknownFields);
    }
    
    public final Builder mergeUnknownFields(UnknownFieldSet unknownFields) {
      return (Builder)super.mergeUnknownFields(unknownFields);
    }
  }
  
  private static final FansClub DEFAULT_INSTANCE = new FansClub();
  
  public static FansClub getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }
  
  private static final Parser<FansClub> PARSER = (Parser<FansClub>)new AbstractParser<FansClub>() {
      public FansClub parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        FansClub.Builder builder = FansClub.newBuilder();
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
  
  public static Parser<FansClub> parser() {
    return PARSER;
  }
  
  public Parser<FansClub> getParserForType() {
    return PARSER;
  }
  
  public FansClub getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }
}
