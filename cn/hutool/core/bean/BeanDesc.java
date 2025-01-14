package cn.hutool.core.bean;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.map.CaseInsensitiveMap;
import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.ModifierUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class BeanDesc implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private final Class<?> beanClass;
  
  private final Map<String, PropDesc> propMap = new LinkedHashMap<>();
  
  public BeanDesc(Class<?> beanClass) {
    Assert.notNull(beanClass);
    this.beanClass = beanClass;
    init();
  }
  
  public String getName() {
    return this.beanClass.getName();
  }
  
  public String getSimpleName() {
    return this.beanClass.getSimpleName();
  }
  
  public Map<String, PropDesc> getPropMap(boolean ignoreCase) {
    return ignoreCase ? (Map<String, PropDesc>)new CaseInsensitiveMap(1.0F, this.propMap) : this.propMap;
  }
  
  public Collection<PropDesc> getProps() {
    return this.propMap.values();
  }
  
  public PropDesc getProp(String fieldName) {
    return this.propMap.get(fieldName);
  }
  
  public Field getField(String fieldName) {
    PropDesc desc = this.propMap.get(fieldName);
    return (null == desc) ? null : desc.getField();
  }
  
  public Method getGetter(String fieldName) {
    PropDesc desc = this.propMap.get(fieldName);
    return (null == desc) ? null : desc.getGetter();
  }
  
  public Method getSetter(String fieldName) {
    PropDesc desc = this.propMap.get(fieldName);
    return (null == desc) ? null : desc.getSetter();
  }
  
  private BeanDesc init() {
    Method[] gettersAndSetters = ReflectUtil.getMethods(this.beanClass, ReflectUtil::isGetterOrSetterIgnoreCase);
    for (Field field : ReflectUtil.getFields(this.beanClass)) {
      if (false == ModifierUtil.isStatic(field) && false == ReflectUtil.isOuterClassField(field)) {
        PropDesc prop = createProp(field, gettersAndSetters);
        this.propMap.putIfAbsent(prop.getFieldName(), prop);
      } 
    } 
    return this;
  }
  
  private PropDesc createProp(Field field, Method[] methods) {
    PropDesc prop = findProp(field, methods, false);
    if (null == prop.getter || null == prop.setter) {
      PropDesc propIgnoreCase = findProp(field, methods, true);
      if (null == prop.getter)
        prop.getter = propIgnoreCase.getter; 
      if (null == prop.setter)
        prop.setter = propIgnoreCase.setter; 
    } 
    return prop;
  }
  
  private PropDesc findProp(Field field, Method[] gettersOrSetters, boolean ignoreCase) {
    String fieldName = field.getName();
    Class<?> fieldType = field.getType();
    boolean isBooleanField = BooleanUtil.isBoolean(fieldType);
    Method getter = null;
    Method setter = null;
    for (Method method : gettersOrSetters) {
      String methodName = method.getName();
      if (method.getParameterCount() == 0) {
        if (isMatchGetter(methodName, fieldName, isBooleanField, ignoreCase))
          getter = method; 
      } else if (isMatchSetter(methodName, fieldName, isBooleanField, ignoreCase)) {
        if (fieldType.isAssignableFrom(method.getParameterTypes()[0]))
          setter = method; 
      } 
      if (null != getter && null != setter)
        break; 
    } 
    return new PropDesc(field, getter, setter);
  }
  
  private boolean isMatchGetter(String methodName, String fieldName, boolean isBooleanField, boolean ignoreCase) {
    String handledFieldName;
    if (ignoreCase) {
      methodName = methodName.toLowerCase();
      handledFieldName = fieldName.toLowerCase();
      fieldName = handledFieldName;
    } else {
      handledFieldName = StrUtil.upperFirst(fieldName);
    } 
    if (isBooleanField)
      if (fieldName.startsWith("is")) {
        if (methodName.equals(fieldName) || ("get" + handledFieldName)
          .equals(methodName) || ("is" + handledFieldName)
          .equals(methodName))
          return true; 
      } else if (("is" + handledFieldName).equals(methodName)) {
        return true;
      }  
    return ("get" + handledFieldName).equals(methodName);
  }
  
  private boolean isMatchSetter(String methodName, String fieldName, boolean isBooleanField, boolean ignoreCase) {
    String handledFieldName;
    if (ignoreCase) {
      methodName = methodName.toLowerCase();
      handledFieldName = fieldName.toLowerCase();
      fieldName = handledFieldName;
    } else {
      handledFieldName = StrUtil.upperFirst(fieldName);
    } 
    if (false == methodName.startsWith("set"))
      return false; 
    if (isBooleanField && fieldName.startsWith("is"))
      if (("set" + StrUtil.removePrefix(fieldName, "is")).equals(methodName) || ("set" + handledFieldName)
        .equals(methodName))
        return true;  
    return ("set" + handledFieldName).equals(methodName);
  }
}
