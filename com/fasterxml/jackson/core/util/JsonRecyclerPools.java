package com.fasterxml.jackson.core.util;

public final class JsonRecyclerPools {
  public static RecyclerPool<BufferRecycler> defaultPool() {
    return threadLocalPool();
  }
  
  public static RecyclerPool<BufferRecycler> threadLocalPool() {
    return ThreadLocalPool.GLOBAL;
  }
  
  public static RecyclerPool<BufferRecycler> nonRecyclingPool() {
    return NonRecyclingPool.GLOBAL;
  }
  
  public static RecyclerPool<BufferRecycler> sharedConcurrentDequePool() {
    return ConcurrentDequePool.GLOBAL;
  }
  
  public static RecyclerPool<BufferRecycler> newConcurrentDequePool() {
    return ConcurrentDequePool.construct();
  }
  
  public static RecyclerPool<BufferRecycler> sharedLockFreePool() {
    return LockFreePool.GLOBAL;
  }
  
  public static RecyclerPool<BufferRecycler> newLockFreePool() {
    return LockFreePool.construct();
  }
  
  public static RecyclerPool<BufferRecycler> sharedBoundedPool() {
    return BoundedPool.GLOBAL;
  }
  
  public static RecyclerPool<BufferRecycler> newBoundedPool(int size) {
    return BoundedPool.construct(size);
  }
  
  public static class ThreadLocalPool extends RecyclerPool.ThreadLocalPoolBase<BufferRecycler> {
    private static final long serialVersionUID = 1L;
    
    protected static final ThreadLocalPool GLOBAL = new ThreadLocalPool();
    
    public BufferRecycler acquirePooled() {
      return BufferRecyclers.getBufferRecycler();
    }
    
    protected Object readResolve() {
      return GLOBAL;
    }
  }
  
  public static class NonRecyclingPool extends RecyclerPool.NonRecyclingPoolBase<BufferRecycler> {
    private static final long serialVersionUID = 1L;
    
    protected static final NonRecyclingPool GLOBAL = new NonRecyclingPool();
    
    public BufferRecycler acquirePooled() {
      return new BufferRecycler();
    }
    
    protected Object readResolve() {
      return GLOBAL;
    }
  }
  
  public static class ConcurrentDequePool extends RecyclerPool.ConcurrentDequePoolBase<BufferRecycler> {
    private static final long serialVersionUID = 1L;
    
    protected static final ConcurrentDequePool GLOBAL = new ConcurrentDequePool(-1);
    
    protected ConcurrentDequePool(int serialization) {
      super(serialization);
    }
    
    public static ConcurrentDequePool construct() {
      return new ConcurrentDequePool(1);
    }
    
    public BufferRecycler createPooled() {
      return new BufferRecycler();
    }
    
    protected Object readResolve() {
      return _resolveToShared(GLOBAL).orElseGet(() -> construct());
    }
  }
  
  public static class LockFreePool extends RecyclerPool.LockFreePoolBase<BufferRecycler> {
    private static final long serialVersionUID = 1L;
    
    protected static final LockFreePool GLOBAL = new LockFreePool(-1);
    
    protected LockFreePool(int serialization) {
      super(serialization);
    }
    
    public static LockFreePool construct() {
      return new LockFreePool(1);
    }
    
    public BufferRecycler createPooled() {
      return new BufferRecycler();
    }
    
    protected Object readResolve() {
      return _resolveToShared(GLOBAL).orElseGet(() -> construct());
    }
  }
  
  public static class BoundedPool extends RecyclerPool.BoundedPoolBase<BufferRecycler> {
    private static final long serialVersionUID = 1L;
    
    protected static final BoundedPool GLOBAL = new BoundedPool(-1);
    
    protected BoundedPool(int capacityAsId) {
      super(capacityAsId);
    }
    
    public static BoundedPool construct(int capacity) {
      if (capacity <= 0)
        throw new IllegalArgumentException("capacity must be > 0, was: " + capacity); 
      return new BoundedPool(capacity);
    }
    
    public BufferRecycler createPooled() {
      return new BufferRecycler();
    }
    
    protected Object readResolve() {
      return _resolveToShared(GLOBAL).orElseGet(() -> construct(this._serialization));
    }
  }
}
