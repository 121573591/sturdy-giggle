package org.openjdk.nashorn.internal.codegen;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import org.openjdk.nashorn.internal.codegen.types.Type;

public final class Label implements Serializable {
  private static final long serialVersionUID = 1L;
  
  static final class Stack implements Cloneable {
    static final int NON_LOAD = -1;
    
    Type[] data = new Type[8];
    
    int[] localLoads = new int[8];
    
    int sp;
    
    List<Type> localVariableTypes = new ArrayList<>(8);
    
    int firstTemp;
    
    BitSet symbolBoundary = new BitSet();
    
    boolean isEmpty() {
      return (this.sp == 0);
    }
    
    int size() {
      return this.sp;
    }
    
    void push(Type type) {
      if (this.data.length == this.sp) {
        Type[] newData = new Type[this.sp * 2];
        int[] newLocalLoad = new int[this.sp * 2];
        System.arraycopy(this.data, 0, newData, 0, this.sp);
        System.arraycopy(this.localLoads, 0, newLocalLoad, 0, this.sp);
        this.data = newData;
        this.localLoads = newLocalLoad;
      } 
      this.data[this.sp] = type;
      this.localLoads[this.sp] = -1;
      this.sp++;
    }
    
    Type peek() {
      return peek(0);
    }
    
    Type peek(int n) {
      int pos = this.sp - 1 - n;
      return (pos < 0) ? null : this.data[pos];
    }
    
    Type[] getTopTypes(int count) {
      Type[] topTypes = new Type[count];
      System.arraycopy(this.data, this.sp - count, topTypes, 0, count);
      return topTypes;
    }
    
    int[] getLocalLoads(int from, int to) {
      int count = to - from;
      int[] topLocalLoads = new int[count];
      System.arraycopy(this.localLoads, from, topLocalLoads, 0, count);
      return topLocalLoads;
    }
    
    int getUsedSlotsWithLiveTemporaries() {
      int usedSlots = this.firstTemp;
      for (int i = this.sp; i-- > 0; ) {
        int slot = this.localLoads[i];
        if (slot != -1) {
          int afterSlot = slot + ((Type)this.localVariableTypes.get(slot)).getSlots();
          if (afterSlot > usedSlots)
            usedSlots = afterSlot; 
        } 
      } 
      return usedSlots;
    }
    
    void joinFrom(Stack joinOrigin, boolean breakTarget) {
      assert isStackCompatible(joinOrigin);
      if (breakTarget) {
        this.firstTemp = Math.min(this.firstTemp, joinOrigin.firstTemp);
      } else {
        assert this.firstTemp == joinOrigin.firstTemp;
      } 
      int[] otherLoads = joinOrigin.localLoads;
      int firstDeadTemp = this.firstTemp;
      for (int i = 0; i < this.sp; i++) {
        int localLoad = this.localLoads[i];
        if (localLoad != otherLoads[i]) {
          this.localLoads[i] = -1;
        } else if (localLoad >= firstDeadTemp) {
          firstDeadTemp = localLoad + ((Type)this.localVariableTypes.get(localLoad)).getSlots();
        } 
      } 
      undefineLocalVariables(firstDeadTemp, false);
      assert isVariablePartitioningEqual(joinOrigin, firstDeadTemp);
      mergeVariableTypes(joinOrigin, firstDeadTemp);
    }
    
    private void mergeVariableTypes(Stack joinOrigin, int toSlot) {
      ListIterator<Type> it1 = this.localVariableTypes.listIterator();
      Iterator<Type> it2 = joinOrigin.localVariableTypes.iterator();
      for (int i = 0; i < toSlot; i++) {
        Type thisType = it1.next();
        Type otherType = it2.next();
        if (otherType == Type.UNKNOWN) {
          it1.set(Type.UNKNOWN);
        } else if (thisType != otherType) {
          if (thisType.isObject() && otherType.isObject()) {
            it1.set(Type.OBJECT);
          } else {
            assert thisType == Type.UNKNOWN;
          } 
        } 
      } 
    }
    
    void joinFromTry(Stack joinOrigin) {
      this.firstTemp = Math.min(this.firstTemp, joinOrigin.firstTemp);
      assert isVariablePartitioningEqual(joinOrigin, this.firstTemp);
      mergeVariableTypes(joinOrigin, this.firstTemp);
    }
    
    private int getFirstDeadLocal(List<Type> types) {
      int i = types.size();
      ListIterator<Type> it = types.listIterator(i);
      while (it.hasPrevious() && it.previous() == Type.UNKNOWN)
        i--; 
      while (!this.symbolBoundary.get(i - 1))
        i++; 
      return i;
    }
    
    private boolean isStackCompatible(Stack other) {
      if (this.sp != other.sp)
        return false; 
      for (int i = 0; i < this.sp; i++) {
        if (!this.data[i].isEquivalentTo(other.data[i]))
          return false; 
      } 
      return true;
    }
    
    private boolean isVariablePartitioningEqual(Stack other, int toSlot) {
      BitSet diff = other.getSymbolBoundaryCopy();
      diff.xor(this.symbolBoundary);
      return (diff.previousSetBit(toSlot - 1) == -1);
    }
    
    void markDeadLocalVariables(int fromSlot, int slotCount) {
      int localCount = this.localVariableTypes.size();
      if (fromSlot >= localCount)
        return; 
      int toSlot = Math.min(fromSlot + slotCount, localCount);
      invalidateLocalLoadsOnStack(fromSlot, toSlot);
      for (int i = fromSlot; i < toSlot; i++)
        this.localVariableTypes.set(i, Type.UNKNOWN); 
    }
    
    List<Type> getLocalVariableTypesCopy() {
      return (List<Type>)((ArrayList)this.localVariableTypes).clone();
    }
    
    BitSet getSymbolBoundaryCopy() {
      return (BitSet)this.symbolBoundary.clone();
    }
    
    List<Type> getWidestLiveLocals(List<Type> lvarTypes) {
      List<Type> widestLiveLocals = new ArrayList<>(lvarTypes);
      boolean keepNextValue = true;
      int size = widestLiveLocals.size();
      for (int i = size - 1; i-- > 0; ) {
        if (this.symbolBoundary.get(i))
          keepNextValue = true; 
        Type t = widestLiveLocals.get(i);
        if (t != Type.UNKNOWN) {
          if (keepNextValue) {
            if (t != Type.SLOT_2)
              keepNextValue = false; 
            continue;
          } 
          widestLiveLocals.set(i, Type.UNKNOWN);
        } 
      } 
      widestLiveLocals.subList(Math.max(getFirstDeadLocal(widestLiveLocals), this.firstTemp), widestLiveLocals.size()).clear();
      return widestLiveLocals;
    }
    
    String markSymbolBoundariesInLvarTypesDescriptor(String lvarDescriptor) {
      char[] chars = lvarDescriptor.toCharArray();
      int j = 0;
      for (int i = 0; i < chars.length; i++) {
        char c = chars[i];
        int nextj = j + CodeGeneratorLexicalContext.getTypeForSlotDescriptor(c).getSlots();
        if (!this.symbolBoundary.get(nextj - 1))
          chars[i] = Character.toLowerCase(c); 
        j = nextj;
      } 
      return new String(chars);
    }
    
    Type pop() {
      assert this.sp > 0;
      return this.data[--this.sp];
    }
    
    public Stack clone() {
      try {
        Stack clone = (Stack)super.clone();
        clone.data = (Type[])this.data.clone();
        clone.localLoads = (int[])this.localLoads.clone();
        clone.symbolBoundary = getSymbolBoundaryCopy();
        clone.localVariableTypes = getLocalVariableTypesCopy();
        return clone;
      } catch (CloneNotSupportedException e) {
        throw new AssertionError("", e);
      } 
    }
    
    private Stack cloneWithEmptyStack() {
      Stack stack = clone();
      stack.sp = 0;
      return stack;
    }
    
    int getTopLocalLoad() {
      return this.localLoads[this.sp - 1];
    }
    
    void markLocalLoad(int slot) {
      this.localLoads[this.sp - 1] = slot;
    }
    
    void onLocalStore(Type type, int slot, boolean onlySymbolLiveValue) {
      if (onlySymbolLiveValue) {
        int fromSlot = (slot == 0) ? 0 : (this.symbolBoundary.previousSetBit(slot - 1) + 1);
        int toSlot = this.symbolBoundary.nextSetBit(slot) + 1;
        for (int i = fromSlot; i < toSlot; i++)
          this.localVariableTypes.set(i, Type.UNKNOWN); 
        invalidateLocalLoadsOnStack(fromSlot, toSlot);
      } else {
        invalidateLocalLoadsOnStack(slot, slot + type.getSlots());
      } 
      this.localVariableTypes.set(slot, type);
      if (type.isCategory2())
        this.localVariableTypes.set(slot + 1, Type.SLOT_2); 
    }
    
    private void invalidateLocalLoadsOnStack(int fromSlot, int toSlot) {
      for (int i = 0; i < this.sp; i++) {
        int localLoad = this.localLoads[i];
        if (localLoad >= fromSlot && localLoad < toSlot)
          this.localLoads[i] = -1; 
      } 
    }
    
    void defineBlockLocalVariable(int fromSlot, int toSlot) {
      defineLocalVariable(fromSlot, toSlot);
      assert this.firstTemp < toSlot;
      this.firstTemp = toSlot;
    }
    
    int defineTemporaryLocalVariable(int width) {
      int fromSlot = getUsedSlotsWithLiveTemporaries();
      defineLocalVariable(fromSlot, fromSlot + width);
      return fromSlot;
    }
    
    void defineTemporaryLocalVariable(int fromSlot, int toSlot) {
      defineLocalVariable(fromSlot, toSlot);
    }
    
    private void defineLocalVariable(int fromSlot, int toSlot) {
      assert !hasLoadsOnStack(fromSlot, toSlot);
      assert fromSlot < toSlot;
      this.symbolBoundary.clear(fromSlot, toSlot - 1);
      this.symbolBoundary.set(toSlot - 1);
      int lastExisting = Math.min(toSlot, this.localVariableTypes.size());
      int i;
      for (i = fromSlot; i < lastExisting; i++)
        this.localVariableTypes.set(i, Type.UNKNOWN); 
      for (i = lastExisting; i < toSlot; i++)
        this.localVariableTypes.add(i, Type.UNKNOWN); 
    }
    
    void undefineLocalVariables(int fromSlot, boolean canTruncateSymbol) {
      int lvarCount = this.localVariableTypes.size();
      assert lvarCount == this.symbolBoundary.length();
      assert !hasLoadsOnStack(fromSlot, lvarCount);
      if (canTruncateSymbol) {
        if (fromSlot > 0)
          this.symbolBoundary.set(fromSlot - 1); 
      } else {
        assert fromSlot == 0 || this.symbolBoundary.get(fromSlot - 1);
      } 
      if (fromSlot < lvarCount) {
        this.symbolBoundary.clear(fromSlot, lvarCount);
        this.localVariableTypes.subList(fromSlot, lvarCount).clear();
      } 
      this.firstTemp = Math.min(fromSlot, this.firstTemp);
      assert this.symbolBoundary.length() == this.localVariableTypes.size();
      assert this.symbolBoundary.length() == fromSlot;
    }
    
    private void markAsOptimisticCatchHandler(int liveLocalCount) {
      undefineLocalVariables(liveLocalCount, true);
      this.firstTemp = liveLocalCount;
      this.localVariableTypes.subList(this.firstTemp, this.localVariableTypes.size()).clear();
      assert this.symbolBoundary.length() == this.firstTemp;
      for (ListIterator<Type> it = this.localVariableTypes.listIterator(); it.hasNext(); ) {
        Type type = it.next();
        if (type == Type.BOOLEAN) {
          it.set(Type.INT);
          continue;
        } 
        if (type.isObject() && type != Type.OBJECT)
          it.set(Type.OBJECT); 
      } 
    }
    
    boolean hasLoadsOnStack(int fromSlot, int toSlot) {
      for (int i = 0; i < this.sp; i++) {
        int load = this.localLoads[i];
        if (load >= fromSlot && load < toSlot)
          return true; 
      } 
      return false;
    }
    
    public String toString() {
      return "stack=" + Arrays.toString(Arrays.copyOf((Object[])this.data, this.sp)) + ", symbolBoundaries=" + this.symbolBoundary + ", firstTemp=" + this.firstTemp + ", localTypes=" + this.localVariableTypes;
    }
  }
  
  private static int nextId = 0;
  
  private final String name;
  
  private transient Stack stack;
  
  private transient org.objectweb.asm.Label label;
  
  private final int id;
  
  private transient boolean reachable;
  
  private transient boolean breakTarget;
  
  private String str;
  
  public Label(String name) {
    this.name = name;
    this.id = nextId++;
  }
  
  public Label(Label label) {
    this.name = label.name;
    this.id = label.id;
  }
  
  org.objectweb.asm.Label getLabel() {
    if (this.label == null)
      this.label = new org.objectweb.asm.Label(); 
    return this.label;
  }
  
  Stack getStack() {
    return this.stack;
  }
  
  void joinFrom(Stack joinOrigin) {
    this.reachable = true;
    if (this.stack == null) {
      this.stack = joinOrigin.clone();
    } else {
      this.stack.joinFrom(joinOrigin, this.breakTarget);
    } 
  }
  
  void joinFromTry(Stack joinOrigin, boolean isOptimismHandler) {
    this.reachable = true;
    if (this.stack == null) {
      if (!isOptimismHandler) {
        this.stack = joinOrigin.cloneWithEmptyStack();
        this.stack.undefineLocalVariables(this.stack.firstTemp, false);
      } 
    } else {
      assert !isOptimismHandler;
      this.stack.joinFromTry(joinOrigin);
    } 
  }
  
  void markAsBreakTarget() {
    this.breakTarget = true;
  }
  
  boolean isBreakTarget() {
    return this.breakTarget;
  }
  
  void onCatch() {
    if (this.stack != null)
      this.stack = this.stack.cloneWithEmptyStack(); 
  }
  
  void markAsOptimisticCatchHandler(Stack currentStack, int liveLocalCount) {
    this.stack = currentStack.cloneWithEmptyStack();
    this.stack.markAsOptimisticCatchHandler(liveLocalCount);
  }
  
  void markAsOptimisticContinuationHandlerFor(Label afterConsumeStackLabel) {
    this.stack = afterConsumeStackLabel.stack.cloneWithEmptyStack();
  }
  
  boolean isReachable() {
    return this.reachable;
  }
  
  boolean isAfter(Label other) {
    return (this.label.getOffset() > other.label.getOffset());
  }
  
  public String toString() {
    if (this.str == null)
      this.str = this.name + "_" + this.name; 
    return this.str;
  }
}
