package org.openjdk.nashorn.internal.runtime;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import org.openjdk.nashorn.internal.lookup.Lookup;
import org.openjdk.nashorn.internal.objects.Global;

public class PrototypeObject extends ScriptObject {
  private static final PropertyMap map$;
  
  private Object constructor;
  
  private static final MethodHandle GET_CONSTRUCTOR = findOwnMH("getConstructor", Object.class, new Class[] { Object.class });
  
  private static final MethodHandle SET_CONSTRUCTOR = findOwnMH("setConstructor", void.class, new Class[] { Object.class, Object.class });
  
  static {
    ArrayList<Property> properties = new ArrayList<>(1);
    properties.add(AccessorProperty.create("constructor", 2, GET_CONSTRUCTOR, SET_CONSTRUCTOR));
    map$ = PropertyMap.newMap(properties);
  }
  
  private PrototypeObject(Global global, PropertyMap map) {
    super(global.getObjectPrototype(), (map != map$) ? map.addAll(map$) : map$);
  }
  
  protected PrototypeObject() {
    this(Global.instance(), map$);
  }
  
  protected PrototypeObject(PropertyMap map) {
    this(Global.instance(), map);
  }
  
  protected PrototypeObject(ScriptFunction func) {
    this(Global.instance(), map$);
    this.constructor = func;
  }
  
  public static Object getConstructor(Object self) {
    return (self instanceof PrototypeObject) ? (
      (PrototypeObject)self).getConstructor() : 
      ScriptRuntime.UNDEFINED;
  }
  
  public static void setConstructor(Object self, Object constructor) {
    if (self instanceof PrototypeObject)
      ((PrototypeObject)self).setConstructor(constructor); 
  }
  
  private Object getConstructor() {
    return this.constructor;
  }
  
  private void setConstructor(Object constructor) {
    this.constructor = constructor;
  }
  
  private static MethodHandle findOwnMH(String name, Class<?> rtype, Class<?>... types) {
    return Lookup.MH.findStatic(MethodHandles.lookup(), PrototypeObject.class, name, Lookup.MH.type(rtype, types));
  }
}
