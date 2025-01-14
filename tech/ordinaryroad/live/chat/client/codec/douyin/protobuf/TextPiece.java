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

public final class TextPiece extends GeneratedMessageV3 implements TextPieceOrBuilder {
  private static final long serialVersionUID = 0L;
  
  private int bitField0_;
  
  public static final int TYPE_FIELD_NUMBER = 1;
  
  private int type_;
  
  public static final int FORMAT_FIELD_NUMBER = 2;
  
  private TextFormat format_;
  
  public static final int VALUE_REF_FIELD_NUMBER = 3;
  
  private volatile Object valueRef_;
  
  public static final int STRING_VALUE_FIELD_NUMBER = 11;
  
  private volatile Object stringValue_;
  
  public static final int USERVALUE_FIELD_NUMBER = 21;
  
  private TextPieceUser uservalue_;
  
  public static final int GIFTVALUE_FIELD_NUMBER = 22;
  
  private TextPieceGift giftvalue_;
  
  public static final int HEARTVALUE_FIELD_NUMBER = 23;
  
  private TextPieceHeart heartvalue_;
  
  public static final int PATTERNREFVALUE_FIELD_NUMBER = 24;
  
  private TextPiecePatternRef patternrefvalue_;
  
  public static final int IMAGEVALUE_FIELD_NUMBER = 25;
  
  private TextPieceImage imagevalue_;
  
  public static final int SCHEMA_KEY_FIELD_NUMBER = 100;
  
  private volatile Object schemaKey_;
  
  private byte memoizedIsInitialized;
  
  private TextPiece(GeneratedMessageV3.Builder<?> builder) {
    super(builder);
    this.type_ = 0;
    this.valueRef_ = "";
    this.stringValue_ = "";
    this.schemaKey_ = "";
    this.memoizedIsInitialized = -1;
  }
  
  private TextPiece() {
    this.type_ = 0;
    this.valueRef_ = "";
    this.stringValue_ = "";
    this.schemaKey_ = "";
    this.memoizedIsInitialized = -1;
    this.valueRef_ = "";
    this.stringValue_ = "";
    this.schemaKey_ = "";
  }
  
  protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
    return new TextPiece();
  }
  
  public static final Descriptors.Descriptor getDescriptor() {
    return Douyin.internal_static_TextPiece_descriptor;
  }
  
  protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
    return Douyin.internal_static_TextPiece_fieldAccessorTable.ensureFieldAccessorsInitialized(TextPiece.class, Builder.class);
  }
  
  public int getType() {
    return this.type_;
  }
  
  public boolean hasFormat() {
    return ((this.bitField0_ & 0x1) != 0);
  }
  
  public TextFormat getFormat() {
    return (this.format_ == null) ? TextFormat.getDefaultInstance() : this.format_;
  }
  
  public TextFormatOrBuilder getFormatOrBuilder() {
    return (this.format_ == null) ? TextFormat.getDefaultInstance() : this.format_;
  }
  
  public String getValueRef() {
    Object ref = this.valueRef_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.valueRef_ = s;
    return s;
  }
  
  public ByteString getValueRefBytes() {
    Object ref = this.valueRef_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.valueRef_ = b;
      return b;
    } 
    return (ByteString)ref;
  }
  
  public String getStringValue() {
    Object ref = this.stringValue_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.stringValue_ = s;
    return s;
  }
  
  public ByteString getStringValueBytes() {
    Object ref = this.stringValue_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.stringValue_ = b;
      return b;
    } 
    return (ByteString)ref;
  }
  
  public boolean hasUservalue() {
    return ((this.bitField0_ & 0x2) != 0);
  }
  
  public TextPieceUser getUservalue() {
    return (this.uservalue_ == null) ? TextPieceUser.getDefaultInstance() : this.uservalue_;
  }
  
  public TextPieceUserOrBuilder getUservalueOrBuilder() {
    return (this.uservalue_ == null) ? TextPieceUser.getDefaultInstance() : this.uservalue_;
  }
  
  public boolean hasGiftvalue() {
    return ((this.bitField0_ & 0x4) != 0);
  }
  
  public TextPieceGift getGiftvalue() {
    return (this.giftvalue_ == null) ? TextPieceGift.getDefaultInstance() : this.giftvalue_;
  }
  
  public TextPieceGiftOrBuilder getGiftvalueOrBuilder() {
    return (this.giftvalue_ == null) ? TextPieceGift.getDefaultInstance() : this.giftvalue_;
  }
  
  public boolean hasHeartvalue() {
    return ((this.bitField0_ & 0x8) != 0);
  }
  
  public TextPieceHeart getHeartvalue() {
    return (this.heartvalue_ == null) ? TextPieceHeart.getDefaultInstance() : this.heartvalue_;
  }
  
  public TextPieceHeartOrBuilder getHeartvalueOrBuilder() {
    return (this.heartvalue_ == null) ? TextPieceHeart.getDefaultInstance() : this.heartvalue_;
  }
  
  public boolean hasPatternrefvalue() {
    return ((this.bitField0_ & 0x10) != 0);
  }
  
  public TextPiecePatternRef getPatternrefvalue() {
    return (this.patternrefvalue_ == null) ? TextPiecePatternRef.getDefaultInstance() : this.patternrefvalue_;
  }
  
  public TextPiecePatternRefOrBuilder getPatternrefvalueOrBuilder() {
    return (this.patternrefvalue_ == null) ? TextPiecePatternRef.getDefaultInstance() : this.patternrefvalue_;
  }
  
  public boolean hasImagevalue() {
    return ((this.bitField0_ & 0x20) != 0);
  }
  
  public TextPieceImage getImagevalue() {
    return (this.imagevalue_ == null) ? TextPieceImage.getDefaultInstance() : this.imagevalue_;
  }
  
  public TextPieceImageOrBuilder getImagevalueOrBuilder() {
    return (this.imagevalue_ == null) ? TextPieceImage.getDefaultInstance() : this.imagevalue_;
  }
  
  public String getSchemaKey() {
    Object ref = this.schemaKey_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.schemaKey_ = s;
    return s;
  }
  
  public ByteString getSchemaKeyBytes() {
    Object ref = this.schemaKey_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.schemaKey_ = b;
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
    if (this.type_ != 0)
      output.writeUInt32(1, this.type_); 
    if ((this.bitField0_ & 0x1) != 0)
      output.writeMessage(2, (MessageLite)getFormat()); 
    if (!GeneratedMessageV3.isStringEmpty(this.valueRef_))
      GeneratedMessageV3.writeString(output, 3, this.valueRef_); 
    if (!GeneratedMessageV3.isStringEmpty(this.stringValue_))
      GeneratedMessageV3.writeString(output, 11, this.stringValue_); 
    if ((this.bitField0_ & 0x2) != 0)
      output.writeMessage(21, (MessageLite)getUservalue()); 
    if ((this.bitField0_ & 0x4) != 0)
      output.writeMessage(22, (MessageLite)getGiftvalue()); 
    if ((this.bitField0_ & 0x8) != 0)
      output.writeMessage(23, (MessageLite)getHeartvalue()); 
    if ((this.bitField0_ & 0x10) != 0)
      output.writeMessage(24, (MessageLite)getPatternrefvalue()); 
    if ((this.bitField0_ & 0x20) != 0)
      output.writeMessage(25, (MessageLite)getImagevalue()); 
    if (!GeneratedMessageV3.isStringEmpty(this.schemaKey_))
      GeneratedMessageV3.writeString(output, 100, this.schemaKey_); 
    getUnknownFields().writeTo(output);
  }
  
  public int getSerializedSize() {
    int size = this.memoizedSize;
    if (size != -1)
      return size; 
    size = 0;
    if (this.type_ != 0)
      size += 
        CodedOutputStream.computeUInt32Size(1, this.type_); 
    if ((this.bitField0_ & 0x1) != 0)
      size += 
        CodedOutputStream.computeMessageSize(2, (MessageLite)getFormat()); 
    if (!GeneratedMessageV3.isStringEmpty(this.valueRef_))
      size += GeneratedMessageV3.computeStringSize(3, this.valueRef_); 
    if (!GeneratedMessageV3.isStringEmpty(this.stringValue_))
      size += GeneratedMessageV3.computeStringSize(11, this.stringValue_); 
    if ((this.bitField0_ & 0x2) != 0)
      size += 
        CodedOutputStream.computeMessageSize(21, (MessageLite)getUservalue()); 
    if ((this.bitField0_ & 0x4) != 0)
      size += 
        CodedOutputStream.computeMessageSize(22, (MessageLite)getGiftvalue()); 
    if ((this.bitField0_ & 0x8) != 0)
      size += 
        CodedOutputStream.computeMessageSize(23, (MessageLite)getHeartvalue()); 
    if ((this.bitField0_ & 0x10) != 0)
      size += 
        CodedOutputStream.computeMessageSize(24, (MessageLite)getPatternrefvalue()); 
    if ((this.bitField0_ & 0x20) != 0)
      size += 
        CodedOutputStream.computeMessageSize(25, (MessageLite)getImagevalue()); 
    if (!GeneratedMessageV3.isStringEmpty(this.schemaKey_))
      size += GeneratedMessageV3.computeStringSize(100, this.schemaKey_); 
    size += getUnknownFields().getSerializedSize();
    this.memoizedSize = size;
    return size;
  }
  
  public boolean equals(Object obj) {
    if (obj == this)
      return true; 
    if (!(obj instanceof TextPiece))
      return super.equals(obj); 
    TextPiece other = (TextPiece)obj;
    if (getType() != other
      .getType())
      return false; 
    if (hasFormat() != other.hasFormat())
      return false; 
    if (hasFormat() && 
      
      !getFormat().equals(other.getFormat()))
      return false; 
    if (!getValueRef().equals(other.getValueRef()))
      return false; 
    if (!getStringValue().equals(other.getStringValue()))
      return false; 
    if (hasUservalue() != other.hasUservalue())
      return false; 
    if (hasUservalue() && 
      
      !getUservalue().equals(other.getUservalue()))
      return false; 
    if (hasGiftvalue() != other.hasGiftvalue())
      return false; 
    if (hasGiftvalue() && 
      
      !getGiftvalue().equals(other.getGiftvalue()))
      return false; 
    if (hasHeartvalue() != other.hasHeartvalue())
      return false; 
    if (hasHeartvalue() && 
      
      !getHeartvalue().equals(other.getHeartvalue()))
      return false; 
    if (hasPatternrefvalue() != other.hasPatternrefvalue())
      return false; 
    if (hasPatternrefvalue() && 
      
      !getPatternrefvalue().equals(other.getPatternrefvalue()))
      return false; 
    if (hasImagevalue() != other.hasImagevalue())
      return false; 
    if (hasImagevalue() && 
      
      !getImagevalue().equals(other.getImagevalue()))
      return false; 
    if (!getSchemaKey().equals(other.getSchemaKey()))
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
    hash = 53 * hash + getType();
    if (hasFormat()) {
      hash = 37 * hash + 2;
      hash = 53 * hash + getFormat().hashCode();
    } 
    hash = 37 * hash + 3;
    hash = 53 * hash + getValueRef().hashCode();
    hash = 37 * hash + 11;
    hash = 53 * hash + getStringValue().hashCode();
    if (hasUservalue()) {
      hash = 37 * hash + 21;
      hash = 53 * hash + getUservalue().hashCode();
    } 
    if (hasGiftvalue()) {
      hash = 37 * hash + 22;
      hash = 53 * hash + getGiftvalue().hashCode();
    } 
    if (hasHeartvalue()) {
      hash = 37 * hash + 23;
      hash = 53 * hash + getHeartvalue().hashCode();
    } 
    if (hasPatternrefvalue()) {
      hash = 37 * hash + 24;
      hash = 53 * hash + getPatternrefvalue().hashCode();
    } 
    if (hasImagevalue()) {
      hash = 37 * hash + 25;
      hash = 53 * hash + getImagevalue().hashCode();
    } 
    hash = 37 * hash + 100;
    hash = 53 * hash + getSchemaKey().hashCode();
    hash = 29 * hash + getUnknownFields().hashCode();
    this.memoizedHashCode = hash;
    return hash;
  }
  
  public static TextPiece parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
    return (TextPiece)PARSER.parseFrom(data);
  }
  
  public static TextPiece parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (TextPiece)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static TextPiece parseFrom(ByteString data) throws InvalidProtocolBufferException {
    return (TextPiece)PARSER.parseFrom(data);
  }
  
  public static TextPiece parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (TextPiece)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static TextPiece parseFrom(byte[] data) throws InvalidProtocolBufferException {
    return (TextPiece)PARSER.parseFrom(data);
  }
  
  public static TextPiece parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (TextPiece)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static TextPiece parseFrom(InputStream input) throws IOException {
    return 
      (TextPiece)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static TextPiece parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (TextPiece)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static TextPiece parseDelimitedFrom(InputStream input) throws IOException {
    return 
      (TextPiece)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
  }
  
  public static TextPiece parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (TextPiece)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static TextPiece parseFrom(CodedInputStream input) throws IOException {
    return 
      (TextPiece)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static TextPiece parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (TextPiece)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public Builder newBuilderForType() {
    return newBuilder();
  }
  
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  
  public static Builder newBuilder(TextPiece prototype) {
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
  
  public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements TextPieceOrBuilder {
    private int bitField0_;
    
    private int type_;
    
    private TextFormat format_;
    
    private SingleFieldBuilderV3<TextFormat, TextFormat.Builder, TextFormatOrBuilder> formatBuilder_;
    
    private Object valueRef_;
    
    private Object stringValue_;
    
    private TextPieceUser uservalue_;
    
    private SingleFieldBuilderV3<TextPieceUser, TextPieceUser.Builder, TextPieceUserOrBuilder> uservalueBuilder_;
    
    private TextPieceGift giftvalue_;
    
    private SingleFieldBuilderV3<TextPieceGift, TextPieceGift.Builder, TextPieceGiftOrBuilder> giftvalueBuilder_;
    
    private TextPieceHeart heartvalue_;
    
    private SingleFieldBuilderV3<TextPieceHeart, TextPieceHeart.Builder, TextPieceHeartOrBuilder> heartvalueBuilder_;
    
    private TextPiecePatternRef patternrefvalue_;
    
    private SingleFieldBuilderV3<TextPiecePatternRef, TextPiecePatternRef.Builder, TextPiecePatternRefOrBuilder> patternrefvalueBuilder_;
    
    private TextPieceImage imagevalue_;
    
    private SingleFieldBuilderV3<TextPieceImage, TextPieceImage.Builder, TextPieceImageOrBuilder> imagevalueBuilder_;
    
    private Object schemaKey_;
    
    public static final Descriptors.Descriptor getDescriptor() {
      return Douyin.internal_static_TextPiece_descriptor;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return Douyin.internal_static_TextPiece_fieldAccessorTable
        .ensureFieldAccessorsInitialized(TextPiece.class, Builder.class);
    }
    
    private Builder() {
      this.valueRef_ = "";
      this.stringValue_ = "";
      this.schemaKey_ = "";
      maybeForceBuilderInitialization();
    }
    
    private Builder(GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      this.valueRef_ = "";
      this.stringValue_ = "";
      this.schemaKey_ = "";
      maybeForceBuilderInitialization();
    }
    
    private void maybeForceBuilderInitialization() {
      if (TextPiece.alwaysUseFieldBuilders) {
        getFormatFieldBuilder();
        getUservalueFieldBuilder();
        getGiftvalueFieldBuilder();
        getHeartvalueFieldBuilder();
        getPatternrefvalueFieldBuilder();
        getImagevalueFieldBuilder();
      } 
    }
    
    public Builder clear() {
      super.clear();
      this.bitField0_ = 0;
      this.type_ = 0;
      this.format_ = null;
      if (this.formatBuilder_ != null) {
        this.formatBuilder_.dispose();
        this.formatBuilder_ = null;
      } 
      this.valueRef_ = "";
      this.stringValue_ = "";
      this.uservalue_ = null;
      if (this.uservalueBuilder_ != null) {
        this.uservalueBuilder_.dispose();
        this.uservalueBuilder_ = null;
      } 
      this.giftvalue_ = null;
      if (this.giftvalueBuilder_ != null) {
        this.giftvalueBuilder_.dispose();
        this.giftvalueBuilder_ = null;
      } 
      this.heartvalue_ = null;
      if (this.heartvalueBuilder_ != null) {
        this.heartvalueBuilder_.dispose();
        this.heartvalueBuilder_ = null;
      } 
      this.patternrefvalue_ = null;
      if (this.patternrefvalueBuilder_ != null) {
        this.patternrefvalueBuilder_.dispose();
        this.patternrefvalueBuilder_ = null;
      } 
      this.imagevalue_ = null;
      if (this.imagevalueBuilder_ != null) {
        this.imagevalueBuilder_.dispose();
        this.imagevalueBuilder_ = null;
      } 
      this.schemaKey_ = "";
      return this;
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
      return Douyin.internal_static_TextPiece_descriptor;
    }
    
    public TextPiece getDefaultInstanceForType() {
      return TextPiece.getDefaultInstance();
    }
    
    public TextPiece build() {
      TextPiece result = buildPartial();
      if (!result.isInitialized())
        throw newUninitializedMessageException(result); 
      return result;
    }
    
    public TextPiece buildPartial() {
      TextPiece result = new TextPiece(this);
      if (this.bitField0_ != 0)
        buildPartial0(result); 
      onBuilt();
      return result;
    }
    
    private void buildPartial0(TextPiece result) {
      int from_bitField0_ = this.bitField0_;
      if ((from_bitField0_ & 0x1) != 0)
        result.type_ = this.type_; 
      int to_bitField0_ = 0;
      if ((from_bitField0_ & 0x2) != 0) {
        result.format_ = (this.formatBuilder_ == null) ? this.format_ : (TextFormat)this.formatBuilder_.build();
        to_bitField0_ |= 0x1;
      } 
      if ((from_bitField0_ & 0x4) != 0)
        result.valueRef_ = this.valueRef_; 
      if ((from_bitField0_ & 0x8) != 0)
        result.stringValue_ = this.stringValue_; 
      if ((from_bitField0_ & 0x10) != 0) {
        result.uservalue_ = (this.uservalueBuilder_ == null) ? this.uservalue_ : (TextPieceUser)this.uservalueBuilder_.build();
        to_bitField0_ |= 0x2;
      } 
      if ((from_bitField0_ & 0x20) != 0) {
        result.giftvalue_ = (this.giftvalueBuilder_ == null) ? this.giftvalue_ : (TextPieceGift)this.giftvalueBuilder_.build();
        to_bitField0_ |= 0x4;
      } 
      if ((from_bitField0_ & 0x40) != 0) {
        result.heartvalue_ = (this.heartvalueBuilder_ == null) ? this.heartvalue_ : (TextPieceHeart)this.heartvalueBuilder_.build();
        to_bitField0_ |= 0x8;
      } 
      if ((from_bitField0_ & 0x80) != 0) {
        result.patternrefvalue_ = (this.patternrefvalueBuilder_ == null) ? this.patternrefvalue_ : (TextPiecePatternRef)this.patternrefvalueBuilder_.build();
        to_bitField0_ |= 0x10;
      } 
      if ((from_bitField0_ & 0x100) != 0) {
        result.imagevalue_ = (this.imagevalueBuilder_ == null) ? this.imagevalue_ : (TextPieceImage)this.imagevalueBuilder_.build();
        to_bitField0_ |= 0x20;
      } 
      if ((from_bitField0_ & 0x200) != 0)
        result.schemaKey_ = this.schemaKey_; 
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
      if (other instanceof TextPiece)
        return mergeFrom((TextPiece)other); 
      super.mergeFrom(other);
      return this;
    }
    
    public Builder mergeFrom(TextPiece other) {
      if (other == TextPiece.getDefaultInstance())
        return this; 
      if (other.getType() != 0)
        setType(other.getType()); 
      if (other.hasFormat())
        mergeFormat(other.getFormat()); 
      if (!other.getValueRef().isEmpty()) {
        this.valueRef_ = other.valueRef_;
        this.bitField0_ |= 0x4;
        onChanged();
      } 
      if (!other.getStringValue().isEmpty()) {
        this.stringValue_ = other.stringValue_;
        this.bitField0_ |= 0x8;
        onChanged();
      } 
      if (other.hasUservalue())
        mergeUservalue(other.getUservalue()); 
      if (other.hasGiftvalue())
        mergeGiftvalue(other.getGiftvalue()); 
      if (other.hasHeartvalue())
        mergeHeartvalue(other.getHeartvalue()); 
      if (other.hasPatternrefvalue())
        mergePatternrefvalue(other.getPatternrefvalue()); 
      if (other.hasImagevalue())
        mergeImagevalue(other.getImagevalue()); 
      if (!other.getSchemaKey().isEmpty()) {
        this.schemaKey_ = other.schemaKey_;
        this.bitField0_ |= 0x200;
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
            case 8:
              this.type_ = input.readUInt32();
              this.bitField0_ |= 0x1;
              continue;
            case 18:
              input.readMessage((MessageLite.Builder)getFormatFieldBuilder().getBuilder(), extensionRegistry);
              this.bitField0_ |= 0x2;
              continue;
            case 26:
              this.valueRef_ = input.readStringRequireUtf8();
              this.bitField0_ |= 0x4;
              continue;
            case 90:
              this.stringValue_ = input.readStringRequireUtf8();
              this.bitField0_ |= 0x8;
              continue;
            case 170:
              input.readMessage((MessageLite.Builder)getUservalueFieldBuilder().getBuilder(), extensionRegistry);
              this.bitField0_ |= 0x10;
              continue;
            case 178:
              input.readMessage((MessageLite.Builder)getGiftvalueFieldBuilder().getBuilder(), extensionRegistry);
              this.bitField0_ |= 0x20;
              continue;
            case 186:
              input.readMessage((MessageLite.Builder)getHeartvalueFieldBuilder().getBuilder(), extensionRegistry);
              this.bitField0_ |= 0x40;
              continue;
            case 194:
              input.readMessage((MessageLite.Builder)getPatternrefvalueFieldBuilder().getBuilder(), extensionRegistry);
              this.bitField0_ |= 0x80;
              continue;
            case 202:
              input.readMessage((MessageLite.Builder)getImagevalueFieldBuilder().getBuilder(), extensionRegistry);
              this.bitField0_ |= 0x100;
              continue;
            case 802:
              this.schemaKey_ = input.readStringRequireUtf8();
              this.bitField0_ |= 0x200;
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
    
    public int getType() {
      return this.type_;
    }
    
    public Builder setType(int value) {
      this.type_ = value;
      this.bitField0_ |= 0x1;
      onChanged();
      return this;
    }
    
    public Builder clearType() {
      this.bitField0_ &= 0xFFFFFFFE;
      this.type_ = 0;
      onChanged();
      return this;
    }
    
    public boolean hasFormat() {
      return ((this.bitField0_ & 0x2) != 0);
    }
    
    public TextFormat getFormat() {
      if (this.formatBuilder_ == null)
        return (this.format_ == null) ? TextFormat.getDefaultInstance() : this.format_; 
      return (TextFormat)this.formatBuilder_.getMessage();
    }
    
    public Builder setFormat(TextFormat value) {
      if (this.formatBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        this.format_ = value;
      } else {
        this.formatBuilder_.setMessage((AbstractMessage)value);
      } 
      this.bitField0_ |= 0x2;
      onChanged();
      return this;
    }
    
    public Builder setFormat(TextFormat.Builder builderForValue) {
      if (this.formatBuilder_ == null) {
        this.format_ = builderForValue.build();
      } else {
        this.formatBuilder_.setMessage((AbstractMessage)builderForValue.build());
      } 
      this.bitField0_ |= 0x2;
      onChanged();
      return this;
    }
    
    public Builder mergeFormat(TextFormat value) {
      if (this.formatBuilder_ == null) {
        if ((this.bitField0_ & 0x2) != 0 && this.format_ != null && this.format_ != TextFormat.getDefaultInstance()) {
          getFormatBuilder().mergeFrom(value);
        } else {
          this.format_ = value;
        } 
      } else {
        this.formatBuilder_.mergeFrom((AbstractMessage)value);
      } 
      if (this.format_ != null) {
        this.bitField0_ |= 0x2;
        onChanged();
      } 
      return this;
    }
    
    public Builder clearFormat() {
      this.bitField0_ &= 0xFFFFFFFD;
      this.format_ = null;
      if (this.formatBuilder_ != null) {
        this.formatBuilder_.dispose();
        this.formatBuilder_ = null;
      } 
      onChanged();
      return this;
    }
    
    public TextFormat.Builder getFormatBuilder() {
      this.bitField0_ |= 0x2;
      onChanged();
      return (TextFormat.Builder)getFormatFieldBuilder().getBuilder();
    }
    
    public TextFormatOrBuilder getFormatOrBuilder() {
      if (this.formatBuilder_ != null)
        return (TextFormatOrBuilder)this.formatBuilder_.getMessageOrBuilder(); 
      return (this.format_ == null) ? TextFormat.getDefaultInstance() : this.format_;
    }
    
    private SingleFieldBuilderV3<TextFormat, TextFormat.Builder, TextFormatOrBuilder> getFormatFieldBuilder() {
      if (this.formatBuilder_ == null) {
        this.formatBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getFormat(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.format_ = null;
      } 
      return this.formatBuilder_;
    }
    
    public String getValueRef() {
      Object ref = this.valueRef_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.valueRef_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getValueRefBytes() {
      Object ref = this.valueRef_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.valueRef_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setValueRef(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.valueRef_ = value;
      this.bitField0_ |= 0x4;
      onChanged();
      return this;
    }
    
    public Builder clearValueRef() {
      this.valueRef_ = TextPiece.getDefaultInstance().getValueRef();
      this.bitField0_ &= 0xFFFFFFFB;
      onChanged();
      return this;
    }
    
    public Builder setValueRefBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      TextPiece.checkByteStringIsUtf8(value);
      this.valueRef_ = value;
      this.bitField0_ |= 0x4;
      onChanged();
      return this;
    }
    
    public String getStringValue() {
      Object ref = this.stringValue_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.stringValue_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getStringValueBytes() {
      Object ref = this.stringValue_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.stringValue_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setStringValue(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.stringValue_ = value;
      this.bitField0_ |= 0x8;
      onChanged();
      return this;
    }
    
    public Builder clearStringValue() {
      this.stringValue_ = TextPiece.getDefaultInstance().getStringValue();
      this.bitField0_ &= 0xFFFFFFF7;
      onChanged();
      return this;
    }
    
    public Builder setStringValueBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      TextPiece.checkByteStringIsUtf8(value);
      this.stringValue_ = value;
      this.bitField0_ |= 0x8;
      onChanged();
      return this;
    }
    
    public boolean hasUservalue() {
      return ((this.bitField0_ & 0x10) != 0);
    }
    
    public TextPieceUser getUservalue() {
      if (this.uservalueBuilder_ == null)
        return (this.uservalue_ == null) ? TextPieceUser.getDefaultInstance() : this.uservalue_; 
      return (TextPieceUser)this.uservalueBuilder_.getMessage();
    }
    
    public Builder setUservalue(TextPieceUser value) {
      if (this.uservalueBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        this.uservalue_ = value;
      } else {
        this.uservalueBuilder_.setMessage((AbstractMessage)value);
      } 
      this.bitField0_ |= 0x10;
      onChanged();
      return this;
    }
    
    public Builder setUservalue(TextPieceUser.Builder builderForValue) {
      if (this.uservalueBuilder_ == null) {
        this.uservalue_ = builderForValue.build();
      } else {
        this.uservalueBuilder_.setMessage((AbstractMessage)builderForValue.build());
      } 
      this.bitField0_ |= 0x10;
      onChanged();
      return this;
    }
    
    public Builder mergeUservalue(TextPieceUser value) {
      if (this.uservalueBuilder_ == null) {
        if ((this.bitField0_ & 0x10) != 0 && this.uservalue_ != null && this.uservalue_ != TextPieceUser.getDefaultInstance()) {
          getUservalueBuilder().mergeFrom(value);
        } else {
          this.uservalue_ = value;
        } 
      } else {
        this.uservalueBuilder_.mergeFrom((AbstractMessage)value);
      } 
      if (this.uservalue_ != null) {
        this.bitField0_ |= 0x10;
        onChanged();
      } 
      return this;
    }
    
    public Builder clearUservalue() {
      this.bitField0_ &= 0xFFFFFFEF;
      this.uservalue_ = null;
      if (this.uservalueBuilder_ != null) {
        this.uservalueBuilder_.dispose();
        this.uservalueBuilder_ = null;
      } 
      onChanged();
      return this;
    }
    
    public TextPieceUser.Builder getUservalueBuilder() {
      this.bitField0_ |= 0x10;
      onChanged();
      return (TextPieceUser.Builder)getUservalueFieldBuilder().getBuilder();
    }
    
    public TextPieceUserOrBuilder getUservalueOrBuilder() {
      if (this.uservalueBuilder_ != null)
        return (TextPieceUserOrBuilder)this.uservalueBuilder_.getMessageOrBuilder(); 
      return (this.uservalue_ == null) ? TextPieceUser.getDefaultInstance() : this.uservalue_;
    }
    
    private SingleFieldBuilderV3<TextPieceUser, TextPieceUser.Builder, TextPieceUserOrBuilder> getUservalueFieldBuilder() {
      if (this.uservalueBuilder_ == null) {
        this.uservalueBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getUservalue(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.uservalue_ = null;
      } 
      return this.uservalueBuilder_;
    }
    
    public boolean hasGiftvalue() {
      return ((this.bitField0_ & 0x20) != 0);
    }
    
    public TextPieceGift getGiftvalue() {
      if (this.giftvalueBuilder_ == null)
        return (this.giftvalue_ == null) ? TextPieceGift.getDefaultInstance() : this.giftvalue_; 
      return (TextPieceGift)this.giftvalueBuilder_.getMessage();
    }
    
    public Builder setGiftvalue(TextPieceGift value) {
      if (this.giftvalueBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        this.giftvalue_ = value;
      } else {
        this.giftvalueBuilder_.setMessage((AbstractMessage)value);
      } 
      this.bitField0_ |= 0x20;
      onChanged();
      return this;
    }
    
    public Builder setGiftvalue(TextPieceGift.Builder builderForValue) {
      if (this.giftvalueBuilder_ == null) {
        this.giftvalue_ = builderForValue.build();
      } else {
        this.giftvalueBuilder_.setMessage((AbstractMessage)builderForValue.build());
      } 
      this.bitField0_ |= 0x20;
      onChanged();
      return this;
    }
    
    public Builder mergeGiftvalue(TextPieceGift value) {
      if (this.giftvalueBuilder_ == null) {
        if ((this.bitField0_ & 0x20) != 0 && this.giftvalue_ != null && this.giftvalue_ != TextPieceGift.getDefaultInstance()) {
          getGiftvalueBuilder().mergeFrom(value);
        } else {
          this.giftvalue_ = value;
        } 
      } else {
        this.giftvalueBuilder_.mergeFrom((AbstractMessage)value);
      } 
      if (this.giftvalue_ != null) {
        this.bitField0_ |= 0x20;
        onChanged();
      } 
      return this;
    }
    
    public Builder clearGiftvalue() {
      this.bitField0_ &= 0xFFFFFFDF;
      this.giftvalue_ = null;
      if (this.giftvalueBuilder_ != null) {
        this.giftvalueBuilder_.dispose();
        this.giftvalueBuilder_ = null;
      } 
      onChanged();
      return this;
    }
    
    public TextPieceGift.Builder getGiftvalueBuilder() {
      this.bitField0_ |= 0x20;
      onChanged();
      return (TextPieceGift.Builder)getGiftvalueFieldBuilder().getBuilder();
    }
    
    public TextPieceGiftOrBuilder getGiftvalueOrBuilder() {
      if (this.giftvalueBuilder_ != null)
        return (TextPieceGiftOrBuilder)this.giftvalueBuilder_.getMessageOrBuilder(); 
      return (this.giftvalue_ == null) ? TextPieceGift.getDefaultInstance() : this.giftvalue_;
    }
    
    private SingleFieldBuilderV3<TextPieceGift, TextPieceGift.Builder, TextPieceGiftOrBuilder> getGiftvalueFieldBuilder() {
      if (this.giftvalueBuilder_ == null) {
        this.giftvalueBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getGiftvalue(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.giftvalue_ = null;
      } 
      return this.giftvalueBuilder_;
    }
    
    public boolean hasHeartvalue() {
      return ((this.bitField0_ & 0x40) != 0);
    }
    
    public TextPieceHeart getHeartvalue() {
      if (this.heartvalueBuilder_ == null)
        return (this.heartvalue_ == null) ? TextPieceHeart.getDefaultInstance() : this.heartvalue_; 
      return (TextPieceHeart)this.heartvalueBuilder_.getMessage();
    }
    
    public Builder setHeartvalue(TextPieceHeart value) {
      if (this.heartvalueBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        this.heartvalue_ = value;
      } else {
        this.heartvalueBuilder_.setMessage((AbstractMessage)value);
      } 
      this.bitField0_ |= 0x40;
      onChanged();
      return this;
    }
    
    public Builder setHeartvalue(TextPieceHeart.Builder builderForValue) {
      if (this.heartvalueBuilder_ == null) {
        this.heartvalue_ = builderForValue.build();
      } else {
        this.heartvalueBuilder_.setMessage((AbstractMessage)builderForValue.build());
      } 
      this.bitField0_ |= 0x40;
      onChanged();
      return this;
    }
    
    public Builder mergeHeartvalue(TextPieceHeart value) {
      if (this.heartvalueBuilder_ == null) {
        if ((this.bitField0_ & 0x40) != 0 && this.heartvalue_ != null && this.heartvalue_ != TextPieceHeart.getDefaultInstance()) {
          getHeartvalueBuilder().mergeFrom(value);
        } else {
          this.heartvalue_ = value;
        } 
      } else {
        this.heartvalueBuilder_.mergeFrom((AbstractMessage)value);
      } 
      if (this.heartvalue_ != null) {
        this.bitField0_ |= 0x40;
        onChanged();
      } 
      return this;
    }
    
    public Builder clearHeartvalue() {
      this.bitField0_ &= 0xFFFFFFBF;
      this.heartvalue_ = null;
      if (this.heartvalueBuilder_ != null) {
        this.heartvalueBuilder_.dispose();
        this.heartvalueBuilder_ = null;
      } 
      onChanged();
      return this;
    }
    
    public TextPieceHeart.Builder getHeartvalueBuilder() {
      this.bitField0_ |= 0x40;
      onChanged();
      return (TextPieceHeart.Builder)getHeartvalueFieldBuilder().getBuilder();
    }
    
    public TextPieceHeartOrBuilder getHeartvalueOrBuilder() {
      if (this.heartvalueBuilder_ != null)
        return (TextPieceHeartOrBuilder)this.heartvalueBuilder_.getMessageOrBuilder(); 
      return (this.heartvalue_ == null) ? TextPieceHeart.getDefaultInstance() : this.heartvalue_;
    }
    
    private SingleFieldBuilderV3<TextPieceHeart, TextPieceHeart.Builder, TextPieceHeartOrBuilder> getHeartvalueFieldBuilder() {
      if (this.heartvalueBuilder_ == null) {
        this.heartvalueBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getHeartvalue(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.heartvalue_ = null;
      } 
      return this.heartvalueBuilder_;
    }
    
    public boolean hasPatternrefvalue() {
      return ((this.bitField0_ & 0x80) != 0);
    }
    
    public TextPiecePatternRef getPatternrefvalue() {
      if (this.patternrefvalueBuilder_ == null)
        return (this.patternrefvalue_ == null) ? TextPiecePatternRef.getDefaultInstance() : this.patternrefvalue_; 
      return (TextPiecePatternRef)this.patternrefvalueBuilder_.getMessage();
    }
    
    public Builder setPatternrefvalue(TextPiecePatternRef value) {
      if (this.patternrefvalueBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        this.patternrefvalue_ = value;
      } else {
        this.patternrefvalueBuilder_.setMessage((AbstractMessage)value);
      } 
      this.bitField0_ |= 0x80;
      onChanged();
      return this;
    }
    
    public Builder setPatternrefvalue(TextPiecePatternRef.Builder builderForValue) {
      if (this.patternrefvalueBuilder_ == null) {
        this.patternrefvalue_ = builderForValue.build();
      } else {
        this.patternrefvalueBuilder_.setMessage((AbstractMessage)builderForValue.build());
      } 
      this.bitField0_ |= 0x80;
      onChanged();
      return this;
    }
    
    public Builder mergePatternrefvalue(TextPiecePatternRef value) {
      if (this.patternrefvalueBuilder_ == null) {
        if ((this.bitField0_ & 0x80) != 0 && this.patternrefvalue_ != null && this.patternrefvalue_ != TextPiecePatternRef.getDefaultInstance()) {
          getPatternrefvalueBuilder().mergeFrom(value);
        } else {
          this.patternrefvalue_ = value;
        } 
      } else {
        this.patternrefvalueBuilder_.mergeFrom((AbstractMessage)value);
      } 
      if (this.patternrefvalue_ != null) {
        this.bitField0_ |= 0x80;
        onChanged();
      } 
      return this;
    }
    
    public Builder clearPatternrefvalue() {
      this.bitField0_ &= 0xFFFFFF7F;
      this.patternrefvalue_ = null;
      if (this.patternrefvalueBuilder_ != null) {
        this.patternrefvalueBuilder_.dispose();
        this.patternrefvalueBuilder_ = null;
      } 
      onChanged();
      return this;
    }
    
    public TextPiecePatternRef.Builder getPatternrefvalueBuilder() {
      this.bitField0_ |= 0x80;
      onChanged();
      return (TextPiecePatternRef.Builder)getPatternrefvalueFieldBuilder().getBuilder();
    }
    
    public TextPiecePatternRefOrBuilder getPatternrefvalueOrBuilder() {
      if (this.patternrefvalueBuilder_ != null)
        return (TextPiecePatternRefOrBuilder)this.patternrefvalueBuilder_.getMessageOrBuilder(); 
      return (this.patternrefvalue_ == null) ? TextPiecePatternRef.getDefaultInstance() : this.patternrefvalue_;
    }
    
    private SingleFieldBuilderV3<TextPiecePatternRef, TextPiecePatternRef.Builder, TextPiecePatternRefOrBuilder> getPatternrefvalueFieldBuilder() {
      if (this.patternrefvalueBuilder_ == null) {
        this.patternrefvalueBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getPatternrefvalue(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.patternrefvalue_ = null;
      } 
      return this.patternrefvalueBuilder_;
    }
    
    public boolean hasImagevalue() {
      return ((this.bitField0_ & 0x100) != 0);
    }
    
    public TextPieceImage getImagevalue() {
      if (this.imagevalueBuilder_ == null)
        return (this.imagevalue_ == null) ? TextPieceImage.getDefaultInstance() : this.imagevalue_; 
      return (TextPieceImage)this.imagevalueBuilder_.getMessage();
    }
    
    public Builder setImagevalue(TextPieceImage value) {
      if (this.imagevalueBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        this.imagevalue_ = value;
      } else {
        this.imagevalueBuilder_.setMessage((AbstractMessage)value);
      } 
      this.bitField0_ |= 0x100;
      onChanged();
      return this;
    }
    
    public Builder setImagevalue(TextPieceImage.Builder builderForValue) {
      if (this.imagevalueBuilder_ == null) {
        this.imagevalue_ = builderForValue.build();
      } else {
        this.imagevalueBuilder_.setMessage((AbstractMessage)builderForValue.build());
      } 
      this.bitField0_ |= 0x100;
      onChanged();
      return this;
    }
    
    public Builder mergeImagevalue(TextPieceImage value) {
      if (this.imagevalueBuilder_ == null) {
        if ((this.bitField0_ & 0x100) != 0 && this.imagevalue_ != null && this.imagevalue_ != TextPieceImage.getDefaultInstance()) {
          getImagevalueBuilder().mergeFrom(value);
        } else {
          this.imagevalue_ = value;
        } 
      } else {
        this.imagevalueBuilder_.mergeFrom((AbstractMessage)value);
      } 
      if (this.imagevalue_ != null) {
        this.bitField0_ |= 0x100;
        onChanged();
      } 
      return this;
    }
    
    public Builder clearImagevalue() {
      this.bitField0_ &= 0xFFFFFEFF;
      this.imagevalue_ = null;
      if (this.imagevalueBuilder_ != null) {
        this.imagevalueBuilder_.dispose();
        this.imagevalueBuilder_ = null;
      } 
      onChanged();
      return this;
    }
    
    public TextPieceImage.Builder getImagevalueBuilder() {
      this.bitField0_ |= 0x100;
      onChanged();
      return (TextPieceImage.Builder)getImagevalueFieldBuilder().getBuilder();
    }
    
    public TextPieceImageOrBuilder getImagevalueOrBuilder() {
      if (this.imagevalueBuilder_ != null)
        return (TextPieceImageOrBuilder)this.imagevalueBuilder_.getMessageOrBuilder(); 
      return (this.imagevalue_ == null) ? TextPieceImage.getDefaultInstance() : this.imagevalue_;
    }
    
    private SingleFieldBuilderV3<TextPieceImage, TextPieceImage.Builder, TextPieceImageOrBuilder> getImagevalueFieldBuilder() {
      if (this.imagevalueBuilder_ == null) {
        this.imagevalueBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getImagevalue(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.imagevalue_ = null;
      } 
      return this.imagevalueBuilder_;
    }
    
    public String getSchemaKey() {
      Object ref = this.schemaKey_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.schemaKey_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getSchemaKeyBytes() {
      Object ref = this.schemaKey_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.schemaKey_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setSchemaKey(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.schemaKey_ = value;
      this.bitField0_ |= 0x200;
      onChanged();
      return this;
    }
    
    public Builder clearSchemaKey() {
      this.schemaKey_ = TextPiece.getDefaultInstance().getSchemaKey();
      this.bitField0_ &= 0xFFFFFDFF;
      onChanged();
      return this;
    }
    
    public Builder setSchemaKeyBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      TextPiece.checkByteStringIsUtf8(value);
      this.schemaKey_ = value;
      this.bitField0_ |= 0x200;
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
  
  private static final TextPiece DEFAULT_INSTANCE = new TextPiece();
  
  public static TextPiece getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }
  
  private static final Parser<TextPiece> PARSER = (Parser<TextPiece>)new AbstractParser<TextPiece>() {
      public TextPiece parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        TextPiece.Builder builder = TextPiece.newBuilder();
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
  
  public static Parser<TextPiece> parser() {
    return PARSER;
  }
  
  public Parser<TextPiece> getParserForType() {
    return PARSER;
  }
  
  public TextPiece getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }
}
