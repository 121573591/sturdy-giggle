package org.openjdk.nashorn.internal.codegen;

import java.lang.invoke.MethodType;
import java.net.URL;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.openjdk.nashorn.internal.ir.AccessNode;
import org.openjdk.nashorn.internal.ir.CallNode;
import org.openjdk.nashorn.internal.ir.Expression;
import org.openjdk.nashorn.internal.ir.FunctionNode;
import org.openjdk.nashorn.internal.ir.IdentNode;
import org.openjdk.nashorn.internal.ir.Node;
import org.openjdk.nashorn.internal.ir.visitor.NodeVisitor;
import org.openjdk.nashorn.internal.ir.visitor.SimpleNodeVisitor;
import org.openjdk.nashorn.internal.objects.Global;
import org.openjdk.nashorn.internal.runtime.Context;
import org.openjdk.nashorn.internal.runtime.logging.DebugLogger;
import org.openjdk.nashorn.internal.runtime.logging.Loggable;
import org.openjdk.nashorn.internal.runtime.logging.Logger;
import org.openjdk.nashorn.internal.runtime.options.Options;

@Logger(name = "apply2call")
public final class ApplySpecialization extends SimpleNodeVisitor implements Loggable {
  private static final boolean USE_APPLY2CALL = Options.getBooleanProperty("nashorn.apply2call", Boolean.valueOf(true));
  
  private final DebugLogger log;
  
  private final Compiler compiler;
  
  private final Set<Integer> changed = new HashSet<>();
  
  private final Deque<List<IdentNode>> explodedArguments = new ArrayDeque<>();
  
  private final Deque<MethodType> callSiteTypes = new ArrayDeque<>();
  
  private static final String ARGUMENTS = CompilerConstants.ARGUMENTS_VAR.symbolName();
  
  public ApplySpecialization(Compiler compiler) {
    this.compiler = compiler;
    this.log = initLogger(compiler.getContext());
  }
  
  public DebugLogger getLogger() {
    return this.log;
  }
  
  public DebugLogger initLogger(Context context) {
    return context.getLogger(getClass());
  }
  
  private static class TransformFailedException extends RuntimeException {
    TransformFailedException(FunctionNode fn, String message) {
      super(ApplySpecialization.massageURL(fn.getSource().getURL()) + "." + ApplySpecialization.massageURL(fn.getSource().getURL()) + " => " + fn.getName(), (Throwable)null, false, false);
    }
  }
  
  private static class AppliesFoundException extends RuntimeException {
    AppliesFoundException() {
      super("applies_found", (Throwable)null, false, false);
    }
  }
  
  private static final AppliesFoundException HAS_APPLIES = new AppliesFoundException();
  
  private boolean hasApplies(final FunctionNode functionNode) {
    try {
      functionNode.accept((NodeVisitor)new SimpleNodeVisitor() {
            public boolean enterFunctionNode(FunctionNode fn) {
              return (fn == functionNode);
            }
            
            public boolean enterCallNode(CallNode callNode) {
              if (ApplySpecialization.isApply(callNode))
                throw ApplySpecialization.HAS_APPLIES; 
              return true;
            }
          });
    } catch (AppliesFoundException e) {
      return true;
    } 
    this.log.fine(new Object[] { "There are no applies in ", DebugLogger.quote(functionNode.getName()), " - nothing to do." });
    return false;
  }
  
  private static void checkValidTransform(final FunctionNode functionNode) {
    final Deque<Set<Expression>> stack = new ArrayDeque<>();
    functionNode.accept((NodeVisitor)new SimpleNodeVisitor() {
          private boolean isCurrentArg(Expression expr) {
            return (!stack.isEmpty() && ((Set)stack.peek()).contains(expr));
          }
          
          private boolean isArguments(Expression expr) {
            return (expr instanceof IdentNode && ApplySpecialization.ARGUMENTS.equals(((IdentNode)expr).getName()));
          }
          
          private boolean isParam(String name) {
            for (IdentNode param : functionNode.getParameters()) {
              if (param.getName().equals(name))
                return true; 
            } 
            return false;
          }
          
          public Node leaveIdentNode(IdentNode identNode) {
            if (isParam(identNode.getName()))
              throw new ApplySpecialization.TransformFailedException(this.lc.getCurrentFunction(), "parameter: " + identNode.getName()); 
            if (isArguments((Expression)identNode) && !isCurrentArg((Expression)identNode))
              throw new ApplySpecialization.TransformFailedException(this.lc.getCurrentFunction(), "is 'arguments': " + identNode.getName()); 
            return (Node)identNode;
          }
          
          public boolean enterCallNode(CallNode callNode) {
            Set<Expression> callArgs = new HashSet<>();
            if (ApplySpecialization.isApply(callNode)) {
              List<Expression> argList = callNode.getArgs();
              if (argList.size() != 2 || !isArguments(argList.get(argList.size() - 1)))
                throw new ApplySpecialization.TransformFailedException(this.lc.getCurrentFunction(), "argument pattern not matched: " + argList); 
              callArgs.addAll(callNode.getArgs());
            } 
            stack.push(callArgs);
            return true;
          }
          
          public Node leaveCallNode(CallNode callNode) {
            stack.pop();
            return (Node)callNode;
          }
        });
  }
  
  public boolean enterCallNode(CallNode callNode) {
    return !this.explodedArguments.isEmpty();
  }
  
  public Node leaveCallNode(CallNode callNode) {
    List<IdentNode> newParams = this.explodedArguments.peek();
    if (isApply(callNode)) {
      List<Expression> newArgs = new ArrayList<>();
      for (Expression arg : callNode.getArgs()) {
        if (arg instanceof IdentNode && ARGUMENTS.equals(((IdentNode)arg).getName())) {
          newArgs.addAll(newParams);
          continue;
        } 
        newArgs.add(arg);
      } 
      this.changed.add(Integer.valueOf(this.lc.getCurrentFunction().getId()));
      CallNode newCallNode = callNode.setArgs(newArgs).setIsApplyToCall();
      if (this.log.isEnabled())
        this.log.fine(new Object[] { "Transformed ", callNode, " from apply to call => ", newCallNode, " in ", 
              
              DebugLogger.quote(this.lc.getCurrentFunction().getName()) }); 
      return (Node)newCallNode;
    } 
    return (Node)callNode;
  }
  
  private void pushExplodedArgs(FunctionNode functionNode) {
    int start = 0;
    MethodType actualCallSiteType = this.compiler.getCallSiteType(functionNode);
    if (actualCallSiteType == null)
      throw new TransformFailedException(this.lc.getCurrentFunction(), "No callsite type"); 
    assert actualCallSiteType.parameterType(actualCallSiteType.parameterCount() - 1) != Object[].class : "error vararg callsite passed to apply2call " + functionNode.getName() + " " + actualCallSiteType;
    TypeMap ptm = this.compiler.getTypeMap();
    if (ptm.needsCallee())
      start++; 
    start++;
    assert functionNode.getNumOfParams() == 0 : "apply2call on function with named paramaters!";
    List<IdentNode> newParams = new ArrayList<>();
    long to = (actualCallSiteType.parameterCount() - start);
    for (int i = 0; i < to; i++)
      newParams.add(new IdentNode(functionNode.getToken(), functionNode.getFinish(), CompilerConstants.EXPLODED_ARGUMENT_PREFIX.symbolName() + CompilerConstants.EXPLODED_ARGUMENT_PREFIX.symbolName())); 
    this.callSiteTypes.push(actualCallSiteType);
    this.explodedArguments.push(newParams);
  }
  
  public boolean enterFunctionNode(FunctionNode functionNode) {
    if (!USE_APPLY2CALL || 
      
      !this.compiler.isOnDemandCompilation() || 
      
      !functionNode.needsArguments() || functionNode
      
      .hasEval() || functionNode
      
      .getNumOfParams() != 0)
      return false; 
    if (!Global.isBuiltinFunctionPrototypeApply()) {
      this.log.fine("Apply transform disabled: apply/call overridden");
      assert !Global.isBuiltinFunctionPrototypeCall() : "call and apply should have the same SwitchPoint";
      return false;
    } 
    if (!hasApplies(functionNode))
      return false; 
    if (this.log.isEnabled())
      this.log.info(new Object[] { "Trying to specialize apply to call in '", functionNode
            .getName(), "' params=", functionNode
            
            .getParameters(), " id=", 
            
            Integer.valueOf(functionNode.getId()), " source=", 
            
            massageURL(functionNode.getSource().getURL()) }); 
    try {
      checkValidTransform(functionNode);
      pushExplodedArgs(functionNode);
    } catch (TransformFailedException e) {
      this.log.info(new Object[] { "Failure: ", e.getMessage() });
      return false;
    } 
    return true;
  }
  
  public Node leaveFunctionNode(FunctionNode functionNode) {
    FunctionNode newFunctionNode = functionNode;
    String functionName = newFunctionNode.getName();
    if (this.changed.contains(Integer.valueOf(newFunctionNode.getId()))) {
      newFunctionNode = newFunctionNode.clearFlag(this.lc, 8).setFlag(this.lc, 4096).setParameters(this.lc, this.explodedArguments.peek());
      if (this.log.isEnabled())
        this.log.info(new Object[] { "Success: ", 
              massageURL(newFunctionNode.getSource().getURL()), 
              Character.valueOf('.'), functionName, "' id=", 
              
              Integer.valueOf(newFunctionNode.getId()), " params=", this.callSiteTypes
              
              .peek() }); 
    } 
    this.callSiteTypes.pop();
    this.explodedArguments.pop();
    return (Node)newFunctionNode;
  }
  
  private static boolean isApply(CallNode callNode) {
    Expression f = callNode.getFunction();
    return (f instanceof AccessNode && "apply".equals(((AccessNode)f).getProperty()));
  }
  
  private static String massageURL(URL url) {
    if (url == null)
      return "<null>"; 
    String str = url.toString();
    int slash = str.lastIndexOf('/');
    if (slash == -1)
      return str; 
    return str.substring(slash + 1);
  }
}
