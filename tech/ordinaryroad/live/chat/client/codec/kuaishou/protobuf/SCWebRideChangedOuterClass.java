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

public final class SCWebRideChangedOuterClass {
  private static final Descriptors.Descriptor internal_static_SCWebRideChanged_descriptor;
  
  private static final GeneratedMessageV3.FieldAccessorTable internal_static_SCWebRideChanged_fieldAccessorTable;
  
  private static Descriptors.FileDescriptor descriptor;
  
  public static void registerAllExtensions(ExtensionRegistryLite registry) {}
  
  public static void registerAllExtensions(ExtensionRegistry registry) {
    registerAllExtensions((ExtensionRegistryLite)registry);
  }
  
  public static final class SCWebRideChanged extends GeneratedMessageV3 implements SCWebRideChangedOrBuilder {
    private static final long serialVersionUID = 0L;
    
    public static final int RIDEID_FIELD_NUMBER = 1;
    
    private volatile Object rideId_;
    
    public static final int REQUESTMAXDELAYMILLIS_FIELD_NUMBER = 2;
    
    private int requestMaxDelayMillis_;
    
    private byte memoizedIsInitialized;
    
    private SCWebRideChanged(GeneratedMessageV3.Builder<?> builder) {
      super(builder);
      this.rideId_ = "";
      this.requestMaxDelayMillis_ = 0;
      this.memoizedIsInitialized = -1;
    }
    
    private SCWebRideChanged() {
      this.rideId_ = "";
      this.requestMaxDelayMillis_ = 0;
      this.memoizedIsInitialized = -1;
      this.rideId_ = "";
    }
    
    protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
      return new SCWebRideChanged();
    }
    
    public static final Descriptors.Descriptor getDescriptor() {
      return SCWebRideChangedOuterClass.internal_static_SCWebRideChanged_descriptor;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return SCWebRideChangedOuterClass.internal_static_SCWebRideChanged_fieldAccessorTable.ensureFieldAccessorsInitialized(SCWebRideChanged.class, Builder.class);
    }
    
    public String getRideId() {
      Object ref = this.rideId_;
      if (ref instanceof String)
        return (String)ref; 
      ByteString bs = (ByteString)ref;
      String s = bs.toStringUtf8();
      this.rideId_ = s;
      return s;
    }
    
    public ByteString getRideIdBytes() {
      Object ref = this.rideId_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.rideId_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public int getRequestMaxDelayMillis() {
      return this.requestMaxDelayMillis_;
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
      if (!GeneratedMessageV3.isStringEmpty(this.rideId_))
        GeneratedMessageV3.writeString(output, 1, this.rideId_); 
      if (this.requestMaxDelayMillis_ != 0)
        output.writeUInt32(2, this.requestMaxDelayMillis_); 
      getUnknownFields().writeTo(output);
    }
    
    public int getSerializedSize() {
      int size = this.memoizedSize;
      if (size != -1)
        return size; 
      size = 0;
      if (!GeneratedMessageV3.isStringEmpty(this.rideId_))
        size += GeneratedMessageV3.computeStringSize(1, this.rideId_); 
      if (this.requestMaxDelayMillis_ != 0)
        size += 
          CodedOutputStream.computeUInt32Size(2, this.requestMaxDelayMillis_); 
      size += getUnknownFields().getSerializedSize();
      this.memoizedSize = size;
      return size;
    }
    
    public boolean equals(Object obj) {
      if (obj == this)
        return true; 
      if (!(obj instanceof SCWebRideChanged))
        return super.equals(obj); 
      SCWebRideChanged other = (SCWebRideChanged)obj;
      if (!getRideId().equals(other.getRideId()))
        return false; 
      if (getRequestMaxDelayMillis() != other
        .getRequestMaxDelayMillis())
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
      hash = 53 * hash + getRideId().hashCode();
      hash = 37 * hash + 2;
      hash = 53 * hash + getRequestMaxDelayMillis();
      hash = 29 * hash + getUnknownFields().hashCode();
      this.memoizedHashCode = hash;
      return hash;
    }
    
    public static SCWebRideChanged parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (SCWebRideChanged)PARSER.parseFrom(data);
    }
    
    public static SCWebRideChanged parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (SCWebRideChanged)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static SCWebRideChanged parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (SCWebRideChanged)PARSER.parseFrom(data);
    }
    
    public static SCWebRideChanged parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (SCWebRideChanged)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static SCWebRideChanged parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (SCWebRideChanged)PARSER.parseFrom(data);
    }
    
    public static SCWebRideChanged parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (SCWebRideChanged)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static SCWebRideChanged parseFrom(InputStream input) throws IOException {
      return 
        (SCWebRideChanged)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static SCWebRideChanged parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (SCWebRideChanged)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static SCWebRideChanged parseDelimitedFrom(InputStream input) throws IOException {
      return 
        (SCWebRideChanged)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }
    
    public static SCWebRideChanged parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (SCWebRideChanged)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static SCWebRideChanged parseFrom(CodedInputStream input) throws IOException {
      return 
        (SCWebRideChanged)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static SCWebRideChanged parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (SCWebRideChanged)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public Builder newBuilderForType() {
      return newBuilder();
    }
    
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    
    public static Builder newBuilder(SCWebRideChanged prototype) {
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
    
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements SCWebRideChangedOuterClass.SCWebRideChangedOrBuilder {
      private int bitField0_;
      
      private Object rideId_;
      
      private int requestMaxDelayMillis_;
      
      public static final Descriptors.Descriptor getDescriptor() {
        return SCWebRideChangedOuterClass.internal_static_SCWebRideChanged_descriptor;
      }
      
      protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return SCWebRideChangedOuterClass.internal_static_SCWebRideChanged_fieldAccessorTable
          .ensureFieldAccessorsInitialized(SCWebRideChangedOuterClass.SCWebRideChanged.class, Builder.class);
      }
      
      private Builder() {
        this.rideId_ = "";
      }
      
      private Builder(GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        this.rideId_ = "";
      }
      
      public Builder clear() {
        super.clear();
        this.bitField0_ = 0;
        this.rideId_ = "";
        this.requestMaxDelayMillis_ = 0;
        return this;
      }
      
      public Descriptors.Descriptor getDescriptorForType() {
        return SCWebRideChangedOuterClass.internal_static_SCWebRideChanged_descriptor;
      }
      
      public SCWebRideChangedOuterClass.SCWebRideChanged getDefaultInstanceForType() {
        return SCWebRideChangedOuterClass.SCWebRideChanged.getDefaultInstance();
      }
      
      public SCWebRideChangedOuterClass.SCWebRideChanged build() {
        SCWebRideChangedOuterClass.SCWebRideChanged result = buildPartial();
        if (!result.isInitialized())
          throw newUninitializedMessageException(result); 
        return result;
      }
      
      public SCWebRideChangedOuterClass.SCWebRideChanged buildPartial() {
        SCWebRideChangedOuterClass.SCWebRideChanged result = new SCWebRideChangedOuterClass.SCWebRideChanged(this);
        if (this.bitField0_ != 0)
          buildPartial0(result); 
        onBuilt();
        return result;
      }
      
      private void buildPartial0(SCWebRideChangedOuterClass.SCWebRideChanged result) {
        int from_bitField0_ = this.bitField0_;
        if ((from_bitField0_ & 0x1) != 0)
          result.rideId_ = this.rideId_; 
        if ((from_bitField0_ & 0x2) != 0)
          result.requestMaxDelayMillis_ = this.requestMaxDelayMillis_; 
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
        if (other instanceof SCWebRideChangedOuterClass.SCWebRideChanged)
          return mergeFrom((SCWebRideChangedOuterClass.SCWebRideChanged)other); 
        super.mergeFrom(other);
        return this;
      }
      
      public Builder mergeFrom(SCWebRideChangedOuterClass.SCWebRideChanged other) {
        if (other == SCWebRideChangedOuterClass.SCWebRideChanged.getDefaultInstance())
          return this; 
        if (!other.getRideId().isEmpty()) {
          this.rideId_ = other.rideId_;
          this.bitField0_ |= 0x1;
          onChanged();
        } 
        if (other.getRequestMaxDelayMillis() != 0)
          setRequestMaxDelayMillis(other.getRequestMaxDelayMillis()); 
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
                this.rideId_ = input.readStringRequireUtf8();
                this.bitField0_ |= 0x1;
                continue;
              case 16:
                this.requestMaxDelayMillis_ = input.readUInt32();
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
      
      public String getRideId() {
        Object ref = this.rideId_;
        if (!(ref instanceof String)) {
          ByteString bs = (ByteString)ref;
          String s = bs.toStringUtf8();
          this.rideId_ = s;
          return s;
        } 
        return (String)ref;
      }
      
      public ByteString getRideIdBytes() {
        Object ref = this.rideId_;
        if (ref instanceof String) {
          ByteString b = ByteString.copyFromUtf8((String)ref);
          this.rideId_ = b;
          return b;
        } 
        return (ByteString)ref;
      }
      
      public Builder setRideId(String value) {
        if (value == null)
          throw new NullPointerException(); 
        this.rideId_ = value;
        this.bitField0_ |= 0x1;
        onChanged();
        return this;
      }
      
      public Builder clearRideId() {
        this.rideId_ = SCWebRideChangedOuterClass.SCWebRideChanged.getDefaultInstance().getRideId();
        this.bitField0_ &= 0xFFFFFFFE;
        onChanged();
        return this;
      }
      
      public Builder setRideIdBytes(ByteString value) {
        if (value == null)
          throw new NullPointerException(); 
        SCWebRideChangedOuterClass.SCWebRideChanged.checkByteStringIsUtf8(value);
        this.rideId_ = value;
        this.bitField0_ |= 0x1;
        onChanged();
        return this;
      }
      
      public int getRequestMaxDelayMillis() {
        return this.requestMaxDelayMillis_;
      }
      
      public Builder setRequestMaxDelayMillis(int value) {
        this.requestMaxDelayMillis_ = value;
        this.bitField0_ |= 0x2;
        onChanged();
        return this;
      }
      
      public Builder clearRequestMaxDelayMillis() {
        this.bitField0_ &= 0xFFFFFFFD;
        this.requestMaxDelayMillis_ = 0;
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
    
    private static final SCWebRideChanged DEFAULT_INSTANCE = new SCWebRideChanged();
    
    public static SCWebRideChanged getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }
    
    private static final Parser<SCWebRideChanged> PARSER = (Parser<SCWebRideChanged>)new AbstractParser<SCWebRideChanged>() {
        public SCWebRideChangedOuterClass.SCWebRideChanged parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
          SCWebRideChangedOuterClass.SCWebRideChanged.Builder builder = SCWebRideChangedOuterClass.SCWebRideChanged.newBuilder();
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
    
    public static Parser<SCWebRideChanged> parser() {
      return PARSER;
    }
    
    public Parser<SCWebRideChanged> getParserForType() {
      return PARSER;
    }
    
    public SCWebRideChanged getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }
  }
  
  public static Descriptors.FileDescriptor getDescriptor() {
    return descriptor;
  }
  
  static {
    String[] descriptorData = { "\n\026SCWebRideChanged.proto\"A\n\020SCWebRideChanged\022\016\n\006rideId\030\001 \001(\t\022\035\n\025requestMaxDelayMillis\030\002 \001(\rB<\n:tech.ordinaryroad.live.chat.client.codec.kuaishou.protobufb\006proto3" };
    descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[0]);
    internal_static_SCWebRideChanged_descriptor = getDescriptor().getMessageTypes().get(0);
    internal_static_SCWebRideChanged_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_SCWebRideChanged_descriptor, new String[] { "RideId", "RequestMaxDelayMillis" });
  }
  
  public static interface SCWebRideChangedOrBuilder extends MessageOrBuilder {
    String getRideId();
    
    ByteString getRideIdBytes();
    
    int getRequestMaxDelayMillis();
  }
}
