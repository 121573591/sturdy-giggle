package org.openjdk.nashorn.internal.ir;

import org.openjdk.nashorn.internal.codegen.types.Type;

public interface Optimistic {
  int getProgramPoint();
  
  Optimistic setProgramPoint(int paramInt);
  
  boolean canBeOptimistic();
  
  Type getMostOptimisticType();
  
  Type getMostPessimisticType();
  
  Optimistic setType(Type paramType);
}
