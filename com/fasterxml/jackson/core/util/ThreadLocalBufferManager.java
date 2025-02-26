package com.fasterxml.jackson.core.util;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class ThreadLocalBufferManager {
  private final Object RELEASE_LOCK = new Object();
  
  private final Map<SoftReference<BufferRecycler>, Boolean> _trackedRecyclers = new ConcurrentHashMap<>();
  
  private final ReferenceQueue<BufferRecycler> _refQueue = new ReferenceQueue<>();
  
  public static ThreadLocalBufferManager instance() {
    return ThreadLocalBufferManagerHolder.manager;
  }
  
  public int releaseBuffers() {
    synchronized (this.RELEASE_LOCK) {
      int count = 0;
      removeSoftRefsClearedByGc();
      for (SoftReference<BufferRecycler> ref : this._trackedRecyclers.keySet()) {
        ref.clear();
        count++;
      } 
      this._trackedRecyclers.clear();
      return count;
    } 
  }
  
  public SoftReference<BufferRecycler> wrapAndTrack(BufferRecycler br) {
    SoftReference<BufferRecycler> newRef = new SoftReference<>(br, this._refQueue);
    this._trackedRecyclers.put(newRef, Boolean.valueOf(true));
    removeSoftRefsClearedByGc();
    return newRef;
  }
  
  private void removeSoftRefsClearedByGc() {
    SoftReference<?> clearedSoftRef;
    while ((clearedSoftRef = (SoftReference)this._refQueue.poll()) != null)
      this._trackedRecyclers.remove(clearedSoftRef); 
  }
  
  private static final class ThreadLocalBufferManagerHolder {
    static final ThreadLocalBufferManager manager = new ThreadLocalBufferManager();
  }
}
