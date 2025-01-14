package okio.internal;

import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okio.Buffer;
import okio.ByteString;
import okio.Segment;
import okio.SegmentedByteString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\000T\n\002\020\025\n\002\020\b\n\002\b\005\n\002\030\002\n\000\n\002\020\022\n\002\b\003\n\002\020\002\n\002\b\002\n\002\020\000\n\000\n\002\020\013\n\002\b\006\n\002\020\005\n\002\b\005\n\002\030\002\n\002\b\007\n\002\030\002\n\002\b\003\n\002\030\002\n\002\030\002\n\002\b\t\032+\020\005\032\0020\001*\0020\0002\006\020\002\032\0020\0012\006\020\003\032\0020\0012\006\020\004\032\0020\001H\000¢\006\004\b\005\020\006\0324\020\016\032\0020\r*\0020\0072\006\020\b\032\0020\0012\006\020\n\032\0020\t2\006\020\013\032\0020\0012\006\020\f\032\0020\001H\b¢\006\004\b\016\020\017\032\036\020\023\032\0020\022*\0020\0072\b\020\021\032\004\030\0010\020H\b¢\006\004\b\023\020\024\032\024\020\025\032\0020\001*\0020\007H\b¢\006\004\b\025\020\026\032\024\020\027\032\0020\001*\0020\007H\b¢\006\004\b\027\020\026\032\034\020\032\032\0020\031*\0020\0072\006\020\030\032\0020\001H\b¢\006\004\b\032\020\033\0324\020\035\032\0020\022*\0020\0072\006\020\b\032\0020\0012\006\020\021\032\0020\t2\006\020\034\032\0020\0012\006\020\f\032\0020\001H\b¢\006\004\b\035\020\036\0324\020\035\032\0020\022*\0020\0072\006\020\b\032\0020\0012\006\020\021\032\0020\0372\006\020\034\032\0020\0012\006\020\f\032\0020\001H\b¢\006\004\b\035\020 \032$\020#\032\0020\037*\0020\0072\006\020!\032\0020\0012\006\020\"\032\0020\001H\b¢\006\004\b#\020$\032\024\020%\032\0020\t*\0020\007H\b¢\006\004\b%\020&\032,\020)\032\0020\r*\0020\0072\006\020(\032\0020'2\006\020\b\032\0020\0012\006\020\f\032\0020\001H\b¢\006\004\b)\020*\032d\0200\032\0020\r*\0020\0072K\020/\032G\022\023\022\0210\t¢\006\f\b,\022\b\b-\022\004\b\b(.\022\023\022\0210\001¢\006\f\b,\022\b\b-\022\004\b\b(\b\022\023\022\0210\001¢\006\f\b,\022\b\b-\022\004\b\b(\f\022\004\022\0020\r0+H\bø\001\000¢\006\004\b0\0201\032q\0200\032\0020\r*\0020\0072\006\020!\032\0020\0012\006\020\"\032\0020\0012K\020/\032G\022\023\022\0210\t¢\006\f\b,\022\b\b-\022\004\b\b(.\022\023\022\0210\001¢\006\f\b,\022\b\b-\022\004\b\b(\b\022\023\022\0210\001¢\006\f\b,\022\b\b-\022\004\b\b(\f\022\004\022\0020\r0+H\b¢\006\004\b0\0202\032\033\0203\032\0020\001*\0020\0072\006\020\030\032\0020\001H\000¢\006\004\b3\0204\002\007\n\005\b20\001¨\0065"}, d2 = {"", "", "value", "fromIndex", "toIndex", "binarySearch", "([IIII)I", "Lokio/SegmentedByteString;", "offset", "", "target", "targetOffset", "byteCount", "", "commonCopyInto", "(Lokio/SegmentedByteString;I[BII)V", "", "other", "", "commonEquals", "(Lokio/SegmentedByteString;Ljava/lang/Object;)Z", "commonGetSize", "(Lokio/SegmentedByteString;)I", "commonHashCode", "pos", "", "commonInternalGet", "(Lokio/SegmentedByteString;I)B", "otherOffset", "commonRangeEquals", "(Lokio/SegmentedByteString;I[BII)Z", "Lokio/ByteString;", "(Lokio/SegmentedByteString;ILokio/ByteString;II)Z", "beginIndex", "endIndex", "commonSubstring", "(Lokio/SegmentedByteString;II)Lokio/ByteString;", "commonToByteArray", "(Lokio/SegmentedByteString;)[B", "Lokio/Buffer;", "buffer", "commonWrite", "(Lokio/SegmentedByteString;Lokio/Buffer;II)V", "Lkotlin/Function3;", "Lkotlin/ParameterName;", "name", "data", "action", "forEachSegment", "(Lokio/SegmentedByteString;Lkotlin/jvm/functions/Function3;)V", "(Lokio/SegmentedByteString;IILkotlin/jvm/functions/Function3;)V", "segment", "(Lokio/SegmentedByteString;I)I", "okio"})
@JvmName(name = "-SegmentedByteString")
@SourceDebugExtension({"SMAP\nSegmentedByteString.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SegmentedByteString.kt\nokio/internal/-SegmentedByteString\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,250:1\n63#1,12:252\n85#1,14:264\n85#1,14:278\n85#1,14:292\n85#1,14:306\n63#1,12:320\n1#2:251\n*S KotlinDebug\n*F\n+ 1 SegmentedByteString.kt\nokio/internal/-SegmentedByteString\n*L\n147#1:252,12\n160#1:264,14\n182#1:278,14\n202#1:292,14\n219#1:306,14\n239#1:320,12\n*E\n"})
public final class -SegmentedByteString {
  public static final int binarySearch(@NotNull int[] $this$binarySearch, int value, int fromIndex, int toIndex) {
    Intrinsics.checkNotNullParameter($this$binarySearch, "<this>");
    int left = fromIndex;
    int right = toIndex - 1;
    while (left <= right) {
      int mid = left + right >>> 1;
      int midVal = $this$binarySearch[mid];
      if (midVal < value) {
        left = mid + 1;
        continue;
      } 
      if (midVal > value) {
        right = mid - 1;
        continue;
      } 
      return mid;
    } 
    return -left - 1;
  }
  
  public static final int segment(@NotNull SegmentedByteString $this$segment, int pos) {
    Intrinsics.checkNotNullParameter($this$segment, "<this>");
    int i = binarySearch($this$segment.getDirectory$okio(), pos + 1, 0, ((Object[])$this$segment.getSegments$okio()).length);
    return (i >= 0) ? i : (i ^ 0xFFFFFFFF);
  }
  
  public static final void forEachSegment(@NotNull SegmentedByteString $this$forEachSegment, @NotNull Function3 action) {
    Intrinsics.checkNotNullParameter($this$forEachSegment, "<this>");
    Intrinsics.checkNotNullParameter(action, "action");
    int $i$f$forEachSegment = 0, segmentCount = ((Object[])$this$forEachSegment.getSegments$okio()).length;
    int s = 0;
    int pos = 0;
    while (s < segmentCount) {
      int segmentPos = $this$forEachSegment.getDirectory$okio()[segmentCount + s];
      int nextSegmentOffset = $this$forEachSegment.getDirectory$okio()[s];
      action.invoke($this$forEachSegment.getSegments$okio()[s], Integer.valueOf(segmentPos), Integer.valueOf(nextSegmentOffset - pos));
      pos = nextSegmentOffset;
      s++;
    } 
  }
  
  private static final void forEachSegment(SegmentedByteString $this$forEachSegment, int beginIndex, int endIndex, Function3 action) {
    int $i$f$forEachSegment = 0, s = segment($this$forEachSegment, beginIndex);
    int pos = beginIndex;
    while (pos < endIndex) {
      int segmentOffset = (s == 0) ? 0 : $this$forEachSegment.getDirectory$okio()[s - 1];
      int segmentSize = $this$forEachSegment.getDirectory$okio()[s] - segmentOffset;
      int segmentPos = $this$forEachSegment.getDirectory$okio()[((Object[])$this$forEachSegment.getSegments$okio()).length + s];
      int byteCount = Math.min(endIndex, segmentOffset + segmentSize) - pos;
      int offset = segmentPos + pos - segmentOffset;
      action.invoke($this$forEachSegment.getSegments$okio()[s], Integer.valueOf(offset), Integer.valueOf(byteCount));
      pos += byteCount;
      s++;
    } 
  }
  
  @NotNull
  public static final ByteString commonSubstring(@NotNull SegmentedByteString $this$commonSubstring, int beginIndex, int endIndex) {
    Intrinsics.checkNotNullParameter($this$commonSubstring, "<this>");
    int $i$f$commonSubstring = 0, i = okio.-SegmentedByteString.resolveDefaultParameter((ByteString)$this$commonSubstring, endIndex);
    if (!((beginIndex >= 0) ? 1 : 0)) {
      int $i$a$-require--SegmentedByteString$commonSubstring$1 = 0;
      String str = "beginIndex=" + beginIndex + " < 0";
      throw new IllegalArgumentException(str.toString());
    } 
    if (!((i <= $this$commonSubstring.size()) ? 1 : 0)) {
      int $i$a$-require--SegmentedByteString$commonSubstring$2 = 0;
      String str = "endIndex=" + i + " > length(" + $this$commonSubstring.size() + ')';
      throw new IllegalArgumentException(str.toString());
    } 
    int subLen = i - beginIndex;
    if (!((subLen >= 0) ? 1 : 0)) {
      int $i$a$-require--SegmentedByteString$commonSubstring$3 = 0;
      String str = "endIndex=" + i + " < beginIndex=" + beginIndex;
      throw new IllegalArgumentException(str.toString());
    } 
    if (beginIndex == 0 && i == $this$commonSubstring.size())
      return (ByteString)$this$commonSubstring; 
    if (beginIndex == i)
      return ByteString.EMPTY; 
    int beginSegment = segment($this$commonSubstring, beginIndex);
    int endSegment = segment($this$commonSubstring, i - 1);
    Object[] arrayOfObject = (Object[])$this$commonSubstring.getSegments$okio();
    int j = endSegment + 1;
    byte[][] newSegments = (byte[][])ArraysKt.copyOfRange(arrayOfObject, beginSegment, j);
    int[] newDirectory = new int[((Object[])newSegments).length * 2];
    int index = 0;
    int s = beginSegment;
    if (s <= endSegment)
      while (true) {
        newDirectory[index] = Math.min($this$commonSubstring.getDirectory$okio()[s] - beginIndex, subLen);
        newDirectory[index++ + ((Object[])newSegments).length] = $this$commonSubstring.getDirectory$okio()[s + ((Object[])$this$commonSubstring.getSegments$okio()).length];
        if (s != endSegment) {
          s++;
          continue;
        } 
        break;
      }  
    int segmentOffset = (beginSegment == 0) ? 0 : $this$commonSubstring.getDirectory$okio()[beginSegment - 1];
    int k = ((Object[])newSegments).length;
    newDirectory[k] = newDirectory[k] + beginIndex - segmentOffset;
    return (ByteString)new SegmentedByteString(newSegments, newDirectory);
  }
  
  public static final byte commonInternalGet(@NotNull SegmentedByteString $this$commonInternalGet, int pos) {
    Intrinsics.checkNotNullParameter($this$commonInternalGet, "<this>");
    int $i$f$commonInternalGet = 0;
    okio.-SegmentedByteString.checkOffsetAndCount($this$commonInternalGet.getDirectory$okio()[((Object[])$this$commonInternalGet.getSegments$okio()).length - 1], pos, 1L);
    int segment = segment($this$commonInternalGet, pos);
    int segmentOffset = (segment == 0) ? 0 : $this$commonInternalGet.getDirectory$okio()[segment - 1];
    int segmentPos = $this$commonInternalGet.getDirectory$okio()[segment + ((Object[])$this$commonInternalGet.getSegments$okio()).length];
    return $this$commonInternalGet.getSegments$okio()[segment][pos - segmentOffset + segmentPos];
  }
  
  public static final int commonGetSize(@NotNull SegmentedByteString $this$commonGetSize) {
    Intrinsics.checkNotNullParameter($this$commonGetSize, "<this>");
    int $i$f$commonGetSize = 0;
    return $this$commonGetSize.getDirectory$okio()[((Object[])$this$commonGetSize.getSegments$okio()).length - 1];
  }
  
  @NotNull
  public static final byte[] commonToByteArray(@NotNull SegmentedByteString $this$commonToByteArray) {
    Intrinsics.checkNotNullParameter($this$commonToByteArray, "<this>");
    int $i$f$commonToByteArray = 0;
    byte[] result = new byte[$this$commonToByteArray.size()];
    int resultPos = 0;
    SegmentedByteString $this$forEachSegment$iv = $this$commonToByteArray;
    int $i$f$forEachSegment = 0;
    int segmentCount$iv = ((Object[])$this$forEachSegment$iv.getSegments$okio()).length;
    int s$iv = 0;
    int pos$iv = 0;
    while (s$iv < segmentCount$iv) {
      int segmentPos$iv = $this$forEachSegment$iv.getDirectory$okio()[segmentCount$iv + s$iv];
      int nextSegmentOffset$iv = $this$forEachSegment$iv.getDirectory$okio()[s$iv];
      int i = nextSegmentOffset$iv - pos$iv, j = segmentPos$iv;
      byte[] data = $this$forEachSegment$iv.getSegments$okio()[s$iv];
      int $i$a$-forEachSegment--SegmentedByteString$commonToByteArray$1 = 0;
    } 
    return result;
  }
  
  public static final void commonWrite(@NotNull SegmentedByteString $this$commonWrite, @NotNull Buffer buffer, int offset, int byteCount) {
    Intrinsics.checkNotNullParameter($this$commonWrite, "<this>");
    Intrinsics.checkNotNullParameter(buffer, "buffer");
    int $i$f$commonWrite = 0;
    SegmentedByteString segmentedByteString = $this$commonWrite;
    int endIndex$iv = offset + byteCount, $i$f$forEachSegment = 0;
    int s$iv = segment(segmentedByteString, offset);
    int pos$iv = offset;
    for (; pos$iv < endIndex$iv; segment = new Segment(data, j, j + i, true, false)) {
      Segment segment;
      int segmentOffset$iv = (s$iv == 0) ? 0 : segmentedByteString.getDirectory$okio()[s$iv - 1];
      int segmentSize$iv = segmentedByteString.getDirectory$okio()[s$iv] - segmentOffset$iv;
      int segmentPos$iv = segmentedByteString.getDirectory$okio()[((Object[])segmentedByteString.getSegments$okio()).length + s$iv];
      int byteCount$iv = Math.min(endIndex$iv, segmentOffset$iv + segmentSize$iv) - pos$iv;
      int offset$iv = segmentPos$iv + pos$iv - segmentOffset$iv;
      int i = byteCount$iv, j = offset$iv;
      byte[] data = segmentedByteString.getSegments$okio()[s$iv];
      int $i$a$-forEachSegment--SegmentedByteString$commonWrite$1 = 0;
    } 
    buffer.setSize$okio(buffer.size() + byteCount);
  }
  
  public static final boolean commonRangeEquals(@NotNull SegmentedByteString $this$commonRangeEquals, int offset, @NotNull ByteString other, int otherOffset, int byteCount) {
    Intrinsics.checkNotNullParameter($this$commonRangeEquals, "<this>");
    Intrinsics.checkNotNullParameter(other, "other");
    int $i$f$commonRangeEquals = 0;
    if (offset < 0 || offset > $this$commonRangeEquals.size() - byteCount)
      return false; 
    int i = 0;
    i = otherOffset;
    SegmentedByteString segmentedByteString = $this$commonRangeEquals;
    int endIndex$iv = offset + byteCount, $i$f$forEachSegment = 0;
    int s$iv = segment(segmentedByteString, offset);
    int pos$iv = offset;
    while (pos$iv < endIndex$iv) {
      int segmentOffset$iv = (s$iv == 0) ? 0 : segmentedByteString.getDirectory$okio()[s$iv - 1];
      int segmentSize$iv = segmentedByteString.getDirectory$okio()[s$iv] - segmentOffset$iv;
      int segmentPos$iv = segmentedByteString.getDirectory$okio()[((Object[])segmentedByteString.getSegments$okio()).length + s$iv];
      int byteCount$iv = Math.min(endIndex$iv, segmentOffset$iv + segmentSize$iv) - pos$iv;
      int offset$iv = segmentPos$iv + pos$iv - segmentOffset$iv;
      int j = byteCount$iv, k = offset$iv;
      byte[] data = segmentedByteString.getSegments$okio()[s$iv];
      int $i$a$-forEachSegment--SegmentedByteString$commonRangeEquals$1 = 0;
      if (!other.rangeEquals(i, data, k, j))
        return false; 
    } 
    return true;
  }
  
  public static final boolean commonRangeEquals(@NotNull SegmentedByteString $this$commonRangeEquals, int offset, @NotNull byte[] other, int otherOffset, int byteCount) {
    Intrinsics.checkNotNullParameter($this$commonRangeEquals, "<this>");
    Intrinsics.checkNotNullParameter(other, "other");
    int $i$f$commonRangeEquals = 0;
    if (offset < 0 || offset > $this$commonRangeEquals.size() - byteCount || otherOffset < 0 || otherOffset > other.length - byteCount)
      return false; 
    int i = 0;
    i = otherOffset;
    SegmentedByteString segmentedByteString = $this$commonRangeEquals;
    int endIndex$iv = offset + byteCount, $i$f$forEachSegment = 0;
    int s$iv = segment(segmentedByteString, offset);
    int pos$iv = offset;
    while (pos$iv < endIndex$iv) {
      int segmentOffset$iv = (s$iv == 0) ? 0 : segmentedByteString.getDirectory$okio()[s$iv - 1];
      int segmentSize$iv = segmentedByteString.getDirectory$okio()[s$iv] - segmentOffset$iv;
      int segmentPos$iv = segmentedByteString.getDirectory$okio()[((Object[])segmentedByteString.getSegments$okio()).length + s$iv];
      int byteCount$iv = Math.min(endIndex$iv, segmentOffset$iv + segmentSize$iv) - pos$iv;
      int offset$iv = segmentPos$iv + pos$iv - segmentOffset$iv;
      int j = byteCount$iv, k = offset$iv;
      byte[] data = segmentedByteString.getSegments$okio()[s$iv];
      int $i$a$-forEachSegment--SegmentedByteString$commonRangeEquals$2 = 0;
      if (!okio.-SegmentedByteString.arrayRangeEquals(data, k, other, i, j))
        return false; 
    } 
    return true;
  }
  
  public static final void commonCopyInto(@NotNull SegmentedByteString $this$commonCopyInto, int offset, @NotNull byte[] target, int targetOffset, int byteCount) {
    Intrinsics.checkNotNullParameter($this$commonCopyInto, "<this>");
    Intrinsics.checkNotNullParameter(target, "target");
    int $i$f$commonCopyInto = 0;
    okio.-SegmentedByteString.checkOffsetAndCount($this$commonCopyInto.size(), offset, byteCount);
    okio.-SegmentedByteString.checkOffsetAndCount(target.length, targetOffset, byteCount);
    int i = 0;
    i = targetOffset;
    SegmentedByteString segmentedByteString = $this$commonCopyInto;
    int endIndex$iv = offset + byteCount, $i$f$forEachSegment = 0;
    int s$iv = segment(segmentedByteString, offset);
    int pos$iv = offset;
    while (pos$iv < endIndex$iv) {
      int segmentOffset$iv = (s$iv == 0) ? 0 : segmentedByteString.getDirectory$okio()[s$iv - 1];
      int segmentSize$iv = segmentedByteString.getDirectory$okio()[s$iv] - segmentOffset$iv;
      int segmentPos$iv = segmentedByteString.getDirectory$okio()[((Object[])segmentedByteString.getSegments$okio()).length + s$iv];
      int byteCount$iv = Math.min(endIndex$iv, segmentOffset$iv + segmentSize$iv) - pos$iv;
      int offset$iv = segmentPos$iv + pos$iv - segmentOffset$iv;
      int j = byteCount$iv, k = offset$iv;
      byte[] data = segmentedByteString.getSegments$okio()[s$iv];
      int $i$a$-forEachSegment--SegmentedByteString$commonCopyInto$1 = 0;
      ArraysKt.copyInto(data, target, i, k, k + j);
      i += j;
      pos$iv += byteCount$iv;
      s$iv++;
    } 
  }
  
  public static final boolean commonEquals(@NotNull SegmentedByteString $this$commonEquals, @Nullable Object other) {
    Intrinsics.checkNotNullParameter($this$commonEquals, "<this>");
    int $i$f$commonEquals = 0;
    return (other == $this$commonEquals) ? true : ((other instanceof ByteString) ? ((((ByteString)other).size() == $this$commonEquals.size() && $this$commonEquals.rangeEquals(0, (ByteString)other, 0, $this$commonEquals.size()))) : false);
  }
  
  public static final int commonHashCode(@NotNull SegmentedByteString $this$commonHashCode) {
    Intrinsics.checkNotNullParameter($this$commonHashCode, "<this>");
    int $i$f$commonHashCode = 0, result = 0;
    result = $this$commonHashCode.getHashCode$okio();
    if (result != 0)
      return result; 
    result = 1;
    SegmentedByteString $this$forEachSegment$iv = $this$commonHashCode;
    int $i$f$forEachSegment = 0;
    int segmentCount$iv = ((Object[])$this$forEachSegment$iv.getSegments$okio()).length;
    int s$iv = 0;
    int pos$iv = 0;
    for (; s$iv < segmentCount$iv; i = k, limit = k + j) {
      int i, limit, segmentPos$iv = $this$forEachSegment$iv.getDirectory$okio()[segmentCount$iv + s$iv];
      int nextSegmentOffset$iv = $this$forEachSegment$iv.getDirectory$okio()[s$iv];
      int j = nextSegmentOffset$iv - pos$iv, k = segmentPos$iv;
      byte[] data = $this$forEachSegment$iv.getSegments$okio()[s$iv];
      int $i$a$-forEachSegment--SegmentedByteString$commonHashCode$1 = 0;
    } 
    $this$commonHashCode.setHashCode$okio(result);
    return result;
  }
}
