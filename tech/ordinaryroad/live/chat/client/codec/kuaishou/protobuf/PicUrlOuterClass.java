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

public final class PicUrlOuterClass {
  private static final Descriptors.Descriptor internal_static_PicUrl_descriptor;
  
  private static final GeneratedMessageV3.FieldAccessorTable internal_static_PicUrl_fieldAccessorTable;
  
  private static Descriptors.FileDescriptor descriptor;
  
  public static void registerAllExtensions(ExtensionRegistryLite registry) {}
  
  public static void registerAllExtensions(ExtensionRegistry registry) {
    registerAllExtensions((ExtensionRegistryLite)registry);
  }
  
  public static final class PicUrl extends GeneratedMessageV3 implements PicUrlOrBuilder {
    private static final long serialVersionUID = 0L;
    
    public static final int CDN_FIELD_NUMBER = 1;
    
    private volatile Object cdn_;
    
    public static final int URL_FIELD_NUMBER = 2;
    
    private volatile Object url_;
    
    public static final int URLPATTERN_FIELD_NUMBER = 3;
    
    private volatile Object urlPattern_;
    
    public static final int IP_FIELD_NUMBER = 4;
    
    private volatile Object ip_;
    
    private byte memoizedIsInitialized;
    
    private PicUrl(GeneratedMessageV3.Builder<?> builder) {
      super(builder);
      this.cdn_ = "";
      this.url_ = "";
      this.urlPattern_ = "";
      this.ip_ = "";
      this.memoizedIsInitialized = -1;
    }
    
    private PicUrl() {
      this.cdn_ = "";
      this.url_ = "";
      this.urlPattern_ = "";
      this.ip_ = "";
      this.memoizedIsInitialized = -1;
      this.cdn_ = "";
      this.url_ = "";
      this.urlPattern_ = "";
      this.ip_ = "";
    }
    
    protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
      return new PicUrl();
    }
    
    public static final Descriptors.Descriptor getDescriptor() {
      return PicUrlOuterClass.internal_static_PicUrl_descriptor;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return PicUrlOuterClass.internal_static_PicUrl_fieldAccessorTable.ensureFieldAccessorsInitialized(PicUrl.class, Builder.class);
    }
    
    public String getCdn() {
      Object ref = this.cdn_;
      if (ref instanceof String)
        return (String)ref; 
      ByteString bs = (ByteString)ref;
      String s = bs.toStringUtf8();
      this.cdn_ = s;
      return s;
    }
    
    public ByteString getCdnBytes() {
      Object ref = this.cdn_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.cdn_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public String getUrl() {
      Object ref = this.url_;
      if (ref instanceof String)
        return (String)ref; 
      ByteString bs = (ByteString)ref;
      String s = bs.toStringUtf8();
      this.url_ = s;
      return s;
    }
    
    public ByteString getUrlBytes() {
      Object ref = this.url_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.url_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public String getUrlPattern() {
      Object ref = this.urlPattern_;
      if (ref instanceof String)
        return (String)ref; 
      ByteString bs = (ByteString)ref;
      String s = bs.toStringUtf8();
      this.urlPattern_ = s;
      return s;
    }
    
    public ByteString getUrlPatternBytes() {
      Object ref = this.urlPattern_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.urlPattern_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public String getIp() {
      Object ref = this.ip_;
      if (ref instanceof String)
        return (String)ref; 
      ByteString bs = (ByteString)ref;
      String s = bs.toStringUtf8();
      this.ip_ = s;
      return s;
    }
    
    public ByteString getIpBytes() {
      Object ref = this.ip_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.ip_ = b;
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
      if (!GeneratedMessageV3.isStringEmpty(this.cdn_))
        GeneratedMessageV3.writeString(output, 1, this.cdn_); 
      if (!GeneratedMessageV3.isStringEmpty(this.url_))
        GeneratedMessageV3.writeString(output, 2, this.url_); 
      if (!GeneratedMessageV3.isStringEmpty(this.urlPattern_))
        GeneratedMessageV3.writeString(output, 3, this.urlPattern_); 
      if (!GeneratedMessageV3.isStringEmpty(this.ip_))
        GeneratedMessageV3.writeString(output, 4, this.ip_); 
      getUnknownFields().writeTo(output);
    }
    
    public int getSerializedSize() {
      int size = this.memoizedSize;
      if (size != -1)
        return size; 
      size = 0;
      if (!GeneratedMessageV3.isStringEmpty(this.cdn_))
        size += GeneratedMessageV3.computeStringSize(1, this.cdn_); 
      if (!GeneratedMessageV3.isStringEmpty(this.url_))
        size += GeneratedMessageV3.computeStringSize(2, this.url_); 
      if (!GeneratedMessageV3.isStringEmpty(this.urlPattern_))
        size += GeneratedMessageV3.computeStringSize(3, this.urlPattern_); 
      if (!GeneratedMessageV3.isStringEmpty(this.ip_))
        size += GeneratedMessageV3.computeStringSize(4, this.ip_); 
      size += getUnknownFields().getSerializedSize();
      this.memoizedSize = size;
      return size;
    }
    
    public boolean equals(Object obj) {
      if (obj == this)
        return true; 
      if (!(obj instanceof PicUrl))
        return super.equals(obj); 
      PicUrl other = (PicUrl)obj;
      if (!getCdn().equals(other.getCdn()))
        return false; 
      if (!getUrl().equals(other.getUrl()))
        return false; 
      if (!getUrlPattern().equals(other.getUrlPattern()))
        return false; 
      if (!getIp().equals(other.getIp()))
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
      hash = 53 * hash + getCdn().hashCode();
      hash = 37 * hash + 2;
      hash = 53 * hash + getUrl().hashCode();
      hash = 37 * hash + 3;
      hash = 53 * hash + getUrlPattern().hashCode();
      hash = 37 * hash + 4;
      hash = 53 * hash + getIp().hashCode();
      hash = 29 * hash + getUnknownFields().hashCode();
      this.memoizedHashCode = hash;
      return hash;
    }
    
    public static PicUrl parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (PicUrl)PARSER.parseFrom(data);
    }
    
    public static PicUrl parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (PicUrl)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static PicUrl parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (PicUrl)PARSER.parseFrom(data);
    }
    
    public static PicUrl parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (PicUrl)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static PicUrl parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (PicUrl)PARSER.parseFrom(data);
    }
    
    public static PicUrl parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (PicUrl)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static PicUrl parseFrom(InputStream input) throws IOException {
      return 
        (PicUrl)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static PicUrl parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (PicUrl)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static PicUrl parseDelimitedFrom(InputStream input) throws IOException {
      return 
        (PicUrl)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }
    
    public static PicUrl parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (PicUrl)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static PicUrl parseFrom(CodedInputStream input) throws IOException {
      return 
        (PicUrl)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static PicUrl parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (PicUrl)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public Builder newBuilderForType() {
      return newBuilder();
    }
    
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    
    public static Builder newBuilder(PicUrl prototype) {
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
    
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements PicUrlOuterClass.PicUrlOrBuilder {
      private int bitField0_;
      
      private Object cdn_;
      
      private Object url_;
      
      private Object urlPattern_;
      
      private Object ip_;
      
      public static final Descriptors.Descriptor getDescriptor() {
        return PicUrlOuterClass.internal_static_PicUrl_descriptor;
      }
      
      protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return PicUrlOuterClass.internal_static_PicUrl_fieldAccessorTable
          .ensureFieldAccessorsInitialized(PicUrlOuterClass.PicUrl.class, Builder.class);
      }
      
      private Builder() {
        this.cdn_ = "";
        this.url_ = "";
        this.urlPattern_ = "";
        this.ip_ = "";
      }
      
      private Builder(GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        this.cdn_ = "";
        this.url_ = "";
        this.urlPattern_ = "";
        this.ip_ = "";
      }
      
      public Builder clear() {
        super.clear();
        this.bitField0_ = 0;
        this.cdn_ = "";
        this.url_ = "";
        this.urlPattern_ = "";
        this.ip_ = "";
        return this;
      }
      
      public Descriptors.Descriptor getDescriptorForType() {
        return PicUrlOuterClass.internal_static_PicUrl_descriptor;
      }
      
      public PicUrlOuterClass.PicUrl getDefaultInstanceForType() {
        return PicUrlOuterClass.PicUrl.getDefaultInstance();
      }
      
      public PicUrlOuterClass.PicUrl build() {
        PicUrlOuterClass.PicUrl result = buildPartial();
        if (!result.isInitialized())
          throw newUninitializedMessageException(result); 
        return result;
      }
      
      public PicUrlOuterClass.PicUrl buildPartial() {
        PicUrlOuterClass.PicUrl result = new PicUrlOuterClass.PicUrl(this);
        if (this.bitField0_ != 0)
          buildPartial0(result); 
        onBuilt();
        return result;
      }
      
      private void buildPartial0(PicUrlOuterClass.PicUrl result) {
        int from_bitField0_ = this.bitField0_;
        if ((from_bitField0_ & 0x1) != 0)
          result.cdn_ = this.cdn_; 
        if ((from_bitField0_ & 0x2) != 0)
          result.url_ = this.url_; 
        if ((from_bitField0_ & 0x4) != 0)
          result.urlPattern_ = this.urlPattern_; 
        if ((from_bitField0_ & 0x8) != 0)
          result.ip_ = this.ip_; 
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
        if (other instanceof PicUrlOuterClass.PicUrl)
          return mergeFrom((PicUrlOuterClass.PicUrl)other); 
        super.mergeFrom(other);
        return this;
      }
      
      public Builder mergeFrom(PicUrlOuterClass.PicUrl other) {
        if (other == PicUrlOuterClass.PicUrl.getDefaultInstance())
          return this; 
        if (!other.getCdn().isEmpty()) {
          this.cdn_ = other.cdn_;
          this.bitField0_ |= 0x1;
          onChanged();
        } 
        if (!other.getUrl().isEmpty()) {
          this.url_ = other.url_;
          this.bitField0_ |= 0x2;
          onChanged();
        } 
        if (!other.getUrlPattern().isEmpty()) {
          this.urlPattern_ = other.urlPattern_;
          this.bitField0_ |= 0x4;
          onChanged();
        } 
        if (!other.getIp().isEmpty()) {
          this.ip_ = other.ip_;
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
                this.cdn_ = input.readStringRequireUtf8();
                this.bitField0_ |= 0x1;
                continue;
              case 18:
                this.url_ = input.readStringRequireUtf8();
                this.bitField0_ |= 0x2;
                continue;
              case 26:
                this.urlPattern_ = input.readStringRequireUtf8();
                this.bitField0_ |= 0x4;
                continue;
              case 34:
                this.ip_ = input.readStringRequireUtf8();
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
      
      public String getCdn() {
        Object ref = this.cdn_;
        if (!(ref instanceof String)) {
          ByteString bs = (ByteString)ref;
          String s = bs.toStringUtf8();
          this.cdn_ = s;
          return s;
        } 
        return (String)ref;
      }
      
      public ByteString getCdnBytes() {
        Object ref = this.cdn_;
        if (ref instanceof String) {
          ByteString b = ByteString.copyFromUtf8((String)ref);
          this.cdn_ = b;
          return b;
        } 
        return (ByteString)ref;
      }
      
      public Builder setCdn(String value) {
        if (value == null)
          throw new NullPointerException(); 
        this.cdn_ = value;
        this.bitField0_ |= 0x1;
        onChanged();
        return this;
      }
      
      public Builder clearCdn() {
        this.cdn_ = PicUrlOuterClass.PicUrl.getDefaultInstance().getCdn();
        this.bitField0_ &= 0xFFFFFFFE;
        onChanged();
        return this;
      }
      
      public Builder setCdnBytes(ByteString value) {
        if (value == null)
          throw new NullPointerException(); 
        PicUrlOuterClass.PicUrl.checkByteStringIsUtf8(value);
        this.cdn_ = value;
        this.bitField0_ |= 0x1;
        onChanged();
        return this;
      }
      
      public String getUrl() {
        Object ref = this.url_;
        if (!(ref instanceof String)) {
          ByteString bs = (ByteString)ref;
          String s = bs.toStringUtf8();
          this.url_ = s;
          return s;
        } 
        return (String)ref;
      }
      
      public ByteString getUrlBytes() {
        Object ref = this.url_;
        if (ref instanceof String) {
          ByteString b = ByteString.copyFromUtf8((String)ref);
          this.url_ = b;
          return b;
        } 
        return (ByteString)ref;
      }
      
      public Builder setUrl(String value) {
        if (value == null)
          throw new NullPointerException(); 
        this.url_ = value;
        this.bitField0_ |= 0x2;
        onChanged();
        return this;
      }
      
      public Builder clearUrl() {
        this.url_ = PicUrlOuterClass.PicUrl.getDefaultInstance().getUrl();
        this.bitField0_ &= 0xFFFFFFFD;
        onChanged();
        return this;
      }
      
      public Builder setUrlBytes(ByteString value) {
        if (value == null)
          throw new NullPointerException(); 
        PicUrlOuterClass.PicUrl.checkByteStringIsUtf8(value);
        this.url_ = value;
        this.bitField0_ |= 0x2;
        onChanged();
        return this;
      }
      
      public String getUrlPattern() {
        Object ref = this.urlPattern_;
        if (!(ref instanceof String)) {
          ByteString bs = (ByteString)ref;
          String s = bs.toStringUtf8();
          this.urlPattern_ = s;
          return s;
        } 
        return (String)ref;
      }
      
      public ByteString getUrlPatternBytes() {
        Object ref = this.urlPattern_;
        if (ref instanceof String) {
          ByteString b = ByteString.copyFromUtf8((String)ref);
          this.urlPattern_ = b;
          return b;
        } 
        return (ByteString)ref;
      }
      
      public Builder setUrlPattern(String value) {
        if (value == null)
          throw new NullPointerException(); 
        this.urlPattern_ = value;
        this.bitField0_ |= 0x4;
        onChanged();
        return this;
      }
      
      public Builder clearUrlPattern() {
        this.urlPattern_ = PicUrlOuterClass.PicUrl.getDefaultInstance().getUrlPattern();
        this.bitField0_ &= 0xFFFFFFFB;
        onChanged();
        return this;
      }
      
      public Builder setUrlPatternBytes(ByteString value) {
        if (value == null)
          throw new NullPointerException(); 
        PicUrlOuterClass.PicUrl.checkByteStringIsUtf8(value);
        this.urlPattern_ = value;
        this.bitField0_ |= 0x4;
        onChanged();
        return this;
      }
      
      public String getIp() {
        Object ref = this.ip_;
        if (!(ref instanceof String)) {
          ByteString bs = (ByteString)ref;
          String s = bs.toStringUtf8();
          this.ip_ = s;
          return s;
        } 
        return (String)ref;
      }
      
      public ByteString getIpBytes() {
        Object ref = this.ip_;
        if (ref instanceof String) {
          ByteString b = ByteString.copyFromUtf8((String)ref);
          this.ip_ = b;
          return b;
        } 
        return (ByteString)ref;
      }
      
      public Builder setIp(String value) {
        if (value == null)
          throw new NullPointerException(); 
        this.ip_ = value;
        this.bitField0_ |= 0x8;
        onChanged();
        return this;
      }
      
      public Builder clearIp() {
        this.ip_ = PicUrlOuterClass.PicUrl.getDefaultInstance().getIp();
        this.bitField0_ &= 0xFFFFFFF7;
        onChanged();
        return this;
      }
      
      public Builder setIpBytes(ByteString value) {
        if (value == null)
          throw new NullPointerException(); 
        PicUrlOuterClass.PicUrl.checkByteStringIsUtf8(value);
        this.ip_ = value;
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
    
    private static final PicUrl DEFAULT_INSTANCE = new PicUrl();
    
    public static PicUrl getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }
    
    private static final Parser<PicUrl> PARSER = (Parser<PicUrl>)new AbstractParser<PicUrl>() {
        public PicUrlOuterClass.PicUrl parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
          PicUrlOuterClass.PicUrl.Builder builder = PicUrlOuterClass.PicUrl.newBuilder();
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
    
    public static Parser<PicUrl> parser() {
      return PARSER;
    }
    
    public Parser<PicUrl> getParserForType() {
      return PARSER;
    }
    
    public PicUrl getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }
  }
  
  public static Descriptors.FileDescriptor getDescriptor() {
    return descriptor;
  }
  
  static {
    String[] descriptorData = { "\n\fPicUrl.proto\"B\n\006PicUrl\022\013\n\003cdn\030\001 \001(\t\022\013\n\003url\030\002 \001(\t\022\022\n\nurlPattern\030\003 \001(\t\022\n\n\002ip\030\004 \001(\tB<\n:tech.ordinaryroad.live.chat.client.codec.kuaishou.protobufb\006proto3" };
    descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[0]);
    internal_static_PicUrl_descriptor = getDescriptor().getMessageTypes().get(0);
    internal_static_PicUrl_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_PicUrl_descriptor, new String[] { "Cdn", "Url", "UrlPattern", "Ip" });
  }
  
  public static interface PicUrlOrBuilder extends MessageOrBuilder {
    String getCdn();
    
    ByteString getCdnBytes();
    
    String getUrl();
    
    ByteString getUrlBytes();
    
    String getUrlPattern();
    
    ByteString getUrlPatternBytes();
    
    String getIp();
    
    ByteString getIpBytes();
  }
}
