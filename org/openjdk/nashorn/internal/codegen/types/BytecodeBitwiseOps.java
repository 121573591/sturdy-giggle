package org.openjdk.nashorn.internal.codegen.types;

import org.objectweb.asm.MethodVisitor;

interface BytecodeBitwiseOps {
  Type shr(MethodVisitor paramMethodVisitor);
  
  Type sar(MethodVisitor paramMethodVisitor);
  
  Type shl(MethodVisitor paramMethodVisitor);
  
  Type and(MethodVisitor paramMethodVisitor);
  
  Type or(MethodVisitor paramMethodVisitor);
  
  Type xor(MethodVisitor paramMethodVisitor);
  
  Type cmp(MethodVisitor paramMethodVisitor);
}
