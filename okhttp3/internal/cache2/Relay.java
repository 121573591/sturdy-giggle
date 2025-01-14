package okhttp3.internal.cache2;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.Util;
import okio.Buffer;
import okio.ByteString;
import okio.Source;
import okio.Timeout;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000J\n\002\030\002\n\002\020\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\t\n\000\n\002\030\002\n\002\b\005\n\002\020\002\n\002\b\n\n\002\030\002\n\002\b\007\n\002\020\013\n\002\b\r\n\002\020\b\n\002\b\016\n\002\030\002\n\002\b\t\030\000 E2\0020\001:\002EFB5\b\002\022\b\020\003\032\004\030\0010\002\022\b\020\005\032\004\030\0010\004\022\006\020\007\032\0020\006\022\006\020\t\032\0020\b\022\006\020\n\032\0020\006¢\006\004\b\013\020\fJ\025\020\017\032\0020\0162\006\020\r\032\0020\006¢\006\004\b\017\020\020J\r\020\t\032\0020\b¢\006\004\b\t\020\021J\017\020\022\032\004\030\0010\004¢\006\004\b\022\020\023J'\020\026\032\0020\0162\006\020\024\032\0020\b2\006\020\r\032\0020\0062\006\020\025\032\0020\006H\002¢\006\004\b\026\020\027J\027\020\030\032\0020\0162\006\020\r\032\0020\006H\002¢\006\004\b\030\020\020R\027\020\032\032\0020\0318\006¢\006\f\n\004\b\032\020\033\032\004\b\034\020\035R\027\020\n\032\0020\0068\006¢\006\f\n\004\b\n\020\036\032\004\b\037\020 R\"\020\"\032\0020!8\006@\006X\016¢\006\022\n\004\b\"\020#\032\004\b$\020%\"\004\b&\020'R$\020\003\032\004\030\0010\0028\006@\006X\016¢\006\022\n\004\b\003\020(\032\004\b)\020*\"\004\b+\020,R\021\020-\032\0020!8F¢\006\006\032\004\b-\020%R\024\020\t\032\0020\b8\002X\004¢\006\006\n\004\b\t\020.R\"\0200\032\0020/8\006@\006X\016¢\006\022\n\004\b0\0201\032\004\b2\0203\"\004\b4\0205R$\020\005\032\004\030\0010\0048\006@\006X\016¢\006\022\n\004\b\005\0206\032\004\b7\020\023\"\004\b8\0209R\027\020:\032\0020\0318\006¢\006\f\n\004\b:\020\033\032\004\b;\020\035R\"\020\007\032\0020\0068\006@\006X\016¢\006\022\n\004\b\007\020\036\032\004\b<\020 \"\004\b=\020\020R$\020?\032\004\030\0010>8\006@\006X\016¢\006\022\n\004\b?\020@\032\004\bA\020B\"\004\bC\020D¨\006G"}, d2 = {"Lokhttp3/internal/cache2/Relay;", "", "Ljava/io/RandomAccessFile;", "file", "Lokio/Source;", "upstream", "", "upstreamPos", "Lokio/ByteString;", "metadata", "bufferMaxSize", "<init>", "(Ljava/io/RandomAccessFile;Lokio/Source;JLokio/ByteString;J)V", "upstreamSize", "", "commit", "(J)V", "()Lokio/ByteString;", "newSource", "()Lokio/Source;", "prefix", "metadataSize", "writeHeader", "(Lokio/ByteString;JJ)V", "writeMetadata", "Lokio/Buffer;", "buffer", "Lokio/Buffer;", "getBuffer", "()Lokio/Buffer;", "J", "getBufferMaxSize", "()J", "", "complete", "Z", "getComplete", "()Z", "setComplete", "(Z)V", "Ljava/io/RandomAccessFile;", "getFile", "()Ljava/io/RandomAccessFile;", "setFile", "(Ljava/io/RandomAccessFile;)V", "isClosed", "Lokio/ByteString;", "", "sourceCount", "I", "getSourceCount", "()I", "setSourceCount", "(I)V", "Lokio/Source;", "getUpstream", "setUpstream", "(Lokio/Source;)V", "upstreamBuffer", "getUpstreamBuffer", "getUpstreamPos", "setUpstreamPos", "Ljava/lang/Thread;", "upstreamReader", "Ljava/lang/Thread;", "getUpstreamReader", "()Ljava/lang/Thread;", "setUpstreamReader", "(Ljava/lang/Thread;)V", "Companion", "RelaySource", "okhttp"})
public final class Relay {
  private Relay(RandomAccessFile file, Source upstream, long upstreamPos, ByteString metadata, long bufferMaxSize) {
    this.file = file;
    this.upstream = upstream;
    this.upstreamPos = upstreamPos;
    this.metadata = metadata;
    this.bufferMaxSize = bufferMaxSize;
    this.upstreamBuffer = new Buffer();
    this.complete = (this.upstream == null);
    this.buffer = new Buffer();
  }
  
  @Nullable
  public final RandomAccessFile getFile() {
    return this.file;
  }
  
  public final void setFile(@Nullable RandomAccessFile <set-?>) {
    this.file = <set-?>;
  }
  
  @Nullable
  public final Source getUpstream() {
    return this.upstream;
  }
  
  public final void setUpstream(@Nullable Source <set-?>) {
    this.upstream = <set-?>;
  }
  
  public final long getUpstreamPos() {
    return this.upstreamPos;
  }
  
  public final void setUpstreamPos(long <set-?>) {
    this.upstreamPos = <set-?>;
  }
  
  public final long getBufferMaxSize() {
    return this.bufferMaxSize;
  }
  
  @Nullable
  public final Thread getUpstreamReader() {
    return this.upstreamReader;
  }
  
  public final void setUpstreamReader(@Nullable Thread <set-?>) {
    this.upstreamReader = <set-?>;
  }
  
  @NotNull
  public final Buffer getUpstreamBuffer() {
    return this.upstreamBuffer;
  }
  
  public final boolean getComplete() {
    return this.complete;
  }
  
  public final void setComplete(boolean <set-?>) {
    this.complete = <set-?>;
  }
  
  @NotNull
  public final Buffer getBuffer() {
    return this.buffer;
  }
  
  public final int getSourceCount() {
    return this.sourceCount;
  }
  
  public final void setSourceCount(int <set-?>) {
    this.sourceCount = <set-?>;
  }
  
  public final boolean isClosed() {
    return (this.file == null);
  }
  
  private final void writeHeader(ByteString prefix, long upstreamSize, long metadataSize) throws IOException {
    Buffer buffer1 = new Buffer(), $this$writeHeader_u24lambda_u240 = buffer1;
    int $i$a$-apply-Relay$writeHeader$header$1 = 0;
    $this$writeHeader_u24lambda_u240.write(prefix);
    $this$writeHeader_u24lambda_u240.writeLong(upstreamSize);
    $this$writeHeader_u24lambda_u240.writeLong(metadataSize);
    if (!(($this$writeHeader_u24lambda_u240.size() == 32L) ? 1 : 0)) {
      String str = "Failed requirement.";
      throw new IllegalArgumentException(str.toString());
    } 
    Buffer header = buffer1;
    Intrinsics.checkNotNull(this.file);
    Intrinsics.checkNotNullExpressionValue(this.file.getChannel(), "file!!.channel");
    FileOperator fileOperator = new FileOperator(this.file.getChannel());
    fileOperator.write(0L, header, 32L);
  }
  
  private final void writeMetadata(long upstreamSize) throws IOException {
    Buffer metadataBuffer = new Buffer();
    metadataBuffer.write(this.metadata);
    Intrinsics.checkNotNull(this.file);
    Intrinsics.checkNotNullExpressionValue(this.file.getChannel(), "file!!.channel");
    FileOperator fileOperator = new FileOperator(this.file.getChannel());
    fileOperator.write(32L + upstreamSize, metadataBuffer, this.metadata.size());
  }
  
  public final void commit(long upstreamSize) throws IOException {
    writeMetadata(upstreamSize);
    Intrinsics.checkNotNull(this.file);
    this.file.getChannel().force(false);
    writeHeader(PREFIX_CLEAN, upstreamSize, this.metadata.size());
    Intrinsics.checkNotNull(this.file);
    this.file.getChannel().force(false);
    synchronized (this) {
      int $i$a$-synchronized-Relay$commit$1 = 0;
      this.complete = true;
      Unit unit = Unit.INSTANCE;
    } 
    if (this.upstream != null) {
      Util.closeQuietly((Closeable)this.upstream);
    } else {
    
    } 
    this.upstream = null;
  }
  
  @NotNull
  public final ByteString metadata() {
    return this.metadata;
  }
  
  @Nullable
  public final Source newSource() {
    synchronized (this) {
      int $i$a$-synchronized-Relay$newSource$1 = 0;
      if (this.file == null)
        return null; 
      int i = this.sourceCount;
      this.sourceCount = i + 1;
      $i$a$-synchronized-Relay$newSource$1 = i;
    } 
    return new RelaySource();
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\0002\n\002\030\002\n\002\030\002\n\002\b\002\n\002\020\002\n\002\b\002\n\002\030\002\n\000\n\002\020\t\n\002\b\003\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\006\b\004\030\0002\0020\001B\007¢\006\004\b\002\020\003J\017\020\005\032\0020\004H\026¢\006\004\b\005\020\006J\037\020\013\032\0020\t2\006\020\b\032\0020\0072\006\020\n\032\0020\tH\026¢\006\004\b\013\020\fJ\017\020\016\032\0020\rH\026¢\006\004\b\016\020\017R\030\020\021\032\004\030\0010\0208\002@\002X\016¢\006\006\n\004\b\021\020\022R\026\020\023\032\0020\t8\002@\002X\016¢\006\006\n\004\b\023\020\024R\024\020\016\032\0020\r8\002X\004¢\006\006\n\004\b\016\020\025¨\006\026"}, d2 = {"Lokhttp3/internal/cache2/Relay$RelaySource;", "Lokio/Source;", "<init>", "(Lokhttp3/internal/cache2/Relay;)V", "", "close", "()V", "Lokio/Buffer;", "sink", "", "byteCount", "read", "(Lokio/Buffer;J)J", "Lokio/Timeout;", "timeout", "()Lokio/Timeout;", "Lokhttp3/internal/cache2/FileOperator;", "fileOperator", "Lokhttp3/internal/cache2/FileOperator;", "sourcePos", "J", "Lokio/Timeout;", "okhttp"})
  @SourceDebugExtension({"SMAP\nRelay.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Relay.kt\nokhttp3/internal/cache2/Relay$RelaySource\n+ 2 Util.kt\nokhttp3/internal/Util\n*L\n1#1,356:1\n563#2:357\n*S KotlinDebug\n*F\n+ 1 Relay.kt\nokhttp3/internal/cache2/Relay$RelaySource\n*L\n267#1:357\n*E\n"})
  public final class RelaySource implements Source {
    @NotNull
    private final Timeout timeout;
    
    @Nullable
    private FileOperator fileOperator;
    
    private long sourcePos;
    
    public RelaySource() {
      this.timeout = new Timeout();
      Intrinsics.checkNotNull(Relay.this.getFile());
      Intrinsics.checkNotNullExpressionValue(Relay.this.getFile().getChannel(), "file!!.channel");
      this.fileOperator = new FileOperator(Relay.this.getFile().getChannel());
    }
    
    public long read(@NotNull Buffer sink, long byteCount) throws IOException {
      // Byte code:
      //   0: aload_1
      //   1: ldc 'sink'
      //   3: invokestatic checkNotNullParameter : (Ljava/lang/Object;Ljava/lang/String;)V
      //   6: aload_0
      //   7: getfield fileOperator : Lokhttp3/internal/cache2/FileOperator;
      //   10: ifnull -> 17
      //   13: iconst_1
      //   14: goto -> 18
      //   17: iconst_0
      //   18: ifne -> 38
      //   21: ldc 'Check failed.'
      //   23: astore #5
      //   25: new java/lang/IllegalStateException
      //   28: dup
      //   29: aload #5
      //   31: invokevirtual toString : ()Ljava/lang/String;
      //   34: invokespecial <init> : (Ljava/lang/String;)V
      //   37: athrow
      //   38: aload_0
      //   39: getfield this$0 : Lokhttp3/internal/cache2/Relay;
      //   42: astore #5
      //   44: aload_0
      //   45: getfield this$0 : Lokhttp3/internal/cache2/Relay;
      //   48: astore #6
      //   50: aload #5
      //   52: astore #7
      //   54: aload #7
      //   56: monitorenter
      //   57: nop
      //   58: iconst_0
      //   59: istore #8
      //   61: nop
      //   62: aload #6
      //   64: invokevirtual getUpstreamPos : ()J
      //   67: lstore #9
      //   69: aload_0
      //   70: getfield sourcePos : J
      //   73: lload #9
      //   75: lcmp
      //   76: ifne -> 130
      //   79: aload #6
      //   81: invokevirtual getComplete : ()Z
      //   84: ifeq -> 98
      //   87: ldc2_w -1
      //   90: lstore #15
      //   92: aload #7
      //   94: monitorexit
      //   95: lload #15
      //   97: lreturn
      //   98: aload #6
      //   100: invokevirtual getUpstreamReader : ()Ljava/lang/Thread;
      //   103: ifnull -> 118
      //   106: aload_0
      //   107: getfield timeout : Lokio/Timeout;
      //   110: aload #6
      //   112: invokevirtual waitUntilNotified : (Ljava/lang/Object;)V
      //   115: goto -> 61
      //   118: aload #6
      //   120: invokestatic currentThread : ()Ljava/lang/Thread;
      //   123: invokevirtual setUpstreamReader : (Ljava/lang/Thread;)V
      //   126: iconst_1
      //   127: goto -> 216
      //   130: aload #6
      //   132: invokevirtual getUpstreamPos : ()J
      //   135: aload #6
      //   137: invokevirtual getBuffer : ()Lokio/Buffer;
      //   140: invokevirtual size : ()J
      //   143: lsub
      //   144: lstore #9
      //   146: aload_0
      //   147: getfield sourcePos : J
      //   150: lload #9
      //   152: lcmp
      //   153: ifge -> 160
      //   156: iconst_2
      //   157: goto -> 216
      //   160: lload_2
      //   161: aload #6
      //   163: invokevirtual getUpstreamPos : ()J
      //   166: aload_0
      //   167: getfield sourcePos : J
      //   170: lsub
      //   171: invokestatic min : (JJ)J
      //   174: lstore #11
      //   176: aload #6
      //   178: invokevirtual getBuffer : ()Lokio/Buffer;
      //   181: aload_1
      //   182: aload_0
      //   183: getfield sourcePos : J
      //   186: lload #9
      //   188: lsub
      //   189: lload #11
      //   191: invokevirtual copyTo : (Lokio/Buffer;JJ)Lokio/Buffer;
      //   194: pop
      //   195: aload_0
      //   196: aload_0
      //   197: getfield sourcePos : J
      //   200: lload #11
      //   202: ladd
      //   203: putfield sourcePos : J
      //   206: lload #11
      //   208: lstore #13
      //   210: aload #7
      //   212: monitorexit
      //   213: lload #13
      //   215: lreturn
      //   216: istore #8
      //   218: aload #7
      //   220: monitorexit
      //   221: iload #8
      //   223: goto -> 234
      //   226: astore #8
      //   228: aload #7
      //   230: monitorexit
      //   231: aload #8
      //   233: athrow
      //   234: istore #4
      //   236: iload #4
      //   238: iconst_2
      //   239: if_icmpne -> 296
      //   242: lload_2
      //   243: aload_0
      //   244: getfield this$0 : Lokhttp3/internal/cache2/Relay;
      //   247: invokevirtual getUpstreamPos : ()J
      //   250: aload_0
      //   251: getfield sourcePos : J
      //   254: lsub
      //   255: invokestatic min : (JJ)J
      //   258: lstore #5
      //   260: aload_0
      //   261: getfield fileOperator : Lokhttp3/internal/cache2/FileOperator;
      //   264: dup
      //   265: invokestatic checkNotNull : (Ljava/lang/Object;)V
      //   268: ldc2_w 32
      //   271: aload_0
      //   272: getfield sourcePos : J
      //   275: ladd
      //   276: aload_1
      //   277: lload #5
      //   279: invokevirtual read : (JLokio/Buffer;J)V
      //   282: aload_0
      //   283: aload_0
      //   284: getfield sourcePos : J
      //   287: lload #5
      //   289: ladd
      //   290: putfield sourcePos : J
      //   293: lload #5
      //   295: lreturn
      //   296: nop
      //   297: aload_0
      //   298: getfield this$0 : Lokhttp3/internal/cache2/Relay;
      //   301: invokevirtual getUpstream : ()Lokio/Source;
      //   304: dup
      //   305: invokestatic checkNotNull : (Ljava/lang/Object;)V
      //   308: aload_0
      //   309: getfield this$0 : Lokhttp3/internal/cache2/Relay;
      //   312: invokevirtual getUpstreamBuffer : ()Lokio/Buffer;
      //   315: aload_0
      //   316: getfield this$0 : Lokhttp3/internal/cache2/Relay;
      //   319: invokevirtual getBufferMaxSize : ()J
      //   322: invokeinterface read : (Lokio/Buffer;J)J
      //   327: lstore #5
      //   329: lload #5
      //   331: ldc2_w -1
      //   334: lcmp
      //   335: ifne -> 431
      //   338: aload_0
      //   339: getfield this$0 : Lokhttp3/internal/cache2/Relay;
      //   342: aload_0
      //   343: getfield this$0 : Lokhttp3/internal/cache2/Relay;
      //   346: invokevirtual getUpstreamPos : ()J
      //   349: invokevirtual commit : (J)V
      //   352: ldc2_w -1
      //   355: lstore #7
      //   357: aload_0
      //   358: getfield this$0 : Lokhttp3/internal/cache2/Relay;
      //   361: astore #9
      //   363: aload_0
      //   364: getfield this$0 : Lokhttp3/internal/cache2/Relay;
      //   367: astore #10
      //   369: aload #9
      //   371: astore #11
      //   373: aload #11
      //   375: monitorenter
      //   376: nop
      //   377: iconst_0
      //   378: istore #12
      //   380: aload #10
      //   382: aconst_null
      //   383: invokevirtual setUpstreamReader : (Ljava/lang/Thread;)V
      //   386: aload #10
      //   388: astore #13
      //   390: iconst_0
      //   391: istore #14
      //   393: aload #13
      //   395: ldc 'null cannot be cast to non-null type java.lang.Object'
      //   397: invokestatic checkNotNull : (Ljava/lang/Object;Ljava/lang/String;)V
      //   400: aload #13
      //   402: checkcast java/lang/Object
      //   405: invokevirtual notifyAll : ()V
      //   408: nop
      //   409: getstatic kotlin/Unit.INSTANCE : Lkotlin/Unit;
      //   412: astore #12
      //   414: aload #11
      //   416: monitorexit
      //   417: goto -> 428
      //   420: astore #12
      //   422: aload #11
      //   424: monitorexit
      //   425: aload #12
      //   427: athrow
      //   428: lload #7
      //   430: lreturn
      //   431: nop
      //   432: lload #5
      //   434: lload_2
      //   435: invokestatic min : (JJ)J
      //   438: lstore #7
      //   440: aload_0
      //   441: getfield this$0 : Lokhttp3/internal/cache2/Relay;
      //   444: invokevirtual getUpstreamBuffer : ()Lokio/Buffer;
      //   447: aload_1
      //   448: lconst_0
      //   449: lload #7
      //   451: invokevirtual copyTo : (Lokio/Buffer;JJ)Lokio/Buffer;
      //   454: pop
      //   455: aload_0
      //   456: aload_0
      //   457: getfield sourcePos : J
      //   460: lload #7
      //   462: ladd
      //   463: putfield sourcePos : J
      //   466: aload_0
      //   467: getfield fileOperator : Lokhttp3/internal/cache2/FileOperator;
      //   470: dup
      //   471: invokestatic checkNotNull : (Ljava/lang/Object;)V
      //   474: ldc2_w 32
      //   477: aload_0
      //   478: getfield this$0 : Lokhttp3/internal/cache2/Relay;
      //   481: invokevirtual getUpstreamPos : ()J
      //   484: ladd
      //   485: aload_0
      //   486: getfield this$0 : Lokhttp3/internal/cache2/Relay;
      //   489: invokevirtual getUpstreamBuffer : ()Lokio/Buffer;
      //   492: invokevirtual clone : ()Lokio/Buffer;
      //   495: lload #5
      //   497: invokevirtual write : (JLokio/Buffer;J)V
      //   500: aload_0
      //   501: getfield this$0 : Lokhttp3/internal/cache2/Relay;
      //   504: astore #9
      //   506: aload_0
      //   507: getfield this$0 : Lokhttp3/internal/cache2/Relay;
      //   510: astore #10
      //   512: aload #9
      //   514: astore #11
      //   516: aload #11
      //   518: monitorenter
      //   519: nop
      //   520: iconst_0
      //   521: istore #12
      //   523: aload #10
      //   525: invokevirtual getBuffer : ()Lokio/Buffer;
      //   528: aload #10
      //   530: invokevirtual getUpstreamBuffer : ()Lokio/Buffer;
      //   533: lload #5
      //   535: invokevirtual write : (Lokio/Buffer;J)V
      //   538: aload #10
      //   540: invokevirtual getBuffer : ()Lokio/Buffer;
      //   543: invokevirtual size : ()J
      //   546: aload #10
      //   548: invokevirtual getBufferMaxSize : ()J
      //   551: lcmp
      //   552: ifle -> 577
      //   555: aload #10
      //   557: invokevirtual getBuffer : ()Lokio/Buffer;
      //   560: aload #10
      //   562: invokevirtual getBuffer : ()Lokio/Buffer;
      //   565: invokevirtual size : ()J
      //   568: aload #10
      //   570: invokevirtual getBufferMaxSize : ()J
      //   573: lsub
      //   574: invokevirtual skip : (J)V
      //   577: aload #10
      //   579: aload #10
      //   581: invokevirtual getUpstreamPos : ()J
      //   584: lload #5
      //   586: ladd
      //   587: invokevirtual setUpstreamPos : (J)V
      //   590: nop
      //   591: getstatic kotlin/Unit.INSTANCE : Lkotlin/Unit;
      //   594: astore #12
      //   596: aload #11
      //   598: monitorexit
      //   599: goto -> 610
      //   602: astore #12
      //   604: aload #11
      //   606: monitorexit
      //   607: aload #12
      //   609: athrow
      //   610: lload #7
      //   612: lstore #9
      //   614: aload_0
      //   615: getfield this$0 : Lokhttp3/internal/cache2/Relay;
      //   618: astore #11
      //   620: aload_0
      //   621: getfield this$0 : Lokhttp3/internal/cache2/Relay;
      //   624: astore #12
      //   626: aload #11
      //   628: astore #13
      //   630: aload #13
      //   632: monitorenter
      //   633: nop
      //   634: iconst_0
      //   635: istore #14
      //   637: aload #12
      //   639: aconst_null
      //   640: invokevirtual setUpstreamReader : (Ljava/lang/Thread;)V
      //   643: aload #12
      //   645: astore #15
      //   647: iconst_0
      //   648: istore #16
      //   650: aload #15
      //   652: ldc 'null cannot be cast to non-null type java.lang.Object'
      //   654: invokestatic checkNotNull : (Ljava/lang/Object;Ljava/lang/String;)V
      //   657: aload #15
      //   659: checkcast java/lang/Object
      //   662: invokevirtual notifyAll : ()V
      //   665: nop
      //   666: getstatic kotlin/Unit.INSTANCE : Lkotlin/Unit;
      //   669: astore #14
      //   671: aload #13
      //   673: monitorexit
      //   674: goto -> 685
      //   677: astore #14
      //   679: aload #13
      //   681: monitorexit
      //   682: aload #14
      //   684: athrow
      //   685: lload #9
      //   687: lreturn
      //   688: astore #5
      //   690: aload_0
      //   691: getfield this$0 : Lokhttp3/internal/cache2/Relay;
      //   694: astore #6
      //   696: aload_0
      //   697: getfield this$0 : Lokhttp3/internal/cache2/Relay;
      //   700: astore #7
      //   702: aload #6
      //   704: astore #8
      //   706: aload #8
      //   708: monitorenter
      //   709: nop
      //   710: iconst_0
      //   711: istore #9
      //   713: aload #7
      //   715: aconst_null
      //   716: invokevirtual setUpstreamReader : (Ljava/lang/Thread;)V
      //   719: aload #7
      //   721: astore #10
      //   723: iconst_0
      //   724: istore #11
      //   726: aload #10
      //   728: ldc 'null cannot be cast to non-null type java.lang.Object'
      //   730: invokestatic checkNotNull : (Ljava/lang/Object;Ljava/lang/String;)V
      //   733: aload #10
      //   735: invokevirtual notifyAll : ()V
      //   738: nop
      //   739: getstatic kotlin/Unit.INSTANCE : Lkotlin/Unit;
      //   742: astore #9
      //   744: aload #8
      //   746: monitorexit
      //   747: goto -> 758
      //   750: astore #9
      //   752: aload #8
      //   754: monitorexit
      //   755: aload #9
      //   757: athrow
      //   758: aload #5
      //   760: athrow
      // Line number table:
      //   Java source line number -> byte code offset
      //   #188	-> 6
      //   #190	-> 38
      //   #192	-> 61
      //   #193	-> 62
      //   #194	-> 69
      //   #197	-> 79
      //   #200	-> 98
      //   #201	-> 106
      //   #202	-> 115
      //   #206	-> 118
      //   #207	-> 126
      //   #210	-> 130
      //   #213	-> 146
      //   #214	-> 156
      //   #218	-> 161
      //   #218	-> 174
      //   #219	-> 176
      //   #220	-> 195
      //   #221	-> 206
      //   #190	-> 216
      //   #190	-> 234
      //   #225	-> 236
      //   #226	-> 243
      //   #226	-> 258
      //   #227	-> 260
      //   #228	-> 282
      //   #229	-> 293
      //   #234	-> 296
      //   #235	-> 297
      //   #238	-> 329
      //   #239	-> 338
      //   #240	-> 352
      //   #265	-> 357
      //   #266	-> 380
      //   #267	-> 386
      //   #357	-> 393
      //   #268	-> 408
      //   #265	-> 412
      //   #240	-> 430
      //   #244	-> 431
      //   #244	-> 438
      //   #245	-> 440
      //   #246	-> 455
      //   #249	-> 466
      //   #250	-> 474
      //   #249	-> 497
      //   #252	-> 500
      //   #254	-> 523
      //   #255	-> 538
      //   #256	-> 555
      //   #260	-> 577
      //   #261	-> 590
      //   #252	-> 594
      //   #263	-> 610
      //   #265	-> 614
      //   #266	-> 637
      //   #267	-> 643
      //   #357	-> 650
      //   #268	-> 665
      //   #265	-> 669
      //   #263	-> 687
      //   #265	-> 688
      //   #266	-> 713
      //   #267	-> 719
      //   #357	-> 726
      //   #268	-> 738
      //   #265	-> 742
      // Local variable table:
      //   start	length	slot	name	descriptor
      //   69	21	9	upstreamPos	J
      //   61	29	8	$i$a$-synchronized-Relay$RelaySource$read$source$1	I
      //   146	62	9	bufferPos	J
      //   176	32	11	bytesToRead	J
      //   98	110	8	$i$a$-synchronized-Relay$RelaySource$read$source$1	I
      //   98	32	9	upstreamPos	J
      //   260	36	5	bytesToRead	J
      //   393	15	14	$i$f$notifyAll	I
      //   390	18	13	$this$notifyAll$iv	Ljava/lang/Object;
      //   380	29	12	$i$a$-synchronized-Relay$RelaySource$read$2	I
      //   523	68	12	$i$a$-synchronized-Relay$RelaySource$read$1	I
      //   650	15	16	$i$f$notifyAll	I
      //   647	18	15	$this$notifyAll$iv	Ljava/lang/Object;
      //   637	29	14	$i$a$-synchronized-Relay$RelaySource$read$2	I
      //   329	28	5	upstreamBytesRead	J
      //   428	186	5	upstreamBytesRead	J
      //   685	3	5	upstreamBytesRead	J
      //   440	174	7	bytesRead	J
      //   685	3	7	bytesRead	J
      //   726	12	11	$i$f$notifyAll	I
      //   723	15	10	$this$notifyAll$iv	Ljava/lang/Object;
      //   713	26	9	$i$a$-synchronized-Relay$RelaySource$read$2	I
      //   236	525	4	source	I
      //   0	761	0	this	Lokhttp3/internal/cache2/Relay$RelaySource;
      //   0	761	1	sink	Lokio/Buffer;
      //   0	761	2	byteCount	J
      // Exception table:
      //   from	to	target	type
      //   57	92	226	finally
      //   98	210	226	finally
      //   216	218	226	finally
      //   226	228	226	finally
      //   296	357	688	finally
      //   376	414	420	finally
      //   420	422	420	finally
      //   431	614	688	finally
      //   519	596	602	finally
      //   602	604	602	finally
      //   633	671	677	finally
      //   677	679	677	finally
      //   688	690	688	finally
      //   709	744	750	finally
      //   750	752	750	finally
    }
    
    @NotNull
    public Timeout timeout() {
      return this.timeout;
    }
    
    public void close() throws IOException {
      if (this.fileOperator == null)
        return; 
      this.fileOperator = null;
      Object fileToClose = null;
      Relay relay1 = Relay.this, relay2 = Relay.this;
      synchronized (relay1) {
        int $i$a$-synchronized-Relay$RelaySource$close$1 = 0;
        int i = relay2.getSourceCount();
        relay2.setSourceCount(i + -1);
        if (relay2.getSourceCount() == 0) {
          fileToClose = relay2.getFile();
          relay2.setFile(null);
        } 
        Unit unit = Unit.INSTANCE;
      } 
      if (fileToClose != null) {
        Util.closeQuietly((Closeable)fileToClose);
      } else {
      
      } 
    }
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\0004\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\t\n\000\n\002\030\002\n\002\b\t\n\002\020\b\n\002\b\004\b\003\030\0002\0020\001B\t\b\002¢\006\004\b\002\020\003J-\020\r\032\0020\f2\006\020\005\032\0020\0042\006\020\007\032\0020\0062\006\020\t\032\0020\b2\006\020\013\032\0020\n¢\006\004\b\r\020\016J\025\020\017\032\0020\f2\006\020\005\032\0020\004¢\006\004\b\017\020\020R\024\020\021\032\0020\n8\002XT¢\006\006\n\004\b\021\020\022R\024\020\023\032\0020\b8\006X\004¢\006\006\n\004\b\023\020\024R\024\020\025\032\0020\b8\006X\004¢\006\006\n\004\b\025\020\024R\024\020\027\032\0020\0268\002XT¢\006\006\n\004\b\027\020\030R\024\020\031\032\0020\0268\002XT¢\006\006\n\004\b\031\020\030¨\006\032"}, d2 = {"Lokhttp3/internal/cache2/Relay$Companion;", "", "<init>", "()V", "Ljava/io/File;", "file", "Lokio/Source;", "upstream", "Lokio/ByteString;", "metadata", "", "bufferMaxSize", "Lokhttp3/internal/cache2/Relay;", "edit", "(Ljava/io/File;Lokio/Source;Lokio/ByteString;J)Lokhttp3/internal/cache2/Relay;", "read", "(Ljava/io/File;)Lokhttp3/internal/cache2/Relay;", "FILE_HEADER_SIZE", "J", "PREFIX_CLEAN", "Lokio/ByteString;", "PREFIX_DIRTY", "", "SOURCE_FILE", "I", "SOURCE_UPSTREAM", "okhttp"})
  public static final class Companion {
    private Companion() {}
    
    @NotNull
    public final Relay edit(@NotNull File file, @NotNull Source upstream, @NotNull ByteString metadata, long bufferMaxSize) throws IOException {
      Intrinsics.checkNotNullParameter(file, "file");
      Intrinsics.checkNotNullParameter(upstream, "upstream");
      Intrinsics.checkNotNullParameter(metadata, "metadata");
      RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
      Relay result = new Relay(randomAccessFile, upstream, 0L, metadata, bufferMaxSize, null);
      randomAccessFile.setLength(0L);
      result.writeHeader(Relay.PREFIX_DIRTY, -1L, -1L);
      return result;
    }
    
    @NotNull
    public final Relay read(@NotNull File file) throws IOException {
      Intrinsics.checkNotNullParameter(file, "file");
      RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
      Intrinsics.checkNotNullExpressionValue(randomAccessFile.getChannel(), "randomAccessFile.channel");
      FileOperator fileOperator = new FileOperator(randomAccessFile.getChannel());
      Buffer header = new Buffer();
      fileOperator.read(0L, header, 32L);
      ByteString prefix = header.readByteString(Relay.PREFIX_CLEAN.size());
      if (!Intrinsics.areEqual(prefix, Relay.PREFIX_CLEAN))
        throw new IOException("unreadable cache file"); 
      long upstreamSize = header.readLong();
      long metadataSize = header.readLong();
      Buffer metadataBuffer = new Buffer();
      fileOperator.read(32L + upstreamSize, metadataBuffer, metadataSize);
      ByteString metadata = metadataBuffer.readByteString();
      return new Relay(randomAccessFile, null, upstreamSize, metadata, 0L, null);
    }
  }
  
  @NotNull
  public static final Companion Companion = new Companion(null);
  
  @Nullable
  private RandomAccessFile file;
  
  @Nullable
  private Source upstream;
  
  private long upstreamPos;
  
  @NotNull
  private final ByteString metadata;
  
  private final long bufferMaxSize;
  
  @Nullable
  private Thread upstreamReader;
  
  @NotNull
  private final Buffer upstreamBuffer;
  
  private boolean complete;
  
  @NotNull
  private final Buffer buffer;
  
  private int sourceCount;
  
  private static final int SOURCE_UPSTREAM = 1;
  
  private static final int SOURCE_FILE = 2;
  
  @JvmField
  @NotNull
  public static final ByteString PREFIX_CLEAN = ByteString.Companion.encodeUtf8("OkHttp cache v1\n");
  
  @JvmField
  @NotNull
  public static final ByteString PREFIX_DIRTY = ByteString.Companion.encodeUtf8("OkHttp DIRTY :(\n");
  
  private static final long FILE_HEADER_SIZE = 32L;
}
