package tech.ordinaryroad.live.chat.client.commons.client.config;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import tech.ordinaryroad.live.chat.client.commons.base.exception.BaseException;
import tech.ordinaryroad.live.chat.client.commons.util.OrLiveChatHttpUtil;

public abstract class BaseLiveChatClientConfig {
  protected final PropertyChangeSupport propertyChangeSupport;
  
  public static final long DEFAULT_HEARTBEAT_INITIAL_DELAY = 15L;
  
  public static final long DEFAULT_HEARTBEAT_PERIOD = 25L;
  
  public static final long DEFAULT_MIN_SEND_DANMU_PERIOD = 3000L;
  
  public static final long DEFAULT_HANDSHAKE_TIMEOUT_MILLIS = 5000L;
  
  private String websocketUri;
  
  private String forwardWebsocketUri;
  
  private String cookie;
  
  private Object roomId;
  
  private boolean autoReconnect;
  
  private int reconnectDelay;
  
  private long heartbeatInitialDelay;
  
  private long heartbeatPeriod;
  
  private long minSendDanmuPeriod;
  
  private long handshakeTimeoutMillis;
  
  private String socks5ProxyHost;
  
  private int socks5ProxyPort;
  
  private String socks5ProxyUsername;
  
  private String socks5ProxyPassword;
  
  public boolean equals(Object o) {
    if (o == this)
      return true; 
    if (!(o instanceof BaseLiveChatClientConfig))
      return false; 
    BaseLiveChatClientConfig other = (BaseLiveChatClientConfig)o;
    if (!other.canEqual(this))
      return false; 
    if (isAutoReconnect() != other.isAutoReconnect())
      return false; 
    if (getReconnectDelay() != other.getReconnectDelay())
      return false; 
    if (getHeartbeatInitialDelay() != other.getHeartbeatInitialDelay())
      return false; 
    if (getHeartbeatPeriod() != other.getHeartbeatPeriod())
      return false; 
    if (getMinSendDanmuPeriod() != other.getMinSendDanmuPeriod())
      return false; 
    if (getHandshakeTimeoutMillis() != other.getHandshakeTimeoutMillis())
      return false; 
    if (getSocks5ProxyPort() != other.getSocks5ProxyPort())
      return false; 
    Object this$propertyChangeSupport = getPropertyChangeSupport(), other$propertyChangeSupport = other.getPropertyChangeSupport();
    if ((this$propertyChangeSupport == null) ? (other$propertyChangeSupport != null) : !this$propertyChangeSupport.equals(other$propertyChangeSupport))
      return false; 
    Object this$websocketUri = getWebsocketUri(), other$websocketUri = other.getWebsocketUri();
    if ((this$websocketUri == null) ? (other$websocketUri != null) : !this$websocketUri.equals(other$websocketUri))
      return false; 
    Object this$forwardWebsocketUri = getForwardWebsocketUri(), other$forwardWebsocketUri = other.getForwardWebsocketUri();
    if ((this$forwardWebsocketUri == null) ? (other$forwardWebsocketUri != null) : !this$forwardWebsocketUri.equals(other$forwardWebsocketUri))
      return false; 
    Object this$cookie = getCookie(), other$cookie = other.getCookie();
    if ((this$cookie == null) ? (other$cookie != null) : !this$cookie.equals(other$cookie))
      return false; 
    Object this$roomId = getRoomId(), other$roomId = other.getRoomId();
    if ((this$roomId == null) ? (other$roomId != null) : !this$roomId.equals(other$roomId))
      return false; 
    Object this$socks5ProxyHost = getSocks5ProxyHost(), other$socks5ProxyHost = other.getSocks5ProxyHost();
    if ((this$socks5ProxyHost == null) ? (other$socks5ProxyHost != null) : !this$socks5ProxyHost.equals(other$socks5ProxyHost))
      return false; 
    Object this$socks5ProxyUsername = getSocks5ProxyUsername(), other$socks5ProxyUsername = other.getSocks5ProxyUsername();
    if ((this$socks5ProxyUsername == null) ? (other$socks5ProxyUsername != null) : !this$socks5ProxyUsername.equals(other$socks5ProxyUsername))
      return false; 
    Object this$socks5ProxyPassword = getSocks5ProxyPassword(), other$socks5ProxyPassword = other.getSocks5ProxyPassword();
    return !((this$socks5ProxyPassword == null) ? (other$socks5ProxyPassword != null) : !this$socks5ProxyPassword.equals(other$socks5ProxyPassword));
  }
  
  protected boolean canEqual(Object other) {
    return other instanceof BaseLiveChatClientConfig;
  }
  
  public int hashCode() {
    int PRIME = 59;
    result = 1;
    result = result * 59 + (isAutoReconnect() ? 79 : 97);
    result = result * 59 + getReconnectDelay();
    long $heartbeatInitialDelay = getHeartbeatInitialDelay();
    result = result * 59 + (int)($heartbeatInitialDelay >>> 32L ^ $heartbeatInitialDelay);
    long $heartbeatPeriod = getHeartbeatPeriod();
    result = result * 59 + (int)($heartbeatPeriod >>> 32L ^ $heartbeatPeriod);
    long $minSendDanmuPeriod = getMinSendDanmuPeriod();
    result = result * 59 + (int)($minSendDanmuPeriod >>> 32L ^ $minSendDanmuPeriod);
    long $handshakeTimeoutMillis = getHandshakeTimeoutMillis();
    result = result * 59 + (int)($handshakeTimeoutMillis >>> 32L ^ $handshakeTimeoutMillis);
    result = result * 59 + getSocks5ProxyPort();
    Object $propertyChangeSupport = getPropertyChangeSupport();
    result = result * 59 + (($propertyChangeSupport == null) ? 43 : $propertyChangeSupport.hashCode());
    Object $websocketUri = getWebsocketUri();
    result = result * 59 + (($websocketUri == null) ? 43 : $websocketUri.hashCode());
    Object $forwardWebsocketUri = getForwardWebsocketUri();
    result = result * 59 + (($forwardWebsocketUri == null) ? 43 : $forwardWebsocketUri.hashCode());
    Object $cookie = getCookie();
    result = result * 59 + (($cookie == null) ? 43 : $cookie.hashCode());
    Object $roomId = getRoomId();
    result = result * 59 + (($roomId == null) ? 43 : $roomId.hashCode());
    Object $socks5ProxyHost = getSocks5ProxyHost();
    result = result * 59 + (($socks5ProxyHost == null) ? 43 : $socks5ProxyHost.hashCode());
    Object $socks5ProxyUsername = getSocks5ProxyUsername();
    result = result * 59 + (($socks5ProxyUsername == null) ? 43 : $socks5ProxyUsername.hashCode());
    Object $socks5ProxyPassword = getSocks5ProxyPassword();
    return result * 59 + (($socks5ProxyPassword == null) ? 43 : $socks5ProxyPassword.hashCode());
  }
  
  public String toString() {
    return "BaseLiveChatClientConfig(propertyChangeSupport=" + getPropertyChangeSupport() + ", websocketUri=" + getWebsocketUri() + ", forwardWebsocketUri=" + getForwardWebsocketUri() + ", cookie=" + getCookie() + ", roomId=" + getRoomId() + ", autoReconnect=" + isAutoReconnect() + ", reconnectDelay=" + getReconnectDelay() + ", heartbeatInitialDelay=" + getHeartbeatInitialDelay() + ", heartbeatPeriod=" + getHeartbeatPeriod() + ", minSendDanmuPeriod=" + getMinSendDanmuPeriod() + ", handshakeTimeoutMillis=" + getHandshakeTimeoutMillis() + ", socks5ProxyHost=" + getSocks5ProxyHost() + ", socks5ProxyPort=" + getSocks5ProxyPort() + ", socks5ProxyUsername=" + getSocks5ProxyUsername() + ", socks5ProxyPassword=" + getSocks5ProxyPassword() + ")";
  }
  
  public BaseLiveChatClientConfig() {
    this.propertyChangeSupport = new PropertyChangeSupport(this);
    this.autoReconnect = $default$autoReconnect();
    this.reconnectDelay = $default$reconnectDelay();
    this.heartbeatInitialDelay = $default$heartbeatInitialDelay();
    this.heartbeatPeriod = $default$heartbeatPeriod();
    this.minSendDanmuPeriod = $default$minSendDanmuPeriod();
    this.handshakeTimeoutMillis = $default$handshakeTimeoutMillis();
  }
  
  public BaseLiveChatClientConfig(String websocketUri, String forwardWebsocketUri, String cookie, Object roomId, boolean autoReconnect, int reconnectDelay, long heartbeatInitialDelay, long heartbeatPeriod, long minSendDanmuPeriod, long handshakeTimeoutMillis, String socks5ProxyHost, int socks5ProxyPort, String socks5ProxyUsername, String socks5ProxyPassword) {
    this.propertyChangeSupport = new PropertyChangeSupport(this);
    this.websocketUri = websocketUri;
    this.forwardWebsocketUri = forwardWebsocketUri;
    this.cookie = cookie;
    this.roomId = roomId;
    this.autoReconnect = autoReconnect;
    this.reconnectDelay = reconnectDelay;
    this.heartbeatInitialDelay = heartbeatInitialDelay;
    this.heartbeatPeriod = heartbeatPeriod;
    this.minSendDanmuPeriod = minSendDanmuPeriod;
    this.handshakeTimeoutMillis = handshakeTimeoutMillis;
    this.socks5ProxyHost = socks5ProxyHost;
    this.socks5ProxyPort = socks5ProxyPort;
    this.socks5ProxyUsername = socks5ProxyUsername;
    this.socks5ProxyPassword = socks5ProxyPassword;
  }
  
  private static boolean $default$autoReconnect() {
    return Boolean.TRUE.booleanValue();
  }
  
  private static int $default$reconnectDelay() {
    return 5;
  }
  
  private static long $default$heartbeatInitialDelay() {
    return 15L;
  }
  
  private static long $default$heartbeatPeriod() {
    return 25L;
  }
  
  private static long $default$minSendDanmuPeriod() {
    return 3000L;
  }
  
  private static long $default$handshakeTimeoutMillis() {
    return 5000L;
  }
  
  protected BaseLiveChatClientConfig(BaseLiveChatClientConfigBuilder<?, ?> b) {
    this.propertyChangeSupport = new PropertyChangeSupport(this);
    this.websocketUri = b.websocketUri;
    this.forwardWebsocketUri = b.forwardWebsocketUri;
    this.cookie = b.cookie;
    this.roomId = b.roomId;
    if (b.autoReconnect$set) {
      this.autoReconnect = b.autoReconnect$value;
    } else {
      this.autoReconnect = $default$autoReconnect();
    } 
    if (b.reconnectDelay$set) {
      this.reconnectDelay = b.reconnectDelay$value;
    } else {
      this.reconnectDelay = $default$reconnectDelay();
    } 
    if (b.heartbeatInitialDelay$set) {
      this.heartbeatInitialDelay = b.heartbeatInitialDelay$value;
    } else {
      this.heartbeatInitialDelay = $default$heartbeatInitialDelay();
    } 
    if (b.heartbeatPeriod$set) {
      this.heartbeatPeriod = b.heartbeatPeriod$value;
    } else {
      this.heartbeatPeriod = $default$heartbeatPeriod();
    } 
    if (b.minSendDanmuPeriod$set) {
      this.minSendDanmuPeriod = b.minSendDanmuPeriod$value;
    } else {
      this.minSendDanmuPeriod = $default$minSendDanmuPeriod();
    } 
    if (b.handshakeTimeoutMillis$set) {
      this.handshakeTimeoutMillis = b.handshakeTimeoutMillis$value;
    } else {
      this.handshakeTimeoutMillis = $default$handshakeTimeoutMillis();
    } 
    this.socks5ProxyHost = b.socks5ProxyHost;
    this.socks5ProxyPort = b.socks5ProxyPort;
    this.socks5ProxyUsername = b.socks5ProxyUsername;
    this.socks5ProxyPassword = b.socks5ProxyPassword;
  }
  
  public static abstract class BaseLiveChatClientConfigBuilder<C extends BaseLiveChatClientConfig, B extends BaseLiveChatClientConfigBuilder<C, B>> {
    private String websocketUri;
    
    private String forwardWebsocketUri;
    
    private String cookie;
    
    private Object roomId;
    
    private boolean autoReconnect$set;
    
    private boolean autoReconnect$value;
    
    private boolean reconnectDelay$set;
    
    private int reconnectDelay$value;
    
    private boolean heartbeatInitialDelay$set;
    
    private long heartbeatInitialDelay$value;
    
    private boolean heartbeatPeriod$set;
    
    private long heartbeatPeriod$value;
    
    private boolean minSendDanmuPeriod$set;
    
    private long minSendDanmuPeriod$value;
    
    private boolean handshakeTimeoutMillis$set;
    
    private long handshakeTimeoutMillis$value;
    
    private String socks5ProxyHost;
    
    private int socks5ProxyPort;
    
    private String socks5ProxyUsername;
    
    private String socks5ProxyPassword;
    
    protected B $fillValuesFrom(C instance) {
      $fillValuesFromInstanceIntoBuilder((BaseLiveChatClientConfig)instance, this);
      return self();
    }
    
    private static void $fillValuesFromInstanceIntoBuilder(BaseLiveChatClientConfig instance, BaseLiveChatClientConfigBuilder<?, ?> b) {
      b.websocketUri(instance.websocketUri);
      b.forwardWebsocketUri(instance.forwardWebsocketUri);
      b.cookie(instance.cookie);
      b.roomId(instance.roomId);
      b.autoReconnect(instance.autoReconnect);
      b.reconnectDelay(instance.reconnectDelay);
      b.heartbeatInitialDelay(instance.heartbeatInitialDelay);
      b.heartbeatPeriod(instance.heartbeatPeriod);
      b.minSendDanmuPeriod(instance.minSendDanmuPeriod);
      b.handshakeTimeoutMillis(instance.handshakeTimeoutMillis);
      b.socks5ProxyHost(instance.socks5ProxyHost);
      b.socks5ProxyPort(instance.socks5ProxyPort);
      b.socks5ProxyUsername(instance.socks5ProxyUsername);
      b.socks5ProxyPassword(instance.socks5ProxyPassword);
    }
    
    public B websocketUri(String websocketUri) {
      this.websocketUri = websocketUri;
      return self();
    }
    
    public B forwardWebsocketUri(String forwardWebsocketUri) {
      this.forwardWebsocketUri = forwardWebsocketUri;
      return self();
    }
    
    public B cookie(String cookie) {
      this.cookie = cookie;
      return self();
    }
    
    public B roomId(Object roomId) {
      this.roomId = roomId;
      return self();
    }
    
    public B autoReconnect(boolean autoReconnect) {
      this.autoReconnect$value = autoReconnect;
      this.autoReconnect$set = true;
      return self();
    }
    
    public B reconnectDelay(int reconnectDelay) {
      this.reconnectDelay$value = reconnectDelay;
      this.reconnectDelay$set = true;
      return self();
    }
    
    public B heartbeatInitialDelay(long heartbeatInitialDelay) {
      this.heartbeatInitialDelay$value = heartbeatInitialDelay;
      this.heartbeatInitialDelay$set = true;
      return self();
    }
    
    public B heartbeatPeriod(long heartbeatPeriod) {
      this.heartbeatPeriod$value = heartbeatPeriod;
      this.heartbeatPeriod$set = true;
      return self();
    }
    
    public B minSendDanmuPeriod(long minSendDanmuPeriod) {
      this.minSendDanmuPeriod$value = minSendDanmuPeriod;
      this.minSendDanmuPeriod$set = true;
      return self();
    }
    
    public B handshakeTimeoutMillis(long handshakeTimeoutMillis) {
      this.handshakeTimeoutMillis$value = handshakeTimeoutMillis;
      this.handshakeTimeoutMillis$set = true;
      return self();
    }
    
    public B socks5ProxyHost(String socks5ProxyHost) {
      this.socks5ProxyHost = socks5ProxyHost;
      return self();
    }
    
    public B socks5ProxyPort(int socks5ProxyPort) {
      this.socks5ProxyPort = socks5ProxyPort;
      return self();
    }
    
    public B socks5ProxyUsername(String socks5ProxyUsername) {
      this.socks5ProxyUsername = socks5ProxyUsername;
      return self();
    }
    
    public B socks5ProxyPassword(String socks5ProxyPassword) {
      this.socks5ProxyPassword = socks5ProxyPassword;
      return self();
    }
    
    protected abstract B self();
    
    public abstract C build();
    
    public String toString() {
      return "BaseLiveChatClientConfig.BaseLiveChatClientConfigBuilder(websocketUri=" + this.websocketUri + ", forwardWebsocketUri=" + this.forwardWebsocketUri + ", cookie=" + this.cookie + ", roomId=" + this.roomId + ", autoReconnect$value=" + this.autoReconnect$value + ", reconnectDelay$value=" + this.reconnectDelay$value + ", heartbeatInitialDelay$value=" + this.heartbeatInitialDelay$value + ", heartbeatPeriod$value=" + this.heartbeatPeriod$value + ", minSendDanmuPeriod$value=" + this.minSendDanmuPeriod$value + ", handshakeTimeoutMillis$value=" + this.handshakeTimeoutMillis$value + ", socks5ProxyHost=" + this.socks5ProxyHost + ", socks5ProxyPort=" + this.socks5ProxyPort + ", socks5ProxyUsername=" + this.socks5ProxyUsername + ", socks5ProxyPassword=" + this.socks5ProxyPassword + ")";
    }
  }
  
  public PropertyChangeSupport getPropertyChangeSupport() {
    return this.propertyChangeSupport;
  }
  
  public String getWebsocketUri() {
    return this.websocketUri;
  }
  
  public String getForwardWebsocketUri() {
    return this.forwardWebsocketUri;
  }
  
  public String getCookie() {
    return this.cookie;
  }
  
  public Object getRoomId() {
    return this.roomId;
  }
  
  public boolean isAutoReconnect() {
    return this.autoReconnect;
  }
  
  public int getReconnectDelay() {
    return this.reconnectDelay;
  }
  
  public long getHeartbeatInitialDelay() {
    return this.heartbeatInitialDelay;
  }
  
  public long getHeartbeatPeriod() {
    return this.heartbeatPeriod;
  }
  
  public long getMinSendDanmuPeriod() {
    return this.minSendDanmuPeriod;
  }
  
  public long getHandshakeTimeoutMillis() {
    return this.handshakeTimeoutMillis;
  }
  
  public String getSocks5ProxyHost() {
    return this.socks5ProxyHost;
  }
  
  public int getSocks5ProxyPort() {
    return this.socks5ProxyPort;
  }
  
  public String getSocks5ProxyUsername() {
    return this.socks5ProxyUsername;
  }
  
  public String getSocks5ProxyPassword() {
    return this.socks5ProxyPassword;
  }
  
  public void setWebsocketUri(String websocketUri) {
    String oldValue = this.websocketUri;
    this.websocketUri = websocketUri;
    this.propertyChangeSupport.firePropertyChange("websocketUri", oldValue, websocketUri);
  }
  
  public void setForwardWebsocketUri(String forwardWebsocketUri) {
    String oldValue = this.forwardWebsocketUri;
    this.forwardWebsocketUri = forwardWebsocketUri;
    this.propertyChangeSupport.firePropertyChange("forwardWebsocketUri", oldValue, forwardWebsocketUri);
  }
  
  public void setCookie(String cookie) {
    String oldValue = this.cookie;
    this.cookie = cookie;
    this.propertyChangeSupport.firePropertyChange("cookie", oldValue, cookie);
  }
  
  public void setRoomId(Object roomId) {
    if (!(roomId instanceof Number) && !(roomId instanceof String))
      throw new BaseException("房间ID仅支持数字或字符串，所传参数类型：" + roomId.getClass() + "值：" + roomId); 
    Object oldValue = this.roomId;
    this.roomId = roomId;
    this.propertyChangeSupport.firePropertyChange("roomId", oldValue, roomId);
  }
  
  public void setAutoReconnect(boolean autoReconnect) {
    boolean oldValue = this.autoReconnect;
    this.autoReconnect = autoReconnect;
    this.propertyChangeSupport.firePropertyChange("autoReconnect", oldValue, autoReconnect);
  }
  
  public void setReconnectDelay(int reconnectDelay) {
    int oldValue = this.reconnectDelay;
    this.reconnectDelay = reconnectDelay;
    this.propertyChangeSupport.firePropertyChange("reconnectDelay", oldValue, reconnectDelay);
  }
  
  public void setHeartbeatInitialDelay(long heartbeatInitialDelay) {
    long oldValue = this.heartbeatInitialDelay;
    this.heartbeatInitialDelay = heartbeatInitialDelay;
    this.propertyChangeSupport.firePropertyChange("heartbeatInitialDelay", Long.valueOf(oldValue), Long.valueOf(heartbeatInitialDelay));
  }
  
  public void setHeartbeatPeriod(long heartbeatPeriod) {
    long oldValue = this.heartbeatPeriod;
    this.heartbeatPeriod = heartbeatPeriod;
    this.propertyChangeSupport.firePropertyChange("heartbeatPeriod", Long.valueOf(oldValue), Long.valueOf(heartbeatPeriod));
  }
  
  public void setMinSendDanmuPeriod(long minSendDanmuPeriod) {
    long oldValue = this.minSendDanmuPeriod;
    this.minSendDanmuPeriod = minSendDanmuPeriod;
    this.propertyChangeSupport.firePropertyChange("minSendDanmuPeriod", Long.valueOf(oldValue), Long.valueOf(minSendDanmuPeriod));
  }
  
  public void setHandshakeTimeoutMillis(long handshakeTimeoutMillis) {
    long oldValue = this.handshakeTimeoutMillis;
    this.handshakeTimeoutMillis = handshakeTimeoutMillis;
    this.propertyChangeSupport.firePropertyChange("handshakeTimeoutMillis", Long.valueOf(oldValue), Long.valueOf(handshakeTimeoutMillis));
  }
  
  public void setSocks5ProxyHost(String socks5ProxyHost) {
    String oldValue = this.socks5ProxyHost;
    this.socks5ProxyHost = socks5ProxyHost;
    this.propertyChangeSupport.firePropertyChange("socks5ProxyHost", oldValue, socks5ProxyHost);
    OrLiveChatHttpUtil.updateProxyHost(socks5ProxyHost);
  }
  
  public void setSocks5ProxyPort(int socks5ProxyPort) {
    int oldValue = this.socks5ProxyPort;
    this.socks5ProxyPort = socks5ProxyPort;
    this.propertyChangeSupport.firePropertyChange("socks5ProxyPort", oldValue, socks5ProxyPort);
    OrLiveChatHttpUtil.updateProxyPort(socks5ProxyPort);
  }
  
  public void setSocks5ProxyUsername(String socks5ProxyUsername) {
    String oldValue = this.socks5ProxyUsername;
    this.socks5ProxyUsername = socks5ProxyUsername;
    this.propertyChangeSupport.firePropertyChange("socks5ProxyUsername", oldValue, socks5ProxyUsername);
    OrLiveChatHttpUtil.updateProxyUsername(socks5ProxyUsername);
  }
  
  public void setSocks5ProxyPassword(String socks5ProxyPassword) {
    String oldValue = this.socks5ProxyPassword;
    this.socks5ProxyPassword = socks5ProxyPassword;
    this.propertyChangeSupport.firePropertyChange("socks5ProxyPassword", oldValue, socks5ProxyPassword);
    OrLiveChatHttpUtil.updateProxyPassword(socks5ProxyPassword);
  }
  
  public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
    this.propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
  }
  
  public void addPropertyChangeListener(PropertyChangeListener listener) {
    this.propertyChangeSupport.addPropertyChangeListener(listener);
  }
  
  public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
    this.propertyChangeSupport.removePropertyChangeListener(propertyName, listener);
  }
  
  public void removePropertyChangeListener(PropertyChangeListener listener) {
    this.propertyChangeSupport.removePropertyChangeListener(listener);
  }
}
