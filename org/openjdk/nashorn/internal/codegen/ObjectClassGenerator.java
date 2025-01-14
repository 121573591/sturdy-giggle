package org.openjdk.nashorn.internal.codegen;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.openjdk.nashorn.internal.codegen.types.Type;
import org.openjdk.nashorn.internal.lookup.Lookup;
import org.openjdk.nashorn.internal.runtime.AllocationStrategy;
import org.openjdk.nashorn.internal.runtime.Context;
import org.openjdk.nashorn.internal.runtime.FunctionScope;
import org.openjdk.nashorn.internal.runtime.JSType;
import org.openjdk.nashorn.internal.runtime.PropertyMap;
import org.openjdk.nashorn.internal.runtime.ScriptEnvironment;
import org.openjdk.nashorn.internal.runtime.ScriptObject;
import org.openjdk.nashorn.internal.runtime.Undefined;
import org.openjdk.nashorn.internal.runtime.UnwarrantedOptimismException;
import org.openjdk.nashorn.internal.runtime.logging.DebugLogger;
import org.openjdk.nashorn.internal.runtime.logging.Loggable;
import org.openjdk.nashorn.internal.runtime.logging.Logger;

@Logger(name = "fields")
public final class ObjectClassGenerator implements Loggable {
  private static final MethodHandle IS_TYPE_GUARD = findOwnMH("isType", boolean.class, new Class[] { Class.class, Object.class });
  
  private static final String SCOPE_MARKER = "P";
  
  static final int FIELD_PADDING = 4;
  
  private final DebugLogger log;
  
  private static final Type[] FIELD_TYPES_OBJECT = new Type[] { Type.OBJECT };
  
  private static final Type[] FIELD_TYPES_DUAL = new Type[] { Type.LONG, Type.OBJECT };
  
  public static final Type PRIMITIVE_FIELD_TYPE = Type.LONG;
  
  private static final MethodHandle GET_DIFFERENT = findOwnMH("getDifferent", Object.class, new Class[] { Object.class, Class.class, MethodHandle.class, MethodHandle.class, int.class });
  
  private static final MethodHandle GET_DIFFERENT_UNDEFINED = findOwnMH("getDifferentUndefined", Object.class, new Class[] { int.class });
  
  private static boolean initialized = false;
  
  private final Context context;
  
  private final boolean dualFields;
  
  public ObjectClassGenerator(Context context, boolean dualFields) {
    this.context = context;
    this.dualFields = dualFields;
    assert context != null;
    this.log = initLogger(context);
    if (!initialized) {
      initialized = true;
      if (!dualFields)
        this.log.warning("Running with object fields only - this is a deprecated configuration."); 
    } 
  }
  
  public DebugLogger getLogger() {
    return this.log;
  }
  
  public DebugLogger initLogger(Context ctxt) {
    return ctxt.getLogger(getClass());
  }
  
  public static long pack(Number n) {
    if (n instanceof Integer)
      return n.intValue(); 
    if (n instanceof Long)
      return n.longValue(); 
    if (n instanceof Double)
      return Double.doubleToRawLongBits(n.doubleValue()); 
    throw new AssertionError("cannot pack" + n);
  }
  
  private static String getPrefixName(boolean dualFields) {
    return dualFields ? CompilerConstants.JS_OBJECT_DUAL_FIELD_PREFIX.symbolName() : CompilerConstants.JS_OBJECT_SINGLE_FIELD_PREFIX.symbolName();
  }
  
  private static String getPrefixName(String className) {
    if (className.startsWith(CompilerConstants.JS_OBJECT_DUAL_FIELD_PREFIX.symbolName()))
      return getPrefixName(true); 
    if (className.startsWith(CompilerConstants.JS_OBJECT_SINGLE_FIELD_PREFIX.symbolName()))
      return getPrefixName(false); 
    throw new AssertionError("Not a structure class: " + className);
  }
  
  public static String getClassName(int fieldCount, boolean dualFields) {
    String prefix = getPrefixName(dualFields);
    return (fieldCount != 0) ? ("org/openjdk/nashorn/internal/scripts/" + prefix + fieldCount) : ("org/openjdk/nashorn/internal/scripts/" + 
      prefix);
  }
  
  public static String getClassName(int fieldCount, int paramCount, boolean dualFields) {
    return "org/openjdk/nashorn/internal/scripts/" + getPrefixName(dualFields) + fieldCount + "P" + paramCount;
  }
  
  public static int getFieldCount(Class<?> clazz) {
    String name = clazz.getSimpleName();
    String prefix = getPrefixName(name);
    if (prefix.equals(name))
      return 0; 
    int scopeMarker = name.indexOf("P");
    return Integer.parseInt((scopeMarker == -1) ? name.substring(prefix.length()) : name.substring(prefix.length(), scopeMarker));
  }
  
  public static String getFieldName(int fieldIndex, Type type) {
    return type.getDescriptor().substring(0, 1) + type.getDescriptor().substring(0, 1);
  }
  
  private void initializeToUndefined(MethodEmitter init, String className, List<String> fieldNames) {
    if (this.dualFields)
      return; 
    if (fieldNames.isEmpty())
      return; 
    init.load(Type.OBJECT, CompilerConstants.JAVA_THIS.slot());
    init.loadUndefined(Type.OBJECT);
    Iterator<String> iter = fieldNames.iterator();
    while (iter.hasNext()) {
      String fieldName = iter.next();
      if (iter.hasNext())
        init.dup2(); 
      init.putField(className, fieldName, Type.OBJECT.getDescriptor());
    } 
  }
  
  public byte[] generate(String descriptor) {
    String[] counts = descriptor.split("P");
    int fieldCount = Integer.valueOf(counts[0]).intValue();
    if (counts.length == 1)
      return generate(fieldCount); 
    int paramCount = Integer.valueOf(counts[1]).intValue();
    return generate(fieldCount, paramCount);
  }
  
  public byte[] generate(int fieldCount) {
    String className = getClassName(fieldCount, this.dualFields);
    String superName = CompilerConstants.className(ScriptObject.class);
    ClassEmitter classEmitter = newClassEmitter(className, superName);
    addFields(classEmitter, fieldCount);
    MethodEmitter init = newInitMethod(classEmitter);
    init.returnVoid();
    init.end();
    MethodEmitter initWithSpillArrays = newInitWithSpillArraysMethod(classEmitter, ScriptObject.class);
    initWithSpillArrays.returnVoid();
    initWithSpillArrays.end();
    newEmptyInit(className, classEmitter);
    newAllocate(className, classEmitter);
    return toByteArray(className, classEmitter);
  }
  
  public byte[] generate(int fieldCount, int paramCount) {
    String className = getClassName(fieldCount, paramCount, this.dualFields);
    String superName = CompilerConstants.className(FunctionScope.class);
    ClassEmitter classEmitter = newClassEmitter(className, superName);
    List<String> initFields = addFields(classEmitter, fieldCount);
    MethodEmitter init = newInitScopeMethod(classEmitter);
    initializeToUndefined(init, className, initFields);
    init.returnVoid();
    init.end();
    MethodEmitter initWithSpillArrays = newInitWithSpillArraysMethod(classEmitter, FunctionScope.class);
    initializeToUndefined(initWithSpillArrays, className, initFields);
    initWithSpillArrays.returnVoid();
    initWithSpillArrays.end();
    MethodEmitter initWithArguments = newInitScopeWithArgumentsMethod(classEmitter);
    initializeToUndefined(initWithArguments, className, initFields);
    initWithArguments.returnVoid();
    initWithArguments.end();
    return toByteArray(className, classEmitter);
  }
  
  private List<String> addFields(ClassEmitter classEmitter, int fieldCount) {
    List<String> initFields = new LinkedList<>();
    Type[] fieldTypes = this.dualFields ? FIELD_TYPES_DUAL : FIELD_TYPES_OBJECT;
    for (int i = 0; i < fieldCount; i++) {
      for (Type type : fieldTypes) {
        String fieldName = getFieldName(i, type);
        classEmitter.field(fieldName, type.getTypeClass());
        if (type == Type.OBJECT)
          initFields.add(fieldName); 
      } 
    } 
    return initFields;
  }
  
  private ClassEmitter newClassEmitter(String className, String superName) {
    ClassEmitter classEmitter = new ClassEmitter(this.context, className, superName, new String[0]);
    classEmitter.begin();
    return classEmitter;
  }
  
  private static MethodEmitter newInitMethod(ClassEmitter classEmitter) {
    MethodEmitter init = classEmitter.init(new Class[] { PropertyMap.class });
    init.begin();
    init.load(Type.OBJECT, CompilerConstants.JAVA_THIS.slot());
    init.load(Type.OBJECT, CompilerConstants.INIT_MAP.slot());
    init.invoke(CompilerConstants.constructorNoLookup(ScriptObject.class, new Class[] { PropertyMap.class }));
    return init;
  }
  
  private static MethodEmitter newInitWithSpillArraysMethod(ClassEmitter classEmitter, Class<?> superClass) {
    MethodEmitter init = classEmitter.init(new Class[] { PropertyMap.class, long[].class, Object[].class });
    init.begin();
    init.load(Type.OBJECT, CompilerConstants.JAVA_THIS.slot());
    init.load(Type.OBJECT, CompilerConstants.INIT_MAP.slot());
    init.load((Type)Type.LONG_ARRAY, 2);
    init.load((Type)Type.OBJECT_ARRAY, 3);
    init.invoke(CompilerConstants.constructorNoLookup(superClass, new Class[] { PropertyMap.class, long[].class, Object[].class }));
    return init;
  }
  
  private static MethodEmitter newInitScopeMethod(ClassEmitter classEmitter) {
    MethodEmitter init = classEmitter.init(new Class[] { PropertyMap.class, ScriptObject.class });
    init.begin();
    init.load(Type.OBJECT, CompilerConstants.JAVA_THIS.slot());
    init.load(Type.OBJECT, CompilerConstants.INIT_MAP.slot());
    init.load(Type.OBJECT, CompilerConstants.INIT_SCOPE.slot());
    init.invoke(CompilerConstants.constructorNoLookup(FunctionScope.class, new Class[] { PropertyMap.class, ScriptObject.class }));
    return init;
  }
  
  private static MethodEmitter newInitScopeWithArgumentsMethod(ClassEmitter classEmitter) {
    MethodEmitter init = classEmitter.init(new Class[] { PropertyMap.class, ScriptObject.class, ScriptObject.class });
    init.begin();
    init.load(Type.OBJECT, CompilerConstants.JAVA_THIS.slot());
    init.load(Type.OBJECT, CompilerConstants.INIT_MAP.slot());
    init.load(Type.OBJECT, CompilerConstants.INIT_SCOPE.slot());
    init.load(Type.OBJECT, CompilerConstants.INIT_ARGUMENTS.slot());
    init.invoke(CompilerConstants.constructorNoLookup(FunctionScope.class, new Class[] { PropertyMap.class, ScriptObject.class, ScriptObject.class }));
    return init;
  }
  
  private static void newEmptyInit(String className, ClassEmitter classEmitter) {
    MethodEmitter emptyInit = classEmitter.init();
    emptyInit.begin();
    emptyInit.load(Type.OBJECT, CompilerConstants.JAVA_THIS.slot());
    emptyInit.loadNull();
    emptyInit.invoke(CompilerConstants.constructorNoLookup(className, new Class[] { PropertyMap.class }));
    emptyInit.returnVoid();
    emptyInit.end();
  }
  
  private static void newAllocate(String className, ClassEmitter classEmitter) {
    MethodEmitter allocate = classEmitter.method(EnumSet.of(ClassEmitter.Flag.PUBLIC, ClassEmitter.Flag.STATIC), CompilerConstants.ALLOCATE.symbolName(), ScriptObject.class, new Class[] { PropertyMap.class });
    allocate.begin();
    allocate._new(className, Type.typeFor(ScriptObject.class));
    allocate.dup();
    allocate.load(Type.typeFor(PropertyMap.class), 0);
    allocate.invoke(CompilerConstants.constructorNoLookup(className, new Class[] { PropertyMap.class }));
    allocate._return();
    allocate.end();
  }
  
  private byte[] toByteArray(String className, ClassEmitter classEmitter) {
    classEmitter.end();
    byte[] code = classEmitter.toByteArray();
    ScriptEnvironment env = this.context.getEnv();
    DumpBytecode.dumpBytecode(env, this.log, code, className);
    if (env._verify_code)
      this.context.verify(code); 
    return code;
  }
  
  public static final MethodHandle PACK_DOUBLE = Lookup.MH
    .explicitCastArguments(Lookup.MH.findStatic(MethodHandles.publicLookup(), Double.class, "doubleToRawLongBits", Lookup.MH.type(long.class, new Class[] { double.class })), Lookup.MH.type(long.class, new Class[] { double.class }));
  
  public static final MethodHandle UNPACK_DOUBLE = Lookup.MH
    .findStatic(MethodHandles.publicLookup(), Double.class, "longBitsToDouble", Lookup.MH.type(double.class, new Class[] { long.class }));
  
  private static Object getDifferent(Object receiver, Class<?> forType, MethodHandle primitiveGetter, MethodHandle objectGetter, int programPoint) {
    MethodHandle sameTypeGetter = getterForType(forType, primitiveGetter, objectGetter);
    MethodHandle mh = Lookup.MH.asType(sameTypeGetter, sameTypeGetter.type().changeReturnType(Object.class));
    try {
      Object value = mh.invokeExact(receiver);
      throw new UnwarrantedOptimismException(value, programPoint);
    } catch (Error|RuntimeException e) {
      throw e;
    } catch (Throwable e) {
      throw new RuntimeException(e);
    } 
  }
  
  private static Object getDifferentUndefined(int programPoint) {
    throw new UnwarrantedOptimismException(Undefined.getUndefined(), programPoint);
  }
  
  private static MethodHandle getterForType(Class<?> forType, MethodHandle primitiveGetter, MethodHandle objectGetter) {
    switch (JSType.getAccessorTypeIndex(forType)) {
      case 0:
        return Lookup.MH.explicitCastArguments(primitiveGetter, primitiveGetter.type().changeReturnType(int.class));
      case 1:
        return Lookup.MH.filterReturnValue(primitiveGetter, UNPACK_DOUBLE);
      case 2:
        return objectGetter;
    } 
    throw new AssertionError(forType);
  }
  
  private static MethodHandle createGetterInner(Class<?> forType, Class<?> type, MethodHandle primitiveGetter, MethodHandle objectGetter, List<MethodHandle> converters, int programPoint) {
    int fti = (forType == null) ? -1 : JSType.getAccessorTypeIndex(forType);
    int ti = JSType.getAccessorTypeIndex(type);
    boolean isOptimistic = (converters == JSType.CONVERT_OBJECT_OPTIMISTIC);
    boolean isPrimitiveStorage = (forType != null && forType.isPrimitive());
    MethodHandle getter = (primitiveGetter == null) ? objectGetter : (isPrimitiveStorage ? primitiveGetter : objectGetter);
    if (forType == null) {
      if (isOptimistic) {
        if (ti == 2)
          return Lookup.MH.dropArguments(JSType.GET_UNDEFINED.get(2), 0, new Class[] { Object.class }); 
        return Lookup.MH.asType(Lookup.MH
            .dropArguments(Lookup.MH
              .insertArguments(GET_DIFFERENT_UNDEFINED, 0, new Object[] { Integer.valueOf(programPoint) }), 0, new Class[] { Object.class }), getter.type().changeReturnType(type));
      } 
      return Lookup.MH.dropArguments(JSType.GET_UNDEFINED.get(ti), 0, new Class[] { Object.class });
    } 
    assert primitiveGetter != null || forType == Object.class : forType;
    if (isOptimistic) {
      if (fti < ti) {
        assert fti != -1;
        MethodHandle methodHandle = getterForType(forType, primitiveGetter, objectGetter);
        return Lookup.MH.asType(methodHandle, methodHandle.type().changeReturnType(type));
      } 
      if (fti == ti)
        return getterForType(forType, primitiveGetter, objectGetter); 
      assert fti > ti;
      if (fti == 2)
        return Lookup.MH.filterReturnValue(objectGetter, Lookup.MH
            
            .insertArguments(converters
              .get(ti), 1, new Object[] { Integer.valueOf(programPoint) })); 
      return Lookup.MH.asType(Lookup.MH
          .filterArguments(objectGetter, 0, new MethodHandle[] { Lookup.MH.insertArguments(GET_DIFFERENT, 1, new Object[] { forType, primitiveGetter, objectGetter, Integer.valueOf(programPoint) }) }), objectGetter.type().changeReturnType(type));
    } 
    assert !isOptimistic;
    MethodHandle tgetter = getterForType(forType, primitiveGetter, objectGetter);
    if (fti == 2) {
      if (fti != ti)
        return Lookup.MH.filterReturnValue(tgetter, JSType.CONVERT_OBJECT.get(ti)); 
      return tgetter;
    } 
    assert primitiveGetter != null;
    MethodType tgetterType = tgetter.type();
    switch (fti) {
      case 0:
        return Lookup.MH.asType(tgetter, tgetterType.changeReturnType(type));
      case 1:
        switch (ti) {
          case 0:
            return Lookup.MH.filterReturnValue(tgetter, JSType.TO_INT32_D.methodHandle);
          case 1:
            assert tgetterType.returnType() == double.class;
            return tgetter;
        } 
        return Lookup.MH.asType(tgetter, tgetterType.changeReturnType(Object.class));
    } 
    throw new UnsupportedOperationException("" + forType + "=>" + forType);
  }
  
  public static MethodHandle createGetter(Class<?> forType, Class<?> type, MethodHandle primitiveGetter, MethodHandle objectGetter, int programPoint) {
    return createGetterInner(forType, type, primitiveGetter, objectGetter, 
        
        UnwarrantedOptimismException.isValid(programPoint) ? JSType.CONVERT_OBJECT_OPTIMISTIC : JSType.CONVERT_OBJECT, programPoint);
  }
  
  public static MethodHandle createSetter(Class<?> forType, Class<?> type, MethodHandle primitiveSetter, MethodHandle objectSetter) {
    assert forType != null;
    int fti = JSType.getAccessorTypeIndex(forType);
    int ti = JSType.getAccessorTypeIndex(type);
    if (fti == 2 || primitiveSetter == null) {
      if (ti == 2)
        return objectSetter; 
      return Lookup.MH.asType(objectSetter, objectSetter.type().changeParameterType(1, type));
    } 
    MethodType pmt = primitiveSetter.type();
    switch (fti) {
      case 0:
        switch (ti) {
          case 0:
            return Lookup.MH.asType(primitiveSetter, pmt.changeParameterType(1, int.class));
          case 1:
            return Lookup.MH.filterArguments(primitiveSetter, 1, new MethodHandle[] { PACK_DOUBLE });
        } 
        return objectSetter;
      case 1:
        if (ti == 2)
          return objectSetter; 
        return Lookup.MH.asType(Lookup.MH.filterArguments(primitiveSetter, 1, new MethodHandle[] { PACK_DOUBLE }), pmt.changeParameterType(1, type));
    } 
    throw new UnsupportedOperationException("" + forType + "=>" + forType);
  }
  
  private static boolean isType(Class<?> boxedForType, Object x) {
    return (x != null && x.getClass() == boxedForType);
  }
  
  private static Class<? extends Number> getBoxedType(Class<?> forType) {
    if (forType == int.class)
      return (Class)Integer.class; 
    if (forType == long.class)
      return (Class)Long.class; 
    if (forType == double.class)
      return (Class)Double.class; 
    assert false;
    return null;
  }
  
  public static MethodHandle createGuardBoxedPrimitiveSetter(Class<?> forType, MethodHandle primitiveSetter, MethodHandle objectSetter) {
    Class<? extends Number> boxedForType = getBoxedType(forType);
    return Lookup.MH.guardWithTest(Lookup.MH
        .insertArguments(Lookup.MH
          .dropArguments(IS_TYPE_GUARD, 1, new Class[] { Object.class }), 0, new Object[] { boxedForType }), Lookup.MH.asType(primitiveSetter, objectSetter
          
          .type()), objectSetter);
  }
  
  static int getPaddedFieldCount(int count) {
    return count / 4 * 4 + 4;
  }
  
  private static MethodHandle findOwnMH(String name, Class<?> rtype, Class<?>... types) {
    return Lookup.MH.findStatic(MethodHandles.lookup(), ObjectClassGenerator.class, name, Lookup.MH.type(rtype, types));
  }
  
  static AllocationStrategy createAllocationStrategy(int thisProperties, boolean dualFields) {
    int paddedFieldCount = getPaddedFieldCount(thisProperties);
    return new AllocationStrategy(paddedFieldCount, dualFields);
  }
}
