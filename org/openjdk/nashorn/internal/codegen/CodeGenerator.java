package org.openjdk.nashorn.internal.codegen;

import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.Supplier;
import org.openjdk.nashorn.internal.AssertsEnabled;
import org.openjdk.nashorn.internal.IntDeque;
import org.openjdk.nashorn.internal.codegen.types.ArrayType;
import org.openjdk.nashorn.internal.codegen.types.Type;
import org.openjdk.nashorn.internal.ir.AccessNode;
import org.openjdk.nashorn.internal.ir.BaseNode;
import org.openjdk.nashorn.internal.ir.BinaryNode;
import org.openjdk.nashorn.internal.ir.Block;
import org.openjdk.nashorn.internal.ir.BlockStatement;
import org.openjdk.nashorn.internal.ir.BreakNode;
import org.openjdk.nashorn.internal.ir.CallNode;
import org.openjdk.nashorn.internal.ir.CaseNode;
import org.openjdk.nashorn.internal.ir.CatchNode;
import org.openjdk.nashorn.internal.ir.ContinueNode;
import org.openjdk.nashorn.internal.ir.EmptyNode;
import org.openjdk.nashorn.internal.ir.Expression;
import org.openjdk.nashorn.internal.ir.ExpressionStatement;
import org.openjdk.nashorn.internal.ir.ForNode;
import org.openjdk.nashorn.internal.ir.FunctionNode;
import org.openjdk.nashorn.internal.ir.GetSplitState;
import org.openjdk.nashorn.internal.ir.IdentNode;
import org.openjdk.nashorn.internal.ir.IfNode;
import org.openjdk.nashorn.internal.ir.IndexNode;
import org.openjdk.nashorn.internal.ir.JoinPredecessor;
import org.openjdk.nashorn.internal.ir.JoinPredecessorExpression;
import org.openjdk.nashorn.internal.ir.JumpStatement;
import org.openjdk.nashorn.internal.ir.JumpToInlinedFinally;
import org.openjdk.nashorn.internal.ir.LabelNode;
import org.openjdk.nashorn.internal.ir.LexicalContext;
import org.openjdk.nashorn.internal.ir.LexicalContextNode;
import org.openjdk.nashorn.internal.ir.LiteralNode;
import org.openjdk.nashorn.internal.ir.LocalVariableConversion;
import org.openjdk.nashorn.internal.ir.LoopNode;
import org.openjdk.nashorn.internal.ir.Node;
import org.openjdk.nashorn.internal.ir.ObjectNode;
import org.openjdk.nashorn.internal.ir.Optimistic;
import org.openjdk.nashorn.internal.ir.PropertyNode;
import org.openjdk.nashorn.internal.ir.ReturnNode;
import org.openjdk.nashorn.internal.ir.RuntimeNode;
import org.openjdk.nashorn.internal.ir.SetSplitState;
import org.openjdk.nashorn.internal.ir.SplitReturn;
import org.openjdk.nashorn.internal.ir.Splittable;
import org.openjdk.nashorn.internal.ir.Statement;
import org.openjdk.nashorn.internal.ir.SwitchNode;
import org.openjdk.nashorn.internal.ir.Symbol;
import org.openjdk.nashorn.internal.ir.TernaryNode;
import org.openjdk.nashorn.internal.ir.ThrowNode;
import org.openjdk.nashorn.internal.ir.TryNode;
import org.openjdk.nashorn.internal.ir.UnaryNode;
import org.openjdk.nashorn.internal.ir.VarNode;
import org.openjdk.nashorn.internal.ir.WhileNode;
import org.openjdk.nashorn.internal.ir.WithNode;
import org.openjdk.nashorn.internal.ir.visitor.NodeOperatorVisitor;
import org.openjdk.nashorn.internal.ir.visitor.NodeVisitor;
import org.openjdk.nashorn.internal.ir.visitor.SimpleNodeVisitor;
import org.openjdk.nashorn.internal.objects.Global;
import org.openjdk.nashorn.internal.parser.Lexer;
import org.openjdk.nashorn.internal.parser.TokenType;
import org.openjdk.nashorn.internal.runtime.Context;
import org.openjdk.nashorn.internal.runtime.Debug;
import org.openjdk.nashorn.internal.runtime.ECMAException;
import org.openjdk.nashorn.internal.runtime.JSType;
import org.openjdk.nashorn.internal.runtime.OptimisticReturnFilters;
import org.openjdk.nashorn.internal.runtime.PropertyMap;
import org.openjdk.nashorn.internal.runtime.RecompilableScriptFunctionData;
import org.openjdk.nashorn.internal.runtime.RewriteException;
import org.openjdk.nashorn.internal.runtime.Scope;
import org.openjdk.nashorn.internal.runtime.ScriptEnvironment;
import org.openjdk.nashorn.internal.runtime.ScriptFunction;
import org.openjdk.nashorn.internal.runtime.ScriptObject;
import org.openjdk.nashorn.internal.runtime.ScriptRuntime;
import org.openjdk.nashorn.internal.runtime.Source;
import org.openjdk.nashorn.internal.runtime.UnwarrantedOptimismException;
import org.openjdk.nashorn.internal.runtime.arrays.ArrayData;
import org.openjdk.nashorn.internal.runtime.logging.DebugLogger;
import org.openjdk.nashorn.internal.runtime.logging.Loggable;
import org.openjdk.nashorn.internal.runtime.logging.Logger;
import org.openjdk.nashorn.internal.runtime.options.Options;

@Logger(name = "codegen")
final class CodeGenerator extends NodeOperatorVisitor<CodeGeneratorLexicalContext> implements Loggable {
  private static final Type SCOPE_TYPE = Type.typeFor(ScriptObject.class);
  
  private static final String GLOBAL_OBJECT = Type.getInternalName(Global.class);
  
  private static final CompilerConstants.Call CREATE_REWRITE_EXCEPTION = CompilerConstants.staticCallNoLookup(RewriteException.class, "create", RewriteException.class, new Class[] { UnwarrantedOptimismException.class, Object[].class, String[].class });
  
  private static final CompilerConstants.Call CREATE_REWRITE_EXCEPTION_REST_OF = CompilerConstants.staticCallNoLookup(RewriteException.class, "create", RewriteException.class, new Class[] { UnwarrantedOptimismException.class, Object[].class, String[].class, int[].class });
  
  private static final CompilerConstants.Call ENSURE_INT = CompilerConstants.staticCallNoLookup(OptimisticReturnFilters.class, "ensureInt", int.class, new Class[] { Object.class, int.class });
  
  private static final CompilerConstants.Call ENSURE_NUMBER = CompilerConstants.staticCallNoLookup(OptimisticReturnFilters.class, "ensureNumber", double.class, new Class[] { Object.class, int.class });
  
  private static final CompilerConstants.Call CREATE_FUNCTION_OBJECT = CompilerConstants.staticCallNoLookup(ScriptFunction.class, "create", ScriptFunction.class, new Class[] { Object[].class, int.class, ScriptObject.class });
  
  private static final CompilerConstants.Call CREATE_FUNCTION_OBJECT_NO_SCOPE = CompilerConstants.staticCallNoLookup(ScriptFunction.class, "create", ScriptFunction.class, new Class[] { Object[].class, int.class });
  
  private static final CompilerConstants.Call TO_NUMBER_FOR_EQ = CompilerConstants.staticCallNoLookup(JSType.class, "toNumberForEq", double.class, new Class[] { Object.class });
  
  private static final CompilerConstants.Call TO_NUMBER_FOR_STRICT_EQ = CompilerConstants.staticCallNoLookup(JSType.class, "toNumberForStrictEq", double.class, new Class[] { Object.class });
  
  private static final Class<?> ITERATOR_CLASS = Iterator.class;
  
  static {
    assert ITERATOR_CLASS == CompilerConstants.ITERATOR_PREFIX.type();
  }
  
  private static final Type ITERATOR_TYPE = Type.typeFor(ITERATOR_CLASS);
  
  private static final Type EXCEPTION_TYPE = Type.typeFor(CompilerConstants.EXCEPTION_PREFIX.type());
  
  private static final Integer INT_ZERO = Integer.valueOf(0);
  
  private final Compiler compiler;
  
  private final boolean evalCode;
  
  private final int callSiteFlags;
  
  private int regexFieldCount;
  
  private int lastLineNumber = -1;
  
  private static final int MAX_REGEX_FIELDS = 2048;
  
  private MethodEmitter method;
  
  private CompileUnit unit;
  
  private final DebugLogger log;
  
  static final int OBJECT_SPILL_THRESHOLD = Options.getIntProperty("nashorn.spill.threshold", 256);
  
  private final Set<String> emittedMethods = new HashSet<>();
  
  private ContinuationInfo continuationInfo;
  
  private final Deque<Label> scopeEntryLabels = new ArrayDeque<>();
  
  private static final Label METHOD_BOUNDARY = new Label("");
  
  private final Deque<Label> catchLabels = new ArrayDeque<>();
  
  private final IntDeque labeledBlockBreakLiveLocals = new IntDeque();
  
  private final int[] continuationEntryPoints;
  
  private final Deque<FieldObjectCreator<?>> scopeObjectCreators = new ArrayDeque<>();
  
  CodeGenerator(Compiler compiler, int[] continuationEntryPoints) {
    super(new CodeGeneratorLexicalContext());
    this.compiler = compiler;
    this.evalCode = compiler.getSource().isEvalCode();
    this.continuationEntryPoints = continuationEntryPoints;
    this.callSiteFlags = (compiler.getScriptEnvironment())._callsite_flags;
    this.log = initLogger(compiler.getContext());
  }
  
  public DebugLogger getLogger() {
    return this.log;
  }
  
  public DebugLogger initLogger(Context context) {
    return context.getLogger(getClass());
  }
  
  int getCallSiteFlags() {
    return ((CodeGeneratorLexicalContext)this.lc).getCurrentFunction().getCallSiteFlags() | this.callSiteFlags;
  }
  
  private int getScopeCallSiteFlags(Symbol symbol) {
    assert symbol.isScope();
    int flags = getCallSiteFlags() | 0x10;
    if (isEvalCode() && symbol.isGlobal())
      return flags; 
    return isFastScope(symbol) ? (flags | 0x40) : flags;
  }
  
  boolean isEvalCode() {
    return this.evalCode;
  }
  
  boolean useDualFields() {
    return this.compiler.getContext().useDualFields();
  }
  
  private void loadIdent(IdentNode identNode, final TypeBounds resultBounds) {
    checkTemporalDeadZone(identNode);
    final Symbol symbol = identNode.getSymbol();
    if (!symbol.isScope()) {
      Type type = identNode.getType();
      if (type == Type.UNDEFINED) {
        this.method.loadUndefined(resultBounds.widest);
      } else {
        assert symbol.hasSlot() || symbol.isParam();
        this.method.load(identNode);
      } 
    } else {
      final int flags = getScopeCallSiteFlags(symbol);
      if (!isFastScope(symbol)) {
        (new LoadScopeVar(identNode, resultBounds, flags)).emit();
      } else if (identNode.isCompileTimePropertyName() || symbol.getUseCount() < SharedScopeCall.SHARED_GET_THRESHOLD) {
        (new LoadFastScopeVar(identNode, resultBounds, flags)).emit();
      } else {
        (new OptimisticOperation((Optimistic)identNode, resultBounds) {
            void loadStack() {
              CodeGenerator.this.method.loadCompilerConstant(CompilerConstants.SCOPE);
              int depth = CodeGenerator.this.getScopeProtoDepth(((CodeGeneratorLexicalContext)CodeGenerator.this.lc).getCurrentBlock(), symbol);
              assert depth >= 0;
              CodeGenerator.this.method.load(depth);
              CodeGenerator.this.method.load(getProgramPoint());
            }
            
            void consumeStack() {
              Type resultType = this.isOptimistic ? getOptimisticCoercedType() : resultBounds.widest;
              ((CodeGeneratorLexicalContext)CodeGenerator.this.lc).getScopeGet(CodeGenerator.this.unit, symbol, resultType, flags, this.isOptimistic).generateInvoke(CodeGenerator.this.method);
            }
          }).emit();
      } 
    } 
  }
  
  private void checkTemporalDeadZone(IdentNode identNode) {
    if (identNode.isDead())
      this.method.load(identNode.getSymbol().getName()).invoke(ScriptRuntime.THROW_REFERENCE_ERROR); 
  }
  
  private void checkAssignTarget(Expression expression) {
    if (expression instanceof IdentNode && ((IdentNode)expression).getSymbol().isConst())
      this.method.load(((IdentNode)expression).getSymbol().getName()).invoke(ScriptRuntime.THROW_CONST_TYPE_ERROR); 
  }
  
  private boolean isRestOf() {
    return (this.continuationEntryPoints != null);
  }
  
  private boolean isCurrentContinuationEntryPoint(int programPoint) {
    return (isRestOf() && getCurrentContinuationEntryPoint() == programPoint);
  }
  
  private int[] getContinuationEntryPoints() {
    return isRestOf() ? this.continuationEntryPoints : null;
  }
  
  private int getCurrentContinuationEntryPoint() {
    return isRestOf() ? this.continuationEntryPoints[0] : -1;
  }
  
  private boolean isContinuationEntryPoint(int programPoint) {
    if (isRestOf()) {
      assert this.continuationEntryPoints != null;
      for (int cep : this.continuationEntryPoints) {
        if (cep == programPoint)
          return true; 
      } 
    } 
    return false;
  }
  
  private boolean isFastScope(Symbol symbol) {
    if (!symbol.isScope())
      return false; 
    if (!((CodeGeneratorLexicalContext)this.lc).inDynamicScope()) {
      assert symbol.isGlobal() || ((CodeGeneratorLexicalContext)this.lc).getDefiningBlock(symbol).needsScope() : symbol.getName();
      return true;
    } 
    if (symbol.isGlobal())
      return false; 
    String name = symbol.getName();
    boolean previousWasBlock = false;
    for (Iterator<LexicalContextNode> it = ((CodeGeneratorLexicalContext)this.lc).getAllNodes(); it.hasNext(); ) {
      LexicalContextNode node = it.next();
      if (node instanceof Block) {
        Block block = (Block)node;
        if (block.getExistingSymbol(name) == symbol) {
          assert block.needsScope();
          return true;
        } 
        previousWasBlock = true;
        continue;
      } 
      if ((node instanceof WithNode && previousWasBlock) || (node instanceof FunctionNode && ((FunctionNode)node).needsDynamicScope()))
        return false; 
      previousWasBlock = false;
    } 
    throw new AssertionError();
  }
  
  private class LoadScopeVar extends OptimisticOperation {
    final IdentNode identNode;
    
    private final int flags;
    
    LoadScopeVar(IdentNode identNode, CodeGenerator.TypeBounds resultBounds, int flags) {
      super((Optimistic)identNode, resultBounds);
      this.identNode = identNode;
      this.flags = flags;
    }
    
    void loadStack() {
      CodeGenerator.this.method.loadCompilerConstant(CompilerConstants.SCOPE);
      getProto();
    }
    
    void getProto() {}
    
    void consumeStack() {
      if (this.identNode.isCompileTimePropertyName()) {
        CodeGenerator.this.method.dynamicGet(Type.OBJECT, this.identNode.getSymbol().getName(), this.flags, this.identNode.isFunction(), false);
        replaceCompileTimeProperty();
      } else {
        dynamicGet(this.identNode.getSymbol().getName(), this.flags, this.identNode.isFunction(), false);
      } 
    }
  }
  
  private class LoadFastScopeVar extends LoadScopeVar {
    LoadFastScopeVar(IdentNode identNode, CodeGenerator.TypeBounds resultBounds, int flags) {
      super(identNode, resultBounds, flags);
    }
    
    void getProto() {
      CodeGenerator.this.loadFastScopeProto(this.identNode.getSymbol(), false);
    }
  }
  
  private void storeFastScopeVar(Symbol symbol, int flags) {
    loadFastScopeProto(symbol, true);
    this.method.dynamicSet(symbol.getName(), flags, false);
  }
  
  private int getScopeProtoDepth(Block startingBlock, Symbol symbol) {
    int depth;
    FunctionNode fn = ((CodeGeneratorLexicalContext)this.lc).getCurrentFunction();
    int externalDepth = this.compiler.getScriptFunctionData(fn.getId()).getExternalSymbolDepth(symbol.getName());
    int internalDepth = FindScopeDepths.findInternalDepth(this.lc, fn, startingBlock, symbol);
    int scopesToStart = FindScopeDepths.findScopesToStart(this.lc, fn, startingBlock);
    if (internalDepth == -1) {
      depth = scopesToStart + externalDepth;
    } else {
      assert internalDepth <= scopesToStart;
      depth = internalDepth;
    } 
    return depth;
  }
  
  private void loadFastScopeProto(Symbol symbol, boolean swap) {
    int depth = getScopeProtoDepth(((CodeGeneratorLexicalContext)this.lc).getCurrentBlock(), symbol);
    assert depth != -1 : "Couldn't find scope depth for symbol " + symbol.getName() + " in " + ((CodeGeneratorLexicalContext)this.lc).getCurrentFunction();
    if (depth > 0) {
      if (swap)
        this.method.swap(); 
      invokeGetProto(depth);
      if (swap)
        this.method.swap(); 
    } 
  }
  
  private void invokeGetProto(int depth) {
    assert depth > 0;
    if (depth > 1) {
      this.method.load(depth);
      this.method.invoke(ScriptObject.GET_PROTO_DEPTH);
    } else {
      this.method.invoke(ScriptObject.GET_PROTO);
    } 
  }
  
  private void loadExpressionUnbounded(Expression expr) {
    loadExpression(expr, TypeBounds.UNBOUNDED);
  }
  
  private void loadExpressionAsObject(Expression expr) {
    loadExpression(expr, TypeBounds.OBJECT);
  }
  
  void loadExpressionAsBoolean(Expression expr) {
    loadExpression(expr, TypeBounds.BOOLEAN);
  }
  
  private static boolean noToPrimitiveConversion(Type source, Type target) {
    return (source.isJSPrimitive() || !target.isJSPrimitive() || target.isBoolean());
  }
  
  MethodEmitter loadBinaryOperands(BinaryNode binaryNode) {
    return loadBinaryOperands(binaryNode.lhs(), binaryNode.rhs(), TypeBounds.UNBOUNDED.notWiderThan(binaryNode.getWidestOperandType()), false, false);
  }
  
  private MethodEmitter loadBinaryOperands(Expression lhs, Expression rhs, TypeBounds explicitOperandBounds, boolean baseAlreadyOnStack, boolean forceConversionSeparation) {
    Type lhsType = undefinedToNumber(lhs.getType());
    Type rhsType = undefinedToNumber(rhs.getType());
    Type narrowestOperandType = Type.narrowest(Type.widest(lhsType, rhsType), explicitOperandBounds.widest);
    TypeBounds operandBounds = explicitOperandBounds.notNarrowerThan(narrowestOperandType);
    if (noToPrimitiveConversion(lhsType, explicitOperandBounds.widest) || rhs.isLocal()) {
      if (forceConversionSeparation) {
        TypeBounds safeConvertBounds = TypeBounds.UNBOUNDED.notNarrowerThan(narrowestOperandType);
        loadExpression(lhs, safeConvertBounds, baseAlreadyOnStack);
        this.method.convert(operandBounds.within(this.method.peekType()));
        loadExpression(rhs, safeConvertBounds, false);
        this.method.convert(operandBounds.within(this.method.peekType()));
      } else {
        loadExpression(lhs, operandBounds, baseAlreadyOnStack);
        loadExpression(rhs, operandBounds, false);
      } 
    } else {
      TypeBounds safeConvertBounds = TypeBounds.UNBOUNDED.notNarrowerThan(narrowestOperandType);
      loadExpression(lhs, safeConvertBounds, baseAlreadyOnStack);
      Type lhsLoadedType = this.method.peekType();
      loadExpression(rhs, safeConvertBounds, false);
      Type convertedLhsType = operandBounds.within(this.method.peekType());
      if (convertedLhsType != lhsLoadedType)
        this.method.swap().convert(convertedLhsType).swap(); 
      this.method.convert(operandBounds.within(this.method.peekType()));
    } 
    assert Type.generic(this.method.peekType()) == operandBounds.narrowest;
    assert Type.generic(this.method.peekType(1)) == operandBounds.narrowest;
    return this.method;
  }
  
  void loadComparisonOperands(BinaryNode cmp) {
    Expression lhs = cmp.lhs();
    Expression rhs = cmp.rhs();
    Type lhsType = lhs.getType();
    Type rhsType = rhs.getType();
    assert !lhsType.isObject() || !rhsType.isObject();
    if (lhsType.isObject() || rhsType.isObject()) {
      boolean canReorder = (lhsType.isPrimitive() || rhs.isLocal());
      boolean canCombineLoadAndConvert = (canReorder && cmp.isRelational());
      loadExpression(lhs, (canCombineLoadAndConvert && !lhs.isOptimistic()) ? TypeBounds.NUMBER : TypeBounds.UNBOUNDED);
      Type lhsLoadedType = this.method.peekType();
      TokenType tt = cmp.tokenType();
      if (canReorder) {
        emitObjectToNumberComparisonConversion(this.method, tt);
        loadExpression(rhs, (canCombineLoadAndConvert && !rhs.isOptimistic()) ? TypeBounds.NUMBER : TypeBounds.UNBOUNDED);
      } else {
        loadExpression(rhs, TypeBounds.UNBOUNDED);
        if (lhsLoadedType != Type.NUMBER) {
          this.method.swap();
          emitObjectToNumberComparisonConversion(this.method, tt);
          this.method.swap();
        } 
      } 
      emitObjectToNumberComparisonConversion(this.method, tt);
    } else {
      loadBinaryOperands(cmp);
    } 
  }
  
  private static void emitObjectToNumberComparisonConversion(MethodEmitter method, TokenType tt) {
    switch (tt) {
      case EQ:
      case NE:
        if (method.peekType().isObject()) {
          TO_NUMBER_FOR_EQ.invoke(method);
          return;
        } 
        break;
      case EQ_STRICT:
      case NE_STRICT:
        if (method.peekType().isObject()) {
          TO_NUMBER_FOR_STRICT_EQ.invoke(method);
          return;
        } 
        break;
    } 
    method.convert((Type)Type.NUMBER);
  }
  
  private static Type undefinedToNumber(Type type) {
    return (type == Type.UNDEFINED) ? (Type)Type.NUMBER : type;
  }
  
  private static final class TypeBounds {
    final Type narrowest;
    
    final Type widest;
    
    static final TypeBounds UNBOUNDED = new TypeBounds(Type.UNKNOWN, Type.OBJECT);
    
    static final TypeBounds INT = exact((Type)Type.INT);
    
    static final TypeBounds NUMBER = exact((Type)Type.NUMBER);
    
    static final TypeBounds OBJECT = exact(Type.OBJECT);
    
    static final TypeBounds BOOLEAN = exact(Type.BOOLEAN);
    
    static TypeBounds exact(Type type) {
      return new TypeBounds(type, type);
    }
    
    TypeBounds(Type narrowest, Type widest) {
      assert widest != null && widest != Type.UNDEFINED && widest != Type.UNKNOWN : widest;
      assert narrowest != null && narrowest != Type.UNDEFINED : narrowest;
      assert !narrowest.widerThan(widest) : "" + narrowest + " wider than " + narrowest;
      assert !widest.narrowerThan(narrowest);
      this.narrowest = Type.generic(narrowest);
      this.widest = Type.generic(widest);
    }
    
    TypeBounds notNarrowerThan(Type type) {
      return maybeNew(Type.narrowest(Type.widest(this.narrowest, type), this.widest), this.widest);
    }
    
    TypeBounds notWiderThan(Type type) {
      return maybeNew(Type.narrowest(this.narrowest, type), Type.narrowest(this.widest, type));
    }
    
    boolean canBeNarrowerThan(Type type) {
      return this.narrowest.narrowerThan(type);
    }
    
    TypeBounds maybeNew(Type newNarrowest, Type newWidest) {
      if (newNarrowest == this.narrowest && newWidest == this.widest)
        return this; 
      return new TypeBounds(newNarrowest, newWidest);
    }
    
    TypeBounds booleanToInt() {
      return maybeNew(CodeGenerator.booleanToInt(this.narrowest), CodeGenerator.booleanToInt(this.widest));
    }
    
    TypeBounds objectToNumber() {
      return maybeNew(CodeGenerator.objectToNumber(this.narrowest), CodeGenerator.objectToNumber(this.widest));
    }
    
    Type within(Type type) {
      if (type.narrowerThan(this.narrowest))
        return this.narrowest; 
      if (type.widerThan(this.widest))
        return this.widest; 
      return type;
    }
    
    public String toString() {
      return "[" + this.narrowest + ", " + this.widest + "]";
    }
  }
  
  private static Type booleanToInt(Type t) {
    return (t == Type.BOOLEAN) ? (Type)Type.INT : t;
  }
  
  private static Type objectToNumber(Type t) {
    return t.isObject() ? (Type)Type.NUMBER : t;
  }
  
  void loadExpressionAsType(Expression expr, Type type) {
    if (type == Type.BOOLEAN) {
      loadExpressionAsBoolean(expr);
    } else if (type == Type.UNDEFINED) {
      assert expr.getType() == Type.UNDEFINED;
      loadExpressionAsObject(expr);
    } else {
      loadExpression(expr, TypeBounds.UNBOUNDED.notNarrowerThan(type)).convert(type);
    } 
  }
  
  private MethodEmitter loadExpression(Expression expr, TypeBounds resultBounds) {
    return loadExpression(expr, resultBounds, false);
  }
  
  private MethodEmitter loadExpression(Expression expr, final TypeBounds resultBounds, final boolean baseAlreadyOnStack) {
    final CodeGenerator codegen = this;
    boolean isCurrentDiscard = ((CodeGeneratorLexicalContext)codegen.lc).isCurrentDiscard(expr);
    expr.accept((NodeVisitor)new NodeOperatorVisitor<LexicalContext>(new LexicalContext()) {
          public boolean enterIdentNode(IdentNode identNode) {
            CodeGenerator.this.loadIdent(identNode, resultBounds);
            return false;
          }
          
          public boolean enterAccessNode(final AccessNode accessNode) {
            (new CodeGenerator.OptimisticOperation((Optimistic)accessNode, resultBounds) {
                void loadStack() {
                  if (!baseAlreadyOnStack)
                    CodeGenerator.this.loadExpressionAsObject(accessNode.getBase()); 
                  assert CodeGenerator.this.method.peekType().isObject();
                }
                
                void consumeStack() {
                  int flags = CodeGenerator.this.getCallSiteFlags();
                  dynamicGet(accessNode.getProperty(), flags, accessNode.isFunction(), accessNode.isIndex());
                }
              }).emit(baseAlreadyOnStack ? 1 : 0);
            return false;
          }
          
          public boolean enterIndexNode(final IndexNode indexNode) {
            (new CodeGenerator.OptimisticOperation((Optimistic)indexNode, resultBounds) {
                void loadStack() {
                  if (!baseAlreadyOnStack) {
                    CodeGenerator.this.loadExpressionAsObject(indexNode.getBase());
                    CodeGenerator.this.loadExpressionUnbounded(indexNode.getIndex());
                  } 
                }
                
                void consumeStack() {
                  int flags = CodeGenerator.this.getCallSiteFlags();
                  dynamicGetIndex(flags, indexNode.isFunction());
                }
              }).emit(baseAlreadyOnStack ? 2 : 0);
            return false;
          }
          
          public boolean enterFunctionNode(FunctionNode functionNode) {
            this.lc.pop((Node)functionNode);
            functionNode.accept((NodeVisitor)codegen);
            this.lc.push((LexicalContextNode)functionNode);
            return false;
          }
          
          public boolean enterASSIGN(BinaryNode binaryNode) {
            CodeGenerator.this.checkAssignTarget(binaryNode.lhs());
            CodeGenerator.this.loadASSIGN(binaryNode);
            return false;
          }
          
          public boolean enterASSIGN_ADD(BinaryNode binaryNode) {
            CodeGenerator.this.checkAssignTarget(binaryNode.lhs());
            CodeGenerator.this.loadASSIGN_ADD(binaryNode);
            return false;
          }
          
          public boolean enterASSIGN_BIT_AND(BinaryNode binaryNode) {
            CodeGenerator.this.checkAssignTarget(binaryNode.lhs());
            CodeGenerator.this.loadASSIGN_BIT_AND(binaryNode);
            return false;
          }
          
          public boolean enterASSIGN_BIT_OR(BinaryNode binaryNode) {
            CodeGenerator.this.checkAssignTarget(binaryNode.lhs());
            CodeGenerator.this.loadASSIGN_BIT_OR(binaryNode);
            return false;
          }
          
          public boolean enterASSIGN_BIT_XOR(BinaryNode binaryNode) {
            CodeGenerator.this.checkAssignTarget(binaryNode.lhs());
            CodeGenerator.this.loadASSIGN_BIT_XOR(binaryNode);
            return false;
          }
          
          public boolean enterASSIGN_DIV(BinaryNode binaryNode) {
            CodeGenerator.this.checkAssignTarget(binaryNode.lhs());
            CodeGenerator.this.loadASSIGN_DIV(binaryNode);
            return false;
          }
          
          public boolean enterASSIGN_MOD(BinaryNode binaryNode) {
            CodeGenerator.this.checkAssignTarget(binaryNode.lhs());
            CodeGenerator.this.loadASSIGN_MOD(binaryNode);
            return false;
          }
          
          public boolean enterASSIGN_MUL(BinaryNode binaryNode) {
            CodeGenerator.this.checkAssignTarget(binaryNode.lhs());
            CodeGenerator.this.loadASSIGN_MUL(binaryNode);
            return false;
          }
          
          public boolean enterASSIGN_SAR(BinaryNode binaryNode) {
            CodeGenerator.this.checkAssignTarget(binaryNode.lhs());
            CodeGenerator.this.loadASSIGN_SAR(binaryNode);
            return false;
          }
          
          public boolean enterASSIGN_SHL(BinaryNode binaryNode) {
            CodeGenerator.this.checkAssignTarget(binaryNode.lhs());
            CodeGenerator.this.loadASSIGN_SHL(binaryNode);
            return false;
          }
          
          public boolean enterASSIGN_SHR(BinaryNode binaryNode) {
            CodeGenerator.this.checkAssignTarget(binaryNode.lhs());
            CodeGenerator.this.loadASSIGN_SHR(binaryNode);
            return false;
          }
          
          public boolean enterASSIGN_SUB(BinaryNode binaryNode) {
            CodeGenerator.this.checkAssignTarget(binaryNode.lhs());
            CodeGenerator.this.loadASSIGN_SUB(binaryNode);
            return false;
          }
          
          public boolean enterCallNode(CallNode callNode) {
            return CodeGenerator.this.loadCallNode(callNode, resultBounds);
          }
          
          public boolean enterLiteralNode(LiteralNode<?> literalNode) {
            CodeGenerator.this.loadLiteral(literalNode, resultBounds);
            return false;
          }
          
          public boolean enterTernaryNode(TernaryNode ternaryNode) {
            CodeGenerator.this.loadTernaryNode(ternaryNode, resultBounds);
            return false;
          }
          
          public boolean enterADD(BinaryNode binaryNode) {
            CodeGenerator.this.loadADD(binaryNode, resultBounds);
            return false;
          }
          
          public boolean enterNEG(UnaryNode unaryNode) {
            CodeGenerator.this.loadSUB(unaryNode, resultBounds);
            return false;
          }
          
          public boolean enterSUB(BinaryNode binaryNode) {
            CodeGenerator.this.loadSUB(binaryNode, resultBounds);
            return false;
          }
          
          public boolean enterMUL(BinaryNode binaryNode) {
            CodeGenerator.this.loadMUL(binaryNode, resultBounds);
            return false;
          }
          
          public boolean enterDIV(BinaryNode binaryNode) {
            CodeGenerator.this.loadDIV(binaryNode, resultBounds);
            return false;
          }
          
          public boolean enterMOD(BinaryNode binaryNode) {
            CodeGenerator.this.loadMOD(binaryNode, resultBounds);
            return false;
          }
          
          public boolean enterSAR(BinaryNode binaryNode) {
            CodeGenerator.this.loadSAR(binaryNode);
            return false;
          }
          
          public boolean enterSHL(BinaryNode binaryNode) {
            CodeGenerator.this.loadSHL(binaryNode);
            return false;
          }
          
          public boolean enterSHR(BinaryNode binaryNode) {
            CodeGenerator.this.loadSHR(binaryNode);
            return false;
          }
          
          public boolean enterCOMMARIGHT(BinaryNode binaryNode) {
            CodeGenerator.this.loadCOMMARIGHT(binaryNode, resultBounds);
            return false;
          }
          
          public boolean enterAND(BinaryNode binaryNode) {
            CodeGenerator.this.loadAND_OR(binaryNode, resultBounds, true);
            return false;
          }
          
          public boolean enterOR(BinaryNode binaryNode) {
            CodeGenerator.this.loadAND_OR(binaryNode, resultBounds, false);
            return false;
          }
          
          public boolean enterNOT(UnaryNode unaryNode) {
            CodeGenerator.this.loadNOT(unaryNode);
            return false;
          }
          
          public boolean enterPOS(UnaryNode unaryNode) {
            CodeGenerator.this.loadADD(unaryNode, resultBounds);
            return false;
          }
          
          public boolean enterBIT_NOT(UnaryNode unaryNode) {
            CodeGenerator.this.loadBIT_NOT(unaryNode);
            return false;
          }
          
          public boolean enterBIT_AND(BinaryNode binaryNode) {
            CodeGenerator.this.loadBIT_AND(binaryNode);
            return false;
          }
          
          public boolean enterBIT_OR(BinaryNode binaryNode) {
            CodeGenerator.this.loadBIT_OR(binaryNode);
            return false;
          }
          
          public boolean enterBIT_XOR(BinaryNode binaryNode) {
            CodeGenerator.this.loadBIT_XOR(binaryNode);
            return false;
          }
          
          public boolean enterVOID(UnaryNode unaryNode) {
            CodeGenerator.this.loadVOID(unaryNode, resultBounds);
            return false;
          }
          
          public boolean enterDELETE(UnaryNode unaryNode) {
            CodeGenerator.this.loadDELETE(unaryNode);
            return false;
          }
          
          public boolean enterEQ(BinaryNode binaryNode) {
            CodeGenerator.this.loadCmp(binaryNode, Condition.EQ);
            return false;
          }
          
          public boolean enterEQ_STRICT(BinaryNode binaryNode) {
            CodeGenerator.this.loadCmp(binaryNode, Condition.EQ);
            return false;
          }
          
          public boolean enterGE(BinaryNode binaryNode) {
            CodeGenerator.this.loadCmp(binaryNode, Condition.GE);
            return false;
          }
          
          public boolean enterGT(BinaryNode binaryNode) {
            CodeGenerator.this.loadCmp(binaryNode, Condition.GT);
            return false;
          }
          
          public boolean enterLE(BinaryNode binaryNode) {
            CodeGenerator.this.loadCmp(binaryNode, Condition.LE);
            return false;
          }
          
          public boolean enterLT(BinaryNode binaryNode) {
            CodeGenerator.this.loadCmp(binaryNode, Condition.LT);
            return false;
          }
          
          public boolean enterNE(BinaryNode binaryNode) {
            CodeGenerator.this.loadCmp(binaryNode, Condition.NE);
            return false;
          }
          
          public boolean enterNE_STRICT(BinaryNode binaryNode) {
            CodeGenerator.this.loadCmp(binaryNode, Condition.NE);
            return false;
          }
          
          public boolean enterObjectNode(ObjectNode objectNode) {
            CodeGenerator.this.loadObjectNode(objectNode);
            return false;
          }
          
          public boolean enterRuntimeNode(RuntimeNode runtimeNode) {
            CodeGenerator.this.loadRuntimeNode(runtimeNode);
            return false;
          }
          
          public boolean enterNEW(UnaryNode unaryNode) {
            CodeGenerator.this.loadNEW(unaryNode);
            return false;
          }
          
          public boolean enterDECINC(UnaryNode unaryNode) {
            CodeGenerator.this.checkAssignTarget(unaryNode.getExpression());
            CodeGenerator.this.loadDECINC(unaryNode);
            return false;
          }
          
          public boolean enterJoinPredecessorExpression(JoinPredecessorExpression joinExpr) {
            CodeGenerator.this.loadMaybeDiscard((Expression)joinExpr, joinExpr.getExpression(), resultBounds);
            return false;
          }
          
          public boolean enterGetSplitState(GetSplitState getSplitState) {
            CodeGenerator.this.method.loadScope();
            CodeGenerator.this.method.invoke(Scope.GET_SPLIT_STATE);
            return false;
          }
          
          public boolean enterDefault(Node otherNode) {
            throw new AssertionError(otherNode.getClass().getName());
          }
        });
    if (!isCurrentDiscard)
      coerceStackTop(resultBounds); 
    return this.method;
  }
  
  private void coerceStackTop(TypeBounds typeBounds) {
    this.method.convert(typeBounds.within(this.method.peekType()));
  }
  
  private void closeBlockVariables(Block block) {
    for (Symbol symbol : block.getSymbols()) {
      if (symbol.isBytecodeLocal())
        this.method.closeLocalVariable(symbol, block.getBreakLabel()); 
    } 
  }
  
  public boolean enterBlock(Block block) {
    Label entryLabel = block.getEntryLabel();
    if (entryLabel.isBreakTarget()) {
      assert !this.method.isReachable();
      this.method.breakLabel(entryLabel, ((CodeGeneratorLexicalContext)this.lc).getUsedSlotCount());
    } else {
      this.method.label(entryLabel);
    } 
    if (!this.method.isReachable())
      return false; 
    if (((CodeGeneratorLexicalContext)this.lc).isFunctionBody() && this.emittedMethods.contains(((CodeGeneratorLexicalContext)this.lc).getCurrentFunction().getName()))
      return false; 
    initLocals(block);
    assert ((CodeGeneratorLexicalContext)this.lc).getUsedSlotCount() == this.method.getFirstTemp();
    return true;
  }
  
  boolean useOptimisticTypes() {
    return (!((CodeGeneratorLexicalContext)this.lc).inSplitLiteral() && this.compiler.useOptimisticTypes());
  }
  
  public Node leaveBlock(Block block) {
    popBlockScope(block);
    this.method.beforeJoinPoint((JoinPredecessor)block);
    closeBlockVariables(block);
    ((CodeGeneratorLexicalContext)this.lc).releaseSlots();
    assert !this.method.isReachable() || (((CodeGeneratorLexicalContext)this.lc).isFunctionBody() ? 0 : ((CodeGeneratorLexicalContext)this.lc).getUsedSlotCount()) == this.method.getFirstTemp() : "reachable=" + this.method
      .isReachable() + " isFunctionBody=" + ((CodeGeneratorLexicalContext)this.lc)
      .isFunctionBody() + " usedSlotCount=" + ((CodeGeneratorLexicalContext)this.lc)
      .getUsedSlotCount() + " firstTemp=" + this.method
      .getFirstTemp();
    return (Node)block;
  }
  
  private void popBlockScope(Block block) {
    Label breakLabel = block.getBreakLabel();
    if (block.providesScopeCreator())
      this.scopeObjectCreators.pop(); 
    if (!block.needsScope() || ((CodeGeneratorLexicalContext)this.lc).isFunctionBody()) {
      emitBlockBreakLabel(breakLabel);
      return;
    } 
    Label beginTryLabel = this.scopeEntryLabels.pop();
    Label recoveryLabel = new Label("block_popscope_catch");
    emitBlockBreakLabel(breakLabel);
    boolean bodyCanThrow = breakLabel.isAfter(beginTryLabel);
    if (bodyCanThrow)
      this.method._try(beginTryLabel, breakLabel, recoveryLabel); 
    Label afterCatchLabel = null;
    if (this.method.isReachable()) {
      popScope();
      if (bodyCanThrow) {
        afterCatchLabel = new Label("block_after_catch");
        this.method._goto(afterCatchLabel);
      } 
    } 
    if (bodyCanThrow) {
      assert !this.method.isReachable();
      this.method._catch(recoveryLabel);
      popScopeException();
      this.method.athrow();
    } 
    if (afterCatchLabel != null)
      this.method.label(afterCatchLabel); 
  }
  
  private void emitBlockBreakLabel(Label breakLabel) {
    LabelNode labelNode = ((CodeGeneratorLexicalContext)this.lc).getCurrentBlockLabelNode();
    if (labelNode != null) {
      assert labelNode.getLocalVariableConversion() == null || this.method.isReachable();
      this.method.beforeJoinPoint((JoinPredecessor)labelNode);
      this.method.breakLabel(breakLabel, this.labeledBlockBreakLiveLocals.pop());
    } else {
      this.method.label(breakLabel);
    } 
  }
  
  private void popScope() {
    popScopes(1);
  }
  
  private void popScopeException() {
    popScope();
    ContinuationInfo ci = getContinuationInfo();
    if (ci != null) {
      Label catchLabel = ci.catchLabel;
      if (catchLabel != METHOD_BOUNDARY && catchLabel == this.catchLabels.peek())
        ci.exceptionScopePops++; 
    } 
  }
  
  private void popScopesUntil(LexicalContextNode until) {
    popScopes(((CodeGeneratorLexicalContext)this.lc).getScopeNestingLevelTo(until));
  }
  
  private void popScopes(int count) {
    if (count == 0)
      return; 
    assert count > 0;
    if (!this.method.hasScope())
      return; 
    this.method.loadCompilerConstant(CompilerConstants.SCOPE);
    invokeGetProto(count);
    this.method.storeCompilerConstant(CompilerConstants.SCOPE);
  }
  
  public boolean enterBreakNode(BreakNode breakNode) {
    return enterJumpStatement((JumpStatement)breakNode);
  }
  
  public boolean enterJumpToInlinedFinally(JumpToInlinedFinally jumpToInlinedFinally) {
    return enterJumpStatement((JumpStatement)jumpToInlinedFinally);
  }
  
  private boolean enterJumpStatement(JumpStatement jump) {
    if (!this.method.isReachable())
      return false; 
    enterStatement((Statement)jump);
    this.method.beforeJoinPoint((JoinPredecessor)jump);
    popScopesUntil(jump.getPopScopeLimit(this.lc));
    Label targetLabel = jump.getTargetLabel(this.lc);
    targetLabel.markAsBreakTarget();
    this.method._goto(targetLabel);
    return false;
  }
  
  private int loadArgs(List<Expression> args) {
    int argCount = args.size();
    if (argCount > 125) {
      loadArgsArray(args);
      return 1;
    } 
    for (Expression arg : args) {
      assert arg != null;
      loadExpressionUnbounded(arg);
    } 
    return argCount;
  }
  
  private boolean loadCallNode(final CallNode callNode, final TypeBounds resultBounds) {
    lineNumber(callNode.getLineNumber());
    final List<Expression> args = callNode.getArgs();
    final Expression function = callNode.getFunction();
    final Block currentBlock = ((CodeGeneratorLexicalContext)this.lc).getCurrentBlock();
    final CodeGeneratorLexicalContext codegenLexicalContext = (CodeGeneratorLexicalContext)this.lc;
    function.accept((NodeVisitor)new SimpleNodeVisitor() {
          private void sharedScopeCall(final IdentNode identNode, final int flags) {
            final Symbol symbol = identNode.getSymbol();
            assert CodeGenerator.this.isFastScope(symbol);
            (new CodeGenerator.OptimisticOperation((Optimistic)callNode, resultBounds) {
                void loadStack() {
                  CodeGenerator.this.method.loadCompilerConstant(CompilerConstants.SCOPE);
                  int depth = CodeGenerator.this.getScopeProtoDepth(currentBlock, symbol);
                  assert depth >= 0;
                  CodeGenerator.this.method.load(depth);
                  CodeGenerator.this.method.load(getProgramPoint());
                  CodeGenerator.this.loadArgs(args);
                }
                
                void consumeStack() {
                  Type[] paramTypes = CodeGenerator.this.method.getTypesFromStack(args.size());
                  for (int i = 0; i < paramTypes.length; i++)
                    paramTypes[i] = Type.generic(paramTypes[i]); 
                  Type resultType = this.isOptimistic ? getOptimisticCoercedType() : resultBounds.widest;
                  SharedScopeCall scopeCall = codegenLexicalContext.getScopeCall(CodeGenerator.this.unit, symbol, identNode
                      .getType(), resultType, paramTypes, flags, this.isOptimistic);
                  scopeCall.generateInvoke(CodeGenerator.this.method);
                }
              }).emit();
          }
          
          private void scopeCall(final IdentNode ident, final int flags) {
            (new CodeGenerator.OptimisticOperation((Optimistic)callNode, resultBounds) {
                int argsCount;
                
                void loadStack() {
                  CodeGenerator.this.loadExpressionAsObject((Expression)ident);
                  CodeGenerator.this.method.loadUndefined(Type.OBJECT);
                  this.argsCount = CodeGenerator.this.loadArgs(args);
                }
                
                void consumeStack() {
                  dynamicCall(2 + this.argsCount, flags, ident.getName());
                }
              }).emit();
          }
          
          private void evalCall(final IdentNode ident, final int flags) {
            final Label invoke_direct_eval = new Label("invoke_direct_eval");
            final Label is_not_eval = new Label("is_not_eval");
            final Label eval_done = new Label("eval_done");
            (new CodeGenerator.OptimisticOperation((Optimistic)callNode, resultBounds) {
                int argsCount;
                
                void loadStack() {
                  CodeGenerator.this.loadExpressionAsObject((Expression)ident.setIsNotFunction());
                  CodeGenerator.this.globalIsEval();
                  CodeGenerator.this.method.ifeq(is_not_eval);
                  CodeGenerator.this.method.loadCompilerConstant(CompilerConstants.SCOPE);
                  List<Expression> evalArgs = callNode.getEvalArgs().getArgs();
                  CodeGenerator.this.loadExpressionAsObject(evalArgs.get(0));
                  int numArgs = evalArgs.size();
                  for (int i = 1; i < numArgs; i++)
                    CodeGenerator.this.loadAndDiscard(evalArgs.get(i)); 
                  CodeGenerator.this.method._goto(invoke_direct_eval);
                  CodeGenerator.this.method.label(is_not_eval);
                  CodeGenerator.this.loadExpressionAsObject((Expression)ident);
                  CodeGenerator.this.method.loadNull();
                  this.argsCount = CodeGenerator.this.loadArgs(callNode.getArgs());
                }
                
                void consumeStack() {
                  dynamicCall(2 + this.argsCount, flags, "eval");
                  CodeGenerator.this.method._goto(eval_done);
                  CodeGenerator.this.method.label(invoke_direct_eval);
                  CodeGenerator.this.method.loadCompilerConstant(CompilerConstants.THIS);
                  CodeGenerator.this.method.load(callNode.getEvalArgs().getLocation());
                  CodeGenerator.this.method.load(((CodeGeneratorLexicalContext)CodeGenerator.this.lc).getCurrentFunction().isStrict());
                  CodeGenerator.this.globalDirectEval();
                  convertOptimisticReturnValue();
                  CodeGenerator.this.coerceStackTop(resultBounds);
                }
              }).emit();
            CodeGenerator.this.method.label(eval_done);
          }
          
          public boolean enterIdentNode(IdentNode node) {
            Symbol symbol = node.getSymbol();
            if (symbol.isScope()) {
              int flags = CodeGenerator.this.getScopeCallSiteFlags(symbol);
              if (callNode.isEval()) {
                evalCall(node, flags);
              } else if (!CodeGenerator.this.isFastScope(symbol) || symbol.getUseCount() < SharedScopeCall.SHARED_CALL_THRESHOLD) {
                scopeCall(node, flags);
              } else {
                sharedScopeCall(node, flags);
              } 
              assert CodeGenerator.this.method.peekType().equals(resultBounds.within(callNode.getType())) : "" + CodeGenerator.this.method.peekType() + " != " + CodeGenerator.this.method.peekType() + "(" + resultBounds + ")";
            } else {
              enterDefault((Node)node);
            } 
            return false;
          }
          
          public boolean enterAccessNode(final AccessNode node) {
            final int flags = CodeGenerator.this.getCallSiteFlags() | (callNode.isApplyToCall() ? 256 : 0);
            (new CodeGenerator.OptimisticOperation((Optimistic)callNode, resultBounds) {
                int argCount;
                
                void loadStack() {
                  CodeGenerator.this.loadExpressionAsObject(node.getBase());
                  CodeGenerator.this.method.dup();
                  assert !node.isOptimistic();
                  CodeGenerator.this.method.dynamicGet(node.getType(), node.getProperty(), flags, true, node.isIndex());
                  CodeGenerator.this.method.swap();
                  this.argCount = CodeGenerator.this.loadArgs(args);
                }
                
                void consumeStack() {
                  dynamicCall(2 + this.argCount, flags, node.toString(false));
                }
              }).emit();
            return false;
          }
          
          public boolean enterFunctionNode(final FunctionNode origCallee) {
            (new CodeGenerator.OptimisticOperation((Optimistic)callNode, resultBounds) {
                FunctionNode callee;
                
                int argsCount;
                
                void loadStack() {
                  this.callee = (FunctionNode)origCallee.accept((NodeVisitor)CodeGenerator.this);
                  if (this.callee.isStrict()) {
                    CodeGenerator.this.method.loadUndefined(Type.OBJECT);
                  } else {
                    CodeGenerator.this.globalInstance();
                  } 
                  this.argsCount = CodeGenerator.this.loadArgs(args);
                }
                
                void consumeStack() {
                  dynamicCall(2 + this.argsCount, CodeGenerator.this.getCallSiteFlags(), (String)null);
                }
              }).emit();
            return false;
          }
          
          public boolean enterIndexNode(final IndexNode node) {
            (new CodeGenerator.OptimisticOperation((Optimistic)callNode, resultBounds) {
                int argsCount;
                
                void loadStack() {
                  CodeGenerator.this.loadExpressionAsObject(node.getBase());
                  CodeGenerator.this.method.dup();
                  Type indexType = node.getIndex().getType();
                  if (indexType.isObject() || indexType.isBoolean()) {
                    CodeGenerator.this.loadExpressionAsObject(node.getIndex());
                  } else {
                    CodeGenerator.this.loadExpressionUnbounded(node.getIndex());
                  } 
                  assert !node.isOptimistic();
                  CodeGenerator.this.method.dynamicGetIndex(node.getType(), CodeGenerator.this.getCallSiteFlags(), true);
                  CodeGenerator.this.method.swap();
                  this.argsCount = CodeGenerator.this.loadArgs(args);
                }
                
                void consumeStack() {
                  dynamicCall(2 + this.argsCount, CodeGenerator.this.getCallSiteFlags(), node.toString(false));
                }
              }).emit();
            return false;
          }
          
          protected boolean enterDefault(final Node node) {
            (new CodeGenerator.OptimisticOperation((Optimistic)callNode, resultBounds) {
                int argsCount;
                
                void loadStack() {
                  CodeGenerator.this.loadExpressionAsObject(function);
                  CodeGenerator.this.method.loadUndefined(Type.OBJECT);
                  this.argsCount = CodeGenerator.this.loadArgs(args);
                }
                
                void consumeStack() {
                  int flags = CodeGenerator.this.getCallSiteFlags() | 0x10;
                  dynamicCall(2 + this.argsCount, flags, node.toString(false));
                }
              }).emit();
            return false;
          }
        });
    return false;
  }
  
  static int nonOptimisticFlags(int flags) {
    return flags & 0x7F7F;
  }
  
  public boolean enterContinueNode(ContinueNode continueNode) {
    return enterJumpStatement((JumpStatement)continueNode);
  }
  
  public boolean enterEmptyNode(EmptyNode emptyNode) {
    return false;
  }
  
  public boolean enterExpressionStatement(ExpressionStatement expressionStatement) {
    if (!this.method.isReachable())
      return false; 
    enterStatement((Statement)expressionStatement);
    loadAndDiscard(expressionStatement.getExpression());
    assert this.method.getStackSize() == 0 : "stack not empty in " + expressionStatement;
    return false;
  }
  
  public boolean enterBlockStatement(BlockStatement blockStatement) {
    if (!this.method.isReachable())
      return false; 
    enterStatement((Statement)blockStatement);
    blockStatement.getBlock().accept((NodeVisitor)this);
    return false;
  }
  
  public boolean enterForNode(ForNode forNode) {
    if (!this.method.isReachable())
      return false; 
    enterStatement((Statement)forNode);
    if (forNode.isForInOrOf()) {
      enterForIn(forNode);
    } else {
      Expression init = forNode.getInit();
      if (init != null)
        loadAndDiscard(init); 
      enterForOrWhile((LoopNode)forNode, forNode.getModify());
    } 
    return false;
  }
  
  private void enterForIn(final ForNode forNode) {
    loadExpression((Expression)forNode.getModify(), TypeBounds.OBJECT);
    if (forNode.isForEach()) {
      this.method.invoke(ScriptRuntime.TO_VALUE_ITERATOR);
    } else if (forNode.isForIn()) {
      this.method.invoke(ScriptRuntime.TO_PROPERTY_ITERATOR);
    } else if (forNode.isForOf()) {
      this.method.invoke(ScriptRuntime.TO_ES6_ITERATOR);
    } else {
      throw new IllegalArgumentException("Unexpected for node");
    } 
    Symbol iterSymbol = forNode.getIterator();
    final int iterSlot = iterSymbol.getSlot(Type.OBJECT);
    this.method.store(iterSymbol, ITERATOR_TYPE);
    this.method.beforeJoinPoint((JoinPredecessor)forNode);
    Label continueLabel = forNode.getContinueLabel();
    Label breakLabel = forNode.getBreakLabel();
    this.method.label(continueLabel);
    this.method.load(ITERATOR_TYPE, iterSlot);
    this.method.invoke(CompilerConstants.interfaceCallNoLookup(ITERATOR_CLASS, "hasNext", boolean.class, new Class[0]));
    JoinPredecessorExpression test = forNode.getTest();
    Block body = forNode.getBody();
    if (LocalVariableConversion.hasLiveConversion((JoinPredecessor)test)) {
      Label afterConversion = new Label("for_in_after_test_conv");
      this.method.ifne(afterConversion);
      this.method.beforeJoinPoint((JoinPredecessor)test);
      this.method._goto(breakLabel);
      this.method.label(afterConversion);
    } else {
      this.method.ifeq(breakLabel);
    } 
    (new Store<Expression>(forNode.getInit()) {
        protected void storeNonDiscard() {}
        
        protected void evaluate() {
          (new CodeGenerator.OptimisticOperation((Optimistic)forNode.getInit(), CodeGenerator.TypeBounds.UNBOUNDED) {
              void loadStack() {
                CodeGenerator.this.method.load(CodeGenerator.ITERATOR_TYPE, iterSlot);
              }
              
              void consumeStack() {
                CodeGenerator.this.method.invoke(CompilerConstants.interfaceCallNoLookup(CodeGenerator.ITERATOR_CLASS, "next", Object.class, new Class[0]));
                convertOptimisticReturnValue();
              }
            }).emit();
        }
      }).store();
    body.accept((NodeVisitor)this);
    if (forNode.needsScopeCreator() && ((CodeGeneratorLexicalContext)this.lc).getCurrentBlock().providesScopeCreator()) {
      FieldObjectCreator<?> creator = this.scopeObjectCreators.peek();
      assert creator != null;
      creator.createForInIterationScope(this.method);
      this.method.storeCompilerConstant(CompilerConstants.SCOPE);
    } 
    if (this.method.isReachable())
      this.method._goto(continueLabel); 
    this.method.label(breakLabel);
  }
  
  private void initLocals(Block block) {
    ((CodeGeneratorLexicalContext)this.lc).onEnterBlock(block);
    boolean isFunctionBody = ((CodeGeneratorLexicalContext)this.lc).isFunctionBody();
    FunctionNode function = ((CodeGeneratorLexicalContext)this.lc).getCurrentFunction();
    if (isFunctionBody) {
      initializeMethodParameters(function);
      if (!function.isVarArg())
        expandParameterSlots(function); 
      if (this.method.hasScope()) {
        if (function.needsParentScope()) {
          this.method.loadCompilerConstant(CompilerConstants.CALLEE);
          this.method.invoke(ScriptFunction.GET_SCOPE);
        } else {
          assert function.hasScopeBlock();
          this.method.loadNull();
        } 
        this.method.storeCompilerConstant(CompilerConstants.SCOPE);
      } 
      if (function.needsArguments())
        initArguments(function); 
    } 
    if (block.needsScope()) {
      boolean varsInScope = function.allVarsInScope();
      boolean hasArguments = function.needsArguments();
      List<MapTuple<Symbol>> tuples = new ArrayList<>();
      Iterator<IdentNode> paramIter = function.getParameters().iterator();
      for (Symbol symbol : block.getSymbols()) {
        if (symbol.isInternal() || symbol.isThis())
          continue; 
        if (symbol.isVar()) {
          assert !varsInScope || symbol.isScope();
          if (varsInScope || symbol.isScope()) {
            assert symbol.isScope() : "scope for " + symbol + " should have been set in Lower already " + function.getName();
            assert !symbol.hasSlot() : "slot for " + symbol + " should have been removed in Lower already" + function.getName();
            tuples.add(new MapTuple<>(symbol.getName(), symbol, null));
            continue;
          } 
          assert symbol.hasSlot() || symbol.slotCount() == 0 : "" + symbol + " should have a slot only, no scope";
          continue;
        } 
        if (symbol.isParam() && (varsInScope || hasArguments || symbol.isScope())) {
          final Type paramType;
          Symbol paramSymbol;
          assert symbol.isScope() : "scope for " + symbol + " should have been set in AssignSymbols already " + function.getName() + " varsInScope=" + varsInScope + " hasArguments=" + hasArguments + " symbol.isScope()=false";
          assert !hasArguments || !symbol.hasSlot() : "slot for " + symbol + " should have been removed in Lower already " + function.getName();
          if (hasArguments) {
            assert !symbol.hasSlot() : "slot for " + symbol + " should have been removed in Lower already ";
            paramSymbol = null;
            paramType = null;
          } else {
            paramSymbol = symbol;
            while (true) {
              IdentNode nextParam = paramIter.next();
              if (nextParam.getName().equals(symbol.getName())) {
                paramType = nextParam.getType();
                break;
              } 
            } 
          } 
          tuples.add(new MapTuple<Symbol>(symbol.getName(), symbol, paramType, paramSymbol) {
                public Class<?> getValueType() {
                  if (!CodeGenerator.this.useDualFields() || this.value == null || paramType == null || paramType.isBoolean())
                    return Object.class; 
                  return paramType.getTypeClass();
                }
              });
        } 
      } 
      FieldObjectCreator<Symbol> creator = new FieldObjectCreator<Symbol>(this, tuples, true, hasArguments) {
          protected void loadValue(Symbol value, Type type) {
            CodeGenerator.this.method.load(value, type);
          }
        };
      creator.makeObject(this.method);
      if (block.providesScopeCreator())
        this.scopeObjectCreators.push(creator); 
      if (isFunctionBody && function.isProgram())
        this.method.invoke(ScriptRuntime.MERGE_SCOPE); 
      this.method.storeCompilerConstant(CompilerConstants.SCOPE);
      if (!isFunctionBody) {
        Label scopeEntryLabel = new Label("scope_entry");
        this.scopeEntryLabels.push(scopeEntryLabel);
        this.method.label(scopeEntryLabel);
      } 
    } else if (isFunctionBody && function.isVarArg()) {
      int nextParam = 0;
      for (IdentNode param : function.getParameters())
        param.getSymbol().setFieldIndex(nextParam++); 
    } 
    printSymbols(block, function, (isFunctionBody ? "Function " : "Block in ") + (isFunctionBody ? "Function " : "Block in "));
  }
  
  private void initializeMethodParameters(FunctionNode function) {
    Label functionStart = new Label("fn_start");
    this.method.label(functionStart);
    int nextSlot = 0;
    if (function.needsCallee())
      initializeInternalFunctionParameter(CompilerConstants.CALLEE, function, functionStart, nextSlot++); 
    initializeInternalFunctionParameter(CompilerConstants.THIS, function, functionStart, nextSlot++);
    if (function.isVarArg()) {
      initializeInternalFunctionParameter(CompilerConstants.VARARGS, function, functionStart, nextSlot++);
    } else {
      for (IdentNode param : function.getParameters()) {
        Symbol symbol = param.getSymbol();
        if (symbol.isBytecodeLocal())
          this.method.initializeMethodParameter(symbol, param.getType(), functionStart); 
      } 
    } 
  }
  
  private void initializeInternalFunctionParameter(CompilerConstants cc, FunctionNode fn, Label functionStart, int slot) {
    Symbol symbol = initializeInternalFunctionOrSplitParameter(cc, fn, functionStart, slot);
    assert symbol.getFirstSlot() == slot;
  }
  
  private Symbol initializeInternalFunctionOrSplitParameter(CompilerConstants cc, FunctionNode fn, Label functionStart, int slot) {
    Symbol symbol = fn.getBody().getExistingSymbol(cc.symbolName());
    Type type = Type.typeFor(cc.type());
    this.method.initializeMethodParameter(symbol, type, functionStart);
    this.method.onLocalStore(type, slot);
    return symbol;
  }
  
  private void expandParameterSlots(FunctionNode function) {
    List<IdentNode> parameters = function.getParameters();
    int currentIncomingSlot = function.needsCallee() ? 2 : 1;
    for (IdentNode parameter : parameters)
      currentIncomingSlot += parameter.getType().getSlots(); 
    for (int i = parameters.size(); i-- > 0; ) {
      IdentNode parameter = parameters.get(i);
      Type parameterType = parameter.getType();
      int typeWidth = parameterType.getSlots();
      currentIncomingSlot -= typeWidth;
      Symbol symbol = parameter.getSymbol();
      int slotCount = symbol.slotCount();
      assert slotCount > 0;
      assert symbol.isBytecodeLocal() || slotCount == typeWidth;
      this.method.onLocalStore(parameterType, currentIncomingSlot);
      if (currentIncomingSlot != symbol.getSlot(parameterType)) {
        this.method.load(parameterType, currentIncomingSlot);
        this.method.store(symbol, parameterType);
      } 
    } 
  }
  
  private void initArguments(FunctionNode function) {
    this.method.loadCompilerConstant(CompilerConstants.VARARGS);
    if (function.needsCallee()) {
      this.method.loadCompilerConstant(CompilerConstants.CALLEE);
    } else {
      assert function.isStrict();
      this.method.loadNull();
    } 
    this.method.load(function.getParameters().size());
    globalAllocateArguments();
    this.method.storeCompilerConstant(CompilerConstants.ARGUMENTS);
  }
  
  private boolean skipFunction(FunctionNode functionNode) {
    ScriptEnvironment env = this.compiler.getScriptEnvironment();
    boolean lazy = env._lazy_compilation;
    boolean onDemand = this.compiler.isOnDemandCompilation();
    if ((onDemand || lazy) && ((CodeGeneratorLexicalContext)this.lc).getOutermostFunction() != functionNode)
      return true; 
    return (!onDemand && lazy && env._optimistic_types && functionNode.isProgram());
  }
  
  public boolean enterFunctionNode(FunctionNode functionNode) {
    if (skipFunction(functionNode)) {
      newFunctionObject(functionNode, false);
      return false;
    } 
    String fnName = functionNode.getName();
    if (!this.emittedMethods.contains(fnName)) {
      this.log.info(new Object[] { "=== BEGIN ", fnName });
      assert functionNode.getCompileUnit() != null : "no compile unit for " + fnName + " " + Debug.id(functionNode);
      this.unit = ((CodeGeneratorLexicalContext)this.lc).pushCompileUnit(functionNode.getCompileUnit());
      assert ((CodeGeneratorLexicalContext)this.lc).hasCompileUnits();
      ClassEmitter classEmitter = this.unit.getClassEmitter();
      pushMethodEmitter(isRestOf() ? classEmitter.restOfMethod(functionNode) : classEmitter.method(functionNode));
      this.method.setPreventUndefinedLoad();
      if (useOptimisticTypes())
        ((CodeGeneratorLexicalContext)this.lc).pushUnwarrantedOptimismHandlers(); 
      this.lastLineNumber = -1;
      this.method.begin();
      if (isRestOf()) {
        assert this.continuationInfo == null;
        this.continuationInfo = new ContinuationInfo();
        this.method.gotoLoopStart(this.continuationInfo.getHandlerLabel());
      } 
    } 
    return true;
  }
  
  private void pushMethodEmitter(MethodEmitter newMethod) {
    this.method = ((CodeGeneratorLexicalContext)this.lc).pushMethodEmitter(newMethod);
    this.catchLabels.push(METHOD_BOUNDARY);
  }
  
  private void popMethodEmitter() {
    this.method = ((CodeGeneratorLexicalContext)this.lc).popMethodEmitter(this.method);
    assert this.catchLabels.peek() == METHOD_BOUNDARY;
    this.catchLabels.pop();
  }
  
  public Node leaveFunctionNode(FunctionNode functionNode) {
    try {
      boolean markOptimistic;
      if (this.emittedMethods.add(functionNode.getName())) {
        markOptimistic = generateUnwarrantedOptimismExceptionHandlers(functionNode);
        generateContinuationHandler();
        this.method.end();
        this.unit = ((CodeGeneratorLexicalContext)this.lc).popCompileUnit(functionNode.getCompileUnit());
        popMethodEmitter();
        this.log.info(new Object[] { "=== END ", functionNode.getName() });
      } else {
        markOptimistic = false;
      } 
      FunctionNode newFunctionNode = functionNode;
      if (markOptimistic)
        newFunctionNode = newFunctionNode.setFlag(this.lc, 2048); 
      newFunctionObject(newFunctionNode, true);
      return (Node)newFunctionNode;
    } catch (Throwable t) {
      Context.printStackTrace(t);
      VerifyError e = new VerifyError("Code generation bug in \"" + functionNode.getName() + "\": likely stack misaligned: " + t + " " + functionNode.getSource().getName());
      e.initCause(t);
      throw e;
    } 
  }
  
  public boolean enterIfNode(IfNode ifNode) {
    if (!this.method.isReachable())
      return false; 
    enterStatement((Statement)ifNode);
    Expression test = ifNode.getTest();
    Block pass = ifNode.getPass();
    Block fail = ifNode.getFail();
    if (Expression.isAlwaysTrue(test)) {
      loadAndDiscard(test);
      pass.accept((NodeVisitor)this);
      return false;
    } 
    if (Expression.isAlwaysFalse(test)) {
      loadAndDiscard(test);
      if (fail != null)
        fail.accept((NodeVisitor)this); 
      return false;
    } 
    boolean hasFailConversion = LocalVariableConversion.hasLiveConversion((JoinPredecessor)ifNode);
    Label failLabel = new Label("if_fail");
    Label afterLabel = (fail == null && !hasFailConversion) ? null : new Label("if_done");
    emitBranch(test, failLabel, false);
    pass.accept((NodeVisitor)this);
    if (this.method.isReachable() && afterLabel != null)
      this.method._goto(afterLabel); 
    this.method.label(failLabel);
    if (fail != null) {
      fail.accept((NodeVisitor)this);
    } else if (hasFailConversion) {
      this.method.beforeJoinPoint((JoinPredecessor)ifNode);
    } 
    if (afterLabel != null && afterLabel.isReachable())
      this.method.label(afterLabel); 
    return false;
  }
  
  private void emitBranch(Expression test, Label label, boolean jumpWhenTrue) {
    (new BranchOptimizer(this, this.method)).execute(test, label, jumpWhenTrue);
  }
  
  private void enterStatement(Statement statement) {
    lineNumber(statement);
  }
  
  private void lineNumber(Statement statement) {
    lineNumber(statement.getLineNumber());
  }
  
  private void lineNumber(int lineNumber) {
    if (lineNumber != this.lastLineNumber && lineNumber != -1) {
      this.method.lineNumber(lineNumber);
      this.lastLineNumber = lineNumber;
    } 
  }
  
  int getLastLineNumber() {
    return this.lastLineNumber;
  }
  
  private void loadArray(LiteralNode.ArrayLiteralNode arrayLiteralNode, ArrayType arrayType) {
    assert arrayType == Type.INT_ARRAY || arrayType == Type.NUMBER_ARRAY || arrayType == Type.OBJECT_ARRAY;
    Expression[] nodes = (Expression[])arrayLiteralNode.getValue();
    Object presets = arrayLiteralNode.getPresets();
    int[] postsets = arrayLiteralNode.getPostsets();
    List<Splittable.SplitRange> ranges = arrayLiteralNode.getSplitRanges();
    loadConstant(presets);
    Type elementType = arrayType.getElementType();
    if (ranges != null) {
      loadSplitLiteral((method, type, slot, start, end) -> {
            for (int i = start; i < end; i++) {
              method.load(type, slot);
              storeElement(nodes, elementType, postsets[i]);
            } 
            method.load(type, slot);
          }ranges, (Type)arrayType);
      return;
    } 
    if (postsets.length > 0) {
      int arraySlot = this.method.getUsedSlotsWithLiveTemporaries();
      this.method.storeTemp((Type)arrayType, arraySlot);
      for (int postset : postsets) {
        this.method.load((Type)arrayType, arraySlot);
        storeElement(nodes, elementType, postset);
      } 
      this.method.load((Type)arrayType, arraySlot);
    } 
  }
  
  private void storeElement(Expression[] nodes, Type elementType, int index) {
    this.method.load(index);
    Expression element = nodes[index];
    if (element == null) {
      this.method.loadEmpty(elementType);
    } else {
      loadExpressionAsType(element, elementType);
    } 
    this.method.arraystore();
  }
  
  private void loadArgsArray(List<Expression> args) {
    Object[] array = new Object[args.size()];
    loadConstant(array);
    for (int i = 0; i < args.size(); i++) {
      this.method.dup();
      this.method.load(i);
      loadExpression(args.get(i), TypeBounds.OBJECT);
      this.method.arraystore();
    } 
  }
  
  void loadConstant(String string) {
    String unitClassName = this.unit.getUnitClassName();
    ClassEmitter classEmitter = this.unit.getClassEmitter();
    int index = this.compiler.getConstantData().add(string);
    this.method.load(index);
    this.method.invokestatic(unitClassName, CompilerConstants.GET_STRING.symbolName(), CompilerConstants.methodDescriptor(String.class, new Class[] { int.class }));
    classEmitter.needGetConstantMethod(String.class);
  }
  
  void loadConstant(Object object) {
    loadConstant(object, this.unit, this.method);
  }
  
  private void loadConstant(Object object, CompileUnit compileUnit, MethodEmitter methodEmitter) {
    String unitClassName = compileUnit.getUnitClassName();
    ClassEmitter classEmitter = compileUnit.getClassEmitter();
    int index = this.compiler.getConstantData().add(object);
    Class<?> cls = object.getClass();
    if (cls == PropertyMap.class) {
      methodEmitter.load(index);
      methodEmitter.invokestatic(unitClassName, CompilerConstants.GET_MAP.symbolName(), CompilerConstants.methodDescriptor(PropertyMap.class, new Class[] { int.class }));
      classEmitter.needGetConstantMethod(PropertyMap.class);
    } else if (cls.isArray()) {
      methodEmitter.load(index);
      String methodName = ClassEmitter.getArrayMethodName(cls);
      methodEmitter.invokestatic(unitClassName, methodName, CompilerConstants.methodDescriptor(cls, new Class[] { int.class }));
      classEmitter.needGetConstantMethod(cls);
    } else {
      methodEmitter.loadConstants().load(index).arrayload();
      if (object instanceof ArrayData) {
        methodEmitter.checkcast(ArrayData.class);
        methodEmitter.invoke(CompilerConstants.virtualCallNoLookup(ArrayData.class, "copy", ArrayData.class, new Class[0]));
      } else if (cls != Object.class) {
        methodEmitter.checkcast(cls);
      } 
    } 
  }
  
  private void loadConstantsAndIndex(Object object, MethodEmitter methodEmitter) {
    methodEmitter.loadConstants().load(this.compiler.getConstantData().add(object));
  }
  
  private void loadLiteral(LiteralNode<?> node, TypeBounds resultBounds) {
    Object value = node.getValue();
    if (value == null) {
      this.method.loadNull();
    } else if (value instanceof org.openjdk.nashorn.internal.runtime.Undefined) {
      this.method.loadUndefined(resultBounds.within(Type.OBJECT));
    } else if (value instanceof String) {
      String string = (String)value;
      if (string.length() > 10922) {
        loadConstant(string);
      } else {
        this.method.load(string);
      } 
    } else if (value instanceof Lexer.RegexToken) {
      loadRegex((Lexer.RegexToken)value);
    } else if (value instanceof Boolean) {
      this.method.load(((Boolean)value).booleanValue());
    } else if (value instanceof Integer) {
      if (!resultBounds.canBeNarrowerThan(Type.OBJECT)) {
        this.method.load(((Integer)value).intValue());
        this.method.convert(Type.OBJECT);
      } else if (!resultBounds.canBeNarrowerThan((Type)Type.NUMBER)) {
        this.method.load(((Integer)value).doubleValue());
      } else {
        this.method.load(((Integer)value).intValue());
      } 
    } else if (value instanceof Double) {
      if (!resultBounds.canBeNarrowerThan(Type.OBJECT)) {
        this.method.load(((Double)value).doubleValue());
        this.method.convert(Type.OBJECT);
      } else {
        this.method.load(((Double)value).doubleValue());
      } 
    } else if (node instanceof LiteralNode.ArrayLiteralNode) {
      LiteralNode.ArrayLiteralNode arrayLiteral = (LiteralNode.ArrayLiteralNode)node;
      ArrayType atype = arrayLiteral.getArrayType();
      loadArray(arrayLiteral, atype);
      globalAllocateArray(atype);
    } else {
      throw new UnsupportedOperationException("Unknown literal for " + node.getClass() + " " + value.getClass() + " " + value);
    } 
  }
  
  private void loadRegexToken(Lexer.RegexToken value) {
    this.method.load(value.getExpression());
    this.method.load(value.getOptions());
    globalNewRegExp();
  }
  
  private void loadRegex(Lexer.RegexToken regexToken) {
    if (this.regexFieldCount > 2048) {
      loadRegexToken(regexToken);
    } else {
      String regexName = ((CodeGeneratorLexicalContext)this.lc).getCurrentFunction().uniqueName(CompilerConstants.REGEX_PREFIX.symbolName());
      ClassEmitter classEmitter = this.unit.getClassEmitter();
      classEmitter.field(EnumSet.of(ClassEmitter.Flag.PRIVATE, ClassEmitter.Flag.STATIC), regexName, Object.class);
      this.regexFieldCount++;
      this.method.getStatic(this.unit.getUnitClassName(), regexName, CompilerConstants.typeDescriptor(Object.class));
      this.method.dup();
      Label cachedLabel = new Label("cached");
      this.method.ifnonnull(cachedLabel);
      this.method.pop();
      loadRegexToken(regexToken);
      this.method.dup();
      this.method.putStatic(this.unit.getUnitClassName(), regexName, CompilerConstants.typeDescriptor(Object.class));
      this.method.label(cachedLabel);
      globalRegExpCopy();
    } 
  }
  
  private static boolean propertyValueContains(final Expression value, final int pp) {
    return (new Supplier<Boolean>() {
        boolean contains;
        
        public Boolean get() {
          value.accept((NodeVisitor)new SimpleNodeVisitor() {
                public boolean enterFunctionNode(FunctionNode functionNode) {
                  return false;
                }
                
                public boolean enterDefault(Node node) {
                  if (CodeGenerator.null.this.contains)
                    return false; 
                  if (node instanceof Optimistic && ((Optimistic)node).getProgramPoint() == pp) {
                    CodeGenerator.null.this.contains = true;
                    return false;
                  } 
                  return true;
                }
              });
          return Boolean.valueOf(this.contains);
        }
      }).get().booleanValue();
  }
  
  private void loadObjectNode(ObjectNode objectNode) {
    int i;
    ObjectCreator<?> oc;
    List<PropertyNode> elements = objectNode.getElements();
    List<MapTuple<Expression>> tuples = new ArrayList<>();
    List<PropertyNode> specialProperties = new ArrayList<>();
    int ccp = getCurrentContinuationEntryPoint();
    List<Splittable.SplitRange> ranges = objectNode.getSplitRanges();
    Expression protoNode = null;
    boolean restOfProperty = false;
    for (PropertyNode propertyNode : elements) {
      Expression value = propertyNode.getValue();
      String key = propertyNode.getKeyName();
      boolean isComputedOrAccessor = (propertyNode.isComputed() || value == null);
      Symbol symbol = isComputedOrAccessor ? null : new Symbol(key, 0);
      if (isComputedOrAccessor) {
        specialProperties.add(propertyNode);
      } else if (propertyNode.getKey() instanceof IdentNode && "__proto__"
        .equals(key)) {
        protoNode = value;
        continue;
      } 
      i = restOfProperty | ((value != null && UnwarrantedOptimismException.isValid(ccp) && propertyValueContains(value, ccp)) ? 1 : 0);
      Class<?> valueType = (!useDualFields() || isComputedOrAccessor || value.getType().isBoolean()) ? Object.class : value.getType().getTypeClass();
      tuples.add(new MapTuple<Expression>(key, symbol, Type.typeFor(valueType), value) {
            public Class<?> getValueType() {
              return this.type.getTypeClass();
            }
          });
    } 
    if (elements.size() > OBJECT_SPILL_THRESHOLD) {
      oc = new SpillObjectCreator(this, tuples);
    } else {
      oc = new FieldObjectCreator<Expression>(this, tuples) {
          protected void loadValue(Expression node, Type type) {
            CodeGenerator.this.loadExpressionAsType(node, Type.generic(type));
          }
        };
    } 
    if (ranges != null) {
      oc.createObject(this.method);
      loadSplitLiteral(oc, ranges, Type.typeFor(oc.getAllocatorClass()));
    } else {
      oc.makeObject(this.method);
    } 
    if (i != 0) {
      ContinuationInfo ci = getContinuationInfo();
      ci.setObjectLiteralMap(this.method.getStackSize(), oc.getMap());
    } 
    this.method.dup();
    if (protoNode != null) {
      loadExpressionAsObject(protoNode);
      this.method.convert(Type.OBJECT);
      this.method.invoke(ScriptObject.SET_PROTO_FROM_LITERAL);
    } else {
      this.method.invoke(ScriptObject.SET_GLOBAL_OBJECT_PROTO);
    } 
    for (PropertyNode propertyNode : specialProperties) {
      this.method.dup();
      if (propertyNode.isComputed()) {
        assert propertyNode.getKeyName() == null;
        loadExpressionAsObject(propertyNode.getKey());
      } else {
        this.method.loadKey(propertyNode.getKey());
      } 
      if (propertyNode.getValue() != null) {
        loadExpressionAsObject(propertyNode.getValue());
        this.method.load(0);
        this.method.invoke(ScriptObject.GENERIC_SET);
        continue;
      } 
      FunctionNode getter = propertyNode.getGetter();
      FunctionNode setter = propertyNode.getSetter();
      assert getter != null || setter != null;
      if (getter == null) {
        this.method.loadNull();
      } else {
        getter.accept((NodeVisitor)this);
      } 
      if (setter == null) {
        this.method.loadNull();
      } else {
        setter.accept((NodeVisitor)this);
      } 
      this.method.invoke(ScriptObject.SET_USER_ACCESSORS);
    } 
  }
  
  public boolean enterReturnNode(ReturnNode returnNode) {
    if (!this.method.isReachable())
      return false; 
    enterStatement((Statement)returnNode);
    Type returnType = ((CodeGeneratorLexicalContext)this.lc).getCurrentFunction().getReturnType();
    Expression expression = returnNode.getExpression();
    if (expression != null) {
      loadExpressionUnbounded(expression);
    } else {
      this.method.loadUndefined(returnType);
    } 
    this.method._return(returnType);
    return false;
  }
  
  private boolean undefinedCheck(RuntimeNode runtimeNode, List<Expression> args) {
    Symbol undefinedSymbol;
    RuntimeNode.Request request = runtimeNode.getRequest();
    if (!RuntimeNode.Request.isUndefinedCheck(request))
      return false; 
    Expression lhs = args.get(0);
    Expression rhs = args.get(1);
    Symbol lhsSymbol = (lhs instanceof IdentNode) ? ((IdentNode)lhs).getSymbol() : null;
    Symbol rhsSymbol = (rhs instanceof IdentNode) ? ((IdentNode)rhs).getSymbol() : null;
    assert lhsSymbol != null || rhsSymbol != null;
    if (isUndefinedSymbol(lhsSymbol)) {
      undefinedSymbol = lhsSymbol;
    } else {
      assert isUndefinedSymbol(rhsSymbol);
      undefinedSymbol = rhsSymbol;
    } 
    assert undefinedSymbol != null;
    if (!undefinedSymbol.isScope())
      return false; 
    if (lhsSymbol == undefinedSymbol && lhs.getType().isPrimitive())
      return false; 
    if (containsOptimisticExpression(lhs))
      return false; 
    if (!this.compiler.isGlobalSymbol(((CodeGeneratorLexicalContext)this.lc).getCurrentFunction(), "undefined"))
      return false; 
    boolean isUndefinedCheck = (request == RuntimeNode.Request.IS_UNDEFINED);
    Expression expr = (undefinedSymbol == lhsSymbol) ? rhs : lhs;
    if (expr.getType().isPrimitive()) {
      loadAndDiscard(expr);
      this.method.load(!isUndefinedCheck);
    } else {
      Label checkTrue = new Label("ud_check_true");
      Label end = new Label("end");
      loadExpressionAsObject(expr);
      this.method.loadUndefined(Type.OBJECT);
      this.method.if_acmpeq(checkTrue);
      this.method.load(!isUndefinedCheck);
      this.method._goto(end);
      this.method.label(checkTrue);
      this.method.load(isUndefinedCheck);
      this.method.label(end);
    } 
    return true;
  }
  
  private static boolean isUndefinedSymbol(Symbol symbol) {
    return (symbol != null && "undefined".equals(symbol.getName()));
  }
  
  private static boolean isNullLiteral(Node node) {
    return (node instanceof LiteralNode && ((LiteralNode)node).isNull());
  }
  
  private boolean nullCheck(RuntimeNode runtimeNode, List<Expression> args) {
    Label popLabel;
    RuntimeNode.Request request = runtimeNode.getRequest();
    if (!RuntimeNode.Request.isEQ(request) && !RuntimeNode.Request.isNE(request))
      return false; 
    assert args.size() == 2 : "EQ or NE or TYPEOF need two args";
    Expression lhs = args.get(0);
    Expression rhs = args.get(1);
    if (isNullLiteral((Node)lhs)) {
      Expression tmp = lhs;
      lhs = rhs;
      rhs = tmp;
    } 
    if (!isNullLiteral((Node)rhs))
      return false; 
    if (!lhs.getType().isObject())
      return false; 
    if (containsOptimisticExpression(lhs))
      return false; 
    Label trueLabel = new Label("trueLabel");
    Label falseLabel = new Label("falseLabel");
    Label endLabel = new Label("end");
    loadExpressionUnbounded(lhs);
    if (!RuntimeNode.Request.isStrict(request)) {
      this.method.dup();
      popLabel = new Label("pop");
    } else {
      popLabel = null;
    } 
    if (RuntimeNode.Request.isEQ(request)) {
      this.method.ifnull(!RuntimeNode.Request.isStrict(request) ? popLabel : trueLabel);
      if (!RuntimeNode.Request.isStrict(request)) {
        this.method.loadUndefined(Type.OBJECT);
        this.method.if_acmpeq(trueLabel);
      } 
      this.method.label(falseLabel);
      this.method.load(false);
      this.method._goto(endLabel);
      if (!RuntimeNode.Request.isStrict(request)) {
        this.method.label(popLabel);
        this.method.pop();
      } 
      this.method.label(trueLabel);
      this.method.load(true);
      this.method.label(endLabel);
    } else if (RuntimeNode.Request.isNE(request)) {
      this.method.ifnull(!RuntimeNode.Request.isStrict(request) ? popLabel : falseLabel);
      if (!RuntimeNode.Request.isStrict(request)) {
        this.method.loadUndefined(Type.OBJECT);
        this.method.if_acmpeq(falseLabel);
      } 
      this.method.label(trueLabel);
      this.method.load(true);
      this.method._goto(endLabel);
      if (!RuntimeNode.Request.isStrict(request)) {
        this.method.label(popLabel);
        this.method.pop();
      } 
      this.method.label(falseLabel);
      this.method.load(false);
      this.method.label(endLabel);
    } 
    assert runtimeNode.getType().isBoolean();
    this.method.convert(runtimeNode.getType());
    return true;
  }
  
  private boolean containsOptimisticExpression(final Expression rootExpr) {
    if (!useOptimisticTypes())
      return false; 
    return (new Supplier<Boolean>() {
        boolean contains;
        
        public Boolean get() {
          rootExpr.accept((NodeVisitor)new SimpleNodeVisitor() {
                public boolean enterFunctionNode(FunctionNode functionNode) {
                  return false;
                }
                
                public boolean enterDefault(Node node) {
                  if (!CodeGenerator.null.this.contains && node instanceof Optimistic) {
                    int pp = ((Optimistic)node).getProgramPoint();
                    CodeGenerator.null.this.contains = UnwarrantedOptimismException.isValid(pp);
                  } 
                  return !CodeGenerator.null.this.contains;
                }
              });
          return Boolean.valueOf(this.contains);
        }
      }).get().booleanValue();
  }
  
  private void loadRuntimeNode(RuntimeNode runtimeNode) {
    RuntimeNode newRuntimeNode;
    List<Expression> args = new ArrayList<>(runtimeNode.getArgs());
    if (nullCheck(runtimeNode, args))
      return; 
    if (undefinedCheck(runtimeNode, args))
      return; 
    RuntimeNode.Request request = runtimeNode.getRequest();
    if (RuntimeNode.Request.isUndefinedCheck(request)) {
      newRuntimeNode = runtimeNode.setRequest((request == RuntimeNode.Request.IS_UNDEFINED) ? RuntimeNode.Request.EQ_STRICT : RuntimeNode.Request.NE_STRICT);
    } else {
      newRuntimeNode = runtimeNode;
    } 
    for (Expression arg : args)
      loadExpression(arg, TypeBounds.OBJECT); 
    this.method.invokestatic(
        CompilerConstants.className(ScriptRuntime.class), newRuntimeNode
        .getRequest().toString(), (new FunctionSignature(false, false, newRuntimeNode
          
          .getType(), args
          .size())).toString());
    this.method.convert(newRuntimeNode.getType());
  }
  
  private void defineCommonSplitMethodParameters() {
    defineSplitMethodParameter(0, CompilerConstants.CALLEE);
    defineSplitMethodParameter(1, CompilerConstants.THIS);
    defineSplitMethodParameter(2, CompilerConstants.SCOPE);
  }
  
  private void defineSplitMethodParameter(int slot, CompilerConstants cc) {
    defineSplitMethodParameter(slot, Type.typeFor(cc.type()));
  }
  
  private void defineSplitMethodParameter(int slot, Type type) {
    this.method.defineBlockLocalVariable(slot, slot + type.getSlots());
    this.method.onLocalStore(type, slot);
  }
  
  private void loadSplitLiteral(SplitLiteralCreator creator, List<Splittable.SplitRange> ranges, Type literalType) {
    assert ranges != null;
    MethodEmitter savedMethod = this.method;
    FunctionNode currentFunction = ((CodeGeneratorLexicalContext)this.lc).getCurrentFunction();
    for (Splittable.SplitRange splitRange : ranges) {
      this.unit = ((CodeGeneratorLexicalContext)this.lc).pushCompileUnit(splitRange.getCompileUnit());
      assert this.unit != null;
      String className = this.unit.getUnitClassName();
      String name = currentFunction.uniqueName(CompilerConstants.SPLIT_PREFIX.symbolName());
      Class<?> clazz = literalType.getTypeClass();
      String signature = CompilerConstants.methodDescriptor(clazz, new Class[] { ScriptFunction.class, Object.class, ScriptObject.class, clazz });
      pushMethodEmitter(this.unit.getClassEmitter().method(EnumSet.of(ClassEmitter.Flag.PUBLIC, ClassEmitter.Flag.STATIC), name, signature));
      this.method.setFunctionNode(currentFunction);
      this.method.begin();
      defineCommonSplitMethodParameters();
      defineSplitMethodParameter(CompilerConstants.SPLIT_ARRAY_ARG.slot(), literalType);
      int literalSlot = fixScopeSlot(currentFunction, 3);
      ((CodeGeneratorLexicalContext)this.lc).enterSplitLiteral();
      creator.populateRange(this.method, literalType, literalSlot, splitRange.getLow(), splitRange.getHigh());
      this.method._return();
      ((CodeGeneratorLexicalContext)this.lc).exitSplitLiteral();
      this.method.end();
      ((CodeGeneratorLexicalContext)this.lc).releaseSlots();
      popMethodEmitter();
      assert this.method == savedMethod;
      this.method.loadCompilerConstant(CompilerConstants.CALLEE).swap();
      this.method.loadCompilerConstant(CompilerConstants.THIS).swap();
      this.method.loadCompilerConstant(CompilerConstants.SCOPE).swap();
      this.method.invokestatic(className, name, signature);
      this.unit = ((CodeGeneratorLexicalContext)this.lc).popCompileUnit(this.unit);
    } 
  }
  
  private int fixScopeSlot(FunctionNode functionNode, int extraSlot) {
    int actualScopeSlot = functionNode.compilerConstant(CompilerConstants.SCOPE).getSlot(SCOPE_TYPE);
    int defaultScopeSlot = CompilerConstants.SCOPE.slot();
    int newExtraSlot = extraSlot;
    if (actualScopeSlot != defaultScopeSlot) {
      if (actualScopeSlot == extraSlot) {
        newExtraSlot = extraSlot + 1;
        this.method.defineBlockLocalVariable(newExtraSlot, newExtraSlot + 1);
        this.method.load(Type.OBJECT, extraSlot);
        this.method.storeHidden(Type.OBJECT, newExtraSlot);
      } else {
        this.method.defineBlockLocalVariable(actualScopeSlot, actualScopeSlot + 1);
      } 
      this.method.load(SCOPE_TYPE, defaultScopeSlot);
      this.method.storeCompilerConstant(CompilerConstants.SCOPE);
    } 
    return newExtraSlot;
  }
  
  public boolean enterSplitReturn(SplitReturn splitReturn) {
    if (this.method.isReachable())
      this.method.loadUndefined(((CodeGeneratorLexicalContext)this.lc).getCurrentFunction().getReturnType())._return(); 
    return false;
  }
  
  public boolean enterSetSplitState(SetSplitState setSplitState) {
    if (this.method.isReachable())
      this.method.setSplitState(setSplitState.getState()); 
    return false;
  }
  
  public boolean enterSwitchNode(SwitchNode switchNode) {
    if (!this.method.isReachable())
      return false; 
    enterStatement((Statement)switchNode);
    Expression expression = switchNode.getExpression();
    List<CaseNode> cases = switchNode.getCases();
    if (cases.isEmpty()) {
      loadAndDiscard(expression);
      return false;
    } 
    CaseNode defaultCase = switchNode.getDefaultCase();
    Label breakLabel = switchNode.getBreakLabel();
    int liveLocalsOnBreak = this.method.getUsedSlotsWithLiveTemporaries();
    if (defaultCase != null && cases.size() == 1) {
      assert cases.get(0) == defaultCase;
      loadAndDiscard(expression);
      defaultCase.getBody().accept((NodeVisitor)this);
      this.method.breakLabel(breakLabel, liveLocalsOnBreak);
      return false;
    } 
    Label defaultLabel = (defaultCase != null) ? defaultCase.getEntry() : breakLabel;
    boolean hasSkipConversion = LocalVariableConversion.hasLiveConversion((JoinPredecessor)switchNode);
    if (switchNode.isUniqueInteger()) {
      TreeMap<Integer, Label> tree = new TreeMap<>();
      for (CaseNode caseNode : cases) {
        Expression expression1 = caseNode.getTest();
        if (expression1 != null) {
          Integer value = (Integer)((LiteralNode)expression1).getValue();
          Label entry = caseNode.getEntry();
          if (!tree.containsKey(value))
            tree.put(value, entry); 
        } 
      } 
      int size = tree.size();
      Integer[] values = (Integer[])tree.keySet().toArray((Object[])new Integer[0]);
      Label[] labels = (Label[])tree.values().toArray((Object[])new Label[0]);
      int lo = values[0].intValue();
      int hi = values[size - 1].intValue();
      long range = hi - lo + 1L;
      int deflt = Integer.MIN_VALUE;
      Integer[] arrayOfInteger1;
      int i;
      byte b;
      for (arrayOfInteger1 = values, i = arrayOfInteger1.length, b = 0; b < i; ) {
        int value = arrayOfInteger1[b].intValue();
        if (deflt == value) {
          deflt++;
        } else if (deflt < value) {
          break;
        } 
        b++;
      } 
      loadExpressionUnbounded(expression);
      Type type = expression.getType();
      if (!type.isInteger()) {
        this.method.load(deflt);
        Class<?> exprClass = type.getTypeClass();
        this.method.invoke(CompilerConstants.staticCallNoLookup(ScriptRuntime.class, "switchTagAsInt", int.class, new Class[] { exprClass.isPrimitive() ? exprClass : Object.class, int.class }));
      } 
      if (hasSkipConversion) {
        assert defaultLabel == breakLabel;
        defaultLabel = new Label("switch_skip");
      } 
      if (range + 1L <= (size * 2)) {
        Label[] table = new Label[(int)range];
        Arrays.fill((Object[])table, defaultLabel);
        for (int j = 0; j < size; j++) {
          int value = values[j].intValue();
          table[value - lo] = labels[j];
        } 
        this.method.tableswitch(lo, hi, defaultLabel, table);
      } else {
        int[] ints = new int[size];
        for (int j = 0; j < size; j++)
          ints[j] = values[j].intValue(); 
        this.method.lookupswitch(defaultLabel, ints, labels);
      } 
      if (hasSkipConversion) {
        this.method.label(defaultLabel);
        this.method.beforeJoinPoint((JoinPredecessor)switchNode);
        this.method._goto(breakLabel);
      } 
    } else {
      Symbol tagSymbol = switchNode.getTag();
      int tagSlot = tagSymbol.getSlot(Type.OBJECT);
      loadExpressionAsObject(expression);
      this.method.store(tagSymbol, Type.OBJECT);
      for (CaseNode caseNode : cases) {
        Expression test = caseNode.getTest();
        if (test != null) {
          this.method.load(Type.OBJECT, tagSlot);
          loadExpressionAsObject(test);
          this.method.invoke(ScriptRuntime.EQ_STRICT);
          this.method.ifne(caseNode.getEntry());
        } 
      } 
      if (defaultCase != null) {
        this.method._goto(defaultLabel);
      } else {
        this.method.beforeJoinPoint((JoinPredecessor)switchNode);
        this.method._goto(breakLabel);
      } 
    } 
    assert !this.method.isReachable();
    for (CaseNode caseNode : cases) {
      Label fallThroughLabel;
      if (caseNode.getLocalVariableConversion() != null && this.method.isReachable()) {
        fallThroughLabel = new Label("fallthrough");
        this.method._goto(fallThroughLabel);
      } else {
        fallThroughLabel = null;
      } 
      this.method.label(caseNode.getEntry());
      this.method.beforeJoinPoint((JoinPredecessor)caseNode);
      if (fallThroughLabel != null)
        this.method.label(fallThroughLabel); 
      caseNode.getBody().accept((NodeVisitor)this);
    } 
    this.method.breakLabel(breakLabel, liveLocalsOnBreak);
    return false;
  }
  
  public boolean enterThrowNode(ThrowNode throwNode) {
    if (!this.method.isReachable())
      return false; 
    enterStatement((Statement)throwNode);
    if (throwNode.isSyntheticRethrow()) {
      this.method.beforeJoinPoint((JoinPredecessor)throwNode);
      IdentNode exceptionExpr = (IdentNode)throwNode.getExpression();
      Symbol exceptionSymbol = exceptionExpr.getSymbol();
      this.method.load(exceptionSymbol, EXCEPTION_TYPE);
      this.method.checkcast(EXCEPTION_TYPE.getTypeClass());
      this.method.athrow();
      return false;
    } 
    Source source = getCurrentSource();
    Expression expression = throwNode.getExpression();
    int position = throwNode.position();
    int line = throwNode.getLineNumber();
    int column = source.getColumn(position);
    loadExpressionAsObject(expression);
    this.method.load(source.getName());
    this.method.load(line);
    this.method.load(column);
    this.method.invoke(ECMAException.CREATE);
    this.method.beforeJoinPoint((JoinPredecessor)throwNode);
    this.method.athrow();
    return false;
  }
  
  private Source getCurrentSource() {
    return ((CodeGeneratorLexicalContext)this.lc).getCurrentFunction().getSource();
  }
  
  public boolean enterTryNode(TryNode tryNode) {
    if (!this.method.isReachable())
      return false; 
    enterStatement((Statement)tryNode);
    Block body = tryNode.getBody();
    List<Block> catchBlocks = tryNode.getCatchBlocks();
    final Symbol vmException = tryNode.getException();
    Label entry = new Label("try");
    Label recovery = new Label("catch");
    Label exit = new Label("end_try");
    Label skip = new Label("skip");
    this.method.canThrow(recovery);
    this.method.beforeTry(tryNode, recovery);
    this.method.label(entry);
    this.catchLabels.push(recovery);
    try {
      body.accept((NodeVisitor)this);
    } finally {
      assert this.catchLabels.peek() == recovery;
      this.catchLabels.pop();
    } 
    this.method.label(exit);
    boolean bodyCanThrow = exit.isAfter(entry);
    if (!bodyCanThrow)
      return false; 
    this.method._try(entry, exit, recovery, Throwable.class);
    if (this.method.isReachable())
      this.method._goto(skip); 
    for (Block inlinedFinally : tryNode.getInlinedFinallies()) {
      TryNode.getLabelledInlinedFinallyBlock(inlinedFinally).accept((NodeVisitor)this);
      assert !this.method.isReachable();
    } 
    this.method._catch(recovery);
    this.method.store(vmException, EXCEPTION_TYPE);
    Label afterCatch = new Label("after_catch");
    for (Block catchBlock : catchBlocks) {
      Label nextCatch;
      assert this.method.isReachable();
      ((CodeGeneratorLexicalContext)this.lc).push(catchBlock);
      enterBlock(catchBlock);
      final CatchNode catchNode = catchBlock.getStatements().get(0);
      IdentNode exception = catchNode.getExceptionIdentifier();
      Expression exceptionCondition = catchNode.getExceptionCondition();
      Block catchBody = catchNode.getBody();
      (new Store<IdentNode>(exception) {
          protected void storeNonDiscard() {}
          
          protected void evaluate() {
            if (catchNode.isSyntheticRethrow()) {
              CodeGenerator.this.method.load(vmException, CodeGenerator.EXCEPTION_TYPE);
              return;
            } 
            Label notEcmaException = new Label("no_ecma_exception");
            CodeGenerator.this.method.load(vmException, CodeGenerator.EXCEPTION_TYPE).dup()._instanceof(ECMAException.class).ifeq(notEcmaException);
            CodeGenerator.this.method.checkcast(ECMAException.class);
            CodeGenerator.this.method.getField(ECMAException.THROWN);
            CodeGenerator.this.method.label(notEcmaException);
          }
        }).store();
      boolean isConditionalCatch = (exceptionCondition != null);
      if (isConditionalCatch) {
        loadExpressionAsBoolean(exceptionCondition);
        nextCatch = new Label("next_catch");
        nextCatch.markAsBreakTarget();
        this.method.ifeq(nextCatch);
      } else {
        nextCatch = null;
      } 
      catchBody.accept((NodeVisitor)this);
      leaveBlock(catchBlock);
      ((CodeGeneratorLexicalContext)this.lc).pop(catchBlock);
      if (nextCatch != null) {
        if (this.method.isReachable())
          this.method._goto(afterCatch); 
        this.method.breakLabel(nextCatch, ((CodeGeneratorLexicalContext)this.lc).getUsedSlotCount());
      } 
    } 
    this.method.label(afterCatch);
    if (this.method.isReachable())
      this.method.markDeadLocalVariable(vmException); 
    this.method.label(skip);
    assert tryNode.getFinallyBody() == null;
    return false;
  }
  
  public boolean enterVarNode(VarNode varNode) {
    if (!this.method.isReachable())
      return false; 
    Expression init = varNode.getInit();
    IdentNode identNode = varNode.getName();
    Symbol identSymbol = identNode.getSymbol();
    assert identSymbol != null : "variable node " + varNode + " requires a name with a symbol";
    boolean needsScope = identSymbol.isScope();
    if (init == null) {
      if (needsScope && varNode.isLet()) {
        this.method.loadCompilerConstant(CompilerConstants.SCOPE);
        this.method.loadUndefined(Type.OBJECT);
        int flags = getScopeCallSiteFlags(identSymbol) | 0x200;
        assert isFastScope(identSymbol);
        storeFastScopeVar(identSymbol, flags);
      } 
      return false;
    } 
    enterStatement((Statement)varNode);
    assert this.method != null;
    if (needsScope) {
      this.method.loadCompilerConstant(CompilerConstants.SCOPE);
      loadExpressionUnbounded(init);
      int flags = getScopeCallSiteFlags(identSymbol) | (varNode.isBlockScoped() ? 512 : 0);
      if (isFastScope(identSymbol)) {
        storeFastScopeVar(identSymbol, flags);
      } else {
        this.method.dynamicSet(identNode.getName(), flags, false);
      } 
    } else {
      Type identType = identNode.getType();
      if (identType == Type.UNDEFINED) {
        assert init.getType() == Type.UNDEFINED || identNode.getSymbol().slotCount() == 0;
        loadAndDiscard(init);
        return false;
      } 
      loadExpressionAsType(init, identType);
      storeIdentWithCatchConversion(identNode, identType);
    } 
    return false;
  }
  
  private void storeIdentWithCatchConversion(IdentNode identNode, Type type) {
    LocalVariableConversion conversion = identNode.getLocalVariableConversion();
    Symbol symbol = identNode.getSymbol();
    if (conversion != null && conversion.isLive()) {
      assert symbol == conversion.getSymbol();
      assert symbol.isBytecodeLocal();
      assert conversion.getNext() == null;
      assert conversion.getFrom() == type;
      Label catchLabel = this.catchLabels.peek();
      assert catchLabel != METHOD_BOUNDARY;
      assert catchLabel.isReachable();
      Type joinType = conversion.getTo();
      Label.Stack catchStack = catchLabel.getStack();
      int joinSlot = symbol.getSlot(joinType);
      if (catchStack.getUsedSlotsWithLiveTemporaries() > joinSlot) {
        this.method.dup();
        this.method.convert(joinType);
        this.method.store(symbol, joinType);
        catchLabel.getStack().onLocalStore(joinType, joinSlot, true);
        this.method.canThrow(catchLabel);
        this.method.store(symbol, type, false);
        return;
      } 
    } 
    this.method.store(symbol, type, true);
  }
  
  public boolean enterWhileNode(WhileNode whileNode) {
    if (!this.method.isReachable())
      return false; 
    if (whileNode.isDoWhile()) {
      enterDoWhile(whileNode);
    } else {
      enterStatement((Statement)whileNode);
      enterForOrWhile((LoopNode)whileNode, null);
    } 
    return false;
  }
  
  private void enterForOrWhile(LoopNode loopNode, JoinPredecessorExpression modify) {
    int liveLocalsOnBreak = this.method.getUsedSlotsWithLiveTemporaries();
    JoinPredecessorExpression test = loopNode.getTest();
    if (Expression.isAlwaysFalse((Expression)test)) {
      loadAndDiscard((Expression)test);
      return;
    } 
    this.method.beforeJoinPoint((JoinPredecessor)loopNode);
    Label continueLabel = loopNode.getContinueLabel();
    Label repeatLabel = (modify != null) ? new Label("for_repeat") : continueLabel;
    this.method.label(repeatLabel);
    int liveLocalsOnContinue = this.method.getUsedSlotsWithLiveTemporaries();
    Block body = loopNode.getBody();
    Label breakLabel = loopNode.getBreakLabel();
    boolean testHasLiveConversion = (test != null && LocalVariableConversion.hasLiveConversion((JoinPredecessor)test));
    if (Expression.isAlwaysTrue((Expression)test)) {
      if (test != null) {
        loadAndDiscard((Expression)test);
        if (testHasLiveConversion)
          this.method.beforeJoinPoint((JoinPredecessor)test); 
      } 
    } else if (testHasLiveConversion) {
      emitBranch(test.getExpression(), body.getEntryLabel(), true);
      this.method.beforeJoinPoint((JoinPredecessor)test);
      this.method._goto(breakLabel);
    } else {
      emitBranch(test.getExpression(), breakLabel, false);
    } 
    body.accept((NodeVisitor)this);
    if (repeatLabel != continueLabel)
      emitContinueLabel(continueLabel, liveLocalsOnContinue); 
    if (loopNode.hasPerIterationScope() && ((CodeGeneratorLexicalContext)this.lc).getCurrentBlock().needsScope()) {
      this.method.loadCompilerConstant(CompilerConstants.SCOPE);
      this.method.invoke(CompilerConstants.virtualCallNoLookup(ScriptObject.class, "copy", ScriptObject.class, new Class[0]));
      this.method.storeCompilerConstant(CompilerConstants.SCOPE);
    } 
    if (this.method.isReachable()) {
      if (modify != null) {
        lineNumber((Statement)loopNode);
        loadAndDiscard((Expression)modify);
        this.method.beforeJoinPoint((JoinPredecessor)modify);
      } 
      this.method._goto(repeatLabel);
    } 
    this.method.breakLabel(breakLabel, liveLocalsOnBreak);
  }
  
  private void emitContinueLabel(Label continueLabel, int liveLocals) {
    boolean reachable = this.method.isReachable();
    this.method.breakLabel(continueLabel, liveLocals);
    if (!reachable)
      this.method.undefineLocalVariables(((CodeGeneratorLexicalContext)this.lc).getUsedSlotCount(), false); 
  }
  
  private void enterDoWhile(WhileNode whileNode) {
    int liveLocalsOnContinueOrBreak = this.method.getUsedSlotsWithLiveTemporaries();
    this.method.beforeJoinPoint((JoinPredecessor)whileNode);
    Block body = whileNode.getBody();
    body.accept((NodeVisitor)this);
    emitContinueLabel(whileNode.getContinueLabel(), liveLocalsOnContinueOrBreak);
    if (this.method.isReachable()) {
      lineNumber((Statement)whileNode);
      JoinPredecessorExpression test = whileNode.getTest();
      Label bodyEntryLabel = body.getEntryLabel();
      boolean testHasLiveConversion = LocalVariableConversion.hasLiveConversion((JoinPredecessor)test);
      if (Expression.isAlwaysFalse((Expression)test)) {
        loadAndDiscard((Expression)test);
        if (testHasLiveConversion)
          this.method.beforeJoinPoint((JoinPredecessor)test); 
      } else if (testHasLiveConversion) {
        Label beforeExit = new Label("do_while_preexit");
        emitBranch(test.getExpression(), beforeExit, false);
        this.method.beforeJoinPoint((JoinPredecessor)test);
        this.method._goto(bodyEntryLabel);
        this.method.label(beforeExit);
        this.method.beforeJoinPoint((JoinPredecessor)test);
      } else {
        emitBranch(test.getExpression(), bodyEntryLabel, true);
      } 
    } 
    this.method.breakLabel(whileNode.getBreakLabel(), liveLocalsOnContinueOrBreak);
  }
  
  public boolean enterWithNode(WithNode withNode) {
    Label tryLabel;
    if (!this.method.isReachable())
      return false; 
    enterStatement((Statement)withNode);
    Expression expression = withNode.getExpression();
    Block body = withNode.getBody();
    boolean hasScope = this.method.hasScope();
    if (hasScope)
      this.method.loadCompilerConstant(CompilerConstants.SCOPE); 
    loadExpressionAsObject(expression);
    if (hasScope) {
      this.method.invoke(ScriptRuntime.OPEN_WITH);
      this.method.storeCompilerConstant(CompilerConstants.SCOPE);
      tryLabel = new Label("with_try");
      this.method.label(tryLabel);
    } else {
      globalCheckObjectCoercible();
      tryLabel = null;
    } 
    body.accept((NodeVisitor)this);
    if (hasScope) {
      Label endLabel = new Label("with_end");
      Label catchLabel = new Label("with_catch");
      Label exitLabel = new Label("with_exit");
      this.method.label(endLabel);
      boolean bodyCanThrow = endLabel.isAfter(tryLabel);
      if (bodyCanThrow)
        this.method._try(tryLabel, endLabel, catchLabel); 
      boolean reachable = this.method.isReachable();
      if (reachable) {
        popScope();
        if (bodyCanThrow)
          this.method._goto(exitLabel); 
      } 
      if (bodyCanThrow) {
        this.method._catch(catchLabel);
        popScopeException();
        this.method.athrow();
        if (reachable)
          this.method.label(exitLabel); 
      } 
    } 
    return false;
  }
  
  private void loadADD(UnaryNode unaryNode, TypeBounds resultBounds) {
    loadExpression(unaryNode.getExpression(), resultBounds.booleanToInt().notWiderThan((Type)Type.NUMBER));
    if (this.method.peekType() == Type.BOOLEAN)
      this.method.convert((Type)Type.INT); 
  }
  
  private void loadBIT_NOT(UnaryNode unaryNode) {
    loadExpression(unaryNode.getExpression(), TypeBounds.INT).load(-1).xor();
  }
  
  private void loadDECINC(final UnaryNode unaryNode) {
    final Expression operand = unaryNode.getExpression();
    final Type type = unaryNode.getType();
    final TypeBounds typeBounds = new TypeBounds(type, (Type)Type.NUMBER);
    TokenType tokenType = unaryNode.tokenType();
    final boolean isPostfix = (tokenType == TokenType.DECPOSTFIX || tokenType == TokenType.INCPOSTFIX);
    final boolean isIncrement = (tokenType == TokenType.INCPREFIX || tokenType == TokenType.INCPOSTFIX);
    assert !type.isObject();
    (new SelfModifyingStore<UnaryNode>(unaryNode, operand) {
        private void loadRhs() {
          CodeGenerator.this.loadExpression(operand, typeBounds, true);
        }
        
        protected void evaluate() {
          if (isPostfix) {
            loadRhs();
          } else {
            (new CodeGenerator.OptimisticOperation((Optimistic)unaryNode, typeBounds) {
                void loadStack() {
                  CodeGenerator.null.this.loadRhs();
                  CodeGenerator.null.this.loadMinusOne();
                }
                
                void consumeStack() {
                  CodeGenerator.null.this.doDecInc(getProgramPoint());
                }
              }).emit(CodeGenerator.getOptimisticIgnoreCountForSelfModifyingExpression(operand));
          } 
        }
        
        protected void storeNonDiscard() {
          super.storeNonDiscard();
          if (isPostfix)
            (new CodeGenerator.OptimisticOperation((Optimistic)unaryNode, typeBounds) {
                void loadStack() {
                  CodeGenerator.null.this.loadMinusOne();
                }
                
                void consumeStack() {
                  CodeGenerator.null.this.doDecInc(getProgramPoint());
                }
              }).emit(1); 
        }
        
        private void loadMinusOne() {
          if (type.isInteger()) {
            CodeGenerator.this.method.load(isIncrement ? 1 : -1);
          } else {
            CodeGenerator.this.method.load(isIncrement ? 1.0D : -1.0D);
          } 
        }
        
        private void doDecInc(int programPoint) {
          CodeGenerator.this.method.add(programPoint);
        }
      }).store();
  }
  
  private static int getOptimisticIgnoreCountForSelfModifyingExpression(Expression target) {
    return (target instanceof AccessNode) ? 1 : ((target instanceof IndexNode) ? 2 : 0);
  }
  
  private void loadAndDiscard(Expression expr) {
    if ((expr instanceof LiteralNode.PrimitiveLiteralNode | isLocalVariable(expr)) != 0) {
      assert !((CodeGeneratorLexicalContext)this.lc).isCurrentDiscard(expr);
      return;
    } 
    ((CodeGeneratorLexicalContext)this.lc).pushDiscard(expr);
    loadExpression(expr, TypeBounds.UNBOUNDED);
    if (((CodeGeneratorLexicalContext)this.lc).popDiscardIfCurrent(expr)) {
      assert !expr.isAssignment();
      this.method.pop();
    } 
  }
  
  private void loadMaybeDiscard(Expression parent, Expression expr, TypeBounds resultBounds) {
    loadMaybeDiscard(((CodeGeneratorLexicalContext)this.lc).popDiscardIfCurrent(parent), expr, resultBounds);
  }
  
  private void loadMaybeDiscard(boolean discard, Expression expr, TypeBounds resultBounds) {
    if (discard) {
      loadAndDiscard(expr);
    } else {
      loadExpression(expr, resultBounds);
    } 
  }
  
  private void loadNEW(UnaryNode unaryNode) {
    CallNode callNode = (CallNode)unaryNode.getExpression();
    List<Expression> args = callNode.getArgs();
    Expression func = callNode.getFunction();
    loadExpressionAsObject(func);
    this.method.dynamicNew(1 + loadArgs(args), getCallSiteFlags(), func.toString(false));
  }
  
  private void loadNOT(UnaryNode unaryNode) {
    Expression expr = unaryNode.getExpression();
    if (expr instanceof UnaryNode && expr.isTokenType(TokenType.NOT)) {
      loadExpressionAsBoolean(((UnaryNode)expr).getExpression());
    } else {
      Label trueLabel = new Label("true");
      Label afterLabel = new Label("after");
      emitBranch(expr, trueLabel, true);
      this.method.load(true);
      this.method._goto(afterLabel);
      this.method.label(trueLabel);
      this.method.load(false);
      this.method.label(afterLabel);
    } 
  }
  
  private void loadSUB(final UnaryNode unaryNode, TypeBounds resultBounds) {
    final Type type = unaryNode.getType();
    assert type.isNumeric();
    final TypeBounds numericBounds = resultBounds.booleanToInt();
    (new OptimisticOperation((Optimistic)unaryNode, numericBounds) {
        void loadStack() {
          Expression expr = unaryNode.getExpression();
          CodeGenerator.this.loadExpression(expr, numericBounds.notWiderThan((Type)Type.NUMBER));
        }
        
        void consumeStack() {
          if (type.isNumber())
            CodeGenerator.this.method.convert(type); 
          CodeGenerator.this.method.neg(getProgramPoint());
        }
      }).emit();
  }
  
  public void loadVOID(UnaryNode unaryNode, TypeBounds resultBounds) {
    loadAndDiscard(unaryNode.getExpression());
    if (!((CodeGeneratorLexicalContext)this.lc).popDiscardIfCurrent((Expression)unaryNode))
      this.method.loadUndefined(resultBounds.widest); 
  }
  
  public void loadDELETE(UnaryNode unaryNode) {
    Expression expression = unaryNode.getExpression();
    if (expression instanceof IdentNode) {
      IdentNode ident = (IdentNode)expression;
      Symbol symbol = ident.getSymbol();
      String name = ident.getName();
      if (symbol.isThis()) {
        if (!((CodeGeneratorLexicalContext)this.lc).popDiscardIfCurrent((Expression)unaryNode))
          this.method.load(true); 
      } else if (((CodeGeneratorLexicalContext)this.lc).getCurrentFunction().isStrict()) {
        this.method.load(name);
        this.method.invoke(ScriptRuntime.STRICT_FAIL_DELETE);
      } else if (!symbol.isScope() && (symbol.isParam() || (symbol.isVar() && !symbol.isProgramLevel()))) {
        if (!((CodeGeneratorLexicalContext)this.lc).popDiscardIfCurrent((Expression)unaryNode))
          this.method.load(false); 
      } else {
        this.method.loadCompilerConstant(CompilerConstants.SCOPE);
        this.method.load(name);
        if ((symbol.isGlobal() && !symbol.isFunctionDeclaration()) || symbol.isProgramLevel()) {
          this.method.invoke(ScriptRuntime.SLOW_DELETE);
        } else {
          this.method.load(false);
          this.method.invoke(ScriptObject.DELETE);
        } 
      } 
    } else if (expression instanceof BaseNode) {
      loadExpressionAsObject(((BaseNode)expression).getBase());
      if (expression instanceof AccessNode) {
        AccessNode accessNode = (AccessNode)expression;
        this.method.dynamicRemove(accessNode.getProperty(), getCallSiteFlags(), accessNode.isIndex());
      } else if (expression instanceof IndexNode) {
        loadExpressionAsObject(((IndexNode)expression).getIndex());
        this.method.dynamicRemoveIndex(getCallSiteFlags());
      } else {
        throw new AssertionError(expression.getClass().getName());
      } 
    } else {
      throw new AssertionError(expression.getClass().getName());
    } 
  }
  
  public void loadADD(final BinaryNode binaryNode, final TypeBounds resultBounds) {
    (new OptimisticOperation((Optimistic)binaryNode, resultBounds) {
        void loadStack() {
          CodeGenerator.TypeBounds operandBounds;
          boolean isOptimistic = UnwarrantedOptimismException.isValid(getProgramPoint());
          boolean forceConversionSeparation = false;
          if (isOptimistic) {
            operandBounds = new CodeGenerator.TypeBounds(binaryNode.getType(), Type.OBJECT);
          } else {
            Type widestOperationType = binaryNode.getWidestOperationType();
            operandBounds = new CodeGenerator.TypeBounds(Type.narrowest(binaryNode.getWidestOperandType(), resultBounds.widest), widestOperationType);
            forceConversionSeparation = widestOperationType.narrowerThan(resultBounds.widest);
          } 
          CodeGenerator.this.loadBinaryOperands(binaryNode.lhs(), binaryNode.rhs(), operandBounds, false, forceConversionSeparation);
        }
        
        void consumeStack() {
          CodeGenerator.this.method.add(getProgramPoint());
        }
      }).emit();
  }
  
  private void loadAND_OR(BinaryNode binaryNode, TypeBounds resultBounds, boolean isAnd) {
    Type narrowestOperandType = Type.widestReturnType(binaryNode.lhs().getType(), binaryNode.rhs().getType());
    boolean isCurrentDiscard = ((CodeGeneratorLexicalContext)this.lc).popDiscardIfCurrent((Expression)binaryNode);
    Label skip = new Label("skip");
    if (narrowestOperandType == Type.BOOLEAN) {
      Label onTrue = new Label("andor_true");
      emitBranch((Expression)binaryNode, onTrue, true);
      if (isCurrentDiscard) {
        this.method.label(onTrue);
      } else {
        this.method.load(false);
        this.method._goto(skip);
        this.method.label(onTrue);
        this.method.load(true);
        this.method.label(skip);
      } 
      return;
    } 
    TypeBounds outBounds = resultBounds.notNarrowerThan(narrowestOperandType);
    JoinPredecessorExpression lhs = (JoinPredecessorExpression)binaryNode.lhs();
    boolean lhsConvert = LocalVariableConversion.hasLiveConversion((JoinPredecessor)lhs);
    Label evalRhs = lhsConvert ? new Label("eval_rhs") : null;
    loadExpression((Expression)lhs, outBounds);
    if (!isCurrentDiscard)
      this.method.dup(); 
    this.method.convert(Type.BOOLEAN);
    if (isAnd) {
      if (lhsConvert) {
        this.method.ifne(evalRhs);
      } else {
        this.method.ifeq(skip);
      } 
    } else if (lhsConvert) {
      this.method.ifeq(evalRhs);
    } else {
      this.method.ifne(skip);
    } 
    if (lhsConvert) {
      this.method.beforeJoinPoint((JoinPredecessor)lhs);
      this.method._goto(skip);
      this.method.label(evalRhs);
    } 
    if (!isCurrentDiscard)
      this.method.pop(); 
    JoinPredecessorExpression rhs = (JoinPredecessorExpression)binaryNode.rhs();
    loadMaybeDiscard(isCurrentDiscard, (Expression)rhs, outBounds);
    this.method.beforeJoinPoint((JoinPredecessor)rhs);
    this.method.label(skip);
  }
  
  private static boolean isLocalVariable(Expression lhs) {
    return (lhs instanceof IdentNode && isLocalVariable((IdentNode)lhs));
  }
  
  private static boolean isLocalVariable(IdentNode lhs) {
    return lhs.getSymbol().isBytecodeLocal();
  }
  
  private void loadASSIGN(BinaryNode binaryNode) {
    Expression lhs = binaryNode.lhs();
    final Expression rhs = binaryNode.rhs();
    final Type rhsType = rhs.getType();
    if (lhs instanceof IdentNode) {
      Symbol symbol = ((IdentNode)lhs).getSymbol();
      if (!symbol.isScope() && !symbol.hasSlotFor(rhsType) && ((CodeGeneratorLexicalContext)this.lc).popDiscardIfCurrent((Expression)binaryNode)) {
        loadAndDiscard(rhs);
        this.method.markDeadLocalVariable(symbol);
        return;
      } 
    } 
    (new Store<BinaryNode>(binaryNode, lhs) {
        protected void evaluate() {
          CodeGenerator.this.loadExpressionAsType(rhs, rhsType);
        }
      }).store();
  }
  
  private abstract class BinaryOptimisticSelfAssignment extends SelfModifyingStore<BinaryNode> {
    BinaryOptimisticSelfAssignment(BinaryNode node) {
      super(node, node.lhs());
    }
    
    protected abstract void op(CodeGenerator.OptimisticOperation param1OptimisticOperation);
    
    protected void evaluate() {
      final Expression lhs = this.assignNode.lhs();
      final Expression rhs = this.assignNode.rhs();
      final Type widestOperationType = this.assignNode.getWidestOperationType();
      final CodeGenerator.TypeBounds bounds = new CodeGenerator.TypeBounds(this.assignNode.getType(), widestOperationType);
      (new CodeGenerator.OptimisticOperation((Optimistic)this.assignNode, bounds) {
          void loadStack() {
            boolean forceConversionSeparation;
            if (UnwarrantedOptimismException.isValid(getProgramPoint()) || widestOperationType == Type.NUMBER) {
              forceConversionSeparation = false;
            } else {
              Type operandType = Type.widest(CodeGenerator.booleanToInt(CodeGenerator.objectToNumber(lhs.getType())), CodeGenerator.booleanToInt(CodeGenerator.objectToNumber(rhs.getType())));
              forceConversionSeparation = operandType.narrowerThan(widestOperationType);
            } 
            CodeGenerator.this.loadBinaryOperands(lhs, rhs, bounds, true, forceConversionSeparation);
          }
          
          void consumeStack() {
            CodeGenerator.BinaryOptimisticSelfAssignment.this.op(this);
          }
        }).emit(CodeGenerator.getOptimisticIgnoreCountForSelfModifyingExpression(lhs));
      CodeGenerator.this.method.convert(this.assignNode.getType());
    }
  }
  
  class null extends OptimisticOperation {
    null(Optimistic optimistic, CodeGenerator.TypeBounds resultBounds) {
      super(optimistic, resultBounds);
    }
    
    void loadStack() {
      boolean forceConversionSeparation;
      if (UnwarrantedOptimismException.isValid(getProgramPoint()) || widestOperationType == Type.NUMBER) {
        forceConversionSeparation = false;
      } else {
        Type operandType = Type.widest(CodeGenerator.booleanToInt(CodeGenerator.objectToNumber(lhs.getType())), CodeGenerator.booleanToInt(CodeGenerator.objectToNumber(rhs.getType())));
        forceConversionSeparation = operandType.narrowerThan(widestOperationType);
      } 
      CodeGenerator.this.loadBinaryOperands(lhs, rhs, bounds, true, forceConversionSeparation);
    }
    
    void consumeStack() {
      CodeGenerator.BinaryOptimisticSelfAssignment.this.op(this);
    }
  }
  
  private abstract class BinarySelfAssignment extends SelfModifyingStore<BinaryNode> {
    BinarySelfAssignment(BinaryNode node) {
      super(node, node.lhs());
    }
    
    protected abstract void op();
    
    protected void evaluate() {
      CodeGenerator.this.loadBinaryOperands(this.assignNode.lhs(), this.assignNode.rhs(), CodeGenerator.TypeBounds.UNBOUNDED.notWiderThan(this.assignNode.getWidestOperandType()), true, false);
      op();
    }
  }
  
  private void loadASSIGN_ADD(final BinaryNode binaryNode) {
    (new BinaryOptimisticSelfAssignment(binaryNode) {
        protected void op(CodeGenerator.OptimisticOperation oo) {
          assert !binaryNode.getType().isObject() || !oo.isOptimistic;
          CodeGenerator.this.method.add(oo.getProgramPoint());
        }
      }).store();
  }
  
  private void loadASSIGN_BIT_AND(BinaryNode binaryNode) {
    (new BinarySelfAssignment(binaryNode) {
        protected void op() {
          CodeGenerator.this.method.and();
        }
      }).store();
  }
  
  private void loadASSIGN_BIT_OR(BinaryNode binaryNode) {
    (new BinarySelfAssignment(binaryNode) {
        protected void op() {
          CodeGenerator.this.method.or();
        }
      }).store();
  }
  
  private void loadASSIGN_BIT_XOR(BinaryNode binaryNode) {
    (new BinarySelfAssignment(binaryNode) {
        protected void op() {
          CodeGenerator.this.method.xor();
        }
      }).store();
  }
  
  private void loadASSIGN_DIV(BinaryNode binaryNode) {
    (new BinaryOptimisticSelfAssignment(binaryNode) {
        protected void op(CodeGenerator.OptimisticOperation oo) {
          CodeGenerator.this.method.div(oo.getProgramPoint());
        }
      }).store();
  }
  
  private void loadASSIGN_MOD(BinaryNode binaryNode) {
    (new BinaryOptimisticSelfAssignment(binaryNode) {
        protected void op(CodeGenerator.OptimisticOperation oo) {
          CodeGenerator.this.method.rem(oo.getProgramPoint());
        }
      }).store();
  }
  
  private void loadASSIGN_MUL(BinaryNode binaryNode) {
    (new BinaryOptimisticSelfAssignment(binaryNode) {
        protected void op(CodeGenerator.OptimisticOperation oo) {
          CodeGenerator.this.method.mul(oo.getProgramPoint());
        }
      }).store();
  }
  
  private void loadASSIGN_SAR(BinaryNode binaryNode) {
    (new BinarySelfAssignment(binaryNode) {
        protected void op() {
          CodeGenerator.this.method.sar();
        }
      }).store();
  }
  
  private void loadASSIGN_SHL(BinaryNode binaryNode) {
    (new BinarySelfAssignment(binaryNode) {
        protected void op() {
          CodeGenerator.this.method.shl();
        }
      }).store();
  }
  
  private void loadASSIGN_SHR(final BinaryNode binaryNode) {
    (new SelfModifyingStore<BinaryNode>(binaryNode, binaryNode.lhs()) {
        protected void evaluate() {
          (new CodeGenerator.OptimisticOperation((Optimistic)this.assignNode, new CodeGenerator.TypeBounds((Type)Type.INT, (Type)Type.NUMBER)) {
              void loadStack() {
                assert CodeGenerator.null.this.assignNode.getWidestOperandType() == Type.INT;
                if (CodeGenerator.isRhsZero(binaryNode)) {
                  CodeGenerator.this.loadExpression(binaryNode.lhs(), CodeGenerator.TypeBounds.INT, true);
                } else {
                  CodeGenerator.this.loadBinaryOperands(binaryNode.lhs(), binaryNode.rhs(), CodeGenerator.TypeBounds.INT, true, false);
                  CodeGenerator.this.method.shr();
                } 
              }
              
              void consumeStack() {
                if (CodeGenerator.isOptimistic((Optimistic)binaryNode)) {
                  CodeGenerator.this.toUint32Optimistic(binaryNode.getProgramPoint());
                } else {
                  CodeGenerator.this.toUint32Double();
                } 
              }
            }).emit(CodeGenerator.getOptimisticIgnoreCountForSelfModifyingExpression(binaryNode.lhs()));
          CodeGenerator.this.method.convert(this.assignNode.getType());
        }
      }).store();
  }
  
  private void doSHR(final BinaryNode binaryNode) {
    (new OptimisticOperation((Optimistic)binaryNode, new TypeBounds((Type)Type.INT, (Type)Type.NUMBER)) {
        void loadStack() {
          if (CodeGenerator.isRhsZero(binaryNode)) {
            CodeGenerator.this.loadExpressionAsType(binaryNode.lhs(), (Type)Type.INT);
          } else {
            CodeGenerator.this.loadBinaryOperands(binaryNode);
            CodeGenerator.this.method.shr();
          } 
        }
        
        void consumeStack() {
          if (CodeGenerator.isOptimistic((Optimistic)binaryNode)) {
            CodeGenerator.this.toUint32Optimistic(binaryNode.getProgramPoint());
          } else {
            CodeGenerator.this.toUint32Double();
          } 
        }
      }).emit();
  }
  
  private void toUint32Optimistic(int programPoint) {
    this.method.load(programPoint);
    JSType.TO_UINT32_OPTIMISTIC.invoke(this.method);
  }
  
  private void toUint32Double() {
    JSType.TO_UINT32_DOUBLE.invoke(this.method);
  }
  
  private void loadASSIGN_SUB(BinaryNode binaryNode) {
    (new BinaryOptimisticSelfAssignment(binaryNode) {
        protected void op(CodeGenerator.OptimisticOperation oo) {
          CodeGenerator.this.method.sub(oo.getProgramPoint());
        }
      }).store();
  }
  
  private abstract class BinaryArith {
    protected abstract void op(int param1Int);
    
    protected void evaluate(final BinaryNode node, CodeGenerator.TypeBounds resultBounds) {
      final CodeGenerator.TypeBounds numericBounds = resultBounds.booleanToInt().objectToNumber();
      (new CodeGenerator.OptimisticOperation((Optimistic)node, numericBounds) {
          void loadStack() {
            CodeGenerator.TypeBounds operandBounds;
            boolean forceConversionSeparation = false;
            if (numericBounds.narrowest == Type.NUMBER) {
              assert numericBounds.widest == Type.NUMBER;
              operandBounds = numericBounds;
            } else {
              boolean isOptimistic = UnwarrantedOptimismException.isValid(getProgramPoint());
              if (isOptimistic || node.isTokenType(TokenType.DIV) || node.isTokenType(TokenType.MOD)) {
                operandBounds = new CodeGenerator.TypeBounds(node.getType(), (Type)Type.NUMBER);
              } else {
                operandBounds = new CodeGenerator.TypeBounds(Type.narrowest(node.getWidestOperandType(), numericBounds.widest), (Type)Type.NUMBER);
                forceConversionSeparation = true;
              } 
            } 
            CodeGenerator.this.loadBinaryOperands(node.lhs(), node.rhs(), operandBounds, false, forceConversionSeparation);
          }
          
          void consumeStack() {
            CodeGenerator.BinaryArith.this.op(getProgramPoint());
          }
        }).emit();
    }
  }
  
  class null extends OptimisticOperation {
    null(Optimistic optimistic, CodeGenerator.TypeBounds resultBounds) {
      super(optimistic, resultBounds);
    }
    
    void loadStack() {
      CodeGenerator.TypeBounds operandBounds;
      boolean forceConversionSeparation = false;
      if (numericBounds.narrowest == Type.NUMBER) {
        assert numericBounds.widest == Type.NUMBER;
        operandBounds = numericBounds;
      } else {
        boolean isOptimistic = UnwarrantedOptimismException.isValid(getProgramPoint());
        if (isOptimistic || node.isTokenType(TokenType.DIV) || node.isTokenType(TokenType.MOD)) {
          operandBounds = new CodeGenerator.TypeBounds(node.getType(), (Type)Type.NUMBER);
        } else {
          operandBounds = new CodeGenerator.TypeBounds(Type.narrowest(node.getWidestOperandType(), numericBounds.widest), (Type)Type.NUMBER);
          forceConversionSeparation = true;
        } 
      } 
      CodeGenerator.this.loadBinaryOperands(node.lhs(), node.rhs(), operandBounds, false, forceConversionSeparation);
    }
    
    void consumeStack() {
      CodeGenerator.BinaryArith.this.op(getProgramPoint());
    }
  }
  
  private void loadBIT_AND(BinaryNode binaryNode) {
    loadBinaryOperands(binaryNode);
    this.method.and();
  }
  
  private void loadBIT_OR(BinaryNode binaryNode) {
    if (isRhsZero(binaryNode)) {
      loadExpressionAsType(binaryNode.lhs(), (Type)Type.INT);
    } else {
      loadBinaryOperands(binaryNode);
      this.method.or();
    } 
  }
  
  private static boolean isRhsZero(BinaryNode binaryNode) {
    Expression rhs = binaryNode.rhs();
    return (rhs instanceof LiteralNode && INT_ZERO.equals(((LiteralNode)rhs).getValue()));
  }
  
  private void loadBIT_XOR(BinaryNode binaryNode) {
    loadBinaryOperands(binaryNode);
    this.method.xor();
  }
  
  private void loadCOMMARIGHT(BinaryNode binaryNode, TypeBounds resultBounds) {
    loadAndDiscard(binaryNode.lhs());
    loadMaybeDiscard((Expression)binaryNode, binaryNode.rhs(), resultBounds);
  }
  
  private void loadDIV(BinaryNode binaryNode, TypeBounds resultBounds) {
    (new BinaryArith() {
        protected void op(int programPoint) {
          CodeGenerator.this.method.div(programPoint);
        }
      }).evaluate(binaryNode, resultBounds);
  }
  
  private void loadCmp(BinaryNode binaryNode, Condition cond) {
    loadComparisonOperands(binaryNode);
    Label trueLabel = new Label("trueLabel");
    Label afterLabel = new Label("skip");
    this.method.conditionalJump(cond, trueLabel);
    this.method.load(Boolean.FALSE.booleanValue());
    this.method._goto(afterLabel);
    this.method.label(trueLabel);
    this.method.load(Boolean.TRUE.booleanValue());
    this.method.label(afterLabel);
  }
  
  private void loadMOD(BinaryNode binaryNode, TypeBounds resultBounds) {
    (new BinaryArith() {
        protected void op(int programPoint) {
          CodeGenerator.this.method.rem(programPoint);
        }
      }).evaluate(binaryNode, resultBounds);
  }
  
  private void loadMUL(BinaryNode binaryNode, TypeBounds resultBounds) {
    (new BinaryArith() {
        protected void op(int programPoint) {
          CodeGenerator.this.method.mul(programPoint);
        }
      }).evaluate(binaryNode, resultBounds);
  }
  
  private void loadSAR(BinaryNode binaryNode) {
    loadBinaryOperands(binaryNode);
    this.method.sar();
  }
  
  private void loadSHL(BinaryNode binaryNode) {
    loadBinaryOperands(binaryNode);
    this.method.shl();
  }
  
  private void loadSHR(BinaryNode binaryNode) {
    doSHR(binaryNode);
  }
  
  private void loadSUB(BinaryNode binaryNode, TypeBounds resultBounds) {
    (new BinaryArith() {
        protected void op(int programPoint) {
          CodeGenerator.this.method.sub(programPoint);
        }
      }).evaluate(binaryNode, resultBounds);
  }
  
  public boolean enterLabelNode(LabelNode labelNode) {
    this.labeledBlockBreakLiveLocals.push(((CodeGeneratorLexicalContext)this.lc).getUsedSlotCount());
    return true;
  }
  
  protected boolean enterDefault(Node node) {
    throw new AssertionError("Code generator entered node of type " + node.getClass().getName());
  }
  
  private void loadTernaryNode(TernaryNode ternaryNode, TypeBounds resultBounds) {
    Expression test = ternaryNode.getTest();
    JoinPredecessorExpression trueExpr = ternaryNode.getTrueExpression();
    JoinPredecessorExpression falseExpr = ternaryNode.getFalseExpression();
    Label falseLabel = new Label("ternary_false");
    Label exitLabel = new Label("ternary_exit");
    Type outNarrowest = Type.narrowest(resultBounds.widest, Type.generic(Type.widestReturnType(trueExpr.getType(), falseExpr.getType())));
    TypeBounds outBounds = resultBounds.notNarrowerThan(outNarrowest);
    emitBranch(test, falseLabel, false);
    boolean isCurrentDiscard = ((CodeGeneratorLexicalContext)this.lc).popDiscardIfCurrent((Expression)ternaryNode);
    loadMaybeDiscard(isCurrentDiscard, trueExpr.getExpression(), outBounds);
    assert isCurrentDiscard || Type.generic(this.method.peekType()) == outBounds.narrowest;
    this.method.beforeJoinPoint((JoinPredecessor)trueExpr);
    this.method._goto(exitLabel);
    this.method.label(falseLabel);
    loadMaybeDiscard(isCurrentDiscard, falseExpr.getExpression(), outBounds);
    assert isCurrentDiscard || Type.generic(this.method.peekType()) == outBounds.narrowest;
    this.method.beforeJoinPoint((JoinPredecessor)falseExpr);
    this.method.label(exitLabel);
  }
  
  void generateScopeCalls() {
    for (SharedScopeCall scopeAccess : ((CodeGeneratorLexicalContext)this.lc).getScopeCalls())
      scopeAccess.generateScopeCall(); 
  }
  
  private void printSymbols(Block block, FunctionNode function, String ident) {
    if ((this.compiler.getScriptEnvironment())._print_symbols || function.getDebugFlag(16)) {
      PrintWriter out = this.compiler.getScriptEnvironment().getErr();
      out.println("[BLOCK in '" + ident + "']");
      if (!block.printSymbols(out))
        out.println("<no symbols>"); 
      out.println();
    } 
  }
  
  private abstract class SelfModifyingStore<T extends Expression> extends Store<T> {
    protected SelfModifyingStore(T assignNode, Expression target) {
      super(assignNode, target);
    }
    
    protected boolean isSelfModifying() {
      return true;
    }
  }
  
  private abstract class Store<T extends Expression> {
    protected final T assignNode;
    
    private final Expression target;
    
    private int depth;
    
    private IdentNode quick;
    
    protected Store(T assignNode, Expression target) {
      this.assignNode = assignNode;
      this.target = target;
    }
    
    protected Store(T assignNode) {
      this(assignNode, (Expression)assignNode);
    }
    
    protected boolean isSelfModifying() {
      return false;
    }
    
    private void prologue() {
      this.target.accept((NodeVisitor)new SimpleNodeVisitor() {
            public boolean enterIdentNode(IdentNode node) {
              if (node.getSymbol().isScope()) {
                CodeGenerator.this.method.loadCompilerConstant(CompilerConstants.SCOPE);
                CodeGenerator.Store.this.depth += Type.SCOPE.getSlots();
                assert CodeGenerator.Store.this.depth == 1;
              } 
              return false;
            }
            
            private void enterBaseNode() {
              assert CodeGenerator.Store.this.target instanceof BaseNode : "error - base node " + CodeGenerator.Store.this.target + " must be instanceof BaseNode";
              BaseNode baseNode = (BaseNode)CodeGenerator.Store.this.target;
              Expression base = baseNode.getBase();
              CodeGenerator.this.loadExpressionAsObject(base);
              CodeGenerator.Store.this.depth += Type.OBJECT.getSlots();
              assert CodeGenerator.Store.this.depth == 1;
              if (CodeGenerator.Store.this.isSelfModifying())
                CodeGenerator.this.method.dup(); 
            }
            
            public boolean enterAccessNode(AccessNode node) {
              enterBaseNode();
              return false;
            }
            
            public boolean enterIndexNode(IndexNode node) {
              enterBaseNode();
              Expression index = node.getIndex();
              if (!index.getType().isNumeric()) {
                CodeGenerator.this.loadExpressionAsObject(index);
              } else {
                CodeGenerator.this.loadExpressionUnbounded(index);
              } 
              CodeGenerator.Store.this.depth += index.getType().getSlots();
              if (CodeGenerator.Store.this.isSelfModifying())
                CodeGenerator.this.method.dup(1); 
              return false;
            }
          });
    }
    
    private IdentNode quickLocalVariable(Type type) {
      String name = ((CodeGeneratorLexicalContext)CodeGenerator.this.lc).getCurrentFunction().uniqueName(CompilerConstants.QUICK_PREFIX.symbolName());
      Symbol symbol = new Symbol(name, 1088);
      symbol.setHasSlotFor(type);
      symbol.setFirstSlot(((CodeGeneratorLexicalContext)CodeGenerator.this.lc).quickSlot(type));
      return IdentNode.createInternalIdentifier(symbol).setType(type);
    }
    
    protected void storeNonDiscard() {
      if (((CodeGeneratorLexicalContext)CodeGenerator.this.lc).popDiscardIfCurrent((Expression)this.assignNode)) {
        assert this.assignNode.isAssignment();
        return;
      } 
      if (CodeGenerator.this.method.dup(this.depth) == null) {
        CodeGenerator.this.method.dup();
        Type quickType = CodeGenerator.this.method.peekType();
        this.quick = quickLocalVariable(quickType);
        Symbol quickSymbol = this.quick.getSymbol();
        CodeGenerator.this.method.storeTemp(quickType, quickSymbol.getFirstSlot());
      } 
    }
    
    private void epilogue() {
      this.target.accept((NodeVisitor)new SimpleNodeVisitor() {
            protected boolean enterDefault(Node node) {
              throw new AssertionError("Unexpected node " + node + " in store epilogue");
            }
            
            public boolean enterIdentNode(IdentNode node) {
              Symbol symbol = node.getSymbol();
              assert symbol != null;
              if (symbol.isScope()) {
                int flags = CodeGenerator.this.getScopeCallSiteFlags(symbol) | (node.isDeclaredHere() ? 512 : 0);
                if (CodeGenerator.this.isFastScope(symbol)) {
                  CodeGenerator.this.storeFastScopeVar(symbol, flags);
                } else {
                  CodeGenerator.this.method.dynamicSet(node.getName(), flags, false);
                } 
              } else {
                Type storeType = CodeGenerator.Store.this.assignNode.getType();
                assert storeType != Type.LONG;
                if (symbol.hasSlotFor(storeType))
                  CodeGenerator.this.method.convert(storeType); 
                CodeGenerator.this.storeIdentWithCatchConversion(node, storeType);
              } 
              return false;
            }
            
            public boolean enterAccessNode(AccessNode node) {
              CodeGenerator.this.method.dynamicSet(node.getProperty(), CodeGenerator.this.getCallSiteFlags(), node.isIndex());
              return false;
            }
            
            public boolean enterIndexNode(IndexNode node) {
              CodeGenerator.this.method.dynamicSetIndex(CodeGenerator.this.getCallSiteFlags());
              return false;
            }
          });
    }
    
    protected abstract void evaluate();
    
    void store() {
      if (this.target instanceof IdentNode)
        CodeGenerator.this.checkTemporalDeadZone((IdentNode)this.target); 
      prologue();
      evaluate();
      storeNonDiscard();
      epilogue();
      if (this.quick != null)
        CodeGenerator.this.method.load(this.quick); 
    }
  }
  
  class null extends SimpleNodeVisitor {
    public boolean enterIdentNode(IdentNode node) {
      if (node.getSymbol().isScope()) {
        CodeGenerator.this.method.loadCompilerConstant(CompilerConstants.SCOPE);
        CodeGenerator.Store.this.depth += Type.SCOPE.getSlots();
        assert CodeGenerator.Store.this.depth == 1;
      } 
      return false;
    }
    
    private void enterBaseNode() {
      assert CodeGenerator.Store.this.target instanceof BaseNode : "error - base node " + CodeGenerator.Store.this.target + " must be instanceof BaseNode";
      BaseNode baseNode = (BaseNode)CodeGenerator.Store.this.target;
      Expression base = baseNode.getBase();
      CodeGenerator.this.loadExpressionAsObject(base);
      CodeGenerator.Store.this.depth += Type.OBJECT.getSlots();
      assert CodeGenerator.Store.this.depth == 1;
      if (CodeGenerator.Store.this.isSelfModifying())
        CodeGenerator.this.method.dup(); 
    }
    
    public boolean enterAccessNode(AccessNode node) {
      enterBaseNode();
      return false;
    }
    
    public boolean enterIndexNode(IndexNode node) {
      enterBaseNode();
      Expression index = node.getIndex();
      if (!index.getType().isNumeric()) {
        CodeGenerator.this.loadExpressionAsObject(index);
      } else {
        CodeGenerator.this.loadExpressionUnbounded(index);
      } 
      CodeGenerator.Store.this.depth += index.getType().getSlots();
      if (CodeGenerator.Store.this.isSelfModifying())
        CodeGenerator.this.method.dup(1); 
      return false;
    }
  }
  
  class null extends SimpleNodeVisitor {
    protected boolean enterDefault(Node node) {
      throw new AssertionError("Unexpected node " + node + " in store epilogue");
    }
    
    public boolean enterIdentNode(IdentNode node) {
      Symbol symbol = node.getSymbol();
      assert symbol != null;
      if (symbol.isScope()) {
        int flags = CodeGenerator.this.getScopeCallSiteFlags(symbol) | (node.isDeclaredHere() ? 512 : 0);
        if (CodeGenerator.this.isFastScope(symbol)) {
          CodeGenerator.this.storeFastScopeVar(symbol, flags);
        } else {
          CodeGenerator.this.method.dynamicSet(node.getName(), flags, false);
        } 
      } else {
        Type storeType = CodeGenerator.Store.this.assignNode.getType();
        assert storeType != Type.LONG;
        if (symbol.hasSlotFor(storeType))
          CodeGenerator.this.method.convert(storeType); 
        CodeGenerator.this.storeIdentWithCatchConversion(node, storeType);
      } 
      return false;
    }
    
    public boolean enterAccessNode(AccessNode node) {
      CodeGenerator.this.method.dynamicSet(node.getProperty(), CodeGenerator.this.getCallSiteFlags(), node.isIndex());
      return false;
    }
    
    public boolean enterIndexNode(IndexNode node) {
      CodeGenerator.this.method.dynamicSetIndex(CodeGenerator.this.getCallSiteFlags());
      return false;
    }
  }
  
  private void newFunctionObject(FunctionNode functionNode, boolean addInitializer) {
    assert ((CodeGeneratorLexicalContext)this.lc).peek() == functionNode;
    RecompilableScriptFunctionData data = this.compiler.getScriptFunctionData(functionNode.getId());
    if (functionNode.isProgram() && !this.compiler.isOnDemandCompilation()) {
      MethodEmitter createFunction = functionNode.getCompileUnit().getClassEmitter().method(
          EnumSet.of(ClassEmitter.Flag.PUBLIC, ClassEmitter.Flag.STATIC), CompilerConstants.CREATE_PROGRAM_FUNCTION.symbolName(), ScriptFunction.class, new Class[] { ScriptObject.class });
      createFunction.begin();
      loadConstantsAndIndex(data, createFunction);
      createFunction.load(SCOPE_TYPE, 0);
      createFunction.invoke(CREATE_FUNCTION_OBJECT);
      createFunction._return();
      createFunction.end();
    } 
    if (addInitializer && !this.compiler.isOnDemandCompilation())
      functionNode.getCompileUnit().addFunctionInitializer(data, functionNode); 
    if (((CodeGeneratorLexicalContext)this.lc).getOutermostFunction() == functionNode)
      return; 
    loadConstantsAndIndex(data, this.method);
    if (functionNode.needsParentScope()) {
      this.method.loadCompilerConstant(CompilerConstants.SCOPE);
      this.method.invoke(CREATE_FUNCTION_OBJECT);
    } else {
      this.method.invoke(CREATE_FUNCTION_OBJECT_NO_SCOPE);
    } 
  }
  
  private void globalInstance() {
    this.method.invokestatic(GLOBAL_OBJECT, "instance", "()L" + GLOBAL_OBJECT + ";");
  }
  
  private void globalAllocateArguments() {
    this.method.invokestatic(GLOBAL_OBJECT, "allocateArguments", CompilerConstants.methodDescriptor(ScriptObject.class, new Class[] { Object[].class, Object.class, int.class }));
  }
  
  private void globalNewRegExp() {
    this.method.invokestatic(GLOBAL_OBJECT, "newRegExp", CompilerConstants.methodDescriptor(Object.class, new Class[] { String.class, String.class }));
  }
  
  private void globalRegExpCopy() {
    this.method.invokestatic(GLOBAL_OBJECT, "regExpCopy", CompilerConstants.methodDescriptor(Object.class, new Class[] { Object.class }));
  }
  
  private void globalAllocateArray(ArrayType type) {
    this.method.invokestatic(GLOBAL_OBJECT, "allocate", "(" + type.getDescriptor() + ")Lorg/openjdk/nashorn/internal/objects/NativeArray;");
  }
  
  private void globalIsEval() {
    this.method.invokestatic(GLOBAL_OBJECT, "isEval", CompilerConstants.methodDescriptor(boolean.class, new Class[] { Object.class }));
  }
  
  private void globalReplaceLocationPropertyPlaceholder() {
    this.method.invokestatic(GLOBAL_OBJECT, "replaceLocationPropertyPlaceholder", CompilerConstants.methodDescriptor(Object.class, new Class[] { Object.class, Object.class }));
  }
  
  private void globalCheckObjectCoercible() {
    this.method.invokestatic(GLOBAL_OBJECT, "checkObjectCoercible", CompilerConstants.methodDescriptor(void.class, new Class[] { Object.class }));
  }
  
  private void globalDirectEval() {
    this.method.invokestatic(GLOBAL_OBJECT, "directEval", 
        CompilerConstants.methodDescriptor(Object.class, new Class[] { Object.class, Object.class, Object.class, Object.class, boolean.class }));
  }
  
  private abstract class OptimisticOperation {
    final boolean isOptimistic;
    
    private final Expression expression;
    
    private final Optimistic optimistic;
    
    private final CodeGenerator.TypeBounds resultBounds;
    
    OptimisticOperation(Optimistic optimistic, CodeGenerator.TypeBounds resultBounds) {
      this.optimistic = optimistic;
      this.expression = (Expression)optimistic;
      this.resultBounds = resultBounds;
      this
        
        .isOptimistic = (CodeGenerator.isOptimistic(optimistic) && resultBounds.within(Type.generic(((Expression)optimistic).getType())).narrowerThan(resultBounds.widest));
      assert !this.isOptimistic || CodeGenerator.this.useOptimisticTypes();
    }
    
    void emit() {
      emit(0);
    }
    
    void emit(int ignoredArgCount) {
      Label beginTry, catchLabel;
      int programPoint = this.optimistic.getProgramPoint();
      boolean optimisticOrContinuation = (this.isOptimistic || CodeGenerator.this.isContinuationEntryPoint(programPoint));
      boolean currentContinuationEntryPoint = CodeGenerator.this.isCurrentContinuationEntryPoint(programPoint);
      int stackSizeOnEntry = CodeGenerator.this.method.getStackSize() - ignoredArgCount;
      storeStack(ignoredArgCount, optimisticOrContinuation);
      loadStack();
      int liveLocalsCount = storeStack(CodeGenerator.this.method.getStackSize() - stackSizeOnEntry, optimisticOrContinuation);
      assert optimisticOrContinuation == ((liveLocalsCount != -1));
      Label afterConsumeStack = (this.isOptimistic || currentContinuationEntryPoint) ? new Label("after_consume_stack") : null;
      if (this.isOptimistic) {
        beginTry = new Label("try_optimistic");
        String catchLabelName = afterConsumeStack.toString() + "_handler";
        catchLabel = new Label(catchLabelName);
        CodeGenerator.this.method.label(beginTry);
      } else {
        beginTry = catchLabel = null;
      } 
      consumeStack();
      if (this.isOptimistic)
        CodeGenerator.this.method._try(beginTry, afterConsumeStack, catchLabel, UnwarrantedOptimismException.class); 
      if (this.isOptimistic || currentContinuationEntryPoint) {
        CodeGenerator.this.method.label(afterConsumeStack);
        int[] localLoads = CodeGenerator.this.method.getLocalLoadsOnStack(0, stackSizeOnEntry);
        assert CodeGenerator.everyStackValueIsLocalLoad(localLoads) : Arrays.toString(localLoads) + ", " + Arrays.toString(localLoads) + ", " + stackSizeOnEntry;
        List<Type> localTypesList = CodeGenerator.this.method.getLocalVariableTypes();
        int usedLocals = CodeGenerator.this.method.getUsedSlotsWithLiveTemporaries();
        List<Type> localTypes = CodeGenerator.this.method.getWidestLiveLocals(localTypesList.subList(0, usedLocals));
        assert CodeGenerator.everyLocalLoadIsValid(localLoads, usedLocals) : Arrays.toString(localLoads) + " ~ " + Arrays.toString(localLoads);
        if (this.isOptimistic)
          addUnwarrantedOptimismHandlerLabel(localTypes, catchLabel); 
        if (currentContinuationEntryPoint) {
          CodeGenerator.ContinuationInfo ci = CodeGenerator.this.getContinuationInfo();
          assert ci != null : "no continuation info found for " + ((CodeGeneratorLexicalContext)CodeGenerator.this.lc).getCurrentFunction();
          assert !ci.hasTargetLabel();
          ci.setTargetLabel(afterConsumeStack);
          ci.getHandlerLabel().markAsOptimisticContinuationHandlerFor(afterConsumeStack);
          ci.lvarCount = localTypes.size();
          ci.setStackStoreSpec(localLoads);
          ci.setStackTypes(Arrays.<Type>copyOf(CodeGenerator.this.method.getTypesFromStack(CodeGenerator.this.method.getStackSize()), stackSizeOnEntry));
          assert (ci.getStackStoreSpec()).length == (ci.getStackTypes()).length;
          ci.setReturnValueType(CodeGenerator.this.method.peekType());
          ci.lineNumber = CodeGenerator.this.getLastLineNumber();
          ci.catchLabel = CodeGenerator.this.catchLabels.peek();
        } 
      } 
    }
    
    private int storeStack(int ignoreArgCount, boolean optimisticOrContinuation) {
      if (!optimisticOrContinuation)
        return -1; 
      int stackSize = CodeGenerator.this.method.getStackSize();
      Type[] stackTypes = CodeGenerator.this.method.getTypesFromStack(stackSize);
      int[] localLoadsOnStack = CodeGenerator.this.method.getLocalLoadsOnStack(0, stackSize);
      int usedSlots = CodeGenerator.this.method.getUsedSlotsWithLiveTemporaries();
      int firstIgnored = stackSize - ignoreArgCount;
      int firstNonLoad = 0;
      while (firstNonLoad < firstIgnored && localLoadsOnStack[firstNonLoad] != -1)
        firstNonLoad++; 
      if (firstNonLoad >= firstIgnored)
        return usedSlots; 
      int tempSlotsNeeded = 0;
      for (int i = firstNonLoad; i < stackSize; i++) {
        if (localLoadsOnStack[i] == -1)
          tempSlotsNeeded += stackTypes[i].getSlots(); 
      } 
      int lastTempSlot = usedSlots + tempSlotsNeeded;
      int ignoreSlotCount = 0;
      for (int j = stackSize; j-- > firstNonLoad; ) {
        int loadSlot = localLoadsOnStack[j];
        if (loadSlot == -1) {
          Type type = stackTypes[j];
          int slots = type.getSlots();
          lastTempSlot -= slots;
          if (j >= firstIgnored)
            ignoreSlotCount += slots; 
          CodeGenerator.this.method.storeTemp(type, lastTempSlot);
          continue;
        } 
        CodeGenerator.this.method.pop();
      } 
      assert lastTempSlot == usedSlots;
      List<Type> localTypesList = CodeGenerator.this.method.getLocalVariableTypes();
      for (int k = firstNonLoad; k < stackSize; k++) {
        int loadSlot = localLoadsOnStack[k];
        Type stackType = stackTypes[k];
        boolean isLoad = (loadSlot != -1);
        int lvarSlot = isLoad ? loadSlot : lastTempSlot;
        Type lvarType = localTypesList.get(lvarSlot);
        CodeGenerator.this.method.load(lvarType, lvarSlot);
        if (isLoad) {
          CodeGenerator.this.method.convert(stackType);
        } else {
          assert lvarType == stackType;
          lastTempSlot += lvarType.getSlots();
        } 
      } 
      assert lastTempSlot == usedSlots + tempSlotsNeeded;
      return lastTempSlot - ignoreSlotCount;
    }
    
    private void addUnwarrantedOptimismHandlerLabel(List<Type> localTypes, Label label) {
      String lvarTypesDescriptor = CodeGenerator.this.getLvarTypesDescriptor(localTypes);
      Map<String, Collection<Label>> unwarrantedOptimismHandlers = ((CodeGeneratorLexicalContext)CodeGenerator.this.lc).getUnwarrantedOptimismHandlers();
      Collection<Label> labels = unwarrantedOptimismHandlers.computeIfAbsent(lvarTypesDescriptor, k -> new LinkedList());
      CodeGenerator.this.method.markLabelAsOptimisticCatchHandler(label, localTypes.size());
      labels.add(label);
    }
    
    void dynamicGet(String name, int flags, boolean isMethod, boolean isIndex) {
      if (this.isOptimistic) {
        CodeGenerator.this.method.dynamicGet(getOptimisticCoercedType(), name, getOptimisticFlags(flags), isMethod, isIndex);
      } else {
        CodeGenerator.this.method.dynamicGet(this.resultBounds.within(this.expression.getType()), name, CodeGenerator.nonOptimisticFlags(flags), isMethod, isIndex);
      } 
    }
    
    void dynamicGetIndex(int flags, boolean isMethod) {
      if (this.isOptimistic) {
        CodeGenerator.this.method.dynamicGetIndex(getOptimisticCoercedType(), getOptimisticFlags(flags), isMethod);
      } else {
        CodeGenerator.this.method.dynamicGetIndex(this.resultBounds.within(this.expression.getType()), CodeGenerator.nonOptimisticFlags(flags), isMethod);
      } 
    }
    
    void dynamicCall(int argCount, int flags, String msg) {
      if (this.isOptimistic) {
        CodeGenerator.this.method.dynamicCall(getOptimisticCoercedType(), argCount, getOptimisticFlags(flags), msg);
      } else {
        CodeGenerator.this.method.dynamicCall(this.resultBounds.within(this.expression.getType()), argCount, CodeGenerator.nonOptimisticFlags(flags), msg);
      } 
    }
    
    int getOptimisticFlags(int flags) {
      return flags | 0x80 | this.optimistic.getProgramPoint() << 15;
    }
    
    int getProgramPoint() {
      return this.isOptimistic ? this.optimistic.getProgramPoint() : -1;
    }
    
    void convertOptimisticReturnValue() {
      if (this.isOptimistic) {
        Type optimisticType = getOptimisticCoercedType();
        if (!optimisticType.isObject()) {
          CodeGenerator.this.method.load(this.optimistic.getProgramPoint());
          if (optimisticType.isInteger()) {
            CodeGenerator.this.method.invoke(CodeGenerator.ENSURE_INT);
          } else if (optimisticType.isNumber()) {
            CodeGenerator.this.method.invoke(CodeGenerator.ENSURE_NUMBER);
          } else {
            throw new AssertionError(optimisticType);
          } 
        } 
      } 
    }
    
    void replaceCompileTimeProperty() {
      IdentNode identNode = (IdentNode)this.expression;
      String name = identNode.getSymbol().getName();
      if (CompilerConstants.__FILE__.name().equals(name)) {
        replaceCompileTimeProperty(CodeGenerator.this.getCurrentSource().getName());
      } else if (CompilerConstants.__DIR__.name().equals(name)) {
        replaceCompileTimeProperty(CodeGenerator.this.getCurrentSource().getBase());
      } else if (CompilerConstants.__LINE__.name().equals(name)) {
        replaceCompileTimeProperty(Integer.valueOf(CodeGenerator.this.getCurrentSource().getLine(identNode.position())));
      } 
    }
    
    private void replaceCompileTimeProperty(Object propertyValue) {
      assert CodeGenerator.this.method.peekType().isObject();
      if (propertyValue instanceof String || propertyValue == null) {
        CodeGenerator.this.method.load((String)propertyValue);
      } else if (propertyValue instanceof Integer) {
        CodeGenerator.this.method.load(((Integer)propertyValue).intValue());
        CodeGenerator.this.method.convert(Type.OBJECT);
      } else {
        throw new AssertionError();
      } 
      CodeGenerator.this.globalReplaceLocationPropertyPlaceholder();
      convertOptimisticReturnValue();
    }
    
    Type getOptimisticCoercedType() {
      Type optimisticType = this.expression.getType();
      assert this.resultBounds.widest.widerThan(optimisticType);
      Type narrowest = this.resultBounds.narrowest;
      if (narrowest.isBoolean() || narrowest.narrowerThan(optimisticType)) {
        assert !optimisticType.isObject();
        return optimisticType;
      } 
      assert !narrowest.isObject();
      return narrowest;
    }
    
    abstract void loadStack();
    
    abstract void consumeStack();
  }
  
  private static boolean isOptimistic(Optimistic optimistic) {
    if (!optimistic.canBeOptimistic())
      return false; 
    Expression expr = (Expression)optimistic;
    return expr.getType().narrowerThan(expr.getWidestOperationType());
  }
  
  private static boolean everyLocalLoadIsValid(int[] loads, int localCount) {
    for (int load : loads) {
      if (load < 0 || load >= localCount)
        return false; 
    } 
    return true;
  }
  
  private static boolean everyStackValueIsLocalLoad(int[] loads) {
    for (int load : loads) {
      if (load == -1)
        return false; 
    } 
    return true;
  }
  
  private String getLvarTypesDescriptor(List<Type> localVarTypes) {
    int count = localVarTypes.size();
    StringBuilder desc = new StringBuilder(count);
    int i;
    for (i = 0; i < count;)
      i += appendType(desc, localVarTypes.get(i)); 
    return this.method.markSymbolBoundariesInLvarTypesDescriptor(desc.toString());
  }
  
  private static int appendType(StringBuilder b, Type t) {
    b.append(t.getBytecodeStackType());
    return t.getSlots();
  }
  
  private static int countSymbolsInLvarTypeDescriptor(String lvarTypeDescriptor) {
    int count = 0;
    for (int i = 0; i < lvarTypeDescriptor.length(); i++) {
      if (Character.isUpperCase(lvarTypeDescriptor.charAt(i)))
        count++; 
    } 
    return count;
  }
  
  private boolean generateUnwarrantedOptimismExceptionHandlers(FunctionNode fn) {
    if (!useOptimisticTypes())
      return false; 
    Map<String, Collection<Label>> unwarrantedOptimismHandlers = ((CodeGeneratorLexicalContext)this.lc).popUnwarrantedOptimismHandlers();
    if (unwarrantedOptimismHandlers.isEmpty())
      return false; 
    this.method.lineNumber(0);
    List<OptimismExceptionHandlerSpec> handlerSpecs = new ArrayList<>(unwarrantedOptimismHandlers.size() * 4 / 3);
    for (String spec : unwarrantedOptimismHandlers.keySet())
      handlerSpecs.add(new OptimismExceptionHandlerSpec(spec, true)); 
    handlerSpecs.sort(Collections.reverseOrder());
    Map<String, Label> delegationLabels = new HashMap<>();
    for (int handlerIndex = 0; handlerIndex < handlerSpecs.size(); handlerIndex++) {
      int lvarIndex, firstArrayIndex, firstLvarIndex;
      Label delegationLabel;
      String commonLvarSpec;
      OptimismExceptionHandlerSpec spec = handlerSpecs.get(handlerIndex);
      String lvarSpec = spec.lvarSpec;
      if (spec.catchTarget) {
        assert !this.method.isReachable();
        this.method._catch(unwarrantedOptimismHandlers.get(lvarSpec));
        this.method.load(countSymbolsInLvarTypeDescriptor(lvarSpec));
        this.method.newarray(Type.OBJECT_ARRAY);
      } 
      if (spec.delegationTarget)
        this.method.label(delegationLabels.get(lvarSpec)); 
      boolean lastHandler = (handlerIndex == handlerSpecs.size() - 1);
      if (lastHandler) {
        lvarIndex = 0;
        firstLvarIndex = 0;
        firstArrayIndex = 0;
        delegationLabel = null;
        commonLvarSpec = null;
      } else {
        int nextHandlerIndex = handlerIndex + 1;
        String nextLvarSpec = ((OptimismExceptionHandlerSpec)handlerSpecs.get(nextHandlerIndex)).lvarSpec;
        commonLvarSpec = commonPrefix(lvarSpec, nextLvarSpec);
        assert Character.isUpperCase(commonLvarSpec.charAt(commonLvarSpec.length() - 1));
        boolean addNewHandler = true;
        int commonHandlerIndex = nextHandlerIndex;
        for (; commonHandlerIndex < handlerSpecs.size(); commonHandlerIndex++) {
          OptimismExceptionHandlerSpec forwardHandlerSpec = handlerSpecs.get(commonHandlerIndex);
          String forwardLvarSpec = forwardHandlerSpec.lvarSpec;
          if (forwardLvarSpec.equals(commonLvarSpec)) {
            addNewHandler = false;
            forwardHandlerSpec.delegationTarget = true;
            break;
          } 
          if (!forwardLvarSpec.startsWith(commonLvarSpec))
            break; 
        } 
        if (addNewHandler)
          handlerSpecs.add(commonHandlerIndex, new OptimismExceptionHandlerSpec(commonLvarSpec, false)); 
        firstArrayIndex = countSymbolsInLvarTypeDescriptor(commonLvarSpec);
        lvarIndex = 0;
        for (int j = 0; j < commonLvarSpec.length(); j++)
          lvarIndex += CodeGeneratorLexicalContext.getTypeForSlotDescriptor(commonLvarSpec.charAt(j)).getSlots(); 
        firstLvarIndex = lvarIndex;
        delegationLabel = delegationLabels.get(commonLvarSpec);
        if (delegationLabel == null) {
          delegationLabel = new Label("uo_pa_" + commonLvarSpec);
          delegationLabels.put(commonLvarSpec, delegationLabel);
        } 
      } 
      int args = 0;
      boolean symbolHadValue = false;
      for (int typeIndex = (commonLvarSpec == null) ? 0 : commonLvarSpec.length(); typeIndex < lvarSpec.length(); typeIndex++) {
        char typeDesc = lvarSpec.charAt(typeIndex);
        Type lvarType = CodeGeneratorLexicalContext.getTypeForSlotDescriptor(typeDesc);
        if (!lvarType.isUnknown()) {
          this.method.load(lvarType, lvarIndex);
          symbolHadValue = true;
          args++;
        } else if (typeDesc == 'U' && !symbolHadValue) {
          if (this.method.peekType() == Type.UNDEFINED) {
            this.method.dup();
          } else {
            this.method.loadUndefined(Type.OBJECT);
          } 
          args++;
        } 
        if (Character.isUpperCase(typeDesc))
          symbolHadValue = false; 
        lvarIndex += lvarType.getSlots();
      } 
      assert args > 0;
      this.method.dynamicArrayPopulatorCall(args + 1, firstArrayIndex);
      if (delegationLabel != null) {
        assert !lastHandler;
        assert commonLvarSpec != null;
        this.method.undefineLocalVariables(firstLvarIndex, true);
        OptimismExceptionHandlerSpec nextSpec = handlerSpecs.get(handlerIndex + 1);
        if (!nextSpec.lvarSpec.equals(commonLvarSpec) || nextSpec.catchTarget)
          this.method._goto(delegationLabel); 
      } else {
        assert lastHandler;
        loadConstant(getByteCodeSymbolNames(fn));
        if (isRestOf()) {
          loadConstant(getContinuationEntryPoints());
          this.method.invoke(CREATE_REWRITE_EXCEPTION_REST_OF);
        } else {
          this.method.invoke(CREATE_REWRITE_EXCEPTION);
        } 
        this.method.athrow();
      } 
    } 
    return true;
  }
  
  private static String[] getByteCodeSymbolNames(FunctionNode fn) {
    List<String> names = new ArrayList<>();
    for (Symbol symbol : fn.getBody().getSymbols()) {
      if (symbol.hasSlot()) {
        if (symbol.isScope()) {
          assert symbol.isParam();
          names.add(null);
          continue;
        } 
        names.add(symbol.getName());
      } 
    } 
    return names.<String>toArray(new String[0]);
  }
  
  private static String commonPrefix(String s1, String s2) {
    int l1 = s1.length();
    int l = Math.min(l1, s2.length());
    int lms = -1;
    for (int i = 0; i < l; i++) {
      char c1 = s1.charAt(i);
      if (c1 != s2.charAt(i))
        return s1.substring(0, lms + 1); 
      if (Character.isUpperCase(c1))
        lms = i; 
    } 
    return (l == l1) ? s1 : s2;
  }
  
  private static class OptimismExceptionHandlerSpec implements Comparable<OptimismExceptionHandlerSpec> {
    private final String lvarSpec;
    
    private final boolean catchTarget;
    
    private boolean delegationTarget;
    
    OptimismExceptionHandlerSpec(String lvarSpec, boolean catchTarget) {
      this.lvarSpec = lvarSpec;
      this.catchTarget = catchTarget;
      if (!catchTarget)
        this.delegationTarget = true; 
    }
    
    public int compareTo(OptimismExceptionHandlerSpec o) {
      return this.lvarSpec.compareTo(o.lvarSpec);
    }
    
    public String toString() {
      StringBuilder b = (new StringBuilder(64)).append("[HandlerSpec ").append(this.lvarSpec);
      if (this.catchTarget)
        b.append(", catchTarget"); 
      if (this.delegationTarget)
        b.append(", delegationTarget"); 
      return b.append("]").toString();
    }
  }
  
  private static class ContinuationInfo {
    private final Label handlerLabel = new Label("continuation_handler");
    
    private Label targetLabel;
    
    int lvarCount;
    
    private int[] stackStoreSpec;
    
    private Type[] stackTypes;
    
    private Type returnValueType;
    
    private Map<Integer, PropertyMap> objectLiteralMaps;
    
    private int lineNumber;
    
    private Label catchLabel;
    
    private int exceptionScopePops;
    
    Label getHandlerLabel() {
      return this.handlerLabel;
    }
    
    boolean hasTargetLabel() {
      return (this.targetLabel != null);
    }
    
    Label getTargetLabel() {
      return this.targetLabel;
    }
    
    void setTargetLabel(Label targetLabel) {
      this.targetLabel = targetLabel;
    }
    
    int[] getStackStoreSpec() {
      return (int[])this.stackStoreSpec.clone();
    }
    
    void setStackStoreSpec(int[] stackStoreSpec) {
      this.stackStoreSpec = stackStoreSpec;
    }
    
    Type[] getStackTypes() {
      return (Type[])this.stackTypes.clone();
    }
    
    void setStackTypes(Type[] stackTypes) {
      this.stackTypes = stackTypes;
    }
    
    Type getReturnValueType() {
      return this.returnValueType;
    }
    
    void setReturnValueType(Type returnValueType) {
      this.returnValueType = returnValueType;
    }
    
    void setObjectLiteralMap(int objectLiteralStackDepth, PropertyMap objectLiteralMap) {
      if (this.objectLiteralMaps == null)
        this.objectLiteralMaps = new HashMap<>(); 
      this.objectLiteralMaps.put(Integer.valueOf(objectLiteralStackDepth), objectLiteralMap);
    }
    
    PropertyMap getObjectLiteralMap(int stackDepth) {
      return (this.objectLiteralMaps == null) ? null : this.objectLiteralMaps.get(Integer.valueOf(stackDepth));
    }
    
    public String toString() {
      return "[localVariableTypes=" + this.targetLabel.getStack().getLocalVariableTypesCopy() + ", stackStoreSpec=" + 
        Arrays.toString(this.stackStoreSpec) + ", returnValueType=" + this.returnValueType + "]";
    }
  }
  
  private ContinuationInfo getContinuationInfo() {
    return this.continuationInfo;
  }
  
  private void generateContinuationHandler() {
    if (!isRestOf())
      return; 
    ContinuationInfo ci = getContinuationInfo();
    this.method.label(ci.getHandlerLabel());
    this.method.lineNumber(0);
    Label.Stack stack = ci.getTargetLabel().getStack();
    List<Type> lvarTypes = stack.getLocalVariableTypesCopy();
    BitSet symbolBoundary = stack.getSymbolBoundaryCopy();
    int lvarCount = ci.lvarCount;
    Type rewriteExceptionType = Type.typeFor(RewriteException.class);
    this.method.load(rewriteExceptionType, 0);
    this.method.storeTemp(rewriteExceptionType, lvarCount);
    this.method.load(rewriteExceptionType, 0);
    this.method.invoke(RewriteException.GET_BYTECODE_SLOTS);
    int arrayIndex = 0;
    int lvarIndex;
    for (lvarIndex = 0; lvarIndex < lvarCount; ) {
      Type lvarType = lvarTypes.get(lvarIndex);
      if (!lvarType.isUnknown()) {
        this.method.dup();
        this.method.load(arrayIndex).arrayload();
        Class<?> typeClass = lvarType.getTypeClass();
        if (typeClass == long[].class) {
          this.method.load(rewriteExceptionType, lvarCount);
          this.method.invoke(RewriteException.TO_LONG_ARRAY);
        } else if (typeClass == double[].class) {
          this.method.load(rewriteExceptionType, lvarCount);
          this.method.invoke(RewriteException.TO_DOUBLE_ARRAY);
        } else if (typeClass == Object[].class) {
          this.method.load(rewriteExceptionType, lvarCount);
          this.method.invoke(RewriteException.TO_OBJECT_ARRAY);
        } else {
          if (!typeClass.isPrimitive() && typeClass != Object.class) {
            this.method.loadType(Type.getInternalName(typeClass));
            this.method.invoke(RewriteException.INSTANCE_OR_NULL);
          } 
          this.method.convert(lvarType);
        } 
        this.method.storeHidden(lvarType, lvarIndex, false);
      } 
      int nextLvarIndex = lvarIndex + lvarType.getSlots();
      if (symbolBoundary.get(nextLvarIndex - 1))
        arrayIndex++; 
      lvarIndex = nextLvarIndex;
    } 
    if (AssertsEnabled.assertsEnabled()) {
      this.method.load(arrayIndex);
      this.method.invoke(RewriteException.ASSERT_ARRAY_LENGTH);
    } else {
      this.method.pop();
    } 
    int[] stackStoreSpec = ci.getStackStoreSpec();
    Type[] stackTypes = ci.getStackTypes();
    boolean isStackEmpty = (stackStoreSpec.length == 0);
    int replacedObjectLiteralMaps = 0;
    if (!isStackEmpty)
      for (int i = 0; i < stackStoreSpec.length; i++) {
        int slot = stackStoreSpec[i];
        this.method.load(lvarTypes.get(slot), slot);
        this.method.convert(stackTypes[i]);
        PropertyMap map = ci.getObjectLiteralMap(i);
        if (map != null) {
          this.method.dup();
          assert ScriptObject.class.isAssignableFrom(this.method.peekType().getTypeClass()) : "" + this.method.peekType().getTypeClass() + " is not a script object";
          loadConstant(map);
          this.method.invoke(ScriptObject.SET_MAP);
          replacedObjectLiteralMaps++;
        } 
      }  
    assert ci.objectLiteralMaps == null || ci.objectLiteralMaps.size() == replacedObjectLiteralMaps;
    this.method.load(rewriteExceptionType, lvarCount);
    this.method.loadNull();
    this.method.storeHidden(Type.OBJECT, lvarCount);
    this.method.markDeadSlots(lvarCount, Type.OBJECT.getSlots());
    this.method.invoke(RewriteException.GET_RETURN_VALUE);
    Type returnValueType = ci.getReturnValueType();
    boolean needsCatch = false;
    Label targetCatchLabel = ci.catchLabel;
    Label _try = null;
    if (returnValueType.isPrimitive()) {
      this.method.lineNumber(ci.lineNumber);
      if (targetCatchLabel != METHOD_BOUNDARY) {
        _try = new Label("");
        this.method.label(_try);
        needsCatch = true;
      } 
    } 
    this.method.convert(returnValueType);
    int scopePopCount = needsCatch ? ci.exceptionScopePops : 0;
    Label catchLabel = (scopePopCount > 0) ? new Label("") : targetCatchLabel;
    if (needsCatch) {
      Label _end_try = new Label("");
      this.method.label(_end_try);
      this.method._try(_try, _end_try, catchLabel);
    } 
    this.method._goto(ci.getTargetLabel());
    if (catchLabel != targetCatchLabel) {
      this.method.lineNumber(0);
      assert scopePopCount > 0;
      this.method._catch(catchLabel);
      popScopes(scopePopCount);
      this.method.uncheckedGoto(targetCatchLabel);
    } 
  }
  
  static interface SplitLiteralCreator {
    void populateRange(MethodEmitter param1MethodEmitter, Type param1Type, int param1Int1, int param1Int2, int param1Int3);
  }
}
