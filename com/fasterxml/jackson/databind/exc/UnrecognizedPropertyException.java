package com.fasterxml.jackson.databind.exc;

import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParser;
import java.util.Collection;

public class UnrecognizedPropertyException extends PropertyBindingException {
  private static final long serialVersionUID = 1L;
  
  public UnrecognizedPropertyException(JsonParser p, String msg, JsonLocation loc, Class<?> referringClass, String propName, Collection<Object> propertyIds) {
    super(p, msg, loc, referringClass, propName, propertyIds);
  }
  
  @Deprecated
  public UnrecognizedPropertyException(String msg, JsonLocation loc, Class<?> referringClass, String propName, Collection<Object> propertyIds) {
    super(msg, loc, referringClass, propName, propertyIds);
  }
  
  public static UnrecognizedPropertyException from(JsonParser p, Object fromObjectOrClass, String propertyName, Collection<Object> propertyIds) {
    Class<?> ref;
    if (fromObjectOrClass instanceof Class) {
      ref = (Class)fromObjectOrClass;
    } else {
      ref = fromObjectOrClass.getClass();
    } 
    String msg = String.format("Unrecognized field \"%s\" (class %s), not marked as ignorable", new Object[] { propertyName, ref
          .getName() });
    UnrecognizedPropertyException e = new UnrecognizedPropertyException(p, msg, p.getCurrentLocation(), ref, propertyName, propertyIds);
    e.prependPath(fromObjectOrClass, propertyName);
    return e;
  }
}
