package io.netty.handler.codec.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;

public class HttpResponseEncoder extends HttpObjectEncoder<HttpResponse> {
  public boolean acceptOutboundMessage(Object msg) throws Exception {
    Class<?> msgClass = msg.getClass();
    if (msgClass == DefaultFullHttpResponse.class || msgClass == DefaultHttpResponse.class)
      return true; 
    return (super.acceptOutboundMessage(msg) && !(msg instanceof HttpRequest));
  }
  
  protected void encodeInitialLine(ByteBuf buf, HttpResponse response) throws Exception {
    response.protocolVersion().encode(buf);
    buf.writeByte(32);
    response.status().encode(buf);
    ByteBufUtil.writeShortBE(buf, 3338);
  }
  
  protected void sanitizeHeadersBeforeEncode(HttpResponse msg, boolean isAlwaysEmpty) {
    if (isAlwaysEmpty) {
      HttpResponseStatus status = msg.status();
      if (status.codeClass() == HttpStatusClass.INFORMATIONAL || status
        .code() == HttpResponseStatus.NO_CONTENT.code()) {
        msg.headers().remove((CharSequence)HttpHeaderNames.CONTENT_LENGTH);
        msg.headers().remove((CharSequence)HttpHeaderNames.TRANSFER_ENCODING);
      } else if (status.code() == HttpResponseStatus.RESET_CONTENT.code()) {
        msg.headers().remove((CharSequence)HttpHeaderNames.TRANSFER_ENCODING);
        msg.headers().setInt((CharSequence)HttpHeaderNames.CONTENT_LENGTH, 0);
      } 
    } 
  }
  
  protected boolean isContentAlwaysEmpty(HttpResponse msg) {
    HttpResponseStatus status = msg.status();
    if (status.codeClass() == HttpStatusClass.INFORMATIONAL) {
      if (status.code() == HttpResponseStatus.SWITCHING_PROTOCOLS.code())
        return msg.headers().contains((CharSequence)HttpHeaderNames.SEC_WEBSOCKET_VERSION); 
      return true;
    } 
    return (status.code() == HttpResponseStatus.NO_CONTENT.code() || status
      .code() == HttpResponseStatus.NOT_MODIFIED.code() || status
      .code() == HttpResponseStatus.RESET_CONTENT.code());
  }
}
