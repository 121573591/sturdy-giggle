package org.openjdk.nashorn.api.tree;

public class SimpleTreeVisitorES5_1<R, P> implements TreeVisitor<R, P> {
  public R visitAssignment(AssignmentTree node, P r) {
    node.getVariable().accept(this, r);
    node.getExpression().accept(this, r);
    return null;
  }
  
  public R visitCompoundAssignment(CompoundAssignmentTree node, P r) {
    node.getVariable().accept(this, r);
    node.getExpression().accept(this, r);
    return null;
  }
  
  public R visitModule(ModuleTree node, P p) {
    return visitUnknown(node, p);
  }
  
  public R visitExportEntry(ExportEntryTree node, P p) {
    return visitUnknown(node, p);
  }
  
  public R visitImportEntry(ImportEntryTree node, P p) {
    return visitUnknown(node, p);
  }
  
  public R visitBinary(BinaryTree node, P r) {
    node.getLeftOperand().accept(this, r);
    node.getRightOperand().accept(this, r);
    return null;
  }
  
  public R visitBlock(BlockTree node, P r) {
    node.getStatements().forEach(tree -> tree.accept(this, (P)r));
    return null;
  }
  
  public R visitBreak(BreakTree node, P r) {
    return null;
  }
  
  public R visitCase(CaseTree node, P r) {
    Tree caseVal = node.getExpression();
    if (caseVal != null)
      caseVal.accept(this, r); 
    node.getStatements().forEach(tree -> tree.accept(this, (P)r));
    return null;
  }
  
  public R visitCatch(CatchTree node, P r) {
    Tree cond = node.getCondition();
    if (cond != null)
      cond.accept(this, r); 
    node.getParameter().accept(this, r);
    node.getBlock().accept(this, r);
    return null;
  }
  
  public R visitClassDeclaration(ClassDeclarationTree node, P p) {
    return visitUnknown(node, p);
  }
  
  public R visitClassExpression(ClassExpressionTree node, P p) {
    return visitUnknown(node, p);
  }
  
  public R visitConditionalExpression(ConditionalExpressionTree node, P r) {
    node.getCondition().accept(this, r);
    node.getTrueExpression().accept(this, r);
    node.getFalseExpression().accept(this, r);
    return null;
  }
  
  public R visitContinue(ContinueTree node, P r) {
    return null;
  }
  
  public R visitDebugger(DebuggerTree node, P r) {
    return null;
  }
  
  public R visitDoWhileLoop(DoWhileLoopTree node, P r) {
    node.getStatement().accept(this, r);
    node.getCondition().accept(this, r);
    return null;
  }
  
  public R visitErroneous(ErroneousTree node, P r) {
    return null;
  }
  
  public R visitExpressionStatement(ExpressionStatementTree node, P r) {
    node.getExpression().accept(this, r);
    return null;
  }
  
  public R visitForLoop(ForLoopTree node, P r) {
    Tree init = node.getInitializer();
    if (init != null)
      init.accept(this, r); 
    Tree cond = node.getCondition();
    if (cond != null)
      cond.accept(this, r); 
    Tree update = node.getUpdate();
    if (update != null)
      update.accept(this, r); 
    node.getStatement().accept(this, r);
    return null;
  }
  
  public R visitForInLoop(ForInLoopTree node, P r) {
    node.getVariable().accept(this, r);
    node.getExpression().accept(this, r);
    StatementTree stat = node.getStatement();
    if (stat != null)
      stat.accept(this, r); 
    return null;
  }
  
  public R visitForOfLoop(ForOfLoopTree node, P p) {
    return visitUnknown(node, p);
  }
  
  public R visitFunctionCall(FunctionCallTree node, P r) {
    node.getFunctionSelect().accept(this, r);
    node.getArguments().forEach(tree -> tree.accept(this, (P)r));
    return null;
  }
  
  public R visitFunctionDeclaration(FunctionDeclarationTree node, P r) {
    node.getParameters().forEach(tree -> tree.accept(this, (P)r));
    node.getBody().accept(this, r);
    return null;
  }
  
  public R visitFunctionExpression(FunctionExpressionTree node, P r) {
    node.getParameters().forEach(tree -> tree.accept(this, (P)r));
    node.getBody().accept(this, r);
    return null;
  }
  
  public R visitIdentifier(IdentifierTree node, P r) {
    return null;
  }
  
  public R visitIf(IfTree node, P r) {
    node.getCondition().accept(this, r);
    node.getThenStatement().accept(this, r);
    Tree elseStat = node.getElseStatement();
    if (elseStat != null)
      elseStat.accept(this, r); 
    return null;
  }
  
  public R visitArrayAccess(ArrayAccessTree node, P r) {
    node.getExpression().accept(this, r);
    node.getIndex().accept(this, r);
    return null;
  }
  
  public R visitArrayLiteral(ArrayLiteralTree node, P r) {
    node.getElements().stream().filter(tree -> (tree != null)).forEach(tree -> tree.accept(this, (P)r));
    return null;
  }
  
  public R visitLabeledStatement(LabeledStatementTree node, P r) {
    node.getStatement().accept(this, r);
    return null;
  }
  
  public R visitLiteral(LiteralTree node, P r) {
    return null;
  }
  
  public R visitParenthesized(ParenthesizedTree node, P r) {
    node.getExpression().accept(this, r);
    return null;
  }
  
  public R visitReturn(ReturnTree node, P r) {
    Tree retExpr = node.getExpression();
    if (retExpr != null)
      retExpr.accept(this, r); 
    return null;
  }
  
  public R visitMemberSelect(MemberSelectTree node, P r) {
    node.getExpression().accept(this, r);
    return null;
  }
  
  public R visitNew(NewTree node, P r) {
    node.getConstructorExpression().accept(this, r);
    return null;
  }
  
  public R visitObjectLiteral(ObjectLiteralTree node, P r) {
    node.getProperties().forEach(tree -> tree.accept(this, (P)r));
    return null;
  }
  
  public R visitProperty(PropertyTree node, P r) {
    FunctionExpressionTree getter = node.getGetter();
    if (getter != null)
      getter.accept(this, r); 
    ExpressionTree key = node.getKey();
    if (key != null)
      key.accept(this, r); 
    FunctionExpressionTree setter = node.getSetter();
    if (setter != null)
      setter.accept(this, r); 
    ExpressionTree value = node.getValue();
    if (value != null)
      value.accept(this, r); 
    return null;
  }
  
  public R visitRegExpLiteral(RegExpLiteralTree node, P r) {
    return null;
  }
  
  public R visitTemplateLiteral(TemplateLiteralTree node, P p) {
    return visitUnknown(node, p);
  }
  
  public R visitEmptyStatement(EmptyStatementTree node, P r) {
    return null;
  }
  
  public R visitSpread(SpreadTree node, P p) {
    return visitUnknown(node, p);
  }
  
  public R visitSwitch(SwitchTree node, P r) {
    node.getExpression().accept(this, r);
    node.getCases().forEach(tree -> tree.accept(this, (P)r));
    return null;
  }
  
  public R visitThrow(ThrowTree node, P r) {
    node.getExpression().accept(this, r);
    return null;
  }
  
  public R visitCompilationUnit(CompilationUnitTree node, P r) {
    node.getSourceElements().forEach(tree -> tree.accept(this, (P)r));
    return null;
  }
  
  public R visitTry(TryTree node, P r) {
    node.getBlock().accept(this, r);
    node.getCatches().forEach(tree -> tree.accept(this, (P)r));
    Tree finallyBlock = node.getFinallyBlock();
    if (finallyBlock != null)
      finallyBlock.accept(this, r); 
    return null;
  }
  
  public R visitInstanceOf(InstanceOfTree node, P r) {
    node.getType().accept(this, r);
    node.getExpression().accept(this, r);
    return null;
  }
  
  public R visitUnary(UnaryTree node, P r) {
    node.getExpression().accept(this, r);
    return null;
  }
  
  public R visitVariable(VariableTree node, P r) {
    if (node.getInitializer() != null)
      node.getInitializer().accept(this, r); 
    return null;
  }
  
  public R visitWhileLoop(WhileLoopTree node, P r) {
    node.getCondition().accept(this, r);
    node.getStatement().accept(this, r);
    return null;
  }
  
  public R visitWith(WithTree node, P r) {
    node.getScope().accept(this, r);
    node.getStatement().accept(this, r);
    return null;
  }
  
  public R visitYield(YieldTree node, P p) {
    return visitUnknown(node, p);
  }
  
  public R visitUnknown(Tree node, P p) {
    throw new UnknownTreeException(node, p);
  }
}
