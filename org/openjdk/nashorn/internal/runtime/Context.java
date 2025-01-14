package org.openjdk.nashorn.internal.runtime;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.SwitchPoint;
import java.lang.module.Configuration;
import java.lang.module.ModuleDescriptor;
import java.lang.module.ModuleFinder;
import java.lang.module.ModuleReader;
import java.lang.module.ModuleReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.CodeSigner;
import java.security.CodeSource;
import java.security.Permissions;
import java.security.PrivilegedAction;
import java.security.PrivilegedActionException;
import java.security.ProtectionDomain;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.script.ScriptEngine;
import jdk.dynalink.DynamicLinker;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.util.CheckClassAdapter;
import org.openjdk.nashorn.api.scripting.ClassFilter;
import org.openjdk.nashorn.api.scripting.ScriptObjectMirror;
import org.openjdk.nashorn.internal.WeakValueCache;
import org.openjdk.nashorn.internal.codegen.Compiler;
import org.openjdk.nashorn.internal.codegen.CompilerConstants;
import org.openjdk.nashorn.internal.ir.FunctionNode;
import org.openjdk.nashorn.internal.ir.Node;
import org.openjdk.nashorn.internal.ir.debug.ASTWriter;
import org.openjdk.nashorn.internal.ir.debug.PrintVisitor;
import org.openjdk.nashorn.internal.lookup.MethodHandleFactory;
import org.openjdk.nashorn.internal.objects.Global;
import org.openjdk.nashorn.internal.parser.Parser;
import org.openjdk.nashorn.internal.runtime.events.RuntimeEvent;
import org.openjdk.nashorn.internal.runtime.linker.Bootstrap;
import org.openjdk.nashorn.internal.runtime.logging.DebugLogger;
import org.openjdk.nashorn.internal.runtime.logging.Loggable;
import org.openjdk.nashorn.internal.runtime.logging.Logger;
import org.openjdk.nashorn.internal.runtime.options.LoggingOption;
import org.openjdk.nashorn.internal.runtime.options.Options;
import sun.misc.Unsafe;

public final class Context {
  public static final String NASHORN_SET_CONFIG = "nashorn.setConfig";
  
  public static final String NASHORN_CREATE_CONTEXT = "nashorn.createContext";
  
  public static final String NASHORN_CREATE_GLOBAL = "nashorn.createGlobal";
  
  public static final String NASHORN_GET_CONTEXT = "nashorn.getContext";
  
  public static final String NASHORN_JAVA_REFLECTION = "nashorn.JavaReflection";
  
  public static final String NASHORN_DEBUG_MODE = "nashorn.debugMode";
  
  private static final String LOAD_CLASSPATH = "classpath:";
  
  private static final String LOAD_FX = "fx:";
  
  private static final String LOAD_NASHORN = "nashorn:";
  
  private static final MethodHandles.Lookup LOOKUP = MethodHandles.lookup();
  
  private static final MethodType CREATE_PROGRAM_FUNCTION_TYPE = MethodType.methodType(ScriptFunction.class, ScriptObject.class);
  
  private static final LongAdder NAMED_INSTALLED_SCRIPT_COUNT = new LongAdder();
  
  private static final LongAdder ANONYMOUS_INSTALLED_SCRIPT_COUNT = new LongAdder();
  
  private final FieldMode fieldMode;
  
  private final Map<String, SwitchPoint> builtinSwitchPoints;
  
  private final WeakValueCache<CodeSource, Class<?>> anonymousHostClasses;
  
  private enum FieldMode {
    AUTO, OBJECTS, DUAL;
  }
  
  static {
    DebuggerSupport.FORCELOAD = true;
  }
  
  static long getNamedInstalledScriptCount() {
    return NAMED_INSTALLED_SCRIPT_COUNT.sum();
  }
  
  static long getAnonymousInstalledScriptCount() {
    return ANONYMOUS_INSTALLED_SCRIPT_COUNT.sum();
  }
  
  private static abstract class ContextCodeInstaller implements CodeInstaller {
    final Context context;
    
    final CodeSource codeSource;
    
    ContextCodeInstaller(Context context, CodeSource codeSource) {
      this.context = context;
      this.codeSource = codeSource;
    }
    
    public Context getContext() {
      return this.context;
    }
    
    public void initialize(Collection<Class<?>> classes, Source source, Object[] constants) {
      try {
        AccessController.doPrivileged(() -> {
              for (Class<?> clazz : (Iterable<Class<?>>)classes) {
                Field sourceField = clazz.getDeclaredField(CompilerConstants.SOURCE.symbolName());
                sourceField.setAccessible(true);
                sourceField.set(null, source);
                Field constantsField = clazz.getDeclaredField(CompilerConstants.CONSTANTS.symbolName());
                constantsField.setAccessible(true);
                constantsField.set(null, constants);
              } 
              return null;
            });
      } catch (PrivilegedActionException e) {
        throw new RuntimeException(e);
      } 
    }
    
    public void verify(byte[] code) {
      this.context.verify(code);
    }
    
    public long getUniqueScriptId() {
      return this.context.getUniqueScriptId();
    }
    
    public void storeScript(String cacheKey, Source source, String mainClassName, Map<String, byte[]> classBytes, Map<Integer, FunctionInitializer> initializers, Object[] constants, int compilationId) {
      if (this.context.codeStore != null)
        this.context.codeStore.store(cacheKey, source, mainClassName, classBytes, initializers, constants, compilationId); 
    }
    
    public StoredScript loadScript(Source source, String functionKey) {
      if (this.context.codeStore != null)
        return this.context.codeStore.load(source, functionKey); 
      return null;
    }
    
    public boolean isCompatibleWith(CodeInstaller other) {
      if (other instanceof ContextCodeInstaller) {
        ContextCodeInstaller cci = (ContextCodeInstaller)other;
        return (cci.context == this.context && cci.codeSource == this.codeSource);
      } 
      return false;
    }
  }
  
  private static class NamedContextCodeInstaller extends ContextCodeInstaller {
    private final ScriptLoader loader;
    
    private int usageCount = 0;
    
    private int bytesDefined = 0;
    
    private static final int MAX_USAGES = 10;
    
    private static final int MAX_BYTES_DEFINED = 200000;
    
    private NamedContextCodeInstaller(Context context, CodeSource codeSource, ScriptLoader loader) {
      super(context, codeSource);
      this.loader = loader;
    }
    
    public Class<?> install(String className, byte[] bytecode) {
      this.usageCount++;
      this.bytesDefined += bytecode.length;
      Context.NAMED_INSTALLED_SCRIPT_COUNT.increment();
      return this.loader.installClass(Compiler.binaryName(className), bytecode, this.codeSource);
    }
    
    public CodeInstaller getOnDemandCompilationInstaller() {
      if (this.usageCount < 10 && this.bytesDefined < 200000)
        return this; 
      return new NamedContextCodeInstaller(this.context, this.codeSource, this.context.createNewLoader());
    }
    
    public CodeInstaller getMultiClassCodeInstaller() {
      return this;
    }
  }
  
  private static final class AnonymousContextCodeInstaller extends ContextCodeInstaller {
    private static final MethodHandle DEFINE_ANONYMOUS_CLASS = getDefineAnonymousClass();
    
    private static final String ANONYMOUS_HOST_CLASS_NAME = "org/openjdk/nashorn/internal/scripts".replace('/', '.') + ".AnonymousHost";
    
    private static final byte[] ANONYMOUS_HOST_CLASS_BYTES = getAnonymousHostClassBytes();
    
    static volatile Exception initFailure;
    
    private final Class<?> hostClass;
    
    private static MethodHandle getDefineAnonymousClass() {
      return AccessController.<MethodHandle>doPrivileged(() -> {
            try {
              MethodHandle mh = MethodHandles.lookup().findVirtual(Unsafe.class, "defineAnonymousClass", MethodType.methodType(Class.class, Class.class, new Class[] { byte[].class, Object[].class }));
              Field f = Unsafe.class.getDeclaredField("theUnsafe");
              f.setAccessible(true);
              return mh.bindTo(f.get(null));
            } catch (Exception e) {
              initFailure = e;
              return null;
            } 
          });
    }
    
    private AnonymousContextCodeInstaller(Context context, CodeSource codeSource, Class<?> hostClass) {
      super(context, codeSource);
      this.hostClass = hostClass;
    }
    
    public Class<?> install(String className, byte[] bytecode) {
      Context.ANONYMOUS_INSTALLED_SCRIPT_COUNT.increment();
      try {
        return DEFINE_ANONYMOUS_CLASS.invokeExact(this.hostClass, bytecode, (Object[])null);
      } catch (RuntimeException|Error e) {
        throw e;
      } catch (Throwable e) {
        throw new RuntimeException(e);
      } 
    }
    
    public CodeInstaller getOnDemandCompilationInstaller() {
      return this;
    }
    
    public CodeInstaller getMultiClassCodeInstaller() {
      return new Context.NamedContextCodeInstaller(this.context, this.codeSource, this.context.createNewLoader());
    }
    
    private static byte[] getAnonymousHostClassBytes() {
      ClassWriter cw = new ClassWriter(3);
      cw.visit(51, 1536, ANONYMOUS_HOST_CLASS_NAME.replace('.', '/'), null, "java/lang/Object", null);
      cw.visitEnd();
      return cw.toByteArray();
    }
  }
  
  public static final boolean DEBUG = Options.getBooleanProperty("nashorn.debug");
  
  private static final ThreadLocal<Global> currentGlobal = new ThreadLocal<>();
  
  private ClassCache classCache;
  
  private CodeStore codeStore;
  
  private final AtomicReference<GlobalConstants> globalConstantsRef;
  
  static final boolean javaSqlFound;
  
  static final boolean javaSqlRowsetFound;
  
  private final ScriptEnvironment env;
  
  final boolean _strict;
  
  private final ClassLoader appLoader;
  
  private final ScriptLoader scriptLoader;
  
  private final DynamicLinker dynamicLinker;
  
  private final ErrorManager errors;
  
  private final AtomicLong uniqueScriptId;
  
  private final ClassFilter classFilter;
  
  private static final StructureLoader theStructLoader;
  
  static {
    ModuleLayer boot = ModuleLayer.boot();
    javaSqlFound = boot.findModule("java.sql").isPresent();
    javaSqlRowsetFound = boot.findModule("java.sql.rowset").isPresent();
  }
  
  public static Global getGlobal() {
    return currentGlobal.get();
  }
  
  public static void setGlobal(ScriptObject global) {
    if (global != null && !(global instanceof Global))
      throw new IllegalArgumentException("not a global!"); 
    setGlobal((Global)global);
  }
  
  public static void setGlobal(Global global) {
    assert getGlobal() != global;
    if (global != null) {
      GlobalConstants globalConstants = getContext(global).getGlobalConstants();
      if (globalConstants != null)
        globalConstants.invalidateAll(); 
    } 
    currentGlobal.set(global);
  }
  
  public static Context getContext() {
    SecurityManager sm = System.getSecurityManager();
    if (sm != null)
      sm.checkPermission(new RuntimePermission("nashorn.getContext")); 
    return getContextTrusted();
  }
  
  public static PrintWriter getCurrentErr() {
    Global global = getGlobal();
    return (global != null) ? global.getContext().getErr() : new PrintWriter(System.err);
  }
  
  public static void err(String str) {
    err(str, true);
  }
  
  public static void err(String str, boolean crlf) {
    PrintWriter err = getCurrentErr();
    if (err != null)
      if (crlf) {
        err.println(str);
      } else {
        err.print(str);
      }  
  }
  
  ClassLoader getAppLoader() {
    return this.appLoader;
  }
  
  private static final ConcurrentMap<String, Class<?>> structureClasses = new ConcurrentHashMap<>();
  
  StructureLoader getStructLoader() {
    return theStructLoader;
  }
  
  private static AccessControlContext createNoPermAccCtxt() {
    return new AccessControlContext(new ProtectionDomain[] { new ProtectionDomain(null, new Permissions()) });
  }
  
  private static AccessControlContext createPermAccCtxt(String permName) {
    Permissions perms = new Permissions();
    perms.add(new RuntimePermission(permName));
    return new AccessControlContext(new ProtectionDomain[] { new ProtectionDomain(null, perms) });
  }
  
  private static final AccessControlContext NO_PERMISSIONS_ACC_CTXT = createNoPermAccCtxt();
  
  private static final AccessControlContext CREATE_LOADER_ACC_CTXT = createPermAccCtxt("createClassLoader");
  
  private static final AccessControlContext CREATE_GLOBAL_ACC_CTXT = createPermAccCtxt("nashorn.createGlobal");
  
  private static final AccessControlContext GET_LOADER_ACC_CTXT = createPermAccCtxt("getClassLoader");
  
  private final Map<String, DebugLogger> loggers;
  
  static {
    ClassLoader myLoader = Context.class.getClassLoader();
    theStructLoader = AccessController.<StructureLoader>doPrivileged(() -> new StructureLoader(myLoader), CREATE_LOADER_ACC_CTXT);
  }
  
  public static class ThrowErrorManager extends ErrorManager {
    public void error(String message) {
      throw new ParserException(message);
    }
    
    public void error(ParserException e) {
      throw e;
    }
  }
  
  public Context(Options options, ErrorManager errors, ClassLoader appLoader) {
    this(options, errors, appLoader, null);
  }
  
  public Context(Options options, ErrorManager errors, ClassLoader appLoader, ClassFilter classFilter) {
    this(options, errors, new PrintWriter(System.out, true), new PrintWriter(System.err, true), appLoader, classFilter);
  }
  
  public Context(Options options, ErrorManager errors, PrintWriter out, PrintWriter err, ClassLoader appLoader) {
    this(options, errors, out, err, appLoader, null);
  }
  
  public Context(Options options, ErrorManager errors, PrintWriter out, PrintWriter err, ClassLoader appLoader, ClassFilter classFilter) {
    ClassLoader appCl;
    this.builtinSwitchPoints = new HashMap<>();
    this.anonymousHostClasses = new WeakValueCache();
    this.globalConstantsRef = new AtomicReference<>();
    this.loggers = new HashMap<>();
    SecurityManager sm = System.getSecurityManager();
    if (sm != null)
      sm.checkPermission(new RuntimePermission("nashorn.createContext")); 
    this.classFilter = classFilter;
    this.env = new ScriptEnvironment(options, out, err);
    this._strict = this.env._strict;
    if (this.env._loader_per_compile) {
      this.scriptLoader = null;
      this.uniqueScriptId = null;
    } else {
      this.scriptLoader = createNewLoader();
      this.uniqueScriptId = new AtomicLong();
    } 
    this.errors = errors;
    String modulePath = this.env._module_path;
    if (!this.env._compile_only && modulePath != null && !modulePath.isEmpty()) {
      if (sm != null)
        sm.checkCreateClassLoader(); 
      appCl = AccessController.<ClassLoader>doPrivileged(() -> createModuleLoader(appLoader, modulePath, this.env._add_modules));
    } else {
      appCl = appLoader;
    } 
    String classPath = this.env._classpath;
    if (!this.env._compile_only && classPath != null && !classPath.isEmpty()) {
      if (sm != null)
        sm.checkCreateClassLoader(); 
      appCl = NashornLoader.createClassLoader(classPath, appCl);
    } 
    this.appLoader = appCl;
    this.dynamicLinker = Bootstrap.createDynamicLinker(this.appLoader, this.env._unstable_relink_threshold);
    int cacheSize = this.env._class_cache_size;
    if (cacheSize > 0)
      this.classCache = new ClassCache(this, cacheSize); 
    if (this.env._persistent_cache)
      this.codeStore = CodeStore.newCodeStore(this); 
    if (this.env._version)
      getErr().println("nashorn " + Version.version()); 
    if (this.env._fullversion)
      getErr().println("nashorn full version " + Version.fullVersion()); 
    if (Options.getBooleanProperty("nashorn.fields.dual")) {
      this.fieldMode = FieldMode.DUAL;
    } else if (Options.getBooleanProperty("nashorn.fields.objects")) {
      this.fieldMode = FieldMode.OBJECTS;
    } else {
      this.fieldMode = FieldMode.AUTO;
    } 
    initLoggers();
  }
  
  public ClassFilter getClassFilter() {
    return this.classFilter;
  }
  
  GlobalConstants getGlobalConstants() {
    return this.globalConstantsRef.get();
  }
  
  public ErrorManager getErrorManager() {
    return this.errors;
  }
  
  public ScriptEnvironment getEnv() {
    return this.env;
  }
  
  public PrintWriter getOut() {
    return this.env.getOut();
  }
  
  public PrintWriter getErr() {
    return this.env.getErr();
  }
  
  public boolean useDualFields() {
    return (this.fieldMode == FieldMode.DUAL || (this.fieldMode == FieldMode.AUTO && this.env._optimistic_types));
  }
  
  public static PropertyMap getGlobalMap() {
    return getGlobal().getMap();
  }
  
  public ScriptFunction compileScript(Source source, ScriptObject scope) {
    return compileScript(source, scope, this.errors);
  }
  
  public MultiGlobalCompiledScript compileScript(Source source) {
    Class<?> clazz = compile(source, this.errors, this._strict);
    MethodHandle createProgramFunctionHandle = getCreateProgramFunctionHandle(clazz);
    return newGlobal -> invokeCreateProgramFunctionHandle(createProgramFunctionHandle, (ScriptObject)newGlobal);
  }
  
  public Object eval(ScriptObject initialScope, String string, Object callThis, Object location) {
    return eval(initialScope, string, callThis, location, false, false);
  }
  
  public Object eval(ScriptObject initialScope, String string, Object callThis, Object location, boolean strict, boolean evalCall) {
    Class<?> clazz;
    Object evalThis;
    String file = (location == ScriptRuntime.UNDEFINED || location == null) ? "<eval>" : location.toString();
    Source source = Source.sourceFor(file, string, evalCall);
    boolean directEval = (evalCall && location != ScriptRuntime.UNDEFINED);
    Global global = getGlobal();
    ScriptObject scope = initialScope;
    boolean strictFlag = (strict || this._strict);
    try {
      clazz = compile(source, new ThrowErrorManager(), strictFlag);
    } catch (ParserException e) {
      e.throwAsEcmaException(global);
      return null;
    } 
    if (!strictFlag)
      try {
        strictFlag = clazz.getField(CompilerConstants.STRICT_MODE.symbolName()).getBoolean(null);
      } catch (NoSuchFieldException|SecurityException|IllegalArgumentException|IllegalAccessException e) {
        strictFlag = false;
      }  
    if (strictFlag)
      scope = newScope(scope); 
    ScriptFunction func = getProgramFunction(clazz, scope);
    if (directEval) {
      evalThis = ((callThis != ScriptRuntime.UNDEFINED && callThis != null) || strictFlag) ? callThis : global;
    } else {
      evalThis = callThis;
    } 
    return ScriptRuntime.apply(func, evalThis, new Object[0]);
  }
  
  private static ScriptObject newScope(ScriptObject callerScope) {
    return new Scope(callerScope, PropertyMap.newMap((Class)Scope.class));
  }
  
  private static Source loadInternal(String srcStr, String prefix, String resourcePath) {
    if (srcStr.startsWith(prefix)) {
      String resource = resourcePath + resourcePath;
      return AccessController.<Source>doPrivileged(() -> {
            try {
              InputStream resStream = Context.class.getResourceAsStream(resource);
              return (resStream != null) ? Source.sourceFor(srcStr, Source.readFully(resStream)) : null;
            } catch (IOException exp) {
              return null;
            } 
          });
    } 
    return null;
  }
  
  public Object load(Object scope, Object from) throws IOException {
    Object src = (from instanceof ConsString) ? from.toString() : from;
    Source source = null;
    if (src instanceof String) {
      String srcStr = (String)src;
      if (srcStr.startsWith("classpath:")) {
        URL url = getResourceURL(srcStr.substring("classpath:".length()));
        source = (url != null) ? Source.sourceFor(url.toString(), url) : null;
      } else {
        File file = new File(srcStr);
        if (srcStr.indexOf(':') != -1) {
          if ((source = loadInternal(srcStr, "nashorn:", "resources/")) == null && (source = loadInternal(srcStr, "fx:", "resources/fx/")) == null) {
            URL url;
            try {
              url = new URL(srcStr);
            } catch (MalformedURLException e) {
              url = file.toURI().toURL();
            } 
            source = Source.sourceFor(url.toString(), url);
          } 
        } else if (file.isFile()) {
          source = Source.sourceFor(srcStr, file);
        } 
      } 
    } else if (src instanceof File && ((File)src).isFile()) {
      File file = (File)src;
      source = Source.sourceFor(file.getName(), file);
    } else if (src instanceof URL) {
      URL url = (URL)src;
      source = Source.sourceFor(url.toString(), url);
    } else if (src instanceof ScriptObject) {
      ScriptObject sobj = (ScriptObject)src;
      if (sobj.has("script") && sobj.has("name")) {
        String script = JSType.toString(sobj.get("script"));
        String name = JSType.toString(sobj.get("name"));
        source = Source.sourceFor(name, script);
      } 
    } else if (src instanceof Map) {
      Map<?, ?> map = (Map<?, ?>)src;
      if (map.containsKey("script") && map.containsKey("name")) {
        String script = JSType.toString(map.get("script"));
        String name = JSType.toString(map.get("name"));
        source = Source.sourceFor(name, script);
      } 
    } 
    if (source != null) {
      if (scope instanceof ScriptObject && ((ScriptObject)scope).isScope()) {
        ScriptObject sobj = (ScriptObject)scope;
        assert sobj.isGlobal() : "non-Global scope object!!";
        return evaluateSource(source, sobj, sobj);
      } 
      if (scope == null || scope == ScriptRuntime.UNDEFINED) {
        Global global1 = getGlobal();
        return evaluateSource(source, (ScriptObject)global1, (ScriptObject)global1);
      } 
      Global global = getGlobal();
      ScriptObject evalScope = newScope((ScriptObject)global);
      ScriptObject withObj = ScriptRuntime.openWith(evalScope, scope);
      return evaluateSource(source, withObj, (ScriptObject)global);
    } 
    throw ECMAErrors.typeError("cant.load.script", new String[] { ScriptRuntime.safeToString(from) });
  }
  
  public Object loadWithNewGlobal(Object from, Object... args) throws IOException {
    Global oldGlobal = getGlobal();
    Global newGlobal = AccessController.<Global>doPrivileged(() -> {
          try {
            return newGlobal();
          } catch (RuntimeException e) {
            if (DEBUG)
              e.printStackTrace(); 
            throw e;
          } 
        }CREATE_GLOBAL_ACC_CTXT);
    initGlobal(newGlobal);
    setGlobal(newGlobal);
    Object[] wrapped = (args == null) ? ScriptRuntime.EMPTY_ARRAY : ScriptObjectMirror.wrapArray(args, oldGlobal);
    newGlobal.put("arguments", newGlobal.wrapAsObject(wrapped), this.env._strict);
    try {
      return ScriptObjectMirror.unwrap(ScriptObjectMirror.wrap(load(newGlobal, from), newGlobal), oldGlobal);
    } finally {
      setGlobal(oldGlobal);
    } 
  }
  
  public static Class<? extends ScriptObject> forStructureClass(String fullName) throws ClassNotFoundException {
    if (System.getSecurityManager() != null && !StructureLoader.isStructureClass(fullName))
      throw new ClassNotFoundException(fullName); 
    return (Class<? extends ScriptObject>)structureClasses.computeIfAbsent(fullName, name -> {
          try {
            return Class.forName(name, true, theStructLoader);
          } catch (ClassNotFoundException e) {
            throw new AssertionError(e);
          } 
        });
  }
  
  public static boolean isStructureClass(String className) {
    return StructureLoader.isStructureClass(className);
  }
  
  public static void checkPackageAccess(Class<?> clazz) {
    SecurityManager sm = System.getSecurityManager();
    if (sm != null) {
      Class<?> bottomClazz = clazz;
      while (bottomClazz.isArray())
        bottomClazz = bottomClazz.getComponentType(); 
      checkPackageAccess(sm, bottomClazz.getName());
    } 
  }
  
  public static void checkPackageAccess(String pkgName) {
    SecurityManager sm = System.getSecurityManager();
    if (sm != null)
      checkPackageAccess(sm, pkgName.endsWith(".") ? pkgName : (pkgName + ".")); 
  }
  
  private static void checkPackageAccess(SecurityManager sm, String fullName) {
    Objects.requireNonNull(sm);
    int index = fullName.lastIndexOf('.');
    if (index != -1) {
      String pkgName = fullName.substring(0, index);
      AccessController.doPrivileged(() -> {
            sm.checkPackageAccess(pkgName);
            return null;
          }NO_PERMISSIONS_ACC_CTXT);
    } 
  }
  
  private static boolean isAccessiblePackage(Class<?> clazz) {
    try {
      checkPackageAccess(clazz);
      return true;
    } catch (SecurityException se) {
      return false;
    } 
  }
  
  public static boolean isAccessibleClass(Class<?> clazz) {
    return (Modifier.isPublic(clazz.getModifiers()) && isAccessiblePackage(clazz));
  }
  
  public Class<?> findClass(String fullName) throws ClassNotFoundException {
    if (fullName.indexOf('[') != -1 || fullName.indexOf('/') != -1)
      throw new ClassNotFoundException(fullName); 
    if (this.classFilter != null && !this.classFilter.exposeToScripts(fullName))
      throw new ClassNotFoundException(fullName); 
    SecurityManager sm = System.getSecurityManager();
    if (sm != null)
      checkPackageAccess(sm, fullName); 
    if (this.appLoader != null)
      return Class.forName(fullName, true, this.appLoader); 
    Class<?> cl = Class.forName(fullName);
    if (cl.getClassLoader() == null)
      return cl; 
    throw new ClassNotFoundException(fullName);
  }
  
  public static void printStackTrace(Throwable t) {
    if (DEBUG)
      t.printStackTrace(getCurrentErr()); 
  }
  
  public void verify(byte[] bytecode) {
    if (this.env._verify_code)
      if (System.getSecurityManager() == null)
        CheckClassAdapter.verify(new ClassReader(bytecode), theStructLoader, false, new PrintWriter(System.err, true));  
  }
  
  public Global createGlobal() {
    return initGlobal(newGlobal());
  }
  
  public Global newGlobal() {
    createOrInvalidateGlobalConstants();
    return new Global(this);
  }
  
  private void createOrInvalidateGlobalConstants() {
    GlobalConstants newGlobalConstants;
    do {
      GlobalConstants currentGlobalConstants = getGlobalConstants();
      if (currentGlobalConstants != null) {
        currentGlobalConstants.invalidateForever();
        return;
      } 
      newGlobalConstants = new GlobalConstants(getLogger((Class)GlobalConstants.class));
    } while (!this.globalConstantsRef.compareAndSet(null, newGlobalConstants));
  }
  
  public Global initGlobal(Global global, ScriptEngine engine) {
    if (!this.env._compile_only) {
      Global oldGlobal = getGlobal();
      try {
        setGlobal(global);
        global.initBuiltinObjects(engine);
      } finally {
        setGlobal(oldGlobal);
      } 
    } 
    return global;
  }
  
  public Global initGlobal(Global global) {
    return initGlobal(global, null);
  }
  
  static Context getContextTrusted() {
    return getContext(getGlobal());
  }
  
  public static DynamicLinker getDynamicLinker(Class<?> clazz) {
    return (fromClass(clazz)).dynamicLinker;
  }
  
  public static DynamicLinker getDynamicLinker() {
    return (getContextTrusted()).dynamicLinker;
  }
  
  static Module createModuleTrusted(ModuleLayer parent, ModuleDescriptor descriptor, ClassLoader loader) {
    final String mn = descriptor.name();
    final ModuleReference mref = new ModuleReference(descriptor, null) {
        public ModuleReader open() {
          throw new UnsupportedOperationException();
        }
      };
    ModuleFinder finder = new ModuleFinder() {
        public Optional<ModuleReference> find(String name) {
          if (name.equals(mn))
            return Optional.of(mref); 
          return Optional.empty();
        }
        
        public Set<ModuleReference> findAll() {
          return Set.of(mref);
        }
      };
    Configuration cf = parent.configuration().resolve(finder, ModuleFinder.of(new Path[0]), Set.of(mn));
    PrivilegedAction<ModuleLayer> pa = () -> parent.defineModules(cf, ());
    ModuleLayer layer = AccessController.<ModuleLayer>doPrivileged(pa, GET_LOADER_ACC_CTXT);
    Module m = layer.findModule(mn).get();
    assert m.getLayer() == layer;
    return m;
  }
  
  static Context getContextTrustedOrNull() {
    Global global = getGlobal();
    return (global == null) ? null : getContext(global);
  }
  
  private static Context getContext(Global global) {
    return global.getContext();
  }
  
  static Context fromClass(Class<?> clazz) {
    ClassLoader loader = null;
    try {
      loader = clazz.getClassLoader();
    } catch (SecurityException securityException) {}
    if (loader instanceof ScriptLoader)
      return ((ScriptLoader)loader).getContext(); 
    return getContextTrusted();
  }
  
  private URL getResourceURL(String resName) {
    if (this.appLoader != null)
      return this.appLoader.getResource(resName); 
    return ClassLoader.getSystemResource(resName);
  }
  
  private Object evaluateSource(Source source, ScriptObject scope, ScriptObject thiz) {
    ScriptFunction script = null;
    try {
      script = compileScript(source, scope, new ThrowErrorManager());
    } catch (ParserException e) {
      e.throwAsEcmaException();
    } 
    return ScriptRuntime.apply(script, thiz, new Object[0]);
  }
  
  private static ScriptFunction getProgramFunction(Class<?> script, ScriptObject scope) {
    if (script == null)
      return null; 
    return invokeCreateProgramFunctionHandle(getCreateProgramFunctionHandle(script), scope);
  }
  
  private static MethodHandle getCreateProgramFunctionHandle(Class<?> script) {
    try {
      return LOOKUP.findStatic(script, CompilerConstants.CREATE_PROGRAM_FUNCTION.symbolName(), CREATE_PROGRAM_FUNCTION_TYPE);
    } catch (NoSuchMethodException|IllegalAccessException e) {
      throw new AssertionError("Failed to retrieve a handle for the program function for " + script.getName(), e);
    } 
  }
  
  private static ScriptFunction invokeCreateProgramFunctionHandle(MethodHandle createProgramFunctionHandle, ScriptObject scope) {
    try {
      return createProgramFunctionHandle.invokeExact(scope);
    } catch (RuntimeException|Error e) {
      throw e;
    } catch (Throwable t) {
      throw new AssertionError("Failed to create a program function", t);
    } 
  }
  
  private ScriptFunction compileScript(Source source, ScriptObject scope, ErrorManager errMan) {
    return getProgramFunction(compile(source, errMan, this._strict), scope);
  }
  
  private synchronized Class<?> compile(Source source, ErrorManager errMan, boolean strict) {
    CodeInstaller installer;
    errMan.reset();
    Class<?> script = findCachedClass(source);
    if (script != null) {
      DebugLogger log = getLogger((Class)Compiler.class);
      if (log.isEnabled())
        log.fine(new RuntimeEvent(Level.INFO, source), new Object[] { "Code cache hit for ", source, " avoiding recompile." }); 
      return script;
    } 
    StoredScript storedScript = null;
    FunctionNode functionNode = null;
    boolean useCodeStore = (this.codeStore != null && !this.env._parse_only && (!this.env._optimistic_types || this.env._lazy_compilation));
    String cacheKey = useCodeStore ? CodeStore.getCacheKey("script", null) : null;
    if (useCodeStore)
      storedScript = this.codeStore.load(source, cacheKey); 
    if (storedScript == null) {
      if (this.env._dest_dir != null)
        source.dump(this.env._dest_dir); 
      functionNode = (new Parser(this.env, source, errMan, strict, getLogger((Class)Parser.class))).parse();
      if (errMan.hasErrors())
        return null; 
      if (this.env._print_ast || functionNode.getDebugFlag(4))
        getErr().println(new ASTWriter((Node)functionNode)); 
      if (this.env._print_parse || functionNode.getDebugFlag(1))
        getErr().println(new PrintVisitor((Node)functionNode, true, false)); 
    } 
    if (this.env._parse_only)
      return null; 
    URL url = source.getURL();
    CodeSource cs = new CodeSource(url, (CodeSigner[])null);
    if (this.env._persistent_cache || !this.env._lazy_compilation || !this.env.useAnonymousClasses(source.getLength(), () -> AnonymousContextCodeInstaller.initFailure)) {
      ScriptLoader loader = this.env._loader_per_compile ? createNewLoader() : this.scriptLoader;
      installer = new NamedContextCodeInstaller(this, cs, loader);
    } else {
      installer = new AnonymousContextCodeInstaller(this, cs, (Class)this.anonymousHostClasses.getOrCreate(cs, key -> createNewLoader().installClass(AnonymousContextCodeInstaller.ANONYMOUS_HOST_CLASS_NAME, AnonymousContextCodeInstaller.ANONYMOUS_HOST_CLASS_BYTES, cs)));
    } 
    if (storedScript == null) {
      Compiler.CompilationPhases phases = Compiler.CompilationPhases.COMPILE_ALL;
      Compiler compiler = Compiler.forInitialCompilation(installer, source, errMan, strict | functionNode.isStrict());
      FunctionNode compiledFunction = compiler.compile(functionNode, phases);
      if (errMan.hasErrors())
        return null; 
      script = compiledFunction.getRootClass();
      compiler.persistClassInfo(cacheKey, compiledFunction);
    } else {
      Compiler.updateCompilationId(storedScript.getCompilationId());
      script = storedScript.installScript(source, installer);
    } 
    cacheClass(source, script);
    return script;
  }
  
  private ScriptLoader createNewLoader() {
    return AccessController.<ScriptLoader>doPrivileged(() -> new ScriptLoader(this), CREATE_LOADER_ACC_CTXT);
  }
  
  private long getUniqueScriptId() {
    return this.uniqueScriptId.getAndIncrement();
  }
  
  @Logger(name = "classcache")
  private static class ClassCache extends LinkedHashMap<Source, ClassReference> implements Loggable {
    private final int size;
    
    private final ReferenceQueue<Class<?>> queue;
    
    private final DebugLogger log;
    
    ClassCache(Context context, int size) {
      super(size, 0.75F, true);
      this.size = size;
      this.queue = new ReferenceQueue<>();
      this.log = initLogger(context);
    }
    
    void cache(Source source, Class<?> clazz) {
      if (this.log.isEnabled())
        this.log.info(new Object[] { "Caching ", source, " in class cache" }); 
      put(source, new Context.ClassReference(clazz, this.queue, source));
    }
    
    protected boolean removeEldestEntry(Map.Entry<Source, Context.ClassReference> eldest) {
      return (size() > this.size);
    }
    
    public Context.ClassReference get(Object key) {
      Context.ClassReference ref;
      while ((ref = (Context.ClassReference)this.queue.poll()) != null) {
        Source source = ref.source;
        if (this.log.isEnabled())
          this.log.info(new Object[] { "Evicting ", source, " from class cache." }); 
        remove(source);
      } 
      ref = super.get(key);
      if (ref != null && this.log.isEnabled())
        this.log.info(new Object[] { "Retrieved class reference for ", ref.source, " from class cache" }); 
      return ref;
    }
    
    public DebugLogger initLogger(Context context) {
      return context.getLogger((Class)getClass());
    }
    
    public DebugLogger getLogger() {
      return this.log;
    }
  }
  
  private static class ClassReference extends SoftReference<Class<?>> {
    private final Source source;
    
    ClassReference(Class<?> clazz, ReferenceQueue<Class<?>> queue, Source source) {
      super(clazz, queue);
      this.source = source;
    }
  }
  
  private Class<?> findCachedClass(Source source) {
    ClassReference ref = (this.classCache == null) ? null : this.classCache.get(source);
    return (ref != null) ? ref.get() : null;
  }
  
  private void cacheClass(Source source, Class<?> clazz) {
    if (this.classCache != null)
      this.classCache.cache(source, clazz); 
  }
  
  private void initLoggers() {
    ((Loggable)MethodHandleFactory.getFunctionality()).initLogger(this);
  }
  
  public DebugLogger getLogger(Class<? extends Loggable> clazz) {
    return getLogger(clazz, null);
  }
  
  public DebugLogger getLogger(Class<? extends Loggable> clazz, Consumer<DebugLogger> initHook) {
    String name = getLoggerName(clazz);
    DebugLogger logger = this.loggers.get(name);
    if (logger == null) {
      if (!this.env.hasLogger(name))
        return DebugLogger.DISABLED_LOGGER; 
      LoggingOption.LoggerInfo info = this.env._loggers.get(name);
      logger = new DebugLogger(name, info.getLevel(), info.isQuiet());
      if (initHook != null)
        initHook.accept(logger); 
      this.loggers.put(name, logger);
    } 
    return logger;
  }
  
  public MethodHandle addLoggingToHandle(Class<? extends Loggable> clazz, MethodHandle mh, Supplier<String> text) {
    return addLoggingToHandle(clazz, Level.INFO, mh, 2147483647, false, text);
  }
  
  public MethodHandle addLoggingToHandle(Class<? extends Loggable> clazz, Level level, MethodHandle mh, int paramStart, boolean printReturnValue, Supplier<String> text) {
    DebugLogger log = getLogger(clazz);
    if (log.isEnabled())
      return MethodHandleFactory.addDebugPrintout(log, level, mh, paramStart, printReturnValue, text.get()); 
    return mh;
  }
  
  private static String getLoggerName(Class<?> clazz) {
    Class<?> current = clazz;
    while (current != null) {
      Logger log = current.<Logger>getAnnotation(Logger.class);
      if (log != null) {
        assert !"".equals(log.name());
        return log.name();
      } 
      current = current.getSuperclass();
    } 
    assert false;
    return null;
  }
  
  public static final class BuiltinSwitchPoint extends SwitchPoint {}
  
  public SwitchPoint newBuiltinSwitchPoint(String name) {
    assert this.builtinSwitchPoints.get(name) == null;
    SwitchPoint sp = new BuiltinSwitchPoint();
    this.builtinSwitchPoints.put(name, sp);
    return sp;
  }
  
  public SwitchPoint getBuiltinSwitchPoint(String name) {
    return this.builtinSwitchPoints.get(name);
  }
  
  private static ClassLoader createModuleLoader(ClassLoader cl, String modulePath, String addModules) {
    Set<String> rootMods;
    if (addModules == null)
      throw new IllegalArgumentException("--module-path specified with no --add-modules"); 
    Path[] paths = (Path[])Stream.<String>of(modulePath.split(File.pathSeparator)).map(x$0 -> Paths.get(x$0, new String[0])).toArray(x$0 -> new Path[x$0]);
    ModuleFinder mf = ModuleFinder.of(paths);
    Set<ModuleReference> mrefs = mf.findAll();
    if (mrefs.isEmpty())
      throw new RuntimeException("No modules in script --module-path: " + modulePath); 
    if (addModules.equals("ALL-MODULE-PATH")) {
      rootMods = (Set<String>)mrefs.stream().map(mr -> mr.descriptor().name()).collect(Collectors.toSet());
    } else {
      rootMods = (Set<String>)Stream.<String>of(addModules.split(",")).map(String::trim).collect(Collectors.toSet());
    } 
    ModuleLayer boot = ModuleLayer.boot();
    Configuration conf = boot.configuration().resolve(mf, ModuleFinder.of(new Path[0]), rootMods);
    String firstMod = rootMods.iterator().next();
    return boot.defineModulesWithOneLoader(conf, cl).findLoader(firstMod);
  }
  
  public static interface MultiGlobalCompiledScript {
    ScriptFunction getFunction(Global param1Global);
  }
}
