package org.openjdk.nashorn.internal.runtime.arrays;

import org.openjdk.nashorn.internal.objects.Global;
import org.openjdk.nashorn.internal.runtime.ECMAErrors;
import org.openjdk.nashorn.internal.runtime.PropertyDescriptor;

class SealedArrayFilter extends ArrayFilter {
  SealedArrayFilter(ArrayData underlying) {
    super(underlying);
  }
  
  public ArrayData copy() {
    return new SealedArrayFilter(this.underlying.copy());
  }
  
  public ArrayData slice(long from, long to) {
    return getUnderlying().slice(from, to);
  }
  
  public boolean canDelete(int index, boolean strict) {
    return canDelete(ArrayIndex.toLongIndex(index), strict);
  }
  
  public boolean canDelete(long longIndex, boolean strict) {
    if (strict)
      throw ECMAErrors.typeError("cant.delete.property", new String[] { Long.toString(longIndex), "sealed array" }); 
    return false;
  }
  
  public PropertyDescriptor getDescriptor(Global global, int index) {
    return global.newDataDescriptor(getObject(index), false, true, true);
  }
}
