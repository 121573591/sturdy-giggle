package cn.hutool.poi.excel.sax;

import cn.hutool.core.io.FileUtil;
import cn.hutool.poi.exceptions.POIException;
import java.io.File;
import java.io.InputStream;

public interface ExcelSaxReader<T> {
  public static final String RID_PREFIX = "rId";
  
  public static final String SHEET_NAME_PREFIX = "sheetName:";
  
  T read(File paramFile, String paramString) throws POIException;
  
  T read(InputStream paramInputStream, String paramString) throws POIException;
  
  default T read(String path) throws POIException {
    return read(FileUtil.file(path));
  }
  
  default T read(File file) throws POIException {
    return read(file, -1);
  }
  
  default T read(InputStream in) throws POIException {
    return read(in, -1);
  }
  
  default T read(String path, int idOrRidOrSheetName) throws POIException {
    return read(FileUtil.file(path), idOrRidOrSheetName);
  }
  
  default T read(String path, String idOrRidOrSheetName) throws POIException {
    return read(FileUtil.file(path), idOrRidOrSheetName);
  }
  
  default T read(File file, int rid) throws POIException {
    return read(file, String.valueOf(rid));
  }
  
  default T read(InputStream in, int rid) throws POIException {
    return read(in, String.valueOf(rid));
  }
}
