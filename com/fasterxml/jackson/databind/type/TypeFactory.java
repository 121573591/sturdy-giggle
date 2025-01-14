package com.fasterxml.jackson.databind.type;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.util.ArrayBuilders;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.LRUMap;
import com.fasterxml.jackson.databind.util.LookupCache;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.BaseStream;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class TypeFactory implements Serializable {
  private static final long serialVersionUID = 1L;
  
  public static final int DEFAULT_MAX_CACHE_SIZE = 200;
  
  private static final JavaType[] NO_TYPES = new JavaType[0];
  
  protected static final TypeFactory instance = new TypeFactory();
  
  protected static final TypeBindings EMPTY_BINDINGS = TypeBindings.emptyBindings();
  
  private static final Class<?> CLS_STRING = String.class;
  
  private static final Class<?> CLS_OBJECT = Object.class;
  
  private static final Class<?> CLS_COMPARABLE = Comparable.class;
  
  private static final Class<?> CLS_ENUM = Enum.class;
  
  private static final Class<?> CLS_JSON_NODE = JsonNode.class;
  
  private static final Class<?> CLS_BOOL = boolean.class;
  
  private static final Class<?> CLS_DOUBLE = double.class;
  
  private static final Class<?> CLS_INT = int.class;
  
  private static final Class<?> CLS_LONG = long.class;
  
  protected static final SimpleType CORE_TYPE_BOOL = new SimpleType(CLS_BOOL);
  
  protected static final SimpleType CORE_TYPE_DOUBLE = new SimpleType(CLS_DOUBLE);
  
  protected static final SimpleType CORE_TYPE_INT = new SimpleType(CLS_INT);
  
  protected static final SimpleType CORE_TYPE_LONG = new SimpleType(CLS_LONG);
  
  protected static final SimpleType CORE_TYPE_STRING = new SimpleType(CLS_STRING);
  
  protected static final SimpleType CORE_TYPE_OBJECT = new SimpleType(CLS_OBJECT);
  
  protected static final SimpleType CORE_TYPE_COMPARABLE = new SimpleType(CLS_COMPARABLE);
  
  protected static final SimpleType CORE_TYPE_ENUM = new SimpleType(CLS_ENUM);
  
  protected static final SimpleType CORE_TYPE_JSON_NODE = new SimpleType(CLS_JSON_NODE);
  
  protected final LookupCache<Object, JavaType> _typeCache;
  
  protected final TypeModifier[] _modifiers;
  
  protected final TypeParser _parser;
  
  protected final ClassLoader _classLoader;
  
  private TypeFactory() {
    this((LookupCache<Object, JavaType>)new LRUMap(16, 200));
  }
  
  protected TypeFactory(LookupCache<Object, JavaType> typeCache) {
    this._typeCache = Objects.<LookupCache<Object, JavaType>>requireNonNull(typeCache);
    this._parser = new TypeParser(this);
    this._modifiers = null;
    this._classLoader = null;
  }
  
  protected TypeFactory(LookupCache<Object, JavaType> typeCache, TypeParser p, TypeModifier[] mods, ClassLoader classLoader) {
    LRUMap lRUMap;
    if (typeCache == null)
      lRUMap = new LRUMap(16, 200); 
    this._typeCache = (LookupCache<Object, JavaType>)lRUMap;
    this._parser = p.withFactory(this);
    this._modifiers = mods;
    this._classLoader = classLoader;
  }
  
  public TypeFactory withModifier(TypeModifier mod) {
    TypeModifier[] mods;
    LookupCache<Object, JavaType> typeCache = this._typeCache;
    if (mod == null) {
      mods = null;
      typeCache = null;
    } else if (this._modifiers == null) {
      mods = new TypeModifier[] { mod };
      typeCache = null;
    } else {
      mods = (TypeModifier[])ArrayBuilders.insertInListNoDup((Object[])this._modifiers, mod);
    } 
    return new TypeFactory(typeCache, this._parser, mods, this._classLoader);
  }
  
  public TypeFactory withClassLoader(ClassLoader classLoader) {
    return new TypeFactory(this._typeCache, this._parser, this._modifiers, classLoader);
  }
  
  @Deprecated
  public TypeFactory withCache(LRUMap<Object, JavaType> cache) {
    return new TypeFactory((LookupCache<Object, JavaType>)cache, this._parser, this._modifiers, this._classLoader);
  }
  
  public TypeFactory withCache(LookupCache<Object, JavaType> cache) {
    return new TypeFactory(cache, this._parser, this._modifiers, this._classLoader);
  }
  
  public static TypeFactory defaultInstance() {
    return instance;
  }
  
  public void clearCache() {
    this._typeCache.clear();
  }
  
  public ClassLoader getClassLoader() {
    return this._classLoader;
  }
  
  public static JavaType unknownType() {
    return defaultInstance()._unknownType();
  }
  
  public static Class<?> rawClass(Type t) {
    if (t instanceof Class)
      return (Class)t; 
    if (t instanceof JavaType)
      return ((JavaType)t).getRawClass(); 
    if (t instanceof GenericArrayType)
      return Array.newInstance(rawClass(((GenericArrayType)t).getGenericComponentType()), 0).getClass(); 
    if (t instanceof ParameterizedType)
      return rawClass(((ParameterizedType)t).getRawType()); 
    if (t instanceof TypeVariable)
      return rawClass(((TypeVariable)t).getBounds()[0]); 
    if (t instanceof WildcardType)
      return rawClass(((WildcardType)t).getUpperBounds()[0]); 
    return defaultInstance().constructType(t).getRawClass();
  }
  
  public Class<?> findClass(String className) throws ClassNotFoundException {
    if (className.indexOf('.') < 0) {
      Class<?> prim = _findPrimitive(className);
      if (prim != null)
        return prim; 
    } 
    Throwable prob = null;
    ClassLoader loader = getClassLoader();
    if (loader == null)
      loader = Thread.currentThread().getContextClassLoader(); 
    if (loader != null)
      try {
        return classForName(className, true, loader);
      } catch (Exception e) {
        prob = ClassUtil.getRootCause(e);
      }  
    try {
      return classForName(className);
    } catch (Exception e) {
      if (prob == null)
        prob = ClassUtil.getRootCause(e); 
      ClassUtil.throwIfRTE(prob);
      throw new ClassNotFoundException(prob.getMessage(), prob);
    } 
  }
  
  protected Class<?> classForName(String name, boolean initialize, ClassLoader loader) throws ClassNotFoundException {
    return Class.forName(name, true, loader);
  }
  
  protected Class<?> classForName(String name) throws ClassNotFoundException {
    return Class.forName(name);
  }
  
  protected Class<?> _findPrimitive(String className) {
    if ("int".equals(className))
      return int.class; 
    if ("long".equals(className))
      return long.class; 
    if ("float".equals(className))
      return float.class; 
    if ("double".equals(className))
      return double.class; 
    if ("boolean".equals(className))
      return boolean.class; 
    if ("byte".equals(className))
      return byte.class; 
    if ("char".equals(className))
      return char.class; 
    if ("short".equals(className))
      return short.class; 
    if ("void".equals(className))
      return void.class; 
    return null;
  }
  
  public JavaType constructSpecializedType(JavaType baseType, Class<?> subclass) throws IllegalArgumentException {
    return constructSpecializedType(baseType, subclass, false);
  }
  
  public JavaType constructSpecializedType(JavaType baseType, Class<?> subclass, boolean relaxedCompatibilityCheck) throws IllegalArgumentException {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual getRawClass : ()Ljava/lang/Class;
    //   4: astore #4
    //   6: aload #4
    //   8: aload_2
    //   9: if_acmpne -> 14
    //   12: aload_1
    //   13: areturn
    //   14: aload #4
    //   16: ldc java/lang/Object
    //   18: if_acmpne -> 35
    //   21: aload_0
    //   22: aconst_null
    //   23: aload_2
    //   24: getstatic com/fasterxml/jackson/databind/type/TypeFactory.EMPTY_BINDINGS : Lcom/fasterxml/jackson/databind/type/TypeBindings;
    //   27: invokevirtual _fromClass : (Lcom/fasterxml/jackson/databind/type/ClassStack;Ljava/lang/Class;Lcom/fasterxml/jackson/databind/type/TypeBindings;)Lcom/fasterxml/jackson/databind/JavaType;
    //   30: astore #5
    //   32: goto -> 276
    //   35: aload #4
    //   37: aload_2
    //   38: invokevirtual isAssignableFrom : (Ljava/lang/Class;)Z
    //   41: ifne -> 76
    //   44: new java/lang/IllegalArgumentException
    //   47: dup
    //   48: ldc_w 'Class %s not subtype of %s'
    //   51: iconst_2
    //   52: anewarray java/lang/Object
    //   55: dup
    //   56: iconst_0
    //   57: aload_2
    //   58: invokestatic nameOf : (Ljava/lang/Class;)Ljava/lang/String;
    //   61: aastore
    //   62: dup
    //   63: iconst_1
    //   64: aload_1
    //   65: invokestatic getTypeDescription : (Lcom/fasterxml/jackson/databind/JavaType;)Ljava/lang/String;
    //   68: aastore
    //   69: invokestatic format : (Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   72: invokespecial <init> : (Ljava/lang/String;)V
    //   75: athrow
    //   76: aload_1
    //   77: invokevirtual isContainerType : ()Z
    //   80: ifeq -> 205
    //   83: aload_1
    //   84: invokevirtual isMapLikeType : ()Z
    //   87: ifeq -> 141
    //   90: aload_2
    //   91: ldc_w java/util/HashMap
    //   94: if_acmpeq -> 118
    //   97: aload_2
    //   98: ldc_w java/util/LinkedHashMap
    //   101: if_acmpeq -> 118
    //   104: aload_2
    //   105: ldc_w java/util/EnumMap
    //   108: if_acmpeq -> 118
    //   111: aload_2
    //   112: ldc_w java/util/TreeMap
    //   115: if_acmpne -> 205
    //   118: aload_0
    //   119: aconst_null
    //   120: aload_2
    //   121: aload_2
    //   122: aload_1
    //   123: invokevirtual getKeyType : ()Lcom/fasterxml/jackson/databind/JavaType;
    //   126: aload_1
    //   127: invokevirtual getContentType : ()Lcom/fasterxml/jackson/databind/JavaType;
    //   130: invokestatic create : (Ljava/lang/Class;Lcom/fasterxml/jackson/databind/JavaType;Lcom/fasterxml/jackson/databind/JavaType;)Lcom/fasterxml/jackson/databind/type/TypeBindings;
    //   133: invokevirtual _fromClass : (Lcom/fasterxml/jackson/databind/type/ClassStack;Ljava/lang/Class;Lcom/fasterxml/jackson/databind/type/TypeBindings;)Lcom/fasterxml/jackson/databind/JavaType;
    //   136: astore #5
    //   138: goto -> 276
    //   141: aload_1
    //   142: invokevirtual isCollectionLikeType : ()Z
    //   145: ifeq -> 205
    //   148: aload_2
    //   149: ldc_w java/util/ArrayList
    //   152: if_acmpeq -> 176
    //   155: aload_2
    //   156: ldc_w java/util/LinkedList
    //   159: if_acmpeq -> 176
    //   162: aload_2
    //   163: ldc_w java/util/HashSet
    //   166: if_acmpeq -> 176
    //   169: aload_2
    //   170: ldc_w java/util/TreeSet
    //   173: if_acmpne -> 195
    //   176: aload_0
    //   177: aconst_null
    //   178: aload_2
    //   179: aload_2
    //   180: aload_1
    //   181: invokevirtual getContentType : ()Lcom/fasterxml/jackson/databind/JavaType;
    //   184: invokestatic create : (Ljava/lang/Class;Lcom/fasterxml/jackson/databind/JavaType;)Lcom/fasterxml/jackson/databind/type/TypeBindings;
    //   187: invokevirtual _fromClass : (Lcom/fasterxml/jackson/databind/type/ClassStack;Ljava/lang/Class;Lcom/fasterxml/jackson/databind/type/TypeBindings;)Lcom/fasterxml/jackson/databind/JavaType;
    //   190: astore #5
    //   192: goto -> 276
    //   195: aload #4
    //   197: ldc_w java/util/EnumSet
    //   200: if_acmpne -> 205
    //   203: aload_1
    //   204: areturn
    //   205: aload_1
    //   206: invokevirtual getBindings : ()Lcom/fasterxml/jackson/databind/type/TypeBindings;
    //   209: invokevirtual isEmpty : ()Z
    //   212: ifeq -> 229
    //   215: aload_0
    //   216: aconst_null
    //   217: aload_2
    //   218: getstatic com/fasterxml/jackson/databind/type/TypeFactory.EMPTY_BINDINGS : Lcom/fasterxml/jackson/databind/type/TypeBindings;
    //   221: invokevirtual _fromClass : (Lcom/fasterxml/jackson/databind/type/ClassStack;Ljava/lang/Class;Lcom/fasterxml/jackson/databind/type/TypeBindings;)Lcom/fasterxml/jackson/databind/JavaType;
    //   224: astore #5
    //   226: goto -> 276
    //   229: aload_2
    //   230: invokevirtual getTypeParameters : ()[Ljava/lang/reflect/TypeVariable;
    //   233: arraylength
    //   234: istore #6
    //   236: iload #6
    //   238: ifne -> 255
    //   241: aload_0
    //   242: aconst_null
    //   243: aload_2
    //   244: getstatic com/fasterxml/jackson/databind/type/TypeFactory.EMPTY_BINDINGS : Lcom/fasterxml/jackson/databind/type/TypeBindings;
    //   247: invokevirtual _fromClass : (Lcom/fasterxml/jackson/databind/type/ClassStack;Ljava/lang/Class;Lcom/fasterxml/jackson/databind/type/TypeBindings;)Lcom/fasterxml/jackson/databind/JavaType;
    //   250: astore #5
    //   252: goto -> 276
    //   255: aload_0
    //   256: aload_1
    //   257: iload #6
    //   259: aload_2
    //   260: iload_3
    //   261: invokespecial _bindingsForSubtype : (Lcom/fasterxml/jackson/databind/JavaType;ILjava/lang/Class;Z)Lcom/fasterxml/jackson/databind/type/TypeBindings;
    //   264: astore #7
    //   266: aload_0
    //   267: aconst_null
    //   268: aload_2
    //   269: aload #7
    //   271: invokevirtual _fromClass : (Lcom/fasterxml/jackson/databind/type/ClassStack;Ljava/lang/Class;Lcom/fasterxml/jackson/databind/type/TypeBindings;)Lcom/fasterxml/jackson/databind/JavaType;
    //   274: astore #5
    //   276: aload #5
    //   278: aload_1
    //   279: invokevirtual withHandlersFrom : (Lcom/fasterxml/jackson/databind/JavaType;)Lcom/fasterxml/jackson/databind/JavaType;
    //   282: astore #5
    //   284: aload #5
    //   286: areturn
    // Line number table:
    //   Java source line number -> byte code offset
    //   #460	-> 0
    //   #461	-> 6
    //   #462	-> 12
    //   #468	-> 14
    //   #469	-> 21
    //   #470	-> 32
    //   #472	-> 35
    //   #473	-> 44
    //   #474	-> 58
    //   #473	-> 69
    //   #480	-> 76
    //   #481	-> 83
    //   #482	-> 90
    //   #486	-> 118
    //   #487	-> 123
    //   #486	-> 133
    //   #488	-> 138
    //   #490	-> 141
    //   #491	-> 148
    //   #495	-> 176
    //   #496	-> 181
    //   #495	-> 187
    //   #497	-> 192
    //   #501	-> 195
    //   #502	-> 203
    //   #507	-> 205
    //   #508	-> 215
    //   #509	-> 226
    //   #513	-> 229
    //   #514	-> 236
    //   #515	-> 241
    //   #516	-> 252
    //   #519	-> 255
    //   #521	-> 266
    //   #527	-> 276
    //   #528	-> 284
    // Local variable table:
    //   start	length	slot	name	descriptor
    //   32	3	5	newType	Lcom/fasterxml/jackson/databind/JavaType;
    //   138	3	5	newType	Lcom/fasterxml/jackson/databind/JavaType;
    //   192	3	5	newType	Lcom/fasterxml/jackson/databind/JavaType;
    //   226	3	5	newType	Lcom/fasterxml/jackson/databind/JavaType;
    //   252	3	5	newType	Lcom/fasterxml/jackson/databind/JavaType;
    //   236	40	6	typeParamCount	I
    //   266	10	7	tb	Lcom/fasterxml/jackson/databind/type/TypeBindings;
    //   0	287	0	this	Lcom/fasterxml/jackson/databind/type/TypeFactory;
    //   0	287	1	baseType	Lcom/fasterxml/jackson/databind/JavaType;
    //   0	287	2	subclass	Ljava/lang/Class;
    //   0	287	3	relaxedCompatibilityCheck	Z
    //   6	281	4	rawBase	Ljava/lang/Class;
    //   276	11	5	newType	Lcom/fasterxml/jackson/databind/JavaType;
    // Local variable type table:
    //   start	length	slot	name	signature
    //   0	287	2	subclass	Ljava/lang/Class<*>;
    //   6	281	4	rawBase	Ljava/lang/Class<*>;
  }
  
  private TypeBindings _bindingsForSubtype(JavaType baseType, int typeParamCount, Class<?> subclass, boolean relaxedCompatibilityCheck) {
    PlaceholderForType[] placeholders = new PlaceholderForType[typeParamCount];
    for (int i = 0; i < typeParamCount; i++)
      placeholders[i] = new PlaceholderForType(i); 
    TypeBindings b = TypeBindings.create(subclass, (JavaType[])placeholders);
    JavaType tmpSub = _fromClass(null, subclass, b);
    JavaType baseWithPlaceholders = tmpSub.findSuperType(baseType.getRawClass());
    if (baseWithPlaceholders == null)
      throw new IllegalArgumentException(String.format("Internal error: unable to locate supertype (%s) from resolved subtype %s", new Object[] { baseType
              .getRawClass().getName(), subclass
              .getName() })); 
    String error = _resolveTypePlaceholders(baseType, baseWithPlaceholders);
    if (error != null)
      if (!relaxedCompatibilityCheck)
        throw new IllegalArgumentException("Failed to specialize base type " + baseType.toCanonical() + " as " + subclass
            .getName() + ", problem: " + error);  
    JavaType[] typeParams = new JavaType[typeParamCount];
    for (int j = 0; j < typeParamCount; j++) {
      JavaType t = placeholders[j].actualType();
      if (t == null)
        t = unknownType(); 
      typeParams[j] = t;
    } 
    return TypeBindings.create(subclass, typeParams);
  }
  
  private String _resolveTypePlaceholders(JavaType sourceType, JavaType actualType) throws IllegalArgumentException {
    List<JavaType> expectedTypes = sourceType.getBindings().getTypeParameters();
    List<JavaType> actualTypes = actualType.getBindings().getTypeParameters();
    int actCount = actualTypes.size();
    for (int i = 0, expCount = expectedTypes.size(); i < expCount; i++) {
      JavaType exp = expectedTypes.get(i);
      JavaType act = (i < actCount) ? actualTypes.get(i) : unknownType();
      if (!_verifyAndResolvePlaceholders(exp, act))
        if (!exp.hasRawClass(Object.class))
          if (i != 0 || 
            !sourceType.isMapLikeType() || 
            !act.hasRawClass(Object.class))
            if (!exp.isInterface() || 
              !exp.isTypeOrSuperTypeOf(act.getRawClass()))
              return String.format("Type parameter #%d/%d differs; can not specialize %s with %s", new Object[] { Integer.valueOf(i + 1), Integer.valueOf(expCount), exp.toCanonical(), act.toCanonical() });    
    } 
    return null;
  }
  
  private boolean _verifyAndResolvePlaceholders(JavaType exp, JavaType act) {
    if (act instanceof PlaceholderForType) {
      ((PlaceholderForType)act).actualType(exp);
      return true;
    } 
    if (exp.getRawClass() != act.getRawClass())
      return false; 
    List<JavaType> expectedTypes = exp.getBindings().getTypeParameters();
    List<JavaType> actualTypes = act.getBindings().getTypeParameters();
    for (int i = 0, len = expectedTypes.size(); i < len; i++) {
      JavaType exp2 = expectedTypes.get(i);
      JavaType act2 = actualTypes.get(i);
      if (!_verifyAndResolvePlaceholders(exp2, act2))
        return false; 
    } 
    return true;
  }
  
  public JavaType constructGeneralizedType(JavaType baseType, Class<?> superClass) {
    Class<?> rawBase = baseType.getRawClass();
    if (rawBase == superClass)
      return baseType; 
    JavaType superType = baseType.findSuperType(superClass);
    if (superType == null) {
      if (!superClass.isAssignableFrom(rawBase))
        throw new IllegalArgumentException(String.format("Class %s not a super-type of %s", new Object[] { superClass
                .getName(), baseType })); 
      throw new IllegalArgumentException(String.format("Internal error: class %s not included as super-type for %s", new Object[] { superClass
              
              .getName(), baseType }));
    } 
    return superType;
  }
  
  public JavaType constructFromCanonical(String canonical) throws IllegalArgumentException {
    return this._parser.parse(canonical);
  }
  
  public JavaType[] findTypeParameters(JavaType type, Class<?> expType) {
    JavaType match = type.findSuperType(expType);
    if (match == null)
      return NO_TYPES; 
    return match.getBindings().typeParameterArray();
  }
  
  public JavaType findFirstTypeParameter(JavaType type, Class<?> expType) {
    JavaType match = type.findSuperType(expType);
    if (match != null) {
      JavaType t = match.getBindings().getBoundType(0);
      if (t != null)
        return t; 
    } 
    return _unknownType();
  }
  
  @Deprecated
  public JavaType[] findTypeParameters(Class<?> clz, Class<?> expType, TypeBindings bindings) {
    return findTypeParameters(constructType(clz, bindings), expType);
  }
  
  @Deprecated
  public JavaType[] findTypeParameters(Class<?> clz, Class<?> expType) {
    return findTypeParameters(constructType(clz), expType);
  }
  
  public JavaType moreSpecificType(JavaType type1, JavaType type2) {
    if (type1 == null)
      return type2; 
    if (type2 == null)
      return type1; 
    Class<?> raw1 = type1.getRawClass();
    Class<?> raw2 = type2.getRawClass();
    if (raw1 == raw2)
      return type1; 
    if (raw1.isAssignableFrom(raw2))
      return type2; 
    return type1;
  }
  
  public JavaType constructType(Type type) {
    return _fromAny(null, type, EMPTY_BINDINGS);
  }
  
  public JavaType constructType(TypeReference<?> typeRef) {
    return _fromAny(null, typeRef.getType(), EMPTY_BINDINGS);
  }
  
  public JavaType resolveMemberType(Type type, TypeBindings contextBindings) {
    return _fromAny(null, type, contextBindings);
  }
  
  @Deprecated
  public JavaType constructType(Type type, TypeBindings bindings) {
    if (type instanceof Class) {
      JavaType resultType = _fromClass(null, (Class)type, bindings);
      return _applyModifiers(type, resultType);
    } 
    return _fromAny(null, type, bindings);
  }
  
  @Deprecated
  public JavaType constructType(Type type, Class<?> contextClass) {
    JavaType contextType = (contextClass == null) ? null : constructType(contextClass);
    return constructType(type, contextType);
  }
  
  @Deprecated
  public JavaType constructType(Type type, JavaType contextType) {
    TypeBindings bindings;
    if (contextType == null) {
      bindings = EMPTY_BINDINGS;
    } else {
      bindings = contextType.getBindings();
      if (type.getClass() != Class.class)
        while (bindings.isEmpty()) {
          contextType = contextType.getSuperClass();
          if (contextType == null)
            break; 
          bindings = contextType.getBindings();
        }  
    } 
    return _fromAny(null, type, bindings);
  }
  
  public ArrayType constructArrayType(Class<?> elementType) {
    return ArrayType.construct(_fromAny(null, elementType, null), null);
  }
  
  public ArrayType constructArrayType(JavaType elementType) {
    return ArrayType.construct(elementType, null);
  }
  
  public CollectionType constructCollectionType(Class<? extends Collection> collectionClass, Class<?> elementClass) {
    return constructCollectionType(collectionClass, 
        _fromClass(null, elementClass, EMPTY_BINDINGS));
  }
  
  public CollectionType constructCollectionType(Class<? extends Collection> collectionClass, JavaType elementType) {
    TypeBindings bindings = TypeBindings.createIfNeeded(collectionClass, elementType);
    CollectionType result = (CollectionType)_fromClass(null, collectionClass, bindings);
    if (bindings.isEmpty() && elementType != null) {
      JavaType t = result.findSuperType(Collection.class);
      JavaType realET = t.getContentType();
      if (!realET.equals(elementType))
        throw new IllegalArgumentException(String.format("Non-generic Collection class %s did not resolve to something with element type %s but %s ", new Object[] { ClassUtil.nameOf(collectionClass), elementType, realET })); 
    } 
    return result;
  }
  
  public CollectionLikeType constructCollectionLikeType(Class<?> collectionClass, Class<?> elementClass) {
    return constructCollectionLikeType(collectionClass, 
        _fromClass(null, elementClass, EMPTY_BINDINGS));
  }
  
  public CollectionLikeType constructCollectionLikeType(Class<?> collectionClass, JavaType elementType) {
    JavaType type = _fromClass(null, collectionClass, 
        TypeBindings.createIfNeeded(collectionClass, elementType));
    if (type instanceof CollectionLikeType)
      return (CollectionLikeType)type; 
    return CollectionLikeType.upgradeFrom(type, elementType);
  }
  
  public MapType constructMapType(Class<? extends Map> mapClass, Class<?> keyClass, Class<?> valueClass) {
    JavaType kt;
    JavaType vt;
    if (mapClass == Properties.class) {
      kt = vt = CORE_TYPE_STRING;
    } else {
      kt = _fromClass(null, keyClass, EMPTY_BINDINGS);
      vt = _fromClass(null, valueClass, EMPTY_BINDINGS);
    } 
    return constructMapType(mapClass, kt, vt);
  }
  
  public MapType constructMapType(Class<? extends Map> mapClass, JavaType keyType, JavaType valueType) {
    TypeBindings bindings = TypeBindings.createIfNeeded(mapClass, new JavaType[] { keyType, valueType });
    MapType result = (MapType)_fromClass(null, mapClass, bindings);
    if (bindings.isEmpty()) {
      JavaType t = result.findSuperType(Map.class);
      JavaType realKT = t.getKeyType();
      if (!realKT.equals(keyType))
        throw new IllegalArgumentException(String.format("Non-generic Map class %s did not resolve to something with key type %s but %s ", new Object[] { ClassUtil.nameOf(mapClass), keyType, realKT })); 
      JavaType realVT = t.getContentType();
      if (!realVT.equals(valueType))
        throw new IllegalArgumentException(String.format("Non-generic Map class %s did not resolve to something with value type %s but %s ", new Object[] { ClassUtil.nameOf(mapClass), valueType, realVT })); 
    } 
    return result;
  }
  
  public MapLikeType constructMapLikeType(Class<?> mapClass, Class<?> keyClass, Class<?> valueClass) {
    return constructMapLikeType(mapClass, 
        _fromClass(null, keyClass, EMPTY_BINDINGS), 
        _fromClass(null, valueClass, EMPTY_BINDINGS));
  }
  
  public MapLikeType constructMapLikeType(Class<?> mapClass, JavaType keyType, JavaType valueType) {
    JavaType type = _fromClass(null, mapClass, 
        TypeBindings.createIfNeeded(mapClass, new JavaType[] { keyType, valueType }));
    if (type instanceof MapLikeType)
      return (MapLikeType)type; 
    return MapLikeType.upgradeFrom(type, keyType, valueType);
  }
  
  public JavaType constructSimpleType(Class<?> rawType, JavaType[] parameterTypes) {
    return _fromClass(null, rawType, TypeBindings.create(rawType, parameterTypes));
  }
  
  @Deprecated
  public JavaType constructSimpleType(Class<?> rawType, Class<?> parameterTarget, JavaType[] parameterTypes) {
    return constructSimpleType(rawType, parameterTypes);
  }
  
  public JavaType constructReferenceType(Class<?> rawType, JavaType referredType) {
    return ReferenceType.construct(rawType, 
        TypeBindings.create(rawType, referredType), null, null, referredType);
  }
  
  @Deprecated
  public JavaType uncheckedSimpleType(Class<?> cls) {
    return _constructSimple(cls, EMPTY_BINDINGS, null, null);
  }
  
  public JavaType constructParametricType(Class<?> parametrized, Class<?>... parameterClasses) {
    int len = parameterClasses.length;
    JavaType[] pt = new JavaType[len];
    for (int i = 0; i < len; i++)
      pt[i] = _fromClass(null, parameterClasses[i], EMPTY_BINDINGS); 
    return constructParametricType(parametrized, pt);
  }
  
  public JavaType constructParametricType(Class<?> rawType, JavaType... parameterTypes) {
    return constructParametricType(rawType, TypeBindings.create(rawType, parameterTypes));
  }
  
  public JavaType constructParametricType(Class<?> rawType, TypeBindings parameterTypes) {
    JavaType resultType = _fromClass(null, rawType, parameterTypes);
    return _applyModifiers(rawType, resultType);
  }
  
  @Deprecated
  public JavaType constructParametrizedType(Class<?> parametrized, Class<?> parametersFor, JavaType... parameterTypes) {
    return constructParametricType(parametrized, parameterTypes);
  }
  
  @Deprecated
  public JavaType constructParametrizedType(Class<?> parametrized, Class<?> parametersFor, Class<?>... parameterClasses) {
    return constructParametricType(parametrized, parameterClasses);
  }
  
  public CollectionType constructRawCollectionType(Class<? extends Collection> collectionClass) {
    return constructCollectionType(collectionClass, unknownType());
  }
  
  public CollectionLikeType constructRawCollectionLikeType(Class<?> collectionClass) {
    return constructCollectionLikeType(collectionClass, unknownType());
  }
  
  public MapType constructRawMapType(Class<? extends Map> mapClass) {
    return constructMapType(mapClass, unknownType(), unknownType());
  }
  
  public MapLikeType constructRawMapLikeType(Class<?> mapClass) {
    return constructMapLikeType(mapClass, unknownType(), unknownType());
  }
  
  private JavaType _mapType(Class<?> rawClass, TypeBindings bindings, JavaType superClass, JavaType[] superInterfaces) {
    JavaType kt;
    JavaType vt;
    if (rawClass == Properties.class) {
      kt = vt = CORE_TYPE_STRING;
    } else {
      List<JavaType> typeParams = bindings.getTypeParameters();
      int pc = typeParams.size();
      switch (pc) {
        case 0:
          kt = vt = _unknownType();
          return MapType.construct(rawClass, bindings, superClass, superInterfaces, kt, vt);
        case 2:
          kt = typeParams.get(0);
          vt = typeParams.get(1);
          return MapType.construct(rawClass, bindings, superClass, superInterfaces, kt, vt);
      } 
      throw new IllegalArgumentException(String.format("Strange Map type %s with %d type parameter%s (%s), can not resolve", new Object[] { ClassUtil.nameOf(rawClass), Integer.valueOf(pc), (pc == 1) ? "" : "s", bindings }));
    } 
    return MapType.construct(rawClass, bindings, superClass, superInterfaces, kt, vt);
  }
  
  private JavaType _collectionType(Class<?> rawClass, TypeBindings bindings, JavaType superClass, JavaType[] superInterfaces) {
    JavaType ct;
    List<JavaType> typeParams = bindings.getTypeParameters();
    if (typeParams.isEmpty()) {
      ct = _unknownType();
    } else if (typeParams.size() == 1) {
      ct = typeParams.get(0);
    } else {
      throw new IllegalArgumentException("Strange Collection type " + rawClass.getName() + ": cannot determine type parameters");
    } 
    return CollectionType.construct(rawClass, bindings, superClass, superInterfaces, ct);
  }
  
  private JavaType _referenceType(Class<?> rawClass, TypeBindings bindings, JavaType superClass, JavaType[] superInterfaces) {
    JavaType ct;
    List<JavaType> typeParams = bindings.getTypeParameters();
    if (typeParams.isEmpty()) {
      ct = _unknownType();
    } else if (typeParams.size() == 1) {
      ct = typeParams.get(0);
    } else {
      throw new IllegalArgumentException("Strange Reference type " + rawClass.getName() + ": cannot determine type parameters");
    } 
    return ReferenceType.construct(rawClass, bindings, superClass, superInterfaces, ct);
  }
  
  private JavaType _iterationType(Class<?> rawClass, TypeBindings bindings, JavaType superClass, JavaType[] superInterfaces) {
    JavaType ct;
    List<JavaType> typeParams = bindings.getTypeParameters();
    if (typeParams.isEmpty()) {
      ct = _unknownType();
    } else if (typeParams.size() == 1) {
      ct = typeParams.get(0);
    } else {
      throw new IllegalArgumentException("Strange Iteration type " + rawClass.getName() + ": cannot determine type parameters");
    } 
    return _iterationType(rawClass, bindings, superClass, superInterfaces, ct);
  }
  
  private JavaType _iterationType(Class<?> rawClass, TypeBindings bindings, JavaType superClass, JavaType[] superInterfaces, JavaType iteratedType) {
    return IterationType.construct(rawClass, bindings, superClass, superInterfaces, iteratedType);
  }
  
  protected JavaType _constructSimple(Class<?> raw, TypeBindings bindings, JavaType superClass, JavaType[] superInterfaces) {
    if (bindings.isEmpty()) {
      JavaType result = _findWellKnownSimple(raw);
      if (result != null)
        return result; 
    } 
    return _newSimpleType(raw, bindings, superClass, superInterfaces);
  }
  
  protected JavaType _newSimpleType(Class<?> raw, TypeBindings bindings, JavaType superClass, JavaType[] superInterfaces) {
    return new SimpleType(raw, bindings, superClass, superInterfaces);
  }
  
  protected JavaType _unknownType() {
    return CORE_TYPE_OBJECT;
  }
  
  protected JavaType _findWellKnownSimple(Class<?> clz) {
    if (clz.isPrimitive()) {
      if (clz == CLS_BOOL)
        return CORE_TYPE_BOOL; 
      if (clz == CLS_INT)
        return CORE_TYPE_INT; 
      if (clz == CLS_LONG)
        return CORE_TYPE_LONG; 
      if (clz == CLS_DOUBLE)
        return CORE_TYPE_DOUBLE; 
    } else {
      if (clz == CLS_STRING)
        return CORE_TYPE_STRING; 
      if (clz == CLS_OBJECT)
        return CORE_TYPE_OBJECT; 
      if (clz == CLS_JSON_NODE)
        return CORE_TYPE_JSON_NODE; 
    } 
    return null;
  }
  
  protected JavaType _fromAny(ClassStack context, Type srcType, TypeBindings bindings) {
    JavaType resultType;
    if (srcType instanceof Class) {
      resultType = _fromClass(context, (Class)srcType, EMPTY_BINDINGS);
    } else if (srcType instanceof ParameterizedType) {
      resultType = _fromParamType(context, (ParameterizedType)srcType, bindings);
    } else {
      if (srcType instanceof JavaType)
        return (JavaType)srcType; 
      if (srcType instanceof GenericArrayType) {
        resultType = _fromArrayType(context, (GenericArrayType)srcType, bindings);
      } else if (srcType instanceof TypeVariable) {
        resultType = _fromVariable(context, (TypeVariable)srcType, bindings);
      } else if (srcType instanceof WildcardType) {
        resultType = _fromWildcard(context, (WildcardType)srcType, bindings);
      } else {
        throw new IllegalArgumentException("Unrecognized Type: " + ((srcType == null) ? "[null]" : srcType.toString()));
      } 
    } 
    return _applyModifiers(srcType, resultType);
  }
  
  protected JavaType _applyModifiers(Type srcType, JavaType resolvedType) {
    if (this._modifiers == null)
      return resolvedType; 
    JavaType resultType = resolvedType;
    TypeBindings b = resultType.getBindings();
    if (b == null)
      b = EMPTY_BINDINGS; 
    for (TypeModifier mod : this._modifiers) {
      JavaType t = mod.modifyType(resultType, srcType, b, this);
      if (t == null)
        throw new IllegalStateException(String.format("TypeModifier %s (of type %s) return null for type %s", new Object[] { mod, mod
                
                .getClass().getName(), resultType })); 
      resultType = t;
    } 
    return resultType;
  }
  
  protected JavaType _fromClass(ClassStack context, Class<?> rawType, TypeBindings bindings) {
    Object key;
    JavaType result = _findWellKnownSimple(rawType);
    if (result != null)
      return result; 
    if (bindings == null || bindings.isEmpty()) {
      key = rawType;
    } else {
      key = bindings.asKey(rawType);
    } 
    result = (key == null) ? null : (JavaType)this._typeCache.get(key);
    if (result != null)
      return result; 
    if (context == null) {
      context = new ClassStack(rawType);
    } else {
      ClassStack prev = context.find(rawType);
      if (prev != null) {
        ResolvedRecursiveType selfRef = new ResolvedRecursiveType(rawType, EMPTY_BINDINGS);
        prev.addSelfReference(selfRef);
        return selfRef;
      } 
      context = context.child(rawType);
    } 
    if (rawType.isArray()) {
      result = ArrayType.construct(_fromAny(context, rawType.getComponentType(), bindings), bindings);
    } else {
      JavaType superClass;
      JavaType[] superInterfaces;
      if (rawType.isInterface()) {
        superClass = null;
        superInterfaces = _resolveSuperInterfaces(context, rawType, bindings);
      } else {
        superClass = _resolveSuperClass(context, rawType, bindings);
        superInterfaces = _resolveSuperInterfaces(context, rawType, bindings);
      } 
      if (rawType == Properties.class) {
        result = MapType.construct(rawType, bindings, superClass, superInterfaces, CORE_TYPE_STRING, CORE_TYPE_STRING);
      } else if (superClass != null) {
        result = superClass.refine(rawType, bindings, superClass, superInterfaces);
      } 
      if (result == null) {
        result = _fromWellKnownClass(context, rawType, bindings, superClass, superInterfaces);
        if (result == null) {
          result = _fromWellKnownInterface(context, rawType, bindings, superClass, superInterfaces);
          if (result == null)
            result = _newSimpleType(rawType, bindings, superClass, superInterfaces); 
        } 
      } 
    } 
    context.resolveSelfReferences(result);
    if (key != null && !result.hasHandlers())
      this._typeCache.putIfAbsent(key, result); 
    return result;
  }
  
  protected JavaType _resolveSuperClass(ClassStack context, Class<?> rawType, TypeBindings parentBindings) {
    Type parent = ClassUtil.getGenericSuperclass(rawType);
    if (parent == null)
      return null; 
    return _fromAny(context, parent, parentBindings);
  }
  
  protected JavaType[] _resolveSuperInterfaces(ClassStack context, Class<?> rawType, TypeBindings parentBindings) {
    Type[] types = ClassUtil.getGenericInterfaces(rawType);
    if (types == null || types.length == 0)
      return NO_TYPES; 
    int len = types.length;
    JavaType[] resolved = new JavaType[len];
    for (int i = 0; i < len; i++) {
      Type type = types[i];
      resolved[i] = _fromAny(context, type, parentBindings);
    } 
    return resolved;
  }
  
  protected JavaType _fromWellKnownClass(ClassStack context, Class<?> rawType, TypeBindings bindings, JavaType superClass, JavaType[] superInterfaces) {
    if (bindings == null)
      bindings = EMPTY_BINDINGS; 
    if (rawType == Map.class)
      return _mapType(rawType, bindings, superClass, superInterfaces); 
    if (rawType == Collection.class)
      return _collectionType(rawType, bindings, superClass, superInterfaces); 
    if (rawType == AtomicReference.class)
      return _referenceType(rawType, bindings, superClass, superInterfaces); 
    if (rawType == Iterator.class || rawType == Stream.class)
      return _iterationType(rawType, bindings, superClass, superInterfaces); 
    if (BaseStream.class.isAssignableFrom(rawType)) {
      if (DoubleStream.class.isAssignableFrom(rawType))
        return _iterationType(rawType, bindings, superClass, superInterfaces, CORE_TYPE_DOUBLE); 
      if (IntStream.class.isAssignableFrom(rawType))
        return _iterationType(rawType, bindings, superClass, superInterfaces, CORE_TYPE_INT); 
      if (LongStream.class.isAssignableFrom(rawType))
        return _iterationType(rawType, bindings, superClass, superInterfaces, CORE_TYPE_LONG); 
    } 
    return null;
  }
  
  protected JavaType _fromWellKnownInterface(ClassStack context, Class<?> rawType, TypeBindings bindings, JavaType superClass, JavaType[] superInterfaces) {
    int intCount = superInterfaces.length;
    for (int i = 0; i < intCount; i++) {
      JavaType result = superInterfaces[i].refine(rawType, bindings, superClass, superInterfaces);
      if (result != null)
        return result; 
    } 
    return null;
  }
  
  protected JavaType _fromParamType(ClassStack context, ParameterizedType ptype, TypeBindings parentBindings) {
    TypeBindings newBindings;
    Class<?> rawType = (Class)ptype.getRawType();
    if (rawType == CLS_ENUM)
      return CORE_TYPE_ENUM; 
    if (rawType == CLS_COMPARABLE)
      return CORE_TYPE_COMPARABLE; 
    Type[] args = ptype.getActualTypeArguments();
    int paramCount = (args == null) ? 0 : args.length;
    if (paramCount == 0) {
      newBindings = EMPTY_BINDINGS;
    } else {
      JavaType[] pt = new JavaType[paramCount];
      for (int i = 0; i < paramCount; i++)
        pt[i] = _fromAny(context, args[i], parentBindings); 
      newBindings = TypeBindings.create(rawType, pt);
    } 
    return _fromClass(context, rawType, newBindings);
  }
  
  protected JavaType _fromArrayType(ClassStack context, GenericArrayType type, TypeBindings bindings) {
    JavaType elementType = _fromAny(context, type.getGenericComponentType(), bindings);
    return ArrayType.construct(elementType, bindings);
  }
  
  protected JavaType _fromVariable(ClassStack context, TypeVariable<?> var, TypeBindings bindings) {
    Type[] bounds;
    String name = var.getName();
    if (bindings == null)
      throw new IllegalArgumentException("Null `bindings` passed (type variable \"" + name + "\")"); 
    JavaType type = bindings.findBoundType(name);
    if (type != null)
      return type; 
    if (bindings.hasUnbound(name))
      return CORE_TYPE_OBJECT; 
    bindings = bindings.withUnboundVariable(name);
    synchronized (var) {
      bounds = var.getBounds();
    } 
    return _fromAny(context, bounds[0], bindings);
  }
  
  protected JavaType _fromWildcard(ClassStack context, WildcardType type, TypeBindings bindings) {
    return _fromAny(context, type.getUpperBounds()[0], bindings);
  }
}
