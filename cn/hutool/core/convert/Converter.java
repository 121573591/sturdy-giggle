package cn.hutool.core.convert;

public interface Converter<T> {
  T convert(Object paramObject, T paramT) throws IllegalArgumentException;
  
  default T convertWithCheck(Object value, T defaultValue, boolean quietly) {
    try {
      return convert(value, defaultValue);
    } catch (Exception e) {
      if (quietly)
        return defaultValue; 
      throw e;
    } 
  }
}
