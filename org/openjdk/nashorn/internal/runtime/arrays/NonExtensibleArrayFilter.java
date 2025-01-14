package org.openjdk.nashorn.internal.runtime.arrays;

import org.openjdk.nashorn.internal.objects.Global;
import org.openjdk.nashorn.internal.runtime.ECMAErrors;
import org.openjdk.nashorn.internal.runtime.ScriptRuntime;

final class NonExtensibleArrayFilter extends ArrayFilter {
  NonExtensibleArrayFilter(ArrayData underlying) {
    super(underlying);
  }
  
  public ArrayData copy() {
    return new NonExtensibleArrayFilter(this.underlying.copy());
  }
  
  public ArrayData slice(long from, long to) {
    return new NonExtensibleArrayFilter(this.underlying.slice(from, to));
  }
  
  private ArrayData extensionCheck(boolean strict, int index) {
    if (!strict)
      return this; 
    throw ECMAErrors.typeError(Global.instance(), "object.non.extensible", new String[] { String.valueOf(index), ScriptRuntime.safeToString(this) });
  }
  
  public ArrayData set(int index, Object value, boolean strict) {
    if (has(index))
      return this.underlying.set(index, value, strict); 
    return extensionCheck(strict, index);
  }
  
  public ArrayData set(int index, int value, boolean strict) {
    if (has(index))
      return this.underlying.set(index, value, strict); 
    return extensionCheck(strict, index);
  }
  
  public ArrayData set(int index, double value, boolean strict) {
    if (has(index))
      return this.underlying.set(index, value, strict); 
    return extensionCheck(strict, index);
  }
}
