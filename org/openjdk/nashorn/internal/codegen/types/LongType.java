package org.openjdk.nashorn.internal.codegen.types;

import org.objectweb.asm.MethodVisitor;
import org.openjdk.nashorn.internal.codegen.CompilerConstants;
import org.openjdk.nashorn.internal.runtime.JSType;

class LongType extends Type {
  private static final long serialVersionUID = 1L;
  
  private static final CompilerConstants.Call VALUE_OF = CompilerConstants.staticCallNoLookup(Long.class, "valueOf", Long.class, new Class[] { long.class });
  
  protected LongType(String name) {
    super(name, long.class, 3, 2);
  }
  
  protected LongType() {
    this("long");
  }
  
  public Type nextWider() {
    return NUMBER;
  }
  
  public Class<?> getBoxedType() {
    return Long.class;
  }
  
  public char getBytecodeStackType() {
    return 'J';
  }
  
  public Type load(MethodVisitor method, int slot) {
    assert slot != -1;
    method.visitVarInsn(22, slot);
    return LONG;
  }
  
  public void store(MethodVisitor method, int slot) {
    assert slot != -1;
    method.visitVarInsn(55, slot);
  }
  
  public Type ldc(MethodVisitor method, Object c) {
    assert c instanceof Long;
    long value = ((Long)c).longValue();
    if (value == 0L) {
      method.visitInsn(9);
    } else if (value == 1L) {
      method.visitInsn(10);
    } else {
      method.visitLdcInsn(c);
    } 
    return Type.LONG;
  }
  
  public Type convert(MethodVisitor method, Type to) {
    if (isEquivalentTo(to))
      return to; 
    if (to.isNumber()) {
      method.visitInsn(138);
    } else if (to.isInteger()) {
      invokestatic(method, JSType.TO_INT32_L);
    } else if (to.isBoolean()) {
      method.visitInsn(136);
    } else if (to.isObject()) {
      invokestatic(method, VALUE_OF);
    } else {
      assert false : "Illegal conversion " + this + " -> " + to;
    } 
    return to;
  }
  
  public Type add(MethodVisitor method, int programPoint) {
    throw new UnsupportedOperationException("add");
  }
  
  public void _return(MethodVisitor method) {
    method.visitInsn(173);
  }
  
  public Type loadUndefined(MethodVisitor method) {
    method.visitLdcInsn(Long.valueOf(0L));
    return LONG;
  }
  
  public Type loadForcedInitializer(MethodVisitor method) {
    method.visitInsn(9);
    return LONG;
  }
}
