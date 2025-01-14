package tech.ordinaryroad.live.chat.client.codec.tiktok.msg;

import tech.ordinaryroad.live.chat.client.codec.tiktok.constant.TiktokCmdEnum;
import tech.ordinaryroad.live.chat.client.codec.tiktok.msg.base.ITiktokCmdMsg;
import tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.Message;

public class TiktokCmdMsg implements ITiktokCmdMsg {
  private Message msg;
  
  public void setMsg(Message msg) {
    this.msg = msg;
  }
  
  public TiktokCmdMsg(Message msg) {
    this.msg = msg;
  }
  
  public TiktokCmdMsg() {}
  
  public Message getMsg() {
    return this.msg;
  }
  
  public String getCmd() {
    if (this.msg == null)
      return null; 
    return this.msg.getMethod();
  }
  
  public void setCmd(String cmd) {}
  
  public TiktokCmdEnum getCmdEnum() {
    if (this.msg == null)
      return null; 
    return TiktokCmdEnum.getByName(this.msg.getMethod());
  }
}
