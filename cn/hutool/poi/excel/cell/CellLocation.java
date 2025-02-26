package cn.hutool.poi.excel.cell;

import java.io.Serializable;
import java.util.Objects;

public class CellLocation implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private int x;
  
  private int y;
  
  public CellLocation(int x, int y) {
    this.x = x;
    this.y = y;
  }
  
  public int getX() {
    return this.x;
  }
  
  public void setX(int x) {
    this.x = x;
  }
  
  public int getY() {
    return this.y;
  }
  
  public void setY(int y) {
    this.y = y;
  }
  
  public boolean equals(Object o) {
    if (this == o)
      return true; 
    if (o == null || getClass() != o.getClass())
      return false; 
    CellLocation that = (CellLocation)o;
    return (this.x == that.x && this.y == that.y);
  }
  
  public int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(this.x), Integer.valueOf(this.y) });
  }
  
  public String toString() {
    return "CellLocation{x=" + this.x + ", y=" + this.y + '}';
  }
}
