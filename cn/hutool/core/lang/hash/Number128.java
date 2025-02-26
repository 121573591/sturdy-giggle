package cn.hutool.core.lang.hash;

import java.util.Objects;

public class Number128 extends Number {
  private static final long serialVersionUID = 1L;
  
  private long lowValue;
  
  private long highValue;
  
  public Number128(long lowValue, long highValue) {
    this.lowValue = lowValue;
    this.highValue = highValue;
  }
  
  public long getLowValue() {
    return this.lowValue;
  }
  
  public void setLowValue(long lowValue) {
    this.lowValue = lowValue;
  }
  
  public long getHighValue() {
    return this.highValue;
  }
  
  public void setHighValue(long hiValue) {
    this.highValue = hiValue;
  }
  
  public long[] getLongArray() {
    return new long[] { this.lowValue, this.highValue };
  }
  
  public int intValue() {
    return (int)longValue();
  }
  
  public long longValue() {
    return this.lowValue;
  }
  
  public float floatValue() {
    return (float)longValue();
  }
  
  public double doubleValue() {
    return longValue();
  }
  
  public boolean equals(Object o) {
    if (this == o)
      return true; 
    if (o == null || getClass() != o.getClass())
      return false; 
    Number128 number128 = (Number128)o;
    return (this.lowValue == number128.lowValue && this.highValue == number128.highValue);
  }
  
  public int hashCode() {
    return Objects.hash(new Object[] { Long.valueOf(this.lowValue), Long.valueOf(this.highValue) });
  }
}
