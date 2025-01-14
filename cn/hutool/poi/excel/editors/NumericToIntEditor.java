package cn.hutool.poi.excel.editors;

import cn.hutool.poi.excel.cell.CellEditor;
import org.apache.poi.ss.usermodel.Cell;

public class NumericToIntEditor implements CellEditor {
  public Object edit(Cell cell, Object value) {
    if (value instanceof Number)
      return Integer.valueOf(((Number)value).intValue()); 
    return value;
  }
}
