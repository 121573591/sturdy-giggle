package org.apache.commons.io.file;

import java.math.BigInteger;
import java.util.Objects;

public class Counters {
  private static class AbstractPathCounters implements PathCounters {
    private final Counters.Counter byteCounter;
    
    private final Counters.Counter directoryCounter;
    
    private final Counters.Counter fileCounter;
    
    protected AbstractPathCounters(Counters.Counter byteCounter, Counters.Counter directoryCounter, Counters.Counter fileCounter) {
      this.byteCounter = byteCounter;
      this.directoryCounter = directoryCounter;
      this.fileCounter = fileCounter;
    }
    
    public boolean equals(Object obj) {
      if (this == obj)
        return true; 
      if (!(obj instanceof AbstractPathCounters))
        return false; 
      AbstractPathCounters other = (AbstractPathCounters)obj;
      return (Objects.equals(this.byteCounter, other.byteCounter) && 
        Objects.equals(this.directoryCounter, other.directoryCounter) && 
        Objects.equals(this.fileCounter, other.fileCounter));
    }
    
    public Counters.Counter getByteCounter() {
      return this.byteCounter;
    }
    
    public Counters.Counter getDirectoryCounter() {
      return this.directoryCounter;
    }
    
    public Counters.Counter getFileCounter() {
      return this.fileCounter;
    }
    
    public int hashCode() {
      return Objects.hash(new Object[] { this.byteCounter, this.directoryCounter, this.fileCounter });
    }
    
    public void reset() {
      this.byteCounter.reset();
      this.directoryCounter.reset();
      this.fileCounter.reset();
    }
    
    public String toString() {
      return String.format("%,d files, %,d directories, %,d bytes", new Object[] { Long.valueOf(this.fileCounter.get()), 
            Long.valueOf(this.directoryCounter.get()), Long.valueOf(this.byteCounter.get()) });
    }
  }
  
  private static final class BigIntegerCounter implements Counter {
    private BigInteger value = BigInteger.ZERO;
    
    public void add(long val) {
      this.value = this.value.add(BigInteger.valueOf(val));
    }
    
    public boolean equals(Object obj) {
      if (this == obj)
        return true; 
      if (!(obj instanceof Counters.Counter))
        return false; 
      Counters.Counter other = (Counters.Counter)obj;
      return Objects.equals(this.value, other.getBigInteger());
    }
    
    public long get() {
      return this.value.longValueExact();
    }
    
    public BigInteger getBigInteger() {
      return this.value;
    }
    
    public Long getLong() {
      return Long.valueOf(this.value.longValueExact());
    }
    
    public int hashCode() {
      return Objects.hash(new Object[] { this.value });
    }
    
    public void increment() {
      this.value = this.value.add(BigInteger.ONE);
    }
    
    public String toString() {
      return this.value.toString();
    }
    
    public void reset() {
      this.value = BigInteger.ZERO;
    }
    
    private BigIntegerCounter() {}
  }
  
  private static final class BigIntegerPathCounters extends AbstractPathCounters {
    protected BigIntegerPathCounters() {
      super(Counters.bigIntegerCounter(), Counters.bigIntegerCounter(), Counters.bigIntegerCounter());
    }
  }
  
  public static interface Counter {
    void add(long param1Long);
    
    long get();
    
    BigInteger getBigInteger();
    
    Long getLong();
    
    void increment();
    
    default void reset() {}
  }
  
  private static final class LongCounter implements Counter {
    private long value;
    
    private LongCounter() {}
    
    public void add(long add) {
      this.value += add;
    }
    
    public boolean equals(Object obj) {
      if (this == obj)
        return true; 
      if (!(obj instanceof Counters.Counter))
        return false; 
      Counters.Counter other = (Counters.Counter)obj;
      return (this.value == other.get());
    }
    
    public long get() {
      return this.value;
    }
    
    public BigInteger getBigInteger() {
      return BigInteger.valueOf(this.value);
    }
    
    public Long getLong() {
      return Long.valueOf(this.value);
    }
    
    public int hashCode() {
      return Objects.hash(new Object[] { Long.valueOf(this.value) });
    }
    
    public void increment() {
      this.value++;
    }
    
    public String toString() {
      return Long.toString(this.value);
    }
    
    public void reset() {
      this.value = 0L;
    }
  }
  
  private static final class LongPathCounters extends AbstractPathCounters {
    protected LongPathCounters() {
      super(Counters.longCounter(), Counters.longCounter(), Counters.longCounter());
    }
  }
  
  private static final class NoopCounter implements Counter {
    static final NoopCounter INSTANCE = new NoopCounter();
    
    public void add(long add) {}
    
    public long get() {
      return 0L;
    }
    
    public BigInteger getBigInteger() {
      return BigInteger.ZERO;
    }
    
    public Long getLong() {
      return Long.valueOf(0L);
    }
    
    public void increment() {}
  }
  
  private static final class NoopPathCounters extends AbstractPathCounters {
    static final NoopPathCounters INSTANCE = new NoopPathCounters();
    
    private NoopPathCounters() {
      super(Counters.noopCounter(), Counters.noopCounter(), Counters.noopCounter());
    }
  }
  
  public static interface PathCounters {
    Counters.Counter getByteCounter();
    
    Counters.Counter getDirectoryCounter();
    
    Counters.Counter getFileCounter();
    
    default void reset() {}
  }
  
  public static Counter bigIntegerCounter() {
    return new BigIntegerCounter();
  }
  
  public static PathCounters bigIntegerPathCounters() {
    return new BigIntegerPathCounters();
  }
  
  public static Counter longCounter() {
    return new LongCounter();
  }
  
  public static PathCounters longPathCounters() {
    return new LongPathCounters();
  }
  
  public static Counter noopCounter() {
    return NoopCounter.INSTANCE;
  }
  
  public static PathCounters noopPathCounters() {
    return NoopPathCounters.INSTANCE;
  }
}
