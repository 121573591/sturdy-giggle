package cn.hutool.core.io.watch;

import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;

public enum WatchKind {
  OVERFLOW(StandardWatchEventKinds.OVERFLOW),
  MODIFY(StandardWatchEventKinds.ENTRY_MODIFY),
  CREATE(StandardWatchEventKinds.ENTRY_CREATE),
  DELETE(StandardWatchEventKinds.ENTRY_DELETE);
  
  public static final WatchEvent.Kind<?>[] ALL;
  
  private final WatchEvent.Kind<?> value;
  
  static {
    ALL = (WatchEvent.Kind<?>[])new WatchEvent.Kind[] { OVERFLOW.getValue(), MODIFY.getValue(), CREATE.getValue(), DELETE.getValue() };
  }
  
  WatchKind(WatchEvent.Kind<?> value) {
    this.value = value;
  }
  
  public WatchEvent.Kind<?> getValue() {
    return this.value;
  }
}
