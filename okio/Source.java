package okio;

import java.io.Closeable;
import java.io.IOException;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\000*\n\002\030\002\n\002\030\002\n\002\030\002\n\002\020\002\n\002\b\002\n\002\030\002\n\000\n\002\020\t\n\002\b\003\n\002\030\002\n\002\b\003\bf\030\0002\0060\001j\002`\002J\017\020\004\032\0020\003H&¢\006\004\b\004\020\005J\037\020\n\032\0020\b2\006\020\007\032\0020\0062\006\020\t\032\0020\bH&¢\006\004\b\n\020\013J\017\020\r\032\0020\fH&¢\006\004\b\r\020\016ø\001\000\002\006\n\004\b!0\001¨\006\017À\006\001"}, d2 = {"Lokio/Source;", "Ljava/io/Closeable;", "Lokio/Closeable;", "", "close", "()V", "Lokio/Buffer;", "sink", "", "byteCount", "read", "(Lokio/Buffer;J)J", "Lokio/Timeout;", "timeout", "()Lokio/Timeout;", "okio"})
public interface Source extends Closeable {
  long read(@NotNull Buffer paramBuffer, long paramLong) throws IOException;
  
  @NotNull
  Timeout timeout();
  
  void close() throws IOException;
}
