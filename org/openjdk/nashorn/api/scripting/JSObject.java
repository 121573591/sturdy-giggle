package org.openjdk.nashorn.api.scripting;

import java.util.Collection;
import java.util.Set;
import org.openjdk.nashorn.internal.runtime.JSType;

public interface JSObject {
  Object call(Object paramObject, Object... paramVarArgs);
  
  Object newObject(Object... paramVarArgs);
  
  Object eval(String paramString);
  
  Object getMember(String paramString);
  
  Object getSlot(int paramInt);
  
  boolean hasMember(String paramString);
  
  boolean hasSlot(int paramInt);
  
  void removeMember(String paramString);
  
  void setMember(String paramString, Object paramObject);
  
  void setSlot(int paramInt, Object paramObject);
  
  Set<String> keySet();
  
  Collection<Object> values();
  
  boolean isInstance(Object paramObject);
  
  boolean isInstanceOf(Object paramObject);
  
  String getClassName();
  
  boolean isFunction();
  
  boolean isStrictFunction();
  
  boolean isArray();
  
  @Deprecated
  default double toNumber() {
    return JSType.toNumber(JSType.toPrimitive(this, Number.class));
  }
  
  default Object getDefaultValue(Class<?> hint) throws UnsupportedOperationException {
    return DefaultValueImpl.getDefaultValue(this, hint);
  }
}
