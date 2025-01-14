package tech.ordinaryroad.live.chat.client.commons.util;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.GlobalHeaders;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.http.Method;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Arrays;

public class OrLiveChatHttpUtil extends HttpUtil {
  public static final String USER_AGENT;
  
  private static final ProxyProperties PROXY_PROPERTIES = new ProxyProperties();
  
  static {
    USER_AGENT = "Mozilla/5.0 " + (String)RandomUtil.randomEle(Arrays.asList(new String[] { "(Windows NT 10.0; WOW64)", "(Windows NT 10.0; WOW64)", "(Windows NT 10.0; Win64; x64)", "(Windows NT 6.3; WOW64)", "(Windows NT 6.3; Win64; x64)", "(Windows NT 6.1; Win64; x64)", "(Windows NT 6.1; WOW64)", "(X11; Linux x86_64)", "(Macintosh; Intel Mac OS X 10_12_6)" })) + " AppleWebKit/537.36 (KHTML, like Gecko) Chrome/" + (String)RandomUtil.randomEle(Arrays.asList(new String[] { "110.0.5481.77", "110.0.5481.30", "109.0.5414.74", "108.0.5359.71", "108.0.5359.22", "98.0.4758.48", "97.0.4692.71" })) + " Safari/536.32";
    GlobalHeaders.INSTANCE.header(Header.USER_AGENT, USER_AGENT);
  }
  
  public static void updateProxyHost(String host) {
    PROXY_PROPERTIES.setHost(host);
  }
  
  public static void updateProxyPort(int port) {
    PROXY_PROPERTIES.setPort(port);
  }
  
  public static void updateProxyUsername(String username) {
    PROXY_PROPERTIES.setUsername(username);
  }
  
  public static void updateProxyPassword(String password) {
    PROXY_PROPERTIES.setPassword(password);
  }
  
  public static HttpRequest createRequest(Method method, String url) {
    return setRequestSocks5Proxy(HttpUtil.createRequest(method, url));
  }
  
  public static HttpRequest createGet(String url, boolean isFollowRedirects) {
    return setRequestSocks5Proxy(HttpUtil.createGet(url, isFollowRedirects));
  }
  
  public static HttpRequest createGet(String url) {
    return setRequestSocks5Proxy(HttpUtil.createGet(url));
  }
  
  public static HttpRequest createPost(String url) {
    return setRequestSocks5Proxy(HttpUtil.createPost(url));
  }
  
  public static HttpRequest setRequestSocks5Proxy(HttpRequest request) {
    String host = PROXY_PROPERTIES.getHost();
    if (StrUtil.isNotBlank(host)) {
      request.setProxy(new Proxy(Proxy.Type.SOCKS, new InetSocketAddress(host, PROXY_PROPERTIES.getPort())));
      String username = PROXY_PROPERTIES.getUsername();
      if (StrUtil.isNotBlank(username))
        request.basicProxyAuth(username, PROXY_PROPERTIES.getPassword()); 
    } 
    return request;
  }
  
  public static class ProxyProperties {
    private String host;
    
    private int port;
    
    private String username;
    
    private String password;
    
    public void setHost(String host) {
      this.host = host;
    }
    
    public void setPort(int port) {
      this.port = port;
    }
    
    public void setUsername(String username) {
      this.username = username;
    }
    
    public void setPassword(String password) {
      this.password = password;
    }
    
    public boolean equals(Object o) {
      if (o == this)
        return true; 
      if (!(o instanceof ProxyProperties))
        return false; 
      ProxyProperties other = (ProxyProperties)o;
      if (!other.canEqual(this))
        return false; 
      if (getPort() != other.getPort())
        return false; 
      Object this$host = getHost(), other$host = other.getHost();
      if ((this$host == null) ? (other$host != null) : !this$host.equals(other$host))
        return false; 
      Object this$username = getUsername(), other$username = other.getUsername();
      if ((this$username == null) ? (other$username != null) : !this$username.equals(other$username))
        return false; 
      Object this$password = getPassword(), other$password = other.getPassword();
      return !((this$password == null) ? (other$password != null) : !this$password.equals(other$password));
    }
    
    protected boolean canEqual(Object other) {
      return other instanceof ProxyProperties;
    }
    
    public int hashCode() {
      int PRIME = 59;
      result = 1;
      result = result * 59 + getPort();
      Object $host = getHost();
      result = result * 59 + (($host == null) ? 43 : $host.hashCode());
      Object $username = getUsername();
      result = result * 59 + (($username == null) ? 43 : $username.hashCode());
      Object $password = getPassword();
      return result * 59 + (($password == null) ? 43 : $password.hashCode());
    }
    
    public String toString() {
      return "OrLiveChatHttpUtil.ProxyProperties(host=" + getHost() + ", port=" + getPort() + ", username=" + getUsername() + ", password=" + getPassword() + ")";
    }
    
    public ProxyProperties() {}
    
    public ProxyProperties(String host, int port, String username, String password) {
      this.host = host;
      this.port = port;
      this.username = username;
      this.password = password;
    }
    
    public String getHost() {
      return this.host;
    }
    
    public int getPort() {
      return this.port;
    }
    
    public String getUsername() {
      return this.username;
    }
    
    public String getPassword() {
      return this.password;
    }
  }
}
