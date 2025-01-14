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

public final class GradeIcon extends GeneratedMessageV3 implements GradeIconOrBuilder {
  private static final long serialVersionUID = 0L;
  
  private int bitField0_;
  
  public static final int ICON_FIELD_NUMBER = 1;
  
  private Image icon_;
  
  public static final int ICONDIAMOND_FIELD_NUMBER = 2;
  
  private long iconDiamond_;
  
  public static final int LEVEL_FIELD_NUMBER = 3;
  
  private long level_;
  
  public static final int LEVELSTR_FIELD_NUMBER = 4;
  
  private volatile Object levelStr_;
  
  private byte memoizedIsInitialized;
  
  private GradeIcon(GeneratedMessageV3.Builder<?> builder) {
    super(builder);
    this.iconDiamond_ = 0L;
    this.level_ = 0L;
    this.levelStr_ = "";
    this.memoizedIsInitialized = -1;
  }
  
  private GradeIcon() {
    this.iconDiamond_ = 0L;
    this.level_ = 0L;
    this.levelStr_ = "";
    this.memoizedIsInitialized = -1;
    this.levelStr_ = "";
  }
  
  protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
    return new GradeIcon();
  }
  
  public static final Descriptors.Descriptor getDescriptor() {
    return Douyin.internal_static_GradeIcon_descriptor;
  }
  
  protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
    return Douyin.internal_static_GradeIcon_fieldAccessorTable.ensureFieldAccessorsInitialized(GradeIcon.class, Builder.class);
  }
  
  public boolean hasIcon() {
    return ((this.bitField0_ & 0x1) != 0);
  }
  
  public Image getIcon() {
    return (this.icon_ == null) ? Image.getDefaultInstance() : this.icon_;
  }
  
  public ImageOrBuilder getIconOrBuilder() {
    return (this.icon_ == null) ? Image.getDefaultInstance() : this.icon_;
  }
  
  public long getIconDiamond() {
    return this.iconDiamond_;
  }
  
  public long getLevel() {
    return this.level_;
  }
  
  public String getLevelStr() {
    Object ref = this.levelStr_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.levelStr_ = s;
    return s;
  }
  
  public ByteString getLevelStrBytes() {
    Object ref = this.levelStr_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.levelStr_ = b;
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
    if ((this.bitField0_ & 0x1) != 0)
      output.writeMessage(1, (MessageLite)getIcon()); 
    if (this.iconDiamond_ != 0L)
      output.writeInt64(2, this.iconDiamond_); 
    if (this.level_ != 0L)
      output.writeInt64(3, this.level_); 
    if (!GeneratedMessageV3.isStringEmpty(this.levelStr_))
      GeneratedMessageV3.writeString(output, 4, this.levelStr_); 
    getUnknownFields().writeTo(output);
  }
  
  public int getSerializedSize() {
    int size = this.memoizedSize;
    if (size != -1)
      return size; 
    size = 0;
    if ((this.bitField0_ & 0x1) != 0)
      size += 
        CodedOutputStream.computeMessageSize(1, (MessageLite)getIcon()); 
    if (this.iconDiamond_ != 0L)
      size += 
        CodedOutputStream.computeInt64Size(2, this.iconDiamond_); 
    if (this.level_ != 0L)
      size += 
        CodedOutputStream.computeInt64Size(3, this.level_); 
    if (!GeneratedMessageV3.isStringEmpty(this.levelStr_))
      size += GeneratedMessageV3.computeStringSize(4, this.levelStr_); 
    size += getUnknownFields().getSerializedSize();
    this.memoizedSize = size;
    return size;
  }
  
  public boolean equals(Object obj) {
    if (obj == this)
      return true; 
    if (!(obj instanceof GradeIcon))
      return super.equals(obj); 
    GradeIcon other = (GradeIcon)obj;
    if (hasIcon() != other.hasIcon())
      return false; 
    if (hasIcon() && 
      
      !getIcon().equals(other.getIcon()))
      return false; 
    if (getIconDiamond() != other
      .getIconDiamond())
      return false; 
    if (getLevel() != other
      .getLevel())
      return false; 
    if (!getLevelStr().equals(other.getLevelStr()))
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
    if (hasIcon()) {
      hash = 37 * hash + 1;
      hash = 53 * hash + getIcon().hashCode();
    } 
    hash = 37 * hash + 2;
    hash = 53 * hash + Internal.hashLong(
        getIconDiamond());
    hash = 37 * hash + 3;
    hash = 53 * hash + Internal.hashLong(
        getLevel());
    hash = 37 * hash + 4;
    hash = 53 * hash + getLevelStr().hashCode();
    hash = 29 * hash + getUnknownFields().hashCode();
    this.memoizedHashCode = hash;
    return hash;
  }
  
  public static GradeIcon parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
    return (GradeIcon)PARSER.parseFrom(data);
  }
  
  public static GradeIcon parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (GradeIcon)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static GradeIcon parseFrom(ByteString data) throws InvalidProtocolBufferException {
    return (GradeIcon)PARSER.parseFrom(data);
  }
  
  public static GradeIcon parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (GradeIcon)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static GradeIcon parseFrom(byte[] data) throws InvalidProtocolBufferException {
    return (GradeIcon)PARSER.parseFrom(data);
  }
  
  public static GradeIcon parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (GradeIcon)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static GradeIcon parseFrom(InputStream input) throws IOException {
    return 
      (GradeIcon)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static GradeIcon parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (GradeIcon)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static GradeIcon parseDelimitedFrom(InputStream input) throws IOException {
    return 
      (GradeIcon)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
  }
  
  public static GradeIcon parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (GradeIcon)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static GradeIcon parseFrom(CodedInputStream input) throws IOException {
    return 
      (GradeIcon)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static GradeIcon parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (GradeIcon)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public Builder newBuilderForType() {
    return newBuilder();
  }
  
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  
  public static Builder newBuilder(GradeIcon prototype) {
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
  
  public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements GradeIconOrBuilder {
    private int bitField0_;
    
    private Image icon_;
    
    private SingleFieldBuilderV3<Image, Image.Builder, ImageOrBuilder> iconBuilder_;
    
    private long iconDiamond_;
    
    private long level_;
    
    private Object levelStr_;
    
    public static final Descriptors.Descriptor getDescriptor() {
      return Douyin.internal_static_GradeIcon_descriptor;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return Douyin.internal_static_GradeIcon_fieldAccessorTable
        .ensureFieldAccessorsInitialized(GradeIcon.class, Builder.class);
    }
    
    private Builder() {
      this.levelStr_ = "";
      maybeForceBuilderInitialization();
    }
    
    private Builder(GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      this.levelStr_ = "";
      maybeForceBuilderInitialization();
    }
    
    private void maybeForceBuilderInitialization() {
      if (GradeIcon.alwaysUseFieldBuilders)
        getIconFieldBuilder(); 
    }
    
    public Builder clear() {
      super.clear();
      this.bitField0_ = 0;
      this.icon_ = null;
      if (this.iconBuilder_ != null) {
        this.iconBuilder_.dispose();
        this.iconBuilder_ = null;
      } 
      this.iconDiamond_ = 0L;
      this.level_ = 0L;
      this.levelStr_ = "";
      return this;
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
      return Douyin.internal_static_GradeIcon_descriptor;
    }
    
    public GradeIcon getDefaultInstanceForType() {
      return GradeIcon.getDefaultInstance();
    }
    
    public GradeIcon build() {
      GradeIcon result = buildPartial();
      if (!result.isInitialized())
        throw newUninitializedMessageException(result); 
      return result;
    }
    
    public GradeIcon buildPartial() {
      GradeIcon result = new GradeIcon(this);
      if (this.bitField0_ != 0)
        buildPartial0(result); 
      onBuilt();
      return result;
    }
    
    private void buildPartial0(GradeIcon result) {
      int from_bitField0_ = this.bitField0_;
      int to_bitField0_ = 0;
      if ((from_bitField0_ & 0x1) != 0) {
        result.icon_ = (this.iconBuilder_ == null) ? this.icon_ : (Image)this.iconBuilder_.build();
        to_bitField0_ |= 0x1;
      } 
      if ((from_bitField0_ & 0x2) != 0)
        result.iconDiamond_ = this.iconDiamond_; 
      if ((from_bitField0_ & 0x4) != 0)
        result.level_ = this.level_; 
      if ((from_bitField0_ & 0x8) != 0)
        result.levelStr_ = this.levelStr_; 
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
      if (other instanceof GradeIcon)
        return mergeFrom((GradeIcon)other); 
      super.mergeFrom(other);
      return this;
    }
    
    public Builder mergeFrom(GradeIcon other) {
      if (other == GradeIcon.getDefaultInstance())
        return this; 
      if (other.hasIcon())
        mergeIcon(other.getIcon()); 
      if (other.getIconDiamond() != 0L)
        setIconDiamond(other.getIconDiamond()); 
      if (other.getLevel() != 0L)
        setLevel(other.getLevel()); 
      if (!other.getLevelStr().isEmpty()) {
        this.levelStr_ = other.levelStr_;
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
            case 10:
              input.readMessage((MessageLite.Builder)getIconFieldBuilder().getBuilder(), extensionRegistry);
              this.bitField0_ |= 0x1;
              continue;
            case 16:
              this.iconDiamond_ = input.readInt64();
              this.bitField0_ |= 0x2;
              continue;
            case 24:
              this.level_ = input.readInt64();
              this.bitField0_ |= 0x4;
              continue;
            case 34:
              this.levelStr_ = input.readStringRequireUtf8();
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
    
    public boolean hasIcon() {
      return ((this.bitField0_ & 0x1) != 0);
    }
    
    public Image getIcon() {
      if (this.iconBuilder_ == null)
        return (this.icon_ == null) ? Image.getDefaultInstance() : this.icon_; 
      return (Image)this.iconBuilder_.getMessage();
    }
    
    public Builder setIcon(Image value) {
      if (this.iconBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        this.icon_ = value;
      } else {
        this.iconBuilder_.setMessage((AbstractMessage)value);
      } 
      this.bitField0_ |= 0x1;
      onChanged();
      return this;
    }
    
    public Builder setIcon(Image.Builder builderForValue) {
      if (this.iconBuilder_ == null) {
        this.icon_ = builderForValue.build();
      } else {
        this.iconBuilder_.setMessage((AbstractMessage)builderForValue.build());
      } 
      this.bitField0_ |= 0x1;
      onChanged();
      return this;
    }
    
    public Builder mergeIcon(Image value) {
      if (this.iconBuilder_ == null) {
        if ((this.bitField0_ & 0x1) != 0 && this.icon_ != null && this.icon_ != Image.getDefaultInstance()) {
          getIconBuilder().mergeFrom(value);
        } else {
          this.icon_ = value;
        } 
      } else {
        this.iconBuilder_.mergeFrom((AbstractMessage)value);
      } 
      if (this.icon_ != null) {
        this.bitField0_ |= 0x1;
        onChanged();
      } 
      return this;
    }
    
    public Builder clearIcon() {
      this.bitField0_ &= 0xFFFFFFFE;
      this.icon_ = null;
      if (this.iconBuilder_ != null) {
        this.iconBuilder_.dispose();
        this.iconBuilder_ = null;
      } 
      onChanged();
      return this;
    }
    
    public Image.Builder getIconBuilder() {
      this.bitField0_ |= 0x1;
      onChanged();
      return (Image.Builder)getIconFieldBuilder().getBuilder();
    }
    
    public ImageOrBuilder getIconOrBuilder() {
      if (this.iconBuilder_ != null)
        return (ImageOrBuilder)this.iconBuilder_.getMessageOrBuilder(); 
      return (this.icon_ == null) ? Image.getDefaultInstance() : this.icon_;
    }
    
    private SingleFieldBuilderV3<Image, Image.Builder, ImageOrBuilder> getIconFieldBuilder() {
      if (this.iconBuilder_ == null) {
        this.iconBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getIcon(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.icon_ = null;
      } 
      return this.iconBuilder_;
    }
    
    public long getIconDiamond() {
      return this.iconDiamond_;
    }
    
    public Builder setIconDiamond(long value) {
      this.iconDiamond_ = value;
      this.bitField0_ |= 0x2;
      onChanged();
      return this;
    }
    
    public Builder clearIconDiamond() {
      this.bitField0_ &= 0xFFFFFFFD;
      this.iconDiamond_ = 0L;
      onChanged();
      return this;
    }
    
    public long getLevel() {
      return this.level_;
    }
    
    public Builder setLevel(long value) {
      this.level_ = value;
      this.bitField0_ |= 0x4;
      onChanged();
      return this;
    }
    
    public Builder clearLevel() {
      this.bitField0_ &= 0xFFFFFFFB;
      this.level_ = 0L;
      onChanged();
      return this;
    }
    
    public String getLevelStr() {
      Object ref = this.levelStr_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.levelStr_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getLevelStrBytes() {
      Object ref = this.levelStr_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.levelStr_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setLevelStr(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.levelStr_ = value;
      this.bitField0_ |= 0x8;
      onChanged();
      return this;
    }
    
    public Builder clearLevelStr() {
      this.levelStr_ = GradeIcon.getDefaultInstance().getLevelStr();
      this.bitField0_ &= 0xFFFFFFF7;
      onChanged();
      return this;
    }
    
    public Builder setLevelStrBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      GradeIcon.checkByteStringIsUtf8(value);
      this.levelStr_ = value;
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
  
  private static final GradeIcon DEFAULT_INSTANCE = new GradeIcon();
  
  public static GradeIcon getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }
  
  private static final Parser<GradeIcon> PARSER = (Parser<GradeIcon>)new AbstractParser<GradeIcon>() {
      public GradeIcon parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        GradeIcon.Builder builder = GradeIcon.newBuilder();
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
  
  public static Parser<GradeIcon> parser() {
    return PARSER;
  }
  
  public Parser<GradeIcon> getParserForType() {
    return PARSER;
  }
  
  public GradeIcon getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }
}
