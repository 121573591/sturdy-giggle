package cn.hutool.poi.excel.sax.handler;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.IterUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.Assert;
import java.lang.invoke.SerializedLambda;
import java.util.List;

public abstract class BeanRowHandler<T> extends AbstractRowHandler<T> {
  private final int headerRowIndex;
  
  List<String> headerList;
  
  public BeanRowHandler(int headerRowIndex, int startRowIndex, int endRowIndex, Class<T> clazz) {
    super(startRowIndex, endRowIndex);
    Assert.isTrue((headerRowIndex <= startRowIndex), "Header row must before the start row!", new Object[0]);
    this.headerRowIndex = headerRowIndex;
    this.convertFunc = (rowList -> BeanUtil.toBean(IterUtil.toMap(this.headerList, rowList), clazz));
  }
  
  public void handle(int sheetIndex, long rowIndex, List<Object> rowCells) {
    if (rowIndex == this.headerRowIndex) {
      this.headerList = ListUtil.unmodifiable(Convert.toList(String.class, rowCells));
      return;
    } 
    super.handle(sheetIndex, rowIndex, rowCells);
  }
}
