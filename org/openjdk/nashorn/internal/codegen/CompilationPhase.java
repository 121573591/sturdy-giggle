package org.openjdk.nashorn.internal.codegen;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import org.openjdk.nashorn.internal.ir.Block;
import org.openjdk.nashorn.internal.ir.FunctionNode;
import org.openjdk.nashorn.internal.ir.LiteralNode;
import org.openjdk.nashorn.internal.ir.Node;
import org.openjdk.nashorn.internal.ir.Symbol;
import org.openjdk.nashorn.internal.ir.debug.ASTWriter;
import org.openjdk.nashorn.internal.ir.debug.PrintVisitor;
import org.openjdk.nashorn.internal.ir.visitor.NodeVisitor;
import org.openjdk.nashorn.internal.ir.visitor.SimpleNodeVisitor;
import org.openjdk.nashorn.internal.runtime.CodeInstaller;
import org.openjdk.nashorn.internal.runtime.RecompilableScriptFunctionData;
import org.openjdk.nashorn.internal.runtime.ScriptEnvironment;
import org.openjdk.nashorn.internal.runtime.logging.DebugLogger;

abstract class CompilationPhase {
  private static final class ConstantFoldingPhase extends CompilationPhase {
    FunctionNode transform(Compiler compiler, Compiler.CompilationPhases phases, FunctionNode fn) {
      return CompilationPhase.transformFunction(fn, (NodeVisitor<?>)new FoldConstants(compiler));
    }
    
    public String toString() {
      return "'Constant Folding'";
    }
  }
  
  static final CompilationPhase CONSTANT_FOLDING_PHASE = new ConstantFoldingPhase();
  
  private static final class LoweringPhase extends CompilationPhase {
    FunctionNode transform(Compiler compiler, Compiler.CompilationPhases phases, FunctionNode fn) {
      return CompilationPhase.transformFunction(fn, (NodeVisitor<?>)new Lower(compiler));
    }
    
    public String toString() {
      return "'Control Flow Lowering'";
    }
  }
  
  static final CompilationPhase LOWERING_PHASE = new LoweringPhase();
  
  private static final class ApplySpecializationPhase extends CompilationPhase {
    FunctionNode transform(Compiler compiler, Compiler.CompilationPhases phases, FunctionNode fn) {
      return CompilationPhase.transformFunction(fn, (NodeVisitor<?>)new ApplySpecialization(compiler));
    }
    
    public String toString() {
      return "'Builtin Replacement'";
    }
  }
  
  static final CompilationPhase APPLY_SPECIALIZATION_PHASE = new ApplySpecializationPhase();
  
  private static final class SplittingPhase extends CompilationPhase {
    FunctionNode transform(Compiler compiler, Compiler.CompilationPhases phases, FunctionNode fn) {
      CompileUnit outermostCompileUnit = compiler.addCompileUnit(0L);
      FunctionNode newFunctionNode = CompilationPhase.transformFunction(fn, (NodeVisitor<?>)new SimpleNodeVisitor() {
            public LiteralNode<?> leaveLiteralNode(LiteralNode<?> literalNode) {
              return literalNode.initialize(this.lc);
            }
          });
      newFunctionNode = (new Splitter(compiler, newFunctionNode, outermostCompileUnit)).split(newFunctionNode, true);
      newFunctionNode = CompilationPhase.transformFunction(newFunctionNode, new SplitIntoFunctions(compiler));
      assert newFunctionNode.getCompileUnit() == outermostCompileUnit : "fn=" + fn.getName() + ", fn.compileUnit (" + newFunctionNode.getCompileUnit() + ") != " + outermostCompileUnit;
      assert newFunctionNode.isStrict() == compiler.isStrict() : "functionNode.isStrict() != compiler.isStrict() for " + DebugLogger.quote(newFunctionNode.getName());
      return newFunctionNode;
    }
    
    public String toString() {
      return "'Code Splitting'";
    }
  }
  
  class null extends SimpleNodeVisitor {
    public LiteralNode<?> leaveLiteralNode(LiteralNode<?> literalNode) {
      return literalNode.initialize(this.lc);
    }
  }
  
  static final CompilationPhase SPLITTING_PHASE = new SplittingPhase();
  
  private static final class ProgramPointPhase extends CompilationPhase {
    FunctionNode transform(Compiler compiler, Compiler.CompilationPhases phases, FunctionNode fn) {
      return CompilationPhase.transformFunction(fn, (NodeVisitor<?>)new ProgramPoints());
    }
    
    public String toString() {
      return "'Program Point Calculation'";
    }
  }
  
  static final CompilationPhase PROGRAM_POINT_PHASE = new ProgramPointPhase();
  
  private static final class CacheAstPhase extends CompilationPhase {
    FunctionNode transform(Compiler compiler, Compiler.CompilationPhases phases, FunctionNode fn) {
      if (!compiler.isOnDemandCompilation())
        CompilationPhase.transformFunction(fn, (NodeVisitor<?>)new CacheAst(compiler)); 
      return fn;
    }
    
    public String toString() {
      return "'Cache ASTs'";
    }
  }
  
  static final CompilationPhase CACHE_AST_PHASE = new CacheAstPhase();
  
  private static final class SymbolAssignmentPhase extends CompilationPhase {
    FunctionNode transform(Compiler compiler, Compiler.CompilationPhases phases, FunctionNode fn) {
      return CompilationPhase.transformFunction(fn, (NodeVisitor<?>)new AssignSymbols(compiler));
    }
    
    public String toString() {
      return "'Symbol Assignment'";
    }
  }
  
  static final CompilationPhase SYMBOL_ASSIGNMENT_PHASE = new SymbolAssignmentPhase();
  
  private static final class ScopeDepthComputationPhase extends CompilationPhase {
    FunctionNode transform(Compiler compiler, Compiler.CompilationPhases phases, FunctionNode fn) {
      return CompilationPhase.transformFunction(fn, (NodeVisitor<?>)new FindScopeDepths(compiler));
    }
    
    public String toString() {
      return "'Scope Depth Computation'";
    }
  }
  
  static final CompilationPhase SCOPE_DEPTH_COMPUTATION_PHASE = new ScopeDepthComputationPhase();
  
  private static final class DeclareLocalSymbolsPhase extends CompilationPhase {
    FunctionNode transform(final Compiler compiler, Compiler.CompilationPhases phases, FunctionNode fn) {
      if (compiler.useOptimisticTypes() && compiler.isOnDemandCompilation())
        fn.getBody().accept((NodeVisitor)new SimpleNodeVisitor() {
              public boolean enterFunctionNode(FunctionNode functionNode) {
                return false;
              }
              
              public boolean enterBlock(Block block) {
                for (Symbol symbol : block.getSymbols()) {
                  if (!symbol.isScope())
                    compiler.declareLocalSymbol(symbol.getName()); 
                } 
                return true;
              }
            }); 
      return fn;
    }
    
    public String toString() {
      return "'Local Symbols Declaration'";
    }
  }
  
  class null extends SimpleNodeVisitor {
    public boolean enterFunctionNode(FunctionNode functionNode) {
      return false;
    }
    
    public boolean enterBlock(Block block) {
      for (Symbol symbol : block.getSymbols()) {
        if (!symbol.isScope())
          compiler.declareLocalSymbol(symbol.getName()); 
      } 
      return true;
    }
  }
  
  static final CompilationPhase DECLARE_LOCAL_SYMBOLS_PHASE = new DeclareLocalSymbolsPhase();
  
  private static final class OptimisticTypeAssignmentPhase extends CompilationPhase {
    FunctionNode transform(Compiler compiler, Compiler.CompilationPhases phases, FunctionNode fn) {
      if (compiler.useOptimisticTypes())
        return CompilationPhase.transformFunction(fn, (NodeVisitor<?>)new OptimisticTypesCalculator(compiler)); 
      return fn;
    }
    
    public String toString() {
      return "'Optimistic Type Assignment'";
    }
  }
  
  static final CompilationPhase OPTIMISTIC_TYPE_ASSIGNMENT_PHASE = new OptimisticTypeAssignmentPhase();
  
  private static final class LocalVariableTypeCalculationPhase extends CompilationPhase {
    FunctionNode transform(Compiler compiler, Compiler.CompilationPhases phases, FunctionNode fn) {
      FunctionNode newFunctionNode = CompilationPhase.transformFunction(fn, (NodeVisitor<?>)new LocalVariableTypesCalculator(compiler, compiler
            .getReturnType()));
      ScriptEnvironment senv = compiler.getScriptEnvironment();
      PrintWriter err = senv.getErr();
      if (senv._print_lower_ast || fn.getDebugFlag(8)) {
        err.println("Lower AST for: " + DebugLogger.quote(newFunctionNode.getName()));
        err.println(new ASTWriter((Node)newFunctionNode));
      } 
      if (senv._print_lower_parse || fn.getDebugFlag(2)) {
        err.println("Lower AST for: " + DebugLogger.quote(newFunctionNode.getName()));
        err.println(new PrintVisitor((Node)newFunctionNode));
      } 
      return newFunctionNode;
    }
    
    public String toString() {
      return "'Local Variable Type Calculation'";
    }
  }
  
  static final CompilationPhase LOCAL_VARIABLE_TYPE_CALCULATION_PHASE = new LocalVariableTypeCalculationPhase();
  
  private static final class ReuseCompileUnitsPhase extends CompilationPhase {
    FunctionNode transform(Compiler compiler, Compiler.CompilationPhases phases, FunctionNode fn) {
      assert phases.isRestOfCompilation() : "reuse compile units currently only used for Rest-Of methods";
      final Map<CompileUnit, CompileUnit> map = new HashMap<>();
      Set<CompileUnit> newUnits = CompileUnit.createCompileUnitSet();
      DebugLogger log = compiler.getLogger();
      log.fine("Clearing bytecode cache");
      compiler.clearBytecode();
      for (CompileUnit oldUnit : compiler.getCompileUnits()) {
        assert map.get(oldUnit) == null;
        CompileUnit newUnit = CompilationPhase.createNewCompileUnit(compiler, phases);
        log.fine(new Object[] { "Creating new compile unit ", oldUnit, " => ", newUnit });
        map.put(oldUnit, newUnit);
        assert newUnit != null;
        newUnits.add(newUnit);
      } 
      log.fine("Replacing compile units in Compiler...");
      compiler.replaceCompileUnits(newUnits);
      log.fine("Done");
      return CompilationPhase.transformFunction(fn, (NodeVisitor<?>)new ReplaceCompileUnits() {
            CompileUnit getReplacement(CompileUnit original) {
              return (CompileUnit)map.get(original);
            }
            
            public Node leaveDefault(Node node) {
              return node.ensureUniqueLabels(this.lc);
            }
          });
    }
    
    public String toString() {
      return "'Reuse Compile Units'";
    }
  }
  
  class null extends ReplaceCompileUnits {
    CompileUnit getReplacement(CompileUnit original) {
      return (CompileUnit)map.get(original);
    }
    
    public Node leaveDefault(Node node) {
      return node.ensureUniqueLabels(this.lc);
    }
  }
  
  static final CompilationPhase REUSE_COMPILE_UNITS_PHASE = new ReuseCompileUnitsPhase();
  
  private static final class ReinitializeCachedPhase extends CompilationPhase {
    FunctionNode transform(final Compiler compiler, final Compiler.CompilationPhases phases, FunctionNode fn) {
      final Set<CompileUnit> unitSet = CompileUnit.createCompileUnitSet();
      final Map<CompileUnit, CompileUnit> unitMap = new HashMap<>();
      createCompileUnit(fn.getCompileUnit(), unitSet, unitMap, compiler, phases);
      FunctionNode newFn = CompilationPhase.transformFunction(fn, (NodeVisitor<?>)new ReplaceCompileUnits() {
            CompileUnit getReplacement(CompileUnit oldUnit) {
              CompileUnit existing = (CompileUnit)unitMap.get(oldUnit);
              return Objects.<CompileUnit>requireNonNullElseGet(existing, () -> CompilationPhase.ReinitializeCachedPhase.this.createCompileUnit(oldUnit, unitSet, unitMap, compiler, phases));
            }
            
            public Node leaveFunctionNode(FunctionNode fn2) {
              return super.leaveFunctionNode(compiler
                  
                  .getScriptFunctionData(fn2.getId()).restoreFlags(this.lc, fn2));
            }
          });
      compiler.replaceCompileUnits(unitSet);
      return newFn;
    }
    
    private CompileUnit createCompileUnit(CompileUnit oldUnit, Set<CompileUnit> unitSet, Map<CompileUnit, CompileUnit> unitMap, Compiler compiler, Compiler.CompilationPhases phases) {
      CompileUnit newUnit = CompilationPhase.createNewCompileUnit(compiler, phases);
      unitMap.put(oldUnit, newUnit);
      unitSet.add(newUnit);
      return newUnit;
    }
    
    public String toString() {
      return "'Reinitialize cached'";
    }
  }
  
  class null extends ReplaceCompileUnits {
    CompileUnit getReplacement(CompileUnit oldUnit) {
      CompileUnit existing = (CompileUnit)unitMap.get(oldUnit);
      return Objects.<CompileUnit>requireNonNullElseGet(existing, () -> CompilationPhase.ReinitializeCachedPhase.this.createCompileUnit(oldUnit, unitSet, unitMap, compiler, phases));
    }
    
    public Node leaveFunctionNode(FunctionNode fn2) {
      return super.leaveFunctionNode(compiler.getScriptFunctionData(fn2.getId()).restoreFlags(this.lc, fn2));
    }
  }
  
  static final CompilationPhase REINITIALIZE_CACHED = new ReinitializeCachedPhase();
  
  private static final class BytecodeGenerationPhase extends CompilationPhase {
    FunctionNode transform(Compiler compiler, Compiler.CompilationPhases phases, FunctionNode fn) {
      ScriptEnvironment senv = compiler.getScriptEnvironment();
      FunctionNode newFunctionNode = fn;
      fn.getCompileUnit().setUsed();
      compiler.getLogger().fine(new Object[] { "Starting bytecode generation for ", DebugLogger.quote(fn.getName()), " - restOf=", Boolean.valueOf(phases.isRestOfCompilation()) });
      CodeGenerator codegen = new CodeGenerator(compiler, phases.isRestOfCompilation() ? compiler.getContinuationEntryPoints() : null);
      try {
        newFunctionNode = CompilationPhase.transformFunction(newFunctionNode, (NodeVisitor<?>)codegen);
        codegen.generateScopeCalls();
      } catch (VerifyError e) {
        if (senv._verify_code || senv._print_code) {
          senv.getErr().println(e.getClass().getSimpleName() + ": " + e.getClass().getSimpleName());
          if (senv._dump_on_error)
            e.printStackTrace(senv.getErr()); 
        } else {
          throw e;
        } 
      } catch (Throwable e) {
        throw new AssertionError("Failed generating bytecode for " + fn.getSourceName() + ":" + codegen.getLastLineNumber(), e);
      } 
      for (CompileUnit compileUnit : compiler.getCompileUnits()) {
        ClassEmitter classEmitter = compileUnit.getClassEmitter();
        classEmitter.end();
        if (!compileUnit.isUsed()) {
          compiler.getLogger().fine(new Object[] { "Skipping unused compile unit ", compileUnit });
          continue;
        } 
        byte[] bytecode = classEmitter.toByteArray();
        assert bytecode != null;
        String className = compileUnit.getUnitClassName();
        compiler.addClass(className, bytecode);
        CompileUnit.increaseEmitCount();
        if (senv._verify_code)
          compiler.getCodeInstaller().verify(bytecode); 
        DumpBytecode.dumpBytecode(senv, compiler.getLogger(), bytecode, className);
      } 
      return newFunctionNode;
    }
    
    public String toString() {
      return "'Bytecode Generation'";
    }
  }
  
  static final CompilationPhase BYTECODE_GENERATION_PHASE = new BytecodeGenerationPhase();
  
  private static final class InstallPhase extends CompilationPhase {
    FunctionNode transform(Compiler compiler, Compiler.CompilationPhases phases, FunctionNode fn) {
      DebugLogger log = compiler.getLogger();
      Map<String, Class<?>> installedClasses = new LinkedHashMap<>();
      boolean first = true;
      Class<?> rootClass = null;
      long length = 0L;
      CodeInstaller origCodeInstaller = compiler.getCodeInstaller();
      Map<String, byte[]> bytecode = compiler.getBytecode();
      CodeInstaller codeInstaller = (bytecode.size() > 1) ? origCodeInstaller.getMultiClassCodeInstaller() : origCodeInstaller;
      for (Map.Entry<String, byte[]> entry : bytecode.entrySet()) {
        String className = entry.getKey();
        byte[] code = entry.getValue();
        length += code.length;
        Class<?> clazz = codeInstaller.install(className, code);
        if (first) {
          rootClass = clazz;
          first = false;
        } 
        installedClasses.put(className, clazz);
      } 
      if (rootClass == null)
        throw new CompilationException("Internal compiler error: root class not found!"); 
      Object[] constants = compiler.getConstantData().toArray();
      codeInstaller.initialize(installedClasses.values(), compiler.getSource(), constants);
      for (Object constant : constants) {
        if (constant instanceof RecompilableScriptFunctionData)
          ((RecompilableScriptFunctionData)constant).initTransients(compiler.getSource(), codeInstaller); 
      } 
      for (CompileUnit unit : compiler.getCompileUnits()) {
        if (!unit.isUsed())
          continue; 
        unit.setCode(installedClasses.get(unit.getUnitClassName()));
        unit.initializeFunctionsCode();
      } 
      if (log.isEnabled())
        log.fine("Installed class '" + rootClass
            .getSimpleName() + "' [" + rootClass
            .getName() + ", size=" + length + " bytes, " + compiler.getCompileUnits()
            .size() + " compile unit(s)]"); 
      return fn.setRootClass(null, rootClass);
    }
    
    public String toString() {
      return "'Class Installation'";
    }
  }
  
  static final CompilationPhase INSTALL_PHASE = new InstallPhase();
  
  private long startTime;
  
  private long endTime;
  
  protected FunctionNode begin(Compiler compiler, FunctionNode functionNode) {
    compiler.getLogger().indent();
    this.startTime = System.nanoTime();
    return functionNode;
  }
  
  protected FunctionNode end(Compiler compiler, FunctionNode functionNode) {
    compiler.getLogger().unindent();
    this.endTime = System.nanoTime();
    (compiler.getScriptEnvironment())._timing.accumulateTime(toString(), this.endTime - this.startTime);
    return functionNode;
  }
  
  long getStartTime() {
    return this.startTime;
  }
  
  long getEndTime() {
    return this.endTime;
  }
  
  abstract FunctionNode transform(Compiler paramCompiler, Compiler.CompilationPhases paramCompilationPhases, FunctionNode paramFunctionNode) throws CompilationException;
  
  final FunctionNode apply(Compiler compiler, Compiler.CompilationPhases phases, FunctionNode functionNode) throws CompilationException {
    assert phases.contains(this);
    return end(compiler, transform(compiler, phases, begin(compiler, functionNode)));
  }
  
  private static FunctionNode transformFunction(FunctionNode fn, NodeVisitor<?> visitor) {
    return (FunctionNode)fn.accept(visitor);
  }
  
  private static CompileUnit createNewCompileUnit(Compiler compiler, Compiler.CompilationPhases phases) {
    StringBuilder sb = new StringBuilder(compiler.nextCompileUnitName());
    if (phases.isRestOfCompilation())
      sb.append("$restOf"); 
    return compiler.createCompileUnit(sb.toString(), 0L);
  }
}
