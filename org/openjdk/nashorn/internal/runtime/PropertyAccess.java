package org.openjdk.nashorn.internal.runtime;

public interface PropertyAccess {
  int getInt(Object paramObject, int paramInt);
  
  int getInt(double paramDouble, int paramInt);
  
  int getInt(int paramInt1, int paramInt2);
  
  double getDouble(Object paramObject, int paramInt);
  
  double getDouble(double paramDouble, int paramInt);
  
  double getDouble(int paramInt1, int paramInt2);
  
  Object get(Object paramObject);
  
  Object get(double paramDouble);
  
  Object get(int paramInt);
  
  void set(Object paramObject, int paramInt1, int paramInt2);
  
  void set(Object paramObject, double paramDouble, int paramInt);
  
  void set(Object paramObject1, Object paramObject2, int paramInt);
  
  void set(double paramDouble, int paramInt1, int paramInt2);
  
  void set(double paramDouble1, double paramDouble2, int paramInt);
  
  void set(double paramDouble, Object paramObject, int paramInt);
  
  void set(int paramInt1, int paramInt2, int paramInt3);
  
  void set(int paramInt1, double paramDouble, int paramInt2);
  
  void set(int paramInt1, Object paramObject, int paramInt2);
  
  boolean has(Object paramObject);
  
  boolean has(int paramInt);
  
  boolean has(double paramDouble);
  
  boolean hasOwnProperty(Object paramObject);
  
  boolean hasOwnProperty(int paramInt);
  
  boolean hasOwnProperty(double paramDouble);
  
  boolean delete(int paramInt, boolean paramBoolean);
  
  boolean delete(double paramDouble, boolean paramBoolean);
  
  boolean delete(Object paramObject, boolean paramBoolean);
}
