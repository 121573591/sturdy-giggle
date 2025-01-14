package cn.hutool.core.io.file;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.exceptions.UtilException;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.io.LineHandler;
import cn.hutool.core.io.watch.SimpleWatcher;
import cn.hutool.core.io.watch.WatchKind;
import cn.hutool.core.io.watch.WatchMonitor;
import cn.hutool.core.io.watch.Watcher;
import cn.hutool.core.lang.Console;
import cn.hutool.core.util.CharsetUtil;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.util.Stack;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class Tailer implements Serializable {
  private static final long serialVersionUID = 1L;
  
  public static final LineHandler CONSOLE_HANDLER = new ConsoleLineHandler();
  
  private final Charset charset;
  
  private final LineHandler lineHandler;
  
  private final int initReadLine;
  
  private final long period;
  
  private final String filePath;
  
  private final RandomAccessFile randomAccessFile;
  
  private final ScheduledExecutorService executorService;
  
  private WatchMonitor fileDeleteWatchMonitor;
  
  private boolean stopOnDelete;
  
  public Tailer(File file, LineHandler lineHandler) {
    this(file, lineHandler, 0);
  }
  
  public Tailer(File file, LineHandler lineHandler, int initReadLine) {
    this(file, CharsetUtil.CHARSET_UTF_8, lineHandler, initReadLine, DateUnit.SECOND.getMillis());
  }
  
  public Tailer(File file, Charset charset, LineHandler lineHandler) {
    this(file, charset, lineHandler, 0, DateUnit.SECOND.getMillis());
  }
  
  public Tailer(File file, Charset charset, LineHandler lineHandler, int initReadLine, long period) {
    checkFile(file);
    this.charset = charset;
    this.lineHandler = lineHandler;
    this.period = period;
    this.initReadLine = initReadLine;
    this.randomAccessFile = FileUtil.createRandomAccessFile(file, FileMode.r);
    this.executorService = Executors.newSingleThreadScheduledExecutor();
    this.filePath = file.getAbsolutePath();
  }
  
  public void setStopOnDelete(boolean stopOnDelete) {
    this.stopOnDelete = stopOnDelete;
  }
  
  public void start() {
    start(false);
  }
  
  public void start(boolean async) {
    try {
      readTail();
    } catch (IOException e) {
      throw new IORuntimeException(e);
    } 
    LineReadWatcher lineReadWatcher = new LineReadWatcher(this.randomAccessFile, this.charset, this.lineHandler);
    ScheduledFuture<?> scheduledFuture = this.executorService.scheduleAtFixedRate(lineReadWatcher, 0L, this.period, TimeUnit.MILLISECONDS);
    if (this.stopOnDelete) {
      this.fileDeleteWatchMonitor = WatchMonitor.create(this.filePath, new WatchEvent.Kind[] { WatchKind.DELETE.getValue() });
      this.fileDeleteWatchMonitor.setWatcher((Watcher)new SimpleWatcher() {
            public void onDelete(WatchEvent<?> event, Path currentPath) {
              super.onDelete(event, currentPath);
              Tailer.this.stop();
              throw new IORuntimeException("{} has been deleted", new Object[] { Tailer.access$000(this.this$0) });
            }
          });
      this.fileDeleteWatchMonitor.start();
    } 
    if (false == async)
      try {
        scheduledFuture.get();
      } catch (ExecutionException e) {
        throw new UtilException(e);
      } catch (InterruptedException interruptedException) {} 
  }
  
  public void stop() {
    try {
      this.executorService.shutdown();
    } finally {
      IoUtil.close(this.randomAccessFile);
      IoUtil.close((Closeable)this.fileDeleteWatchMonitor);
    } 
  }
  
  private void readTail() throws IOException {
    long len = this.randomAccessFile.length();
    if (this.initReadLine > 0) {
      Stack<String> stack = new Stack<>();
      long start = this.randomAccessFile.getFilePointer();
      long nextEnd = (len - 1L < 0L) ? 0L : (len - 1L);
      this.randomAccessFile.seek(nextEnd);
      int currentLine = 0;
      while (nextEnd > start) {
        if (currentLine > this.initReadLine)
          break; 
        int c = this.randomAccessFile.read();
        if (c == 10 || c == 13) {
          String line = FileUtil.readLine(this.randomAccessFile, this.charset);
          if (null != line)
            stack.push(line); 
          currentLine++;
          nextEnd--;
        } 
        nextEnd--;
        this.randomAccessFile.seek(nextEnd);
        if (nextEnd == 0L) {
          String line = FileUtil.readLine(this.randomAccessFile, this.charset);
          if (null != line)
            stack.push(line); 
          break;
        } 
      } 
      while (false == stack.isEmpty())
        this.lineHandler.handle(stack.pop()); 
    } 
    try {
      this.randomAccessFile.seek(len);
    } catch (IOException e) {
      throw new IORuntimeException(e);
    } 
  }
  
  private static void checkFile(File file) {
    if (false == file.exists())
      throw new UtilException("File [{}] not exist !", new Object[] { file.getAbsolutePath() }); 
    if (false == file.isFile())
      throw new UtilException("Path [{}] is not a file !", new Object[] { file.getAbsolutePath() }); 
  }
  
  public static class ConsoleLineHandler implements LineHandler {
    public void handle(String line) {
      Console.log(line);
    }
  }
}
