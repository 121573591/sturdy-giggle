package cn.hutool.core.text.csv;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.ObjectUtil;
import java.io.File;
import java.io.Reader;
import java.io.Serializable;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class CsvBaseReader implements Serializable {
  private static final long serialVersionUID = 1L;
  
  protected static final Charset DEFAULT_CHARSET = CharsetUtil.CHARSET_UTF_8;
  
  private final CsvReadConfig config;
  
  public CsvBaseReader() {
    this(null);
  }
  
  public CsvBaseReader(CsvReadConfig config) {
    this.config = (CsvReadConfig)ObjectUtil.defaultIfNull(config, CsvReadConfig::defaultConfig);
  }
  
  public void setFieldSeparator(char fieldSeparator) {
    this.config.setFieldSeparator(fieldSeparator);
  }
  
  public void setTextDelimiter(char textDelimiter) {
    this.config.setTextDelimiter(textDelimiter);
  }
  
  public void setContainsHeader(boolean containsHeader) {
    this.config.setContainsHeader(containsHeader);
  }
  
  public void setSkipEmptyRows(boolean skipEmptyRows) {
    this.config.setSkipEmptyRows(skipEmptyRows);
  }
  
  public void setErrorOnDifferentFieldCount(boolean errorOnDifferentFieldCount) {
    this.config.setErrorOnDifferentFieldCount(errorOnDifferentFieldCount);
  }
  
  public CsvData read(File file) throws IORuntimeException {
    return read(file, DEFAULT_CHARSET);
  }
  
  public CsvData readFromStr(String csvStr) {
    return read(new StringReader(csvStr));
  }
  
  public void readFromStr(String csvStr, CsvRowHandler rowHandler) {
    read(parse(new StringReader(csvStr)), true, rowHandler);
  }
  
  public CsvData read(File file, Charset charset) throws IORuntimeException {
    return read(Objects.<Path>requireNonNull(file.toPath(), "file must not be null"), charset);
  }
  
  public CsvData read(Path path) throws IORuntimeException {
    return read(path, DEFAULT_CHARSET);
  }
  
  public CsvData read(Path path, Charset charset) throws IORuntimeException {
    Assert.notNull(path, "path must not be null", new Object[0]);
    return read(FileUtil.getReader(path, charset));
  }
  
  public CsvData read(Reader reader) throws IORuntimeException {
    return read(reader, true);
  }
  
  public CsvData read(Reader reader, boolean close) throws IORuntimeException {
    CsvParser csvParser = parse(reader);
    List<CsvRow> rows = new ArrayList<>();
    read(csvParser, close, rows::add);
    List<String> header = (this.config.headerLineNo > -1L) ? csvParser.getHeader() : null;
    return new CsvData(header, rows);
  }
  
  public List<Map<String, String>> readMapList(Reader reader) throws IORuntimeException {
    this.config.setContainsHeader(true);
    List<Map<String, String>> result = new ArrayList<>();
    read(reader, row -> result.add(row.getFieldMap()));
    return result;
  }
  
  public <T> List<T> read(Reader reader, Class<T> clazz) {
    this.config.setContainsHeader(true);
    List<T> result = new ArrayList<>();
    read(reader, row -> result.add(row.toBean(clazz)));
    return result;
  }
  
  public <T> List<T> read(String csvStr, Class<T> clazz) {
    this.config.setContainsHeader(true);
    List<T> result = new ArrayList<>();
    read(new StringReader(csvStr), row -> result.add(row.toBean(clazz)));
    return result;
  }
  
  public void read(Reader reader, CsvRowHandler rowHandler) throws IORuntimeException {
    read(reader, true, rowHandler);
  }
  
  public void read(Reader reader, boolean close, CsvRowHandler rowHandler) throws IORuntimeException {
    read(parse(reader), close, rowHandler);
  }
  
  private void read(CsvParser csvParser, boolean close, CsvRowHandler rowHandler) throws IORuntimeException {
    try {
      while (csvParser.hasNext())
        rowHandler.handle((CsvRow)csvParser.next()); 
    } finally {
      if (close)
        IoUtil.close(csvParser); 
    } 
  }
  
  protected CsvParser parse(Reader reader) throws IORuntimeException {
    return new CsvParser(reader, this.config);
  }
}
