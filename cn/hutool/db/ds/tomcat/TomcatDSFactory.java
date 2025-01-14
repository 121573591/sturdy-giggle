package cn.hutool.db.ds.tomcat;

import cn.hutool.core.util.StrUtil;
import cn.hutool.db.ds.AbstractDSFactory;
import cn.hutool.setting.Setting;
import cn.hutool.setting.dialect.Props;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolConfiguration;
import org.apache.tomcat.jdbc.pool.PoolProperties;

public class TomcatDSFactory extends AbstractDSFactory {
  private static final long serialVersionUID = 4925514193275150156L;
  
  public static final String DS_NAME = "Tomcat-Jdbc-Pool";
  
  public TomcatDSFactory() {
    this(null);
  }
  
  public TomcatDSFactory(Setting setting) {
    super("Tomcat-Jdbc-Pool", DataSource.class, setting);
  }
  
  protected DataSource createDataSource(String jdbcUrl, String driver, String user, String pass, Setting poolSetting) {
    PoolProperties poolProps = new PoolProperties();
    poolProps.setUrl(jdbcUrl);
    poolProps.setDriverClassName(driver);
    poolProps.setUsername(user);
    poolProps.setPassword(pass);
    Props connProps = new Props();
    for (String key : KEY_CONN_PROPS) {
      String connValue = poolSetting.getAndRemoveStr(new String[] { key });
      if (StrUtil.isNotBlank(connValue))
        connProps.setProperty(key, connValue); 
    } 
    poolProps.setDbProperties((Properties)connProps);
    poolSetting.toBean(poolProps);
    return (DataSource)new DataSource((PoolConfiguration)poolProps);
  }
}
