package tech.ordinaryroad.live.chat.client.servers.netty.client.handler;

import java.util.List;
import tech.ordinaryroad.live.chat.client.commons.base.listener.IBaseMsgListener;
import tech.ordinaryroad.live.chat.client.commons.base.msg.IMsg;
import tech.ordinaryroad.live.chat.client.servers.netty.client.base.BaseNettyClient;
import tech.ordinaryroad.live.chat.client.servers.netty.client.config.BaseNettyClientConfig;
import tech.ordinaryroad.live.chat.client.servers.netty.handler.base.BaseBinaryFrameHandler;

public abstract class BaseNettyClientBinaryFrameHandler<Client extends BaseNettyClient<?, ?, ?, ?, ?, ?>, BinaryFrameHandler extends BaseBinaryFrameHandler<BinaryFrameHandler, CmdEnum, Msg, MsgListener>, CmdEnum extends Enum<CmdEnum>, Msg extends IMsg, MsgListener extends IBaseMsgListener<BinaryFrameHandler, CmdEnum>> extends BaseBinaryFrameHandler<BinaryFrameHandler, CmdEnum, Msg, MsgListener> {
  protected final Client client;
  
  public Client getClient() {
    return this.client;
  }
  
  public BaseNettyClientBinaryFrameHandler(List<MsgListener> msgListeners, Client client, long roomId) {
    super(msgListeners, Long.valueOf(roomId));
    this.client = client;
  }
  
  public BaseNettyClientBinaryFrameHandler(List<MsgListener> msgListeners, Client client) {
    super(msgListeners, ((BaseNettyClientConfig)client.getConfig()).getRoomId());
    this.client = client;
  }
  
  public BaseNettyClientBinaryFrameHandler(List<MsgListener> msgListeners, long roomId) {
    super(msgListeners, Long.valueOf(roomId));
    this.client = null;
  }
}
