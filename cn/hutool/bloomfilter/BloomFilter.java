package cn.hutool.bloomfilter;

import java.io.Serializable;

public interface BloomFilter extends Serializable {
  boolean contains(String paramString);
  
  boolean add(String paramString);
}
