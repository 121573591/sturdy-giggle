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
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class SCLiveWarningMaskStatusChangedAudienceOuterClass {
  private static final Descriptors.Descriptor internal_static_SCLiveWarningMaskStatusChangedAudience_descriptor;
  
  private static final GeneratedMessageV3.FieldAccessorTable internal_static_SCLiveWarningMaskStatusChangedAudience_fieldAccessorTable;
  
  private static Descriptors.FileDescriptor descriptor;
  
  public static void registerAllExtensions(ExtensionRegistryLite registry) {}
  
  public static void registerAllExtensions(ExtensionRegistry registry) {
    registerAllExtensions((ExtensionRegistryLite)registry);
  }
  
  public static final class SCLiveWarningMaskStatusChangedAudience extends GeneratedMessageV3 implements SCLiveWarningMaskStatusChangedAudienceOrBuilder {
    private static final long serialVersionUID = 0L;
    
    private int bitField0_;
    
    public static final int DISPLAYMASK_FIELD_NUMBER = 1;
    
    private boolean displayMask_;
    
    public static final int WARNINGMASK_FIELD_NUMBER = 2;
    
    private AuditAudienceMaskOuterClass.AuditAudienceMask warningMask_;
    
    private byte memoizedIsInitialized;
    
    private SCLiveWarningMaskStatusChangedAudience(GeneratedMessageV3.Builder<?> builder) {
      super(builder);
      this.displayMask_ = false;
      this.memoizedIsInitialized = -1;
    }
    
    private SCLiveWarningMaskStatusChangedAudience() {
      this.displayMask_ = false;
      this.memoizedIsInitialized = -1;
    }
    
    protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
      return new SCLiveWarningMaskStatusChangedAudience();
    }
    
    public static final Descriptors.Descriptor getDescriptor() {
      return SCLiveWarningMaskStatusChangedAudienceOuterClass.internal_static_SCLiveWarningMaskStatusChangedAudience_descriptor;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return SCLiveWarningMaskStatusChangedAudienceOuterClass.internal_static_SCLiveWarningMaskStatusChangedAudience_fieldAccessorTable.ensureFieldAccessorsInitialized(SCLiveWarningMaskStatusChangedAudience.class, Builder.class);
    }
    
    public boolean getDisplayMask() {
      return this.displayMask_;
    }
    
    public boolean hasWarningMask() {
      return ((this.bitField0_ & 0x1) != 0);
    }
    
    public AuditAudienceMaskOuterClass.AuditAudienceMask getWarningMask() {
      return (this.warningMask_ == null) ? AuditAudienceMaskOuterClass.AuditAudienceMask.getDefaultInstance() : this.warningMask_;
    }
    
    public AuditAudienceMaskOuterClass.AuditAudienceMaskOrBuilder getWarningMaskOrBuilder() {
      return (this.warningMask_ == null) ? AuditAudienceMaskOuterClass.AuditAudienceMask.getDefaultInstance() : this.warningMask_;
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
      if (this.displayMask_)
        output.writeBool(1, this.displayMask_); 
      if ((this.bitField0_ & 0x1) != 0)
        output.writeMessage(2, (MessageLite)getWarningMask()); 
      getUnknownFields().writeTo(output);
    }
    
    public int getSerializedSize() {
      int size = this.memoizedSize;
      if (size != -1)
        return size; 
      size = 0;
      if (this.displayMask_)
        size += 
          CodedOutputStream.computeBoolSize(1, this.displayMask_); 
      if ((this.bitField0_ & 0x1) != 0)
        size += 
          CodedOutputStream.computeMessageSize(2, (MessageLite)getWarningMask()); 
      size += getUnknownFields().getSerializedSize();
      this.memoizedSize = size;
      return size;
    }
    
    public boolean equals(Object obj) {
      if (obj == this)
        return true; 
      if (!(obj instanceof SCLiveWarningMaskStatusChangedAudience))
        return super.equals(obj); 
      SCLiveWarningMaskStatusChangedAudience other = (SCLiveWarningMaskStatusChangedAudience)obj;
      if (getDisplayMask() != other
        .getDisplayMask())
        return false; 
      if (hasWarningMask() != other.hasWarningMask())
        return false; 
      if (hasWarningMask() && 
        
        !getWarningMask().equals(other.getWarningMask()))
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
      hash = 53 * hash + Internal.hashBoolean(
          getDisplayMask());
      if (hasWarningMask()) {
        hash = 37 * hash + 2;
        hash = 53 * hash + getWarningMask().hashCode();
      } 
      hash = 29 * hash + getUnknownFields().hashCode();
      this.memoizedHashCode = hash;
      return hash;
    }
    
    public static SCLiveWarningMaskStatusChangedAudience parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (SCLiveWarningMaskStatusChangedAudience)PARSER.parseFrom(data);
    }
    
    public static SCLiveWarningMaskStatusChangedAudience parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (SCLiveWarningMaskStatusChangedAudience)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static SCLiveWarningMaskStatusChangedAudience parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (SCLiveWarningMaskStatusChangedAudience)PARSER.parseFrom(data);
    }
    
    public static SCLiveWarningMaskStatusChangedAudience parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (SCLiveWarningMaskStatusChangedAudience)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static SCLiveWarningMaskStatusChangedAudience parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (SCLiveWarningMaskStatusChangedAudience)PARSER.parseFrom(data);
    }
    
    public static SCLiveWarningMaskStatusChangedAudience parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (SCLiveWarningMaskStatusChangedAudience)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static SCLiveWarningMaskStatusChangedAudience parseFrom(InputStream input) throws IOException {
      return 
        (SCLiveWarningMaskStatusChangedAudience)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static SCLiveWarningMaskStatusChangedAudience parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (SCLiveWarningMaskStatusChangedAudience)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static SCLiveWarningMaskStatusChangedAudience parseDelimitedFrom(InputStream input) throws IOException {
      return 
        (SCLiveWarningMaskStatusChangedAudience)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }
    
    public static SCLiveWarningMaskStatusChangedAudience parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (SCLiveWarningMaskStatusChangedAudience)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static SCLiveWarningMaskStatusChangedAudience parseFrom(CodedInputStream input) throws IOException {
      return 
        (SCLiveWarningMaskStatusChangedAudience)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static SCLiveWarningMaskStatusChangedAudience parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (SCLiveWarningMaskStatusChangedAudience)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public Builder newBuilderForType() {
      return newBuilder();
    }
    
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    
    public static Builder newBuilder(SCLiveWarningMaskStatusChangedAudience prototype) {
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
    
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements SCLiveWarningMaskStatusChangedAudienceOuterClass.SCLiveWarningMaskStatusChangedAudienceOrBuilder {
      private int bitField0_;
      
      private boolean displayMask_;
      
      private AuditAudienceMaskOuterClass.AuditAudienceMask warningMask_;
      
      private SingleFieldBuilderV3<AuditAudienceMaskOuterClass.AuditAudienceMask, AuditAudienceMaskOuterClass.AuditAudienceMask.Builder, AuditAudienceMaskOuterClass.AuditAudienceMaskOrBuilder> warningMaskBuilder_;
      
      public static final Descriptors.Descriptor getDescriptor() {
        return SCLiveWarningMaskStatusChangedAudienceOuterClass.internal_static_SCLiveWarningMaskStatusChangedAudience_descriptor;
      }
      
      protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return SCLiveWarningMaskStatusChangedAudienceOuterClass.internal_static_SCLiveWarningMaskStatusChangedAudience_fieldAccessorTable
          .ensureFieldAccessorsInitialized(SCLiveWarningMaskStatusChangedAudienceOuterClass.SCLiveWarningMaskStatusChangedAudience.class, Builder.class);
      }
      
      private Builder() {
        maybeForceBuilderInitialization();
      }
      
      private Builder(GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      
      private void maybeForceBuilderInitialization() {
        if (SCLiveWarningMaskStatusChangedAudienceOuterClass.SCLiveWarningMaskStatusChangedAudience.alwaysUseFieldBuilders)
          getWarningMaskFieldBuilder(); 
      }
      
      public Builder clear() {
        super.clear();
        this.bitField0_ = 0;
        this.displayMask_ = false;
        this.warningMask_ = null;
        if (this.warningMaskBuilder_ != null) {
          this.warningMaskBuilder_.dispose();
          this.warningMaskBuilder_ = null;
        } 
        return this;
      }
      
      public Descriptors.Descriptor getDescriptorForType() {
        return SCLiveWarningMaskStatusChangedAudienceOuterClass.internal_static_SCLiveWarningMaskStatusChangedAudience_descriptor;
      }
      
      public SCLiveWarningMaskStatusChangedAudienceOuterClass.SCLiveWarningMaskStatusChangedAudience getDefaultInstanceForType() {
        return SCLiveWarningMaskStatusChangedAudienceOuterClass.SCLiveWarningMaskStatusChangedAudience.getDefaultInstance();
      }
      
      public SCLiveWarningMaskStatusChangedAudienceOuterClass.SCLiveWarningMaskStatusChangedAudience build() {
        SCLiveWarningMaskStatusChangedAudienceOuterClass.SCLiveWarningMaskStatusChangedAudience result = buildPartial();
        if (!result.isInitialized())
          throw newUninitializedMessageException(result); 
        return result;
      }
      
      public SCLiveWarningMaskStatusChangedAudienceOuterClass.SCLiveWarningMaskStatusChangedAudience buildPartial() {
        SCLiveWarningMaskStatusChangedAudienceOuterClass.SCLiveWarningMaskStatusChangedAudience result = new SCLiveWarningMaskStatusChangedAudienceOuterClass.SCLiveWarningMaskStatusChangedAudience(this);
        if (this.bitField0_ != 0)
          buildPartial0(result); 
        onBuilt();
        return result;
      }
      
      private void buildPartial0(SCLiveWarningMaskStatusChangedAudienceOuterClass.SCLiveWarningMaskStatusChangedAudience result) {
        int from_bitField0_ = this.bitField0_;
        if ((from_bitField0_ & 0x1) != 0)
          result.displayMask_ = this.displayMask_; 
        int to_bitField0_ = 0;
        if ((from_bitField0_ & 0x2) != 0) {
          result.warningMask_ = (this.warningMaskBuilder_ == null) ? 
            this.warningMask_ : 
            (AuditAudienceMaskOuterClass.AuditAudienceMask)this.warningMaskBuilder_.build();
          to_bitField0_ |= 0x1;
        } 
        result.bitField0_ |= to_bitField0_;
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
        if (other instanceof SCLiveWarningMaskStatusChangedAudienceOuterClass.SCLiveWarningMaskStatusChangedAudience)
          return mergeFrom((SCLiveWarningMaskStatusChangedAudienceOuterClass.SCLiveWarningMaskStatusChangedAudience)other); 
        super.mergeFrom(other);
        return this;
      }
      
      public Builder mergeFrom(SCLiveWarningMaskStatusChangedAudienceOuterClass.SCLiveWarningMaskStatusChangedAudience other) {
        if (other == SCLiveWarningMaskStatusChangedAudienceOuterClass.SCLiveWarningMaskStatusChangedAudience.getDefaultInstance())
          return this; 
        if (other.getDisplayMask())
          setDisplayMask(other.getDisplayMask()); 
        if (other.hasWarningMask())
          mergeWarningMask(other.getWarningMask()); 
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
                this.displayMask_ = input.readBool();
                this.bitField0_ |= 0x1;
                continue;
              case 18:
                input.readMessage((MessageLite.Builder)
                    getWarningMaskFieldBuilder().getBuilder(), extensionRegistry);
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
      
      public boolean getDisplayMask() {
        return this.displayMask_;
      }
      
      public Builder setDisplayMask(boolean value) {
        this.displayMask_ = value;
        this.bitField0_ |= 0x1;
        onChanged();
        return this;
      }
      
      public Builder clearDisplayMask() {
        this.bitField0_ &= 0xFFFFFFFE;
        this.displayMask_ = false;
        onChanged();
        return this;
      }
      
      public boolean hasWarningMask() {
        return ((this.bitField0_ & 0x2) != 0);
      }
      
      public AuditAudienceMaskOuterClass.AuditAudienceMask getWarningMask() {
        if (this.warningMaskBuilder_ == null)
          return (this.warningMask_ == null) ? AuditAudienceMaskOuterClass.AuditAudienceMask.getDefaultInstance() : this.warningMask_; 
        return (AuditAudienceMaskOuterClass.AuditAudienceMask)this.warningMaskBuilder_.getMessage();
      }
      
      public Builder setWarningMask(AuditAudienceMaskOuterClass.AuditAudienceMask value) {
        if (this.warningMaskBuilder_ == null) {
          if (value == null)
            throw new NullPointerException(); 
          this.warningMask_ = value;
        } else {
          this.warningMaskBuilder_.setMessage((AbstractMessage)value);
        } 
        this.bitField0_ |= 0x2;
        onChanged();
        return this;
      }
      
      public Builder setWarningMask(AuditAudienceMaskOuterClass.AuditAudienceMask.Builder builderForValue) {
        if (this.warningMaskBuilder_ == null) {
          this.warningMask_ = builderForValue.build();
        } else {
          this.warningMaskBuilder_.setMessage((AbstractMessage)builderForValue.build());
        } 
        this.bitField0_ |= 0x2;
        onChanged();
        return this;
      }
      
      public Builder mergeWarningMask(AuditAudienceMaskOuterClass.AuditAudienceMask value) {
        if (this.warningMaskBuilder_ == null) {
          if ((this.bitField0_ & 0x2) != 0 && this.warningMask_ != null && this.warningMask_ != 
            
            AuditAudienceMaskOuterClass.AuditAudienceMask.getDefaultInstance()) {
            getWarningMaskBuilder().mergeFrom(value);
          } else {
            this.warningMask_ = value;
          } 
        } else {
          this.warningMaskBuilder_.mergeFrom((AbstractMessage)value);
        } 
        if (this.warningMask_ != null) {
          this.bitField0_ |= 0x2;
          onChanged();
        } 
        return this;
      }
      
      public Builder clearWarningMask() {
        this.bitField0_ &= 0xFFFFFFFD;
        this.warningMask_ = null;
        if (this.warningMaskBuilder_ != null) {
          this.warningMaskBuilder_.dispose();
          this.warningMaskBuilder_ = null;
        } 
        onChanged();
        return this;
      }
      
      public AuditAudienceMaskOuterClass.AuditAudienceMask.Builder getWarningMaskBuilder() {
        this.bitField0_ |= 0x2;
        onChanged();
        return (AuditAudienceMaskOuterClass.AuditAudienceMask.Builder)getWarningMaskFieldBuilder().getBuilder();
      }
      
      public AuditAudienceMaskOuterClass.AuditAudienceMaskOrBuilder getWarningMaskOrBuilder() {
        if (this.warningMaskBuilder_ != null)
          return (AuditAudienceMaskOuterClass.AuditAudienceMaskOrBuilder)this.warningMaskBuilder_.getMessageOrBuilder(); 
        return (this.warningMask_ == null) ? 
          AuditAudienceMaskOuterClass.AuditAudienceMask.getDefaultInstance() : this.warningMask_;
      }
      
      private SingleFieldBuilderV3<AuditAudienceMaskOuterClass.AuditAudienceMask, AuditAudienceMaskOuterClass.AuditAudienceMask.Builder, AuditAudienceMaskOuterClass.AuditAudienceMaskOrBuilder> getWarningMaskFieldBuilder() {
        if (this.warningMaskBuilder_ == null) {
          this
            
            .warningMaskBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getWarningMask(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
          this.warningMask_ = null;
        } 
        return this.warningMaskBuilder_;
      }
      
      public final Builder setUnknownFields(UnknownFieldSet unknownFields) {
        return (Builder)super.setUnknownFields(unknownFields);
      }
      
      public final Builder mergeUnknownFields(UnknownFieldSet unknownFields) {
        return (Builder)super.mergeUnknownFields(unknownFields);
      }
    }
    
    private static final SCLiveWarningMaskStatusChangedAudience DEFAULT_INSTANCE = new SCLiveWarningMaskStatusChangedAudience();
    
    public static SCLiveWarningMaskStatusChangedAudience getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }
    
    private static final Parser<SCLiveWarningMaskStatusChangedAudience> PARSER = (Parser<SCLiveWarningMaskStatusChangedAudience>)new AbstractParser<SCLiveWarningMaskStatusChangedAudience>() {
        public SCLiveWarningMaskStatusChangedAudienceOuterClass.SCLiveWarningMaskStatusChangedAudience parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
          SCLiveWarningMaskStatusChangedAudienceOuterClass.SCLiveWarningMaskStatusChangedAudience.Builder builder = SCLiveWarningMaskStatusChangedAudienceOuterClass.SCLiveWarningMaskStatusChangedAudience.newBuilder();
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
    
    public static Parser<SCLiveWarningMaskStatusChangedAudience> parser() {
      return PARSER;
    }
    
    public Parser<SCLiveWarningMaskStatusChangedAudience> getParserForType() {
      return PARSER;
    }
    
    public SCLiveWarningMaskStatusChangedAudience getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }
  }
  
  public static Descriptors.FileDescriptor getDescriptor() {
    return descriptor;
  }
  
  static {
    String[] descriptorData = { "\n,SCLiveWarningMaskStatusChangedAudience.proto\032\027AuditAudienceMask.proto\"f\n&SCLiveWarningMaskStatusChangedAudience\022\023\n\013displayMask\030\001 \001(\b\022'\n\013warningMask\030\002 \001(\0132\022.AuditAudienceMaskB<\n:tech.ordinaryroad.live.chat.client.codec.kuaishou.protobufb\006proto3" };
    descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[] { AuditAudienceMaskOuterClass.getDescriptor() });
    internal_static_SCLiveWarningMaskStatusChangedAudience_descriptor = getDescriptor().getMessageTypes().get(0);
    internal_static_SCLiveWarningMaskStatusChangedAudience_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_SCLiveWarningMaskStatusChangedAudience_descriptor, new String[] { "DisplayMask", "WarningMask" });
    AuditAudienceMaskOuterClass.getDescriptor();
  }
  
  public static interface SCLiveWarningMaskStatusChangedAudienceOrBuilder extends MessageOrBuilder {
    boolean getDisplayMask();
    
    boolean hasWarningMask();
    
    AuditAudienceMaskOuterClass.AuditAudienceMask getWarningMask();
    
    AuditAudienceMaskOuterClass.AuditAudienceMaskOrBuilder getWarningMaskOrBuilder();
  }
}
