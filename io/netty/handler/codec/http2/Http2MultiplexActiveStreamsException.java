package io.netty.handler.codec.http2;

public final class Http2MultiplexActiveStreamsException extends Exception {
  public Http2MultiplexActiveStreamsException(Throwable cause) {
    super(cause);
  }
  
  public Throwable fillInStackTrace() {
    return this;
  }
}
