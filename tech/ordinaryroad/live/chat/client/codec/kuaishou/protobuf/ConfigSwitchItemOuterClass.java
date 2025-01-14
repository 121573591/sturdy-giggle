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

public final class ConfigSwitchItemOuterClass {
  private static final Descriptors.Descriptor internal_static_ConfigSwitchItem_descriptor;
  
  private static final GeneratedMessageV3.FieldAccessorTable internal_static_ConfigSwitchItem_fieldAccessorTable;
  
  private static Descriptors.FileDescriptor descriptor;
  
  public static void registerAllExtensions(ExtensionRegistryLite registry) {}
  
  public static void registerAllExtensions(ExtensionRegistry registry) {
    registerAllExtensions((ExtensionRegistryLite)registry);
  }
  
  public static final class ConfigSwitchItem extends GeneratedMessageV3 implements ConfigSwitchItemOrBuilder {
    private static final long serialVersionUID = 0L;
    
    public static final int CONFIGSWITCHTYPE_FIELD_NUMBER = 1;
    
    private int configSwitchType_;
    
    public static final int VALUE_FIELD_NUMBER = 2;
    
    private boolean value_;
    
    private byte memoizedIsInitialized;
    
    private ConfigSwitchItem(GeneratedMessageV3.Builder<?> builder) {
      super(builder);
      this.configSwitchType_ = 0;
      this.value_ = false;
      this.memoizedIsInitialized = -1;
    }
    
    private ConfigSwitchItem() {
      this.configSwitchType_ = 0;
      this.value_ = false;
      this.memoizedIsInitialized = -1;
      this.configSwitchType_ = 0;
    }
    
    protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
      return new ConfigSwitchItem();
    }
    
    public static final Descriptors.Descriptor getDescriptor() {
      return ConfigSwitchItemOuterClass.internal_static_ConfigSwitchItem_descriptor;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return ConfigSwitchItemOuterClass.internal_static_ConfigSwitchItem_fieldAccessorTable.ensureFieldAccessorsInitialized(ConfigSwitchItem.class, Builder.class);
    }
    
    public int getConfigSwitchTypeValue() {
      return this.configSwitchType_;
    }
    
    public ConfigSwitchTypeOuterClass.ConfigSwitchType getConfigSwitchType() {
      ConfigSwitchTypeOuterClass.ConfigSwitchType result = ConfigSwitchTypeOuterClass.ConfigSwitchType.forNumber(this.configSwitchType_);
      return (result == null) ? ConfigSwitchTypeOuterClass.ConfigSwitchType.UNRECOGNIZED : result;
    }
    
    public boolean getValue() {
      return this.value_;
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
      if (this.configSwitchType_ != ConfigSwitchTypeOuterClass.ConfigSwitchType.UNKNOWN.getNumber())
        output.writeEnum(1, this.configSwitchType_); 
      if (this.value_)
        output.writeBool(2, this.value_); 
      getUnknownFields().writeTo(output);
    }
    
    public int getSerializedSize() {
      int size = this.memoizedSize;
      if (size != -1)
        return size; 
      size = 0;
      if (this.configSwitchType_ != ConfigSwitchTypeOuterClass.ConfigSwitchType.UNKNOWN.getNumber())
        size += 
          CodedOutputStream.computeEnumSize(1, this.configSwitchType_); 
      if (this.value_)
        size += 
          CodedOutputStream.computeBoolSize(2, this.value_); 
      size += getUnknownFields().getSerializedSize();
      this.memoizedSize = size;
      return size;
    }
    
    public boolean equals(Object obj) {
      if (obj == this)
        return true; 
      if (!(obj instanceof ConfigSwitchItem))
        return super.equals(obj); 
      ConfigSwitchItem other = (ConfigSwitchItem)obj;
      if (this.configSwitchType_ != other.configSwitchType_)
        return false; 
      if (getValue() != other
        .getValue())
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
      hash = 53 * hash + this.configSwitchType_;
      hash = 37 * hash + 2;
      hash = 53 * hash + Internal.hashBoolean(
          getValue());
      hash = 29 * hash + getUnknownFields().hashCode();
      this.memoizedHashCode = hash;
      return hash;
    }
    
    public static ConfigSwitchItem parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (ConfigSwitchItem)PARSER.parseFrom(data);
    }
    
    public static ConfigSwitchItem parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (ConfigSwitchItem)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static ConfigSwitchItem parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (ConfigSwitchItem)PARSER.parseFrom(data);
    }
    
    public static ConfigSwitchItem parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (ConfigSwitchItem)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static ConfigSwitchItem parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (ConfigSwitchItem)PARSER.parseFrom(data);
    }
    
    public static ConfigSwitchItem parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (ConfigSwitchItem)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static ConfigSwitchItem parseFrom(InputStream input) throws IOException {
      return 
        (ConfigSwitchItem)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static ConfigSwitchItem parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (ConfigSwitchItem)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static ConfigSwitchItem parseDelimitedFrom(InputStream input) throws IOException {
      return 
        (ConfigSwitchItem)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }
    
    public static ConfigSwitchItem parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (ConfigSwitchItem)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static ConfigSwitchItem parseFrom(CodedInputStream input) throws IOException {
      return 
        (ConfigSwitchItem)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static ConfigSwitchItem parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (ConfigSwitchItem)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public Builder newBuilderForType() {
      return newBuilder();
    }
    
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    
    public static Builder newBuilder(ConfigSwitchItem prototype) {
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
    
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ConfigSwitchItemOuterClass.ConfigSwitchItemOrBuilder {
      private int bitField0_;
      
      private int configSwitchType_;
      
      private boolean value_;
      
      public static final Descriptors.Descriptor getDescriptor() {
        return ConfigSwitchItemOuterClass.internal_static_ConfigSwitchItem_descriptor;
      }
      
      protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return ConfigSwitchItemOuterClass.internal_static_ConfigSwitchItem_fieldAccessorTable
          .ensureFieldAccessorsInitialized(ConfigSwitchItemOuterClass.ConfigSwitchItem.class, Builder.class);
      }
      
      private Builder() {
        this.configSwitchType_ = 0;
      }
      
      private Builder(GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        this.configSwitchType_ = 0;
      }
      
      public Builder clear() {
        super.clear();
        this.bitField0_ = 0;
        this.configSwitchType_ = 0;
        this.value_ = false;
        return this;
      }
      
      public Descriptors.Descriptor getDescriptorForType() {
        return ConfigSwitchItemOuterClass.internal_static_ConfigSwitchItem_descriptor;
      }
      
      public ConfigSwitchItemOuterClass.ConfigSwitchItem getDefaultInstanceForType() {
        return ConfigSwitchItemOuterClass.ConfigSwitchItem.getDefaultInstance();
      }
      
      public ConfigSwitchItemOuterClass.ConfigSwitchItem build() {
        ConfigSwitchItemOuterClass.ConfigSwitchItem result = buildPartial();
        if (!result.isInitialized())
          throw newUninitializedMessageException(result); 
        return result;
      }
      
      public ConfigSwitchItemOuterClass.ConfigSwitchItem buildPartial() {
        ConfigSwitchItemOuterClass.ConfigSwitchItem result = new ConfigSwitchItemOuterClass.ConfigSwitchItem(this);
        if (this.bitField0_ != 0)
          buildPartial0(result); 
        onBuilt();
        return result;
      }
      
      private void buildPartial0(ConfigSwitchItemOuterClass.ConfigSwitchItem result) {
        int from_bitField0_ = this.bitField0_;
        if ((from_bitField0_ & 0x1) != 0)
          result.configSwitchType_ = this.configSwitchType_; 
        if ((from_bitField0_ & 0x2) != 0)
          result.value_ = this.value_; 
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
        if (other instanceof ConfigSwitchItemOuterClass.ConfigSwitchItem)
          return mergeFrom((ConfigSwitchItemOuterClass.ConfigSwitchItem)other); 
        super.mergeFrom(other);
        return this;
      }
      
      public Builder mergeFrom(ConfigSwitchItemOuterClass.ConfigSwitchItem other) {
        if (other == ConfigSwitchItemOuterClass.ConfigSwitchItem.getDefaultInstance())
          return this; 
        if (other.configSwitchType_ != 0)
          setConfigSwitchTypeValue(other.getConfigSwitchTypeValue()); 
        if (other.getValue())
          setValue(other.getValue()); 
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
              case 8:
                this.configSwitchType_ = input.readEnum();
                this.bitField0_ |= 0x1;
                continue;
              case 16:
                this.value_ = input.readBool();
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
      
      public int getConfigSwitchTypeValue() {
        return this.configSwitchType_;
      }
      
      public Builder setConfigSwitchTypeValue(int value) {
        this.configSwitchType_ = value;
        this.bitField0_ |= 0x1;
        onChanged();
        return this;
      }
      
      public ConfigSwitchTypeOuterClass.ConfigSwitchType getConfigSwitchType() {
        ConfigSwitchTypeOuterClass.ConfigSwitchType result = ConfigSwitchTypeOuterClass.ConfigSwitchType.forNumber(this.configSwitchType_);
        return (result == null) ? ConfigSwitchTypeOuterClass.ConfigSwitchType.UNRECOGNIZED : result;
      }
      
      public Builder setConfigSwitchType(ConfigSwitchTypeOuterClass.ConfigSwitchType value) {
        if (value == null)
          throw new NullPointerException(); 
        this.bitField0_ |= 0x1;
        this.configSwitchType_ = value.getNumber();
        onChanged();
        return this;
      }
      
      public Builder clearConfigSwitchType() {
        this.bitField0_ &= 0xFFFFFFFE;
        this.configSwitchType_ = 0;
        onChanged();
        return this;
      }
      
      public boolean getValue() {
        return this.value_;
      }
      
      public Builder setValue(boolean value) {
        this.value_ = value;
        this.bitField0_ |= 0x2;
        onChanged();
        return this;
      }
      
      public Builder clearValue() {
        this.bitField0_ &= 0xFFFFFFFD;
        this.value_ = false;
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
    
    private static final ConfigSwitchItem DEFAULT_INSTANCE = new ConfigSwitchItem();
    
    public static ConfigSwitchItem getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }
    
    private static final Parser<ConfigSwitchItem> PARSER = (Parser<ConfigSwitchItem>)new AbstractParser<ConfigSwitchItem>() {
        public ConfigSwitchItemOuterClass.ConfigSwitchItem parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
          ConfigSwitchItemOuterClass.ConfigSwitchItem.Builder builder = ConfigSwitchItemOuterClass.ConfigSwitchItem.newBuilder();
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
    
    public static Parser<ConfigSwitchItem> parser() {
      return PARSER;
    }
    
    public Parser<ConfigSwitchItem> getParserForType() {
      return PARSER;
    }
    
    public ConfigSwitchItem getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }
  }
  
  public static Descriptors.FileDescriptor getDescriptor() {
    return descriptor;
  }
  
  static {
    String[] descriptorData = { "\n\026ConfigSwitchItem.proto\032\026ConfigSwitchType.proto\"N\n\020ConfigSwitchItem\022+\n\020configSwitchType\030\001 \001(\0162\021.ConfigSwitchType\022\r\n\005value\030\002 \001(\bB<\n:tech.ordinaryroad.live.chat.client.codec.kuaishou.protobufb\006proto3" };
    descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[] { ConfigSwitchTypeOuterClass.getDescriptor() });
    internal_static_ConfigSwitchItem_descriptor = getDescriptor().getMessageTypes().get(0);
    internal_static_ConfigSwitchItem_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_ConfigSwitchItem_descriptor, new String[] { "ConfigSwitchType", "Value" });
    ConfigSwitchTypeOuterClass.getDescriptor();
  }
  
  public static interface ConfigSwitchItemOrBuilder extends MessageOrBuilder {
    int getConfigSwitchTypeValue();
    
    ConfigSwitchTypeOuterClass.ConfigSwitchType getConfigSwitchType();
    
    boolean getValue();
  }
}
