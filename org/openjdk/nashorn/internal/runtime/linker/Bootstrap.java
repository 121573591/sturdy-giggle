package org.openjdk.nashorn.internal.runtime.linker;

import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.Collections;
import java.util.List;
import jdk.dynalink.CallSiteDescriptor;
import jdk.dynalink.DynamicLinker;
import jdk.dynalink.DynamicLinkerFactory;
import jdk.dynalink.beans.BeansLinker;
import jdk.dynalink.linker.GuardedInvocation;
import jdk.dynalink.linker.GuardingDynamicLinker;
import jdk.dynalink.linker.LinkRequest;
import jdk.dynalink.linker.LinkerServices;
import jdk.dynalink.linker.TypeBasedGuardingDynamicLinker;
import jdk.dynalink.linker.support.TypeUtilities;
import org.openjdk.nashorn.api.scripting.JSObject;
import org.openjdk.nashorn.internal.codegen.CompilerConstants;
import org.openjdk.nashorn.internal.lookup.MethodHandleFactory;
import org.openjdk.nashorn.internal.lookup.MethodHandleFunctionality;
import org.openjdk.nashorn.internal.runtime.Context;
import org.openjdk.nashorn.internal.runtime.ECMAErrors;
import org.openjdk.nashorn.internal.runtime.ECMAException;
import org.openjdk.nashorn.internal.runtime.JSType;
import org.openjdk.nashorn.internal.runtime.OptimisticReturnFilters;
import org.openjdk.nashorn.internal.runtime.ScriptFunction;
import org.openjdk.nashorn.internal.runtime.ScriptRuntime;

public final class Bootstrap {
  public static final CompilerConstants.Call BOOTSTRAP = CompilerConstants.staticCallNoLookup(Bootstrap.class, "bootstrap", CallSite.class, new Class[] { MethodHandles.Lookup.class, String.class, MethodType.class, int.class });
  
  private static final MethodHandleFunctionality MH = MethodHandleFactory.getFunctionality();
  
  private static final MethodHandle VOID_TO_OBJECT = MH.constant(Object.class, ScriptRuntime.UNDEFINED);
  
  private static final BeansLinker beansLinker = new BeansLinker(Bootstrap::createMissingMemberHandler);
  
  private static final GuardingDynamicLinker[] prioritizedLinkers;
  
  private static final GuardingDynamicLinker[] fallbackLinkers;
  
  static {
    NashornBeansLinker nashornBeansLinker = new NashornBeansLinker(beansLinker);
    prioritizedLinkers = new GuardingDynamicLinker[] { new NashornLinker(), new NashornPrimitiveLinker(), new BoundCallableLinker(), new JavaSuperAdapterLinker(beansLinker), new JSObjectLinker(nashornBeansLinker), new BrowserJSObjectLinker(nashornBeansLinker), new ReflectionCheckLinker() };
    fallbackLinkers = new GuardingDynamicLinker[] { new NashornStaticClassLinker(beansLinker), nashornBeansLinker, new NashornBottomLinker() };
  }
  
  public static List<GuardingDynamicLinker> getExposedLinkers() {
    NashornBeansLinker nbl = new NashornBeansLinker(new BeansLinker());
    JSObjectLinker linker = new JSObjectLinker(nbl);
    return Collections.singletonList(linker);
  }
  
  public static DynamicLinker createDynamicLinker(ClassLoader appLoader, int unstableRelinkThreshold) {
    DynamicLinkerFactory factory = new DynamicLinkerFactory();
    factory.setPrioritizedLinkers(prioritizedLinkers);
    factory.setFallbackLinkers(fallbackLinkers);
    factory.setSyncOnRelink(true);
    factory.setPrelinkTransformer((inv, request, linkerServices) -> {
          CallSiteDescriptor desc = request.getCallSiteDescriptor();
          return OptimisticReturnFilters.filterOptimisticReturnValue(inv, desc).asType(linkerServices, desc.getMethodType());
        });
    factory.setAutoConversionStrategy(Bootstrap::unboxReturnType);
    factory.setInternalObjectsFilter(NashornBeansLinker.createHiddenObjectFilter());
    factory.setUnstableRelinkThreshold(unstableRelinkThreshold);
    factory.setClassLoader(appLoader);
    return factory.createLinker();
  }
  
  public static TypeBasedGuardingDynamicLinker getBeanLinkerForClass(Class<?> clazz) {
    return beansLinker.getLinkerForClass(clazz);
  }
  
  public static boolean isCallable(Object obj) {
    if (obj == ScriptRuntime.UNDEFINED || obj == null)
      return false; 
    return (obj instanceof ScriptFunction || 
      isJSObjectFunction(obj) || 
      BeansLinker.isDynamicMethod(obj) || obj instanceof BoundCallable || 
      
      isFunctionalInterfaceObject(obj) || obj instanceof jdk.dynalink.beans.StaticClass);
  }
  
  public static boolean isStrictCallable(Object callable) {
    if (callable instanceof ScriptFunction)
      return ((ScriptFunction)callable).isStrict(); 
    if (isJSObjectFunction(callable))
      return ((JSObject)callable).isStrictFunction(); 
    if (callable instanceof BoundCallable)
      return isStrictCallable(((BoundCallable)callable).getCallable()); 
    if (BeansLinker.isDynamicMethod(callable) || callable instanceof jdk.dynalink.beans.StaticClass || 
      
      isFunctionalInterfaceObject(callable))
      return false; 
    throw notFunction(callable);
  }
  
  private static ECMAException notFunction(Object obj) {
    return ECMAErrors.typeError("not.a.function", new String[] { ScriptRuntime.safeToString(obj) });
  }
  
  private static boolean isJSObjectFunction(Object obj) {
    return (obj instanceof JSObject && ((JSObject)obj).isFunction());
  }
  
  public static boolean isDynamicMethod(Object obj) {
    return BeansLinker.isDynamicMethod((obj instanceof BoundCallable) ? ((BoundCallable)obj).getCallable() : obj);
  }
  
  public static boolean isFunctionalInterfaceObject(Object obj) {
    return (!JSType.isPrimitive(obj) && NashornBeansLinker.getFunctionalInterfaceMethodName(obj.getClass()) != null);
  }
  
  public static CallSite bootstrap(MethodHandles.Lookup lookup, String opDesc, MethodType type, int flags) {
    return Context.getDynamicLinker(lookup.lookupClass()).<CallSite>link(LinkerCallSite.newLinkerCallSite(lookup, opDesc, type, flags));
  }
  
  public static MethodHandle createDynamicInvoker(String name, int flags, Class<?> rtype, Class<?>... ptypes) {
    return bootstrap(MethodHandles.publicLookup(), name, MethodType.methodType(rtype, ptypes), flags).dynamicInvoker();
  }
  
  public static MethodHandle createDynamicCallInvoker(Class<?> rtype, Class<?>... ptypes) {
    return createDynamicInvoker("", 8, rtype, ptypes);
  }
  
  public static MethodHandle createDynamicInvoker(String name, int flags, MethodType type) {
    return bootstrap(MethodHandles.publicLookup(), name, type, flags).dynamicInvoker();
  }
  
  public static Object bindCallable(Object callable, Object boundThis, Object[] boundArgs) {
    if (callable instanceof ScriptFunction)
      return ((ScriptFunction)callable).createBound(boundThis, boundArgs); 
    if (callable instanceof BoundCallable)
      return ((BoundCallable)callable).bind(boundArgs); 
    if (isCallable(callable))
      return new BoundCallable(callable, boundThis, boundArgs); 
    throw notFunction(callable);
  }
  
  public static Object createSuperAdapter(Object adapter) {
    return new JavaSuperAdapter(adapter);
  }
  
  public static void checkReflectionAccess(Class<?> clazz, boolean isStatic) {
    ReflectionCheckLinker.checkReflectionAccess(clazz, isStatic);
  }
  
  public static LinkerServices getLinkerServices() {
    return Context.getDynamicLinker().getLinkerServices();
  }
  
  static GuardedInvocation asTypeSafeReturn(GuardedInvocation inv, LinkerServices linkerServices, CallSiteDescriptor desc) {
    return (inv == null) ? null : inv.asTypeSafeReturn(linkerServices, desc.getMethodType());
  }
  
  private static MethodHandle unboxReturnType(MethodHandle target, MethodType newType) {
    MethodType targetType = target.type();
    Class<?> oldReturnType = targetType.returnType();
    Class<?> newReturnType = newType.returnType();
    if (TypeUtilities.isWrapperType(oldReturnType)) {
      if (newReturnType.isPrimitive()) {
        assert TypeUtilities.isMethodInvocationConvertible(oldReturnType, newReturnType);
        return MethodHandles.explicitCastArguments(target, targetType.changeReturnType(newReturnType));
      } 
    } else if (oldReturnType == void.class && newReturnType == Object.class) {
      return MethodHandles.filterReturnValue(target, VOID_TO_OBJECT);
    } 
    return target;
  }
  
  private static MethodHandle createMissingMemberHandler(LinkRequest linkRequest, LinkerServices linkerServices) {
    if (BrowserJSObjectLinker.canLinkTypeStatic(linkRequest.getReceiver().getClass()))
      return null; 
    return NashornBottomLinker.linkMissingBeanMember(linkRequest, linkerServices);
  }
}
