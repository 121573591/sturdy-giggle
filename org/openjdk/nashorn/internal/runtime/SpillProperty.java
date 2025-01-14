package org.openjdk.nashorn.internal.runtime;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import org.openjdk.nashorn.internal.lookup.Lookup;

public class SpillProperty extends AccessorProperty {
  private static final long serialVersionUID = 3028496245198669460L;
  
  private static final MethodHandles.Lookup LOOKUP = MethodHandles.lookup();
  
  private static final MethodHandle PARRAY_GETTER = Lookup.MH.asType(Lookup.MH.getter(LOOKUP, ScriptObject.class, "primitiveSpill", long[].class), Lookup.MH.type(long[].class, new Class[] { Object.class }));
  
  private static final MethodHandle OARRAY_GETTER = Lookup.MH.asType(Lookup.MH.getter(LOOKUP, ScriptObject.class, "objectSpill", Object[].class), Lookup.MH.type(Object[].class, new Class[] { Object.class }));
  
  private static final MethodHandle OBJECT_GETTER = Lookup.MH.filterArguments(Lookup.MH.arrayElementGetter(Object[].class), 0, new MethodHandle[] { OARRAY_GETTER });
  
  private static final MethodHandle PRIMITIVE_GETTER = Lookup.MH.filterArguments(Lookup.MH.arrayElementGetter(long[].class), 0, new MethodHandle[] { PARRAY_GETTER });
  
  private static final MethodHandle OBJECT_SETTER = Lookup.MH.filterArguments(Lookup.MH.arrayElementSetter(Object[].class), 0, new MethodHandle[] { OARRAY_GETTER });
  
  private static final MethodHandle PRIMITIVE_SETTER = Lookup.MH.filterArguments(Lookup.MH.arrayElementSetter(long[].class), 0, new MethodHandle[] { PARRAY_GETTER });
  
  private static class Accessors {
    private MethodHandle objectGetter;
    
    private MethodHandle objectSetter;
    
    private MethodHandle primitiveGetter;
    
    private MethodHandle primitiveSetter;
    
    private final int slot;
    
    private final MethodHandle ensureSpillSize;
    
    private static Accessors[] ACCESSOR_CACHE = new Accessors[512];
    
    Accessors(int slot) {
      assert slot >= 0;
      this.slot = slot;
      this.ensureSpillSize = Lookup.MH.asType(Lookup.MH.insertArguments(ScriptObject.ENSURE_SPILL_SIZE, 1, new Object[] { Integer.valueOf(slot) }), Lookup.MH.type(Object.class, new Class[] { Object.class }));
    }
    
    private static void ensure(int slot) {
      int len = ACCESSOR_CACHE.length;
      if (slot >= len)
        while (true) {
          len *= 2;
          if (slot < len) {
            Accessors[] newCache = new Accessors[len];
            System.arraycopy(ACCESSOR_CACHE, 0, newCache, 0, ACCESSOR_CACHE.length);
            ACCESSOR_CACHE = newCache;
            break;
          } 
        }  
    }
    
    static MethodHandle getCached(int slot, boolean isPrimitive, boolean isGetter) {
      ensure(slot);
      Accessors acc = ACCESSOR_CACHE[slot];
      if (acc == null) {
        acc = new Accessors(slot);
        ACCESSOR_CACHE[slot] = acc;
      } 
      return acc.getOrCreate(isPrimitive, isGetter);
    }
    
    private static MethodHandle primordial(boolean isPrimitive, boolean isGetter) {
      if (isPrimitive)
        return isGetter ? SpillProperty.PRIMITIVE_GETTER : SpillProperty.PRIMITIVE_SETTER; 
      return isGetter ? SpillProperty.OBJECT_GETTER : SpillProperty.OBJECT_SETTER;
    }
    
    MethodHandle getOrCreate(boolean isPrimitive, boolean isGetter) {
      MethodHandle accessor = getInner(isPrimitive, isGetter);
      if (accessor != null)
        return accessor; 
      accessor = primordial(isPrimitive, isGetter);
      accessor = Lookup.MH.insertArguments(accessor, 1, new Object[] { Integer.valueOf(this.slot) });
      if (!isGetter)
        accessor = Lookup.MH.filterArguments(accessor, 0, new MethodHandle[] { this.ensureSpillSize }); 
      setInner(isPrimitive, isGetter, accessor);
      return accessor;
    }
    
    void setInner(boolean isPrimitive, boolean isGetter, MethodHandle mh) {
      if (isPrimitive) {
        if (isGetter) {
          this.primitiveGetter = mh;
        } else {
          this.primitiveSetter = mh;
        } 
      } else if (isGetter) {
        this.objectGetter = mh;
      } else {
        this.objectSetter = mh;
      } 
    }
    
    MethodHandle getInner(boolean isPrimitive, boolean isGetter) {
      if (isPrimitive)
        return isGetter ? this.primitiveGetter : this.primitiveSetter; 
      return isGetter ? this.objectGetter : this.objectSetter;
    }
  }
  
  private static MethodHandle primitiveGetter(int slot, int flags) {
    return ((flags & 0x800) == 2048) ? Accessors.getCached(slot, true, true) : null;
  }
  
  private static MethodHandle primitiveSetter(int slot, int flags) {
    return ((flags & 0x800) == 2048) ? Accessors.getCached(slot, true, false) : null;
  }
  
  private static MethodHandle objectGetter(int slot) {
    return Accessors.getCached(slot, false, true);
  }
  
  private static MethodHandle objectSetter(int slot) {
    return Accessors.getCached(slot, false, false);
  }
  
  public SpillProperty(Object key, int flags, int slot) {
    super(key, flags, slot, primitiveGetter(slot, flags), primitiveSetter(slot, flags), objectGetter(slot), objectSetter(slot));
  }
  
  public SpillProperty(String key, int flags, int slot, Class<?> initialType) {
    this(key, flags, slot);
    setType(hasDualFields() ? initialType : Object.class);
  }
  
  SpillProperty(Object key, int flags, int slot, ScriptObject owner, Object initialValue) {
    this(key, flags, slot);
    setInitialValue(owner, initialValue);
  }
  
  protected SpillProperty(SpillProperty property) {
    super(property);
  }
  
  protected SpillProperty(SpillProperty property, Class<?> newType) {
    super(property, newType);
  }
  
  public Property copy() {
    return new SpillProperty(this);
  }
  
  public Property copy(Class<?> newType) {
    return new SpillProperty(this, newType);
  }
  
  public boolean isSpill() {
    return true;
  }
  
  void initMethodHandles(Class<?> structure) {
    int slot = getSlot();
    this.primitiveGetter = primitiveGetter(slot, getFlags());
    this.primitiveSetter = primitiveSetter(slot, getFlags());
    this.objectGetter = objectGetter(slot);
    this.objectSetter = objectSetter(slot);
  }
}
