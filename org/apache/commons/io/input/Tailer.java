package org.apache.commons.io.input;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

public class Tailer implements Runnable {
  private static final int DEFAULT_DELAY_MILLIS = 1000;
  
  private static final String RAF_MODE = "r";
  
  private static final Charset DEFAULT_CHARSET = Charset.defaultCharset();
  
  private final byte[] inbuf;
  
  private final File file;
  
  private final Charset charset;
  
  private final long delayMillis;
  
  private final boolean end;
  
  private final TailerListener listener;
  
  private final boolean reOpen;
  
  private volatile boolean run = true;
  
  public Tailer(File file, TailerListener listener) {
    this(file, listener, 1000L);
  }
  
  public Tailer(File file, TailerListener listener, long delayMillis) {
    this(file, listener, delayMillis, false);
  }
  
  public Tailer(File file, TailerListener listener, long delayMillis, boolean end) {
    this(file, listener, delayMillis, end, 8192);
  }
  
  public Tailer(File file, TailerListener listener, long delayMillis, boolean end, boolean reOpen) {
    this(file, listener, delayMillis, end, reOpen, 8192);
  }
  
  public Tailer(File file, TailerListener listener, long delayMillis, boolean end, int bufSize) {
    this(file, listener, delayMillis, end, false, bufSize);
  }
  
  public Tailer(File file, TailerListener listener, long delayMillis, boolean end, boolean reOpen, int bufSize) {
    this(file, DEFAULT_CHARSET, listener, delayMillis, end, reOpen, bufSize);
  }
  
  public Tailer(File file, Charset charset, TailerListener listener, long delayMillis, boolean end, boolean reOpen, int bufSize) {
    this.file = file;
    this.delayMillis = delayMillis;
    this.end = end;
    this.inbuf = IOUtils.byteArray(bufSize);
    this.listener = listener;
    listener.init(this);
    this.reOpen = reOpen;
    this.charset = charset;
  }
  
  public static Tailer create(File file, TailerListener listener, long delayMillis, boolean end, int bufSize) {
    return create(file, listener, delayMillis, end, false, bufSize);
  }
  
  public static Tailer create(File file, TailerListener listener, long delayMillis, boolean end, boolean reOpen, int bufSize) {
    return create(file, DEFAULT_CHARSET, listener, delayMillis, end, reOpen, bufSize);
  }
  
  public static Tailer create(File file, Charset charset, TailerListener listener, long delayMillis, boolean end, boolean reOpen, int bufSize) {
    Tailer tailer = new Tailer(file, charset, listener, delayMillis, end, reOpen, bufSize);
    Thread thread = new Thread(tailer);
    thread.setDaemon(true);
    thread.start();
    return tailer;
  }
  
  public static Tailer create(File file, TailerListener listener, long delayMillis, boolean end) {
    return create(file, listener, delayMillis, end, 8192);
  }
  
  public static Tailer create(File file, TailerListener listener, long delayMillis, boolean end, boolean reOpen) {
    return create(file, listener, delayMillis, end, reOpen, 8192);
  }
  
  public static Tailer create(File file, TailerListener listener, long delayMillis) {
    return create(file, listener, delayMillis, false);
  }
  
  public static Tailer create(File file, TailerListener listener) {
    return create(file, listener, 1000L, false);
  }
  
  public File getFile() {
    return this.file;
  }
  
  protected boolean getRun() {
    return this.run;
  }
  
  public long getDelay() {
    return this.delayMillis;
  }
  
  public void run() {
    RandomAccessFile reader = null;
    try {
      long last = 0L;
      long position = 0L;
      while (getRun() && reader == null) {
        try {
          reader = new RandomAccessFile(this.file, "r");
        } catch (FileNotFoundException e) {
          this.listener.fileNotFound();
        } 
        if (reader == null) {
          Thread.sleep(this.delayMillis);
          continue;
        } 
        position = this.end ? this.file.length() : 0L;
        last = FileUtils.lastModified(this.file);
        reader.seek(position);
      } 
      while (getRun()) {
        boolean newer = FileUtils.isFileNewer(this.file, last);
        long length = this.file.length();
        if (length < position) {
          this.listener.fileRotated();
          try (RandomAccessFile save = reader) {
            reader = new RandomAccessFile(this.file, "r");
            try {
              readLines(save);
            } catch (IOException ioe) {
              this.listener.handle(ioe);
            } 
            position = 0L;
          } catch (FileNotFoundException e) {
            this.listener.fileNotFound();
            Thread.sleep(this.delayMillis);
          } 
          continue;
        } 
        if (length > position) {
          position = readLines(reader);
          last = FileUtils.lastModified(this.file);
        } else if (newer) {
          position = 0L;
          reader.seek(position);
          position = readLines(reader);
          last = FileUtils.lastModified(this.file);
        } 
        if (this.reOpen && reader != null)
          reader.close(); 
        Thread.sleep(this.delayMillis);
        if (getRun() && this.reOpen) {
          reader = new RandomAccessFile(this.file, "r");
          reader.seek(position);
        } 
      } 
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      this.listener.handle(e);
    } catch (Exception e) {
      this.listener.handle(e);
    } finally {
      try {
        if (reader != null)
          reader.close(); 
      } catch (IOException e) {
        this.listener.handle(e);
      } 
      stop();
    } 
  }
  
  public void stop() {
    this.run = false;
  }
  
  private long readLines(RandomAccessFile reader) throws IOException {
    try (ByteArrayOutputStream lineBuf = new ByteArrayOutputStream(64)) {
      long pos = reader.getFilePointer();
      long rePos = pos;
      boolean seenCR = false;
      int num;
      while (getRun() && (num = reader.read(this.inbuf)) != -1) {
        for (int i = 0; i < num; i++) {
          byte ch = this.inbuf[i];
          switch (ch) {
            case 10:
              seenCR = false;
              this.listener.handle(new String(lineBuf.toByteArray(), this.charset));
              lineBuf.reset();
              rePos = pos + i + 1L;
              break;
            case 13:
              if (seenCR)
                lineBuf.write(13); 
              seenCR = true;
              break;
            default:
              if (seenCR) {
                seenCR = false;
                this.listener.handle(new String(lineBuf.toByteArray(), this.charset));
                lineBuf.reset();
                rePos = pos + i + 1L;
              } 
              lineBuf.write(ch);
              break;
          } 
        } 
        pos = reader.getFilePointer();
      } 
      reader.seek(rePos);
      if (this.listener instanceof TailerListenerAdapter)
        ((TailerListenerAdapter)this.listener).endOfFileReached(); 
      return rePos;
    } 
  }
}
