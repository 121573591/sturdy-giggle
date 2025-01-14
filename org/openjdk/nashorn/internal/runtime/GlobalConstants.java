package org.openjdk.nashorn.internal.runtime;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.SwitchPoint;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import jdk.dynalink.CallSiteDescriptor;
import jdk.dynalink.DynamicLinker;
import jdk.dynalink.linker.GuardedInvocation;
import jdk.dynalink.linker.LinkRequest;
import org.openjdk.nashorn.internal.codegen.CompilerConstants;
import org.openjdk.nashorn.internal.lookup.Lookup;
import org.openjdk.nashorn.internal.lookup.MethodHandleFactory;
import org.openjdk.nashorn.internal.runtime.linker.NashornCallSiteDescriptor;
import org.openjdk.nashorn.internal.runtime.logging.DebugLogger;
import org.openjdk.nashorn.internal.runtime.logging.Loggable;
import org.openjdk.nashorn.internal.runtime.logging.Logger;

@Logger(name = "const")
public final class GlobalConstants implements Loggable {
  public static final boolean GLOBAL_ONLY = true;
  
  private static final MethodHandles.Lookup LOOKUP = MethodHandles.lookup();
  
  private static final MethodHandle INVALIDATE_SP = CompilerConstants.virtualCall(LOOKUP, GlobalConstants.class, "invalidateSwitchPoint", Object.class, new Class[] { Object.class, Access.class }).methodHandle();
  
  private static final MethodHandle RECEIVER_GUARD = CompilerConstants.staticCall(LOOKUP, GlobalConstants.class, "receiverGuard", boolean.class, new Class[] { Access.class, Object.class, Object.class }).methodHandle();
  
  private final DebugLogger log;
  
  private final Map<Object, Access> map = new HashMap<>();
  
  private final AtomicBoolean invalidatedForever = new AtomicBoolean(false);
  
  public GlobalConstants(DebugLogger log) {
    this.log = (log == null) ? DebugLogger.DISABLED_LOGGER : log;
  }
  
  public DebugLogger getLogger() {
    return this.log;
  }
  
  public DebugLogger initLogger(Context context) {
    return DebugLogger.DISABLED_LOGGER;
  }
  
  private static class Access {
    private final String name;
    
    private SwitchPoint sp;
    
    private int invalidations;
    
    private boolean guardFailed;
    
    private static final int MAX_RETRIES = 2;
    
    private Access(String name, SwitchPoint sp) {
      this.name = name;
      this.sp = sp;
    }
    
    private boolean hasBeenInvalidated() {
      return this.sp.hasBeenInvalidated();
    }
    
    private boolean guardFailed() {
      return this.guardFailed;
    }
    
    private void failGuard() {
      invalidateOnce();
      this.guardFailed = true;
    }
    
    private void newSwitchPoint() {
      assert hasBeenInvalidated();
      this.sp = new SwitchPoint();
    }
    
    private void invalidate(int count) {
      if (!this.sp.hasBeenInvalidated()) {
        SwitchPoint.invalidateAll(new SwitchPoint[] { this.sp });
        this.invalidations += count;
      } 
    }
    
    private void invalidateUncounted() {
      invalidate(0);
    }
    
    private void invalidateOnce() {
      invalidate(1);
    }
    
    private void invalidateForever() {
      invalidate(2);
    }
    
    private boolean mayRetry() {
      return (this.invalidations < 2);
    }
    
    public String toString() {
      return "[" + DebugLogger.quote(this.name) + " <id=" + Debug.id(this) + "> inv#=" + this.invalidations + "/2 sp_inv=" + this.sp.hasBeenInvalidated() + "]";
    }
    
    String getName() {
      return this.name;
    }
    
    SwitchPoint getSwitchPoint() {
      return this.sp;
    }
  }
  
  public void invalidateAll() {
    if (!this.invalidatedForever.get()) {
      this.log.info("New global created - invalidating all constant callsites without increasing invocation count.");
      synchronized (this) {
        for (Access acc : this.map.values())
          acc.invalidateUncounted(); 
      } 
    } 
  }
  
  public void invalidateForever() {
    if (this.invalidatedForever.compareAndSet(false, true)) {
      this.log.info("New global created - invalidating all constant callsites.");
      synchronized (this) {
        for (Access acc : this.map.values())
          acc.invalidateForever(); 
        this.map.clear();
      } 
    } 
  }
  
  private synchronized Object invalidateSwitchPoint(Object obj, Access acc) {
    if (this.log.isEnabled())
      this.log.info("*** Invalidating switchpoint " + acc.getSwitchPoint() + " for receiver=" + obj + " access=" + acc); 
    acc.invalidateOnce();
    if (acc.mayRetry()) {
      if (this.log.isEnabled())
        this.log.info("Retry is allowed for " + acc + "... Creating a new switchpoint."); 
      acc.newSwitchPoint();
    } else if (this.log.isEnabled()) {
      this.log.info("This was the last time I allowed " + DebugLogger.quote(acc.getName()) + " to relink as constant.");
    } 
    return obj;
  }
  
  private Access getOrCreateSwitchPoint(String name) {
    Access acc = this.map.get(name);
    if (acc != null)
      return acc; 
    SwitchPoint sp = new SwitchPoint();
    this.map.put(name, acc = new Access(name, sp));
    return acc;
  }
  
  void delete(Object name) {
    if (!this.invalidatedForever.get())
      synchronized (this) {
        Access acc = this.map.get(name);
        if (acc != null)
          acc.invalidateForever(); 
      }  
  }
  
  private static boolean receiverGuard(Access acc, Object boundReceiver, Object receiver) {
    boolean id = (receiver == boundReceiver);
    if (!id)
      acc.failGuard(); 
    return id;
  }
  
  private static boolean isGlobalSetter(ScriptObject receiver, FindProperty find) {
    if (find == null)
      return receiver.isScope(); 
    return find.getOwner().isGlobal();
  }
  
  GuardedInvocation findSetMethod(FindProperty find, ScriptObject receiver, GuardedInvocation inv, CallSiteDescriptor desc, LinkRequest request) {
    if (this.invalidatedForever.get() || !isGlobalSetter(receiver, find))
      return null; 
    String name = NashornCallSiteDescriptor.getOperand(desc);
    synchronized (this) {
      Access acc = getOrCreateSwitchPoint(name);
      if (this.log.isEnabled())
        this.log.fine(new Object[] { "Trying to link constant SETTER ", acc }); 
      if (!acc.mayRetry() || this.invalidatedForever.get()) {
        if (this.log.isEnabled())
          this.log.fine("*** SET: Giving up on " + DebugLogger.quote(name) + " - retry count has exceeded " + DynamicLinker.getLinkedCallSiteLocation()); 
        return null;
      } 
      if (acc.hasBeenInvalidated()) {
        this.log.info("New chance for " + acc);
        acc.newSwitchPoint();
      } 
      assert !acc.hasBeenInvalidated();
      MethodHandle target = inv.getInvocation();
      Class<?> receiverType = target.type().parameterType(0);
      MethodHandle boundInvalidator = Lookup.MH.bindTo(INVALIDATE_SP, this);
      MethodHandle invalidator = Lookup.MH.asType(boundInvalidator, boundInvalidator.type().changeParameterType(0, receiverType).changeReturnType(receiverType));
      MethodHandle mh = Lookup.MH.filterArguments(inv.getInvocation(), 0, new MethodHandle[] { Lookup.MH.insertArguments(invalidator, 1, new Object[] { acc }) });
      assert inv.getSwitchPoints() == null : Arrays.asList((T[])inv.getSwitchPoints());
      this.log.info("Linked setter " + DebugLogger.quote(name) + " " + acc.getSwitchPoint());
      return new GuardedInvocation(mh, inv.getGuard(), acc.getSwitchPoint(), inv.getException());
    } 
  }
  
  public static MethodHandle staticConstantGetter(Object c) {
    return Lookup.MH.dropArguments(JSType.unboxConstant(c), 0, new Class[] { Object.class });
  }
  
  private MethodHandle constantGetter(Object c) {
    MethodHandle mh = staticConstantGetter(c);
    if (this.log.isEnabled())
      return MethodHandleFactory.addDebugPrintout(this.log, Level.FINEST, mh, "getting as constant"); 
    return mh;
  }
  
  GuardedInvocation findGetMethod(FindProperty find, ScriptObject receiver, CallSiteDescriptor desc) {
    if (this.invalidatedForever.get() || !NashornCallSiteDescriptor.isFastScope(desc) || 
      !find.getOwner().isGlobal() || find
      .getProperty() instanceof UserAccessorProperty)
      return null; 
    boolean isOptimistic = NashornCallSiteDescriptor.isOptimistic(desc);
    int programPoint = isOptimistic ? NashornCallSiteDescriptor.getProgramPoint(desc) : -1;
    Class<?> retType = desc.getMethodType().returnType();
    String name = NashornCallSiteDescriptor.getOperand(desc);
    synchronized (this) {
      MethodHandle mh, guard;
      Access acc = getOrCreateSwitchPoint(name);
      this.log.fine("Starting to look up object value " + name);
      Object c = find.getObjectValue();
      if (this.log.isEnabled())
        this.log.fine("Trying to link constant GETTER " + acc + " value = " + c); 
      if (acc.hasBeenInvalidated() || acc.guardFailed() || this.invalidatedForever.get()) {
        if (this.log.isEnabled())
          this.log.info("*** GET: Giving up on " + DebugLogger.quote(name) + " - retry count has exceeded " + DynamicLinker.getLinkedCallSiteLocation()); 
        return null;
      } 
      MethodHandle cmh = constantGetter(c);
      if (isOptimistic) {
        if (JSType.getAccessorTypeIndex(cmh.type().returnType()) <= JSType.getAccessorTypeIndex(retType)) {
          mh = Lookup.MH.asType(cmh, cmh.type().changeReturnType(retType));
        } else {
          mh = Lookup.MH.dropArguments(Lookup.MH.insertArguments(JSType.THROW_UNWARRANTED.methodHandle(), 0, new Object[] { c, Integer.valueOf(programPoint) }), 0, new Class[] { Object.class });
        } 
      } else {
        mh = Lookup.filterReturnType(cmh, retType);
      } 
      if (find.getOwner().isGlobal()) {
        guard = null;
      } else {
        guard = Lookup.MH.insertArguments(RECEIVER_GUARD, 0, new Object[] { acc, receiver });
      } 
      if (this.log.isEnabled()) {
        this.log.info("Linked getter " + DebugLogger.quote(name) + " as MethodHandle.constant() -> " + c + " " + acc.getSwitchPoint());
        mh = MethodHandleFactory.addDebugPrintout(this.log, Level.FINE, mh, "get const " + acc);
      } 
      return new GuardedInvocation(mh, guard, acc.getSwitchPoint(), null);
    } 
  }
}
