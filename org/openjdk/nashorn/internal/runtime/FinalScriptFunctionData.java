package org.openjdk.nashorn.internal.runtime;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;
import java.util.Collection;
import java.util.List;

final class FinalScriptFunctionData extends ScriptFunctionData {
  private String docKey;
  
  private static final long serialVersionUID = -930632846167768864L;
  
  FinalScriptFunctionData(String name, int arity, List<CompiledFunction> functions, int flags) {
    super(name, arity, flags);
    this.code.addAll(functions);
    assert !needsCallee();
  }
  
  FinalScriptFunctionData(String name, MethodHandle mh, Specialization[] specs, int flags) {
    super(name, methodHandleArity(mh), flags);
    addInvoker(mh);
    if (specs != null)
      for (Specialization spec : specs)
        addInvoker(spec.getMethodHandle(), spec);  
  }
  
  String getDocumentationKey() {
    return this.docKey;
  }
  
  void setDocumentationKey(String docKey) {
    this.docKey = docKey;
  }
  
  String getDocumentation() {
    String doc = (this.docKey != null) ? FunctionDocumentation.getDoc(this.docKey) : null;
    return (doc != null) ? doc : super.getDocumentation();
  }
  
  protected boolean needsCallee() {
    boolean needsCallee = ((CompiledFunction)this.code.getFirst()).needsCallee();
    assert allNeedCallee(needsCallee);
    return needsCallee;
  }
  
  private boolean allNeedCallee(boolean needCallee) {
    for (CompiledFunction inv : this.code) {
      if (inv.needsCallee() != needCallee)
        return false; 
    } 
    return true;
  }
  
  CompiledFunction getBest(MethodType callSiteType, ScriptObject runtimeScope, Collection<CompiledFunction> forbidden, boolean linkLogicOkay) {
    assert isValidCallSite(callSiteType) : callSiteType;
    CompiledFunction best = null;
    for (CompiledFunction candidate : this.code) {
      if (!linkLogicOkay && candidate.hasLinkLogic())
        continue; 
      if (!forbidden.contains(candidate) && candidate.betterThanFinal(best, callSiteType))
        best = candidate; 
    } 
    return best;
  }
  
  MethodType getGenericType() {
    int max = 0;
    for (CompiledFunction fn : this.code) {
      MethodType t = fn.type();
      if (ScriptFunctionData.isVarArg(t))
        return MethodType.genericMethodType(2, true); 
      int paramCount = t.parameterCount() - (ScriptFunctionData.needsCallee(t) ? 1 : 0);
      if (paramCount > max)
        max = paramCount; 
    } 
    return MethodType.genericMethodType(max + 1);
  }
  
  private void addInvoker(MethodHandle mh, Specialization specialization) {
    CompiledFunction invoker;
    assert !needsCallee(mh);
    if (isConstructor(mh)) {
      assert isConstructor();
      invoker = CompiledFunction.createBuiltInConstructor(mh);
    } else {
      invoker = new CompiledFunction(mh, null, specialization);
    } 
    this.code.add(invoker);
  }
  
  private void addInvoker(MethodHandle mh) {
    addInvoker(mh, (Specialization)null);
  }
  
  private static int methodHandleArity(MethodHandle mh) {
    if (isVarArg(mh))
      return 125; 
    return mh.type().parameterCount() - 1 - (needsCallee(mh) ? 1 : 0) - (isConstructor(mh) ? 1 : 0);
  }
  
  private static boolean isConstructor(MethodHandle mh) {
    return (mh.type().parameterCount() >= 1 && mh.type().parameterType(0) == boolean.class);
  }
}
