package cn.hutool.core.lang;

import cn.hutool.core.bean.BeanPath;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.getter.BasicTypeGetter;
import cn.hutool.core.lang.func.Func0;
import cn.hutool.core.lang.func.LambdaUtil;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Dict extends LinkedHashMap<String, Object> implements BasicTypeGetter<String> {
  private static final long serialVersionUID = 6135423866861206530L;
  
  static final float DEFAULT_LOAD_FACTOR = 0.75F;
  
  static final int DEFAULT_INITIAL_CAPACITY = 16;
  
  private boolean caseInsensitive;
  
  public static Dict create() {
    return new Dict();
  }
  
  public static <T> Dict parse(T bean) {
    return create().parseBean(bean);
  }
  
  @SafeVarargs
  public static Dict of(Pair<String, Object>... pairs) {
    Dict dict = create();
    for (Pair<String, Object> pair : pairs)
      dict.put(pair.getKey(), pair.getValue()); 
    return dict;
  }
  
  public static Dict of(Object... keysAndValues) {
    Dict dict = create();
    String key = null;
    for (int i = 0; i < keysAndValues.length; i++) {
      if (i % 2 == 0) {
        key = Convert.toStr(keysAndValues[i]);
      } else {
        dict.put(key, keysAndValues[i]);
      } 
    } 
    return dict;
  }
  
  public Dict() {
    this(false);
  }
  
  public Dict(boolean caseInsensitive) {
    this(16, caseInsensitive);
  }
  
  public Dict(int initialCapacity) {
    this(initialCapacity, false);
  }
  
  public Dict(int initialCapacity, boolean caseInsensitive) {
    this(initialCapacity, 0.75F, caseInsensitive);
  }
  
  public Dict(int initialCapacity, float loadFactor) {
    this(initialCapacity, loadFactor, false);
  }
  
  public Dict(int initialCapacity, float loadFactor, boolean caseInsensitive) {
    super(initialCapacity, loadFactor);
    this.caseInsensitive = caseInsensitive;
  }
  
  public Dict(Map<String, Object> m) {
    super((null == m) ? new HashMap<>() : m);
  }
  
  public <T> T toBean(T bean) {
    return toBean(bean, false);
  }
  
  public <T> T toBeanIgnoreCase(T bean) {
    BeanUtil.fillBeanWithMapIgnoreCase(this, bean, false);
    return bean;
  }
  
  public <T> T toBean(T bean, boolean isToCamelCase) {
    BeanUtil.fillBeanWithMap(this, bean, isToCamelCase, false);
    return bean;
  }
  
  public <T> T toBeanWithCamelCase(T bean) {
    BeanUtil.fillBeanWithMap(this, bean, true, false);
    return bean;
  }
  
  public <T> T toBean(Class<T> clazz) {
    return (T)BeanUtil.toBean(this, clazz);
  }
  
  public <T> T toBeanIgnoreCase(Class<T> clazz) {
    return (T)BeanUtil.toBeanIgnoreCase(this, clazz, false);
  }
  
  public <T> Dict parseBean(T bean) {
    Assert.notNull(bean, "Bean class must be not null", new Object[0]);
    putAll(BeanUtil.beanToMap(bean, new String[0]));
    return this;
  }
  
  public <T> Dict parseBean(T bean, boolean isToUnderlineCase, boolean ignoreNullValue) {
    Assert.notNull(bean, "Bean class must be not null", new Object[0]);
    putAll(BeanUtil.beanToMap(bean, isToUnderlineCase, ignoreNullValue));
    return this;
  }
  
  public <T extends Dict> void removeEqual(T dict, String... withoutNames) {
    HashSet<String> withoutSet = CollUtil.newHashSet((Object[])withoutNames);
    for (Map.Entry<String, Object> entry : dict.entrySet()) {
      if (withoutSet.contains(entry.getKey()))
        continue; 
      Object value = get(entry.getKey());
      if (Objects.equals(value, entry.getValue()))
        remove(entry.getKey()); 
    } 
  }
  
  public Dict filter(String... keys) {
    Dict result = new Dict(keys.length, 1.0F);
    for (String key : keys) {
      if (containsKey(key))
        result.put(key, get(key)); 
    } 
    return result;
  }
  
  public Dict set(String attr, Object value) {
    put(attr, value);
    return this;
  }
  
  public Dict setIgnoreNull(String attr, Object value) {
    if (null != attr && null != value)
      set(attr, value); 
    return this;
  }
  
  public Object getObj(String key) {
    return super.get(key);
  }
  
  public <T> T getBean(String attr) {
    return get(attr, null);
  }
  
  public <T> T get(String attr, T defaultValue) {
    Object result = get(attr);
    return (result != null) ? (T)result : defaultValue;
  }
  
  public String getStr(String attr) {
    return Convert.toStr(get(attr), null);
  }
  
  public Integer getInt(String attr) {
    return Convert.toInt(get(attr), null);
  }
  
  public Long getLong(String attr) {
    return Convert.toLong(get(attr), null);
  }
  
  public Float getFloat(String attr) {
    return Convert.toFloat(get(attr), null);
  }
  
  public Short getShort(String attr) {
    return Convert.toShort(get(attr), null);
  }
  
  public Character getChar(String attr) {
    return Convert.toChar(get(attr), null);
  }
  
  public Double getDouble(String attr) {
    return Convert.toDouble(get(attr), null);
  }
  
  public Byte getByte(String attr) {
    return Convert.toByte(get(attr), null);
  }
  
  public Boolean getBool(String attr) {
    return Convert.toBool(get(attr), null);
  }
  
  public BigDecimal getBigDecimal(String attr) {
    return Convert.toBigDecimal(get(attr));
  }
  
  public BigInteger getBigInteger(String attr) {
    return Convert.toBigInteger(get(attr));
  }
  
  public <E extends Enum<E>> E getEnum(Class<E> clazz, String key) {
    return (E)Convert.toEnum(clazz, get(key));
  }
  
  public byte[] getBytes(String attr) {
    return get(attr, null);
  }
  
  public Date getDate(String attr) {
    return get(attr, null);
  }
  
  public Time getTime(String attr) {
    return get(attr, null);
  }
  
  public Timestamp getTimestamp(String attr) {
    return get(attr, null);
  }
  
  public Number getNumber(String attr) {
    return get(attr, null);
  }
  
  public <T> T getByPath(String expression) {
    return (T)BeanPath.create(expression).get(this);
  }
  
  public <T> T getByPath(String expression, Class<T> resultType) {
    return (T)Convert.convert(resultType, getByPath(expression));
  }
  
  public boolean containsKey(Object key) {
    return super.containsKey(customKey((String)key));
  }
  
  public Object get(Object key) {
    return super.get(customKey((String)key));
  }
  
  public Object put(String key, Object value) {
    return super.put(customKey(key), value);
  }
  
  public void putAll(Map<? extends String, ?> m) {
    m.forEach(this::put);
  }
  
  public Dict clone() {
    return (Dict)super.clone();
  }
  
  public Object remove(Object key) {
    return super.remove(customKey((String)key));
  }
  
  public boolean remove(Object key, Object value) {
    return super.remove(customKey((String)key), value);
  }
  
  public boolean replace(String key, Object oldValue, Object newValue) {
    return super.replace(customKey(key), oldValue, newValue);
  }
  
  public Object replace(String key, Object value) {
    return super.replace(customKey(key), value);
  }
  
  public Object getOrDefault(Object key, Object defaultValue) {
    return super.getOrDefault(customKey((String)key), defaultValue);
  }
  
  public Object computeIfPresent(String key, BiFunction<? super String, ? super Object, ?> remappingFunction) {
    return super.computeIfPresent(customKey(key), remappingFunction);
  }
  
  public Object compute(String key, BiFunction<? super String, ? super Object, ?> remappingFunction) {
    return super.compute(customKey(key), remappingFunction);
  }
  
  public Object merge(String key, Object value, BiFunction<? super Object, ? super Object, ?> remappingFunction) {
    return super.merge(customKey(key), value, remappingFunction);
  }
  
  public Object putIfAbsent(String key, Object value) {
    return super.putIfAbsent(customKey(key), value);
  }
  
  public Object computeIfAbsent(String key, Function<? super String, ?> mappingFunction) {
    return super.computeIfAbsent(customKey(key), mappingFunction);
  }
  
  private String customKey(String key) {
    if (this.caseInsensitive && null != key)
      key = key.toLowerCase(); 
    return key;
  }
  
  public Dict setFields(Func0<?>... fields) {
    Arrays.<Func0<?>>stream(fields).forEach(f -> set(LambdaUtil.getFieldName(f), f.callWithRuntimeException()));
    return this;
  }
}
