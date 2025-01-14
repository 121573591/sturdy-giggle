package io.netty.handler.codec.http2;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.stream.ChunkedInput;
import io.netty.util.internal.ObjectUtil;

public final class Http2DataChunkedInput implements ChunkedInput<Http2DataFrame> {
  private final ChunkedInput<ByteBuf> input;
  
  private final Http2FrameStream stream;
  
  private boolean endStreamSent;
  
  public Http2DataChunkedInput(ChunkedInput<ByteBuf> input, Http2FrameStream stream) {
    this.input = (ChunkedInput<ByteBuf>)ObjectUtil.checkNotNull(input, "input");
    this.stream = (Http2FrameStream)ObjectUtil.checkNotNull(stream, "stream");
  }
  
  public boolean isEndOfInput() throws Exception {
    if (this.input.isEndOfInput())
      return this.endStreamSent; 
    return false;
  }
  
  public void close() throws Exception {
    this.input.close();
  }
  
  @Deprecated
  public Http2DataFrame readChunk(ChannelHandlerContext ctx) throws Exception {
    return readChunk(ctx.alloc());
  }
  
  public Http2DataFrame readChunk(ByteBufAllocator allocator) throws Exception {
    if (this.endStreamSent)
      return null; 
    if (this.input.isEndOfInput()) {
      this.endStreamSent = true;
      return (new DefaultHttp2DataFrame(true)).stream(this.stream);
    } 
    ByteBuf buf = (ByteBuf)this.input.readChunk(allocator);
    if (buf == null)
      return null; 
    Http2DataFrame dataFrame = (new DefaultHttp2DataFrame(buf, this.input.isEndOfInput())).stream(this.stream);
    if (dataFrame.isEndStream())
      this.endStreamSent = true; 
    return dataFrame;
  }
  
  public long length() {
    return this.input.length();
  }
  
  public long progress() {
    return this.input.progress();
  }
}
