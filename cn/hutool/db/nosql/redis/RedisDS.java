package cn.hutool.db.nosql.redis;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.setting.Setting;
import java.io.Closeable;
import java.io.Serializable;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisDS implements Closeable, Serializable {
  private static final long serialVersionUID = -5605411972456177456L;
  
  public static final String REDIS_CONFIG_PATH = "config/redis.setting";
  
  private Setting setting;
  
  private JedisPool pool;
  
  public static RedisDS create() {
    return new RedisDS();
  }
  
  public static RedisDS create(String group) {
    return new RedisDS(group);
  }
  
  public static RedisDS create(Setting setting, String group) {
    return new RedisDS(setting, group);
  }
  
  public RedisDS() {
    this(null, null);
  }
  
  public RedisDS(String group) {
    this(null, group);
  }
  
  public RedisDS(Setting setting, String group) {
    this.setting = setting;
    init(group);
  }
  
  public RedisDS init(String group) {
    if (null == this.setting)
      this.setting = new Setting("config/redis.setting", true); 
    JedisPoolConfig config = new JedisPoolConfig();
    this.setting.toBean(config);
    if (StrUtil.isNotBlank(group))
      this.setting.toBean(group, config); 
    Long maxWaitMillis = this.setting.getLong("maxWaitMillis");
    if (null != maxWaitMillis)
      config.setMaxWaitMillis(maxWaitMillis.longValue()); 
    this
      
      .pool = new JedisPool((GenericObjectPoolConfig)config, this.setting.getStr("host", group, "127.0.0.1"), this.setting.getInt("port", group, Integer.valueOf(6379)).intValue(), this.setting.getInt("connectionTimeout", group, this.setting.getInt("timeout", group, Integer.valueOf(2000))).intValue(), this.setting.getInt("soTimeout", group, this.setting.getInt("timeout", group, Integer.valueOf(2000))).intValue(), this.setting.getStr("user", group, null), this.setting.getStr("password", group, null), this.setting.getInt("database", group, Integer.valueOf(0)).intValue(), this.setting.getStr("clientName", group, "Hutool"), this.setting.getBool("ssl", group, Boolean.valueOf(false)).booleanValue(), null, null, null);
    return this;
  }
  
  public Jedis getJedis() {
    return this.pool.getResource();
  }
  
  public String getStr(String key) {
    try (Jedis jedis = getJedis()) {
      return jedis.get(key);
    } 
  }
  
  public String setStr(String key, String value) {
    try (Jedis jedis = getJedis()) {
      return jedis.set(key, value);
    } 
  }
  
  public Long del(String... keys) {
    try (Jedis jedis = getJedis()) {
      return Long.valueOf(jedis.del(keys));
    } 
  }
  
  public void close() {
    IoUtil.close((Closeable)this.pool);
  }
}
