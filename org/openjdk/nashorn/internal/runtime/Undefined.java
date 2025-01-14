package org.openjdk.nashorn.internal.runtime;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import jdk.dynalink.CallSiteDescriptor;
import jdk.dynalink.StandardOperation;
import jdk.dynalink.linker.GuardedInvocation;
import jdk.dynalink.linker.support.Guards;
import org.openjdk.nashorn.internal.lookup.Lookup;
import org.openjdk.nashorn.internal.runtime.linker.NashornCallSiteDescriptor;

public final class Undefined extends DefaultPropertyAccess {
  private static final Undefined UNDEFINED = new Undefined();
  
  private static final Undefined EMPTY = new Undefined();
  
  private static final MethodHandle UNDEFINED_GUARD = Guards.getIdentityGuard(UNDEFINED);
  
  public static Undefined getUndefined() {
    return UNDEFINED;
  }
  
  public static Undefined getEmpty() {
    return EMPTY;
  }
  
  public String getClassName() {
    return "Undefined";
  }
  
  public String toString() {
    return "undefined";
  }
  
  public static GuardedInvocation lookup(CallSiteDescriptor desc) {
    String name;
    String msg;
    switch (NashornCallSiteDescriptor.getStandardOperation(desc)) {
      case CALL:
      case NEW:
        name = NashornCallSiteDescriptor.getOperand(desc);
        msg = (name != null) ? "not.a.function" : "cant.call.undefined";
        throw ECMAErrors.typeError(msg, new String[] { name });
      case GET:
        if (!(desc.getOperation() instanceof jdk.dynalink.NamedOperation))
          return findGetIndexMethod(desc); 
        return findGetMethod(desc);
      case SET:
        if (!(desc.getOperation() instanceof jdk.dynalink.NamedOperation))
          return findSetIndexMethod(desc); 
        return findSetMethod(desc);
      case REMOVE:
        if (!(desc.getOperation() instanceof jdk.dynalink.NamedOperation))
          return findDeleteIndexMethod(desc); 
        return findDeleteMethod(desc);
    } 
    return null;
  }
  
  private static ECMAException lookupTypeError(String msg, CallSiteDescriptor desc) {
    String name = NashornCallSiteDescriptor.getOperand(desc);
    return ECMAErrors.typeError(msg, new String[] { (name != null && !name.isEmpty()) ? name : null });
  }
  
  private static final MethodHandle GET_METHOD = findOwnMH("get", Object.class, new Class[] { Object.class });
  
  private static final MethodHandle SET_METHOD = Lookup.MH.insertArguments(findOwnMH("set", void.class, new Class[] { Object.class, Object.class, int.class }), 3, new Object[] { Integer.valueOf(32) });
  
  private static final MethodHandle DELETE_METHOD = Lookup.MH.insertArguments(findOwnMH("delete", boolean.class, new Class[] { Object.class, boolean.class }), 2, new Object[] { Boolean.valueOf(false) });
  
  private static GuardedInvocation findGetMethod(CallSiteDescriptor desc) {
    return (new GuardedInvocation(Lookup.MH.insertArguments(GET_METHOD, 1, new Object[] { NashornCallSiteDescriptor.getOperand(desc) }), UNDEFINED_GUARD)).asType(desc);
  }
  
  private static GuardedInvocation findGetIndexMethod(CallSiteDescriptor desc) {
    return (new GuardedInvocation(GET_METHOD, UNDEFINED_GUARD)).asType(desc);
  }
  
  private static GuardedInvocation findSetMethod(CallSiteDescriptor desc) {
    return (new GuardedInvocation(Lookup.MH.insertArguments(SET_METHOD, 1, new Object[] { NashornCallSiteDescriptor.getOperand(desc) }), UNDEFINED_GUARD)).asType(desc);
  }
  
  private static GuardedInvocation findSetIndexMethod(CallSiteDescriptor desc) {
    return (new GuardedInvocation(SET_METHOD, UNDEFINED_GUARD)).asType(desc);
  }
  
  private static GuardedInvocation findDeleteMethod(CallSiteDescriptor desc) {
    return (new GuardedInvocation(Lookup.MH.insertArguments(DELETE_METHOD, 1, new Object[] { NashornCallSiteDescriptor.getOperand(desc) }), UNDEFINED_GUARD)).asType(desc);
  }
  
  private static GuardedInvocation findDeleteIndexMethod(CallSiteDescriptor desc) {
    return (new GuardedInvocation(DELETE_METHOD, UNDEFINED_GUARD)).asType(desc);
  }
  
  public Object get(Object key) {
    throw ECMAErrors.typeError("cant.read.property.of.undefined", new String[] { ScriptRuntime.safeToString(key) });
  }
  
  public void set(Object key, Object value, int flags) {
    throw ECMAErrors.typeError("cant.set.property.of.undefined", new String[] { ScriptRuntime.safeToString(key) });
  }
  
  public boolean delete(Object key, boolean strict) {
    throw ECMAErrors.typeError("cant.delete.property.of.undefined", new String[] { ScriptRuntime.safeToString(key) });
  }
  
  public boolean has(Object key) {
    return false;
  }
  
  public boolean hasOwnProperty(Object key) {
    return false;
  }
  
  private static MethodHandle findOwnMH(String name, Class<?> rtype, Class<?>... types) {
    return Lookup.MH.findVirtual(MethodHandles.lookup(), Undefined.class, name, Lookup.MH.type(rtype, types));
  }
}
