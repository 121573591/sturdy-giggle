package org.openjdk.nashorn.api.tree;

import org.openjdk.nashorn.internal.ir.Expression;
import org.openjdk.nashorn.internal.ir.Node;

abstract class ExpressionTreeImpl extends TreeImpl implements ExpressionTree {
  ExpressionTreeImpl(Expression expr) {
    super((Node)expr);
  }
}
