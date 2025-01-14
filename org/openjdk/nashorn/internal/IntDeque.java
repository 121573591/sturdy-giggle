package org.openjdk.nashorn.internal;

public class IntDeque {
  private int[] deque = new int[16];
  
  private int nextFree = 0;
  
  public void push(int value) {
    if (this.nextFree == this.deque.length) {
      int[] newDeque = new int[this.nextFree * 2];
      System.arraycopy(this.deque, 0, newDeque, 0, this.nextFree);
      this.deque = newDeque;
    } 
    this.deque[this.nextFree++] = value;
  }
  
  public int pop() {
    return this.deque[--this.nextFree];
  }
  
  public int peek() {
    return this.deque[this.nextFree - 1];
  }
  
  public int getAndIncrement() {
    this.deque[this.nextFree - 1] = this.deque[this.nextFree - 1] + 1;
    return this.deque[this.nextFree - 1];
  }
  
  public int decrementAndGet() {
    this.deque[this.nextFree - 1] = this.deque[this.nextFree - 1] - 1;
    return this.deque[this.nextFree - 1] - 1;
  }
  
  public boolean isEmpty() {
    return (this.nextFree == 0);
  }
}
