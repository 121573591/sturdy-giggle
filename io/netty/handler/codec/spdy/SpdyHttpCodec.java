package io.netty.handler.codec.spdy;

import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.ChannelOutboundHandler;
import io.netty.channel.CombinedChannelDuplexHandler;
import io.netty.handler.codec.http.FullHttpMessage;
import io.netty.handler.codec.http.HttpHeadersFactory;
import java.util.HashMap;

public final class SpdyHttpCodec extends CombinedChannelDuplexHandler<SpdyHttpDecoder, SpdyHttpEncoder> {
  public SpdyHttpCodec(SpdyVersion version, int maxContentLength) {
    super((ChannelInboundHandler)new SpdyHttpDecoder(version, maxContentLength), (ChannelOutboundHandler)new SpdyHttpEncoder(version));
  }
  
  @Deprecated
  public SpdyHttpCodec(SpdyVersion version, int maxContentLength, boolean validateHttpHeaders) {
    super((ChannelInboundHandler)new SpdyHttpDecoder(version, maxContentLength, validateHttpHeaders), (ChannelOutboundHandler)new SpdyHttpEncoder(version));
  }
  
  public SpdyHttpCodec(SpdyVersion version, int maxContentLength, HttpHeadersFactory headersFactory, HttpHeadersFactory trailersFactory) {
    super((ChannelInboundHandler)new SpdyHttpDecoder(version, maxContentLength, new HashMap<Integer, FullHttpMessage>(), headersFactory, trailersFactory), (ChannelOutboundHandler)new SpdyHttpEncoder(version));
  }
}
