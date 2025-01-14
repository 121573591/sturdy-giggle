package cn.hutool.poi.excel.cell.values;

import cn.hutool.core.date.DateUtil;
import cn.hutool.poi.excel.ExcelDateUtil;
import cn.hutool.poi.excel.cell.CellValue;
import java.time.LocalDateTime;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.NumberToTextConverter;

public class NumericCellValue implements CellValue<Object> {
  private final Cell cell;
  
  public NumericCellValue(Cell cell) {
    this.cell = cell;
  }
  
  public Object getValue() {
    double value = this.cell.getNumericCellValue();
    CellStyle style = this.cell.getCellStyle();
    if (null != style) {
      if (ExcelDateUtil.isDateFormat(this.cell)) {
        LocalDateTime dateCellValue = this.cell.getLocalDateTimeCellValue();
        if (1899 == dateCellValue.getYear())
          return dateCellValue.toLocalTime(); 
        return DateUtil.date(dateCellValue);
      } 
      String format = style.getDataFormatString();
      if (null != format && format.indexOf('.') < 0) {
        long longPart = (long)value;
        if (longPart == value)
          return Long.valueOf(longPart); 
      } 
    } 
    return Double.valueOf(Double.parseDouble(NumberToTextConverter.toText(value)));
  }
}
