package cn.hutool.db.dialect;

import cn.hutool.core.map.SafeConcurrentHashMap;
import cn.hutool.core.util.ClassLoaderUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.db.dialect.impl.AnsiSqlDialect;
import cn.hutool.db.dialect.impl.H2Dialect;
import cn.hutool.db.dialect.impl.MysqlDialect;
import cn.hutool.db.dialect.impl.OracleDialect;
import cn.hutool.db.dialect.impl.PhoenixDialect;
import cn.hutool.db.dialect.impl.PostgresqlDialect;
import cn.hutool.db.dialect.impl.SqlServer2012Dialect;
import cn.hutool.db.dialect.impl.Sqlite3Dialect;
import cn.hutool.log.StaticLog;
import java.sql.Connection;
import java.util.Map;
import javax.sql.DataSource;

public class DialectFactory implements DriverNamePool {
  private static final Map<DataSource, Dialect> DIALECT_POOL = (Map<DataSource, Dialect>)new SafeConcurrentHashMap();
  
  public static Dialect newDialect(String driverName) {
    Dialect dialect = internalNewDialect(driverName);
    StaticLog.debug("Use Dialect: [{}].", new Object[] { dialect.getClass().getSimpleName() });
    return dialect;
  }
  
  private static Dialect internalNewDialect(String driverName) {
    if (StrUtil.isNotBlank(driverName)) {
      if ("com.mysql.jdbc.Driver".equalsIgnoreCase(driverName) || "com.mysql.cj.jdbc.Driver".equalsIgnoreCase(driverName))
        return (Dialect)new MysqlDialect(); 
      if ("oracle.jdbc.OracleDriver".equalsIgnoreCase(driverName) || "oracle.jdbc.driver.OracleDriver".equalsIgnoreCase(driverName))
        return (Dialect)new OracleDialect(); 
      if ("org.sqlite.JDBC".equalsIgnoreCase(driverName))
        return (Dialect)new Sqlite3Dialect(); 
      if ("org.postgresql.Driver".equalsIgnoreCase(driverName))
        return (Dialect)new PostgresqlDialect(); 
      if ("org.h2.Driver".equalsIgnoreCase(driverName))
        return (Dialect)new H2Dialect(); 
      if ("com.microsoft.sqlserver.jdbc.SQLServerDriver".equalsIgnoreCase(driverName))
        return (Dialect)new SqlServer2012Dialect(); 
      if ("org.apache.phoenix.jdbc.PhoenixDriver".equalsIgnoreCase(driverName))
        return (Dialect)new PhoenixDialect(); 
    } 
    return (Dialect)new AnsiSqlDialect();
  }
  
  public static String identifyDriver(String nameContainsProductInfo) {
    return identifyDriver(nameContainsProductInfo, null);
  }
  
  public static String identifyDriver(String nameContainsProductInfo, ClassLoader classLoader) {
    if (StrUtil.isBlank(nameContainsProductInfo))
      return null; 
    nameContainsProductInfo = StrUtil.cleanBlank(nameContainsProductInfo.toLowerCase());
    String name = ReUtil.getGroup1("jdbc:(.*?):", nameContainsProductInfo);
    if (StrUtil.isNotBlank(name))
      nameContainsProductInfo = name; 
    String driver = null;
    if (nameContainsProductInfo.contains("mysql") || nameContainsProductInfo.contains("cobar")) {
      driver = ClassLoaderUtil.isPresent("com.mysql.cj.jdbc.Driver", classLoader) ? "com.mysql.cj.jdbc.Driver" : "com.mysql.jdbc.Driver";
    } else if (nameContainsProductInfo.contains("oracle")) {
      driver = ClassLoaderUtil.isPresent("oracle.jdbc.OracleDriver", classLoader) ? "oracle.jdbc.OracleDriver" : "oracle.jdbc.driver.OracleDriver";
    } else if (nameContainsProductInfo.contains("postgresql")) {
      driver = "org.postgresql.Driver";
    } else if (nameContainsProductInfo.contains("sqlite")) {
      driver = "org.sqlite.JDBC";
    } else if (nameContainsProductInfo.contains("sqlserver") || nameContainsProductInfo.contains("microsoft")) {
      driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    } else if (nameContainsProductInfo.contains("hive2")) {
      driver = "org.apache.hive.jdbc.HiveDriver";
    } else if (nameContainsProductInfo.contains("hive")) {
      driver = "org.apache.hadoop.hive.jdbc.HiveDriver";
    } else if (nameContainsProductInfo.contains("h2")) {
      driver = "org.h2.Driver";
    } else if (nameContainsProductInfo.contains("derby")) {
      driver = "org.apache.derby.jdbc.AutoloadedDriver";
    } else if (nameContainsProductInfo.contains("hsqldb")) {
      driver = "org.hsqldb.jdbc.JDBCDriver";
    } else if (nameContainsProductInfo.contains("dm")) {
      driver = "dm.jdbc.driver.DmDriver";
    } else if (nameContainsProductInfo.contains("kingbase8")) {
      driver = "com.kingbase8.Driver";
    } else if (nameContainsProductInfo.contains("ignite")) {
      driver = "org.apache.ignite.IgniteJdbcThinDriver";
    } else if (nameContainsProductInfo.contains("clickhouse")) {
      driver = "com.clickhouse.jdbc.ClickHouseDriver";
    } else if (nameContainsProductInfo.contains("highgo")) {
      driver = "com.highgo.jdbc.Driver";
    } else if (nameContainsProductInfo.contains("db2")) {
      driver = "com.ibm.db2.jdbc.app.DB2Driver";
    } else if (nameContainsProductInfo.contains("xugu")) {
      driver = "com.xugu.cloudjdbc.Driver";
    } else if (nameContainsProductInfo.contains("phoenix")) {
      driver = "org.apache.phoenix.jdbc.PhoenixDriver";
    } else if (nameContainsProductInfo.contains("zenith")) {
      driver = "com.huawei.gauss.jdbc.ZenithDriver";
    } else if (nameContainsProductInfo.contains("gbase")) {
      driver = "com.gbase.jdbc.Driver";
    } else if (nameContainsProductInfo.contains("oscar")) {
      driver = "com.oscar.Driver";
    } else if (nameContainsProductInfo.contains("sybase")) {
      driver = "com.sybase.jdbc4.jdbc.SybDriver";
    } else if (nameContainsProductInfo.contains("mariadb")) {
      driver = "org.mariadb.jdbc.Driver";
    } else if (nameContainsProductInfo.contains("opengauss")) {
      driver = "org.opengauss.Driver";
    } 
    return driver;
  }
  
  public static Dialect getDialect(DataSource ds) {
    Dialect dialect = DIALECT_POOL.get(ds);
    if (null == dialect)
      synchronized (ds) {
        dialect = DIALECT_POOL.computeIfAbsent(ds, DialectFactory::newDialect);
      }  
    return dialect;
  }
  
  public static Dialect newDialect(DataSource ds) {
    return newDialect(DriverUtil.identifyDriver(ds));
  }
  
  public static Dialect newDialect(Connection conn) {
    return newDialect(DriverUtil.identifyDriver(conn));
  }
}
