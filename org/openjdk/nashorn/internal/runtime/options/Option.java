package org.openjdk.nashorn.internal.runtime.options;

public class Option<T> {
  protected T value;
  
  Option(T value) {
    this.value = value;
  }
  
  public T getValue() {
    return this.value;
  }
  
  public String toString() {
    return "" + getValue() + " [" + getValue() + "]";
  }
}
