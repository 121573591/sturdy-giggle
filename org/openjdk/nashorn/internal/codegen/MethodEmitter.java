package org.openjdk.nashorn.internal.codegen;

import java.io.PrintStream;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.EnumSet;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import org.objectweb.asm.Handle;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;
import org.openjdk.nashorn.internal.codegen.types.ArrayType;
import org.openjdk.nashorn.internal.codegen.types.BitwiseType;
import org.openjdk.nashorn.internal.codegen.types.NumericType;
import org.openjdk.nashorn.internal.codegen.types.Type;
import org.openjdk.nashorn.internal.ir.FunctionNode;
import org.openjdk.nashorn.internal.ir.IdentNode;
import org.openjdk.nashorn.internal.ir.JoinPredecessor;
import org.openjdk.nashorn.internal.ir.LiteralNode;
import org.openjdk.nashorn.internal.ir.LocalVariableConversion;
import org.openjdk.nashorn.internal.ir.Symbol;
import org.openjdk.nashorn.internal.ir.TryNode;
import org.openjdk.nashorn.internal.objects.NativeArray;
import org.openjdk.nashorn.internal.runtime.ArgumentSetter;
import org.openjdk.nashorn.internal.runtime.Context;
import org.openjdk.nashorn.internal.runtime.Debug;
import org.openjdk.nashorn.internal.runtime.JSType;
import org.openjdk.nashorn.internal.runtime.RewriteException;
import org.openjdk.nashorn.internal.runtime.Scope;
import org.openjdk.nashorn.internal.runtime.ScriptObject;
import org.openjdk.nashorn.internal.runtime.ScriptRuntime;
import org.openjdk.nashorn.internal.runtime.UnwarrantedOptimismException;
import org.openjdk.nashorn.internal.runtime.linker.Bootstrap;
import org.openjdk.nashorn.internal.runtime.linker.NameCodec;
import org.openjdk.nashorn.internal.runtime.logging.DebugLogger;
import org.openjdk.nashorn.internal.runtime.options.Options;

public class MethodEmitter {
  private final MethodVisitor method;
  
  private final ClassEmitter classEmitter;
  
  protected FunctionNode functionNode;
  
  private Label.Stack stack;
  
  private boolean preventUndefinedLoad;
  
  private final Map<Symbol, LocalVariableDef> localVariableDefs = new IdentityHashMap<>();
  
  private final Context context;
  
  static final int LARGE_STRING_THRESHOLD = 32768;
  
  private final DebugLogger log;
  
  private final boolean debug;
  
  private static final int DEBUG_TRACE_LINE;
  
  static {
    String tl = Options.getStringProperty("nashorn.codegen.debug.trace", "-1");
    int line = -1;
    try {
      line = Integer.parseInt(tl);
    } catch (NumberFormatException numberFormatException) {}
    DEBUG_TRACE_LINE = line;
  }
  
  private static final Handle LINKERBOOTSTRAP = new Handle(6, Bootstrap.BOOTSTRAP.className(), Bootstrap.BOOTSTRAP.name(), Bootstrap.BOOTSTRAP.descriptor(), false);
  
  private static final Handle POPULATE_ARRAY_BOOTSTRAP = new Handle(6, RewriteException.BOOTSTRAP.className(), RewriteException.BOOTSTRAP.name(), RewriteException.BOOTSTRAP.descriptor(), false);
  
  private final CompilerConstants.FieldAccess ERR_STREAM;
  
  private final CompilerConstants.Call PRINT;
  
  private final CompilerConstants.Call PRINTLN;
  
  private final CompilerConstants.Call PRINT_STACKTRACE;
  
  MethodEmitter(ClassEmitter classEmitter, MethodVisitor method) {
    this(classEmitter, method, null);
  }
  
  public void begin() {
    this.classEmitter.beginMethod(this);
    newStack();
    this.method.visitCode();
  }
  
  public void end() {
    this.method.visitMaxs(0, 0);
    this.method.visitEnd();
    this.classEmitter.endMethod(this);
  }
  
  boolean isReachable() {
    return (this.stack != null);
  }
  
  private void doesNotContinueSequentially() {
    this.stack = null;
  }
  
  private void newStack() {
    this.stack = new Label.Stack();
  }
  
  public String toString() {
    return "methodEmitter: " + ((this.functionNode == null) ? this.method : this.functionNode.getName()).toString() + " " + Debug.id(this);
  }
  
  void pushType(Type type) {
    if (type != null)
      this.stack.push(type); 
  }
  
  private Type popType(Type expected) {
    Type type = popType();
    assert type.isEquivalentTo(expected) : "" + type + " is not compatible with " + type;
    return type;
  }
  
  private Type popType() {
    return this.stack.pop();
  }
  
  private NumericType popNumeric() {
    Type type = popType();
    if (type.isBoolean())
      return (NumericType)Type.INT; 
    assert type.isNumeric();
    return (NumericType)type;
  }
  
  private BitwiseType popBitwise() {
    Type type = popType();
    if (type == Type.BOOLEAN)
      return Type.INT; 
    return (BitwiseType)type;
  }
  
  private BitwiseType popInteger() {
    Type type = popType();
    if (type == Type.BOOLEAN)
      return Type.INT; 
    assert type == Type.INT;
    return (BitwiseType)type;
  }
  
  private ArrayType popArray() {
    Type type = popType();
    assert type.isArray() : type;
    return (ArrayType)type;
  }
  
  final Type peekType(int pos) {
    return this.stack.peek(pos);
  }
  
  final Type peekType() {
    return this.stack.peek();
  }
  
  MethodEmitter _new(String classDescriptor, Type type) {
    debug("new", classDescriptor);
    this.method.visitTypeInsn(187, classDescriptor);
    pushType(type);
    return this;
  }
  
  MethodEmitter _new(Class<?> clazz) {
    return _new(CompilerConstants.className(clazz), Type.typeFor(clazz));
  }
  
  MethodEmitter newInstance(Class<?> clazz) {
    return invoke(CompilerConstants.constructorNoLookup(clazz));
  }
  
  MethodEmitter dup(int depth) {
    int l0;
    Type p0;
    int l1;
    Type p1;
    int l2;
    Type p2;
    if (peekType().dup(this.method, depth) == null)
      return null; 
    debug("dup", Integer.valueOf(depth));
    switch (depth) {
      case 0:
        l0 = this.stack.getTopLocalLoad();
        pushType(peekType());
        this.stack.markLocalLoad(l0);
        return this;
      case 1:
        l0 = this.stack.getTopLocalLoad();
        p0 = popType();
        l1 = this.stack.getTopLocalLoad();
        p1 = popType();
        pushType(p0);
        this.stack.markLocalLoad(l0);
        pushType(p1);
        this.stack.markLocalLoad(l1);
        pushType(p0);
        this.stack.markLocalLoad(l0);
        return this;
      case 2:
        l0 = this.stack.getTopLocalLoad();
        p0 = popType();
        l1 = this.stack.getTopLocalLoad();
        p1 = popType();
        l2 = this.stack.getTopLocalLoad();
        p2 = popType();
        pushType(p0);
        this.stack.markLocalLoad(l0);
        pushType(p2);
        this.stack.markLocalLoad(l2);
        pushType(p1);
        this.stack.markLocalLoad(l1);
        pushType(p0);
        this.stack.markLocalLoad(l0);
        return this;
    } 
    assert false : "illegal dup depth = " + depth;
    return null;
  }
  
  MethodEmitter dup2() {
    debug("dup2");
    if (peekType().isCategory2()) {
      int l0 = this.stack.getTopLocalLoad();
      pushType(peekType());
      this.stack.markLocalLoad(l0);
    } else {
      int l0 = this.stack.getTopLocalLoad();
      Type p0 = popType();
      int l1 = this.stack.getTopLocalLoad();
      Type p1 = popType();
      pushType(p0);
      this.stack.markLocalLoad(l0);
      pushType(p1);
      this.stack.markLocalLoad(l1);
      pushType(p0);
      this.stack.markLocalLoad(l0);
      pushType(p1);
      this.stack.markLocalLoad(l1);
    } 
    this.method.visitInsn(92);
    return this;
  }
  
  MethodEmitter dup() {
    return dup(0);
  }
  
  MethodEmitter pop() {
    debug("pop", peekType());
    popType().pop(this.method);
    return this;
  }
  
  MethodEmitter pop2() {
    if (peekType().isCategory2()) {
      popType();
    } else {
      get2n();
    } 
    return this;
  }
  
  MethodEmitter swap() {
    debug("swap");
    int l0 = this.stack.getTopLocalLoad();
    Type p0 = popType();
    int l1 = this.stack.getTopLocalLoad();
    Type p1 = popType();
    p0.swap(this.method, p1);
    pushType(p0);
    this.stack.markLocalLoad(l0);
    pushType(p1);
    this.stack.markLocalLoad(l1);
    return this;
  }
  
  void pack() {
    Type type = peekType();
    if (type.isInteger()) {
      convert(ObjectClassGenerator.PRIMITIVE_FIELD_TYPE);
    } else if (!type.isLong()) {
      if (type.isNumber()) {
        invokestatic("java/lang/Double", "doubleToRawLongBits", "(D)J");
      } else {
        assert false : "" + type + " cannot be packed!";
      } 
    } 
  }
  
  void initializeMethodParameter(Symbol symbol, Type type, Label start) {
    assert symbol.isBytecodeLocal();
    this.localVariableDefs.put(symbol, new LocalVariableDef(start.getLabel(), type));
  }
  
  MethodEmitter newStringBuilder() {
    return invoke(CompilerConstants.constructorNoLookup(StringBuilder.class)).dup();
  }
  
  MethodEmitter stringBuilderAppend() {
    convert(Type.STRING);
    return invoke(CompilerConstants.virtualCallNoLookup(StringBuilder.class, "append", StringBuilder.class, new Class[] { String.class }));
  }
  
  MethodEmitter and() {
    debug("and");
    pushType(get2i().and(this.method));
    return this;
  }
  
  MethodEmitter or() {
    debug("or");
    pushType(get2i().or(this.method));
    return this;
  }
  
  MethodEmitter xor() {
    debug("xor");
    pushType(get2i().xor(this.method));
    return this;
  }
  
  MethodEmitter shr() {
    debug("shr");
    popInteger();
    pushType(popBitwise().shr(this.method));
    return this;
  }
  
  MethodEmitter shl() {
    debug("shl");
    popInteger();
    pushType(popBitwise().shl(this.method));
    return this;
  }
  
  MethodEmitter sar() {
    debug("sar");
    popInteger();
    pushType(popBitwise().sar(this.method));
    return this;
  }
  
  MethodEmitter neg(int programPoint) {
    debug("neg");
    pushType(popNumeric().neg(this.method, programPoint));
    return this;
  }
  
  void _catch(Label recovery) {
    assert this.stack == null;
    recovery.onCatch();
    label(recovery);
    beginCatchBlock();
  }
  
  void _catch(Collection<Label> recoveries) {
    assert this.stack == null;
    for (Label l : recoveries)
      label(l); 
    beginCatchBlock();
  }
  
  private void beginCatchBlock() {
    if (!isReachable())
      newStack(); 
    pushType(Type.typeFor(Throwable.class));
  }
  
  void _try(Label entry, Label exit, Label recovery, Class<?> clazz, boolean isOptimismHandler) {
    recovery.joinFromTry(entry.getStack(), isOptimismHandler);
    String typeDescriptor = (clazz == null) ? null : CompilerConstants.className(clazz);
    this.method.visitTryCatchBlock(entry.getLabel(), exit.getLabel(), recovery.getLabel(), typeDescriptor);
  }
  
  void _try(Label entry, Label exit, Label recovery, Class<?> clazz) {
    _try(entry, exit, recovery, clazz, (clazz == UnwarrantedOptimismException.class));
  }
  
  void _try(Label entry, Label exit, Label recovery) {
    _try(entry, exit, recovery, null, false);
  }
  
  void markLabelAsOptimisticCatchHandler(Label label, int liveLocalCount) {
    label.markAsOptimisticCatchHandler(this.stack, liveLocalCount);
  }
  
  MethodEmitter loadConstants() {
    getStatic(this.classEmitter.getUnitClassName(), CompilerConstants.CONSTANTS.symbolName(), CompilerConstants.CONSTANTS.descriptor());
    assert peekType().isArray() : peekType();
    return this;
  }
  
  MethodEmitter loadUndefined(Type type) {
    debug("load undefined ", type);
    pushType(type.loadUndefined(this.method));
    return this;
  }
  
  MethodEmitter loadForcedInitializer(Type type) {
    debug("load forced initializer ", type);
    pushType(type.loadForcedInitializer(this.method));
    return this;
  }
  
  MethodEmitter loadEmpty(Type type) {
    debug("load empty ", type);
    pushType(type.loadEmpty(this.method));
    return this;
  }
  
  MethodEmitter loadNull() {
    debug("aconst_null");
    pushType(Type.OBJECT.ldc(this.method, null));
    return this;
  }
  
  MethodEmitter loadType(String className) {
    debug("load type", className);
    this.method.visitLdcInsn(Type.getObjectType(className));
    pushType(Type.OBJECT);
    return this;
  }
  
  MethodEmitter load(boolean b) {
    debug("load boolean", Boolean.valueOf(b));
    pushType(Type.BOOLEAN.ldc(this.method, Boolean.valueOf(b)));
    return this;
  }
  
  MethodEmitter load(int i) {
    debug("load int", Integer.valueOf(i));
    pushType(Type.INT.ldc(this.method, Integer.valueOf(i)));
    return this;
  }
  
  MethodEmitter load(double d) {
    debug("load double", Double.valueOf(d));
    pushType(Type.NUMBER.ldc(this.method, Double.valueOf(d)));
    return this;
  }
  
  MethodEmitter load(long l) {
    debug("load long", Long.valueOf(l));
    pushType(Type.LONG.ldc(this.method, Long.valueOf(l)));
    return this;
  }
  
  MethodEmitter arraylength() {
    debug("arraylength");
    popType(Type.OBJECT);
    pushType(Type.OBJECT_ARRAY.arraylength(this.method));
    return this;
  }
  
  MethodEmitter load(String s) {
    debug("load string", s);
    if (s == null) {
      loadNull();
      return this;
    } 
    int length = s.length();
    if (length > 32768) {
      _new(StringBuilder.class);
      dup();
      load(length);
      invoke(CompilerConstants.constructorNoLookup(StringBuilder.class, new Class[] { int.class }));
      for (int n = 0; n < length; n += 32768) {
        String part = s.substring(n, Math.min(n + 32768, length));
        load(part);
        stringBuilderAppend();
      } 
      invoke(CompilerConstants.virtualCallNoLookup(StringBuilder.class, "toString", String.class, new Class[0]));
      return this;
    } 
    pushType(Type.OBJECT.ldc(this.method, s));
    return this;
  }
  
  MethodEmitter load(IdentNode ident) {
    return load(ident.getSymbol(), ident.getType());
  }
  
  MethodEmitter load(Symbol symbol, Type type) {
    assert symbol != null;
    if (symbol.hasSlot()) {
      int slot = symbol.getSlot(type);
      debug("load symbol", symbol.getName(), " slot=", Integer.valueOf(slot), "type=", type);
      load(type, slot);
    } else if (symbol.isParam()) {
      assert this.functionNode.isVarArg() : "Non-vararg functions have slotted parameters";
      int index = symbol.getFieldIndex();
      if (this.functionNode.needsArguments()) {
        debug("load symbol", symbol.getName(), " arguments index=", Integer.valueOf(index));
        loadCompilerConstant(CompilerConstants.ARGUMENTS);
        load(index);
        ScriptObject.GET_ARGUMENT.invoke(this);
      } else {
        debug("load symbol", symbol.getName(), " array index=", Integer.valueOf(index));
        loadCompilerConstant(CompilerConstants.VARARGS);
        load(symbol.getFieldIndex());
        arrayload();
      } 
    } 
    return this;
  }
  
  MethodEmitter load(Type type, int slot) {
    debug("explicit load", type, Integer.valueOf(slot));
    Type loadType = type.load(this.method, slot);
    assert loadType != null;
    pushType((loadType == Type.OBJECT && isThisSlot(slot)) ? Type.THIS : loadType);
    assert !this.preventUndefinedLoad || (slot < this.stack.localVariableTypes.size() && this.stack.localVariableTypes.get(slot) != Type.UNKNOWN) : "Attempted load of uninitialized slot " + slot + " (as type " + type + ")";
    this.stack.markLocalLoad(slot);
    return this;
  }
  
  private boolean isThisSlot(int slot) {
    if (this.functionNode == null)
      return (slot == CompilerConstants.JAVA_THIS.slot()); 
    int thisSlot = getCompilerConstantSymbol(CompilerConstants.THIS).getSlot(Type.OBJECT);
    assert !this.functionNode.needsCallee() || thisSlot == 1;
    assert this.functionNode.needsCallee() || thisSlot == 0;
    return (slot == thisSlot);
  }
  
  MethodEmitter loadHandle(String className, String methodName, String descName, EnumSet<ClassEmitter.Flag> flags) {
    int flag = ClassEmitter.Flag.getValue(flags);
    debug("load handle ");
    pushType(Type.OBJECT.ldc(this.method, new Handle(flag, className, methodName, descName, (flag == 9))));
    return this;
  }
  
  private Symbol getCompilerConstantSymbol(CompilerConstants cc) {
    return this.functionNode.getBody().getExistingSymbol(cc.symbolName());
  }
  
  boolean hasScope() {
    return getCompilerConstantSymbol(CompilerConstants.SCOPE).hasSlot();
  }
  
  MethodEmitter loadCompilerConstant(CompilerConstants cc) {
    return loadCompilerConstant(cc, null);
  }
  
  MethodEmitter loadCompilerConstant(CompilerConstants cc, Type type) {
    if (cc == CompilerConstants.SCOPE && peekType() == Type.SCOPE) {
      dup();
      return this;
    } 
    return load(getCompilerConstantSymbol(cc), (type != null) ? type : getCompilerConstantType(cc));
  }
  
  MethodEmitter loadScope() {
    return loadCompilerConstant(CompilerConstants.SCOPE).checkcast(Scope.class);
  }
  
  MethodEmitter setSplitState(int state) {
    return loadScope().load(state).invoke(Scope.SET_SPLIT_STATE);
  }
  
  void storeCompilerConstant(CompilerConstants cc) {
    storeCompilerConstant(cc, null);
  }
  
  void storeCompilerConstant(CompilerConstants cc, Type type) {
    Symbol symbol = getCompilerConstantSymbol(cc);
    if (!symbol.hasSlot())
      return; 
    debug("store compiler constant ", symbol);
    store(symbol, (type != null) ? type : getCompilerConstantType(cc));
  }
  
  private static Type getCompilerConstantType(CompilerConstants cc) {
    Class<?> constantType = cc.type();
    assert constantType != null;
    return Type.typeFor(constantType);
  }
  
  MethodEmitter arrayload() {
    debug("Xaload");
    popType((Type)Type.INT);
    pushType(popArray().aload(this.method));
    return this;
  }
  
  void arraystore() {
    debug("Xastore");
    Type value = popType();
    Type index = popType((Type)Type.INT);
    assert index.isInteger() : "array index is not integer, but " + index;
    ArrayType array = popArray();
    assert value.isEquivalentTo(array.getElementType()) : "Storing " + value + " into " + array;
    assert array.isObject();
    array.astore(this.method);
  }
  
  void store(IdentNode ident) {
    Type type = ident.getType();
    Symbol symbol = ident.getSymbol();
    if (type == Type.UNDEFINED) {
      assert peekType() == Type.UNDEFINED;
      store(symbol, Type.OBJECT);
    } else {
      store(symbol, type);
    } 
  }
  
  private static class LocalVariableDef {
    private final Label label;
    
    private final Type type;
    
    LocalVariableDef(Label label, Type type) {
      this.label = label;
      this.type = type;
    }
  }
  
  void closeLocalVariable(Symbol symbol, Label label) {
    LocalVariableDef def = this.localVariableDefs.get(symbol);
    if (def != null)
      endLocalValueDef(symbol, def, label.getLabel()); 
    if (isReachable())
      markDeadLocalVariable(symbol); 
  }
  
  void markDeadLocalVariable(Symbol symbol) {
    if (!symbol.isDead())
      markDeadSlots(symbol.getFirstSlot(), symbol.slotCount()); 
  }
  
  void markDeadSlots(int firstSlot, int slotCount) {
    this.stack.markDeadLocalVariables(firstSlot, slotCount);
  }
  
  private void endLocalValueDef(Symbol symbol, LocalVariableDef def, Label label) {
    String name = symbol.getName();
    if (name.equals(CompilerConstants.THIS.symbolName()))
      name = CompilerConstants.THIS_DEBUGGER.symbolName(); 
    this.method.visitLocalVariable(name, def.type.getDescriptor(), null, def.label, label, symbol.getSlot(def.type));
  }
  
  void store(Symbol symbol, Type type) {
    store(symbol, type, true);
  }
  
  void store(Symbol symbol, Type type, boolean onlySymbolLiveValue) {
    assert symbol != null : "No symbol to store";
    if (symbol.hasSlot()) {
      boolean isLiveType = symbol.hasSlotFor(type);
      LocalVariableDef existingDef = this.localVariableDefs.get(symbol);
      if (existingDef == null || existingDef.type != type) {
        Label here = new Label();
        if (isLiveType) {
          LocalVariableDef newDef = new LocalVariableDef(here, type);
          this.localVariableDefs.put(symbol, newDef);
        } 
        this.method.visitLabel(here);
        if (existingDef != null)
          endLocalValueDef(symbol, existingDef, here); 
      } 
      if (isLiveType) {
        int slot = symbol.getSlot(type);
        debug("store symbol", symbol.getName(), " type=", type, " slot=", Integer.valueOf(slot));
        storeHidden(type, slot, onlySymbolLiveValue);
      } else {
        if (onlySymbolLiveValue)
          markDeadLocalVariable(symbol); 
        debug("dead store symbol ", symbol.getName(), " type=", type);
        pop();
      } 
    } else if (symbol.isParam()) {
      assert !symbol.isScope();
      assert this.functionNode.isVarArg() : "Non-vararg functions have slotted parameters";
      int index = symbol.getFieldIndex();
      if (this.functionNode.needsArguments()) {
        convert(Type.OBJECT);
        debug("store symbol", symbol.getName(), " arguments index=", Integer.valueOf(index));
        loadCompilerConstant(CompilerConstants.ARGUMENTS);
        load(index);
        ArgumentSetter.SET_ARGUMENT.invoke(this);
      } else {
        convert(Type.OBJECT);
        debug("store symbol", symbol.getName(), " array index=", Integer.valueOf(index));
        loadCompilerConstant(CompilerConstants.VARARGS);
        load(index);
        ArgumentSetter.SET_ARRAY_ELEMENT.invoke(this);
      } 
    } else {
      debug("dead store symbol ", symbol.getName(), " type=", type);
      pop();
    } 
  }
  
  void storeHidden(Type type, int slot) {
    storeHidden(type, slot, true);
  }
  
  void storeHidden(Type type, int slot, boolean onlyLiveSymbolValue) {
    explicitStore(type, slot);
    this.stack.onLocalStore(type, slot, onlyLiveSymbolValue);
  }
  
  void storeTemp(Type type, int slot) {
    explicitStore(type, slot);
    defineTemporaryLocalVariable(slot, slot + type.getSlots());
    onLocalStore(type, slot);
  }
  
  void onLocalStore(Type type, int slot) {
    this.stack.onLocalStore(type, slot, true);
  }
  
  private void explicitStore(Type type, int slot) {
    assert slot != -1;
    debug("explicit store", type, Integer.valueOf(slot));
    popType(type);
    type.store(this.method, slot);
  }
  
  void defineBlockLocalVariable(int fromSlot, int toSlot) {
    this.stack.defineBlockLocalVariable(fromSlot, toSlot);
  }
  
  void defineTemporaryLocalVariable(int fromSlot, int toSlot) {
    this.stack.defineTemporaryLocalVariable(fromSlot, toSlot);
  }
  
  int defineTemporaryLocalVariable(int width) {
    return this.stack.defineTemporaryLocalVariable(width);
  }
  
  void undefineLocalVariables(int fromSlot, boolean canTruncateSymbol) {
    if (isReachable())
      this.stack.undefineLocalVariables(fromSlot, canTruncateSymbol); 
  }
  
  List<Type> getLocalVariableTypes() {
    return this.stack.localVariableTypes;
  }
  
  List<Type> getWidestLiveLocals(List<Type> localTypes) {
    return this.stack.getWidestLiveLocals(localTypes);
  }
  
  String markSymbolBoundariesInLvarTypesDescriptor(String lvarDescriptor) {
    return this.stack.markSymbolBoundariesInLvarTypesDescriptor(lvarDescriptor);
  }
  
  void iinc(int slot, int increment) {
    debug("iinc");
    this.method.visitIincInsn(slot, increment);
  }
  
  public void athrow() {
    debug("athrow");
    Type receiver = popType(Type.OBJECT);
    assert Throwable.class.isAssignableFrom(receiver.getTypeClass()) : receiver.getTypeClass();
    this.method.visitInsn(191);
    doesNotContinueSequentially();
  }
  
  MethodEmitter _instanceof(String classDescriptor) {
    debug("instanceof", classDescriptor);
    popType(Type.OBJECT);
    this.method.visitTypeInsn(193, classDescriptor);
    pushType((Type)Type.INT);
    return this;
  }
  
  MethodEmitter _instanceof(Class<?> clazz) {
    return _instanceof(CompilerConstants.className(clazz));
  }
  
  MethodEmitter checkcast(String classDescriptor) {
    debug("checkcast", classDescriptor);
    assert peekType().isObject();
    this.method.visitTypeInsn(192, classDescriptor);
    return this;
  }
  
  MethodEmitter checkcast(Class<?> clazz) {
    return checkcast(CompilerConstants.className(clazz));
  }
  
  MethodEmitter newarray(ArrayType arrayType) {
    debug("newarray ", "arrayType=", arrayType);
    popType((Type)Type.INT);
    pushType(arrayType.newarray(this.method));
    return this;
  }
  
  MethodEmitter multinewarray(ArrayType arrayType, int dims) {
    debug("multianewarray ", arrayType, Integer.valueOf(dims));
    for (int i = 0; i < dims; i++)
      popType((Type)Type.INT); 
    pushType(arrayType.newarray(this.method, dims));
    return this;
  }
  
  private Type fixParamStack(String signature) {
    Type[] params = Type.getMethodArguments(signature);
    for (int i = params.length - 1; i >= 0; i--)
      popType(params[i]); 
    Type returnType = Type.getMethodReturnType(signature);
    return returnType;
  }
  
  MethodEmitter invoke(CompilerConstants.Call call) {
    return call.invoke(this);
  }
  
  private MethodEmitter invoke(int opcode, String className, String methodName, String methodDescriptor, boolean hasReceiver) {
    Type returnType = fixParamStack(methodDescriptor);
    if (hasReceiver)
      popType(Type.OBJECT); 
    this.method.visitMethodInsn(opcode, className, methodName, methodDescriptor, (opcode == 185));
    if (returnType != null)
      pushType(returnType); 
    return this;
  }
  
  MethodEmitter invokespecial(String className, String methodName, String methodDescriptor) {
    debug("invokespecial", className, ".", methodName, methodDescriptor);
    return invoke(183, className, methodName, methodDescriptor, true);
  }
  
  MethodEmitter invokevirtual(String className, String methodName, String methodDescriptor) {
    debug("invokevirtual", className, ".", methodName, methodDescriptor, " ", this.stack);
    return invoke(182, className, methodName, methodDescriptor, true);
  }
  
  MethodEmitter invokestatic(String className, String methodName, String methodDescriptor) {
    debug("invokestatic", className, ".", methodName, methodDescriptor);
    invoke(184, className, methodName, methodDescriptor, false);
    return this;
  }
  
  MethodEmitter invokestatic(String className, String methodName, String methodDescriptor, Type returnType) {
    invokestatic(className, methodName, methodDescriptor);
    popType();
    pushType(returnType);
    return this;
  }
  
  MethodEmitter invokeinterface(String className, String methodName, String methodDescriptor) {
    debug("invokeinterface", className, ".", methodName, methodDescriptor);
    return invoke(185, className, methodName, methodDescriptor, true);
  }
  
  static Label[] getLabels(Label... table) {
    Label[] internalLabels = new Label[table.length];
    for (int i = 0; i < table.length; i++)
      internalLabels[i] = table[i].getLabel(); 
    return internalLabels;
  }
  
  void lookupswitch(Label defaultLabel, int[] values, Label... table) {
    debug("lookupswitch", peekType());
    adjustStackForSwitch(defaultLabel, table);
    this.method.visitLookupSwitchInsn(defaultLabel.getLabel(), values, getLabels(table));
    doesNotContinueSequentially();
  }
  
  void tableswitch(int lo, int hi, Label defaultLabel, Label... table) {
    debug("tableswitch", peekType());
    adjustStackForSwitch(defaultLabel, table);
    this.method.visitTableSwitchInsn(lo, hi, defaultLabel.getLabel(), getLabels(table));
    doesNotContinueSequentially();
  }
  
  private void adjustStackForSwitch(Label defaultLabel, Label... table) {
    popType((Type)Type.INT);
    joinTo(defaultLabel);
    for (Label label : table)
      joinTo(label); 
  }
  
  void conditionalJump(Condition cond, Label trueLabel) {
    conditionalJump(cond, (cond != Condition.GT && cond != Condition.GE), trueLabel);
  }
  
  void conditionalJump(Condition cond, boolean isCmpG, Label trueLabel) {
    if (peekType().isCategory2()) {
      debug("[ld]cmp isCmpG=", Boolean.valueOf(isCmpG));
      pushType(get2n().cmp(this.method, isCmpG));
      jump(Condition.toUnary(cond), trueLabel, 1);
    } else {
      debug("if", cond);
      jump(Condition.toBinary(cond, peekType().isObject()), trueLabel, 2);
    } 
  }
  
  void _return(Type type) {
    debug("return", type);
    assert this.stack.size() == 1 : "Only return value on stack allowed at return point - depth=" + this.stack.size() + " stack = " + this.stack;
    Type stackType = peekType();
    if (!Type.areEquivalent(type, stackType))
      convert(type); 
    popType(type)._return(this.method);
    doesNotContinueSequentially();
  }
  
  void _return() {
    _return(peekType());
  }
  
  void returnVoid() {
    debug("return [void]");
    assert this.stack.isEmpty() : this.stack;
    this.method.visitInsn(177);
    doesNotContinueSequentially();
  }
  
  MethodEmitter cmp(boolean isCmpG) {
    pushType(get2n().cmp(this.method, isCmpG));
    return this;
  }
  
  private void jump(int opcode, Label label, int n) {
    for (int i = 0; i < n; i++) {
      assert peekType().isInteger() || peekType().isBoolean() || peekType().isObject() : "expecting integer type or object for jump, but found " + peekType();
      popType();
    } 
    joinTo(label);
    this.method.visitJumpInsn(opcode, label.getLabel());
  }
  
  void if_acmpeq(Label label) {
    debug("if_acmpeq", label);
    jump(165, label, 2);
  }
  
  void if_acmpne(Label label) {
    debug("if_acmpne", label);
    jump(166, label, 2);
  }
  
  void ifnull(Label label) {
    debug("ifnull", label);
    jump(198, label, 1);
  }
  
  void ifnonnull(Label label) {
    debug("ifnonnull", label);
    jump(199, label, 1);
  }
  
  void ifeq(Label label) {
    debug("ifeq ", label);
    jump(153, label, 1);
  }
  
  void if_icmpeq(Label label) {
    debug("if_icmpeq", label);
    jump(159, label, 2);
  }
  
  void ifne(Label label) {
    debug("ifne", label);
    jump(154, label, 1);
  }
  
  void if_icmpne(Label label) {
    debug("if_icmpne", label);
    jump(160, label, 2);
  }
  
  void iflt(Label label) {
    debug("iflt", label);
    jump(155, label, 1);
  }
  
  void if_icmplt(Label label) {
    debug("if_icmplt", label);
    jump(161, label, 2);
  }
  
  void ifle(Label label) {
    debug("ifle", label);
    jump(158, label, 1);
  }
  
  void if_icmple(Label label) {
    debug("if_icmple", label);
    jump(164, label, 2);
  }
  
  void ifgt(Label label) {
    debug("ifgt", label);
    jump(157, label, 1);
  }
  
  void if_icmpgt(Label label) {
    debug("if_icmpgt", label);
    jump(163, label, 2);
  }
  
  void ifge(Label label) {
    debug("ifge", label);
    jump(156, label, 1);
  }
  
  void if_icmpge(Label label) {
    debug("if_icmpge", label);
    jump(162, label, 2);
  }
  
  void _goto(Label label) {
    debug("goto", label);
    jump(167, label, 0);
    doesNotContinueSequentially();
  }
  
  void gotoLoopStart(Label loopStart) {
    debug("goto (loop)", loopStart);
    jump(167, loopStart, 0);
  }
  
  void uncheckedGoto(Label target) {
    this.method.visitJumpInsn(167, target.getLabel());
  }
  
  void canThrow(Label catchLabel) {
    catchLabel.joinFromTry(this.stack, false);
  }
  
  private void joinTo(Label label) {
    assert isReachable();
    label.joinFrom(this.stack);
  }
  
  void label(Label label) {
    breakLabel(label, -1);
  }
  
  void breakLabel(Label label, int liveLocals) {
    if (!isReachable()) {
      assert ((label.getStack() == null)) != label.isReachable();
    } else {
      joinTo(label);
    } 
    Label.Stack labelStack = label.getStack();
    this.stack = (labelStack == null) ? null : labelStack.clone();
    if (this.stack != null && label.isBreakTarget() && liveLocals != -1) {
      assert this.stack.firstTemp >= liveLocals;
      this.stack.firstTemp = liveLocals;
    } 
    debug_label(new Object[] { label });
    this.method.visitLabel(label.getLabel());
  }
  
  MethodEmitter convert(Type to) {
    Type from = peekType();
    Type type = from.convert(this.method, to);
    if (type != null) {
      if (!from.isEquivalentTo(to))
        debug("convert", from, "->", to); 
      if (type != from) {
        int l0 = this.stack.getTopLocalLoad();
        popType();
        pushType(type);
        if (!from.isObject())
          this.stack.markLocalLoad(l0); 
      } 
    } 
    return this;
  }
  
  private Type get2() {
    Type p0 = popType();
    Type p1 = popType();
    assert p0.isEquivalentTo(p1) : "expecting equivalent types on stack but got " + p0 + " and " + p1;
    return p0;
  }
  
  private BitwiseType get2i() {
    BitwiseType p0 = popBitwise();
    BitwiseType p1 = popBitwise();
    assert p0.isEquivalentTo((Type)p1) : "expecting equivalent types on stack but got " + p0 + " and " + p1;
    return p0;
  }
  
  private NumericType get2n() {
    NumericType p0 = popNumeric();
    NumericType p1 = popNumeric();
    assert p0.isEquivalentTo((Type)p1) : "expecting equivalent types on stack but got " + p0 + " and " + p1;
    return p0;
  }
  
  MethodEmitter add(int programPoint) {
    debug("add");
    pushType(get2().add(this.method, programPoint));
    return this;
  }
  
  MethodEmitter sub(int programPoint) {
    debug("sub");
    pushType(get2n().sub(this.method, programPoint));
    return this;
  }
  
  MethodEmitter mul(int programPoint) {
    debug("mul ");
    pushType(get2n().mul(this.method, programPoint));
    return this;
  }
  
  MethodEmitter div(int programPoint) {
    debug("div");
    pushType(get2n().div(this.method, programPoint));
    return this;
  }
  
  MethodEmitter rem(int programPoint) {
    debug("rem");
    pushType(get2n().rem(this.method, programPoint));
    return this;
  }
  
  protected Type[] getTypesFromStack(int count) {
    return this.stack.getTopTypes(count);
  }
  
  int[] getLocalLoadsOnStack(int from, int to) {
    return this.stack.getLocalLoads(from, to);
  }
  
  int getStackSize() {
    return this.stack.size();
  }
  
  int getFirstTemp() {
    return this.stack.firstTemp;
  }
  
  int getUsedSlotsWithLiveTemporaries() {
    return this.stack.getUsedSlotsWithLiveTemporaries();
  }
  
  private String getDynamicSignature(Type returnType, int argCount) {
    Type[] paramTypes = new Type[argCount];
    int pos = 0;
    for (int i = argCount - 1; i >= 0; i--) {
      Type pt = this.stack.peek(pos++);
      if (ScriptObject.class.isAssignableFrom(pt.getTypeClass()) && 
        !NativeArray.class.isAssignableFrom(pt.getTypeClass()))
        pt = Type.SCRIPT_OBJECT; 
      paramTypes[i] = pt;
    } 
    String descriptor = Type.getMethodDescriptor(returnType, paramTypes);
    for (int j = 0; j < argCount; j++)
      popType(paramTypes[argCount - j - 1]); 
    return descriptor;
  }
  
  MethodEmitter invalidateSpecialName(String name) {
    switch (name) {
      case "apply":
      case "call":
        debug("invalidate_name", "name=", name);
        load("Function");
        invoke(ScriptRuntime.INVALIDATE_RESERVED_BUILTIN_NAME);
        break;
    } 
    return this;
  }
  
  MethodEmitter dynamicNew(int argCount, int flags) {
    return dynamicNew(argCount, flags, null);
  }
  
  MethodEmitter dynamicNew(int argCount, int flags, String msg) {
    assert !isOptimistic(flags);
    debug("dynamic_new", "argcount=", Integer.valueOf(argCount));
    String signature = getDynamicSignature(Type.OBJECT, argCount);
    this.method.visitInvokeDynamicInsn((
        msg != null && msg.length() < 32768) ? NameCodec.encode(msg) : NameCodec.EMPTY_NAME, signature, LINKERBOOTSTRAP, new Object[] { Integer.valueOf(flags | 0x9) });
    pushType(Type.OBJECT);
    return this;
  }
  
  MethodEmitter dynamicCall(Type returnType, int argCount, int flags) {
    return dynamicCall(returnType, argCount, flags, null);
  }
  
  MethodEmitter dynamicCall(Type returnType, int argCount, int flags, String msg) {
    debug("dynamic_call", "args=", Integer.valueOf(argCount), "returnType=", returnType);
    String signature = getDynamicSignature(returnType, argCount);
    debug("   signature", signature);
    this.method.visitInvokeDynamicInsn((
        msg != null && msg.length() < 32768) ? NameCodec.encode(msg) : NameCodec.EMPTY_NAME, signature, LINKERBOOTSTRAP, new Object[] { Integer.valueOf(flags | 0x8) });
    pushType(returnType);
    return this;
  }
  
  MethodEmitter dynamicArrayPopulatorCall(int argCount, int startIndex) {
    debug("populate_array", "args=", Integer.valueOf(argCount), "startIndex=", Integer.valueOf(startIndex));
    String signature = getDynamicSignature((Type)Type.OBJECT_ARRAY, argCount);
    this.method.visitInvokeDynamicInsn("populateArray", signature, POPULATE_ARRAY_BOOTSTRAP, new Object[] { Integer.valueOf(startIndex) });
    pushType((Type)Type.OBJECT_ARRAY);
    return this;
  }
  
  MethodEmitter dynamicGet(Type valueType, String name, int flags, boolean isMethod, boolean isIndex) {
    if (name.length() > 32768)
      return load(name).dynamicGetIndex(valueType, flags, isMethod); 
    debug("dynamic_get", name, valueType, getProgramPoint(flags));
    Type type = valueType;
    if (type.isObject() || type.isBoolean())
      type = Type.OBJECT; 
    popType(Type.OBJECT);
    this.method.visitInvokeDynamicInsn(NameCodec.encode(name), 
        Type.getMethodDescriptor(type, new Type[] { Type.OBJECT }), LINKERBOOTSTRAP, new Object[] { Integer.valueOf(flags | dynGetOperation(isMethod, isIndex)) });
    pushType(type);
    convert(valueType);
    return this;
  }
  
  void dynamicSet(String name, int flags, boolean isIndex) {
    if (name.length() > 32768) {
      load(name).swap().dynamicSetIndex(flags);
      return;
    } 
    assert !isOptimistic(flags);
    debug("dynamic_set", name, peekType());
    Type type = peekType();
    if (type.isObject() || type.isBoolean()) {
      type = Type.OBJECT;
      convert(Type.OBJECT);
    } 
    popType(type);
    popType(Type.OBJECT);
    this.method.visitInvokeDynamicInsn(NameCodec.encode(name), 
        CompilerConstants.methodDescriptor(void.class, new Class[] { Object.class, type.getTypeClass() }), LINKERBOOTSTRAP, new Object[] { Integer.valueOf(flags | dynSetOperation(isIndex)) });
  }
  
  MethodEmitter dynamicRemove(String name, int flags, boolean isIndex) {
    if (name.length() > 32768)
      return load(name).dynamicRemoveIndex(flags); 
    debug("dynamic_remove", name, Type.BOOLEAN, getProgramPoint(flags));
    popType(Type.OBJECT);
    this.method.visitInvokeDynamicInsn(NameCodec.encode(name), 
        Type.getMethodDescriptor(Type.OBJECT, new Type[] { Type.OBJECT }), LINKERBOOTSTRAP, new Object[] { Integer.valueOf(flags | dynRemoveOperation(isIndex)) });
    pushType(Type.OBJECT);
    convert(Type.BOOLEAN);
    return this;
  }
  
  MethodEmitter dynamicGetIndex(Type result, int flags, boolean isMethod) {
    assert result.getTypeClass().isPrimitive() || result.getTypeClass() == Object.class;
    debug("dynamic_get_index", peekType(1), "[", peekType(), "]", getProgramPoint(flags));
    Type resultType = result;
    if (result.isBoolean())
      resultType = Type.OBJECT; 
    Type index = peekType();
    if (index.isObject() || index.isBoolean()) {
      index = Type.OBJECT;
      convert(Type.OBJECT);
    } 
    popType();
    popType(Type.OBJECT);
    String signature = Type.getMethodDescriptor(resultType, new Type[] { Type.OBJECT, index });
    this.method.visitInvokeDynamicInsn(NameCodec.EMPTY_NAME, signature, LINKERBOOTSTRAP, new Object[] { Integer.valueOf(flags | dynGetOperation(isMethod, true)) });
    pushType(resultType);
    if (result.isBoolean())
      convert(Type.BOOLEAN); 
    return this;
  }
  
  private static String getProgramPoint(int flags) {
    if ((flags & 0x80) == 0)
      return ""; 
    return "pp=" + String.valueOf((flags & 0xFFFF8000) >> 15);
  }
  
  void dynamicSetIndex(int flags) {
    assert !isOptimistic(flags);
    debug("dynamic_set_index", peekType(2), "[", peekType(1), "] =", peekType());
    Type value = peekType();
    if (value.isObject() || value.isBoolean()) {
      value = Type.OBJECT;
      convert(Type.OBJECT);
    } 
    popType();
    Type index = peekType();
    if (index.isObject() || index.isBoolean()) {
      index = Type.OBJECT;
      convert(Type.OBJECT);
    } 
    popType(index);
    Type receiver = popType(Type.OBJECT);
    assert receiver.isObject();
    this.method.visitInvokeDynamicInsn(NameCodec.EMPTY_NAME, 
        CompilerConstants.methodDescriptor(void.class, new Class[] { receiver.getTypeClass(), index.getTypeClass(), value.getTypeClass() }), LINKERBOOTSTRAP, new Object[] { Integer.valueOf(flags | 0x5) });
  }
  
  MethodEmitter dynamicRemoveIndex(int flags) {
    debug("dynamic_remove_index", peekType(1), "[", peekType(), "]", getProgramPoint(flags));
    Type index = peekType();
    if (index.isObject() || index.isBoolean()) {
      index = Type.OBJECT;
      convert(Type.OBJECT);
    } 
    popType();
    popType(Type.OBJECT);
    String signature = Type.getMethodDescriptor(Type.OBJECT, new Type[] { Type.OBJECT, index });
    this.method.visitInvokeDynamicInsn(NameCodec.EMPTY_NAME, signature, LINKERBOOTSTRAP, new Object[] { Integer.valueOf(flags | dynRemoveOperation(true)) });
    pushType(Type.OBJECT);
    convert(Type.BOOLEAN);
    return this;
  }
  
  MethodEmitter loadKey(Object key) {
    if (key instanceof IdentNode) {
      this.method.visitLdcInsn(((IdentNode)key).getName());
    } else if (key instanceof LiteralNode) {
      this.method.visitLdcInsn(((LiteralNode)key).getString());
    } else {
      this.method.visitLdcInsn(JSType.toString(key));
    } 
    pushType(Type.OBJECT);
    return this;
  }
  
  private static Type fieldType(String desc) {
    switch (desc) {
      case "Z":
      case "B":
      case "C":
      case "S":
      case "I":
        return (Type)Type.INT;
      case "F":
        assert false;
      case "D":
        return (Type)Type.NUMBER;
      case "J":
        return Type.LONG;
    } 
    assert desc.startsWith("[") || desc.startsWith("L") : desc + " is not an object type";
    switch (desc.charAt(0)) {
      case 'L':
        return Type.OBJECT;
      case '[':
        return Type.typeFor(Array.newInstance(fieldType(desc.substring(1)).getTypeClass(), 0).getClass());
    } 
    assert false;
    return Type.OBJECT;
  }
  
  MethodEmitter getField(CompilerConstants.FieldAccess fa) {
    return fa.get(this);
  }
  
  void putField(CompilerConstants.FieldAccess fa) {
    fa.put(this);
  }
  
  MethodEmitter getField(String className, String fieldName, String fieldDescriptor) {
    debug("getfield", "receiver=", peekType(), className, ".", fieldName, fieldDescriptor);
    Type receiver = popType();
    assert receiver.isObject();
    this.method.visitFieldInsn(180, className, fieldName, fieldDescriptor);
    pushType(fieldType(fieldDescriptor));
    return this;
  }
  
  MethodEmitter getStatic(String className, String fieldName, String fieldDescriptor) {
    debug("getstatic", className, ".", fieldName, ".", fieldDescriptor);
    this.method.visitFieldInsn(178, className, fieldName, fieldDescriptor);
    pushType(fieldType(fieldDescriptor));
    return this;
  }
  
  void putField(String className, String fieldName, String fieldDescriptor) {
    debug("putfield", "receiver=", peekType(1), "value=", peekType());
    popType(fieldType(fieldDescriptor));
    popType(Type.OBJECT);
    this.method.visitFieldInsn(181, className, fieldName, fieldDescriptor);
  }
  
  void putStatic(String className, String fieldName, String fieldDescriptor) {
    debug("putfield", "value=", peekType());
    popType(fieldType(fieldDescriptor));
    this.method.visitFieldInsn(179, className, fieldName, fieldDescriptor);
  }
  
  void lineNumber(int line) {
    if ((this.context.getEnv())._debug_lines) {
      debug_label(new Object[] { "[LINE]", Integer.valueOf(line) });
      Label l = new Label();
      this.method.visitLabel(l);
      this.method.visitLineNumber(line, l);
    } 
  }
  
  void beforeJoinPoint(JoinPredecessor joinPredecessor) {
    LocalVariableConversion next = joinPredecessor.getLocalVariableConversion();
    while (next != null) {
      Symbol symbol = next.getSymbol();
      if (next.isLive()) {
        emitLocalVariableConversion(next, true);
      } else {
        markDeadLocalVariable(symbol);
      } 
      next = next.getNext();
    } 
  }
  
  void beforeTry(TryNode tryNode, Label recovery) {
    LocalVariableConversion next = tryNode.getLocalVariableConversion();
    while (next != null) {
      if (next.isLive()) {
        Type to = emitLocalVariableConversion(next, false);
        recovery.getStack().onLocalStore(to, next.getSymbol().getSlot(to), true);
      } 
      next = next.getNext();
    } 
  }
  
  private static int dynGetOperation(boolean isMethod, boolean isIndex) {
    if (isMethod)
      return isIndex ? 3 : 2; 
    return isIndex ? 1 : 0;
  }
  
  private static int dynSetOperation(boolean isIndex) {
    return isIndex ? 5 : 4;
  }
  
  private static int dynRemoveOperation(boolean isIndex) {
    return isIndex ? 7 : 6;
  }
  
  private Type emitLocalVariableConversion(LocalVariableConversion conversion, boolean onlySymbolLiveValue) {
    Type from = conversion.getFrom();
    Type to = conversion.getTo();
    Symbol symbol = conversion.getSymbol();
    assert symbol.isBytecodeLocal();
    if (from == Type.UNDEFINED) {
      loadUndefined(to);
    } else {
      load(symbol, from).convert(to);
    } 
    store(symbol, to, onlySymbolLiveValue);
    return to;
  }
  
  MethodEmitter(ClassEmitter classEmitter, MethodVisitor method, FunctionNode functionNode) {
    this.ERR_STREAM = CompilerConstants.staticField(System.class, "err", PrintStream.class);
    this.PRINT = CompilerConstants.virtualCallNoLookup(PrintStream.class, "print", void.class, new Class[] { Object.class });
    this.PRINTLN = CompilerConstants.virtualCallNoLookup(PrintStream.class, "println", void.class, new Class[] { Object.class });
    this.PRINT_STACKTRACE = CompilerConstants.virtualCallNoLookup(Throwable.class, "printStackTrace", void.class, new Class[0]);
    this.context = classEmitter.getContext();
    this.classEmitter = classEmitter;
    this.method = method;
    this.functionNode = functionNode;
    this.stack = null;
    this.log = this.context.getLogger(CodeGenerator.class);
    this.debug = this.log.isEnabled();
  }
  
  void print() {
    getField(this.ERR_STREAM);
    swap();
    convert(Type.OBJECT);
    invoke(this.PRINT);
  }
  
  void println() {
    getField(this.ERR_STREAM);
    swap();
    convert(Type.OBJECT);
    invoke(this.PRINTLN);
  }
  
  void print(String string) {
    getField(this.ERR_STREAM);
    load(string);
    invoke(this.PRINT);
  }
  
  void println(String string) {
    getField(this.ERR_STREAM);
    load(string);
    invoke(this.PRINTLN);
  }
  
  void stacktrace() {
    _new(Throwable.class);
    dup();
    invoke(CompilerConstants.constructorNoLookup(Throwable.class));
    invoke(this.PRINT_STACKTRACE);
  }
  
  private static int linePrefix = 0;
  
  private void debug(Object... args) {
    if (this.debug)
      debug(30, args); 
  }
  
  private void debug(String arg) {
    if (this.debug)
      debug(Integer.valueOf(30), arg); 
  }
  
  private void debug(Object arg0, Object arg1) {
    if (this.debug)
      debug(30, new Object[] { arg0, arg1 }); 
  }
  
  private void debug(Object arg0, Object arg1, Object arg2) {
    if (this.debug)
      debug(30, new Object[] { arg0, arg1, arg2 }); 
  }
  
  private void debug(Object arg0, Object arg1, Object arg2, Object arg3) {
    if (this.debug)
      debug(30, new Object[] { arg0, arg1, arg2, arg3 }); 
  }
  
  private void debug(Object arg0, Object arg1, Object arg2, Object arg3, Object arg4) {
    if (this.debug)
      debug(30, new Object[] { arg0, arg1, arg2, arg3, arg4 }); 
  }
  
  private void debug(Object arg0, Object arg1, Object arg2, Object arg3, Object arg4, Object arg5) {
    if (this.debug)
      debug(30, new Object[] { arg0, arg1, arg2, arg3, arg4, arg5 }); 
  }
  
  private void debug(Object arg0, Object arg1, Object arg2, Object arg3, Object arg4, Object arg5, Object arg6) {
    if (this.debug)
      debug(30, new Object[] { arg0, arg1, arg2, arg3, arg4, arg5, arg6 }); 
  }
  
  private void debug_label(Object... args) {
    if (this.debug)
      debug(22, args); 
  }
  
  private void debug(int padConstant, Object... args) {
    if (this.debug) {
      StringBuilder sb = new StringBuilder();
      sb.append('#');
      sb.append(++linePrefix);
      int pad = 5 - sb.length();
      while (pad > 0) {
        sb.append(' ');
        pad--;
      } 
      if (isReachable() && !this.stack.isEmpty()) {
        sb.append("{");
        sb.append(this.stack.size());
        sb.append(":");
        for (int pos = 0; pos < this.stack.size(); pos++) {
          Type t = this.stack.peek(pos);
          if (t == Type.SCOPE) {
            sb.append("scope");
          } else if (t == Type.THIS) {
            sb.append("this");
          } else if (t.isObject()) {
            String desc = t.getDescriptor();
            int i;
            for (i = 0; desc.charAt(i) == '[' && i < desc.length(); i++)
              sb.append('['); 
            desc = desc.substring(i);
            int slash = desc.lastIndexOf('/');
            if (slash != -1)
              desc = desc.substring(slash + 1, desc.length() - 1); 
            if ("Object".equals(desc)) {
              sb.append('O');
            } else {
              sb.append(desc);
            } 
          } else {
            sb.append(t.getDescriptor());
          } 
          int loadIndex = this.stack.localLoads[this.stack.sp - 1 - pos];
          if (loadIndex != -1)
            sb.append('(').append(loadIndex).append(')'); 
          if (pos + 1 < this.stack.size())
            sb.append(' '); 
        } 
        sb.append('}');
        sb.append(' ');
      } 
      pad = padConstant - sb.length();
      while (pad > 0) {
        sb.append(' ');
        pad--;
      } 
      for (Object arg : args) {
        sb.append(arg);
        sb.append(' ');
      } 
      if (this.context.getEnv() != null) {
        this.log.info(new Object[] { sb });
        if (DEBUG_TRACE_LINE == linePrefix)
          (new Throwable()).printStackTrace(this.log.getOutputStream()); 
      } 
    } 
  }
  
  void setFunctionNode(FunctionNode functionNode) {
    this.functionNode = functionNode;
  }
  
  void setPreventUndefinedLoad() {
    this.preventUndefinedLoad = true;
  }
  
  private static boolean isOptimistic(int flags) {
    return ((flags & 0x80) != 0);
  }
}
