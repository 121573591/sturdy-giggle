package org.openjdk.nashorn.internal.ir;

import org.openjdk.nashorn.internal.ir.annotations.Immutable;
import org.openjdk.nashorn.internal.ir.visitor.NodeVisitor;

@Immutable
public final class VarNode extends Statement implements Assignment<IdentNode> {
  private static final long serialVersionUID = 1L;
  
  private final IdentNode name;
  
  private final Expression init;
  
  private final int flags;
  
  private final int sourceOrder;
  
  public static final int IS_LET = 1;
  
  public static final int IS_CONST = 2;
  
  public static final int IS_LAST_FUNCTION_DECLARATION = 4;
  
  public VarNode(int lineNumber, long token, int finish, IdentNode name, Expression init) {
    this(lineNumber, token, finish, name, init, 0);
  }
  
  private VarNode(VarNode varNode, IdentNode name, Expression init, int flags) {
    super(varNode);
    this.sourceOrder = -1;
    this.name = (init == null) ? name : name.setIsInitializedHere();
    this.init = init;
    this.flags = flags;
  }
  
  public VarNode(int lineNumber, long token, int finish, IdentNode name, Expression init, int flags) {
    this(lineNumber, token, -1, finish, name, init, flags);
  }
  
  public VarNode(int lineNumber, long token, int sourceOrder, int finish, IdentNode name, Expression init, int flags) {
    super(lineNumber, token, finish);
    this.sourceOrder = sourceOrder;
    this.name = (init == null) ? name : name.setIsInitializedHere();
    this.init = init;
    this.flags = flags;
  }
  
  public int getSourceOrder() {
    return (this.sourceOrder == -1) ? super.getSourceOrder() : this.sourceOrder;
  }
  
  public boolean isAssignment() {
    return hasInit();
  }
  
  public IdentNode getAssignmentDest() {
    return isAssignment() ? this.name : null;
  }
  
  public VarNode setAssignmentDest(IdentNode n) {
    return setName(n);
  }
  
  public Expression getAssignmentSource() {
    return isAssignment() ? getInit() : null;
  }
  
  public boolean isBlockScoped() {
    return (getFlag(1) || getFlag(2));
  }
  
  public boolean isLet() {
    return getFlag(1);
  }
  
  public boolean isConst() {
    return getFlag(2);
  }
  
  public int getSymbolFlags() {
    if (isLet())
      return 18; 
    if (isConst())
      return 34; 
    return 2;
  }
  
  public boolean hasInit() {
    return (this.init != null);
  }
  
  public Node accept(NodeVisitor<? extends LexicalContext> visitor) {
    if (visitor.enterVarNode(this)) {
      VarNode newThis;
      Expression newInit = (this.init == null) ? null : (Expression)this.init.accept(visitor);
      IdentNode newName = (IdentNode)this.name.accept(visitor);
      if (this.name != newName || this.init != newInit) {
        newThis = new VarNode(this, newName, newInit, this.flags);
      } else {
        newThis = this;
      } 
      return visitor.leaveVarNode(newThis);
    } 
    return this;
  }
  
  public void toString(StringBuilder sb, boolean printType) {
    sb.append(tokenType().getName()).append(' ');
    this.name.toString(sb, printType);
    if (this.init != null) {
      sb.append(" = ");
      this.init.toString(sb, printType);
    } 
  }
  
  public Expression getInit() {
    return this.init;
  }
  
  public VarNode setInit(Expression init) {
    if (this.init == init)
      return this; 
    return new VarNode(this, this.name, init, this.flags);
  }
  
  public IdentNode getName() {
    return this.name;
  }
  
  public VarNode setName(IdentNode name) {
    if (this.name == name)
      return this; 
    return new VarNode(this, name, this.init, this.flags);
  }
  
  private VarNode setFlags(int flags) {
    if (this.flags == flags)
      return this; 
    return new VarNode(this, this.name, this.init, flags);
  }
  
  public boolean getFlag(int flag) {
    return ((this.flags & flag) == flag);
  }
  
  public VarNode setFlag(int flag) {
    return setFlags(this.flags | flag);
  }
  
  public boolean isFunctionDeclaration() {
    return (this.init instanceof FunctionNode && ((FunctionNode)this.init).isDeclared());
  }
}
