package cn.hutool.setting;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class GroupedMap extends LinkedHashMap<String, LinkedHashMap<String, String>> {
  private static final long serialVersionUID = -7777365130776081931L;
  
  private final ReentrantReadWriteLock cacheLock = new ReentrantReadWriteLock();
  
  private final ReentrantReadWriteLock.ReadLock readLock = this.cacheLock.readLock();
  
  private final ReentrantReadWriteLock.WriteLock writeLock = this.cacheLock.writeLock();
  
  private int size = -1;
  
  public String get(String group, String key) {
    this.readLock.lock();
    try {
      LinkedHashMap<String, String> map = get(StrUtil.nullToEmpty(group));
      if (MapUtil.isNotEmpty(map))
        return map.get(key); 
    } finally {
      this.readLock.unlock();
    } 
    return null;
  }
  
  public LinkedHashMap<String, String> get(Object key) {
    this.readLock.lock();
    try {
      return super.get(key);
    } finally {
      this.readLock.unlock();
    } 
  }
  
  public int size() {
    this.writeLock.lock();
    try {
      if (this.size < 0) {
        this.size = 0;
        for (LinkedHashMap<String, String> value : values())
          this.size += value.size(); 
      } 
    } finally {
      this.writeLock.unlock();
    } 
    return this.size;
  }
  
  public String put(String group, String key, String value) {
    group = StrUtil.nullToEmpty(group).trim();
    this.writeLock.lock();
    try {
      LinkedHashMap<String, String> valueMap = computeIfAbsent(group, k -> new LinkedHashMap<>());
      this.size = -1;
      return valueMap.put(key, value);
    } finally {
      this.writeLock.unlock();
    } 
  }
  
  public GroupedMap putAll(String group, Map<? extends String, ? extends String> m) {
    for (Map.Entry<? extends String, ? extends String> entry : m.entrySet())
      put(group, entry.getKey(), entry.getValue()); 
    return this;
  }
  
  public String remove(String group, String key) {
    group = StrUtil.nullToEmpty(group).trim();
    this.writeLock.lock();
    try {
      LinkedHashMap<String, String> valueMap = get(group);
      if (MapUtil.isNotEmpty(valueMap))
        return valueMap.remove(key); 
    } finally {
      this.writeLock.unlock();
    } 
    return null;
  }
  
  public boolean isEmpty(String group) {
    group = StrUtil.nullToEmpty(group).trim();
    this.readLock.lock();
    try {
      LinkedHashMap<String, String> valueMap = get(group);
      if (MapUtil.isNotEmpty(valueMap))
        return valueMap.isEmpty(); 
    } finally {
      this.readLock.unlock();
    } 
    return true;
  }
  
  public boolean isEmpty() {
    return (size() == 0);
  }
  
  public boolean containsKey(String group, String key) {
    group = StrUtil.nullToEmpty(group).trim();
    this.readLock.lock();
    try {
      LinkedHashMap<String, String> valueMap = get(group);
      if (MapUtil.isNotEmpty(valueMap))
        return valueMap.containsKey(key); 
    } finally {
      this.readLock.unlock();
    } 
    return false;
  }
  
  public boolean containsValue(String group, String value) {
    group = StrUtil.nullToEmpty(group).trim();
    this.readLock.lock();
    try {
      LinkedHashMap<String, String> valueMap = get(group);
      if (MapUtil.isNotEmpty(valueMap))
        return valueMap.containsValue(value); 
    } finally {
      this.readLock.unlock();
    } 
    return false;
  }
  
  public GroupedMap clear(String group) {
    group = StrUtil.nullToEmpty(group).trim();
    this.writeLock.lock();
    try {
      LinkedHashMap<String, String> valueMap = get(group);
      if (MapUtil.isNotEmpty(valueMap))
        valueMap.clear(); 
    } finally {
      this.writeLock.unlock();
    } 
    return this;
  }
  
  public Set<String> keySet() {
    this.readLock.lock();
    try {
      return super.keySet();
    } finally {
      this.readLock.unlock();
    } 
  }
  
  public Set<String> keySet(String group) {
    group = StrUtil.nullToEmpty(group).trim();
    this.readLock.lock();
    try {
      LinkedHashMap<String, String> valueMap = get(group);
      if (MapUtil.isNotEmpty(valueMap))
        return valueMap.keySet(); 
    } finally {
      this.readLock.unlock();
    } 
    return Collections.emptySet();
  }
  
  public Collection<String> values(String group) {
    group = StrUtil.nullToEmpty(group).trim();
    this.readLock.lock();
    try {
      LinkedHashMap<String, String> valueMap = get(group);
      if (MapUtil.isNotEmpty(valueMap))
        return valueMap.values(); 
    } finally {
      this.readLock.unlock();
    } 
    return Collections.emptyList();
  }
  
  public Set<Map.Entry<String, LinkedHashMap<String, String>>> entrySet() {
    this.readLock.lock();
    try {
      return super.entrySet();
    } finally {
      this.readLock.unlock();
    } 
  }
  
  public Set<Map.Entry<String, String>> entrySet(String group) {
    group = StrUtil.nullToEmpty(group).trim();
    this.readLock.lock();
    try {
      LinkedHashMap<String, String> valueMap = get(group);
      if (MapUtil.isNotEmpty(valueMap))
        return valueMap.entrySet(); 
    } finally {
      this.readLock.unlock();
    } 
    return Collections.emptySet();
  }
  
  public String toString() {
    this.readLock.lock();
    try {
      return super.toString();
    } finally {
      this.readLock.unlock();
    } 
  }
}
