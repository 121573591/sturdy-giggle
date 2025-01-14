package org.openjdk.nashorn.internal.runtime;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import jdk.dynalink.CallSiteDescriptor;
import jdk.dynalink.linker.GuardedInvocation;
import jdk.dynalink.linker.support.TypeUtilities;
import org.openjdk.nashorn.internal.codegen.types.Type;
import org.openjdk.nashorn.internal.lookup.Lookup;
import org.openjdk.nashorn.internal.runtime.linker.NashornCallSiteDescriptor;

public final class OptimisticReturnFilters {
  private static final MethodHandle[] ENSURE_INT;
  
  private static final MethodHandle[] ENSURE_NUMBER;
  
  static {
    MethodHandle INT_DOUBLE = findOwnMH("ensureInt", int.class, new Class[] { double.class, int.class });
    ENSURE_INT = new MethodHandle[] { null, INT_DOUBLE, findOwnMH("ensureInt", int.class, new Class[] { Object.class, int.class }), findOwnMH("ensureInt", int.class, new Class[] { int.class }), findOwnMH("ensureInt", int.class, new Class[] { boolean.class, int.class }), findOwnMH("ensureInt", int.class, new Class[] { char.class, int.class }), findOwnMH("ensureInt", int.class, new Class[] { long.class, int.class }), INT_DOUBLE.asType(INT_DOUBLE.type().changeParameterType(0, float.class)) };
  }
  
  private static final int VOID_TYPE_INDEX = ENSURE_INT.length - 5;
  
  private static final int BOOLEAN_TYPE_INDEX = ENSURE_INT.length - 4;
  
  private static final int CHAR_TYPE_INDEX = ENSURE_INT.length - 3;
  
  private static final int LONG_TYPE_INDEX = ENSURE_INT.length - 2;
  
  private static final int FLOAT_TYPE_INDEX = ENSURE_INT.length - 1;
  
  static {
    ENSURE_NUMBER = new MethodHandle[] { null, null, findOwnMH("ensureNumber", double.class, new Class[] { Object.class, int.class }), ENSURE_INT[VOID_TYPE_INDEX].asType(ENSURE_INT[VOID_TYPE_INDEX].type().changeReturnType(double.class)), ENSURE_INT[BOOLEAN_TYPE_INDEX].asType(ENSURE_INT[BOOLEAN_TYPE_INDEX].type().changeReturnType(double.class)), ENSURE_INT[CHAR_TYPE_INDEX].asType(ENSURE_INT[CHAR_TYPE_INDEX].type().changeReturnType(double.class)), findOwnMH("ensureNumber", double.class, new Class[] { long.class, int.class }), null };
  }
  
  public static MethodHandle filterOptimisticReturnValue(MethodHandle mh, Class<?> expectedReturnType, int programPoint) {
    if (!UnwarrantedOptimismException.isValid(programPoint))
      return mh; 
    MethodType type = mh.type();
    Class<?> actualReturnType = type.returnType();
    if (TypeUtilities.isConvertibleWithoutLoss(actualReturnType, expectedReturnType))
      return mh; 
    MethodHandle guard = getOptimisticTypeGuard(expectedReturnType, actualReturnType);
    return (guard == null) ? mh : Lookup.MH.filterReturnValue(mh, Lookup.MH.insertArguments(guard, guard.type().parameterCount() - 1, new Object[] { Integer.valueOf(programPoint) }));
  }
  
  public static GuardedInvocation filterOptimisticReturnValue(GuardedInvocation inv, CallSiteDescriptor desc) {
    if (!NashornCallSiteDescriptor.isOptimistic(desc))
      return inv; 
    return inv.replaceMethods(filterOptimisticReturnValue(inv.getInvocation(), desc.getMethodType().returnType(), 
          NashornCallSiteDescriptor.getProgramPoint(desc)), inv.getGuard());
  }
  
  private static MethodHandle getOptimisticTypeGuard(Class<?> actual, Class<?> provable) {
    MethodHandle guard;
    int provableTypeIndex = getProvableTypeIndex(provable);
    if (actual == int.class) {
      guard = ENSURE_INT[provableTypeIndex];
    } else if (actual == double.class) {
      guard = ENSURE_NUMBER[provableTypeIndex];
    } else {
      guard = null;
      assert !actual.isPrimitive() : "" + actual + ", " + actual;
    } 
    if (guard != null && !provable.isPrimitive())
      return guard.asType(guard.type().changeParameterType(0, provable)); 
    return guard;
  }
  
  private static int getProvableTypeIndex(Class<?> provable) {
    int accTypeIndex = JSType.getAccessorTypeIndex(provable);
    if (accTypeIndex != -1)
      return accTypeIndex; 
    if (provable == boolean.class)
      return BOOLEAN_TYPE_INDEX; 
    if (provable == void.class)
      return VOID_TYPE_INDEX; 
    if (provable == byte.class || provable == short.class)
      return 0; 
    if (provable == char.class)
      return CHAR_TYPE_INDEX; 
    if (provable == long.class)
      return LONG_TYPE_INDEX; 
    if (provable == float.class)
      return FLOAT_TYPE_INDEX; 
    throw new AssertionError(provable.getName());
  }
  
  private static int ensureInt(long arg, int programPoint) {
    if (JSType.isRepresentableAsInt(arg))
      return (int)arg; 
    throw UnwarrantedOptimismException.createNarrowest(Long.valueOf(arg), programPoint);
  }
  
  private static int ensureInt(double arg, int programPoint) {
    if (JSType.isStrictlyRepresentableAsInt(arg))
      return (int)arg; 
    throw new UnwarrantedOptimismException(Double.valueOf(arg), programPoint, Type.NUMBER);
  }
  
  public static int ensureInt(Object arg, int programPoint) {
    if (isPrimitiveNumberWrapper(arg)) {
      double d = ((Number)arg).doubleValue();
      if (JSType.isStrictlyRepresentableAsInt(d))
        return (int)d; 
    } 
    throw UnwarrantedOptimismException.createNarrowest(arg, programPoint);
  }
  
  private static boolean isPrimitiveNumberWrapper(Object obj) {
    if (obj == null)
      return false; 
    Class<?> c = obj.getClass();
    return (c == Integer.class || c == Double.class || c == Long.class || c == Float.class || c == Short.class || c == Byte.class);
  }
  
  private static int ensureInt(boolean arg, int programPoint) {
    throw new UnwarrantedOptimismException(Boolean.valueOf(arg), programPoint, Type.OBJECT);
  }
  
  private static int ensureInt(char arg, int programPoint) {
    throw new UnwarrantedOptimismException(Character.valueOf(arg), programPoint, Type.OBJECT);
  }
  
  private static int ensureInt(int programPoint) {
    throw new UnwarrantedOptimismException(ScriptRuntime.UNDEFINED, programPoint, Type.OBJECT);
  }
  
  private static double ensureNumber(long arg, int programPoint) {
    if (JSType.isRepresentableAsDouble(arg))
      return arg; 
    throw new UnwarrantedOptimismException(Long.valueOf(arg), programPoint, Type.OBJECT);
  }
  
  public static double ensureNumber(Object arg, int programPoint) {
    if (isPrimitiveNumberWrapper(arg) && (arg
      .getClass() != Long.class || JSType.isRepresentableAsDouble(((Long)arg).longValue())))
      return ((Number)arg).doubleValue(); 
    throw new UnwarrantedOptimismException(arg, programPoint, Type.OBJECT);
  }
  
  private static MethodHandle findOwnMH(String name, Class<?> rtype, Class<?>... types) {
    return Lookup.MH.findStatic(MethodHandles.lookup(), OptimisticReturnFilters.class, name, Lookup.MH.type(rtype, types));
  }
}
