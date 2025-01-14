package org.openjdk.nashorn.api.tree;

import java.util.List;

public class SimpleTreeVisitorES6<R, P> extends SimpleTreeVisitorES5_1<R, P> {
  public R visitCompilationUnit(CompilationUnitTree node, P r) {
    ModuleTree mod = node.getModule();
    if (mod != null)
      mod.accept(this, r); 
    return super.visitCompilationUnit(node, r);
  }
  
  public R visitModule(ModuleTree node, P p) {
    node.getImportEntries().forEach(e -> visitImportEntry(e, (P)p));
    node.getLocalExportEntries().forEach(e -> visitExportEntry(e, (P)p));
    node.getIndirectExportEntries().forEach(e -> visitExportEntry(e, (P)p));
    node.getStarExportEntries().forEach(e -> visitExportEntry(e, (P)p));
    return null;
  }
  
  public R visitExportEntry(ExportEntryTree node, P p) {
    return null;
  }
  
  public R visitImportEntry(ImportEntryTree node, P p) {
    return null;
  }
  
  public R visitClassDeclaration(ClassDeclarationTree node, P p) {
    node.getName().accept(this, p);
    ExpressionTree heritage = node.getClassHeritage();
    if (heritage != null)
      heritage.accept(this, p); 
    PropertyTree constructor = node.getConstructor();
    if (constructor != null)
      constructor.accept(this, p); 
    List<? extends PropertyTree> elements = node.getClassElements();
    if (elements != null)
      for (PropertyTree prop : elements)
        prop.accept(this, p);  
    return null;
  }
  
  public R visitClassExpression(ClassExpressionTree node, P p) {
    node.getName().accept(this, p);
    ExpressionTree heritage = node.getClassHeritage();
    if (heritage != null)
      heritage.accept(this, p); 
    PropertyTree constructor = node.getConstructor();
    if (constructor != null)
      constructor.accept(this, p); 
    List<? extends PropertyTree> elements = node.getClassElements();
    if (elements != null)
      for (PropertyTree prop : elements)
        prop.accept(this, p);  
    return null;
  }
  
  public R visitForOfLoop(ForOfLoopTree node, P p) {
    node.getVariable().accept(this, p);
    node.getExpression().accept(this, p);
    StatementTree stat = node.getStatement();
    if (stat != null)
      stat.accept(this, p); 
    return null;
  }
  
  public R visitYield(YieldTree node, P p) {
    node.getExpression().accept(this, p);
    return null;
  }
  
  public R visitSpread(SpreadTree node, P p) {
    node.getExpression().accept(this, p);
    return null;
  }
  
  public R visitTemplateLiteral(TemplateLiteralTree node, P p) {
    List<? extends ExpressionTree> expressions = node.getExpressions();
    for (ExpressionTree expr : expressions)
      expr.accept(this, p); 
    return null;
  }
  
  public R visitVariable(VariableTree node, P r) {
    ExpressionTree expr = node.getBinding();
    if (expr != null)
      expr.accept(this, r); 
    super.visitVariable(node, r);
    return null;
  }
}
