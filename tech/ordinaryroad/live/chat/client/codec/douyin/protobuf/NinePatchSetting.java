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
import com.google.protobuf.LazyStringArrayList;
import com.google.protobuf.LazyStringList;
import com.google.protobuf.Message;
import com.google.protobuf.MessageLite;
import com.google.protobuf.Parser;
import com.google.protobuf.ProtocolStringList;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.List;

public final class NinePatchSetting extends GeneratedMessageV3 implements NinePatchSettingOrBuilder {
  private static final long serialVersionUID = 0L;
  
  public static final int SETTINGLISTLIST_FIELD_NUMBER = 1;
  
  private LazyStringArrayList settingListList_;
  
  private byte memoizedIsInitialized;
  
  private NinePatchSetting(GeneratedMessageV3.Builder<?> builder) {
    super(builder);
    this
      
      .settingListList_ = LazyStringArrayList.emptyList();
    this.memoizedIsInitialized = -1;
  }
  
  private NinePatchSetting() {
    this.settingListList_ = LazyStringArrayList.emptyList();
    this.memoizedIsInitialized = -1;
    this.settingListList_ = LazyStringArrayList.emptyList();
  }
  
  protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
    return new NinePatchSetting();
  }
  
  public static final Descriptors.Descriptor getDescriptor() {
    return Douyin.internal_static_NinePatchSetting_descriptor;
  }
  
  protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
    return Douyin.internal_static_NinePatchSetting_fieldAccessorTable.ensureFieldAccessorsInitialized(NinePatchSetting.class, Builder.class);
  }
  
  public ProtocolStringList getSettingListListList() {
    return (ProtocolStringList)this.settingListList_;
  }
  
  public int getSettingListListCount() {
    return this.settingListList_.size();
  }
  
  public String getSettingListList(int index) {
    return this.settingListList_.get(index);
  }
  
  public ByteString getSettingListListBytes(int index) {
    return this.settingListList_.getByteString(index);
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
    for (int i = 0; i < this.settingListList_.size(); i++)
      GeneratedMessageV3.writeString(output, 1, this.settingListList_.getRaw(i)); 
    getUnknownFields().writeTo(output);
  }
  
  public int getSerializedSize() {
    int size = this.memoizedSize;
    if (size != -1)
      return size; 
    size = 0;
    int dataSize = 0;
    for (int i = 0; i < this.settingListList_.size(); i++)
      dataSize += computeStringSizeNoTag(this.settingListList_.getRaw(i)); 
    size += dataSize;
    size += 1 * getSettingListListList().size();
    size += getUnknownFields().getSerializedSize();
    this.memoizedSize = size;
    return size;
  }
  
  public boolean equals(Object obj) {
    if (obj == this)
      return true; 
    if (!(obj instanceof NinePatchSetting))
      return super.equals(obj); 
    NinePatchSetting other = (NinePatchSetting)obj;
    if (!getSettingListListList().equals(other.getSettingListListList()))
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
    if (getSettingListListCount() > 0) {
      hash = 37 * hash + 1;
      hash = 53 * hash + getSettingListListList().hashCode();
    } 
    hash = 29 * hash + getUnknownFields().hashCode();
    this.memoizedHashCode = hash;
    return hash;
  }
  
  public static NinePatchSetting parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
    return (NinePatchSetting)PARSER.parseFrom(data);
  }
  
  public static NinePatchSetting parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (NinePatchSetting)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static NinePatchSetting parseFrom(ByteString data) throws InvalidProtocolBufferException {
    return (NinePatchSetting)PARSER.parseFrom(data);
  }
  
  public static NinePatchSetting parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (NinePatchSetting)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static NinePatchSetting parseFrom(byte[] data) throws InvalidProtocolBufferException {
    return (NinePatchSetting)PARSER.parseFrom(data);
  }
  
  public static NinePatchSetting parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (NinePatchSetting)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static NinePatchSetting parseFrom(InputStream input) throws IOException {
    return 
      (NinePatchSetting)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static NinePatchSetting parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (NinePatchSetting)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static NinePatchSetting parseDelimitedFrom(InputStream input) throws IOException {
    return 
      (NinePatchSetting)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
  }
  
  public static NinePatchSetting parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (NinePatchSetting)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static NinePatchSetting parseFrom(CodedInputStream input) throws IOException {
    return 
      (NinePatchSetting)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static NinePatchSetting parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (NinePatchSetting)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public Builder newBuilderForType() {
    return newBuilder();
  }
  
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  
  public static Builder newBuilder(NinePatchSetting prototype) {
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
  
  public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements NinePatchSettingOrBuilder {
    private int bitField0_;
    
    private LazyStringArrayList settingListList_;
    
    public static final Descriptors.Descriptor getDescriptor() {
      return Douyin.internal_static_NinePatchSetting_descriptor;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return Douyin.internal_static_NinePatchSetting_fieldAccessorTable
        .ensureFieldAccessorsInitialized(NinePatchSetting.class, Builder.class);
    }
    
    private Builder() {
      this
        .settingListList_ = LazyStringArrayList.emptyList();
    }
    
    private Builder(GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      this.settingListList_ = LazyStringArrayList.emptyList();
    }
    
    public Builder clear() {
      super.clear();
      this.bitField0_ = 0;
      this.settingListList_ = LazyStringArrayList.emptyList();
      return this;
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
      return Douyin.internal_static_NinePatchSetting_descriptor;
    }
    
    public NinePatchSetting getDefaultInstanceForType() {
      return NinePatchSetting.getDefaultInstance();
    }
    
    public NinePatchSetting build() {
      NinePatchSetting result = buildPartial();
      if (!result.isInitialized())
        throw newUninitializedMessageException(result); 
      return result;
    }
    
    public NinePatchSetting buildPartial() {
      NinePatchSetting result = new NinePatchSetting(this);
      if (this.bitField0_ != 0)
        buildPartial0(result); 
      onBuilt();
      return result;
    }
    
    private void buildPartial0(NinePatchSetting result) {
      int from_bitField0_ = this.bitField0_;
      if ((from_bitField0_ & 0x1) != 0) {
        this.settingListList_.makeImmutable();
        result.settingListList_ = this.settingListList_;
      } 
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
      if (other instanceof NinePatchSetting)
        return mergeFrom((NinePatchSetting)other); 
      super.mergeFrom(other);
      return this;
    }
    
    public Builder mergeFrom(NinePatchSetting other) {
      if (other == NinePatchSetting.getDefaultInstance())
        return this; 
      if (!other.settingListList_.isEmpty()) {
        if (this.settingListList_.isEmpty()) {
          this.settingListList_ = other.settingListList_;
          this.bitField0_ |= 0x1;
        } else {
          ensureSettingListListIsMutable();
          this.settingListList_.addAll((Collection)other.settingListList_);
        } 
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
          String s;
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              continue;
            case 10:
              s = input.readStringRequireUtf8();
              ensureSettingListListIsMutable();
              this.settingListList_.add(s);
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
    
    private void ensureSettingListListIsMutable() {
      if (!this.settingListList_.isModifiable())
        this.settingListList_ = new LazyStringArrayList((LazyStringList)this.settingListList_); 
      this.bitField0_ |= 0x1;
    }
    
    public ProtocolStringList getSettingListListList() {
      this.settingListList_.makeImmutable();
      return (ProtocolStringList)this.settingListList_;
    }
    
    public int getSettingListListCount() {
      return this.settingListList_.size();
    }
    
    public String getSettingListList(int index) {
      return this.settingListList_.get(index);
    }
    
    public ByteString getSettingListListBytes(int index) {
      return this.settingListList_.getByteString(index);
    }
    
    public Builder setSettingListList(int index, String value) {
      if (value == null)
        throw new NullPointerException(); 
      ensureSettingListListIsMutable();
      this.settingListList_.set(index, value);
      this.bitField0_ |= 0x1;
      onChanged();
      return this;
    }
    
    public Builder addSettingListList(String value) {
      if (value == null)
        throw new NullPointerException(); 
      ensureSettingListListIsMutable();
      this.settingListList_.add(value);
      this.bitField0_ |= 0x1;
      onChanged();
      return this;
    }
    
    public Builder addAllSettingListList(Iterable<String> values) {
      ensureSettingListListIsMutable();
      AbstractMessageLite.Builder.addAll(values, (List)this.settingListList_);
      this.bitField0_ |= 0x1;
      onChanged();
      return this;
    }
    
    public Builder clearSettingListList() {
      this
        .settingListList_ = LazyStringArrayList.emptyList();
      this.bitField0_ &= 0xFFFFFFFE;
      onChanged();
      return this;
    }
    
    public Builder addSettingListListBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      NinePatchSetting.checkByteStringIsUtf8(value);
      ensureSettingListListIsMutable();
      this.settingListList_.add(value);
      this.bitField0_ |= 0x1;
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
  
  private static final NinePatchSetting DEFAULT_INSTANCE = new NinePatchSetting();
  
  public static NinePatchSetting getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }
  
  private static final Parser<NinePatchSetting> PARSER = (Parser<NinePatchSetting>)new AbstractParser<NinePatchSetting>() {
      public NinePatchSetting parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        NinePatchSetting.Builder builder = NinePatchSetting.newBuilder();
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
  
  public static Parser<NinePatchSetting> parser() {
    return PARSER;
  }
  
  public Parser<NinePatchSetting> getParserForType() {
    return PARSER;
  }
  
  public NinePatchSetting getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }
}
