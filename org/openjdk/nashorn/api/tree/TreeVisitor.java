package org.openjdk.nashorn.api.tree;

public interface TreeVisitor<R, P> {
  R visitAssignment(AssignmentTree paramAssignmentTree, P paramP);
  
  R visitCompoundAssignment(CompoundAssignmentTree paramCompoundAssignmentTree, P paramP);
  
  R visitBinary(BinaryTree paramBinaryTree, P paramP);
  
  R visitBlock(BlockTree paramBlockTree, P paramP);
  
  R visitBreak(BreakTree paramBreakTree, P paramP);
  
  R visitCase(CaseTree paramCaseTree, P paramP);
  
  R visitCatch(CatchTree paramCatchTree, P paramP);
  
  R visitClassDeclaration(ClassDeclarationTree paramClassDeclarationTree, P paramP);
  
  R visitClassExpression(ClassExpressionTree paramClassExpressionTree, P paramP);
  
  R visitConditionalExpression(ConditionalExpressionTree paramConditionalExpressionTree, P paramP);
  
  R visitContinue(ContinueTree paramContinueTree, P paramP);
  
  R visitDebugger(DebuggerTree paramDebuggerTree, P paramP);
  
  R visitDoWhileLoop(DoWhileLoopTree paramDoWhileLoopTree, P paramP);
  
  R visitErroneous(ErroneousTree paramErroneousTree, P paramP);
  
  R visitExpressionStatement(ExpressionStatementTree paramExpressionStatementTree, P paramP);
  
  R visitForLoop(ForLoopTree paramForLoopTree, P paramP);
  
  R visitForInLoop(ForInLoopTree paramForInLoopTree, P paramP);
  
  R visitForOfLoop(ForOfLoopTree paramForOfLoopTree, P paramP);
  
  R visitFunctionCall(FunctionCallTree paramFunctionCallTree, P paramP);
  
  R visitFunctionDeclaration(FunctionDeclarationTree paramFunctionDeclarationTree, P paramP);
  
  R visitFunctionExpression(FunctionExpressionTree paramFunctionExpressionTree, P paramP);
  
  R visitIdentifier(IdentifierTree paramIdentifierTree, P paramP);
  
  R visitIf(IfTree paramIfTree, P paramP);
  
  R visitArrayAccess(ArrayAccessTree paramArrayAccessTree, P paramP);
  
  R visitArrayLiteral(ArrayLiteralTree paramArrayLiteralTree, P paramP);
  
  R visitLabeledStatement(LabeledStatementTree paramLabeledStatementTree, P paramP);
  
  R visitLiteral(LiteralTree paramLiteralTree, P paramP);
  
  R visitParenthesized(ParenthesizedTree paramParenthesizedTree, P paramP);
  
  R visitReturn(ReturnTree paramReturnTree, P paramP);
  
  R visitMemberSelect(MemberSelectTree paramMemberSelectTree, P paramP);
  
  R visitNew(NewTree paramNewTree, P paramP);
  
  R visitObjectLiteral(ObjectLiteralTree paramObjectLiteralTree, P paramP);
  
  R visitProperty(PropertyTree paramPropertyTree, P paramP);
  
  R visitRegExpLiteral(RegExpLiteralTree paramRegExpLiteralTree, P paramP);
  
  R visitTemplateLiteral(TemplateLiteralTree paramTemplateLiteralTree, P paramP);
  
  R visitEmptyStatement(EmptyStatementTree paramEmptyStatementTree, P paramP);
  
  R visitSpread(SpreadTree paramSpreadTree, P paramP);
  
  R visitSwitch(SwitchTree paramSwitchTree, P paramP);
  
  R visitThrow(ThrowTree paramThrowTree, P paramP);
  
  R visitCompilationUnit(CompilationUnitTree paramCompilationUnitTree, P paramP);
  
  R visitModule(ModuleTree paramModuleTree, P paramP);
  
  R visitExportEntry(ExportEntryTree paramExportEntryTree, P paramP);
  
  R visitImportEntry(ImportEntryTree paramImportEntryTree, P paramP);
  
  R visitTry(TryTree paramTryTree, P paramP);
  
  R visitInstanceOf(InstanceOfTree paramInstanceOfTree, P paramP);
  
  R visitUnary(UnaryTree paramUnaryTree, P paramP);
  
  R visitVariable(VariableTree paramVariableTree, P paramP);
  
  R visitWhileLoop(WhileLoopTree paramWhileLoopTree, P paramP);
  
  R visitWith(WithTree paramWithTree, P paramP);
  
  R visitYield(YieldTree paramYieldTree, P paramP);
  
  R visitUnknown(Tree paramTree, P paramP);
}
