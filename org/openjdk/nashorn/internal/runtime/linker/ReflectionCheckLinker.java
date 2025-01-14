package org.openjdk.nashorn.internal.runtime.linker;

import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import jdk.dynalink.CallSiteDescriptor;
import jdk.dynalink.StandardNamespace;
import jdk.dynalink.StandardOperation;
import jdk.dynalink.linker.GuardedInvocation;
import jdk.dynalink.linker.LinkRequest;
import jdk.dynalink.linker.LinkerServices;
import jdk.dynalink.linker.TypeBasedGuardingDynamicLinker;
import org.openjdk.nashorn.api.scripting.ClassFilter;
import org.openjdk.nashorn.internal.objects.Global;
import org.openjdk.nashorn.internal.runtime.Context;
import org.openjdk.nashorn.internal.runtime.ECMAErrors;

final class ReflectionCheckLinker implements TypeBasedGuardingDynamicLinker {
  private static final Class<?> STATEMENT_CLASS = getBeanClass("Statement");
  
  private static final Class<?> XMLENCODER_CLASS = getBeanClass("XMLEncoder");
  
  private static final Class<?> XMLDECODER_CLASS = getBeanClass("XMLDecoder");
  
  private static Class<?> getBeanClass(String name) {
    try {
      return Class.forName("java.beans." + name);
    } catch (ClassNotFoundException cnfe) {
      return null;
    } 
  }
  
  public boolean canLinkType(Class<?> type) {
    return isReflectionClass(type);
  }
  
  private static boolean isReflectionClass(Class<?> type) {
    if (type == Class.class || ClassLoader.class.isAssignableFrom(type))
      return true; 
    if ((STATEMENT_CLASS != null && STATEMENT_CLASS.isAssignableFrom(type)) || (XMLENCODER_CLASS != null && XMLENCODER_CLASS
      .isAssignableFrom(type)) || (XMLDECODER_CLASS != null && XMLDECODER_CLASS
      .isAssignableFrom(type)))
      return true; 
    String name = type.getName();
    return (name.startsWith("java.lang.reflect.") || name.startsWith("java.lang.invoke."));
  }
  
  public GuardedInvocation getGuardedInvocation(LinkRequest origRequest, LinkerServices linkerServices) {
    checkLinkRequest(origRequest);
    return null;
  }
  
  private static boolean isReflectiveCheckNeeded(Class<?> type, boolean isStatic) {
    if (Proxy.class.isAssignableFrom(type)) {
      if (Proxy.isProxyClass(type))
        return isStatic; 
      return true;
    } 
    return isReflectionClass(type);
  }
  
  static void checkReflectionAccess(Class<?> clazz, boolean isStatic) {
    Global global = Context.getGlobal();
    ClassFilter cf = global.getClassFilter();
    if (cf != null && isReflectiveCheckNeeded(clazz, isStatic))
      throw ECMAErrors.typeError("no.reflection.with.classfilter", new String[0]); 
    SecurityManager sm = System.getSecurityManager();
    if (sm != null && isReflectiveCheckNeeded(clazz, isStatic))
      checkReflectionPermission(sm); 
  }
  
  private static void checkLinkRequest(LinkRequest request) {
    Global global = Context.getGlobal();
    ClassFilter cf = global.getClassFilter();
    if (cf != null)
      throw ECMAErrors.typeError("no.reflection.with.classfilter", new String[0]); 
    SecurityManager sm = System.getSecurityManager();
    if (sm != null) {
      Object self = request.getReceiver();
      if (self instanceof Class && Modifier.isPublic(((Class)self).getModifiers())) {
        CallSiteDescriptor desc = request.getCallSiteDescriptor();
        if ("static".equals(NashornCallSiteDescriptor.getOperand(desc)) && NashornCallSiteDescriptor.contains(desc, StandardOperation.GET, StandardNamespace.PROPERTY) && 
          Context.isAccessibleClass((Class)self) && !isReflectionClass((Class)self))
          return; 
      } 
      checkReflectionPermission(sm);
    } 
  }
  
  private static void checkReflectionPermission(SecurityManager sm) {
    sm.checkPermission(new RuntimePermission("nashorn.JavaReflection"));
  }
}
