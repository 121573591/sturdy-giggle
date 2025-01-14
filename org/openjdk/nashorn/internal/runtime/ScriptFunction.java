package org.openjdk.nashorn.internal.runtime;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.SwitchPoint;
import java.security.AccessControlContext;
import java.security.AccessController;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.LongAdder;
import jdk.dynalink.CallSiteDescriptor;
import jdk.dynalink.linker.GuardedInvocation;
import jdk.dynalink.linker.LinkRequest;
import jdk.dynalink.linker.support.Guards;
import org.openjdk.nashorn.internal.codegen.ApplySpecialization;
import org.openjdk.nashorn.internal.codegen.Compiler;
import org.openjdk.nashorn.internal.codegen.CompilerConstants;
import org.openjdk.nashorn.internal.lookup.Lookup;
import org.openjdk.nashorn.internal.objects.Global;
import org.openjdk.nashorn.internal.objects.NativeFunction;
import org.openjdk.nashorn.internal.objects.annotations.SpecializedFunction;
import org.openjdk.nashorn.internal.runtime.linker.Bootstrap;
import org.openjdk.nashorn.internal.runtime.linker.NashornCallSiteDescriptor;
import org.openjdk.nashorn.internal.runtime.logging.DebugLogger;

public class ScriptFunction extends ScriptObject {
  public static final MethodHandle G$PROTOTYPE = findOwnMH_S("G$prototype", Object.class, new Class[] { Object.class });
  
  public static final MethodHandle S$PROTOTYPE = findOwnMH_S("S$prototype", void.class, new Class[] { Object.class, Object.class });
  
  public static final MethodHandle G$LENGTH = findOwnMH_S("G$length", int.class, new Class[] { Object.class });
  
  public static final MethodHandle G$NAME = findOwnMH_S("G$name", Object.class, new Class[] { Object.class });
  
  public static final MethodHandle INVOKE_SYNC = findOwnMH_S("invokeSync", Object.class, new Class[] { ScriptFunction.class, Object.class, Object.class, Object[].class });
  
  static final MethodHandle ALLOCATE = findOwnMH_V("allocate", Object.class, new Class[0]);
  
  private static final MethodHandle WRAPFILTER = findOwnMH_S("wrapFilter", Object.class, new Class[] { Object.class });
  
  private static final MethodHandle SCRIPTFUNCTION_GLOBALFILTER = findOwnMH_S("globalFilter", Object.class, new Class[] { Object.class });
  
  public static final CompilerConstants.Call GET_SCOPE = CompilerConstants.virtualCallNoLookup(ScriptFunction.class, "getScope", ScriptObject.class, new Class[0]);
  
  private static final MethodHandle IS_FUNCTION_MH = findOwnMH_S("isFunctionMH", boolean.class, new Class[] { Object.class, ScriptFunctionData.class });
  
  private static final MethodHandle IS_APPLY_FUNCTION = findOwnMH_S("isApplyFunction", boolean.class, new Class[] { boolean.class, Object.class, Object.class });
  
  private static final MethodHandle IS_NONSTRICT_FUNCTION = findOwnMH_S("isNonStrictFunction", boolean.class, new Class[] { Object.class, Object.class, ScriptFunctionData.class });
  
  private static final MethodHandle ADD_ZEROTH_ELEMENT = findOwnMH_S("addZerothElement", Object[].class, new Class[] { Object[].class, Object.class });
  
  private static final MethodHandle WRAP_THIS = Lookup.MH.findStatic(MethodHandles.lookup(), ScriptFunctionData.class, "wrapThis", Lookup.MH.type(Object.class, new Class[] { Object.class }));
  
  private static final PropertyMap anonmap$;
  
  private static final PropertyMap strictmodemap$;
  
  private static final PropertyMap boundfunctionmap$;
  
  private static final PropertyMap map$;
  
  private static final Object LAZY_PROTOTYPE = new Object();
  
  private static final AccessControlContext GET_LOOKUP_PERMISSION_CONTEXT = AccessControlContextFactory.createAccessControlContext(new String[] { "dynalink.getLookup" });
  
  private final ScriptObject scope;
  
  private final ScriptFunctionData data;
  
  protected PropertyMap allocatorMap;
  
  protected Object prototype;
  
  private static LongAdder constructorCount;
  
  private static LongAdder invokes;
  
  private static LongAdder allocations;
  
  private static PropertyMap createStrictModeMap(PropertyMap map) {
    int flags = 6;
    PropertyMap newMap = map;
    newMap = newMap.addPropertyNoHistory(newMap.newUserAccessors("arguments", 6));
    newMap = newMap.addPropertyNoHistory(newMap.newUserAccessors("caller", 6));
    return newMap;
  }
  
  private static PropertyMap createBoundFunctionMap(PropertyMap strictModeMap) {
    return strictModeMap.deleteProperty(strictModeMap.findProperty("prototype"));
  }
  
  static {
    anonmap$ = PropertyMap.newMap();
    ArrayList<Property> properties = new ArrayList<>(3);
    properties.add(AccessorProperty.create("prototype", 6, G$PROTOTYPE, S$PROTOTYPE));
    properties.add(AccessorProperty.create("length", 7, G$LENGTH, null));
    properties.add(AccessorProperty.create("name", 7, G$NAME, null));
    map$ = PropertyMap.newMap(properties);
    strictmodemap$ = createStrictModeMap(map$);
    boundfunctionmap$ = createBoundFunctionMap(strictmodemap$);
    if (Context.DEBUG) {
      constructorCount = new LongAdder();
      invokes = new LongAdder();
      allocations = new LongAdder();
    } 
  }
  
  private static boolean isStrict(int flags) {
    return ((flags & 0x1) != 0);
  }
  
  private static PropertyMap getMap(boolean strict) {
    return strict ? strictmodemap$ : map$;
  }
  
  private ScriptFunction(ScriptFunctionData data, PropertyMap map, ScriptObject scope, Global global) {
    super(map);
    if (Context.DEBUG)
      constructorCount.increment(); 
    this.data = data;
    this.scope = scope;
    setInitialProto(global.getFunctionPrototype());
    this.prototype = LAZY_PROTOTYPE;
    assert this.objectSpill == null;
    if (isStrict() || isBoundFunction()) {
      ScriptFunction typeErrorThrower = global.getTypeErrorThrower();
      initUserAccessors("arguments", typeErrorThrower, typeErrorThrower);
      initUserAccessors("caller", typeErrorThrower, typeErrorThrower);
    } 
  }
  
  private ScriptFunction(String name, MethodHandle methodHandle, PropertyMap map, ScriptObject scope, Specialization[] specs, int flags, Global global) {
    this(new FinalScriptFunctionData(name, methodHandle, specs, flags), map, scope, global);
  }
  
  private ScriptFunction(String name, MethodHandle methodHandle, ScriptObject scope, Specialization[] specs, int flags) {
    this(name, methodHandle, getMap(isStrict(flags)), scope, specs, flags, Global.instance());
  }
  
  protected ScriptFunction(String name, MethodHandle invokeHandle, Specialization[] specs) {
    this(name, invokeHandle, map$, (ScriptObject)null, specs, 6, Global.instance());
  }
  
  protected ScriptFunction(String name, MethodHandle invokeHandle, PropertyMap map, Specialization[] specs) {
    this(name, invokeHandle, map.addAll(map$), (ScriptObject)null, specs, 6, Global.instance());
  }
  
  public static ScriptFunction create(Object[] constants, int index, ScriptObject scope) {
    RecompilableScriptFunctionData data = (RecompilableScriptFunctionData)constants[index];
    return new ScriptFunction(data, getMap(data.isStrict()), scope, Global.instance());
  }
  
  public static ScriptFunction create(Object[] constants, int index) {
    return create(constants, index, (ScriptObject)null);
  }
  
  public static ScriptFunction createAnonymous() {
    return new ScriptFunction("", GlobalFunctions.ANONYMOUS, anonmap$, null);
  }
  
  private static ScriptFunction createBuiltin(String name, MethodHandle methodHandle, Specialization[] specs, int flags) {
    ScriptFunction func = new ScriptFunction(name, methodHandle, null, specs, flags);
    func.setPrototype(ScriptRuntime.UNDEFINED);
    func.deleteOwnProperty(func.getMap().findProperty("prototype"));
    return func;
  }
  
  public static ScriptFunction createBuiltin(String name, MethodHandle methodHandle, Specialization[] specs) {
    return createBuiltin(name, methodHandle, specs, 2);
  }
  
  public static ScriptFunction createBuiltin(String name, MethodHandle methodHandle) {
    return createBuiltin(name, methodHandle, (Specialization[])null);
  }
  
  public static ScriptFunction createStrictBuiltin(String name, MethodHandle methodHandle) {
    return createBuiltin(name, methodHandle, (Specialization[])null, 3);
  }
  
  private static class Bound extends ScriptFunction {
    private final ScriptFunction target;
    
    Bound(ScriptFunctionData boundData, ScriptFunction target) {
      super(boundData, ScriptFunction.boundfunctionmap$, (ScriptObject)null, Global.instance());
      setPrototype(ScriptRuntime.UNDEFINED);
      this.target = target;
    }
    
    protected ScriptFunction getTargetFunction() {
      return this.target;
    }
  }
  
  public final ScriptFunction createBound(Object self, Object[] args) {
    return new Bound(this.data.makeBoundFunctionData(this, self, args), getTargetFunction());
  }
  
  public final ScriptFunction createSynchronized(Object sync) {
    MethodHandle mh = Lookup.MH.insertArguments(INVOKE_SYNC, 0, new Object[] { this, sync });
    return createBuiltin(getName(), mh);
  }
  
  public String getClassName() {
    return "Function";
  }
  
  public boolean isInstance(ScriptObject instance) {
    Object basePrototype = getTargetFunction().getPrototype();
    if (!(basePrototype instanceof ScriptObject))
      throw ECMAErrors.typeError("prototype.not.an.object", new String[] { ScriptRuntime.safeToString(getTargetFunction()), ScriptRuntime.safeToString(basePrototype) }); 
    for (ScriptObject proto = instance.getProto(); proto != null; proto = proto.getProto()) {
      if (proto == basePrototype)
        return true; 
    } 
    return false;
  }
  
  protected ScriptFunction getTargetFunction() {
    return this;
  }
  
  final boolean isBoundFunction() {
    return (getTargetFunction() != this);
  }
  
  public final void setArity(int arity) {
    this.data.setArity(arity);
  }
  
  public final boolean isStrict() {
    return this.data.isStrict();
  }
  
  public boolean hasAllVarsInScope() {
    return (this.data instanceof RecompilableScriptFunctionData && (((RecompilableScriptFunctionData)this.data).getFunctionFlags() & 0x60) != 0);
  }
  
  public final boolean needsWrappedThis() {
    return this.data.needsWrappedThis();
  }
  
  private static boolean needsWrappedThis(Object fn) {
    return (fn instanceof ScriptFunction && ((ScriptFunction)fn).needsWrappedThis());
  }
  
  final Object invoke(Object self, Object... arguments) throws Throwable {
    if (Context.DEBUG)
      invokes.increment(); 
    return this.data.invoke(this, self, arguments);
  }
  
  final Object construct(Object... arguments) throws Throwable {
    return this.data.construct(this, arguments);
  }
  
  private Object allocate() {
    if (Context.DEBUG)
      allocations.increment(); 
    assert !isBoundFunction();
    ScriptObject prototype = getAllocatorPrototype();
    ScriptObject object = this.data.allocate(getAllocatorMap(prototype));
    if (object != null)
      object.setInitialProto(prototype); 
    return object;
  }
  
  private PropertyMap getAllocatorMap(ScriptObject prototype) {
    if (this.allocatorMap == null || this.allocatorMap.isInvalidSharedMapFor(prototype))
      this.allocatorMap = this.data.getAllocatorMap(prototype); 
    return this.allocatorMap;
  }
  
  private ScriptObject getAllocatorPrototype() {
    Object prototype = getPrototype();
    if (prototype instanceof ScriptObject)
      return (ScriptObject)prototype; 
    return Global.objectPrototype();
  }
  
  public final String safeToString() {
    return toSource();
  }
  
  public final String toString() {
    return this.data.toString();
  }
  
  public final String toSource() {
    return this.data.toSource();
  }
  
  public final Object getPrototype() {
    if (this.prototype == LAZY_PROTOTYPE)
      this.prototype = new PrototypeObject(this); 
    return this.prototype;
  }
  
  public final void setPrototype(Object newPrototype) {
    if (newPrototype instanceof ScriptObject && newPrototype != this.prototype && this.allocatorMap != null)
      this.allocatorMap = null; 
    this.prototype = newPrototype;
  }
  
  public final MethodHandle getBoundInvokeHandle(Object self) {
    return Lookup.MH.bindTo(bindToCalleeIfNeeded(this.data.getGenericInvoker(this.scope)), self);
  }
  
  private MethodHandle bindToCalleeIfNeeded(MethodHandle methodHandle) {
    return ScriptFunctionData.needsCallee(methodHandle) ? Lookup.MH.bindTo(methodHandle, this) : methodHandle;
  }
  
  public final String getDocumentation() {
    return this.data.getDocumentation();
  }
  
  public final String getDocumentationKey() {
    return this.data.getDocumentationKey();
  }
  
  public final void setDocumentationKey(String docKey) {
    this.data.setDocumentationKey(docKey);
  }
  
  public final String getName() {
    return this.data.getName();
  }
  
  public final ScriptObject getScope() {
    return this.scope;
  }
  
  public static Object G$prototype(Object self) {
    return (self instanceof ScriptFunction) ? ((ScriptFunction)self).getPrototype() : ScriptRuntime.UNDEFINED;
  }
  
  public static void S$prototype(Object self, Object prototype) {
    if (self instanceof ScriptFunction)
      ((ScriptFunction)self).setPrototype(prototype); 
  }
  
  public static int G$length(Object self) {
    if (self instanceof ScriptFunction)
      return ((ScriptFunction)self).data.getArity(); 
    return 0;
  }
  
  public static Object G$name(Object self) {
    if (self instanceof ScriptFunction)
      return ((ScriptFunction)self).getName(); 
    return ScriptRuntime.UNDEFINED;
  }
  
  public static ScriptObject getPrototype(ScriptFunction constructor) {
    if (constructor != null) {
      Object proto = constructor.getPrototype();
      if (proto instanceof ScriptObject)
        return (ScriptObject)proto; 
    } 
    return null;
  }
  
  public static long getConstructorCount() {
    return constructorCount.longValue();
  }
  
  public static long getInvokes() {
    return invokes.longValue();
  }
  
  public static long getAllocations() {
    return allocations.longValue();
  }
  
  protected GuardedInvocation findNewMethod(CallSiteDescriptor desc, LinkRequest request) {
    MethodType type = desc.getMethodType();
    assert desc.getMethodType().returnType() == Object.class && !NashornCallSiteDescriptor.isOptimistic(desc);
    CompiledFunction cf = this.data.getBestConstructor(type, this.scope, CompiledFunction.NO_FUNCTIONS);
    GuardedInvocation bestCtorInv = cf.createConstructorInvocation();
    return new GuardedInvocation(pairArguments(bestCtorInv.getInvocation(), type), getFunctionGuard(this), bestCtorInv.getSwitchPoints(), null);
  }
  
  private static Object wrapFilter(Object obj) {
    if (obj instanceof ScriptObject || !ScriptFunctionData.isPrimitiveThis(obj))
      return obj; 
    return Context.getGlobal().wrapAsObject(obj);
  }
  
  private static Object globalFilter(Object object) {
    return Context.getGlobal();
  }
  
  private static SpecializedFunction.LinkLogic getLinkLogic(Object self, Class<? extends SpecializedFunction.LinkLogic> linkLogicClass) {
    if (linkLogicClass == null)
      return SpecializedFunction.LinkLogic.EMPTY_INSTANCE; 
    if (!(Context.getContextTrusted().getEnv())._optimistic_types)
      return null; 
    Object wrappedSelf = wrapFilter(self);
    if (wrappedSelf instanceof OptimisticBuiltins) {
      if (wrappedSelf != self && ((OptimisticBuiltins)wrappedSelf).hasPerInstanceAssumptions())
        return null; 
      return ((OptimisticBuiltins)wrappedSelf).getLinkLogic(linkLogicClass);
    } 
    return null;
  }
  
  protected GuardedInvocation findCallMethod(CallSiteDescriptor desc, LinkRequest request) {
    MethodType type = desc.getMethodType();
    String name = getName();
    boolean isUnstable = request.isCallSiteUnstable();
    boolean scopeCall = NashornCallSiteDescriptor.isScope(desc);
    boolean isCall = (!scopeCall && this.data.isBuiltin() && "call".equals(name));
    boolean isApply = (!scopeCall && this.data.isBuiltin() && "apply".equals(name));
    boolean isApplyOrCall = isCall | isApply;
    if (isUnstable && !isApplyOrCall) {
      MethodHandle handle;
      if (type.parameterCount() == 3 && type.parameterType(2) == Object[].class) {
        handle = ScriptRuntime.APPLY.methodHandle();
      } else {
        handle = Lookup.MH.asCollector(ScriptRuntime.APPLY.methodHandle(), Object[].class, type.parameterCount() - 2);
      } 
      return new GuardedInvocation(handle, null, (SwitchPoint)null, (Class)ClassCastException.class);
    } 
    MethodHandle guard = null;
    if (isApplyOrCall && !isUnstable) {
      Object[] args = request.getArguments();
      if (Bootstrap.isCallable(args[1]))
        return createApplyOrCallCall(isApply, desc, request, args); 
    } 
    int programPoint = -1;
    if (NashornCallSiteDescriptor.isOptimistic(desc))
      programPoint = NashornCallSiteDescriptor.getProgramPoint(desc); 
    CompiledFunction cf = this.data.getBestInvoker(type, this.scope, CompiledFunction.NO_FUNCTIONS);
    Object self = request.getArguments()[1];
    Collection<CompiledFunction> forbidden = new HashSet<>();
    List<SwitchPoint> sps = new ArrayList<>();
    Class<? extends Throwable> exceptionGuard = null;
    while (cf.isSpecialization()) {
      Class<? extends SpecializedFunction.LinkLogic> linkLogicClass = cf.getLinkLogicClass();
      SpecializedFunction.LinkLogic linkLogic = getLinkLogic(self, linkLogicClass);
      if (linkLogic != null && linkLogic.checkLinkable(self, desc, request)) {
        DebugLogger log = Context.getContextTrusted().getLogger((Class)Compiler.class);
        if (log.isEnabled())
          log.info(new Object[] { "Linking optimistic builtin function: '", name, "' args=", Arrays.toString(request.getArguments()), " desc=", desc }); 
        exceptionGuard = linkLogic.getRelinkException();
        break;
      } 
      forbidden.add(cf);
      CompiledFunction oldCf = cf;
      cf = this.data.getBestInvoker(type, this.scope, forbidden);
      assert oldCf != cf;
    } 
    GuardedInvocation bestInvoker = cf.createFunctionInvocation(type.returnType(), programPoint);
    MethodHandle callHandle = bestInvoker.getInvocation();
    if (this.data.needsCallee()) {
      if (scopeCall && needsWrappedThis()) {
        boundHandle = Lookup.MH.filterArguments(callHandle, 1, new MethodHandle[] { SCRIPTFUNCTION_GLOBALFILTER });
      } else {
        boundHandle = callHandle;
      } 
    } else if (this.data.isBuiltin() && Global.isBuiltInJavaExtend(this)) {
      boundHandle = Lookup.MH.dropArguments(Lookup.MH.bindTo(callHandle, getLookupPrivileged(desc)), 0, new Class[] { type.parameterType(0), type.parameterType(1) });
    } else if (this.data.isBuiltin() && Global.isBuiltInJavaTo(this)) {
      boundHandle = Lookup.MH.dropArguments(Lookup.MH.bindTo(callHandle, desc), 0, new Class[] { type.parameterType(0), type.parameterType(1) });
    } else if (scopeCall && needsWrappedThis()) {
      boundHandle = Lookup.MH.filterArguments(callHandle, 0, new MethodHandle[] { SCRIPTFUNCTION_GLOBALFILTER });
      boundHandle = Lookup.MH.dropArguments(boundHandle, 0, new Class[] { type.parameterType(0) });
    } else {
      boundHandle = Lookup.MH.dropArguments(callHandle, 0, new Class[] { type.parameterType(0) });
    } 
    if (!scopeCall && needsWrappedThis())
      if (ScriptFunctionData.isPrimitiveThis(request.getArguments()[1])) {
        boundHandle = Lookup.MH.filterArguments(boundHandle, 1, new MethodHandle[] { WRAPFILTER });
      } else {
        guard = getNonStrictFunctionGuard(this);
      }  
    if (isUnstable && NashornCallSiteDescriptor.isApplyToCall(desc))
      boundHandle = Lookup.MH.asCollector(boundHandle, Object[].class, type.parameterCount() - 2); 
    MethodHandle boundHandle = pairArguments(boundHandle, type);
    if (bestInvoker.getSwitchPoints() != null)
      sps.addAll(Arrays.asList(bestInvoker.getSwitchPoints())); 
    SwitchPoint[] spsArray = sps.isEmpty() ? null : sps.<SwitchPoint>toArray(new SwitchPoint[0]);
    return new GuardedInvocation(boundHandle, 
        
        (guard == null) ? 
        getFunctionGuard(this) : 
        
        guard, spsArray, exceptionGuard);
  }
  
  private static MethodHandles.Lookup getLookupPrivileged(CallSiteDescriptor desc) {
    Objects.requireNonNull(desc);
    return AccessController.<MethodHandles.Lookup>doPrivileged(desc::getLookup, GET_LOOKUP_PERMISSION_CONTEXT);
  }
  
  private GuardedInvocation createApplyOrCallCall(boolean isApply, CallSiteDescriptor desc, LinkRequest request, Object[] args) {
    GuardedInvocation appliedInvocation;
    MethodType descType = desc.getMethodType();
    int paramCount = descType.parameterCount();
    if (descType.parameterType(paramCount - 1).isArray())
      return createVarArgApplyOrCallCall(isApply, desc, request, args); 
    boolean passesThis = (paramCount > 2);
    boolean passesArgs = (paramCount > 3);
    int realArgCount = passesArgs ? (paramCount - 3) : 0;
    Object appliedFn = args[1];
    boolean appliedFnNeedsWrappedThis = needsWrappedThis(appliedFn);
    CallSiteDescriptor appliedDesc = desc;
    SwitchPoint applyToCallSwitchPoint = Global.getBuiltinFunctionApplySwitchPoint();
    boolean isApplyToCall = NashornCallSiteDescriptor.isApplyToCall(desc);
    boolean isFailedApplyToCall = (isApplyToCall && applyToCallSwitchPoint.hasBeenInvalidated());
    MethodType appliedType = descType.dropParameterTypes(0, 1);
    if (!passesThis) {
      appliedType = appliedType.insertParameterTypes(1, new Class[] { Object.class });
    } else if (appliedFnNeedsWrappedThis) {
      appliedType = appliedType.changeParameterType(1, Object.class);
    } 
    MethodType dropArgs = Lookup.MH.type(void.class, new Class[0]);
    if (isApply && !isFailedApplyToCall) {
      int pc = appliedType.parameterCount();
      for (int j = 3; j < pc; j++) {
        dropArgs = dropArgs.appendParameterTypes(new Class[] { appliedType.parameterType(j) });
      } 
      if (pc > 3)
        appliedType = appliedType.dropParameterTypes(3, pc); 
    } 
    if (isApply || isFailedApplyToCall)
      if (passesArgs) {
        appliedType = appliedType.changeParameterType(2, Object[].class);
        if (isFailedApplyToCall)
          appliedType = appliedType.dropParameterTypes(3, paramCount - 1); 
      } else {
        appliedType = appliedType.insertParameterTypes(2, new Class[] { Object[].class });
      }  
    appliedDesc = appliedDesc.changeMethodType(appliedType);
    Object[] appliedArgs = new Object[isApply ? 3 : appliedType.parameterCount()];
    appliedArgs[0] = appliedFn;
    appliedArgs[1] = passesThis ? (appliedFnNeedsWrappedThis ? ScriptFunctionData.wrapThis(args[2]) : args[2]) : ScriptRuntime.UNDEFINED;
    if (isApply && !isFailedApplyToCall) {
      appliedArgs[2] = passesArgs ? NativeFunction.toApplyArgs(args[3]) : ScriptRuntime.EMPTY_ARRAY;
    } else if (passesArgs) {
      if (isFailedApplyToCall) {
        Object[] tmp = new Object[args.length - 3];
        System.arraycopy(args, 3, tmp, 0, tmp.length);
        appliedArgs[2] = NativeFunction.toApplyArgs(tmp);
      } else {
        assert !isApply;
        System.arraycopy(args, 3, appliedArgs, 2, args.length - 3);
      } 
    } else if (isFailedApplyToCall) {
      appliedArgs[2] = ScriptRuntime.EMPTY_ARRAY;
    } 
    LinkRequest appliedRequest = request.replaceArguments(appliedDesc, appliedArgs);
    try {
      appliedInvocation = Bootstrap.getLinkerServices().getGuardedInvocation(appliedRequest);
    } catch (RuntimeException|Error e) {
      throw e;
    } catch (Exception e) {
      throw new RuntimeException(e);
    } 
    assert appliedRequest != null;
    Class<?> applyFnType = descType.parameterType(0);
    MethodHandle inv = appliedInvocation.getInvocation();
    MethodHandle guard = appliedInvocation.getGuard();
    if (isApply && !isFailedApplyToCall)
      if (passesArgs) {
        inv = Lookup.MH.filterArguments(inv, 2, new MethodHandle[] { NativeFunction.TO_APPLY_ARGS });
        if (guard.type().parameterCount() > 2)
          guard = Lookup.MH.filterArguments(guard, 2, new MethodHandle[] { NativeFunction.TO_APPLY_ARGS }); 
      } else {
        inv = Lookup.MH.insertArguments(inv, 2, new Object[] { ScriptRuntime.EMPTY_ARRAY });
      }  
    if (isApplyToCall)
      if (isFailedApplyToCall) {
        Context.getContextTrusted().getLogger((Class)ApplySpecialization.class).info("Collection arguments to revert call to apply in " + appliedFn);
        inv = Lookup.MH.asCollector(inv, Object[].class, realArgCount);
      } else {
        appliedInvocation = appliedInvocation.addSwitchPoint(applyToCallSwitchPoint);
      }  
    if (!passesThis) {
      inv = bindImplicitThis(appliedFnNeedsWrappedThis, inv);
      if (guard.type().parameterCount() > 1)
        guard = bindImplicitThis(appliedFnNeedsWrappedThis, guard); 
    } else if (appliedFnNeedsWrappedThis) {
      inv = Lookup.MH.filterArguments(inv, 1, new MethodHandle[] { WRAP_THIS });
      if (guard.type().parameterCount() > 1)
        guard = Lookup.MH.filterArguments(guard, 1, new MethodHandle[] { WRAP_THIS }); 
    } 
    MethodType guardType = guard.type();
    inv = Lookup.MH.dropArguments(inv, 0, new Class[] { applyFnType });
    guard = Lookup.MH.dropArguments(guard, 0, new Class[] { applyFnType });
    for (int i = 0; i < dropArgs.parameterCount(); i++) {
      inv = Lookup.MH.dropArguments(inv, 4 + i, new Class[] { dropArgs.parameterType(i) });
    } 
    MethodHandle applyFnGuard = Lookup.MH.insertArguments(IS_APPLY_FUNCTION, 2, new Object[] { this });
    applyFnGuard = Lookup.MH.dropArguments(applyFnGuard, 2, guardType.parameterArray());
    guard = Lookup.MH.foldArguments(applyFnGuard, guard);
    return appliedInvocation.replaceMethods(inv, guard);
  }
  
  private GuardedInvocation createVarArgApplyOrCallCall(boolean isApply, CallSiteDescriptor desc, LinkRequest request, Object[] args) {
    MethodType descType = desc.getMethodType();
    int paramCount = descType.parameterCount();
    Object[] varArgs = (Object[])args[paramCount - 1];
    int copiedArgCount = args.length - 1;
    int varArgCount = varArgs.length;
    Object[] spreadArgs = new Object[copiedArgCount + varArgCount];
    System.arraycopy(args, 0, spreadArgs, 0, copiedArgCount);
    System.arraycopy(varArgs, 0, spreadArgs, copiedArgCount, varArgCount);
    MethodType spreadType = descType.dropParameterTypes(paramCount - 1, paramCount).appendParameterTypes(
        Collections.nCopies(varArgCount, Object.class));
    CallSiteDescriptor spreadDesc = desc.changeMethodType(spreadType);
    LinkRequest spreadRequest = request.replaceArguments(spreadDesc, spreadArgs);
    GuardedInvocation spreadInvocation = createApplyOrCallCall(isApply, spreadDesc, spreadRequest, spreadArgs);
    return spreadInvocation.replaceMethods(
        
        pairArguments(spreadInvocation.getInvocation(), descType), 
        
        spreadGuardArguments(spreadInvocation.getGuard(), descType));
  }
  
  private static MethodHandle spreadGuardArguments(MethodHandle guard, MethodType descType) {
    MethodHandle arrayConvertingGuard;
    MethodType guardType = guard.type();
    int guardParamCount = guardType.parameterCount();
    int descParamCount = descType.parameterCount();
    int spreadCount = guardParamCount - descParamCount + 1;
    if (spreadCount <= 0)
      return guard; 
    if (guardType.parameterType(guardParamCount - 1).isArray()) {
      arrayConvertingGuard = Lookup.MH.filterArguments(guard, guardParamCount - 1, new MethodHandle[] { NativeFunction.TO_APPLY_ARGS });
    } else {
      arrayConvertingGuard = guard;
    } 
    return ScriptObject.adaptHandleToVarArgCallSite(arrayConvertingGuard, descParamCount);
  }
  
  private static MethodHandle bindImplicitThis(boolean needsWrappedThis, MethodHandle mh) {
    MethodHandle bound;
    if (needsWrappedThis) {
      bound = Lookup.MH.filterArguments(mh, 1, new MethodHandle[] { SCRIPTFUNCTION_GLOBALFILTER });
    } else {
      bound = mh;
    } 
    return Lookup.MH.insertArguments(bound, 1, new Object[] { ScriptRuntime.UNDEFINED });
  }
  
  MethodHandle getCallMethodHandle(MethodType type, String bindName) {
    return pairArguments(bindToNameIfNeeded(bindToCalleeIfNeeded(this.data.getGenericInvoker(this.scope)), bindName), type);
  }
  
  private static MethodHandle bindToNameIfNeeded(MethodHandle methodHandle, String bindName) {
    if (bindName == null)
      return methodHandle; 
    MethodType methodType = methodHandle.type();
    int parameterCount = methodType.parameterCount();
    if (parameterCount < 2)
      return methodHandle; 
    boolean isVarArg = methodType.parameterType(parameterCount - 1).isArray();
    if (isVarArg)
      return Lookup.MH.filterArguments(methodHandle, 1, new MethodHandle[] { Lookup.MH.insertArguments(ADD_ZEROTH_ELEMENT, 1, new Object[] { bindName }) }); 
    return Lookup.MH.insertArguments(methodHandle, 1, new Object[] { bindName });
  }
  
  private static MethodHandle getFunctionGuard(ScriptFunction function) {
    assert function.data != null;
    if (function.data.isBuiltin())
      return Guards.getIdentityGuard(function); 
    return Lookup.MH.insertArguments(IS_FUNCTION_MH, 1, new Object[] { function.data });
  }
  
  private static MethodHandle getNonStrictFunctionGuard(ScriptFunction function) {
    assert function.data != null;
    return Lookup.MH.insertArguments(IS_NONSTRICT_FUNCTION, 2, new Object[] { function.data });
  }
  
  private static boolean isFunctionMH(Object self, ScriptFunctionData data) {
    return (self instanceof ScriptFunction && ((ScriptFunction)self).data == data);
  }
  
  private static boolean isNonStrictFunction(Object self, Object arg, ScriptFunctionData data) {
    return (self instanceof ScriptFunction && ((ScriptFunction)self).data == data && arg instanceof ScriptObject);
  }
  
  private static boolean isApplyFunction(boolean appliedFnCondition, Object self, Object expectedSelf) {
    return (appliedFnCondition && self == expectedSelf);
  }
  
  private static Object[] addZerothElement(Object[] args, Object value) {
    Object[] src = (args == null) ? ScriptRuntime.EMPTY_ARRAY : args;
    Object[] result = new Object[src.length + 1];
    System.arraycopy(src, 0, result, 1, src.length);
    result[0] = value;
    return result;
  }
  
  private static Object invokeSync(ScriptFunction func, Object sync, Object self, Object... args) throws Throwable {
    Object syncObj = (sync == ScriptRuntime.UNDEFINED) ? self : sync;
    synchronized (syncObj) {
      return func.invoke(self, args);
    } 
  }
  
  private static MethodHandle findOwnMH_S(String name, Class<?> rtype, Class<?>... types) {
    return Lookup.MH.findStatic(MethodHandles.lookup(), ScriptFunction.class, name, Lookup.MH.type(rtype, types));
  }
  
  private static MethodHandle findOwnMH_V(String name, Class<?> rtype, Class<?>... types) {
    return Lookup.MH.findVirtual(MethodHandles.lookup(), ScriptFunction.class, name, Lookup.MH.type(rtype, types));
  }
}
