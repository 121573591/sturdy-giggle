package cn.hutool.poi.excel.sax;

import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.sax.handler.RowHandler;
import cn.hutool.poi.exceptions.POIException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageAccess;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.model.SharedStrings;

public class Excel07SaxReader implements ExcelSaxReader<Excel07SaxReader> {
  private final SheetDataSaxHandler handler;
  
  public Excel07SaxReader(RowHandler rowHandler) {
    this.handler = new SheetDataSaxHandler(rowHandler);
  }
  
  public Excel07SaxReader setRowHandler(RowHandler rowHandler) {
    this.handler.setRowHandler(rowHandler);
    return this;
  }
  
  public Excel07SaxReader read(File file, int rid) throws POIException {
    return read(file, "rId" + rid);
  }
  
  public Excel07SaxReader read(File file, String idOrRidOrSheetName) throws POIException {
    try (OPCPackage open = OPCPackage.open(file, PackageAccess.READ)) {
      return read(open, idOrRidOrSheetName);
    } catch (InvalidFormatException|IOException e) {
      throw new POIException(e);
    } 
  }
  
  public Excel07SaxReader read(InputStream in, int rid) throws POIException {
    return read(in, "rId" + rid);
  }
  
  public Excel07SaxReader read(InputStream in, String idOrRidOrSheetName) throws POIException {
    try (OPCPackage opcPackage = OPCPackage.open(in)) {
      return read(opcPackage, idOrRidOrSheetName);
    } catch (IOException e) {
      throw new IORuntimeException(e);
    } catch (InvalidFormatException e) {
      throw new POIException(e);
    } 
  }
  
  public Excel07SaxReader read(OPCPackage opcPackage, int rid) throws POIException {
    return read(opcPackage, "rId" + rid);
  }
  
  public Excel07SaxReader read(OPCPackage opcPackage, String idOrRidOrSheetName) throws POIException {
    try {
      return read(new XSSFReader(opcPackage), idOrRidOrSheetName);
    } catch (OpenXML4JException e) {
      throw new POIException(e);
    } catch (IOException e) {
      throw new IORuntimeException(e);
    } 
  }
  
  public Excel07SaxReader read(XSSFReader xssfReader, String idOrRidOrSheetName) throws POIException {
    try {
      this.handler.stylesTable = xssfReader.getStylesTable();
    } catch (IOException|InvalidFormatException iOException) {}
    this.handler.sharedStrings = (SharedStrings)ReflectUtil.invoke(xssfReader, "getSharedStringsTable", new Object[0]);
    return readSheets(xssfReader, idOrRidOrSheetName);
  }
  
  private Excel07SaxReader readSheets(XSSFReader xssfReader, String idOrRidOrSheetName) throws POIException {
    this.handler.sheetIndex = getSheetIndex(xssfReader, idOrRidOrSheetName);
    InputStream sheetInputStream = null;
    try {
      if (this.handler.sheetIndex > -1) {
        sheetInputStream = xssfReader.getSheet("rId" + (this.handler.sheetIndex + 1));
        ExcelSaxUtil.readFrom(sheetInputStream, this.handler);
        this.handler.rowHandler.doAfterAllAnalysed();
      } else {
        this.handler.sheetIndex = -1;
        Iterator<InputStream> sheetInputStreams = xssfReader.getSheetsData();
        while (sheetInputStreams.hasNext()) {
          this.handler.index = 0;
          this.handler.sheetIndex++;
          sheetInputStream = sheetInputStreams.next();
          ExcelSaxUtil.readFrom(sheetInputStream, this.handler);
          this.handler.rowHandler.doAfterAllAnalysed();
        } 
      } 
    } catch (RuntimeException e) {
      throw e;
    } catch (Exception e) {
      throw new POIException(e);
    } finally {
      IoUtil.close(sheetInputStream);
    } 
    return this;
  }
  
  private int getSheetIndex(XSSFReader xssfReader, String idOrRidOrSheetName) {
    if (StrUtil.startWithIgnoreCase(idOrRidOrSheetName, "rId"))
      return Integer.parseInt(StrUtil.removePrefixIgnoreCase(idOrRidOrSheetName, "rId")); 
    SheetRidReader ridReader = SheetRidReader.parse(xssfReader);
    if (StrUtil.startWithIgnoreCase(idOrRidOrSheetName, "sheetName:")) {
      idOrRidOrSheetName = StrUtil.removePrefixIgnoreCase(idOrRidOrSheetName, "sheetName:");
      Integer rid = ridReader.getRidByNameBase0(idOrRidOrSheetName);
      if (null != rid)
        return rid.intValue(); 
    } else {
      Integer rid = ridReader.getRidByNameBase0(idOrRidOrSheetName);
      if (null != rid)
        return rid.intValue(); 
      try {
        int sheetIndex = Integer.parseInt(idOrRidOrSheetName);
        rid = ridReader.getRidBySheetIdBase0(sheetIndex);
        return ((Integer)ObjectUtil.defaultIfNull(rid, Integer.valueOf(sheetIndex))).intValue();
      } catch (NumberFormatException numberFormatException) {}
    } 
    throw new IllegalArgumentException("Invalid rId or id or sheetName: " + idOrRidOrSheetName);
  }
}
