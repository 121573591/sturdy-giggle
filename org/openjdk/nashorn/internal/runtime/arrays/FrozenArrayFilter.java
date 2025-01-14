package org.openjdk.nashorn.internal.runtime.arrays;

import org.openjdk.nashorn.internal.objects.Global;
import org.openjdk.nashorn.internal.runtime.ECMAErrors;
import org.openjdk.nashorn.internal.runtime.PropertyDescriptor;
import org.openjdk.nashorn.internal.runtime.ScriptRuntime;

final class FrozenArrayFilter extends SealedArrayFilter {
  FrozenArrayFilter(ArrayData underlying) {
    super(underlying);
  }
  
  public ArrayData copy() {
    return this;
  }
  
  public PropertyDescriptor getDescriptor(Global global, int index) {
    return global.newDataDescriptor(getObject(index), false, true, false);
  }
  
  public ArrayData set(int index, int value, boolean strict) {
    if (strict)
      throw ECMAErrors.typeError("cant.set.property", new String[] { Integer.toString(index), "frozen array" }); 
    return this;
  }
  
  public ArrayData set(int index, double value, boolean strict) {
    if (strict)
      throw ECMAErrors.typeError("cant.set.property", new String[] { Integer.toString(index), "frozen array" }); 
    return this;
  }
  
  public ArrayData set(int index, Object value, boolean strict) {
    if (strict)
      throw ECMAErrors.typeError("cant.set.property", new String[] { Integer.toString(index), "frozen array" }); 
    return this;
  }
  
  public ArrayData push(boolean strict, Object... items) {
    return this;
  }
  
  public Object pop() {
    int len = (int)this.underlying.length();
    return (len == 0) ? ScriptRuntime.UNDEFINED : this.underlying.getObject(len - 1);
  }
}
