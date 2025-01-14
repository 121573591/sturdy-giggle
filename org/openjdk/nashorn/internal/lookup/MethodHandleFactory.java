package org.openjdk.nashorn.internal.lookup;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.SwitchPoint;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import org.openjdk.nashorn.internal.runtime.Context;
import org.openjdk.nashorn.internal.runtime.JSType;
import org.openjdk.nashorn.internal.runtime.logging.DebugLogger;
import org.openjdk.nashorn.internal.runtime.logging.Loggable;
import org.openjdk.nashorn.internal.runtime.logging.Logger;
import org.openjdk.nashorn.internal.runtime.options.Options;

public final class MethodHandleFactory {
  private static final MethodHandles.Lookup PUBLIC_LOOKUP = MethodHandles.publicLookup();
  
  private static final MethodHandles.Lookup LOOKUP = MethodHandles.lookup();
  
  private static final Level TRACE_LEVEL = Level.INFO;
  
  public static class LookupException extends RuntimeException {
    public LookupException(Exception e) {
      super(e);
    }
  }
  
  public static String stripName(Object obj) {
    if (obj == null)
      return "null"; 
    if (obj instanceof Class)
      return ((Class)obj).getSimpleName(); 
    return obj.toString();
  }
  
  private static final MethodHandleFunctionality FUNC = new StandardMethodHandleFunctionality();
  
  private static final boolean PRINT_STACKTRACE = Options.getBooleanProperty("nashorn.methodhandles.debug.stacktrace");
  
  public static MethodHandleFunctionality getFunctionality() {
    return FUNC;
  }
  
  private static final MethodHandle TRACE = FUNC.findStatic(LOOKUP, MethodHandleFactory.class, "traceArgs", MethodType.methodType(void.class, DebugLogger.class, new Class[] { String.class, int.class, Object[].class }));
  
  private static final MethodHandle TRACE_RETURN = FUNC.findStatic(LOOKUP, MethodHandleFactory.class, "traceReturn", MethodType.methodType(Object.class, DebugLogger.class, new Class[] { Object.class }));
  
  private static final MethodHandle TRACE_RETURN_VOID = FUNC.findStatic(LOOKUP, MethodHandleFactory.class, "traceReturnVoid", MethodType.methodType(void.class, DebugLogger.class));
  
  private static final String VOID_TAG = "[VOID]";
  
  private static void err(String str) {
    Context.getContext().getErr().println(str);
  }
  
  static Object traceReturn(DebugLogger logger, Object value) {
    String str = "    return" + ("[VOID]".equals(value) ? ";" : (" " + stripName(value) + "; // [type=" + ((value == null) ? "null]" : (stripName(value.getClass()) + "]"))));
    if (logger == null) {
      err(str);
    } else if (logger.isEnabled()) {
      logger.log(TRACE_LEVEL, str);
    } 
    return value;
  }
  
  static void traceReturnVoid(DebugLogger logger) {
    traceReturn(logger, "[VOID]");
  }
  
  static void traceArgs(DebugLogger logger, String tag, int paramStart, Object... args) {
    StringBuilder sb = new StringBuilder();
    sb.append(tag);
    for (int i = paramStart; i < args.length; i++) {
      if (i == paramStart)
        sb.append(" => args: "); 
      sb.append('\'')
        .append(stripName(argString(args[i])))
        .append('\'')
        .append(' ')
        .append('[')
        .append("type=")
        .append((args[i] == null) ? "null" : stripName(args[i].getClass()))
        .append(']');
      if (i + 1 < args.length)
        sb.append(", "); 
    } 
    if (logger == null) {
      err(sb.toString());
    } else {
      logger.log(TRACE_LEVEL, new Object[] { sb });
    } 
    stacktrace(logger);
  }
  
  private static void stacktrace(DebugLogger logger) {
    if (!PRINT_STACKTRACE)
      return; 
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(baos);
    (new Throwable()).printStackTrace(ps);
    String st = baos.toString();
    if (logger == null) {
      err(st);
    } else {
      logger.log(TRACE_LEVEL, st);
    } 
  }
  
  private static String argString(Object arg) {
    if (arg == null)
      return "null"; 
    if (arg.getClass().isArray()) {
      List<Object> list = new ArrayList();
      for (Object elem : (Object[])arg)
        list.add("'" + argString(elem) + "'"); 
      return list.toString();
    } 
    if (arg instanceof org.openjdk.nashorn.internal.runtime.ScriptObject)
      return arg.toString() + " (map=" + arg.toString() + ")"; 
    return arg.toString();
  }
  
  public static MethodHandle addDebugPrintout(MethodHandle mh, Object tag) {
    return addDebugPrintout(null, Level.OFF, mh, 0, true, tag);
  }
  
  public static MethodHandle addDebugPrintout(DebugLogger logger, Level level, MethodHandle mh, Object tag) {
    return addDebugPrintout(logger, level, mh, 0, true, tag);
  }
  
  public static MethodHandle addDebugPrintout(MethodHandle mh, int paramStart, boolean printReturnValue, Object tag) {
    return addDebugPrintout(null, Level.OFF, mh, paramStart, printReturnValue, tag);
  }
  
  public static MethodHandle addDebugPrintout(DebugLogger logger, Level level, MethodHandle mh, int paramStart, boolean printReturnValue, Object tag) {
    MethodType type = mh.type();
    if (logger == null || !logger.isLoggable(level))
      return mh; 
    assert TRACE != null;
    MethodHandle trace = MethodHandles.insertArguments(TRACE, 0, new Object[] { logger, tag, Integer.valueOf(paramStart) });
    trace = MethodHandles.foldArguments(mh, trace
        
        .asCollector(Object[].class, type
          
          .parameterCount())
        .asType(type.changeReturnType(void.class)));
    Class<?> retType = type.returnType();
    if (printReturnValue)
      if (retType != void.class) {
        MethodHandle traceReturn = MethodHandles.insertArguments(TRACE_RETURN, 0, new Object[] { logger });
        trace = MethodHandles.filterReturnValue(trace, traceReturn
            .asType(traceReturn
              .type().changeParameterType(0, retType).changeReturnType(retType)));
      } else {
        trace = MethodHandles.filterReturnValue(trace, MethodHandles.insertArguments(TRACE_RETURN_VOID, 0, new Object[] { logger }));
      }  
    return trace;
  }
  
  @Logger(name = "methodhandles")
  private static class StandardMethodHandleFunctionality implements MethodHandleFunctionality, Loggable {
    private DebugLogger log = DebugLogger.DISABLED_LOGGER;
    
    public DebugLogger initLogger(Context context) {
      return this.log = context.getLogger(getClass());
    }
    
    public DebugLogger getLogger() {
      return this.log;
    }
    
    protected static String describe(Object... data) {
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < data.length; i++) {
        Object d = data[i];
        if (d == null) {
          sb.append("<null> ");
        } else if (JSType.isString(d)) {
          sb.append(d.toString());
          sb.append(' ');
        } else if (d.getClass().isArray()) {
          sb.append("[ ");
          for (Object da : (Object[])d) {
            sb.append(describe(new Object[] { da })).append(' ');
          } 
          sb.append("] ");
        } else {
          sb.append(d)
            .append('{')
            .append(Integer.toHexString(System.identityHashCode(d)))
            .append('}');
        } 
        if (i + 1 < data.length)
          sb.append(", "); 
      } 
      return sb.toString();
    }
    
    public MethodHandle debug(MethodHandle master, String str, Object... args) {
      if (this.log.isEnabled()) {
        if (MethodHandleFactory.PRINT_STACKTRACE)
          MethodHandleFactory.stacktrace(this.log); 
        return MethodHandleFactory.addDebugPrintout(this.log, Level.INFO, master, 2147483647, false, str + " " + str);
      } 
      return master;
    }
    
    public MethodHandle filterArguments(MethodHandle target, int pos, MethodHandle... filters) {
      MethodHandle mh = MethodHandles.filterArguments(target, pos, filters);
      return debug(mh, "filterArguments", new Object[] { target, Integer.valueOf(pos), filters });
    }
    
    public MethodHandle filterReturnValue(MethodHandle target, MethodHandle filter) {
      MethodHandle mh = MethodHandles.filterReturnValue(target, filter);
      return debug(mh, "filterReturnValue", new Object[] { target, filter });
    }
    
    public MethodHandle guardWithTest(MethodHandle test, MethodHandle target, MethodHandle fallback) {
      MethodHandle mh = MethodHandles.guardWithTest(test, target, fallback);
      return debug(mh, "guardWithTest", new Object[] { test, target, fallback });
    }
    
    public MethodHandle insertArguments(MethodHandle target, int pos, Object... values) {
      MethodHandle mh = MethodHandles.insertArguments(target, pos, values);
      return debug(mh, "insertArguments", new Object[] { target, Integer.valueOf(pos), values });
    }
    
    public MethodHandle dropArguments(MethodHandle target, int pos, Class<?>... values) {
      MethodHandle mh = MethodHandles.dropArguments(target, pos, values);
      return debug(mh, "dropArguments", new Object[] { target, Integer.valueOf(pos), values });
    }
    
    public MethodHandle dropArguments(MethodHandle target, int pos, List<Class<?>> values) {
      MethodHandle mh = MethodHandles.dropArguments(target, pos, values);
      return debug(mh, "dropArguments", new Object[] { target, Integer.valueOf(pos), values });
    }
    
    public MethodHandle asType(MethodHandle handle, MethodType type) {
      MethodHandle mh = handle.asType(type);
      return debug(mh, "asType", new Object[] { handle, type });
    }
    
    public MethodHandle bindTo(MethodHandle handle, Object x) {
      MethodHandle mh = handle.bindTo(x);
      return debug(mh, "bindTo", new Object[] { handle, x });
    }
    
    public MethodHandle foldArguments(MethodHandle target, MethodHandle combiner) {
      MethodHandle mh = MethodHandles.foldArguments(target, combiner);
      return debug(mh, "foldArguments", new Object[] { target, combiner });
    }
    
    public MethodHandle explicitCastArguments(MethodHandle target, MethodType type) {
      MethodHandle mh = MethodHandles.explicitCastArguments(target, type);
      return debug(mh, "explicitCastArguments", new Object[] { target, type });
    }
    
    public MethodHandle arrayElementGetter(Class<?> type) {
      MethodHandle mh = MethodHandles.arrayElementGetter(type);
      return debug(mh, "arrayElementGetter", new Object[] { type });
    }
    
    public MethodHandle arrayElementSetter(Class<?> type) {
      MethodHandle mh = MethodHandles.arrayElementSetter(type);
      return debug(mh, "arrayElementSetter", new Object[] { type });
    }
    
    public MethodHandle throwException(Class<?> returnType, Class<? extends Throwable> exType) {
      MethodHandle mh = MethodHandles.throwException(returnType, exType);
      return debug(mh, "throwException", new Object[] { returnType, exType });
    }
    
    public MethodHandle catchException(MethodHandle target, Class<? extends Throwable> exType, MethodHandle handler) {
      MethodHandle mh = MethodHandles.catchException(target, exType, handler);
      return debug(mh, "catchException", new Object[] { exType });
    }
    
    public MethodHandle constant(Class<?> type, Object value) {
      MethodHandle mh = MethodHandles.constant(type, value);
      return debug(mh, "constant", new Object[] { type, value });
    }
    
    public MethodHandle identity(Class<?> type) {
      MethodHandle mh = MethodHandles.identity(type);
      return debug(mh, "identity", new Object[] { type });
    }
    
    public MethodHandle asCollector(MethodHandle handle, Class<?> arrayType, int arrayLength) {
      MethodHandle mh = handle.asCollector(arrayType, arrayLength);
      return debug(mh, "asCollector", new Object[] { handle, arrayType, Integer.valueOf(arrayLength) });
    }
    
    public MethodHandle asSpreader(MethodHandle handle, Class<?> arrayType, int arrayLength) {
      MethodHandle mh = handle.asSpreader(arrayType, arrayLength);
      return debug(mh, "asSpreader", new Object[] { handle, arrayType, Integer.valueOf(arrayLength) });
    }
    
    public MethodHandle getter(MethodHandles.Lookup explicitLookup, Class<?> clazz, String name, Class<?> type) {
      try {
        MethodHandle mh = explicitLookup.findGetter(clazz, name, type);
        return debug(mh, "getter", new Object[] { explicitLookup, clazz, name, type });
      } catch (NoSuchFieldException|IllegalAccessException e) {
        throw new MethodHandleFactory.LookupException(e);
      } 
    }
    
    public MethodHandle staticGetter(MethodHandles.Lookup explicitLookup, Class<?> clazz, String name, Class<?> type) {
      try {
        MethodHandle mh = explicitLookup.findStaticGetter(clazz, name, type);
        return debug(mh, "static getter", new Object[] { explicitLookup, clazz, name, type });
      } catch (NoSuchFieldException|IllegalAccessException e) {
        throw new MethodHandleFactory.LookupException(e);
      } 
    }
    
    public MethodHandle setter(MethodHandles.Lookup explicitLookup, Class<?> clazz, String name, Class<?> type) {
      try {
        MethodHandle mh = explicitLookup.findSetter(clazz, name, type);
        return debug(mh, "setter", new Object[] { explicitLookup, clazz, name, type });
      } catch (NoSuchFieldException|IllegalAccessException e) {
        throw new MethodHandleFactory.LookupException(e);
      } 
    }
    
    public MethodHandle staticSetter(MethodHandles.Lookup explicitLookup, Class<?> clazz, String name, Class<?> type) {
      try {
        MethodHandle mh = explicitLookup.findStaticSetter(clazz, name, type);
        return debug(mh, "static setter", new Object[] { explicitLookup, clazz, name, type });
      } catch (NoSuchFieldException|IllegalAccessException e) {
        throw new MethodHandleFactory.LookupException(e);
      } 
    }
    
    public MethodHandle find(Method method) {
      try {
        MethodHandle mh = MethodHandleFactory.PUBLIC_LOOKUP.unreflect(method);
        return debug(mh, "find", new Object[] { method });
      } catch (IllegalAccessException e) {
        throw new MethodHandleFactory.LookupException(e);
      } 
    }
    
    public MethodHandle findStatic(MethodHandles.Lookup explicitLookup, Class<?> clazz, String name, MethodType type) {
      try {
        MethodHandle mh = explicitLookup.findStatic(clazz, name, type);
        return debug(mh, "findStatic", new Object[] { explicitLookup, clazz, name, type });
      } catch (NoSuchMethodException|IllegalAccessException e) {
        throw new MethodHandleFactory.LookupException(e);
      } 
    }
    
    public MethodHandle findSpecial(MethodHandles.Lookup explicitLookup, Class<?> clazz, String name, MethodType type, Class<?> thisClass) {
      try {
        MethodHandle mh = explicitLookup.findSpecial(clazz, name, type, thisClass);
        return debug(mh, "findSpecial", new Object[] { explicitLookup, clazz, name, type });
      } catch (NoSuchMethodException|IllegalAccessException e) {
        throw new MethodHandleFactory.LookupException(e);
      } 
    }
    
    public MethodHandle findVirtual(MethodHandles.Lookup explicitLookup, Class<?> clazz, String name, MethodType type) {
      try {
        MethodHandle mh = explicitLookup.findVirtual(clazz, name, type);
        return debug(mh, "findVirtual", new Object[] { explicitLookup, clazz, name, type });
      } catch (NoSuchMethodException|IllegalAccessException e) {
        throw new MethodHandleFactory.LookupException(e);
      } 
    }
    
    public SwitchPoint createSwitchPoint() {
      SwitchPoint sp = new SwitchPoint();
      this.log.log(MethodHandleFactory.TRACE_LEVEL, new Object[] { "createSwitchPoint ", sp });
      return sp;
    }
    
    public MethodHandle guardWithTest(SwitchPoint sp, MethodHandle before, MethodHandle after) {
      MethodHandle mh = sp.guardWithTest(before, after);
      return debug(mh, "guardWithTest", new Object[] { sp, before, after });
    }
    
    public MethodType type(Class<?> returnType, Class<?>... paramTypes) {
      MethodType mt = MethodType.methodType(returnType, paramTypes);
      this.log.log(MethodHandleFactory.TRACE_LEVEL, new Object[] { "methodType ", returnType, " ", Arrays.toString((Object[])paramTypes), " ", mt });
      return mt;
    }
  }
}
