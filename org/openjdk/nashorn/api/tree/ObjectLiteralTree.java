package org.openjdk.nashorn.api.tree;

import java.util.List;

public interface ObjectLiteralTree extends ExpressionTree {
  List<? extends PropertyTree> getProperties();
}
