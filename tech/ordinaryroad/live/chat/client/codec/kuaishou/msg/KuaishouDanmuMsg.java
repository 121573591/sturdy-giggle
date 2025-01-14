package tech.ordinaryroad.live.chat.client.codec.kuaishou.msg;

import tech.ordinaryroad.live.chat.client.codec.kuaishou.api.KuaishouApis;
import tech.ordinaryroad.live.chat.client.codec.kuaishou.msg.base.IKuaishouMsg;
import tech.ordinaryroad.live.chat.client.codec.kuaishou.protobuf.WebCommentFeedOuterClass;
import tech.ordinaryroad.live.chat.client.commons.base.msg.IDanmuMsg;

public class KuaishouDanmuMsg implements IKuaishouMsg, IDanmuMsg {
  private WebCommentFeedOuterClass.WebCommentFeed msg;
  
  public void setMsg(WebCommentFeedOuterClass.WebCommentFeed msg) {
    this.msg = msg;
  }
  
  public KuaishouDanmuMsg(WebCommentFeedOuterClass.WebCommentFeed msg) {
    this.msg = msg;
  }
  
  public KuaishouDanmuMsg() {}
  
  public WebCommentFeedOuterClass.WebCommentFeed getMsg() {
    return this.msg;
  }
  
  public String getBadgeName() {
    return KuaishouApis.getBadgeName(this.msg.getSenderState());
  }
  
  public byte getBadgeLevel() {
    return KuaishouApis.getBadgeLevel(this.msg.getSenderState());
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
  
  public String getContent() {
    return this.msg.getContent();
  }
  
  public String toString() {
    return this.msg.toString();
  }
}
