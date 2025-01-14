package cn.hutool.core.bean.copier;

import cn.hutool.core.util.TypeUtil;
import java.lang.reflect.Type;
import java.util.Map;

public class MapToMapCopier extends AbsCopier<Map, Map> {
  private final Type targetType;
  
  public MapToMapCopier(Map source, Map target, Type targetType, CopyOptions copyOptions) {
    super(source, target, copyOptions);
    this.targetType = targetType;
  }
  
  public Map copy() {
    this.source.forEach((sKey, sValue) -> {
          if (null == sKey)
            return; 
          if (true == this.copyOptions.ignoreNullValue && sValue == null)
            return; 
          String sKeyStr = this.copyOptions.editFieldName(sKey.toString());
          if (null == sKeyStr)
            return; 
          if (false == this.copyOptions.testKeyFilter(sKeyStr))
            return; 
          Object targetValue = this.target.get(sKeyStr);
          if (false == this.copyOptions.override && null != targetValue)
            return; 
          Type[] typeArguments = TypeUtil.getTypeArguments(this.targetType);
          if (null != typeArguments) {
            sValue = this.copyOptions.convertField(typeArguments[1], sValue);
            sValue = this.copyOptions.editFieldValue(sKeyStr, sValue);
          } 
          this.target.put(sKeyStr, sValue);
        });
    return this.target;
  }
}
