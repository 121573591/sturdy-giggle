package io.netty.handler.codec.spdy;

import io.netty.util.internal.ObjectUtil;

public abstract class DefaultSpdyStreamFrame implements SpdyStreamFrame {
  private int streamId;
  
  private boolean last;
  
  protected DefaultSpdyStreamFrame(int streamId) {
    setStreamId(streamId);
  }
  
  public int streamId() {
    return this.streamId;
  }
  
  public SpdyStreamFrame setStreamId(int streamId) {
    ObjectUtil.checkPositive(streamId, "streamId");
    this.streamId = streamId;
    return this;
  }
  
  public boolean isLast() {
    return this.last;
  }
  
  public SpdyStreamFrame setLast(boolean last) {
    this.last = last;
    return this;
  }
}
