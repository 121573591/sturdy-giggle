package org.openjdk.nashorn.internal.ir;

public interface Assignment<D extends Expression> {
  D getAssignmentDest();
  
  Expression getAssignmentSource();
  
  Node setAssignmentDest(D paramD);
}
