package org.openjdk.nashorn.internal.runtime.linker;

import java.lang.invoke.MethodHandle;

public final class InvokeByName {
  private final String name;
  
  private final MethodHandle getter;
  
  private final MethodHandle invoker;
  
  public InvokeByName(String name, Class<?> targetClass) {
    this(name, targetClass, Object.class, new Class[0]);
  }
  
  public InvokeByName(String name, Class<?> targetClass, Class<?> rtype, Class<?>... ptypes) {
    Class<?>[] finalPtypes;
    this.name = name;
    this.getter = Bootstrap.createDynamicInvoker(name, 2, Object.class, new Class[] { targetClass });
    int plength = ptypes.length;
    if (plength == 0) {
      finalPtypes = new Class[] { Object.class, targetClass };
    } else {
      finalPtypes = new Class[plength + 2];
      finalPtypes[0] = Object.class;
      finalPtypes[1] = targetClass;
      System.arraycopy(ptypes, 0, finalPtypes, 2, plength);
    } 
    this.invoker = Bootstrap.createDynamicCallInvoker(rtype, finalPtypes);
  }
  
  public String getName() {
    return this.name;
  }
  
  public MethodHandle getGetter() {
    return this.getter;
  }
  
  public MethodHandle getInvoker() {
    return this.invoker;
  }
}
