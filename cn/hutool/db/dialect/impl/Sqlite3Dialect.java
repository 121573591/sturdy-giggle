package cn.hutool.db.dialect.impl;

import cn.hutool.db.dialect.DialectName;

public class Sqlite3Dialect extends AnsiSqlDialect {
  private static final long serialVersionUID = -3527642408849291634L;
  
  public String dialectName() {
    return DialectName.SQLITE3.name();
  }
}
