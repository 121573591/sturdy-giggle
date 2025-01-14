package tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf;

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
  
  public static final int USERVALUE_FIELD_NUMBER = 21;
  
  private TextPieceUser uservalue_;
  
  public static final int GIFTVALUE_FIELD_NUMBER = 22;
  
  private TextPieceGift giftvalue_;
  
  private byte memoizedIsInitialized;
  
  private TextPiece(GeneratedMessageV3.Builder<?> builder) {
    super(builder);
    this.type_ = 0;
    this.memoizedIsInitialized = -1;
  }
  
  private TextPiece() {
    this.type_ = 0;
    this.memoizedIsInitialized = -1;
  }
  
  protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
    return new TextPiece();
  }
  
  public static final Descriptors.Descriptor getDescriptor() {
    return Tiktok.internal_static_TextPiece_descriptor;
  }
  
  protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
    return Tiktok.internal_static_TextPiece_fieldAccessorTable.ensureFieldAccessorsInitialized(TextPiece.class, Builder.class);
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
    if ((this.bitField0_ & 0x2) != 0)
      output.writeMessage(21, (MessageLite)getUservalue()); 
    if ((this.bitField0_ & 0x4) != 0)
      output.writeMessage(22, (MessageLite)getGiftvalue()); 
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
    if ((this.bitField0_ & 0x2) != 0)
      size += 
        CodedOutputStream.computeMessageSize(21, (MessageLite)getUservalue()); 
    if ((this.bitField0_ & 0x4) != 0)
      size += 
        CodedOutputStream.computeMessageSize(22, (MessageLite)getGiftvalue()); 
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
    if (hasUservalue()) {
      hash = 37 * hash + 21;
      hash = 53 * hash + getUservalue().hashCode();
    } 
    if (hasGiftvalue()) {
      hash = 37 * hash + 22;
      hash = 53 * hash + getGiftvalue().hashCode();
    } 
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
    
    private TextPieceUser uservalue_;
    
    private SingleFieldBuilderV3<TextPieceUser, TextPieceUser.Builder, TextPieceUserOrBuilder> uservalueBuilder_;
    
    private TextPieceGift giftvalue_;
    
    private SingleFieldBuilderV3<TextPieceGift, TextPieceGift.Builder, TextPieceGiftOrBuilder> giftvalueBuilder_;
    
    public static final Descriptors.Descriptor getDescriptor() {
      return Tiktok.internal_static_TextPiece_descriptor;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return Tiktok.internal_static_TextPiece_fieldAccessorTable
        .ensureFieldAccessorsInitialized(TextPiece.class, Builder.class);
    }
    
    private Builder() {
      maybeForceBuilderInitialization();
    }
    
    private Builder(GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    
    private void maybeForceBuilderInitialization() {
      if (TextPiece.alwaysUseFieldBuilders) {
        getFormatFieldBuilder();
        getUservalueFieldBuilder();
        getGiftvalueFieldBuilder();
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
      return this;
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
      return Tiktok.internal_static_TextPiece_descriptor;
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
        result.format_ = (this.formatBuilder_ == null) ? 
          this.format_ : 
          (TextFormat)this.formatBuilder_.build();
        to_bitField0_ |= 0x1;
      } 
      if ((from_bitField0_ & 0x4) != 0) {
        result.uservalue_ = (this.uservalueBuilder_ == null) ? 
          this.uservalue_ : 
          (TextPieceUser)this.uservalueBuilder_.build();
        to_bitField0_ |= 0x2;
      } 
      if ((from_bitField0_ & 0x8) != 0) {
        result.giftvalue_ = (this.giftvalueBuilder_ == null) ? 
          this.giftvalue_ : 
          (TextPieceGift)this.giftvalueBuilder_.build();
        to_bitField0_ |= 0x4;
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
      if (other.hasUservalue())
        mergeUservalue(other.getUservalue()); 
      if (other.hasGiftvalue())
        mergeGiftvalue(other.getGiftvalue()); 
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
              input.readMessage((MessageLite.Builder)
                  getFormatFieldBuilder().getBuilder(), extensionRegistry);
              this.bitField0_ |= 0x2;
              continue;
            case 170:
              input.readMessage((MessageLite.Builder)
                  getUservalueFieldBuilder().getBuilder(), extensionRegistry);
              this.bitField0_ |= 0x4;
              continue;
            case 178:
              input.readMessage((MessageLite.Builder)
                  getGiftvalueFieldBuilder().getBuilder(), extensionRegistry);
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
        if ((this.bitField0_ & 0x2) != 0 && this.format_ != null && this.format_ != 
          
          TextFormat.getDefaultInstance()) {
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
      return (this.format_ == null) ? 
        TextFormat.getDefaultInstance() : this.format_;
    }
    
    private SingleFieldBuilderV3<TextFormat, TextFormat.Builder, TextFormatOrBuilder> getFormatFieldBuilder() {
      if (this.formatBuilder_ == null) {
        this
          
          .formatBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getFormat(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.format_ = null;
      } 
      return this.formatBuilder_;
    }
    
    public boolean hasUservalue() {
      return ((this.bitField0_ & 0x4) != 0);
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
      this.bitField0_ |= 0x4;
      onChanged();
      return this;
    }
    
    public Builder setUservalue(TextPieceUser.Builder builderForValue) {
      if (this.uservalueBuilder_ == null) {
        this.uservalue_ = builderForValue.build();
      } else {
        this.uservalueBuilder_.setMessage((AbstractMessage)builderForValue.build());
      } 
      this.bitField0_ |= 0x4;
      onChanged();
      return this;
    }
    
    public Builder mergeUservalue(TextPieceUser value) {
      if (this.uservalueBuilder_ == null) {
        if ((this.bitField0_ & 0x4) != 0 && this.uservalue_ != null && this.uservalue_ != 
          
          TextPieceUser.getDefaultInstance()) {
          getUservalueBuilder().mergeFrom(value);
        } else {
          this.uservalue_ = value;
        } 
      } else {
        this.uservalueBuilder_.mergeFrom((AbstractMessage)value);
      } 
      if (this.uservalue_ != null) {
        this.bitField0_ |= 0x4;
        onChanged();
      } 
      return this;
    }
    
    public Builder clearUservalue() {
      this.bitField0_ &= 0xFFFFFFFB;
      this.uservalue_ = null;
      if (this.uservalueBuilder_ != null) {
        this.uservalueBuilder_.dispose();
        this.uservalueBuilder_ = null;
      } 
      onChanged();
      return this;
    }
    
    public TextPieceUser.Builder getUservalueBuilder() {
      this.bitField0_ |= 0x4;
      onChanged();
      return (TextPieceUser.Builder)getUservalueFieldBuilder().getBuilder();
    }
    
    public TextPieceUserOrBuilder getUservalueOrBuilder() {
      if (this.uservalueBuilder_ != null)
        return (TextPieceUserOrBuilder)this.uservalueBuilder_.getMessageOrBuilder(); 
      return (this.uservalue_ == null) ? 
        TextPieceUser.getDefaultInstance() : this.uservalue_;
    }
    
    private SingleFieldBuilderV3<TextPieceUser, TextPieceUser.Builder, TextPieceUserOrBuilder> getUservalueFieldBuilder() {
      if (this.uservalueBuilder_ == null) {
        this
          
          .uservalueBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getUservalue(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.uservalue_ = null;
      } 
      return this.uservalueBuilder_;
    }
    
    public boolean hasGiftvalue() {
      return ((this.bitField0_ & 0x8) != 0);
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
      this.bitField0_ |= 0x8;
      onChanged();
      return this;
    }
    
    public Builder setGiftvalue(TextPieceGift.Builder builderForValue) {
      if (this.giftvalueBuilder_ == null) {
        this.giftvalue_ = builderForValue.build();
      } else {
        this.giftvalueBuilder_.setMessage((AbstractMessage)builderForValue.build());
      } 
      this.bitField0_ |= 0x8;
      onChanged();
      return this;
    }
    
    public Builder mergeGiftvalue(TextPieceGift value) {
      if (this.giftvalueBuilder_ == null) {
        if ((this.bitField0_ & 0x8) != 0 && this.giftvalue_ != null && this.giftvalue_ != 
          
          TextPieceGift.getDefaultInstance()) {
          getGiftvalueBuilder().mergeFrom(value);
        } else {
          this.giftvalue_ = value;
        } 
      } else {
        this.giftvalueBuilder_.mergeFrom((AbstractMessage)value);
      } 
      if (this.giftvalue_ != null) {
        this.bitField0_ |= 0x8;
        onChanged();
      } 
      return this;
    }
    
    public Builder clearGiftvalue() {
      this.bitField0_ &= 0xFFFFFFF7;
      this.giftvalue_ = null;
      if (this.giftvalueBuilder_ != null) {
        this.giftvalueBuilder_.dispose();
        this.giftvalueBuilder_ = null;
      } 
      onChanged();
      return this;
    }
    
    public TextPieceGift.Builder getGiftvalueBuilder() {
      this.bitField0_ |= 0x8;
      onChanged();
      return (TextPieceGift.Builder)getGiftvalueFieldBuilder().getBuilder();
    }
    
    public TextPieceGiftOrBuilder getGiftvalueOrBuilder() {
      if (this.giftvalueBuilder_ != null)
        return (TextPieceGiftOrBuilder)this.giftvalueBuilder_.getMessageOrBuilder(); 
      return (this.giftvalue_ == null) ? 
        TextPieceGift.getDefaultInstance() : this.giftvalue_;
    }
    
    private SingleFieldBuilderV3<TextPieceGift, TextPieceGift.Builder, TextPieceGiftOrBuilder> getGiftvalueFieldBuilder() {
      if (this.giftvalueBuilder_ == null) {
        this
          
          .giftvalueBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getGiftvalue(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.giftvalue_ = null;
      } 
      return this.giftvalueBuilder_;
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
