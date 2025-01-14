package okio.internal;

import java.io.EOFException;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okio.-SegmentedByteString;
import okio.Buffer;
import okio.ByteString;
import okio.Options;
import okio.Segment;
import okio.SegmentPool;
import okio.SegmentedByteString;
import okio.Sink;
import okio.Source;
import okio._JvmPlatformKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\000\001\n\002\030\002\n\000\n\002\020\b\n\000\n\002\020\022\n\002\b\003\n\002\020\013\n\002\b\002\n\002\030\002\n\002\020\002\n\002\b\002\n\002\030\002\n\002\b\002\n\002\020\t\n\002\b\t\n\002\020\000\n\002\b\007\n\002\020\005\n\002\b\t\n\002\030\002\n\002\b\f\n\002\030\002\n\002\b\024\n\002\020\n\n\002\b\003\n\002\020\016\n\002\b\f\n\002\030\002\n\002\b\020\n\002\030\002\n\002\b\030\n\002\030\002\n\002\b\022\0327\020\t\032\0020\b2\006\020\001\032\0020\0002\006\020\003\032\0020\0022\006\020\005\032\0020\0042\006\020\006\032\0020\0022\006\020\007\032\0020\002H\000¢\006\004\b\t\020\n\032\024\020\r\032\0020\f*\0020\013H\b¢\006\004\b\r\020\016\032\024\020\020\032\0020\f*\0020\017H\b¢\006\004\b\020\020\021\032\024\020\023\032\0020\022*\0020\013H\b¢\006\004\b\023\020\024\032\024\020\025\032\0020\013*\0020\013H\b¢\006\004\b\025\020\026\032,\020\032\032\0020\013*\0020\0132\006\020\027\032\0020\0132\006\020\030\032\0020\0222\006\020\031\032\0020\022H\b¢\006\004\b\032\020\033\032\036\020\036\032\0020\b*\0020\0132\b\020\035\032\004\030\0010\034H\b¢\006\004\b\036\020\037\032\034\020!\032\0020\022*\0020\0172\006\020 \032\0020\002H\b¢\006\004\b!\020\"\032\034\020%\032\0020$*\0020\0132\006\020#\032\0020\022H\b¢\006\004\b%\020&\032\024\020'\032\0020\002*\0020\013H\b¢\006\004\b'\020(\032,\020,\032\0020\022*\0020\0132\006\020)\032\0020$2\006\020*\032\0020\0222\006\020+\032\0020\022H\b¢\006\004\b,\020-\032$\020,\032\0020\022*\0020\0132\006\020\005\032\0020.2\006\020*\032\0020\022H\b¢\006\004\b,\020/\032$\0201\032\0020\022*\0020\0132\006\0200\032\0020.2\006\020*\032\0020\022H\b¢\006\004\b1\020/\032\024\0202\032\0020\002*\0020\017H\b¢\006\004\b2\0203\0324\0204\032\0020\b*\0020\0132\006\020\030\032\0020\0222\006\020\005\032\0020.2\006\020\006\032\0020\0022\006\020\031\032\0020\002H\b¢\006\004\b4\0205\032\034\0207\032\0020\002*\0020\0132\006\0206\032\0020\004H\b¢\006\004\b7\0208\032,\0207\032\0020\002*\0020\0132\006\0206\032\0020\0042\006\020\030\032\0020\0022\006\020\031\032\0020\002H\b¢\006\004\b7\0209\032$\0207\032\0020\022*\0020\0132\006\0206\032\0020\0132\006\020\031\032\0020\022H\b¢\006\004\b7\020:\032\034\020<\032\0020\022*\0020\0132\006\0206\032\0020;H\b¢\006\004\b<\020=\032\033\020?\032\0020\017*\0020\0132\006\020>\032\0020\017H\000¢\006\004\b?\020@\032\024\020A\032\0020$*\0020\013H\b¢\006\004\bA\020B\032\024\020C\032\0020\004*\0020\013H\b¢\006\004\bC\020D\032\034\020C\032\0020\004*\0020\0132\006\020\031\032\0020\022H\b¢\006\004\bC\020E\032\024\020F\032\0020.*\0020\013H\b¢\006\004\bF\020G\032\034\020F\032\0020.*\0020\0132\006\020\031\032\0020\022H\b¢\006\004\bF\020H\032\024\020I\032\0020\022*\0020\013H\b¢\006\004\bI\020\024\032\034\020J\032\0020\f*\0020\0132\006\0206\032\0020\004H\b¢\006\004\bJ\020K\032$\020J\032\0020\f*\0020\0132\006\0206\032\0020\0132\006\020\031\032\0020\022H\b¢\006\004\bJ\020L\032\024\020M\032\0020\022*\0020\013H\b¢\006\004\bM\020\024\032\024\020N\032\0020\002*\0020\013H\b¢\006\004\bN\020(\032\024\020O\032\0020\022*\0020\013H\b¢\006\004\bO\020\024\032\024\020Q\032\0020P*\0020\013H\b¢\006\004\bQ\020R\032\033\020S\032\0020\017*\0020\0132\006\020>\032\0020\017H\000¢\006\004\bS\020@\032\034\020U\032\0020T*\0020\0132\006\020\031\032\0020\022H\b¢\006\004\bU\020V\032\024\020W\032\0020\002*\0020\013H\b¢\006\004\bW\020(\032\026\020X\032\004\030\0010T*\0020\013H\b¢\006\004\bX\020Y\032\034\020[\032\0020T*\0020\0132\006\020Z\032\0020\022H\b¢\006\004\b[\020V\032\034\020]\032\0020\022*\0020\0172\006\020\\\032\0020\022H\b¢\006\004\b]\020^\032\034\020_\032\0020\002*\0020\0172\006\020\030\032\0020\022H\b¢\006\004\b_\020`\032\034\020c\032\0020\002*\0020\0132\006\020b\032\0020aH\b¢\006\004\bc\020d\032\034\020e\032\0020\f*\0020\0132\006\020\031\032\0020\022H\b¢\006\004\be\020f\032\024\020g\032\0020.*\0020\013H\b¢\006\004\bg\020G\032\034\020g\032\0020.*\0020\0132\006\020\031\032\0020\002H\b¢\006\004\bg\020h\032\034\020j\032\0020\000*\0020\0132\006\020i\032\0020\002H\b¢\006\004\bj\020k\032\034\020m\032\0020\013*\0020\0132\006\020l\032\0020\004H\b¢\006\004\bm\020n\032,\020m\032\0020\013*\0020\0132\006\020l\032\0020\0042\006\020\030\032\0020\0022\006\020\031\032\0020\002H\b¢\006\004\bm\020o\032$\020m\032\0020\f*\0020\0132\006\020l\032\0020\0132\006\020\031\032\0020\022H\b¢\006\004\bm\020L\0320\020m\032\0020\013*\0020\0132\006\020p\032\0020.2\b\b\002\020\030\032\0020\0022\b\b\002\020\031\032\0020\002H\b¢\006\004\bm\020q\032$\020m\032\0020\013*\0020\0132\006\020l\032\0020r2\006\020\031\032\0020\022H\b¢\006\004\bm\020s\032\034\020t\032\0020\022*\0020\0132\006\020l\032\0020rH\b¢\006\004\bt\020u\032\034\020v\032\0020\013*\0020\0132\006\020)\032\0020\002H\b¢\006\004\bv\020w\032\034\020y\032\0020\013*\0020\0132\006\020x\032\0020\022H\b¢\006\004\by\020z\032\034\020{\032\0020\013*\0020\0132\006\020x\032\0020\022H\b¢\006\004\b{\020z\032\034\020}\032\0020\013*\0020\0132\006\020|\032\0020\002H\b¢\006\004\b}\020w\032\034\020~\032\0020\013*\0020\0132\006\020x\032\0020\022H\b¢\006\004\b~\020z\032\036\020\001\032\0020\013*\0020\0132\006\020\032\0020\002H\b¢\006\005\b\001\020w\0322\020\001\032\0020\013*\0020\0132\007\020\001\032\0020T2\007\020\001\032\0020\0022\007\020\001\032\0020\002H\b¢\006\006\b\001\020\001\032\037\020\001\032\0020\013*\0020\0132\007\020\001\032\0020\002H\b¢\006\005\b\001\020w\032\036\020\001\032\0020T*\0020\0132\007\020\001\032\0020\022H\000¢\006\005\b\001\020V\032G\020\001\032\0028\000\"\005\b\000\020\001*\0020\0132\006\020*\032\0020\0222\034\020\001\032\027\022\006\022\004\030\0010\000\022\004\022\0020\022\022\004\022\0028\0000\001H\bø\001\000¢\006\006\b\001\020\001\032)\020\001\032\0020\002*\0020\0132\006\020b\032\0020a2\t\b\002\020\001\032\0020\bH\000¢\006\006\b\001\020\001\"'\020\001\032\0020\0048\000X\004¢\006\030\n\006\b\001\020\001\022\006\b\001\020\001\032\006\b\001\020\001\"\027\020\001\032\0020\0228\000XT¢\006\b\n\006\b\001\020\001\"\027\020\001\032\0020\0228\000XT¢\006\b\n\006\b\001\020\001\"\027\020\001\032\0020\0028\000XT¢\006\b\n\006\b\001\020\001\002\007\n\005\b20\001¨\006\001"}, d2 = {"Lokio/Segment;", "segment", "", "segmentPos", "", "bytes", "bytesOffset", "bytesLimit", "", "rangeEquals", "(Lokio/Segment;I[BII)Z", "Lokio/Buffer;", "", "commonClear", "(Lokio/Buffer;)V", "Lokio/Buffer$UnsafeCursor;", "commonClose", "(Lokio/Buffer$UnsafeCursor;)V", "", "commonCompleteSegmentByteCount", "(Lokio/Buffer;)J", "commonCopy", "(Lokio/Buffer;)Lokio/Buffer;", "out", "offset", "byteCount", "commonCopyTo", "(Lokio/Buffer;Lokio/Buffer;JJ)Lokio/Buffer;", "", "other", "commonEquals", "(Lokio/Buffer;Ljava/lang/Object;)Z", "minByteCount", "commonExpandBuffer", "(Lokio/Buffer$UnsafeCursor;I)J", "pos", "", "commonGet", "(Lokio/Buffer;J)B", "commonHashCode", "(Lokio/Buffer;)I", "b", "fromIndex", "toIndex", "commonIndexOf", "(Lokio/Buffer;BJJ)J", "Lokio/ByteString;", "(Lokio/Buffer;Lokio/ByteString;J)J", "targetBytes", "commonIndexOfElement", "commonNext", "(Lokio/Buffer$UnsafeCursor;)I", "commonRangeEquals", "(Lokio/Buffer;JLokio/ByteString;II)Z", "sink", "commonRead", "(Lokio/Buffer;[B)I", "(Lokio/Buffer;[BII)I", "(Lokio/Buffer;Lokio/Buffer;J)J", "Lokio/Sink;", "commonReadAll", "(Lokio/Buffer;Lokio/Sink;)J", "unsafeCursor", "commonReadAndWriteUnsafe", "(Lokio/Buffer;Lokio/Buffer$UnsafeCursor;)Lokio/Buffer$UnsafeCursor;", "commonReadByte", "(Lokio/Buffer;)B", "commonReadByteArray", "(Lokio/Buffer;)[B", "(Lokio/Buffer;J)[B", "commonReadByteString", "(Lokio/Buffer;)Lokio/ByteString;", "(Lokio/Buffer;J)Lokio/ByteString;", "commonReadDecimalLong", "commonReadFully", "(Lokio/Buffer;[B)V", "(Lokio/Buffer;Lokio/Buffer;J)V", "commonReadHexadecimalUnsignedLong", "commonReadInt", "commonReadLong", "", "commonReadShort", "(Lokio/Buffer;)S", "commonReadUnsafe", "", "commonReadUtf8", "(Lokio/Buffer;J)Ljava/lang/String;", "commonReadUtf8CodePoint", "commonReadUtf8Line", "(Lokio/Buffer;)Ljava/lang/String;", "limit", "commonReadUtf8LineStrict", "newSize", "commonResizeBuffer", "(Lokio/Buffer$UnsafeCursor;J)J", "commonSeek", "(Lokio/Buffer$UnsafeCursor;J)I", "Lokio/Options;", "options", "commonSelect", "(Lokio/Buffer;Lokio/Options;)I", "commonSkip", "(Lokio/Buffer;J)V", "commonSnapshot", "(Lokio/Buffer;I)Lokio/ByteString;", "minimumCapacity", "commonWritableSegment", "(Lokio/Buffer;I)Lokio/Segment;", "source", "commonWrite", "(Lokio/Buffer;[B)Lokio/Buffer;", "(Lokio/Buffer;[BII)Lokio/Buffer;", "byteString", "(Lokio/Buffer;Lokio/ByteString;II)Lokio/Buffer;", "Lokio/Source;", "(Lokio/Buffer;Lokio/Source;J)Lokio/Buffer;", "commonWriteAll", "(Lokio/Buffer;Lokio/Source;)J", "commonWriteByte", "(Lokio/Buffer;I)Lokio/Buffer;", "v", "commonWriteDecimalLong", "(Lokio/Buffer;J)Lokio/Buffer;", "commonWriteHexadecimalUnsignedLong", "i", "commonWriteInt", "commonWriteLong", "s", "commonWriteShort", "string", "beginIndex", "endIndex", "commonWriteUtf8", "(Lokio/Buffer;Ljava/lang/String;II)Lokio/Buffer;", "codePoint", "commonWriteUtf8CodePoint", "newline", "readUtf8Line", "T", "Lkotlin/Function2;", "lambda", "seek", "(Lokio/Buffer;JLkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "selectTruncated", "selectPrefix", "(Lokio/Buffer;Lokio/Options;Z)I", "HEX_DIGIT_BYTES", "[B", "getHEX_DIGIT_BYTES", "()[B", "getHEX_DIGIT_BYTES$annotations", "()V", "OVERFLOW_DIGIT_START", "J", "OVERFLOW_ZONE", "SEGMENTING_THRESHOLD", "I", "okio"})
@JvmName(name = "-Buffer")
@SourceDebugExtension({"SMAP\nBuffer.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Buffer.kt\nokio/internal/-Buffer\n+ 2 Util.kt\nokio/-SegmentedByteString\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,1730:1\n112#1,20:1753\n112#1,20:1786\n112#1:1806\n114#1,18:1808\n112#1,20:1826\n74#2:1731\n74#2:1732\n74#2:1733\n74#2:1734\n74#2:1735\n74#2:1736\n74#2:1737\n74#2:1738\n74#2:1739\n74#2:1740\n74#2:1741\n74#2:1742\n83#2:1743\n83#2:1744\n77#2:1745\n77#2:1746\n77#2:1747\n77#2:1748\n77#2:1749\n77#2:1750\n77#2:1751\n77#2:1752\n86#2:1773\n89#2:1775\n74#2:1776\n74#2:1777\n74#2:1778\n74#2:1779\n74#2:1780\n74#2:1781\n74#2:1782\n74#2:1783\n74#2:1784\n74#2:1785\n89#2:1807\n86#2:1846\n1#3:1774\n*S KotlinDebug\n*F\n+ 1 Buffer.kt\nokio/internal/-Buffer\n*L\n415#1:1753,20\n1292#1:1786,20\n1323#1:1806\n1323#1:1808,18\n1357#1:1826,20\n178#1:1731\n202#1:1732\n321#1:1733\n326#1:1734\n349#1:1735\n350#1:1736\n351#1:1737\n352#1:1738\n358#1:1739\n359#1:1740\n360#1:1741\n361#1:1742\n385#1:1743\n386#1:1744\n392#1:1745\n393#1:1746\n394#1:1747\n395#1:1748\n396#1:1749\n397#1:1750\n398#1:1751\n399#1:1752\n427#1:1773\n888#1:1775\n906#1:1776\n908#1:1777\n912#1:1778\n914#1:1779\n918#1:1780\n920#1:1781\n924#1:1782\n926#1:1783\n946#1:1784\n949#1:1785\n1336#1:1807\n1676#1:1846\n*E\n"})
public final class -Buffer {
  @NotNull
  private static final byte[] HEX_DIGIT_BYTES = _JvmPlatformKt.asUtf8ToByteArray("0123456789abcdef");
  
  public static final int SEGMENTING_THRESHOLD = 4096;
  
  public static final long OVERFLOW_ZONE = -922337203685477580L;
  
  public static final long OVERFLOW_DIGIT_START = -7L;
  
  @NotNull
  public static final byte[] getHEX_DIGIT_BYTES() {
    return HEX_DIGIT_BYTES;
  }
  
  public static final boolean rangeEquals(@NotNull Segment segment, int segmentPos, @NotNull byte[] bytes, int bytesOffset, int bytesLimit) {
    Intrinsics.checkNotNullParameter(segment, "segment");
    Intrinsics.checkNotNullParameter(bytes, "bytes");
    Segment segment1 = segment;
    int j = segmentPos;
    int segmentLimit = segment1.limit;
    byte[] data = segment1.data;
    int i = bytesOffset;
    while (i < bytesLimit) {
      if (j == segmentLimit) {
        Intrinsics.checkNotNull(segment1.next);
        segment1 = segment1.next;
        data = segment1.data;
        j = segment1.pos;
        segmentLimit = segment1.limit;
      } 
      if (data[j] != bytes[i])
        return false; 
      j++;
      i++;
    } 
    return true;
  }
  
  @NotNull
  public static final String readUtf8Line(@NotNull Buffer $this$readUtf8Line, long newline) {
    Intrinsics.checkNotNullParameter($this$readUtf8Line, "<this>");
    String result = $this$readUtf8Line.readUtf8(newline - 1L);
    $this$readUtf8Line.skip(2L);
    result = $this$readUtf8Line.readUtf8(newline);
    $this$readUtf8Line.skip(1L);
    return (newline > 0L && $this$readUtf8Line.getByte(newline - 1L) == 13) ? result : result;
  }
  
  public static final <T> T seek(@NotNull Buffer $this$seek, long fromIndex, @NotNull Function2 lambda) {
    Segment s;
    Intrinsics.checkNotNullParameter($this$seek, "<this>");
    Intrinsics.checkNotNullParameter(lambda, "lambda");
    int $i$f$seek = 0;
    if ($this$seek.head == null)
      return (T)lambda.invoke(null, Long.valueOf(-1L)); 
    if ($this$seek.size() - fromIndex < fromIndex) {
      long l = $this$seek.size();
      while (l > fromIndex) {
        Intrinsics.checkNotNull(s.prev);
        s = s.prev;
        l -= (s.limit - s.pos);
      } 
      return (T)lambda.invoke(s, Long.valueOf(l));
    } 
    long offset = 0L;
    while (true) {
      long nextOffset = offset + (s.limit - s.pos);
      if (nextOffset <= fromIndex) {
        Intrinsics.checkNotNull(s.next);
        s = s.next;
        offset = nextOffset;
        continue;
      } 
      break;
    } 
    return (T)lambda.invoke(s, Long.valueOf(offset));
  }
  
  public static final int selectPrefix(@NotNull Buffer $this$selectPrefix, @NotNull Options options, boolean selectTruncated) {
    Segment head;
    Intrinsics.checkNotNullParameter($this$selectPrefix, "<this>");
    Intrinsics.checkNotNullParameter(options, "options");
    if ($this$selectPrefix.head == null)
      return selectTruncated ? -2 : -1; 
    Segment s = head;
    byte[] data = head.data;
    int pos = head.pos;
    int limit = head.limit;
    int[] trie = options.getTrie$okio();
    int triePos = 0;
    int prefixIndex = -1;
    while (true) {
      int scanOrSelect = trie[triePos++];
      int possiblePrefixIndex = trie[triePos++];
      if (possiblePrefixIndex != -1)
        prefixIndex = possiblePrefixIndex; 
      int nextStep = 0;
      if (s == null)
        break; 
      if (scanOrSelect < 0) {
        int scanByteCount = -1 * scanOrSelect;
        int trieLimit = triePos + scanByteCount;
        while (true) {
          byte b1 = data[pos++];
          int j = 255, k = 0, i = 
            
            b1 & j;
          if (i != trie[triePos++])
            return prefixIndex; 
          boolean scanComplete = (triePos == trieLimit);
          if (pos == limit) {
            Intrinsics.checkNotNull(s);
            Intrinsics.checkNotNull(s.next);
            s = s.next;
            pos = s.pos;
            data = s.data;
            limit = s.limit;
            if (s == head)
              if (scanComplete) {
                s = null;
              } else {
                break;
              }  
          } 
          if (scanComplete) {
            nextStep = trie[triePos];
            if (nextStep >= 0)
              return nextStep; 
            triePos = -nextStep;
          } 
        } 
        break;
      } 
      int selectChoiceCount = scanOrSelect;
      byte b = data[pos++];
      int other$iv = 255, $i$f$and = 0, byte = b & other$iv;
      int selectLimit = triePos + selectChoiceCount;
      while (true) {
        if (triePos == selectLimit)
          return prefixIndex; 
        if (byte == trie[triePos]) {
          nextStep = trie[triePos + selectChoiceCount];
          break;
        } 
        triePos++;
      } 
      if (pos == limit) {
        Intrinsics.checkNotNull(s.next);
        s = s.next;
        pos = s.pos;
        data = s.data;
        limit = s.limit;
        if (s == head) {
          s = null;
          continue;
        } 
        continue;
      } 
      continue;
    } 
    if (selectTruncated)
      return -2; 
    return prefixIndex;
  }
  
  @NotNull
  public static final Buffer commonCopyTo(@NotNull Buffer $this$commonCopyTo, @NotNull Buffer out, long offset, long byteCount) {
    Intrinsics.checkNotNullParameter($this$commonCopyTo, "<this>");
    Intrinsics.checkNotNullParameter(out, "out");
    int $i$f$commonCopyTo = 0;
    long l1 = offset;
    long l2 = byteCount;
    -SegmentedByteString.checkOffsetAndCount($this$commonCopyTo.size(), l1, l2);
    if (l2 == 0L)
      return $this$commonCopyTo; 
    out.setSize$okio(out.size() + l2);
    Segment s = $this$commonCopyTo.head;
    Intrinsics.checkNotNull(s);
    while (l1 >= (s.limit - s.pos)) {
      l1 -= (s.limit - s.pos);
      s = s.next;
    } 
    while (l2 > 0L) {
      Intrinsics.checkNotNull(s);
      Segment copy = s.sharedCopy();
      copy.pos += (int)l1;
      copy.limit = Math.min(copy.pos + (int)l2, copy.limit);
      if (out.head == null) {
        copy.prev = copy;
        copy.next = copy.prev;
        out.head = copy.next;
      } else {
        Intrinsics.checkNotNull(out.head);
        Intrinsics.checkNotNull(out.head.prev);
        out.head.prev.push(copy);
      } 
      l2 -= (copy.limit - copy.pos);
      l1 = 0L;
      s = s.next;
    } 
    return $this$commonCopyTo;
  }
  
  public static final long commonCompleteSegmentByteCount(@NotNull Buffer $this$commonCompleteSegmentByteCount) {
    Intrinsics.checkNotNullParameter($this$commonCompleteSegmentByteCount, "<this>");
    int $i$f$commonCompleteSegmentByteCount = 0;
    long result = $this$commonCompleteSegmentByteCount.size();
    if (result == 0L)
      return 0L; 
    Intrinsics.checkNotNull($this$commonCompleteSegmentByteCount.head);
    Intrinsics.checkNotNull($this$commonCompleteSegmentByteCount.head.prev);
    Segment tail = $this$commonCompleteSegmentByteCount.head.prev;
    if (tail.limit < 8192 && tail.owner)
      result -= (tail.limit - tail.pos); 
    return result;
  }
  
  public static final byte commonReadByte(@NotNull Buffer $this$commonReadByte) {
    Intrinsics.checkNotNullParameter($this$commonReadByte, "<this>");
    int $i$f$commonReadByte = 0;
    if ($this$commonReadByte.size() == 0L)
      throw new EOFException(); 
    Intrinsics.checkNotNull($this$commonReadByte.head);
    Segment segment = $this$commonReadByte.head;
    int pos = segment.pos;
    int limit = segment.limit;
    byte[] data = segment.data;
    byte b = data[pos++];
    $this$commonReadByte.setSize$okio($this$commonReadByte.size() - 1L);
    if (pos == limit) {
      $this$commonReadByte.head = segment.pop();
      SegmentPool.recycle(segment);
    } else {
      segment.pos = pos;
    } 
    return b;
  }
  
  public static final short commonReadShort(@NotNull Buffer $this$commonReadShort) {
    Intrinsics.checkNotNullParameter($this$commonReadShort, "<this>");
    int $i$f$commonReadShort = 0;
    if ($this$commonReadShort.size() < 2L)
      throw new EOFException(); 
    Intrinsics.checkNotNull($this$commonReadShort.head);
    Segment segment = $this$commonReadShort.head;
    int pos = segment.pos;
    int limit = segment.limit;
    if (limit - pos < 2) {
      byte b3 = $this$commonReadShort.readByte();
      int j = 255, k = 0;
      byte b2 = $this$commonReadShort.readByte();
      j = 255;
      k = 0;
      int i = (b3 & j) << 8 | b2 & j;
      return (short)i;
    } 
    byte[] data = segment.data;
    byte b1 = data[pos++];
    int other$iv = 255, $i$f$and = 0;
    byte $this$and$iv = data[pos++];
    other$iv = 255;
    $i$f$and = 0;
    int s = (b1 & other$iv) << 8 | $this$and$iv & other$iv;
    $this$commonReadShort.setSize$okio($this$commonReadShort.size() - 2L);
    if (pos == limit) {
      $this$commonReadShort.head = segment.pop();
      SegmentPool.recycle(segment);
    } else {
      segment.pos = pos;
    } 
    return (short)s;
  }
  
  public static final int commonReadInt(@NotNull Buffer $this$commonReadInt) {
    Intrinsics.checkNotNullParameter($this$commonReadInt, "<this>");
    int $i$f$commonReadInt = 0;
    if ($this$commonReadInt.size() < 4L)
      throw new EOFException(); 
    Intrinsics.checkNotNull($this$commonReadInt.head);
    Segment segment = $this$commonReadInt.head;
    int pos = segment.pos;
    int limit = segment.limit;
    if ((limit - pos) < 4L) {
      byte b3 = $this$commonReadInt.readByte();
      int j = 255, k = 0;
      byte b2 = $this$commonReadInt.readByte();
      j = 255;
      k = 0;
      b2 = $this$commonReadInt.readByte();
      j = 255;
      k = 0;
      b2 = $this$commonReadInt.readByte();
      j = 255;
      k = 0;
      return (b3 & j) << 24 | (
        b2 & j) << 16 | (
        b2 & j) << 8 | 
        b2 & j;
    } 
    byte[] data = segment.data;
    byte b1 = data[pos++];
    int other$iv = 255, $i$f$and = 0;
    byte $this$and$iv = data[pos++];
    other$iv = 255;
    $i$f$and = 0;
    $this$and$iv = data[pos++];
    other$iv = 255;
    $i$f$and = 0;
    $this$and$iv = data[pos++];
    other$iv = 255;
    $i$f$and = 0;
    int i = (b1 & other$iv) << 24 | (
      $this$and$iv & other$iv) << 16 | (
      $this$and$iv & other$iv) << 8 | 
      $this$and$iv & other$iv;
    $this$commonReadInt.setSize$okio($this$commonReadInt.size() - 4L);
    if (pos == limit) {
      $this$commonReadInt.head = segment.pop();
      SegmentPool.recycle(segment);
    } else {
      segment.pos = pos;
    } 
    return i;
  }
  
  public static final long commonReadLong(@NotNull Buffer $this$commonReadLong) {
    Intrinsics.checkNotNullParameter($this$commonReadLong, "<this>");
    int $i$f$commonReadLong = 0;
    if ($this$commonReadLong.size() < 8L)
      throw new EOFException(); 
    Intrinsics.checkNotNull($this$commonReadLong.head);
    Segment segment = $this$commonReadLong.head;
    int pos = segment.pos;
    int limit = segment.limit;
    if ((limit - pos) < 8L) {
      int j = $this$commonReadLong.readInt();
      long l = 4294967295L;
      int k = 0;
      int i = $this$commonReadLong.readInt();
      l = 4294967295L;
      k = 0;
      return (j & l) << 32L | 
        i & l;
    } 
    byte[] data = segment.data;
    byte b1 = data[pos++];
    long other$iv = 255L;
    int $i$f$and = 0;
    byte $this$and$iv = data[pos++];
    other$iv = 255L;
    $i$f$and = 0;
    $this$and$iv = data[pos++];
    other$iv = 255L;
    $i$f$and = 0;
    $this$and$iv = data[pos++];
    other$iv = 255L;
    $i$f$and = 0;
    $this$and$iv = data[pos++];
    other$iv = 255L;
    $i$f$and = 0;
    $this$and$iv = data[pos++];
    other$iv = 255L;
    $i$f$and = 0;
    $this$and$iv = data[pos++];
    other$iv = 255L;
    $i$f$and = 0;
    $this$and$iv = data[pos++];
    other$iv = 255L;
    $i$f$and = 0;
    long v = (b1 & other$iv) << 56L | (
      $this$and$iv & other$iv) << 48L | (
      $this$and$iv & other$iv) << 40L | (
      $this$and$iv & other$iv) << 32L | (
      $this$and$iv & other$iv) << 24L | (
      $this$and$iv & other$iv) << 16L | (
      $this$and$iv & other$iv) << 8L | 
      $this$and$iv & other$iv;
    $this$commonReadLong.setSize$okio($this$commonReadLong.size() - 8L);
    if (pos == limit) {
      $this$commonReadLong.head = segment.pop();
      SegmentPool.recycle(segment);
    } else {
      segment.pos = pos;
    } 
    return v;
  }
  
  public static final byte commonGet(@NotNull Buffer $this$commonGet, long pos) {
    Segment s$iv;
    Intrinsics.checkNotNullParameter($this$commonGet, "<this>");
    int $i$f$commonGet = 0;
    -SegmentedByteString.checkOffsetAndCount($this$commonGet.size(), pos, 1L);
    Buffer $this$seek$iv = $this$commonGet;
    int $i$f$seek = 0;
    if ($this$seek$iv.head == null) {
      long l = -1L;
      Segment segment = null;
      int i = 0;
      Intrinsics.checkNotNull(segment);
      return segment.data[(int)(segment.pos + pos - l)];
    } 
    if ($this$seek$iv.size() - pos < pos) {
      long l2 = $this$seek$iv.size();
      while (l2 > pos) {
        Intrinsics.checkNotNull(s$iv.prev);
        s$iv = s$iv.prev;
        l2 -= (s$iv.limit - s$iv.pos);
      } 
      long l3 = l2;
      Segment segment = s$iv;
      int i = 0;
      Intrinsics.checkNotNull(segment);
      return segment.data[(int)(segment.pos + pos - l3)];
    } 
    long offset$iv = 0L;
    while (true) {
      long nextOffset$iv = offset$iv + (s$iv.limit - s$iv.pos);
      if (nextOffset$iv <= pos) {
        Intrinsics.checkNotNull(s$iv.next);
        s$iv = s$iv.next;
        offset$iv = nextOffset$iv;
        continue;
      } 
      break;
    } 
    long l1 = offset$iv;
    Segment s = s$iv;
    int $i$a$-seek--Buffer$commonGet$1 = 0;
    Intrinsics.checkNotNull(s);
    return s.data[(int)(s.pos + pos - l1)];
  }
  
  public static final void commonClear(@NotNull Buffer $this$commonClear) {
    Intrinsics.checkNotNullParameter($this$commonClear, "<this>");
    int $i$f$commonClear = 0;
    $this$commonClear.skip($this$commonClear.size());
  }
  
  public static final void commonSkip(@NotNull Buffer $this$commonSkip, long byteCount) {
    Intrinsics.checkNotNullParameter($this$commonSkip, "<this>");
    int $i$f$commonSkip = 0;
    long l = byteCount;
    while (l > 0L) {
      Segment head;
      if ($this$commonSkip.head == null)
        throw new EOFException(); 
      int b$iv = head.limit - head.pos, $i$f$minOf = 0, toSkip = (int)Math.min(l, b$iv);
      $this$commonSkip.setSize$okio($this$commonSkip.size() - toSkip);
      l -= toSkip;
      head.pos += toSkip;
      if (head.pos == head.limit) {
        $this$commonSkip.head = head.pop();
        SegmentPool.recycle(head);
      } 
    } 
  }
  
  @NotNull
  public static final Buffer commonWrite(@NotNull Buffer $this$commonWrite, @NotNull ByteString byteString, int offset, int byteCount) {
    Intrinsics.checkNotNullParameter($this$commonWrite, "<this>");
    Intrinsics.checkNotNullParameter(byteString, "byteString");
    int $i$f$commonWrite = 0;
    byteString.write$okio($this$commonWrite, offset, byteCount);
    return $this$commonWrite;
  }
  
  @NotNull
  public static final Buffer commonWriteDecimalLong(@NotNull Buffer $this$commonWriteDecimalLong, long v) {
    Intrinsics.checkNotNullParameter($this$commonWriteDecimalLong, "<this>");
    int $i$f$commonWriteDecimalLong = 0;
    long l = v;
    if (l == 0L)
      return $this$commonWriteDecimalLong.writeByte(48); 
    boolean negative = false;
    if (l < 0L) {
      l = -l;
      if (l < 0L)
        return $this$commonWriteDecimalLong.writeUtf8("-9223372036854775808"); 
      negative = true;
    } 
    int width = (l < 100000000L) ? ((l < 10000L) ? ((l < 100L) ? ((l < 10L) ? 1 : 2) : ((l < 1000L) ? 3 : 4)) : ((l < 1000000L) ? ((l < 100000L) ? 5 : 6) : ((l < 10000000L) ? 7 : 8))) : ((l < 1000000000000L) ? ((l < 10000000000L) ? ((l < 1000000000L) ? 9 : 10) : ((l < 100000000000L) ? 11 : 12)) : ((l < 1000000000000000L) ? ((l < 10000000000000L) ? 13 : ((l < 100000000000000L) ? 14 : 15)) : ((l < 100000000000000000L) ? ((l < 10000000000000000L) ? 16 : 17) : ((l < 1000000000000000000L) ? 18 : 19))));
    if (negative)
      width++; 
    Segment tail = $this$commonWriteDecimalLong.writableSegment$okio(width);
    byte[] data = tail.data;
    int pos = tail.limit + width;
    while (l != 0L) {
      int digit = (int)(l % 10L);
      data[--pos] = getHEX_DIGIT_BYTES()[digit];
      l /= 10L;
    } 
    if (negative)
      data[--pos] = 45; 
    tail.limit += width;
    $this$commonWriteDecimalLong.setSize$okio($this$commonWriteDecimalLong.size() + width);
    return $this$commonWriteDecimalLong;
  }
  
  @NotNull
  public static final Buffer commonWriteHexadecimalUnsignedLong(@NotNull Buffer $this$commonWriteHexadecimalUnsignedLong, long v) {
    Intrinsics.checkNotNullParameter($this$commonWriteHexadecimalUnsignedLong, "<this>");
    int $i$f$commonWriteHexadecimalUnsignedLong = 0;
    long l1 = v;
    if (l1 == 0L)
      return $this$commonWriteHexadecimalUnsignedLong.writeByte(48); 
    long x = l1;
    x |= x >>> 1L;
    x |= x >>> 2L;
    x |= x >>> 4L;
    x |= x >>> 8L;
    x |= x >>> 16L;
    x |= x >>> 32L;
    x -= x >>> 1L & 0x5555555555555555L;
    x = (x >>> 2L & 0x3333333333333333L) + (x & 0x3333333333333333L);
    x = (x >>> 4L) + x & 0xF0F0F0F0F0F0F0FL;
    x += x >>> 8L;
    x += x >>> 16L;
    x = (x & 0x3FL) + (x >>> 32L & 0x3FL);
    int width = (int)((x + 3L) / 4L);
    Segment tail = $this$commonWriteHexadecimalUnsignedLong.writableSegment$okio(width);
    byte[] data = tail.data;
    int pos = tail.limit + width - 1;
    int start = tail.limit;
    while (pos >= start) {
      data[pos] = getHEX_DIGIT_BYTES()[(int)(l1 & 0xFL)];
      l1 >>>= 4L;
      pos--;
    } 
    tail.limit += width;
    $this$commonWriteHexadecimalUnsignedLong.setSize$okio($this$commonWriteHexadecimalUnsignedLong.size() + width);
    return $this$commonWriteHexadecimalUnsignedLong;
  }
  
  @NotNull
  public static final Segment commonWritableSegment(@NotNull Buffer $this$commonWritableSegment, int minimumCapacity) {
    Intrinsics.checkNotNullParameter($this$commonWritableSegment, "<this>");
    int $i$f$commonWritableSegment = 0;
    if (!((minimumCapacity >= 1 && minimumCapacity <= 8192) ? 1 : 0)) {
      int $i$a$-require--Buffer$commonWritableSegment$1 = 0;
      String str = "unexpected capacity";
      throw new IllegalArgumentException(str.toString());
    } 
    if ($this$commonWritableSegment.head == null) {
      Segment result = SegmentPool.take();
      $this$commonWritableSegment.head = result;
      result.prev = result;
      result.next = result;
      return result;
    } 
    Intrinsics.checkNotNull($this$commonWritableSegment.head);
    Segment tail = $this$commonWritableSegment.head.prev;
    Intrinsics.checkNotNull(tail);
    if (tail.limit + minimumCapacity > 8192 || !tail.owner)
      tail = tail.push(SegmentPool.take()); 
    return tail;
  }
  
  @NotNull
  public static final Buffer commonWrite(@NotNull Buffer $this$commonWrite, @NotNull byte[] source) {
    Intrinsics.checkNotNullParameter($this$commonWrite, "<this>");
    Intrinsics.checkNotNullParameter(source, "source");
    int $i$f$commonWrite = 0;
    return $this$commonWrite.write(source, 0, source.length);
  }
  
  @NotNull
  public static final Buffer commonWrite(@NotNull Buffer $this$commonWrite, @NotNull byte[] source, int offset, int byteCount) {
    Intrinsics.checkNotNullParameter($this$commonWrite, "<this>");
    Intrinsics.checkNotNullParameter(source, "source");
    int $i$f$commonWrite = 0, i = offset;
    -SegmentedByteString.checkOffsetAndCount(source.length, i, byteCount);
    int limit = i + byteCount;
    while (i < limit) {
      Segment tail = $this$commonWrite.writableSegment$okio(1);
      int toCopy = Math.min(limit - i, 8192 - tail.limit);
      ArraysKt.copyInto(source, tail.data, tail.limit, i, i + toCopy);
      i += toCopy;
      tail.limit += toCopy;
    } 
    $this$commonWrite.setSize$okio($this$commonWrite.size() + byteCount);
    return $this$commonWrite;
  }
  
  @NotNull
  public static final byte[] commonReadByteArray(@NotNull Buffer $this$commonReadByteArray) {
    Intrinsics.checkNotNullParameter($this$commonReadByteArray, "<this>");
    int $i$f$commonReadByteArray = 0;
    return $this$commonReadByteArray.readByteArray($this$commonReadByteArray.size());
  }
  
  @NotNull
  public static final byte[] commonReadByteArray(@NotNull Buffer $this$commonReadByteArray, long byteCount) {
    Intrinsics.checkNotNullParameter($this$commonReadByteArray, "<this>");
    int $i$f$commonReadByteArray = 0;
    if (!((byteCount >= 0L && byteCount <= 2147483647L) ? 1 : 0)) {
      int $i$a$-require--Buffer$commonReadByteArray$1 = 0;
      String str = "byteCount: " + byteCount;
      throw new IllegalArgumentException(str.toString());
    } 
    if ($this$commonReadByteArray.size() < byteCount)
      throw new EOFException(); 
    byte[] result = new byte[(int)byteCount];
    $this$commonReadByteArray.readFully(result);
    return result;
  }
  
  public static final int commonRead(@NotNull Buffer $this$commonRead, @NotNull byte[] sink) {
    Intrinsics.checkNotNullParameter($this$commonRead, "<this>");
    Intrinsics.checkNotNullParameter(sink, "sink");
    int $i$f$commonRead = 0;
    return $this$commonRead.read(sink, 0, sink.length);
  }
  
  public static final void commonReadFully(@NotNull Buffer $this$commonReadFully, @NotNull byte[] sink) {
    Intrinsics.checkNotNullParameter($this$commonReadFully, "<this>");
    Intrinsics.checkNotNullParameter(sink, "sink");
    int $i$f$commonReadFully = 0, offset = 0;
    while (offset < sink.length) {
      int read = $this$commonReadFully.read(sink, offset, sink.length - offset);
      if (read == -1)
        throw new EOFException(); 
      offset += read;
    } 
  }
  
  public static final int commonRead(@NotNull Buffer $this$commonRead, @NotNull byte[] sink, int offset, int byteCount) {
    Segment s;
    Intrinsics.checkNotNullParameter($this$commonRead, "<this>");
    Intrinsics.checkNotNullParameter(sink, "sink");
    int $i$f$commonRead = 0;
    -SegmentedByteString.checkOffsetAndCount(sink.length, offset, byteCount);
    if ($this$commonRead.head == null)
      return -1; 
    int toCopy = Math.min(byteCount, s.limit - s.pos);
    ArraysKt.copyInto(s.data, sink, offset, s.pos, s.pos + toCopy);
    s.pos += toCopy;
    $this$commonRead.setSize$okio($this$commonRead.size() - toCopy);
    if (s.pos == s.limit) {
      $this$commonRead.head = s.pop();
      SegmentPool.recycle(s);
    } 
    return toCopy;
  }
  
  public static final long commonReadDecimalLong(@NotNull Buffer $this$commonReadDecimalLong) {
    Intrinsics.checkNotNullParameter($this$commonReadDecimalLong, "<this>");
    int $i$f$commonReadDecimalLong = 0;
    if ($this$commonReadDecimalLong.size() == 0L)
      throw new EOFException(); 
    long value = 0L;
    int seen = 0;
    boolean negative = false;
    boolean done = false;
    long overflowDigit = -7L;
    do {
      Intrinsics.checkNotNull($this$commonReadDecimalLong.head);
      Segment segment = $this$commonReadDecimalLong.head;
      byte[] data = segment.data;
      int pos = segment.pos;
      int limit = segment.limit;
      while (pos < limit) {
        byte b = data[pos];
        if (b >= 48 && b <= 57) {
          int digit = 48 - b;
          if (value < -922337203685477580L || (value == -922337203685477580L && digit < overflowDigit)) {
            Buffer buffer = (new Buffer()).writeDecimalLong(value).writeByte(b);
            if (!negative)
              buffer.readByte(); 
            throw new NumberFormatException("Number too large: " + buffer.readUtf8());
          } 
          value *= 10L;
          value += digit;
        } else if (b == 45 && seen == 0) {
          negative = true;
          overflowDigit--;
        } else {
          done = true;
          break;
        } 
        pos++;
        seen++;
      } 
      if (pos == limit) {
        $this$commonReadDecimalLong.head = segment.pop();
        SegmentPool.recycle(segment);
      } else {
        segment.pos = pos;
      } 
    } while (!done && $this$commonReadDecimalLong.head != null);
    $this$commonReadDecimalLong.setSize$okio($this$commonReadDecimalLong.size() - seen);
    int minimumSeen = negative ? 2 : 1;
    if (seen < minimumSeen) {
      if ($this$commonReadDecimalLong.size() == 0L)
        throw new EOFException(); 
      String expected = negative ? "Expected a digit" : "Expected a digit or '-'";
      throw new NumberFormatException(expected + " but was 0x" + -SegmentedByteString.toHexString($this$commonReadDecimalLong.getByte(0L)));
    } 
    return negative ? value : -value;
  }
  
  public static final long commonReadHexadecimalUnsignedLong(@NotNull Buffer $this$commonReadHexadecimalUnsignedLong) {
    Intrinsics.checkNotNullParameter($this$commonReadHexadecimalUnsignedLong, "<this>");
    int $i$f$commonReadHexadecimalUnsignedLong = 0;
    if ($this$commonReadHexadecimalUnsignedLong.size() == 0L)
      throw new EOFException(); 
    long value = 0L;
    int seen = 0;
    boolean done = false;
    do {
      Intrinsics.checkNotNull($this$commonReadHexadecimalUnsignedLong.head);
      Segment segment = $this$commonReadHexadecimalUnsignedLong.head;
      byte[] data = segment.data;
      int pos = segment.pos;
      int limit = segment.limit;
      while (pos < limit) {
        int digit = 0;
        byte b = data[pos];
        if (b >= 48 && b <= 57) {
          digit = b - 48;
        } else if (b >= 97 && b <= 102) {
          digit = b - 97 + 10;
        } else if (b >= 65 && b <= 70) {
          digit = b - 65 + 10;
        } else {
          if (seen == 0)
            throw new NumberFormatException("Expected leading [0-9a-fA-F] character but was 0x" + -SegmentedByteString.toHexString(b)); 
          done = true;
          break;
        } 
        if ((value & 0xF000000000000000L) != 0L) {
          Buffer buffer = (new Buffer()).writeHexadecimalUnsignedLong(value).writeByte(b);
          throw new NumberFormatException("Number too large: " + buffer.readUtf8());
        } 
        value <<= 4L;
        value |= digit;
        pos++;
        seen++;
      } 
      if (pos == limit) {
        $this$commonReadHexadecimalUnsignedLong.head = segment.pop();
        SegmentPool.recycle(segment);
      } else {
        segment.pos = pos;
      } 
    } while (!done && $this$commonReadHexadecimalUnsignedLong.head != null);
    $this$commonReadHexadecimalUnsignedLong.setSize$okio($this$commonReadHexadecimalUnsignedLong.size() - seen);
    return value;
  }
  
  @NotNull
  public static final ByteString commonReadByteString(@NotNull Buffer $this$commonReadByteString) {
    Intrinsics.checkNotNullParameter($this$commonReadByteString, "<this>");
    int $i$f$commonReadByteString = 0;
    return $this$commonReadByteString.readByteString($this$commonReadByteString.size());
  }
  
  @NotNull
  public static final ByteString commonReadByteString(@NotNull Buffer $this$commonReadByteString, long byteCount) {
    Intrinsics.checkNotNullParameter($this$commonReadByteString, "<this>");
    int $i$f$commonReadByteString = 0;
    if (!((byteCount >= 0L && byteCount <= 2147483647L) ? 1 : 0)) {
      int $i$a$-require--Buffer$commonReadByteString$1 = 0;
      String str = "byteCount: " + byteCount;
      throw new IllegalArgumentException(str.toString());
    } 
    if ($this$commonReadByteString.size() < byteCount)
      throw new EOFException(); 
    if (byteCount >= 4096L) {
      ByteString byteString1 = $this$commonReadByteString.snapshot((int)byteCount), it = byteString1;
      int $i$a$-also--Buffer$commonReadByteString$2 = 0;
      $this$commonReadByteString.skip(byteCount);
      return byteString1;
    } 
    return new ByteString($this$commonReadByteString.readByteArray(byteCount));
  }
  
  public static final int commonSelect(@NotNull Buffer $this$commonSelect, @NotNull Options options) {
    Intrinsics.checkNotNullParameter($this$commonSelect, "<this>");
    Intrinsics.checkNotNullParameter(options, "options");
    int $i$f$commonSelect = 0, index = selectPrefix$default($this$commonSelect, options, false, 2, null);
    if (index == -1)
      return -1; 
    int selectedSize = options.getByteStrings$okio()[index].size();
    $this$commonSelect.skip(selectedSize);
    return index;
  }
  
  public static final void commonReadFully(@NotNull Buffer $this$commonReadFully, @NotNull Buffer sink, long byteCount) {
    Intrinsics.checkNotNullParameter($this$commonReadFully, "<this>");
    Intrinsics.checkNotNullParameter(sink, "sink");
    int $i$f$commonReadFully = 0;
    if ($this$commonReadFully.size() < byteCount) {
      sink.write($this$commonReadFully, $this$commonReadFully.size());
      throw new EOFException();
    } 
    sink.write($this$commonReadFully, byteCount);
  }
  
  public static final long commonReadAll(@NotNull Buffer $this$commonReadAll, @NotNull Sink sink) {
    Intrinsics.checkNotNullParameter($this$commonReadAll, "<this>");
    Intrinsics.checkNotNullParameter(sink, "sink");
    int $i$f$commonReadAll = 0;
    long byteCount = $this$commonReadAll.size();
    if (byteCount > 0L)
      sink.write($this$commonReadAll, byteCount); 
    return byteCount;
  }
  
  @NotNull
  public static final String commonReadUtf8(@NotNull Buffer $this$commonReadUtf8, long byteCount) {
    Intrinsics.checkNotNullParameter($this$commonReadUtf8, "<this>");
    int $i$f$commonReadUtf8 = 0;
    if (!((byteCount >= 0L && byteCount <= 2147483647L) ? 1 : 0)) {
      int $i$a$-require--Buffer$commonReadUtf8$1 = 0;
      String str = "byteCount: " + byteCount;
      throw new IllegalArgumentException(str.toString());
    } 
    if ($this$commonReadUtf8.size() < byteCount)
      throw new EOFException(); 
    if (byteCount == 0L)
      return ""; 
    Intrinsics.checkNotNull($this$commonReadUtf8.head);
    Segment s = $this$commonReadUtf8.head;
    if (s.pos + byteCount > s.limit)
      return _Utf8Kt.commonToUtf8String$default($this$commonReadUtf8.readByteArray(byteCount), 0, 0, 3, null); 
    String result = _Utf8Kt.commonToUtf8String(s.data, s.pos, s.pos + (int)byteCount);
    s.pos += (int)byteCount;
    $this$commonReadUtf8.setSize$okio($this$commonReadUtf8.size() - byteCount);
    if (s.pos == s.limit) {
      $this$commonReadUtf8.head = s.pop();
      SegmentPool.recycle(s);
    } 
    return result;
  }
  
  @Nullable
  public static final String commonReadUtf8Line(@NotNull Buffer $this$commonReadUtf8Line) {
    Intrinsics.checkNotNullParameter($this$commonReadUtf8Line, "<this>");
    int $i$f$commonReadUtf8Line = 0;
    long newline = $this$commonReadUtf8Line.indexOf((byte)10);
    return (newline != -1L) ? readUtf8Line($this$commonReadUtf8Line, newline) : (($this$commonReadUtf8Line.size() != 0L) ? $this$commonReadUtf8Line.readUtf8($this$commonReadUtf8Line.size()) : null);
  }
  
  @NotNull
  public static final String commonReadUtf8LineStrict(@NotNull Buffer $this$commonReadUtf8LineStrict, long limit) {
    Intrinsics.checkNotNullParameter($this$commonReadUtf8LineStrict, "<this>");
    int $i$f$commonReadUtf8LineStrict = 0;
    if (!((limit >= 0L) ? 1 : 0)) {
      int $i$a$-require--Buffer$commonReadUtf8LineStrict$1 = 0;
      String str = "limit < 0: " + limit;
      throw new IllegalArgumentException(str.toString());
    } 
    long scanLength = (limit == Long.MAX_VALUE) ? Long.MAX_VALUE : (limit + 1L);
    long newline = $this$commonReadUtf8LineStrict.indexOf((byte)10, 0L, scanLength);
    if (newline != -1L)
      return readUtf8Line($this$commonReadUtf8LineStrict, newline); 
    if (scanLength < $this$commonReadUtf8LineStrict.size() && $this$commonReadUtf8LineStrict.getByte(scanLength - 1L) == 13 && $this$commonReadUtf8LineStrict.getByte(scanLength) == 10)
      return readUtf8Line($this$commonReadUtf8LineStrict, scanLength); 
    Buffer data = new Buffer();
    byte b = 32;
    long b$iv = $this$commonReadUtf8LineStrict.size();
    int $i$f$minOf = 0;
    $this$commonReadUtf8LineStrict.copyTo(data, 0L, Math.min(b, b$iv));
    throw new EOFException("\\n not found: limit=" + Math.min($this$commonReadUtf8LineStrict.size(), limit) + " content=" + data.readByteString().hex() + '…');
  }
  
  public static final int commonReadUtf8CodePoint(@NotNull Buffer $this$commonReadUtf8CodePoint) {
    Intrinsics.checkNotNullParameter($this$commonReadUtf8CodePoint, "<this>");
    int $i$f$commonReadUtf8CodePoint = 0;
    if ($this$commonReadUtf8CodePoint.size() == 0L)
      throw new EOFException(); 
    byte b0 = $this$commonReadUtf8CodePoint.getByte(0L);
    int codePoint = 0, byteCount = 0, min = 0;
    byte b = b0;
    int other$iv = 128, $i$f$and = 0;
    if ((b & other$iv) == 0) {
      byte b1 = b0;
      other$iv = 127;
      $i$f$and = 0;
      codePoint = b1 & other$iv;
      byteCount = 1;
      min = 0;
    } 
    byte $this$and$iv = b0;
    other$iv = 224;
    $i$f$and = 0;
    if (($this$and$iv & other$iv) == 192) {
      $this$and$iv = b0;
      other$iv = 31;
      $i$f$and = 0;
      codePoint = $this$and$iv & other$iv;
      byteCount = 2;
      min = 128;
    } 
    $this$and$iv = b0;
    other$iv = 240;
    $i$f$and = 0;
    if (($this$and$iv & other$iv) == 224) {
      $this$and$iv = b0;
      other$iv = 15;
      $i$f$and = 0;
      codePoint = $this$and$iv & other$iv;
      byteCount = 3;
      min = 2048;
    } 
    $this$and$iv = b0;
    other$iv = 248;
    $i$f$and = 0;
    if (($this$and$iv & other$iv) == 240) {
      $this$and$iv = b0;
      other$iv = 7;
      $i$f$and = 0;
      codePoint = $this$and$iv & other$iv;
      byteCount = 4;
      min = 65536;
    } 
    $this$commonReadUtf8CodePoint.skip(1L);
    return 65533;
  }
  
  @NotNull
  public static final Buffer commonWriteUtf8(@NotNull Buffer $this$commonWriteUtf8, @NotNull String string, int beginIndex, int endIndex) {
    Intrinsics.checkNotNullParameter($this$commonWriteUtf8, "<this>");
    Intrinsics.checkNotNullParameter(string, "string");
    int $i$f$commonWriteUtf8 = 0;
    if (!((beginIndex >= 0) ? 1 : 0)) {
      int $i$a$-require--Buffer$commonWriteUtf8$1 = 0;
      String str = "beginIndex < 0: " + beginIndex;
      throw new IllegalArgumentException(str.toString());
    } 
    if (!((endIndex >= beginIndex) ? 1 : 0)) {
      int $i$a$-require--Buffer$commonWriteUtf8$2 = 0;
      String str = "endIndex < beginIndex: " + endIndex + " < " + beginIndex;
      throw new IllegalArgumentException(str.toString());
    } 
    if (!((endIndex <= string.length()) ? 1 : 0)) {
      int $i$a$-require--Buffer$commonWriteUtf8$3 = 0;
      String str = "endIndex > string.length: " + endIndex + " > " + string.length();
      throw new IllegalArgumentException(str.toString());
    } 
    int i = beginIndex;
    while (i < endIndex) {
      int c = string.charAt(i);
      if (c < 128) {
        Segment tail = $this$commonWriteUtf8.writableSegment$okio(1);
        byte[] data = tail.data;
        int segmentOffset = tail.limit - i;
        int runLimit = Math.min(endIndex, 8192 - segmentOffset);
        data[segmentOffset + i++] = (byte)c;
        while (i < runLimit) {
          c = string.charAt(i);
          if (c < 128)
            data[segmentOffset + i++] = (byte)c; 
        } 
        int runSize = i + segmentOffset - tail.limit;
        tail.limit += runSize;
        $this$commonWriteUtf8.setSize$okio($this$commonWriteUtf8.size() + runSize);
        continue;
      } 
      if (c < 2048) {
        Segment tail = $this$commonWriteUtf8.writableSegment$okio(2);
        tail.data[tail.limit] = (byte)(c >> 6 | 0xC0);
        tail.data[tail.limit + 1] = (byte)(c & 0x3F | 0x80);
        tail.limit += 2;
        $this$commonWriteUtf8.setSize$okio($this$commonWriteUtf8.size() + 2L);
        i++;
        continue;
      } 
      if (c < 55296 || c > 57343) {
        Segment tail = $this$commonWriteUtf8.writableSegment$okio(3);
        tail.data[tail.limit] = (byte)(c >> 12 | 0xE0);
        tail.data[tail.limit + 1] = (byte)(c >> 6 & 0x3F | 0x80);
        tail.data[tail.limit + 2] = (byte)(c & 0x3F | 0x80);
        tail.limit += 3;
        $this$commonWriteUtf8.setSize$okio($this$commonWriteUtf8.size() + 3L);
        i++;
        continue;
      } 
      int low = (i + 1 < endIndex) ? string.charAt(i + 1) : 0;
      if (c <= 56319)
        if ((56320 <= low) ? ((low < 57344)) : false) {
          int codePoint = 65536 + ((c & 0x3FF) << 10 | low & 0x3FF);
          Segment tail = $this$commonWriteUtf8.writableSegment$okio(4);
          tail.data[tail.limit] = (byte)(codePoint >> 18 | 0xF0);
          tail.data[tail.limit + 1] = (byte)(codePoint >> 12 & 0x3F | 0x80);
          tail.data[tail.limit + 2] = (byte)(codePoint >> 6 & 0x3F | 0x80);
          tail.data[tail.limit + 3] = (byte)(codePoint & 0x3F | 0x80);
          tail.limit += 4;
          $this$commonWriteUtf8.setSize$okio($this$commonWriteUtf8.size() + 4L);
          i += 2;
        }  
      $this$commonWriteUtf8.writeByte(63);
      i++;
    } 
    return $this$commonWriteUtf8;
  }
  
  @NotNull
  public static final Buffer commonWriteUtf8CodePoint(@NotNull Buffer $this$commonWriteUtf8CodePoint, int codePoint) {
    Intrinsics.checkNotNullParameter($this$commonWriteUtf8CodePoint, "<this>");
    int $i$f$commonWriteUtf8CodePoint = 0;
    if (codePoint < 128) {
      $this$commonWriteUtf8CodePoint.writeByte(codePoint);
    } else if (codePoint < 2048) {
      Segment tail = $this$commonWriteUtf8CodePoint.writableSegment$okio(2);
      tail.data[tail.limit] = (byte)(codePoint >> 6 | 0xC0);
      tail.data[tail.limit + 1] = (byte)(codePoint & 0x3F | 0x80);
      tail.limit += 2;
      $this$commonWriteUtf8CodePoint.setSize$okio($this$commonWriteUtf8CodePoint.size() + 2L);
    } else if ((55296 <= codePoint) ? ((codePoint < 57344)) : false) {
      $this$commonWriteUtf8CodePoint.writeByte(63);
    } else if (codePoint < 65536) {
      Segment tail = $this$commonWriteUtf8CodePoint.writableSegment$okio(3);
      tail.data[tail.limit] = (byte)(codePoint >> 12 | 0xE0);
      tail.data[tail.limit + 1] = (byte)(codePoint >> 6 & 0x3F | 0x80);
      tail.data[tail.limit + 2] = (byte)(codePoint & 0x3F | 0x80);
      tail.limit += 3;
      $this$commonWriteUtf8CodePoint.setSize$okio($this$commonWriteUtf8CodePoint.size() + 3L);
    } else if (codePoint <= 1114111) {
      Segment tail = $this$commonWriteUtf8CodePoint.writableSegment$okio(4);
      tail.data[tail.limit] = (byte)(codePoint >> 18 | 0xF0);
      tail.data[tail.limit + 1] = (byte)(codePoint >> 12 & 0x3F | 0x80);
      tail.data[tail.limit + 2] = (byte)(codePoint >> 6 & 0x3F | 0x80);
      tail.data[tail.limit + 3] = (byte)(codePoint & 0x3F | 0x80);
      tail.limit += 4;
      $this$commonWriteUtf8CodePoint.setSize$okio($this$commonWriteUtf8CodePoint.size() + 4L);
    } else {
      throw new IllegalArgumentException("Unexpected code point: 0x" + -SegmentedByteString.toHexString(codePoint));
    } 
    return $this$commonWriteUtf8CodePoint;
  }
  
  public static final long commonWriteAll(@NotNull Buffer $this$commonWriteAll, @NotNull Source source) {
    Intrinsics.checkNotNullParameter($this$commonWriteAll, "<this>");
    Intrinsics.checkNotNullParameter(source, "source");
    int $i$f$commonWriteAll = 0;
    long totalBytesRead = 0L;
    while (true) {
      long readCount = source.read($this$commonWriteAll, 8192L);
      if (readCount != -1L) {
        totalBytesRead += readCount;
        continue;
      } 
      break;
    } 
    return totalBytesRead;
  }
  
  @NotNull
  public static final Buffer commonWrite(@NotNull Buffer $this$commonWrite, @NotNull Source source, long byteCount) {
    Intrinsics.checkNotNullParameter($this$commonWrite, "<this>");
    Intrinsics.checkNotNullParameter(source, "source");
    int $i$f$commonWrite = 0;
    long l = byteCount;
    while (l > 0L) {
      long read = source.read($this$commonWrite, l);
      if (read == -1L)
        throw new EOFException(); 
      l -= read;
    } 
    return $this$commonWrite;
  }
  
  @NotNull
  public static final Buffer commonWriteByte(@NotNull Buffer $this$commonWriteByte, int b) {
    Intrinsics.checkNotNullParameter($this$commonWriteByte, "<this>");
    int $i$f$commonWriteByte = 0;
    Segment tail = $this$commonWriteByte.writableSegment$okio(1);
    int i = tail.limit;
    tail.limit = i + 1;
    tail.data[i] = (byte)b;
    $this$commonWriteByte.setSize$okio($this$commonWriteByte.size() + 1L);
    return $this$commonWriteByte;
  }
  
  @NotNull
  public static final Buffer commonWriteShort(@NotNull Buffer $this$commonWriteShort, int s) {
    Intrinsics.checkNotNullParameter($this$commonWriteShort, "<this>");
    int $i$f$commonWriteShort = 0;
    Segment tail = $this$commonWriteShort.writableSegment$okio(2);
    byte[] data = tail.data;
    int limit = tail.limit;
    data[limit++] = (byte)(s >>> 8 & 0xFF);
    data[limit++] = (byte)(s & 0xFF);
    tail.limit = limit;
    $this$commonWriteShort.setSize$okio($this$commonWriteShort.size() + 2L);
    return $this$commonWriteShort;
  }
  
  @NotNull
  public static final Buffer commonWriteInt(@NotNull Buffer $this$commonWriteInt, int i) {
    Intrinsics.checkNotNullParameter($this$commonWriteInt, "<this>");
    int $i$f$commonWriteInt = 0;
    Segment tail = $this$commonWriteInt.writableSegment$okio(4);
    byte[] data = tail.data;
    int limit = tail.limit;
    data[limit++] = (byte)(i >>> 24 & 0xFF);
    data[limit++] = (byte)(i >>> 16 & 0xFF);
    data[limit++] = (byte)(i >>> 8 & 0xFF);
    data[limit++] = (byte)(i & 0xFF);
    tail.limit = limit;
    $this$commonWriteInt.setSize$okio($this$commonWriteInt.size() + 4L);
    return $this$commonWriteInt;
  }
  
  @NotNull
  public static final Buffer commonWriteLong(@NotNull Buffer $this$commonWriteLong, long v) {
    Intrinsics.checkNotNullParameter($this$commonWriteLong, "<this>");
    int $i$f$commonWriteLong = 0;
    Segment tail = $this$commonWriteLong.writableSegment$okio(8);
    byte[] data = tail.data;
    int limit = tail.limit;
    data[limit++] = (byte)(int)(v >>> 56L & 0xFFL);
    data[limit++] = (byte)(int)(v >>> 48L & 0xFFL);
    data[limit++] = (byte)(int)(v >>> 40L & 0xFFL);
    data[limit++] = (byte)(int)(v >>> 32L & 0xFFL);
    data[limit++] = (byte)(int)(v >>> 24L & 0xFFL);
    data[limit++] = (byte)(int)(v >>> 16L & 0xFFL);
    data[limit++] = (byte)(int)(v >>> 8L & 0xFFL);
    data[limit++] = (byte)(int)(v & 0xFFL);
    tail.limit = limit;
    $this$commonWriteLong.setSize$okio($this$commonWriteLong.size() + 8L);
    return $this$commonWriteLong;
  }
  
  public static final void commonWrite(@NotNull Buffer $this$commonWrite, @NotNull Buffer source, long byteCount) {
    Intrinsics.checkNotNullParameter($this$commonWrite, "<this>");
    Intrinsics.checkNotNullParameter(source, "source");
    int $i$f$commonWrite = 0;
    long l = byteCount;
    if (!((source != $this$commonWrite) ? 1 : 0)) {
      int $i$a$-require--Buffer$commonWrite$1 = 0;
      String str = "source == this";
      throw new IllegalArgumentException(str.toString());
    } 
    -SegmentedByteString.checkOffsetAndCount(source.size(), 0L, l);
    while (l > 0L) {
      Intrinsics.checkNotNull(source.head);
      Intrinsics.checkNotNull(source.head);
      if (l < (source.head.limit - source.head.pos)) {
        Intrinsics.checkNotNull($this$commonWrite.head);
        Segment tail = ($this$commonWrite.head != null) ? $this$commonWrite.head.prev : null;
        if (tail != null && tail.owner && l + tail.limit - (tail.shared ? 0L : tail.pos) <= 8192L) {
          Intrinsics.checkNotNull(source.head);
          source.head.writeTo(tail, (int)l);
          source.setSize$okio(source.size() - l);
          $this$commonWrite.setSize$okio($this$commonWrite.size() + l);
          return;
        } 
        Intrinsics.checkNotNull(source.head);
        source.head = source.head.split((int)l);
      } 
      Segment segmentToMove = source.head;
      Intrinsics.checkNotNull(segmentToMove);
      long movedByteCount = (segmentToMove.limit - segmentToMove.pos);
      source.head = segmentToMove.pop();
      if ($this$commonWrite.head == null) {
        $this$commonWrite.head = segmentToMove;
        segmentToMove.prev = segmentToMove;
        segmentToMove.next = segmentToMove.prev;
      } else {
        Intrinsics.checkNotNull($this$commonWrite.head);
        Segment tail = $this$commonWrite.head.prev;
        Intrinsics.checkNotNull(tail);
        tail = tail.push(segmentToMove);
        tail.compact();
      } 
      source.setSize$okio(source.size() - movedByteCount);
      $this$commonWrite.setSize$okio($this$commonWrite.size() + movedByteCount);
      l -= movedByteCount;
    } 
  }
  
  public static final long commonRead(@NotNull Buffer $this$commonRead, @NotNull Buffer sink, long byteCount) {
    Intrinsics.checkNotNullParameter($this$commonRead, "<this>");
    Intrinsics.checkNotNullParameter(sink, "sink");
    int $i$f$commonRead = 0;
    long l = 0L;
    l = byteCount;
    if (!((l >= 0L) ? 1 : 0)) {
      int $i$a$-require--Buffer$commonRead$1 = 0;
      String str = "byteCount < 0: " + l;
      throw new IllegalArgumentException(str.toString());
    } 
    if ($this$commonRead.size() == 0L)
      return -1L; 
    if (l > $this$commonRead.size())
      l = $this$commonRead.size(); 
    sink.write($this$commonRead, l);
    return l;
  }
  
  public static final long commonIndexOf(@NotNull Buffer $this$commonIndexOf, byte b, long fromIndex, long toIndex) {
    Segment s$iv, segment1;
    Intrinsics.checkNotNullParameter($this$commonIndexOf, "<this>");
    int $i$f$commonIndexOf = 0;
    long l2 = 0L;
    l2 = fromIndex;
    long l3 = 0L;
    l3 = toIndex;
    if (!((0L <= l2) ? ((l2 <= l3) ? 1 : 0) : 0)) {
      int $i$a$-require--Buffer$commonIndexOf$1 = 0;
      String str = "size=" + $this$commonIndexOf.size() + " fromIndex=" + l2 + " toIndex=" + l3;
      throw new IllegalArgumentException(str.toString());
    } 
    if (l3 > $this$commonIndexOf.size())
      l3 = $this$commonIndexOf.size(); 
    if (l2 == l3)
      return -1L; 
    Buffer buffer = $this$commonIndexOf;
    long fromIndex$iv = l2;
    int $i$f$seek = 0;
    if (buffer.head == null) {
      long l = -1L;
      Segment segment = null;
      int i = 0;
      return -1L;
    } 
    if (buffer.size() - fromIndex$iv < fromIndex$iv) {
      long l5 = buffer.size();
      while (l5 > fromIndex$iv) {
        Intrinsics.checkNotNull(s$iv.prev);
        s$iv = s$iv.prev;
        l5 -= (s$iv.limit - s$iv.pos);
      } 
      long l4 = l5;
      Segment segment = s$iv;
      int i = 0;
      if (segment == null)
        return -1L; 
    } 
    long offset$iv = 0L;
    while (true) {
      long nextOffset$iv = offset$iv + (s$iv.limit - s$iv.pos);
      if (nextOffset$iv <= fromIndex$iv) {
        Intrinsics.checkNotNull(s$iv.next);
        s$iv = s$iv.next;
        offset$iv = nextOffset$iv;
        continue;
      } 
      break;
    } 
    long l1 = offset$iv;
    Segment s = s$iv;
    int $i$a$-seek--Buffer$commonIndexOf$2 = 0;
    if (s == null)
      return -1L; 
    long offset = l1;
    while (offset < l3) {
      byte[] data = segment1.data;
      int limit = (int)Math.min(segment1.limit, segment1.pos + l3 - offset);
      int pos = (int)(segment1.pos + l2 - offset);
      while (pos < limit) {
        if (data[pos] == b)
          return (pos - segment1.pos) + offset; 
        pos++;
      } 
      offset += (segment1.limit - segment1.pos);
      l2 = offset;
      Intrinsics.checkNotNull(segment1.next);
      segment1 = segment1.next;
    } 
    return -1L;
  }
  
  public static final long commonIndexOf(@NotNull Buffer $this$commonIndexOf, @NotNull ByteString bytes, long fromIndex) {
    Segment s$iv, segment1;
    Intrinsics.checkNotNullParameter($this$commonIndexOf, "<this>");
    Intrinsics.checkNotNullParameter(bytes, "bytes");
    int $i$f$commonIndexOf = 0;
    long l2 = 0L;
    l2 = fromIndex;
    if (!((bytes.size() > 0) ? 1 : 0)) {
      int $i$a$-require--Buffer$commonIndexOf$3 = 0;
      String str = "bytes is empty";
      throw new IllegalArgumentException(str.toString());
    } 
    if (!((l2 >= 0L) ? 1 : 0)) {
      int $i$a$-require--Buffer$commonIndexOf$4 = 0;
      String str = "fromIndex < 0: " + l2;
      throw new IllegalArgumentException(str.toString());
    } 
    Buffer buffer = $this$commonIndexOf;
    long fromIndex$iv = l2;
    int $i$f$seek = 0;
    if (buffer.head == null) {
      long l = -1L;
      Segment segment = null;
      int i = 0;
      return -1L;
    } 
    if (buffer.size() - fromIndex$iv < fromIndex$iv) {
      long l4 = buffer.size();
      while (l4 > fromIndex$iv) {
        Intrinsics.checkNotNull(s$iv.prev);
        s$iv = s$iv.prev;
        l4 -= (s$iv.limit - s$iv.pos);
      } 
      long l3 = l4;
      Segment segment = s$iv;
      int i = 0;
      if (segment == null)
        return -1L; 
    } 
    long offset$iv = 0L;
    while (true) {
      long nextOffset$iv = offset$iv + (s$iv.limit - s$iv.pos);
      if (nextOffset$iv <= fromIndex$iv) {
        Intrinsics.checkNotNull(s$iv.next);
        s$iv = s$iv.next;
        offset$iv = nextOffset$iv;
        continue;
      } 
      break;
    } 
    long l1 = offset$iv;
    Segment s = s$iv;
    int $i$a$-seek--Buffer$commonIndexOf$5 = 0;
    if (s == null)
      return -1L; 
    long offset = l1;
    byte[] targetByteArray = bytes.internalArray$okio();
    byte b0 = targetByteArray[0];
    int bytesSize = bytes.size();
    long resultLimit = $this$commonIndexOf.size() - bytesSize + 1L;
    while (offset < resultLimit) {
      byte[] data = segment1.data;
      int i = segment1.limit;
      long b$iv = segment1.pos + resultLimit - offset;
      int $i$f$minOf = 0, segmentLimit = (int)Math.min(i, b$iv);
      for (int pos = (int)(segment1.pos + l2 - offset); pos < segmentLimit; pos++) {
        if (data[pos] == b0 && rangeEquals(segment1, pos + 1, targetByteArray, 1, bytesSize))
          return (pos - segment1.pos) + offset; 
      } 
      offset += (segment1.limit - segment1.pos);
      l2 = offset;
      Intrinsics.checkNotNull(segment1.next);
      segment1 = segment1.next;
    } 
    return -1L;
  }
  
  public static final long commonIndexOfElement(@NotNull Buffer $this$commonIndexOfElement, @NotNull ByteString targetBytes, long fromIndex) {
    Segment s$iv, segment1;
    Intrinsics.checkNotNullParameter($this$commonIndexOfElement, "<this>");
    Intrinsics.checkNotNullParameter(targetBytes, "targetBytes");
    int $i$f$commonIndexOfElement = 0;
    long l2 = 0L;
    l2 = fromIndex;
    if (!((l2 >= 0L) ? 1 : 0)) {
      int $i$a$-require--Buffer$commonIndexOfElement$1 = 0;
      String str = "fromIndex < 0: " + l2;
      throw new IllegalArgumentException(str.toString());
    } 
    Buffer buffer = $this$commonIndexOfElement;
    long fromIndex$iv = l2;
    int $i$f$seek = 0;
    if (buffer.head == null) {
      long l = -1L;
      Segment segment = null;
      int i = 0;
      return -1L;
    } 
    if (buffer.size() - fromIndex$iv < fromIndex$iv) {
      long l4 = buffer.size();
      while (l4 > fromIndex$iv) {
        Intrinsics.checkNotNull(s$iv.prev);
        s$iv = s$iv.prev;
        l4 -= (s$iv.limit - s$iv.pos);
      } 
      long l3 = l4;
      Segment segment = s$iv;
      int i = 0;
      if (segment == null)
        return -1L; 
    } 
    long offset$iv = 0L;
    while (true) {
      long nextOffset$iv = offset$iv + (s$iv.limit - s$iv.pos);
      if (nextOffset$iv <= fromIndex$iv) {
        Intrinsics.checkNotNull(s$iv.next);
        s$iv = s$iv.next;
        offset$iv = nextOffset$iv;
        continue;
      } 
      break;
    } 
    long l1 = offset$iv;
    Segment s = s$iv;
    int $i$a$-seek--Buffer$commonIndexOfElement$2 = 0;
    if (s == null)
      return -1L; 
    long offset = l1;
    if (targetBytes.size() == 2) {
      byte b0 = targetBytes.getByte(0);
      byte b1 = targetBytes.getByte(1);
      while (offset < $this$commonIndexOfElement.size()) {
        byte[] data = segment1.data;
        int pos = (int)(segment1.pos + l2 - offset);
        int limit = segment1.limit;
        while (pos < limit) {
          int b = data[pos];
          if (b == b0 || b == b1)
            return (pos - segment1.pos) + offset; 
          pos++;
        } 
        offset += (segment1.limit - segment1.pos);
        l2 = offset;
        Intrinsics.checkNotNull(segment1.next);
        segment1 = segment1.next;
      } 
    } else {
      byte[] targetByteArray = targetBytes.internalArray$okio();
      while (offset < $this$commonIndexOfElement.size()) {
        byte[] data = segment1.data;
        int pos = (int)(segment1.pos + l2 - offset);
        int limit = segment1.limit;
        while (pos < limit) {
          int b = data[pos];
          byte t;
          int i;
          for (t = 0, i = targetByteArray.length; t < i; ) {
            byte b1 = targetByteArray[t];
            if (b == b1)
              return (pos - segment1.pos) + offset; 
            t++;
          } 
          pos++;
        } 
        offset += (segment1.limit - segment1.pos);
        l2 = offset;
        Intrinsics.checkNotNull(segment1.next);
        segment1 = segment1.next;
      } 
    } 
    return -1L;
  }
  
  public static final boolean commonRangeEquals(@NotNull Buffer $this$commonRangeEquals, long offset, @NotNull ByteString bytes, int bytesOffset, int byteCount) {
    Intrinsics.checkNotNullParameter($this$commonRangeEquals, "<this>");
    Intrinsics.checkNotNullParameter(bytes, "bytes");
    int $i$f$commonRangeEquals = 0;
    if (offset < 0L || bytesOffset < 0 || byteCount < 0 || $this$commonRangeEquals.size() - offset < byteCount || bytes.size() - bytesOffset < byteCount)
      return false; 
    for (int i = 0; i < byteCount; i++) {
      if ($this$commonRangeEquals.getByte(offset + i) != bytes.getByte(bytesOffset + i))
        return false; 
    } 
    return true;
  }
  
  public static final boolean commonEquals(@NotNull Buffer $this$commonEquals, @Nullable Object other) {
    Intrinsics.checkNotNullParameter($this$commonEquals, "<this>");
    int $i$f$commonEquals = 0;
    if ($this$commonEquals == other)
      return true; 
    if (!(other instanceof Buffer))
      return false; 
    if ($this$commonEquals.size() != ((Buffer)other).size())
      return false; 
    if ($this$commonEquals.size() == 0L)
      return true; 
    Intrinsics.checkNotNull($this$commonEquals.head);
    Segment sa = $this$commonEquals.head;
    Intrinsics.checkNotNull(((Buffer)other).head);
    Segment sb = ((Buffer)other).head;
    int posA = sa.pos;
    int posB = sb.pos;
    long pos = 0L, count = 0L;
    while (pos < $this$commonEquals.size()) {
      count = Math.min(sa.limit - posA, sb.limit - posB);
      long i, l1;
      for (i = 0L, l1 = count; i < l1; i++) {
        if (sa.data[posA++] != sb.data[posB++])
          return false; 
      } 
      if (posA == sa.limit) {
        Intrinsics.checkNotNull(sa.next);
        sa = sa.next;
        posA = sa.pos;
      } 
      if (posB == sb.limit) {
        Intrinsics.checkNotNull(sb.next);
        sb = sb.next;
        posB = sb.pos;
      } 
      pos += count;
    } 
    return true;
  }
  
  public static final int commonHashCode(@NotNull Buffer $this$commonHashCode) {
    Segment s;
    Intrinsics.checkNotNullParameter($this$commonHashCode, "<this>");
    int $i$f$commonHashCode = 0;
    if ($this$commonHashCode.head == null)
      return 0; 
    int result = 1;
    while (true) {
      int pos = s.pos;
      int limit = s.limit;
      while (pos < limit) {
        result = 31 * result + s.data[pos];
        pos++;
      } 
      Intrinsics.checkNotNull(s.next);
      s = s.next;
      if (s == $this$commonHashCode.head)
        return result; 
    } 
  }
  
  @NotNull
  public static final Buffer commonCopy(@NotNull Buffer $this$commonCopy) {
    Intrinsics.checkNotNullParameter($this$commonCopy, "<this>");
    int $i$f$commonCopy = 0;
    Buffer result = new Buffer();
    if ($this$commonCopy.size() == 0L)
      return result; 
    Intrinsics.checkNotNull($this$commonCopy.head);
    Segment head = $this$commonCopy.head;
    Segment headCopy = head.sharedCopy();
    result.head = headCopy;
    headCopy.prev = result.head;
    headCopy.next = headCopy.prev;
    Segment s = head.next;
    while (s != head) {
      Intrinsics.checkNotNull(headCopy.prev);
      Intrinsics.checkNotNull(s);
      headCopy.prev.push(s.sharedCopy());
      s = s.next;
    } 
    result.setSize$okio($this$commonCopy.size());
    return result;
  }
  
  @NotNull
  public static final ByteString commonSnapshot(@NotNull Buffer $this$commonSnapshot) {
    Intrinsics.checkNotNullParameter($this$commonSnapshot, "<this>");
    int $i$f$commonSnapshot = 0;
    if (!(($this$commonSnapshot.size() <= 2147483647L) ? 1 : 0)) {
      int $i$a$-check--Buffer$commonSnapshot$1 = 0;
      String str = "size > Int.MAX_VALUE: " + $this$commonSnapshot.size();
      throw new IllegalStateException(str.toString());
    } 
    return $this$commonSnapshot.snapshot((int)$this$commonSnapshot.size());
  }
  
  @NotNull
  public static final ByteString commonSnapshot(@NotNull Buffer $this$commonSnapshot, int byteCount) {
    Intrinsics.checkNotNullParameter($this$commonSnapshot, "<this>");
    int $i$f$commonSnapshot = 0;
    if (byteCount == 0)
      return ByteString.EMPTY; 
    -SegmentedByteString.checkOffsetAndCount($this$commonSnapshot.size(), 0L, byteCount);
    int offset = 0;
    int segmentCount = 0;
    Segment s = $this$commonSnapshot.head;
    while (offset < byteCount) {
      Intrinsics.checkNotNull(s);
      if (s.limit == s.pos)
        throw new AssertionError("s.limit == s.pos"); 
      offset += s.limit - s.pos;
      segmentCount++;
      s = s.next;
    } 
    byte[][] segments = new byte[segmentCount][];
    int[] directory = new int[segmentCount * 2];
    offset = 0;
    segmentCount = 0;
    s = $this$commonSnapshot.head;
    while (offset < byteCount) {
      Intrinsics.checkNotNull(s);
      segments[segmentCount] = s.data;
      offset += s.limit - s.pos;
      directory[segmentCount] = Math.min(offset, byteCount);
      directory[segmentCount + ((Object[])segments).length] = s.pos;
      s.shared = true;
      segmentCount++;
      s = s.next;
    } 
    return (ByteString)new SegmentedByteString(segments, directory);
  }
  
  @NotNull
  public static final Buffer.UnsafeCursor commonReadUnsafe(@NotNull Buffer $this$commonReadUnsafe, @NotNull Buffer.UnsafeCursor unsafeCursor) {
    Intrinsics.checkNotNullParameter($this$commonReadUnsafe, "<this>");
    Intrinsics.checkNotNullParameter(unsafeCursor, "unsafeCursor");
    Buffer.UnsafeCursor unsafeCursor1 = -SegmentedByteString.resolveDefaultParameter(unsafeCursor);
    if (!((unsafeCursor1.buffer == null) ? 1 : 0)) {
      int $i$a$-check--Buffer$commonReadUnsafe$1 = 0;
      String str = "already attached to a buffer";
      throw new IllegalStateException(str.toString());
    } 
    unsafeCursor1.buffer = $this$commonReadUnsafe;
    unsafeCursor1.readWrite = false;
    return unsafeCursor1;
  }
  
  @NotNull
  public static final Buffer.UnsafeCursor commonReadAndWriteUnsafe(@NotNull Buffer $this$commonReadAndWriteUnsafe, @NotNull Buffer.UnsafeCursor unsafeCursor) {
    Intrinsics.checkNotNullParameter($this$commonReadAndWriteUnsafe, "<this>");
    Intrinsics.checkNotNullParameter(unsafeCursor, "unsafeCursor");
    Buffer.UnsafeCursor unsafeCursor1 = -SegmentedByteString.resolveDefaultParameter(unsafeCursor);
    if (!((unsafeCursor1.buffer == null) ? 1 : 0)) {
      int $i$a$-check--Buffer$commonReadAndWriteUnsafe$1 = 0;
      String str = "already attached to a buffer";
      throw new IllegalStateException(str.toString());
    } 
    unsafeCursor1.buffer = $this$commonReadAndWriteUnsafe;
    unsafeCursor1.readWrite = true;
    return unsafeCursor1;
  }
  
  public static final int commonNext(@NotNull Buffer.UnsafeCursor $this$commonNext) {
    Intrinsics.checkNotNullParameter($this$commonNext, "<this>");
    int $i$f$commonNext = 0;
    Intrinsics.checkNotNull($this$commonNext.buffer);
    if (!(($this$commonNext.offset != $this$commonNext.buffer.size()) ? 1 : 0)) {
      int $i$a$-check--Buffer$commonNext$1 = 0;
      String str = "no more bytes";
      throw new IllegalStateException(str.toString());
    } 
    return ($this$commonNext.offset == -1L) ? $this$commonNext.seek(0L) : $this$commonNext.seek($this$commonNext.offset + ($this$commonNext.end - $this$commonNext.start));
  }
  
  public static final int commonSeek(@NotNull Buffer.UnsafeCursor $this$commonSeek, long offset) {
    Buffer buffer;
    Intrinsics.checkNotNullParameter($this$commonSeek, "<this>");
    int $i$f$commonSeek = 0;
    if ($this$commonSeek.buffer == null) {
      int $i$a$-checkNotNull--Buffer$commonSeek$buffer$1 = 0;
      String str = "not attached to a buffer";
      throw new IllegalStateException(str.toString());
    } 
    if (offset < -1L || offset > buffer.size())
      throw new ArrayIndexOutOfBoundsException("offset=" + offset + " > size=" + buffer.size()); 
    if (offset == -1L || offset == buffer.size()) {
      $this$commonSeek.setSegment$okio(null);
      $this$commonSeek.offset = offset;
      $this$commonSeek.data = null;
      $this$commonSeek.start = -1;
      $this$commonSeek.end = -1;
      return -1;
    } 
    long min = 0L;
    long max = buffer.size();
    Segment head = buffer.head;
    Segment tail = buffer.head;
    if ($this$commonSeek.getSegment$okio() != null) {
      Intrinsics.checkNotNull($this$commonSeek.getSegment$okio());
      long segmentOffset = $this$commonSeek.offset - ($this$commonSeek.start - ($this$commonSeek.getSegment$okio()).pos);
      if (segmentOffset > offset) {
        max = segmentOffset;
        tail = $this$commonSeek.getSegment$okio();
      } else {
        min = segmentOffset;
        head = $this$commonSeek.getSegment$okio();
      } 
    } 
    Segment next = null;
    long nextOffset = 0L;
    if (max - offset > offset - min) {
      next = head;
      nextOffset = min;
      Intrinsics.checkNotNull(next);
      while (offset >= nextOffset + (next.limit - next.pos)) {
        nextOffset += (next.limit - next.pos);
        next = next.next;
      } 
    } else {
      next = tail;
      nextOffset = max;
      while (nextOffset > offset) {
        Intrinsics.checkNotNull(next);
        next = next.prev;
        Intrinsics.checkNotNull(next);
        nextOffset -= (next.limit - next.pos);
      } 
    } 
    Intrinsics.checkNotNull(next);
    if ($this$commonSeek.readWrite && next.shared) {
      Segment unsharedNext = next.unsharedCopy();
      if (buffer.head == next)
        buffer.head = unsharedNext; 
      next = next.push(unsharedNext);
      Intrinsics.checkNotNull(next.prev);
      next.prev.pop();
    } 
    $this$commonSeek.setSegment$okio(next);
    $this$commonSeek.offset = offset;
    Intrinsics.checkNotNull(next);
    $this$commonSeek.data = next.data;
    $this$commonSeek.start = next.pos + (int)(offset - nextOffset);
    $this$commonSeek.end = next.limit;
    return $this$commonSeek.end - $this$commonSeek.start;
  }
  
  public static final long commonResizeBuffer(@NotNull Buffer.UnsafeCursor $this$commonResizeBuffer, long newSize) {
    Buffer buffer;
    Intrinsics.checkNotNullParameter($this$commonResizeBuffer, "<this>");
    int $i$f$commonResizeBuffer = 0;
    if ($this$commonResizeBuffer.buffer == null) {
      int $i$a$-checkNotNull--Buffer$commonResizeBuffer$buffer$1 = 0;
      String str = "not attached to a buffer";
      throw new IllegalStateException(str.toString());
    } 
    if (!$this$commonResizeBuffer.readWrite) {
      int $i$a$-check--Buffer$commonResizeBuffer$1 = 0;
      String str = "resizeBuffer() only permitted for read/write buffers";
      throw new IllegalStateException(str.toString());
    } 
    long oldSize = buffer.size();
    if (newSize <= oldSize) {
      if (!((newSize >= 0L) ? 1 : 0)) {
        int $i$a$-require--Buffer$commonResizeBuffer$2 = 0;
        String str = "newSize < 0: " + newSize;
        throw new IllegalArgumentException(str.toString());
      } 
      long bytesToSubtract = oldSize - newSize;
      while (bytesToSubtract > 0L) {
        Intrinsics.checkNotNull(buffer.head);
        Segment tail = buffer.head.prev;
        Intrinsics.checkNotNull(tail);
        int tailSize = tail.limit - tail.pos;
        if (tailSize <= bytesToSubtract) {
          buffer.head = tail.pop();
          SegmentPool.recycle(tail);
          bytesToSubtract -= tailSize;
          continue;
        } 
        tail.limit -= (int)bytesToSubtract;
      } 
      $this$commonResizeBuffer.setSegment$okio(null);
      $this$commonResizeBuffer.offset = newSize;
      $this$commonResizeBuffer.data = null;
      $this$commonResizeBuffer.start = -1;
      $this$commonResizeBuffer.end = -1;
    } else if (newSize > oldSize) {
      boolean needsToSeek = true;
      long bytesToAdd = newSize - oldSize;
      while (bytesToAdd > 0L) {
        Segment tail = buffer.writableSegment$okio(1);
        int b$iv = 8192 - tail.limit, $i$f$minOf = 0, segmentBytesToAdd = (int)Math.min(bytesToAdd, b$iv);
        tail.limit += segmentBytesToAdd;
        bytesToAdd -= segmentBytesToAdd;
        if (needsToSeek) {
          $this$commonResizeBuffer.setSegment$okio(tail);
          $this$commonResizeBuffer.offset = oldSize;
          $this$commonResizeBuffer.data = tail.data;
          $this$commonResizeBuffer.start = tail.limit - segmentBytesToAdd;
          $this$commonResizeBuffer.end = tail.limit;
          needsToSeek = false;
        } 
      } 
    } 
    buffer.setSize$okio(newSize);
    return oldSize;
  }
  
  public static final long commonExpandBuffer(@NotNull Buffer.UnsafeCursor $this$commonExpandBuffer, int minByteCount) {
    Buffer buffer;
    Intrinsics.checkNotNullParameter($this$commonExpandBuffer, "<this>");
    int $i$f$commonExpandBuffer = 0;
    if (!((minByteCount > 0) ? 1 : 0)) {
      int $i$a$-require--Buffer$commonExpandBuffer$1 = 0;
      String str = "minByteCount <= 0: " + minByteCount;
      throw new IllegalArgumentException(str.toString());
    } 
    if (!((minByteCount <= 8192) ? 1 : 0)) {
      int $i$a$-require--Buffer$commonExpandBuffer$2 = 0;
      String str = "minByteCount > Segment.SIZE: " + minByteCount;
      throw new IllegalArgumentException(str.toString());
    } 
    if ($this$commonExpandBuffer.buffer == null) {
      int $i$a$-checkNotNull--Buffer$commonExpandBuffer$buffer$1 = 0;
      String str = "not attached to a buffer";
      throw new IllegalStateException(str.toString());
    } 
    if (!$this$commonExpandBuffer.readWrite) {
      int $i$a$-check--Buffer$commonExpandBuffer$3 = 0;
      String str = "expandBuffer() only permitted for read/write buffers";
      throw new IllegalStateException(str.toString());
    } 
    long oldSize = buffer.size();
    Segment tail = buffer.writableSegment$okio(minByteCount);
    int result = 8192 - tail.limit;
    tail.limit = 8192;
    buffer.setSize$okio(oldSize + result);
    $this$commonExpandBuffer.setSegment$okio(tail);
    $this$commonExpandBuffer.offset = oldSize;
    $this$commonExpandBuffer.data = tail.data;
    $this$commonExpandBuffer.start = 8192 - result;
    $this$commonExpandBuffer.end = 8192;
    return result;
  }
  
  public static final void commonClose(@NotNull Buffer.UnsafeCursor $this$commonClose) {
    Intrinsics.checkNotNullParameter($this$commonClose, "<this>");
    int $i$f$commonClose = 0;
    if (!(($this$commonClose.buffer != null) ? 1 : 0)) {
      int $i$a$-check--Buffer$commonClose$1 = 0;
      String str = "not attached to a buffer";
      throw new IllegalStateException(str.toString());
    } 
    $this$commonClose.buffer = null;
    $this$commonClose.setSegment$okio(null);
    $this$commonClose.offset = -1L;
    $this$commonClose.data = null;
    $this$commonClose.start = -1;
    $this$commonClose.end = -1;
  }
}
