package org.openjdk.nashorn.internal.codegen.types;

import org.objectweb.asm.MethodVisitor;

interface BytecodeArrayOps {
  Type aload(MethodVisitor paramMethodVisitor);
  
  void astore(MethodVisitor paramMethodVisitor);
  
  Type arraylength(MethodVisitor paramMethodVisitor);
  
  Type newarray(MethodVisitor paramMethodVisitor);
  
  Type newarray(MethodVisitor paramMethodVisitor, int paramInt);
}
