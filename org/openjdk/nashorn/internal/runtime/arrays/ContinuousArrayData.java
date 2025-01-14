package org.openjdk.nashorn.internal.runtime.arrays;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.SwitchPoint;
import jdk.dynalink.CallSiteDescriptor;
import jdk.dynalink.linker.GuardedInvocation;
import jdk.dynalink.linker.LinkRequest;
import org.openjdk.nashorn.internal.codegen.CompilerConstants;
import org.openjdk.nashorn.internal.codegen.types.Type;
import org.openjdk.nashorn.internal.lookup.Lookup;
import org.openjdk.nashorn.internal.runtime.JSType;
import org.openjdk.nashorn.internal.runtime.ScriptObject;
import org.openjdk.nashorn.internal.runtime.UnwarrantedOptimismException;
import org.openjdk.nashorn.internal.runtime.linker.NashornCallSiteDescriptor;
import org.openjdk.nashorn.internal.runtime.logging.Logger;

@Logger(name = "arrays")
public abstract class ContinuousArrayData extends ArrayData {
  protected ContinuousArrayData(long length) {
    super(length);
  }
  
  public final boolean hasRoomFor(int index) {
    return (has(index) || (index == length() && ensure(index) == this));
  }
  
  public boolean isEmpty() {
    return (length() == 0L);
  }
  
  protected final int throwHas(int index) {
    if (!has(index))
      throw new ClassCastException(); 
    return index;
  }
  
  public Type getOptimisticType() {
    return Type.typeFor(getElementType());
  }
  
  public ContinuousArrayData widest(ContinuousArrayData otherData) {
    Class<?> elementType = getElementType();
    return (Type.widest(elementType, otherData.getElementType()) == elementType) ? this : otherData;
  }
  
  protected final MethodHandle getContinuousElementGetter(MethodHandle get, Class<?> returnType, int programPoint) {
    return getContinuousElementGetter((Class)getClass(), get, returnType, programPoint);
  }
  
  protected final MethodHandle getContinuousElementSetter(MethodHandle set, Class<?> returnType) {
    return getContinuousElementSetter((Class)getClass(), set, returnType);
  }
  
  protected MethodHandle getContinuousElementGetter(Class<? extends ContinuousArrayData> clazz, MethodHandle getHas, Class<?> returnType, int programPoint) {
    boolean isOptimistic = UnwarrantedOptimismException.isValid(programPoint);
    int fti = JSType.getAccessorTypeIndex(getHas.type().returnType());
    int ti = JSType.getAccessorTypeIndex(returnType);
    MethodHandle mh = getHas;
    if (isOptimistic && 
      ti < fti)
      mh = Lookup.MH.insertArguments(ArrayData.THROW_UNWARRANTED.methodHandle(), 1, new Object[] { Integer.valueOf(programPoint) }); 
    mh = Lookup.MH.asType(mh, mh.type().changeReturnType(returnType).changeParameterType(0, clazz));
    if (!isOptimistic)
      return Lookup.filterReturnType(mh, returnType); 
    return mh;
  }
  
  protected MethodHandle getContinuousElementSetter(Class<? extends ContinuousArrayData> clazz, MethodHandle setHas, Class<?> elementType) {
    return Lookup.MH.asType(setHas, setHas.type().changeParameterType(2, elementType).changeParameterType(0, clazz));
  }
  
  protected static final MethodHandle FAST_ACCESS_GUARD = Lookup.MH
    .dropArguments(
      CompilerConstants.staticCall(
        MethodHandles.lookup(), ContinuousArrayData.class, "guard", boolean.class, new Class[] { Class.class, ScriptObject.class }).methodHandle(), 2, new Class[] { int.class });
  
  private static boolean guard(Class<? extends ContinuousArrayData> clazz, ScriptObject sobj) {
    return (sobj != null && sobj.getArray().getClass() == clazz);
  }
  
  public GuardedInvocation findFastGetIndexMethod(Class<? extends ArrayData> clazz, CallSiteDescriptor desc, LinkRequest request) {
    MethodType callType = desc.getMethodType();
    Class<?> indexType = callType.parameterType(1);
    Class<?> returnType = callType.returnType();
    if (ContinuousArrayData.class.isAssignableFrom(clazz) && indexType == int.class) {
      Object[] args = request.getArguments();
      int index = ((Integer)args[args.length - 1]).intValue();
      if (has(index)) {
        MethodHandle getArray = ScriptObject.GET_ARRAY.methodHandle();
        int programPoint = NashornCallSiteDescriptor.isOptimistic(desc) ? NashornCallSiteDescriptor.getProgramPoint(desc) : -1;
        MethodHandle getElement = getElementGetter(returnType, programPoint);
        if (getElement != null) {
          getElement = Lookup.MH.filterArguments(getElement, 0, new MethodHandle[] { Lookup.MH.asType(getArray, getArray.type().changeReturnType(clazz)) });
          MethodHandle guard = Lookup.MH.insertArguments(FAST_ACCESS_GUARD, 0, new Object[] { clazz });
          return new GuardedInvocation(getElement, guard, (SwitchPoint)null, (Class)ClassCastException.class);
        } 
      } 
    } 
    return null;
  }
  
  public GuardedInvocation findFastSetIndexMethod(Class<? extends ArrayData> clazz, CallSiteDescriptor desc, LinkRequest request) {
    MethodType callType = desc.getMethodType();
    Class<?> indexType = callType.parameterType(1);
    Class<?> elementType = callType.parameterType(2);
    if (ContinuousArrayData.class.isAssignableFrom(clazz) && indexType == int.class) {
      Object[] args = request.getArguments();
      int index = ((Integer)args[args.length - 2]).intValue();
      if (hasRoomFor(index)) {
        MethodHandle setElement = getElementSetter(elementType);
        if (setElement != null) {
          MethodHandle getArray = ScriptObject.GET_ARRAY.methodHandle();
          getArray = Lookup.MH.asType(getArray, getArray.type().changeReturnType(getClass()));
          setElement = Lookup.MH.filterArguments(setElement, 0, new MethodHandle[] { getArray });
          MethodHandle guard = Lookup.MH.insertArguments(FAST_ACCESS_GUARD, 0, new Object[] { clazz });
          return new GuardedInvocation(setElement, guard, (SwitchPoint)null, (Class)ClassCastException.class);
        } 
      } 
    } 
    return null;
  }
  
  public double fastPush(int arg) {
    throw new ClassCastException(String.valueOf(getClass()));
  }
  
  public double fastPush(long arg) {
    throw new ClassCastException(String.valueOf(getClass()));
  }
  
  public double fastPush(double arg) {
    throw new ClassCastException(String.valueOf(getClass()));
  }
  
  public double fastPush(Object arg) {
    throw new ClassCastException(String.valueOf(getClass()));
  }
  
  public int fastPopInt() {
    throw new ClassCastException(String.valueOf(getClass()));
  }
  
  public double fastPopDouble() {
    throw new ClassCastException(String.valueOf(getClass()));
  }
  
  public Object fastPopObject() {
    throw new ClassCastException(String.valueOf(getClass()));
  }
  
  public ContinuousArrayData fastConcat(ContinuousArrayData otherData) {
    throw new ClassCastException("" + getClass() + " != " + getClass());
  }
  
  public abstract MethodHandle getElementGetter(Class<?> paramClass, int paramInt);
  
  public abstract MethodHandle getElementSetter(Class<?> paramClass);
  
  public abstract ContinuousArrayData copy();
  
  public abstract Class<?> getElementType();
  
  public abstract Class<?> getBoxedElementType();
}
