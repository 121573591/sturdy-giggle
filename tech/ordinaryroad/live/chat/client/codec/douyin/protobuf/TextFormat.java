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

public final class TextFormat extends GeneratedMessageV3 implements TextFormatOrBuilder {
  private static final long serialVersionUID = 0L;
  
  public static final int COLOR_FIELD_NUMBER = 1;
  
  private volatile Object color_;
  
  public static final int BOLD_FIELD_NUMBER = 2;
  
  private boolean bold_;
  
  public static final int ITALIC_FIELD_NUMBER = 3;
  
  private boolean italic_;
  
  public static final int WEIGHT_FIELD_NUMBER = 4;
  
  private int weight_;
  
  public static final int ITALIC_ANGLE_FIELD_NUMBER = 5;
  
  private int italicAngle_;
  
  public static final int FONT_SIZE_FIELD_NUMBER = 6;
  
  private int fontSize_;
  
  public static final int USE_HEIGH_LIGHT_COLOR_FIELD_NUMBER = 7;
  
  private boolean useHeighLightColor_;
  
  public static final int USE_REMOTE_CLOR_FIELD_NUMBER = 8;
  
  private boolean useRemoteClor_;
  
  private byte memoizedIsInitialized;
  
  private TextFormat(GeneratedMessageV3.Builder<?> builder) {
    super(builder);
    this.color_ = "";
    this.bold_ = false;
    this.italic_ = false;
    this.weight_ = 0;
    this.italicAngle_ = 0;
    this.fontSize_ = 0;
    this.useHeighLightColor_ = false;
    this.useRemoteClor_ = false;
    this.memoizedIsInitialized = -1;
  }
  
  private TextFormat() {
    this.color_ = "";
    this.bold_ = false;
    this.italic_ = false;
    this.weight_ = 0;
    this.italicAngle_ = 0;
    this.fontSize_ = 0;
    this.useHeighLightColor_ = false;
    this.useRemoteClor_ = false;
    this.memoizedIsInitialized = -1;
    this.color_ = "";
  }
  
  protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
    return new TextFormat();
  }
  
  public static final Descriptors.Descriptor getDescriptor() {
    return Douyin.internal_static_TextFormat_descriptor;
  }
  
  protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
    return Douyin.internal_static_TextFormat_fieldAccessorTable.ensureFieldAccessorsInitialized(TextFormat.class, Builder.class);
  }
  
  public String getColor() {
    Object ref = this.color_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.color_ = s;
    return s;
  }
  
  public ByteString getColorBytes() {
    Object ref = this.color_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.color_ = b;
      return b;
    } 
    return (ByteString)ref;
  }
  
  public boolean getBold() {
    return this.bold_;
  }
  
  public boolean getItalic() {
    return this.italic_;
  }
  
  public int getWeight() {
    return this.weight_;
  }
  
  public int getItalicAngle() {
    return this.italicAngle_;
  }
  
  public int getFontSize() {
    return this.fontSize_;
  }
  
  public boolean getUseHeighLightColor() {
    return this.useHeighLightColor_;
  }
  
  public boolean getUseRemoteClor() {
    return this.useRemoteClor_;
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
    if (!GeneratedMessageV3.isStringEmpty(this.color_))
      GeneratedMessageV3.writeString(output, 1, this.color_); 
    if (this.bold_)
      output.writeBool(2, this.bold_); 
    if (this.italic_)
      output.writeBool(3, this.italic_); 
    if (this.weight_ != 0)
      output.writeUInt32(4, this.weight_); 
    if (this.italicAngle_ != 0)
      output.writeUInt32(5, this.italicAngle_); 
    if (this.fontSize_ != 0)
      output.writeUInt32(6, this.fontSize_); 
    if (this.useHeighLightColor_)
      output.writeBool(7, this.useHeighLightColor_); 
    if (this.useRemoteClor_)
      output.writeBool(8, this.useRemoteClor_); 
    getUnknownFields().writeTo(output);
  }
  
  public int getSerializedSize() {
    int size = this.memoizedSize;
    if (size != -1)
      return size; 
    size = 0;
    if (!GeneratedMessageV3.isStringEmpty(this.color_))
      size += GeneratedMessageV3.computeStringSize(1, this.color_); 
    if (this.bold_)
      size += 
        CodedOutputStream.computeBoolSize(2, this.bold_); 
    if (this.italic_)
      size += 
        CodedOutputStream.computeBoolSize(3, this.italic_); 
    if (this.weight_ != 0)
      size += 
        CodedOutputStream.computeUInt32Size(4, this.weight_); 
    if (this.italicAngle_ != 0)
      size += 
        CodedOutputStream.computeUInt32Size(5, this.italicAngle_); 
    if (this.fontSize_ != 0)
      size += 
        CodedOutputStream.computeUInt32Size(6, this.fontSize_); 
    if (this.useHeighLightColor_)
      size += 
        CodedOutputStream.computeBoolSize(7, this.useHeighLightColor_); 
    if (this.useRemoteClor_)
      size += 
        CodedOutputStream.computeBoolSize(8, this.useRemoteClor_); 
    size += getUnknownFields().getSerializedSize();
    this.memoizedSize = size;
    return size;
  }
  
  public boolean equals(Object obj) {
    if (obj == this)
      return true; 
    if (!(obj instanceof TextFormat))
      return super.equals(obj); 
    TextFormat other = (TextFormat)obj;
    if (!getColor().equals(other.getColor()))
      return false; 
    if (getBold() != other
      .getBold())
      return false; 
    if (getItalic() != other
      .getItalic())
      return false; 
    if (getWeight() != other
      .getWeight())
      return false; 
    if (getItalicAngle() != other
      .getItalicAngle())
      return false; 
    if (getFontSize() != other
      .getFontSize())
      return false; 
    if (getUseHeighLightColor() != other
      .getUseHeighLightColor())
      return false; 
    if (getUseRemoteClor() != other
      .getUseRemoteClor())
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
    hash = 53 * hash + getColor().hashCode();
    hash = 37 * hash + 2;
    hash = 53 * hash + Internal.hashBoolean(
        getBold());
    hash = 37 * hash + 3;
    hash = 53 * hash + Internal.hashBoolean(
        getItalic());
    hash = 37 * hash + 4;
    hash = 53 * hash + getWeight();
    hash = 37 * hash + 5;
    hash = 53 * hash + getItalicAngle();
    hash = 37 * hash + 6;
    hash = 53 * hash + getFontSize();
    hash = 37 * hash + 7;
    hash = 53 * hash + Internal.hashBoolean(
        getUseHeighLightColor());
    hash = 37 * hash + 8;
    hash = 53 * hash + Internal.hashBoolean(
        getUseRemoteClor());
    hash = 29 * hash + getUnknownFields().hashCode();
    this.memoizedHashCode = hash;
    return hash;
  }
  
  public static TextFormat parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
    return (TextFormat)PARSER.parseFrom(data);
  }
  
  public static TextFormat parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (TextFormat)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static TextFormat parseFrom(ByteString data) throws InvalidProtocolBufferException {
    return (TextFormat)PARSER.parseFrom(data);
  }
  
  public static TextFormat parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (TextFormat)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static TextFormat parseFrom(byte[] data) throws InvalidProtocolBufferException {
    return (TextFormat)PARSER.parseFrom(data);
  }
  
  public static TextFormat parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (TextFormat)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static TextFormat parseFrom(InputStream input) throws IOException {
    return 
      (TextFormat)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static TextFormat parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (TextFormat)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static TextFormat parseDelimitedFrom(InputStream input) throws IOException {
    return 
      (TextFormat)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
  }
  
  public static TextFormat parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (TextFormat)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static TextFormat parseFrom(CodedInputStream input) throws IOException {
    return 
      (TextFormat)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static TextFormat parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (TextFormat)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public Builder newBuilderForType() {
    return newBuilder();
  }
  
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  
  public static Builder newBuilder(TextFormat prototype) {
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
  
  public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements TextFormatOrBuilder {
    private int bitField0_;
    
    private Object color_;
    
    private boolean bold_;
    
    private boolean italic_;
    
    private int weight_;
    
    private int italicAngle_;
    
    private int fontSize_;
    
    private boolean useHeighLightColor_;
    
    private boolean useRemoteClor_;
    
    public static final Descriptors.Descriptor getDescriptor() {
      return Douyin.internal_static_TextFormat_descriptor;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return Douyin.internal_static_TextFormat_fieldAccessorTable
        .ensureFieldAccessorsInitialized(TextFormat.class, Builder.class);
    }
    
    private Builder() {
      this.color_ = "";
    }
    
    private Builder(GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      this.color_ = "";
    }
    
    public Builder clear() {
      super.clear();
      this.bitField0_ = 0;
      this.color_ = "";
      this.bold_ = false;
      this.italic_ = false;
      this.weight_ = 0;
      this.italicAngle_ = 0;
      this.fontSize_ = 0;
      this.useHeighLightColor_ = false;
      this.useRemoteClor_ = false;
      return this;
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
      return Douyin.internal_static_TextFormat_descriptor;
    }
    
    public TextFormat getDefaultInstanceForType() {
      return TextFormat.getDefaultInstance();
    }
    
    public TextFormat build() {
      TextFormat result = buildPartial();
      if (!result.isInitialized())
        throw newUninitializedMessageException(result); 
      return result;
    }
    
    public TextFormat buildPartial() {
      TextFormat result = new TextFormat(this);
      if (this.bitField0_ != 0)
        buildPartial0(result); 
      onBuilt();
      return result;
    }
    
    private void buildPartial0(TextFormat result) {
      int from_bitField0_ = this.bitField0_;
      if ((from_bitField0_ & 0x1) != 0)
        result.color_ = this.color_; 
      if ((from_bitField0_ & 0x2) != 0)
        result.bold_ = this.bold_; 
      if ((from_bitField0_ & 0x4) != 0)
        result.italic_ = this.italic_; 
      if ((from_bitField0_ & 0x8) != 0)
        result.weight_ = this.weight_; 
      if ((from_bitField0_ & 0x10) != 0)
        result.italicAngle_ = this.italicAngle_; 
      if ((from_bitField0_ & 0x20) != 0)
        result.fontSize_ = this.fontSize_; 
      if ((from_bitField0_ & 0x40) != 0)
        result.useHeighLightColor_ = this.useHeighLightColor_; 
      if ((from_bitField0_ & 0x80) != 0)
        result.useRemoteClor_ = this.useRemoteClor_; 
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
      if (other instanceof TextFormat)
        return mergeFrom((TextFormat)other); 
      super.mergeFrom(other);
      return this;
    }
    
    public Builder mergeFrom(TextFormat other) {
      if (other == TextFormat.getDefaultInstance())
        return this; 
      if (!other.getColor().isEmpty()) {
        this.color_ = other.color_;
        this.bitField0_ |= 0x1;
        onChanged();
      } 
      if (other.getBold())
        setBold(other.getBold()); 
      if (other.getItalic())
        setItalic(other.getItalic()); 
      if (other.getWeight() != 0)
        setWeight(other.getWeight()); 
      if (other.getItalicAngle() != 0)
        setItalicAngle(other.getItalicAngle()); 
      if (other.getFontSize() != 0)
        setFontSize(other.getFontSize()); 
      if (other.getUseHeighLightColor())
        setUseHeighLightColor(other.getUseHeighLightColor()); 
      if (other.getUseRemoteClor())
        setUseRemoteClor(other.getUseRemoteClor()); 
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
              this.color_ = input.readStringRequireUtf8();
              this.bitField0_ |= 0x1;
              continue;
            case 16:
              this.bold_ = input.readBool();
              this.bitField0_ |= 0x2;
              continue;
            case 24:
              this.italic_ = input.readBool();
              this.bitField0_ |= 0x4;
              continue;
            case 32:
              this.weight_ = input.readUInt32();
              this.bitField0_ |= 0x8;
              continue;
            case 40:
              this.italicAngle_ = input.readUInt32();
              this.bitField0_ |= 0x10;
              continue;
            case 48:
              this.fontSize_ = input.readUInt32();
              this.bitField0_ |= 0x20;
              continue;
            case 56:
              this.useHeighLightColor_ = input.readBool();
              this.bitField0_ |= 0x40;
              continue;
            case 64:
              this.useRemoteClor_ = input.readBool();
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
    
    public String getColor() {
      Object ref = this.color_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.color_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getColorBytes() {
      Object ref = this.color_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.color_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setColor(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.color_ = value;
      this.bitField0_ |= 0x1;
      onChanged();
      return this;
    }
    
    public Builder clearColor() {
      this.color_ = TextFormat.getDefaultInstance().getColor();
      this.bitField0_ &= 0xFFFFFFFE;
      onChanged();
      return this;
    }
    
    public Builder setColorBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      TextFormat.checkByteStringIsUtf8(value);
      this.color_ = value;
      this.bitField0_ |= 0x1;
      onChanged();
      return this;
    }
    
    public boolean getBold() {
      return this.bold_;
    }
    
    public Builder setBold(boolean value) {
      this.bold_ = value;
      this.bitField0_ |= 0x2;
      onChanged();
      return this;
    }
    
    public Builder clearBold() {
      this.bitField0_ &= 0xFFFFFFFD;
      this.bold_ = false;
      onChanged();
      return this;
    }
    
    public boolean getItalic() {
      return this.italic_;
    }
    
    public Builder setItalic(boolean value) {
      this.italic_ = value;
      this.bitField0_ |= 0x4;
      onChanged();
      return this;
    }
    
    public Builder clearItalic() {
      this.bitField0_ &= 0xFFFFFFFB;
      this.italic_ = false;
      onChanged();
      return this;
    }
    
    public int getWeight() {
      return this.weight_;
    }
    
    public Builder setWeight(int value) {
      this.weight_ = value;
      this.bitField0_ |= 0x8;
      onChanged();
      return this;
    }
    
    public Builder clearWeight() {
      this.bitField0_ &= 0xFFFFFFF7;
      this.weight_ = 0;
      onChanged();
      return this;
    }
    
    public int getItalicAngle() {
      return this.italicAngle_;
    }
    
    public Builder setItalicAngle(int value) {
      this.italicAngle_ = value;
      this.bitField0_ |= 0x10;
      onChanged();
      return this;
    }
    
    public Builder clearItalicAngle() {
      this.bitField0_ &= 0xFFFFFFEF;
      this.italicAngle_ = 0;
      onChanged();
      return this;
    }
    
    public int getFontSize() {
      return this.fontSize_;
    }
    
    public Builder setFontSize(int value) {
      this.fontSize_ = value;
      this.bitField0_ |= 0x20;
      onChanged();
      return this;
    }
    
    public Builder clearFontSize() {
      this.bitField0_ &= 0xFFFFFFDF;
      this.fontSize_ = 0;
      onChanged();
      return this;
    }
    
    public boolean getUseHeighLightColor() {
      return this.useHeighLightColor_;
    }
    
    public Builder setUseHeighLightColor(boolean value) {
      this.useHeighLightColor_ = value;
      this.bitField0_ |= 0x40;
      onChanged();
      return this;
    }
    
    public Builder clearUseHeighLightColor() {
      this.bitField0_ &= 0xFFFFFFBF;
      this.useHeighLightColor_ = false;
      onChanged();
      return this;
    }
    
    public boolean getUseRemoteClor() {
      return this.useRemoteClor_;
    }
    
    public Builder setUseRemoteClor(boolean value) {
      this.useRemoteClor_ = value;
      this.bitField0_ |= 0x80;
      onChanged();
      return this;
    }
    
    public Builder clearUseRemoteClor() {
      this.bitField0_ &= 0xFFFFFF7F;
      this.useRemoteClor_ = false;
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
  
  private static final TextFormat DEFAULT_INSTANCE = new TextFormat();
  
  public static TextFormat getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }
  
  private static final Parser<TextFormat> PARSER = (Parser<TextFormat>)new AbstractParser<TextFormat>() {
      public TextFormat parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        TextFormat.Builder builder = TextFormat.newBuilder();
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
  
  public static Parser<TextFormat> parser() {
    return PARSER;
  }
  
  public Parser<TextFormat> getParserForType() {
    return PARSER;
  }
  
  public TextFormat getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }
}
