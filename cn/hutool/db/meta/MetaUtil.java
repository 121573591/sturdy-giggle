package cn.hutool.db.meta;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.db.DbRuntimeException;
import cn.hutool.db.DbUtil;
import cn.hutool.db.Entity;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;

public class MetaUtil {
  public static List<String> getTables(DataSource ds) {
    return getTables(ds, new TableType[] { TableType.TABLE });
  }
  
  public static List<String> getTables(DataSource ds, TableType... types) {
    return getTables(ds, null, null, types);
  }
  
  public static List<String> getTables(DataSource ds, String schema, TableType... types) {
    return getTables(ds, schema, null, types);
  }
  
  public static List<String> getTables(DataSource ds, String schema, String tableName, TableType... types) {
    List<String> tables = new ArrayList<>();
    Connection conn = null;
    try {
      conn = ds.getConnection();
      String catalog = getCatalog(conn);
      if (null == schema)
        schema = getSchema(conn); 
      DatabaseMetaData metaData = conn.getMetaData();
      try (ResultSet rs = metaData.getTables(catalog, schema, tableName, Convert.toStrArray(types))) {
        if (null != rs)
          while (rs.next()) {
            String table = rs.getString("TABLE_NAME");
            if (StrUtil.isNotBlank(table))
              tables.add(table); 
          }  
      } 
    } catch (Exception e) {
      throw new DbRuntimeException("Get tables error!", e);
    } finally {
      DbUtil.close(new Object[] { conn });
    } 
    return tables;
  }
  
  public static String[] getColumnNames(ResultSet rs) throws DbRuntimeException {
    try {
      ResultSetMetaData rsmd = rs.getMetaData();
      int columnCount = rsmd.getColumnCount();
      String[] labelNames = new String[columnCount];
      for (int i = 0; i < labelNames.length; i++)
        labelNames[i] = rsmd.getColumnLabel(i + 1); 
      return labelNames;
    } catch (Exception e) {
      throw new DbRuntimeException("Get colunms error!", e);
    } 
  }
  
  public static String[] getColumnNames(DataSource ds, String tableName) {
    List<String> columnNames = new ArrayList<>();
    Connection conn = null;
    try {
      conn = ds.getConnection();
      String catalog = getCatalog(conn);
      String schema = getSchema(conn);
      DatabaseMetaData metaData = conn.getMetaData();
      try (ResultSet rs = metaData.getColumns(catalog, schema, tableName, null)) {
        if (null != rs)
          while (rs.next())
            columnNames.add(rs.getString("COLUMN_NAME"));  
      } 
      return columnNames.<String>toArray(new String[0]);
    } catch (Exception e) {
      throw new DbRuntimeException("Get columns error!", e);
    } finally {
      DbUtil.close(new Object[] { conn });
    } 
  }
  
  public static Entity createLimitedEntity(DataSource ds, String tableName) {
    String[] columnNames = getColumnNames(ds, tableName);
    return Entity.create(tableName).setFieldNames(columnNames);
  }
  
  public static Table getTableMeta(DataSource ds, String tableName) {
    return getTableMeta(ds, null, null, tableName);
  }
  
  public static Table getTableMeta(DataSource ds, String catalog, String schema, String tableName) {
    Table table = Table.create(tableName);
    Connection conn = null;
    try {
      conn = ds.getConnection();
      if (null == catalog)
        catalog = getCatalog(conn); 
      table.setCatalog(catalog);
      if (null == schema)
        schema = getSchema(conn); 
      table.setSchema(schema);
      DatabaseMetaData metaData = conn.getMetaData();
      try (ResultSet rs = metaData.getTables(catalog, schema, tableName, new String[] { TableType.TABLE.value() })) {
        if (null != rs && 
          rs.next())
          table.setComment(rs.getString("REMARKS")); 
      } 
      try (ResultSet rs = metaData.getPrimaryKeys(catalog, schema, tableName)) {
        if (null != rs)
          while (rs.next())
            table.addPk(rs.getString("COLUMN_NAME"));  
      } 
      try (ResultSet rs = metaData.getColumns(catalog, schema, tableName, null)) {
        if (null != rs)
          while (rs.next())
            table.setColumn(Column.create(table, rs));  
      } 
      try (ResultSet rs = metaData.getIndexInfo(catalog, schema, tableName, false, false)) {
        Map<String, IndexInfo> indexInfoMap = new LinkedHashMap<>();
        if (null != rs)
          while (rs.next()) {
            if (0 == rs.getShort("TYPE"))
              continue; 
            String indexName = rs.getString("INDEX_NAME");
            String key = StrUtil.join("&", new Object[] { tableName, indexName });
            IndexInfo indexInfo = indexInfoMap.get(key);
            if (null == indexInfo) {
              indexInfo = new IndexInfo(rs.getBoolean("NON_UNIQUE"), indexName, tableName, schema, catalog);
              indexInfoMap.put(key, indexInfo);
            } 
            indexInfo.getColumnIndexInfoList().add(ColumnIndexInfo.create(rs));
          }  
        table.setIndexInfoList(ListUtil.toList(indexInfoMap.values()));
      } 
    } catch (SQLException e) {
      throw new DbRuntimeException("Get columns error!", e);
    } finally {
      DbUtil.close(new Object[] { conn });
    } 
    return table;
  }
  
  @Deprecated
  public static String getCataLog(Connection conn) {
    return getCatalog(conn);
  }
  
  public static String getCatalog(Connection conn) {
    if (null == conn)
      return null; 
    try {
      return conn.getCatalog();
    } catch (SQLException sQLException) {
      return null;
    } 
  }
  
  public static String getSchema(Connection conn) {
    if (null == conn)
      return null; 
    try {
      return conn.getSchema();
    } catch (SQLException sQLException) {
      return null;
    } 
  }
}
