package org.openjdk.nashorn.internal.runtime;

import java.lang.invoke.MethodHandle;
import java.util.AbstractList;
import java.util.Deque;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.RandomAccess;
import java.util.function.Supplier;
import org.openjdk.nashorn.api.scripting.JSObject;
import org.openjdk.nashorn.api.scripting.ScriptObjectMirror;
import org.openjdk.nashorn.internal.objects.Global;
import org.openjdk.nashorn.internal.runtime.linker.Bootstrap;

public class ListAdapter extends AbstractList<Object> implements RandomAccess, Deque<Object> {
  private static final Supplier<MethodHandle> ADD_INVOKER_CREATOR = invokerCreator(void.class, new Class[] { Object.class, JSObject.class, Object.class });
  
  private static final Object PUSH = new Object();
  
  private static final Object UNSHIFT = new Object();
  
  private static final Supplier<MethodHandle> REMOVE_INVOKER_CREATOR = invokerCreator(Object.class, new Class[] { Object.class, JSObject.class });
  
  private static final Object POP = new Object();
  
  private static final Object SHIFT = new Object();
  
  private static final Object SPLICE_ADD = new Object();
  
  private static final Supplier<MethodHandle> SPLICE_ADD_INVOKER_CREATOR = invokerCreator(void.class, new Class[] { Object.class, JSObject.class, int.class, int.class, Object.class });
  
  private static final Object SPLICE_REMOVE = new Object();
  
  private static final Supplier<MethodHandle> SPLICE_REMOVE_INVOKER_CREATOR = invokerCreator(void.class, new Class[] { Object.class, JSObject.class, int.class, int.class });
  
  final JSObject obj;
  
  private final Global global;
  
  ListAdapter(JSObject obj, Global global) {
    if (global == null)
      throw new IllegalStateException(ECMAErrors.getMessage("list.adapter.null.global", new String[0])); 
    this.obj = obj;
    this.global = global;
  }
  
  public static ListAdapter create(Object obj) {
    Global global = Context.getGlobal();
    return new ListAdapter(getJSObject(obj, global), global);
  }
  
  private static JSObject getJSObject(Object obj, Global global) {
    if (obj instanceof ScriptObject)
      return (JSObject)ScriptObjectMirror.wrap(obj, global); 
    if (obj instanceof JSObject)
      return (JSObject)obj; 
    throw new IllegalArgumentException("ScriptObject or JSObject expected");
  }
  
  public final Object get(int index) {
    checkRange(index);
    return getAt(index);
  }
  
  private Object getAt(int index) {
    return this.obj.getSlot(index);
  }
  
  public Object set(int index, Object element) {
    checkRange(index);
    Object prevValue = getAt(index);
    this.obj.setSlot(index, element);
    return prevValue;
  }
  
  private void checkRange(int index) {
    if (index < 0 || index >= size())
      throw invalidIndex(index); 
  }
  
  public int size() {
    return JSType.toInt32(this.obj.getMember("length"));
  }
  
  public final void push(Object e) {
    addFirst(e);
  }
  
  public final boolean add(Object e) {
    addLast(e);
    return true;
  }
  
  public final void addFirst(Object e) {
    try {
      getDynamicInvoker(UNSHIFT, ADD_INVOKER_CREATOR).invokeExact(getFunction("unshift"), this.obj, e);
    } catch (RuntimeException|Error ex) {
      throw ex;
    } catch (Throwable t) {
      throw new RuntimeException(t);
    } 
  }
  
  public final void addLast(Object e) {
    try {
      getDynamicInvoker(PUSH, ADD_INVOKER_CREATOR).invokeExact(getFunction("push"), this.obj, e);
    } catch (RuntimeException|Error ex) {
      throw ex;
    } catch (Throwable t) {
      throw new RuntimeException(t);
    } 
  }
  
  public final void add(int index, Object e) {
    try {
      if (index < 0)
        throw invalidIndex(index); 
      if (index == 0) {
        addFirst(e);
      } else {
        int size = size();
        if (index < size) {
          getDynamicInvoker(SPLICE_ADD, SPLICE_ADD_INVOKER_CREATOR).invokeExact(this.obj.getMember("splice"), this.obj, index, 0, e);
        } else if (index == size) {
          addLast(e);
        } else {
          throw invalidIndex(index);
        } 
      } 
    } catch (RuntimeException|Error ex) {
      throw ex;
    } catch (Throwable t) {
      throw new RuntimeException(t);
    } 
  }
  
  private Object getFunction(String name) {
    Object fn = this.obj.getMember(name);
    if (!Bootstrap.isCallable(fn))
      throw new UnsupportedOperationException("The script object doesn't have a function named " + name); 
    return fn;
  }
  
  private static IndexOutOfBoundsException invalidIndex(int index) {
    return new IndexOutOfBoundsException(String.valueOf(index));
  }
  
  public final boolean offer(Object e) {
    return offerLast(e);
  }
  
  public final boolean offerFirst(Object e) {
    addFirst(e);
    return true;
  }
  
  public final boolean offerLast(Object e) {
    addLast(e);
    return true;
  }
  
  public final Object pop() {
    return removeFirst();
  }
  
  public final Object remove() {
    return removeFirst();
  }
  
  public final Object removeFirst() {
    checkNonEmpty();
    return invokeShift();
  }
  
  public final Object removeLast() {
    checkNonEmpty();
    return invokePop();
  }
  
  private void checkNonEmpty() {
    if (isEmpty())
      throw new NoSuchElementException(); 
  }
  
  public final Object remove(int index) {
    if (index < 0)
      throw invalidIndex(index); 
    if (index == 0)
      return invokeShift(); 
    int maxIndex = size() - 1;
    if (index < maxIndex) {
      Object prevValue = get(index);
      invokeSpliceRemove(index, 1);
      return prevValue;
    } 
    if (index == maxIndex)
      return invokePop(); 
    throw invalidIndex(index);
  }
  
  private Object invokeShift() {
    try {
      return getDynamicInvoker(SHIFT, REMOVE_INVOKER_CREATOR).invokeExact(getFunction("shift"), this.obj);
    } catch (RuntimeException|Error ex) {
      throw ex;
    } catch (Throwable t) {
      throw new RuntimeException(t);
    } 
  }
  
  private Object invokePop() {
    try {
      return getDynamicInvoker(POP, REMOVE_INVOKER_CREATOR).invokeExact(getFunction("pop"), this.obj);
    } catch (RuntimeException|Error ex) {
      throw ex;
    } catch (Throwable t) {
      throw new RuntimeException(t);
    } 
  }
  
  protected final void removeRange(int fromIndex, int toIndex) {
    invokeSpliceRemove(fromIndex, toIndex - fromIndex);
  }
  
  private void invokeSpliceRemove(int fromIndex, int count) {
    try {
      getDynamicInvoker(SPLICE_REMOVE, SPLICE_REMOVE_INVOKER_CREATOR).invokeExact(getFunction("splice"), this.obj, fromIndex, count);
    } catch (RuntimeException|Error ex) {
      throw ex;
    } catch (Throwable t) {
      throw new RuntimeException(t);
    } 
  }
  
  public final Object poll() {
    return pollFirst();
  }
  
  public final Object pollFirst() {
    return isEmpty() ? null : invokeShift();
  }
  
  public final Object pollLast() {
    return isEmpty() ? null : invokePop();
  }
  
  public final Object peek() {
    return peekFirst();
  }
  
  public final Object peekFirst() {
    return isEmpty() ? null : get(0);
  }
  
  public final Object peekLast() {
    return isEmpty() ? null : get(size() - 1);
  }
  
  public final Object element() {
    return getFirst();
  }
  
  public final Object getFirst() {
    checkNonEmpty();
    return get(0);
  }
  
  public final Object getLast() {
    checkNonEmpty();
    return get(size() - 1);
  }
  
  public final Iterator<Object> descendingIterator() {
    final ListIterator<Object> it = listIterator(size());
    return new Iterator() {
        public boolean hasNext() {
          return it.hasPrevious();
        }
        
        public Object next() {
          return it.previous();
        }
        
        public void remove() {
          it.remove();
        }
      };
  }
  
  public final boolean removeFirstOccurrence(Object o) {
    return removeOccurrence(o, iterator());
  }
  
  public final boolean removeLastOccurrence(Object o) {
    return removeOccurrence(o, descendingIterator());
  }
  
  private static boolean removeOccurrence(Object o, Iterator<Object> it) {
    while (it.hasNext()) {
      if (Objects.equals(o, it.next())) {
        it.remove();
        return true;
      } 
    } 
    return false;
  }
  
  private static Supplier<MethodHandle> invokerCreator(Class<?> rtype, Class<?>... ptypes) {
    return () -> Bootstrap.createDynamicCallInvoker(rtype, ptypes);
  }
  
  private MethodHandle getDynamicInvoker(Object key, Supplier<MethodHandle> creator) {
    return this.global.getDynamicInvoker(key, creator);
  }
}
