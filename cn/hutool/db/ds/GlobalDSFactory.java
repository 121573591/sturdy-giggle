package cn.hutool.db.ds;

import cn.hutool.log.StaticLog;

public class GlobalDSFactory {
  private static volatile DSFactory factory;
  
  private static final Object lock = new Object();
  
  static {
    Runtime.getRuntime().addShutdownHook(new Thread() {
          public void run() {
            if (null != GlobalDSFactory.factory) {
              GlobalDSFactory.factory.destroy();
              StaticLog.debug("DataSource: [{}] destroyed.", new Object[] { (GlobalDSFactory.access$000()).dataSourceName });
              GlobalDSFactory.factory = null;
            } 
          }
        });
  }
  
  public static DSFactory get() {
    if (null == factory)
      synchronized (lock) {
        if (null == factory)
          factory = DSFactory.create(null); 
      }  
    return factory;
  }
  
  public static DSFactory set(DSFactory customDSFactory) {
    synchronized (lock) {
      if (null != factory) {
        if (factory.equals(customDSFactory))
          return factory; 
        factory.destroy();
      } 
      StaticLog.debug("Custom use [{}] DataSource.", new Object[] { customDSFactory.dataSourceName });
      factory = customDSFactory;
    } 
    return factory;
  }
}
