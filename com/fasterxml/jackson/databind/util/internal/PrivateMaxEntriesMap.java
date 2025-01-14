package com.fasterxml.jackson.databind.util.internal;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public final class PrivateMaxEntriesMap<K, V> extends AbstractMap<K, V> implements ConcurrentMap<K, V>, Serializable {
  static final int NCPU = Runtime.getRuntime().availableProcessors();
  
  static final long MAXIMUM_CAPACITY = 9223372034707292160L;
  
  static final int NUMBER_OF_READ_BUFFERS = Math.min(4, ceilingNextPowerOfTwo(NCPU));
  
  static final int READ_BUFFERS_MASK = NUMBER_OF_READ_BUFFERS - 1;
  
  static final int READ_BUFFER_THRESHOLD = 4;
  
  static final int READ_BUFFER_DRAIN_THRESHOLD = 8;
  
  static final int READ_BUFFER_SIZE = 16;
  
  static final int READ_BUFFER_INDEX_MASK = 15;
  
  static final int WRITE_BUFFER_DRAIN_THRESHOLD = 16;
  
  final ConcurrentMap<K, Node<K, V>> data;
  
  final int concurrencyLevel;
  
  final long[] readBufferReadCount;
  
  final LinkedDeque<Node<K, V>> evictionDeque;
  
  final AtomicLong weightedSize;
  
  final AtomicLong capacity;
  
  final Lock evictionLock;
  
  final Queue<Runnable> writeBuffer;
  
  final AtomicLongArray readBufferWriteCount;
  
  final AtomicLongArray readBufferDrainAtWriteCount;
  
  final AtomicReferenceArray<Node<K, V>> readBuffers;
  
  final AtomicReference<DrainStatus> drainStatus;
  
  transient Set<K> keySet;
  
  transient Collection<V> values;
  
  transient Set<Map.Entry<K, V>> entrySet;
  
  static final long serialVersionUID = 1L;
  
  static int ceilingNextPowerOfTwo(int x) {
    return 1 << 32 - Integer.numberOfLeadingZeros(x - 1);
  }
  
  private static int readBufferIndex(int bufferIndex, int entryIndex) {
    return 16 * bufferIndex + entryIndex;
  }
  
  PrivateMaxEntriesMap(Builder<K, V> builder) {
    this.concurrencyLevel = builder.concurrencyLevel;
    this.capacity = new AtomicLong(Math.min(builder.capacity, 9223372034707292160L));
    this.data = new ConcurrentHashMap<>(builder.initialCapacity, 0.75F, this.concurrencyLevel);
    this.evictionLock = new ReentrantLock();
    this.weightedSize = new AtomicLong();
    this.evictionDeque = new LinkedDeque<>();
    this.writeBuffer = new ConcurrentLinkedQueue<>();
    this.drainStatus = new AtomicReference<>(DrainStatus.IDLE);
    this.readBufferReadCount = new long[NUMBER_OF_READ_BUFFERS];
    this.readBufferWriteCount = new AtomicLongArray(NUMBER_OF_READ_BUFFERS);
    this.readBufferDrainAtWriteCount = new AtomicLongArray(NUMBER_OF_READ_BUFFERS);
    this.readBuffers = new AtomicReferenceArray<>(NUMBER_OF_READ_BUFFERS * 16);
  }
  
  static void checkNotNull(Object o) {
    if (o == null)
      throw new NullPointerException(); 
  }
  
  static void checkArgument(boolean expression) {
    if (!expression)
      throw new IllegalArgumentException(); 
  }
  
  static void checkState(boolean expression) {
    if (!expression)
      throw new IllegalStateException(); 
  }
  
  public long capacity() {
    return this.capacity.get();
  }
  
  public void setCapacity(long capacity) {
    checkArgument((capacity >= 0L));
    this.evictionLock.lock();
    try {
      this.capacity.lazySet(Math.min(capacity, 9223372034707292160L));
      drainBuffers();
      evict();
    } finally {
      this.evictionLock.unlock();
    } 
  }
  
  boolean hasOverflowed() {
    return (this.weightedSize.get() > this.capacity.get());
  }
  
  void evict() {
    while (hasOverflowed()) {
      Node<K, V> node = this.evictionDeque.poll();
      if (node == null)
        return; 
      this.data.remove(node.key, node);
      makeDead(node);
    } 
  }
  
  void afterRead(Node<K, V> node) {
    int bufferIndex = readBufferIndex();
    long writeCount = recordRead(bufferIndex, node);
    drainOnReadIfNeeded(bufferIndex, writeCount);
  }
  
  static int readBufferIndex() {
    return (int)Thread.currentThread().getId() & READ_BUFFERS_MASK;
  }
  
  long recordRead(int bufferIndex, Node<K, V> node) {
    long writeCount = this.readBufferWriteCount.get(bufferIndex);
    this.readBufferWriteCount.lazySet(bufferIndex, writeCount + 1L);
    int index = (int)(writeCount & 0xFL);
    this.readBuffers.lazySet(readBufferIndex(bufferIndex, index), node);
    return writeCount;
  }
  
  void drainOnReadIfNeeded(int bufferIndex, long writeCount) {
    long pending = writeCount - this.readBufferDrainAtWriteCount.get(bufferIndex);
    boolean delayable = (pending < 4L);
    DrainStatus status = this.drainStatus.get();
    if (status.shouldDrainBuffers(delayable))
      tryToDrainBuffers(); 
  }
  
  void afterWrite(Runnable task) {
    this.writeBuffer.add(task);
    this.drainStatus.lazySet(DrainStatus.REQUIRED);
    tryToDrainBuffers();
  }
  
  void tryToDrainBuffers() {
    if (this.evictionLock.tryLock())
      try {
        this.drainStatus.lazySet(DrainStatus.PROCESSING);
        drainBuffers();
      } finally {
        this.drainStatus.compareAndSet(DrainStatus.PROCESSING, DrainStatus.IDLE);
        this.evictionLock.unlock();
      }  
  }
  
  void drainBuffers() {
    drainReadBuffers();
    drainWriteBuffer();
  }
  
  void drainReadBuffers() {
    int start = (int)Thread.currentThread().getId();
    int end = start + NUMBER_OF_READ_BUFFERS;
    for (int i = start; i < end; i++)
      drainReadBuffer(i & READ_BUFFERS_MASK); 
  }
  
  void drainReadBuffer(int bufferIndex) {
    long writeCount = this.readBufferWriteCount.get(bufferIndex);
    for (int i = 0; i < 8; i++) {
      int index = (int)(this.readBufferReadCount[bufferIndex] & 0xFL);
      int arrayIndex = readBufferIndex(bufferIndex, index);
      Node<K, V> node = this.readBuffers.get(arrayIndex);
      if (node == null)
        break; 
      this.readBuffers.lazySet(arrayIndex, null);
      applyRead(node);
      this.readBufferReadCount[bufferIndex] = this.readBufferReadCount[bufferIndex] + 1L;
    } 
    this.readBufferDrainAtWriteCount.lazySet(bufferIndex, writeCount);
  }
  
  void applyRead(Node<K, V> node) {
    if (this.evictionDeque.contains(node))
      this.evictionDeque.moveToBack(node); 
  }
  
  void drainWriteBuffer() {
    for (int i = 0; i < 16; i++) {
      Runnable task = this.writeBuffer.poll();
      if (task == null)
        break; 
      task.run();
    } 
  }
  
  boolean tryToRetire(Node<K, V> node, WeightedValue<V> expect) {
    if (expect.isAlive()) {
      WeightedValue<V> retired = new WeightedValue<>(expect.value, -expect.weight);
      return node.compareAndSet(expect, retired);
    } 
    return false;
  }
  
  void makeRetired(Node<K, V> node) {
    WeightedValue<V> current;
    WeightedValue<V> retired;
    do {
      current = node.get();
      if (!current.isAlive())
        return; 
      retired = new WeightedValue<>(current.value, -current.weight);
    } while (!node.compareAndSet(current, retired));
  }
  
  void makeDead(Node<K, V> node) {
    while (true) {
      WeightedValue<V> current = node.get();
      WeightedValue<V> dead = new WeightedValue<>(current.value, 0);
      if (node.compareAndSet(current, dead)) {
        this.weightedSize.lazySet(this.weightedSize.get() - Math.abs(current.weight));
        return;
      } 
    } 
  }
  
  final class AddTask implements Runnable {
    final PrivateMaxEntriesMap.Node<K, V> node;
    
    final int weight;
    
    AddTask(PrivateMaxEntriesMap.Node<K, V> node, int weight) {
      this.weight = weight;
      this.node = node;
    }
    
    public void run() {
      PrivateMaxEntriesMap.this.weightedSize.lazySet(PrivateMaxEntriesMap.this.weightedSize.get() + this.weight);
      if (this.node.get().isAlive()) {
        PrivateMaxEntriesMap.this.evictionDeque.add(this.node);
        PrivateMaxEntriesMap.this.evict();
      } 
    }
  }
  
  final class RemovalTask implements Runnable {
    final PrivateMaxEntriesMap.Node<K, V> node;
    
    RemovalTask(PrivateMaxEntriesMap.Node<K, V> node) {
      this.node = node;
    }
    
    public void run() {
      PrivateMaxEntriesMap.this.evictionDeque.remove(this.node);
      PrivateMaxEntriesMap.this.makeDead(this.node);
    }
  }
  
  final class UpdateTask implements Runnable {
    final int weightDifference;
    
    final PrivateMaxEntriesMap.Node<K, V> node;
    
    UpdateTask(PrivateMaxEntriesMap.Node<K, V> node, int weightDifference) {
      this.weightDifference = weightDifference;
      this.node = node;
    }
    
    public void run() {
      PrivateMaxEntriesMap.this.weightedSize.lazySet(PrivateMaxEntriesMap.this.weightedSize.get() + this.weightDifference);
      PrivateMaxEntriesMap.this.applyRead(this.node);
      PrivateMaxEntriesMap.this.evict();
    }
  }
  
  public boolean isEmpty() {
    return this.data.isEmpty();
  }
  
  public int size() {
    return this.data.size();
  }
  
  public void clear() {
    this.evictionLock.lock();
    try {
      Node<K, V> node;
      while ((node = this.evictionDeque.poll()) != null) {
        this.data.remove(node.key, node);
        makeDead(node);
      } 
      for (int i = 0; i < this.readBuffers.length(); i++)
        this.readBuffers.lazySet(i, null); 
      Runnable task;
      while ((task = this.writeBuffer.poll()) != null)
        task.run(); 
    } finally {
      this.evictionLock.unlock();
    } 
  }
  
  public boolean containsKey(Object key) {
    return this.data.containsKey(key);
  }
  
  public boolean containsValue(Object value) {
    checkNotNull(value);
    for (Node<K, V> node : this.data.values()) {
      if (node.getValue().equals(value))
        return true; 
    } 
    return false;
  }
  
  public V get(Object key) {
    Node<K, V> node = this.data.get(key);
    if (node == null)
      return null; 
    afterRead(node);
    return node.getValue();
  }
  
  public V put(K key, V value) {
    return put(key, value, false);
  }
  
  public V putIfAbsent(K key, V value) {
    return put(key, value, true);
  }
  
  V put(K key, V value, boolean onlyIfAbsent) {
    checkNotNull(key);
    checkNotNull(value);
    int weight = 1;
    WeightedValue<V> weightedValue = new WeightedValue<>(value, 1);
    Node<K, V> node = new Node<>(key, weightedValue);
    label21: while (true) {
      Node<K, V> prior = this.data.putIfAbsent(node.key, node);
      if (prior == null) {
        afterWrite(new AddTask(node, 1));
        return null;
      } 
      if (onlyIfAbsent) {
        afterRead(prior);
        return prior.getValue();
      } 
      while (true) {
        WeightedValue<V> oldWeightedValue = prior.get();
        if (!oldWeightedValue.isAlive())
          continue label21; 
        if (prior.compareAndSet(oldWeightedValue, weightedValue)) {
          int weightedDifference = 1 - oldWeightedValue.weight;
          if (weightedDifference == 0) {
            afterRead(prior);
          } else {
            afterWrite(new UpdateTask(prior, weightedDifference));
          } 
          return oldWeightedValue.value;
        } 
      } 
      break;
    } 
  }
  
  public V remove(Object key) {
    Node<K, V> node = this.data.remove(key);
    if (node == null)
      return null; 
    makeRetired(node);
    afterWrite(new RemovalTask(node));
    return node.getValue();
  }
  
  public boolean remove(Object key, Object value) {
    Node<K, V> node = this.data.get(key);
    if (node == null || value == null)
      return false; 
    WeightedValue<V> weightedValue = node.get();
    while (weightedValue.contains(value)) {
      if (tryToRetire(node, weightedValue)) {
        if (this.data.remove(key, node)) {
          afterWrite(new RemovalTask(node));
          return true;
        } 
        break;
      } 
      weightedValue = node.get();
      if (weightedValue.isAlive());
    } 
    return false;
  }
  
  public V replace(K key, V value) {
    checkNotNull(key);
    checkNotNull(value);
    int weight = 1;
    WeightedValue<V> weightedValue = new WeightedValue<>(value, 1);
    Node<K, V> node = this.data.get(key);
    if (node == null)
      return null; 
    while (true) {
      WeightedValue<V> oldWeightedValue = node.get();
      if (!oldWeightedValue.isAlive())
        return null; 
      if (node.compareAndSet(oldWeightedValue, weightedValue)) {
        int weightedDifference = 1 - oldWeightedValue.weight;
        if (weightedDifference == 0) {
          afterRead(node);
        } else {
          afterWrite(new UpdateTask(node, weightedDifference));
        } 
        return oldWeightedValue.value;
      } 
    } 
  }
  
  public boolean replace(K key, V oldValue, V newValue) {
    checkNotNull(key);
    checkNotNull(oldValue);
    checkNotNull(newValue);
    int weight = 1;
    WeightedValue<V> newWeightedValue = new WeightedValue<>(newValue, 1);
    Node<K, V> node = this.data.get(key);
    if (node == null)
      return false; 
    while (true) {
      WeightedValue<V> weightedValue = node.get();
      if (!weightedValue.isAlive() || !weightedValue.contains(oldValue))
        return false; 
      if (node.compareAndSet(weightedValue, newWeightedValue)) {
        int weightedDifference = 1 - weightedValue.weight;
        if (weightedDifference == 0) {
          afterRead(node);
        } else {
          afterWrite(new UpdateTask(node, weightedDifference));
        } 
        return true;
      } 
    } 
  }
  
  public Set<K> keySet() {
    Set<K> ks = this.keySet;
    return (ks == null) ? (this.keySet = new KeySet()) : ks;
  }
  
  public Collection<V> values() {
    Collection<V> vs = this.values;
    return (vs == null) ? (this.values = new Values()) : vs;
  }
  
  public Set<Map.Entry<K, V>> entrySet() {
    Set<Map.Entry<K, V>> es = this.entrySet;
    return (es == null) ? (this.entrySet = new EntrySet()) : es;
  }
  
  enum DrainStatus {
    IDLE {
      boolean shouldDrainBuffers(boolean delayable) {
        return !delayable;
      }
    },
    REQUIRED {
      boolean shouldDrainBuffers(boolean delayable) {
        return true;
      }
    },
    PROCESSING {
      boolean shouldDrainBuffers(boolean delayable) {
        return false;
      }
    };
    
    abstract boolean shouldDrainBuffers(boolean param1Boolean);
  }
  
  static final class WeightedValue<V> {
    final int weight;
    
    final V value;
    
    WeightedValue(V value, int weight) {
      this.weight = weight;
      this.value = value;
    }
    
    boolean contains(Object o) {
      return (o == this.value || this.value.equals(o));
    }
    
    boolean isAlive() {
      return (this.weight > 0);
    }
  }
  
  static final class Node<K, V> extends AtomicReference<WeightedValue<V>> implements Linked<Node<K, V>> {
    final K key;
    
    Node<K, V> prev;
    
    Node<K, V> next;
    
    Node(K key, PrivateMaxEntriesMap.WeightedValue<V> weightedValue) {
      super(weightedValue);
      this.key = key;
    }
    
    public Node<K, V> getPrevious() {
      return this.prev;
    }
    
    public void setPrevious(Node<K, V> prev) {
      this.prev = prev;
    }
    
    public Node<K, V> getNext() {
      return this.next;
    }
    
    public void setNext(Node<K, V> next) {
      this.next = next;
    }
    
    V getValue() {
      return (get()).value;
    }
  }
  
  final class KeySet extends AbstractSet<K> {
    final PrivateMaxEntriesMap<K, V> map = PrivateMaxEntriesMap.this;
    
    public int size() {
      return this.map.size();
    }
    
    public void clear() {
      this.map.clear();
    }
    
    public Iterator<K> iterator() {
      return new PrivateMaxEntriesMap.KeyIterator();
    }
    
    public boolean contains(Object obj) {
      return PrivateMaxEntriesMap.this.containsKey(obj);
    }
    
    public boolean remove(Object obj) {
      return (this.map.remove(obj) != null);
    }
    
    public Object[] toArray() {
      return this.map.data.keySet().toArray();
    }
    
    public <T> T[] toArray(T[] array) {
      return (T[])this.map.data.keySet().toArray((Object[])array);
    }
  }
  
  final class KeyIterator implements Iterator<K> {
    final Iterator<K> iterator = PrivateMaxEntriesMap.this.data.keySet().iterator();
    
    K current;
    
    public boolean hasNext() {
      return this.iterator.hasNext();
    }
    
    public K next() {
      this.current = this.iterator.next();
      return this.current;
    }
    
    public void remove() {
      PrivateMaxEntriesMap.checkState((this.current != null));
      PrivateMaxEntriesMap.this.remove(this.current);
      this.current = null;
    }
  }
  
  final class Values extends AbstractCollection<V> {
    public int size() {
      return PrivateMaxEntriesMap.this.size();
    }
    
    public void clear() {
      PrivateMaxEntriesMap.this.clear();
    }
    
    public Iterator<V> iterator() {
      return new PrivateMaxEntriesMap.ValueIterator();
    }
    
    public boolean contains(Object o) {
      return PrivateMaxEntriesMap.this.containsValue(o);
    }
  }
  
  final class ValueIterator implements Iterator<V> {
    final Iterator<PrivateMaxEntriesMap.Node<K, V>> iterator = PrivateMaxEntriesMap.this.data.values().iterator();
    
    PrivateMaxEntriesMap.Node<K, V> current;
    
    public boolean hasNext() {
      return this.iterator.hasNext();
    }
    
    public V next() {
      this.current = this.iterator.next();
      return this.current.getValue();
    }
    
    public void remove() {
      PrivateMaxEntriesMap.checkState((this.current != null));
      PrivateMaxEntriesMap.this.remove(this.current.key);
      this.current = null;
    }
  }
  
  final class EntrySet extends AbstractSet<Map.Entry<K, V>> {
    final PrivateMaxEntriesMap<K, V> map = PrivateMaxEntriesMap.this;
    
    public int size() {
      return this.map.size();
    }
    
    public void clear() {
      this.map.clear();
    }
    
    public Iterator<Map.Entry<K, V>> iterator() {
      return new PrivateMaxEntriesMap.EntryIterator();
    }
    
    public boolean contains(Object obj) {
      if (!(obj instanceof Map.Entry))
        return false; 
      Map.Entry<?, ?> entry = (Map.Entry<?, ?>)obj;
      PrivateMaxEntriesMap.Node<K, V> node = this.map.data.get(entry.getKey());
      return (node != null && node.getValue().equals(entry.getValue()));
    }
    
    public boolean add(Map.Entry<K, V> entry) {
      throw new UnsupportedOperationException("ConcurrentLinkedHashMap does not allow add to be called on entrySet()");
    }
    
    public boolean remove(Object obj) {
      if (!(obj instanceof Map.Entry))
        return false; 
      Map.Entry<?, ?> entry = (Map.Entry<?, ?>)obj;
      return this.map.remove(entry.getKey(), entry.getValue());
    }
  }
  
  final class EntryIterator implements Iterator<Map.Entry<K, V>> {
    final Iterator<PrivateMaxEntriesMap.Node<K, V>> iterator = PrivateMaxEntriesMap.this.data.values().iterator();
    
    PrivateMaxEntriesMap.Node<K, V> current;
    
    public boolean hasNext() {
      return this.iterator.hasNext();
    }
    
    public Map.Entry<K, V> next() {
      this.current = this.iterator.next();
      return new PrivateMaxEntriesMap.WriteThroughEntry(this.current);
    }
    
    public void remove() {
      PrivateMaxEntriesMap.checkState((this.current != null));
      PrivateMaxEntriesMap.this.remove(this.current.key);
      this.current = null;
    }
  }
  
  final class WriteThroughEntry extends AbstractMap.SimpleEntry<K, V> {
    static final long serialVersionUID = 1L;
    
    WriteThroughEntry(PrivateMaxEntriesMap.Node<K, V> node) {
      super(node.key, node.getValue());
    }
    
    public V setValue(V value) {
      PrivateMaxEntriesMap.this.put(getKey(), value);
      return super.setValue(value);
    }
    
    Object writeReplace() {
      return new AbstractMap.SimpleEntry<>(this);
    }
  }
  
  Object writeReplace() {
    return new SerializationProxy<>(this);
  }
  
  private void readObject(ObjectInputStream stream) throws InvalidObjectException {
    throw new InvalidObjectException("Proxy required");
  }
  
  static final class SerializationProxy<K, V> implements Serializable {
    final int concurrencyLevel;
    
    final Map<K, V> data;
    
    final long capacity;
    
    static final long serialVersionUID = 1L;
    
    SerializationProxy(PrivateMaxEntriesMap<K, V> map) {
      this.concurrencyLevel = map.concurrencyLevel;
      this.data = new HashMap<>(map);
      this.capacity = map.capacity.get();
    }
    
    Object readResolve() {
      PrivateMaxEntriesMap<K, V> map = (new PrivateMaxEntriesMap.Builder<>()).maximumCapacity(this.capacity).build();
      map.putAll(this.data);
      return map;
    }
  }
  
  public static final class Builder<K, V> {
    long capacity = -1L;
    
    int initialCapacity = 16;
    
    int concurrencyLevel = 16;
    
    static final int DEFAULT_INITIAL_CAPACITY = 16;
    
    static final int DEFAULT_CONCURRENCY_LEVEL = 16;
    
    public Builder<K, V> initialCapacity(int initialCapacity) {
      PrivateMaxEntriesMap.checkArgument((initialCapacity >= 0));
      this.initialCapacity = initialCapacity;
      return this;
    }
    
    public Builder<K, V> maximumCapacity(long capacity) {
      PrivateMaxEntriesMap.checkArgument((capacity >= 0L));
      this.capacity = capacity;
      return this;
    }
    
    public Builder<K, V> concurrencyLevel(int concurrencyLevel) {
      PrivateMaxEntriesMap.checkArgument((concurrencyLevel > 0));
      this.concurrencyLevel = concurrencyLevel;
      return this;
    }
    
    public PrivateMaxEntriesMap<K, V> build() {
      PrivateMaxEntriesMap.checkState((this.capacity >= 0L));
      return new PrivateMaxEntriesMap<>(this);
    }
  }
}
