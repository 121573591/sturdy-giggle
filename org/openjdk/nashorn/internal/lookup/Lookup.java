package org.openjdk.nashorn.internal.lookup;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import org.openjdk.nashorn.internal.runtime.ECMAErrors;
import org.openjdk.nashorn.internal.runtime.JSType;
import org.openjdk.nashorn.internal.runtime.ScriptRuntime;

public final class Lookup {
  public static final MethodHandleFunctionality MH = MethodHandleFactory.getFunctionality();
  
  public static final MethodHandle EMPTY_GETTER = findOwnMH("emptyGetter", Object.class, new Class[] { Object.class });
  
  public static final MethodHandle EMPTY_SETTER = findOwnMH("emptySetter", void.class, new Class[] { Object.class, Object.class });
  
  public static final MethodHandle TYPE_ERROR_THROWER = findOwnMH("typeErrorThrower", Object.class, new Class[] { Object.class });
  
  public static final MethodType GET_OBJECT_TYPE = MH.type(Object.class, new Class[] { Object.class });
  
  public static final MethodType SET_OBJECT_TYPE = MH.type(void.class, new Class[] { Object.class, Object.class });
  
  public static final MethodType GET_PRIMITIVE_TYPE = MH.type(long.class, new Class[] { Object.class });
  
  public static final MethodType SET_PRIMITIVE_TYPE = MH.type(void.class, new Class[] { Object.class, long.class });
  
  public static Object emptyGetter(Object self) {
    return ScriptRuntime.UNDEFINED;
  }
  
  public static void emptySetter(Object self, Object value) {}
  
  public static MethodHandle emptyGetter(Class<?> type) {
    return filterReturnType(EMPTY_GETTER, type);
  }
  
  public static Object typeErrorThrower(Object self) {
    throw ECMAErrors.typeError("strict.getter.setter.poison", new String[] { ScriptRuntime.safeToString(self) });
  }
  
  public static MethodHandle filterArgumentType(MethodHandle mh, int n, Class<?> from) {
    Class<?> to = mh.type().parameterType(n);
    if (from != int.class)
      if (from == long.class) {
        if (to == int.class)
          return MH.filterArguments(mh, n, new MethodHandle[] { JSType.TO_INT32_L.methodHandle() }); 
      } else if (from == double.class) {
        if (to == int.class)
          return MH.filterArguments(mh, n, new MethodHandle[] { JSType.TO_INT32_D.methodHandle() }); 
        if (to == long.class)
          return MH.filterArguments(mh, n, new MethodHandle[] { JSType.TO_UINT32_D.methodHandle() }); 
      } else if (!from.isPrimitive()) {
        if (to == int.class)
          return MH.filterArguments(mh, n, new MethodHandle[] { JSType.TO_INT32.methodHandle() }); 
        if (to == long.class)
          return MH.filterArguments(mh, n, new MethodHandle[] { JSType.TO_UINT32.methodHandle() }); 
        if (to == double.class)
          return MH.filterArguments(mh, n, new MethodHandle[] { JSType.TO_NUMBER.methodHandle() }); 
        if (!to.isPrimitive())
          return mh; 
        assert false : "unsupported Lookup.filterReturnType type " + from + " -> " + to;
      }  
    return MH.explicitCastArguments(mh, mh.type().changeParameterType(n, from));
  }
  
  public static MethodHandle filterReturnType(MethodHandle mh, Class<?> type) {
    Class<?> retType = mh.type().returnType();
    if (retType != int.class)
      if (retType == long.class) {
        if (type == int.class)
          return MH.filterReturnValue(mh, JSType.TO_INT32_L.methodHandle()); 
      } else if (retType == double.class) {
        if (type == int.class)
          return MH.filterReturnValue(mh, JSType.TO_INT32_D.methodHandle()); 
        if (type == long.class)
          return MH.filterReturnValue(mh, JSType.TO_UINT32_D.methodHandle()); 
      } else if (!retType.isPrimitive()) {
        if (type == int.class)
          return MH.filterReturnValue(mh, JSType.TO_INT32.methodHandle()); 
        if (type == long.class)
          return MH.filterReturnValue(mh, JSType.TO_UINT32.methodHandle()); 
        if (type == double.class)
          return MH.filterReturnValue(mh, JSType.TO_NUMBER.methodHandle()); 
        if (!type.isPrimitive())
          return mh; 
        assert false : "unsupported Lookup.filterReturnType type " + retType + " -> " + type;
      }  
    return MH.explicitCastArguments(mh, mh.type().changeReturnType(type));
  }
  
  private static MethodHandle findOwnMH(String name, Class<?> rtype, Class<?>... types) {
    return MH.findStatic(MethodHandles.lookup(), Lookup.class, name, MH.type(rtype, types));
  }
}
