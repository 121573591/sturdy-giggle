package cn.hutool.poi.excel.sax;

import cn.hutool.core.text.StrBuilder;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.cell.FormulaCellValue;
import cn.hutool.poi.excel.sax.handler.RowHandler;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.model.SharedStrings;
import org.apache.poi.xssf.model.StylesTable;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class SheetDataSaxHandler extends DefaultHandler {
  protected StylesTable stylesTable;
  
  protected SharedStrings sharedStrings;
  
  protected int sheetIndex;
  
  protected int index;
  
  private int curCell;
  
  private CellDataType cellDataType;
  
  private long rowNumber;
  
  private String curCoordinate;
  
  private ElementName curElementName;
  
  private String preCoordinate;
  
  private String maxCellCoordinate;
  
  private XSSFCellStyle xssfCellStyle;
  
  private String numFmtString;
  
  private boolean isInSheetData;
  
  private final StrBuilder lastContent = StrUtil.strBuilder();
  
  private final StrBuilder lastFormula = StrUtil.strBuilder();
  
  private List<Object> rowCellList = new ArrayList();
  
  protected RowHandler rowHandler;
  
  public SheetDataSaxHandler(RowHandler rowHandler) {
    this.rowHandler = rowHandler;
  }
  
  public void setRowHandler(RowHandler rowHandler) {
    this.rowHandler = rowHandler;
  }
  
  public void startElement(String uri, String localName, String qName, Attributes attributes) {
    if ("sheetData".equals(qName)) {
      this.isInSheetData = true;
      return;
    } 
    if (false == this.isInSheetData)
      return; 
    ElementName name = ElementName.of(qName);
    this.curElementName = name;
    if (null != name)
      switch (name) {
        case row:
          startRow(attributes);
          break;
        case c:
          startCell(attributes);
          break;
      }  
  }
  
  public void endElement(String uri, String localName, String qName) {
    if ("sheetData".equals(qName)) {
      this.isInSheetData = false;
      return;
    } 
    if (false == this.isInSheetData)
      return; 
    this.curElementName = null;
    if (ElementName.c.match(qName)) {
      endCell();
    } else if (ElementName.row.match(qName)) {
      endRow();
    } 
  }
  
  public void characters(char[] ch, int start, int length) {
    if (false == this.isInSheetData)
      return; 
    ElementName elementName = this.curElementName;
    if (null != elementName) {
      switch (elementName) {
        case v:
          this.lastContent.append(ch, start, length);
          break;
        case f:
          this.lastFormula.append(ch, start, length);
          break;
      } 
    } else {
      this.lastContent.append(ch, start, length);
    } 
  }
  
  private void startRow(Attributes attributes) {
    String rValue = AttributeName.r.getValue(attributes);
    this.rowNumber = (null == rValue) ? -1L : (Long.parseLong(rValue) - 1L);
  }
  
  private void startCell(Attributes attributes) {
    String tempCurCoordinate = AttributeName.r.getValue(attributes);
    if (this.preCoordinate == null) {
      this.preCoordinate = String.valueOf('@');
    } else {
      this.preCoordinate = this.curCoordinate;
    } 
    this.curCoordinate = tempCurCoordinate;
    setCellType(attributes);
    this.lastContent.reset();
    this.lastFormula.reset();
  }
  
  private void endRow() {
    if (this.index == 0)
      this.maxCellCoordinate = this.curCoordinate; 
    if (this.maxCellCoordinate != null)
      fillBlankCell(this.curCoordinate, this.maxCellCoordinate, true); 
    this.rowHandler.handle(this.sheetIndex, this.rowNumber, this.rowCellList);
    this.rowCellList = new ArrayList(this.curCell + 1);
    this.index++;
    this.curCell = 0;
    this.curCoordinate = null;
    this.preCoordinate = null;
  }
  
  private void endCell() {
    fillBlankCell(this.preCoordinate, this.curCoordinate, false);
    String contentStr = StrUtil.trim((CharSequence)this.lastContent);
    Object value = ExcelSaxUtil.getDataValue(this.cellDataType, contentStr, this.sharedStrings, this.numFmtString);
    if (false == this.lastFormula.isEmpty())
      value = new FormulaCellValue(StrUtil.trim((CharSequence)this.lastFormula), value); 
    addCellValue(this.curCell++, value);
  }
  
  private void addCellValue(int index, Object value) {
    this.rowCellList.add(index, value);
    this.rowHandler.handleCell(this.sheetIndex, this.rowNumber, index, value, (CellStyle)this.xssfCellStyle);
  }
  
  private void fillBlankCell(String preCoordinate, String curCoordinate, boolean isEnd) {
    if (false == curCoordinate.equals(preCoordinate)) {
      int len = ExcelSaxUtil.countNullCell(preCoordinate, curCoordinate);
      if (isEnd)
        len++; 
      while (len-- > 0)
        addCellValue(this.curCell++, ""); 
    } 
  }
  
  private void setCellType(Attributes attributes) {
    this.numFmtString = "";
    this.cellDataType = CellDataType.of(AttributeName.t.getValue(attributes));
    if (null != this.stylesTable) {
      String xfIndexStr = AttributeName.s.getValue(attributes);
      if (null != xfIndexStr) {
        this.xssfCellStyle = this.stylesTable.getStyleAt(Integer.parseInt(xfIndexStr));
        int numFmtIndex = this.xssfCellStyle.getDataFormat();
        this.numFmtString = (String)ObjectUtil.defaultIfNull(this.xssfCellStyle
            .getDataFormatString(), () -> BuiltinFormats.getBuiltinFormat(numFmtIndex));
        if (CellDataType.NUMBER == this.cellDataType && ExcelSaxUtil.isDateFormat(numFmtIndex, this.numFmtString))
          this.cellDataType = CellDataType.DATE; 
      } 
    } 
  }
}
