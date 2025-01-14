package cn.hutool.socket.protocol;

import cn.hutool.socket.aio.AioSession;
import java.nio.ByteBuffer;

public interface MsgDecoder<T> {
  T decode(AioSession paramAioSession, ByteBuffer paramByteBuffer);
}
