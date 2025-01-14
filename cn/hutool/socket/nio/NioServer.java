package cn.hutool.socket.nio;

import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.io.IoUtil;
import cn.hutool.log.Log;
import java.io.Closeable;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NioServer implements Closeable {
  private static final Log log = Log.get();
  
  private static final AcceptHandler ACCEPT_HANDLER = new AcceptHandler();
  
  private Selector selector;
  
  private ServerSocketChannel serverSocketChannel;
  
  private ChannelHandler handler;
  
  public NioServer(int port) {
    init(new InetSocketAddress(port));
  }
  
  public NioServer init(InetSocketAddress address) {
    try {
      this.serverSocketChannel = ServerSocketChannel.open();
      this.serverSocketChannel.configureBlocking(false);
      this.serverSocketChannel.bind(address);
      this.selector = Selector.open();
      this.serverSocketChannel.register(this.selector, 16);
    } catch (IOException e) {
      close();
      throw new IORuntimeException(e);
    } 
    log.debug("Server listen on: [{}]...", new Object[] { address });
    return this;
  }
  
  public NioServer setChannelHandler(ChannelHandler handler) {
    this.handler = handler;
    return this;
  }
  
  public Selector getSelector() {
    return this.selector;
  }
  
  public void start() {
    listen();
  }
  
  public void listen() {
    try {
      doListen();
    } catch (IOException e) {
      throw new IORuntimeException(e);
    } 
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
    if (key.isAcceptable())
      ACCEPT_HANDLER.completed((ServerSocketChannel)key.channel(), this); 
    if (key.isReadable()) {
      SocketChannel socketChannel = (SocketChannel)key.channel();
      try {
        this.handler.handle(socketChannel);
      } catch (Exception e) {
        IoUtil.close(socketChannel);
        log.error(e);
      } 
    } 
  }
  
  public void close() {
    IoUtil.close(this.selector);
    IoUtil.close(this.serverSocketChannel);
  }
}
