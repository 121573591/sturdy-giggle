package com.fasterxml.jackson.core.sym;

public abstract class Name {
  protected final String _name;
  
  protected final int _hashCode;
  
  protected Name(String name, int hashCode) {
    this._name = name;
    this._hashCode = hashCode;
  }
  
  public String getName() {
    return this._name;
  }
  
  public abstract boolean equals(int paramInt);
  
  public abstract boolean equals(int paramInt1, int paramInt2);
  
  public abstract boolean equals(int paramInt1, int paramInt2, int paramInt3);
  
  public abstract boolean equals(int[] paramArrayOfint, int paramInt);
  
  public String toString() {
    return this._name;
  }
  
  public final int hashCode() {
    return this._hashCode;
  }
  
  public boolean equals(Object o) {
    return (o == this);
  }
}
