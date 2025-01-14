package cn.hutool.core.io;

import cn.hutool.core.collection.CollUtil;
import java.io.IOException;
import java.io.InputStream;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.util.HashSet;
import java.util.Set;

public class ValidateObjectInputStream extends ObjectInputStream {
  private Set<String> whiteClassSet;
  
  private Set<String> blackClassSet;
  
  public ValidateObjectInputStream(InputStream inputStream, Class<?>... acceptClasses) throws IOException {
    super(inputStream);
    accept(acceptClasses);
  }
  
  public void refuse(Class<?>... refuseClasses) {
    if (null == this.blackClassSet)
      this.blackClassSet = new HashSet<>(); 
    for (Class<?> acceptClass : refuseClasses)
      this.blackClassSet.add(acceptClass.getName()); 
  }
  
  public void accept(Class<?>... acceptClasses) {
    if (null == this.whiteClassSet)
      this.whiteClassSet = new HashSet<>(); 
    for (Class<?> acceptClass : acceptClasses)
      this.whiteClassSet.add(acceptClass.getName()); 
  }
  
  protected Class<?> resolveClass(ObjectStreamClass desc) throws IOException, ClassNotFoundException {
    validateClassName(desc.getName());
    return super.resolveClass(desc);
  }
  
  private void validateClassName(String className) throws InvalidClassException {
    if (CollUtil.isNotEmpty(this.blackClassSet) && 
      this.blackClassSet.contains(className))
      throw new InvalidClassException("Unauthorized deserialization attempt by black list", className); 
    if (CollUtil.isEmpty(this.whiteClassSet))
      return; 
    if (className.startsWith("java."))
      return; 
    if (this.whiteClassSet.contains(className))
      return; 
    throw new InvalidClassException("Unauthorized deserialization attempt", className);
  }
}
