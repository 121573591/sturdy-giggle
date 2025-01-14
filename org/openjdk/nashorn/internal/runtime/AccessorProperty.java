package org.openjdk.nashorn.internal.runtime;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.SwitchPoint;
import java.util.logging.Level;
import org.openjdk.nashorn.internal.codegen.ObjectClassGenerator;
import org.openjdk.nashorn.internal.codegen.types.Type;
import org.openjdk.nashorn.internal.lookup.Lookup;
import org.openjdk.nashorn.internal.lookup.MethodHandleFactory;
import org.openjdk.nashorn.internal.objects.Global;

public class AccessorProperty extends Property {
  private static final MethodHandles.Lookup LOOKUP = MethodHandles.lookup();
  
  private static final MethodHandle REPLACE_MAP = findOwnMH_S("replaceMap", Object.class, new Class[] { Object.class, PropertyMap.class });
  
  private static final MethodHandle INVALIDATE_SP = findOwnMH_S("invalidateSwitchPoint", Object.class, new Class[] { AccessorProperty.class, Object.class });
  
  private static final int NOOF_TYPES = JSType.getNumberOfAccessorTypes();
  
  private static final long serialVersionUID = 3371720170182154920L;
  
  private static final ClassValue<Accessors> GETTERS_SETTERS = new ClassValue<Accessors>() {
      protected AccessorProperty.Accessors computeValue(Class<?> structure) {
        return new AccessorProperty.Accessors(structure);
      }
    };
  
  private static class Accessors {
    final MethodHandle[] objectGetters;
    
    final MethodHandle[] objectSetters;
    
    final MethodHandle[] primitiveGetters;
    
    final MethodHandle[] primitiveSetters;
    
    Accessors(Class<?> structure) {
      int fieldCount = ObjectClassGenerator.getFieldCount(structure);
      this.objectGetters = new MethodHandle[fieldCount];
      this.objectSetters = new MethodHandle[fieldCount];
      this.primitiveGetters = new MethodHandle[fieldCount];
      this.primitiveSetters = new MethodHandle[fieldCount];
      int i;
      for (i = 0; i < fieldCount; i++) {
        String fieldName = ObjectClassGenerator.getFieldName(i, Type.OBJECT);
        Class<?> typeClass = Type.OBJECT.getTypeClass();
        this.objectGetters[i] = Lookup.MH.asType(Lookup.MH.getter(AccessorProperty.LOOKUP, structure, fieldName, typeClass), Lookup.GET_OBJECT_TYPE);
        this.objectSetters[i] = Lookup.MH.asType(Lookup.MH.setter(AccessorProperty.LOOKUP, structure, fieldName, typeClass), Lookup.SET_OBJECT_TYPE);
      } 
      if (!StructureLoader.isSingleFieldStructure(structure.getName()))
        for (i = 0; i < fieldCount; i++) {
          String fieldNamePrimitive = ObjectClassGenerator.getFieldName(i, ObjectClassGenerator.PRIMITIVE_FIELD_TYPE);
          Class<?> typeClass = ObjectClassGenerator.PRIMITIVE_FIELD_TYPE.getTypeClass();
          this.primitiveGetters[i] = Lookup.MH.asType(Lookup.MH.getter(AccessorProperty.LOOKUP, structure, fieldNamePrimitive, typeClass), Lookup.GET_PRIMITIVE_TYPE);
          this.primitiveSetters[i] = Lookup.MH.asType(Lookup.MH.setter(AccessorProperty.LOOKUP, structure, fieldNamePrimitive, typeClass), Lookup.SET_PRIMITIVE_TYPE);
        }  
    }
  }
  
  private transient MethodHandle[] GETTER_CACHE = new MethodHandle[NOOF_TYPES];
  
  transient MethodHandle primitiveGetter;
  
  transient MethodHandle primitiveSetter;
  
  transient MethodHandle objectGetter;
  
  transient MethodHandle objectSetter;
  
  public static AccessorProperty create(Object key, int propertyFlags, MethodHandle getter, MethodHandle setter) {
    return new AccessorProperty(key, propertyFlags, -1, getter, setter);
  }
  
  AccessorProperty(AccessorProperty property, Object delegate) {
    super(property, property.getFlags() | 0x100);
    this.primitiveGetter = bindTo(property.primitiveGetter, delegate);
    this.primitiveSetter = bindTo(property.primitiveSetter, delegate);
    this.objectGetter = bindTo(property.objectGetter, delegate);
    this.objectSetter = bindTo(property.objectSetter, delegate);
    property.GETTER_CACHE = new MethodHandle[NOOF_TYPES];
    setType(property.getType());
  }
  
  protected AccessorProperty(Object key, int flags, int slot, MethodHandle primitiveGetter, MethodHandle primitiveSetter, MethodHandle objectGetter, MethodHandle objectSetter) {
    super(key, flags, slot);
    assert getClass() != AccessorProperty.class;
    this.primitiveGetter = primitiveGetter;
    this.primitiveSetter = primitiveSetter;
    this.objectGetter = objectGetter;
    this.objectSetter = objectSetter;
    initializeType();
  }
  
  private AccessorProperty(Object key, int flags, int slot, MethodHandle getter, MethodHandle setter) {
    super(key, flags | 0x80 | 0x800 | (getter.type().returnType().isPrimitive() ? 64 : 0), slot);
    assert !isSpill();
    Class<?> getterType = getter.type().returnType();
    Class<?> setterType = (setter == null) ? null : setter.type().parameterType(1);
    assert setterType == null || setterType == getterType;
    if (getterType == int.class) {
      this.primitiveGetter = Lookup.MH.asType(getter, Lookup.GET_PRIMITIVE_TYPE);
      this.primitiveSetter = (setter == null) ? null : Lookup.MH.asType(setter, Lookup.SET_PRIMITIVE_TYPE);
    } else if (getterType == double.class) {
      this.primitiveGetter = Lookup.MH.asType(Lookup.MH.filterReturnValue(getter, ObjectClassGenerator.PACK_DOUBLE), Lookup.GET_PRIMITIVE_TYPE);
      this.primitiveSetter = (setter == null) ? null : Lookup.MH.asType(Lookup.MH.filterArguments(setter, 1, new MethodHandle[] { ObjectClassGenerator.UNPACK_DOUBLE }), Lookup.SET_PRIMITIVE_TYPE);
    } else {
      this.primitiveGetter = this.primitiveSetter = null;
    } 
    assert this.primitiveGetter == null || this.primitiveGetter.type() == Lookup.GET_PRIMITIVE_TYPE : "" + this.primitiveGetter + "!=" + this.primitiveGetter;
    assert this.primitiveSetter == null || this.primitiveSetter.type() == Lookup.SET_PRIMITIVE_TYPE : this.primitiveSetter;
    this.objectGetter = (getter.type() != Lookup.GET_OBJECT_TYPE) ? Lookup.MH.asType(getter, Lookup.GET_OBJECT_TYPE) : getter;
    this.objectSetter = (setter != null && setter.type() != Lookup.SET_OBJECT_TYPE) ? Lookup.MH.asType(setter, Lookup.SET_OBJECT_TYPE) : setter;
    setType(getterType);
  }
  
  public AccessorProperty(Object key, int flags, Class<?> structure, int slot) {
    super(key, flags, slot);
    initGetterSetter(structure);
    initializeType();
  }
  
  private void initGetterSetter(Class<?> structure) {
    int slot = getSlot();
    if (isParameter() && hasArguments()) {
      MethodHandle arguments = Lookup.MH.getter(LOOKUP, structure, "arguments", ScriptObject.class);
      this.objectGetter = Lookup.MH.asType(Lookup.MH.insertArguments(Lookup.MH.filterArguments(ScriptObject.GET_ARGUMENT.methodHandle(), 0, new MethodHandle[] { arguments }), 1, new Object[] { Integer.valueOf(slot) }), Lookup.GET_OBJECT_TYPE);
      this.objectSetter = Lookup.MH.asType(Lookup.MH.insertArguments(Lookup.MH.filterArguments(ScriptObject.SET_ARGUMENT.methodHandle(), 0, new MethodHandle[] { arguments }), 1, new Object[] { Integer.valueOf(slot) }), Lookup.SET_OBJECT_TYPE);
      this.primitiveGetter = null;
      this.primitiveSetter = null;
    } else {
      Accessors gs = GETTERS_SETTERS.get(structure);
      this.objectGetter = gs.objectGetters[slot];
      this.primitiveGetter = gs.primitiveGetters[slot];
      this.objectSetter = gs.objectSetters[slot];
      this.primitiveSetter = gs.primitiveSetters[slot];
    } 
    assert hasDualFields() != StructureLoader.isSingleFieldStructure(structure.getName());
  }
  
  protected AccessorProperty(Object key, int flags, int slot, ScriptObject owner, Object initialValue) {
    this(key, flags, owner.getClass(), slot);
    setInitialValue(owner, initialValue);
  }
  
  public AccessorProperty(Object key, int flags, Class<?> structure, int slot, Class<?> initialType) {
    this(key, flags, structure, slot);
    setType(hasDualFields() ? initialType : Object.class);
  }
  
  protected AccessorProperty(AccessorProperty property, Class<?> newType) {
    super(property, property.getFlags());
    this.GETTER_CACHE = (newType != property.getLocalType()) ? new MethodHandle[NOOF_TYPES] : property.GETTER_CACHE;
    this.primitiveGetter = property.primitiveGetter;
    this.primitiveSetter = property.primitiveSetter;
    this.objectGetter = property.objectGetter;
    this.objectSetter = property.objectSetter;
    setType(newType);
  }
  
  protected AccessorProperty(AccessorProperty property) {
    this(property, property.getLocalType());
  }
  
  protected final void setInitialValue(ScriptObject owner, Object initialValue) {
    setType(hasDualFields() ? JSType.unboxedFieldType(initialValue) : Object.class);
    if (initialValue instanceof Integer) {
      invokeSetter(owner, ((Integer)initialValue).intValue());
    } else if (initialValue instanceof Double) {
      invokeSetter(owner, ((Double)initialValue).doubleValue());
    } else {
      invokeSetter(owner, initialValue);
    } 
  }
  
  protected final void initializeType() {
    setType(!hasDualFields() ? Object.class : null);
  }
  
  private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
    s.defaultReadObject();
    this.GETTER_CACHE = new MethodHandle[NOOF_TYPES];
  }
  
  private static MethodHandle bindTo(MethodHandle mh, Object receiver) {
    if (mh == null)
      return null; 
    return Lookup.MH.dropArguments(Lookup.MH.bindTo(mh, receiver), 0, new Class[] { Object.class });
  }
  
  public Property copy() {
    return new AccessorProperty(this);
  }
  
  public Property copy(Class<?> newType) {
    return new AccessorProperty(this, newType);
  }
  
  public int getIntValue(ScriptObject self, ScriptObject owner) {
    try {
      return getGetter(int.class).invokeExact(self);
    } catch (Error|RuntimeException e) {
      throw e;
    } catch (Throwable e) {
      throw new RuntimeException(e);
    } 
  }
  
  public double getDoubleValue(ScriptObject self, ScriptObject owner) {
    try {
      return getGetter(double.class).invokeExact(self);
    } catch (Error|RuntimeException e) {
      throw e;
    } catch (Throwable e) {
      throw new RuntimeException(e);
    } 
  }
  
  public Object getObjectValue(ScriptObject self, ScriptObject owner) {
    try {
      return getGetter(Object.class).invokeExact(self);
    } catch (Error|RuntimeException e) {
      throw e;
    } catch (Throwable e) {
      throw new RuntimeException(e);
    } 
  }
  
  protected final void invokeSetter(ScriptObject self, int value) {
    try {
      getSetter(int.class, self.getMap()).invokeExact(self, value);
    } catch (Error|RuntimeException e) {
      throw e;
    } catch (Throwable e) {
      throw new RuntimeException(e);
    } 
  }
  
  protected final void invokeSetter(ScriptObject self, double value) {
    try {
      getSetter(double.class, self.getMap()).invokeExact(self, value);
    } catch (Error|RuntimeException e) {
      throw e;
    } catch (Throwable e) {
      throw new RuntimeException(e);
    } 
  }
  
  protected final void invokeSetter(ScriptObject self, Object value) {
    try {
      getSetter(Object.class, self.getMap()).invokeExact(self, value);
    } catch (Error|RuntimeException e) {
      throw e;
    } catch (Throwable e) {
      throw new RuntimeException(e);
    } 
  }
  
  public void setValue(ScriptObject self, ScriptObject owner, int value, boolean strict) {
    assert isConfigurable() || isWritable() : "" + getKey() + " is not writable or configurable";
    invokeSetter(self, value);
  }
  
  public void setValue(ScriptObject self, ScriptObject owner, double value, boolean strict) {
    assert isConfigurable() || isWritable() : "" + getKey() + " is not writable or configurable";
    invokeSetter(self, value);
  }
  
  public void setValue(ScriptObject self, ScriptObject owner, Object value, boolean strict) {
    invokeSetter(self, value);
  }
  
  void initMethodHandles(Class<?> structure) {
    if (!ScriptObject.class.isAssignableFrom(structure) || !StructureLoader.isStructureClass(structure.getName()))
      throw new IllegalArgumentException(); 
    assert !isSpill();
    initGetterSetter(structure);
  }
  
  public MethodHandle getGetter(Class<?> type) {
    MethodHandle getter;
    int i = JSType.getAccessorTypeIndex(type);
    assert type == int.class || type == double.class || type == Object.class : "invalid getter type " + type + " for " + 
      
      getKey();
    checkUndeclared();
    MethodHandle[] getterCache = this.GETTER_CACHE;
    MethodHandle cachedGetter = getterCache[i];
    if (cachedGetter != null) {
      getter = cachedGetter;
    } else {
      getter = debug(
          ObjectClassGenerator.createGetter(
            getLocalType(), type, this.primitiveGetter, this.objectGetter, -1), 
          
          getLocalType(), type, "get");
      getterCache[i] = getter;
    } 
    assert getter.type().returnType() == type && getter.type().parameterType(0) == Object.class;
    return getter;
  }
  
  public MethodHandle getOptimisticGetter(Class<?> type, int programPoint) {
    if (this.objectGetter == null)
      return getOptimisticPrimitiveGetter(type, programPoint); 
    checkUndeclared();
    return debug(
        ObjectClassGenerator.createGetter(
          getLocalType(), type, this.primitiveGetter, this.objectGetter, programPoint), 
        
        getLocalType(), type, "get");
  }
  
  private MethodHandle getOptimisticPrimitiveGetter(Class<?> type, int programPoint) {
    MethodHandle g = getGetter(getLocalType());
    return Lookup.MH.asType(OptimisticReturnFilters.filterOptimisticReturnValue(g, type, programPoint), g.type().changeReturnType(type));
  }
  
  private Property getWiderProperty(Class<?> type) {
    return copy(type);
  }
  
  private PropertyMap getWiderMap(PropertyMap oldMap, Property newProperty) {
    PropertyMap newMap = oldMap.replaceProperty(this, newProperty);
    assert oldMap.size() > 0;
    assert newMap.size() == oldMap.size();
    return newMap;
  }
  
  private void checkUndeclared() {
    if ((getFlags() & 0x200) != 0)
      throw ECMAErrors.referenceError("not.defined", new String[] { getKey().toString() }); 
  }
  
  private static Object replaceMap(Object sobj, PropertyMap newMap) {
    ((ScriptObject)sobj).setMap(newMap);
    return sobj;
  }
  
  private static Object invalidateSwitchPoint(AccessorProperty property, Object obj) {
    if (!property.builtinSwitchPoint.hasBeenInvalidated())
      SwitchPoint.invalidateAll(new SwitchPoint[] { property.builtinSwitchPoint }); 
    return obj;
  }
  
  private MethodHandle generateSetter(Class<?> forType, Class<?> type) {
    return debug(ObjectClassGenerator.createSetter(forType, type, this.primitiveSetter, this.objectSetter), getLocalType(), type, "set");
  }
  
  protected final boolean isUndefined() {
    return (getLocalType() == null);
  }
  
  public boolean hasNativeSetter() {
    return (this.objectSetter != null);
  }
  
  public MethodHandle getSetter(Class<?> type, PropertyMap currentMap) {
    MethodHandle mh;
    checkUndeclared();
    int typeIndex = JSType.getAccessorTypeIndex(type);
    int currentTypeIndex = JSType.getAccessorTypeIndex(getLocalType());
    if (needsInvalidator(typeIndex, currentTypeIndex)) {
      Property newProperty = getWiderProperty(type);
      PropertyMap newMap = getWiderMap(currentMap, newProperty);
      MethodHandle widerSetter = newProperty.getSetter(type, newMap);
      Class<?> ct = getLocalType();
      mh = Lookup.MH.filterArguments(widerSetter, 0, new MethodHandle[] { Lookup.MH.insertArguments(debugReplace(ct, type, currentMap, newMap), 1, new Object[] { newMap }) });
      if (ct != null && ct.isPrimitive() && !type.isPrimitive())
        mh = ObjectClassGenerator.createGuardBoxedPrimitiveSetter(ct, generateSetter(ct, ct), mh); 
    } else {
      Class<?> forType = isUndefined() ? type : getLocalType();
      mh = generateSetter(!forType.isPrimitive() ? Object.class : forType, type);
    } 
    if (isBuiltin())
      mh = Lookup.MH.filterArguments(mh, 0, new MethodHandle[] { debugInvalidate(Lookup.MH.insertArguments(INVALIDATE_SP, 0, new Object[] { this }), getKey().toString()) }); 
    assert mh.type().returnType() == void.class : mh.type();
    return mh;
  }
  
  public final boolean canChangeType() {
    if (!hasDualFields())
      return false; 
    return (getLocalType() == null || (getLocalType() != Object.class && (isConfigurable() || isWritable())));
  }
  
  private boolean needsInvalidator(int typeIndex, int currentTypeIndex) {
    return (canChangeType() && typeIndex > currentTypeIndex);
  }
  
  private MethodHandle debug(MethodHandle mh, Class<?> forType, Class<?> type, String tag) {
    if (!Context.DEBUG || !Global.hasInstance())
      return mh; 
    Context context = Context.getContextTrusted();
    assert context != null;
    return context.addLoggingToHandle((Class)ObjectClassGenerator.class, Level.INFO, mh, 0, true, () -> tag + " '" + tag + "' (property=" + getKey() + ", slot=" + Debug.id(this) + " " + getSlot() + " forType=" + getClass().getSimpleName() + ", type=" + MethodHandleFactory.stripName(forType) + ")");
  }
  
  private MethodHandle debugReplace(Class<?> oldType, Class<?> newType, PropertyMap oldMap, PropertyMap newMap) {
    if (!Context.DEBUG || !Global.hasInstance())
      return REPLACE_MAP; 
    Context context = Context.getContextTrusted();
    assert context != null;
    MethodHandle mh = context.addLoggingToHandle((Class)ObjectClassGenerator.class, REPLACE_MAP, () -> "Type change for '" + getKey() + "' " + oldType + "=>" + newType);
    mh = context.addLoggingToHandle((Class)ObjectClassGenerator.class, Level.FINEST, mh, 2147483647, false, () -> "Setting map " + Debug.id(oldMap) + " => " + Debug.id(newMap) + " " + oldMap + " => " + newMap);
    return mh;
  }
  
  private static MethodHandle debugInvalidate(MethodHandle invalidator, String key) {
    if (!Context.DEBUG || !Global.hasInstance())
      return invalidator; 
    Context context = Context.getContextTrusted();
    assert context != null;
    return context.addLoggingToHandle((Class)ObjectClassGenerator.class, invalidator, () -> "Field change callback for " + key + " triggered ");
  }
  
  private static MethodHandle findOwnMH_S(String name, Class<?> rtype, Class<?>... types) {
    return Lookup.MH.findStatic(LOOKUP, AccessorProperty.class, name, Lookup.MH.type(rtype, types));
  }
}
