package cn.hutool.poi.excel;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IORuntimeException;
import java.io.File;
import java.io.OutputStream;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

public class BigExcelWriter extends ExcelWriter {
  public static final int DEFAULT_WINDOW_SIZE = 100;
  
  private boolean isFlushed;
  
  public BigExcelWriter() {
    this(100);
  }
  
  public BigExcelWriter(int rowAccessWindowSize) {
    this(WorkbookUtil.createSXSSFBook(rowAccessWindowSize), (String)null);
  }
  
  public BigExcelWriter(int rowAccessWindowSize, boolean compressTmpFiles, boolean useSharedStringsTable, String sheetName) {
    this(WorkbookUtil.createSXSSFBook(rowAccessWindowSize, compressTmpFiles, useSharedStringsTable), sheetName);
  }
  
  public BigExcelWriter(String destFilePath) {
    this(destFilePath, (String)null);
  }
  
  public BigExcelWriter(int rowAccessWindowSize, String sheetName) {
    this(WorkbookUtil.createSXSSFBook(rowAccessWindowSize), sheetName);
  }
  
  public BigExcelWriter(String destFilePath, String sheetName) {
    this(FileUtil.file(destFilePath), sheetName);
  }
  
  public BigExcelWriter(File destFile) {
    this(destFile, (String)null);
  }
  
  public BigExcelWriter(File destFile, String sheetName) {
    this(destFile.exists() ? WorkbookUtil.createSXSSFBook(destFile) : WorkbookUtil.createSXSSFBook(), sheetName);
    this.destFile = destFile;
  }
  
  public BigExcelWriter(SXSSFWorkbook workbook, String sheetName) {
    this(WorkbookUtil.getOrCreateSheet((Workbook)workbook, sheetName));
  }
  
  public BigExcelWriter(Sheet sheet) {
    super(sheet);
  }
  
  public BigExcelWriter autoSizeColumn(int columnIndex) {
    SXSSFSheet sheet = (SXSSFSheet)this.sheet;
    sheet.trackColumnForAutoSizing(columnIndex);
    super.autoSizeColumn(columnIndex);
    sheet.untrackColumnForAutoSizing(columnIndex);
    return this;
  }
  
  public BigExcelWriter autoSizeColumnAll() {
    SXSSFSheet sheet = (SXSSFSheet)this.sheet;
    sheet.trackAllColumnsForAutoSizing();
    super.autoSizeColumnAll();
    sheet.untrackAllColumnsForAutoSizing();
    return this;
  }
  
  public ExcelWriter flush(OutputStream out, boolean isCloseOut) throws IORuntimeException {
    if (false == this.isFlushed) {
      this.isFlushed = true;
      return super.flush(out, isCloseOut);
    } 
    return this;
  }
  
  public void close() {
    if (null != this.destFile && false == this.isFlushed)
      flush(); 
    ((SXSSFWorkbook)this.workbook).dispose();
    closeWithoutFlush();
  }
}
