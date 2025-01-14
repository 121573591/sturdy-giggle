package org.openjdk.nashorn.internal.codegen.types;

import org.objectweb.asm.MethodVisitor;
import org.openjdk.nashorn.internal.codegen.CompilerConstants;

public final class BooleanType extends Type {
  private static final long serialVersionUID = 1L;
  
  private static final CompilerConstants.Call VALUE_OF = CompilerConstants.staticCallNoLookup(Boolean.class, "valueOf", Boolean.class, new Class[] { boolean.class });
  
  private static final CompilerConstants.Call TO_STRING = CompilerConstants.staticCallNoLookup(Boolean.class, "toString", String.class, new Class[] { boolean.class });
  
  protected BooleanType() {
    super("boolean", boolean.class, 1, 1);
  }
  
  public Type nextWider() {
    return INT;
  }
  
  public Class<?> getBoxedType() {
    return Boolean.class;
  }
  
  public char getBytecodeStackType() {
    return 'I';
  }
  
  public Type loadUndefined(MethodVisitor method) {
    method.visitLdcInsn(Integer.valueOf(0));
    return BOOLEAN;
  }
  
  public Type loadForcedInitializer(MethodVisitor method) {
    method.visitInsn(3);
    return BOOLEAN;
  }
  
  public void _return(MethodVisitor method) {
    method.visitInsn(172);
  }
  
  public Type load(MethodVisitor method, int slot) {
    assert slot != -1;
    method.visitVarInsn(21, slot);
    return BOOLEAN;
  }
  
  public void store(MethodVisitor method, int slot) {
    assert slot != -1;
    method.visitVarInsn(54, slot);
  }
  
  public Type ldc(MethodVisitor method, Object c) {
    assert c instanceof Boolean;
    method.visitInsn(((Boolean)c).booleanValue() ? 4 : 3);
    return BOOLEAN;
  }
  
  public Type convert(MethodVisitor method, Type to) {
    if (isEquivalentTo(to))
      return to; 
    if (to.isNumber()) {
      method.visitInsn(135);
    } else if (to.isLong()) {
      method.visitInsn(133);
    } else if (!to.isInteger()) {
      if (to.isString()) {
        invokestatic(method, TO_STRING);
      } else if (to.isObject()) {
        invokestatic(method, VALUE_OF);
      } else {
        throw new UnsupportedOperationException("Illegal conversion " + this + " -> " + to);
      } 
    } 
    return to;
  }
  
  public Type add(MethodVisitor method, int programPoint) {
    return Type.INT.add(method, programPoint);
  }
}
