package com.fasterxml.jackson.databind.type;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.Serializable;
import java.lang.reflect.TypeVariable;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TypeBindings implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private static final String[] NO_STRINGS = new String[0];
  
  private static final JavaType[] NO_TYPES = new JavaType[0];
  
  private static final TypeBindings EMPTY = new TypeBindings(NO_STRINGS, NO_TYPES, null);
  
  private final String[] _names;
  
  private final JavaType[] _types;
  
  private final String[] _unboundVariables;
  
  private final int _hashCode;
  
  private TypeBindings(String[] names, JavaType[] types, String[] uvars) {
    this._names = (names == null) ? NO_STRINGS : names;
    this._types = (types == null) ? NO_TYPES : types;
    if (this._names.length != this._types.length)
      throw new IllegalArgumentException("Mismatching names (" + this._names.length + "), types (" + this._types.length + ")"); 
    this._unboundVariables = uvars;
    this._hashCode = Arrays.hashCode((Object[])this._types);
  }
  
  public static TypeBindings emptyBindings() {
    return EMPTY;
  }
  
  protected Object readResolve() {
    if (this._names == null || this._names.length == 0)
      return EMPTY; 
    return this;
  }
  
  public static TypeBindings create(Class<?> erasedType, List<JavaType> typeList) {
    JavaType[] types = (typeList == null || typeList.isEmpty()) ? NO_TYPES : typeList.<JavaType>toArray(NO_TYPES);
    return create(erasedType, types);
  }
  
  public static TypeBindings create(Class<?> erasedType, JavaType[] types) {
    String[] names;
    if (types == null) {
      types = NO_TYPES;
    } else {
      switch (types.length) {
        case 1:
          return create(erasedType, types[0]);
        case 2:
          return create(erasedType, types[0], types[1]);
      } 
    } 
    TypeVariable[] arrayOfTypeVariable = (TypeVariable[])erasedType.getTypeParameters();
    if (arrayOfTypeVariable == null || arrayOfTypeVariable.length == 0) {
      names = NO_STRINGS;
    } else {
      int len = arrayOfTypeVariable.length;
      names = new String[len];
      for (int i = 0; i < len; i++)
        names[i] = arrayOfTypeVariable[i].getName(); 
    } 
    if (names.length != types.length)
      throw new IllegalArgumentException("Cannot create TypeBindings for class " + erasedType.getName() + " with " + types.length + " type parameter" + ((types.length == 1) ? "" : "s") + ": class expects " + names.length); 
    return new TypeBindings(names, types, null);
  }
  
  public static TypeBindings create(Class<?> erasedType, JavaType typeArg1) {
    TypeVariable[] arrayOfTypeVariable = (TypeVariable[])TypeParamStash.paramsFor1(erasedType);
    int varLen = (arrayOfTypeVariable == null) ? 0 : arrayOfTypeVariable.length;
    if (varLen != 1)
      throw new IllegalArgumentException("Cannot create TypeBindings for class " + erasedType.getName() + " with 1 type parameter: class expects " + varLen); 
    return new TypeBindings(new String[] { arrayOfTypeVariable[0].getName() }, new JavaType[] { typeArg1 }, null);
  }
  
  public static TypeBindings create(Class<?> erasedType, JavaType typeArg1, JavaType typeArg2) {
    TypeVariable[] arrayOfTypeVariable = (TypeVariable[])TypeParamStash.paramsFor2(erasedType);
    int varLen = (arrayOfTypeVariable == null) ? 0 : arrayOfTypeVariable.length;
    if (varLen != 2)
      throw new IllegalArgumentException("Cannot create TypeBindings for class " + erasedType.getName() + " with 2 type parameters: class expects " + varLen); 
    return new TypeBindings(new String[] { arrayOfTypeVariable[0].getName(), arrayOfTypeVariable[1].getName() }, new JavaType[] { typeArg1, typeArg2 }, null);
  }
  
  public static TypeBindings create(List<String> names, List<JavaType> types) {
    if (names == null || names.isEmpty() || types == null || types.isEmpty())
      return EMPTY; 
    return new TypeBindings(names.<String>toArray(NO_STRINGS), types.<JavaType>toArray(NO_TYPES), null);
  }
  
  public static TypeBindings createIfNeeded(Class<?> erasedType, JavaType typeArg1) {
    TypeVariable[] arrayOfTypeVariable = (TypeVariable[])erasedType.getTypeParameters();
    int varLen = (arrayOfTypeVariable == null) ? 0 : arrayOfTypeVariable.length;
    if (varLen == 0)
      return EMPTY; 
    if (varLen != 1)
      throw new IllegalArgumentException("Cannot create TypeBindings for class " + erasedType.getName() + " with 1 type parameter: class expects " + varLen); 
    return new TypeBindings(new String[] { arrayOfTypeVariable[0].getName() }, new JavaType[] { typeArg1 }, null);
  }
  
  public static TypeBindings createIfNeeded(Class<?> erasedType, JavaType[] types) {
    TypeVariable[] arrayOfTypeVariable = (TypeVariable[])erasedType.getTypeParameters();
    if (arrayOfTypeVariable == null || arrayOfTypeVariable.length == 0)
      return EMPTY; 
    if (types == null)
      types = NO_TYPES; 
    int len = arrayOfTypeVariable.length;
    String[] names = new String[len];
    for (int i = 0; i < len; i++)
      names[i] = arrayOfTypeVariable[i].getName(); 
    if (names.length != types.length)
      throw new IllegalArgumentException("Cannot create TypeBindings for class " + erasedType.getName() + " with " + types.length + " type parameter" + ((types.length == 1) ? "" : "s") + ": class expects " + names.length); 
    return new TypeBindings(names, types, null);
  }
  
  public TypeBindings withUnboundVariable(String name) {
    int len = (this._unboundVariables == null) ? 0 : this._unboundVariables.length;
    String[] names = (len == 0) ? new String[1] : Arrays.<String>copyOf(this._unboundVariables, len + 1);
    names[len] = name;
    return new TypeBindings(this._names, this._types, names);
  }
  
  public TypeBindings withoutVariable(String name) {
    int index = Arrays.<String>asList(this._names).indexOf(name);
    if (index == -1)
      return this; 
    String[] newNames = Arrays.<String>copyOf(this._names, this._names.length - 1);
    JavaType[] newTypes = Arrays.<JavaType>copyOf(this._types, this._types.length - 1);
    if (index != newNames.length) {
      System.arraycopy(this._names, index + 1, newNames, index, newNames.length - index);
      System.arraycopy(this._types, index + 1, newTypes, index, newTypes.length - index);
    } 
    return new TypeBindings(newNames, newTypes, this._unboundVariables);
  }
  
  public JavaType findBoundType(String name) {
    for (int i = 0, len = this._names.length; i < len; i++) {
      if (name.equals(this._names[i])) {
        JavaType t = this._types[i];
        if (t instanceof ResolvedRecursiveType) {
          ResolvedRecursiveType rrt = (ResolvedRecursiveType)t;
          JavaType t2 = rrt.getSelfReferencedType();
          if (t2 != null)
            t = t2; 
        } 
        return t;
      } 
    } 
    return null;
  }
  
  private boolean invalidCacheKey() {
    for (JavaType type : this._types) {
      if (type instanceof IdentityEqualityType)
        return true; 
    } 
    return false;
  }
  
  public boolean isEmpty() {
    return (this._types.length == 0);
  }
  
  public int size() {
    return this._types.length;
  }
  
  public String getBoundName(int index) {
    if (index < 0 || index >= this._names.length)
      return null; 
    return this._names[index];
  }
  
  public JavaType getBoundType(int index) {
    if (index < 0 || index >= this._types.length)
      return null; 
    return this._types[index];
  }
  
  public List<JavaType> getTypeParameters() {
    if (this._types.length == 0)
      return Collections.emptyList(); 
    return Arrays.asList(this._types);
  }
  
  public boolean hasUnbound(String name) {
    if (this._unboundVariables != null)
      for (int i = this._unboundVariables.length; --i >= 0;) {
        if (name.equals(this._unboundVariables[i]))
          return true; 
      }  
    return false;
  }
  
  public Object asKey(Class<?> rawBase) {
    if (invalidCacheKey())
      return null; 
    return new AsKey(rawBase, this._types, this._hashCode);
  }
  
  public String toString() {
    if (this._types.length == 0)
      return "<>"; 
    StringBuilder sb = new StringBuilder();
    sb.append('<');
    for (int i = 0, len = this._types.length; i < len; i++) {
      if (i > 0)
        sb.append(','); 
      String sig = this._types[i].getGenericSignature();
      sb.append(sig);
    } 
    sb.append('>');
    return sb.toString();
  }
  
  public int hashCode() {
    return this._hashCode;
  }
  
  public boolean equals(Object o) {
    if (o == this)
      return true; 
    if (!ClassUtil.hasClass(o, getClass()))
      return false; 
    TypeBindings other = (TypeBindings)o;
    return (this._hashCode == other._hashCode && Arrays.equals((Object[])this._types, (Object[])other._types));
  }
  
  protected JavaType[] typeParameterArray() {
    return this._types;
  }
  
  static class TypeParamStash {
    private static final TypeVariable<?>[] VARS_ABSTRACT_LIST = (TypeVariable<?>[])AbstractList.class.getTypeParameters();
    
    private static final TypeVariable<?>[] VARS_COLLECTION = (TypeVariable<?>[])Collection.class.getTypeParameters();
    
    private static final TypeVariable<?>[] VARS_ITERABLE = (TypeVariable<?>[])Iterable.class.getTypeParameters();
    
    private static final TypeVariable<?>[] VARS_LIST = (TypeVariable<?>[])List.class.getTypeParameters();
    
    private static final TypeVariable<?>[] VARS_ARRAY_LIST = (TypeVariable<?>[])ArrayList.class.getTypeParameters();
    
    private static final TypeVariable<?>[] VARS_MAP = (TypeVariable<?>[])Map.class.getTypeParameters();
    
    private static final TypeVariable<?>[] VARS_HASH_MAP = (TypeVariable<?>[])HashMap.class.getTypeParameters();
    
    private static final TypeVariable<?>[] VARS_LINKED_HASH_MAP = (TypeVariable<?>[])LinkedHashMap.class.getTypeParameters();
    
    public static TypeVariable<?>[] paramsFor1(Class<?> erasedType) {
      if (erasedType == Collection.class)
        return VARS_COLLECTION; 
      if (erasedType == List.class)
        return VARS_LIST; 
      if (erasedType == ArrayList.class)
        return VARS_ARRAY_LIST; 
      if (erasedType == AbstractList.class)
        return VARS_ABSTRACT_LIST; 
      if (erasedType == Iterable.class)
        return VARS_ITERABLE; 
      return (TypeVariable<?>[])erasedType.getTypeParameters();
    }
    
    public static TypeVariable<?>[] paramsFor2(Class<?> erasedType) {
      if (erasedType == Map.class)
        return VARS_MAP; 
      if (erasedType == HashMap.class)
        return VARS_HASH_MAP; 
      if (erasedType == LinkedHashMap.class)
        return VARS_LINKED_HASH_MAP; 
      return (TypeVariable<?>[])erasedType.getTypeParameters();
    }
  }
  
  static final class AsKey {
    private final Class<?> _raw;
    
    private final JavaType[] _params;
    
    private final int _hash;
    
    public AsKey(Class<?> raw, JavaType[] params, int hash) {
      this._raw = raw;
      this._params = params;
      this._hash = 31 * raw.hashCode() + hash;
    }
    
    public int hashCode() {
      return this._hash;
    }
    
    public boolean equals(Object o) {
      if (o == this)
        return true; 
      if (o == null)
        return false; 
      if (o.getClass() != getClass())
        return false; 
      AsKey other = (AsKey)o;
      if (this._hash == other._hash && this._raw == other._raw) {
        JavaType[] otherParams = other._params;
        int len = this._params.length;
        if (len == otherParams.length) {
          for (int i = 0; i < len; i++) {
            if (!this._params[i].equals(otherParams[i]))
              return false; 
          } 
          return true;
        } 
      } 
      return false;
    }
    
    public String toString() {
      return this._raw.getName() + "<>";
    }
  }
}
