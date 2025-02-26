package com.fasterxml.jackson.databind.type;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.ResolvedType;
import com.fasterxml.jackson.core.type.WritableTypeId;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import java.io.IOException;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class TypeBase extends JavaType implements JsonSerializable {
  private static final long serialVersionUID = 1L;
  
  private static final TypeBindings NO_BINDINGS = TypeBindings.emptyBindings();
  
  private static final JavaType[] NO_TYPES = new JavaType[0];
  
  protected final JavaType _superClass;
  
  protected final JavaType[] _superInterfaces;
  
  protected final TypeBindings _bindings;
  
  volatile transient String _canonicalName;
  
  protected TypeBase(Class<?> raw, TypeBindings bindings, JavaType superClass, JavaType[] superInts, int hash, Object valueHandler, Object typeHandler, boolean asStatic) {
    super(raw, hash, valueHandler, typeHandler, asStatic);
    this._bindings = (bindings == null) ? NO_BINDINGS : bindings;
    this._superClass = superClass;
    this._superInterfaces = superInts;
  }
  
  protected TypeBase(TypeBase base) {
    super(base);
    this._superClass = base._superClass;
    this._superInterfaces = base._superInterfaces;
    this._bindings = base._bindings;
  }
  
  public String toCanonical() {
    String str = this._canonicalName;
    if (str == null)
      str = buildCanonicalName(); 
    return str;
  }
  
  protected String buildCanonicalName() {
    return this._class.getName();
  }
  
  public TypeBindings getBindings() {
    return this._bindings;
  }
  
  public int containedTypeCount() {
    return this._bindings.size();
  }
  
  public JavaType containedType(int index) {
    return this._bindings.getBoundType(index);
  }
  
  @Deprecated
  public String containedTypeName(int index) {
    return this._bindings.getBoundName(index);
  }
  
  public JavaType getSuperClass() {
    return this._superClass;
  }
  
  public List<JavaType> getInterfaces() {
    if (this._superInterfaces == null)
      return Collections.emptyList(); 
    switch (this._superInterfaces.length) {
      case 0:
        return Collections.emptyList();
      case 1:
        return Collections.singletonList(this._superInterfaces[0]);
    } 
    return Arrays.asList(this._superInterfaces);
  }
  
  public final JavaType findSuperType(Class<?> rawTarget) {
    if (rawTarget == this._class)
      return this; 
    if (rawTarget.isInterface() && this._superInterfaces != null)
      for (int i = 0, count = this._superInterfaces.length; i < count; i++) {
        JavaType type = this._superInterfaces[i].findSuperType(rawTarget);
        if (type != null)
          return type; 
      }  
    if (this._superClass != null) {
      JavaType type = this._superClass.findSuperType(rawTarget);
      if (type != null)
        return type; 
    } 
    return null;
  }
  
  public JavaType[] findTypeParameters(Class<?> expType) {
    JavaType match = findSuperType(expType);
    if (match == null)
      return NO_TYPES; 
    return match.getBindings().typeParameterArray();
  }
  
  public void serializeWithType(JsonGenerator g, SerializerProvider provider, TypeSerializer typeSer) throws IOException {
    WritableTypeId typeIdDef = new WritableTypeId(this, JsonToken.VALUE_STRING);
    typeSer.writeTypePrefix(g, typeIdDef);
    serialize(g, provider);
    typeSer.writeTypeSuffix(g, typeIdDef);
  }
  
  public void serialize(JsonGenerator gen, SerializerProvider provider) throws IOException {
    gen.writeString(toCanonical());
  }
  
  protected static StringBuilder _classSignature(Class<?> cls, StringBuilder sb, boolean trailingSemicolon) {
    if (cls.isPrimitive()) {
      if (cls == boolean.class) {
        sb.append('Z');
      } else if (cls == byte.class) {
        sb.append('B');
      } else if (cls == short.class) {
        sb.append('S');
      } else if (cls == char.class) {
        sb.append('C');
      } else if (cls == int.class) {
        sb.append('I');
      } else if (cls == long.class) {
        sb.append('J');
      } else if (cls == float.class) {
        sb.append('F');
      } else if (cls == double.class) {
        sb.append('D');
      } else if (cls == void.class) {
        sb.append('V');
      } else {
        throw new IllegalStateException("Unrecognized primitive type: " + cls.getName());
      } 
    } else {
      sb.append('L');
      String name = cls.getName();
      for (int i = 0, len = name.length(); i < len; i++) {
        char c = name.charAt(i);
        if (c == '.')
          c = '/'; 
        sb.append(c);
      } 
      if (trailingSemicolon)
        sb.append(';'); 
    } 
    return sb;
  }
  
  protected static JavaType _bogusSuperClass(Class<?> cls) {
    Class<?> parent = cls.getSuperclass();
    if (parent == null)
      return null; 
    return TypeFactory.unknownType();
  }
  
  protected boolean _hasNTypeParameters(int count) {
    TypeVariable[] arrayOfTypeVariable = this._class.getTypeParameters();
    return (arrayOfTypeVariable.length == count);
  }
  
  public abstract StringBuilder getGenericSignature(StringBuilder paramStringBuilder);
  
  public abstract StringBuilder getErasedSignature(StringBuilder paramStringBuilder);
}
