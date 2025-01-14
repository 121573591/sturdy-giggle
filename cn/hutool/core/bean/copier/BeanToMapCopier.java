package cn.hutool.core.bean.copier;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.PropDesc;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.TypeUtil;
import java.lang.reflect.Type;
import java.util.Map;

public class BeanToMapCopier extends AbsCopier<Object, Map> {
  private final Type targetType;
  
  public BeanToMapCopier(Object source, Map target, Type targetType, CopyOptions copyOptions) {
    super(source, target, copyOptions);
    this.targetType = targetType;
  }
  
  public Map copy() {
    Class<?> actualEditable = this.source.getClass();
    if (null != this.copyOptions.editable) {
      Assert.isTrue(this.copyOptions.editable.isInstance(this.source), "Source class [{}] not assignable to Editable class [{}]", new Object[] { actualEditable
            .getName(), this.copyOptions.editable.getName() });
      actualEditable = this.copyOptions.editable;
    } 
    Map<String, PropDesc> sourcePropDescMap = BeanUtil.getBeanDesc(actualEditable).getPropMap(this.copyOptions.ignoreCase);
    sourcePropDescMap.forEach((sFieldName, sDesc) -> {
          if (null == sFieldName || false == sDesc.isReadable(this.copyOptions.transientSupport))
            return; 
          sFieldName = this.copyOptions.editFieldName(sFieldName);
          if (null == sFieldName)
            return; 
          if (false == this.copyOptions.testKeyFilter(sFieldName))
            return; 
          Object sValue = sDesc.getValue(this.source);
          if (false == this.copyOptions.testPropertyFilter(sDesc.getField(), sValue))
            return; 
          Type[] typeArguments = TypeUtil.getTypeArguments(this.targetType);
          if (null != typeArguments && typeArguments.length > 1) {
            sValue = this.copyOptions.convertField(typeArguments[1], sValue);
            sValue = this.copyOptions.editFieldValue(sFieldName, sValue);
          } 
          if (null != sValue || false == this.copyOptions.ignoreNullValue)
            this.target.put(sFieldName, sValue); 
        });
    return this.target;
  }
}
