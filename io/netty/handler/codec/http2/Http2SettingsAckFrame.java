package io.netty.handler.codec.http2;

public interface Http2SettingsAckFrame extends Http2Frame {
  public static final Http2SettingsAckFrame INSTANCE = new DefaultHttp2SettingsAckFrame();
  
  String name();
}
