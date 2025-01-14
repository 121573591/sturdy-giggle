package org.openjdk.nashorn.api.scripting;

import org.openjdk.nashorn.internal.runtime.JSType;

class DefaultValueImpl {
  private static final String[] DEFAULT_VALUE_FNS_NUMBER = new String[] { "valueOf", "toString" };
  
  private static final String[] DEFAULT_VALUE_FNS_STRING = new String[] { "toString", "valueOf" };
  
  static Object getDefaultValue(JSObject jsobj, Class<?> hint) throws UnsupportedOperationException {
    boolean isNumber = (hint == null || hint == Number.class);
    for (String methodName : isNumber ? DEFAULT_VALUE_FNS_NUMBER : DEFAULT_VALUE_FNS_STRING) {
      Object objMember = jsobj.getMember(methodName);
      if (objMember instanceof JSObject) {
        JSObject member = (JSObject)objMember;
        if (member.isFunction()) {
          Object value = member.call(jsobj, new Object[0]);
          if (JSType.isPrimitive(value))
            return value; 
        } 
      } 
    } 
    throw new UnsupportedOperationException(isNumber ? "cannot.get.default.number" : "cannot.get.default.string");
  }
}
