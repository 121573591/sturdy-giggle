package org.openjdk.nashorn.internal.ir;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.openjdk.nashorn.internal.codegen.types.Type;
import org.openjdk.nashorn.internal.ir.annotations.Immutable;
import org.openjdk.nashorn.internal.ir.visitor.NodeVisitor;
import org.openjdk.nashorn.internal.parser.TokenType;

@Immutable
public class RuntimeNode extends Expression {
  private static final long serialVersionUID = 1L;
  
  private final Request request;
  
  private final List<Expression> args;
  
  public enum Request {
    ADD((String)TokenType.ADD, Type.OBJECT, 2, true),
    DEBUGGER,
    NEW,
    TYPEOF,
    REFERENCE_ERROR,
    EQ_STRICT((String)TokenType.EQ_STRICT, Type.BOOLEAN, 2, true),
    EQ((String)TokenType.EQ, Type.BOOLEAN, 2, true),
    GE((String)TokenType.GE, Type.BOOLEAN, 2, true),
    GT((String)TokenType.GT, Type.BOOLEAN, 2, true),
    IN((String)TokenType.IN, Type.BOOLEAN, 2),
    INSTANCEOF((String)TokenType.INSTANCEOF, Type.BOOLEAN, 2),
    LE((String)TokenType.LE, Type.BOOLEAN, 2, true),
    LT((String)TokenType.LT, Type.BOOLEAN, 2, true),
    NE_STRICT((String)TokenType.NE_STRICT, Type.BOOLEAN, 2, true),
    NE((String)TokenType.NE, Type.BOOLEAN, 2, true),
    IS_UNDEFINED((String)TokenType.EQ_STRICT, Type.BOOLEAN, 2),
    IS_NOT_UNDEFINED((String)TokenType.NE_STRICT, Type.BOOLEAN, 2),
    GET_TEMPLATE_OBJECT((String)TokenType.TEMPLATE, Type.SCRIPT_OBJECT, 2);
    
    private final TokenType tokenType;
    
    private final Type returnType;
    
    private final int arity;
    
    private final boolean canSpecialize;
    
    Request(TokenType tokenType, Type returnType, int arity, boolean canSpecialize) {
      this.tokenType = tokenType;
      this.returnType = returnType;
      this.arity = arity;
      this.canSpecialize = canSpecialize;
    }
    
    public boolean canSpecialize() {
      return this.canSpecialize;
    }
    
    public int getArity() {
      return this.arity;
    }
    
    public Type getReturnType() {
      return this.returnType;
    }
    
    public TokenType getTokenType() {
      return this.tokenType;
    }
    
    public String nonStrictName() {
      switch (this) {
        case TYPEOF:
          return NE.name();
        case IN:
          return EQ.name();
      } 
      return name();
    }
    
    public static Request requestFor(Expression node) {
      switch (node.tokenType()) {
        case TYPEOF:
          return TYPEOF;
        case IN:
          return IN;
        case INSTANCEOF:
          return INSTANCEOF;
        case EQ_STRICT:
          return EQ_STRICT;
        case NE_STRICT:
          return NE_STRICT;
        case EQ:
          return EQ;
        case NE:
          return NE;
        case LT:
          return LT;
        case LE:
          return LE;
        case GT:
          return GT;
        case GE:
          return GE;
      } 
      assert false;
      return null;
    }
    
    public static boolean isUndefinedCheck(Request request) {
      return (request == IS_UNDEFINED || request == IS_NOT_UNDEFINED);
    }
    
    public static boolean isEQ(Request request) {
      return (request == EQ || request == EQ_STRICT);
    }
    
    public static boolean isNE(Request request) {
      return (request == NE || request == NE_STRICT);
    }
    
    public static boolean isStrict(Request request) {
      return (request == EQ_STRICT || request == NE_STRICT);
    }
    
    public static Request reverse(Request request) {
      switch (request) {
        case TYPEOF:
        case IN:
        case INSTANCEOF:
        case EQ_STRICT:
          return request;
        case NE_STRICT:
          return GE;
        case EQ:
          return GT;
        case NE:
          return LE;
        case LT:
          return LT;
      } 
      return null;
    }
    
    public static Request invert(Request request) {
      switch (request) {
        case INSTANCEOF:
          return NE;
        case IN:
          return NE_STRICT;
        case EQ_STRICT:
          return EQ;
        case TYPEOF:
          return EQ_STRICT;
        case NE_STRICT:
          return GT;
        case EQ:
          return GE;
        case NE:
          return LT;
        case LT:
          return LE;
      } 
      return null;
    }
    
    public static boolean isComparison(Request request) {
      switch (request) {
        case TYPEOF:
        case IN:
        case INSTANCEOF:
        case EQ_STRICT:
        case NE_STRICT:
        case EQ:
        case NE:
        case LT:
        case LE:
        case GT:
          return true;
      } 
      return false;
    }
  }
  
  public RuntimeNode(long token, int finish, Request request, List<Expression> args) {
    super(token, finish);
    this.request = request;
    this.args = args;
  }
  
  private RuntimeNode(RuntimeNode runtimeNode, Request request, List<Expression> args) {
    super(runtimeNode);
    this.request = request;
    this.args = args;
  }
  
  public RuntimeNode(long token, int finish, Request request, Expression... args) {
    this(token, finish, request, Arrays.asList(args));
  }
  
  public RuntimeNode(Expression parent, Request request, Expression... args) {
    this(parent, request, Arrays.asList(args));
  }
  
  public RuntimeNode(Expression parent, Request request, List<Expression> args) {
    super(parent);
    this.request = request;
    this.args = args;
  }
  
  public RuntimeNode(UnaryNode parent, Request request) {
    this(parent, request, new Expression[] { parent.getExpression() });
  }
  
  public RuntimeNode(BinaryNode parent) {
    this(parent, Request.requestFor(parent), new Expression[] { parent.lhs(), parent.rhs() });
  }
  
  public RuntimeNode setRequest(Request request) {
    if (this.request == request)
      return this; 
    return new RuntimeNode(this, request, this.args);
  }
  
  public Type getType() {
    return this.request.getReturnType();
  }
  
  public Node accept(NodeVisitor<? extends LexicalContext> visitor) {
    if (visitor.enterRuntimeNode(this))
      return visitor.leaveRuntimeNode(setArgs(Node.accept(visitor, this.args))); 
    return this;
  }
  
  public void toString(StringBuilder sb, boolean printType) {
    sb.append("ScriptRuntime.");
    sb.append(this.request);
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
  
  public RuntimeNode setArgs(List<Expression> args) {
    if (this.args == args)
      return this; 
    return new RuntimeNode(this, this.request, args);
  }
  
  public Request getRequest() {
    return this.request;
  }
  
  public boolean isPrimitive() {
    for (Expression arg : this.args) {
      if (arg.getType().isObject())
        return false; 
    } 
    return true;
  }
}
