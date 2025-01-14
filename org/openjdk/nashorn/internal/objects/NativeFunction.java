package org.openjdk.nashorn.internal.objects;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.util.Collections;
import java.util.List;
import jdk.dynalink.linker.support.Lookup;
import org.openjdk.nashorn.api.scripting.JSObject;
import org.openjdk.nashorn.api.scripting.ScriptObjectMirror;
import org.openjdk.nashorn.internal.parser.Parser;
import org.openjdk.nashorn.internal.runtime.Context;
import org.openjdk.nashorn.internal.runtime.ECMAErrors;
import org.openjdk.nashorn.internal.runtime.ErrorManager;
import org.openjdk.nashorn.internal.runtime.JSType;
import org.openjdk.nashorn.internal.runtime.ParserException;
import org.openjdk.nashorn.internal.runtime.PropertyMap;
import org.openjdk.nashorn.internal.runtime.ScriptEnvironment;
import org.openjdk.nashorn.internal.runtime.ScriptFunction;
import org.openjdk.nashorn.internal.runtime.ScriptObject;
import org.openjdk.nashorn.internal.runtime.ScriptRuntime;
import org.openjdk.nashorn.internal.runtime.Source;
import org.openjdk.nashorn.internal.runtime.linker.Bootstrap;

public final class NativeFunction {
  public static final MethodHandle TO_APPLY_ARGS = Lookup.findOwnStatic(MethodHandles.lookup(), "toApplyArgs", Object[].class, new Class[] { Object.class });
  
  private static PropertyMap $nasgenmap$;
  
  static {
    $clinit$();
  }
  
  private NativeFunction() {
    throw new UnsupportedOperationException();
  }
  
  public static String toString(Object self) {
    if (!(self instanceof ScriptFunction))
      throw ECMAErrors.typeError("not.a.function", new String[] { ScriptRuntime.safeToString(self) }); 
    return ((ScriptFunction)self).toSource();
  }
  
  public static Object apply(Object self, Object thiz, Object array) {
    checkCallable(self);
    Object[] args = toApplyArgs(array);
    if (self instanceof ScriptFunction)
      return ScriptRuntime.apply((ScriptFunction)self, thiz, args); 
    if (self instanceof ScriptObjectMirror)
      return ((JSObject)self).call(thiz, args); 
    if (self instanceof JSObject) {
      Global global = Global.instance();
      Object result = ((JSObject)self).call(ScriptObjectMirror.wrap(thiz, global), 
          ScriptObjectMirror.wrapArray(args, global));
      return ScriptObjectMirror.unwrap(result, global);
    } 
    throw new AssertionError("Should not reach here");
  }
  
  public static Object[] toApplyArgs(Object array) {
    if (array instanceof NativeArguments)
      return ((NativeArguments)array).getArray().asObjectArray(); 
    if (array instanceof ScriptObject) {
      ScriptObject sobj = (ScriptObject)array;
      int n = lengthToInt(sobj.getLength());
      Object[] args = new Object[n];
      for (int i = 0; i < args.length; i++)
        args[i] = sobj.get(i); 
      return args;
    } 
    if (array instanceof Object[])
      return (Object[])array; 
    if (array instanceof List) {
      List<?> list = (List)array;
      return list.toArray(new Object[0]);
    } 
    if (array == null || array == ScriptRuntime.UNDEFINED)
      return ScriptRuntime.EMPTY_ARRAY; 
    if (array instanceof JSObject) {
      JSObject jsObj = (JSObject)array;
      Object len = jsObj.hasMember("length") ? jsObj.getMember("length") : Integer.valueOf(0);
      int n = lengthToInt(len);
      Object[] args = new Object[n];
      for (int i = 0; i < args.length; i++)
        args[i] = jsObj.hasSlot(i) ? jsObj.getSlot(i) : ScriptRuntime.UNDEFINED; 
      return args;
    } 
    throw ECMAErrors.typeError("function.apply.expects.array", new String[0]);
  }
  
  private static int lengthToInt(Object len) {
    long ln = JSType.toUint32(len);
    if (ln > 2147483647L)
      throw ECMAErrors.rangeError("range.error.inappropriate.array.length", new String[] { JSType.toString(len) }); 
    return (int)ln;
  }
  
  private static void checkCallable(Object self) {
    if (!(self instanceof ScriptFunction) && (!(self instanceof JSObject) || !((JSObject)self).isFunction()))
      throw ECMAErrors.typeError("not.a.function", new String[] { ScriptRuntime.safeToString(self) }); 
  }
  
  public static Object call(Object self, Object... args) {
    Object[] arguments;
    checkCallable(self);
    Object thiz = (args.length == 0) ? ScriptRuntime.UNDEFINED : args[0];
    if (args.length > 1) {
      arguments = new Object[args.length - 1];
      System.arraycopy(args, 1, arguments, 0, arguments.length);
    } else {
      arguments = ScriptRuntime.EMPTY_ARRAY;
    } 
    if (self instanceof ScriptFunction)
      return ScriptRuntime.apply((ScriptFunction)self, thiz, arguments); 
    if (self instanceof JSObject)
      return ((JSObject)self).call(thiz, arguments); 
    throw new AssertionError("should not reach here");
  }
  
  public static Object bind(Object self, Object... args) {
    Object arguments[], thiz = (args.length == 0) ? ScriptRuntime.UNDEFINED : args[0];
    if (args.length > 1) {
      arguments = new Object[args.length - 1];
      System.arraycopy(args, 1, arguments, 0, arguments.length);
    } else {
      arguments = ScriptRuntime.EMPTY_ARRAY;
    } 
    return Bootstrap.bindCallable(self, thiz, arguments);
  }
  
  public static String toSource(Object self) {
    if (!(self instanceof ScriptFunction))
      throw ECMAErrors.typeError("not.a.function", new String[] { ScriptRuntime.safeToString(self) }); 
    return ((ScriptFunction)self).toSource();
  }
  
  public static ScriptFunction function(boolean newObj, Object self, Object... args) {
    String funcBody;
    StringBuilder sb = new StringBuilder();
    sb.append("(function (");
    if (args.length > 0) {
      StringBuilder paramListBuf = new StringBuilder();
      for (int i = 0; i < args.length - 1; i++) {
        paramListBuf.append(JSType.toString(args[i]));
        if (i < args.length - 2)
          paramListBuf.append(","); 
      } 
      funcBody = JSType.toString(args[args.length - 1]);
      String paramList = paramListBuf.toString();
      if (!paramList.isEmpty()) {
        checkFunctionParameters(paramList);
        sb.append(paramList);
      } 
    } else {
      funcBody = null;
    } 
    sb.append(") {\n");
    if (args.length > 0) {
      checkFunctionBody(funcBody);
      sb.append(funcBody);
      sb.append('\n');
    } 
    sb.append("})");
    Global global = Global.instance();
    Context context = global.getContext();
    return (ScriptFunction)context.eval((ScriptObject)global, sb.toString(), global, "<function>");
  }
  
  private static void checkFunctionParameters(String params) {
    Parser parser = getParser(params);
    try {
      parser.parseFormalParameterList();
    } catch (ParserException pe) {
      pe.throwAsEcmaException();
    } 
  }
  
  private static void checkFunctionBody(String funcBody) {
    Parser parser = getParser(funcBody);
    try {
      parser.parseFunctionBody();
    } catch (ParserException pe) {
      pe.throwAsEcmaException();
    } 
  }
  
  private static Parser getParser(String sourceText) {
    ScriptEnvironment env = Global.getEnv();
    return new Parser(env, Source.sourceFor("<function>", sourceText), (ErrorManager)new Context.ThrowErrorManager(), env._strict, null);
  }
  
  public static void $clinit$() {
    $nasgenmap$ = PropertyMap.newMap(Collections.EMPTY_LIST);
  }
}
