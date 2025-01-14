package cn.hutool.json;

import cn.hutool.core.bean.copier.IJSONTypeConverter;
import cn.hutool.core.lang.TypeReference;
import java.io.Serializable;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Type;

public interface JSON extends Cloneable, Serializable, IJSONTypeConverter {
  JSONConfig getConfig();
  
  Object getByPath(String paramString);
  
  void putByPath(String paramString, Object paramObject);
  
  <T> T getByPath(String paramString, Class<T> paramClass);
  
  default String toStringPretty() throws JSONException {
    return toJSONString(4);
  }
  
  default String toJSONString(int indentFactor) throws JSONException {
    StringWriter sw = new StringWriter();
    return write(sw, indentFactor, 0).toString();
  }
  
  default Writer write(Writer writer) throws JSONException {
    return write(writer, 0, 0);
  }
  
  Writer write(Writer paramWriter, int paramInt1, int paramInt2) throws JSONException;
  
  default <T> T toBean(Class<T> clazz) {
    return toBean(clazz);
  }
  
  default <T> T toBean(TypeReference<T> reference) {
    return toBean(reference.getType());
  }
  
  default <T> T toBean(Type type) {
    return JSONConverter.jsonConvert(type, this, getConfig());
  }
  
  @Deprecated
  default <T> T toBean(Type type, boolean ignoreError) {
    return JSONConverter.jsonConvert(type, this, JSONConfig.create().setIgnoreError(ignoreError));
  }
}
