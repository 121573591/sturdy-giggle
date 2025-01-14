package tech.ordinaryroad.live.chat.client.codec.douyin.protobuf;

import com.google.protobuf.AbstractMessage;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.MessageLite;
import com.google.protobuf.Parser;
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

public final class RoomUserSeqMessage extends GeneratedMessageV3 implements RoomUserSeqMessageOrBuilder {
  private static final long serialVersionUID = 0L;
  
  private int bitField0_;
  
  public static final int COMMON_FIELD_NUMBER = 1;
  
  private Common common_;
  
  public static final int RANKSLIST_FIELD_NUMBER = 2;
  
  private List<RoomUserSeqMessageContributor> ranksList_;
  
  public static final int TOTAL_FIELD_NUMBER = 3;
  
  private long total_;
  
  public static final int POPSTR_FIELD_NUMBER = 4;
  
  private volatile Object popStr_;
  
  public static final int SEATSLIST_FIELD_NUMBER = 5;
  
  private List<RoomUserSeqMessageContributor> seatsList_;
  
  public static final int POPULARITY_FIELD_NUMBER = 6;
  
  private long popularity_;
  
  public static final int TOTALUSER_FIELD_NUMBER = 7;
  
  private long totalUser_;
  
  public static final int TOTALUSERSTR_FIELD_NUMBER = 8;
  
  private volatile Object totalUserStr_;
  
  public static final int TOTALSTR_FIELD_NUMBER = 9;
  
  private volatile Object totalStr_;
  
  public static final int ONLINEUSERFORANCHOR_FIELD_NUMBER = 10;
  
  private volatile Object onlineUserForAnchor_;
  
  public static final int TOTALPVFORANCHOR_FIELD_NUMBER = 11;
  
  private volatile Object totalPvForAnchor_;
  
  public static final int UPRIGHTSTATSSTR_FIELD_NUMBER = 12;
  
  private volatile Object upRightStatsStr_;
  
  public static final int UPRIGHTSTATSSTRCOMPLETE_FIELD_NUMBER = 13;
  
  private volatile Object upRightStatsStrComplete_;
  
  private byte memoizedIsInitialized;
  
  private RoomUserSeqMessage(GeneratedMessageV3.Builder<?> builder) {
    super(builder);
    this.total_ = 0L;
    this.popStr_ = "";
    this.popularity_ = 0L;
    this.totalUser_ = 0L;
    this.totalUserStr_ = "";
    this.totalStr_ = "";
    this.onlineUserForAnchor_ = "";
    this.totalPvForAnchor_ = "";
    this.upRightStatsStr_ = "";
    this.upRightStatsStrComplete_ = "";
    this.memoizedIsInitialized = -1;
  }
  
  private RoomUserSeqMessage() {
    this.total_ = 0L;
    this.popStr_ = "";
    this.popularity_ = 0L;
    this.totalUser_ = 0L;
    this.totalUserStr_ = "";
    this.totalStr_ = "";
    this.onlineUserForAnchor_ = "";
    this.totalPvForAnchor_ = "";
    this.upRightStatsStr_ = "";
    this.upRightStatsStrComplete_ = "";
    this.memoizedIsInitialized = -1;
    this.ranksList_ = Collections.emptyList();
    this.popStr_ = "";
    this.seatsList_ = Collections.emptyList();
    this.totalUserStr_ = "";
    this.totalStr_ = "";
    this.onlineUserForAnchor_ = "";
    this.totalPvForAnchor_ = "";
    this.upRightStatsStr_ = "";
    this.upRightStatsStrComplete_ = "";
  }
  
  protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
    return new RoomUserSeqMessage();
  }
  
  public static final Descriptors.Descriptor getDescriptor() {
    return Douyin.internal_static_RoomUserSeqMessage_descriptor;
  }
  
  protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
    return Douyin.internal_static_RoomUserSeqMessage_fieldAccessorTable.ensureFieldAccessorsInitialized(RoomUserSeqMessage.class, Builder.class);
  }
  
  public boolean hasCommon() {
    return ((this.bitField0_ & 0x1) != 0);
  }
  
  public Common getCommon() {
    return (this.common_ == null) ? Common.getDefaultInstance() : this.common_;
  }
  
  public CommonOrBuilder getCommonOrBuilder() {
    return (this.common_ == null) ? Common.getDefaultInstance() : this.common_;
  }
  
  public List<RoomUserSeqMessageContributor> getRanksListList() {
    return this.ranksList_;
  }
  
  public List<? extends RoomUserSeqMessageContributorOrBuilder> getRanksListOrBuilderList() {
    return (List)this.ranksList_;
  }
  
  public int getRanksListCount() {
    return this.ranksList_.size();
  }
  
  public RoomUserSeqMessageContributor getRanksList(int index) {
    return this.ranksList_.get(index);
  }
  
  public RoomUserSeqMessageContributorOrBuilder getRanksListOrBuilder(int index) {
    return this.ranksList_.get(index);
  }
  
  public long getTotal() {
    return this.total_;
  }
  
  public String getPopStr() {
    Object ref = this.popStr_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.popStr_ = s;
    return s;
  }
  
  public ByteString getPopStrBytes() {
    Object ref = this.popStr_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.popStr_ = b;
      return b;
    } 
    return (ByteString)ref;
  }
  
  public List<RoomUserSeqMessageContributor> getSeatsListList() {
    return this.seatsList_;
  }
  
  public List<? extends RoomUserSeqMessageContributorOrBuilder> getSeatsListOrBuilderList() {
    return (List)this.seatsList_;
  }
  
  public int getSeatsListCount() {
    return this.seatsList_.size();
  }
  
  public RoomUserSeqMessageContributor getSeatsList(int index) {
    return this.seatsList_.get(index);
  }
  
  public RoomUserSeqMessageContributorOrBuilder getSeatsListOrBuilder(int index) {
    return this.seatsList_.get(index);
  }
  
  public long getPopularity() {
    return this.popularity_;
  }
  
  public long getTotalUser() {
    return this.totalUser_;
  }
  
  public String getTotalUserStr() {
    Object ref = this.totalUserStr_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.totalUserStr_ = s;
    return s;
  }
  
  public ByteString getTotalUserStrBytes() {
    Object ref = this.totalUserStr_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.totalUserStr_ = b;
      return b;
    } 
    return (ByteString)ref;
  }
  
  public String getTotalStr() {
    Object ref = this.totalStr_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.totalStr_ = s;
    return s;
  }
  
  public ByteString getTotalStrBytes() {
    Object ref = this.totalStr_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.totalStr_ = b;
      return b;
    } 
    return (ByteString)ref;
  }
  
  public String getOnlineUserForAnchor() {
    Object ref = this.onlineUserForAnchor_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.onlineUserForAnchor_ = s;
    return s;
  }
  
  public ByteString getOnlineUserForAnchorBytes() {
    Object ref = this.onlineUserForAnchor_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.onlineUserForAnchor_ = b;
      return b;
    } 
    return (ByteString)ref;
  }
  
  public String getTotalPvForAnchor() {
    Object ref = this.totalPvForAnchor_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.totalPvForAnchor_ = s;
    return s;
  }
  
  public ByteString getTotalPvForAnchorBytes() {
    Object ref = this.totalPvForAnchor_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.totalPvForAnchor_ = b;
      return b;
    } 
    return (ByteString)ref;
  }
  
  public String getUpRightStatsStr() {
    Object ref = this.upRightStatsStr_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.upRightStatsStr_ = s;
    return s;
  }
  
  public ByteString getUpRightStatsStrBytes() {
    Object ref = this.upRightStatsStr_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.upRightStatsStr_ = b;
      return b;
    } 
    return (ByteString)ref;
  }
  
  public String getUpRightStatsStrComplete() {
    Object ref = this.upRightStatsStrComplete_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.upRightStatsStrComplete_ = s;
    return s;
  }
  
  public ByteString getUpRightStatsStrCompleteBytes() {
    Object ref = this.upRightStatsStrComplete_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.upRightStatsStrComplete_ = b;
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
    if ((this.bitField0_ & 0x1) != 0)
      output.writeMessage(1, (MessageLite)getCommon()); 
    int i;
    for (i = 0; i < this.ranksList_.size(); i++)
      output.writeMessage(2, (MessageLite)this.ranksList_.get(i)); 
    if (this.total_ != 0L)
      output.writeInt64(3, this.total_); 
    if (!GeneratedMessageV3.isStringEmpty(this.popStr_))
      GeneratedMessageV3.writeString(output, 4, this.popStr_); 
    for (i = 0; i < this.seatsList_.size(); i++)
      output.writeMessage(5, (MessageLite)this.seatsList_.get(i)); 
    if (this.popularity_ != 0L)
      output.writeInt64(6, this.popularity_); 
    if (this.totalUser_ != 0L)
      output.writeInt64(7, this.totalUser_); 
    if (!GeneratedMessageV3.isStringEmpty(this.totalUserStr_))
      GeneratedMessageV3.writeString(output, 8, this.totalUserStr_); 
    if (!GeneratedMessageV3.isStringEmpty(this.totalStr_))
      GeneratedMessageV3.writeString(output, 9, this.totalStr_); 
    if (!GeneratedMessageV3.isStringEmpty(this.onlineUserForAnchor_))
      GeneratedMessageV3.writeString(output, 10, this.onlineUserForAnchor_); 
    if (!GeneratedMessageV3.isStringEmpty(this.totalPvForAnchor_))
      GeneratedMessageV3.writeString(output, 11, this.totalPvForAnchor_); 
    if (!GeneratedMessageV3.isStringEmpty(this.upRightStatsStr_))
      GeneratedMessageV3.writeString(output, 12, this.upRightStatsStr_); 
    if (!GeneratedMessageV3.isStringEmpty(this.upRightStatsStrComplete_))
      GeneratedMessageV3.writeString(output, 13, this.upRightStatsStrComplete_); 
    getUnknownFields().writeTo(output);
  }
  
  public int getSerializedSize() {
    int size = this.memoizedSize;
    if (size != -1)
      return size; 
    size = 0;
    if ((this.bitField0_ & 0x1) != 0)
      size += 
        CodedOutputStream.computeMessageSize(1, (MessageLite)getCommon()); 
    int i;
    for (i = 0; i < this.ranksList_.size(); i++)
      size += 
        CodedOutputStream.computeMessageSize(2, (MessageLite)this.ranksList_.get(i)); 
    if (this.total_ != 0L)
      size += 
        CodedOutputStream.computeInt64Size(3, this.total_); 
    if (!GeneratedMessageV3.isStringEmpty(this.popStr_))
      size += GeneratedMessageV3.computeStringSize(4, this.popStr_); 
    for (i = 0; i < this.seatsList_.size(); i++)
      size += 
        CodedOutputStream.computeMessageSize(5, (MessageLite)this.seatsList_.get(i)); 
    if (this.popularity_ != 0L)
      size += 
        CodedOutputStream.computeInt64Size(6, this.popularity_); 
    if (this.totalUser_ != 0L)
      size += 
        CodedOutputStream.computeInt64Size(7, this.totalUser_); 
    if (!GeneratedMessageV3.isStringEmpty(this.totalUserStr_))
      size += GeneratedMessageV3.computeStringSize(8, this.totalUserStr_); 
    if (!GeneratedMessageV3.isStringEmpty(this.totalStr_))
      size += GeneratedMessageV3.computeStringSize(9, this.totalStr_); 
    if (!GeneratedMessageV3.isStringEmpty(this.onlineUserForAnchor_))
      size += GeneratedMessageV3.computeStringSize(10, this.onlineUserForAnchor_); 
    if (!GeneratedMessageV3.isStringEmpty(this.totalPvForAnchor_))
      size += GeneratedMessageV3.computeStringSize(11, this.totalPvForAnchor_); 
    if (!GeneratedMessageV3.isStringEmpty(this.upRightStatsStr_))
      size += GeneratedMessageV3.computeStringSize(12, this.upRightStatsStr_); 
    if (!GeneratedMessageV3.isStringEmpty(this.upRightStatsStrComplete_))
      size += GeneratedMessageV3.computeStringSize(13, this.upRightStatsStrComplete_); 
    size += getUnknownFields().getSerializedSize();
    this.memoizedSize = size;
    return size;
  }
  
  public boolean equals(Object obj) {
    if (obj == this)
      return true; 
    if (!(obj instanceof RoomUserSeqMessage))
      return super.equals(obj); 
    RoomUserSeqMessage other = (RoomUserSeqMessage)obj;
    if (hasCommon() != other.hasCommon())
      return false; 
    if (hasCommon() && 
      
      !getCommon().equals(other.getCommon()))
      return false; 
    if (!getRanksListList().equals(other.getRanksListList()))
      return false; 
    if (getTotal() != other
      .getTotal())
      return false; 
    if (!getPopStr().equals(other.getPopStr()))
      return false; 
    if (!getSeatsListList().equals(other.getSeatsListList()))
      return false; 
    if (getPopularity() != other
      .getPopularity())
      return false; 
    if (getTotalUser() != other
      .getTotalUser())
      return false; 
    if (!getTotalUserStr().equals(other.getTotalUserStr()))
      return false; 
    if (!getTotalStr().equals(other.getTotalStr()))
      return false; 
    if (!getOnlineUserForAnchor().equals(other.getOnlineUserForAnchor()))
      return false; 
    if (!getTotalPvForAnchor().equals(other.getTotalPvForAnchor()))
      return false; 
    if (!getUpRightStatsStr().equals(other.getUpRightStatsStr()))
      return false; 
    if (!getUpRightStatsStrComplete().equals(other.getUpRightStatsStrComplete()))
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
    if (hasCommon()) {
      hash = 37 * hash + 1;
      hash = 53 * hash + getCommon().hashCode();
    } 
    if (getRanksListCount() > 0) {
      hash = 37 * hash + 2;
      hash = 53 * hash + getRanksListList().hashCode();
    } 
    hash = 37 * hash + 3;
    hash = 53 * hash + Internal.hashLong(
        getTotal());
    hash = 37 * hash + 4;
    hash = 53 * hash + getPopStr().hashCode();
    if (getSeatsListCount() > 0) {
      hash = 37 * hash + 5;
      hash = 53 * hash + getSeatsListList().hashCode();
    } 
    hash = 37 * hash + 6;
    hash = 53 * hash + Internal.hashLong(
        getPopularity());
    hash = 37 * hash + 7;
    hash = 53 * hash + Internal.hashLong(
        getTotalUser());
    hash = 37 * hash + 8;
    hash = 53 * hash + getTotalUserStr().hashCode();
    hash = 37 * hash + 9;
    hash = 53 * hash + getTotalStr().hashCode();
    hash = 37 * hash + 10;
    hash = 53 * hash + getOnlineUserForAnchor().hashCode();
    hash = 37 * hash + 11;
    hash = 53 * hash + getTotalPvForAnchor().hashCode();
    hash = 37 * hash + 12;
    hash = 53 * hash + getUpRightStatsStr().hashCode();
    hash = 37 * hash + 13;
    hash = 53 * hash + getUpRightStatsStrComplete().hashCode();
    hash = 29 * hash + getUnknownFields().hashCode();
    this.memoizedHashCode = hash;
    return hash;
  }
  
  public static RoomUserSeqMessage parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
    return (RoomUserSeqMessage)PARSER.parseFrom(data);
  }
  
  public static RoomUserSeqMessage parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (RoomUserSeqMessage)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static RoomUserSeqMessage parseFrom(ByteString data) throws InvalidProtocolBufferException {
    return (RoomUserSeqMessage)PARSER.parseFrom(data);
  }
  
  public static RoomUserSeqMessage parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (RoomUserSeqMessage)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static RoomUserSeqMessage parseFrom(byte[] data) throws InvalidProtocolBufferException {
    return (RoomUserSeqMessage)PARSER.parseFrom(data);
  }
  
  public static RoomUserSeqMessage parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (RoomUserSeqMessage)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static RoomUserSeqMessage parseFrom(InputStream input) throws IOException {
    return 
      (RoomUserSeqMessage)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static RoomUserSeqMessage parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (RoomUserSeqMessage)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static RoomUserSeqMessage parseDelimitedFrom(InputStream input) throws IOException {
    return 
      (RoomUserSeqMessage)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
  }
  
  public static RoomUserSeqMessage parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (RoomUserSeqMessage)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static RoomUserSeqMessage parseFrom(CodedInputStream input) throws IOException {
    return 
      (RoomUserSeqMessage)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static RoomUserSeqMessage parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (RoomUserSeqMessage)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public Builder newBuilderForType() {
    return newBuilder();
  }
  
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  
  public static Builder newBuilder(RoomUserSeqMessage prototype) {
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
  
  public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements RoomUserSeqMessageOrBuilder {
    private int bitField0_;
    
    private Common common_;
    
    private SingleFieldBuilderV3<Common, Common.Builder, CommonOrBuilder> commonBuilder_;
    
    private List<RoomUserSeqMessageContributor> ranksList_;
    
    private RepeatedFieldBuilderV3<RoomUserSeqMessageContributor, RoomUserSeqMessageContributor.Builder, RoomUserSeqMessageContributorOrBuilder> ranksListBuilder_;
    
    private long total_;
    
    private Object popStr_;
    
    private List<RoomUserSeqMessageContributor> seatsList_;
    
    private RepeatedFieldBuilderV3<RoomUserSeqMessageContributor, RoomUserSeqMessageContributor.Builder, RoomUserSeqMessageContributorOrBuilder> seatsListBuilder_;
    
    private long popularity_;
    
    private long totalUser_;
    
    private Object totalUserStr_;
    
    private Object totalStr_;
    
    private Object onlineUserForAnchor_;
    
    private Object totalPvForAnchor_;
    
    private Object upRightStatsStr_;
    
    private Object upRightStatsStrComplete_;
    
    public static final Descriptors.Descriptor getDescriptor() {
      return Douyin.internal_static_RoomUserSeqMessage_descriptor;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return Douyin.internal_static_RoomUserSeqMessage_fieldAccessorTable
        .ensureFieldAccessorsInitialized(RoomUserSeqMessage.class, Builder.class);
    }
    
    private Builder() {
      this
        .ranksList_ = Collections.emptyList();
      this.popStr_ = "";
      this
        .seatsList_ = Collections.emptyList();
      this.totalUserStr_ = "";
      this.totalStr_ = "";
      this.onlineUserForAnchor_ = "";
      this.totalPvForAnchor_ = "";
      this.upRightStatsStr_ = "";
      this.upRightStatsStrComplete_ = "";
      maybeForceBuilderInitialization();
    }
    
    private Builder(GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      this.ranksList_ = Collections.emptyList();
      this.popStr_ = "";
      this.seatsList_ = Collections.emptyList();
      this.totalUserStr_ = "";
      this.totalStr_ = "";
      this.onlineUserForAnchor_ = "";
      this.totalPvForAnchor_ = "";
      this.upRightStatsStr_ = "";
      this.upRightStatsStrComplete_ = "";
      maybeForceBuilderInitialization();
    }
    
    private void maybeForceBuilderInitialization() {
      if (RoomUserSeqMessage.alwaysUseFieldBuilders) {
        getCommonFieldBuilder();
        getRanksListFieldBuilder();
        getSeatsListFieldBuilder();
      } 
    }
    
    public Builder clear() {
      super.clear();
      this.bitField0_ = 0;
      this.common_ = null;
      if (this.commonBuilder_ != null) {
        this.commonBuilder_.dispose();
        this.commonBuilder_ = null;
      } 
      if (this.ranksListBuilder_ == null) {
        this.ranksList_ = Collections.emptyList();
      } else {
        this.ranksList_ = null;
        this.ranksListBuilder_.clear();
      } 
      this.bitField0_ &= 0xFFFFFFFD;
      this.total_ = 0L;
      this.popStr_ = "";
      if (this.seatsListBuilder_ == null) {
        this.seatsList_ = Collections.emptyList();
      } else {
        this.seatsList_ = null;
        this.seatsListBuilder_.clear();
      } 
      this.bitField0_ &= 0xFFFFFFEF;
      this.popularity_ = 0L;
      this.totalUser_ = 0L;
      this.totalUserStr_ = "";
      this.totalStr_ = "";
      this.onlineUserForAnchor_ = "";
      this.totalPvForAnchor_ = "";
      this.upRightStatsStr_ = "";
      this.upRightStatsStrComplete_ = "";
      return this;
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
      return Douyin.internal_static_RoomUserSeqMessage_descriptor;
    }
    
    public RoomUserSeqMessage getDefaultInstanceForType() {
      return RoomUserSeqMessage.getDefaultInstance();
    }
    
    public RoomUserSeqMessage build() {
      RoomUserSeqMessage result = buildPartial();
      if (!result.isInitialized())
        throw newUninitializedMessageException(result); 
      return result;
    }
    
    public RoomUserSeqMessage buildPartial() {
      RoomUserSeqMessage result = new RoomUserSeqMessage(this);
      buildPartialRepeatedFields(result);
      if (this.bitField0_ != 0)
        buildPartial0(result); 
      onBuilt();
      return result;
    }
    
    private void buildPartialRepeatedFields(RoomUserSeqMessage result) {
      if (this.ranksListBuilder_ == null) {
        if ((this.bitField0_ & 0x2) != 0) {
          this.ranksList_ = Collections.unmodifiableList(this.ranksList_);
          this.bitField0_ &= 0xFFFFFFFD;
        } 
        result.ranksList_ = this.ranksList_;
      } else {
        result.ranksList_ = this.ranksListBuilder_.build();
      } 
      if (this.seatsListBuilder_ == null) {
        if ((this.bitField0_ & 0x10) != 0) {
          this.seatsList_ = Collections.unmodifiableList(this.seatsList_);
          this.bitField0_ &= 0xFFFFFFEF;
        } 
        result.seatsList_ = this.seatsList_;
      } else {
        result.seatsList_ = this.seatsListBuilder_.build();
      } 
    }
    
    private void buildPartial0(RoomUserSeqMessage result) {
      int from_bitField0_ = this.bitField0_;
      int to_bitField0_ = 0;
      if ((from_bitField0_ & 0x1) != 0) {
        result.common_ = (this.commonBuilder_ == null) ? this.common_ : (Common)this.commonBuilder_.build();
        to_bitField0_ |= 0x1;
      } 
      if ((from_bitField0_ & 0x4) != 0)
        result.total_ = this.total_; 
      if ((from_bitField0_ & 0x8) != 0)
        result.popStr_ = this.popStr_; 
      if ((from_bitField0_ & 0x20) != 0)
        result.popularity_ = this.popularity_; 
      if ((from_bitField0_ & 0x40) != 0)
        result.totalUser_ = this.totalUser_; 
      if ((from_bitField0_ & 0x80) != 0)
        result.totalUserStr_ = this.totalUserStr_; 
      if ((from_bitField0_ & 0x100) != 0)
        result.totalStr_ = this.totalStr_; 
      if ((from_bitField0_ & 0x200) != 0)
        result.onlineUserForAnchor_ = this.onlineUserForAnchor_; 
      if ((from_bitField0_ & 0x400) != 0)
        result.totalPvForAnchor_ = this.totalPvForAnchor_; 
      if ((from_bitField0_ & 0x800) != 0)
        result.upRightStatsStr_ = this.upRightStatsStr_; 
      if ((from_bitField0_ & 0x1000) != 0)
        result.upRightStatsStrComplete_ = this.upRightStatsStrComplete_; 
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
      if (other instanceof RoomUserSeqMessage)
        return mergeFrom((RoomUserSeqMessage)other); 
      super.mergeFrom(other);
      return this;
    }
    
    public Builder mergeFrom(RoomUserSeqMessage other) {
      if (other == RoomUserSeqMessage.getDefaultInstance())
        return this; 
      if (other.hasCommon())
        mergeCommon(other.getCommon()); 
      if (this.ranksListBuilder_ == null) {
        if (!other.ranksList_.isEmpty()) {
          if (this.ranksList_.isEmpty()) {
            this.ranksList_ = other.ranksList_;
            this.bitField0_ &= 0xFFFFFFFD;
          } else {
            ensureRanksListIsMutable();
            this.ranksList_.addAll(other.ranksList_);
          } 
          onChanged();
        } 
      } else if (!other.ranksList_.isEmpty()) {
        if (this.ranksListBuilder_.isEmpty()) {
          this.ranksListBuilder_.dispose();
          this.ranksListBuilder_ = null;
          this.ranksList_ = other.ranksList_;
          this.bitField0_ &= 0xFFFFFFFD;
          this.ranksListBuilder_ = RoomUserSeqMessage.alwaysUseFieldBuilders ? getRanksListFieldBuilder() : null;
        } else {
          this.ranksListBuilder_.addAllMessages(other.ranksList_);
        } 
      } 
      if (other.getTotal() != 0L)
        setTotal(other.getTotal()); 
      if (!other.getPopStr().isEmpty()) {
        this.popStr_ = other.popStr_;
        this.bitField0_ |= 0x8;
        onChanged();
      } 
      if (this.seatsListBuilder_ == null) {
        if (!other.seatsList_.isEmpty()) {
          if (this.seatsList_.isEmpty()) {
            this.seatsList_ = other.seatsList_;
            this.bitField0_ &= 0xFFFFFFEF;
          } else {
            ensureSeatsListIsMutable();
            this.seatsList_.addAll(other.seatsList_);
          } 
          onChanged();
        } 
      } else if (!other.seatsList_.isEmpty()) {
        if (this.seatsListBuilder_.isEmpty()) {
          this.seatsListBuilder_.dispose();
          this.seatsListBuilder_ = null;
          this.seatsList_ = other.seatsList_;
          this.bitField0_ &= 0xFFFFFFEF;
          this.seatsListBuilder_ = RoomUserSeqMessage.alwaysUseFieldBuilders ? getSeatsListFieldBuilder() : null;
        } else {
          this.seatsListBuilder_.addAllMessages(other.seatsList_);
        } 
      } 
      if (other.getPopularity() != 0L)
        setPopularity(other.getPopularity()); 
      if (other.getTotalUser() != 0L)
        setTotalUser(other.getTotalUser()); 
      if (!other.getTotalUserStr().isEmpty()) {
        this.totalUserStr_ = other.totalUserStr_;
        this.bitField0_ |= 0x80;
        onChanged();
      } 
      if (!other.getTotalStr().isEmpty()) {
        this.totalStr_ = other.totalStr_;
        this.bitField0_ |= 0x100;
        onChanged();
      } 
      if (!other.getOnlineUserForAnchor().isEmpty()) {
        this.onlineUserForAnchor_ = other.onlineUserForAnchor_;
        this.bitField0_ |= 0x200;
        onChanged();
      } 
      if (!other.getTotalPvForAnchor().isEmpty()) {
        this.totalPvForAnchor_ = other.totalPvForAnchor_;
        this.bitField0_ |= 0x400;
        onChanged();
      } 
      if (!other.getUpRightStatsStr().isEmpty()) {
        this.upRightStatsStr_ = other.upRightStatsStr_;
        this.bitField0_ |= 0x800;
        onChanged();
      } 
      if (!other.getUpRightStatsStrComplete().isEmpty()) {
        this.upRightStatsStrComplete_ = other.upRightStatsStrComplete_;
        this.bitField0_ |= 0x1000;
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
          RoomUserSeqMessageContributor m;
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              continue;
            case 10:
              input.readMessage((MessageLite.Builder)getCommonFieldBuilder().getBuilder(), extensionRegistry);
              this.bitField0_ |= 0x1;
              continue;
            case 18:
              m = (RoomUserSeqMessageContributor)input.readMessage(RoomUserSeqMessageContributor.parser(), extensionRegistry);
              if (this.ranksListBuilder_ == null) {
                ensureRanksListIsMutable();
                this.ranksList_.add(m);
                continue;
              } 
              this.ranksListBuilder_.addMessage((AbstractMessage)m);
              continue;
            case 24:
              this.total_ = input.readInt64();
              this.bitField0_ |= 0x4;
              continue;
            case 34:
              this.popStr_ = input.readStringRequireUtf8();
              this.bitField0_ |= 0x8;
              continue;
            case 42:
              m = (RoomUserSeqMessageContributor)input.readMessage(RoomUserSeqMessageContributor.parser(), extensionRegistry);
              if (this.seatsListBuilder_ == null) {
                ensureSeatsListIsMutable();
                this.seatsList_.add(m);
                continue;
              } 
              this.seatsListBuilder_.addMessage((AbstractMessage)m);
              continue;
            case 48:
              this.popularity_ = input.readInt64();
              this.bitField0_ |= 0x20;
              continue;
            case 56:
              this.totalUser_ = input.readInt64();
              this.bitField0_ |= 0x40;
              continue;
            case 66:
              this.totalUserStr_ = input.readStringRequireUtf8();
              this.bitField0_ |= 0x80;
              continue;
            case 74:
              this.totalStr_ = input.readStringRequireUtf8();
              this.bitField0_ |= 0x100;
              continue;
            case 82:
              this.onlineUserForAnchor_ = input.readStringRequireUtf8();
              this.bitField0_ |= 0x200;
              continue;
            case 90:
              this.totalPvForAnchor_ = input.readStringRequireUtf8();
              this.bitField0_ |= 0x400;
              continue;
            case 98:
              this.upRightStatsStr_ = input.readStringRequireUtf8();
              this.bitField0_ |= 0x800;
              continue;
            case 106:
              this.upRightStatsStrComplete_ = input.readStringRequireUtf8();
              this.bitField0_ |= 0x1000;
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
    
    public boolean hasCommon() {
      return ((this.bitField0_ & 0x1) != 0);
    }
    
    public Common getCommon() {
      if (this.commonBuilder_ == null)
        return (this.common_ == null) ? Common.getDefaultInstance() : this.common_; 
      return (Common)this.commonBuilder_.getMessage();
    }
    
    public Builder setCommon(Common value) {
      if (this.commonBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        this.common_ = value;
      } else {
        this.commonBuilder_.setMessage((AbstractMessage)value);
      } 
      this.bitField0_ |= 0x1;
      onChanged();
      return this;
    }
    
    public Builder setCommon(Common.Builder builderForValue) {
      if (this.commonBuilder_ == null) {
        this.common_ = builderForValue.build();
      } else {
        this.commonBuilder_.setMessage((AbstractMessage)builderForValue.build());
      } 
      this.bitField0_ |= 0x1;
      onChanged();
      return this;
    }
    
    public Builder mergeCommon(Common value) {
      if (this.commonBuilder_ == null) {
        if ((this.bitField0_ & 0x1) != 0 && this.common_ != null && this.common_ != Common.getDefaultInstance()) {
          getCommonBuilder().mergeFrom(value);
        } else {
          this.common_ = value;
        } 
      } else {
        this.commonBuilder_.mergeFrom((AbstractMessage)value);
      } 
      if (this.common_ != null) {
        this.bitField0_ |= 0x1;
        onChanged();
      } 
      return this;
    }
    
    public Builder clearCommon() {
      this.bitField0_ &= 0xFFFFFFFE;
      this.common_ = null;
      if (this.commonBuilder_ != null) {
        this.commonBuilder_.dispose();
        this.commonBuilder_ = null;
      } 
      onChanged();
      return this;
    }
    
    public Common.Builder getCommonBuilder() {
      this.bitField0_ |= 0x1;
      onChanged();
      return (Common.Builder)getCommonFieldBuilder().getBuilder();
    }
    
    public CommonOrBuilder getCommonOrBuilder() {
      if (this.commonBuilder_ != null)
        return (CommonOrBuilder)this.commonBuilder_.getMessageOrBuilder(); 
      return (this.common_ == null) ? Common.getDefaultInstance() : this.common_;
    }
    
    private SingleFieldBuilderV3<Common, Common.Builder, CommonOrBuilder> getCommonFieldBuilder() {
      if (this.commonBuilder_ == null) {
        this.commonBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getCommon(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.common_ = null;
      } 
      return this.commonBuilder_;
    }
    
    private void ensureRanksListIsMutable() {
      if ((this.bitField0_ & 0x2) == 0) {
        this.ranksList_ = new ArrayList<>(this.ranksList_);
        this.bitField0_ |= 0x2;
      } 
    }
    
    public List<RoomUserSeqMessageContributor> getRanksListList() {
      if (this.ranksListBuilder_ == null)
        return Collections.unmodifiableList(this.ranksList_); 
      return this.ranksListBuilder_.getMessageList();
    }
    
    public int getRanksListCount() {
      if (this.ranksListBuilder_ == null)
        return this.ranksList_.size(); 
      return this.ranksListBuilder_.getCount();
    }
    
    public RoomUserSeqMessageContributor getRanksList(int index) {
      if (this.ranksListBuilder_ == null)
        return this.ranksList_.get(index); 
      return (RoomUserSeqMessageContributor)this.ranksListBuilder_.getMessage(index);
    }
    
    public Builder setRanksList(int index, RoomUserSeqMessageContributor value) {
      if (this.ranksListBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        ensureRanksListIsMutable();
        this.ranksList_.set(index, value);
        onChanged();
      } else {
        this.ranksListBuilder_.setMessage(index, (AbstractMessage)value);
      } 
      return this;
    }
    
    public Builder setRanksList(int index, RoomUserSeqMessageContributor.Builder builderForValue) {
      if (this.ranksListBuilder_ == null) {
        ensureRanksListIsMutable();
        this.ranksList_.set(index, builderForValue.build());
        onChanged();
      } else {
        this.ranksListBuilder_.setMessage(index, (AbstractMessage)builderForValue.build());
      } 
      return this;
    }
    
    public Builder addRanksList(RoomUserSeqMessageContributor value) {
      if (this.ranksListBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        ensureRanksListIsMutable();
        this.ranksList_.add(value);
        onChanged();
      } else {
        this.ranksListBuilder_.addMessage((AbstractMessage)value);
      } 
      return this;
    }
    
    public Builder addRanksList(int index, RoomUserSeqMessageContributor value) {
      if (this.ranksListBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        ensureRanksListIsMutable();
        this.ranksList_.add(index, value);
        onChanged();
      } else {
        this.ranksListBuilder_.addMessage(index, (AbstractMessage)value);
      } 
      return this;
    }
    
    public Builder addRanksList(RoomUserSeqMessageContributor.Builder builderForValue) {
      if (this.ranksListBuilder_ == null) {
        ensureRanksListIsMutable();
        this.ranksList_.add(builderForValue.build());
        onChanged();
      } else {
        this.ranksListBuilder_.addMessage((AbstractMessage)builderForValue.build());
      } 
      return this;
    }
    
    public Builder addRanksList(int index, RoomUserSeqMessageContributor.Builder builderForValue) {
      if (this.ranksListBuilder_ == null) {
        ensureRanksListIsMutable();
        this.ranksList_.add(index, builderForValue.build());
        onChanged();
      } else {
        this.ranksListBuilder_.addMessage(index, (AbstractMessage)builderForValue.build());
      } 
      return this;
    }
    
    public Builder addAllRanksList(Iterable<? extends RoomUserSeqMessageContributor> values) {
      if (this.ranksListBuilder_ == null) {
        ensureRanksListIsMutable();
        AbstractMessageLite.Builder.addAll(values, this.ranksList_);
        onChanged();
      } else {
        this.ranksListBuilder_.addAllMessages(values);
      } 
      return this;
    }
    
    public Builder clearRanksList() {
      if (this.ranksListBuilder_ == null) {
        this.ranksList_ = Collections.emptyList();
        this.bitField0_ &= 0xFFFFFFFD;
        onChanged();
      } else {
        this.ranksListBuilder_.clear();
      } 
      return this;
    }
    
    public Builder removeRanksList(int index) {
      if (this.ranksListBuilder_ == null) {
        ensureRanksListIsMutable();
        this.ranksList_.remove(index);
        onChanged();
      } else {
        this.ranksListBuilder_.remove(index);
      } 
      return this;
    }
    
    public RoomUserSeqMessageContributor.Builder getRanksListBuilder(int index) {
      return (RoomUserSeqMessageContributor.Builder)getRanksListFieldBuilder().getBuilder(index);
    }
    
    public RoomUserSeqMessageContributorOrBuilder getRanksListOrBuilder(int index) {
      if (this.ranksListBuilder_ == null)
        return this.ranksList_.get(index); 
      return (RoomUserSeqMessageContributorOrBuilder)this.ranksListBuilder_.getMessageOrBuilder(index);
    }
    
    public List<? extends RoomUserSeqMessageContributorOrBuilder> getRanksListOrBuilderList() {
      if (this.ranksListBuilder_ != null)
        return this.ranksListBuilder_.getMessageOrBuilderList(); 
      return Collections.unmodifiableList((List)this.ranksList_);
    }
    
    public RoomUserSeqMessageContributor.Builder addRanksListBuilder() {
      return (RoomUserSeqMessageContributor.Builder)getRanksListFieldBuilder().addBuilder((AbstractMessage)RoomUserSeqMessageContributor.getDefaultInstance());
    }
    
    public RoomUserSeqMessageContributor.Builder addRanksListBuilder(int index) {
      return (RoomUserSeqMessageContributor.Builder)getRanksListFieldBuilder().addBuilder(index, (AbstractMessage)RoomUserSeqMessageContributor.getDefaultInstance());
    }
    
    public List<RoomUserSeqMessageContributor.Builder> getRanksListBuilderList() {
      return getRanksListFieldBuilder().getBuilderList();
    }
    
    private RepeatedFieldBuilderV3<RoomUserSeqMessageContributor, RoomUserSeqMessageContributor.Builder, RoomUserSeqMessageContributorOrBuilder> getRanksListFieldBuilder() {
      if (this.ranksListBuilder_ == null) {
        this.ranksListBuilder_ = new RepeatedFieldBuilderV3(this.ranksList_, ((this.bitField0_ & 0x2) != 0), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.ranksList_ = null;
      } 
      return this.ranksListBuilder_;
    }
    
    public long getTotal() {
      return this.total_;
    }
    
    public Builder setTotal(long value) {
      this.total_ = value;
      this.bitField0_ |= 0x4;
      onChanged();
      return this;
    }
    
    public Builder clearTotal() {
      this.bitField0_ &= 0xFFFFFFFB;
      this.total_ = 0L;
      onChanged();
      return this;
    }
    
    public String getPopStr() {
      Object ref = this.popStr_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.popStr_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getPopStrBytes() {
      Object ref = this.popStr_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.popStr_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setPopStr(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.popStr_ = value;
      this.bitField0_ |= 0x8;
      onChanged();
      return this;
    }
    
    public Builder clearPopStr() {
      this.popStr_ = RoomUserSeqMessage.getDefaultInstance().getPopStr();
      this.bitField0_ &= 0xFFFFFFF7;
      onChanged();
      return this;
    }
    
    public Builder setPopStrBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      RoomUserSeqMessage.checkByteStringIsUtf8(value);
      this.popStr_ = value;
      this.bitField0_ |= 0x8;
      onChanged();
      return this;
    }
    
    private void ensureSeatsListIsMutable() {
      if ((this.bitField0_ & 0x10) == 0) {
        this.seatsList_ = new ArrayList<>(this.seatsList_);
        this.bitField0_ |= 0x10;
      } 
    }
    
    public List<RoomUserSeqMessageContributor> getSeatsListList() {
      if (this.seatsListBuilder_ == null)
        return Collections.unmodifiableList(this.seatsList_); 
      return this.seatsListBuilder_.getMessageList();
    }
    
    public int getSeatsListCount() {
      if (this.seatsListBuilder_ == null)
        return this.seatsList_.size(); 
      return this.seatsListBuilder_.getCount();
    }
    
    public RoomUserSeqMessageContributor getSeatsList(int index) {
      if (this.seatsListBuilder_ == null)
        return this.seatsList_.get(index); 
      return (RoomUserSeqMessageContributor)this.seatsListBuilder_.getMessage(index);
    }
    
    public Builder setSeatsList(int index, RoomUserSeqMessageContributor value) {
      if (this.seatsListBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        ensureSeatsListIsMutable();
        this.seatsList_.set(index, value);
        onChanged();
      } else {
        this.seatsListBuilder_.setMessage(index, (AbstractMessage)value);
      } 
      return this;
    }
    
    public Builder setSeatsList(int index, RoomUserSeqMessageContributor.Builder builderForValue) {
      if (this.seatsListBuilder_ == null) {
        ensureSeatsListIsMutable();
        this.seatsList_.set(index, builderForValue.build());
        onChanged();
      } else {
        this.seatsListBuilder_.setMessage(index, (AbstractMessage)builderForValue.build());
      } 
      return this;
    }
    
    public Builder addSeatsList(RoomUserSeqMessageContributor value) {
      if (this.seatsListBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        ensureSeatsListIsMutable();
        this.seatsList_.add(value);
        onChanged();
      } else {
        this.seatsListBuilder_.addMessage((AbstractMessage)value);
      } 
      return this;
    }
    
    public Builder addSeatsList(int index, RoomUserSeqMessageContributor value) {
      if (this.seatsListBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        ensureSeatsListIsMutable();
        this.seatsList_.add(index, value);
        onChanged();
      } else {
        this.seatsListBuilder_.addMessage(index, (AbstractMessage)value);
      } 
      return this;
    }
    
    public Builder addSeatsList(RoomUserSeqMessageContributor.Builder builderForValue) {
      if (this.seatsListBuilder_ == null) {
        ensureSeatsListIsMutable();
        this.seatsList_.add(builderForValue.build());
        onChanged();
      } else {
        this.seatsListBuilder_.addMessage((AbstractMessage)builderForValue.build());
      } 
      return this;
    }
    
    public Builder addSeatsList(int index, RoomUserSeqMessageContributor.Builder builderForValue) {
      if (this.seatsListBuilder_ == null) {
        ensureSeatsListIsMutable();
        this.seatsList_.add(index, builderForValue.build());
        onChanged();
      } else {
        this.seatsListBuilder_.addMessage(index, (AbstractMessage)builderForValue.build());
      } 
      return this;
    }
    
    public Builder addAllSeatsList(Iterable<? extends RoomUserSeqMessageContributor> values) {
      if (this.seatsListBuilder_ == null) {
        ensureSeatsListIsMutable();
        AbstractMessageLite.Builder.addAll(values, this.seatsList_);
        onChanged();
      } else {
        this.seatsListBuilder_.addAllMessages(values);
      } 
      return this;
    }
    
    public Builder clearSeatsList() {
      if (this.seatsListBuilder_ == null) {
        this.seatsList_ = Collections.emptyList();
        this.bitField0_ &= 0xFFFFFFEF;
        onChanged();
      } else {
        this.seatsListBuilder_.clear();
      } 
      return this;
    }
    
    public Builder removeSeatsList(int index) {
      if (this.seatsListBuilder_ == null) {
        ensureSeatsListIsMutable();
        this.seatsList_.remove(index);
        onChanged();
      } else {
        this.seatsListBuilder_.remove(index);
      } 
      return this;
    }
    
    public RoomUserSeqMessageContributor.Builder getSeatsListBuilder(int index) {
      return (RoomUserSeqMessageContributor.Builder)getSeatsListFieldBuilder().getBuilder(index);
    }
    
    public RoomUserSeqMessageContributorOrBuilder getSeatsListOrBuilder(int index) {
      if (this.seatsListBuilder_ == null)
        return this.seatsList_.get(index); 
      return (RoomUserSeqMessageContributorOrBuilder)this.seatsListBuilder_.getMessageOrBuilder(index);
    }
    
    public List<? extends RoomUserSeqMessageContributorOrBuilder> getSeatsListOrBuilderList() {
      if (this.seatsListBuilder_ != null)
        return this.seatsListBuilder_.getMessageOrBuilderList(); 
      return Collections.unmodifiableList((List)this.seatsList_);
    }
    
    public RoomUserSeqMessageContributor.Builder addSeatsListBuilder() {
      return (RoomUserSeqMessageContributor.Builder)getSeatsListFieldBuilder().addBuilder((AbstractMessage)RoomUserSeqMessageContributor.getDefaultInstance());
    }
    
    public RoomUserSeqMessageContributor.Builder addSeatsListBuilder(int index) {
      return (RoomUserSeqMessageContributor.Builder)getSeatsListFieldBuilder().addBuilder(index, (AbstractMessage)RoomUserSeqMessageContributor.getDefaultInstance());
    }
    
    public List<RoomUserSeqMessageContributor.Builder> getSeatsListBuilderList() {
      return getSeatsListFieldBuilder().getBuilderList();
    }
    
    private RepeatedFieldBuilderV3<RoomUserSeqMessageContributor, RoomUserSeqMessageContributor.Builder, RoomUserSeqMessageContributorOrBuilder> getSeatsListFieldBuilder() {
      if (this.seatsListBuilder_ == null) {
        this.seatsListBuilder_ = new RepeatedFieldBuilderV3(this.seatsList_, ((this.bitField0_ & 0x10) != 0), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.seatsList_ = null;
      } 
      return this.seatsListBuilder_;
    }
    
    public long getPopularity() {
      return this.popularity_;
    }
    
    public Builder setPopularity(long value) {
      this.popularity_ = value;
      this.bitField0_ |= 0x20;
      onChanged();
      return this;
    }
    
    public Builder clearPopularity() {
      this.bitField0_ &= 0xFFFFFFDF;
      this.popularity_ = 0L;
      onChanged();
      return this;
    }
    
    public long getTotalUser() {
      return this.totalUser_;
    }
    
    public Builder setTotalUser(long value) {
      this.totalUser_ = value;
      this.bitField0_ |= 0x40;
      onChanged();
      return this;
    }
    
    public Builder clearTotalUser() {
      this.bitField0_ &= 0xFFFFFFBF;
      this.totalUser_ = 0L;
      onChanged();
      return this;
    }
    
    public String getTotalUserStr() {
      Object ref = this.totalUserStr_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.totalUserStr_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getTotalUserStrBytes() {
      Object ref = this.totalUserStr_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.totalUserStr_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setTotalUserStr(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.totalUserStr_ = value;
      this.bitField0_ |= 0x80;
      onChanged();
      return this;
    }
    
    public Builder clearTotalUserStr() {
      this.totalUserStr_ = RoomUserSeqMessage.getDefaultInstance().getTotalUserStr();
      this.bitField0_ &= 0xFFFFFF7F;
      onChanged();
      return this;
    }
    
    public Builder setTotalUserStrBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      RoomUserSeqMessage.checkByteStringIsUtf8(value);
      this.totalUserStr_ = value;
      this.bitField0_ |= 0x80;
      onChanged();
      return this;
    }
    
    public String getTotalStr() {
      Object ref = this.totalStr_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.totalStr_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getTotalStrBytes() {
      Object ref = this.totalStr_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.totalStr_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setTotalStr(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.totalStr_ = value;
      this.bitField0_ |= 0x100;
      onChanged();
      return this;
    }
    
    public Builder clearTotalStr() {
      this.totalStr_ = RoomUserSeqMessage.getDefaultInstance().getTotalStr();
      this.bitField0_ &= 0xFFFFFEFF;
      onChanged();
      return this;
    }
    
    public Builder setTotalStrBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      RoomUserSeqMessage.checkByteStringIsUtf8(value);
      this.totalStr_ = value;
      this.bitField0_ |= 0x100;
      onChanged();
      return this;
    }
    
    public String getOnlineUserForAnchor() {
      Object ref = this.onlineUserForAnchor_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.onlineUserForAnchor_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getOnlineUserForAnchorBytes() {
      Object ref = this.onlineUserForAnchor_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.onlineUserForAnchor_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setOnlineUserForAnchor(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.onlineUserForAnchor_ = value;
      this.bitField0_ |= 0x200;
      onChanged();
      return this;
    }
    
    public Builder clearOnlineUserForAnchor() {
      this.onlineUserForAnchor_ = RoomUserSeqMessage.getDefaultInstance().getOnlineUserForAnchor();
      this.bitField0_ &= 0xFFFFFDFF;
      onChanged();
      return this;
    }
    
    public Builder setOnlineUserForAnchorBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      RoomUserSeqMessage.checkByteStringIsUtf8(value);
      this.onlineUserForAnchor_ = value;
      this.bitField0_ |= 0x200;
      onChanged();
      return this;
    }
    
    public String getTotalPvForAnchor() {
      Object ref = this.totalPvForAnchor_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.totalPvForAnchor_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getTotalPvForAnchorBytes() {
      Object ref = this.totalPvForAnchor_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.totalPvForAnchor_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setTotalPvForAnchor(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.totalPvForAnchor_ = value;
      this.bitField0_ |= 0x400;
      onChanged();
      return this;
    }
    
    public Builder clearTotalPvForAnchor() {
      this.totalPvForAnchor_ = RoomUserSeqMessage.getDefaultInstance().getTotalPvForAnchor();
      this.bitField0_ &= 0xFFFFFBFF;
      onChanged();
      return this;
    }
    
    public Builder setTotalPvForAnchorBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      RoomUserSeqMessage.checkByteStringIsUtf8(value);
      this.totalPvForAnchor_ = value;
      this.bitField0_ |= 0x400;
      onChanged();
      return this;
    }
    
    public String getUpRightStatsStr() {
      Object ref = this.upRightStatsStr_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.upRightStatsStr_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getUpRightStatsStrBytes() {
      Object ref = this.upRightStatsStr_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.upRightStatsStr_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setUpRightStatsStr(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.upRightStatsStr_ = value;
      this.bitField0_ |= 0x800;
      onChanged();
      return this;
    }
    
    public Builder clearUpRightStatsStr() {
      this.upRightStatsStr_ = RoomUserSeqMessage.getDefaultInstance().getUpRightStatsStr();
      this.bitField0_ &= 0xFFFFF7FF;
      onChanged();
      return this;
    }
    
    public Builder setUpRightStatsStrBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      RoomUserSeqMessage.checkByteStringIsUtf8(value);
      this.upRightStatsStr_ = value;
      this.bitField0_ |= 0x800;
      onChanged();
      return this;
    }
    
    public String getUpRightStatsStrComplete() {
      Object ref = this.upRightStatsStrComplete_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.upRightStatsStrComplete_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getUpRightStatsStrCompleteBytes() {
      Object ref = this.upRightStatsStrComplete_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.upRightStatsStrComplete_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setUpRightStatsStrComplete(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.upRightStatsStrComplete_ = value;
      this.bitField0_ |= 0x1000;
      onChanged();
      return this;
    }
    
    public Builder clearUpRightStatsStrComplete() {
      this.upRightStatsStrComplete_ = RoomUserSeqMessage.getDefaultInstance().getUpRightStatsStrComplete();
      this.bitField0_ &= 0xFFFFEFFF;
      onChanged();
      return this;
    }
    
    public Builder setUpRightStatsStrCompleteBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      RoomUserSeqMessage.checkByteStringIsUtf8(value);
      this.upRightStatsStrComplete_ = value;
      this.bitField0_ |= 0x1000;
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
  
  private static final RoomUserSeqMessage DEFAULT_INSTANCE = new RoomUserSeqMessage();
  
  public static RoomUserSeqMessage getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }
  
  private static final Parser<RoomUserSeqMessage> PARSER = (Parser<RoomUserSeqMessage>)new AbstractParser<RoomUserSeqMessage>() {
      public RoomUserSeqMessage parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        RoomUserSeqMessage.Builder builder = RoomUserSeqMessage.newBuilder();
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
  
  public static Parser<RoomUserSeqMessage> parser() {
    return PARSER;
  }
  
  public Parser<RoomUserSeqMessage> getParserForType() {
    return PARSER;
  }
  
  public RoomUserSeqMessage getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }
}
