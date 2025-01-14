package okio;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

@Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\000V\n\002\030\002\n\002\030\002\n\002\030\002\n\002\020\013\n\002\b\003\n\002\030\002\n\002\b\002\n\002\020\002\n\002\b\004\n\002\020\t\n\002\b\002\n\002\030\002\n\002\b\005\n\002\020\022\n\000\n\002\020\b\n\002\b\f\n\002\030\002\n\002\b\r\n\002\030\002\n\002\030\002\n\002\b\013\b&\030\0002\0060\001j\002`\002:\002?@B\017\022\006\020\004\032\0020\003¢\006\004\b\005\020\006J\r\020\b\032\0020\007¢\006\004\b\b\020\tJ\r\020\013\032\0020\n¢\006\004\b\013\020\fJ\r\020\r\032\0020\n¢\006\004\b\r\020\fJ\025\020\020\032\0020\0172\006\020\016\032\0020\007¢\006\004\b\020\020\021J\025\020\020\032\0020\0172\006\020\023\032\0020\022¢\006\004\b\020\020\024J\017\020\025\032\0020\nH$¢\006\004\b\025\020\fJ\017\020\026\032\0020\nH$¢\006\004\b\026\020\fJ/\020\035\032\0020\0322\006\020\027\032\0020\0172\006\020\031\032\0020\0302\006\020\033\032\0020\0322\006\020\034\032\0020\032H$¢\006\004\b\035\020\036J\027\020 \032\0020\n2\006\020\037\032\0020\017H$¢\006\004\b \020!J\017\020\"\032\0020\017H$¢\006\004\b\"\020#J/\020$\032\0020\n2\006\020\027\032\0020\0172\006\020\031\032\0020\0302\006\020\033\032\0020\0322\006\020\034\032\0020\032H$¢\006\004\b$\020%J-\020&\032\0020\0322\006\020\027\032\0020\0172\006\020\031\032\0020\0302\006\020\033\032\0020\0322\006\020\034\032\0020\032¢\006\004\b&\020\036J%\020&\032\0020\0172\006\020\027\032\0020\0172\006\020\016\032\0020'2\006\020\034\032\0020\017¢\006\004\b&\020(J'\020)\032\0020\0172\006\020\027\032\0020\0172\006\020\016\032\0020'2\006\020\034\032\0020\017H\002¢\006\004\b)\020(J\035\020*\032\0020\n2\006\020\016\032\0020\0072\006\020\020\032\0020\017¢\006\004\b*\020+J\035\020*\032\0020\n2\006\020\023\032\0020\0222\006\020\020\032\0020\017¢\006\004\b*\020,J\025\020-\032\0020\n2\006\020\037\032\0020\017¢\006\004\b-\020!J\027\020\016\032\0020\0072\b\b\002\020\027\032\0020\017¢\006\004\b\016\020.J\r\020\037\032\0020\017¢\006\004\b\037\020#J\027\020\023\032\0020\0222\b\b\002\020\027\032\0020\017¢\006\004\b\023\020/J-\0200\032\0020\n2\006\020\027\032\0020\0172\006\020\031\032\0020\0302\006\020\033\032\0020\0322\006\020\034\032\0020\032¢\006\004\b0\020%J%\0200\032\0020\n2\006\020\027\032\0020\0172\006\020\023\032\0020'2\006\020\034\032\0020\017¢\006\004\b0\0201J'\0202\032\0020\n2\006\020\027\032\0020\0172\006\020\023\032\0020'2\006\020\034\032\0020\017H\002¢\006\004\b2\0201R\026\0203\032\0020\0038\002@\002X\016¢\006\006\n\004\b3\0204R\033\0207\032\00605j\002`68\006¢\006\f\n\004\b7\0208\032\004\b9\020:R\026\020;\032\0020\0328\002@\002X\016¢\006\006\n\004\b;\020<R\027\020\004\032\0020\0038\006¢\006\f\n\004\b\004\0204\032\004\b=\020>¨\006A"}, d2 = {"Lokio/FileHandle;", "Ljava/io/Closeable;", "Lokio/Closeable;", "", "readWrite", "<init>", "(Z)V", "Lokio/Sink;", "appendingSink", "()Lokio/Sink;", "", "close", "()V", "flush", "sink", "", "position", "(Lokio/Sink;)J", "Lokio/Source;", "source", "(Lokio/Source;)J", "protectedClose", "protectedFlush", "fileOffset", "", "array", "", "arrayOffset", "byteCount", "protectedRead", "(J[BII)I", "size", "protectedResize", "(J)V", "protectedSize", "()J", "protectedWrite", "(J[BII)V", "read", "Lokio/Buffer;", "(JLokio/Buffer;J)J", "readNoCloseCheck", "reposition", "(Lokio/Sink;J)V", "(Lokio/Source;J)V", "resize", "(J)Lokio/Sink;", "(J)Lokio/Source;", "write", "(JLokio/Buffer;J)V", "writeNoCloseCheck", "closed", "Z", "Ljava/util/concurrent/locks/ReentrantLock;", "Lokio/Lock;", "lock", "Ljava/util/concurrent/locks/ReentrantLock;", "getLock", "()Ljava/util/concurrent/locks/ReentrantLock;", "openStreamCount", "I", "getReadWrite", "()Z", "FileHandleSink", "FileHandleSource", "okio"})
@SourceDebugExtension({"SMAP\nFileHandle.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FileHandle.kt\nokio/FileHandle\n+ 2 -JvmPlatform.kt\nokio/_JvmPlatformKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 RealBufferedSource.kt\nokio/RealBufferedSource\n+ 5 RealBufferedSink.kt\nokio/RealBufferedSink\n+ 6 Util.kt\nokio/-SegmentedByteString\n*L\n1#1,444:1\n33#2:445\n33#2:447\n33#2:448\n33#2:449\n33#2:450\n33#2:451\n33#2:452\n33#2:453\n33#2:457\n33#2:459\n1#3:446\n62#4:454\n62#4:455\n62#4:456\n51#5:458\n86#6:460\n86#6:461\n*S KotlinDebug\n*F\n+ 1 FileHandle.kt\nokio/FileHandle\n*L\n69#1:445\n81#1:447\n92#1:448\n105#1:449\n119#1:450\n129#1:451\n139#1:452\n151#1:453\n221#1:457\n287#1:459\n169#1:454\n195#1:455\n202#1:456\n248#1:458\n345#1:460\n374#1:461\n*E\n"})
public abstract class FileHandle implements Closeable {
  private final boolean readWrite;
  
  private boolean closed;
  
  private int openStreamCount;
  
  @NotNull
  private final ReentrantLock lock;
  
  public FileHandle(boolean readWrite) {
    this.readWrite = readWrite;
    this.lock = _JvmPlatformKt.newLock();
  }
  
  public final boolean getReadWrite() {
    return this.readWrite;
  }
  
  @NotNull
  public final ReentrantLock getLock() {
    return this.lock;
  }
  
  public final int read(long fileOffset, @NotNull byte[] array, int arrayOffset, int byteCount) throws IOException {
    Intrinsics.checkNotNullParameter(array, "array");
    ReentrantLock $this$withLock$iv = this.lock;
    int $i$f$withLock = 0;
    ReentrantLock reentrantLock1 = $this$withLock$iv;
    reentrantLock1.lock();
    try {
      int $i$a$-withLock-FileHandle$read$1 = 0;
      if (!(!this.closed ? 1 : 0)) {
        int $i$a$-check-FileHandle$read$1$1 = 0;
        String str = "closed";
        throw new IllegalStateException(str.toString());
      } 
      Unit unit = Unit.INSTANCE;
    } finally {
      reentrantLock1.unlock();
    } 
    return protectedRead(fileOffset, array, arrayOffset, byteCount);
  }
  
  public final long read(long fileOffset, @NotNull Buffer sink, long byteCount) throws IOException {
    Intrinsics.checkNotNullParameter(sink, "sink");
    ReentrantLock $this$withLock$iv = this.lock;
    int $i$f$withLock = 0;
    ReentrantLock reentrantLock1 = $this$withLock$iv;
    reentrantLock1.lock();
    try {
      int $i$a$-withLock-FileHandle$read$2 = 0;
      if (!(!this.closed ? 1 : 0)) {
        int $i$a$-check-FileHandle$read$2$1 = 0;
        String str = "closed";
        throw new IllegalStateException(str.toString());
      } 
      Unit unit = Unit.INSTANCE;
    } finally {
      reentrantLock1.unlock();
    } 
    return readNoCloseCheck(fileOffset, sink, byteCount);
  }
  
  public final long size() throws IOException {
    ReentrantLock $this$withLock$iv = this.lock;
    int $i$f$withLock = 0;
    ReentrantLock reentrantLock1 = $this$withLock$iv;
    reentrantLock1.lock();
    try {
      int $i$a$-withLock-FileHandle$size$1 = 0;
      if (!(!this.closed ? 1 : 0)) {
        int $i$a$-check-FileHandle$size$1$1 = 0;
        String str = "closed";
        throw new IllegalStateException(str.toString());
      } 
      Unit unit = Unit.INSTANCE;
    } finally {
      reentrantLock1.unlock();
    } 
    return protectedSize();
  }
  
  public final void resize(long size) throws IOException {
    if (!this.readWrite) {
      int $i$a$-check-FileHandle$resize$1 = 0;
      String str = "file handle is read-only";
      throw new IllegalStateException(str.toString());
    } 
    ReentrantLock $this$withLock$iv = this.lock;
    int $i$f$withLock = 0;
    ReentrantLock reentrantLock1 = $this$withLock$iv;
    reentrantLock1.lock();
    try {
      int $i$a$-withLock-FileHandle$resize$2 = 0;
      if (!(!this.closed ? 1 : 0)) {
        int $i$a$-check-FileHandle$resize$2$1 = 0;
        String str = "closed";
        throw new IllegalStateException(str.toString());
      } 
      Unit unit = Unit.INSTANCE;
    } finally {
      reentrantLock1.unlock();
    } 
    protectedResize(size);
  }
  
  public final void write(long fileOffset, @NotNull byte[] array, int arrayOffset, int byteCount) {
    Intrinsics.checkNotNullParameter(array, "array");
    if (!this.readWrite) {
      int $i$a$-check-FileHandle$write$1 = 0;
      String str = "file handle is read-only";
      throw new IllegalStateException(str.toString());
    } 
    ReentrantLock $this$withLock$iv = this.lock;
    int $i$f$withLock = 0;
    ReentrantLock reentrantLock1 = $this$withLock$iv;
    reentrantLock1.lock();
    try {
      int $i$a$-withLock-FileHandle$write$2 = 0;
      if (!(!this.closed ? 1 : 0)) {
        int $i$a$-check-FileHandle$write$2$1 = 0;
        String str = "closed";
        throw new IllegalStateException(str.toString());
      } 
      Unit unit = Unit.INSTANCE;
    } finally {
      reentrantLock1.unlock();
    } 
    protectedWrite(fileOffset, array, arrayOffset, byteCount);
  }
  
  public final void write(long fileOffset, @NotNull Buffer source, long byteCount) throws IOException {
    Intrinsics.checkNotNullParameter(source, "source");
    if (!this.readWrite) {
      int $i$a$-check-FileHandle$write$3 = 0;
      String str = "file handle is read-only";
      throw new IllegalStateException(str.toString());
    } 
    ReentrantLock $this$withLock$iv = this.lock;
    int $i$f$withLock = 0;
    ReentrantLock reentrantLock1 = $this$withLock$iv;
    reentrantLock1.lock();
    try {
      int $i$a$-withLock-FileHandle$write$4 = 0;
      if (!(!this.closed ? 1 : 0)) {
        int $i$a$-check-FileHandle$write$4$1 = 0;
        String str = "closed";
        throw new IllegalStateException(str.toString());
      } 
      Unit unit = Unit.INSTANCE;
    } finally {
      reentrantLock1.unlock();
    } 
    writeNoCloseCheck(fileOffset, source, byteCount);
  }
  
  public final void flush() throws IOException {
    if (!this.readWrite) {
      int $i$a$-check-FileHandle$flush$1 = 0;
      String str = "file handle is read-only";
      throw new IllegalStateException(str.toString());
    } 
    ReentrantLock $this$withLock$iv = this.lock;
    int $i$f$withLock = 0;
    ReentrantLock reentrantLock1 = $this$withLock$iv;
    reentrantLock1.lock();
    try {
      int $i$a$-withLock-FileHandle$flush$2 = 0;
      if (!(!this.closed ? 1 : 0)) {
        int $i$a$-check-FileHandle$flush$2$1 = 0;
        String str = "closed";
        throw new IllegalStateException(str.toString());
      } 
      Unit unit = Unit.INSTANCE;
    } finally {
      reentrantLock1.unlock();
    } 
    protectedFlush();
  }
  
  @NotNull
  public final Source source(long fileOffset) throws IOException {
    ReentrantLock $this$withLock$iv = this.lock;
    int $i$f$withLock = 0;
    ReentrantLock reentrantLock1 = $this$withLock$iv;
    reentrantLock1.lock();
    try {
      int $i$a$-withLock-FileHandle$source$1 = 0;
      if (!(!this.closed ? 1 : 0)) {
        int $i$a$-check-FileHandle$source$1$1 = 0;
        String str = "closed";
        throw new IllegalStateException(str.toString());
      } 
      int i = this.openStreamCount;
      this.openStreamCount = i + 1;
      $i$a$-withLock-FileHandle$source$1 = i;
    } finally {
      reentrantLock1.unlock();
    } 
    return new FileHandleSource(this, fileOffset);
  }
  
  public final long position(@NotNull Source source) throws IOException {
    Intrinsics.checkNotNullParameter(source, "source");
    Source source1 = source;
    long bufferSize = 0L;
    if (source1 instanceof RealBufferedSource) {
      RealBufferedSource this_$iv = (RealBufferedSource)source1;
      int $i$f$getBuffer = 0;
      bufferSize = this_$iv.bufferField.size();
      source1 = ((RealBufferedSource)source1).source;
    } 
    if (!((source1 instanceof FileHandleSource && ((FileHandleSource)source1).getFileHandle() == this) ? 1 : 0)) {
      int $i$a$-require-FileHandle$position$1 = 0;
      String str = "source was not created by this FileHandle";
      throw new IllegalArgumentException(str.toString());
    } 
    if (!(!((FileHandleSource)source1).getClosed() ? 1 : 0)) {
      int $i$a$-check-FileHandle$position$2 = 0;
      String str = "closed";
      throw new IllegalStateException(str.toString());
    } 
    return ((FileHandleSource)source1).getPosition() - bufferSize;
  }
  
  public final void reposition(@NotNull Source source, long position) throws IOException {
    Intrinsics.checkNotNullParameter(source, "source");
    if (source instanceof RealBufferedSource) {
      Source fileHandleSource = ((RealBufferedSource)source).source;
      if (!((fileHandleSource instanceof FileHandleSource && ((FileHandleSource)fileHandleSource).getFileHandle() == this) ? 1 : 0)) {
        int $i$a$-require-FileHandle$reposition$1 = 0;
        String str = "source was not created by this FileHandle";
        throw new IllegalArgumentException(str.toString());
      } 
      if (!(!((FileHandleSource)fileHandleSource).getClosed() ? 1 : 0)) {
        int $i$a$-check-FileHandle$reposition$2 = 0;
        String str = "closed";
        throw new IllegalStateException(str.toString());
      } 
      RealBufferedSource this_$iv = (RealBufferedSource)source;
      int $i$f$getBuffer = 0;
      long bufferSize = this_$iv.bufferField.size();
      long toSkip = position - ((FileHandleSource)fileHandleSource).getPosition() - bufferSize;
      if ((0L <= toSkip) ? ((toSkip < bufferSize)) : false) {
        ((RealBufferedSource)source).skip(toSkip);
      } else {
        RealBufferedSource realBufferedSource = (RealBufferedSource)source;
        int i = 0;
        realBufferedSource.bufferField.clear();
        ((FileHandleSource)fileHandleSource).setPosition(position);
      } 
    } else {
      if (!((source instanceof FileHandleSource && ((FileHandleSource)source).getFileHandle() == this) ? 1 : 0)) {
        int $i$a$-require-FileHandle$reposition$3 = 0;
        String str = "source was not created by this FileHandle";
        throw new IllegalArgumentException(str.toString());
      } 
      if (!(!((FileHandleSource)source).getClosed() ? 1 : 0)) {
        int $i$a$-check-FileHandle$reposition$4 = 0;
        String str = "closed";
        throw new IllegalStateException(str.toString());
      } 
      ((FileHandleSource)source).setPosition(position);
    } 
  }
  
  @NotNull
  public final Sink sink(long fileOffset) throws IOException {
    if (!this.readWrite) {
      int $i$a$-check-FileHandle$sink$1 = 0;
      String str = "file handle is read-only";
      throw new IllegalStateException(str.toString());
    } 
    ReentrantLock $this$withLock$iv = this.lock;
    int $i$f$withLock = 0;
    ReentrantLock reentrantLock1 = $this$withLock$iv;
    reentrantLock1.lock();
    try {
      int $i$a$-withLock-FileHandle$sink$2 = 0;
      if (!(!this.closed ? 1 : 0)) {
        int $i$a$-check-FileHandle$sink$2$1 = 0;
        String str = "closed";
        throw new IllegalStateException(str.toString());
      } 
      int i = this.openStreamCount;
      this.openStreamCount = i + 1;
      $i$a$-withLock-FileHandle$sink$2 = i;
    } finally {
      reentrantLock1.unlock();
    } 
    return new FileHandleSink(this, fileOffset);
  }
  
  @NotNull
  public final Sink appendingSink() throws IOException {
    return sink(size());
  }
  
  public final long position(@NotNull Sink sink) throws IOException {
    Intrinsics.checkNotNullParameter(sink, "sink");
    Sink sink1 = sink;
    long bufferSize = 0L;
    if (sink1 instanceof RealBufferedSink) {
      RealBufferedSink this_$iv = (RealBufferedSink)sink1;
      int $i$f$getBuffer = 0;
      bufferSize = this_$iv.bufferField.size();
      sink1 = ((RealBufferedSink)sink1).sink;
    } 
    if (!((sink1 instanceof FileHandleSink && ((FileHandleSink)sink1).getFileHandle() == this) ? 1 : 0)) {
      int $i$a$-require-FileHandle$position$3 = 0;
      String str = "sink was not created by this FileHandle";
      throw new IllegalArgumentException(str.toString());
    } 
    if (!(!((FileHandleSink)sink1).getClosed() ? 1 : 0)) {
      int $i$a$-check-FileHandle$position$4 = 0;
      String str = "closed";
      throw new IllegalStateException(str.toString());
    } 
    return ((FileHandleSink)sink1).getPosition() + bufferSize;
  }
  
  public final void reposition(@NotNull Sink sink, long position) throws IOException {
    Intrinsics.checkNotNullParameter(sink, "sink");
    if (sink instanceof RealBufferedSink) {
      Sink fileHandleSink = ((RealBufferedSink)sink).sink;
      if (!((fileHandleSink instanceof FileHandleSink && ((FileHandleSink)fileHandleSink).getFileHandle() == this) ? 1 : 0)) {
        int $i$a$-require-FileHandle$reposition$5 = 0;
        String str = "sink was not created by this FileHandle";
        throw new IllegalArgumentException(str.toString());
      } 
      if (!(!((FileHandleSink)fileHandleSink).getClosed() ? 1 : 0)) {
        int $i$a$-check-FileHandle$reposition$6 = 0;
        String str = "closed";
        throw new IllegalStateException(str.toString());
      } 
      ((RealBufferedSink)sink).emit();
      ((FileHandleSink)fileHandleSink).setPosition(position);
    } else {
      if (!((sink instanceof FileHandleSink && ((FileHandleSink)sink).getFileHandle() == this) ? 1 : 0)) {
        int $i$a$-require-FileHandle$reposition$7 = 0;
        String str = "sink was not created by this FileHandle";
        throw new IllegalArgumentException(str.toString());
      } 
      if (!(!((FileHandleSink)sink).getClosed() ? 1 : 0)) {
        int $i$a$-check-FileHandle$reposition$8 = 0;
        String str = "closed";
        throw new IllegalStateException(str.toString());
      } 
      ((FileHandleSink)sink).setPosition(position);
    } 
  }
  
  public final void close() throws IOException {
    ReentrantLock $this$withLock$iv = this.lock;
    int $i$f$withLock = 0;
    ReentrantLock reentrantLock1 = $this$withLock$iv;
    reentrantLock1.lock();
    try {
      int $i$a$-withLock-FileHandle$close$1 = 0;
      if (this.closed)
        return; 
      this.closed = true;
      if (this.openStreamCount != 0)
        return; 
      Unit unit = Unit.INSTANCE;
    } finally {
      reentrantLock1.unlock();
    } 
    protectedClose();
  }
  
  private final long readNoCloseCheck(long fileOffset, Buffer sink, long byteCount) {
    if (!((byteCount >= 0L) ? 1 : 0)) {
      int $i$a$-require-FileHandle$readNoCloseCheck$1 = 0;
      String str = "byteCount < 0: " + byteCount;
      throw new IllegalArgumentException(str.toString());
    } 
    long currentOffset = fileOffset;
    long targetOffset = fileOffset + byteCount;
    while (currentOffset < targetOffset) {
      Segment tail = sink.writableSegment$okio(1);
      long l = targetOffset - currentOffset;
      int b$iv = 8192 - tail.limit, $i$f$minOf = 0;
      int readByteCount = protectedRead(currentOffset, tail.data, tail.limit, (int)Math.min(l, b$iv));
      if (readByteCount == -1) {
        if (tail.pos == tail.limit) {
          sink.head = tail.pop();
          SegmentPool.recycle(tail);
        } 
        if (fileOffset == currentOffset)
          return -1L; 
        break;
      } 
      tail.limit += readByteCount;
      currentOffset += readByteCount;
      sink.setSize$okio(sink.size() + readByteCount);
    } 
    return currentOffset - fileOffset;
  }
  
  private final void writeNoCloseCheck(long fileOffset, Buffer source, long byteCount) {
    -SegmentedByteString.checkOffsetAndCount(source.size(), 0L, byteCount);
    long currentOffset = fileOffset;
    long targetOffset = fileOffset + byteCount;
    while (currentOffset < targetOffset) {
      Intrinsics.checkNotNull(source.head);
      Segment head = source.head;
      long l = targetOffset - currentOffset;
      int b$iv = head.limit - head.pos, $i$f$minOf = 0, toCopy = (int)Math.min(l, b$iv);
      protectedWrite(currentOffset, head.data, head.pos, toCopy);
      head.pos += toCopy;
      currentOffset += toCopy;
      source.setSize$okio(source.size() - toCopy);
      if (head.pos == head.limit) {
        source.head = head.pop();
        SegmentPool.recycle(head);
      } 
    } 
  }
  
  protected abstract int protectedRead(long paramLong, @NotNull byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws IOException;
  
  protected abstract void protectedWrite(long paramLong, @NotNull byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws IOException;
  
  protected abstract void protectedFlush() throws IOException;
  
  protected abstract void protectedResize(long paramLong) throws IOException;
  
  protected abstract long protectedSize() throws IOException;
  
  protected abstract void protectedClose() throws IOException;
  
  @Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\0006\n\002\030\002\n\002\030\002\n\002\030\002\n\000\n\002\020\t\n\002\b\003\n\002\020\002\n\002\b\003\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\004\n\002\020\013\n\002\b\017\b\002\030\0002\0020\001B\027\022\006\020\003\032\0020\002\022\006\020\005\032\0020\004¢\006\004\b\006\020\007J\017\020\t\032\0020\bH\026¢\006\004\b\t\020\nJ\017\020\013\032\0020\bH\026¢\006\004\b\013\020\nJ\017\020\r\032\0020\fH\026¢\006\004\b\r\020\016J\037\020\022\032\0020\b2\006\020\020\032\0020\0172\006\020\021\032\0020\004H\026¢\006\004\b\022\020\023R\"\020\025\032\0020\0248\006@\006X\016¢\006\022\n\004\b\025\020\026\032\004\b\027\020\030\"\004\b\031\020\032R\027\020\003\032\0020\0028\006¢\006\f\n\004\b\003\020\033\032\004\b\034\020\035R\"\020\005\032\0020\0048\006@\006X\016¢\006\022\n\004\b\005\020\036\032\004\b\037\020 \"\004\b!\020\"¨\006#"}, d2 = {"Lokio/FileHandle$FileHandleSink;", "Lokio/Sink;", "Lokio/FileHandle;", "fileHandle", "", "position", "<init>", "(Lokio/FileHandle;J)V", "", "close", "()V", "flush", "Lokio/Timeout;", "timeout", "()Lokio/Timeout;", "Lokio/Buffer;", "source", "byteCount", "write", "(Lokio/Buffer;J)V", "", "closed", "Z", "getClosed", "()Z", "setClosed", "(Z)V", "Lokio/FileHandle;", "getFileHandle", "()Lokio/FileHandle;", "J", "getPosition", "()J", "setPosition", "(J)V", "okio"})
  @SourceDebugExtension({"SMAP\nFileHandle.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FileHandle.kt\nokio/FileHandle$FileHandleSink\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 -JvmPlatform.kt\nokio/_JvmPlatformKt\n*L\n1#1,444:1\n1#2:445\n33#3:446\n*S KotlinDebug\n*F\n+ 1 FileHandle.kt\nokio/FileHandle$FileHandleSink\n*L\n410#1:446\n*E\n"})
  private static final class FileHandleSink implements Sink {
    @NotNull
    private final FileHandle fileHandle;
    
    private long position;
    
    private boolean closed;
    
    public FileHandleSink(@NotNull FileHandle fileHandle, long position) {
      this.fileHandle = fileHandle;
      this.position = position;
    }
    
    @NotNull
    public final FileHandle getFileHandle() {
      return this.fileHandle;
    }
    
    public final long getPosition() {
      return this.position;
    }
    
    public final void setPosition(long <set-?>) {
      this.position = <set-?>;
    }
    
    public final boolean getClosed() {
      return this.closed;
    }
    
    public final void setClosed(boolean <set-?>) {
      this.closed = <set-?>;
    }
    
    public void write(@NotNull Buffer source, long byteCount) {
      Intrinsics.checkNotNullParameter(source, "source");
      if (!(!this.closed ? 1 : 0)) {
        int $i$a$-check-FileHandle$FileHandleSink$write$1 = 0;
        String str = "closed";
        throw new IllegalStateException(str.toString());
      } 
      this.fileHandle.writeNoCloseCheck(this.position, source, byteCount);
      this.position += byteCount;
    }
    
    public void flush() {
      if (!(!this.closed ? 1 : 0)) {
        int $i$a$-check-FileHandle$FileHandleSink$flush$1 = 0;
        String str = "closed";
        throw new IllegalStateException(str.toString());
      } 
      this.fileHandle.protectedFlush();
    }
    
    @NotNull
    public Timeout timeout() {
      return Timeout.NONE;
    }
    
    public void close() {
      if (this.closed)
        return; 
      this.closed = true;
      ReentrantLock $this$withLock$iv = this.fileHandle.getLock();
      int $i$f$withLock = 0;
      ReentrantLock reentrantLock1 = $this$withLock$iv;
      reentrantLock1.lock();
      try {
        int $i$a$-withLock-FileHandle$FileHandleSink$close$1 = 0;
        FileHandle fileHandle = this.fileHandle;
        int i = fileHandle.openStreamCount;
        fileHandle.openStreamCount = i + -1;
        if (this.fileHandle.openStreamCount != 0 || !this.fileHandle.closed)
          return; 
        Unit unit = Unit.INSTANCE;
      } finally {
        reentrantLock1.unlock();
      } 
      this.fileHandle.protectedClose();
    }
  }
  
  @Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\0006\n\002\030\002\n\002\030\002\n\002\030\002\n\000\n\002\020\t\n\002\b\003\n\002\020\002\n\002\b\002\n\002\030\002\n\002\b\004\n\002\030\002\n\002\b\002\n\002\020\013\n\002\b\017\b\002\030\0002\0020\001B\027\022\006\020\003\032\0020\002\022\006\020\005\032\0020\004¢\006\004\b\006\020\007J\017\020\t\032\0020\bH\026¢\006\004\b\t\020\nJ\037\020\016\032\0020\0042\006\020\f\032\0020\0132\006\020\r\032\0020\004H\026¢\006\004\b\016\020\017J\017\020\021\032\0020\020H\026¢\006\004\b\021\020\022R\"\020\024\032\0020\0238\006@\006X\016¢\006\022\n\004\b\024\020\025\032\004\b\026\020\027\"\004\b\030\020\031R\027\020\003\032\0020\0028\006¢\006\f\n\004\b\003\020\032\032\004\b\033\020\034R\"\020\005\032\0020\0048\006@\006X\016¢\006\022\n\004\b\005\020\035\032\004\b\036\020\037\"\004\b \020!¨\006\""}, d2 = {"Lokio/FileHandle$FileHandleSource;", "Lokio/Source;", "Lokio/FileHandle;", "fileHandle", "", "position", "<init>", "(Lokio/FileHandle;J)V", "", "close", "()V", "Lokio/Buffer;", "sink", "byteCount", "read", "(Lokio/Buffer;J)J", "Lokio/Timeout;", "timeout", "()Lokio/Timeout;", "", "closed", "Z", "getClosed", "()Z", "setClosed", "(Z)V", "Lokio/FileHandle;", "getFileHandle", "()Lokio/FileHandle;", "J", "getPosition", "()J", "setPosition", "(J)V", "okio"})
  @SourceDebugExtension({"SMAP\nFileHandle.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FileHandle.kt\nokio/FileHandle$FileHandleSource\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 -JvmPlatform.kt\nokio/_JvmPlatformKt\n*L\n1#1,444:1\n1#2:445\n33#3:446\n*S KotlinDebug\n*F\n+ 1 FileHandle.kt\nokio/FileHandle$FileHandleSource\n*L\n436#1:446\n*E\n"})
  private static final class FileHandleSource implements Source {
    @NotNull
    private final FileHandle fileHandle;
    
    private long position;
    
    private boolean closed;
    
    public FileHandleSource(@NotNull FileHandle fileHandle, long position) {
      this.fileHandle = fileHandle;
      this.position = position;
    }
    
    @NotNull
    public final FileHandle getFileHandle() {
      return this.fileHandle;
    }
    
    public final long getPosition() {
      return this.position;
    }
    
    public final void setPosition(long <set-?>) {
      this.position = <set-?>;
    }
    
    public final boolean getClosed() {
      return this.closed;
    }
    
    public final void setClosed(boolean <set-?>) {
      this.closed = <set-?>;
    }
    
    public long read(@NotNull Buffer sink, long byteCount) {
      Intrinsics.checkNotNullParameter(sink, "sink");
      if (!(!this.closed ? 1 : 0)) {
        int $i$a$-check-FileHandle$FileHandleSource$read$1 = 0;
        String str = "closed";
        throw new IllegalStateException(str.toString());
      } 
      long result = this.fileHandle.readNoCloseCheck(this.position, sink, byteCount);
      if (result != -1L)
        this.position += result; 
      return result;
    }
    
    @NotNull
    public Timeout timeout() {
      return Timeout.NONE;
    }
    
    public void close() {
      if (this.closed)
        return; 
      this.closed = true;
      ReentrantLock $this$withLock$iv = this.fileHandle.getLock();
      int $i$f$withLock = 0;
      ReentrantLock reentrantLock1 = $this$withLock$iv;
      reentrantLock1.lock();
      try {
        int $i$a$-withLock-FileHandle$FileHandleSource$close$1 = 0;
        FileHandle fileHandle = this.fileHandle;
        int i = fileHandle.openStreamCount;
        fileHandle.openStreamCount = i + -1;
        if (this.fileHandle.openStreamCount != 0 || !this.fileHandle.closed)
          return; 
        Unit unit = Unit.INSTANCE;
      } finally {
        reentrantLock1.unlock();
      } 
      this.fileHandle.protectedClose();
    }
  }
}
