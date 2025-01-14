package cn.hutool.db.sql;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import cn.hutool.log.level.Level;

public enum SqlLog {
  INSTANCE;
  
  private Level level;
  
  private boolean showParams;
  
  private boolean formatSql;
  
  private boolean showSql;
  
  private static final Log log;
  
  public static final String KEY_SQL_LEVEL = "sqlLevel";
  
  public static final String KEY_SHOW_PARAMS = "showParams";
  
  public static final String KEY_FORMAT_SQL = "formatSql";
  
  public static final String KEY_SHOW_SQL = "showSql";
  
  SqlLog() {
    this.level = Level.DEBUG;
  }
  
  static {
    log = LogFactory.get();
  }
  
  public void init(boolean isShowSql, boolean isFormatSql, boolean isShowParams, Level level) {
    this.showSql = isShowSql;
    this.formatSql = isFormatSql;
    this.showParams = isShowParams;
    this.level = level;
  }
  
  public void log(String sql) {
    log(sql, null);
  }
  
  public void logForBatch(String sql) {
    if (this.showSql)
      log.log(this.level, "\n[Batch SQL] -> {}", new Object[] { this.formatSql ? SqlFormatter.format(sql) : sql }); 
  }
  
  public void log(String sql, Object paramValues) {
    if (this.showSql)
      if (null != paramValues && this.showParams) {
        log.log(this.level, "\n[SQL] -> {}\nParams -> {}", new Object[] { this.formatSql ? SqlFormatter.format(sql) : sql, paramValues });
      } else {
        log.log(this.level, "\n[SQL] -> {}", new Object[] { this.formatSql ? SqlFormatter.format(sql) : sql });
      }  
  }
}
