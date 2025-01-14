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
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class WebComboCommentFeedOuterClass {
  private static final Descriptors.Descriptor internal_static_WebComboCommentFeed_descriptor;
  
  private static final GeneratedMessageV3.FieldAccessorTable internal_static_WebComboCommentFeed_fieldAccessorTable;
  
  private static Descriptors.FileDescriptor descriptor;
  
  public static void registerAllExtensions(ExtensionRegistryLite registry) {}
  
  public static void registerAllExtensions(ExtensionRegistry registry) {
    registerAllExtensions((ExtensionRegistryLite)registry);
  }
  
  public static final class WebComboCommentFeed extends GeneratedMessageV3 implements WebComboCommentFeedOrBuilder {
    private static final long serialVersionUID = 0L;
    
    public static final int ID_FIELD_NUMBER = 1;
    
    private volatile Object id_;
    
    public static final int CONTENT_FIELD_NUMBER = 2;
    
    private volatile Object content_;
    
    public static final int COMBOCOUNT_FIELD_NUMBER = 3;
    
    private int comboCount_;
    
    private byte memoizedIsInitialized;
    
    private WebComboCommentFeed(GeneratedMessageV3.Builder<?> builder) {
      super(builder);
      this.id_ = "";
      this.content_ = "";
      this.comboCount_ = 0;
      this.memoizedIsInitialized = -1;
    }
    
    private WebComboCommentFeed() {
      this.id_ = "";
      this.content_ = "";
      this.comboCount_ = 0;
      this.memoizedIsInitialized = -1;
      this.id_ = "";
      this.content_ = "";
    }
    
    protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
      return new WebComboCommentFeed();
    }
    
    public static final Descriptors.Descriptor getDescriptor() {
      return WebComboCommentFeedOuterClass.internal_static_WebComboCommentFeed_descriptor;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return WebComboCommentFeedOuterClass.internal_static_WebComboCommentFeed_fieldAccessorTable.ensureFieldAccessorsInitialized(WebComboCommentFeed.class, Builder.class);
    }
    
    public String getId() {
      Object ref = this.id_;
      if (ref instanceof String)
        return (String)ref; 
      ByteString bs = (ByteString)ref;
      String s = bs.toStringUtf8();
      this.id_ = s;
      return s;
    }
    
    public ByteString getIdBytes() {
      Object ref = this.id_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.id_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public String getContent() {
      Object ref = this.content_;
      if (ref instanceof String)
        return (String)ref; 
      ByteString bs = (ByteString)ref;
      String s = bs.toStringUtf8();
      this.content_ = s;
      return s;
    }
    
    public ByteString getContentBytes() {
      Object ref = this.content_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.content_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public int getComboCount() {
      return this.comboCount_;
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
      if (!GeneratedMessageV3.isStringEmpty(this.id_))
        GeneratedMessageV3.writeString(output, 1, this.id_); 
      if (!GeneratedMessageV3.isStringEmpty(this.content_))
        GeneratedMessageV3.writeString(output, 2, this.content_); 
      if (this.comboCount_ != 0)
        output.writeUInt32(3, this.comboCount_); 
      getUnknownFields().writeTo(output);
    }
    
    public int getSerializedSize() {
      int size = this.memoizedSize;
      if (size != -1)
        return size; 
      size = 0;
      if (!GeneratedMessageV3.isStringEmpty(this.id_))
        size += GeneratedMessageV3.computeStringSize(1, this.id_); 
      if (!GeneratedMessageV3.isStringEmpty(this.content_))
        size += GeneratedMessageV3.computeStringSize(2, this.content_); 
      if (this.comboCount_ != 0)
        size += 
          CodedOutputStream.computeUInt32Size(3, this.comboCount_); 
      size += getUnknownFields().getSerializedSize();
      this.memoizedSize = size;
      return size;
    }
    
    public boolean equals(Object obj) {
      if (obj == this)
        return true; 
      if (!(obj instanceof WebComboCommentFeed))
        return super.equals(obj); 
      WebComboCommentFeed other = (WebComboCommentFeed)obj;
      if (!getId().equals(other.getId()))
        return false; 
      if (!getContent().equals(other.getContent()))
        return false; 
      if (getComboCount() != other
        .getComboCount())
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
      hash = 53 * hash + getId().hashCode();
      hash = 37 * hash + 2;
      hash = 53 * hash + getContent().hashCode();
      hash = 37 * hash + 3;
      hash = 53 * hash + getComboCount();
      hash = 29 * hash + getUnknownFields().hashCode();
      this.memoizedHashCode = hash;
      return hash;
    }
    
    public static WebComboCommentFeed parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (WebComboCommentFeed)PARSER.parseFrom(data);
    }
    
    public static WebComboCommentFeed parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (WebComboCommentFeed)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static WebComboCommentFeed parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (WebComboCommentFeed)PARSER.parseFrom(data);
    }
    
    public static WebComboCommentFeed parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (WebComboCommentFeed)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static WebComboCommentFeed parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (WebComboCommentFeed)PARSER.parseFrom(data);
    }
    
    public static WebComboCommentFeed parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (WebComboCommentFeed)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static WebComboCommentFeed parseFrom(InputStream input) throws IOException {
      return 
        (WebComboCommentFeed)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static WebComboCommentFeed parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (WebComboCommentFeed)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static WebComboCommentFeed parseDelimitedFrom(InputStream input) throws IOException {
      return 
        (WebComboCommentFeed)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }
    
    public static WebComboCommentFeed parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (WebComboCommentFeed)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static WebComboCommentFeed parseFrom(CodedInputStream input) throws IOException {
      return 
        (WebComboCommentFeed)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static WebComboCommentFeed parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (WebComboCommentFeed)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public Builder newBuilderForType() {
      return newBuilder();
    }
    
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    
    public static Builder newBuilder(WebComboCommentFeed prototype) {
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
    
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements WebComboCommentFeedOuterClass.WebComboCommentFeedOrBuilder {
      private int bitField0_;
      
      private Object id_;
      
      private Object content_;
      
      private int comboCount_;
      
      public static final Descriptors.Descriptor getDescriptor() {
        return WebComboCommentFeedOuterClass.internal_static_WebComboCommentFeed_descriptor;
      }
      
      protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return WebComboCommentFeedOuterClass.internal_static_WebComboCommentFeed_fieldAccessorTable
          .ensureFieldAccessorsInitialized(WebComboCommentFeedOuterClass.WebComboCommentFeed.class, Builder.class);
      }
      
      private Builder() {
        this.id_ = "";
        this.content_ = "";
      }
      
      private Builder(GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        this.id_ = "";
        this.content_ = "";
      }
      
      public Builder clear() {
        super.clear();
        this.bitField0_ = 0;
        this.id_ = "";
        this.content_ = "";
        this.comboCount_ = 0;
        return this;
      }
      
      public Descriptors.Descriptor getDescriptorForType() {
        return WebComboCommentFeedOuterClass.internal_static_WebComboCommentFeed_descriptor;
      }
      
      public WebComboCommentFeedOuterClass.WebComboCommentFeed getDefaultInstanceForType() {
        return WebComboCommentFeedOuterClass.WebComboCommentFeed.getDefaultInstance();
      }
      
      public WebComboCommentFeedOuterClass.WebComboCommentFeed build() {
        WebComboCommentFeedOuterClass.WebComboCommentFeed result = buildPartial();
        if (!result.isInitialized())
          throw newUninitializedMessageException(result); 
        return result;
      }
      
      public WebComboCommentFeedOuterClass.WebComboCommentFeed buildPartial() {
        WebComboCommentFeedOuterClass.WebComboCommentFeed result = new WebComboCommentFeedOuterClass.WebComboCommentFeed(this);
        if (this.bitField0_ != 0)
          buildPartial0(result); 
        onBuilt();
        return result;
      }
      
      private void buildPartial0(WebComboCommentFeedOuterClass.WebComboCommentFeed result) {
        int from_bitField0_ = this.bitField0_;
        if ((from_bitField0_ & 0x1) != 0)
          result.id_ = this.id_; 
        if ((from_bitField0_ & 0x2) != 0)
          result.content_ = this.content_; 
        if ((from_bitField0_ & 0x4) != 0)
          result.comboCount_ = this.comboCount_; 
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
        if (other instanceof WebComboCommentFeedOuterClass.WebComboCommentFeed)
          return mergeFrom((WebComboCommentFeedOuterClass.WebComboCommentFeed)other); 
        super.mergeFrom(other);
        return this;
      }
      
      public Builder mergeFrom(WebComboCommentFeedOuterClass.WebComboCommentFeed other) {
        if (other == WebComboCommentFeedOuterClass.WebComboCommentFeed.getDefaultInstance())
          return this; 
        if (!other.getId().isEmpty()) {
          this.id_ = other.id_;
          this.bitField0_ |= 0x1;
          onChanged();
        } 
        if (!other.getContent().isEmpty()) {
          this.content_ = other.content_;
          this.bitField0_ |= 0x2;
          onChanged();
        } 
        if (other.getComboCount() != 0)
          setComboCount(other.getComboCount()); 
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
                this.id_ = input.readStringRequireUtf8();
                this.bitField0_ |= 0x1;
                continue;
              case 18:
                this.content_ = input.readStringRequireUtf8();
                this.bitField0_ |= 0x2;
                continue;
              case 24:
                this.comboCount_ = input.readUInt32();
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
      
      public String getId() {
        Object ref = this.id_;
        if (!(ref instanceof String)) {
          ByteString bs = (ByteString)ref;
          String s = bs.toStringUtf8();
          this.id_ = s;
          return s;
        } 
        return (String)ref;
      }
      
      public ByteString getIdBytes() {
        Object ref = this.id_;
        if (ref instanceof String) {
          ByteString b = ByteString.copyFromUtf8((String)ref);
          this.id_ = b;
          return b;
        } 
        return (ByteString)ref;
      }
      
      public Builder setId(String value) {
        if (value == null)
          throw new NullPointerException(); 
        this.id_ = value;
        this.bitField0_ |= 0x1;
        onChanged();
        return this;
      }
      
      public Builder clearId() {
        this.id_ = WebComboCommentFeedOuterClass.WebComboCommentFeed.getDefaultInstance().getId();
        this.bitField0_ &= 0xFFFFFFFE;
        onChanged();
        return this;
      }
      
      public Builder setIdBytes(ByteString value) {
        if (value == null)
          throw new NullPointerException(); 
        WebComboCommentFeedOuterClass.WebComboCommentFeed.checkByteStringIsUtf8(value);
        this.id_ = value;
        this.bitField0_ |= 0x1;
        onChanged();
        return this;
      }
      
      public String getContent() {
        Object ref = this.content_;
        if (!(ref instanceof String)) {
          ByteString bs = (ByteString)ref;
          String s = bs.toStringUtf8();
          this.content_ = s;
          return s;
        } 
        return (String)ref;
      }
      
      public ByteString getContentBytes() {
        Object ref = this.content_;
        if (ref instanceof String) {
          ByteString b = ByteString.copyFromUtf8((String)ref);
          this.content_ = b;
          return b;
        } 
        return (ByteString)ref;
      }
      
      public Builder setContent(String value) {
        if (value == null)
          throw new NullPointerException(); 
        this.content_ = value;
        this.bitField0_ |= 0x2;
        onChanged();
        return this;
      }
      
      public Builder clearContent() {
        this.content_ = WebComboCommentFeedOuterClass.WebComboCommentFeed.getDefaultInstance().getContent();
        this.bitField0_ &= 0xFFFFFFFD;
        onChanged();
        return this;
      }
      
      public Builder setContentBytes(ByteString value) {
        if (value == null)
          throw new NullPointerException(); 
        WebComboCommentFeedOuterClass.WebComboCommentFeed.checkByteStringIsUtf8(value);
        this.content_ = value;
        this.bitField0_ |= 0x2;
        onChanged();
        return this;
      }
      
      public int getComboCount() {
        return this.comboCount_;
      }
      
      public Builder setComboCount(int value) {
        this.comboCount_ = value;
        this.bitField0_ |= 0x4;
        onChanged();
        return this;
      }
      
      public Builder clearComboCount() {
        this.bitField0_ &= 0xFFFFFFFB;
        this.comboCount_ = 0;
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
    
    private static final WebComboCommentFeed DEFAULT_INSTANCE = new WebComboCommentFeed();
    
    public static WebComboCommentFeed getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }
    
    private static final Parser<WebComboCommentFeed> PARSER = (Parser<WebComboCommentFeed>)new AbstractParser<WebComboCommentFeed>() {
        public WebComboCommentFeedOuterClass.WebComboCommentFeed parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
          WebComboCommentFeedOuterClass.WebComboCommentFeed.Builder builder = WebComboCommentFeedOuterClass.WebComboCommentFeed.newBuilder();
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
    
    public static Parser<WebComboCommentFeed> parser() {
      return PARSER;
    }
    
    public Parser<WebComboCommentFeed> getParserForType() {
      return PARSER;
    }
    
    public WebComboCommentFeed getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }
  }
  
  public static Descriptors.FileDescriptor getDescriptor() {
    return descriptor;
  }
  
  static {
    String[] descriptorData = { "\n\031WebComboCommentFeed.proto\"F\n\023WebComboCommentFeed\022\n\n\002id\030\001 \001(\t\022\017\n\007content\030\002 \001(\t\022\022\n\ncomboCount\030\003 \001(\rB<\n:tech.ordinaryroad.live.chat.client.codec.kuaishou.protobufb\006proto3" };
    descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[0]);
    internal_static_WebComboCommentFeed_descriptor = getDescriptor().getMessageTypes().get(0);
    internal_static_WebComboCommentFeed_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_WebComboCommentFeed_descriptor, new String[] { "Id", "Content", "ComboCount" });
  }
  
  public static interface WebComboCommentFeedOrBuilder extends MessageOrBuilder {
    String getId();
    
    ByteString getIdBytes();
    
    String getContent();
    
    ByteString getContentBytes();
    
    int getComboCount();
  }
}
