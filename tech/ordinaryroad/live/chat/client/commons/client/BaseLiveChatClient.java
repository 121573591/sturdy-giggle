package tech.ordinaryroad.live.chat.client.commons.client;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import tech.ordinaryroad.live.chat.client.commons.base.listener.IBaseMsgListener;
import tech.ordinaryroad.live.chat.client.commons.client.config.BaseLiveChatClientConfig;
import tech.ordinaryroad.live.chat.client.commons.client.enums.ClientStatusEnums;
import tech.ordinaryroad.live.chat.client.commons.client.listener.IClientStatusChangeListener;

public abstract class BaseLiveChatClient<Config extends BaseLiveChatClientConfig, MsgListener extends IBaseMsgListener<?, ?>> implements IBaseLiveChatClient<MsgListener> {
  private final Config config;
  
  private volatile ClientStatusEnums status = ClientStatusEnums.NEW;
  
  protected PropertyChangeSupport statusChangeSupport = new PropertyChangeSupport(this.status);
  
  protected volatile boolean cancelReconnect = false;
  
  public List<MsgListener> getMsgListeners() {
    return this.msgListeners;
  }
  
  private final List<MsgListener> msgListeners = Collections.synchronizedList(new ArrayList<>());
  
  protected BaseLiveChatClient(Config config) {
    this.config = config;
    this.config.setSocks5ProxyHost(this.config.getSocks5ProxyHost());
    this.config.setSocks5ProxyPort(this.config.getSocks5ProxyPort());
    this.config.setSocks5ProxyUsername(this.config.getSocks5ProxyUsername());
    this.config.setSocks5ProxyPassword(this.config.getSocks5ProxyPassword());
  }
  
  public Config getConfig() {
    return this.config;
  }
  
  public void connect(Runnable success) {
    connect(success, null);
  }
  
  public void connect() {
    connect(null, null);
  }
  
  public void disconnect(boolean cancelReconnect) {
    this.cancelReconnect = cancelReconnect;
    disconnect();
  }
  
  public void send(Object msg) {
    send(msg, null, null);
  }
  
  public void send(Object msg, Runnable success) {
    send(msg, success, null);
  }
  
  public void send(Object msg, Consumer<Throwable> failed) {
    send(msg, null, failed);
  }
  
  public void sendDanmu(Object danmu) {
    sendDanmu(danmu, null, null);
  }
  
  public void sendDanmu(Object danmu, Runnable success) {
    sendDanmu(danmu, success, null);
  }
  
  public void sendDanmu(Object danmu, Consumer<Throwable> failed) {
    sendDanmu(danmu, null, failed);
  }
  
  public void clickLike(int count) {
    clickLike(count, null, null);
  }
  
  public void clickLike(int count, Runnable success) {
    clickLike(count, success, null);
  }
  
  public void clickLike(int count, Consumer<Throwable> failed) {
    clickLike(count, null, failed);
  }
  
  protected boolean checkStatus(ClientStatusEnums status) {
    return (this.status.getCode() >= ((ClientStatusEnums)Objects.requireNonNull((T)status)).getCode());
  }
  
  protected void setStatus(ClientStatusEnums status) {
    ClientStatusEnums oldStatus = this.status;
    if (oldStatus != status) {
      this.status = status;
      this.statusChangeSupport.firePropertyChange("status", oldStatus, status);
    } 
  }
  
  public ClientStatusEnums getStatus() {
    return this.status;
  }
  
  public void addStatusChangeListener(IClientStatusChangeListener listener) {
    this.statusChangeSupport.addPropertyChangeListener((PropertyChangeListener)listener);
  }
  
  public void removeStatusChangeListener(IClientStatusChangeListener listener) {
    this.statusChangeSupport.removePropertyChangeListener((PropertyChangeListener)listener);
  }
  
  public void destroy() {
    for (PropertyChangeListener propertyChangeListener : this.statusChangeSupport.getPropertyChangeListeners())
      this.statusChangeSupport.removePropertyChangeListener(propertyChangeListener); 
    this.msgListeners.clear();
  }
  
  public boolean addMsgListener(MsgListener msgListener) {
    if (msgListener == null)
      return false; 
    return this.msgListeners.add(msgListener);
  }
  
  public boolean addMsgListeners(List<MsgListener> msgListeners) {
    if (msgListeners == null || msgListeners.isEmpty())
      return false; 
    return this.msgListeners.addAll(msgListeners);
  }
  
  public boolean removeMsgListener(MsgListener msgListener) {
    if (msgListener == null)
      return false; 
    return this.msgListeners.remove(msgListener);
  }
  
  public boolean removeMsgListeners(List<MsgListener> msgListeners) {
    if (msgListeners == null || msgListeners.isEmpty())
      return false; 
    return this.msgListeners.removeAll(msgListeners);
  }
  
  public void removeAllMsgListeners() {
    this.msgListeners.clear();
  }
  
  public void iteratorMsgListeners(Consumer<MsgListener> consumer) {
    if (this.msgListeners.isEmpty())
      return; 
    for (int i = 0; i < this.msgListeners.size(); i++)
      consumer.accept(this.msgListeners.get(i)); 
  }
  
  protected abstract void tryReconnect();
  
  protected abstract String getWebSocketUriString();
}
