package cn.hutool.db.handler;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.PropDesc;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.TypeUtil;
import cn.hutool.db.Entity;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HandleHelper {
  public static <T> T handleRow(int columnCount, ResultSetMetaData meta, ResultSet rs, T bean) throws SQLException {
    return (T)handleRow(columnCount, meta, rs).toBeanIgnoreCase(bean);
  }
  
  public static <T> T handleRow(int columnCount, ResultSetMetaData meta, ResultSet rs, Class<T> beanClass) throws SQLException {
    Assert.notNull(beanClass, "Bean Class must be not null !", new Object[0]);
    if (beanClass.isArray()) {
      Class<?> componentType = beanClass.getComponentType();
      Object[] result = ArrayUtil.newArray(componentType, columnCount);
      for (int k = 0, j = 1; k < columnCount; k++, j++)
        result[k] = getColumnValue(rs, j, meta.getColumnType(j), componentType); 
      return (T)result;
    } 
    if (Iterable.class.isAssignableFrom(beanClass)) {
      Object[] objRow = handleRow(columnCount, meta, rs, Object[].class);
      return (T)Convert.convert(beanClass, objRow);
    } 
    if (beanClass.isAssignableFrom(Entity.class))
      return (T)handleRow(columnCount, meta, rs); 
    if (String.class == beanClass) {
      Object[] objRow = handleRow(columnCount, meta, rs, Object[].class);
      return (T)StrUtil.join(", ", objRow);
    } 
    T bean = (T)ReflectUtil.newInstanceIfPossible(beanClass);
    Map<String, PropDesc> propMap = BeanUtil.getBeanDesc(beanClass).getPropMap(true);
    for (int i = 1; i <= columnCount; i++) {
      String columnLabel = meta.getColumnLabel(i);
      PropDesc pd = propMap.get(columnLabel);
      if (null == pd)
        pd = propMap.get(StrUtil.toCamelCase(columnLabel)); 
      Method setter = (null == pd) ? null : pd.getSetter();
      if (null != setter) {
        Object value = getColumnValue(rs, i, meta.getColumnType(i), TypeUtil.getFirstParamType(setter));
        ReflectUtil.invokeWithCheck(bean, setter, new Object[] { value });
      } 
    } 
    return bean;
  }
  
  public static Entity handleRow(int columnCount, ResultSetMetaData meta, ResultSet rs) throws SQLException {
    return handleRow(columnCount, meta, rs, false);
  }
  
  public static Entity handleRow(int columnCount, ResultSetMetaData meta, ResultSet rs, boolean caseInsensitive) throws SQLException {
    return handleRow(new Entity(null, caseInsensitive), columnCount, meta, rs, true);
  }
  
  public static <T extends Entity> T handleRow(T row, int columnCount, ResultSetMetaData meta, ResultSet rs, boolean withMetaInfo) throws SQLException {
    for (int i = 1; i <= columnCount; i++) {
      int type = meta.getColumnType(i);
      String columnLabel = meta.getColumnLabel(i);
      if (!"rownum_".equalsIgnoreCase(columnLabel))
        row.put(columnLabel, getColumnValue(rs, i, type, null)); 
    } 
    if (withMetaInfo) {
      try {
        row.setTableName(meta.getTableName(1));
      } catch (SQLException sQLException) {}
      row.setFieldNames(row.keySet());
    } 
    return row;
  }
  
  public static Entity handleRow(ResultSet rs) throws SQLException {
    ResultSetMetaData meta = rs.getMetaData();
    int columnCount = meta.getColumnCount();
    return handleRow(columnCount, meta, rs);
  }
  
  public static List<Object> handleRowToList(ResultSet rs) throws SQLException {
    ResultSetMetaData meta = rs.getMetaData();
    int columnCount = meta.getColumnCount();
    List<Object> row = new ArrayList(columnCount);
    for (int i = 1; i <= columnCount; i++)
      row.add(getColumnValue(rs, i, meta.getColumnType(i), null)); 
    return row;
  }
  
  public static <T extends java.util.Collection<Entity>> T handleRs(ResultSet rs, T collection) throws SQLException {
    return handleRs(rs, collection, false);
  }
  
  public static <T extends java.util.Collection<Entity>> T handleRs(ResultSet rs, T collection, boolean caseInsensitive) throws SQLException {
    ResultSetMetaData meta = rs.getMetaData();
    int columnCount = meta.getColumnCount();
    while (rs.next())
      collection.add(handleRow(columnCount, meta, rs, caseInsensitive)); 
    return collection;
  }
  
  public static <E, T extends java.util.Collection<E>> T handleRsToBeanList(ResultSet rs, T collection, Class<E> elementBeanType) throws SQLException {
    ResultSetMetaData meta = rs.getMetaData();
    int columnCount = meta.getColumnCount();
    while (rs.next())
      collection.add(handleRow(columnCount, meta, rs, elementBeanType)); 
    return collection;
  }
  
  private static Object getColumnValue(ResultSet rs, int columnIndex, int type, Type targetColumnType) throws SQLException {
    Object rawValue = null;
    switch (type) {
      case 93:
        try {
          rawValue = rs.getTimestamp(columnIndex);
        } catch (SQLException sQLException) {}
        break;
      case 92:
        rawValue = rs.getTime(columnIndex);
        break;
      default:
        rawValue = rs.getObject(columnIndex);
        break;
    } 
    if (null == targetColumnType || Object.class == targetColumnType)
      return rawValue; 
    return Convert.convert(targetColumnType, rawValue);
  }
}
