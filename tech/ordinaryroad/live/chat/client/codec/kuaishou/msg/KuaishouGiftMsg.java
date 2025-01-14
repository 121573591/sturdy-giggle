package tech.ordinaryroad.live.chat.client.codec.kuaishou.msg;

import tech.ordinaryroad.live.chat.client.codec.kuaishou.api.KuaishouApis;
import tech.ordinaryroad.live.chat.client.codec.kuaishou.msg.base.IKuaishouMsg;
import tech.ordinaryroad.live.chat.client.codec.kuaishou.protobuf.WebGiftFeedOuterClass;
import tech.ordinaryroad.live.chat.client.commons.base.msg.IGiftMsg;

public class KuaishouGiftMsg implements IKuaishouMsg, IGiftMsg {
  private WebGiftFeedOuterClass.WebGiftFeed msg;
  
  private int calculatedGiftCount;
  
  public void setMsg(WebGiftFeedOuterClass.WebGiftFeed msg) {
    this.msg = msg;
  }
  
  public void setCalculatedGiftCount(int calculatedGiftCount) {
    this.calculatedGiftCount = calculatedGiftCount;
  }
  
  public KuaishouGiftMsg(WebGiftFeedOuterClass.WebGiftFeed msg, int calculatedGiftCount) {
    this.msg = msg;
    this.calculatedGiftCount = calculatedGiftCount;
  }
  
  public KuaishouGiftMsg() {}
  
  public WebGiftFeedOuterClass.WebGiftFeed getMsg() {
    return this.msg;
  }
  
  public int getCalculatedGiftCount() {
    return this.calculatedGiftCount;
  }
  
  public KuaishouGiftMsg(WebGiftFeedOuterClass.WebGiftFeed msg) {
    this.msg = msg;
  }
  
  public String getBadgeName() {
    return KuaishouApis.getBadgeName(this.msg.getLiveAudienceState());
  }
  
  public byte getBadgeLevel() {
    return KuaishouApis.getBadgeLevel(this.msg.getLiveAudienceState());
  }
  
  public String getUid() {
    return this.msg.getUser().getPrincipalId();
  }
  
  public String getUsername() {
    return this.msg.getUser().getUserName();
  }
  
  public String getUserAvatar() {
    return this.msg.getUser().getHeadUrl();
  }
  
  public String getGiftName() {
    return KuaishouApis.getGiftInfoById(getGiftId()).getGiftName();
  }
  
  public String getGiftImg() {
    return KuaishouApis.getGiftInfoById(getGiftId()).getGiftUrl();
  }
  
  public String getGiftId() {
    return Integer.toString(this.msg.getIntGiftId());
  }
  
  public int getGiftCount() {
    return this.calculatedGiftCount;
  }
  
  public int getGiftPrice() {
    return 0;
  }
  
  public String getReceiveUid() {
    return null;
  }
  
  public String getReceiveUsername() {
    return null;
  }
  
  public String toString() {
    return this.msg.toString();
  }
}
