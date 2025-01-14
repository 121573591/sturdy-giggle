package okio;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\000*\n\002\030\002\n\002\030\002\n\002\030\002\n\002\020\002\n\002\b\003\n\002\030\002\n\002\b\002\n\002\030\002\n\000\n\002\020\t\n\002\b\004\bf\030\0002\0020\0012\0020\002J\017\020\004\032\0020\003H&¢\006\004\b\004\020\005J\017\020\006\032\0020\003H&¢\006\004\b\006\020\005J\017\020\b\032\0020\007H&¢\006\004\b\b\020\tJ\037\020\016\032\0020\0032\006\020\013\032\0020\n2\006\020\r\032\0020\fH&¢\006\004\b\016\020\017ø\001\000\002\006\n\004\b!0\001¨\006\020À\006\001"}, d2 = {"Lokio/Sink;", "Ljava/io/Closeable;", "Ljava/io/Flushable;", "", "close", "()V", "flush", "Lokio/Timeout;", "timeout", "()Lokio/Timeout;", "Lokio/Buffer;", "source", "", "byteCount", "write", "(Lokio/Buffer;J)V", "okio"})
public interface Sink extends Closeable, Flushable {
  void write(@NotNull Buffer paramBuffer, long paramLong) throws IOException;
  
  void flush() throws IOException;
  
  @NotNull
  Timeout timeout();
  
  void close() throws IOException;
}
