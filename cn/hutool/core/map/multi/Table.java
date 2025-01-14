package cn.hutool.core.map.multi;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.lang.Opt;
import cn.hutool.core.lang.func.Consumer3;
import cn.hutool.core.map.MapUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface Table<R, C, V> extends Iterable<Table.Cell<R, C, V>> {
  default boolean contains(R rowKey, C columnKey) {
    return ((Boolean)Opt.ofNullable(getRow(rowKey)).map(map -> Boolean.valueOf(map.containsKey(columnKey)))
      .orElse(Boolean.valueOf(false))).booleanValue();
  }
  
  default boolean containsRow(R rowKey) {
    return ((Boolean)Opt.ofNullable(rowMap()).map(map -> Boolean.valueOf(map.containsKey(rowKey))).get()).booleanValue();
  }
  
  default Map<C, V> getRow(R rowKey) {
    return (Map<C, V>)Opt.ofNullable(rowMap()).map(map -> (Map)map.get(rowKey)).get();
  }
  
  default Set<R> rowKeySet() {
    return (Set<R>)Opt.ofNullable(rowMap()).map(Map::keySet).get();
  }
  
  default boolean containsColumn(C columnKey) {
    return ((Boolean)Opt.ofNullable(columnMap()).map(map -> Boolean.valueOf(map.containsKey(columnKey))).get()).booleanValue();
  }
  
  default Map<R, V> getColumn(C columnKey) {
    return (Map<R, V>)Opt.ofNullable(columnMap()).map(map -> (Map)map.get(columnKey)).get();
  }
  
  default Set<C> columnKeySet() {
    return (Set<C>)Opt.ofNullable(columnMap()).map(Map::keySet).get();
  }
  
  default List<C> columnKeys() {
    Map<C, Map<R, V>> columnMap = columnMap();
    if (MapUtil.isEmpty(columnMap))
      return ListUtil.empty(); 
    List<C> result = new ArrayList<>(columnMap.size());
    for (Map.Entry<C, Map<R, V>> cMapEntry : columnMap.entrySet())
      result.add(cMapEntry.getKey()); 
    return result;
  }
  
  default boolean containsValue(V value) {
    Collection<Map<C, V>> rows = (Collection<Map<C, V>>)Opt.ofNullable(rowMap()).map(Map::values).get();
    if (null != rows)
      for (Map<C, V> row : rows) {
        if (row.containsValue(value))
          return true; 
      }  
    return false;
  }
  
  default V get(R rowKey, C columnKey) {
    return (V)Opt.ofNullable(getRow(rowKey)).map(map -> map.get(columnKey)).get();
  }
  
  default void putAll(Table<? extends R, ? extends C, ? extends V> table) {
    if (null != table)
      for (Cell<? extends R, ? extends C, ? extends V> cell : table.cellSet())
        put(cell.getRowKey(), cell.getColumnKey(), cell.getValue());  
  }
  
  default int size() {
    Map<R, Map<C, V>> rowMap = rowMap();
    if (MapUtil.isEmpty(rowMap))
      return 0; 
    int size = 0;
    for (Map<C, V> map : rowMap.values())
      size += map.size(); 
    return size;
  }
  
  default void forEach(Consumer3<? super R, ? super C, ? super V> consumer) {
    for (Cell<R, C, V> cell : this)
      consumer.accept(cell.getRowKey(), cell.getColumnKey(), cell.getValue()); 
  }
  
  Map<R, Map<C, V>> rowMap();
  
  Map<C, Map<R, V>> columnMap();
  
  Collection<V> values();
  
  Set<Cell<R, C, V>> cellSet();
  
  V put(R paramR, C paramC, V paramV);
  
  V remove(R paramR, C paramC);
  
  boolean isEmpty();
  
  void clear();
  
  public static interface Cell<R, C, V> {
    R getRowKey();
    
    C getColumnKey();
    
    V getValue();
  }
}
