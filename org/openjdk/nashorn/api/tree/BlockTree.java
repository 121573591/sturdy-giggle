package org.openjdk.nashorn.api.tree;

import java.util.List;

public interface BlockTree extends StatementTree {
  List<? extends StatementTree> getStatements();
}
