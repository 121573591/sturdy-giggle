package cn.hutool.extra.compress.extractor;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.lang.Filter;
import cn.hutool.core.util.StrUtil;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.SeekableByteChannel;
import java.util.RandomAccess;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry;
import org.apache.commons.compress.archivers.sevenz.SevenZFile;
import org.apache.commons.compress.utils.SeekableInMemoryByteChannel;

public class SevenZExtractor implements Extractor, RandomAccess {
  private final SevenZFile sevenZFile;
  
  public SevenZExtractor(File file) {
    this(file, (char[])null);
  }
  
  public SevenZExtractor(File file, char[] password) {
    try {
      this.sevenZFile = new SevenZFile(file, password);
    } catch (IOException e) {
      throw new IORuntimeException(e);
    } 
  }
  
  public SevenZExtractor(InputStream in) {
    this(in, (char[])null);
  }
  
  public SevenZExtractor(InputStream in, char[] password) {
    this((SeekableByteChannel)new SeekableInMemoryByteChannel(IoUtil.readBytes(in)), password);
  }
  
  public SevenZExtractor(SeekableByteChannel channel) {
    this(channel, (char[])null);
  }
  
  public SevenZExtractor(SeekableByteChannel channel, char[] password) {
    try {
      this.sevenZFile = new SevenZFile(channel, password);
    } catch (IOException e) {
      throw new IORuntimeException(e);
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
  
  public InputStream getFirst(Filter<ArchiveEntry> filter) {
    SevenZFile sevenZFile = this.sevenZFile;
    for (SevenZArchiveEntry entry : sevenZFile.getEntries()) {
      if (null != filter && false == filter.accept(entry))
        continue; 
      if (entry.isDirectory())
        continue; 
      try {
        return sevenZFile.getInputStream(entry);
      } catch (IOException e) {
        throw new IORuntimeException(e);
      } 
    } 
    return null;
  }
  
  public InputStream get(String entryName) {
    return getFirst(entry -> StrUtil.equals(entryName, entry.getName()));
  }
  
  private void extractInternal(File targetDir, int stripComponents, Filter<ArchiveEntry> filter) throws IOException {
    Assert.isTrue((null != targetDir && (false == targetDir.exists() || targetDir.isDirectory())), "target must be dir.", new Object[0]);
    SevenZFile sevenZFile = this.sevenZFile;
    SevenZArchiveEntry entry;
    while (null != (entry = this.sevenZFile.getNextEntry())) {
      if (null != filter && false == filter.accept(entry))
        continue; 
      String entryName = stripName(entry.getName(), stripComponents);
      if (entryName == null)
        continue; 
      File outItemFile = FileUtil.file(targetDir, entryName);
      if (entry.isDirectory()) {
        outItemFile.mkdirs();
        continue;
      } 
      if (entry.hasStream()) {
        FileUtil.writeFromStream(new Seven7EntryInputStream(sevenZFile, entry), outItemFile);
        continue;
      } 
      FileUtil.touch(outItemFile);
    } 
  }
  
  public void close() {
    IoUtil.close((Closeable)this.sevenZFile);
  }
}
