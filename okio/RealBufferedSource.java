package okio;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.CharsKt;
import okio.internal.-Buffer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\000\001\n\002\030\002\n\002\030\002\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\002\n\002\020\002\n\002\b\002\n\002\020\013\n\002\b\002\n\002\020\005\n\000\n\002\020\t\n\002\b\006\n\002\030\002\n\002\b\005\n\002\030\002\n\002\b\b\n\002\020\b\n\002\b\003\n\002\030\002\n\002\b\003\n\002\020\022\n\002\b\003\n\002\030\002\n\002\b\025\n\002\020\n\n\002\b\003\n\002\030\002\n\000\n\002\020\016\n\002\b\016\n\002\030\002\n\002\b\004\n\002\030\002\n\002\b\013\b\000\030\0002\0020\001B\017\022\006\020\003\032\0020\002¢\006\004\b\004\020\005J\017\020\007\032\0020\006H\026¢\006\004\b\007\020\bJ\017\020\n\032\0020\tH\026¢\006\004\b\n\020\013J\017\020\r\032\0020\fH\026¢\006\004\b\r\020\016J\027\020\022\032\0020\0212\006\020\020\032\0020\017H\026¢\006\004\b\022\020\023J\037\020\022\032\0020\0212\006\020\020\032\0020\0172\006\020\024\032\0020\021H\026¢\006\004\b\022\020\025J'\020\022\032\0020\0212\006\020\020\032\0020\0172\006\020\024\032\0020\0212\006\020\026\032\0020\021H\026¢\006\004\b\022\020\027J\027\020\022\032\0020\0212\006\020\031\032\0020\030H\026¢\006\004\b\022\020\032J\037\020\022\032\0020\0212\006\020\031\032\0020\0302\006\020\024\032\0020\021H\026¢\006\004\b\022\020\033J\027\020\035\032\0020\0212\006\020\034\032\0020\030H\026¢\006\004\b\035\020\032J\037\020\035\032\0020\0212\006\020\034\032\0020\0302\006\020\024\032\0020\021H\026¢\006\004\b\035\020\033J\017\020\037\032\0020\036H\026¢\006\004\b\037\020 J\017\020!\032\0020\fH\026¢\006\004\b!\020\016J\017\020\"\032\0020\001H\026¢\006\004\b\"\020#J\037\020%\032\0020\f2\006\020$\032\0020\0212\006\020\031\032\0020\030H\026¢\006\004\b%\020&J/\020%\032\0020\f2\006\020$\032\0020\0212\006\020\031\032\0020\0302\006\020(\032\0020'2\006\020)\032\0020'H\026¢\006\004\b%\020*J\027\020-\032\0020'2\006\020,\032\0020+H\026¢\006\004\b-\020.J\027\020-\032\0020'2\006\020,\032\0020/H\026¢\006\004\b-\0200J'\020-\032\0020'2\006\020,\032\0020/2\006\020$\032\0020'2\006\020)\032\0020'H\026¢\006\004\b-\0201J\037\020-\032\0020\0212\006\020,\032\0020\0062\006\020)\032\0020\021H\026¢\006\004\b-\0202J\027\0204\032\0020\0212\006\020,\032\00203H\026¢\006\004\b4\0205J\017\0206\032\0020\017H\026¢\006\004\b6\0207J\017\0208\032\0020/H\026¢\006\004\b8\0209J\027\0208\032\0020/2\006\020)\032\0020\021H\026¢\006\004\b8\020:J\017\020;\032\0020\030H\026¢\006\004\b;\020<J\027\020;\032\0020\0302\006\020)\032\0020\021H\026¢\006\004\b;\020=J\017\020>\032\0020\021H\026¢\006\004\b>\020?J\027\020@\032\0020\t2\006\020,\032\0020/H\026¢\006\004\b@\020AJ\037\020@\032\0020\t2\006\020,\032\0020\0062\006\020)\032\0020\021H\026¢\006\004\b@\020BJ\017\020C\032\0020\021H\026¢\006\004\bC\020?J\017\020D\032\0020'H\026¢\006\004\bD\020EJ\017\020F\032\0020'H\026¢\006\004\bF\020EJ\017\020G\032\0020\021H\026¢\006\004\bG\020?J\017\020H\032\0020\021H\026¢\006\004\bH\020?J\017\020J\032\0020IH\026¢\006\004\bJ\020KJ\017\020L\032\0020IH\026¢\006\004\bL\020KJ\027\020P\032\0020O2\006\020N\032\0020MH\026¢\006\004\bP\020QJ\037\020P\032\0020O2\006\020)\032\0020\0212\006\020N\032\0020MH\026¢\006\004\bP\020RJ\017\020S\032\0020OH\026¢\006\004\bS\020TJ\027\020S\032\0020O2\006\020)\032\0020\021H\026¢\006\004\bS\020UJ\017\020V\032\0020'H\026¢\006\004\bV\020EJ\021\020W\032\004\030\0010OH\026¢\006\004\bW\020TJ\017\020X\032\0020OH\026¢\006\004\bX\020TJ\027\020X\032\0020O2\006\020Y\032\0020\021H\026¢\006\004\bX\020UJ\027\020Z\032\0020\f2\006\020)\032\0020\021H\026¢\006\004\bZ\020[J\027\020\\\032\0020\t2\006\020)\032\0020\021H\026¢\006\004\b\\\020]J\027\020`\032\0020'2\006\020_\032\0020^H\026¢\006\004\b`\020aJ\027\020b\032\0020\t2\006\020)\032\0020\021H\026¢\006\004\bb\020]J\017\020d\032\0020cH\026¢\006\004\bd\020eJ\017\020f\032\0020OH\026¢\006\004\bf\020TR\033\020\007\032\0020\0068Ö\002X\004¢\006\f\022\004\bh\020\013\032\004\bg\020\bR\024\020i\032\0020\0068\006X\004¢\006\006\n\004\bi\020jR\026\020k\032\0020\f8\006@\006X\016¢\006\006\n\004\bk\020lR\024\020\003\032\0020\0028\006X\004¢\006\006\n\004\b\003\020m¨\006n"}, d2 = {"Lokio/RealBufferedSource;", "Lokio/BufferedSource;", "Lokio/Source;", "source", "<init>", "(Lokio/Source;)V", "Lokio/Buffer;", "buffer", "()Lokio/Buffer;", "", "close", "()V", "", "exhausted", "()Z", "", "b", "", "indexOf", "(B)J", "fromIndex", "(BJ)J", "toIndex", "(BJJ)J", "Lokio/ByteString;", "bytes", "(Lokio/ByteString;)J", "(Lokio/ByteString;J)J", "targetBytes", "indexOfElement", "Ljava/io/InputStream;", "inputStream", "()Ljava/io/InputStream;", "isOpen", "peek", "()Lokio/BufferedSource;", "offset", "rangeEquals", "(JLokio/ByteString;)Z", "", "bytesOffset", "byteCount", "(JLokio/ByteString;II)Z", "Ljava/nio/ByteBuffer;", "sink", "read", "(Ljava/nio/ByteBuffer;)I", "", "([B)I", "([BII)I", "(Lokio/Buffer;J)J", "Lokio/Sink;", "readAll", "(Lokio/Sink;)J", "readByte", "()B", "readByteArray", "()[B", "(J)[B", "readByteString", "()Lokio/ByteString;", "(J)Lokio/ByteString;", "readDecimalLong", "()J", "readFully", "([B)V", "(Lokio/Buffer;J)V", "readHexadecimalUnsignedLong", "readInt", "()I", "readIntLe", "readLong", "readLongLe", "", "readShort", "()S", "readShortLe", "Ljava/nio/charset/Charset;", "charset", "", "readString", "(Ljava/nio/charset/Charset;)Ljava/lang/String;", "(JLjava/nio/charset/Charset;)Ljava/lang/String;", "readUtf8", "()Ljava/lang/String;", "(J)Ljava/lang/String;", "readUtf8CodePoint", "readUtf8Line", "readUtf8LineStrict", "limit", "request", "(J)Z", "require", "(J)V", "Lokio/Options;", "options", "select", "(Lokio/Options;)I", "skip", "Lokio/Timeout;", "timeout", "()Lokio/Timeout;", "toString", "getBuffer", "getBuffer$annotations", "bufferField", "Lokio/Buffer;", "closed", "Z", "Lokio/Source;", "okio"})
@SourceDebugExtension({"SMAP\nRealBufferedSource.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RealBufferedSource.kt\nokio/RealBufferedSource\n+ 2 RealBufferedSource.kt\nokio/internal/-RealBufferedSource\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 Util.kt\nokio/-SegmentedByteString\n*L\n1#1,185:1\n62#1:191\n62#1:201\n62#1:208\n62#1:214\n62#1:216\n62#1:220\n62#1:225\n62#1:240\n62#1:244\n62#1:251\n62#1:264\n62#1:272\n62#1:273\n62#1:274\n62#1:280\n62#1:288\n62#1:301\n62#1:305\n62#1:306\n62#1:307\n62#1:308\n62#1:313\n62#1:325\n62#1:341\n62#1:351\n62#1:354\n62#1:357\n62#1:360\n62#1:363\n62#1:366\n62#1:372\n62#1:389\n62#1:409\n62#1:424\n62#1:441\n62#1:454\n62#1:475\n62#1:482\n38#2:186\n39#2,3:188\n42#2,6:192\n51#2:198\n52#2:200\n56#2,2:202\n60#2:204\n61#2,2:206\n63#2,3:209\n69#2,2:212\n74#2:215\n75#2:217\n79#2,2:218\n84#2:221\n86#2,2:223\n88#2,13:226\n107#2:239\n108#2:241\n112#2,2:242\n117#2,6:245\n123#2,9:252\n134#2,3:261\n137#2,5:265\n142#2:271\n146#2,5:275\n151#2,5:281\n158#2,2:286\n160#2,11:289\n174#2:300\n175#2:302\n179#2,2:303\n184#2,4:309\n188#2,6:314\n198#2:320\n199#2,3:322\n202#2,8:326\n210#2,3:335\n217#2,3:338\n220#2,7:342\n230#2,2:349\n235#2,2:352\n240#2,2:355\n245#2,2:358\n250#2,2:361\n255#2,2:364\n260#2,5:367\n265#2,11:373\n279#2,5:384\n284#2,14:390\n301#2,2:404\n303#2,2:407\n305#2,7:410\n314#2,2:417\n316#2,4:420\n320#2,11:425\n334#2,2:436\n337#2,2:439\n339#2,7:442\n350#2,2:449\n353#2,2:452\n355#2,7:455\n371#2:462\n373#2,11:464\n385#2:476\n389#2:477\n393#2,4:478\n397#2:483\n399#2:484\n401#2:485\n1#3:187\n1#3:199\n1#3:205\n1#3:222\n1#3:321\n1#3:406\n1#3:419\n1#3:438\n1#3:451\n1#3:463\n89#4:270\n89#4:334\n*S KotlinDebug\n*F\n+ 1 RealBufferedSource.kt\nokio/RealBufferedSource\n*L\n66#1:191\n67#1:201\n69#1:208\n70#1:214\n71#1:216\n72#1:220\n73#1:225\n74#1:240\n75#1:244\n77#1:251\n79#1:264\n82#1:272\n83#1:273\n87#1:274\n90#1:280\n91#1:288\n92#1:301\n93#1:305\n96#1:306\n97#1:307\n102#1:308\n105#1:313\n107#1:325\n108#1:341\n109#1:351\n110#1:354\n111#1:357\n112#1:360\n113#1:363\n114#1:366\n115#1:372\n116#1:389\n117#1:409\n121#1:424\n124#1:441\n127#1:454\n141#1:475\n181#1:482\n66#1:186\n66#1:188,3\n66#1:192,6\n67#1:198\n67#1:200\n68#1:202,2\n69#1:204\n69#1:206,2\n69#1:209,3\n70#1:212,2\n71#1:215\n71#1:217\n72#1:218,2\n73#1:221\n73#1:223,2\n73#1:226,13\n74#1:239\n74#1:241\n75#1:242,2\n77#1:245,6\n77#1:252,9\n79#1:261,3\n79#1:265,5\n79#1:271\n90#1:275,5\n90#1:281,5\n91#1:286,2\n91#1:289,11\n92#1:300\n92#1:302\n93#1:303,2\n105#1:309,4\n105#1:314,6\n107#1:320\n107#1:322,3\n107#1:326,8\n107#1:335,3\n108#1:338,3\n108#1:342,7\n109#1:349,2\n110#1:352,2\n111#1:355,2\n112#1:358,2\n113#1:361,2\n114#1:364,2\n115#1:367,5\n115#1:373,11\n116#1:384,5\n116#1:390,14\n117#1:404,2\n117#1:407,2\n117#1:410,7\n121#1:417,2\n121#1:420,4\n121#1:425,11\n124#1:436,2\n124#1:439,2\n124#1:442,7\n127#1:449,2\n127#1:452,2\n127#1:455,7\n141#1:462\n141#1:464,11\n141#1:476\n143#1:477\n181#1:478,4\n181#1:483\n182#1:484\n183#1:485\n66#1:187\n67#1:199\n69#1:205\n73#1:222\n107#1:321\n117#1:406\n121#1:419\n124#1:438\n127#1:451\n141#1:463\n79#1:270\n107#1:334\n*E\n"})
public final class RealBufferedSource implements BufferedSource {
  @JvmField
  @NotNull
  public final Source source;
  
  @JvmField
  @NotNull
  public final Buffer bufferField;
  
  @JvmField
  public boolean closed;
  
  public RealBufferedSource(@NotNull Source source) {
    this.source = source;
    this.bufferField = new Buffer();
  }
  
  @NotNull
  public Buffer getBuffer() {
    int $i$f$getBuffer = 0;
    return this.bufferField;
  }
  
  @NotNull
  public Buffer buffer() {
    return this.bufferField;
  }
  
  public long read(@NotNull Buffer sink, long byteCount) {
    Intrinsics.checkNotNullParameter(sink, "sink");
    RealBufferedSource $this$commonRead$iv = this;
    int $i$f$commonRead = 0;
    if (!((byteCount >= 0L) ? 1 : 0)) {
      int $i$a$-require--RealBufferedSource$commonRead$1$iv = 0;
      String str = "byteCount < 0: " + byteCount;
      throw new IllegalArgumentException(str.toString());
    } 
    if (!(!$this$commonRead$iv.closed ? 1 : 0)) {
      int $i$a$-check--RealBufferedSource$commonRead$2$iv = 0;
      String str = "closed";
      throw new IllegalStateException(str.toString());
    } 
    RealBufferedSource this_$iv$iv = $this$commonRead$iv;
    int $i$f$getBuffer = 0;
    if (this_$iv$iv.bufferField.size() == 0L) {
      RealBufferedSource realBufferedSource = $this$commonRead$iv;
      int j = 0;
      long read$iv = $this$commonRead$iv.source.read(realBufferedSource.bufferField, 8192L);
      if (read$iv == -1L);
    } 
    RealBufferedSource realBufferedSource1 = $this$commonRead$iv;
    int i = 0;
    long l1 = realBufferedSource1.bufferField.size(), toRead$iv = Math.min(byteCount, l1);
    realBufferedSource1 = $this$commonRead$iv;
    i = 0;
    return realBufferedSource1.bufferField.read(sink, toRead$iv);
  }
  
  public boolean exhausted() {
    RealBufferedSource $this$commonExhausted$iv = this;
    int $i$f$commonExhausted = 0;
    if (!(!$this$commonExhausted$iv.closed ? 1 : 0)) {
      int $i$a$-check--RealBufferedSource$commonExhausted$1$iv = 0;
      String str = "closed";
      throw new IllegalStateException(str.toString());
    } 
    RealBufferedSource this_$iv$iv = $this$commonExhausted$iv;
    int $i$f$getBuffer = 0;
    if (this_$iv$iv.bufferField.exhausted()) {
      this_$iv$iv = $this$commonExhausted$iv;
      $i$f$getBuffer = 0;
      if ($this$commonExhausted$iv.source.read(this_$iv$iv.bufferField, 8192L) == -1L);
    } 
    return false;
  }
  
  public void require(long byteCount) {
    RealBufferedSource $this$commonRequire$iv = this;
    int $i$f$commonRequire = 0;
    if (!$this$commonRequire$iv.request(byteCount))
      throw new EOFException(); 
  }
  
  public boolean request(long byteCount) {
    // Byte code:
    //   0: aload_0
    //   1: astore_3
    //   2: iconst_0
    //   3: istore #4
    //   5: lload_1
    //   6: lconst_0
    //   7: lcmp
    //   8: iflt -> 15
    //   11: iconst_1
    //   12: goto -> 16
    //   15: iconst_0
    //   16: ifne -> 56
    //   19: iconst_0
    //   20: istore #5
    //   22: new java/lang/StringBuilder
    //   25: dup
    //   26: invokespecial <init> : ()V
    //   29: ldc 'byteCount < 0: '
    //   31: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   34: lload_1
    //   35: invokevirtual append : (J)Ljava/lang/StringBuilder;
    //   38: invokevirtual toString : ()Ljava/lang/String;
    //   41: astore #5
    //   43: new java/lang/IllegalArgumentException
    //   46: dup
    //   47: aload #5
    //   49: invokevirtual toString : ()Ljava/lang/String;
    //   52: invokespecial <init> : (Ljava/lang/String;)V
    //   55: athrow
    //   56: aload_3
    //   57: getfield closed : Z
    //   60: ifne -> 67
    //   63: iconst_1
    //   64: goto -> 68
    //   67: iconst_0
    //   68: ifne -> 91
    //   71: iconst_0
    //   72: istore #5
    //   74: ldc 'closed'
    //   76: astore #5
    //   78: new java/lang/IllegalStateException
    //   81: dup
    //   82: aload #5
    //   84: invokevirtual toString : ()Ljava/lang/String;
    //   87: invokespecial <init> : (Ljava/lang/String;)V
    //   90: athrow
    //   91: aload_3
    //   92: astore #6
    //   94: iconst_0
    //   95: istore #5
    //   97: aload #6
    //   99: getfield bufferField : Lokio/Buffer;
    //   102: invokevirtual size : ()J
    //   105: lload_1
    //   106: lcmp
    //   107: ifge -> 144
    //   110: aload_3
    //   111: getfield source : Lokio/Source;
    //   114: aload_3
    //   115: astore #6
    //   117: iconst_0
    //   118: istore #5
    //   120: aload #6
    //   122: getfield bufferField : Lokio/Buffer;
    //   125: ldc2_w 8192
    //   128: invokeinterface read : (Lokio/Buffer;J)J
    //   133: ldc2_w -1
    //   136: lcmp
    //   137: ifne -> 91
    //   140: iconst_0
    //   141: goto -> 145
    //   144: iconst_1
    //   145: ireturn
    // Line number table:
    //   Java source line number -> byte code offset
    //   #69	-> 0
    //   #204	-> 5
    //   #205	-> 19
    //   #204	-> 22
    //   #204	-> 41
    //   #206	-> 56
    //   #205	-> 71
    //   #206	-> 74
    //   #206	-> 76
    //   #207	-> 91
    //   #208	-> 97
    //   #207	-> 102
    //   #209	-> 110
    //   #208	-> 120
    //   #209	-> 125
    //   #211	-> 144
    //   #69	-> 145
    // Local variable table:
    //   start	length	slot	name	descriptor
    //   22	19	5	$i$a$-require--RealBufferedSource$commonRequest$1$iv	I
    //   74	2	5	$i$a$-check--RealBufferedSource$commonRequest$2$iv	I
    //   97	5	5	$i$f$getBuffer	I
    //   94	8	6	this_$iv$iv	Lokio/RealBufferedSource;
    //   120	5	5	$i$f$getBuffer	I
    //   117	8	6	this_$iv$iv	Lokio/RealBufferedSource;
    //   5	140	4	$i$f$commonRequest	I
    //   2	143	3	$this$commonRequest$iv	Lokio/RealBufferedSource;
    //   0	146	0	this	Lokio/RealBufferedSource;
    //   0	146	1	byteCount	J
  }
  
  public byte readByte() {
    RealBufferedSource $this$commonReadByte$iv = this;
    int $i$f$commonReadByte = 0;
    $this$commonReadByte$iv.require(1L);
    RealBufferedSource this_$iv$iv = $this$commonReadByte$iv;
    int $i$f$getBuffer = 0;
    return this_$iv$iv.bufferField.readByte();
  }
  
  @NotNull
  public ByteString readByteString() {
    RealBufferedSource $this$commonReadByteString$iv = this;
    int $i$f$commonReadByteString = 0;
    RealBufferedSource this_$iv$iv = $this$commonReadByteString$iv;
    int $i$f$getBuffer = 0;
    this_$iv$iv.bufferField.writeAll($this$commonReadByteString$iv.source);
    this_$iv$iv = $this$commonReadByteString$iv;
    $i$f$getBuffer = 0;
    return this_$iv$iv.bufferField.readByteString();
  }
  
  @NotNull
  public ByteString readByteString(long byteCount) {
    RealBufferedSource $this$commonReadByteString$iv = this;
    int $i$f$commonReadByteString = 0;
    $this$commonReadByteString$iv.require(byteCount);
    RealBufferedSource this_$iv$iv = $this$commonReadByteString$iv;
    int $i$f$getBuffer = 0;
    return this_$iv$iv.bufferField.readByteString(byteCount);
  }
  
  public int select(@NotNull Options options) {
    int index$iv;
    Intrinsics.checkNotNullParameter(options, "options");
    RealBufferedSource $this$commonSelect$iv = this;
    int $i$f$commonSelect = 0;
    if (!(!$this$commonSelect$iv.closed ? 1 : 0)) {
      int $i$a$-check--RealBufferedSource$commonSelect$1$iv = 0;
      String str = "closed";
      throw new IllegalStateException(str.toString());
    } 
    while (true) {
      RealBufferedSource realBufferedSource2;
      int j;
      RealBufferedSource realBufferedSource1 = $this$commonSelect$iv;
      int i = 0;
      index$iv = -Buffer.selectPrefix(
          realBufferedSource1.bufferField, options, true);
      switch (index$iv) {
        case -1:
        
        case -2:
          realBufferedSource2 = $this$commonSelect$iv;
          j = 0;
          if ($this$commonSelect$iv.source.read(realBufferedSource2.bufferField, 8192L) == -1L);
          continue;
      } 
      break;
    } 
    int selectedSize$iv = options.getByteStrings$okio()[index$iv].size();
    RealBufferedSource this_$iv$iv = $this$commonSelect$iv;
    int $i$f$getBuffer = 0;
    this_$iv$iv.bufferField.skip(selectedSize$iv);
  }
  
  @NotNull
  public byte[] readByteArray() {
    RealBufferedSource $this$commonReadByteArray$iv = this;
    int $i$f$commonReadByteArray = 0;
    RealBufferedSource this_$iv$iv = $this$commonReadByteArray$iv;
    int $i$f$getBuffer = 0;
    this_$iv$iv.bufferField.writeAll($this$commonReadByteArray$iv.source);
    this_$iv$iv = $this$commonReadByteArray$iv;
    $i$f$getBuffer = 0;
    return this_$iv$iv.bufferField.readByteArray();
  }
  
  @NotNull
  public byte[] readByteArray(long byteCount) {
    RealBufferedSource $this$commonReadByteArray$iv = this;
    int $i$f$commonReadByteArray = 0;
    $this$commonReadByteArray$iv.require(byteCount);
    RealBufferedSource this_$iv$iv = $this$commonReadByteArray$iv;
    int $i$f$getBuffer = 0;
    return this_$iv$iv.bufferField.readByteArray(byteCount);
  }
  
  public int read(@NotNull byte[] sink) {
    Intrinsics.checkNotNullParameter(sink, "sink");
    return read(sink, 0, sink.length);
  }
  
  public void readFully(@NotNull byte[] sink) {
    Intrinsics.checkNotNullParameter(sink, "sink");
    RealBufferedSource $this$commonReadFully$iv = this;
    int $i$f$commonReadFully = 0;
    try {
      $this$commonReadFully$iv.require(sink.length);
    } catch (EOFException e$iv) {
      int offset$iv = 0;
      while (true) {
        RealBufferedSource realBufferedSource = $this$commonReadFully$iv;
        int i = 0;
        if (realBufferedSource.bufferField.size() > 0L) {
          RealBufferedSource realBufferedSource1 = $this$commonReadFully$iv;
          int j = 0;
          realBufferedSource1 = $this$commonReadFully$iv;
          j = 0;
          int read$iv = realBufferedSource1.bufferField.read(sink, offset$iv, (int)realBufferedSource1.bufferField.size());
          if (read$iv == -1)
            throw new AssertionError(); 
          offset$iv += read$iv;
          continue;
        } 
        break;
      } 
      throw e$iv;
    } 
    RealBufferedSource this_$iv$iv = $this$commonReadFully$iv;
    int $i$f$getBuffer = 0;
    this_$iv$iv.bufferField.readFully(sink);
  }
  
  public int read(@NotNull byte[] sink, int offset, int byteCount) {
    Intrinsics.checkNotNullParameter(sink, "sink");
    RealBufferedSource $this$commonRead$iv = this;
    int $i$f$commonRead = 0;
    -SegmentedByteString.checkOffsetAndCount(sink.length, offset, byteCount);
    RealBufferedSource this_$iv$iv = $this$commonRead$iv;
    int $i$f$getBuffer = 0;
    if (this_$iv$iv.bufferField.size() == 0L) {
      RealBufferedSource realBufferedSource = $this$commonRead$iv;
      int j = 0;
      long read$iv = $this$commonRead$iv.source.read(realBufferedSource.bufferField, 8192L);
      if (read$iv == -1L);
    } 
    RealBufferedSource realBufferedSource1 = $this$commonRead$iv;
    int i = 0;
    long b$iv$iv = realBufferedSource1.bufferField.size();
    int $i$f$minOf = 0, toRead$iv = (int)
      Math.min(byteCount, b$iv$iv);
    realBufferedSource1 = $this$commonRead$iv;
    i = 0;
    return realBufferedSource1.bufferField.read(sink, offset, toRead$iv);
  }
  
  public int read(@NotNull ByteBuffer sink) {
    // Byte code:
    //   0: aload_1
    //   1: ldc 'sink'
    //   3: invokestatic checkNotNullParameter : (Ljava/lang/Object;Ljava/lang/String;)V
    //   6: aload_0
    //   7: astore_2
    //   8: iconst_0
    //   9: istore_3
    //   10: aload_2
    //   11: getfield bufferField : Lokio/Buffer;
    //   14: invokevirtual size : ()J
    //   17: lconst_0
    //   18: lcmp
    //   19: ifne -> 56
    //   22: aload_0
    //   23: getfield source : Lokio/Source;
    //   26: aload_0
    //   27: astore #4
    //   29: iconst_0
    //   30: istore #5
    //   32: aload #4
    //   34: getfield bufferField : Lokio/Buffer;
    //   37: ldc2_w 8192
    //   40: invokeinterface read : (Lokio/Buffer;J)J
    //   45: lstore_2
    //   46: lload_2
    //   47: ldc2_w -1
    //   50: lcmp
    //   51: ifne -> 56
    //   54: iconst_m1
    //   55: ireturn
    //   56: aload_0
    //   57: astore_2
    //   58: iconst_0
    //   59: istore_3
    //   60: aload_2
    //   61: getfield bufferField : Lokio/Buffer;
    //   64: aload_1
    //   65: invokevirtual read : (Ljava/nio/ByteBuffer;)I
    //   68: ireturn
    // Line number table:
    //   Java source line number -> byte code offset
    //   #82	-> 6
    //   #272	-> 10
    //   #82	-> 14
    //   #83	-> 22
    //   #273	-> 32
    //   #83	-> 37
    //   #84	-> 46
    //   #87	-> 56
    //   #274	-> 60
    //   #87	-> 64
    // Local variable table:
    //   start	length	slot	name	descriptor
    //   10	4	3	$i$f$getBuffer	I
    //   8	6	2	this_$iv	Lokio/RealBufferedSource;
    //   32	5	5	$i$f$getBuffer	I
    //   29	8	4	this_$iv	Lokio/RealBufferedSource;
    //   46	10	2	read	J
    //   60	4	3	$i$f$getBuffer	I
    //   58	6	2	this_$iv	Lokio/RealBufferedSource;
    //   0	69	0	this	Lokio/RealBufferedSource;
    //   0	69	1	sink	Ljava/nio/ByteBuffer;
  }
  
  public void readFully(@NotNull Buffer sink, long byteCount) {
    Intrinsics.checkNotNullParameter(sink, "sink");
    RealBufferedSource $this$commonReadFully$iv = this;
    int $i$f$commonReadFully = 0;
    try {
      $this$commonReadFully$iv.require(byteCount);
    } catch (EOFException e$iv) {
      RealBufferedSource realBufferedSource = $this$commonReadFully$iv;
      int i = 0;
      sink.writeAll(
          realBufferedSource.bufferField);
      throw e$iv;
    } 
    RealBufferedSource this_$iv$iv = $this$commonReadFully$iv;
    int $i$f$getBuffer = 0;
    this_$iv$iv.bufferField.readFully(sink, byteCount);
  }
  
  public long readAll(@NotNull Sink sink) {
    Intrinsics.checkNotNullParameter(sink, "sink");
    RealBufferedSource $this$commonReadAll$iv = this;
    int $i$f$commonReadAll = 0;
    long totalBytesWritten$iv = 0L;
    while (true) {
      RealBufferedSource realBufferedSource = $this$commonReadAll$iv;
      int i = 0;
      if ($this$commonReadAll$iv.source.read(
          realBufferedSource.bufferField, 8192L) != -1L) {
        RealBufferedSource realBufferedSource1 = $this$commonReadAll$iv;
        int j = 0;
        long emitByteCount$iv = realBufferedSource1.bufferField.completeSegmentByteCount();
        if (emitByteCount$iv > 0L) {
          totalBytesWritten$iv += emitByteCount$iv;
          realBufferedSource1 = $this$commonReadAll$iv;
          j = 0;
          sink.write(realBufferedSource1.bufferField, emitByteCount$iv);
        } 
        continue;
      } 
      break;
    } 
    RealBufferedSource this_$iv$iv = $this$commonReadAll$iv;
    int $i$f$getBuffer = 0;
    if (this_$iv$iv.bufferField.size() > 0L) {
      this_$iv$iv = $this$commonReadAll$iv;
      $i$f$getBuffer = 0;
      totalBytesWritten$iv += this_$iv$iv.bufferField.size();
      this_$iv$iv = $this$commonReadAll$iv;
      $i$f$getBuffer = 0;
      this_$iv$iv = $this$commonReadAll$iv;
      $i$f$getBuffer = 0;
      sink.write(this_$iv$iv.bufferField, this_$iv$iv.bufferField.size());
    } 
    return totalBytesWritten$iv;
  }
  
  @NotNull
  public String readUtf8() {
    RealBufferedSource $this$commonReadUtf8$iv = this;
    int $i$f$commonReadUtf8 = 0;
    RealBufferedSource this_$iv$iv = $this$commonReadUtf8$iv;
    int $i$f$getBuffer = 0;
    this_$iv$iv.bufferField.writeAll($this$commonReadUtf8$iv.source);
    this_$iv$iv = $this$commonReadUtf8$iv;
    $i$f$getBuffer = 0;
    return this_$iv$iv.bufferField.readUtf8();
  }
  
  @NotNull
  public String readUtf8(long byteCount) {
    RealBufferedSource $this$commonReadUtf8$iv = this;
    int $i$f$commonReadUtf8 = 0;
    $this$commonReadUtf8$iv.require(byteCount);
    RealBufferedSource this_$iv$iv = $this$commonReadUtf8$iv;
    int $i$f$getBuffer = 0;
    return this_$iv$iv.bufferField.readUtf8(byteCount);
  }
  
  @NotNull
  public String readString(@NotNull Charset charset) {
    Intrinsics.checkNotNullParameter(charset, "charset");
    RealBufferedSource this_$iv = this;
    int $i$f$getBuffer = 0;
    this_$iv.bufferField.writeAll(this.source);
    this_$iv = this;
    $i$f$getBuffer = 0;
    return this_$iv.bufferField.readString(charset);
  }
  
  @NotNull
  public String readString(long byteCount, @NotNull Charset charset) {
    Intrinsics.checkNotNullParameter(charset, "charset");
    require(byteCount);
    RealBufferedSource this_$iv = this;
    int $i$f$getBuffer = 0;
    return this_$iv.bufferField.readString(byteCount, charset);
  }
  
  @Nullable
  public String readUtf8Line() {
    RealBufferedSource $this$commonReadUtf8Line$iv = this;
    int $i$f$commonReadUtf8Line = 0;
    long newline$iv = $this$commonReadUtf8Line$iv.indexOf((byte)10);
    RealBufferedSource this_$iv$iv = $this$commonReadUtf8Line$iv;
    int $i$f$getBuffer = 0;
    this_$iv$iv = $this$commonReadUtf8Line$iv;
    $i$f$getBuffer = 0;
    this_$iv$iv = $this$commonReadUtf8Line$iv;
    $i$f$getBuffer = 0;
    return (newline$iv == -1L) ? ((this_$iv$iv.bufferField.size() != 0L) ? $this$commonReadUtf8Line$iv.readUtf8(this_$iv$iv.bufferField.size()) : null) : -Buffer.readUtf8Line(this_$iv$iv.bufferField, newline$iv);
  }
  
  @NotNull
  public String readUtf8LineStrict() {
    return readUtf8LineStrict(Long.MAX_VALUE);
  }
  
  @NotNull
  public String readUtf8LineStrict(long limit) {
    // Byte code:
    //   0: aload_0
    //   1: astore_3
    //   2: iconst_0
    //   3: istore #4
    //   5: lload_1
    //   6: lconst_0
    //   7: lcmp
    //   8: iflt -> 15
    //   11: iconst_1
    //   12: goto -> 16
    //   15: iconst_0
    //   16: ifne -> 57
    //   19: iconst_0
    //   20: istore #5
    //   22: new java/lang/StringBuilder
    //   25: dup
    //   26: invokespecial <init> : ()V
    //   29: ldc_w 'limit < 0: '
    //   32: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   35: lload_1
    //   36: invokevirtual append : (J)Ljava/lang/StringBuilder;
    //   39: invokevirtual toString : ()Ljava/lang/String;
    //   42: astore #5
    //   44: new java/lang/IllegalArgumentException
    //   47: dup
    //   48: aload #5
    //   50: invokevirtual toString : ()Ljava/lang/String;
    //   53: invokespecial <init> : (Ljava/lang/String;)V
    //   56: athrow
    //   57: lload_1
    //   58: ldc2_w 9223372036854775807
    //   61: lcmp
    //   62: ifne -> 71
    //   65: ldc2_w 9223372036854775807
    //   68: goto -> 74
    //   71: lload_1
    //   72: lconst_1
    //   73: ladd
    //   74: lstore #6
    //   76: aload_3
    //   77: bipush #10
    //   79: lconst_0
    //   80: lload #6
    //   82: invokevirtual indexOf : (BJJ)J
    //   85: lstore #8
    //   87: lload #8
    //   89: ldc2_w -1
    //   92: lcmp
    //   93: ifeq -> 115
    //   96: aload_3
    //   97: astore #10
    //   99: iconst_0
    //   100: istore #11
    //   102: aload #10
    //   104: getfield bufferField : Lokio/Buffer;
    //   107: lload #8
    //   109: invokestatic readUtf8Line : (Lokio/Buffer;J)Ljava/lang/String;
    //   112: goto -> 334
    //   115: lload #6
    //   117: ldc2_w 9223372036854775807
    //   120: lcmp
    //   121: ifge -> 207
    //   124: aload_3
    //   125: lload #6
    //   127: invokevirtual request : (J)Z
    //   130: ifeq -> 207
    //   133: aload_3
    //   134: astore #10
    //   136: iconst_0
    //   137: istore #11
    //   139: aload #10
    //   141: getfield bufferField : Lokio/Buffer;
    //   144: lload #6
    //   146: lconst_1
    //   147: lsub
    //   148: invokevirtual getByte : (J)B
    //   151: bipush #13
    //   153: if_icmpne -> 207
    //   156: aload_3
    //   157: lload #6
    //   159: lconst_1
    //   160: ladd
    //   161: invokevirtual request : (J)Z
    //   164: ifeq -> 207
    //   167: aload_3
    //   168: astore #10
    //   170: iconst_0
    //   171: istore #11
    //   173: aload #10
    //   175: getfield bufferField : Lokio/Buffer;
    //   178: lload #6
    //   180: invokevirtual getByte : (J)B
    //   183: bipush #10
    //   185: if_icmpne -> 207
    //   188: aload_3
    //   189: astore #10
    //   191: iconst_0
    //   192: istore #11
    //   194: aload #10
    //   196: getfield bufferField : Lokio/Buffer;
    //   199: lload #6
    //   201: invokestatic readUtf8Line : (Lokio/Buffer;J)Ljava/lang/String;
    //   204: goto -> 334
    //   207: new okio/Buffer
    //   210: dup
    //   211: invokespecial <init> : ()V
    //   214: astore #10
    //   216: aload_3
    //   217: astore #11
    //   219: iconst_0
    //   220: istore #12
    //   222: aload #11
    //   224: getfield bufferField : Lokio/Buffer;
    //   227: aload #10
    //   229: lconst_0
    //   230: bipush #32
    //   232: istore #11
    //   234: aload_3
    //   235: astore #12
    //   237: iconst_0
    //   238: istore #13
    //   240: aload #12
    //   242: getfield bufferField : Lokio/Buffer;
    //   245: invokevirtual size : ()J
    //   248: lstore #14
    //   250: iconst_0
    //   251: istore #16
    //   253: iload #11
    //   255: i2l
    //   256: lload #14
    //   258: invokestatic min : (JJ)J
    //   261: nop
    //   262: invokevirtual copyTo : (Lokio/Buffer;JJ)Lokio/Buffer;
    //   265: pop
    //   266: new java/io/EOFException
    //   269: dup
    //   270: new java/lang/StringBuilder
    //   273: dup
    //   274: invokespecial <init> : ()V
    //   277: ldc_w '\n not found: limit='
    //   280: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   283: aload_3
    //   284: astore #11
    //   286: iconst_0
    //   287: istore #12
    //   289: aload #11
    //   291: getfield bufferField : Lokio/Buffer;
    //   294: invokevirtual size : ()J
    //   297: lload_1
    //   298: invokestatic min : (JJ)J
    //   301: invokevirtual append : (J)Ljava/lang/StringBuilder;
    //   304: ldc_w ' content='
    //   307: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   310: aload #10
    //   312: invokevirtual readByteString : ()Lokio/ByteString;
    //   315: invokevirtual hex : ()Ljava/lang/String;
    //   318: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   321: sipush #8230
    //   324: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   327: invokevirtual toString : ()Ljava/lang/String;
    //   330: invokespecial <init> : (Ljava/lang/String;)V
    //   333: athrow
    //   334: areturn
    // Line number table:
    //   Java source line number -> byte code offset
    //   #107	-> 0
    //   #320	-> 5
    //   #321	-> 19
    //   #320	-> 22
    //   #320	-> 42
    //   #322	-> 57
    //   #323	-> 76
    //   #324	-> 87
    //   #325	-> 102
    //   #324	-> 107
    //   #326	-> 115
    //   #327	-> 124
    //   #325	-> 139
    //   #327	-> 144
    //   #328	-> 156
    //   #325	-> 173
    //   #328	-> 178
    //   #330	-> 188
    //   #325	-> 194
    //   #330	-> 199
    //   #332	-> 207
    //   #333	-> 216
    //   #325	-> 222
    //   #333	-> 227
    //   #325	-> 240
    //   #333	-> 245
    //   #334	-> 253
    //   #334	-> 261
    //   #333	-> 262
    //   #335	-> 266
    //   #336	-> 270
    //   #325	-> 289
    //   #336	-> 294
    //   #336	-> 301
    //   #337	-> 304
    //   #336	-> 307
    //   #337	-> 310
    //   #336	-> 318
    //   #335	-> 330
    //   #107	-> 334
    // Local variable table:
    //   start	length	slot	name	descriptor
    //   22	20	5	$i$a$-require--RealBufferedSource$commonReadUtf8LineStrict$1$iv	I
    //   102	5	11	$i$f$getBuffer	I
    //   99	8	10	this_$iv$iv	Lokio/RealBufferedSource;
    //   139	5	11	$i$f$getBuffer	I
    //   136	8	10	this_$iv$iv	Lokio/RealBufferedSource;
    //   173	5	11	$i$f$getBuffer	I
    //   170	8	10	this_$iv$iv	Lokio/RealBufferedSource;
    //   194	5	11	$i$f$getBuffer	I
    //   191	8	10	this_$iv$iv	Lokio/RealBufferedSource;
    //   222	5	12	$i$f$getBuffer	I
    //   219	8	11	this_$iv$iv	Lokio/RealBufferedSource;
    //   240	5	13	$i$f$getBuffer	I
    //   237	8	12	this_$iv$iv	Lokio/RealBufferedSource;
    //   253	9	16	$i$f$minOf	I
    //   250	12	11	a$iv$iv	I
    //   250	12	14	b$iv$iv	J
    //   289	5	12	$i$f$getBuffer	I
    //   286	8	11	this_$iv$iv	Lokio/RealBufferedSource;
    //   5	329	4	$i$f$commonReadUtf8LineStrict	I
    //   76	258	6	scanLength$iv	J
    //   87	247	8	newline$iv	J
    //   216	118	10	data$iv	Lokio/Buffer;
    //   2	332	3	$this$commonReadUtf8LineStrict$iv	Lokio/RealBufferedSource;
    //   0	335	0	this	Lokio/RealBufferedSource;
    //   0	335	1	limit	J
  }
  
  public int readUtf8CodePoint() {
    RealBufferedSource $this$commonReadUtf8CodePoint$iv = this;
    int $i$f$commonReadUtf8CodePoint = 0;
    $this$commonReadUtf8CodePoint$iv.require(1L);
    RealBufferedSource this_$iv$iv = $this$commonReadUtf8CodePoint$iv;
    int $i$f$getBuffer = 0, b0$iv = 
      this_$iv$iv.bufferField.getByte(0L);
    if ((b0$iv & 0xE0) == 192) {
      $this$commonReadUtf8CodePoint$iv.require(2L);
    } else if ((b0$iv & 0xF0) == 224) {
      $this$commonReadUtf8CodePoint$iv.require(3L);
    } else if ((b0$iv & 0xF8) == 240) {
      $this$commonReadUtf8CodePoint$iv.require(4L);
    } 
    this_$iv$iv = $this$commonReadUtf8CodePoint$iv;
    $i$f$getBuffer = 0;
    return this_$iv$iv.bufferField.readUtf8CodePoint();
  }
  
  public short readShort() {
    RealBufferedSource $this$commonReadShort$iv = this;
    int $i$f$commonReadShort = 0;
    $this$commonReadShort$iv.require(2L);
    RealBufferedSource this_$iv$iv = $this$commonReadShort$iv;
    int $i$f$getBuffer = 0;
    return this_$iv$iv.bufferField.readShort();
  }
  
  public short readShortLe() {
    RealBufferedSource $this$commonReadShortLe$iv = this;
    int $i$f$commonReadShortLe = 0;
    $this$commonReadShortLe$iv.require(2L);
    RealBufferedSource this_$iv$iv = $this$commonReadShortLe$iv;
    int $i$f$getBuffer = 0;
    return this_$iv$iv.bufferField.readShortLe();
  }
  
  public int readInt() {
    RealBufferedSource $this$commonReadInt$iv = this;
    int $i$f$commonReadInt = 0;
    $this$commonReadInt$iv.require(4L);
    RealBufferedSource this_$iv$iv = $this$commonReadInt$iv;
    int $i$f$getBuffer = 0;
    return this_$iv$iv.bufferField.readInt();
  }
  
  public int readIntLe() {
    RealBufferedSource $this$commonReadIntLe$iv = this;
    int $i$f$commonReadIntLe = 0;
    $this$commonReadIntLe$iv.require(4L);
    RealBufferedSource this_$iv$iv = $this$commonReadIntLe$iv;
    int $i$f$getBuffer = 0;
    return this_$iv$iv.bufferField.readIntLe();
  }
  
  public long readLong() {
    RealBufferedSource $this$commonReadLong$iv = this;
    int $i$f$commonReadLong = 0;
    $this$commonReadLong$iv.require(8L);
    RealBufferedSource this_$iv$iv = $this$commonReadLong$iv;
    int $i$f$getBuffer = 0;
    return this_$iv$iv.bufferField.readLong();
  }
  
  public long readLongLe() {
    RealBufferedSource $this$commonReadLongLe$iv = this;
    int $i$f$commonReadLongLe = 0;
    $this$commonReadLongLe$iv.require(8L);
    RealBufferedSource this_$iv$iv = $this$commonReadLongLe$iv;
    int $i$f$getBuffer = 0;
    return this_$iv$iv.bufferField.readLongLe();
  }
  
  public long readDecimalLong() {
    RealBufferedSource $this$commonReadDecimalLong$iv = this;
    int $i$f$commonReadDecimalLong = 0;
    $this$commonReadDecimalLong$iv.require(1L);
    long pos$iv = 0L;
    while ($this$commonReadDecimalLong$iv.request(pos$iv + 1L)) {
      RealBufferedSource realBufferedSource = $this$commonReadDecimalLong$iv;
      int i = 0;
      byte b$iv = 
        realBufferedSource.bufferField.getByte(pos$iv);
      if ((b$iv < 48 || b$iv > 57) && (pos$iv != 0L || b$iv != 45)) {
        if (pos$iv == 0L) {
          Intrinsics.checkNotNullExpressionValue(Integer.toString(b$iv, CharsKt.checkRadix(CharsKt.checkRadix(16))), "toString(this, checkRadix(radix))");
          throw new NumberFormatException("Expected a digit or '-' but was 0x" + Integer.toString(b$iv, CharsKt.checkRadix(CharsKt.checkRadix(16))));
        } 
        break;
      } 
      pos$iv++;
    } 
    RealBufferedSource this_$iv$iv = $this$commonReadDecimalLong$iv;
    int $i$f$getBuffer = 0;
    return this_$iv$iv.bufferField.readDecimalLong();
  }
  
  public long readHexadecimalUnsignedLong() {
    RealBufferedSource $this$commonReadHexadecimalUnsignedLong$iv = this;
    int $i$f$commonReadHexadecimalUnsignedLong = 0;
    $this$commonReadHexadecimalUnsignedLong$iv.require(1L);
    int pos$iv = 0;
    while ($this$commonReadHexadecimalUnsignedLong$iv.request((pos$iv + 1))) {
      RealBufferedSource realBufferedSource = $this$commonReadHexadecimalUnsignedLong$iv;
      int i = 0;
      byte b$iv = 
        realBufferedSource.bufferField.getByte(pos$iv);
      if ((b$iv < 48 || b$iv > 57) && (
        b$iv < 97 || b$iv > 102) && (
        b$iv < 65 || b$iv > 70)) {
        if (pos$iv == 0) {
          Intrinsics.checkNotNullExpressionValue(Integer.toString(b$iv, CharsKt.checkRadix(CharsKt.checkRadix(16))), "toString(this, checkRadix(radix))");
          throw new NumberFormatException("Expected leading [0-9a-fA-F] character but was 0x" + Integer.toString(b$iv, CharsKt.checkRadix(CharsKt.checkRadix(16))));
        } 
        break;
      } 
      pos$iv++;
    } 
    RealBufferedSource this_$iv$iv = $this$commonReadHexadecimalUnsignedLong$iv;
    int $i$f$getBuffer = 0;
    return this_$iv$iv.bufferField.readHexadecimalUnsignedLong();
  }
  
  public void skip(long byteCount) {
    RealBufferedSource $this$commonSkip$iv = this;
    int $i$f$commonSkip = 0;
    long byteCount$iv = byteCount;
    if (!(!$this$commonSkip$iv.closed ? 1 : 0)) {
      int $i$a$-check--RealBufferedSource$commonSkip$1$iv = 0;
      String str = "closed";
      throw new IllegalStateException(str.toString());
    } 
    while (byteCount$iv > 0L) {
      RealBufferedSource this_$iv$iv = $this$commonSkip$iv;
      int $i$f$getBuffer = 0;
      if (this_$iv$iv.bufferField.size() == 0L) {
        this_$iv$iv = $this$commonSkip$iv;
        $i$f$getBuffer = 0;
        if ($this$commonSkip$iv.source.read(this_$iv$iv.bufferField, 8192L) == -1L)
          throw new EOFException(); 
      } 
      RealBufferedSource realBufferedSource1 = $this$commonSkip$iv;
      int i = 0;
      long l1 = realBufferedSource1.bufferField.size(), toSkip$iv = Math.min(byteCount$iv, l1);
      realBufferedSource1 = $this$commonSkip$iv;
      i = 0;
      realBufferedSource1.bufferField.skip(toSkip$iv);
      byteCount$iv -= toSkip$iv;
    } 
  }
  
  public long indexOf(byte b) {
    return indexOf(b, 0L, Long.MAX_VALUE);
  }
  
  public long indexOf(byte b, long fromIndex) {
    return indexOf(b, fromIndex, Long.MAX_VALUE);
  }
  
  public long indexOf(byte b, long fromIndex, long toIndex) {
    // Byte code:
    //   0: aload_0
    //   1: astore #6
    //   3: iconst_0
    //   4: istore #7
    //   6: lconst_0
    //   7: lstore #17
    //   9: lload_2
    //   10: lstore #17
    //   12: aload #6
    //   14: getfield closed : Z
    //   17: ifne -> 24
    //   20: iconst_1
    //   21: goto -> 25
    //   24: iconst_0
    //   25: ifne -> 48
    //   28: iconst_0
    //   29: istore #8
    //   31: ldc 'closed'
    //   33: astore #8
    //   35: new java/lang/IllegalStateException
    //   38: dup
    //   39: aload #8
    //   41: invokevirtual toString : ()Ljava/lang/String;
    //   44: invokespecial <init> : (Ljava/lang/String;)V
    //   47: athrow
    //   48: lconst_0
    //   49: lload #17
    //   51: lcmp
    //   52: ifgt -> 71
    //   55: lload #17
    //   57: lload #4
    //   59: lcmp
    //   60: ifgt -> 67
    //   63: iconst_1
    //   64: goto -> 72
    //   67: iconst_0
    //   68: goto -> 72
    //   71: iconst_0
    //   72: ifne -> 125
    //   75: iconst_0
    //   76: istore #8
    //   78: new java/lang/StringBuilder
    //   81: dup
    //   82: invokespecial <init> : ()V
    //   85: ldc_w 'fromIndex='
    //   88: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   91: lload #17
    //   93: invokevirtual append : (J)Ljava/lang/StringBuilder;
    //   96: ldc_w ' toIndex='
    //   99: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   102: lload #4
    //   104: invokevirtual append : (J)Ljava/lang/StringBuilder;
    //   107: invokevirtual toString : ()Ljava/lang/String;
    //   110: astore #8
    //   112: new java/lang/IllegalArgumentException
    //   115: dup
    //   116: aload #8
    //   118: invokevirtual toString : ()Ljava/lang/String;
    //   121: invokespecial <init> : (Ljava/lang/String;)V
    //   124: athrow
    //   125: lload #17
    //   127: lload #4
    //   129: lcmp
    //   130: ifge -> 244
    //   133: aload #6
    //   135: astore #9
    //   137: iconst_0
    //   138: istore #10
    //   140: aload #9
    //   142: getfield bufferField : Lokio/Buffer;
    //   145: iload_1
    //   146: lload #17
    //   148: lload #4
    //   150: invokevirtual indexOf : (BJJ)J
    //   153: lstore #11
    //   155: lload #11
    //   157: ldc2_w -1
    //   160: lcmp
    //   161: ifeq -> 169
    //   164: lload #11
    //   166: goto -> 247
    //   169: aload #6
    //   171: astore #13
    //   173: iconst_0
    //   174: istore #14
    //   176: aload #13
    //   178: getfield bufferField : Lokio/Buffer;
    //   181: invokevirtual size : ()J
    //   184: lstore #15
    //   186: lload #15
    //   188: lload #4
    //   190: lcmp
    //   191: ifge -> 226
    //   194: aload #6
    //   196: getfield source : Lokio/Source;
    //   199: aload #6
    //   201: astore #13
    //   203: iconst_0
    //   204: istore #14
    //   206: aload #13
    //   208: getfield bufferField : Lokio/Buffer;
    //   211: ldc2_w 8192
    //   214: invokeinterface read : (Lokio/Buffer;J)J
    //   219: ldc2_w -1
    //   222: lcmp
    //   223: ifne -> 232
    //   226: ldc2_w -1
    //   229: goto -> 247
    //   232: lload #17
    //   234: lload #15
    //   236: invokestatic max : (JJ)J
    //   239: lstore #17
    //   241: goto -> 125
    //   244: ldc2_w -1
    //   247: lreturn
    // Line number table:
    //   Java source line number -> byte code offset
    //   #121	-> 0
    //   #417	-> 6
    //   #418	-> 12
    //   #419	-> 28
    //   #418	-> 31
    //   #418	-> 33
    //   #420	-> 48
    //   #419	-> 75
    //   #420	-> 78
    //   #420	-> 110
    //   #422	-> 125
    //   #423	-> 133
    //   #424	-> 140
    //   #423	-> 145
    //   #425	-> 155
    //   #429	-> 169
    //   #424	-> 176
    //   #429	-> 181
    //   #430	-> 186
    //   #424	-> 206
    //   #430	-> 211
    //   #433	-> 232
    //   #433	-> 239
    //   #435	-> 244
    //   #121	-> 247
    // Local variable table:
    //   start	length	slot	name	descriptor
    //   31	2	8	$i$a$-check--RealBufferedSource$commonIndexOf$1$iv	I
    //   78	32	8	$i$a$-require--RealBufferedSource$commonIndexOf$2$iv	I
    //   140	5	10	$i$f$getBuffer	I
    //   137	8	9	this_$iv$iv	Lokio/RealBufferedSource;
    //   176	5	14	$i$f$getBuffer	I
    //   173	8	13	this_$iv$iv	Lokio/RealBufferedSource;
    //   206	5	14	$i$f$getBuffer	I
    //   203	8	13	this_$iv$iv	Lokio/RealBufferedSource;
    //   155	86	11	result$iv	J
    //   186	55	15	lastBufferSize$iv	J
    //   6	241	7	$i$f$commonIndexOf	I
    //   9	238	17	fromIndex$iv	J
    //   3	244	6	$this$commonIndexOf$iv	Lokio/RealBufferedSource;
    //   0	248	0	this	Lokio/RealBufferedSource;
    //   0	248	1	b	B
    //   0	248	2	fromIndex	J
    //   0	248	4	toIndex	J
  }
  
  public long indexOf(@NotNull ByteString bytes) {
    Intrinsics.checkNotNullParameter(bytes, "bytes");
    return indexOf(bytes, 0L);
  }
  
  public long indexOf(@NotNull ByteString bytes, long fromIndex) {
    // Byte code:
    //   0: aload_1
    //   1: ldc_w 'bytes'
    //   4: invokestatic checkNotNullParameter : (Ljava/lang/Object;Ljava/lang/String;)V
    //   7: aload_0
    //   8: astore #4
    //   10: iconst_0
    //   11: istore #5
    //   13: lload_2
    //   14: lstore #6
    //   16: aload #4
    //   18: getfield closed : Z
    //   21: ifne -> 28
    //   24: iconst_1
    //   25: goto -> 29
    //   28: iconst_0
    //   29: ifne -> 52
    //   32: iconst_0
    //   33: istore #8
    //   35: ldc 'closed'
    //   37: astore #8
    //   39: new java/lang/IllegalStateException
    //   42: dup
    //   43: aload #8
    //   45: invokevirtual toString : ()Ljava/lang/String;
    //   48: invokespecial <init> : (Ljava/lang/String;)V
    //   51: athrow
    //   52: nop
    //   53: aload #4
    //   55: astore #9
    //   57: iconst_0
    //   58: istore #10
    //   60: aload #9
    //   62: getfield bufferField : Lokio/Buffer;
    //   65: aload_1
    //   66: lload #6
    //   68: invokevirtual indexOf : (Lokio/ByteString;J)J
    //   71: lstore #11
    //   73: lload #11
    //   75: ldc2_w -1
    //   78: lcmp
    //   79: ifeq -> 87
    //   82: lload #11
    //   84: goto -> 162
    //   87: aload #4
    //   89: astore #13
    //   91: iconst_0
    //   92: istore #14
    //   94: aload #13
    //   96: getfield bufferField : Lokio/Buffer;
    //   99: invokevirtual size : ()J
    //   102: lstore #15
    //   104: aload #4
    //   106: getfield source : Lokio/Source;
    //   109: aload #4
    //   111: astore #13
    //   113: iconst_0
    //   114: istore #14
    //   116: aload #13
    //   118: getfield bufferField : Lokio/Buffer;
    //   121: ldc2_w 8192
    //   124: invokeinterface read : (Lokio/Buffer;J)J
    //   129: ldc2_w -1
    //   132: lcmp
    //   133: ifne -> 142
    //   136: ldc2_w -1
    //   139: goto -> 162
    //   142: lload #6
    //   144: lload #15
    //   146: aload_1
    //   147: invokevirtual size : ()I
    //   150: i2l
    //   151: lsub
    //   152: lconst_1
    //   153: ladd
    //   154: invokestatic max : (JJ)J
    //   157: lstore #6
    //   159: goto -> 52
    //   162: lreturn
    // Line number table:
    //   Java source line number -> byte code offset
    //   #124	-> 7
    //   #436	-> 13
    //   #437	-> 16
    //   #438	-> 32
    //   #437	-> 35
    //   #437	-> 37
    //   #439	-> 52
    //   #440	-> 53
    //   #441	-> 60
    //   #440	-> 65
    //   #442	-> 73
    //   #444	-> 87
    //   #441	-> 94
    //   #444	-> 99
    //   #445	-> 104
    //   #441	-> 116
    //   #445	-> 121
    //   #448	-> 142
    //   #448	-> 157
    //   #124	-> 162
    // Local variable table:
    //   start	length	slot	name	descriptor
    //   35	2	8	$i$a$-check--RealBufferedSource$commonIndexOf$3$iv	I
    //   60	5	10	$i$f$getBuffer	I
    //   57	8	9	this_$iv$iv	Lokio/RealBufferedSource;
    //   94	5	14	$i$f$getBuffer	I
    //   91	8	13	this_$iv$iv	Lokio/RealBufferedSource;
    //   116	5	14	$i$f$getBuffer	I
    //   113	8	13	this_$iv$iv	Lokio/RealBufferedSource;
    //   73	86	11	result$iv	J
    //   104	55	15	lastBufferSize$iv	J
    //   13	149	5	$i$f$commonIndexOf	I
    //   16	146	6	fromIndex$iv	J
    //   10	152	4	$this$commonIndexOf$iv	Lokio/RealBufferedSource;
    //   0	163	0	this	Lokio/RealBufferedSource;
    //   0	163	1	bytes	Lokio/ByteString;
    //   0	163	2	fromIndex	J
  }
  
  public long indexOfElement(@NotNull ByteString targetBytes) {
    Intrinsics.checkNotNullParameter(targetBytes, "targetBytes");
    return indexOfElement(targetBytes, 0L);
  }
  
  public long indexOfElement(@NotNull ByteString targetBytes, long fromIndex) {
    // Byte code:
    //   0: aload_1
    //   1: ldc_w 'targetBytes'
    //   4: invokestatic checkNotNullParameter : (Ljava/lang/Object;Ljava/lang/String;)V
    //   7: aload_0
    //   8: astore #4
    //   10: iconst_0
    //   11: istore #5
    //   13: lload_2
    //   14: lstore #6
    //   16: aload #4
    //   18: getfield closed : Z
    //   21: ifne -> 28
    //   24: iconst_1
    //   25: goto -> 29
    //   28: iconst_0
    //   29: ifne -> 52
    //   32: iconst_0
    //   33: istore #8
    //   35: ldc 'closed'
    //   37: astore #8
    //   39: new java/lang/IllegalStateException
    //   42: dup
    //   43: aload #8
    //   45: invokevirtual toString : ()Ljava/lang/String;
    //   48: invokespecial <init> : (Ljava/lang/String;)V
    //   51: athrow
    //   52: nop
    //   53: aload #4
    //   55: astore #9
    //   57: iconst_0
    //   58: istore #10
    //   60: aload #9
    //   62: getfield bufferField : Lokio/Buffer;
    //   65: aload_1
    //   66: lload #6
    //   68: invokevirtual indexOfElement : (Lokio/ByteString;J)J
    //   71: lstore #11
    //   73: lload #11
    //   75: ldc2_w -1
    //   78: lcmp
    //   79: ifeq -> 87
    //   82: lload #11
    //   84: goto -> 154
    //   87: aload #4
    //   89: astore #13
    //   91: iconst_0
    //   92: istore #14
    //   94: aload #13
    //   96: getfield bufferField : Lokio/Buffer;
    //   99: invokevirtual size : ()J
    //   102: lstore #15
    //   104: aload #4
    //   106: getfield source : Lokio/Source;
    //   109: aload #4
    //   111: astore #13
    //   113: iconst_0
    //   114: istore #14
    //   116: aload #13
    //   118: getfield bufferField : Lokio/Buffer;
    //   121: ldc2_w 8192
    //   124: invokeinterface read : (Lokio/Buffer;J)J
    //   129: ldc2_w -1
    //   132: lcmp
    //   133: ifne -> 142
    //   136: ldc2_w -1
    //   139: goto -> 154
    //   142: lload #6
    //   144: lload #15
    //   146: invokestatic max : (JJ)J
    //   149: lstore #6
    //   151: goto -> 52
    //   154: lreturn
    // Line number table:
    //   Java source line number -> byte code offset
    //   #127	-> 7
    //   #449	-> 13
    //   #450	-> 16
    //   #451	-> 32
    //   #450	-> 35
    //   #450	-> 37
    //   #452	-> 52
    //   #453	-> 53
    //   #454	-> 60
    //   #453	-> 65
    //   #455	-> 73
    //   #457	-> 87
    //   #454	-> 94
    //   #457	-> 99
    //   #458	-> 104
    //   #454	-> 116
    //   #458	-> 121
    //   #461	-> 142
    //   #461	-> 149
    //   #127	-> 154
    // Local variable table:
    //   start	length	slot	name	descriptor
    //   35	2	8	$i$a$-check--RealBufferedSource$commonIndexOfElement$1$iv	I
    //   60	5	10	$i$f$getBuffer	I
    //   57	8	9	this_$iv$iv	Lokio/RealBufferedSource;
    //   94	5	14	$i$f$getBuffer	I
    //   91	8	13	this_$iv$iv	Lokio/RealBufferedSource;
    //   116	5	14	$i$f$getBuffer	I
    //   113	8	13	this_$iv$iv	Lokio/RealBufferedSource;
    //   73	78	11	result$iv	J
    //   104	47	15	lastBufferSize$iv	J
    //   13	141	5	$i$f$commonIndexOfElement	I
    //   16	138	6	fromIndex$iv	J
    //   10	144	4	$this$commonIndexOfElement$iv	Lokio/RealBufferedSource;
    //   0	155	0	this	Lokio/RealBufferedSource;
    //   0	155	1	targetBytes	Lokio/ByteString;
    //   0	155	2	fromIndex	J
  }
  
  public boolean rangeEquals(long offset, @NotNull ByteString bytes) {
    Intrinsics.checkNotNullParameter(bytes, "bytes");
    return rangeEquals(offset, bytes, 0, bytes.size());
  }
  
  public boolean rangeEquals(long offset, @NotNull ByteString bytes, int bytesOffset, int byteCount) {
    // Byte code:
    //   0: aload_3
    //   1: ldc_w 'bytes'
    //   4: invokestatic checkNotNullParameter : (Ljava/lang/Object;Ljava/lang/String;)V
    //   7: aload_0
    //   8: astore #6
    //   10: iconst_0
    //   11: istore #7
    //   13: aload #6
    //   15: getfield closed : Z
    //   18: ifne -> 25
    //   21: iconst_1
    //   22: goto -> 26
    //   25: iconst_0
    //   26: ifne -> 49
    //   29: iconst_0
    //   30: istore #8
    //   32: ldc 'closed'
    //   34: astore #8
    //   36: new java/lang/IllegalStateException
    //   39: dup
    //   40: aload #8
    //   42: invokevirtual toString : ()Ljava/lang/String;
    //   45: invokespecial <init> : (Ljava/lang/String;)V
    //   48: athrow
    //   49: lload_1
    //   50: lconst_0
    //   51: lcmp
    //   52: iflt -> 77
    //   55: iload #4
    //   57: iflt -> 77
    //   60: iload #5
    //   62: iflt -> 77
    //   65: aload_3
    //   66: invokevirtual size : ()I
    //   69: iload #4
    //   71: isub
    //   72: iload #5
    //   74: if_icmpge -> 81
    //   77: iconst_0
    //   78: goto -> 154
    //   81: iconst_0
    //   82: istore #9
    //   84: iload #9
    //   86: iload #5
    //   88: if_icmpge -> 153
    //   91: lload_1
    //   92: iload #9
    //   94: i2l
    //   95: ladd
    //   96: lstore #10
    //   98: aload #6
    //   100: lload #10
    //   102: lconst_1
    //   103: ladd
    //   104: invokevirtual request : (J)Z
    //   107: ifne -> 114
    //   110: iconst_0
    //   111: goto -> 154
    //   114: aload #6
    //   116: astore #12
    //   118: iconst_0
    //   119: istore #13
    //   121: aload #12
    //   123: getfield bufferField : Lokio/Buffer;
    //   126: lload #10
    //   128: invokevirtual getByte : (J)B
    //   131: aload_3
    //   132: iload #4
    //   134: iload #9
    //   136: iadd
    //   137: invokevirtual getByte : (I)B
    //   140: if_icmpeq -> 147
    //   143: iconst_0
    //   144: goto -> 154
    //   147: iinc #9, 1
    //   150: goto -> 84
    //   153: iconst_1
    //   154: ireturn
    // Line number table:
    //   Java source line number -> byte code offset
    //   #141	-> 7
    //   #462	-> 13
    //   #463	-> 29
    //   #462	-> 32
    //   #462	-> 34
    //   #464	-> 49
    //   #465	-> 55
    //   #466	-> 60
    //   #467	-> 65
    //   #469	-> 77
    //   #471	-> 81
    //   #472	-> 91
    //   #473	-> 98
    //   #474	-> 114
    //   #475	-> 121
    //   #474	-> 126
    //   #471	-> 147
    //   #476	-> 153
    //   #141	-> 154
    // Local variable table:
    //   start	length	slot	name	descriptor
    //   32	2	8	$i$a$-check--RealBufferedSource$commonRangeEquals$1$iv	I
    //   121	5	13	$i$f$getBuffer	I
    //   118	8	12	this_$iv$iv	Lokio/RealBufferedSource;
    //   98	49	10	bufferOffset$iv	J
    //   84	69	9	i$iv	I
    //   13	141	7	$i$f$commonRangeEquals	I
    //   10	144	6	$this$commonRangeEquals$iv	Lokio/RealBufferedSource;
    //   0	155	0	this	Lokio/RealBufferedSource;
    //   0	155	1	offset	J
    //   0	155	3	bytes	Lokio/ByteString;
    //   0	155	4	bytesOffset	I
    //   0	155	5	byteCount	I
  }
  
  @NotNull
  public BufferedSource peek() {
    RealBufferedSource $this$commonPeek$iv = this;
    int $i$f$commonPeek = 0;
    return Okio.buffer(new PeekSource($this$commonPeek$iv));
  }
  
  @NotNull
  public InputStream inputStream() {
    return new RealBufferedSource$inputStream$1();
  }
  
  @Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\000)\n\000\n\002\030\002\n\002\020\b\n\002\b\002\n\002\020\002\n\002\b\003\n\002\020\022\n\002\b\004\n\002\020\016\n\002\b\003*\001\000\b\n\030\0002\0020\001J\017\020\003\032\0020\002H\026¢\006\004\b\003\020\004J\017\020\006\032\0020\005H\026¢\006\004\b\006\020\007J\017\020\b\032\0020\002H\026¢\006\004\b\b\020\004J'\020\b\032\0020\0022\006\020\n\032\0020\t2\006\020\013\032\0020\0022\006\020\f\032\0020\002H\026¢\006\004\b\b\020\rJ\017\020\017\032\0020\016H\026¢\006\004\b\017\020\020¨\006\021"}, d2 = {"okio/RealBufferedSource.inputStream.1", "Ljava/io/InputStream;", "", "available", "()I", "", "close", "()V", "read", "", "data", "offset", "byteCount", "([BII)I", "", "toString", "()Ljava/lang/String;", "okio"})
  @SourceDebugExtension({"SMAP\nRealBufferedSource.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RealBufferedSource.kt\nokio/RealBufferedSource$inputStream$1\n+ 2 RealBufferedSource.kt\nokio/RealBufferedSource\n+ 3 Util.kt\nokio/-SegmentedByteString\n*L\n1#1,185:1\n62#2:186\n62#2:187\n62#2:188\n62#2:190\n62#2:191\n62#2:192\n62#2:193\n74#3:189\n86#3:194\n*S KotlinDebug\n*F\n+ 1 RealBufferedSource.kt\nokio/RealBufferedSource$inputStream$1\n*L\n149#1:186\n150#1:187\n153#1:188\n160#1:190\n161#1:191\n165#1:192\n170#1:193\n153#1:189\n170#1:194\n*E\n"})
  public static final class RealBufferedSource$inputStream$1 extends InputStream {
    public int read() {
      // Byte code:
      //   0: aload_0
      //   1: getfield this$0 : Lokio/RealBufferedSource;
      //   4: getfield closed : Z
      //   7: ifeq -> 20
      //   10: new java/io/IOException
      //   13: dup
      //   14: ldc 'closed'
      //   16: invokespecial <init> : (Ljava/lang/String;)V
      //   19: athrow
      //   20: aload_0
      //   21: getfield this$0 : Lokio/RealBufferedSource;
      //   24: astore_1
      //   25: iconst_0
      //   26: istore_2
      //   27: aload_1
      //   28: getfield bufferField : Lokio/Buffer;
      //   31: invokevirtual size : ()J
      //   34: lconst_0
      //   35: lcmp
      //   36: ifne -> 77
      //   39: aload_0
      //   40: getfield this$0 : Lokio/RealBufferedSource;
      //   43: getfield source : Lokio/Source;
      //   46: aload_0
      //   47: getfield this$0 : Lokio/RealBufferedSource;
      //   50: astore_3
      //   51: iconst_0
      //   52: istore #4
      //   54: aload_3
      //   55: getfield bufferField : Lokio/Buffer;
      //   58: ldc2_w 8192
      //   61: invokeinterface read : (Lokio/Buffer;J)J
      //   66: lstore_1
      //   67: lload_1
      //   68: ldc2_w -1
      //   71: lcmp
      //   72: ifne -> 77
      //   75: iconst_m1
      //   76: ireturn
      //   77: aload_0
      //   78: getfield this$0 : Lokio/RealBufferedSource;
      //   81: astore_1
      //   82: iconst_0
      //   83: istore_2
      //   84: aload_1
      //   85: getfield bufferField : Lokio/Buffer;
      //   88: invokevirtual readByte : ()B
      //   91: istore_1
      //   92: sipush #255
      //   95: istore_2
      //   96: iconst_0
      //   97: istore_3
      //   98: iload_1
      //   99: iload_2
      //   100: iand
      //   101: ireturn
      // Line number table:
      //   Java source line number -> byte code offset
      //   #148	-> 0
      //   #149	-> 20
      //   #186	-> 27
      //   #149	-> 31
      //   #150	-> 39
      //   #187	-> 54
      //   #150	-> 58
      //   #151	-> 67
      //   #153	-> 77
      //   #188	-> 84
      //   #153	-> 88
      //   #189	-> 98
      //   #153	-> 101
      // Local variable table:
      //   start	length	slot	name	descriptor
      //   27	4	2	$i$f$getBuffer	I
      //   25	6	1	this_$iv	Lokio/RealBufferedSource;
      //   54	4	4	$i$f$getBuffer	I
      //   51	7	3	this_$iv	Lokio/RealBufferedSource;
      //   67	10	1	count	J
      //   84	4	2	$i$f$getBuffer	I
      //   82	6	1	this_$iv	Lokio/RealBufferedSource;
      //   98	3	3	$i$f$and	I
      //   96	5	1	$this$and$iv	B
      //   96	5	2	other$iv	I
      //   0	102	0	this	Lokio/RealBufferedSource$inputStream$1;
    }
    
    public int read(@NotNull byte[] data, int offset, int byteCount) {
      // Byte code:
      //   0: aload_1
      //   1: ldc 'data'
      //   3: invokestatic checkNotNullParameter : (Ljava/lang/Object;Ljava/lang/String;)V
      //   6: aload_0
      //   7: getfield this$0 : Lokio/RealBufferedSource;
      //   10: getfield closed : Z
      //   13: ifeq -> 26
      //   16: new java/io/IOException
      //   19: dup
      //   20: ldc 'closed'
      //   22: invokespecial <init> : (Ljava/lang/String;)V
      //   25: athrow
      //   26: aload_1
      //   27: arraylength
      //   28: i2l
      //   29: iload_2
      //   30: i2l
      //   31: iload_3
      //   32: i2l
      //   33: invokestatic checkOffsetAndCount : (JJJ)V
      //   36: aload_0
      //   37: getfield this$0 : Lokio/RealBufferedSource;
      //   40: astore #4
      //   42: iconst_0
      //   43: istore #5
      //   45: aload #4
      //   47: getfield bufferField : Lokio/Buffer;
      //   50: invokevirtual size : ()J
      //   53: lconst_0
      //   54: lcmp
      //   55: ifne -> 100
      //   58: aload_0
      //   59: getfield this$0 : Lokio/RealBufferedSource;
      //   62: getfield source : Lokio/Source;
      //   65: aload_0
      //   66: getfield this$0 : Lokio/RealBufferedSource;
      //   69: astore #6
      //   71: iconst_0
      //   72: istore #7
      //   74: aload #6
      //   76: getfield bufferField : Lokio/Buffer;
      //   79: ldc2_w 8192
      //   82: invokeinterface read : (Lokio/Buffer;J)J
      //   87: lstore #4
      //   89: lload #4
      //   91: ldc2_w -1
      //   94: lcmp
      //   95: ifne -> 100
      //   98: iconst_m1
      //   99: ireturn
      //   100: aload_0
      //   101: getfield this$0 : Lokio/RealBufferedSource;
      //   104: astore #4
      //   106: iconst_0
      //   107: istore #5
      //   109: aload #4
      //   111: getfield bufferField : Lokio/Buffer;
      //   114: aload_1
      //   115: iload_2
      //   116: iload_3
      //   117: invokevirtual read : ([BII)I
      //   120: ireturn
      // Line number table:
      //   Java source line number -> byte code offset
      //   #157	-> 6
      //   #158	-> 26
      //   #160	-> 36
      //   #190	-> 45
      //   #160	-> 50
      //   #161	-> 58
      //   #191	-> 74
      //   #161	-> 79
      //   #162	-> 89
      //   #165	-> 100
      //   #192	-> 109
      //   #165	-> 114
      // Local variable table:
      //   start	length	slot	name	descriptor
      //   45	5	5	$i$f$getBuffer	I
      //   42	8	4	this_$iv	Lokio/RealBufferedSource;
      //   74	5	7	$i$f$getBuffer	I
      //   71	8	6	this_$iv	Lokio/RealBufferedSource;
      //   89	11	4	count	J
      //   109	5	5	$i$f$getBuffer	I
      //   106	8	4	this_$iv	Lokio/RealBufferedSource;
      //   0	121	0	this	Lokio/RealBufferedSource$inputStream$1;
      //   0	121	1	data	[B
      //   0	121	2	offset	I
      //   0	121	3	byteCount	I
    }
    
    public int available() {
      if (RealBufferedSource.this.closed)
        throw new IOException("closed"); 
      RealBufferedSource this_$iv = RealBufferedSource.this;
      int $i$f$getBuffer = 0;
      long l = this_$iv.bufferField.size();
      int b$iv = Integer.MAX_VALUE, $i$f$minOf = 0;
      return (int)Math.min(l, b$iv);
    }
    
    public void close() {
      RealBufferedSource.this.close();
    }
    
    @NotNull
    public String toString() {
      return RealBufferedSource.this + ".inputStream()";
    }
  }
  
  public boolean isOpen() {
    return !this.closed;
  }
  
  public void close() {
    RealBufferedSource $this$commonClose$iv = this;
    int $i$f$commonClose = 0;
    if (!$this$commonClose$iv.closed) {
      $this$commonClose$iv.closed = true;
      $this$commonClose$iv.source.close();
      RealBufferedSource this_$iv$iv = $this$commonClose$iv;
      int $i$f$getBuffer = 0;
      this_$iv$iv.bufferField.clear();
    } 
  }
  
  @NotNull
  public Timeout timeout() {
    RealBufferedSource $this$commonTimeout$iv = this;
    int $i$f$commonTimeout = 0;
    return $this$commonTimeout$iv.source.timeout();
  }
  
  @NotNull
  public String toString() {
    RealBufferedSource $this$commonToString$iv = this;
    int $i$f$commonToString = 0;
    return "buffer(" + $this$commonToString$iv.source + ')';
  }
}
