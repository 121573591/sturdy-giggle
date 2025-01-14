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
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class TextPieceGift extends GeneratedMessageV3 implements TextPieceGiftOrBuilder {
  private static final long serialVersionUID = 0L;
  
  private int bitField0_;
  
  public static final int GIFTID_FIELD_NUMBER = 1;
  
  private long giftId_;
  
  public static final int NAMEREF_FIELD_NUMBER = 2;
  
  private PatternRef nameRef_;
  
  private byte memoizedIsInitialized;
  
  private TextPieceGift(GeneratedMessageV3.Builder<?> builder) {
    super(builder);
    this.giftId_ = 0L;
    this.memoizedIsInitialized = -1;
  }
  
  private TextPieceGift() {
    this.giftId_ = 0L;
    this.memoizedIsInitialized = -1;
  }
  
  protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
    return new TextPieceGift();
  }
  
  public static final Descriptors.Descriptor getDescriptor() {
    return Douyin.internal_static_TextPieceGift_descriptor;
  }
  
  protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
    return Douyin.internal_static_TextPieceGift_fieldAccessorTable.ensureFieldAccessorsInitialized(TextPieceGift.class, Builder.class);
  }
  
  public long getGiftId() {
    return this.giftId_;
  }
  
  public boolean hasNameRef() {
    return ((this.bitField0_ & 0x1) != 0);
  }
  
  public PatternRef getNameRef() {
    return (this.nameRef_ == null) ? PatternRef.getDefaultInstance() : this.nameRef_;
  }
  
  public PatternRefOrBuilder getNameRefOrBuilder() {
    return (this.nameRef_ == null) ? PatternRef.getDefaultInstance() : this.nameRef_;
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
    if (this.giftId_ != 0L)
      output.writeUInt64(1, this.giftId_); 
    if ((this.bitField0_ & 0x1) != 0)
      output.writeMessage(2, (MessageLite)getNameRef()); 
    getUnknownFields().writeTo(output);
  }
  
  public int getSerializedSize() {
    int size = this.memoizedSize;
    if (size != -1)
      return size; 
    size = 0;
    if (this.giftId_ != 0L)
      size += 
        CodedOutputStream.computeUInt64Size(1, this.giftId_); 
    if ((this.bitField0_ & 0x1) != 0)
      size += 
        CodedOutputStream.computeMessageSize(2, (MessageLite)getNameRef()); 
    size += getUnknownFields().getSerializedSize();
    this.memoizedSize = size;
    return size;
  }
  
  public boolean equals(Object obj) {
    if (obj == this)
      return true; 
    if (!(obj instanceof TextPieceGift))
      return super.equals(obj); 
    TextPieceGift other = (TextPieceGift)obj;
    if (getGiftId() != other
      .getGiftId())
      return false; 
    if (hasNameRef() != other.hasNameRef())
      return false; 
    if (hasNameRef() && 
      
      !getNameRef().equals(other.getNameRef()))
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
    hash = 53 * hash + Internal.hashLong(
        getGiftId());
    if (hasNameRef()) {
      hash = 37 * hash + 2;
      hash = 53 * hash + getNameRef().hashCode();
    } 
    hash = 29 * hash + getUnknownFields().hashCode();
    this.memoizedHashCode = hash;
    return hash;
  }
  
  public static TextPieceGift parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
    return (TextPieceGift)PARSER.parseFrom(data);
  }
  
  public static TextPieceGift parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (TextPieceGift)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static TextPieceGift parseFrom(ByteString data) throws InvalidProtocolBufferException {
    return (TextPieceGift)PARSER.parseFrom(data);
  }
  
  public static TextPieceGift parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (TextPieceGift)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static TextPieceGift parseFrom(byte[] data) throws InvalidProtocolBufferException {
    return (TextPieceGift)PARSER.parseFrom(data);
  }
  
  public static TextPieceGift parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (TextPieceGift)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static TextPieceGift parseFrom(InputStream input) throws IOException {
    return 
      (TextPieceGift)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static TextPieceGift parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (TextPieceGift)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static TextPieceGift parseDelimitedFrom(InputStream input) throws IOException {
    return 
      (TextPieceGift)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
  }
  
  public static TextPieceGift parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (TextPieceGift)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static TextPieceGift parseFrom(CodedInputStream input) throws IOException {
    return 
      (TextPieceGift)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static TextPieceGift parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (TextPieceGift)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public Builder newBuilderForType() {
    return newBuilder();
  }
  
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  
  public static Builder newBuilder(TextPieceGift prototype) {
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
  
  public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements TextPieceGiftOrBuilder {
    private int bitField0_;
    
    private long giftId_;
    
    private PatternRef nameRef_;
    
    private SingleFieldBuilderV3<PatternRef, PatternRef.Builder, PatternRefOrBuilder> nameRefBuilder_;
    
    public static final Descriptors.Descriptor getDescriptor() {
      return Douyin.internal_static_TextPieceGift_descriptor;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return Douyin.internal_static_TextPieceGift_fieldAccessorTable
        .ensureFieldAccessorsInitialized(TextPieceGift.class, Builder.class);
    }
    
    private Builder() {
      maybeForceBuilderInitialization();
    }
    
    private Builder(GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    
    private void maybeForceBuilderInitialization() {
      if (TextPieceGift.alwaysUseFieldBuilders)
        getNameRefFieldBuilder(); 
    }
    
    public Builder clear() {
      super.clear();
      this.bitField0_ = 0;
      this.giftId_ = 0L;
      this.nameRef_ = null;
      if (this.nameRefBuilder_ != null) {
        this.nameRefBuilder_.dispose();
        this.nameRefBuilder_ = null;
      } 
      return this;
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
      return Douyin.internal_static_TextPieceGift_descriptor;
    }
    
    public TextPieceGift getDefaultInstanceForType() {
      return TextPieceGift.getDefaultInstance();
    }
    
    public TextPieceGift build() {
      TextPieceGift result = buildPartial();
      if (!result.isInitialized())
        throw newUninitializedMessageException(result); 
      return result;
    }
    
    public TextPieceGift buildPartial() {
      TextPieceGift result = new TextPieceGift(this);
      if (this.bitField0_ != 0)
        buildPartial0(result); 
      onBuilt();
      return result;
    }
    
    private void buildPartial0(TextPieceGift result) {
      int from_bitField0_ = this.bitField0_;
      if ((from_bitField0_ & 0x1) != 0)
        result.giftId_ = this.giftId_; 
      int to_bitField0_ = 0;
      if ((from_bitField0_ & 0x2) != 0) {
        result.nameRef_ = (this.nameRefBuilder_ == null) ? 
          this.nameRef_ : 
          (PatternRef)this.nameRefBuilder_.build();
        to_bitField0_ |= 0x1;
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
      if (other instanceof TextPieceGift)
        return mergeFrom((TextPieceGift)other); 
      super.mergeFrom(other);
      return this;
    }
    
    public Builder mergeFrom(TextPieceGift other) {
      if (other == TextPieceGift.getDefaultInstance())
        return this; 
      if (other.getGiftId() != 0L)
        setGiftId(other.getGiftId()); 
      if (other.hasNameRef())
        mergeNameRef(other.getNameRef()); 
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
              this.giftId_ = input.readUInt64();
              this.bitField0_ |= 0x1;
              continue;
            case 18:
              input.readMessage((MessageLite.Builder)
                  getNameRefFieldBuilder().getBuilder(), extensionRegistry);
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
    
    public long getGiftId() {
      return this.giftId_;
    }
    
    public Builder setGiftId(long value) {
      this.giftId_ = value;
      this.bitField0_ |= 0x1;
      onChanged();
      return this;
    }
    
    public Builder clearGiftId() {
      this.bitField0_ &= 0xFFFFFFFE;
      this.giftId_ = 0L;
      onChanged();
      return this;
    }
    
    public boolean hasNameRef() {
      return ((this.bitField0_ & 0x2) != 0);
    }
    
    public PatternRef getNameRef() {
      if (this.nameRefBuilder_ == null)
        return (this.nameRef_ == null) ? PatternRef.getDefaultInstance() : this.nameRef_; 
      return (PatternRef)this.nameRefBuilder_.getMessage();
    }
    
    public Builder setNameRef(PatternRef value) {
      if (this.nameRefBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        this.nameRef_ = value;
      } else {
        this.nameRefBuilder_.setMessage((AbstractMessage)value);
      } 
      this.bitField0_ |= 0x2;
      onChanged();
      return this;
    }
    
    public Builder setNameRef(PatternRef.Builder builderForValue) {
      if (this.nameRefBuilder_ == null) {
        this.nameRef_ = builderForValue.build();
      } else {
        this.nameRefBuilder_.setMessage((AbstractMessage)builderForValue.build());
      } 
      this.bitField0_ |= 0x2;
      onChanged();
      return this;
    }
    
    public Builder mergeNameRef(PatternRef value) {
      if (this.nameRefBuilder_ == null) {
        if ((this.bitField0_ & 0x2) != 0 && this.nameRef_ != null && this.nameRef_ != 
          
          PatternRef.getDefaultInstance()) {
          getNameRefBuilder().mergeFrom(value);
        } else {
          this.nameRef_ = value;
        } 
      } else {
        this.nameRefBuilder_.mergeFrom((AbstractMessage)value);
      } 
      if (this.nameRef_ != null) {
        this.bitField0_ |= 0x2;
        onChanged();
      } 
      return this;
    }
    
    public Builder clearNameRef() {
      this.bitField0_ &= 0xFFFFFFFD;
      this.nameRef_ = null;
      if (this.nameRefBuilder_ != null) {
        this.nameRefBuilder_.dispose();
        this.nameRefBuilder_ = null;
      } 
      onChanged();
      return this;
    }
    
    public PatternRef.Builder getNameRefBuilder() {
      this.bitField0_ |= 0x2;
      onChanged();
      return (PatternRef.Builder)getNameRefFieldBuilder().getBuilder();
    }
    
    public PatternRefOrBuilder getNameRefOrBuilder() {
      if (this.nameRefBuilder_ != null)
        return (PatternRefOrBuilder)this.nameRefBuilder_.getMessageOrBuilder(); 
      return (this.nameRef_ == null) ? 
        PatternRef.getDefaultInstance() : this.nameRef_;
    }
    
    private SingleFieldBuilderV3<PatternRef, PatternRef.Builder, PatternRefOrBuilder> getNameRefFieldBuilder() {
      if (this.nameRefBuilder_ == null) {
        this
          
          .nameRefBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getNameRef(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.nameRef_ = null;
      } 
      return this.nameRefBuilder_;
    }
    
    public final Builder setUnknownFields(UnknownFieldSet unknownFields) {
      return (Builder)super.setUnknownFields(unknownFields);
    }
    
    public final Builder mergeUnknownFields(UnknownFieldSet unknownFields) {
      return (Builder)super.mergeUnknownFields(unknownFields);
    }
  }
  
  private static final TextPieceGift DEFAULT_INSTANCE = new TextPieceGift();
  
  public static TextPieceGift getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }
  
  private static final Parser<TextPieceGift> PARSER = (Parser<TextPieceGift>)new AbstractParser<TextPieceGift>() {
      public TextPieceGift parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        TextPieceGift.Builder builder = TextPieceGift.newBuilder();
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
  
  public static Parser<TextPieceGift> parser() {
    return PARSER;
  }
  
  public Parser<TextPieceGift> getParserForType() {
    return PARSER;
  }
  
  public TextPieceGift getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }
}
