package okhttp3.internal.ws;

import java.io.Closeable;
import java.io.IOException;
import java.util.zip.Inflater;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okio.Buffer;
import okio.InflaterSource;
import okio.Source;
import org.jetbrains.annotations.NotNull;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\0000\n\002\030\002\n\002\030\002\n\002\020\013\n\002\b\003\n\002\020\002\n\002\b\002\n\002\030\002\n\002\b\005\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\004\030\0002\0020\001B\017\022\006\020\003\032\0020\002¢\006\004\b\004\020\005J\017\020\007\032\0020\006H\026¢\006\004\b\007\020\bJ\025\020\013\032\0020\0062\006\020\n\032\0020\t¢\006\004\b\013\020\fR\024\020\r\032\0020\t8\002X\004¢\006\006\n\004\b\r\020\016R\024\020\020\032\0020\0178\002X\004¢\006\006\n\004\b\020\020\021R\024\020\023\032\0020\0228\002X\004¢\006\006\n\004\b\023\020\024R\024\020\003\032\0020\0028\002X\004¢\006\006\n\004\b\003\020\025¨\006\026"}, d2 = {"Lokhttp3/internal/ws/MessageInflater;", "Ljava/io/Closeable;", "", "noContextTakeover", "<init>", "(Z)V", "", "close", "()V", "Lokio/Buffer;", "buffer", "inflate", "(Lokio/Buffer;)V", "deflatedBytes", "Lokio/Buffer;", "Ljava/util/zip/Inflater;", "inflater", "Ljava/util/zip/Inflater;", "Lokio/InflaterSource;", "inflaterSource", "Lokio/InflaterSource;", "Z", "okhttp"})
public final class MessageInflater implements Closeable {
  private final boolean noContextTakeover;
  
  @NotNull
  private final Buffer deflatedBytes;
  
  @NotNull
  private final Inflater inflater;
  
  @NotNull
  private final InflaterSource inflaterSource;
  
  public MessageInflater(boolean noContextTakeover) {
    this.noContextTakeover = noContextTakeover;
    this.deflatedBytes = new Buffer();
    this.inflater = new Inflater(true);
    this.inflaterSource = new InflaterSource((Source)this.deflatedBytes, this.inflater);
  }
  
  public final void inflate(@NotNull Buffer buffer) throws IOException {
    Intrinsics.checkNotNullParameter(buffer, "buffer");
    if (!((this.deflatedBytes.size() == 0L) ? 1 : 0)) {
      String str = "Failed requirement.";
      throw new IllegalArgumentException(str.toString());
    } 
    if (this.noContextTakeover)
      this.inflater.reset(); 
    this.deflatedBytes.writeAll((Source)buffer);
    this.deflatedBytes.writeInt(65535);
    long totalBytesToRead = this.inflater.getBytesRead() + this.deflatedBytes.size();
    do {
      this.inflaterSource.readOrInflate(buffer, Long.MAX_VALUE);
    } while (this.inflater.getBytesRead() < totalBytesToRead);
  }
  
  public void close() throws IOException {
    this.inflaterSource.close();
  }
}
