package org.openjdk.nashorn.internal.runtime.linker;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import jdk.dynalink.CallSiteDescriptor;
import jdk.dynalink.NamedOperation;
import jdk.dynalink.Operation;
import jdk.dynalink.StandardOperation;
import jdk.dynalink.beans.BeansLinker;
import jdk.dynalink.beans.StaticClass;
import jdk.dynalink.linker.GuardedInvocation;
import jdk.dynalink.linker.GuardingDynamicLinker;
import jdk.dynalink.linker.GuardingTypeConverterFactory;
import jdk.dynalink.linker.LinkRequest;
import jdk.dynalink.linker.LinkerServices;
import jdk.dynalink.linker.support.Lookup;
import org.openjdk.nashorn.internal.codegen.types.Type;
import org.openjdk.nashorn.internal.lookup.Lookup;
import org.openjdk.nashorn.internal.runtime.ECMAErrors;
import org.openjdk.nashorn.internal.runtime.ECMAException;
import org.openjdk.nashorn.internal.runtime.JSType;
import org.openjdk.nashorn.internal.runtime.ScriptRuntime;
import org.openjdk.nashorn.internal.runtime.UnwarrantedOptimismException;

final class NashornBottomLinker implements GuardingDynamicLinker, GuardingTypeConverterFactory {
  public GuardedInvocation getGuardedInvocation(LinkRequest linkRequest, LinkerServices linkerServices) {
    Object self = linkRequest.getReceiver();
    if (self == null)
      return linkNull(linkRequest); 
    assert isExpectedObject(self) : "Couldn't link " + linkRequest.getCallSiteDescriptor() + " for " + self.getClass().getName();
    return linkBean(linkRequest, linkerServices);
  }
  
  private static final MethodHandle EMPTY_PROP_GETTER = Lookup.MH
    .dropArguments(Lookup.MH.constant(Object.class, ScriptRuntime.UNDEFINED), 0, new Class[] { Object.class });
  
  private static final MethodHandle EMPTY_ELEM_GETTER = Lookup.MH
    .dropArguments(EMPTY_PROP_GETTER, 0, new Class[] { Object.class });
  
  private static final MethodHandle EMPTY_PROP_SETTER = Lookup.MH
    .asType(EMPTY_ELEM_GETTER, EMPTY_ELEM_GETTER.type().changeReturnType(void.class));
  
  private static final MethodHandle EMPTY_ELEM_SETTER = Lookup.MH
    .dropArguments(EMPTY_PROP_SETTER, 0, new Class[] { Object.class });
  
  private static final MethodHandle THROW_STRICT_PROPERTY_SETTER;
  
  private static final MethodHandle THROW_STRICT_PROPERTY_REMOVER;
  
  private static final MethodHandle THROW_OPTIMISTIC_UNDEFINED;
  
  private static final MethodHandle MISSING_PROPERTY_REMOVER;
  
  private static final MethodHandle IS_DYNAMIC_METHOD;
  
  static {
    Lookup lookup = new Lookup(MethodHandles.lookup());
    THROW_STRICT_PROPERTY_SETTER = lookup.findOwnStatic("throwStrictPropertySetter", void.class, new Class[] { Object.class, Object.class });
    THROW_STRICT_PROPERTY_REMOVER = lookup.findOwnStatic("throwStrictPropertyRemover", boolean.class, new Class[] { Object.class, Object.class });
    THROW_OPTIMISTIC_UNDEFINED = lookup.findOwnStatic("throwOptimisticUndefined", Object.class, new Class[] { int.class });
    MISSING_PROPERTY_REMOVER = lookup.findOwnStatic("missingPropertyRemover", boolean.class, new Class[] { Object.class, Object.class });
    IS_DYNAMIC_METHOD = lookup.findStatic(BeansLinker.class, "isDynamicMethod", MethodType.methodType(boolean.class, Object.class));
  }
  
  private static GuardedInvocation linkBean(LinkRequest linkRequest, LinkerServices linkerServices) {
    CallSiteDescriptor desc = linkRequest.getCallSiteDescriptor();
    Object self = linkRequest.getReceiver();
    switch (NashornCallSiteDescriptor.getStandardOperation(desc)) {
      case NEW:
        if (BeansLinker.isDynamicConstructor(self))
          throw ECMAErrors.typeError("no.constructor.matches.args", new String[] { ScriptRuntime.safeToString(self) }); 
        if (BeansLinker.isDynamicMethod(self))
          throw ECMAErrors.typeError("method.not.constructor", new String[] { ScriptRuntime.safeToString(self) }); 
        throw ECMAErrors.typeError("not.a.function", new String[] { NashornCallSiteDescriptor.getFunctionErrorMessage(desc, self) });
      case CALL:
        if (BeansLinker.isDynamicConstructor(self))
          throw ECMAErrors.typeError("constructor.requires.new", new String[] { ScriptRuntime.safeToString(self) }); 
        if (BeansLinker.isDynamicMethod(self))
          throw ECMAErrors.typeError("no.method.matches.args", new String[] { ScriptRuntime.safeToString(self) }); 
        throw ECMAErrors.typeError("not.a.function", new String[] { NashornCallSiteDescriptor.getFunctionErrorMessage(desc, self) });
    } 
    if (BeansLinker.isDynamicMethod(self))
      return new GuardedInvocation(
          linkMissingBeanMember(linkRequest, linkerServices), IS_DYNAMIC_METHOD); 
    throw new AssertionError("unknown call type " + desc);
  }
  
  static MethodHandle linkMissingBeanMember(LinkRequest linkRequest, LinkerServices linkerServices) {
    CallSiteDescriptor desc = linkRequest.getCallSiteDescriptor();
    String operand = NashornCallSiteDescriptor.getOperand(desc);
    boolean strict = NashornCallSiteDescriptor.isStrict(desc);
    switch (NashornCallSiteDescriptor.getStandardOperation(desc)) {
      case GET:
        if (NashornCallSiteDescriptor.isOptimistic(desc))
          return adaptThrower(MethodHandles.insertArguments(THROW_OPTIMISTIC_UNDEFINED, 0, new Object[] { Integer.valueOf(NashornCallSiteDescriptor.getProgramPoint(desc)) }), desc); 
        if (operand != null)
          return getInvocation(EMPTY_PROP_GETTER, linkerServices, desc); 
        return getInvocation(EMPTY_ELEM_GETTER, linkerServices, desc);
      case SET:
        if (strict)
          return adaptThrower(bindOperand(THROW_STRICT_PROPERTY_SETTER, operand), desc); 
        if (operand != null)
          return getInvocation(EMPTY_PROP_SETTER, linkerServices, desc); 
        return getInvocation(EMPTY_ELEM_SETTER, linkerServices, desc);
      case REMOVE:
        if (strict)
          return adaptThrower(bindOperand(THROW_STRICT_PROPERTY_REMOVER, operand), desc); 
        return getInvocation(bindOperand(MISSING_PROPERTY_REMOVER, operand), linkerServices, desc);
    } 
    throw new AssertionError("unknown call type " + desc);
  }
  
  private static MethodHandle bindOperand(MethodHandle handle, String operand) {
    return (operand == null) ? handle : MethodHandles.insertArguments(handle, 1, new Object[] { operand });
  }
  
  private static MethodHandle adaptThrower(MethodHandle handle, CallSiteDescriptor desc) {
    MethodType targetType = desc.getMethodType();
    int paramCount = handle.type().parameterCount();
    return 
      MethodHandles.dropArguments(handle, paramCount, targetType.parameterList().subList(paramCount, targetType.parameterCount()))
      .asType(targetType);
  }
  
  private static void throwStrictPropertySetter(Object self, Object name) {
    throw createTypeError(self, name, "cant.set.property");
  }
  
  private static boolean throwStrictPropertyRemover(Object self, Object name) {
    if (isNonConfigurableProperty(self, name))
      throw createTypeError(self, name, "cant.delete.property"); 
    return true;
  }
  
  private static boolean missingPropertyRemover(Object self, Object name) {
    return !isNonConfigurableProperty(self, name);
  }
  
  private static boolean isNonConfigurableProperty(Object self, Object name) {
    if (self instanceof StaticClass) {
      Class<?> clazz1 = ((StaticClass)self).getRepresentedClass();
      return (BeansLinker.getReadableStaticPropertyNames(clazz1).contains(name) || 
        BeansLinker.getWritableStaticPropertyNames(clazz1).contains(name) || 
        BeansLinker.getStaticMethodNames(clazz1).contains(name));
    } 
    Class<?> clazz = self.getClass();
    return (BeansLinker.getReadableInstancePropertyNames(clazz).contains(name) || 
      BeansLinker.getWritableInstancePropertyNames(clazz).contains(name) || 
      BeansLinker.getInstanceMethodNames(clazz).contains(name));
  }
  
  private static ECMAException createTypeError(Object self, Object name, String msg) {
    return ECMAErrors.typeError(msg, new String[] { String.valueOf(name), ScriptRuntime.safeToString(self) });
  }
  
  private static Object throwOptimisticUndefined(int programPoint) {
    throw new UnwarrantedOptimismException(ScriptRuntime.UNDEFINED, programPoint, Type.OBJECT);
  }
  
  public GuardedInvocation convertToType(Class<?> sourceType, Class<?> targetType, Supplier<MethodHandles.Lookup> lookupSupplier) {
    GuardedInvocation gi = convertToTypeNoCast(targetType);
    return (gi == null) ? null : gi.asType(Lookup.MH.type(targetType, new Class[] { sourceType }));
  }
  
  private static GuardedInvocation convertToTypeNoCast(Class<?> targetType) {
    MethodHandle mh = CONVERTERS.get(targetType);
    if (mh != null)
      return new GuardedInvocation(mh); 
    return null;
  }
  
  private static MethodHandle getInvocation(MethodHandle handle, LinkerServices linkerServices, CallSiteDescriptor desc) {
    return linkerServices.asTypeLosslessReturn(handle, desc.getMethodType());
  }
  
  private static boolean isExpectedObject(Object obj) {
    return !NashornLinker.canLinkTypeStatic(obj.getClass());
  }
  
  private static GuardedInvocation linkNull(LinkRequest linkRequest) {
    CallSiteDescriptor desc = linkRequest.getCallSiteDescriptor();
    switch (NashornCallSiteDescriptor.getStandardOperation(desc)) {
      case NEW:
      case CALL:
        throw ECMAErrors.typeError("not.a.function", new String[] { "null" });
      case GET:
        throw ECMAErrors.typeError(NashornCallSiteDescriptor.isMethodFirstOperation(desc) ? "no.such.function" : "cant.get.property", new String[] { getArgument(linkRequest), "null" });
      case SET:
        throw ECMAErrors.typeError("cant.set.property", new String[] { getArgument(linkRequest), "null" });
      case REMOVE:
        throw ECMAErrors.typeError("cant.delete.property", new String[] { getArgument(linkRequest), "null" });
    } 
    throw new AssertionError("unknown call type " + desc);
  }
  
  private static final Map<Class<?>, MethodHandle> CONVERTERS = new HashMap<>();
  
  static {
    CONVERTERS.put(boolean.class, JSType.TO_BOOLEAN.methodHandle());
    CONVERTERS.put(double.class, JSType.TO_NUMBER.methodHandle());
    CONVERTERS.put(int.class, JSType.TO_INTEGER.methodHandle());
    CONVERTERS.put(long.class, JSType.TO_LONG.methodHandle());
    CONVERTERS.put(String.class, JSType.TO_STRING.methodHandle());
  }
  
  private static String getArgument(LinkRequest linkRequest) {
    Operation op = linkRequest.getCallSiteDescriptor().getOperation();
    if (op instanceof NamedOperation)
      return ((NamedOperation)op).getName().toString(); 
    return ScriptRuntime.safeToString(linkRequest.getArguments()[1]);
  }
}
