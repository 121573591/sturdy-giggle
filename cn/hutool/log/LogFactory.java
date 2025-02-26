package cn.hutool.log;

import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.lang.caller.CallerUtil;
import cn.hutool.core.map.SafeConcurrentHashMap;
import cn.hutool.core.util.ServiceLoaderUtil;
import cn.hutool.log.dialect.console.ConsoleLogFactory;
import cn.hutool.log.dialect.jdk.JdkLogFactory;
import java.net.URL;
import java.util.Map;

public abstract class LogFactory {
  protected String name;
  
  private final Map<Object, Log> logCache;
  
  public LogFactory(String name) {
    this.name = name;
    this.logCache = (Map<Object, Log>)new SafeConcurrentHashMap();
  }
  
  public String getName() {
    return this.name;
  }
  
  public Log getLog(String name) {
    return this.logCache.computeIfAbsent(name, o -> createLog((String)o));
  }
  
  public Log getLog(Class<?> clazz) {
    return this.logCache.computeIfAbsent(clazz, o -> createLog((Class)o));
  }
  
  protected void checkLogExist(Class<?> logClassName) {}
  
  public static LogFactory getCurrentLogFactory() {
    return GlobalLogFactory.get();
  }
  
  public static LogFactory setCurrentLogFactory(Class<? extends LogFactory> logFactoryClass) {
    return GlobalLogFactory.set(logFactoryClass);
  }
  
  public static LogFactory setCurrentLogFactory(LogFactory logFactory) {
    return GlobalLogFactory.set(logFactory);
  }
  
  public static Log get(String name) {
    return getCurrentLogFactory().getLog(name);
  }
  
  public static Log get(Class<?> clazz) {
    return getCurrentLogFactory().getLog(clazz);
  }
  
  public static Log get() {
    return get(CallerUtil.getCallerCaller());
  }
  
  public static LogFactory create() {
    LogFactory factory = doCreate();
    factory.getLog(LogFactory.class).debug("Use [{}] Logger As Default.", new Object[] { factory.name });
    return factory;
  }
  
  private static LogFactory doCreate() {
    LogFactory factory = (LogFactory)ServiceLoaderUtil.loadFirstAvailable(LogFactory.class);
    if (null != factory)
      return factory; 
    URL url = ResourceUtil.getResource("logging.properties");
    return (null != url) ? (LogFactory)new JdkLogFactory() : (LogFactory)new ConsoleLogFactory();
  }
  
  public abstract Log createLog(String paramString);
  
  public abstract Log createLog(Class<?> paramClass);
}
