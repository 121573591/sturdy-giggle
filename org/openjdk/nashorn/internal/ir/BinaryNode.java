package org.openjdk.nashorn.internal.ir;

import java.util.Set;
import org.openjdk.nashorn.internal.codegen.types.Type;
import org.openjdk.nashorn.internal.ir.annotations.Ignore;
import org.openjdk.nashorn.internal.ir.annotations.Immutable;
import org.openjdk.nashorn.internal.ir.visitor.NodeVisitor;
import org.openjdk.nashorn.internal.parser.TokenType;

@Immutable
public final class BinaryNode extends Expression implements Assignment<Expression>, Optimistic {
  private static final long serialVersionUID = 1L;
  
  private static final Type OPTIMISTIC_UNDECIDED_TYPE = Type.typeFor((new Object() {
      
      }).getClass());
  
  private final Expression lhs;
  
  private final Expression rhs;
  
  private final int programPoint;
  
  private final Type type;
  
  private transient Type cachedType;
  
  @Ignore
  private static final Set<TokenType> CAN_OVERFLOW = Set.of(new TokenType[] { 
        TokenType.ADD, TokenType.DIV, TokenType.MOD, TokenType.MUL, TokenType.SUB, TokenType.ASSIGN_ADD, TokenType.ASSIGN_DIV, TokenType.ASSIGN_MOD, TokenType.ASSIGN_MUL, TokenType.ASSIGN_SUB, 
        TokenType.SHR, TokenType.ASSIGN_SHR });
  
  public BinaryNode(long token, Expression lhs, Expression rhs) {
    super(token, lhs.getStart(), rhs.getFinish());
    assert lhs instanceof JoinPredecessorExpression;
    this.lhs = lhs;
    this.rhs = rhs;
    this.programPoint = -1;
    this.type = null;
  }
  
  private BinaryNode(BinaryNode binaryNode, Expression lhs, Expression rhs, Type type, int programPoint) {
    super(binaryNode);
    this.lhs = lhs;
    this.rhs = rhs;
    this.programPoint = programPoint;
    this.type = type;
  }
  
  public boolean isComparison() {
    switch (tokenType()) {
      case EQ:
      case EQ_STRICT:
      case NE:
      case NE_STRICT:
      case LE:
      case LT:
      case GE:
      case GT:
        return true;
    } 
    return false;
  }
  
  public boolean isRelational() {
    switch (tokenType()) {
      case LE:
      case LT:
      case GE:
      case GT:
        return true;
    } 
    return false;
  }
  
  public boolean isLogical() {
    return isLogical(tokenType());
  }
  
  public static boolean isLogical(TokenType tokenType) {
    switch (tokenType) {
      case AND:
      case OR:
        return true;
    } 
    return false;
  }
  
  public Type getWidestOperandType() {
    switch (tokenType()) {
      case SHR:
      case ASSIGN_SHR:
        return (Type)Type.INT;
      case INSTANCEOF:
        return Type.OBJECT;
    } 
    if (isComparison())
      return Type.OBJECT; 
    return getWidestOperationType();
  }
  
  public Type getWidestOperationType() {
    Type lhsType;
    Type rhsType;
    Type widestOperandType;
    switch (tokenType()) {
      case ADD:
      case ASSIGN_ADD:
        lhsType = this.lhs.getType();
        rhsType = this.rhs.getType();
        if (lhsType == Type.BOOLEAN && rhsType == Type.BOOLEAN)
          return (Type)Type.INT; 
        if (isString(lhsType) || isString(rhsType))
          return Type.CHARSEQUENCE; 
        widestOperandType = Type.widest(undefinedToNumber(booleanToInt(lhsType)), undefinedToNumber(booleanToInt(rhsType)));
        if (widestOperandType.isNumeric())
          return (Type)Type.NUMBER; 
        return Type.OBJECT;
      case SHR:
      case ASSIGN_SHR:
        return (Type)Type.NUMBER;
      case ASSIGN_SAR:
      case ASSIGN_SHL:
      case BIT_AND:
      case BIT_OR:
      case BIT_XOR:
      case ASSIGN_BIT_AND:
      case ASSIGN_BIT_OR:
      case ASSIGN_BIT_XOR:
      case SAR:
      case SHL:
        return (Type)Type.INT;
      case DIV:
      case MOD:
      case ASSIGN_DIV:
      case ASSIGN_MOD:
        return (Type)Type.NUMBER;
      case MUL:
      case SUB:
      case ASSIGN_MUL:
      case ASSIGN_SUB:
        lhsType = this.lhs.getType();
        rhsType = this.rhs.getType();
        if (lhsType == Type.BOOLEAN && rhsType == Type.BOOLEAN)
          return (Type)Type.INT; 
        return (Type)Type.NUMBER;
      case VOID:
        return Type.UNDEFINED;
      case ASSIGN:
        return this.rhs.getType();
      case INSTANCEOF:
        return Type.BOOLEAN;
      case COMMARIGHT:
        return this.rhs.getType();
      case AND:
      case OR:
        return Type.widestReturnType(this.lhs.getType(), this.rhs.getType());
    } 
    if (isComparison())
      return Type.BOOLEAN; 
    return Type.OBJECT;
  }
  
  private static boolean isString(Type type) {
    return (type == Type.STRING || type == Type.CHARSEQUENCE);
  }
  
  private static Type booleanToInt(Type type) {
    return (type == Type.BOOLEAN) ? (Type)Type.INT : type;
  }
  
  private static Type undefinedToNumber(Type type) {
    return (type == Type.UNDEFINED) ? (Type)Type.NUMBER : type;
  }
  
  public boolean isAssignment() {
    switch (tokenType()) {
      case ASSIGN_SHR:
      case ASSIGN_ADD:
      case ASSIGN_SAR:
      case ASSIGN_SHL:
      case ASSIGN_BIT_AND:
      case ASSIGN_BIT_OR:
      case ASSIGN_BIT_XOR:
      case ASSIGN_DIV:
      case ASSIGN_MOD:
      case ASSIGN_MUL:
      case ASSIGN_SUB:
      case ASSIGN:
        return true;
    } 
    return false;
  }
  
  public boolean isSelfModifying() {
    return (isAssignment() && !isTokenType(TokenType.ASSIGN));
  }
  
  public Expression getAssignmentDest() {
    return isAssignment() ? lhs() : null;
  }
  
  public BinaryNode setAssignmentDest(Expression n) {
    return setLHS(n);
  }
  
  public Expression getAssignmentSource() {
    return rhs();
  }
  
  public Node accept(NodeVisitor<? extends LexicalContext> visitor) {
    if (visitor.enterBinaryNode(this))
      return visitor.leaveBinaryNode(setLHS((Expression)this.lhs.accept(visitor)).setRHS((Expression)this.rhs.accept(visitor))); 
    return this;
  }
  
  public boolean isLocal() {
    switch (tokenType()) {
      case SHR:
      case ADD:
      case BIT_AND:
      case BIT_OR:
      case BIT_XOR:
      case SAR:
      case SHL:
      case DIV:
      case MOD:
      case MUL:
      case SUB:
        return (this.lhs.isLocal() && this.lhs.getType().isJSPrimitive() && this.rhs
          .isLocal() && this.rhs.getType().isJSPrimitive());
      case ASSIGN_SHR:
      case ASSIGN_ADD:
      case ASSIGN_SAR:
      case ASSIGN_SHL:
      case ASSIGN_BIT_AND:
      case ASSIGN_BIT_OR:
      case ASSIGN_BIT_XOR:
      case ASSIGN_DIV:
      case ASSIGN_MOD:
      case ASSIGN_MUL:
      case ASSIGN_SUB:
        return (this.lhs instanceof IdentNode && this.lhs.isLocal() && this.lhs.getType().isJSPrimitive() && this.rhs
          .isLocal() && this.rhs.getType().isJSPrimitive());
      case ASSIGN:
        return (this.lhs instanceof IdentNode && this.lhs.isLocal() && this.rhs.isLocal());
    } 
    return false;
  }
  
  public boolean isAlwaysFalse() {
    switch (tokenType()) {
      case COMMARIGHT:
        return this.rhs.isAlwaysFalse();
    } 
    return false;
  }
  
  public boolean isAlwaysTrue() {
    switch (tokenType()) {
      case COMMARIGHT:
        return this.rhs.isAlwaysTrue();
    } 
    return false;
  }
  
  public void toString(StringBuilder sb, boolean printType) {
    TokenType tokenType = tokenType();
    boolean lhsParen = tokenType.needsParens(lhs().tokenType(), true);
    boolean rhsParen = tokenType.needsParens(rhs().tokenType(), false);
    if (lhsParen)
      sb.append('('); 
    lhs().toString(sb, printType);
    if (lhsParen)
      sb.append(')'); 
    sb.append(' ');
    switch (tokenType) {
      case COMMARIGHT:
        sb.append(",>");
        break;
      case INCPREFIX:
      case DECPREFIX:
        sb.append("++");
        break;
      default:
        sb.append(tokenType.getName());
        break;
    } 
    if (isOptimistic())
      sb.append("%"); 
    sb.append(' ');
    if (rhsParen)
      sb.append('('); 
    rhs().toString(sb, printType);
    if (rhsParen)
      sb.append(')'); 
  }
  
  public Expression lhs() {
    return this.lhs;
  }
  
  public Expression rhs() {
    return this.rhs;
  }
  
  public BinaryNode setLHS(Expression lhs) {
    if (this.lhs == lhs)
      return this; 
    return new BinaryNode(this, lhs, this.rhs, this.type, this.programPoint);
  }
  
  public BinaryNode setRHS(Expression rhs) {
    if (this.rhs == rhs)
      return this; 
    return new BinaryNode(this, this.lhs, rhs, this.type, this.programPoint);
  }
  
  public BinaryNode setOperands(Expression lhs, Expression rhs) {
    if (this.lhs == lhs && this.rhs == rhs)
      return this; 
    return new BinaryNode(this, lhs, rhs, this.type, this.programPoint);
  }
  
  public int getProgramPoint() {
    return this.programPoint;
  }
  
  public boolean canBeOptimistic() {
    return (isTokenType(TokenType.ADD) || getMostOptimisticType() != getMostPessimisticType());
  }
  
  public BinaryNode setProgramPoint(int programPoint) {
    if (this.programPoint == programPoint)
      return this; 
    return new BinaryNode(this, this.lhs, this.rhs, this.type, programPoint);
  }
  
  public Type getMostOptimisticType() {
    TokenType tokenType = tokenType();
    if (tokenType == TokenType.ADD || tokenType == TokenType.ASSIGN_ADD)
      return OPTIMISTIC_UNDECIDED_TYPE; 
    if (CAN_OVERFLOW.contains(tokenType))
      return (Type)Type.INT; 
    return getMostPessimisticType();
  }
  
  public Type getMostPessimisticType() {
    return getWidestOperationType();
  }
  
  public boolean isOptimisticUndecidedType() {
    return (this.type == OPTIMISTIC_UNDECIDED_TYPE);
  }
  
  public Type getType() {
    if (this.cachedType == null)
      this.cachedType = getTypeUncached(); 
    return this.cachedType;
  }
  
  private Type getTypeUncached() {
    if (this.type == OPTIMISTIC_UNDECIDED_TYPE)
      return decideType(this.lhs.getType(), this.rhs.getType()); 
    Type widest = getWidestOperationType();
    if (this.type == null)
      return widest; 
    if (tokenType() == TokenType.ASSIGN_SHR || tokenType() == TokenType.SHR)
      return this.type; 
    return Type.narrowest(widest, Type.widest(this.type, Type.widest(this.lhs.getType(), this.rhs.getType())));
  }
  
  private static Type decideType(Type lhsType, Type rhsType) {
    if (isString(lhsType) || isString(rhsType))
      return Type.CHARSEQUENCE; 
    Type widest = Type.widest(undefinedToNumber(booleanToInt(lhsType)), undefinedToNumber(booleanToInt(rhsType)));
    return widest.isObject() ? Type.OBJECT : widest;
  }
  
  public BinaryNode decideType() {
    assert this.type == OPTIMISTIC_UNDECIDED_TYPE;
    return setType(decideType(this.lhs.getType(), this.rhs.getType()));
  }
  
  public BinaryNode setType(Type type) {
    if (this.type == type)
      return this; 
    return new BinaryNode(this, this.lhs, this.rhs, type, this.programPoint);
  }
}
