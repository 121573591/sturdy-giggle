package org.openjdk.nashorn.internal.codegen;

import java.util.List;
import org.openjdk.nashorn.internal.codegen.types.Type;
import org.openjdk.nashorn.internal.ir.Expression;
import org.openjdk.nashorn.internal.ir.LiteralNode;
import org.openjdk.nashorn.internal.runtime.JSType;
import org.openjdk.nashorn.internal.runtime.Property;
import org.openjdk.nashorn.internal.runtime.PropertyMap;
import org.openjdk.nashorn.internal.runtime.ScriptObject;
import org.openjdk.nashorn.internal.runtime.ScriptRuntime;
import org.openjdk.nashorn.internal.runtime.arrays.ArrayData;
import org.openjdk.nashorn.internal.runtime.arrays.ArrayIndex;
import org.openjdk.nashorn.internal.scripts.JD;
import org.openjdk.nashorn.internal.scripts.JO;

public final class SpillObjectCreator extends ObjectCreator<Expression> {
  SpillObjectCreator(CodeGenerator codegen, List<MapTuple<Expression>> tuples) {
    super(codegen, tuples, false, false);
    makeMap();
  }
  
  public void createObject(MethodEmitter method) {
    assert !isScope() : "spill scope objects are not currently supported";
    int length = this.tuples.size();
    boolean dualFields = this.codegen.useDualFields();
    int spillLength = ScriptObject.spillAllocationLength(length);
    long[] jpresetValues = dualFields ? new long[spillLength] : null;
    Object[] opresetValues = new Object[spillLength];
    Class<?> objectClass = getAllocatorClass();
    ArrayData arrayData = ArrayData.allocate(ScriptRuntime.EMPTY_ARRAY);
    int pos = 0;
    for (MapTuple<Expression> tuple : this.tuples) {
      String key = tuple.key;
      Expression value = (Expression)tuple.value;
      method.invalidateSpecialName(tuple.key);
      if (value != null) {
        Object constantValue = LiteralNode.objectAsConstant(value);
        if (constantValue != LiteralNode.POSTSET_MARKER) {
          Property property = this.propertyMap.findProperty(key);
          if (property != null) {
            property.setType(dualFields ? JSType.unboxedFieldType(constantValue) : Object.class);
            int slot = property.getSlot();
            if (dualFields && constantValue instanceof Number) {
              jpresetValues[slot] = ObjectClassGenerator.pack((Number)constantValue);
            } else {
              opresetValues[slot] = constantValue;
            } 
          } else {
            long oldLength = arrayData.length();
            int index = ArrayIndex.getArrayIndex(key);
            long longIndex = ArrayIndex.toLongIndex(index);
            assert ArrayIndex.isValidArrayIndex(index);
            if (longIndex >= oldLength)
              arrayData = arrayData.ensure(longIndex); 
            if (constantValue instanceof Integer) {
              arrayData = arrayData.set(index, ((Integer)constantValue).intValue(), false);
            } else if (constantValue instanceof Double) {
              arrayData = arrayData.set(index, ((Double)constantValue).doubleValue(), false);
            } else {
              arrayData = arrayData.set(index, constantValue, false);
            } 
            if (longIndex > oldLength)
              arrayData = arrayData.delete(oldLength, longIndex - 1L); 
          } 
        } 
      } 
      pos++;
    } 
    method._new(objectClass).dup();
    this.codegen.loadConstant(this.propertyMap);
    if (dualFields) {
      this.codegen.loadConstant(jpresetValues);
    } else {
      method.loadNull();
    } 
    this.codegen.loadConstant(opresetValues);
    method.invoke(CompilerConstants.constructorNoLookup(objectClass, new Class[] { PropertyMap.class, long[].class, Object[].class }));
    if (arrayData.length() > 0L) {
      method.dup();
      this.codegen.loadConstant(arrayData);
      method.invoke(CompilerConstants.virtualCallNoLookup(ScriptObject.class, "setArray", void.class, new Class[] { ArrayData.class }));
    } 
  }
  
  public void populateRange(MethodEmitter method, Type objectType, int objectSlot, int start, int end) {
    int callSiteFlags = this.codegen.getCallSiteFlags();
    method.load(objectType, objectSlot);
    for (int i = start; i < end; i++) {
      MapTuple<Expression> tuple = this.tuples.get(i);
      if (!LiteralNode.isConstant(tuple.value)) {
        Property property = this.propertyMap.findProperty(tuple.key);
        if (property == null) {
          int index = ArrayIndex.getArrayIndex(tuple.key);
          assert ArrayIndex.isValidArrayIndex(index);
          method.dup();
          loadIndex(method, ArrayIndex.toLongIndex(index));
          loadTuple(method, tuple, false);
          method.dynamicSetIndex(callSiteFlags);
        } else {
          assert property.getKey() instanceof String;
          method.dup();
          loadTuple(method, tuple, false);
          method.dynamicSet((String)property.getKey(), this.codegen.getCallSiteFlags(), false);
        } 
      } 
    } 
  }
  
  protected PropertyMap makeMap() {
    assert this.propertyMap == null : "property map already initialized";
    Class<? extends ScriptObject> clazz = getAllocatorClass();
    this.propertyMap = (new MapCreator(clazz, this.tuples)).makeSpillMap(false, this.codegen.useDualFields());
    return this.propertyMap;
  }
  
  protected void loadValue(Expression expr, Type type) {
    this.codegen.loadExpressionAsType(expr, Type.generic(type));
  }
  
  protected Class<? extends ScriptObject> getAllocatorClass() {
    return this.codegen.useDualFields() ? (Class)JD.class : (Class)JO.class;
  }
}
