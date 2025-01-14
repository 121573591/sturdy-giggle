package org.openjdk.nashorn.internal.codegen.types;

import org.objectweb.asm.MethodVisitor;

interface BytecodeOps {
  Type dup(MethodVisitor paramMethodVisitor, int paramInt);
  
  Type pop(MethodVisitor paramMethodVisitor);
  
  Type swap(MethodVisitor paramMethodVisitor, Type paramType);
  
  Type add(MethodVisitor paramMethodVisitor, int paramInt);
  
  Type load(MethodVisitor paramMethodVisitor, int paramInt);
  
  void store(MethodVisitor paramMethodVisitor, int paramInt);
  
  Type ldc(MethodVisitor paramMethodVisitor, Object paramObject);
  
  Type loadUndefined(MethodVisitor paramMethodVisitor);
  
  Type loadForcedInitializer(MethodVisitor paramMethodVisitor);
  
  Type loadEmpty(MethodVisitor paramMethodVisitor);
  
  Type convert(MethodVisitor paramMethodVisitor, Type paramType);
  
  void _return(MethodVisitor paramMethodVisitor);
}
