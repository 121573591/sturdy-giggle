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

public final class User extends GeneratedMessageV3 implements UserOrBuilder {
  private static final long serialVersionUID = 0L;
  
  private int bitField0_;
  
  public static final int ID_FIELD_NUMBER = 1;
  
  private long id_;
  
  public static final int SHORTID_FIELD_NUMBER = 2;
  
  private long shortId_;
  
  public static final int NICKNAME_FIELD_NUMBER = 3;
  
  private volatile Object nickName_;
  
  public static final int GENDER_FIELD_NUMBER = 4;
  
  private int gender_;
  
  public static final int SIGNATURE_FIELD_NUMBER = 5;
  
  private volatile Object signature_;
  
  public static final int LEVEL_FIELD_NUMBER = 6;
  
  private int level_;
  
  public static final int BIRTHDAY_FIELD_NUMBER = 7;
  
  private long birthday_;
  
  public static final int TELEPHONE_FIELD_NUMBER = 8;
  
  private volatile Object telephone_;
  
  public static final int AVATARTHUMB_FIELD_NUMBER = 9;
  
  private Image avatarThumb_;
  
  public static final int AVATARMEDIUM_FIELD_NUMBER = 10;
  
  private Image avatarMedium_;
  
  public static final int AVATARLARGE_FIELD_NUMBER = 11;
  
  private Image avatarLarge_;
  
  public static final int VERIFIED_FIELD_NUMBER = 12;
  
  private boolean verified_;
  
  public static final int EXPERIENCE_FIELD_NUMBER = 13;
  
  private int experience_;
  
  public static final int CITY_FIELD_NUMBER = 14;
  
  private volatile Object city_;
  
  public static final int STATUS_FIELD_NUMBER = 15;
  
  private int status_;
  
  public static final int CREATETIME_FIELD_NUMBER = 16;
  
  private long createTime_;
  
  public static final int MODIFYTIME_FIELD_NUMBER = 17;
  
  private long modifyTime_;
  
  public static final int SECRET_FIELD_NUMBER = 18;
  
  private int secret_;
  
  public static final int SHAREQRCODEURI_FIELD_NUMBER = 19;
  
  private volatile Object shareQrcodeUri_;
  
  public static final int INCOMESHAREPERCENT_FIELD_NUMBER = 20;
  
  private int incomeSharePercent_;
  
  public static final int BADGEIMAGELIST_FIELD_NUMBER = 21;
  
  private List<Image> badgeImageList_;
  
  public static final int FOLLOWINFO_FIELD_NUMBER = 22;
  
  private FollowInfo followInfo_;
  
  public static final int PAYGRADE_FIELD_NUMBER = 23;
  
  private PayGrade payGrade_;
  
  public static final int FANSCLUB_FIELD_NUMBER = 24;
  
  private FansClub fansClub_;
  
  public static final int SPECIALID_FIELD_NUMBER = 26;
  
  private volatile Object specialId_;
  
  public static final int AVATARBORDER_FIELD_NUMBER = 27;
  
  private Image avatarBorder_;
  
  public static final int MEDAL_FIELD_NUMBER = 28;
  
  private Image medal_;
  
  public static final int REALTIMEICONSLIST_FIELD_NUMBER = 29;
  
  private List<Image> realTimeIconsList_;
  
  public static final int DISPLAYID_FIELD_NUMBER = 38;
  
  private volatile Object displayId_;
  
  public static final int SECUID_FIELD_NUMBER = 46;
  
  private volatile Object secUid_;
  
  public static final int FANTICKETCOUNT_FIELD_NUMBER = 1022;
  
  private long fanTicketCount_;
  
  public static final int IDSTR_FIELD_NUMBER = 1028;
  
  private volatile Object idStr_;
  
  public static final int AGERANGE_FIELD_NUMBER = 1045;
  
  private int ageRange_;
  
  private byte memoizedIsInitialized;
  
  private User(GeneratedMessageV3.Builder<?> builder) {
    super(builder);
    this.id_ = 0L;
    this.shortId_ = 0L;
    this.nickName_ = "";
    this.gender_ = 0;
    this.signature_ = "";
    this.level_ = 0;
    this.birthday_ = 0L;
    this.telephone_ = "";
    this.verified_ = false;
    this.experience_ = 0;
    this.city_ = "";
    this.status_ = 0;
    this.createTime_ = 0L;
    this.modifyTime_ = 0L;
    this.secret_ = 0;
    this.shareQrcodeUri_ = "";
    this.incomeSharePercent_ = 0;
    this.specialId_ = "";
    this.displayId_ = "";
    this.secUid_ = "";
    this.fanTicketCount_ = 0L;
    this.idStr_ = "";
    this.ageRange_ = 0;
    this.memoizedIsInitialized = -1;
  }
  
  private User() {
    this.id_ = 0L;
    this.shortId_ = 0L;
    this.nickName_ = "";
    this.gender_ = 0;
    this.signature_ = "";
    this.level_ = 0;
    this.birthday_ = 0L;
    this.telephone_ = "";
    this.verified_ = false;
    this.experience_ = 0;
    this.city_ = "";
    this.status_ = 0;
    this.createTime_ = 0L;
    this.modifyTime_ = 0L;
    this.secret_ = 0;
    this.shareQrcodeUri_ = "";
    this.incomeSharePercent_ = 0;
    this.specialId_ = "";
    this.displayId_ = "";
    this.secUid_ = "";
    this.fanTicketCount_ = 0L;
    this.idStr_ = "";
    this.ageRange_ = 0;
    this.memoizedIsInitialized = -1;
    this.nickName_ = "";
    this.signature_ = "";
    this.telephone_ = "";
    this.city_ = "";
    this.shareQrcodeUri_ = "";
    this.badgeImageList_ = Collections.emptyList();
    this.specialId_ = "";
    this.realTimeIconsList_ = Collections.emptyList();
    this.displayId_ = "";
    this.secUid_ = "";
    this.idStr_ = "";
  }
  
  protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
    return new User();
  }
  
  public static final Descriptors.Descriptor getDescriptor() {
    return Douyin.internal_static_User_descriptor;
  }
  
  protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
    return Douyin.internal_static_User_fieldAccessorTable.ensureFieldAccessorsInitialized(User.class, Builder.class);
  }
  
  public long getId() {
    return this.id_;
  }
  
  public long getShortId() {
    return this.shortId_;
  }
  
  public String getNickName() {
    Object ref = this.nickName_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.nickName_ = s;
    return s;
  }
  
  public ByteString getNickNameBytes() {
    Object ref = this.nickName_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.nickName_ = b;
      return b;
    } 
    return (ByteString)ref;
  }
  
  public int getGender() {
    return this.gender_;
  }
  
  public String getSignature() {
    Object ref = this.signature_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.signature_ = s;
    return s;
  }
  
  public ByteString getSignatureBytes() {
    Object ref = this.signature_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.signature_ = b;
      return b;
    } 
    return (ByteString)ref;
  }
  
  public int getLevel() {
    return this.level_;
  }
  
  public long getBirthday() {
    return this.birthday_;
  }
  
  public String getTelephone() {
    Object ref = this.telephone_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.telephone_ = s;
    return s;
  }
  
  public ByteString getTelephoneBytes() {
    Object ref = this.telephone_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.telephone_ = b;
      return b;
    } 
    return (ByteString)ref;
  }
  
  public boolean hasAvatarThumb() {
    return ((this.bitField0_ & 0x1) != 0);
  }
  
  public Image getAvatarThumb() {
    return (this.avatarThumb_ == null) ? Image.getDefaultInstance() : this.avatarThumb_;
  }
  
  public ImageOrBuilder getAvatarThumbOrBuilder() {
    return (this.avatarThumb_ == null) ? Image.getDefaultInstance() : this.avatarThumb_;
  }
  
  public boolean hasAvatarMedium() {
    return ((this.bitField0_ & 0x2) != 0);
  }
  
  public Image getAvatarMedium() {
    return (this.avatarMedium_ == null) ? Image.getDefaultInstance() : this.avatarMedium_;
  }
  
  public ImageOrBuilder getAvatarMediumOrBuilder() {
    return (this.avatarMedium_ == null) ? Image.getDefaultInstance() : this.avatarMedium_;
  }
  
  public boolean hasAvatarLarge() {
    return ((this.bitField0_ & 0x4) != 0);
  }
  
  public Image getAvatarLarge() {
    return (this.avatarLarge_ == null) ? Image.getDefaultInstance() : this.avatarLarge_;
  }
  
  public ImageOrBuilder getAvatarLargeOrBuilder() {
    return (this.avatarLarge_ == null) ? Image.getDefaultInstance() : this.avatarLarge_;
  }
  
  public boolean getVerified() {
    return this.verified_;
  }
  
  public int getExperience() {
    return this.experience_;
  }
  
  public String getCity() {
    Object ref = this.city_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.city_ = s;
    return s;
  }
  
  public ByteString getCityBytes() {
    Object ref = this.city_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.city_ = b;
      return b;
    } 
    return (ByteString)ref;
  }
  
  public int getStatus() {
    return this.status_;
  }
  
  public long getCreateTime() {
    return this.createTime_;
  }
  
  public long getModifyTime() {
    return this.modifyTime_;
  }
  
  public int getSecret() {
    return this.secret_;
  }
  
  public String getShareQrcodeUri() {
    Object ref = this.shareQrcodeUri_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.shareQrcodeUri_ = s;
    return s;
  }
  
  public ByteString getShareQrcodeUriBytes() {
    Object ref = this.shareQrcodeUri_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.shareQrcodeUri_ = b;
      return b;
    } 
    return (ByteString)ref;
  }
  
  public int getIncomeSharePercent() {
    return this.incomeSharePercent_;
  }
  
  public List<Image> getBadgeImageListList() {
    return this.badgeImageList_;
  }
  
  public List<? extends ImageOrBuilder> getBadgeImageListOrBuilderList() {
    return (List)this.badgeImageList_;
  }
  
  public int getBadgeImageListCount() {
    return this.badgeImageList_.size();
  }
  
  public Image getBadgeImageList(int index) {
    return this.badgeImageList_.get(index);
  }
  
  public ImageOrBuilder getBadgeImageListOrBuilder(int index) {
    return this.badgeImageList_.get(index);
  }
  
  public boolean hasFollowInfo() {
    return ((this.bitField0_ & 0x8) != 0);
  }
  
  public FollowInfo getFollowInfo() {
    return (this.followInfo_ == null) ? FollowInfo.getDefaultInstance() : this.followInfo_;
  }
  
  public FollowInfoOrBuilder getFollowInfoOrBuilder() {
    return (this.followInfo_ == null) ? FollowInfo.getDefaultInstance() : this.followInfo_;
  }
  
  public boolean hasPayGrade() {
    return ((this.bitField0_ & 0x10) != 0);
  }
  
  public PayGrade getPayGrade() {
    return (this.payGrade_ == null) ? PayGrade.getDefaultInstance() : this.payGrade_;
  }
  
  public PayGradeOrBuilder getPayGradeOrBuilder() {
    return (this.payGrade_ == null) ? PayGrade.getDefaultInstance() : this.payGrade_;
  }
  
  public boolean hasFansClub() {
    return ((this.bitField0_ & 0x20) != 0);
  }
  
  public FansClub getFansClub() {
    return (this.fansClub_ == null) ? FansClub.getDefaultInstance() : this.fansClub_;
  }
  
  public FansClubOrBuilder getFansClubOrBuilder() {
    return (this.fansClub_ == null) ? FansClub.getDefaultInstance() : this.fansClub_;
  }
  
  public String getSpecialId() {
    Object ref = this.specialId_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.specialId_ = s;
    return s;
  }
  
  public ByteString getSpecialIdBytes() {
    Object ref = this.specialId_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.specialId_ = b;
      return b;
    } 
    return (ByteString)ref;
  }
  
  public boolean hasAvatarBorder() {
    return ((this.bitField0_ & 0x40) != 0);
  }
  
  public Image getAvatarBorder() {
    return (this.avatarBorder_ == null) ? Image.getDefaultInstance() : this.avatarBorder_;
  }
  
  public ImageOrBuilder getAvatarBorderOrBuilder() {
    return (this.avatarBorder_ == null) ? Image.getDefaultInstance() : this.avatarBorder_;
  }
  
  public boolean hasMedal() {
    return ((this.bitField0_ & 0x80) != 0);
  }
  
  public Image getMedal() {
    return (this.medal_ == null) ? Image.getDefaultInstance() : this.medal_;
  }
  
  public ImageOrBuilder getMedalOrBuilder() {
    return (this.medal_ == null) ? Image.getDefaultInstance() : this.medal_;
  }
  
  public List<Image> getRealTimeIconsListList() {
    return this.realTimeIconsList_;
  }
  
  public List<? extends ImageOrBuilder> getRealTimeIconsListOrBuilderList() {
    return (List)this.realTimeIconsList_;
  }
  
  public int getRealTimeIconsListCount() {
    return this.realTimeIconsList_.size();
  }
  
  public Image getRealTimeIconsList(int index) {
    return this.realTimeIconsList_.get(index);
  }
  
  public ImageOrBuilder getRealTimeIconsListOrBuilder(int index) {
    return this.realTimeIconsList_.get(index);
  }
  
  public String getDisplayId() {
    Object ref = this.displayId_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.displayId_ = s;
    return s;
  }
  
  public ByteString getDisplayIdBytes() {
    Object ref = this.displayId_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.displayId_ = b;
      return b;
    } 
    return (ByteString)ref;
  }
  
  public String getSecUid() {
    Object ref = this.secUid_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.secUid_ = s;
    return s;
  }
  
  public ByteString getSecUidBytes() {
    Object ref = this.secUid_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.secUid_ = b;
      return b;
    } 
    return (ByteString)ref;
  }
  
  public long getFanTicketCount() {
    return this.fanTicketCount_;
  }
  
  public String getIdStr() {
    Object ref = this.idStr_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.idStr_ = s;
    return s;
  }
  
  public ByteString getIdStrBytes() {
    Object ref = this.idStr_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.idStr_ = b;
      return b;
    } 
    return (ByteString)ref;
  }
  
  public int getAgeRange() {
    return this.ageRange_;
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
    if (this.id_ != 0L)
      output.writeUInt64(1, this.id_); 
    if (this.shortId_ != 0L)
      output.writeUInt64(2, this.shortId_); 
    if (!GeneratedMessageV3.isStringEmpty(this.nickName_))
      GeneratedMessageV3.writeString(output, 3, this.nickName_); 
    if (this.gender_ != 0)
      output.writeUInt32(4, this.gender_); 
    if (!GeneratedMessageV3.isStringEmpty(this.signature_))
      GeneratedMessageV3.writeString(output, 5, this.signature_); 
    if (this.level_ != 0)
      output.writeUInt32(6, this.level_); 
    if (this.birthday_ != 0L)
      output.writeUInt64(7, this.birthday_); 
    if (!GeneratedMessageV3.isStringEmpty(this.telephone_))
      GeneratedMessageV3.writeString(output, 8, this.telephone_); 
    if ((this.bitField0_ & 0x1) != 0)
      output.writeMessage(9, (MessageLite)getAvatarThumb()); 
    if ((this.bitField0_ & 0x2) != 0)
      output.writeMessage(10, (MessageLite)getAvatarMedium()); 
    if ((this.bitField0_ & 0x4) != 0)
      output.writeMessage(11, (MessageLite)getAvatarLarge()); 
    if (this.verified_)
      output.writeBool(12, this.verified_); 
    if (this.experience_ != 0)
      output.writeUInt32(13, this.experience_); 
    if (!GeneratedMessageV3.isStringEmpty(this.city_))
      GeneratedMessageV3.writeString(output, 14, this.city_); 
    if (this.status_ != 0)
      output.writeInt32(15, this.status_); 
    if (this.createTime_ != 0L)
      output.writeUInt64(16, this.createTime_); 
    if (this.modifyTime_ != 0L)
      output.writeUInt64(17, this.modifyTime_); 
    if (this.secret_ != 0)
      output.writeUInt32(18, this.secret_); 
    if (!GeneratedMessageV3.isStringEmpty(this.shareQrcodeUri_))
      GeneratedMessageV3.writeString(output, 19, this.shareQrcodeUri_); 
    if (this.incomeSharePercent_ != 0)
      output.writeUInt32(20, this.incomeSharePercent_); 
    int i;
    for (i = 0; i < this.badgeImageList_.size(); i++)
      output.writeMessage(21, (MessageLite)this.badgeImageList_.get(i)); 
    if ((this.bitField0_ & 0x8) != 0)
      output.writeMessage(22, (MessageLite)getFollowInfo()); 
    if ((this.bitField0_ & 0x10) != 0)
      output.writeMessage(23, (MessageLite)getPayGrade()); 
    if ((this.bitField0_ & 0x20) != 0)
      output.writeMessage(24, (MessageLite)getFansClub()); 
    if (!GeneratedMessageV3.isStringEmpty(this.specialId_))
      GeneratedMessageV3.writeString(output, 26, this.specialId_); 
    if ((this.bitField0_ & 0x40) != 0)
      output.writeMessage(27, (MessageLite)getAvatarBorder()); 
    if ((this.bitField0_ & 0x80) != 0)
      output.writeMessage(28, (MessageLite)getMedal()); 
    for (i = 0; i < this.realTimeIconsList_.size(); i++)
      output.writeMessage(29, (MessageLite)this.realTimeIconsList_.get(i)); 
    if (!GeneratedMessageV3.isStringEmpty(this.displayId_))
      GeneratedMessageV3.writeString(output, 38, this.displayId_); 
    if (!GeneratedMessageV3.isStringEmpty(this.secUid_))
      GeneratedMessageV3.writeString(output, 46, this.secUid_); 
    if (this.fanTicketCount_ != 0L)
      output.writeUInt64(1022, this.fanTicketCount_); 
    if (!GeneratedMessageV3.isStringEmpty(this.idStr_))
      GeneratedMessageV3.writeString(output, 1028, this.idStr_); 
    if (this.ageRange_ != 0)
      output.writeUInt32(1045, this.ageRange_); 
    getUnknownFields().writeTo(output);
  }
  
  public int getSerializedSize() {
    int size = this.memoizedSize;
    if (size != -1)
      return size; 
    size = 0;
    if (this.id_ != 0L)
      size += 
        CodedOutputStream.computeUInt64Size(1, this.id_); 
    if (this.shortId_ != 0L)
      size += 
        CodedOutputStream.computeUInt64Size(2, this.shortId_); 
    if (!GeneratedMessageV3.isStringEmpty(this.nickName_))
      size += GeneratedMessageV3.computeStringSize(3, this.nickName_); 
    if (this.gender_ != 0)
      size += 
        CodedOutputStream.computeUInt32Size(4, this.gender_); 
    if (!GeneratedMessageV3.isStringEmpty(this.signature_))
      size += GeneratedMessageV3.computeStringSize(5, this.signature_); 
    if (this.level_ != 0)
      size += 
        CodedOutputStream.computeUInt32Size(6, this.level_); 
    if (this.birthday_ != 0L)
      size += 
        CodedOutputStream.computeUInt64Size(7, this.birthday_); 
    if (!GeneratedMessageV3.isStringEmpty(this.telephone_))
      size += GeneratedMessageV3.computeStringSize(8, this.telephone_); 
    if ((this.bitField0_ & 0x1) != 0)
      size += 
        CodedOutputStream.computeMessageSize(9, (MessageLite)getAvatarThumb()); 
    if ((this.bitField0_ & 0x2) != 0)
      size += 
        CodedOutputStream.computeMessageSize(10, (MessageLite)getAvatarMedium()); 
    if ((this.bitField0_ & 0x4) != 0)
      size += 
        CodedOutputStream.computeMessageSize(11, (MessageLite)getAvatarLarge()); 
    if (this.verified_)
      size += 
        CodedOutputStream.computeBoolSize(12, this.verified_); 
    if (this.experience_ != 0)
      size += 
        CodedOutputStream.computeUInt32Size(13, this.experience_); 
    if (!GeneratedMessageV3.isStringEmpty(this.city_))
      size += GeneratedMessageV3.computeStringSize(14, this.city_); 
    if (this.status_ != 0)
      size += 
        CodedOutputStream.computeInt32Size(15, this.status_); 
    if (this.createTime_ != 0L)
      size += 
        CodedOutputStream.computeUInt64Size(16, this.createTime_); 
    if (this.modifyTime_ != 0L)
      size += 
        CodedOutputStream.computeUInt64Size(17, this.modifyTime_); 
    if (this.secret_ != 0)
      size += 
        CodedOutputStream.computeUInt32Size(18, this.secret_); 
    if (!GeneratedMessageV3.isStringEmpty(this.shareQrcodeUri_))
      size += GeneratedMessageV3.computeStringSize(19, this.shareQrcodeUri_); 
    if (this.incomeSharePercent_ != 0)
      size += 
        CodedOutputStream.computeUInt32Size(20, this.incomeSharePercent_); 
    int i;
    for (i = 0; i < this.badgeImageList_.size(); i++)
      size += 
        CodedOutputStream.computeMessageSize(21, (MessageLite)this.badgeImageList_.get(i)); 
    if ((this.bitField0_ & 0x8) != 0)
      size += 
        CodedOutputStream.computeMessageSize(22, (MessageLite)getFollowInfo()); 
    if ((this.bitField0_ & 0x10) != 0)
      size += 
        CodedOutputStream.computeMessageSize(23, (MessageLite)getPayGrade()); 
    if ((this.bitField0_ & 0x20) != 0)
      size += 
        CodedOutputStream.computeMessageSize(24, (MessageLite)getFansClub()); 
    if (!GeneratedMessageV3.isStringEmpty(this.specialId_))
      size += GeneratedMessageV3.computeStringSize(26, this.specialId_); 
    if ((this.bitField0_ & 0x40) != 0)
      size += 
        CodedOutputStream.computeMessageSize(27, (MessageLite)getAvatarBorder()); 
    if ((this.bitField0_ & 0x80) != 0)
      size += 
        CodedOutputStream.computeMessageSize(28, (MessageLite)getMedal()); 
    for (i = 0; i < this.realTimeIconsList_.size(); i++)
      size += 
        CodedOutputStream.computeMessageSize(29, (MessageLite)this.realTimeIconsList_.get(i)); 
    if (!GeneratedMessageV3.isStringEmpty(this.displayId_))
      size += GeneratedMessageV3.computeStringSize(38, this.displayId_); 
    if (!GeneratedMessageV3.isStringEmpty(this.secUid_))
      size += GeneratedMessageV3.computeStringSize(46, this.secUid_); 
    if (this.fanTicketCount_ != 0L)
      size += 
        CodedOutputStream.computeUInt64Size(1022, this.fanTicketCount_); 
    if (!GeneratedMessageV3.isStringEmpty(this.idStr_))
      size += GeneratedMessageV3.computeStringSize(1028, this.idStr_); 
    if (this.ageRange_ != 0)
      size += 
        CodedOutputStream.computeUInt32Size(1045, this.ageRange_); 
    size += getUnknownFields().getSerializedSize();
    this.memoizedSize = size;
    return size;
  }
  
  public boolean equals(Object obj) {
    if (obj == this)
      return true; 
    if (!(obj instanceof User))
      return super.equals(obj); 
    User other = (User)obj;
    if (getId() != other
      .getId())
      return false; 
    if (getShortId() != other
      .getShortId())
      return false; 
    if (!getNickName().equals(other.getNickName()))
      return false; 
    if (getGender() != other
      .getGender())
      return false; 
    if (!getSignature().equals(other.getSignature()))
      return false; 
    if (getLevel() != other
      .getLevel())
      return false; 
    if (getBirthday() != other
      .getBirthday())
      return false; 
    if (!getTelephone().equals(other.getTelephone()))
      return false; 
    if (hasAvatarThumb() != other.hasAvatarThumb())
      return false; 
    if (hasAvatarThumb() && 
      
      !getAvatarThumb().equals(other.getAvatarThumb()))
      return false; 
    if (hasAvatarMedium() != other.hasAvatarMedium())
      return false; 
    if (hasAvatarMedium() && 
      
      !getAvatarMedium().equals(other.getAvatarMedium()))
      return false; 
    if (hasAvatarLarge() != other.hasAvatarLarge())
      return false; 
    if (hasAvatarLarge() && 
      
      !getAvatarLarge().equals(other.getAvatarLarge()))
      return false; 
    if (getVerified() != other
      .getVerified())
      return false; 
    if (getExperience() != other
      .getExperience())
      return false; 
    if (!getCity().equals(other.getCity()))
      return false; 
    if (getStatus() != other
      .getStatus())
      return false; 
    if (getCreateTime() != other
      .getCreateTime())
      return false; 
    if (getModifyTime() != other
      .getModifyTime())
      return false; 
    if (getSecret() != other
      .getSecret())
      return false; 
    if (!getShareQrcodeUri().equals(other.getShareQrcodeUri()))
      return false; 
    if (getIncomeSharePercent() != other
      .getIncomeSharePercent())
      return false; 
    if (!getBadgeImageListList().equals(other.getBadgeImageListList()))
      return false; 
    if (hasFollowInfo() != other.hasFollowInfo())
      return false; 
    if (hasFollowInfo() && 
      
      !getFollowInfo().equals(other.getFollowInfo()))
      return false; 
    if (hasPayGrade() != other.hasPayGrade())
      return false; 
    if (hasPayGrade() && 
      
      !getPayGrade().equals(other.getPayGrade()))
      return false; 
    if (hasFansClub() != other.hasFansClub())
      return false; 
    if (hasFansClub() && 
      
      !getFansClub().equals(other.getFansClub()))
      return false; 
    if (!getSpecialId().equals(other.getSpecialId()))
      return false; 
    if (hasAvatarBorder() != other.hasAvatarBorder())
      return false; 
    if (hasAvatarBorder() && 
      
      !getAvatarBorder().equals(other.getAvatarBorder()))
      return false; 
    if (hasMedal() != other.hasMedal())
      return false; 
    if (hasMedal() && 
      
      !getMedal().equals(other.getMedal()))
      return false; 
    if (!getRealTimeIconsListList().equals(other.getRealTimeIconsListList()))
      return false; 
    if (!getDisplayId().equals(other.getDisplayId()))
      return false; 
    if (!getSecUid().equals(other.getSecUid()))
      return false; 
    if (getFanTicketCount() != other
      .getFanTicketCount())
      return false; 
    if (!getIdStr().equals(other.getIdStr()))
      return false; 
    if (getAgeRange() != other
      .getAgeRange())
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
    hash = 53 * hash + Internal.hashLong(
        getId());
    hash = 37 * hash + 2;
    hash = 53 * hash + Internal.hashLong(
        getShortId());
    hash = 37 * hash + 3;
    hash = 53 * hash + getNickName().hashCode();
    hash = 37 * hash + 4;
    hash = 53 * hash + getGender();
    hash = 37 * hash + 5;
    hash = 53 * hash + getSignature().hashCode();
    hash = 37 * hash + 6;
    hash = 53 * hash + getLevel();
    hash = 37 * hash + 7;
    hash = 53 * hash + Internal.hashLong(
        getBirthday());
    hash = 37 * hash + 8;
    hash = 53 * hash + getTelephone().hashCode();
    if (hasAvatarThumb()) {
      hash = 37 * hash + 9;
      hash = 53 * hash + getAvatarThumb().hashCode();
    } 
    if (hasAvatarMedium()) {
      hash = 37 * hash + 10;
      hash = 53 * hash + getAvatarMedium().hashCode();
    } 
    if (hasAvatarLarge()) {
      hash = 37 * hash + 11;
      hash = 53 * hash + getAvatarLarge().hashCode();
    } 
    hash = 37 * hash + 12;
    hash = 53 * hash + Internal.hashBoolean(
        getVerified());
    hash = 37 * hash + 13;
    hash = 53 * hash + getExperience();
    hash = 37 * hash + 14;
    hash = 53 * hash + getCity().hashCode();
    hash = 37 * hash + 15;
    hash = 53 * hash + getStatus();
    hash = 37 * hash + 16;
    hash = 53 * hash + Internal.hashLong(
        getCreateTime());
    hash = 37 * hash + 17;
    hash = 53 * hash + Internal.hashLong(
        getModifyTime());
    hash = 37 * hash + 18;
    hash = 53 * hash + getSecret();
    hash = 37 * hash + 19;
    hash = 53 * hash + getShareQrcodeUri().hashCode();
    hash = 37 * hash + 20;
    hash = 53 * hash + getIncomeSharePercent();
    if (getBadgeImageListCount() > 0) {
      hash = 37 * hash + 21;
      hash = 53 * hash + getBadgeImageListList().hashCode();
    } 
    if (hasFollowInfo()) {
      hash = 37 * hash + 22;
      hash = 53 * hash + getFollowInfo().hashCode();
    } 
    if (hasPayGrade()) {
      hash = 37 * hash + 23;
      hash = 53 * hash + getPayGrade().hashCode();
    } 
    if (hasFansClub()) {
      hash = 37 * hash + 24;
      hash = 53 * hash + getFansClub().hashCode();
    } 
    hash = 37 * hash + 26;
    hash = 53 * hash + getSpecialId().hashCode();
    if (hasAvatarBorder()) {
      hash = 37 * hash + 27;
      hash = 53 * hash + getAvatarBorder().hashCode();
    } 
    if (hasMedal()) {
      hash = 37 * hash + 28;
      hash = 53 * hash + getMedal().hashCode();
    } 
    if (getRealTimeIconsListCount() > 0) {
      hash = 37 * hash + 29;
      hash = 53 * hash + getRealTimeIconsListList().hashCode();
    } 
    hash = 37 * hash + 38;
    hash = 53 * hash + getDisplayId().hashCode();
    hash = 37 * hash + 46;
    hash = 53 * hash + getSecUid().hashCode();
    hash = 37 * hash + 1022;
    hash = 53 * hash + Internal.hashLong(
        getFanTicketCount());
    hash = 37 * hash + 1028;
    hash = 53 * hash + getIdStr().hashCode();
    hash = 37 * hash + 1045;
    hash = 53 * hash + getAgeRange();
    hash = 29 * hash + getUnknownFields().hashCode();
    this.memoizedHashCode = hash;
    return hash;
  }
  
  public static User parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
    return (User)PARSER.parseFrom(data);
  }
  
  public static User parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (User)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static User parseFrom(ByteString data) throws InvalidProtocolBufferException {
    return (User)PARSER.parseFrom(data);
  }
  
  public static User parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (User)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static User parseFrom(byte[] data) throws InvalidProtocolBufferException {
    return (User)PARSER.parseFrom(data);
  }
  
  public static User parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (User)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static User parseFrom(InputStream input) throws IOException {
    return 
      (User)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static User parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (User)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static User parseDelimitedFrom(InputStream input) throws IOException {
    return 
      (User)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
  }
  
  public static User parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (User)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static User parseFrom(CodedInputStream input) throws IOException {
    return 
      (User)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static User parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (User)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public Builder newBuilderForType() {
    return newBuilder();
  }
  
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  
  public static Builder newBuilder(User prototype) {
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
  
  public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements UserOrBuilder {
    private int bitField0_;
    
    private int bitField1_;
    
    private long id_;
    
    private long shortId_;
    
    private Object nickName_;
    
    private int gender_;
    
    private Object signature_;
    
    private int level_;
    
    private long birthday_;
    
    private Object telephone_;
    
    private Image avatarThumb_;
    
    private SingleFieldBuilderV3<Image, Image.Builder, ImageOrBuilder> avatarThumbBuilder_;
    
    private Image avatarMedium_;
    
    private SingleFieldBuilderV3<Image, Image.Builder, ImageOrBuilder> avatarMediumBuilder_;
    
    private Image avatarLarge_;
    
    private SingleFieldBuilderV3<Image, Image.Builder, ImageOrBuilder> avatarLargeBuilder_;
    
    private boolean verified_;
    
    private int experience_;
    
    private Object city_;
    
    private int status_;
    
    private long createTime_;
    
    private long modifyTime_;
    
    private int secret_;
    
    private Object shareQrcodeUri_;
    
    private int incomeSharePercent_;
    
    private List<Image> badgeImageList_;
    
    private RepeatedFieldBuilderV3<Image, Image.Builder, ImageOrBuilder> badgeImageListBuilder_;
    
    private FollowInfo followInfo_;
    
    private SingleFieldBuilderV3<FollowInfo, FollowInfo.Builder, FollowInfoOrBuilder> followInfoBuilder_;
    
    private PayGrade payGrade_;
    
    private SingleFieldBuilderV3<PayGrade, PayGrade.Builder, PayGradeOrBuilder> payGradeBuilder_;
    
    private FansClub fansClub_;
    
    private SingleFieldBuilderV3<FansClub, FansClub.Builder, FansClubOrBuilder> fansClubBuilder_;
    
    private Object specialId_;
    
    private Image avatarBorder_;
    
    private SingleFieldBuilderV3<Image, Image.Builder, ImageOrBuilder> avatarBorderBuilder_;
    
    private Image medal_;
    
    private SingleFieldBuilderV3<Image, Image.Builder, ImageOrBuilder> medalBuilder_;
    
    private List<Image> realTimeIconsList_;
    
    private RepeatedFieldBuilderV3<Image, Image.Builder, ImageOrBuilder> realTimeIconsListBuilder_;
    
    private Object displayId_;
    
    private Object secUid_;
    
    private long fanTicketCount_;
    
    private Object idStr_;
    
    private int ageRange_;
    
    public static final Descriptors.Descriptor getDescriptor() {
      return Douyin.internal_static_User_descriptor;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return Douyin.internal_static_User_fieldAccessorTable
        .ensureFieldAccessorsInitialized(User.class, Builder.class);
    }
    
    private Builder() {
      this.nickName_ = "";
      this.signature_ = "";
      this.telephone_ = "";
      this.city_ = "";
      this.shareQrcodeUri_ = "";
      this
        .badgeImageList_ = Collections.emptyList();
      this.specialId_ = "";
      this
        .realTimeIconsList_ = Collections.emptyList();
      this.displayId_ = "";
      this.secUid_ = "";
      this.idStr_ = "";
      maybeForceBuilderInitialization();
    }
    
    private Builder(GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      this.nickName_ = "";
      this.signature_ = "";
      this.telephone_ = "";
      this.city_ = "";
      this.shareQrcodeUri_ = "";
      this.badgeImageList_ = Collections.emptyList();
      this.specialId_ = "";
      this.realTimeIconsList_ = Collections.emptyList();
      this.displayId_ = "";
      this.secUid_ = "";
      this.idStr_ = "";
      maybeForceBuilderInitialization();
    }
    
    private void maybeForceBuilderInitialization() {
      if (User.alwaysUseFieldBuilders) {
        getAvatarThumbFieldBuilder();
        getAvatarMediumFieldBuilder();
        getAvatarLargeFieldBuilder();
        getBadgeImageListFieldBuilder();
        getFollowInfoFieldBuilder();
        getPayGradeFieldBuilder();
        getFansClubFieldBuilder();
        getAvatarBorderFieldBuilder();
        getMedalFieldBuilder();
        getRealTimeIconsListFieldBuilder();
      } 
    }
    
    public Builder clear() {
      super.clear();
      this.bitField0_ = 0;
      this.bitField1_ = 0;
      this.id_ = 0L;
      this.shortId_ = 0L;
      this.nickName_ = "";
      this.gender_ = 0;
      this.signature_ = "";
      this.level_ = 0;
      this.birthday_ = 0L;
      this.telephone_ = "";
      this.avatarThumb_ = null;
      if (this.avatarThumbBuilder_ != null) {
        this.avatarThumbBuilder_.dispose();
        this.avatarThumbBuilder_ = null;
      } 
      this.avatarMedium_ = null;
      if (this.avatarMediumBuilder_ != null) {
        this.avatarMediumBuilder_.dispose();
        this.avatarMediumBuilder_ = null;
      } 
      this.avatarLarge_ = null;
      if (this.avatarLargeBuilder_ != null) {
        this.avatarLargeBuilder_.dispose();
        this.avatarLargeBuilder_ = null;
      } 
      this.verified_ = false;
      this.experience_ = 0;
      this.city_ = "";
      this.status_ = 0;
      this.createTime_ = 0L;
      this.modifyTime_ = 0L;
      this.secret_ = 0;
      this.shareQrcodeUri_ = "";
      this.incomeSharePercent_ = 0;
      if (this.badgeImageListBuilder_ == null) {
        this.badgeImageList_ = Collections.emptyList();
      } else {
        this.badgeImageList_ = null;
        this.badgeImageListBuilder_.clear();
      } 
      this.bitField0_ &= 0xFFEFFFFF;
      this.followInfo_ = null;
      if (this.followInfoBuilder_ != null) {
        this.followInfoBuilder_.dispose();
        this.followInfoBuilder_ = null;
      } 
      this.payGrade_ = null;
      if (this.payGradeBuilder_ != null) {
        this.payGradeBuilder_.dispose();
        this.payGradeBuilder_ = null;
      } 
      this.fansClub_ = null;
      if (this.fansClubBuilder_ != null) {
        this.fansClubBuilder_.dispose();
        this.fansClubBuilder_ = null;
      } 
      this.specialId_ = "";
      this.avatarBorder_ = null;
      if (this.avatarBorderBuilder_ != null) {
        this.avatarBorderBuilder_.dispose();
        this.avatarBorderBuilder_ = null;
      } 
      this.medal_ = null;
      if (this.medalBuilder_ != null) {
        this.medalBuilder_.dispose();
        this.medalBuilder_ = null;
      } 
      if (this.realTimeIconsListBuilder_ == null) {
        this.realTimeIconsList_ = Collections.emptyList();
      } else {
        this.realTimeIconsList_ = null;
        this.realTimeIconsListBuilder_.clear();
      } 
      this.bitField0_ &= 0xF7FFFFFF;
      this.displayId_ = "";
      this.secUid_ = "";
      this.fanTicketCount_ = 0L;
      this.idStr_ = "";
      this.ageRange_ = 0;
      return this;
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
      return Douyin.internal_static_User_descriptor;
    }
    
    public User getDefaultInstanceForType() {
      return User.getDefaultInstance();
    }
    
    public User build() {
      User result = buildPartial();
      if (!result.isInitialized())
        throw newUninitializedMessageException(result); 
      return result;
    }
    
    public User buildPartial() {
      User result = new User(this);
      buildPartialRepeatedFields(result);
      if (this.bitField0_ != 0)
        buildPartial0(result); 
      if (this.bitField1_ != 0)
        buildPartial1(result); 
      onBuilt();
      return result;
    }
    
    private void buildPartialRepeatedFields(User result) {
      if (this.badgeImageListBuilder_ == null) {
        if ((this.bitField0_ & 0x100000) != 0) {
          this.badgeImageList_ = Collections.unmodifiableList(this.badgeImageList_);
          this.bitField0_ &= 0xFFEFFFFF;
        } 
        result.badgeImageList_ = this.badgeImageList_;
      } else {
        result.badgeImageList_ = this.badgeImageListBuilder_.build();
      } 
      if (this.realTimeIconsListBuilder_ == null) {
        if ((this.bitField0_ & 0x8000000) != 0) {
          this.realTimeIconsList_ = Collections.unmodifiableList(this.realTimeIconsList_);
          this.bitField0_ &= 0xF7FFFFFF;
        } 
        result.realTimeIconsList_ = this.realTimeIconsList_;
      } else {
        result.realTimeIconsList_ = this.realTimeIconsListBuilder_.build();
      } 
    }
    
    private void buildPartial0(User result) {
      int from_bitField0_ = this.bitField0_;
      if ((from_bitField0_ & 0x1) != 0)
        result.id_ = this.id_; 
      if ((from_bitField0_ & 0x2) != 0)
        result.shortId_ = this.shortId_; 
      if ((from_bitField0_ & 0x4) != 0)
        result.nickName_ = this.nickName_; 
      if ((from_bitField0_ & 0x8) != 0)
        result.gender_ = this.gender_; 
      if ((from_bitField0_ & 0x10) != 0)
        result.signature_ = this.signature_; 
      if ((from_bitField0_ & 0x20) != 0)
        result.level_ = this.level_; 
      if ((from_bitField0_ & 0x40) != 0)
        result.birthday_ = this.birthday_; 
      if ((from_bitField0_ & 0x80) != 0)
        result.telephone_ = this.telephone_; 
      int to_bitField0_ = 0;
      if ((from_bitField0_ & 0x100) != 0) {
        result.avatarThumb_ = (this.avatarThumbBuilder_ == null) ? this.avatarThumb_ : (Image)this.avatarThumbBuilder_.build();
        to_bitField0_ |= 0x1;
      } 
      if ((from_bitField0_ & 0x200) != 0) {
        result.avatarMedium_ = (this.avatarMediumBuilder_ == null) ? this.avatarMedium_ : (Image)this.avatarMediumBuilder_.build();
        to_bitField0_ |= 0x2;
      } 
      if ((from_bitField0_ & 0x400) != 0) {
        result.avatarLarge_ = (this.avatarLargeBuilder_ == null) ? this.avatarLarge_ : (Image)this.avatarLargeBuilder_.build();
        to_bitField0_ |= 0x4;
      } 
      if ((from_bitField0_ & 0x800) != 0)
        result.verified_ = this.verified_; 
      if ((from_bitField0_ & 0x1000) != 0)
        result.experience_ = this.experience_; 
      if ((from_bitField0_ & 0x2000) != 0)
        result.city_ = this.city_; 
      if ((from_bitField0_ & 0x4000) != 0)
        result.status_ = this.status_; 
      if ((from_bitField0_ & 0x8000) != 0)
        result.createTime_ = this.createTime_; 
      if ((from_bitField0_ & 0x10000) != 0)
        result.modifyTime_ = this.modifyTime_; 
      if ((from_bitField0_ & 0x20000) != 0)
        result.secret_ = this.secret_; 
      if ((from_bitField0_ & 0x40000) != 0)
        result.shareQrcodeUri_ = this.shareQrcodeUri_; 
      if ((from_bitField0_ & 0x80000) != 0)
        result.incomeSharePercent_ = this.incomeSharePercent_; 
      if ((from_bitField0_ & 0x200000) != 0) {
        result.followInfo_ = (this.followInfoBuilder_ == null) ? this.followInfo_ : (FollowInfo)this.followInfoBuilder_.build();
        to_bitField0_ |= 0x8;
      } 
      if ((from_bitField0_ & 0x400000) != 0) {
        result.payGrade_ = (this.payGradeBuilder_ == null) ? this.payGrade_ : (PayGrade)this.payGradeBuilder_.build();
        to_bitField0_ |= 0x10;
      } 
      if ((from_bitField0_ & 0x800000) != 0) {
        result.fansClub_ = (this.fansClubBuilder_ == null) ? this.fansClub_ : (FansClub)this.fansClubBuilder_.build();
        to_bitField0_ |= 0x20;
      } 
      if ((from_bitField0_ & 0x1000000) != 0)
        result.specialId_ = this.specialId_; 
      if ((from_bitField0_ & 0x2000000) != 0) {
        result.avatarBorder_ = (this.avatarBorderBuilder_ == null) ? this.avatarBorder_ : (Image)this.avatarBorderBuilder_.build();
        to_bitField0_ |= 0x40;
      } 
      if ((from_bitField0_ & 0x4000000) != 0) {
        result.medal_ = (this.medalBuilder_ == null) ? this.medal_ : (Image)this.medalBuilder_.build();
        to_bitField0_ |= 0x80;
      } 
      if ((from_bitField0_ & 0x10000000) != 0)
        result.displayId_ = this.displayId_; 
      if ((from_bitField0_ & 0x20000000) != 0)
        result.secUid_ = this.secUid_; 
      if ((from_bitField0_ & 0x40000000) != 0)
        result.fanTicketCount_ = this.fanTicketCount_; 
      if ((from_bitField0_ & Integer.MIN_VALUE) != 0)
        result.idStr_ = this.idStr_; 
      result.bitField0_ |= to_bitField0_;
    }
    
    private void buildPartial1(User result) {
      int from_bitField1_ = this.bitField1_;
      if ((from_bitField1_ & 0x1) != 0)
        result.ageRange_ = this.ageRange_; 
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
      if (other instanceof User)
        return mergeFrom((User)other); 
      super.mergeFrom(other);
      return this;
    }
    
    public Builder mergeFrom(User other) {
      if (other == User.getDefaultInstance())
        return this; 
      if (other.getId() != 0L)
        setId(other.getId()); 
      if (other.getShortId() != 0L)
        setShortId(other.getShortId()); 
      if (!other.getNickName().isEmpty()) {
        this.nickName_ = other.nickName_;
        this.bitField0_ |= 0x4;
        onChanged();
      } 
      if (other.getGender() != 0)
        setGender(other.getGender()); 
      if (!other.getSignature().isEmpty()) {
        this.signature_ = other.signature_;
        this.bitField0_ |= 0x10;
        onChanged();
      } 
      if (other.getLevel() != 0)
        setLevel(other.getLevel()); 
      if (other.getBirthday() != 0L)
        setBirthday(other.getBirthday()); 
      if (!other.getTelephone().isEmpty()) {
        this.telephone_ = other.telephone_;
        this.bitField0_ |= 0x80;
        onChanged();
      } 
      if (other.hasAvatarThumb())
        mergeAvatarThumb(other.getAvatarThumb()); 
      if (other.hasAvatarMedium())
        mergeAvatarMedium(other.getAvatarMedium()); 
      if (other.hasAvatarLarge())
        mergeAvatarLarge(other.getAvatarLarge()); 
      if (other.getVerified())
        setVerified(other.getVerified()); 
      if (other.getExperience() != 0)
        setExperience(other.getExperience()); 
      if (!other.getCity().isEmpty()) {
        this.city_ = other.city_;
        this.bitField0_ |= 0x2000;
        onChanged();
      } 
      if (other.getStatus() != 0)
        setStatus(other.getStatus()); 
      if (other.getCreateTime() != 0L)
        setCreateTime(other.getCreateTime()); 
      if (other.getModifyTime() != 0L)
        setModifyTime(other.getModifyTime()); 
      if (other.getSecret() != 0)
        setSecret(other.getSecret()); 
      if (!other.getShareQrcodeUri().isEmpty()) {
        this.shareQrcodeUri_ = other.shareQrcodeUri_;
        this.bitField0_ |= 0x40000;
        onChanged();
      } 
      if (other.getIncomeSharePercent() != 0)
        setIncomeSharePercent(other.getIncomeSharePercent()); 
      if (this.badgeImageListBuilder_ == null) {
        if (!other.badgeImageList_.isEmpty()) {
          if (this.badgeImageList_.isEmpty()) {
            this.badgeImageList_ = other.badgeImageList_;
            this.bitField0_ &= 0xFFEFFFFF;
          } else {
            ensureBadgeImageListIsMutable();
            this.badgeImageList_.addAll(other.badgeImageList_);
          } 
          onChanged();
        } 
      } else if (!other.badgeImageList_.isEmpty()) {
        if (this.badgeImageListBuilder_.isEmpty()) {
          this.badgeImageListBuilder_.dispose();
          this.badgeImageListBuilder_ = null;
          this.badgeImageList_ = other.badgeImageList_;
          this.bitField0_ &= 0xFFEFFFFF;
          this.badgeImageListBuilder_ = User.alwaysUseFieldBuilders ? getBadgeImageListFieldBuilder() : null;
        } else {
          this.badgeImageListBuilder_.addAllMessages(other.badgeImageList_);
        } 
      } 
      if (other.hasFollowInfo())
        mergeFollowInfo(other.getFollowInfo()); 
      if (other.hasPayGrade())
        mergePayGrade(other.getPayGrade()); 
      if (other.hasFansClub())
        mergeFansClub(other.getFansClub()); 
      if (!other.getSpecialId().isEmpty()) {
        this.specialId_ = other.specialId_;
        this.bitField0_ |= 0x1000000;
        onChanged();
      } 
      if (other.hasAvatarBorder())
        mergeAvatarBorder(other.getAvatarBorder()); 
      if (other.hasMedal())
        mergeMedal(other.getMedal()); 
      if (this.realTimeIconsListBuilder_ == null) {
        if (!other.realTimeIconsList_.isEmpty()) {
          if (this.realTimeIconsList_.isEmpty()) {
            this.realTimeIconsList_ = other.realTimeIconsList_;
            this.bitField0_ &= 0xF7FFFFFF;
          } else {
            ensureRealTimeIconsListIsMutable();
            this.realTimeIconsList_.addAll(other.realTimeIconsList_);
          } 
          onChanged();
        } 
      } else if (!other.realTimeIconsList_.isEmpty()) {
        if (this.realTimeIconsListBuilder_.isEmpty()) {
          this.realTimeIconsListBuilder_.dispose();
          this.realTimeIconsListBuilder_ = null;
          this.realTimeIconsList_ = other.realTimeIconsList_;
          this.bitField0_ &= 0xF7FFFFFF;
          this.realTimeIconsListBuilder_ = User.alwaysUseFieldBuilders ? getRealTimeIconsListFieldBuilder() : null;
        } else {
          this.realTimeIconsListBuilder_.addAllMessages(other.realTimeIconsList_);
        } 
      } 
      if (!other.getDisplayId().isEmpty()) {
        this.displayId_ = other.displayId_;
        this.bitField0_ |= 0x10000000;
        onChanged();
      } 
      if (!other.getSecUid().isEmpty()) {
        this.secUid_ = other.secUid_;
        this.bitField0_ |= 0x20000000;
        onChanged();
      } 
      if (other.getFanTicketCount() != 0L)
        setFanTicketCount(other.getFanTicketCount()); 
      if (!other.getIdStr().isEmpty()) {
        this.idStr_ = other.idStr_;
        this.bitField0_ |= Integer.MIN_VALUE;
        onChanged();
      } 
      if (other.getAgeRange() != 0)
        setAgeRange(other.getAgeRange()); 
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
          Image m;
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              continue;
            case 8:
              this.id_ = input.readUInt64();
              this.bitField0_ |= 0x1;
              continue;
            case 16:
              this.shortId_ = input.readUInt64();
              this.bitField0_ |= 0x2;
              continue;
            case 26:
              this.nickName_ = input.readStringRequireUtf8();
              this.bitField0_ |= 0x4;
              continue;
            case 32:
              this.gender_ = input.readUInt32();
              this.bitField0_ |= 0x8;
              continue;
            case 42:
              this.signature_ = input.readStringRequireUtf8();
              this.bitField0_ |= 0x10;
              continue;
            case 48:
              this.level_ = input.readUInt32();
              this.bitField0_ |= 0x20;
              continue;
            case 56:
              this.birthday_ = input.readUInt64();
              this.bitField0_ |= 0x40;
              continue;
            case 66:
              this.telephone_ = input.readStringRequireUtf8();
              this.bitField0_ |= 0x80;
              continue;
            case 74:
              input.readMessage((MessageLite.Builder)getAvatarThumbFieldBuilder().getBuilder(), extensionRegistry);
              this.bitField0_ |= 0x100;
              continue;
            case 82:
              input.readMessage((MessageLite.Builder)getAvatarMediumFieldBuilder().getBuilder(), extensionRegistry);
              this.bitField0_ |= 0x200;
              continue;
            case 90:
              input.readMessage((MessageLite.Builder)getAvatarLargeFieldBuilder().getBuilder(), extensionRegistry);
              this.bitField0_ |= 0x400;
              continue;
            case 96:
              this.verified_ = input.readBool();
              this.bitField0_ |= 0x800;
              continue;
            case 104:
              this.experience_ = input.readUInt32();
              this.bitField0_ |= 0x1000;
              continue;
            case 114:
              this.city_ = input.readStringRequireUtf8();
              this.bitField0_ |= 0x2000;
              continue;
            case 120:
              this.status_ = input.readInt32();
              this.bitField0_ |= 0x4000;
              continue;
            case 128:
              this.createTime_ = input.readUInt64();
              this.bitField0_ |= 0x8000;
              continue;
            case 136:
              this.modifyTime_ = input.readUInt64();
              this.bitField0_ |= 0x10000;
              continue;
            case 144:
              this.secret_ = input.readUInt32();
              this.bitField0_ |= 0x20000;
              continue;
            case 154:
              this.shareQrcodeUri_ = input.readStringRequireUtf8();
              this.bitField0_ |= 0x40000;
              continue;
            case 160:
              this.incomeSharePercent_ = input.readUInt32();
              this.bitField0_ |= 0x80000;
              continue;
            case 170:
              m = (Image)input.readMessage(Image.parser(), extensionRegistry);
              if (this.badgeImageListBuilder_ == null) {
                ensureBadgeImageListIsMutable();
                this.badgeImageList_.add(m);
                continue;
              } 
              this.badgeImageListBuilder_.addMessage((AbstractMessage)m);
              continue;
            case 178:
              input.readMessage((MessageLite.Builder)getFollowInfoFieldBuilder().getBuilder(), extensionRegistry);
              this.bitField0_ |= 0x200000;
              continue;
            case 186:
              input.readMessage((MessageLite.Builder)getPayGradeFieldBuilder().getBuilder(), extensionRegistry);
              this.bitField0_ |= 0x400000;
              continue;
            case 194:
              input.readMessage((MessageLite.Builder)getFansClubFieldBuilder().getBuilder(), extensionRegistry);
              this.bitField0_ |= 0x800000;
              continue;
            case 210:
              this.specialId_ = input.readStringRequireUtf8();
              this.bitField0_ |= 0x1000000;
              continue;
            case 218:
              input.readMessage((MessageLite.Builder)getAvatarBorderFieldBuilder().getBuilder(), extensionRegistry);
              this.bitField0_ |= 0x2000000;
              continue;
            case 226:
              input.readMessage((MessageLite.Builder)getMedalFieldBuilder().getBuilder(), extensionRegistry);
              this.bitField0_ |= 0x4000000;
              continue;
            case 234:
              m = (Image)input.readMessage(Image.parser(), extensionRegistry);
              if (this.realTimeIconsListBuilder_ == null) {
                ensureRealTimeIconsListIsMutable();
                this.realTimeIconsList_.add(m);
                continue;
              } 
              this.realTimeIconsListBuilder_.addMessage((AbstractMessage)m);
              continue;
            case 306:
              this.displayId_ = input.readStringRequireUtf8();
              this.bitField0_ |= 0x10000000;
              continue;
            case 370:
              this.secUid_ = input.readStringRequireUtf8();
              this.bitField0_ |= 0x20000000;
              continue;
            case 8176:
              this.fanTicketCount_ = input.readUInt64();
              this.bitField0_ |= 0x40000000;
              continue;
            case 8226:
              this.idStr_ = input.readStringRequireUtf8();
              this.bitField0_ |= Integer.MIN_VALUE;
              continue;
            case 8360:
              this.ageRange_ = input.readUInt32();
              this.bitField1_ |= 0x1;
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
    
    public long getId() {
      return this.id_;
    }
    
    public Builder setId(long value) {
      this.id_ = value;
      this.bitField0_ |= 0x1;
      onChanged();
      return this;
    }
    
    public Builder clearId() {
      this.bitField0_ &= 0xFFFFFFFE;
      this.id_ = 0L;
      onChanged();
      return this;
    }
    
    public long getShortId() {
      return this.shortId_;
    }
    
    public Builder setShortId(long value) {
      this.shortId_ = value;
      this.bitField0_ |= 0x2;
      onChanged();
      return this;
    }
    
    public Builder clearShortId() {
      this.bitField0_ &= 0xFFFFFFFD;
      this.shortId_ = 0L;
      onChanged();
      return this;
    }
    
    public String getNickName() {
      Object ref = this.nickName_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.nickName_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getNickNameBytes() {
      Object ref = this.nickName_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.nickName_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setNickName(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.nickName_ = value;
      this.bitField0_ |= 0x4;
      onChanged();
      return this;
    }
    
    public Builder clearNickName() {
      this.nickName_ = User.getDefaultInstance().getNickName();
      this.bitField0_ &= 0xFFFFFFFB;
      onChanged();
      return this;
    }
    
    public Builder setNickNameBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      User.checkByteStringIsUtf8(value);
      this.nickName_ = value;
      this.bitField0_ |= 0x4;
      onChanged();
      return this;
    }
    
    public int getGender() {
      return this.gender_;
    }
    
    public Builder setGender(int value) {
      this.gender_ = value;
      this.bitField0_ |= 0x8;
      onChanged();
      return this;
    }
    
    public Builder clearGender() {
      this.bitField0_ &= 0xFFFFFFF7;
      this.gender_ = 0;
      onChanged();
      return this;
    }
    
    public String getSignature() {
      Object ref = this.signature_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.signature_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getSignatureBytes() {
      Object ref = this.signature_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.signature_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setSignature(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.signature_ = value;
      this.bitField0_ |= 0x10;
      onChanged();
      return this;
    }
    
    public Builder clearSignature() {
      this.signature_ = User.getDefaultInstance().getSignature();
      this.bitField0_ &= 0xFFFFFFEF;
      onChanged();
      return this;
    }
    
    public Builder setSignatureBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      User.checkByteStringIsUtf8(value);
      this.signature_ = value;
      this.bitField0_ |= 0x10;
      onChanged();
      return this;
    }
    
    public int getLevel() {
      return this.level_;
    }
    
    public Builder setLevel(int value) {
      this.level_ = value;
      this.bitField0_ |= 0x20;
      onChanged();
      return this;
    }
    
    public Builder clearLevel() {
      this.bitField0_ &= 0xFFFFFFDF;
      this.level_ = 0;
      onChanged();
      return this;
    }
    
    public long getBirthday() {
      return this.birthday_;
    }
    
    public Builder setBirthday(long value) {
      this.birthday_ = value;
      this.bitField0_ |= 0x40;
      onChanged();
      return this;
    }
    
    public Builder clearBirthday() {
      this.bitField0_ &= 0xFFFFFFBF;
      this.birthday_ = 0L;
      onChanged();
      return this;
    }
    
    public String getTelephone() {
      Object ref = this.telephone_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.telephone_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getTelephoneBytes() {
      Object ref = this.telephone_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.telephone_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setTelephone(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.telephone_ = value;
      this.bitField0_ |= 0x80;
      onChanged();
      return this;
    }
    
    public Builder clearTelephone() {
      this.telephone_ = User.getDefaultInstance().getTelephone();
      this.bitField0_ &= 0xFFFFFF7F;
      onChanged();
      return this;
    }
    
    public Builder setTelephoneBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      User.checkByteStringIsUtf8(value);
      this.telephone_ = value;
      this.bitField0_ |= 0x80;
      onChanged();
      return this;
    }
    
    public boolean hasAvatarThumb() {
      return ((this.bitField0_ & 0x100) != 0);
    }
    
    public Image getAvatarThumb() {
      if (this.avatarThumbBuilder_ == null)
        return (this.avatarThumb_ == null) ? Image.getDefaultInstance() : this.avatarThumb_; 
      return (Image)this.avatarThumbBuilder_.getMessage();
    }
    
    public Builder setAvatarThumb(Image value) {
      if (this.avatarThumbBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        this.avatarThumb_ = value;
      } else {
        this.avatarThumbBuilder_.setMessage((AbstractMessage)value);
      } 
      this.bitField0_ |= 0x100;
      onChanged();
      return this;
    }
    
    public Builder setAvatarThumb(Image.Builder builderForValue) {
      if (this.avatarThumbBuilder_ == null) {
        this.avatarThumb_ = builderForValue.build();
      } else {
        this.avatarThumbBuilder_.setMessage((AbstractMessage)builderForValue.build());
      } 
      this.bitField0_ |= 0x100;
      onChanged();
      return this;
    }
    
    public Builder mergeAvatarThumb(Image value) {
      if (this.avatarThumbBuilder_ == null) {
        if ((this.bitField0_ & 0x100) != 0 && this.avatarThumb_ != null && this.avatarThumb_ != Image.getDefaultInstance()) {
          getAvatarThumbBuilder().mergeFrom(value);
        } else {
          this.avatarThumb_ = value;
        } 
      } else {
        this.avatarThumbBuilder_.mergeFrom((AbstractMessage)value);
      } 
      if (this.avatarThumb_ != null) {
        this.bitField0_ |= 0x100;
        onChanged();
      } 
      return this;
    }
    
    public Builder clearAvatarThumb() {
      this.bitField0_ &= 0xFFFFFEFF;
      this.avatarThumb_ = null;
      if (this.avatarThumbBuilder_ != null) {
        this.avatarThumbBuilder_.dispose();
        this.avatarThumbBuilder_ = null;
      } 
      onChanged();
      return this;
    }
    
    public Image.Builder getAvatarThumbBuilder() {
      this.bitField0_ |= 0x100;
      onChanged();
      return (Image.Builder)getAvatarThumbFieldBuilder().getBuilder();
    }
    
    public ImageOrBuilder getAvatarThumbOrBuilder() {
      if (this.avatarThumbBuilder_ != null)
        return (ImageOrBuilder)this.avatarThumbBuilder_.getMessageOrBuilder(); 
      return (this.avatarThumb_ == null) ? Image.getDefaultInstance() : this.avatarThumb_;
    }
    
    private SingleFieldBuilderV3<Image, Image.Builder, ImageOrBuilder> getAvatarThumbFieldBuilder() {
      if (this.avatarThumbBuilder_ == null) {
        this.avatarThumbBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getAvatarThumb(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.avatarThumb_ = null;
      } 
      return this.avatarThumbBuilder_;
    }
    
    public boolean hasAvatarMedium() {
      return ((this.bitField0_ & 0x200) != 0);
    }
    
    public Image getAvatarMedium() {
      if (this.avatarMediumBuilder_ == null)
        return (this.avatarMedium_ == null) ? Image.getDefaultInstance() : this.avatarMedium_; 
      return (Image)this.avatarMediumBuilder_.getMessage();
    }
    
    public Builder setAvatarMedium(Image value) {
      if (this.avatarMediumBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        this.avatarMedium_ = value;
      } else {
        this.avatarMediumBuilder_.setMessage((AbstractMessage)value);
      } 
      this.bitField0_ |= 0x200;
      onChanged();
      return this;
    }
    
    public Builder setAvatarMedium(Image.Builder builderForValue) {
      if (this.avatarMediumBuilder_ == null) {
        this.avatarMedium_ = builderForValue.build();
      } else {
        this.avatarMediumBuilder_.setMessage((AbstractMessage)builderForValue.build());
      } 
      this.bitField0_ |= 0x200;
      onChanged();
      return this;
    }
    
    public Builder mergeAvatarMedium(Image value) {
      if (this.avatarMediumBuilder_ == null) {
        if ((this.bitField0_ & 0x200) != 0 && this.avatarMedium_ != null && this.avatarMedium_ != Image.getDefaultInstance()) {
          getAvatarMediumBuilder().mergeFrom(value);
        } else {
          this.avatarMedium_ = value;
        } 
      } else {
        this.avatarMediumBuilder_.mergeFrom((AbstractMessage)value);
      } 
      if (this.avatarMedium_ != null) {
        this.bitField0_ |= 0x200;
        onChanged();
      } 
      return this;
    }
    
    public Builder clearAvatarMedium() {
      this.bitField0_ &= 0xFFFFFDFF;
      this.avatarMedium_ = null;
      if (this.avatarMediumBuilder_ != null) {
        this.avatarMediumBuilder_.dispose();
        this.avatarMediumBuilder_ = null;
      } 
      onChanged();
      return this;
    }
    
    public Image.Builder getAvatarMediumBuilder() {
      this.bitField0_ |= 0x200;
      onChanged();
      return (Image.Builder)getAvatarMediumFieldBuilder().getBuilder();
    }
    
    public ImageOrBuilder getAvatarMediumOrBuilder() {
      if (this.avatarMediumBuilder_ != null)
        return (ImageOrBuilder)this.avatarMediumBuilder_.getMessageOrBuilder(); 
      return (this.avatarMedium_ == null) ? Image.getDefaultInstance() : this.avatarMedium_;
    }
    
    private SingleFieldBuilderV3<Image, Image.Builder, ImageOrBuilder> getAvatarMediumFieldBuilder() {
      if (this.avatarMediumBuilder_ == null) {
        this.avatarMediumBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getAvatarMedium(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.avatarMedium_ = null;
      } 
      return this.avatarMediumBuilder_;
    }
    
    public boolean hasAvatarLarge() {
      return ((this.bitField0_ & 0x400) != 0);
    }
    
    public Image getAvatarLarge() {
      if (this.avatarLargeBuilder_ == null)
        return (this.avatarLarge_ == null) ? Image.getDefaultInstance() : this.avatarLarge_; 
      return (Image)this.avatarLargeBuilder_.getMessage();
    }
    
    public Builder setAvatarLarge(Image value) {
      if (this.avatarLargeBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        this.avatarLarge_ = value;
      } else {
        this.avatarLargeBuilder_.setMessage((AbstractMessage)value);
      } 
      this.bitField0_ |= 0x400;
      onChanged();
      return this;
    }
    
    public Builder setAvatarLarge(Image.Builder builderForValue) {
      if (this.avatarLargeBuilder_ == null) {
        this.avatarLarge_ = builderForValue.build();
      } else {
        this.avatarLargeBuilder_.setMessage((AbstractMessage)builderForValue.build());
      } 
      this.bitField0_ |= 0x400;
      onChanged();
      return this;
    }
    
    public Builder mergeAvatarLarge(Image value) {
      if (this.avatarLargeBuilder_ == null) {
        if ((this.bitField0_ & 0x400) != 0 && this.avatarLarge_ != null && this.avatarLarge_ != Image.getDefaultInstance()) {
          getAvatarLargeBuilder().mergeFrom(value);
        } else {
          this.avatarLarge_ = value;
        } 
      } else {
        this.avatarLargeBuilder_.mergeFrom((AbstractMessage)value);
      } 
      if (this.avatarLarge_ != null) {
        this.bitField0_ |= 0x400;
        onChanged();
      } 
      return this;
    }
    
    public Builder clearAvatarLarge() {
      this.bitField0_ &= 0xFFFFFBFF;
      this.avatarLarge_ = null;
      if (this.avatarLargeBuilder_ != null) {
        this.avatarLargeBuilder_.dispose();
        this.avatarLargeBuilder_ = null;
      } 
      onChanged();
      return this;
    }
    
    public Image.Builder getAvatarLargeBuilder() {
      this.bitField0_ |= 0x400;
      onChanged();
      return (Image.Builder)getAvatarLargeFieldBuilder().getBuilder();
    }
    
    public ImageOrBuilder getAvatarLargeOrBuilder() {
      if (this.avatarLargeBuilder_ != null)
        return (ImageOrBuilder)this.avatarLargeBuilder_.getMessageOrBuilder(); 
      return (this.avatarLarge_ == null) ? Image.getDefaultInstance() : this.avatarLarge_;
    }
    
    private SingleFieldBuilderV3<Image, Image.Builder, ImageOrBuilder> getAvatarLargeFieldBuilder() {
      if (this.avatarLargeBuilder_ == null) {
        this.avatarLargeBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getAvatarLarge(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.avatarLarge_ = null;
      } 
      return this.avatarLargeBuilder_;
    }
    
    public boolean getVerified() {
      return this.verified_;
    }
    
    public Builder setVerified(boolean value) {
      this.verified_ = value;
      this.bitField0_ |= 0x800;
      onChanged();
      return this;
    }
    
    public Builder clearVerified() {
      this.bitField0_ &= 0xFFFFF7FF;
      this.verified_ = false;
      onChanged();
      return this;
    }
    
    public int getExperience() {
      return this.experience_;
    }
    
    public Builder setExperience(int value) {
      this.experience_ = value;
      this.bitField0_ |= 0x1000;
      onChanged();
      return this;
    }
    
    public Builder clearExperience() {
      this.bitField0_ &= 0xFFFFEFFF;
      this.experience_ = 0;
      onChanged();
      return this;
    }
    
    public String getCity() {
      Object ref = this.city_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.city_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getCityBytes() {
      Object ref = this.city_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.city_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setCity(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.city_ = value;
      this.bitField0_ |= 0x2000;
      onChanged();
      return this;
    }
    
    public Builder clearCity() {
      this.city_ = User.getDefaultInstance().getCity();
      this.bitField0_ &= 0xFFFFDFFF;
      onChanged();
      return this;
    }
    
    public Builder setCityBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      User.checkByteStringIsUtf8(value);
      this.city_ = value;
      this.bitField0_ |= 0x2000;
      onChanged();
      return this;
    }
    
    public int getStatus() {
      return this.status_;
    }
    
    public Builder setStatus(int value) {
      this.status_ = value;
      this.bitField0_ |= 0x4000;
      onChanged();
      return this;
    }
    
    public Builder clearStatus() {
      this.bitField0_ &= 0xFFFFBFFF;
      this.status_ = 0;
      onChanged();
      return this;
    }
    
    public long getCreateTime() {
      return this.createTime_;
    }
    
    public Builder setCreateTime(long value) {
      this.createTime_ = value;
      this.bitField0_ |= 0x8000;
      onChanged();
      return this;
    }
    
    public Builder clearCreateTime() {
      this.bitField0_ &= 0xFFFF7FFF;
      this.createTime_ = 0L;
      onChanged();
      return this;
    }
    
    public long getModifyTime() {
      return this.modifyTime_;
    }
    
    public Builder setModifyTime(long value) {
      this.modifyTime_ = value;
      this.bitField0_ |= 0x10000;
      onChanged();
      return this;
    }
    
    public Builder clearModifyTime() {
      this.bitField0_ &= 0xFFFEFFFF;
      this.modifyTime_ = 0L;
      onChanged();
      return this;
    }
    
    public int getSecret() {
      return this.secret_;
    }
    
    public Builder setSecret(int value) {
      this.secret_ = value;
      this.bitField0_ |= 0x20000;
      onChanged();
      return this;
    }
    
    public Builder clearSecret() {
      this.bitField0_ &= 0xFFFDFFFF;
      this.secret_ = 0;
      onChanged();
      return this;
    }
    
    public String getShareQrcodeUri() {
      Object ref = this.shareQrcodeUri_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.shareQrcodeUri_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getShareQrcodeUriBytes() {
      Object ref = this.shareQrcodeUri_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.shareQrcodeUri_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setShareQrcodeUri(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.shareQrcodeUri_ = value;
      this.bitField0_ |= 0x40000;
      onChanged();
      return this;
    }
    
    public Builder clearShareQrcodeUri() {
      this.shareQrcodeUri_ = User.getDefaultInstance().getShareQrcodeUri();
      this.bitField0_ &= 0xFFFBFFFF;
      onChanged();
      return this;
    }
    
    public Builder setShareQrcodeUriBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      User.checkByteStringIsUtf8(value);
      this.shareQrcodeUri_ = value;
      this.bitField0_ |= 0x40000;
      onChanged();
      return this;
    }
    
    public int getIncomeSharePercent() {
      return this.incomeSharePercent_;
    }
    
    public Builder setIncomeSharePercent(int value) {
      this.incomeSharePercent_ = value;
      this.bitField0_ |= 0x80000;
      onChanged();
      return this;
    }
    
    public Builder clearIncomeSharePercent() {
      this.bitField0_ &= 0xFFF7FFFF;
      this.incomeSharePercent_ = 0;
      onChanged();
      return this;
    }
    
    private void ensureBadgeImageListIsMutable() {
      if ((this.bitField0_ & 0x100000) == 0) {
        this.badgeImageList_ = new ArrayList<>(this.badgeImageList_);
        this.bitField0_ |= 0x100000;
      } 
    }
    
    public List<Image> getBadgeImageListList() {
      if (this.badgeImageListBuilder_ == null)
        return Collections.unmodifiableList(this.badgeImageList_); 
      return this.badgeImageListBuilder_.getMessageList();
    }
    
    public int getBadgeImageListCount() {
      if (this.badgeImageListBuilder_ == null)
        return this.badgeImageList_.size(); 
      return this.badgeImageListBuilder_.getCount();
    }
    
    public Image getBadgeImageList(int index) {
      if (this.badgeImageListBuilder_ == null)
        return this.badgeImageList_.get(index); 
      return (Image)this.badgeImageListBuilder_.getMessage(index);
    }
    
    public Builder setBadgeImageList(int index, Image value) {
      if (this.badgeImageListBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        ensureBadgeImageListIsMutable();
        this.badgeImageList_.set(index, value);
        onChanged();
      } else {
        this.badgeImageListBuilder_.setMessage(index, (AbstractMessage)value);
      } 
      return this;
    }
    
    public Builder setBadgeImageList(int index, Image.Builder builderForValue) {
      if (this.badgeImageListBuilder_ == null) {
        ensureBadgeImageListIsMutable();
        this.badgeImageList_.set(index, builderForValue.build());
        onChanged();
      } else {
        this.badgeImageListBuilder_.setMessage(index, (AbstractMessage)builderForValue.build());
      } 
      return this;
    }
    
    public Builder addBadgeImageList(Image value) {
      if (this.badgeImageListBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        ensureBadgeImageListIsMutable();
        this.badgeImageList_.add(value);
        onChanged();
      } else {
        this.badgeImageListBuilder_.addMessage((AbstractMessage)value);
      } 
      return this;
    }
    
    public Builder addBadgeImageList(int index, Image value) {
      if (this.badgeImageListBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        ensureBadgeImageListIsMutable();
        this.badgeImageList_.add(index, value);
        onChanged();
      } else {
        this.badgeImageListBuilder_.addMessage(index, (AbstractMessage)value);
      } 
      return this;
    }
    
    public Builder addBadgeImageList(Image.Builder builderForValue) {
      if (this.badgeImageListBuilder_ == null) {
        ensureBadgeImageListIsMutable();
        this.badgeImageList_.add(builderForValue.build());
        onChanged();
      } else {
        this.badgeImageListBuilder_.addMessage((AbstractMessage)builderForValue.build());
      } 
      return this;
    }
    
    public Builder addBadgeImageList(int index, Image.Builder builderForValue) {
      if (this.badgeImageListBuilder_ == null) {
        ensureBadgeImageListIsMutable();
        this.badgeImageList_.add(index, builderForValue.build());
        onChanged();
      } else {
        this.badgeImageListBuilder_.addMessage(index, (AbstractMessage)builderForValue.build());
      } 
      return this;
    }
    
    public Builder addAllBadgeImageList(Iterable<? extends Image> values) {
      if (this.badgeImageListBuilder_ == null) {
        ensureBadgeImageListIsMutable();
        AbstractMessageLite.Builder.addAll(values, this.badgeImageList_);
        onChanged();
      } else {
        this.badgeImageListBuilder_.addAllMessages(values);
      } 
      return this;
    }
    
    public Builder clearBadgeImageList() {
      if (this.badgeImageListBuilder_ == null) {
        this.badgeImageList_ = Collections.emptyList();
        this.bitField0_ &= 0xFFEFFFFF;
        onChanged();
      } else {
        this.badgeImageListBuilder_.clear();
      } 
      return this;
    }
    
    public Builder removeBadgeImageList(int index) {
      if (this.badgeImageListBuilder_ == null) {
        ensureBadgeImageListIsMutable();
        this.badgeImageList_.remove(index);
        onChanged();
      } else {
        this.badgeImageListBuilder_.remove(index);
      } 
      return this;
    }
    
    public Image.Builder getBadgeImageListBuilder(int index) {
      return (Image.Builder)getBadgeImageListFieldBuilder().getBuilder(index);
    }
    
    public ImageOrBuilder getBadgeImageListOrBuilder(int index) {
      if (this.badgeImageListBuilder_ == null)
        return this.badgeImageList_.get(index); 
      return (ImageOrBuilder)this.badgeImageListBuilder_.getMessageOrBuilder(index);
    }
    
    public List<? extends ImageOrBuilder> getBadgeImageListOrBuilderList() {
      if (this.badgeImageListBuilder_ != null)
        return this.badgeImageListBuilder_.getMessageOrBuilderList(); 
      return Collections.unmodifiableList((List)this.badgeImageList_);
    }
    
    public Image.Builder addBadgeImageListBuilder() {
      return (Image.Builder)getBadgeImageListFieldBuilder().addBuilder((AbstractMessage)Image.getDefaultInstance());
    }
    
    public Image.Builder addBadgeImageListBuilder(int index) {
      return (Image.Builder)getBadgeImageListFieldBuilder().addBuilder(index, (AbstractMessage)Image.getDefaultInstance());
    }
    
    public List<Image.Builder> getBadgeImageListBuilderList() {
      return getBadgeImageListFieldBuilder().getBuilderList();
    }
    
    private RepeatedFieldBuilderV3<Image, Image.Builder, ImageOrBuilder> getBadgeImageListFieldBuilder() {
      if (this.badgeImageListBuilder_ == null) {
        this.badgeImageListBuilder_ = new RepeatedFieldBuilderV3(this.badgeImageList_, ((this.bitField0_ & 0x100000) != 0), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.badgeImageList_ = null;
      } 
      return this.badgeImageListBuilder_;
    }
    
    public boolean hasFollowInfo() {
      return ((this.bitField0_ & 0x200000) != 0);
    }
    
    public FollowInfo getFollowInfo() {
      if (this.followInfoBuilder_ == null)
        return (this.followInfo_ == null) ? FollowInfo.getDefaultInstance() : this.followInfo_; 
      return (FollowInfo)this.followInfoBuilder_.getMessage();
    }
    
    public Builder setFollowInfo(FollowInfo value) {
      if (this.followInfoBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        this.followInfo_ = value;
      } else {
        this.followInfoBuilder_.setMessage((AbstractMessage)value);
      } 
      this.bitField0_ |= 0x200000;
      onChanged();
      return this;
    }
    
    public Builder setFollowInfo(FollowInfo.Builder builderForValue) {
      if (this.followInfoBuilder_ == null) {
        this.followInfo_ = builderForValue.build();
      } else {
        this.followInfoBuilder_.setMessage((AbstractMessage)builderForValue.build());
      } 
      this.bitField0_ |= 0x200000;
      onChanged();
      return this;
    }
    
    public Builder mergeFollowInfo(FollowInfo value) {
      if (this.followInfoBuilder_ == null) {
        if ((this.bitField0_ & 0x200000) != 0 && this.followInfo_ != null && this.followInfo_ != FollowInfo.getDefaultInstance()) {
          getFollowInfoBuilder().mergeFrom(value);
        } else {
          this.followInfo_ = value;
        } 
      } else {
        this.followInfoBuilder_.mergeFrom((AbstractMessage)value);
      } 
      if (this.followInfo_ != null) {
        this.bitField0_ |= 0x200000;
        onChanged();
      } 
      return this;
    }
    
    public Builder clearFollowInfo() {
      this.bitField0_ &= 0xFFDFFFFF;
      this.followInfo_ = null;
      if (this.followInfoBuilder_ != null) {
        this.followInfoBuilder_.dispose();
        this.followInfoBuilder_ = null;
      } 
      onChanged();
      return this;
    }
    
    public FollowInfo.Builder getFollowInfoBuilder() {
      this.bitField0_ |= 0x200000;
      onChanged();
      return (FollowInfo.Builder)getFollowInfoFieldBuilder().getBuilder();
    }
    
    public FollowInfoOrBuilder getFollowInfoOrBuilder() {
      if (this.followInfoBuilder_ != null)
        return (FollowInfoOrBuilder)this.followInfoBuilder_.getMessageOrBuilder(); 
      return (this.followInfo_ == null) ? FollowInfo.getDefaultInstance() : this.followInfo_;
    }
    
    private SingleFieldBuilderV3<FollowInfo, FollowInfo.Builder, FollowInfoOrBuilder> getFollowInfoFieldBuilder() {
      if (this.followInfoBuilder_ == null) {
        this.followInfoBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getFollowInfo(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.followInfo_ = null;
      } 
      return this.followInfoBuilder_;
    }
    
    public boolean hasPayGrade() {
      return ((this.bitField0_ & 0x400000) != 0);
    }
    
    public PayGrade getPayGrade() {
      if (this.payGradeBuilder_ == null)
        return (this.payGrade_ == null) ? PayGrade.getDefaultInstance() : this.payGrade_; 
      return (PayGrade)this.payGradeBuilder_.getMessage();
    }
    
    public Builder setPayGrade(PayGrade value) {
      if (this.payGradeBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        this.payGrade_ = value;
      } else {
        this.payGradeBuilder_.setMessage((AbstractMessage)value);
      } 
      this.bitField0_ |= 0x400000;
      onChanged();
      return this;
    }
    
    public Builder setPayGrade(PayGrade.Builder builderForValue) {
      if (this.payGradeBuilder_ == null) {
        this.payGrade_ = builderForValue.build();
      } else {
        this.payGradeBuilder_.setMessage((AbstractMessage)builderForValue.build());
      } 
      this.bitField0_ |= 0x400000;
      onChanged();
      return this;
    }
    
    public Builder mergePayGrade(PayGrade value) {
      if (this.payGradeBuilder_ == null) {
        if ((this.bitField0_ & 0x400000) != 0 && this.payGrade_ != null && this.payGrade_ != PayGrade.getDefaultInstance()) {
          getPayGradeBuilder().mergeFrom(value);
        } else {
          this.payGrade_ = value;
        } 
      } else {
        this.payGradeBuilder_.mergeFrom((AbstractMessage)value);
      } 
      if (this.payGrade_ != null) {
        this.bitField0_ |= 0x400000;
        onChanged();
      } 
      return this;
    }
    
    public Builder clearPayGrade() {
      this.bitField0_ &= 0xFFBFFFFF;
      this.payGrade_ = null;
      if (this.payGradeBuilder_ != null) {
        this.payGradeBuilder_.dispose();
        this.payGradeBuilder_ = null;
      } 
      onChanged();
      return this;
    }
    
    public PayGrade.Builder getPayGradeBuilder() {
      this.bitField0_ |= 0x400000;
      onChanged();
      return (PayGrade.Builder)getPayGradeFieldBuilder().getBuilder();
    }
    
    public PayGradeOrBuilder getPayGradeOrBuilder() {
      if (this.payGradeBuilder_ != null)
        return (PayGradeOrBuilder)this.payGradeBuilder_.getMessageOrBuilder(); 
      return (this.payGrade_ == null) ? PayGrade.getDefaultInstance() : this.payGrade_;
    }
    
    private SingleFieldBuilderV3<PayGrade, PayGrade.Builder, PayGradeOrBuilder> getPayGradeFieldBuilder() {
      if (this.payGradeBuilder_ == null) {
        this.payGradeBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getPayGrade(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.payGrade_ = null;
      } 
      return this.payGradeBuilder_;
    }
    
    public boolean hasFansClub() {
      return ((this.bitField0_ & 0x800000) != 0);
    }
    
    public FansClub getFansClub() {
      if (this.fansClubBuilder_ == null)
        return (this.fansClub_ == null) ? FansClub.getDefaultInstance() : this.fansClub_; 
      return (FansClub)this.fansClubBuilder_.getMessage();
    }
    
    public Builder setFansClub(FansClub value) {
      if (this.fansClubBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        this.fansClub_ = value;
      } else {
        this.fansClubBuilder_.setMessage((AbstractMessage)value);
      } 
      this.bitField0_ |= 0x800000;
      onChanged();
      return this;
    }
    
    public Builder setFansClub(FansClub.Builder builderForValue) {
      if (this.fansClubBuilder_ == null) {
        this.fansClub_ = builderForValue.build();
      } else {
        this.fansClubBuilder_.setMessage((AbstractMessage)builderForValue.build());
      } 
      this.bitField0_ |= 0x800000;
      onChanged();
      return this;
    }
    
    public Builder mergeFansClub(FansClub value) {
      if (this.fansClubBuilder_ == null) {
        if ((this.bitField0_ & 0x800000) != 0 && this.fansClub_ != null && this.fansClub_ != FansClub.getDefaultInstance()) {
          getFansClubBuilder().mergeFrom(value);
        } else {
          this.fansClub_ = value;
        } 
      } else {
        this.fansClubBuilder_.mergeFrom((AbstractMessage)value);
      } 
      if (this.fansClub_ != null) {
        this.bitField0_ |= 0x800000;
        onChanged();
      } 
      return this;
    }
    
    public Builder clearFansClub() {
      this.bitField0_ &= 0xFF7FFFFF;
      this.fansClub_ = null;
      if (this.fansClubBuilder_ != null) {
        this.fansClubBuilder_.dispose();
        this.fansClubBuilder_ = null;
      } 
      onChanged();
      return this;
    }
    
    public FansClub.Builder getFansClubBuilder() {
      this.bitField0_ |= 0x800000;
      onChanged();
      return (FansClub.Builder)getFansClubFieldBuilder().getBuilder();
    }
    
    public FansClubOrBuilder getFansClubOrBuilder() {
      if (this.fansClubBuilder_ != null)
        return (FansClubOrBuilder)this.fansClubBuilder_.getMessageOrBuilder(); 
      return (this.fansClub_ == null) ? FansClub.getDefaultInstance() : this.fansClub_;
    }
    
    private SingleFieldBuilderV3<FansClub, FansClub.Builder, FansClubOrBuilder> getFansClubFieldBuilder() {
      if (this.fansClubBuilder_ == null) {
        this.fansClubBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getFansClub(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.fansClub_ = null;
      } 
      return this.fansClubBuilder_;
    }
    
    public String getSpecialId() {
      Object ref = this.specialId_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.specialId_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getSpecialIdBytes() {
      Object ref = this.specialId_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.specialId_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setSpecialId(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.specialId_ = value;
      this.bitField0_ |= 0x1000000;
      onChanged();
      return this;
    }
    
    public Builder clearSpecialId() {
      this.specialId_ = User.getDefaultInstance().getSpecialId();
      this.bitField0_ &= 0xFEFFFFFF;
      onChanged();
      return this;
    }
    
    public Builder setSpecialIdBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      User.checkByteStringIsUtf8(value);
      this.specialId_ = value;
      this.bitField0_ |= 0x1000000;
      onChanged();
      return this;
    }
    
    public boolean hasAvatarBorder() {
      return ((this.bitField0_ & 0x2000000) != 0);
    }
    
    public Image getAvatarBorder() {
      if (this.avatarBorderBuilder_ == null)
        return (this.avatarBorder_ == null) ? Image.getDefaultInstance() : this.avatarBorder_; 
      return (Image)this.avatarBorderBuilder_.getMessage();
    }
    
    public Builder setAvatarBorder(Image value) {
      if (this.avatarBorderBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        this.avatarBorder_ = value;
      } else {
        this.avatarBorderBuilder_.setMessage((AbstractMessage)value);
      } 
      this.bitField0_ |= 0x2000000;
      onChanged();
      return this;
    }
    
    public Builder setAvatarBorder(Image.Builder builderForValue) {
      if (this.avatarBorderBuilder_ == null) {
        this.avatarBorder_ = builderForValue.build();
      } else {
        this.avatarBorderBuilder_.setMessage((AbstractMessage)builderForValue.build());
      } 
      this.bitField0_ |= 0x2000000;
      onChanged();
      return this;
    }
    
    public Builder mergeAvatarBorder(Image value) {
      if (this.avatarBorderBuilder_ == null) {
        if ((this.bitField0_ & 0x2000000) != 0 && this.avatarBorder_ != null && this.avatarBorder_ != Image.getDefaultInstance()) {
          getAvatarBorderBuilder().mergeFrom(value);
        } else {
          this.avatarBorder_ = value;
        } 
      } else {
        this.avatarBorderBuilder_.mergeFrom((AbstractMessage)value);
      } 
      if (this.avatarBorder_ != null) {
        this.bitField0_ |= 0x2000000;
        onChanged();
      } 
      return this;
    }
    
    public Builder clearAvatarBorder() {
      this.bitField0_ &= 0xFDFFFFFF;
      this.avatarBorder_ = null;
      if (this.avatarBorderBuilder_ != null) {
        this.avatarBorderBuilder_.dispose();
        this.avatarBorderBuilder_ = null;
      } 
      onChanged();
      return this;
    }
    
    public Image.Builder getAvatarBorderBuilder() {
      this.bitField0_ |= 0x2000000;
      onChanged();
      return (Image.Builder)getAvatarBorderFieldBuilder().getBuilder();
    }
    
    public ImageOrBuilder getAvatarBorderOrBuilder() {
      if (this.avatarBorderBuilder_ != null)
        return (ImageOrBuilder)this.avatarBorderBuilder_.getMessageOrBuilder(); 
      return (this.avatarBorder_ == null) ? Image.getDefaultInstance() : this.avatarBorder_;
    }
    
    private SingleFieldBuilderV3<Image, Image.Builder, ImageOrBuilder> getAvatarBorderFieldBuilder() {
      if (this.avatarBorderBuilder_ == null) {
        this.avatarBorderBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getAvatarBorder(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.avatarBorder_ = null;
      } 
      return this.avatarBorderBuilder_;
    }
    
    public boolean hasMedal() {
      return ((this.bitField0_ & 0x4000000) != 0);
    }
    
    public Image getMedal() {
      if (this.medalBuilder_ == null)
        return (this.medal_ == null) ? Image.getDefaultInstance() : this.medal_; 
      return (Image)this.medalBuilder_.getMessage();
    }
    
    public Builder setMedal(Image value) {
      if (this.medalBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        this.medal_ = value;
      } else {
        this.medalBuilder_.setMessage((AbstractMessage)value);
      } 
      this.bitField0_ |= 0x4000000;
      onChanged();
      return this;
    }
    
    public Builder setMedal(Image.Builder builderForValue) {
      if (this.medalBuilder_ == null) {
        this.medal_ = builderForValue.build();
      } else {
        this.medalBuilder_.setMessage((AbstractMessage)builderForValue.build());
      } 
      this.bitField0_ |= 0x4000000;
      onChanged();
      return this;
    }
    
    public Builder mergeMedal(Image value) {
      if (this.medalBuilder_ == null) {
        if ((this.bitField0_ & 0x4000000) != 0 && this.medal_ != null && this.medal_ != Image.getDefaultInstance()) {
          getMedalBuilder().mergeFrom(value);
        } else {
          this.medal_ = value;
        } 
      } else {
        this.medalBuilder_.mergeFrom((AbstractMessage)value);
      } 
      if (this.medal_ != null) {
        this.bitField0_ |= 0x4000000;
        onChanged();
      } 
      return this;
    }
    
    public Builder clearMedal() {
      this.bitField0_ &= 0xFBFFFFFF;
      this.medal_ = null;
      if (this.medalBuilder_ != null) {
        this.medalBuilder_.dispose();
        this.medalBuilder_ = null;
      } 
      onChanged();
      return this;
    }
    
    public Image.Builder getMedalBuilder() {
      this.bitField0_ |= 0x4000000;
      onChanged();
      return (Image.Builder)getMedalFieldBuilder().getBuilder();
    }
    
    public ImageOrBuilder getMedalOrBuilder() {
      if (this.medalBuilder_ != null)
        return (ImageOrBuilder)this.medalBuilder_.getMessageOrBuilder(); 
      return (this.medal_ == null) ? Image.getDefaultInstance() : this.medal_;
    }
    
    private SingleFieldBuilderV3<Image, Image.Builder, ImageOrBuilder> getMedalFieldBuilder() {
      if (this.medalBuilder_ == null) {
        this.medalBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getMedal(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.medal_ = null;
      } 
      return this.medalBuilder_;
    }
    
    private void ensureRealTimeIconsListIsMutable() {
      if ((this.bitField0_ & 0x8000000) == 0) {
        this.realTimeIconsList_ = new ArrayList<>(this.realTimeIconsList_);
        this.bitField0_ |= 0x8000000;
      } 
    }
    
    public List<Image> getRealTimeIconsListList() {
      if (this.realTimeIconsListBuilder_ == null)
        return Collections.unmodifiableList(this.realTimeIconsList_); 
      return this.realTimeIconsListBuilder_.getMessageList();
    }
    
    public int getRealTimeIconsListCount() {
      if (this.realTimeIconsListBuilder_ == null)
        return this.realTimeIconsList_.size(); 
      return this.realTimeIconsListBuilder_.getCount();
    }
    
    public Image getRealTimeIconsList(int index) {
      if (this.realTimeIconsListBuilder_ == null)
        return this.realTimeIconsList_.get(index); 
      return (Image)this.realTimeIconsListBuilder_.getMessage(index);
    }
    
    public Builder setRealTimeIconsList(int index, Image value) {
      if (this.realTimeIconsListBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        ensureRealTimeIconsListIsMutable();
        this.realTimeIconsList_.set(index, value);
        onChanged();
      } else {
        this.realTimeIconsListBuilder_.setMessage(index, (AbstractMessage)value);
      } 
      return this;
    }
    
    public Builder setRealTimeIconsList(int index, Image.Builder builderForValue) {
      if (this.realTimeIconsListBuilder_ == null) {
        ensureRealTimeIconsListIsMutable();
        this.realTimeIconsList_.set(index, builderForValue.build());
        onChanged();
      } else {
        this.realTimeIconsListBuilder_.setMessage(index, (AbstractMessage)builderForValue.build());
      } 
      return this;
    }
    
    public Builder addRealTimeIconsList(Image value) {
      if (this.realTimeIconsListBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        ensureRealTimeIconsListIsMutable();
        this.realTimeIconsList_.add(value);
        onChanged();
      } else {
        this.realTimeIconsListBuilder_.addMessage((AbstractMessage)value);
      } 
      return this;
    }
    
    public Builder addRealTimeIconsList(int index, Image value) {
      if (this.realTimeIconsListBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        ensureRealTimeIconsListIsMutable();
        this.realTimeIconsList_.add(index, value);
        onChanged();
      } else {
        this.realTimeIconsListBuilder_.addMessage(index, (AbstractMessage)value);
      } 
      return this;
    }
    
    public Builder addRealTimeIconsList(Image.Builder builderForValue) {
      if (this.realTimeIconsListBuilder_ == null) {
        ensureRealTimeIconsListIsMutable();
        this.realTimeIconsList_.add(builderForValue.build());
        onChanged();
      } else {
        this.realTimeIconsListBuilder_.addMessage((AbstractMessage)builderForValue.build());
      } 
      return this;
    }
    
    public Builder addRealTimeIconsList(int index, Image.Builder builderForValue) {
      if (this.realTimeIconsListBuilder_ == null) {
        ensureRealTimeIconsListIsMutable();
        this.realTimeIconsList_.add(index, builderForValue.build());
        onChanged();
      } else {
        this.realTimeIconsListBuilder_.addMessage(index, (AbstractMessage)builderForValue.build());
      } 
      return this;
    }
    
    public Builder addAllRealTimeIconsList(Iterable<? extends Image> values) {
      if (this.realTimeIconsListBuilder_ == null) {
        ensureRealTimeIconsListIsMutable();
        AbstractMessageLite.Builder.addAll(values, this.realTimeIconsList_);
        onChanged();
      } else {
        this.realTimeIconsListBuilder_.addAllMessages(values);
      } 
      return this;
    }
    
    public Builder clearRealTimeIconsList() {
      if (this.realTimeIconsListBuilder_ == null) {
        this.realTimeIconsList_ = Collections.emptyList();
        this.bitField0_ &= 0xF7FFFFFF;
        onChanged();
      } else {
        this.realTimeIconsListBuilder_.clear();
      } 
      return this;
    }
    
    public Builder removeRealTimeIconsList(int index) {
      if (this.realTimeIconsListBuilder_ == null) {
        ensureRealTimeIconsListIsMutable();
        this.realTimeIconsList_.remove(index);
        onChanged();
      } else {
        this.realTimeIconsListBuilder_.remove(index);
      } 
      return this;
    }
    
    public Image.Builder getRealTimeIconsListBuilder(int index) {
      return (Image.Builder)getRealTimeIconsListFieldBuilder().getBuilder(index);
    }
    
    public ImageOrBuilder getRealTimeIconsListOrBuilder(int index) {
      if (this.realTimeIconsListBuilder_ == null)
        return this.realTimeIconsList_.get(index); 
      return (ImageOrBuilder)this.realTimeIconsListBuilder_.getMessageOrBuilder(index);
    }
    
    public List<? extends ImageOrBuilder> getRealTimeIconsListOrBuilderList() {
      if (this.realTimeIconsListBuilder_ != null)
        return this.realTimeIconsListBuilder_.getMessageOrBuilderList(); 
      return Collections.unmodifiableList((List)this.realTimeIconsList_);
    }
    
    public Image.Builder addRealTimeIconsListBuilder() {
      return (Image.Builder)getRealTimeIconsListFieldBuilder().addBuilder((AbstractMessage)Image.getDefaultInstance());
    }
    
    public Image.Builder addRealTimeIconsListBuilder(int index) {
      return (Image.Builder)getRealTimeIconsListFieldBuilder().addBuilder(index, (AbstractMessage)Image.getDefaultInstance());
    }
    
    public List<Image.Builder> getRealTimeIconsListBuilderList() {
      return getRealTimeIconsListFieldBuilder().getBuilderList();
    }
    
    private RepeatedFieldBuilderV3<Image, Image.Builder, ImageOrBuilder> getRealTimeIconsListFieldBuilder() {
      if (this.realTimeIconsListBuilder_ == null) {
        this.realTimeIconsListBuilder_ = new RepeatedFieldBuilderV3(this.realTimeIconsList_, ((this.bitField0_ & 0x8000000) != 0), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.realTimeIconsList_ = null;
      } 
      return this.realTimeIconsListBuilder_;
    }
    
    public String getDisplayId() {
      Object ref = this.displayId_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.displayId_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getDisplayIdBytes() {
      Object ref = this.displayId_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.displayId_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setDisplayId(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.displayId_ = value;
      this.bitField0_ |= 0x10000000;
      onChanged();
      return this;
    }
    
    public Builder clearDisplayId() {
      this.displayId_ = User.getDefaultInstance().getDisplayId();
      this.bitField0_ &= 0xEFFFFFFF;
      onChanged();
      return this;
    }
    
    public Builder setDisplayIdBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      User.checkByteStringIsUtf8(value);
      this.displayId_ = value;
      this.bitField0_ |= 0x10000000;
      onChanged();
      return this;
    }
    
    public String getSecUid() {
      Object ref = this.secUid_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.secUid_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getSecUidBytes() {
      Object ref = this.secUid_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.secUid_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setSecUid(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.secUid_ = value;
      this.bitField0_ |= 0x20000000;
      onChanged();
      return this;
    }
    
    public Builder clearSecUid() {
      this.secUid_ = User.getDefaultInstance().getSecUid();
      this.bitField0_ &= 0xDFFFFFFF;
      onChanged();
      return this;
    }
    
    public Builder setSecUidBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      User.checkByteStringIsUtf8(value);
      this.secUid_ = value;
      this.bitField0_ |= 0x20000000;
      onChanged();
      return this;
    }
    
    public long getFanTicketCount() {
      return this.fanTicketCount_;
    }
    
    public Builder setFanTicketCount(long value) {
      this.fanTicketCount_ = value;
      this.bitField0_ |= 0x40000000;
      onChanged();
      return this;
    }
    
    public Builder clearFanTicketCount() {
      this.bitField0_ &= 0xBFFFFFFF;
      this.fanTicketCount_ = 0L;
      onChanged();
      return this;
    }
    
    public String getIdStr() {
      Object ref = this.idStr_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.idStr_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getIdStrBytes() {
      Object ref = this.idStr_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.idStr_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setIdStr(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.idStr_ = value;
      this.bitField0_ |= Integer.MIN_VALUE;
      onChanged();
      return this;
    }
    
    public Builder clearIdStr() {
      this.idStr_ = User.getDefaultInstance().getIdStr();
      this.bitField0_ &= Integer.MAX_VALUE;
      onChanged();
      return this;
    }
    
    public Builder setIdStrBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      User.checkByteStringIsUtf8(value);
      this.idStr_ = value;
      this.bitField0_ |= Integer.MIN_VALUE;
      onChanged();
      return this;
    }
    
    public int getAgeRange() {
      return this.ageRange_;
    }
    
    public Builder setAgeRange(int value) {
      this.ageRange_ = value;
      this.bitField1_ |= 0x1;
      onChanged();
      return this;
    }
    
    public Builder clearAgeRange() {
      this.bitField1_ &= 0xFFFFFFFE;
      this.ageRange_ = 0;
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
  
  private static final User DEFAULT_INSTANCE = new User();
  
  public static User getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }
  
  private static final Parser<User> PARSER = (Parser<User>)new AbstractParser<User>() {
      public User parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        User.Builder builder = User.newBuilder();
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
  
  public static Parser<User> parser() {
    return PARSER;
  }
  
  public Parser<User> getParserForType() {
    return PARSER;
  }
  
  public User getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }
}
