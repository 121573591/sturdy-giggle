package org.openjdk.nashorn.internal.runtime.linker;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;
import jdk.dynalink.DynamicLinker;
import jdk.dynalink.linker.GuardedInvocation;
import jdk.dynalink.support.ChainedCallSite;
import org.openjdk.nashorn.internal.lookup.Lookup;
import org.openjdk.nashorn.internal.runtime.Context;
import org.openjdk.nashorn.internal.runtime.Debug;
import org.openjdk.nashorn.internal.runtime.ScriptObject;
import org.openjdk.nashorn.internal.runtime.ScriptRuntime;
import org.openjdk.nashorn.internal.runtime.options.Options;

public class LinkerCallSite extends ChainedCallSite {
  public static final int ARGLIMIT = 125;
  
  private static final String PROFILEFILE = Options.getStringProperty("nashorn.profilefile", "NashornProfile.txt");
  
  private static final MethodHandle INCREASE_MISS_COUNTER = Lookup.MH.findStatic(MethodHandles.lookup(), LinkerCallSite.class, "increaseMissCount", Lookup.MH.type(Object.class, new Class[] { String.class, Object.class }));
  
  private static final Comparator<Map.Entry<String, AtomicInteger>> MISS_COUNT_COMPARATOR;
  
  private static LongAdder count;
  
  static {
    MISS_COUNT_COMPARATOR = Comparator.comparingInt(e -> ((AtomicInteger)e.getValue()).get());
  }
  
  LinkerCallSite(NashornCallSiteDescriptor descriptor) {
    super(descriptor);
    if (Context.DEBUG)
      count.increment(); 
  }
  
  static LinkerCallSite newLinkerCallSite(MethodHandles.Lookup lookup, String name, MethodType type, int flags) {
    NashornCallSiteDescriptor desc = NashornCallSiteDescriptor.get(lookup, name, type, flags);
    if (desc.isProfile())
      return ProfilingLinkerCallSite.newProfilingLinkerCallSite(desc); 
    if (desc.isTrace())
      return new TracingLinkerCallSite(desc); 
    return new LinkerCallSite(desc);
  }
  
  public String toString() {
    return getDescriptor().toString();
  }
  
  public NashornCallSiteDescriptor getNashornDescriptor() {
    return (NashornCallSiteDescriptor)getDescriptor();
  }
  
  public void relink(GuardedInvocation invocation, MethodHandle relink) {
    super.relink(invocation, getDebuggingRelink(relink));
  }
  
  public void resetAndRelink(GuardedInvocation invocation, MethodHandle relink) {
    super.resetAndRelink(invocation, getDebuggingRelink(relink));
  }
  
  private MethodHandle getDebuggingRelink(MethodHandle relink) {
    if (Context.DEBUG)
      return Lookup.MH.filterArguments(relink, 0, new MethodHandle[] { getIncreaseMissCounter(relink.type().parameterType(0)) }); 
    return relink;
  }
  
  private MethodHandle getIncreaseMissCounter(Class<?> type) {
    MethodHandle missCounterWithDesc = Lookup.MH.bindTo(INCREASE_MISS_COUNTER, "" + getDescriptor().getOperation() + " @ " + getDescriptor().getOperation());
    if (type == Object.class)
      return missCounterWithDesc; 
    return Lookup.MH.asType(missCounterWithDesc, missCounterWithDesc.type().changeParameterType(0, type).changeReturnType(type));
  }
  
  private static String getScriptLocation() {
    StackTraceElement caller = DynamicLinker.getLinkedCallSiteLocation();
    return (caller == null) ? "unknown location" : (caller.getFileName() + ":" + caller.getFileName());
  }
  
  public static Object increaseMissCount(String desc, Object self) {
    missCount.increment();
    if (r.nextInt(100) < missSamplingPercentage) {
      AtomicInteger i = missCounts.get(desc);
      if (i == null) {
        missCounts.put(desc, new AtomicInteger(1));
      } else {
        i.incrementAndGet();
      } 
    } 
    return self;
  }
  
  private static class ProfilingLinkerCallSite extends LinkerCallSite {
    private static LinkedList<ProfilingLinkerCallSite> profileCallSites = null;
    
    private long startTime;
    
    private int depth;
    
    private long totalTime;
    
    private long hitCount;
    
    private static final MethodHandles.Lookup LOOKUP = MethodHandles.lookup();
    
    private static final MethodHandle PROFILEENTRY = Lookup.MH.findVirtual(LOOKUP, ProfilingLinkerCallSite.class, "profileEntry", Lookup.MH.type(Object.class, new Class[] { Object.class }));
    
    private static final MethodHandle PROFILEEXIT = Lookup.MH.findVirtual(LOOKUP, ProfilingLinkerCallSite.class, "profileExit", Lookup.MH.type(Object.class, new Class[] { Object.class }));
    
    private static final MethodHandle PROFILEVOIDEXIT = Lookup.MH.findVirtual(LOOKUP, ProfilingLinkerCallSite.class, "profileVoidExit", Lookup.MH.type(void.class, new Class[0]));
    
    ProfilingLinkerCallSite(NashornCallSiteDescriptor desc) {
      super(desc);
    }
    
    public static ProfilingLinkerCallSite newProfilingLinkerCallSite(NashornCallSiteDescriptor desc) {
      if (profileCallSites == null) {
        profileCallSites = new LinkedList<>();
        Thread profileDumperThread = new Thread(new ProfileDumper());
        Runtime.getRuntime().addShutdownHook(profileDumperThread);
      } 
      ProfilingLinkerCallSite callSite = new ProfilingLinkerCallSite(desc);
      profileCallSites.add(callSite);
      return callSite;
    }
    
    public void setTarget(MethodHandle newTarget) {
      MethodType type = type();
      boolean isVoid = (type.returnType() == void.class);
      Class<?> newSelfType = newTarget.type().parameterType(0);
      MethodHandle selfFilter = Lookup.MH.bindTo(PROFILEENTRY, this);
      if (newSelfType != Object.class) {
        MethodType selfFilterType = MethodType.methodType(newSelfType, newSelfType);
        selfFilter = selfFilter.asType(selfFilterType);
      } 
      MethodHandle methodHandle = Lookup.MH.filterArguments(newTarget, 0, new MethodHandle[] { selfFilter });
      if (isVoid) {
        methodHandle = Lookup.MH.filterReturnValue(methodHandle, Lookup.MH.bindTo(PROFILEVOIDEXIT, this));
      } else {
        MethodType filter = Lookup.MH.type(type.returnType(), new Class[] { type.returnType() });
        methodHandle = Lookup.MH.filterReturnValue(methodHandle, Lookup.MH.asType(Lookup.MH.bindTo(PROFILEEXIT, this), filter));
      } 
      super.setTarget(methodHandle);
    }
    
    public Object profileEntry(Object self) {
      if (this.depth == 0)
        this.startTime = System.nanoTime(); 
      this.depth++;
      this.hitCount++;
      return self;
    }
    
    public Object profileExit(Object result) {
      this.depth--;
      if (this.depth == 0)
        this.totalTime += System.nanoTime() - this.startTime; 
      return result;
    }
    
    public void profileVoidExit() {
      this.depth--;
      if (this.depth == 0)
        this.totalTime += System.nanoTime() - this.startTime; 
    }
    
    static class ProfileDumper implements Runnable {
      public void run() {
        PrintWriter out = null;
        boolean fileOutput = false;
        try {
          try {
            out = new PrintWriter(new FileOutputStream(LinkerCallSite.PROFILEFILE));
            fileOutput = true;
          } catch (FileNotFoundException e) {
            out = Context.getCurrentErr();
          } 
          dump(out);
        } finally {
          if (out != null && fileOutput)
            out.close(); 
        } 
      }
      
      private static void dump(PrintWriter out) {
        int index = 0;
        for (LinkerCallSite.ProfilingLinkerCallSite callSite : LinkerCallSite.ProfilingLinkerCallSite.profileCallSites)
          out.println("" + index++ + "\t" + index++ + "\t" + callSite
              .getDescriptor().getOperation() + "\t" + callSite.totalTime); 
      }
    }
  }
  
  static class ProfileDumper implements Runnable {
    public void run() {
      PrintWriter out = null;
      boolean fileOutput = false;
      try {
        try {
          out = new PrintWriter(new FileOutputStream(LinkerCallSite.PROFILEFILE));
          fileOutput = true;
        } catch (FileNotFoundException e) {
          out = Context.getCurrentErr();
        } 
        dump(out);
      } finally {
        if (out != null && fileOutput)
          out.close(); 
      } 
    }
    
    private static void dump(PrintWriter out) {
      int index = 0;
      for (LinkerCallSite.ProfilingLinkerCallSite callSite : LinkerCallSite.ProfilingLinkerCallSite.profileCallSites)
        out.println("" + index++ + "\t" + index++ + "\t" + callSite.getDescriptor().getOperation() + "\t" + callSite.totalTime); 
    }
  }
  
  private static class TracingLinkerCallSite extends LinkerCallSite {
    private static final MethodHandles.Lookup LOOKUP = MethodHandles.lookup();
    
    private static final MethodHandle TRACEOBJECT = Lookup.MH.findVirtual(LOOKUP, TracingLinkerCallSite.class, "traceObject", Lookup.MH.type(Object.class, new Class[] { MethodHandle.class, Object[].class }));
    
    private static final MethodHandle TRACEVOID = Lookup.MH.findVirtual(LOOKUP, TracingLinkerCallSite.class, "traceVoid", Lookup.MH.type(void.class, new Class[] { MethodHandle.class, Object[].class }));
    
    private static final MethodHandle TRACEMISS = Lookup.MH.findVirtual(LOOKUP, TracingLinkerCallSite.class, "traceMiss", Lookup.MH.type(void.class, new Class[] { String.class, Object[].class }));
    
    TracingLinkerCallSite(NashornCallSiteDescriptor desc) {
      super(desc);
    }
    
    public void setTarget(MethodHandle newTarget) {
      if (!getNashornDescriptor().isTraceEnterExit()) {
        super.setTarget(newTarget);
        return;
      } 
      MethodType type = type();
      boolean isVoid = (type.returnType() == void.class);
      MethodHandle traceMethodHandle = isVoid ? TRACEVOID : TRACEOBJECT;
      traceMethodHandle = Lookup.MH.bindTo(traceMethodHandle, this);
      traceMethodHandle = Lookup.MH.bindTo(traceMethodHandle, newTarget);
      traceMethodHandle = Lookup.MH.asCollector(traceMethodHandle, Object[].class, type.parameterCount());
      traceMethodHandle = Lookup.MH.asType(traceMethodHandle, type);
      super.setTarget(traceMethodHandle);
    }
    
    public void initialize(MethodHandle relinkAndInvoke) {
      super.initialize(getFallbackLoggingRelink(relinkAndInvoke));
    }
    
    public void relink(GuardedInvocation invocation, MethodHandle relink) {
      super.relink(invocation, getFallbackLoggingRelink(relink));
    }
    
    public void resetAndRelink(GuardedInvocation invocation, MethodHandle relink) {
      super.resetAndRelink(invocation, getFallbackLoggingRelink(relink));
    }
    
    private MethodHandle getFallbackLoggingRelink(MethodHandle relink) {
      if (!getNashornDescriptor().isTraceMisses())
        return relink; 
      MethodType type = relink.type();
      return Lookup.MH.foldArguments(relink, Lookup.MH.asType(Lookup.MH.asCollector(Lookup.MH.insertArguments(TRACEMISS, 0, new Object[] { this, "MISS " + LinkerCallSite.getScriptLocation() + " " }), Object[].class, type.parameterCount()), type.changeReturnType(void.class)));
    }
    
    private void printObject(PrintWriter out, Object arg) {
      if (!getNashornDescriptor().isTraceObjects()) {
        out.print((arg instanceof ScriptObject) ? "ScriptObject" : arg);
        return;
      } 
      if (arg instanceof ScriptObject) {
        ScriptObject object = (ScriptObject)arg;
        boolean isFirst = true;
        Set<Object> keySet = object.keySet();
        if (keySet.isEmpty()) {
          out.print(ScriptRuntime.safeToString(arg));
        } else {
          out.print("{ ");
          for (Object key : keySet) {
            if (!isFirst)
              out.print(", "); 
            out.print(key);
            out.print(":");
            Object value = object.get(key);
            if (value instanceof ScriptObject) {
              out.print("...");
            } else {
              printObject(out, value);
            } 
            isFirst = false;
          } 
          out.print(" }");
        } 
      } else {
        out.print(ScriptRuntime.safeToString(arg));
      } 
    }
    
    private void tracePrint(PrintWriter out, String tag, Object[] args, Object result) {
      out.print(Debug.id(this) + " TAG " + Debug.id(this));
      out.print("" + getDescriptor().getOperation() + "(");
      if (args.length > 0) {
        printObject(out, args[0]);
        for (int i = 1; i < args.length; i++) {
          Object arg = args[i];
          out.print(", ");
          if (!(arg instanceof ScriptObject) || !((ScriptObject)arg).isScope()) {
            printObject(out, arg);
          } else {
            out.print("SCOPE");
          } 
        } 
      } 
      out.print(")");
      if (tag.equals("EXIT  ")) {
        out.print(" --> ");
        printObject(out, result);
      } 
      out.println();
    }
    
    public Object traceObject(MethodHandle mh, Object... args) throws Throwable {
      PrintWriter out = Context.getCurrentErr();
      tracePrint(out, "ENTER ", args, (Object)null);
      Object result = mh.invokeWithArguments(args);
      tracePrint(out, "EXIT  ", args, result);
      return result;
    }
    
    public void traceVoid(MethodHandle mh, Object... args) throws Throwable {
      PrintWriter out = Context.getCurrentErr();
      tracePrint(out, "ENTER ", args, (Object)null);
      mh.invokeWithArguments(args);
      tracePrint(out, "EXIT  ", args, (Object)null);
    }
    
    public void traceMiss(String desc, Object... args) {
      tracePrint(Context.getCurrentErr(), desc, args, (Object)null);
    }
  }
  
  private static final HashMap<String, AtomicInteger> missCounts = new HashMap<>();
  
  private static LongAdder missCount;
  
  private static final Random r = new Random();
  
  private static final int missSamplingPercentage = Options.getIntProperty("nashorn.tcs.miss.samplePercent", 1);
  
  static {
    if (Context.DEBUG) {
      count = new LongAdder();
      missCount = new LongAdder();
    } 
  }
  
  protected int getMaxChainLength() {
    return 8;
  }
  
  public static long getCount() {
    return count.longValue();
  }
  
  public static long getMissCount() {
    return missCount.longValue();
  }
  
  public static int getMissSamplingPercentage() {
    return missSamplingPercentage;
  }
  
  public static void getMissCounts(PrintWriter out) {
    ArrayList<Map.Entry<String, AtomicInteger>> entries = new ArrayList<>(missCounts.entrySet());
    entries.sort(MISS_COUNT_COMPARATOR);
    for (Map.Entry<String, AtomicInteger> entry : entries)
      out.println("  " + (String)entry.getKey() + "\t" + ((AtomicInteger)entry.getValue()).get()); 
  }
}
