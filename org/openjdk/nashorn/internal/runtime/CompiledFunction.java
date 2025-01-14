package org.openjdk.nashorn.internal.runtime;

import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.lang.invoke.SwitchPoint;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Supplier;
import java.util.logging.Level;
import jdk.dynalink.linker.GuardedInvocation;
import org.openjdk.nashorn.internal.codegen.Compiler;
import org.openjdk.nashorn.internal.codegen.TypeMap;
import org.openjdk.nashorn.internal.codegen.types.ArrayType;
import org.openjdk.nashorn.internal.codegen.types.Type;
import org.openjdk.nashorn.internal.ir.FunctionNode;
import org.openjdk.nashorn.internal.lookup.Lookup;
import org.openjdk.nashorn.internal.objects.annotations.SpecializedFunction;
import org.openjdk.nashorn.internal.runtime.events.RecompilationEvent;
import org.openjdk.nashorn.internal.runtime.events.RuntimeEvent;
import org.openjdk.nashorn.internal.runtime.linker.Bootstrap;
import org.openjdk.nashorn.internal.runtime.logging.DebugLogger;

final class CompiledFunction {
  private static final MethodHandle NEWFILTER = findOwnMH("newFilter", Object.class, new Class[] { Object.class, Object.class });
  
  private static final MethodHandle RELINK_COMPOSABLE_INVOKER = findOwnMH("relinkComposableInvoker", void.class, new Class[] { CallSite.class, CompiledFunction.class, boolean.class });
  
  private static final MethodHandle HANDLE_REWRITE_EXCEPTION = findOwnMH("handleRewriteException", MethodHandle.class, new Class[] { CompiledFunction.class, OptimismInfo.class, RewriteException.class });
  
  private static final MethodHandle RESTOF_INVOKER = MethodHandles.exactInvoker(MethodType.methodType(Object.class, RewriteException.class));
  
  private final DebugLogger log;
  
  static final Collection<CompiledFunction> NO_FUNCTIONS = Collections.emptySet();
  
  private MethodHandle invoker;
  
  private MethodHandle constructor;
  
  private OptimismInfo optimismInfo;
  
  private final int flags;
  
  private final MethodType callSiteType;
  
  private final Specialization specialization;
  
  CompiledFunction(MethodHandle invoker) {
    this(invoker, null, null);
  }
  
  CompiledFunction(MethodHandle invoker, MethodHandle constructor, Specialization specialization) {
    this(invoker, constructor, 0, null, specialization, DebugLogger.DISABLED_LOGGER);
  }
  
  CompiledFunction(MethodHandle invoker, MethodHandle constructor, int flags, MethodType callSiteType, Specialization specialization, DebugLogger log) {
    this.specialization = specialization;
    if (specialization != null && specialization.isOptimistic()) {
      this.invoker = Lookup.MH.insertArguments(invoker, invoker.type().parameterCount() - 1, new Object[] { Integer.valueOf(1) });
      throw new AssertionError("Optimistic (UnwarrantedOptimismException throwing) builtin functions are currently not in use");
    } 
    this.invoker = invoker;
    this.constructor = constructor;
    this.flags = flags;
    this.callSiteType = callSiteType;
    this.log = log;
  }
  
  CompiledFunction(MethodHandle invoker, RecompilableScriptFunctionData functionData, Map<Integer, Type> invalidatedProgramPoints, MethodType callSiteType, int flags) {
    this(invoker, null, flags, callSiteType, null, functionData.getLogger());
    if ((flags & 0x800) != 0) {
      this.optimismInfo = new OptimismInfo(functionData, invalidatedProgramPoints);
    } else {
      this.optimismInfo = null;
    } 
  }
  
  static CompiledFunction createBuiltInConstructor(MethodHandle invoker) {
    return new CompiledFunction(Lookup.MH.insertArguments(invoker, 0, new Object[] { Boolean.valueOf(false) }), createConstructorFromInvoker(Lookup.MH.insertArguments(invoker, 0, new Object[] { Boolean.valueOf(true) })), null);
  }
  
  boolean isSpecialization() {
    return (this.specialization != null);
  }
  
  boolean hasLinkLogic() {
    return (getLinkLogicClass() != null);
  }
  
  Class<? extends SpecializedFunction.LinkLogic> getLinkLogicClass() {
    if (isSpecialization()) {
      Class<? extends SpecializedFunction.LinkLogic> linkLogicClass = this.specialization.getLinkLogicClass();
      assert !SpecializedFunction.LinkLogic.isEmpty(linkLogicClass) : "empty link logic classes should have been removed by nasgen";
      return linkLogicClass;
    } 
    return null;
  }
  
  boolean convertsNumericArgs() {
    return (isSpecialization() && this.specialization.convertsNumericArgs());
  }
  
  int getFlags() {
    return this.flags;
  }
  
  boolean isApplyToCall() {
    return ((this.flags & 0x1000) != 0);
  }
  
  public String toString() {
    StringBuilder sb = new StringBuilder();
    Class<? extends SpecializedFunction.LinkLogic> linkLogicClass = getLinkLogicClass();
    sb.append("[invokerType=")
      .append(this.invoker.type())
      .append(" ctor=")
      .append(this.constructor)
      .append(" weight=")
      .append(weight())
      .append(" linkLogic=")
      .append((linkLogicClass != null) ? linkLogicClass.getSimpleName() : "none");
    return sb.toString();
  }
  
  boolean needsCallee() {
    return ScriptFunctionData.needsCallee(this.invoker);
  }
  
  MethodHandle createComposableInvoker() {
    return createComposableInvoker(false);
  }
  
  private MethodHandle getConstructor() {
    if (this.constructor == null)
      this.constructor = createConstructorFromInvoker(createInvokerForPessimisticCaller()); 
    return this.constructor;
  }
  
  private MethodHandle createInvokerForPessimisticCaller() {
    return createInvoker(Object.class, -1);
  }
  
  private static MethodHandle createConstructorFromInvoker(MethodHandle invoker) {
    boolean needsCallee = ScriptFunctionData.needsCallee(invoker);
    MethodHandle swapped = needsCallee ? swapCalleeAndThis(invoker) : invoker;
    MethodHandle returnsObject = Lookup.MH.asType(swapped, swapped.type().changeReturnType(Object.class));
    MethodType ctorType = returnsObject.type();
    Class<?>[] ctorArgs = ctorType.dropParameterTypes(0, 1).parameterArray();
    MethodHandle filtered = Lookup.MH.foldArguments(Lookup.MH.dropArguments(NEWFILTER, 2, ctorArgs), returnsObject);
    if (needsCallee)
      return Lookup.MH.foldArguments(filtered, ScriptFunction.ALLOCATE); 
    return Lookup.MH.filterArguments(filtered, 0, new MethodHandle[] { ScriptFunction.ALLOCATE });
  }
  
  private static MethodHandle swapCalleeAndThis(MethodHandle mh) {
    MethodType type = mh.type();
    assert type.parameterType(0) == ScriptFunction.class : type;
    assert type.parameterType(1) == Object.class : type;
    MethodType newType = type.changeParameterType(0, Object.class).changeParameterType(1, ScriptFunction.class);
    int[] reorder = new int[type.parameterCount()];
    reorder[0] = 1;
    assert reorder[1] == 0;
    for (int i = 2; i < reorder.length; i++)
      reorder[i] = i; 
    return MethodHandles.permuteArguments(mh, newType, reorder);
  }
  
  MethodHandle createComposableConstructor() {
    return createComposableInvoker(true);
  }
  
  MethodType type() {
    return this.invoker.type();
  }
  
  int weight() {
    return weight(type());
  }
  
  private static int weight(MethodType type) {
    if (isVarArgsType(type))
      return Integer.MAX_VALUE; 
    int weight = Type.typeFor(type.returnType()).getWeight();
    for (int i = 0; i < type.parameterCount(); i++) {
      Class<?> paramType = type.parameterType(i);
      int pweight = Type.typeFor(paramType).getWeight() * 2;
      weight += pweight;
    } 
    weight += type.parameterCount();
    return weight;
  }
  
  static boolean isVarArgsType(MethodType type) {
    assert type.parameterCount() >= 1 : type;
    return (type.parameterType(type.parameterCount() - 1) == Object[].class);
  }
  
  boolean betterThanFinal(CompiledFunction other, MethodType callSiteMethodType) {
    if (other == null)
      return true; 
    return betterThanFinal(this, other, callSiteMethodType);
  }
  
  private static boolean betterThanFinal(CompiledFunction cf, CompiledFunction other, MethodType callSiteMethodType) {
    MethodType thisMethodType = cf.type();
    MethodType otherMethodType = other.type();
    int thisParamCount = getParamCount(thisMethodType);
    int otherParamCount = getParamCount(otherMethodType);
    int callSiteRawParamCount = getParamCount(callSiteMethodType);
    boolean csVarArg = (callSiteRawParamCount == Integer.MAX_VALUE);
    int callSiteParamCount = csVarArg ? callSiteRawParamCount : (callSiteRawParamCount - 1);
    int thisDiscardsParams = Math.max(callSiteParamCount - thisParamCount, 0);
    int otherDiscardsParams = Math.max(callSiteParamCount - otherParamCount, 0);
    if (thisDiscardsParams < otherDiscardsParams)
      return true; 
    if (thisDiscardsParams > otherDiscardsParams)
      return false; 
    boolean thisVarArg = (thisParamCount == Integer.MAX_VALUE);
    boolean otherVarArg = (otherParamCount == Integer.MAX_VALUE);
    if (!thisVarArg || !otherVarArg || !csVarArg) {
      Type[] thisType = toTypeWithoutCallee(thisMethodType, 0);
      Type[] otherType = toTypeWithoutCallee(otherMethodType, 0);
      Type[] callSiteType = toTypeWithoutCallee(callSiteMethodType, 1);
      int narrowWeightDelta = 0;
      int widenWeightDelta = 0;
      int minParamsCount = Math.min(Math.min(thisParamCount, otherParamCount), callSiteParamCount);
      boolean convertsNumericArgs = cf.convertsNumericArgs();
      int i;
      for (i = 0; i < minParamsCount; i++) {
        Type callSiteParamType = getParamType(i, callSiteType, csVarArg);
        Type thisParamType = getParamType(i, thisType, thisVarArg);
        if (!convertsNumericArgs && callSiteParamType.isBoolean() && thisParamType.isNumeric())
          return false; 
        int callSiteParamWeight = callSiteParamType.getWeight();
        int thisParamWeightDelta = thisParamType.getWeight() - callSiteParamWeight;
        int otherParamWeightDelta = getParamType(i, otherType, otherVarArg).getWeight() - callSiteParamWeight;
        narrowWeightDelta += Math.max(-thisParamWeightDelta, 0) - Math.max(-otherParamWeightDelta, 0);
        widenWeightDelta += Math.max(thisParamWeightDelta, 0) - Math.max(otherParamWeightDelta, 0);
      } 
      if (!thisVarArg)
        for (i = callSiteParamCount; i < thisParamCount; i++)
          narrowWeightDelta += Math.max(Type.OBJECT.getWeight() - thisType[i].getWeight(), 0);  
      if (!otherVarArg)
        for (i = callSiteParamCount; i < otherParamCount; i++)
          narrowWeightDelta -= Math.max(Type.OBJECT.getWeight() - otherType[i].getWeight(), 0);  
      if (narrowWeightDelta < 0)
        return true; 
      if (narrowWeightDelta > 0)
        return false; 
      if (widenWeightDelta < 0)
        return true; 
      if (widenWeightDelta > 0)
        return false; 
    } 
    if (thisParamCount == callSiteParamCount && otherParamCount != callSiteParamCount)
      return true; 
    if (thisParamCount != callSiteParamCount && otherParamCount == callSiteParamCount)
      return false; 
    if (thisVarArg) {
      if (!otherVarArg)
        return true; 
    } else if (otherVarArg) {
      return false;
    } 
    int fnParamDelta = thisParamCount - otherParamCount;
    if (fnParamDelta < 0)
      return true; 
    if (fnParamDelta > 0)
      return false; 
    int callSiteRetWeight = Type.typeFor(callSiteMethodType.returnType()).getWeight();
    int thisRetWeightDelta = Type.typeFor(thisMethodType.returnType()).getWeight() - callSiteRetWeight;
    int otherRetWeightDelta = Type.typeFor(otherMethodType.returnType()).getWeight() - callSiteRetWeight;
    int widenRetDelta = Math.max(thisRetWeightDelta, 0) - Math.max(otherRetWeightDelta, 0);
    if (widenRetDelta < 0)
      return true; 
    if (widenRetDelta > 0)
      return false; 
    int narrowRetDelta = Math.max(-thisRetWeightDelta, 0) - Math.max(-otherRetWeightDelta, 0);
    if (narrowRetDelta < 0)
      return true; 
    if (narrowRetDelta > 0)
      return false; 
    if (cf.isSpecialization() != other.isSpecialization())
      return cf.isSpecialization(); 
    if (cf.isSpecialization())
      return (cf.getLinkLogicClass() != null); 
    throw new AssertionError("" + thisMethodType + " identically applicable to " + thisMethodType + " for " + otherMethodType);
  }
  
  private static Type[] toTypeWithoutCallee(MethodType type, int thisIndex) {
    int paramCount = type.parameterCount();
    Type[] t = new Type[paramCount - thisIndex];
    for (int i = thisIndex; i < paramCount; i++)
      t[i - thisIndex] = Type.typeFor(type.parameterType(i)); 
    return t;
  }
  
  private static Type getParamType(int i, Type[] paramTypes, boolean isVarArg) {
    int fixParamCount = paramTypes.length - (isVarArg ? 1 : 0);
    if (i < fixParamCount)
      return paramTypes[i]; 
    assert isVarArg;
    return ((ArrayType)paramTypes[paramTypes.length - 1]).getElementType();
  }
  
  boolean matchesCallSite(MethodType other, boolean pickVarArg) {
    if (other.equals(this.callSiteType))
      return true; 
    MethodType type = type();
    int fnParamCount = getParamCount(type);
    boolean isVarArg = (fnParamCount == Integer.MAX_VALUE);
    if (isVarArg)
      return pickVarArg; 
    int csParamCount = getParamCount(other);
    boolean csIsVarArg = (csParamCount == Integer.MAX_VALUE);
    if (csIsVarArg && isApplyToCall())
      return false; 
    int thisThisIndex = needsCallee() ? 1 : 0;
    int fnParamCountNoCallee = fnParamCount - thisThisIndex;
    int minParams = Math.min(csParamCount - 1, fnParamCountNoCallee);
    int i;
    for (i = 0; i < minParams; i++) {
      Type fnType = Type.typeFor(type.parameterType(i + thisThisIndex));
      Type csType = csIsVarArg ? Type.OBJECT : Type.typeFor(other.parameterType(i + 1));
      if (!fnType.isEquivalentTo(csType))
        return false; 
    } 
    for (i = minParams; i < fnParamCountNoCallee; i++) {
      if (!Type.typeFor(type.parameterType(i + thisThisIndex)).isEquivalentTo(Type.OBJECT))
        return false; 
    } 
    return true;
  }
  
  private static int getParamCount(MethodType type) {
    int paramCount = type.parameterCount();
    return type.parameterType(paramCount - 1).isArray() ? Integer.MAX_VALUE : paramCount;
  }
  
  private boolean canBeDeoptimized() {
    return (this.optimismInfo != null);
  }
  
  private MethodHandle createComposableInvoker(boolean isConstructor) {
    MethodHandle handle = getInvokerOrConstructor(isConstructor);
    if (!canBeDeoptimized())
      return handle; 
    CallSite cs = new MutableCallSite(handle.type());
    relinkComposableInvoker(cs, this, isConstructor);
    return cs.dynamicInvoker();
  }
  
  private static class HandleAndAssumptions {
    final MethodHandle handle;
    
    final SwitchPoint assumptions;
    
    HandleAndAssumptions(MethodHandle handle, SwitchPoint assumptions) {
      this.handle = handle;
      this.assumptions = assumptions;
    }
    
    GuardedInvocation createInvocation() {
      return new GuardedInvocation(this.handle, this.assumptions);
    }
  }
  
  private synchronized HandleAndAssumptions getValidOptimisticInvocation(Supplier<MethodHandle> invocationSupplier) {
    MethodHandle handle;
    SwitchPoint assumptions;
    while (true) {
      handle = invocationSupplier.get();
      assumptions = canBeDeoptimized() ? this.optimismInfo.optimisticAssumptions : null;
      if (assumptions != null && assumptions.hasBeenInvalidated()) {
        try {
          wait();
        } catch (InterruptedException interruptedException) {}
        continue;
      } 
      break;
    } 
    return new HandleAndAssumptions(handle, assumptions);
  }
  
  private static void relinkComposableInvoker(CallSite cs, CompiledFunction inv, boolean constructor) {
    MethodHandle target;
    HandleAndAssumptions handleAndAssumptions = inv.getValidOptimisticInvocation(() -> inv.getInvokerOrConstructor(constructor));
    MethodHandle handle = handleAndAssumptions.handle;
    SwitchPoint assumptions = handleAndAssumptions.assumptions;
    if (assumptions == null) {
      target = handle;
    } else {
      MethodHandle relink = MethodHandles.insertArguments(RELINK_COMPOSABLE_INVOKER, 0, new Object[] { cs, inv, Boolean.valueOf(constructor) });
      target = assumptions.guardWithTest(handle, MethodHandles.foldArguments(cs.dynamicInvoker(), relink));
    } 
    cs.setTarget(target.asType(cs.type()));
  }
  
  private MethodHandle getInvokerOrConstructor(boolean selectCtor) {
    return selectCtor ? getConstructor() : createInvokerForPessimisticCaller();
  }
  
  GuardedInvocation createFunctionInvocation(Class<?> callSiteReturnType, int callerProgramPoint) {
    return getValidOptimisticInvocation(() -> createInvoker(callSiteReturnType, callerProgramPoint)).createInvocation();
  }
  
  GuardedInvocation createConstructorInvocation() {
    return getValidOptimisticInvocation(this::getConstructor).createInvocation();
  }
  
  private MethodHandle createInvoker(Class<?> callSiteReturnType, int callerProgramPoint) {
    boolean isOptimistic = canBeDeoptimized();
    MethodHandle handleRewriteException = isOptimistic ? createRewriteExceptionHandler() : null;
    MethodHandle inv = this.invoker;
    if (UnwarrantedOptimismException.isValid(callerProgramPoint)) {
      inv = OptimisticReturnFilters.filterOptimisticReturnValue(inv, callSiteReturnType, callerProgramPoint);
      inv = changeReturnType(inv, callSiteReturnType);
      if (callSiteReturnType.isPrimitive() && handleRewriteException != null)
        handleRewriteException = OptimisticReturnFilters.filterOptimisticReturnValue(handleRewriteException, callSiteReturnType, callerProgramPoint); 
    } else if (isOptimistic) {
      inv = changeReturnType(inv, callSiteReturnType);
    } 
    if (isOptimistic) {
      assert handleRewriteException != null;
      MethodHandle typedHandleRewriteException = changeReturnType(handleRewriteException, inv.type().returnType());
      return Lookup.MH.catchException(inv, RewriteException.class, typedHandleRewriteException);
    } 
    return inv;
  }
  
  private MethodHandle createRewriteExceptionHandler() {
    return Lookup.MH.foldArguments(RESTOF_INVOKER, Lookup.MH.insertArguments(HANDLE_REWRITE_EXCEPTION, 0, new Object[] { this, this.optimismInfo }));
  }
  
  private static MethodHandle changeReturnType(MethodHandle mh, Class<?> newReturnType) {
    return Bootstrap.getLinkerServices().asType(mh, mh.type().changeReturnType(newReturnType));
  }
  
  private static MethodHandle handleRewriteException(CompiledFunction function, OptimismInfo oldOptimismInfo, RewriteException re) {
    return function.handleRewriteException(oldOptimismInfo, re);
  }
  
  private static List<String> toStringInvalidations(Map<Integer, Type> ipp) {
    if (ipp == null)
      return Collections.emptyList(); 
    List<String> list = new ArrayList<>();
    for (Map.Entry<Integer, Type> entry : ipp.entrySet()) {
      String type;
      char bct = ((Type)entry.getValue()).getBytecodeStackType();
      switch (((Type)entry.getValue()).getBytecodeStackType()) {
        case 'A':
          type = "object";
          break;
        case 'I':
          type = "int";
          break;
        case 'J':
          type = "long";
          break;
        case 'D':
          type = "double";
          break;
        default:
          type = String.valueOf(bct);
          break;
      } 
      list.add("[program point: " + entry.getKey() + " -> " + type + "]");
    } 
    return list;
  }
  
  private void logRecompile(String reason, FunctionNode fn, MethodType type, Map<Integer, Type> ipp) {
    if (this.log.isEnabled()) {
      this.log.info(new Object[] { reason, DebugLogger.quote(fn.getName()), " signature: ", type });
      this.log.indent();
      for (String str : toStringInvalidations(ipp))
        this.log.fine(str); 
      this.log.unindent();
    } 
  }
  
  private synchronized MethodHandle handleRewriteException(OptimismInfo oldOptInfo, RewriteException re) {
    if (this.log.isEnabled()) {
      this.log.info((RuntimeEvent)new RecompilationEvent(Level.INFO, re, re
            
            .getReturnValueNonDestructive()), new Object[] { "caught RewriteException ", re
            
            .getMessageShort() });
      this.log.indent();
    } 
    MethodType type = type();
    MethodType ct = (type.parameterType(0) == ScriptFunction.class) ? type : type.insertParameterTypes(0, new Class[] { ScriptFunction.class });
    OptimismInfo currentOptInfo = this.optimismInfo;
    boolean shouldRecompile = (currentOptInfo != null && currentOptInfo.requestRecompile(re));
    OptimismInfo effectiveOptInfo = (currentOptInfo != null) ? currentOptInfo : oldOptInfo;
    FunctionNode fn = effectiveOptInfo.reparse();
    boolean cached = fn.isCached();
    Compiler compiler = effectiveOptInfo.getCompiler(fn, ct, re);
    if (!shouldRecompile) {
      logRecompile("Rest-of compilation [STANDALONE] ", fn, ct, effectiveOptInfo.invalidatedProgramPoints);
      return restOfHandle(effectiveOptInfo, compiler.compile(fn, cached ? Compiler.CompilationPhases.COMPILE_CACHED_RESTOF : Compiler.CompilationPhases.COMPILE_ALL_RESTOF), (currentOptInfo != null));
    } 
    logRecompile("Deoptimizing recompilation (up to bytecode) ", fn, ct, effectiveOptInfo.invalidatedProgramPoints);
    fn = compiler.compile(fn, cached ? Compiler.CompilationPhases.RECOMPILE_CACHED_UPTO_BYTECODE : Compiler.CompilationPhases.COMPILE_UPTO_BYTECODE);
    this.log.fine("Reusable IR generated");
    this.log.info("Generating and installing bytecode from reusable IR...");
    logRecompile("Rest-of compilation [CODE PIPELINE REUSE] ", fn, ct, effectiveOptInfo.invalidatedProgramPoints);
    FunctionNode normalFn = compiler.compile(fn, Compiler.CompilationPhases.GENERATE_BYTECODE_AND_INSTALL);
    if (effectiveOptInfo.data.usePersistentCodeCache()) {
      RecompilableScriptFunctionData data = effectiveOptInfo.data;
      int functionNodeId = data.getFunctionNodeId();
      TypeMap typeMap = data.typeMap(ct);
      Type[] paramTypes = (typeMap == null) ? null : typeMap.getParameterTypes(functionNodeId);
      String cacheKey = CodeStore.getCacheKey(Integer.valueOf(functionNodeId), paramTypes);
      compiler.persistClassInfo(cacheKey, normalFn);
    } 
    boolean canBeDeoptimized = normalFn.canBeDeoptimized();
    if (this.log.isEnabled()) {
      this.log.unindent();
      this.log.info("Done.");
      this.log.info(new Object[] { "Recompiled '", fn.getName(), "' (", Debug.id(this), ") ", canBeDeoptimized ? "can still be deoptimized." : " is completely deoptimized." });
      this.log.finest("Looking up invoker...");
    } 
    MethodHandle newInvoker = effectiveOptInfo.data.lookup(fn);
    this.invoker = newInvoker.asType(type.changeReturnType(newInvoker.type().returnType()));
    this.constructor = null;
    this.log.info(new Object[] { "Done: ", this.invoker });
    MethodHandle restOf = restOfHandle(effectiveOptInfo, compiler.compile(fn, Compiler.CompilationPhases.GENERATE_BYTECODE_AND_INSTALL_RESTOF), canBeDeoptimized);
    if (canBeDeoptimized) {
      effectiveOptInfo.newOptimisticAssumptions();
    } else {
      this.optimismInfo = null;
    } 
    notifyAll();
    return restOf;
  }
  
  private MethodHandle restOfHandle(OptimismInfo info, FunctionNode restOfFunction, boolean canBeDeoptimized) {
    assert info != null;
    assert restOfFunction.getCompileUnit().getUnitClassName().contains("restOf");
    MethodHandle restOf = changeReturnType(info.data
        .lookupCodeMethod(restOfFunction
          .getCompileUnit().getCode(), Lookup.MH
          .type(restOfFunction.getReturnType().getTypeClass(), new Class[] { RewriteException.class })), Object.class);
    if (!canBeDeoptimized)
      return restOf; 
    return Lookup.MH.catchException(restOf, RewriteException.class, createRewriteExceptionHandler());
  }
  
  private static class OptimismInfo {
    private final RecompilableScriptFunctionData data;
    
    private final Map<Integer, Type> invalidatedProgramPoints;
    
    private SwitchPoint optimisticAssumptions;
    
    private final DebugLogger log;
    
    OptimismInfo(RecompilableScriptFunctionData data, Map<Integer, Type> invalidatedProgramPoints) {
      this.data = data;
      this.log = data.getLogger();
      this.invalidatedProgramPoints = (invalidatedProgramPoints == null) ? new TreeMap<>() : invalidatedProgramPoints;
      newOptimisticAssumptions();
    }
    
    private void newOptimisticAssumptions() {
      this.optimisticAssumptions = new SwitchPoint();
    }
    
    boolean requestRecompile(RewriteException e) {
      Type retType = e.getReturnType();
      Type previousFailedType = this.invalidatedProgramPoints.put(Integer.valueOf(e.getProgramPoint()), retType);
      if (previousFailedType != null && !previousFailedType.narrowerThan(retType)) {
        StackTraceElement[] stack = e.getStackTrace();
        String functionId = (stack.length == 0) ? this.data.getName() : (stack[0].getClassName() + "." + stack[0].getClassName());
        this.log.info(new Object[] { "RewriteException for an already invalidated program point ", Integer.valueOf(e.getProgramPoint()), " in ", functionId, ". This is okay for a recursive function invocation, but a bug otherwise." });
        return false;
      } 
      SwitchPoint.invalidateAll(new SwitchPoint[] { this.optimisticAssumptions });
      return true;
    }
    
    Compiler getCompiler(FunctionNode fn, MethodType actualCallSiteType, RewriteException e) {
      return this.data.getCompiler(fn, actualCallSiteType, e.getRuntimeScope(), this.invalidatedProgramPoints, getEntryPoints(e));
    }
    
    private static int[] getEntryPoints(RewriteException e) {
      int[] entryPoints, prevEntryPoints = e.getPreviousContinuationEntryPoints();
      if (prevEntryPoints == null) {
        entryPoints = new int[1];
      } else {
        int l = prevEntryPoints.length;
        entryPoints = new int[l + 1];
        System.arraycopy(prevEntryPoints, 0, entryPoints, 1, l);
      } 
      entryPoints[0] = e.getProgramPoint();
      return entryPoints;
    }
    
    FunctionNode reparse() {
      return this.data.reparse();
    }
  }
  
  private static Object newFilter(Object result, Object allocation) {
    return (result instanceof ScriptObject || !JSType.isPrimitive(result)) ? result : allocation;
  }
  
  private static MethodHandle findOwnMH(String name, Class<?> rtype, Class<?>... types) {
    return Lookup.MH.findStatic(MethodHandles.lookup(), CompiledFunction.class, name, Lookup.MH.type(rtype, types));
  }
}
