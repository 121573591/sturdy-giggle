package org.openjdk.nashorn.internal.runtime.linker;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.util.HashMap;
import java.util.Map;
import jdk.dynalink.linker.support.TypeUtilities;
import org.openjdk.nashorn.internal.lookup.Lookup;
import org.openjdk.nashorn.internal.runtime.ECMAErrors;
import org.openjdk.nashorn.internal.runtime.JSType;
import org.openjdk.nashorn.internal.runtime.ScriptRuntime;

final class JavaArgumentConverters {
  private static final MethodHandle TO_BOOLEAN = findOwnMH("toBoolean", Boolean.class, new Class[] { Object.class });
  
  private static final MethodHandle TO_STRING = findOwnMH("toString", String.class, new Class[] { Object.class });
  
  private static final MethodHandle TO_DOUBLE = findOwnMH("toDouble", Double.class, new Class[] { Object.class });
  
  private static final MethodHandle TO_NUMBER = findOwnMH("toNumber", Number.class, new Class[] { Object.class });
  
  private static final MethodHandle TO_LONG = findOwnMH("toLong", Long.class, new Class[] { Object.class });
  
  private static final MethodHandle TO_LONG_PRIMITIVE = findOwnMH("toLongPrimitive", long.class, new Class[] { Object.class });
  
  private static final MethodHandle TO_CHAR = findOwnMH("toChar", Character.class, new Class[] { Object.class });
  
  private static final MethodHandle TO_CHAR_PRIMITIVE = findOwnMH("toCharPrimitive", char.class, new Class[] { Object.class });
  
  static MethodHandle getConverter(Class<?> targetType) {
    return CONVERTERS.get(targetType);
  }
  
  private static Boolean toBoolean(Object obj) {
    if (obj instanceof Boolean)
      return (Boolean)obj; 
    if (obj == null)
      return null; 
    if (obj == ScriptRuntime.UNDEFINED)
      return null; 
    if (obj instanceof Number) {
      double num = ((Number)obj).doubleValue();
      return Boolean.valueOf((num != 0.0D && !Double.isNaN(num)));
    } 
    if (JSType.isString(obj))
      return Boolean.valueOf((((CharSequence)obj).length() > 0)); 
    if (obj instanceof org.openjdk.nashorn.internal.runtime.ScriptObject)
      return Boolean.valueOf(true); 
    throw assertUnexpectedType(obj);
  }
  
  private static Character toChar(Object o) {
    if (o == null)
      return null; 
    if (o instanceof Number) {
      int ival = ((Number)o).intValue();
      if (ival >= 0 && ival <= 65535)
        return Character.valueOf((char)ival); 
      throw ECMAErrors.typeError("cant.convert.number.to.char", new String[0]);
    } 
    String s = toString(o);
    if (s == null)
      return null; 
    if (s.length() != 1)
      throw ECMAErrors.typeError("cant.convert.string.to.char", new String[0]); 
    return Character.valueOf(s.charAt(0));
  }
  
  static char toCharPrimitive(Object obj0) {
    Character c = toChar(obj0);
    return (c == null) ? Character.MIN_VALUE : c.charValue();
  }
  
  static String toString(Object obj) {
    return (obj == null) ? null : JSType.toString(obj);
  }
  
  private static Double toDouble(Object obj0) {
    Object obj = obj0;
    while (true) {
      if (obj == null)
        return null; 
      if (obj instanceof Double)
        return (Double)obj; 
      if (obj instanceof Number)
        return Double.valueOf(((Number)obj).doubleValue()); 
      if (obj instanceof String)
        return Double.valueOf(JSType.toNumber((String)obj)); 
      if (obj instanceof org.openjdk.nashorn.internal.runtime.ConsString)
        return Double.valueOf(JSType.toNumber(obj.toString())); 
      if (obj instanceof Boolean)
        return Double.valueOf(((Boolean)obj).booleanValue() ? 1.0D : 0.0D); 
      if (obj instanceof org.openjdk.nashorn.internal.runtime.ScriptObject) {
        obj = JSType.toPrimitive(obj, Number.class);
        continue;
      } 
      break;
    } 
    if (obj == ScriptRuntime.UNDEFINED)
      return Double.valueOf(Double.NaN); 
    throw assertUnexpectedType(obj);
  }
  
  private static Number toNumber(Object obj0) {
    Object obj = obj0;
    while (true) {
      if (obj == null)
        return null; 
      if (obj instanceof Number)
        return (Number)obj; 
      if (obj instanceof String)
        return Double.valueOf(JSType.toNumber((String)obj)); 
      if (obj instanceof org.openjdk.nashorn.internal.runtime.ConsString)
        return Double.valueOf(JSType.toNumber(obj.toString())); 
      if (obj instanceof Boolean)
        return Double.valueOf(((Boolean)obj).booleanValue() ? 1.0D : 0.0D); 
      if (obj instanceof org.openjdk.nashorn.internal.runtime.ScriptObject) {
        obj = JSType.toPrimitive(obj, Number.class);
        continue;
      } 
      break;
    } 
    if (obj == ScriptRuntime.UNDEFINED)
      return Double.valueOf(Double.NaN); 
    throw assertUnexpectedType(obj);
  }
  
  private static Long toLong(Object obj0) {
    Object obj = obj0;
    while (true) {
      if (obj == null)
        return null; 
      if (obj instanceof Long)
        return (Long)obj; 
      if (obj instanceof Integer)
        return Long.valueOf(((Integer)obj).longValue()); 
      if (obj instanceof Double) {
        Double d = (Double)obj;
        if (Double.isInfinite(d.doubleValue()))
          return Long.valueOf(0L); 
        return Long.valueOf(d.longValue());
      } 
      if (obj instanceof Float) {
        Float f = (Float)obj;
        if (Float.isInfinite(f.floatValue()))
          return Long.valueOf(0L); 
        return Long.valueOf(f.longValue());
      } 
      if (obj instanceof Number)
        return Long.valueOf(((Number)obj).longValue()); 
      if (JSType.isString(obj))
        return Long.valueOf(JSType.toLong(obj)); 
      if (obj instanceof Boolean)
        return Long.valueOf(((Boolean)obj).booleanValue() ? 1L : 0L); 
      if (obj instanceof org.openjdk.nashorn.internal.runtime.ScriptObject) {
        obj = JSType.toPrimitive(obj, Number.class);
        continue;
      } 
      break;
    } 
    if (obj == ScriptRuntime.UNDEFINED)
      return null; 
    throw assertUnexpectedType(obj);
  }
  
  private static AssertionError assertUnexpectedType(Object obj) {
    return new AssertionError("Unexpected type" + obj.getClass().getName() + ". Guards should have prevented this");
  }
  
  private static long toLongPrimitive(Object obj0) {
    Long l = toLong(obj0);
    return (l == null) ? 0L : l.longValue();
  }
  
  private static MethodHandle findOwnMH(String name, Class<?> rtype, Class<?>... types) {
    return Lookup.MH.findStatic(MethodHandles.lookup(), JavaArgumentConverters.class, name, Lookup.MH.type(rtype, types));
  }
  
  private static final Map<Class<?>, MethodHandle> CONVERTERS = new HashMap<>();
  
  static {
    CONVERTERS.put(Number.class, TO_NUMBER);
    CONVERTERS.put(String.class, TO_STRING);
    CONVERTERS.put(boolean.class, JSType.TO_BOOLEAN.methodHandle());
    CONVERTERS.put(Boolean.class, TO_BOOLEAN);
    CONVERTERS.put(char.class, TO_CHAR_PRIMITIVE);
    CONVERTERS.put(Character.class, TO_CHAR);
    CONVERTERS.put(double.class, JSType.TO_NUMBER.methodHandle());
    CONVERTERS.put(Double.class, TO_DOUBLE);
    CONVERTERS.put(long.class, TO_LONG_PRIMITIVE);
    CONVERTERS.put(Long.class, TO_LONG);
    putLongConverter(Byte.class);
    putLongConverter(Short.class);
    putLongConverter(Integer.class);
    putDoubleConverter(Float.class);
  }
  
  private static void putDoubleConverter(Class<?> targetType) {
    Class<?> primitive = TypeUtilities.getPrimitiveType(targetType);
    CONVERTERS.put(primitive, Lookup.MH.explicitCastArguments(JSType.TO_NUMBER.methodHandle(), JSType.TO_NUMBER.methodHandle().type().changeReturnType(primitive)));
    CONVERTERS.put(targetType, Lookup.MH.filterReturnValue(TO_DOUBLE, findOwnMH(primitive.getName() + "Value", targetType, new Class[] { Double.class })));
  }
  
  private static void putLongConverter(Class<?> targetType) {
    Class<?> primitive = TypeUtilities.getPrimitiveType(targetType);
    CONVERTERS.put(primitive, Lookup.MH.explicitCastArguments(TO_LONG_PRIMITIVE, TO_LONG_PRIMITIVE.type().changeReturnType(primitive)));
    CONVERTERS.put(targetType, Lookup.MH.filterReturnValue(TO_LONG, findOwnMH(primitive.getName() + "Value", targetType, new Class[] { Long.class })));
  }
  
  private static Byte byteValue(Long l) {
    return (l == null) ? null : Byte.valueOf(l.byteValue());
  }
  
  private static Short shortValue(Long l) {
    return (l == null) ? null : Short.valueOf(l.shortValue());
  }
  
  private static Integer intValue(Long l) {
    return (l == null) ? null : Integer.valueOf(l.intValue());
  }
  
  private static Float floatValue(Double d) {
    return (d == null) ? null : Float.valueOf(d.floatValue());
  }
}
