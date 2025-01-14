package cn.hutool.db.dialect.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.db.Entity;
import cn.hutool.db.Page;
import cn.hutool.db.StatementUtil;
import cn.hutool.db.dialect.DialectName;
import cn.hutool.db.sql.SqlBuilder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class H2Dialect extends AnsiSqlDialect {
  private static final long serialVersionUID = 1490520247974768214L;
  
  public String dialectName() {
    return DialectName.H2.name();
  }
  
  protected SqlBuilder wrapPageSql(SqlBuilder find, Page page) {
    return find.append(" limit ").append(Integer.valueOf(page.getStartPosition())).append(" , ").append(Integer.valueOf(page.getPageSize()));
  }
  
  public PreparedStatement psForUpsert(Connection conn, Entity entity, String... keys) throws SQLException {
    Assert.notEmpty((Object[])keys, "Keys must be not empty for H2 MERGE SQL.", new Object[0]);
    SqlBuilder.validateEntity(entity);
    SqlBuilder builder = SqlBuilder.create(this.wrapper);
    StringBuilder fieldsPart = new StringBuilder();
    StringBuilder placeHolder = new StringBuilder();
    entity.forEach((field, value) -> {
          if (StrUtil.isNotBlank(field)) {
            if (fieldsPart.length() > 0) {
              fieldsPart.append(", ");
              placeHolder.append(", ");
            } 
            fieldsPart.append((null != this.wrapper) ? this.wrapper.wrap(field) : field);
            placeHolder.append("?");
            builder.addParams(new Object[] { value });
          } 
        });
    String tableName = entity.getTableName();
    if (null != this.wrapper) {
      tableName = this.wrapper.wrap(tableName);
      keys = this.wrapper.wrap(keys);
    } 
    builder.append("MERGE INTO ").append(tableName)
      
      .append(" (").append(fieldsPart)
      
      .append(") KEY(").append(ArrayUtil.join((Object[])keys, ", "))
      
      .append(") VALUES (").append(placeHolder).append(")");
    return StatementUtil.prepareStatement(conn, builder);
  }
}
