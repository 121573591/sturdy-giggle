package tech.ordinaryroad.live.chat.client.douyin.netty.handler;

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
import tech.ordinaryroad.live.chat.client.codec.douyin.constant.DouyinPayloadTypeEnum;
import tech.ordinaryroad.live.chat.client.codec.douyin.msg.DouyinMsg;
import tech.ordinaryroad.live.chat.client.codec.douyin.msg.base.IDouyinMsg;
import tech.ordinaryroad.live.chat.client.codec.douyin.protobuf.HeadersList;
import tech.ordinaryroad.live.chat.client.codec.douyin.protobuf.PushFrame;
import tech.ordinaryroad.live.chat.client.codec.douyin.protobuf.Response;
import tech.ordinaryroad.live.chat.client.commons.base.exception.BaseException;
import tech.ordinaryroad.live.chat.client.servers.netty.client.handler.BinaryWebSocketFrameToMessageCodec;

public class DouyinCodecHandler extends BinaryWebSocketFrameToMessageCodec<IDouyinMsg> {
  private static final Logger log = LoggerFactory.getLogger(DouyinCodecHandler.class);
  
  protected void encode(ChannelHandlerContext ctx, IDouyinMsg msg, List<Object> out) throws Exception {
    if (msg instanceof DouyinMsg) {
      out.add(new BinaryWebSocketFrame(ctx.alloc().buffer().writeBytes(((DouyinMsg)msg).getMsg().toByteArray())));
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
    DouyinPayloadTypeEnum payloadTypeEnum = DouyinPayloadTypeEnum.getByCode(payloadType);
    if (payloadTypeEnum == null) {
      if (log.isDebugEnabled())
        log.debug("暂不支持的payloadType: {}", payloadType); 
      return;
    } 
    switch (payloadTypeEnum) {
      case MSG:
        response = Response.parseFrom(bytes);
        if (response.getNeedAck()) {
          PushFrame ack = PushFrame.newBuilder().setLogId(pushFrame.getLogId()).setPayloadType(DouyinPayloadTypeEnum.ACK.getCode()).setPayload(response.getInternalExtBytes()).build();
          ctx.writeAndFlush(ack);
        } 
        out.addAll((Collection)response.getMessagesListList().stream().map(tech.ordinaryroad.live.chat.client.codec.douyin.msg.DouyinCmdMsg::new).collect(Collectors.toList()));
        return;
    } 
  }
}
