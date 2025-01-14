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
import com.google.protobuf.Internal;
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

public final class GzoneNameplateOuterClass {
  private static final Descriptors.Descriptor internal_static_GzoneNameplate_descriptor;
  
  private static final GeneratedMessageV3.FieldAccessorTable internal_static_GzoneNameplate_fieldAccessorTable;
  
  private static Descriptors.FileDescriptor descriptor;
  
  public static void registerAllExtensions(ExtensionRegistryLite registry) {}
  
  public static void registerAllExtensions(ExtensionRegistry registry) {
    registerAllExtensions((ExtensionRegistryLite)registry);
  }
  
  public static final class GzoneNameplate extends GeneratedMessageV3 implements GzoneNameplateOrBuilder {
    private static final long serialVersionUID = 0L;
    
    public static final int ID_FIELD_NUMBER = 1;
    
    private long id_;
    
    public static final int NAME_FIELD_NUMBER = 2;
    
    private volatile Object name_;
    
    public static final int URLS_FIELD_NUMBER = 3;
    
    private List<PicUrlOuterClass.PicUrl> urls_;
    
    private byte memoizedIsInitialized;
    
    private GzoneNameplate(GeneratedMessageV3.Builder<?> builder) {
      super(builder);
      this.id_ = 0L;
      this.name_ = "";
      this.memoizedIsInitialized = -1;
    }
    
    private GzoneNameplate() {
      this.id_ = 0L;
      this.name_ = "";
      this.memoizedIsInitialized = -1;
      this.name_ = "";
      this.urls_ = Collections.emptyList();
    }
    
    protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
      return new GzoneNameplate();
    }
    
    public static final Descriptors.Descriptor getDescriptor() {
      return GzoneNameplateOuterClass.internal_static_GzoneNameplate_descriptor;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return GzoneNameplateOuterClass.internal_static_GzoneNameplate_fieldAccessorTable.ensureFieldAccessorsInitialized(GzoneNameplate.class, Builder.class);
    }
    
    public long getId() {
      return this.id_;
    }
    
    public String getName() {
      Object ref = this.name_;
      if (ref instanceof String)
        return (String)ref; 
      ByteString bs = (ByteString)ref;
      String s = bs.toStringUtf8();
      this.name_ = s;
      return s;
    }
    
    public ByteString getNameBytes() {
      Object ref = this.name_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.name_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public List<PicUrlOuterClass.PicUrl> getUrlsList() {
      return this.urls_;
    }
    
    public List<? extends PicUrlOuterClass.PicUrlOrBuilder> getUrlsOrBuilderList() {
      return (List)this.urls_;
    }
    
    public int getUrlsCount() {
      return this.urls_.size();
    }
    
    public PicUrlOuterClass.PicUrl getUrls(int index) {
      return this.urls_.get(index);
    }
    
    public PicUrlOuterClass.PicUrlOrBuilder getUrlsOrBuilder(int index) {
      return this.urls_.get(index);
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
      if (this.id_ != 0L)
        output.writeInt64(1, this.id_); 
      if (!GeneratedMessageV3.isStringEmpty(this.name_))
        GeneratedMessageV3.writeString(output, 2, this.name_); 
      for (int i = 0; i < this.urls_.size(); i++)
        output.writeMessage(3, (MessageLite)this.urls_.get(i)); 
      getUnknownFields().writeTo(output);
    }
    
    public int getSerializedSize() {
      int size = this.memoizedSize;
      if (size != -1)
        return size; 
      size = 0;
      if (this.id_ != 0L)
        size += 
          CodedOutputStream.computeInt64Size(1, this.id_); 
      if (!GeneratedMessageV3.isStringEmpty(this.name_))
        size += GeneratedMessageV3.computeStringSize(2, this.name_); 
      for (int i = 0; i < this.urls_.size(); i++)
        size += 
          CodedOutputStream.computeMessageSize(3, (MessageLite)this.urls_.get(i)); 
      size += getUnknownFields().getSerializedSize();
      this.memoizedSize = size;
      return size;
    }
    
    public boolean equals(Object obj) {
      if (obj == this)
        return true; 
      if (!(obj instanceof GzoneNameplate))
        return super.equals(obj); 
      GzoneNameplate other = (GzoneNameplate)obj;
      if (getId() != other
        .getId())
        return false; 
      if (!getName().equals(other.getName()))
        return false; 
      if (!getUrlsList().equals(other.getUrlsList()))
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
          getId());
      hash = 37 * hash + 2;
      hash = 53 * hash + getName().hashCode();
      if (getUrlsCount() > 0) {
        hash = 37 * hash + 3;
        hash = 53 * hash + getUrlsList().hashCode();
      } 
      hash = 29 * hash + getUnknownFields().hashCode();
      this.memoizedHashCode = hash;
      return hash;
    }
    
    public static GzoneNameplate parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (GzoneNameplate)PARSER.parseFrom(data);
    }
    
    public static GzoneNameplate parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (GzoneNameplate)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static GzoneNameplate parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (GzoneNameplate)PARSER.parseFrom(data);
    }
    
    public static GzoneNameplate parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (GzoneNameplate)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static GzoneNameplate parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (GzoneNameplate)PARSER.parseFrom(data);
    }
    
    public static GzoneNameplate parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (GzoneNameplate)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static GzoneNameplate parseFrom(InputStream input) throws IOException {
      return 
        (GzoneNameplate)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static GzoneNameplate parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (GzoneNameplate)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static GzoneNameplate parseDelimitedFrom(InputStream input) throws IOException {
      return 
        (GzoneNameplate)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }
    
    public static GzoneNameplate parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (GzoneNameplate)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static GzoneNameplate parseFrom(CodedInputStream input) throws IOException {
      return 
        (GzoneNameplate)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static GzoneNameplate parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (GzoneNameplate)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public Builder newBuilderForType() {
      return newBuilder();
    }
    
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    
    public static Builder newBuilder(GzoneNameplate prototype) {
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
    
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements GzoneNameplateOuterClass.GzoneNameplateOrBuilder {
      private int bitField0_;
      
      private long id_;
      
      private Object name_;
      
      private List<PicUrlOuterClass.PicUrl> urls_;
      
      private RepeatedFieldBuilderV3<PicUrlOuterClass.PicUrl, PicUrlOuterClass.PicUrl.Builder, PicUrlOuterClass.PicUrlOrBuilder> urlsBuilder_;
      
      public static final Descriptors.Descriptor getDescriptor() {
        return GzoneNameplateOuterClass.internal_static_GzoneNameplate_descriptor;
      }
      
      protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return GzoneNameplateOuterClass.internal_static_GzoneNameplate_fieldAccessorTable
          .ensureFieldAccessorsInitialized(GzoneNameplateOuterClass.GzoneNameplate.class, Builder.class);
      }
      
      private Builder() {
        this.name_ = "";
        this
          .urls_ = Collections.emptyList();
      }
      
      private Builder(GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        this.name_ = "";
        this.urls_ = Collections.emptyList();
      }
      
      public Builder clear() {
        super.clear();
        this.bitField0_ = 0;
        this.id_ = 0L;
        this.name_ = "";
        if (this.urlsBuilder_ == null) {
          this.urls_ = Collections.emptyList();
        } else {
          this.urls_ = null;
          this.urlsBuilder_.clear();
        } 
        this.bitField0_ &= 0xFFFFFFFB;
        return this;
      }
      
      public Descriptors.Descriptor getDescriptorForType() {
        return GzoneNameplateOuterClass.internal_static_GzoneNameplate_descriptor;
      }
      
      public GzoneNameplateOuterClass.GzoneNameplate getDefaultInstanceForType() {
        return GzoneNameplateOuterClass.GzoneNameplate.getDefaultInstance();
      }
      
      public GzoneNameplateOuterClass.GzoneNameplate build() {
        GzoneNameplateOuterClass.GzoneNameplate result = buildPartial();
        if (!result.isInitialized())
          throw newUninitializedMessageException(result); 
        return result;
      }
      
      public GzoneNameplateOuterClass.GzoneNameplate buildPartial() {
        GzoneNameplateOuterClass.GzoneNameplate result = new GzoneNameplateOuterClass.GzoneNameplate(this);
        buildPartialRepeatedFields(result);
        if (this.bitField0_ != 0)
          buildPartial0(result); 
        onBuilt();
        return result;
      }
      
      private void buildPartialRepeatedFields(GzoneNameplateOuterClass.GzoneNameplate result) {
        if (this.urlsBuilder_ == null) {
          if ((this.bitField0_ & 0x4) != 0) {
            this.urls_ = Collections.unmodifiableList(this.urls_);
            this.bitField0_ &= 0xFFFFFFFB;
          } 
          result.urls_ = this.urls_;
        } else {
          result.urls_ = this.urlsBuilder_.build();
        } 
      }
      
      private void buildPartial0(GzoneNameplateOuterClass.GzoneNameplate result) {
        int from_bitField0_ = this.bitField0_;
        if ((from_bitField0_ & 0x1) != 0)
          result.id_ = this.id_; 
        if ((from_bitField0_ & 0x2) != 0)
          result.name_ = this.name_; 
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
        if (other instanceof GzoneNameplateOuterClass.GzoneNameplate)
          return mergeFrom((GzoneNameplateOuterClass.GzoneNameplate)other); 
        super.mergeFrom(other);
        return this;
      }
      
      public Builder mergeFrom(GzoneNameplateOuterClass.GzoneNameplate other) {
        if (other == GzoneNameplateOuterClass.GzoneNameplate.getDefaultInstance())
          return this; 
        if (other.getId() != 0L)
          setId(other.getId()); 
        if (!other.getName().isEmpty()) {
          this.name_ = other.name_;
          this.bitField0_ |= 0x2;
          onChanged();
        } 
        if (this.urlsBuilder_ == null) {
          if (!other.urls_.isEmpty()) {
            if (this.urls_.isEmpty()) {
              this.urls_ = other.urls_;
              this.bitField0_ &= 0xFFFFFFFB;
            } else {
              ensureUrlsIsMutable();
              this.urls_.addAll(other.urls_);
            } 
            onChanged();
          } 
        } else if (!other.urls_.isEmpty()) {
          if (this.urlsBuilder_.isEmpty()) {
            this.urlsBuilder_.dispose();
            this.urlsBuilder_ = null;
            this.urls_ = other.urls_;
            this.bitField0_ &= 0xFFFFFFFB;
            this.urlsBuilder_ = GzoneNameplateOuterClass.GzoneNameplate.alwaysUseFieldBuilders ? getUrlsFieldBuilder() : null;
          } else {
            this.urlsBuilder_.addAllMessages(other.urls_);
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
            PicUrlOuterClass.PicUrl m;
            int tag = input.readTag();
            switch (tag) {
              case 0:
                done = true;
                continue;
              case 8:
                this.id_ = input.readInt64();
                this.bitField0_ |= 0x1;
                continue;
              case 18:
                this.name_ = input.readStringRequireUtf8();
                this.bitField0_ |= 0x2;
                continue;
              case 26:
                m = (PicUrlOuterClass.PicUrl)input.readMessage(PicUrlOuterClass.PicUrl.parser(), extensionRegistry);
                if (this.urlsBuilder_ == null) {
                  ensureUrlsIsMutable();
                  this.urls_.add(m);
                  continue;
                } 
                this.urlsBuilder_.addMessage((AbstractMessage)m);
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
      
      public long getId() {
        return this.id_;
      }
      
      public Builder setId(long value) {
        this.id_ = value;
        this.bitField0_ |= 0x1;
        onChanged();
        return this;
      }
      
      public Builder clearId() {
        this.bitField0_ &= 0xFFFFFFFE;
        this.id_ = 0L;
        onChanged();
        return this;
      }
      
      public String getName() {
        Object ref = this.name_;
        if (!(ref instanceof String)) {
          ByteString bs = (ByteString)ref;
          String s = bs.toStringUtf8();
          this.name_ = s;
          return s;
        } 
        return (String)ref;
      }
      
      public ByteString getNameBytes() {
        Object ref = this.name_;
        if (ref instanceof String) {
          ByteString b = ByteString.copyFromUtf8((String)ref);
          this.name_ = b;
          return b;
        } 
        return (ByteString)ref;
      }
      
      public Builder setName(String value) {
        if (value == null)
          throw new NullPointerException(); 
        this.name_ = value;
        this.bitField0_ |= 0x2;
        onChanged();
        return this;
      }
      
      public Builder clearName() {
        this.name_ = GzoneNameplateOuterClass.GzoneNameplate.getDefaultInstance().getName();
        this.bitField0_ &= 0xFFFFFFFD;
        onChanged();
        return this;
      }
      
      public Builder setNameBytes(ByteString value) {
        if (value == null)
          throw new NullPointerException(); 
        GzoneNameplateOuterClass.GzoneNameplate.checkByteStringIsUtf8(value);
        this.name_ = value;
        this.bitField0_ |= 0x2;
        onChanged();
        return this;
      }
      
      private void ensureUrlsIsMutable() {
        if ((this.bitField0_ & 0x4) == 0) {
          this.urls_ = new ArrayList<>(this.urls_);
          this.bitField0_ |= 0x4;
        } 
      }
      
      public List<PicUrlOuterClass.PicUrl> getUrlsList() {
        if (this.urlsBuilder_ == null)
          return Collections.unmodifiableList(this.urls_); 
        return this.urlsBuilder_.getMessageList();
      }
      
      public int getUrlsCount() {
        if (this.urlsBuilder_ == null)
          return this.urls_.size(); 
        return this.urlsBuilder_.getCount();
      }
      
      public PicUrlOuterClass.PicUrl getUrls(int index) {
        if (this.urlsBuilder_ == null)
          return this.urls_.get(index); 
        return (PicUrlOuterClass.PicUrl)this.urlsBuilder_.getMessage(index);
      }
      
      public Builder setUrls(int index, PicUrlOuterClass.PicUrl value) {
        if (this.urlsBuilder_ == null) {
          if (value == null)
            throw new NullPointerException(); 
          ensureUrlsIsMutable();
          this.urls_.set(index, value);
          onChanged();
        } else {
          this.urlsBuilder_.setMessage(index, (AbstractMessage)value);
        } 
        return this;
      }
      
      public Builder setUrls(int index, PicUrlOuterClass.PicUrl.Builder builderForValue) {
        if (this.urlsBuilder_ == null) {
          ensureUrlsIsMutable();
          this.urls_.set(index, builderForValue.build());
          onChanged();
        } else {
          this.urlsBuilder_.setMessage(index, (AbstractMessage)builderForValue.build());
        } 
        return this;
      }
      
      public Builder addUrls(PicUrlOuterClass.PicUrl value) {
        if (this.urlsBuilder_ == null) {
          if (value == null)
            throw new NullPointerException(); 
          ensureUrlsIsMutable();
          this.urls_.add(value);
          onChanged();
        } else {
          this.urlsBuilder_.addMessage((AbstractMessage)value);
        } 
        return this;
      }
      
      public Builder addUrls(int index, PicUrlOuterClass.PicUrl value) {
        if (this.urlsBuilder_ == null) {
          if (value == null)
            throw new NullPointerException(); 
          ensureUrlsIsMutable();
          this.urls_.add(index, value);
          onChanged();
        } else {
          this.urlsBuilder_.addMessage(index, (AbstractMessage)value);
        } 
        return this;
      }
      
      public Builder addUrls(PicUrlOuterClass.PicUrl.Builder builderForValue) {
        if (this.urlsBuilder_ == null) {
          ensureUrlsIsMutable();
          this.urls_.add(builderForValue.build());
          onChanged();
        } else {
          this.urlsBuilder_.addMessage((AbstractMessage)builderForValue.build());
        } 
        return this;
      }
      
      public Builder addUrls(int index, PicUrlOuterClass.PicUrl.Builder builderForValue) {
        if (this.urlsBuilder_ == null) {
          ensureUrlsIsMutable();
          this.urls_.add(index, builderForValue.build());
          onChanged();
        } else {
          this.urlsBuilder_.addMessage(index, (AbstractMessage)builderForValue.build());
        } 
        return this;
      }
      
      public Builder addAllUrls(Iterable<? extends PicUrlOuterClass.PicUrl> values) {
        if (this.urlsBuilder_ == null) {
          ensureUrlsIsMutable();
          AbstractMessageLite.Builder.addAll(values, this.urls_);
          onChanged();
        } else {
          this.urlsBuilder_.addAllMessages(values);
        } 
        return this;
      }
      
      public Builder clearUrls() {
        if (this.urlsBuilder_ == null) {
          this.urls_ = Collections.emptyList();
          this.bitField0_ &= 0xFFFFFFFB;
          onChanged();
        } else {
          this.urlsBuilder_.clear();
        } 
        return this;
      }
      
      public Builder removeUrls(int index) {
        if (this.urlsBuilder_ == null) {
          ensureUrlsIsMutable();
          this.urls_.remove(index);
          onChanged();
        } else {
          this.urlsBuilder_.remove(index);
        } 
        return this;
      }
      
      public PicUrlOuterClass.PicUrl.Builder getUrlsBuilder(int index) {
        return (PicUrlOuterClass.PicUrl.Builder)getUrlsFieldBuilder().getBuilder(index);
      }
      
      public PicUrlOuterClass.PicUrlOrBuilder getUrlsOrBuilder(int index) {
        if (this.urlsBuilder_ == null)
          return this.urls_.get(index); 
        return (PicUrlOuterClass.PicUrlOrBuilder)this.urlsBuilder_.getMessageOrBuilder(index);
      }
      
      public List<? extends PicUrlOuterClass.PicUrlOrBuilder> getUrlsOrBuilderList() {
        if (this.urlsBuilder_ != null)
          return this.urlsBuilder_.getMessageOrBuilderList(); 
        return Collections.unmodifiableList((List)this.urls_);
      }
      
      public PicUrlOuterClass.PicUrl.Builder addUrlsBuilder() {
        return (PicUrlOuterClass.PicUrl.Builder)getUrlsFieldBuilder().addBuilder(
            (AbstractMessage)PicUrlOuterClass.PicUrl.getDefaultInstance());
      }
      
      public PicUrlOuterClass.PicUrl.Builder addUrlsBuilder(int index) {
        return (PicUrlOuterClass.PicUrl.Builder)getUrlsFieldBuilder().addBuilder(index, 
            (AbstractMessage)PicUrlOuterClass.PicUrl.getDefaultInstance());
      }
      
      public List<PicUrlOuterClass.PicUrl.Builder> getUrlsBuilderList() {
        return getUrlsFieldBuilder().getBuilderList();
      }
      
      private RepeatedFieldBuilderV3<PicUrlOuterClass.PicUrl, PicUrlOuterClass.PicUrl.Builder, PicUrlOuterClass.PicUrlOrBuilder> getUrlsFieldBuilder() {
        if (this.urlsBuilder_ == null) {
          this
            
            .urlsBuilder_ = new RepeatedFieldBuilderV3(this.urls_, ((this.bitField0_ & 0x4) != 0), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
          this.urls_ = null;
        } 
        return this.urlsBuilder_;
      }
      
      public final Builder setUnknownFields(UnknownFieldSet unknownFields) {
        return (Builder)super.setUnknownFields(unknownFields);
      }
      
      public final Builder mergeUnknownFields(UnknownFieldSet unknownFields) {
        return (Builder)super.mergeUnknownFields(unknownFields);
      }
    }
    
    private static final GzoneNameplate DEFAULT_INSTANCE = new GzoneNameplate();
    
    public static GzoneNameplate getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }
    
    private static final Parser<GzoneNameplate> PARSER = (Parser<GzoneNameplate>)new AbstractParser<GzoneNameplate>() {
        public GzoneNameplateOuterClass.GzoneNameplate parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
          GzoneNameplateOuterClass.GzoneNameplate.Builder builder = GzoneNameplateOuterClass.GzoneNameplate.newBuilder();
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
    
    public static Parser<GzoneNameplate> parser() {
      return PARSER;
    }
    
    public Parser<GzoneNameplate> getParserForType() {
      return PARSER;
    }
    
    public GzoneNameplate getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }
  }
  
  public static Descriptors.FileDescriptor getDescriptor() {
    return descriptor;
  }
  
  static {
    String[] descriptorData = { "\n\024GzoneNameplate.proto\032\fPicUrl.proto\"A\n\016GzoneNameplate\022\n\n\002id\030\001 \001(\003\022\f\n\004name\030\002 \001(\t\022\025\n\004urls\030\003 \003(\0132\007.PicUrlB<\n:tech.ordinaryroad.live.chat.client.codec.kuaishou.protobufb\006proto3" };
    descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[] { PicUrlOuterClass.getDescriptor() });
    internal_static_GzoneNameplate_descriptor = getDescriptor().getMessageTypes().get(0);
    internal_static_GzoneNameplate_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_GzoneNameplate_descriptor, new String[] { "Id", "Name", "Urls" });
    PicUrlOuterClass.getDescriptor();
  }
  
  public static interface GzoneNameplateOrBuilder extends MessageOrBuilder {
    long getId();
    
    String getName();
    
    ByteString getNameBytes();
    
    List<PicUrlOuterClass.PicUrl> getUrlsList();
    
    PicUrlOuterClass.PicUrl getUrls(int param1Int);
    
    int getUrlsCount();
    
    List<? extends PicUrlOuterClass.PicUrlOrBuilder> getUrlsOrBuilderList();
    
    PicUrlOuterClass.PicUrlOrBuilder getUrlsOrBuilder(int param1Int);
  }
}
