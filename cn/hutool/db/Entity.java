package cn.hutool.db;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.lang.func.Func0;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.db.sql.SqlUtil;
import java.nio.charset.Charset;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.RowId;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.Set;

public class Entity extends Dict {
  private static final long serialVersionUID = -1951012511464327448L;
  
  private String tableName;
  
  private Set<String> fieldNames;
  
  public static Entity create() {
    return new Entity();
  }
  
  public static Entity create(String tableName) {
    return new Entity(tableName);
  }
  
  public static <T> Entity parse(T bean) {
    return create((String)null).parseBean(bean);
  }
  
  public static <T> Entity parse(T bean, boolean isToUnderlineCase, boolean ignoreNullValue) {
    return create((String)null).parseBean(bean, isToUnderlineCase, ignoreNullValue);
  }
  
  public static <T> Entity parseWithUnderlineCase(T bean) {
    return create((String)null).parseBean(bean, true, true);
  }
  
  public Entity() {}
  
  public Entity(String tableName) {
    this.tableName = tableName;
  }
  
  public Entity(String tableName, boolean caseInsensitive) {
    super(caseInsensitive);
    this.tableName = tableName;
  }
  
  public String getTableName() {
    return this.tableName;
  }
  
  public Entity setTableName(String tableName) {
    this.tableName = tableName;
    return this;
  }
  
  public Set<String> getFieldNames() {
    return this.fieldNames;
  }
  
  public Entity setFieldNames(Collection<String> fieldNames) {
    if (CollectionUtil.isNotEmpty(fieldNames))
      this.fieldNames = CollectionUtil.newHashSet(true, fieldNames); 
    return this;
  }
  
  public Entity setFieldNames(String... fieldNames) {
    if (ArrayUtil.isNotEmpty((Object[])fieldNames))
      this.fieldNames = CollectionUtil.newLinkedHashSet((Object[])fieldNames); 
    return this;
  }
  
  public Entity setFields(Func0<?>... fields) {
    return (Entity)super.setFields((Func0[])fields);
  }
  
  public Entity addFieldNames(String... fieldNames) {
    if (ArrayUtil.isNotEmpty((Object[])fieldNames)) {
      if (null == this.fieldNames)
        return setFieldNames(fieldNames); 
      Collections.addAll(this.fieldNames, fieldNames);
    } 
    return this;
  }
  
  public <T> Entity parseBean(T bean) {
    if (StrUtil.isBlank(this.tableName))
      setTableName(StrUtil.lowerFirst(bean.getClass().getSimpleName())); 
    return (Entity)super.parseBean(bean);
  }
  
  public <T> Entity parseBean(T bean, boolean isToUnderlineCase, boolean ignoreNullValue) {
    if (StrUtil.isBlank(this.tableName)) {
      String simpleName = bean.getClass().getSimpleName();
      setTableName(isToUnderlineCase ? StrUtil.toUnderlineCase(simpleName) : StrUtil.lowerFirst(simpleName));
    } 
    return (Entity)super.parseBean(bean, isToUnderlineCase, ignoreNullValue);
  }
  
  public Entity filter(String... keys) {
    Entity result = new Entity(this.tableName);
    result.setFieldNames(this.fieldNames);
    for (String key : keys) {
      if (containsKey(key))
        result.put(key, get(key)); 
    } 
    return result;
  }
  
  public Entity removeNew(String... keys) {
    return (Entity)MapUtil.removeAny((Map)clone(), (Object[])keys);
  }
  
  public Entity set(String field, Object value) {
    return (Entity)super.set(field, value);
  }
  
  public Entity setIgnoreNull(String field, Object value) {
    return (Entity)super.setIgnoreNull(field, value);
  }
  
  public Clob getClob(String field) {
    return (Clob)get(field, null);
  }
  
  public Blob getBlob(String field) {
    return (Blob)get(field, null);
  }
  
  public Time getTime(String field) {
    Object obj = get(field);
    Time result = null;
    if (null != obj)
      try {
        result = (Time)obj;
      } catch (Exception e) {
        result = (Time)ReflectUtil.invoke(obj, "timeValue", new Object[0]);
      }  
    return result;
  }
  
  public Date getDate(String field) {
    Object obj = get(field);
    Date result = null;
    if (null != obj)
      try {
        result = (Date)obj;
      } catch (Exception e) {
        result = (Date)ReflectUtil.invoke(obj, "dateValue", new Object[0]);
      }  
    return result;
  }
  
  public Timestamp getTimestamp(String field) {
    Object obj = get(field);
    Timestamp result = null;
    if (null != obj)
      try {
        result = (Timestamp)obj;
      } catch (Exception e) {
        result = (Timestamp)ReflectUtil.invoke(obj, "timestampValue", new Object[0]);
      }  
    return result;
  }
  
  public String getStr(String field) {
    return getStr(field, CharsetUtil.CHARSET_UTF_8);
  }
  
  public String getStr(String field, Charset charset) {
    Object obj = get(field);
    if (obj instanceof Clob)
      return SqlUtil.clobToStr((Clob)obj); 
    if (obj instanceof Blob)
      return SqlUtil.blobToStr((Blob)obj, charset); 
    if (obj instanceof RowId) {
      RowId rowId = (RowId)obj;
      return StrUtil.str(rowId.getBytes(), charset);
    } 
    return super.getStr(field);
  }
  
  public RowId getRowId() {
    return getRowId("ROWID");
  }
  
  public RowId getRowId(String field) {
    Object obj = get(field);
    if (null == obj)
      return null; 
    if (obj instanceof RowId)
      return (RowId)obj; 
    throw new DbRuntimeException("Value of field [{}] is not a rowid!", new Object[] { field });
  }
  
  public Entity clone() {
    return (Entity)super.clone();
  }
  
  public String toString() {
    return "Entity {tableName=" + this.tableName + ", fieldNames=" + this.fieldNames + ", fields=" + super.toString() + "}";
  }
}
