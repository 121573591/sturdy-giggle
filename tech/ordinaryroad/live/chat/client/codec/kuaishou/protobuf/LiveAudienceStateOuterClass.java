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
import com.google.protobuf.ProtocolMessageEnum;
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class LiveAudienceStateOuterClass {
  private static final Descriptors.Descriptor internal_static_LiveAudienceState_descriptor;
  
  private static final GeneratedMessageV3.FieldAccessorTable internal_static_LiveAudienceState_fieldAccessorTable;
  
  private static final Descriptors.Descriptor internal_static_LiveAudienceState_LiveAudienceState_11_descriptor;
  
  private static final GeneratedMessageV3.FieldAccessorTable internal_static_LiveAudienceState_LiveAudienceState_11_fieldAccessorTable;
  
  private static final Descriptors.Descriptor internal_static_LiveAudienceState_LiveAudienceState_11_LiveAudienceState_11_1_descriptor;
  
  private static final GeneratedMessageV3.FieldAccessorTable internal_static_LiveAudienceState_LiveAudienceState_11_LiveAudienceState_11_1_fieldAccessorTable;
  
  private static Descriptors.FileDescriptor descriptor;
  
  public static void registerAllExtensions(ExtensionRegistryLite registry) {}
  
  public static void registerAllExtensions(ExtensionRegistry registry) {
    registerAllExtensions((ExtensionRegistryLite)registry);
  }
  
  public static final class LiveAudienceState extends GeneratedMessageV3 implements LiveAudienceStateOrBuilder {
    private static final long serialVersionUID = 0L;
    
    private int bitField0_;
    
    public static final int ISFROMFANSTOP_FIELD_NUMBER = 1;
    
    private boolean isFromFansTop_;
    
    public static final int ISKOI_FIELD_NUMBER = 2;
    
    private boolean isKoi_;
    
    public static final int ASSISTANTTYPE_FIELD_NUMBER = 3;
    
    private int assistantType_;
    
    public static final int FANSGROUPINTIMACYLEVEL_FIELD_NUMBER = 4;
    
    private int fansGroupIntimacyLevel_;
    
    public static final int NAMEPLATE_FIELD_NUMBER = 5;
    
    private GzoneNameplateOuterClass.GzoneNameplate nameplate_;
    
    public static final int LIVEFANSGROUPSTATE_FIELD_NUMBER = 6;
    
    private LiveFansGroupStateOuterClass.LiveFansGroupState liveFansGroupState_;
    
    public static final int WEALTHGRADE_FIELD_NUMBER = 7;
    
    private int wealthGrade_;
    
    public static final int BADGEKEY_FIELD_NUMBER = 8;
    
    private volatile Object badgeKey_;
    
    public static final int LIVEAUDIENCESTATE_11_FIELD_NUMBER = 11;
    
    private List<LiveAudienceState_11> liveAudienceState11_;
    
    private byte memoizedIsInitialized;
    
    private LiveAudienceState(GeneratedMessageV3.Builder<?> builder) {
      super(builder);
      this.isFromFansTop_ = false;
      this.isKoi_ = false;
      this.assistantType_ = 0;
      this.fansGroupIntimacyLevel_ = 0;
      this.wealthGrade_ = 0;
      this.badgeKey_ = "";
      this.memoizedIsInitialized = -1;
    }
    
    private LiveAudienceState() {
      this.isFromFansTop_ = false;
      this.isKoi_ = false;
      this.assistantType_ = 0;
      this.fansGroupIntimacyLevel_ = 0;
      this.wealthGrade_ = 0;
      this.badgeKey_ = "";
      this.memoizedIsInitialized = -1;
      this.assistantType_ = 0;
      this.badgeKey_ = "";
      this.liveAudienceState11_ = Collections.emptyList();
    }
    
    protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
      return new LiveAudienceState();
    }
    
    public static final Descriptors.Descriptor getDescriptor() {
      return LiveAudienceStateOuterClass.internal_static_LiveAudienceState_descriptor;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return LiveAudienceStateOuterClass.internal_static_LiveAudienceState_fieldAccessorTable.ensureFieldAccessorsInitialized(LiveAudienceState.class, Builder.class);
    }
    
    public enum AssistantType implements ProtocolMessageEnum {
      UNKNOWN_ASSISTANT_TYPE(0),
      SUPER(1),
      JUNIOR(2),
      UNRECOGNIZED(-1);
      
      public static final int UNKNOWN_ASSISTANT_TYPE_VALUE = 0;
      
      public static final int SUPER_VALUE = 1;
      
      public static final int JUNIOR_VALUE = 2;
      
      private static final Internal.EnumLiteMap<AssistantType> internalValueMap = new Internal.EnumLiteMap<AssistantType>() {
          public LiveAudienceStateOuterClass.LiveAudienceState.AssistantType findValueByNumber(int number) {
            return LiveAudienceStateOuterClass.LiveAudienceState.AssistantType.forNumber(number);
          }
        };
      
      private static final AssistantType[] VALUES = values();
      
      private final int value;
      
      public final int getNumber() {
        if (this == UNRECOGNIZED)
          throw new IllegalArgumentException("Can't get the number of an unknown enum value."); 
        return this.value;
      }
      
      public static AssistantType forNumber(int value) {
        switch (value) {
          case 0:
            return UNKNOWN_ASSISTANT_TYPE;
          case 1:
            return SUPER;
          case 2:
            return JUNIOR;
        } 
        return null;
      }
      
      public static Internal.EnumLiteMap<AssistantType> internalGetValueMap() {
        return internalValueMap;
      }
      
      static {
      
      }
      
      public final Descriptors.EnumValueDescriptor getValueDescriptor() {
        if (this == UNRECOGNIZED)
          throw new IllegalStateException("Can't get the descriptor of an unrecognized enum value."); 
        return getDescriptor().getValues().get(ordinal());
      }
      
      public final Descriptors.EnumDescriptor getDescriptorForType() {
        return getDescriptor();
      }
      
      public static final Descriptors.EnumDescriptor getDescriptor() {
        return LiveAudienceStateOuterClass.LiveAudienceState.getDescriptor().getEnumTypes().get(0);
      }
      
      AssistantType(int value) {
        this.value = value;
      }
    }
    
    public static final class LiveAudienceState_11 extends GeneratedMessageV3 implements LiveAudienceState_11OrBuilder {
      private static final long serialVersionUID = 0L;
      
      private int bitField0_;
      
      public static final int LIVEAUDIENCESTATE_11_1_FIELD_NUMBER = 1;
      
      private LiveAudienceState_11_1 liveAudienceState111_;
      
      private byte memoizedIsInitialized;
      
      private LiveAudienceState_11(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = -1;
      }
      
      private LiveAudienceState_11() {
        this.memoizedIsInitialized = -1;
      }
      
      protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
        return new LiveAudienceState_11();
      }
      
      public static final Descriptors.Descriptor getDescriptor() {
        return LiveAudienceStateOuterClass.internal_static_LiveAudienceState_LiveAudienceState_11_descriptor;
      }
      
      protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return LiveAudienceStateOuterClass.internal_static_LiveAudienceState_LiveAudienceState_11_fieldAccessorTable.ensureFieldAccessorsInitialized(LiveAudienceState_11.class, Builder.class);
      }
      
      public static final class LiveAudienceState_11_1 extends GeneratedMessageV3 implements LiveAudienceState_11_1OrBuilder {
        private static final long serialVersionUID = 0L;
        
        public static final int BADGEICON_FIELD_NUMBER = 2;
        
        private volatile Object badgeIcon_;
        
        public static final int BADGENAME_FIELD_NUMBER = 4;
        
        private volatile Object badgeName_;
        
        private byte memoizedIsInitialized;
        
        private LiveAudienceState_11_1(GeneratedMessageV3.Builder<?> builder) {
          super(builder);
          this.badgeIcon_ = "";
          this.badgeName_ = "";
          this.memoizedIsInitialized = -1;
        }
        
        private LiveAudienceState_11_1() {
          this.badgeIcon_ = "";
          this.badgeName_ = "";
          this.memoizedIsInitialized = -1;
          this.badgeIcon_ = "";
          this.badgeName_ = "";
        }
        
        protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
          return new LiveAudienceState_11_1();
        }
        
        public static final Descriptors.Descriptor getDescriptor() {
          return LiveAudienceStateOuterClass.internal_static_LiveAudienceState_LiveAudienceState_11_LiveAudienceState_11_1_descriptor;
        }
        
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
          return LiveAudienceStateOuterClass.internal_static_LiveAudienceState_LiveAudienceState_11_LiveAudienceState_11_1_fieldAccessorTable.ensureFieldAccessorsInitialized(LiveAudienceState_11_1.class, Builder.class);
        }
        
        public String getBadgeIcon() {
          Object ref = this.badgeIcon_;
          if (ref instanceof String)
            return (String)ref; 
          ByteString bs = (ByteString)ref;
          String s = bs.toStringUtf8();
          this.badgeIcon_ = s;
          return s;
        }
        
        public ByteString getBadgeIconBytes() {
          Object ref = this.badgeIcon_;
          if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            this.badgeIcon_ = b;
            return b;
          } 
          return (ByteString)ref;
        }
        
        public String getBadgeName() {
          Object ref = this.badgeName_;
          if (ref instanceof String)
            return (String)ref; 
          ByteString bs = (ByteString)ref;
          String s = bs.toStringUtf8();
          this.badgeName_ = s;
          return s;
        }
        
        public ByteString getBadgeNameBytes() {
          Object ref = this.badgeName_;
          if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            this.badgeName_ = b;
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
          if (!GeneratedMessageV3.isStringEmpty(this.badgeIcon_))
            GeneratedMessageV3.writeString(output, 2, this.badgeIcon_); 
          if (!GeneratedMessageV3.isStringEmpty(this.badgeName_))
            GeneratedMessageV3.writeString(output, 4, this.badgeName_); 
          getUnknownFields().writeTo(output);
        }
        
        public int getSerializedSize() {
          int size = this.memoizedSize;
          if (size != -1)
            return size; 
          size = 0;
          if (!GeneratedMessageV3.isStringEmpty(this.badgeIcon_))
            size += GeneratedMessageV3.computeStringSize(2, this.badgeIcon_); 
          if (!GeneratedMessageV3.isStringEmpty(this.badgeName_))
            size += GeneratedMessageV3.computeStringSize(4, this.badgeName_); 
          size += getUnknownFields().getSerializedSize();
          this.memoizedSize = size;
          return size;
        }
        
        public boolean equals(Object obj) {
          if (obj == this)
            return true; 
          if (!(obj instanceof LiveAudienceState_11_1))
            return super.equals(obj); 
          LiveAudienceState_11_1 other = (LiveAudienceState_11_1)obj;
          if (!getBadgeIcon().equals(other.getBadgeIcon()))
            return false; 
          if (!getBadgeName().equals(other.getBadgeName()))
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
          hash = 37 * hash + 2;
          hash = 53 * hash + getBadgeIcon().hashCode();
          hash = 37 * hash + 4;
          hash = 53 * hash + getBadgeName().hashCode();
          hash = 29 * hash + getUnknownFields().hashCode();
          this.memoizedHashCode = hash;
          return hash;
        }
        
        public static LiveAudienceState_11_1 parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
          return (LiveAudienceState_11_1)PARSER.parseFrom(data);
        }
        
        public static LiveAudienceState_11_1 parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
          return (LiveAudienceState_11_1)PARSER.parseFrom(data, extensionRegistry);
        }
        
        public static LiveAudienceState_11_1 parseFrom(ByteString data) throws InvalidProtocolBufferException {
          return (LiveAudienceState_11_1)PARSER.parseFrom(data);
        }
        
        public static LiveAudienceState_11_1 parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
          return (LiveAudienceState_11_1)PARSER.parseFrom(data, extensionRegistry);
        }
        
        public static LiveAudienceState_11_1 parseFrom(byte[] data) throws InvalidProtocolBufferException {
          return (LiveAudienceState_11_1)PARSER.parseFrom(data);
        }
        
        public static LiveAudienceState_11_1 parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
          return (LiveAudienceState_11_1)PARSER.parseFrom(data, extensionRegistry);
        }
        
        public static LiveAudienceState_11_1 parseFrom(InputStream input) throws IOException {
          return (LiveAudienceState_11_1)GeneratedMessageV3.parseWithIOException(PARSER, input);
        }
        
        public static LiveAudienceState_11_1 parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
          return (LiveAudienceState_11_1)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
        }
        
        public static LiveAudienceState_11_1 parseDelimitedFrom(InputStream input) throws IOException {
          return (LiveAudienceState_11_1)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
        }
        
        public static LiveAudienceState_11_1 parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
          return (LiveAudienceState_11_1)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
        }
        
        public static LiveAudienceState_11_1 parseFrom(CodedInputStream input) throws IOException {
          return (LiveAudienceState_11_1)GeneratedMessageV3.parseWithIOException(PARSER, input);
        }
        
        public static LiveAudienceState_11_1 parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
          return (LiveAudienceState_11_1)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
        }
        
        public Builder newBuilderForType() {
          return newBuilder();
        }
        
        public static Builder newBuilder() {
          return DEFAULT_INSTANCE.toBuilder();
        }
        
        public static Builder newBuilder(LiveAudienceState_11_1 prototype) {
          return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
        }
        
        public Builder toBuilder() {
          return (this == DEFAULT_INSTANCE) ? new Builder() : (new Builder()).mergeFrom(this);
        }
        
        protected Builder newBuilderForType(GeneratedMessageV3.BuilderParent parent) {
          Builder builder = new Builder(parent);
          return builder;
        }
        
        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements LiveAudienceStateOuterClass.LiveAudienceState.LiveAudienceState_11.LiveAudienceState_11_1OrBuilder {
          private int bitField0_;
          
          private Object badgeIcon_;
          
          private Object badgeName_;
          
          public static final Descriptors.Descriptor getDescriptor() {
            return LiveAudienceStateOuterClass.internal_static_LiveAudienceState_LiveAudienceState_11_LiveAudienceState_11_1_descriptor;
          }
          
          protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return LiveAudienceStateOuterClass.internal_static_LiveAudienceState_LiveAudienceState_11_LiveAudienceState_11_1_fieldAccessorTable.ensureFieldAccessorsInitialized(LiveAudienceStateOuterClass.LiveAudienceState.LiveAudienceState_11.LiveAudienceState_11_1.class, Builder.class);
          }
          
          private Builder() {
            this.badgeIcon_ = "";
            this.badgeName_ = "";
          }
          
          private Builder(GeneratedMessageV3.BuilderParent parent) {
            super(parent);
            this.badgeIcon_ = "";
            this.badgeName_ = "";
          }
          
          public Builder clear() {
            super.clear();
            this.bitField0_ = 0;
            this.badgeIcon_ = "";
            this.badgeName_ = "";
            return this;
          }
          
          public Descriptors.Descriptor getDescriptorForType() {
            return LiveAudienceStateOuterClass.internal_static_LiveAudienceState_LiveAudienceState_11_LiveAudienceState_11_1_descriptor;
          }
          
          public LiveAudienceStateOuterClass.LiveAudienceState.LiveAudienceState_11.LiveAudienceState_11_1 getDefaultInstanceForType() {
            return LiveAudienceStateOuterClass.LiveAudienceState.LiveAudienceState_11.LiveAudienceState_11_1.getDefaultInstance();
          }
          
          public LiveAudienceStateOuterClass.LiveAudienceState.LiveAudienceState_11.LiveAudienceState_11_1 build() {
            LiveAudienceStateOuterClass.LiveAudienceState.LiveAudienceState_11.LiveAudienceState_11_1 result = buildPartial();
            if (!result.isInitialized())
              throw newUninitializedMessageException(result); 
            return result;
          }
          
          public LiveAudienceStateOuterClass.LiveAudienceState.LiveAudienceState_11.LiveAudienceState_11_1 buildPartial() {
            LiveAudienceStateOuterClass.LiveAudienceState.LiveAudienceState_11.LiveAudienceState_11_1 result = new LiveAudienceStateOuterClass.LiveAudienceState.LiveAudienceState_11.LiveAudienceState_11_1(this);
            if (this.bitField0_ != 0)
              buildPartial0(result); 
            onBuilt();
            return result;
          }
          
          private void buildPartial0(LiveAudienceStateOuterClass.LiveAudienceState.LiveAudienceState_11.LiveAudienceState_11_1 result) {
            int from_bitField0_ = this.bitField0_;
            if ((from_bitField0_ & 0x1) != 0)
              result.badgeIcon_ = this.badgeIcon_; 
            if ((from_bitField0_ & 0x2) != 0)
              result.badgeName_ = this.badgeName_; 
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
            if (other instanceof LiveAudienceStateOuterClass.LiveAudienceState.LiveAudienceState_11.LiveAudienceState_11_1)
              return mergeFrom((LiveAudienceStateOuterClass.LiveAudienceState.LiveAudienceState_11.LiveAudienceState_11_1)other); 
            super.mergeFrom(other);
            return this;
          }
          
          public Builder mergeFrom(LiveAudienceStateOuterClass.LiveAudienceState.LiveAudienceState_11.LiveAudienceState_11_1 other) {
            if (other == LiveAudienceStateOuterClass.LiveAudienceState.LiveAudienceState_11.LiveAudienceState_11_1.getDefaultInstance())
              return this; 
            if (!other.getBadgeIcon().isEmpty()) {
              this.badgeIcon_ = other.badgeIcon_;
              this.bitField0_ |= 0x1;
              onChanged();
            } 
            if (!other.getBadgeName().isEmpty()) {
              this.badgeName_ = other.badgeName_;
              this.bitField0_ |= 0x2;
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
                  case 18:
                    this.badgeIcon_ = input.readStringRequireUtf8();
                    this.bitField0_ |= 0x1;
                    continue;
                  case 34:
                    this.badgeName_ = input.readStringRequireUtf8();
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
          
          public String getBadgeIcon() {
            Object ref = this.badgeIcon_;
            if (!(ref instanceof String)) {
              ByteString bs = (ByteString)ref;
              String s = bs.toStringUtf8();
              this.badgeIcon_ = s;
              return s;
            } 
            return (String)ref;
          }
          
          public ByteString getBadgeIconBytes() {
            Object ref = this.badgeIcon_;
            if (ref instanceof String) {
              ByteString b = ByteString.copyFromUtf8((String)ref);
              this.badgeIcon_ = b;
              return b;
            } 
            return (ByteString)ref;
          }
          
          public Builder setBadgeIcon(String value) {
            if (value == null)
              throw new NullPointerException(); 
            this.badgeIcon_ = value;
            this.bitField0_ |= 0x1;
            onChanged();
            return this;
          }
          
          public Builder clearBadgeIcon() {
            this.badgeIcon_ = LiveAudienceStateOuterClass.LiveAudienceState.LiveAudienceState_11.LiveAudienceState_11_1.getDefaultInstance().getBadgeIcon();
            this.bitField0_ &= 0xFFFFFFFE;
            onChanged();
            return this;
          }
          
          public Builder setBadgeIconBytes(ByteString value) {
            if (value == null)
              throw new NullPointerException(); 
            LiveAudienceStateOuterClass.LiveAudienceState.LiveAudienceState_11.LiveAudienceState_11_1.checkByteStringIsUtf8(value);
            this.badgeIcon_ = value;
            this.bitField0_ |= 0x1;
            onChanged();
            return this;
          }
          
          public String getBadgeName() {
            Object ref = this.badgeName_;
            if (!(ref instanceof String)) {
              ByteString bs = (ByteString)ref;
              String s = bs.toStringUtf8();
              this.badgeName_ = s;
              return s;
            } 
            return (String)ref;
          }
          
          public ByteString getBadgeNameBytes() {
            Object ref = this.badgeName_;
            if (ref instanceof String) {
              ByteString b = ByteString.copyFromUtf8((String)ref);
              this.badgeName_ = b;
              return b;
            } 
            return (ByteString)ref;
          }
          
          public Builder setBadgeName(String value) {
            if (value == null)
              throw new NullPointerException(); 
            this.badgeName_ = value;
            this.bitField0_ |= 0x2;
            onChanged();
            return this;
          }
          
          public Builder clearBadgeName() {
            this.badgeName_ = LiveAudienceStateOuterClass.LiveAudienceState.LiveAudienceState_11.LiveAudienceState_11_1.getDefaultInstance().getBadgeName();
            this.bitField0_ &= 0xFFFFFFFD;
            onChanged();
            return this;
          }
          
          public Builder setBadgeNameBytes(ByteString value) {
            if (value == null)
              throw new NullPointerException(); 
            LiveAudienceStateOuterClass.LiveAudienceState.LiveAudienceState_11.LiveAudienceState_11_1.checkByteStringIsUtf8(value);
            this.badgeName_ = value;
            this.bitField0_ |= 0x2;
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
        
        private static final LiveAudienceState_11_1 DEFAULT_INSTANCE = new LiveAudienceState_11_1();
        
        public static LiveAudienceState_11_1 getDefaultInstance() {
          return DEFAULT_INSTANCE;
        }
        
        private static final Parser<LiveAudienceState_11_1> PARSER = (Parser<LiveAudienceState_11_1>)new AbstractParser<LiveAudienceState_11_1>() {
            public LiveAudienceStateOuterClass.LiveAudienceState.LiveAudienceState_11.LiveAudienceState_11_1 parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
              LiveAudienceStateOuterClass.LiveAudienceState.LiveAudienceState_11.LiveAudienceState_11_1.Builder builder = LiveAudienceStateOuterClass.LiveAudienceState.LiveAudienceState_11.LiveAudienceState_11_1.newBuilder();
              try {
                builder.mergeFrom(input, extensionRegistry);
              } catch (InvalidProtocolBufferException e) {
                throw e.setUnfinishedMessage(builder.buildPartial());
              } catch (UninitializedMessageException e) {
                throw e.asInvalidProtocolBufferException().setUnfinishedMessage(builder.buildPartial());
              } catch (IOException e) {
                throw (new InvalidProtocolBufferException(e)).setUnfinishedMessage(builder.buildPartial());
              } 
              return builder.buildPartial();
            }
          };
        
        public static Parser<LiveAudienceState_11_1> parser() {
          return PARSER;
        }
        
        public Parser<LiveAudienceState_11_1> getParserForType() {
          return PARSER;
        }
        
        public LiveAudienceState_11_1 getDefaultInstanceForType() {
          return DEFAULT_INSTANCE;
        }
      }
      
      public boolean hasLiveAudienceState111() {
        return ((this.bitField0_ & 0x1) != 0);
      }
      
      public LiveAudienceState_11_1 getLiveAudienceState111() {
        return (this.liveAudienceState111_ == null) ? LiveAudienceState_11_1.getDefaultInstance() : this.liveAudienceState111_;
      }
      
      public LiveAudienceState_11_1OrBuilder getLiveAudienceState111OrBuilder() {
        return (this.liveAudienceState111_ == null) ? LiveAudienceState_11_1.getDefaultInstance() : this.liveAudienceState111_;
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
        if ((this.bitField0_ & 0x1) != 0)
          output.writeMessage(1, (MessageLite)getLiveAudienceState111()); 
        getUnknownFields().writeTo(output);
      }
      
      public int getSerializedSize() {
        int size = this.memoizedSize;
        if (size != -1)
          return size; 
        size = 0;
        if ((this.bitField0_ & 0x1) != 0)
          size += CodedOutputStream.computeMessageSize(1, (MessageLite)getLiveAudienceState111()); 
        size += getUnknownFields().getSerializedSize();
        this.memoizedSize = size;
        return size;
      }
      
      public boolean equals(Object obj) {
        if (obj == this)
          return true; 
        if (!(obj instanceof LiveAudienceState_11))
          return super.equals(obj); 
        LiveAudienceState_11 other = (LiveAudienceState_11)obj;
        if (hasLiveAudienceState111() != other.hasLiveAudienceState111())
          return false; 
        if (hasLiveAudienceState111() && !getLiveAudienceState111().equals(other.getLiveAudienceState111()))
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
        if (hasLiveAudienceState111()) {
          hash = 37 * hash + 1;
          hash = 53 * hash + getLiveAudienceState111().hashCode();
        } 
        hash = 29 * hash + getUnknownFields().hashCode();
        this.memoizedHashCode = hash;
        return hash;
      }
      
      public static LiveAudienceState_11 parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return (LiveAudienceState_11)PARSER.parseFrom(data);
      }
      
      public static LiveAudienceState_11 parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (LiveAudienceState_11)PARSER.parseFrom(data, extensionRegistry);
      }
      
      public static LiveAudienceState_11 parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (LiveAudienceState_11)PARSER.parseFrom(data);
      }
      
      public static LiveAudienceState_11 parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (LiveAudienceState_11)PARSER.parseFrom(data, extensionRegistry);
      }
      
      public static LiveAudienceState_11 parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (LiveAudienceState_11)PARSER.parseFrom(data);
      }
      
      public static LiveAudienceState_11 parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (LiveAudienceState_11)PARSER.parseFrom(data, extensionRegistry);
      }
      
      public static LiveAudienceState_11 parseFrom(InputStream input) throws IOException {
        return (LiveAudienceState_11)GeneratedMessageV3.parseWithIOException(PARSER, input);
      }
      
      public static LiveAudienceState_11 parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (LiveAudienceState_11)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
      }
      
      public static LiveAudienceState_11 parseDelimitedFrom(InputStream input) throws IOException {
        return (LiveAudienceState_11)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
      }
      
      public static LiveAudienceState_11 parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (LiveAudienceState_11)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
      }
      
      public static LiveAudienceState_11 parseFrom(CodedInputStream input) throws IOException {
        return (LiveAudienceState_11)GeneratedMessageV3.parseWithIOException(PARSER, input);
      }
      
      public static LiveAudienceState_11 parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (LiveAudienceState_11)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
      }
      
      public Builder newBuilderForType() {
        return newBuilder();
      }
      
      public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
      }
      
      public static Builder newBuilder(LiveAudienceState_11 prototype) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
      }
      
      public Builder toBuilder() {
        return (this == DEFAULT_INSTANCE) ? new Builder() : (new Builder()).mergeFrom(this);
      }
      
      protected Builder newBuilderForType(GeneratedMessageV3.BuilderParent parent) {
        Builder builder = new Builder(parent);
        return builder;
      }
      
      public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements LiveAudienceStateOuterClass.LiveAudienceState.LiveAudienceState_11OrBuilder {
        private int bitField0_;
        
        private LiveAudienceStateOuterClass.LiveAudienceState.LiveAudienceState_11.LiveAudienceState_11_1 liveAudienceState111_;
        
        private SingleFieldBuilderV3<LiveAudienceStateOuterClass.LiveAudienceState.LiveAudienceState_11.LiveAudienceState_11_1, LiveAudienceStateOuterClass.LiveAudienceState.LiveAudienceState_11.LiveAudienceState_11_1.Builder, LiveAudienceStateOuterClass.LiveAudienceState.LiveAudienceState_11.LiveAudienceState_11_1OrBuilder> liveAudienceState111Builder_;
        
        public static final Descriptors.Descriptor getDescriptor() {
          return LiveAudienceStateOuterClass.internal_static_LiveAudienceState_LiveAudienceState_11_descriptor;
        }
        
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
          return LiveAudienceStateOuterClass.internal_static_LiveAudienceState_LiveAudienceState_11_fieldAccessorTable.ensureFieldAccessorsInitialized(LiveAudienceStateOuterClass.LiveAudienceState.LiveAudienceState_11.class, Builder.class);
        }
        
        private Builder() {
          maybeForceBuilderInitialization();
        }
        
        private Builder(GeneratedMessageV3.BuilderParent parent) {
          super(parent);
          maybeForceBuilderInitialization();
        }
        
        private void maybeForceBuilderInitialization() {
          if (LiveAudienceStateOuterClass.LiveAudienceState.LiveAudienceState_11.alwaysUseFieldBuilders)
            getLiveAudienceState111FieldBuilder(); 
        }
        
        public Builder clear() {
          super.clear();
          this.bitField0_ = 0;
          this.liveAudienceState111_ = null;
          if (this.liveAudienceState111Builder_ != null) {
            this.liveAudienceState111Builder_.dispose();
            this.liveAudienceState111Builder_ = null;
          } 
          return this;
        }
        
        public Descriptors.Descriptor getDescriptorForType() {
          return LiveAudienceStateOuterClass.internal_static_LiveAudienceState_LiveAudienceState_11_descriptor;
        }
        
        public LiveAudienceStateOuterClass.LiveAudienceState.LiveAudienceState_11 getDefaultInstanceForType() {
          return LiveAudienceStateOuterClass.LiveAudienceState.LiveAudienceState_11.getDefaultInstance();
        }
        
        public LiveAudienceStateOuterClass.LiveAudienceState.LiveAudienceState_11 build() {
          LiveAudienceStateOuterClass.LiveAudienceState.LiveAudienceState_11 result = buildPartial();
          if (!result.isInitialized())
            throw newUninitializedMessageException(result); 
          return result;
        }
        
        public LiveAudienceStateOuterClass.LiveAudienceState.LiveAudienceState_11 buildPartial() {
          LiveAudienceStateOuterClass.LiveAudienceState.LiveAudienceState_11 result = new LiveAudienceStateOuterClass.LiveAudienceState.LiveAudienceState_11(this);
          if (this.bitField0_ != 0)
            buildPartial0(result); 
          onBuilt();
          return result;
        }
        
        private void buildPartial0(LiveAudienceStateOuterClass.LiveAudienceState.LiveAudienceState_11 result) {
          int from_bitField0_ = this.bitField0_;
          int to_bitField0_ = 0;
          if ((from_bitField0_ & 0x1) != 0) {
            result.liveAudienceState111_ = (this.liveAudienceState111Builder_ == null) ? this.liveAudienceState111_ : (LiveAudienceStateOuterClass.LiveAudienceState.LiveAudienceState_11.LiveAudienceState_11_1)this.liveAudienceState111Builder_.build();
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
          if (other instanceof LiveAudienceStateOuterClass.LiveAudienceState.LiveAudienceState_11)
            return mergeFrom((LiveAudienceStateOuterClass.LiveAudienceState.LiveAudienceState_11)other); 
          super.mergeFrom(other);
          return this;
        }
        
        public Builder mergeFrom(LiveAudienceStateOuterClass.LiveAudienceState.LiveAudienceState_11 other) {
          if (other == LiveAudienceStateOuterClass.LiveAudienceState.LiveAudienceState_11.getDefaultInstance())
            return this; 
          if (other.hasLiveAudienceState111())
            mergeLiveAudienceState111(other.getLiveAudienceState111()); 
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
                  input.readMessage((MessageLite.Builder)getLiveAudienceState111FieldBuilder().getBuilder(), extensionRegistry);
                  this.bitField0_ |= 0x1;
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
        
        public boolean hasLiveAudienceState111() {
          return ((this.bitField0_ & 0x1) != 0);
        }
        
        public LiveAudienceStateOuterClass.LiveAudienceState.LiveAudienceState_11.LiveAudienceState_11_1 getLiveAudienceState111() {
          if (this.liveAudienceState111Builder_ == null)
            return (this.liveAudienceState111_ == null) ? LiveAudienceStateOuterClass.LiveAudienceState.LiveAudienceState_11.LiveAudienceState_11_1.getDefaultInstance() : this.liveAudienceState111_; 
          return (LiveAudienceStateOuterClass.LiveAudienceState.LiveAudienceState_11.LiveAudienceState_11_1)this.liveAudienceState111Builder_.getMessage();
        }
        
        public Builder setLiveAudienceState111(LiveAudienceStateOuterClass.LiveAudienceState.LiveAudienceState_11.LiveAudienceState_11_1 value) {
          if (this.liveAudienceState111Builder_ == null) {
            if (value == null)
              throw new NullPointerException(); 
            this.liveAudienceState111_ = value;
          } else {
            this.liveAudienceState111Builder_.setMessage((AbstractMessage)value);
          } 
          this.bitField0_ |= 0x1;
          onChanged();
          return this;
        }
        
        public Builder setLiveAudienceState111(LiveAudienceStateOuterClass.LiveAudienceState.LiveAudienceState_11.LiveAudienceState_11_1.Builder builderForValue) {
          if (this.liveAudienceState111Builder_ == null) {
            this.liveAudienceState111_ = builderForValue.build();
          } else {
            this.liveAudienceState111Builder_.setMessage((AbstractMessage)builderForValue.build());
          } 
          this.bitField0_ |= 0x1;
          onChanged();
          return this;
        }
        
        public Builder mergeLiveAudienceState111(LiveAudienceStateOuterClass.LiveAudienceState.LiveAudienceState_11.LiveAudienceState_11_1 value) {
          if (this.liveAudienceState111Builder_ == null) {
            if ((this.bitField0_ & 0x1) != 0 && this.liveAudienceState111_ != null && this.liveAudienceState111_ != LiveAudienceStateOuterClass.LiveAudienceState.LiveAudienceState_11.LiveAudienceState_11_1.getDefaultInstance()) {
              getLiveAudienceState111Builder().mergeFrom(value);
            } else {
              this.liveAudienceState111_ = value;
            } 
          } else {
            this.liveAudienceState111Builder_.mergeFrom((AbstractMessage)value);
          } 
          if (this.liveAudienceState111_ != null) {
            this.bitField0_ |= 0x1;
            onChanged();
          } 
          return this;
        }
        
        public Builder clearLiveAudienceState111() {
          this.bitField0_ &= 0xFFFFFFFE;
          this.liveAudienceState111_ = null;
          if (this.liveAudienceState111Builder_ != null) {
            this.liveAudienceState111Builder_.dispose();
            this.liveAudienceState111Builder_ = null;
          } 
          onChanged();
          return this;
        }
        
        public LiveAudienceStateOuterClass.LiveAudienceState.LiveAudienceState_11.LiveAudienceState_11_1.Builder getLiveAudienceState111Builder() {
          this.bitField0_ |= 0x1;
          onChanged();
          return (LiveAudienceStateOuterClass.LiveAudienceState.LiveAudienceState_11.LiveAudienceState_11_1.Builder)getLiveAudienceState111FieldBuilder().getBuilder();
        }
        
        public LiveAudienceStateOuterClass.LiveAudienceState.LiveAudienceState_11.LiveAudienceState_11_1OrBuilder getLiveAudienceState111OrBuilder() {
          if (this.liveAudienceState111Builder_ != null)
            return (LiveAudienceStateOuterClass.LiveAudienceState.LiveAudienceState_11.LiveAudienceState_11_1OrBuilder)this.liveAudienceState111Builder_.getMessageOrBuilder(); 
          return (this.liveAudienceState111_ == null) ? LiveAudienceStateOuterClass.LiveAudienceState.LiveAudienceState_11.LiveAudienceState_11_1.getDefaultInstance() : this.liveAudienceState111_;
        }
        
        private SingleFieldBuilderV3<LiveAudienceStateOuterClass.LiveAudienceState.LiveAudienceState_11.LiveAudienceState_11_1, LiveAudienceStateOuterClass.LiveAudienceState.LiveAudienceState_11.LiveAudienceState_11_1.Builder, LiveAudienceStateOuterClass.LiveAudienceState.LiveAudienceState_11.LiveAudienceState_11_1OrBuilder> getLiveAudienceState111FieldBuilder() {
          if (this.liveAudienceState111Builder_ == null) {
            this.liveAudienceState111Builder_ = new SingleFieldBuilderV3((AbstractMessage)getLiveAudienceState111(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
            this.liveAudienceState111_ = null;
          } 
          return this.liveAudienceState111Builder_;
        }
        
        public final Builder setUnknownFields(UnknownFieldSet unknownFields) {
          return (Builder)super.setUnknownFields(unknownFields);
        }
        
        public final Builder mergeUnknownFields(UnknownFieldSet unknownFields) {
          return (Builder)super.mergeUnknownFields(unknownFields);
        }
      }
      
      private static final LiveAudienceState_11 DEFAULT_INSTANCE = new LiveAudienceState_11();
      
      public static LiveAudienceState_11 getDefaultInstance() {
        return DEFAULT_INSTANCE;
      }
      
      private static final Parser<LiveAudienceState_11> PARSER = (Parser<LiveAudienceState_11>)new AbstractParser<LiveAudienceState_11>() {
          public LiveAudienceStateOuterClass.LiveAudienceState.LiveAudienceState_11 parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            LiveAudienceStateOuterClass.LiveAudienceState.LiveAudienceState_11.Builder builder = LiveAudienceStateOuterClass.LiveAudienceState.LiveAudienceState_11.newBuilder();
            try {
              builder.mergeFrom(input, extensionRegistry);
            } catch (InvalidProtocolBufferException e) {
              throw e.setUnfinishedMessage(builder.buildPartial());
            } catch (UninitializedMessageException e) {
              throw e.asInvalidProtocolBufferException().setUnfinishedMessage(builder.buildPartial());
            } catch (IOException e) {
              throw (new InvalidProtocolBufferException(e)).setUnfinishedMessage(builder.buildPartial());
            } 
            return builder.buildPartial();
          }
        };
      
      public static Parser<LiveAudienceState_11> parser() {
        return PARSER;
      }
      
      public Parser<LiveAudienceState_11> getParserForType() {
        return PARSER;
      }
      
      public LiveAudienceState_11 getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
      }
      
      public static interface LiveAudienceState_11_1OrBuilder extends MessageOrBuilder {
        String getBadgeIcon();
        
        ByteString getBadgeIconBytes();
        
        String getBadgeName();
        
        ByteString getBadgeNameBytes();
      }
    }
    
    public boolean getIsFromFansTop() {
      return this.isFromFansTop_;
    }
    
    public boolean getIsKoi() {
      return this.isKoi_;
    }
    
    public int getAssistantTypeValue() {
      return this.assistantType_;
    }
    
    public AssistantType getAssistantType() {
      AssistantType result = AssistantType.forNumber(this.assistantType_);
      return (result == null) ? AssistantType.UNRECOGNIZED : result;
    }
    
    public int getFansGroupIntimacyLevel() {
      return this.fansGroupIntimacyLevel_;
    }
    
    public boolean hasNameplate() {
      return ((this.bitField0_ & 0x1) != 0);
    }
    
    public GzoneNameplateOuterClass.GzoneNameplate getNameplate() {
      return (this.nameplate_ == null) ? GzoneNameplateOuterClass.GzoneNameplate.getDefaultInstance() : this.nameplate_;
    }
    
    public GzoneNameplateOuterClass.GzoneNameplateOrBuilder getNameplateOrBuilder() {
      return (this.nameplate_ == null) ? GzoneNameplateOuterClass.GzoneNameplate.getDefaultInstance() : this.nameplate_;
    }
    
    public boolean hasLiveFansGroupState() {
      return ((this.bitField0_ & 0x2) != 0);
    }
    
    public LiveFansGroupStateOuterClass.LiveFansGroupState getLiveFansGroupState() {
      return (this.liveFansGroupState_ == null) ? LiveFansGroupStateOuterClass.LiveFansGroupState.getDefaultInstance() : this.liveFansGroupState_;
    }
    
    public LiveFansGroupStateOuterClass.LiveFansGroupStateOrBuilder getLiveFansGroupStateOrBuilder() {
      return (this.liveFansGroupState_ == null) ? LiveFansGroupStateOuterClass.LiveFansGroupState.getDefaultInstance() : this.liveFansGroupState_;
    }
    
    public int getWealthGrade() {
      return this.wealthGrade_;
    }
    
    public String getBadgeKey() {
      Object ref = this.badgeKey_;
      if (ref instanceof String)
        return (String)ref; 
      ByteString bs = (ByteString)ref;
      String s = bs.toStringUtf8();
      this.badgeKey_ = s;
      return s;
    }
    
    public ByteString getBadgeKeyBytes() {
      Object ref = this.badgeKey_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.badgeKey_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public List<LiveAudienceState_11> getLiveAudienceState11List() {
      return this.liveAudienceState11_;
    }
    
    public List<? extends LiveAudienceState_11OrBuilder> getLiveAudienceState11OrBuilderList() {
      return (List)this.liveAudienceState11_;
    }
    
    public int getLiveAudienceState11Count() {
      return this.liveAudienceState11_.size();
    }
    
    public LiveAudienceState_11 getLiveAudienceState11(int index) {
      return this.liveAudienceState11_.get(index);
    }
    
    public LiveAudienceState_11OrBuilder getLiveAudienceState11OrBuilder(int index) {
      return this.liveAudienceState11_.get(index);
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
      if (this.isFromFansTop_)
        output.writeBool(1, this.isFromFansTop_); 
      if (this.isKoi_)
        output.writeBool(2, this.isKoi_); 
      if (this.assistantType_ != AssistantType.UNKNOWN_ASSISTANT_TYPE.getNumber())
        output.writeEnum(3, this.assistantType_); 
      if (this.fansGroupIntimacyLevel_ != 0)
        output.writeUInt32(4, this.fansGroupIntimacyLevel_); 
      if ((this.bitField0_ & 0x1) != 0)
        output.writeMessage(5, (MessageLite)getNameplate()); 
      if ((this.bitField0_ & 0x2) != 0)
        output.writeMessage(6, (MessageLite)getLiveFansGroupState()); 
      if (this.wealthGrade_ != 0)
        output.writeUInt32(7, this.wealthGrade_); 
      if (!GeneratedMessageV3.isStringEmpty(this.badgeKey_))
        GeneratedMessageV3.writeString(output, 8, this.badgeKey_); 
      for (int i = 0; i < this.liveAudienceState11_.size(); i++)
        output.writeMessage(11, (MessageLite)this.liveAudienceState11_.get(i)); 
      getUnknownFields().writeTo(output);
    }
    
    public int getSerializedSize() {
      int size = this.memoizedSize;
      if (size != -1)
        return size; 
      size = 0;
      if (this.isFromFansTop_)
        size += 
          CodedOutputStream.computeBoolSize(1, this.isFromFansTop_); 
      if (this.isKoi_)
        size += 
          CodedOutputStream.computeBoolSize(2, this.isKoi_); 
      if (this.assistantType_ != AssistantType.UNKNOWN_ASSISTANT_TYPE.getNumber())
        size += 
          CodedOutputStream.computeEnumSize(3, this.assistantType_); 
      if (this.fansGroupIntimacyLevel_ != 0)
        size += 
          CodedOutputStream.computeUInt32Size(4, this.fansGroupIntimacyLevel_); 
      if ((this.bitField0_ & 0x1) != 0)
        size += 
          CodedOutputStream.computeMessageSize(5, (MessageLite)getNameplate()); 
      if ((this.bitField0_ & 0x2) != 0)
        size += 
          CodedOutputStream.computeMessageSize(6, (MessageLite)getLiveFansGroupState()); 
      if (this.wealthGrade_ != 0)
        size += 
          CodedOutputStream.computeUInt32Size(7, this.wealthGrade_); 
      if (!GeneratedMessageV3.isStringEmpty(this.badgeKey_))
        size += GeneratedMessageV3.computeStringSize(8, this.badgeKey_); 
      for (int i = 0; i < this.liveAudienceState11_.size(); i++)
        size += 
          CodedOutputStream.computeMessageSize(11, (MessageLite)this.liveAudienceState11_.get(i)); 
      size += getUnknownFields().getSerializedSize();
      this.memoizedSize = size;
      return size;
    }
    
    public boolean equals(Object obj) {
      if (obj == this)
        return true; 
      if (!(obj instanceof LiveAudienceState))
        return super.equals(obj); 
      LiveAudienceState other = (LiveAudienceState)obj;
      if (getIsFromFansTop() != other
        .getIsFromFansTop())
        return false; 
      if (getIsKoi() != other
        .getIsKoi())
        return false; 
      if (this.assistantType_ != other.assistantType_)
        return false; 
      if (getFansGroupIntimacyLevel() != other
        .getFansGroupIntimacyLevel())
        return false; 
      if (hasNameplate() != other.hasNameplate())
        return false; 
      if (hasNameplate() && 
        
        !getNameplate().equals(other.getNameplate()))
        return false; 
      if (hasLiveFansGroupState() != other.hasLiveFansGroupState())
        return false; 
      if (hasLiveFansGroupState() && 
        
        !getLiveFansGroupState().equals(other.getLiveFansGroupState()))
        return false; 
      if (getWealthGrade() != other
        .getWealthGrade())
        return false; 
      if (!getBadgeKey().equals(other.getBadgeKey()))
        return false; 
      if (!getLiveAudienceState11List().equals(other.getLiveAudienceState11List()))
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
          getIsFromFansTop());
      hash = 37 * hash + 2;
      hash = 53 * hash + Internal.hashBoolean(
          getIsKoi());
      hash = 37 * hash + 3;
      hash = 53 * hash + this.assistantType_;
      hash = 37 * hash + 4;
      hash = 53 * hash + getFansGroupIntimacyLevel();
      if (hasNameplate()) {
        hash = 37 * hash + 5;
        hash = 53 * hash + getNameplate().hashCode();
      } 
      if (hasLiveFansGroupState()) {
        hash = 37 * hash + 6;
        hash = 53 * hash + getLiveFansGroupState().hashCode();
      } 
      hash = 37 * hash + 7;
      hash = 53 * hash + getWealthGrade();
      hash = 37 * hash + 8;
      hash = 53 * hash + getBadgeKey().hashCode();
      if (getLiveAudienceState11Count() > 0) {
        hash = 37 * hash + 11;
        hash = 53 * hash + getLiveAudienceState11List().hashCode();
      } 
      hash = 29 * hash + getUnknownFields().hashCode();
      this.memoizedHashCode = hash;
      return hash;
    }
    
    public static LiveAudienceState parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (LiveAudienceState)PARSER.parseFrom(data);
    }
    
    public static LiveAudienceState parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (LiveAudienceState)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static LiveAudienceState parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (LiveAudienceState)PARSER.parseFrom(data);
    }
    
    public static LiveAudienceState parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (LiveAudienceState)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static LiveAudienceState parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (LiveAudienceState)PARSER.parseFrom(data);
    }
    
    public static LiveAudienceState parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (LiveAudienceState)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static LiveAudienceState parseFrom(InputStream input) throws IOException {
      return 
        (LiveAudienceState)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static LiveAudienceState parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (LiveAudienceState)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static LiveAudienceState parseDelimitedFrom(InputStream input) throws IOException {
      return 
        (LiveAudienceState)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }
    
    public static LiveAudienceState parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (LiveAudienceState)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static LiveAudienceState parseFrom(CodedInputStream input) throws IOException {
      return 
        (LiveAudienceState)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static LiveAudienceState parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (LiveAudienceState)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public Builder newBuilderForType() {
      return newBuilder();
    }
    
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    
    public static Builder newBuilder(LiveAudienceState prototype) {
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
    
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements LiveAudienceStateOuterClass.LiveAudienceStateOrBuilder {
      private int bitField0_;
      
      private boolean isFromFansTop_;
      
      private boolean isKoi_;
      
      private int assistantType_;
      
      private int fansGroupIntimacyLevel_;
      
      private GzoneNameplateOuterClass.GzoneNameplate nameplate_;
      
      private SingleFieldBuilderV3<GzoneNameplateOuterClass.GzoneNameplate, GzoneNameplateOuterClass.GzoneNameplate.Builder, GzoneNameplateOuterClass.GzoneNameplateOrBuilder> nameplateBuilder_;
      
      private LiveFansGroupStateOuterClass.LiveFansGroupState liveFansGroupState_;
      
      private SingleFieldBuilderV3<LiveFansGroupStateOuterClass.LiveFansGroupState, LiveFansGroupStateOuterClass.LiveFansGroupState.Builder, LiveFansGroupStateOuterClass.LiveFansGroupStateOrBuilder> liveFansGroupStateBuilder_;
      
      private int wealthGrade_;
      
      private Object badgeKey_;
      
      private List<LiveAudienceStateOuterClass.LiveAudienceState.LiveAudienceState_11> liveAudienceState11_;
      
      private RepeatedFieldBuilderV3<LiveAudienceStateOuterClass.LiveAudienceState.LiveAudienceState_11, LiveAudienceStateOuterClass.LiveAudienceState.LiveAudienceState_11.Builder, LiveAudienceStateOuterClass.LiveAudienceState.LiveAudienceState_11OrBuilder> liveAudienceState11Builder_;
      
      public static final Descriptors.Descriptor getDescriptor() {
        return LiveAudienceStateOuterClass.internal_static_LiveAudienceState_descriptor;
      }
      
      protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return LiveAudienceStateOuterClass.internal_static_LiveAudienceState_fieldAccessorTable
          .ensureFieldAccessorsInitialized(LiveAudienceStateOuterClass.LiveAudienceState.class, Builder.class);
      }
      
      private Builder() {
        this.assistantType_ = 0;
        this.badgeKey_ = "";
        this
          .liveAudienceState11_ = Collections.emptyList();
        maybeForceBuilderInitialization();
      }
      
      private Builder(GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        this.assistantType_ = 0;
        this.badgeKey_ = "";
        this.liveAudienceState11_ = Collections.emptyList();
        maybeForceBuilderInitialization();
      }
      
      private void maybeForceBuilderInitialization() {
        if (LiveAudienceStateOuterClass.LiveAudienceState.alwaysUseFieldBuilders) {
          getNameplateFieldBuilder();
          getLiveFansGroupStateFieldBuilder();
          getLiveAudienceState11FieldBuilder();
        } 
      }
      
      public Builder clear() {
        super.clear();
        this.bitField0_ = 0;
        this.isFromFansTop_ = false;
        this.isKoi_ = false;
        this.assistantType_ = 0;
        this.fansGroupIntimacyLevel_ = 0;
        this.nameplate_ = null;
        if (this.nameplateBuilder_ != null) {
          this.nameplateBuilder_.dispose();
          this.nameplateBuilder_ = null;
        } 
        this.liveFansGroupState_ = null;
        if (this.liveFansGroupStateBuilder_ != null) {
          this.liveFansGroupStateBuilder_.dispose();
          this.liveFansGroupStateBuilder_ = null;
        } 
        this.wealthGrade_ = 0;
        this.badgeKey_ = "";
        if (this.liveAudienceState11Builder_ == null) {
          this.liveAudienceState11_ = Collections.emptyList();
        } else {
          this.liveAudienceState11_ = null;
          this.liveAudienceState11Builder_.clear();
        } 
        this.bitField0_ &= 0xFFFFFEFF;
        return this;
      }
      
      public Descriptors.Descriptor getDescriptorForType() {
        return LiveAudienceStateOuterClass.internal_static_LiveAudienceState_descriptor;
      }
      
      public LiveAudienceStateOuterClass.LiveAudienceState getDefaultInstanceForType() {
        return LiveAudienceStateOuterClass.LiveAudienceState.getDefaultInstance();
      }
      
      public LiveAudienceStateOuterClass.LiveAudienceState build() {
        LiveAudienceStateOuterClass.LiveAudienceState result = buildPartial();
        if (!result.isInitialized())
          throw newUninitializedMessageException(result); 
        return result;
      }
      
      public LiveAudienceStateOuterClass.LiveAudienceState buildPartial() {
        LiveAudienceStateOuterClass.LiveAudienceState result = new LiveAudienceStateOuterClass.LiveAudienceState(this);
        buildPartialRepeatedFields(result);
        if (this.bitField0_ != 0)
          buildPartial0(result); 
        onBuilt();
        return result;
      }
      
      private void buildPartialRepeatedFields(LiveAudienceStateOuterClass.LiveAudienceState result) {
        if (this.liveAudienceState11Builder_ == null) {
          if ((this.bitField0_ & 0x100) != 0) {
            this.liveAudienceState11_ = Collections.unmodifiableList(this.liveAudienceState11_);
            this.bitField0_ &= 0xFFFFFEFF;
          } 
          result.liveAudienceState11_ = this.liveAudienceState11_;
        } else {
          result.liveAudienceState11_ = this.liveAudienceState11Builder_.build();
        } 
      }
      
      private void buildPartial0(LiveAudienceStateOuterClass.LiveAudienceState result) {
        int from_bitField0_ = this.bitField0_;
        if ((from_bitField0_ & 0x1) != 0)
          result.isFromFansTop_ = this.isFromFansTop_; 
        if ((from_bitField0_ & 0x2) != 0)
          result.isKoi_ = this.isKoi_; 
        if ((from_bitField0_ & 0x4) != 0)
          result.assistantType_ = this.assistantType_; 
        if ((from_bitField0_ & 0x8) != 0)
          result.fansGroupIntimacyLevel_ = this.fansGroupIntimacyLevel_; 
        int to_bitField0_ = 0;
        if ((from_bitField0_ & 0x10) != 0) {
          result.nameplate_ = (this.nameplateBuilder_ == null) ? this.nameplate_ : (GzoneNameplateOuterClass.GzoneNameplate)this.nameplateBuilder_.build();
          to_bitField0_ |= 0x1;
        } 
        if ((from_bitField0_ & 0x20) != 0) {
          result.liveFansGroupState_ = (this.liveFansGroupStateBuilder_ == null) ? this.liveFansGroupState_ : (LiveFansGroupStateOuterClass.LiveFansGroupState)this.liveFansGroupStateBuilder_.build();
          to_bitField0_ |= 0x2;
        } 
        if ((from_bitField0_ & 0x40) != 0)
          result.wealthGrade_ = this.wealthGrade_; 
        if ((from_bitField0_ & 0x80) != 0)
          result.badgeKey_ = this.badgeKey_; 
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
        if (other instanceof LiveAudienceStateOuterClass.LiveAudienceState)
          return mergeFrom((LiveAudienceStateOuterClass.LiveAudienceState)other); 
        super.mergeFrom(other);
        return this;
      }
      
      public Builder mergeFrom(LiveAudienceStateOuterClass.LiveAudienceState other) {
        if (other == LiveAudienceStateOuterClass.LiveAudienceState.getDefaultInstance())
          return this; 
        if (other.getIsFromFansTop())
          setIsFromFansTop(other.getIsFromFansTop()); 
        if (other.getIsKoi())
          setIsKoi(other.getIsKoi()); 
        if (other.assistantType_ != 0)
          setAssistantTypeValue(other.getAssistantTypeValue()); 
        if (other.getFansGroupIntimacyLevel() != 0)
          setFansGroupIntimacyLevel(other.getFansGroupIntimacyLevel()); 
        if (other.hasNameplate())
          mergeNameplate(other.getNameplate()); 
        if (other.hasLiveFansGroupState())
          mergeLiveFansGroupState(other.getLiveFansGroupState()); 
        if (other.getWealthGrade() != 0)
          setWealthGrade(other.getWealthGrade()); 
        if (!other.getBadgeKey().isEmpty()) {
          this.badgeKey_ = other.badgeKey_;
          this.bitField0_ |= 0x80;
          onChanged();
        } 
        if (this.liveAudienceState11Builder_ == null) {
          if (!other.liveAudienceState11_.isEmpty()) {
            if (this.liveAudienceState11_.isEmpty()) {
              this.liveAudienceState11_ = other.liveAudienceState11_;
              this.bitField0_ &= 0xFFFFFEFF;
            } else {
              ensureLiveAudienceState11IsMutable();
              this.liveAudienceState11_.addAll(other.liveAudienceState11_);
            } 
            onChanged();
          } 
        } else if (!other.liveAudienceState11_.isEmpty()) {
          if (this.liveAudienceState11Builder_.isEmpty()) {
            this.liveAudienceState11Builder_.dispose();
            this.liveAudienceState11Builder_ = null;
            this.liveAudienceState11_ = other.liveAudienceState11_;
            this.bitField0_ &= 0xFFFFFEFF;
            this.liveAudienceState11Builder_ = LiveAudienceStateOuterClass.LiveAudienceState.alwaysUseFieldBuilders ? getLiveAudienceState11FieldBuilder() : null;
          } else {
            this.liveAudienceState11Builder_.addAllMessages(other.liveAudienceState11_);
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
            LiveAudienceStateOuterClass.LiveAudienceState.LiveAudienceState_11 m;
            int tag = input.readTag();
            switch (tag) {
              case 0:
                done = true;
                continue;
              case 8:
                this.isFromFansTop_ = input.readBool();
                this.bitField0_ |= 0x1;
                continue;
              case 16:
                this.isKoi_ = input.readBool();
                this.bitField0_ |= 0x2;
                continue;
              case 24:
                this.assistantType_ = input.readEnum();
                this.bitField0_ |= 0x4;
                continue;
              case 32:
                this.fansGroupIntimacyLevel_ = input.readUInt32();
                this.bitField0_ |= 0x8;
                continue;
              case 42:
                input.readMessage((MessageLite.Builder)getNameplateFieldBuilder().getBuilder(), extensionRegistry);
                this.bitField0_ |= 0x10;
                continue;
              case 50:
                input.readMessage((MessageLite.Builder)getLiveFansGroupStateFieldBuilder().getBuilder(), extensionRegistry);
                this.bitField0_ |= 0x20;
                continue;
              case 56:
                this.wealthGrade_ = input.readUInt32();
                this.bitField0_ |= 0x40;
                continue;
              case 66:
                this.badgeKey_ = input.readStringRequireUtf8();
                this.bitField0_ |= 0x80;
                continue;
              case 90:
                m = (LiveAudienceStateOuterClass.LiveAudienceState.LiveAudienceState_11)input.readMessage(LiveAudienceStateOuterClass.LiveAudienceState.LiveAudienceState_11.parser(), extensionRegistry);
                if (this.liveAudienceState11Builder_ == null) {
                  ensureLiveAudienceState11IsMutable();
                  this.liveAudienceState11_.add(m);
                  continue;
                } 
                this.liveAudienceState11Builder_.addMessage((AbstractMessage)m);
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
      
      public boolean getIsFromFansTop() {
        return this.isFromFansTop_;
      }
      
      public Builder setIsFromFansTop(boolean value) {
        this.isFromFansTop_ = value;
        this.bitField0_ |= 0x1;
        onChanged();
        return this;
      }
      
      public Builder clearIsFromFansTop() {
        this.bitField0_ &= 0xFFFFFFFE;
        this.isFromFansTop_ = false;
        onChanged();
        return this;
      }
      
      public boolean getIsKoi() {
        return this.isKoi_;
      }
      
      public Builder setIsKoi(boolean value) {
        this.isKoi_ = value;
        this.bitField0_ |= 0x2;
        onChanged();
        return this;
      }
      
      public Builder clearIsKoi() {
        this.bitField0_ &= 0xFFFFFFFD;
        this.isKoi_ = false;
        onChanged();
        return this;
      }
      
      public int getAssistantTypeValue() {
        return this.assistantType_;
      }
      
      public Builder setAssistantTypeValue(int value) {
        this.assistantType_ = value;
        this.bitField0_ |= 0x4;
        onChanged();
        return this;
      }
      
      public LiveAudienceStateOuterClass.LiveAudienceState.AssistantType getAssistantType() {
        LiveAudienceStateOuterClass.LiveAudienceState.AssistantType result = LiveAudienceStateOuterClass.LiveAudienceState.AssistantType.forNumber(this.assistantType_);
        return (result == null) ? LiveAudienceStateOuterClass.LiveAudienceState.AssistantType.UNRECOGNIZED : result;
      }
      
      public Builder setAssistantType(LiveAudienceStateOuterClass.LiveAudienceState.AssistantType value) {
        if (value == null)
          throw new NullPointerException(); 
        this.bitField0_ |= 0x4;
        this.assistantType_ = value.getNumber();
        onChanged();
        return this;
      }
      
      public Builder clearAssistantType() {
        this.bitField0_ &= 0xFFFFFFFB;
        this.assistantType_ = 0;
        onChanged();
        return this;
      }
      
      public int getFansGroupIntimacyLevel() {
        return this.fansGroupIntimacyLevel_;
      }
      
      public Builder setFansGroupIntimacyLevel(int value) {
        this.fansGroupIntimacyLevel_ = value;
        this.bitField0_ |= 0x8;
        onChanged();
        return this;
      }
      
      public Builder clearFansGroupIntimacyLevel() {
        this.bitField0_ &= 0xFFFFFFF7;
        this.fansGroupIntimacyLevel_ = 0;
        onChanged();
        return this;
      }
      
      public boolean hasNameplate() {
        return ((this.bitField0_ & 0x10) != 0);
      }
      
      public GzoneNameplateOuterClass.GzoneNameplate getNameplate() {
        if (this.nameplateBuilder_ == null)
          return (this.nameplate_ == null) ? GzoneNameplateOuterClass.GzoneNameplate.getDefaultInstance() : this.nameplate_; 
        return (GzoneNameplateOuterClass.GzoneNameplate)this.nameplateBuilder_.getMessage();
      }
      
      public Builder setNameplate(GzoneNameplateOuterClass.GzoneNameplate value) {
        if (this.nameplateBuilder_ == null) {
          if (value == null)
            throw new NullPointerException(); 
          this.nameplate_ = value;
        } else {
          this.nameplateBuilder_.setMessage((AbstractMessage)value);
        } 
        this.bitField0_ |= 0x10;
        onChanged();
        return this;
      }
      
      public Builder setNameplate(GzoneNameplateOuterClass.GzoneNameplate.Builder builderForValue) {
        if (this.nameplateBuilder_ == null) {
          this.nameplate_ = builderForValue.build();
        } else {
          this.nameplateBuilder_.setMessage((AbstractMessage)builderForValue.build());
        } 
        this.bitField0_ |= 0x10;
        onChanged();
        return this;
      }
      
      public Builder mergeNameplate(GzoneNameplateOuterClass.GzoneNameplate value) {
        if (this.nameplateBuilder_ == null) {
          if ((this.bitField0_ & 0x10) != 0 && this.nameplate_ != null && this.nameplate_ != GzoneNameplateOuterClass.GzoneNameplate.getDefaultInstance()) {
            getNameplateBuilder().mergeFrom(value);
          } else {
            this.nameplate_ = value;
          } 
        } else {
          this.nameplateBuilder_.mergeFrom((AbstractMessage)value);
        } 
        if (this.nameplate_ != null) {
          this.bitField0_ |= 0x10;
          onChanged();
        } 
        return this;
      }
      
      public Builder clearNameplate() {
        this.bitField0_ &= 0xFFFFFFEF;
        this.nameplate_ = null;
        if (this.nameplateBuilder_ != null) {
          this.nameplateBuilder_.dispose();
          this.nameplateBuilder_ = null;
        } 
        onChanged();
        return this;
      }
      
      public GzoneNameplateOuterClass.GzoneNameplate.Builder getNameplateBuilder() {
        this.bitField0_ |= 0x10;
        onChanged();
        return (GzoneNameplateOuterClass.GzoneNameplate.Builder)getNameplateFieldBuilder().getBuilder();
      }
      
      public GzoneNameplateOuterClass.GzoneNameplateOrBuilder getNameplateOrBuilder() {
        if (this.nameplateBuilder_ != null)
          return (GzoneNameplateOuterClass.GzoneNameplateOrBuilder)this.nameplateBuilder_.getMessageOrBuilder(); 
        return (this.nameplate_ == null) ? GzoneNameplateOuterClass.GzoneNameplate.getDefaultInstance() : this.nameplate_;
      }
      
      private SingleFieldBuilderV3<GzoneNameplateOuterClass.GzoneNameplate, GzoneNameplateOuterClass.GzoneNameplate.Builder, GzoneNameplateOuterClass.GzoneNameplateOrBuilder> getNameplateFieldBuilder() {
        if (this.nameplateBuilder_ == null) {
          this.nameplateBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getNameplate(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
          this.nameplate_ = null;
        } 
        return this.nameplateBuilder_;
      }
      
      public boolean hasLiveFansGroupState() {
        return ((this.bitField0_ & 0x20) != 0);
      }
      
      public LiveFansGroupStateOuterClass.LiveFansGroupState getLiveFansGroupState() {
        if (this.liveFansGroupStateBuilder_ == null)
          return (this.liveFansGroupState_ == null) ? LiveFansGroupStateOuterClass.LiveFansGroupState.getDefaultInstance() : this.liveFansGroupState_; 
        return (LiveFansGroupStateOuterClass.LiveFansGroupState)this.liveFansGroupStateBuilder_.getMessage();
      }
      
      public Builder setLiveFansGroupState(LiveFansGroupStateOuterClass.LiveFansGroupState value) {
        if (this.liveFansGroupStateBuilder_ == null) {
          if (value == null)
            throw new NullPointerException(); 
          this.liveFansGroupState_ = value;
        } else {
          this.liveFansGroupStateBuilder_.setMessage((AbstractMessage)value);
        } 
        this.bitField0_ |= 0x20;
        onChanged();
        return this;
      }
      
      public Builder setLiveFansGroupState(LiveFansGroupStateOuterClass.LiveFansGroupState.Builder builderForValue) {
        if (this.liveFansGroupStateBuilder_ == null) {
          this.liveFansGroupState_ = builderForValue.build();
        } else {
          this.liveFansGroupStateBuilder_.setMessage((AbstractMessage)builderForValue.build());
        } 
        this.bitField0_ |= 0x20;
        onChanged();
        return this;
      }
      
      public Builder mergeLiveFansGroupState(LiveFansGroupStateOuterClass.LiveFansGroupState value) {
        if (this.liveFansGroupStateBuilder_ == null) {
          if ((this.bitField0_ & 0x20) != 0 && this.liveFansGroupState_ != null && this.liveFansGroupState_ != LiveFansGroupStateOuterClass.LiveFansGroupState.getDefaultInstance()) {
            getLiveFansGroupStateBuilder().mergeFrom(value);
          } else {
            this.liveFansGroupState_ = value;
          } 
        } else {
          this.liveFansGroupStateBuilder_.mergeFrom((AbstractMessage)value);
        } 
        if (this.liveFansGroupState_ != null) {
          this.bitField0_ |= 0x20;
          onChanged();
        } 
        return this;
      }
      
      public Builder clearLiveFansGroupState() {
        this.bitField0_ &= 0xFFFFFFDF;
        this.liveFansGroupState_ = null;
        if (this.liveFansGroupStateBuilder_ != null) {
          this.liveFansGroupStateBuilder_.dispose();
          this.liveFansGroupStateBuilder_ = null;
        } 
        onChanged();
        return this;
      }
      
      public LiveFansGroupStateOuterClass.LiveFansGroupState.Builder getLiveFansGroupStateBuilder() {
        this.bitField0_ |= 0x20;
        onChanged();
        return (LiveFansGroupStateOuterClass.LiveFansGroupState.Builder)getLiveFansGroupStateFieldBuilder().getBuilder();
      }
      
      public LiveFansGroupStateOuterClass.LiveFansGroupStateOrBuilder getLiveFansGroupStateOrBuilder() {
        if (this.liveFansGroupStateBuilder_ != null)
          return (LiveFansGroupStateOuterClass.LiveFansGroupStateOrBuilder)this.liveFansGroupStateBuilder_.getMessageOrBuilder(); 
        return (this.liveFansGroupState_ == null) ? LiveFansGroupStateOuterClass.LiveFansGroupState.getDefaultInstance() : this.liveFansGroupState_;
      }
      
      private SingleFieldBuilderV3<LiveFansGroupStateOuterClass.LiveFansGroupState, LiveFansGroupStateOuterClass.LiveFansGroupState.Builder, LiveFansGroupStateOuterClass.LiveFansGroupStateOrBuilder> getLiveFansGroupStateFieldBuilder() {
        if (this.liveFansGroupStateBuilder_ == null) {
          this.liveFansGroupStateBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getLiveFansGroupState(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
          this.liveFansGroupState_ = null;
        } 
        return this.liveFansGroupStateBuilder_;
      }
      
      public int getWealthGrade() {
        return this.wealthGrade_;
      }
      
      public Builder setWealthGrade(int value) {
        this.wealthGrade_ = value;
        this.bitField0_ |= 0x40;
        onChanged();
        return this;
      }
      
      public Builder clearWealthGrade() {
        this.bitField0_ &= 0xFFFFFFBF;
        this.wealthGrade_ = 0;
        onChanged();
        return this;
      }
      
      public String getBadgeKey() {
        Object ref = this.badgeKey_;
        if (!(ref instanceof String)) {
          ByteString bs = (ByteString)ref;
          String s = bs.toStringUtf8();
          this.badgeKey_ = s;
          return s;
        } 
        return (String)ref;
      }
      
      public ByteString getBadgeKeyBytes() {
        Object ref = this.badgeKey_;
        if (ref instanceof String) {
          ByteString b = ByteString.copyFromUtf8((String)ref);
          this.badgeKey_ = b;
          return b;
        } 
        return (ByteString)ref;
      }
      
      public Builder setBadgeKey(String value) {
        if (value == null)
          throw new NullPointerException(); 
        this.badgeKey_ = value;
        this.bitField0_ |= 0x80;
        onChanged();
        return this;
      }
      
      public Builder clearBadgeKey() {
        this.badgeKey_ = LiveAudienceStateOuterClass.LiveAudienceState.getDefaultInstance().getBadgeKey();
        this.bitField0_ &= 0xFFFFFF7F;
        onChanged();
        return this;
      }
      
      public Builder setBadgeKeyBytes(ByteString value) {
        if (value == null)
          throw new NullPointerException(); 
        LiveAudienceStateOuterClass.LiveAudienceState.checkByteStringIsUtf8(value);
        this.badgeKey_ = value;
        this.bitField0_ |= 0x80;
        onChanged();
        return this;
      }
      
      private void ensureLiveAudienceState11IsMutable() {
        if ((this.bitField0_ & 0x100) == 0) {
          this.liveAudienceState11_ = new ArrayList<>(this.liveAudienceState11_);
          this.bitField0_ |= 0x100;
        } 
      }
      
      public List<LiveAudienceStateOuterClass.LiveAudienceState.LiveAudienceState_11> getLiveAudienceState11List() {
        if (this.liveAudienceState11Builder_ == null)
          return Collections.unmodifiableList(this.liveAudienceState11_); 
        return this.liveAudienceState11Builder_.getMessageList();
      }
      
      public int getLiveAudienceState11Count() {
        if (this.liveAudienceState11Builder_ == null)
          return this.liveAudienceState11_.size(); 
        return this.liveAudienceState11Builder_.getCount();
      }
      
      public LiveAudienceStateOuterClass.LiveAudienceState.LiveAudienceState_11 getLiveAudienceState11(int index) {
        if (this.liveAudienceState11Builder_ == null)
          return this.liveAudienceState11_.get(index); 
        return (LiveAudienceStateOuterClass.LiveAudienceState.LiveAudienceState_11)this.liveAudienceState11Builder_.getMessage(index);
      }
      
      public Builder setLiveAudienceState11(int index, LiveAudienceStateOuterClass.LiveAudienceState.LiveAudienceState_11 value) {
        if (this.liveAudienceState11Builder_ == null) {
          if (value == null)
            throw new NullPointerException(); 
          ensureLiveAudienceState11IsMutable();
          this.liveAudienceState11_.set(index, value);
          onChanged();
        } else {
          this.liveAudienceState11Builder_.setMessage(index, (AbstractMessage)value);
        } 
        return this;
      }
      
      public Builder setLiveAudienceState11(int index, LiveAudienceStateOuterClass.LiveAudienceState.LiveAudienceState_11.Builder builderForValue) {
        if (this.liveAudienceState11Builder_ == null) {
          ensureLiveAudienceState11IsMutable();
          this.liveAudienceState11_.set(index, builderForValue.build());
          onChanged();
        } else {
          this.liveAudienceState11Builder_.setMessage(index, (AbstractMessage)builderForValue.build());
        } 
        return this;
      }
      
      public Builder addLiveAudienceState11(LiveAudienceStateOuterClass.LiveAudienceState.LiveAudienceState_11 value) {
        if (this.liveAudienceState11Builder_ == null) {
          if (value == null)
            throw new NullPointerException(); 
          ensureLiveAudienceState11IsMutable();
          this.liveAudienceState11_.add(value);
          onChanged();
        } else {
          this.liveAudienceState11Builder_.addMessage((AbstractMessage)value);
        } 
        return this;
      }
      
      public Builder addLiveAudienceState11(int index, LiveAudienceStateOuterClass.LiveAudienceState.LiveAudienceState_11 value) {
        if (this.liveAudienceState11Builder_ == null) {
          if (value == null)
            throw new NullPointerException(); 
          ensureLiveAudienceState11IsMutable();
          this.liveAudienceState11_.add(index, value);
          onChanged();
        } else {
          this.liveAudienceState11Builder_.addMessage(index, (AbstractMessage)value);
        } 
        return this;
      }
      
      public Builder addLiveAudienceState11(LiveAudienceStateOuterClass.LiveAudienceState.LiveAudienceState_11.Builder builderForValue) {
        if (this.liveAudienceState11Builder_ == null) {
          ensureLiveAudienceState11IsMutable();
          this.liveAudienceState11_.add(builderForValue.build());
          onChanged();
        } else {
          this.liveAudienceState11Builder_.addMessage((AbstractMessage)builderForValue.build());
        } 
        return this;
      }
      
      public Builder addLiveAudienceState11(int index, LiveAudienceStateOuterClass.LiveAudienceState.LiveAudienceState_11.Builder builderForValue) {
        if (this.liveAudienceState11Builder_ == null) {
          ensureLiveAudienceState11IsMutable();
          this.liveAudienceState11_.add(index, builderForValue.build());
          onChanged();
        } else {
          this.liveAudienceState11Builder_.addMessage(index, (AbstractMessage)builderForValue.build());
        } 
        return this;
      }
      
      public Builder addAllLiveAudienceState11(Iterable<? extends LiveAudienceStateOuterClass.LiveAudienceState.LiveAudienceState_11> values) {
        if (this.liveAudienceState11Builder_ == null) {
          ensureLiveAudienceState11IsMutable();
          AbstractMessageLite.Builder.addAll(values, this.liveAudienceState11_);
          onChanged();
        } else {
          this.liveAudienceState11Builder_.addAllMessages(values);
        } 
        return this;
      }
      
      public Builder clearLiveAudienceState11() {
        if (this.liveAudienceState11Builder_ == null) {
          this.liveAudienceState11_ = Collections.emptyList();
          this.bitField0_ &= 0xFFFFFEFF;
          onChanged();
        } else {
          this.liveAudienceState11Builder_.clear();
        } 
        return this;
      }
      
      public Builder removeLiveAudienceState11(int index) {
        if (this.liveAudienceState11Builder_ == null) {
          ensureLiveAudienceState11IsMutable();
          this.liveAudienceState11_.remove(index);
          onChanged();
        } else {
          this.liveAudienceState11Builder_.remove(index);
        } 
        return this;
      }
      
      public LiveAudienceStateOuterClass.LiveAudienceState.LiveAudienceState_11.Builder getLiveAudienceState11Builder(int index) {
        return (LiveAudienceStateOuterClass.LiveAudienceState.LiveAudienceState_11.Builder)getLiveAudienceState11FieldBuilder().getBuilder(index);
      }
      
      public LiveAudienceStateOuterClass.LiveAudienceState.LiveAudienceState_11OrBuilder getLiveAudienceState11OrBuilder(int index) {
        if (this.liveAudienceState11Builder_ == null)
          return this.liveAudienceState11_.get(index); 
        return (LiveAudienceStateOuterClass.LiveAudienceState.LiveAudienceState_11OrBuilder)this.liveAudienceState11Builder_.getMessageOrBuilder(index);
      }
      
      public List<? extends LiveAudienceStateOuterClass.LiveAudienceState.LiveAudienceState_11OrBuilder> getLiveAudienceState11OrBuilderList() {
        if (this.liveAudienceState11Builder_ != null)
          return this.liveAudienceState11Builder_.getMessageOrBuilderList(); 
        return Collections.unmodifiableList((List)this.liveAudienceState11_);
      }
      
      public LiveAudienceStateOuterClass.LiveAudienceState.LiveAudienceState_11.Builder addLiveAudienceState11Builder() {
        return (LiveAudienceStateOuterClass.LiveAudienceState.LiveAudienceState_11.Builder)getLiveAudienceState11FieldBuilder().addBuilder(
            (AbstractMessage)LiveAudienceStateOuterClass.LiveAudienceState.LiveAudienceState_11.getDefaultInstance());
      }
      
      public LiveAudienceStateOuterClass.LiveAudienceState.LiveAudienceState_11.Builder addLiveAudienceState11Builder(int index) {
        return (LiveAudienceStateOuterClass.LiveAudienceState.LiveAudienceState_11.Builder)getLiveAudienceState11FieldBuilder().addBuilder(index, 
            (AbstractMessage)LiveAudienceStateOuterClass.LiveAudienceState.LiveAudienceState_11.getDefaultInstance());
      }
      
      public List<LiveAudienceStateOuterClass.LiveAudienceState.LiveAudienceState_11.Builder> getLiveAudienceState11BuilderList() {
        return getLiveAudienceState11FieldBuilder().getBuilderList();
      }
      
      private RepeatedFieldBuilderV3<LiveAudienceStateOuterClass.LiveAudienceState.LiveAudienceState_11, LiveAudienceStateOuterClass.LiveAudienceState.LiveAudienceState_11.Builder, LiveAudienceStateOuterClass.LiveAudienceState.LiveAudienceState_11OrBuilder> getLiveAudienceState11FieldBuilder() {
        if (this.liveAudienceState11Builder_ == null) {
          this
            
            .liveAudienceState11Builder_ = new RepeatedFieldBuilderV3(this.liveAudienceState11_, ((this.bitField0_ & 0x100) != 0), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
          this.liveAudienceState11_ = null;
        } 
        return this.liveAudienceState11Builder_;
      }
      
      public final Builder setUnknownFields(UnknownFieldSet unknownFields) {
        return (Builder)super.setUnknownFields(unknownFields);
      }
      
      public final Builder mergeUnknownFields(UnknownFieldSet unknownFields) {
        return (Builder)super.mergeUnknownFields(unknownFields);
      }
    }
    
    private static final LiveAudienceState DEFAULT_INSTANCE = new LiveAudienceState();
    
    public static LiveAudienceState getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }
    
    private static final Parser<LiveAudienceState> PARSER = (Parser<LiveAudienceState>)new AbstractParser<LiveAudienceState>() {
        public LiveAudienceStateOuterClass.LiveAudienceState parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
          LiveAudienceStateOuterClass.LiveAudienceState.Builder builder = LiveAudienceStateOuterClass.LiveAudienceState.newBuilder();
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
    
    public static Parser<LiveAudienceState> parser() {
      return PARSER;
    }
    
    public Parser<LiveAudienceState> getParserForType() {
      return PARSER;
    }
    
    public LiveAudienceState getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }
    
    public static interface LiveAudienceState_11OrBuilder extends MessageOrBuilder {
      boolean hasLiveAudienceState111();
      
      LiveAudienceStateOuterClass.LiveAudienceState.LiveAudienceState_11.LiveAudienceState_11_1 getLiveAudienceState111();
      
      LiveAudienceStateOuterClass.LiveAudienceState.LiveAudienceState_11.LiveAudienceState_11_1OrBuilder getLiveAudienceState111OrBuilder();
    }
  }
  
  public static Descriptors.FileDescriptor getDescriptor() {
    return descriptor;
  }
  
  static {
    String[] descriptorData = { "\n\027LiveAudienceState.proto\032\024GzoneNameplate.proto\032\030LiveFansGroupState.proto\"Ò\004\n\021LiveAudienceState\022\025\n\risFromFansTop\030\001 \001(\b\022\r\n\005isKoi\030\002 \001(\b\0227\n\rassistantType\030\003 \001(\0162 .LiveAudienceState.AssistantType\022\036\n\026fansGroupIntimacyLevel\030\004 \001(\r\022\"\n\tnameplate\030\005 \001(\0132\017.GzoneNameplate\022/\n\022liveFansGroupState\030\006 \001(\0132\023.LiveFansGroupState\022\023\n\013wealthGrade\030\007 \001(\r\022\020\n\bbadgeKey\030\b \001(\t\022E\n\024liveAudienceState_11\030\013 \003(\0132'.LiveAudienceState.LiveAudienceState_11\032¶\001\n\024LiveAudienceState_11\022^\n\026liveAudienceState_11_1\030\001 \001(\0132>.LiveAudienceState.LiveAudienceState_11.LiveAudienceState_11_1\032>\n\026LiveAudienceState_11_1\022\021\n\tbadgeIcon\030\002 \001(\t\022\021\n\tbadgeName\030\004 \001(\t\"B\n\rAssistantType\022\032\n\026UNKNOWN_ASSISTANT_TYPE\020\000\022\t\n\005SUPER\020\001\022\n\n\006JUNIOR\020\002B<\n:tech.ordinaryroad.live.chat.client.codec.kuaishou.protobufb\006proto3" };
    descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[] { GzoneNameplateOuterClass.getDescriptor(), 
          LiveFansGroupStateOuterClass.getDescriptor() });
    internal_static_LiveAudienceState_descriptor = getDescriptor().getMessageTypes().get(0);
    internal_static_LiveAudienceState_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_LiveAudienceState_descriptor, new String[] { "IsFromFansTop", "IsKoi", "AssistantType", "FansGroupIntimacyLevel", "Nameplate", "LiveFansGroupState", "WealthGrade", "BadgeKey", "LiveAudienceState11" });
    internal_static_LiveAudienceState_LiveAudienceState_11_descriptor = internal_static_LiveAudienceState_descriptor.getNestedTypes().get(0);
    internal_static_LiveAudienceState_LiveAudienceState_11_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_LiveAudienceState_LiveAudienceState_11_descriptor, new String[] { "LiveAudienceState111" });
    internal_static_LiveAudienceState_LiveAudienceState_11_LiveAudienceState_11_1_descriptor = internal_static_LiveAudienceState_LiveAudienceState_11_descriptor.getNestedTypes().get(0);
    internal_static_LiveAudienceState_LiveAudienceState_11_LiveAudienceState_11_1_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_LiveAudienceState_LiveAudienceState_11_LiveAudienceState_11_1_descriptor, new String[] { "BadgeIcon", "BadgeName" });
    GzoneNameplateOuterClass.getDescriptor();
    LiveFansGroupStateOuterClass.getDescriptor();
  }
  
  public static interface LiveAudienceStateOrBuilder extends MessageOrBuilder {
    boolean getIsFromFansTop();
    
    boolean getIsKoi();
    
    int getAssistantTypeValue();
    
    LiveAudienceStateOuterClass.LiveAudienceState.AssistantType getAssistantType();
    
    int getFansGroupIntimacyLevel();
    
    boolean hasNameplate();
    
    GzoneNameplateOuterClass.GzoneNameplate getNameplate();
    
    GzoneNameplateOuterClass.GzoneNameplateOrBuilder getNameplateOrBuilder();
    
    boolean hasLiveFansGroupState();
    
    LiveFansGroupStateOuterClass.LiveFansGroupState getLiveFansGroupState();
    
    LiveFansGroupStateOuterClass.LiveFansGroupStateOrBuilder getLiveFansGroupStateOrBuilder();
    
    int getWealthGrade();
    
    String getBadgeKey();
    
    ByteString getBadgeKeyBytes();
    
    List<LiveAudienceStateOuterClass.LiveAudienceState.LiveAudienceState_11> getLiveAudienceState11List();
    
    LiveAudienceStateOuterClass.LiveAudienceState.LiveAudienceState_11 getLiveAudienceState11(int param1Int);
    
    int getLiveAudienceState11Count();
    
    List<? extends LiveAudienceStateOuterClass.LiveAudienceState.LiveAudienceState_11OrBuilder> getLiveAudienceState11OrBuilderList();
    
    LiveAudienceStateOuterClass.LiveAudienceState.LiveAudienceState_11OrBuilder getLiveAudienceState11OrBuilder(int param1Int);
  }
}
