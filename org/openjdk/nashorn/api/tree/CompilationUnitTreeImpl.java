package org.openjdk.nashorn.api.tree;

import java.util.List;
import org.openjdk.nashorn.internal.ir.FunctionNode;
import org.openjdk.nashorn.internal.ir.Node;

final class CompilationUnitTreeImpl extends TreeImpl implements CompilationUnitTree {
  private final FunctionNode funcNode;
  
  private final List<? extends Tree> elements;
  
  private final ModuleTree module;
  
  CompilationUnitTreeImpl(FunctionNode node, List<? extends Tree> elements, ModuleTree module) {
    super((Node)node);
    this.funcNode = node;
    assert this.funcNode.getKind() == FunctionNode.Kind.SCRIPT || this.funcNode
      .getKind() == FunctionNode.Kind.MODULE : "script or module function expected";
    this.elements = elements;
    this.module = module;
  }
  
  public Tree.Kind getKind() {
    return Tree.Kind.COMPILATION_UNIT;
  }
  
  public List<? extends Tree> getSourceElements() {
    return this.elements;
  }
  
  public String getSourceName() {
    return this.funcNode.getSourceName();
  }
  
  public boolean isStrict() {
    return this.funcNode.isStrict();
  }
  
  public LineMap getLineMap() {
    return new LineMapImpl(this.funcNode.getSource());
  }
  
  public ModuleTree getModule() {
    return this.module;
  }
  
  public <R, D> R accept(TreeVisitor<R, D> visitor, D data) {
    return visitor.visitCompilationUnit(this, data);
  }
}
