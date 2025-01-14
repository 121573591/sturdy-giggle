package tech.ordinaryroad.live.chat.client.commons.util;

import cn.hutool.core.util.ReflectUtil;
import java.lang.reflect.Method;

public class OrLiveChatReflectUtil extends ReflectUtil {
  public static Method getGetterMethod(Class<?> objectClass, String key) {
    Method method;
    if (key.startsWith("is")) {
      method = ReflectUtil.getMethodByNameIgnoreCase(objectClass, key);
      if (method == null)
        ReflectUtil.getMethodByNameIgnoreCase(objectClass, "get" + key); 
    } else {
      method = ReflectUtil.getMethodByNameIgnoreCase(objectClass, "get" + key);
    } 
    return method;
  }
}
