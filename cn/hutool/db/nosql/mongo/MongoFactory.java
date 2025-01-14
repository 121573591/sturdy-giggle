package cn.hutool.db.nosql.mongo;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.map.SafeConcurrentHashMap;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.RuntimeUtil;
import cn.hutool.setting.Setting;
import java.util.Collection;
import java.util.Map;

public class MongoFactory {
  private static final String GROUP_SEPRATER = ",";
  
  private static final Map<String, MongoDS> DS_MAP = (Map<String, MongoDS>)new SafeConcurrentHashMap();
  
  static {
    RuntimeUtil.addShutdownHook(MongoFactory::closeAll);
  }
  
  public static MongoDS getDS(String host, int port) {
    String key = host + ":" + port;
    return DS_MAP.computeIfAbsent(key, k -> new MongoDS(host, port));
  }
  
  public static MongoDS getDS(String... groups) {
    String key = ArrayUtil.join((Object[])groups, ",");
    MongoDS ds = DS_MAP.get(key);
    if (null == ds) {
      ds = new MongoDS(groups);
      DS_MAP.put(key, ds);
    } 
    return ds;
  }
  
  public static MongoDS getDS(Collection<String> groups) {
    return getDS(groups.<String>toArray(new String[0]));
  }
  
  public static MongoDS getDS(Setting setting, String... groups) {
    String key = setting.getSettingPath() + "," + ArrayUtil.join((Object[])groups, ",");
    MongoDS ds = DS_MAP.get(key);
    if (null == ds) {
      ds = new MongoDS(setting, groups);
      DS_MAP.put(key, ds);
    } 
    return ds;
  }
  
  public static MongoDS getDS(Setting setting, Collection<String> groups) {
    return getDS(setting, groups.<String>toArray(new String[0]));
  }
  
  public static void closeAll() {
    if (MapUtil.isNotEmpty(DS_MAP)) {
      for (MongoDS ds : DS_MAP.values())
        ds.close(); 
      DS_MAP.clear();
    } 
  }
}
