package tech.ordinaryroad.live.chat.client.tiktok.netty.handler;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.ZipUtil;
import com.google.protobuf.ByteString;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.ordinaryroad.live.chat.client.codec.tiktok.constant.TiktokPayloadTypeEnum;
import tech.ordinaryroad.live.chat.client.codec.tiktok.msg.TiktokMsg;
import tech.ordinaryroad.live.chat.client.codec.tiktok.msg.base.ITiktokMsg;
import tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.HeadersList;
import tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.PushFrame;
import tech.ordinaryroad.live.chat.client.codec.tiktok.protobuf.Response;
import tech.ordinaryroad.live.chat.client.commons.base.exception.BaseException;
import tech.ordinaryroad.live.chat.client.servers.netty.client.handler.BinaryWebSocketFrameToMessageCodec;

public class TiktokCodecHandler extends BinaryWebSocketFrameToMessageCodec<ITiktokMsg> {
  private static final Logger log = LoggerFactory.getLogger(TiktokCodecHandler.class);
  
  protected void encode(ChannelHandlerContext ctx, ITiktokMsg msg, List<Object> out) throws Exception {
    if (msg instanceof TiktokMsg) {
      out.add(new BinaryWebSocketFrame(ctx.alloc().buffer().writeBytes(((TiktokMsg)msg).getMsg().toByteArray())));
    } else {
      throw new BaseException("暂不支持" + msg.getClass());
    } 
  }
  
  protected void decode(ChannelHandlerContext ctx, BinaryWebSocketFrame msg, List<Object> out) throws Exception {
    byte[] bytes;
    Response response;
    PushFrame pushFrame = PushFrame.parseFrom(msg.content().nioBuffer());
    String compressType = null;
    if (CollUtil.isNotEmpty(pushFrame.getHeadersListList()))
      for (HeadersList headersList : pushFrame.getHeadersListList()) {
        if ("compress_type".equals(headersList.getKey()))
          compressType = headersList.getValue(); 
      }  
    if (StrUtil.isBlank(compressType) || "none".equals(compressType)) {
      bytes = pushFrame.getPayload().toByteArray();
    } else if ("gzip".equalsIgnoreCase(compressType)) {
      ByteString payload = pushFrame.getPayload();
      bytes = ZipUtil.unGzip(payload.newInput());
    } else {
      if (log.isWarnEnabled())
        log.warn("暂不支持的压缩方式: {}", compressType); 
      return;
    } 
    String payloadType = pushFrame.getPayloadType();
    TiktokPayloadTypeEnum payloadTypeEnum = TiktokPayloadTypeEnum.getByCode(payloadType);
    if (payloadTypeEnum == null) {
      if (log.isDebugEnabled())
        log.debug("暂不支持的payloadType: {}", payloadType); 
      return;
    } 
    switch (payloadTypeEnum) {
      case MSG:
        response = Response.parseFrom(bytes);
        if (response.getNeedAck()) {
          PushFrame ack = PushFrame.newBuilder().setLogId(pushFrame.getLogId()).setPayloadType(TiktokPayloadTypeEnum.ACK.getCode()).setPayload(response.getInternalExtBytes()).build();
          ctx.writeAndFlush(ack);
        } 
        out.addAll((Collection)response.getMessagesListList().stream().map(tech.ordinaryroad.live.chat.client.codec.tiktok.msg.TiktokCmdMsg::new).collect(Collectors.toList()));
        return;
    } 
  }
}
