package org.openjdk.nashorn.internal.codegen;

import java.util.List;
import org.openjdk.nashorn.internal.codegen.types.Type;
import org.openjdk.nashorn.internal.runtime.JSType;
import org.openjdk.nashorn.internal.runtime.PropertyMap;
import org.openjdk.nashorn.internal.runtime.ScriptObject;

public abstract class ObjectCreator<T> implements CodeGenerator.SplitLiteralCreator {
  final List<MapTuple<T>> tuples;
  
  final CodeGenerator codegen;
  
  protected PropertyMap propertyMap;
  
  private final boolean isScope;
  
  private final boolean hasArguments;
  
  ObjectCreator(CodeGenerator codegen, List<MapTuple<T>> tuples, boolean isScope, boolean hasArguments) {
    this.codegen = codegen;
    this.tuples = tuples;
    this.isScope = isScope;
    this.hasArguments = hasArguments;
  }
  
  public void makeObject(MethodEmitter method) {
    createObject(method);
    int objectSlot = method.getUsedSlotsWithLiveTemporaries();
    Type objectType = method.peekType();
    method.storeTemp(objectType, objectSlot);
    populateRange(method, objectType, objectSlot, 0, this.tuples.size());
  }
  
  protected abstract void createObject(MethodEmitter paramMethodEmitter);
  
  protected abstract PropertyMap makeMap();
  
  protected MapCreator<?> newMapCreator(Class<? extends ScriptObject> clazz) {
    return new MapCreator(clazz, this.tuples);
  }
  
  protected void loadScope(MethodEmitter method) {
    method.loadCompilerConstant(CompilerConstants.SCOPE);
  }
  
  protected MethodEmitter loadMap(MethodEmitter method) {
    this.codegen.loadConstant(this.propertyMap);
    return method;
  }
  
  PropertyMap getMap() {
    return this.propertyMap;
  }
  
  protected boolean isScope() {
    return this.isScope;
  }
  
  protected boolean hasArguments() {
    return this.hasArguments;
  }
  
  protected abstract Class<? extends ScriptObject> getAllocatorClass();
  
  protected abstract void loadValue(T paramT, Type paramType);
  
  MethodEmitter loadTuple(MethodEmitter method, MapTuple<T> tuple, boolean pack) {
    loadValue(tuple.value, tuple.type);
    if (!this.codegen.useDualFields() || !tuple.isPrimitive()) {
      method.convert(Type.OBJECT);
    } else if (pack) {
      method.pack();
    } 
    return method;
  }
  
  MethodEmitter loadIndex(MethodEmitter method, long index) {
    return JSType.isRepresentableAsInt(index) ? method.load((int)index) : method.load(index);
  }
}
