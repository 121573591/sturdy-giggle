package tech.ordinaryroad.live.chat.client.servers.netty.handler.base;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import java.util.List;
import java.util.function.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.ordinaryroad.live.chat.client.commons.base.listener.IBaseMsgListener;
import tech.ordinaryroad.live.chat.client.commons.base.msg.ICmdMsg;
import tech.ordinaryroad.live.chat.client.commons.base.msg.IMsg;

public abstract class BaseBinaryFrameHandler<T extends BaseBinaryFrameHandler<?, ?, ?, ?>, CmdEnum extends Enum<CmdEnum>, Msg extends IMsg, MsgListener extends IBaseMsgListener<T, CmdEnum>> extends SimpleChannelInboundHandler<Msg> implements IBaseMsgListener<T, CmdEnum> {
  private static final Logger log = LoggerFactory.getLogger(BaseBinaryFrameHandler.class);
  
  private final Object roomId;
  
  protected final List<MsgListener> msgListeners;
  
  public Object getRoomId() {
    return this.roomId;
  }
  
  public BaseBinaryFrameHandler(List<MsgListener> msgListeners, Object roomId) {
    this.msgListeners = msgListeners;
    this.roomId = roomId;
    if ((this.msgListeners == null || this.msgListeners.isEmpty()) && 
      log.isDebugEnabled())
      log.debug("listener not set"); 
  }
  
  protected void channelRead0(ChannelHandlerContext ctx, Msg msg) {
    onMsg((T)this, (IMsg)msg);
    if (msg instanceof ICmdMsg) {
      ICmdMsg<?> cmdMsg = (ICmdMsg)msg;
      Enum<?> cmdEnum = cmdMsg.getCmdEnum();
      if (cmdEnum == null) {
        onUnknownCmd((T)this, cmdMsg.getCmd(), (IMsg)cmdMsg);
      } else {
        onCmdMsg((T)this, (CmdEnum)cmdEnum, (ICmdMsg)cmdMsg);
      } 
    } 
  }
  
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    if (cause.getCause() instanceof com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException) {
      log.error("缺少字段：{}", cause.getMessage());
    } else {
      super.exceptionCaught(ctx, cause);
    } 
  }
  
  public void onMsg(T t, IMsg msg) {
    super.onMsg(t, msg);
    iteratorMsgListeners(msgListener -> msgListener.onMsg(t, msg));
  }
  
  public void onCmdMsg(T t, CmdEnum cmd, ICmdMsg<CmdEnum> cmdMsg) {
    super.onCmdMsg(t, (Enum)cmd, cmdMsg);
    iteratorMsgListeners(msgListener -> msgListener.onCmdMsg(t, cmd, cmdMsg));
  }
  
  public void onUnknownCmd(T t, String cmdString, IMsg msg) {
    super.onUnknownCmd(t, cmdString, msg);
    iteratorMsgListeners(msgListener -> msgListener.onUnknownCmd(t, cmdString, msg));
  }
  
  public void iteratorMsgListeners(Consumer<MsgListener> consumer) {
    if (this.msgListeners.isEmpty())
      return; 
    for (int i = 0; i < this.msgListeners.size(); i++)
      consumer.accept(this.msgListeners.get(i)); 
  }
  
  public String getRoomIdAsString() {
    if (this.roomId == null)
      return ""; 
    return this.roomId.toString();
  }
  
  public long getRoomIdAsLong() {
    String roomIdAsString = getRoomIdAsString();
    if (roomIdAsString.trim().isEmpty())
      return 0L; 
    return Long.parseLong(roomIdAsString);
  }
}
