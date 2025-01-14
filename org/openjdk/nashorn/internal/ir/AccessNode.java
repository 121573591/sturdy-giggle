package org.openjdk.nashorn.internal.ir;

import org.openjdk.nashorn.internal.codegen.types.Type;
import org.openjdk.nashorn.internal.ir.annotations.Immutable;
import org.openjdk.nashorn.internal.ir.visitor.NodeVisitor;

@Immutable
public final class AccessNode extends BaseNode {
  private static final long serialVersionUID = 1L;
  
  private final String property;
  
  public AccessNode(long token, int finish, Expression base, String property) {
    super(token, finish, base, false, false);
    this.property = property;
  }
  
  private AccessNode(AccessNode accessNode, Expression base, String property, boolean isFunction, Type type, int id, boolean isSuper) {
    super(accessNode, base, isFunction, type, id, isSuper);
    this.property = property;
  }
  
  public Node accept(NodeVisitor<? extends LexicalContext> visitor) {
    if (visitor.enterAccessNode(this))
      return visitor.leaveAccessNode(
          setBase((Expression)this.base.accept(visitor))); 
    return this;
  }
  
  public void toString(StringBuilder sb, boolean printType) {
    boolean needsParen = tokenType().needsParens(getBase().tokenType(), true);
    if (printType)
      optimisticTypeToString(sb); 
    if (needsParen)
      sb.append('('); 
    this.base.toString(sb, printType);
    if (needsParen)
      sb.append(')'); 
    sb.append('.');
    sb.append(this.property);
  }
  
  public String getProperty() {
    return this.property;
  }
  
  private AccessNode setBase(Expression base) {
    if (this.base == base)
      return this; 
    return new AccessNode(this, base, this.property, isFunction(), this.type, this.programPoint, isSuper());
  }
  
  public AccessNode setType(Type type) {
    if (this.type == type)
      return this; 
    return new AccessNode(this, this.base, this.property, isFunction(), type, this.programPoint, isSuper());
  }
  
  public AccessNode setProgramPoint(int programPoint) {
    if (this.programPoint == programPoint)
      return this; 
    return new AccessNode(this, this.base, this.property, isFunction(), this.type, programPoint, isSuper());
  }
  
  public AccessNode setIsFunction() {
    if (isFunction())
      return this; 
    return new AccessNode(this, this.base, this.property, true, this.type, this.programPoint, isSuper());
  }
  
  public AccessNode setIsSuper() {
    if (isSuper())
      return this; 
    return new AccessNode(this, this.base, this.property, isFunction(), this.type, this.programPoint, true);
  }
}
