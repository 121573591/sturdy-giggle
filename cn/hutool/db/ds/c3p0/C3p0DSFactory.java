package cn.hutool.db.ds.c3p0;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.db.DbRuntimeException;
import cn.hutool.db.ds.AbstractDSFactory;
import cn.hutool.setting.Setting;
import cn.hutool.setting.dialect.Props;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.beans.PropertyVetoException;
import java.util.Map;
import java.util.Properties;
import javax.sql.DataSource;

public class C3p0DSFactory extends AbstractDSFactory {
  private static final long serialVersionUID = -6090788225842047281L;
  
  public static final String DS_NAME = "C3P0";
  
  public C3p0DSFactory() {
    this(null);
  }
  
  public C3p0DSFactory(Setting setting) {
    super("C3P0", ComboPooledDataSource.class, setting);
  }
  
  protected DataSource createDataSource(String jdbcUrl, String driver, String user, String pass, Setting poolSetting) {
    ComboPooledDataSource ds = new ComboPooledDataSource();
    Props connProps = new Props();
    for (String key : KEY_CONN_PROPS) {
      String connValue = poolSetting.getAndRemoveStr(new String[] { key });
      if (StrUtil.isNotBlank(connValue))
        connProps.setProperty(key, connValue); 
    } 
    if (MapUtil.isNotEmpty((Map)connProps))
      ds.setProperties((Properties)connProps); 
    ds.setJdbcUrl(jdbcUrl);
    try {
      ds.setDriverClass(driver);
    } catch (PropertyVetoException e) {
      throw new DbRuntimeException(e);
    } 
    ds.setUser(user);
    ds.setPassword(pass);
    poolSetting.toBean(ds);
    return (DataSource)ds;
  }
}
