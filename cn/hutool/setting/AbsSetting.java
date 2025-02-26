package cn.hutool.setting;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.bean.copier.ValueProvider;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.getter.OptNullBasicTypeFromStringGetter;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import java.io.Serializable;
import java.lang.reflect.Type;

public abstract class AbsSetting implements OptNullBasicTypeFromStringGetter<String>, Serializable {
  private static final long serialVersionUID = 6200156302595905863L;
  
  private static final Log log = LogFactory.get();
  
  public static final String DEFAULT_DELIMITER = ",";
  
  public static final String DEFAULT_GROUP = "";
  
  public String getStr(String key, String defaultValue) {
    return getStr(key, "", defaultValue);
  }
  
  public String getStr(String key, String group, String defaultValue) {
    String value = getByGroup(key, group);
    return (String)ObjectUtil.defaultIfNull(value, defaultValue);
  }
  
  public String getStrNotEmpty(String key, String group, String defaultValue) {
    String value = getByGroup(key, group);
    return (String)ObjectUtil.defaultIfEmpty(value, defaultValue);
  }
  
  public String getWithLog(String key) {
    String value = getStr(key);
    if (value == null)
      log.debug("No key define for [{}]!", new Object[] { key }); 
    return value;
  }
  
  public String getByGroupWithLog(String key, String group) {
    String value = getByGroup(key, group);
    if (value == null)
      log.debug("No key define for [{}] of group [{}] !", new Object[] { key, group }); 
    return value;
  }
  
  public String[] getStrings(String key) {
    return getStrings(key, (String)null);
  }
  
  public String[] getStringsWithDefault(String key, String[] defaultValue) {
    String[] value = getStrings(key, (String)null);
    if (null == value)
      value = defaultValue; 
    return value;
  }
  
  public String[] getStrings(String key, String group) {
    return getStrings(key, group, ",");
  }
  
  public String[] getStrings(String key, String group, String delimiter) {
    String value = getByGroup(key, group);
    if (StrUtil.isBlank(value))
      return null; 
    return StrUtil.splitToArray(value, delimiter);
  }
  
  public Integer getInt(String key, String group) {
    return getInt(key, group, (Integer)null);
  }
  
  public Integer getInt(String key, String group, Integer defaultValue) {
    return Convert.toInt(getByGroup(key, group), defaultValue);
  }
  
  public Boolean getBool(String key, String group) {
    return getBool(key, group, (Boolean)null);
  }
  
  public Boolean getBool(String key, String group, Boolean defaultValue) {
    return Convert.toBool(getByGroup(key, group), defaultValue);
  }
  
  public Long getLong(String key, String group) {
    return getLong(key, group, (Long)null);
  }
  
  public Long getLong(String key, String group, Long defaultValue) {
    return Convert.toLong(getByGroup(key, group), defaultValue);
  }
  
  public Character getChar(String key, String group) {
    String value = getByGroup(key, group);
    if (StrUtil.isBlank(value))
      return null; 
    return Character.valueOf(value.charAt(0));
  }
  
  public Double getDouble(String key, String group) {
    return getDouble(key, group, (Double)null);
  }
  
  public Double getDouble(String key, String group, Double defaultValue) {
    return Convert.toDouble(getByGroup(key, group), defaultValue);
  }
  
  public <T> T toBean(final String group, T bean) {
    return (T)BeanUtil.fillBean(bean, new ValueProvider<String>() {
          public Object value(String key, Type valueType) {
            return AbsSetting.this.getByGroup(key, group);
          }
          
          public boolean containsKey(String key) {
            return (null != AbsSetting.this.getByGroup(key, group));
          }
        }CopyOptions.create());
  }
  
  public <T> T toBean(String group, Class<T> beanClass) {
    return toBean(group, (T)ReflectUtil.newInstanceIfPossible(beanClass));
  }
  
  public <T> T toBean(T bean) {
    return toBean((String)null, bean);
  }
  
  public <T> T toBean(Class<T> beanClass) {
    return toBean((String)null, beanClass);
  }
  
  public abstract String getByGroup(String paramString1, String paramString2);
}
