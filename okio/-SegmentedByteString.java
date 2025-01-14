package okio;

import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import okio.internal.-ByteString;
import org.jetbrains.annotations.NotNull;

@Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\000N\n\002\020\022\n\000\n\002\020\b\n\002\b\004\n\002\020\013\n\002\b\002\n\002\020\t\n\002\b\002\n\002\020\002\n\002\b\005\n\002\030\002\n\002\b\003\n\002\020\005\n\002\b\t\n\002\030\002\n\002\b\005\n\002\020\n\n\002\b\004\n\002\020\016\n\002\b\021\0327\020\b\032\0020\0072\006\020\001\032\0020\0002\006\020\003\032\0020\0022\006\020\004\032\0020\0002\006\020\005\032\0020\0022\006\020\006\032\0020\002H\000¢\006\004\b\b\020\t\032'\020\016\032\0020\r2\006\020\013\032\0020\n2\006\020\f\032\0020\n2\006\020\006\032\0020\nH\000¢\006\004\b\016\020\017\032 \020\020\032\0020\n2\006\020\001\032\0020\0022\006\020\004\032\0020\nH\b¢\006\004\b\020\020\021\032 \020\020\032\0020\n2\006\020\001\032\0020\n2\006\020\004\032\0020\002H\b¢\006\004\b\020\020\022\032\027\020\025\032\0020\0232\006\020\024\032\0020\023H\000¢\006\004\b\025\020\026\032\034\020\031\032\0020\002*\0020\0272\006\020\030\032\0020\002H\f¢\006\004\b\031\020\032\032\034\020\031\032\0020\n*\0020\0272\006\020\030\032\0020\nH\f¢\006\004\b\031\020\033\032\034\020\031\032\0020\n*\0020\0022\006\020\030\032\0020\nH\f¢\006\004\b\031\020\021\032\034\020\035\032\0020\002*\0020\0022\006\020\034\032\0020\002H\f¢\006\004\b\035\020\036\032\033\020\025\032\0020\002*\0020\0002\006\020\037\032\0020\002H\000¢\006\004\b\025\020 \032\033\020\025\032\0020\002*\0020!2\006\020\"\032\0020\002H\000¢\006\004\b\025\020#\032\023\020$\032\0020\002*\0020\002H\000¢\006\004\b$\020%\032\023\020$\032\0020\n*\0020\nH\000¢\006\004\b$\020&\032\023\020$\032\0020'*\0020'H\000¢\006\004\b$\020(\032\034\020)\032\0020\n*\0020\n2\006\020\034\032\0020\002H\f¢\006\004\b)\020\022\032\034\020*\032\0020\002*\0020\0272\006\020\030\032\0020\002H\f¢\006\004\b*\020\032\032\034\020+\032\0020\002*\0020\0272\006\020\030\032\0020\002H\f¢\006\004\b+\020\032\032\023\020-\032\0020,*\0020\027H\000¢\006\004\b-\020.\032\023\020-\032\0020,*\0020\002H\000¢\006\004\b-\020/\032\023\020-\032\0020,*\0020\nH\000¢\006\004\b-\0200\032\034\0201\032\0020\027*\0020\0272\006\020\030\032\0020\027H\f¢\006\004\b1\0202\"\032\0203\032\0020\0028\000XD¢\006\f\n\004\b3\0204\032\004\b5\0206\" \0207\032\0020\0238\000X\004¢\006\022\n\004\b7\0208\022\004\b;\020<\032\004\b9\020:¨\006="}, d2 = {"", "a", "", "aOffset", "b", "bOffset", "byteCount", "", "arrayRangeEquals", "([BI[BII)Z", "", "size", "offset", "", "checkOffsetAndCount", "(JJJ)V", "minOf", "(IJ)J", "(JI)J", "Lokio/Buffer$UnsafeCursor;", "unsafeCursor", "resolveDefaultParameter", "(Lokio/Buffer$UnsafeCursor;)Lokio/Buffer$UnsafeCursor;", "", "other", "and", "(BI)I", "(BJ)J", "bitCount", "leftRotate", "(II)I", "sizeParam", "([BI)I", "Lokio/ByteString;", "position", "(Lokio/ByteString;I)I", "reverseBytes", "(I)I", "(J)J", "", "(S)S", "rightRotate", "shl", "shr", "", "toHexString", "(B)Ljava/lang/String;", "(I)Ljava/lang/String;", "(J)Ljava/lang/String;", "xor", "(BB)B", "DEFAULT__ByteString_size", "I", "getDEFAULT__ByteString_size", "()I", "DEFAULT__new_UnsafeCursor", "Lokio/Buffer$UnsafeCursor;", "getDEFAULT__new_UnsafeCursor", "()Lokio/Buffer$UnsafeCursor;", "getDEFAULT__new_UnsafeCursor$annotations", "()V", "okio"})
@JvmName(name = "-SegmentedByteString")
@SourceDebugExtension({"SMAP\nUtil.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Util.kt\nokio/-SegmentedByteString\n*L\n1#1,187:1\n68#1:188\n74#1:189\n*S KotlinDebug\n*F\n+ 1 Util.kt\nokio/-SegmentedByteString\n*L\n106#1:188\n107#1:189\n*E\n"})
public final class -SegmentedByteString {
  public static final void checkOffsetAndCount(long size, long offset, long byteCount) {
    if ((offset | byteCount) < 0L || offset > size || size - offset < byteCount)
      throw new ArrayIndexOutOfBoundsException("size=" + size + " offset=" + offset + " byteCount=" + byteCount); 
  }
  
  public static final short reverseBytes(short $this$reverseBytes) {
    int i = $this$reverseBytes & 0xFFFF;
    int reversed = (i & 0xFF00) >>> 8 | (
      i & 0xFF) << 8;
    return (short)reversed;
  }
  
  public static final int reverseBytes(int $this$reverseBytes) {
    return ($this$reverseBytes & 0xFF000000) >>> 24 | (
      $this$reverseBytes & 0xFF0000) >>> 8 | (
      $this$reverseBytes & 0xFF00) << 8 | (
      $this$reverseBytes & 0xFF) << 24;
  }
  
  public static final long reverseBytes(long $this$reverseBytes) {
    return ($this$reverseBytes & 0xFF00000000000000L) >>> 56L | (
      $this$reverseBytes & 0xFF000000000000L) >>> 40L | (
      $this$reverseBytes & 0xFF0000000000L) >>> 24L | (
      $this$reverseBytes & 0xFF00000000L) >>> 8L | (
      $this$reverseBytes & 0xFF000000L) << 8L | (
      $this$reverseBytes & 0xFF0000L) << 24L | (
      $this$reverseBytes & 0xFF00L) << 40L | (
      $this$reverseBytes & 0xFFL) << 56L;
  }
  
  public static final int leftRotate(int $this$leftRotate, int bitCount) {
    int $i$f$leftRotate = 0;
    return $this$leftRotate << bitCount | $this$leftRotate >>> 32 - bitCount;
  }
  
  public static final long rightRotate(long $this$rightRotate, int bitCount) {
    int $i$f$rightRotate = 0;
    return $this$rightRotate >>> bitCount | $this$rightRotate << 64 - bitCount;
  }
  
  public static final int shr(byte $this$shr, int other) {
    int $i$f$shr = 0;
    return $this$shr >> other;
  }
  
  public static final int shl(byte $this$shl, int other) {
    int $i$f$shl = 0;
    return $this$shl << other;
  }
  
  public static final int and(byte $this$and, int other) {
    int $i$f$and = 0;
    return $this$and & other;
  }
  
  public static final long and(byte $this$and, long other) {
    int $i$f$and = 0;
    return $this$and & other;
  }
  
  public static final byte xor(byte $this$xor, byte other) {
    int $i$f$xor = 0;
    return (byte)($this$xor ^ other);
  }
  
  public static final long and(int $this$and, long other) {
    int $i$f$and = 0;
    return $this$and & other;
  }
  
  public static final long minOf(long a, int b) {
    int $i$f$minOf = 0;
    return Math.min(a, b);
  }
  
  public static final long minOf(int a, long b) {
    int $i$f$minOf = 0;
    return Math.min(a, b);
  }
  
  public static final boolean arrayRangeEquals(@NotNull byte[] a, int aOffset, @NotNull byte[] b, int bOffset, int byteCount) {
    Intrinsics.checkNotNullParameter(a, "a");
    Intrinsics.checkNotNullParameter(b, "b");
    for (int i = 0; i < byteCount; i++) {
      if (a[i + aOffset] != b[i + bOffset])
        return false; 
    } 
    return true;
  }
  
  @NotNull
  public static final String toHexString(byte $this$toHexString) {
    char[] result = new char[2];
    byte b = $this$toHexString;
    int other$iv = 4, $i$f$shr = 0;
    result[0] = -ByteString.getHEX_DIGIT_CHARS()[
        
        b >> other$iv & 0xF];
    byte $this$shr$iv = $this$toHexString;
    other$iv = 15;
    int $i$f$and = 0;
    result[1] = -ByteString.getHEX_DIGIT_CHARS()[$this$shr$iv & other$iv];
    return StringsKt.concatToString(result);
  }
  
  @NotNull
  public static final String toHexString(int $this$toHexString) {
    if ($this$toHexString == 0)
      return "0"; 
    char[] result = new char[8];
    result[0] = -ByteString.getHEX_DIGIT_CHARS()[$this$toHexString >> 28 & 0xF];
    result[1] = -ByteString.getHEX_DIGIT_CHARS()[$this$toHexString >> 24 & 0xF];
    result[2] = -ByteString.getHEX_DIGIT_CHARS()[$this$toHexString >> 20 & 0xF];
    result[3] = -ByteString.getHEX_DIGIT_CHARS()[$this$toHexString >> 16 & 0xF];
    result[4] = -ByteString.getHEX_DIGIT_CHARS()[$this$toHexString >> 12 & 0xF];
    result[5] = -ByteString.getHEX_DIGIT_CHARS()[$this$toHexString >> 8 & 0xF];
    result[6] = -ByteString.getHEX_DIGIT_CHARS()[$this$toHexString >> 4 & 0xF];
    result[7] = -ByteString.getHEX_DIGIT_CHARS()[$this$toHexString & 0xF];
    int i = 0;
    while (i < result.length && result[i] == '0')
      i++; 
    return StringsKt.concatToString(result, i, result.length);
  }
  
  @NotNull
  public static final String toHexString(long $this$toHexString) {
    if ($this$toHexString == 0L)
      return "0"; 
    char[] result = new char[16];
    result[0] = -ByteString.getHEX_DIGIT_CHARS()[(int)($this$toHexString >> 60L & 0xFL)];
    result[1] = -ByteString.getHEX_DIGIT_CHARS()[(int)($this$toHexString >> 56L & 0xFL)];
    result[2] = -ByteString.getHEX_DIGIT_CHARS()[(int)($this$toHexString >> 52L & 0xFL)];
    result[3] = -ByteString.getHEX_DIGIT_CHARS()[(int)($this$toHexString >> 48L & 0xFL)];
    result[4] = -ByteString.getHEX_DIGIT_CHARS()[(int)($this$toHexString >> 44L & 0xFL)];
    result[5] = -ByteString.getHEX_DIGIT_CHARS()[(int)($this$toHexString >> 40L & 0xFL)];
    result[6] = -ByteString.getHEX_DIGIT_CHARS()[(int)($this$toHexString >> 36L & 0xFL)];
    result[7] = -ByteString.getHEX_DIGIT_CHARS()[(int)($this$toHexString >> 32L & 0xFL)];
    result[8] = -ByteString.getHEX_DIGIT_CHARS()[(int)($this$toHexString >> 28L & 0xFL)];
    result[9] = -ByteString.getHEX_DIGIT_CHARS()[(int)($this$toHexString >> 24L & 0xFL)];
    result[10] = -ByteString.getHEX_DIGIT_CHARS()[(int)($this$toHexString >> 20L & 0xFL)];
    result[11] = -ByteString.getHEX_DIGIT_CHARS()[(int)($this$toHexString >> 16L & 0xFL)];
    result[12] = -ByteString.getHEX_DIGIT_CHARS()[(int)($this$toHexString >> 12L & 0xFL)];
    result[13] = -ByteString.getHEX_DIGIT_CHARS()[(int)($this$toHexString >> 8L & 0xFL)];
    result[14] = -ByteString.getHEX_DIGIT_CHARS()[(int)($this$toHexString >> 4L & 0xFL)];
    result[15] = -ByteString.getHEX_DIGIT_CHARS()[(int)($this$toHexString & 0xFL)];
    int i = 0;
    while (i < result.length && result[i] == '0')
      i++; 
    return StringsKt.concatToString(result, i, result.length);
  }
  
  @NotNull
  private static final Buffer.UnsafeCursor DEFAULT__new_UnsafeCursor = new Buffer.UnsafeCursor();
  
  @NotNull
  public static final Buffer.UnsafeCursor getDEFAULT__new_UnsafeCursor() {
    return DEFAULT__new_UnsafeCursor;
  }
  
  @NotNull
  public static final Buffer.UnsafeCursor resolveDefaultParameter(@NotNull Buffer.UnsafeCursor unsafeCursor) {
    Intrinsics.checkNotNullParameter(unsafeCursor, "unsafeCursor");
    if (unsafeCursor == DEFAULT__new_UnsafeCursor)
      return new Buffer.UnsafeCursor(); 
    return unsafeCursor;
  }
  
  private static final int DEFAULT__ByteString_size = -1234567890;
  
  public static final int getDEFAULT__ByteString_size() {
    return DEFAULT__ByteString_size;
  }
  
  public static final int resolveDefaultParameter(@NotNull ByteString $this$resolveDefaultParameter, int position) {
    Intrinsics.checkNotNullParameter($this$resolveDefaultParameter, "<this>");
    if (position == DEFAULT__ByteString_size)
      return $this$resolveDefaultParameter.size(); 
    return position;
  }
  
  public static final int resolveDefaultParameter(@NotNull byte[] $this$resolveDefaultParameter, int sizeParam) {
    Intrinsics.checkNotNullParameter($this$resolveDefaultParameter, "<this>");
    if (sizeParam == DEFAULT__ByteString_size)
      return $this$resolveDefaultParameter.length; 
    return sizeParam;
  }
}
