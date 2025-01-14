package okio;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okio.internal.-SegmentedByteString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\000p\n\002\030\002\n\002\030\002\n\002\020\021\n\002\020\022\n\000\n\002\020\025\n\002\b\003\n\002\030\002\n\002\b\002\n\002\020\016\n\002\b\003\n\002\020\b\n\002\b\004\n\002\020\002\n\002\b\006\n\002\020\000\n\000\n\002\020\013\n\002\b\022\n\002\020\005\n\002\b\b\n\002\030\002\n\002\b\r\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\t\b\000\030\0002\0020\001B\037\b\000\022\f\020\004\032\b\022\004\022\0020\0030\002\022\006\020\006\032\0020\005¢\006\004\b\007\020\bJ\017\020\n\032\0020\tH\026¢\006\004\b\n\020\013J\017\020\r\032\0020\fH\026¢\006\004\b\r\020\016J\017\020\017\032\0020\fH\026¢\006\004\b\017\020\016J/\020\026\032\0020\0252\006\020\021\032\0020\0202\006\020\022\032\0020\0032\006\020\023\032\0020\0202\006\020\024\032\0020\020H\026¢\006\004\b\026\020\027J\027\020\033\032\0020\0012\006\020\030\032\0020\fH\020¢\006\004\b\031\020\032J\032\020\037\032\0020\0362\b\020\035\032\004\030\0010\034H\002¢\006\004\b\037\020 J\017\020#\032\0020\020H\020¢\006\004\b!\020\"J\017\020$\032\0020\020H\026¢\006\004\b$\020\"J\017\020%\032\0020\fH\026¢\006\004\b%\020\016J\037\020)\032\0020\0012\006\020\030\032\0020\f2\006\020&\032\0020\001H\020¢\006\004\b'\020(J\037\020+\032\0020\0202\006\020\035\032\0020\0032\006\020*\032\0020\020H\026¢\006\004\b+\020,J\017\020/\032\0020\003H\020¢\006\004\b-\020.J\027\0204\032\002012\006\0200\032\0020\020H\020¢\006\004\b2\0203J\037\0205\032\0020\0202\006\020\035\032\0020\0032\006\020*\032\0020\020H\026¢\006\004\b5\020,J/\0207\032\0020\0362\006\020\021\032\0020\0202\006\020\035\032\0020\0032\006\0206\032\0020\0202\006\020\024\032\0020\020H\026¢\006\004\b7\0208J/\0207\032\0020\0362\006\020\021\032\0020\0202\006\020\035\032\0020\0012\006\0206\032\0020\0202\006\020\024\032\0020\020H\026¢\006\004\b7\0209J\027\020<\032\0020\f2\006\020;\032\0020:H\026¢\006\004\b<\020=J\037\020@\032\0020\0012\006\020>\032\0020\0202\006\020?\032\0020\020H\026¢\006\004\b@\020AJ\017\020B\032\0020\001H\026¢\006\004\bB\020CJ\017\020D\032\0020\001H\026¢\006\004\bD\020CJ\017\020E\032\0020\003H\026¢\006\004\bE\020.J\017\020F\032\0020\001H\002¢\006\004\bF\020CJ\017\020G\032\0020\fH\026¢\006\004\bG\020\016J\027\020J\032\0020\0252\006\020I\032\0020HH\026¢\006\004\bJ\020KJ'\020J\032\0020\0252\006\020M\032\0020L2\006\020\021\032\0020\0202\006\020\024\032\0020\020H\020¢\006\004\bN\020OJ\017\020Q\032\0020PH\002¢\006\004\bQ\020RR\032\020\006\032\0020\0058\000X\004¢\006\f\n\004\b\006\020S\032\004\bT\020UR \020\004\032\b\022\004\022\0020\0030\0028\000X\004¢\006\f\n\004\b\004\020V\032\004\bW\020X¨\006Y"}, d2 = {"Lokio/SegmentedByteString;", "Lokio/ByteString;", "", "", "segments", "", "directory", "<init>", "([[B[I)V", "Ljava/nio/ByteBuffer;", "asByteBuffer", "()Ljava/nio/ByteBuffer;", "", "base64", "()Ljava/lang/String;", "base64Url", "", "offset", "target", "targetOffset", "byteCount", "", "copyInto", "(I[BII)V", "algorithm", "digest$okio", "(Ljava/lang/String;)Lokio/ByteString;", "digest", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "getSize$okio", "()I", "getSize", "hashCode", "hex", "key", "hmac$okio", "(Ljava/lang/String;Lokio/ByteString;)Lokio/ByteString;", "hmac", "fromIndex", "indexOf", "([BI)I", "internalArray$okio", "()[B", "internalArray", "pos", "", "internalGet$okio", "(I)B", "internalGet", "lastIndexOf", "otherOffset", "rangeEquals", "(I[BII)Z", "(ILokio/ByteString;II)Z", "Ljava/nio/charset/Charset;", "charset", "string", "(Ljava/nio/charset/Charset;)Ljava/lang/String;", "beginIndex", "endIndex", "substring", "(II)Lokio/ByteString;", "toAsciiLowercase", "()Lokio/ByteString;", "toAsciiUppercase", "toByteArray", "toByteString", "toString", "Ljava/io/OutputStream;", "out", "write", "(Ljava/io/OutputStream;)V", "Lokio/Buffer;", "buffer", "write$okio", "(Lokio/Buffer;II)V", "Ljava/lang/Object;", "writeReplace", "()Ljava/lang/Object;", "[I", "getDirectory$okio", "()[I", "[[B", "getSegments$okio", "()[[B", "okio"})
@SourceDebugExtension({"SMAP\nSegmentedByteString.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SegmentedByteString.kt\nokio/SegmentedByteString\n+ 2 SegmentedByteString.kt\nokio/internal/-SegmentedByteString\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,140:1\n63#2,12:141\n63#2,12:153\n104#2,2:165\n106#2,26:168\n135#2,5:194\n142#2:199\n145#2,3:200\n63#2,8:203\n148#2,8:211\n71#2,4:219\n156#2:223\n63#2,12:224\n160#2:236\n85#2,10:237\n161#2,9:247\n95#2,4:256\n170#2,2:260\n179#2,4:262\n85#2,10:266\n183#2,3:276\n95#2,4:279\n186#2:283\n195#2,8:284\n85#2,10:292\n203#2,3:302\n95#2,4:305\n206#2:309\n215#2,5:310\n85#2,10:315\n220#2,3:325\n95#2,4:328\n223#2:332\n226#2,4:333\n234#2,6:337\n63#2,8:343\n240#2,7:351\n71#2,4:358\n247#2,2:362\n1#3:167\n*S KotlinDebug\n*F\n+ 1 SegmentedByteString.kt\nokio/SegmentedByteString\n*L\n54#1:141,12\n66#1:153,12\n78#1:165,2\n78#1:168,26\n80#1:194,5\n82#1:199\n84#1:200,3\n84#1:203,8\n84#1:211,8\n84#1:219,4\n84#1:223\n90#1:224,12\n96#1:236\n96#1:237,10\n96#1:247,9\n96#1:256,4\n96#1:260,2\n103#1:262,4\n103#1:266,10\n103#1:276,3\n103#1:279,4\n103#1:283\n110#1:284,8\n110#1:292,10\n110#1:302,3\n110#1:305,4\n110#1:309\n117#1:310,5\n117#1:315,10\n117#1:325,3\n117#1:328,4\n117#1:332\n131#1:333,4\n133#1:337,6\n133#1:343,8\n133#1:351,7\n133#1:358,4\n133#1:362,2\n78#1:167\n*E\n"})
public final class SegmentedByteString extends ByteString {
  @NotNull
  private final transient byte[][] segments;
  
  @NotNull
  private final transient int[] directory;
  
  @NotNull
  public final byte[][] getSegments$okio() {
    return this.segments;
  }
  
  @NotNull
  public final int[] getDirectory$okio() {
    return this.directory;
  }
  
  public SegmentedByteString(@NotNull byte[][] segments, @NotNull int[] directory) {
    super(ByteString.EMPTY.getData$okio());
    this.segments = segments;
    this.directory = directory;
  }
  
  @NotNull
  public String string(@NotNull Charset charset) {
    Intrinsics.checkNotNullParameter(charset, "charset");
    return toByteString().string(charset);
  }
  
  @NotNull
  public String base64() {
    return toByteString().base64();
  }
  
  @NotNull
  public String hex() {
    return toByteString().hex();
  }
  
  @NotNull
  public ByteString toAsciiLowercase() {
    return toByteString().toAsciiLowercase();
  }
  
  @NotNull
  public ByteString toAsciiUppercase() {
    return toByteString().toAsciiUppercase();
  }
  
  @NotNull
  public ByteString digest$okio(@NotNull String algorithm) {
    Intrinsics.checkNotNullParameter(algorithm, "algorithm");
    MessageDigest $this$digest_u24lambda_u241 = MessageDigest.getInstance(algorithm);
    int $i$a$-run-SegmentedByteString$digest$digestBytes$1 = 0;
    SegmentedByteString $this$forEachSegment$iv = this;
    int $i$f$forEachSegment = 0;
    int segmentCount$iv = ((Object[])$this$forEachSegment$iv.getSegments$okio()).length;
    int s$iv = 0;
    int pos$iv = 0;
    while (s$iv < segmentCount$iv) {
      int segmentPos$iv = $this$forEachSegment$iv.getDirectory$okio()[segmentCount$iv + s$iv];
      int nextSegmentOffset$iv = $this$forEachSegment$iv.getDirectory$okio()[s$iv];
      int i = nextSegmentOffset$iv - pos$iv, j = segmentPos$iv;
      byte[] data = $this$forEachSegment$iv.getSegments$okio()[s$iv];
      int $i$a$-forEachSegment-SegmentedByteString$digest$digestBytes$1$1 = 0;
      $this$digest_u24lambda_u241.update(data, j, i);
      pos$iv = nextSegmentOffset$iv;
      s$iv++;
    } 
    byte[] digestBytes = $this$digest_u24lambda_u241.digest();
    Intrinsics.checkNotNull(digestBytes);
    return new ByteString(digestBytes);
  }
  
  @NotNull
  public ByteString hmac$okio(@NotNull String algorithm, @NotNull ByteString key) {
    Intrinsics.checkNotNullParameter(algorithm, "algorithm");
    Intrinsics.checkNotNullParameter(key, "key");
    try {
      Mac mac = Mac.getInstance(algorithm);
      mac.init(new SecretKeySpec(key.toByteArray(), algorithm));
      SegmentedByteString $this$forEachSegment$iv = this;
      int $i$f$forEachSegment = 0;
      int segmentCount$iv = ((Object[])$this$forEachSegment$iv.getSegments$okio()).length;
      int s$iv = 0;
      int pos$iv = 0;
      while (s$iv < segmentCount$iv) {
        int segmentPos$iv = $this$forEachSegment$iv.getDirectory$okio()[segmentCount$iv + s$iv];
        int nextSegmentOffset$iv = $this$forEachSegment$iv.getDirectory$okio()[s$iv];
        int i = nextSegmentOffset$iv - pos$iv, j = segmentPos$iv;
        byte[] data = $this$forEachSegment$iv.getSegments$okio()[s$iv];
        int $i$a$-forEachSegment-SegmentedByteString$hmac$1 = 0;
        mac.update(data, j, i);
        pos$iv = nextSegmentOffset$iv;
        s$iv++;
      } 
      Intrinsics.checkNotNullExpressionValue(mac.doFinal(), "doFinal(...)");
      return new ByteString(mac.doFinal());
    } catch (InvalidKeyException e) {
      throw new IllegalArgumentException((Throwable)e);
    } 
  }
  
  @NotNull
  public String base64Url() {
    return toByteString().base64Url();
  }
  
  @NotNull
  public ByteString substring(int beginIndex, int endIndex) {
    SegmentedByteString $this$commonSubstring$iv = this;
    int $i$f$commonSubstring = 0;
    int endIndex$iv = -SegmentedByteString.resolveDefaultParameter($this$commonSubstring$iv, endIndex);
    if (!((beginIndex >= 0) ? 1 : 0)) {
      int $i$a$-require--SegmentedByteString$commonSubstring$1$iv = 0;
      String str = "beginIndex=" + beginIndex + " < 0";
      throw new IllegalArgumentException(str.toString());
    } 
    if (!((endIndex$iv <= $this$commonSubstring$iv.size()) ? 1 : 0)) {
      int $i$a$-require--SegmentedByteString$commonSubstring$2$iv = 0;
      String str = "endIndex=" + endIndex$iv + " > length(" + $this$commonSubstring$iv.size() + ')';
      throw new IllegalArgumentException(str.toString());
    } 
    int subLen$iv = endIndex$iv - beginIndex;
    if (!((subLen$iv >= 0) ? 1 : 0)) {
      int $i$a$-require--SegmentedByteString$commonSubstring$3$iv = 0;
      String str = "endIndex=" + endIndex$iv + " < beginIndex=" + beginIndex;
      throw new IllegalArgumentException(str.toString());
    } 
    int beginSegment$iv = -SegmentedByteString.segment($this$commonSubstring$iv, beginIndex);
    int endSegment$iv = -SegmentedByteString.segment($this$commonSubstring$iv, endIndex$iv - 1);
    Object[] arrayOfObject = (Object[])$this$commonSubstring$iv.getSegments$okio();
    int i = endSegment$iv + 1;
    byte[][] newSegments$iv = (byte[][])ArraysKt.copyOfRange(arrayOfObject, beginSegment$iv, i);
    int[] newDirectory$iv = new int[((Object[])newSegments$iv).length * 2];
    int index$iv = 0;
    int s$iv = beginSegment$iv;
    if (s$iv <= endSegment$iv)
      while (true) {
        newDirectory$iv[index$iv] = Math.min($this$commonSubstring$iv.getDirectory$okio()[s$iv] - beginIndex, subLen$iv);
        newDirectory$iv[index$iv++ + ((Object[])newSegments$iv).length] = $this$commonSubstring$iv.getDirectory$okio()[s$iv + ((Object[])$this$commonSubstring$iv.getSegments$okio()).length];
        if (s$iv != endSegment$iv) {
          s$iv++;
          continue;
        } 
        break;
      }  
    int segmentOffset$iv = (beginSegment$iv == 0) ? 0 : $this$commonSubstring$iv.getDirectory$okio()[beginSegment$iv - 1];
    int j = ((Object[])newSegments$iv).length;
    newDirectory$iv[j] = newDirectory$iv[j] + beginIndex - segmentOffset$iv;
    return (beginIndex == 0 && endIndex$iv == $this$commonSubstring$iv.size()) ? $this$commonSubstring$iv : ((beginIndex == endIndex$iv) ? ByteString.EMPTY : new SegmentedByteString(newSegments$iv, newDirectory$iv));
  }
  
  public byte internalGet$okio(int pos) {
    SegmentedByteString $this$commonInternalGet$iv = this;
    int $i$f$commonInternalGet = 0;
    -SegmentedByteString.checkOffsetAndCount($this$commonInternalGet$iv.getDirectory$okio()[((Object[])$this$commonInternalGet$iv.getSegments$okio()).length - 1], pos, 1L);
    int segment$iv = -SegmentedByteString.segment($this$commonInternalGet$iv, pos);
    int segmentOffset$iv = (segment$iv == 0) ? 0 : $this$commonInternalGet$iv.getDirectory$okio()[segment$iv - 1];
    int segmentPos$iv = $this$commonInternalGet$iv.getDirectory$okio()[segment$iv + ((Object[])$this$commonInternalGet$iv.getSegments$okio()).length];
    return $this$commonInternalGet$iv.getSegments$okio()[segment$iv][pos - segmentOffset$iv + segmentPos$iv];
  }
  
  public int getSize$okio() {
    SegmentedByteString $this$commonGetSize$iv = this;
    int $i$f$commonGetSize = 0;
    return $this$commonGetSize$iv.getDirectory$okio()[((Object[])$this$commonGetSize$iv.getSegments$okio()).length - 1];
  }
  
  @NotNull
  public byte[] toByteArray() {
    SegmentedByteString $this$commonToByteArray$iv = this;
    int $i$f$commonToByteArray = 0;
    byte[] result$iv = new byte[$this$commonToByteArray$iv.size()];
    int resultPos$iv = 0;
    SegmentedByteString $this$forEachSegment$iv$iv = $this$commonToByteArray$iv;
    int $i$f$forEachSegment = 0;
    int segmentCount$iv$iv = ((Object[])$this$forEachSegment$iv$iv.getSegments$okio()).length;
    int s$iv$iv = 0;
    int pos$iv$iv = 0;
    while (s$iv$iv < segmentCount$iv$iv) {
      int segmentPos$iv$iv = $this$forEachSegment$iv$iv.getDirectory$okio()[segmentCount$iv$iv + s$iv$iv];
      int nextSegmentOffset$iv$iv = $this$forEachSegment$iv$iv.getDirectory$okio()[s$iv$iv];
      int i = nextSegmentOffset$iv$iv - pos$iv$iv, j = segmentPos$iv$iv;
      byte[] data$iv = $this$forEachSegment$iv$iv.getSegments$okio()[s$iv$iv];
      int $i$a$-forEachSegment--SegmentedByteString$commonToByteArray$1$iv = 0;
      ArraysKt.copyInto(data$iv, 
          result$iv, 
          resultPos$iv, 
          j, 
          j + i);
      resultPos$iv += i;
      pos$iv$iv = nextSegmentOffset$iv$iv;
      s$iv$iv++;
    } 
    return result$iv;
  }
  
  @NotNull
  public ByteBuffer asByteBuffer() {
    Intrinsics.checkNotNullExpressionValue(ByteBuffer.wrap(toByteArray()).asReadOnlyBuffer(), "asReadOnlyBuffer(...)");
    return ByteBuffer.wrap(toByteArray()).asReadOnlyBuffer();
  }
  
  public void write(@NotNull OutputStream out) throws IOException {
    Intrinsics.checkNotNullParameter(out, "out");
    SegmentedByteString $this$forEachSegment$iv = this;
    int $i$f$forEachSegment = 0;
    int segmentCount$iv = ((Object[])$this$forEachSegment$iv.getSegments$okio()).length;
    int s$iv = 0;
    int pos$iv = 0;
    while (s$iv < segmentCount$iv) {
      int segmentPos$iv = $this$forEachSegment$iv.getDirectory$okio()[segmentCount$iv + s$iv];
      int nextSegmentOffset$iv = $this$forEachSegment$iv.getDirectory$okio()[s$iv];
      int i = nextSegmentOffset$iv - pos$iv, j = segmentPos$iv;
      byte[] data = $this$forEachSegment$iv.getSegments$okio()[s$iv];
      int $i$a$-forEachSegment-SegmentedByteString$write$1 = 0;
      out.write(data, j, i);
      pos$iv = nextSegmentOffset$iv;
      s$iv++;
    } 
  }
  
  public void write$okio(@NotNull Buffer buffer, int offset, int byteCount) {
    Intrinsics.checkNotNullParameter(buffer, "buffer");
    SegmentedByteString $this$commonWrite$iv = this;
    int $i$f$commonWrite = 0;
    SegmentedByteString segmentedByteString1 = $this$commonWrite$iv;
    int endIndex$iv$iv = offset + byteCount, $i$f$forEachSegment = 0;
    int s$iv$iv = -SegmentedByteString.segment(segmentedByteString1, offset);
    int pos$iv$iv = offset;
    while (pos$iv$iv < endIndex$iv$iv) {
      int segmentOffset$iv$iv = (s$iv$iv == 0) ? 0 : segmentedByteString1.getDirectory$okio()[s$iv$iv - 1];
      int segmentSize$iv$iv = segmentedByteString1.getDirectory$okio()[s$iv$iv] - segmentOffset$iv$iv;
      int segmentPos$iv$iv = segmentedByteString1.getDirectory$okio()[((Object[])segmentedByteString1.getSegments$okio()).length + s$iv$iv];
      int byteCount$iv$iv = Math.min(endIndex$iv$iv, segmentOffset$iv$iv + segmentSize$iv$iv) - pos$iv$iv;
      int offset$iv$iv = segmentPos$iv$iv + pos$iv$iv - segmentOffset$iv$iv;
      int i = byteCount$iv$iv, j = offset$iv$iv;
      byte[] data$iv = segmentedByteString1.getSegments$okio()[s$iv$iv];
      int $i$a$-forEachSegment--SegmentedByteString$commonWrite$1$iv = 0;
      Segment segment$iv = new Segment(data$iv, j, j + i, true, false);
      if (buffer.head == null) {
        segment$iv.prev = segment$iv;
        segment$iv.next = segment$iv.prev;
        buffer.head = segment$iv.next;
      } else {
        Intrinsics.checkNotNull(buffer.head);
        Intrinsics.checkNotNull(buffer.head.prev);
        buffer.head.prev.push(segment$iv);
      } 
      pos$iv$iv += byteCount$iv$iv;
      s$iv$iv++;
    } 
    buffer.setSize$okio(buffer.size() + byteCount);
  }
  
  public boolean rangeEquals(int offset, @NotNull ByteString other, int otherOffset, int byteCount) {
    // Byte code:
    //   0: aload_2
    //   1: ldc_w 'other'
    //   4: invokestatic checkNotNullParameter : (Ljava/lang/Object;Ljava/lang/String;)V
    //   7: aload_0
    //   8: astore #5
    //   10: iconst_0
    //   11: istore #6
    //   13: iload_1
    //   14: iflt -> 29
    //   17: iload_1
    //   18: aload #5
    //   20: invokevirtual size : ()I
    //   23: iload #4
    //   25: isub
    //   26: if_icmple -> 33
    //   29: iconst_0
    //   30: goto -> 213
    //   33: iconst_0
    //   34: istore #7
    //   36: iload_3
    //   37: istore #7
    //   39: aload #5
    //   41: astore #8
    //   43: iload_1
    //   44: iload #4
    //   46: iadd
    //   47: istore #9
    //   49: iconst_0
    //   50: istore #10
    //   52: aload #8
    //   54: iload_1
    //   55: invokestatic segment : (Lokio/SegmentedByteString;I)I
    //   58: istore #11
    //   60: iload_1
    //   61: istore #12
    //   63: iload #12
    //   65: iload #9
    //   67: if_icmpge -> 211
    //   70: iload #11
    //   72: ifne -> 79
    //   75: iconst_0
    //   76: goto -> 89
    //   79: aload #8
    //   81: invokevirtual getDirectory$okio : ()[I
    //   84: iload #11
    //   86: iconst_1
    //   87: isub
    //   88: iaload
    //   89: istore #13
    //   91: aload #8
    //   93: invokevirtual getDirectory$okio : ()[I
    //   96: iload #11
    //   98: iaload
    //   99: iload #13
    //   101: isub
    //   102: istore #14
    //   104: aload #8
    //   106: invokevirtual getDirectory$okio : ()[I
    //   109: aload #8
    //   111: invokevirtual getSegments$okio : ()[[B
    //   114: checkcast [Ljava/lang/Object;
    //   117: arraylength
    //   118: iload #11
    //   120: iadd
    //   121: iaload
    //   122: istore #15
    //   124: iload #9
    //   126: iload #13
    //   128: iload #14
    //   130: iadd
    //   131: invokestatic min : (II)I
    //   134: iload #12
    //   136: isub
    //   137: istore #16
    //   139: iload #15
    //   141: iload #12
    //   143: iload #13
    //   145: isub
    //   146: iadd
    //   147: istore #17
    //   149: aload #8
    //   151: invokevirtual getSegments$okio : ()[[B
    //   154: iload #11
    //   156: aaload
    //   157: iload #17
    //   159: iload #16
    //   161: istore #18
    //   163: istore #19
    //   165: astore #20
    //   167: iconst_0
    //   168: istore #21
    //   170: aload_2
    //   171: iload #7
    //   173: aload #20
    //   175: iload #19
    //   177: iload #18
    //   179: invokevirtual rangeEquals : (I[BII)Z
    //   182: ifne -> 189
    //   185: iconst_0
    //   186: goto -> 213
    //   189: iload #7
    //   191: iload #18
    //   193: iadd
    //   194: istore #7
    //   196: nop
    //   197: nop
    //   198: iload #12
    //   200: iload #16
    //   202: iadd
    //   203: istore #12
    //   205: iinc #11, 1
    //   208: goto -> 63
    //   211: nop
    //   212: iconst_1
    //   213: ireturn
    // Line number table:
    //   Java source line number -> byte code offset
    //   #103	-> 7
    //   #262	-> 13
    //   #264	-> 33
    //   #265	-> 39
    //   #266	-> 52
    //   #267	-> 60
    //   #268	-> 63
    //   #269	-> 70
    //   #270	-> 91
    //   #271	-> 104
    //   #273	-> 126
    //   #273	-> 134
    //   #274	-> 139
    //   #275	-> 149
    //   #276	-> 170
    //   #277	-> 189
    //   #278	-> 196
    //   #275	-> 197
    //   #279	-> 198
    //   #280	-> 205
    //   #282	-> 211
    //   #283	-> 212
    //   #103	-> 213
    // Local variable table:
    //   start	length	slot	name	descriptor
    //   170	27	21	$i$a$-forEachSegment--SegmentedByteString$commonRangeEquals$1$iv	I
    //   167	30	20	data$iv	[B
    //   167	30	19	offset$iv	I
    //   167	30	18	byteCount$iv	I
    //   91	117	13	segmentOffset$iv$iv	I
    //   104	104	14	segmentSize$iv$iv	I
    //   124	84	15	segmentPos$iv$iv	I
    //   139	69	16	byteCount$iv$iv	I
    //   149	59	17	offset$iv$iv	I
    //   52	160	10	$i$f$forEachSegment	I
    //   60	152	11	s$iv$iv	I
    //   63	149	12	pos$iv$iv	I
    //   49	163	8	$this$forEachSegment$iv$iv	Lokio/SegmentedByteString;
    //   49	163	9	endIndex$iv$iv	I
    //   13	200	6	$i$f$commonRangeEquals	I
    //   36	177	7	otherOffset$iv	I
    //   10	203	5	$this$commonRangeEquals$iv	Lokio/SegmentedByteString;
    //   0	214	0	this	Lokio/SegmentedByteString;
    //   0	214	1	offset	I
    //   0	214	2	other	Lokio/ByteString;
    //   0	214	3	otherOffset	I
    //   0	214	4	byteCount	I
  }
  
  public boolean rangeEquals(int offset, @NotNull byte[] other, int otherOffset, int byteCount) {
    // Byte code:
    //   0: aload_2
    //   1: ldc_w 'other'
    //   4: invokestatic checkNotNullParameter : (Ljava/lang/Object;Ljava/lang/String;)V
    //   7: aload_0
    //   8: astore #5
    //   10: iconst_0
    //   11: istore #6
    //   13: iload_1
    //   14: iflt -> 42
    //   17: iload_1
    //   18: aload #5
    //   20: invokevirtual size : ()I
    //   23: iload #4
    //   25: isub
    //   26: if_icmpgt -> 42
    //   29: iload_3
    //   30: iflt -> 42
    //   33: iload_3
    //   34: aload_2
    //   35: arraylength
    //   36: iload #4
    //   38: isub
    //   39: if_icmple -> 46
    //   42: iconst_0
    //   43: goto -> 226
    //   46: iconst_0
    //   47: istore #7
    //   49: iload_3
    //   50: istore #7
    //   52: aload #5
    //   54: astore #8
    //   56: iload_1
    //   57: iload #4
    //   59: iadd
    //   60: istore #9
    //   62: iconst_0
    //   63: istore #10
    //   65: aload #8
    //   67: iload_1
    //   68: invokestatic segment : (Lokio/SegmentedByteString;I)I
    //   71: istore #11
    //   73: iload_1
    //   74: istore #12
    //   76: iload #12
    //   78: iload #9
    //   80: if_icmpge -> 224
    //   83: iload #11
    //   85: ifne -> 92
    //   88: iconst_0
    //   89: goto -> 102
    //   92: aload #8
    //   94: invokevirtual getDirectory$okio : ()[I
    //   97: iload #11
    //   99: iconst_1
    //   100: isub
    //   101: iaload
    //   102: istore #13
    //   104: aload #8
    //   106: invokevirtual getDirectory$okio : ()[I
    //   109: iload #11
    //   111: iaload
    //   112: iload #13
    //   114: isub
    //   115: istore #14
    //   117: aload #8
    //   119: invokevirtual getDirectory$okio : ()[I
    //   122: aload #8
    //   124: invokevirtual getSegments$okio : ()[[B
    //   127: checkcast [Ljava/lang/Object;
    //   130: arraylength
    //   131: iload #11
    //   133: iadd
    //   134: iaload
    //   135: istore #15
    //   137: iload #9
    //   139: iload #13
    //   141: iload #14
    //   143: iadd
    //   144: invokestatic min : (II)I
    //   147: iload #12
    //   149: isub
    //   150: istore #16
    //   152: iload #15
    //   154: iload #12
    //   156: iload #13
    //   158: isub
    //   159: iadd
    //   160: istore #17
    //   162: aload #8
    //   164: invokevirtual getSegments$okio : ()[[B
    //   167: iload #11
    //   169: aaload
    //   170: iload #17
    //   172: iload #16
    //   174: istore #18
    //   176: istore #19
    //   178: astore #20
    //   180: iconst_0
    //   181: istore #21
    //   183: aload #20
    //   185: iload #19
    //   187: aload_2
    //   188: iload #7
    //   190: iload #18
    //   192: invokestatic arrayRangeEquals : ([BI[BII)Z
    //   195: ifne -> 202
    //   198: iconst_0
    //   199: goto -> 226
    //   202: iload #7
    //   204: iload #18
    //   206: iadd
    //   207: istore #7
    //   209: nop
    //   210: nop
    //   211: iload #12
    //   213: iload #16
    //   215: iadd
    //   216: istore #12
    //   218: iinc #11, 1
    //   221: goto -> 76
    //   224: nop
    //   225: iconst_1
    //   226: ireturn
    // Line number table:
    //   Java source line number -> byte code offset
    //   #110	-> 7
    //   #284	-> 13
    //   #285	-> 29
    //   #287	-> 42
    //   #290	-> 46
    //   #291	-> 52
    //   #292	-> 65
    //   #293	-> 73
    //   #294	-> 76
    //   #295	-> 83
    //   #296	-> 104
    //   #297	-> 117
    //   #299	-> 139
    //   #299	-> 147
    //   #300	-> 152
    //   #301	-> 162
    //   #302	-> 183
    //   #303	-> 202
    //   #304	-> 209
    //   #301	-> 210
    //   #305	-> 211
    //   #306	-> 218
    //   #308	-> 224
    //   #309	-> 225
    //   #110	-> 226
    // Local variable table:
    //   start	length	slot	name	descriptor
    //   183	27	21	$i$a$-forEachSegment--SegmentedByteString$commonRangeEquals$2$iv	I
    //   180	30	20	data$iv	[B
    //   180	30	19	offset$iv	I
    //   180	30	18	byteCount$iv	I
    //   104	117	13	segmentOffset$iv$iv	I
    //   117	104	14	segmentSize$iv$iv	I
    //   137	84	15	segmentPos$iv$iv	I
    //   152	69	16	byteCount$iv$iv	I
    //   162	59	17	offset$iv$iv	I
    //   65	160	10	$i$f$forEachSegment	I
    //   73	152	11	s$iv$iv	I
    //   76	149	12	pos$iv$iv	I
    //   62	163	8	$this$forEachSegment$iv$iv	Lokio/SegmentedByteString;
    //   62	163	9	endIndex$iv$iv	I
    //   13	213	6	$i$f$commonRangeEquals	I
    //   49	177	7	otherOffset$iv	I
    //   10	216	5	$this$commonRangeEquals$iv	Lokio/SegmentedByteString;
    //   0	227	0	this	Lokio/SegmentedByteString;
    //   0	227	1	offset	I
    //   0	227	2	other	[B
    //   0	227	3	otherOffset	I
    //   0	227	4	byteCount	I
  }
  
  public void copyInto(int offset, @NotNull byte[] target, int targetOffset, int byteCount) {
    Intrinsics.checkNotNullParameter(target, "target");
    SegmentedByteString $this$commonCopyInto$iv = this;
    int $i$f$commonCopyInto = 0;
    -SegmentedByteString.checkOffsetAndCount($this$commonCopyInto$iv.size(), offset, byteCount);
    -SegmentedByteString.checkOffsetAndCount(target.length, targetOffset, byteCount);
    int targetOffset$iv = 0;
    targetOffset$iv = targetOffset;
    SegmentedByteString segmentedByteString1 = $this$commonCopyInto$iv;
    int endIndex$iv$iv = offset + byteCount, $i$f$forEachSegment = 0;
    int s$iv$iv = -SegmentedByteString.segment(segmentedByteString1, offset);
    int pos$iv$iv = offset;
    while (pos$iv$iv < endIndex$iv$iv) {
      int segmentOffset$iv$iv = (s$iv$iv == 0) ? 0 : segmentedByteString1.getDirectory$okio()[s$iv$iv - 1];
      int segmentSize$iv$iv = segmentedByteString1.getDirectory$okio()[s$iv$iv] - segmentOffset$iv$iv;
      int segmentPos$iv$iv = segmentedByteString1.getDirectory$okio()[((Object[])segmentedByteString1.getSegments$okio()).length + s$iv$iv];
      int byteCount$iv$iv = Math.min(endIndex$iv$iv, segmentOffset$iv$iv + segmentSize$iv$iv) - pos$iv$iv;
      int offset$iv$iv = segmentPos$iv$iv + pos$iv$iv - segmentOffset$iv$iv;
      int i = byteCount$iv$iv, j = offset$iv$iv;
      byte[] data$iv = segmentedByteString1.getSegments$okio()[s$iv$iv];
      int $i$a$-forEachSegment--SegmentedByteString$commonCopyInto$1$iv = 0;
      ArraysKt.copyInto(data$iv, target, targetOffset$iv, j, j + i);
      targetOffset$iv += i;
      pos$iv$iv += byteCount$iv$iv;
      s$iv$iv++;
    } 
  }
  
  public int indexOf(@NotNull byte[] other, int fromIndex) {
    Intrinsics.checkNotNullParameter(other, "other");
    return toByteString().indexOf(other, fromIndex);
  }
  
  public int lastIndexOf(@NotNull byte[] other, int fromIndex) {
    Intrinsics.checkNotNullParameter(other, "other");
    return toByteString().lastIndexOf(other, fromIndex);
  }
  
  private final ByteString toByteString() {
    return new ByteString(toByteArray());
  }
  
  @NotNull
  public byte[] internalArray$okio() {
    return toByteArray();
  }
  
  public boolean equals(@Nullable Object other) {
    SegmentedByteString $this$commonEquals$iv = this;
    int $i$f$commonEquals = 0;
    return (other == $this$commonEquals$iv) ? true : (
      (other instanceof ByteString) ? ((((ByteString)other).size() == $this$commonEquals$iv.size() && $this$commonEquals$iv.rangeEquals(0, (ByteString)other, 0, $this$commonEquals$iv.size()))) : false);
  }
  
  public int hashCode() {
    SegmentedByteString $this$commonHashCode$iv = this;
    int $i$f$commonHashCode = 0;
    int result$iv = 0;
    result$iv = $this$commonHashCode$iv.getHashCode$okio();
    result$iv = 1;
    SegmentedByteString $this$forEachSegment$iv$iv = $this$commonHashCode$iv;
    int $i$f$forEachSegment = 0;
    int segmentCount$iv$iv = ((Object[])$this$forEachSegment$iv$iv.getSegments$okio()).length;
    int s$iv$iv = 0;
    int pos$iv$iv = 0;
    while (s$iv$iv < segmentCount$iv$iv) {
      int segmentPos$iv$iv = $this$forEachSegment$iv$iv.getDirectory$okio()[segmentCount$iv$iv + s$iv$iv];
      int nextSegmentOffset$iv$iv = $this$forEachSegment$iv$iv.getDirectory$okio()[s$iv$iv];
      int i = nextSegmentOffset$iv$iv - pos$iv$iv, j = segmentPos$iv$iv;
      byte[] data$iv = $this$forEachSegment$iv$iv.getSegments$okio()[s$iv$iv];
      int $i$a$-forEachSegment--SegmentedByteString$commonHashCode$1$iv = 0;
      int i$iv = j;
      int limit$iv = j + i;
      while (i$iv < limit$iv) {
        result$iv = 31 * result$iv + data$iv[i$iv];
        i$iv++;
      } 
      pos$iv$iv = nextSegmentOffset$iv$iv;
      s$iv$iv++;
    } 
    $this$commonHashCode$iv.setHashCode$okio(result$iv);
    return (result$iv != 0) ? result$iv : result$iv;
  }
  
  @NotNull
  public String toString() {
    return toByteString().toString();
  }
  
  private final Object writeReplace() {
    Intrinsics.checkNotNull(toByteString(), "null cannot be cast to non-null type java.lang.Object");
    return toByteString();
  }
}
