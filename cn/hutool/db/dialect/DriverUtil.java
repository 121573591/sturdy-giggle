package cn.hutool.db.dialect;

import cn.hutool.core.util.StrUtil;
import cn.hutool.db.DbRuntimeException;
import cn.hutool.db.DbUtil;
import cn.hutool.db.ds.DataSourceWrapper;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import javax.sql.DataSource;

public class DriverUtil {
  public static String identifyDriver(String nameContainsProductInfo) {
    return DialectFactory.identifyDriver(nameContainsProductInfo);
  }
  
  public static String identifyDriver(DataSource ds) {
    String driver;
    if (ds instanceof DataSourceWrapper) {
      String str = ((DataSourceWrapper)ds).getDriver();
      if (StrUtil.isNotBlank(str))
        return str; 
    } 
    Connection conn = null;
    try {
      try {
        conn = ds.getConnection();
      } catch (SQLException e) {
        throw new DbRuntimeException("Get Connection error !", e);
      } catch (NullPointerException e) {
        throw new DbRuntimeException("Unexpected NullPointException, maybe [jdbcUrl] or [url] is empty!", e);
      } 
      driver = identifyDriver(conn);
    } finally {
      DbUtil.close(new Object[] { conn });
    } 
    return driver;
  }
  
  public static String identifyDriver(Connection conn) throws DbRuntimeException {
    String driver;
    try {
      DatabaseMetaData meta = conn.getMetaData();
      driver = identifyDriver(meta.getDatabaseProductName());
      if (StrUtil.isBlank(driver))
        driver = identifyDriver(meta.getDriverName()); 
    } catch (SQLException e) {
      throw new DbRuntimeException("Identify driver error!", e);
    } 
    return driver;
  }
}
