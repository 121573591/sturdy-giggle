package okio.internal;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;

@Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\000\024\n\002\020\016\n\002\020\022\n\002\b\002\n\002\020\b\n\002\b\005\032\021\020\002\032\0020\001*\0020\000¢\006\004\b\002\020\003\032%\020\007\032\0020\000*\0020\0012\b\b\002\020\005\032\0020\0042\b\b\002\020\006\032\0020\004¢\006\004\b\007\020\b¨\006\t"}, d2 = {"", "", "commonAsUtf8ToByteArray", "(Ljava/lang/String;)[B", "", "beginIndex", "endIndex", "commonToUtf8String", "([BII)Ljava/lang/String;", "okio"})
@SourceDebugExtension({"SMAP\n-Utf8.kt\nKotlin\n*S Kotlin\n*F\n+ 1 -Utf8.kt\nokio/internal/_Utf8Kt\n+ 2 Utf8.kt\nokio/Utf8\n+ 3 Util.kt\nokio/-SegmentedByteString\n*L\n1#1,60:1\n260#2,16:61\n277#2:78\n397#2,9:79\n127#2:88\n406#2,20:90\n279#2,3:110\n440#2,4:113\n127#2:117\n446#2,10:118\n127#2:128\n456#2,5:129\n127#2:134\n461#2,24:135\n283#2,3:159\n500#2,3:162\n286#2,12:165\n503#2:177\n127#2:178\n506#2,2:179\n127#2:181\n510#2,10:182\n127#2:192\n520#2,5:193\n127#2:198\n525#2,5:199\n127#2:204\n530#2,28:205\n302#2,6:233\n138#2,67:239\n68#3:77\n74#3:89\n*S KotlinDebug\n*F\n+ 1 -Utf8.kt\nokio/internal/_Utf8Kt\n*L\n34#1:61,16\n34#1:78\n34#1:79,9\n34#1:88\n34#1:90,20\n34#1:110,3\n34#1:113,4\n34#1:117\n34#1:118,10\n34#1:128\n34#1:129,5\n34#1:134\n34#1:135,24\n34#1:159,3\n34#1:162,3\n34#1:165,12\n34#1:177\n34#1:178\n34#1:179,2\n34#1:181\n34#1:182,10\n34#1:192\n34#1:193,5\n34#1:198\n34#1:199,5\n34#1:204\n34#1:205,28\n34#1:233,6\n50#1:239,67\n34#1:77\n34#1:89\n*E\n"})
public final class _Utf8Kt {
  @NotNull
  public static final String commonToUtf8String(@NotNull byte[] $this$commonToUtf8String, int beginIndex, int endIndex) {
    Intrinsics.checkNotNullParameter($this$commonToUtf8String, "<this>");
    if (beginIndex < 0 || endIndex > $this$commonToUtf8String.length || beginIndex > endIndex)
      throw new ArrayIndexOutOfBoundsException("size=" + $this$commonToUtf8String.length + " beginIndex=" + beginIndex + " endIndex=" + endIndex); 
    char[] chars = new char[endIndex - beginIndex];
    int length = 0;
    byte[] $this$processUtf16Chars$iv = $this$commonToUtf8String;
    int $i$f$processUtf16Chars = 0;
    int index$iv = beginIndex;
    while (index$iv < endIndex) {
      byte b0$iv = $this$processUtf16Chars$iv[index$iv];
      if (b0$iv >= 0) {
        char c = (char)b0$iv;
        int $i$a$-processUtf16Chars-_Utf8Kt$commonToUtf8String$1 = 0;
        int i = length;
        length = i + 1;
        chars[i] = c;
        index$iv++;
      } 
      byte b = b0$iv;
      int other$iv$iv = 5, $i$f$shr = 0;
      if (b >> other$iv$iv == -2) {
        byte[] $this$process2Utf8Bytes$iv$iv = $this$processUtf16Chars$iv;
        int $i$f$process2Utf8Bytes = 0;
        if (endIndex <= index$iv + 1) {
          $i$f$shr = 65533;
          int n = index$iv, i1 = 0;
          char c1 = (char)$i$f$shr;
          int k = 0;
          int m = length;
          length = m + 1;
          chars[m] = c1;
          Unit unit1 = Unit.INSTANCE;
        } 
        byte b0$iv$iv = $this$process2Utf8Bytes$iv$iv[index$iv];
        byte b1$iv$iv = $this$process2Utf8Bytes$iv$iv[index$iv + 1];
        int $i$f$isUtf8Continuation = 0;
        byte b1 = b1$iv$iv;
        int other$iv$iv$iv$iv = 192, $i$f$and = 0;
        if (!(((
          b1 & other$iv$iv$iv$iv) == 128) ? 1 : 0)) {
          int m = 65533, n = index$iv, i1 = 0;
          char c1 = (char)m;
          int k = 0;
        } 
        int codePoint$iv$iv = 
          
          0xF80 ^ 
          b1$iv$iv ^ 
          b0$iv$iv << 6;
        if (codePoint$iv$iv < 128) {
          int m = 65533, n = index$iv, i1 = 0;
          char c1 = (char)m;
          int k = 0;
        } 
        int it$iv = codePoint$iv$iv, j = index$iv, $i$a$-process2Utf8Bytes-Utf8$processUtf16Chars$1$iv = 0;
        char c = (char)it$iv;
        int $i$a$-processUtf16Chars-_Utf8Kt$commonToUtf8String$1 = 0;
        int i = length;
        length = i + 1;
        chars[i] = c;
        Unit unit = Unit.INSTANCE;
      } 
      byte $this$shr$iv$iv = b0$iv;
      other$iv$iv = 4;
      $i$f$shr = 0;
    } 
    return StringsKt.concatToString(chars, 0, length);
  }
  
  @NotNull
  public static final byte[] commonAsUtf8ToByteArray(@NotNull String $this$commonAsUtf8ToByteArray) {
    Intrinsics.checkNotNullParameter($this$commonAsUtf8ToByteArray, "<this>");
    byte[] bytes = new byte[4 * $this$commonAsUtf8ToByteArray.length()];
    for (int index = 0, i = $this$commonAsUtf8ToByteArray.length(); index < i; index++) {
      char b0 = $this$commonAsUtf8ToByteArray.charAt(index);
      if (Intrinsics.compare(b0, 128) >= 0) {
        int size = 0;
        size = index;
        String str = $this$commonAsUtf8ToByteArray;
        int endIndex$iv = $this$commonAsUtf8ToByteArray.length(), $i$f$processUtf8Bytes = 0;
        int index$iv = index;
        for (; index$iv < endIndex$iv; j = size, size = j + 1, bytes[j] = c) {
          int j;
          char c$iv = str.charAt(index$iv);
          if (Intrinsics.compare(c$iv, 128) < 0) {
            byte b = (byte)c$iv;
            int k = 0;
            int m = size;
            size = m + 1;
            bytes[m] = b;
          } 
          if (Intrinsics.compare(c$iv, 2048) < 0) {
            byte b = (byte)(c$iv >> 6 | 0xC0);
            int k = 0;
            int m = size;
            size = m + 1;
            bytes[m] = b;
            b = (byte)(c$iv & 0x3F | 0x80);
            k = 0;
          } 
          if (!(('?' <= c$iv) ? ((c$iv < '') ? 1 : 0) : 0)) {
            byte b = (byte)(c$iv >> 12 | 0xE0);
            int k = 0;
            int m = size;
            size = m + 1;
            bytes[m] = b;
            b = (byte)(c$iv >> 6 & 0x3F | 0x80);
            k = 0;
          } 
          if (Intrinsics.compare(c$iv, 56319) <= 0 && 
            endIndex$iv > index$iv + 1) {
            char c1 = str.charAt(index$iv + 1);
            if (('?' <= c1) ? ((c1 < '')) : false) {
              int codePoint$iv = (
                c$iv << 10) + str.charAt(index$iv + 1) + 
                -56613888;
              byte b = (byte)(codePoint$iv >> 18 | 0xF0);
              int k = 0;
              int m = size;
              size = m + 1;
              bytes[m] = b;
              b = (byte)(codePoint$iv >> 12 & 0x3F | 0x80);
              k = 0;
              m = size;
              size = m + 1;
              bytes[m] = b;
              b = (byte)(codePoint$iv >> 6 & 0x3F | 0x80);
              k = 0;
              m = size;
              size = m + 1;
              bytes[m] = b;
              b = (byte)(codePoint$iv & 0x3F | 0x80);
              k = 0;
              m = size;
              size = m + 1;
              bytes[m] = b;
              index$iv += 2;
            } 
          } 
          byte c = 63;
          int $i$a$-processUtf8Bytes-_Utf8Kt$commonAsUtf8ToByteArray$1 = 0;
        } 
        Intrinsics.checkNotNullExpressionValue(Arrays.copyOf(bytes, size), "copyOf(this, newSize)");
        return Arrays.copyOf(bytes, size);
      } 
      bytes[index] = (byte)b0;
    } 
    Intrinsics.checkNotNullExpressionValue(Arrays.copyOf(bytes, $this$commonAsUtf8ToByteArray.length()), "copyOf(this, newSize)");
    return Arrays.copyOf(bytes, $this$commonAsUtf8ToByteArray.length());
  }
}
