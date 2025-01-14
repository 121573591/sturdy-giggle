package org.openjdk.nashorn.internal.runtime.linker;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import jdk.dynalink.CallSiteDescriptor;
import jdk.dynalink.Operation;
import jdk.dynalink.StandardNamespace;
import jdk.dynalink.StandardOperation;
import jdk.dynalink.beans.BeansLinker;
import jdk.dynalink.linker.GuardedInvocation;
import jdk.dynalink.linker.LinkRequest;
import jdk.dynalink.linker.LinkerServices;
import jdk.dynalink.linker.TypeBasedGuardingDynamicLinker;
import jdk.dynalink.linker.support.Lookup;
import org.openjdk.nashorn.internal.runtime.ScriptRuntime;

final class JavaSuperAdapterLinker implements TypeBasedGuardingDynamicLinker {
  private static final MethodHandle ADD_PREFIX_TO_METHOD_NAME;
  
  private static final MethodHandle BIND_DYNAMIC_METHOD;
  
  private static final MethodHandle GET_ADAPTER;
  
  private static final MethodHandle IS_ADAPTER_OF_CLASS;
  
  static {
    Lookup lookup = new Lookup(MethodHandles.lookup());
    ADD_PREFIX_TO_METHOD_NAME = lookup.findOwnStatic("addPrefixToMethodName", Object.class, new Class[] { Object.class });
    BIND_DYNAMIC_METHOD = lookup.findOwnStatic("bindDynamicMethod", Object.class, new Class[] { Object.class, Object.class });
    GET_ADAPTER = lookup.findVirtual(JavaSuperAdapter.class, "getAdapter", MethodType.methodType(Object.class));
    IS_ADAPTER_OF_CLASS = lookup.findOwnStatic("isAdapterOfClass", boolean.class, new Class[] { Class.class, Object.class });
  }
  
  private static final Operation GET_METHOD = StandardOperation.GET.withNamespace(StandardNamespace.METHOD);
  
  private final BeansLinker beansLinker;
  
  JavaSuperAdapterLinker(BeansLinker beansLinker) {
    this.beansLinker = beansLinker;
  }
  
  public boolean canLinkType(Class<?> type) {
    return (type == JavaSuperAdapter.class);
  }
  
  public GuardedInvocation getGuardedInvocation(LinkRequest linkRequest, LinkerServices linkerServices) throws Exception {
    MethodHandle adaptedInvocation;
    Object objSuperAdapter = linkRequest.getReceiver();
    if (!(objSuperAdapter instanceof JavaSuperAdapter))
      return null; 
    CallSiteDescriptor descriptor = linkRequest.getCallSiteDescriptor();
    if (!NashornCallSiteDescriptor.contains(descriptor, StandardOperation.GET, StandardNamespace.METHOD))
      return null; 
    Object adapter = ((JavaSuperAdapter)objSuperAdapter).getAdapter();
    Object[] args = linkRequest.getArguments();
    args[0] = adapter;
    MethodType type = descriptor.getMethodType();
    Class<?> adapterClass = adapter.getClass();
    String name = NashornCallSiteDescriptor.getOperand(descriptor);
    Operation newOp = (name == null) ? GET_METHOD : GET_METHOD.named("super$" + name);
    CallSiteDescriptor newDescriptor = new CallSiteDescriptor(NashornCallSiteDescriptor.getLookupInternal(descriptor), newOp, type.changeParameterType(0, adapterClass));
    GuardedInvocation guardedInv = NashornBeansLinker.getGuardedInvocation(this.beansLinker, linkRequest
        .replaceArguments(newDescriptor, args), linkerServices);
    assert guardedInv != null;
    MethodHandle guard = IS_ADAPTER_OF_CLASS.bindTo(adapterClass);
    MethodHandle invocation = guardedInv.getInvocation();
    MethodType invType = invocation.type();
    MethodHandle typedBinder = BIND_DYNAMIC_METHOD.asType(MethodType.methodType(Object.class, invType
          .returnType(), new Class[] { invType.parameterType(0) }));
    MethodHandle droppingBinder = MethodHandles.dropArguments(typedBinder, 2, invType
        .parameterList().subList(1, invType.parameterCount()));
    MethodHandle bindingInvocation = MethodHandles.foldArguments(droppingBinder, invocation);
    MethodHandle typedGetAdapter = asFilterType(GET_ADAPTER, 0, invType, type);
    if (name != null) {
      adaptedInvocation = MethodHandles.filterArguments(bindingInvocation, 0, new MethodHandle[] { typedGetAdapter });
    } else {
      MethodHandle typedAddPrefix = asFilterType(ADD_PREFIX_TO_METHOD_NAME, 1, invType, type);
      adaptedInvocation = MethodHandles.filterArguments(bindingInvocation, 0, new MethodHandle[] { typedGetAdapter, typedAddPrefix });
    } 
    return guardedInv.replaceMethods(adaptedInvocation, guard).asType(descriptor);
  }
  
  private static MethodHandle asFilterType(MethodHandle filter, int pos, MethodType targetType, MethodType sourceType) {
    return filter.asType(MethodType.methodType(targetType.parameterType(pos), sourceType.parameterType(pos)));
  }
  
  private static Object addPrefixToMethodName(Object name) {
    return "super$".concat(String.valueOf(name));
  }
  
  private static Object bindDynamicMethod(Object dynamicMethod, Object boundThis) {
    return (dynamicMethod == ScriptRuntime.UNDEFINED) ? ScriptRuntime.UNDEFINED : Bootstrap.bindCallable(dynamicMethod, boundThis, null);
  }
  
  private static boolean isAdapterOfClass(Class<?> clazz, Object obj) {
    return (obj instanceof JavaSuperAdapter && clazz == ((JavaSuperAdapter)obj).getAdapter().getClass());
  }
}
