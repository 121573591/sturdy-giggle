package org.openjdk.nashorn.internal.codegen.types;

import org.objectweb.asm.MethodVisitor;
import org.openjdk.nashorn.internal.codegen.CompilerConstants;
import org.openjdk.nashorn.internal.runtime.JSType;

class NumberType extends NumericType {
  private static final long serialVersionUID = 1L;
  
  private static final CompilerConstants.Call VALUE_OF = CompilerConstants.staticCallNoLookup(Double.class, "valueOf", Double.class, new Class[] { double.class });
  
  protected NumberType() {
    super("double", double.class, 4, 2);
  }
  
  public Type nextWider() {
    return OBJECT;
  }
  
  public Class<?> getBoxedType() {
    return Double.class;
  }
  
  public char getBytecodeStackType() {
    return 'D';
  }
  
  public Type cmp(MethodVisitor method, boolean isCmpG) {
    method.visitInsn(isCmpG ? 152 : 151);
    return INT;
  }
  
  public Type load(MethodVisitor method, int slot) {
    assert slot != -1;
    method.visitVarInsn(24, slot);
    return NUMBER;
  }
  
  public void store(MethodVisitor method, int slot) {
    assert slot != -1;
    method.visitVarInsn(57, slot);
  }
  
  public Type loadUndefined(MethodVisitor method) {
    method.visitLdcInsn(Double.valueOf(Double.NaN));
    return NUMBER;
  }
  
  public Type loadForcedInitializer(MethodVisitor method) {
    method.visitInsn(14);
    return NUMBER;
  }
  
  public Type ldc(MethodVisitor method, Object c) {
    assert c instanceof Double;
    double value = ((Double)c).doubleValue();
    if (Double.doubleToLongBits(value) == 0L) {
      method.visitInsn(14);
    } else if (value == 1.0D) {
      method.visitInsn(15);
    } else {
      method.visitLdcInsn(Double.valueOf(value));
    } 
    return NUMBER;
  }
  
  public Type convert(MethodVisitor method, Type to) {
    if (isEquivalentTo(to))
      return null; 
    if (to.isInteger()) {
      invokestatic(method, JSType.TO_INT32_D);
    } else if (to.isLong()) {
      invokestatic(method, JSType.TO_LONG_D);
    } else if (to.isBoolean()) {
      invokestatic(method, JSType.TO_BOOLEAN_D);
    } else if (to.isString()) {
      invokestatic(method, JSType.TO_STRING_D);
    } else if (to.isObject()) {
      invokestatic(method, VALUE_OF);
    } else {
      throw new UnsupportedOperationException("Illegal conversion " + this + " -> " + to);
    } 
    return to;
  }
  
  public Type add(MethodVisitor method, int programPoint) {
    method.visitInsn(99);
    return NUMBER;
  }
  
  public Type sub(MethodVisitor method, int programPoint) {
    method.visitInsn(103);
    return NUMBER;
  }
  
  public Type mul(MethodVisitor method, int programPoint) {
    method.visitInsn(107);
    return NUMBER;
  }
  
  public Type div(MethodVisitor method, int programPoint) {
    method.visitInsn(111);
    return NUMBER;
  }
  
  public Type rem(MethodVisitor method, int programPoint) {
    method.visitInsn(115);
    return NUMBER;
  }
  
  public Type neg(MethodVisitor method, int programPoint) {
    method.visitInsn(119);
    return NUMBER;
  }
  
  public void _return(MethodVisitor method) {
    method.visitInsn(175);
  }
}
