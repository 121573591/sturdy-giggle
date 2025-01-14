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
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class SCWebCurrentRedPackFeedOuterClass {
  private static final Descriptors.Descriptor internal_static_SCWebCurrentRedPackFeed_descriptor;
  
  private static final GeneratedMessageV3.FieldAccessorTable internal_static_SCWebCurrentRedPackFeed_fieldAccessorTable;
  
  private static Descriptors.FileDescriptor descriptor;
  
  public static void registerAllExtensions(ExtensionRegistryLite registry) {}
  
  public static void registerAllExtensions(ExtensionRegistry registry) {
    registerAllExtensions((ExtensionRegistryLite)registry);
  }
  
  public static final class SCWebCurrentRedPackFeed extends GeneratedMessageV3 implements SCWebCurrentRedPackFeedOrBuilder {
    private static final long serialVersionUID = 0L;
    
    public static final int REDPACK_FIELD_NUMBER = 1;
    
    private List<WebRedPackInfoOuterClass.WebRedPackInfo> redPack_;
    
    private byte memoizedIsInitialized;
    
    private SCWebCurrentRedPackFeed(GeneratedMessageV3.Builder<?> builder) {
      super(builder);
      this.memoizedIsInitialized = -1;
    }
    
    private SCWebCurrentRedPackFeed() {
      this.memoizedIsInitialized = -1;
      this.redPack_ = Collections.emptyList();
    }
    
    protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
      return new SCWebCurrentRedPackFeed();
    }
    
    public static final Descriptors.Descriptor getDescriptor() {
      return SCWebCurrentRedPackFeedOuterClass.internal_static_SCWebCurrentRedPackFeed_descriptor;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return SCWebCurrentRedPackFeedOuterClass.internal_static_SCWebCurrentRedPackFeed_fieldAccessorTable.ensureFieldAccessorsInitialized(SCWebCurrentRedPackFeed.class, Builder.class);
    }
    
    public List<WebRedPackInfoOuterClass.WebRedPackInfo> getRedPackList() {
      return this.redPack_;
    }
    
    public List<? extends WebRedPackInfoOuterClass.WebRedPackInfoOrBuilder> getRedPackOrBuilderList() {
      return (List)this.redPack_;
    }
    
    public int getRedPackCount() {
      return this.redPack_.size();
    }
    
    public WebRedPackInfoOuterClass.WebRedPackInfo getRedPack(int index) {
      return this.redPack_.get(index);
    }
    
    public WebRedPackInfoOuterClass.WebRedPackInfoOrBuilder getRedPackOrBuilder(int index) {
      return this.redPack_.get(index);
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
      for (int i = 0; i < this.redPack_.size(); i++)
        output.writeMessage(1, (MessageLite)this.redPack_.get(i)); 
      getUnknownFields().writeTo(output);
    }
    
    public int getSerializedSize() {
      int size = this.memoizedSize;
      if (size != -1)
        return size; 
      size = 0;
      for (int i = 0; i < this.redPack_.size(); i++)
        size += 
          CodedOutputStream.computeMessageSize(1, (MessageLite)this.redPack_.get(i)); 
      size += getUnknownFields().getSerializedSize();
      this.memoizedSize = size;
      return size;
    }
    
    public boolean equals(Object obj) {
      if (obj == this)
        return true; 
      if (!(obj instanceof SCWebCurrentRedPackFeed))
        return super.equals(obj); 
      SCWebCurrentRedPackFeed other = (SCWebCurrentRedPackFeed)obj;
      if (!getRedPackList().equals(other.getRedPackList()))
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
      if (getRedPackCount() > 0) {
        hash = 37 * hash + 1;
        hash = 53 * hash + getRedPackList().hashCode();
      } 
      hash = 29 * hash + getUnknownFields().hashCode();
      this.memoizedHashCode = hash;
      return hash;
    }
    
    public static SCWebCurrentRedPackFeed parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (SCWebCurrentRedPackFeed)PARSER.parseFrom(data);
    }
    
    public static SCWebCurrentRedPackFeed parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (SCWebCurrentRedPackFeed)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static SCWebCurrentRedPackFeed parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (SCWebCurrentRedPackFeed)PARSER.parseFrom(data);
    }
    
    public static SCWebCurrentRedPackFeed parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (SCWebCurrentRedPackFeed)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static SCWebCurrentRedPackFeed parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (SCWebCurrentRedPackFeed)PARSER.parseFrom(data);
    }
    
    public static SCWebCurrentRedPackFeed parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (SCWebCurrentRedPackFeed)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static SCWebCurrentRedPackFeed parseFrom(InputStream input) throws IOException {
      return 
        (SCWebCurrentRedPackFeed)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static SCWebCurrentRedPackFeed parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (SCWebCurrentRedPackFeed)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static SCWebCurrentRedPackFeed parseDelimitedFrom(InputStream input) throws IOException {
      return 
        (SCWebCurrentRedPackFeed)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }
    
    public static SCWebCurrentRedPackFeed parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (SCWebCurrentRedPackFeed)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static SCWebCurrentRedPackFeed parseFrom(CodedInputStream input) throws IOException {
      return 
        (SCWebCurrentRedPackFeed)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static SCWebCurrentRedPackFeed parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (SCWebCurrentRedPackFeed)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public Builder newBuilderForType() {
      return newBuilder();
    }
    
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    
    public static Builder newBuilder(SCWebCurrentRedPackFeed prototype) {
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
    
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements SCWebCurrentRedPackFeedOuterClass.SCWebCurrentRedPackFeedOrBuilder {
      private int bitField0_;
      
      private List<WebRedPackInfoOuterClass.WebRedPackInfo> redPack_;
      
      private RepeatedFieldBuilderV3<WebRedPackInfoOuterClass.WebRedPackInfo, WebRedPackInfoOuterClass.WebRedPackInfo.Builder, WebRedPackInfoOuterClass.WebRedPackInfoOrBuilder> redPackBuilder_;
      
      public static final Descriptors.Descriptor getDescriptor() {
        return SCWebCurrentRedPackFeedOuterClass.internal_static_SCWebCurrentRedPackFeed_descriptor;
      }
      
      protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return SCWebCurrentRedPackFeedOuterClass.internal_static_SCWebCurrentRedPackFeed_fieldAccessorTable
          .ensureFieldAccessorsInitialized(SCWebCurrentRedPackFeedOuterClass.SCWebCurrentRedPackFeed.class, Builder.class);
      }
      
      private Builder() {
        this
          .redPack_ = Collections.emptyList();
      }
      
      private Builder(GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        this.redPack_ = Collections.emptyList();
      }
      
      public Builder clear() {
        super.clear();
        this.bitField0_ = 0;
        if (this.redPackBuilder_ == null) {
          this.redPack_ = Collections.emptyList();
        } else {
          this.redPack_ = null;
          this.redPackBuilder_.clear();
        } 
        this.bitField0_ &= 0xFFFFFFFE;
        return this;
      }
      
      public Descriptors.Descriptor getDescriptorForType() {
        return SCWebCurrentRedPackFeedOuterClass.internal_static_SCWebCurrentRedPackFeed_descriptor;
      }
      
      public SCWebCurrentRedPackFeedOuterClass.SCWebCurrentRedPackFeed getDefaultInstanceForType() {
        return SCWebCurrentRedPackFeedOuterClass.SCWebCurrentRedPackFeed.getDefaultInstance();
      }
      
      public SCWebCurrentRedPackFeedOuterClass.SCWebCurrentRedPackFeed build() {
        SCWebCurrentRedPackFeedOuterClass.SCWebCurrentRedPackFeed result = buildPartial();
        if (!result.isInitialized())
          throw newUninitializedMessageException(result); 
        return result;
      }
      
      public SCWebCurrentRedPackFeedOuterClass.SCWebCurrentRedPackFeed buildPartial() {
        SCWebCurrentRedPackFeedOuterClass.SCWebCurrentRedPackFeed result = new SCWebCurrentRedPackFeedOuterClass.SCWebCurrentRedPackFeed(this);
        buildPartialRepeatedFields(result);
        if (this.bitField0_ != 0)
          buildPartial0(result); 
        onBuilt();
        return result;
      }
      
      private void buildPartialRepeatedFields(SCWebCurrentRedPackFeedOuterClass.SCWebCurrentRedPackFeed result) {
        if (this.redPackBuilder_ == null) {
          if ((this.bitField0_ & 0x1) != 0) {
            this.redPack_ = Collections.unmodifiableList(this.redPack_);
            this.bitField0_ &= 0xFFFFFFFE;
          } 
          result.redPack_ = this.redPack_;
        } else {
          result.redPack_ = this.redPackBuilder_.build();
        } 
      }
      
      private void buildPartial0(SCWebCurrentRedPackFeedOuterClass.SCWebCurrentRedPackFeed result) {
        int from_bitField0_ = this.bitField0_;
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
        if (other instanceof SCWebCurrentRedPackFeedOuterClass.SCWebCurrentRedPackFeed)
          return mergeFrom((SCWebCurrentRedPackFeedOuterClass.SCWebCurrentRedPackFeed)other); 
        super.mergeFrom(other);
        return this;
      }
      
      public Builder mergeFrom(SCWebCurrentRedPackFeedOuterClass.SCWebCurrentRedPackFeed other) {
        if (other == SCWebCurrentRedPackFeedOuterClass.SCWebCurrentRedPackFeed.getDefaultInstance())
          return this; 
        if (this.redPackBuilder_ == null) {
          if (!other.redPack_.isEmpty()) {
            if (this.redPack_.isEmpty()) {
              this.redPack_ = other.redPack_;
              this.bitField0_ &= 0xFFFFFFFE;
            } else {
              ensureRedPackIsMutable();
              this.redPack_.addAll(other.redPack_);
            } 
            onChanged();
          } 
        } else if (!other.redPack_.isEmpty()) {
          if (this.redPackBuilder_.isEmpty()) {
            this.redPackBuilder_.dispose();
            this.redPackBuilder_ = null;
            this.redPack_ = other.redPack_;
            this.bitField0_ &= 0xFFFFFFFE;
            this.redPackBuilder_ = SCWebCurrentRedPackFeedOuterClass.SCWebCurrentRedPackFeed.alwaysUseFieldBuilders ? getRedPackFieldBuilder() : null;
          } else {
            this.redPackBuilder_.addAllMessages(other.redPack_);
          } 
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
            WebRedPackInfoOuterClass.WebRedPackInfo m;
            int tag = input.readTag();
            switch (tag) {
              case 0:
                done = true;
                continue;
              case 10:
                m = (WebRedPackInfoOuterClass.WebRedPackInfo)input.readMessage(WebRedPackInfoOuterClass.WebRedPackInfo.parser(), extensionRegistry);
                if (this.redPackBuilder_ == null) {
                  ensureRedPackIsMutable();
                  this.redPack_.add(m);
                  continue;
                } 
                this.redPackBuilder_.addMessage((AbstractMessage)m);
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
      
      private void ensureRedPackIsMutable() {
        if ((this.bitField0_ & 0x1) == 0) {
          this.redPack_ = new ArrayList<>(this.redPack_);
          this.bitField0_ |= 0x1;
        } 
      }
      
      public List<WebRedPackInfoOuterClass.WebRedPackInfo> getRedPackList() {
        if (this.redPackBuilder_ == null)
          return Collections.unmodifiableList(this.redPack_); 
        return this.redPackBuilder_.getMessageList();
      }
      
      public int getRedPackCount() {
        if (this.redPackBuilder_ == null)
          return this.redPack_.size(); 
        return this.redPackBuilder_.getCount();
      }
      
      public WebRedPackInfoOuterClass.WebRedPackInfo getRedPack(int index) {
        if (this.redPackBuilder_ == null)
          return this.redPack_.get(index); 
        return (WebRedPackInfoOuterClass.WebRedPackInfo)this.redPackBuilder_.getMessage(index);
      }
      
      public Builder setRedPack(int index, WebRedPackInfoOuterClass.WebRedPackInfo value) {
        if (this.redPackBuilder_ == null) {
          if (value == null)
            throw new NullPointerException(); 
          ensureRedPackIsMutable();
          this.redPack_.set(index, value);
          onChanged();
        } else {
          this.redPackBuilder_.setMessage(index, (AbstractMessage)value);
        } 
        return this;
      }
      
      public Builder setRedPack(int index, WebRedPackInfoOuterClass.WebRedPackInfo.Builder builderForValue) {
        if (this.redPackBuilder_ == null) {
          ensureRedPackIsMutable();
          this.redPack_.set(index, builderForValue.build());
          onChanged();
        } else {
          this.redPackBuilder_.setMessage(index, (AbstractMessage)builderForValue.build());
        } 
        return this;
      }
      
      public Builder addRedPack(WebRedPackInfoOuterClass.WebRedPackInfo value) {
        if (this.redPackBuilder_ == null) {
          if (value == null)
            throw new NullPointerException(); 
          ensureRedPackIsMutable();
          this.redPack_.add(value);
          onChanged();
        } else {
          this.redPackBuilder_.addMessage((AbstractMessage)value);
        } 
        return this;
      }
      
      public Builder addRedPack(int index, WebRedPackInfoOuterClass.WebRedPackInfo value) {
        if (this.redPackBuilder_ == null) {
          if (value == null)
            throw new NullPointerException(); 
          ensureRedPackIsMutable();
          this.redPack_.add(index, value);
          onChanged();
        } else {
          this.redPackBuilder_.addMessage(index, (AbstractMessage)value);
        } 
        return this;
      }
      
      public Builder addRedPack(WebRedPackInfoOuterClass.WebRedPackInfo.Builder builderForValue) {
        if (this.redPackBuilder_ == null) {
          ensureRedPackIsMutable();
          this.redPack_.add(builderForValue.build());
          onChanged();
        } else {
          this.redPackBuilder_.addMessage((AbstractMessage)builderForValue.build());
        } 
        return this;
      }
      
      public Builder addRedPack(int index, WebRedPackInfoOuterClass.WebRedPackInfo.Builder builderForValue) {
        if (this.redPackBuilder_ == null) {
          ensureRedPackIsMutable();
          this.redPack_.add(index, builderForValue.build());
          onChanged();
        } else {
          this.redPackBuilder_.addMessage(index, (AbstractMessage)builderForValue.build());
        } 
        return this;
      }
      
      public Builder addAllRedPack(Iterable<? extends WebRedPackInfoOuterClass.WebRedPackInfo> values) {
        if (this.redPackBuilder_ == null) {
          ensureRedPackIsMutable();
          AbstractMessageLite.Builder.addAll(values, this.redPack_);
          onChanged();
        } else {
          this.redPackBuilder_.addAllMessages(values);
        } 
        return this;
      }
      
      public Builder clearRedPack() {
        if (this.redPackBuilder_ == null) {
          this.redPack_ = Collections.emptyList();
          this.bitField0_ &= 0xFFFFFFFE;
          onChanged();
        } else {
          this.redPackBuilder_.clear();
        } 
        return this;
      }
      
      public Builder removeRedPack(int index) {
        if (this.redPackBuilder_ == null) {
          ensureRedPackIsMutable();
          this.redPack_.remove(index);
          onChanged();
        } else {
          this.redPackBuilder_.remove(index);
        } 
        return this;
      }
      
      public WebRedPackInfoOuterClass.WebRedPackInfo.Builder getRedPackBuilder(int index) {
        return (WebRedPackInfoOuterClass.WebRedPackInfo.Builder)getRedPackFieldBuilder().getBuilder(index);
      }
      
      public WebRedPackInfoOuterClass.WebRedPackInfoOrBuilder getRedPackOrBuilder(int index) {
        if (this.redPackBuilder_ == null)
          return this.redPack_.get(index); 
        return (WebRedPackInfoOuterClass.WebRedPackInfoOrBuilder)this.redPackBuilder_.getMessageOrBuilder(index);
      }
      
      public List<? extends WebRedPackInfoOuterClass.WebRedPackInfoOrBuilder> getRedPackOrBuilderList() {
        if (this.redPackBuilder_ != null)
          return this.redPackBuilder_.getMessageOrBuilderList(); 
        return Collections.unmodifiableList((List)this.redPack_);
      }
      
      public WebRedPackInfoOuterClass.WebRedPackInfo.Builder addRedPackBuilder() {
        return (WebRedPackInfoOuterClass.WebRedPackInfo.Builder)getRedPackFieldBuilder().addBuilder(
            (AbstractMessage)WebRedPackInfoOuterClass.WebRedPackInfo.getDefaultInstance());
      }
      
      public WebRedPackInfoOuterClass.WebRedPackInfo.Builder addRedPackBuilder(int index) {
        return (WebRedPackInfoOuterClass.WebRedPackInfo.Builder)getRedPackFieldBuilder().addBuilder(index, 
            (AbstractMessage)WebRedPackInfoOuterClass.WebRedPackInfo.getDefaultInstance());
      }
      
      public List<WebRedPackInfoOuterClass.WebRedPackInfo.Builder> getRedPackBuilderList() {
        return getRedPackFieldBuilder().getBuilderList();
      }
      
      private RepeatedFieldBuilderV3<WebRedPackInfoOuterClass.WebRedPackInfo, WebRedPackInfoOuterClass.WebRedPackInfo.Builder, WebRedPackInfoOuterClass.WebRedPackInfoOrBuilder> getRedPackFieldBuilder() {
        if (this.redPackBuilder_ == null) {
          this
            
            .redPackBuilder_ = new RepeatedFieldBuilderV3(this.redPack_, ((this.bitField0_ & 0x1) != 0), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
          this.redPack_ = null;
        } 
        return this.redPackBuilder_;
      }
      
      public final Builder setUnknownFields(UnknownFieldSet unknownFields) {
        return (Builder)super.setUnknownFields(unknownFields);
      }
      
      public final Builder mergeUnknownFields(UnknownFieldSet unknownFields) {
        return (Builder)super.mergeUnknownFields(unknownFields);
      }
    }
    
    private static final SCWebCurrentRedPackFeed DEFAULT_INSTANCE = new SCWebCurrentRedPackFeed();
    
    public static SCWebCurrentRedPackFeed getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }
    
    private static final Parser<SCWebCurrentRedPackFeed> PARSER = (Parser<SCWebCurrentRedPackFeed>)new AbstractParser<SCWebCurrentRedPackFeed>() {
        public SCWebCurrentRedPackFeedOuterClass.SCWebCurrentRedPackFeed parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
          SCWebCurrentRedPackFeedOuterClass.SCWebCurrentRedPackFeed.Builder builder = SCWebCurrentRedPackFeedOuterClass.SCWebCurrentRedPackFeed.newBuilder();
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
    
    public static Parser<SCWebCurrentRedPackFeed> parser() {
      return PARSER;
    }
    
    public Parser<SCWebCurrentRedPackFeed> getParserForType() {
      return PARSER;
    }
    
    public SCWebCurrentRedPackFeed getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }
  }
  
  public static Descriptors.FileDescriptor getDescriptor() {
    return descriptor;
  }
  
  static {
    String[] descriptorData = { "\n\035SCWebCurrentRedPackFeed.proto\032\024WebRedPackInfo.proto\";\n\027SCWebCurrentRedPackFeed\022 \n\007redPack\030\001 \003(\0132\017.WebRedPackInfoB<\n:tech.ordinaryroad.live.chat.client.codec.kuaishou.protobufb\006proto3" };
    descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[] { WebRedPackInfoOuterClass.getDescriptor() });
    internal_static_SCWebCurrentRedPackFeed_descriptor = getDescriptor().getMessageTypes().get(0);
    internal_static_SCWebCurrentRedPackFeed_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_SCWebCurrentRedPackFeed_descriptor, new String[] { "RedPack" });
    WebRedPackInfoOuterClass.getDescriptor();
  }
  
  public static interface SCWebCurrentRedPackFeedOrBuilder extends MessageOrBuilder {
    List<WebRedPackInfoOuterClass.WebRedPackInfo> getRedPackList();
    
    WebRedPackInfoOuterClass.WebRedPackInfo getRedPack(int param1Int);
    
    int getRedPackCount();
    
    List<? extends WebRedPackInfoOuterClass.WebRedPackInfoOrBuilder> getRedPackOrBuilderList();
    
    WebRedPackInfoOuterClass.WebRedPackInfoOrBuilder getRedPackOrBuilder(int param1Int);
  }
}
