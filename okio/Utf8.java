package okio;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

@Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\000B\n\002\020\b\n\000\n\002\020\013\n\002\b\002\n\002\020\005\n\002\b\003\n\002\020\022\n\002\b\002\n\002\030\002\n\002\020\002\n\002\b\005\n\002\020\f\n\002\b\002\n\002\020\016\n\002\b\003\n\002\020\t\n\002\b\017\032\030\020\003\032\0020\0022\006\020\001\032\0020\000H\b¢\006\004\b\003\020\004\032\030\020\007\032\0020\0022\006\020\006\032\0020\005H\b¢\006\004\b\007\020\b\032;\020\017\032\0020\000*\0020\t2\006\020\n\032\0020\0002\006\020\013\032\0020\0002\022\020\016\032\016\022\004\022\0020\000\022\004\022\0020\r0\fH\bø\001\000¢\006\004\b\017\020\020\032;\020\021\032\0020\000*\0020\t2\006\020\n\032\0020\0002\006\020\013\032\0020\0002\022\020\016\032\016\022\004\022\0020\000\022\004\022\0020\r0\fH\bø\001\000¢\006\004\b\021\020\020\032;\020\022\032\0020\000*\0020\t2\006\020\n\032\0020\0002\006\020\013\032\0020\0002\022\020\016\032\016\022\004\022\0020\000\022\004\022\0020\r0\fH\bø\001\000¢\006\004\b\022\020\020\032;\020\024\032\0020\r*\0020\t2\006\020\n\032\0020\0002\006\020\013\032\0020\0002\022\020\016\032\016\022\004\022\0020\023\022\004\022\0020\r0\fH\bø\001\000¢\006\004\b\024\020\025\032;\020\027\032\0020\r*\0020\0262\006\020\n\032\0020\0002\006\020\013\032\0020\0002\022\020\016\032\016\022\004\022\0020\005\022\004\022\0020\r0\fH\bø\001\000¢\006\004\b\027\020\030\032;\020\031\032\0020\r*\0020\t2\006\020\n\032\0020\0002\006\020\013\032\0020\0002\022\020\016\032\016\022\004\022\0020\000\022\004\022\0020\r0\fH\bø\001\000¢\006\004\b\031\020\025\032'\020\035\032\0020\032*\0020\0262\b\b\002\020\n\032\0020\0002\b\b\002\020\013\032\0020\000H\007¢\006\004\b\033\020\034\"\024\020\036\032\0020\0008\000XT¢\006\006\n\004\b\036\020\037\"\024\020 \032\0020\0008\000XT¢\006\006\n\004\b \020\037\"\024\020!\032\0020\0008\000XT¢\006\006\n\004\b!\020\037\"\024\020\"\032\0020\0008\000XT¢\006\006\n\004\b\"\020\037\"\024\020#\032\0020\0008\000XT¢\006\006\n\004\b#\020\037\"\024\020$\032\0020\0058\000XT¢\006\006\n\004\b$\020%\"\024\020&\032\0020\0238\000XT¢\006\006\n\004\b&\020'\"\024\020(\032\0020\0008\000XT¢\006\006\n\004\b(\020\037\002\007\n\005\b20\001¨\006)"}, d2 = {"", "codePoint", "", "isIsoControl", "(I)Z", "", "byte", "isUtf8Continuation", "(B)Z", "", "beginIndex", "endIndex", "Lkotlin/Function1;", "", "yield", "process2Utf8Bytes", "([BIILkotlin/jvm/functions/Function1;)I", "process3Utf8Bytes", "process4Utf8Bytes", "", "processUtf16Chars", "([BIILkotlin/jvm/functions/Function1;)V", "", "processUtf8Bytes", "(Ljava/lang/String;IILkotlin/jvm/functions/Function1;)V", "processUtf8CodePoints", "", "size", "(Ljava/lang/String;II)J", "utf8Size", "HIGH_SURROGATE_HEADER", "I", "LOG_SURROGATE_HEADER", "MASK_2BYTES", "MASK_3BYTES", "MASK_4BYTES", "REPLACEMENT_BYTE", "B", "REPLACEMENT_CHARACTER", "C", "REPLACEMENT_CODE_POINT", "okio"})
@JvmName(name = "Utf8")
@SourceDebugExtension({"SMAP\nUtf8.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Utf8.kt\nokio/Utf8\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 Util.kt\nokio/-SegmentedByteString\n*L\n1#1,559:1\n397#1,9:563\n127#1:572\n406#1,20:574\n440#1,4:595\n127#1:599\n446#1,10:601\n127#1:611\n456#1,5:612\n127#1:617\n461#1,24:618\n500#1,4:643\n127#1:647\n506#1,2:649\n127#1:651\n510#1,10:652\n127#1:662\n520#1,5:663\n127#1:668\n525#1,5:669\n127#1:674\n530#1,28:675\n397#1,9:704\n127#1:713\n406#1,20:715\n440#1,4:736\n127#1:740\n446#1,10:742\n127#1:752\n456#1,5:753\n127#1:758\n461#1,24:759\n500#1,4:784\n127#1:788\n506#1,2:790\n127#1:792\n510#1,10:793\n127#1:803\n520#1,5:804\n127#1:809\n525#1,5:810\n127#1:815\n530#1,28:816\n127#1:844\n127#1:846\n127#1:848\n127#1:850\n127#1:852\n127#1:854\n127#1:856\n127#1:858\n127#1:860\n1#2:560\n74#3:561\n68#3:562\n74#3:573\n68#3:594\n74#3:600\n68#3:642\n74#3:648\n68#3:703\n74#3:714\n68#3:735\n74#3:741\n68#3:783\n74#3:789\n74#3:845\n74#3:847\n74#3:849\n74#3:851\n74#3:853\n74#3:855\n74#3:857\n74#3:859\n74#3:861\n*S KotlinDebug\n*F\n+ 1 Utf8.kt\nokio/Utf8\n*L\n228#1:563,9\n228#1:572\n228#1:574,20\n232#1:595,4\n232#1:599\n232#1:601,10\n232#1:611\n232#1:612,5\n232#1:617\n232#1:618,24\n236#1:643,4\n236#1:647\n236#1:649,2\n236#1:651\n236#1:652,10\n236#1:662\n236#1:663,5\n236#1:668\n236#1:669,5\n236#1:674\n236#1:675,28\n277#1:704,9\n277#1:713\n277#1:715,20\n281#1:736,4\n281#1:740\n281#1:742,10\n281#1:752\n281#1:753,5\n281#1:758\n281#1:759,24\n285#1:784,4\n285#1:788\n285#1:790,2\n285#1:792\n285#1:793,10\n285#1:803\n285#1:804,5\n285#1:809\n285#1:810,5\n285#1:815\n285#1:816,28\n405#1:844\n443#1:846\n455#1:848\n460#1:850\n503#1:852\n507#1:854\n519#1:856\n524#1:858\n529#1:860\n127#1:561\n226#1:562\n228#1:573\n230#1:594\n232#1:600\n234#1:642\n236#1:648\n275#1:703\n277#1:714\n279#1:735\n281#1:741\n283#1:783\n285#1:789\n405#1:845\n443#1:847\n455#1:849\n460#1:851\n503#1:853\n507#1:855\n519#1:857\n524#1:859\n529#1:861\n*E\n"})
public final class Utf8 {
  public static final byte REPLACEMENT_BYTE = 63;
  
  public static final char REPLACEMENT_CHARACTER = '�';
  
  public static final int REPLACEMENT_CODE_POINT = 65533;
  
  public static final int HIGH_SURROGATE_HEADER = 55232;
  
  public static final int LOG_SURROGATE_HEADER = 56320;
  
  public static final int MASK_2BYTES = 3968;
  
  public static final int MASK_3BYTES = -123008;
  
  public static final int MASK_4BYTES = 3678080;
  
  @JvmOverloads
  @JvmName(name = "size")
  public static final long size(@NotNull String $this$utf8Size, int beginIndex, int endIndex) {
    Intrinsics.checkNotNullParameter($this$utf8Size, "<this>");
    if (!((beginIndex >= 0) ? 1 : 0)) {
      int $i$a$-require-Utf8$utf8Size$1 = 0;
      String str = "beginIndex < 0: " + beginIndex;
      throw new IllegalArgumentException(str.toString());
    } 
    if (!((endIndex >= beginIndex) ? 1 : 0)) {
      int $i$a$-require-Utf8$utf8Size$2 = 0;
      String str = "endIndex < beginIndex: " + endIndex + " < " + beginIndex;
      throw new IllegalArgumentException(str.toString());
    } 
    if (!((endIndex <= $this$utf8Size.length()) ? 1 : 0)) {
      int $i$a$-require-Utf8$utf8Size$3 = 0;
      String str = "endIndex > string.length: " + endIndex + " > " + $this$utf8Size.length();
      throw new IllegalArgumentException(str.toString());
    } 
    long result = 0L;
    int i = beginIndex;
    while (i < endIndex) {
      int c = $this$utf8Size.charAt(i);
      if (c < 128) {
        long l = result;
        result = l + 1L;
        i++;
        continue;
      } 
      if (c < 2048) {
        result += 2L;
        i++;
        continue;
      } 
      if (c < 55296 || c > 57343) {
        result += 3L;
        i++;
        continue;
      } 
      int low = (i + 1 < endIndex) ? $this$utf8Size.charAt(i + 1) : 0;
      if (c > 56319 || low < 56320 || low > 57343) {
        long l = result;
        result = l + 1L;
        i++;
        continue;
      } 
      result += 4L;
      i += 2;
    } 
    return result;
  }
  
  public static final boolean isIsoControl(int codePoint) {
    int $i$f$isIsoControl = 0;
    if (!((0 <= codePoint) ? ((codePoint < 32) ? 1 : 0) : 0)) {
      if ((127 <= codePoint) ? ((codePoint < 160)) : false);
      return false;
    } 
  }
  
  public static final boolean isUtf8Continuation(byte byte) {
    int $i$f$isUtf8Continuation = 0;
    byte b = byte;
    int other$iv = 192, $i$f$and = 0;
    return ((b & other$iv) == 128);
  }
  
  public static final void processUtf8Bytes(@NotNull String $this$processUtf8Bytes, int beginIndex, int endIndex, @NotNull Function1 yield) {
    Intrinsics.checkNotNullParameter($this$processUtf8Bytes, "<this>");
    Intrinsics.checkNotNullParameter(yield, "yield");
    int $i$f$processUtf8Bytes = 0, index = beginIndex;
    while (index < endIndex) {
      char c = $this$processUtf8Bytes.charAt(index);
      if (Intrinsics.compare(c, 128) < 0) {
        yield.invoke(Byte.valueOf((byte)c));
        index++;
        while (index < endIndex && Intrinsics.compare($this$processUtf8Bytes.charAt(index), 128) < 0)
          yield.invoke(Byte.valueOf((byte)$this$processUtf8Bytes.charAt(index++))); 
        continue;
      } 
      if (Intrinsics.compare(c, 2048) < 0) {
        yield.invoke(Byte.valueOf((byte)(c >> 6 | 0xC0)));
        yield.invoke(Byte.valueOf((byte)(c & 0x3F | 0x80)));
        index++;
        continue;
      } 
      if (!(('?' <= c) ? ((c < '') ? 1 : 0) : 0)) {
        yield.invoke(Byte.valueOf((byte)(c >> 12 | 0xE0)));
        yield.invoke(Byte.valueOf((byte)(c >> 6 & 0x3F | 0x80)));
        yield.invoke(Byte.valueOf((byte)(c & 0x3F | 0x80)));
        index++;
        continue;
      } 
      if (Intrinsics.compare(c, 56319) <= 0 && endIndex > index + 1) {
        char c1 = $this$processUtf8Bytes.charAt(index + 1);
        if (('?' <= c1) ? ((c1 < '')) : false) {
          int codePoint = (c << 10) + $this$processUtf8Bytes.charAt(index + 1) + -56613888;
          yield.invoke(Byte.valueOf((byte)(codePoint >> 18 | 0xF0)));
          yield.invoke(Byte.valueOf((byte)(codePoint >> 12 & 0x3F | 0x80)));
          yield.invoke(Byte.valueOf((byte)(codePoint >> 6 & 0x3F | 0x80)));
          yield.invoke(Byte.valueOf((byte)(codePoint & 0x3F | 0x80)));
          index += 2;
        } 
      } 
      yield.invoke(Byte.valueOf((byte)63));
      index++;
    } 
  }
  
  public static final void processUtf8CodePoints(@NotNull byte[] $this$processUtf8CodePoints, int beginIndex, int endIndex, @NotNull Function1 yield) {
    Intrinsics.checkNotNullParameter($this$processUtf8CodePoints, "<this>");
    Intrinsics.checkNotNullParameter(yield, "yield");
    int $i$f$processUtf8CodePoints = 0, index = beginIndex;
    while (true) {
      if (index < endIndex) {
        byte b0 = $this$processUtf8CodePoints[index];
        if (b0 >= 0) {
          yield.invoke(Integer.valueOf(b0));
          index++;
          while (index < endIndex && $this$processUtf8CodePoints[index] >= 0)
            yield.invoke(Integer.valueOf($this$processUtf8CodePoints[index++])); 
          continue;
        } 
        byte b = b0;
        int other$iv = 5, $i$f$shr = 0;
        if (b >> other$iv == -2) {
          byte[] arrayOfByte = $this$processUtf8CodePoints;
          int $i$f$process2Utf8Bytes = 0;
          if (endIndex <= index + 1) {
            $i$f$shr = 65533;
            int i = index, $i$a$-process2Utf8Bytes-Utf8$processUtf8CodePoints$1 = 0;
            yield.invoke(Integer.valueOf($i$f$shr));
            Unit unit = Unit.INSTANCE;
          } else {
            byte b0$iv = arrayOfByte[index];
            byte b1$iv = arrayOfByte[index + 1];
            int $i$f$isUtf8Continuation = 0;
            byte b1 = b1$iv;
            int other$iv$iv$iv = 192, $i$f$and = 0;
            if (!(((
              b1 & other$iv$iv$iv) == 128) ? 1 : 0)) {
              int j = 65533, m = index, k = 0;
              yield.invoke(Integer.valueOf(j));
              Unit unit1 = Unit.INSTANCE;
            } 
            int codePoint$iv = 
              
              0xF80 ^ 
              b1$iv ^ 
              b0$iv << 6;
            if (codePoint$iv < 128) {
              int j = 65533, m = index, k = 0;
              yield.invoke(Integer.valueOf(j));
              Unit unit1 = Unit.INSTANCE;
            } 
            int it = codePoint$iv, i = index, $i$a$-process2Utf8Bytes-Utf8$processUtf8CodePoints$1 = 0;
            yield.invoke(Integer.valueOf(it));
            Unit unit = Unit.INSTANCE;
          } 
        } else {
          continue;
        } 
      } else {
        break;
      } 
      index = i + 2;
      continue;
      object = SYNTHETIC_LOCAL_VARIABLE_6;
      other$iv = 4;
      $i$f$shr = 0;
    } 
  }
  
  public static final void processUtf16Chars(@NotNull byte[] $this$processUtf16Chars, int beginIndex, int endIndex, @NotNull Function1 yield) {
    Intrinsics.checkNotNullParameter($this$processUtf16Chars, "<this>");
    Intrinsics.checkNotNullParameter(yield, "yield");
    int $i$f$processUtf16Chars = 0, index = beginIndex;
    while (true) {
      if (index < endIndex) {
        byte b0 = $this$processUtf16Chars[index];
        if (b0 >= 0) {
          yield.invoke(Character.valueOf((char)b0));
          index++;
          while (index < endIndex && $this$processUtf16Chars[index] >= 0)
            yield.invoke(Character.valueOf((char)$this$processUtf16Chars[index++])); 
          continue;
        } 
        byte b = b0;
        int other$iv = 5, $i$f$shr = 0;
        if (b >> other$iv == -2) {
          byte[] arrayOfByte = $this$processUtf16Chars;
          int $i$f$process2Utf8Bytes = 0;
          if (endIndex <= index + 1) {
            $i$f$shr = 65533;
            int i = index, $i$a$-process2Utf8Bytes-Utf8$processUtf16Chars$1 = 0;
            yield.invoke(Character.valueOf((char)$i$f$shr));
            Unit unit = Unit.INSTANCE;
          } else {
            byte b0$iv = arrayOfByte[index];
            byte b1$iv = arrayOfByte[index + 1];
            int $i$f$isUtf8Continuation = 0;
            byte b1 = b1$iv;
            int other$iv$iv$iv = 192, $i$f$and = 0;
            if (!(((
              b1 & other$iv$iv$iv) == 128) ? 1 : 0)) {
              int j = 65533, m = index, k = 0;
              yield.invoke(Character.valueOf((char)j));
              Unit unit1 = Unit.INSTANCE;
            } 
            int codePoint$iv = 
              
              0xF80 ^ 
              b1$iv ^ 
              b0$iv << 6;
            if (codePoint$iv < 128) {
              int j = 65533, m = index, k = 0;
              yield.invoke(Character.valueOf((char)j));
              Unit unit1 = Unit.INSTANCE;
            } 
            int it = codePoint$iv, i = index, $i$a$-process2Utf8Bytes-Utf8$processUtf16Chars$1 = 0;
            yield.invoke(Character.valueOf((char)it));
            Unit unit = Unit.INSTANCE;
          } 
        } else {
          continue;
        } 
      } else {
        break;
      } 
      index = i + 2;
      continue;
      object = SYNTHETIC_LOCAL_VARIABLE_6;
      other$iv = 4;
      $i$f$shr = 0;
    } 
  }
  
  public static final int process2Utf8Bytes(@NotNull byte[] $this$process2Utf8Bytes, int beginIndex, int endIndex, @NotNull Function1 yield) {
    Intrinsics.checkNotNullParameter($this$process2Utf8Bytes, "<this>");
    Intrinsics.checkNotNullParameter(yield, "yield");
    int $i$f$process2Utf8Bytes = 0;
    if (endIndex <= beginIndex + 1) {
      yield.invoke(Integer.valueOf(65533));
      return 1;
    } 
    byte b0 = $this$process2Utf8Bytes[beginIndex];
    byte b1 = $this$process2Utf8Bytes[beginIndex + 1];
    int $i$f$isUtf8Continuation = 0;
    byte b = b1;
    int other$iv$iv = 192, $i$f$and = 0;
    if (!(((
      b & other$iv$iv) == 128) ? 1 : 0)) {
      yield.invoke(Integer.valueOf(65533));
      return 1;
    } 
    int codePoint = 0xF80 ^ b1 ^ b0 << 6;
    if (codePoint < 128) {
      yield.invoke(Integer.valueOf(65533));
    } else {
      yield.invoke(Integer.valueOf(codePoint));
    } 
    return 2;
  }
  
  public static final int process3Utf8Bytes(@NotNull byte[] $this$process3Utf8Bytes, int beginIndex, int endIndex, @NotNull Function1 yield) {
    Intrinsics.checkNotNullParameter($this$process3Utf8Bytes, "<this>");
    Intrinsics.checkNotNullParameter(yield, "yield");
    int $i$f$process3Utf8Bytes = 0;
    if (endIndex <= beginIndex + 2) {
      yield.invoke(Integer.valueOf(65533));
      if (endIndex > beginIndex + 1) {
        byte byte$iv = $this$process3Utf8Bytes[beginIndex + 1];
        int m = 0;
        byte b3 = byte$iv;
        int n = 192, i1 = 0;
        if (((
          b3 & n) == 128))
          return 2; 
      } 
      return 1;
    } 
    byte b0 = $this$process3Utf8Bytes[beginIndex];
    byte b1 = $this$process3Utf8Bytes[beginIndex + 1];
    int $i$f$isUtf8Continuation = 0;
    byte b = b1;
    int other$iv$iv = 192, $i$f$and = 0;
    if (!(((
      b & other$iv$iv) == 128) ? 1 : 0)) {
      yield.invoke(Integer.valueOf(65533));
      return 1;
    } 
    byte b2 = $this$process3Utf8Bytes[beginIndex + 2];
    int i = 0;
    other$iv$iv = b2;
    int j = 192, k = 0;
    if (!(((
      other$iv$iv & j) == 128) ? 1 : 0)) {
      yield.invoke(Integer.valueOf(65533));
      return 2;
    } 
    int codePoint = 0xFFFE1F80 ^ b2 ^ b1 << 6 ^ b0 << 12;
    if (codePoint < 2048) {
      yield.invoke(Integer.valueOf(65533));
    } else if ((55296 <= codePoint) ? ((codePoint < 57344)) : false) {
      yield.invoke(Integer.valueOf(65533));
    } else {
      yield.invoke(Integer.valueOf(codePoint));
    } 
    return 3;
  }
  
  public static final int process4Utf8Bytes(@NotNull byte[] $this$process4Utf8Bytes, int beginIndex, int endIndex, @NotNull Function1 yield) {
    Intrinsics.checkNotNullParameter($this$process4Utf8Bytes, "<this>");
    Intrinsics.checkNotNullParameter(yield, "yield");
    int $i$f$process4Utf8Bytes = 0;
    if (endIndex <= beginIndex + 3) {
      yield.invoke(Integer.valueOf(65533));
      if (endIndex > beginIndex + 1) {
        byte byte$iv = $this$process4Utf8Bytes[beginIndex + 1];
        int i2 = 0;
        byte b4 = byte$iv;
        int i3 = 192, i4 = 0;
        if (((
          b4 & i3) == 128)) {
          if (endIndex > beginIndex + 2) {
            byte$iv = $this$process4Utf8Bytes[beginIndex + 2];
            i2 = 0;
            byte $this$and$iv$iv = byte$iv;
            i3 = 192;
            i4 = 0;
            if (((
              $this$and$iv$iv & i3) == 128))
              return 3; 
          } 
          return 2;
        } 
      } 
      return 1;
    } 
    byte b0 = $this$process4Utf8Bytes[beginIndex];
    byte b1 = $this$process4Utf8Bytes[beginIndex + 1];
    int $i$f$isUtf8Continuation = 0;
    byte b = b1;
    int other$iv$iv = 192, $i$f$and = 0;
    if (!(((
      b & other$iv$iv) == 128) ? 1 : 0)) {
      yield.invoke(Integer.valueOf(65533));
      return 1;
    } 
    byte b2 = $this$process4Utf8Bytes[beginIndex + 2];
    int i = 0;
    other$iv$iv = b2;
    int k = 192, n = 0;
    if (!(((
      other$iv$iv & k) == 128) ? 1 : 0)) {
      yield.invoke(Integer.valueOf(65533));
      return 2;
    } 
    byte b3 = $this$process4Utf8Bytes[beginIndex + 3];
    int j = 0;
    k = b3;
    int m = 192, i1 = 0;
    if (!(((
      k & m) == 128) ? 1 : 0)) {
      yield.invoke(Integer.valueOf(65533));
      return 3;
    } 
    int codePoint = 0x381F80 ^ b3 ^ b2 << 6 ^ b1 << 12 ^ b0 << 18;
    if (codePoint > 1114111) {
      yield.invoke(Integer.valueOf(65533));
    } else if ((55296 <= codePoint) ? ((codePoint < 57344)) : false) {
      yield.invoke(Integer.valueOf(65533));
    } else if (codePoint < 65536) {
      yield.invoke(Integer.valueOf(65533));
    } else {
      yield.invoke(Integer.valueOf(codePoint));
    } 
    return 4;
  }
  
  @JvmOverloads
  @JvmName(name = "size")
  public static final long size(@NotNull String $this$utf8Size, int beginIndex) {
    Intrinsics.checkNotNullParameter($this$utf8Size, "<this>");
    return size$default($this$utf8Size, beginIndex, 0, 2, null);
  }
  
  @JvmOverloads
  @JvmName(name = "size")
  public static final long size(@NotNull String $this$utf8Size) {
    Intrinsics.checkNotNullParameter($this$utf8Size, "<this>");
    return size$default($this$utf8Size, 0, 0, 3, null);
  }
}
