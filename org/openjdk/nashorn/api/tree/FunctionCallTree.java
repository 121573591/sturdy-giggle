package org.openjdk.nashorn.api.tree;

import java.util.List;

public interface FunctionCallTree extends ExpressionTree {
  ExpressionTree getFunctionSelect();
  
  List<? extends ExpressionTree> getArguments();
}
