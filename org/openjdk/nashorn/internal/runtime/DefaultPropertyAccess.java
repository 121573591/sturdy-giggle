package org.openjdk.nashorn.internal.runtime;

public abstract class DefaultPropertyAccess implements PropertyAccess {
  public int getInt(Object key, int programPoint) {
    return JSType.toInt32(get(key));
  }
  
  public int getInt(double key, int programPoint) {
    return getInt(JSType.toObject(key), programPoint);
  }
  
  public int getInt(int key, int programPoint) {
    return getInt(JSType.toObject(key), programPoint);
  }
  
  public double getDouble(Object key, int programPoint) {
    return JSType.toNumber(get(key));
  }
  
  public double getDouble(double key, int programPoint) {
    return getDouble(JSType.toObject(key), programPoint);
  }
  
  public double getDouble(int key, int programPoint) {
    return getDouble(JSType.toObject(key), programPoint);
  }
  
  public abstract Object get(Object paramObject);
  
  public Object get(double key) {
    return get(JSType.toObject(key));
  }
  
  public Object get(int key) {
    return get(JSType.toObject(key));
  }
  
  public void set(double key, int value, int flags) {
    set(JSType.toObject(key), JSType.toObject(value), flags);
  }
  
  public void set(double key, double value, int flags) {
    set(JSType.toObject(key), JSType.toObject(value), flags);
  }
  
  public void set(double key, Object value, int flags) {
    set(JSType.toObject(key), JSType.toObject(value), flags);
  }
  
  public void set(int key, int value, int flags) {
    set(JSType.toObject(key), JSType.toObject(value), flags);
  }
  
  public void set(int key, double value, int flags) {
    set(JSType.toObject(key), JSType.toObject(value), flags);
  }
  
  public void set(int key, Object value, int flags) {
    set(JSType.toObject(key), value, flags);
  }
  
  public void set(Object key, int value, int flags) {
    set(key, JSType.toObject(value), flags);
  }
  
  public void set(Object key, double value, int flags) {
    set(key, JSType.toObject(value), flags);
  }
  
  public abstract void set(Object paramObject1, Object paramObject2, int paramInt);
  
  public abstract boolean has(Object paramObject);
  
  public boolean has(int key) {
    return has(JSType.toObject(key));
  }
  
  public boolean has(double key) {
    return has(JSType.toObject(key));
  }
  
  public boolean hasOwnProperty(int key) {
    return hasOwnProperty(JSType.toObject(key));
  }
  
  public boolean hasOwnProperty(double key) {
    return hasOwnProperty(JSType.toObject(key));
  }
  
  public abstract boolean hasOwnProperty(Object paramObject);
  
  public boolean delete(int key, boolean strict) {
    return delete(JSType.toObject(key), strict);
  }
  
  public boolean delete(double key, boolean strict) {
    return delete(JSType.toObject(key), strict);
  }
}
