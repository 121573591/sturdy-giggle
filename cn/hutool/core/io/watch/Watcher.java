package cn.hutool.core.io.watch;

import java.nio.file.Path;
import java.nio.file.WatchEvent;

public interface Watcher {
  void onCreate(WatchEvent<?> paramWatchEvent, Path paramPath);
  
  void onModify(WatchEvent<?> paramWatchEvent, Path paramPath);
  
  void onDelete(WatchEvent<?> paramWatchEvent, Path paramPath);
  
  void onOverflow(WatchEvent<?> paramWatchEvent, Path paramPath);
}
