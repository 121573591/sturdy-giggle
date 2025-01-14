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
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import com.google.protobuf.WireFormat;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Map;

public final class UserBadge extends GeneratedMessageV3 implements UserBadgeOrBuilder {
  private static final long serialVersionUID = 0L;
  
  public static final int ICONS_FIELD_NUMBER = 1;
  
  private MapField<Integer, Image> icons_;
  
  public static final int TITLE_FIELD_NUMBER = 2;
  
  private volatile Object title_;
  
  private byte memoizedIsInitialized;
  
  private UserBadge(GeneratedMessageV3.Builder<?> builder) {
    super(builder);
    this.title_ = "";
    this.memoizedIsInitialized = -1;
  }
  
  private UserBadge() {
    this.title_ = "";
    this.memoizedIsInitialized = -1;
    this.title_ = "";
  }
  
  protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
    return new UserBadge();
  }
  
  public static final Descriptors.Descriptor getDescriptor() {
    return Douyin.internal_static_UserBadge_descriptor;
  }
  
  protected MapFieldReflectionAccessor internalGetMapFieldReflection(int number) {
    switch (number) {
      case 1:
        return (MapFieldReflectionAccessor)internalGetIcons();
    } 
    throw new RuntimeException("Invalid map field number: " + number);
  }
  
  protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
    return Douyin.internal_static_UserBadge_fieldAccessorTable.ensureFieldAccessorsInitialized(UserBadge.class, Builder.class);
  }
  
  private static final class IconsDefaultEntryHolder {
    static final MapEntry<Integer, Image> defaultEntry = MapEntry.newDefaultInstance(Douyin.internal_static_UserBadge_IconsEntry_descriptor, WireFormat.FieldType.INT32, Integer.valueOf(0), WireFormat.FieldType.MESSAGE, Image.getDefaultInstance());
  }
  
  private MapField<Integer, Image> internalGetIcons() {
    if (this.icons_ == null)
      return MapField.emptyMapField(IconsDefaultEntryHolder.defaultEntry); 
    return this.icons_;
  }
  
  public int getIconsCount() {
    return internalGetIcons().getMap().size();
  }
  
  public boolean containsIcons(int key) {
    return internalGetIcons().getMap().containsKey(Integer.valueOf(key));
  }
  
  @Deprecated
  public Map<Integer, Image> getIcons() {
    return getIconsMap();
  }
  
  public Map<Integer, Image> getIconsMap() {
    return internalGetIcons().getMap();
  }
  
  public Image getIconsOrDefault(int key, Image defaultValue) {
    Map<Integer, Image> map = internalGetIcons().getMap();
    return map.containsKey(Integer.valueOf(key)) ? map.get(Integer.valueOf(key)) : defaultValue;
  }
  
  public Image getIconsOrThrow(int key) {
    Map<Integer, Image> map = internalGetIcons().getMap();
    if (!map.containsKey(Integer.valueOf(key)))
      throw new IllegalArgumentException(); 
    return map.get(Integer.valueOf(key));
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
    GeneratedMessageV3.serializeIntegerMapTo(output, 
        
        internalGetIcons(), IconsDefaultEntryHolder.defaultEntry, 1);
    if (!GeneratedMessageV3.isStringEmpty(this.title_))
      GeneratedMessageV3.writeString(output, 2, this.title_); 
    getUnknownFields().writeTo(output);
  }
  
  public int getSerializedSize() {
    int size = this.memoizedSize;
    if (size != -1)
      return size; 
    size = 0;
    for (Map.Entry<Integer, Image> entry : (Iterable<Map.Entry<Integer, Image>>)internalGetIcons().getMap().entrySet()) {
      MapEntry<Integer, Image> icons__ = IconsDefaultEntryHolder.defaultEntry.newBuilderForType().setKey(entry.getKey()).setValue(entry.getValue()).build();
      size += 
        CodedOutputStream.computeMessageSize(1, (MessageLite)icons__);
    } 
    if (!GeneratedMessageV3.isStringEmpty(this.title_))
      size += GeneratedMessageV3.computeStringSize(2, this.title_); 
    size += getUnknownFields().getSerializedSize();
    this.memoizedSize = size;
    return size;
  }
  
  public boolean equals(Object obj) {
    if (obj == this)
      return true; 
    if (!(obj instanceof UserBadge))
      return super.equals(obj); 
    UserBadge other = (UserBadge)obj;
    if (!internalGetIcons().equals(other
        .internalGetIcons()))
      return false; 
    if (!getTitle().equals(other.getTitle()))
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
    if (!internalGetIcons().getMap().isEmpty()) {
      hash = 37 * hash + 1;
      hash = 53 * hash + internalGetIcons().hashCode();
    } 
    hash = 37 * hash + 2;
    hash = 53 * hash + getTitle().hashCode();
    hash = 29 * hash + getUnknownFields().hashCode();
    this.memoizedHashCode = hash;
    return hash;
  }
  
  public static UserBadge parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
    return (UserBadge)PARSER.parseFrom(data);
  }
  
  public static UserBadge parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (UserBadge)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static UserBadge parseFrom(ByteString data) throws InvalidProtocolBufferException {
    return (UserBadge)PARSER.parseFrom(data);
  }
  
  public static UserBadge parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (UserBadge)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static UserBadge parseFrom(byte[] data) throws InvalidProtocolBufferException {
    return (UserBadge)PARSER.parseFrom(data);
  }
  
  public static UserBadge parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (UserBadge)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static UserBadge parseFrom(InputStream input) throws IOException {
    return 
      (UserBadge)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static UserBadge parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (UserBadge)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static UserBadge parseDelimitedFrom(InputStream input) throws IOException {
    return 
      (UserBadge)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
  }
  
  public static UserBadge parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (UserBadge)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static UserBadge parseFrom(CodedInputStream input) throws IOException {
    return 
      (UserBadge)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static UserBadge parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (UserBadge)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public Builder newBuilderForType() {
    return newBuilder();
  }
  
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  
  public static Builder newBuilder(UserBadge prototype) {
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
  
  public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements UserBadgeOrBuilder {
    private int bitField0_;
    
    public static final Descriptors.Descriptor getDescriptor() {
      return Douyin.internal_static_UserBadge_descriptor;
    }
    
    protected MapFieldReflectionAccessor internalGetMapFieldReflection(int number) {
      switch (number) {
        case 1:
          return (MapFieldReflectionAccessor)internalGetIcons();
      } 
      throw new RuntimeException("Invalid map field number: " + number);
    }
    
    protected MapFieldReflectionAccessor internalGetMutableMapFieldReflection(int number) {
      switch (number) {
        case 1:
          return (MapFieldReflectionAccessor)internalGetMutableIcons();
      } 
      throw new RuntimeException("Invalid map field number: " + number);
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return Douyin.internal_static_UserBadge_fieldAccessorTable
        .ensureFieldAccessorsInitialized(UserBadge.class, Builder.class);
    }
    
    private Builder() {
      this.title_ = "";
    }
    
    private Builder(GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      this.title_ = "";
    }
    
    public Builder clear() {
      super.clear();
      this.bitField0_ = 0;
      internalGetMutableIcons().clear();
      this.title_ = "";
      return this;
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
      return Douyin.internal_static_UserBadge_descriptor;
    }
    
    public UserBadge getDefaultInstanceForType() {
      return UserBadge.getDefaultInstance();
    }
    
    public UserBadge build() {
      UserBadge result = buildPartial();
      if (!result.isInitialized())
        throw newUninitializedMessageException(result); 
      return result;
    }
    
    public UserBadge buildPartial() {
      UserBadge result = new UserBadge(this);
      if (this.bitField0_ != 0)
        buildPartial0(result); 
      onBuilt();
      return result;
    }
    
    private void buildPartial0(UserBadge result) {
      int from_bitField0_ = this.bitField0_;
      if ((from_bitField0_ & 0x1) != 0)
        result.icons_ = internalGetIcons().build(UserBadge.IconsDefaultEntryHolder.defaultEntry); 
      if ((from_bitField0_ & 0x2) != 0)
        result.title_ = this.title_; 
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
      if (other instanceof UserBadge)
        return mergeFrom((UserBadge)other); 
      super.mergeFrom(other);
      return this;
    }
    
    public Builder mergeFrom(UserBadge other) {
      if (other == UserBadge.getDefaultInstance())
        return this; 
      internalGetMutableIcons().mergeFrom(other.internalGetIcons());
      this.bitField0_ |= 0x1;
      if (!other.getTitle().isEmpty()) {
        this.title_ = other.title_;
        this.bitField0_ |= 0x2;
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
          MapEntry<Integer, Image> icons__;
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              continue;
            case 10:
              icons__ = (MapEntry<Integer, Image>)input.readMessage(UserBadge.IconsDefaultEntryHolder.defaultEntry.getParserForType(), extensionRegistry);
              internalGetMutableIcons().ensureBuilderMap().put((Integer)icons__.getKey(), (ImageOrBuilder)icons__.getValue());
              this.bitField0_ |= 0x1;
              continue;
            case 18:
              this.title_ = input.readStringRequireUtf8();
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
    
    private static final class IconsConverter implements MapFieldBuilder.Converter<Integer, ImageOrBuilder, Image> {
      private IconsConverter() {}
      
      public Image build(ImageOrBuilder val) {
        if (val instanceof Image)
          return (Image)val; 
        return ((Image.Builder)val).build();
      }
      
      public MapEntry<Integer, Image> defaultEntry() {
        return UserBadge.IconsDefaultEntryHolder.defaultEntry;
      }
    }
    
    private static final IconsConverter iconsConverter = new IconsConverter();
    
    private MapFieldBuilder<Integer, ImageOrBuilder, Image, Image.Builder> icons_;
    
    private Object title_;
    
    private MapFieldBuilder<Integer, ImageOrBuilder, Image, Image.Builder> internalGetIcons() {
      if (this.icons_ == null)
        return new MapFieldBuilder(iconsConverter); 
      return this.icons_;
    }
    
    private MapFieldBuilder<Integer, ImageOrBuilder, Image, Image.Builder> internalGetMutableIcons() {
      if (this.icons_ == null)
        this.icons_ = new MapFieldBuilder(iconsConverter); 
      this.bitField0_ |= 0x1;
      onChanged();
      return this.icons_;
    }
    
    public int getIconsCount() {
      return internalGetIcons().ensureBuilderMap().size();
    }
    
    public boolean containsIcons(int key) {
      return internalGetIcons().ensureBuilderMap().containsKey(Integer.valueOf(key));
    }
    
    @Deprecated
    public Map<Integer, Image> getIcons() {
      return getIconsMap();
    }
    
    public Map<Integer, Image> getIconsMap() {
      return internalGetIcons().getImmutableMap();
    }
    
    public Image getIconsOrDefault(int key, Image defaultValue) {
      Map<Integer, ImageOrBuilder> map = internalGetMutableIcons().ensureBuilderMap();
      return map.containsKey(Integer.valueOf(key)) ? iconsConverter.build(map.get(Integer.valueOf(key))) : defaultValue;
    }
    
    public Image getIconsOrThrow(int key) {
      Map<Integer, ImageOrBuilder> map = internalGetMutableIcons().ensureBuilderMap();
      if (!map.containsKey(Integer.valueOf(key)))
        throw new IllegalArgumentException(); 
      return iconsConverter.build(map.get(Integer.valueOf(key)));
    }
    
    public Builder clearIcons() {
      this.bitField0_ &= 0xFFFFFFFE;
      internalGetMutableIcons().clear();
      return this;
    }
    
    public Builder removeIcons(int key) {
      internalGetMutableIcons().ensureBuilderMap().remove(Integer.valueOf(key));
      return this;
    }
    
    @Deprecated
    public Map<Integer, Image> getMutableIcons() {
      this.bitField0_ |= 0x1;
      return internalGetMutableIcons().ensureMessageMap();
    }
    
    public Builder putIcons(int key, Image value) {
      if (value == null)
        throw new NullPointerException("map value"); 
      internalGetMutableIcons().ensureBuilderMap().put(Integer.valueOf(key), value);
      this.bitField0_ |= 0x1;
      return this;
    }
    
    public Builder putAllIcons(Map<Integer, Image> values) {
      for (Map.Entry<Integer, Image> e : values.entrySet()) {
        if (e.getKey() == null || e.getValue() == null)
          throw new NullPointerException(); 
      } 
      internalGetMutableIcons().ensureBuilderMap().putAll(values);
      this.bitField0_ |= 0x1;
      return this;
    }
    
    public Image.Builder putIconsBuilderIfAbsent(int key) {
      Map<Integer, ImageOrBuilder> builderMap = internalGetMutableIcons().ensureBuilderMap();
      ImageOrBuilder entry = builderMap.get(Integer.valueOf(key));
      if (entry == null) {
        entry = Image.newBuilder();
        builderMap.put(Integer.valueOf(key), entry);
      } 
      if (entry instanceof Image) {
        entry = ((Image)entry).toBuilder();
        builderMap.put(Integer.valueOf(key), entry);
      } 
      return (Image.Builder)entry;
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
      this.bitField0_ |= 0x2;
      onChanged();
      return this;
    }
    
    public Builder clearTitle() {
      this.title_ = UserBadge.getDefaultInstance().getTitle();
      this.bitField0_ &= 0xFFFFFFFD;
      onChanged();
      return this;
    }
    
    public Builder setTitleBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      UserBadge.checkByteStringIsUtf8(value);
      this.title_ = value;
      this.bitField0_ |= 0x2;
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
  
  private static final UserBadge DEFAULT_INSTANCE = new UserBadge();
  
  public static UserBadge getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }
  
  private static final Parser<UserBadge> PARSER = (Parser<UserBadge>)new AbstractParser<UserBadge>() {
      public UserBadge parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        UserBadge.Builder builder = UserBadge.newBuilder();
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
  
  public static Parser<UserBadge> parser() {
    return PARSER;
  }
  
  public Parser<UserBadge> getParserForType() {
    return PARSER;
  }
  
  public UserBadge getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }
}
