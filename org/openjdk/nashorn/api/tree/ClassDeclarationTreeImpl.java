package org.openjdk.nashorn.api.tree;

import java.util.List;
import org.openjdk.nashorn.internal.ir.Statement;
import org.openjdk.nashorn.internal.ir.VarNode;

final class ClassDeclarationTreeImpl extends StatementTreeImpl implements ClassDeclarationTree {
  private final IdentifierTree name;
  
  private final ExpressionTree classHeritage;
  
  private final PropertyTree constructor;
  
  private final List<? extends PropertyTree> classElements;
  
  ClassDeclarationTreeImpl(VarNode node, IdentifierTree name, ExpressionTree classHeritage, PropertyTree constructor, List<? extends PropertyTree> classElements) {
    super((Statement)node);
    this.name = name;
    this.classHeritage = classHeritage;
    this.constructor = constructor;
    this.classElements = classElements;
  }
  
  public Tree.Kind getKind() {
    return Tree.Kind.CLASS;
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
    return visitor.visitClassDeclaration(this, data);
  }
}
