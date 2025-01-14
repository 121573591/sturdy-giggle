package tech.ordinaryroad.live.chat.client.codec.kuaishou.msg;

import tech.ordinaryroad.live.chat.client.codec.kuaishou.api.KuaishouApis;
import tech.ordinaryroad.live.chat.client.codec.kuaishou.msg.base.IKuaishouMsg;
import tech.ordinaryroad.live.chat.client.codec.kuaishou.protobuf.WebLikeFeedOuterClass;
import tech.ordinaryroad.live.chat.client.commons.base.msg.ILikeMsg;

public class KuaishouLikeMsg implements IKuaishouMsg, ILikeMsg {
  private WebLikeFeedOuterClass.WebLikeFeed msg;
  
  public void setMsg(WebLikeFeedOuterClass.WebLikeFeed msg) {
    this.msg = msg;
  }
  
  public KuaishouLikeMsg(WebLikeFeedOuterClass.WebLikeFeed msg) {
    this.msg = msg;
  }
  
  public KuaishouLikeMsg() {}
  
  public WebLikeFeedOuterClass.WebLikeFeed getMsg() {
    return this.msg;
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
  
  public String toString() {
    return this.msg.toString();
  }
}
