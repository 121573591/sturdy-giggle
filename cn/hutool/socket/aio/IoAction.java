package cn.hutool.socket.aio;

public interface IoAction<T> {
  void accept(AioSession paramAioSession);
  
  void doAction(AioSession paramAioSession, T paramT);
  
  void failed(Throwable paramThrowable, AioSession paramAioSession);
}
