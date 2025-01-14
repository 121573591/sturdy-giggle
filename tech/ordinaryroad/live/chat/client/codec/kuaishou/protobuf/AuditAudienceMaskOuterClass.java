package tech.ordinaryroad.live.chat.client.codec.kuaishou.protobuf;

import com.google.protobuf.AbstractMessage;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class AuditAudienceMaskOuterClass {
  private static final Descriptors.Descriptor internal_static_AuditAudienceMask_descriptor;
  
  private static final GeneratedMessageV3.FieldAccessorTable internal_static_AuditAudienceMask_fieldAccessorTable;
  
  private static Descriptors.FileDescriptor descriptor;
  
  public static void registerAllExtensions(ExtensionRegistryLite registry) {}
  
  public static void registerAllExtensions(ExtensionRegistry registry) {
    registerAllExtensions((ExtensionRegistryLite)registry);
  }
  
  public static final class AuditAudienceMask extends GeneratedMessageV3 implements AuditAudienceMaskOrBuilder {
    private static final long serialVersionUID = 0L;
    
    public static final int ICONCDNNODEVIEW_FIELD_NUMBER = 1;
    
    private List<LiveCdnNodeViewOuterClass.LiveCdnNodeView> iconCdnNodeView_;
    
    public static final int TITLE_FIELD_NUMBER = 2;
    
    private volatile Object title_;
    
    public static final int DETAIL_FIELD_NUMBER = 3;
    
    private volatile Object detail_;
    
    private byte memoizedIsInitialized;
    
    private AuditAudienceMask(GeneratedMessageV3.Builder<?> builder) {
      super(builder);
      this.title_ = "";
      this.detail_ = "";
      this.memoizedIsInitialized = -1;
    }
    
    private AuditAudienceMask() {
      this.title_ = "";
      this.detail_ = "";
      this.memoizedIsInitialized = -1;
      this.iconCdnNodeView_ = Collections.emptyList();
      this.title_ = "";
      this.detail_ = "";
    }
    
    protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
      return new AuditAudienceMask();
    }
    
    public static final Descriptors.Descriptor getDescriptor() {
      return AuditAudienceMaskOuterClass.internal_static_AuditAudienceMask_descriptor;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return AuditAudienceMaskOuterClass.internal_static_AuditAudienceMask_fieldAccessorTable.ensureFieldAccessorsInitialized(AuditAudienceMask.class, Builder.class);
    }
    
    public List<LiveCdnNodeViewOuterClass.LiveCdnNodeView> getIconCdnNodeViewList() {
      return this.iconCdnNodeView_;
    }
    
    public List<? extends LiveCdnNodeViewOuterClass.LiveCdnNodeViewOrBuilder> getIconCdnNodeViewOrBuilderList() {
      return (List)this.iconCdnNodeView_;
    }
    
    public int getIconCdnNodeViewCount() {
      return this.iconCdnNodeView_.size();
    }
    
    public LiveCdnNodeViewOuterClass.LiveCdnNodeView getIconCdnNodeView(int index) {
      return this.iconCdnNodeView_.get(index);
    }
    
    public LiveCdnNodeViewOuterClass.LiveCdnNodeViewOrBuilder getIconCdnNodeViewOrBuilder(int index) {
      return this.iconCdnNodeView_.get(index);
    }
    
    public String getTitle() {
      Object ref = this.title_;
      if (ref instanceof String)
        return (String)ref; 
      ByteString bs = (ByteString)ref;
      String s = bs.toStringUtf8();
      this.title_ = s;
      return s;
    }
    
    public ByteString getTitleBytes() {
      Object ref = this.title_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.title_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public String getDetail() {
      Object ref = this.detail_;
      if (ref instanceof String)
        return (String)ref; 
      ByteString bs = (ByteString)ref;
      String s = bs.toStringUtf8();
      this.detail_ = s;
      return s;
    }
    
    public ByteString getDetailBytes() {
      Object ref = this.detail_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.detail_ = b;
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
      for (int i = 0; i < this.iconCdnNodeView_.size(); i++)
        output.writeMessage(1, (MessageLite)this.iconCdnNodeView_.get(i)); 
      if (!GeneratedMessageV3.isStringEmpty(this.title_))
        GeneratedMessageV3.writeString(output, 2, this.title_); 
      if (!GeneratedMessageV3.isStringEmpty(this.detail_))
        GeneratedMessageV3.writeString(output, 3, this.detail_); 
      getUnknownFields().writeTo(output);
    }
    
    public int getSerializedSize() {
      int size = this.memoizedSize;
      if (size != -1)
        return size; 
      size = 0;
      for (int i = 0; i < this.iconCdnNodeView_.size(); i++)
        size += 
          CodedOutputStream.computeMessageSize(1, (MessageLite)this.iconCdnNodeView_.get(i)); 
      if (!GeneratedMessageV3.isStringEmpty(this.title_))
        size += GeneratedMessageV3.computeStringSize(2, this.title_); 
      if (!GeneratedMessageV3.isStringEmpty(this.detail_))
        size += GeneratedMessageV3.computeStringSize(3, this.detail_); 
      size += getUnknownFields().getSerializedSize();
      this.memoizedSize = size;
      return size;
    }
    
    public boolean equals(Object obj) {
      if (obj == this)
        return true; 
      if (!(obj instanceof AuditAudienceMask))
        return super.equals(obj); 
      AuditAudienceMask other = (AuditAudienceMask)obj;
      if (!getIconCdnNodeViewList().equals(other.getIconCdnNodeViewList()))
        return false; 
      if (!getTitle().equals(other.getTitle()))
        return false; 
      if (!getDetail().equals(other.getDetail()))
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
      if (getIconCdnNodeViewCount() > 0) {
        hash = 37 * hash + 1;
        hash = 53 * hash + getIconCdnNodeViewList().hashCode();
      } 
      hash = 37 * hash + 2;
      hash = 53 * hash + getTitle().hashCode();
      hash = 37 * hash + 3;
      hash = 53 * hash + getDetail().hashCode();
      hash = 29 * hash + getUnknownFields().hashCode();
      this.memoizedHashCode = hash;
      return hash;
    }
    
    public static AuditAudienceMask parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (AuditAudienceMask)PARSER.parseFrom(data);
    }
    
    public static AuditAudienceMask parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (AuditAudienceMask)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static AuditAudienceMask parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (AuditAudienceMask)PARSER.parseFrom(data);
    }
    
    public static AuditAudienceMask parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (AuditAudienceMask)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static AuditAudienceMask parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (AuditAudienceMask)PARSER.parseFrom(data);
    }
    
    public static AuditAudienceMask parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (AuditAudienceMask)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static AuditAudienceMask parseFrom(InputStream input) throws IOException {
      return 
        (AuditAudienceMask)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static AuditAudienceMask parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (AuditAudienceMask)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static AuditAudienceMask parseDelimitedFrom(InputStream input) throws IOException {
      return 
        (AuditAudienceMask)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }
    
    public static AuditAudienceMask parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (AuditAudienceMask)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static AuditAudienceMask parseFrom(CodedInputStream input) throws IOException {
      return 
        (AuditAudienceMask)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static AuditAudienceMask parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (AuditAudienceMask)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public Builder newBuilderForType() {
      return newBuilder();
    }
    
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    
    public static Builder newBuilder(AuditAudienceMask prototype) {
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
    
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements AuditAudienceMaskOuterClass.AuditAudienceMaskOrBuilder {
      private int bitField0_;
      
      private List<LiveCdnNodeViewOuterClass.LiveCdnNodeView> iconCdnNodeView_;
      
      private RepeatedFieldBuilderV3<LiveCdnNodeViewOuterClass.LiveCdnNodeView, LiveCdnNodeViewOuterClass.LiveCdnNodeView.Builder, LiveCdnNodeViewOuterClass.LiveCdnNodeViewOrBuilder> iconCdnNodeViewBuilder_;
      
      private Object title_;
      
      private Object detail_;
      
      public static final Descriptors.Descriptor getDescriptor() {
        return AuditAudienceMaskOuterClass.internal_static_AuditAudienceMask_descriptor;
      }
      
      protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return AuditAudienceMaskOuterClass.internal_static_AuditAudienceMask_fieldAccessorTable
          .ensureFieldAccessorsInitialized(AuditAudienceMaskOuterClass.AuditAudienceMask.class, Builder.class);
      }
      
      private Builder() {
        this
          .iconCdnNodeView_ = Collections.emptyList();
        this.title_ = "";
        this.detail_ = "";
      }
      
      private Builder(GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        this.iconCdnNodeView_ = Collections.emptyList();
        this.title_ = "";
        this.detail_ = "";
      }
      
      public Builder clear() {
        super.clear();
        this.bitField0_ = 0;
        if (this.iconCdnNodeViewBuilder_ == null) {
          this.iconCdnNodeView_ = Collections.emptyList();
        } else {
          this.iconCdnNodeView_ = null;
          this.iconCdnNodeViewBuilder_.clear();
        } 
        this.bitField0_ &= 0xFFFFFFFE;
        this.title_ = "";
        this.detail_ = "";
        return this;
      }
      
      public Descriptors.Descriptor getDescriptorForType() {
        return AuditAudienceMaskOuterClass.internal_static_AuditAudienceMask_descriptor;
      }
      
      public AuditAudienceMaskOuterClass.AuditAudienceMask getDefaultInstanceForType() {
        return AuditAudienceMaskOuterClass.AuditAudienceMask.getDefaultInstance();
      }
      
      public AuditAudienceMaskOuterClass.AuditAudienceMask build() {
        AuditAudienceMaskOuterClass.AuditAudienceMask result = buildPartial();
        if (!result.isInitialized())
          throw newUninitializedMessageException(result); 
        return result;
      }
      
      public AuditAudienceMaskOuterClass.AuditAudienceMask buildPartial() {
        AuditAudienceMaskOuterClass.AuditAudienceMask result = new AuditAudienceMaskOuterClass.AuditAudienceMask(this);
        buildPartialRepeatedFields(result);
        if (this.bitField0_ != 0)
          buildPartial0(result); 
        onBuilt();
        return result;
      }
      
      private void buildPartialRepeatedFields(AuditAudienceMaskOuterClass.AuditAudienceMask result) {
        if (this.iconCdnNodeViewBuilder_ == null) {
          if ((this.bitField0_ & 0x1) != 0) {
            this.iconCdnNodeView_ = Collections.unmodifiableList(this.iconCdnNodeView_);
            this.bitField0_ &= 0xFFFFFFFE;
          } 
          result.iconCdnNodeView_ = this.iconCdnNodeView_;
        } else {
          result.iconCdnNodeView_ = this.iconCdnNodeViewBuilder_.build();
        } 
      }
      
      private void buildPartial0(AuditAudienceMaskOuterClass.AuditAudienceMask result) {
        int from_bitField0_ = this.bitField0_;
        if ((from_bitField0_ & 0x2) != 0)
          result.title_ = this.title_; 
        if ((from_bitField0_ & 0x4) != 0)
          result.detail_ = this.detail_; 
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
        if (other instanceof AuditAudienceMaskOuterClass.AuditAudienceMask)
          return mergeFrom((AuditAudienceMaskOuterClass.AuditAudienceMask)other); 
        super.mergeFrom(other);
        return this;
      }
      
      public Builder mergeFrom(AuditAudienceMaskOuterClass.AuditAudienceMask other) {
        if (other == AuditAudienceMaskOuterClass.AuditAudienceMask.getDefaultInstance())
          return this; 
        if (this.iconCdnNodeViewBuilder_ == null) {
          if (!other.iconCdnNodeView_.isEmpty()) {
            if (this.iconCdnNodeView_.isEmpty()) {
              this.iconCdnNodeView_ = other.iconCdnNodeView_;
              this.bitField0_ &= 0xFFFFFFFE;
            } else {
              ensureIconCdnNodeViewIsMutable();
              this.iconCdnNodeView_.addAll(other.iconCdnNodeView_);
            } 
            onChanged();
          } 
        } else if (!other.iconCdnNodeView_.isEmpty()) {
          if (this.iconCdnNodeViewBuilder_.isEmpty()) {
            this.iconCdnNodeViewBuilder_.dispose();
            this.iconCdnNodeViewBuilder_ = null;
            this.iconCdnNodeView_ = other.iconCdnNodeView_;
            this.bitField0_ &= 0xFFFFFFFE;
            this.iconCdnNodeViewBuilder_ = AuditAudienceMaskOuterClass.AuditAudienceMask.alwaysUseFieldBuilders ? getIconCdnNodeViewFieldBuilder() : null;
          } else {
            this.iconCdnNodeViewBuilder_.addAllMessages(other.iconCdnNodeView_);
          } 
        } 
        if (!other.getTitle().isEmpty()) {
          this.title_ = other.title_;
          this.bitField0_ |= 0x2;
          onChanged();
        } 
        if (!other.getDetail().isEmpty()) {
          this.detail_ = other.detail_;
          this.bitField0_ |= 0x4;
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
            LiveCdnNodeViewOuterClass.LiveCdnNodeView m;
            int tag = input.readTag();
            switch (tag) {
              case 0:
                done = true;
                continue;
              case 10:
                m = (LiveCdnNodeViewOuterClass.LiveCdnNodeView)input.readMessage(LiveCdnNodeViewOuterClass.LiveCdnNodeView.parser(), extensionRegistry);
                if (this.iconCdnNodeViewBuilder_ == null) {
                  ensureIconCdnNodeViewIsMutable();
                  this.iconCdnNodeView_.add(m);
                  continue;
                } 
                this.iconCdnNodeViewBuilder_.addMessage((AbstractMessage)m);
                continue;
              case 18:
                this.title_ = input.readStringRequireUtf8();
                this.bitField0_ |= 0x2;
                continue;
              case 26:
                this.detail_ = input.readStringRequireUtf8();
                this.bitField0_ |= 0x4;
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
      
      private void ensureIconCdnNodeViewIsMutable() {
        if ((this.bitField0_ & 0x1) == 0) {
          this.iconCdnNodeView_ = new ArrayList<>(this.iconCdnNodeView_);
          this.bitField0_ |= 0x1;
        } 
      }
      
      public List<LiveCdnNodeViewOuterClass.LiveCdnNodeView> getIconCdnNodeViewList() {
        if (this.iconCdnNodeViewBuilder_ == null)
          return Collections.unmodifiableList(this.iconCdnNodeView_); 
        return this.iconCdnNodeViewBuilder_.getMessageList();
      }
      
      public int getIconCdnNodeViewCount() {
        if (this.iconCdnNodeViewBuilder_ == null)
          return this.iconCdnNodeView_.size(); 
        return this.iconCdnNodeViewBuilder_.getCount();
      }
      
      public LiveCdnNodeViewOuterClass.LiveCdnNodeView getIconCdnNodeView(int index) {
        if (this.iconCdnNodeViewBuilder_ == null)
          return this.iconCdnNodeView_.get(index); 
        return (LiveCdnNodeViewOuterClass.LiveCdnNodeView)this.iconCdnNodeViewBuilder_.getMessage(index);
      }
      
      public Builder setIconCdnNodeView(int index, LiveCdnNodeViewOuterClass.LiveCdnNodeView value) {
        if (this.iconCdnNodeViewBuilder_ == null) {
          if (value == null)
            throw new NullPointerException(); 
          ensureIconCdnNodeViewIsMutable();
          this.iconCdnNodeView_.set(index, value);
          onChanged();
        } else {
          this.iconCdnNodeViewBuilder_.setMessage(index, (AbstractMessage)value);
        } 
        return this;
      }
      
      public Builder setIconCdnNodeView(int index, LiveCdnNodeViewOuterClass.LiveCdnNodeView.Builder builderForValue) {
        if (this.iconCdnNodeViewBuilder_ == null) {
          ensureIconCdnNodeViewIsMutable();
          this.iconCdnNodeView_.set(index, builderForValue.build());
          onChanged();
        } else {
          this.iconCdnNodeViewBuilder_.setMessage(index, (AbstractMessage)builderForValue.build());
        } 
        return this;
      }
      
      public Builder addIconCdnNodeView(LiveCdnNodeViewOuterClass.LiveCdnNodeView value) {
        if (this.iconCdnNodeViewBuilder_ == null) {
          if (value == null)
            throw new NullPointerException(); 
          ensureIconCdnNodeViewIsMutable();
          this.iconCdnNodeView_.add(value);
          onChanged();
        } else {
          this.iconCdnNodeViewBuilder_.addMessage((AbstractMessage)value);
        } 
        return this;
      }
      
      public Builder addIconCdnNodeView(int index, LiveCdnNodeViewOuterClass.LiveCdnNodeView value) {
        if (this.iconCdnNodeViewBuilder_ == null) {
          if (value == null)
            throw new NullPointerException(); 
          ensureIconCdnNodeViewIsMutable();
          this.iconCdnNodeView_.add(index, value);
          onChanged();
        } else {
          this.iconCdnNodeViewBuilder_.addMessage(index, (AbstractMessage)value);
        } 
        return this;
      }
      
      public Builder addIconCdnNodeView(LiveCdnNodeViewOuterClass.LiveCdnNodeView.Builder builderForValue) {
        if (this.iconCdnNodeViewBuilder_ == null) {
          ensureIconCdnNodeViewIsMutable();
          this.iconCdnNodeView_.add(builderForValue.build());
          onChanged();
        } else {
          this.iconCdnNodeViewBuilder_.addMessage((AbstractMessage)builderForValue.build());
        } 
        return this;
      }
      
      public Builder addIconCdnNodeView(int index, LiveCdnNodeViewOuterClass.LiveCdnNodeView.Builder builderForValue) {
        if (this.iconCdnNodeViewBuilder_ == null) {
          ensureIconCdnNodeViewIsMutable();
          this.iconCdnNodeView_.add(index, builderForValue.build());
          onChanged();
        } else {
          this.iconCdnNodeViewBuilder_.addMessage(index, (AbstractMessage)builderForValue.build());
        } 
        return this;
      }
      
      public Builder addAllIconCdnNodeView(Iterable<? extends LiveCdnNodeViewOuterClass.LiveCdnNodeView> values) {
        if (this.iconCdnNodeViewBuilder_ == null) {
          ensureIconCdnNodeViewIsMutable();
          AbstractMessageLite.Builder.addAll(values, this.iconCdnNodeView_);
          onChanged();
        } else {
          this.iconCdnNodeViewBuilder_.addAllMessages(values);
        } 
        return this;
      }
      
      public Builder clearIconCdnNodeView() {
        if (this.iconCdnNodeViewBuilder_ == null) {
          this.iconCdnNodeView_ = Collections.emptyList();
          this.bitField0_ &= 0xFFFFFFFE;
          onChanged();
        } else {
          this.iconCdnNodeViewBuilder_.clear();
        } 
        return this;
      }
      
      public Builder removeIconCdnNodeView(int index) {
        if (this.iconCdnNodeViewBuilder_ == null) {
          ensureIconCdnNodeViewIsMutable();
          this.iconCdnNodeView_.remove(index);
          onChanged();
        } else {
          this.iconCdnNodeViewBuilder_.remove(index);
        } 
        return this;
      }
      
      public LiveCdnNodeViewOuterClass.LiveCdnNodeView.Builder getIconCdnNodeViewBuilder(int index) {
        return (LiveCdnNodeViewOuterClass.LiveCdnNodeView.Builder)getIconCdnNodeViewFieldBuilder().getBuilder(index);
      }
      
      public LiveCdnNodeViewOuterClass.LiveCdnNodeViewOrBuilder getIconCdnNodeViewOrBuilder(int index) {
        if (this.iconCdnNodeViewBuilder_ == null)
          return this.iconCdnNodeView_.get(index); 
        return (LiveCdnNodeViewOuterClass.LiveCdnNodeViewOrBuilder)this.iconCdnNodeViewBuilder_.getMessageOrBuilder(index);
      }
      
      public List<? extends LiveCdnNodeViewOuterClass.LiveCdnNodeViewOrBuilder> getIconCdnNodeViewOrBuilderList() {
        if (this.iconCdnNodeViewBuilder_ != null)
          return this.iconCdnNodeViewBuilder_.getMessageOrBuilderList(); 
        return Collections.unmodifiableList((List)this.iconCdnNodeView_);
      }
      
      public LiveCdnNodeViewOuterClass.LiveCdnNodeView.Builder addIconCdnNodeViewBuilder() {
        return (LiveCdnNodeViewOuterClass.LiveCdnNodeView.Builder)getIconCdnNodeViewFieldBuilder().addBuilder((AbstractMessage)LiveCdnNodeViewOuterClass.LiveCdnNodeView.getDefaultInstance());
      }
      
      public LiveCdnNodeViewOuterClass.LiveCdnNodeView.Builder addIconCdnNodeViewBuilder(int index) {
        return (LiveCdnNodeViewOuterClass.LiveCdnNodeView.Builder)getIconCdnNodeViewFieldBuilder().addBuilder(index, (AbstractMessage)LiveCdnNodeViewOuterClass.LiveCdnNodeView.getDefaultInstance());
      }
      
      public List<LiveCdnNodeViewOuterClass.LiveCdnNodeView.Builder> getIconCdnNodeViewBuilderList() {
        return getIconCdnNodeViewFieldBuilder().getBuilderList();
      }
      
      private RepeatedFieldBuilderV3<LiveCdnNodeViewOuterClass.LiveCdnNodeView, LiveCdnNodeViewOuterClass.LiveCdnNodeView.Builder, LiveCdnNodeViewOuterClass.LiveCdnNodeViewOrBuilder> getIconCdnNodeViewFieldBuilder() {
        if (this.iconCdnNodeViewBuilder_ == null) {
          this.iconCdnNodeViewBuilder_ = new RepeatedFieldBuilderV3(this.iconCdnNodeView_, ((this.bitField0_ & 0x1) != 0), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
          this.iconCdnNodeView_ = null;
        } 
        return this.iconCdnNodeViewBuilder_;
      }
      
      public String getTitle() {
        Object ref = this.title_;
        if (!(ref instanceof String)) {
          ByteString bs = (ByteString)ref;
          String s = bs.toStringUtf8();
          this.title_ = s;
          return s;
        } 
        return (String)ref;
      }
      
      public ByteString getTitleBytes() {
        Object ref = this.title_;
        if (ref instanceof String) {
          ByteString b = ByteString.copyFromUtf8((String)ref);
          this.title_ = b;
          return b;
        } 
        return (ByteString)ref;
      }
      
      public Builder setTitle(String value) {
        if (value == null)
          throw new NullPointerException(); 
        this.title_ = value;
        this.bitField0_ |= 0x2;
        onChanged();
        return this;
      }
      
      public Builder clearTitle() {
        this.title_ = AuditAudienceMaskOuterClass.AuditAudienceMask.getDefaultInstance().getTitle();
        this.bitField0_ &= 0xFFFFFFFD;
        onChanged();
        return this;
      }
      
      public Builder setTitleBytes(ByteString value) {
        if (value == null)
          throw new NullPointerException(); 
        AuditAudienceMaskOuterClass.AuditAudienceMask.checkByteStringIsUtf8(value);
        this.title_ = value;
        this.bitField0_ |= 0x2;
        onChanged();
        return this;
      }
      
      public String getDetail() {
        Object ref = this.detail_;
        if (!(ref instanceof String)) {
          ByteString bs = (ByteString)ref;
          String s = bs.toStringUtf8();
          this.detail_ = s;
          return s;
        } 
        return (String)ref;
      }
      
      public ByteString getDetailBytes() {
        Object ref = this.detail_;
        if (ref instanceof String) {
          ByteString b = ByteString.copyFromUtf8((String)ref);
          this.detail_ = b;
          return b;
        } 
        return (ByteString)ref;
      }
      
      public Builder setDetail(String value) {
        if (value == null)
          throw new NullPointerException(); 
        this.detail_ = value;
        this.bitField0_ |= 0x4;
        onChanged();
        return this;
      }
      
      public Builder clearDetail() {
        this.detail_ = AuditAudienceMaskOuterClass.AuditAudienceMask.getDefaultInstance().getDetail();
        this.bitField0_ &= 0xFFFFFFFB;
        onChanged();
        return this;
      }
      
      public Builder setDetailBytes(ByteString value) {
        if (value == null)
          throw new NullPointerException(); 
        AuditAudienceMaskOuterClass.AuditAudienceMask.checkByteStringIsUtf8(value);
        this.detail_ = value;
        this.bitField0_ |= 0x4;
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
    
    private static final AuditAudienceMask DEFAULT_INSTANCE = new AuditAudienceMask();
    
    public static AuditAudienceMask getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }
    
    private static final Parser<AuditAudienceMask> PARSER = (Parser<AuditAudienceMask>)new AbstractParser<AuditAudienceMask>() {
        public AuditAudienceMaskOuterClass.AuditAudienceMask parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
          AuditAudienceMaskOuterClass.AuditAudienceMask.Builder builder = AuditAudienceMaskOuterClass.AuditAudienceMask.newBuilder();
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
    
    public static Parser<AuditAudienceMask> parser() {
      return PARSER;
    }
    
    public Parser<AuditAudienceMask> getParserForType() {
      return PARSER;
    }
    
    public AuditAudienceMask getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }
  }
  
  public static Descriptors.FileDescriptor getDescriptor() {
    return descriptor;
  }
  
  static {
    String[] descriptorData = { "\n\027AuditAudienceMask.proto\032\025LiveCdnNodeView.proto\"]\n\021AuditAudienceMask\022)\n\017iconCdnNodeView\030\001 \003(\0132\020.LiveCdnNodeView\022\r\n\005title\030\002 \001(\t\022\016\n\006detail\030\003 \001(\tB<\n:tech.ordinaryroad.live.chat.client.codec.kuaishou.protobufb\006proto3" };
    descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[] { LiveCdnNodeViewOuterClass.getDescriptor() });
    internal_static_AuditAudienceMask_descriptor = getDescriptor().getMessageTypes().get(0);
    internal_static_AuditAudienceMask_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_AuditAudienceMask_descriptor, new String[] { "IconCdnNodeView", "Title", "Detail" });
    LiveCdnNodeViewOuterClass.getDescriptor();
  }
  
  public static interface AuditAudienceMaskOrBuilder extends MessageOrBuilder {
    List<LiveCdnNodeViewOuterClass.LiveCdnNodeView> getIconCdnNodeViewList();
    
    LiveCdnNodeViewOuterClass.LiveCdnNodeView getIconCdnNodeView(int param1Int);
    
    int getIconCdnNodeViewCount();
    
    List<? extends LiveCdnNodeViewOuterClass.LiveCdnNodeViewOrBuilder> getIconCdnNodeViewOrBuilderList();
    
    LiveCdnNodeViewOuterClass.LiveCdnNodeViewOrBuilder getIconCdnNodeViewOrBuilder(int param1Int);
    
    String getTitle();
    
    ByteString getTitleBytes();
    
    String getDetail();
    
    ByteString getDetailBytes();
  }
}
