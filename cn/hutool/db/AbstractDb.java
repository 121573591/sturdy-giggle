package cn.hutool.db;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.func.Func1;
import cn.hutool.db.dialect.Dialect;
import cn.hutool.db.handler.BeanListHandler;
import cn.hutool.db.handler.EntityHandler;
import cn.hutool.db.handler.EntityListHandler;
import cn.hutool.db.handler.HandleHelper;
import cn.hutool.db.handler.NumberHandler;
import cn.hutool.db.handler.RsHandler;
import cn.hutool.db.handler.StringHandler;
import cn.hutool.db.sql.Condition;
import cn.hutool.db.sql.Query;
import cn.hutool.db.sql.SqlBuilder;
import cn.hutool.db.sql.SqlExecutor;
import cn.hutool.db.sql.SqlUtil;
import cn.hutool.db.sql.Wrapper;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;

public abstract class AbstractDb implements Serializable {
  private static final long serialVersionUID = 3858951941916349062L;
  
  protected final DataSource ds;
  
  protected Boolean isSupportTransaction = null;
  
  protected boolean caseInsensitive = GlobalDbConfig.caseInsensitive;
  
  protected SqlConnRunner runner;
  
  public AbstractDb(DataSource ds, Dialect dialect) {
    this.ds = ds;
    this.runner = new SqlConnRunner(dialect);
  }
  
  public List<Entity> query(String sql, Map<String, Object> params) throws SQLException {
    return query(sql, (RsHandler<List<Entity>>)new EntityListHandler(this.caseInsensitive), params);
  }
  
  public List<Entity> query(String sql, Object... params) throws SQLException {
    return query(sql, (RsHandler<List<Entity>>)new EntityListHandler(this.caseInsensitive), params);
  }
  
  public <T> List<T> query(String sql, Class<T> beanClass, Object... params) throws SQLException {
    return query(sql, (RsHandler<List<T>>)new BeanListHandler(beanClass), params);
  }
  
  public Entity queryOne(String sql, Object... params) throws SQLException {
    return query(sql, (RsHandler<Entity>)new EntityHandler(this.caseInsensitive), params);
  }
  
  public Number queryNumber(String sql, Object... params) throws SQLException {
    return query(sql, (RsHandler<Number>)new NumberHandler(), params);
  }
  
  public String queryString(String sql, Object... params) throws SQLException {
    return query(sql, (RsHandler<String>)new StringHandler(), params);
  }
  
  public <T> T query(String sql, RsHandler<T> rsh, Object... params) throws SQLException {
    Connection conn = null;
    try {
      conn = getConnection();
      return (T)SqlExecutor.query(conn, sql, rsh, params);
    } finally {
      closeConnection(conn);
    } 
  }
  
  public <T> T query(String sql, RsHandler<T> rsh, Map<String, Object> paramMap) throws SQLException {
    Connection conn = null;
    try {
      conn = getConnection();
      return (T)SqlExecutor.query(conn, sql, rsh, paramMap);
    } finally {
      closeConnection(conn);
    } 
  }
  
  public <T> T query(Func1<Connection, PreparedStatement> statementFunc, RsHandler<T> rsh) throws SQLException {
    Connection conn = null;
    try {
      conn = getConnection();
      return (T)SqlExecutor.query(conn, statementFunc, rsh);
    } finally {
      closeConnection(conn);
    } 
  }
  
  public int execute(String sql, Object... params) throws SQLException {
    Connection conn = null;
    try {
      conn = getConnection();
      return SqlExecutor.execute(conn, sql, params);
    } finally {
      closeConnection(conn);
    } 
  }
  
  public Long executeForGeneratedKey(String sql, Object... params) throws SQLException {
    Connection conn = null;
    try {
      conn = getConnection();
      return SqlExecutor.executeForGeneratedKey(conn, sql, params);
    } finally {
      closeConnection(conn);
    } 
  }
  
  @Deprecated
  public int[] executeBatch(String sql, Object[]... paramsBatch) throws SQLException {
    Connection conn = null;
    try {
      conn = getConnection();
      return SqlExecutor.executeBatch(conn, sql, paramsBatch);
    } finally {
      closeConnection(conn);
    } 
  }
  
  public int[] executeBatch(String sql, Iterable<Object[]> paramsBatch) throws SQLException {
    Connection conn = null;
    try {
      conn = getConnection();
      return SqlExecutor.executeBatch(conn, sql, paramsBatch);
    } finally {
      closeConnection(conn);
    } 
  }
  
  public int[] executeBatch(String... sqls) throws SQLException {
    Connection conn = null;
    try {
      conn = getConnection();
      return SqlExecutor.executeBatch(conn, sqls);
    } finally {
      closeConnection(conn);
    } 
  }
  
  public int[] executeBatch(Iterable<String> sqls) throws SQLException {
    Connection conn = null;
    try {
      conn = getConnection();
      return SqlExecutor.executeBatch(conn, sqls);
    } finally {
      closeConnection(conn);
    } 
  }
  
  public int insert(Entity record) throws SQLException {
    Connection conn = null;
    try {
      conn = getConnection();
      return this.runner.insert(conn, record);
    } finally {
      closeConnection(conn);
    } 
  }
  
  public int insertOrUpdate(Entity record, String... keys) throws SQLException {
    Connection conn = null;
    try {
      conn = getConnection();
      return this.runner.insertOrUpdate(conn, record, keys);
    } finally {
      closeConnection(conn);
    } 
  }
  
  public int upsert(Entity record, String... keys) throws SQLException {
    Connection conn = null;
    try {
      conn = getConnection();
      return this.runner.upsert(conn, record, keys);
    } finally {
      closeConnection(conn);
    } 
  }
  
  public int[] insert(Collection<Entity> records) throws SQLException {
    Connection conn = null;
    try {
      conn = getConnection();
      return this.runner.insert(conn, records);
    } finally {
      closeConnection(conn);
    } 
  }
  
  public List<Object> insertForGeneratedKeys(Entity record) throws SQLException {
    Connection conn = null;
    try {
      conn = getConnection();
      return this.runner.insertForGeneratedKeys(conn, record);
    } finally {
      closeConnection(conn);
    } 
  }
  
  public Long insertForGeneratedKey(Entity record) throws SQLException {
    Connection conn = null;
    try {
      conn = getConnection();
      return this.runner.insertForGeneratedKey(conn, record);
    } finally {
      closeConnection(conn);
    } 
  }
  
  public int del(String tableName, String field, Object value) throws SQLException {
    return del(Entity.create(tableName).set(field, value));
  }
  
  public int del(Entity where) throws SQLException {
    Connection conn = null;
    try {
      conn = getConnection();
      return this.runner.del(conn, where);
    } finally {
      closeConnection(conn);
    } 
  }
  
  public int update(Entity record, Entity where) throws SQLException {
    Connection conn = null;
    try {
      conn = getConnection();
      return this.runner.update(conn, record, where);
    } finally {
      closeConnection(conn);
    } 
  }
  
  public <T> Entity get(String tableName, String field, T value) throws SQLException {
    return get(Entity.create(tableName).set(field, value));
  }
  
  public Entity get(Entity where) throws SQLException {
    return find(where.getFieldNames(), where, (RsHandler<Entity>)new EntityHandler(this.caseInsensitive));
  }
  
  public <T> T find(Collection<String> fields, Entity where, RsHandler<T> rsh) throws SQLException {
    Connection conn = null;
    try {
      conn = getConnection();
      return (T)this.runner.find(conn, fields, where, (RsHandler)rsh);
    } finally {
      closeConnection(conn);
    } 
  }
  
  public List<Entity> find(Collection<String> fields, Entity where) throws SQLException {
    return find(fields, where, (RsHandler<List<Entity>>)new EntityListHandler(this.caseInsensitive));
  }
  
  public <T> T find(Query query, RsHandler<T> rsh) throws SQLException {
    Connection conn = null;
    try {
      conn = getConnection();
      return (T)this.runner.find(conn, query, (RsHandler)rsh);
    } finally {
      closeConnection(conn);
    } 
  }
  
  public <T> T find(Entity where, RsHandler<T> rsh, String... fields) throws SQLException {
    return find(CollUtil.newArrayList((Object[])fields), where, rsh);
  }
  
  public List<Entity> find(Entity where) throws SQLException {
    return find(where.getFieldNames(), where, (RsHandler<List<Entity>>)new EntityListHandler(this.caseInsensitive));
  }
  
  public <T> List<T> find(Entity where, Class<T> beanClass) throws SQLException {
    return find(where.getFieldNames(), where, (RsHandler<List<T>>)BeanListHandler.create(beanClass));
  }
  
  public List<Entity> findAll(Entity where) throws SQLException {
    return find(where, (RsHandler<List<Entity>>)new EntityListHandler(this.caseInsensitive), new String[0]);
  }
  
  public <T> List<T> findAll(Entity where, Class<T> beanClass) throws SQLException {
    return find(where, (RsHandler<List<T>>)BeanListHandler.create(beanClass), new String[0]);
  }
  
  public List<Entity> findAll(String tableName) throws SQLException {
    return findAll(Entity.create(tableName));
  }
  
  public List<Entity> findBy(String tableName, String field, Object value) throws SQLException {
    return findAll(Entity.create(tableName).set(field, value));
  }
  
  public List<Entity> findBy(String tableName, Condition... wheres) throws SQLException {
    Query query = new Query(wheres, new String[] { tableName });
    return find(query, (RsHandler<List<Entity>>)new EntityListHandler(this.caseInsensitive));
  }
  
  public List<Entity> findLike(String tableName, String field, String value, Condition.LikeType likeType) throws SQLException {
    return findAll(Entity.create(tableName).set(field, SqlUtil.buildLikeValue(value, likeType, true)));
  }
  
  public long count(Entity where) throws SQLException {
    Connection conn = null;
    try {
      conn = getConnection();
      return this.runner.count(conn, where);
    } finally {
      closeConnection(conn);
    } 
  }
  
  public long count(SqlBuilder sql) throws SQLException {
    Connection conn = null;
    try {
      conn = getConnection();
      return this.runner.count(conn, sql);
    } finally {
      closeConnection(conn);
    } 
  }
  
  public long count(CharSequence selectSql, Object... params) throws SQLException {
    Connection conn = null;
    try {
      conn = getConnection();
      return this.runner.count(conn, selectSql, params);
    } finally {
      closeConnection(conn);
    } 
  }
  
  public <T> T page(Collection<String> fields, Entity where, int page, int numPerPage, RsHandler<T> rsh) throws SQLException {
    Connection conn = null;
    try {
      conn = getConnection();
      return (T)this.runner.page(conn, fields, where, page, numPerPage, (RsHandler)rsh);
    } finally {
      closeConnection(conn);
    } 
  }
  
  public <T> T page(Entity where, int page, int numPerPage, RsHandler<T> rsh) throws SQLException {
    return page(where, new Page(page, numPerPage), rsh);
  }
  
  public List<Entity> pageForEntityList(Entity where, int page, int numPerPage) throws SQLException {
    return pageForEntityList(where, new Page(page, numPerPage));
  }
  
  public List<Entity> pageForEntityList(Entity where, Page page) throws SQLException {
    return page(where, page, (RsHandler<List<Entity>>)new EntityListHandler(this.caseInsensitive));
  }
  
  public <T> T page(Entity where, Page page, RsHandler<T> rsh) throws SQLException {
    return page(where.getFieldNames(), where, page, rsh);
  }
  
  public <T> T page(Collection<String> fields, Entity where, Page page, RsHandler<T> rsh) throws SQLException {
    Connection conn = null;
    try {
      conn = getConnection();
      return (T)this.runner.page(conn, fields, where, page, (RsHandler)rsh);
    } finally {
      closeConnection(conn);
    } 
  }
  
  public <T> T page(CharSequence sql, Page page, RsHandler<T> rsh, Object... params) throws SQLException {
    Connection conn = null;
    try {
      conn = getConnection();
      return (T)this.runner.page(conn, SqlBuilder.of(sql).addParams(params), page, (RsHandler)rsh);
    } finally {
      closeConnection(conn);
    } 
  }
  
  public <T> PageResult<T> page(CharSequence sql, Page page, Class<T> elementBeanType, Object... params) throws SQLException {
    PageResult<T> result = new PageResult<>(page.getPageNumber(), page.getPageSize(), (int)count(sql, params));
    return page(sql, page, rs -> (PageResult)HandleHelper.handleRsToBeanList(rs, result, elementBeanType), params);
  }
  
  public <T> T page(SqlBuilder sql, Page page, RsHandler<T> rsh) throws SQLException {
    Connection conn = null;
    try {
      conn = getConnection();
      return (T)this.runner.page(conn, sql, page, (RsHandler)rsh);
    } finally {
      closeConnection(conn);
    } 
  }
  
  public PageResult<Entity> page(CharSequence sql, Page page, Object... params) throws SQLException {
    Connection conn = null;
    try {
      conn = getConnection();
      return this.runner.page(conn, SqlBuilder.of(sql).addParams(params), page);
    } finally {
      closeConnection(conn);
    } 
  }
  
  public PageResult<Entity> page(Collection<String> fields, Entity where, int pageNumber, int pageSize) throws SQLException {
    return page(fields, where, new Page(pageNumber, pageSize));
  }
  
  public PageResult<Entity> page(Collection<String> fields, Entity where, Page page) throws SQLException {
    Connection conn = null;
    try {
      conn = getConnection();
      return this.runner.page(conn, fields, where, page);
    } finally {
      closeConnection(conn);
    } 
  }
  
  public PageResult<Entity> page(Entity where, int page, int numPerPage) throws SQLException {
    return page(where, new Page(page, numPerPage));
  }
  
  public PageResult<Entity> page(Entity where, Page page) throws SQLException {
    return page(where.getFieldNames(), where, page);
  }
  
  public void setCaseInsensitive(boolean caseInsensitive) {
    this.caseInsensitive = caseInsensitive;
  }
  
  public SqlConnRunner getRunner() {
    return this.runner;
  }
  
  public void setRunner(SqlConnRunner runner) {
    this.runner = runner;
  }
  
  public AbstractDb setWrapper(Character wrapperChar) {
    return setWrapper(new Wrapper(wrapperChar));
  }
  
  public AbstractDb setWrapper(Wrapper wrapper) {
    this.runner.setWrapper(wrapper);
    return this;
  }
  
  public AbstractDb disableWrapper() {
    return setWrapper((Wrapper)null);
  }
  
  protected void checkTransactionSupported(Connection conn) throws SQLException, DbRuntimeException {
    if (null == this.isSupportTransaction)
      this.isSupportTransaction = Boolean.valueOf(conn.getMetaData().supportsTransactions()); 
    if (false == this.isSupportTransaction.booleanValue())
      throw new DbRuntimeException("Transaction not supported for current database!"); 
  }
  
  public abstract Connection getConnection() throws SQLException;
  
  public abstract void closeConnection(Connection paramConnection);
}
