package cn.hutool.core.map.multi;

import cn.hutool.core.builder.Builder;
import cn.hutool.core.collection.ComputeIter;
import cn.hutool.core.collection.IterUtil;
import cn.hutool.core.collection.TransIter;
import cn.hutool.core.map.AbsEntry;
import cn.hutool.core.map.MapUtil;
import java.lang.invoke.SerializedLambda;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RowKeyTable<R, C, V> extends AbsTable<R, C, V> {
  final Map<R, Map<C, V>> raw;
  
  final Builder<? extends Map<C, V>> columnBuilder;
  
  private Map<C, Map<R, V>> columnMap;
  
  private Set<C> columnKeySet;
  
  public RowKeyTable() {
    this(new HashMap<>());
  }
  
  public RowKeyTable(boolean isLinked) {
    this(MapUtil.newHashMap(isLinked), () -> MapUtil.newHashMap(isLinked));
  }
  
  public RowKeyTable(Map<R, Map<C, V>> raw) {
    this(raw, HashMap::new);
  }
  
  public RowKeyTable(Map<R, Map<C, V>> raw, Builder<? extends Map<C, V>> columnMapBuilder) {
    this.raw = raw;
    this.columnBuilder = (null == columnMapBuilder) ? HashMap::new : columnMapBuilder;
  }
  
  public Map<R, Map<C, V>> rowMap() {
    return this.raw;
  }
  
  public V put(R rowKey, C columnKey, V value) {
    return ((Map<C, V>)this.raw.computeIfAbsent(rowKey, key -> (Map)this.columnBuilder.build())).put(columnKey, value);
  }
  
  public V remove(R rowKey, C columnKey) {
    Map<C, V> map = getRow(rowKey);
    if (null == map)
      return null; 
    V value = map.remove(columnKey);
    if (map.isEmpty())
      this.raw.remove(rowKey); 
    return value;
  }
  
  public boolean isEmpty() {
    return this.raw.isEmpty();
  }
  
  public void clear() {
    this.raw.clear();
  }
  
  public boolean containsColumn(C columnKey) {
    if (columnKey == null)
      return false; 
    for (Map<C, V> map : this.raw.values()) {
      if (null != map && map.containsKey(columnKey))
        return true; 
    } 
    return false;
  }
  
  public Map<C, Map<R, V>> columnMap() {
    Map<C, Map<R, V>> result = this.columnMap;
    return (result == null) ? (this.columnMap = new ColumnMap()) : result;
  }
  
  private class ColumnMap extends AbstractMap<C, Map<R, V>> {
    private ColumnMap() {}
    
    public Set<Map.Entry<C, Map<R, V>>> entrySet() {
      return new RowKeyTable.ColumnMapEntrySet();
    }
  }
  
  private class ColumnMapEntrySet extends AbstractSet<Map.Entry<C, Map<R, V>>> {
    private final Set<C> columnKeySet = RowKeyTable.this.columnKeySet();
    
    public Iterator<Map.Entry<C, Map<R, V>>> iterator() {
      return (Iterator<Map.Entry<C, Map<R, V>>>)new TransIter(this.columnKeySet.iterator(), c -> MapUtil.entry(c, RowKeyTable.this.getColumn(c)));
    }
    
    public int size() {
      return this.columnKeySet.size();
    }
    
    private ColumnMapEntrySet() {}
  }
  
  public Set<C> columnKeySet() {
    Set<C> result = this.columnKeySet;
    return (result == null) ? (this.columnKeySet = new ColumnKeySet()) : result;
  }
  
  private class ColumnKeySet extends AbstractSet<C> {
    private ColumnKeySet() {}
    
    public Iterator<C> iterator() {
      return (Iterator<C>)new RowKeyTable.ColumnKeyIterator();
    }
    
    public int size() {
      return IterUtil.size(iterator());
    }
  }
  
  private class ColumnKeyIterator extends ComputeIter<C> {
    final Map<C, V> seen = (Map<C, V>)RowKeyTable.this.columnBuilder.build();
    
    final Iterator<Map<C, V>> mapIterator = RowKeyTable.this.raw.values().iterator();
    
    Iterator<Map.Entry<C, V>> entryIterator = IterUtil.empty();
    
    protected C computeNext() {
      while (true) {
        while (this.entryIterator.hasNext()) {
          Map.Entry<C, V> entry = this.entryIterator.next();
          if (false == this.seen.containsKey(entry.getKey())) {
            this.seen.put(entry.getKey(), entry.getValue());
            return entry.getKey();
          } 
        } 
        if (this.mapIterator.hasNext()) {
          this.entryIterator = ((Map<C, V>)this.mapIterator.next()).entrySet().iterator();
          continue;
        } 
        break;
      } 
      return null;
    }
    
    private ColumnKeyIterator() {}
  }
  
  public List<C> columnKeys() {
    Collection<Map<C, V>> values = this.raw.values();
    List<C> result = new ArrayList<>(values.size() * 16);
    for (Map<C, V> map : values)
      map.forEach((key, value) -> result.add(key)); 
    return result;
  }
  
  public Map<R, V> getColumn(C columnKey) {
    return new Column(columnKey);
  }
  
  private class Column extends AbstractMap<R, V> {
    final C columnKey;
    
    Column(C columnKey) {
      this.columnKey = columnKey;
    }
    
    public Set<Map.Entry<R, V>> entrySet() {
      return new EntrySet();
    }
    
    private class EntrySet extends AbstractSet<Map.Entry<R, V>> {
      private EntrySet() {}
      
      public Iterator<Map.Entry<R, V>> iterator() {
        return (Iterator<Map.Entry<R, V>>)new RowKeyTable.Column.EntrySetIterator();
      }
      
      public int size() {
        int size = 0;
        for (Map<C, V> map : (Iterable<Map<C, V>>)RowKeyTable.this.raw.values()) {
          if (map.containsKey(RowKeyTable.Column.this.columnKey))
            size++; 
        } 
        return size;
      }
    }
    
    private class EntrySetIterator extends ComputeIter<Map.Entry<R, V>> {
      final Iterator<Map.Entry<R, Map<C, V>>> iterator = RowKeyTable.this.raw.entrySet().iterator();
      
      protected Map.Entry<R, V> computeNext() {
        while (this.iterator.hasNext()) {
          final Map.Entry<R, Map<C, V>> entry = this.iterator.next();
          if (((Map)entry.getValue()).containsKey(RowKeyTable.Column.this.columnKey))
            return (Map.Entry<R, V>)new AbsEntry<R, V>() {
                public R getKey() {
                  return (R)entry.getKey();
                }
                
                public V getValue() {
                  return (V)((Map)entry.getValue()).get(RowKeyTable.Column.this.columnKey);
                }
                
                public V setValue(V value) {
                  return ((Map<C, V>)entry.getValue()).put(RowKeyTable.Column.this.columnKey, value);
                }
              }; 
        } 
        return null;
      }
      
      private EntrySetIterator() {}
    }
  }
}
