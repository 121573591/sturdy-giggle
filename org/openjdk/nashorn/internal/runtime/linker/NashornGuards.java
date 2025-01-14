package org.openjdk.nashorn.internal.runtime.linker;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.ref.WeakReference;
import jdk.dynalink.CallSiteDescriptor;
import jdk.dynalink.linker.LinkRequest;
import org.openjdk.nashorn.internal.lookup.Lookup;
import org.openjdk.nashorn.internal.runtime.JSType;
import org.openjdk.nashorn.internal.runtime.Property;
import org.openjdk.nashorn.internal.runtime.PropertyMap;
import org.openjdk.nashorn.internal.runtime.ScriptObject;
import org.openjdk.nashorn.internal.runtime.options.Options;

public final class NashornGuards {
  private static final MethodHandle IS_MAP = findOwnMH("isMap", boolean.class, new Class[] { ScriptObject.class, PropertyMap.class });
  
  private static final MethodHandle IS_MAP_SCRIPTOBJECT = findOwnMH("isMap", boolean.class, new Class[] { Object.class, PropertyMap.class });
  
  private static final MethodHandle IS_SCRIPTOBJECT = findOwnMH("isScriptObject", boolean.class, new Class[] { Object.class });
  
  private static final MethodHandle IS_NOT_JSOBJECT = findOwnMH("isNotJSObject", boolean.class, new Class[] { Object.class });
  
  private static final MethodHandle SAME_OBJECT = findOwnMH("sameObject", boolean.class, new Class[] { Object.class, WeakReference.class });
  
  private static final boolean CCE_ONLY = Options.getBooleanProperty("nashorn.cce");
  
  public static boolean explicitInstanceOfCheck(CallSiteDescriptor desc, LinkRequest request) {
    return !CCE_ONLY;
  }
  
  public static MethodHandle getScriptObjectGuard() {
    return IS_SCRIPTOBJECT;
  }
  
  public static MethodHandle getNotJSObjectGuard() {
    return IS_NOT_JSOBJECT;
  }
  
  public static MethodHandle getScriptObjectGuard(boolean explicitInstanceOfCheck) {
    return explicitInstanceOfCheck ? IS_SCRIPTOBJECT : null;
  }
  
  public static MethodHandle getMapGuard(PropertyMap map, boolean explicitInstanceOfCheck) {
    return Lookup.MH.insertArguments(explicitInstanceOfCheck ? IS_MAP_SCRIPTOBJECT : IS_MAP, 1, new Object[] { map });
  }
  
  static boolean needsGuard(Property property, CallSiteDescriptor desc) {
    return (property == null || property.isConfigurable() || property
      .isBound() || property.hasDualFields() || 
      !NashornCallSiteDescriptor.isFastScope(desc) || property.canChangeType());
  }
  
  public static MethodHandle getGuard(ScriptObject sobj, Property property, CallSiteDescriptor desc, boolean explicitInstanceOfCheck) {
    if (!needsGuard(property, desc))
      return null; 
    if (NashornCallSiteDescriptor.isScope(desc) && sobj.isScope()) {
      if (property != null && property.isBound() && !property.canChangeType())
        return getIdentityGuard(sobj); 
      if (!(sobj instanceof org.openjdk.nashorn.internal.objects.Global) && (property == null || property.isConfigurable()))
        return combineGuards(getIdentityGuard(sobj), getMapGuard(sobj.getMap(), explicitInstanceOfCheck)); 
    } 
    return getMapGuard(sobj.getMap(), explicitInstanceOfCheck);
  }
  
  public static MethodHandle getIdentityGuard(ScriptObject sobj) {
    return Lookup.MH.insertArguments(SAME_OBJECT, 1, new Object[] { new WeakReference<>(sobj) });
  }
  
  public static MethodHandle getStringGuard() {
    return JSType.IS_STRING.methodHandle();
  }
  
  public static MethodHandle getNumberGuard() {
    return JSType.IS_NUMBER.methodHandle();
  }
  
  public static MethodHandle combineGuards(MethodHandle guard1, MethodHandle guard2) {
    if (guard1 == null)
      return guard2; 
    if (guard2 == null)
      return guard1; 
    return Lookup.MH.guardWithTest(guard1, guard2, Lookup.MH.dropArguments(Lookup.MH.constant(boolean.class, Boolean.valueOf(false)), 0, new Class[] { Object.class }));
  }
  
  private static boolean isScriptObject(Object self) {
    return self instanceof ScriptObject;
  }
  
  private static boolean isScriptObject(Class<? extends ScriptObject> clazz, Object self) {
    return clazz.isInstance(self);
  }
  
  private static boolean isMap(ScriptObject self, PropertyMap map) {
    return (self.getMap() == map);
  }
  
  private static boolean isNotJSObject(Object self) {
    return !(self instanceof org.openjdk.nashorn.api.scripting.JSObject);
  }
  
  private static boolean isMap(Object self, PropertyMap map) {
    return (self instanceof ScriptObject && ((ScriptObject)self).getMap() == map);
  }
  
  private static boolean sameObject(Object self, WeakReference<ScriptObject> ref) {
    return (self == ref.get());
  }
  
  private static boolean isScriptFunction(Object self) {
    return self instanceof org.openjdk.nashorn.internal.runtime.ScriptFunction;
  }
  
  private static MethodHandle findOwnMH(String name, Class<?> rtype, Class<?>... types) {
    return Lookup.MH.findStatic(MethodHandles.lookup(), NashornGuards.class, name, Lookup.MH.type(rtype, types));
  }
}
