package org.openjdk.nashorn.api.tree;

import java.util.List;
import org.openjdk.nashorn.internal.ir.CaseNode;
import org.openjdk.nashorn.internal.ir.Node;

final class CaseTreeImpl extends TreeImpl implements CaseTree {
  private final ExpressionTree expression;
  
  private final List<? extends StatementTree> statements;
  
  public CaseTreeImpl(CaseNode node, ExpressionTree expression, List<? extends StatementTree> statements) {
    super((Node)node);
    this.expression = expression;
    this.statements = statements;
  }
  
  public Tree.Kind getKind() {
    return Tree.Kind.CASE;
  }
  
  public ExpressionTree getExpression() {
    return this.expression;
  }
  
  public List<? extends StatementTree> getStatements() {
    return this.statements;
  }
  
  public <R, D> R accept(TreeVisitor<R, D> visitor, D data) {
    return visitor.visitCase(this, data);
  }
}
