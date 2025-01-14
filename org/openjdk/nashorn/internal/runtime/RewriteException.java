package org.openjdk.nashorn.internal.runtime;

import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.invoke.CallSite;
import java.lang.invoke.ConstantCallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Array;
import java.util.Arrays;
import org.openjdk.nashorn.internal.codegen.CompilerConstants;
import org.openjdk.nashorn.internal.codegen.types.Type;
import org.openjdk.nashorn.internal.lookup.MethodHandleFactory;
import org.openjdk.nashorn.internal.lookup.MethodHandleFunctionality;
import org.openjdk.nashorn.internal.objects.Global;

public final class RewriteException extends Exception {
  private static final MethodHandleFunctionality MH = MethodHandleFactory.getFunctionality();
  
  private ScriptObject runtimeScope;
  
  private Object[] byteCodeSlots;
  
  private final int[] previousContinuationEntryPoints;
  
  public static final CompilerConstants.Call GET_BYTECODE_SLOTS = CompilerConstants.virtualCallNoLookup(RewriteException.class, "getByteCodeSlots", Object[].class, new Class[0]);
  
  public static final CompilerConstants.Call GET_PROGRAM_POINT = CompilerConstants.virtualCallNoLookup(RewriteException.class, "getProgramPoint", int.class, new Class[0]);
  
  public static final CompilerConstants.Call GET_RETURN_VALUE = CompilerConstants.virtualCallNoLookup(RewriteException.class, "getReturnValueDestructive", Object.class, new Class[0]);
  
  public static final CompilerConstants.Call BOOTSTRAP = CompilerConstants.staticCallNoLookup(RewriteException.class, "populateArrayBootstrap", CallSite.class, new Class[] { MethodHandles.Lookup.class, String.class, MethodType.class, int.class });
  
  private static final CompilerConstants.Call POPULATE_ARRAY = CompilerConstants.staticCall(MethodHandles.lookup(), RewriteException.class, "populateArray", Object[].class, new Class[] { Object[].class, int.class, Object[].class });
  
  public static final CompilerConstants.Call TO_LONG_ARRAY = CompilerConstants.staticCallNoLookup(RewriteException.class, "toLongArray", long[].class, new Class[] { Object.class, RewriteException.class });
  
  public static final CompilerConstants.Call TO_DOUBLE_ARRAY = CompilerConstants.staticCallNoLookup(RewriteException.class, "toDoubleArray", double[].class, new Class[] { Object.class, RewriteException.class });
  
  public static final CompilerConstants.Call TO_OBJECT_ARRAY = CompilerConstants.staticCallNoLookup(RewriteException.class, "toObjectArray", Object[].class, new Class[] { Object.class, RewriteException.class });
  
  public static final CompilerConstants.Call INSTANCE_OR_NULL = CompilerConstants.staticCallNoLookup(RewriteException.class, "instanceOrNull", Object.class, new Class[] { Object.class, Class.class });
  
  public static final CompilerConstants.Call ASSERT_ARRAY_LENGTH = CompilerConstants.staticCallNoLookup(RewriteException.class, "assertArrayLength", void.class, new Class[] { Object[].class, int.class });
  
  private RewriteException(UnwarrantedOptimismException e, Object[] byteCodeSlots, String[] byteCodeSymbolNames, int[] previousContinuationEntryPoints) {
    super("", e, false, Context.DEBUG);
    this.byteCodeSlots = byteCodeSlots;
    this.runtimeScope = mergeSlotsWithScope(byteCodeSlots, byteCodeSymbolNames);
    this.previousContinuationEntryPoints = previousContinuationEntryPoints;
  }
  
  public static RewriteException create(UnwarrantedOptimismException e, Object[] byteCodeSlots, String[] byteCodeSymbolNames) {
    return create(e, byteCodeSlots, byteCodeSymbolNames, null);
  }
  
  public static RewriteException create(UnwarrantedOptimismException e, Object[] byteCodeSlots, String[] byteCodeSymbolNames, int[] previousContinuationEntryPoints) {
    return new RewriteException(e, byteCodeSlots, byteCodeSymbolNames, previousContinuationEntryPoints);
  }
  
  public static CallSite populateArrayBootstrap(MethodHandles.Lookup lookup, String name, MethodType type, int startIndex) {
    MethodHandle mh = POPULATE_ARRAY.methodHandle();
    mh = MH.insertArguments(mh, 1, new Object[] { Integer.valueOf(startIndex) });
    mh = MH.asCollector(mh, Object[].class, type.parameterCount() - 1);
    mh = MH.asType(mh, type);
    return new ConstantCallSite(mh);
  }
  
  private static ScriptObject mergeSlotsWithScope(Object[] byteCodeSlots, String[] byteCodeSymbolNames) {
    ScriptObject locals = Global.newEmptyInstance();
    int l = Math.min(byteCodeSlots.length, byteCodeSymbolNames.length);
    ScriptObject runtimeScope = null;
    String scopeName = CompilerConstants.SCOPE.symbolName();
    for (int i = 0; i < l; i++) {
      String name = byteCodeSymbolNames[i];
      Object value = byteCodeSlots[i];
      if (scopeName.equals(name)) {
        assert runtimeScope == null;
        runtimeScope = (ScriptObject)value;
      } else if (name != null) {
        locals.set(name, value, 32);
      } 
    } 
    locals.setProto(runtimeScope);
    return locals;
  }
  
  public static Object[] populateArray(Object[] arrayToBePopluated, int startIndex, Object[] items) {
    System.arraycopy(items, 0, arrayToBePopluated, startIndex, items.length);
    return arrayToBePopluated;
  }
  
  public static long[] toLongArray(Object obj, RewriteException e) {
    if (obj instanceof long[])
      return (long[])obj; 
    assert obj instanceof int[];
    int[] in = (int[])obj;
    long[] out = new long[in.length];
    for (int i = 0; i < in.length; i++)
      out[i] = in[i]; 
    return e.<long[]>replaceByteCodeValue(in, out);
  }
  
  public static double[] toDoubleArray(Object obj, RewriteException e) {
    if (obj instanceof double[])
      return (double[])obj; 
    assert obj instanceof int[] || obj instanceof long[];
    int l = Array.getLength(obj);
    double[] out = new double[l];
    for (int i = 0; i < l; i++)
      out[i] = Array.getDouble(obj, i); 
    return e.<double[]>replaceByteCodeValue(obj, out);
  }
  
  public static Object[] toObjectArray(Object obj, RewriteException e) {
    if (obj instanceof Object[])
      return (Object[])obj; 
    assert obj instanceof int[] || obj instanceof long[] || obj instanceof double[] : "" + obj + " is " + obj;
    int l = Array.getLength(obj);
    Object[] out = new Object[l];
    for (int i = 0; i < l; i++)
      out[i] = Array.get(obj, i); 
    return e.<Object[]>replaceByteCodeValue(obj, out);
  }
  
  public static Object instanceOrNull(Object obj, Class<?> clazz) {
    return clazz.isInstance(obj) ? obj : null;
  }
  
  public static void assertArrayLength(Object[] arr, int length) {
    for (int i = arr.length; i-- > length;) {
      if (arr[i] != ScriptRuntime.UNDEFINED)
        throw new AssertionError(String.format("Expected array length %d, but it is %d", new Object[] { Integer.valueOf(length), Integer.valueOf(i + 1) })); 
    } 
  }
  
  private <T> T replaceByteCodeValue(Object in, T out) {
    for (int i = 0; i < this.byteCodeSlots.length; i++) {
      if (this.byteCodeSlots[i] == in)
        this.byteCodeSlots[i] = out; 
    } 
    return out;
  }
  
  private UnwarrantedOptimismException getUOE() {
    return (UnwarrantedOptimismException)getCause();
  }
  
  public Object getReturnValueDestructive() {
    assert this.byteCodeSlots != null;
    this.byteCodeSlots = null;
    this.runtimeScope = null;
    return getUOE().getReturnValueDestructive();
  }
  
  Object getReturnValueNonDestructive() {
    return getUOE().getReturnValueNonDestructive();
  }
  
  public Type getReturnType() {
    return getUOE().getReturnType();
  }
  
  public int getProgramPoint() {
    return getUOE().getProgramPoint();
  }
  
  public Object[] getByteCodeSlots() {
    return (this.byteCodeSlots == null) ? null : (Object[])this.byteCodeSlots.clone();
  }
  
  public int[] getPreviousContinuationEntryPoints() {
    return (this.previousContinuationEntryPoints == null) ? null : (int[])this.previousContinuationEntryPoints.clone();
  }
  
  public ScriptObject getRuntimeScope() {
    return this.runtimeScope;
  }
  
  private static String stringify(Object returnValue) {
    if (returnValue == null)
      return "null"; 
    String str = returnValue.toString();
    if (returnValue instanceof String) {
      str = "'" + str + "'";
    } else if (returnValue instanceof Double) {
      str = str + "d";
    } else if (returnValue instanceof Long) {
      str = str + "l";
    } 
    return str;
  }
  
  public String getMessage() {
    return getMessage(false);
  }
  
  public String getMessageShort() {
    return getMessage(true);
  }
  
  private String getMessage(boolean isShort) {
    StringBuilder sb = new StringBuilder();
    sb.append("[pp=")
      .append(getProgramPoint())
      .append(", ");
    if (!isShort) {
      Object[] slots = this.byteCodeSlots;
      if (slots != null)
        sb.append("slots=")
          .append(Arrays.asList(slots))
          .append(", "); 
    } 
    sb.append("type=")
      .append(getReturnType())
      .append(", ");
    sb.append("value=")
      .append(stringify(getReturnValueNonDestructive()))
      .append(")]");
    return sb.toString();
  }
  
  private void writeObject(ObjectOutputStream out) throws NotSerializableException {
    throw new NotSerializableException(getClass().getName());
  }
  
  private void readObject(ObjectInputStream in) throws NotSerializableException {
    throw new NotSerializableException(getClass().getName());
  }
}
