package cn.hutool.poi.excel;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.poi.excel.cell.CellLocation;
import cn.hutool.poi.excel.cell.CellUtil;
import cn.hutool.poi.excel.style.StyleUtil;
import java.io.Closeable;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelBase<T extends ExcelBase<T>> implements Closeable {
  protected boolean isClosed;
  
  protected File destFile;
  
  protected Workbook workbook;
  
  protected Sheet sheet;
  
  protected Map<String, String> headerAlias;
  
  public ExcelBase(Sheet sheet) {
    Assert.notNull(sheet, "No Sheet provided.", new Object[0]);
    this.sheet = sheet;
    this.workbook = sheet.getWorkbook();
  }
  
  public Workbook getWorkbook() {
    return this.workbook;
  }
  
  public int getSheetCount() {
    return this.workbook.getNumberOfSheets();
  }
  
  public List<Sheet> getSheets() {
    int totalSheet = getSheetCount();
    List<Sheet> result = new ArrayList<>(totalSheet);
    for (int i = 0; i < totalSheet; i++)
      result.add(this.workbook.getSheetAt(i)); 
    return result;
  }
  
  public List<String> getSheetNames() {
    int totalSheet = this.workbook.getNumberOfSheets();
    List<String> result = new ArrayList<>(totalSheet);
    for (int i = 0; i < totalSheet; i++)
      result.add(this.workbook.getSheetAt(i).getSheetName()); 
    return result;
  }
  
  public Sheet getSheet() {
    return this.sheet;
  }
  
  public T renameSheet(String newName) {
    this.workbook.setSheetName(this.workbook.getSheetIndex(this.sheet), newName);
    return (T)this;
  }
  
  public T setSheet(String sheetName) {
    return setSheet(WorkbookUtil.getOrCreateSheet(this.workbook, sheetName));
  }
  
  public T setSheet(int sheetIndex) {
    return setSheet(WorkbookUtil.getOrCreateSheet(this.workbook, sheetIndex));
  }
  
  public T setSheet(Sheet sheet) {
    this.sheet = sheet;
    return (T)this;
  }
  
  public T cloneSheet(int sheetIndex, String newSheetName, boolean setAsCurrentSheet) {
    Sheet sheet;
    if (this.workbook instanceof XSSFWorkbook) {
      XSSFWorkbook workbook = (XSSFWorkbook)this.workbook;
      XSSFSheet xSSFSheet = workbook.cloneSheet(sheetIndex, newSheetName);
    } else {
      sheet = this.workbook.cloneSheet(sheetIndex);
      this.workbook.setSheetName(this.workbook.getSheetIndex(sheet), newSheetName);
    } 
    if (setAsCurrentSheet)
      this.sheet = sheet; 
    return (T)this;
  }
  
  public Cell getCell(String locationRef) {
    CellLocation cellLocation = ExcelUtil.toLocation(locationRef);
    return getCell(cellLocation.getX(), cellLocation.getY());
  }
  
  public Cell getCell(int x, int y) {
    return getCell(x, y, false);
  }
  
  public Cell getOrCreateCell(String locationRef) {
    CellLocation cellLocation = ExcelUtil.toLocation(locationRef);
    return getOrCreateCell(cellLocation.getX(), cellLocation.getY());
  }
  
  public Cell getOrCreateCell(int x, int y) {
    return getCell(x, y, true);
  }
  
  public Cell getCell(String locationRef, boolean isCreateIfNotExist) {
    CellLocation cellLocation = ExcelUtil.toLocation(locationRef);
    return getCell(cellLocation.getX(), cellLocation.getY(), isCreateIfNotExist);
  }
  
  public Cell getCell(int x, int y, boolean isCreateIfNotExist) {
    Row row = isCreateIfNotExist ? RowUtil.getOrCreateRow(this.sheet, y) : this.sheet.getRow(y);
    if (null != row)
      return isCreateIfNotExist ? CellUtil.getOrCreateCell(row, x) : row.getCell(x); 
    return null;
  }
  
  public Row getOrCreateRow(int y) {
    return RowUtil.getOrCreateRow(this.sheet, y);
  }
  
  public CellStyle getOrCreateCellStyle(String locationRef) {
    CellLocation cellLocation = ExcelUtil.toLocation(locationRef);
    return getOrCreateCellStyle(cellLocation.getX(), cellLocation.getY());
  }
  
  public CellStyle getOrCreateCellStyle(int x, int y) {
    CellStyle cellStyle = getOrCreateCell(x, y).getCellStyle();
    return StyleUtil.isNullOrDefaultStyle(this.workbook, cellStyle) ? createCellStyle(x, y) : cellStyle;
  }
  
  public CellStyle createCellStyle(String locationRef) {
    CellLocation cellLocation = ExcelUtil.toLocation(locationRef);
    return createCellStyle(cellLocation.getX(), cellLocation.getY());
  }
  
  public CellStyle createCellStyle(int x, int y) {
    Cell cell = getOrCreateCell(x, y);
    CellStyle cellStyle = this.workbook.createCellStyle();
    cell.setCellStyle(cellStyle);
    return cellStyle;
  }
  
  public CellStyle createCellStyle() {
    return StyleUtil.createCellStyle(this.workbook);
  }
  
  public CellStyle getOrCreateRowStyle(int y) {
    CellStyle rowStyle = getOrCreateRow(y).getRowStyle();
    return StyleUtil.isNullOrDefaultStyle(this.workbook, rowStyle) ? createRowStyle(y) : rowStyle;
  }
  
  public CellStyle createRowStyle(int y) {
    CellStyle rowStyle = this.workbook.createCellStyle();
    getOrCreateRow(y).setRowStyle(rowStyle);
    return rowStyle;
  }
  
  public CellStyle getOrCreateColumnStyle(int x) {
    CellStyle columnStyle = this.sheet.getColumnStyle(x);
    return StyleUtil.isNullOrDefaultStyle(this.workbook, columnStyle) ? createColumnStyle(x) : columnStyle;
  }
  
  public CellStyle createColumnStyle(int x) {
    CellStyle columnStyle = this.workbook.createCellStyle();
    this.sheet.setDefaultColumnStyle(x, columnStyle);
    return columnStyle;
  }
  
  public Hyperlink createHyperlink(HyperlinkType type, String address) {
    return createHyperlink(type, address, address);
  }
  
  public Hyperlink createHyperlink(HyperlinkType type, String address, String label) {
    Hyperlink hyperlink = this.workbook.getCreationHelper().createHyperlink(type);
    hyperlink.setAddress(address);
    hyperlink.setLabel(label);
    return hyperlink;
  }
  
  public int getRowCount() {
    return this.sheet.getLastRowNum() + 1;
  }
  
  public int getPhysicalRowCount() {
    return this.sheet.getPhysicalNumberOfRows();
  }
  
  public int getColumnCount() {
    return getColumnCount(0);
  }
  
  public int getColumnCount(int rowNum) {
    Row row = this.sheet.getRow(rowNum);
    if (null != row)
      return row.getLastCellNum(); 
    return -1;
  }
  
  public boolean isXlsx() {
    return (this.sheet instanceof XSSFSheet || this.sheet instanceof org.apache.poi.xssf.streaming.SXSSFSheet);
  }
  
  public void close() {
    IoUtil.close((Closeable)this.workbook);
    this.sheet = null;
    this.workbook = null;
    this.isClosed = true;
  }
  
  public Map<String, String> getHeaderAlias() {
    return this.headerAlias;
  }
  
  public T setHeaderAlias(Map<String, String> headerAlias) {
    this.headerAlias = headerAlias;
    return (T)this;
  }
  
  public T addHeaderAlias(String header, String alias) {
    Map<String, String> headerAlias = this.headerAlias;
    if (null == headerAlias)
      headerAlias = new LinkedHashMap<>(); 
    this.headerAlias = headerAlias;
    this.headerAlias.put(header, alias);
    return (T)this;
  }
  
  public T removeHeaderAlias(String header) {
    this.headerAlias.remove(header);
    return (T)this;
  }
  
  public T clearHeaderAlias() {
    this.headerAlias = null;
    return (T)this;
  }
}
