package org.openjdk.nashorn.internal.codegen.types;

import org.objectweb.asm.MethodVisitor;
import org.openjdk.nashorn.internal.codegen.CompilerConstants;
import org.openjdk.nashorn.internal.runtime.JSType;

class IntType extends BitwiseType {
  private static final long serialVersionUID = 1L;
  
  private static final CompilerConstants.Call TO_STRING = CompilerConstants.staticCallNoLookup(Integer.class, "toString", String.class, new Class[] { int.class });
  
  private static final CompilerConstants.Call VALUE_OF = CompilerConstants.staticCallNoLookup(Integer.class, "valueOf", Integer.class, new Class[] { int.class });
  
  protected IntType() {
    super("int", int.class, 2, 1);
  }
  
  public Type nextWider() {
    return NUMBER;
  }
  
  public Class<?> getBoxedType() {
    return Integer.class;
  }
  
  public char getBytecodeStackType() {
    return 'I';
  }
  
  public Type ldc(MethodVisitor method, Object c) {
    assert c instanceof Integer;
    int value = ((Integer)c).intValue();
    switch (value) {
      case -1:
        method.visitInsn(2);
        return Type.INT;
      case 0:
        method.visitInsn(3);
        return Type.INT;
      case 1:
        method.visitInsn(4);
        return Type.INT;
      case 2:
        method.visitInsn(5);
        return Type.INT;
      case 3:
        method.visitInsn(6);
        return Type.INT;
      case 4:
        method.visitInsn(7);
        return Type.INT;
      case 5:
        method.visitInsn(8);
        return Type.INT;
    } 
    if (value == (byte)value) {
      method.visitIntInsn(16, value);
    } else if (value == (short)value) {
      method.visitIntInsn(17, value);
    } else {
      method.visitLdcInsn(c);
    } 
    return Type.INT;
  }
  
  public Type convert(MethodVisitor method, Type to) {
    if (to.isEquivalentTo(this))
      return to; 
    if (to.isNumber()) {
      method.visitInsn(135);
    } else if (to.isLong()) {
      method.visitInsn(133);
    } else if (to.isBoolean()) {
      invokestatic(method, JSType.TO_BOOLEAN_I);
    } else if (to.isString()) {
      invokestatic(method, TO_STRING);
    } else if (to.isObject()) {
      invokestatic(method, VALUE_OF);
    } else {
      throw new UnsupportedOperationException("Illegal conversion " + this + " -> " + to);
    } 
    return to;
  }
  
  public Type add(MethodVisitor method, int programPoint) {
    if (programPoint == -1) {
      method.visitInsn(96);
    } else {
      ldc(method, Integer.valueOf(programPoint));
      JSType.ADD_EXACT.invoke(method);
    } 
    return INT;
  }
  
  public Type shr(MethodVisitor method) {
    method.visitInsn(124);
    return INT;
  }
  
  public Type sar(MethodVisitor method) {
    method.visitInsn(122);
    return INT;
  }
  
  public Type shl(MethodVisitor method) {
    method.visitInsn(120);
    return INT;
  }
  
  public Type and(MethodVisitor method) {
    method.visitInsn(126);
    return INT;
  }
  
  public Type or(MethodVisitor method) {
    method.visitInsn(128);
    return INT;
  }
  
  public Type xor(MethodVisitor method) {
    method.visitInsn(130);
    return INT;
  }
  
  public Type load(MethodVisitor method, int slot) {
    assert slot != -1;
    method.visitVarInsn(21, slot);
    return INT;
  }
  
  public void store(MethodVisitor method, int slot) {
    assert slot != -1;
    method.visitVarInsn(54, slot);
  }
  
  public Type sub(MethodVisitor method, int programPoint) {
    if (programPoint == -1) {
      method.visitInsn(100);
    } else {
      ldc(method, Integer.valueOf(programPoint));
      JSType.SUB_EXACT.invoke(method);
    } 
    return INT;
  }
  
  public Type mul(MethodVisitor method, int programPoint) {
    if (programPoint == -1) {
      method.visitInsn(104);
    } else {
      ldc(method, Integer.valueOf(programPoint));
      JSType.MUL_EXACT.invoke(method);
    } 
    return INT;
  }
  
  public Type div(MethodVisitor method, int programPoint) {
    if (programPoint == -1) {
      JSType.DIV_ZERO.invoke(method);
    } else {
      ldc(method, Integer.valueOf(programPoint));
      JSType.DIV_EXACT.invoke(method);
    } 
    return INT;
  }
  
  public Type rem(MethodVisitor method, int programPoint) {
    if (programPoint == -1) {
      JSType.REM_ZERO.invoke(method);
    } else {
      ldc(method, Integer.valueOf(programPoint));
      JSType.REM_EXACT.invoke(method);
    } 
    return INT;
  }
  
  public Type neg(MethodVisitor method, int programPoint) {
    if (programPoint == -1) {
      method.visitInsn(116);
    } else {
      ldc(method, Integer.valueOf(programPoint));
      JSType.NEGATE_EXACT.invoke(method);
    } 
    return INT;
  }
  
  public void _return(MethodVisitor method) {
    method.visitInsn(172);
  }
  
  public Type loadUndefined(MethodVisitor method) {
    method.visitLdcInsn(Integer.valueOf(0));
    return INT;
  }
  
  public Type loadForcedInitializer(MethodVisitor method) {
    method.visitInsn(3);
    return INT;
  }
  
  public Type cmp(MethodVisitor method, boolean isCmpG) {
    throw new UnsupportedOperationException("cmp" + (isCmpG ? 103 : 108));
  }
  
  public Type cmp(MethodVisitor method) {
    throw new UnsupportedOperationException("cmp");
  }
}
