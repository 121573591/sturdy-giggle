package org.openjdk.nashorn.internal.codegen;

import java.lang.invoke.MethodType;
import org.openjdk.nashorn.internal.codegen.types.Type;
import org.openjdk.nashorn.internal.ir.AccessNode;
import org.openjdk.nashorn.internal.ir.CallNode;
import org.openjdk.nashorn.internal.ir.Expression;
import org.openjdk.nashorn.internal.ir.FunctionNode;
import org.openjdk.nashorn.internal.ir.IdentNode;
import org.openjdk.nashorn.internal.ir.IndexNode;
import org.openjdk.nashorn.internal.ir.Optimistic;
import org.openjdk.nashorn.internal.runtime.ECMAException;
import org.openjdk.nashorn.internal.runtime.FindProperty;
import org.openjdk.nashorn.internal.runtime.JSType;
import org.openjdk.nashorn.internal.runtime.Property;
import org.openjdk.nashorn.internal.runtime.RecompilableScriptFunctionData;
import org.openjdk.nashorn.internal.runtime.ScriptFunction;
import org.openjdk.nashorn.internal.runtime.ScriptObject;
import org.openjdk.nashorn.internal.runtime.ScriptRuntime;

final class TypeEvaluator {
  private static final MethodType EMPTY_INVOCATION_TYPE = MethodType.methodType(Object.class, ScriptFunction.class, new Class[] { Object.class });
  
  private final Compiler compiler;
  
  private final ScriptObject runtimeScope;
  
  TypeEvaluator(Compiler compiler, ScriptObject runtimeScope) {
    this.compiler = compiler;
    this.runtimeScope = runtimeScope;
  }
  
  boolean hasStringPropertyIterator(Expression expr) {
    return evaluateSafely(expr) instanceof ScriptObject;
  }
  
  Type getOptimisticType(Optimistic node) {
    assert this.compiler.useOptimisticTypes();
    int programPoint = node.getProgramPoint();
    Type validType = this.compiler.getInvalidatedProgramPointType(programPoint);
    if (validType != null)
      return validType; 
    Type mostOptimisticType = node.getMostOptimisticType();
    Type evaluatedType = getEvaluatedType(node);
    if (evaluatedType != null) {
      if (evaluatedType.widerThan(mostOptimisticType)) {
        Type newValidType = (evaluatedType.isObject() || evaluatedType.isBoolean()) ? Type.OBJECT : evaluatedType;
        this.compiler.addInvalidatedProgramPoint(node.getProgramPoint(), newValidType);
      } 
      return evaluatedType;
    } 
    return mostOptimisticType;
  }
  
  private static Type getPropertyType(ScriptObject sobj, String name) {
    FindProperty find = sobj.findProperty(name, true);
    if (find == null)
      return null; 
    Property property = find.getProperty();
    Class<?> propertyClass = property.getType();
    if (propertyClass == null)
      return null; 
    if (propertyClass.isPrimitive())
      return Type.typeFor(propertyClass); 
    ScriptObject owner = find.getOwner();
    if (property.hasGetterFunction(owner))
      return Type.OBJECT; 
    Object value = property.needsDeclaration() ? ScriptRuntime.UNDEFINED : property.getObjectValue(owner, owner);
    if (value == ScriptRuntime.UNDEFINED)
      return null; 
    return Type.typeFor(JSType.unboxedFieldType(value));
  }
  
  void declareLocalSymbol(String symbolName) {
    assert this.compiler
      .useOptimisticTypes() && this.compiler
      .isOnDemandCompilation() && this.runtimeScope != null : "useOptimistic=" + this.compiler
      
      .useOptimisticTypes() + " isOnDemand=" + this.compiler
      
      .isOnDemandCompilation() + " scope=" + this.runtimeScope;
    if (this.runtimeScope.findProperty(symbolName, false) == null)
      this.runtimeScope.addOwnProperty(symbolName, 7, ScriptRuntime.UNDEFINED); 
  }
  
  private Object evaluateSafely(Expression expr) {
    if (expr instanceof IdentNode)
      return (this.runtimeScope == null) ? null : evaluatePropertySafely(this.runtimeScope, ((IdentNode)expr).getName()); 
    if (expr instanceof AccessNode) {
      AccessNode accessNode = (AccessNode)expr;
      Object base = evaluateSafely(accessNode.getBase());
      if (!(base instanceof ScriptObject))
        return null; 
      return evaluatePropertySafely((ScriptObject)base, accessNode.getProperty());
    } 
    return null;
  }
  
  private static Object evaluatePropertySafely(ScriptObject sobj, String name) {
    FindProperty find = sobj.findProperty(name, true);
    if (find == null)
      return null; 
    Property property = find.getProperty();
    ScriptObject owner = find.getOwner();
    if (property.hasGetterFunction(owner))
      return null; 
    try {
      return property.getObjectValue(owner, owner);
    } catch (ECMAException e) {
      if (e.thrown instanceof org.openjdk.nashorn.internal.objects.NativeReferenceError)
        return null; 
      throw e;
    } 
  }
  
  private Type getEvaluatedType(Optimistic expr) {
    if (expr instanceof IdentNode) {
      if (this.runtimeScope == null)
        return null; 
      return getPropertyType(this.runtimeScope, ((IdentNode)expr).getName());
    } 
    if (expr instanceof AccessNode) {
      AccessNode accessNode = (AccessNode)expr;
      Object base = evaluateSafely(accessNode.getBase());
      if (!(base instanceof ScriptObject))
        return null; 
      return getPropertyType((ScriptObject)base, accessNode.getProperty());
    } 
    if (expr instanceof IndexNode) {
      IndexNode indexNode = (IndexNode)expr;
      Object base = evaluateSafely(indexNode.getBase());
      if (base instanceof org.openjdk.nashorn.internal.objects.NativeArray || base instanceof org.openjdk.nashorn.internal.objects.ArrayBufferView)
        return ((ScriptObject)base).getArray().getOptimisticType(); 
    } else if (expr instanceof CallNode) {
      CallNode callExpr = (CallNode)expr;
      Expression fnExpr = callExpr.getFunction();
      if (fnExpr instanceof FunctionNode && (this.compiler.getContext().getEnv())._lazy_compilation) {
        FunctionNode fn = (FunctionNode)fnExpr;
        if (callExpr.getArgs().isEmpty()) {
          RecompilableScriptFunctionData data = this.compiler.getScriptFunctionData(fn.getId());
          if (data != null) {
            Type returnType = Type.typeFor(data.getReturnType(EMPTY_INVOCATION_TYPE, this.runtimeScope));
            if (returnType == Type.BOOLEAN)
              return Type.OBJECT; 
            assert returnType == Type.INT || returnType == Type.NUMBER || returnType == Type.OBJECT;
            return returnType;
          } 
        } 
      } 
    } 
    return null;
  }
}
