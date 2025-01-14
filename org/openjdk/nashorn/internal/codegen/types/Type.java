package org.openjdk.nashorn.internal.codegen.types;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.Serializable;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.objectweb.asm.MethodVisitor;
import org.openjdk.nashorn.internal.codegen.CompilerConstants;
import org.openjdk.nashorn.internal.runtime.Context;
import org.openjdk.nashorn.internal.runtime.ScriptObject;
import org.openjdk.nashorn.internal.runtime.Undefined;

public abstract class Type implements Comparable<Type>, BytecodeOps, Serializable {
  private static final long serialVersionUID = 1L;
  
  private final transient String name;
  
  private final transient String descriptor;
  
  private final transient int weight;
  
  private final transient int slots;
  
  private final Class<?> clazz;
  
  private static final Map<Class<?>, org.objectweb.asm.Type> INTERNAL_TYPE_CACHE = Collections.synchronizedMap(new WeakHashMap<>());
  
  private final transient org.objectweb.asm.Type internalType;
  
  protected static final int MIN_WEIGHT = -1;
  
  protected static final int MAX_WEIGHT = 20;
  
  Type(String name, Class<?> clazz, int weight, int slots) {
    this.name = name;
    this.clazz = clazz;
    this.descriptor = org.objectweb.asm.Type.getDescriptor(clazz);
    this.weight = weight;
    assert weight >= -1 && weight <= 20 : "illegal type weight: " + weight;
    this.slots = slots;
    this.internalType = getInternalType(clazz);
  }
  
  public int getWeight() {
    return this.weight;
  }
  
  public Class<?> getTypeClass() {
    return this.clazz;
  }
  
  public Type nextWider() {
    return null;
  }
  
  public Class<?> getBoxedType() {
    assert !getTypeClass().isPrimitive();
    return null;
  }
  
  public static String getMethodDescriptor(Type returnType, Type... types) {
    org.objectweb.asm.Type[] itypes = new org.objectweb.asm.Type[types.length];
    for (int i = 0; i < types.length; i++)
      itypes[i] = types[i].getInternalType(); 
    return org.objectweb.asm.Type.getMethodDescriptor(returnType.getInternalType(), itypes);
  }
  
  public static String getMethodDescriptor(Class<?> returnType, Class<?>... types) {
    org.objectweb.asm.Type[] itypes = new org.objectweb.asm.Type[types.length];
    for (int i = 0; i < types.length; i++)
      itypes[i] = getInternalType(types[i]); 
    return org.objectweb.asm.Type.getMethodDescriptor(getInternalType(returnType), itypes);
  }
  
  public static char getShortSignatureDescriptor(Type type) {
    if (type instanceof BooleanType)
      return 'Z'; 
    return type.getBytecodeStackType();
  }
  
  private static Type typeFor(org.objectweb.asm.Type itype) {
    switch (itype.getSort()) {
      case 1:
        return BOOLEAN;
      case 5:
        return INT;
      case 7:
        return LONG;
      case 8:
        return NUMBER;
      case 10:
        if (Context.isStructureClass(itype.getClassName()))
          return SCRIPT_OBJECT; 
        return cacheByName.computeIfAbsent(itype.getClassName(), name -> {
              try {
                return typeFor(Class.forName(name));
              } catch (ClassNotFoundException e) {
                throw new AssertionError(e);
              } 
            });
      case 0:
        return null;
      case 9:
        switch (itype.getElementType().getSort()) {
          case 8:
            return NUMBER_ARRAY;
          case 5:
            return INT_ARRAY;
          case 7:
            return LONG_ARRAY;
          default:
            assert false;
            break;
          case 10:
            break;
        } 
        return OBJECT_ARRAY;
    } 
    assert false : "Unknown itype : " + itype + " sort " + itype.getSort();
    return null;
  }
  
  public static Type getMethodReturnType(String methodDescriptor) {
    return typeFor(org.objectweb.asm.Type.getReturnType(methodDescriptor));
  }
  
  public static Type[] getMethodArguments(String methodDescriptor) {
    org.objectweb.asm.Type[] itypes = org.objectweb.asm.Type.getArgumentTypes(methodDescriptor);
    Type[] types = new Type[itypes.length];
    for (int i = 0; i < itypes.length; i++)
      types[i] = typeFor(itypes[i]); 
    return types;
  }
  
  public static void writeTypeMap(Map<Integer, Type> typeMap, DataOutput output) throws IOException {
    if (typeMap == null) {
      output.writeInt(0);
    } else {
      output.writeInt(typeMap.size());
      for (Map.Entry<Integer, Type> e : typeMap.entrySet()) {
        byte typeChar;
        output.writeInt(((Integer)e.getKey()).intValue());
        Type type = e.getValue();
        if (type == OBJECT) {
          typeChar = 76;
        } else if (type == NUMBER) {
          typeChar = 68;
        } else if (type == LONG) {
          typeChar = 74;
        } else {
          throw new AssertionError();
        } 
        output.writeByte(typeChar);
      } 
    } 
  }
  
  public static Map<Integer, Type> readTypeMap(DataInput input) throws IOException {
    int size = input.readInt();
    if (size <= 0)
      return null; 
    Map<Integer, Type> map = new TreeMap<>();
    for (int i = 0; i < size; ) {
      Type type;
      int pp = input.readInt();
      int typeChar = input.readByte();
      switch (typeChar) {
        case 76:
          type = OBJECT;
          break;
        case 68:
          type = NUMBER;
          break;
        case 74:
          type = LONG;
          break;
        default:
          i++;
          continue;
      } 
      map.put(Integer.valueOf(pp), type);
    } 
    return map;
  }
  
  static org.objectweb.asm.Type getInternalType(String className) {
    return org.objectweb.asm.Type.getType(className);
  }
  
  private org.objectweb.asm.Type getInternalType() {
    return this.internalType;
  }
  
  private static org.objectweb.asm.Type lookupInternalType(Class<?> type) {
    Map<Class<?>, org.objectweb.asm.Type> c = INTERNAL_TYPE_CACHE;
    org.objectweb.asm.Type itype = c.get(type);
    if (itype != null)
      return itype; 
    itype = org.objectweb.asm.Type.getType(type);
    c.put(type, itype);
    return itype;
  }
  
  private static org.objectweb.asm.Type getInternalType(Class<?> type) {
    return lookupInternalType(type);
  }
  
  static void invokestatic(MethodVisitor method, CompilerConstants.Call call) {
    method.visitMethodInsn(184, call.className(), call.name(), call.descriptor(), false);
  }
  
  public String getInternalName() {
    return org.objectweb.asm.Type.getInternalName(getTypeClass());
  }
  
  public static String getInternalName(Class<?> clazz) {
    return org.objectweb.asm.Type.getInternalName(clazz);
  }
  
  public boolean isUnknown() {
    return equals(UNKNOWN);
  }
  
  public boolean isJSPrimitive() {
    return (!isObject() || isString());
  }
  
  public boolean isBoolean() {
    return equals(BOOLEAN);
  }
  
  public boolean isInteger() {
    return equals(INT);
  }
  
  public boolean isLong() {
    return equals(LONG);
  }
  
  public boolean isNumber() {
    return equals(NUMBER);
  }
  
  public boolean isNumeric() {
    return this instanceof NumericType;
  }
  
  public boolean isArray() {
    return this instanceof ArrayType;
  }
  
  public boolean isCategory2() {
    return (getSlots() == 2);
  }
  
  public boolean isObject() {
    return this instanceof ObjectType;
  }
  
  public boolean isPrimitive() {
    return !isObject();
  }
  
  public boolean isString() {
    return equals(STRING);
  }
  
  public boolean isCharSequence() {
    return equals(CHARSEQUENCE);
  }
  
  public boolean isEquivalentTo(Type type) {
    return (weight() == type.weight() || (isObject() && type.isObject()));
  }
  
  public static boolean isAssignableFrom(Type type0, Type type1) {
    if (type0.isObject() && type1.isObject())
      return (type0.weight() >= type1.weight()); 
    return (type0.weight() == type1.weight());
  }
  
  public boolean isAssignableFrom(Type type) {
    return isAssignableFrom(this, type);
  }
  
  public static boolean areEquivalent(Type type0, Type type1) {
    return type0.isEquivalentTo(type1);
  }
  
  public int getSlots() {
    return this.slots;
  }
  
  public static Type widest(Type type0, Type type1) {
    if (type0.isArray() && type1.isArray())
      return (((ArrayType)type0).getElementType() == ((ArrayType)type1).getElementType()) ? type0 : OBJECT; 
    if (type0.isArray() != type1.isArray())
      return OBJECT; 
    if (type0.isObject() && type1.isObject() && type0.getTypeClass() != type1.getTypeClass())
      return OBJECT; 
    return (type0.weight() > type1.weight()) ? type0 : type1;
  }
  
  public static Class<?> widest(Class<?> type0, Class<?> type1) {
    return widest(typeFor(type0), typeFor(type1)).getTypeClass();
  }
  
  public static Type widestReturnType(Type t1, Type t2) {
    if (t1.isUnknown())
      return t2; 
    if (t2.isUnknown())
      return t1; 
    if (t1.isBoolean() != t2.isBoolean() || t1.isNumeric() != t2.isNumeric())
      return OBJECT; 
    return widest(t1, t2);
  }
  
  public static Type generic(Type type) {
    return type.isObject() ? OBJECT : type;
  }
  
  public static Type narrowest(Type type0, Type type1) {
    return type0.narrowerThan(type1) ? type0 : type1;
  }
  
  public boolean narrowerThan(Type type) {
    return (weight() < type.weight());
  }
  
  public boolean widerThan(Type type) {
    return (weight() > type.weight());
  }
  
  public static Type widest(Type type0, Type type1, Type limit) {
    Type type = widest(type0, type1);
    if (type.weight() > limit.weight())
      return limit; 
    return type;
  }
  
  public static Type narrowest(Type type0, Type type1, Type limit) {
    Type type = (type0.weight() < type1.weight()) ? type0 : type1;
    if (type.weight() < limit.weight())
      return limit; 
    return type;
  }
  
  public Type narrowest(Type other) {
    return narrowest(this, other);
  }
  
  public Type widest(Type other) {
    return widest(this, other);
  }
  
  int weight() {
    return this.weight;
  }
  
  public String getDescriptor() {
    return this.descriptor;
  }
  
  public String getShortDescriptor() {
    return this.descriptor;
  }
  
  public String toString() {
    return this.name;
  }
  
  public static Type typeFor(Class<?> clazz) {
    return cache.computeIfAbsent(clazz, keyClass -> {
          assert !keyClass.isPrimitive() || keyClass == void.class;
          return keyClass.isArray() ? new ArrayType(keyClass) : new ObjectType(keyClass);
        });
  }
  
  public int compareTo(Type o) {
    return o.weight() - weight();
  }
  
  public Type dup(MethodVisitor method, int depth) {
    return dup(method, this, depth);
  }
  
  public Type swap(MethodVisitor method, Type other) {
    swap(method, this, other);
    return other;
  }
  
  public Type pop(MethodVisitor method) {
    pop(method, this);
    return this;
  }
  
  public Type loadEmpty(MethodVisitor method) {
    assert false : "unsupported operation";
    return null;
  }
  
  protected static void pop(MethodVisitor method, Type type) {
    method.visitInsn(type.isCategory2() ? 88 : 87);
  }
  
  private static Type dup(MethodVisitor method, Type type, int depth) {
    boolean cat2 = type.isCategory2();
    switch (depth) {
      case 0:
        method.visitInsn(cat2 ? 92 : 89);
        return type;
      case 1:
        method.visitInsn(cat2 ? 93 : 90);
        return type;
      case 2:
        method.visitInsn(cat2 ? 94 : 91);
        return type;
    } 
    return null;
  }
  
  private static void swap(MethodVisitor method, Type above, Type below) {
    if (below.isCategory2()) {
      if (above.isCategory2()) {
        method.visitInsn(94);
        method.visitInsn(88);
      } else {
        method.visitInsn(91);
        method.visitInsn(87);
      } 
    } else if (above.isCategory2()) {
      method.visitInsn(93);
      method.visitInsn(88);
    } else {
      method.visitInsn(95);
    } 
  }
  
  private static final ConcurrentMap<Class<?>, Type> cache = new ConcurrentHashMap<>();
  
  private static final ConcurrentMap<String, Type> cacheByName = new ConcurrentHashMap<>();
  
  public static final Type BOOLEAN = putInCache(new BooleanType());
  
  public static final BitwiseType INT = putInCache(new IntType());
  
  public static final NumericType NUMBER = putInCache(new NumberType());
  
  public static final Type LONG = putInCache(new LongType());
  
  public static final Type STRING = putInCache(new ObjectType(String.class));
  
  public static final Type CHARSEQUENCE = putInCache(new ObjectType(CharSequence.class));
  
  public static final Type OBJECT = putInCache(new ObjectType());
  
  public static final Type UNDEFINED = putInCache(new ObjectType(Undefined.class));
  
  public static final Type SCRIPT_OBJECT = putInCache(new ObjectType(ScriptObject.class));
  
  public static final ArrayType INT_ARRAY = putInCache(new ArrayType(int[].class) {
        private static final long serialVersionUID = 1L;
        
        public void astore(MethodVisitor method) {
          method.visitInsn(79);
        }
        
        public Type aload(MethodVisitor method) {
          method.visitInsn(46);
          return INT;
        }
        
        public Type newarray(MethodVisitor method) {
          method.visitIntInsn(188, 10);
          return this;
        }
        
        public Type getElementType() {
          return INT;
        }
      });
  
  public static final ArrayType LONG_ARRAY = putInCache(new ArrayType(long[].class) {
        private static final long serialVersionUID = 1L;
        
        public void astore(MethodVisitor method) {
          method.visitInsn(80);
        }
        
        public Type aload(MethodVisitor method) {
          method.visitInsn(47);
          return LONG;
        }
        
        public Type newarray(MethodVisitor method) {
          method.visitIntInsn(188, 11);
          return this;
        }
        
        public Type getElementType() {
          return LONG;
        }
      });
  
  public static final ArrayType NUMBER_ARRAY = putInCache(new ArrayType(double[].class) {
        private static final long serialVersionUID = 1L;
        
        public void astore(MethodVisitor method) {
          method.visitInsn(82);
        }
        
        public Type aload(MethodVisitor method) {
          method.visitInsn(49);
          return NUMBER;
        }
        
        public Type newarray(MethodVisitor method) {
          method.visitIntInsn(188, 7);
          return this;
        }
        
        public Type getElementType() {
          return NUMBER;
        }
      });
  
  public static final ArrayType OBJECT_ARRAY = putInCache(new ArrayType(Object[].class));
  
  public static final Type THIS = new ObjectType() {
      private static final long serialVersionUID = 1L;
      
      public String toString() {
        return "this";
      }
    };
  
  public static final Type SCOPE = new ObjectType() {
      private static final long serialVersionUID = 1L;
      
      public String toString() {
        return "scope";
      }
    };
  
  private static abstract class ValueLessType extends Type {
    private static final long serialVersionUID = 1L;
    
    ValueLessType(String name) {
      super(name, Type.Unknown.class, -1, 1);
    }
    
    public Type load(MethodVisitor method, int slot) {
      throw new UnsupportedOperationException("load " + slot);
    }
    
    public void store(MethodVisitor method, int slot) {
      throw new UnsupportedOperationException("store " + slot);
    }
    
    public Type ldc(MethodVisitor method, Object c) {
      throw new UnsupportedOperationException("ldc " + c);
    }
    
    public Type loadUndefined(MethodVisitor method) {
      throw new UnsupportedOperationException("load undefined");
    }
    
    public Type loadForcedInitializer(MethodVisitor method) {
      throw new UnsupportedOperationException("load forced initializer");
    }
    
    public Type convert(MethodVisitor method, Type to) {
      throw new UnsupportedOperationException("convert => " + to);
    }
    
    public void _return(MethodVisitor method) {
      throw new UnsupportedOperationException("return");
    }
    
    public Type add(MethodVisitor method, int programPoint) {
      throw new UnsupportedOperationException("add");
    }
  }
  
  public static final Type UNKNOWN = new ValueLessType("<unknown>") {
      private static final long serialVersionUID = 1L;
      
      public String getDescriptor() {
        return "<unknown>";
      }
      
      public char getBytecodeStackType() {
        return 'U';
      }
    };
  
  public static final Type SLOT_2 = new ValueLessType("<slot_2>") {
      private static final long serialVersionUID = 1L;
      
      public String getDescriptor() {
        return "<slot_2>";
      }
      
      public char getBytecodeStackType() {
        throw new UnsupportedOperationException("getBytecodeStackType");
      }
    };
  
  private static <T extends Type> T putInCache(T type) {
    cache.put(type.getTypeClass(), (Type)type);
    return type;
  }
  
  protected final Object readResolve() {
    return typeFor(this.clazz);
  }
  
  public abstract char getBytecodeStackType();
  
  private static interface Unknown {}
}
