package cn.hutool.core.io.watch;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.lang.Filter;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import java.io.Closeable;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public class WatchServer extends Thread implements Closeable, Serializable {
  private static final long serialVersionUID = 1L;
  
  private WatchService watchService;
  
  protected WatchEvent.Kind<?>[] events;
  
  private WatchEvent.Modifier[] modifiers;
  
  protected boolean isClosed;
  
  private final Map<WatchKey, Path> watchKeyPathMap = new HashMap<>();
  
  public void init() throws WatchException {
    try {
      this.watchService = FileSystems.getDefault().newWatchService();
    } catch (IOException e) {
      throw new WatchException(e);
    } 
    this.isClosed = false;
  }
  
  public void setModifiers(WatchEvent.Modifier[] modifiers) {
    this.modifiers = modifiers;
  }
  
  public void registerPath(Path path, int maxDepth) {
    WatchEvent.Kind[] arrayOfKind = (WatchEvent.Kind[])ArrayUtil.defaultIfEmpty((Object[])this.events, (Object[])WatchKind.ALL);
    try {
      WatchKey key;
      if (ArrayUtil.isEmpty((Object[])this.modifiers)) {
        key = path.register(this.watchService, (WatchEvent.Kind<?>[])arrayOfKind);
      } else {
        key = path.register(this.watchService, (WatchEvent.Kind<?>[])arrayOfKind, this.modifiers);
      } 
      this.watchKeyPathMap.put(key, path);
      if (maxDepth > 1)
        Files.walkFileTree(path, EnumSet.noneOf(FileVisitOption.class), maxDepth, new SimpleFileVisitor<Path>() {
              public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                WatchServer.this.registerPath(dir, 0);
                return super.postVisitDirectory(dir, exc);
              }
            }); 
    } catch (IOException e) {
      if (false == e instanceof java.nio.file.AccessDeniedException)
        throw new WatchException(e); 
    } 
  }
  
  public void watch(WatchAction action, Filter<WatchEvent<?>> watchFilter) {
    WatchKey wk;
    try {
      wk = this.watchService.take();
    } catch (InterruptedException|java.nio.file.ClosedWatchServiceException e) {
      close();
      return;
    } 
    Path currentPath = this.watchKeyPathMap.get(wk);
    for (WatchEvent<?> event : wk.pollEvents()) {
      if (null != watchFilter && false == watchFilter.accept(event))
        continue; 
      action.doAction(event, currentPath);
    } 
    wk.reset();
  }
  
  public void watch(Watcher watcher, Filter<WatchEvent<?>> watchFilter) {
    watch((event, currentPath) -> {
          WatchEvent.Kind<?> kind = event.kind();
          if (kind == WatchKind.CREATE.getValue()) {
            watcher.onCreate(event, currentPath);
          } else if (kind == WatchKind.MODIFY.getValue()) {
            watcher.onModify(event, currentPath);
          } else if (kind == WatchKind.DELETE.getValue()) {
            watcher.onDelete(event, currentPath);
          } else if (kind == WatchKind.OVERFLOW.getValue()) {
            watcher.onOverflow(event, currentPath);
          } 
        }watchFilter);
  }
  
  public WatchKey getWatchKey(Path path) {
    for (Map.Entry<WatchKey, Path> entry : this.watchKeyPathMap.entrySet()) {
      if (ObjectUtil.equals(path, entry.getValue()))
        return entry.getKey(); 
    } 
    return null;
  }
  
  public void close() {
    this.isClosed = true;
    IoUtil.close(this.watchService);
  }
}
