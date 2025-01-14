package org.openjdk.nashorn.api.tree;

import org.openjdk.nashorn.internal.ir.Block;
import org.openjdk.nashorn.internal.ir.Node;
import org.openjdk.nashorn.internal.ir.Statement;

abstract class StatementTreeImpl extends TreeImpl implements StatementTree {
  StatementTreeImpl(Statement stat) {
    super((Node)stat);
  }
  
  StatementTreeImpl(Block stat) {
    super((Node)stat);
  }
}
