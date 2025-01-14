package org.openjdk.nashorn.internal.codegen.types;

import org.objectweb.asm.MethodVisitor;

interface BytecodeNumericOps {
  Type neg(MethodVisitor paramMethodVisitor, int paramInt);
  
  Type sub(MethodVisitor paramMethodVisitor, int paramInt);
  
  Type mul(MethodVisitor paramMethodVisitor, int paramInt);
  
  Type div(MethodVisitor paramMethodVisitor, int paramInt);
  
  Type rem(MethodVisitor paramMethodVisitor, int paramInt);
  
  Type cmp(MethodVisitor paramMethodVisitor, boolean paramBoolean);
}
