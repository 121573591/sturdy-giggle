package cn.hutool.core.bean.copier;

import java.lang.reflect.Type;

public interface IJSONTypeConverter {
  <T> T toBean(Type paramType);
}
