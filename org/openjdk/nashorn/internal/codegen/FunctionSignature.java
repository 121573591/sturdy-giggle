package org.openjdk.nashorn.internal.codegen;

import java.lang.invoke.MethodType;
import java.util.ArrayList;
import java.util.List;
import org.openjdk.nashorn.internal.codegen.types.Type;
import org.openjdk.nashorn.internal.ir.Expression;
import org.openjdk.nashorn.internal.ir.FunctionNode;
import org.openjdk.nashorn.internal.lookup.Lookup;
import org.openjdk.nashorn.internal.runtime.ScriptFunction;

public final class FunctionSignature {
  private final Type[] paramTypes;
  
  private final Type returnType;
  
  private final String descriptor;
  
  private final MethodType methodType;
  
  public FunctionSignature(boolean hasSelf, boolean hasCallee, Type retType, List<? extends Expression> args) {
    this(hasSelf, hasCallee, retType, typeArray(args));
  }
  
  public FunctionSignature(boolean hasSelf, boolean hasCallee, Type retType, int nArgs) {
    this(hasSelf, hasCallee, retType, objectArgs(nArgs));
  }
  
  private FunctionSignature(boolean hasSelf, boolean hasCallee, Type retType, Type... argTypes) {
    boolean isVarArg;
    int count = 1;
    if (argTypes == null) {
      isVarArg = true;
    } else {
      isVarArg = (argTypes.length > 125);
      count = isVarArg ? 1 : argTypes.length;
    } 
    if (hasCallee)
      count++; 
    if (hasSelf)
      count++; 
    this.paramTypes = new Type[count];
    int next = 0;
    if (hasCallee)
      this.paramTypes[next++] = Type.typeFor(ScriptFunction.class); 
    if (hasSelf)
      this.paramTypes[next++] = Type.OBJECT; 
    if (isVarArg) {
      this.paramTypes[next] = (Type)Type.OBJECT_ARRAY;
    } else {
      assert argTypes != null : "isVarArgs cannot be false when argTypes are null";
      for (int j = 0; next < count; ) {
        Type type = argTypes[j++];
        this.paramTypes[next++] = type.isObject() ? Type.OBJECT : type;
      } 
    } 
    this.returnType = retType;
    this.descriptor = Type.getMethodDescriptor(this.returnType, this.paramTypes);
    List<Class<?>> paramTypeList = new ArrayList<>();
    for (Type paramType : this.paramTypes)
      paramTypeList.add(paramType.getTypeClass()); 
    this.methodType = Lookup.MH.type(this.returnType.getTypeClass(), (Class[])paramTypeList.<Class<?>[]>toArray((Class<?>[][])new Class[0]));
  }
  
  public FunctionSignature(FunctionNode functionNode) {
    this(true, functionNode
        
        .needsCallee(), functionNode
        .getReturnType(), (
        functionNode.isVarArg() && !functionNode.isProgram()) ? 
        null : 
        functionNode.getParameters());
  }
  
  private static Type[] typeArray(List<? extends Expression> args) {
    if (args == null)
      return null; 
    Type[] typeArray = new Type[args.size()];
    int pos = 0;
    for (Expression arg : args)
      typeArray[pos++] = arg.getType(); 
    return typeArray;
  }
  
  public String toString() {
    return this.descriptor;
  }
  
  public int size() {
    return this.paramTypes.length;
  }
  
  public Type[] getParamTypes() {
    return (Type[])this.paramTypes.clone();
  }
  
  public MethodType getMethodType() {
    return this.methodType;
  }
  
  public Type getReturnType() {
    return this.returnType;
  }
  
  private static Type[] objectArgs(int nArgs) {
    Type[] array = new Type[nArgs];
    for (int i = 0; i < nArgs; i++)
      array[i] = Type.OBJECT; 
    return array;
  }
}
