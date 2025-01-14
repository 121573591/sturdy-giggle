package org.openjdk.nashorn.internal.runtime;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.SwitchPoint;
import jdk.dynalink.CallSiteDescriptor;
import jdk.dynalink.linker.GuardedInvocation;
import jdk.dynalink.linker.LinkRequest;
import org.openjdk.nashorn.internal.lookup.Lookup;
import org.openjdk.nashorn.internal.objects.Global;
import org.openjdk.nashorn.internal.runtime.linker.NashornCallSiteDescriptor;
import org.openjdk.nashorn.internal.runtime.linker.NashornGuards;

final class SetMethodCreator {
  private final ScriptObject sobj;
  
  private final PropertyMap map;
  
  private final FindProperty find;
  
  private final CallSiteDescriptor desc;
  
  private final Class<?> type;
  
  private final LinkRequest request;
  
  SetMethodCreator(ScriptObject sobj, FindProperty find, CallSiteDescriptor desc, LinkRequest request) {
    this.sobj = sobj;
    this.map = sobj.getMap();
    this.find = find;
    this.desc = desc;
    this.type = desc.getMethodType().parameterType(1);
    this.request = request;
  }
  
  private String getName() {
    return NashornCallSiteDescriptor.getOperand(this.desc);
  }
  
  private PropertyMap getMap() {
    return this.map;
  }
  
  GuardedInvocation createGuardedInvocation(SwitchPoint builtinSwitchPoint) {
    return createSetMethod(builtinSwitchPoint).createGuardedInvocation();
  }
  
  private class SetMethod {
    private final MethodHandle methodHandle;
    
    private final Property property;
    
    SetMethod(MethodHandle methodHandle, Property property) {
      assert methodHandle != null;
      this.methodHandle = methodHandle;
      this.property = property;
    }
    
    GuardedInvocation createGuardedInvocation() {
      boolean explicitInstanceOfCheck = NashornGuards.explicitInstanceOfCheck(SetMethodCreator.this.desc, SetMethodCreator.this.request);
      return new GuardedInvocation(this.methodHandle, NashornGuards.getGuard(SetMethodCreator.this.sobj, this.property, SetMethodCreator.this.desc, explicitInstanceOfCheck), (SwitchPoint)null, 
          explicitInstanceOfCheck ? null : (Class)ClassCastException.class);
    }
  }
  
  private SetMethod createSetMethod(SwitchPoint builtinSwitchPoint) {
    if (this.find != null)
      return createExistingPropertySetter(); 
    checkStrictCreateNewVariable();
    if (this.sobj.isScope())
      return createGlobalPropertySetter(); 
    return createNewPropertySetter(builtinSwitchPoint);
  }
  
  private void checkStrictCreateNewVariable() {
    if (NashornCallSiteDescriptor.isScope(this.desc) && NashornCallSiteDescriptor.isStrict(this.desc))
      throw ECMAErrors.referenceError("not.defined", new String[] { getName() }); 
  }
  
  private SetMethod createExistingPropertySetter() {
    MethodHandle methodHandle, boundHandle;
    Property property = this.find.getProperty();
    boolean isStrict = NashornCallSiteDescriptor.isStrict(this.desc);
    if (NashornCallSiteDescriptor.isDeclaration(this.desc) && property.needsDeclaration()) {
      PropertyMap oldMap = getMap();
      Property newProperty = property.removeFlags(512);
      PropertyMap newMap = oldMap.replaceProperty(property, newProperty);
      MethodHandle fastSetter = this.find.replaceProperty(newProperty).getSetter(this.type, isStrict, this.request);
      MethodHandle slowSetter = Lookup.MH.insertArguments(ScriptObject.DECLARE_AND_SET, 1, new Object[] { getName() }).asType(fastSetter.type());
      MethodHandle casMap = Lookup.MH.insertArguments(ScriptObject.CAS_MAP, 1, new Object[] { oldMap, newMap });
      casMap = Lookup.MH.dropArguments(casMap, 1, new Class[] { this.type });
      casMap = Lookup.MH.asType(casMap, casMap.type().changeParameterType(0, Object.class));
      methodHandle = Lookup.MH.guardWithTest(casMap, fastSetter, slowSetter);
    } else {
      methodHandle = this.find.getSetter(this.type, isStrict, this.request);
    } 
    assert methodHandle != null;
    assert property != null;
    if (this.find.isInheritedOrdinaryProperty()) {
      boundHandle = ScriptObject.addProtoFilter(methodHandle, this.find.getProtoChainLength());
    } else {
      boundHandle = methodHandle;
    } 
    return new SetMethod(boundHandle, property);
  }
  
  private SetMethod createGlobalPropertySetter() {
    Global global = Context.getGlobal();
    return new SetMethod(Lookup.MH.filterArguments(global.addSpill(this.type, getName()), 0, new MethodHandle[] { ScriptObject.GLOBALFILTER }), null);
  }
  
  private SetMethod createNewPropertySetter(SwitchPoint builtinSwitchPoint) {
    SetMethod sm = (this.map.getFreeFieldSlot() > -1) ? createNewFieldSetter(builtinSwitchPoint) : createNewSpillPropertySetter(builtinSwitchPoint);
    this.map.propertyChanged(sm.property);
    return sm;
  }
  
  private SetMethod createNewSetter(Property property, SwitchPoint builtinSwitchPoint) {
    property.setBuiltinSwitchPoint(builtinSwitchPoint);
    PropertyMap oldMap = getMap();
    PropertyMap newMap = getNewMap(property);
    boolean isStrict = NashornCallSiteDescriptor.isStrict(this.desc);
    String name = NashornCallSiteDescriptor.getOperand(this.desc);
    MethodHandle fastSetter = property.getSetter(this.type, newMap);
    MethodHandle slowSetter = ScriptObject.SET_SLOW[JSType.getAccessorTypeIndex(this.type)];
    slowSetter = Lookup.MH.insertArguments(slowSetter, 3, new Object[] { Integer.valueOf(NashornCallSiteDescriptor.getFlags(this.desc)) });
    slowSetter = Lookup.MH.insertArguments(slowSetter, 1, new Object[] { name });
    slowSetter = Lookup.MH.asType(slowSetter, slowSetter.type().changeParameterType(0, Object.class));
    assert slowSetter.type().equals(fastSetter.type()) : "slow=" + slowSetter + " != fast=" + fastSetter;
    MethodHandle casMap = Lookup.MH.insertArguments(ScriptObject.CAS_MAP, 1, new Object[] { oldMap, newMap });
    casMap = Lookup.MH.dropArguments(casMap, 1, new Class[] { this.type });
    casMap = Lookup.MH.asType(casMap, casMap.type().changeParameterType(0, Object.class));
    MethodHandle casGuard = Lookup.MH.guardWithTest(casMap, fastSetter, slowSetter);
    MethodHandle extCheck = Lookup.MH.insertArguments(ScriptObject.EXTENSION_CHECK, 1, new Object[] { Boolean.valueOf(isStrict), name });
    extCheck = Lookup.MH.asType(extCheck, extCheck.type().changeParameterType(0, Object.class));
    extCheck = Lookup.MH.dropArguments(extCheck, 1, new Class[] { this.type });
    MethodHandle nop = JSType.VOID_RETURN.methodHandle();
    nop = Lookup.MH.dropArguments(nop, 0, new Class[] { Object.class, this.type });
    return new SetMethod(Lookup.MH.asType(Lookup.MH.guardWithTest(extCheck, casGuard, nop), fastSetter.type()), property);
  }
  
  private SetMethod createNewFieldSetter(SwitchPoint builtinSwitchPoint) {
    return createNewSetter(new AccessorProperty(getName(), getFlags(this.sobj), this.sobj.getClass(), getMap().getFreeFieldSlot(), this.type), builtinSwitchPoint);
  }
  
  private SetMethod createNewSpillPropertySetter(SwitchPoint builtinSwitchPoint) {
    return createNewSetter(new SpillProperty(getName(), getFlags(this.sobj), getMap().getFreeSpillSlot(), this.type), builtinSwitchPoint);
  }
  
  private PropertyMap getNewMap(Property property) {
    return getMap().addProperty(property);
  }
  
  private static int getFlags(ScriptObject scriptObject) {
    return scriptObject.useDualFields() ? 2048 : 0;
  }
}
