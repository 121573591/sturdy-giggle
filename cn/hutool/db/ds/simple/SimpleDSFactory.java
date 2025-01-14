package cn.hutool.db.ds.simple;

import cn.hutool.db.ds.AbstractDSFactory;
import cn.hutool.setting.Setting;
import java.util.Properties;
import javax.sql.DataSource;

public class SimpleDSFactory extends AbstractDSFactory {
  private static final long serialVersionUID = 4738029988261034743L;
  
  public static final String DS_NAME = "Hutool-Simple-DataSource";
  
  public SimpleDSFactory() {
    this(null);
  }
  
  public SimpleDSFactory(Setting setting) {
    super("Hutool-Simple-DataSource", SimpleDataSource.class, setting);
  }
  
  protected DataSource createDataSource(String jdbcUrl, String driver, String user, String pass, Setting poolSetting) {
    SimpleDataSource ds = new SimpleDataSource(jdbcUrl, user, pass, driver);
    ds.setConnProps((Properties)poolSetting.getProps(""));
    return ds;
  }
}
