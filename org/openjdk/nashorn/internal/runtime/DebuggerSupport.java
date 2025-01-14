package org.openjdk.nashorn.internal.runtime;

import java.lang.invoke.MethodHandle;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import org.openjdk.nashorn.internal.codegen.CompilerConstants;
import org.openjdk.nashorn.internal.objects.Global;
import org.openjdk.nashorn.internal.scripts.JS;

final class DebuggerSupport {
  static boolean FORCELOAD = true;
  
  static {
    DebuggerValueDesc forceLoad = new DebuggerValueDesc(null, false, null, null);
    SourceInfo sourceInfo = new SourceInfo(null, 0, null, null);
  }
  
  static class DebuggerValueDesc {
    final String key;
    
    final boolean expandable;
    
    final Object valueAsObject;
    
    final String valueAsString;
    
    DebuggerValueDesc(String key, boolean expandable, Object valueAsObject, String valueAsString) {
      this.key = key;
      this.expandable = expandable;
      this.valueAsObject = valueAsObject;
      this.valueAsString = valueAsString;
    }
  }
  
  static class SourceInfo {
    final String name;
    
    final URL url;
    
    final int hash;
    
    final char[] content;
    
    SourceInfo(String name, int hash, URL url, char[] content) {
      this.name = name;
      this.hash = hash;
      this.url = url;
      this.content = content;
    }
  }
  
  static void notifyInvoke(MethodHandle mh) {}
  
  static SourceInfo getSourceInfo(Class<?> clazz) {
    if (JS.class.isAssignableFrom(clazz))
      try {
        Field sourceField = clazz.getDeclaredField(CompilerConstants.SOURCE.symbolName());
        sourceField.setAccessible(true);
        Source src = (Source)sourceField.get(null);
        return src.getSourceInfo();
      } catch (IllegalAccessException|NoSuchFieldException ignored) {
        return null;
      }  
    return null;
  }
  
  static Object getGlobal() {
    return Context.getGlobal();
  }
  
  static Object eval(ScriptObject scope, Object self, String string, boolean returnException) {
    Global global = Context.getGlobal();
    ScriptObject initialScope = (scope != null) ? scope : (ScriptObject)global;
    Object callThis = (self != null) ? self : global;
    Context context = global.getContext();
    try {
      return context.eval(initialScope, string, callThis, ScriptRuntime.UNDEFINED);
    } catch (Throwable ex) {
      return returnException ? ex : null;
    } 
  }
  
  static DebuggerValueDesc[] valueInfos(Object object, boolean all) {
    assert object instanceof ScriptObject;
    return getDebuggerValueDescs((ScriptObject)object, all, new HashSet());
  }
  
  static DebuggerValueDesc valueInfo(String name, Object value, boolean all) {
    return valueInfo(name, value, all, new HashSet());
  }
  
  private static DebuggerValueDesc valueInfo(String name, Object value, boolean all, Set<Object> duplicates) {
    if (value instanceof ScriptObject && !(value instanceof ScriptFunction)) {
      ScriptObject object = (ScriptObject)value;
      return new DebuggerValueDesc(name, !object.isEmpty(), value, objectAsString(object, all, duplicates));
    } 
    return new DebuggerValueDesc(name, false, value, valueAsString(value));
  }
  
  private static DebuggerValueDesc[] getDebuggerValueDescs(ScriptObject object, boolean all, Set<Object> duplicates) {
    if (duplicates.contains(object))
      return null; 
    duplicates.add(object);
    String[] keys = object.getOwnKeys(all);
    DebuggerValueDesc[] descs = new DebuggerValueDesc[keys.length];
    for (int i = 0; i < keys.length; i++) {
      String key = keys[i];
      descs[i] = valueInfo(key, object.get(key), all, duplicates);
    } 
    duplicates.remove(object);
    return descs;
  }
  
  private static String objectAsString(ScriptObject object, boolean all, Set<Object> duplicates) {
    StringBuilder sb = new StringBuilder();
    if (ScriptObject.isArray(object)) {
      sb.append('[');
      long length = (long)object.getDouble("length", -1);
      long i;
      for (i = 0L; i < length; i++) {
        if (object.has(i)) {
          Object valueAsObject = object.get(i);
          boolean isUndefined = (valueAsObject == ScriptRuntime.UNDEFINED);
          if (isUndefined) {
            if (i != 0L)
              sb.append(","); 
          } else {
            if (i != 0L)
              sb.append(", "); 
            if (valueAsObject instanceof ScriptObject && !(valueAsObject instanceof ScriptFunction)) {
              String objectString = objectAsString((ScriptObject)valueAsObject, all, duplicates);
              sb.append((objectString != null) ? objectString : "{...}");
            } else {
              sb.append(valueAsString(valueAsObject));
            } 
          } 
        } else if (i != 0L) {
          sb.append(',');
        } 
      } 
      sb.append(']');
    } else {
      sb.append('{');
      DebuggerValueDesc[] descs = getDebuggerValueDescs(object, all, duplicates);
      if (descs != null)
        for (int i = 0; i < descs.length; i++) {
          if (i != 0)
            sb.append(", "); 
          String valueAsString = (descs[i]).valueAsString;
          sb.append((descs[i]).key);
          sb.append(": ");
          sb.append(valueAsString);
        }  
      sb.append('}');
    } 
    return sb.toString();
  }
  
  static String valueAsString(Object value) {
    JSType type = JSType.of(value);
    switch (type) {
      case BOOLEAN:
        return value.toString();
      case STRING:
        return escape(value.toString());
      case NUMBER:
        return JSType.toString(((Number)value).doubleValue());
      case NULL:
        return "null";
      case UNDEFINED:
        return "undefined";
      case OBJECT:
        return ScriptRuntime.safeToString(value);
      case FUNCTION:
        if (value instanceof ScriptFunction)
          return ((ScriptFunction)value).toSource(); 
        return value.toString();
    } 
    return value.toString();
  }
  
  private static String escape(String value) {
    StringBuilder sb = new StringBuilder();
    sb.append("\"");
    for (char ch : value.toCharArray()) {
      switch (ch) {
        case '\\':
          sb.append("\\\\");
          break;
        case '"':
          sb.append("\\\"");
          break;
        case '\'':
          sb.append("\\'");
          break;
        case '\b':
          sb.append("\\b");
          break;
        case '\f':
          sb.append("\\f");
          break;
        case '\n':
          sb.append("\\n");
          break;
        case '\r':
          sb.append("\\r");
          break;
        case '\t':
          sb.append("\\t");
          break;
        default:
          if (ch < ' ' || ch >= 'ÿ') {
            sb.append("\\u");
            String hex = Integer.toHexString(ch);
            for (int i = hex.length(); i < 4; i++)
              sb.append('0'); 
            sb.append(hex);
            break;
          } 
          sb.append(ch);
          break;
      } 
    } 
    sb.append("\"");
    return sb.toString();
  }
}
