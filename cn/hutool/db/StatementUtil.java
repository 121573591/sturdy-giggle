package cn.hutool.db;

import cn.hutool.core.collection.ArrayIter;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.db.handler.HandleHelper;
import cn.hutool.db.handler.RsHandler;
import cn.hutool.db.sql.NamedSql;
import cn.hutool.db.sql.SqlBuilder;
import cn.hutool.db.sql.SqlLog;
import cn.hutool.db.sql.SqlUtil;
import java.lang.invoke.SerializedLambda;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatementUtil {
  public static PreparedStatement fillParams(PreparedStatement ps, Object... params) throws SQLException {
    if (ArrayUtil.isEmpty(params))
      return ps; 
    return fillParams(ps, (Iterable<?>)new ArrayIter(params));
  }
  
  public static PreparedStatement fillParams(PreparedStatement ps, Iterable<?> params) throws SQLException {
    return fillParams(ps, params, null);
  }
  
  public static PreparedStatement fillParams(PreparedStatement ps, Iterable<?> params, Map<Integer, Integer> nullTypeCache) throws SQLException {
    if (null == params)
      return ps; 
    int paramIndex = 1;
    for (Object param : params)
      setParam(ps, paramIndex++, param, nullTypeCache); 
    return ps;
  }
  
  public static PreparedStatement prepareStatement(Connection conn, SqlBuilder sqlBuilder) throws SQLException {
    return prepareStatement(conn, sqlBuilder.build(), sqlBuilder.getParamValueArray());
  }
  
  public static PreparedStatement prepareStatement(Connection conn, String sql, Collection<Object> params) throws SQLException {
    return prepareStatement(conn, sql, params.toArray(new Object[0]));
  }
  
  public static PreparedStatement prepareStatement(Connection conn, String sql, Object... params) throws SQLException {
    return prepareStatement(GlobalDbConfig.returnGeneratedKey, conn, sql, params);
  }
  
  public static PreparedStatement prepareStatement(boolean returnGeneratedKey, Connection conn, String sql, Object... params) throws SQLException {
    PreparedStatement ps;
    Assert.notBlank(sql, "Sql String must be not blank!", new Object[0]);
    sql = sql.trim();
    if (ArrayUtil.isNotEmpty(params) && 1 == params.length && params[0] instanceof Map) {
      NamedSql namedSql = new NamedSql(sql, Convert.toMap(String.class, Object.class, params[0]));
      sql = namedSql.getSql();
      params = namedSql.getParams();
    } 
    SqlLog.INSTANCE.log(sql, ArrayUtil.isEmpty(params) ? null : params);
    if (returnGeneratedKey && StrUtil.startWithIgnoreCase(sql, "insert")) {
      ps = conn.prepareStatement(sql, 1);
    } else {
      ps = conn.prepareStatement(sql);
    } 
    return fillParams(ps, params);
  }
  
  public static PreparedStatement prepareStatementForBatch(Connection conn, String sql, Object[]... paramsBatch) throws SQLException {
    return prepareStatementForBatch(conn, sql, (Iterable<Object[]>)new ArrayIter((Object[])paramsBatch));
  }
  
  public static PreparedStatement prepareStatementForBatch(Connection conn, String sql, Iterable<Object[]> paramsBatch) throws SQLException {
    Assert.notBlank(sql, "Sql String must be not blank!", new Object[0]);
    sql = sql.trim();
    SqlLog.INSTANCE.log(sql, paramsBatch);
    PreparedStatement ps = conn.prepareStatement(sql);
    Map<Integer, Integer> nullTypeMap = new HashMap<>();
    for (Object[] params : paramsBatch) {
      fillParams(ps, (Iterable<?>)new ArrayIter(params), nullTypeMap);
      ps.addBatch();
    } 
    return ps;
  }
  
  public static PreparedStatement prepareStatementForBatch(Connection conn, String sql, Iterable<String> fields, Entity... entities) throws SQLException {
    Assert.notBlank(sql, "Sql String must be not blank!", new Object[0]);
    sql = sql.trim();
    SqlLog.INSTANCE.logForBatch(sql);
    PreparedStatement ps = conn.prepareStatement(sql);
    Map<Integer, Integer> nullTypeMap = new HashMap<>();
    for (Entity entity : entities) {
      fillParams(ps, CollUtil.valuesOfKeys((Map)entity, fields), nullTypeMap);
      ps.addBatch();
    } 
    return ps;
  }
  
  public static CallableStatement prepareCall(Connection conn, String sql, Object... params) throws SQLException {
    Assert.notBlank(sql, "Sql String must be not blank!", new Object[0]);
    sql = sql.trim();
    SqlLog.INSTANCE.log(sql, params);
    CallableStatement call = conn.prepareCall(sql);
    fillParams(call, params);
    return call;
  }
  
  public static Long getGeneratedKeyOfLong(Statement ps) throws SQLException {
    return getGeneratedKeys(ps, rs -> {
          Long generatedKey = null;
          if (rs != null && rs.next())
            try {
              generatedKey = Long.valueOf(rs.getLong(1));
            } catch (SQLException sQLException) {} 
          return generatedKey;
        });
  }
  
  public static List<Object> getGeneratedKeys(Statement ps) throws SQLException {
    return getGeneratedKeys(ps, HandleHelper::handleRowToList);
  }
  
  public static <T> T getGeneratedKeys(Statement statement, RsHandler<T> rsHandler) throws SQLException {
    try (ResultSet rs = statement.getGeneratedKeys()) {
      return (T)rsHandler.handle(rs);
    } 
  }
  
  public static int getTypeOfNull(PreparedStatement ps, int paramIndex) {
    int sqlType = 12;
    try {
      ParameterMetaData pmd = ps.getParameterMetaData();
      sqlType = pmd.getParameterType(paramIndex);
    } catch (SQLException sQLException) {}
    return sqlType;
  }
  
  public static void setParam(PreparedStatement ps, int paramIndex, Object param) throws SQLException {
    setParam(ps, paramIndex, param, null);
  }
  
  private static void setParam(PreparedStatement ps, int paramIndex, Object param, Map<Integer, Integer> nullTypeCache) throws SQLException {
    if (null == param) {
      Integer type = (null == nullTypeCache) ? null : nullTypeCache.get(Integer.valueOf(paramIndex));
      if (null == type) {
        type = Integer.valueOf(getTypeOfNull(ps, paramIndex));
        if (null != nullTypeCache)
          nullTypeCache.put(Integer.valueOf(paramIndex), type); 
      } 
      ps.setNull(paramIndex, type.intValue());
    } 
    if (param instanceof Date) {
      if (param instanceof Date) {
        ps.setDate(paramIndex, (Date)param);
      } else if (param instanceof Time) {
        ps.setTime(paramIndex, (Time)param);
      } else {
        ps.setTimestamp(paramIndex, SqlUtil.toSqlTimestamp((Date)param));
      } 
      return;
    } 
    if (param instanceof Number) {
      if (param instanceof BigDecimal) {
        ps.setBigDecimal(paramIndex, (BigDecimal)param);
        return;
      } 
      if (param instanceof BigInteger) {
        ps.setBigDecimal(paramIndex, new BigDecimal((BigInteger)param));
        return;
      } 
    } 
    ps.setObject(paramIndex, param);
  }
}
