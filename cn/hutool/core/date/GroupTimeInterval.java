package cn.hutool.core.date;

import cn.hutool.core.map.SafeConcurrentHashMap;
import cn.hutool.core.util.ObjectUtil;
import java.io.Serializable;
import java.util.Map;

public class GroupTimeInterval implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private final boolean isNano;
  
  protected final Map<String, Long> groupMap;
  
  public GroupTimeInterval(boolean isNano) {
    this.isNano = isNano;
    this.groupMap = (Map<String, Long>)new SafeConcurrentHashMap();
  }
  
  public GroupTimeInterval clear() {
    this.groupMap.clear();
    return this;
  }
  
  public long start(String id) {
    long time = getTime();
    this.groupMap.put(id, Long.valueOf(time));
    return time;
  }
  
  public long intervalRestart(String id) {
    long now = getTime();
    return now - ((Long)ObjectUtil.defaultIfNull(this.groupMap.put(id, Long.valueOf(now)), Long.valueOf(now))).longValue();
  }
  
  public long interval(String id) {
    Long lastTime = this.groupMap.get(id);
    if (null == lastTime)
      return 0L; 
    return getTime() - lastTime.longValue();
  }
  
  public long interval(String id, DateUnit dateUnit) {
    long intervalMs = this.isNano ? (interval(id) / 1000000L) : interval(id);
    if (DateUnit.MS == dateUnit)
      return intervalMs; 
    return intervalMs / dateUnit.getMillis();
  }
  
  public long intervalMs(String id) {
    return interval(id, DateUnit.MS);
  }
  
  public long intervalSecond(String id) {
    return interval(id, DateUnit.SECOND);
  }
  
  public long intervalMinute(String id) {
    return interval(id, DateUnit.MINUTE);
  }
  
  public long intervalHour(String id) {
    return interval(id, DateUnit.HOUR);
  }
  
  public long intervalDay(String id) {
    return interval(id, DateUnit.DAY);
  }
  
  public long intervalWeek(String id) {
    return interval(id, DateUnit.WEEK);
  }
  
  public String intervalPretty(String id) {
    return DateUtil.formatBetween(intervalMs(id));
  }
  
  private long getTime() {
    return this.isNano ? System.nanoTime() : System.currentTimeMillis();
  }
}
