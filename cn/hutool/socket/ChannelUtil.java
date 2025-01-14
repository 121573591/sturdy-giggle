package cn.hutool.socket;

import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.thread.ThreadFactoryBuilder;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousSocketChannel;

public class ChannelUtil {
  public static AsynchronousChannelGroup createFixedGroup(int poolSize) {
    try {
      return AsynchronousChannelGroup.withFixedThreadPool(poolSize, 
          
          ThreadFactoryBuilder.create().setNamePrefix("Huool-socket-").build());
    } catch (IOException e) {
      throw new IORuntimeException(e);
    } 
  }
  
  public static AsynchronousSocketChannel connect(AsynchronousChannelGroup group, InetSocketAddress address) {
    AsynchronousSocketChannel channel;
    try {
      channel = AsynchronousSocketChannel.open(group);
    } catch (IOException e) {
      throw new IORuntimeException(e);
    } 
    try {
      channel.connect(address).get();
    } catch (InterruptedException|java.util.concurrent.ExecutionException e) {
      IoUtil.close(channel);
      throw new SocketRuntimeException(e);
    } 
    return channel;
  }
}
