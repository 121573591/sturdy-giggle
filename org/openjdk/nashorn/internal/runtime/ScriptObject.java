package org.openjdk.nashorn.internal.runtime;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.SwitchPoint;
import java.lang.reflect.Array;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.LongAdder;
import jdk.dynalink.CallSiteDescriptor;
import jdk.dynalink.NamedOperation;
import jdk.dynalink.StandardOperation;
import jdk.dynalink.linker.GuardedInvocation;
import jdk.dynalink.linker.LinkRequest;
import org.openjdk.nashorn.internal.codegen.CompilerConstants;
import org.openjdk.nashorn.internal.codegen.ObjectClassGenerator;
import org.openjdk.nashorn.internal.codegen.types.Type;
import org.openjdk.nashorn.internal.lookup.Lookup;
import org.openjdk.nashorn.internal.objects.Global;
import org.openjdk.nashorn.internal.runtime.arrays.ArrayData;
import org.openjdk.nashorn.internal.runtime.arrays.ArrayIndex;
import org.openjdk.nashorn.internal.runtime.linker.NashornCallSiteDescriptor;
import org.openjdk.nashorn.internal.runtime.linker.NashornGuards;

public abstract class ScriptObject implements PropertyAccess, Cloneable {
  public static final String PROTO_PROPERTY_NAME = "__proto__";
  
  public static final String NO_SUCH_METHOD_NAME = "__noSuchMethod__";
  
  public static final String NO_SUCH_PROPERTY_NAME = "__noSuchProperty__";
  
  public static final int IS_ARRAY = 1;
  
  public static final int IS_ARGUMENTS = 2;
  
  public static final int IS_LENGTH_NOT_WRITABLE = 4;
  
  public static final int IS_BUILTIN = 8;
  
  public static final int IS_INTERNAL = 16;
  
  public static final int SPILL_RATE = 8;
  
  private PropertyMap map;
  
  private ScriptObject proto;
  
  private int flags;
  
  protected long[] primitiveSpill;
  
  protected Object[] objectSpill;
  
  private ArrayData arrayData;
  
  public static final MethodHandle GETPROTO = findOwnMH_V("getProto", ScriptObject.class, new Class[0]);
  
  static final MethodHandle MEGAMORPHIC_GET = findOwnMH_V("megamorphicGet", Object.class, new Class[] { String.class, boolean.class, boolean.class });
  
  static final MethodHandle GLOBALFILTER = findOwnMH_S("globalFilter", Object.class, new Class[] { Object.class });
  
  static final MethodHandle DECLARE_AND_SET = findOwnMH_V("declareAndSet", void.class, new Class[] { String.class, Object.class });
  
  private static final MethodHandle TRUNCATINGFILTER = findOwnMH_S("truncatingFilter", Object[].class, new Class[] { int.class, Object[].class });
  
  private static final MethodHandle KNOWNFUNCPROPGUARDSELF = findOwnMH_S("knownFunctionPropertyGuardSelf", boolean.class, new Class[] { Object.class, PropertyMap.class, MethodHandle.class, ScriptFunction.class });
  
  private static final MethodHandle KNOWNFUNCPROPGUARDPROTO = findOwnMH_S("knownFunctionPropertyGuardProto", boolean.class, new Class[] { Object.class, PropertyMap.class, MethodHandle.class, int.class, ScriptFunction.class });
  
  private static final ArrayList<MethodHandle> PROTO_FILTERS = new ArrayList<>();
  
  public static final CompilerConstants.Call GET_ARRAY = CompilerConstants.virtualCall(MethodHandles.lookup(), ScriptObject.class, "getArray", ArrayData.class, new Class[0]);
  
  public static final CompilerConstants.Call GET_ARGUMENT = CompilerConstants.virtualCall(MethodHandles.lookup(), ScriptObject.class, "getArgument", Object.class, new Class[] { int.class });
  
  public static final CompilerConstants.Call SET_ARGUMENT = CompilerConstants.virtualCall(MethodHandles.lookup(), ScriptObject.class, "setArgument", void.class, new Class[] { int.class, Object.class });
  
  public static final CompilerConstants.Call GET_PROTO = CompilerConstants.virtualCallNoLookup(ScriptObject.class, "getProto", ScriptObject.class, new Class[0]);
  
  public static final CompilerConstants.Call GET_PROTO_DEPTH = CompilerConstants.virtualCallNoLookup(ScriptObject.class, "getProto", ScriptObject.class, new Class[] { int.class });
  
  public static final CompilerConstants.Call SET_GLOBAL_OBJECT_PROTO = CompilerConstants.staticCallNoLookup(ScriptObject.class, "setGlobalObjectProto", void.class, new Class[] { ScriptObject.class });
  
  public static final CompilerConstants.Call SET_PROTO_FROM_LITERAL = CompilerConstants.virtualCallNoLookup(ScriptObject.class, "setProtoFromLiteral", void.class, new Class[] { Object.class });
  
  public static final CompilerConstants.Call SET_USER_ACCESSORS = CompilerConstants.virtualCallNoLookup(ScriptObject.class, "setUserAccessors", void.class, new Class[] { Object.class, ScriptFunction.class, ScriptFunction.class });
  
  public static final CompilerConstants.Call GENERIC_SET = CompilerConstants.virtualCallNoLookup(ScriptObject.class, "set", void.class, new Class[] { Object.class, Object.class, int.class });
  
  public static final CompilerConstants.Call DELETE = CompilerConstants.virtualCall(MethodHandles.lookup(), ScriptObject.class, "delete", boolean.class, new Class[] { Object.class, boolean.class });
  
  static final MethodHandle[] SET_SLOW = new MethodHandle[] { findOwnMH_V("set", void.class, new Class[] { Object.class, int.class, int.class }), findOwnMH_V("set", void.class, new Class[] { Object.class, double.class, int.class }), findOwnMH_V("set", void.class, new Class[] { Object.class, Object.class, int.class }) };
  
  public static final CompilerConstants.Call SET_MAP = CompilerConstants.virtualCallNoLookup(ScriptObject.class, "setMap", void.class, new Class[] { PropertyMap.class });
  
  static final MethodHandle CAS_MAP = findOwnMH_V("compareAndSetMap", boolean.class, new Class[] { PropertyMap.class, PropertyMap.class });
  
  static final MethodHandle EXTENSION_CHECK = findOwnMH_V("extensionCheck", boolean.class, new Class[] { boolean.class, String.class });
  
  static final MethodHandle ENSURE_SPILL_SIZE = findOwnMH_V("ensureSpillSize", Object.class, new Class[] { int.class });
  
  private static final GuardedInvocation DELETE_GUARDED = new GuardedInvocation(Lookup.MH.insertArguments(DELETE.methodHandle(), 2, new Object[] { Boolean.valueOf(false) }), NashornGuards.getScriptObjectGuard());
  
  private static final GuardedInvocation DELETE_GUARDED_STRICT = new GuardedInvocation(Lookup.MH.insertArguments(DELETE.methodHandle(), 2, new Object[] { Boolean.valueOf(true) }), NashornGuards.getScriptObjectGuard());
  
  private static LongAdder count;
  
  public ScriptObject() {
    this(null);
  }
  
  public ScriptObject(PropertyMap map) {
    if (Context.DEBUG)
      count.increment(); 
    this.arrayData = ArrayData.EMPTY_ARRAY;
    setMap((map == null) ? PropertyMap.newMap() : map);
  }
  
  protected ScriptObject(ScriptObject proto, PropertyMap map) {
    this(map);
    this.proto = proto;
  }
  
  public ScriptObject(PropertyMap map, long[] primitiveSpill, Object[] objectSpill) {
    this(map);
    this.primitiveSpill = primitiveSpill;
    this.objectSpill = objectSpill;
    assert primitiveSpill == null || primitiveSpill.length == objectSpill.length : " primitive spill pool size is not the same length as object spill pool size";
  }
  
  protected boolean isGlobal() {
    return false;
  }
  
  private static int alignUp(int size, int alignment) {
    return size + alignment - 1 & -alignment;
  }
  
  public static int spillAllocationLength(int nProperties) {
    return alignUp(nProperties, 8);
  }
  
  public void addBoundProperties(ScriptObject source) {
    addBoundProperties(source, source.getMap().getProperties());
  }
  
  public void addBoundProperties(ScriptObject source, Property[] properties) {
    PropertyMap newMap = getMap();
    boolean extensible = newMap.isExtensible();
    for (Property property : properties)
      newMap = addBoundProperty(newMap, source, property, extensible); 
    setMap(newMap);
  }
  
  protected PropertyMap addBoundProperty(PropertyMap propMap, ScriptObject source, Property property, boolean extensible) {
    PropertyMap newMap = propMap;
    Object key = property.getKey();
    Property oldProp = newMap.findProperty(key);
    if (oldProp == null) {
      if (!extensible)
        throw ECMAErrors.typeError("object.non.extensible", new String[] { key.toString(), ScriptRuntime.safeToString(this) }); 
      if (property instanceof UserAccessorProperty) {
        UserAccessorProperty prop = newUserAccessors(key, property.getFlags(), property.getGetterFunction(source), property.getSetterFunction(source));
        newMap = newMap.addPropertyNoHistory(prop);
      } else {
        newMap = newMap.addPropertyBind((AccessorProperty)property, source);
      } 
    } else if (property.isFunctionDeclaration() && !oldProp.isConfigurable() && (
      oldProp instanceof UserAccessorProperty || 
      !oldProp.isWritable() || !oldProp.isEnumerable())) {
      throw ECMAErrors.typeError("cant.redefine.property", new String[] { key.toString(), ScriptRuntime.safeToString(this) });
    } 
    return newMap;
  }
  
  public void addBoundProperties(Object source, AccessorProperty[] properties) {
    PropertyMap newMap = getMap();
    boolean extensible = newMap.isExtensible();
    for (AccessorProperty property : properties) {
      Object key = property.getKey();
      if (newMap.findProperty(key) == null) {
        if (!extensible)
          throw ECMAErrors.typeError("object.non.extensible", new String[] { key.toString(), ScriptRuntime.safeToString(this) }); 
        newMap = newMap.addPropertyBind(property, source);
      } 
    } 
    setMap(newMap);
  }
  
  static MethodHandle bindTo(MethodHandle methodHandle, Object receiver) {
    return Lookup.MH.dropArguments(Lookup.MH.bindTo(methodHandle, receiver), 0, new Class[] { methodHandle.type().parameterType(0) });
  }
  
  public Iterator<String> propertyIterator() {
    return new KeyIterator(this);
  }
  
  public Iterator<Object> valueIterator() {
    return new ValueIterator(this);
  }
  
  public final boolean isAccessorDescriptor() {
    return (has("get") || has("set"));
  }
  
  public final boolean isDataDescriptor() {
    return (has("value") || has("writable"));
  }
  
  public final PropertyDescriptor toPropertyDescriptor() {
    PropertyDescriptor desc;
    Global global = Context.getGlobal();
    if (isDataDescriptor()) {
      if (has("set") || has("get"))
        throw ECMAErrors.typeError(global, "inconsistent.property.descriptor", new String[0]); 
      desc = global.newDataDescriptor(ScriptRuntime.UNDEFINED, false, false, false);
    } else if (isAccessorDescriptor()) {
      if (has("value") || has("writable"))
        throw ECMAErrors.typeError(global, "inconsistent.property.descriptor", new String[0]); 
      desc = global.newAccessorDescriptor(ScriptRuntime.UNDEFINED, ScriptRuntime.UNDEFINED, false, false);
    } else {
      desc = global.newGenericDescriptor(false, false);
    } 
    return desc.fillFrom(this);
  }
  
  public static PropertyDescriptor toPropertyDescriptor(Global global, Object obj) {
    if (obj instanceof ScriptObject)
      return ((ScriptObject)obj).toPropertyDescriptor(); 
    throw ECMAErrors.typeError(global, "not.an.object", new String[] { ScriptRuntime.safeToString(obj) });
  }
  
  public Object getOwnPropertyDescriptor(Object key) {
    Property property = getMap().findProperty(key);
    Global global = Context.getGlobal();
    if (property != null) {
      ScriptFunction get = property.getGetterFunction(this);
      ScriptFunction set = property.getSetterFunction(this);
      boolean configurable = property.isConfigurable();
      boolean enumerable = property.isEnumerable();
      boolean writable = property.isWritable();
      if (property.isAccessorProperty())
        return global.newAccessorDescriptor(
            (get != null) ? 
            get : 
            ScriptRuntime.UNDEFINED, 
            (set != null) ? 
            set : 
            ScriptRuntime.UNDEFINED, configurable, enumerable); 
      return global.newDataDescriptor(getWithProperty(property), configurable, enumerable, writable);
    } 
    int index = ArrayIndex.getArrayIndex(key);
    ArrayData array = getArray();
    if (array.has(index))
      return array.getDescriptor(global, index); 
    return ScriptRuntime.UNDEFINED;
  }
  
  public Object getPropertyDescriptor(String key) {
    Object res = getOwnPropertyDescriptor(key);
    if (res != ScriptRuntime.UNDEFINED)
      return res; 
    if (getProto() != null)
      return getProto().getOwnPropertyDescriptor(key); 
    return ScriptRuntime.UNDEFINED;
  }
  
  protected void invalidateGlobalConstant(Object key) {
    GlobalConstants globalConstants = getGlobalConstants();
    if (globalConstants != null)
      globalConstants.delete(key); 
  }
  
  public boolean defineOwnProperty(Object key, Object propertyDesc, boolean reject) {
    Global global = Context.getGlobal();
    PropertyDescriptor desc = toPropertyDescriptor(global, propertyDesc);
    Object current = getOwnPropertyDescriptor(key);
    invalidateGlobalConstant(key);
    if (current == ScriptRuntime.UNDEFINED) {
      if (isExtensible()) {
        addOwnProperty(key, desc);
        return true;
      } 
      if (reject)
        throw ECMAErrors.typeError(global, "object.non.extensible", new String[] { key.toString(), ScriptRuntime.safeToString(this) }); 
      return false;
    } 
    PropertyDescriptor currentDesc = (PropertyDescriptor)current;
    if (desc.type() == 0 && !desc.has("configurable") && !desc.has("enumerable"))
      return true; 
    if (desc.hasAndEquals(currentDesc))
      return true; 
    if (!currentDesc.isConfigurable()) {
      if (desc.has("configurable") && desc.isConfigurable()) {
        if (reject)
          throw ECMAErrors.typeError(global, "cant.redefine.property", new String[] { key.toString(), ScriptRuntime.safeToString(this) }); 
        return false;
      } 
      if (desc.has("enumerable") && currentDesc
        .isEnumerable() != desc.isEnumerable()) {
        if (reject)
          throw ECMAErrors.typeError(global, "cant.redefine.property", new String[] { key.toString(), ScriptRuntime.safeToString(this) }); 
        return false;
      } 
    } 
    int propFlags = Property.mergeFlags(currentDesc, desc);
    Property property = getMap().findProperty(key);
    if (currentDesc.type() == 1 && (desc
      .type() == 1 || desc
      .type() == 0)) {
      if (!currentDesc.isConfigurable() && !currentDesc.isWritable() && ((
        desc.has("writable") && desc.isWritable()) || (desc
        .has("value") && !ScriptRuntime.sameValue(currentDesc.getValue(), desc.getValue())))) {
        if (reject)
          throw ECMAErrors.typeError(global, "cant.redefine.property", new String[] { key.toString(), ScriptRuntime.safeToString(this) }); 
        return false;
      } 
      boolean newValue = desc.has("value");
      Object value = newValue ? desc.getValue() : currentDesc.getValue();
      if (newValue && property != null) {
        modifyOwnProperty(property, 0);
        set(key, value, 0);
        property = getMap().findProperty(key);
      } 
      if (property == null) {
        addOwnProperty(key, propFlags, value);
        checkIntegerKey(key);
      } else {
        modifyOwnProperty(property, propFlags);
      } 
    } else if (currentDesc.type() == 2 && (desc
      .type() == 2 || desc
      .type() == 0)) {
      if (!currentDesc.isConfigurable() && ((
        desc.has("get") && !ScriptRuntime.sameValue(currentDesc.getGetter(), desc
          .getGetter())) || (desc
        .has("set") && !ScriptRuntime.sameValue(currentDesc.getSetter(), desc
          .getSetter())))) {
        if (reject)
          throw ECMAErrors.typeError(global, "cant.redefine.property", new String[] { key.toString(), ScriptRuntime.safeToString(this) }); 
        return false;
      } 
      modifyOwnProperty(property, propFlags, 
          desc.has("get") ? desc.getGetter() : currentDesc.getGetter(), 
          desc.has("set") ? desc.getSetter() : currentDesc.getSetter());
    } else {
      if (!currentDesc.isConfigurable()) {
        if (reject)
          throw ECMAErrors.typeError(global, "cant.redefine.property", new String[] { key.toString(), ScriptRuntime.safeToString(this) }); 
        return false;
      } 
      propFlags = 0;
      boolean value = desc.has("configurable") ? desc.isConfigurable() : currentDesc.isConfigurable();
      if (!value)
        propFlags |= 0x4; 
      value = desc.has("enumerable") ? desc.isEnumerable() : currentDesc.isEnumerable();
      if (!value)
        propFlags |= 0x2; 
      int type = desc.type();
      if (type == 1) {
        value = (desc.has("writable") && desc.isWritable());
        if (!value)
          propFlags |= 0x1; 
        deleteOwnProperty(property);
        addOwnProperty(key, propFlags, desc.getValue());
      } else if (type == 2) {
        if (property == null) {
          addOwnProperty(key, propFlags, 
              desc.has("get") ? desc.getGetter() : null, 
              desc.has("set") ? desc.getSetter() : null);
        } else {
          modifyOwnProperty(property, propFlags, 
              desc.has("get") ? desc.getGetter() : null, 
              desc.has("set") ? desc.getSetter() : null);
        } 
      } 
    } 
    checkIntegerKey(key);
    return true;
  }
  
  public void defineOwnProperty(int index, Object value) {
    assert ArrayIndex.isValidArrayIndex(index) : "invalid array index";
    long longIndex = ArrayIndex.toLongIndex(index);
    long oldLength = getArray().length();
    if (longIndex >= oldLength)
      setArray(getArray().ensure(longIndex).safeDelete(oldLength, longIndex - 1L, false)); 
    setArray(getArray().set(index, value, false));
  }
  
  private void checkIntegerKey(Object key) {
    int index = ArrayIndex.getArrayIndex(key);
    if (ArrayIndex.isValidArrayIndex(index)) {
      ArrayData data = getArray();
      if (data.has(index))
        setArray(data.delete(index)); 
    } 
  }
  
  public final void addOwnProperty(Object key, PropertyDescriptor propertyDesc) {
    PropertyDescriptor pdesc = propertyDesc;
    int propFlags = Property.toFlags(pdesc);
    if (pdesc.type() == 0) {
      Global global = Context.getGlobal();
      PropertyDescriptor dDesc = global.newDataDescriptor(ScriptRuntime.UNDEFINED, false, false, false);
      dDesc.fillFrom((ScriptObject)pdesc);
      pdesc = dDesc;
    } 
    int type = pdesc.type();
    if (type == 1) {
      addOwnProperty(key, propFlags, pdesc.getValue());
    } else if (type == 2) {
      addOwnProperty(key, propFlags, 
          pdesc.has("get") ? pdesc.getGetter() : null, 
          pdesc.has("set") ? pdesc.getSetter() : null);
    } 
    checkIntegerKey(key);
  }
  
  public final FindProperty findProperty(Object key, boolean deep) {
    return findProperty(key, deep, false, this);
  }
  
  protected FindProperty findProperty(Object key, boolean deep, boolean isScope, ScriptObject start) {
    PropertyMap selfMap = getMap();
    Property property = selfMap.findProperty(key);
    if (property != null)
      return new FindProperty(start, this, property); 
    if (deep) {
      ScriptObject myProto = getProto();
      FindProperty find = (myProto == null) ? null : myProto.findProperty(key, true, isScope, start);
      checkSharedProtoMap();
      return find;
    } 
    return null;
  }
  
  boolean hasProperty(Object key, boolean deep) {
    if (getMap().findProperty(key) != null)
      return true; 
    if (deep) {
      ScriptObject myProto = getProto();
      if (myProto != null)
        return myProto.hasProperty(key, true); 
    } 
    return false;
  }
  
  private SwitchPoint findBuiltinSwitchPoint(Object key) {
    for (ScriptObject myProto = getProto(); myProto != null; myProto = myProto.getProto()) {
      Property prop = myProto.getMap().findProperty(key);
      if (prop != null) {
        SwitchPoint sp = prop.getBuiltinSwitchPoint();
        if (sp != null && !sp.hasBeenInvalidated())
          return sp; 
      } 
    } 
    return null;
  }
  
  public final Property addOwnProperty(Object key, int propertyFlags, ScriptFunction getter, ScriptFunction setter) {
    return addOwnProperty(newUserAccessors(key, propertyFlags, getter, setter));
  }
  
  public final Property addOwnProperty(Object key, int propertyFlags, Object value) {
    return addSpillProperty(key, propertyFlags, value, true);
  }
  
  public final Property addOwnProperty(Property newProperty) {
    PropertyMap oldMap = getMap();
    while (true) {
      PropertyMap newMap = oldMap.addProperty(newProperty);
      if (!compareAndSetMap(oldMap, newMap)) {
        oldMap = getMap();
        Property oldProperty = oldMap.findProperty(newProperty.getKey());
        if (oldProperty != null)
          return oldProperty; 
        continue;
      } 
      break;
    } 
    return newProperty;
  }
  
  private void erasePropertyValue(Property property) {
    if (property != null && !property.isAccessorProperty())
      property.setValue(this, this, ScriptRuntime.UNDEFINED, false); 
  }
  
  public final boolean deleteOwnProperty(Property property) {
    erasePropertyValue(property);
    PropertyMap oldMap = getMap();
    while (true) {
      PropertyMap newMap = oldMap.deleteProperty(property);
      if (newMap == null)
        return false; 
      if (!compareAndSetMap(oldMap, newMap)) {
        oldMap = getMap();
        continue;
      } 
      break;
    } 
    if (property instanceof UserAccessorProperty)
      ((UserAccessorProperty)property).setAccessors(this, getMap(), (UserAccessorProperty.Accessors)null); 
    invalidateGlobalConstant(property.getKey());
    return true;
  }
  
  protected final void initUserAccessors(String key, ScriptFunction getter, ScriptFunction setter) {
    PropertyMap map = getMap();
    Property property = map.findProperty(key);
    assert property instanceof UserAccessorProperty;
    ensureSpillSize(property.getSlot());
    this.objectSpill[property.getSlot()] = new UserAccessorProperty.Accessors(getter, setter);
  }
  
  public final Property modifyOwnProperty(Property oldProperty, int propertyFlags, ScriptFunction getter, ScriptFunction setter) {
    Property newProperty;
    if (oldProperty instanceof UserAccessorProperty) {
      UserAccessorProperty uc = (UserAccessorProperty)oldProperty;
      int slot = uc.getSlot();
      assert uc.getLocalType() == Object.class;
      UserAccessorProperty.Accessors gs = uc.getAccessors(this);
      assert gs != null;
      gs.set(getter, setter);
      if (uc.getFlags() == (propertyFlags | 0x1000))
        return oldProperty; 
      newProperty = new UserAccessorProperty(uc.getKey(), propertyFlags, slot);
    } else {
      erasePropertyValue(oldProperty);
      newProperty = newUserAccessors(oldProperty.getKey(), propertyFlags, getter, setter);
    } 
    return modifyOwnProperty(oldProperty, newProperty);
  }
  
  public final Property modifyOwnProperty(Property oldProperty, int propertyFlags) {
    return modifyOwnProperty(oldProperty, oldProperty.setFlags(propertyFlags));
  }
  
  private Property modifyOwnProperty(Property oldProperty, Property newProperty) {
    if (oldProperty == newProperty)
      return newProperty; 
    assert newProperty.getKey().equals(oldProperty.getKey()) : "replacing property with different key";
    PropertyMap oldMap = getMap();
    while (true) {
      PropertyMap newMap = oldMap.replaceProperty(oldProperty, newProperty);
      if (!compareAndSetMap(oldMap, newMap)) {
        oldMap = getMap();
        Property oldPropertyLookup = oldMap.findProperty(oldProperty.getKey());
        if (oldPropertyLookup != null && oldPropertyLookup.equals(newProperty))
          return oldPropertyLookup; 
        continue;
      } 
      break;
    } 
    return newProperty;
  }
  
  public final void setUserAccessors(Object key, ScriptFunction getter, ScriptFunction setter) {
    Object realKey = JSType.toPropertyKey(key);
    Property oldProperty = getMap().findProperty(realKey);
    if (oldProperty instanceof UserAccessorProperty) {
      modifyOwnProperty(oldProperty, oldProperty.getFlags(), getter, setter);
    } else {
      addOwnProperty(newUserAccessors(realKey, (oldProperty != null) ? oldProperty.getFlags() : 0, getter, setter));
    } 
  }
  
  private static int getIntValue(FindProperty find, int programPoint) {
    MethodHandle getter = find.getGetter(int.class, programPoint, null);
    if (getter != null)
      try {
        return getter.invokeExact(find.getGetterReceiver());
      } catch (Error|RuntimeException e) {
        throw e;
      } catch (Throwable e) {
        throw new RuntimeException(e);
      }  
    return 0;
  }
  
  private static double getDoubleValue(FindProperty find, int programPoint) {
    MethodHandle getter = find.getGetter(double.class, programPoint, null);
    if (getter != null)
      try {
        return getter.invokeExact(find.getGetterReceiver());
      } catch (Error|RuntimeException e) {
        throw e;
      } catch (Throwable e) {
        throw new RuntimeException(e);
      }  
    return Double.NaN;
  }
  
  protected static MethodHandle getCallMethodHandle(FindProperty find, MethodType type, String bindName) {
    return getCallMethodHandle(find.getObjectValue(), type, bindName);
  }
  
  private static MethodHandle getCallMethodHandle(Object value, MethodType type, String bindName) {
    return (value instanceof ScriptFunction) ? ((ScriptFunction)value).getCallMethodHandle(type, bindName) : null;
  }
  
  public final Object getWithProperty(Property property) {
    return (new FindProperty(this, this, property)).getObjectValue();
  }
  
  public final Property getProperty(String key) {
    return getMap().findProperty(key);
  }
  
  public Object getArgument(int key) {
    return get(key);
  }
  
  public void setArgument(int key, Object value) {
    set(key, value, 0);
  }
  
  protected Context getContext() {
    return Context.fromClass(getClass());
  }
  
  public final PropertyMap getMap() {
    return this.map;
  }
  
  public final void setMap(PropertyMap map) {
    this.map = map;
  }
  
  protected final boolean compareAndSetMap(PropertyMap oldMap, PropertyMap newMap) {
    if (oldMap == this.map) {
      this.map = newMap;
      return true;
    } 
    return false;
  }
  
  public final ScriptObject getProto() {
    return this.proto;
  }
  
  public final ScriptObject getProto(int n) {
    ScriptObject p = this;
    for (int i = n; i > 0; i--)
      p = p.getProto(); 
    return p;
  }
  
  public final void setProto(ScriptObject newProto) {
    ScriptObject oldProto = this.proto;
    if (oldProto != newProto) {
      this.proto = newProto;
      getMap().protoChanged();
      setMap(getMap().changeProto(newProto));
    } 
  }
  
  public void setInitialProto(ScriptObject initialProto) {
    this.proto = initialProto;
  }
  
  public static void setGlobalObjectProto(ScriptObject obj) {
    obj.setInitialProto(Global.objectPrototype());
  }
  
  public final void setPrototypeOf(Object newProto) {
    if (newProto == null || newProto instanceof ScriptObject) {
      if (!isExtensible()) {
        if (newProto == getProto())
          return; 
        throw ECMAErrors.typeError("__proto__.set.non.extensible", new String[] { ScriptRuntime.safeToString(this) });
      } 
      ScriptObject p = (ScriptObject)newProto;
      while (p != null) {
        if (p == this)
          throw ECMAErrors.typeError("circular.__proto__.set", new String[] { ScriptRuntime.safeToString(this) }); 
        p = p.getProto();
      } 
      setProto((ScriptObject)newProto);
    } else {
      throw ECMAErrors.typeError("cant.set.proto.to.non.object", new String[] { ScriptRuntime.safeToString(this), ScriptRuntime.safeToString(newProto) });
    } 
  }
  
  public final void setProtoFromLiteral(Object newProto) {
    if (newProto == null || newProto instanceof ScriptObject) {
      setPrototypeOf(newProto);
    } else {
      setPrototypeOf(Global.objectPrototype());
    } 
  }
  
  public String[] getAllKeys() {
    Set<String> keys = new HashSet<>();
    Set<String> nonEnumerable = new HashSet<>();
    for (ScriptObject self = this; self != null; self = self.getProto())
      keys.addAll(Arrays.asList(self.<String>getOwnKeys(String.class, true, nonEnumerable))); 
    return keys.<String>toArray(new String[0]);
  }
  
  public final String[] getOwnKeys(boolean all) {
    return getOwnKeys(String.class, all, null);
  }
  
  public final Symbol[] getOwnSymbols(boolean all) {
    return getOwnKeys(Symbol.class, all, null);
  }
  
  protected <T> T[] getOwnKeys(Class<T> type, boolean all, Set<T> nonEnumerable) {
    List<Object> keys = new ArrayList();
    PropertyMap selfMap = getMap();
    ArrayData array = getArray();
    if (type == String.class)
      for (Iterator<Long> iter = array.indexIterator(); iter.hasNext();)
        keys.add(JSType.toString(((Long)iter.next()).longValue()));  
    for (Property property : selfMap.getProperties()) {
      boolean enumerable = property.isEnumerable();
      Object key = property.getKey();
      if (type.isInstance(key))
        if (all) {
          keys.add(key);
        } else if (enumerable) {
          if (nonEnumerable == null || !nonEnumerable.contains(key))
            keys.add(key); 
        } else if (nonEnumerable != null) {
          nonEnumerable.add((T)key);
        }  
    } 
    return keys.toArray((T[])Array.newInstance(type, keys.size()));
  }
  
  public boolean hasArrayEntries() {
    return (getArray().length() > 0L || getMap().containsArrayKeys());
  }
  
  public String getClassName() {
    return "Object";
  }
  
  public Object getLength() {
    return get("length");
  }
  
  public String safeToString() {
    return "[object " + getClassName() + "]";
  }
  
  public Object getDefaultValue(Class<?> typeHint) {
    return Context.getGlobal().getDefaultValue(this, typeHint);
  }
  
  public boolean isInstance(ScriptObject instance) {
    return false;
  }
  
  public ScriptObject preventExtensions() {
    PropertyMap oldMap = getMap();
    while (!compareAndSetMap(oldMap, getMap().preventExtensions()))
      oldMap = getMap(); 
    ArrayData array = getArray();
    assert array != null;
    setArray(ArrayData.preventExtension(array));
    return this;
  }
  
  public static boolean isArray(Object obj) {
    return (obj instanceof ScriptObject && ((ScriptObject)obj).isArray());
  }
  
  public final boolean isArray() {
    return ((this.flags & 0x1) != 0);
  }
  
  public final void setIsArray() {
    this.flags |= 0x1;
  }
  
  public final boolean isArguments() {
    return ((this.flags & 0x2) != 0);
  }
  
  public final void setIsArguments() {
    this.flags |= 0x2;
  }
  
  public boolean isLengthNotWritable() {
    return ((this.flags & 0x4) != 0);
  }
  
  public void setIsLengthNotWritable() {
    this.flags |= 0x4;
  }
  
  public final ArrayData getArray(Class<?> elementType) {
    if (elementType == null)
      return this.arrayData; 
    ArrayData newArrayData = this.arrayData.convert(elementType);
    if (newArrayData != this.arrayData)
      this.arrayData = newArrayData; 
    return newArrayData;
  }
  
  public final ArrayData getArray() {
    return this.arrayData;
  }
  
  public final void setArray(ArrayData arrayData) {
    this.arrayData = arrayData;
  }
  
  public boolean isExtensible() {
    return getMap().isExtensible();
  }
  
  public ScriptObject seal() {
    PropertyMap oldMap = getMap();
    while (true) {
      PropertyMap newMap = getMap().seal();
      if (!compareAndSetMap(oldMap, newMap)) {
        oldMap = getMap();
        continue;
      } 
      break;
    } 
    setArray(ArrayData.seal(getArray()));
    return this;
  }
  
  public boolean isSealed() {
    return getMap().isSealed();
  }
  
  public ScriptObject freeze() {
    PropertyMap oldMap = getMap();
    while (true) {
      PropertyMap newMap = getMap().freeze();
      if (!compareAndSetMap(oldMap, newMap)) {
        oldMap = getMap();
        continue;
      } 
      break;
    } 
    setArray(ArrayData.freeze(getArray()));
    return this;
  }
  
  public boolean isFrozen() {
    return getMap().isFrozen();
  }
  
  public boolean isScope() {
    return false;
  }
  
  public final void setIsBuiltin() {
    this.flags |= 0x8;
  }
  
  public final boolean isBuiltin() {
    return ((this.flags & 0x8) != 0);
  }
  
  public final void setIsInternal() {
    this.flags |= 0x10;
  }
  
  public final boolean isInternal() {
    return ((this.flags & 0x10) != 0);
  }
  
  public void clear(boolean strict) {
    Iterator<String> iter = propertyIterator();
    while (iter.hasNext())
      delete(iter.next(), strict); 
  }
  
  public boolean containsKey(Object key) {
    return has(key);
  }
  
  public boolean containsValue(Object value) {
    Iterator<Object> iter = valueIterator();
    while (iter.hasNext()) {
      if (iter.next().equals(value))
        return true; 
    } 
    return false;
  }
  
  public Set<Map.Entry<Object, Object>> entrySet() {
    Iterator<String> iter = propertyIterator();
    Set<Map.Entry<Object, Object>> entries = new HashSet<>();
    while (iter.hasNext()) {
      Object key = iter.next();
      entries.add(new AbstractMap.SimpleImmutableEntry<>(key, get(key)));
    } 
    return Collections.unmodifiableSet(entries);
  }
  
  public boolean isEmpty() {
    return !propertyIterator().hasNext();
  }
  
  public Set<Object> keySet() {
    Iterator<String> iter = propertyIterator();
    Set<Object> keySet = new HashSet();
    while (iter.hasNext())
      keySet.add(iter.next()); 
    return Collections.unmodifiableSet(keySet);
  }
  
  public Object put(Object key, Object value, boolean strict) {
    Object oldValue = get(key);
    int scriptObjectFlags = strict ? 32 : 0;
    set(key, value, scriptObjectFlags);
    return oldValue;
  }
  
  public void putAll(Map<?, ?> otherMap, boolean strict) {
    int scriptObjectFlags = strict ? 32 : 0;
    for (Map.Entry<?, ?> entry : otherMap.entrySet())
      set(entry.getKey(), entry.getValue(), scriptObjectFlags); 
  }
  
  public Object remove(Object key, boolean strict) {
    Object oldValue = get(key);
    delete(key, strict);
    return oldValue;
  }
  
  public int size() {
    int n = 0;
    for (Iterator<String> iter = propertyIterator(); iter.hasNext(); iter.next())
      n++; 
    return n;
  }
  
  public Collection<Object> values() {
    List<Object> values = new ArrayList(size());
    Iterator<Object> iter = valueIterator();
    while (iter.hasNext())
      values.add(iter.next()); 
    return Collections.unmodifiableList(values);
  }
  
  public GuardedInvocation lookup(CallSiteDescriptor desc, LinkRequest request) {
    GuardedInvocation inv;
    Object name;
    switch (NashornCallSiteDescriptor.getStandardOperation(desc)) {
      case GET:
        return (desc.getOperation() instanceof NamedOperation) ? 
          findGetMethod(desc, request) : 
          findGetIndexMethod(desc, request);
      case SET:
        return (desc.getOperation() instanceof NamedOperation) ? 
          findSetMethod(desc, request) : 
          findSetIndexMethod(desc, request);
      case REMOVE:
        inv = NashornCallSiteDescriptor.isStrict(desc) ? DELETE_GUARDED_STRICT : DELETE_GUARDED;
        name = NamedOperation.getName(desc.getOperation());
        if (name != null)
          return inv.replaceMethods(Lookup.MH.insertArguments(inv.getInvocation(), 1, new Object[] { name }), inv.getGuard()); 
        return inv;
      case CALL:
        return findCallMethod(desc, request);
      case NEW:
        return findNewMethod(desc, request);
    } 
    return null;
  }
  
  protected GuardedInvocation findNewMethod(CallSiteDescriptor desc, LinkRequest request) {
    return notAFunction(desc);
  }
  
  protected GuardedInvocation findCallMethod(CallSiteDescriptor desc, LinkRequest request) {
    return notAFunction(desc);
  }
  
  private GuardedInvocation notAFunction(CallSiteDescriptor desc) {
    throw ECMAErrors.typeError("not.a.function", new String[] { NashornCallSiteDescriptor.getFunctionErrorMessage(desc, this) });
  }
  
  boolean hasWithScope() {
    return false;
  }
  
  static MethodHandle addProtoFilter(MethodHandle methodHandle, int depth) {
    if (depth == 0)
      return methodHandle; 
    int listIndex = depth - 1;
    MethodHandle filter = (listIndex < PROTO_FILTERS.size()) ? PROTO_FILTERS.get(listIndex) : null;
    if (filter == null) {
      filter = addProtoFilter(GETPROTO, depth - 1);
      PROTO_FILTERS.add(null);
      PROTO_FILTERS.set(listIndex, filter);
    } 
    return Lookup.MH.filterArguments(methodHandle, 0, new MethodHandle[] { filter.asType(filter.type().changeReturnType(methodHandle.type().parameterType(0))) });
  }
  
  protected GuardedInvocation findGetMethod(CallSiteDescriptor desc, LinkRequest request) {
    SwitchPoint[] protoSwitchPoints;
    boolean explicitInstanceOfCheck = NashornGuards.explicitInstanceOfCheck(desc, request);
    String name = NashornCallSiteDescriptor.getOperand(desc);
    if (NashornCallSiteDescriptor.isApplyToCall(desc) && 
      Global.isBuiltinFunctionPrototypeApply())
      name = "call"; 
    if (request.isCallSiteUnstable() || hasWithScope())
      return findMegaMorphicGetMethod(desc, name, NashornCallSiteDescriptor.isMethodFirstOperation(desc)); 
    FindProperty find = findProperty(name, true, NashornCallSiteDescriptor.isScope(desc), this);
    if (find == null) {
      if (!NashornCallSiteDescriptor.isMethodFirstOperation(desc))
        return noSuchProperty(desc, request); 
      return noSuchMethod(desc, request);
    } 
    GlobalConstants globalConstants = getGlobalConstants();
    if (globalConstants != null) {
      GuardedInvocation cinv = globalConstants.findGetMethod(find, this, desc);
      if (cinv != null)
        return cinv; 
    } 
    Class<?> returnType = desc.getMethodType().returnType();
    Property property = find.getProperty();
    int programPoint = NashornCallSiteDescriptor.isOptimistic(desc) ? NashornCallSiteDescriptor.getProgramPoint(desc) : -1;
    MethodHandle mh = find.getGetter(returnType, programPoint, request);
    MethodHandle guard = NashornGuards.getGuard(this, property, desc, explicitInstanceOfCheck);
    ScriptObject owner = find.getOwner();
    Class<ClassCastException> exception = explicitInstanceOfCheck ? null : ClassCastException.class;
    if (mh == null) {
      mh = Lookup.emptyGetter(returnType);
      protoSwitchPoints = getProtoSwitchPoints(name, owner);
    } else if (!find.isSelf()) {
      assert mh.type().returnType().equals(returnType) : "return type mismatch for getter " + mh
        .type().returnType() + " != " + returnType;
      if (!property.isAccessorProperty())
        mh = addProtoFilter(mh, find.getProtoChainLength()); 
      protoSwitchPoints = getProtoSwitchPoints(name, owner);
    } else {
      protoSwitchPoints = null;
    } 
    GuardedInvocation inv = new GuardedInvocation(mh, guard, protoSwitchPoints, (Class)exception);
    return inv.addSwitchPoint(findBuiltinSwitchPoint(name));
  }
  
  private static GuardedInvocation findMegaMorphicGetMethod(CallSiteDescriptor desc, String name, boolean isMethod) {
    Context.getContextTrusted().getLogger((Class)ObjectClassGenerator.class).warning(new Object[] { "Megamorphic getter: ", desc, " ", name + " ", Boolean.valueOf(isMethod) });
    MethodHandle invoker = Lookup.MH.insertArguments(MEGAMORPHIC_GET, 1, new Object[] { name, Boolean.valueOf(isMethod), Boolean.valueOf(NashornCallSiteDescriptor.isScope(desc)) });
    MethodHandle guard = getScriptObjectGuard(desc.getMethodType(), true);
    return new GuardedInvocation(invoker, guard);
  }
  
  private Object megamorphicGet(String key, boolean isMethod, boolean isScope) {
    FindProperty find = findProperty(key, true, isScope, this);
    if (find != null) {
      Object value = find.getObjectValue();
      if (isMethod && value instanceof ScriptFunction && find.getSelf() != this && !find.getSelf().isInternal())
        return ((ScriptFunction)value).createBound(find.getSelf(), ScriptRuntime.EMPTY_ARRAY); 
      return value;
    } 
    return isMethod ? getNoSuchMethod(key, isScope, -1) : invokeNoSuchProperty(key, isScope, -1);
  }
  
  private void declareAndSet(String key, Object value) {
    declareAndSet(findProperty(key, false), value);
  }
  
  private void declareAndSet(FindProperty find, Object value) {
    PropertyMap oldMap = getMap();
    assert find != null;
    Property property = find.getProperty();
    assert property != null;
    assert property.needsDeclaration();
    PropertyMap newMap = oldMap.replaceProperty(property, property.removeFlags(512));
    setMap(newMap);
    set(property.getKey(), value, 512);
  }
  
  protected GuardedInvocation findGetIndexMethod(CallSiteDescriptor desc, LinkRequest request) {
    String name;
    MethodType callType = desc.getMethodType();
    Class<?> returnType = callType.returnType();
    Class<?> returnClass = returnType.isPrimitive() ? returnType : Object.class;
    Class<?> keyClass = callType.parameterType(1);
    boolean explicitInstanceOfCheck = NashornGuards.explicitInstanceOfCheck(desc, request);
    if (returnClass.isPrimitive()) {
      String returnTypeName = returnClass.getName();
      name = "get" + Character.toUpperCase(returnTypeName.charAt(0)) + returnTypeName.substring(1);
    } else {
      name = "get";
    } 
    MethodHandle mh = findGetIndexMethodHandle(returnClass, name, keyClass, desc);
    return new GuardedInvocation(mh, getScriptObjectGuard(callType, explicitInstanceOfCheck), (SwitchPoint)null, explicitInstanceOfCheck ? null : (Class)ClassCastException.class);
  }
  
  private static MethodHandle getScriptObjectGuard(MethodType type, boolean explicitInstanceOfCheck) {
    return ScriptObject.class.isAssignableFrom(type.parameterType(0)) ? null : NashornGuards.getScriptObjectGuard(explicitInstanceOfCheck);
  }
  
  private static MethodHandle findGetIndexMethodHandle(Class<?> returnType, String name, Class<?> elementType, CallSiteDescriptor desc) {
    if (!returnType.isPrimitive())
      return findOwnMH_V(name, returnType, new Class[] { elementType }); 
    return Lookup.MH.insertArguments(
        findOwnMH_V(name, returnType, new Class[] { elementType, int.class }), 2, new Object[] { Integer.valueOf(NashornCallSiteDescriptor.isOptimistic(desc) ? 
            NashornCallSiteDescriptor.getProgramPoint(desc) : 
            -1) });
  }
  
  public final SwitchPoint[] getProtoSwitchPoints(String name, ScriptObject owner) {
    if (owner == this || getProto() == null)
      return null; 
    Set<SwitchPoint> switchPoints = new HashSet<>();
    SwitchPoint switchPoint = getProto().getMap().getSwitchPoint(name);
    if (switchPoint == null) {
      switchPoint = new SwitchPoint();
      for (ScriptObject scriptObject = this; scriptObject != owner && scriptObject.getProto() != null; scriptObject = scriptObject.getProto())
        scriptObject.getProto().getMap().addSwitchPoint(name, switchPoint); 
    } 
    switchPoints.add(switchPoint);
    for (ScriptObject obj = this; obj != owner && obj.getProto() != null; obj = obj.getProto()) {
      SwitchPoint sharedProtoSwitchPoint = obj.getProto().getMap().getSharedProtoSwitchPoint();
      if (sharedProtoSwitchPoint != null && !sharedProtoSwitchPoint.hasBeenInvalidated())
        switchPoints.add(sharedProtoSwitchPoint); 
    } 
    return switchPoints.<SwitchPoint>toArray(new SwitchPoint[0]);
  }
  
  final SwitchPoint getProtoSwitchPoint(String name) {
    if (getProto() == null)
      return null; 
    SwitchPoint switchPoint = getProto().getMap().getSwitchPoint(name);
    if (switchPoint == null) {
      switchPoint = new SwitchPoint();
      for (ScriptObject obj = this; obj.getProto() != null; obj = obj.getProto())
        obj.getProto().getMap().addSwitchPoint(name, switchPoint); 
    } 
    return switchPoint;
  }
  
  private void checkSharedProtoMap() {
    if (getMap().isInvalidSharedMapFor(getProto()))
      setMap(getMap().makeUnsharedCopy()); 
  }
  
  protected GuardedInvocation findSetMethod(CallSiteDescriptor desc, LinkRequest request) {
    String name = NashornCallSiteDescriptor.getOperand(desc);
    if (request.isCallSiteUnstable() || hasWithScope())
      return findMegaMorphicSetMethod(desc, name); 
    boolean explicitInstanceOfCheck = NashornGuards.explicitInstanceOfCheck(desc, request);
    FindProperty find = findProperty(name, true, NashornCallSiteDescriptor.isScope(desc), this);
    if (find != null && find.isInheritedOrdinaryProperty()) {
      if (isExtensible() && !find.getProperty().isWritable())
        return createEmptySetMethod(desc, explicitInstanceOfCheck, "property.not.writable", true); 
      if (!NashornCallSiteDescriptor.isScope(desc) || !find.getOwner().isScope())
        find = null; 
    } 
    if (find != null) {
      if (!find.getProperty().isWritable() && !NashornCallSiteDescriptor.isDeclaration(desc)) {
        if (NashornCallSiteDescriptor.isScope(desc) && find.getProperty().isLexicalBinding())
          throw ECMAErrors.typeError("assign.constant", new String[] { name }); 
        return createEmptySetMethod(desc, explicitInstanceOfCheck, "property.not.writable", true);
      } 
      if (!find.getProperty().hasNativeSetter())
        return createEmptySetMethod(desc, explicitInstanceOfCheck, "property.has.no.setter", true); 
    } else if (!isExtensible()) {
      return createEmptySetMethod(desc, explicitInstanceOfCheck, "object.non.extensible", false);
    } 
    GuardedInvocation inv = (new SetMethodCreator(this, find, desc, request)).createGuardedInvocation(findBuiltinSwitchPoint(name));
    GlobalConstants globalConstants = getGlobalConstants();
    if (globalConstants != null) {
      GuardedInvocation cinv = globalConstants.findSetMethod(find, this, inv, desc, request);
      if (cinv != null)
        return cinv; 
    } 
    return inv;
  }
  
  private GlobalConstants getGlobalConstants() {
    return !isGlobal() ? null : getContext().getGlobalConstants();
  }
  
  private GuardedInvocation createEmptySetMethod(CallSiteDescriptor desc, boolean explicitInstanceOfCheck, String strictErrorMessage, boolean canBeFastScope) {
    String name = NashornCallSiteDescriptor.getOperand(desc);
    if (NashornCallSiteDescriptor.isStrict(desc))
      throw ECMAErrors.typeError(strictErrorMessage, new String[] { name, ScriptRuntime.safeToString(this) }); 
    assert canBeFastScope || !NashornCallSiteDescriptor.isFastScope(desc);
    return new GuardedInvocation(Lookup.EMPTY_SETTER, 
        
        NashornGuards.getMapGuard(getMap(), explicitInstanceOfCheck), 
        getProtoSwitchPoints(name, null), 
        explicitInstanceOfCheck ? null : (Class)ClassCastException.class);
  }
  
  private boolean extensionCheck(boolean isStrict, String name) {
    if (isExtensible())
      return true; 
    if (isStrict)
      throw ECMAErrors.typeError("object.non.extensible", new String[] { name, ScriptRuntime.safeToString(this) }); 
    return false;
  }
  
  private static GuardedInvocation findMegaMorphicSetMethod(CallSiteDescriptor desc, String name) {
    Context.getContextTrusted().getLogger((Class)ObjectClassGenerator.class).warning(new Object[] { "Megamorphic setter: ", desc, " ", name });
    MethodType type = desc.getMethodType().insertParameterTypes(1, new Class[] { Object.class });
    GuardedInvocation inv = findSetIndexMethod(desc, false, type);
    return inv.replaceMethods(Lookup.MH.insertArguments(inv.getInvocation(), 1, new Object[] { name }), inv.getGuard());
  }
  
  private static Object globalFilter(Object object) {
    ScriptObject sobj = (ScriptObject)object;
    while (sobj != null && !(sobj instanceof Global))
      sobj = sobj.getProto(); 
    return sobj;
  }
  
  protected GuardedInvocation findSetIndexMethod(CallSiteDescriptor desc, LinkRequest request) {
    return findSetIndexMethod(desc, NashornGuards.explicitInstanceOfCheck(desc, request), desc.getMethodType());
  }
  
  private static GuardedInvocation findSetIndexMethod(CallSiteDescriptor desc, boolean explicitInstanceOfCheck, MethodType callType) {
    assert callType.parameterCount() == 3;
    Class<?> keyClass = callType.parameterType(1);
    Class<?> valueClass = callType.parameterType(2);
    MethodHandle methodHandle = findOwnMH_V("set", void.class, new Class[] { keyClass, valueClass, int.class });
    methodHandle = Lookup.MH.insertArguments(methodHandle, 3, new Object[] { Integer.valueOf(NashornCallSiteDescriptor.getFlags(desc)) });
    return new GuardedInvocation(methodHandle, getScriptObjectGuard(callType, explicitInstanceOfCheck), (SwitchPoint)null, explicitInstanceOfCheck ? null : (Class)ClassCastException.class);
  }
  
  public GuardedInvocation noSuchMethod(CallSiteDescriptor desc, LinkRequest request) {
    String name = NashornCallSiteDescriptor.getOperand(desc);
    FindProperty find = findProperty("__noSuchMethod__", true);
    boolean scopeCall = (isScope() && NashornCallSiteDescriptor.isScope(desc));
    if (find == null)
      return noSuchProperty(desc, request)
        
        .addSwitchPoint(getProtoSwitchPoint("__noSuchMethod__")); 
    boolean explicitInstanceOfCheck = NashornGuards.explicitInstanceOfCheck(desc, request);
    Object value = find.getObjectValue();
    if (!(value instanceof ScriptFunction))
      return createEmptyGetter(desc, explicitInstanceOfCheck, name); 
    ScriptFunction func = (ScriptFunction)value;
    Object thiz = (scopeCall && func.isStrict()) ? ScriptRuntime.UNDEFINED : this;
    return (new GuardedInvocation(Lookup.MH
        .dropArguments(Lookup.MH
          .constant(ScriptFunction.class, func
            
            .createBound(thiz, new Object[] { name })), 0, new Class[] { Object.class }), NashornGuards.combineGuards(
          NashornGuards.getIdentityGuard(this), 
          NashornGuards.getMapGuard(getMap(), true))))
      
      .addSwitchPoint(getProtoSwitchPoint(name));
  }
  
  public GuardedInvocation noSuchProperty(CallSiteDescriptor desc, LinkRequest request) {
    String name = NashornCallSiteDescriptor.getOperand(desc);
    FindProperty find = findProperty("__noSuchProperty__", true);
    boolean scopeAccess = (isScope() && NashornCallSiteDescriptor.isScope(desc));
    if (find != null) {
      Object value = find.getObjectValue();
      ScriptFunction func = null;
      MethodHandle mh = null;
      if (value instanceof ScriptFunction) {
        func = (ScriptFunction)value;
        mh = getCallMethodHandle(func, desc.getMethodType(), name);
      } 
      if (mh != null) {
        assert func != null;
        if (scopeAccess && func.isStrict())
          mh = bindTo(mh, ScriptRuntime.UNDEFINED); 
        return (new GuardedInvocation(mh, 
            
            find.isSelf() ? 
            getKnownFunctionPropertyGuardSelf(
              getMap(), find
              .getGetter(Object.class, -1, request), func) : 
            
            getKnownFunctionPropertyGuardProto(
              getMap(), find
              .getGetter(Object.class, -1, request), find
              .getProtoChainLength(), func), 
            
            getProtoSwitchPoints("__noSuchProperty__", find.getOwner()), null))
          
          .addSwitchPoint(getProtoSwitchPoint(name));
      } 
    } 
    if (scopeAccess)
      throw ECMAErrors.referenceError("not.defined", new String[] { name }); 
    return createEmptyGetter(desc, NashornGuards.explicitInstanceOfCheck(desc, request), name);
  }
  
  protected Object invokeNoSuchProperty(Object key, boolean isScope, int programPoint) {
    FindProperty find = findProperty("__noSuchProperty__", true);
    Object func = (find != null) ? find.getObjectValue() : null;
    Object ret = ScriptRuntime.UNDEFINED;
    if (func instanceof ScriptFunction) {
      ScriptFunction sfunc = (ScriptFunction)func;
      Object self = (isScope && sfunc.isStrict()) ? ScriptRuntime.UNDEFINED : this;
      ret = ScriptRuntime.apply(sfunc, self, new Object[] { key });
    } else if (isScope) {
      throw ECMAErrors.referenceError("not.defined", new String[] { key.toString() });
    } 
    if (UnwarrantedOptimismException.isValid(programPoint))
      throw new UnwarrantedOptimismException(ret, programPoint); 
    return ret;
  }
  
  private Object getNoSuchMethod(String name, boolean isScope, int programPoint) {
    FindProperty find = findProperty("__noSuchMethod__", true);
    if (find == null)
      return invokeNoSuchProperty(name, isScope, programPoint); 
    Object value = find.getObjectValue();
    if (!(value instanceof ScriptFunction)) {
      if (isScope)
        throw ECMAErrors.referenceError("not.defined", new String[] { name }); 
      return ScriptRuntime.UNDEFINED;
    } 
    ScriptFunction func = (ScriptFunction)value;
    Object self = (isScope && func.isStrict()) ? ScriptRuntime.UNDEFINED : this;
    return func.createBound(self, new Object[] { name });
  }
  
  private GuardedInvocation createEmptyGetter(CallSiteDescriptor desc, boolean explicitInstanceOfCheck, String name) {
    if (NashornCallSiteDescriptor.isOptimistic(desc))
      throw new UnwarrantedOptimismException(ScriptRuntime.UNDEFINED, NashornCallSiteDescriptor.getProgramPoint(desc), Type.OBJECT); 
    return new GuardedInvocation(Lookup.emptyGetter(desc.getMethodType().returnType()), 
        NashornGuards.getMapGuard(getMap(), explicitInstanceOfCheck), getProtoSwitchPoints(name, null), 
        explicitInstanceOfCheck ? null : (Class)ClassCastException.class);
  }
  
  private static abstract class ScriptObjectIterator<T> implements Iterator<T> {
    protected T[] values;
    
    protected final ScriptObject object;
    
    private int index;
    
    ScriptObjectIterator(ScriptObject object) {
      this.object = object;
    }
    
    protected abstract void init();
    
    public boolean hasNext() {
      if (this.values == null)
        init(); 
      return (this.index < this.values.length);
    }
    
    public T next() {
      if (this.values == null)
        init(); 
      return this.values[this.index++];
    }
    
    public void remove() {
      throw new UnsupportedOperationException("remove");
    }
  }
  
  private static class KeyIterator extends ScriptObjectIterator<String> {
    KeyIterator(ScriptObject object) {
      super(object);
    }
    
    protected void init() {
      Set<String> keys = new LinkedHashSet<>();
      Set<String> nonEnumerable = new HashSet<>();
      for (ScriptObject self = this.object; self != null; self = self.getProto())
        keys.addAll(Arrays.asList(self.<String>getOwnKeys(String.class, false, nonEnumerable))); 
      this.values = keys.toArray(new String[0]);
    }
  }
  
  private static class ValueIterator extends ScriptObjectIterator<Object> {
    ValueIterator(ScriptObject object) {
      super(object);
    }
    
    protected void init() {
      ArrayList<Object> valueList = new ArrayList();
      Set<String> nonEnumerable = new HashSet<>();
      for (ScriptObject self = this.object; self != null; self = self.getProto()) {
        for (String key : (String[])self.<String>getOwnKeys(String.class, false, nonEnumerable))
          valueList.add(self.get(key)); 
      } 
      this.values = valueList.toArray(new Object[0]);
    }
  }
  
  private Property addSpillProperty(Object key, int flags, Object value, boolean hasInitialValue) {
    Property property;
    PropertyMap propertyMap = getMap();
    int fieldSlot = propertyMap.getFreeFieldSlot();
    int propertyFlags = flags | (useDualFields() ? 2048 : 0);
    if (fieldSlot > -1) {
      property = hasInitialValue ? new AccessorProperty(key, propertyFlags, fieldSlot, this, value) : new AccessorProperty(key, propertyFlags, getClass(), fieldSlot);
      property = addOwnProperty(property);
    } else {
      int spillSlot = propertyMap.getFreeSpillSlot();
      property = hasInitialValue ? new SpillProperty(key, propertyFlags, spillSlot, this, value) : new SpillProperty(key, propertyFlags, spillSlot);
      property = addOwnProperty(property);
      ensureSpillSize(property.getSlot());
    } 
    return property;
  }
  
  MethodHandle addSpill(Class<?> type, String key) {
    return addSpillProperty(key, 0, null, false).getSetter(type, getMap());
  }
  
  protected static MethodHandle pairArguments(MethodHandle methodHandle, MethodType callType) {
    return pairArguments(methodHandle, callType, null);
  }
  
  public static MethodHandle pairArguments(MethodHandle methodHandle, MethodType callType, Boolean callerVarArg) {
    MethodType methodType = methodHandle.type();
    if (methodType.equals(callType.changeReturnType(methodType.returnType())))
      return methodHandle; 
    int parameterCount = methodType.parameterCount();
    int callCount = callType.parameterCount();
    boolean isCalleeVarArg = (parameterCount > 0 && methodType.parameterType(parameterCount - 1).isArray());
    boolean isCallerVarArg = (callerVarArg != null) ? callerVarArg.booleanValue() : ((callCount > 0 && callType.parameterType(callCount - 1).isArray()));
    if (isCalleeVarArg)
      return isCallerVarArg ? 
        methodHandle : 
        Lookup.MH.asCollector(methodHandle, Object[].class, callCount - parameterCount + 1); 
    if (isCallerVarArg)
      return adaptHandleToVarArgCallSite(methodHandle, callCount); 
    if (callCount < parameterCount) {
      int missingArgs = parameterCount - callCount;
      Object[] fillers = new Object[missingArgs];
      Arrays.fill(fillers, ScriptRuntime.UNDEFINED);
      return Lookup.MH.insertArguments(methodHandle, parameterCount - missingArgs, fillers);
    } 
    if (callCount > parameterCount) {
      int discardedArgs = callCount - parameterCount;
      Class<?>[] discards = new Class[discardedArgs];
      Arrays.fill((Object[])discards, Object.class);
      return Lookup.MH.dropArguments(methodHandle, callCount - discardedArgs, discards);
    } 
    return methodHandle;
  }
  
  static MethodHandle adaptHandleToVarArgCallSite(MethodHandle mh, int callSiteParamCount) {
    int spreadArgs = mh.type().parameterCount() - callSiteParamCount + 1;
    return Lookup.MH.filterArguments(Lookup.MH
        .asSpreader(mh, Object[].class, spreadArgs), callSiteParamCount - 1, new MethodHandle[] { Lookup.MH
          
          .insertArguments(TRUNCATINGFILTER, 0, new Object[] { Integer.valueOf(spreadArgs) }) });
  }
  
  private static Object[] truncatingFilter(int n, Object[] array) {
    int length = (array == null) ? 0 : array.length;
    if (n == length)
      return (array == null) ? ScriptRuntime.EMPTY_ARRAY : array; 
    Object[] newArray = new Object[n];
    if (array != null)
      System.arraycopy(array, 0, newArray, 0, Math.min(n, length)); 
    if (length < n)
      Arrays.fill(newArray, length, n, ScriptRuntime.UNDEFINED); 
    return newArray;
  }
  
  public final void setLength(long newLength) {
    ArrayData data = getArray();
    long arrayLength = data.length();
    if (newLength == arrayLength)
      return; 
    if (newLength > arrayLength) {
      setArray(data.ensure(newLength - 1L).safeDelete(arrayLength, newLength - 1L, false));
      return;
    } 
    long actualLength = newLength;
    if (getMap().containsArrayKeys()) {
      long l;
      for (l = arrayLength - 1L; l >= newLength; l--) {
        FindProperty find = findProperty(JSType.toString(l), false);
        if (find != null)
          if (find.getProperty().isConfigurable()) {
            deleteOwnProperty(find.getProperty());
          } else {
            actualLength = l + 1L;
            break;
          }  
      } 
    } 
    setArray(data.shrink(actualLength));
    data.setLength(actualLength);
  }
  
  private int getInt(int index, Object key, int programPoint) {
    if (ArrayIndex.isValidArrayIndex(index)) {
      ScriptObject object = this;
      while (true) {
        if (object.getMap().containsArrayKeys()) {
          FindProperty find = object.findProperty(key, false);
          if (find != null)
            return getIntValue(find, programPoint); 
        } 
        if ((object = object.getProto()) == null)
          break; 
        ArrayData array = object.getArray();
        if (array.has(index))
          return UnwarrantedOptimismException.isValid(programPoint) ? 
            array.getIntOptimistic(index, programPoint) : 
            array.getInt(index); 
      } 
    } else {
      FindProperty find = findProperty(key, true);
      if (find != null)
        return getIntValue(find, programPoint); 
    } 
    return JSType.toInt32(invokeNoSuchProperty(key, false, programPoint));
  }
  
  public int getInt(Object key, int programPoint) {
    Object primitiveKey = JSType.toPrimitive(key, String.class);
    int index = ArrayIndex.getArrayIndex(primitiveKey);
    ArrayData array = getArray();
    if (array.has(index))
      return UnwarrantedOptimismException.isValid(programPoint) ? array.getIntOptimistic(index, programPoint) : array.getInt(index); 
    return getInt(index, JSType.toPropertyKey(primitiveKey), programPoint);
  }
  
  public int getInt(double key, int programPoint) {
    int index = ArrayIndex.getArrayIndex(key);
    ArrayData array = getArray();
    if (array.has(index))
      return UnwarrantedOptimismException.isValid(programPoint) ? array.getIntOptimistic(index, programPoint) : array.getInt(index); 
    return getInt(index, JSType.toString(key), programPoint);
  }
  
  public int getInt(int key, int programPoint) {
    int index = ArrayIndex.getArrayIndex(key);
    ArrayData array = getArray();
    if (array.has(index))
      return UnwarrantedOptimismException.isValid(programPoint) ? array.getIntOptimistic(key, programPoint) : array.getInt(key); 
    return getInt(index, JSType.toString(key), programPoint);
  }
  
  private double getDouble(int index, Object key, int programPoint) {
    if (ArrayIndex.isValidArrayIndex(index)) {
      ScriptObject object = this;
      while (true) {
        if (object.getMap().containsArrayKeys()) {
          FindProperty find = object.findProperty(key, false);
          if (find != null)
            return getDoubleValue(find, programPoint); 
        } 
        if ((object = object.getProto()) == null)
          break; 
        ArrayData array = object.getArray();
        if (array.has(index))
          return UnwarrantedOptimismException.isValid(programPoint) ? 
            array.getDoubleOptimistic(index, programPoint) : 
            array.getDouble(index); 
      } 
    } else {
      FindProperty find = findProperty(key, true);
      if (find != null)
        return getDoubleValue(find, programPoint); 
    } 
    return JSType.toNumber(invokeNoSuchProperty(key, false, -1));
  }
  
  public double getDouble(Object key, int programPoint) {
    Object primitiveKey = JSType.toPrimitive(key, String.class);
    int index = ArrayIndex.getArrayIndex(primitiveKey);
    ArrayData array = getArray();
    if (array.has(index))
      return UnwarrantedOptimismException.isValid(programPoint) ? array.getDoubleOptimistic(index, programPoint) : array.getDouble(index); 
    return getDouble(index, JSType.toPropertyKey(primitiveKey), programPoint);
  }
  
  public double getDouble(double key, int programPoint) {
    int index = ArrayIndex.getArrayIndex(key);
    ArrayData array = getArray();
    if (array.has(index))
      return UnwarrantedOptimismException.isValid(programPoint) ? array.getDoubleOptimistic(index, programPoint) : array.getDouble(index); 
    return getDouble(index, JSType.toString(key), programPoint);
  }
  
  public double getDouble(int key, int programPoint) {
    int index = ArrayIndex.getArrayIndex(key);
    ArrayData array = getArray();
    if (array.has(index))
      return UnwarrantedOptimismException.isValid(programPoint) ? array.getDoubleOptimistic(key, programPoint) : array.getDouble(key); 
    return getDouble(index, JSType.toString(key), programPoint);
  }
  
  private Object get(int index, Object key) {
    if (ArrayIndex.isValidArrayIndex(index)) {
      ScriptObject object = this;
      while (true) {
        if (object.getMap().containsArrayKeys()) {
          FindProperty find = object.findProperty(key, false);
          if (find != null)
            return find.getObjectValue(); 
        } 
        if ((object = object.getProto()) == null)
          break; 
        ArrayData array = object.getArray();
        if (array.has(index))
          return array.getObject(index); 
      } 
    } else {
      FindProperty find = findProperty(key, true);
      if (find != null)
        return find.getObjectValue(); 
    } 
    return invokeNoSuchProperty(key, false, -1);
  }
  
  public Object get(Object key) {
    Object primitiveKey = JSType.toPrimitive(key, String.class);
    int index = ArrayIndex.getArrayIndex(primitiveKey);
    ArrayData array = getArray();
    if (array.has(index))
      return array.getObject(index); 
    return get(index, JSType.toPropertyKey(primitiveKey));
  }
  
  public Object get(double key) {
    int index = ArrayIndex.getArrayIndex(key);
    ArrayData array = getArray();
    if (array.has(index))
      return array.getObject(index); 
    return get(index, JSType.toString(key));
  }
  
  public Object get(int key) {
    int index = ArrayIndex.getArrayIndex(key);
    ArrayData array = getArray();
    if (array.has(index))
      return array.getObject(index); 
    return get(index, JSType.toString(key));
  }
  
  private boolean doesNotHaveCheckArrayKeys(long longIndex, int value, int callSiteFlags) {
    if (hasDefinedArrayProperties()) {
      String key = JSType.toString(longIndex);
      FindProperty find = findProperty(key, true);
      if (find != null) {
        setObject(find, callSiteFlags, key, Integer.valueOf(value));
        return true;
      } 
    } 
    return false;
  }
  
  private boolean doesNotHaveCheckArrayKeys(long longIndex, double value, int callSiteFlags) {
    if (hasDefinedArrayProperties()) {
      String key = JSType.toString(longIndex);
      FindProperty find = findProperty(key, true);
      if (find != null) {
        setObject(find, callSiteFlags, key, Double.valueOf(value));
        return true;
      } 
    } 
    return false;
  }
  
  private boolean doesNotHaveCheckArrayKeys(long longIndex, Object value, int callSiteFlags) {
    if (hasDefinedArrayProperties()) {
      String key = JSType.toString(longIndex);
      FindProperty find = findProperty(key, true);
      if (find != null) {
        setObject(find, callSiteFlags, key, value);
        return true;
      } 
    } 
    return false;
  }
  
  private boolean hasDefinedArrayProperties() {
    for (ScriptObject obj = this; obj != null; obj = obj.getProto()) {
      if (obj.getMap().containsArrayKeys())
        return true; 
    } 
    return false;
  }
  
  private boolean doesNotHaveEnsureLength(long longIndex, long oldLength, int callSiteFlags) {
    if (longIndex >= oldLength) {
      if (!isExtensible()) {
        if (NashornCallSiteDescriptor.isStrictFlag(callSiteFlags))
          throw ECMAErrors.typeError("object.non.extensible", new String[] { JSType.toString(longIndex), ScriptRuntime.safeToString(this) }); 
        return true;
      } 
      setArray(getArray().ensure(longIndex));
    } 
    return false;
  }
  
  private void doesNotHave(int index, int value, int callSiteFlags) {
    long oldLength = getArray().length();
    long longIndex = ArrayIndex.toLongIndex(index);
    if (!doesNotHaveCheckArrayKeys(longIndex, value, callSiteFlags) && !doesNotHaveEnsureLength(longIndex, oldLength, callSiteFlags)) {
      boolean strict = NashornCallSiteDescriptor.isStrictFlag(callSiteFlags);
      setArray(getArray().set(index, value, strict).safeDelete(oldLength, longIndex - 1L, strict));
    } 
  }
  
  private void doesNotHave(int index, double value, int callSiteFlags) {
    long oldLength = getArray().length();
    long longIndex = ArrayIndex.toLongIndex(index);
    if (!doesNotHaveCheckArrayKeys(longIndex, value, callSiteFlags) && !doesNotHaveEnsureLength(longIndex, oldLength, callSiteFlags)) {
      boolean strict = NashornCallSiteDescriptor.isStrictFlag(callSiteFlags);
      setArray(getArray().set(index, value, strict).safeDelete(oldLength, longIndex - 1L, strict));
    } 
  }
  
  private void doesNotHave(int index, Object value, int callSiteFlags) {
    long oldLength = getArray().length();
    long longIndex = ArrayIndex.toLongIndex(index);
    if (!doesNotHaveCheckArrayKeys(longIndex, value, callSiteFlags) && !doesNotHaveEnsureLength(longIndex, oldLength, callSiteFlags)) {
      boolean strict = NashornCallSiteDescriptor.isStrictFlag(callSiteFlags);
      setArray(getArray().set(index, value, strict).safeDelete(oldLength, longIndex - 1L, strict));
    } 
  }
  
  public final void setObject(FindProperty find, int callSiteFlags, Object key, Object value) {
    FindProperty f = find;
    invalidateGlobalConstant(key);
    if (f != null && f.isInheritedOrdinaryProperty()) {
      boolean isScope = NashornCallSiteDescriptor.isScopeFlag(callSiteFlags);
      if (isScope && f.getSelf() != this) {
        f.getSelf().setObject(null, 0, key, value);
        return;
      } 
      if (!isScope || !f.getOwner().isScope())
        f = null; 
    } 
    if (f != null) {
      if ((!f.getProperty().isWritable() && !NashornCallSiteDescriptor.isDeclaration(callSiteFlags)) || !f.getProperty().hasNativeSetter()) {
        if (NashornCallSiteDescriptor.isScopeFlag(callSiteFlags) && f.getProperty().isLexicalBinding())
          throw ECMAErrors.typeError("assign.constant", new String[] { key.toString() }); 
        if (NashornCallSiteDescriptor.isStrictFlag(callSiteFlags))
          throw ECMAErrors.typeError(
              f.getProperty().isAccessorProperty() ? "property.has.no.setter" : "property.not.writable", new String[] { key
                .toString(), ScriptRuntime.safeToString(this) }); 
        return;
      } 
      if (NashornCallSiteDescriptor.isDeclaration(callSiteFlags) && f.getProperty().needsDeclaration()) {
        f.getOwner().declareAndSet(f, value);
        return;
      } 
      f.setValue(value, NashornCallSiteDescriptor.isStrictFlag(callSiteFlags));
    } else if (!isExtensible()) {
      if (NashornCallSiteDescriptor.isStrictFlag(callSiteFlags))
        throw ECMAErrors.typeError("object.non.extensible", new String[] { key.toString(), ScriptRuntime.safeToString(this) }); 
    } else {
      ScriptObject sobj = this;
      if (isScope()) {
        while (sobj != null && !(sobj instanceof Global))
          sobj = sobj.getProto(); 
        assert sobj != null : "no parent global object in scope";
      } 
      sobj.addSpillProperty(key, 0, value, true);
    } 
  }
  
  public void set(Object key, int value, int callSiteFlags) {
    Object primitiveKey = JSType.toPrimitive(key, String.class);
    int index = ArrayIndex.getArrayIndex(primitiveKey);
    if (ArrayIndex.isValidArrayIndex(index)) {
      ArrayData data = getArray();
      if (data.has(index)) {
        setArray(data.set(index, value, NashornCallSiteDescriptor.isStrictFlag(callSiteFlags)));
      } else {
        doesNotHave(index, value, callSiteFlags);
      } 
      return;
    } 
    Object propName = JSType.toPropertyKey(primitiveKey);
    setObject(findProperty(propName, true), callSiteFlags, propName, JSType.toObject(value));
  }
  
  public void set(Object key, double value, int callSiteFlags) {
    Object primitiveKey = JSType.toPrimitive(key, String.class);
    int index = ArrayIndex.getArrayIndex(primitiveKey);
    if (ArrayIndex.isValidArrayIndex(index)) {
      ArrayData data = getArray();
      if (data.has(index)) {
        setArray(data.set(index, value, NashornCallSiteDescriptor.isStrictFlag(callSiteFlags)));
      } else {
        doesNotHave(index, value, callSiteFlags);
      } 
      return;
    } 
    Object propName = JSType.toPropertyKey(primitiveKey);
    setObject(findProperty(propName, true), callSiteFlags, propName, JSType.toObject(value));
  }
  
  public void set(Object key, Object value, int callSiteFlags) {
    Object primitiveKey = JSType.toPrimitive(key, String.class);
    int index = ArrayIndex.getArrayIndex(primitiveKey);
    if (ArrayIndex.isValidArrayIndex(index)) {
      ArrayData data = getArray();
      if (data.has(index)) {
        setArray(data.set(index, value, NashornCallSiteDescriptor.isStrictFlag(callSiteFlags)));
      } else {
        doesNotHave(index, value, callSiteFlags);
      } 
      return;
    } 
    Object propName = JSType.toPropertyKey(primitiveKey);
    setObject(findProperty(propName, true), callSiteFlags, propName, value);
  }
  
  public void set(double key, int value, int callSiteFlags) {
    int index = ArrayIndex.getArrayIndex(key);
    if (ArrayIndex.isValidArrayIndex(index)) {
      ArrayData data = getArray();
      if (data.has(index)) {
        setArray(data.set(index, value, NashornCallSiteDescriptor.isStrictFlag(callSiteFlags)));
      } else {
        doesNotHave(index, value, callSiteFlags);
      } 
      return;
    } 
    String propName = JSType.toString(key);
    setObject(findProperty(propName, true), callSiteFlags, propName, JSType.toObject(value));
  }
  
  public void set(double key, double value, int callSiteFlags) {
    int index = ArrayIndex.getArrayIndex(key);
    if (ArrayIndex.isValidArrayIndex(index)) {
      ArrayData data = getArray();
      if (data.has(index)) {
        setArray(data.set(index, value, NashornCallSiteDescriptor.isStrictFlag(callSiteFlags)));
      } else {
        doesNotHave(index, value, callSiteFlags);
      } 
      return;
    } 
    String propName = JSType.toString(key);
    setObject(findProperty(propName, true), callSiteFlags, propName, JSType.toObject(value));
  }
  
  public void set(double key, Object value, int callSiteFlags) {
    int index = ArrayIndex.getArrayIndex(key);
    if (ArrayIndex.isValidArrayIndex(index)) {
      ArrayData data = getArray();
      if (data.has(index)) {
        setArray(data.set(index, value, NashornCallSiteDescriptor.isStrictFlag(callSiteFlags)));
      } else {
        doesNotHave(index, value, callSiteFlags);
      } 
      return;
    } 
    String propName = JSType.toString(key);
    setObject(findProperty(propName, true), callSiteFlags, propName, value);
  }
  
  public void set(int key, int value, int callSiteFlags) {
    int index = ArrayIndex.getArrayIndex(key);
    if (ArrayIndex.isValidArrayIndex(index)) {
      if (getArray().has(index)) {
        ArrayData data = getArray();
        setArray(data.set(index, value, NashornCallSiteDescriptor.isStrictFlag(callSiteFlags)));
      } else {
        doesNotHave(index, value, callSiteFlags);
      } 
      return;
    } 
    String propName = JSType.toString(key);
    setObject(findProperty(propName, true), callSiteFlags, propName, JSType.toObject(value));
  }
  
  public void set(int key, double value, int callSiteFlags) {
    int index = ArrayIndex.getArrayIndex(key);
    if (ArrayIndex.isValidArrayIndex(index)) {
      ArrayData data = getArray();
      if (data.has(index)) {
        setArray(data.set(index, value, NashornCallSiteDescriptor.isStrictFlag(callSiteFlags)));
      } else {
        doesNotHave(index, value, callSiteFlags);
      } 
      return;
    } 
    String propName = JSType.toString(key);
    setObject(findProperty(propName, true), callSiteFlags, propName, JSType.toObject(value));
  }
  
  public void set(int key, Object value, int callSiteFlags) {
    int index = ArrayIndex.getArrayIndex(key);
    if (ArrayIndex.isValidArrayIndex(index)) {
      ArrayData data = getArray();
      if (data.has(index)) {
        setArray(data.set(index, value, NashornCallSiteDescriptor.isStrictFlag(callSiteFlags)));
      } else {
        doesNotHave(index, value, callSiteFlags);
      } 
      return;
    } 
    String propName = JSType.toString(key);
    setObject(findProperty(propName, true), callSiteFlags, propName, value);
  }
  
  public boolean has(Object key) {
    Object primitiveKey = JSType.toPrimitive(key);
    int index = ArrayIndex.getArrayIndex(primitiveKey);
    return ArrayIndex.isValidArrayIndex(index) ? hasArrayProperty(index) : hasProperty(JSType.toPropertyKey(primitiveKey), true);
  }
  
  public boolean has(double key) {
    int index = ArrayIndex.getArrayIndex(key);
    return ArrayIndex.isValidArrayIndex(index) ? hasArrayProperty(index) : hasProperty(JSType.toString(key), true);
  }
  
  public boolean has(int key) {
    int index = ArrayIndex.getArrayIndex(key);
    return ArrayIndex.isValidArrayIndex(index) ? hasArrayProperty(index) : hasProperty(JSType.toString(key), true);
  }
  
  private boolean hasArrayProperty(int index) {
    boolean hasArrayKeys = false;
    for (ScriptObject self = this; self != null; self = self.getProto()) {
      if (self.getArray().has(index))
        return true; 
      hasArrayKeys = (hasArrayKeys || self.getMap().containsArrayKeys());
    } 
    return (hasArrayKeys && hasProperty(ArrayIndex.toKey(index), true));
  }
  
  public boolean hasOwnProperty(Object key) {
    Object primitiveKey = JSType.toPrimitive(key, String.class);
    int index = ArrayIndex.getArrayIndex(primitiveKey);
    return ArrayIndex.isValidArrayIndex(index) ? hasOwnArrayProperty(index) : hasProperty(JSType.toPropertyKey(primitiveKey), false);
  }
  
  public boolean hasOwnProperty(int key) {
    int index = ArrayIndex.getArrayIndex(key);
    return ArrayIndex.isValidArrayIndex(index) ? hasOwnArrayProperty(index) : hasProperty(JSType.toString(key), false);
  }
  
  public boolean hasOwnProperty(double key) {
    int index = ArrayIndex.getArrayIndex(key);
    return ArrayIndex.isValidArrayIndex(index) ? hasOwnArrayProperty(index) : hasProperty(JSType.toString(key), false);
  }
  
  private boolean hasOwnArrayProperty(int index) {
    return (getArray().has(index) || (getMap().containsArrayKeys() && hasProperty(ArrayIndex.toKey(index), false)));
  }
  
  public boolean delete(int key, boolean strict) {
    int index = ArrayIndex.getArrayIndex(key);
    ArrayData array = getArray();
    if (array.has(index)) {
      if (array.canDelete(index, strict)) {
        setArray(array.delete(index));
        return true;
      } 
      return false;
    } 
    return deleteObject(JSType.toObject(key), strict);
  }
  
  public boolean delete(double key, boolean strict) {
    int index = ArrayIndex.getArrayIndex(key);
    ArrayData array = getArray();
    if (array.has(index)) {
      if (array.canDelete(index, strict)) {
        setArray(array.delete(index));
        return true;
      } 
      return false;
    } 
    return deleteObject(JSType.toObject(key), strict);
  }
  
  public boolean delete(Object key, boolean strict) {
    Object primitiveKey = JSType.toPrimitive(key, String.class);
    int index = ArrayIndex.getArrayIndex(primitiveKey);
    ArrayData array = getArray();
    if (array.has(index)) {
      if (array.canDelete(index, strict)) {
        setArray(array.delete(index));
        return true;
      } 
      return false;
    } 
    return deleteObject(primitiveKey, strict);
  }
  
  private boolean deleteObject(Object key, boolean strict) {
    Object propName = JSType.toPropertyKey(key);
    FindProperty find = findProperty(propName, false);
    if (find == null)
      return true; 
    if (!find.getProperty().isConfigurable()) {
      if (strict)
        throw ECMAErrors.typeError("cant.delete.property", new String[] { propName.toString(), ScriptRuntime.safeToString(this) }); 
      return false;
    } 
    Property prop = find.getProperty();
    deleteOwnProperty(prop);
    return true;
  }
  
  public final ScriptObject copy() {
    try {
      return clone();
    } catch (CloneNotSupportedException e) {
      throw new RuntimeException(e);
    } 
  }
  
  protected ScriptObject clone() throws CloneNotSupportedException {
    ScriptObject clone = (ScriptObject)super.clone();
    if (this.objectSpill != null) {
      clone.objectSpill = (Object[])this.objectSpill.clone();
      if (this.primitiveSpill != null)
        clone.primitiveSpill = (long[])this.primitiveSpill.clone(); 
    } 
    clone.arrayData = this.arrayData.copy();
    return clone;
  }
  
  protected final UserAccessorProperty newUserAccessors(Object key, int propertyFlags, ScriptFunction getter, ScriptFunction setter) {
    UserAccessorProperty uc = getMap().newUserAccessors(key, propertyFlags);
    uc.setAccessors(this, getMap(), new UserAccessorProperty.Accessors(getter, setter));
    return uc;
  }
  
  protected boolean useDualFields() {
    return !StructureLoader.isSingleFieldStructure(getClass().getName());
  }
  
  Object ensureSpillSize(int slot) {
    int oldLength = (this.objectSpill == null) ? 0 : this.objectSpill.length;
    if (slot < oldLength)
      return this; 
    int newLength = alignUp(slot + 1, 8);
    Object[] newObjectSpill = new Object[newLength];
    long[] newPrimitiveSpill = useDualFields() ? new long[newLength] : null;
    if (this.objectSpill != null) {
      System.arraycopy(this.objectSpill, 0, newObjectSpill, 0, oldLength);
      if (this.primitiveSpill != null && newPrimitiveSpill != null)
        System.arraycopy(this.primitiveSpill, 0, newPrimitiveSpill, 0, oldLength); 
    } 
    this.primitiveSpill = newPrimitiveSpill;
    this.objectSpill = newObjectSpill;
    return this;
  }
  
  private static MethodHandle findOwnMH_V(String name, Class<?> rtype, Class<?>... types) {
    return Lookup.MH.findVirtual(MethodHandles.lookup(), ScriptObject.class, name, Lookup.MH.type(rtype, types));
  }
  
  private static MethodHandle findOwnMH_S(String name, Class<?> rtype, Class<?>... types) {
    return Lookup.MH.findStatic(MethodHandles.lookup(), ScriptObject.class, name, Lookup.MH.type(rtype, types));
  }
  
  private static MethodHandle getKnownFunctionPropertyGuardSelf(PropertyMap map, MethodHandle getter, ScriptFunction func) {
    return Lookup.MH.insertArguments(KNOWNFUNCPROPGUARDSELF, 1, new Object[] { map, getter, func });
  }
  
  private static boolean knownFunctionPropertyGuardSelf(Object self, PropertyMap map, MethodHandle getter, ScriptFunction func) {
    if (self instanceof ScriptObject && ((ScriptObject)self).getMap() == map)
      try {
        return (getter.invokeExact(self) == func);
      } catch (RuntimeException|Error e) {
        throw e;
      } catch (Throwable t) {
        throw new RuntimeException(t);
      }  
    return false;
  }
  
  private static MethodHandle getKnownFunctionPropertyGuardProto(PropertyMap map, MethodHandle getter, int depth, ScriptFunction func) {
    return Lookup.MH.insertArguments(KNOWNFUNCPROPGUARDPROTO, 1, new Object[] { map, getter, Integer.valueOf(depth), func });
  }
  
  private static ScriptObject getProto(ScriptObject self, int depth) {
    ScriptObject proto = self;
    for (int d = 0; d < depth; d++) {
      proto = proto.getProto();
      if (proto == null)
        return null; 
    } 
    return proto;
  }
  
  private static boolean knownFunctionPropertyGuardProto(Object self, PropertyMap map, MethodHandle getter, int depth, ScriptFunction func) {
    if (self instanceof ScriptObject && ((ScriptObject)self).getMap() == map) {
      ScriptObject proto = getProto((ScriptObject)self, depth);
      if (proto == null)
        return false; 
      try {
        return (getter.invokeExact(proto) == func);
      } catch (RuntimeException|Error e) {
        throw e;
      } catch (Throwable t) {
        throw new RuntimeException(t);
      } 
    } 
    return false;
  }
  
  static {
    if (Context.DEBUG)
      count = new LongAdder(); 
  }
  
  public static long getCount() {
    return count.longValue();
  }
}
