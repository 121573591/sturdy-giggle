package cn.hutool.json;

import cn.hutool.core.bean.BeanPath;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Filter;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.lang.mutable.Mutable;
import cn.hutool.core.lang.mutable.MutableObj;
import cn.hutool.core.lang.mutable.MutablePair;
import cn.hutool.core.text.StrJoiner;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.serialize.JSONWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.invoke.SerializedLambda;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

public class JSONArray implements JSON, JSONGetter<Integer>, List<Object>, RandomAccess {
  private static final long serialVersionUID = 2664900568717612292L;
  
  public static final int DEFAULT_CAPACITY = 10;
  
  private List<Object> rawList;
  
  private final JSONConfig config;
  
  public JSONArray() {
    this(10);
  }
  
  public JSONArray(int initialCapacity) {
    this(initialCapacity, JSONConfig.create());
  }
  
  public JSONArray(JSONConfig config) {
    this(10, config);
  }
  
  public JSONArray(int initialCapacity, JSONConfig config) {
    this.rawList = new ArrayList(initialCapacity);
    this.config = (JSONConfig)ObjectUtil.defaultIfNull(config, JSONConfig::create);
  }
  
  public JSONArray(Object object) throws JSONException {
    this(object, true);
  }
  
  public JSONArray(Object object, boolean ignoreNullValue) throws JSONException {
    this(object, JSONConfig.create().setIgnoreNullValue(ignoreNullValue));
  }
  
  public JSONArray(Object object, JSONConfig jsonConfig) throws JSONException {
    this(object, jsonConfig, (Filter<Mutable<Object>>)null);
  }
  
  public JSONArray(Object object, JSONConfig jsonConfig, Filter<Mutable<Object>> filter) throws JSONException {
    this(10, jsonConfig);
    ObjectMapper.of(object).map(this, filter);
  }
  
  public JSONConfig getConfig() {
    return this.config;
  }
  
  public JSONArray setDateFormat(String format) {
    this.config.setDateFormat(format);
    return this;
  }
  
  public String join(String separator) throws JSONException {
    return StrJoiner.of(separator)
      .append(this, InternalJSONUtil::valueToString).toString();
  }
  
  public Object get(int index) {
    return this.rawList.get(index);
  }
  
  public Object getObj(Integer index, Object defaultValue) {
    return (index.intValue() < 0 || index.intValue() >= size()) ? defaultValue : this.rawList.get(index.intValue());
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
  
  public JSONArray put(Object value) {
    return set(value);
  }
  
  public JSONArray set(Object value) {
    add(value);
    return this;
  }
  
  public JSONArray put(int index, Object value) throws JSONException {
    set(index, value);
    return this;
  }
  
  public JSONObject toJSONObject(JSONArray names) throws JSONException {
    if (names == null || names.size() == 0 || size() == 0)
      return null; 
    JSONObject jo = new JSONObject(this.config);
    for (int i = 0; i < names.size(); i++)
      jo.set(names.getStr(Integer.valueOf(i)), getObj(Integer.valueOf(i))); 
    return jo;
  }
  
  public int hashCode() {
    int prime = 31;
    int result = 1;
    result = 31 * result + ((this.rawList == null) ? 0 : this.rawList.hashCode());
    return result;
  }
  
  public boolean equals(Object obj) {
    if (this == obj)
      return true; 
    if (obj == null)
      return false; 
    if (getClass() != obj.getClass())
      return false; 
    JSONArray other = (JSONArray)obj;
    if (this.rawList == null)
      return (other.rawList == null); 
    return this.rawList.equals(other.rawList);
  }
  
  public Iterator<Object> iterator() {
    return this.rawList.iterator();
  }
  
  public Iterable<JSONObject> jsonIter() {
    return new JSONObjectIter(iterator());
  }
  
  public int size() {
    return this.rawList.size();
  }
  
  public boolean isEmpty() {
    return this.rawList.isEmpty();
  }
  
  public boolean contains(Object o) {
    return this.rawList.contains(o);
  }
  
  public Object[] toArray() {
    return this.rawList.toArray();
  }
  
  public <T> T[] toArray(T[] a) {
    return (T[])JSONConverter.toArray(this, a.getClass().getComponentType());
  }
  
  public boolean add(Object e) {
    return addRaw(JSONUtil.wrap(e, this.config), (Filter<Mutable<Object>>)null);
  }
  
  public Object remove(int index) {
    return (index >= 0 && index < size()) ? this.rawList.remove(index) : null;
  }
  
  public boolean remove(Object o) {
    return this.rawList.remove(o);
  }
  
  public boolean containsAll(Collection<?> c) {
    return this.rawList.containsAll(c);
  }
  
  public boolean addAll(Collection<?> c) {
    if (CollUtil.isEmpty(c))
      return false; 
    for (Object obj : c)
      add(obj); 
    return true;
  }
  
  public boolean addAll(int index, Collection<?> c) {
    if (CollUtil.isEmpty(c))
      return false; 
    ArrayList<Object> list = new ArrayList(c.size());
    for (Object object : c)
      list.add(JSONUtil.wrap(object, this.config)); 
    return this.rawList.addAll(index, list);
  }
  
  public boolean removeAll(Collection<?> c) {
    return this.rawList.removeAll(c);
  }
  
  public boolean retainAll(Collection<?> c) {
    return this.rawList.retainAll(c);
  }
  
  public void clear() {
    this.rawList.clear();
  }
  
  public Object set(int index, Object element) {
    return set(index, element, (Filter<MutablePair<Integer, Object>>)null);
  }
  
  public Object set(int index, Object element, Filter<MutablePair<Integer, Object>> filter) {
    if (null != filter) {
      MutablePair<Integer, Object> pair = new MutablePair(Integer.valueOf(index), element);
      if (filter.accept(pair))
        element = pair.getValue(); 
    } 
    if (index >= size())
      add(index, element); 
    return this.rawList.set(index, JSONUtil.wrap(element, this.config));
  }
  
  public void add(int index, Object element) {
    if (index < 0)
      throw new JSONException("JSONArray[{}] not found.", new Object[] { Integer.valueOf(index) }); 
    if (index < size()) {
      InternalJSONUtil.testValidity(element);
      this.rawList.add(index, JSONUtil.wrap(element, this.config));
    } else {
      Validator.checkIndexLimit(index, size());
      while (index != size())
        add(JSONNull.NULL); 
      set(element);
    } 
  }
  
  public int indexOf(Object o) {
    return this.rawList.indexOf(o);
  }
  
  public int lastIndexOf(Object o) {
    return this.rawList.lastIndexOf(o);
  }
  
  public ListIterator<Object> listIterator() {
    return this.rawList.listIterator();
  }
  
  public ListIterator<Object> listIterator(int index) {
    return this.rawList.listIterator(index);
  }
  
  public List<Object> subList(int fromIndex, int toIndex) {
    return this.rawList.subList(fromIndex, toIndex);
  }
  
  public Object toArray(Class<?> arrayClass) {
    return JSONConverter.toArray(this, arrayClass);
  }
  
  public <T> List<T> toList(Class<T> elementType) {
    return JSONConverter.toList(this, elementType);
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
    JSONWriter jsonWriter = JSONWriter.of(writer, indentFactor, indent, this.config).beginArray();
    CollUtil.forEach(this, (value, index) -> jsonWriter.writeField(new MutablePair(Integer.valueOf(index), value), filter));
    jsonWriter.end();
    return writer;
  }
  
  public Object clone() throws CloneNotSupportedException {
    JSONArray clone = (JSONArray)super.clone();
    clone.rawList = (List<Object>)ObjectUtil.clone(this.rawList);
    return clone;
  }
  
  protected boolean addRaw(Object obj, Filter<Mutable<Object>> filter) {
    if (null != filter) {
      MutableObj mutableObj = new MutableObj(obj);
      if (filter.accept(mutableObj)) {
        obj = mutableObj.get();
      } else {
        return false;
      } 
    } 
    return this.rawList.add(obj);
  }
}
