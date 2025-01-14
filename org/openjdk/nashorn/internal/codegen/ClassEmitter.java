package org.openjdk.nashorn.internal.codegen;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.security.AccessController;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.util.Printer;
import org.objectweb.asm.util.TraceClassVisitor;
import org.openjdk.nashorn.internal.codegen.types.Type;
import org.openjdk.nashorn.internal.ir.FunctionNode;
import org.openjdk.nashorn.internal.ir.debug.NashornClassReader;
import org.openjdk.nashorn.internal.ir.debug.NashornTextifier;
import org.openjdk.nashorn.internal.runtime.Context;
import org.openjdk.nashorn.internal.runtime.PropertyMap;
import org.openjdk.nashorn.internal.runtime.RewriteException;
import org.openjdk.nashorn.internal.runtime.ScriptObject;
import org.openjdk.nashorn.internal.runtime.Source;
import org.openjdk.nashorn.internal.scripts.JS;

public class ClassEmitter {
  private static final EnumSet<Flag> DEFAULT_METHOD_FLAGS = EnumSet.of(Flag.PUBLIC);
  
  private boolean classStarted;
  
  private boolean classEnded;
  
  private final HashSet<MethodEmitter> methodsStarted;
  
  protected final ClassWriter cw;
  
  protected final Context context;
  
  private String unitClassName;
  
  private Set<Class<?>> constantMethodNeeded;
  
  private int methodCount;
  
  private int initCount;
  
  private int fieldCount;
  
  private final Set<String> methodNames;
  
  private ClassEmitter(Context context, ClassWriter cw) {
    this.context = context;
    this.cw = cw;
    this.methodsStarted = new HashSet<>();
    this.methodNames = new HashSet<>();
  }
  
  public Set<String> getMethodNames() {
    return Collections.unmodifiableSet(this.methodNames);
  }
  
  ClassEmitter(Context context, String className, String superClassName, String... interfaceNames) {
    this(context, new ClassWriter(3));
    this.cw.visit(51, 33, className, null, superClassName, interfaceNames);
  }
  
  ClassEmitter(Context context, String sourceName, String unitClassName, boolean strictMode) {
    this(context, new ClassWriter(3) {
          private static final String OBJECT_CLASS = "java/lang/Object";
          
          protected String getCommonSuperClass(String type1, String type2) {
            try {
              return super.getCommonSuperClass(type1, type2);
            } catch (RuntimeException e) {
              if (ClassEmitter.isScriptObject(type1) && ClassEmitter.isScriptObject(type2))
                return CompilerConstants.className(ScriptObject.class); 
              return "java/lang/Object";
            } 
          }
        });
    this.unitClassName = unitClassName;
    this.constantMethodNeeded = new HashSet<>();
    this.cw.visit(51, 33, unitClassName, null, pathName(JS.class.getName()), null);
    this.cw.visitSource(sourceName, null);
    defineCommonStatics(strictMode);
  }
  
  Context getContext() {
    return this.context;
  }
  
  String getUnitClassName() {
    return this.unitClassName;
  }
  
  public int getMethodCount() {
    return this.methodCount;
  }
  
  public int getInitCount() {
    return this.initCount;
  }
  
  public int getFieldCount() {
    return this.fieldCount;
  }
  
  private static String pathName(String name) {
    return name.replace('.', '/');
  }
  
  private void defineCommonStatics(boolean strictMode) {
    field(EnumSet.of(Flag.PRIVATE, Flag.STATIC), CompilerConstants.SOURCE.symbolName(), Source.class);
    field(EnumSet.of(Flag.PRIVATE, Flag.STATIC), CompilerConstants.CONSTANTS.symbolName(), Object[].class);
    field(EnumSet.of(Flag.PUBLIC, Flag.STATIC, Flag.FINAL), CompilerConstants.STRICT_MODE.symbolName(), boolean.class, Boolean.valueOf(strictMode));
  }
  
  private void defineCommonUtilities() {
    assert this.unitClassName != null;
    if (this.constantMethodNeeded.contains(String.class)) {
      MethodEmitter getStringMethod = method(EnumSet.of(Flag.PRIVATE, Flag.STATIC), CompilerConstants.GET_STRING.symbolName(), String.class, new Class[] { int.class });
      getStringMethod.begin();
      getStringMethod.getStatic(this.unitClassName, CompilerConstants.CONSTANTS.symbolName(), CompilerConstants.CONSTANTS.descriptor())
        .load((Type)Type.INT, 0)
        .arrayload()
        .checkcast(String.class)
        ._return();
      getStringMethod.end();
    } 
    if (this.constantMethodNeeded.contains(PropertyMap.class)) {
      MethodEmitter getMapMethod = method(EnumSet.of(Flag.PUBLIC, Flag.STATIC), CompilerConstants.GET_MAP.symbolName(), PropertyMap.class, new Class[] { int.class });
      getMapMethod.begin();
      getMapMethod.loadConstants()
        .load((Type)Type.INT, 0)
        .arrayload()
        .checkcast(PropertyMap.class)
        ._return();
      getMapMethod.end();
      MethodEmitter setMapMethod = method(EnumSet.of(Flag.PUBLIC, Flag.STATIC), CompilerConstants.SET_MAP.symbolName(), void.class, new Class[] { int.class, PropertyMap.class });
      setMapMethod.begin();
      setMapMethod.loadConstants()
        .load((Type)Type.INT, 0)
        .load(Type.OBJECT, 1)
        .arraystore();
      setMapMethod.returnVoid();
      setMapMethod.end();
    } 
    for (Class<?> clazz : this.constantMethodNeeded) {
      if (clazz.isArray())
        defineGetArrayMethod(clazz); 
    } 
  }
  
  private void defineGetArrayMethod(Class<?> clazz) {
    assert this.unitClassName != null;
    String methodName = getArrayMethodName(clazz);
    MethodEmitter getArrayMethod = method(EnumSet.of(Flag.PRIVATE, Flag.STATIC), methodName, clazz, new Class[] { int.class });
    getArrayMethod.begin();
    getArrayMethod.getStatic(this.unitClassName, CompilerConstants.CONSTANTS.symbolName(), CompilerConstants.CONSTANTS.descriptor())
      .load((Type)Type.INT, 0)
      .arrayload()
      .checkcast(clazz)
      .invoke(CompilerConstants.virtualCallNoLookup(clazz, "clone", Object.class, new Class[0]))
      .checkcast(clazz)
      ._return();
    getArrayMethod.end();
  }
  
  static String getArrayMethodName(Class<?> clazz) {
    assert clazz.isArray();
    return CompilerConstants.GET_ARRAY_PREFIX.symbolName() + CompilerConstants.GET_ARRAY_PREFIX.symbolName() + clazz.getComponentType().getSimpleName();
  }
  
  void needGetConstantMethod(Class<?> clazz) {
    this.constantMethodNeeded.add(clazz);
  }
  
  private static boolean isScriptObject(String type) {
    return (type
      .startsWith("org/openjdk/nashorn/internal/scripts") || type
      .equals(CompilerConstants.className(ScriptObject.class)) || type
      .startsWith("org/openjdk/nashorn/internal/objects"));
  }
  
  public void begin() {
    this.classStarted = true;
  }
  
  public void end() {
    assert this.classStarted : "class not started for " + this.unitClassName;
    if (this.unitClassName != null) {
      MethodEmitter initMethod = init(EnumSet.of(Flag.PRIVATE), new Class[0]);
      initMethod.begin();
      initMethod.load(Type.OBJECT, 0);
      initMethod.newInstance(JS.class);
      initMethod.returnVoid();
      initMethod.end();
      defineCommonUtilities();
    } 
    this.cw.visitEnd();
    this.classStarted = false;
    this.classEnded = true;
    assert this.methodsStarted.isEmpty() : "methodsStarted not empty " + this.methodsStarted;
  }
  
  static String disassemble(byte[] bytecode) {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintWriter pw = new PrintWriter(baos);
    try {
      NashornClassReader cr = new NashornClassReader(bytecode);
      Context ctx = AccessController.<Context>doPrivileged(Context::getContext);
      TraceClassVisitor tcv = new TraceClassVisitor(null, (Printer)new NashornTextifier(ctx.getEnv(), cr), pw);
      cr.accept((ClassVisitor)tcv, 0);
      pw.close();
    } catch (Throwable throwable) {
      try {
        pw.close();
      } catch (Throwable throwable1) {
        throwable.addSuppressed(throwable1);
      } 
      throw throwable;
    } 
    return new String(baos.toByteArray());
  }
  
  void beginMethod(MethodEmitter method) {
    assert !this.methodsStarted.contains(method);
    this.methodsStarted.add(method);
  }
  
  void endMethod(MethodEmitter method) {
    assert this.methodsStarted.contains(method);
    this.methodsStarted.remove(method);
  }
  
  MethodEmitter method(String methodName, Class<?> rtype, Class<?>... ptypes) {
    return method(DEFAULT_METHOD_FLAGS, methodName, rtype, ptypes);
  }
  
  MethodEmitter method(EnumSet<Flag> methodFlags, String methodName, Class<?> rtype, Class<?>... ptypes) {
    this.methodCount++;
    this.methodNames.add(methodName);
    return new MethodEmitter(this, methodVisitor(methodFlags, methodName, rtype, ptypes));
  }
  
  MethodEmitter method(String methodName, String descriptor) {
    return method(DEFAULT_METHOD_FLAGS, methodName, descriptor);
  }
  
  MethodEmitter method(EnumSet<Flag> methodFlags, String methodName, String descriptor) {
    this.methodCount++;
    this.methodNames.add(methodName);
    return new MethodEmitter(this, this.cw.visitMethod(Flag.getValue(methodFlags), methodName, descriptor, null, null));
  }
  
  MethodEmitter method(FunctionNode functionNode) {
    this.methodCount++;
    this.methodNames.add(functionNode.getName());
    FunctionSignature signature = new FunctionSignature(functionNode);
    MethodVisitor mv = this.cw.visitMethod(0x9 | (
        functionNode.isVarArg() ? 128 : 0), functionNode
        .getName(), signature
        .toString(), null, null);
    return new MethodEmitter(this, mv, functionNode);
  }
  
  MethodEmitter restOfMethod(FunctionNode functionNode) {
    this.methodCount++;
    this.methodNames.add(functionNode.getName());
    MethodVisitor mv = this.cw.visitMethod(9, functionNode
        
        .getName(), 
        Type.getMethodDescriptor(functionNode.getReturnType().getTypeClass(), new Class[] { RewriteException.class }), null, null);
    return new MethodEmitter(this, mv, functionNode);
  }
  
  MethodEmitter init() {
    this.initCount++;
    return method(CompilerConstants.INIT.symbolName(), void.class, new Class[0]);
  }
  
  MethodEmitter init(Class<?>... ptypes) {
    this.initCount++;
    return method(CompilerConstants.INIT.symbolName(), void.class, ptypes);
  }
  
  MethodEmitter init(EnumSet<Flag> flags, Class<?>... ptypes) {
    this.initCount++;
    return method(flags, CompilerConstants.INIT.symbolName(), void.class, ptypes);
  }
  
  final void field(EnumSet<Flag> fieldFlags, String fieldName, Class<?> fieldType, Object value) {
    this.fieldCount++;
    this.cw.visitField(Flag.getValue(fieldFlags), fieldName, CompilerConstants.typeDescriptor(fieldType), null, value).visitEnd();
  }
  
  final void field(EnumSet<Flag> fieldFlags, String fieldName, Class<?> fieldType) {
    field(fieldFlags, fieldName, fieldType, null);
  }
  
  final void field(String fieldName, Class<?> fieldType) {
    field(EnumSet.of(Flag.PUBLIC), fieldName, fieldType, null);
  }
  
  byte[] toByteArray() {
    if (this.classEnded)
      return this.cw.toByteArray(); 
    throw new AssertionError();
  }
  
  enum Flag {
    HANDLE_STATIC(6),
    HANDLE_NEWSPECIAL(8),
    HANDLE_SPECIAL(7),
    HANDLE_VIRTUAL(5),
    HANDLE_INTERFACE(9),
    FINAL(16),
    STATIC(8),
    PUBLIC(1),
    PRIVATE(2);
    
    private final int value;
    
    Flag(int value) {
      this.value = value;
    }
    
    int getValue() {
      return this.value;
    }
    
    static int getValue(EnumSet<Flag> flags) {
      int v = 0;
      for (Flag flag : flags)
        v |= flag.getValue(); 
      return v;
    }
  }
  
  private MethodVisitor methodVisitor(EnumSet<Flag> flags, String methodName, Class<?> rtype, Class<?>... ptypes) {
    return this.cw.visitMethod(Flag.getValue(flags), methodName, CompilerConstants.methodDescriptor(rtype, ptypes), null, null);
  }
}
