package org.openjdk.nashorn.internal.objects;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import org.openjdk.nashorn.internal.lookup.Lookup;
import org.openjdk.nashorn.internal.runtime.AccessorProperty;
import org.openjdk.nashorn.internal.runtime.ECMAErrors;
import org.openjdk.nashorn.internal.runtime.JSType;
import org.openjdk.nashorn.internal.runtime.Property;
import org.openjdk.nashorn.internal.runtime.PropertyDescriptor;
import org.openjdk.nashorn.internal.runtime.PropertyMap;
import org.openjdk.nashorn.internal.runtime.ScriptFunction;
import org.openjdk.nashorn.internal.runtime.ScriptObject;
import org.openjdk.nashorn.internal.runtime.ScriptRuntime;
import org.openjdk.nashorn.internal.runtime.arrays.ArrayData;
import org.openjdk.nashorn.internal.runtime.arrays.ArrayIndex;

public final class NativeArguments extends ScriptObject {
  private static final MethodHandle G$LENGTH = findOwnMH("G$length", Object.class, new Class[] { Object.class });
  
  private static final MethodHandle S$LENGTH = findOwnMH("S$length", void.class, new Class[] { Object.class, Object.class });
  
  private static final MethodHandle G$CALLEE = findOwnMH("G$callee", Object.class, new Class[] { Object.class });
  
  private static final MethodHandle S$CALLEE = findOwnMH("S$callee", void.class, new Class[] { Object.class, Object.class });
  
  private static final PropertyMap map$;
  
  private Object length;
  
  private Object callee;
  
  private final int numMapped;
  
  private final int numParams;
  
  private ArrayData unmappedArgs;
  
  private BitSet deleted;
  
  static {
    ArrayList<Property> properties = new ArrayList<>(2);
    properties.add(AccessorProperty.create("length", 2, G$LENGTH, S$LENGTH));
    properties.add(AccessorProperty.create("callee", 2, G$CALLEE, S$CALLEE));
    map$ = PropertyMap.newMap(properties);
  }
  
  static PropertyMap getInitialMap() {
    return map$;
  }
  
  NativeArguments(Object[] arguments, Object callee, int numParams, ScriptObject proto, PropertyMap map) {
    super(proto, map);
    setIsArguments();
    setArray(ArrayData.allocate(arguments));
    this.length = Integer.valueOf(arguments.length);
    this.callee = callee;
    this.numMapped = Math.min(numParams, arguments.length);
    this.numParams = numParams;
  }
  
  public String getClassName() {
    return "Arguments";
  }
  
  public Object getArgument(int key) {
    assert key >= 0 && key < this.numParams : "invalid argument index";
    return isMapped(key) ? getArray().getObject(key) : getUnmappedArg(key);
  }
  
  public void setArgument(int key, Object value) {
    assert key >= 0 && key < this.numParams : "invalid argument index";
    if (isMapped(key)) {
      setArray(getArray().set(key, value, false));
    } else {
      setUnmappedArg(key, value);
    } 
  }
  
  public boolean delete(int key, boolean strict) {
    int index = ArrayIndex.getArrayIndex(key);
    return isMapped(index) ? deleteMapped(index, strict) : super.delete(key, strict);
  }
  
  public boolean delete(double key, boolean strict) {
    int index = ArrayIndex.getArrayIndex(key);
    return isMapped(index) ? deleteMapped(index, strict) : super.delete(key, strict);
  }
  
  public boolean delete(Object key, boolean strict) {
    Object primitiveKey = JSType.toPrimitive(key, String.class);
    int index = ArrayIndex.getArrayIndex(primitiveKey);
    return isMapped(index) ? deleteMapped(index, strict) : super.delete(primitiveKey, strict);
  }
  
  public boolean defineOwnProperty(Object key, Object propertyDesc, boolean reject) {
    int index = ArrayIndex.getArrayIndex(key);
    if (index >= 0) {
      boolean isMapped = isMapped(index);
      Object oldValue = isMapped ? getArray().getObject(index) : null;
      if (!super.defineOwnProperty(key, propertyDesc, false)) {
        if (reject)
          throw ECMAErrors.typeError("cant.redefine.property", new String[] { key.toString(), ScriptRuntime.safeToString(this) }); 
        return false;
      } 
      if (isMapped) {
        PropertyDescriptor desc = toPropertyDescriptor(Global.instance(), propertyDesc);
        if (desc.type() == 2) {
          setDeleted(index, oldValue);
        } else if (desc.has("writable") && !desc.isWritable()) {
          setDeleted(index, desc.has("value") ? desc.getValue() : oldValue);
        } else if (desc.has("value")) {
          setArray(getArray().set(index, desc.getValue(), false));
        } 
      } 
      return true;
    } 
    return super.defineOwnProperty(key, propertyDesc, reject);
  }
  
  private boolean isDeleted(int index) {
    return (this.deleted != null && this.deleted.get(index));
  }
  
  private void setDeleted(int index, Object unmappedValue) {
    if (this.deleted == null)
      this.deleted = new BitSet(this.numMapped); 
    this.deleted.set(index, true);
    setUnmappedArg(index, unmappedValue);
  }
  
  private boolean deleteMapped(int index, boolean strict) {
    Object value = getArray().getObject(index);
    boolean success = super.delete(index, strict);
    if (success)
      setDeleted(index, value); 
    return success;
  }
  
  private Object getUnmappedArg(int key) {
    assert key >= 0 && key < this.numParams;
    return (this.unmappedArgs == null) ? ScriptRuntime.UNDEFINED : this.unmappedArgs.getObject(key);
  }
  
  private void setUnmappedArg(int key, Object value) {
    assert key >= 0 && key < this.numParams;
    if (this.unmappedArgs == null) {
      Object[] newValues = new Object[this.numParams];
      System.arraycopy(getArray().asObjectArray(), 0, newValues, 0, this.numMapped);
      if (this.numMapped < this.numParams)
        Arrays.fill(newValues, this.numMapped, this.numParams, ScriptRuntime.UNDEFINED); 
      this.unmappedArgs = ArrayData.allocate(newValues);
    } 
    this.unmappedArgs = this.unmappedArgs.set(key, value, false);
  }
  
  private boolean isMapped(int index) {
    return (index >= 0 && index < this.numMapped && !isDeleted(index));
  }
  
  public static ScriptObject allocate(Object[] arguments, ScriptFunction callee, int numParams) {
    boolean isStrict = (callee == null || callee.isStrict());
    Global global = Global.instance();
    ScriptObject proto = global.getObjectPrototype();
    if (isStrict)
      return new NativeStrictArguments(arguments, numParams, proto, NativeStrictArguments.getInitialMap()); 
    return new NativeArguments(arguments, callee, numParams, proto, getInitialMap());
  }
  
  public static Object G$length(Object self) {
    if (self instanceof NativeArguments)
      return ((NativeArguments)self).getArgumentsLength(); 
    return Integer.valueOf(0);
  }
  
  public static void S$length(Object self, Object value) {
    if (self instanceof NativeArguments)
      ((NativeArguments)self).setArgumentsLength(value); 
  }
  
  public static Object G$callee(Object self) {
    if (self instanceof NativeArguments)
      return ((NativeArguments)self).getCallee(); 
    return ScriptRuntime.UNDEFINED;
  }
  
  public static void S$callee(Object self, Object value) {
    if (self instanceof NativeArguments)
      ((NativeArguments)self).setCallee(value); 
  }
  
  public Object getLength() {
    return this.length;
  }
  
  private Object getArgumentsLength() {
    return this.length;
  }
  
  private void setArgumentsLength(Object length) {
    this.length = length;
  }
  
  private Object getCallee() {
    return this.callee;
  }
  
  private void setCallee(Object callee) {
    this.callee = callee;
  }
  
  private static MethodHandle findOwnMH(String name, Class<?> rtype, Class<?>... types) {
    return Lookup.MH.findStatic(MethodHandles.lookup(), NativeArguments.class, name, Lookup.MH.type(rtype, types));
  }
}
