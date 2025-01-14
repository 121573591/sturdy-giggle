package cn.hutool.extra.compress.archiver;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.lang.Filter;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.compress.CompressException;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveOutputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.archivers.ar.ArArchiveOutputStream;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorOutputStream;

public class StreamArchiver implements Archiver {
  private final ArchiveOutputStream out;
  
  public static StreamArchiver create(Charset charset, String archiverName, File file) {
    return new StreamArchiver(charset, archiverName, file);
  }
  
  public static StreamArchiver create(Charset charset, String archiverName, OutputStream out) {
    return new StreamArchiver(charset, archiverName, out);
  }
  
  public StreamArchiver(Charset charset, String archiverName, File file) {
    this(charset, archiverName, FileUtil.getOutputStream(file));
  }
  
  public StreamArchiver(Charset charset, String archiverName, OutputStream targetStream) {
    if ("tgz".equalsIgnoreCase(archiverName) || "tar.gz".equalsIgnoreCase(archiverName)) {
      try {
        this.out = (ArchiveOutputStream)new TarArchiveOutputStream((OutputStream)new GzipCompressorOutputStream(targetStream));
      } catch (IOException e) {
        throw new IORuntimeException(e);
      } 
    } else {
      ArchiveStreamFactory factory = new ArchiveStreamFactory(charset.name());
      try {
        this.out = factory.createArchiveOutputStream(archiverName, targetStream);
      } catch (ArchiveException e) {
        throw new CompressException(e);
      } 
    } 
    if (this.out instanceof TarArchiveOutputStream) {
      ((TarArchiveOutputStream)this.out).setLongFileMode(2);
    } else if (this.out instanceof ArArchiveOutputStream) {
      ((ArArchiveOutputStream)this.out).setLongFileMode(1);
    } 
  }
  
  public StreamArchiver add(File file, String path, Filter<File> filter) throws IORuntimeException {
    try {
      addInternal(file, path, filter);
    } catch (IOException e) {
      throw new IORuntimeException(e);
    } 
    return this;
  }
  
  public StreamArchiver finish() {
    try {
      this.out.finish();
    } catch (IOException e) {
      throw new IORuntimeException(e);
    } 
    return this;
  }
  
  public void close() {
    try {
      finish();
    } catch (Exception exception) {}
    IoUtil.close((Closeable)this.out);
  }
  
  private void addInternal(File file, String path, Filter<File> filter) throws IOException {
    String entryName;
    if (null != filter && false == filter.accept(file))
      return; 
    ArchiveOutputStream out = this.out;
    if (StrUtil.isNotEmpty(path)) {
      entryName = StrUtil.addSuffixIfNot(path, "/") + file.getName();
    } else {
      entryName = file.getName();
    } 
    out.putArchiveEntry(out.createArchiveEntry(file, entryName));
    if (file.isDirectory()) {
      File[] files = file.listFiles();
      if (ArrayUtil.isNotEmpty((Object[])files)) {
        for (File childFile : files)
          addInternal(childFile, entryName, filter); 
      } else {
        out.closeArchiveEntry();
      } 
    } else {
      if (file.isFile())
        FileUtil.writeToStream(file, (OutputStream)out); 
      out.closeArchiveEntry();
    } 
  }
}
