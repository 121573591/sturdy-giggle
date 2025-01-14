package com.google.protobuf;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MapFieldBuilder<KeyT, MessageOrBuilderT extends MessageOrBuilder, MessageT extends MessageOrBuilderT, BuilderT extends MessageOrBuilderT> extends MapFieldReflectionAccessor {
  Map<KeyT, MessageOrBuilderT> builderMap = new LinkedHashMap<>();
  
  Map<KeyT, MessageT> messageMap = null;
  
  List<Message> messageList = null;
  
  Converter<KeyT, MessageOrBuilderT, MessageT> converter;
  
  public MapFieldBuilder(Converter<KeyT, MessageOrBuilderT, MessageT> converter) {
    this.converter = converter;
  }
  
  private List<MapEntry<KeyT, MessageT>> getMapEntryList() {
    ArrayList<MapEntry<KeyT, MessageT>> list = new ArrayList<>(this.messageList.size());
    Class<?> valueClass = ((MessageOrBuilder)this.converter.defaultEntry().getValue()).getClass();
    for (Message entry : this.messageList) {
      MapEntry<KeyT, ?> typedEntry = (MapEntry<KeyT, ?>)entry;
      if (valueClass.isInstance(typedEntry.getValue())) {
        list.add(typedEntry);
        continue;
      } 
      list.add(this.converter.defaultEntry().toBuilder().mergeFrom(entry).build());
    } 
    return list;
  }
  
  public Map<KeyT, MessageOrBuilderT> ensureBuilderMap() {
    if (this.builderMap != null)
      return this.builderMap; 
    if (this.messageMap != null) {
      this.builderMap = new LinkedHashMap<>(this.messageMap.size());
      for (Map.Entry<KeyT, MessageT> entry : this.messageMap.entrySet())
        this.builderMap.put(entry.getKey(), (MessageOrBuilderT)entry.getValue()); 
      this.messageMap = null;
      return this.builderMap;
    } 
    this.builderMap = new LinkedHashMap<>(this.messageList.size());
    for (MapEntry<KeyT, MessageT> entry : getMapEntryList())
      this.builderMap.put(entry.getKey(), (MessageOrBuilderT)entry.getValue()); 
    this.messageList = null;
    return this.builderMap;
  }
  
  public List<Message> ensureMessageList() {
    if (this.messageList != null)
      return this.messageList; 
    if (this.builderMap != null) {
      this.messageList = new ArrayList<>(this.builderMap.size());
      for (Map.Entry<KeyT, MessageOrBuilderT> entry : this.builderMap.entrySet())
        this.messageList.add(this.converter
            .defaultEntry().toBuilder()
            .setKey(entry.getKey())
            .setValue(this.converter.build(entry.getValue()))
            .build()); 
      this.builderMap = null;
      return this.messageList;
    } 
    this.messageList = new ArrayList<>(this.messageMap.size());
    for (Map.Entry<KeyT, MessageT> entry : this.messageMap.entrySet())
      this.messageList.add(this.converter
          .defaultEntry().toBuilder()
          .setKey(entry.getKey())
          .setValue((MessageOrBuilder)entry.getValue())
          .build()); 
    this.messageMap = null;
    return this.messageList;
  }
  
  public Map<KeyT, MessageT> ensureMessageMap() {
    this.messageMap = populateMutableMap();
    this.builderMap = null;
    this.messageList = null;
    return this.messageMap;
  }
  
  public Map<KeyT, MessageT> getImmutableMap() {
    return new MapField.MutabilityAwareMap<>(MutabilityOracle.IMMUTABLE, populateMutableMap());
  }
  
  private Map<KeyT, MessageT> populateMutableMap() {
    if (this.messageMap != null)
      return this.messageMap; 
    if (this.builderMap != null) {
      Map<KeyT, MessageT> map = new LinkedHashMap<>(this.builderMap.size());
      for (Map.Entry<KeyT, MessageOrBuilderT> entry : this.builderMap.entrySet())
        map.put(entry.getKey(), this.converter.build(entry.getValue())); 
      return map;
    } 
    Map<KeyT, MessageT> toReturn = new LinkedHashMap<>(this.messageList.size());
    for (MapEntry<KeyT, MessageT> entry : getMapEntryList())
      toReturn.put(entry.getKey(), entry.getValue()); 
    return toReturn;
  }
  
  public void mergeFrom(MapField<KeyT, MessageT> other) {
    ensureBuilderMap().putAll(MapFieldLite.copy(other.getMap()));
  }
  
  public void clear() {
    this.builderMap = new LinkedHashMap<>();
    this.messageMap = null;
    this.messageList = null;
  }
  
  private boolean typedEquals(MapFieldBuilder<KeyT, MessageOrBuilderT, MessageT, BuilderT> other) {
    return MapFieldLite.equals(
        ensureBuilderMap(), other.ensureBuilderMap());
  }
  
  public boolean equals(Object object) {
    if (!(object instanceof MapFieldBuilder))
      return false; 
    return typedEquals((MapFieldBuilder<KeyT, MessageOrBuilderT, MessageT, BuilderT>)object);
  }
  
  public int hashCode() {
    return MapFieldLite.calculateHashCodeForMap(ensureBuilderMap());
  }
  
  public MapFieldBuilder<KeyT, MessageOrBuilderT, MessageT, BuilderT> copy() {
    MapFieldBuilder<KeyT, MessageOrBuilderT, MessageT, BuilderT> clone = new MapFieldBuilder(this.converter);
    clone.ensureBuilderMap().putAll(ensureBuilderMap());
    return clone;
  }
  
  public MapField<KeyT, MessageT> build(MapEntry<KeyT, MessageT> defaultEntry) {
    MapField<KeyT, MessageT> mapField = MapField.newMapField(defaultEntry);
    Map<KeyT, MessageT> map = mapField.getMutableMap();
    for (Map.Entry<KeyT, MessageOrBuilderT> entry : ensureBuilderMap().entrySet())
      map.put(entry.getKey(), this.converter.build(entry.getValue())); 
    mapField.makeImmutable();
    return mapField;
  }
  
  List<Message> getList() {
    return ensureMessageList();
  }
  
  List<Message> getMutableList() {
    return ensureMessageList();
  }
  
  Message getMapEntryMessageDefaultInstance() {
    return this.converter.defaultEntry();
  }
  
  public static interface Converter<KeyT, MessageOrBuilderT extends MessageOrBuilder, MessageT extends MessageOrBuilderT> {
    MessageT build(MessageOrBuilderT param1MessageOrBuilderT);
    
    MapEntry<KeyT, MessageT> defaultEntry();
  }
}
