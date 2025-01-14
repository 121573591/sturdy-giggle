package tech.ordinaryroad.live.chat.client.codec.kuaishou.msg;

import tech.ordinaryroad.live.chat.client.codec.kuaishou.msg.base.IKuaishouCmdMsg;
import tech.ordinaryroad.live.chat.client.codec.kuaishou.protobuf.PayloadTypeOuterClass;
import tech.ordinaryroad.live.chat.client.codec.kuaishou.protobuf.SocketMessageOuterClass;

public class KuaishouCmdMsg implements IKuaishouCmdMsg {
  private SocketMessageOuterClass.SocketMessage msg;
  
  public void setMsg(SocketMessageOuterClass.SocketMessage msg) {
    this.msg = msg;
  }
  
  public KuaishouCmdMsg(SocketMessageOuterClass.SocketMessage msg) {
    this.msg = msg;
  }
  
  public KuaishouCmdMsg() {}
  
  public SocketMessageOuterClass.SocketMessage getMsg() {
    return this.msg;
  }
  
  public String getCmd() {
    if (this.msg == null)
      return null; 
    return this.msg.getPayloadType().name();
  }
  
  public void setCmd(String cmd) {}
  
  public PayloadTypeOuterClass.PayloadType getCmdEnum() {
    if (this.msg == null)
      return null; 
    return this.msg.getPayloadType();
  }
}
