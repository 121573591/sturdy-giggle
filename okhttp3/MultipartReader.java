package okhttp3;

import java.io.Closeable;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.http1.HeadersReader;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;
import okio.Okio;
import okio.Options;
import okio.Source;
import okio.Timeout;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000V\n\002\030\002\n\002\030\002\n\002\030\002\n\002\b\003\n\002\030\002\n\000\n\002\020\016\n\002\b\002\n\002\020\002\n\002\b\002\n\002\020\t\n\002\b\003\n\002\030\002\n\002\b\004\n\002\020\013\n\002\b\002\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\004\n\002\020\b\n\002\b\007\030\000 &2\0020\001:\003&'(B\021\b\026\022\006\020\003\032\0020\002¢\006\004\b\004\020\005B\027\022\006\020\007\032\0020\006\022\006\020\t\032\0020\b¢\006\004\b\004\020\nJ\017\020\f\032\0020\013H\026¢\006\004\b\f\020\rJ\027\020\020\032\0020\0162\006\020\017\032\0020\016H\002¢\006\004\b\020\020\021J\017\020\023\032\004\030\0010\022¢\006\004\b\023\020\024R\027\020\t\032\0020\b8\007¢\006\f\n\004\b\t\020\025\032\004\b\t\020\026R\026\020\030\032\0020\0278\002@\002X\016¢\006\006\n\004\b\030\020\031R\024\020\033\032\0020\0328\002X\004¢\006\006\n\004\b\033\020\034R\034\020\036\032\b\030\0010\035R\0020\0008\002@\002X\016¢\006\006\n\004\b\036\020\037R\024\020 \032\0020\0328\002X\004¢\006\006\n\004\b \020\034R\026\020!\032\0020\0278\002@\002X\016¢\006\006\n\004\b!\020\031R\026\020#\032\0020\"8\002@\002X\016¢\006\006\n\004\b#\020$R\024\020\007\032\0020\0068\002X\004¢\006\006\n\004\b\007\020%¨\006)"}, d2 = {"Lokhttp3/MultipartReader;", "Ljava/io/Closeable;", "Lokhttp3/ResponseBody;", "response", "<init>", "(Lokhttp3/ResponseBody;)V", "Lokio/BufferedSource;", "source", "", "boundary", "(Lokio/BufferedSource;Ljava/lang/String;)V", "", "close", "()V", "", "maxResult", "currentPartBytesRemaining", "(J)J", "Lokhttp3/MultipartReader$Part;", "nextPart", "()Lokhttp3/MultipartReader$Part;", "Ljava/lang/String;", "()Ljava/lang/String;", "", "closed", "Z", "Lokio/ByteString;", "crlfDashDashBoundary", "Lokio/ByteString;", "Lokhttp3/MultipartReader$PartSource;", "currentPart", "Lokhttp3/MultipartReader$PartSource;", "dashDashBoundary", "noMoreParts", "", "partCount", "I", "Lokio/BufferedSource;", "Companion", "Part", "PartSource", "okhttp"})
@SourceDebugExtension({"SMAP\nMultipartReader.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MultipartReader.kt\nokhttp3/MultipartReader\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,210:1\n1#2:211\n*E\n"})
public final class MultipartReader implements Closeable {
  public MultipartReader(@NotNull BufferedSource source, @NotNull String boundary) throws IOException {
    this.source = source;
    this.boundary = boundary;
    this.dashDashBoundary = (new Buffer()).writeUtf8("--").writeUtf8(this.boundary).readByteString();
    this.crlfDashDashBoundary = (new Buffer()).writeUtf8("\r\n--").writeUtf8(this.boundary).readByteString();
  }
  
  @JvmName(name = "boundary")
  @NotNull
  public final String boundary() {
    return this.boundary;
  }
  
  public MultipartReader(@NotNull ResponseBody response) throws IOException {
    super(
        
        (BufferedSource)response.contentType().parameter("boundary"), response.contentType().parameter("boundary"));
  }
  
  @Nullable
  public final Part nextPart() throws IOException {
    if (!(!this.closed ? 1 : 0)) {
      int $i$a$-check-MultipartReader$nextPart$1 = 0;
      String str = "closed";
      throw new IllegalStateException(str.toString());
    } 
    if (this.noMoreParts)
      return null; 
    if (this.partCount == 0 && this.source.rangeEquals(0L, this.dashDashBoundary)) {
      this.source.skip(this.dashDashBoundary.size());
    } else {
      while (true) {
        long toSkip = currentPartBytesRemaining(8192L);
        if (toSkip != 0L) {
          this.source.skip(toSkip);
          continue;
        } 
        break;
      } 
      this.source.skip(this.crlfDashDashBoundary.size());
    } 
    boolean whitespace = false;
    while (true) {
      int i;
      switch (this.source.select(afterBoundaryOptions)) {
        case 0:
          i = this.partCount;
          this.partCount = i + 1;
          break;
        case 1:
          if (whitespace)
            throw new ProtocolException("unexpected characters after boundary"); 
          if (this.partCount == 0)
            throw new ProtocolException("expected at least 1 part"); 
          this.noMoreParts = true;
          return null;
        case 2:
        case 3:
          whitespace = true;
        case -1:
          throw new ProtocolException("unexpected characters after boundary");
      } 
    } 
    Headers headers = (new HeadersReader(this.source)).readHeaders();
    PartSource partSource = new PartSource();
    this.currentPart = partSource;
    return new Part(headers, Okio.buffer(partSource));
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000*\n\002\030\002\n\002\030\002\n\002\b\002\n\002\020\002\n\002\b\002\n\002\030\002\n\000\n\002\020\t\n\002\b\003\n\002\030\002\n\002\b\004\b\004\030\0002\0020\001B\007¢\006\004\b\002\020\003J\017\020\005\032\0020\004H\026¢\006\004\b\005\020\006J\037\020\013\032\0020\t2\006\020\b\032\0020\0072\006\020\n\032\0020\tH\026¢\006\004\b\013\020\fJ\017\020\016\032\0020\rH\026¢\006\004\b\016\020\017R\024\020\016\032\0020\r8\002X\004¢\006\006\n\004\b\016\020\020¨\006\021"}, d2 = {"Lokhttp3/MultipartReader$PartSource;", "Lokio/Source;", "<init>", "(Lokhttp3/MultipartReader;)V", "", "close", "()V", "Lokio/Buffer;", "sink", "", "byteCount", "read", "(Lokio/Buffer;J)J", "Lokio/Timeout;", "timeout", "()Lokio/Timeout;", "Lokio/Timeout;", "okhttp"})
  @SourceDebugExtension({"SMAP\nMultipartReader.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MultipartReader.kt\nokhttp3/MultipartReader$PartSource\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 Timeout.kt\nokio/Timeout\n*L\n1#1,210:1\n1#2:211\n268#3,26:212\n*S KotlinDebug\n*F\n+ 1 MultipartReader.kt\nokhttp3/MultipartReader$PartSource\n*L\n159#1:212,26\n*E\n"})
  private final class PartSource implements Source {
    @NotNull
    private final Timeout timeout = new Timeout();
    
    public void close() {
      if (Intrinsics.areEqual(MultipartReader.this.currentPart, this))
        MultipartReader.this.currentPart = null; 
    }
    
    public long read(@NotNull Buffer sink, long byteCount) {
      Intrinsics.checkNotNullParameter(sink, "sink");
      if (!((byteCount >= 0L) ? 1 : 0)) {
        int $i$a$-require-MultipartReader$PartSource$read$1 = 0;
        String str = "byteCount < 0: " + byteCount;
        throw new IllegalArgumentException(str.toString());
      } 
      if (!Intrinsics.areEqual(MultipartReader.this.currentPart, this)) {
        int $i$a$-check-MultipartReader$PartSource$read$2 = 0;
        String str = "closed";
        throw new IllegalStateException(str.toString());
      } 
      Timeout timeout1 = MultipartReader.this.source.timeout(), timeout2 = this.timeout;
      MultipartReader multipartReader = MultipartReader.this;
      int $i$f$intersectWith = 0;
      long originalTimeout$iv = timeout1.timeoutNanos();
      timeout1.timeout(Timeout.Companion.minTimeout(timeout2.timeoutNanos(), timeout1.timeoutNanos()), TimeUnit.NANOSECONDS);
      if (timeout1.hasDeadline()) {
        long originalDeadline$iv = timeout1.deadlineNanoTime();
        if (timeout2.hasDeadline())
          timeout1.deadlineNanoTime(Math.min(timeout1.deadlineNanoTime(), timeout2.deadlineNanoTime())); 
        try {
          int $i$a$-intersectWith-MultipartReader$PartSource$read$3 = 0;
          long limit = multipartReader.currentPartBytesRemaining(byteCount);
          return (limit == 0L) ? -1L : multipartReader.source.read(sink, limit);
        } finally {
          timeout1.timeout(originalTimeout$iv, TimeUnit.NANOSECONDS);
          if (timeout2.hasDeadline())
            timeout1.deadlineNanoTime(originalDeadline$iv); 
        } 
      } 
      if (timeout2.hasDeadline())
        timeout1.deadlineNanoTime(timeout2.deadlineNanoTime()); 
      try {
        int $i$a$-intersectWith-MultipartReader$PartSource$read$3 = 0;
        long limit = multipartReader.currentPartBytesRemaining(byteCount);
        return (limit == 0L) ? -1L : multipartReader.source.read(sink, limit);
      } finally {
        timeout1.timeout(originalTimeout$iv, TimeUnit.NANOSECONDS);
        if (timeout2.hasDeadline())
          timeout1.clearDeadline(); 
      } 
    }
    
    @NotNull
    public Timeout timeout() {
      return this.timeout;
    }
  }
  
  private final long currentPartBytesRemaining(long maxResult) {
    this.source.require(this.crlfDashDashBoundary.size());
    long delimiterIndex = this.source.getBuffer().indexOf(this.crlfDashDashBoundary);
    return (delimiterIndex == -1L) ? Math.min(maxResult, this.source.getBuffer().size() - this.crlfDashDashBoundary.size() + 1L) : Math.min(maxResult, delimiterIndex);
  }
  
  public void close() throws IOException {
    if (this.closed)
      return; 
    this.closed = true;
    this.currentPart = null;
    this.source.close();
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000\036\n\002\030\002\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\002\b\003\n\002\020\002\n\002\b\007\030\0002\0020\001B\027\022\006\020\003\032\0020\002\022\006\020\005\032\0020\004¢\006\004\b\006\020\007J\020\020\t\032\0020\bH\001¢\006\004\b\t\020\nR\027\020\005\032\0020\0048\007¢\006\f\n\004\b\005\020\013\032\004\b\005\020\fR\027\020\003\032\0020\0028\007¢\006\f\n\004\b\003\020\r\032\004\b\003\020\016¨\006\017"}, d2 = {"Lokhttp3/MultipartReader$Part;", "Ljava/io/Closeable;", "Lokhttp3/Headers;", "headers", "Lokio/BufferedSource;", "body", "<init>", "(Lokhttp3/Headers;Lokio/BufferedSource;)V", "", "close", "()V", "Lokio/BufferedSource;", "()Lokio/BufferedSource;", "Lokhttp3/Headers;", "()Lokhttp3/Headers;", "okhttp"})
  public static final class Part implements Closeable {
    @NotNull
    private final Headers headers;
    
    @NotNull
    private final BufferedSource body;
    
    public Part(@NotNull Headers headers, @NotNull BufferedSource body) {
      this.headers = headers;
      this.body = body;
    }
    
    @JvmName(name = "headers")
    @NotNull
    public final Headers headers() {
      return this.headers;
    }
    
    @JvmName(name = "body")
    @NotNull
    public final BufferedSource body() {
      return this.body;
    }
    
    public void close() {
      this.body.close();
    }
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000\024\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\002\b\005\b\003\030\0002\0020\001B\t\b\002¢\006\004\b\002\020\003R\027\020\005\032\0020\0048\006¢\006\f\n\004\b\005\020\006\032\004\b\007\020\b¨\006\t"}, d2 = {"Lokhttp3/MultipartReader$Companion;", "", "<init>", "()V", "Lokio/Options;", "afterBoundaryOptions", "Lokio/Options;", "getAfterBoundaryOptions", "()Lokio/Options;", "okhttp"})
  public static final class Companion {
    private Companion() {}
    
    @NotNull
    public final Options getAfterBoundaryOptions() {
      return MultipartReader.afterBoundaryOptions;
    }
  }
  
  @NotNull
  public static final Companion Companion = new Companion(null);
  
  @NotNull
  private final BufferedSource source;
  
  @NotNull
  private final String boundary;
  
  @NotNull
  private final ByteString dashDashBoundary;
  
  @NotNull
  private final ByteString crlfDashDashBoundary;
  
  private int partCount;
  
  private boolean closed;
  
  private boolean noMoreParts;
  
  @Nullable
  private PartSource currentPart;
  
  @NotNull
  private static final Options afterBoundaryOptions;
  
  static {
    ByteString[] arrayOfByteString = new ByteString[4];
    arrayOfByteString[0] = ByteString.Companion.encodeUtf8("\r\n");
    arrayOfByteString[1] = ByteString.Companion.encodeUtf8("--");
    arrayOfByteString[2] = ByteString.Companion.encodeUtf8(" ");
    arrayOfByteString[3] = ByteString.Companion.encodeUtf8("\t");
    afterBoundaryOptions = Options.Companion.of(arrayOfByteString);
  }
}
