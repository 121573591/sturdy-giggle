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

public final class PicoDisplayInfo extends GeneratedMessageV3 implements PicoDisplayInfoOrBuilder {
  private static final long serialVersionUID = 0L;
  
  private int bitField0_;
  
  public static final int COMBOSUMCOUNT_FIELD_NUMBER = 1;
  
  private long comboSumCount_;
  
  public static final int EMOJI_FIELD_NUMBER = 2;
  
  private volatile Object emoji_;
  
  public static final int EMOJIICON_FIELD_NUMBER = 3;
  
  private Image emojiIcon_;
  
  public static final int EMOJITEXT_FIELD_NUMBER = 4;
  
  private volatile Object emojiText_;
  
  private byte memoizedIsInitialized;
  
  private PicoDisplayInfo(GeneratedMessageV3.Builder<?> builder) {
    super(builder);
    this.comboSumCount_ = 0L;
    this.emoji_ = "";
    this.emojiText_ = "";
    this.memoizedIsInitialized = -1;
  }
  
  private PicoDisplayInfo() {
    this.comboSumCount_ = 0L;
    this.emoji_ = "";
    this.emojiText_ = "";
    this.memoizedIsInitialized = -1;
    this.emoji_ = "";
    this.emojiText_ = "";
  }
  
  protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
    return new PicoDisplayInfo();
  }
  
  public static final Descriptors.Descriptor getDescriptor() {
    return Douyin.internal_static_PicoDisplayInfo_descriptor;
  }
  
  protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
    return Douyin.internal_static_PicoDisplayInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(PicoDisplayInfo.class, Builder.class);
  }
  
  public long getComboSumCount() {
    return this.comboSumCount_;
  }
  
  public String getEmoji() {
    Object ref = this.emoji_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.emoji_ = s;
    return s;
  }
  
  public ByteString getEmojiBytes() {
    Object ref = this.emoji_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.emoji_ = b;
      return b;
    } 
    return (ByteString)ref;
  }
  
  public boolean hasEmojiIcon() {
    return ((this.bitField0_ & 0x1) != 0);
  }
  
  public Image getEmojiIcon() {
    return (this.emojiIcon_ == null) ? Image.getDefaultInstance() : this.emojiIcon_;
  }
  
  public ImageOrBuilder getEmojiIconOrBuilder() {
    return (this.emojiIcon_ == null) ? Image.getDefaultInstance() : this.emojiIcon_;
  }
  
  public String getEmojiText() {
    Object ref = this.emojiText_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.emojiText_ = s;
    return s;
  }
  
  public ByteString getEmojiTextBytes() {
    Object ref = this.emojiText_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.emojiText_ = b;
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
    if (this.comboSumCount_ != 0L)
      output.writeUInt64(1, this.comboSumCount_); 
    if (!GeneratedMessageV3.isStringEmpty(this.emoji_))
      GeneratedMessageV3.writeString(output, 2, this.emoji_); 
    if ((this.bitField0_ & 0x1) != 0)
      output.writeMessage(3, (MessageLite)getEmojiIcon()); 
    if (!GeneratedMessageV3.isStringEmpty(this.emojiText_))
      GeneratedMessageV3.writeString(output, 4, this.emojiText_); 
    getUnknownFields().writeTo(output);
  }
  
  public int getSerializedSize() {
    int size = this.memoizedSize;
    if (size != -1)
      return size; 
    size = 0;
    if (this.comboSumCount_ != 0L)
      size += 
        CodedOutputStream.computeUInt64Size(1, this.comboSumCount_); 
    if (!GeneratedMessageV3.isStringEmpty(this.emoji_))
      size += GeneratedMessageV3.computeStringSize(2, this.emoji_); 
    if ((this.bitField0_ & 0x1) != 0)
      size += 
        CodedOutputStream.computeMessageSize(3, (MessageLite)getEmojiIcon()); 
    if (!GeneratedMessageV3.isStringEmpty(this.emojiText_))
      size += GeneratedMessageV3.computeStringSize(4, this.emojiText_); 
    size += getUnknownFields().getSerializedSize();
    this.memoizedSize = size;
    return size;
  }
  
  public boolean equals(Object obj) {
    if (obj == this)
      return true; 
    if (!(obj instanceof PicoDisplayInfo))
      return super.equals(obj); 
    PicoDisplayInfo other = (PicoDisplayInfo)obj;
    if (getComboSumCount() != other
      .getComboSumCount())
      return false; 
    if (!getEmoji().equals(other.getEmoji()))
      return false; 
    if (hasEmojiIcon() != other.hasEmojiIcon())
      return false; 
    if (hasEmojiIcon() && 
      
      !getEmojiIcon().equals(other.getEmojiIcon()))
      return false; 
    if (!getEmojiText().equals(other.getEmojiText()))
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
        getComboSumCount());
    hash = 37 * hash + 2;
    hash = 53 * hash + getEmoji().hashCode();
    if (hasEmojiIcon()) {
      hash = 37 * hash + 3;
      hash = 53 * hash + getEmojiIcon().hashCode();
    } 
    hash = 37 * hash + 4;
    hash = 53 * hash + getEmojiText().hashCode();
    hash = 29 * hash + getUnknownFields().hashCode();
    this.memoizedHashCode = hash;
    return hash;
  }
  
  public static PicoDisplayInfo parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
    return (PicoDisplayInfo)PARSER.parseFrom(data);
  }
  
  public static PicoDisplayInfo parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (PicoDisplayInfo)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static PicoDisplayInfo parseFrom(ByteString data) throws InvalidProtocolBufferException {
    return (PicoDisplayInfo)PARSER.parseFrom(data);
  }
  
  public static PicoDisplayInfo parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (PicoDisplayInfo)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static PicoDisplayInfo parseFrom(byte[] data) throws InvalidProtocolBufferException {
    return (PicoDisplayInfo)PARSER.parseFrom(data);
  }
  
  public static PicoDisplayInfo parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (PicoDisplayInfo)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static PicoDisplayInfo parseFrom(InputStream input) throws IOException {
    return 
      (PicoDisplayInfo)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static PicoDisplayInfo parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (PicoDisplayInfo)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static PicoDisplayInfo parseDelimitedFrom(InputStream input) throws IOException {
    return 
      (PicoDisplayInfo)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
  }
  
  public static PicoDisplayInfo parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (PicoDisplayInfo)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static PicoDisplayInfo parseFrom(CodedInputStream input) throws IOException {
    return 
      (PicoDisplayInfo)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static PicoDisplayInfo parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (PicoDisplayInfo)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public Builder newBuilderForType() {
    return newBuilder();
  }
  
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  
  public static Builder newBuilder(PicoDisplayInfo prototype) {
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
  
  public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements PicoDisplayInfoOrBuilder {
    private int bitField0_;
    
    private long comboSumCount_;
    
    private Object emoji_;
    
    private Image emojiIcon_;
    
    private SingleFieldBuilderV3<Image, Image.Builder, ImageOrBuilder> emojiIconBuilder_;
    
    private Object emojiText_;
    
    public static final Descriptors.Descriptor getDescriptor() {
      return Douyin.internal_static_PicoDisplayInfo_descriptor;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return Douyin.internal_static_PicoDisplayInfo_fieldAccessorTable
        .ensureFieldAccessorsInitialized(PicoDisplayInfo.class, Builder.class);
    }
    
    private Builder() {
      this.emoji_ = "";
      this.emojiText_ = "";
      maybeForceBuilderInitialization();
    }
    
    private Builder(GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      this.emoji_ = "";
      this.emojiText_ = "";
      maybeForceBuilderInitialization();
    }
    
    private void maybeForceBuilderInitialization() {
      if (PicoDisplayInfo.alwaysUseFieldBuilders)
        getEmojiIconFieldBuilder(); 
    }
    
    public Builder clear() {
      super.clear();
      this.bitField0_ = 0;
      this.comboSumCount_ = 0L;
      this.emoji_ = "";
      this.emojiIcon_ = null;
      if (this.emojiIconBuilder_ != null) {
        this.emojiIconBuilder_.dispose();
        this.emojiIconBuilder_ = null;
      } 
      this.emojiText_ = "";
      return this;
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
      return Douyin.internal_static_PicoDisplayInfo_descriptor;
    }
    
    public PicoDisplayInfo getDefaultInstanceForType() {
      return PicoDisplayInfo.getDefaultInstance();
    }
    
    public PicoDisplayInfo build() {
      PicoDisplayInfo result = buildPartial();
      if (!result.isInitialized())
        throw newUninitializedMessageException(result); 
      return result;
    }
    
    public PicoDisplayInfo buildPartial() {
      PicoDisplayInfo result = new PicoDisplayInfo(this);
      if (this.bitField0_ != 0)
        buildPartial0(result); 
      onBuilt();
      return result;
    }
    
    private void buildPartial0(PicoDisplayInfo result) {
      int from_bitField0_ = this.bitField0_;
      if ((from_bitField0_ & 0x1) != 0)
        result.comboSumCount_ = this.comboSumCount_; 
      if ((from_bitField0_ & 0x2) != 0)
        result.emoji_ = this.emoji_; 
      int to_bitField0_ = 0;
      if ((from_bitField0_ & 0x4) != 0) {
        result.emojiIcon_ = (this.emojiIconBuilder_ == null) ? this.emojiIcon_ : (Image)this.emojiIconBuilder_.build();
        to_bitField0_ |= 0x1;
      } 
      if ((from_bitField0_ & 0x8) != 0)
        result.emojiText_ = this.emojiText_; 
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
      if (other instanceof PicoDisplayInfo)
        return mergeFrom((PicoDisplayInfo)other); 
      super.mergeFrom(other);
      return this;
    }
    
    public Builder mergeFrom(PicoDisplayInfo other) {
      if (other == PicoDisplayInfo.getDefaultInstance())
        return this; 
      if (other.getComboSumCount() != 0L)
        setComboSumCount(other.getComboSumCount()); 
      if (!other.getEmoji().isEmpty()) {
        this.emoji_ = other.emoji_;
        this.bitField0_ |= 0x2;
        onChanged();
      } 
      if (other.hasEmojiIcon())
        mergeEmojiIcon(other.getEmojiIcon()); 
      if (!other.getEmojiText().isEmpty()) {
        this.emojiText_ = other.emojiText_;
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
            case 8:
              this.comboSumCount_ = input.readUInt64();
              this.bitField0_ |= 0x1;
              continue;
            case 18:
              this.emoji_ = input.readStringRequireUtf8();
              this.bitField0_ |= 0x2;
              continue;
            case 26:
              input.readMessage((MessageLite.Builder)getEmojiIconFieldBuilder().getBuilder(), extensionRegistry);
              this.bitField0_ |= 0x4;
              continue;
            case 34:
              this.emojiText_ = input.readStringRequireUtf8();
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
    
    public long getComboSumCount() {
      return this.comboSumCount_;
    }
    
    public Builder setComboSumCount(long value) {
      this.comboSumCount_ = value;
      this.bitField0_ |= 0x1;
      onChanged();
      return this;
    }
    
    public Builder clearComboSumCount() {
      this.bitField0_ &= 0xFFFFFFFE;
      this.comboSumCount_ = 0L;
      onChanged();
      return this;
    }
    
    public String getEmoji() {
      Object ref = this.emoji_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.emoji_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getEmojiBytes() {
      Object ref = this.emoji_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.emoji_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setEmoji(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.emoji_ = value;
      this.bitField0_ |= 0x2;
      onChanged();
      return this;
    }
    
    public Builder clearEmoji() {
      this.emoji_ = PicoDisplayInfo.getDefaultInstance().getEmoji();
      this.bitField0_ &= 0xFFFFFFFD;
      onChanged();
      return this;
    }
    
    public Builder setEmojiBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      PicoDisplayInfo.checkByteStringIsUtf8(value);
      this.emoji_ = value;
      this.bitField0_ |= 0x2;
      onChanged();
      return this;
    }
    
    public boolean hasEmojiIcon() {
      return ((this.bitField0_ & 0x4) != 0);
    }
    
    public Image getEmojiIcon() {
      if (this.emojiIconBuilder_ == null)
        return (this.emojiIcon_ == null) ? Image.getDefaultInstance() : this.emojiIcon_; 
      return (Image)this.emojiIconBuilder_.getMessage();
    }
    
    public Builder setEmojiIcon(Image value) {
      if (this.emojiIconBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        this.emojiIcon_ = value;
      } else {
        this.emojiIconBuilder_.setMessage((AbstractMessage)value);
      } 
      this.bitField0_ |= 0x4;
      onChanged();
      return this;
    }
    
    public Builder setEmojiIcon(Image.Builder builderForValue) {
      if (this.emojiIconBuilder_ == null) {
        this.emojiIcon_ = builderForValue.build();
      } else {
        this.emojiIconBuilder_.setMessage((AbstractMessage)builderForValue.build());
      } 
      this.bitField0_ |= 0x4;
      onChanged();
      return this;
    }
    
    public Builder mergeEmojiIcon(Image value) {
      if (this.emojiIconBuilder_ == null) {
        if ((this.bitField0_ & 0x4) != 0 && this.emojiIcon_ != null && this.emojiIcon_ != Image.getDefaultInstance()) {
          getEmojiIconBuilder().mergeFrom(value);
        } else {
          this.emojiIcon_ = value;
        } 
      } else {
        this.emojiIconBuilder_.mergeFrom((AbstractMessage)value);
      } 
      if (this.emojiIcon_ != null) {
        this.bitField0_ |= 0x4;
        onChanged();
      } 
      return this;
    }
    
    public Builder clearEmojiIcon() {
      this.bitField0_ &= 0xFFFFFFFB;
      this.emojiIcon_ = null;
      if (this.emojiIconBuilder_ != null) {
        this.emojiIconBuilder_.dispose();
        this.emojiIconBuilder_ = null;
      } 
      onChanged();
      return this;
    }
    
    public Image.Builder getEmojiIconBuilder() {
      this.bitField0_ |= 0x4;
      onChanged();
      return (Image.Builder)getEmojiIconFieldBuilder().getBuilder();
    }
    
    public ImageOrBuilder getEmojiIconOrBuilder() {
      if (this.emojiIconBuilder_ != null)
        return (ImageOrBuilder)this.emojiIconBuilder_.getMessageOrBuilder(); 
      return (this.emojiIcon_ == null) ? Image.getDefaultInstance() : this.emojiIcon_;
    }
    
    private SingleFieldBuilderV3<Image, Image.Builder, ImageOrBuilder> getEmojiIconFieldBuilder() {
      if (this.emojiIconBuilder_ == null) {
        this.emojiIconBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getEmojiIcon(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.emojiIcon_ = null;
      } 
      return this.emojiIconBuilder_;
    }
    
    public String getEmojiText() {
      Object ref = this.emojiText_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.emojiText_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getEmojiTextBytes() {
      Object ref = this.emojiText_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.emojiText_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setEmojiText(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.emojiText_ = value;
      this.bitField0_ |= 0x8;
      onChanged();
      return this;
    }
    
    public Builder clearEmojiText() {
      this.emojiText_ = PicoDisplayInfo.getDefaultInstance().getEmojiText();
      this.bitField0_ &= 0xFFFFFFF7;
      onChanged();
      return this;
    }
    
    public Builder setEmojiTextBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      PicoDisplayInfo.checkByteStringIsUtf8(value);
      this.emojiText_ = value;
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
  
  private static final PicoDisplayInfo DEFAULT_INSTANCE = new PicoDisplayInfo();
  
  public static PicoDisplayInfo getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }
  
  private static final Parser<PicoDisplayInfo> PARSER = (Parser<PicoDisplayInfo>)new AbstractParser<PicoDisplayInfo>() {
      public PicoDisplayInfo parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        PicoDisplayInfo.Builder builder = PicoDisplayInfo.newBuilder();
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
  
  public static Parser<PicoDisplayInfo> parser() {
    return PARSER;
  }
  
  public Parser<PicoDisplayInfo> getParserForType() {
    return PARSER;
  }
  
  public PicoDisplayInfo getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }
}
