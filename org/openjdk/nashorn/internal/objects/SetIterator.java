package org.openjdk.nashorn.internal.objects;

import java.util.Collections;
import org.openjdk.nashorn.internal.runtime.ECMAErrors;
import org.openjdk.nashorn.internal.runtime.PropertyMap;
import org.openjdk.nashorn.internal.runtime.ScriptRuntime;
import org.openjdk.nashorn.internal.runtime.Undefined;

public class SetIterator extends AbstractIterator {
  private static PropertyMap $nasgenmap$;
  
  private LinkedMap.LinkedMapIterator iterator;
  
  private final AbstractIterator.IterationKind iterationKind;
  
  private final Global global;
  
  SetIterator(NativeSet set, AbstractIterator.IterationKind iterationKind, Global global) {
    super(global.getSetIteratorPrototype(), $nasgenmap$);
    this.iterator = set.getJavaMap().getIterator();
    this.iterationKind = iterationKind;
    this.global = global;
  }
  
  public static Object next(Object self, Object arg) {
    if (!(self instanceof SetIterator))
      throw ECMAErrors.typeError("not.a.set.iterator", new String[] { ScriptRuntime.safeToString(self) }); 
    return ((SetIterator)self).next(arg);
  }
  
  public String getClassName() {
    return "Set Iterator";
  }
  
  protected IteratorResult next(Object arg) {
    if (this.iterator == null)
      return makeResult(Undefined.getUndefined(), Boolean.TRUE, this.global); 
    LinkedMap.Node node = this.iterator.next();
    if (node == null) {
      this.iterator = null;
      return makeResult(Undefined.getUndefined(), Boolean.TRUE, this.global);
    } 
    if (this.iterationKind == AbstractIterator.IterationKind.KEY_VALUE) {
      NativeArray array = new NativeArray(new Object[] { node.getKey(), node.getKey() });
      return makeResult(array, Boolean.FALSE, this.global);
    } 
    return makeResult(node.getKey(), Boolean.FALSE, this.global);
  }
  
  static {
    $clinit$();
  }
  
  public static void $clinit$() {
    $nasgenmap$ = PropertyMap.newMap(Collections.EMPTY_LIST);
  }
}
