package cn.hutool.core.collection;

import cn.hutool.core.lang.Assert;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class RingIndexUtil {
  public static int ringNextIntByObj(Object object, AtomicInteger atomicInteger) {
    Assert.notNull(object);
    int modulo = CollUtil.size(object);
    return ringNextInt(modulo, atomicInteger);
  }
  
  public static int ringNextInt(int modulo, AtomicInteger atomicInteger) {
    Assert.notNull(atomicInteger);
    Assert.isTrue((modulo > 0));
    if (modulo <= 1)
      return 0; 
    while (true) {
      int current = atomicInteger.get();
      int next = (current + 1) % modulo;
      if (atomicInteger.compareAndSet(current, next))
        return next; 
    } 
  }
  
  public static long ringNextLong(long modulo, AtomicLong atomicLong) {
    Assert.notNull(atomicLong);
    Assert.isTrue((modulo > 0L));
    if (modulo <= 1L)
      return 0L; 
    while (true) {
      long current = atomicLong.get();
      long next = (current + 1L) % modulo;
      if (atomicLong.compareAndSet(current, next))
        return next; 
    } 
  }
}
