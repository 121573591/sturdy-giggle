package tech.ordinaryroad.live.chat.client.websocket.netty.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import java.util.List;
import tech.ordinaryroad.live.chat.client.servers.netty.client.handler.BinaryWebSocketFrameToMessageCodec;
import tech.ordinaryroad.live.chat.client.websocket.msg.WebSocketMsg;

public class WebSocketCodecHandler extends BinaryWebSocketFrameToMessageCodec<WebSocketMsg> {
  protected void encode(ChannelHandlerContext ctx, WebSocketMsg msg, List<Object> out) throws Exception {
    out.add(msg);
  }
  
  protected void decode(ChannelHandlerContext ctx, BinaryWebSocketFrame msg, List<Object> out) throws Exception {
    out.add(new WebSocketMsg(msg.content().copy()));
  }
}
