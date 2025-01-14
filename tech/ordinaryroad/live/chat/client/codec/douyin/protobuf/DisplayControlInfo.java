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
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class DisplayControlInfo extends GeneratedMessageV3 implements DisplayControlInfoOrBuilder {
  private static final long serialVersionUID = 0L;
  
  public static final int SHOWTEXT_FIELD_NUMBER = 1;
  
  private boolean showText_;
  
  public static final int SHOWICONS_FIELD_NUMBER = 2;
  
  private boolean showIcons_;
  
  private byte memoizedIsInitialized;
  
  private DisplayControlInfo(GeneratedMessageV3.Builder<?> builder) {
    super(builder);
    this.showText_ = false;
    this.showIcons_ = false;
    this.memoizedIsInitialized = -1;
  }
  
  private DisplayControlInfo() {
    this.showText_ = false;
    this.showIcons_ = false;
    this.memoizedIsInitialized = -1;
  }
  
  protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
    return new DisplayControlInfo();
  }
  
  public static final Descriptors.Descriptor getDescriptor() {
    return Douyin.internal_static_DisplayControlInfo_descriptor;
  }
  
  protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
    return Douyin.internal_static_DisplayControlInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(DisplayControlInfo.class, Builder.class);
  }
  
  public boolean getShowText() {
    return this.showText_;
  }
  
  public boolean getShowIcons() {
    return this.showIcons_;
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
    if (this.showText_)
      output.writeBool(1, this.showText_); 
    if (this.showIcons_)
      output.writeBool(2, this.showIcons_); 
    getUnknownFields().writeTo(output);
  }
  
  public int getSerializedSize() {
    int size = this.memoizedSize;
    if (size != -1)
      return size; 
    size = 0;
    if (this.showText_)
      size += 
        CodedOutputStream.computeBoolSize(1, this.showText_); 
    if (this.showIcons_)
      size += 
        CodedOutputStream.computeBoolSize(2, this.showIcons_); 
    size += getUnknownFields().getSerializedSize();
    this.memoizedSize = size;
    return size;
  }
  
  public boolean equals(Object obj) {
    if (obj == this)
      return true; 
    if (!(obj instanceof DisplayControlInfo))
      return super.equals(obj); 
    DisplayControlInfo other = (DisplayControlInfo)obj;
    if (getShowText() != other
      .getShowText())
      return false; 
    if (getShowIcons() != other
      .getShowIcons())
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
    hash = 53 * hash + Internal.hashBoolean(
        getShowText());
    hash = 37 * hash + 2;
    hash = 53 * hash + Internal.hashBoolean(
        getShowIcons());
    hash = 29 * hash + getUnknownFields().hashCode();
    this.memoizedHashCode = hash;
    return hash;
  }
  
  public static DisplayControlInfo parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
    return (DisplayControlInfo)PARSER.parseFrom(data);
  }
  
  public static DisplayControlInfo parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (DisplayControlInfo)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static DisplayControlInfo parseFrom(ByteString data) throws InvalidProtocolBufferException {
    return (DisplayControlInfo)PARSER.parseFrom(data);
  }
  
  public static DisplayControlInfo parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (DisplayControlInfo)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static DisplayControlInfo parseFrom(byte[] data) throws InvalidProtocolBufferException {
    return (DisplayControlInfo)PARSER.parseFrom(data);
  }
  
  public static DisplayControlInfo parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (DisplayControlInfo)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static DisplayControlInfo parseFrom(InputStream input) throws IOException {
    return 
      (DisplayControlInfo)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static DisplayControlInfo parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (DisplayControlInfo)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static DisplayControlInfo parseDelimitedFrom(InputStream input) throws IOException {
    return 
      (DisplayControlInfo)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
  }
  
  public static DisplayControlInfo parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (DisplayControlInfo)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static DisplayControlInfo parseFrom(CodedInputStream input) throws IOException {
    return 
      (DisplayControlInfo)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static DisplayControlInfo parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (DisplayControlInfo)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public Builder newBuilderForType() {
    return newBuilder();
  }
  
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  
  public static Builder newBuilder(DisplayControlInfo prototype) {
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
  
  public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements DisplayControlInfoOrBuilder {
    private int bitField0_;
    
    private boolean showText_;
    
    private boolean showIcons_;
    
    public static final Descriptors.Descriptor getDescriptor() {
      return Douyin.internal_static_DisplayControlInfo_descriptor;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return Douyin.internal_static_DisplayControlInfo_fieldAccessorTable
        .ensureFieldAccessorsInitialized(DisplayControlInfo.class, Builder.class);
    }
    
    private Builder() {}
    
    private Builder(GeneratedMessageV3.BuilderParent parent) {
      super(parent);
    }
    
    public Builder clear() {
      super.clear();
      this.bitField0_ = 0;
      this.showText_ = false;
      this.showIcons_ = false;
      return this;
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
      return Douyin.internal_static_DisplayControlInfo_descriptor;
    }
    
    public DisplayControlInfo getDefaultInstanceForType() {
      return DisplayControlInfo.getDefaultInstance();
    }
    
    public DisplayControlInfo build() {
      DisplayControlInfo result = buildPartial();
      if (!result.isInitialized())
        throw newUninitializedMessageException(result); 
      return result;
    }
    
    public DisplayControlInfo buildPartial() {
      DisplayControlInfo result = new DisplayControlInfo(this);
      if (this.bitField0_ != 0)
        buildPartial0(result); 
      onBuilt();
      return result;
    }
    
    private void buildPartial0(DisplayControlInfo result) {
      int from_bitField0_ = this.bitField0_;
      if ((from_bitField0_ & 0x1) != 0)
        result.showText_ = this.showText_; 
      if ((from_bitField0_ & 0x2) != 0)
        result.showIcons_ = this.showIcons_; 
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
      if (other instanceof DisplayControlInfo)
        return mergeFrom((DisplayControlInfo)other); 
      super.mergeFrom(other);
      return this;
    }
    
    public Builder mergeFrom(DisplayControlInfo other) {
      if (other == DisplayControlInfo.getDefaultInstance())
        return this; 
      if (other.getShowText())
        setShowText(other.getShowText()); 
      if (other.getShowIcons())
        setShowIcons(other.getShowIcons()); 
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
              this.showText_ = input.readBool();
              this.bitField0_ |= 0x1;
              continue;
            case 16:
              this.showIcons_ = input.readBool();
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
    
    public boolean getShowText() {
      return this.showText_;
    }
    
    public Builder setShowText(boolean value) {
      this.showText_ = value;
      this.bitField0_ |= 0x1;
      onChanged();
      return this;
    }
    
    public Builder clearShowText() {
      this.bitField0_ &= 0xFFFFFFFE;
      this.showText_ = false;
      onChanged();
      return this;
    }
    
    public boolean getShowIcons() {
      return this.showIcons_;
    }
    
    public Builder setShowIcons(boolean value) {
      this.showIcons_ = value;
      this.bitField0_ |= 0x2;
      onChanged();
      return this;
    }
    
    public Builder clearShowIcons() {
      this.bitField0_ &= 0xFFFFFFFD;
      this.showIcons_ = false;
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
  
  private static final DisplayControlInfo DEFAULT_INSTANCE = new DisplayControlInfo();
  
  public static DisplayControlInfo getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }
  
  private static final Parser<DisplayControlInfo> PARSER = (Parser<DisplayControlInfo>)new AbstractParser<DisplayControlInfo>() {
      public DisplayControlInfo parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        DisplayControlInfo.Builder builder = DisplayControlInfo.newBuilder();
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
  
  public static Parser<DisplayControlInfo> parser() {
    return PARSER;
  }
  
  public Parser<DisplayControlInfo> getParserForType() {
    return PARSER;
  }
  
  public DisplayControlInfo getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }
}
