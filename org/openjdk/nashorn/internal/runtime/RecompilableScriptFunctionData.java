package org.openjdk.nashorn.internal.runtime;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.openjdk.nashorn.internal.codegen.Compiler;
import org.openjdk.nashorn.internal.codegen.CompilerConstants;
import org.openjdk.nashorn.internal.codegen.FunctionSignature;
import org.openjdk.nashorn.internal.codegen.Namespace;
import org.openjdk.nashorn.internal.codegen.OptimisticTypesPersistence;
import org.openjdk.nashorn.internal.codegen.TypeMap;
import org.openjdk.nashorn.internal.codegen.types.Type;
import org.openjdk.nashorn.internal.ir.Block;
import org.openjdk.nashorn.internal.ir.ForNode;
import org.openjdk.nashorn.internal.ir.FunctionNode;
import org.openjdk.nashorn.internal.ir.IdentNode;
import org.openjdk.nashorn.internal.ir.LexicalContext;
import org.openjdk.nashorn.internal.ir.Node;
import org.openjdk.nashorn.internal.ir.SwitchNode;
import org.openjdk.nashorn.internal.ir.Symbol;
import org.openjdk.nashorn.internal.ir.TryNode;
import org.openjdk.nashorn.internal.ir.visitor.NodeVisitor;
import org.openjdk.nashorn.internal.ir.visitor.SimpleNodeVisitor;
import org.openjdk.nashorn.internal.lookup.Lookup;
import org.openjdk.nashorn.internal.objects.Global;
import org.openjdk.nashorn.internal.parser.Parser;
import org.openjdk.nashorn.internal.parser.Token;
import org.openjdk.nashorn.internal.parser.TokenType;
import org.openjdk.nashorn.internal.runtime.linker.NameCodec;
import org.openjdk.nashorn.internal.runtime.logging.DebugLogger;
import org.openjdk.nashorn.internal.runtime.logging.Loggable;
import org.openjdk.nashorn.internal.runtime.logging.Logger;
import org.openjdk.nashorn.internal.runtime.options.Options;

@Logger(name = "recompile")
public final class RecompilableScriptFunctionData extends ScriptFunctionData implements Loggable {
  public static final String RECOMPILATION_PREFIX = "Recompilation$";
  
  private static final ExecutorService astSerializerExecutorService = createAstSerializerExecutorService();
  
  private final int functionNodeId;
  
  private final String functionName;
  
  private final int lineNumber;
  
  private transient Source source;
  
  private volatile transient Object cachedAst;
  
  private final long token;
  
  private final AllocationStrategy allocationStrategy;
  
  private final Object endParserState;
  
  private transient CodeInstaller installer;
  
  private final Map<Integer, RecompilableScriptFunctionData> nestedFunctions;
  
  private RecompilableScriptFunctionData parent;
  
  private final int functionFlags;
  
  private static final MethodHandles.Lookup LOOKUP = MethodHandles.lookup();
  
  private transient DebugLogger log;
  
  private final Map<String, Integer> externalScopeDepths;
  
  private final Set<String> internalSymbols;
  
  private static final int GET_SET_PREFIX_LENGTH = "*et ".length();
  
  private static final long serialVersionUID = 4914839316174633726L;
  
  public RecompilableScriptFunctionData(FunctionNode functionNode, CodeInstaller installer, AllocationStrategy allocationStrategy, Map<Integer, RecompilableScriptFunctionData> nestedFunctions, Map<String, Integer> externalScopeDepths, Set<String> internalSymbols) {
    super(functionName(functionNode), 
        Math.min(functionNode.getParameters().size(), 125), 
        getDataFlags(functionNode));
    this.functionName = functionNode.getName();
    this.lineNumber = functionNode.getLineNumber();
    this.functionFlags = functionNode.getFlags() | (functionNode.needsCallee() ? 131072 : 0);
    this.functionNodeId = functionNode.getId();
    this.source = functionNode.getSource();
    this.endParserState = functionNode.getEndParserState();
    this.token = tokenFor(functionNode);
    this.installer = installer;
    this.allocationStrategy = allocationStrategy;
    this.nestedFunctions = smallMap(nestedFunctions);
    this.externalScopeDepths = smallMap(externalScopeDepths);
    this.internalSymbols = smallSet(new HashSet<>(internalSymbols));
    for (RecompilableScriptFunctionData nfn : nestedFunctions.values()) {
      assert nfn.getParent() == null;
      nfn.setParent(this);
    } 
    createLogger();
  }
  
  private static <K, V> Map<K, V> smallMap(Map<K, V> map) {
    if (map == null || map.isEmpty())
      return Collections.emptyMap(); 
    if (map.size() == 1) {
      Map.Entry<K, V> entry = map.entrySet().iterator().next();
      return Collections.singletonMap(entry.getKey(), entry.getValue());
    } 
    return map;
  }
  
  private static <T> Set<T> smallSet(Set<T> set) {
    if (set == null || set.isEmpty())
      return Collections.emptySet(); 
    if (set.size() == 1)
      return Collections.singleton(set.iterator().next()); 
    return set;
  }
  
  public DebugLogger getLogger() {
    return this.log;
  }
  
  public DebugLogger initLogger(Context ctxt) {
    return ctxt.getLogger((Class)getClass());
  }
  
  public boolean hasInternalSymbol(String symbolName) {
    return this.internalSymbols.contains(symbolName);
  }
  
  public int getExternalSymbolDepth(String symbolName) {
    Integer depth = this.externalScopeDepths.get(symbolName);
    return (depth == null) ? -1 : depth.intValue();
  }
  
  public Set<String> getExternalSymbolNames() {
    return Collections.unmodifiableSet(this.externalScopeDepths.keySet());
  }
  
  public Object getEndParserState() {
    return this.endParserState;
  }
  
  public RecompilableScriptFunctionData getParent() {
    return this.parent;
  }
  
  void setParent(RecompilableScriptFunctionData parent) {
    this.parent = parent;
  }
  
  String toSource() {
    if (this.source != null && this.token != 0L)
      return this.source.getString(this.token); 
    return "function " + ((this.name == null) ? "" : this.name) + "() { [native code] }";
  }
  
  public void initTransients(Source src, CodeInstaller inst) {
    if (this.source == null && this.installer == null) {
      this.source = src;
      this.installer = inst;
      for (RecompilableScriptFunctionData nested : this.nestedFunctions.values())
        nested.initTransients(src, inst); 
    } else if (this.source != src || !this.installer.isCompatibleWith(inst)) {
      throw new IllegalArgumentException();
    } 
  }
  
  public String toString() {
    return super.toString() + "@" + super.toString();
  }
  
  public String toStringVerbose() {
    StringBuilder sb = new StringBuilder();
    sb.append("fnId=").append(this.functionNodeId).append(' ');
    if (this.source != null)
      sb.append(this.source.getName())
        .append(':')
        .append(this.lineNumber)
        .append(' '); 
    return sb.toString() + sb.toString();
  }
  
  public String getFunctionName() {
    return this.functionName;
  }
  
  public boolean inDynamicContext() {
    return getFunctionFlag(65536);
  }
  
  private static String functionName(FunctionNode fn) {
    if (fn.isAnonymous())
      return ""; 
    FunctionNode.Kind kind = fn.getKind();
    if (kind == FunctionNode.Kind.GETTER || kind == FunctionNode.Kind.SETTER) {
      String name = NameCodec.decode(fn.getIdent().getName());
      return name.substring(GET_SET_PREFIX_LENGTH);
    } 
    return fn.getIdent().getName();
  }
  
  private static long tokenFor(FunctionNode fn) {
    int position = Token.descPosition(fn.getFirstToken());
    long lastToken = Token.withDelimiter(fn.getLastToken());
    int length = Token.descPosition(lastToken) - position + ((Token.descType(lastToken) == TokenType.EOL) ? 0 : Token.descLength(lastToken));
    return Token.toDesc(TokenType.FUNCTION, position, length);
  }
  
  private static int getDataFlags(FunctionNode functionNode) {
    int flags = 4;
    if (functionNode.isStrict())
      flags |= 0x1; 
    if (functionNode.needsCallee())
      flags |= 0x8; 
    if (functionNode.usesThis() || functionNode.hasEval())
      flags |= 0x10; 
    if (functionNode.isVarArg())
      flags |= 0x20; 
    if (functionNode.getKind() == FunctionNode.Kind.GETTER || functionNode.getKind() == FunctionNode.Kind.SETTER)
      flags |= 0x40; 
    if (functionNode.isMethod() || functionNode.isClassConstructor())
      flags |= 0x80; 
    return flags;
  }
  
  PropertyMap getAllocatorMap(ScriptObject prototype) {
    return this.allocationStrategy.getAllocatorMap(prototype);
  }
  
  ScriptObject allocate(PropertyMap map) {
    return this.allocationStrategy.allocate(map);
  }
  
  FunctionNode reparse() {
    FunctionNode cachedFunction = getCachedAst();
    if (cachedFunction != null) {
      assert cachedFunction.isCached();
      return cachedFunction;
    } 
    int descPosition = Token.descPosition(this.token);
    Context context = Context.getContextTrusted();
    Parser parser = new Parser(context.getEnv(), this.source, new Context.ThrowErrorManager(), isStrict(), this.lineNumber - 1, context.getLogger((Class)Parser.class));
    if (getFunctionFlag(1))
      parser.setFunctionName(this.functionName); 
    parser.setReparsedFunction(this);
    FunctionNode program = parser.parse(CompilerConstants.PROGRAM.symbolName(), descPosition, 
        Token.descLength(this.token), this.flags);
    return (isProgram() ? program : extractFunctionFromScript(program)).setName(null, this.functionName);
  }
  
  private FunctionNode getCachedAst() {
    Object lCachedAst = this.cachedAst;
    if (lCachedAst instanceof Reference) {
      FunctionNode fn = ((Reference<FunctionNode>)lCachedAst).get();
      if (fn != null)
        return cloneSymbols(fn); 
    } else if (lCachedAst instanceof SerializedAst) {
      SerializedAst serializedAst = (SerializedAst)lCachedAst;
      FunctionNode cachedFn = (serializedAst.cachedAst == null) ? null : serializedAst.cachedAst.get();
      if (cachedFn != null)
        return cloneSymbols(cachedFn); 
      FunctionNode deserializedFn = deserialize(serializedAst.serializedAst);
      serializedAst.cachedAst = new SoftReference<>(deserializedFn);
      return deserializedFn;
    } 
    return null;
  }
  
  public void setCachedAst(FunctionNode astToCache) {
    assert astToCache.getId() == this.functionNodeId;
    assert !(this.cachedAst instanceof SerializedAst);
    boolean isSplit = astToCache.isSplit();
    assert !isSplit || this.cachedAst == null;
    FunctionNode symbolClonedAst = cloneSymbols(astToCache);
    Reference<FunctionNode> ref = new SoftReference<>(symbolClonedAst);
    this.cachedAst = ref;
    if (isSplit)
      astSerializerExecutorService.execute(() -> this.cachedAst = new SerializedAst(symbolClonedAst, ref)); 
  }
  
  private static ExecutorService createAstSerializerExecutorService() {
    int threads = Math.max(1, Options.getIntProperty("nashorn.serialize.threads", Runtime.getRuntime().availableProcessors() / 2));
    ThreadPoolExecutor service = new ThreadPoolExecutor(threads, threads, 1L, TimeUnit.MINUTES, new LinkedBlockingDeque<>(), r -> {
          Thread t = new Thread(r, "Nashorn AST Serializer");
          t.setDaemon(true);
          t.setPriority(4);
          return t;
        });
    service.allowCoreThreadTimeOut(true);
    return service;
  }
  
  private static class SerializedAst implements Serializable {
    private final byte[] serializedAst;
    
    private volatile transient Reference<FunctionNode> cachedAst;
    
    private static final long serialVersionUID = 1L;
    
    SerializedAst(FunctionNode fn, Reference<FunctionNode> cachedAst) {
      this.serializedAst = AstSerializer.serialize(fn);
      this.cachedAst = cachedAst;
    }
  }
  
  private FunctionNode deserialize(byte[] serializedAst) {
    ScriptEnvironment env = this.installer.getContext().getEnv();
    Timing timing = env._timing;
    long t1 = System.nanoTime();
    try {
      return AstDeserializer.deserialize(serializedAst).initializeDeserialized(this.source, new Namespace(env.getNamespace()));
    } finally {
      timing.accumulateTime("'Deserialize'", System.nanoTime() - t1);
    } 
  }
  
  private FunctionNode cloneSymbols(FunctionNode fn) {
    final IdentityHashMap<Symbol, Symbol> symbolReplacements = new IdentityHashMap<>();
    final boolean cached = fn.isCached();
    final Set<Symbol> blockDefinedSymbols = (fn.isSplit() && !cached) ? Collections.<Symbol>newSetFromMap(new IdentityHashMap<>()) : null;
    FunctionNode newFn = (FunctionNode)fn.accept((NodeVisitor)new SimpleNodeVisitor() {
          private Symbol getReplacement(Symbol original) {
            if (original == null)
              return null; 
            Symbol existingReplacement = (Symbol)symbolReplacements.get(original);
            if (existingReplacement != null)
              return existingReplacement; 
            Symbol newReplacement = original.clone();
            symbolReplacements.put(original, newReplacement);
            return newReplacement;
          }
          
          public Node leaveIdentNode(IdentNode identNode) {
            Symbol oldSymbol = identNode.getSymbol();
            if (oldSymbol != null) {
              Symbol replacement = getReplacement(oldSymbol);
              return (Node)identNode.setSymbol(replacement);
            } 
            return (Node)identNode;
          }
          
          public Node leaveForNode(ForNode forNode) {
            return ensureUniqueLabels((Node)forNode.setIterator(this.lc, getReplacement(forNode.getIterator())));
          }
          
          public Node leaveSwitchNode(SwitchNode switchNode) {
            return ensureUniqueLabels((Node)switchNode.setTag(this.lc, getReplacement(switchNode.getTag())));
          }
          
          public Node leaveTryNode(TryNode tryNode) {
            return ensureUniqueLabels((Node)tryNode.setException(this.lc, getReplacement(tryNode.getException())));
          }
          
          public boolean enterBlock(Block block) {
            for (Symbol symbol : block.getSymbols()) {
              Symbol replacement = getReplacement(symbol);
              if (blockDefinedSymbols != null)
                blockDefinedSymbols.add(replacement); 
            } 
            return true;
          }
          
          public Node leaveBlock(Block block) {
            return ensureUniqueLabels((Node)block.replaceSymbols(this.lc, symbolReplacements));
          }
          
          public Node leaveFunctionNode(FunctionNode functionNode) {
            return (Node)functionNode.setParameters(this.lc, functionNode.visitParameters((NodeVisitor)this));
          }
          
          protected Node leaveDefault(Node node) {
            return ensureUniqueLabels(node);
          }
          
          private Node ensureUniqueLabels(Node node) {
            return cached ? node.ensureUniqueLabels(this.lc) : node;
          }
        });
    if (blockDefinedSymbols != null) {
      Block newBody = null;
      for (Symbol symbol : symbolReplacements.values()) {
        if (!blockDefinedSymbols.contains(symbol)) {
          assert symbol.isScope();
          assert this.externalScopeDepths.containsKey(symbol.getName());
          symbol.setFlags(symbol.getFlags() & 0xFFFFFFFC | 0x1);
          if (newBody == null) {
            newBody = newFn.getBody().copyWithNewSymbols();
            newFn = newFn.setBody(null, newBody);
          } 
          assert newBody.getExistingSymbol(symbol.getName()) == null;
          newBody.putSymbol(symbol);
        } 
      } 
    } 
    return newFn.setCached(null);
  }
  
  private boolean getFunctionFlag(int flag) {
    return ((this.functionFlags & flag) != 0);
  }
  
  private boolean isProgram() {
    return getFunctionFlag(8192);
  }
  
  TypeMap typeMap(MethodType fnCallSiteType) {
    if (fnCallSiteType == null)
      return null; 
    if (CompiledFunction.isVarArgsType(fnCallSiteType))
      return null; 
    return new TypeMap(this.functionNodeId, explicitParams(fnCallSiteType), needsCallee());
  }
  
  private static ScriptObject newLocals(ScriptObject runtimeScope) {
    ScriptObject locals = Global.newEmptyInstance();
    locals.setProto(runtimeScope);
    return locals;
  }
  
  private Compiler getCompiler(FunctionNode fn, MethodType actualCallSiteType, ScriptObject runtimeScope) {
    return getCompiler(fn, actualCallSiteType, newLocals(runtimeScope), (Map<Integer, Type>)null, (int[])null);
  }
  
  private CodeInstaller getInstallerForNewCode() {
    ScriptEnvironment env = this.installer.getContext().getEnv();
    return (env._optimistic_types || env._loader_per_compile) ? this.installer.getOnDemandCompilationInstaller() : this.installer;
  }
  
  Compiler getCompiler(FunctionNode functionNode, MethodType actualCallSiteType, ScriptObject runtimeScope, Map<Integer, Type> invalidatedProgramPoints, int[] continuationEntryPoints) {
    TypeMap typeMap = typeMap(actualCallSiteType);
    Type[] paramTypes = (typeMap == null) ? null : typeMap.getParameterTypes(this.functionNodeId);
    Object typeInformationFile = OptimisticTypesPersistence.getLocationDescriptor(this.source, this.functionNodeId, paramTypes);
    return Compiler.forOnDemandCompilation(
        getInstallerForNewCode(), functionNode
        .getSource(), 
        isStrict() | functionNode.isStrict(), this, typeMap, 
        
        getEffectiveInvalidatedProgramPoints(invalidatedProgramPoints, typeInformationFile), typeInformationFile, continuationEntryPoints, runtimeScope);
  }
  
  private static Map<Integer, Type> getEffectiveInvalidatedProgramPoints(Map<Integer, Type> invalidatedProgramPoints, Object typeInformationFile) {
    if (invalidatedProgramPoints != null)
      return invalidatedProgramPoints; 
    Map<Integer, Type> loadedProgramPoints = OptimisticTypesPersistence.load(typeInformationFile);
    return (loadedProgramPoints != null) ? loadedProgramPoints : new TreeMap<>();
  }
  
  private FunctionInitializer compileTypeSpecialization(MethodType actualCallSiteType, ScriptObject runtimeScope, boolean persist) {
    if (this.log.isEnabled())
      this.log.info(new Object[] { "Parameter type specialization of '", this.functionName, "' signature: ", actualCallSiteType }); 
    boolean persistentCache = (persist && usePersistentCodeCache());
    String cacheKey = null;
    if (persistentCache) {
      TypeMap typeMap = typeMap(actualCallSiteType);
      Type[] paramTypes = (typeMap == null) ? null : typeMap.getParameterTypes(this.functionNodeId);
      cacheKey = CodeStore.getCacheKey(Integer.valueOf(this.functionNodeId), paramTypes);
      CodeInstaller newInstaller = getInstallerForNewCode();
      StoredScript script = newInstaller.loadScript(this.source, cacheKey);
      if (script != null) {
        Compiler.updateCompilationId(script.getCompilationId());
        return script.installFunction(this, newInstaller);
      } 
    } 
    FunctionNode fn = reparse();
    Compiler compiler = getCompiler(fn, actualCallSiteType, runtimeScope);
    FunctionNode compiledFn = compiler.compile(fn, 
        fn.isCached() ? Compiler.CompilationPhases.COMPILE_ALL_CACHED : Compiler.CompilationPhases.COMPILE_ALL);
    if (persist && !compiledFn.hasApplyToCallSpecialization())
      compiler.persistClassInfo(cacheKey, compiledFn); 
    return new FunctionInitializer(compiledFn, compiler.getInvalidatedProgramPoints());
  }
  
  boolean usePersistentCodeCache() {
    return (this.installer != null && (this.installer.getContext().getEnv())._persistent_cache);
  }
  
  private MethodType explicitParams(MethodType callSiteType) {
    if (CompiledFunction.isVarArgsType(callSiteType))
      return null; 
    MethodType noCalleeThisType = callSiteType.dropParameterTypes(0, 2);
    int callSiteParamCount = noCalleeThisType.parameterCount();
    Class<?>[] paramTypes = noCalleeThisType.parameterArray();
    boolean changed = false;
    for (int i = 0; i < paramTypes.length; i++) {
      Class<?> paramType = paramTypes[i];
      if (!paramType.isPrimitive() && paramType != Object.class) {
        paramTypes[i] = Object.class;
        changed = true;
      } 
    } 
    MethodType generalized = changed ? MethodType.methodType(noCalleeThisType.returnType(), paramTypes) : noCalleeThisType;
    if (callSiteParamCount < getArity())
      return generalized.appendParameterTypes(Collections.nCopies(getArity() - callSiteParamCount, Object.class)); 
    return generalized;
  }
  
  private FunctionNode extractFunctionFromScript(FunctionNode script) {
    final Set<FunctionNode> fns = new HashSet<>();
    script.getBody().accept((NodeVisitor)new SimpleNodeVisitor() {
          public boolean enterFunctionNode(FunctionNode fn) {
            fns.add(fn);
            return false;
          }
        });
    assert fns.size() == 1 : "got back more than one method in recompilation";
    FunctionNode f = fns.iterator().next();
    assert f.getId() == this.functionNodeId;
    if (!getFunctionFlag(2) && f.isDeclared())
      return f.clearFlag(null, 2); 
    return f;
  }
  
  private void logLookup(boolean shouldLog, MethodType targetType) {
    if (shouldLog && this.log.isEnabled())
      this.log.info(new Object[] { "Looking up ", DebugLogger.quote(this.functionName), " type=", targetType }); 
  }
  
  private MethodHandle lookup(FunctionInitializer fnInit, boolean shouldLog) {
    MethodType type = fnInit.getMethodType();
    logLookup(shouldLog, type);
    return lookupCodeMethod(fnInit.getCode(), type);
  }
  
  MethodHandle lookup(FunctionNode fn) {
    MethodType type = (new FunctionSignature(fn)).getMethodType();
    logLookup(true, type);
    return lookupCodeMethod(fn.getCompileUnit().getCode(), type);
  }
  
  MethodHandle lookupCodeMethod(Class<?> codeClass, MethodType targetType) {
    return Lookup.MH.findStatic(LOOKUP, codeClass, this.functionName, targetType);
  }
  
  public void initializeCode(FunctionNode functionNode) {
    if (!this.code.isEmpty() || functionNode.getId() != this.functionNodeId || !functionNode.getCompileUnit().isInitializing(this, functionNode))
      throw new IllegalStateException(this.name); 
    addCode(lookup(functionNode), (Map<Integer, Type>)null, (MethodType)null, functionNode.getFlags());
  }
  
  void initializeCode(FunctionInitializer initializer) {
    addCode(lookup(initializer, true), (Map<Integer, Type>)null, (MethodType)null, initializer.getFlags());
  }
  
  private CompiledFunction addCode(MethodHandle target, Map<Integer, Type> invalidatedProgramPoints, MethodType callSiteType, int fnFlags) {
    CompiledFunction cfn = new CompiledFunction(target, this, invalidatedProgramPoints, callSiteType, fnFlags);
    assert noDuplicateCode(cfn) : "duplicate code";
    this.code.add(cfn);
    return cfn;
  }
  
  private CompiledFunction addCode(FunctionInitializer fnInit, MethodType callSiteType) {
    if (isVariableArity())
      return addCode(lookup(fnInit, true), fnInit.getInvalidatedProgramPoints(), callSiteType, fnInit.getFlags()); 
    MethodHandle handle = lookup(fnInit, true);
    MethodType fromType = handle.type();
    MethodType toType = needsCallee(fromType) ? callSiteType.changeParameterType(0, ScriptFunction.class) : callSiteType.dropParameterTypes(0, 1);
    toType = toType.changeReturnType(fromType.returnType());
    int toCount = toType.parameterCount();
    int fromCount = fromType.parameterCount();
    int minCount = Math.min(fromCount, toCount);
    for (int i = 0; i < minCount; i++) {
      Class<?> fromParam = fromType.parameterType(i);
      Class<?> toParam = toType.parameterType(i);
      if (fromParam != toParam && !fromParam.isPrimitive() && !toParam.isPrimitive()) {
        assert fromParam.isAssignableFrom(toParam);
        toType = toType.changeParameterType(i, fromParam);
      } 
    } 
    if (fromCount > toCount) {
      toType = toType.appendParameterTypes(fromType.parameterList().subList(toCount, fromCount));
    } else if (fromCount < toCount) {
      toType = toType.dropParameterTypes(fromCount, toCount);
    } 
    return addCode(lookup(fnInit, false).asType(toType), fnInit.getInvalidatedProgramPoints(), callSiteType, fnInit.getFlags());
  }
  
  public Class<?> getReturnType(MethodType callSiteType, ScriptObject runtimeScope) {
    return getBest(callSiteType, runtimeScope, CompiledFunction.NO_FUNCTIONS).type().returnType();
  }
  
  synchronized CompiledFunction getBest(MethodType callSiteType, ScriptObject runtimeScope, Collection<CompiledFunction> forbidden, boolean linkLogicOkay) {
    assert isValidCallSite(callSiteType) : callSiteType;
    CompiledFunction existingBest = pickFunction(callSiteType, false);
    if (existingBest == null)
      existingBest = pickFunction(callSiteType, true); 
    if (existingBest == null)
      existingBest = addCode(compileTypeSpecialization(callSiteType, runtimeScope, true), callSiteType); 
    assert existingBest != null;
    if (existingBest.isApplyToCall()) {
      CompiledFunction best = lookupExactApplyToCall(callSiteType);
      if (best != null)
        return best; 
      existingBest = addCode(compileTypeSpecialization(callSiteType, runtimeScope, false), callSiteType);
    } 
    return existingBest;
  }
  
  public boolean needsCallee() {
    return getFunctionFlag(131072);
  }
  
  public int getFunctionFlags() {
    return this.functionFlags;
  }
  
  MethodType getGenericType() {
    if (isVariableArity())
      return MethodType.genericMethodType(2, true); 
    return MethodType.genericMethodType(2 + getArity());
  }
  
  public int getFunctionNodeId() {
    return this.functionNodeId;
  }
  
  public Source getSource() {
    return this.source;
  }
  
  public RecompilableScriptFunctionData getScriptFunctionData(int functionId) {
    if (functionId == this.functionNodeId)
      return this; 
    RecompilableScriptFunctionData data = (this.nestedFunctions == null) ? null : this.nestedFunctions.get(Integer.valueOf(functionId));
    if (data != null)
      return data; 
    for (RecompilableScriptFunctionData ndata : this.nestedFunctions.values()) {
      data = ndata.getScriptFunctionData(functionId);
      if (data != null)
        return data; 
    } 
    return null;
  }
  
  public boolean isGlobalSymbol(FunctionNode functionNode, String symbolName) {
    RecompilableScriptFunctionData data = getScriptFunctionData(functionNode.getId());
    assert data != null;
    do {
      if (data.hasInternalSymbol(symbolName))
        return false; 
      data = data.getParent();
    } while (data != null);
    return true;
  }
  
  public FunctionNode restoreFlags(LexicalContext lc, FunctionNode fn) {
    assert fn.getId() == this.functionNodeId;
    FunctionNode newFn = fn.setFlags(lc, this.functionFlags);
    if (newFn.hasNestedEval()) {
      assert newFn.hasScopeBlock();
      newFn = newFn.setBody(lc, newFn.getBody().setNeedsScope(null));
    } 
    return newFn;
  }
  
  private boolean noDuplicateCode(CompiledFunction compiledFunction) {
    for (CompiledFunction cf : this.code) {
      if (cf.type().equals(compiledFunction.type()))
        return false; 
    } 
    return true;
  }
  
  private void writeObject(ObjectOutputStream out) throws IOException {
    Object localCachedAst = this.cachedAst;
    out.defaultWriteObject();
    if (localCachedAst instanceof SerializedAst) {
      out.writeObject(localCachedAst);
    } else {
      out.writeObject(null);
    } 
  }
  
  private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
    in.defaultReadObject();
    this.cachedAst = in.readObject();
    createLogger();
  }
  
  private void createLogger() {
    this.log = initLogger(Context.getContextTrusted());
  }
}
