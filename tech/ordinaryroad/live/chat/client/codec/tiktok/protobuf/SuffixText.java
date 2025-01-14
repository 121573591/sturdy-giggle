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

public final class SuffixText extends GeneratedMessageV3 implements SuffixTextOrBuilder {
  private static final long serialVersionUID = 0L;
  
  private int bitField0_;
  
  public static final int BIZ_TYPE_FIELD_NUMBER = 1;
  
  private long bizType_;
  
  public static final int TEXT_FIELD_NUMBER = 2;
  
  private Text text_;
  
  private byte memoizedIsInitialized;
  
  private SuffixText(GeneratedMessageV3.Builder<?> builder) {
    super(builder);
    this.bizType_ = 0L;
    this.memoizedIsInitialized = -1;
  }
  
  private SuffixText() {
    this.bizType_ = 0L;
    this.memoizedIsInitialized = -1;
  }
  
  protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
    return new SuffixText();
  }
  
  public static final Descriptors.Descriptor getDescriptor() {
    return Tiktok.internal_static_SuffixText_descriptor;
  }
  
  protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
    return Tiktok.internal_static_SuffixText_fieldAccessorTable.ensureFieldAccessorsInitialized(SuffixText.class, Builder.class);
  }
  
  public long getBizType() {
    return this.bizType_;
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
    if (this.bizType_ != 0L)
      output.writeUInt64(1, this.bizType_); 
    if ((this.bitField0_ & 0x1) != 0)
      output.writeMessage(2, (MessageLite)getText()); 
    getUnknownFields().writeTo(output);
  }
  
  public int getSerializedSize() {
    int size = this.memoizedSize;
    if (size != -1)
      return size; 
    size = 0;
    if (this.bizType_ != 0L)
      size += 
        CodedOutputStream.computeUInt64Size(1, this.bizType_); 
    if ((this.bitField0_ & 0x1) != 0)
      size += 
        CodedOutputStream.computeMessageSize(2, (MessageLite)getText()); 
    size += getUnknownFields().getSerializedSize();
    this.memoizedSize = size;
    return size;
  }
  
  public boolean equals(Object obj) {
    if (obj == this)
      return true; 
    if (!(obj instanceof SuffixText))
      return super.equals(obj); 
    SuffixText other = (SuffixText)obj;
    if (getBizType() != other
      .getBizType())
      return false; 
    if (hasText() != other.hasText())
      return false; 
    if (hasText() && 
      
      !getText().equals(other.getText()))
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
        getBizType());
    if (hasText()) {
      hash = 37 * hash + 2;
      hash = 53 * hash + getText().hashCode();
    } 
    hash = 29 * hash + getUnknownFields().hashCode();
    this.memoizedHashCode = hash;
    return hash;
  }
  
  public static SuffixText parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
    return (SuffixText)PARSER.parseFrom(data);
  }
  
  public static SuffixText parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (SuffixText)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static SuffixText parseFrom(ByteString data) throws InvalidProtocolBufferException {
    return (SuffixText)PARSER.parseFrom(data);
  }
  
  public static SuffixText parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (SuffixText)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static SuffixText parseFrom(byte[] data) throws InvalidProtocolBufferException {
    return (SuffixText)PARSER.parseFrom(data);
  }
  
  public static SuffixText parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (SuffixText)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static SuffixText parseFrom(InputStream input) throws IOException {
    return 
      (SuffixText)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static SuffixText parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (SuffixText)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static SuffixText parseDelimitedFrom(InputStream input) throws IOException {
    return 
      (SuffixText)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
  }
  
  public static SuffixText parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (SuffixText)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static SuffixText parseFrom(CodedInputStream input) throws IOException {
    return 
      (SuffixText)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static SuffixText parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (SuffixText)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public Builder newBuilderForType() {
    return newBuilder();
  }
  
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  
  public static Builder newBuilder(SuffixText prototype) {
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
  
  public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements SuffixTextOrBuilder {
    private int bitField0_;
    
    private long bizType_;
    
    private Text text_;
    
    private SingleFieldBuilderV3<Text, Text.Builder, TextOrBuilder> textBuilder_;
    
    public static final Descriptors.Descriptor getDescriptor() {
      return Tiktok.internal_static_SuffixText_descriptor;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return Tiktok.internal_static_SuffixText_fieldAccessorTable
        .ensureFieldAccessorsInitialized(SuffixText.class, Builder.class);
    }
    
    private Builder() {
      maybeForceBuilderInitialization();
    }
    
    private Builder(GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    
    private void maybeForceBuilderInitialization() {
      if (SuffixText.alwaysUseFieldBuilders)
        getTextFieldBuilder(); 
    }
    
    public Builder clear() {
      super.clear();
      this.bitField0_ = 0;
      this.bizType_ = 0L;
      this.text_ = null;
      if (this.textBuilder_ != null) {
        this.textBuilder_.dispose();
        this.textBuilder_ = null;
      } 
      return this;
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
      return Tiktok.internal_static_SuffixText_descriptor;
    }
    
    public SuffixText getDefaultInstanceForType() {
      return SuffixText.getDefaultInstance();
    }
    
    public SuffixText build() {
      SuffixText result = buildPartial();
      if (!result.isInitialized())
        throw newUninitializedMessageException(result); 
      return result;
    }
    
    public SuffixText buildPartial() {
      SuffixText result = new SuffixText(this);
      if (this.bitField0_ != 0)
        buildPartial0(result); 
      onBuilt();
      return result;
    }
    
    private void buildPartial0(SuffixText result) {
      int from_bitField0_ = this.bitField0_;
      if ((from_bitField0_ & 0x1) != 0)
        result.bizType_ = this.bizType_; 
      int to_bitField0_ = 0;
      if ((from_bitField0_ & 0x2) != 0) {
        result.text_ = (this.textBuilder_ == null) ? 
          this.text_ : 
          (Text)this.textBuilder_.build();
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
      if (other instanceof SuffixText)
        return mergeFrom((SuffixText)other); 
      super.mergeFrom(other);
      return this;
    }
    
    public Builder mergeFrom(SuffixText other) {
      if (other == SuffixText.getDefaultInstance())
        return this; 
      if (other.getBizType() != 0L)
        setBizType(other.getBizType()); 
      if (other.hasText())
        mergeText(other.getText()); 
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
              this.bizType_ = input.readUInt64();
              this.bitField0_ |= 0x1;
              continue;
            case 18:
              input.readMessage((MessageLite.Builder)
                  getTextFieldBuilder().getBuilder(), extensionRegistry);
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
    
    public long getBizType() {
      return this.bizType_;
    }
    
    public Builder setBizType(long value) {
      this.bizType_ = value;
      this.bitField0_ |= 0x1;
      onChanged();
      return this;
    }
    
    public Builder clearBizType() {
      this.bitField0_ &= 0xFFFFFFFE;
      this.bizType_ = 0L;
      onChanged();
      return this;
    }
    
    public boolean hasText() {
      return ((this.bitField0_ & 0x2) != 0);
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
      this.bitField0_ |= 0x2;
      onChanged();
      return this;
    }
    
    public Builder setText(Text.Builder builderForValue) {
      if (this.textBuilder_ == null) {
        this.text_ = builderForValue.build();
      } else {
        this.textBuilder_.setMessage((AbstractMessage)builderForValue.build());
      } 
      this.bitField0_ |= 0x2;
      onChanged();
      return this;
    }
    
    public Builder mergeText(Text value) {
      if (this.textBuilder_ == null) {
        if ((this.bitField0_ & 0x2) != 0 && this.text_ != null && this.text_ != 
          
          Text.getDefaultInstance()) {
          getTextBuilder().mergeFrom(value);
        } else {
          this.text_ = value;
        } 
      } else {
        this.textBuilder_.mergeFrom((AbstractMessage)value);
      } 
      if (this.text_ != null) {
        this.bitField0_ |= 0x2;
        onChanged();
      } 
      return this;
    }
    
    public Builder clearText() {
      this.bitField0_ &= 0xFFFFFFFD;
      this.text_ = null;
      if (this.textBuilder_ != null) {
        this.textBuilder_.dispose();
        this.textBuilder_ = null;
      } 
      onChanged();
      return this;
    }
    
    public Text.Builder getTextBuilder() {
      this.bitField0_ |= 0x2;
      onChanged();
      return (Text.Builder)getTextFieldBuilder().getBuilder();
    }
    
    public TextOrBuilder getTextOrBuilder() {
      if (this.textBuilder_ != null)
        return (TextOrBuilder)this.textBuilder_.getMessageOrBuilder(); 
      return (this.text_ == null) ? 
        Text.getDefaultInstance() : this.text_;
    }
    
    private SingleFieldBuilderV3<Text, Text.Builder, TextOrBuilder> getTextFieldBuilder() {
      if (this.textBuilder_ == null) {
        this
          
          .textBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getText(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.text_ = null;
      } 
      return this.textBuilder_;
    }
    
    public final Builder setUnknownFields(UnknownFieldSet unknownFields) {
      return (Builder)super.setUnknownFields(unknownFields);
    }
    
    public final Builder mergeUnknownFields(UnknownFieldSet unknownFields) {
      return (Builder)super.mergeUnknownFields(unknownFields);
    }
  }
  
  private static final SuffixText DEFAULT_INSTANCE = new SuffixText();
  
  public static SuffixText getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }
  
  private static final Parser<SuffixText> PARSER = (Parser<SuffixText>)new AbstractParser<SuffixText>() {
      public SuffixText parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        SuffixText.Builder builder = SuffixText.newBuilder();
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
  
  public static Parser<SuffixText> parser() {
    return PARSER;
  }
  
  public Parser<SuffixText> getParserForType() {
    return PARSER;
  }
  
  public SuffixText getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }
}
