package io.netty.handler.codec.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.channel.FileRegion;
import io.netty.handler.codec.EncoderException;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.Promise;
import io.netty.util.concurrent.PromiseCombiner;
import io.netty.util.internal.StringUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public abstract class HttpObjectEncoder<H extends HttpMessage> extends MessageToMessageEncoder<Object> {
  private static final int COPY_CONTENT_THRESHOLD = 128;
  
  static final int CRLF_SHORT = 3338;
  
  private static final int ZERO_CRLF_MEDIUM = 3149066;
  
  private static final byte[] ZERO_CRLF_CRLF = new byte[] { 48, 13, 10, 13, 10 };
  
  private static final ByteBuf CRLF_BUF = Unpooled.unreleasableBuffer(
      Unpooled.directBuffer(2).writeByte(13).writeByte(10)).asReadOnly();
  
  private static final ByteBuf ZERO_CRLF_CRLF_BUF = Unpooled.unreleasableBuffer(
      Unpooled.directBuffer(ZERO_CRLF_CRLF.length).writeBytes(ZERO_CRLF_CRLF)).asReadOnly();
  
  private static final float HEADERS_WEIGHT_NEW = 0.2F;
  
  private static final float HEADERS_WEIGHT_HISTORICAL = 0.8F;
  
  private static final float TRAILERS_WEIGHT_NEW = 0.2F;
  
  private static final float TRAILERS_WEIGHT_HISTORICAL = 0.8F;
  
  private static final int ST_INIT = 0;
  
  private static final int ST_CONTENT_NON_CHUNK = 1;
  
  private static final int ST_CONTENT_CHUNK = 2;
  
  private static final int ST_CONTENT_ALWAYS_EMPTY = 3;
  
  private int state = 0;
  
  private float headersEncodedSizeAccumulator = 256.0F;
  
  private float trailersEncodedSizeAccumulator = 256.0F;
  
  private final List<Object> out = new ArrayList();
  
  private static boolean checkContentState(int state) {
    return (state == 2 || state == 1 || state == 3);
  }
  
  public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
    try {
      if (acceptOutboundMessage(msg)) {
        encode(ctx, msg, this.out);
        if (this.out.isEmpty())
          throw new EncoderException(
              StringUtil.simpleClassName(this) + " must produce at least one message."); 
      } else {
        ctx.write(msg, promise);
      } 
    } catch (EncoderException e) {
      throw e;
    } catch (Throwable t) {
      throw new EncoderException(t);
    } finally {
      writeOutList(ctx, this.out, promise);
    } 
  }
  
  private static void writeOutList(ChannelHandlerContext ctx, List<Object> out, ChannelPromise promise) {
    int size = out.size();
    try {
      if (size == 1) {
        ctx.write(out.get(0), promise);
      } else if (size > 1) {
        if (promise == ctx.voidPromise()) {
          writeVoidPromise(ctx, out);
        } else {
          writePromiseCombiner(ctx, out, promise);
        } 
      } 
    } finally {
      out.clear();
    } 
  }
  
  private static void writeVoidPromise(ChannelHandlerContext ctx, List<Object> out) {
    ChannelPromise voidPromise = ctx.voidPromise();
    for (int i = 0; i < out.size(); i++)
      ctx.write(out.get(i), voidPromise); 
  }
  
  private static void writePromiseCombiner(ChannelHandlerContext ctx, List<Object> out, ChannelPromise promise) {
    PromiseCombiner combiner = new PromiseCombiner(ctx.executor());
    for (int i = 0; i < out.size(); i++)
      combiner.add((Future)ctx.write(out.get(i))); 
    combiner.finish((Promise)promise);
  }
  
  protected void encode(ChannelHandlerContext ctx, Object msg, List<Object> out) throws Exception {
    if (msg == Unpooled.EMPTY_BUFFER) {
      out.add(Unpooled.EMPTY_BUFFER);
      return;
    } 
    if (msg instanceof FullHttpMessage) {
      encodeFullHttpMessage(ctx, msg, out);
      return;
    } 
    if (msg instanceof HttpMessage) {
      HttpMessage httpMessage;
      try {
        httpMessage = (HttpMessage)msg;
      } catch (Exception rethrow) {
        ReferenceCountUtil.release(msg);
        throw rethrow;
      } 
      if (httpMessage instanceof LastHttpContent) {
        encodeHttpMessageLastContent(ctx, (H)httpMessage, out);
      } else if (httpMessage instanceof HttpContent) {
        encodeHttpMessageNotLastContent(ctx, (H)httpMessage, out);
      } else {
        encodeJustHttpMessage(ctx, (H)httpMessage, out);
      } 
    } else {
      encodeNotHttpMessageContentTypes(ctx, msg, out);
    } 
  }
  
  private void encodeJustHttpMessage(ChannelHandlerContext ctx, H m, List<Object> out) throws Exception {
    assert !(m instanceof HttpContent);
    try {
      if (this.state != 0)
        throwUnexpectedMessageTypeEx(m, this.state); 
      ByteBuf buf = encodeInitHttpMessage(ctx, m);
      assert checkContentState(this.state);
      out.add(buf);
    } finally {
      ReferenceCountUtil.release(m);
    } 
  }
  
  private void encodeByteBufHttpContent(int state, ChannelHandlerContext ctx, ByteBuf buf, ByteBuf content, HttpHeaders trailingHeaders, List<Object> out) {
    switch (state) {
      case 1:
        if (encodeContentNonChunk(out, buf, content))
          return; 
      case 3:
        out.add(buf);
        return;
      case 2:
        out.add(buf);
        encodeChunkedHttpContent(ctx, content, trailingHeaders, out);
        return;
    } 
    throw new Error();
  }
  
  private void encodeHttpMessageNotLastContent(ChannelHandlerContext ctx, H m, List<Object> out) throws Exception {
    assert m instanceof HttpContent;
    assert !(m instanceof LastHttpContent);
    HttpContent httpContent = (HttpContent)m;
    try {
      if (this.state != 0)
        throwUnexpectedMessageTypeEx(m, this.state); 
      ByteBuf buf = encodeInitHttpMessage(ctx, m);
      assert checkContentState(this.state);
      encodeByteBufHttpContent(this.state, ctx, buf, httpContent.content(), null, out);
    } finally {
      httpContent.release();
    } 
  }
  
  private void encodeHttpMessageLastContent(ChannelHandlerContext ctx, H m, List<Object> out) throws Exception {
    assert m instanceof LastHttpContent;
    LastHttpContent httpContent = (LastHttpContent)m;
    try {
      if (this.state != 0)
        throwUnexpectedMessageTypeEx(m, this.state); 
      ByteBuf buf = encodeInitHttpMessage(ctx, m);
      assert checkContentState(this.state);
      encodeByteBufHttpContent(this.state, ctx, buf, httpContent.content(), httpContent.trailingHeaders(), out);
      this.state = 0;
    } finally {
      httpContent.release();
    } 
  }
  
  private void encodeNotHttpMessageContentTypes(ChannelHandlerContext ctx, Object msg, List<Object> out) {
    assert !(msg instanceof HttpMessage);
    if (this.state == 0)
      try {
        if (msg instanceof ByteBuf && bypassEncoderIfEmpty((ByteBuf)msg, out))
          return; 
        throwUnexpectedMessageTypeEx(msg, 0);
      } finally {
        ReferenceCountUtil.release(msg);
      }  
    if (msg == LastHttpContent.EMPTY_LAST_CONTENT) {
      this.state = encodeEmptyLastHttpContent(this.state, out);
      return;
    } 
    if (msg instanceof LastHttpContent) {
      encodeLastHttpContent(ctx, (LastHttpContent)msg, out);
      return;
    } 
    if (msg instanceof HttpContent) {
      encodeHttpContent(ctx, (HttpContent)msg, out);
      return;
    } 
    if (msg instanceof ByteBuf) {
      encodeByteBufContent(ctx, (ByteBuf)msg, out);
      return;
    } 
    if (msg instanceof FileRegion) {
      encodeFileRegionContent(ctx, (FileRegion)msg, out);
      return;
    } 
    try {
      throwUnexpectedMessageTypeEx(msg, this.state);
    } finally {
      ReferenceCountUtil.release(msg);
    } 
  }
  
  private void encodeFullHttpMessage(ChannelHandlerContext ctx, Object o, List<Object> out) throws Exception {
    assert o instanceof FullHttpMessage;
    FullHttpMessage msg = (FullHttpMessage)o;
    try {
      if (this.state != 0)
        throwUnexpectedMessageTypeEx(o, this.state); 
      HttpMessage httpMessage = (HttpMessage)o;
      int state = isContentAlwaysEmpty((H)httpMessage) ? 3 : (HttpUtil.isTransferEncodingChunked(httpMessage) ? 2 : 1);
      ByteBuf content = msg.content();
      boolean accountForContentSize = (content.readableBytes() > 0 && state == 1 && content.readableBytes() <= Math.max(128, (int)this.headersEncodedSizeAccumulator / 8));
      int headersAndContentSize = (int)this.headersEncodedSizeAccumulator + (accountForContentSize ? content.readableBytes() : 0);
      ByteBuf buf = ctx.alloc().buffer(headersAndContentSize);
      encodeInitialLine(buf, (H)httpMessage);
      sanitizeHeadersBeforeEncode((H)httpMessage, (state == 3));
      encodeHeaders(httpMessage.headers(), buf);
      ByteBufUtil.writeShortBE(buf, 3338);
      this.headersEncodedSizeAccumulator = 0.2F * padSizeForAccumulation(buf.readableBytes()) + 0.8F * this.headersEncodedSizeAccumulator;
      encodeByteBufHttpContent(state, ctx, buf, content, msg.trailingHeaders(), out);
    } finally {
      msg.release();
    } 
  }
  
  private static boolean encodeContentNonChunk(List<Object> out, ByteBuf buf, ByteBuf content) {
    int contentLength = content.readableBytes();
    if (contentLength > 0) {
      if (buf.maxFastWritableBytes() >= contentLength) {
        buf.writeBytes(content);
        out.add(buf);
      } else {
        out.add(buf);
        out.add(content.retain());
      } 
      return true;
    } 
    return false;
  }
  
  private static void throwUnexpectedMessageTypeEx(Object msg, int state) {
    throw new IllegalStateException("unexpected message type: " + StringUtil.simpleClassName(msg) + ", state: " + state);
  }
  
  private void encodeFileRegionContent(ChannelHandlerContext ctx, FileRegion msg, List<Object> out) {
    try {
      assert this.state != 0;
      switch (this.state) {
        case 1:
          if (msg.count() > 0L) {
            out.add(msg.retain());
            break;
          } 
        case 3:
          out.add(Unpooled.EMPTY_BUFFER);
          break;
        case 2:
          encodedChunkedFileRegionContent(ctx, msg, out);
          break;
        default:
          throw new Error();
      } 
    } finally {
      msg.release();
    } 
  }
  
  private static boolean bypassEncoderIfEmpty(ByteBuf msg, List<Object> out) {
    if (!msg.isReadable()) {
      out.add(msg.retain());
      return true;
    } 
    return false;
  }
  
  private void encodeByteBufContent(ChannelHandlerContext ctx, ByteBuf content, List<Object> out) {
    try {
      assert this.state != 0;
      if (bypassEncoderIfEmpty(content, out))
        return; 
      encodeByteBufAndTrailers(this.state, ctx, out, content, null);
    } finally {
      content.release();
    } 
  }
  
  private static int encodeEmptyLastHttpContent(int state, List<Object> out) {
    assert state != 0;
    switch (state) {
      case 1:
      case 3:
        out.add(Unpooled.EMPTY_BUFFER);
        return 0;
      case 2:
        out.add(ZERO_CRLF_CRLF_BUF.duplicate());
        return 0;
    } 
    throw new Error();
  }
  
  private void encodeLastHttpContent(ChannelHandlerContext ctx, LastHttpContent msg, List<Object> out) {
    assert this.state != 0;
    assert !(msg instanceof HttpMessage);
    try {
      encodeByteBufAndTrailers(this.state, ctx, out, msg.content(), msg.trailingHeaders());
      this.state = 0;
    } finally {
      msg.release();
    } 
  }
  
  private void encodeHttpContent(ChannelHandlerContext ctx, HttpContent msg, List<Object> out) {
    assert this.state != 0;
    assert !(msg instanceof HttpMessage);
    assert !(msg instanceof LastHttpContent);
    try {
      encodeByteBufAndTrailers(this.state, ctx, out, msg.content(), null);
    } finally {
      msg.release();
    } 
  }
  
  private void encodeByteBufAndTrailers(int state, ChannelHandlerContext ctx, List<Object> out, ByteBuf content, HttpHeaders trailingHeaders) {
    switch (state) {
      case 1:
        if (content.isReadable()) {
          out.add(content.retain());
          return;
        } 
      case 3:
        out.add(Unpooled.EMPTY_BUFFER);
        return;
      case 2:
        encodeChunkedHttpContent(ctx, content, trailingHeaders, out);
        return;
    } 
    throw new Error();
  }
  
  private void encodeChunkedHttpContent(ChannelHandlerContext ctx, ByteBuf content, HttpHeaders trailingHeaders, List<Object> out) {
    int contentLength = content.readableBytes();
    if (contentLength > 0) {
      addEncodedLengthHex(ctx, contentLength, out);
      out.add(content.retain());
      out.add(CRLF_BUF.duplicate());
    } 
    if (trailingHeaders != null) {
      encodeTrailingHeaders(ctx, trailingHeaders, out);
    } else if (contentLength == 0) {
      out.add(content.retain());
    } 
  }
  
  private void encodeTrailingHeaders(ChannelHandlerContext ctx, HttpHeaders trailingHeaders, List<Object> out) {
    if (trailingHeaders.isEmpty()) {
      out.add(ZERO_CRLF_CRLF_BUF.duplicate());
    } else {
      ByteBuf buf = ctx.alloc().buffer((int)this.trailersEncodedSizeAccumulator);
      ByteBufUtil.writeMediumBE(buf, 3149066);
      encodeHeaders(trailingHeaders, buf);
      ByteBufUtil.writeShortBE(buf, 3338);
      this.trailersEncodedSizeAccumulator = 0.2F * padSizeForAccumulation(buf.readableBytes()) + 0.8F * this.trailersEncodedSizeAccumulator;
      out.add(buf);
    } 
  }
  
  private ByteBuf encodeInitHttpMessage(ChannelHandlerContext ctx, H m) throws Exception {
    assert this.state == 0;
    ByteBuf buf = ctx.alloc().buffer((int)this.headersEncodedSizeAccumulator);
    encodeInitialLine(buf, m);
    this
      .state = isContentAlwaysEmpty(m) ? 3 : (HttpUtil.isTransferEncodingChunked((HttpMessage)m) ? 2 : 1);
    sanitizeHeadersBeforeEncode(m, (this.state == 3));
    encodeHeaders(m.headers(), buf);
    ByteBufUtil.writeShortBE(buf, 3338);
    this.headersEncodedSizeAccumulator = 0.2F * padSizeForAccumulation(buf.readableBytes()) + 0.8F * this.headersEncodedSizeAccumulator;
    return buf;
  }
  
  protected void encodeHeaders(HttpHeaders headers, ByteBuf buf) {
    Iterator<Map.Entry<CharSequence, CharSequence>> iter = headers.iteratorCharSequence();
    while (iter.hasNext()) {
      Map.Entry<CharSequence, CharSequence> header = iter.next();
      HttpHeadersEncoder.encoderHeader(header.getKey(), header.getValue(), buf);
    } 
  }
  
  private static void encodedChunkedFileRegionContent(ChannelHandlerContext ctx, FileRegion msg, List<Object> out) {
    long contentLength = msg.count();
    if (contentLength > 0L) {
      addEncodedLengthHex(ctx, contentLength, out);
      out.add(msg.retain());
      out.add(CRLF_BUF.duplicate());
    } else if (contentLength == 0L) {
      out.add(msg.retain());
    } 
  }
  
  private static void addEncodedLengthHex(ChannelHandlerContext ctx, long contentLength, List<Object> out) {
    String lengthHex = Long.toHexString(contentLength);
    ByteBuf buf = ctx.alloc().buffer(lengthHex.length() + 2);
    buf.writeCharSequence(lengthHex, CharsetUtil.US_ASCII);
    ByteBufUtil.writeShortBE(buf, 3338);
    out.add(buf);
  }
  
  protected void sanitizeHeadersBeforeEncode(H msg, boolean isAlwaysEmpty) {}
  
  protected boolean isContentAlwaysEmpty(H msg) {
    return false;
  }
  
  public boolean acceptOutboundMessage(Object msg) throws Exception {
    return (msg == Unpooled.EMPTY_BUFFER || msg == LastHttpContent.EMPTY_LAST_CONTENT || msg instanceof FullHttpMessage || msg instanceof HttpMessage || msg instanceof LastHttpContent || msg instanceof HttpContent || msg instanceof ByteBuf || msg instanceof FileRegion);
  }
  
  private static int padSizeForAccumulation(int readableBytes) {
    return (readableBytes << 2) / 3;
  }
  
  @Deprecated
  protected static void encodeAscii(String s, ByteBuf buf) {
    buf.writeCharSequence(s, CharsetUtil.US_ASCII);
  }
  
  protected abstract void encodeInitialLine(ByteBuf paramByteBuf, H paramH) throws Exception;
}
