package org.openjdk.nashorn.internal.runtime.linker;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.function.Supplier;
import jdk.dynalink.CallSiteDescriptor;
import jdk.dynalink.NamedOperation;
import jdk.dynalink.Operation;
import jdk.dynalink.SecureLookupSupplier;
import jdk.dynalink.StandardNamespace;
import jdk.dynalink.StandardOperation;
import jdk.dynalink.beans.BeansLinker;
import jdk.dynalink.linker.ConversionComparator;
import jdk.dynalink.linker.GuardedInvocation;
import jdk.dynalink.linker.GuardingDynamicLinker;
import jdk.dynalink.linker.LinkRequest;
import jdk.dynalink.linker.LinkerServices;
import jdk.dynalink.linker.MethodHandleTransformer;
import jdk.dynalink.linker.support.DefaultInternalObjectFilter;
import jdk.dynalink.linker.support.Lookup;
import jdk.dynalink.linker.support.SimpleLinkRequest;
import org.openjdk.nashorn.api.scripting.ScriptUtils;
import org.openjdk.nashorn.internal.lookup.Lookup;
import org.openjdk.nashorn.internal.runtime.ConsString;
import org.openjdk.nashorn.internal.runtime.Context;
import org.openjdk.nashorn.internal.runtime.options.Options;

public class NashornBeansLinker implements GuardingDynamicLinker {
  private static final boolean MIRROR_ALWAYS = Options.getBooleanProperty("nashorn.mirror.always", Boolean.valueOf(true));
  
  private static final Operation GET_METHOD = StandardOperation.GET.withNamespace(StandardNamespace.METHOD);
  
  private static final MethodType GET_METHOD_TYPE = MethodType.methodType(Object.class, Object.class);
  
  private static final MethodHandle EXPORT_ARGUMENT;
  
  private static final MethodHandle IMPORT_RESULT;
  
  private static final MethodHandle FILTER_CONSSTRING;
  
  static {
    Lookup lookup = new Lookup(MethodHandles.lookup());
    EXPORT_ARGUMENT = lookup.findOwnStatic("exportArgument", Object.class, new Class[] { Object.class });
    IMPORT_RESULT = lookup.findOwnStatic("importResult", Object.class, new Class[] { Object.class });
    FILTER_CONSSTRING = lookup.findOwnStatic("consStringFilter", Object.class, new Class[] { Object.class });
  }
  
  private static final ClassValue<String> FUNCTIONAL_IFACE_METHOD_NAME = new ClassValue<String>() {
      protected String computeValue(Class<?> type) {
        return NashornBeansLinker.findFunctionalInterfaceMethodName(type);
      }
    };
  
  private final BeansLinker beansLinker;
  
  NashornBeansLinker(BeansLinker beansLinker) {
    this.beansLinker = beansLinker;
  }
  
  public GuardedInvocation getGuardedInvocation(LinkRequest linkRequest, LinkerServices linkerServices) throws Exception {
    Object self = linkRequest.getReceiver();
    CallSiteDescriptor desc = linkRequest.getCallSiteDescriptor();
    if (self instanceof ConsString) {
      Object[] arguments = linkRequest.getArguments();
      arguments[0] = "";
      LinkRequest forgedLinkRequest = linkRequest.replaceArguments(desc, arguments);
      GuardedInvocation invocation = getGuardedInvocation(this.beansLinker, forgedLinkRequest, linkerServices);
      return (invocation == null) ? null : invocation.filterArguments(0, new MethodHandle[] { FILTER_CONSSTRING });
    } 
    if (self != null && NamedOperation.getBaseOperation(desc.getOperation()) == StandardOperation.CALL) {
      String name = getFunctionalInterfaceMethodName(self.getClass());
      if (name != null) {
        Object method;
        CallSiteDescriptor getMethodDesc = new CallSiteDescriptor(NashornCallSiteDescriptor.getLookupInternal(desc), GET_METHOD.named(name), GET_METHOD_TYPE);
        GuardedInvocation getMethodInv = linkerServices.getGuardedInvocation(new SimpleLinkRequest(getMethodDesc, false, new Object[] { self }));
        try {
          method = getMethodInv.getInvocation().invokeExact(self);
        } catch (Exception|Error e) {
          throw e;
        } catch (Throwable t) {
          throw new RuntimeException(t);
        } 
        Object[] args = linkRequest.getArguments();
        args[1] = args[0];
        args[0] = method;
        MethodType callType = desc.getMethodType();
        CallSiteDescriptor newDesc = desc.changeMethodType(desc
            .getMethodType().changeParameterType(0, Object.class).changeParameterType(1, callType.parameterType(0)));
        GuardedInvocation gi = getGuardedInvocation(this.beansLinker, linkRequest.replaceArguments(newDesc, args), new NashornBeansLinkerServices(linkerServices));
        MethodHandle inv = gi.getInvocation().bindTo(method);
        MethodHandle calleeToThis = Lookup.MH.dropArguments(inv, 1, new Class[] { callType.parameterType(1) });
        return gi.replaceMethods(calleeToThis, gi.getGuard());
      } 
    } 
    return getGuardedInvocation(this.beansLinker, linkRequest, linkerServices);
  }
  
  public static GuardedInvocation getGuardedInvocation(GuardingDynamicLinker delegateLinker, LinkRequest linkRequest, LinkerServices linkerServices) throws Exception {
    return delegateLinker.getGuardedInvocation(linkRequest, new NashornBeansLinkerServices(linkerServices));
  }
  
  private static Object exportArgument(Object arg) {
    return exportArgument(arg, MIRROR_ALWAYS);
  }
  
  static Object exportArgument(Object arg, boolean mirrorAlways) {
    if (arg instanceof ConsString)
      return arg.toString(); 
    if (mirrorAlways && arg instanceof org.openjdk.nashorn.internal.runtime.ScriptObject)
      return ScriptUtils.wrap(arg); 
    return arg;
  }
  
  private static Object importResult(Object arg) {
    return ScriptUtils.unwrap(arg);
  }
  
  private static Object consStringFilter(Object arg) {
    return (arg instanceof ConsString) ? arg.toString() : arg;
  }
  
  private static String findFunctionalInterfaceMethodName(Class<?> clazz) {
    if (clazz == null)
      return null; 
    for (Class<?> iface : clazz.getInterfaces()) {
      if (Context.isAccessibleClass(iface))
        if (iface.isAnnotationPresent((Class)FunctionalInterface.class))
          for (Method m : iface.getMethods()) {
            if (Modifier.isAbstract(m.getModifiers()) && !isOverridableObjectMethod(m))
              return m.getName(); 
          }   
    } 
    return findFunctionalInterfaceMethodName(clazz.getSuperclass());
  }
  
  private static boolean isOverridableObjectMethod(Method m) {
    switch (m.getName()) {
      case "equals":
        if (m.getReturnType() == boolean.class) {
          Class<?>[] params = m.getParameterTypes();
          return (params.length == 1 && params[0] == Object.class);
        } 
        return false;
      case "hashCode":
        return (m.getReturnType() == int.class && m.getParameterCount() == 0);
      case "toString":
        return (m.getReturnType() == String.class && m.getParameterCount() == 0);
    } 
    return false;
  }
  
  static String getFunctionalInterfaceMethodName(Class<?> clazz) {
    return FUNCTIONAL_IFACE_METHOD_NAME.get(clazz);
  }
  
  static MethodHandleTransformer createHiddenObjectFilter() {
    return new DefaultInternalObjectFilter(EXPORT_ARGUMENT, MIRROR_ALWAYS ? IMPORT_RESULT : null);
  }
  
  private static class NashornBeansLinkerServices implements LinkerServices {
    private final LinkerServices linkerServices;
    
    NashornBeansLinkerServices(LinkerServices linkerServices) {
      this.linkerServices = linkerServices;
    }
    
    public MethodHandle asType(MethodHandle handle, MethodType fromType) {
      return this.linkerServices.asType(handle, fromType);
    }
    
    public MethodHandle getTypeConverter(Class<?> sourceType, Class<?> targetType) {
      return this.linkerServices.getTypeConverter(sourceType, targetType);
    }
    
    public boolean canConvert(Class<?> from, Class<?> to) {
      return this.linkerServices.canConvert(from, to);
    }
    
    public GuardedInvocation getGuardedInvocation(LinkRequest linkRequest) throws Exception {
      return this.linkerServices.getGuardedInvocation(linkRequest);
    }
    
    public ConversionComparator.Comparison compareConversion(Class<?> sourceType, Class<?> targetType1, Class<?> targetType2) {
      if (sourceType == ConsString.class) {
        if (String.class == targetType1 || CharSequence.class == targetType1)
          return ConversionComparator.Comparison.TYPE_1_BETTER; 
        if (String.class == targetType2 || CharSequence.class == targetType2)
          return ConversionComparator.Comparison.TYPE_2_BETTER; 
      } 
      return this.linkerServices.compareConversion(sourceType, targetType1, targetType2);
    }
    
    public MethodHandle filterInternalObjects(MethodHandle target) {
      return this.linkerServices.filterInternalObjects(target);
    }
    
    public <T> T getWithLookup(Supplier<T> operation, SecureLookupSupplier lookupSupplier) {
      return this.linkerServices.getWithLookup(operation, lookupSupplier);
    }
  }
}
