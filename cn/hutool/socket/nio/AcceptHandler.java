package cn.hutool.socket.nio;

import cn.hutool.core.io.IORuntimeException;
import cn.hutool.log.StaticLog;
import java.io.IOException;
import java.nio.channels.CompletionHandler;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class AcceptHandler implements CompletionHandler<ServerSocketChannel, NioServer> {
  public void completed(ServerSocketChannel serverSocketChannel, NioServer nioServer) {
    SocketChannel socketChannel;
    try {
      socketChannel = serverSocketChannel.accept();
      StaticLog.debug("Client [{}] accepted.", new Object[] { socketChannel.getRemoteAddress() });
    } catch (IOException e) {
      throw new IORuntimeException(e);
    } 
    NioUtil.registerChannel(nioServer.getSelector(), socketChannel, Operation.READ);
  }
  
  public void failed(Throwable exc, NioServer nioServer) {
    StaticLog.error(exc);
  }
}
