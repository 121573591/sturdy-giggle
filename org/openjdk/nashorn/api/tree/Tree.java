package org.openjdk.nashorn.api.tree;

public interface Tree {
  long getStartPosition();
  
  long getEndPosition();
  
  Kind getKind();
  
  <R, D> R accept(TreeVisitor<R, D> paramTreeVisitor, D paramD);
  
  public enum Kind {
    ARRAY_ACCESS((String)ArrayAccessTree.class),
    ARRAY_LITERAL((String)ArrayLiteralTree.class),
    ASSIGNMENT((String)AssignmentTree.class),
    BLOCK((String)BlockTree.class),
    BREAK((String)BreakTree.class),
    CLASS((String)ClassDeclarationTree.class),
    CLASS_EXPRESSION((String)ClassExpressionTree.class),
    CASE((String)CaseTree.class),
    CATCH((String)CatchTree.class),
    COMPILATION_UNIT((String)CompilationUnitTree.class),
    CONDITIONAL_EXPRESSION((String)ConditionalExpressionTree.class),
    CONTINUE((String)ContinueTree.class),
    DO_WHILE_LOOP((String)DoWhileLoopTree.class),
    DEBUGGER((String)DebuggerTree.class),
    FOR_IN_LOOP((String)ForInLoopTree.class),
    FUNCTION_EXPRESSION((String)FunctionExpressionTree.class),
    ERROR((String)ErroneousTree.class),
    EXPRESSION_STATEMENT((String)ExpressionStatementTree.class),
    MEMBER_SELECT((String)MemberSelectTree.class),
    FOR_LOOP((String)ForLoopTree.class),
    IDENTIFIER((String)IdentifierTree.class),
    IF((String)IfTree.class),
    INSTANCE_OF((String)InstanceOfTree.class),
    LABELED_STATEMENT((String)LabeledStatementTree.class),
    MODULE((String)ModuleTree.class),
    EXPORT_ENTRY((String)ExportEntryTree.class),
    IMPORT_ENTRY((String)ImportEntryTree.class),
    FUNCTION((String)FunctionDeclarationTree.class),
    FUNCTION_INVOCATION((String)FunctionCallTree.class),
    NEW((String)NewTree.class),
    OBJECT_LITERAL((String)ObjectLiteralTree.class),
    PARENTHESIZED((String)ParenthesizedTree.class),
    PROPERTY((String)PropertyTree.class),
    REGEXP_LITERAL((String)RegExpLiteralTree.class),
    TEMPLATE_LITERAL((String)TemplateLiteralTree.class),
    RETURN((String)ReturnTree.class),
    EMPTY_STATEMENT((String)EmptyStatementTree.class),
    SWITCH((String)SwitchTree.class),
    THROW((String)ThrowTree.class),
    TRY((String)TryTree.class),
    VARIABLE((String)VariableTree.class),
    WHILE_LOOP((String)WhileLoopTree.class),
    WITH((String)WithTree.class),
    POSTFIX_INCREMENT((String)UnaryTree.class),
    POSTFIX_DECREMENT((String)UnaryTree.class),
    PREFIX_INCREMENT((String)UnaryTree.class),
    PREFIX_DECREMENT((String)UnaryTree.class),
    UNARY_PLUS((String)UnaryTree.class),
    UNARY_MINUS((String)UnaryTree.class),
    BITWISE_COMPLEMENT((String)UnaryTree.class),
    LOGICAL_COMPLEMENT((String)UnaryTree.class),
    DELETE((String)UnaryTree.class),
    TYPEOF((String)UnaryTree.class),
    VOID((String)UnaryTree.class),
    COMMA((String)BinaryTree.class),
    MULTIPLY((String)BinaryTree.class),
    DIVIDE((String)BinaryTree.class),
    REMAINDER((String)BinaryTree.class),
    PLUS((String)BinaryTree.class),
    MINUS((String)BinaryTree.class),
    LEFT_SHIFT((String)BinaryTree.class),
    RIGHT_SHIFT((String)BinaryTree.class),
    UNSIGNED_RIGHT_SHIFT((String)BinaryTree.class),
    LESS_THAN((String)BinaryTree.class),
    GREATER_THAN((String)BinaryTree.class),
    LESS_THAN_EQUAL((String)BinaryTree.class),
    GREATER_THAN_EQUAL((String)BinaryTree.class),
    IN((String)BinaryTree.class),
    EQUAL_TO((String)BinaryTree.class),
    NOT_EQUAL_TO((String)BinaryTree.class),
    STRICT_EQUAL_TO((String)BinaryTree.class),
    STRICT_NOT_EQUAL_TO((String)BinaryTree.class),
    AND((String)BinaryTree.class),
    XOR((String)BinaryTree.class),
    OR((String)BinaryTree.class),
    CONDITIONAL_AND((String)BinaryTree.class),
    CONDITIONAL_OR((String)BinaryTree.class),
    MULTIPLY_ASSIGNMENT((String)CompoundAssignmentTree.class),
    DIVIDE_ASSIGNMENT((String)CompoundAssignmentTree.class),
    REMAINDER_ASSIGNMENT((String)CompoundAssignmentTree.class),
    PLUS_ASSIGNMENT((String)CompoundAssignmentTree.class),
    MINUS_ASSIGNMENT((String)CompoundAssignmentTree.class),
    LEFT_SHIFT_ASSIGNMENT((String)CompoundAssignmentTree.class),
    RIGHT_SHIFT_ASSIGNMENT((String)CompoundAssignmentTree.class),
    UNSIGNED_RIGHT_SHIFT_ASSIGNMENT((String)CompoundAssignmentTree.class),
    AND_ASSIGNMENT((String)CompoundAssignmentTree.class),
    XOR_ASSIGNMENT((String)CompoundAssignmentTree.class),
    OR_ASSIGNMENT((String)CompoundAssignmentTree.class),
    SPREAD((String)SpreadTree.class),
    YIELD((String)YieldTree.class),
    NUMBER_LITERAL((String)LiteralTree.class),
    BOOLEAN_LITERAL((String)LiteralTree.class),
    STRING_LITERAL((String)LiteralTree.class),
    NULL_LITERAL((String)LiteralTree.class),
    OTHER(null);
    
    private final Class<? extends Tree> associatedInterface;
    
    Kind(Class<? extends Tree> intf) {
      this.associatedInterface = intf;
    }
    
    public Class<? extends Tree> asInterface() {
      return this.associatedInterface;
    }
    
    public boolean isLiteral() {
      return (this.associatedInterface == LiteralTree.class);
    }
    
    public boolean isExpression() {
      return ExpressionTree.class.isAssignableFrom(this.associatedInterface);
    }
    
    public boolean isStatement() {
      return StatementTree.class.isAssignableFrom(this.associatedInterface);
    }
  }
}
