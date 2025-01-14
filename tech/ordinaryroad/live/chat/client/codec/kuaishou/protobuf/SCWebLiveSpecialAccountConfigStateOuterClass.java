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

public final class SCWebLiveSpecialAccountConfigStateOuterClass {
  private static final Descriptors.Descriptor internal_static_SCWebLiveSpecialAccountConfigState_descriptor;
  
  private static final GeneratedMessageV3.FieldAccessorTable internal_static_SCWebLiveSpecialAccountConfigState_fieldAccessorTable;
  
  private static Descriptors.FileDescriptor descriptor;
  
  public static void registerAllExtensions(ExtensionRegistryLite registry) {}
  
  public static void registerAllExtensions(ExtensionRegistry registry) {
    registerAllExtensions((ExtensionRegistryLite)registry);
  }
  
  public static final class SCWebLiveSpecialAccountConfigState extends GeneratedMessageV3 implements SCWebLiveSpecialAccountConfigStateOrBuilder {
    private static final long serialVersionUID = 0L;
    
    public static final int CONFIGSWITCHITEM_FIELD_NUMBER = 1;
    
    private List<ConfigSwitchItemOuterClass.ConfigSwitchItem> configSwitchItem_;
    
    public static final int TIMESTAMP_FIELD_NUMBER = 2;
    
    private long timestamp_;
    
    private byte memoizedIsInitialized;
    
    private SCWebLiveSpecialAccountConfigState(GeneratedMessageV3.Builder<?> builder) {
      super(builder);
      this.timestamp_ = 0L;
      this.memoizedIsInitialized = -1;
    }
    
    private SCWebLiveSpecialAccountConfigState() {
      this.timestamp_ = 0L;
      this.memoizedIsInitialized = -1;
      this.configSwitchItem_ = Collections.emptyList();
    }
    
    protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
      return new SCWebLiveSpecialAccountConfigState();
    }
    
    public static final Descriptors.Descriptor getDescriptor() {
      return SCWebLiveSpecialAccountConfigStateOuterClass.internal_static_SCWebLiveSpecialAccountConfigState_descriptor;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return SCWebLiveSpecialAccountConfigStateOuterClass.internal_static_SCWebLiveSpecialAccountConfigState_fieldAccessorTable.ensureFieldAccessorsInitialized(SCWebLiveSpecialAccountConfigState.class, Builder.class);
    }
    
    public List<ConfigSwitchItemOuterClass.ConfigSwitchItem> getConfigSwitchItemList() {
      return this.configSwitchItem_;
    }
    
    public List<? extends ConfigSwitchItemOuterClass.ConfigSwitchItemOrBuilder> getConfigSwitchItemOrBuilderList() {
      return (List)this.configSwitchItem_;
    }
    
    public int getConfigSwitchItemCount() {
      return this.configSwitchItem_.size();
    }
    
    public ConfigSwitchItemOuterClass.ConfigSwitchItem getConfigSwitchItem(int index) {
      return this.configSwitchItem_.get(index);
    }
    
    public ConfigSwitchItemOuterClass.ConfigSwitchItemOrBuilder getConfigSwitchItemOrBuilder(int index) {
      return this.configSwitchItem_.get(index);
    }
    
    public long getTimestamp() {
      return this.timestamp_;
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
      for (int i = 0; i < this.configSwitchItem_.size(); i++)
        output.writeMessage(1, (MessageLite)this.configSwitchItem_.get(i)); 
      if (this.timestamp_ != 0L)
        output.writeUInt64(2, this.timestamp_); 
      getUnknownFields().writeTo(output);
    }
    
    public int getSerializedSize() {
      int size = this.memoizedSize;
      if (size != -1)
        return size; 
      size = 0;
      for (int i = 0; i < this.configSwitchItem_.size(); i++)
        size += 
          CodedOutputStream.computeMessageSize(1, (MessageLite)this.configSwitchItem_.get(i)); 
      if (this.timestamp_ != 0L)
        size += 
          CodedOutputStream.computeUInt64Size(2, this.timestamp_); 
      size += getUnknownFields().getSerializedSize();
      this.memoizedSize = size;
      return size;
    }
    
    public boolean equals(Object obj) {
      if (obj == this)
        return true; 
      if (!(obj instanceof SCWebLiveSpecialAccountConfigState))
        return super.equals(obj); 
      SCWebLiveSpecialAccountConfigState other = (SCWebLiveSpecialAccountConfigState)obj;
      if (!getConfigSwitchItemList().equals(other.getConfigSwitchItemList()))
        return false; 
      if (getTimestamp() != other
        .getTimestamp())
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
      if (getConfigSwitchItemCount() > 0) {
        hash = 37 * hash + 1;
        hash = 53 * hash + getConfigSwitchItemList().hashCode();
      } 
      hash = 37 * hash + 2;
      hash = 53 * hash + Internal.hashLong(
          getTimestamp());
      hash = 29 * hash + getUnknownFields().hashCode();
      this.memoizedHashCode = hash;
      return hash;
    }
    
    public static SCWebLiveSpecialAccountConfigState parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (SCWebLiveSpecialAccountConfigState)PARSER.parseFrom(data);
    }
    
    public static SCWebLiveSpecialAccountConfigState parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (SCWebLiveSpecialAccountConfigState)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static SCWebLiveSpecialAccountConfigState parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (SCWebLiveSpecialAccountConfigState)PARSER.parseFrom(data);
    }
    
    public static SCWebLiveSpecialAccountConfigState parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (SCWebLiveSpecialAccountConfigState)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static SCWebLiveSpecialAccountConfigState parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (SCWebLiveSpecialAccountConfigState)PARSER.parseFrom(data);
    }
    
    public static SCWebLiveSpecialAccountConfigState parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (SCWebLiveSpecialAccountConfigState)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static SCWebLiveSpecialAccountConfigState parseFrom(InputStream input) throws IOException {
      return 
        (SCWebLiveSpecialAccountConfigState)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static SCWebLiveSpecialAccountConfigState parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (SCWebLiveSpecialAccountConfigState)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static SCWebLiveSpecialAccountConfigState parseDelimitedFrom(InputStream input) throws IOException {
      return 
        (SCWebLiveSpecialAccountConfigState)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }
    
    public static SCWebLiveSpecialAccountConfigState parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (SCWebLiveSpecialAccountConfigState)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static SCWebLiveSpecialAccountConfigState parseFrom(CodedInputStream input) throws IOException {
      return 
        (SCWebLiveSpecialAccountConfigState)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static SCWebLiveSpecialAccountConfigState parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (SCWebLiveSpecialAccountConfigState)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public Builder newBuilderForType() {
      return newBuilder();
    }
    
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    
    public static Builder newBuilder(SCWebLiveSpecialAccountConfigState prototype) {
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
    
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements SCWebLiveSpecialAccountConfigStateOuterClass.SCWebLiveSpecialAccountConfigStateOrBuilder {
      private int bitField0_;
      
      private List<ConfigSwitchItemOuterClass.ConfigSwitchItem> configSwitchItem_;
      
      private RepeatedFieldBuilderV3<ConfigSwitchItemOuterClass.ConfigSwitchItem, ConfigSwitchItemOuterClass.ConfigSwitchItem.Builder, ConfigSwitchItemOuterClass.ConfigSwitchItemOrBuilder> configSwitchItemBuilder_;
      
      private long timestamp_;
      
      public static final Descriptors.Descriptor getDescriptor() {
        return SCWebLiveSpecialAccountConfigStateOuterClass.internal_static_SCWebLiveSpecialAccountConfigState_descriptor;
      }
      
      protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return SCWebLiveSpecialAccountConfigStateOuterClass.internal_static_SCWebLiveSpecialAccountConfigState_fieldAccessorTable
          .ensureFieldAccessorsInitialized(SCWebLiveSpecialAccountConfigStateOuterClass.SCWebLiveSpecialAccountConfigState.class, Builder.class);
      }
      
      private Builder() {
        this
          .configSwitchItem_ = Collections.emptyList();
      }
      
      private Builder(GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        this.configSwitchItem_ = Collections.emptyList();
      }
      
      public Builder clear() {
        super.clear();
        this.bitField0_ = 0;
        if (this.configSwitchItemBuilder_ == null) {
          this.configSwitchItem_ = Collections.emptyList();
        } else {
          this.configSwitchItem_ = null;
          this.configSwitchItemBuilder_.clear();
        } 
        this.bitField0_ &= 0xFFFFFFFE;
        this.timestamp_ = 0L;
        return this;
      }
      
      public Descriptors.Descriptor getDescriptorForType() {
        return SCWebLiveSpecialAccountConfigStateOuterClass.internal_static_SCWebLiveSpecialAccountConfigState_descriptor;
      }
      
      public SCWebLiveSpecialAccountConfigStateOuterClass.SCWebLiveSpecialAccountConfigState getDefaultInstanceForType() {
        return SCWebLiveSpecialAccountConfigStateOuterClass.SCWebLiveSpecialAccountConfigState.getDefaultInstance();
      }
      
      public SCWebLiveSpecialAccountConfigStateOuterClass.SCWebLiveSpecialAccountConfigState build() {
        SCWebLiveSpecialAccountConfigStateOuterClass.SCWebLiveSpecialAccountConfigState result = buildPartial();
        if (!result.isInitialized())
          throw newUninitializedMessageException(result); 
        return result;
      }
      
      public SCWebLiveSpecialAccountConfigStateOuterClass.SCWebLiveSpecialAccountConfigState buildPartial() {
        SCWebLiveSpecialAccountConfigStateOuterClass.SCWebLiveSpecialAccountConfigState result = new SCWebLiveSpecialAccountConfigStateOuterClass.SCWebLiveSpecialAccountConfigState(this);
        buildPartialRepeatedFields(result);
        if (this.bitField0_ != 0)
          buildPartial0(result); 
        onBuilt();
        return result;
      }
      
      private void buildPartialRepeatedFields(SCWebLiveSpecialAccountConfigStateOuterClass.SCWebLiveSpecialAccountConfigState result) {
        if (this.configSwitchItemBuilder_ == null) {
          if ((this.bitField0_ & 0x1) != 0) {
            this.configSwitchItem_ = Collections.unmodifiableList(this.configSwitchItem_);
            this.bitField0_ &= 0xFFFFFFFE;
          } 
          result.configSwitchItem_ = this.configSwitchItem_;
        } else {
          result.configSwitchItem_ = this.configSwitchItemBuilder_.build();
        } 
      }
      
      private void buildPartial0(SCWebLiveSpecialAccountConfigStateOuterClass.SCWebLiveSpecialAccountConfigState result) {
        int from_bitField0_ = this.bitField0_;
        if ((from_bitField0_ & 0x2) != 0)
          result.timestamp_ = this.timestamp_; 
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
        if (other instanceof SCWebLiveSpecialAccountConfigStateOuterClass.SCWebLiveSpecialAccountConfigState)
          return mergeFrom((SCWebLiveSpecialAccountConfigStateOuterClass.SCWebLiveSpecialAccountConfigState)other); 
        super.mergeFrom(other);
        return this;
      }
      
      public Builder mergeFrom(SCWebLiveSpecialAccountConfigStateOuterClass.SCWebLiveSpecialAccountConfigState other) {
        if (other == SCWebLiveSpecialAccountConfigStateOuterClass.SCWebLiveSpecialAccountConfigState.getDefaultInstance())
          return this; 
        if (this.configSwitchItemBuilder_ == null) {
          if (!other.configSwitchItem_.isEmpty()) {
            if (this.configSwitchItem_.isEmpty()) {
              this.configSwitchItem_ = other.configSwitchItem_;
              this.bitField0_ &= 0xFFFFFFFE;
            } else {
              ensureConfigSwitchItemIsMutable();
              this.configSwitchItem_.addAll(other.configSwitchItem_);
            } 
            onChanged();
          } 
        } else if (!other.configSwitchItem_.isEmpty()) {
          if (this.configSwitchItemBuilder_.isEmpty()) {
            this.configSwitchItemBuilder_.dispose();
            this.configSwitchItemBuilder_ = null;
            this.configSwitchItem_ = other.configSwitchItem_;
            this.bitField0_ &= 0xFFFFFFFE;
            this.configSwitchItemBuilder_ = SCWebLiveSpecialAccountConfigStateOuterClass.SCWebLiveSpecialAccountConfigState.alwaysUseFieldBuilders ? getConfigSwitchItemFieldBuilder() : null;
          } else {
            this.configSwitchItemBuilder_.addAllMessages(other.configSwitchItem_);
          } 
        } 
        if (other.getTimestamp() != 0L)
          setTimestamp(other.getTimestamp()); 
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
            ConfigSwitchItemOuterClass.ConfigSwitchItem m;
            int tag = input.readTag();
            switch (tag) {
              case 0:
                done = true;
                continue;
              case 10:
                m = (ConfigSwitchItemOuterClass.ConfigSwitchItem)input.readMessage(ConfigSwitchItemOuterClass.ConfigSwitchItem.parser(), extensionRegistry);
                if (this.configSwitchItemBuilder_ == null) {
                  ensureConfigSwitchItemIsMutable();
                  this.configSwitchItem_.add(m);
                  continue;
                } 
                this.configSwitchItemBuilder_.addMessage((AbstractMessage)m);
                continue;
              case 16:
                this.timestamp_ = input.readUInt64();
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
      
      private void ensureConfigSwitchItemIsMutable() {
        if ((this.bitField0_ & 0x1) == 0) {
          this.configSwitchItem_ = new ArrayList<>(this.configSwitchItem_);
          this.bitField0_ |= 0x1;
        } 
      }
      
      public List<ConfigSwitchItemOuterClass.ConfigSwitchItem> getConfigSwitchItemList() {
        if (this.configSwitchItemBuilder_ == null)
          return Collections.unmodifiableList(this.configSwitchItem_); 
        return this.configSwitchItemBuilder_.getMessageList();
      }
      
      public int getConfigSwitchItemCount() {
        if (this.configSwitchItemBuilder_ == null)
          return this.configSwitchItem_.size(); 
        return this.configSwitchItemBuilder_.getCount();
      }
      
      public ConfigSwitchItemOuterClass.ConfigSwitchItem getConfigSwitchItem(int index) {
        if (this.configSwitchItemBuilder_ == null)
          return this.configSwitchItem_.get(index); 
        return (ConfigSwitchItemOuterClass.ConfigSwitchItem)this.configSwitchItemBuilder_.getMessage(index);
      }
      
      public Builder setConfigSwitchItem(int index, ConfigSwitchItemOuterClass.ConfigSwitchItem value) {
        if (this.configSwitchItemBuilder_ == null) {
          if (value == null)
            throw new NullPointerException(); 
          ensureConfigSwitchItemIsMutable();
          this.configSwitchItem_.set(index, value);
          onChanged();
        } else {
          this.configSwitchItemBuilder_.setMessage(index, (AbstractMessage)value);
        } 
        return this;
      }
      
      public Builder setConfigSwitchItem(int index, ConfigSwitchItemOuterClass.ConfigSwitchItem.Builder builderForValue) {
        if (this.configSwitchItemBuilder_ == null) {
          ensureConfigSwitchItemIsMutable();
          this.configSwitchItem_.set(index, builderForValue.build());
          onChanged();
        } else {
          this.configSwitchItemBuilder_.setMessage(index, (AbstractMessage)builderForValue.build());
        } 
        return this;
      }
      
      public Builder addConfigSwitchItem(ConfigSwitchItemOuterClass.ConfigSwitchItem value) {
        if (this.configSwitchItemBuilder_ == null) {
          if (value == null)
            throw new NullPointerException(); 
          ensureConfigSwitchItemIsMutable();
          this.configSwitchItem_.add(value);
          onChanged();
        } else {
          this.configSwitchItemBuilder_.addMessage((AbstractMessage)value);
        } 
        return this;
      }
      
      public Builder addConfigSwitchItem(int index, ConfigSwitchItemOuterClass.ConfigSwitchItem value) {
        if (this.configSwitchItemBuilder_ == null) {
          if (value == null)
            throw new NullPointerException(); 
          ensureConfigSwitchItemIsMutable();
          this.configSwitchItem_.add(index, value);
          onChanged();
        } else {
          this.configSwitchItemBuilder_.addMessage(index, (AbstractMessage)value);
        } 
        return this;
      }
      
      public Builder addConfigSwitchItem(ConfigSwitchItemOuterClass.ConfigSwitchItem.Builder builderForValue) {
        if (this.configSwitchItemBuilder_ == null) {
          ensureConfigSwitchItemIsMutable();
          this.configSwitchItem_.add(builderForValue.build());
          onChanged();
        } else {
          this.configSwitchItemBuilder_.addMessage((AbstractMessage)builderForValue.build());
        } 
        return this;
      }
      
      public Builder addConfigSwitchItem(int index, ConfigSwitchItemOuterClass.ConfigSwitchItem.Builder builderForValue) {
        if (this.configSwitchItemBuilder_ == null) {
          ensureConfigSwitchItemIsMutable();
          this.configSwitchItem_.add(index, builderForValue.build());
          onChanged();
        } else {
          this.configSwitchItemBuilder_.addMessage(index, (AbstractMessage)builderForValue.build());
        } 
        return this;
      }
      
      public Builder addAllConfigSwitchItem(Iterable<? extends ConfigSwitchItemOuterClass.ConfigSwitchItem> values) {
        if (this.configSwitchItemBuilder_ == null) {
          ensureConfigSwitchItemIsMutable();
          AbstractMessageLite.Builder.addAll(values, this.configSwitchItem_);
          onChanged();
        } else {
          this.configSwitchItemBuilder_.addAllMessages(values);
        } 
        return this;
      }
      
      public Builder clearConfigSwitchItem() {
        if (this.configSwitchItemBuilder_ == null) {
          this.configSwitchItem_ = Collections.emptyList();
          this.bitField0_ &= 0xFFFFFFFE;
          onChanged();
        } else {
          this.configSwitchItemBuilder_.clear();
        } 
        return this;
      }
      
      public Builder removeConfigSwitchItem(int index) {
        if (this.configSwitchItemBuilder_ == null) {
          ensureConfigSwitchItemIsMutable();
          this.configSwitchItem_.remove(index);
          onChanged();
        } else {
          this.configSwitchItemBuilder_.remove(index);
        } 
        return this;
      }
      
      public ConfigSwitchItemOuterClass.ConfigSwitchItem.Builder getConfigSwitchItemBuilder(int index) {
        return (ConfigSwitchItemOuterClass.ConfigSwitchItem.Builder)getConfigSwitchItemFieldBuilder().getBuilder(index);
      }
      
      public ConfigSwitchItemOuterClass.ConfigSwitchItemOrBuilder getConfigSwitchItemOrBuilder(int index) {
        if (this.configSwitchItemBuilder_ == null)
          return this.configSwitchItem_.get(index); 
        return (ConfigSwitchItemOuterClass.ConfigSwitchItemOrBuilder)this.configSwitchItemBuilder_.getMessageOrBuilder(index);
      }
      
      public List<? extends ConfigSwitchItemOuterClass.ConfigSwitchItemOrBuilder> getConfigSwitchItemOrBuilderList() {
        if (this.configSwitchItemBuilder_ != null)
          return this.configSwitchItemBuilder_.getMessageOrBuilderList(); 
        return Collections.unmodifiableList((List)this.configSwitchItem_);
      }
      
      public ConfigSwitchItemOuterClass.ConfigSwitchItem.Builder addConfigSwitchItemBuilder() {
        return (ConfigSwitchItemOuterClass.ConfigSwitchItem.Builder)getConfigSwitchItemFieldBuilder().addBuilder(
            (AbstractMessage)ConfigSwitchItemOuterClass.ConfigSwitchItem.getDefaultInstance());
      }
      
      public ConfigSwitchItemOuterClass.ConfigSwitchItem.Builder addConfigSwitchItemBuilder(int index) {
        return (ConfigSwitchItemOuterClass.ConfigSwitchItem.Builder)getConfigSwitchItemFieldBuilder().addBuilder(index, 
            (AbstractMessage)ConfigSwitchItemOuterClass.ConfigSwitchItem.getDefaultInstance());
      }
      
      public List<ConfigSwitchItemOuterClass.ConfigSwitchItem.Builder> getConfigSwitchItemBuilderList() {
        return getConfigSwitchItemFieldBuilder().getBuilderList();
      }
      
      private RepeatedFieldBuilderV3<ConfigSwitchItemOuterClass.ConfigSwitchItem, ConfigSwitchItemOuterClass.ConfigSwitchItem.Builder, ConfigSwitchItemOuterClass.ConfigSwitchItemOrBuilder> getConfigSwitchItemFieldBuilder() {
        if (this.configSwitchItemBuilder_ == null) {
          this
            
            .configSwitchItemBuilder_ = new RepeatedFieldBuilderV3(this.configSwitchItem_, ((this.bitField0_ & 0x1) != 0), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
          this.configSwitchItem_ = null;
        } 
        return this.configSwitchItemBuilder_;
      }
      
      public long getTimestamp() {
        return this.timestamp_;
      }
      
      public Builder setTimestamp(long value) {
        this.timestamp_ = value;
        this.bitField0_ |= 0x2;
        onChanged();
        return this;
      }
      
      public Builder clearTimestamp() {
        this.bitField0_ &= 0xFFFFFFFD;
        this.timestamp_ = 0L;
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
    
    private static final SCWebLiveSpecialAccountConfigState DEFAULT_INSTANCE = new SCWebLiveSpecialAccountConfigState();
    
    public static SCWebLiveSpecialAccountConfigState getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }
    
    private static final Parser<SCWebLiveSpecialAccountConfigState> PARSER = (Parser<SCWebLiveSpecialAccountConfigState>)new AbstractParser<SCWebLiveSpecialAccountConfigState>() {
        public SCWebLiveSpecialAccountConfigStateOuterClass.SCWebLiveSpecialAccountConfigState parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
          SCWebLiveSpecialAccountConfigStateOuterClass.SCWebLiveSpecialAccountConfigState.Builder builder = SCWebLiveSpecialAccountConfigStateOuterClass.SCWebLiveSpecialAccountConfigState.newBuilder();
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
    
    public static Parser<SCWebLiveSpecialAccountConfigState> parser() {
      return PARSER;
    }
    
    public Parser<SCWebLiveSpecialAccountConfigState> getParserForType() {
      return PARSER;
    }
    
    public SCWebLiveSpecialAccountConfigState getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }
  }
  
  public static Descriptors.FileDescriptor getDescriptor() {
    return descriptor;
  }
  
  static {
    String[] descriptorData = { "\n(SCWebLiveSpecialAccountConfigState.proto\032\026ConfigSwitchItem.proto\"d\n\"SCWebLiveSpecialAccountConfigState\022+\n\020configSwitchItem\030\001 \003(\0132\021.ConfigSwitchItem\022\021\n\ttimestamp\030\002 \001(\004B<\n:tech.ordinaryroad.live.chat.client.codec.kuaishou.protobufb\006proto3" };
    descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[] { ConfigSwitchItemOuterClass.getDescriptor() });
    internal_static_SCWebLiveSpecialAccountConfigState_descriptor = getDescriptor().getMessageTypes().get(0);
    internal_static_SCWebLiveSpecialAccountConfigState_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_SCWebLiveSpecialAccountConfigState_descriptor, new String[] { "ConfigSwitchItem", "Timestamp" });
    ConfigSwitchItemOuterClass.getDescriptor();
  }
  
  public static interface SCWebLiveSpecialAccountConfigStateOrBuilder extends MessageOrBuilder {
    List<ConfigSwitchItemOuterClass.ConfigSwitchItem> getConfigSwitchItemList();
    
    ConfigSwitchItemOuterClass.ConfigSwitchItem getConfigSwitchItem(int param1Int);
    
    int getConfigSwitchItemCount();
    
    List<? extends ConfigSwitchItemOuterClass.ConfigSwitchItemOrBuilder> getConfigSwitchItemOrBuilderList();
    
    ConfigSwitchItemOuterClass.ConfigSwitchItemOrBuilder getConfigSwitchItemOrBuilder(int param1Int);
    
    long getTimestamp();
  }
}
