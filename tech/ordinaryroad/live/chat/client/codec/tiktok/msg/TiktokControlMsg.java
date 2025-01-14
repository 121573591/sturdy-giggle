package tech.ordinaryroad.live.chat.client.codec.tiktok.msg;

import tech.ordinaryroad.live.chat.client.codec.tiktok.msg.base.ITiktokMsg;
import tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.ControlMessage;
import tech.ordinaryroad.live.chat.client.commons.base.constant.LiveStatusAction;
import tech.ordinaryroad.live.chat.client.commons.base.msg.ILiveStatusChangeMsg;

public class TiktokControlMsg implements ITiktokMsg, ILiveStatusChangeMsg {
  private ControlMessage msg;
  
  public void setMsg(ControlMessage msg) {
    this.msg = msg;
  }
  
  public TiktokControlMsg(ControlMessage msg) {
    this.msg = msg;
  }
  
  public TiktokControlMsg() {}
  
  public ControlMessage getMsg() {
    return this.msg;
  }
  
  public LiveStatusAction getLiveStatusAction() {
    if (this.msg.getStatus() == 3)
      return LiveStatusAction.END; 
    return null;
  }
  
  public String toString() {
    return this.msg.toString();
  }
}
