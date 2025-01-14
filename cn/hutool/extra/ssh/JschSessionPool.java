package cn.hutool.extra.ssh;

import cn.hutool.core.lang.SimpleCache;
import cn.hutool.core.util.StrUtil;
import com.jcraft.jsch.Session;
import java.lang.invoke.SerializedLambda;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum JschSessionPool {
  INSTANCE;
  
  private final SimpleCache<String, Session> cache;
  
  JschSessionPool() {
    this.cache = new SimpleCache(new HashMap<>());
  }
  
  public Session get(String key) {
    return (Session)this.cache.get(key);
  }
  
  public Session getSession(String sshHost, int sshPort, String sshUser, String sshPass) {
    String key = StrUtil.format("{}@{}:{}", new Object[] { sshUser, sshHost, Integer.valueOf(sshPort) });
    return (Session)this.cache.get(key, Session::isConnected, () -> JschUtil.openSession(sshHost, sshPort, sshUser, sshPass));
  }
  
  public Session getSession(String sshHost, int sshPort, String sshUser, String prvkey, byte[] passphrase) {
    String key = StrUtil.format("{}@{}:{}", new Object[] { sshUser, sshHost, Integer.valueOf(sshPort) });
    return (Session)this.cache.get(key, Session::isConnected, () -> JschUtil.openSession(sshHost, sshPort, sshUser, prvkey, passphrase));
  }
  
  public Session getSession(String sshHost, int sshPort, String sshUser, byte[] prvkey, byte[] passphrase) {
    String key = StrUtil.format("{}@{}:{}", new Object[] { sshUser, sshHost, Integer.valueOf(sshPort) });
    return (Session)this.cache.get(key, Session::isConnected, () -> JschUtil.openSession(sshHost, sshPort, sshUser, prvkey, passphrase));
  }
  
  public void put(String key, Session session) {
    this.cache.put(key, session);
  }
  
  public void close(String key) {
    Session session = get(key);
    if (session != null && session.isConnected())
      session.disconnect(); 
    this.cache.remove(key);
  }
  
  public void remove(Session session) {
    if (null != session) {
      Iterator<Map.Entry<String, Session>> iterator = this.cache.iterator();
      while (iterator.hasNext()) {
        Map.Entry<String, Session> entry = iterator.next();
        if (session.equals(entry.getValue())) {
          iterator.remove();
          break;
        } 
      } 
    } 
  }
  
  public void closeAll() {
    for (Map.Entry<String, Session> entry : this.cache) {
      Session session = entry.getValue();
      if (session != null && session.isConnected())
        session.disconnect(); 
    } 
    this.cache.clear();
  }
}
