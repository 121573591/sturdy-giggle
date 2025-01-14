package org.openjdk.nashorn.internal.codegen.types;

import java.lang.invoke.MethodHandle;
import org.objectweb.asm.MethodVisitor;
import org.openjdk.nashorn.internal.codegen.CompilerConstants;
import org.openjdk.nashorn.internal.runtime.JSType;
import org.openjdk.nashorn.internal.runtime.ScriptRuntime;
import org.openjdk.nashorn.internal.runtime.Undefined;

class ObjectType extends Type {
  private static final long serialVersionUID = 1L;
  
  protected ObjectType() {
    this(Object.class);
  }
  
  protected ObjectType(Class<?> clazz) {
    super("object", clazz, 
        
        (clazz == Object.class) ? 20 : 10, 1);
  }
  
  public String toString() {
    return "object" + ((getTypeClass() != Object.class) ? ("<type=" + getTypeClass().getSimpleName() + ">") : "");
  }
  
  public String getShortDescriptor() {
    return (getTypeClass() == Object.class) ? "Object" : getTypeClass().getSimpleName();
  }
  
  public Type add(MethodVisitor method, int programPoint) {
    invokestatic(method, ScriptRuntime.ADD);
    return Type.OBJECT;
  }
  
  public Type load(MethodVisitor method, int slot) {
    assert slot != -1;
    method.visitVarInsn(25, slot);
    return this;
  }
  
  public void store(MethodVisitor method, int slot) {
    assert slot != -1;
    method.visitVarInsn(58, slot);
  }
  
  public Type loadUndefined(MethodVisitor method) {
    method.visitFieldInsn(178, CompilerConstants.className(ScriptRuntime.class), "UNDEFINED", CompilerConstants.typeDescriptor(Undefined.class));
    return UNDEFINED;
  }
  
  public Type loadForcedInitializer(MethodVisitor method) {
    method.visitInsn(1);
    return OBJECT;
  }
  
  public Type loadEmpty(MethodVisitor method) {
    method.visitFieldInsn(178, CompilerConstants.className(ScriptRuntime.class), "EMPTY", CompilerConstants.typeDescriptor(Undefined.class));
    return UNDEFINED;
  }
  
  public Type ldc(MethodVisitor method, Object c) {
    if (c == null) {
      method.visitInsn(1);
    } else {
      if (c instanceof Undefined)
        return loadUndefined(method); 
      if (c instanceof String) {
        method.visitLdcInsn(c);
        return STRING;
      } 
      if (c instanceof org.objectweb.asm.Handle) {
        method.visitLdcInsn(c);
        return Type.typeFor(MethodHandle.class);
      } 
      throw new UnsupportedOperationException("implementation missing for class " + c.getClass() + " value=" + c);
    } 
    return Type.OBJECT;
  }
  
  public Type convert(MethodVisitor method, Type to) {
    boolean toString = to.isString();
    if (!toString) {
      if (to.isArray()) {
        Type elemType = ((ArrayType)to).getElementType();
        if (elemType.isString()) {
          method.visitTypeInsn(192, CompilerConstants.className(String[].class));
        } else if (elemType.isNumber()) {
          method.visitTypeInsn(192, CompilerConstants.className(double[].class));
        } else if (elemType.isLong()) {
          method.visitTypeInsn(192, CompilerConstants.className(long[].class));
        } else if (elemType.isInteger()) {
          method.visitTypeInsn(192, CompilerConstants.className(int[].class));
        } else {
          method.visitTypeInsn(192, CompilerConstants.className(Object[].class));
        } 
        return to;
      } 
      if (to.isObject()) {
        Class<?> toClass = to.getTypeClass();
        if (!toClass.isAssignableFrom(getTypeClass()))
          method.visitTypeInsn(192, CompilerConstants.className(toClass)); 
        return to;
      } 
    } else if (isString()) {
      return to;
    } 
    if (to.isInteger()) {
      invokestatic(method, JSType.TO_INT32);
    } else if (to.isNumber()) {
      invokestatic(method, JSType.TO_NUMBER);
    } else if (to.isLong()) {
      invokestatic(method, JSType.TO_LONG);
    } else if (to.isBoolean()) {
      invokestatic(method, JSType.TO_BOOLEAN);
    } else if (to.isString()) {
      invokestatic(method, JSType.TO_PRIMITIVE_TO_STRING);
    } else if (to.isCharSequence()) {
      invokestatic(method, JSType.TO_PRIMITIVE_TO_CHARSEQUENCE);
    } else {
      throw new UnsupportedOperationException("Illegal conversion " + this + " -> " + to + " " + isString() + " " + toString);
    } 
    return to;
  }
  
  public void _return(MethodVisitor method) {
    method.visitInsn(176);
  }
  
  public char getBytecodeStackType() {
    return 'A';
  }
}
