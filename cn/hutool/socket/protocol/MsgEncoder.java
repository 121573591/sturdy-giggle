package cn.hutool.socket.protocol;

import cn.hutool.socket.aio.AioSession;
import java.nio.ByteBuffer;

public interface MsgEncoder<T> {
  void encode(AioSession paramAioSession, ByteBuffer paramByteBuffer, T paramT);
}
