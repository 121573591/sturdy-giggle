package org.openjdk.nashorn.internal.objects;

import java.util.Collections;
import org.openjdk.nashorn.internal.runtime.ECMAErrors;
import org.openjdk.nashorn.internal.runtime.JSType;
import org.openjdk.nashorn.internal.runtime.PropertyMap;
import org.openjdk.nashorn.internal.runtime.ScriptObject;
import org.openjdk.nashorn.internal.runtime.ScriptRuntime;
import org.openjdk.nashorn.internal.runtime.Undefined;

public class ArrayIterator extends AbstractIterator {
  private static PropertyMap $nasgenmap$;
  
  private ScriptObject iteratedObject;
  
  private long nextIndex = 0L;
  
  private final AbstractIterator.IterationKind iterationKind;
  
  private final Global global;
  
  private ArrayIterator(Object iteratedObject, AbstractIterator.IterationKind iterationKind, Global global) {
    super(global.getArrayIteratorPrototype(), $nasgenmap$);
    this.iteratedObject = (iteratedObject instanceof ScriptObject) ? (ScriptObject)iteratedObject : null;
    this.iterationKind = iterationKind;
    this.global = global;
  }
  
  static ArrayIterator newArrayValueIterator(Object iteratedObject) {
    return new ArrayIterator(Global.toObject(iteratedObject), AbstractIterator.IterationKind.VALUE, Global.instance());
  }
  
  static ArrayIterator newArrayKeyIterator(Object iteratedObject) {
    return new ArrayIterator(Global.toObject(iteratedObject), AbstractIterator.IterationKind.KEY, Global.instance());
  }
  
  static ArrayIterator newArrayKeyValueIterator(Object iteratedObject) {
    return new ArrayIterator(Global.toObject(iteratedObject), AbstractIterator.IterationKind.KEY_VALUE, Global.instance());
  }
  
  public static Object next(Object self, Object arg) {
    if (!(self instanceof ArrayIterator))
      throw ECMAErrors.typeError("not.a.array.iterator", new String[] { ScriptRuntime.safeToString(self) }); 
    return ((ArrayIterator)self).next(arg);
  }
  
  public String getClassName() {
    return "Array Iterator";
  }
  
  protected IteratorResult next(Object arg) {
    long index = this.nextIndex;
    if (this.iteratedObject == null || index >= JSType.toUint32(this.iteratedObject.getLength())) {
      this.iteratedObject = null;
      return makeResult(Undefined.getUndefined(), Boolean.TRUE, this.global);
    } 
    this.nextIndex++;
    if (this.iterationKind == AbstractIterator.IterationKind.KEY_VALUE) {
      NativeArray nativeArray = new NativeArray(new Object[] { JSType.toNarrowestNumber(index), this.iteratedObject.get(index) });
      return makeResult(nativeArray, Boolean.FALSE, this.global);
    } 
    Object value = (this.iterationKind == AbstractIterator.IterationKind.KEY) ? JSType.toNarrowestNumber(index) : this.iteratedObject.get(index);
    return makeResult(value, Boolean.FALSE, this.global);
  }
  
  static {
    $clinit$();
  }
  
  public static void $clinit$() {
    $nasgenmap$ = PropertyMap.newMap(Collections.EMPTY_LIST);
  }
}
