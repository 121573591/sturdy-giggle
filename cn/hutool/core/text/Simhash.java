package cn.hutool.core.text;

import cn.hutool.core.lang.hash.MurmurHash;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.StampedLock;

public class Simhash {
  private final int bitNum = 64;
  
  private final int fracCount;
  
  private final int fracBitNum;
  
  private final int hammingThresh;
  
  private final List<Map<String, List<Long>>> storage;
  
  private final StampedLock lock = new StampedLock();
  
  public Simhash() {
    this(4, 3);
  }
  
  public Simhash(int fracCount, int hammingThresh) {
    this.fracCount = fracCount;
    this.fracBitNum = 64 / fracCount;
    this.hammingThresh = hammingThresh;
    this.storage = new ArrayList<>(fracCount);
    for (int i = 0; i < fracCount; i++)
      this.storage.add(new HashMap<>()); 
  }
  
  public long hash(Collection<? extends CharSequence> segList) {
    getClass();
    int bitNum = 64;
    int[] weight = new int[bitNum];
    for (CharSequence seg : segList) {
      long wordHash = MurmurHash.hash64(seg);
      for (int j = 0; j < bitNum; j++) {
        if ((wordHash >> j & 0x1L) == 1L) {
          weight[j] = weight[j] + 1;
        } else {
          weight[j] = weight[j] - 1;
        } 
      } 
    } 
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < bitNum; i++)
      sb.append((weight[i] > 0) ? 1 : 0); 
    return (new BigInteger(sb.toString(), 2)).longValue();
  }
  
  public boolean equals(Collection<? extends CharSequence> segList) {
    long simhash = hash(segList);
    List<String> fracList = splitSimhash(Long.valueOf(simhash));
    int hammingThresh = this.hammingThresh;
    long stamp = this.lock.readLock();
    try {
      for (int i = 0; i < this.fracCount; i++) {
        String frac = fracList.get(i);
        Map<String, List<Long>> fracMap = this.storage.get(i);
        if (fracMap.containsKey(frac))
          for (Long simhash2 : fracMap.get(frac)) {
            if (hamming(Long.valueOf(simhash), simhash2) < hammingThresh)
              return true; 
          }  
      } 
    } finally {
      this.lock.unlockRead(stamp);
    } 
    return false;
  }
  
  public void store(Long simhash) {
    int fracCount = this.fracCount;
    List<Map<String, List<Long>>> storage = this.storage;
    List<String> lFrac = splitSimhash(simhash);
    long stamp = this.lock.writeLock();
    try {
      for (int i = 0; i < fracCount; i++) {
        String frac = lFrac.get(i);
        Map<String, List<Long>> fracMap = storage.get(i);
        if (fracMap.containsKey(frac)) {
          ((List<Long>)fracMap.get(frac)).add(simhash);
        } else {
          List<Long> ls = new ArrayList<>();
          ls.add(simhash);
          fracMap.put(frac, ls);
        } 
      } 
    } finally {
      this.lock.unlockWrite(stamp);
    } 
  }
  
  private int hamming(Long s1, Long s2) {
    getClass();
    int bitNum = 64;
    int dis = 0;
    for (int i = 0; i < bitNum; i++) {
      if ((s1.longValue() >> i & 0x1L) != (s2.longValue() >> i & 0x1L))
        dis++; 
    } 
    return dis;
  }
  
  private List<String> splitSimhash(Long simhash) {
    getClass();
    int bitNum = 64;
    int fracBitNum = this.fracBitNum;
    List<String> ls = new ArrayList<>();
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < bitNum; i++) {
      sb.append(simhash.longValue() >> i & 0x1L);
      if ((i + 1) % fracBitNum == 0) {
        ls.add(sb.toString());
        sb.setLength(0);
      } 
    } 
    return ls;
  }
}
