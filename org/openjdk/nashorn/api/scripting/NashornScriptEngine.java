package org.openjdk.nashorn.api.scripting;

import java.io.IOException;
import java.io.Reader;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.Permissions;
import java.security.ProtectionDomain;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Objects;
import java.util.ResourceBundle;
import javax.script.AbstractScriptEngine;
import javax.script.Bindings;
import javax.script.Compilable;
import javax.script.CompiledScript;
import javax.script.Invocable;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptException;
import javax.script.SimpleBindings;
import org.openjdk.nashorn.internal.objects.Global;
import org.openjdk.nashorn.internal.runtime.Context;
import org.openjdk.nashorn.internal.runtime.ErrorManager;
import org.openjdk.nashorn.internal.runtime.ScriptFunction;
import org.openjdk.nashorn.internal.runtime.ScriptObject;
import org.openjdk.nashorn.internal.runtime.Source;
import org.openjdk.nashorn.internal.runtime.linker.JavaAdapterFactory;
import org.openjdk.nashorn.internal.runtime.options.Options;

public final class NashornScriptEngine extends AbstractScriptEngine implements Compilable, Invocable {
  public static final String NASHORN_GLOBAL = "nashorn.global";
  
  private static AccessControlContext createPermAccCtxt(String permName) {
    Permissions perms = new Permissions();
    perms.add(new RuntimePermission(permName));
    return new AccessControlContext(new ProtectionDomain[] { new ProtectionDomain(null, perms) });
  }
  
  private static final AccessControlContext CREATE_CONTEXT_ACC_CTXT = createPermAccCtxt("nashorn.createContext");
  
  private static final AccessControlContext CREATE_GLOBAL_ACC_CTXT = createPermAccCtxt("nashorn.createGlobal");
  
  private final ScriptEngineFactory factory;
  
  private final Context nashornContext;
  
  private final boolean _global_per_engine;
  
  private final Global global;
  
  private static final String MESSAGES_RESOURCE = "org.openjdk.nashorn.api.scripting.resources.Messages";
  
  private static final ResourceBundle MESSAGES_BUNDLE = ResourceBundle.getBundle("org.openjdk.nashorn.api.scripting.resources.Messages", Locale.getDefault());
  
  private static String getMessage(String msgId, String... args) {
    try {
      return (new MessageFormat(MESSAGES_BUNDLE.getString(msgId))).format(args);
    } catch (MissingResourceException e) {
      throw new RuntimeException("no message resource found for message id: " + msgId);
    } 
  }
  
  NashornScriptEngine(NashornScriptEngineFactory factory, String[] args, ClassLoader appLoader, ClassFilter classFilter) {
    assert args != null : "null argument array";
    this.factory = factory;
    Options options = new Options("nashorn");
    options.process(args);
    Context.ThrowErrorManager throwErrorManager = new Context.ThrowErrorManager();
    this.nashornContext = AccessController.<Context>doPrivileged(() -> {
          try {
            return new Context(options, errMgr, appLoader, classFilter);
          } catch (RuntimeException e) {
            if (Context.DEBUG)
              e.printStackTrace(); 
            throw e;
          } 
        }CREATE_CONTEXT_ACC_CTXT);
    this._global_per_engine = (this.nashornContext.getEnv())._global_per_engine;
    this.global = createNashornGlobal();
    this.context.setBindings(new ScriptObjectMirror((ScriptObject)this.global, this.global), 100);
  }
  
  public Object eval(Reader reader, ScriptContext ctxt) throws ScriptException {
    return evalImpl(makeSource(reader, ctxt), ctxt);
  }
  
  public Object eval(String script, ScriptContext ctxt) throws ScriptException {
    return evalImpl(makeSource(script, ctxt), ctxt);
  }
  
  public ScriptEngineFactory getFactory() {
    return this.factory;
  }
  
  public Bindings createBindings() {
    if (this._global_per_engine)
      return new SimpleBindings(); 
    return createGlobalMirror();
  }
  
  public CompiledScript compile(Reader reader) throws ScriptException {
    return asCompiledScript(makeSource(reader, this.context));
  }
  
  public CompiledScript compile(String str) throws ScriptException {
    return asCompiledScript(makeSource(str, this.context));
  }
  
  public Object invokeFunction(String name, Object... args) throws ScriptException, NoSuchMethodException {
    return invokeImpl(null, name, args);
  }
  
  public Object invokeMethod(Object thiz, String name, Object... args) throws ScriptException, NoSuchMethodException {
    if (thiz == null)
      throw new IllegalArgumentException(getMessage("thiz.cannot.be.null", new String[0])); 
    return invokeImpl(thiz, name, args);
  }
  
  public <T> T getInterface(Class<T> clazz) {
    return getInterfaceInner(null, clazz);
  }
  
  public <T> T getInterface(Object thiz, Class<T> clazz) {
    if (thiz == null)
      throw new IllegalArgumentException(getMessage("thiz.cannot.be.null", new String[0])); 
    return getInterfaceInner(thiz, clazz);
  }
  
  private static Source makeSource(Reader reader, ScriptContext ctxt) throws ScriptException {
    try {
      return Source.sourceFor(getScriptName(ctxt), reader);
    } catch (IOException e) {
      throw new ScriptException(e);
    } 
  }
  
  private static Source makeSource(String src, ScriptContext ctxt) {
    return Source.sourceFor(getScriptName(ctxt), src);
  }
  
  private static String getScriptName(ScriptContext ctxt) {
    Object val = ctxt.getAttribute("javax.script.filename");
    return (val != null) ? val.toString() : "<eval>";
  }
  
  private <T> T getInterfaceInner(Object thiz, Class<T> clazz) {
    assert !(thiz instanceof ScriptObject) : "raw ScriptObject not expected here";
    if (clazz == null || !clazz.isInterface())
      throw new IllegalArgumentException(getMessage("interface.class.expected", new String[0])); 
    SecurityManager sm = System.getSecurityManager();
    if (sm != null) {
      if (!Modifier.isPublic(clazz.getModifiers()))
        throw new SecurityException(getMessage("implementing.non.public.interface", new String[] { clazz.getName() })); 
      Context.checkPackageAccess(clazz);
    } 
    ScriptObject realSelf = null;
    Global realGlobal = null;
    if (thiz == null) {
      Global global = realGlobal = getNashornGlobalFrom(this.context);
    } else if (thiz instanceof ScriptObjectMirror) {
      ScriptObjectMirror mirror = (ScriptObjectMirror)thiz;
      realSelf = mirror.getScriptObject();
      realGlobal = mirror.getHomeGlobal();
      if (!isOfContext(realGlobal, this.nashornContext))
        throw new IllegalArgumentException(getMessage("script.object.from.another.engine", new String[0])); 
    } 
    if (realSelf == null)
      throw new IllegalArgumentException(getMessage("interface.on.non.script.object", new String[0])); 
    try {
      Global oldGlobal = Context.getGlobal();
      boolean globalChanged = (oldGlobal != realGlobal);
      try {
        if (globalChanged)
          Context.setGlobal(realGlobal); 
        if (!isInterfaceImplemented(clazz, realSelf))
          return null; 
        return clazz.cast(JavaAdapterFactory.getConstructor(realSelf.getClass(), clazz, 
              MethodHandles.publicLookup()).invoke(realSelf));
      } finally {
        if (globalChanged)
          Context.setGlobal(oldGlobal); 
      } 
    } catch (RuntimeException|Error e) {
      throw e;
    } catch (Throwable t) {
      throw new RuntimeException(t);
    } 
  }
  
  private Global getNashornGlobalFrom(ScriptContext ctxt) {
    if (this._global_per_engine)
      return this.global; 
    Bindings bindings = ctxt.getBindings(100);
    if (bindings instanceof ScriptObjectMirror) {
      Global glob = globalFromMirror((ScriptObjectMirror)bindings);
      if (glob != null)
        return glob; 
    } 
    Object scope = bindings.get("nashorn.global");
    if (scope instanceof ScriptObjectMirror) {
      Global glob = globalFromMirror((ScriptObjectMirror)scope);
      if (glob != null)
        return glob; 
    } 
    ScriptObjectMirror mirror = createGlobalMirror();
    bindings.put("nashorn.global", mirror);
    mirror.getHomeGlobal().setInitScriptContext(ctxt);
    return mirror.getHomeGlobal();
  }
  
  private Global globalFromMirror(ScriptObjectMirror mirror) {
    ScriptObject sobj = mirror.getScriptObject();
    if (sobj instanceof Global && isOfContext((Global)sobj, this.nashornContext))
      return (Global)sobj; 
    return null;
  }
  
  private ScriptObjectMirror createGlobalMirror() {
    Global newGlobal = createNashornGlobal();
    return new ScriptObjectMirror((ScriptObject)newGlobal, newGlobal);
  }
  
  private Global createNashornGlobal() {
    Global newGlobal = AccessController.<Global>doPrivileged(() -> {
          try {
            return this.nashornContext.newGlobal();
          } catch (RuntimeException e) {
            if (Context.DEBUG)
              e.printStackTrace(); 
            throw e;
          } 
        }CREATE_GLOBAL_ACC_CTXT);
    this.nashornContext.initGlobal(newGlobal, this);
    return newGlobal;
  }
  
  private Object invokeImpl(Object selfObject, String name, Object... args) throws ScriptException, NoSuchMethodException {
    Objects.requireNonNull(name);
    assert !(selfObject instanceof ScriptObject) : "raw ScriptObject not expected here";
    Global invokeGlobal = null;
    ScriptObjectMirror selfMirror = null;
    if (selfObject instanceof ScriptObjectMirror) {
      selfMirror = (ScriptObjectMirror)selfObject;
      if (!isOfContext(selfMirror.getHomeGlobal(), this.nashornContext))
        throw new IllegalArgumentException(getMessage("script.object.from.another.engine", new String[0])); 
      invokeGlobal = selfMirror.getHomeGlobal();
    } else if (selfObject == null) {
      Global ctxtGlobal = getNashornGlobalFrom(this.context);
      invokeGlobal = ctxtGlobal;
      selfMirror = (ScriptObjectMirror)ScriptObjectMirror.wrap(ctxtGlobal, ctxtGlobal);
    } 
    if (selfMirror != null)
      try {
        return ScriptObjectMirror.translateUndefined(selfMirror.callMember(name, args));
      } catch (Exception e) {
        Throwable cause = e.getCause();
        if (cause instanceof NoSuchMethodException)
          throw (NoSuchMethodException)cause; 
        throwAsScriptException(e, invokeGlobal);
        throw new AssertionError("should not reach here");
      }  
    throw new IllegalArgumentException(getMessage("interface.on.non.script.object", new String[0]));
  }
  
  private Object evalImpl(Source src, ScriptContext ctxt) throws ScriptException {
    return evalImpl(compileImpl(src, ctxt), ctxt);
  }
  
  private Object evalImpl(ScriptFunction script, ScriptContext ctxt) throws ScriptException {
    return evalImpl(script, ctxt, getNashornGlobalFrom(ctxt));
  }
  
  private Object evalImpl(Context.MultiGlobalCompiledScript mgcs, ScriptContext ctxt, Global ctxtGlobal) throws ScriptException {
    Global oldGlobal = Context.getGlobal();
    boolean globalChanged = (oldGlobal != ctxtGlobal);
    try {
      if (globalChanged)
        Context.setGlobal(ctxtGlobal); 
      ScriptFunction script = mgcs.getFunction(ctxtGlobal);
      ScriptContext oldCtxt = ctxtGlobal.getScriptContext();
      ctxtGlobal.setScriptContext(ctxt);
    } catch (Exception e) {
      throwAsScriptException(e, ctxtGlobal);
      throw new AssertionError("should not reach here");
    } finally {
      if (globalChanged)
        Context.setGlobal(oldGlobal); 
    } 
  }
  
  private Object evalImpl(ScriptFunction script, ScriptContext ctxt, Global ctxtGlobal) throws ScriptException {
    if (script == null)
      return null; 
    Global oldGlobal = Context.getGlobal();
    boolean globalChanged = (oldGlobal != ctxtGlobal);
    try {
      if (globalChanged)
        Context.setGlobal(ctxtGlobal); 
      ScriptContext oldCtxt = ctxtGlobal.getScriptContext();
      ctxtGlobal.setScriptContext(ctxt);
    } catch (Exception e) {
      throwAsScriptException(e, ctxtGlobal);
      throw new AssertionError("should not reach here");
    } finally {
      if (globalChanged)
        Context.setGlobal(oldGlobal); 
    } 
  }
  
  private static void throwAsScriptException(Exception e, Global global) throws ScriptException {
    if (e instanceof ScriptException)
      throw (ScriptException)e; 
    if (e instanceof NashornException) {
      NashornException ne = (NashornException)e;
      ScriptException se = new ScriptException(ne.getMessage(), ne.getFileName(), ne.getLineNumber(), ne.getColumnNumber());
      ne.initEcmaError((ScriptObject)global);
      se.initCause(e);
      throw se;
    } 
    if (e instanceof RuntimeException)
      throw (RuntimeException)e; 
    throw new ScriptException(e);
  }
  
  private CompiledScript asCompiledScript(Source source) throws ScriptException {
    final Context.MultiGlobalCompiledScript mgcs;
    final ScriptFunction func;
    Global oldGlobal = Context.getGlobal();
    Global newGlobal = getNashornGlobalFrom(this.context);
    boolean globalChanged = (oldGlobal != newGlobal);
    try {
      if (globalChanged)
        Context.setGlobal(newGlobal); 
      mgcs = this.nashornContext.compileScript(source);
      func = mgcs.getFunction(newGlobal);
    } catch (Exception e) {
      throwAsScriptException(e, newGlobal);
      throw new AssertionError("should not reach here");
    } finally {
      if (globalChanged)
        Context.setGlobal(oldGlobal); 
    } 
    return new CompiledScript() {
        public Object eval(ScriptContext ctxt) throws ScriptException {
          Global globalObject = NashornScriptEngine.this.getNashornGlobalFrom(ctxt);
          if (func.getScope() == globalObject)
            return NashornScriptEngine.this.evalImpl(func, ctxt, globalObject); 
          return NashornScriptEngine.this.evalImpl(mgcs, ctxt, globalObject);
        }
        
        public ScriptEngine getEngine() {
          return NashornScriptEngine.this;
        }
      };
  }
  
  private ScriptFunction compileImpl(Source source, ScriptContext ctxt) throws ScriptException {
    return compileImpl(source, getNashornGlobalFrom(ctxt));
  }
  
  private ScriptFunction compileImpl(Source source, Global newGlobal) throws ScriptException {
    Global oldGlobal = Context.getGlobal();
    boolean globalChanged = (oldGlobal != newGlobal);
    try {
      if (globalChanged)
        Context.setGlobal(newGlobal); 
      return this.nashornContext.compileScript(source, (ScriptObject)newGlobal);
    } catch (Exception e) {
      throwAsScriptException(e, newGlobal);
      throw new AssertionError("should not reach here");
    } finally {
      if (globalChanged)
        Context.setGlobal(oldGlobal); 
    } 
  }
  
  private static boolean isInterfaceImplemented(Class<?> iface, ScriptObject sobj) {
    for (Method method : iface.getMethods()) {
      if (method.getDeclaringClass() != Object.class)
        if (Modifier.isAbstract(method.getModifiers())) {
          Object obj = sobj.get(method.getName());
          if (!(obj instanceof ScriptFunction))
            return false; 
        }  
    } 
    return true;
  }
  
  private static boolean isOfContext(Global global, Context context) {
    return global.isOfContext(context);
  }
}
