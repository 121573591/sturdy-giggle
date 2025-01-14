package com.fasterxml.jackson.core.util;

import java.util.concurrent.ConcurrentHashMap;

public final class InternCache extends ConcurrentHashMap<String, String> {
  private static final long serialVersionUID = 1L;
  
  private static final int MAX_ENTRIES = 180;
  
  public static final InternCache instance = new InternCache();
  
  private final Object lock = new Object();
  
  public InternCache() {
    this(180, 0.8F, 4);
  }
  
  public InternCache(int maxSize, float loadFactor, int concurrency) {
    super(maxSize, loadFactor, concurrency);
  }
  
  public String intern(String input) {
    String result = get(input);
    if (result != null)
      return result; 
    if (size() >= 180)
      synchronized (this.lock) {
        if (size() >= 180)
          clear(); 
      }  
    result = input.intern();
    put(result, result);
    return result;
  }
}
