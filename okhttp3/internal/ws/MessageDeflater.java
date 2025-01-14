package okhttp3.internal.ws;

import java.io.Closeable;
import java.io.IOException;
import java.util.zip.Deflater;
import kotlin.Metadata;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import okio.Buffer;
import okio.ByteString;
import okio.DeflaterSink;
import okio.Sink;
import org.jetbrains.annotations.NotNull;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\0008\n\002\030\002\n\002\030\002\n\002\020\013\n\002\b\003\n\002\020\002\n\002\b\002\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\005\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\004\030\0002\0020\001B\017\022\006\020\003\032\0020\002¢\006\004\b\004\020\005J\017\020\007\032\0020\006H\026¢\006\004\b\007\020\bJ\025\020\013\032\0020\0062\006\020\n\032\0020\t¢\006\004\b\013\020\fJ\033\020\017\032\0020\002*\0020\t2\006\020\016\032\0020\rH\002¢\006\004\b\017\020\020R\024\020\021\032\0020\t8\002X\004¢\006\006\n\004\b\021\020\022R\024\020\024\032\0020\0238\002X\004¢\006\006\n\004\b\024\020\025R\024\020\027\032\0020\0268\002X\004¢\006\006\n\004\b\027\020\030R\024\020\003\032\0020\0028\002X\004¢\006\006\n\004\b\003\020\031¨\006\032"}, d2 = {"Lokhttp3/internal/ws/MessageDeflater;", "Ljava/io/Closeable;", "", "noContextTakeover", "<init>", "(Z)V", "", "close", "()V", "Lokio/Buffer;", "buffer", "deflate", "(Lokio/Buffer;)V", "Lokio/ByteString;", "suffix", "endsWith", "(Lokio/Buffer;Lokio/ByteString;)Z", "deflatedBytes", "Lokio/Buffer;", "Ljava/util/zip/Deflater;", "deflater", "Ljava/util/zip/Deflater;", "Lokio/DeflaterSink;", "deflaterSink", "Lokio/DeflaterSink;", "Z", "okhttp"})
public final class MessageDeflater implements Closeable {
  private final boolean noContextTakeover;
  
  @NotNull
  private final Buffer deflatedBytes;
  
  @NotNull
  private final Deflater deflater;
  
  @NotNull
  private final DeflaterSink deflaterSink;
  
  public MessageDeflater(boolean noContextTakeover) {
    this.noContextTakeover = noContextTakeover;
    this.deflatedBytes = new Buffer();
    this.deflater = new Deflater(-1, true);
    this.deflaterSink = new DeflaterSink((Sink)this.deflatedBytes, this.deflater);
  }
  
  public final void deflate(@NotNull Buffer buffer) throws IOException {
    Intrinsics.checkNotNullParameter(buffer, "buffer");
    if (!((this.deflatedBytes.size() == 0L) ? 1 : 0)) {
      String str = "Failed requirement.";
      throw new IllegalArgumentException(str.toString());
    } 
    if (this.noContextTakeover)
      this.deflater.reset(); 
    this.deflaterSink.write(buffer, buffer.size());
    this.deflaterSink.flush();
    if (endsWith(this.deflatedBytes, MessageDeflaterKt.access$getEMPTY_DEFLATE_BLOCK$p())) {
      long newSize = this.deflatedBytes.size() - 4L;
      Closeable closeable = (Closeable)Buffer.readAndWriteUnsafe$default(this.deflatedBytes, null, 1, null);
      Throwable throwable = null;
      try {
        Buffer.UnsafeCursor cursor = (Buffer.UnsafeCursor)closeable;
        int $i$a$-use-MessageDeflater$deflate$1 = 0;
        long l = 
          cursor.resizeBuffer(newSize);
      } catch (Throwable throwable1) {
        throwable = throwable1 = null;
        throw throwable1;
      } finally {
        CloseableKt.closeFinally(closeable, throwable);
      } 
    } else {
      this.deflatedBytes.writeByte(0);
    } 
    buffer.write(this.deflatedBytes, this.deflatedBytes.size());
  }
  
  public void close() throws IOException {
    this.deflaterSink.close();
  }
  
  private final boolean endsWith(Buffer $this$endsWith, ByteString suffix) {
    return $this$endsWith.rangeEquals($this$endsWith.size() - suffix.size(), suffix);
  }
}
