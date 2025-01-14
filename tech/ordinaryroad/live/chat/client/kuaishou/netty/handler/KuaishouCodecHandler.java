package tech.ordinaryroad.live.chat.client.kuaishou.netty.handler;

import cn.hutool.core.util.ZipUtil;
import com.google.protobuf.ByteString;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.ordinaryroad.live.chat.client.codec.kuaishou.msg.KuaishouCmdMsg;
import tech.ordinaryroad.live.chat.client.codec.kuaishou.msg.base.IKuaishouMsg;
import tech.ordinaryroad.live.chat.client.codec.kuaishou.protobuf.SocketMessageOuterClass;
import tech.ordinaryroad.live.chat.client.commons.base.exception.BaseException;
import tech.ordinaryroad.live.chat.client.servers.netty.client.handler.BinaryWebSocketFrameToMessageCodec;

public class KuaishouCodecHandler extends BinaryWebSocketFrameToMessageCodec<IKuaishouMsg> {
  private static final Logger log = LoggerFactory.getLogger(KuaishouCodecHandler.class);
  
  protected void encode(ChannelHandlerContext ctx, IKuaishouMsg msg, List<Object> out) throws Exception {
    if (msg instanceof KuaishouCmdMsg) {
      out.add(new BinaryWebSocketFrame(ctx.alloc().buffer().writeBytes(((KuaishouCmdMsg)msg).getMsg().toByteArray())));
    } else {
      throw new BaseException("暂不支持" + msg.getClass());
    } 
  }
  
  protected void decode(ChannelHandlerContext ctx, BinaryWebSocketFrame msg, List<Object> out) throws Exception {
    byte[] payload;
    ByteBuf content = msg.content();
    SocketMessageOuterClass.SocketMessage socketMessage = SocketMessageOuterClass.SocketMessage.parseFrom(content.nioBuffer());
    SocketMessageOuterClass.SocketMessage.CompressionType compressionType = socketMessage.getCompressionType();
    ByteString payloadByteString = socketMessage.getPayload();
    switch (compressionType) {
      case NONE:
        payload = payloadByteString.toByteArray();
        break;
      case GZIP:
        payload = ZipUtil.unGzip(payloadByteString.newInput());
        break;
      default:
        if (log.isWarnEnabled())
          log.warn("暂不支持的压缩方式 " + compressionType); 
        return;
    } 
    out.add(new KuaishouCmdMsg(socketMessage.toBuilder().setPayload(ByteString.copyFrom(payload)).build()));
  }
}
