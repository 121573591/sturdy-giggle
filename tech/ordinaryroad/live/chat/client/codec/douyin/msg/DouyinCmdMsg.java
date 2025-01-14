package tech.ordinaryroad.live.chat.client.codec.douyin.msg;

import tech.ordinaryroad.live.chat.client.codec.douyin.constant.DouyinCmdEnum;
import tech.ordinaryroad.live.chat.client.codec.douyin.msg.base.IDouyinCmdMsg;
import tech.ordinaryroad.live.chat.client.codec.douyin.protobuf.Message;

public class DouyinCmdMsg implements IDouyinCmdMsg {
  private Message msg;
  
  public void setMsg(Message msg) {
    this.msg = msg;
  }
  
  public DouyinCmdMsg(Message msg) {
    this.msg = msg;
  }
  
  public DouyinCmdMsg() {}
  
  public Message getMsg() {
    return this.msg;
  }
  
  public String getCmd() {
    if (this.msg == null)
      return null; 
    return this.msg.getMethod();
  }
  
  public void setCmd(String cmd) {}
  
  public DouyinCmdEnum getCmdEnum() {
    if (this.msg == null)
      return null; 
    return DouyinCmdEnum.getByName(this.msg.getMethod());
  }
}
