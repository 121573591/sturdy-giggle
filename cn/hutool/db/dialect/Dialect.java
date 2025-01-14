package cn.hutool.db.dialect;

import cn.hutool.db.Entity;
import cn.hutool.db.Page;
import cn.hutool.db.sql.Query;
import cn.hutool.db.sql.SqlBuilder;
import cn.hutool.db.sql.Wrapper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface Dialect extends Serializable {
  Wrapper getWrapper();
  
  void setWrapper(Wrapper paramWrapper);
  
  PreparedStatement psForInsert(Connection paramConnection, Entity paramEntity) throws SQLException;
  
  PreparedStatement psForInsertBatch(Connection paramConnection, Entity... paramVarArgs) throws SQLException;
  
  PreparedStatement psForDelete(Connection paramConnection, Query paramQuery) throws SQLException;
  
  PreparedStatement psForUpdate(Connection paramConnection, Entity paramEntity, Query paramQuery) throws SQLException;
  
  PreparedStatement psForFind(Connection paramConnection, Query paramQuery) throws SQLException;
  
  PreparedStatement psForPage(Connection paramConnection, Query paramQuery) throws SQLException;
  
  PreparedStatement psForPage(Connection paramConnection, SqlBuilder paramSqlBuilder, Page paramPage) throws SQLException;
  
  default PreparedStatement psForCount(Connection conn, Query query) throws SQLException {
    return psForCount(conn, SqlBuilder.create().query(query));
  }
  
  default PreparedStatement psForCount(Connection conn, SqlBuilder sqlBuilder) throws SQLException {
    sqlBuilder = sqlBuilder.insertPreFragment("SELECT count(*) from(").append(") hutool_alias_count_");
    return psForPage(conn, sqlBuilder, null);
  }
  
  PreparedStatement psForUpsert(Connection conn, Entity entity, String... keys) throws SQLException {
    throw new SQLException("Unsupported upsert operation of " + dialectName());
  }
  
  String dialectName();
}
