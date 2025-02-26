package io.netty.handler.codec.http;

import io.netty.buffer.ByteBuf;

public interface FullHttpRequest extends HttpRequest, FullHttpMessage {
  FullHttpRequest copy();
  
  FullHttpRequest duplicate();
  
  FullHttpRequest retainedDuplicate();
  
  FullHttpRequest replace(ByteBuf paramByteBuf);
  
  FullHttpRequest retain(int paramInt);
  
  FullHttpRequest retain();
  
  FullHttpRequest touch();
  
  FullHttpRequest touch(Object paramObject);
  
  FullHttpRequest setProtocolVersion(HttpVersion paramHttpVersion);
  
  FullHttpRequest setMethod(HttpMethod paramHttpMethod);
  
  FullHttpRequest setUri(String paramString);
}
