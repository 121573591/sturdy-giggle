package cn.hutool.bloomfilter;

import cn.hutool.bloomfilter.filter.DefaultFilter;
import cn.hutool.bloomfilter.filter.ELFFilter;
import cn.hutool.bloomfilter.filter.JSFilter;
import cn.hutool.bloomfilter.filter.PJWFilter;
import cn.hutool.bloomfilter.filter.SDBMFilter;
import cn.hutool.core.util.NumberUtil;

public class BitMapBloomFilter implements BloomFilter {
  private static final long serialVersionUID = 1L;
  
  private BloomFilter[] filters;
  
  public BitMapBloomFilter(int m) {
    long mNum = NumberUtil.div(String.valueOf(m), String.valueOf(5)).longValue();
    long size = mNum * 1024L * 1024L * 8L;
    this.filters = new BloomFilter[] { (BloomFilter)new DefaultFilter(size), (BloomFilter)new ELFFilter(size), (BloomFilter)new JSFilter(size), (BloomFilter)new PJWFilter(size), (BloomFilter)new SDBMFilter(size) };
  }
  
  public BitMapBloomFilter(int m, BloomFilter... filters) {
    this(m);
    this.filters = filters;
  }
  
  public boolean add(String str) {
    boolean flag = false;
    for (BloomFilter filter : this.filters)
      flag |= filter.add(str); 
    return flag;
  }
  
  public boolean contains(String str) {
    for (BloomFilter filter : this.filters) {
      if (!filter.contains(str))
        return false; 
    } 
    return true;
  }
}
