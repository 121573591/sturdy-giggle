package org.openjdk.nashorn.internal.ir;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import org.openjdk.nashorn.internal.codegen.types.Type;
import org.openjdk.nashorn.internal.ir.annotations.Ignore;
import org.openjdk.nashorn.internal.ir.annotations.Immutable;
import org.openjdk.nashorn.internal.ir.visitor.NodeVisitor;

@Immutable
public final class CallNode extends LexicalContextExpression implements Optimistic {
  private static final long serialVersionUID = 1L;
  
  private final Expression function;
  
  private final List<Expression> args;
  
  private static final int IS_NEW = 1;
  
  private static final int IS_APPLY_TO_CALL = 2;
  
  private final int flags;
  
  private final int lineNumber;
  
  private final int programPoint;
  
  private final Type optimisticType;
  
  @Ignore
  private final EvalArgs evalArgs;
  
  public static class EvalArgs implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private final List<Expression> args;
    
    private final String location;
    
    public EvalArgs(List<Expression> args, String location) {
      this.args = args;
      this.location = location;
    }
    
    public List<Expression> getArgs() {
      return Collections.unmodifiableList(this.args);
    }
    
    private EvalArgs setArgs(List<Expression> args) {
      if (this.args == args)
        return this; 
      return new EvalArgs(args, this.location);
    }
    
    public String getLocation() {
      return this.location;
    }
  }
  
  public CallNode(int lineNumber, long token, int finish, Expression function, List<Expression> args, boolean isNew) {
    super(token, finish);
    this.function = function;
    this.args = args;
    this.flags = isNew ? 1 : 0;
    this.evalArgs = null;
    this.lineNumber = lineNumber;
    this.programPoint = -1;
    this.optimisticType = null;
  }
  
  private CallNode(CallNode callNode, Expression function, List<Expression> args, int flags, Type optimisticType, EvalArgs evalArgs, int programPoint) {
    super(callNode);
    this.lineNumber = callNode.lineNumber;
    this.function = function;
    this.args = args;
    this.flags = flags;
    this.evalArgs = evalArgs;
    this.programPoint = programPoint;
    this.optimisticType = optimisticType;
  }
  
  public int getLineNumber() {
    return this.lineNumber;
  }
  
  public Type getType() {
    return (this.optimisticType == null) ? Type.OBJECT : this.optimisticType;
  }
  
  public Optimistic setType(Type optimisticType) {
    if (this.optimisticType == optimisticType)
      return this; 
    return new CallNode(this, this.function, this.args, this.flags, optimisticType, this.evalArgs, this.programPoint);
  }
  
  public Node accept(LexicalContext lc, NodeVisitor<? extends LexicalContext> visitor) {
    if (visitor.enterCallNode(this)) {
      CallNode newCallNode = (CallNode)visitor.leaveCallNode(
          setFunction((Expression)this.function.accept(visitor))
          .setArgs(Node.accept(visitor, this.args))
          .setEvalArgs((this.evalArgs == null) ? 
            null : 
            this.evalArgs.setArgs(Node.accept(visitor, this.evalArgs.getArgs()))));
      if (this != newCallNode)
        return Node.<Node>replaceInLexicalContext(lc, this, newCallNode); 
    } 
    return this;
  }
  
  public void toString(StringBuilder sb, boolean printType) {
    if (printType)
      optimisticTypeToString(sb); 
    StringBuilder fsb = new StringBuilder();
    this.function.toString(fsb, printType);
    if (isApplyToCall()) {
      sb.append(fsb.toString().replace("apply", "[apply => call]"));
    } else {
      sb.append(fsb);
    } 
    sb.append('(');
    boolean first = true;
    for (Node arg : this.args) {
      if (!first) {
        sb.append(", ");
      } else {
        first = false;
      } 
      arg.toString(sb, printType);
    } 
    sb.append(')');
  }
  
  public List<Expression> getArgs() {
    return Collections.unmodifiableList(this.args);
  }
  
  public CallNode setArgs(List<Expression> args) {
    if (this.args == args)
      return this; 
    return new CallNode(this, this.function, args, this.flags, this.optimisticType, this.evalArgs, this.programPoint);
  }
  
  public EvalArgs getEvalArgs() {
    return this.evalArgs;
  }
  
  public CallNode setEvalArgs(EvalArgs evalArgs) {
    if (this.evalArgs == evalArgs)
      return this; 
    return new CallNode(this, this.function, this.args, this.flags, this.optimisticType, evalArgs, this.programPoint);
  }
  
  public boolean isEval() {
    return (this.evalArgs != null);
  }
  
  public boolean isApplyToCall() {
    return ((this.flags & 0x2) != 0);
  }
  
  public CallNode setIsApplyToCall() {
    return setFlags(this.flags | 0x2);
  }
  
  public Expression getFunction() {
    return this.function;
  }
  
  public CallNode setFunction(Expression function) {
    if (this.function == function)
      return this; 
    return new CallNode(this, function, this.args, this.flags, this.optimisticType, this.evalArgs, this.programPoint);
  }
  
  public boolean isNew() {
    return ((this.flags & 0x1) != 0);
  }
  
  private CallNode setFlags(int flags) {
    if (this.flags == flags)
      return this; 
    return new CallNode(this, this.function, this.args, flags, this.optimisticType, this.evalArgs, this.programPoint);
  }
  
  public int getProgramPoint() {
    return this.programPoint;
  }
  
  public CallNode setProgramPoint(int programPoint) {
    if (this.programPoint == programPoint)
      return this; 
    return new CallNode(this, this.function, this.args, this.flags, this.optimisticType, this.evalArgs, programPoint);
  }
  
  public Type getMostOptimisticType() {
    return (Type)Type.INT;
  }
  
  public Type getMostPessimisticType() {
    return Type.OBJECT;
  }
  
  public boolean canBeOptimistic() {
    return true;
  }
}
