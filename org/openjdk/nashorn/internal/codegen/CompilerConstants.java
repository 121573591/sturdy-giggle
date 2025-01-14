package org.openjdk.nashorn.internal.codegen;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.objectweb.asm.MethodVisitor;
import org.openjdk.nashorn.internal.codegen.types.Type;
import org.openjdk.nashorn.internal.lookup.Lookup;
import org.openjdk.nashorn.internal.runtime.ScriptFunction;
import org.openjdk.nashorn.internal.runtime.ScriptObject;
import org.openjdk.nashorn.internal.runtime.Source;

public enum CompilerConstants {
  __FILE__,
  __DIR__,
  __LINE__,
  INIT("<init>"),
  EVAL("eval"),
  SOURCE("source", Source.class),
  CONSTANTS("constants", Object[].class),
  STRICT_MODE("strictMode", boolean.class),
  DEFAULT_SCRIPT_NAME("Script"),
  ANON_FUNCTION_PREFIX("L:"),
  NESTED_FUNCTION_SEPARATOR("#"),
  ID_FUNCTION_SEPARATOR("-"),
  PROGRAM(":program"),
  CREATE_PROGRAM_FUNCTION(":createProgramFunction"),
  THIS("this", Object.class),
  THIS_DEBUGGER(":this"),
  SCOPE(":scope", ScriptObject.class, 2),
  RETURN(":return"),
  CALLEE(":callee", ScriptFunction.class),
  VARARGS(":varargs", Object[].class),
  ARGUMENTS_VAR("arguments", Object.class),
  ARGUMENTS(":arguments", ScriptObject.class),
  EXPLODED_ARGUMENT_PREFIX(":xarg"),
  ITERATOR_PREFIX(":i", Iterator.class),
  SWITCH_TAG_PREFIX(":s"),
  EXCEPTION_PREFIX(":e", Throwable.class),
  QUICK_PREFIX(":q"),
  TEMP_PREFIX(":t"),
  LITERAL_PREFIX(":l"),
  REGEX_PREFIX(":r"),
  JAVA_THIS(null, 0),
  INIT_MAP(null, 1),
  INIT_SCOPE(null, 2),
  INIT_ARGUMENTS(null, 3),
  JS_OBJECT_DUAL_FIELD_PREFIX("JD"),
  JS_OBJECT_SINGLE_FIELD_PREFIX("JO"),
  ALLOCATE("allocate"),
  SPLIT_PREFIX(":split"),
  SPLIT_ARRAY_ARG(":split_array", 3),
  GET_STRING(":getString"),
  GET_MAP(":getMap"),
  SET_MAP(":setMap"),
  GET_ARRAY_PREFIX(":get"),
  GET_ARRAY_SUFFIX("$array");
  
  private static Set<String> symbolNames;
  
  private static final String INTERNAL_METHOD_PREFIX = ":";
  
  private final String symbolName;
  
  private final Class<?> type;
  
  private final int slot;
  
  CompilerConstants() {
    this.symbolName = name();
    this.type = null;
    this.slot = -1;
  }
  
  CompilerConstants(String symbolName, Class<?> type, int slot) {
    this.symbolName = symbolName;
    this.type = type;
    this.slot = slot;
  }
  
  public static boolean isCompilerConstant(String name) {
    ensureSymbolNames();
    return symbolNames.contains(name);
  }
  
  private static void ensureSymbolNames() {
    if (symbolNames == null) {
      symbolNames = new HashSet<>();
      for (CompilerConstants cc : values())
        symbolNames.add(cc.symbolName); 
    } 
  }
  
  public final String symbolName() {
    return this.symbolName;
  }
  
  public final Class<?> type() {
    return this.type;
  }
  
  public final int slot() {
    return this.slot;
  }
  
  public final String descriptor() {
    assert this.type != null : " asking for descriptor of typeless constant";
    return typeDescriptor(this.type);
  }
  
  public static String className(Class<?> type) {
    return Type.getInternalName(type);
  }
  
  public static String methodDescriptor(Class<?> rtype, Class<?>... ptypes) {
    return Type.getMethodDescriptor(rtype, ptypes);
  }
  
  public static String typeDescriptor(Class<?> clazz) {
    return Type.typeFor(clazz).getDescriptor();
  }
  
  public static Call constructorNoLookup(Class<?> clazz) {
    return specialCallNoLookup(clazz, INIT.symbolName(), void.class, new Class[0]);
  }
  
  public static Call constructorNoLookup(String className, Class<?>... ptypes) {
    return specialCallNoLookup(className, INIT.symbolName(), methodDescriptor(void.class, ptypes));
  }
  
  public static Call constructorNoLookup(Class<?> clazz, Class<?>... ptypes) {
    return specialCallNoLookup(clazz, INIT.symbolName(), void.class, ptypes);
  }
  
  public static Call specialCallNoLookup(String className, String name, final String desc) {
    return new Call(null, className, name, desc) {
        MethodEmitter invoke(MethodEmitter method) {
          return method.invokespecial(this.className, this.name, this.descriptor);
        }
        
        public void invoke(MethodVisitor mv) {
          mv.visitMethodInsn(183, this.className, this.name, desc, false);
        }
      };
  }
  
  public static Call specialCallNoLookup(Class<?> clazz, String name, Class<?> rtype, Class<?>... ptypes) {
    return specialCallNoLookup(className(clazz), name, methodDescriptor(rtype, ptypes));
  }
  
  public static Call staticCallNoLookup(String className, String name, final String desc) {
    return new Call(null, className, name, desc) {
        MethodEmitter invoke(MethodEmitter method) {
          return method.invokestatic(this.className, this.name, this.descriptor);
        }
        
        public void invoke(MethodVisitor mv) {
          mv.visitMethodInsn(184, this.className, this.name, desc, false);
        }
      };
  }
  
  public static Call staticCallNoLookup(Class<?> clazz, String name, Class<?> rtype, Class<?>... ptypes) {
    return staticCallNoLookup(className(clazz), name, methodDescriptor(rtype, ptypes));
  }
  
  public static Call virtualCallNoLookup(Class<?> clazz, String name, Class<?> rtype, Class<?>... ptypes) {
    return new Call(null, className(clazz), name, methodDescriptor(rtype, ptypes)) {
        MethodEmitter invoke(MethodEmitter method) {
          return method.invokevirtual(this.className, this.name, this.descriptor);
        }
        
        public void invoke(MethodVisitor mv) {
          mv.visitMethodInsn(182, this.className, this.name, this.descriptor, false);
        }
      };
  }
  
  public static Call interfaceCallNoLookup(Class<?> clazz, String name, Class<?> rtype, Class<?>... ptypes) {
    return new Call(null, className(clazz), name, methodDescriptor(rtype, ptypes)) {
        MethodEmitter invoke(MethodEmitter method) {
          return method.invokeinterface(this.className, this.name, this.descriptor);
        }
        
        public void invoke(MethodVisitor mv) {
          mv.visitMethodInsn(185, this.className, this.name, this.descriptor, true);
        }
      };
  }
  
  public static FieldAccess virtualField(String className, String name, String desc) {
    return new FieldAccess(className, name, desc) {
        public MethodEmitter get(MethodEmitter method) {
          return method.getField(this.className, this.name, this.descriptor);
        }
        
        public void put(MethodEmitter method) {
          method.putField(this.className, this.name, this.descriptor);
        }
      };
  }
  
  public static FieldAccess virtualField(Class<?> clazz, String name, Class<?> type) {
    return virtualField(className(clazz), name, typeDescriptor(type));
  }
  
  public static FieldAccess staticField(String className, String name, String desc) {
    return new FieldAccess(className, name, desc) {
        public MethodEmitter get(MethodEmitter method) {
          return method.getStatic(this.className, this.name, this.descriptor);
        }
        
        public void put(MethodEmitter method) {
          method.putStatic(this.className, this.name, this.descriptor);
        }
      };
  }
  
  public static FieldAccess staticField(Class<?> clazz, String name, Class<?> type) {
    return staticField(className(clazz), name, typeDescriptor(type));
  }
  
  public static Call staticCall(MethodHandles.Lookup lookup, Class<?> clazz, String name, Class<?> rtype, Class<?>... ptypes) {
    return new Call(Lookup.MH.findStatic(lookup, clazz, name, Lookup.MH.type(rtype, ptypes)), className(clazz), name, methodDescriptor(rtype, ptypes)) {
        MethodEmitter invoke(MethodEmitter method) {
          return method.invokestatic(this.className, this.name, this.descriptor);
        }
        
        public void invoke(MethodVisitor mv) {
          mv.visitMethodInsn(184, this.className, this.name, this.descriptor, false);
        }
      };
  }
  
  public static Call virtualCall(MethodHandles.Lookup lookup, Class<?> clazz, String name, Class<?> rtype, Class<?>... ptypes) {
    return new Call(Lookup.MH.findVirtual(lookup, clazz, name, Lookup.MH.type(rtype, ptypes)), className(clazz), name, methodDescriptor(rtype, ptypes)) {
        MethodEmitter invoke(MethodEmitter method) {
          return method.invokevirtual(this.className, this.name, this.descriptor);
        }
        
        public void invoke(MethodVisitor mv) {
          mv.visitMethodInsn(182, this.className, this.name, this.descriptor, false);
        }
      };
  }
  
  public static Call specialCall(MethodHandles.Lookup lookup, Class<?> clazz, String name, Class<?> rtype, Class<?>... ptypes) {
    return new Call(Lookup.MH.findSpecial(lookup, clazz, name, Lookup.MH.type(rtype, ptypes), clazz), className(clazz), name, methodDescriptor(rtype, ptypes)) {
        MethodEmitter invoke(MethodEmitter method) {
          return method.invokespecial(this.className, this.name, this.descriptor);
        }
        
        public void invoke(MethodVisitor mv) {
          mv.visitMethodInsn(183, this.className, this.name, this.descriptor, false);
        }
      };
  }
  
  public static boolean isInternalMethodName(String methodName) {
    return (methodName.startsWith(":") && !methodName.equals(PROGRAM.symbolName));
  }
  
  private static abstract class Access {
    protected final MethodHandle methodHandle;
    
    protected final String className;
    
    protected final String name;
    
    protected final String descriptor;
    
    protected Access(MethodHandle methodHandle, String className, String name, String descriptor) {
      this.methodHandle = methodHandle;
      this.className = className;
      this.name = name;
      this.descriptor = descriptor;
    }
    
    public MethodHandle methodHandle() {
      return this.methodHandle;
    }
    
    public String className() {
      return this.className;
    }
    
    public String name() {
      return this.name;
    }
    
    public String descriptor() {
      return this.descriptor;
    }
  }
  
  public static abstract class FieldAccess extends Access {
    protected abstract void put(MethodEmitter param1MethodEmitter);
    
    protected abstract MethodEmitter get(MethodEmitter param1MethodEmitter);
    
    protected FieldAccess(String className, String name, String descriptor) {
      super(null, className, name, descriptor);
    }
  }
  
  public static abstract class Call extends Access {
    protected Call(String className, String name, String descriptor) {
      super(null, className, name, descriptor);
    }
    
    protected Call(MethodHandle methodHandle, String className, String name, String descriptor) {
      super(methodHandle, className, name, descriptor);
    }
    
    abstract MethodEmitter invoke(MethodEmitter param1MethodEmitter);
    
    public abstract void invoke(MethodVisitor param1MethodVisitor);
  }
}
