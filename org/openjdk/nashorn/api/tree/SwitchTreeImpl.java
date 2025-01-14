package org.openjdk.nashorn.api.tree;

import java.util.List;
import org.openjdk.nashorn.internal.ir.Statement;
import org.openjdk.nashorn.internal.ir.SwitchNode;

final class SwitchTreeImpl extends StatementTreeImpl implements SwitchTree {
  private final ExpressionTree expr;
  
  private final List<? extends CaseTree> cases;
  
  SwitchTreeImpl(SwitchNode node, ExpressionTree expr, List<? extends CaseTree> cases) {
    super((Statement)node);
    this.expr = expr;
    this.cases = cases;
  }
  
  public Tree.Kind getKind() {
    return Tree.Kind.SWITCH;
  }
  
  public ExpressionTree getExpression() {
    return this.expr;
  }
  
  public List<? extends CaseTree> getCases() {
    return this.cases;
  }
  
  public <R, D> R accept(TreeVisitor<R, D> visitor, D data) {
    return visitor.visitSwitch(this, data);
  }
}
