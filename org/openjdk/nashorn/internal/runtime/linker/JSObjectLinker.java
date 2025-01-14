package org.openjdk.nashorn.internal.runtime.linker;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.Map;
import java.util.Objects;
import jdk.dynalink.CallSiteDescriptor;
import jdk.dynalink.Operation;
import jdk.dynalink.StandardOperation;
import jdk.dynalink.linker.GuardedInvocation;
import jdk.dynalink.linker.LinkRequest;
import jdk.dynalink.linker.LinkerServices;
import jdk.dynalink.linker.TypeBasedGuardingDynamicLinker;
import org.openjdk.nashorn.api.scripting.JSObject;
import org.openjdk.nashorn.api.scripting.ScriptObjectMirror;
import org.openjdk.nashorn.internal.lookup.MethodHandleFactory;
import org.openjdk.nashorn.internal.lookup.MethodHandleFunctionality;
import org.openjdk.nashorn.internal.objects.Global;
import org.openjdk.nashorn.internal.runtime.Context;
import org.openjdk.nashorn.internal.runtime.JSType;
import org.openjdk.nashorn.internal.runtime.ScriptRuntime;

final class JSObjectLinker implements TypeBasedGuardingDynamicLinker {
  private final NashornBeansLinker nashornBeansLinker;
  
  JSObjectLinker(NashornBeansLinker nashornBeansLinker) {
    this.nashornBeansLinker = nashornBeansLinker;
  }
  
  public boolean canLinkType(Class<?> type) {
    return canLinkTypeStatic(type);
  }
  
  private static boolean canLinkTypeStatic(Class<?> type) {
    return (Map.class.isAssignableFrom(type) || JSObject.class
      .isAssignableFrom(type));
  }
  
  public GuardedInvocation getGuardedInvocation(LinkRequest request, LinkerServices linkerServices) throws Exception {
    GuardedInvocation inv;
    Object self = request.getReceiver();
    CallSiteDescriptor desc = request.getCallSiteDescriptor();
    if (self == null || !canLinkTypeStatic(self.getClass()))
      return null; 
    if (self instanceof JSObject) {
      inv = lookup(desc, request, linkerServices);
      inv = inv.replaceMethods(linkerServices.filterInternalObjects(inv.getInvocation()), inv.getGuard());
    } else if (self instanceof Map) {
      GuardedInvocation beanInv = this.nashornBeansLinker.getGuardedInvocation(request, linkerServices);
      inv = new GuardedInvocation(beanInv.getInvocation(), NashornGuards.combineGuards(beanInv.getGuard(), NashornGuards.getNotJSObjectGuard()));
    } else {
      throw new AssertionError("got instanceof: " + self.getClass());
    } 
    return Bootstrap.asTypeSafeReturn(inv, linkerServices, desc);
  }
  
  private GuardedInvocation lookup(CallSiteDescriptor desc, LinkRequest request, LinkerServices linkerServices) throws Exception {
    Operation op = NashornCallSiteDescriptor.getBaseOperation(desc);
    if (op instanceof StandardOperation) {
      String name = NashornCallSiteDescriptor.getOperand(desc);
      switch ((StandardOperation)op) {
        case GET:
          if (NashornCallSiteDescriptor.hasStandardNamespace(desc)) {
            if (name != null)
              return findGetMethod(name); 
            return findGetIndexMethod(this.nashornBeansLinker.getGuardedInvocation(request, linkerServices));
          } 
          break;
        case SET:
          if (NashornCallSiteDescriptor.hasStandardNamespace(desc))
            return (name != null) ? findSetMethod(name) : findSetIndexMethod(); 
          break;
        case REMOVE:
          if (NashornCallSiteDescriptor.hasStandardNamespace(desc))
            return new GuardedInvocation(
                (name == null) ? JSOBJECTLINKER_DEL : MH.insertArguments(JSOBJECTLINKER_DEL, 1, new Object[] { name }), IS_JSOBJECT_GUARD); 
          break;
        case CALL:
          return findCallMethod(desc);
        case NEW:
          return findNewMethod(desc);
      } 
    } 
    return null;
  }
  
  private static GuardedInvocation findGetMethod(String name) {
    MethodHandle getter = MH.insertArguments(JSOBJECT_GETMEMBER, 1, new Object[] { name });
    return new GuardedInvocation(getter, IS_JSOBJECT_GUARD);
  }
  
  private static GuardedInvocation findGetIndexMethod(GuardedInvocation inv) {
    MethodHandle getter = MH.insertArguments(JSOBJECTLINKER_GET, 0, new Object[] { inv.getInvocation() });
    return inv.replaceMethods(getter, inv.getGuard());
  }
  
  private static GuardedInvocation findSetMethod(String name) {
    MethodHandle getter = MH.insertArguments(JSOBJECT_SETMEMBER, 1, new Object[] { name });
    return new GuardedInvocation(getter, IS_JSOBJECT_GUARD);
  }
  
  private static GuardedInvocation findSetIndexMethod() {
    return new GuardedInvocation(JSOBJECTLINKER_PUT, IS_JSOBJECT_GUARD);
  }
  
  private static GuardedInvocation findCallMethod(CallSiteDescriptor desc) {
    MethodHandle mh = NashornCallSiteDescriptor.isScope(desc) ? JSOBJECT_SCOPE_CALL : JSOBJECT_CALL;
    if (NashornCallSiteDescriptor.isApplyToCall(desc))
      mh = MH.insertArguments(JSOBJECT_CALL_TO_APPLY, 0, new Object[] { mh }); 
    MethodType type = desc.getMethodType();
    mh = (type.parameterType(type.parameterCount() - 1) == Object[].class) ? mh : MH.asCollector(mh, Object[].class, type.parameterCount() - 2);
    return new GuardedInvocation(mh, IS_JSOBJECT_GUARD);
  }
  
  private static GuardedInvocation findNewMethod(CallSiteDescriptor desc) {
    MethodHandle func = MH.asCollector(JSOBJECT_NEW, Object[].class, desc.getMethodType().parameterCount() - 1);
    return new GuardedInvocation(func, IS_JSOBJECT_GUARD);
  }
  
  private static boolean isJSObject(Object self) {
    return self instanceof JSObject;
  }
  
  private static Object get(MethodHandle fallback, Object jsobj, Object key) throws Throwable {
    if (key instanceof Integer)
      return ((JSObject)jsobj).getSlot(((Integer)key).intValue()); 
    if (key instanceof Number) {
      int index = getIndex((Number)key);
      if (index > -1)
        return ((JSObject)jsobj).getSlot(index); 
      return ((JSObject)jsobj).getMember(JSType.toString(key));
    } 
    if (JSType.isString(key)) {
      String name = key.toString();
      if (name.indexOf('(') != -1)
        return fallback.invokeExact(jsobj, name); 
      return ((JSObject)jsobj).getMember(name);
    } 
    return null;
  }
  
  private static void put(Object jsobj, Object key, Object value) {
    if (key instanceof Integer) {
      ((JSObject)jsobj).setSlot(((Integer)key).intValue(), value);
    } else if (key instanceof Number) {
      int index = getIndex((Number)key);
      if (index > -1) {
        ((JSObject)jsobj).setSlot(index, value);
      } else {
        ((JSObject)jsobj).setMember(JSType.toString(key), value);
      } 
    } else if (JSType.isString(key)) {
      ((JSObject)jsobj).setMember(key.toString(), value);
    } 
  }
  
  private static boolean del(Object jsobj, Object key) {
    if (jsobj instanceof ScriptObjectMirror)
      return ((ScriptObjectMirror)jsobj).delete(key); 
    ((JSObject)jsobj).removeMember(Objects.toString(key));
    return true;
  }
  
  private static int getIndex(Number n) {
    double value = n.doubleValue();
    return JSType.isRepresentableAsInt(value) ? (int)value : -1;
  }
  
  private static Object callToApply(MethodHandle mh, JSObject obj, Object thiz, Object... args) {
    assert args.length >= 1;
    Object receiver = args[0];
    Object[] arguments = new Object[args.length - 1];
    System.arraycopy(args, 1, arguments, 0, arguments.length);
    try {
      return mh.invokeExact(obj, thiz, new Object[] { receiver, arguments });
    } catch (RuntimeException|Error e) {
      throw e;
    } catch (Throwable e) {
      throw new RuntimeException(e);
    } 
  }
  
  private static Object jsObjectScopeCall(JSObject jsObj, Object thiz, Object[] args) {
    Object modifiedThiz;
    if (thiz == ScriptRuntime.UNDEFINED && !jsObj.isStrictFunction()) {
      Global global = Context.getGlobal();
      modifiedThiz = ScriptObjectMirror.wrap(global, global);
    } else {
      modifiedThiz = thiz;
    } 
    return jsObj.call(modifiedThiz, args);
  }
  
  private static final MethodHandleFunctionality MH = MethodHandleFactory.getFunctionality();
  
  private static final MethodHandle IS_JSOBJECT_GUARD = findOwnMH_S("isJSObject", boolean.class, new Class[] { Object.class });
  
  private static final MethodHandle JSOBJECTLINKER_GET = findOwnMH_S("get", Object.class, new Class[] { MethodHandle.class, Object.class, Object.class });
  
  private static final MethodHandle JSOBJECTLINKER_PUT = findOwnMH_S("put", void.class, new Class[] { Object.class, Object.class, Object.class });
  
  private static final MethodHandle JSOBJECTLINKER_DEL = findOwnMH_S("del", boolean.class, new Class[] { Object.class, Object.class });
  
  private static final MethodHandle JSOBJECT_GETMEMBER = findJSObjectMH_V("getMember", Object.class, new Class[] { String.class });
  
  private static final MethodHandle JSOBJECT_SETMEMBER = findJSObjectMH_V("setMember", void.class, new Class[] { String.class, Object.class });
  
  private static final MethodHandle JSOBJECT_CALL = findJSObjectMH_V("call", Object.class, new Class[] { Object.class, Object[].class });
  
  private static final MethodHandle JSOBJECT_SCOPE_CALL = findOwnMH_S("jsObjectScopeCall", Object.class, new Class[] { JSObject.class, Object.class, Object[].class });
  
  private static final MethodHandle JSOBJECT_CALL_TO_APPLY = findOwnMH_S("callToApply", Object.class, new Class[] { MethodHandle.class, JSObject.class, Object.class, Object[].class });
  
  private static final MethodHandle JSOBJECT_NEW = findJSObjectMH_V("newObject", Object.class, new Class[] { Object[].class });
  
  private static MethodHandle findJSObjectMH_V(String name, Class<?> rtype, Class<?>... types) {
    return MH.findVirtual(MethodHandles.lookup(), JSObject.class, name, MH.type(rtype, types));
  }
  
  private static MethodHandle findOwnMH_S(String name, Class<?> rtype, Class<?>... types) {
    return MH.findStatic(MethodHandles.lookup(), JSObjectLinker.class, name, MH.type(rtype, types));
  }
}
