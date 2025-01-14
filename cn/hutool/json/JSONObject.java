package cn.hutool.json;

import cn.hutool.core.bean.BeanPath;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Filter;
import cn.hutool.core.lang.mutable.MutablePair;
import cn.hutool.core.map.MapWrapper;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.json.serialize.JSONWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Map;

public class JSONObject extends MapWrapper<String, Object> implements JSON, JSONGetter<String> {
  private static final long serialVersionUID = -330220388580734346L;
  
  public static final int DEFAULT_CAPACITY = 16;
  
  private JSONConfig config;
  
  public JSONObject() {
    this(16, false);
  }
  
  public JSONObject(boolean isOrder) {
    this(16, isOrder);
  }
  
  public JSONObject(int capacity, boolean isOrder) {
    this(capacity, false, isOrder);
  }
  
  @Deprecated
  public JSONObject(int capacity, boolean isIgnoreCase, boolean isOrder) {
    this(capacity, JSONConfig.create().setIgnoreCase(isIgnoreCase));
  }
  
  public JSONObject(JSONConfig config) {
    this(16, config);
  }
  
  public JSONObject(int capacity, JSONConfig config) {
    super(InternalJSONUtil.createRawMap(capacity, (JSONConfig)ObjectUtil.defaultIfNull(config, JSONConfig.create())));
    this.config = (JSONConfig)ObjectUtil.defaultIfNull(config, JSONConfig.create());
  }
  
  public JSONObject(Object source) {
    this(source, InternalJSONUtil.defaultIgnoreNullValue(source));
  }
  
  public JSONObject(Object source, boolean ignoreNullValue) {
    this(source, JSONConfig.create().setIgnoreNullValue(ignoreNullValue));
  }
  
  @Deprecated
  public JSONObject(Object source, boolean ignoreNullValue, boolean isOrder) {
    this(source, JSONConfig.create()
        .setIgnoreCase(source instanceof cn.hutool.core.map.CaseInsensitiveMap)
        .setIgnoreNullValue(ignoreNullValue));
  }
  
  public JSONObject(Object source, JSONConfig config) {
    this(source, config, (Filter<MutablePair<String, Object>>)null);
  }
  
  public JSONObject(Object source, JSONConfig config, Filter<MutablePair<String, Object>> filter) {
    this(16, config);
    ObjectMapper.of(source).map(this, filter);
  }
  
  public JSONObject(Object source, String... names) {
    this();
    if (ArrayUtil.isEmpty((Object[])names)) {
      ObjectMapper.of(source).map(this, (Filter<MutablePair<String, Object>>)null);
      return;
    } 
    if (source instanceof Map) {
      for (String name : names) {
        Object value = ((Map)source).get(name);
        set(name, value, (Filter<MutablePair<String, Object>>)null, getConfig().isCheckDuplicate());
      } 
    } else {
      for (String name : names) {
        try {
          putOpt(name, ReflectUtil.getFieldValue(source, name));
        } catch (Exception exception) {}
      } 
    } 
  }
  
  @Deprecated
  public JSONObject(CharSequence source, boolean isOrder) throws JSONException {
    this(source, JSONConfig.create());
  }
  
  public JSONConfig getConfig() {
    return this.config;
  }
  
  public JSONObject setDateFormat(String format) {
    this.config.setDateFormat(format);
    return this;
  }
  
  public JSONArray toJSONArray(Collection<String> names) throws JSONException {
    if (CollectionUtil.isEmpty(names))
      return null; 
    JSONArray ja = new JSONArray(this.config);
    for (String name : names) {
      Object value = get(name);
      if (null != value)
        ja.set(value); 
    } 
    return ja;
  }
  
  public Object getObj(String key, Object defaultValue) {
    return getOrDefault(key, defaultValue);
  }
  
  public Object getByPath(String expression) {
    return BeanPath.create(expression).get(this);
  }
  
  public <T> T getByPath(String expression, Class<T> resultType) {
    return JSONConverter.jsonConvert(resultType, getByPath(expression), getConfig());
  }
  
  public void putByPath(String expression, Object value) {
    BeanPath.create(expression).set(this, value);
  }
  
  @Deprecated
  public JSONObject put(String key, Object value) throws JSONException {
    return set(key, value);
  }
  
  public JSONObject set(String key, Object value) throws JSONException {
    return set(key, value, (Filter<MutablePair<String, Object>>)null, false);
  }
  
  public JSONObject set(String key, Object value, Filter<MutablePair<String, Object>> filter, boolean checkDuplicate) throws JSONException {
    if (null == key)
      return this; 
    if (null != filter) {
      MutablePair<String, Object> pair = new MutablePair(key, value);
      if (filter.accept(pair)) {
        key = (String)pair.getKey();
        value = pair.getValue();
      } else {
        return this;
      } 
    } 
    boolean ignoreNullValue = this.config.isIgnoreNullValue();
    if (ObjectUtil.isNull(value) && ignoreNullValue) {
      remove(key);
    } else {
      if (checkDuplicate && containsKey(key))
        throw new JSONException("Duplicate key \"{}\"", new Object[] { key }); 
      super.put(key, JSONUtil.wrap(InternalJSONUtil.testValidity(value), this.config));
    } 
    return this;
  }
  
  public JSONObject putOnce(String key, Object value) throws JSONException {
    return setOnce(key, value, (Filter<MutablePair<String, Object>>)null);
  }
  
  public JSONObject setOnce(String key, Object value, Filter<MutablePair<String, Object>> filter) throws JSONException {
    return set(key, value, filter, true);
  }
  
  public JSONObject putOpt(String key, Object value) throws JSONException {
    if (key != null && value != null)
      set(key, value); 
    return this;
  }
  
  public void putAll(Map<? extends String, ?> m) {
    for (Map.Entry<? extends String, ?> entry : m.entrySet())
      set(entry.getKey(), entry.getValue()); 
  }
  
  public JSONObject accumulate(String key, Object value) throws JSONException {
    InternalJSONUtil.testValidity(value);
    Object object = getObj(key);
    if (object == null) {
      set(key, value);
    } else if (object instanceof JSONArray) {
      ((JSONArray)object).set(value);
    } else {
      set(key, JSONUtil.createArray(this.config).set(object).set(value));
    } 
    return this;
  }
  
  public JSONObject append(String key, Object value) throws JSONException {
    InternalJSONUtil.testValidity(value);
    Object object = getObj(key);
    if (object == null) {
      set(key, (new JSONArray(this.config)).set(value));
    } else if (object instanceof JSONArray) {
      set(key, ((JSONArray)object).set(value));
    } else {
      throw new JSONException("JSONObject [" + key + "] is not a JSONArray.");
    } 
    return this;
  }
  
  public JSONObject increment(String key) throws JSONException {
    Object value = getObj(key);
    if (value == null) {
      set(key, Integer.valueOf(1));
    } else if (value instanceof BigInteger) {
      set(key, ((BigInteger)value).add(BigInteger.ONE));
    } else if (value instanceof BigDecimal) {
      set(key, ((BigDecimal)value).add(BigDecimal.ONE));
    } else if (value instanceof Integer) {
      set(key, Integer.valueOf(((Integer)value).intValue() + 1));
    } else if (value instanceof Long) {
      set(key, Long.valueOf(((Long)value).longValue() + 1L));
    } else if (value instanceof Double) {
      set(key, Double.valueOf(((Double)value).doubleValue() + 1.0D));
    } else if (value instanceof Float) {
      set(key, Float.valueOf(((Float)value).floatValue() + 1.0F));
    } else {
      throw new JSONException("Unable to increment [" + JSONUtil.quote(key) + "].");
    } 
    return this;
  }
  
  public String toString() {
    return toJSONString(0);
  }
  
  public String toJSONString(int indentFactor, Filter<MutablePair<Object, Object>> filter) {
    StringWriter sw = new StringWriter();
    synchronized (sw.getBuffer()) {
      return write(sw, indentFactor, 0, filter).toString();
    } 
  }
  
  public Writer write(Writer writer, int indentFactor, int indent) throws JSONException {
    return write(writer, indentFactor, indent, (Filter<MutablePair<Object, Object>>)null);
  }
  
  public Writer write(Writer writer, int indentFactor, int indent, Filter<MutablePair<Object, Object>> filter) throws JSONException {
    JSONWriter jsonWriter = JSONWriter.of(writer, indentFactor, indent, this.config).beginObj();
    forEach((key, value) -> jsonWriter.writeField(new MutablePair(key, value), filter));
    jsonWriter.end();
    return writer;
  }
  
  public JSONObject clone() throws CloneNotSupportedException {
    JSONObject clone = (JSONObject)super.clone();
    clone.config = this.config;
    return clone;
  }
}
