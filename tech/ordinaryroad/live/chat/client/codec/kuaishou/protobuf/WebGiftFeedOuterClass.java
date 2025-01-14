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
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class WebGiftFeedOuterClass {
  private static final Descriptors.Descriptor internal_static_WebGiftFeed_descriptor;
  
  private static final GeneratedMessageV3.FieldAccessorTable internal_static_WebGiftFeed_fieldAccessorTable;
  
  private static Descriptors.FileDescriptor descriptor;
  
  public static void registerAllExtensions(ExtensionRegistryLite registry) {}
  
  public static void registerAllExtensions(ExtensionRegistry registry) {
    registerAllExtensions((ExtensionRegistryLite)registry);
  }
  
  public static final class WebGiftFeed extends GeneratedMessageV3 implements WebGiftFeedOrBuilder {
    private static final long serialVersionUID = 0L;
    
    private int bitField0_;
    
    public static final int ID_FIELD_NUMBER = 1;
    
    private volatile Object id_;
    
    public static final int USER_FIELD_NUMBER = 2;
    
    private SimpleUserInfoOuterClass.SimpleUserInfo user_;
    
    public static final int TIME_FIELD_NUMBER = 3;
    
    private long time_;
    
    public static final int INTGIFTID_FIELD_NUMBER = 4;
    
    private int intGiftId_;
    
    public static final int SORTRANK_FIELD_NUMBER = 5;
    
    private long sortRank_;
    
    public static final int MERGEKEY_FIELD_NUMBER = 6;
    
    private volatile Object mergeKey_;
    
    public static final int BATCHSIZE_FIELD_NUMBER = 7;
    
    private int batchSize_;
    
    public static final int COMBOCOUNT_FIELD_NUMBER = 8;
    
    private int comboCount_;
    
    public static final int RANK_FIELD_NUMBER = 9;
    
    private int rank_;
    
    public static final int EXPIREDURATION_FIELD_NUMBER = 10;
    
    private long expireDuration_;
    
    public static final int CLIENTTIMESTAMP_FIELD_NUMBER = 11;
    
    private long clientTimestamp_;
    
    public static final int SLOTDISPLAYDURATION_FIELD_NUMBER = 12;
    
    private long slotDisplayDuration_;
    
    public static final int STARLEVEL_FIELD_NUMBER = 13;
    
    private int starLevel_;
    
    public static final int STYLETYPE_FIELD_NUMBER = 14;
    
    private int styleType_;
    
    public static final int LIVEASSISTANTTYPE_FIELD_NUMBER = 15;
    
    private int liveAssistantType_;
    
    public static final int DEVICEHASH_FIELD_NUMBER = 16;
    
    private volatile Object deviceHash_;
    
    public static final int DANMAKUDISPLAY_FIELD_NUMBER = 17;
    
    private boolean danmakuDisplay_;
    
    public static final int LIVEAUDIENCESTATE_FIELD_NUMBER = 18;
    
    private LiveAudienceStateOuterClass.LiveAudienceState liveAudienceState_;
    
    private byte memoizedIsInitialized;
    
    private WebGiftFeed(GeneratedMessageV3.Builder<?> builder) {
      super(builder);
      this.id_ = "";
      this.time_ = 0L;
      this.intGiftId_ = 0;
      this.sortRank_ = 0L;
      this.mergeKey_ = "";
      this.batchSize_ = 0;
      this.comboCount_ = 0;
      this.rank_ = 0;
      this.expireDuration_ = 0L;
      this.clientTimestamp_ = 0L;
      this.slotDisplayDuration_ = 0L;
      this.starLevel_ = 0;
      this.styleType_ = 0;
      this.liveAssistantType_ = 0;
      this.deviceHash_ = "";
      this.danmakuDisplay_ = false;
      this.memoizedIsInitialized = -1;
    }
    
    private WebGiftFeed() {
      this.id_ = "";
      this.time_ = 0L;
      this.intGiftId_ = 0;
      this.sortRank_ = 0L;
      this.mergeKey_ = "";
      this.batchSize_ = 0;
      this.comboCount_ = 0;
      this.rank_ = 0;
      this.expireDuration_ = 0L;
      this.clientTimestamp_ = 0L;
      this.slotDisplayDuration_ = 0L;
      this.starLevel_ = 0;
      this.styleType_ = 0;
      this.liveAssistantType_ = 0;
      this.deviceHash_ = "";
      this.danmakuDisplay_ = false;
      this.memoizedIsInitialized = -1;
      this.id_ = "";
      this.mergeKey_ = "";
      this.styleType_ = 0;
      this.liveAssistantType_ = 0;
      this.deviceHash_ = "";
    }
    
    protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
      return new WebGiftFeed();
    }
    
    public static final Descriptors.Descriptor getDescriptor() {
      return WebGiftFeedOuterClass.internal_static_WebGiftFeed_descriptor;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return WebGiftFeedOuterClass.internal_static_WebGiftFeed_fieldAccessorTable.ensureFieldAccessorsInitialized(WebGiftFeed.class, Builder.class);
    }
    
    public enum StyleType implements ProtocolMessageEnum {
      UNKNOWN_STYLE(0),
      BATCH_STAR_0(1),
      BATCH_STAR_1(2),
      BATCH_STAR_2(3),
      BATCH_STAR_3(4),
      BATCH_STAR_4(5),
      BATCH_STAR_5(6),
      BATCH_STAR_6(7),
      UNRECOGNIZED(-1);
      
      public static final int UNKNOWN_STYLE_VALUE = 0;
      
      public static final int BATCH_STAR_0_VALUE = 1;
      
      public static final int BATCH_STAR_1_VALUE = 2;
      
      public static final int BATCH_STAR_2_VALUE = 3;
      
      public static final int BATCH_STAR_3_VALUE = 4;
      
      public static final int BATCH_STAR_4_VALUE = 5;
      
      public static final int BATCH_STAR_5_VALUE = 6;
      
      public static final int BATCH_STAR_6_VALUE = 7;
      
      private static final Internal.EnumLiteMap<StyleType> internalValueMap = new Internal.EnumLiteMap<StyleType>() {
          public WebGiftFeedOuterClass.WebGiftFeed.StyleType findValueByNumber(int number) {
            return WebGiftFeedOuterClass.WebGiftFeed.StyleType.forNumber(number);
          }
        };
      
      private static final StyleType[] VALUES = values();
      
      private final int value;
      
      public final int getNumber() {
        if (this == UNRECOGNIZED)
          throw new IllegalArgumentException("Can't get the number of an unknown enum value."); 
        return this.value;
      }
      
      public static StyleType forNumber(int value) {
        switch (value) {
          case 0:
            return UNKNOWN_STYLE;
          case 1:
            return BATCH_STAR_0;
          case 2:
            return BATCH_STAR_1;
          case 3:
            return BATCH_STAR_2;
          case 4:
            return BATCH_STAR_3;
          case 5:
            return BATCH_STAR_4;
          case 6:
            return BATCH_STAR_5;
          case 7:
            return BATCH_STAR_6;
        } 
        return null;
      }
      
      public static Internal.EnumLiteMap<StyleType> internalGetValueMap() {
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
        return WebGiftFeedOuterClass.WebGiftFeed.getDescriptor().getEnumTypes().get(0);
      }
      
      StyleType(int value) {
        this.value = value;
      }
    }
    
    public String getId() {
      Object ref = this.id_;
      if (ref instanceof String)
        return (String)ref; 
      ByteString bs = (ByteString)ref;
      String s = bs.toStringUtf8();
      this.id_ = s;
      return s;
    }
    
    public ByteString getIdBytes() {
      Object ref = this.id_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.id_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public boolean hasUser() {
      return ((this.bitField0_ & 0x1) != 0);
    }
    
    public SimpleUserInfoOuterClass.SimpleUserInfo getUser() {
      return (this.user_ == null) ? SimpleUserInfoOuterClass.SimpleUserInfo.getDefaultInstance() : this.user_;
    }
    
    public SimpleUserInfoOuterClass.SimpleUserInfoOrBuilder getUserOrBuilder() {
      return (this.user_ == null) ? SimpleUserInfoOuterClass.SimpleUserInfo.getDefaultInstance() : this.user_;
    }
    
    public long getTime() {
      return this.time_;
    }
    
    public int getIntGiftId() {
      return this.intGiftId_;
    }
    
    public long getSortRank() {
      return this.sortRank_;
    }
    
    public String getMergeKey() {
      Object ref = this.mergeKey_;
      if (ref instanceof String)
        return (String)ref; 
      ByteString bs = (ByteString)ref;
      String s = bs.toStringUtf8();
      this.mergeKey_ = s;
      return s;
    }
    
    public ByteString getMergeKeyBytes() {
      Object ref = this.mergeKey_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.mergeKey_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public int getBatchSize() {
      return this.batchSize_;
    }
    
    public int getComboCount() {
      return this.comboCount_;
    }
    
    public int getRank() {
      return this.rank_;
    }
    
    public long getExpireDuration() {
      return this.expireDuration_;
    }
    
    public long getClientTimestamp() {
      return this.clientTimestamp_;
    }
    
    public long getSlotDisplayDuration() {
      return this.slotDisplayDuration_;
    }
    
    public int getStarLevel() {
      return this.starLevel_;
    }
    
    public int getStyleTypeValue() {
      return this.styleType_;
    }
    
    public StyleType getStyleType() {
      StyleType result = StyleType.forNumber(this.styleType_);
      return (result == null) ? StyleType.UNRECOGNIZED : result;
    }
    
    public int getLiveAssistantTypeValue() {
      return this.liveAssistantType_;
    }
    
    public WebLiveAssistantTypeOuterClass.WebLiveAssistantType getLiveAssistantType() {
      WebLiveAssistantTypeOuterClass.WebLiveAssistantType result = WebLiveAssistantTypeOuterClass.WebLiveAssistantType.forNumber(this.liveAssistantType_);
      return (result == null) ? WebLiveAssistantTypeOuterClass.WebLiveAssistantType.UNRECOGNIZED : result;
    }
    
    public String getDeviceHash() {
      Object ref = this.deviceHash_;
      if (ref instanceof String)
        return (String)ref; 
      ByteString bs = (ByteString)ref;
      String s = bs.toStringUtf8();
      this.deviceHash_ = s;
      return s;
    }
    
    public ByteString getDeviceHashBytes() {
      Object ref = this.deviceHash_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.deviceHash_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public boolean getDanmakuDisplay() {
      return this.danmakuDisplay_;
    }
    
    public boolean hasLiveAudienceState() {
      return ((this.bitField0_ & 0x2) != 0);
    }
    
    public LiveAudienceStateOuterClass.LiveAudienceState getLiveAudienceState() {
      return (this.liveAudienceState_ == null) ? LiveAudienceStateOuterClass.LiveAudienceState.getDefaultInstance() : this.liveAudienceState_;
    }
    
    public LiveAudienceStateOuterClass.LiveAudienceStateOrBuilder getLiveAudienceStateOrBuilder() {
      return (this.liveAudienceState_ == null) ? LiveAudienceStateOuterClass.LiveAudienceState.getDefaultInstance() : this.liveAudienceState_;
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
      if (!GeneratedMessageV3.isStringEmpty(this.id_))
        GeneratedMessageV3.writeString(output, 1, this.id_); 
      if ((this.bitField0_ & 0x1) != 0)
        output.writeMessage(2, (MessageLite)getUser()); 
      if (this.time_ != 0L)
        output.writeUInt64(3, this.time_); 
      if (this.intGiftId_ != 0)
        output.writeUInt32(4, this.intGiftId_); 
      if (this.sortRank_ != 0L)
        output.writeUInt64(5, this.sortRank_); 
      if (!GeneratedMessageV3.isStringEmpty(this.mergeKey_))
        GeneratedMessageV3.writeString(output, 6, this.mergeKey_); 
      if (this.batchSize_ != 0)
        output.writeUInt32(7, this.batchSize_); 
      if (this.comboCount_ != 0)
        output.writeUInt32(8, this.comboCount_); 
      if (this.rank_ != 0)
        output.writeUInt32(9, this.rank_); 
      if (this.expireDuration_ != 0L)
        output.writeUInt64(10, this.expireDuration_); 
      if (this.clientTimestamp_ != 0L)
        output.writeUInt64(11, this.clientTimestamp_); 
      if (this.slotDisplayDuration_ != 0L)
        output.writeUInt64(12, this.slotDisplayDuration_); 
      if (this.starLevel_ != 0)
        output.writeUInt32(13, this.starLevel_); 
      if (this.styleType_ != StyleType.UNKNOWN_STYLE.getNumber())
        output.writeEnum(14, this.styleType_); 
      if (this.liveAssistantType_ != WebLiveAssistantTypeOuterClass.WebLiveAssistantType.UNKNOWN_ASSISTANT_TYPE.getNumber())
        output.writeEnum(15, this.liveAssistantType_); 
      if (!GeneratedMessageV3.isStringEmpty(this.deviceHash_))
        GeneratedMessageV3.writeString(output, 16, this.deviceHash_); 
      if (this.danmakuDisplay_)
        output.writeBool(17, this.danmakuDisplay_); 
      if ((this.bitField0_ & 0x2) != 0)
        output.writeMessage(18, (MessageLite)getLiveAudienceState()); 
      getUnknownFields().writeTo(output);
    }
    
    public int getSerializedSize() {
      int size = this.memoizedSize;
      if (size != -1)
        return size; 
      size = 0;
      if (!GeneratedMessageV3.isStringEmpty(this.id_))
        size += GeneratedMessageV3.computeStringSize(1, this.id_); 
      if ((this.bitField0_ & 0x1) != 0)
        size += 
          CodedOutputStream.computeMessageSize(2, (MessageLite)getUser()); 
      if (this.time_ != 0L)
        size += 
          CodedOutputStream.computeUInt64Size(3, this.time_); 
      if (this.intGiftId_ != 0)
        size += 
          CodedOutputStream.computeUInt32Size(4, this.intGiftId_); 
      if (this.sortRank_ != 0L)
        size += 
          CodedOutputStream.computeUInt64Size(5, this.sortRank_); 
      if (!GeneratedMessageV3.isStringEmpty(this.mergeKey_))
        size += GeneratedMessageV3.computeStringSize(6, this.mergeKey_); 
      if (this.batchSize_ != 0)
        size += 
          CodedOutputStream.computeUInt32Size(7, this.batchSize_); 
      if (this.comboCount_ != 0)
        size += 
          CodedOutputStream.computeUInt32Size(8, this.comboCount_); 
      if (this.rank_ != 0)
        size += 
          CodedOutputStream.computeUInt32Size(9, this.rank_); 
      if (this.expireDuration_ != 0L)
        size += 
          CodedOutputStream.computeUInt64Size(10, this.expireDuration_); 
      if (this.clientTimestamp_ != 0L)
        size += 
          CodedOutputStream.computeUInt64Size(11, this.clientTimestamp_); 
      if (this.slotDisplayDuration_ != 0L)
        size += 
          CodedOutputStream.computeUInt64Size(12, this.slotDisplayDuration_); 
      if (this.starLevel_ != 0)
        size += 
          CodedOutputStream.computeUInt32Size(13, this.starLevel_); 
      if (this.styleType_ != StyleType.UNKNOWN_STYLE.getNumber())
        size += 
          CodedOutputStream.computeEnumSize(14, this.styleType_); 
      if (this.liveAssistantType_ != WebLiveAssistantTypeOuterClass.WebLiveAssistantType.UNKNOWN_ASSISTANT_TYPE.getNumber())
        size += 
          CodedOutputStream.computeEnumSize(15, this.liveAssistantType_); 
      if (!GeneratedMessageV3.isStringEmpty(this.deviceHash_))
        size += GeneratedMessageV3.computeStringSize(16, this.deviceHash_); 
      if (this.danmakuDisplay_)
        size += 
          CodedOutputStream.computeBoolSize(17, this.danmakuDisplay_); 
      if ((this.bitField0_ & 0x2) != 0)
        size += 
          CodedOutputStream.computeMessageSize(18, (MessageLite)getLiveAudienceState()); 
      size += getUnknownFields().getSerializedSize();
      this.memoizedSize = size;
      return size;
    }
    
    public boolean equals(Object obj) {
      if (obj == this)
        return true; 
      if (!(obj instanceof WebGiftFeed))
        return super.equals(obj); 
      WebGiftFeed other = (WebGiftFeed)obj;
      if (!getId().equals(other.getId()))
        return false; 
      if (hasUser() != other.hasUser())
        return false; 
      if (hasUser() && 
        
        !getUser().equals(other.getUser()))
        return false; 
      if (getTime() != other
        .getTime())
        return false; 
      if (getIntGiftId() != other
        .getIntGiftId())
        return false; 
      if (getSortRank() != other
        .getSortRank())
        return false; 
      if (!getMergeKey().equals(other.getMergeKey()))
        return false; 
      if (getBatchSize() != other
        .getBatchSize())
        return false; 
      if (getComboCount() != other
        .getComboCount())
        return false; 
      if (getRank() != other
        .getRank())
        return false; 
      if (getExpireDuration() != other
        .getExpireDuration())
        return false; 
      if (getClientTimestamp() != other
        .getClientTimestamp())
        return false; 
      if (getSlotDisplayDuration() != other
        .getSlotDisplayDuration())
        return false; 
      if (getStarLevel() != other
        .getStarLevel())
        return false; 
      if (this.styleType_ != other.styleType_)
        return false; 
      if (this.liveAssistantType_ != other.liveAssistantType_)
        return false; 
      if (!getDeviceHash().equals(other.getDeviceHash()))
        return false; 
      if (getDanmakuDisplay() != other
        .getDanmakuDisplay())
        return false; 
      if (hasLiveAudienceState() != other.hasLiveAudienceState())
        return false; 
      if (hasLiveAudienceState() && 
        
        !getLiveAudienceState().equals(other.getLiveAudienceState()))
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
      hash = 53 * hash + getId().hashCode();
      if (hasUser()) {
        hash = 37 * hash + 2;
        hash = 53 * hash + getUser().hashCode();
      } 
      hash = 37 * hash + 3;
      hash = 53 * hash + Internal.hashLong(
          getTime());
      hash = 37 * hash + 4;
      hash = 53 * hash + getIntGiftId();
      hash = 37 * hash + 5;
      hash = 53 * hash + Internal.hashLong(
          getSortRank());
      hash = 37 * hash + 6;
      hash = 53 * hash + getMergeKey().hashCode();
      hash = 37 * hash + 7;
      hash = 53 * hash + getBatchSize();
      hash = 37 * hash + 8;
      hash = 53 * hash + getComboCount();
      hash = 37 * hash + 9;
      hash = 53 * hash + getRank();
      hash = 37 * hash + 10;
      hash = 53 * hash + Internal.hashLong(
          getExpireDuration());
      hash = 37 * hash + 11;
      hash = 53 * hash + Internal.hashLong(
          getClientTimestamp());
      hash = 37 * hash + 12;
      hash = 53 * hash + Internal.hashLong(
          getSlotDisplayDuration());
      hash = 37 * hash + 13;
      hash = 53 * hash + getStarLevel();
      hash = 37 * hash + 14;
      hash = 53 * hash + this.styleType_;
      hash = 37 * hash + 15;
      hash = 53 * hash + this.liveAssistantType_;
      hash = 37 * hash + 16;
      hash = 53 * hash + getDeviceHash().hashCode();
      hash = 37 * hash + 17;
      hash = 53 * hash + Internal.hashBoolean(
          getDanmakuDisplay());
      if (hasLiveAudienceState()) {
        hash = 37 * hash + 18;
        hash = 53 * hash + getLiveAudienceState().hashCode();
      } 
      hash = 29 * hash + getUnknownFields().hashCode();
      this.memoizedHashCode = hash;
      return hash;
    }
    
    public static WebGiftFeed parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (WebGiftFeed)PARSER.parseFrom(data);
    }
    
    public static WebGiftFeed parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (WebGiftFeed)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static WebGiftFeed parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (WebGiftFeed)PARSER.parseFrom(data);
    }
    
    public static WebGiftFeed parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (WebGiftFeed)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static WebGiftFeed parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (WebGiftFeed)PARSER.parseFrom(data);
    }
    
    public static WebGiftFeed parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (WebGiftFeed)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static WebGiftFeed parseFrom(InputStream input) throws IOException {
      return 
        (WebGiftFeed)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static WebGiftFeed parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (WebGiftFeed)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static WebGiftFeed parseDelimitedFrom(InputStream input) throws IOException {
      return 
        (WebGiftFeed)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }
    
    public static WebGiftFeed parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (WebGiftFeed)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static WebGiftFeed parseFrom(CodedInputStream input) throws IOException {
      return 
        (WebGiftFeed)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static WebGiftFeed parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (WebGiftFeed)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public Builder newBuilderForType() {
      return newBuilder();
    }
    
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    
    public static Builder newBuilder(WebGiftFeed prototype) {
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
    
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements WebGiftFeedOuterClass.WebGiftFeedOrBuilder {
      private int bitField0_;
      
      private Object id_;
      
      private SimpleUserInfoOuterClass.SimpleUserInfo user_;
      
      private SingleFieldBuilderV3<SimpleUserInfoOuterClass.SimpleUserInfo, SimpleUserInfoOuterClass.SimpleUserInfo.Builder, SimpleUserInfoOuterClass.SimpleUserInfoOrBuilder> userBuilder_;
      
      private long time_;
      
      private int intGiftId_;
      
      private long sortRank_;
      
      private Object mergeKey_;
      
      private int batchSize_;
      
      private int comboCount_;
      
      private int rank_;
      
      private long expireDuration_;
      
      private long clientTimestamp_;
      
      private long slotDisplayDuration_;
      
      private int starLevel_;
      
      private int styleType_;
      
      private int liveAssistantType_;
      
      private Object deviceHash_;
      
      private boolean danmakuDisplay_;
      
      private LiveAudienceStateOuterClass.LiveAudienceState liveAudienceState_;
      
      private SingleFieldBuilderV3<LiveAudienceStateOuterClass.LiveAudienceState, LiveAudienceStateOuterClass.LiveAudienceState.Builder, LiveAudienceStateOuterClass.LiveAudienceStateOrBuilder> liveAudienceStateBuilder_;
      
      public static final Descriptors.Descriptor getDescriptor() {
        return WebGiftFeedOuterClass.internal_static_WebGiftFeed_descriptor;
      }
      
      protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return WebGiftFeedOuterClass.internal_static_WebGiftFeed_fieldAccessorTable
          .ensureFieldAccessorsInitialized(WebGiftFeedOuterClass.WebGiftFeed.class, Builder.class);
      }
      
      private Builder() {
        this.id_ = "";
        this.mergeKey_ = "";
        this.styleType_ = 0;
        this.liveAssistantType_ = 0;
        this.deviceHash_ = "";
        maybeForceBuilderInitialization();
      }
      
      private Builder(GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        this.id_ = "";
        this.mergeKey_ = "";
        this.styleType_ = 0;
        this.liveAssistantType_ = 0;
        this.deviceHash_ = "";
        maybeForceBuilderInitialization();
      }
      
      private void maybeForceBuilderInitialization() {
        if (WebGiftFeedOuterClass.WebGiftFeed.alwaysUseFieldBuilders) {
          getUserFieldBuilder();
          getLiveAudienceStateFieldBuilder();
        } 
      }
      
      public Builder clear() {
        super.clear();
        this.bitField0_ = 0;
        this.id_ = "";
        this.user_ = null;
        if (this.userBuilder_ != null) {
          this.userBuilder_.dispose();
          this.userBuilder_ = null;
        } 
        this.time_ = 0L;
        this.intGiftId_ = 0;
        this.sortRank_ = 0L;
        this.mergeKey_ = "";
        this.batchSize_ = 0;
        this.comboCount_ = 0;
        this.rank_ = 0;
        this.expireDuration_ = 0L;
        this.clientTimestamp_ = 0L;
        this.slotDisplayDuration_ = 0L;
        this.starLevel_ = 0;
        this.styleType_ = 0;
        this.liveAssistantType_ = 0;
        this.deviceHash_ = "";
        this.danmakuDisplay_ = false;
        this.liveAudienceState_ = null;
        if (this.liveAudienceStateBuilder_ != null) {
          this.liveAudienceStateBuilder_.dispose();
          this.liveAudienceStateBuilder_ = null;
        } 
        return this;
      }
      
      public Descriptors.Descriptor getDescriptorForType() {
        return WebGiftFeedOuterClass.internal_static_WebGiftFeed_descriptor;
      }
      
      public WebGiftFeedOuterClass.WebGiftFeed getDefaultInstanceForType() {
        return WebGiftFeedOuterClass.WebGiftFeed.getDefaultInstance();
      }
      
      public WebGiftFeedOuterClass.WebGiftFeed build() {
        WebGiftFeedOuterClass.WebGiftFeed result = buildPartial();
        if (!result.isInitialized())
          throw newUninitializedMessageException(result); 
        return result;
      }
      
      public WebGiftFeedOuterClass.WebGiftFeed buildPartial() {
        WebGiftFeedOuterClass.WebGiftFeed result = new WebGiftFeedOuterClass.WebGiftFeed(this);
        if (this.bitField0_ != 0)
          buildPartial0(result); 
        onBuilt();
        return result;
      }
      
      private void buildPartial0(WebGiftFeedOuterClass.WebGiftFeed result) {
        int from_bitField0_ = this.bitField0_;
        if ((from_bitField0_ & 0x1) != 0)
          result.id_ = this.id_; 
        int to_bitField0_ = 0;
        if ((from_bitField0_ & 0x2) != 0) {
          result.user_ = (this.userBuilder_ == null) ? this.user_ : (SimpleUserInfoOuterClass.SimpleUserInfo)this.userBuilder_.build();
          to_bitField0_ |= 0x1;
        } 
        if ((from_bitField0_ & 0x4) != 0)
          result.time_ = this.time_; 
        if ((from_bitField0_ & 0x8) != 0)
          result.intGiftId_ = this.intGiftId_; 
        if ((from_bitField0_ & 0x10) != 0)
          result.sortRank_ = this.sortRank_; 
        if ((from_bitField0_ & 0x20) != 0)
          result.mergeKey_ = this.mergeKey_; 
        if ((from_bitField0_ & 0x40) != 0)
          result.batchSize_ = this.batchSize_; 
        if ((from_bitField0_ & 0x80) != 0)
          result.comboCount_ = this.comboCount_; 
        if ((from_bitField0_ & 0x100) != 0)
          result.rank_ = this.rank_; 
        if ((from_bitField0_ & 0x200) != 0)
          result.expireDuration_ = this.expireDuration_; 
        if ((from_bitField0_ & 0x400) != 0)
          result.clientTimestamp_ = this.clientTimestamp_; 
        if ((from_bitField0_ & 0x800) != 0)
          result.slotDisplayDuration_ = this.slotDisplayDuration_; 
        if ((from_bitField0_ & 0x1000) != 0)
          result.starLevel_ = this.starLevel_; 
        if ((from_bitField0_ & 0x2000) != 0)
          result.styleType_ = this.styleType_; 
        if ((from_bitField0_ & 0x4000) != 0)
          result.liveAssistantType_ = this.liveAssistantType_; 
        if ((from_bitField0_ & 0x8000) != 0)
          result.deviceHash_ = this.deviceHash_; 
        if ((from_bitField0_ & 0x10000) != 0)
          result.danmakuDisplay_ = this.danmakuDisplay_; 
        if ((from_bitField0_ & 0x20000) != 0) {
          result.liveAudienceState_ = (this.liveAudienceStateBuilder_ == null) ? this.liveAudienceState_ : (LiveAudienceStateOuterClass.LiveAudienceState)this.liveAudienceStateBuilder_.build();
          to_bitField0_ |= 0x2;
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
        if (other instanceof WebGiftFeedOuterClass.WebGiftFeed)
          return mergeFrom((WebGiftFeedOuterClass.WebGiftFeed)other); 
        super.mergeFrom(other);
        return this;
      }
      
      public Builder mergeFrom(WebGiftFeedOuterClass.WebGiftFeed other) {
        if (other == WebGiftFeedOuterClass.WebGiftFeed.getDefaultInstance())
          return this; 
        if (!other.getId().isEmpty()) {
          this.id_ = other.id_;
          this.bitField0_ |= 0x1;
          onChanged();
        } 
        if (other.hasUser())
          mergeUser(other.getUser()); 
        if (other.getTime() != 0L)
          setTime(other.getTime()); 
        if (other.getIntGiftId() != 0)
          setIntGiftId(other.getIntGiftId()); 
        if (other.getSortRank() != 0L)
          setSortRank(other.getSortRank()); 
        if (!other.getMergeKey().isEmpty()) {
          this.mergeKey_ = other.mergeKey_;
          this.bitField0_ |= 0x20;
          onChanged();
        } 
        if (other.getBatchSize() != 0)
          setBatchSize(other.getBatchSize()); 
        if (other.getComboCount() != 0)
          setComboCount(other.getComboCount()); 
        if (other.getRank() != 0)
          setRank(other.getRank()); 
        if (other.getExpireDuration() != 0L)
          setExpireDuration(other.getExpireDuration()); 
        if (other.getClientTimestamp() != 0L)
          setClientTimestamp(other.getClientTimestamp()); 
        if (other.getSlotDisplayDuration() != 0L)
          setSlotDisplayDuration(other.getSlotDisplayDuration()); 
        if (other.getStarLevel() != 0)
          setStarLevel(other.getStarLevel()); 
        if (other.styleType_ != 0)
          setStyleTypeValue(other.getStyleTypeValue()); 
        if (other.liveAssistantType_ != 0)
          setLiveAssistantTypeValue(other.getLiveAssistantTypeValue()); 
        if (!other.getDeviceHash().isEmpty()) {
          this.deviceHash_ = other.deviceHash_;
          this.bitField0_ |= 0x8000;
          onChanged();
        } 
        if (other.getDanmakuDisplay())
          setDanmakuDisplay(other.getDanmakuDisplay()); 
        if (other.hasLiveAudienceState())
          mergeLiveAudienceState(other.getLiveAudienceState()); 
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
                this.id_ = input.readStringRequireUtf8();
                this.bitField0_ |= 0x1;
                continue;
              case 18:
                input.readMessage((MessageLite.Builder)getUserFieldBuilder().getBuilder(), extensionRegistry);
                this.bitField0_ |= 0x2;
                continue;
              case 24:
                this.time_ = input.readUInt64();
                this.bitField0_ |= 0x4;
                continue;
              case 32:
                this.intGiftId_ = input.readUInt32();
                this.bitField0_ |= 0x8;
                continue;
              case 40:
                this.sortRank_ = input.readUInt64();
                this.bitField0_ |= 0x10;
                continue;
              case 50:
                this.mergeKey_ = input.readStringRequireUtf8();
                this.bitField0_ |= 0x20;
                continue;
              case 56:
                this.batchSize_ = input.readUInt32();
                this.bitField0_ |= 0x40;
                continue;
              case 64:
                this.comboCount_ = input.readUInt32();
                this.bitField0_ |= 0x80;
                continue;
              case 72:
                this.rank_ = input.readUInt32();
                this.bitField0_ |= 0x100;
                continue;
              case 80:
                this.expireDuration_ = input.readUInt64();
                this.bitField0_ |= 0x200;
                continue;
              case 88:
                this.clientTimestamp_ = input.readUInt64();
                this.bitField0_ |= 0x400;
                continue;
              case 96:
                this.slotDisplayDuration_ = input.readUInt64();
                this.bitField0_ |= 0x800;
                continue;
              case 104:
                this.starLevel_ = input.readUInt32();
                this.bitField0_ |= 0x1000;
                continue;
              case 112:
                this.styleType_ = input.readEnum();
                this.bitField0_ |= 0x2000;
                continue;
              case 120:
                this.liveAssistantType_ = input.readEnum();
                this.bitField0_ |= 0x4000;
                continue;
              case 130:
                this.deviceHash_ = input.readStringRequireUtf8();
                this.bitField0_ |= 0x8000;
                continue;
              case 136:
                this.danmakuDisplay_ = input.readBool();
                this.bitField0_ |= 0x10000;
                continue;
              case 146:
                input.readMessage((MessageLite.Builder)getLiveAudienceStateFieldBuilder().getBuilder(), extensionRegistry);
                this.bitField0_ |= 0x20000;
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
      
      public String getId() {
        Object ref = this.id_;
        if (!(ref instanceof String)) {
          ByteString bs = (ByteString)ref;
          String s = bs.toStringUtf8();
          this.id_ = s;
          return s;
        } 
        return (String)ref;
      }
      
      public ByteString getIdBytes() {
        Object ref = this.id_;
        if (ref instanceof String) {
          ByteString b = ByteString.copyFromUtf8((String)ref);
          this.id_ = b;
          return b;
        } 
        return (ByteString)ref;
      }
      
      public Builder setId(String value) {
        if (value == null)
          throw new NullPointerException(); 
        this.id_ = value;
        this.bitField0_ |= 0x1;
        onChanged();
        return this;
      }
      
      public Builder clearId() {
        this.id_ = WebGiftFeedOuterClass.WebGiftFeed.getDefaultInstance().getId();
        this.bitField0_ &= 0xFFFFFFFE;
        onChanged();
        return this;
      }
      
      public Builder setIdBytes(ByteString value) {
        if (value == null)
          throw new NullPointerException(); 
        WebGiftFeedOuterClass.WebGiftFeed.checkByteStringIsUtf8(value);
        this.id_ = value;
        this.bitField0_ |= 0x1;
        onChanged();
        return this;
      }
      
      public boolean hasUser() {
        return ((this.bitField0_ & 0x2) != 0);
      }
      
      public SimpleUserInfoOuterClass.SimpleUserInfo getUser() {
        if (this.userBuilder_ == null)
          return (this.user_ == null) ? SimpleUserInfoOuterClass.SimpleUserInfo.getDefaultInstance() : this.user_; 
        return (SimpleUserInfoOuterClass.SimpleUserInfo)this.userBuilder_.getMessage();
      }
      
      public Builder setUser(SimpleUserInfoOuterClass.SimpleUserInfo value) {
        if (this.userBuilder_ == null) {
          if (value == null)
            throw new NullPointerException(); 
          this.user_ = value;
        } else {
          this.userBuilder_.setMessage((AbstractMessage)value);
        } 
        this.bitField0_ |= 0x2;
        onChanged();
        return this;
      }
      
      public Builder setUser(SimpleUserInfoOuterClass.SimpleUserInfo.Builder builderForValue) {
        if (this.userBuilder_ == null) {
          this.user_ = builderForValue.build();
        } else {
          this.userBuilder_.setMessage((AbstractMessage)builderForValue.build());
        } 
        this.bitField0_ |= 0x2;
        onChanged();
        return this;
      }
      
      public Builder mergeUser(SimpleUserInfoOuterClass.SimpleUserInfo value) {
        if (this.userBuilder_ == null) {
          if ((this.bitField0_ & 0x2) != 0 && this.user_ != null && this.user_ != SimpleUserInfoOuterClass.SimpleUserInfo.getDefaultInstance()) {
            getUserBuilder().mergeFrom(value);
          } else {
            this.user_ = value;
          } 
        } else {
          this.userBuilder_.mergeFrom((AbstractMessage)value);
        } 
        if (this.user_ != null) {
          this.bitField0_ |= 0x2;
          onChanged();
        } 
        return this;
      }
      
      public Builder clearUser() {
        this.bitField0_ &= 0xFFFFFFFD;
        this.user_ = null;
        if (this.userBuilder_ != null) {
          this.userBuilder_.dispose();
          this.userBuilder_ = null;
        } 
        onChanged();
        return this;
      }
      
      public SimpleUserInfoOuterClass.SimpleUserInfo.Builder getUserBuilder() {
        this.bitField0_ |= 0x2;
        onChanged();
        return (SimpleUserInfoOuterClass.SimpleUserInfo.Builder)getUserFieldBuilder().getBuilder();
      }
      
      public SimpleUserInfoOuterClass.SimpleUserInfoOrBuilder getUserOrBuilder() {
        if (this.userBuilder_ != null)
          return (SimpleUserInfoOuterClass.SimpleUserInfoOrBuilder)this.userBuilder_.getMessageOrBuilder(); 
        return (this.user_ == null) ? SimpleUserInfoOuterClass.SimpleUserInfo.getDefaultInstance() : this.user_;
      }
      
      private SingleFieldBuilderV3<SimpleUserInfoOuterClass.SimpleUserInfo, SimpleUserInfoOuterClass.SimpleUserInfo.Builder, SimpleUserInfoOuterClass.SimpleUserInfoOrBuilder> getUserFieldBuilder() {
        if (this.userBuilder_ == null) {
          this.userBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getUser(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
          this.user_ = null;
        } 
        return this.userBuilder_;
      }
      
      public long getTime() {
        return this.time_;
      }
      
      public Builder setTime(long value) {
        this.time_ = value;
        this.bitField0_ |= 0x4;
        onChanged();
        return this;
      }
      
      public Builder clearTime() {
        this.bitField0_ &= 0xFFFFFFFB;
        this.time_ = 0L;
        onChanged();
        return this;
      }
      
      public int getIntGiftId() {
        return this.intGiftId_;
      }
      
      public Builder setIntGiftId(int value) {
        this.intGiftId_ = value;
        this.bitField0_ |= 0x8;
        onChanged();
        return this;
      }
      
      public Builder clearIntGiftId() {
        this.bitField0_ &= 0xFFFFFFF7;
        this.intGiftId_ = 0;
        onChanged();
        return this;
      }
      
      public long getSortRank() {
        return this.sortRank_;
      }
      
      public Builder setSortRank(long value) {
        this.sortRank_ = value;
        this.bitField0_ |= 0x10;
        onChanged();
        return this;
      }
      
      public Builder clearSortRank() {
        this.bitField0_ &= 0xFFFFFFEF;
        this.sortRank_ = 0L;
        onChanged();
        return this;
      }
      
      public String getMergeKey() {
        Object ref = this.mergeKey_;
        if (!(ref instanceof String)) {
          ByteString bs = (ByteString)ref;
          String s = bs.toStringUtf8();
          this.mergeKey_ = s;
          return s;
        } 
        return (String)ref;
      }
      
      public ByteString getMergeKeyBytes() {
        Object ref = this.mergeKey_;
        if (ref instanceof String) {
          ByteString b = ByteString.copyFromUtf8((String)ref);
          this.mergeKey_ = b;
          return b;
        } 
        return (ByteString)ref;
      }
      
      public Builder setMergeKey(String value) {
        if (value == null)
          throw new NullPointerException(); 
        this.mergeKey_ = value;
        this.bitField0_ |= 0x20;
        onChanged();
        return this;
      }
      
      public Builder clearMergeKey() {
        this.mergeKey_ = WebGiftFeedOuterClass.WebGiftFeed.getDefaultInstance().getMergeKey();
        this.bitField0_ &= 0xFFFFFFDF;
        onChanged();
        return this;
      }
      
      public Builder setMergeKeyBytes(ByteString value) {
        if (value == null)
          throw new NullPointerException(); 
        WebGiftFeedOuterClass.WebGiftFeed.checkByteStringIsUtf8(value);
        this.mergeKey_ = value;
        this.bitField0_ |= 0x20;
        onChanged();
        return this;
      }
      
      public int getBatchSize() {
        return this.batchSize_;
      }
      
      public Builder setBatchSize(int value) {
        this.batchSize_ = value;
        this.bitField0_ |= 0x40;
        onChanged();
        return this;
      }
      
      public Builder clearBatchSize() {
        this.bitField0_ &= 0xFFFFFFBF;
        this.batchSize_ = 0;
        onChanged();
        return this;
      }
      
      public int getComboCount() {
        return this.comboCount_;
      }
      
      public Builder setComboCount(int value) {
        this.comboCount_ = value;
        this.bitField0_ |= 0x80;
        onChanged();
        return this;
      }
      
      public Builder clearComboCount() {
        this.bitField0_ &= 0xFFFFFF7F;
        this.comboCount_ = 0;
        onChanged();
        return this;
      }
      
      public int getRank() {
        return this.rank_;
      }
      
      public Builder setRank(int value) {
        this.rank_ = value;
        this.bitField0_ |= 0x100;
        onChanged();
        return this;
      }
      
      public Builder clearRank() {
        this.bitField0_ &= 0xFFFFFEFF;
        this.rank_ = 0;
        onChanged();
        return this;
      }
      
      public long getExpireDuration() {
        return this.expireDuration_;
      }
      
      public Builder setExpireDuration(long value) {
        this.expireDuration_ = value;
        this.bitField0_ |= 0x200;
        onChanged();
        return this;
      }
      
      public Builder clearExpireDuration() {
        this.bitField0_ &= 0xFFFFFDFF;
        this.expireDuration_ = 0L;
        onChanged();
        return this;
      }
      
      public long getClientTimestamp() {
        return this.clientTimestamp_;
      }
      
      public Builder setClientTimestamp(long value) {
        this.clientTimestamp_ = value;
        this.bitField0_ |= 0x400;
        onChanged();
        return this;
      }
      
      public Builder clearClientTimestamp() {
        this.bitField0_ &= 0xFFFFFBFF;
        this.clientTimestamp_ = 0L;
        onChanged();
        return this;
      }
      
      public long getSlotDisplayDuration() {
        return this.slotDisplayDuration_;
      }
      
      public Builder setSlotDisplayDuration(long value) {
        this.slotDisplayDuration_ = value;
        this.bitField0_ |= 0x800;
        onChanged();
        return this;
      }
      
      public Builder clearSlotDisplayDuration() {
        this.bitField0_ &= 0xFFFFF7FF;
        this.slotDisplayDuration_ = 0L;
        onChanged();
        return this;
      }
      
      public int getStarLevel() {
        return this.starLevel_;
      }
      
      public Builder setStarLevel(int value) {
        this.starLevel_ = value;
        this.bitField0_ |= 0x1000;
        onChanged();
        return this;
      }
      
      public Builder clearStarLevel() {
        this.bitField0_ &= 0xFFFFEFFF;
        this.starLevel_ = 0;
        onChanged();
        return this;
      }
      
      public int getStyleTypeValue() {
        return this.styleType_;
      }
      
      public Builder setStyleTypeValue(int value) {
        this.styleType_ = value;
        this.bitField0_ |= 0x2000;
        onChanged();
        return this;
      }
      
      public WebGiftFeedOuterClass.WebGiftFeed.StyleType getStyleType() {
        WebGiftFeedOuterClass.WebGiftFeed.StyleType result = WebGiftFeedOuterClass.WebGiftFeed.StyleType.forNumber(this.styleType_);
        return (result == null) ? WebGiftFeedOuterClass.WebGiftFeed.StyleType.UNRECOGNIZED : result;
      }
      
      public Builder setStyleType(WebGiftFeedOuterClass.WebGiftFeed.StyleType value) {
        if (value == null)
          throw new NullPointerException(); 
        this.bitField0_ |= 0x2000;
        this.styleType_ = value.getNumber();
        onChanged();
        return this;
      }
      
      public Builder clearStyleType() {
        this.bitField0_ &= 0xFFFFDFFF;
        this.styleType_ = 0;
        onChanged();
        return this;
      }
      
      public int getLiveAssistantTypeValue() {
        return this.liveAssistantType_;
      }
      
      public Builder setLiveAssistantTypeValue(int value) {
        this.liveAssistantType_ = value;
        this.bitField0_ |= 0x4000;
        onChanged();
        return this;
      }
      
      public WebLiveAssistantTypeOuterClass.WebLiveAssistantType getLiveAssistantType() {
        WebLiveAssistantTypeOuterClass.WebLiveAssistantType result = WebLiveAssistantTypeOuterClass.WebLiveAssistantType.forNumber(this.liveAssistantType_);
        return (result == null) ? WebLiveAssistantTypeOuterClass.WebLiveAssistantType.UNRECOGNIZED : result;
      }
      
      public Builder setLiveAssistantType(WebLiveAssistantTypeOuterClass.WebLiveAssistantType value) {
        if (value == null)
          throw new NullPointerException(); 
        this.bitField0_ |= 0x4000;
        this.liveAssistantType_ = value.getNumber();
        onChanged();
        return this;
      }
      
      public Builder clearLiveAssistantType() {
        this.bitField0_ &= 0xFFFFBFFF;
        this.liveAssistantType_ = 0;
        onChanged();
        return this;
      }
      
      public String getDeviceHash() {
        Object ref = this.deviceHash_;
        if (!(ref instanceof String)) {
          ByteString bs = (ByteString)ref;
          String s = bs.toStringUtf8();
          this.deviceHash_ = s;
          return s;
        } 
        return (String)ref;
      }
      
      public ByteString getDeviceHashBytes() {
        Object ref = this.deviceHash_;
        if (ref instanceof String) {
          ByteString b = ByteString.copyFromUtf8((String)ref);
          this.deviceHash_ = b;
          return b;
        } 
        return (ByteString)ref;
      }
      
      public Builder setDeviceHash(String value) {
        if (value == null)
          throw new NullPointerException(); 
        this.deviceHash_ = value;
        this.bitField0_ |= 0x8000;
        onChanged();
        return this;
      }
      
      public Builder clearDeviceHash() {
        this.deviceHash_ = WebGiftFeedOuterClass.WebGiftFeed.getDefaultInstance().getDeviceHash();
        this.bitField0_ &= 0xFFFF7FFF;
        onChanged();
        return this;
      }
      
      public Builder setDeviceHashBytes(ByteString value) {
        if (value == null)
          throw new NullPointerException(); 
        WebGiftFeedOuterClass.WebGiftFeed.checkByteStringIsUtf8(value);
        this.deviceHash_ = value;
        this.bitField0_ |= 0x8000;
        onChanged();
        return this;
      }
      
      public boolean getDanmakuDisplay() {
        return this.danmakuDisplay_;
      }
      
      public Builder setDanmakuDisplay(boolean value) {
        this.danmakuDisplay_ = value;
        this.bitField0_ |= 0x10000;
        onChanged();
        return this;
      }
      
      public Builder clearDanmakuDisplay() {
        this.bitField0_ &= 0xFFFEFFFF;
        this.danmakuDisplay_ = false;
        onChanged();
        return this;
      }
      
      public boolean hasLiveAudienceState() {
        return ((this.bitField0_ & 0x20000) != 0);
      }
      
      public LiveAudienceStateOuterClass.LiveAudienceState getLiveAudienceState() {
        if (this.liveAudienceStateBuilder_ == null)
          return (this.liveAudienceState_ == null) ? LiveAudienceStateOuterClass.LiveAudienceState.getDefaultInstance() : this.liveAudienceState_; 
        return (LiveAudienceStateOuterClass.LiveAudienceState)this.liveAudienceStateBuilder_.getMessage();
      }
      
      public Builder setLiveAudienceState(LiveAudienceStateOuterClass.LiveAudienceState value) {
        if (this.liveAudienceStateBuilder_ == null) {
          if (value == null)
            throw new NullPointerException(); 
          this.liveAudienceState_ = value;
        } else {
          this.liveAudienceStateBuilder_.setMessage((AbstractMessage)value);
        } 
        this.bitField0_ |= 0x20000;
        onChanged();
        return this;
      }
      
      public Builder setLiveAudienceState(LiveAudienceStateOuterClass.LiveAudienceState.Builder builderForValue) {
        if (this.liveAudienceStateBuilder_ == null) {
          this.liveAudienceState_ = builderForValue.build();
        } else {
          this.liveAudienceStateBuilder_.setMessage((AbstractMessage)builderForValue.build());
        } 
        this.bitField0_ |= 0x20000;
        onChanged();
        return this;
      }
      
      public Builder mergeLiveAudienceState(LiveAudienceStateOuterClass.LiveAudienceState value) {
        if (this.liveAudienceStateBuilder_ == null) {
          if ((this.bitField0_ & 0x20000) != 0 && this.liveAudienceState_ != null && this.liveAudienceState_ != 
            
            LiveAudienceStateOuterClass.LiveAudienceState.getDefaultInstance()) {
            getLiveAudienceStateBuilder().mergeFrom(value);
          } else {
            this.liveAudienceState_ = value;
          } 
        } else {
          this.liveAudienceStateBuilder_.mergeFrom((AbstractMessage)value);
        } 
        if (this.liveAudienceState_ != null) {
          this.bitField0_ |= 0x20000;
          onChanged();
        } 
        return this;
      }
      
      public Builder clearLiveAudienceState() {
        this.bitField0_ &= 0xFFFDFFFF;
        this.liveAudienceState_ = null;
        if (this.liveAudienceStateBuilder_ != null) {
          this.liveAudienceStateBuilder_.dispose();
          this.liveAudienceStateBuilder_ = null;
        } 
        onChanged();
        return this;
      }
      
      public LiveAudienceStateOuterClass.LiveAudienceState.Builder getLiveAudienceStateBuilder() {
        this.bitField0_ |= 0x20000;
        onChanged();
        return (LiveAudienceStateOuterClass.LiveAudienceState.Builder)getLiveAudienceStateFieldBuilder().getBuilder();
      }
      
      public LiveAudienceStateOuterClass.LiveAudienceStateOrBuilder getLiveAudienceStateOrBuilder() {
        if (this.liveAudienceStateBuilder_ != null)
          return (LiveAudienceStateOuterClass.LiveAudienceStateOrBuilder)this.liveAudienceStateBuilder_.getMessageOrBuilder(); 
        return (this.liveAudienceState_ == null) ? 
          LiveAudienceStateOuterClass.LiveAudienceState.getDefaultInstance() : this.liveAudienceState_;
      }
      
      private SingleFieldBuilderV3<LiveAudienceStateOuterClass.LiveAudienceState, LiveAudienceStateOuterClass.LiveAudienceState.Builder, LiveAudienceStateOuterClass.LiveAudienceStateOrBuilder> getLiveAudienceStateFieldBuilder() {
        if (this.liveAudienceStateBuilder_ == null) {
          this
            
            .liveAudienceStateBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getLiveAudienceState(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
          this.liveAudienceState_ = null;
        } 
        return this.liveAudienceStateBuilder_;
      }
      
      public final Builder setUnknownFields(UnknownFieldSet unknownFields) {
        return (Builder)super.setUnknownFields(unknownFields);
      }
      
      public final Builder mergeUnknownFields(UnknownFieldSet unknownFields) {
        return (Builder)super.mergeUnknownFields(unknownFields);
      }
    }
    
    private static final WebGiftFeed DEFAULT_INSTANCE = new WebGiftFeed();
    
    public static WebGiftFeed getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }
    
    private static final Parser<WebGiftFeed> PARSER = (Parser<WebGiftFeed>)new AbstractParser<WebGiftFeed>() {
        public WebGiftFeedOuterClass.WebGiftFeed parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
          WebGiftFeedOuterClass.WebGiftFeed.Builder builder = WebGiftFeedOuterClass.WebGiftFeed.newBuilder();
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
    
    public static Parser<WebGiftFeed> parser() {
      return PARSER;
    }
    
    public Parser<WebGiftFeed> getParserForType() {
      return PARSER;
    }
    
    public WebGiftFeed getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }
  }
  
  public static Descriptors.FileDescriptor getDescriptor() {
    return descriptor;
  }
  
  static {
    String[] descriptorData = { "\n\021WebGiftFeed.proto\032\024SimpleUserInfo.proto\032\032WebLiveAssistantType.proto\032\027LiveAudienceState.proto\"ê\004\n\013WebGiftFeed\022\n\n\002id\030\001 \001(\t\022\035\n\004user\030\002 \001(\0132\017.SimpleUserInfo\022\f\n\004time\030\003 \001(\004\022\021\n\tintGiftId\030\004 \001(\r\022\020\n\bsortRank\030\005 \001(\004\022\020\n\bmergeKey\030\006 \001(\t\022\021\n\tbatchSize\030\007 \001(\r\022\022\n\ncomboCount\030\b \001(\r\022\f\n\004rank\030\t \001(\r\022\026\n\016expireDuration\030\n \001(\004\022\027\n\017clientTimestamp\030\013 \001(\004\022\033\n\023slotDisplayDuration\030\f \001(\004\022\021\n\tstarLevel\030\r \001(\r\022)\n\tstyleType\030\016 \001(\0162\026.WebGiftFeed.StyleType\0220\n\021liveAssistantType\030\017 \001(\0162\025.WebLiveAssistantType\022\022\n\ndeviceHash\030\020 \001(\t\022\026\n\016danmakuDisplay\030\021 \001(\b\022-\n\021liveAudienceState\030\022 \001(\0132\022.LiveAudienceState\"\001\n\tStyleType\022\021\n\rUNKNOWN_STYLE\020\000\022\020\n\fBATCH_STAR_0\020\001\022\020\n\fBATCH_STAR_1\020\002\022\020\n\fBATCH_STAR_2\020\003\022\020\n\fBATCH_STAR_3\020\004\022\020\n\fBATCH_STAR_4\020\005\022\020\n\fBATCH_STAR_5\020\006\022\020\n\fBATCH_STAR_6\020\007B<\n:tech.ordinaryroad.live.chat.client.codec.kuaishou.protobufb\006proto3" };
    descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[] { SimpleUserInfoOuterClass.getDescriptor(), 
          WebLiveAssistantTypeOuterClass.getDescriptor(), 
          LiveAudienceStateOuterClass.getDescriptor() });
    internal_static_WebGiftFeed_descriptor = getDescriptor().getMessageTypes().get(0);
    internal_static_WebGiftFeed_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_WebGiftFeed_descriptor, new String[] { 
          "Id", "User", "Time", "IntGiftId", "SortRank", "MergeKey", "BatchSize", "ComboCount", "Rank", "ExpireDuration", 
          "ClientTimestamp", "SlotDisplayDuration", "StarLevel", "StyleType", "LiveAssistantType", "DeviceHash", "DanmakuDisplay", "LiveAudienceState" });
    SimpleUserInfoOuterClass.getDescriptor();
    WebLiveAssistantTypeOuterClass.getDescriptor();
    LiveAudienceStateOuterClass.getDescriptor();
  }
  
  public static interface WebGiftFeedOrBuilder extends MessageOrBuilder {
    String getId();
    
    ByteString getIdBytes();
    
    boolean hasUser();
    
    SimpleUserInfoOuterClass.SimpleUserInfo getUser();
    
    SimpleUserInfoOuterClass.SimpleUserInfoOrBuilder getUserOrBuilder();
    
    long getTime();
    
    int getIntGiftId();
    
    long getSortRank();
    
    String getMergeKey();
    
    ByteString getMergeKeyBytes();
    
    int getBatchSize();
    
    int getComboCount();
    
    int getRank();
    
    long getExpireDuration();
    
    long getClientTimestamp();
    
    long getSlotDisplayDuration();
    
    int getStarLevel();
    
    int getStyleTypeValue();
    
    WebGiftFeedOuterClass.WebGiftFeed.StyleType getStyleType();
    
    int getLiveAssistantTypeValue();
    
    WebLiveAssistantTypeOuterClass.WebLiveAssistantType getLiveAssistantType();
    
    String getDeviceHash();
    
    ByteString getDeviceHashBytes();
    
    boolean getDanmakuDisplay();
    
    boolean hasLiveAudienceState();
    
    LiveAudienceStateOuterClass.LiveAudienceState getLiveAudienceState();
    
    LiveAudienceStateOuterClass.LiveAudienceStateOrBuilder getLiveAudienceStateOrBuilder();
  }
}
