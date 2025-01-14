package cn.hutool.poi.excel;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.poi.excel.cell.CellEditor;
import cn.hutool.poi.excel.cell.CellHandler;
import cn.hutool.poi.excel.cell.CellUtil;
import cn.hutool.poi.excel.reader.BeanSheetReader;
import cn.hutool.poi.excel.reader.ColumnSheetReader;
import cn.hutool.poi.excel.reader.ListSheetReader;
import cn.hutool.poi.excel.reader.MapSheetReader;
import cn.hutool.poi.excel.reader.SheetReader;
import java.io.Closeable;
import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.extractor.ExcelExtractor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ExcelReader extends ExcelBase<ExcelReader> {
  private boolean ignoreEmptyRow = true;
  
  private CellEditor cellEditor;
  
  public ExcelReader(String excelFilePath, int sheetIndex) {
    this(FileUtil.file(excelFilePath), sheetIndex);
  }
  
  public ExcelReader(String excelFilePath, String sheetName) {
    this(FileUtil.file(excelFilePath), sheetName);
  }
  
  public ExcelReader(File bookFile, int sheetIndex) {
    this(WorkbookUtil.createBook(bookFile, true), sheetIndex);
    this.destFile = bookFile;
  }
  
  public ExcelReader(File bookFile, String sheetName) {
    this(WorkbookUtil.createBook(bookFile, true), sheetName);
    this.destFile = bookFile;
  }
  
  public ExcelReader(InputStream bookStream, int sheetIndex) {
    this(WorkbookUtil.createBook(bookStream), sheetIndex);
  }
  
  public ExcelReader(InputStream bookStream, String sheetName) {
    this(WorkbookUtil.createBook(bookStream), sheetName);
  }
  
  public ExcelReader(Workbook book, int sheetIndex) {
    this(getSheetOrCloseWorkbook(book, sheetIndex));
  }
  
  public ExcelReader(Workbook book, String sheetName) {
    this(getSheetOrCloseWorkbook(book, sheetName));
  }
  
  public ExcelReader(Sheet sheet) {
    super(sheet);
  }
  
  public boolean isIgnoreEmptyRow() {
    return this.ignoreEmptyRow;
  }
  
  public ExcelReader setIgnoreEmptyRow(boolean ignoreEmptyRow) {
    this.ignoreEmptyRow = ignoreEmptyRow;
    return this;
  }
  
  public ExcelReader setCellEditor(CellEditor cellEditor) {
    this.cellEditor = cellEditor;
    return this;
  }
  
  public List<List<Object>> read() {
    return read(0);
  }
  
  public List<List<Object>> read(int startRowIndex) {
    return read(startRowIndex, 2147483647);
  }
  
  public List<List<Object>> read(int startRowIndex, int endRowIndex) {
    return read(startRowIndex, endRowIndex, true);
  }
  
  public List<List<Object>> read(int startRowIndex, int endRowIndex, boolean aliasFirstLine) {
    ListSheetReader reader = new ListSheetReader(startRowIndex, endRowIndex, aliasFirstLine);
    reader.setCellEditor(this.cellEditor);
    reader.setIgnoreEmptyRow(this.ignoreEmptyRow);
    reader.setHeaderAlias(this.headerAlias);
    return read((SheetReader<List<List<Object>>>)reader);
  }
  
  public List<Object> readColumn(int columnIndex, int startRowIndex) {
    return readColumn(columnIndex, startRowIndex, 2147483647);
  }
  
  public List<Object> readColumn(int columnIndex, int startRowIndex, int endRowIndex) {
    ColumnSheetReader reader = new ColumnSheetReader(columnIndex, startRowIndex, endRowIndex);
    reader.setCellEditor(this.cellEditor);
    reader.setIgnoreEmptyRow(this.ignoreEmptyRow);
    reader.setHeaderAlias(this.headerAlias);
    return read((SheetReader<List<Object>>)reader);
  }
  
  public void read(CellHandler cellHandler) {
    read(0, 2147483647, cellHandler);
  }
  
  public void read(int startRowIndex, int endRowIndex, CellHandler cellHandler) {
    checkNotClosed();
    startRowIndex = Math.max(startRowIndex, this.sheet.getFirstRowNum());
    endRowIndex = Math.min(endRowIndex, this.sheet.getLastRowNum());
    for (int y = startRowIndex; y <= endRowIndex; y++) {
      Row row = this.sheet.getRow(y);
      if (null != row) {
        short columnSize = row.getLastCellNum();
        short x;
        for (x = 0; x < columnSize; x = (short)(x + 1)) {
          Cell cell = row.getCell(x);
          cellHandler.handle(cell, CellUtil.getCellValue(cell));
        } 
      } 
    } 
  }
  
  public List<Map<String, Object>> readAll() {
    return read(0, 1, 2147483647);
  }
  
  public List<Map<String, Object>> read(int headerRowIndex, int startRowIndex, int endRowIndex) {
    MapSheetReader reader = new MapSheetReader(headerRowIndex, startRowIndex, endRowIndex);
    reader.setCellEditor(this.cellEditor);
    reader.setIgnoreEmptyRow(this.ignoreEmptyRow);
    reader.setHeaderAlias(this.headerAlias);
    return read((SheetReader<List<Map<String, Object>>>)reader);
  }
  
  public <T> List<T> readAll(Class<T> beanType) {
    return read(0, 1, 2147483647, beanType);
  }
  
  public <T> List<T> read(int headerRowIndex, int startRowIndex, Class<T> beanType) {
    return read(headerRowIndex, startRowIndex, 2147483647, beanType);
  }
  
  public <T> List<T> read(int headerRowIndex, int startRowIndex, int endRowIndex, Class<T> beanType) {
    BeanSheetReader<T> reader = new BeanSheetReader(headerRowIndex, startRowIndex, endRowIndex, beanType);
    reader.setCellEditor(this.cellEditor);
    reader.setIgnoreEmptyRow(this.ignoreEmptyRow);
    reader.setHeaderAlias(this.headerAlias);
    return read((SheetReader)reader);
  }
  
  public <T> T read(SheetReader<T> sheetReader) {
    checkNotClosed();
    return (T)((SheetReader)Assert.notNull(sheetReader)).read(this.sheet);
  }
  
  public String readAsText(boolean withSheetName) {
    return ExcelExtractorUtil.readAsText(this.workbook, withSheetName);
  }
  
  public ExcelExtractor getExtractor() {
    return ExcelExtractorUtil.getExtractor(this.workbook);
  }
  
  public List<Object> readRow(int rowIndex) {
    return readRow(this.sheet.getRow(rowIndex));
  }
  
  public Object readCellValue(int x, int y) {
    return CellUtil.getCellValue(getCell(x, y), this.cellEditor);
  }
  
  public ExcelWriter getWriter() {
    if (null == this.destFile)
      return new ExcelWriter(this.sheet); 
    return ExcelUtil.getWriter(this.destFile, this.sheet.getSheetName());
  }
  
  private List<Object> readRow(Row row) {
    return RowUtil.readRow(row, this.cellEditor);
  }
  
  private void checkNotClosed() {
    Assert.isFalse(this.isClosed, "ExcelReader has been closed!", new Object[0]);
  }
  
  private static Sheet getSheetOrCloseWorkbook(Workbook workbook, String name) throws IllegalArgumentException {
    Assert.notNull(workbook);
    if (null == name)
      name = "sheet1"; 
    Sheet sheet = workbook.getSheet(name);
    if (null == sheet) {
      IoUtil.close((Closeable)workbook);
      throw new IllegalArgumentException("Sheet [" + name + "] not exist!");
    } 
    return sheet;
  }
  
  private static Sheet getSheetOrCloseWorkbook(Workbook workbook, int sheetIndex) throws IllegalArgumentException {
    Sheet sheet;
    Assert.notNull(workbook);
    try {
      sheet = workbook.getSheetAt(sheetIndex);
    } catch (IllegalArgumentException e) {
      IoUtil.close((Closeable)workbook);
      throw e;
    } 
    if (null == sheet) {
      IoUtil.close((Closeable)workbook);
      throw new IllegalArgumentException("Sheet at [" + sheetIndex + "] not exist!");
    } 
    return sheet;
  }
}
