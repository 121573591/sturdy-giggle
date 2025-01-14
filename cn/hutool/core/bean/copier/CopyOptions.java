package cn.hutool.core.bean.copier;

import cn.hutool.core.bean.PropDesc;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.convert.TypeConverter;
import cn.hutool.core.lang.Editor;
import cn.hutool.core.lang.func.Func1;
import cn.hutool.core.lang.func.LambdaUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

public class CopyOptions implements Serializable {
  private static final long serialVersionUID = 1L;
  
  protected Class<?> editable;
  
  protected boolean ignoreNullValue;
  
  private BiPredicate<Field, Object> propertiesFilter;
  
  protected boolean ignoreError;
  
  protected boolean ignoreCase;
  
  private Editor<String> fieldNameEditor;
  
  protected BiFunction<String, Object, Object> fieldValueEditor;
  
  protected boolean transientSupport = true;
  
  protected boolean override = true;
  
  protected boolean autoTransCamelCase = true;
  
  private Set<String> ignoreKeySet;
  
  protected TypeConverter converter;
  
  public CopyOptions() {
    this.converter = ((type, value) -> (null == value) ? null : ((value instanceof IJSONTypeConverter) ? ((IJSONTypeConverter)value).toBean((Type)ObjectUtil.defaultIfNull(type, Object.class)) : Convert.convertWithCheck(type, value, null, this.ignoreError)));
  }
  
  public CopyOptions(Class<?> editable, boolean ignoreNullValue, String... ignoreProperties) {
    this.converter = ((type, value) -> (null == value) ? null : ((value instanceof IJSONTypeConverter) ? ((IJSONTypeConverter)value).toBean((Type)ObjectUtil.defaultIfNull(type, Object.class)) : Convert.convertWithCheck(type, value, null, this.ignoreError)));
    this.propertiesFilter = ((f, v) -> true);
    this.editable = editable;
    this.ignoreNullValue = ignoreNullValue;
    setIgnoreProperties(ignoreProperties);
  }
  
  public static CopyOptions create() {
    return new CopyOptions();
  }
  
  public static CopyOptions create(Class<?> editable, boolean ignoreNullValue, String... ignoreProperties) {
    return new CopyOptions(editable, ignoreNullValue, ignoreProperties);
  }
  
  public CopyOptions setEditable(Class<?> editable) {
    this.editable = editable;
    return this;
  }
  
  public CopyOptions setIgnoreNullValue(boolean ignoreNullVall) {
    this.ignoreNullValue = ignoreNullVall;
    return this;
  }
  
  public CopyOptions ignoreNullValue() {
    return setIgnoreNullValue(true);
  }
  
  public CopyOptions setPropertiesFilter(BiPredicate<Field, Object> propertiesFilter) {
    this.propertiesFilter = propertiesFilter;
    return this;
  }
  
  public CopyOptions setIgnoreProperties(String... ignoreProperties) {
    this.ignoreKeySet = CollUtil.newHashSet((Object[])ignoreProperties);
    return this;
  }
  
  public <P, R> CopyOptions setIgnoreProperties(Func1<P, R>... funcs) {
    this.ignoreKeySet = ArrayUtil.mapToSet((Object[])funcs, LambdaUtil::getFieldName);
    return this;
  }
  
  public CopyOptions setIgnoreError(boolean ignoreError) {
    this.ignoreError = ignoreError;
    return this;
  }
  
  public CopyOptions ignoreError() {
    return setIgnoreError(true);
  }
  
  public CopyOptions setIgnoreCase(boolean ignoreCase) {
    this.ignoreCase = ignoreCase;
    return this;
  }
  
  public CopyOptions ignoreCase() {
    return setIgnoreCase(true);
  }
  
  public CopyOptions setFieldMapping(Map<String, String> fieldMapping) {
    return setFieldNameEditor(key -> (String)fieldMapping.getOrDefault(key, key));
  }
  
  public CopyOptions setFieldNameEditor(Editor<String> fieldNameEditor) {
    this.fieldNameEditor = fieldNameEditor;
    return this;
  }
  
  public CopyOptions setFieldValueEditor(BiFunction<String, Object, Object> fieldValueEditor) {
    this.fieldValueEditor = fieldValueEditor;
    return this;
  }
  
  protected Object editFieldValue(String fieldName, Object fieldValue) {
    return (null != this.fieldValueEditor) ? this.fieldValueEditor
      .apply(fieldName, fieldValue) : fieldValue;
  }
  
  public CopyOptions setTransientSupport(boolean transientSupport) {
    this.transientSupport = transientSupport;
    return this;
  }
  
  public CopyOptions setOverride(boolean override) {
    this.override = override;
    return this;
  }
  
  public CopyOptions setAutoTransCamelCase(boolean autoTransCamelCase) {
    this.autoTransCamelCase = autoTransCamelCase;
    return this;
  }
  
  public CopyOptions setConverter(TypeConverter converter) {
    this.converter = converter;
    return this;
  }
  
  protected Object convertField(Type targetType, Object fieldValue) {
    return (null != this.converter) ? this.converter
      .convert(targetType, fieldValue) : fieldValue;
  }
  
  protected String editFieldName(String fieldName) {
    return (null != this.fieldNameEditor) ? (String)this.fieldNameEditor.edit(fieldName) : fieldName;
  }
  
  protected boolean testPropertyFilter(Field field, Object value) {
    return (null == this.propertiesFilter || this.propertiesFilter.test(field, value));
  }
  
  protected boolean testKeyFilter(Object key) {
    if (CollUtil.isEmpty(this.ignoreKeySet))
      return true; 
    if (this.ignoreCase)
      for (String ignoreKey : this.ignoreKeySet) {
        if (StrUtil.equalsIgnoreCase(key.toString(), ignoreKey))
          return false; 
      }  
    return (false == this.ignoreKeySet.contains(key));
  }
  
  protected PropDesc findPropDesc(Map<String, PropDesc> targetPropDescMap, String sKeyStr) {
    PropDesc propDesc = targetPropDescMap.get(sKeyStr);
    if (null == propDesc && this.autoTransCamelCase) {
      String camelCaseKey = StrUtil.toCamelCase(sKeyStr);
      if (!StrUtil.equals(sKeyStr, camelCaseKey))
        propDesc = targetPropDescMap.get(camelCaseKey); 
    } 
    return propDesc;
  }
}
