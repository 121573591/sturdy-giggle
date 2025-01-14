package cn.hutool.db.sql;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.db.DbRuntimeException;
import cn.hutool.db.Entity;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class SqlUtil {
  public static String buildEqualsWhere(Entity entity, List<Object> paramValues) {
    if (null == entity || entity.isEmpty())
      return ""; 
    StringBuilder sb = new StringBuilder(" WHERE ");
    boolean isNotFirst = false;
    for (Map.Entry<String, Object> entry : (Iterable<Map.Entry<String, Object>>)entity.entrySet()) {
      if (isNotFirst) {
        sb.append(" and ");
      } else {
        isNotFirst = true;
      } 
      sb.append("`").append(entry.getKey()).append("`").append(" = ?");
      paramValues.add(entry.getValue());
    } 
    return sb.toString();
  }
  
  public static Condition[] buildConditions(Entity entity) {
    if (null == entity || entity.isEmpty())
      return null; 
    Condition[] conditions = new Condition[entity.size()];
    int i = 0;
    for (Map.Entry<String, Object> entry : (Iterable<Map.Entry<String, Object>>)entity.entrySet()) {
      Object value = entry.getValue();
      if (value instanceof Condition) {
        conditions[i++] = (Condition)value;
        continue;
      } 
      conditions[i++] = new Condition(entry.getKey(), value);
    } 
    return conditions;
  }
  
  public static String buildLikeValue(String value, Condition.LikeType likeType, boolean withLikeKeyword) {
    if (null == value)
      return null; 
    StringBuilder likeValue = StrUtil.builder(new CharSequence[] { withLikeKeyword ? "LIKE " : "" });
    switch (likeType) {
      case StartWith:
        likeValue.append(value).append('%');
        break;
      case EndWith:
        likeValue.append('%').append(value);
        break;
      case Contains:
        likeValue.append('%').append(value).append('%');
        break;
    } 
    return likeValue.toString();
  }
  
  public static String formatSql(String sql) {
    return SqlFormatter.format(sql);
  }
  
  public static String rowIdToString(RowId rowId) {
    return StrUtil.str(rowId.getBytes(), CharsetUtil.CHARSET_ISO_8859_1);
  }
  
  public static String clobToStr(Clob clob) {
    Reader reader = null;
    try {
      reader = clob.getCharacterStream();
      return IoUtil.read(reader);
    } catch (SQLException e) {
      throw new DbRuntimeException(e);
    } finally {
      IoUtil.close(reader);
    } 
  }
  
  public static String blobToStr(Blob blob, Charset charset) {
    InputStream in = null;
    try {
      in = blob.getBinaryStream();
      return IoUtil.read(in, charset);
    } catch (SQLException e) {
      throw new DbRuntimeException(e);
    } finally {
      IoUtil.close(in);
    } 
  }
  
  public static Blob createBlob(Connection conn, InputStream dataStream, boolean closeAfterUse) {
    Blob blob;
    OutputStream out = null;
    try {
      blob = conn.createBlob();
      out = blob.setBinaryStream(1L);
      IoUtil.copy(dataStream, out);
    } catch (SQLException e) {
      throw new DbRuntimeException(e);
    } finally {
      IoUtil.close(out);
      if (closeAfterUse)
        IoUtil.close(dataStream); 
    } 
    return blob;
  }
  
  public static Blob createBlob(Connection conn, byte[] data) {
    Blob blob;
    try {
      blob = conn.createBlob();
      blob.setBytes(0L, data);
    } catch (SQLException e) {
      throw new DbRuntimeException(e);
    } 
    return blob;
  }
  
  public static Date toSqlDate(Date date) {
    return new Date(date.getTime());
  }
  
  public static Timestamp toSqlTimestamp(Date date) {
    return new Timestamp(date.getTime());
  }
}
