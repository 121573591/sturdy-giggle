package cn.hutool.db.ds.bee;

import cn.beecp.BeeDataSource;
import cn.beecp.BeeDataSourceConfig;
import cn.hutool.core.util.StrUtil;
import cn.hutool.db.ds.AbstractDSFactory;
import cn.hutool.setting.Setting;
import javax.sql.DataSource;

public class BeeDSFactory extends AbstractDSFactory {
  private static final long serialVersionUID = 1L;
  
  public static final String DS_NAME = "BeeCP";
  
  public BeeDSFactory() {
    this(null);
  }
  
  public BeeDSFactory(Setting setting) {
    super("BeeCP", BeeDataSource.class, setting);
  }
  
  protected DataSource createDataSource(String jdbcUrl, String driver, String user, String pass, Setting poolSetting) {
    BeeDataSourceConfig beeConfig = new BeeDataSourceConfig(driver, jdbcUrl, user, pass);
    poolSetting.toBean(beeConfig);
    for (String key : KEY_CONN_PROPS) {
      String connValue = poolSetting.getAndRemoveStr(new String[] { key });
      if (StrUtil.isNotBlank(connValue))
        beeConfig.addConnectProperty(key, connValue); 
    } 
    return (DataSource)new BeeDataSource(beeConfig);
  }
}
