package io.netty.handler.codec.http;

import io.netty.util.internal.StringUtil;
import java.util.Map;

final class HttpMessageUtil {
  static StringBuilder appendRequest(StringBuilder buf, HttpRequest req) {
    appendCommon(buf, req);
    appendInitialLine(buf, req);
    appendHeaders(buf, req.headers());
    removeLastNewLine(buf);
    return buf;
  }
  
  static StringBuilder appendResponse(StringBuilder buf, HttpResponse res) {
    appendCommon(buf, res);
    appendInitialLine(buf, res);
    appendHeaders(buf, res.headers());
    removeLastNewLine(buf);
    return buf;
  }
  
  private static void appendCommon(StringBuilder buf, HttpMessage msg) {
    buf.append(StringUtil.simpleClassName(msg));
    buf.append("(decodeResult: ");
    buf.append(msg.decoderResult());
    buf.append(", version: ");
    buf.append(msg.protocolVersion());
    buf.append(')');
    buf.append(StringUtil.NEWLINE);
  }
  
  static StringBuilder appendFullRequest(StringBuilder buf, FullHttpRequest req) {
    appendFullCommon(buf, req);
    appendInitialLine(buf, req);
    appendHeaders(buf, req.headers());
    appendHeaders(buf, req.trailingHeaders());
    removeLastNewLine(buf);
    return buf;
  }
  
  static StringBuilder appendFullResponse(StringBuilder buf, FullHttpResponse res) {
    appendFullCommon(buf, res);
    appendInitialLine(buf, res);
    appendHeaders(buf, res.headers());
    appendHeaders(buf, res.trailingHeaders());
    removeLastNewLine(buf);
    return buf;
  }
  
  private static void appendFullCommon(StringBuilder buf, FullHttpMessage msg) {
    buf.append(StringUtil.simpleClassName(msg));
    buf.append("(decodeResult: ");
    buf.append(msg.decoderResult());
    buf.append(", version: ");
    buf.append(msg.protocolVersion());
    buf.append(", content: ");
    buf.append(msg.content());
    buf.append(')');
    buf.append(StringUtil.NEWLINE);
  }
  
  private static void appendInitialLine(StringBuilder buf, HttpRequest req) {
    buf.append(req.method());
    buf.append(' ');
    buf.append(req.uri());
    buf.append(' ');
    buf.append(req.protocolVersion());
    buf.append(StringUtil.NEWLINE);
  }
  
  private static void appendInitialLine(StringBuilder buf, HttpResponse res) {
    buf.append(res.protocolVersion());
    buf.append(' ');
    buf.append(res.status());
    buf.append(StringUtil.NEWLINE);
  }
  
  private static void appendHeaders(StringBuilder buf, HttpHeaders headers) {
    for (Map.Entry<String, String> e : (Iterable<Map.Entry<String, String>>)headers) {
      buf.append(e.getKey());
      buf.append(": ");
      buf.append(e.getValue());
      buf.append(StringUtil.NEWLINE);
    } 
  }
  
  private static void removeLastNewLine(StringBuilder buf) {
    buf.setLength(buf.length() - StringUtil.NEWLINE.length());
  }
}
