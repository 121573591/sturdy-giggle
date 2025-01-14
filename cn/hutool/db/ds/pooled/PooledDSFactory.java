package cn.hutool.db.ds.pooled;

import cn.hutool.core.util.StrUtil;
import cn.hutool.db.ds.AbstractDSFactory;
import cn.hutool.setting.Setting;
import javax.sql.DataSource;

public class PooledDSFactory extends AbstractDSFactory {
  private static final long serialVersionUID = 8093886210895248277L;
  
  public static final String DS_NAME = "Hutool-Pooled-DataSource";
  
  public PooledDSFactory() {
    this(null);
  }
  
  public PooledDSFactory(Setting setting) {
    super("Hutool-Pooled-DataSource", PooledDataSource.class, setting);
  }
  
  protected DataSource createDataSource(String jdbcUrl, String driver, String user, String pass, Setting poolSetting) {
    DbConfig dbConfig = new DbConfig();
    dbConfig.setUrl(jdbcUrl);
    dbConfig.setDriver(driver);
    dbConfig.setUser(user);
    dbConfig.setPass(pass);
    dbConfig.setInitialSize(poolSetting.getInt("initialSize", Integer.valueOf(0)).intValue());
    dbConfig.setMinIdle(poolSetting.getInt("minIdle", Integer.valueOf(0)).intValue());
    dbConfig.setMaxActive(poolSetting.getInt("maxActive", Integer.valueOf(8)).intValue());
    dbConfig.setMaxWait(poolSetting.getLong("maxWait", Long.valueOf(6000L)).longValue());
    for (String key : KEY_CONN_PROPS) {
      String connValue = poolSetting.get(key);
      if (StrUtil.isNotBlank(connValue))
        dbConfig.addConnProps(key, connValue); 
    } 
    return (DataSource)new PooledDataSource(dbConfig);
  }
}
