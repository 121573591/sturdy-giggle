package cn.hutool.socket.nio;

import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.log.Log;
import cn.hutool.socket.SocketRuntimeException;
import java.io.Closeable;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NioClient implements Closeable {
  private static final Log log = Log.get();
  
  private Selector selector;
  
  private SocketChannel channel;
  
  private ChannelHandler handler;
  
  public NioClient(String host, int port) {
    init(new InetSocketAddress(host, port));
  }
  
  public NioClient(InetSocketAddress address) {
    init(address);
  }
  
  public NioClient init(InetSocketAddress address) {
    try {
      this.channel = SocketChannel.open();
      this.channel.configureBlocking(false);
      this.channel.connect(address);
      this.selector = Selector.open();
      this.channel.register(this.selector, 1);
      while (false == this.channel.finishConnect());
    } catch (IOException e) {
      close();
      throw new IORuntimeException(e);
    } 
    return this;
  }
  
  public NioClient setChannelHandler(ChannelHandler handler) {
    this.handler = handler;
    return this;
  }
  
  public void listen() {
    ThreadUtil.execute(() -> {
          try {
            doListen();
          } catch (IOException e) {
            log.error("Listen failed", new Object[] { e });
          } 
        });
  }
  
  private void doListen() throws IOException {
    while (this.selector.isOpen() && 0 != this.selector.select()) {
      Iterator<SelectionKey> keyIter = this.selector.selectedKeys().iterator();
      while (keyIter.hasNext()) {
        handle(keyIter.next());
        keyIter.remove();
      } 
    } 
  }
  
  private void handle(SelectionKey key) {
    if (key.isReadable()) {
      SocketChannel socketChannel = (SocketChannel)key.channel();
      try {
        this.handler.handle(socketChannel);
      } catch (Exception e) {
        throw new SocketRuntimeException(e);
      } 
    } 
  }
  
  public NioClient write(ByteBuffer... datas) {
    try {
      this.channel.write(datas);
    } catch (IOException e) {
      throw new IORuntimeException(e);
    } 
    return this;
  }
  
  public SocketChannel getChannel() {
    return this.channel;
  }
  
  public void close() {
    IoUtil.close(this.selector);
    IoUtil.close(this.channel);
  }
}
