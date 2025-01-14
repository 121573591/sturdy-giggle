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
import com.google.protobuf.Message;
import com.google.protobuf.MessageLite;
import com.google.protobuf.Parser;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class TextEffectDetail extends GeneratedMessageV3 implements TextEffectDetailOrBuilder {
  private static final long serialVersionUID = 0L;
  
  private int bitField0_;
  
  public static final int TEXT_FIELD_NUMBER = 1;
  
  private Text text_;
  
  public static final int TEXTFONTSIZE_FIELD_NUMBER = 2;
  
  private int textFontSize_;
  
  public static final int BACKGROUND_FIELD_NUMBER = 3;
  
  private Image background_;
  
  public static final int START_FIELD_NUMBER = 4;
  
  private int start_;
  
  public static final int DURATION_FIELD_NUMBER = 5;
  
  private int duration_;
  
  public static final int X_FIELD_NUMBER = 6;
  
  private int x_;
  
  public static final int Y_FIELD_NUMBER = 7;
  
  private int y_;
  
  public static final int WIDTH_FIELD_NUMBER = 8;
  
  private int width_;
  
  public static final int HEIGHT_FIELD_NUMBER = 9;
  
  private int height_;
  
  public static final int SHADOWDX_FIELD_NUMBER = 10;
  
  private int shadowDx_;
  
  public static final int SHADOWDY_FIELD_NUMBER = 11;
  
  private int shadowDy_;
  
  public static final int SHADOWRADIUS_FIELD_NUMBER = 12;
  
  private int shadowRadius_;
  
  public static final int SHADOWCOLOR_FIELD_NUMBER = 13;
  
  private volatile Object shadowColor_;
  
  public static final int STROKECOLOR_FIELD_NUMBER = 14;
  
  private volatile Object strokeColor_;
  
  public static final int STROKEWIDTH_FIELD_NUMBER = 15;
  
  private int strokeWidth_;
  
  private byte memoizedIsInitialized;
  
  private TextEffectDetail(GeneratedMessageV3.Builder<?> builder) {
    super(builder);
    this.textFontSize_ = 0;
    this.start_ = 0;
    this.duration_ = 0;
    this.x_ = 0;
    this.y_ = 0;
    this.width_ = 0;
    this.height_ = 0;
    this.shadowDx_ = 0;
    this.shadowDy_ = 0;
    this.shadowRadius_ = 0;
    this.shadowColor_ = "";
    this.strokeColor_ = "";
    this.strokeWidth_ = 0;
    this.memoizedIsInitialized = -1;
  }
  
  private TextEffectDetail() {
    this.textFontSize_ = 0;
    this.start_ = 0;
    this.duration_ = 0;
    this.x_ = 0;
    this.y_ = 0;
    this.width_ = 0;
    this.height_ = 0;
    this.shadowDx_ = 0;
    this.shadowDy_ = 0;
    this.shadowRadius_ = 0;
    this.shadowColor_ = "";
    this.strokeColor_ = "";
    this.strokeWidth_ = 0;
    this.memoizedIsInitialized = -1;
    this.shadowColor_ = "";
    this.strokeColor_ = "";
  }
  
  protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
    return new TextEffectDetail();
  }
  
  public static final Descriptors.Descriptor getDescriptor() {
    return Douyin.internal_static_TextEffectDetail_descriptor;
  }
  
  protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
    return Douyin.internal_static_TextEffectDetail_fieldAccessorTable.ensureFieldAccessorsInitialized(TextEffectDetail.class, Builder.class);
  }
  
  public boolean hasText() {
    return ((this.bitField0_ & 0x1) != 0);
  }
  
  public Text getText() {
    return (this.text_ == null) ? Text.getDefaultInstance() : this.text_;
  }
  
  public TextOrBuilder getTextOrBuilder() {
    return (this.text_ == null) ? Text.getDefaultInstance() : this.text_;
  }
  
  public int getTextFontSize() {
    return this.textFontSize_;
  }
  
  public boolean hasBackground() {
    return ((this.bitField0_ & 0x2) != 0);
  }
  
  public Image getBackground() {
    return (this.background_ == null) ? Image.getDefaultInstance() : this.background_;
  }
  
  public ImageOrBuilder getBackgroundOrBuilder() {
    return (this.background_ == null) ? Image.getDefaultInstance() : this.background_;
  }
  
  public int getStart() {
    return this.start_;
  }
  
  public int getDuration() {
    return this.duration_;
  }
  
  public int getX() {
    return this.x_;
  }
  
  public int getY() {
    return this.y_;
  }
  
  public int getWidth() {
    return this.width_;
  }
  
  public int getHeight() {
    return this.height_;
  }
  
  public int getShadowDx() {
    return this.shadowDx_;
  }
  
  public int getShadowDy() {
    return this.shadowDy_;
  }
  
  public int getShadowRadius() {
    return this.shadowRadius_;
  }
  
  public String getShadowColor() {
    Object ref = this.shadowColor_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.shadowColor_ = s;
    return s;
  }
  
  public ByteString getShadowColorBytes() {
    Object ref = this.shadowColor_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.shadowColor_ = b;
      return b;
    } 
    return (ByteString)ref;
  }
  
  public String getStrokeColor() {
    Object ref = this.strokeColor_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.strokeColor_ = s;
    return s;
  }
  
  public ByteString getStrokeColorBytes() {
    Object ref = this.strokeColor_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.strokeColor_ = b;
      return b;
    } 
    return (ByteString)ref;
  }
  
  public int getStrokeWidth() {
    return this.strokeWidth_;
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
      output.writeMessage(1, (MessageLite)getText()); 
    if (this.textFontSize_ != 0)
      output.writeUInt32(2, this.textFontSize_); 
    if ((this.bitField0_ & 0x2) != 0)
      output.writeMessage(3, (MessageLite)getBackground()); 
    if (this.start_ != 0)
      output.writeUInt32(4, this.start_); 
    if (this.duration_ != 0)
      output.writeUInt32(5, this.duration_); 
    if (this.x_ != 0)
      output.writeUInt32(6, this.x_); 
    if (this.y_ != 0)
      output.writeUInt32(7, this.y_); 
    if (this.width_ != 0)
      output.writeUInt32(8, this.width_); 
    if (this.height_ != 0)
      output.writeUInt32(9, this.height_); 
    if (this.shadowDx_ != 0)
      output.writeUInt32(10, this.shadowDx_); 
    if (this.shadowDy_ != 0)
      output.writeUInt32(11, this.shadowDy_); 
    if (this.shadowRadius_ != 0)
      output.writeUInt32(12, this.shadowRadius_); 
    if (!GeneratedMessageV3.isStringEmpty(this.shadowColor_))
      GeneratedMessageV3.writeString(output, 13, this.shadowColor_); 
    if (!GeneratedMessageV3.isStringEmpty(this.strokeColor_))
      GeneratedMessageV3.writeString(output, 14, this.strokeColor_); 
    if (this.strokeWidth_ != 0)
      output.writeUInt32(15, this.strokeWidth_); 
    getUnknownFields().writeTo(output);
  }
  
  public int getSerializedSize() {
    int size = this.memoizedSize;
    if (size != -1)
      return size; 
    size = 0;
    if ((this.bitField0_ & 0x1) != 0)
      size += 
        CodedOutputStream.computeMessageSize(1, (MessageLite)getText()); 
    if (this.textFontSize_ != 0)
      size += 
        CodedOutputStream.computeUInt32Size(2, this.textFontSize_); 
    if ((this.bitField0_ & 0x2) != 0)
      size += 
        CodedOutputStream.computeMessageSize(3, (MessageLite)getBackground()); 
    if (this.start_ != 0)
      size += 
        CodedOutputStream.computeUInt32Size(4, this.start_); 
    if (this.duration_ != 0)
      size += 
        CodedOutputStream.computeUInt32Size(5, this.duration_); 
    if (this.x_ != 0)
      size += 
        CodedOutputStream.computeUInt32Size(6, this.x_); 
    if (this.y_ != 0)
      size += 
        CodedOutputStream.computeUInt32Size(7, this.y_); 
    if (this.width_ != 0)
      size += 
        CodedOutputStream.computeUInt32Size(8, this.width_); 
    if (this.height_ != 0)
      size += 
        CodedOutputStream.computeUInt32Size(9, this.height_); 
    if (this.shadowDx_ != 0)
      size += 
        CodedOutputStream.computeUInt32Size(10, this.shadowDx_); 
    if (this.shadowDy_ != 0)
      size += 
        CodedOutputStream.computeUInt32Size(11, this.shadowDy_); 
    if (this.shadowRadius_ != 0)
      size += 
        CodedOutputStream.computeUInt32Size(12, this.shadowRadius_); 
    if (!GeneratedMessageV3.isStringEmpty(this.shadowColor_))
      size += GeneratedMessageV3.computeStringSize(13, this.shadowColor_); 
    if (!GeneratedMessageV3.isStringEmpty(this.strokeColor_))
      size += GeneratedMessageV3.computeStringSize(14, this.strokeColor_); 
    if (this.strokeWidth_ != 0)
      size += 
        CodedOutputStream.computeUInt32Size(15, this.strokeWidth_); 
    size += getUnknownFields().getSerializedSize();
    this.memoizedSize = size;
    return size;
  }
  
  public boolean equals(Object obj) {
    if (obj == this)
      return true; 
    if (!(obj instanceof TextEffectDetail))
      return super.equals(obj); 
    TextEffectDetail other = (TextEffectDetail)obj;
    if (hasText() != other.hasText())
      return false; 
    if (hasText() && 
      
      !getText().equals(other.getText()))
      return false; 
    if (getTextFontSize() != other
      .getTextFontSize())
      return false; 
    if (hasBackground() != other.hasBackground())
      return false; 
    if (hasBackground() && 
      
      !getBackground().equals(other.getBackground()))
      return false; 
    if (getStart() != other
      .getStart())
      return false; 
    if (getDuration() != other
      .getDuration())
      return false; 
    if (getX() != other
      .getX())
      return false; 
    if (getY() != other
      .getY())
      return false; 
    if (getWidth() != other
      .getWidth())
      return false; 
    if (getHeight() != other
      .getHeight())
      return false; 
    if (getShadowDx() != other
      .getShadowDx())
      return false; 
    if (getShadowDy() != other
      .getShadowDy())
      return false; 
    if (getShadowRadius() != other
      .getShadowRadius())
      return false; 
    if (!getShadowColor().equals(other.getShadowColor()))
      return false; 
    if (!getStrokeColor().equals(other.getStrokeColor()))
      return false; 
    if (getStrokeWidth() != other
      .getStrokeWidth())
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
    if (hasText()) {
      hash = 37 * hash + 1;
      hash = 53 * hash + getText().hashCode();
    } 
    hash = 37 * hash + 2;
    hash = 53 * hash + getTextFontSize();
    if (hasBackground()) {
      hash = 37 * hash + 3;
      hash = 53 * hash + getBackground().hashCode();
    } 
    hash = 37 * hash + 4;
    hash = 53 * hash + getStart();
    hash = 37 * hash + 5;
    hash = 53 * hash + getDuration();
    hash = 37 * hash + 6;
    hash = 53 * hash + getX();
    hash = 37 * hash + 7;
    hash = 53 * hash + getY();
    hash = 37 * hash + 8;
    hash = 53 * hash + getWidth();
    hash = 37 * hash + 9;
    hash = 53 * hash + getHeight();
    hash = 37 * hash + 10;
    hash = 53 * hash + getShadowDx();
    hash = 37 * hash + 11;
    hash = 53 * hash + getShadowDy();
    hash = 37 * hash + 12;
    hash = 53 * hash + getShadowRadius();
    hash = 37 * hash + 13;
    hash = 53 * hash + getShadowColor().hashCode();
    hash = 37 * hash + 14;
    hash = 53 * hash + getStrokeColor().hashCode();
    hash = 37 * hash + 15;
    hash = 53 * hash + getStrokeWidth();
    hash = 29 * hash + getUnknownFields().hashCode();
    this.memoizedHashCode = hash;
    return hash;
  }
  
  public static TextEffectDetail parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
    return (TextEffectDetail)PARSER.parseFrom(data);
  }
  
  public static TextEffectDetail parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (TextEffectDetail)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static TextEffectDetail parseFrom(ByteString data) throws InvalidProtocolBufferException {
    return (TextEffectDetail)PARSER.parseFrom(data);
  }
  
  public static TextEffectDetail parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (TextEffectDetail)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static TextEffectDetail parseFrom(byte[] data) throws InvalidProtocolBufferException {
    return (TextEffectDetail)PARSER.parseFrom(data);
  }
  
  public static TextEffectDetail parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (TextEffectDetail)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static TextEffectDetail parseFrom(InputStream input) throws IOException {
    return 
      (TextEffectDetail)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static TextEffectDetail parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (TextEffectDetail)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static TextEffectDetail parseDelimitedFrom(InputStream input) throws IOException {
    return 
      (TextEffectDetail)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
  }
  
  public static TextEffectDetail parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (TextEffectDetail)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static TextEffectDetail parseFrom(CodedInputStream input) throws IOException {
    return 
      (TextEffectDetail)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static TextEffectDetail parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (TextEffectDetail)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public Builder newBuilderForType() {
    return newBuilder();
  }
  
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  
  public static Builder newBuilder(TextEffectDetail prototype) {
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
  
  public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements TextEffectDetailOrBuilder {
    private int bitField0_;
    
    private Text text_;
    
    private SingleFieldBuilderV3<Text, Text.Builder, TextOrBuilder> textBuilder_;
    
    private int textFontSize_;
    
    private Image background_;
    
    private SingleFieldBuilderV3<Image, Image.Builder, ImageOrBuilder> backgroundBuilder_;
    
    private int start_;
    
    private int duration_;
    
    private int x_;
    
    private int y_;
    
    private int width_;
    
    private int height_;
    
    private int shadowDx_;
    
    private int shadowDy_;
    
    private int shadowRadius_;
    
    private Object shadowColor_;
    
    private Object strokeColor_;
    
    private int strokeWidth_;
    
    public static final Descriptors.Descriptor getDescriptor() {
      return Douyin.internal_static_TextEffectDetail_descriptor;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return Douyin.internal_static_TextEffectDetail_fieldAccessorTable
        .ensureFieldAccessorsInitialized(TextEffectDetail.class, Builder.class);
    }
    
    private Builder() {
      this.shadowColor_ = "";
      this.strokeColor_ = "";
      maybeForceBuilderInitialization();
    }
    
    private Builder(GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      this.shadowColor_ = "";
      this.strokeColor_ = "";
      maybeForceBuilderInitialization();
    }
    
    private void maybeForceBuilderInitialization() {
      if (TextEffectDetail.alwaysUseFieldBuilders) {
        getTextFieldBuilder();
        getBackgroundFieldBuilder();
      } 
    }
    
    public Builder clear() {
      super.clear();
      this.bitField0_ = 0;
      this.text_ = null;
      if (this.textBuilder_ != null) {
        this.textBuilder_.dispose();
        this.textBuilder_ = null;
      } 
      this.textFontSize_ = 0;
      this.background_ = null;
      if (this.backgroundBuilder_ != null) {
        this.backgroundBuilder_.dispose();
        this.backgroundBuilder_ = null;
      } 
      this.start_ = 0;
      this.duration_ = 0;
      this.x_ = 0;
      this.y_ = 0;
      this.width_ = 0;
      this.height_ = 0;
      this.shadowDx_ = 0;
      this.shadowDy_ = 0;
      this.shadowRadius_ = 0;
      this.shadowColor_ = "";
      this.strokeColor_ = "";
      this.strokeWidth_ = 0;
      return this;
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
      return Douyin.internal_static_TextEffectDetail_descriptor;
    }
    
    public TextEffectDetail getDefaultInstanceForType() {
      return TextEffectDetail.getDefaultInstance();
    }
    
    public TextEffectDetail build() {
      TextEffectDetail result = buildPartial();
      if (!result.isInitialized())
        throw newUninitializedMessageException(result); 
      return result;
    }
    
    public TextEffectDetail buildPartial() {
      TextEffectDetail result = new TextEffectDetail(this);
      if (this.bitField0_ != 0)
        buildPartial0(result); 
      onBuilt();
      return result;
    }
    
    private void buildPartial0(TextEffectDetail result) {
      int from_bitField0_ = this.bitField0_;
      int to_bitField0_ = 0;
      if ((from_bitField0_ & 0x1) != 0) {
        result.text_ = (this.textBuilder_ == null) ? this.text_ : (Text)this.textBuilder_.build();
        to_bitField0_ |= 0x1;
      } 
      if ((from_bitField0_ & 0x2) != 0)
        result.textFontSize_ = this.textFontSize_; 
      if ((from_bitField0_ & 0x4) != 0) {
        result.background_ = (this.backgroundBuilder_ == null) ? this.background_ : (Image)this.backgroundBuilder_.build();
        to_bitField0_ |= 0x2;
      } 
      if ((from_bitField0_ & 0x8) != 0)
        result.start_ = this.start_; 
      if ((from_bitField0_ & 0x10) != 0)
        result.duration_ = this.duration_; 
      if ((from_bitField0_ & 0x20) != 0)
        result.x_ = this.x_; 
      if ((from_bitField0_ & 0x40) != 0)
        result.y_ = this.y_; 
      if ((from_bitField0_ & 0x80) != 0)
        result.width_ = this.width_; 
      if ((from_bitField0_ & 0x100) != 0)
        result.height_ = this.height_; 
      if ((from_bitField0_ & 0x200) != 0)
        result.shadowDx_ = this.shadowDx_; 
      if ((from_bitField0_ & 0x400) != 0)
        result.shadowDy_ = this.shadowDy_; 
      if ((from_bitField0_ & 0x800) != 0)
        result.shadowRadius_ = this.shadowRadius_; 
      if ((from_bitField0_ & 0x1000) != 0)
        result.shadowColor_ = this.shadowColor_; 
      if ((from_bitField0_ & 0x2000) != 0)
        result.strokeColor_ = this.strokeColor_; 
      if ((from_bitField0_ & 0x4000) != 0)
        result.strokeWidth_ = this.strokeWidth_; 
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
      if (other instanceof TextEffectDetail)
        return mergeFrom((TextEffectDetail)other); 
      super.mergeFrom(other);
      return this;
    }
    
    public Builder mergeFrom(TextEffectDetail other) {
      if (other == TextEffectDetail.getDefaultInstance())
        return this; 
      if (other.hasText())
        mergeText(other.getText()); 
      if (other.getTextFontSize() != 0)
        setTextFontSize(other.getTextFontSize()); 
      if (other.hasBackground())
        mergeBackground(other.getBackground()); 
      if (other.getStart() != 0)
        setStart(other.getStart()); 
      if (other.getDuration() != 0)
        setDuration(other.getDuration()); 
      if (other.getX() != 0)
        setX(other.getX()); 
      if (other.getY() != 0)
        setY(other.getY()); 
      if (other.getWidth() != 0)
        setWidth(other.getWidth()); 
      if (other.getHeight() != 0)
        setHeight(other.getHeight()); 
      if (other.getShadowDx() != 0)
        setShadowDx(other.getShadowDx()); 
      if (other.getShadowDy() != 0)
        setShadowDy(other.getShadowDy()); 
      if (other.getShadowRadius() != 0)
        setShadowRadius(other.getShadowRadius()); 
      if (!other.getShadowColor().isEmpty()) {
        this.shadowColor_ = other.shadowColor_;
        this.bitField0_ |= 0x1000;
        onChanged();
      } 
      if (!other.getStrokeColor().isEmpty()) {
        this.strokeColor_ = other.strokeColor_;
        this.bitField0_ |= 0x2000;
        onChanged();
      } 
      if (other.getStrokeWidth() != 0)
        setStrokeWidth(other.getStrokeWidth()); 
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
              input.readMessage((MessageLite.Builder)getTextFieldBuilder().getBuilder(), extensionRegistry);
              this.bitField0_ |= 0x1;
              continue;
            case 16:
              this.textFontSize_ = input.readUInt32();
              this.bitField0_ |= 0x2;
              continue;
            case 26:
              input.readMessage((MessageLite.Builder)getBackgroundFieldBuilder().getBuilder(), extensionRegistry);
              this.bitField0_ |= 0x4;
              continue;
            case 32:
              this.start_ = input.readUInt32();
              this.bitField0_ |= 0x8;
              continue;
            case 40:
              this.duration_ = input.readUInt32();
              this.bitField0_ |= 0x10;
              continue;
            case 48:
              this.x_ = input.readUInt32();
              this.bitField0_ |= 0x20;
              continue;
            case 56:
              this.y_ = input.readUInt32();
              this.bitField0_ |= 0x40;
              continue;
            case 64:
              this.width_ = input.readUInt32();
              this.bitField0_ |= 0x80;
              continue;
            case 72:
              this.height_ = input.readUInt32();
              this.bitField0_ |= 0x100;
              continue;
            case 80:
              this.shadowDx_ = input.readUInt32();
              this.bitField0_ |= 0x200;
              continue;
            case 88:
              this.shadowDy_ = input.readUInt32();
              this.bitField0_ |= 0x400;
              continue;
            case 96:
              this.shadowRadius_ = input.readUInt32();
              this.bitField0_ |= 0x800;
              continue;
            case 106:
              this.shadowColor_ = input.readStringRequireUtf8();
              this.bitField0_ |= 0x1000;
              continue;
            case 114:
              this.strokeColor_ = input.readStringRequireUtf8();
              this.bitField0_ |= 0x2000;
              continue;
            case 120:
              this.strokeWidth_ = input.readUInt32();
              this.bitField0_ |= 0x4000;
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
    
    public boolean hasText() {
      return ((this.bitField0_ & 0x1) != 0);
    }
    
    public Text getText() {
      if (this.textBuilder_ == null)
        return (this.text_ == null) ? Text.getDefaultInstance() : this.text_; 
      return (Text)this.textBuilder_.getMessage();
    }
    
    public Builder setText(Text value) {
      if (this.textBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        this.text_ = value;
      } else {
        this.textBuilder_.setMessage((AbstractMessage)value);
      } 
      this.bitField0_ |= 0x1;
      onChanged();
      return this;
    }
    
    public Builder setText(Text.Builder builderForValue) {
      if (this.textBuilder_ == null) {
        this.text_ = builderForValue.build();
      } else {
        this.textBuilder_.setMessage((AbstractMessage)builderForValue.build());
      } 
      this.bitField0_ |= 0x1;
      onChanged();
      return this;
    }
    
    public Builder mergeText(Text value) {
      if (this.textBuilder_ == null) {
        if ((this.bitField0_ & 0x1) != 0 && this.text_ != null && this.text_ != Text.getDefaultInstance()) {
          getTextBuilder().mergeFrom(value);
        } else {
          this.text_ = value;
        } 
      } else {
        this.textBuilder_.mergeFrom((AbstractMessage)value);
      } 
      if (this.text_ != null) {
        this.bitField0_ |= 0x1;
        onChanged();
      } 
      return this;
    }
    
    public Builder clearText() {
      this.bitField0_ &= 0xFFFFFFFE;
      this.text_ = null;
      if (this.textBuilder_ != null) {
        this.textBuilder_.dispose();
        this.textBuilder_ = null;
      } 
      onChanged();
      return this;
    }
    
    public Text.Builder getTextBuilder() {
      this.bitField0_ |= 0x1;
      onChanged();
      return (Text.Builder)getTextFieldBuilder().getBuilder();
    }
    
    public TextOrBuilder getTextOrBuilder() {
      if (this.textBuilder_ != null)
        return (TextOrBuilder)this.textBuilder_.getMessageOrBuilder(); 
      return (this.text_ == null) ? Text.getDefaultInstance() : this.text_;
    }
    
    private SingleFieldBuilderV3<Text, Text.Builder, TextOrBuilder> getTextFieldBuilder() {
      if (this.textBuilder_ == null) {
        this.textBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getText(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.text_ = null;
      } 
      return this.textBuilder_;
    }
    
    public int getTextFontSize() {
      return this.textFontSize_;
    }
    
    public Builder setTextFontSize(int value) {
      this.textFontSize_ = value;
      this.bitField0_ |= 0x2;
      onChanged();
      return this;
    }
    
    public Builder clearTextFontSize() {
      this.bitField0_ &= 0xFFFFFFFD;
      this.textFontSize_ = 0;
      onChanged();
      return this;
    }
    
    public boolean hasBackground() {
      return ((this.bitField0_ & 0x4) != 0);
    }
    
    public Image getBackground() {
      if (this.backgroundBuilder_ == null)
        return (this.background_ == null) ? Image.getDefaultInstance() : this.background_; 
      return (Image)this.backgroundBuilder_.getMessage();
    }
    
    public Builder setBackground(Image value) {
      if (this.backgroundBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        this.background_ = value;
      } else {
        this.backgroundBuilder_.setMessage((AbstractMessage)value);
      } 
      this.bitField0_ |= 0x4;
      onChanged();
      return this;
    }
    
    public Builder setBackground(Image.Builder builderForValue) {
      if (this.backgroundBuilder_ == null) {
        this.background_ = builderForValue.build();
      } else {
        this.backgroundBuilder_.setMessage((AbstractMessage)builderForValue.build());
      } 
      this.bitField0_ |= 0x4;
      onChanged();
      return this;
    }
    
    public Builder mergeBackground(Image value) {
      if (this.backgroundBuilder_ == null) {
        if ((this.bitField0_ & 0x4) != 0 && this.background_ != null && this.background_ != Image.getDefaultInstance()) {
          getBackgroundBuilder().mergeFrom(value);
        } else {
          this.background_ = value;
        } 
      } else {
        this.backgroundBuilder_.mergeFrom((AbstractMessage)value);
      } 
      if (this.background_ != null) {
        this.bitField0_ |= 0x4;
        onChanged();
      } 
      return this;
    }
    
    public Builder clearBackground() {
      this.bitField0_ &= 0xFFFFFFFB;
      this.background_ = null;
      if (this.backgroundBuilder_ != null) {
        this.backgroundBuilder_.dispose();
        this.backgroundBuilder_ = null;
      } 
      onChanged();
      return this;
    }
    
    public Image.Builder getBackgroundBuilder() {
      this.bitField0_ |= 0x4;
      onChanged();
      return (Image.Builder)getBackgroundFieldBuilder().getBuilder();
    }
    
    public ImageOrBuilder getBackgroundOrBuilder() {
      if (this.backgroundBuilder_ != null)
        return (ImageOrBuilder)this.backgroundBuilder_.getMessageOrBuilder(); 
      return (this.background_ == null) ? Image.getDefaultInstance() : this.background_;
    }
    
    private SingleFieldBuilderV3<Image, Image.Builder, ImageOrBuilder> getBackgroundFieldBuilder() {
      if (this.backgroundBuilder_ == null) {
        this.backgroundBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getBackground(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.background_ = null;
      } 
      return this.backgroundBuilder_;
    }
    
    public int getStart() {
      return this.start_;
    }
    
    public Builder setStart(int value) {
      this.start_ = value;
      this.bitField0_ |= 0x8;
      onChanged();
      return this;
    }
    
    public Builder clearStart() {
      this.bitField0_ &= 0xFFFFFFF7;
      this.start_ = 0;
      onChanged();
      return this;
    }
    
    public int getDuration() {
      return this.duration_;
    }
    
    public Builder setDuration(int value) {
      this.duration_ = value;
      this.bitField0_ |= 0x10;
      onChanged();
      return this;
    }
    
    public Builder clearDuration() {
      this.bitField0_ &= 0xFFFFFFEF;
      this.duration_ = 0;
      onChanged();
      return this;
    }
    
    public int getX() {
      return this.x_;
    }
    
    public Builder setX(int value) {
      this.x_ = value;
      this.bitField0_ |= 0x20;
      onChanged();
      return this;
    }
    
    public Builder clearX() {
      this.bitField0_ &= 0xFFFFFFDF;
      this.x_ = 0;
      onChanged();
      return this;
    }
    
    public int getY() {
      return this.y_;
    }
    
    public Builder setY(int value) {
      this.y_ = value;
      this.bitField0_ |= 0x40;
      onChanged();
      return this;
    }
    
    public Builder clearY() {
      this.bitField0_ &= 0xFFFFFFBF;
      this.y_ = 0;
      onChanged();
      return this;
    }
    
    public int getWidth() {
      return this.width_;
    }
    
    public Builder setWidth(int value) {
      this.width_ = value;
      this.bitField0_ |= 0x80;
      onChanged();
      return this;
    }
    
    public Builder clearWidth() {
      this.bitField0_ &= 0xFFFFFF7F;
      this.width_ = 0;
      onChanged();
      return this;
    }
    
    public int getHeight() {
      return this.height_;
    }
    
    public Builder setHeight(int value) {
      this.height_ = value;
      this.bitField0_ |= 0x100;
      onChanged();
      return this;
    }
    
    public Builder clearHeight() {
      this.bitField0_ &= 0xFFFFFEFF;
      this.height_ = 0;
      onChanged();
      return this;
    }
    
    public int getShadowDx() {
      return this.shadowDx_;
    }
    
    public Builder setShadowDx(int value) {
      this.shadowDx_ = value;
      this.bitField0_ |= 0x200;
      onChanged();
      return this;
    }
    
    public Builder clearShadowDx() {
      this.bitField0_ &= 0xFFFFFDFF;
      this.shadowDx_ = 0;
      onChanged();
      return this;
    }
    
    public int getShadowDy() {
      return this.shadowDy_;
    }
    
    public Builder setShadowDy(int value) {
      this.shadowDy_ = value;
      this.bitField0_ |= 0x400;
      onChanged();
      return this;
    }
    
    public Builder clearShadowDy() {
      this.bitField0_ &= 0xFFFFFBFF;
      this.shadowDy_ = 0;
      onChanged();
      return this;
    }
    
    public int getShadowRadius() {
      return this.shadowRadius_;
    }
    
    public Builder setShadowRadius(int value) {
      this.shadowRadius_ = value;
      this.bitField0_ |= 0x800;
      onChanged();
      return this;
    }
    
    public Builder clearShadowRadius() {
      this.bitField0_ &= 0xFFFFF7FF;
      this.shadowRadius_ = 0;
      onChanged();
      return this;
    }
    
    public String getShadowColor() {
      Object ref = this.shadowColor_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.shadowColor_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getShadowColorBytes() {
      Object ref = this.shadowColor_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.shadowColor_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setShadowColor(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.shadowColor_ = value;
      this.bitField0_ |= 0x1000;
      onChanged();
      return this;
    }
    
    public Builder clearShadowColor() {
      this.shadowColor_ = TextEffectDetail.getDefaultInstance().getShadowColor();
      this.bitField0_ &= 0xFFFFEFFF;
      onChanged();
      return this;
    }
    
    public Builder setShadowColorBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      TextEffectDetail.checkByteStringIsUtf8(value);
      this.shadowColor_ = value;
      this.bitField0_ |= 0x1000;
      onChanged();
      return this;
    }
    
    public String getStrokeColor() {
      Object ref = this.strokeColor_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.strokeColor_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getStrokeColorBytes() {
      Object ref = this.strokeColor_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.strokeColor_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setStrokeColor(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.strokeColor_ = value;
      this.bitField0_ |= 0x2000;
      onChanged();
      return this;
    }
    
    public Builder clearStrokeColor() {
      this.strokeColor_ = TextEffectDetail.getDefaultInstance().getStrokeColor();
      this.bitField0_ &= 0xFFFFDFFF;
      onChanged();
      return this;
    }
    
    public Builder setStrokeColorBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      TextEffectDetail.checkByteStringIsUtf8(value);
      this.strokeColor_ = value;
      this.bitField0_ |= 0x2000;
      onChanged();
      return this;
    }
    
    public int getStrokeWidth() {
      return this.strokeWidth_;
    }
    
    public Builder setStrokeWidth(int value) {
      this.strokeWidth_ = value;
      this.bitField0_ |= 0x4000;
      onChanged();
      return this;
    }
    
    public Builder clearStrokeWidth() {
      this.bitField0_ &= 0xFFFFBFFF;
      this.strokeWidth_ = 0;
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
  
  private static final TextEffectDetail DEFAULT_INSTANCE = new TextEffectDetail();
  
  public static TextEffectDetail getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }
  
  private static final Parser<TextEffectDetail> PARSER = (Parser<TextEffectDetail>)new AbstractParser<TextEffectDetail>() {
      public TextEffectDetail parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        TextEffectDetail.Builder builder = TextEffectDetail.newBuilder();
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
  
  public static Parser<TextEffectDetail> parser() {
    return PARSER;
  }
  
  public Parser<TextEffectDetail> getParserForType() {
    return PARSER;
  }
  
  public TextEffectDetail getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }
}
