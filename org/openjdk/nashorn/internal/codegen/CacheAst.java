package org.openjdk.nashorn.internal.codegen;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import org.openjdk.nashorn.internal.ir.FunctionNode;
import org.openjdk.nashorn.internal.ir.Node;
import org.openjdk.nashorn.internal.ir.visitor.SimpleNodeVisitor;
import org.openjdk.nashorn.internal.runtime.RecompilableScriptFunctionData;

class CacheAst extends SimpleNodeVisitor {
  private final Deque<RecompilableScriptFunctionData> dataStack = new ArrayDeque<>();
  
  private final Compiler compiler;
  
  CacheAst(Compiler compiler) {
    this.compiler = compiler;
    assert !compiler.isOnDemandCompilation();
  }
  
  public boolean enterFunctionNode(FunctionNode functionNode) {
    int id = functionNode.getId();
    this.dataStack.push(this.dataStack.isEmpty() ? this.compiler.getScriptFunctionData(id) : ((RecompilableScriptFunctionData)this.dataStack.peek()).getScriptFunctionData(id));
    return true;
  }
  
  public Node leaveFunctionNode(FunctionNode functionNode) {
    RecompilableScriptFunctionData data = this.dataStack.pop();
    if (functionNode.isSplit())
      data.setCachedAst(functionNode); 
    if (!this.dataStack.isEmpty() && (((RecompilableScriptFunctionData)this.dataStack.peek()).getFunctionFlags() & 0x10) != 0)
      return (Node)functionNode.setBody(this.lc, functionNode.getBody().setStatements(null, Collections.emptyList())); 
    return (Node)functionNode;
  }
}
