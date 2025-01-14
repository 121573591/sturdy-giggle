package cn.hutool.extra.compress.extractor;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Filter;
import cn.hutool.core.util.StrUtil;
import java.io.Closeable;
import java.io.File;
import java.util.List;
import org.apache.commons.compress.archivers.ArchiveEntry;

public interface Extractor extends Closeable {
  default void extract(File targetDir) {
    extract(targetDir, (Filter<ArchiveEntry>)null);
  }
  
  default void extract(File targetDir, Filter<ArchiveEntry> filter) {
    extract(targetDir, 0, filter);
  }
  
  default void extract(File targetDir, int stripComponents) {
    extract(targetDir, stripComponents, null);
  }
  
  void extract(File paramFile, int paramInt, Filter<ArchiveEntry> paramFilter);
  
  default String stripName(String name, int stripComponents) {
    if (stripComponents <= 0)
      return name; 
    List<String> nameList = StrUtil.splitTrim(name, "/");
    int size = nameList.size();
    if (size > stripComponents) {
      nameList = CollUtil.sub(nameList, stripComponents, size);
      return CollUtil.join(nameList, "/");
    } 
    return null;
  }
  
  void close();
}
