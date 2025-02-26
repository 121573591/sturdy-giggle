package com.fasterxml.jackson.databind.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class BeanUtil {
  @Deprecated
  public static String okNameForGetter(AnnotatedMethod am, boolean stdNaming) {
    String name = am.getName();
    String str = okNameForIsGetter(am, name, stdNaming);
    if (str == null)
      str = okNameForRegularGetter(am, name, stdNaming); 
    return str;
  }
  
  @Deprecated
  public static String okNameForRegularGetter(AnnotatedMethod am, String name, boolean stdNaming) {
    if (name.startsWith("get")) {
      if ("getCallbacks".equals(name)) {
        if (isCglibGetCallbacks(am))
          return null; 
      } else if ("getMetaClass".equals(name)) {
        if (isGroovyMetaClassGetter(am))
          return null; 
      } 
      return stdNaming ? 
        stdManglePropertyName(name, 3) : 
        legacyManglePropertyName(name, 3);
    } 
    return null;
  }
  
  @Deprecated
  public static String okNameForIsGetter(AnnotatedMethod am, String name, boolean stdNaming) {
    if (name.startsWith("is")) {
      Class<?> rt = am.getRawType();
      if (rt == Boolean.class || rt == boolean.class)
        return stdNaming ? 
          stdManglePropertyName(name, 2) : 
          legacyManglePropertyName(name, 2); 
    } 
    return null;
  }
  
  @Deprecated
  public static String okNameForSetter(AnnotatedMethod am, boolean stdNaming) {
    return okNameForMutator(am, "set", stdNaming);
  }
  
  @Deprecated
  public static String okNameForMutator(AnnotatedMethod am, String prefix, boolean stdNaming) {
    String name = am.getName();
    if (name.startsWith(prefix))
      return stdNaming ? 
        stdManglePropertyName(name, prefix.length()) : 
        legacyManglePropertyName(name, prefix.length()); 
    return null;
  }
  
  public static Object getDefaultValue(JavaType type) {
    Class<?> cls = type.getRawClass();
    Class<?> prim = ClassUtil.primitiveType(cls);
    if (prim != null)
      return ClassUtil.defaultValue(prim); 
    if (type.isContainerType() || type.isReferenceType())
      return JsonInclude.Include.NON_EMPTY; 
    if (cls == String.class)
      return ""; 
    if (type.isTypeOrSubTypeOf(Date.class))
      return new Date(0L); 
    if (type.isTypeOrSubTypeOf(Calendar.class)) {
      Calendar c = new GregorianCalendar();
      c.setTimeInMillis(0L);
      return c;
    } 
    return null;
  }
  
  protected static boolean isCglibGetCallbacks(AnnotatedMethod am) {
    Class<?> rt = am.getRawType();
    if (rt.isArray()) {
      Class<?> compType = rt.getComponentType();
      String className = compType.getName();
      if (className.contains(".cglib"))
        return (className.startsWith("net.sf.cglib") || className
          
          .startsWith("org.hibernate.repackage.cglib") || className
          
          .startsWith("org.springframework.cglib")); 
    } 
    return false;
  }
  
  protected static boolean isGroovyMetaClassGetter(AnnotatedMethod am) {
    return am.getRawType().getName().startsWith("groovy.lang");
  }
  
  protected static String legacyManglePropertyName(String basename, int offset) {
    int end = basename.length();
    if (end == offset)
      return null; 
    char c = basename.charAt(offset);
    char d = Character.toLowerCase(c);
    if (c == d)
      return basename.substring(offset); 
    StringBuilder sb = new StringBuilder(end - offset);
    sb.append(d);
    int i = offset + 1;
    for (; i < end; i++) {
      c = basename.charAt(i);
      d = Character.toLowerCase(c);
      if (c == d) {
        sb.append(basename, i, end);
        break;
      } 
      sb.append(d);
    } 
    return sb.toString();
  }
  
  public static String stdManglePropertyName(String basename, int offset) {
    int end = basename.length();
    if (end == offset)
      return null; 
    char c0 = basename.charAt(offset);
    char c1 = Character.toLowerCase(c0);
    if (c0 == c1)
      return basename.substring(offset); 
    if (offset + 1 < end && 
      Character.isUpperCase(basename.charAt(offset + 1)))
      return basename.substring(offset); 
    StringBuilder sb = new StringBuilder(end - offset);
    sb.append(c1);
    sb.append(basename, offset + 1, end);
    return sb.toString();
  }
  
  public static String checkUnsupportedType(JavaType type) {
    String typeName, moduleName, className = type.getRawClass().getName();
    if (isJava8TimeClass(className)) {
      if (className.indexOf('.', 10) >= 0)
        return null; 
      typeName = "Java 8 date/time";
      moduleName = "com.fasterxml.jackson.datatype:jackson-datatype-jsr310";
    } else if (isJodaTimeClass(className)) {
      typeName = "Joda date/time";
      moduleName = "com.fasterxml.jackson.datatype:jackson-datatype-joda";
    } else if (isJava8OptionalClass(className)) {
      typeName = "Java 8 optional";
      moduleName = "com.fasterxml.jackson.datatype:jackson-datatype-jdk8";
    } else {
      return null;
    } 
    return String.format("%s type %s not supported by default: add Module \"%s\" to enable handling", new Object[] { typeName, 
          ClassUtil.getTypeDescription(type), moduleName });
  }
  
  public static boolean isJava8TimeClass(Class<?> rawType) {
    return isJava8TimeClass(rawType.getName());
  }
  
  private static boolean isJava8TimeClass(String className) {
    return className.startsWith("java.time.");
  }
  
  public static boolean isJava8OptionalClass(Class<?> rawType) {
    return isJava8OptionalClass(rawType.getName());
  }
  
  private static boolean isJava8OptionalClass(String className) {
    return className.startsWith("java.util.Optional");
  }
  
  public static boolean isJodaTimeClass(Class<?> rawType) {
    return isJodaTimeClass(rawType.getName());
  }
  
  private static boolean isJodaTimeClass(String className) {
    return className.startsWith("org.joda.time.");
  }
}
