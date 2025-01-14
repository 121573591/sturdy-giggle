package tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf;

import com.google.protobuf.AbstractMessage;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.MessageLite;
import com.google.protobuf.Parser;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class RoomMessage extends GeneratedMessageV3 implements RoomMessageOrBuilder {
  private static final long serialVersionUID = 0L;
  
  private byte memoizedIsInitialized;
  
  private RoomMessage(GeneratedMessageV3.Builder<?> builder) {
    super(builder);
    this.memoizedIsInitialized = -1;
  }
  
  private RoomMessage() {
    this.memoizedIsInitialized = -1;
  }
  
  protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
    return new RoomMessage();
  }
  
  public static final Descriptors.Descriptor getDescriptor() {
    return Tiktok.internal_static_RoomMessage_descriptor;
  }
  
  protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
    return Tiktok.internal_static_RoomMessage_fieldAccessorTable.ensureFieldAccessorsInitialized(RoomMessage.class, Builder.class);
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
    getUnknownFields().writeTo(output);
  }
  
  public int getSerializedSize() {
    int size = this.memoizedSize;
    if (size != -1)
      return size; 
    size = 0;
    size += getUnknownFields().getSerializedSize();
    this.memoizedSize = size;
    return size;
  }
  
  public boolean equals(Object obj) {
    if (obj == this)
      return true; 
    if (!(obj instanceof RoomMessage))
      return super.equals(obj); 
    RoomMessage other = (RoomMessage)obj;
    if (!getUnknownFields().equals(other.getUnknownFields()))
      return false; 
    return true;
  }
  
  public int hashCode() {
    if (this.memoizedHashCode != 0)
      return this.memoizedHashCode; 
    int hash = 41;
    hash = 19 * hash + getDescriptor().hashCode();
    hash = 29 * hash + getUnknownFields().hashCode();
    this.memoizedHashCode = hash;
    return hash;
  }
  
  public static RoomMessage parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
    return (RoomMessage)PARSER.parseFrom(data);
  }
  
  public static RoomMessage parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (RoomMessage)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static RoomMessage parseFrom(ByteString data) throws InvalidProtocolBufferException {
    return (RoomMessage)PARSER.parseFrom(data);
  }
  
  public static RoomMessage parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (RoomMessage)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static RoomMessage parseFrom(byte[] data) throws InvalidProtocolBufferException {
    return (RoomMessage)PARSER.parseFrom(data);
  }
  
  public static RoomMessage parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (RoomMessage)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static RoomMessage parseFrom(InputStream input) throws IOException {
    return 
      (RoomMessage)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static RoomMessage parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (RoomMessage)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static RoomMessage parseDelimitedFrom(InputStream input) throws IOException {
    return 
      (RoomMessage)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
  }
  
  public static RoomMessage parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (RoomMessage)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static RoomMessage parseFrom(CodedInputStream input) throws IOException {
    return 
      (RoomMessage)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static RoomMessage parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (RoomMessage)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public Builder newBuilderForType() {
    return newBuilder();
  }
  
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  
  public static Builder newBuilder(RoomMessage prototype) {
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
  
  public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements RoomMessageOrBuilder {
    public static final Descriptors.Descriptor getDescriptor() {
      return Tiktok.internal_static_RoomMessage_descriptor;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return Tiktok.internal_static_RoomMessage_fieldAccessorTable
        .ensureFieldAccessorsInitialized(RoomMessage.class, Builder.class);
    }
    
    private Builder() {}
    
    private Builder(GeneratedMessageV3.BuilderParent parent) {
      super(parent);
    }
    
    public Builder clear() {
      super.clear();
      return this;
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
      return Tiktok.internal_static_RoomMessage_descriptor;
    }
    
    public RoomMessage getDefaultInstanceForType() {
      return RoomMessage.getDefaultInstance();
    }
    
    public RoomMessage build() {
      RoomMessage result = buildPartial();
      if (!result.isInitialized())
        throw newUninitializedMessageException(result); 
      return result;
    }
    
    public RoomMessage buildPartial() {
      RoomMessage result = new RoomMessage(this);
      onBuilt();
      return result;
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
      if (other instanceof RoomMessage)
        return mergeFrom((RoomMessage)other); 
      super.mergeFrom(other);
      return this;
    }
    
    public Builder mergeFrom(RoomMessage other) {
      if (other == RoomMessage.getDefaultInstance())
        return this; 
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
    
    public final Builder setUnknownFields(UnknownFieldSet unknownFields) {
      return (Builder)super.setUnknownFields(unknownFields);
    }
    
    public final Builder mergeUnknownFields(UnknownFieldSet unknownFields) {
      return (Builder)super.mergeUnknownFields(unknownFields);
    }
  }
  
  private static final RoomMessage DEFAULT_INSTANCE = new RoomMessage();
  
  public static RoomMessage getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }
  
  private static final Parser<RoomMessage> PARSER = (Parser<RoomMessage>)new AbstractParser<RoomMessage>() {
      public RoomMessage parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        RoomMessage.Builder builder = RoomMessage.newBuilder();
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
  
  public static Parser<RoomMessage> parser() {
    return PARSER;
  }
  
  public Parser<RoomMessage> getParserForType() {
    return PARSER;
  }
  
  public RoomMessage getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }
}
