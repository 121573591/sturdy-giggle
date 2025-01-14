package tech.ordinaryroad.live.chat.client.codec.douyin.msg;

import tech.ordinaryroad.live.chat.client.codec.douyin.msg.base.IDouyinMsg;
import tech.ordinaryroad.live.chat.client.codec.douyin.protobuf.PushFrame;

public class DouyinMsg implements IDouyinMsg {
  private PushFrame msg;
  
  public void setMsg(PushFrame msg) {
    this.msg = msg;
  }
  
  public DouyinMsg(PushFrame msg) {
    this.msg = msg;
  }
  
  public DouyinMsg() {}
  
  public PushFrame getMsg() {
    return this.msg;
  }
}
