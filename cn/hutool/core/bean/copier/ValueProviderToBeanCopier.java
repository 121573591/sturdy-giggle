package cn.hutool.core.bean.copier;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.PropDesc;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.TypeUtil;
import java.lang.reflect.Type;
import java.util.Map;

public class ValueProviderToBeanCopier<T> extends AbsCopier<ValueProvider<String>, T> {
  private final Type targetType;
  
  public ValueProviderToBeanCopier(ValueProvider<String> source, T target, Type targetType, CopyOptions copyOptions) {
    super(source, target, copyOptions);
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
    targetPropDescMap.forEach((tFieldName, tDesc) -> {
          if (null == tFieldName)
            return; 
          tFieldName = this.copyOptions.editFieldName(tFieldName);
          if (null == tFieldName)
            return; 
          if (false == this.source.containsKey(tFieldName))
            return; 
          if (false == this.copyOptions.testKeyFilter(tFieldName))
            return; 
          if (null == tDesc || false == tDesc.isWritable(this.copyOptions.transientSupport))
            return; 
          Type fieldType = TypeUtil.getActualType(this.targetType, tDesc.getFieldType());
          Object sValue = this.source.value(tFieldName, fieldType);
          if (false == this.copyOptions.testPropertyFilter(tDesc.getField(), sValue))
            return; 
          sValue = this.copyOptions.editFieldValue(tFieldName, sValue);
          tDesc.setValue(this.target, sValue, this.copyOptions.ignoreNullValue, this.copyOptions.ignoreError, this.copyOptions.override);
        });
    return this.target;
  }
}
