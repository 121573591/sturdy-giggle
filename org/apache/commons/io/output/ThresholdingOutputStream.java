package org.apache.commons.io.output;

import java.io.IOException;
import java.io.OutputStream;
import org.apache.commons.io.function.IOConsumer;
import org.apache.commons.io.function.IOFunction;

public class ThresholdingOutputStream extends OutputStream {
  private static final IOFunction<ThresholdingOutputStream, OutputStream> NOOP_OS_GETTER = os -> NullOutputStream.NULL_OUTPUT_STREAM;
  
  private final int threshold;
  
  private final IOConsumer<ThresholdingOutputStream> thresholdConsumer;
  
  private final IOFunction<ThresholdingOutputStream, OutputStream> outputStreamGetter;
  
  private long written;
  
  private boolean thresholdExceeded;
  
  public ThresholdingOutputStream(int threshold) {
    this(threshold, IOConsumer.noop(), NOOP_OS_GETTER);
  }
  
  public ThresholdingOutputStream(int threshold, IOConsumer<ThresholdingOutputStream> thresholdConsumer, IOFunction<ThresholdingOutputStream, OutputStream> outputStreamGetter) {
    this.threshold = threshold;
    this.thresholdConsumer = (thresholdConsumer == null) ? IOConsumer.noop() : thresholdConsumer;
    this.outputStreamGetter = (outputStreamGetter == null) ? NOOP_OS_GETTER : outputStreamGetter;
  }
  
  protected void checkThreshold(int count) throws IOException {
    if (!this.thresholdExceeded && this.written + count > this.threshold) {
      this.thresholdExceeded = true;
      thresholdReached();
    } 
  }
  
  public void close() throws IOException {
    try {
      flush();
    } catch (IOException iOException) {}
    getStream().close();
  }
  
  public void flush() throws IOException {
    getStream().flush();
  }
  
  public long getByteCount() {
    return this.written;
  }
  
  protected OutputStream getStream() throws IOException {
    return (OutputStream)this.outputStreamGetter.apply(this);
  }
  
  public int getThreshold() {
    return this.threshold;
  }
  
  public boolean isThresholdExceeded() {
    return (this.written > this.threshold);
  }
  
  protected void resetByteCount() {
    this.thresholdExceeded = false;
    this.written = 0L;
  }
  
  protected void setByteCount(long count) {
    this.written = count;
  }
  
  protected void thresholdReached() throws IOException {
    this.thresholdConsumer.accept(this);
  }
  
  public void write(byte[] b) throws IOException {
    checkThreshold(b.length);
    getStream().write(b);
    this.written += b.length;
  }
  
  public void write(byte[] b, int off, int len) throws IOException {
    checkThreshold(len);
    getStream().write(b, off, len);
    this.written += len;
  }
  
  public void write(int b) throws IOException {
    checkThreshold(1);
    getStream().write(b);
    this.written++;
  }
}
