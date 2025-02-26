package cn.hutool.db;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.io.IoUtil;
import cn.hutool.db.dialect.Dialect;
import cn.hutool.db.dialect.DialectFactory;
import cn.hutool.db.ds.DSFactory;
import cn.hutool.log.Log;
import cn.hutool.log.level.Level;
import cn.hutool.setting.Setting;
import java.sql.Connection;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public final class DbUtil {
  private static final Log log = Log.get();
  
  public static SqlConnRunner newSqlConnRunner(Dialect dialect) {
    return SqlConnRunner.create(dialect);
  }
  
  public static SqlConnRunner newSqlConnRunner(DataSource ds) {
    return SqlConnRunner.create(ds);
  }
  
  public static SqlConnRunner newSqlConnRunner(Connection conn) {
    return SqlConnRunner.create(DialectFactory.newDialect(conn));
  }
  
  public static Db use() {
    return Db.use();
  }
  
  public static Db use(DataSource ds) {
    return Db.use(ds);
  }
  
  public static Db use(DataSource ds, Dialect dialect) {
    return Db.use(ds, dialect);
  }
  
  public static Session newSession() {
    return Session.create(getDs());
  }
  
  public static Session newSession(DataSource ds) {
    return Session.create(ds);
  }
  
  public static void close(Object... objsToClose) {
    for (Object obj : objsToClose) {
      if (null != obj)
        if (obj instanceof AutoCloseable) {
          IoUtil.close((AutoCloseable)obj);
        } else {
          log.warn("Object {} not a ResultSet or Statement or PreparedStatement or Connection!", new Object[] { obj.getClass().getName() });
        }  
    } 
  }
  
  public static DataSource getDs() {
    return DSFactory.get();
  }
  
  public static DataSource getDs(String group) {
    return DSFactory.get(group);
  }
  
  public static DataSource getJndiDsWithLog(String jndiName) {
    try {
      return getJndiDs(jndiName);
    } catch (DbRuntimeException e) {
      log.error(e.getCause(), "Find JNDI datasource error!", new Object[0]);
      return null;
    } 
  }
  
  public static DataSource getJndiDs(String jndiName) {
    try {
      return (DataSource)(new InitialContext()).lookup(jndiName);
    } catch (NamingException e) {
      throw new DbRuntimeException(e);
    } 
  }
  
  public static void removeShowSqlParams(Setting setting) {
    setting.remove("showSql");
    setting.remove("formatSql");
    setting.remove("showParams");
    setting.remove("sqlLevel");
  }
  
  public static void setShowSqlGlobal(Setting setting) {
    boolean isShowSql = Convert.toBool(setting.remove("showSql"), Boolean.valueOf(false)).booleanValue();
    boolean isFormatSql = Convert.toBool(setting.remove("formatSql"), Boolean.valueOf(false)).booleanValue();
    boolean isShowParams = Convert.toBool(setting.remove("showParams"), Boolean.valueOf(false)).booleanValue();
    String sqlLevelStr = setting.remove("sqlLevel");
    if (null != sqlLevelStr)
      sqlLevelStr = sqlLevelStr.toUpperCase(); 
    Level level = (Level)Convert.toEnum(Level.class, sqlLevelStr, (Enum)Level.DEBUG);
    log.debug("Show sql: [{}], format sql: [{}], show params: [{}], level: [{}]", new Object[] { Boolean.valueOf(isShowSql), Boolean.valueOf(isFormatSql), Boolean.valueOf(isShowParams), level });
    setShowSqlGlobal(isShowSql, isFormatSql, isShowParams, level);
  }
  
  public static void setShowSqlGlobal(boolean isShowSql, boolean isFormatSql, boolean isShowParams, Level level) {
    GlobalDbConfig.setShowSql(isShowSql, isFormatSql, isShowParams, level);
  }
  
  public static void setCaseInsensitiveGlobal(boolean caseInsensitive) {
    GlobalDbConfig.setCaseInsensitive(caseInsensitive);
  }
  
  public static void setReturnGeneratedKeyGlobal(boolean returnGeneratedKey) {
    GlobalDbConfig.setReturnGeneratedKey(returnGeneratedKey);
  }
  
  public static void setDbSettingPathGlobal(String dbSettingPath) {
    GlobalDbConfig.setDbSettingPath(dbSettingPath);
  }
}
