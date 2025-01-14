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

public final class ImageContent extends GeneratedMessageV3 implements ImageContentOrBuilder {
  private static final long serialVersionUID = 0L;
  
  public static final int NAME_FIELD_NUMBER = 1;
  
  private volatile Object name_;
  
  public static final int FONTCOLOR_FIELD_NUMBER = 2;
  
  private volatile Object fontColor_;
  
  public static final int LEVEL_FIELD_NUMBER = 3;
  
  private long level_;
  
  public static final int ALTERNATIVETEXT_FIELD_NUMBER = 4;
  
  private volatile Object alternativeText_;
  
  private byte memoizedIsInitialized;
  
  private ImageContent(GeneratedMessageV3.Builder<?> builder) {
    super(builder);
    this.name_ = "";
    this.fontColor_ = "";
    this.level_ = 0L;
    this.alternativeText_ = "";
    this.memoizedIsInitialized = -1;
  }
  
  private ImageContent() {
    this.name_ = "";
    this.fontColor_ = "";
    this.level_ = 0L;
    this.alternativeText_ = "";
    this.memoizedIsInitialized = -1;
    this.name_ = "";
    this.fontColor_ = "";
    this.alternativeText_ = "";
  }
  
  protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
    return new ImageContent();
  }
  
  public static final Descriptors.Descriptor getDescriptor() {
    return Douyin.internal_static_ImageContent_descriptor;
  }
  
  protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
    return Douyin.internal_static_ImageContent_fieldAccessorTable.ensureFieldAccessorsInitialized(ImageContent.class, Builder.class);
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
  
  public String getFontColor() {
    Object ref = this.fontColor_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.fontColor_ = s;
    return s;
  }
  
  public ByteString getFontColorBytes() {
    Object ref = this.fontColor_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.fontColor_ = b;
      return b;
    } 
    return (ByteString)ref;
  }
  
  public long getLevel() {
    return this.level_;
  }
  
  public String getAlternativeText() {
    Object ref = this.alternativeText_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.alternativeText_ = s;
    return s;
  }
  
  public ByteString getAlternativeTextBytes() {
    Object ref = this.alternativeText_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.alternativeText_ = b;
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
    if (!GeneratedMessageV3.isStringEmpty(this.name_))
      GeneratedMessageV3.writeString(output, 1, this.name_); 
    if (!GeneratedMessageV3.isStringEmpty(this.fontColor_))
      GeneratedMessageV3.writeString(output, 2, this.fontColor_); 
    if (this.level_ != 0L)
      output.writeUInt64(3, this.level_); 
    if (!GeneratedMessageV3.isStringEmpty(this.alternativeText_))
      GeneratedMessageV3.writeString(output, 4, this.alternativeText_); 
    getUnknownFields().writeTo(output);
  }
  
  public int getSerializedSize() {
    int size = this.memoizedSize;
    if (size != -1)
      return size; 
    size = 0;
    if (!GeneratedMessageV3.isStringEmpty(this.name_))
      size += GeneratedMessageV3.computeStringSize(1, this.name_); 
    if (!GeneratedMessageV3.isStringEmpty(this.fontColor_))
      size += GeneratedMessageV3.computeStringSize(2, this.fontColor_); 
    if (this.level_ != 0L)
      size += 
        CodedOutputStream.computeUInt64Size(3, this.level_); 
    if (!GeneratedMessageV3.isStringEmpty(this.alternativeText_))
      size += GeneratedMessageV3.computeStringSize(4, this.alternativeText_); 
    size += getUnknownFields().getSerializedSize();
    this.memoizedSize = size;
    return size;
  }
  
  public boolean equals(Object obj) {
    if (obj == this)
      return true; 
    if (!(obj instanceof ImageContent))
      return super.equals(obj); 
    ImageContent other = (ImageContent)obj;
    if (!getName().equals(other.getName()))
      return false; 
    if (!getFontColor().equals(other.getFontColor()))
      return false; 
    if (getLevel() != other
      .getLevel())
      return false; 
    if (!getAlternativeText().equals(other.getAlternativeText()))
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
    hash = 53 * hash + getName().hashCode();
    hash = 37 * hash + 2;
    hash = 53 * hash + getFontColor().hashCode();
    hash = 37 * hash + 3;
    hash = 53 * hash + Internal.hashLong(
        getLevel());
    hash = 37 * hash + 4;
    hash = 53 * hash + getAlternativeText().hashCode();
    hash = 29 * hash + getUnknownFields().hashCode();
    this.memoizedHashCode = hash;
    return hash;
  }
  
  public static ImageContent parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
    return (ImageContent)PARSER.parseFrom(data);
  }
  
  public static ImageContent parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (ImageContent)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static ImageContent parseFrom(ByteString data) throws InvalidProtocolBufferException {
    return (ImageContent)PARSER.parseFrom(data);
  }
  
  public static ImageContent parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (ImageContent)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static ImageContent parseFrom(byte[] data) throws InvalidProtocolBufferException {
    return (ImageContent)PARSER.parseFrom(data);
  }
  
  public static ImageContent parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (ImageContent)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static ImageContent parseFrom(InputStream input) throws IOException {
    return 
      (ImageContent)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static ImageContent parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (ImageContent)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static ImageContent parseDelimitedFrom(InputStream input) throws IOException {
    return 
      (ImageContent)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
  }
  
  public static ImageContent parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (ImageContent)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static ImageContent parseFrom(CodedInputStream input) throws IOException {
    return 
      (ImageContent)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static ImageContent parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (ImageContent)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public Builder newBuilderForType() {
    return newBuilder();
  }
  
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  
  public static Builder newBuilder(ImageContent prototype) {
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
  
  public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ImageContentOrBuilder {
    private int bitField0_;
    
    private Object name_;
    
    private Object fontColor_;
    
    private long level_;
    
    private Object alternativeText_;
    
    public static final Descriptors.Descriptor getDescriptor() {
      return Douyin.internal_static_ImageContent_descriptor;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return Douyin.internal_static_ImageContent_fieldAccessorTable
        .ensureFieldAccessorsInitialized(ImageContent.class, Builder.class);
    }
    
    private Builder() {
      this.name_ = "";
      this.fontColor_ = "";
      this.alternativeText_ = "";
    }
    
    private Builder(GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      this.name_ = "";
      this.fontColor_ = "";
      this.alternativeText_ = "";
    }
    
    public Builder clear() {
      super.clear();
      this.bitField0_ = 0;
      this.name_ = "";
      this.fontColor_ = "";
      this.level_ = 0L;
      this.alternativeText_ = "";
      return this;
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
      return Douyin.internal_static_ImageContent_descriptor;
    }
    
    public ImageContent getDefaultInstanceForType() {
      return ImageContent.getDefaultInstance();
    }
    
    public ImageContent build() {
      ImageContent result = buildPartial();
      if (!result.isInitialized())
        throw newUninitializedMessageException(result); 
      return result;
    }
    
    public ImageContent buildPartial() {
      ImageContent result = new ImageContent(this);
      if (this.bitField0_ != 0)
        buildPartial0(result); 
      onBuilt();
      return result;
    }
    
    private void buildPartial0(ImageContent result) {
      int from_bitField0_ = this.bitField0_;
      if ((from_bitField0_ & 0x1) != 0)
        result.name_ = this.name_; 
      if ((from_bitField0_ & 0x2) != 0)
        result.fontColor_ = this.fontColor_; 
      if ((from_bitField0_ & 0x4) != 0)
        result.level_ = this.level_; 
      if ((from_bitField0_ & 0x8) != 0)
        result.alternativeText_ = this.alternativeText_; 
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
      if (other instanceof ImageContent)
        return mergeFrom((ImageContent)other); 
      super.mergeFrom(other);
      return this;
    }
    
    public Builder mergeFrom(ImageContent other) {
      if (other == ImageContent.getDefaultInstance())
        return this; 
      if (!other.getName().isEmpty()) {
        this.name_ = other.name_;
        this.bitField0_ |= 0x1;
        onChanged();
      } 
      if (!other.getFontColor().isEmpty()) {
        this.fontColor_ = other.fontColor_;
        this.bitField0_ |= 0x2;
        onChanged();
      } 
      if (other.getLevel() != 0L)
        setLevel(other.getLevel()); 
      if (!other.getAlternativeText().isEmpty()) {
        this.alternativeText_ = other.alternativeText_;
        this.bitField0_ |= 0x8;
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
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              continue;
            case 10:
              this.name_ = input.readStringRequireUtf8();
              this.bitField0_ |= 0x1;
              continue;
            case 18:
              this.fontColor_ = input.readStringRequireUtf8();
              this.bitField0_ |= 0x2;
              continue;
            case 24:
              this.level_ = input.readUInt64();
              this.bitField0_ |= 0x4;
              continue;
            case 34:
              this.alternativeText_ = input.readStringRequireUtf8();
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
      this.bitField0_ |= 0x1;
      onChanged();
      return this;
    }
    
    public Builder clearName() {
      this.name_ = ImageContent.getDefaultInstance().getName();
      this.bitField0_ &= 0xFFFFFFFE;
      onChanged();
      return this;
    }
    
    public Builder setNameBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      ImageContent.checkByteStringIsUtf8(value);
      this.name_ = value;
      this.bitField0_ |= 0x1;
      onChanged();
      return this;
    }
    
    public String getFontColor() {
      Object ref = this.fontColor_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.fontColor_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getFontColorBytes() {
      Object ref = this.fontColor_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.fontColor_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setFontColor(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.fontColor_ = value;
      this.bitField0_ |= 0x2;
      onChanged();
      return this;
    }
    
    public Builder clearFontColor() {
      this.fontColor_ = ImageContent.getDefaultInstance().getFontColor();
      this.bitField0_ &= 0xFFFFFFFD;
      onChanged();
      return this;
    }
    
    public Builder setFontColorBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      ImageContent.checkByteStringIsUtf8(value);
      this.fontColor_ = value;
      this.bitField0_ |= 0x2;
      onChanged();
      return this;
    }
    
    public long getLevel() {
      return this.level_;
    }
    
    public Builder setLevel(long value) {
      this.level_ = value;
      this.bitField0_ |= 0x4;
      onChanged();
      return this;
    }
    
    public Builder clearLevel() {
      this.bitField0_ &= 0xFFFFFFFB;
      this.level_ = 0L;
      onChanged();
      return this;
    }
    
    public String getAlternativeText() {
      Object ref = this.alternativeText_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.alternativeText_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getAlternativeTextBytes() {
      Object ref = this.alternativeText_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.alternativeText_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setAlternativeText(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.alternativeText_ = value;
      this.bitField0_ |= 0x8;
      onChanged();
      return this;
    }
    
    public Builder clearAlternativeText() {
      this.alternativeText_ = ImageContent.getDefaultInstance().getAlternativeText();
      this.bitField0_ &= 0xFFFFFFF7;
      onChanged();
      return this;
    }
    
    public Builder setAlternativeTextBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      ImageContent.checkByteStringIsUtf8(value);
      this.alternativeText_ = value;
      this.bitField0_ |= 0x8;
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
  
  private static final ImageContent DEFAULT_INSTANCE = new ImageContent();
  
  public static ImageContent getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }
  
  private static final Parser<ImageContent> PARSER = (Parser<ImageContent>)new AbstractParser<ImageContent>() {
      public ImageContent parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        ImageContent.Builder builder = ImageContent.newBuilder();
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
  
  public static Parser<ImageContent> parser() {
    return PARSER;
  }
  
  public Parser<ImageContent> getParserForType() {
    return PARSER;
  }
  
  public ImageContent getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }
}
