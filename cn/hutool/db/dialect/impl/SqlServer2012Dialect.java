package cn.hutool.db.dialect.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.db.Page;
import cn.hutool.db.dialect.DialectName;
import cn.hutool.db.sql.SqlBuilder;

public class SqlServer2012Dialect extends AnsiSqlDialect {
  private static final long serialVersionUID = -37598166015777797L;
  
  protected SqlBuilder wrapPageSql(SqlBuilder find, Page page) {
    if (false == StrUtil.containsIgnoreCase(find.toString(), "order by"))
      find.append(" order by current_timestamp"); 
    return find.append(" offset ")
      .append(Integer.valueOf(page.getStartPosition()))
      .append(" row fetch next ")
      .append(Integer.valueOf(page.getPageSize()))
      .append(" row only");
  }
  
  public String dialectName() {
    return DialectName.SQLSERVER2012.name();
  }
}
