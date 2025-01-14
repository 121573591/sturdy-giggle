package cn.hutool.core.comparator;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import java.lang.reflect.Field;

public class FieldComparator<T> extends FuncComparator<T> {
  private static final long serialVersionUID = 9157326766723846313L;
  
  public FieldComparator(Class<T> beanClass, String fieldName) {
    this(getNonNullField(beanClass, fieldName));
  }
  
  public FieldComparator(Field field) {
    this(true, true, field);
  }
  
  public FieldComparator(boolean nullGreater, boolean compareSelf, Field field) {
    super(nullGreater, compareSelf, bean -> (Comparable)ReflectUtil.getFieldValue(bean, (Field)Assert.notNull(field, "Field must be not null!", new Object[0])));
  }
  
  private static Field getNonNullField(Class<?> beanClass, String fieldName) {
    Field field = ClassUtil.getDeclaredField(beanClass, fieldName);
    if (field == null)
      throw new IllegalArgumentException(StrUtil.format("Field [{}] not found in Class [{}]", new Object[] { fieldName, beanClass.getName() })); 
    return field;
  }
}
