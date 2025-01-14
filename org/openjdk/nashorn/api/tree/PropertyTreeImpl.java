package org.openjdk.nashorn.api.tree;

import org.openjdk.nashorn.internal.ir.Node;
import org.openjdk.nashorn.internal.ir.PropertyNode;

final class PropertyTreeImpl extends TreeImpl implements PropertyTree {
  private final ExpressionTree key;
  
  private final ExpressionTree value;
  
  private final FunctionExpressionTree getter;
  
  private final FunctionExpressionTree setter;
  
  private final boolean isStatic;
  
  private final boolean isComputed;
  
  PropertyTreeImpl(PropertyNode node, ExpressionTree key, ExpressionTree value, FunctionExpressionTree getter, FunctionExpressionTree setter) {
    super((Node)node);
    this.key = key;
    this.value = value;
    this.getter = getter;
    this.setter = setter;
    this.isStatic = node.isStatic();
    this.isComputed = node.isComputed();
  }
  
  public Tree.Kind getKind() {
    return Tree.Kind.PROPERTY;
  }
  
  public ExpressionTree getKey() {
    return this.key;
  }
  
  public ExpressionTree getValue() {
    return this.value;
  }
  
  public FunctionExpressionTree getGetter() {
    return this.getter;
  }
  
  public FunctionExpressionTree getSetter() {
    return this.setter;
  }
  
  public boolean isStatic() {
    return this.isStatic;
  }
  
  public boolean isComputed() {
    return this.isComputed;
  }
  
  public <R, D> R accept(TreeVisitor<R, D> visitor, D data) {
    return visitor.visitProperty(this, data);
  }
}
