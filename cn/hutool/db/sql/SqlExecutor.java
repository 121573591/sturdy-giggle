package cn.hutool.db.sql;

import cn.hutool.core.collection.ArrayIter;
import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.lang.func.Func1;
import cn.hutool.db.DbUtil;
import cn.hutool.db.StatementUtil;
import cn.hutool.db.handler.RsHandler;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

public class SqlExecutor {
  public static int execute(Connection conn, String sql, Map<String, Object> paramMap) throws SQLException {
    NamedSql namedSql = new NamedSql(sql, paramMap);
    return execute(conn, namedSql.getSql(), namedSql.getParams());
  }
  
  public static int execute(Connection conn, String sql, Object... params) throws SQLException {
    PreparedStatement ps = null;
    try {
      ps = StatementUtil.prepareStatement(false, conn, sql, params);
      return ps.executeUpdate();
    } finally {
      DbUtil.close(new Object[] { ps });
    } 
  }
  
  public static boolean call(Connection conn, String sql, Object... params) throws SQLException {
    CallableStatement call = null;
    try {
      call = StatementUtil.prepareCall(conn, sql, params);
      return call.execute();
    } finally {
      DbUtil.close(new Object[] { call });
    } 
  }
  
  public static ResultSet callQuery(Connection conn, String sql, Object... params) throws SQLException {
    return StatementUtil.prepareCall(conn, sql, params).executeQuery();
  }
  
  public static Long executeForGeneratedKey(Connection conn, String sql, Map<String, Object> paramMap) throws SQLException {
    NamedSql namedSql = new NamedSql(sql, paramMap);
    return executeForGeneratedKey(conn, namedSql.getSql(), namedSql.getParams());
  }
  
  public static Long executeForGeneratedKey(Connection conn, String sql, Object... params) throws SQLException {
    PreparedStatement ps = null;
    ResultSet rs = null;
    try {
      ps = StatementUtil.prepareStatement(true, conn, sql, params);
      ps.executeUpdate();
      rs = ps.getGeneratedKeys();
      if (rs != null && rs.next())
        try {
          return Long.valueOf(rs.getLong(1));
        } catch (SQLException sQLException) {} 
      return null;
    } finally {
      DbUtil.close(new Object[] { ps });
      DbUtil.close(new Object[] { rs });
    } 
  }
  
  @Deprecated
  public static int[] executeBatch(Connection conn, String sql, Object[]... paramsBatch) throws SQLException {
    return executeBatch(conn, sql, (Iterable<Object[]>)new ArrayIter((Object[])paramsBatch));
  }
  
  public static int[] executeBatch(Connection conn, String sql, Iterable<Object[]> paramsBatch) throws SQLException {
    PreparedStatement ps = null;
    try {
      ps = StatementUtil.prepareStatementForBatch(conn, sql, paramsBatch);
      return ps.executeBatch();
    } finally {
      DbUtil.close(new Object[] { ps });
    } 
  }
  
  public static int[] executeBatch(Connection conn, String... sqls) throws SQLException {
    return executeBatch(conn, (Iterable<String>)new ArrayIter((Object[])sqls));
  }
  
  public static int[] executeBatch(Connection conn, Iterable<String> sqls) throws SQLException {
    Statement statement = null;
    try {
      statement = conn.createStatement();
      for (String sql : sqls)
        statement.addBatch(sql); 
      return statement.executeBatch();
    } finally {
      DbUtil.close(new Object[] { statement });
    } 
  }
  
  public static <T> T query(Connection conn, String sql, RsHandler<T> rsh, Map<String, Object> paramMap) throws SQLException {
    NamedSql namedSql = new NamedSql(sql, paramMap);
    return query(conn, namedSql.getSql(), rsh, namedSql.getParams());
  }
  
  public static <T> T query(Connection conn, SqlBuilder sqlBuilder, RsHandler<T> rsh) throws SQLException {
    return query(conn, sqlBuilder.build(), rsh, sqlBuilder.getParamValueArray());
  }
  
  public static <T> T query(Connection conn, String sql, RsHandler<T> rsh, Object... params) throws SQLException {
    PreparedStatement ps = null;
    try {
      ps = StatementUtil.prepareStatement(false, conn, sql, params);
      return (T)executeQuery(ps, (RsHandler)rsh);
    } finally {
      DbUtil.close(new Object[] { ps });
    } 
  }
  
  public static <T> T query(Connection conn, Func1<Connection, PreparedStatement> statementFunc, RsHandler<T> rsh) throws SQLException {
    PreparedStatement ps = null;
    try {
      ps = (PreparedStatement)statementFunc.call(conn);
      return (T)executeQuery(ps, (RsHandler)rsh);
    } catch (Exception e) {
      if (e instanceof SQLException)
        throw (SQLException)e; 
      throw ExceptionUtil.wrapRuntime(e);
    } finally {
      DbUtil.close(new Object[] { ps });
    } 
  }
  
  public static int executeUpdate(PreparedStatement ps, Object... params) throws SQLException {
    StatementUtil.fillParams(ps, params);
    return ps.executeUpdate();
  }
  
  public static boolean execute(PreparedStatement ps, Object... params) throws SQLException {
    StatementUtil.fillParams(ps, params);
    return ps.execute();
  }
  
  public static <T> T query(PreparedStatement ps, RsHandler<T> rsh, Object... params) throws SQLException {
    StatementUtil.fillParams(ps, params);
    return executeQuery(ps, rsh);
  }
  
  public static <T> T queryAndClosePs(PreparedStatement ps, RsHandler<T> rsh, Object... params) throws SQLException {
    try {
      return (T)query(ps, (RsHandler)rsh, params);
    } finally {
      DbUtil.close(new Object[] { ps });
    } 
  }
  
  private static <T> T executeQuery(PreparedStatement ps, RsHandler<T> rsh) throws SQLException {
    ResultSet rs = null;
    try {
      rs = ps.executeQuery();
      return (T)rsh.handle(rs);
    } finally {
      DbUtil.close(new Object[] { rs });
    } 
  }
}
