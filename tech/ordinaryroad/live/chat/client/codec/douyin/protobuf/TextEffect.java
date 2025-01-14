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

public final class TextEffect extends GeneratedMessageV3 implements TextEffectOrBuilder {
  private static final long serialVersionUID = 0L;
  
  private int bitField0_;
  
  public static final int PORTRAIT_FIELD_NUMBER = 1;
  
  private TextEffectDetail portrait_;
  
  public static final int LANDSCAPE_FIELD_NUMBER = 2;
  
  private TextEffectDetail landscape_;
  
  private byte memoizedIsInitialized;
  
  private TextEffect(GeneratedMessageV3.Builder<?> builder) {
    super(builder);
    this.memoizedIsInitialized = -1;
  }
  
  private TextEffect() {
    this.memoizedIsInitialized = -1;
  }
  
  protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
    return new TextEffect();
  }
  
  public static final Descriptors.Descriptor getDescriptor() {
    return Douyin.internal_static_TextEffect_descriptor;
  }
  
  protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
    return Douyin.internal_static_TextEffect_fieldAccessorTable.ensureFieldAccessorsInitialized(TextEffect.class, Builder.class);
  }
  
  public boolean hasPortrait() {
    return ((this.bitField0_ & 0x1) != 0);
  }
  
  public TextEffectDetail getPortrait() {
    return (this.portrait_ == null) ? TextEffectDetail.getDefaultInstance() : this.portrait_;
  }
  
  public TextEffectDetailOrBuilder getPortraitOrBuilder() {
    return (this.portrait_ == null) ? TextEffectDetail.getDefaultInstance() : this.portrait_;
  }
  
  public boolean hasLandscape() {
    return ((this.bitField0_ & 0x2) != 0);
  }
  
  public TextEffectDetail getLandscape() {
    return (this.landscape_ == null) ? TextEffectDetail.getDefaultInstance() : this.landscape_;
  }
  
  public TextEffectDetailOrBuilder getLandscapeOrBuilder() {
    return (this.landscape_ == null) ? TextEffectDetail.getDefaultInstance() : this.landscape_;
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
      output.writeMessage(1, (MessageLite)getPortrait()); 
    if ((this.bitField0_ & 0x2) != 0)
      output.writeMessage(2, (MessageLite)getLandscape()); 
    getUnknownFields().writeTo(output);
  }
  
  public int getSerializedSize() {
    int size = this.memoizedSize;
    if (size != -1)
      return size; 
    size = 0;
    if ((this.bitField0_ & 0x1) != 0)
      size += 
        CodedOutputStream.computeMessageSize(1, (MessageLite)getPortrait()); 
    if ((this.bitField0_ & 0x2) != 0)
      size += 
        CodedOutputStream.computeMessageSize(2, (MessageLite)getLandscape()); 
    size += getUnknownFields().getSerializedSize();
    this.memoizedSize = size;
    return size;
  }
  
  public boolean equals(Object obj) {
    if (obj == this)
      return true; 
    if (!(obj instanceof TextEffect))
      return super.equals(obj); 
    TextEffect other = (TextEffect)obj;
    if (hasPortrait() != other.hasPortrait())
      return false; 
    if (hasPortrait() && 
      
      !getPortrait().equals(other.getPortrait()))
      return false; 
    if (hasLandscape() != other.hasLandscape())
      return false; 
    if (hasLandscape() && 
      
      !getLandscape().equals(other.getLandscape()))
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
    if (hasPortrait()) {
      hash = 37 * hash + 1;
      hash = 53 * hash + getPortrait().hashCode();
    } 
    if (hasLandscape()) {
      hash = 37 * hash + 2;
      hash = 53 * hash + getLandscape().hashCode();
    } 
    hash = 29 * hash + getUnknownFields().hashCode();
    this.memoizedHashCode = hash;
    return hash;
  }
  
  public static TextEffect parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
    return (TextEffect)PARSER.parseFrom(data);
  }
  
  public static TextEffect parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (TextEffect)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static TextEffect parseFrom(ByteString data) throws InvalidProtocolBufferException {
    return (TextEffect)PARSER.parseFrom(data);
  }
  
  public static TextEffect parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (TextEffect)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static TextEffect parseFrom(byte[] data) throws InvalidProtocolBufferException {
    return (TextEffect)PARSER.parseFrom(data);
  }
  
  public static TextEffect parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (TextEffect)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static TextEffect parseFrom(InputStream input) throws IOException {
    return 
      (TextEffect)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static TextEffect parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (TextEffect)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static TextEffect parseDelimitedFrom(InputStream input) throws IOException {
    return 
      (TextEffect)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
  }
  
  public static TextEffect parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (TextEffect)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static TextEffect parseFrom(CodedInputStream input) throws IOException {
    return 
      (TextEffect)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static TextEffect parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (TextEffect)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public Builder newBuilderForType() {
    return newBuilder();
  }
  
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  
  public static Builder newBuilder(TextEffect prototype) {
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
  
  public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements TextEffectOrBuilder {
    private int bitField0_;
    
    private TextEffectDetail portrait_;
    
    private SingleFieldBuilderV3<TextEffectDetail, TextEffectDetail.Builder, TextEffectDetailOrBuilder> portraitBuilder_;
    
    private TextEffectDetail landscape_;
    
    private SingleFieldBuilderV3<TextEffectDetail, TextEffectDetail.Builder, TextEffectDetailOrBuilder> landscapeBuilder_;
    
    public static final Descriptors.Descriptor getDescriptor() {
      return Douyin.internal_static_TextEffect_descriptor;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return Douyin.internal_static_TextEffect_fieldAccessorTable
        .ensureFieldAccessorsInitialized(TextEffect.class, Builder.class);
    }
    
    private Builder() {
      maybeForceBuilderInitialization();
    }
    
    private Builder(GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    
    private void maybeForceBuilderInitialization() {
      if (TextEffect.alwaysUseFieldBuilders) {
        getPortraitFieldBuilder();
        getLandscapeFieldBuilder();
      } 
    }
    
    public Builder clear() {
      super.clear();
      this.bitField0_ = 0;
      this.portrait_ = null;
      if (this.portraitBuilder_ != null) {
        this.portraitBuilder_.dispose();
        this.portraitBuilder_ = null;
      } 
      this.landscape_ = null;
      if (this.landscapeBuilder_ != null) {
        this.landscapeBuilder_.dispose();
        this.landscapeBuilder_ = null;
      } 
      return this;
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
      return Douyin.internal_static_TextEffect_descriptor;
    }
    
    public TextEffect getDefaultInstanceForType() {
      return TextEffect.getDefaultInstance();
    }
    
    public TextEffect build() {
      TextEffect result = buildPartial();
      if (!result.isInitialized())
        throw newUninitializedMessageException(result); 
      return result;
    }
    
    public TextEffect buildPartial() {
      TextEffect result = new TextEffect(this);
      if (this.bitField0_ != 0)
        buildPartial0(result); 
      onBuilt();
      return result;
    }
    
    private void buildPartial0(TextEffect result) {
      int from_bitField0_ = this.bitField0_;
      int to_bitField0_ = 0;
      if ((from_bitField0_ & 0x1) != 0) {
        result.portrait_ = (this.portraitBuilder_ == null) ? 
          this.portrait_ : 
          (TextEffectDetail)this.portraitBuilder_.build();
        to_bitField0_ |= 0x1;
      } 
      if ((from_bitField0_ & 0x2) != 0) {
        result.landscape_ = (this.landscapeBuilder_ == null) ? 
          this.landscape_ : 
          (TextEffectDetail)this.landscapeBuilder_.build();
        to_bitField0_ |= 0x2;
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
      if (other instanceof TextEffect)
        return mergeFrom((TextEffect)other); 
      super.mergeFrom(other);
      return this;
    }
    
    public Builder mergeFrom(TextEffect other) {
      if (other == TextEffect.getDefaultInstance())
        return this; 
      if (other.hasPortrait())
        mergePortrait(other.getPortrait()); 
      if (other.hasLandscape())
        mergeLandscape(other.getLandscape()); 
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
                  getPortraitFieldBuilder().getBuilder(), extensionRegistry);
              this.bitField0_ |= 0x1;
              continue;
            case 18:
              input.readMessage((MessageLite.Builder)
                  getLandscapeFieldBuilder().getBuilder(), extensionRegistry);
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
    
    public boolean hasPortrait() {
      return ((this.bitField0_ & 0x1) != 0);
    }
    
    public TextEffectDetail getPortrait() {
      if (this.portraitBuilder_ == null)
        return (this.portrait_ == null) ? TextEffectDetail.getDefaultInstance() : this.portrait_; 
      return (TextEffectDetail)this.portraitBuilder_.getMessage();
    }
    
    public Builder setPortrait(TextEffectDetail value) {
      if (this.portraitBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        this.portrait_ = value;
      } else {
        this.portraitBuilder_.setMessage((AbstractMessage)value);
      } 
      this.bitField0_ |= 0x1;
      onChanged();
      return this;
    }
    
    public Builder setPortrait(TextEffectDetail.Builder builderForValue) {
      if (this.portraitBuilder_ == null) {
        this.portrait_ = builderForValue.build();
      } else {
        this.portraitBuilder_.setMessage((AbstractMessage)builderForValue.build());
      } 
      this.bitField0_ |= 0x1;
      onChanged();
      return this;
    }
    
    public Builder mergePortrait(TextEffectDetail value) {
      if (this.portraitBuilder_ == null) {
        if ((this.bitField0_ & 0x1) != 0 && this.portrait_ != null && this.portrait_ != 
          
          TextEffectDetail.getDefaultInstance()) {
          getPortraitBuilder().mergeFrom(value);
        } else {
          this.portrait_ = value;
        } 
      } else {
        this.portraitBuilder_.mergeFrom((AbstractMessage)value);
      } 
      if (this.portrait_ != null) {
        this.bitField0_ |= 0x1;
        onChanged();
      } 
      return this;
    }
    
    public Builder clearPortrait() {
      this.bitField0_ &= 0xFFFFFFFE;
      this.portrait_ = null;
      if (this.portraitBuilder_ != null) {
        this.portraitBuilder_.dispose();
        this.portraitBuilder_ = null;
      } 
      onChanged();
      return this;
    }
    
    public TextEffectDetail.Builder getPortraitBuilder() {
      this.bitField0_ |= 0x1;
      onChanged();
      return (TextEffectDetail.Builder)getPortraitFieldBuilder().getBuilder();
    }
    
    public TextEffectDetailOrBuilder getPortraitOrBuilder() {
      if (this.portraitBuilder_ != null)
        return (TextEffectDetailOrBuilder)this.portraitBuilder_.getMessageOrBuilder(); 
      return (this.portrait_ == null) ? 
        TextEffectDetail.getDefaultInstance() : this.portrait_;
    }
    
    private SingleFieldBuilderV3<TextEffectDetail, TextEffectDetail.Builder, TextEffectDetailOrBuilder> getPortraitFieldBuilder() {
      if (this.portraitBuilder_ == null) {
        this
          
          .portraitBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getPortrait(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.portrait_ = null;
      } 
      return this.portraitBuilder_;
    }
    
    public boolean hasLandscape() {
      return ((this.bitField0_ & 0x2) != 0);
    }
    
    public TextEffectDetail getLandscape() {
      if (this.landscapeBuilder_ == null)
        return (this.landscape_ == null) ? TextEffectDetail.getDefaultInstance() : this.landscape_; 
      return (TextEffectDetail)this.landscapeBuilder_.getMessage();
    }
    
    public Builder setLandscape(TextEffectDetail value) {
      if (this.landscapeBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        this.landscape_ = value;
      } else {
        this.landscapeBuilder_.setMessage((AbstractMessage)value);
      } 
      this.bitField0_ |= 0x2;
      onChanged();
      return this;
    }
    
    public Builder setLandscape(TextEffectDetail.Builder builderForValue) {
      if (this.landscapeBuilder_ == null) {
        this.landscape_ = builderForValue.build();
      } else {
        this.landscapeBuilder_.setMessage((AbstractMessage)builderForValue.build());
      } 
      this.bitField0_ |= 0x2;
      onChanged();
      return this;
    }
    
    public Builder mergeLandscape(TextEffectDetail value) {
      if (this.landscapeBuilder_ == null) {
        if ((this.bitField0_ & 0x2) != 0 && this.landscape_ != null && this.landscape_ != 
          
          TextEffectDetail.getDefaultInstance()) {
          getLandscapeBuilder().mergeFrom(value);
        } else {
          this.landscape_ = value;
        } 
      } else {
        this.landscapeBuilder_.mergeFrom((AbstractMessage)value);
      } 
      if (this.landscape_ != null) {
        this.bitField0_ |= 0x2;
        onChanged();
      } 
      return this;
    }
    
    public Builder clearLandscape() {
      this.bitField0_ &= 0xFFFFFFFD;
      this.landscape_ = null;
      if (this.landscapeBuilder_ != null) {
        this.landscapeBuilder_.dispose();
        this.landscapeBuilder_ = null;
      } 
      onChanged();
      return this;
    }
    
    public TextEffectDetail.Builder getLandscapeBuilder() {
      this.bitField0_ |= 0x2;
      onChanged();
      return (TextEffectDetail.Builder)getLandscapeFieldBuilder().getBuilder();
    }
    
    public TextEffectDetailOrBuilder getLandscapeOrBuilder() {
      if (this.landscapeBuilder_ != null)
        return (TextEffectDetailOrBuilder)this.landscapeBuilder_.getMessageOrBuilder(); 
      return (this.landscape_ == null) ? 
        TextEffectDetail.getDefaultInstance() : this.landscape_;
    }
    
    private SingleFieldBuilderV3<TextEffectDetail, TextEffectDetail.Builder, TextEffectDetailOrBuilder> getLandscapeFieldBuilder() {
      if (this.landscapeBuilder_ == null) {
        this
          
          .landscapeBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getLandscape(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.landscape_ = null;
      } 
      return this.landscapeBuilder_;
    }
    
    public final Builder setUnknownFields(UnknownFieldSet unknownFields) {
      return (Builder)super.setUnknownFields(unknownFields);
    }
    
    public final Builder mergeUnknownFields(UnknownFieldSet unknownFields) {
      return (Builder)super.mergeUnknownFields(unknownFields);
    }
  }
  
  private static final TextEffect DEFAULT_INSTANCE = new TextEffect();
  
  public static TextEffect getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }
  
  private static final Parser<TextEffect> PARSER = (Parser<TextEffect>)new AbstractParser<TextEffect>() {
      public TextEffect parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        TextEffect.Builder builder = TextEffect.newBuilder();
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
  
  public static Parser<TextEffect> parser() {
    return PARSER;
  }
  
  public Parser<TextEffect> getParserForType() {
    return PARSER;
  }
  
  public TextEffect getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }
}
