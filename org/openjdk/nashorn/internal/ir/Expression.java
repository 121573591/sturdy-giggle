package org.openjdk.nashorn.internal.ir;

import org.openjdk.nashorn.internal.codegen.types.Type;
import org.openjdk.nashorn.internal.runtime.UnwarrantedOptimismException;

public abstract class Expression extends Node {
  private static final long serialVersionUID = 1L;
  
  static final String OPT_IDENTIFIER = "%";
  
  protected Expression(long token, int start, int finish) {
    super(token, start, finish);
  }
  
  Expression(long token, int finish) {
    super(token, finish);
  }
  
  Expression(Expression expr) {
    super(expr);
  }
  
  public abstract Type getType();
  
  public boolean isLocal() {
    return false;
  }
  
  public boolean isSelfModifying() {
    return false;
  }
  
  public Type getWidestOperationType() {
    return Type.OBJECT;
  }
  
  public final boolean isOptimistic() {
    return getType().narrowerThan(getWidestOperationType());
  }
  
  void optimisticTypeToString(StringBuilder sb) {
    optimisticTypeToString(sb, isOptimistic());
  }
  
  void optimisticTypeToString(StringBuilder sb, boolean optimistic) {
    sb.append('{');
    Type type = getType();
    String desc = (type == Type.UNDEFINED) ? "U" : type.getDescriptor();
    sb.append((desc.charAt(desc.length() - 1) == ';') ? "O" : desc);
    if (isOptimistic() && optimistic) {
      sb.append("%");
      int pp = ((Optimistic)this).getProgramPoint();
      if (UnwarrantedOptimismException.isValid(pp))
        sb.append('_').append(pp); 
    } 
    sb.append('}');
  }
  
  public boolean isAlwaysFalse() {
    return false;
  }
  
  public boolean isAlwaysTrue() {
    return false;
  }
  
  public static boolean isAlwaysFalse(Expression test) {
    return (test != null && test.isAlwaysFalse());
  }
  
  public static boolean isAlwaysTrue(Expression test) {
    return (test == null || test.isAlwaysTrue());
  }
}
