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
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MapEntry;
import com.google.protobuf.MapField;
import com.google.protobuf.MapFieldReflectionAccessor;
import com.google.protobuf.Message;
import com.google.protobuf.MessageLite;
import com.google.protobuf.Parser;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import com.google.protobuf.WireFormat;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Map;

public final class ChatMessage extends GeneratedMessageV3 implements ChatMessageOrBuilder {
  private static final long serialVersionUID = 0L;
  
  private int bitField0_;
  
  public static final int COMMON_FIELD_NUMBER = 1;
  
  private Common common_;
  
  public static final int USER_FIELD_NUMBER = 2;
  
  private User user_;
  
  public static final int CONTENT_FIELD_NUMBER = 3;
  
  private volatile Object content_;
  
  public static final int VISIBLETOSENDER_FIELD_NUMBER = 4;
  
  private boolean visibleToSender_;
  
  public static final int BACKGROUNDIMAGE_FIELD_NUMBER = 5;
  
  private Image backgroundImage_;
  
  public static final int FULLSCREENTEXTCOLOR_FIELD_NUMBER = 6;
  
  private volatile Object fullScreenTextColor_;
  
  public static final int BACKGROUNDIMAGEV2_FIELD_NUMBER = 7;
  
  private Image backgroundImageV2_;
  
  public static final int PUBLICAREACOMMON_FIELD_NUMBER = 9;
  
  private PublicAreaCommon publicAreaCommon_;
  
  public static final int GIFTIMAGE_FIELD_NUMBER = 10;
  
  private Image giftImage_;
  
  public static final int LANGUAGE_FIELD_NUMBER = 14;
  
  private volatile Object language_;
  
  public static final int CHATTAGSLIST_FIELD_NUMBER = 19;
  
  private MapField<String, String> chatTagsList_;
  
  private byte memoizedIsInitialized;
  
  private ChatMessage(GeneratedMessageV3.Builder<?> builder) {
    super(builder);
    this.content_ = "";
    this.visibleToSender_ = false;
    this.fullScreenTextColor_ = "";
    this.language_ = "";
    this.memoizedIsInitialized = -1;
  }
  
  private ChatMessage() {
    this.content_ = "";
    this.visibleToSender_ = false;
    this.fullScreenTextColor_ = "";
    this.language_ = "";
    this.memoizedIsInitialized = -1;
    this.content_ = "";
    this.fullScreenTextColor_ = "";
    this.language_ = "";
  }
  
  protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
    return new ChatMessage();
  }
  
  public static final Descriptors.Descriptor getDescriptor() {
    return Tiktok.internal_static_ChatMessage_descriptor;
  }
  
  protected MapFieldReflectionAccessor internalGetMapFieldReflection(int number) {
    switch (number) {
      case 19:
        return (MapFieldReflectionAccessor)internalGetChatTagsList();
    } 
    throw new RuntimeException("Invalid map field number: " + number);
  }
  
  protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
    return Tiktok.internal_static_ChatMessage_fieldAccessorTable.ensureFieldAccessorsInitialized(ChatMessage.class, Builder.class);
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
  
  public boolean hasUser() {
    return ((this.bitField0_ & 0x2) != 0);
  }
  
  public User getUser() {
    return (this.user_ == null) ? User.getDefaultInstance() : this.user_;
  }
  
  public UserOrBuilder getUserOrBuilder() {
    return (this.user_ == null) ? User.getDefaultInstance() : this.user_;
  }
  
  public String getContent() {
    Object ref = this.content_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.content_ = s;
    return s;
  }
  
  public ByteString getContentBytes() {
    Object ref = this.content_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.content_ = b;
      return b;
    } 
    return (ByteString)ref;
  }
  
  public boolean getVisibleToSender() {
    return this.visibleToSender_;
  }
  
  public boolean hasBackgroundImage() {
    return ((this.bitField0_ & 0x4) != 0);
  }
  
  public Image getBackgroundImage() {
    return (this.backgroundImage_ == null) ? Image.getDefaultInstance() : this.backgroundImage_;
  }
  
  public ImageOrBuilder getBackgroundImageOrBuilder() {
    return (this.backgroundImage_ == null) ? Image.getDefaultInstance() : this.backgroundImage_;
  }
  
  public String getFullScreenTextColor() {
    Object ref = this.fullScreenTextColor_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.fullScreenTextColor_ = s;
    return s;
  }
  
  public ByteString getFullScreenTextColorBytes() {
    Object ref = this.fullScreenTextColor_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.fullScreenTextColor_ = b;
      return b;
    } 
    return (ByteString)ref;
  }
  
  public boolean hasBackgroundImageV2() {
    return ((this.bitField0_ & 0x8) != 0);
  }
  
  public Image getBackgroundImageV2() {
    return (this.backgroundImageV2_ == null) ? Image.getDefaultInstance() : this.backgroundImageV2_;
  }
  
  public ImageOrBuilder getBackgroundImageV2OrBuilder() {
    return (this.backgroundImageV2_ == null) ? Image.getDefaultInstance() : this.backgroundImageV2_;
  }
  
  public boolean hasPublicAreaCommon() {
    return ((this.bitField0_ & 0x10) != 0);
  }
  
  public PublicAreaCommon getPublicAreaCommon() {
    return (this.publicAreaCommon_ == null) ? PublicAreaCommon.getDefaultInstance() : this.publicAreaCommon_;
  }
  
  public PublicAreaCommonOrBuilder getPublicAreaCommonOrBuilder() {
    return (this.publicAreaCommon_ == null) ? PublicAreaCommon.getDefaultInstance() : this.publicAreaCommon_;
  }
  
  public boolean hasGiftImage() {
    return ((this.bitField0_ & 0x20) != 0);
  }
  
  public Image getGiftImage() {
    return (this.giftImage_ == null) ? Image.getDefaultInstance() : this.giftImage_;
  }
  
  public ImageOrBuilder getGiftImageOrBuilder() {
    return (this.giftImage_ == null) ? Image.getDefaultInstance() : this.giftImage_;
  }
  
  public String getLanguage() {
    Object ref = this.language_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.language_ = s;
    return s;
  }
  
  public ByteString getLanguageBytes() {
    Object ref = this.language_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.language_ = b;
      return b;
    } 
    return (ByteString)ref;
  }
  
  private static final class ChatTagsListDefaultEntryHolder {
    static final MapEntry<String, String> defaultEntry = MapEntry.newDefaultInstance(Tiktok.internal_static_ChatMessage_ChatTagsListEntry_descriptor, WireFormat.FieldType.STRING, "", WireFormat.FieldType.STRING, "");
  }
  
  private MapField<String, String> internalGetChatTagsList() {
    if (this.chatTagsList_ == null)
      return MapField.emptyMapField(ChatTagsListDefaultEntryHolder.defaultEntry); 
    return this.chatTagsList_;
  }
  
  public int getChatTagsListCount() {
    return internalGetChatTagsList().getMap().size();
  }
  
  public boolean containsChatTagsList(String key) {
    if (key == null)
      throw new NullPointerException("map key"); 
    return internalGetChatTagsList().getMap().containsKey(key);
  }
  
  @Deprecated
  public Map<String, String> getChatTagsList() {
    return getChatTagsListMap();
  }
  
  public Map<String, String> getChatTagsListMap() {
    return internalGetChatTagsList().getMap();
  }
  
  public String getChatTagsListOrDefault(String key, String defaultValue) {
    if (key == null)
      throw new NullPointerException("map key"); 
    Map<String, String> map = internalGetChatTagsList().getMap();
    return map.containsKey(key) ? map.get(key) : defaultValue;
  }
  
  public String getChatTagsListOrThrow(String key) {
    if (key == null)
      throw new NullPointerException("map key"); 
    Map<String, String> map = internalGetChatTagsList().getMap();
    if (!map.containsKey(key))
      throw new IllegalArgumentException(); 
    return map.get(key);
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
    if ((this.bitField0_ & 0x2) != 0)
      output.writeMessage(2, (MessageLite)getUser()); 
    if (!GeneratedMessageV3.isStringEmpty(this.content_))
      GeneratedMessageV3.writeString(output, 3, this.content_); 
    if (this.visibleToSender_)
      output.writeBool(4, this.visibleToSender_); 
    if ((this.bitField0_ & 0x4) != 0)
      output.writeMessage(5, (MessageLite)getBackgroundImage()); 
    if (!GeneratedMessageV3.isStringEmpty(this.fullScreenTextColor_))
      GeneratedMessageV3.writeString(output, 6, this.fullScreenTextColor_); 
    if ((this.bitField0_ & 0x8) != 0)
      output.writeMessage(7, (MessageLite)getBackgroundImageV2()); 
    if ((this.bitField0_ & 0x10) != 0)
      output.writeMessage(9, (MessageLite)getPublicAreaCommon()); 
    if ((this.bitField0_ & 0x20) != 0)
      output.writeMessage(10, (MessageLite)getGiftImage()); 
    if (!GeneratedMessageV3.isStringEmpty(this.language_))
      GeneratedMessageV3.writeString(output, 14, this.language_); 
    GeneratedMessageV3.serializeStringMapTo(output, 
        
        internalGetChatTagsList(), ChatTagsListDefaultEntryHolder.defaultEntry, 19);
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
    if ((this.bitField0_ & 0x2) != 0)
      size += 
        CodedOutputStream.computeMessageSize(2, (MessageLite)getUser()); 
    if (!GeneratedMessageV3.isStringEmpty(this.content_))
      size += GeneratedMessageV3.computeStringSize(3, this.content_); 
    if (this.visibleToSender_)
      size += 
        CodedOutputStream.computeBoolSize(4, this.visibleToSender_); 
    if ((this.bitField0_ & 0x4) != 0)
      size += 
        CodedOutputStream.computeMessageSize(5, (MessageLite)getBackgroundImage()); 
    if (!GeneratedMessageV3.isStringEmpty(this.fullScreenTextColor_))
      size += GeneratedMessageV3.computeStringSize(6, this.fullScreenTextColor_); 
    if ((this.bitField0_ & 0x8) != 0)
      size += 
        CodedOutputStream.computeMessageSize(7, (MessageLite)getBackgroundImageV2()); 
    if ((this.bitField0_ & 0x10) != 0)
      size += 
        CodedOutputStream.computeMessageSize(9, (MessageLite)getPublicAreaCommon()); 
    if ((this.bitField0_ & 0x20) != 0)
      size += 
        CodedOutputStream.computeMessageSize(10, (MessageLite)getGiftImage()); 
    if (!GeneratedMessageV3.isStringEmpty(this.language_))
      size += GeneratedMessageV3.computeStringSize(14, this.language_); 
    for (Map.Entry<String, String> entry : (Iterable<Map.Entry<String, String>>)internalGetChatTagsList().getMap().entrySet()) {
      MapEntry<String, String> chatTagsList__ = ChatTagsListDefaultEntryHolder.defaultEntry.newBuilderForType().setKey(entry.getKey()).setValue(entry.getValue()).build();
      size += 
        CodedOutputStream.computeMessageSize(19, (MessageLite)chatTagsList__);
    } 
    size += getUnknownFields().getSerializedSize();
    this.memoizedSize = size;
    return size;
  }
  
  public boolean equals(Object obj) {
    if (obj == this)
      return true; 
    if (!(obj instanceof ChatMessage))
      return super.equals(obj); 
    ChatMessage other = (ChatMessage)obj;
    if (hasCommon() != other.hasCommon())
      return false; 
    if (hasCommon() && 
      
      !getCommon().equals(other.getCommon()))
      return false; 
    if (hasUser() != other.hasUser())
      return false; 
    if (hasUser() && 
      
      !getUser().equals(other.getUser()))
      return false; 
    if (!getContent().equals(other.getContent()))
      return false; 
    if (getVisibleToSender() != other
      .getVisibleToSender())
      return false; 
    if (hasBackgroundImage() != other.hasBackgroundImage())
      return false; 
    if (hasBackgroundImage() && 
      
      !getBackgroundImage().equals(other.getBackgroundImage()))
      return false; 
    if (!getFullScreenTextColor().equals(other.getFullScreenTextColor()))
      return false; 
    if (hasBackgroundImageV2() != other.hasBackgroundImageV2())
      return false; 
    if (hasBackgroundImageV2() && 
      
      !getBackgroundImageV2().equals(other.getBackgroundImageV2()))
      return false; 
    if (hasPublicAreaCommon() != other.hasPublicAreaCommon())
      return false; 
    if (hasPublicAreaCommon() && 
      
      !getPublicAreaCommon().equals(other.getPublicAreaCommon()))
      return false; 
    if (hasGiftImage() != other.hasGiftImage())
      return false; 
    if (hasGiftImage() && 
      
      !getGiftImage().equals(other.getGiftImage()))
      return false; 
    if (!getLanguage().equals(other.getLanguage()))
      return false; 
    if (!internalGetChatTagsList().equals(other
        .internalGetChatTagsList()))
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
    if (hasUser()) {
      hash = 37 * hash + 2;
      hash = 53 * hash + getUser().hashCode();
    } 
    hash = 37 * hash + 3;
    hash = 53 * hash + getContent().hashCode();
    hash = 37 * hash + 4;
    hash = 53 * hash + Internal.hashBoolean(
        getVisibleToSender());
    if (hasBackgroundImage()) {
      hash = 37 * hash + 5;
      hash = 53 * hash + getBackgroundImage().hashCode();
    } 
    hash = 37 * hash + 6;
    hash = 53 * hash + getFullScreenTextColor().hashCode();
    if (hasBackgroundImageV2()) {
      hash = 37 * hash + 7;
      hash = 53 * hash + getBackgroundImageV2().hashCode();
    } 
    if (hasPublicAreaCommon()) {
      hash = 37 * hash + 9;
      hash = 53 * hash + getPublicAreaCommon().hashCode();
    } 
    if (hasGiftImage()) {
      hash = 37 * hash + 10;
      hash = 53 * hash + getGiftImage().hashCode();
    } 
    hash = 37 * hash + 14;
    hash = 53 * hash + getLanguage().hashCode();
    if (!internalGetChatTagsList().getMap().isEmpty()) {
      hash = 37 * hash + 19;
      hash = 53 * hash + internalGetChatTagsList().hashCode();
    } 
    hash = 29 * hash + getUnknownFields().hashCode();
    this.memoizedHashCode = hash;
    return hash;
  }
  
  public static ChatMessage parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
    return (ChatMessage)PARSER.parseFrom(data);
  }
  
  public static ChatMessage parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (ChatMessage)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static ChatMessage parseFrom(ByteString data) throws InvalidProtocolBufferException {
    return (ChatMessage)PARSER.parseFrom(data);
  }
  
  public static ChatMessage parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (ChatMessage)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static ChatMessage parseFrom(byte[] data) throws InvalidProtocolBufferException {
    return (ChatMessage)PARSER.parseFrom(data);
  }
  
  public static ChatMessage parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (ChatMessage)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static ChatMessage parseFrom(InputStream input) throws IOException {
    return 
      (ChatMessage)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static ChatMessage parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (ChatMessage)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static ChatMessage parseDelimitedFrom(InputStream input) throws IOException {
    return 
      (ChatMessage)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
  }
  
  public static ChatMessage parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (ChatMessage)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static ChatMessage parseFrom(CodedInputStream input) throws IOException {
    return 
      (ChatMessage)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static ChatMessage parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (ChatMessage)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public Builder newBuilderForType() {
    return newBuilder();
  }
  
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  
  public static Builder newBuilder(ChatMessage prototype) {
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
  
  public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ChatMessageOrBuilder {
    private int bitField0_;
    
    private Common common_;
    
    private SingleFieldBuilderV3<Common, Common.Builder, CommonOrBuilder> commonBuilder_;
    
    private User user_;
    
    private SingleFieldBuilderV3<User, User.Builder, UserOrBuilder> userBuilder_;
    
    private Object content_;
    
    private boolean visibleToSender_;
    
    private Image backgroundImage_;
    
    private SingleFieldBuilderV3<Image, Image.Builder, ImageOrBuilder> backgroundImageBuilder_;
    
    private Object fullScreenTextColor_;
    
    private Image backgroundImageV2_;
    
    private SingleFieldBuilderV3<Image, Image.Builder, ImageOrBuilder> backgroundImageV2Builder_;
    
    private PublicAreaCommon publicAreaCommon_;
    
    private SingleFieldBuilderV3<PublicAreaCommon, PublicAreaCommon.Builder, PublicAreaCommonOrBuilder> publicAreaCommonBuilder_;
    
    private Image giftImage_;
    
    private SingleFieldBuilderV3<Image, Image.Builder, ImageOrBuilder> giftImageBuilder_;
    
    private Object language_;
    
    private MapField<String, String> chatTagsList_;
    
    public static final Descriptors.Descriptor getDescriptor() {
      return Tiktok.internal_static_ChatMessage_descriptor;
    }
    
    protected MapFieldReflectionAccessor internalGetMapFieldReflection(int number) {
      switch (number) {
        case 19:
          return (MapFieldReflectionAccessor)internalGetChatTagsList();
      } 
      throw new RuntimeException("Invalid map field number: " + number);
    }
    
    protected MapFieldReflectionAccessor internalGetMutableMapFieldReflection(int number) {
      switch (number) {
        case 19:
          return (MapFieldReflectionAccessor)internalGetMutableChatTagsList();
      } 
      throw new RuntimeException("Invalid map field number: " + number);
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return Tiktok.internal_static_ChatMessage_fieldAccessorTable
        .ensureFieldAccessorsInitialized(ChatMessage.class, Builder.class);
    }
    
    private Builder() {
      this.content_ = "";
      this.fullScreenTextColor_ = "";
      this.language_ = "";
      maybeForceBuilderInitialization();
    }
    
    private Builder(GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      this.content_ = "";
      this.fullScreenTextColor_ = "";
      this.language_ = "";
      maybeForceBuilderInitialization();
    }
    
    private void maybeForceBuilderInitialization() {
      if (ChatMessage.alwaysUseFieldBuilders) {
        getCommonFieldBuilder();
        getUserFieldBuilder();
        getBackgroundImageFieldBuilder();
        getBackgroundImageV2FieldBuilder();
        getPublicAreaCommonFieldBuilder();
        getGiftImageFieldBuilder();
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
      this.user_ = null;
      if (this.userBuilder_ != null) {
        this.userBuilder_.dispose();
        this.userBuilder_ = null;
      } 
      this.content_ = "";
      this.visibleToSender_ = false;
      this.backgroundImage_ = null;
      if (this.backgroundImageBuilder_ != null) {
        this.backgroundImageBuilder_.dispose();
        this.backgroundImageBuilder_ = null;
      } 
      this.fullScreenTextColor_ = "";
      this.backgroundImageV2_ = null;
      if (this.backgroundImageV2Builder_ != null) {
        this.backgroundImageV2Builder_.dispose();
        this.backgroundImageV2Builder_ = null;
      } 
      this.publicAreaCommon_ = null;
      if (this.publicAreaCommonBuilder_ != null) {
        this.publicAreaCommonBuilder_.dispose();
        this.publicAreaCommonBuilder_ = null;
      } 
      this.giftImage_ = null;
      if (this.giftImageBuilder_ != null) {
        this.giftImageBuilder_.dispose();
        this.giftImageBuilder_ = null;
      } 
      this.language_ = "";
      internalGetMutableChatTagsList().clear();
      return this;
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
      return Tiktok.internal_static_ChatMessage_descriptor;
    }
    
    public ChatMessage getDefaultInstanceForType() {
      return ChatMessage.getDefaultInstance();
    }
    
    public ChatMessage build() {
      ChatMessage result = buildPartial();
      if (!result.isInitialized())
        throw newUninitializedMessageException(result); 
      return result;
    }
    
    public ChatMessage buildPartial() {
      ChatMessage result = new ChatMessage(this);
      if (this.bitField0_ != 0)
        buildPartial0(result); 
      onBuilt();
      return result;
    }
    
    private void buildPartial0(ChatMessage result) {
      int from_bitField0_ = this.bitField0_;
      int to_bitField0_ = 0;
      if ((from_bitField0_ & 0x1) != 0) {
        result.common_ = (this.commonBuilder_ == null) ? this.common_ : (Common)this.commonBuilder_.build();
        to_bitField0_ |= 0x1;
      } 
      if ((from_bitField0_ & 0x2) != 0) {
        result.user_ = (this.userBuilder_ == null) ? this.user_ : (User)this.userBuilder_.build();
        to_bitField0_ |= 0x2;
      } 
      if ((from_bitField0_ & 0x4) != 0)
        result.content_ = this.content_; 
      if ((from_bitField0_ & 0x8) != 0)
        result.visibleToSender_ = this.visibleToSender_; 
      if ((from_bitField0_ & 0x10) != 0) {
        result.backgroundImage_ = (this.backgroundImageBuilder_ == null) ? this.backgroundImage_ : (Image)this.backgroundImageBuilder_.build();
        to_bitField0_ |= 0x4;
      } 
      if ((from_bitField0_ & 0x20) != 0)
        result.fullScreenTextColor_ = this.fullScreenTextColor_; 
      if ((from_bitField0_ & 0x40) != 0) {
        result.backgroundImageV2_ = (this.backgroundImageV2Builder_ == null) ? this.backgroundImageV2_ : (Image)this.backgroundImageV2Builder_.build();
        to_bitField0_ |= 0x8;
      } 
      if ((from_bitField0_ & 0x80) != 0) {
        result.publicAreaCommon_ = (this.publicAreaCommonBuilder_ == null) ? this.publicAreaCommon_ : (PublicAreaCommon)this.publicAreaCommonBuilder_.build();
        to_bitField0_ |= 0x10;
      } 
      if ((from_bitField0_ & 0x100) != 0) {
        result.giftImage_ = (this.giftImageBuilder_ == null) ? this.giftImage_ : (Image)this.giftImageBuilder_.build();
        to_bitField0_ |= 0x20;
      } 
      if ((from_bitField0_ & 0x200) != 0)
        result.language_ = this.language_; 
      if ((from_bitField0_ & 0x400) != 0) {
        result.chatTagsList_ = internalGetChatTagsList();
        result.chatTagsList_.makeImmutable();
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
      if (other instanceof ChatMessage)
        return mergeFrom((ChatMessage)other); 
      super.mergeFrom(other);
      return this;
    }
    
    public Builder mergeFrom(ChatMessage other) {
      if (other == ChatMessage.getDefaultInstance())
        return this; 
      if (other.hasCommon())
        mergeCommon(other.getCommon()); 
      if (other.hasUser())
        mergeUser(other.getUser()); 
      if (!other.getContent().isEmpty()) {
        this.content_ = other.content_;
        this.bitField0_ |= 0x4;
        onChanged();
      } 
      if (other.getVisibleToSender())
        setVisibleToSender(other.getVisibleToSender()); 
      if (other.hasBackgroundImage())
        mergeBackgroundImage(other.getBackgroundImage()); 
      if (!other.getFullScreenTextColor().isEmpty()) {
        this.fullScreenTextColor_ = other.fullScreenTextColor_;
        this.bitField0_ |= 0x20;
        onChanged();
      } 
      if (other.hasBackgroundImageV2())
        mergeBackgroundImageV2(other.getBackgroundImageV2()); 
      if (other.hasPublicAreaCommon())
        mergePublicAreaCommon(other.getPublicAreaCommon()); 
      if (other.hasGiftImage())
        mergeGiftImage(other.getGiftImage()); 
      if (!other.getLanguage().isEmpty()) {
        this.language_ = other.language_;
        this.bitField0_ |= 0x200;
        onChanged();
      } 
      internalGetMutableChatTagsList().mergeFrom(other.internalGetChatTagsList());
      this.bitField0_ |= 0x400;
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
          MapEntry<String, String> chatTagsList__;
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
              input.readMessage((MessageLite.Builder)getUserFieldBuilder().getBuilder(), extensionRegistry);
              this.bitField0_ |= 0x2;
              continue;
            case 26:
              this.content_ = input.readStringRequireUtf8();
              this.bitField0_ |= 0x4;
              continue;
            case 32:
              this.visibleToSender_ = input.readBool();
              this.bitField0_ |= 0x8;
              continue;
            case 42:
              input.readMessage((MessageLite.Builder)getBackgroundImageFieldBuilder().getBuilder(), extensionRegistry);
              this.bitField0_ |= 0x10;
              continue;
            case 50:
              this.fullScreenTextColor_ = input.readStringRequireUtf8();
              this.bitField0_ |= 0x20;
              continue;
            case 58:
              input.readMessage((MessageLite.Builder)getBackgroundImageV2FieldBuilder().getBuilder(), extensionRegistry);
              this.bitField0_ |= 0x40;
              continue;
            case 74:
              input.readMessage((MessageLite.Builder)getPublicAreaCommonFieldBuilder().getBuilder(), extensionRegistry);
              this.bitField0_ |= 0x80;
              continue;
            case 82:
              input.readMessage((MessageLite.Builder)getGiftImageFieldBuilder().getBuilder(), extensionRegistry);
              this.bitField0_ |= 0x100;
              continue;
            case 114:
              this.language_ = input.readStringRequireUtf8();
              this.bitField0_ |= 0x200;
              continue;
            case 154:
              chatTagsList__ = (MapEntry<String, String>)input.readMessage(ChatMessage.ChatTagsListDefaultEntryHolder.defaultEntry.getParserForType(), extensionRegistry);
              internalGetMutableChatTagsList().getMutableMap().put((String)chatTagsList__.getKey(), (String)chatTagsList__.getValue());
              this.bitField0_ |= 0x400;
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
    
    public boolean hasUser() {
      return ((this.bitField0_ & 0x2) != 0);
    }
    
    public User getUser() {
      if (this.userBuilder_ == null)
        return (this.user_ == null) ? User.getDefaultInstance() : this.user_; 
      return (User)this.userBuilder_.getMessage();
    }
    
    public Builder setUser(User value) {
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
    
    public Builder setUser(User.Builder builderForValue) {
      if (this.userBuilder_ == null) {
        this.user_ = builderForValue.build();
      } else {
        this.userBuilder_.setMessage((AbstractMessage)builderForValue.build());
      } 
      this.bitField0_ |= 0x2;
      onChanged();
      return this;
    }
    
    public Builder mergeUser(User value) {
      if (this.userBuilder_ == null) {
        if ((this.bitField0_ & 0x2) != 0 && this.user_ != null && this.user_ != User.getDefaultInstance()) {
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
    
    public User.Builder getUserBuilder() {
      this.bitField0_ |= 0x2;
      onChanged();
      return (User.Builder)getUserFieldBuilder().getBuilder();
    }
    
    public UserOrBuilder getUserOrBuilder() {
      if (this.userBuilder_ != null)
        return (UserOrBuilder)this.userBuilder_.getMessageOrBuilder(); 
      return (this.user_ == null) ? User.getDefaultInstance() : this.user_;
    }
    
    private SingleFieldBuilderV3<User, User.Builder, UserOrBuilder> getUserFieldBuilder() {
      if (this.userBuilder_ == null) {
        this.userBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getUser(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.user_ = null;
      } 
      return this.userBuilder_;
    }
    
    public String getContent() {
      Object ref = this.content_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.content_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getContentBytes() {
      Object ref = this.content_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.content_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setContent(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.content_ = value;
      this.bitField0_ |= 0x4;
      onChanged();
      return this;
    }
    
    public Builder clearContent() {
      this.content_ = ChatMessage.getDefaultInstance().getContent();
      this.bitField0_ &= 0xFFFFFFFB;
      onChanged();
      return this;
    }
    
    public Builder setContentBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      ChatMessage.checkByteStringIsUtf8(value);
      this.content_ = value;
      this.bitField0_ |= 0x4;
      onChanged();
      return this;
    }
    
    public boolean getVisibleToSender() {
      return this.visibleToSender_;
    }
    
    public Builder setVisibleToSender(boolean value) {
      this.visibleToSender_ = value;
      this.bitField0_ |= 0x8;
      onChanged();
      return this;
    }
    
    public Builder clearVisibleToSender() {
      this.bitField0_ &= 0xFFFFFFF7;
      this.visibleToSender_ = false;
      onChanged();
      return this;
    }
    
    public boolean hasBackgroundImage() {
      return ((this.bitField0_ & 0x10) != 0);
    }
    
    public Image getBackgroundImage() {
      if (this.backgroundImageBuilder_ == null)
        return (this.backgroundImage_ == null) ? Image.getDefaultInstance() : this.backgroundImage_; 
      return (Image)this.backgroundImageBuilder_.getMessage();
    }
    
    public Builder setBackgroundImage(Image value) {
      if (this.backgroundImageBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        this.backgroundImage_ = value;
      } else {
        this.backgroundImageBuilder_.setMessage((AbstractMessage)value);
      } 
      this.bitField0_ |= 0x10;
      onChanged();
      return this;
    }
    
    public Builder setBackgroundImage(Image.Builder builderForValue) {
      if (this.backgroundImageBuilder_ == null) {
        this.backgroundImage_ = builderForValue.build();
      } else {
        this.backgroundImageBuilder_.setMessage((AbstractMessage)builderForValue.build());
      } 
      this.bitField0_ |= 0x10;
      onChanged();
      return this;
    }
    
    public Builder mergeBackgroundImage(Image value) {
      if (this.backgroundImageBuilder_ == null) {
        if ((this.bitField0_ & 0x10) != 0 && this.backgroundImage_ != null && this.backgroundImage_ != Image.getDefaultInstance()) {
          getBackgroundImageBuilder().mergeFrom(value);
        } else {
          this.backgroundImage_ = value;
        } 
      } else {
        this.backgroundImageBuilder_.mergeFrom((AbstractMessage)value);
      } 
      if (this.backgroundImage_ != null) {
        this.bitField0_ |= 0x10;
        onChanged();
      } 
      return this;
    }
    
    public Builder clearBackgroundImage() {
      this.bitField0_ &= 0xFFFFFFEF;
      this.backgroundImage_ = null;
      if (this.backgroundImageBuilder_ != null) {
        this.backgroundImageBuilder_.dispose();
        this.backgroundImageBuilder_ = null;
      } 
      onChanged();
      return this;
    }
    
    public Image.Builder getBackgroundImageBuilder() {
      this.bitField0_ |= 0x10;
      onChanged();
      return (Image.Builder)getBackgroundImageFieldBuilder().getBuilder();
    }
    
    public ImageOrBuilder getBackgroundImageOrBuilder() {
      if (this.backgroundImageBuilder_ != null)
        return (ImageOrBuilder)this.backgroundImageBuilder_.getMessageOrBuilder(); 
      return (this.backgroundImage_ == null) ? Image.getDefaultInstance() : this.backgroundImage_;
    }
    
    private SingleFieldBuilderV3<Image, Image.Builder, ImageOrBuilder> getBackgroundImageFieldBuilder() {
      if (this.backgroundImageBuilder_ == null) {
        this.backgroundImageBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getBackgroundImage(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.backgroundImage_ = null;
      } 
      return this.backgroundImageBuilder_;
    }
    
    public String getFullScreenTextColor() {
      Object ref = this.fullScreenTextColor_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.fullScreenTextColor_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getFullScreenTextColorBytes() {
      Object ref = this.fullScreenTextColor_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.fullScreenTextColor_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setFullScreenTextColor(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.fullScreenTextColor_ = value;
      this.bitField0_ |= 0x20;
      onChanged();
      return this;
    }
    
    public Builder clearFullScreenTextColor() {
      this.fullScreenTextColor_ = ChatMessage.getDefaultInstance().getFullScreenTextColor();
      this.bitField0_ &= 0xFFFFFFDF;
      onChanged();
      return this;
    }
    
    public Builder setFullScreenTextColorBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      ChatMessage.checkByteStringIsUtf8(value);
      this.fullScreenTextColor_ = value;
      this.bitField0_ |= 0x20;
      onChanged();
      return this;
    }
    
    public boolean hasBackgroundImageV2() {
      return ((this.bitField0_ & 0x40) != 0);
    }
    
    public Image getBackgroundImageV2() {
      if (this.backgroundImageV2Builder_ == null)
        return (this.backgroundImageV2_ == null) ? Image.getDefaultInstance() : this.backgroundImageV2_; 
      return (Image)this.backgroundImageV2Builder_.getMessage();
    }
    
    public Builder setBackgroundImageV2(Image value) {
      if (this.backgroundImageV2Builder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        this.backgroundImageV2_ = value;
      } else {
        this.backgroundImageV2Builder_.setMessage((AbstractMessage)value);
      } 
      this.bitField0_ |= 0x40;
      onChanged();
      return this;
    }
    
    public Builder setBackgroundImageV2(Image.Builder builderForValue) {
      if (this.backgroundImageV2Builder_ == null) {
        this.backgroundImageV2_ = builderForValue.build();
      } else {
        this.backgroundImageV2Builder_.setMessage((AbstractMessage)builderForValue.build());
      } 
      this.bitField0_ |= 0x40;
      onChanged();
      return this;
    }
    
    public Builder mergeBackgroundImageV2(Image value) {
      if (this.backgroundImageV2Builder_ == null) {
        if ((this.bitField0_ & 0x40) != 0 && this.backgroundImageV2_ != null && this.backgroundImageV2_ != Image.getDefaultInstance()) {
          getBackgroundImageV2Builder().mergeFrom(value);
        } else {
          this.backgroundImageV2_ = value;
        } 
      } else {
        this.backgroundImageV2Builder_.mergeFrom((AbstractMessage)value);
      } 
      if (this.backgroundImageV2_ != null) {
        this.bitField0_ |= 0x40;
        onChanged();
      } 
      return this;
    }
    
    public Builder clearBackgroundImageV2() {
      this.bitField0_ &= 0xFFFFFFBF;
      this.backgroundImageV2_ = null;
      if (this.backgroundImageV2Builder_ != null) {
        this.backgroundImageV2Builder_.dispose();
        this.backgroundImageV2Builder_ = null;
      } 
      onChanged();
      return this;
    }
    
    public Image.Builder getBackgroundImageV2Builder() {
      this.bitField0_ |= 0x40;
      onChanged();
      return (Image.Builder)getBackgroundImageV2FieldBuilder().getBuilder();
    }
    
    public ImageOrBuilder getBackgroundImageV2OrBuilder() {
      if (this.backgroundImageV2Builder_ != null)
        return (ImageOrBuilder)this.backgroundImageV2Builder_.getMessageOrBuilder(); 
      return (this.backgroundImageV2_ == null) ? Image.getDefaultInstance() : this.backgroundImageV2_;
    }
    
    private SingleFieldBuilderV3<Image, Image.Builder, ImageOrBuilder> getBackgroundImageV2FieldBuilder() {
      if (this.backgroundImageV2Builder_ == null) {
        this.backgroundImageV2Builder_ = new SingleFieldBuilderV3((AbstractMessage)getBackgroundImageV2(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.backgroundImageV2_ = null;
      } 
      return this.backgroundImageV2Builder_;
    }
    
    public boolean hasPublicAreaCommon() {
      return ((this.bitField0_ & 0x80) != 0);
    }
    
    public PublicAreaCommon getPublicAreaCommon() {
      if (this.publicAreaCommonBuilder_ == null)
        return (this.publicAreaCommon_ == null) ? PublicAreaCommon.getDefaultInstance() : this.publicAreaCommon_; 
      return (PublicAreaCommon)this.publicAreaCommonBuilder_.getMessage();
    }
    
    public Builder setPublicAreaCommon(PublicAreaCommon value) {
      if (this.publicAreaCommonBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        this.publicAreaCommon_ = value;
      } else {
        this.publicAreaCommonBuilder_.setMessage((AbstractMessage)value);
      } 
      this.bitField0_ |= 0x80;
      onChanged();
      return this;
    }
    
    public Builder setPublicAreaCommon(PublicAreaCommon.Builder builderForValue) {
      if (this.publicAreaCommonBuilder_ == null) {
        this.publicAreaCommon_ = builderForValue.build();
      } else {
        this.publicAreaCommonBuilder_.setMessage((AbstractMessage)builderForValue.build());
      } 
      this.bitField0_ |= 0x80;
      onChanged();
      return this;
    }
    
    public Builder mergePublicAreaCommon(PublicAreaCommon value) {
      if (this.publicAreaCommonBuilder_ == null) {
        if ((this.bitField0_ & 0x80) != 0 && this.publicAreaCommon_ != null && this.publicAreaCommon_ != PublicAreaCommon.getDefaultInstance()) {
          getPublicAreaCommonBuilder().mergeFrom(value);
        } else {
          this.publicAreaCommon_ = value;
        } 
      } else {
        this.publicAreaCommonBuilder_.mergeFrom((AbstractMessage)value);
      } 
      if (this.publicAreaCommon_ != null) {
        this.bitField0_ |= 0x80;
        onChanged();
      } 
      return this;
    }
    
    public Builder clearPublicAreaCommon() {
      this.bitField0_ &= 0xFFFFFF7F;
      this.publicAreaCommon_ = null;
      if (this.publicAreaCommonBuilder_ != null) {
        this.publicAreaCommonBuilder_.dispose();
        this.publicAreaCommonBuilder_ = null;
      } 
      onChanged();
      return this;
    }
    
    public PublicAreaCommon.Builder getPublicAreaCommonBuilder() {
      this.bitField0_ |= 0x80;
      onChanged();
      return (PublicAreaCommon.Builder)getPublicAreaCommonFieldBuilder().getBuilder();
    }
    
    public PublicAreaCommonOrBuilder getPublicAreaCommonOrBuilder() {
      if (this.publicAreaCommonBuilder_ != null)
        return (PublicAreaCommonOrBuilder)this.publicAreaCommonBuilder_.getMessageOrBuilder(); 
      return (this.publicAreaCommon_ == null) ? PublicAreaCommon.getDefaultInstance() : this.publicAreaCommon_;
    }
    
    private SingleFieldBuilderV3<PublicAreaCommon, PublicAreaCommon.Builder, PublicAreaCommonOrBuilder> getPublicAreaCommonFieldBuilder() {
      if (this.publicAreaCommonBuilder_ == null) {
        this.publicAreaCommonBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getPublicAreaCommon(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.publicAreaCommon_ = null;
      } 
      return this.publicAreaCommonBuilder_;
    }
    
    public boolean hasGiftImage() {
      return ((this.bitField0_ & 0x100) != 0);
    }
    
    public Image getGiftImage() {
      if (this.giftImageBuilder_ == null)
        return (this.giftImage_ == null) ? Image.getDefaultInstance() : this.giftImage_; 
      return (Image)this.giftImageBuilder_.getMessage();
    }
    
    public Builder setGiftImage(Image value) {
      if (this.giftImageBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        this.giftImage_ = value;
      } else {
        this.giftImageBuilder_.setMessage((AbstractMessage)value);
      } 
      this.bitField0_ |= 0x100;
      onChanged();
      return this;
    }
    
    public Builder setGiftImage(Image.Builder builderForValue) {
      if (this.giftImageBuilder_ == null) {
        this.giftImage_ = builderForValue.build();
      } else {
        this.giftImageBuilder_.setMessage((AbstractMessage)builderForValue.build());
      } 
      this.bitField0_ |= 0x100;
      onChanged();
      return this;
    }
    
    public Builder mergeGiftImage(Image value) {
      if (this.giftImageBuilder_ == null) {
        if ((this.bitField0_ & 0x100) != 0 && this.giftImage_ != null && this.giftImage_ != Image.getDefaultInstance()) {
          getGiftImageBuilder().mergeFrom(value);
        } else {
          this.giftImage_ = value;
        } 
      } else {
        this.giftImageBuilder_.mergeFrom((AbstractMessage)value);
      } 
      if (this.giftImage_ != null) {
        this.bitField0_ |= 0x100;
        onChanged();
      } 
      return this;
    }
    
    public Builder clearGiftImage() {
      this.bitField0_ &= 0xFFFFFEFF;
      this.giftImage_ = null;
      if (this.giftImageBuilder_ != null) {
        this.giftImageBuilder_.dispose();
        this.giftImageBuilder_ = null;
      } 
      onChanged();
      return this;
    }
    
    public Image.Builder getGiftImageBuilder() {
      this.bitField0_ |= 0x100;
      onChanged();
      return (Image.Builder)getGiftImageFieldBuilder().getBuilder();
    }
    
    public ImageOrBuilder getGiftImageOrBuilder() {
      if (this.giftImageBuilder_ != null)
        return (ImageOrBuilder)this.giftImageBuilder_.getMessageOrBuilder(); 
      return (this.giftImage_ == null) ? Image.getDefaultInstance() : this.giftImage_;
    }
    
    private SingleFieldBuilderV3<Image, Image.Builder, ImageOrBuilder> getGiftImageFieldBuilder() {
      if (this.giftImageBuilder_ == null) {
        this.giftImageBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getGiftImage(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.giftImage_ = null;
      } 
      return this.giftImageBuilder_;
    }
    
    public String getLanguage() {
      Object ref = this.language_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.language_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getLanguageBytes() {
      Object ref = this.language_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.language_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setLanguage(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.language_ = value;
      this.bitField0_ |= 0x200;
      onChanged();
      return this;
    }
    
    public Builder clearLanguage() {
      this.language_ = ChatMessage.getDefaultInstance().getLanguage();
      this.bitField0_ &= 0xFFFFFDFF;
      onChanged();
      return this;
    }
    
    public Builder setLanguageBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      ChatMessage.checkByteStringIsUtf8(value);
      this.language_ = value;
      this.bitField0_ |= 0x200;
      onChanged();
      return this;
    }
    
    private MapField<String, String> internalGetChatTagsList() {
      if (this.chatTagsList_ == null)
        return MapField.emptyMapField(ChatMessage.ChatTagsListDefaultEntryHolder.defaultEntry); 
      return this.chatTagsList_;
    }
    
    private MapField<String, String> internalGetMutableChatTagsList() {
      if (this.chatTagsList_ == null)
        this.chatTagsList_ = MapField.newMapField(ChatMessage.ChatTagsListDefaultEntryHolder.defaultEntry); 
      if (!this.chatTagsList_.isMutable())
        this.chatTagsList_ = this.chatTagsList_.copy(); 
      this.bitField0_ |= 0x400;
      onChanged();
      return this.chatTagsList_;
    }
    
    public int getChatTagsListCount() {
      return internalGetChatTagsList().getMap().size();
    }
    
    public boolean containsChatTagsList(String key) {
      if (key == null)
        throw new NullPointerException("map key"); 
      return internalGetChatTagsList().getMap().containsKey(key);
    }
    
    @Deprecated
    public Map<String, String> getChatTagsList() {
      return getChatTagsListMap();
    }
    
    public Map<String, String> getChatTagsListMap() {
      return internalGetChatTagsList().getMap();
    }
    
    public String getChatTagsListOrDefault(String key, String defaultValue) {
      if (key == null)
        throw new NullPointerException("map key"); 
      Map<String, String> map = internalGetChatTagsList().getMap();
      return map.containsKey(key) ? map.get(key) : defaultValue;
    }
    
    public String getChatTagsListOrThrow(String key) {
      if (key == null)
        throw new NullPointerException("map key"); 
      Map<String, String> map = internalGetChatTagsList().getMap();
      if (!map.containsKey(key))
        throw new IllegalArgumentException(); 
      return map.get(key);
    }
    
    public Builder clearChatTagsList() {
      this.bitField0_ &= 0xFFFFFBFF;
      internalGetMutableChatTagsList().getMutableMap()
        .clear();
      return this;
    }
    
    public Builder removeChatTagsList(String key) {
      if (key == null)
        throw new NullPointerException("map key"); 
      internalGetMutableChatTagsList().getMutableMap()
        .remove(key);
      return this;
    }
    
    @Deprecated
    public Map<String, String> getMutableChatTagsList() {
      this.bitField0_ |= 0x400;
      return internalGetMutableChatTagsList().getMutableMap();
    }
    
    public Builder putChatTagsList(String key, String value) {
      if (key == null)
        throw new NullPointerException("map key"); 
      if (value == null)
        throw new NullPointerException("map value"); 
      internalGetMutableChatTagsList().getMutableMap()
        .put(key, value);
      this.bitField0_ |= 0x400;
      return this;
    }
    
    public Builder putAllChatTagsList(Map<String, String> values) {
      internalGetMutableChatTagsList().getMutableMap()
        .putAll(values);
      this.bitField0_ |= 0x400;
      return this;
    }
    
    public final Builder setUnknownFields(UnknownFieldSet unknownFields) {
      return (Builder)super.setUnknownFields(unknownFields);
    }
    
    public final Builder mergeUnknownFields(UnknownFieldSet unknownFields) {
      return (Builder)super.mergeUnknownFields(unknownFields);
    }
  }
  
  private static final ChatMessage DEFAULT_INSTANCE = new ChatMessage();
  
  public static ChatMessage getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }
  
  private static final Parser<ChatMessage> PARSER = (Parser<ChatMessage>)new AbstractParser<ChatMessage>() {
      public ChatMessage parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        ChatMessage.Builder builder = ChatMessage.newBuilder();
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
  
  public static Parser<ChatMessage> parser() {
    return PARSER;
  }
  
  public Parser<ChatMessage> getParserForType() {
    return PARSER;
  }
  
  public ChatMessage getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }
}
