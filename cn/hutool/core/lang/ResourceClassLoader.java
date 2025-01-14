package cn.hutool.core.lang;

import cn.hutool.core.io.resource.Resource;
import cn.hutool.core.util.ClassLoaderUtil;
import cn.hutool.core.util.ObjectUtil;
import java.security.SecureClassLoader;
import java.util.HashMap;
import java.util.Map;

public class ResourceClassLoader<T extends Resource> extends SecureClassLoader {
  private final Map<String, T> resourceMap;
  
  private final Map<String, Class<?>> cacheClassMap;
  
  public ResourceClassLoader(ClassLoader parentClassLoader, Map<String, T> resourceMap) {
    super((ClassLoader)ObjectUtil.defaultIfNull(parentClassLoader, ClassLoaderUtil::getClassLoader));
    this.resourceMap = (Map<String, T>)ObjectUtil.defaultIfNull(resourceMap, new HashMap<>());
    this.cacheClassMap = new HashMap<>();
  }
  
  public ResourceClassLoader<T> addResource(T resource) {
    this.resourceMap.put(resource.getName(), resource);
    return this;
  }
  
  protected Class<?> findClass(String name) throws ClassNotFoundException {
    Class<?> clazz = this.cacheClassMap.computeIfAbsent(name, this::defineByName);
    if (clazz == null)
      return super.findClass(name); 
    return clazz;
  }
  
  private Class<?> defineByName(String name) {
    Resource resource = (Resource)this.resourceMap.get(name);
    if (null != resource) {
      byte[] bytes = resource.readBytes();
      return defineClass(name, bytes, 0, bytes.length);
    } 
    return null;
  }
}
