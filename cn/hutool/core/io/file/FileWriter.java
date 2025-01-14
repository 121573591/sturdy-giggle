package cn.hutool.core.io.file;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.Map;

public class FileWriter extends FileWrapper {
  private static final long serialVersionUID = 1L;
  
  public static FileWriter create(File file, Charset charset) {
    return new FileWriter(file, charset);
  }
  
  public static FileWriter create(File file) {
    return new FileWriter(file);
  }
  
  public FileWriter(File file, Charset charset) {
    super(file, charset);
    checkFile();
  }
  
  public FileWriter(File file, String charset) {
    this(file, CharsetUtil.charset(charset));
  }
  
  public FileWriter(String filePath, Charset charset) {
    this(FileUtil.file(filePath), charset);
  }
  
  public FileWriter(String filePath, String charset) {
    this(FileUtil.file(filePath), CharsetUtil.charset(charset));
  }
  
  public FileWriter(File file) {
    this(file, DEFAULT_CHARSET);
  }
  
  public FileWriter(String filePath) {
    this(filePath, DEFAULT_CHARSET);
  }
  
  public File write(String content, boolean isAppend) throws IORuntimeException {
    BufferedWriter writer = null;
    try {
      writer = getWriter(isAppend);
      writer.write(content);
      writer.flush();
    } catch (IOException e) {
      throw new IORuntimeException(e);
    } finally {
      IoUtil.close(writer);
    } 
    return this.file;
  }
  
  public File write(String content) throws IORuntimeException {
    return write(content, false);
  }
  
  public File append(String content) throws IORuntimeException {
    return write(content, true);
  }
  
  public <T> File writeLines(Iterable<T> list) throws IORuntimeException {
    return writeLines(list, false);
  }
  
  public <T> File appendLines(Iterable<T> list) throws IORuntimeException {
    return writeLines(list, true);
  }
  
  public <T> File writeLines(Iterable<T> list, boolean isAppend) throws IORuntimeException {
    return writeLines(list, (LineSeparator)null, isAppend);
  }
  
  public <T> File writeLines(Iterable<T> list, LineSeparator lineSeparator, boolean isAppend) throws IORuntimeException {
    try (PrintWriter writer = getPrintWriter(isAppend)) {
      boolean isFirst = true;
      for (T t : list) {
        if (null != t) {
          if (isFirst) {
            isFirst = false;
            if (isAppend && FileUtil.isNotEmpty(this.file))
              printNewLine(writer, lineSeparator); 
          } else {
            printNewLine(writer, lineSeparator);
          } 
          writer.print(t);
          writer.flush();
        } 
      } 
    } 
    return this.file;
  }
  
  public File writeMap(Map<?, ?> map, String kvSeparator, boolean isAppend) throws IORuntimeException {
    return writeMap(map, (LineSeparator)null, kvSeparator, isAppend);
  }
  
  public File writeMap(Map<?, ?> map, LineSeparator lineSeparator, String kvSeparator, boolean isAppend) throws IORuntimeException {
    if (null == kvSeparator)
      kvSeparator = " = "; 
    try (PrintWriter writer = getPrintWriter(isAppend)) {
      for (Map.Entry<?, ?> entry : map.entrySet()) {
        if (null != entry) {
          writer.print(StrUtil.format("{}{}{}", new Object[] { entry.getKey(), kvSeparator, entry.getValue() }));
          printNewLine(writer, lineSeparator);
          writer.flush();
        } 
      } 
    } 
    return this.file;
  }
  
  public File write(byte[] data, int off, int len) throws IORuntimeException {
    return write(data, off, len, false);
  }
  
  public File append(byte[] data, int off, int len) throws IORuntimeException {
    return write(data, off, len, true);
  }
  
  public File write(byte[] data, int off, int len, boolean isAppend) throws IORuntimeException {
    try (FileOutputStream out = new FileOutputStream(FileUtil.touch(this.file), isAppend)) {
      out.write(data, off, len);
      out.flush();
    } catch (IOException e) {
      throw new IORuntimeException(e);
    } 
    return this.file;
  }
  
  public File writeFromStream(InputStream in) throws IORuntimeException {
    return writeFromStream(in, true);
  }
  
  public File writeFromStream(InputStream in, boolean isCloseIn) throws IORuntimeException {
    OutputStream out = null;
    try {
      out = Files.newOutputStream(FileUtil.touch(this.file).toPath(), new java.nio.file.OpenOption[0]);
      IoUtil.copy(in, out);
    } catch (IOException e) {
      throw new IORuntimeException(e);
    } finally {
      IoUtil.close(out);
      if (isCloseIn)
        IoUtil.close(in); 
    } 
    return this.file;
  }
  
  public BufferedOutputStream getOutputStream() throws IORuntimeException {
    try {
      return new BufferedOutputStream(Files.newOutputStream(FileUtil.touch(this.file).toPath(), new java.nio.file.OpenOption[0]));
    } catch (IOException e) {
      throw new IORuntimeException(e);
    } 
  }
  
  public BufferedWriter getWriter(boolean isAppend) throws IORuntimeException {
    try {
      return new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FileUtil.touch(this.file), isAppend), this.charset));
    } catch (Exception e) {
      throw new IORuntimeException(e);
    } 
  }
  
  public PrintWriter getPrintWriter(boolean isAppend) throws IORuntimeException {
    return new PrintWriter(getWriter(isAppend));
  }
  
  private void checkFile() throws IORuntimeException {
    Assert.notNull(this.file, "File to write content is null !", new Object[0]);
    if (this.file.exists() && false == this.file.isFile())
      throw new IORuntimeException("File [{}] is not a file !", new Object[] { this.file.getAbsoluteFile() }); 
  }
  
  private void printNewLine(PrintWriter writer, LineSeparator lineSeparator) {
    if (null == lineSeparator) {
      writer.println();
    } else {
      writer.print(lineSeparator.getValue());
    } 
  }
}
