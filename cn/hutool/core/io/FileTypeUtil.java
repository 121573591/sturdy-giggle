package cn.hutool.core.io;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.HexUtil;
import cn.hutool.core.util.StrUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

public class FileTypeUtil {
  private static final Map<String, String> FILE_TYPE_MAP = new ConcurrentSkipListMap<>();
  
  public static String putFileType(String fileStreamHexHead, String extName) {
    return FILE_TYPE_MAP.put(fileStreamHexHead, extName);
  }
  
  public static String removeFileType(String fileStreamHexHead) {
    return FILE_TYPE_MAP.remove(fileStreamHexHead);
  }
  
  public static String getType(String fileStreamHexHead) {
    if (MapUtil.isNotEmpty(FILE_TYPE_MAP))
      for (Map.Entry<String, String> fileTypeEntry : FILE_TYPE_MAP.entrySet()) {
        if (StrUtil.startWithIgnoreCase(fileStreamHexHead, fileTypeEntry.getKey()))
          return fileTypeEntry.getValue(); 
      }  
    byte[] bytes = HexUtil.decodeHex(fileStreamHexHead);
    return FileMagicNumber.getMagicNumber(bytes).getExtension();
  }
  
  public static String getType(InputStream in, int fileHeadSize) throws IORuntimeException {
    return getType(IoUtil.readHex(in, fileHeadSize, false));
  }
  
  public static String getType(InputStream in, boolean isExact) throws IORuntimeException {
    if (null == in)
      return null; 
    return isExact ? 
      getType(IoUtil.readHex8192Upper(in)) : 
      getType(IoUtil.readHex64Upper(in));
  }
  
  public static String getType(InputStream in) throws IORuntimeException {
    return getType(in, false);
  }
  
  public static String getType(InputStream in, String filename) throws IORuntimeException {
    return getType(in, filename, false);
  }
  
  public static String getType(InputStream in, String filename, boolean isExact) throws IORuntimeException {
    String typeName = getType(in, isExact);
    if (null == typeName) {
      typeName = FileUtil.extName(filename);
    } else if ("zip".equals(typeName)) {
      String extName = FileUtil.extName(filename);
      if ("docx".equalsIgnoreCase(extName)) {
        typeName = "docx";
      } else if ("xlsx".equalsIgnoreCase(extName)) {
        typeName = "xlsx";
      } else if ("pptx".equalsIgnoreCase(extName)) {
        typeName = "pptx";
      } else if ("jar".equalsIgnoreCase(extName)) {
        typeName = "jar";
      } else if ("war".equalsIgnoreCase(extName)) {
        typeName = "war";
      } else if ("ofd".equalsIgnoreCase(extName)) {
        typeName = "ofd";
      } else if ("apk".equalsIgnoreCase(extName)) {
        typeName = "apk";
      } 
    } else if ("jar".equals(typeName)) {
      String extName = FileUtil.extName(filename);
      if ("xlsx".equalsIgnoreCase(extName)) {
        typeName = "xlsx";
      } else if ("docx".equalsIgnoreCase(extName)) {
        typeName = "docx";
      } else if ("pptx".equalsIgnoreCase(extName)) {
        typeName = "pptx";
      } else if ("zip".equalsIgnoreCase(extName)) {
        typeName = "zip";
      } else if ("apk".equalsIgnoreCase(extName)) {
        typeName = "apk";
      } 
    } 
    return typeName;
  }
  
  public static String getType(File file, boolean isExact) throws IORuntimeException {
    if (false == FileUtil.isFile(file))
      throw new IllegalArgumentException("Not a regular file!"); 
    FileInputStream in = null;
    try {
      in = IoUtil.toStream(file);
      return getType(in, file.getName(), isExact);
    } finally {
      IoUtil.close(in);
    } 
  }
  
  public static String getType(File file) throws IORuntimeException {
    return getType(file, false);
  }
  
  public static String getTypeByPath(String path, boolean isExact) throws IORuntimeException {
    return getType(FileUtil.file(path), isExact);
  }
  
  public static String getTypeByPath(String path) throws IORuntimeException {
    return getTypeByPath(path, false);
  }
}
