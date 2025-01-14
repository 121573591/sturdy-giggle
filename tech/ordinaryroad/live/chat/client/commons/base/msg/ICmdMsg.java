package tech.ordinaryroad.live.chat.client.commons.base.msg;

public interface ICmdMsg<CmdEnum extends Enum<CmdEnum>> extends IMsg {
  String getCmd();
  
  void setCmd(String paramString);
  
  CmdEnum getCmdEnum();
}
