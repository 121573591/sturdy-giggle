package okio;

import java.io.IOException;
import javax.crypto.Cipher;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

@Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\000D\n\002\030\002\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\002\b\003\n\002\020\002\n\002\b\003\n\002\030\002\n\000\n\002\020\t\n\002\b\004\n\002\030\002\n\002\b\003\n\002\020\b\n\002\b\007\n\002\020\013\n\002\b\005\030\0002\0020\001B\027\022\006\020\003\032\0020\002\022\006\020\005\032\0020\004¢\006\004\b\006\020\007J\017\020\t\032\0020\bH\026¢\006\004\b\t\020\nJ\017\020\013\032\0020\bH\002¢\006\004\b\013\020\nJ\037\020\020\032\0020\0162\006\020\r\032\0020\f2\006\020\017\032\0020\016H\026¢\006\004\b\020\020\021J\017\020\022\032\0020\bH\002¢\006\004\b\022\020\nJ\017\020\024\032\0020\023H\026¢\006\004\b\024\020\025J\017\020\026\032\0020\bH\002¢\006\004\b\026\020\nR\024\020\030\032\0020\0278\002X\004¢\006\006\n\004\b\030\020\031R\024\020\032\032\0020\f8\002X\004¢\006\006\n\004\b\032\020\033R\027\020\005\032\0020\0048\006¢\006\f\n\004\b\005\020\034\032\004\b\035\020\036R\026\020 \032\0020\0378\002@\002X\016¢\006\006\n\004\b \020!R\026\020\"\032\0020\0378\002@\002X\016¢\006\006\n\004\b\"\020!R\024\020\003\032\0020\0028\002X\004¢\006\006\n\004\b\003\020#¨\006$"}, d2 = {"Lokio/CipherSource;", "Lokio/Source;", "Lokio/BufferedSource;", "source", "Ljavax/crypto/Cipher;", "cipher", "<init>", "(Lokio/BufferedSource;Ljavax/crypto/Cipher;)V", "", "close", "()V", "doFinal", "Lokio/Buffer;", "sink", "", "byteCount", "read", "(Lokio/Buffer;J)J", "refill", "Lokio/Timeout;", "timeout", "()Lokio/Timeout;", "update", "", "blockSize", "I", "buffer", "Lokio/Buffer;", "Ljavax/crypto/Cipher;", "getCipher", "()Ljavax/crypto/Cipher;", "", "closed", "Z", "final", "Lokio/BufferedSource;", "okio"})
@SourceDebugExtension({"SMAP\nCipherSource.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CipherSource.kt\nokio/CipherSource\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,120:1\n1#2:121\n*E\n"})
public final class CipherSource implements Source {
  @NotNull
  private final BufferedSource source;
  
  @NotNull
  private final Cipher cipher;
  
  private final int blockSize;
  
  @NotNull
  private final Buffer buffer;
  
  private boolean final;
  
  private boolean closed;
  
  public CipherSource(@NotNull BufferedSource source, @NotNull Cipher cipher) {
    this.source = source;
    this.cipher = cipher;
    this.blockSize = this.cipher.getBlockSize();
    this.buffer = new Buffer();
    if (!((this.blockSize > 0) ? 1 : 0)) {
      int $i$a$-require-CipherSource$1 = 0;
      String str = "Block cipher required " + this.cipher;
      throw new IllegalArgumentException(str.toString());
    } 
  }
  
  @NotNull
  public final Cipher getCipher() {
    return this.cipher;
  }
  
  public long read(@NotNull Buffer sink, long byteCount) throws IOException {
    Intrinsics.checkNotNullParameter(sink, "sink");
    if (!((byteCount >= 0L) ? 1 : 0)) {
      int $i$a$-require-CipherSource$read$1 = 0;
      String str = "byteCount < 0: " + byteCount;
      throw new IllegalArgumentException(str.toString());
    } 
    if (!(!this.closed ? 1 : 0)) {
      int $i$a$-check-CipherSource$read$2 = 0;
      String str = "closed";
      throw new IllegalStateException(str.toString());
    } 
    if (byteCount == 0L)
      return 0L; 
    refill();
    return this.buffer.read(sink, byteCount);
  }
  
  private final void refill() {
    while (this.buffer.size() == 0L && !this.final) {
      if (this.source.exhausted()) {
        this.final = true;
        doFinal();
        break;
      } 
      update();
    } 
  }
  
  private final void update() {
    Intrinsics.checkNotNull((this.source.getBuffer()).head);
    Segment head = (this.source.getBuffer()).head;
    int size = head.limit - head.pos;
    int outputSize = this.cipher.getOutputSize(size);
    while (outputSize > 8192) {
      if (size <= this.blockSize) {
        this.final = true;
        Intrinsics.checkNotNullExpressionValue(this.cipher.doFinal(this.source.readByteArray()), "doFinal(...)");
        this.buffer.write(this.cipher.doFinal(this.source.readByteArray()));
        return;
      } 
      size -= this.blockSize;
      outputSize = this.cipher.getOutputSize(size);
    } 
    Segment s = this.buffer.writableSegment$okio(outputSize);
    int ciphered = this.cipher.update(head.data, head.pos, size, s.data, s.pos);
    this.source.skip(size);
    s.limit += ciphered;
    Buffer buffer = this.buffer;
    buffer.setSize$okio(buffer.size() + ciphered);
    if (s.pos == s.limit) {
      this.buffer.head = s.pop();
      SegmentPool.recycle(s);
    } 
  }
  
  private final void doFinal() {
    int outputSize = this.cipher.getOutputSize(0);
    if (outputSize == 0)
      return; 
    Segment s = this.buffer.writableSegment$okio(outputSize);
    int ciphered = this.cipher.doFinal(s.data, s.pos);
    s.limit += ciphered;
    Buffer buffer = this.buffer;
    buffer.setSize$okio(buffer.size() + ciphered);
    if (s.pos == s.limit) {
      this.buffer.head = s.pop();
      SegmentPool.recycle(s);
    } 
  }
  
  @NotNull
  public Timeout timeout() {
    return this.source.timeout();
  }
  
  public void close() throws IOException {
    this.closed = true;
    this.source.close();
  }
}
