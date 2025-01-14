package org.openjdk.nashorn.internal.lookup;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.SwitchPoint;
import java.lang.reflect.Method;
import java.util.List;

public interface MethodHandleFunctionality {
  MethodHandle filterArguments(MethodHandle paramMethodHandle, int paramInt, MethodHandle... paramVarArgs);
  
  MethodHandle filterReturnValue(MethodHandle paramMethodHandle1, MethodHandle paramMethodHandle2);
  
  MethodHandle guardWithTest(MethodHandle paramMethodHandle1, MethodHandle paramMethodHandle2, MethodHandle paramMethodHandle3);
  
  MethodHandle insertArguments(MethodHandle paramMethodHandle, int paramInt, Object... paramVarArgs);
  
  MethodHandle dropArguments(MethodHandle paramMethodHandle, int paramInt, Class<?>... paramVarArgs);
  
  MethodHandle dropArguments(MethodHandle paramMethodHandle, int paramInt, List<Class<?>> paramList);
  
  MethodHandle foldArguments(MethodHandle paramMethodHandle1, MethodHandle paramMethodHandle2);
  
  MethodHandle explicitCastArguments(MethodHandle paramMethodHandle, MethodType paramMethodType);
  
  MethodHandle arrayElementGetter(Class<?> paramClass);
  
  MethodHandle arrayElementSetter(Class<?> paramClass);
  
  MethodHandle throwException(Class<?> paramClass, Class<? extends Throwable> paramClass1);
  
  MethodHandle catchException(MethodHandle paramMethodHandle1, Class<? extends Throwable> paramClass, MethodHandle paramMethodHandle2);
  
  MethodHandle constant(Class<?> paramClass, Object paramObject);
  
  MethodHandle identity(Class<?> paramClass);
  
  MethodHandle asType(MethodHandle paramMethodHandle, MethodType paramMethodType);
  
  MethodHandle asCollector(MethodHandle paramMethodHandle, Class<?> paramClass, int paramInt);
  
  MethodHandle asSpreader(MethodHandle paramMethodHandle, Class<?> paramClass, int paramInt);
  
  MethodHandle bindTo(MethodHandle paramMethodHandle, Object paramObject);
  
  MethodHandle getter(MethodHandles.Lookup paramLookup, Class<?> paramClass1, String paramString, Class<?> paramClass2);
  
  MethodHandle staticGetter(MethodHandles.Lookup paramLookup, Class<?> paramClass1, String paramString, Class<?> paramClass2);
  
  MethodHandle setter(MethodHandles.Lookup paramLookup, Class<?> paramClass1, String paramString, Class<?> paramClass2);
  
  MethodHandle staticSetter(MethodHandles.Lookup paramLookup, Class<?> paramClass1, String paramString, Class<?> paramClass2);
  
  MethodHandle find(Method paramMethod);
  
  MethodHandle findStatic(MethodHandles.Lookup paramLookup, Class<?> paramClass, String paramString, MethodType paramMethodType);
  
  MethodHandle findVirtual(MethodHandles.Lookup paramLookup, Class<?> paramClass, String paramString, MethodType paramMethodType);
  
  MethodHandle findSpecial(MethodHandles.Lookup paramLookup, Class<?> paramClass1, String paramString, MethodType paramMethodType, Class<?> paramClass2);
  
  SwitchPoint createSwitchPoint();
  
  MethodHandle guardWithTest(SwitchPoint paramSwitchPoint, MethodHandle paramMethodHandle1, MethodHandle paramMethodHandle2);
  
  MethodType type(Class<?> paramClass, Class<?>... paramVarArgs);
}
