package org.openjdk.nashorn.internal.runtime.linker;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import jdk.dynalink.CallSiteDescriptor;
import jdk.dynalink.StandardOperation;
import jdk.dynalink.linker.GuardedInvocation;
import jdk.dynalink.linker.LinkRequest;
import jdk.dynalink.linker.LinkerServices;
import jdk.dynalink.linker.TypeBasedGuardingDynamicLinker;
import org.openjdk.nashorn.internal.lookup.MethodHandleFactory;
import org.openjdk.nashorn.internal.lookup.MethodHandleFunctionality;
import org.openjdk.nashorn.internal.runtime.JSType;

final class BrowserJSObjectLinker implements TypeBasedGuardingDynamicLinker {
  private static final String JSOBJECT_CLASS = "netscape.javascript.JSObject";
  
  private static final Class<?> jsObjectClass = findBrowserJSObjectClass();
  
  private final NashornBeansLinker nashornBeansLinker;
  
  BrowserJSObjectLinker(NashornBeansLinker nashornBeansLinker) {
    this.nashornBeansLinker = nashornBeansLinker;
  }
  
  public boolean canLinkType(Class<?> type) {
    return canLinkTypeStatic(type);
  }
  
  static boolean canLinkTypeStatic(Class<?> type) {
    return (jsObjectClass != null && jsObjectClass.isAssignableFrom(type));
  }
  
  private static void checkJSObjectClass() {
    assert jsObjectClass != null : "netscape.javascript.JSObject not found!";
  }
  
  public GuardedInvocation getGuardedInvocation(LinkRequest request, LinkerServices linkerServices) throws Exception {
    Object self = request.getReceiver();
    CallSiteDescriptor desc = request.getCallSiteDescriptor();
    checkJSObjectClass();
    assert jsObjectClass.isInstance(self);
    GuardedInvocation inv = lookup(desc, request, linkerServices);
    inv = inv.replaceMethods(linkerServices.filterInternalObjects(inv.getInvocation()), inv.getGuard());
    return Bootstrap.asTypeSafeReturn(inv, linkerServices, desc);
  }
  
  private GuardedInvocation lookup(CallSiteDescriptor desc, LinkRequest request, LinkerServices linkerServices) {
    GuardedInvocation inv;
    try {
      inv = this.nashornBeansLinker.getGuardedInvocation(request, linkerServices);
    } catch (Throwable th) {
      inv = null;
    } 
    String name = NashornCallSiteDescriptor.getOperand(desc);
    switch (NashornCallSiteDescriptor.getStandardOperation(desc)) {
      case GET:
        return (name != null) ? findGetMethod(name, inv) : findGetIndexMethod(inv);
      case SET:
        return (name != null) ? findSetMethod(name, inv) : findSetIndexMethod();
      case CALL:
        return findCallMethod(desc);
    } 
    return null;
  }
  
  private static GuardedInvocation findGetMethod(String name, GuardedInvocation inv) {
    if (inv != null)
      return inv; 
    MethodHandle getter = MH.insertArguments(JSObjectHandles.JSOBJECT_GETMEMBER, 1, new Object[] { name });
    return new GuardedInvocation(getter, IS_JSOBJECT_GUARD);
  }
  
  private static GuardedInvocation findGetIndexMethod(GuardedInvocation inv) {
    MethodHandle getter = MH.insertArguments(JSOBJECTLINKER_GET, 0, new Object[] { inv.getInvocation() });
    return inv.replaceMethods(getter, inv.getGuard());
  }
  
  private static GuardedInvocation findSetMethod(String name, GuardedInvocation inv) {
    if (inv != null)
      return inv; 
    MethodHandle getter = MH.insertArguments(JSObjectHandles.JSOBJECT_SETMEMBER, 1, new Object[] { name });
    return new GuardedInvocation(getter, IS_JSOBJECT_GUARD);
  }
  
  private static GuardedInvocation findSetIndexMethod() {
    return new GuardedInvocation(JSOBJECTLINKER_PUT, IS_JSOBJECT_GUARD);
  }
  
  private static GuardedInvocation findCallMethod(CallSiteDescriptor desc) {
    MethodHandle call = MH.insertArguments(JSObjectHandles.JSOBJECT_CALL, 1, new Object[] { "call" });
    return new GuardedInvocation(MH.asCollector(call, Object[].class, desc.getMethodType().parameterCount() - 1), IS_JSOBJECT_GUARD);
  }
  
  private static boolean isJSObject(Object self) {
    return jsObjectClass.isInstance(self);
  }
  
  private static Object get(MethodHandle fallback, Object jsobj, Object key) throws Throwable {
    if (key instanceof Integer)
      return JSObjectHandles.JSOBJECT_GETSLOT.invokeExact(jsobj, ((Integer)key).intValue()); 
    if (key instanceof Number) {
      int index = getIndex((Number)key);
      if (index > -1)
        return JSObjectHandles.JSOBJECT_GETSLOT.invokeExact(jsobj, index); 
    } else if (JSType.isString(key)) {
      String name = key.toString();
      if (name.indexOf('(') != -1)
        return fallback.invokeExact(jsobj, name); 
      return JSObjectHandles.JSOBJECT_GETMEMBER.invokeExact(jsobj, name);
    } 
    return null;
  }
  
  private static void put(Object jsobj, Object key, Object value) throws Throwable {
    if (key instanceof Integer) {
      JSObjectHandles.JSOBJECT_SETSLOT.invokeExact(jsobj, ((Integer)key).intValue(), value);
    } else if (key instanceof Number) {
      JSObjectHandles.JSOBJECT_SETSLOT.invokeExact(jsobj, getIndex((Number)key), value);
    } else if (JSType.isString(key)) {
      JSObjectHandles.JSOBJECT_SETMEMBER.invokeExact(jsobj, key.toString(), value);
    } 
  }
  
  private static int getIndex(Number n) {
    double value = n.doubleValue();
    return JSType.isRepresentableAsInt(value) ? (int)value : -1;
  }
  
  private static final MethodHandleFunctionality MH = MethodHandleFactory.getFunctionality();
  
  private static final MethodHandle IS_JSOBJECT_GUARD = findOwnMH_S("isJSObject", boolean.class, new Class[] { Object.class });
  
  private static final MethodHandle JSOBJECTLINKER_GET = findOwnMH_S("get", Object.class, new Class[] { MethodHandle.class, Object.class, Object.class });
  
  private static final MethodHandle JSOBJECTLINKER_PUT = findOwnMH_S("put", void.class, new Class[] { Object.class, Object.class, Object.class });
  
  private static MethodHandle findOwnMH_S(String name, Class<?> rtype, Class<?>... types) {
    return MH.findStatic(MethodHandles.lookup(), BrowserJSObjectLinker.class, name, MH.type(rtype, types));
  }
  
  static class JSObjectHandles {
    static final MethodHandle JSOBJECT_GETMEMBER = findJSObjectMH_V("getMember", Object.class, new Class[] { String.class }).asType(BrowserJSObjectLinker.MH.type(Object.class, new Class[] { Object.class, String.class }));
    
    static final MethodHandle JSOBJECT_GETSLOT = findJSObjectMH_V("getSlot", Object.class, new Class[] { int.class }).asType(BrowserJSObjectLinker.MH.type(Object.class, new Class[] { Object.class, int.class }));
    
    static final MethodHandle JSOBJECT_SETMEMBER = findJSObjectMH_V("setMember", void.class, new Class[] { String.class, Object.class }).asType(BrowserJSObjectLinker.MH.type(void.class, new Class[] { Object.class, String.class, Object.class }));
    
    static final MethodHandle JSOBJECT_SETSLOT = findJSObjectMH_V("setSlot", void.class, new Class[] { int.class, Object.class }).asType(BrowserJSObjectLinker.MH.type(void.class, new Class[] { Object.class, int.class, Object.class }));
    
    static final MethodHandle JSOBJECT_CALL = findJSObjectMH_V("call", Object.class, new Class[] { String.class, Object[].class }).asType(BrowserJSObjectLinker.MH.type(Object.class, new Class[] { Object.class, String.class, Object[].class }));
    
    private static MethodHandle findJSObjectMH_V(String name, Class<?> rtype, Class<?>... types) {
      BrowserJSObjectLinker.checkJSObjectClass();
      return BrowserJSObjectLinker.MH.findVirtual(MethodHandles.publicLookup(), BrowserJSObjectLinker.jsObjectClass, name, BrowserJSObjectLinker.MH.type(rtype, types));
    }
  }
  
  private static Class<?> findBrowserJSObjectClass() {
    ClassLoader extLoader = BrowserJSObjectLinker.class.getClassLoader();
    if (extLoader == null)
      extLoader = ClassLoader.getSystemClassLoader().getParent(); 
    try {
      return Class.forName("netscape.javascript.JSObject", false, extLoader);
    } catch (ClassNotFoundException e) {
      return null;
    } 
  }
}
