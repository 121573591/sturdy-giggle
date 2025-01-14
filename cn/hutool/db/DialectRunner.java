package cn.hutool.db;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.lang.PatternPool;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.db.dialect.Dialect;
import cn.hutool.db.dialect.DialectFactory;
import cn.hutool.db.handler.NumberHandler;
import cn.hutool.db.handler.RsHandler;
import cn.hutool.db.sql.Query;
import cn.hutool.db.sql.SqlBuilder;
import cn.hutool.db.sql.SqlExecutor;
import cn.hutool.db.sql.SqlUtil;
import cn.hutool.db.sql.Wrapper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DialectRunner implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private Dialect dialect;
  
  protected boolean caseInsensitive = GlobalDbConfig.caseInsensitive;
  
  public DialectRunner(Dialect dialect) {
    this.dialect = dialect;
  }
  
  public DialectRunner(String driverClassName) {
    this(DialectFactory.newDialect(driverClassName));
  }
  
  public int[] insert(Connection conn, Entity... records) throws SQLException {
    checkConn(conn);
    if (ArrayUtil.isEmpty((Object[])records))
      return new int[] { 0 }; 
    PreparedStatement ps = null;
    try {
      if (1 == records.length) {
        ps = this.dialect.psForInsert(conn, records[0]);
        return new int[] { ps.executeUpdate() };
      } 
      ps = this.dialect.psForInsertBatch(conn, records);
      return ps.executeBatch();
    } finally {
      DbUtil.close(new Object[] { ps });
    } 
  }
  
  public int upsert(Connection conn, Entity record, String... keys) throws SQLException {
    PreparedStatement ps = null;
    try {
      ps = getDialect().psForUpsert(conn, record, keys);
    } catch (SQLException sQLException) {}
    if (null != ps)
      try {
        return ps.executeUpdate();
      } finally {
        DbUtil.close(new Object[] { ps });
      }  
    return insertOrUpdate(conn, record, keys);
  }
  
  public int insertOrUpdate(Connection conn, Entity record, String... keys) throws SQLException {
    Entity where = record.filter(keys);
    if (MapUtil.isNotEmpty((Map)where) && count(conn, where) > 0L)
      return update(conn, record.removeNew(keys), where); 
    return insert(conn, new Entity[] { record })[0];
  }
  
  public <T> T insert(Connection conn, Entity record, RsHandler<T> generatedKeysHandler) throws SQLException {
    checkConn(conn);
    if (MapUtil.isEmpty((Map)record))
      throw new SQLException("Empty entity provided!"); 
    PreparedStatement ps = null;
    try {
      ps = this.dialect.psForInsert(conn, record);
      ps.executeUpdate();
      if (null == generatedKeysHandler)
        return null; 
      return (T)StatementUtil.getGeneratedKeys(ps, (RsHandler)generatedKeysHandler);
    } finally {
      DbUtil.close(new Object[] { ps });
    } 
  }
  
  public int del(Connection conn, Entity where) throws SQLException {
    checkConn(conn);
    if (MapUtil.isEmpty((Map)where))
      throw new SQLException("Empty entity provided!"); 
    PreparedStatement ps = null;
    try {
      ps = this.dialect.psForDelete(conn, Query.of(where));
      return ps.executeUpdate();
    } finally {
      DbUtil.close(new Object[] { ps });
    } 
  }
  
  public int update(Connection conn, Entity record, Entity where) throws SQLException {
    checkConn(conn);
    if (MapUtil.isEmpty((Map)record))
      throw new SQLException("Empty entity provided!"); 
    if (MapUtil.isEmpty((Map)where))
      throw new SQLException("Empty where provided!"); 
    String tableName = record.getTableName();
    if (StrUtil.isBlank(tableName)) {
      tableName = where.getTableName();
      record.setTableName(tableName);
    } 
    Query query = new Query(SqlUtil.buildConditions(where), new String[] { tableName });
    PreparedStatement ps = null;
    try {
      ps = this.dialect.psForUpdate(conn, record, query);
      return ps.executeUpdate();
    } finally {
      DbUtil.close(new Object[] { ps });
    } 
  }
  
  public <T> T find(Connection conn, Query query, RsHandler<T> rsh) throws SQLException {
    checkConn(conn);
    Assert.notNull(query, "[query] is null !", new Object[0]);
    return (T)SqlExecutor.queryAndClosePs(this.dialect.psForFind(conn, query), rsh, new Object[0]);
  }
  
  public long count(Connection conn, Entity where) throws SQLException {
    checkConn(conn);
    return ((Number)SqlExecutor.queryAndClosePs(this.dialect.psForCount(conn, Query.of(where)), (RsHandler)new NumberHandler(), new Object[0])).longValue();
  }
  
  public long count(Connection conn, SqlBuilder sqlBuilder) throws SQLException {
    checkConn(conn);
    String selectSql = sqlBuilder.build();
    Pattern pattern = PatternPool.get("(.*?)[\\s]order[\\s]by[\\s][^\\s]+\\s(asc|desc)?", 2);
    Matcher matcher = pattern.matcher(selectSql);
    if (matcher.matches())
      selectSql = matcher.group(1); 
    return ((Number)SqlExecutor.queryAndClosePs(this.dialect.psForCount(conn, 
          SqlBuilder.of(selectSql).addParams(sqlBuilder.getParamValueArray())), (RsHandler)new NumberHandler(), new Object[0]))
      .longValue();
  }
  
  public <T> T page(Connection conn, Query query, RsHandler<T> rsh) throws SQLException {
    checkConn(conn);
    if (null == query.getPage())
      return find(conn, query, rsh); 
    return (T)SqlExecutor.queryAndClosePs(this.dialect.psForPage(conn, query), rsh, new Object[0]);
  }
  
  public <T> T page(Connection conn, SqlBuilder sqlBuilder, Page page, RsHandler<T> rsh) throws SQLException {
    checkConn(conn);
    if (null == page)
      return (T)SqlExecutor.query(conn, sqlBuilder, rsh); 
    return (T)SqlExecutor.queryAndClosePs(this.dialect.psForPage(conn, sqlBuilder, page), rsh, new Object[0]);
  }
  
  public void setCaseInsensitive(boolean caseInsensitive) {
    this.caseInsensitive = caseInsensitive;
  }
  
  public Dialect getDialect() {
    return this.dialect;
  }
  
  public void setDialect(Dialect dialect) {
    this.dialect = dialect;
  }
  
  public void setWrapper(Character wrapperChar) {
    setWrapper(new Wrapper(wrapperChar));
  }
  
  public void setWrapper(Wrapper wrapper) {
    this.dialect.setWrapper(wrapper);
  }
  
  private void checkConn(Connection conn) {
    Assert.notNull(conn, "Connection object must be not null!", new Object[0]);
  }
}
