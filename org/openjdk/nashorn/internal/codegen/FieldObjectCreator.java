package org.openjdk.nashorn.internal.codegen;

import java.util.List;
import org.openjdk.nashorn.internal.codegen.types.Type;
import org.openjdk.nashorn.internal.ir.Symbol;
import org.openjdk.nashorn.internal.runtime.Context;
import org.openjdk.nashorn.internal.runtime.PropertyMap;
import org.openjdk.nashorn.internal.runtime.ScriptObject;
import org.openjdk.nashorn.internal.runtime.arrays.ArrayIndex;

public abstract class FieldObjectCreator<T> extends ObjectCreator<T> {
  private String fieldObjectClassName;
  
  private Class<? extends ScriptObject> fieldObjectClass;
  
  private int fieldCount;
  
  private int paddedFieldCount;
  
  private int paramCount;
  
  private final int callSiteFlags;
  
  private final boolean evalCode;
  
  FieldObjectCreator(CodeGenerator codegen, List<MapTuple<T>> tuples) {
    this(codegen, tuples, false, false);
  }
  
  FieldObjectCreator(CodeGenerator codegen, List<MapTuple<T>> tuples, boolean isScope, boolean hasArguments) {
    super(codegen, tuples, isScope, hasArguments);
    this.callSiteFlags = codegen.getCallSiteFlags();
    this.evalCode = codegen.isEvalCode();
    countFields();
    findClass();
  }
  
  public void createObject(MethodEmitter method) {
    makeMap();
    String className = getClassName();
    assert this.fieldObjectClass != null;
    method._new(this.fieldObjectClass).dup();
    loadMap(method);
    if (isScope()) {
      loadScope(method);
      if (hasArguments()) {
        method.loadCompilerConstant(CompilerConstants.ARGUMENTS);
        method.invoke(CompilerConstants.constructorNoLookup(className, new Class[] { PropertyMap.class, ScriptObject.class, CompilerConstants.ARGUMENTS.type() }));
      } else {
        method.invoke(CompilerConstants.constructorNoLookup(className, new Class[] { PropertyMap.class, ScriptObject.class }));
      } 
    } else {
      method.invoke(CompilerConstants.constructorNoLookup(className, new Class[] { PropertyMap.class }));
    } 
  }
  
  void createForInIterationScope(MethodEmitter method) {
    assert this.fieldObjectClass != null;
    assert isScope();
    assert getMap() != null;
    String className = getClassName();
    method._new(this.fieldObjectClass).dup();
    loadMap(method);
    loadScope(method);
    method.invoke(ScriptObject.GET_PROTO);
    method.invoke(CompilerConstants.constructorNoLookup(className, new Class[] { PropertyMap.class, ScriptObject.class }));
  }
  
  public void populateRange(MethodEmitter method, Type objectType, int objectSlot, int start, int end) {
    method.load(objectType, objectSlot);
    for (int i = start; i < end; i++) {
      MapTuple<T> tuple = this.tuples.get(i);
      if (tuple.symbol != null && tuple.value != null) {
        int index = ArrayIndex.getArrayIndex(tuple.key);
        method.dup();
        if (!ArrayIndex.isValidArrayIndex(index)) {
          putField(method, tuple.key, tuple.symbol.getFieldIndex(), tuple);
        } else {
          putSlot(method, ArrayIndex.toLongIndex(index), tuple);
        } 
        method.invalidateSpecialName(tuple.key);
      } 
    } 
  }
  
  protected PropertyMap makeMap() {
    assert this.propertyMap == null : "property map already initialized";
    this.propertyMap = newMapCreator(this.fieldObjectClass).makeFieldMap(hasArguments(), this.codegen.useDualFields(), this.fieldCount, this.paddedFieldCount, this.evalCode);
    return this.propertyMap;
  }
  
  private void putField(MethodEmitter method, String key, int fieldIndex, MapTuple<T> tuple) {
    Type fieldType = (this.codegen.useDualFields() && tuple.isPrimitive()) ? ObjectClassGenerator.PRIMITIVE_FIELD_TYPE : Type.OBJECT;
    String fieldClass = getClassName();
    String fieldName = ObjectClassGenerator.getFieldName(fieldIndex, fieldType);
    String fieldDesc = CompilerConstants.typeDescriptor(fieldType.getTypeClass());
    assert fieldName.equals(ObjectClassGenerator.getFieldName(fieldIndex, ObjectClassGenerator.PRIMITIVE_FIELD_TYPE)) || fieldType.isObject() : key + " object keys must store to L*-fields";
    assert fieldName.equals(ObjectClassGenerator.getFieldName(fieldIndex, Type.OBJECT)) || fieldType.isPrimitive() : key + " primitive keys must store to J*-fields";
    loadTuple(method, tuple, true);
    method.putField(fieldClass, fieldName, fieldDesc);
  }
  
  private void putSlot(MethodEmitter method, long index, MapTuple<T> tuple) {
    loadIndex(method, index);
    loadTuple(method, tuple, false);
    method.dynamicSetIndex(this.callSiteFlags);
  }
  
  private void findClass() {
    this
      
      .fieldObjectClassName = isScope() ? ObjectClassGenerator.getClassName(this.fieldCount, this.paramCount, this.codegen.useDualFields()) : ObjectClassGenerator.getClassName(this.paddedFieldCount, this.codegen.useDualFields());
    try {
      this.fieldObjectClass = Context.forStructureClass(Compiler.binaryName(this.fieldObjectClassName));
    } catch (ClassNotFoundException e) {
      throw new AssertionError("Nashorn has encountered an internal error.  Structure can not be created.");
    } 
  }
  
  protected Class<? extends ScriptObject> getAllocatorClass() {
    return this.fieldObjectClass;
  }
  
  String getClassName() {
    return this.fieldObjectClassName;
  }
  
  private void countFields() {
    for (MapTuple<T> tuple : this.tuples) {
      Symbol symbol = tuple.symbol;
      if (symbol != null) {
        if (hasArguments() && symbol.isParam()) {
          symbol.setFieldIndex(this.paramCount++);
          continue;
        } 
        if (!ArrayIndex.isValidArrayIndex(ArrayIndex.getArrayIndex(tuple.key)))
          symbol.setFieldIndex(this.fieldCount++); 
      } 
    } 
    this.paddedFieldCount = ObjectClassGenerator.getPaddedFieldCount(this.fieldCount);
  }
}
