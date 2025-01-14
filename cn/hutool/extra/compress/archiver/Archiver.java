package cn.hutool.extra.compress.archiver;

import cn.hutool.core.lang.Filter;
import java.io.Closeable;
import java.io.File;

public interface Archiver extends Closeable {
  default Archiver add(File file) {
    return add(file, null);
  }
  
  default Archiver add(File file, Filter<File> filter) {
    return add(file, "/", filter);
  }
  
  Archiver add(File paramFile, String paramString, Filter<File> paramFilter);
  
  Archiver finish();
  
  void close();
}
