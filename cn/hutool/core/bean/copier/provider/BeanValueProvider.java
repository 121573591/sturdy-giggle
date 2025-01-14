package cn.hutool.core.bean.copier.provider;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.PropDesc;
import cn.hutool.core.bean.copier.ValueProvider;
import cn.hutool.core.lang.Editor;
import cn.hutool.core.map.FuncKeyMap;
import cn.hutool.core.util.StrUtil;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class BeanValueProvider implements ValueProvider<String> {
  private final Object source;
  
  private final boolean ignoreError;
  
  final Map<String, PropDesc> sourcePdMap;
  
  public BeanValueProvider(Object bean, boolean ignoreCase, boolean ignoreError) {
    this(bean, ignoreCase, ignoreError, null);
  }
  
  public BeanValueProvider(Object bean, boolean ignoreCase, boolean ignoreError, Editor<String> keyEditor) {
    this.source = bean;
    this.ignoreError = ignoreError;
    Map<String, PropDesc> sourcePdMap = BeanUtil.getBeanDesc(this.source.getClass()).getPropMap(ignoreCase);
    this.sourcePdMap = (Map<String, PropDesc>)new FuncKeyMap(new HashMap<>(sourcePdMap.size(), 1.0F), key -> {
          if (ignoreCase && key instanceof CharSequence)
            key = key.toString().toLowerCase(); 
          if (null != keyEditor)
            key = keyEditor.edit(key.toString()); 
          return key.toString();
        });
    this.sourcePdMap.putAll(sourcePdMap);
  }
  
  public Object value(String key, Type valueType) {
    PropDesc sourcePd = getPropDesc(key, valueType);
    Object result = null;
    if (null != sourcePd)
      result = sourcePd.getValue(this.source, valueType, this.ignoreError); 
    return result;
  }
  
  public boolean containsKey(String key) {
    PropDesc sourcePd = getPropDesc(key, null);
    return (null != sourcePd && sourcePd.isReadable(false));
  }
  
  private PropDesc getPropDesc(String key, Type valueType) {
    PropDesc sourcePd = this.sourcePdMap.get(key);
    if (null == sourcePd && (null == valueType || Boolean.class == valueType || boolean.class == valueType))
      sourcePd = this.sourcePdMap.get(StrUtil.upperFirstAndAddPre(key, "is")); 
    return sourcePd;
  }
}
