package cn.hutool.core.lang;

import cn.hutool.core.date.SystemClock;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import java.io.Serializable;
import java.util.Date;

public class Snowflake implements Serializable {
  private static final long serialVersionUID = 1L;
  
  public static long DEFAULT_TWEPOCH = 1288834974657L;
  
  public static long DEFAULT_TIME_OFFSET = 2000L;
  
  private static final long WORKER_ID_BITS = 5L;
  
  private static final long MAX_WORKER_ID = 31L;
  
  private static final long DATA_CENTER_ID_BITS = 5L;
  
  private static final long MAX_DATA_CENTER_ID = 31L;
  
  private static final long SEQUENCE_BITS = 12L;
  
  private static final long WORKER_ID_SHIFT = 12L;
  
  private static final long DATA_CENTER_ID_SHIFT = 17L;
  
  private static final long TIMESTAMP_LEFT_SHIFT = 22L;
  
  private static final long SEQUENCE_MASK = 4095L;
  
  private final long twepoch;
  
  private final long workerId;
  
  private final long dataCenterId;
  
  private final boolean useSystemClock;
  
  private final long timeOffset;
  
  private final long randomSequenceLimit;
  
  private long sequence = 0L;
  
  private long lastTimestamp = -1L;
  
  public Snowflake() {
    this(IdUtil.getWorkerId(IdUtil.getDataCenterId(31L), 31L));
  }
  
  public Snowflake(long workerId) {
    this(workerId, IdUtil.getDataCenterId(31L));
  }
  
  public Snowflake(long workerId, long dataCenterId) {
    this(workerId, dataCenterId, false);
  }
  
  public Snowflake(long workerId, long dataCenterId, boolean isUseSystemClock) {
    this(null, workerId, dataCenterId, isUseSystemClock);
  }
  
  public Snowflake(Date epochDate, long workerId, long dataCenterId, boolean isUseSystemClock) {
    this(epochDate, workerId, dataCenterId, isUseSystemClock, DEFAULT_TIME_OFFSET);
  }
  
  public Snowflake(Date epochDate, long workerId, long dataCenterId, boolean isUseSystemClock, long timeOffset) {
    this(epochDate, workerId, dataCenterId, isUseSystemClock, timeOffset, 0L);
  }
  
  public Snowflake(Date epochDate, long workerId, long dataCenterId, boolean isUseSystemClock, long timeOffset, long randomSequenceLimit) {
    this.twepoch = (null != epochDate) ? epochDate.getTime() : DEFAULT_TWEPOCH;
    this.workerId = Assert.checkBetween(workerId, 0L, 31L);
    this.dataCenterId = Assert.checkBetween(dataCenterId, 0L, 31L);
    this.useSystemClock = isUseSystemClock;
    this.timeOffset = timeOffset;
    this.randomSequenceLimit = Assert.checkBetween(randomSequenceLimit, 0L, 4095L);
  }
  
  public long getWorkerId(long id) {
    return id >> 12L & 0x1FL;
  }
  
  public long getDataCenterId(long id) {
    return id >> 17L & 0x1FL;
  }
  
  public long getGenerateDateTime(long id) {
    return (id >> 22L & 0x1FFFFFFFFFFL) + this.twepoch;
  }
  
  public Pair<Long, Long> getIdScopeByTimestamp(long timestampStart, long timestampEnd) {
    return getIdScopeByTimestamp(timestampStart, timestampEnd, true);
  }
  
  public Pair<Long, Long> getIdScopeByTimestamp(long timestampStart, long timestampEnd, boolean ignoreCenterAndWorker) {
    long startTimeMinId = timestampStart - this.twepoch << 22L;
    long endTimeMinId = timestampEnd - this.twepoch << 22L;
    if (ignoreCenterAndWorker) {
      long l = endTimeMinId | 0x3FFFFFL;
      return Pair.of(Long.valueOf(startTimeMinId), Long.valueOf(l));
    } 
    long startId = startTimeMinId | this.dataCenterId << 17L | this.workerId << 12L;
    long endId = endTimeMinId | this.dataCenterId << 17L | this.workerId << 12L | 0xFFFL;
    return Pair.of(Long.valueOf(startId), Long.valueOf(endId));
  }
  
  public synchronized long nextId() {
    long timestamp = genTime();
    if (timestamp < this.lastTimestamp)
      if (this.lastTimestamp - timestamp < this.timeOffset) {
        timestamp = this.lastTimestamp;
      } else {
        throw new IllegalStateException(StrUtil.format("Clock moved backwards. Refusing to generate id for {}ms", new Object[] { Long.valueOf(this.lastTimestamp - timestamp) }));
      }  
    if (timestamp == this.lastTimestamp) {
      long sequence = this.sequence + 1L & 0xFFFL;
      if (sequence == 0L)
        timestamp = tilNextMillis(this.lastTimestamp); 
      this.sequence = sequence;
    } else if (this.randomSequenceLimit > 1L) {
      this.sequence = RandomUtil.randomLong(this.randomSequenceLimit);
    } else {
      this.sequence = 0L;
    } 
    this.lastTimestamp = timestamp;
    return timestamp - this.twepoch << 22L | this.dataCenterId << 17L | this.workerId << 12L | this.sequence;
  }
  
  public String nextIdStr() {
    return Long.toString(nextId());
  }
  
  private long tilNextMillis(long lastTimestamp) {
    long timestamp = genTime();
    while (timestamp == lastTimestamp)
      timestamp = genTime(); 
    if (timestamp < lastTimestamp)
      throw new IllegalStateException(
          StrUtil.format("Clock moved backwards. Refusing to generate id for {}ms", new Object[] { Long.valueOf(lastTimestamp - timestamp) })); 
    return timestamp;
  }
  
  private long genTime() {
    return this.useSystemClock ? SystemClock.now() : System.currentTimeMillis();
  }
}
