package org.openjdk.nashorn.internal.runtime.linker;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.SwitchPoint;
import jdk.dynalink.CallSiteDescriptor;
import jdk.dynalink.StandardOperation;
import jdk.dynalink.linker.GuardedInvocation;
import jdk.dynalink.linker.LinkRequest;
import jdk.dynalink.linker.support.Guards;
import org.openjdk.nashorn.internal.lookup.Lookup;
import org.openjdk.nashorn.internal.runtime.ECMAErrors;
import org.openjdk.nashorn.internal.runtime.FindProperty;
import org.openjdk.nashorn.internal.runtime.GlobalConstants;
import org.openjdk.nashorn.internal.runtime.JSType;
import org.openjdk.nashorn.internal.runtime.ScriptObject;
import org.openjdk.nashorn.internal.runtime.ScriptRuntime;

public final class PrimitiveLookup {
  private static final MethodHandle PRIMITIVE_SETTER = findOwnMH("primitiveSetter", Lookup.MH
      .type(void.class, new Class[] { ScriptObject.class, Object.class, Object.class, boolean.class, Object.class }));
  
  public static GuardedInvocation lookupPrimitive(LinkRequest request, Class<?> receiverClass, ScriptObject wrappedReceiver, MethodHandle wrapFilter, MethodHandle protoFilter) {
    return lookupPrimitive(request, Guards.getInstanceOfGuard(receiverClass), wrappedReceiver, wrapFilter, protoFilter);
  }
  
  public static GuardedInvocation lookupPrimitive(LinkRequest request, MethodHandle guard, ScriptObject wrappedReceiver, MethodHandle wrapFilter, MethodHandle protoFilter) {
    CallSiteDescriptor desc = request.getCallSiteDescriptor();
    String name = NashornCallSiteDescriptor.getOperand(desc);
    FindProperty find = (name != null) ? wrappedReceiver.findProperty(name, true) : null;
    switch (NashornCallSiteDescriptor.getStandardOperation(desc)) {
      case GET:
        if (name != null) {
          if (find == null)
            return null; 
          SwitchPoint sp = find.getProperty().getBuiltinSwitchPoint();
          if (sp instanceof org.openjdk.nashorn.internal.runtime.Context.BuiltinSwitchPoint && !sp.hasBeenInvalidated())
            return new GuardedInvocation(GlobalConstants.staticConstantGetter(find.getObjectValue()), guard, sp, null); 
          if (find.isInheritedOrdinaryProperty()) {
            ScriptObject proto = wrappedReceiver.getProto();
            GuardedInvocation guardedInvocation = proto.lookup(desc, request);
            if (guardedInvocation != null) {
              MethodHandle invocation = guardedInvocation.getInvocation();
              MethodHandle adaptedInvocation = Lookup.MH.asType(invocation, invocation.type().changeParameterType(0, Object.class));
              MethodHandle method = Lookup.MH.filterArguments(adaptedInvocation, 0, new MethodHandle[] { protoFilter });
              MethodHandle protoGuard = Lookup.MH.filterArguments(guardedInvocation.getGuard(), 0, new MethodHandle[] { protoFilter });
              return new GuardedInvocation(method, NashornGuards.combineGuards(guard, protoGuard));
            } 
          } 
        } 
        break;
      case SET:
        return getPrimitiveSetter(name, guard, wrapFilter, NashornCallSiteDescriptor.isStrict(desc));
    } 
    GuardedInvocation link = wrappedReceiver.lookup(desc, request);
    if (link != null) {
      MethodHandle method = link.getInvocation();
      Class<?> receiverType = method.type().parameterType(0);
      if (receiverType != Object.class) {
        MethodType wrapType = wrapFilter.type();
        assert receiverType.isAssignableFrom(wrapType.returnType());
        method = Lookup.MH.filterArguments(method, 0, new MethodHandle[] { Lookup.MH.asType(wrapFilter, wrapType.changeReturnType(receiverType)) });
      } 
      return new GuardedInvocation(method, guard, link.getSwitchPoints(), null);
    } 
    return null;
  }
  
  private static GuardedInvocation getPrimitiveSetter(String name, MethodHandle guard, MethodHandle wrapFilter, boolean isStrict) {
    MethodHandle target, filter = Lookup.MH.asType(wrapFilter, wrapFilter.type().changeReturnType(ScriptObject.class));
    if (name == null) {
      filter = Lookup.MH.dropArguments(filter, 1, new Class[] { Object.class, Object.class });
      target = Lookup.MH.insertArguments(PRIMITIVE_SETTER, 3, new Object[] { Boolean.valueOf(isStrict) });
    } else {
      filter = Lookup.MH.dropArguments(filter, 1, new Class[] { Object.class });
      target = Lookup.MH.insertArguments(PRIMITIVE_SETTER, 2, new Object[] { name, Boolean.valueOf(isStrict) });
    } 
    return new GuardedInvocation(Lookup.MH.foldArguments(target, filter), guard);
  }
  
  private static void primitiveSetter(ScriptObject wrappedSelf, Object self, Object key, boolean strict, Object value) {
    String name = JSType.toString(key);
    FindProperty find = wrappedSelf.findProperty(name, true);
    if (find == null || !find.getProperty().isAccessorProperty() || !find.getProperty().hasNativeSetter()) {
      if (strict) {
        if (find == null || !find.getProperty().isAccessorProperty())
          throw ECMAErrors.typeError("property.not.writable", new String[] { name, ScriptRuntime.safeToString(self) }); 
        throw ECMAErrors.typeError("property.has.no.setter", new String[] { name, ScriptRuntime.safeToString(self) });
      } 
      return;
    } 
    find.setValue(value, strict);
  }
  
  private static MethodHandle findOwnMH(String name, MethodType type) {
    return Lookup.MH.findStatic(MethodHandles.lookup(), PrimitiveLookup.class, name, type);
  }
}
