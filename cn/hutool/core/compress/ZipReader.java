package cn.hutool.core.compress;

import cn.hutool.core.exceptions.UtilException;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.lang.Filter;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.ZipUtil;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.function.Consumer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class ZipReader implements Closeable {
  private static final int DEFAULT_MAX_SIZE_DIFF = 100;
  
  private ZipFile zipFile;
  
  private ZipInputStream in;
  
  private int maxSizeDiff = 100;
  
  public static ZipReader of(File zipFile, Charset charset) {
    return new ZipReader(zipFile, charset);
  }
  
  public static ZipReader of(InputStream in, Charset charset) {
    return new ZipReader(in, charset);
  }
  
  public ZipReader(File zipFile, Charset charset) {
    this.zipFile = ZipUtil.toZipFile(zipFile, charset);
  }
  
  public ZipReader(ZipFile zipFile) {
    this.zipFile = zipFile;
  }
  
  public ZipReader(InputStream in, Charset charset) {
    this.in = new ZipInputStream(in, charset);
  }
  
  public ZipReader(ZipInputStream zin) {
    this.in = zin;
  }
  
  public ZipReader setMaxSizeDiff(int maxSizeDiff) {
    this.maxSizeDiff = maxSizeDiff;
    return this;
  }
  
  public InputStream get(String path) {
    if (null != this.zipFile) {
      ZipFile zipFile = this.zipFile;
      ZipEntry entry = zipFile.getEntry(path);
      if (null != entry)
        return ZipUtil.getStream(zipFile, entry); 
    } else {
      try {
        ZipEntry zipEntry;
        while (null != (zipEntry = this.in.getNextEntry())) {
          if (zipEntry.getName().equals(path))
            return this.in; 
        } 
      } catch (IOException e) {
        throw new IORuntimeException(e);
      } 
    } 
    return null;
  }
  
  public File readTo(File outFile) throws IORuntimeException {
    return readTo(outFile, null);
  }
  
  public File readTo(File outFile, Filter<ZipEntry> entryFilter) throws IORuntimeException {
    read(zipEntry -> {
          if (null == entryFilter || entryFilter.accept(zipEntry)) {
            String path = zipEntry.getName();
            if (FileUtil.isWindows())
              path = StrUtil.replace(path, "*", "_"); 
            File outItemFile = FileUtil.file(outFile, path);
            if (zipEntry.isDirectory()) {
              outItemFile.mkdirs();
            } else {
              InputStream in;
              if (null != this.zipFile) {
                in = ZipUtil.getStream(this.zipFile, zipEntry);
              } else {
                in = this.in;
              } 
              FileUtil.writeFromStream(in, outItemFile, false);
            } 
          } 
        });
    return outFile;
  }
  
  public ZipReader read(Consumer<ZipEntry> consumer) throws IORuntimeException {
    if (null != this.zipFile) {
      readFromZipFile(consumer);
    } else {
      readFromStream(consumer);
    } 
    return this;
  }
  
  public void close() throws IORuntimeException {
    if (null != this.zipFile) {
      IoUtil.close(this.zipFile);
    } else {
      IoUtil.close(this.in);
    } 
  }
  
  private void readFromZipFile(Consumer<ZipEntry> consumer) {
    Enumeration<? extends ZipEntry> em = this.zipFile.entries();
    while (em.hasMoreElements())
      consumer.accept(checkZipBomb(em.nextElement())); 
  }
  
  private void readFromStream(Consumer<ZipEntry> consumer) throws IORuntimeException {
    try {
      ZipEntry zipEntry;
      while (null != (zipEntry = this.in.getNextEntry())) {
        consumer.accept(zipEntry);
        checkZipBomb(zipEntry);
      } 
    } catch (IOException e) {
      throw new IORuntimeException(e);
    } 
  }
  
  private ZipEntry checkZipBomb(ZipEntry entry) {
    if (this.maxSizeDiff < 0)
      return entry; 
    if (null == entry)
      return null; 
    long compressedSize = entry.getCompressedSize();
    long uncompressedSize = entry.getSize();
    if (compressedSize < 0L || uncompressedSize < 0L || compressedSize * this.maxSizeDiff < uncompressedSize)
      throw new UtilException("Zip bomb attack detected, invalid sizes: compressed {}, uncompressed {}, name {}", new Object[] { Long.valueOf(compressedSize), Long.valueOf(uncompressedSize), entry.getName() }); 
    return entry;
  }
}
