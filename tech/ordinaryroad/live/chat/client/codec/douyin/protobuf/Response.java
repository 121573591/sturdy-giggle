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
import com.google.protobuf.MapEntry;
import com.google.protobuf.MapField;
import com.google.protobuf.MapFieldReflectionAccessor;
import com.google.protobuf.Message;
import com.google.protobuf.MessageLite;
import com.google.protobuf.Parser;
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import com.google.protobuf.WireFormat;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public final class Response extends GeneratedMessageV3 implements ResponseOrBuilder {
  private static final long serialVersionUID = 0L;
  
  public static final int MESSAGESLIST_FIELD_NUMBER = 1;
  
  private List<Message> messagesList_;
  
  public static final int CURSOR_FIELD_NUMBER = 2;
  
  private volatile Object cursor_;
  
  public static final int FETCHINTERVAL_FIELD_NUMBER = 3;
  
  private long fetchInterval_;
  
  public static final int NOW_FIELD_NUMBER = 4;
  
  private long now_;
  
  public static final int INTERNALEXT_FIELD_NUMBER = 5;
  
  private volatile Object internalExt_;
  
  public static final int FETCHTYPE_FIELD_NUMBER = 6;
  
  private int fetchType_;
  
  public static final int ROUTEPARAMS_FIELD_NUMBER = 7;
  
  private MapField<String, String> routeParams_;
  
  public static final int HEARTBEATDURATION_FIELD_NUMBER = 8;
  
  private long heartbeatDuration_;
  
  public static final int NEEDACK_FIELD_NUMBER = 9;
  
  private boolean needAck_;
  
  public static final int PUSHSERVER_FIELD_NUMBER = 10;
  
  private volatile Object pushServer_;
  
  public static final int LIVECURSOR_FIELD_NUMBER = 11;
  
  private volatile Object liveCursor_;
  
  public static final int HISTORYNOMORE_FIELD_NUMBER = 12;
  
  private boolean historyNoMore_;
  
  private byte memoizedIsInitialized;
  
  private Response(GeneratedMessageV3.Builder<?> builder) {
    super(builder);
    this.cursor_ = "";
    this.fetchInterval_ = 0L;
    this.now_ = 0L;
    this.internalExt_ = "";
    this.fetchType_ = 0;
    this.heartbeatDuration_ = 0L;
    this.needAck_ = false;
    this.pushServer_ = "";
    this.liveCursor_ = "";
    this.historyNoMore_ = false;
    this.memoizedIsInitialized = -1;
  }
  
  private Response() {
    this.cursor_ = "";
    this.fetchInterval_ = 0L;
    this.now_ = 0L;
    this.internalExt_ = "";
    this.fetchType_ = 0;
    this.heartbeatDuration_ = 0L;
    this.needAck_ = false;
    this.pushServer_ = "";
    this.liveCursor_ = "";
    this.historyNoMore_ = false;
    this.memoizedIsInitialized = -1;
    this.messagesList_ = Collections.emptyList();
    this.cursor_ = "";
    this.internalExt_ = "";
    this.pushServer_ = "";
    this.liveCursor_ = "";
  }
  
  protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
    return new Response();
  }
  
  public static final Descriptors.Descriptor getDescriptor() {
    return Douyin.internal_static_Response_descriptor;
  }
  
  protected MapFieldReflectionAccessor internalGetMapFieldReflection(int number) {
    switch (number) {
      case 7:
        return (MapFieldReflectionAccessor)internalGetRouteParams();
    } 
    throw new RuntimeException("Invalid map field number: " + number);
  }
  
  protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
    return Douyin.internal_static_Response_fieldAccessorTable.ensureFieldAccessorsInitialized(Response.class, Builder.class);
  }
  
  public List<Message> getMessagesListList() {
    return this.messagesList_;
  }
  
  public List<? extends MessageOrBuilder> getMessagesListOrBuilderList() {
    return (List)this.messagesList_;
  }
  
  public int getMessagesListCount() {
    return this.messagesList_.size();
  }
  
  public Message getMessagesList(int index) {
    return this.messagesList_.get(index);
  }
  
  public MessageOrBuilder getMessagesListOrBuilder(int index) {
    return this.messagesList_.get(index);
  }
  
  public String getCursor() {
    Object ref = this.cursor_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.cursor_ = s;
    return s;
  }
  
  public ByteString getCursorBytes() {
    Object ref = this.cursor_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.cursor_ = b;
      return b;
    } 
    return (ByteString)ref;
  }
  
  public long getFetchInterval() {
    return this.fetchInterval_;
  }
  
  public long getNow() {
    return this.now_;
  }
  
  public String getInternalExt() {
    Object ref = this.internalExt_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.internalExt_ = s;
    return s;
  }
  
  public ByteString getInternalExtBytes() {
    Object ref = this.internalExt_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.internalExt_ = b;
      return b;
    } 
    return (ByteString)ref;
  }
  
  public int getFetchType() {
    return this.fetchType_;
  }
  
  private static final class RouteParamsDefaultEntryHolder {
    static final MapEntry<String, String> defaultEntry = MapEntry.newDefaultInstance(Douyin.internal_static_Response_RouteParamsEntry_descriptor, WireFormat.FieldType.STRING, "", WireFormat.FieldType.STRING, "");
  }
  
  private MapField<String, String> internalGetRouteParams() {
    if (this.routeParams_ == null)
      return MapField.emptyMapField(RouteParamsDefaultEntryHolder.defaultEntry); 
    return this.routeParams_;
  }
  
  public int getRouteParamsCount() {
    return internalGetRouteParams().getMap().size();
  }
  
  public boolean containsRouteParams(String key) {
    if (key == null)
      throw new NullPointerException("map key"); 
    return internalGetRouteParams().getMap().containsKey(key);
  }
  
  @Deprecated
  public Map<String, String> getRouteParams() {
    return getRouteParamsMap();
  }
  
  public Map<String, String> getRouteParamsMap() {
    return internalGetRouteParams().getMap();
  }
  
  public String getRouteParamsOrDefault(String key, String defaultValue) {
    if (key == null)
      throw new NullPointerException("map key"); 
    Map<String, String> map = internalGetRouteParams().getMap();
    return map.containsKey(key) ? map.get(key) : defaultValue;
  }
  
  public String getRouteParamsOrThrow(String key) {
    if (key == null)
      throw new NullPointerException("map key"); 
    Map<String, String> map = internalGetRouteParams().getMap();
    if (!map.containsKey(key))
      throw new IllegalArgumentException(); 
    return map.get(key);
  }
  
  public long getHeartbeatDuration() {
    return this.heartbeatDuration_;
  }
  
  public boolean getNeedAck() {
    return this.needAck_;
  }
  
  public String getPushServer() {
    Object ref = this.pushServer_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.pushServer_ = s;
    return s;
  }
  
  public ByteString getPushServerBytes() {
    Object ref = this.pushServer_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.pushServer_ = b;
      return b;
    } 
    return (ByteString)ref;
  }
  
  public String getLiveCursor() {
    Object ref = this.liveCursor_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.liveCursor_ = s;
    return s;
  }
  
  public ByteString getLiveCursorBytes() {
    Object ref = this.liveCursor_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.liveCursor_ = b;
      return b;
    } 
    return (ByteString)ref;
  }
  
  public boolean getHistoryNoMore() {
    return this.historyNoMore_;
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
    for (int i = 0; i < this.messagesList_.size(); i++)
      output.writeMessage(1, (MessageLite)this.messagesList_.get(i)); 
    if (!GeneratedMessageV3.isStringEmpty(this.cursor_))
      GeneratedMessageV3.writeString(output, 2, this.cursor_); 
    if (this.fetchInterval_ != 0L)
      output.writeUInt64(3, this.fetchInterval_); 
    if (this.now_ != 0L)
      output.writeUInt64(4, this.now_); 
    if (!GeneratedMessageV3.isStringEmpty(this.internalExt_))
      GeneratedMessageV3.writeString(output, 5, this.internalExt_); 
    if (this.fetchType_ != 0)
      output.writeUInt32(6, this.fetchType_); 
    GeneratedMessageV3.serializeStringMapTo(output, 
        
        internalGetRouteParams(), RouteParamsDefaultEntryHolder.defaultEntry, 7);
    if (this.heartbeatDuration_ != 0L)
      output.writeUInt64(8, this.heartbeatDuration_); 
    if (this.needAck_)
      output.writeBool(9, this.needAck_); 
    if (!GeneratedMessageV3.isStringEmpty(this.pushServer_))
      GeneratedMessageV3.writeString(output, 10, this.pushServer_); 
    if (!GeneratedMessageV3.isStringEmpty(this.liveCursor_))
      GeneratedMessageV3.writeString(output, 11, this.liveCursor_); 
    if (this.historyNoMore_)
      output.writeBool(12, this.historyNoMore_); 
    getUnknownFields().writeTo(output);
  }
  
  public int getSerializedSize() {
    int size = this.memoizedSize;
    if (size != -1)
      return size; 
    size = 0;
    for (int i = 0; i < this.messagesList_.size(); i++)
      size += 
        CodedOutputStream.computeMessageSize(1, (MessageLite)this.messagesList_.get(i)); 
    if (!GeneratedMessageV3.isStringEmpty(this.cursor_))
      size += GeneratedMessageV3.computeStringSize(2, this.cursor_); 
    if (this.fetchInterval_ != 0L)
      size += 
        CodedOutputStream.computeUInt64Size(3, this.fetchInterval_); 
    if (this.now_ != 0L)
      size += 
        CodedOutputStream.computeUInt64Size(4, this.now_); 
    if (!GeneratedMessageV3.isStringEmpty(this.internalExt_))
      size += GeneratedMessageV3.computeStringSize(5, this.internalExt_); 
    if (this.fetchType_ != 0)
      size += 
        CodedOutputStream.computeUInt32Size(6, this.fetchType_); 
    for (Map.Entry<String, String> entry : (Iterable<Map.Entry<String, String>>)internalGetRouteParams().getMap().entrySet()) {
      MapEntry<String, String> routeParams__ = RouteParamsDefaultEntryHolder.defaultEntry.newBuilderForType().setKey(entry.getKey()).setValue(entry.getValue()).build();
      size += 
        CodedOutputStream.computeMessageSize(7, (MessageLite)routeParams__);
    } 
    if (this.heartbeatDuration_ != 0L)
      size += 
        CodedOutputStream.computeUInt64Size(8, this.heartbeatDuration_); 
    if (this.needAck_)
      size += 
        CodedOutputStream.computeBoolSize(9, this.needAck_); 
    if (!GeneratedMessageV3.isStringEmpty(this.pushServer_))
      size += GeneratedMessageV3.computeStringSize(10, this.pushServer_); 
    if (!GeneratedMessageV3.isStringEmpty(this.liveCursor_))
      size += GeneratedMessageV3.computeStringSize(11, this.liveCursor_); 
    if (this.historyNoMore_)
      size += 
        CodedOutputStream.computeBoolSize(12, this.historyNoMore_); 
    size += getUnknownFields().getSerializedSize();
    this.memoizedSize = size;
    return size;
  }
  
  public boolean equals(Object obj) {
    if (obj == this)
      return true; 
    if (!(obj instanceof Response))
      return super.equals(obj); 
    Response other = (Response)obj;
    if (!getMessagesListList().equals(other.getMessagesListList()))
      return false; 
    if (!getCursor().equals(other.getCursor()))
      return false; 
    if (getFetchInterval() != other
      .getFetchInterval())
      return false; 
    if (getNow() != other
      .getNow())
      return false; 
    if (!getInternalExt().equals(other.getInternalExt()))
      return false; 
    if (getFetchType() != other
      .getFetchType())
      return false; 
    if (!internalGetRouteParams().equals(other
        .internalGetRouteParams()))
      return false; 
    if (getHeartbeatDuration() != other
      .getHeartbeatDuration())
      return false; 
    if (getNeedAck() != other
      .getNeedAck())
      return false; 
    if (!getPushServer().equals(other.getPushServer()))
      return false; 
    if (!getLiveCursor().equals(other.getLiveCursor()))
      return false; 
    if (getHistoryNoMore() != other
      .getHistoryNoMore())
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
    if (getMessagesListCount() > 0) {
      hash = 37 * hash + 1;
      hash = 53 * hash + getMessagesListList().hashCode();
    } 
    hash = 37 * hash + 2;
    hash = 53 * hash + getCursor().hashCode();
    hash = 37 * hash + 3;
    hash = 53 * hash + Internal.hashLong(
        getFetchInterval());
    hash = 37 * hash + 4;
    hash = 53 * hash + Internal.hashLong(
        getNow());
    hash = 37 * hash + 5;
    hash = 53 * hash + getInternalExt().hashCode();
    hash = 37 * hash + 6;
    hash = 53 * hash + getFetchType();
    if (!internalGetRouteParams().getMap().isEmpty()) {
      hash = 37 * hash + 7;
      hash = 53 * hash + internalGetRouteParams().hashCode();
    } 
    hash = 37 * hash + 8;
    hash = 53 * hash + Internal.hashLong(
        getHeartbeatDuration());
    hash = 37 * hash + 9;
    hash = 53 * hash + Internal.hashBoolean(
        getNeedAck());
    hash = 37 * hash + 10;
    hash = 53 * hash + getPushServer().hashCode();
    hash = 37 * hash + 11;
    hash = 53 * hash + getLiveCursor().hashCode();
    hash = 37 * hash + 12;
    hash = 53 * hash + Internal.hashBoolean(
        getHistoryNoMore());
    hash = 29 * hash + getUnknownFields().hashCode();
    this.memoizedHashCode = hash;
    return hash;
  }
  
  public static Response parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
    return (Response)PARSER.parseFrom(data);
  }
  
  public static Response parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (Response)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static Response parseFrom(ByteString data) throws InvalidProtocolBufferException {
    return (Response)PARSER.parseFrom(data);
  }
  
  public static Response parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (Response)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static Response parseFrom(byte[] data) throws InvalidProtocolBufferException {
    return (Response)PARSER.parseFrom(data);
  }
  
  public static Response parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (Response)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static Response parseFrom(InputStream input) throws IOException {
    return 
      (Response)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static Response parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (Response)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static Response parseDelimitedFrom(InputStream input) throws IOException {
    return 
      (Response)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
  }
  
  public static Response parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (Response)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static Response parseFrom(CodedInputStream input) throws IOException {
    return 
      (Response)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static Response parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (Response)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public Builder newBuilderForType() {
    return newBuilder();
  }
  
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  
  public static Builder newBuilder(Response prototype) {
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
  
  public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ResponseOrBuilder {
    private int bitField0_;
    
    private List<Message> messagesList_;
    
    private RepeatedFieldBuilderV3<Message, Message.Builder, MessageOrBuilder> messagesListBuilder_;
    
    private Object cursor_;
    
    private long fetchInterval_;
    
    private long now_;
    
    private Object internalExt_;
    
    private int fetchType_;
    
    private MapField<String, String> routeParams_;
    
    private long heartbeatDuration_;
    
    private boolean needAck_;
    
    private Object pushServer_;
    
    private Object liveCursor_;
    
    private boolean historyNoMore_;
    
    public static final Descriptors.Descriptor getDescriptor() {
      return Douyin.internal_static_Response_descriptor;
    }
    
    protected MapFieldReflectionAccessor internalGetMapFieldReflection(int number) {
      switch (number) {
        case 7:
          return (MapFieldReflectionAccessor)internalGetRouteParams();
      } 
      throw new RuntimeException("Invalid map field number: " + number);
    }
    
    protected MapFieldReflectionAccessor internalGetMutableMapFieldReflection(int number) {
      switch (number) {
        case 7:
          return (MapFieldReflectionAccessor)internalGetMutableRouteParams();
      } 
      throw new RuntimeException("Invalid map field number: " + number);
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return Douyin.internal_static_Response_fieldAccessorTable
        .ensureFieldAccessorsInitialized(Response.class, Builder.class);
    }
    
    private Builder() {
      this
        .messagesList_ = Collections.emptyList();
      this.cursor_ = "";
      this.internalExt_ = "";
      this.pushServer_ = "";
      this.liveCursor_ = "";
    }
    
    private Builder(GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      this.messagesList_ = Collections.emptyList();
      this.cursor_ = "";
      this.internalExt_ = "";
      this.pushServer_ = "";
      this.liveCursor_ = "";
    }
    
    public Builder clear() {
      super.clear();
      this.bitField0_ = 0;
      if (this.messagesListBuilder_ == null) {
        this.messagesList_ = Collections.emptyList();
      } else {
        this.messagesList_ = null;
        this.messagesListBuilder_.clear();
      } 
      this.bitField0_ &= 0xFFFFFFFE;
      this.cursor_ = "";
      this.fetchInterval_ = 0L;
      this.now_ = 0L;
      this.internalExt_ = "";
      this.fetchType_ = 0;
      internalGetMutableRouteParams().clear();
      this.heartbeatDuration_ = 0L;
      this.needAck_ = false;
      this.pushServer_ = "";
      this.liveCursor_ = "";
      this.historyNoMore_ = false;
      return this;
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
      return Douyin.internal_static_Response_descriptor;
    }
    
    public Response getDefaultInstanceForType() {
      return Response.getDefaultInstance();
    }
    
    public Response build() {
      Response result = buildPartial();
      if (!result.isInitialized())
        throw newUninitializedMessageException(result); 
      return result;
    }
    
    public Response buildPartial() {
      Response result = new Response(this);
      buildPartialRepeatedFields(result);
      if (this.bitField0_ != 0)
        buildPartial0(result); 
      onBuilt();
      return result;
    }
    
    private void buildPartialRepeatedFields(Response result) {
      if (this.messagesListBuilder_ == null) {
        if ((this.bitField0_ & 0x1) != 0) {
          this.messagesList_ = Collections.unmodifiableList(this.messagesList_);
          this.bitField0_ &= 0xFFFFFFFE;
        } 
        result.messagesList_ = this.messagesList_;
      } else {
        result.messagesList_ = this.messagesListBuilder_.build();
      } 
    }
    
    private void buildPartial0(Response result) {
      int from_bitField0_ = this.bitField0_;
      if ((from_bitField0_ & 0x2) != 0)
        result.cursor_ = this.cursor_; 
      if ((from_bitField0_ & 0x4) != 0)
        result.fetchInterval_ = this.fetchInterval_; 
      if ((from_bitField0_ & 0x8) != 0)
        result.now_ = this.now_; 
      if ((from_bitField0_ & 0x10) != 0)
        result.internalExt_ = this.internalExt_; 
      if ((from_bitField0_ & 0x20) != 0)
        result.fetchType_ = this.fetchType_; 
      if ((from_bitField0_ & 0x40) != 0) {
        result.routeParams_ = internalGetRouteParams();
        result.routeParams_.makeImmutable();
      } 
      if ((from_bitField0_ & 0x80) != 0)
        result.heartbeatDuration_ = this.heartbeatDuration_; 
      if ((from_bitField0_ & 0x100) != 0)
        result.needAck_ = this.needAck_; 
      if ((from_bitField0_ & 0x200) != 0)
        result.pushServer_ = this.pushServer_; 
      if ((from_bitField0_ & 0x400) != 0)
        result.liveCursor_ = this.liveCursor_; 
      if ((from_bitField0_ & 0x800) != 0)
        result.historyNoMore_ = this.historyNoMore_; 
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
      if (other instanceof Response)
        return mergeFrom((Response)other); 
      super.mergeFrom(other);
      return this;
    }
    
    public Builder mergeFrom(Response other) {
      if (other == Response.getDefaultInstance())
        return this; 
      if (this.messagesListBuilder_ == null) {
        if (!other.messagesList_.isEmpty()) {
          if (this.messagesList_.isEmpty()) {
            this.messagesList_ = other.messagesList_;
            this.bitField0_ &= 0xFFFFFFFE;
          } else {
            ensureMessagesListIsMutable();
            this.messagesList_.addAll(other.messagesList_);
          } 
          onChanged();
        } 
      } else if (!other.messagesList_.isEmpty()) {
        if (this.messagesListBuilder_.isEmpty()) {
          this.messagesListBuilder_.dispose();
          this.messagesListBuilder_ = null;
          this.messagesList_ = other.messagesList_;
          this.bitField0_ &= 0xFFFFFFFE;
          this.messagesListBuilder_ = Response.alwaysUseFieldBuilders ? getMessagesListFieldBuilder() : null;
        } else {
          this.messagesListBuilder_.addAllMessages(other.messagesList_);
        } 
      } 
      if (!other.getCursor().isEmpty()) {
        this.cursor_ = other.cursor_;
        this.bitField0_ |= 0x2;
        onChanged();
      } 
      if (other.getFetchInterval() != 0L)
        setFetchInterval(other.getFetchInterval()); 
      if (other.getNow() != 0L)
        setNow(other.getNow()); 
      if (!other.getInternalExt().isEmpty()) {
        this.internalExt_ = other.internalExt_;
        this.bitField0_ |= 0x10;
        onChanged();
      } 
      if (other.getFetchType() != 0)
        setFetchType(other.getFetchType()); 
      internalGetMutableRouteParams().mergeFrom(other.internalGetRouteParams());
      this.bitField0_ |= 0x40;
      if (other.getHeartbeatDuration() != 0L)
        setHeartbeatDuration(other.getHeartbeatDuration()); 
      if (other.getNeedAck())
        setNeedAck(other.getNeedAck()); 
      if (!other.getPushServer().isEmpty()) {
        this.pushServer_ = other.pushServer_;
        this.bitField0_ |= 0x200;
        onChanged();
      } 
      if (!other.getLiveCursor().isEmpty()) {
        this.liveCursor_ = other.liveCursor_;
        this.bitField0_ |= 0x400;
        onChanged();
      } 
      if (other.getHistoryNoMore())
        setHistoryNoMore(other.getHistoryNoMore()); 
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
          Message m;
          MapEntry<String, String> routeParams__;
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              continue;
            case 10:
              m = (Message)input.readMessage(Message.parser(), extensionRegistry);
              if (this.messagesListBuilder_ == null) {
                ensureMessagesListIsMutable();
                this.messagesList_.add(m);
                continue;
              } 
              this.messagesListBuilder_.addMessage((AbstractMessage)m);
              continue;
            case 18:
              this.cursor_ = input.readStringRequireUtf8();
              this.bitField0_ |= 0x2;
              continue;
            case 24:
              this.fetchInterval_ = input.readUInt64();
              this.bitField0_ |= 0x4;
              continue;
            case 32:
              this.now_ = input.readUInt64();
              this.bitField0_ |= 0x8;
              continue;
            case 42:
              this.internalExt_ = input.readStringRequireUtf8();
              this.bitField0_ |= 0x10;
              continue;
            case 48:
              this.fetchType_ = input.readUInt32();
              this.bitField0_ |= 0x20;
              continue;
            case 58:
              routeParams__ = (MapEntry<String, String>)input.readMessage(Response.RouteParamsDefaultEntryHolder.defaultEntry.getParserForType(), extensionRegistry);
              internalGetMutableRouteParams().getMutableMap().put((String)routeParams__.getKey(), (String)routeParams__.getValue());
              this.bitField0_ |= 0x40;
              continue;
            case 64:
              this.heartbeatDuration_ = input.readUInt64();
              this.bitField0_ |= 0x80;
              continue;
            case 72:
              this.needAck_ = input.readBool();
              this.bitField0_ |= 0x100;
              continue;
            case 82:
              this.pushServer_ = input.readStringRequireUtf8();
              this.bitField0_ |= 0x200;
              continue;
            case 90:
              this.liveCursor_ = input.readStringRequireUtf8();
              this.bitField0_ |= 0x400;
              continue;
            case 96:
              this.historyNoMore_ = input.readBool();
              this.bitField0_ |= 0x800;
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
    
    private void ensureMessagesListIsMutable() {
      if ((this.bitField0_ & 0x1) == 0) {
        this.messagesList_ = new ArrayList<>(this.messagesList_);
        this.bitField0_ |= 0x1;
      } 
    }
    
    public List<Message> getMessagesListList() {
      if (this.messagesListBuilder_ == null)
        return Collections.unmodifiableList(this.messagesList_); 
      return this.messagesListBuilder_.getMessageList();
    }
    
    public int getMessagesListCount() {
      if (this.messagesListBuilder_ == null)
        return this.messagesList_.size(); 
      return this.messagesListBuilder_.getCount();
    }
    
    public Message getMessagesList(int index) {
      if (this.messagesListBuilder_ == null)
        return this.messagesList_.get(index); 
      return (Message)this.messagesListBuilder_.getMessage(index);
    }
    
    public Builder setMessagesList(int index, Message value) {
      if (this.messagesListBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        ensureMessagesListIsMutable();
        this.messagesList_.set(index, value);
        onChanged();
      } else {
        this.messagesListBuilder_.setMessage(index, (AbstractMessage)value);
      } 
      return this;
    }
    
    public Builder setMessagesList(int index, Message.Builder builderForValue) {
      if (this.messagesListBuilder_ == null) {
        ensureMessagesListIsMutable();
        this.messagesList_.set(index, builderForValue.build());
        onChanged();
      } else {
        this.messagesListBuilder_.setMessage(index, (AbstractMessage)builderForValue.build());
      } 
      return this;
    }
    
    public Builder addMessagesList(Message value) {
      if (this.messagesListBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        ensureMessagesListIsMutable();
        this.messagesList_.add(value);
        onChanged();
      } else {
        this.messagesListBuilder_.addMessage((AbstractMessage)value);
      } 
      return this;
    }
    
    public Builder addMessagesList(int index, Message value) {
      if (this.messagesListBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        ensureMessagesListIsMutable();
        this.messagesList_.add(index, value);
        onChanged();
      } else {
        this.messagesListBuilder_.addMessage(index, (AbstractMessage)value);
      } 
      return this;
    }
    
    public Builder addMessagesList(Message.Builder builderForValue) {
      if (this.messagesListBuilder_ == null) {
        ensureMessagesListIsMutable();
        this.messagesList_.add(builderForValue.build());
        onChanged();
      } else {
        this.messagesListBuilder_.addMessage((AbstractMessage)builderForValue.build());
      } 
      return this;
    }
    
    public Builder addMessagesList(int index, Message.Builder builderForValue) {
      if (this.messagesListBuilder_ == null) {
        ensureMessagesListIsMutable();
        this.messagesList_.add(index, builderForValue.build());
        onChanged();
      } else {
        this.messagesListBuilder_.addMessage(index, (AbstractMessage)builderForValue.build());
      } 
      return this;
    }
    
    public Builder addAllMessagesList(Iterable<? extends Message> values) {
      if (this.messagesListBuilder_ == null) {
        ensureMessagesListIsMutable();
        AbstractMessageLite.Builder.addAll(values, this.messagesList_);
        onChanged();
      } else {
        this.messagesListBuilder_.addAllMessages(values);
      } 
      return this;
    }
    
    public Builder clearMessagesList() {
      if (this.messagesListBuilder_ == null) {
        this.messagesList_ = Collections.emptyList();
        this.bitField0_ &= 0xFFFFFFFE;
        onChanged();
      } else {
        this.messagesListBuilder_.clear();
      } 
      return this;
    }
    
    public Builder removeMessagesList(int index) {
      if (this.messagesListBuilder_ == null) {
        ensureMessagesListIsMutable();
        this.messagesList_.remove(index);
        onChanged();
      } else {
        this.messagesListBuilder_.remove(index);
      } 
      return this;
    }
    
    public Message.Builder getMessagesListBuilder(int index) {
      return (Message.Builder)getMessagesListFieldBuilder().getBuilder(index);
    }
    
    public MessageOrBuilder getMessagesListOrBuilder(int index) {
      if (this.messagesListBuilder_ == null)
        return this.messagesList_.get(index); 
      return (MessageOrBuilder)this.messagesListBuilder_.getMessageOrBuilder(index);
    }
    
    public List<? extends MessageOrBuilder> getMessagesListOrBuilderList() {
      if (this.messagesListBuilder_ != null)
        return this.messagesListBuilder_.getMessageOrBuilderList(); 
      return Collections.unmodifiableList((List)this.messagesList_);
    }
    
    public Message.Builder addMessagesListBuilder() {
      return (Message.Builder)getMessagesListFieldBuilder().addBuilder((AbstractMessage)Message.getDefaultInstance());
    }
    
    public Message.Builder addMessagesListBuilder(int index) {
      return (Message.Builder)getMessagesListFieldBuilder().addBuilder(index, (AbstractMessage)Message.getDefaultInstance());
    }
    
    public List<Message.Builder> getMessagesListBuilderList() {
      return getMessagesListFieldBuilder().getBuilderList();
    }
    
    private RepeatedFieldBuilderV3<Message, Message.Builder, MessageOrBuilder> getMessagesListFieldBuilder() {
      if (this.messagesListBuilder_ == null) {
        this.messagesListBuilder_ = new RepeatedFieldBuilderV3(this.messagesList_, ((this.bitField0_ & 0x1) != 0), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.messagesList_ = null;
      } 
      return this.messagesListBuilder_;
    }
    
    public String getCursor() {
      Object ref = this.cursor_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.cursor_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getCursorBytes() {
      Object ref = this.cursor_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.cursor_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setCursor(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.cursor_ = value;
      this.bitField0_ |= 0x2;
      onChanged();
      return this;
    }
    
    public Builder clearCursor() {
      this.cursor_ = Response.getDefaultInstance().getCursor();
      this.bitField0_ &= 0xFFFFFFFD;
      onChanged();
      return this;
    }
    
    public Builder setCursorBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      Response.checkByteStringIsUtf8(value);
      this.cursor_ = value;
      this.bitField0_ |= 0x2;
      onChanged();
      return this;
    }
    
    public long getFetchInterval() {
      return this.fetchInterval_;
    }
    
    public Builder setFetchInterval(long value) {
      this.fetchInterval_ = value;
      this.bitField0_ |= 0x4;
      onChanged();
      return this;
    }
    
    public Builder clearFetchInterval() {
      this.bitField0_ &= 0xFFFFFFFB;
      this.fetchInterval_ = 0L;
      onChanged();
      return this;
    }
    
    public long getNow() {
      return this.now_;
    }
    
    public Builder setNow(long value) {
      this.now_ = value;
      this.bitField0_ |= 0x8;
      onChanged();
      return this;
    }
    
    public Builder clearNow() {
      this.bitField0_ &= 0xFFFFFFF7;
      this.now_ = 0L;
      onChanged();
      return this;
    }
    
    public String getInternalExt() {
      Object ref = this.internalExt_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.internalExt_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getInternalExtBytes() {
      Object ref = this.internalExt_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.internalExt_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setInternalExt(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.internalExt_ = value;
      this.bitField0_ |= 0x10;
      onChanged();
      return this;
    }
    
    public Builder clearInternalExt() {
      this.internalExt_ = Response.getDefaultInstance().getInternalExt();
      this.bitField0_ &= 0xFFFFFFEF;
      onChanged();
      return this;
    }
    
    public Builder setInternalExtBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      Response.checkByteStringIsUtf8(value);
      this.internalExt_ = value;
      this.bitField0_ |= 0x10;
      onChanged();
      return this;
    }
    
    public int getFetchType() {
      return this.fetchType_;
    }
    
    public Builder setFetchType(int value) {
      this.fetchType_ = value;
      this.bitField0_ |= 0x20;
      onChanged();
      return this;
    }
    
    public Builder clearFetchType() {
      this.bitField0_ &= 0xFFFFFFDF;
      this.fetchType_ = 0;
      onChanged();
      return this;
    }
    
    private MapField<String, String> internalGetRouteParams() {
      if (this.routeParams_ == null)
        return MapField.emptyMapField(Response.RouteParamsDefaultEntryHolder.defaultEntry); 
      return this.routeParams_;
    }
    
    private MapField<String, String> internalGetMutableRouteParams() {
      if (this.routeParams_ == null)
        this.routeParams_ = MapField.newMapField(Response.RouteParamsDefaultEntryHolder.defaultEntry); 
      if (!this.routeParams_.isMutable())
        this.routeParams_ = this.routeParams_.copy(); 
      this.bitField0_ |= 0x40;
      onChanged();
      return this.routeParams_;
    }
    
    public int getRouteParamsCount() {
      return internalGetRouteParams().getMap().size();
    }
    
    public boolean containsRouteParams(String key) {
      if (key == null)
        throw new NullPointerException("map key"); 
      return internalGetRouteParams().getMap().containsKey(key);
    }
    
    @Deprecated
    public Map<String, String> getRouteParams() {
      return getRouteParamsMap();
    }
    
    public Map<String, String> getRouteParamsMap() {
      return internalGetRouteParams().getMap();
    }
    
    public String getRouteParamsOrDefault(String key, String defaultValue) {
      if (key == null)
        throw new NullPointerException("map key"); 
      Map<String, String> map = internalGetRouteParams().getMap();
      return map.containsKey(key) ? map.get(key) : defaultValue;
    }
    
    public String getRouteParamsOrThrow(String key) {
      if (key == null)
        throw new NullPointerException("map key"); 
      Map<String, String> map = internalGetRouteParams().getMap();
      if (!map.containsKey(key))
        throw new IllegalArgumentException(); 
      return map.get(key);
    }
    
    public Builder clearRouteParams() {
      this.bitField0_ &= 0xFFFFFFBF;
      internalGetMutableRouteParams().getMutableMap().clear();
      return this;
    }
    
    public Builder removeRouteParams(String key) {
      if (key == null)
        throw new NullPointerException("map key"); 
      internalGetMutableRouteParams().getMutableMap().remove(key);
      return this;
    }
    
    @Deprecated
    public Map<String, String> getMutableRouteParams() {
      this.bitField0_ |= 0x40;
      return internalGetMutableRouteParams().getMutableMap();
    }
    
    public Builder putRouteParams(String key, String value) {
      if (key == null)
        throw new NullPointerException("map key"); 
      if (value == null)
        throw new NullPointerException("map value"); 
      internalGetMutableRouteParams().getMutableMap().put(key, value);
      this.bitField0_ |= 0x40;
      return this;
    }
    
    public Builder putAllRouteParams(Map<String, String> values) {
      internalGetMutableRouteParams().getMutableMap().putAll(values);
      this.bitField0_ |= 0x40;
      return this;
    }
    
    public long getHeartbeatDuration() {
      return this.heartbeatDuration_;
    }
    
    public Builder setHeartbeatDuration(long value) {
      this.heartbeatDuration_ = value;
      this.bitField0_ |= 0x80;
      onChanged();
      return this;
    }
    
    public Builder clearHeartbeatDuration() {
      this.bitField0_ &= 0xFFFFFF7F;
      this.heartbeatDuration_ = 0L;
      onChanged();
      return this;
    }
    
    public boolean getNeedAck() {
      return this.needAck_;
    }
    
    public Builder setNeedAck(boolean value) {
      this.needAck_ = value;
      this.bitField0_ |= 0x100;
      onChanged();
      return this;
    }
    
    public Builder clearNeedAck() {
      this.bitField0_ &= 0xFFFFFEFF;
      this.needAck_ = false;
      onChanged();
      return this;
    }
    
    public String getPushServer() {
      Object ref = this.pushServer_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.pushServer_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getPushServerBytes() {
      Object ref = this.pushServer_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.pushServer_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setPushServer(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.pushServer_ = value;
      this.bitField0_ |= 0x200;
      onChanged();
      return this;
    }
    
    public Builder clearPushServer() {
      this.pushServer_ = Response.getDefaultInstance().getPushServer();
      this.bitField0_ &= 0xFFFFFDFF;
      onChanged();
      return this;
    }
    
    public Builder setPushServerBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      Response.checkByteStringIsUtf8(value);
      this.pushServer_ = value;
      this.bitField0_ |= 0x200;
      onChanged();
      return this;
    }
    
    public String getLiveCursor() {
      Object ref = this.liveCursor_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.liveCursor_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getLiveCursorBytes() {
      Object ref = this.liveCursor_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.liveCursor_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setLiveCursor(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.liveCursor_ = value;
      this.bitField0_ |= 0x400;
      onChanged();
      return this;
    }
    
    public Builder clearLiveCursor() {
      this.liveCursor_ = Response.getDefaultInstance().getLiveCursor();
      this.bitField0_ &= 0xFFFFFBFF;
      onChanged();
      return this;
    }
    
    public Builder setLiveCursorBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      Response.checkByteStringIsUtf8(value);
      this.liveCursor_ = value;
      this.bitField0_ |= 0x400;
      onChanged();
      return this;
    }
    
    public boolean getHistoryNoMore() {
      return this.historyNoMore_;
    }
    
    public Builder setHistoryNoMore(boolean value) {
      this.historyNoMore_ = value;
      this.bitField0_ |= 0x800;
      onChanged();
      return this;
    }
    
    public Builder clearHistoryNoMore() {
      this.bitField0_ &= 0xFFFFF7FF;
      this.historyNoMore_ = false;
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
  
  private static final Response DEFAULT_INSTANCE = new Response();
  
  public static Response getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }
  
  private static final Parser<Response> PARSER = (Parser<Response>)new AbstractParser<Response>() {
      public Response parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        Response.Builder builder = Response.newBuilder();
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
  
  public static Parser<Response> parser() {
    return PARSER;
  }
  
  public Parser<Response> getParserForType() {
    return PARSER;
  }
  
  public Response getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }
}
