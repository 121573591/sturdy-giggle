package org.apache.commons.io.input;

import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.nio.ByteBuffer;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReadAheadInputStream extends InputStream {
  private static final ThreadLocal<byte[]> oneByte = (ThreadLocal)ThreadLocal.withInitial(() -> new byte[1]);
  
  private static ExecutorService newExecutorService() {
    return Executors.newSingleThreadExecutor(ReadAheadInputStream::newThread);
  }
  
  private static Thread newThread(Runnable r) {
    Thread thread = new Thread(r, "commons-io-read-ahead");
    thread.setDaemon(true);
    return thread;
  }
  
  private final ReentrantLock stateChangeLock = new ReentrantLock();
  
  private ByteBuffer activeBuffer;
  
  private ByteBuffer readAheadBuffer;
  
  private boolean endOfStream;
  
  private boolean readInProgress;
  
  private boolean readAborted;
  
  private Throwable readException;
  
  private boolean isClosed;
  
  private boolean isUnderlyingInputStreamBeingClosed;
  
  private boolean isReading;
  
  private final AtomicBoolean isWaiting = new AtomicBoolean(false);
  
  private final InputStream underlyingInputStream;
  
  private final ExecutorService executorService;
  
  private final boolean shutdownExecutorService;
  
  private final Condition asyncReadComplete = this.stateChangeLock.newCondition();
  
  public ReadAheadInputStream(InputStream inputStream, int bufferSizeInBytes) {
    this(inputStream, bufferSizeInBytes, newExecutorService(), true);
  }
  
  public ReadAheadInputStream(InputStream inputStream, int bufferSizeInBytes, ExecutorService executorService) {
    this(inputStream, bufferSizeInBytes, executorService, false);
  }
  
  private ReadAheadInputStream(InputStream inputStream, int bufferSizeInBytes, ExecutorService executorService, boolean shutdownExecutorService) {
    if (bufferSizeInBytes <= 0)
      throw new IllegalArgumentException("bufferSizeInBytes should be greater than 0, but the value is " + bufferSizeInBytes); 
    this.executorService = Objects.<ExecutorService>requireNonNull(executorService, "executorService");
    this.underlyingInputStream = Objects.<InputStream>requireNonNull(inputStream, "inputStream");
    this.shutdownExecutorService = shutdownExecutorService;
    this.activeBuffer = ByteBuffer.allocate(bufferSizeInBytes);
    this.readAheadBuffer = ByteBuffer.allocate(bufferSizeInBytes);
    this.activeBuffer.flip();
    this.readAheadBuffer.flip();
  }
  
  public int available() throws IOException {
    this.stateChangeLock.lock();
    try {
      return (int)Math.min(2147483647L, this.activeBuffer.remaining() + this.readAheadBuffer.remaining());
    } finally {
      this.stateChangeLock.unlock();
    } 
  }
  
  private void checkReadException() throws IOException {
    if (this.readAborted) {
      if (this.readException instanceof IOException)
        throw (IOException)this.readException; 
      throw new IOException(this.readException);
    } 
  }
  
  public void close() throws IOException {
    boolean isSafeToCloseUnderlyingInputStream = false;
    this.stateChangeLock.lock();
    try {
      if (this.isClosed)
        return; 
      this.isClosed = true;
      if (!this.isReading) {
        isSafeToCloseUnderlyingInputStream = true;
        this.isUnderlyingInputStreamBeingClosed = true;
      } 
    } finally {
      this.stateChangeLock.unlock();
    } 
    if (this.shutdownExecutorService)
      try {
        this.executorService.shutdownNow();
        this.executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
      } catch (InterruptedException e) {
        InterruptedIOException iio = new InterruptedIOException(e.getMessage());
        iio.initCause(e);
        throw iio;
      } finally {
        if (isSafeToCloseUnderlyingInputStream)
          this.underlyingInputStream.close(); 
      }  
  }
  
  private void closeUnderlyingInputStreamIfNecessary() {
    boolean needToCloseUnderlyingInputStream = false;
    this.stateChangeLock.lock();
    try {
      this.isReading = false;
      if (this.isClosed && !this.isUnderlyingInputStreamBeingClosed)
        needToCloseUnderlyingInputStream = true; 
    } finally {
      this.stateChangeLock.unlock();
    } 
    if (needToCloseUnderlyingInputStream)
      try {
        this.underlyingInputStream.close();
      } catch (IOException iOException) {} 
  }
  
  private boolean isEndOfStream() {
    return (!this.activeBuffer.hasRemaining() && !this.readAheadBuffer.hasRemaining() && this.endOfStream);
  }
  
  public int read() throws IOException {
    if (this.activeBuffer.hasRemaining())
      return this.activeBuffer.get() & 0xFF; 
    byte[] oneByteArray = oneByte.get();
    return (read(oneByteArray, 0, 1) == -1) ? -1 : (oneByteArray[0] & 0xFF);
  }
  
  public int read(byte[] b, int offset, int len) throws IOException {
    if (offset < 0 || len < 0 || len > b.length - offset)
      throw new IndexOutOfBoundsException(); 
    if (len == 0)
      return 0; 
    if (!this.activeBuffer.hasRemaining()) {
      this.stateChangeLock.lock();
      try {
        waitForAsyncReadComplete();
        if (!this.readAheadBuffer.hasRemaining()) {
          readAsync();
          waitForAsyncReadComplete();
          if (isEndOfStream())
            return -1; 
        } 
        swapBuffers();
        readAsync();
      } finally {
        this.stateChangeLock.unlock();
      } 
    } 
    len = Math.min(len, this.activeBuffer.remaining());
    this.activeBuffer.get(b, offset, len);
    return len;
  }
  
  private void readAsync() throws IOException {
    byte[] arr;
    this.stateChangeLock.lock();
    try {
      arr = this.readAheadBuffer.array();
      if (this.endOfStream || this.readInProgress)
        return; 
      checkReadException();
      this.readAheadBuffer.position(0);
      this.readAheadBuffer.flip();
      this.readInProgress = true;
    } finally {
      this.stateChangeLock.unlock();
    } 
    this.executorService.execute(() -> {
          this.stateChangeLock.lock();
          try {
            if (this.isClosed) {
              this.readInProgress = false;
              return;
            } 
            this.isReading = true;
          } finally {
            this.stateChangeLock.unlock();
          } 
          int read = 0;
          int off = 0;
          int len = arr.length;
          Throwable exception = null;
          try {
            do {
              read = this.underlyingInputStream.read(arr, off, len);
              if (read <= 0)
                break; 
              off += read;
              len -= read;
            } while (len > 0 && !this.isWaiting.get());
          } catch (Throwable ex) {
            exception = ex;
            if (ex instanceof Error)
              throw (Error)ex; 
          } finally {
            this.stateChangeLock.lock();
            try {
              this.readAheadBuffer.limit(off);
              if (read < 0 || exception instanceof java.io.EOFException) {
                this.endOfStream = true;
              } else if (exception != null) {
                this.readAborted = true;
                this.readException = exception;
              } 
              this.readInProgress = false;
              signalAsyncReadComplete();
            } finally {
              this.stateChangeLock.unlock();
            } 
            closeUnderlyingInputStreamIfNecessary();
          } 
        });
  }
  
  private void signalAsyncReadComplete() {
    this.stateChangeLock.lock();
    try {
      this.asyncReadComplete.signalAll();
    } finally {
      this.stateChangeLock.unlock();
    } 
  }
  
  public long skip(long n) throws IOException {
    long skipped;
    if (n <= 0L)
      return 0L; 
    if (n <= this.activeBuffer.remaining()) {
      this.activeBuffer.position((int)n + this.activeBuffer.position());
      return n;
    } 
    this.stateChangeLock.lock();
    try {
      skipped = skipInternal(n);
    } finally {
      this.stateChangeLock.unlock();
    } 
    return skipped;
  }
  
  private long skipInternal(long n) throws IOException {
    assert this.stateChangeLock.isLocked();
    waitForAsyncReadComplete();
    if (isEndOfStream())
      return 0L; 
    if (available() >= n) {
      int i = (int)n;
      i -= this.activeBuffer.remaining();
      assert i > 0;
      this.activeBuffer.position(0);
      this.activeBuffer.flip();
      this.readAheadBuffer.position(i + this.readAheadBuffer.position());
      swapBuffers();
      readAsync();
      return n;
    } 
    int skippedBytes = available();
    long toSkip = n - skippedBytes;
    this.activeBuffer.position(0);
    this.activeBuffer.flip();
    this.readAheadBuffer.position(0);
    this.readAheadBuffer.flip();
    long skippedFromInputStream = this.underlyingInputStream.skip(toSkip);
    readAsync();
    return skippedBytes + skippedFromInputStream;
  }
  
  private void swapBuffers() {
    ByteBuffer temp = this.activeBuffer;
    this.activeBuffer = this.readAheadBuffer;
    this.readAheadBuffer = temp;
  }
  
  private void waitForAsyncReadComplete() throws IOException {
    this.stateChangeLock.lock();
    try {
      this.isWaiting.set(true);
      while (this.readInProgress)
        this.asyncReadComplete.await(); 
    } catch (InterruptedException e) {
      InterruptedIOException iio = new InterruptedIOException(e.getMessage());
      iio.initCause(e);
      throw iio;
    } finally {
      this.isWaiting.set(false);
      this.stateChangeLock.unlock();
    } 
    checkReadException();
  }
}
