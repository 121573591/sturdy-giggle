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

public final class SimpleUserInfoOuterClass {
  private static final Descriptors.Descriptor internal_static_SimpleUserInfo_descriptor;
  
  private static final GeneratedMessageV3.FieldAccessorTable internal_static_SimpleUserInfo_fieldAccessorTable;
  
  private static Descriptors.FileDescriptor descriptor;
  
  public static void registerAllExtensions(ExtensionRegistryLite registry) {}
  
  public static void registerAllExtensions(ExtensionRegistry registry) {
    registerAllExtensions((ExtensionRegistryLite)registry);
  }
  
  public static final class SimpleUserInfo extends GeneratedMessageV3 implements SimpleUserInfoOrBuilder {
    private static final long serialVersionUID = 0L;
    
    public static final int PRINCIPALID_FIELD_NUMBER = 1;
    
    private volatile Object principalId_;
    
    public static final int USERNAME_FIELD_NUMBER = 2;
    
    private volatile Object userName_;
    
    public static final int HEADURL_FIELD_NUMBER = 3;
    
    private volatile Object headUrl_;
    
    private byte memoizedIsInitialized;
    
    private SimpleUserInfo(GeneratedMessageV3.Builder<?> builder) {
      super(builder);
      this.principalId_ = "";
      this.userName_ = "";
      this.headUrl_ = "";
      this.memoizedIsInitialized = -1;
    }
    
    private SimpleUserInfo() {
      this.principalId_ = "";
      this.userName_ = "";
      this.headUrl_ = "";
      this.memoizedIsInitialized = -1;
      this.principalId_ = "";
      this.userName_ = "";
      this.headUrl_ = "";
    }
    
    protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
      return new SimpleUserInfo();
    }
    
    public static final Descriptors.Descriptor getDescriptor() {
      return SimpleUserInfoOuterClass.internal_static_SimpleUserInfo_descriptor;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return SimpleUserInfoOuterClass.internal_static_SimpleUserInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(SimpleUserInfo.class, Builder.class);
    }
    
    public String getPrincipalId() {
      Object ref = this.principalId_;
      if (ref instanceof String)
        return (String)ref; 
      ByteString bs = (ByteString)ref;
      String s = bs.toStringUtf8();
      this.principalId_ = s;
      return s;
    }
    
    public ByteString getPrincipalIdBytes() {
      Object ref = this.principalId_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.principalId_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public String getUserName() {
      Object ref = this.userName_;
      if (ref instanceof String)
        return (String)ref; 
      ByteString bs = (ByteString)ref;
      String s = bs.toStringUtf8();
      this.userName_ = s;
      return s;
    }
    
    public ByteString getUserNameBytes() {
      Object ref = this.userName_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.userName_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public String getHeadUrl() {
      Object ref = this.headUrl_;
      if (ref instanceof String)
        return (String)ref; 
      ByteString bs = (ByteString)ref;
      String s = bs.toStringUtf8();
      this.headUrl_ = s;
      return s;
    }
    
    public ByteString getHeadUrlBytes() {
      Object ref = this.headUrl_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.headUrl_ = b;
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
      if (!GeneratedMessageV3.isStringEmpty(this.principalId_))
        GeneratedMessageV3.writeString(output, 1, this.principalId_); 
      if (!GeneratedMessageV3.isStringEmpty(this.userName_))
        GeneratedMessageV3.writeString(output, 2, this.userName_); 
      if (!GeneratedMessageV3.isStringEmpty(this.headUrl_))
        GeneratedMessageV3.writeString(output, 3, this.headUrl_); 
      getUnknownFields().writeTo(output);
    }
    
    public int getSerializedSize() {
      int size = this.memoizedSize;
      if (size != -1)
        return size; 
      size = 0;
      if (!GeneratedMessageV3.isStringEmpty(this.principalId_))
        size += GeneratedMessageV3.computeStringSize(1, this.principalId_); 
      if (!GeneratedMessageV3.isStringEmpty(this.userName_))
        size += GeneratedMessageV3.computeStringSize(2, this.userName_); 
      if (!GeneratedMessageV3.isStringEmpty(this.headUrl_))
        size += GeneratedMessageV3.computeStringSize(3, this.headUrl_); 
      size += getUnknownFields().getSerializedSize();
      this.memoizedSize = size;
      return size;
    }
    
    public boolean equals(Object obj) {
      if (obj == this)
        return true; 
      if (!(obj instanceof SimpleUserInfo))
        return super.equals(obj); 
      SimpleUserInfo other = (SimpleUserInfo)obj;
      if (!getPrincipalId().equals(other.getPrincipalId()))
        return false; 
      if (!getUserName().equals(other.getUserName()))
        return false; 
      if (!getHeadUrl().equals(other.getHeadUrl()))
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
      hash = 53 * hash + getPrincipalId().hashCode();
      hash = 37 * hash + 2;
      hash = 53 * hash + getUserName().hashCode();
      hash = 37 * hash + 3;
      hash = 53 * hash + getHeadUrl().hashCode();
      hash = 29 * hash + getUnknownFields().hashCode();
      this.memoizedHashCode = hash;
      return hash;
    }
    
    public static SimpleUserInfo parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (SimpleUserInfo)PARSER.parseFrom(data);
    }
    
    public static SimpleUserInfo parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (SimpleUserInfo)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static SimpleUserInfo parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (SimpleUserInfo)PARSER.parseFrom(data);
    }
    
    public static SimpleUserInfo parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (SimpleUserInfo)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static SimpleUserInfo parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (SimpleUserInfo)PARSER.parseFrom(data);
    }
    
    public static SimpleUserInfo parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (SimpleUserInfo)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static SimpleUserInfo parseFrom(InputStream input) throws IOException {
      return 
        (SimpleUserInfo)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static SimpleUserInfo parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (SimpleUserInfo)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static SimpleUserInfo parseDelimitedFrom(InputStream input) throws IOException {
      return 
        (SimpleUserInfo)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }
    
    public static SimpleUserInfo parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (SimpleUserInfo)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static SimpleUserInfo parseFrom(CodedInputStream input) throws IOException {
      return 
        (SimpleUserInfo)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static SimpleUserInfo parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (SimpleUserInfo)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public Builder newBuilderForType() {
      return newBuilder();
    }
    
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    
    public static Builder newBuilder(SimpleUserInfo prototype) {
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
    
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements SimpleUserInfoOuterClass.SimpleUserInfoOrBuilder {
      private int bitField0_;
      
      private Object principalId_;
      
      private Object userName_;
      
      private Object headUrl_;
      
      public static final Descriptors.Descriptor getDescriptor() {
        return SimpleUserInfoOuterClass.internal_static_SimpleUserInfo_descriptor;
      }
      
      protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return SimpleUserInfoOuterClass.internal_static_SimpleUserInfo_fieldAccessorTable
          .ensureFieldAccessorsInitialized(SimpleUserInfoOuterClass.SimpleUserInfo.class, Builder.class);
      }
      
      private Builder() {
        this.principalId_ = "";
        this.userName_ = "";
        this.headUrl_ = "";
      }
      
      private Builder(GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        this.principalId_ = "";
        this.userName_ = "";
        this.headUrl_ = "";
      }
      
      public Builder clear() {
        super.clear();
        this.bitField0_ = 0;
        this.principalId_ = "";
        this.userName_ = "";
        this.headUrl_ = "";
        return this;
      }
      
      public Descriptors.Descriptor getDescriptorForType() {
        return SimpleUserInfoOuterClass.internal_static_SimpleUserInfo_descriptor;
      }
      
      public SimpleUserInfoOuterClass.SimpleUserInfo getDefaultInstanceForType() {
        return SimpleUserInfoOuterClass.SimpleUserInfo.getDefaultInstance();
      }
      
      public SimpleUserInfoOuterClass.SimpleUserInfo build() {
        SimpleUserInfoOuterClass.SimpleUserInfo result = buildPartial();
        if (!result.isInitialized())
          throw newUninitializedMessageException(result); 
        return result;
      }
      
      public SimpleUserInfoOuterClass.SimpleUserInfo buildPartial() {
        SimpleUserInfoOuterClass.SimpleUserInfo result = new SimpleUserInfoOuterClass.SimpleUserInfo(this);
        if (this.bitField0_ != 0)
          buildPartial0(result); 
        onBuilt();
        return result;
      }
      
      private void buildPartial0(SimpleUserInfoOuterClass.SimpleUserInfo result) {
        int from_bitField0_ = this.bitField0_;
        if ((from_bitField0_ & 0x1) != 0)
          result.principalId_ = this.principalId_; 
        if ((from_bitField0_ & 0x2) != 0)
          result.userName_ = this.userName_; 
        if ((from_bitField0_ & 0x4) != 0)
          result.headUrl_ = this.headUrl_; 
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
        if (other instanceof SimpleUserInfoOuterClass.SimpleUserInfo)
          return mergeFrom((SimpleUserInfoOuterClass.SimpleUserInfo)other); 
        super.mergeFrom(other);
        return this;
      }
      
      public Builder mergeFrom(SimpleUserInfoOuterClass.SimpleUserInfo other) {
        if (other == SimpleUserInfoOuterClass.SimpleUserInfo.getDefaultInstance())
          return this; 
        if (!other.getPrincipalId().isEmpty()) {
          this.principalId_ = other.principalId_;
          this.bitField0_ |= 0x1;
          onChanged();
        } 
        if (!other.getUserName().isEmpty()) {
          this.userName_ = other.userName_;
          this.bitField0_ |= 0x2;
          onChanged();
        } 
        if (!other.getHeadUrl().isEmpty()) {
          this.headUrl_ = other.headUrl_;
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
            int tag = input.readTag();
            switch (tag) {
              case 0:
                done = true;
                continue;
              case 10:
                this.principalId_ = input.readStringRequireUtf8();
                this.bitField0_ |= 0x1;
                continue;
              case 18:
                this.userName_ = input.readStringRequireUtf8();
                this.bitField0_ |= 0x2;
                continue;
              case 26:
                this.headUrl_ = input.readStringRequireUtf8();
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
      
      public String getPrincipalId() {
        Object ref = this.principalId_;
        if (!(ref instanceof String)) {
          ByteString bs = (ByteString)ref;
          String s = bs.toStringUtf8();
          this.principalId_ = s;
          return s;
        } 
        return (String)ref;
      }
      
      public ByteString getPrincipalIdBytes() {
        Object ref = this.principalId_;
        if (ref instanceof String) {
          ByteString b = ByteString.copyFromUtf8((String)ref);
          this.principalId_ = b;
          return b;
        } 
        return (ByteString)ref;
      }
      
      public Builder setPrincipalId(String value) {
        if (value == null)
          throw new NullPointerException(); 
        this.principalId_ = value;
        this.bitField0_ |= 0x1;
        onChanged();
        return this;
      }
      
      public Builder clearPrincipalId() {
        this.principalId_ = SimpleUserInfoOuterClass.SimpleUserInfo.getDefaultInstance().getPrincipalId();
        this.bitField0_ &= 0xFFFFFFFE;
        onChanged();
        return this;
      }
      
      public Builder setPrincipalIdBytes(ByteString value) {
        if (value == null)
          throw new NullPointerException(); 
        SimpleUserInfoOuterClass.SimpleUserInfo.checkByteStringIsUtf8(value);
        this.principalId_ = value;
        this.bitField0_ |= 0x1;
        onChanged();
        return this;
      }
      
      public String getUserName() {
        Object ref = this.userName_;
        if (!(ref instanceof String)) {
          ByteString bs = (ByteString)ref;
          String s = bs.toStringUtf8();
          this.userName_ = s;
          return s;
        } 
        return (String)ref;
      }
      
      public ByteString getUserNameBytes() {
        Object ref = this.userName_;
        if (ref instanceof String) {
          ByteString b = ByteString.copyFromUtf8((String)ref);
          this.userName_ = b;
          return b;
        } 
        return (ByteString)ref;
      }
      
      public Builder setUserName(String value) {
        if (value == null)
          throw new NullPointerException(); 
        this.userName_ = value;
        this.bitField0_ |= 0x2;
        onChanged();
        return this;
      }
      
      public Builder clearUserName() {
        this.userName_ = SimpleUserInfoOuterClass.SimpleUserInfo.getDefaultInstance().getUserName();
        this.bitField0_ &= 0xFFFFFFFD;
        onChanged();
        return this;
      }
      
      public Builder setUserNameBytes(ByteString value) {
        if (value == null)
          throw new NullPointerException(); 
        SimpleUserInfoOuterClass.SimpleUserInfo.checkByteStringIsUtf8(value);
        this.userName_ = value;
        this.bitField0_ |= 0x2;
        onChanged();
        return this;
      }
      
      public String getHeadUrl() {
        Object ref = this.headUrl_;
        if (!(ref instanceof String)) {
          ByteString bs = (ByteString)ref;
          String s = bs.toStringUtf8();
          this.headUrl_ = s;
          return s;
        } 
        return (String)ref;
      }
      
      public ByteString getHeadUrlBytes() {
        Object ref = this.headUrl_;
        if (ref instanceof String) {
          ByteString b = ByteString.copyFromUtf8((String)ref);
          this.headUrl_ = b;
          return b;
        } 
        return (ByteString)ref;
      }
      
      public Builder setHeadUrl(String value) {
        if (value == null)
          throw new NullPointerException(); 
        this.headUrl_ = value;
        this.bitField0_ |= 0x4;
        onChanged();
        return this;
      }
      
      public Builder clearHeadUrl() {
        this.headUrl_ = SimpleUserInfoOuterClass.SimpleUserInfo.getDefaultInstance().getHeadUrl();
        this.bitField0_ &= 0xFFFFFFFB;
        onChanged();
        return this;
      }
      
      public Builder setHeadUrlBytes(ByteString value) {
        if (value == null)
          throw new NullPointerException(); 
        SimpleUserInfoOuterClass.SimpleUserInfo.checkByteStringIsUtf8(value);
        this.headUrl_ = value;
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
    
    private static final SimpleUserInfo DEFAULT_INSTANCE = new SimpleUserInfo();
    
    public static SimpleUserInfo getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }
    
    private static final Parser<SimpleUserInfo> PARSER = (Parser<SimpleUserInfo>)new AbstractParser<SimpleUserInfo>() {
        public SimpleUserInfoOuterClass.SimpleUserInfo parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
          SimpleUserInfoOuterClass.SimpleUserInfo.Builder builder = SimpleUserInfoOuterClass.SimpleUserInfo.newBuilder();
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
    
    public static Parser<SimpleUserInfo> parser() {
      return PARSER;
    }
    
    public Parser<SimpleUserInfo> getParserForType() {
      return PARSER;
    }
    
    public SimpleUserInfo getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }
  }
  
  public static Descriptors.FileDescriptor getDescriptor() {
    return descriptor;
  }
  
  static {
    String[] descriptorData = { "\n\024SimpleUserInfo.proto\"H\n\016SimpleUserInfo\022\023\n\013principalId\030\001 \001(\t\022\020\n\buserName\030\002 \001(\t\022\017\n\007headUrl\030\003 \001(\tB<\n:tech.ordinaryroad.live.chat.client.codec.kuaishou.protobufb\006proto3" };
    descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[0]);
    internal_static_SimpleUserInfo_descriptor = getDescriptor().getMessageTypes().get(0);
    internal_static_SimpleUserInfo_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_SimpleUserInfo_descriptor, new String[] { "PrincipalId", "UserName", "HeadUrl" });
  }
  
  public static interface SimpleUserInfoOrBuilder extends MessageOrBuilder {
    String getPrincipalId();
    
    ByteString getPrincipalIdBytes();
    
    String getUserName();
    
    ByteString getUserNameBytes();
    
    String getHeadUrl();
    
    ByteString getHeadUrlBytes();
  }
}
