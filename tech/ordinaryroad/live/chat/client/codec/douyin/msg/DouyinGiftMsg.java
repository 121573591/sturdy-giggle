package tech.ordinaryroad.live.chat.client.codec.douyin.msg;

import cn.hutool.core.collection.CollUtil;
import tech.ordinaryroad.live.chat.client.codec.douyin.msg.base.IDouyinMsg;
import tech.ordinaryroad.live.chat.client.codec.douyin.protobuf.GiftMessage;
import tech.ordinaryroad.live.chat.client.commons.base.msg.IGiftMsg;

public class DouyinGiftMsg implements IDouyinMsg, IGiftMsg {
  private GiftMessage msg;
  
  private int calculatedGiftCount;
  
  public void setMsg(GiftMessage msg) {
    this.msg = msg;
  }
  
  public void setCalculatedGiftCount(int calculatedGiftCount) {
    this.calculatedGiftCount = calculatedGiftCount;
  }
  
  public DouyinGiftMsg(GiftMessage msg, int calculatedGiftCount) {
    this.msg = msg;
    this.calculatedGiftCount = calculatedGiftCount;
  }
  
  public DouyinGiftMsg() {}
  
  public GiftMessage getMsg() {
    return this.msg;
  }
  
  public int getCalculatedGiftCount() {
    return this.calculatedGiftCount;
  }
  
  public DouyinGiftMsg(GiftMessage msg) {
    this.msg = msg;
  }
  
  public String getBadgeName() {
    return this.msg.getUser().getFansClub().getData().getClubName();
  }
  
  public byte getBadgeLevel() {
    return (byte)this.msg.getUser().getFansClub().getData().getLevel();
  }
  
  public String getUid() {
    return Long.toString(this.msg.getUser().getId());
  }
  
  public String getUsername() {
    return this.msg.getUser().getNickName();
  }
  
  public String getUserAvatar() {
    return (String)CollUtil.getFirst((Iterable)this.msg.getUser().getAvatarThumb().getUrlListListList());
  }
  
  public String getGiftName() {
    return this.msg.getGift().getName();
  }
  
  public String getGiftImg() {
    return (String)CollUtil.getFirst((Iterable)this.msg.getGift().getImage().getUrlListListList());
  }
  
  public String getGiftId() {
    return Long.toString(this.msg.getGiftId());
  }
  
  public int getGiftCount() {
    return this.calculatedGiftCount;
  }
  
  public int getGiftPrice() {
    return this.msg.getGift().getDiamondCount();
  }
  
  public String getReceiveUid() {
    return Long.toString(this.msg.getToUser().getId());
  }
  
  public String getReceiveUsername() {
    return this.msg.getToUser().getNickName();
  }
  
  public String toString() {
    return this.msg.toString();
  }
}
