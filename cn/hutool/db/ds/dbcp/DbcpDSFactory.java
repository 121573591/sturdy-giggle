package cn.hutool.db.ds.dbcp;

import cn.hutool.core.util.StrUtil;
import cn.hutool.db.ds.AbstractDSFactory;
import cn.hutool.setting.Setting;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;

public class DbcpDSFactory extends AbstractDSFactory {
  private static final long serialVersionUID = -9133501414334104548L;
  
  public static final String DS_NAME = "commons-dbcp2";
  
  public DbcpDSFactory() {
    this(null);
  }
  
  public DbcpDSFactory(Setting setting) {
    super("commons-dbcp2", BasicDataSource.class, setting);
  }
  
  protected DataSource createDataSource(String jdbcUrl, String driver, String user, String pass, Setting poolSetting) {
    BasicDataSource ds = new BasicDataSource();
    ds.setUrl(jdbcUrl);
    ds.setDriverClassName(driver);
    ds.setUsername(user);
    ds.setPassword(pass);
    for (String key : KEY_CONN_PROPS) {
      String connValue = poolSetting.getAndRemoveStr(new String[] { key });
      if (StrUtil.isNotBlank(connValue))
        ds.addConnectionProperty(key, connValue); 
    } 
    poolSetting.toBean(ds);
    return (DataSource)ds;
  }
}
