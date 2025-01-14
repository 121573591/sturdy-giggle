package cn.hutool.core.net;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;
import javax.net.SocketFactory;

public class ProxySocketFactory extends SocketFactory {
  private final Proxy proxy;
  
  public static ProxySocketFactory of(Proxy proxy) {
    return new ProxySocketFactory(proxy);
  }
  
  public ProxySocketFactory(Proxy proxy) {
    this.proxy = proxy;
  }
  
  public Socket createSocket() {
    if (this.proxy != null)
      return new Socket(this.proxy); 
    return new Socket();
  }
  
  public Socket createSocket(InetAddress address, int port) throws IOException {
    if (this.proxy != null) {
      Socket s = new Socket(this.proxy);
      s.connect(new InetSocketAddress(address, port));
      return s;
    } 
    return new Socket(address, port);
  }
  
  public Socket createSocket(InetAddress address, int port, InetAddress localAddr, int localPort) throws IOException {
    if (this.proxy != null) {
      Socket s = new Socket(this.proxy);
      s.bind(new InetSocketAddress(localAddr, localPort));
      s.connect(new InetSocketAddress(address, port));
      return s;
    } 
    return new Socket(address, port, localAddr, localPort);
  }
  
  public Socket createSocket(String host, int port) throws IOException {
    if (this.proxy != null) {
      Socket s = new Socket(this.proxy);
      s.connect(new InetSocketAddress(host, port));
      return s;
    } 
    return new Socket(host, port);
  }
  
  public Socket createSocket(String host, int port, InetAddress localAddr, int localPort) throws IOException {
    if (this.proxy != null) {
      Socket s = new Socket(this.proxy);
      s.bind(new InetSocketAddress(localAddr, localPort));
      s.connect(new InetSocketAddress(host, port));
      return s;
    } 
    return new Socket(host, port, localAddr, localPort);
  }
}
