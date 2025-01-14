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
import com.google.protobuf.LazyStringArrayList;
import com.google.protobuf.LazyStringList;
import com.google.protobuf.Message;
import com.google.protobuf.MessageLite;
import com.google.protobuf.Parser;
import com.google.protobuf.ProtocolStringList;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class LandscapeAreaCommon extends GeneratedMessageV3 implements LandscapeAreaCommonOrBuilder {
  private static final long serialVersionUID = 0L;
  
  public static final int SHOWHEAD_FIELD_NUMBER = 1;
  
  private boolean showHead_;
  
  public static final int SHOWNICKNAME_FIELD_NUMBER = 2;
  
  private boolean showNickname_;
  
  public static final int SHOWFONTCOLOR_FIELD_NUMBER = 3;
  
  private boolean showFontColor_;
  
  public static final int COLORVALUELIST_FIELD_NUMBER = 4;
  
  private LazyStringArrayList colorValueList_;
  
  public static final int COMMENTTYPETAGSLIST_FIELD_NUMBER = 5;
  
  private List<Integer> commentTypeTagsList_;
  
  private LandscapeAreaCommon(GeneratedMessageV3.Builder<?> builder) {
    super(builder);
    this.showHead_ = false;
    this.showNickname_ = false;
    this.showFontColor_ = false;
    this
      
      .colorValueList_ = LazyStringArrayList.emptyList();
    this.memoizedIsInitialized = -1;
  }
  
  private LandscapeAreaCommon() {
    this.showHead_ = false;
    this.showNickname_ = false;
    this.showFontColor_ = false;
    this.colorValueList_ = LazyStringArrayList.emptyList();
    this.memoizedIsInitialized = -1;
    this.colorValueList_ = LazyStringArrayList.emptyList();
    this.commentTypeTagsList_ = Collections.emptyList();
  }
  
  protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
    return new LandscapeAreaCommon();
  }
  
  public static final Descriptors.Descriptor getDescriptor() {
    return Douyin.internal_static_LandscapeAreaCommon_descriptor;
  }
  
  protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
    return Douyin.internal_static_LandscapeAreaCommon_fieldAccessorTable.ensureFieldAccessorsInitialized(LandscapeAreaCommon.class, Builder.class);
  }
  
  public boolean getShowHead() {
    return this.showHead_;
  }
  
  public boolean getShowNickname() {
    return this.showNickname_;
  }
  
  public boolean getShowFontColor() {
    return this.showFontColor_;
  }
  
  public ProtocolStringList getColorValueListList() {
    return (ProtocolStringList)this.colorValueList_;
  }
  
  public int getColorValueListCount() {
    return this.colorValueList_.size();
  }
  
  public String getColorValueList(int index) {
    return this.colorValueList_.get(index);
  }
  
  public ByteString getColorValueListBytes(int index) {
    return this.colorValueList_.getByteString(index);
  }
  
  private static final Internal.ListAdapter.Converter<Integer, CommentTypeTag> commentTypeTagsList_converter_ = new Internal.ListAdapter.Converter<Integer, CommentTypeTag>() {
      public CommentTypeTag convert(Integer from) {
        CommentTypeTag result = CommentTypeTag.forNumber(from.intValue());
        return (result == null) ? CommentTypeTag.UNRECOGNIZED : result;
      }
    };
  
  private int commentTypeTagsListMemoizedSerializedSize;
  
  private byte memoizedIsInitialized;
  
  public List<CommentTypeTag> getCommentTypeTagsListList() {
    return (List<CommentTypeTag>)new Internal.ListAdapter(this.commentTypeTagsList_, commentTypeTagsList_converter_);
  }
  
  public int getCommentTypeTagsListCount() {
    return this.commentTypeTagsList_.size();
  }
  
  public CommentTypeTag getCommentTypeTagsList(int index) {
    return (CommentTypeTag)commentTypeTagsList_converter_.convert(this.commentTypeTagsList_.get(index));
  }
  
  public List<Integer> getCommentTypeTagsListValueList() {
    return this.commentTypeTagsList_;
  }
  
  public int getCommentTypeTagsListValue(int index) {
    return ((Integer)this.commentTypeTagsList_.get(index)).intValue();
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
    getSerializedSize();
    if (this.showHead_)
      output.writeBool(1, this.showHead_); 
    if (this.showNickname_)
      output.writeBool(2, this.showNickname_); 
    if (this.showFontColor_)
      output.writeBool(3, this.showFontColor_); 
    int i;
    for (i = 0; i < this.colorValueList_.size(); i++)
      GeneratedMessageV3.writeString(output, 4, this.colorValueList_.getRaw(i)); 
    if (getCommentTypeTagsListList().size() > 0) {
      output.writeUInt32NoTag(42);
      output.writeUInt32NoTag(this.commentTypeTagsListMemoizedSerializedSize);
    } 
    for (i = 0; i < this.commentTypeTagsList_.size(); i++)
      output.writeEnumNoTag(((Integer)this.commentTypeTagsList_.get(i)).intValue()); 
    getUnknownFields().writeTo(output);
  }
  
  public int getSerializedSize() {
    int size = this.memoizedSize;
    if (size != -1)
      return size; 
    size = 0;
    if (this.showHead_)
      size += 
        CodedOutputStream.computeBoolSize(1, this.showHead_); 
    if (this.showNickname_)
      size += 
        CodedOutputStream.computeBoolSize(2, this.showNickname_); 
    if (this.showFontColor_)
      size += 
        CodedOutputStream.computeBoolSize(3, this.showFontColor_); 
    int dataSize = 0;
    int i;
    for (i = 0; i < this.colorValueList_.size(); i++)
      dataSize += computeStringSizeNoTag(this.colorValueList_.getRaw(i)); 
    size += dataSize;
    size += 1 * getColorValueListList().size();
    dataSize = 0;
    for (i = 0; i < this.commentTypeTagsList_.size(); i++)
      dataSize += 
        CodedOutputStream.computeEnumSizeNoTag(((Integer)this.commentTypeTagsList_.get(i)).intValue()); 
    size += dataSize;
    if (!getCommentTypeTagsListList().isEmpty()) {
      size++;
      size += 
        CodedOutputStream.computeUInt32SizeNoTag(dataSize);
    } 
    this.commentTypeTagsListMemoizedSerializedSize = dataSize;
    size += getUnknownFields().getSerializedSize();
    this.memoizedSize = size;
    return size;
  }
  
  public boolean equals(Object obj) {
    if (obj == this)
      return true; 
    if (!(obj instanceof LandscapeAreaCommon))
      return super.equals(obj); 
    LandscapeAreaCommon other = (LandscapeAreaCommon)obj;
    if (getShowHead() != other
      .getShowHead())
      return false; 
    if (getShowNickname() != other
      .getShowNickname())
      return false; 
    if (getShowFontColor() != other
      .getShowFontColor())
      return false; 
    if (!getColorValueListList().equals(other.getColorValueListList()))
      return false; 
    if (!this.commentTypeTagsList_.equals(other.commentTypeTagsList_))
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
        getShowHead());
    hash = 37 * hash + 2;
    hash = 53 * hash + Internal.hashBoolean(
        getShowNickname());
    hash = 37 * hash + 3;
    hash = 53 * hash + Internal.hashBoolean(
        getShowFontColor());
    if (getColorValueListCount() > 0) {
      hash = 37 * hash + 4;
      hash = 53 * hash + getColorValueListList().hashCode();
    } 
    if (getCommentTypeTagsListCount() > 0) {
      hash = 37 * hash + 5;
      hash = 53 * hash + this.commentTypeTagsList_.hashCode();
    } 
    hash = 29 * hash + getUnknownFields().hashCode();
    this.memoizedHashCode = hash;
    return hash;
  }
  
  public static LandscapeAreaCommon parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
    return (LandscapeAreaCommon)PARSER.parseFrom(data);
  }
  
  public static LandscapeAreaCommon parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (LandscapeAreaCommon)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static LandscapeAreaCommon parseFrom(ByteString data) throws InvalidProtocolBufferException {
    return (LandscapeAreaCommon)PARSER.parseFrom(data);
  }
  
  public static LandscapeAreaCommon parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (LandscapeAreaCommon)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static LandscapeAreaCommon parseFrom(byte[] data) throws InvalidProtocolBufferException {
    return (LandscapeAreaCommon)PARSER.parseFrom(data);
  }
  
  public static LandscapeAreaCommon parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (LandscapeAreaCommon)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static LandscapeAreaCommon parseFrom(InputStream input) throws IOException {
    return 
      (LandscapeAreaCommon)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static LandscapeAreaCommon parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (LandscapeAreaCommon)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static LandscapeAreaCommon parseDelimitedFrom(InputStream input) throws IOException {
    return 
      (LandscapeAreaCommon)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
  }
  
  public static LandscapeAreaCommon parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (LandscapeAreaCommon)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static LandscapeAreaCommon parseFrom(CodedInputStream input) throws IOException {
    return 
      (LandscapeAreaCommon)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static LandscapeAreaCommon parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (LandscapeAreaCommon)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public Builder newBuilderForType() {
    return newBuilder();
  }
  
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  
  public static Builder newBuilder(LandscapeAreaCommon prototype) {
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
  
  public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements LandscapeAreaCommonOrBuilder {
    private int bitField0_;
    
    private boolean showHead_;
    
    private boolean showNickname_;
    
    private boolean showFontColor_;
    
    private LazyStringArrayList colorValueList_;
    
    private List<Integer> commentTypeTagsList_;
    
    public static final Descriptors.Descriptor getDescriptor() {
      return Douyin.internal_static_LandscapeAreaCommon_descriptor;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return Douyin.internal_static_LandscapeAreaCommon_fieldAccessorTable
        .ensureFieldAccessorsInitialized(LandscapeAreaCommon.class, Builder.class);
    }
    
    private Builder() {
      this
        .colorValueList_ = LazyStringArrayList.emptyList();
      this
        .commentTypeTagsList_ = Collections.emptyList();
    }
    
    private Builder(GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      this.colorValueList_ = LazyStringArrayList.emptyList();
      this.commentTypeTagsList_ = Collections.emptyList();
    }
    
    public Builder clear() {
      super.clear();
      this.bitField0_ = 0;
      this.showHead_ = false;
      this.showNickname_ = false;
      this.showFontColor_ = false;
      this.colorValueList_ = LazyStringArrayList.emptyList();
      this.commentTypeTagsList_ = Collections.emptyList();
      this.bitField0_ &= 0xFFFFFFEF;
      return this;
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
      return Douyin.internal_static_LandscapeAreaCommon_descriptor;
    }
    
    public LandscapeAreaCommon getDefaultInstanceForType() {
      return LandscapeAreaCommon.getDefaultInstance();
    }
    
    public LandscapeAreaCommon build() {
      LandscapeAreaCommon result = buildPartial();
      if (!result.isInitialized())
        throw newUninitializedMessageException(result); 
      return result;
    }
    
    public LandscapeAreaCommon buildPartial() {
      LandscapeAreaCommon result = new LandscapeAreaCommon(this);
      buildPartialRepeatedFields(result);
      if (this.bitField0_ != 0)
        buildPartial0(result); 
      onBuilt();
      return result;
    }
    
    private void buildPartialRepeatedFields(LandscapeAreaCommon result) {
      if ((this.bitField0_ & 0x10) != 0) {
        this.commentTypeTagsList_ = Collections.unmodifiableList(this.commentTypeTagsList_);
        this.bitField0_ &= 0xFFFFFFEF;
      } 
      result.commentTypeTagsList_ = this.commentTypeTagsList_;
    }
    
    private void buildPartial0(LandscapeAreaCommon result) {
      int from_bitField0_ = this.bitField0_;
      if ((from_bitField0_ & 0x1) != 0)
        result.showHead_ = this.showHead_; 
      if ((from_bitField0_ & 0x2) != 0)
        result.showNickname_ = this.showNickname_; 
      if ((from_bitField0_ & 0x4) != 0)
        result.showFontColor_ = this.showFontColor_; 
      if ((from_bitField0_ & 0x8) != 0) {
        this.colorValueList_.makeImmutable();
        result.colorValueList_ = this.colorValueList_;
      } 
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
      if (other instanceof LandscapeAreaCommon)
        return mergeFrom((LandscapeAreaCommon)other); 
      super.mergeFrom(other);
      return this;
    }
    
    public Builder mergeFrom(LandscapeAreaCommon other) {
      if (other == LandscapeAreaCommon.getDefaultInstance())
        return this; 
      if (other.getShowHead())
        setShowHead(other.getShowHead()); 
      if (other.getShowNickname())
        setShowNickname(other.getShowNickname()); 
      if (other.getShowFontColor())
        setShowFontColor(other.getShowFontColor()); 
      if (!other.colorValueList_.isEmpty()) {
        if (this.colorValueList_.isEmpty()) {
          this.colorValueList_ = other.colorValueList_;
          this.bitField0_ |= 0x8;
        } else {
          ensureColorValueListIsMutable();
          this.colorValueList_.addAll((Collection)other.colorValueList_);
        } 
        onChanged();
      } 
      if (!other.commentTypeTagsList_.isEmpty()) {
        if (this.commentTypeTagsList_.isEmpty()) {
          this.commentTypeTagsList_ = other.commentTypeTagsList_;
          this.bitField0_ &= 0xFFFFFFEF;
        } else {
          ensureCommentTypeTagsListIsMutable();
          this.commentTypeTagsList_.addAll(other.commentTypeTagsList_);
        } 
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
          String s;
          int tmpRaw, length, oldLimit, tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              continue;
            case 8:
              this.showHead_ = input.readBool();
              this.bitField0_ |= 0x1;
              continue;
            case 16:
              this.showNickname_ = input.readBool();
              this.bitField0_ |= 0x2;
              continue;
            case 24:
              this.showFontColor_ = input.readBool();
              this.bitField0_ |= 0x4;
              continue;
            case 34:
              s = input.readStringRequireUtf8();
              ensureColorValueListIsMutable();
              this.colorValueList_.add(s);
              continue;
            case 40:
              tmpRaw = input.readEnum();
              ensureCommentTypeTagsListIsMutable();
              this.commentTypeTagsList_.add(Integer.valueOf(tmpRaw));
              continue;
            case 42:
              length = input.readRawVarint32();
              oldLimit = input.pushLimit(length);
              while (input.getBytesUntilLimit() > 0) {
                int i = input.readEnum();
                ensureCommentTypeTagsListIsMutable();
                this.commentTypeTagsList_.add(Integer.valueOf(i));
              } 
              input.popLimit(oldLimit);
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
    
    public boolean getShowHead() {
      return this.showHead_;
    }
    
    public Builder setShowHead(boolean value) {
      this.showHead_ = value;
      this.bitField0_ |= 0x1;
      onChanged();
      return this;
    }
    
    public Builder clearShowHead() {
      this.bitField0_ &= 0xFFFFFFFE;
      this.showHead_ = false;
      onChanged();
      return this;
    }
    
    public boolean getShowNickname() {
      return this.showNickname_;
    }
    
    public Builder setShowNickname(boolean value) {
      this.showNickname_ = value;
      this.bitField0_ |= 0x2;
      onChanged();
      return this;
    }
    
    public Builder clearShowNickname() {
      this.bitField0_ &= 0xFFFFFFFD;
      this.showNickname_ = false;
      onChanged();
      return this;
    }
    
    public boolean getShowFontColor() {
      return this.showFontColor_;
    }
    
    public Builder setShowFontColor(boolean value) {
      this.showFontColor_ = value;
      this.bitField0_ |= 0x4;
      onChanged();
      return this;
    }
    
    public Builder clearShowFontColor() {
      this.bitField0_ &= 0xFFFFFFFB;
      this.showFontColor_ = false;
      onChanged();
      return this;
    }
    
    private void ensureColorValueListIsMutable() {
      if (!this.colorValueList_.isModifiable())
        this.colorValueList_ = new LazyStringArrayList((LazyStringList)this.colorValueList_); 
      this.bitField0_ |= 0x8;
    }
    
    public ProtocolStringList getColorValueListList() {
      this.colorValueList_.makeImmutable();
      return (ProtocolStringList)this.colorValueList_;
    }
    
    public int getColorValueListCount() {
      return this.colorValueList_.size();
    }
    
    public String getColorValueList(int index) {
      return this.colorValueList_.get(index);
    }
    
    public ByteString getColorValueListBytes(int index) {
      return this.colorValueList_.getByteString(index);
    }
    
    public Builder setColorValueList(int index, String value) {
      if (value == null)
        throw new NullPointerException(); 
      ensureColorValueListIsMutable();
      this.colorValueList_.set(index, value);
      this.bitField0_ |= 0x8;
      onChanged();
      return this;
    }
    
    public Builder addColorValueList(String value) {
      if (value == null)
        throw new NullPointerException(); 
      ensureColorValueListIsMutable();
      this.colorValueList_.add(value);
      this.bitField0_ |= 0x8;
      onChanged();
      return this;
    }
    
    public Builder addAllColorValueList(Iterable<String> values) {
      ensureColorValueListIsMutable();
      AbstractMessageLite.Builder.addAll(values, (List)this.colorValueList_);
      this.bitField0_ |= 0x8;
      onChanged();
      return this;
    }
    
    public Builder clearColorValueList() {
      this.colorValueList_ = LazyStringArrayList.emptyList();
      this.bitField0_ &= 0xFFFFFFF7;
      onChanged();
      return this;
    }
    
    public Builder addColorValueListBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      LandscapeAreaCommon.checkByteStringIsUtf8(value);
      ensureColorValueListIsMutable();
      this.colorValueList_.add(value);
      this.bitField0_ |= 0x8;
      onChanged();
      return this;
    }
    
    private void ensureCommentTypeTagsListIsMutable() {
      if ((this.bitField0_ & 0x10) == 0) {
        this.commentTypeTagsList_ = new ArrayList<>(this.commentTypeTagsList_);
        this.bitField0_ |= 0x10;
      } 
    }
    
    public List<CommentTypeTag> getCommentTypeTagsListList() {
      return (List<CommentTypeTag>)new Internal.ListAdapter(this.commentTypeTagsList_, LandscapeAreaCommon
          .commentTypeTagsList_converter_);
    }
    
    public int getCommentTypeTagsListCount() {
      return this.commentTypeTagsList_.size();
    }
    
    public CommentTypeTag getCommentTypeTagsList(int index) {
      return (CommentTypeTag)LandscapeAreaCommon.commentTypeTagsList_converter_.convert(this.commentTypeTagsList_.get(index));
    }
    
    public Builder setCommentTypeTagsList(int index, CommentTypeTag value) {
      if (value == null)
        throw new NullPointerException(); 
      ensureCommentTypeTagsListIsMutable();
      this.commentTypeTagsList_.set(index, Integer.valueOf(value.getNumber()));
      onChanged();
      return this;
    }
    
    public Builder addCommentTypeTagsList(CommentTypeTag value) {
      if (value == null)
        throw new NullPointerException(); 
      ensureCommentTypeTagsListIsMutable();
      this.commentTypeTagsList_.add(Integer.valueOf(value.getNumber()));
      onChanged();
      return this;
    }
    
    public Builder addAllCommentTypeTagsList(Iterable<? extends CommentTypeTag> values) {
      ensureCommentTypeTagsListIsMutable();
      for (CommentTypeTag value : values)
        this.commentTypeTagsList_.add(Integer.valueOf(value.getNumber())); 
      onChanged();
      return this;
    }
    
    public Builder clearCommentTypeTagsList() {
      this.commentTypeTagsList_ = Collections.emptyList();
      this.bitField0_ &= 0xFFFFFFEF;
      onChanged();
      return this;
    }
    
    public List<Integer> getCommentTypeTagsListValueList() {
      return Collections.unmodifiableList(this.commentTypeTagsList_);
    }
    
    public int getCommentTypeTagsListValue(int index) {
      return ((Integer)this.commentTypeTagsList_.get(index)).intValue();
    }
    
    public Builder setCommentTypeTagsListValue(int index, int value) {
      ensureCommentTypeTagsListIsMutable();
      this.commentTypeTagsList_.set(index, Integer.valueOf(value));
      onChanged();
      return this;
    }
    
    public Builder addCommentTypeTagsListValue(int value) {
      ensureCommentTypeTagsListIsMutable();
      this.commentTypeTagsList_.add(Integer.valueOf(value));
      onChanged();
      return this;
    }
    
    public Builder addAllCommentTypeTagsListValue(Iterable<Integer> values) {
      ensureCommentTypeTagsListIsMutable();
      for (Iterator<Integer> iterator = values.iterator(); iterator.hasNext(); ) {
        int value = ((Integer)iterator.next()).intValue();
        this.commentTypeTagsList_.add(Integer.valueOf(value));
      } 
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
  
  private static final LandscapeAreaCommon DEFAULT_INSTANCE = new LandscapeAreaCommon();
  
  public static LandscapeAreaCommon getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }
  
  private static final Parser<LandscapeAreaCommon> PARSER = (Parser<LandscapeAreaCommon>)new AbstractParser<LandscapeAreaCommon>() {
      public LandscapeAreaCommon parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        LandscapeAreaCommon.Builder builder = LandscapeAreaCommon.newBuilder();
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
  
  public static Parser<LandscapeAreaCommon> parser() {
    return PARSER;
  }
  
  public Parser<LandscapeAreaCommon> getParserForType() {
    return PARSER;
  }
  
  public LandscapeAreaCommon getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }
}
