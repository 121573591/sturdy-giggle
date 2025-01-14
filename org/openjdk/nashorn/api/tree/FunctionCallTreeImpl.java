package org.openjdk.nashorn.api.tree;

import java.util.List;
import org.openjdk.nashorn.internal.ir.CallNode;
import org.openjdk.nashorn.internal.ir.Expression;

class FunctionCallTreeImpl extends ExpressionTreeImpl implements FunctionCallTree {
  private final List<? extends ExpressionTree> arguments;
  
  private final ExpressionTree function;
  
  FunctionCallTreeImpl(CallNode node, ExpressionTree function, List<? extends ExpressionTree> arguments) {
    super((Expression)node);
    this.function = function;
    this.arguments = arguments;
  }
  
  public Tree.Kind getKind() {
    return Tree.Kind.FUNCTION_INVOCATION;
  }
  
  public ExpressionTree getFunctionSelect() {
    return this.function;
  }
  
  public List<? extends ExpressionTree> getArguments() {
    return this.arguments;
  }
  
  public <R, D> R accept(TreeVisitor<R, D> visitor, D data) {
    return visitor.visitFunctionCall(this, data);
  }
}
