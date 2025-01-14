package org.openjdk.nashorn.internal.runtime;

import java.io.Serializable;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.SwitchPoint;
import java.util.Objects;

public abstract class Property implements Serializable {
  public static final int WRITABLE_ENUMERABLE_CONFIGURABLE = 0;
  
  public static final int NOT_WRITABLE = 1;
  
  public static final int NOT_ENUMERABLE = 2;
  
  public static final int NOT_CONFIGURABLE = 4;
  
  private static final int MODIFY_MASK = 7;
  
  public static final int IS_PARAMETER = 8;
  
  public static final int HAS_ARGUMENTS = 16;
  
  public static final int IS_FUNCTION_DECLARATION = 32;
  
  public static final int IS_NASGEN_PRIMITIVE = 64;
  
  public static final int IS_BUILTIN = 128;
  
  public static final int IS_BOUND = 256;
  
  public static final int NEEDS_DECLARATION = 512;
  
  public static final int IS_LEXICAL_BINDING = 1024;
  
  public static final int DUAL_FIELDS = 2048;
  
  public static final int IS_ACCESSOR_PROPERTY = 4096;
  
  private final Object key;
  
  private int flags;
  
  private final int slot;
  
  private Class<?> type;
  
  protected transient SwitchPoint builtinSwitchPoint;
  
  private static final long serialVersionUID = 2099814273074501176L;
  
  Property(Object key, int flags, int slot) {
    assert key != null;
    this.key = key;
    this.flags = flags;
    this.slot = slot;
  }
  
  Property(Property property, int flags) {
    this.key = property.key;
    this.slot = property.slot;
    this.builtinSwitchPoint = property.builtinSwitchPoint;
    this.flags = flags;
  }
  
  public abstract Property copy();
  
  public abstract Property copy(Class<?> paramClass);
  
  static int mergeFlags(PropertyDescriptor oldDesc, PropertyDescriptor newDesc) {
    int propFlags = 0;
    boolean value = newDesc.has("configurable") ? newDesc.isConfigurable() : oldDesc.isConfigurable();
    if (!value)
      propFlags |= 0x4; 
    value = newDesc.has("enumerable") ? newDesc.isEnumerable() : oldDesc.isEnumerable();
    if (!value)
      propFlags |= 0x2; 
    value = newDesc.has("writable") ? newDesc.isWritable() : oldDesc.isWritable();
    if (!value)
      propFlags |= 0x1; 
    return propFlags;
  }
  
  public final void setBuiltinSwitchPoint(SwitchPoint sp) {
    this.builtinSwitchPoint = sp;
  }
  
  public final SwitchPoint getBuiltinSwitchPoint() {
    return this.builtinSwitchPoint;
  }
  
  public boolean isBuiltin() {
    return (this.builtinSwitchPoint != null && !this.builtinSwitchPoint.hasBeenInvalidated());
  }
  
  static int toFlags(PropertyDescriptor desc) {
    int propFlags = 0;
    if (!desc.isConfigurable())
      propFlags |= 0x4; 
    if (!desc.isEnumerable())
      propFlags |= 0x2; 
    if (!desc.isWritable())
      propFlags |= 0x1; 
    return propFlags;
  }
  
  public boolean hasGetterFunction(ScriptObject obj) {
    return false;
  }
  
  public boolean hasSetterFunction(ScriptObject obj) {
    return false;
  }
  
  public boolean isWritable() {
    return ((this.flags & 0x1) == 0);
  }
  
  public boolean isConfigurable() {
    return ((this.flags & 0x4) == 0);
  }
  
  public boolean isEnumerable() {
    return ((this.flags & 0x2) == 0);
  }
  
  public boolean isParameter() {
    return ((this.flags & 0x8) != 0);
  }
  
  public boolean hasArguments() {
    return ((this.flags & 0x10) != 0);
  }
  
  public boolean isSpill() {
    return false;
  }
  
  public boolean isBound() {
    return ((this.flags & 0x100) != 0);
  }
  
  public boolean needsDeclaration() {
    return ((this.flags & 0x200) != 0);
  }
  
  public Property addFlags(int propertyFlags) {
    if ((this.flags & propertyFlags) != propertyFlags) {
      Property cloned = copy();
      cloned.flags |= propertyFlags;
      return cloned;
    } 
    return this;
  }
  
  public int getFlags() {
    return this.flags;
  }
  
  public Property removeFlags(int propertyFlags) {
    if ((this.flags & propertyFlags) != 0) {
      Property cloned = copy();
      cloned.flags &= propertyFlags ^ 0xFFFFFFFF;
      return cloned;
    } 
    return this;
  }
  
  public Property setFlags(int propertyFlags) {
    if (this.flags != propertyFlags) {
      Property cloned = copy();
      cloned.flags &= 0xFFFFFFF8;
      cloned.flags |= propertyFlags & 0x7;
      return cloned;
    } 
    return this;
  }
  
  public abstract MethodHandle getGetter(Class<?> paramClass);
  
  public abstract MethodHandle getOptimisticGetter(Class<?> paramClass, int paramInt);
  
  abstract void initMethodHandles(Class<?> paramClass);
  
  public Object getKey() {
    return this.key;
  }
  
  public int getSlot() {
    return this.slot;
  }
  
  public abstract int getIntValue(ScriptObject paramScriptObject1, ScriptObject paramScriptObject2);
  
  public abstract double getDoubleValue(ScriptObject paramScriptObject1, ScriptObject paramScriptObject2);
  
  public abstract Object getObjectValue(ScriptObject paramScriptObject1, ScriptObject paramScriptObject2);
  
  public abstract void setValue(ScriptObject paramScriptObject1, ScriptObject paramScriptObject2, int paramInt, boolean paramBoolean);
  
  public abstract void setValue(ScriptObject paramScriptObject1, ScriptObject paramScriptObject2, double paramDouble, boolean paramBoolean);
  
  public abstract void setValue(ScriptObject paramScriptObject1, ScriptObject paramScriptObject2, Object paramObject, boolean paramBoolean);
  
  public abstract boolean hasNativeSetter();
  
  public abstract MethodHandle getSetter(Class<?> paramClass, PropertyMap paramPropertyMap);
  
  public ScriptFunction getGetterFunction(ScriptObject obj) {
    return null;
  }
  
  public ScriptFunction getSetterFunction(ScriptObject obj) {
    return null;
  }
  
  public int hashCode() {
    Class<?> t = getLocalType();
    return Objects.hashCode(this.key) ^ this.flags ^ getSlot() ^ ((t == null) ? 0 : t.hashCode());
  }
  
  public boolean equals(Object other) {
    if (this == other)
      return true; 
    if (other == null || getClass() != other.getClass())
      return false; 
    Property otherProperty = (Property)other;
    return (equalsWithoutType(otherProperty) && 
      getLocalType() == otherProperty.getLocalType());
  }
  
  boolean equalsWithoutType(Property otherProperty) {
    return (getFlags() == otherProperty.getFlags() && 
      getSlot() == otherProperty.getSlot() && 
      getKey().equals(otherProperty.getKey()));
  }
  
  private static String type(Class<?> type) {
    if (type == null)
      return "undef"; 
    if (type == int.class)
      return "i"; 
    if (type == double.class)
      return "d"; 
    return "o";
  }
  
  public final String toStringShort() {
    StringBuilder sb = new StringBuilder();
    Class<?> t = getLocalType();
    sb.append(getKey()).append(" (").append(type(t)).append(')');
    return sb.toString();
  }
  
  private static String indent(String str, int indent) {
    return str + str;
  }
  
  public String toString() {
    StringBuilder sb = new StringBuilder();
    Class<?> t = getLocalType();
    sb.append(indent(getKey().toString(), 20))
      .append(" id=")
      .append(Debug.id(this))
      .append(" (0x")
      .append(indent(Integer.toHexString(this.flags), 4))
      .append(") ")
      .append(getClass().getSimpleName())
      .append(" {")
      .append(indent(type(t), 5))
      .append('}');
    if (this.slot != -1)
      sb.append(" [")
        .append("slot=")
        .append(this.slot)
        .append(']'); 
    return sb.toString();
  }
  
  public final Class<?> getType() {
    return this.type;
  }
  
  public final void setType(Class<?> type) {
    assert type != boolean.class : "no boolean storage support yet - fix this";
    this.type = (type == null) ? null : (type.isPrimitive() ? type : Object.class);
  }
  
  protected Class<?> getLocalType() {
    return getType();
  }
  
  public boolean canChangeType() {
    return false;
  }
  
  public boolean isFunctionDeclaration() {
    return ((this.flags & 0x20) != 0);
  }
  
  public boolean isLexicalBinding() {
    return ((this.flags & 0x400) != 0);
  }
  
  public boolean hasDualFields() {
    return ((this.flags & 0x800) != 0);
  }
  
  public boolean isAccessorProperty() {
    return ((this.flags & 0x1000) != 0);
  }
}
