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

public final class CSWebEnterRoomOuterClass {
  private static final Descriptors.Descriptor internal_static_CSWebEnterRoom_descriptor;
  
  private static final GeneratedMessageV3.FieldAccessorTable internal_static_CSWebEnterRoom_fieldAccessorTable;
  
  private static Descriptors.FileDescriptor descriptor;
  
  public static void registerAllExtensions(ExtensionRegistryLite registry) {}
  
  public static void registerAllExtensions(ExtensionRegistry registry) {
    registerAllExtensions((ExtensionRegistryLite)registry);
  }
  
  public static final class CSWebEnterRoom extends GeneratedMessageV3 implements CSWebEnterRoomOrBuilder {
    private static final long serialVersionUID = 0L;
    
    public static final int TOKEN_FIELD_NUMBER = 1;
    
    private volatile Object token_;
    
    public static final int LIVESTREAMID_FIELD_NUMBER = 2;
    
    private volatile Object liveStreamId_;
    
    public static final int RECONNECTCOUNT_FIELD_NUMBER = 3;
    
    private int reconnectCount_;
    
    public static final int LASTERRORCODE_FIELD_NUMBER = 4;
    
    private int lastErrorCode_;
    
    public static final int EXPTAG_FIELD_NUMBER = 5;
    
    private volatile Object expTag_;
    
    public static final int ATTACH_FIELD_NUMBER = 6;
    
    private volatile Object attach_;
    
    public static final int PAGEID_FIELD_NUMBER = 7;
    
    private volatile Object pageId_;
    
    private byte memoizedIsInitialized;
    
    private CSWebEnterRoom(GeneratedMessageV3.Builder<?> builder) {
      super(builder);
      this.token_ = "";
      this.liveStreamId_ = "";
      this.reconnectCount_ = 0;
      this.lastErrorCode_ = 0;
      this.expTag_ = "";
      this.attach_ = "";
      this.pageId_ = "";
      this.memoizedIsInitialized = -1;
    }
    
    private CSWebEnterRoom() {
      this.token_ = "";
      this.liveStreamId_ = "";
      this.reconnectCount_ = 0;
      this.lastErrorCode_ = 0;
      this.expTag_ = "";
      this.attach_ = "";
      this.pageId_ = "";
      this.memoizedIsInitialized = -1;
      this.token_ = "";
      this.liveStreamId_ = "";
      this.expTag_ = "";
      this.attach_ = "";
      this.pageId_ = "";
    }
    
    protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
      return new CSWebEnterRoom();
    }
    
    public static final Descriptors.Descriptor getDescriptor() {
      return CSWebEnterRoomOuterClass.internal_static_CSWebEnterRoom_descriptor;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return CSWebEnterRoomOuterClass.internal_static_CSWebEnterRoom_fieldAccessorTable.ensureFieldAccessorsInitialized(CSWebEnterRoom.class, Builder.class);
    }
    
    public String getToken() {
      Object ref = this.token_;
      if (ref instanceof String)
        return (String)ref; 
      ByteString bs = (ByteString)ref;
      String s = bs.toStringUtf8();
      this.token_ = s;
      return s;
    }
    
    public ByteString getTokenBytes() {
      Object ref = this.token_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.token_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public String getLiveStreamId() {
      Object ref = this.liveStreamId_;
      if (ref instanceof String)
        return (String)ref; 
      ByteString bs = (ByteString)ref;
      String s = bs.toStringUtf8();
      this.liveStreamId_ = s;
      return s;
    }
    
    public ByteString getLiveStreamIdBytes() {
      Object ref = this.liveStreamId_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.liveStreamId_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public int getReconnectCount() {
      return this.reconnectCount_;
    }
    
    public int getLastErrorCode() {
      return this.lastErrorCode_;
    }
    
    public String getExpTag() {
      Object ref = this.expTag_;
      if (ref instanceof String)
        return (String)ref; 
      ByteString bs = (ByteString)ref;
      String s = bs.toStringUtf8();
      this.expTag_ = s;
      return s;
    }
    
    public ByteString getExpTagBytes() {
      Object ref = this.expTag_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.expTag_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public String getAttach() {
      Object ref = this.attach_;
      if (ref instanceof String)
        return (String)ref; 
      ByteString bs = (ByteString)ref;
      String s = bs.toStringUtf8();
      this.attach_ = s;
      return s;
    }
    
    public ByteString getAttachBytes() {
      Object ref = this.attach_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.attach_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public String getPageId() {
      Object ref = this.pageId_;
      if (ref instanceof String)
        return (String)ref; 
      ByteString bs = (ByteString)ref;
      String s = bs.toStringUtf8();
      this.pageId_ = s;
      return s;
    }
    
    public ByteString getPageIdBytes() {
      Object ref = this.pageId_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.pageId_ = b;
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
      if (!GeneratedMessageV3.isStringEmpty(this.token_))
        GeneratedMessageV3.writeString(output, 1, this.token_); 
      if (!GeneratedMessageV3.isStringEmpty(this.liveStreamId_))
        GeneratedMessageV3.writeString(output, 2, this.liveStreamId_); 
      if (this.reconnectCount_ != 0)
        output.writeUInt32(3, this.reconnectCount_); 
      if (this.lastErrorCode_ != 0)
        output.writeUInt32(4, this.lastErrorCode_); 
      if (!GeneratedMessageV3.isStringEmpty(this.expTag_))
        GeneratedMessageV3.writeString(output, 5, this.expTag_); 
      if (!GeneratedMessageV3.isStringEmpty(this.attach_))
        GeneratedMessageV3.writeString(output, 6, this.attach_); 
      if (!GeneratedMessageV3.isStringEmpty(this.pageId_))
        GeneratedMessageV3.writeString(output, 7, this.pageId_); 
      getUnknownFields().writeTo(output);
    }
    
    public int getSerializedSize() {
      int size = this.memoizedSize;
      if (size != -1)
        return size; 
      size = 0;
      if (!GeneratedMessageV3.isStringEmpty(this.token_))
        size += GeneratedMessageV3.computeStringSize(1, this.token_); 
      if (!GeneratedMessageV3.isStringEmpty(this.liveStreamId_))
        size += GeneratedMessageV3.computeStringSize(2, this.liveStreamId_); 
      if (this.reconnectCount_ != 0)
        size += 
          CodedOutputStream.computeUInt32Size(3, this.reconnectCount_); 
      if (this.lastErrorCode_ != 0)
        size += 
          CodedOutputStream.computeUInt32Size(4, this.lastErrorCode_); 
      if (!GeneratedMessageV3.isStringEmpty(this.expTag_))
        size += GeneratedMessageV3.computeStringSize(5, this.expTag_); 
      if (!GeneratedMessageV3.isStringEmpty(this.attach_))
        size += GeneratedMessageV3.computeStringSize(6, this.attach_); 
      if (!GeneratedMessageV3.isStringEmpty(this.pageId_))
        size += GeneratedMessageV3.computeStringSize(7, this.pageId_); 
      size += getUnknownFields().getSerializedSize();
      this.memoizedSize = size;
      return size;
    }
    
    public boolean equals(Object obj) {
      if (obj == this)
        return true; 
      if (!(obj instanceof CSWebEnterRoom))
        return super.equals(obj); 
      CSWebEnterRoom other = (CSWebEnterRoom)obj;
      if (!getToken().equals(other.getToken()))
        return false; 
      if (!getLiveStreamId().equals(other.getLiveStreamId()))
        return false; 
      if (getReconnectCount() != other
        .getReconnectCount())
        return false; 
      if (getLastErrorCode() != other
        .getLastErrorCode())
        return false; 
      if (!getExpTag().equals(other.getExpTag()))
        return false; 
      if (!getAttach().equals(other.getAttach()))
        return false; 
      if (!getPageId().equals(other.getPageId()))
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
      hash = 53 * hash + getToken().hashCode();
      hash = 37 * hash + 2;
      hash = 53 * hash + getLiveStreamId().hashCode();
      hash = 37 * hash + 3;
      hash = 53 * hash + getReconnectCount();
      hash = 37 * hash + 4;
      hash = 53 * hash + getLastErrorCode();
      hash = 37 * hash + 5;
      hash = 53 * hash + getExpTag().hashCode();
      hash = 37 * hash + 6;
      hash = 53 * hash + getAttach().hashCode();
      hash = 37 * hash + 7;
      hash = 53 * hash + getPageId().hashCode();
      hash = 29 * hash + getUnknownFields().hashCode();
      this.memoizedHashCode = hash;
      return hash;
    }
    
    public static CSWebEnterRoom parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (CSWebEnterRoom)PARSER.parseFrom(data);
    }
    
    public static CSWebEnterRoom parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (CSWebEnterRoom)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static CSWebEnterRoom parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (CSWebEnterRoom)PARSER.parseFrom(data);
    }
    
    public static CSWebEnterRoom parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (CSWebEnterRoom)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static CSWebEnterRoom parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (CSWebEnterRoom)PARSER.parseFrom(data);
    }
    
    public static CSWebEnterRoom parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (CSWebEnterRoom)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static CSWebEnterRoom parseFrom(InputStream input) throws IOException {
      return 
        (CSWebEnterRoom)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static CSWebEnterRoom parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (CSWebEnterRoom)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static CSWebEnterRoom parseDelimitedFrom(InputStream input) throws IOException {
      return 
        (CSWebEnterRoom)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }
    
    public static CSWebEnterRoom parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (CSWebEnterRoom)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static CSWebEnterRoom parseFrom(CodedInputStream input) throws IOException {
      return 
        (CSWebEnterRoom)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static CSWebEnterRoom parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (CSWebEnterRoom)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public Builder newBuilderForType() {
      return newBuilder();
    }
    
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    
    public static Builder newBuilder(CSWebEnterRoom prototype) {
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
    
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements CSWebEnterRoomOuterClass.CSWebEnterRoomOrBuilder {
      private int bitField0_;
      
      private Object token_;
      
      private Object liveStreamId_;
      
      private int reconnectCount_;
      
      private int lastErrorCode_;
      
      private Object expTag_;
      
      private Object attach_;
      
      private Object pageId_;
      
      public static final Descriptors.Descriptor getDescriptor() {
        return CSWebEnterRoomOuterClass.internal_static_CSWebEnterRoom_descriptor;
      }
      
      protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return CSWebEnterRoomOuterClass.internal_static_CSWebEnterRoom_fieldAccessorTable
          .ensureFieldAccessorsInitialized(CSWebEnterRoomOuterClass.CSWebEnterRoom.class, Builder.class);
      }
      
      private Builder() {
        this.token_ = "";
        this.liveStreamId_ = "";
        this.expTag_ = "";
        this.attach_ = "";
        this.pageId_ = "";
      }
      
      private Builder(GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        this.token_ = "";
        this.liveStreamId_ = "";
        this.expTag_ = "";
        this.attach_ = "";
        this.pageId_ = "";
      }
      
      public Builder clear() {
        super.clear();
        this.bitField0_ = 0;
        this.token_ = "";
        this.liveStreamId_ = "";
        this.reconnectCount_ = 0;
        this.lastErrorCode_ = 0;
        this.expTag_ = "";
        this.attach_ = "";
        this.pageId_ = "";
        return this;
      }
      
      public Descriptors.Descriptor getDescriptorForType() {
        return CSWebEnterRoomOuterClass.internal_static_CSWebEnterRoom_descriptor;
      }
      
      public CSWebEnterRoomOuterClass.CSWebEnterRoom getDefaultInstanceForType() {
        return CSWebEnterRoomOuterClass.CSWebEnterRoom.getDefaultInstance();
      }
      
      public CSWebEnterRoomOuterClass.CSWebEnterRoom build() {
        CSWebEnterRoomOuterClass.CSWebEnterRoom result = buildPartial();
        if (!result.isInitialized())
          throw newUninitializedMessageException(result); 
        return result;
      }
      
      public CSWebEnterRoomOuterClass.CSWebEnterRoom buildPartial() {
        CSWebEnterRoomOuterClass.CSWebEnterRoom result = new CSWebEnterRoomOuterClass.CSWebEnterRoom(this);
        if (this.bitField0_ != 0)
          buildPartial0(result); 
        onBuilt();
        return result;
      }
      
      private void buildPartial0(CSWebEnterRoomOuterClass.CSWebEnterRoom result) {
        int from_bitField0_ = this.bitField0_;
        if ((from_bitField0_ & 0x1) != 0)
          result.token_ = this.token_; 
        if ((from_bitField0_ & 0x2) != 0)
          result.liveStreamId_ = this.liveStreamId_; 
        if ((from_bitField0_ & 0x4) != 0)
          result.reconnectCount_ = this.reconnectCount_; 
        if ((from_bitField0_ & 0x8) != 0)
          result.lastErrorCode_ = this.lastErrorCode_; 
        if ((from_bitField0_ & 0x10) != 0)
          result.expTag_ = this.expTag_; 
        if ((from_bitField0_ & 0x20) != 0)
          result.attach_ = this.attach_; 
        if ((from_bitField0_ & 0x40) != 0)
          result.pageId_ = this.pageId_; 
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
        if (other instanceof CSWebEnterRoomOuterClass.CSWebEnterRoom)
          return mergeFrom((CSWebEnterRoomOuterClass.CSWebEnterRoom)other); 
        super.mergeFrom(other);
        return this;
      }
      
      public Builder mergeFrom(CSWebEnterRoomOuterClass.CSWebEnterRoom other) {
        if (other == CSWebEnterRoomOuterClass.CSWebEnterRoom.getDefaultInstance())
          return this; 
        if (!other.getToken().isEmpty()) {
          this.token_ = other.token_;
          this.bitField0_ |= 0x1;
          onChanged();
        } 
        if (!other.getLiveStreamId().isEmpty()) {
          this.liveStreamId_ = other.liveStreamId_;
          this.bitField0_ |= 0x2;
          onChanged();
        } 
        if (other.getReconnectCount() != 0)
          setReconnectCount(other.getReconnectCount()); 
        if (other.getLastErrorCode() != 0)
          setLastErrorCode(other.getLastErrorCode()); 
        if (!other.getExpTag().isEmpty()) {
          this.expTag_ = other.expTag_;
          this.bitField0_ |= 0x10;
          onChanged();
        } 
        if (!other.getAttach().isEmpty()) {
          this.attach_ = other.attach_;
          this.bitField0_ |= 0x20;
          onChanged();
        } 
        if (!other.getPageId().isEmpty()) {
          this.pageId_ = other.pageId_;
          this.bitField0_ |= 0x40;
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
                this.token_ = input.readStringRequireUtf8();
                this.bitField0_ |= 0x1;
                continue;
              case 18:
                this.liveStreamId_ = input.readStringRequireUtf8();
                this.bitField0_ |= 0x2;
                continue;
              case 24:
                this.reconnectCount_ = input.readUInt32();
                this.bitField0_ |= 0x4;
                continue;
              case 32:
                this.lastErrorCode_ = input.readUInt32();
                this.bitField0_ |= 0x8;
                continue;
              case 42:
                this.expTag_ = input.readStringRequireUtf8();
                this.bitField0_ |= 0x10;
                continue;
              case 50:
                this.attach_ = input.readStringRequireUtf8();
                this.bitField0_ |= 0x20;
                continue;
              case 58:
                this.pageId_ = input.readStringRequireUtf8();
                this.bitField0_ |= 0x40;
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
      
      public String getToken() {
        Object ref = this.token_;
        if (!(ref instanceof String)) {
          ByteString bs = (ByteString)ref;
          String s = bs.toStringUtf8();
          this.token_ = s;
          return s;
        } 
        return (String)ref;
      }
      
      public ByteString getTokenBytes() {
        Object ref = this.token_;
        if (ref instanceof String) {
          ByteString b = ByteString.copyFromUtf8((String)ref);
          this.token_ = b;
          return b;
        } 
        return (ByteString)ref;
      }
      
      public Builder setToken(String value) {
        if (value == null)
          throw new NullPointerException(); 
        this.token_ = value;
        this.bitField0_ |= 0x1;
        onChanged();
        return this;
      }
      
      public Builder clearToken() {
        this.token_ = CSWebEnterRoomOuterClass.CSWebEnterRoom.getDefaultInstance().getToken();
        this.bitField0_ &= 0xFFFFFFFE;
        onChanged();
        return this;
      }
      
      public Builder setTokenBytes(ByteString value) {
        if (value == null)
          throw new NullPointerException(); 
        CSWebEnterRoomOuterClass.CSWebEnterRoom.checkByteStringIsUtf8(value);
        this.token_ = value;
        this.bitField0_ |= 0x1;
        onChanged();
        return this;
      }
      
      public String getLiveStreamId() {
        Object ref = this.liveStreamId_;
        if (!(ref instanceof String)) {
          ByteString bs = (ByteString)ref;
          String s = bs.toStringUtf8();
          this.liveStreamId_ = s;
          return s;
        } 
        return (String)ref;
      }
      
      public ByteString getLiveStreamIdBytes() {
        Object ref = this.liveStreamId_;
        if (ref instanceof String) {
          ByteString b = ByteString.copyFromUtf8((String)ref);
          this.liveStreamId_ = b;
          return b;
        } 
        return (ByteString)ref;
      }
      
      public Builder setLiveStreamId(String value) {
        if (value == null)
          throw new NullPointerException(); 
        this.liveStreamId_ = value;
        this.bitField0_ |= 0x2;
        onChanged();
        return this;
      }
      
      public Builder clearLiveStreamId() {
        this.liveStreamId_ = CSWebEnterRoomOuterClass.CSWebEnterRoom.getDefaultInstance().getLiveStreamId();
        this.bitField0_ &= 0xFFFFFFFD;
        onChanged();
        return this;
      }
      
      public Builder setLiveStreamIdBytes(ByteString value) {
        if (value == null)
          throw new NullPointerException(); 
        CSWebEnterRoomOuterClass.CSWebEnterRoom.checkByteStringIsUtf8(value);
        this.liveStreamId_ = value;
        this.bitField0_ |= 0x2;
        onChanged();
        return this;
      }
      
      public int getReconnectCount() {
        return this.reconnectCount_;
      }
      
      public Builder setReconnectCount(int value) {
        this.reconnectCount_ = value;
        this.bitField0_ |= 0x4;
        onChanged();
        return this;
      }
      
      public Builder clearReconnectCount() {
        this.bitField0_ &= 0xFFFFFFFB;
        this.reconnectCount_ = 0;
        onChanged();
        return this;
      }
      
      public int getLastErrorCode() {
        return this.lastErrorCode_;
      }
      
      public Builder setLastErrorCode(int value) {
        this.lastErrorCode_ = value;
        this.bitField0_ |= 0x8;
        onChanged();
        return this;
      }
      
      public Builder clearLastErrorCode() {
        this.bitField0_ &= 0xFFFFFFF7;
        this.lastErrorCode_ = 0;
        onChanged();
        return this;
      }
      
      public String getExpTag() {
        Object ref = this.expTag_;
        if (!(ref instanceof String)) {
          ByteString bs = (ByteString)ref;
          String s = bs.toStringUtf8();
          this.expTag_ = s;
          return s;
        } 
        return (String)ref;
      }
      
      public ByteString getExpTagBytes() {
        Object ref = this.expTag_;
        if (ref instanceof String) {
          ByteString b = ByteString.copyFromUtf8((String)ref);
          this.expTag_ = b;
          return b;
        } 
        return (ByteString)ref;
      }
      
      public Builder setExpTag(String value) {
        if (value == null)
          throw new NullPointerException(); 
        this.expTag_ = value;
        this.bitField0_ |= 0x10;
        onChanged();
        return this;
      }
      
      public Builder clearExpTag() {
        this.expTag_ = CSWebEnterRoomOuterClass.CSWebEnterRoom.getDefaultInstance().getExpTag();
        this.bitField0_ &= 0xFFFFFFEF;
        onChanged();
        return this;
      }
      
      public Builder setExpTagBytes(ByteString value) {
        if (value == null)
          throw new NullPointerException(); 
        CSWebEnterRoomOuterClass.CSWebEnterRoom.checkByteStringIsUtf8(value);
        this.expTag_ = value;
        this.bitField0_ |= 0x10;
        onChanged();
        return this;
      }
      
      public String getAttach() {
        Object ref = this.attach_;
        if (!(ref instanceof String)) {
          ByteString bs = (ByteString)ref;
          String s = bs.toStringUtf8();
          this.attach_ = s;
          return s;
        } 
        return (String)ref;
      }
      
      public ByteString getAttachBytes() {
        Object ref = this.attach_;
        if (ref instanceof String) {
          ByteString b = ByteString.copyFromUtf8((String)ref);
          this.attach_ = b;
          return b;
        } 
        return (ByteString)ref;
      }
      
      public Builder setAttach(String value) {
        if (value == null)
          throw new NullPointerException(); 
        this.attach_ = value;
        this.bitField0_ |= 0x20;
        onChanged();
        return this;
      }
      
      public Builder clearAttach() {
        this.attach_ = CSWebEnterRoomOuterClass.CSWebEnterRoom.getDefaultInstance().getAttach();
        this.bitField0_ &= 0xFFFFFFDF;
        onChanged();
        return this;
      }
      
      public Builder setAttachBytes(ByteString value) {
        if (value == null)
          throw new NullPointerException(); 
        CSWebEnterRoomOuterClass.CSWebEnterRoom.checkByteStringIsUtf8(value);
        this.attach_ = value;
        this.bitField0_ |= 0x20;
        onChanged();
        return this;
      }
      
      public String getPageId() {
        Object ref = this.pageId_;
        if (!(ref instanceof String)) {
          ByteString bs = (ByteString)ref;
          String s = bs.toStringUtf8();
          this.pageId_ = s;
          return s;
        } 
        return (String)ref;
      }
      
      public ByteString getPageIdBytes() {
        Object ref = this.pageId_;
        if (ref instanceof String) {
          ByteString b = ByteString.copyFromUtf8((String)ref);
          this.pageId_ = b;
          return b;
        } 
        return (ByteString)ref;
      }
      
      public Builder setPageId(String value) {
        if (value == null)
          throw new NullPointerException(); 
        this.pageId_ = value;
        this.bitField0_ |= 0x40;
        onChanged();
        return this;
      }
      
      public Builder clearPageId() {
        this.pageId_ = CSWebEnterRoomOuterClass.CSWebEnterRoom.getDefaultInstance().getPageId();
        this.bitField0_ &= 0xFFFFFFBF;
        onChanged();
        return this;
      }
      
      public Builder setPageIdBytes(ByteString value) {
        if (value == null)
          throw new NullPointerException(); 
        CSWebEnterRoomOuterClass.CSWebEnterRoom.checkByteStringIsUtf8(value);
        this.pageId_ = value;
        this.bitField0_ |= 0x40;
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
    
    private static final CSWebEnterRoom DEFAULT_INSTANCE = new CSWebEnterRoom();
    
    public static CSWebEnterRoom getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }
    
    private static final Parser<CSWebEnterRoom> PARSER = (Parser<CSWebEnterRoom>)new AbstractParser<CSWebEnterRoom>() {
        public CSWebEnterRoomOuterClass.CSWebEnterRoom parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
          CSWebEnterRoomOuterClass.CSWebEnterRoom.Builder builder = CSWebEnterRoomOuterClass.CSWebEnterRoom.newBuilder();
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
    
    public static Parser<CSWebEnterRoom> parser() {
      return PARSER;
    }
    
    public Parser<CSWebEnterRoom> getParserForType() {
      return PARSER;
    }
    
    public CSWebEnterRoom getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }
  }
  
  public static Descriptors.FileDescriptor getDescriptor() {
    return descriptor;
  }
  
  static {
    String[] descriptorData = { "\n\024CSWebEnterRoom.proto\"\001\n\016CSWebEnterRoom\022\r\n\005token\030\001 \001(\t\022\024\n\fliveStreamId\030\002 \001(\t\022\026\n\016reconnectCount\030\003 \001(\r\022\025\n\rlastErrorCode\030\004 \001(\r\022\016\n\006expTag\030\005 \001(\t\022\016\n\006attach\030\006 \001(\t\022\016\n\006pageId\030\007 \001(\tB<\n:tech.ordinaryroad.live.chat.client.codec.kuaishou.protobufb\006proto3" };
    descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[0]);
    internal_static_CSWebEnterRoom_descriptor = getDescriptor().getMessageTypes().get(0);
    internal_static_CSWebEnterRoom_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_CSWebEnterRoom_descriptor, new String[] { "Token", "LiveStreamId", "ReconnectCount", "LastErrorCode", "ExpTag", "Attach", "PageId" });
  }
  
  public static interface CSWebEnterRoomOrBuilder extends MessageOrBuilder {
    String getToken();
    
    ByteString getTokenBytes();
    
    String getLiveStreamId();
    
    ByteString getLiveStreamIdBytes();
    
    int getReconnectCount();
    
    int getLastErrorCode();
    
    String getExpTag();
    
    ByteString getExpTagBytes();
    
    String getAttach();
    
    ByteString getAttachBytes();
    
    String getPageId();
    
    ByteString getPageIdBytes();
  }
}
