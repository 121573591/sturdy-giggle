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
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class LiveCdnNodeViewOuterClass {
  private static final Descriptors.Descriptor internal_static_LiveCdnNodeView_descriptor;
  
  private static final GeneratedMessageV3.FieldAccessorTable internal_static_LiveCdnNodeView_fieldAccessorTable;
  
  private static Descriptors.FileDescriptor descriptor;
  
  public static void registerAllExtensions(ExtensionRegistryLite registry) {}
  
  public static void registerAllExtensions(ExtensionRegistry registry) {
    registerAllExtensions((ExtensionRegistryLite)registry);
  }
  
  public static final class LiveCdnNodeView extends GeneratedMessageV3 implements LiveCdnNodeViewOrBuilder {
    private static final long serialVersionUID = 0L;
    
    public static final int CDN_FIELD_NUMBER = 1;
    
    private volatile Object cdn_;
    
    public static final int URL_FIELD_NUMBER = 2;
    
    private volatile Object url_;
    
    public static final int FREETRAFFIC_FIELD_NUMBER = 3;
    
    private boolean freeTraffic_;
    
    private byte memoizedIsInitialized;
    
    private LiveCdnNodeView(GeneratedMessageV3.Builder<?> builder) {
      super(builder);
      this.cdn_ = "";
      this.url_ = "";
      this.freeTraffic_ = false;
      this.memoizedIsInitialized = -1;
    }
    
    private LiveCdnNodeView() {
      this.cdn_ = "";
      this.url_ = "";
      this.freeTraffic_ = false;
      this.memoizedIsInitialized = -1;
      this.cdn_ = "";
      this.url_ = "";
    }
    
    protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
      return new LiveCdnNodeView();
    }
    
    public static final Descriptors.Descriptor getDescriptor() {
      return LiveCdnNodeViewOuterClass.internal_static_LiveCdnNodeView_descriptor;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return LiveCdnNodeViewOuterClass.internal_static_LiveCdnNodeView_fieldAccessorTable.ensureFieldAccessorsInitialized(LiveCdnNodeView.class, Builder.class);
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
    
    public boolean getFreeTraffic() {
      return this.freeTraffic_;
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
      if (this.freeTraffic_)
        output.writeBool(3, this.freeTraffic_); 
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
      if (this.freeTraffic_)
        size += 
          CodedOutputStream.computeBoolSize(3, this.freeTraffic_); 
      size += getUnknownFields().getSerializedSize();
      this.memoizedSize = size;
      return size;
    }
    
    public boolean equals(Object obj) {
      if (obj == this)
        return true; 
      if (!(obj instanceof LiveCdnNodeView))
        return super.equals(obj); 
      LiveCdnNodeView other = (LiveCdnNodeView)obj;
      if (!getCdn().equals(other.getCdn()))
        return false; 
      if (!getUrl().equals(other.getUrl()))
        return false; 
      if (getFreeTraffic() != other
        .getFreeTraffic())
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
      hash = 53 * hash + Internal.hashBoolean(
          getFreeTraffic());
      hash = 29 * hash + getUnknownFields().hashCode();
      this.memoizedHashCode = hash;
      return hash;
    }
    
    public static LiveCdnNodeView parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (LiveCdnNodeView)PARSER.parseFrom(data);
    }
    
    public static LiveCdnNodeView parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (LiveCdnNodeView)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static LiveCdnNodeView parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (LiveCdnNodeView)PARSER.parseFrom(data);
    }
    
    public static LiveCdnNodeView parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (LiveCdnNodeView)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static LiveCdnNodeView parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (LiveCdnNodeView)PARSER.parseFrom(data);
    }
    
    public static LiveCdnNodeView parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (LiveCdnNodeView)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static LiveCdnNodeView parseFrom(InputStream input) throws IOException {
      return 
        (LiveCdnNodeView)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static LiveCdnNodeView parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (LiveCdnNodeView)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static LiveCdnNodeView parseDelimitedFrom(InputStream input) throws IOException {
      return 
        (LiveCdnNodeView)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }
    
    public static LiveCdnNodeView parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (LiveCdnNodeView)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static LiveCdnNodeView parseFrom(CodedInputStream input) throws IOException {
      return 
        (LiveCdnNodeView)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static LiveCdnNodeView parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (LiveCdnNodeView)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public Builder newBuilderForType() {
      return newBuilder();
    }
    
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    
    public static Builder newBuilder(LiveCdnNodeView prototype) {
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
    
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements LiveCdnNodeViewOuterClass.LiveCdnNodeViewOrBuilder {
      private int bitField0_;
      
      private Object cdn_;
      
      private Object url_;
      
      private boolean freeTraffic_;
      
      public static final Descriptors.Descriptor getDescriptor() {
        return LiveCdnNodeViewOuterClass.internal_static_LiveCdnNodeView_descriptor;
      }
      
      protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return LiveCdnNodeViewOuterClass.internal_static_LiveCdnNodeView_fieldAccessorTable
          .ensureFieldAccessorsInitialized(LiveCdnNodeViewOuterClass.LiveCdnNodeView.class, Builder.class);
      }
      
      private Builder() {
        this.cdn_ = "";
        this.url_ = "";
      }
      
      private Builder(GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        this.cdn_ = "";
        this.url_ = "";
      }
      
      public Builder clear() {
        super.clear();
        this.bitField0_ = 0;
        this.cdn_ = "";
        this.url_ = "";
        this.freeTraffic_ = false;
        return this;
      }
      
      public Descriptors.Descriptor getDescriptorForType() {
        return LiveCdnNodeViewOuterClass.internal_static_LiveCdnNodeView_descriptor;
      }
      
      public LiveCdnNodeViewOuterClass.LiveCdnNodeView getDefaultInstanceForType() {
        return LiveCdnNodeViewOuterClass.LiveCdnNodeView.getDefaultInstance();
      }
      
      public LiveCdnNodeViewOuterClass.LiveCdnNodeView build() {
        LiveCdnNodeViewOuterClass.LiveCdnNodeView result = buildPartial();
        if (!result.isInitialized())
          throw newUninitializedMessageException(result); 
        return result;
      }
      
      public LiveCdnNodeViewOuterClass.LiveCdnNodeView buildPartial() {
        LiveCdnNodeViewOuterClass.LiveCdnNodeView result = new LiveCdnNodeViewOuterClass.LiveCdnNodeView(this);
        if (this.bitField0_ != 0)
          buildPartial0(result); 
        onBuilt();
        return result;
      }
      
      private void buildPartial0(LiveCdnNodeViewOuterClass.LiveCdnNodeView result) {
        int from_bitField0_ = this.bitField0_;
        if ((from_bitField0_ & 0x1) != 0)
          result.cdn_ = this.cdn_; 
        if ((from_bitField0_ & 0x2) != 0)
          result.url_ = this.url_; 
        if ((from_bitField0_ & 0x4) != 0)
          result.freeTraffic_ = this.freeTraffic_; 
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
        if (other instanceof LiveCdnNodeViewOuterClass.LiveCdnNodeView)
          return mergeFrom((LiveCdnNodeViewOuterClass.LiveCdnNodeView)other); 
        super.mergeFrom(other);
        return this;
      }
      
      public Builder mergeFrom(LiveCdnNodeViewOuterClass.LiveCdnNodeView other) {
        if (other == LiveCdnNodeViewOuterClass.LiveCdnNodeView.getDefaultInstance())
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
        if (other.getFreeTraffic())
          setFreeTraffic(other.getFreeTraffic()); 
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
              case 24:
                this.freeTraffic_ = input.readBool();
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
        this.cdn_ = LiveCdnNodeViewOuterClass.LiveCdnNodeView.getDefaultInstance().getCdn();
        this.bitField0_ &= 0xFFFFFFFE;
        onChanged();
        return this;
      }
      
      public Builder setCdnBytes(ByteString value) {
        if (value == null)
          throw new NullPointerException(); 
        LiveCdnNodeViewOuterClass.LiveCdnNodeView.checkByteStringIsUtf8(value);
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
        this.url_ = LiveCdnNodeViewOuterClass.LiveCdnNodeView.getDefaultInstance().getUrl();
        this.bitField0_ &= 0xFFFFFFFD;
        onChanged();
        return this;
      }
      
      public Builder setUrlBytes(ByteString value) {
        if (value == null)
          throw new NullPointerException(); 
        LiveCdnNodeViewOuterClass.LiveCdnNodeView.checkByteStringIsUtf8(value);
        this.url_ = value;
        this.bitField0_ |= 0x2;
        onChanged();
        return this;
      }
      
      public boolean getFreeTraffic() {
        return this.freeTraffic_;
      }
      
      public Builder setFreeTraffic(boolean value) {
        this.freeTraffic_ = value;
        this.bitField0_ |= 0x4;
        onChanged();
        return this;
      }
      
      public Builder clearFreeTraffic() {
        this.bitField0_ &= 0xFFFFFFFB;
        this.freeTraffic_ = false;
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
    
    private static final LiveCdnNodeView DEFAULT_INSTANCE = new LiveCdnNodeView();
    
    public static LiveCdnNodeView getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }
    
    private static final Parser<LiveCdnNodeView> PARSER = (Parser<LiveCdnNodeView>)new AbstractParser<LiveCdnNodeView>() {
        public LiveCdnNodeViewOuterClass.LiveCdnNodeView parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
          LiveCdnNodeViewOuterClass.LiveCdnNodeView.Builder builder = LiveCdnNodeViewOuterClass.LiveCdnNodeView.newBuilder();
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
    
    public static Parser<LiveCdnNodeView> parser() {
      return PARSER;
    }
    
    public Parser<LiveCdnNodeView> getParserForType() {
      return PARSER;
    }
    
    public LiveCdnNodeView getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }
  }
  
  public static Descriptors.FileDescriptor getDescriptor() {
    return descriptor;
  }
  
  static {
    String[] descriptorData = { "\n\025LiveCdnNodeView.proto\"@\n\017LiveCdnNodeView\022\013\n\003cdn\030\001 \001(\t\022\013\n\003url\030\002 \001(\t\022\023\n\013freeTraffic\030\003 \001(\bB<\n:tech.ordinaryroad.live.chat.client.codec.kuaishou.protobufb\006proto3" };
    descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[0]);
    internal_static_LiveCdnNodeView_descriptor = getDescriptor().getMessageTypes().get(0);
    internal_static_LiveCdnNodeView_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_LiveCdnNodeView_descriptor, new String[] { "Cdn", "Url", "FreeTraffic" });
  }
  
  public static interface LiveCdnNodeViewOrBuilder extends MessageOrBuilder {
    String getCdn();
    
    ByteString getCdnBytes();
    
    String getUrl();
    
    ByteString getUrlBytes();
    
    boolean getFreeTraffic();
  }
}
