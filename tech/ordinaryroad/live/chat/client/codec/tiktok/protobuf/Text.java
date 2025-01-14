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
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Text extends GeneratedMessageV3 implements TextOrBuilder {
  private static final long serialVersionUID = 0L;
  
  private int bitField0_;
  
  public static final int KEY_FIELD_NUMBER = 1;
  
  private volatile Object key_;
  
  public static final int DEFAULT_PATTERN_FIELD_NUMBER = 2;
  
  private volatile Object defaultPattern_;
  
  public static final int DEFAULT_FORMAT_FIELD_NUMBER = 3;
  
  private TextFormat defaultFormat_;
  
  public static final int PIECES_FIELD_NUMBER = 4;
  
  private List<TextPiece> pieces_;
  
  private byte memoizedIsInitialized;
  
  private Text(GeneratedMessageV3.Builder<?> builder) {
    super(builder);
    this.key_ = "";
    this.defaultPattern_ = "";
    this.memoizedIsInitialized = -1;
  }
  
  private Text() {
    this.key_ = "";
    this.defaultPattern_ = "";
    this.memoizedIsInitialized = -1;
    this.key_ = "";
    this.defaultPattern_ = "";
    this.pieces_ = Collections.emptyList();
  }
  
  protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
    return new Text();
  }
  
  public static final Descriptors.Descriptor getDescriptor() {
    return Tiktok.internal_static_Text_descriptor;
  }
  
  protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
    return Tiktok.internal_static_Text_fieldAccessorTable.ensureFieldAccessorsInitialized(Text.class, Builder.class);
  }
  
  public String getKey() {
    Object ref = this.key_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.key_ = s;
    return s;
  }
  
  public ByteString getKeyBytes() {
    Object ref = this.key_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.key_ = b;
      return b;
    } 
    return (ByteString)ref;
  }
  
  public String getDefaultPattern() {
    Object ref = this.defaultPattern_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.defaultPattern_ = s;
    return s;
  }
  
  public ByteString getDefaultPatternBytes() {
    Object ref = this.defaultPattern_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.defaultPattern_ = b;
      return b;
    } 
    return (ByteString)ref;
  }
  
  public boolean hasDefaultFormat() {
    return ((this.bitField0_ & 0x1) != 0);
  }
  
  public TextFormat getDefaultFormat() {
    return (this.defaultFormat_ == null) ? TextFormat.getDefaultInstance() : this.defaultFormat_;
  }
  
  public TextFormatOrBuilder getDefaultFormatOrBuilder() {
    return (this.defaultFormat_ == null) ? TextFormat.getDefaultInstance() : this.defaultFormat_;
  }
  
  public List<TextPiece> getPiecesList() {
    return this.pieces_;
  }
  
  public List<? extends TextPieceOrBuilder> getPiecesOrBuilderList() {
    return (List)this.pieces_;
  }
  
  public int getPiecesCount() {
    return this.pieces_.size();
  }
  
  public TextPiece getPieces(int index) {
    return this.pieces_.get(index);
  }
  
  public TextPieceOrBuilder getPiecesOrBuilder(int index) {
    return this.pieces_.get(index);
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
    if (!GeneratedMessageV3.isStringEmpty(this.key_))
      GeneratedMessageV3.writeString(output, 1, this.key_); 
    if (!GeneratedMessageV3.isStringEmpty(this.defaultPattern_))
      GeneratedMessageV3.writeString(output, 2, this.defaultPattern_); 
    if ((this.bitField0_ & 0x1) != 0)
      output.writeMessage(3, (MessageLite)getDefaultFormat()); 
    for (int i = 0; i < this.pieces_.size(); i++)
      output.writeMessage(4, (MessageLite)this.pieces_.get(i)); 
    getUnknownFields().writeTo(output);
  }
  
  public int getSerializedSize() {
    int size = this.memoizedSize;
    if (size != -1)
      return size; 
    size = 0;
    if (!GeneratedMessageV3.isStringEmpty(this.key_))
      size += GeneratedMessageV3.computeStringSize(1, this.key_); 
    if (!GeneratedMessageV3.isStringEmpty(this.defaultPattern_))
      size += GeneratedMessageV3.computeStringSize(2, this.defaultPattern_); 
    if ((this.bitField0_ & 0x1) != 0)
      size += 
        CodedOutputStream.computeMessageSize(3, (MessageLite)getDefaultFormat()); 
    for (int i = 0; i < this.pieces_.size(); i++)
      size += 
        CodedOutputStream.computeMessageSize(4, (MessageLite)this.pieces_.get(i)); 
    size += getUnknownFields().getSerializedSize();
    this.memoizedSize = size;
    return size;
  }
  
  public boolean equals(Object obj) {
    if (obj == this)
      return true; 
    if (!(obj instanceof Text))
      return super.equals(obj); 
    Text other = (Text)obj;
    if (!getKey().equals(other.getKey()))
      return false; 
    if (!getDefaultPattern().equals(other.getDefaultPattern()))
      return false; 
    if (hasDefaultFormat() != other.hasDefaultFormat())
      return false; 
    if (hasDefaultFormat() && 
      
      !getDefaultFormat().equals(other.getDefaultFormat()))
      return false; 
    if (!getPiecesList().equals(other.getPiecesList()))
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
    hash = 53 * hash + getKey().hashCode();
    hash = 37 * hash + 2;
    hash = 53 * hash + getDefaultPattern().hashCode();
    if (hasDefaultFormat()) {
      hash = 37 * hash + 3;
      hash = 53 * hash + getDefaultFormat().hashCode();
    } 
    if (getPiecesCount() > 0) {
      hash = 37 * hash + 4;
      hash = 53 * hash + getPiecesList().hashCode();
    } 
    hash = 29 * hash + getUnknownFields().hashCode();
    this.memoizedHashCode = hash;
    return hash;
  }
  
  public static Text parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
    return (Text)PARSER.parseFrom(data);
  }
  
  public static Text parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (Text)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static Text parseFrom(ByteString data) throws InvalidProtocolBufferException {
    return (Text)PARSER.parseFrom(data);
  }
  
  public static Text parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (Text)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static Text parseFrom(byte[] data) throws InvalidProtocolBufferException {
    return (Text)PARSER.parseFrom(data);
  }
  
  public static Text parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (Text)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static Text parseFrom(InputStream input) throws IOException {
    return 
      (Text)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static Text parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (Text)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static Text parseDelimitedFrom(InputStream input) throws IOException {
    return 
      (Text)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
  }
  
  public static Text parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (Text)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static Text parseFrom(CodedInputStream input) throws IOException {
    return 
      (Text)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static Text parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (Text)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public Builder newBuilderForType() {
    return newBuilder();
  }
  
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  
  public static Builder newBuilder(Text prototype) {
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
  
  public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements TextOrBuilder {
    private int bitField0_;
    
    private Object key_;
    
    private Object defaultPattern_;
    
    private TextFormat defaultFormat_;
    
    private SingleFieldBuilderV3<TextFormat, TextFormat.Builder, TextFormatOrBuilder> defaultFormatBuilder_;
    
    private List<TextPiece> pieces_;
    
    private RepeatedFieldBuilderV3<TextPiece, TextPiece.Builder, TextPieceOrBuilder> piecesBuilder_;
    
    public static final Descriptors.Descriptor getDescriptor() {
      return Tiktok.internal_static_Text_descriptor;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return Tiktok.internal_static_Text_fieldAccessorTable
        .ensureFieldAccessorsInitialized(Text.class, Builder.class);
    }
    
    private Builder() {
      this.key_ = "";
      this.defaultPattern_ = "";
      this
        .pieces_ = Collections.emptyList();
      maybeForceBuilderInitialization();
    }
    
    private Builder(GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      this.key_ = "";
      this.defaultPattern_ = "";
      this.pieces_ = Collections.emptyList();
      maybeForceBuilderInitialization();
    }
    
    private void maybeForceBuilderInitialization() {
      if (Text.alwaysUseFieldBuilders) {
        getDefaultFormatFieldBuilder();
        getPiecesFieldBuilder();
      } 
    }
    
    public Builder clear() {
      super.clear();
      this.bitField0_ = 0;
      this.key_ = "";
      this.defaultPattern_ = "";
      this.defaultFormat_ = null;
      if (this.defaultFormatBuilder_ != null) {
        this.defaultFormatBuilder_.dispose();
        this.defaultFormatBuilder_ = null;
      } 
      if (this.piecesBuilder_ == null) {
        this.pieces_ = Collections.emptyList();
      } else {
        this.pieces_ = null;
        this.piecesBuilder_.clear();
      } 
      this.bitField0_ &= 0xFFFFFFF7;
      return this;
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
      return Tiktok.internal_static_Text_descriptor;
    }
    
    public Text getDefaultInstanceForType() {
      return Text.getDefaultInstance();
    }
    
    public Text build() {
      Text result = buildPartial();
      if (!result.isInitialized())
        throw newUninitializedMessageException(result); 
      return result;
    }
    
    public Text buildPartial() {
      Text result = new Text(this);
      buildPartialRepeatedFields(result);
      if (this.bitField0_ != 0)
        buildPartial0(result); 
      onBuilt();
      return result;
    }
    
    private void buildPartialRepeatedFields(Text result) {
      if (this.piecesBuilder_ == null) {
        if ((this.bitField0_ & 0x8) != 0) {
          this.pieces_ = Collections.unmodifiableList(this.pieces_);
          this.bitField0_ &= 0xFFFFFFF7;
        } 
        result.pieces_ = this.pieces_;
      } else {
        result.pieces_ = this.piecesBuilder_.build();
      } 
    }
    
    private void buildPartial0(Text result) {
      int from_bitField0_ = this.bitField0_;
      if ((from_bitField0_ & 0x1) != 0)
        result.key_ = this.key_; 
      if ((from_bitField0_ & 0x2) != 0)
        result.defaultPattern_ = this.defaultPattern_; 
      int to_bitField0_ = 0;
      if ((from_bitField0_ & 0x4) != 0) {
        result.defaultFormat_ = (this.defaultFormatBuilder_ == null) ? this.defaultFormat_ : (TextFormat)this.defaultFormatBuilder_.build();
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
      if (other instanceof Text)
        return mergeFrom((Text)other); 
      super.mergeFrom(other);
      return this;
    }
    
    public Builder mergeFrom(Text other) {
      if (other == Text.getDefaultInstance())
        return this; 
      if (!other.getKey().isEmpty()) {
        this.key_ = other.key_;
        this.bitField0_ |= 0x1;
        onChanged();
      } 
      if (!other.getDefaultPattern().isEmpty()) {
        this.defaultPattern_ = other.defaultPattern_;
        this.bitField0_ |= 0x2;
        onChanged();
      } 
      if (other.hasDefaultFormat())
        mergeDefaultFormat(other.getDefaultFormat()); 
      if (this.piecesBuilder_ == null) {
        if (!other.pieces_.isEmpty()) {
          if (this.pieces_.isEmpty()) {
            this.pieces_ = other.pieces_;
            this.bitField0_ &= 0xFFFFFFF7;
          } else {
            ensurePiecesIsMutable();
            this.pieces_.addAll(other.pieces_);
          } 
          onChanged();
        } 
      } else if (!other.pieces_.isEmpty()) {
        if (this.piecesBuilder_.isEmpty()) {
          this.piecesBuilder_.dispose();
          this.piecesBuilder_ = null;
          this.pieces_ = other.pieces_;
          this.bitField0_ &= 0xFFFFFFF7;
          this.piecesBuilder_ = Text.alwaysUseFieldBuilders ? getPiecesFieldBuilder() : null;
        } else {
          this.piecesBuilder_.addAllMessages(other.pieces_);
        } 
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
          TextPiece m;
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              continue;
            case 10:
              this.key_ = input.readStringRequireUtf8();
              this.bitField0_ |= 0x1;
              continue;
            case 18:
              this.defaultPattern_ = input.readStringRequireUtf8();
              this.bitField0_ |= 0x2;
              continue;
            case 26:
              input.readMessage((MessageLite.Builder)getDefaultFormatFieldBuilder().getBuilder(), extensionRegistry);
              this.bitField0_ |= 0x4;
              continue;
            case 34:
              m = (TextPiece)input.readMessage(TextPiece.parser(), extensionRegistry);
              if (this.piecesBuilder_ == null) {
                ensurePiecesIsMutable();
                this.pieces_.add(m);
                continue;
              } 
              this.piecesBuilder_.addMessage((AbstractMessage)m);
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
    
    public String getKey() {
      Object ref = this.key_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.key_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getKeyBytes() {
      Object ref = this.key_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.key_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setKey(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.key_ = value;
      this.bitField0_ |= 0x1;
      onChanged();
      return this;
    }
    
    public Builder clearKey() {
      this.key_ = Text.getDefaultInstance().getKey();
      this.bitField0_ &= 0xFFFFFFFE;
      onChanged();
      return this;
    }
    
    public Builder setKeyBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      Text.checkByteStringIsUtf8(value);
      this.key_ = value;
      this.bitField0_ |= 0x1;
      onChanged();
      return this;
    }
    
    public String getDefaultPattern() {
      Object ref = this.defaultPattern_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.defaultPattern_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getDefaultPatternBytes() {
      Object ref = this.defaultPattern_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.defaultPattern_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setDefaultPattern(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.defaultPattern_ = value;
      this.bitField0_ |= 0x2;
      onChanged();
      return this;
    }
    
    public Builder clearDefaultPattern() {
      this.defaultPattern_ = Text.getDefaultInstance().getDefaultPattern();
      this.bitField0_ &= 0xFFFFFFFD;
      onChanged();
      return this;
    }
    
    public Builder setDefaultPatternBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      Text.checkByteStringIsUtf8(value);
      this.defaultPattern_ = value;
      this.bitField0_ |= 0x2;
      onChanged();
      return this;
    }
    
    public boolean hasDefaultFormat() {
      return ((this.bitField0_ & 0x4) != 0);
    }
    
    public TextFormat getDefaultFormat() {
      if (this.defaultFormatBuilder_ == null)
        return (this.defaultFormat_ == null) ? TextFormat.getDefaultInstance() : this.defaultFormat_; 
      return (TextFormat)this.defaultFormatBuilder_.getMessage();
    }
    
    public Builder setDefaultFormat(TextFormat value) {
      if (this.defaultFormatBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        this.defaultFormat_ = value;
      } else {
        this.defaultFormatBuilder_.setMessage((AbstractMessage)value);
      } 
      this.bitField0_ |= 0x4;
      onChanged();
      return this;
    }
    
    public Builder setDefaultFormat(TextFormat.Builder builderForValue) {
      if (this.defaultFormatBuilder_ == null) {
        this.defaultFormat_ = builderForValue.build();
      } else {
        this.defaultFormatBuilder_.setMessage((AbstractMessage)builderForValue.build());
      } 
      this.bitField0_ |= 0x4;
      onChanged();
      return this;
    }
    
    public Builder mergeDefaultFormat(TextFormat value) {
      if (this.defaultFormatBuilder_ == null) {
        if ((this.bitField0_ & 0x4) != 0 && this.defaultFormat_ != null && this.defaultFormat_ != TextFormat.getDefaultInstance()) {
          getDefaultFormatBuilder().mergeFrom(value);
        } else {
          this.defaultFormat_ = value;
        } 
      } else {
        this.defaultFormatBuilder_.mergeFrom((AbstractMessage)value);
      } 
      if (this.defaultFormat_ != null) {
        this.bitField0_ |= 0x4;
        onChanged();
      } 
      return this;
    }
    
    public Builder clearDefaultFormat() {
      this.bitField0_ &= 0xFFFFFFFB;
      this.defaultFormat_ = null;
      if (this.defaultFormatBuilder_ != null) {
        this.defaultFormatBuilder_.dispose();
        this.defaultFormatBuilder_ = null;
      } 
      onChanged();
      return this;
    }
    
    public TextFormat.Builder getDefaultFormatBuilder() {
      this.bitField0_ |= 0x4;
      onChanged();
      return (TextFormat.Builder)getDefaultFormatFieldBuilder().getBuilder();
    }
    
    public TextFormatOrBuilder getDefaultFormatOrBuilder() {
      if (this.defaultFormatBuilder_ != null)
        return (TextFormatOrBuilder)this.defaultFormatBuilder_.getMessageOrBuilder(); 
      return (this.defaultFormat_ == null) ? TextFormat.getDefaultInstance() : this.defaultFormat_;
    }
    
    private SingleFieldBuilderV3<TextFormat, TextFormat.Builder, TextFormatOrBuilder> getDefaultFormatFieldBuilder() {
      if (this.defaultFormatBuilder_ == null) {
        this.defaultFormatBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getDefaultFormat(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.defaultFormat_ = null;
      } 
      return this.defaultFormatBuilder_;
    }
    
    private void ensurePiecesIsMutable() {
      if ((this.bitField0_ & 0x8) == 0) {
        this.pieces_ = new ArrayList<>(this.pieces_);
        this.bitField0_ |= 0x8;
      } 
    }
    
    public List<TextPiece> getPiecesList() {
      if (this.piecesBuilder_ == null)
        return Collections.unmodifiableList(this.pieces_); 
      return this.piecesBuilder_.getMessageList();
    }
    
    public int getPiecesCount() {
      if (this.piecesBuilder_ == null)
        return this.pieces_.size(); 
      return this.piecesBuilder_.getCount();
    }
    
    public TextPiece getPieces(int index) {
      if (this.piecesBuilder_ == null)
        return this.pieces_.get(index); 
      return (TextPiece)this.piecesBuilder_.getMessage(index);
    }
    
    public Builder setPieces(int index, TextPiece value) {
      if (this.piecesBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        ensurePiecesIsMutable();
        this.pieces_.set(index, value);
        onChanged();
      } else {
        this.piecesBuilder_.setMessage(index, (AbstractMessage)value);
      } 
      return this;
    }
    
    public Builder setPieces(int index, TextPiece.Builder builderForValue) {
      if (this.piecesBuilder_ == null) {
        ensurePiecesIsMutable();
        this.pieces_.set(index, builderForValue.build());
        onChanged();
      } else {
        this.piecesBuilder_.setMessage(index, (AbstractMessage)builderForValue.build());
      } 
      return this;
    }
    
    public Builder addPieces(TextPiece value) {
      if (this.piecesBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        ensurePiecesIsMutable();
        this.pieces_.add(value);
        onChanged();
      } else {
        this.piecesBuilder_.addMessage((AbstractMessage)value);
      } 
      return this;
    }
    
    public Builder addPieces(int index, TextPiece value) {
      if (this.piecesBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        ensurePiecesIsMutable();
        this.pieces_.add(index, value);
        onChanged();
      } else {
        this.piecesBuilder_.addMessage(index, (AbstractMessage)value);
      } 
      return this;
    }
    
    public Builder addPieces(TextPiece.Builder builderForValue) {
      if (this.piecesBuilder_ == null) {
        ensurePiecesIsMutable();
        this.pieces_.add(builderForValue.build());
        onChanged();
      } else {
        this.piecesBuilder_.addMessage((AbstractMessage)builderForValue.build());
      } 
      return this;
    }
    
    public Builder addPieces(int index, TextPiece.Builder builderForValue) {
      if (this.piecesBuilder_ == null) {
        ensurePiecesIsMutable();
        this.pieces_.add(index, builderForValue.build());
        onChanged();
      } else {
        this.piecesBuilder_.addMessage(index, (AbstractMessage)builderForValue.build());
      } 
      return this;
    }
    
    public Builder addAllPieces(Iterable<? extends TextPiece> values) {
      if (this.piecesBuilder_ == null) {
        ensurePiecesIsMutable();
        AbstractMessageLite.Builder.addAll(values, this.pieces_);
        onChanged();
      } else {
        this.piecesBuilder_.addAllMessages(values);
      } 
      return this;
    }
    
    public Builder clearPieces() {
      if (this.piecesBuilder_ == null) {
        this.pieces_ = Collections.emptyList();
        this.bitField0_ &= 0xFFFFFFF7;
        onChanged();
      } else {
        this.piecesBuilder_.clear();
      } 
      return this;
    }
    
    public Builder removePieces(int index) {
      if (this.piecesBuilder_ == null) {
        ensurePiecesIsMutable();
        this.pieces_.remove(index);
        onChanged();
      } else {
        this.piecesBuilder_.remove(index);
      } 
      return this;
    }
    
    public TextPiece.Builder getPiecesBuilder(int index) {
      return (TextPiece.Builder)getPiecesFieldBuilder().getBuilder(index);
    }
    
    public TextPieceOrBuilder getPiecesOrBuilder(int index) {
      if (this.piecesBuilder_ == null)
        return this.pieces_.get(index); 
      return (TextPieceOrBuilder)this.piecesBuilder_.getMessageOrBuilder(index);
    }
    
    public List<? extends TextPieceOrBuilder> getPiecesOrBuilderList() {
      if (this.piecesBuilder_ != null)
        return this.piecesBuilder_.getMessageOrBuilderList(); 
      return Collections.unmodifiableList((List)this.pieces_);
    }
    
    public TextPiece.Builder addPiecesBuilder() {
      return (TextPiece.Builder)getPiecesFieldBuilder().addBuilder(
          (AbstractMessage)TextPiece.getDefaultInstance());
    }
    
    public TextPiece.Builder addPiecesBuilder(int index) {
      return (TextPiece.Builder)getPiecesFieldBuilder().addBuilder(index, 
          (AbstractMessage)TextPiece.getDefaultInstance());
    }
    
    public List<TextPiece.Builder> getPiecesBuilderList() {
      return getPiecesFieldBuilder().getBuilderList();
    }
    
    private RepeatedFieldBuilderV3<TextPiece, TextPiece.Builder, TextPieceOrBuilder> getPiecesFieldBuilder() {
      if (this.piecesBuilder_ == null) {
        this
          
          .piecesBuilder_ = new RepeatedFieldBuilderV3(this.pieces_, ((this.bitField0_ & 0x8) != 0), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.pieces_ = null;
      } 
      return this.piecesBuilder_;
    }
    
    public final Builder setUnknownFields(UnknownFieldSet unknownFields) {
      return (Builder)super.setUnknownFields(unknownFields);
    }
    
    public final Builder mergeUnknownFields(UnknownFieldSet unknownFields) {
      return (Builder)super.mergeUnknownFields(unknownFields);
    }
  }
  
  private static final Text DEFAULT_INSTANCE = new Text();
  
  public static Text getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }
  
  private static final Parser<Text> PARSER = (Parser<Text>)new AbstractParser<Text>() {
      public Text parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        Text.Builder builder = Text.newBuilder();
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
  
  public static Parser<Text> parser() {
    return PARSER;
  }
  
  public Parser<Text> getParserForType() {
    return PARSER;
  }
  
  public Text getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }
}
