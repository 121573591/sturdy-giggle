package cn.hutool.socket;

import cn.hutool.core.io.IORuntimeException;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.ClosedChannelException;

public class SocketUtil {
  public static SocketAddress getRemoteAddress(AsynchronousSocketChannel channel) throws IORuntimeException {
    try {
      return (null == channel) ? null : channel.getRemoteAddress();
    } catch (ClosedChannelException e) {
      return null;
    } catch (IOException e) {
      throw new IORuntimeException(e);
    } 
  }
  
  public static boolean isConnected(AsynchronousSocketChannel channel) throws IORuntimeException {
    return (null != getRemoteAddress(channel));
  }
  
  public static Socket connect(String hostname, int port) throws IORuntimeException {
    return connect(hostname, port, -1);
  }
  
  public static Socket connect(String hostname, int port, int connectionTimeout) throws IORuntimeException {
    return connect(new InetSocketAddress(hostname, port), connectionTimeout);
  }
  
  public static Socket connect(InetSocketAddress address, int connectionTimeout) throws IORuntimeException {
    Socket socket = new Socket();
    try {
      if (connectionTimeout <= 0) {
        socket.connect(address);
      } else {
        socket.connect(address, connectionTimeout);
      } 
    } catch (IOException e) {
      throw new IORuntimeException(e);
    } 
    return socket;
  }
}
