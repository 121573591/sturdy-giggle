package cn.hutool.poi.excel;

import org.apache.poi.hssf.extractor.ExcelExtractor;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.extractor.ExcelExtractor;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.extractor.XSSFExcelExtractor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelExtractorUtil {
  public static ExcelExtractor getExtractor(Workbook wb) {
    XSSFExcelExtractor xSSFExcelExtractor;
    if (wb instanceof HSSFWorkbook) {
      ExcelExtractor excelExtractor = new ExcelExtractor((HSSFWorkbook)wb);
    } else {
      xSSFExcelExtractor = new XSSFExcelExtractor((XSSFWorkbook)wb);
    } 
    return (ExcelExtractor)xSSFExcelExtractor;
  }
  
  public static String readAsText(Workbook wb, boolean withSheetName) {
    ExcelExtractor extractor = getExtractor(wb);
    extractor.setIncludeSheetNames(withSheetName);
    return extractor.getText();
  }
}
