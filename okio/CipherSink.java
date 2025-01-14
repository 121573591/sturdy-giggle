package okio;

import java.io.IOException;
import javax.crypto.Cipher;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

@Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\000J\n\002\030\002\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\002\b\003\n\002\020\002\n\002\b\002\n\002\020\003\n\002\b\003\n\002\030\002\n\002\b\002\n\002\030\002\n\000\n\002\020\t\n\000\n\002\020\b\n\002\b\n\n\002\020\013\n\002\b\004\030\0002\0020\001B\027\022\006\020\003\032\0020\002\022\006\020\005\032\0020\004¢\006\004\b\006\020\007J\017\020\t\032\0020\bH\026¢\006\004\b\t\020\nJ\021\020\f\032\004\030\0010\013H\002¢\006\004\b\f\020\rJ\017\020\016\032\0020\bH\026¢\006\004\b\016\020\nJ\017\020\020\032\0020\017H\026¢\006\004\b\020\020\021J\037\020\027\032\0020\0262\006\020\023\032\0020\0222\006\020\025\032\0020\024H\002¢\006\004\b\027\020\030J\037\020\032\032\0020\b2\006\020\023\032\0020\0222\006\020\031\032\0020\024H\026¢\006\004\b\032\020\033R\024\020\034\032\0020\0268\002X\004¢\006\006\n\004\b\034\020\035R\027\020\005\032\0020\0048\006¢\006\f\n\004\b\005\020\036\032\004\b\037\020 R\026\020\"\032\0020!8\002@\002X\016¢\006\006\n\004\b\"\020#R\024\020\003\032\0020\0028\002X\004¢\006\006\n\004\b\003\020$¨\006%"}, d2 = {"Lokio/CipherSink;", "Lokio/Sink;", "Lokio/BufferedSink;", "sink", "Ljavax/crypto/Cipher;", "cipher", "<init>", "(Lokio/BufferedSink;Ljavax/crypto/Cipher;)V", "", "close", "()V", "", "doFinal", "()Ljava/lang/Throwable;", "flush", "Lokio/Timeout;", "timeout", "()Lokio/Timeout;", "Lokio/Buffer;", "source", "", "remaining", "", "update", "(Lokio/Buffer;J)I", "byteCount", "write", "(Lokio/Buffer;J)V", "blockSize", "I", "Ljavax/crypto/Cipher;", "getCipher", "()Ljavax/crypto/Cipher;", "", "closed", "Z", "Lokio/BufferedSink;", "okio"})
@SourceDebugExtension({"SMAP\nCipherSink.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CipherSink.kt\nokio/CipherSink\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 Util.kt\nokio/-SegmentedByteString\n*L\n1#1,148:1\n1#2:149\n86#3:150\n*S KotlinDebug\n*F\n+ 1 CipherSink.kt\nokio/CipherSink\n*L\n47#1:150\n*E\n"})
public final class CipherSink implements Sink {
  @NotNull
  private final BufferedSink sink;
  
  @NotNull
  private final Cipher cipher;
  
  private final int blockSize;
  
  private boolean closed;
  
  public CipherSink(@NotNull BufferedSink sink, @NotNull Cipher cipher) {
    this.sink = sink;
    this.cipher = cipher;
    this.blockSize = this.cipher.getBlockSize();
    if (!((this.blockSize > 0) ? 1 : 0)) {
      int $i$a$-require-CipherSink$1 = 0;
      String str = "Block cipher required " + this.cipher;
      throw new IllegalArgumentException(str.toString());
    } 
  }
  
  @NotNull
  public final Cipher getCipher() {
    return this.cipher;
  }
  
  public void write(@NotNull Buffer source, long byteCount) throws IOException {
    Intrinsics.checkNotNullParameter(source, "source");
    -SegmentedByteString.checkOffsetAndCount(source.size(), 0L, byteCount);
    if (!(!this.closed ? 1 : 0)) {
      int $i$a$-check-CipherSink$write$1 = 0;
      String str = "closed";
      throw new IllegalStateException(str.toString());
    } 
    long remaining = byteCount;
    while (remaining > 0L) {
      int size = update(source, remaining);
      remaining -= size;
    } 
  }
  
  private final int update(Buffer source, long remaining) {
    Intrinsics.checkNotNull(source.head);
    Segment head = source.head;
    int b$iv = head.limit - head.pos, $i$f$minOf = 0, size = (int)Math.min(remaining, b$iv);
    Buffer buffer = this.sink.getBuffer();
    int outputSize = this.cipher.getOutputSize(size);
    while (outputSize > 8192) {
      if (size <= this.blockSize) {
        Intrinsics.checkNotNullExpressionValue(this.cipher.update(source.readByteArray(remaining)), "update(...)");
        this.sink.write(this.cipher.update(source.readByteArray(remaining)));
        return (int)remaining;
      } 
      size -= this.blockSize;
      outputSize = this.cipher.getOutputSize(size);
    } 
    Segment s = buffer.writableSegment$okio(outputSize);
    int ciphered = this.cipher.update(head.data, head.pos, size, s.data, s.limit);
    s.limit += ciphered;
    buffer.setSize$okio(buffer.size() + ciphered);
    if (s.pos == s.limit) {
      buffer.head = s.pop();
      SegmentPool.recycle(s);
    } 
    this.sink.emitCompleteSegments();
    source.setSize$okio(source.size() - size);
    head.pos += size;
    if (head.pos == head.limit) {
      source.head = head.pop();
      SegmentPool.recycle(head);
    } 
    return size;
  }
  
  public void flush() {
    this.sink.flush();
  }
  
  @NotNull
  public Timeout timeout() {
    return this.sink.timeout();
  }
  
  public void close() throws IOException {
    if (this.closed)
      return; 
    this.closed = true;
    Throwable thrown = doFinal();
    try {
      this.sink.close();
    } catch (Throwable e) {
      if (thrown == null)
        thrown = e; 
    } 
    if (thrown != null)
      throw thrown; 
  }
  
  private final Throwable doFinal() {
    int outputSize = this.cipher.getOutputSize(0);
    if (outputSize == 0)
      return null; 
    if (outputSize > 8192) {
      try {
        Intrinsics.checkNotNullExpressionValue(this.cipher.doFinal(), "doFinal(...)");
        this.sink.write(this.cipher.doFinal());
      } catch (Throwable t) {
        return t;
      } 
      return null;
    } 
    Throwable thrown = null;
    Buffer buffer = this.sink.getBuffer();
    Segment s = buffer.writableSegment$okio(outputSize);
    try {
      int ciphered = this.cipher.doFinal(s.data, s.limit);
      s.limit += ciphered;
      buffer.setSize$okio(buffer.size() + ciphered);
    } catch (Throwable e) {
      thrown = e;
    } 
    if (s.pos == s.limit) {
      buffer.head = s.pop();
      SegmentPool.recycle(s);
    } 
    return thrown;
  }
}
