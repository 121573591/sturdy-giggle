package org.openjdk.nashorn.api.tree;

public interface ConditionalLoopTree extends LoopTree {
  ExpressionTree getCondition();
}
