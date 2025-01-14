package cn.hutool.db.sql;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.NClob;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;

public class StatementWrapper implements PreparedStatement {
  private PreparedStatement rawStatement;
  
  public StatementWrapper(PreparedStatement rawStatement) {
    this.rawStatement = rawStatement;
  }
  
  public ResultSet executeQuery(String sql) throws SQLException {
    return this.rawStatement.executeQuery(sql);
  }
  
  public int executeUpdate(String sql) throws SQLException {
    return this.rawStatement.executeUpdate(sql);
  }
  
  public void close() throws SQLException {
    this.rawStatement.close();
  }
  
  public int getMaxFieldSize() throws SQLException {
    return this.rawStatement.getMaxFieldSize();
  }
  
  public void setMaxFieldSize(int max) throws SQLException {
    this.rawStatement.setMaxFieldSize(max);
  }
  
  public int getMaxRows() throws SQLException {
    return this.rawStatement.getMaxRows();
  }
  
  public void setMaxRows(int max) throws SQLException {
    this.rawStatement.setMaxRows(max);
  }
  
  public void setEscapeProcessing(boolean enable) throws SQLException {
    this.rawStatement.setEscapeProcessing(enable);
  }
  
  public int getQueryTimeout() throws SQLException {
    return this.rawStatement.getQueryTimeout();
  }
  
  public void setQueryTimeout(int seconds) throws SQLException {
    this.rawStatement.setQueryTimeout(seconds);
  }
  
  public void cancel() throws SQLException {
    this.rawStatement.cancel();
  }
  
  public SQLWarning getWarnings() throws SQLException {
    return this.rawStatement.getWarnings();
  }
  
  public void clearWarnings() throws SQLException {
    this.rawStatement.clearWarnings();
  }
  
  public void setCursorName(String name) throws SQLException {
    this.rawStatement.setCursorName(name);
  }
  
  public boolean execute(String sql) throws SQLException {
    return this.rawStatement.execute(sql);
  }
  
  public ResultSet getResultSet() throws SQLException {
    return this.rawStatement.getResultSet();
  }
  
  public int getUpdateCount() throws SQLException {
    return this.rawStatement.getUpdateCount();
  }
  
  public boolean getMoreResults() throws SQLException {
    return this.rawStatement.getMoreResults();
  }
  
  public void setFetchDirection(int direction) throws SQLException {
    this.rawStatement.setFetchDirection(direction);
  }
  
  public int getFetchDirection() throws SQLException {
    return this.rawStatement.getFetchDirection();
  }
  
  public void setFetchSize(int rows) throws SQLException {
    this.rawStatement.setFetchSize(rows);
  }
  
  public int getFetchSize() throws SQLException {
    return this.rawStatement.getFetchSize();
  }
  
  public int getResultSetConcurrency() throws SQLException {
    return this.rawStatement.getResultSetConcurrency();
  }
  
  public int getResultSetType() throws SQLException {
    return this.rawStatement.getResultSetType();
  }
  
  public void addBatch(String sql) throws SQLException {
    this.rawStatement.addBatch(sql);
  }
  
  public void clearBatch() throws SQLException {
    this.rawStatement.clearBatch();
  }
  
  public int[] executeBatch() throws SQLException {
    return this.rawStatement.executeBatch();
  }
  
  public Connection getConnection() throws SQLException {
    return this.rawStatement.getConnection();
  }
  
  public boolean getMoreResults(int current) throws SQLException {
    return this.rawStatement.getMoreResults(current);
  }
  
  public ResultSet getGeneratedKeys() throws SQLException {
    return this.rawStatement.getGeneratedKeys();
  }
  
  public int executeUpdate(String sql, int autoGeneratedKeys) throws SQLException {
    return this.rawStatement.executeUpdate(sql, autoGeneratedKeys);
  }
  
  public int executeUpdate(String sql, int[] columnIndexes) throws SQLException {
    return this.rawStatement.executeUpdate(sql, columnIndexes);
  }
  
  public int executeUpdate(String sql, String[] columnNames) throws SQLException {
    return this.rawStatement.executeUpdate(sql, columnNames);
  }
  
  public boolean execute(String sql, int autoGeneratedKeys) throws SQLException {
    return this.rawStatement.execute(sql, autoGeneratedKeys);
  }
  
  public boolean execute(String sql, int[] columnIndexes) throws SQLException {
    return this.rawStatement.execute(sql, columnIndexes);
  }
  
  public boolean execute(String sql, String[] columnNames) throws SQLException {
    return this.rawStatement.execute(sql, columnNames);
  }
  
  public int getResultSetHoldability() throws SQLException {
    return this.rawStatement.getResultSetHoldability();
  }
  
  public boolean isClosed() throws SQLException {
    return this.rawStatement.isClosed();
  }
  
  public void setPoolable(boolean poolable) throws SQLException {
    this.rawStatement.setPoolable(poolable);
  }
  
  public boolean isPoolable() throws SQLException {
    return this.rawStatement.isPoolable();
  }
  
  public void closeOnCompletion() throws SQLException {
    this.rawStatement.closeOnCompletion();
  }
  
  public boolean isCloseOnCompletion() throws SQLException {
    return this.rawStatement.isCloseOnCompletion();
  }
  
  public <T> T unwrap(Class<T> iface) throws SQLException {
    return this.rawStatement.unwrap(iface);
  }
  
  public boolean isWrapperFor(Class<?> iface) throws SQLException {
    return this.rawStatement.isWrapperFor(iface);
  }
  
  public ResultSet executeQuery() throws SQLException {
    return this.rawStatement.executeQuery();
  }
  
  public int executeUpdate() throws SQLException {
    return this.rawStatement.executeUpdate();
  }
  
  public void setNull(int parameterIndex, int sqlType) throws SQLException {
    this.rawStatement.setNull(parameterIndex, sqlType);
  }
  
  public void setBoolean(int parameterIndex, boolean x) throws SQLException {
    this.rawStatement.setBoolean(parameterIndex, x);
  }
  
  public void setByte(int parameterIndex, byte x) throws SQLException {
    this.rawStatement.setByte(parameterIndex, x);
  }
  
  public void setShort(int parameterIndex, short x) throws SQLException {
    this.rawStatement.setShort(parameterIndex, x);
  }
  
  public void setInt(int parameterIndex, int x) throws SQLException {
    this.rawStatement.setInt(parameterIndex, x);
  }
  
  public void setLong(int parameterIndex, long x) throws SQLException {
    this.rawStatement.setLong(parameterIndex, x);
  }
  
  public void setFloat(int parameterIndex, float x) throws SQLException {
    this.rawStatement.setFloat(parameterIndex, x);
  }
  
  public void setDouble(int parameterIndex, double x) throws SQLException {
    this.rawStatement.setDouble(parameterIndex, x);
  }
  
  public void setBigDecimal(int parameterIndex, BigDecimal x) throws SQLException {
    this.rawStatement.setBigDecimal(parameterIndex, x);
  }
  
  public void setString(int parameterIndex, String x) throws SQLException {
    this.rawStatement.setString(parameterIndex, x);
  }
  
  public void setBytes(int parameterIndex, byte[] x) throws SQLException {
    this.rawStatement.setBytes(parameterIndex, x);
  }
  
  public void setDate(int parameterIndex, Date x) throws SQLException {
    this.rawStatement.setDate(parameterIndex, x);
  }
  
  public void setTime(int parameterIndex, Time x) throws SQLException {
    this.rawStatement.setTime(parameterIndex, x);
  }
  
  public void setTimestamp(int parameterIndex, Timestamp x) throws SQLException {
    this.rawStatement.setTimestamp(parameterIndex, x);
  }
  
  public void setAsciiStream(int parameterIndex, InputStream x, int length) throws SQLException {
    this.rawStatement.setAsciiStream(parameterIndex, x, length);
  }
  
  @Deprecated
  public void setUnicodeStream(int parameterIndex, InputStream x, int length) throws SQLException {
    this.rawStatement.setUnicodeStream(parameterIndex, x, length);
  }
  
  public void setBinaryStream(int parameterIndex, InputStream x, int length) throws SQLException {
    this.rawStatement.setBinaryStream(parameterIndex, x, length);
  }
  
  public void clearParameters() throws SQLException {
    this.rawStatement.clearParameters();
  }
  
  public void setObject(int parameterIndex, Object x, int targetSqlType) throws SQLException {
    this.rawStatement.setObject(parameterIndex, x, targetSqlType, targetSqlType);
  }
  
  public void setObject(int parameterIndex, Object x) throws SQLException {
    this.rawStatement.setObject(parameterIndex, x);
  }
  
  public boolean execute() throws SQLException {
    return this.rawStatement.execute();
  }
  
  public void addBatch() throws SQLException {
    this.rawStatement.addBatch();
  }
  
  public void setCharacterStream(int parameterIndex, Reader reader, int length) throws SQLException {
    this.rawStatement.setCharacterStream(parameterIndex, reader, length);
  }
  
  public void setRef(int parameterIndex, Ref x) throws SQLException {
    this.rawStatement.setRef(parameterIndex, x);
  }
  
  public void setBlob(int parameterIndex, Blob x) throws SQLException {
    this.rawStatement.setBlob(parameterIndex, x);
  }
  
  public void setClob(int parameterIndex, Clob x) throws SQLException {
    this.rawStatement.setClob(parameterIndex, x);
  }
  
  public void setArray(int parameterIndex, Array x) throws SQLException {
    this.rawStatement.setArray(parameterIndex, x);
  }
  
  public ResultSetMetaData getMetaData() throws SQLException {
    return this.rawStatement.getMetaData();
  }
  
  public void setDate(int parameterIndex, Date x, Calendar cal) throws SQLException {
    this.rawStatement.setDate(parameterIndex, x, cal);
  }
  
  public void setTime(int parameterIndex, Time x, Calendar cal) throws SQLException {
    this.rawStatement.setTime(parameterIndex, x, cal);
  }
  
  public void setTimestamp(int parameterIndex, Timestamp x, Calendar cal) throws SQLException {
    this.rawStatement.setTimestamp(parameterIndex, x, cal);
  }
  
  public void setNull(int parameterIndex, int sqlType, String typeName) throws SQLException {
    this.rawStatement.setNull(parameterIndex, sqlType, typeName);
  }
  
  public void setURL(int parameterIndex, URL x) throws SQLException {
    this.rawStatement.setURL(parameterIndex, x);
  }
  
  public ParameterMetaData getParameterMetaData() throws SQLException {
    return this.rawStatement.getParameterMetaData();
  }
  
  public void setRowId(int parameterIndex, RowId x) throws SQLException {
    this.rawStatement.setRowId(parameterIndex, x);
  }
  
  public void setNString(int parameterIndex, String value) throws SQLException {
    this.rawStatement.setNString(parameterIndex, value);
  }
  
  public void setNCharacterStream(int parameterIndex, Reader value, long length) throws SQLException {
    this.rawStatement.setCharacterStream(parameterIndex, value, length);
  }
  
  public void setNClob(int parameterIndex, NClob value) throws SQLException {
    this.rawStatement.setNClob(parameterIndex, value);
  }
  
  public void setClob(int parameterIndex, Reader reader, long length) throws SQLException {
    this.rawStatement.setClob(parameterIndex, reader, length);
  }
  
  public void setBlob(int parameterIndex, InputStream inputStream, long length) throws SQLException {
    this.rawStatement.setBlob(parameterIndex, inputStream, length);
  }
  
  public void setNClob(int parameterIndex, Reader reader, long length) throws SQLException {
    this.rawStatement.setNClob(parameterIndex, reader, length);
  }
  
  public void setSQLXML(int parameterIndex, SQLXML xmlObject) throws SQLException {
    this.rawStatement.setSQLXML(parameterIndex, xmlObject);
  }
  
  public void setObject(int parameterIndex, Object x, int targetSqlType, int scaleOrLength) throws SQLException {
    this.rawStatement.setObject(parameterIndex, x, targetSqlType, scaleOrLength);
  }
  
  public void setAsciiStream(int parameterIndex, InputStream x, long length) throws SQLException {
    this.rawStatement.setAsciiStream(parameterIndex, x, length);
  }
  
  public void setBinaryStream(int parameterIndex, InputStream x, long length) throws SQLException {
    this.rawStatement.setBinaryStream(parameterIndex, x, length);
  }
  
  public void setCharacterStream(int parameterIndex, Reader reader, long length) throws SQLException {
    this.rawStatement.setCharacterStream(parameterIndex, reader, length);
  }
  
  public void setAsciiStream(int parameterIndex, InputStream x) throws SQLException {
    this.rawStatement.setAsciiStream(parameterIndex, x);
  }
  
  public void setBinaryStream(int parameterIndex, InputStream x) throws SQLException {
    this.rawStatement.setBinaryStream(parameterIndex, x);
  }
  
  public void setCharacterStream(int parameterIndex, Reader reader) throws SQLException {
    this.rawStatement.setCharacterStream(parameterIndex, reader);
  }
  
  public void setNCharacterStream(int parameterIndex, Reader value) throws SQLException {
    this.rawStatement.setNCharacterStream(parameterIndex, value);
  }
  
  public void setClob(int parameterIndex, Reader reader) throws SQLException {
    this.rawStatement.setClob(parameterIndex, reader);
  }
  
  public void setBlob(int parameterIndex, InputStream inputStream) throws SQLException {
    this.rawStatement.setBlob(parameterIndex, inputStream);
  }
  
  public void setNClob(int parameterIndex, Reader reader) throws SQLException {
    this.rawStatement.setNClob(parameterIndex, reader);
  }
}
