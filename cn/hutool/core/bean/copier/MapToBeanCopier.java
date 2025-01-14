package cn.hutool.core.bean.copier;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.PropDesc;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.map.MapWrapper;
import cn.hutool.core.util.TypeUtil;
import java.lang.reflect.Type;
import java.util.Map;

public class MapToBeanCopier<T> extends AbsCopier<Map<?, ?>, T> {
  private final Type targetType;
  
  public MapToBeanCopier(Map<?, ?> source, T target, Type targetType, CopyOptions copyOptions) {
    super(source, target, copyOptions);
    if (source instanceof MapWrapper) {
      Map<?, ?> raw = ((MapWrapper)source).getRaw();
      if (raw instanceof cn.hutool.core.map.CaseInsensitiveMap)
        copyOptions.setIgnoreCase(true); 
    } 
    this.targetType = targetType;
  }
  
  public T copy() {
    Class<?> actualEditable = this.target.getClass();
    if (null != this.copyOptions.editable) {
      Assert.isTrue(this.copyOptions.editable.isInstance(this.target), "Target class [{}] not assignable to Editable class [{}]", new Object[] { actualEditable
            .getName(), this.copyOptions.editable.getName() });
      actualEditable = this.copyOptions.editable;
    } 
    Map<String, PropDesc> targetPropDescMap = BeanUtil.getBeanDesc(actualEditable).getPropMap(this.copyOptions.ignoreCase);
    this.source.forEach((sKey, sValue) -> {
          if (null == sKey)
            return; 
          String sKeyStr = this.copyOptions.editFieldName(sKey.toString());
          if (null == sKeyStr)
            return; 
          if (false == this.copyOptions.testKeyFilter(sKeyStr))
            return; 
          PropDesc tDesc = this.copyOptions.findPropDesc(targetPropDescMap, sKeyStr);
          if (null == tDesc || false == tDesc.isWritable(this.copyOptions.transientSupport))
            return; 
          sKeyStr = tDesc.getFieldName();
          if (false == this.copyOptions.testPropertyFilter(tDesc.getField(), sValue))
            return; 
          Type fieldType = TypeUtil.getActualType(this.targetType, tDesc.getFieldType());
          Object newValue = this.copyOptions.convertField(fieldType, sValue);
          newValue = this.copyOptions.editFieldValue(sKeyStr, newValue);
          tDesc.setValue(this.target, newValue, this.copyOptions.ignoreNullValue, this.copyOptions.ignoreError, this.copyOptions.override);
        });
    return this.target;
  }
}
