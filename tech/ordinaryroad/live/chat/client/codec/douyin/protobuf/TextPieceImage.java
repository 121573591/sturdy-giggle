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

public final class TextPieceImage extends GeneratedMessageV3 implements TextPieceImageOrBuilder {
  private static final long serialVersionUID = 0L;
  
  private int bitField0_;
  
  public static final int IMAGE_FIELD_NUMBER = 1;
  
  private Image image_;
  
  public static final int SCALINGRATE_FIELD_NUMBER = 2;
  
  private float scalingRate_;
  
  private byte memoizedIsInitialized;
  
  private TextPieceImage(GeneratedMessageV3.Builder<?> builder) {
    super(builder);
    this.scalingRate_ = 0.0F;
    this.memoizedIsInitialized = -1;
  }
  
  private TextPieceImage() {
    this.scalingRate_ = 0.0F;
    this.memoizedIsInitialized = -1;
  }
  
  protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
    return new TextPieceImage();
  }
  
  public static final Descriptors.Descriptor getDescriptor() {
    return Douyin.internal_static_TextPieceImage_descriptor;
  }
  
  protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
    return Douyin.internal_static_TextPieceImage_fieldAccessorTable.ensureFieldAccessorsInitialized(TextPieceImage.class, Builder.class);
  }
  
  public boolean hasImage() {
    return ((this.bitField0_ & 0x1) != 0);
  }
  
  public Image getImage() {
    return (this.image_ == null) ? Image.getDefaultInstance() : this.image_;
  }
  
  public ImageOrBuilder getImageOrBuilder() {
    return (this.image_ == null) ? Image.getDefaultInstance() : this.image_;
  }
  
  public float getScalingRate() {
    return this.scalingRate_;
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
      output.writeMessage(1, (MessageLite)getImage()); 
    if (Float.floatToRawIntBits(this.scalingRate_) != 0)
      output.writeFloat(2, this.scalingRate_); 
    getUnknownFields().writeTo(output);
  }
  
  public int getSerializedSize() {
    int size = this.memoizedSize;
    if (size != -1)
      return size; 
    size = 0;
    if ((this.bitField0_ & 0x1) != 0)
      size += 
        CodedOutputStream.computeMessageSize(1, (MessageLite)getImage()); 
    if (Float.floatToRawIntBits(this.scalingRate_) != 0)
      size += 
        CodedOutputStream.computeFloatSize(2, this.scalingRate_); 
    size += getUnknownFields().getSerializedSize();
    this.memoizedSize = size;
    return size;
  }
  
  public boolean equals(Object obj) {
    if (obj == this)
      return true; 
    if (!(obj instanceof TextPieceImage))
      return super.equals(obj); 
    TextPieceImage other = (TextPieceImage)obj;
    if (hasImage() != other.hasImage())
      return false; 
    if (hasImage() && 
      
      !getImage().equals(other.getImage()))
      return false; 
    if (Float.floatToIntBits(getScalingRate()) != 
      Float.floatToIntBits(other
        .getScalingRate()))
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
    if (hasImage()) {
      hash = 37 * hash + 1;
      hash = 53 * hash + getImage().hashCode();
    } 
    hash = 37 * hash + 2;
    hash = 53 * hash + Float.floatToIntBits(
        getScalingRate());
    hash = 29 * hash + getUnknownFields().hashCode();
    this.memoizedHashCode = hash;
    return hash;
  }
  
  public static TextPieceImage parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
    return (TextPieceImage)PARSER.parseFrom(data);
  }
  
  public static TextPieceImage parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (TextPieceImage)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static TextPieceImage parseFrom(ByteString data) throws InvalidProtocolBufferException {
    return (TextPieceImage)PARSER.parseFrom(data);
  }
  
  public static TextPieceImage parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (TextPieceImage)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static TextPieceImage parseFrom(byte[] data) throws InvalidProtocolBufferException {
    return (TextPieceImage)PARSER.parseFrom(data);
  }
  
  public static TextPieceImage parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (TextPieceImage)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static TextPieceImage parseFrom(InputStream input) throws IOException {
    return 
      (TextPieceImage)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static TextPieceImage parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (TextPieceImage)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static TextPieceImage parseDelimitedFrom(InputStream input) throws IOException {
    return 
      (TextPieceImage)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
  }
  
  public static TextPieceImage parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (TextPieceImage)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static TextPieceImage parseFrom(CodedInputStream input) throws IOException {
    return 
      (TextPieceImage)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static TextPieceImage parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (TextPieceImage)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public Builder newBuilderForType() {
    return newBuilder();
  }
  
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  
  public static Builder newBuilder(TextPieceImage prototype) {
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
  
  public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements TextPieceImageOrBuilder {
    private int bitField0_;
    
    private Image image_;
    
    private SingleFieldBuilderV3<Image, Image.Builder, ImageOrBuilder> imageBuilder_;
    
    private float scalingRate_;
    
    public static final Descriptors.Descriptor getDescriptor() {
      return Douyin.internal_static_TextPieceImage_descriptor;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return Douyin.internal_static_TextPieceImage_fieldAccessorTable
        .ensureFieldAccessorsInitialized(TextPieceImage.class, Builder.class);
    }
    
    private Builder() {
      maybeForceBuilderInitialization();
    }
    
    private Builder(GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    
    private void maybeForceBuilderInitialization() {
      if (TextPieceImage.alwaysUseFieldBuilders)
        getImageFieldBuilder(); 
    }
    
    public Builder clear() {
      super.clear();
      this.bitField0_ = 0;
      this.image_ = null;
      if (this.imageBuilder_ != null) {
        this.imageBuilder_.dispose();
        this.imageBuilder_ = null;
      } 
      this.scalingRate_ = 0.0F;
      return this;
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
      return Douyin.internal_static_TextPieceImage_descriptor;
    }
    
    public TextPieceImage getDefaultInstanceForType() {
      return TextPieceImage.getDefaultInstance();
    }
    
    public TextPieceImage build() {
      TextPieceImage result = buildPartial();
      if (!result.isInitialized())
        throw newUninitializedMessageException(result); 
      return result;
    }
    
    public TextPieceImage buildPartial() {
      TextPieceImage result = new TextPieceImage(this);
      if (this.bitField0_ != 0)
        buildPartial0(result); 
      onBuilt();
      return result;
    }
    
    private void buildPartial0(TextPieceImage result) {
      int from_bitField0_ = this.bitField0_;
      int to_bitField0_ = 0;
      if ((from_bitField0_ & 0x1) != 0) {
        result.image_ = (this.imageBuilder_ == null) ? 
          this.image_ : 
          (Image)this.imageBuilder_.build();
        to_bitField0_ |= 0x1;
      } 
      if ((from_bitField0_ & 0x2) != 0)
        result.scalingRate_ = this.scalingRate_; 
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
      if (other instanceof TextPieceImage)
        return mergeFrom((TextPieceImage)other); 
      super.mergeFrom(other);
      return this;
    }
    
    public Builder mergeFrom(TextPieceImage other) {
      if (other == TextPieceImage.getDefaultInstance())
        return this; 
      if (other.hasImage())
        mergeImage(other.getImage()); 
      if (other.getScalingRate() != 0.0F)
        setScalingRate(other.getScalingRate()); 
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
              input.readMessage((MessageLite.Builder)
                  getImageFieldBuilder().getBuilder(), extensionRegistry);
              this.bitField0_ |= 0x1;
              continue;
            case 21:
              this.scalingRate_ = input.readFloat();
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
    
    public boolean hasImage() {
      return ((this.bitField0_ & 0x1) != 0);
    }
    
    public Image getImage() {
      if (this.imageBuilder_ == null)
        return (this.image_ == null) ? Image.getDefaultInstance() : this.image_; 
      return (Image)this.imageBuilder_.getMessage();
    }
    
    public Builder setImage(Image value) {
      if (this.imageBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        this.image_ = value;
      } else {
        this.imageBuilder_.setMessage((AbstractMessage)value);
      } 
      this.bitField0_ |= 0x1;
      onChanged();
      return this;
    }
    
    public Builder setImage(Image.Builder builderForValue) {
      if (this.imageBuilder_ == null) {
        this.image_ = builderForValue.build();
      } else {
        this.imageBuilder_.setMessage((AbstractMessage)builderForValue.build());
      } 
      this.bitField0_ |= 0x1;
      onChanged();
      return this;
    }
    
    public Builder mergeImage(Image value) {
      if (this.imageBuilder_ == null) {
        if ((this.bitField0_ & 0x1) != 0 && this.image_ != null && this.image_ != 
          
          Image.getDefaultInstance()) {
          getImageBuilder().mergeFrom(value);
        } else {
          this.image_ = value;
        } 
      } else {
        this.imageBuilder_.mergeFrom((AbstractMessage)value);
      } 
      if (this.image_ != null) {
        this.bitField0_ |= 0x1;
        onChanged();
      } 
      return this;
    }
    
    public Builder clearImage() {
      this.bitField0_ &= 0xFFFFFFFE;
      this.image_ = null;
      if (this.imageBuilder_ != null) {
        this.imageBuilder_.dispose();
        this.imageBuilder_ = null;
      } 
      onChanged();
      return this;
    }
    
    public Image.Builder getImageBuilder() {
      this.bitField0_ |= 0x1;
      onChanged();
      return (Image.Builder)getImageFieldBuilder().getBuilder();
    }
    
    public ImageOrBuilder getImageOrBuilder() {
      if (this.imageBuilder_ != null)
        return (ImageOrBuilder)this.imageBuilder_.getMessageOrBuilder(); 
      return (this.image_ == null) ? 
        Image.getDefaultInstance() : this.image_;
    }
    
    private SingleFieldBuilderV3<Image, Image.Builder, ImageOrBuilder> getImageFieldBuilder() {
      if (this.imageBuilder_ == null) {
        this
          
          .imageBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getImage(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.image_ = null;
      } 
      return this.imageBuilder_;
    }
    
    public float getScalingRate() {
      return this.scalingRate_;
    }
    
    public Builder setScalingRate(float value) {
      this.scalingRate_ = value;
      this.bitField0_ |= 0x2;
      onChanged();
      return this;
    }
    
    public Builder clearScalingRate() {
      this.bitField0_ &= 0xFFFFFFFD;
      this.scalingRate_ = 0.0F;
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
  
  private static final TextPieceImage DEFAULT_INSTANCE = new TextPieceImage();
  
  public static TextPieceImage getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }
  
  private static final Parser<TextPieceImage> PARSER = (Parser<TextPieceImage>)new AbstractParser<TextPieceImage>() {
      public TextPieceImage parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        TextPieceImage.Builder builder = TextPieceImage.newBuilder();
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
  
  public static Parser<TextPieceImage> parser() {
    return PARSER;
  }
  
  public Parser<TextPieceImage> getParserForType() {
    return PARSER;
  }
  
  public TextPieceImage getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }
}
