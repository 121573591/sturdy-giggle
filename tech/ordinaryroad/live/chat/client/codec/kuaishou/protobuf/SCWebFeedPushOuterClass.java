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

public final class SCWebFeedPushOuterClass {
  private static final Descriptors.Descriptor internal_static_SCWebFeedPush_descriptor;
  
  private static final GeneratedMessageV3.FieldAccessorTable internal_static_SCWebFeedPush_fieldAccessorTable;
  
  private static Descriptors.FileDescriptor descriptor;
  
  public static void registerAllExtensions(ExtensionRegistryLite registry) {}
  
  public static void registerAllExtensions(ExtensionRegistry registry) {
    registerAllExtensions((ExtensionRegistryLite)registry);
  }
  
  public static final class SCWebFeedPush extends GeneratedMessageV3 implements SCWebFeedPushOrBuilder {
    private static final long serialVersionUID = 0L;
    
    public static final int DISPLAYWATCHINGCOUNT_FIELD_NUMBER = 1;
    
    private volatile Object displayWatchingCount_;
    
    public static final int DISPLAYLIKECOUNT_FIELD_NUMBER = 2;
    
    private volatile Object displayLikeCount_;
    
    public static final int PENDINGLIKECOUNT_FIELD_NUMBER = 3;
    
    private long pendingLikeCount_;
    
    public static final int PUSHINTERVAL_FIELD_NUMBER = 4;
    
    private long pushInterval_;
    
    public static final int COMMENTFEEDS_FIELD_NUMBER = 5;
    
    private List<WebCommentFeedOuterClass.WebCommentFeed> commentFeeds_;
    
    public static final int COMMENTCURSOR_FIELD_NUMBER = 6;
    
    private volatile Object commentCursor_;
    
    public static final int COMBOCOMMENTFEED_FIELD_NUMBER = 7;
    
    private List<WebComboCommentFeedOuterClass.WebComboCommentFeed> comboCommentFeed_;
    
    public static final int LIKEFEEDS_FIELD_NUMBER = 8;
    
    private List<WebLikeFeedOuterClass.WebLikeFeed> likeFeeds_;
    
    public static final int GIFTFEEDS_FIELD_NUMBER = 9;
    
    private List<WebGiftFeedOuterClass.WebGiftFeed> giftFeeds_;
    
    public static final int GIFTCURSOR_FIELD_NUMBER = 10;
    
    private volatile Object giftCursor_;
    
    public static final int SYSTEMNOTICEFEEDS_FIELD_NUMBER = 11;
    
    private List<WebSystemNoticeFeedOuterClass.WebSystemNoticeFeed> systemNoticeFeeds_;
    
    public static final int SHAREFEEDS_FIELD_NUMBER = 12;
    
    private List<WebShareFeedOuterClass.WebShareFeed> shareFeeds_;
    
    private byte memoizedIsInitialized;
    
    private SCWebFeedPush(GeneratedMessageV3.Builder<?> builder) {
      super(builder);
      this.displayWatchingCount_ = "";
      this.displayLikeCount_ = "";
      this.pendingLikeCount_ = 0L;
      this.pushInterval_ = 0L;
      this.commentCursor_ = "";
      this.giftCursor_ = "";
      this.memoizedIsInitialized = -1;
    }
    
    private SCWebFeedPush() {
      this.displayWatchingCount_ = "";
      this.displayLikeCount_ = "";
      this.pendingLikeCount_ = 0L;
      this.pushInterval_ = 0L;
      this.commentCursor_ = "";
      this.giftCursor_ = "";
      this.memoizedIsInitialized = -1;
      this.displayWatchingCount_ = "";
      this.displayLikeCount_ = "";
      this.commentFeeds_ = Collections.emptyList();
      this.commentCursor_ = "";
      this.comboCommentFeed_ = Collections.emptyList();
      this.likeFeeds_ = Collections.emptyList();
      this.giftFeeds_ = Collections.emptyList();
      this.giftCursor_ = "";
      this.systemNoticeFeeds_ = Collections.emptyList();
      this.shareFeeds_ = Collections.emptyList();
    }
    
    protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
      return new SCWebFeedPush();
    }
    
    public static final Descriptors.Descriptor getDescriptor() {
      return SCWebFeedPushOuterClass.internal_static_SCWebFeedPush_descriptor;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return SCWebFeedPushOuterClass.internal_static_SCWebFeedPush_fieldAccessorTable.ensureFieldAccessorsInitialized(SCWebFeedPush.class, Builder.class);
    }
    
    public String getDisplayWatchingCount() {
      Object ref = this.displayWatchingCount_;
      if (ref instanceof String)
        return (String)ref; 
      ByteString bs = (ByteString)ref;
      String s = bs.toStringUtf8();
      this.displayWatchingCount_ = s;
      return s;
    }
    
    public ByteString getDisplayWatchingCountBytes() {
      Object ref = this.displayWatchingCount_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.displayWatchingCount_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public String getDisplayLikeCount() {
      Object ref = this.displayLikeCount_;
      if (ref instanceof String)
        return (String)ref; 
      ByteString bs = (ByteString)ref;
      String s = bs.toStringUtf8();
      this.displayLikeCount_ = s;
      return s;
    }
    
    public ByteString getDisplayLikeCountBytes() {
      Object ref = this.displayLikeCount_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.displayLikeCount_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public long getPendingLikeCount() {
      return this.pendingLikeCount_;
    }
    
    public long getPushInterval() {
      return this.pushInterval_;
    }
    
    public List<WebCommentFeedOuterClass.WebCommentFeed> getCommentFeedsList() {
      return this.commentFeeds_;
    }
    
    public List<? extends WebCommentFeedOuterClass.WebCommentFeedOrBuilder> getCommentFeedsOrBuilderList() {
      return (List)this.commentFeeds_;
    }
    
    public int getCommentFeedsCount() {
      return this.commentFeeds_.size();
    }
    
    public WebCommentFeedOuterClass.WebCommentFeed getCommentFeeds(int index) {
      return this.commentFeeds_.get(index);
    }
    
    public WebCommentFeedOuterClass.WebCommentFeedOrBuilder getCommentFeedsOrBuilder(int index) {
      return this.commentFeeds_.get(index);
    }
    
    public String getCommentCursor() {
      Object ref = this.commentCursor_;
      if (ref instanceof String)
        return (String)ref; 
      ByteString bs = (ByteString)ref;
      String s = bs.toStringUtf8();
      this.commentCursor_ = s;
      return s;
    }
    
    public ByteString getCommentCursorBytes() {
      Object ref = this.commentCursor_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.commentCursor_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public List<WebComboCommentFeedOuterClass.WebComboCommentFeed> getComboCommentFeedList() {
      return this.comboCommentFeed_;
    }
    
    public List<? extends WebComboCommentFeedOuterClass.WebComboCommentFeedOrBuilder> getComboCommentFeedOrBuilderList() {
      return (List)this.comboCommentFeed_;
    }
    
    public int getComboCommentFeedCount() {
      return this.comboCommentFeed_.size();
    }
    
    public WebComboCommentFeedOuterClass.WebComboCommentFeed getComboCommentFeed(int index) {
      return this.comboCommentFeed_.get(index);
    }
    
    public WebComboCommentFeedOuterClass.WebComboCommentFeedOrBuilder getComboCommentFeedOrBuilder(int index) {
      return this.comboCommentFeed_.get(index);
    }
    
    public List<WebLikeFeedOuterClass.WebLikeFeed> getLikeFeedsList() {
      return this.likeFeeds_;
    }
    
    public List<? extends WebLikeFeedOuterClass.WebLikeFeedOrBuilder> getLikeFeedsOrBuilderList() {
      return (List)this.likeFeeds_;
    }
    
    public int getLikeFeedsCount() {
      return this.likeFeeds_.size();
    }
    
    public WebLikeFeedOuterClass.WebLikeFeed getLikeFeeds(int index) {
      return this.likeFeeds_.get(index);
    }
    
    public WebLikeFeedOuterClass.WebLikeFeedOrBuilder getLikeFeedsOrBuilder(int index) {
      return this.likeFeeds_.get(index);
    }
    
    public List<WebGiftFeedOuterClass.WebGiftFeed> getGiftFeedsList() {
      return this.giftFeeds_;
    }
    
    public List<? extends WebGiftFeedOuterClass.WebGiftFeedOrBuilder> getGiftFeedsOrBuilderList() {
      return (List)this.giftFeeds_;
    }
    
    public int getGiftFeedsCount() {
      return this.giftFeeds_.size();
    }
    
    public WebGiftFeedOuterClass.WebGiftFeed getGiftFeeds(int index) {
      return this.giftFeeds_.get(index);
    }
    
    public WebGiftFeedOuterClass.WebGiftFeedOrBuilder getGiftFeedsOrBuilder(int index) {
      return this.giftFeeds_.get(index);
    }
    
    public String getGiftCursor() {
      Object ref = this.giftCursor_;
      if (ref instanceof String)
        return (String)ref; 
      ByteString bs = (ByteString)ref;
      String s = bs.toStringUtf8();
      this.giftCursor_ = s;
      return s;
    }
    
    public ByteString getGiftCursorBytes() {
      Object ref = this.giftCursor_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.giftCursor_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public List<WebSystemNoticeFeedOuterClass.WebSystemNoticeFeed> getSystemNoticeFeedsList() {
      return this.systemNoticeFeeds_;
    }
    
    public List<? extends WebSystemNoticeFeedOuterClass.WebSystemNoticeFeedOrBuilder> getSystemNoticeFeedsOrBuilderList() {
      return (List)this.systemNoticeFeeds_;
    }
    
    public int getSystemNoticeFeedsCount() {
      return this.systemNoticeFeeds_.size();
    }
    
    public WebSystemNoticeFeedOuterClass.WebSystemNoticeFeed getSystemNoticeFeeds(int index) {
      return this.systemNoticeFeeds_.get(index);
    }
    
    public WebSystemNoticeFeedOuterClass.WebSystemNoticeFeedOrBuilder getSystemNoticeFeedsOrBuilder(int index) {
      return this.systemNoticeFeeds_.get(index);
    }
    
    public List<WebShareFeedOuterClass.WebShareFeed> getShareFeedsList() {
      return this.shareFeeds_;
    }
    
    public List<? extends WebShareFeedOuterClass.WebShareFeedOrBuilder> getShareFeedsOrBuilderList() {
      return (List)this.shareFeeds_;
    }
    
    public int getShareFeedsCount() {
      return this.shareFeeds_.size();
    }
    
    public WebShareFeedOuterClass.WebShareFeed getShareFeeds(int index) {
      return this.shareFeeds_.get(index);
    }
    
    public WebShareFeedOuterClass.WebShareFeedOrBuilder getShareFeedsOrBuilder(int index) {
      return this.shareFeeds_.get(index);
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
      if (!GeneratedMessageV3.isStringEmpty(this.displayWatchingCount_))
        GeneratedMessageV3.writeString(output, 1, this.displayWatchingCount_); 
      if (!GeneratedMessageV3.isStringEmpty(this.displayLikeCount_))
        GeneratedMessageV3.writeString(output, 2, this.displayLikeCount_); 
      if (this.pendingLikeCount_ != 0L)
        output.writeUInt64(3, this.pendingLikeCount_); 
      if (this.pushInterval_ != 0L)
        output.writeUInt64(4, this.pushInterval_); 
      int i;
      for (i = 0; i < this.commentFeeds_.size(); i++)
        output.writeMessage(5, (MessageLite)this.commentFeeds_.get(i)); 
      if (!GeneratedMessageV3.isStringEmpty(this.commentCursor_))
        GeneratedMessageV3.writeString(output, 6, this.commentCursor_); 
      for (i = 0; i < this.comboCommentFeed_.size(); i++)
        output.writeMessage(7, (MessageLite)this.comboCommentFeed_.get(i)); 
      for (i = 0; i < this.likeFeeds_.size(); i++)
        output.writeMessage(8, (MessageLite)this.likeFeeds_.get(i)); 
      for (i = 0; i < this.giftFeeds_.size(); i++)
        output.writeMessage(9, (MessageLite)this.giftFeeds_.get(i)); 
      if (!GeneratedMessageV3.isStringEmpty(this.giftCursor_))
        GeneratedMessageV3.writeString(output, 10, this.giftCursor_); 
      for (i = 0; i < this.systemNoticeFeeds_.size(); i++)
        output.writeMessage(11, (MessageLite)this.systemNoticeFeeds_.get(i)); 
      for (i = 0; i < this.shareFeeds_.size(); i++)
        output.writeMessage(12, (MessageLite)this.shareFeeds_.get(i)); 
      getUnknownFields().writeTo(output);
    }
    
    public int getSerializedSize() {
      int size = this.memoizedSize;
      if (size != -1)
        return size; 
      size = 0;
      if (!GeneratedMessageV3.isStringEmpty(this.displayWatchingCount_))
        size += GeneratedMessageV3.computeStringSize(1, this.displayWatchingCount_); 
      if (!GeneratedMessageV3.isStringEmpty(this.displayLikeCount_))
        size += GeneratedMessageV3.computeStringSize(2, this.displayLikeCount_); 
      if (this.pendingLikeCount_ != 0L)
        size += 
          CodedOutputStream.computeUInt64Size(3, this.pendingLikeCount_); 
      if (this.pushInterval_ != 0L)
        size += 
          CodedOutputStream.computeUInt64Size(4, this.pushInterval_); 
      int i;
      for (i = 0; i < this.commentFeeds_.size(); i++)
        size += 
          CodedOutputStream.computeMessageSize(5, (MessageLite)this.commentFeeds_.get(i)); 
      if (!GeneratedMessageV3.isStringEmpty(this.commentCursor_))
        size += GeneratedMessageV3.computeStringSize(6, this.commentCursor_); 
      for (i = 0; i < this.comboCommentFeed_.size(); i++)
        size += 
          CodedOutputStream.computeMessageSize(7, (MessageLite)this.comboCommentFeed_.get(i)); 
      for (i = 0; i < this.likeFeeds_.size(); i++)
        size += 
          CodedOutputStream.computeMessageSize(8, (MessageLite)this.likeFeeds_.get(i)); 
      for (i = 0; i < this.giftFeeds_.size(); i++)
        size += 
          CodedOutputStream.computeMessageSize(9, (MessageLite)this.giftFeeds_.get(i)); 
      if (!GeneratedMessageV3.isStringEmpty(this.giftCursor_))
        size += GeneratedMessageV3.computeStringSize(10, this.giftCursor_); 
      for (i = 0; i < this.systemNoticeFeeds_.size(); i++)
        size += 
          CodedOutputStream.computeMessageSize(11, (MessageLite)this.systemNoticeFeeds_.get(i)); 
      for (i = 0; i < this.shareFeeds_.size(); i++)
        size += 
          CodedOutputStream.computeMessageSize(12, (MessageLite)this.shareFeeds_.get(i)); 
      size += getUnknownFields().getSerializedSize();
      this.memoizedSize = size;
      return size;
    }
    
    public boolean equals(Object obj) {
      if (obj == this)
        return true; 
      if (!(obj instanceof SCWebFeedPush))
        return super.equals(obj); 
      SCWebFeedPush other = (SCWebFeedPush)obj;
      if (!getDisplayWatchingCount().equals(other.getDisplayWatchingCount()))
        return false; 
      if (!getDisplayLikeCount().equals(other.getDisplayLikeCount()))
        return false; 
      if (getPendingLikeCount() != other
        .getPendingLikeCount())
        return false; 
      if (getPushInterval() != other
        .getPushInterval())
        return false; 
      if (!getCommentFeedsList().equals(other.getCommentFeedsList()))
        return false; 
      if (!getCommentCursor().equals(other.getCommentCursor()))
        return false; 
      if (!getComboCommentFeedList().equals(other.getComboCommentFeedList()))
        return false; 
      if (!getLikeFeedsList().equals(other.getLikeFeedsList()))
        return false; 
      if (!getGiftFeedsList().equals(other.getGiftFeedsList()))
        return false; 
      if (!getGiftCursor().equals(other.getGiftCursor()))
        return false; 
      if (!getSystemNoticeFeedsList().equals(other.getSystemNoticeFeedsList()))
        return false; 
      if (!getShareFeedsList().equals(other.getShareFeedsList()))
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
      hash = 53 * hash + getDisplayWatchingCount().hashCode();
      hash = 37 * hash + 2;
      hash = 53 * hash + getDisplayLikeCount().hashCode();
      hash = 37 * hash + 3;
      hash = 53 * hash + Internal.hashLong(
          getPendingLikeCount());
      hash = 37 * hash + 4;
      hash = 53 * hash + Internal.hashLong(
          getPushInterval());
      if (getCommentFeedsCount() > 0) {
        hash = 37 * hash + 5;
        hash = 53 * hash + getCommentFeedsList().hashCode();
      } 
      hash = 37 * hash + 6;
      hash = 53 * hash + getCommentCursor().hashCode();
      if (getComboCommentFeedCount() > 0) {
        hash = 37 * hash + 7;
        hash = 53 * hash + getComboCommentFeedList().hashCode();
      } 
      if (getLikeFeedsCount() > 0) {
        hash = 37 * hash + 8;
        hash = 53 * hash + getLikeFeedsList().hashCode();
      } 
      if (getGiftFeedsCount() > 0) {
        hash = 37 * hash + 9;
        hash = 53 * hash + getGiftFeedsList().hashCode();
      } 
      hash = 37 * hash + 10;
      hash = 53 * hash + getGiftCursor().hashCode();
      if (getSystemNoticeFeedsCount() > 0) {
        hash = 37 * hash + 11;
        hash = 53 * hash + getSystemNoticeFeedsList().hashCode();
      } 
      if (getShareFeedsCount() > 0) {
        hash = 37 * hash + 12;
        hash = 53 * hash + getShareFeedsList().hashCode();
      } 
      hash = 29 * hash + getUnknownFields().hashCode();
      this.memoizedHashCode = hash;
      return hash;
    }
    
    public static SCWebFeedPush parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (SCWebFeedPush)PARSER.parseFrom(data);
    }
    
    public static SCWebFeedPush parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (SCWebFeedPush)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static SCWebFeedPush parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (SCWebFeedPush)PARSER.parseFrom(data);
    }
    
    public static SCWebFeedPush parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (SCWebFeedPush)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static SCWebFeedPush parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (SCWebFeedPush)PARSER.parseFrom(data);
    }
    
    public static SCWebFeedPush parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (SCWebFeedPush)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static SCWebFeedPush parseFrom(InputStream input) throws IOException {
      return 
        (SCWebFeedPush)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static SCWebFeedPush parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (SCWebFeedPush)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static SCWebFeedPush parseDelimitedFrom(InputStream input) throws IOException {
      return 
        (SCWebFeedPush)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }
    
    public static SCWebFeedPush parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (SCWebFeedPush)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static SCWebFeedPush parseFrom(CodedInputStream input) throws IOException {
      return 
        (SCWebFeedPush)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static SCWebFeedPush parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return 
        (SCWebFeedPush)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public Builder newBuilderForType() {
      return newBuilder();
    }
    
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    
    public static Builder newBuilder(SCWebFeedPush prototype) {
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
    
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements SCWebFeedPushOuterClass.SCWebFeedPushOrBuilder {
      private int bitField0_;
      
      private Object displayWatchingCount_;
      
      private Object displayLikeCount_;
      
      private long pendingLikeCount_;
      
      private long pushInterval_;
      
      private List<WebCommentFeedOuterClass.WebCommentFeed> commentFeeds_;
      
      private RepeatedFieldBuilderV3<WebCommentFeedOuterClass.WebCommentFeed, WebCommentFeedOuterClass.WebCommentFeed.Builder, WebCommentFeedOuterClass.WebCommentFeedOrBuilder> commentFeedsBuilder_;
      
      private Object commentCursor_;
      
      private List<WebComboCommentFeedOuterClass.WebComboCommentFeed> comboCommentFeed_;
      
      private RepeatedFieldBuilderV3<WebComboCommentFeedOuterClass.WebComboCommentFeed, WebComboCommentFeedOuterClass.WebComboCommentFeed.Builder, WebComboCommentFeedOuterClass.WebComboCommentFeedOrBuilder> comboCommentFeedBuilder_;
      
      private List<WebLikeFeedOuterClass.WebLikeFeed> likeFeeds_;
      
      private RepeatedFieldBuilderV3<WebLikeFeedOuterClass.WebLikeFeed, WebLikeFeedOuterClass.WebLikeFeed.Builder, WebLikeFeedOuterClass.WebLikeFeedOrBuilder> likeFeedsBuilder_;
      
      private List<WebGiftFeedOuterClass.WebGiftFeed> giftFeeds_;
      
      private RepeatedFieldBuilderV3<WebGiftFeedOuterClass.WebGiftFeed, WebGiftFeedOuterClass.WebGiftFeed.Builder, WebGiftFeedOuterClass.WebGiftFeedOrBuilder> giftFeedsBuilder_;
      
      private Object giftCursor_;
      
      private List<WebSystemNoticeFeedOuterClass.WebSystemNoticeFeed> systemNoticeFeeds_;
      
      private RepeatedFieldBuilderV3<WebSystemNoticeFeedOuterClass.WebSystemNoticeFeed, WebSystemNoticeFeedOuterClass.WebSystemNoticeFeed.Builder, WebSystemNoticeFeedOuterClass.WebSystemNoticeFeedOrBuilder> systemNoticeFeedsBuilder_;
      
      private List<WebShareFeedOuterClass.WebShareFeed> shareFeeds_;
      
      private RepeatedFieldBuilderV3<WebShareFeedOuterClass.WebShareFeed, WebShareFeedOuterClass.WebShareFeed.Builder, WebShareFeedOuterClass.WebShareFeedOrBuilder> shareFeedsBuilder_;
      
      public static final Descriptors.Descriptor getDescriptor() {
        return SCWebFeedPushOuterClass.internal_static_SCWebFeedPush_descriptor;
      }
      
      protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return SCWebFeedPushOuterClass.internal_static_SCWebFeedPush_fieldAccessorTable
          .ensureFieldAccessorsInitialized(SCWebFeedPushOuterClass.SCWebFeedPush.class, Builder.class);
      }
      
      private Builder() {
        this.displayWatchingCount_ = "";
        this.displayLikeCount_ = "";
        this
          .commentFeeds_ = Collections.emptyList();
        this.commentCursor_ = "";
        this
          .comboCommentFeed_ = Collections.emptyList();
        this
          .likeFeeds_ = Collections.emptyList();
        this
          .giftFeeds_ = Collections.emptyList();
        this.giftCursor_ = "";
        this
          .systemNoticeFeeds_ = Collections.emptyList();
        this
          .shareFeeds_ = Collections.emptyList();
      }
      
      private Builder(GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        this.displayWatchingCount_ = "";
        this.displayLikeCount_ = "";
        this.commentFeeds_ = Collections.emptyList();
        this.commentCursor_ = "";
        this.comboCommentFeed_ = Collections.emptyList();
        this.likeFeeds_ = Collections.emptyList();
        this.giftFeeds_ = Collections.emptyList();
        this.giftCursor_ = "";
        this.systemNoticeFeeds_ = Collections.emptyList();
        this.shareFeeds_ = Collections.emptyList();
      }
      
      public Builder clear() {
        super.clear();
        this.bitField0_ = 0;
        this.displayWatchingCount_ = "";
        this.displayLikeCount_ = "";
        this.pendingLikeCount_ = 0L;
        this.pushInterval_ = 0L;
        if (this.commentFeedsBuilder_ == null) {
          this.commentFeeds_ = Collections.emptyList();
        } else {
          this.commentFeeds_ = null;
          this.commentFeedsBuilder_.clear();
        } 
        this.bitField0_ &= 0xFFFFFFEF;
        this.commentCursor_ = "";
        if (this.comboCommentFeedBuilder_ == null) {
          this.comboCommentFeed_ = Collections.emptyList();
        } else {
          this.comboCommentFeed_ = null;
          this.comboCommentFeedBuilder_.clear();
        } 
        this.bitField0_ &= 0xFFFFFFBF;
        if (this.likeFeedsBuilder_ == null) {
          this.likeFeeds_ = Collections.emptyList();
        } else {
          this.likeFeeds_ = null;
          this.likeFeedsBuilder_.clear();
        } 
        this.bitField0_ &= 0xFFFFFF7F;
        if (this.giftFeedsBuilder_ == null) {
          this.giftFeeds_ = Collections.emptyList();
        } else {
          this.giftFeeds_ = null;
          this.giftFeedsBuilder_.clear();
        } 
        this.bitField0_ &= 0xFFFFFEFF;
        this.giftCursor_ = "";
        if (this.systemNoticeFeedsBuilder_ == null) {
          this.systemNoticeFeeds_ = Collections.emptyList();
        } else {
          this.systemNoticeFeeds_ = null;
          this.systemNoticeFeedsBuilder_.clear();
        } 
        this.bitField0_ &= 0xFFFFFBFF;
        if (this.shareFeedsBuilder_ == null) {
          this.shareFeeds_ = Collections.emptyList();
        } else {
          this.shareFeeds_ = null;
          this.shareFeedsBuilder_.clear();
        } 
        this.bitField0_ &= 0xFFFFF7FF;
        return this;
      }
      
      public Descriptors.Descriptor getDescriptorForType() {
        return SCWebFeedPushOuterClass.internal_static_SCWebFeedPush_descriptor;
      }
      
      public SCWebFeedPushOuterClass.SCWebFeedPush getDefaultInstanceForType() {
        return SCWebFeedPushOuterClass.SCWebFeedPush.getDefaultInstance();
      }
      
      public SCWebFeedPushOuterClass.SCWebFeedPush build() {
        SCWebFeedPushOuterClass.SCWebFeedPush result = buildPartial();
        if (!result.isInitialized())
          throw newUninitializedMessageException(result); 
        return result;
      }
      
      public SCWebFeedPushOuterClass.SCWebFeedPush buildPartial() {
        SCWebFeedPushOuterClass.SCWebFeedPush result = new SCWebFeedPushOuterClass.SCWebFeedPush(this);
        buildPartialRepeatedFields(result);
        if (this.bitField0_ != 0)
          buildPartial0(result); 
        onBuilt();
        return result;
      }
      
      private void buildPartialRepeatedFields(SCWebFeedPushOuterClass.SCWebFeedPush result) {
        if (this.commentFeedsBuilder_ == null) {
          if ((this.bitField0_ & 0x10) != 0) {
            this.commentFeeds_ = Collections.unmodifiableList(this.commentFeeds_);
            this.bitField0_ &= 0xFFFFFFEF;
          } 
          result.commentFeeds_ = this.commentFeeds_;
        } else {
          result.commentFeeds_ = this.commentFeedsBuilder_.build();
        } 
        if (this.comboCommentFeedBuilder_ == null) {
          if ((this.bitField0_ & 0x40) != 0) {
            this.comboCommentFeed_ = Collections.unmodifiableList(this.comboCommentFeed_);
            this.bitField0_ &= 0xFFFFFFBF;
          } 
          result.comboCommentFeed_ = this.comboCommentFeed_;
        } else {
          result.comboCommentFeed_ = this.comboCommentFeedBuilder_.build();
        } 
        if (this.likeFeedsBuilder_ == null) {
          if ((this.bitField0_ & 0x80) != 0) {
            this.likeFeeds_ = Collections.unmodifiableList(this.likeFeeds_);
            this.bitField0_ &= 0xFFFFFF7F;
          } 
          result.likeFeeds_ = this.likeFeeds_;
        } else {
          result.likeFeeds_ = this.likeFeedsBuilder_.build();
        } 
        if (this.giftFeedsBuilder_ == null) {
          if ((this.bitField0_ & 0x100) != 0) {
            this.giftFeeds_ = Collections.unmodifiableList(this.giftFeeds_);
            this.bitField0_ &= 0xFFFFFEFF;
          } 
          result.giftFeeds_ = this.giftFeeds_;
        } else {
          result.giftFeeds_ = this.giftFeedsBuilder_.build();
        } 
        if (this.systemNoticeFeedsBuilder_ == null) {
          if ((this.bitField0_ & 0x400) != 0) {
            this.systemNoticeFeeds_ = Collections.unmodifiableList(this.systemNoticeFeeds_);
            this.bitField0_ &= 0xFFFFFBFF;
          } 
          result.systemNoticeFeeds_ = this.systemNoticeFeeds_;
        } else {
          result.systemNoticeFeeds_ = this.systemNoticeFeedsBuilder_.build();
        } 
        if (this.shareFeedsBuilder_ == null) {
          if ((this.bitField0_ & 0x800) != 0) {
            this.shareFeeds_ = Collections.unmodifiableList(this.shareFeeds_);
            this.bitField0_ &= 0xFFFFF7FF;
          } 
          result.shareFeeds_ = this.shareFeeds_;
        } else {
          result.shareFeeds_ = this.shareFeedsBuilder_.build();
        } 
      }
      
      private void buildPartial0(SCWebFeedPushOuterClass.SCWebFeedPush result) {
        int from_bitField0_ = this.bitField0_;
        if ((from_bitField0_ & 0x1) != 0)
          result.displayWatchingCount_ = this.displayWatchingCount_; 
        if ((from_bitField0_ & 0x2) != 0)
          result.displayLikeCount_ = this.displayLikeCount_; 
        if ((from_bitField0_ & 0x4) != 0)
          result.pendingLikeCount_ = this.pendingLikeCount_; 
        if ((from_bitField0_ & 0x8) != 0)
          result.pushInterval_ = this.pushInterval_; 
        if ((from_bitField0_ & 0x20) != 0)
          result.commentCursor_ = this.commentCursor_; 
        if ((from_bitField0_ & 0x200) != 0)
          result.giftCursor_ = this.giftCursor_; 
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
        if (other instanceof SCWebFeedPushOuterClass.SCWebFeedPush)
          return mergeFrom((SCWebFeedPushOuterClass.SCWebFeedPush)other); 
        super.mergeFrom(other);
        return this;
      }
      
      public Builder mergeFrom(SCWebFeedPushOuterClass.SCWebFeedPush other) {
        if (other == SCWebFeedPushOuterClass.SCWebFeedPush.getDefaultInstance())
          return this; 
        if (!other.getDisplayWatchingCount().isEmpty()) {
          this.displayWatchingCount_ = other.displayWatchingCount_;
          this.bitField0_ |= 0x1;
          onChanged();
        } 
        if (!other.getDisplayLikeCount().isEmpty()) {
          this.displayLikeCount_ = other.displayLikeCount_;
          this.bitField0_ |= 0x2;
          onChanged();
        } 
        if (other.getPendingLikeCount() != 0L)
          setPendingLikeCount(other.getPendingLikeCount()); 
        if (other.getPushInterval() != 0L)
          setPushInterval(other.getPushInterval()); 
        if (this.commentFeedsBuilder_ == null) {
          if (!other.commentFeeds_.isEmpty()) {
            if (this.commentFeeds_.isEmpty()) {
              this.commentFeeds_ = other.commentFeeds_;
              this.bitField0_ &= 0xFFFFFFEF;
            } else {
              ensureCommentFeedsIsMutable();
              this.commentFeeds_.addAll(other.commentFeeds_);
            } 
            onChanged();
          } 
        } else if (!other.commentFeeds_.isEmpty()) {
          if (this.commentFeedsBuilder_.isEmpty()) {
            this.commentFeedsBuilder_.dispose();
            this.commentFeedsBuilder_ = null;
            this.commentFeeds_ = other.commentFeeds_;
            this.bitField0_ &= 0xFFFFFFEF;
            this.commentFeedsBuilder_ = SCWebFeedPushOuterClass.SCWebFeedPush.alwaysUseFieldBuilders ? getCommentFeedsFieldBuilder() : null;
          } else {
            this.commentFeedsBuilder_.addAllMessages(other.commentFeeds_);
          } 
        } 
        if (!other.getCommentCursor().isEmpty()) {
          this.commentCursor_ = other.commentCursor_;
          this.bitField0_ |= 0x20;
          onChanged();
        } 
        if (this.comboCommentFeedBuilder_ == null) {
          if (!other.comboCommentFeed_.isEmpty()) {
            if (this.comboCommentFeed_.isEmpty()) {
              this.comboCommentFeed_ = other.comboCommentFeed_;
              this.bitField0_ &= 0xFFFFFFBF;
            } else {
              ensureComboCommentFeedIsMutable();
              this.comboCommentFeed_.addAll(other.comboCommentFeed_);
            } 
            onChanged();
          } 
        } else if (!other.comboCommentFeed_.isEmpty()) {
          if (this.comboCommentFeedBuilder_.isEmpty()) {
            this.comboCommentFeedBuilder_.dispose();
            this.comboCommentFeedBuilder_ = null;
            this.comboCommentFeed_ = other.comboCommentFeed_;
            this.bitField0_ &= 0xFFFFFFBF;
            this.comboCommentFeedBuilder_ = SCWebFeedPushOuterClass.SCWebFeedPush.alwaysUseFieldBuilders ? getComboCommentFeedFieldBuilder() : null;
          } else {
            this.comboCommentFeedBuilder_.addAllMessages(other.comboCommentFeed_);
          } 
        } 
        if (this.likeFeedsBuilder_ == null) {
          if (!other.likeFeeds_.isEmpty()) {
            if (this.likeFeeds_.isEmpty()) {
              this.likeFeeds_ = other.likeFeeds_;
              this.bitField0_ &= 0xFFFFFF7F;
            } else {
              ensureLikeFeedsIsMutable();
              this.likeFeeds_.addAll(other.likeFeeds_);
            } 
            onChanged();
          } 
        } else if (!other.likeFeeds_.isEmpty()) {
          if (this.likeFeedsBuilder_.isEmpty()) {
            this.likeFeedsBuilder_.dispose();
            this.likeFeedsBuilder_ = null;
            this.likeFeeds_ = other.likeFeeds_;
            this.bitField0_ &= 0xFFFFFF7F;
            this.likeFeedsBuilder_ = SCWebFeedPushOuterClass.SCWebFeedPush.alwaysUseFieldBuilders ? getLikeFeedsFieldBuilder() : null;
          } else {
            this.likeFeedsBuilder_.addAllMessages(other.likeFeeds_);
          } 
        } 
        if (this.giftFeedsBuilder_ == null) {
          if (!other.giftFeeds_.isEmpty()) {
            if (this.giftFeeds_.isEmpty()) {
              this.giftFeeds_ = other.giftFeeds_;
              this.bitField0_ &= 0xFFFFFEFF;
            } else {
              ensureGiftFeedsIsMutable();
              this.giftFeeds_.addAll(other.giftFeeds_);
            } 
            onChanged();
          } 
        } else if (!other.giftFeeds_.isEmpty()) {
          if (this.giftFeedsBuilder_.isEmpty()) {
            this.giftFeedsBuilder_.dispose();
            this.giftFeedsBuilder_ = null;
            this.giftFeeds_ = other.giftFeeds_;
            this.bitField0_ &= 0xFFFFFEFF;
            this.giftFeedsBuilder_ = SCWebFeedPushOuterClass.SCWebFeedPush.alwaysUseFieldBuilders ? getGiftFeedsFieldBuilder() : null;
          } else {
            this.giftFeedsBuilder_.addAllMessages(other.giftFeeds_);
          } 
        } 
        if (!other.getGiftCursor().isEmpty()) {
          this.giftCursor_ = other.giftCursor_;
          this.bitField0_ |= 0x200;
          onChanged();
        } 
        if (this.systemNoticeFeedsBuilder_ == null) {
          if (!other.systemNoticeFeeds_.isEmpty()) {
            if (this.systemNoticeFeeds_.isEmpty()) {
              this.systemNoticeFeeds_ = other.systemNoticeFeeds_;
              this.bitField0_ &= 0xFFFFFBFF;
            } else {
              ensureSystemNoticeFeedsIsMutable();
              this.systemNoticeFeeds_.addAll(other.systemNoticeFeeds_);
            } 
            onChanged();
          } 
        } else if (!other.systemNoticeFeeds_.isEmpty()) {
          if (this.systemNoticeFeedsBuilder_.isEmpty()) {
            this.systemNoticeFeedsBuilder_.dispose();
            this.systemNoticeFeedsBuilder_ = null;
            this.systemNoticeFeeds_ = other.systemNoticeFeeds_;
            this.bitField0_ &= 0xFFFFFBFF;
            this.systemNoticeFeedsBuilder_ = SCWebFeedPushOuterClass.SCWebFeedPush.alwaysUseFieldBuilders ? getSystemNoticeFeedsFieldBuilder() : null;
          } else {
            this.systemNoticeFeedsBuilder_.addAllMessages(other.systemNoticeFeeds_);
          } 
        } 
        if (this.shareFeedsBuilder_ == null) {
          if (!other.shareFeeds_.isEmpty()) {
            if (this.shareFeeds_.isEmpty()) {
              this.shareFeeds_ = other.shareFeeds_;
              this.bitField0_ &= 0xFFFFF7FF;
            } else {
              ensureShareFeedsIsMutable();
              this.shareFeeds_.addAll(other.shareFeeds_);
            } 
            onChanged();
          } 
        } else if (!other.shareFeeds_.isEmpty()) {
          if (this.shareFeedsBuilder_.isEmpty()) {
            this.shareFeedsBuilder_.dispose();
            this.shareFeedsBuilder_ = null;
            this.shareFeeds_ = other.shareFeeds_;
            this.bitField0_ &= 0xFFFFF7FF;
            this.shareFeedsBuilder_ = SCWebFeedPushOuterClass.SCWebFeedPush.alwaysUseFieldBuilders ? getShareFeedsFieldBuilder() : null;
          } else {
            this.shareFeedsBuilder_.addAllMessages(other.shareFeeds_);
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
            WebCommentFeedOuterClass.WebCommentFeed webCommentFeed;
            WebComboCommentFeedOuterClass.WebComboCommentFeed webComboCommentFeed;
            WebLikeFeedOuterClass.WebLikeFeed webLikeFeed;
            WebGiftFeedOuterClass.WebGiftFeed webGiftFeed;
            WebSystemNoticeFeedOuterClass.WebSystemNoticeFeed webSystemNoticeFeed;
            WebShareFeedOuterClass.WebShareFeed m;
            int tag = input.readTag();
            switch (tag) {
              case 0:
                done = true;
                continue;
              case 10:
                this.displayWatchingCount_ = input.readStringRequireUtf8();
                this.bitField0_ |= 0x1;
                continue;
              case 18:
                this.displayLikeCount_ = input.readStringRequireUtf8();
                this.bitField0_ |= 0x2;
                continue;
              case 24:
                this.pendingLikeCount_ = input.readUInt64();
                this.bitField0_ |= 0x4;
                continue;
              case 32:
                this.pushInterval_ = input.readUInt64();
                this.bitField0_ |= 0x8;
                continue;
              case 42:
                webCommentFeed = (WebCommentFeedOuterClass.WebCommentFeed)input.readMessage(WebCommentFeedOuterClass.WebCommentFeed.parser(), extensionRegistry);
                if (this.commentFeedsBuilder_ == null) {
                  ensureCommentFeedsIsMutable();
                  this.commentFeeds_.add(webCommentFeed);
                  continue;
                } 
                this.commentFeedsBuilder_.addMessage((AbstractMessage)webCommentFeed);
                continue;
              case 50:
                this.commentCursor_ = input.readStringRequireUtf8();
                this.bitField0_ |= 0x20;
                continue;
              case 58:
                webComboCommentFeed = (WebComboCommentFeedOuterClass.WebComboCommentFeed)input.readMessage(WebComboCommentFeedOuterClass.WebComboCommentFeed.parser(), extensionRegistry);
                if (this.comboCommentFeedBuilder_ == null) {
                  ensureComboCommentFeedIsMutable();
                  this.comboCommentFeed_.add(webComboCommentFeed);
                  continue;
                } 
                this.comboCommentFeedBuilder_.addMessage((AbstractMessage)webComboCommentFeed);
                continue;
              case 66:
                webLikeFeed = (WebLikeFeedOuterClass.WebLikeFeed)input.readMessage(WebLikeFeedOuterClass.WebLikeFeed.parser(), extensionRegistry);
                if (this.likeFeedsBuilder_ == null) {
                  ensureLikeFeedsIsMutable();
                  this.likeFeeds_.add(webLikeFeed);
                  continue;
                } 
                this.likeFeedsBuilder_.addMessage((AbstractMessage)webLikeFeed);
                continue;
              case 74:
                webGiftFeed = (WebGiftFeedOuterClass.WebGiftFeed)input.readMessage(WebGiftFeedOuterClass.WebGiftFeed.parser(), extensionRegistry);
                if (this.giftFeedsBuilder_ == null) {
                  ensureGiftFeedsIsMutable();
                  this.giftFeeds_.add(webGiftFeed);
                  continue;
                } 
                this.giftFeedsBuilder_.addMessage((AbstractMessage)webGiftFeed);
                continue;
              case 82:
                this.giftCursor_ = input.readStringRequireUtf8();
                this.bitField0_ |= 0x200;
                continue;
              case 90:
                webSystemNoticeFeed = (WebSystemNoticeFeedOuterClass.WebSystemNoticeFeed)input.readMessage(WebSystemNoticeFeedOuterClass.WebSystemNoticeFeed.parser(), extensionRegistry);
                if (this.systemNoticeFeedsBuilder_ == null) {
                  ensureSystemNoticeFeedsIsMutable();
                  this.systemNoticeFeeds_.add(webSystemNoticeFeed);
                  continue;
                } 
                this.systemNoticeFeedsBuilder_.addMessage((AbstractMessage)webSystemNoticeFeed);
                continue;
              case 98:
                m = (WebShareFeedOuterClass.WebShareFeed)input.readMessage(WebShareFeedOuterClass.WebShareFeed.parser(), extensionRegistry);
                if (this.shareFeedsBuilder_ == null) {
                  ensureShareFeedsIsMutable();
                  this.shareFeeds_.add(m);
                  continue;
                } 
                this.shareFeedsBuilder_.addMessage((AbstractMessage)m);
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
      
      public String getDisplayWatchingCount() {
        Object ref = this.displayWatchingCount_;
        if (!(ref instanceof String)) {
          ByteString bs = (ByteString)ref;
          String s = bs.toStringUtf8();
          this.displayWatchingCount_ = s;
          return s;
        } 
        return (String)ref;
      }
      
      public ByteString getDisplayWatchingCountBytes() {
        Object ref = this.displayWatchingCount_;
        if (ref instanceof String) {
          ByteString b = ByteString.copyFromUtf8((String)ref);
          this.displayWatchingCount_ = b;
          return b;
        } 
        return (ByteString)ref;
      }
      
      public Builder setDisplayWatchingCount(String value) {
        if (value == null)
          throw new NullPointerException(); 
        this.displayWatchingCount_ = value;
        this.bitField0_ |= 0x1;
        onChanged();
        return this;
      }
      
      public Builder clearDisplayWatchingCount() {
        this.displayWatchingCount_ = SCWebFeedPushOuterClass.SCWebFeedPush.getDefaultInstance().getDisplayWatchingCount();
        this.bitField0_ &= 0xFFFFFFFE;
        onChanged();
        return this;
      }
      
      public Builder setDisplayWatchingCountBytes(ByteString value) {
        if (value == null)
          throw new NullPointerException(); 
        SCWebFeedPushOuterClass.SCWebFeedPush.checkByteStringIsUtf8(value);
        this.displayWatchingCount_ = value;
        this.bitField0_ |= 0x1;
        onChanged();
        return this;
      }
      
      public String getDisplayLikeCount() {
        Object ref = this.displayLikeCount_;
        if (!(ref instanceof String)) {
          ByteString bs = (ByteString)ref;
          String s = bs.toStringUtf8();
          this.displayLikeCount_ = s;
          return s;
        } 
        return (String)ref;
      }
      
      public ByteString getDisplayLikeCountBytes() {
        Object ref = this.displayLikeCount_;
        if (ref instanceof String) {
          ByteString b = ByteString.copyFromUtf8((String)ref);
          this.displayLikeCount_ = b;
          return b;
        } 
        return (ByteString)ref;
      }
      
      public Builder setDisplayLikeCount(String value) {
        if (value == null)
          throw new NullPointerException(); 
        this.displayLikeCount_ = value;
        this.bitField0_ |= 0x2;
        onChanged();
        return this;
      }
      
      public Builder clearDisplayLikeCount() {
        this.displayLikeCount_ = SCWebFeedPushOuterClass.SCWebFeedPush.getDefaultInstance().getDisplayLikeCount();
        this.bitField0_ &= 0xFFFFFFFD;
        onChanged();
        return this;
      }
      
      public Builder setDisplayLikeCountBytes(ByteString value) {
        if (value == null)
          throw new NullPointerException(); 
        SCWebFeedPushOuterClass.SCWebFeedPush.checkByteStringIsUtf8(value);
        this.displayLikeCount_ = value;
        this.bitField0_ |= 0x2;
        onChanged();
        return this;
      }
      
      public long getPendingLikeCount() {
        return this.pendingLikeCount_;
      }
      
      public Builder setPendingLikeCount(long value) {
        this.pendingLikeCount_ = value;
        this.bitField0_ |= 0x4;
        onChanged();
        return this;
      }
      
      public Builder clearPendingLikeCount() {
        this.bitField0_ &= 0xFFFFFFFB;
        this.pendingLikeCount_ = 0L;
        onChanged();
        return this;
      }
      
      public long getPushInterval() {
        return this.pushInterval_;
      }
      
      public Builder setPushInterval(long value) {
        this.pushInterval_ = value;
        this.bitField0_ |= 0x8;
        onChanged();
        return this;
      }
      
      public Builder clearPushInterval() {
        this.bitField0_ &= 0xFFFFFFF7;
        this.pushInterval_ = 0L;
        onChanged();
        return this;
      }
      
      private void ensureCommentFeedsIsMutable() {
        if ((this.bitField0_ & 0x10) == 0) {
          this.commentFeeds_ = new ArrayList<>(this.commentFeeds_);
          this.bitField0_ |= 0x10;
        } 
      }
      
      public List<WebCommentFeedOuterClass.WebCommentFeed> getCommentFeedsList() {
        if (this.commentFeedsBuilder_ == null)
          return Collections.unmodifiableList(this.commentFeeds_); 
        return this.commentFeedsBuilder_.getMessageList();
      }
      
      public int getCommentFeedsCount() {
        if (this.commentFeedsBuilder_ == null)
          return this.commentFeeds_.size(); 
        return this.commentFeedsBuilder_.getCount();
      }
      
      public WebCommentFeedOuterClass.WebCommentFeed getCommentFeeds(int index) {
        if (this.commentFeedsBuilder_ == null)
          return this.commentFeeds_.get(index); 
        return (WebCommentFeedOuterClass.WebCommentFeed)this.commentFeedsBuilder_.getMessage(index);
      }
      
      public Builder setCommentFeeds(int index, WebCommentFeedOuterClass.WebCommentFeed value) {
        if (this.commentFeedsBuilder_ == null) {
          if (value == null)
            throw new NullPointerException(); 
          ensureCommentFeedsIsMutable();
          this.commentFeeds_.set(index, value);
          onChanged();
        } else {
          this.commentFeedsBuilder_.setMessage(index, (AbstractMessage)value);
        } 
        return this;
      }
      
      public Builder setCommentFeeds(int index, WebCommentFeedOuterClass.WebCommentFeed.Builder builderForValue) {
        if (this.commentFeedsBuilder_ == null) {
          ensureCommentFeedsIsMutable();
          this.commentFeeds_.set(index, builderForValue.build());
          onChanged();
        } else {
          this.commentFeedsBuilder_.setMessage(index, (AbstractMessage)builderForValue.build());
        } 
        return this;
      }
      
      public Builder addCommentFeeds(WebCommentFeedOuterClass.WebCommentFeed value) {
        if (this.commentFeedsBuilder_ == null) {
          if (value == null)
            throw new NullPointerException(); 
          ensureCommentFeedsIsMutable();
          this.commentFeeds_.add(value);
          onChanged();
        } else {
          this.commentFeedsBuilder_.addMessage((AbstractMessage)value);
        } 
        return this;
      }
      
      public Builder addCommentFeeds(int index, WebCommentFeedOuterClass.WebCommentFeed value) {
        if (this.commentFeedsBuilder_ == null) {
          if (value == null)
            throw new NullPointerException(); 
          ensureCommentFeedsIsMutable();
          this.commentFeeds_.add(index, value);
          onChanged();
        } else {
          this.commentFeedsBuilder_.addMessage(index, (AbstractMessage)value);
        } 
        return this;
      }
      
      public Builder addCommentFeeds(WebCommentFeedOuterClass.WebCommentFeed.Builder builderForValue) {
        if (this.commentFeedsBuilder_ == null) {
          ensureCommentFeedsIsMutable();
          this.commentFeeds_.add(builderForValue.build());
          onChanged();
        } else {
          this.commentFeedsBuilder_.addMessage((AbstractMessage)builderForValue.build());
        } 
        return this;
      }
      
      public Builder addCommentFeeds(int index, WebCommentFeedOuterClass.WebCommentFeed.Builder builderForValue) {
        if (this.commentFeedsBuilder_ == null) {
          ensureCommentFeedsIsMutable();
          this.commentFeeds_.add(index, builderForValue.build());
          onChanged();
        } else {
          this.commentFeedsBuilder_.addMessage(index, (AbstractMessage)builderForValue.build());
        } 
        return this;
      }
      
      public Builder addAllCommentFeeds(Iterable<? extends WebCommentFeedOuterClass.WebCommentFeed> values) {
        if (this.commentFeedsBuilder_ == null) {
          ensureCommentFeedsIsMutable();
          AbstractMessageLite.Builder.addAll(values, this.commentFeeds_);
          onChanged();
        } else {
          this.commentFeedsBuilder_.addAllMessages(values);
        } 
        return this;
      }
      
      public Builder clearCommentFeeds() {
        if (this.commentFeedsBuilder_ == null) {
          this.commentFeeds_ = Collections.emptyList();
          this.bitField0_ &= 0xFFFFFFEF;
          onChanged();
        } else {
          this.commentFeedsBuilder_.clear();
        } 
        return this;
      }
      
      public Builder removeCommentFeeds(int index) {
        if (this.commentFeedsBuilder_ == null) {
          ensureCommentFeedsIsMutable();
          this.commentFeeds_.remove(index);
          onChanged();
        } else {
          this.commentFeedsBuilder_.remove(index);
        } 
        return this;
      }
      
      public WebCommentFeedOuterClass.WebCommentFeed.Builder getCommentFeedsBuilder(int index) {
        return (WebCommentFeedOuterClass.WebCommentFeed.Builder)getCommentFeedsFieldBuilder().getBuilder(index);
      }
      
      public WebCommentFeedOuterClass.WebCommentFeedOrBuilder getCommentFeedsOrBuilder(int index) {
        if (this.commentFeedsBuilder_ == null)
          return this.commentFeeds_.get(index); 
        return (WebCommentFeedOuterClass.WebCommentFeedOrBuilder)this.commentFeedsBuilder_.getMessageOrBuilder(index);
      }
      
      public List<? extends WebCommentFeedOuterClass.WebCommentFeedOrBuilder> getCommentFeedsOrBuilderList() {
        if (this.commentFeedsBuilder_ != null)
          return this.commentFeedsBuilder_.getMessageOrBuilderList(); 
        return Collections.unmodifiableList((List)this.commentFeeds_);
      }
      
      public WebCommentFeedOuterClass.WebCommentFeed.Builder addCommentFeedsBuilder() {
        return (WebCommentFeedOuterClass.WebCommentFeed.Builder)getCommentFeedsFieldBuilder().addBuilder((AbstractMessage)WebCommentFeedOuterClass.WebCommentFeed.getDefaultInstance());
      }
      
      public WebCommentFeedOuterClass.WebCommentFeed.Builder addCommentFeedsBuilder(int index) {
        return (WebCommentFeedOuterClass.WebCommentFeed.Builder)getCommentFeedsFieldBuilder().addBuilder(index, (AbstractMessage)WebCommentFeedOuterClass.WebCommentFeed.getDefaultInstance());
      }
      
      public List<WebCommentFeedOuterClass.WebCommentFeed.Builder> getCommentFeedsBuilderList() {
        return getCommentFeedsFieldBuilder().getBuilderList();
      }
      
      private RepeatedFieldBuilderV3<WebCommentFeedOuterClass.WebCommentFeed, WebCommentFeedOuterClass.WebCommentFeed.Builder, WebCommentFeedOuterClass.WebCommentFeedOrBuilder> getCommentFeedsFieldBuilder() {
        if (this.commentFeedsBuilder_ == null) {
          this.commentFeedsBuilder_ = new RepeatedFieldBuilderV3(this.commentFeeds_, ((this.bitField0_ & 0x10) != 0), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
          this.commentFeeds_ = null;
        } 
        return this.commentFeedsBuilder_;
      }
      
      public String getCommentCursor() {
        Object ref = this.commentCursor_;
        if (!(ref instanceof String)) {
          ByteString bs = (ByteString)ref;
          String s = bs.toStringUtf8();
          this.commentCursor_ = s;
          return s;
        } 
        return (String)ref;
      }
      
      public ByteString getCommentCursorBytes() {
        Object ref = this.commentCursor_;
        if (ref instanceof String) {
          ByteString b = ByteString.copyFromUtf8((String)ref);
          this.commentCursor_ = b;
          return b;
        } 
        return (ByteString)ref;
      }
      
      public Builder setCommentCursor(String value) {
        if (value == null)
          throw new NullPointerException(); 
        this.commentCursor_ = value;
        this.bitField0_ |= 0x20;
        onChanged();
        return this;
      }
      
      public Builder clearCommentCursor() {
        this.commentCursor_ = SCWebFeedPushOuterClass.SCWebFeedPush.getDefaultInstance().getCommentCursor();
        this.bitField0_ &= 0xFFFFFFDF;
        onChanged();
        return this;
      }
      
      public Builder setCommentCursorBytes(ByteString value) {
        if (value == null)
          throw new NullPointerException(); 
        SCWebFeedPushOuterClass.SCWebFeedPush.checkByteStringIsUtf8(value);
        this.commentCursor_ = value;
        this.bitField0_ |= 0x20;
        onChanged();
        return this;
      }
      
      private void ensureComboCommentFeedIsMutable() {
        if ((this.bitField0_ & 0x40) == 0) {
          this.comboCommentFeed_ = new ArrayList<>(this.comboCommentFeed_);
          this.bitField0_ |= 0x40;
        } 
      }
      
      public List<WebComboCommentFeedOuterClass.WebComboCommentFeed> getComboCommentFeedList() {
        if (this.comboCommentFeedBuilder_ == null)
          return Collections.unmodifiableList(this.comboCommentFeed_); 
        return this.comboCommentFeedBuilder_.getMessageList();
      }
      
      public int getComboCommentFeedCount() {
        if (this.comboCommentFeedBuilder_ == null)
          return this.comboCommentFeed_.size(); 
        return this.comboCommentFeedBuilder_.getCount();
      }
      
      public WebComboCommentFeedOuterClass.WebComboCommentFeed getComboCommentFeed(int index) {
        if (this.comboCommentFeedBuilder_ == null)
          return this.comboCommentFeed_.get(index); 
        return (WebComboCommentFeedOuterClass.WebComboCommentFeed)this.comboCommentFeedBuilder_.getMessage(index);
      }
      
      public Builder setComboCommentFeed(int index, WebComboCommentFeedOuterClass.WebComboCommentFeed value) {
        if (this.comboCommentFeedBuilder_ == null) {
          if (value == null)
            throw new NullPointerException(); 
          ensureComboCommentFeedIsMutable();
          this.comboCommentFeed_.set(index, value);
          onChanged();
        } else {
          this.comboCommentFeedBuilder_.setMessage(index, (AbstractMessage)value);
        } 
        return this;
      }
      
      public Builder setComboCommentFeed(int index, WebComboCommentFeedOuterClass.WebComboCommentFeed.Builder builderForValue) {
        if (this.comboCommentFeedBuilder_ == null) {
          ensureComboCommentFeedIsMutable();
          this.comboCommentFeed_.set(index, builderForValue.build());
          onChanged();
        } else {
          this.comboCommentFeedBuilder_.setMessage(index, (AbstractMessage)builderForValue.build());
        } 
        return this;
      }
      
      public Builder addComboCommentFeed(WebComboCommentFeedOuterClass.WebComboCommentFeed value) {
        if (this.comboCommentFeedBuilder_ == null) {
          if (value == null)
            throw new NullPointerException(); 
          ensureComboCommentFeedIsMutable();
          this.comboCommentFeed_.add(value);
          onChanged();
        } else {
          this.comboCommentFeedBuilder_.addMessage((AbstractMessage)value);
        } 
        return this;
      }
      
      public Builder addComboCommentFeed(int index, WebComboCommentFeedOuterClass.WebComboCommentFeed value) {
        if (this.comboCommentFeedBuilder_ == null) {
          if (value == null)
            throw new NullPointerException(); 
          ensureComboCommentFeedIsMutable();
          this.comboCommentFeed_.add(index, value);
          onChanged();
        } else {
          this.comboCommentFeedBuilder_.addMessage(index, (AbstractMessage)value);
        } 
        return this;
      }
      
      public Builder addComboCommentFeed(WebComboCommentFeedOuterClass.WebComboCommentFeed.Builder builderForValue) {
        if (this.comboCommentFeedBuilder_ == null) {
          ensureComboCommentFeedIsMutable();
          this.comboCommentFeed_.add(builderForValue.build());
          onChanged();
        } else {
          this.comboCommentFeedBuilder_.addMessage((AbstractMessage)builderForValue.build());
        } 
        return this;
      }
      
      public Builder addComboCommentFeed(int index, WebComboCommentFeedOuterClass.WebComboCommentFeed.Builder builderForValue) {
        if (this.comboCommentFeedBuilder_ == null) {
          ensureComboCommentFeedIsMutable();
          this.comboCommentFeed_.add(index, builderForValue.build());
          onChanged();
        } else {
          this.comboCommentFeedBuilder_.addMessage(index, (AbstractMessage)builderForValue.build());
        } 
        return this;
      }
      
      public Builder addAllComboCommentFeed(Iterable<? extends WebComboCommentFeedOuterClass.WebComboCommentFeed> values) {
        if (this.comboCommentFeedBuilder_ == null) {
          ensureComboCommentFeedIsMutable();
          AbstractMessageLite.Builder.addAll(values, this.comboCommentFeed_);
          onChanged();
        } else {
          this.comboCommentFeedBuilder_.addAllMessages(values);
        } 
        return this;
      }
      
      public Builder clearComboCommentFeed() {
        if (this.comboCommentFeedBuilder_ == null) {
          this.comboCommentFeed_ = Collections.emptyList();
          this.bitField0_ &= 0xFFFFFFBF;
          onChanged();
        } else {
          this.comboCommentFeedBuilder_.clear();
        } 
        return this;
      }
      
      public Builder removeComboCommentFeed(int index) {
        if (this.comboCommentFeedBuilder_ == null) {
          ensureComboCommentFeedIsMutable();
          this.comboCommentFeed_.remove(index);
          onChanged();
        } else {
          this.comboCommentFeedBuilder_.remove(index);
        } 
        return this;
      }
      
      public WebComboCommentFeedOuterClass.WebComboCommentFeed.Builder getComboCommentFeedBuilder(int index) {
        return (WebComboCommentFeedOuterClass.WebComboCommentFeed.Builder)getComboCommentFeedFieldBuilder().getBuilder(index);
      }
      
      public WebComboCommentFeedOuterClass.WebComboCommentFeedOrBuilder getComboCommentFeedOrBuilder(int index) {
        if (this.comboCommentFeedBuilder_ == null)
          return this.comboCommentFeed_.get(index); 
        return (WebComboCommentFeedOuterClass.WebComboCommentFeedOrBuilder)this.comboCommentFeedBuilder_.getMessageOrBuilder(index);
      }
      
      public List<? extends WebComboCommentFeedOuterClass.WebComboCommentFeedOrBuilder> getComboCommentFeedOrBuilderList() {
        if (this.comboCommentFeedBuilder_ != null)
          return this.comboCommentFeedBuilder_.getMessageOrBuilderList(); 
        return Collections.unmodifiableList((List)this.comboCommentFeed_);
      }
      
      public WebComboCommentFeedOuterClass.WebComboCommentFeed.Builder addComboCommentFeedBuilder() {
        return (WebComboCommentFeedOuterClass.WebComboCommentFeed.Builder)getComboCommentFeedFieldBuilder().addBuilder((AbstractMessage)WebComboCommentFeedOuterClass.WebComboCommentFeed.getDefaultInstance());
      }
      
      public WebComboCommentFeedOuterClass.WebComboCommentFeed.Builder addComboCommentFeedBuilder(int index) {
        return (WebComboCommentFeedOuterClass.WebComboCommentFeed.Builder)getComboCommentFeedFieldBuilder().addBuilder(index, (AbstractMessage)WebComboCommentFeedOuterClass.WebComboCommentFeed.getDefaultInstance());
      }
      
      public List<WebComboCommentFeedOuterClass.WebComboCommentFeed.Builder> getComboCommentFeedBuilderList() {
        return getComboCommentFeedFieldBuilder().getBuilderList();
      }
      
      private RepeatedFieldBuilderV3<WebComboCommentFeedOuterClass.WebComboCommentFeed, WebComboCommentFeedOuterClass.WebComboCommentFeed.Builder, WebComboCommentFeedOuterClass.WebComboCommentFeedOrBuilder> getComboCommentFeedFieldBuilder() {
        if (this.comboCommentFeedBuilder_ == null) {
          this.comboCommentFeedBuilder_ = new RepeatedFieldBuilderV3(this.comboCommentFeed_, ((this.bitField0_ & 0x40) != 0), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
          this.comboCommentFeed_ = null;
        } 
        return this.comboCommentFeedBuilder_;
      }
      
      private void ensureLikeFeedsIsMutable() {
        if ((this.bitField0_ & 0x80) == 0) {
          this.likeFeeds_ = new ArrayList<>(this.likeFeeds_);
          this.bitField0_ |= 0x80;
        } 
      }
      
      public List<WebLikeFeedOuterClass.WebLikeFeed> getLikeFeedsList() {
        if (this.likeFeedsBuilder_ == null)
          return Collections.unmodifiableList(this.likeFeeds_); 
        return this.likeFeedsBuilder_.getMessageList();
      }
      
      public int getLikeFeedsCount() {
        if (this.likeFeedsBuilder_ == null)
          return this.likeFeeds_.size(); 
        return this.likeFeedsBuilder_.getCount();
      }
      
      public WebLikeFeedOuterClass.WebLikeFeed getLikeFeeds(int index) {
        if (this.likeFeedsBuilder_ == null)
          return this.likeFeeds_.get(index); 
        return (WebLikeFeedOuterClass.WebLikeFeed)this.likeFeedsBuilder_.getMessage(index);
      }
      
      public Builder setLikeFeeds(int index, WebLikeFeedOuterClass.WebLikeFeed value) {
        if (this.likeFeedsBuilder_ == null) {
          if (value == null)
            throw new NullPointerException(); 
          ensureLikeFeedsIsMutable();
          this.likeFeeds_.set(index, value);
          onChanged();
        } else {
          this.likeFeedsBuilder_.setMessage(index, (AbstractMessage)value);
        } 
        return this;
      }
      
      public Builder setLikeFeeds(int index, WebLikeFeedOuterClass.WebLikeFeed.Builder builderForValue) {
        if (this.likeFeedsBuilder_ == null) {
          ensureLikeFeedsIsMutable();
          this.likeFeeds_.set(index, builderForValue.build());
          onChanged();
        } else {
          this.likeFeedsBuilder_.setMessage(index, (AbstractMessage)builderForValue.build());
        } 
        return this;
      }
      
      public Builder addLikeFeeds(WebLikeFeedOuterClass.WebLikeFeed value) {
        if (this.likeFeedsBuilder_ == null) {
          if (value == null)
            throw new NullPointerException(); 
          ensureLikeFeedsIsMutable();
          this.likeFeeds_.add(value);
          onChanged();
        } else {
          this.likeFeedsBuilder_.addMessage((AbstractMessage)value);
        } 
        return this;
      }
      
      public Builder addLikeFeeds(int index, WebLikeFeedOuterClass.WebLikeFeed value) {
        if (this.likeFeedsBuilder_ == null) {
          if (value == null)
            throw new NullPointerException(); 
          ensureLikeFeedsIsMutable();
          this.likeFeeds_.add(index, value);
          onChanged();
        } else {
          this.likeFeedsBuilder_.addMessage(index, (AbstractMessage)value);
        } 
        return this;
      }
      
      public Builder addLikeFeeds(WebLikeFeedOuterClass.WebLikeFeed.Builder builderForValue) {
        if (this.likeFeedsBuilder_ == null) {
          ensureLikeFeedsIsMutable();
          this.likeFeeds_.add(builderForValue.build());
          onChanged();
        } else {
          this.likeFeedsBuilder_.addMessage((AbstractMessage)builderForValue.build());
        } 
        return this;
      }
      
      public Builder addLikeFeeds(int index, WebLikeFeedOuterClass.WebLikeFeed.Builder builderForValue) {
        if (this.likeFeedsBuilder_ == null) {
          ensureLikeFeedsIsMutable();
          this.likeFeeds_.add(index, builderForValue.build());
          onChanged();
        } else {
          this.likeFeedsBuilder_.addMessage(index, (AbstractMessage)builderForValue.build());
        } 
        return this;
      }
      
      public Builder addAllLikeFeeds(Iterable<? extends WebLikeFeedOuterClass.WebLikeFeed> values) {
        if (this.likeFeedsBuilder_ == null) {
          ensureLikeFeedsIsMutable();
          AbstractMessageLite.Builder.addAll(values, this.likeFeeds_);
          onChanged();
        } else {
          this.likeFeedsBuilder_.addAllMessages(values);
        } 
        return this;
      }
      
      public Builder clearLikeFeeds() {
        if (this.likeFeedsBuilder_ == null) {
          this.likeFeeds_ = Collections.emptyList();
          this.bitField0_ &= 0xFFFFFF7F;
          onChanged();
        } else {
          this.likeFeedsBuilder_.clear();
        } 
        return this;
      }
      
      public Builder removeLikeFeeds(int index) {
        if (this.likeFeedsBuilder_ == null) {
          ensureLikeFeedsIsMutable();
          this.likeFeeds_.remove(index);
          onChanged();
        } else {
          this.likeFeedsBuilder_.remove(index);
        } 
        return this;
      }
      
      public WebLikeFeedOuterClass.WebLikeFeed.Builder getLikeFeedsBuilder(int index) {
        return (WebLikeFeedOuterClass.WebLikeFeed.Builder)getLikeFeedsFieldBuilder().getBuilder(index);
      }
      
      public WebLikeFeedOuterClass.WebLikeFeedOrBuilder getLikeFeedsOrBuilder(int index) {
        if (this.likeFeedsBuilder_ == null)
          return this.likeFeeds_.get(index); 
        return (WebLikeFeedOuterClass.WebLikeFeedOrBuilder)this.likeFeedsBuilder_.getMessageOrBuilder(index);
      }
      
      public List<? extends WebLikeFeedOuterClass.WebLikeFeedOrBuilder> getLikeFeedsOrBuilderList() {
        if (this.likeFeedsBuilder_ != null)
          return this.likeFeedsBuilder_.getMessageOrBuilderList(); 
        return Collections.unmodifiableList((List)this.likeFeeds_);
      }
      
      public WebLikeFeedOuterClass.WebLikeFeed.Builder addLikeFeedsBuilder() {
        return (WebLikeFeedOuterClass.WebLikeFeed.Builder)getLikeFeedsFieldBuilder().addBuilder((AbstractMessage)WebLikeFeedOuterClass.WebLikeFeed.getDefaultInstance());
      }
      
      public WebLikeFeedOuterClass.WebLikeFeed.Builder addLikeFeedsBuilder(int index) {
        return (WebLikeFeedOuterClass.WebLikeFeed.Builder)getLikeFeedsFieldBuilder().addBuilder(index, (AbstractMessage)WebLikeFeedOuterClass.WebLikeFeed.getDefaultInstance());
      }
      
      public List<WebLikeFeedOuterClass.WebLikeFeed.Builder> getLikeFeedsBuilderList() {
        return getLikeFeedsFieldBuilder().getBuilderList();
      }
      
      private RepeatedFieldBuilderV3<WebLikeFeedOuterClass.WebLikeFeed, WebLikeFeedOuterClass.WebLikeFeed.Builder, WebLikeFeedOuterClass.WebLikeFeedOrBuilder> getLikeFeedsFieldBuilder() {
        if (this.likeFeedsBuilder_ == null) {
          this.likeFeedsBuilder_ = new RepeatedFieldBuilderV3(this.likeFeeds_, ((this.bitField0_ & 0x80) != 0), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
          this.likeFeeds_ = null;
        } 
        return this.likeFeedsBuilder_;
      }
      
      private void ensureGiftFeedsIsMutable() {
        if ((this.bitField0_ & 0x100) == 0) {
          this.giftFeeds_ = new ArrayList<>(this.giftFeeds_);
          this.bitField0_ |= 0x100;
        } 
      }
      
      public List<WebGiftFeedOuterClass.WebGiftFeed> getGiftFeedsList() {
        if (this.giftFeedsBuilder_ == null)
          return Collections.unmodifiableList(this.giftFeeds_); 
        return this.giftFeedsBuilder_.getMessageList();
      }
      
      public int getGiftFeedsCount() {
        if (this.giftFeedsBuilder_ == null)
          return this.giftFeeds_.size(); 
        return this.giftFeedsBuilder_.getCount();
      }
      
      public WebGiftFeedOuterClass.WebGiftFeed getGiftFeeds(int index) {
        if (this.giftFeedsBuilder_ == null)
          return this.giftFeeds_.get(index); 
        return (WebGiftFeedOuterClass.WebGiftFeed)this.giftFeedsBuilder_.getMessage(index);
      }
      
      public Builder setGiftFeeds(int index, WebGiftFeedOuterClass.WebGiftFeed value) {
        if (this.giftFeedsBuilder_ == null) {
          if (value == null)
            throw new NullPointerException(); 
          ensureGiftFeedsIsMutable();
          this.giftFeeds_.set(index, value);
          onChanged();
        } else {
          this.giftFeedsBuilder_.setMessage(index, (AbstractMessage)value);
        } 
        return this;
      }
      
      public Builder setGiftFeeds(int index, WebGiftFeedOuterClass.WebGiftFeed.Builder builderForValue) {
        if (this.giftFeedsBuilder_ == null) {
          ensureGiftFeedsIsMutable();
          this.giftFeeds_.set(index, builderForValue.build());
          onChanged();
        } else {
          this.giftFeedsBuilder_.setMessage(index, (AbstractMessage)builderForValue.build());
        } 
        return this;
      }
      
      public Builder addGiftFeeds(WebGiftFeedOuterClass.WebGiftFeed value) {
        if (this.giftFeedsBuilder_ == null) {
          if (value == null)
            throw new NullPointerException(); 
          ensureGiftFeedsIsMutable();
          this.giftFeeds_.add(value);
          onChanged();
        } else {
          this.giftFeedsBuilder_.addMessage((AbstractMessage)value);
        } 
        return this;
      }
      
      public Builder addGiftFeeds(int index, WebGiftFeedOuterClass.WebGiftFeed value) {
        if (this.giftFeedsBuilder_ == null) {
          if (value == null)
            throw new NullPointerException(); 
          ensureGiftFeedsIsMutable();
          this.giftFeeds_.add(index, value);
          onChanged();
        } else {
          this.giftFeedsBuilder_.addMessage(index, (AbstractMessage)value);
        } 
        return this;
      }
      
      public Builder addGiftFeeds(WebGiftFeedOuterClass.WebGiftFeed.Builder builderForValue) {
        if (this.giftFeedsBuilder_ == null) {
          ensureGiftFeedsIsMutable();
          this.giftFeeds_.add(builderForValue.build());
          onChanged();
        } else {
          this.giftFeedsBuilder_.addMessage((AbstractMessage)builderForValue.build());
        } 
        return this;
      }
      
      public Builder addGiftFeeds(int index, WebGiftFeedOuterClass.WebGiftFeed.Builder builderForValue) {
        if (this.giftFeedsBuilder_ == null) {
          ensureGiftFeedsIsMutable();
          this.giftFeeds_.add(index, builderForValue.build());
          onChanged();
        } else {
          this.giftFeedsBuilder_.addMessage(index, (AbstractMessage)builderForValue.build());
        } 
        return this;
      }
      
      public Builder addAllGiftFeeds(Iterable<? extends WebGiftFeedOuterClass.WebGiftFeed> values) {
        if (this.giftFeedsBuilder_ == null) {
          ensureGiftFeedsIsMutable();
          AbstractMessageLite.Builder.addAll(values, this.giftFeeds_);
          onChanged();
        } else {
          this.giftFeedsBuilder_.addAllMessages(values);
        } 
        return this;
      }
      
      public Builder clearGiftFeeds() {
        if (this.giftFeedsBuilder_ == null) {
          this.giftFeeds_ = Collections.emptyList();
          this.bitField0_ &= 0xFFFFFEFF;
          onChanged();
        } else {
          this.giftFeedsBuilder_.clear();
        } 
        return this;
      }
      
      public Builder removeGiftFeeds(int index) {
        if (this.giftFeedsBuilder_ == null) {
          ensureGiftFeedsIsMutable();
          this.giftFeeds_.remove(index);
          onChanged();
        } else {
          this.giftFeedsBuilder_.remove(index);
        } 
        return this;
      }
      
      public WebGiftFeedOuterClass.WebGiftFeed.Builder getGiftFeedsBuilder(int index) {
        return (WebGiftFeedOuterClass.WebGiftFeed.Builder)getGiftFeedsFieldBuilder().getBuilder(index);
      }
      
      public WebGiftFeedOuterClass.WebGiftFeedOrBuilder getGiftFeedsOrBuilder(int index) {
        if (this.giftFeedsBuilder_ == null)
          return this.giftFeeds_.get(index); 
        return (WebGiftFeedOuterClass.WebGiftFeedOrBuilder)this.giftFeedsBuilder_.getMessageOrBuilder(index);
      }
      
      public List<? extends WebGiftFeedOuterClass.WebGiftFeedOrBuilder> getGiftFeedsOrBuilderList() {
        if (this.giftFeedsBuilder_ != null)
          return this.giftFeedsBuilder_.getMessageOrBuilderList(); 
        return Collections.unmodifiableList((List)this.giftFeeds_);
      }
      
      public WebGiftFeedOuterClass.WebGiftFeed.Builder addGiftFeedsBuilder() {
        return (WebGiftFeedOuterClass.WebGiftFeed.Builder)getGiftFeedsFieldBuilder().addBuilder((AbstractMessage)WebGiftFeedOuterClass.WebGiftFeed.getDefaultInstance());
      }
      
      public WebGiftFeedOuterClass.WebGiftFeed.Builder addGiftFeedsBuilder(int index) {
        return (WebGiftFeedOuterClass.WebGiftFeed.Builder)getGiftFeedsFieldBuilder().addBuilder(index, (AbstractMessage)WebGiftFeedOuterClass.WebGiftFeed.getDefaultInstance());
      }
      
      public List<WebGiftFeedOuterClass.WebGiftFeed.Builder> getGiftFeedsBuilderList() {
        return getGiftFeedsFieldBuilder().getBuilderList();
      }
      
      private RepeatedFieldBuilderV3<WebGiftFeedOuterClass.WebGiftFeed, WebGiftFeedOuterClass.WebGiftFeed.Builder, WebGiftFeedOuterClass.WebGiftFeedOrBuilder> getGiftFeedsFieldBuilder() {
        if (this.giftFeedsBuilder_ == null) {
          this.giftFeedsBuilder_ = new RepeatedFieldBuilderV3(this.giftFeeds_, ((this.bitField0_ & 0x100) != 0), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
          this.giftFeeds_ = null;
        } 
        return this.giftFeedsBuilder_;
      }
      
      public String getGiftCursor() {
        Object ref = this.giftCursor_;
        if (!(ref instanceof String)) {
          ByteString bs = (ByteString)ref;
          String s = bs.toStringUtf8();
          this.giftCursor_ = s;
          return s;
        } 
        return (String)ref;
      }
      
      public ByteString getGiftCursorBytes() {
        Object ref = this.giftCursor_;
        if (ref instanceof String) {
          ByteString b = ByteString.copyFromUtf8((String)ref);
          this.giftCursor_ = b;
          return b;
        } 
        return (ByteString)ref;
      }
      
      public Builder setGiftCursor(String value) {
        if (value == null)
          throw new NullPointerException(); 
        this.giftCursor_ = value;
        this.bitField0_ |= 0x200;
        onChanged();
        return this;
      }
      
      public Builder clearGiftCursor() {
        this.giftCursor_ = SCWebFeedPushOuterClass.SCWebFeedPush.getDefaultInstance().getGiftCursor();
        this.bitField0_ &= 0xFFFFFDFF;
        onChanged();
        return this;
      }
      
      public Builder setGiftCursorBytes(ByteString value) {
        if (value == null)
          throw new NullPointerException(); 
        SCWebFeedPushOuterClass.SCWebFeedPush.checkByteStringIsUtf8(value);
        this.giftCursor_ = value;
        this.bitField0_ |= 0x200;
        onChanged();
        return this;
      }
      
      private void ensureSystemNoticeFeedsIsMutable() {
        if ((this.bitField0_ & 0x400) == 0) {
          this.systemNoticeFeeds_ = new ArrayList<>(this.systemNoticeFeeds_);
          this.bitField0_ |= 0x400;
        } 
      }
      
      public List<WebSystemNoticeFeedOuterClass.WebSystemNoticeFeed> getSystemNoticeFeedsList() {
        if (this.systemNoticeFeedsBuilder_ == null)
          return Collections.unmodifiableList(this.systemNoticeFeeds_); 
        return this.systemNoticeFeedsBuilder_.getMessageList();
      }
      
      public int getSystemNoticeFeedsCount() {
        if (this.systemNoticeFeedsBuilder_ == null)
          return this.systemNoticeFeeds_.size(); 
        return this.systemNoticeFeedsBuilder_.getCount();
      }
      
      public WebSystemNoticeFeedOuterClass.WebSystemNoticeFeed getSystemNoticeFeeds(int index) {
        if (this.systemNoticeFeedsBuilder_ == null)
          return this.systemNoticeFeeds_.get(index); 
        return (WebSystemNoticeFeedOuterClass.WebSystemNoticeFeed)this.systemNoticeFeedsBuilder_.getMessage(index);
      }
      
      public Builder setSystemNoticeFeeds(int index, WebSystemNoticeFeedOuterClass.WebSystemNoticeFeed value) {
        if (this.systemNoticeFeedsBuilder_ == null) {
          if (value == null)
            throw new NullPointerException(); 
          ensureSystemNoticeFeedsIsMutable();
          this.systemNoticeFeeds_.set(index, value);
          onChanged();
        } else {
          this.systemNoticeFeedsBuilder_.setMessage(index, (AbstractMessage)value);
        } 
        return this;
      }
      
      public Builder setSystemNoticeFeeds(int index, WebSystemNoticeFeedOuterClass.WebSystemNoticeFeed.Builder builderForValue) {
        if (this.systemNoticeFeedsBuilder_ == null) {
          ensureSystemNoticeFeedsIsMutable();
          this.systemNoticeFeeds_.set(index, builderForValue.build());
          onChanged();
        } else {
          this.systemNoticeFeedsBuilder_.setMessage(index, (AbstractMessage)builderForValue.build());
        } 
        return this;
      }
      
      public Builder addSystemNoticeFeeds(WebSystemNoticeFeedOuterClass.WebSystemNoticeFeed value) {
        if (this.systemNoticeFeedsBuilder_ == null) {
          if (value == null)
            throw new NullPointerException(); 
          ensureSystemNoticeFeedsIsMutable();
          this.systemNoticeFeeds_.add(value);
          onChanged();
        } else {
          this.systemNoticeFeedsBuilder_.addMessage((AbstractMessage)value);
        } 
        return this;
      }
      
      public Builder addSystemNoticeFeeds(int index, WebSystemNoticeFeedOuterClass.WebSystemNoticeFeed value) {
        if (this.systemNoticeFeedsBuilder_ == null) {
          if (value == null)
            throw new NullPointerException(); 
          ensureSystemNoticeFeedsIsMutable();
          this.systemNoticeFeeds_.add(index, value);
          onChanged();
        } else {
          this.systemNoticeFeedsBuilder_.addMessage(index, (AbstractMessage)value);
        } 
        return this;
      }
      
      public Builder addSystemNoticeFeeds(WebSystemNoticeFeedOuterClass.WebSystemNoticeFeed.Builder builderForValue) {
        if (this.systemNoticeFeedsBuilder_ == null) {
          ensureSystemNoticeFeedsIsMutable();
          this.systemNoticeFeeds_.add(builderForValue.build());
          onChanged();
        } else {
          this.systemNoticeFeedsBuilder_.addMessage((AbstractMessage)builderForValue.build());
        } 
        return this;
      }
      
      public Builder addSystemNoticeFeeds(int index, WebSystemNoticeFeedOuterClass.WebSystemNoticeFeed.Builder builderForValue) {
        if (this.systemNoticeFeedsBuilder_ == null) {
          ensureSystemNoticeFeedsIsMutable();
          this.systemNoticeFeeds_.add(index, builderForValue.build());
          onChanged();
        } else {
          this.systemNoticeFeedsBuilder_.addMessage(index, (AbstractMessage)builderForValue.build());
        } 
        return this;
      }
      
      public Builder addAllSystemNoticeFeeds(Iterable<? extends WebSystemNoticeFeedOuterClass.WebSystemNoticeFeed> values) {
        if (this.systemNoticeFeedsBuilder_ == null) {
          ensureSystemNoticeFeedsIsMutable();
          AbstractMessageLite.Builder.addAll(values, this.systemNoticeFeeds_);
          onChanged();
        } else {
          this.systemNoticeFeedsBuilder_.addAllMessages(values);
        } 
        return this;
      }
      
      public Builder clearSystemNoticeFeeds() {
        if (this.systemNoticeFeedsBuilder_ == null) {
          this.systemNoticeFeeds_ = Collections.emptyList();
          this.bitField0_ &= 0xFFFFFBFF;
          onChanged();
        } else {
          this.systemNoticeFeedsBuilder_.clear();
        } 
        return this;
      }
      
      public Builder removeSystemNoticeFeeds(int index) {
        if (this.systemNoticeFeedsBuilder_ == null) {
          ensureSystemNoticeFeedsIsMutable();
          this.systemNoticeFeeds_.remove(index);
          onChanged();
        } else {
          this.systemNoticeFeedsBuilder_.remove(index);
        } 
        return this;
      }
      
      public WebSystemNoticeFeedOuterClass.WebSystemNoticeFeed.Builder getSystemNoticeFeedsBuilder(int index) {
        return (WebSystemNoticeFeedOuterClass.WebSystemNoticeFeed.Builder)getSystemNoticeFeedsFieldBuilder().getBuilder(index);
      }
      
      public WebSystemNoticeFeedOuterClass.WebSystemNoticeFeedOrBuilder getSystemNoticeFeedsOrBuilder(int index) {
        if (this.systemNoticeFeedsBuilder_ == null)
          return this.systemNoticeFeeds_.get(index); 
        return (WebSystemNoticeFeedOuterClass.WebSystemNoticeFeedOrBuilder)this.systemNoticeFeedsBuilder_.getMessageOrBuilder(index);
      }
      
      public List<? extends WebSystemNoticeFeedOuterClass.WebSystemNoticeFeedOrBuilder> getSystemNoticeFeedsOrBuilderList() {
        if (this.systemNoticeFeedsBuilder_ != null)
          return this.systemNoticeFeedsBuilder_.getMessageOrBuilderList(); 
        return Collections.unmodifiableList((List)this.systemNoticeFeeds_);
      }
      
      public WebSystemNoticeFeedOuterClass.WebSystemNoticeFeed.Builder addSystemNoticeFeedsBuilder() {
        return (WebSystemNoticeFeedOuterClass.WebSystemNoticeFeed.Builder)getSystemNoticeFeedsFieldBuilder().addBuilder((AbstractMessage)WebSystemNoticeFeedOuterClass.WebSystemNoticeFeed.getDefaultInstance());
      }
      
      public WebSystemNoticeFeedOuterClass.WebSystemNoticeFeed.Builder addSystemNoticeFeedsBuilder(int index) {
        return (WebSystemNoticeFeedOuterClass.WebSystemNoticeFeed.Builder)getSystemNoticeFeedsFieldBuilder().addBuilder(index, (AbstractMessage)WebSystemNoticeFeedOuterClass.WebSystemNoticeFeed.getDefaultInstance());
      }
      
      public List<WebSystemNoticeFeedOuterClass.WebSystemNoticeFeed.Builder> getSystemNoticeFeedsBuilderList() {
        return getSystemNoticeFeedsFieldBuilder().getBuilderList();
      }
      
      private RepeatedFieldBuilderV3<WebSystemNoticeFeedOuterClass.WebSystemNoticeFeed, WebSystemNoticeFeedOuterClass.WebSystemNoticeFeed.Builder, WebSystemNoticeFeedOuterClass.WebSystemNoticeFeedOrBuilder> getSystemNoticeFeedsFieldBuilder() {
        if (this.systemNoticeFeedsBuilder_ == null) {
          this.systemNoticeFeedsBuilder_ = new RepeatedFieldBuilderV3(this.systemNoticeFeeds_, ((this.bitField0_ & 0x400) != 0), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
          this.systemNoticeFeeds_ = null;
        } 
        return this.systemNoticeFeedsBuilder_;
      }
      
      private void ensureShareFeedsIsMutable() {
        if ((this.bitField0_ & 0x800) == 0) {
          this.shareFeeds_ = new ArrayList<>(this.shareFeeds_);
          this.bitField0_ |= 0x800;
        } 
      }
      
      public List<WebShareFeedOuterClass.WebShareFeed> getShareFeedsList() {
        if (this.shareFeedsBuilder_ == null)
          return Collections.unmodifiableList(this.shareFeeds_); 
        return this.shareFeedsBuilder_.getMessageList();
      }
      
      public int getShareFeedsCount() {
        if (this.shareFeedsBuilder_ == null)
          return this.shareFeeds_.size(); 
        return this.shareFeedsBuilder_.getCount();
      }
      
      public WebShareFeedOuterClass.WebShareFeed getShareFeeds(int index) {
        if (this.shareFeedsBuilder_ == null)
          return this.shareFeeds_.get(index); 
        return (WebShareFeedOuterClass.WebShareFeed)this.shareFeedsBuilder_.getMessage(index);
      }
      
      public Builder setShareFeeds(int index, WebShareFeedOuterClass.WebShareFeed value) {
        if (this.shareFeedsBuilder_ == null) {
          if (value == null)
            throw new NullPointerException(); 
          ensureShareFeedsIsMutable();
          this.shareFeeds_.set(index, value);
          onChanged();
        } else {
          this.shareFeedsBuilder_.setMessage(index, (AbstractMessage)value);
        } 
        return this;
      }
      
      public Builder setShareFeeds(int index, WebShareFeedOuterClass.WebShareFeed.Builder builderForValue) {
        if (this.shareFeedsBuilder_ == null) {
          ensureShareFeedsIsMutable();
          this.shareFeeds_.set(index, builderForValue.build());
          onChanged();
        } else {
          this.shareFeedsBuilder_.setMessage(index, (AbstractMessage)builderForValue.build());
        } 
        return this;
      }
      
      public Builder addShareFeeds(WebShareFeedOuterClass.WebShareFeed value) {
        if (this.shareFeedsBuilder_ == null) {
          if (value == null)
            throw new NullPointerException(); 
          ensureShareFeedsIsMutable();
          this.shareFeeds_.add(value);
          onChanged();
        } else {
          this.shareFeedsBuilder_.addMessage((AbstractMessage)value);
        } 
        return this;
      }
      
      public Builder addShareFeeds(int index, WebShareFeedOuterClass.WebShareFeed value) {
        if (this.shareFeedsBuilder_ == null) {
          if (value == null)
            throw new NullPointerException(); 
          ensureShareFeedsIsMutable();
          this.shareFeeds_.add(index, value);
          onChanged();
        } else {
          this.shareFeedsBuilder_.addMessage(index, (AbstractMessage)value);
        } 
        return this;
      }
      
      public Builder addShareFeeds(WebShareFeedOuterClass.WebShareFeed.Builder builderForValue) {
        if (this.shareFeedsBuilder_ == null) {
          ensureShareFeedsIsMutable();
          this.shareFeeds_.add(builderForValue.build());
          onChanged();
        } else {
          this.shareFeedsBuilder_.addMessage((AbstractMessage)builderForValue.build());
        } 
        return this;
      }
      
      public Builder addShareFeeds(int index, WebShareFeedOuterClass.WebShareFeed.Builder builderForValue) {
        if (this.shareFeedsBuilder_ == null) {
          ensureShareFeedsIsMutable();
          this.shareFeeds_.add(index, builderForValue.build());
          onChanged();
        } else {
          this.shareFeedsBuilder_.addMessage(index, (AbstractMessage)builderForValue.build());
        } 
        return this;
      }
      
      public Builder addAllShareFeeds(Iterable<? extends WebShareFeedOuterClass.WebShareFeed> values) {
        if (this.shareFeedsBuilder_ == null) {
          ensureShareFeedsIsMutable();
          AbstractMessageLite.Builder.addAll(values, this.shareFeeds_);
          onChanged();
        } else {
          this.shareFeedsBuilder_.addAllMessages(values);
        } 
        return this;
      }
      
      public Builder clearShareFeeds() {
        if (this.shareFeedsBuilder_ == null) {
          this.shareFeeds_ = Collections.emptyList();
          this.bitField0_ &= 0xFFFFF7FF;
          onChanged();
        } else {
          this.shareFeedsBuilder_.clear();
        } 
        return this;
      }
      
      public Builder removeShareFeeds(int index) {
        if (this.shareFeedsBuilder_ == null) {
          ensureShareFeedsIsMutable();
          this.shareFeeds_.remove(index);
          onChanged();
        } else {
          this.shareFeedsBuilder_.remove(index);
        } 
        return this;
      }
      
      public WebShareFeedOuterClass.WebShareFeed.Builder getShareFeedsBuilder(int index) {
        return (WebShareFeedOuterClass.WebShareFeed.Builder)getShareFeedsFieldBuilder().getBuilder(index);
      }
      
      public WebShareFeedOuterClass.WebShareFeedOrBuilder getShareFeedsOrBuilder(int index) {
        if (this.shareFeedsBuilder_ == null)
          return this.shareFeeds_.get(index); 
        return (WebShareFeedOuterClass.WebShareFeedOrBuilder)this.shareFeedsBuilder_.getMessageOrBuilder(index);
      }
      
      public List<? extends WebShareFeedOuterClass.WebShareFeedOrBuilder> getShareFeedsOrBuilderList() {
        if (this.shareFeedsBuilder_ != null)
          return this.shareFeedsBuilder_.getMessageOrBuilderList(); 
        return Collections.unmodifiableList((List)this.shareFeeds_);
      }
      
      public WebShareFeedOuterClass.WebShareFeed.Builder addShareFeedsBuilder() {
        return (WebShareFeedOuterClass.WebShareFeed.Builder)getShareFeedsFieldBuilder().addBuilder(
            (AbstractMessage)WebShareFeedOuterClass.WebShareFeed.getDefaultInstance());
      }
      
      public WebShareFeedOuterClass.WebShareFeed.Builder addShareFeedsBuilder(int index) {
        return (WebShareFeedOuterClass.WebShareFeed.Builder)getShareFeedsFieldBuilder().addBuilder(index, 
            (AbstractMessage)WebShareFeedOuterClass.WebShareFeed.getDefaultInstance());
      }
      
      public List<WebShareFeedOuterClass.WebShareFeed.Builder> getShareFeedsBuilderList() {
        return getShareFeedsFieldBuilder().getBuilderList();
      }
      
      private RepeatedFieldBuilderV3<WebShareFeedOuterClass.WebShareFeed, WebShareFeedOuterClass.WebShareFeed.Builder, WebShareFeedOuterClass.WebShareFeedOrBuilder> getShareFeedsFieldBuilder() {
        if (this.shareFeedsBuilder_ == null) {
          this
            
            .shareFeedsBuilder_ = new RepeatedFieldBuilderV3(this.shareFeeds_, ((this.bitField0_ & 0x800) != 0), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
          this.shareFeeds_ = null;
        } 
        return this.shareFeedsBuilder_;
      }
      
      public final Builder setUnknownFields(UnknownFieldSet unknownFields) {
        return (Builder)super.setUnknownFields(unknownFields);
      }
      
      public final Builder mergeUnknownFields(UnknownFieldSet unknownFields) {
        return (Builder)super.mergeUnknownFields(unknownFields);
      }
    }
    
    private static final SCWebFeedPush DEFAULT_INSTANCE = new SCWebFeedPush();
    
    public static SCWebFeedPush getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }
    
    private static final Parser<SCWebFeedPush> PARSER = (Parser<SCWebFeedPush>)new AbstractParser<SCWebFeedPush>() {
        public SCWebFeedPushOuterClass.SCWebFeedPush parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
          SCWebFeedPushOuterClass.SCWebFeedPush.Builder builder = SCWebFeedPushOuterClass.SCWebFeedPush.newBuilder();
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
    
    public static Parser<SCWebFeedPush> parser() {
      return PARSER;
    }
    
    public Parser<SCWebFeedPush> getParserForType() {
      return PARSER;
    }
    
    public SCWebFeedPush getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }
  }
  
  public static Descriptors.FileDescriptor getDescriptor() {
    return descriptor;
  }
  
  static {
    String[] descriptorData = { "\n\023SCWebFeedPush.proto\032\024WebCommentFeed.proto\032\031WebComboCommentFeed.proto\032\021WebLikeFeed.proto\032\021WebGiftFeed.proto\032\031WebSystemNoticeFeed.proto\032\022WebShareFeed.proto\"\003\n\rSCWebFeedPush\022\034\n\024displayWatchingCount\030\001 \001(\t\022\030\n\020displayLikeCount\030\002 \001(\t\022\030\n\020pendingLikeCount\030\003 \001(\004\022\024\n\fpushInterval\030\004 \001(\004\022%\n\fcommentFeeds\030\005 \003(\0132\017.WebCommentFeed\022\025\n\rcommentCursor\030\006 \001(\t\022.\n\020comboCommentFeed\030\007 \003(\0132\024.WebComboCommentFeed\022\037\n\tlikeFeeds\030\b \003(\0132\f.WebLikeFeed\022\037\n\tgiftFeeds\030\t \003(\0132\f.WebGiftFeed\022\022\n\ngiftCursor\030\n \001(\t\022/\n\021systemNoticeFeeds\030\013 \003(\0132\024.WebSystemNoticeFeed\022!\n\nshareFeeds\030\f \003(\0132\r.WebShareFeedB<\n:tech.ordinaryroad.live.chat.client.codec.kuaishou.protobufb\006proto3" };
    descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[] { WebCommentFeedOuterClass.getDescriptor(), 
          WebComboCommentFeedOuterClass.getDescriptor(), 
          WebLikeFeedOuterClass.getDescriptor(), 
          WebGiftFeedOuterClass.getDescriptor(), 
          WebSystemNoticeFeedOuterClass.getDescriptor(), 
          WebShareFeedOuterClass.getDescriptor() });
    internal_static_SCWebFeedPush_descriptor = getDescriptor().getMessageTypes().get(0);
    internal_static_SCWebFeedPush_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_SCWebFeedPush_descriptor, new String[] { 
          "DisplayWatchingCount", "DisplayLikeCount", "PendingLikeCount", "PushInterval", "CommentFeeds", "CommentCursor", "ComboCommentFeed", "LikeFeeds", "GiftFeeds", "GiftCursor", 
          "SystemNoticeFeeds", "ShareFeeds" });
    WebCommentFeedOuterClass.getDescriptor();
    WebComboCommentFeedOuterClass.getDescriptor();
    WebLikeFeedOuterClass.getDescriptor();
    WebGiftFeedOuterClass.getDescriptor();
    WebSystemNoticeFeedOuterClass.getDescriptor();
    WebShareFeedOuterClass.getDescriptor();
  }
  
  public static interface SCWebFeedPushOrBuilder extends MessageOrBuilder {
    String getDisplayWatchingCount();
    
    ByteString getDisplayWatchingCountBytes();
    
    String getDisplayLikeCount();
    
    ByteString getDisplayLikeCountBytes();
    
    long getPendingLikeCount();
    
    long getPushInterval();
    
    List<WebCommentFeedOuterClass.WebCommentFeed> getCommentFeedsList();
    
    WebCommentFeedOuterClass.WebCommentFeed getCommentFeeds(int param1Int);
    
    int getCommentFeedsCount();
    
    List<? extends WebCommentFeedOuterClass.WebCommentFeedOrBuilder> getCommentFeedsOrBuilderList();
    
    WebCommentFeedOuterClass.WebCommentFeedOrBuilder getCommentFeedsOrBuilder(int param1Int);
    
    String getCommentCursor();
    
    ByteString getCommentCursorBytes();
    
    List<WebComboCommentFeedOuterClass.WebComboCommentFeed> getComboCommentFeedList();
    
    WebComboCommentFeedOuterClass.WebComboCommentFeed getComboCommentFeed(int param1Int);
    
    int getComboCommentFeedCount();
    
    List<? extends WebComboCommentFeedOuterClass.WebComboCommentFeedOrBuilder> getComboCommentFeedOrBuilderList();
    
    WebComboCommentFeedOuterClass.WebComboCommentFeedOrBuilder getComboCommentFeedOrBuilder(int param1Int);
    
    List<WebLikeFeedOuterClass.WebLikeFeed> getLikeFeedsList();
    
    WebLikeFeedOuterClass.WebLikeFeed getLikeFeeds(int param1Int);
    
    int getLikeFeedsCount();
    
    List<? extends WebLikeFeedOuterClass.WebLikeFeedOrBuilder> getLikeFeedsOrBuilderList();
    
    WebLikeFeedOuterClass.WebLikeFeedOrBuilder getLikeFeedsOrBuilder(int param1Int);
    
    List<WebGiftFeedOuterClass.WebGiftFeed> getGiftFeedsList();
    
    WebGiftFeedOuterClass.WebGiftFeed getGiftFeeds(int param1Int);
    
    int getGiftFeedsCount();
    
    List<? extends WebGiftFeedOuterClass.WebGiftFeedOrBuilder> getGiftFeedsOrBuilderList();
    
    WebGiftFeedOuterClass.WebGiftFeedOrBuilder getGiftFeedsOrBuilder(int param1Int);
    
    String getGiftCursor();
    
    ByteString getGiftCursorBytes();
    
    List<WebSystemNoticeFeedOuterClass.WebSystemNoticeFeed> getSystemNoticeFeedsList();
    
    WebSystemNoticeFeedOuterClass.WebSystemNoticeFeed getSystemNoticeFeeds(int param1Int);
    
    int getSystemNoticeFeedsCount();
    
    List<? extends WebSystemNoticeFeedOuterClass.WebSystemNoticeFeedOrBuilder> getSystemNoticeFeedsOrBuilderList();
    
    WebSystemNoticeFeedOuterClass.WebSystemNoticeFeedOrBuilder getSystemNoticeFeedsOrBuilder(int param1Int);
    
    List<WebShareFeedOuterClass.WebShareFeed> getShareFeedsList();
    
    WebShareFeedOuterClass.WebShareFeed getShareFeeds(int param1Int);
    
    int getShareFeedsCount();
    
    List<? extends WebShareFeedOuterClass.WebShareFeedOrBuilder> getShareFeedsOrBuilderList();
    
    WebShareFeedOuterClass.WebShareFeedOrBuilder getShareFeedsOrBuilder(int param1Int);
  }
}
