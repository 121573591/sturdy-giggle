package com.google.protobuf;

import java.util.Iterator;
import java.util.Map;

public class LazyField extends LazyFieldLite {
  private final MessageLite defaultInstance;
  
  public LazyField(MessageLite defaultInstance, ExtensionRegistryLite extensionRegistry, ByteString bytes) {
    super(extensionRegistry, bytes);
    this.defaultInstance = defaultInstance;
  }
  
  public boolean containsDefaultInstance() {
    return (super.containsDefaultInstance() || this.value == this.defaultInstance);
  }
  
  public MessageLite getValue() {
    return getValue(this.defaultInstance);
  }
  
  public int hashCode() {
    return getValue().hashCode();
  }
  
  public boolean equals(Object obj) {
    return getValue().equals(obj);
  }
  
  public String toString() {
    return getValue().toString();
  }
  
  static class LazyEntry<K> implements Map.Entry<K, Object> {
    private Map.Entry<K, LazyField> entry;
    
    private LazyEntry(Map.Entry<K, LazyField> entry) {
      this.entry = entry;
    }
    
    public K getKey() {
      return this.entry.getKey();
    }
    
    public Object getValue() {
      LazyField field = this.entry.getValue();
      if (field == null)
        return null; 
      return field.getValue();
    }
    
    public LazyField getField() {
      return this.entry.getValue();
    }
    
    public Object setValue(Object value) {
      if (!(value instanceof MessageLite))
        throw new IllegalArgumentException("LazyField now only used for MessageSet, and the value of MessageSet must be an instance of MessageLite"); 
      return ((LazyField)this.entry.getValue()).setValue((MessageLite)value);
    }
  }
  
  static class LazyIterator<K> implements Iterator<Map.Entry<K, Object>> {
    private Iterator<Map.Entry<K, Object>> iterator;
    
    public LazyIterator(Iterator<Map.Entry<K, Object>> iterator) {
      this.iterator = iterator;
    }
    
    public boolean hasNext() {
      return this.iterator.hasNext();
    }
    
    public Map.Entry<K, Object> next() {
      Map.Entry<K, ?> entry = this.iterator.next();
      if (entry.getValue() instanceof LazyField)
        return new LazyField.LazyEntry<>(entry); 
      return (Map.Entry)entry;
    }
    
    public void remove() {
      this.iterator.remove();
    }
  }
}
