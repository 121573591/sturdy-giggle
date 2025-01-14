package org.openjdk.nashorn.api.tree;

import java.util.List;
import org.openjdk.nashorn.internal.ir.ClassNode;
import org.openjdk.nashorn.internal.ir.Expression;

final class ClassExpressionTreeImpl extends ExpressionTreeImpl implements ClassExpressionTree {
  private final IdentifierTree name;
  
  private final ExpressionTree classHeritage;
  
  private final PropertyTree constructor;
  
  private final List<? extends PropertyTree> classElements;
  
  ClassExpressionTreeImpl(ClassNode cn, IdentifierTree name, ExpressionTree classHeritage, PropertyTree constructor, List<? extends PropertyTree> classElements) {
    super((Expression)cn);
    this.name = name;
    this.classHeritage = classHeritage;
    this.constructor = constructor;
    this.classElements = classElements;
  }
  
  public Tree.Kind getKind() {
    return Tree.Kind.CLASS_EXPRESSION;
  }
  
  public IdentifierTree getName() {
    return this.name;
  }
  
  public ExpressionTree getClassHeritage() {
    return this.classHeritage;
  }
  
  public PropertyTree getConstructor() {
    return this.constructor;
  }
  
  public List<? extends PropertyTree> getClassElements() {
    return this.classElements;
  }
  
  public <R, D> R accept(TreeVisitor<R, D> visitor, D data) {
    return visitor.visitClassExpression(this, data);
  }
}
