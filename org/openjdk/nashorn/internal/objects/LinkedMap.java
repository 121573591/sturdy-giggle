package org.openjdk.nashorn.internal.objects;

import java.util.HashMap;
import java.util.Map;
import org.openjdk.nashorn.internal.runtime.Undefined;

public class LinkedMap {
  private final Map<Object, Node> data = new HashMap<>();
  
  private final Node head = new Node();
  
  static class Node {
    private final Object key;
    
    private volatile Object value;
    
    private volatile boolean alive = true;
    
    private volatile Node prev;
    
    private volatile Node next;
    
    private Node() {
      this(null, null);
      this.next = this;
      this.prev = this;
    }
    
    private Node(Object key, Object value) {
      this.key = key;
      this.value = value;
    }
    
    public Object getKey() {
      return this.key;
    }
    
    public Object getValue() {
      return this.value;
    }
    
    void setValue(Object value) {
      this.value = value;
    }
  }
  
  class LinkedMapIterator {
    private LinkedMap.Node cursor = LinkedMap.this.head;
    
    public LinkedMap.Node next() {
      if (this.cursor != null) {
        while (!this.cursor.alive) {
          assert this.cursor != LinkedMap.this.head;
          this.cursor = this.cursor.prev;
        } 
        this.cursor = this.cursor.next;
        if (this.cursor == LinkedMap.this.head)
          this.cursor = null; 
      } 
      return this.cursor;
    }
  }
  
  public void set(Object key, Object value) {
    Node node = this.data.get(key);
    if (node != null) {
      node.setValue(value);
    } else {
      node = new Node(key, value);
      this.data.put(key, node);
      link(node);
    } 
  }
  
  public Object get(Object key) {
    Node node = this.data.get(key);
    return (node == null) ? Undefined.getUndefined() : node.getValue();
  }
  
  public boolean has(Object key) {
    return this.data.containsKey(key);
  }
  
  public boolean delete(Object key) {
    Node node = this.data.remove(key);
    if (node != null) {
      unlink(node);
      return true;
    } 
    return false;
  }
  
  public void clear() {
    this.data.clear();
    for (Node node = this.head.next; node != this.head; node = node.next)
      node.alive = false; 
    this.head.next = this.head;
    this.head.prev = this.head;
  }
  
  public int size() {
    return this.data.size();
  }
  
  public LinkedMapIterator getIterator() {
    return new LinkedMapIterator();
  }
  
  private void link(Node newNode) {
    newNode.next = this.head;
    newNode.prev = this.head.prev;
    newNode.prev.next = newNode;
    this.head.prev = newNode;
  }
  
  private void unlink(Node oldNode) {
    oldNode.prev.next = oldNode.next;
    oldNode.next.prev = oldNode.prev;
    oldNode.alive = false;
  }
}
