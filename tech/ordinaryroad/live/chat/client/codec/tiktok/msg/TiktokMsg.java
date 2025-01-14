package tech.ordinaryroad.live.chat.client.codec.tiktok.msg;

import tech.ordinaryroad.live.chat.client.codec.tiktok.msg.base.ITiktokMsg;
import tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.PushFrame;

public class TiktokMsg implements ITiktokMsg {
  private PushFrame msg;
  
  public void setMsg(PushFrame msg) {
    this.msg = msg;
  }
  
  public TiktokMsg(PushFrame msg) {
    this.msg = msg;
  }
  
  public TiktokMsg() {}
  
  public PushFrame getMsg() {
    return this.msg;
  }
}
