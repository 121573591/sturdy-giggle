package cn.hutool.extra.compress.extractor;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.lang.Filter;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.compress.CompressException;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;

public class StreamExtractor implements Extractor {
  private final ArchiveInputStream in;
  
  public StreamExtractor(Charset charset, File file) {
    this(charset, (String)null, file);
  }
  
  public StreamExtractor(Charset charset, String archiverName, File file) {
    this(charset, archiverName, FileUtil.getInputStream(file));
  }
  
  public StreamExtractor(Charset charset, InputStream in) {
    this(charset, (String)null, in);
  }
  
  public StreamExtractor(Charset charset, String archiverName, InputStream in) {
    if (in instanceof ArchiveInputStream) {
      this.in = (ArchiveInputStream)in;
      return;
    } 
    ArchiveStreamFactory factory = new ArchiveStreamFactory(charset.name());
    try {
      in = IoUtil.toBuffered(in);
      if (StrUtil.isBlank(archiverName)) {
        this.in = factory.createArchiveInputStream(in);
      } else if ("tgz".equalsIgnoreCase(archiverName) || "tar.gz".equalsIgnoreCase(archiverName)) {
        try {
          this.in = (ArchiveInputStream)new TarArchiveInputStream((InputStream)new GzipCompressorInputStream(in));
        } catch (IOException e) {
          throw new IORuntimeException(e);
        } 
      } else {
        this.in = factory.createArchiveInputStream(archiverName, in);
      } 
    } catch (ArchiveException e) {
      IoUtil.close(in);
      throw new CompressException(e);
    } 
  }
  
  public void extract(File targetDir, int stripComponents, Filter<ArchiveEntry> filter) {
    try {
      extractInternal(targetDir, stripComponents, filter);
    } catch (IOException e) {
      throw new IORuntimeException(e);
    } finally {
      close();
    } 
  }
  
  private void extractInternal(File targetDir, int stripComponents, Filter<ArchiveEntry> filter) throws IOException {
    Assert.isTrue((null != targetDir && (false == targetDir.exists() || targetDir.isDirectory())), "target must be dir.", new Object[0]);
    ArchiveInputStream in = this.in;
    ArchiveEntry entry;
    while (null != (entry = in.getNextEntry())) {
      if (null != filter && false == filter.accept(entry))
        continue; 
      if (false == in.canReadEntryData(entry))
        continue; 
      String entryName = stripName(entry.getName(), stripComponents);
      if (entryName == null)
        continue; 
      File outItemFile = FileUtil.file(targetDir, entryName);
      if (entry.isDirectory()) {
        outItemFile.mkdirs();
        continue;
      } 
      FileUtil.writeFromStream((InputStream)in, outItemFile, false);
    } 
  }
  
  public void close() {
    IoUtil.close((Closeable)this.in);
  }
}
