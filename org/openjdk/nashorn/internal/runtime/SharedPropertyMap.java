package org.openjdk.nashorn.internal.runtime;

import java.lang.invoke.SwitchPoint;

public final class SharedPropertyMap extends PropertyMap {
  private SwitchPoint switchPoint;
  
  private static final long serialVersionUID = 2166297719721778876L;
  
  SharedPropertyMap(PropertyMap map) {
    super(map);
    this.switchPoint = new SwitchPoint();
  }
  
  public void propertyChanged(Property property) {
    invalidateSwitchPoint();
    super.propertyChanged(property);
  }
  
  synchronized boolean isValidSharedProtoMap() {
    return (this.switchPoint != null);
  }
  
  synchronized SwitchPoint getSharedProtoSwitchPoint() {
    return this.switchPoint;
  }
  
  synchronized void invalidateSwitchPoint() {
    if (this.switchPoint != null) {
      assert !this.switchPoint.hasBeenInvalidated();
      SwitchPoint.invalidateAll(new SwitchPoint[] { this.switchPoint });
      this.switchPoint = null;
    } 
  }
}
