package tech.ordinaryroad.live.chat.client.codec.douyin.msg;

import tech.ordinaryroad.live.chat.client.codec.douyin.msg.base.IDouyinMsg;
import tech.ordinaryroad.live.chat.client.codec.douyin.protobuf.ControlMessage;
import tech.ordinaryroad.live.chat.client.commons.base.constant.LiveStatusAction;
import tech.ordinaryroad.live.chat.client.commons.base.msg.ILiveStatusChangeMsg;

public class DouyinControlMsg implements IDouyinMsg, ILiveStatusChangeMsg {
  private ControlMessage msg;
  
  public void setMsg(ControlMessage msg) {
    this.msg = msg;
  }
  
  public DouyinControlMsg(ControlMessage msg) {
    this.msg = msg;
  }
  
  public DouyinControlMsg() {}
  
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
