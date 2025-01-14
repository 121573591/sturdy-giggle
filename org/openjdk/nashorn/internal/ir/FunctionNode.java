package org.openjdk.nashorn.internal.ir;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.openjdk.nashorn.internal.codegen.CompileUnit;
import org.openjdk.nashorn.internal.codegen.CompilerConstants;
import org.openjdk.nashorn.internal.codegen.Namespace;
import org.openjdk.nashorn.internal.codegen.types.Type;
import org.openjdk.nashorn.internal.ir.annotations.Ignore;
import org.openjdk.nashorn.internal.ir.annotations.Immutable;
import org.openjdk.nashorn.internal.ir.visitor.NodeVisitor;
import org.openjdk.nashorn.internal.parser.Token;
import org.openjdk.nashorn.internal.runtime.ScriptFunction;
import org.openjdk.nashorn.internal.runtime.Source;

@Immutable
public final class FunctionNode extends LexicalContextExpression implements Flags<FunctionNode>, CompileUnitHolder {
  private static final long serialVersionUID = 1L;
  
  public static final Type FUNCTION_TYPE = Type.typeFor(ScriptFunction.class);
  
  private final transient Source source;
  
  private final Object endParserState;
  
  @Ignore
  private final IdentNode ident;
  
  private final Block body;
  
  private final String name;
  
  private final CompileUnit compileUnit;
  
  private final Kind kind;
  
  private final List<IdentNode> parameters;
  
  private final Map<IdentNode, Expression> parameterExpressions;
  
  private final long firstToken;
  
  private final long lastToken;
  
  private final transient Namespace namespace;
  
  @Ignore
  private final int thisProperties;
  
  private final int flags;
  
  private final int lineNumber;
  
  private final Class<?> rootClass;
  
  private final Module module;
  
  private final int debugFlags;
  
  public static final int IS_ANONYMOUS = 1;
  
  public static final int IS_DECLARED = 2;
  
  public static final int IS_STRICT = 4;
  
  public static final int USES_ARGUMENTS = 8;
  
  public static final int IS_SPLIT = 16;
  
  public static final int HAS_EVAL = 32;
  
  public static final int HAS_NESTED_EVAL = 64;
  
  public static final int HAS_SCOPE_BLOCK = 128;
  
  public static final int DEFINES_ARGUMENTS = 256;
  
  public static final int USES_ANCESTOR_SCOPE = 512;
  
  public static final int HAS_FUNCTION_DECLARATIONS = 1024;
  
  public static final int IS_DEOPTIMIZABLE = 2048;
  
  public static final int HAS_APPLY_TO_CALL_SPECIALIZATION = 4096;
  
  public static final int IS_PROGRAM = 8192;
  
  public static final int USES_SELF_SYMBOL = 16384;
  
  public static final int USES_THIS = 32768;
  
  public static final int IN_DYNAMIC_CONTEXT = 65536;
  
  public static final int NEEDS_CALLEE = 131072;
  
  public static final int IS_CACHED = 262144;
  
  public static final int ES6_HAS_DIRECT_SUPER = 524288;
  
  public static final int ES6_USES_SUPER = 1048576;
  
  public static final int ES6_IS_METHOD = 2097152;
  
  public static final int ES6_IS_CLASS_CONSTRUCTOR = 4194304;
  
  public static final int ES6_IS_SUBCLASS_CONSTRUCTOR = 8388608;
  
  public static final int ES6_IS_STRONG = 16777216;
  
  public static final int ES6_USES_NEW_TARGET = 33554432;
  
  public static final int HAS_EXPRESSION_BODY = 67108864;
  
  private static final int HAS_DEEP_EVAL = 96;
  
  public static final int HAS_ALL_VARS_IN_SCOPE = 96;
  
  private static final int MAYBE_NEEDS_ARGUMENTS = 40;
  
  public static final int NEEDS_PARENT_SCOPE = 8800;
  
  public static final int DEBUG_PRINT_PARSE = 1;
  
  public static final int DEBUG_PRINT_LOWER_PARSE = 2;
  
  public static final int DEBUG_PRINT_AST = 4;
  
  public static final int DEBUG_PRINT_LOWER_AST = 8;
  
  public static final int DEBUG_PRINT_SYMBOLS = 16;
  
  public static final int DEBUG_PROFILE = 32;
  
  public static final int DEBUG_TRACE_ENTEREXIT = 64;
  
  public static final int DEBUG_TRACE_MISSES = 128;
  
  public static final int DEBUG_TRACE_VALUES = 256;
  
  public static final int DEBUG_CALLSITE_FLAGS = 511;
  
  public enum Kind {
    NORMAL, SCRIPT, GETTER, SETTER, ARROW, GENERATOR, MODULE;
  }
  
  public Type returnType = Type.UNKNOWN;
  
  public FunctionNode(Source source, int lineNumber, long token, int finish, long firstToken, long lastToken, Namespace namespace, IdentNode ident, String name, List<IdentNode> parameters, Map<IdentNode, Expression> paramExprs, Kind kind, int flags, Block body, Object endParserState, Module module, int debugFlags) {
    super(token, finish);
    this.source = source;
    this.lineNumber = lineNumber;
    this.ident = ident;
    this.name = name;
    this.kind = kind;
    this.parameters = parameters;
    this.parameterExpressions = paramExprs;
    this.firstToken = firstToken;
    this.lastToken = lastToken;
    this.namespace = namespace;
    this.flags = flags;
    this.compileUnit = null;
    this.body = body;
    this.thisProperties = 0;
    this.rootClass = null;
    this.endParserState = endParserState;
    this.module = module;
    this.debugFlags = debugFlags;
  }
  
  private FunctionNode(FunctionNode functionNode, long lastToken, Object endParserState, int flags, String name, Type returnType, CompileUnit compileUnit, Block body, List<IdentNode> parameters, int thisProperties, Class<?> rootClass, Source source, Namespace namespace) {
    super(functionNode);
    this.endParserState = endParserState;
    this.lineNumber = functionNode.lineNumber;
    this.flags = flags;
    this.name = name;
    this.returnType = returnType;
    this.compileUnit = compileUnit;
    this.lastToken = lastToken;
    this.body = body;
    this.parameters = parameters;
    this.parameterExpressions = functionNode.parameterExpressions;
    this.thisProperties = thisProperties;
    this.rootClass = rootClass;
    this.source = source;
    this.namespace = namespace;
    this.ident = functionNode.ident;
    this.kind = functionNode.kind;
    this.firstToken = functionNode.firstToken;
    this.module = functionNode.module;
    this.debugFlags = functionNode.debugFlags;
  }
  
  public Node accept(LexicalContext lc, NodeVisitor<? extends LexicalContext> visitor) {
    if (visitor.enterFunctionNode(this))
      return visitor.leaveFunctionNode(setBody(lc, (Block)this.body.accept(visitor))); 
    return this;
  }
  
  public List<IdentNode> visitParameters(NodeVisitor<? extends LexicalContext> visitor) {
    return Node.accept(visitor, this.parameters);
  }
  
  public int getCallSiteFlags() {
    int callsiteFlags = 0;
    if (getFlag(4))
      callsiteFlags |= 0x20; 
    if ((this.debugFlags & 0x1FF) == 0)
      return callsiteFlags; 
    if (getDebugFlag(32))
      callsiteFlags |= 0x400; 
    if (getDebugFlag(128))
      callsiteFlags |= 0x1800; 
    if (getDebugFlag(256))
      callsiteFlags |= 0x6800; 
    if (getDebugFlag(64))
      callsiteFlags |= 0x2800; 
    return callsiteFlags;
  }
  
  public Source getSource() {
    return this.source;
  }
  
  public FunctionNode initializeDeserialized(Source source, Namespace namespace) {
    if (source == null || namespace == null)
      throw new IllegalArgumentException(); 
    if (this.source == source && this.namespace == namespace)
      return this; 
    if (this.source != null || this.namespace != null)
      throw new IllegalStateException(); 
    return new FunctionNode(this, this.lastToken, this.endParserState, this.flags, this.name, this.returnType, this.compileUnit, this.body, this.parameters, this.thisProperties, this.rootClass, source, namespace);
  }
  
  public int getId() {
    return isProgram() ? -1 : Token.descPosition(this.firstToken);
  }
  
  public String getSourceName() {
    return getSourceName(this.source);
  }
  
  public static String getSourceName(Source source) {
    String explicitURL = source.getExplicitURL();
    return (explicitURL != null) ? explicitURL : source.getName();
  }
  
  public static int getDirectiveFlag(String directive) {
    switch (directive) {
      case "nashorn callsite trace enterexit":
        return 64;
      case "nashorn callsite trace misses":
        return 128;
      case "nashorn callsite trace objects":
        return 256;
      case "nashorn callsite profile":
        return 32;
      case "nashorn print parse":
        return 1;
      case "nashorn print lower parse":
        return 2;
      case "nashorn print ast":
        return 4;
      case "nashorn print lower ast":
        return 8;
      case "nashorn print symbols":
        return 16;
    } 
    return 0;
  }
  
  public int getLineNumber() {
    return this.lineNumber;
  }
  
  public String uniqueName(String base) {
    return this.namespace.uniqueName(base);
  }
  
  public void toString(StringBuilder sb, boolean printTypes) {
    sb.append('[')
      .append(this.returnType)
      .append(']')
      .append(' ');
    sb.append("function");
    if (this.ident != null) {
      sb.append(' ');
      this.ident.toString(sb, printTypes);
    } 
    sb.append('(');
    for (Iterator<IdentNode> iter = this.parameters.iterator(); iter.hasNext(); ) {
      IdentNode parameter = iter.next();
      if (parameter.getSymbol() != null)
        sb.append('[').append(parameter.getType()).append(']').append(' '); 
      parameter.toString(sb, printTypes);
      if (iter.hasNext())
        sb.append(", "); 
    } 
    sb.append(')');
  }
  
  public int getFlags() {
    return this.flags;
  }
  
  public boolean getFlag(int flag) {
    return ((this.flags & flag) != 0);
  }
  
  public FunctionNode setFlags(LexicalContext lc, int flags) {
    if (this.flags == flags)
      return this; 
    return Node.<FunctionNode>replaceInLexicalContext(lc, this, new FunctionNode(this, this.lastToken, this.endParserState, flags, this.name, this.returnType, this.compileUnit, this.body, this.parameters, this.thisProperties, this.rootClass, this.source, this.namespace));
  }
  
  public FunctionNode clearFlag(LexicalContext lc, int flag) {
    return setFlags(lc, this.flags & (flag ^ 0xFFFFFFFF));
  }
  
  public FunctionNode setFlag(LexicalContext lc, int flag) {
    return setFlags(lc, this.flags | flag);
  }
  
  public int getDebugFlags() {
    return this.debugFlags;
  }
  
  public boolean getDebugFlag(int debugFlag) {
    return ((this.debugFlags & debugFlag) != 0);
  }
  
  public boolean isProgram() {
    return getFlag(8192);
  }
  
  public boolean canBeDeoptimized() {
    return getFlag(2048);
  }
  
  public boolean hasEval() {
    return getFlag(32);
  }
  
  public boolean hasNestedEval() {
    return getFlag(64);
  }
  
  public long getFirstToken() {
    return this.firstToken;
  }
  
  public boolean hasDeclaredFunctions() {
    return getFlag(1024);
  }
  
  public boolean needsCallee() {
    return (needsParentScope() || usesSelfSymbol() || isSplit() || ((needsArguments() || hasApplyToCallSpecialization()) && !isStrict()));
  }
  
  public boolean usesThis() {
    return getFlag(32768);
  }
  
  public boolean hasApplyToCallSpecialization() {
    return getFlag(4096);
  }
  
  public IdentNode getIdent() {
    return this.ident;
  }
  
  public Block getBody() {
    return this.body;
  }
  
  public FunctionNode setBody(LexicalContext lc, Block body) {
    if (this.body == body)
      return this; 
    return Node.<FunctionNode>replaceInLexicalContext(lc, this, new FunctionNode(this, this.lastToken, this.endParserState, this.flags | (
          
          body.needsScope() ? 
          128 : 
          0), this.name, this.returnType, this.compileUnit, body, this.parameters, this.thisProperties, this.rootClass, this.source, this.namespace));
  }
  
  public boolean isVarArg() {
    return (needsArguments() || this.parameters.size() > 125);
  }
  
  public boolean inDynamicContext() {
    return getFlag(65536);
  }
  
  public boolean needsDynamicScope() {
    return (hasEval() && !isStrict());
  }
  
  public FunctionNode setInDynamicContext(LexicalContext lc) {
    return setFlag(lc, 65536);
  }
  
  public boolean needsArguments() {
    return (getFlag(40) && !getFlag(256) && !isProgram());
  }
  
  public boolean needsParentScope() {
    return getFlag(8800);
  }
  
  public FunctionNode setThisProperties(LexicalContext lc, int thisProperties) {
    if (this.thisProperties == thisProperties)
      return this; 
    return Node.<FunctionNode>replaceInLexicalContext(lc, this, new FunctionNode(this, this.lastToken, this.endParserState, this.flags, this.name, this.returnType, this.compileUnit, this.body, this.parameters, thisProperties, this.rootClass, this.source, this.namespace));
  }
  
  public int getThisProperties() {
    return this.thisProperties;
  }
  
  public boolean hasScopeBlock() {
    return getFlag(128);
  }
  
  public Kind getKind() {
    return this.kind;
  }
  
  public long getLastToken() {
    return this.lastToken;
  }
  
  public Object getEndParserState() {
    return this.endParserState;
  }
  
  public String getName() {
    return this.name;
  }
  
  public FunctionNode setName(LexicalContext lc, String name) {
    if (this.name.equals(name))
      return this; 
    return Node.<FunctionNode>replaceInLexicalContext(lc, this, new FunctionNode(this, this.lastToken, this.endParserState, this.flags, name, this.returnType, this.compileUnit, this.body, this.parameters, this.thisProperties, this.rootClass, this.source, this.namespace));
  }
  
  public boolean allVarsInScope() {
    return getFlag(96);
  }
  
  public boolean isSplit() {
    return getFlag(16);
  }
  
  public List<IdentNode> getParameters() {
    return Collections.unmodifiableList(this.parameters);
  }
  
  public Map<IdentNode, Expression> getParameterExpressions() {
    return this.parameterExpressions;
  }
  
  public int getNumOfParams() {
    return this.parameters.size();
  }
  
  public IdentNode getParameter(int index) {
    return this.parameters.get(index);
  }
  
  public FunctionNode setParameters(LexicalContext lc, List<IdentNode> parameters) {
    if (this.parameters == parameters)
      return this; 
    return Node.<FunctionNode>replaceInLexicalContext(lc, this, new FunctionNode(this, this.lastToken, this.endParserState, this.flags, this.name, this.returnType, this.compileUnit, this.body, parameters, this.thisProperties, this.rootClass, this.source, this.namespace));
  }
  
  public boolean isDeclared() {
    return getFlag(2);
  }
  
  public boolean isAnonymous() {
    return getFlag(1);
  }
  
  public boolean usesSelfSymbol() {
    return getFlag(16384);
  }
  
  public boolean isNamedFunctionExpression() {
    return !getFlag(8195);
  }
  
  public Type getType() {
    return FUNCTION_TYPE;
  }
  
  public Type getWidestOperationType() {
    return FUNCTION_TYPE;
  }
  
  public Type getReturnType() {
    return this.returnType;
  }
  
  public FunctionNode setReturnType(LexicalContext lc, Type returnType) {
    Type type = returnType.isObject() ? Type.OBJECT : returnType;
    if (this.returnType == type)
      return this; 
    return Node.<FunctionNode>replaceInLexicalContext(lc, this, new FunctionNode(this, this.lastToken, this.endParserState, this.flags, this.name, type, this.compileUnit, this.body, this.parameters, this.thisProperties, this.rootClass, this.source, this.namespace));
  }
  
  public boolean isStrict() {
    return getFlag(4);
  }
  
  public boolean isCached() {
    return getFlag(262144);
  }
  
  public FunctionNode setCached(LexicalContext lc) {
    return setFlag(lc, 262144);
  }
  
  public boolean isStrong() {
    return getFlag(16777216);
  }
  
  public boolean isMethod() {
    return getFlag(2097152);
  }
  
  public boolean usesSuper() {
    return getFlag(1048576);
  }
  
  public boolean hasDirectSuper() {
    return getFlag(524288);
  }
  
  public boolean isClassConstructor() {
    return getFlag(4194304);
  }
  
  public boolean isSubclassConstructor() {
    return getFlag(8388608);
  }
  
  public boolean usesNewTarget() {
    return getFlag(33554432);
  }
  
  public boolean isModule() {
    return (this.kind == Kind.MODULE);
  }
  
  public Module getModule() {
    return this.module;
  }
  
  public CompileUnit getCompileUnit() {
    return this.compileUnit;
  }
  
  public FunctionNode setCompileUnit(LexicalContext lc, CompileUnit compileUnit) {
    if (this.compileUnit == compileUnit)
      return this; 
    return Node.<FunctionNode>replaceInLexicalContext(lc, this, new FunctionNode(this, this.lastToken, this.endParserState, this.flags, this.name, this.returnType, compileUnit, this.body, this.parameters, this.thisProperties, this.rootClass, this.source, this.namespace));
  }
  
  public Symbol compilerConstant(CompilerConstants cc) {
    return this.body.getExistingSymbol(cc.symbolName());
  }
  
  public Class<?> getRootClass() {
    return this.rootClass;
  }
  
  public FunctionNode setRootClass(LexicalContext lc, Class<?> rootClass) {
    if (this.rootClass == rootClass)
      return this; 
    return Node.<FunctionNode>replaceInLexicalContext(lc, this, new FunctionNode(this, this.lastToken, this.endParserState, this.flags, this.name, this.returnType, this.compileUnit, this.body, this.parameters, this.thisProperties, rootClass, this.source, this.namespace));
  }
}
