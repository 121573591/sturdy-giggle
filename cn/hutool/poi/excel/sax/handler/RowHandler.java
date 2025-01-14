package cn.hutool.poi.excel.sax.handler;

import java.util.List;
import org.apache.poi.ss.usermodel.CellStyle;

@FunctionalInterface
public interface RowHandler {
  void handle(int paramInt, long paramLong, List<Object> paramList);
  
  default void handleCell(int sheetIndex, long rowIndex, int cellIndex, Object value, CellStyle xssfCellStyle) {}
  
  default void doAfterAllAnalysed() {}
}
