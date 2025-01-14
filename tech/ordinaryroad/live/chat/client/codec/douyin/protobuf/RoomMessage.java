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
import com.google.protobuf.MapEntry;
import com.google.protobuf.MapField;
import com.google.protobuf.MapFieldReflectionAccessor;
import com.google.protobuf.Message;
import com.google.protobuf.MessageLite;
import com.google.protobuf.Parser;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import com.google.protobuf.WireFormat;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Map;

public final class RoomMessage extends GeneratedMessageV3 implements RoomMessageOrBuilder {
  private static final long serialVersionUID = 0L;
  
  private int bitField0_;
  
  public static final int COMMON_FIELD_NUMBER = 1;
  
  private Common common_;
  
  public static final int CONTENT_FIELD_NUMBER = 2;
  
  private volatile Object content_;
  
  public static final int SUPPROTLANDSCAPE_FIELD_NUMBER = 3;
  
  private boolean supprotLandscape_;
  
  public static final int ROOMMESSAGETYPE_FIELD_NUMBER = 4;
  
  private int roommessagetype_;
  
  public static final int SYSTEMTOPMSG_FIELD_NUMBER = 5;
  
  private boolean systemTopMsg_;
  
  public static final int FORCEDGUARANTEE_FIELD_NUMBER = 6;
  
  private boolean forcedGuarantee_;
  
  public static final int BIZSCENE_FIELD_NUMBER = 20;
  
  private volatile Object bizScene_;
  
  public static final int BURIEDPOINTMAP_FIELD_NUMBER = 30;
  
  private MapField<String, String> buriedPointMap_;
  
  private byte memoizedIsInitialized;
  
  private RoomMessage(GeneratedMessageV3.Builder<?> builder) {
    super(builder);
    this.content_ = "";
    this.supprotLandscape_ = false;
    this.roommessagetype_ = 0;
    this.systemTopMsg_ = false;
    this.forcedGuarantee_ = false;
    this.bizScene_ = "";
    this.memoizedIsInitialized = -1;
  }
  
  private RoomMessage() {
    this.content_ = "";
    this.supprotLandscape_ = false;
    this.roommessagetype_ = 0;
    this.systemTopMsg_ = false;
    this.forcedGuarantee_ = false;
    this.bizScene_ = "";
    this.memoizedIsInitialized = -1;
    this.content_ = "";
    this.roommessagetype_ = 0;
    this.bizScene_ = "";
  }
  
  protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
    return new RoomMessage();
  }
  
  public static final Descriptors.Descriptor getDescriptor() {
    return Douyin.internal_static_RoomMessage_descriptor;
  }
  
  protected MapFieldReflectionAccessor internalGetMapFieldReflection(int number) {
    switch (number) {
      case 30:
        return (MapFieldReflectionAccessor)internalGetBuriedPointMap();
    } 
    throw new RuntimeException("Invalid map field number: " + number);
  }
  
  protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
    return Douyin.internal_static_RoomMessage_fieldAccessorTable.ensureFieldAccessorsInitialized(RoomMessage.class, Builder.class);
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
  
  public String getContent() {
    Object ref = this.content_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.content_ = s;
    return s;
  }
  
  public ByteString getContentBytes() {
    Object ref = this.content_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.content_ = b;
      return b;
    } 
    return (ByteString)ref;
  }
  
  public boolean getSupprotLandscape() {
    return this.supprotLandscape_;
  }
  
  public int getRoommessagetypeValue() {
    return this.roommessagetype_;
  }
  
  public RoomMsgTypeEnum getRoommessagetype() {
    RoomMsgTypeEnum result = RoomMsgTypeEnum.forNumber(this.roommessagetype_);
    return (result == null) ? RoomMsgTypeEnum.UNRECOGNIZED : result;
  }
  
  public boolean getSystemTopMsg() {
    return this.systemTopMsg_;
  }
  
  public boolean getForcedGuarantee() {
    return this.forcedGuarantee_;
  }
  
  public String getBizScene() {
    Object ref = this.bizScene_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.bizScene_ = s;
    return s;
  }
  
  public ByteString getBizSceneBytes() {
    Object ref = this.bizScene_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.bizScene_ = b;
      return b;
    } 
    return (ByteString)ref;
  }
  
  private static final class BuriedPointMapDefaultEntryHolder {
    static final MapEntry<String, String> defaultEntry = MapEntry.newDefaultInstance(Douyin.internal_static_RoomMessage_BuriedPointMapEntry_descriptor, WireFormat.FieldType.STRING, "", WireFormat.FieldType.STRING, "");
  }
  
  private MapField<String, String> internalGetBuriedPointMap() {
    if (this.buriedPointMap_ == null)
      return MapField.emptyMapField(BuriedPointMapDefaultEntryHolder.defaultEntry); 
    return this.buriedPointMap_;
  }
  
  public int getBuriedPointMapCount() {
    return internalGetBuriedPointMap().getMap().size();
  }
  
  public boolean containsBuriedPointMap(String key) {
    if (key == null)
      throw new NullPointerException("map key"); 
    return internalGetBuriedPointMap().getMap().containsKey(key);
  }
  
  @Deprecated
  public Map<String, String> getBuriedPointMap() {
    return getBuriedPointMapMap();
  }
  
  public Map<String, String> getBuriedPointMapMap() {
    return internalGetBuriedPointMap().getMap();
  }
  
  public String getBuriedPointMapOrDefault(String key, String defaultValue) {
    if (key == null)
      throw new NullPointerException("map key"); 
    Map<String, String> map = internalGetBuriedPointMap().getMap();
    return map.containsKey(key) ? map.get(key) : defaultValue;
  }
  
  public String getBuriedPointMapOrThrow(String key) {
    if (key == null)
      throw new NullPointerException("map key"); 
    Map<String, String> map = internalGetBuriedPointMap().getMap();
    if (!map.containsKey(key))
      throw new IllegalArgumentException(); 
    return map.get(key);
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
    if (!GeneratedMessageV3.isStringEmpty(this.content_))
      GeneratedMessageV3.writeString(output, 2, this.content_); 
    if (this.supprotLandscape_)
      output.writeBool(3, this.supprotLandscape_); 
    if (this.roommessagetype_ != RoomMsgTypeEnum.DEFAULTROOMMSG.getNumber())
      output.writeEnum(4, this.roommessagetype_); 
    if (this.systemTopMsg_)
      output.writeBool(5, this.systemTopMsg_); 
    if (this.forcedGuarantee_)
      output.writeBool(6, this.forcedGuarantee_); 
    if (!GeneratedMessageV3.isStringEmpty(this.bizScene_))
      GeneratedMessageV3.writeString(output, 20, this.bizScene_); 
    GeneratedMessageV3.serializeStringMapTo(output, 
        
        internalGetBuriedPointMap(), BuriedPointMapDefaultEntryHolder.defaultEntry, 30);
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
    if (!GeneratedMessageV3.isStringEmpty(this.content_))
      size += GeneratedMessageV3.computeStringSize(2, this.content_); 
    if (this.supprotLandscape_)
      size += 
        CodedOutputStream.computeBoolSize(3, this.supprotLandscape_); 
    if (this.roommessagetype_ != RoomMsgTypeEnum.DEFAULTROOMMSG.getNumber())
      size += 
        CodedOutputStream.computeEnumSize(4, this.roommessagetype_); 
    if (this.systemTopMsg_)
      size += 
        CodedOutputStream.computeBoolSize(5, this.systemTopMsg_); 
    if (this.forcedGuarantee_)
      size += 
        CodedOutputStream.computeBoolSize(6, this.forcedGuarantee_); 
    if (!GeneratedMessageV3.isStringEmpty(this.bizScene_))
      size += GeneratedMessageV3.computeStringSize(20, this.bizScene_); 
    for (Map.Entry<String, String> entry : (Iterable<Map.Entry<String, String>>)internalGetBuriedPointMap().getMap().entrySet()) {
      MapEntry<String, String> buriedPointMap__ = BuriedPointMapDefaultEntryHolder.defaultEntry.newBuilderForType().setKey(entry.getKey()).setValue(entry.getValue()).build();
      size += 
        CodedOutputStream.computeMessageSize(30, (MessageLite)buriedPointMap__);
    } 
    size += getUnknownFields().getSerializedSize();
    this.memoizedSize = size;
    return size;
  }
  
  public boolean equals(Object obj) {
    if (obj == this)
      return true; 
    if (!(obj instanceof RoomMessage))
      return super.equals(obj); 
    RoomMessage other = (RoomMessage)obj;
    if (hasCommon() != other.hasCommon())
      return false; 
    if (hasCommon() && 
      
      !getCommon().equals(other.getCommon()))
      return false; 
    if (!getContent().equals(other.getContent()))
      return false; 
    if (getSupprotLandscape() != other
      .getSupprotLandscape())
      return false; 
    if (this.roommessagetype_ != other.roommessagetype_)
      return false; 
    if (getSystemTopMsg() != other
      .getSystemTopMsg())
      return false; 
    if (getForcedGuarantee() != other
      .getForcedGuarantee())
      return false; 
    if (!getBizScene().equals(other.getBizScene()))
      return false; 
    if (!internalGetBuriedPointMap().equals(other
        .internalGetBuriedPointMap()))
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
    hash = 53 * hash + getContent().hashCode();
    hash = 37 * hash + 3;
    hash = 53 * hash + Internal.hashBoolean(
        getSupprotLandscape());
    hash = 37 * hash + 4;
    hash = 53 * hash + this.roommessagetype_;
    hash = 37 * hash + 5;
    hash = 53 * hash + Internal.hashBoolean(
        getSystemTopMsg());
    hash = 37 * hash + 6;
    hash = 53 * hash + Internal.hashBoolean(
        getForcedGuarantee());
    hash = 37 * hash + 20;
    hash = 53 * hash + getBizScene().hashCode();
    if (!internalGetBuriedPointMap().getMap().isEmpty()) {
      hash = 37 * hash + 30;
      hash = 53 * hash + internalGetBuriedPointMap().hashCode();
    } 
    hash = 29 * hash + getUnknownFields().hashCode();
    this.memoizedHashCode = hash;
    return hash;
  }
  
  public static RoomMessage parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
    return (RoomMessage)PARSER.parseFrom(data);
  }
  
  public static RoomMessage parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (RoomMessage)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static RoomMessage parseFrom(ByteString data) throws InvalidProtocolBufferException {
    return (RoomMessage)PARSER.parseFrom(data);
  }
  
  public static RoomMessage parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (RoomMessage)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static RoomMessage parseFrom(byte[] data) throws InvalidProtocolBufferException {
    return (RoomMessage)PARSER.parseFrom(data);
  }
  
  public static RoomMessage parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (RoomMessage)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static RoomMessage parseFrom(InputStream input) throws IOException {
    return 
      (RoomMessage)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static RoomMessage parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (RoomMessage)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static RoomMessage parseDelimitedFrom(InputStream input) throws IOException {
    return 
      (RoomMessage)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
  }
  
  public static RoomMessage parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (RoomMessage)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static RoomMessage parseFrom(CodedInputStream input) throws IOException {
    return 
      (RoomMessage)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static RoomMessage parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (RoomMessage)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public Builder newBuilderForType() {
    return newBuilder();
  }
  
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  
  public static Builder newBuilder(RoomMessage prototype) {
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
  
  public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements RoomMessageOrBuilder {
    private int bitField0_;
    
    private Common common_;
    
    private SingleFieldBuilderV3<Common, Common.Builder, CommonOrBuilder> commonBuilder_;
    
    private Object content_;
    
    private boolean supprotLandscape_;
    
    private int roommessagetype_;
    
    private boolean systemTopMsg_;
    
    private boolean forcedGuarantee_;
    
    private Object bizScene_;
    
    private MapField<String, String> buriedPointMap_;
    
    public static final Descriptors.Descriptor getDescriptor() {
      return Douyin.internal_static_RoomMessage_descriptor;
    }
    
    protected MapFieldReflectionAccessor internalGetMapFieldReflection(int number) {
      switch (number) {
        case 30:
          return (MapFieldReflectionAccessor)internalGetBuriedPointMap();
      } 
      throw new RuntimeException("Invalid map field number: " + number);
    }
    
    protected MapFieldReflectionAccessor internalGetMutableMapFieldReflection(int number) {
      switch (number) {
        case 30:
          return (MapFieldReflectionAccessor)internalGetMutableBuriedPointMap();
      } 
      throw new RuntimeException("Invalid map field number: " + number);
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return Douyin.internal_static_RoomMessage_fieldAccessorTable
        .ensureFieldAccessorsInitialized(RoomMessage.class, Builder.class);
    }
    
    private Builder() {
      this.content_ = "";
      this.roommessagetype_ = 0;
      this.bizScene_ = "";
      maybeForceBuilderInitialization();
    }
    
    private Builder(GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      this.content_ = "";
      this.roommessagetype_ = 0;
      this.bizScene_ = "";
      maybeForceBuilderInitialization();
    }
    
    private void maybeForceBuilderInitialization() {
      if (RoomMessage.alwaysUseFieldBuilders)
        getCommonFieldBuilder(); 
    }
    
    public Builder clear() {
      super.clear();
      this.bitField0_ = 0;
      this.common_ = null;
      if (this.commonBuilder_ != null) {
        this.commonBuilder_.dispose();
        this.commonBuilder_ = null;
      } 
      this.content_ = "";
      this.supprotLandscape_ = false;
      this.roommessagetype_ = 0;
      this.systemTopMsg_ = false;
      this.forcedGuarantee_ = false;
      this.bizScene_ = "";
      internalGetMutableBuriedPointMap().clear();
      return this;
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
      return Douyin.internal_static_RoomMessage_descriptor;
    }
    
    public RoomMessage getDefaultInstanceForType() {
      return RoomMessage.getDefaultInstance();
    }
    
    public RoomMessage build() {
      RoomMessage result = buildPartial();
      if (!result.isInitialized())
        throw newUninitializedMessageException(result); 
      return result;
    }
    
    public RoomMessage buildPartial() {
      RoomMessage result = new RoomMessage(this);
      if (this.bitField0_ != 0)
        buildPartial0(result); 
      onBuilt();
      return result;
    }
    
    private void buildPartial0(RoomMessage result) {
      int from_bitField0_ = this.bitField0_;
      int to_bitField0_ = 0;
      if ((from_bitField0_ & 0x1) != 0) {
        result.common_ = (this.commonBuilder_ == null) ? this.common_ : (Common)this.commonBuilder_.build();
        to_bitField0_ |= 0x1;
      } 
      if ((from_bitField0_ & 0x2) != 0)
        result.content_ = this.content_; 
      if ((from_bitField0_ & 0x4) != 0)
        result.supprotLandscape_ = this.supprotLandscape_; 
      if ((from_bitField0_ & 0x8) != 0)
        result.roommessagetype_ = this.roommessagetype_; 
      if ((from_bitField0_ & 0x10) != 0)
        result.systemTopMsg_ = this.systemTopMsg_; 
      if ((from_bitField0_ & 0x20) != 0)
        result.forcedGuarantee_ = this.forcedGuarantee_; 
      if ((from_bitField0_ & 0x40) != 0)
        result.bizScene_ = this.bizScene_; 
      if ((from_bitField0_ & 0x80) != 0) {
        result.buriedPointMap_ = internalGetBuriedPointMap();
        result.buriedPointMap_.makeImmutable();
      } 
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
      if (other instanceof RoomMessage)
        return mergeFrom((RoomMessage)other); 
      super.mergeFrom(other);
      return this;
    }
    
    public Builder mergeFrom(RoomMessage other) {
      if (other == RoomMessage.getDefaultInstance())
        return this; 
      if (other.hasCommon())
        mergeCommon(other.getCommon()); 
      if (!other.getContent().isEmpty()) {
        this.content_ = other.content_;
        this.bitField0_ |= 0x2;
        onChanged();
      } 
      if (other.getSupprotLandscape())
        setSupprotLandscape(other.getSupprotLandscape()); 
      if (other.roommessagetype_ != 0)
        setRoommessagetypeValue(other.getRoommessagetypeValue()); 
      if (other.getSystemTopMsg())
        setSystemTopMsg(other.getSystemTopMsg()); 
      if (other.getForcedGuarantee())
        setForcedGuarantee(other.getForcedGuarantee()); 
      if (!other.getBizScene().isEmpty()) {
        this.bizScene_ = other.bizScene_;
        this.bitField0_ |= 0x40;
        onChanged();
      } 
      internalGetMutableBuriedPointMap().mergeFrom(other.internalGetBuriedPointMap());
      this.bitField0_ |= 0x80;
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
          MapEntry<String, String> buriedPointMap__;
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              continue;
            case 10:
              input.readMessage((MessageLite.Builder)getCommonFieldBuilder().getBuilder(), extensionRegistry);
              this.bitField0_ |= 0x1;
              continue;
            case 18:
              this.content_ = input.readStringRequireUtf8();
              this.bitField0_ |= 0x2;
              continue;
            case 24:
              this.supprotLandscape_ = input.readBool();
              this.bitField0_ |= 0x4;
              continue;
            case 32:
              this.roommessagetype_ = input.readEnum();
              this.bitField0_ |= 0x8;
              continue;
            case 40:
              this.systemTopMsg_ = input.readBool();
              this.bitField0_ |= 0x10;
              continue;
            case 48:
              this.forcedGuarantee_ = input.readBool();
              this.bitField0_ |= 0x20;
              continue;
            case 162:
              this.bizScene_ = input.readStringRequireUtf8();
              this.bitField0_ |= 0x40;
              continue;
            case 242:
              buriedPointMap__ = (MapEntry<String, String>)input.readMessage(RoomMessage.BuriedPointMapDefaultEntryHolder.defaultEntry.getParserForType(), extensionRegistry);
              internalGetMutableBuriedPointMap().getMutableMap().put((String)buriedPointMap__.getKey(), (String)buriedPointMap__.getValue());
              this.bitField0_ |= 0x80;
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
    
    public String getContent() {
      Object ref = this.content_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.content_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getContentBytes() {
      Object ref = this.content_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.content_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setContent(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.content_ = value;
      this.bitField0_ |= 0x2;
      onChanged();
      return this;
    }
    
    public Builder clearContent() {
      this.content_ = RoomMessage.getDefaultInstance().getContent();
      this.bitField0_ &= 0xFFFFFFFD;
      onChanged();
      return this;
    }
    
    public Builder setContentBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      RoomMessage.checkByteStringIsUtf8(value);
      this.content_ = value;
      this.bitField0_ |= 0x2;
      onChanged();
      return this;
    }
    
    public boolean getSupprotLandscape() {
      return this.supprotLandscape_;
    }
    
    public Builder setSupprotLandscape(boolean value) {
      this.supprotLandscape_ = value;
      this.bitField0_ |= 0x4;
      onChanged();
      return this;
    }
    
    public Builder clearSupprotLandscape() {
      this.bitField0_ &= 0xFFFFFFFB;
      this.supprotLandscape_ = false;
      onChanged();
      return this;
    }
    
    public int getRoommessagetypeValue() {
      return this.roommessagetype_;
    }
    
    public Builder setRoommessagetypeValue(int value) {
      this.roommessagetype_ = value;
      this.bitField0_ |= 0x8;
      onChanged();
      return this;
    }
    
    public RoomMsgTypeEnum getRoommessagetype() {
      RoomMsgTypeEnum result = RoomMsgTypeEnum.forNumber(this.roommessagetype_);
      return (result == null) ? RoomMsgTypeEnum.UNRECOGNIZED : result;
    }
    
    public Builder setRoommessagetype(RoomMsgTypeEnum value) {
      if (value == null)
        throw new NullPointerException(); 
      this.bitField0_ |= 0x8;
      this.roommessagetype_ = value.getNumber();
      onChanged();
      return this;
    }
    
    public Builder clearRoommessagetype() {
      this.bitField0_ &= 0xFFFFFFF7;
      this.roommessagetype_ = 0;
      onChanged();
      return this;
    }
    
    public boolean getSystemTopMsg() {
      return this.systemTopMsg_;
    }
    
    public Builder setSystemTopMsg(boolean value) {
      this.systemTopMsg_ = value;
      this.bitField0_ |= 0x10;
      onChanged();
      return this;
    }
    
    public Builder clearSystemTopMsg() {
      this.bitField0_ &= 0xFFFFFFEF;
      this.systemTopMsg_ = false;
      onChanged();
      return this;
    }
    
    public boolean getForcedGuarantee() {
      return this.forcedGuarantee_;
    }
    
    public Builder setForcedGuarantee(boolean value) {
      this.forcedGuarantee_ = value;
      this.bitField0_ |= 0x20;
      onChanged();
      return this;
    }
    
    public Builder clearForcedGuarantee() {
      this.bitField0_ &= 0xFFFFFFDF;
      this.forcedGuarantee_ = false;
      onChanged();
      return this;
    }
    
    public String getBizScene() {
      Object ref = this.bizScene_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.bizScene_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getBizSceneBytes() {
      Object ref = this.bizScene_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.bizScene_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setBizScene(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.bizScene_ = value;
      this.bitField0_ |= 0x40;
      onChanged();
      return this;
    }
    
    public Builder clearBizScene() {
      this.bizScene_ = RoomMessage.getDefaultInstance().getBizScene();
      this.bitField0_ &= 0xFFFFFFBF;
      onChanged();
      return this;
    }
    
    public Builder setBizSceneBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      RoomMessage.checkByteStringIsUtf8(value);
      this.bizScene_ = value;
      this.bitField0_ |= 0x40;
      onChanged();
      return this;
    }
    
    private MapField<String, String> internalGetBuriedPointMap() {
      if (this.buriedPointMap_ == null)
        return MapField.emptyMapField(RoomMessage.BuriedPointMapDefaultEntryHolder.defaultEntry); 
      return this.buriedPointMap_;
    }
    
    private MapField<String, String> internalGetMutableBuriedPointMap() {
      if (this.buriedPointMap_ == null)
        this.buriedPointMap_ = MapField.newMapField(RoomMessage.BuriedPointMapDefaultEntryHolder.defaultEntry); 
      if (!this.buriedPointMap_.isMutable())
        this.buriedPointMap_ = this.buriedPointMap_.copy(); 
      this.bitField0_ |= 0x80;
      onChanged();
      return this.buriedPointMap_;
    }
    
    public int getBuriedPointMapCount() {
      return internalGetBuriedPointMap().getMap().size();
    }
    
    public boolean containsBuriedPointMap(String key) {
      if (key == null)
        throw new NullPointerException("map key"); 
      return internalGetBuriedPointMap().getMap().containsKey(key);
    }
    
    @Deprecated
    public Map<String, String> getBuriedPointMap() {
      return getBuriedPointMapMap();
    }
    
    public Map<String, String> getBuriedPointMapMap() {
      return internalGetBuriedPointMap().getMap();
    }
    
    public String getBuriedPointMapOrDefault(String key, String defaultValue) {
      if (key == null)
        throw new NullPointerException("map key"); 
      Map<String, String> map = internalGetBuriedPointMap().getMap();
      return map.containsKey(key) ? map.get(key) : defaultValue;
    }
    
    public String getBuriedPointMapOrThrow(String key) {
      if (key == null)
        throw new NullPointerException("map key"); 
      Map<String, String> map = internalGetBuriedPointMap().getMap();
      if (!map.containsKey(key))
        throw new IllegalArgumentException(); 
      return map.get(key);
    }
    
    public Builder clearBuriedPointMap() {
      this.bitField0_ &= 0xFFFFFF7F;
      internalGetMutableBuriedPointMap().getMutableMap()
        .clear();
      return this;
    }
    
    public Builder removeBuriedPointMap(String key) {
      if (key == null)
        throw new NullPointerException("map key"); 
      internalGetMutableBuriedPointMap().getMutableMap()
        .remove(key);
      return this;
    }
    
    @Deprecated
    public Map<String, String> getMutableBuriedPointMap() {
      this.bitField0_ |= 0x80;
      return internalGetMutableBuriedPointMap().getMutableMap();
    }
    
    public Builder putBuriedPointMap(String key, String value) {
      if (key == null)
        throw new NullPointerException("map key"); 
      if (value == null)
        throw new NullPointerException("map value"); 
      internalGetMutableBuriedPointMap().getMutableMap()
        .put(key, value);
      this.bitField0_ |= 0x80;
      return this;
    }
    
    public Builder putAllBuriedPointMap(Map<String, String> values) {
      internalGetMutableBuriedPointMap().getMutableMap()
        .putAll(values);
      this.bitField0_ |= 0x80;
      return this;
    }
    
    public final Builder setUnknownFields(UnknownFieldSet unknownFields) {
      return (Builder)super.setUnknownFields(unknownFields);
    }
    
    public final Builder mergeUnknownFields(UnknownFieldSet unknownFields) {
      return (Builder)super.mergeUnknownFields(unknownFields);
    }
  }
  
  private static final RoomMessage DEFAULT_INSTANCE = new RoomMessage();
  
  public static RoomMessage getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }
  
  private static final Parser<RoomMessage> PARSER = (Parser<RoomMessage>)new AbstractParser<RoomMessage>() {
      public RoomMessage parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        RoomMessage.Builder builder = RoomMessage.newBuilder();
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
  
  public static Parser<RoomMessage> parser() {
    return PARSER;
  }
  
  public Parser<RoomMessage> getParserForType() {
    return PARSER;
  }
  
  public RoomMessage getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }
}
