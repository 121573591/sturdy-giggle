package cn.hutool.poi.excel.sax.handler;

import cn.hutool.core.collection.IterUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.convert.Convert;
import java.lang.invoke.SerializedLambda;
import java.util.List;
import java.util.Map;

public abstract class MapRowHandler extends AbstractRowHandler<Map<String, Object>> {
  private final int headerRowIndex;
  
  List<String> headerList;
  
  public MapRowHandler(int headerRowIndex, int startRowIndex, int endRowIndex) {
    super(startRowIndex, endRowIndex);
    this.headerRowIndex = headerRowIndex;
    this.convertFunc = (rowList -> IterUtil.toMap(this.headerList, rowList, true));
  }
  
  public void handle(int sheetIndex, long rowIndex, List<Object> rowCells) {
    if (rowIndex == this.headerRowIndex) {
      this.headerList = ListUtil.unmodifiable(Convert.toList(String.class, rowCells));
      return;
    } 
    super.handle(sheetIndex, rowIndex, rowCells);
  }
}
