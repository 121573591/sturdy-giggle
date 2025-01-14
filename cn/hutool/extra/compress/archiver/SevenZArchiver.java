package cn.hutool.extra.compress.archiver;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.lang.Filter;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.channels.SeekableByteChannel;
import org.apache.commons.compress.archivers.sevenz.SevenZOutputFile;
import org.apache.commons.compress.utils.SeekableInMemoryByteChannel;

public class SevenZArchiver implements Archiver {
  private final SevenZOutputFile sevenZOutputFile;
  
  private SeekableByteChannel channel;
  
  private OutputStream out;
  
  public SevenZArchiver(File file) {
    try {
      this.sevenZOutputFile = new SevenZOutputFile(file);
    } catch (IOException e) {
      throw new IORuntimeException(e);
    } 
  }
  
  public SevenZArchiver(OutputStream out) {
    this.out = out;
    this.channel = (SeekableByteChannel)new SeekableInMemoryByteChannel();
    try {
      this.sevenZOutputFile = new SevenZOutputFile(this.channel);
    } catch (IOException e) {
      throw new IORuntimeException(e);
    } 
  }
  
  public SevenZArchiver(SeekableByteChannel channel) {
    try {
      this.sevenZOutputFile = new SevenZOutputFile(channel);
    } catch (IOException e) {
      throw new IORuntimeException(e);
    } 
  }
  
  public SevenZOutputFile getSevenZOutputFile() {
    return this.sevenZOutputFile;
  }
  
  public SevenZArchiver add(File file, String path, Filter<File> filter) {
    try {
      addInternal(file, path, filter);
    } catch (IOException e) {
      throw new IORuntimeException(e);
    } 
    return this;
  }
  
  public SevenZArchiver finish() {
    try {
      this.sevenZOutputFile.finish();
    } catch (IOException e) {
      throw new IORuntimeException(e);
    } 
    return this;
  }
  
  public void close() {
    try {
      finish();
    } catch (Exception exception) {}
    if (null != this.out && this.channel instanceof SeekableInMemoryByteChannel)
      try {
        this.out.write(((SeekableInMemoryByteChannel)this.channel).array());
      } catch (IOException e) {
        throw new IORuntimeException(e);
      }  
    IoUtil.close((Closeable)this.sevenZOutputFile);
  }
  
  private void addInternal(File file, String path, Filter<File> filter) throws IOException {
    String entryName;
    if (null != filter && false == filter.accept(file))
      return; 
    SevenZOutputFile out = this.sevenZOutputFile;
    if (StrUtil.isNotEmpty(path)) {
      entryName = StrUtil.addSuffixIfNot(path, "/") + file.getName();
    } else {
      entryName = file.getName();
    } 
    out.putArchiveEntry(out.createArchiveEntry(file, entryName));
    if (file.isDirectory()) {
      File[] files = file.listFiles();
      if (ArrayUtil.isNotEmpty((Object[])files))
        for (File childFile : files)
          addInternal(childFile, entryName, filter);  
    } else {
      if (file.isFile())
        out.write(FileUtil.readBytes(file)); 
      out.closeArchiveEntry();
    } 
  }
}
