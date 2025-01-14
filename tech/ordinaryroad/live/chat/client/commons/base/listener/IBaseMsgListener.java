package tech.ordinaryroad.live.chat.client.commons.base.listener;

import tech.ordinaryroad.live.chat.client.commons.base.msg.ICmdMsg;
import tech.ordinaryroad.live.chat.client.commons.base.msg.IMsg;

public interface IBaseMsgListener<T, CmdEnum extends Enum<CmdEnum>> {
  default void onMsg(T t, IMsg msg) {
    onMsg(msg);
  }
  
  default void onMsg(IMsg msg) {}
  
  default void onCmdMsg(T t, CmdEnum cmd, ICmdMsg<CmdEnum> cmdMsg) {
    onCmdMsg(cmd, cmdMsg);
  }
  
  default void onCmdMsg(CmdEnum cmd, ICmdMsg<CmdEnum> cmdMsg) {}
  
  default void onOtherCmdMsg(T t, CmdEnum cmd, ICmdMsg<CmdEnum> cmdMsg) {
    onOtherCmdMsg(cmd, cmdMsg);
  }
  
  default void onOtherCmdMsg(CmdEnum cmd, ICmdMsg<CmdEnum> cmdMsg) {}
  
  default void onUnknownCmd(T t, String cmdString, IMsg msg) {
    onUnknownCmd(cmdString, msg);
  }
  
  default void onUnknownCmd(String cmdString, IMsg msg) {}
}
