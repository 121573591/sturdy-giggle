package okio.internal;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import okio.-Base64;
import okio.-SegmentedByteString;
import okio.Buffer;
import okio.ByteString;
import okio._JvmPlatformKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\000V\n\002\020\022\n\000\n\002\020\b\n\002\b\004\n\002\030\002\n\002\b\002\n\002\020\f\n\002\b\003\n\002\020\016\n\002\b\n\n\002\020\002\n\002\b\007\n\002\020\013\n\002\b\003\n\002\020\000\n\002\b\003\n\002\020\005\n\002\b\037\n\002\030\002\n\002\b\003\n\002\020\031\n\002\b\007\032\037\020\004\032\0020\0022\006\020\001\032\0020\0002\006\020\003\032\0020\002H\002¢\006\004\b\004\020\005\032\030\020\b\032\0020\0072\006\020\006\032\0020\000H\b¢\006\004\b\b\020\t\032\027\020\f\032\0020\0022\006\020\013\032\0020\nH\002¢\006\004\b\f\020\r\032\024\020\017\032\0020\016*\0020\007H\b¢\006\004\b\017\020\020\032\024\020\021\032\0020\016*\0020\007H\b¢\006\004\b\021\020\020\032\034\020\023\032\0020\002*\0020\0072\006\020\022\032\0020\007H\b¢\006\004\b\023\020\024\0324\020\032\032\0020\031*\0020\0072\006\020\025\032\0020\0022\006\020\026\032\0020\0002\006\020\027\032\0020\0022\006\020\030\032\0020\002H\b¢\006\004\b\032\020\033\032\026\020\034\032\004\030\0010\007*\0020\016H\b¢\006\004\b\034\020\035\032\024\020\036\032\0020\007*\0020\016H\b¢\006\004\b\036\020\035\032\024\020\037\032\0020\007*\0020\016H\b¢\006\004\b\037\020\035\032\034\020\"\032\0020!*\0020\0072\006\020 \032\0020\000H\b¢\006\004\b\"\020#\032\034\020\"\032\0020!*\0020\0072\006\020 \032\0020\007H\b¢\006\004\b\"\020$\032\036\020&\032\0020!*\0020\0072\b\020\022\032\004\030\0010%H\b¢\006\004\b&\020'\032\034\020*\032\0020)*\0020\0072\006\020(\032\0020\002H\b¢\006\004\b*\020+\032\024\020,\032\0020\002*\0020\007H\b¢\006\004\b,\020-\032\024\020.\032\0020\002*\0020\007H\b¢\006\004\b.\020-\032\024\020/\032\0020\016*\0020\007H\b¢\006\004\b/\020\020\032$\0201\032\0020\002*\0020\0072\006\020\022\032\0020\0002\006\0200\032\0020\002H\b¢\006\004\b1\0202\032\024\0203\032\0020\000*\0020\007H\b¢\006\004\b3\0204\032$\0205\032\0020\002*\0020\0072\006\020\022\032\0020\0002\006\0200\032\0020\002H\b¢\006\004\b5\0202\032$\0205\032\0020\002*\0020\0072\006\020\022\032\0020\0072\006\0200\032\0020\002H\b¢\006\004\b5\0206\0324\0208\032\0020!*\0020\0072\006\020\025\032\0020\0022\006\020\022\032\0020\0002\006\0207\032\0020\0022\006\020\030\032\0020\002H\b¢\006\004\b8\0209\0324\0208\032\0020!*\0020\0072\006\020\025\032\0020\0022\006\020\022\032\0020\0072\006\0207\032\0020\0022\006\020\030\032\0020\002H\b¢\006\004\b8\020:\032\034\020<\032\0020!*\0020\0072\006\020;\032\0020\000H\b¢\006\004\b<\020#\032\034\020<\032\0020!*\0020\0072\006\020;\032\0020\007H\b¢\006\004\b<\020$\032$\020?\032\0020\007*\0020\0072\006\020=\032\0020\0022\006\020>\032\0020\002H\b¢\006\004\b?\020@\032\024\020A\032\0020\007*\0020\007H\b¢\006\004\bA\020B\032\024\020C\032\0020\007*\0020\007H\b¢\006\004\bC\020B\032\024\020D\032\0020\000*\0020\007H\b¢\006\004\bD\0204\032$\020E\032\0020\007*\0020\0002\006\020\025\032\0020\0022\006\020\030\032\0020\002H\b¢\006\004\bE\020F\032\024\020G\032\0020\016*\0020\007H\b¢\006\004\bG\020\020\032\024\020H\032\0020\016*\0020\007H\b¢\006\004\bH\020\020\032+\020K\032\0020\031*\0020\0072\006\020J\032\0020I2\006\020\025\032\0020\0022\006\020\030\032\0020\002H\000¢\006\004\bK\020L\" \020N\032\0020M8\000X\004¢\006\022\n\004\bN\020O\022\004\bR\020S\032\004\bP\020Q¨\006T"}, d2 = {"", "s", "", "codePointCount", "codePointIndexToCharIndex", "([BI)I", "data", "Lokio/ByteString;", "commonOf", "([B)Lokio/ByteString;", "", "c", "decodeHexDigit", "(C)I", "", "commonBase64", "(Lokio/ByteString;)Ljava/lang/String;", "commonBase64Url", "other", "commonCompareTo", "(Lokio/ByteString;Lokio/ByteString;)I", "offset", "target", "targetOffset", "byteCount", "", "commonCopyInto", "(Lokio/ByteString;I[BII)V", "commonDecodeBase64", "(Ljava/lang/String;)Lokio/ByteString;", "commonDecodeHex", "commonEncodeUtf8", "suffix", "", "commonEndsWith", "(Lokio/ByteString;[B)Z", "(Lokio/ByteString;Lokio/ByteString;)Z", "", "commonEquals", "(Lokio/ByteString;Ljava/lang/Object;)Z", "pos", "", "commonGetByte", "(Lokio/ByteString;I)B", "commonGetSize", "(Lokio/ByteString;)I", "commonHashCode", "commonHex", "fromIndex", "commonIndexOf", "(Lokio/ByteString;[BI)I", "commonInternalArray", "(Lokio/ByteString;)[B", "commonLastIndexOf", "(Lokio/ByteString;Lokio/ByteString;I)I", "otherOffset", "commonRangeEquals", "(Lokio/ByteString;I[BII)Z", "(Lokio/ByteString;ILokio/ByteString;II)Z", "prefix", "commonStartsWith", "beginIndex", "endIndex", "commonSubstring", "(Lokio/ByteString;II)Lokio/ByteString;", "commonToAsciiLowercase", "(Lokio/ByteString;)Lokio/ByteString;", "commonToAsciiUppercase", "commonToByteArray", "commonToByteString", "([BII)Lokio/ByteString;", "commonToString", "commonUtf8", "Lokio/Buffer;", "buffer", "commonWrite", "(Lokio/ByteString;Lokio/Buffer;II)V", "", "HEX_DIGIT_CHARS", "[C", "getHEX_DIGIT_CHARS", "()[C", "getHEX_DIGIT_CHARS$annotations", "()V", "okio"})
@JvmName(name = "-ByteString")
@SourceDebugExtension({"SMAP\nByteString.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ByteString.kt\nokio/internal/-ByteString\n+ 2 Util.kt\nokio/-SegmentedByteString\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 Utf8.kt\nokio/Utf8\n*L\n1#1,363:1\n131#1,2:369\n133#1,9:372\n68#2:364\n74#2:365\n74#2:367\n74#2:368\n68#2:396\n74#2:408\n1#3:366\n1#3:371\n212#4,7:381\n122#4:388\n219#4,5:389\n122#4:394\n226#4:395\n228#4:397\n397#4,2:398\n122#4:400\n400#4,6:401\n127#4:407\n406#4:409\n122#4:410\n407#4,13:411\n122#4:424\n422#4:425\n122#4:426\n425#4:427\n230#4,3:428\n440#4,3:431\n122#4:434\n443#4:435\n127#4:436\n446#4,10:437\n127#4:447\n456#4:448\n122#4:449\n457#4,4:450\n127#4:454\n461#4:455\n122#4:456\n462#4,14:457\n122#4:471\n477#4,2:472\n122#4:474\n481#4:475\n122#4:476\n484#4:477\n234#4,3:478\n500#4,3:481\n122#4:484\n503#4:485\n127#4:486\n506#4,2:487\n127#4:489\n510#4,10:490\n127#4:500\n520#4:501\n122#4:502\n521#4,4:503\n127#4:507\n525#4:508\n122#4:509\n526#4,4:510\n127#4:514\n530#4:515\n122#4:516\n531#4,15:517\n122#4:532\n547#4,2:533\n122#4:535\n550#4,2:536\n122#4:538\n554#4:539\n122#4:540\n557#4:541\n241#4:542\n122#4:543\n242#4,5:544\n*S KotlinDebug\n*F\n+ 1 ByteString.kt\nokio/internal/-ByteString\n*L\n329#1:369,2\n329#1:372,9\n67#1:364\n68#1:365\n258#1:367\n259#1:368\n348#1:396\n348#1:408\n329#1:371\n348#1:381,7\n353#1:388\n348#1:389,5\n353#1:394\n348#1:395\n348#1:397\n348#1:398,2\n353#1:400\n348#1:401,6\n348#1:407\n348#1:409\n353#1:410\n348#1:411,13\n353#1:424\n348#1:425\n353#1:426\n348#1:427\n348#1:428,3\n348#1:431,3\n353#1:434\n348#1:435\n348#1:436\n348#1:437,10\n348#1:447\n348#1:448\n353#1:449\n348#1:450,4\n348#1:454\n348#1:455\n353#1:456\n348#1:457,14\n353#1:471\n348#1:472,2\n353#1:474\n348#1:475\n353#1:476\n348#1:477\n348#1:478,3\n348#1:481,3\n353#1:484\n348#1:485\n348#1:486\n348#1:487,2\n348#1:489\n348#1:490,10\n348#1:500\n348#1:501\n353#1:502\n348#1:503,4\n348#1:507\n348#1:508\n353#1:509\n348#1:510,4\n348#1:514\n348#1:515\n353#1:516\n348#1:517,15\n353#1:532\n348#1:533,2\n353#1:535\n348#1:536,2\n353#1:538\n348#1:539\n353#1:540\n348#1:541\n348#1:542\n353#1:543\n348#1:544,5\n*E\n"})
public final class -ByteString {
  @NotNull
  private static final char[] HEX_DIGIT_CHARS;
  
  @NotNull
  public static final String commonUtf8(@NotNull ByteString $this$commonUtf8) {
    Intrinsics.checkNotNullParameter($this$commonUtf8, "<this>");
    int $i$f$commonUtf8 = 0;
    String result = $this$commonUtf8.getUtf8$okio();
    if (result == null) {
      result = _JvmPlatformKt.toUtf8String($this$commonUtf8.internalArray$okio());
      $this$commonUtf8.setUtf8$okio(result);
    } 
    return result;
  }
  
  @NotNull
  public static final String commonBase64(@NotNull ByteString $this$commonBase64) {
    Intrinsics.checkNotNullParameter($this$commonBase64, "<this>");
    int $i$f$commonBase64 = 0;
    return -Base64.encodeBase64$default($this$commonBase64.getData$okio(), null, 1, null);
  }
  
  @NotNull
  public static final String commonBase64Url(@NotNull ByteString $this$commonBase64Url) {
    Intrinsics.checkNotNullParameter($this$commonBase64Url, "<this>");
    int $i$f$commonBase64Url = 0;
    return -Base64.encodeBase64($this$commonBase64Url.getData$okio(), -Base64.getBASE64_URL_SAFE());
  }
  
  @NotNull
  public static final char[] getHEX_DIGIT_CHARS() {
    return HEX_DIGIT_CHARS;
  }
  
  static {
    char[] arrayOfChar = new char[16];
    arrayOfChar[0] = '0';
    arrayOfChar[1] = '1';
    arrayOfChar[2] = '2';
    arrayOfChar[3] = '3';
    arrayOfChar[4] = '4';
    arrayOfChar[5] = '5';
    arrayOfChar[6] = '6';
    arrayOfChar[7] = '7';
    arrayOfChar[8] = '8';
    arrayOfChar[9] = '9';
    arrayOfChar[10] = 'a';
    arrayOfChar[11] = 'b';
    arrayOfChar[12] = 'c';
    arrayOfChar[13] = 'd';
    arrayOfChar[14] = 'e';
    arrayOfChar[15] = 'f';
    HEX_DIGIT_CHARS = arrayOfChar;
  }
  
  @NotNull
  public static final String commonHex(@NotNull ByteString $this$commonHex) {
    Intrinsics.checkNotNullParameter($this$commonHex, "<this>");
    int $i$f$commonHex = 0;
    char[] result = new char[($this$commonHex.getData$okio()).length * 2];
    int c = 0;
    byte[] arrayOfByte;
    byte b;
    int i;
    for (arrayOfByte = $this$commonHex.getData$okio(), b = 0, i = arrayOfByte.length; b < i; ) {
      byte b1 = arrayOfByte[b];
      byte b2 = b1;
      int other$iv = 4, $i$f$shr = 0;
      result[c++] = getHEX_DIGIT_CHARS()[
          
          b2 >> other$iv & 0xF];
      byte $this$shr$iv = b1;
      other$iv = 15;
      int $i$f$and = 0;
      result[c++] = getHEX_DIGIT_CHARS()[$this$shr$iv & other$iv];
      b++;
    } 
    return StringsKt.concatToString(result);
  }
  
  @NotNull
  public static final ByteString commonToAsciiLowercase(@NotNull ByteString $this$commonToAsciiLowercase) {
    Intrinsics.checkNotNullParameter($this$commonToAsciiLowercase, "<this>");
    int $i$f$commonToAsciiLowercase = 0, i = 0;
    while (i < ($this$commonToAsciiLowercase.getData$okio()).length) {
      byte c = $this$commonToAsciiLowercase.getData$okio()[i];
      if (c < 65 || c > 90) {
        i++;
        continue;
      } 
      Intrinsics.checkNotNullExpressionValue(Arrays.copyOf($this$commonToAsciiLowercase.getData$okio(), ($this$commonToAsciiLowercase.getData$okio()).length), "copyOf(this, size)");
      byte[] lowercase = Arrays.copyOf($this$commonToAsciiLowercase.getData$okio(), ($this$commonToAsciiLowercase.getData$okio()).length);
      lowercase[i++] = (byte)(c - -32);
      while (i < lowercase.length) {
        c = lowercase[i];
        if (c < 65 || c > 90) {
          i++;
          continue;
        } 
        lowercase[i] = (byte)(c - -32);
        i++;
      } 
      return new ByteString(lowercase);
    } 
    return $this$commonToAsciiLowercase;
  }
  
  @NotNull
  public static final ByteString commonToAsciiUppercase(@NotNull ByteString $this$commonToAsciiUppercase) {
    Intrinsics.checkNotNullParameter($this$commonToAsciiUppercase, "<this>");
    int $i$f$commonToAsciiUppercase = 0, i = 0;
    while (i < ($this$commonToAsciiUppercase.getData$okio()).length) {
      byte c = $this$commonToAsciiUppercase.getData$okio()[i];
      if (c < 97 || c > 122) {
        i++;
        continue;
      } 
      Intrinsics.checkNotNullExpressionValue(Arrays.copyOf($this$commonToAsciiUppercase.getData$okio(), ($this$commonToAsciiUppercase.getData$okio()).length), "copyOf(this, size)");
      byte[] lowercase = Arrays.copyOf($this$commonToAsciiUppercase.getData$okio(), ($this$commonToAsciiUppercase.getData$okio()).length);
      lowercase[i++] = (byte)(c - 32);
      while (i < lowercase.length) {
        c = lowercase[i];
        if (c < 97 || c > 122) {
          i++;
          continue;
        } 
        lowercase[i] = (byte)(c - 32);
        i++;
      } 
      return new ByteString(lowercase);
    } 
    return $this$commonToAsciiUppercase;
  }
  
  @NotNull
  public static final ByteString commonSubstring(@NotNull ByteString $this$commonSubstring, int beginIndex, int endIndex) {
    Intrinsics.checkNotNullParameter($this$commonSubstring, "<this>");
    int $i$f$commonSubstring = 0, i = -SegmentedByteString.resolveDefaultParameter($this$commonSubstring, endIndex);
    if (!((beginIndex >= 0) ? 1 : 0)) {
      int $i$a$-require--ByteString$commonSubstring$1 = 0;
      String str = "beginIndex < 0";
      throw new IllegalArgumentException(str.toString());
    } 
    if (!((i <= ($this$commonSubstring.getData$okio()).length) ? 1 : 0)) {
      int $i$a$-require--ByteString$commonSubstring$2 = 0;
      String str = "endIndex > length(" + ($this$commonSubstring.getData$okio()).length + ')';
      throw new IllegalArgumentException(str.toString());
    } 
    int subLen = i - beginIndex;
    if (!((subLen >= 0) ? 1 : 0)) {
      int $i$a$-require--ByteString$commonSubstring$3 = 0;
      String str = "endIndex < beginIndex";
      throw new IllegalArgumentException(str.toString());
    } 
    if (beginIndex == 0 && i == ($this$commonSubstring.getData$okio()).length)
      return $this$commonSubstring; 
    byte[] arrayOfByte = $this$commonSubstring.getData$okio();
    return new ByteString(ArraysKt.copyOfRange(arrayOfByte, beginIndex, i));
  }
  
  public static final byte commonGetByte(@NotNull ByteString $this$commonGetByte, int pos) {
    Intrinsics.checkNotNullParameter($this$commonGetByte, "<this>");
    int $i$f$commonGetByte = 0;
    return $this$commonGetByte.getData$okio()[pos];
  }
  
  public static final int commonGetSize(@NotNull ByteString $this$commonGetSize) {
    Intrinsics.checkNotNullParameter($this$commonGetSize, "<this>");
    int $i$f$commonGetSize = 0;
    return ($this$commonGetSize.getData$okio()).length;
  }
  
  @NotNull
  public static final byte[] commonToByteArray(@NotNull ByteString $this$commonToByteArray) {
    Intrinsics.checkNotNullParameter($this$commonToByteArray, "<this>");
    int $i$f$commonToByteArray = 0;
    Intrinsics.checkNotNullExpressionValue(Arrays.copyOf($this$commonToByteArray.getData$okio(), ($this$commonToByteArray.getData$okio()).length), "copyOf(this, size)");
    return Arrays.copyOf($this$commonToByteArray.getData$okio(), ($this$commonToByteArray.getData$okio()).length);
  }
  
  @NotNull
  public static final byte[] commonInternalArray(@NotNull ByteString $this$commonInternalArray) {
    Intrinsics.checkNotNullParameter($this$commonInternalArray, "<this>");
    int $i$f$commonInternalArray = 0;
    return $this$commonInternalArray.getData$okio();
  }
  
  public static final boolean commonRangeEquals(@NotNull ByteString $this$commonRangeEquals, int offset, @NotNull ByteString other, int otherOffset, int byteCount) {
    Intrinsics.checkNotNullParameter($this$commonRangeEquals, "<this>");
    Intrinsics.checkNotNullParameter(other, "other");
    int $i$f$commonRangeEquals = 0;
    return other.rangeEquals(otherOffset, $this$commonRangeEquals.getData$okio(), offset, byteCount);
  }
  
  public static final boolean commonRangeEquals(@NotNull ByteString $this$commonRangeEquals, int offset, @NotNull byte[] other, int otherOffset, int byteCount) {
    Intrinsics.checkNotNullParameter($this$commonRangeEquals, "<this>");
    Intrinsics.checkNotNullParameter(other, "other");
    int $i$f$commonRangeEquals = 0;
    return (offset >= 0 && offset <= ($this$commonRangeEquals.getData$okio()).length - byteCount && otherOffset >= 0 && otherOffset <= other.length - byteCount && -SegmentedByteString.arrayRangeEquals($this$commonRangeEquals.getData$okio(), offset, other, otherOffset, byteCount));
  }
  
  public static final void commonCopyInto(@NotNull ByteString $this$commonCopyInto, int offset, @NotNull byte[] target, int targetOffset, int byteCount) {
    Intrinsics.checkNotNullParameter($this$commonCopyInto, "<this>");
    Intrinsics.checkNotNullParameter(target, "target");
    int $i$f$commonCopyInto = 0;
    ArraysKt.copyInto($this$commonCopyInto.getData$okio(), target, targetOffset, offset, offset + byteCount);
  }
  
  public static final boolean commonStartsWith(@NotNull ByteString $this$commonStartsWith, @NotNull ByteString prefix) {
    Intrinsics.checkNotNullParameter($this$commonStartsWith, "<this>");
    Intrinsics.checkNotNullParameter(prefix, "prefix");
    int $i$f$commonStartsWith = 0;
    return $this$commonStartsWith.rangeEquals(0, prefix, 0, prefix.size());
  }
  
  public static final boolean commonStartsWith(@NotNull ByteString $this$commonStartsWith, @NotNull byte[] prefix) {
    Intrinsics.checkNotNullParameter($this$commonStartsWith, "<this>");
    Intrinsics.checkNotNullParameter(prefix, "prefix");
    int $i$f$commonStartsWith = 0;
    return $this$commonStartsWith.rangeEquals(0, prefix, 0, prefix.length);
  }
  
  public static final boolean commonEndsWith(@NotNull ByteString $this$commonEndsWith, @NotNull ByteString suffix) {
    Intrinsics.checkNotNullParameter($this$commonEndsWith, "<this>");
    Intrinsics.checkNotNullParameter(suffix, "suffix");
    int $i$f$commonEndsWith = 0;
    return $this$commonEndsWith.rangeEquals($this$commonEndsWith.size() - suffix.size(), suffix, 0, suffix.size());
  }
  
  public static final boolean commonEndsWith(@NotNull ByteString $this$commonEndsWith, @NotNull byte[] suffix) {
    Intrinsics.checkNotNullParameter($this$commonEndsWith, "<this>");
    Intrinsics.checkNotNullParameter(suffix, "suffix");
    int $i$f$commonEndsWith = 0;
    return $this$commonEndsWith.rangeEquals($this$commonEndsWith.size() - suffix.length, suffix, 0, suffix.length);
  }
  
  public static final int commonIndexOf(@NotNull ByteString $this$commonIndexOf, @NotNull byte[] other, int fromIndex) {
    Intrinsics.checkNotNullParameter($this$commonIndexOf, "<this>");
    Intrinsics.checkNotNullParameter(other, "other");
    int $i$f$commonIndexOf = 0, limit = ($this$commonIndexOf.getData$okio()).length - other.length;
    int i = Math.max(fromIndex, 0);
    if (i <= limit)
      while (true) {
        if (-SegmentedByteString.arrayRangeEquals($this$commonIndexOf.getData$okio(), i, other, 0, other.length))
          return i; 
        if (i != limit) {
          i++;
          continue;
        } 
        break;
      }  
    return -1;
  }
  
  public static final int commonLastIndexOf(@NotNull ByteString $this$commonLastIndexOf, @NotNull ByteString other, int fromIndex) {
    Intrinsics.checkNotNullParameter($this$commonLastIndexOf, "<this>");
    Intrinsics.checkNotNullParameter(other, "other");
    int $i$f$commonLastIndexOf = 0;
    return $this$commonLastIndexOf.lastIndexOf(other.internalArray$okio(), fromIndex);
  }
  
  public static final int commonLastIndexOf(@NotNull ByteString $this$commonLastIndexOf, @NotNull byte[] other, int fromIndex) {
    Intrinsics.checkNotNullParameter($this$commonLastIndexOf, "<this>");
    Intrinsics.checkNotNullParameter(other, "other");
    int $i$f$commonLastIndexOf = 0, j = -SegmentedByteString.resolveDefaultParameter($this$commonLastIndexOf, fromIndex);
    int limit = ($this$commonLastIndexOf.getData$okio()).length - other.length;
    for (int i = Math.min(j, limit); -1 < i; i--) {
      if (-SegmentedByteString.arrayRangeEquals($this$commonLastIndexOf.getData$okio(), i, other, 0, other.length))
        return i; 
    } 
    return -1;
  }
  
  public static final boolean commonEquals(@NotNull ByteString $this$commonEquals, @Nullable Object other) {
    Intrinsics.checkNotNullParameter($this$commonEquals, "<this>");
    int $i$f$commonEquals = 0;
    return (other == $this$commonEquals) ? true : ((other instanceof ByteString) ? ((((ByteString)other).size() == ($this$commonEquals.getData$okio()).length && ((ByteString)other).rangeEquals(0, $this$commonEquals.getData$okio(), 0, ($this$commonEquals.getData$okio()).length))) : false);
  }
  
  public static final int commonHashCode(@NotNull ByteString $this$commonHashCode) {
    Intrinsics.checkNotNullParameter($this$commonHashCode, "<this>");
    int $i$f$commonHashCode = 0, result = $this$commonHashCode.getHashCode$okio();
    if (result != 0)
      return result; 
    int i = Arrays.hashCode($this$commonHashCode.getData$okio()), it = i, $i$a$-also--ByteString$commonHashCode$1 = 0;
    $this$commonHashCode.setHashCode$okio(it);
    return i;
  }
  
  public static final int commonCompareTo(@NotNull ByteString $this$commonCompareTo, @NotNull ByteString other) {
    Intrinsics.checkNotNullParameter($this$commonCompareTo, "<this>");
    Intrinsics.checkNotNullParameter(other, "other");
    int $i$f$commonCompareTo = 0, sizeA = $this$commonCompareTo.size();
    int sizeB = other.size();
    int i = 0;
    int size = Math.min(sizeA, sizeB);
    while (i < size) {
      byte b = $this$commonCompareTo.getByte(i);
      int other$iv = 255, $i$f$and = 0, byteA = b & other$iv;
      other$iv = other.getByte(i);
      int j = 255, k = 0, byteB = other$iv & j;
      if (byteA == byteB) {
        i++;
        continue;
      } 
      return (byteA < byteB) ? -1 : 1;
    } 
    if (sizeA == sizeB)
      return 0; 
    return (sizeA < sizeB) ? -1 : 1;
  }
  
  @NotNull
  public static final ByteString commonOf(@NotNull byte[] data) {
    Intrinsics.checkNotNullParameter(data, "data");
    int $i$f$commonOf = 0;
    Intrinsics.checkNotNullExpressionValue(Arrays.copyOf(data, data.length), "copyOf(this, size)");
    return new ByteString(Arrays.copyOf(data, data.length));
  }
  
  @NotNull
  public static final ByteString commonToByteString(@NotNull byte[] $this$commonToByteString, int offset, int byteCount) {
    Intrinsics.checkNotNullParameter($this$commonToByteString, "<this>");
    int $i$f$commonToByteString = 0, i = -SegmentedByteString.resolveDefaultParameter($this$commonToByteString, byteCount);
    -SegmentedByteString.checkOffsetAndCount($this$commonToByteString.length, offset, i);
    byte[] arrayOfByte = $this$commonToByteString;
    int j = offset + i;
    return new ByteString(ArraysKt.copyOfRange(arrayOfByte, offset, j));
  }
  
  @NotNull
  public static final ByteString commonEncodeUtf8(@NotNull String $this$commonEncodeUtf8) {
    Intrinsics.checkNotNullParameter($this$commonEncodeUtf8, "<this>");
    int $i$f$commonEncodeUtf8 = 0;
    ByteString byteString = new ByteString(_JvmPlatformKt.asUtf8ToByteArray($this$commonEncodeUtf8));
    byteString.setUtf8$okio($this$commonEncodeUtf8);
    return byteString;
  }
  
  @Nullable
  public static final ByteString commonDecodeBase64(@NotNull String $this$commonDecodeBase64) {
    Intrinsics.checkNotNullParameter($this$commonDecodeBase64, "<this>");
    int $i$f$commonDecodeBase64 = 0;
    byte[] decoded = -Base64.decodeBase64ToArray($this$commonDecodeBase64);
    return (decoded != null) ? new ByteString(decoded) : null;
  }
  
  @NotNull
  public static final ByteString commonDecodeHex(@NotNull String $this$commonDecodeHex) {
    Intrinsics.checkNotNullParameter($this$commonDecodeHex, "<this>");
    int $i$f$commonDecodeHex = 0;
    if (!(($this$commonDecodeHex.length() % 2 == 0) ? 1 : 0)) {
      int $i$a$-require--ByteString$commonDecodeHex$1 = 0;
      String str = "Unexpected hex string: " + $this$commonDecodeHex;
      throw new IllegalArgumentException(str.toString());
    } 
    byte[] result = new byte[$this$commonDecodeHex.length() / 2];
    for (int i = 0, j = result.length; i < j; i++) {
      int d1 = access$decodeHexDigit($this$commonDecodeHex.charAt(i * 2)) << 4;
      int d2 = access$decodeHexDigit($this$commonDecodeHex.charAt(i * 2 + 1));
      result[i] = (byte)(d1 + d2);
    } 
    return new ByteString(result);
  }
  
  public static final void commonWrite(@NotNull ByteString $this$commonWrite, @NotNull Buffer buffer, int offset, int byteCount) {
    Intrinsics.checkNotNullParameter($this$commonWrite, "<this>");
    Intrinsics.checkNotNullParameter(buffer, "buffer");
    buffer.write($this$commonWrite.getData$okio(), offset, byteCount);
  }
  
  private static final int decodeHexDigit(char c) {
    char c1 = c;
    if (('A' <= c1) ? ((c1 < 'G')) : false) {
    
    } else {
      throw new IllegalArgumentException("Unexpected hex digit: " + c);
    } 
    return (('0' <= c1) ? ((c1 < ':')) : false) ? (c - 48) : ((('a' <= c1) ? ((c1 < 'g')) : false) ? (c - 97 + 10) : "JD-Core does not support Kotlin");
  }
  
  @NotNull
  public static final String commonToString(@NotNull ByteString $this$commonToString) {
    Intrinsics.checkNotNullParameter($this$commonToString, "<this>");
    int $i$f$commonToString = 0;
    if ((($this$commonToString.getData$okio()).length == 0))
      return "[size=0]"; 
    int i = access$codePointIndexToCharIndex($this$commonToString.getData$okio(), 64);
    if (i == -1) {
      ByteString byteString = $this$commonToString;
      byte b = 0;
      int endIndex$iv = 64, $i$f$commonSubstring = 0;
      int j = -SegmentedByteString.resolveDefaultParameter(byteString, endIndex$iv);
      if (!((j <= (byteString.getData$okio()).length) ? 1 : 0)) {
        int $i$a$-require--ByteString$commonSubstring$2$iv = 0;
        String str = "endIndex > length(" + (byteString.getData$okio()).length + ')';
        throw new IllegalArgumentException(str.toString());
      } 
      int subLen$iv = j - b;
      if (!((subLen$iv >= 0) ? 1 : 0)) {
        int $i$a$-require--ByteString$commonSubstring$3$iv = 0;
        String str = "endIndex < beginIndex";
        throw new IllegalArgumentException(str.toString());
      } 
      byte[] arrayOfByte = byteString.getData$okio();
      return (($this$commonToString.getData$okio()).length <= 64) ? ("[hex=" + $this$commonToString.hex() + ']') : ("[size=" + ($this$commonToString.getData$okio()).length + " hex=" + ((j == (byteString.getData$okio()).length) ? byteString : new ByteString(ArraysKt.copyOfRange(arrayOfByte, b, j))).hex() + "…]");
    } 
    String text = $this$commonToString.utf8();
    Intrinsics.checkNotNullExpressionValue(text.substring(0, i), "this as java.lang.String…ing(startIndex, endIndex)");
    String safeText = StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(text.substring(0, i), "\\", "\\\\", false, 4, null), "\n", "\\n", false, 4, null), "\r", "\\r", false, 4, null);
    return (i < text.length()) ? ("[size=" + ($this$commonToString.getData$okio()).length + " text=" + safeText + "…]") : ("[text=" + safeText + ']');
  }
  
  private static final int codePointIndexToCharIndex(byte[] s, int codePointCount) {
    // Byte code:
    //   0: iconst_0
    //   1: istore_2
    //   2: iconst_0
    //   3: istore_3
    //   4: aload_0
    //   5: astore #4
    //   7: iconst_0
    //   8: istore #5
    //   10: aload_0
    //   11: arraylength
    //   12: istore #6
    //   14: iconst_0
    //   15: istore #7
    //   17: iload #5
    //   19: istore #8
    //   21: iload #8
    //   23: iload #6
    //   25: if_icmpge -> 3870
    //   28: aload #4
    //   30: iload #8
    //   32: baload
    //   33: istore #9
    //   35: nop
    //   36: iload #9
    //   38: iflt -> 326
    //   41: iload #9
    //   43: istore #10
    //   45: iconst_0
    //   46: istore #11
    //   48: iload_3
    //   49: istore #12
    //   51: iload #12
    //   53: iconst_1
    //   54: iadd
    //   55: istore_3
    //   56: iload #12
    //   58: iload_1
    //   59: if_icmpne -> 64
    //   62: iload_2
    //   63: ireturn
    //   64: iload #10
    //   66: bipush #10
    //   68: if_icmpeq -> 141
    //   71: iload #10
    //   73: bipush #13
    //   75: if_icmpeq -> 141
    //   78: iconst_0
    //   79: istore #12
    //   81: iconst_0
    //   82: iload #10
    //   84: if_icmpgt -> 102
    //   87: iload #10
    //   89: bipush #32
    //   91: if_icmpge -> 98
    //   94: iconst_1
    //   95: goto -> 103
    //   98: iconst_0
    //   99: goto -> 103
    //   102: iconst_0
    //   103: ifne -> 133
    //   106: bipush #127
    //   108: iload #10
    //   110: if_icmpgt -> 129
    //   113: iload #10
    //   115: sipush #160
    //   118: if_icmpge -> 125
    //   121: iconst_1
    //   122: goto -> 130
    //   125: iconst_0
    //   126: goto -> 130
    //   129: iconst_0
    //   130: ifeq -> 137
    //   133: iconst_1
    //   134: goto -> 138
    //   137: iconst_0
    //   138: ifne -> 149
    //   141: iload #10
    //   143: ldc_w 65533
    //   146: if_icmpne -> 151
    //   149: iconst_m1
    //   150: ireturn
    //   151: iload_2
    //   152: iload #10
    //   154: ldc_w 65536
    //   157: if_icmpge -> 164
    //   160: iconst_1
    //   161: goto -> 165
    //   164: iconst_2
    //   165: iadd
    //   166: istore_2
    //   167: nop
    //   168: nop
    //   169: iload #8
    //   171: iinc #8, 1
    //   174: pop
    //   175: iload #8
    //   177: iload #6
    //   179: if_icmpge -> 21
    //   182: aload #4
    //   184: iload #8
    //   186: baload
    //   187: iflt -> 21
    //   190: aload #4
    //   192: iload #8
    //   194: iinc #8, 1
    //   197: baload
    //   198: istore #10
    //   200: iconst_0
    //   201: istore #11
    //   203: iload_3
    //   204: istore #12
    //   206: iload #12
    //   208: iconst_1
    //   209: iadd
    //   210: istore_3
    //   211: iload #12
    //   213: iload_1
    //   214: if_icmpne -> 219
    //   217: iload_2
    //   218: ireturn
    //   219: iload #10
    //   221: bipush #10
    //   223: if_icmpeq -> 296
    //   226: iload #10
    //   228: bipush #13
    //   230: if_icmpeq -> 296
    //   233: iconst_0
    //   234: istore #12
    //   236: iconst_0
    //   237: iload #10
    //   239: if_icmpgt -> 257
    //   242: iload #10
    //   244: bipush #32
    //   246: if_icmpge -> 253
    //   249: iconst_1
    //   250: goto -> 258
    //   253: iconst_0
    //   254: goto -> 258
    //   257: iconst_0
    //   258: ifne -> 288
    //   261: bipush #127
    //   263: iload #10
    //   265: if_icmpgt -> 284
    //   268: iload #10
    //   270: sipush #160
    //   273: if_icmpge -> 280
    //   276: iconst_1
    //   277: goto -> 285
    //   280: iconst_0
    //   281: goto -> 285
    //   284: iconst_0
    //   285: ifeq -> 292
    //   288: iconst_1
    //   289: goto -> 293
    //   292: iconst_0
    //   293: ifne -> 304
    //   296: iload #10
    //   298: ldc_w 65533
    //   301: if_icmpne -> 306
    //   304: iconst_m1
    //   305: ireturn
    //   306: iload_2
    //   307: iload #10
    //   309: ldc_w 65536
    //   312: if_icmpge -> 319
    //   315: iconst_1
    //   316: goto -> 320
    //   319: iconst_2
    //   320: iadd
    //   321: istore_2
    //   322: nop
    //   323: goto -> 175
    //   326: iload #9
    //   328: istore #13
    //   330: iconst_5
    //   331: istore #14
    //   333: iconst_0
    //   334: istore #15
    //   336: iload #13
    //   338: iload #14
    //   340: ishr
    //   341: bipush #-2
    //   343: if_icmpne -> 1037
    //   346: iload #8
    //   348: aload #4
    //   350: astore #13
    //   352: iconst_0
    //   353: istore #14
    //   355: iload #6
    //   357: iload #8
    //   359: iconst_1
    //   360: iadd
    //   361: if_icmpgt -> 514
    //   364: ldc_w 65533
    //   367: istore #15
    //   369: istore #16
    //   371: iconst_0
    //   372: istore #17
    //   374: iload #15
    //   376: istore #10
    //   378: iconst_0
    //   379: istore #11
    //   381: iload_3
    //   382: istore #12
    //   384: iload #12
    //   386: iconst_1
    //   387: iadd
    //   388: istore_3
    //   389: iload #12
    //   391: iload_1
    //   392: if_icmpne -> 397
    //   395: iload_2
    //   396: ireturn
    //   397: iload #10
    //   399: bipush #10
    //   401: if_icmpeq -> 474
    //   404: iload #10
    //   406: bipush #13
    //   408: if_icmpeq -> 474
    //   411: iconst_0
    //   412: istore #12
    //   414: iconst_0
    //   415: iload #10
    //   417: if_icmpgt -> 435
    //   420: iload #10
    //   422: bipush #32
    //   424: if_icmpge -> 431
    //   427: iconst_1
    //   428: goto -> 436
    //   431: iconst_0
    //   432: goto -> 436
    //   435: iconst_0
    //   436: ifne -> 466
    //   439: bipush #127
    //   441: iload #10
    //   443: if_icmpgt -> 462
    //   446: iload #10
    //   448: sipush #160
    //   451: if_icmpge -> 458
    //   454: iconst_1
    //   455: goto -> 463
    //   458: iconst_0
    //   459: goto -> 463
    //   462: iconst_0
    //   463: ifeq -> 470
    //   466: iconst_1
    //   467: goto -> 471
    //   470: iconst_0
    //   471: ifne -> 482
    //   474: iload #10
    //   476: ldc_w 65533
    //   479: if_icmpne -> 484
    //   482: iconst_m1
    //   483: ireturn
    //   484: iload_2
    //   485: iload #10
    //   487: ldc_w 65536
    //   490: if_icmpge -> 497
    //   493: iconst_1
    //   494: goto -> 498
    //   497: iconst_2
    //   498: iadd
    //   499: istore_2
    //   500: nop
    //   501: nop
    //   502: getstatic kotlin/Unit.INSTANCE : Lkotlin/Unit;
    //   505: astore #18
    //   507: iload #16
    //   509: nop
    //   510: iconst_1
    //   511: goto -> 1031
    //   514: aload #13
    //   516: iload #8
    //   518: baload
    //   519: istore #19
    //   521: aload #13
    //   523: iload #8
    //   525: iconst_1
    //   526: iadd
    //   527: baload
    //   528: istore #20
    //   530: iconst_0
    //   531: istore #21
    //   533: iload #20
    //   535: istore #22
    //   537: sipush #192
    //   540: istore #23
    //   542: iconst_0
    //   543: istore #24
    //   545: iload #22
    //   547: iload #23
    //   549: iand
    //   550: sipush #128
    //   553: if_icmpne -> 560
    //   556: iconst_1
    //   557: goto -> 561
    //   560: iconst_0
    //   561: ifne -> 714
    //   564: ldc_w 65533
    //   567: istore #15
    //   569: istore #16
    //   571: iconst_0
    //   572: istore #17
    //   574: iload #15
    //   576: istore #10
    //   578: iconst_0
    //   579: istore #11
    //   581: iload_3
    //   582: istore #12
    //   584: iload #12
    //   586: iconst_1
    //   587: iadd
    //   588: istore_3
    //   589: iload #12
    //   591: iload_1
    //   592: if_icmpne -> 597
    //   595: iload_2
    //   596: ireturn
    //   597: iload #10
    //   599: bipush #10
    //   601: if_icmpeq -> 674
    //   604: iload #10
    //   606: bipush #13
    //   608: if_icmpeq -> 674
    //   611: iconst_0
    //   612: istore #12
    //   614: iconst_0
    //   615: iload #10
    //   617: if_icmpgt -> 635
    //   620: iload #10
    //   622: bipush #32
    //   624: if_icmpge -> 631
    //   627: iconst_1
    //   628: goto -> 636
    //   631: iconst_0
    //   632: goto -> 636
    //   635: iconst_0
    //   636: ifne -> 666
    //   639: bipush #127
    //   641: iload #10
    //   643: if_icmpgt -> 662
    //   646: iload #10
    //   648: sipush #160
    //   651: if_icmpge -> 658
    //   654: iconst_1
    //   655: goto -> 663
    //   658: iconst_0
    //   659: goto -> 663
    //   662: iconst_0
    //   663: ifeq -> 670
    //   666: iconst_1
    //   667: goto -> 671
    //   670: iconst_0
    //   671: ifne -> 682
    //   674: iload #10
    //   676: ldc_w 65533
    //   679: if_icmpne -> 684
    //   682: iconst_m1
    //   683: ireturn
    //   684: iload_2
    //   685: iload #10
    //   687: ldc_w 65536
    //   690: if_icmpge -> 697
    //   693: iconst_1
    //   694: goto -> 698
    //   697: iconst_2
    //   698: iadd
    //   699: istore_2
    //   700: nop
    //   701: nop
    //   702: getstatic kotlin/Unit.INSTANCE : Lkotlin/Unit;
    //   705: astore #18
    //   707: iload #16
    //   709: nop
    //   710: iconst_1
    //   711: goto -> 1031
    //   714: sipush #3968
    //   717: iload #20
    //   719: ixor
    //   720: iload #19
    //   722: bipush #6
    //   724: ishl
    //   725: ixor
    //   726: istore #21
    //   728: nop
    //   729: iload #21
    //   731: sipush #128
    //   734: if_icmpge -> 885
    //   737: ldc_w 65533
    //   740: istore #15
    //   742: istore #16
    //   744: iconst_0
    //   745: istore #17
    //   747: iload #15
    //   749: istore #10
    //   751: iconst_0
    //   752: istore #11
    //   754: iload_3
    //   755: istore #12
    //   757: iload #12
    //   759: iconst_1
    //   760: iadd
    //   761: istore_3
    //   762: iload #12
    //   764: iload_1
    //   765: if_icmpne -> 770
    //   768: iload_2
    //   769: ireturn
    //   770: iload #10
    //   772: bipush #10
    //   774: if_icmpeq -> 847
    //   777: iload #10
    //   779: bipush #13
    //   781: if_icmpeq -> 847
    //   784: iconst_0
    //   785: istore #12
    //   787: iconst_0
    //   788: iload #10
    //   790: if_icmpgt -> 808
    //   793: iload #10
    //   795: bipush #32
    //   797: if_icmpge -> 804
    //   800: iconst_1
    //   801: goto -> 809
    //   804: iconst_0
    //   805: goto -> 809
    //   808: iconst_0
    //   809: ifne -> 839
    //   812: bipush #127
    //   814: iload #10
    //   816: if_icmpgt -> 835
    //   819: iload #10
    //   821: sipush #160
    //   824: if_icmpge -> 831
    //   827: iconst_1
    //   828: goto -> 836
    //   831: iconst_0
    //   832: goto -> 836
    //   835: iconst_0
    //   836: ifeq -> 843
    //   839: iconst_1
    //   840: goto -> 844
    //   843: iconst_0
    //   844: ifne -> 855
    //   847: iload #10
    //   849: ldc_w 65533
    //   852: if_icmpne -> 857
    //   855: iconst_m1
    //   856: ireturn
    //   857: iload_2
    //   858: iload #10
    //   860: ldc_w 65536
    //   863: if_icmpge -> 870
    //   866: iconst_1
    //   867: goto -> 871
    //   870: iconst_2
    //   871: iadd
    //   872: istore_2
    //   873: nop
    //   874: nop
    //   875: getstatic kotlin/Unit.INSTANCE : Lkotlin/Unit;
    //   878: astore #18
    //   880: iload #16
    //   882: goto -> 1030
    //   885: iload #21
    //   887: istore #15
    //   889: istore #16
    //   891: iconst_0
    //   892: istore #17
    //   894: iload #15
    //   896: istore #10
    //   898: iconst_0
    //   899: istore #11
    //   901: iload_3
    //   902: istore #12
    //   904: iload #12
    //   906: iconst_1
    //   907: iadd
    //   908: istore_3
    //   909: iload #12
    //   911: iload_1
    //   912: if_icmpne -> 917
    //   915: iload_2
    //   916: ireturn
    //   917: iload #10
    //   919: bipush #10
    //   921: if_icmpeq -> 994
    //   924: iload #10
    //   926: bipush #13
    //   928: if_icmpeq -> 994
    //   931: iconst_0
    //   932: istore #12
    //   934: iconst_0
    //   935: iload #10
    //   937: if_icmpgt -> 955
    //   940: iload #10
    //   942: bipush #32
    //   944: if_icmpge -> 951
    //   947: iconst_1
    //   948: goto -> 956
    //   951: iconst_0
    //   952: goto -> 956
    //   955: iconst_0
    //   956: ifne -> 986
    //   959: bipush #127
    //   961: iload #10
    //   963: if_icmpgt -> 982
    //   966: iload #10
    //   968: sipush #160
    //   971: if_icmpge -> 978
    //   974: iconst_1
    //   975: goto -> 983
    //   978: iconst_0
    //   979: goto -> 983
    //   982: iconst_0
    //   983: ifeq -> 990
    //   986: iconst_1
    //   987: goto -> 991
    //   990: iconst_0
    //   991: ifne -> 1002
    //   994: iload #10
    //   996: ldc_w 65533
    //   999: if_icmpne -> 1004
    //   1002: iconst_m1
    //   1003: ireturn
    //   1004: iload_2
    //   1005: iload #10
    //   1007: ldc_w 65536
    //   1010: if_icmpge -> 1017
    //   1013: iconst_1
    //   1014: goto -> 1018
    //   1017: iconst_2
    //   1018: iadd
    //   1019: istore_2
    //   1020: nop
    //   1021: nop
    //   1022: getstatic kotlin/Unit.INSTANCE : Lkotlin/Unit;
    //   1025: astore #18
    //   1027: iload #16
    //   1029: nop
    //   1030: iconst_2
    //   1031: iadd
    //   1032: istore #8
    //   1034: goto -> 21
    //   1037: iload #9
    //   1039: istore #13
    //   1041: iconst_4
    //   1042: istore #14
    //   1044: iconst_0
    //   1045: istore #15
    //   1047: iload #13
    //   1049: iload #14
    //   1051: ishr
    //   1052: bipush #-2
    //   1054: if_icmpne -> 2179
    //   1057: iload #8
    //   1059: aload #4
    //   1061: astore #13
    //   1063: iconst_0
    //   1064: istore #14
    //   1066: iload #6
    //   1068: iload #8
    //   1070: iconst_2
    //   1071: iadd
    //   1072: if_icmpgt -> 1281
    //   1075: ldc_w 65533
    //   1078: istore #15
    //   1080: istore #16
    //   1082: iconst_0
    //   1083: istore #17
    //   1085: iload #15
    //   1087: istore #10
    //   1089: iconst_0
    //   1090: istore #11
    //   1092: iload_3
    //   1093: istore #12
    //   1095: iload #12
    //   1097: iconst_1
    //   1098: iadd
    //   1099: istore_3
    //   1100: iload #12
    //   1102: iload_1
    //   1103: if_icmpne -> 1108
    //   1106: iload_2
    //   1107: ireturn
    //   1108: iload #10
    //   1110: bipush #10
    //   1112: if_icmpeq -> 1185
    //   1115: iload #10
    //   1117: bipush #13
    //   1119: if_icmpeq -> 1185
    //   1122: iconst_0
    //   1123: istore #12
    //   1125: iconst_0
    //   1126: iload #10
    //   1128: if_icmpgt -> 1146
    //   1131: iload #10
    //   1133: bipush #32
    //   1135: if_icmpge -> 1142
    //   1138: iconst_1
    //   1139: goto -> 1147
    //   1142: iconst_0
    //   1143: goto -> 1147
    //   1146: iconst_0
    //   1147: ifne -> 1177
    //   1150: bipush #127
    //   1152: iload #10
    //   1154: if_icmpgt -> 1173
    //   1157: iload #10
    //   1159: sipush #160
    //   1162: if_icmpge -> 1169
    //   1165: iconst_1
    //   1166: goto -> 1174
    //   1169: iconst_0
    //   1170: goto -> 1174
    //   1173: iconst_0
    //   1174: ifeq -> 1181
    //   1177: iconst_1
    //   1178: goto -> 1182
    //   1181: iconst_0
    //   1182: ifne -> 1193
    //   1185: iload #10
    //   1187: ldc_w 65533
    //   1190: if_icmpne -> 1195
    //   1193: iconst_m1
    //   1194: ireturn
    //   1195: iload_2
    //   1196: iload #10
    //   1198: ldc_w 65536
    //   1201: if_icmpge -> 1208
    //   1204: iconst_1
    //   1205: goto -> 1209
    //   1208: iconst_2
    //   1209: iadd
    //   1210: istore_2
    //   1211: nop
    //   1212: nop
    //   1213: getstatic kotlin/Unit.INSTANCE : Lkotlin/Unit;
    //   1216: astore #18
    //   1218: iload #16
    //   1220: nop
    //   1221: iload #6
    //   1223: iload #8
    //   1225: iconst_1
    //   1226: iadd
    //   1227: if_icmple -> 1273
    //   1230: aload #13
    //   1232: iload #8
    //   1234: iconst_1
    //   1235: iadd
    //   1236: baload
    //   1237: istore #19
    //   1239: iconst_0
    //   1240: istore #20
    //   1242: iload #19
    //   1244: istore #21
    //   1246: sipush #192
    //   1249: istore #22
    //   1251: iconst_0
    //   1252: istore #23
    //   1254: iload #21
    //   1256: iload #22
    //   1258: iand
    //   1259: sipush #128
    //   1262: if_icmpne -> 1269
    //   1265: iconst_1
    //   1266: goto -> 1270
    //   1269: iconst_0
    //   1270: ifne -> 1277
    //   1273: iconst_1
    //   1274: goto -> 2173
    //   1277: iconst_2
    //   1278: goto -> 2173
    //   1281: aload #13
    //   1283: iload #8
    //   1285: baload
    //   1286: istore #19
    //   1288: aload #13
    //   1290: iload #8
    //   1292: iconst_1
    //   1293: iadd
    //   1294: baload
    //   1295: istore #20
    //   1297: iconst_0
    //   1298: istore #21
    //   1300: iload #20
    //   1302: istore #22
    //   1304: sipush #192
    //   1307: istore #23
    //   1309: iconst_0
    //   1310: istore #24
    //   1312: iload #22
    //   1314: iload #23
    //   1316: iand
    //   1317: sipush #128
    //   1320: if_icmpne -> 1327
    //   1323: iconst_1
    //   1324: goto -> 1328
    //   1327: iconst_0
    //   1328: ifne -> 1481
    //   1331: ldc_w 65533
    //   1334: istore #15
    //   1336: istore #16
    //   1338: iconst_0
    //   1339: istore #17
    //   1341: iload #15
    //   1343: istore #10
    //   1345: iconst_0
    //   1346: istore #11
    //   1348: iload_3
    //   1349: istore #12
    //   1351: iload #12
    //   1353: iconst_1
    //   1354: iadd
    //   1355: istore_3
    //   1356: iload #12
    //   1358: iload_1
    //   1359: if_icmpne -> 1364
    //   1362: iload_2
    //   1363: ireturn
    //   1364: iload #10
    //   1366: bipush #10
    //   1368: if_icmpeq -> 1441
    //   1371: iload #10
    //   1373: bipush #13
    //   1375: if_icmpeq -> 1441
    //   1378: iconst_0
    //   1379: istore #12
    //   1381: iconst_0
    //   1382: iload #10
    //   1384: if_icmpgt -> 1402
    //   1387: iload #10
    //   1389: bipush #32
    //   1391: if_icmpge -> 1398
    //   1394: iconst_1
    //   1395: goto -> 1403
    //   1398: iconst_0
    //   1399: goto -> 1403
    //   1402: iconst_0
    //   1403: ifne -> 1433
    //   1406: bipush #127
    //   1408: iload #10
    //   1410: if_icmpgt -> 1429
    //   1413: iload #10
    //   1415: sipush #160
    //   1418: if_icmpge -> 1425
    //   1421: iconst_1
    //   1422: goto -> 1430
    //   1425: iconst_0
    //   1426: goto -> 1430
    //   1429: iconst_0
    //   1430: ifeq -> 1437
    //   1433: iconst_1
    //   1434: goto -> 1438
    //   1437: iconst_0
    //   1438: ifne -> 1449
    //   1441: iload #10
    //   1443: ldc_w 65533
    //   1446: if_icmpne -> 1451
    //   1449: iconst_m1
    //   1450: ireturn
    //   1451: iload_2
    //   1452: iload #10
    //   1454: ldc_w 65536
    //   1457: if_icmpge -> 1464
    //   1460: iconst_1
    //   1461: goto -> 1465
    //   1464: iconst_2
    //   1465: iadd
    //   1466: istore_2
    //   1467: nop
    //   1468: nop
    //   1469: getstatic kotlin/Unit.INSTANCE : Lkotlin/Unit;
    //   1472: astore #18
    //   1474: iload #16
    //   1476: nop
    //   1477: iconst_1
    //   1478: goto -> 2173
    //   1481: aload #13
    //   1483: iload #8
    //   1485: iconst_2
    //   1486: iadd
    //   1487: baload
    //   1488: istore #21
    //   1490: iconst_0
    //   1491: istore #22
    //   1493: iload #21
    //   1495: istore #23
    //   1497: sipush #192
    //   1500: istore #24
    //   1502: iconst_0
    //   1503: istore #25
    //   1505: iload #23
    //   1507: iload #24
    //   1509: iand
    //   1510: sipush #128
    //   1513: if_icmpne -> 1520
    //   1516: iconst_1
    //   1517: goto -> 1521
    //   1520: iconst_0
    //   1521: ifne -> 1674
    //   1524: ldc_w 65533
    //   1527: istore #15
    //   1529: istore #16
    //   1531: iconst_0
    //   1532: istore #17
    //   1534: iload #15
    //   1536: istore #10
    //   1538: iconst_0
    //   1539: istore #11
    //   1541: iload_3
    //   1542: istore #12
    //   1544: iload #12
    //   1546: iconst_1
    //   1547: iadd
    //   1548: istore_3
    //   1549: iload #12
    //   1551: iload_1
    //   1552: if_icmpne -> 1557
    //   1555: iload_2
    //   1556: ireturn
    //   1557: iload #10
    //   1559: bipush #10
    //   1561: if_icmpeq -> 1634
    //   1564: iload #10
    //   1566: bipush #13
    //   1568: if_icmpeq -> 1634
    //   1571: iconst_0
    //   1572: istore #12
    //   1574: iconst_0
    //   1575: iload #10
    //   1577: if_icmpgt -> 1595
    //   1580: iload #10
    //   1582: bipush #32
    //   1584: if_icmpge -> 1591
    //   1587: iconst_1
    //   1588: goto -> 1596
    //   1591: iconst_0
    //   1592: goto -> 1596
    //   1595: iconst_0
    //   1596: ifne -> 1626
    //   1599: bipush #127
    //   1601: iload #10
    //   1603: if_icmpgt -> 1622
    //   1606: iload #10
    //   1608: sipush #160
    //   1611: if_icmpge -> 1618
    //   1614: iconst_1
    //   1615: goto -> 1623
    //   1618: iconst_0
    //   1619: goto -> 1623
    //   1622: iconst_0
    //   1623: ifeq -> 1630
    //   1626: iconst_1
    //   1627: goto -> 1631
    //   1630: iconst_0
    //   1631: ifne -> 1642
    //   1634: iload #10
    //   1636: ldc_w 65533
    //   1639: if_icmpne -> 1644
    //   1642: iconst_m1
    //   1643: ireturn
    //   1644: iload_2
    //   1645: iload #10
    //   1647: ldc_w 65536
    //   1650: if_icmpge -> 1657
    //   1653: iconst_1
    //   1654: goto -> 1658
    //   1657: iconst_2
    //   1658: iadd
    //   1659: istore_2
    //   1660: nop
    //   1661: nop
    //   1662: getstatic kotlin/Unit.INSTANCE : Lkotlin/Unit;
    //   1665: astore #18
    //   1667: iload #16
    //   1669: nop
    //   1670: iconst_2
    //   1671: goto -> 2173
    //   1674: ldc_w -123008
    //   1677: iload #21
    //   1679: ixor
    //   1680: iload #20
    //   1682: bipush #6
    //   1684: ishl
    //   1685: ixor
    //   1686: iload #19
    //   1688: bipush #12
    //   1690: ishl
    //   1691: ixor
    //   1692: istore #22
    //   1694: nop
    //   1695: iload #22
    //   1697: sipush #2048
    //   1700: if_icmpge -> 1851
    //   1703: ldc_w 65533
    //   1706: istore #15
    //   1708: istore #16
    //   1710: iconst_0
    //   1711: istore #17
    //   1713: iload #15
    //   1715: istore #10
    //   1717: iconst_0
    //   1718: istore #11
    //   1720: iload_3
    //   1721: istore #12
    //   1723: iload #12
    //   1725: iconst_1
    //   1726: iadd
    //   1727: istore_3
    //   1728: iload #12
    //   1730: iload_1
    //   1731: if_icmpne -> 1736
    //   1734: iload_2
    //   1735: ireturn
    //   1736: iload #10
    //   1738: bipush #10
    //   1740: if_icmpeq -> 1813
    //   1743: iload #10
    //   1745: bipush #13
    //   1747: if_icmpeq -> 1813
    //   1750: iconst_0
    //   1751: istore #12
    //   1753: iconst_0
    //   1754: iload #10
    //   1756: if_icmpgt -> 1774
    //   1759: iload #10
    //   1761: bipush #32
    //   1763: if_icmpge -> 1770
    //   1766: iconst_1
    //   1767: goto -> 1775
    //   1770: iconst_0
    //   1771: goto -> 1775
    //   1774: iconst_0
    //   1775: ifne -> 1805
    //   1778: bipush #127
    //   1780: iload #10
    //   1782: if_icmpgt -> 1801
    //   1785: iload #10
    //   1787: sipush #160
    //   1790: if_icmpge -> 1797
    //   1793: iconst_1
    //   1794: goto -> 1802
    //   1797: iconst_0
    //   1798: goto -> 1802
    //   1801: iconst_0
    //   1802: ifeq -> 1809
    //   1805: iconst_1
    //   1806: goto -> 1810
    //   1809: iconst_0
    //   1810: ifne -> 1821
    //   1813: iload #10
    //   1815: ldc_w 65533
    //   1818: if_icmpne -> 1823
    //   1821: iconst_m1
    //   1822: ireturn
    //   1823: iload_2
    //   1824: iload #10
    //   1826: ldc_w 65536
    //   1829: if_icmpge -> 1836
    //   1832: iconst_1
    //   1833: goto -> 1837
    //   1836: iconst_2
    //   1837: iadd
    //   1838: istore_2
    //   1839: nop
    //   1840: nop
    //   1841: getstatic kotlin/Unit.INSTANCE : Lkotlin/Unit;
    //   1844: astore #18
    //   1846: iload #16
    //   1848: goto -> 2172
    //   1851: ldc_w 55296
    //   1854: iload #22
    //   1856: if_icmpgt -> 1875
    //   1859: iload #22
    //   1861: ldc_w 57344
    //   1864: if_icmpge -> 1871
    //   1867: iconst_1
    //   1868: goto -> 1876
    //   1871: iconst_0
    //   1872: goto -> 1876
    //   1875: iconst_0
    //   1876: ifeq -> 2027
    //   1879: ldc_w 65533
    //   1882: istore #15
    //   1884: istore #16
    //   1886: iconst_0
    //   1887: istore #17
    //   1889: iload #15
    //   1891: istore #10
    //   1893: iconst_0
    //   1894: istore #11
    //   1896: iload_3
    //   1897: istore #12
    //   1899: iload #12
    //   1901: iconst_1
    //   1902: iadd
    //   1903: istore_3
    //   1904: iload #12
    //   1906: iload_1
    //   1907: if_icmpne -> 1912
    //   1910: iload_2
    //   1911: ireturn
    //   1912: iload #10
    //   1914: bipush #10
    //   1916: if_icmpeq -> 1989
    //   1919: iload #10
    //   1921: bipush #13
    //   1923: if_icmpeq -> 1989
    //   1926: iconst_0
    //   1927: istore #12
    //   1929: iconst_0
    //   1930: iload #10
    //   1932: if_icmpgt -> 1950
    //   1935: iload #10
    //   1937: bipush #32
    //   1939: if_icmpge -> 1946
    //   1942: iconst_1
    //   1943: goto -> 1951
    //   1946: iconst_0
    //   1947: goto -> 1951
    //   1950: iconst_0
    //   1951: ifne -> 1981
    //   1954: bipush #127
    //   1956: iload #10
    //   1958: if_icmpgt -> 1977
    //   1961: iload #10
    //   1963: sipush #160
    //   1966: if_icmpge -> 1973
    //   1969: iconst_1
    //   1970: goto -> 1978
    //   1973: iconst_0
    //   1974: goto -> 1978
    //   1977: iconst_0
    //   1978: ifeq -> 1985
    //   1981: iconst_1
    //   1982: goto -> 1986
    //   1985: iconst_0
    //   1986: ifne -> 1997
    //   1989: iload #10
    //   1991: ldc_w 65533
    //   1994: if_icmpne -> 1999
    //   1997: iconst_m1
    //   1998: ireturn
    //   1999: iload_2
    //   2000: iload #10
    //   2002: ldc_w 65536
    //   2005: if_icmpge -> 2012
    //   2008: iconst_1
    //   2009: goto -> 2013
    //   2012: iconst_2
    //   2013: iadd
    //   2014: istore_2
    //   2015: nop
    //   2016: nop
    //   2017: getstatic kotlin/Unit.INSTANCE : Lkotlin/Unit;
    //   2020: astore #18
    //   2022: iload #16
    //   2024: goto -> 2172
    //   2027: iload #22
    //   2029: istore #15
    //   2031: istore #16
    //   2033: iconst_0
    //   2034: istore #17
    //   2036: iload #15
    //   2038: istore #10
    //   2040: iconst_0
    //   2041: istore #11
    //   2043: iload_3
    //   2044: istore #12
    //   2046: iload #12
    //   2048: iconst_1
    //   2049: iadd
    //   2050: istore_3
    //   2051: iload #12
    //   2053: iload_1
    //   2054: if_icmpne -> 2059
    //   2057: iload_2
    //   2058: ireturn
    //   2059: iload #10
    //   2061: bipush #10
    //   2063: if_icmpeq -> 2136
    //   2066: iload #10
    //   2068: bipush #13
    //   2070: if_icmpeq -> 2136
    //   2073: iconst_0
    //   2074: istore #12
    //   2076: iconst_0
    //   2077: iload #10
    //   2079: if_icmpgt -> 2097
    //   2082: iload #10
    //   2084: bipush #32
    //   2086: if_icmpge -> 2093
    //   2089: iconst_1
    //   2090: goto -> 2098
    //   2093: iconst_0
    //   2094: goto -> 2098
    //   2097: iconst_0
    //   2098: ifne -> 2128
    //   2101: bipush #127
    //   2103: iload #10
    //   2105: if_icmpgt -> 2124
    //   2108: iload #10
    //   2110: sipush #160
    //   2113: if_icmpge -> 2120
    //   2116: iconst_1
    //   2117: goto -> 2125
    //   2120: iconst_0
    //   2121: goto -> 2125
    //   2124: iconst_0
    //   2125: ifeq -> 2132
    //   2128: iconst_1
    //   2129: goto -> 2133
    //   2132: iconst_0
    //   2133: ifne -> 2144
    //   2136: iload #10
    //   2138: ldc_w 65533
    //   2141: if_icmpne -> 2146
    //   2144: iconst_m1
    //   2145: ireturn
    //   2146: iload_2
    //   2147: iload #10
    //   2149: ldc_w 65536
    //   2152: if_icmpge -> 2159
    //   2155: iconst_1
    //   2156: goto -> 2160
    //   2159: iconst_2
    //   2160: iadd
    //   2161: istore_2
    //   2162: nop
    //   2163: nop
    //   2164: getstatic kotlin/Unit.INSTANCE : Lkotlin/Unit;
    //   2167: astore #18
    //   2169: iload #16
    //   2171: nop
    //   2172: iconst_3
    //   2173: iadd
    //   2174: istore #8
    //   2176: goto -> 21
    //   2179: iload #9
    //   2181: istore #13
    //   2183: iconst_3
    //   2184: istore #14
    //   2186: iconst_0
    //   2187: istore #15
    //   2189: iload #13
    //   2191: iload #14
    //   2193: ishr
    //   2194: bipush #-2
    //   2196: if_icmpne -> 3732
    //   2199: iload #8
    //   2201: aload #4
    //   2203: astore #13
    //   2205: iconst_0
    //   2206: istore #14
    //   2208: iload #6
    //   2210: iload #8
    //   2212: iconst_3
    //   2213: iadd
    //   2214: if_icmpgt -> 2479
    //   2217: ldc_w 65533
    //   2220: istore #15
    //   2222: istore #16
    //   2224: iconst_0
    //   2225: istore #17
    //   2227: iload #15
    //   2229: istore #10
    //   2231: iconst_0
    //   2232: istore #11
    //   2234: iload_3
    //   2235: istore #12
    //   2237: iload #12
    //   2239: iconst_1
    //   2240: iadd
    //   2241: istore_3
    //   2242: iload #12
    //   2244: iload_1
    //   2245: if_icmpne -> 2250
    //   2248: iload_2
    //   2249: ireturn
    //   2250: iload #10
    //   2252: bipush #10
    //   2254: if_icmpeq -> 2327
    //   2257: iload #10
    //   2259: bipush #13
    //   2261: if_icmpeq -> 2327
    //   2264: iconst_0
    //   2265: istore #12
    //   2267: iconst_0
    //   2268: iload #10
    //   2270: if_icmpgt -> 2288
    //   2273: iload #10
    //   2275: bipush #32
    //   2277: if_icmpge -> 2284
    //   2280: iconst_1
    //   2281: goto -> 2289
    //   2284: iconst_0
    //   2285: goto -> 2289
    //   2288: iconst_0
    //   2289: ifne -> 2319
    //   2292: bipush #127
    //   2294: iload #10
    //   2296: if_icmpgt -> 2315
    //   2299: iload #10
    //   2301: sipush #160
    //   2304: if_icmpge -> 2311
    //   2307: iconst_1
    //   2308: goto -> 2316
    //   2311: iconst_0
    //   2312: goto -> 2316
    //   2315: iconst_0
    //   2316: ifeq -> 2323
    //   2319: iconst_1
    //   2320: goto -> 2324
    //   2323: iconst_0
    //   2324: ifne -> 2335
    //   2327: iload #10
    //   2329: ldc_w 65533
    //   2332: if_icmpne -> 2337
    //   2335: iconst_m1
    //   2336: ireturn
    //   2337: iload_2
    //   2338: iload #10
    //   2340: ldc_w 65536
    //   2343: if_icmpge -> 2350
    //   2346: iconst_1
    //   2347: goto -> 2351
    //   2350: iconst_2
    //   2351: iadd
    //   2352: istore_2
    //   2353: nop
    //   2354: nop
    //   2355: getstatic kotlin/Unit.INSTANCE : Lkotlin/Unit;
    //   2358: astore #18
    //   2360: iload #16
    //   2362: nop
    //   2363: iload #6
    //   2365: iload #8
    //   2367: iconst_1
    //   2368: iadd
    //   2369: if_icmple -> 2415
    //   2372: aload #13
    //   2374: iload #8
    //   2376: iconst_1
    //   2377: iadd
    //   2378: baload
    //   2379: istore #19
    //   2381: iconst_0
    //   2382: istore #20
    //   2384: iload #19
    //   2386: istore #21
    //   2388: sipush #192
    //   2391: istore #22
    //   2393: iconst_0
    //   2394: istore #23
    //   2396: iload #21
    //   2398: iload #22
    //   2400: iand
    //   2401: sipush #128
    //   2404: if_icmpne -> 2411
    //   2407: iconst_1
    //   2408: goto -> 2412
    //   2411: iconst_0
    //   2412: ifne -> 2419
    //   2415: iconst_1
    //   2416: goto -> 3726
    //   2419: iload #6
    //   2421: iload #8
    //   2423: iconst_2
    //   2424: iadd
    //   2425: if_icmple -> 2471
    //   2428: aload #13
    //   2430: iload #8
    //   2432: iconst_2
    //   2433: iadd
    //   2434: baload
    //   2435: istore #19
    //   2437: iconst_0
    //   2438: istore #20
    //   2440: iload #19
    //   2442: istore #21
    //   2444: sipush #192
    //   2447: istore #22
    //   2449: iconst_0
    //   2450: istore #23
    //   2452: iload #21
    //   2454: iload #22
    //   2456: iand
    //   2457: sipush #128
    //   2460: if_icmpne -> 2467
    //   2463: iconst_1
    //   2464: goto -> 2468
    //   2467: iconst_0
    //   2468: ifne -> 2475
    //   2471: iconst_2
    //   2472: goto -> 3726
    //   2475: iconst_3
    //   2476: goto -> 3726
    //   2479: aload #13
    //   2481: iload #8
    //   2483: baload
    //   2484: istore #19
    //   2486: aload #13
    //   2488: iload #8
    //   2490: iconst_1
    //   2491: iadd
    //   2492: baload
    //   2493: istore #20
    //   2495: iconst_0
    //   2496: istore #21
    //   2498: iload #20
    //   2500: istore #22
    //   2502: sipush #192
    //   2505: istore #23
    //   2507: iconst_0
    //   2508: istore #24
    //   2510: iload #22
    //   2512: iload #23
    //   2514: iand
    //   2515: sipush #128
    //   2518: if_icmpne -> 2525
    //   2521: iconst_1
    //   2522: goto -> 2526
    //   2525: iconst_0
    //   2526: ifne -> 2679
    //   2529: ldc_w 65533
    //   2532: istore #15
    //   2534: istore #16
    //   2536: iconst_0
    //   2537: istore #17
    //   2539: iload #15
    //   2541: istore #10
    //   2543: iconst_0
    //   2544: istore #11
    //   2546: iload_3
    //   2547: istore #12
    //   2549: iload #12
    //   2551: iconst_1
    //   2552: iadd
    //   2553: istore_3
    //   2554: iload #12
    //   2556: iload_1
    //   2557: if_icmpne -> 2562
    //   2560: iload_2
    //   2561: ireturn
    //   2562: iload #10
    //   2564: bipush #10
    //   2566: if_icmpeq -> 2639
    //   2569: iload #10
    //   2571: bipush #13
    //   2573: if_icmpeq -> 2639
    //   2576: iconst_0
    //   2577: istore #12
    //   2579: iconst_0
    //   2580: iload #10
    //   2582: if_icmpgt -> 2600
    //   2585: iload #10
    //   2587: bipush #32
    //   2589: if_icmpge -> 2596
    //   2592: iconst_1
    //   2593: goto -> 2601
    //   2596: iconst_0
    //   2597: goto -> 2601
    //   2600: iconst_0
    //   2601: ifne -> 2631
    //   2604: bipush #127
    //   2606: iload #10
    //   2608: if_icmpgt -> 2627
    //   2611: iload #10
    //   2613: sipush #160
    //   2616: if_icmpge -> 2623
    //   2619: iconst_1
    //   2620: goto -> 2628
    //   2623: iconst_0
    //   2624: goto -> 2628
    //   2627: iconst_0
    //   2628: ifeq -> 2635
    //   2631: iconst_1
    //   2632: goto -> 2636
    //   2635: iconst_0
    //   2636: ifne -> 2647
    //   2639: iload #10
    //   2641: ldc_w 65533
    //   2644: if_icmpne -> 2649
    //   2647: iconst_m1
    //   2648: ireturn
    //   2649: iload_2
    //   2650: iload #10
    //   2652: ldc_w 65536
    //   2655: if_icmpge -> 2662
    //   2658: iconst_1
    //   2659: goto -> 2663
    //   2662: iconst_2
    //   2663: iadd
    //   2664: istore_2
    //   2665: nop
    //   2666: nop
    //   2667: getstatic kotlin/Unit.INSTANCE : Lkotlin/Unit;
    //   2670: astore #18
    //   2672: iload #16
    //   2674: nop
    //   2675: iconst_1
    //   2676: goto -> 3726
    //   2679: aload #13
    //   2681: iload #8
    //   2683: iconst_2
    //   2684: iadd
    //   2685: baload
    //   2686: istore #21
    //   2688: iconst_0
    //   2689: istore #22
    //   2691: iload #21
    //   2693: istore #23
    //   2695: sipush #192
    //   2698: istore #24
    //   2700: iconst_0
    //   2701: istore #25
    //   2703: iload #23
    //   2705: iload #24
    //   2707: iand
    //   2708: sipush #128
    //   2711: if_icmpne -> 2718
    //   2714: iconst_1
    //   2715: goto -> 2719
    //   2718: iconst_0
    //   2719: ifne -> 2872
    //   2722: ldc_w 65533
    //   2725: istore #15
    //   2727: istore #16
    //   2729: iconst_0
    //   2730: istore #17
    //   2732: iload #15
    //   2734: istore #10
    //   2736: iconst_0
    //   2737: istore #11
    //   2739: iload_3
    //   2740: istore #12
    //   2742: iload #12
    //   2744: iconst_1
    //   2745: iadd
    //   2746: istore_3
    //   2747: iload #12
    //   2749: iload_1
    //   2750: if_icmpne -> 2755
    //   2753: iload_2
    //   2754: ireturn
    //   2755: iload #10
    //   2757: bipush #10
    //   2759: if_icmpeq -> 2832
    //   2762: iload #10
    //   2764: bipush #13
    //   2766: if_icmpeq -> 2832
    //   2769: iconst_0
    //   2770: istore #12
    //   2772: iconst_0
    //   2773: iload #10
    //   2775: if_icmpgt -> 2793
    //   2778: iload #10
    //   2780: bipush #32
    //   2782: if_icmpge -> 2789
    //   2785: iconst_1
    //   2786: goto -> 2794
    //   2789: iconst_0
    //   2790: goto -> 2794
    //   2793: iconst_0
    //   2794: ifne -> 2824
    //   2797: bipush #127
    //   2799: iload #10
    //   2801: if_icmpgt -> 2820
    //   2804: iload #10
    //   2806: sipush #160
    //   2809: if_icmpge -> 2816
    //   2812: iconst_1
    //   2813: goto -> 2821
    //   2816: iconst_0
    //   2817: goto -> 2821
    //   2820: iconst_0
    //   2821: ifeq -> 2828
    //   2824: iconst_1
    //   2825: goto -> 2829
    //   2828: iconst_0
    //   2829: ifne -> 2840
    //   2832: iload #10
    //   2834: ldc_w 65533
    //   2837: if_icmpne -> 2842
    //   2840: iconst_m1
    //   2841: ireturn
    //   2842: iload_2
    //   2843: iload #10
    //   2845: ldc_w 65536
    //   2848: if_icmpge -> 2855
    //   2851: iconst_1
    //   2852: goto -> 2856
    //   2855: iconst_2
    //   2856: iadd
    //   2857: istore_2
    //   2858: nop
    //   2859: nop
    //   2860: getstatic kotlin/Unit.INSTANCE : Lkotlin/Unit;
    //   2863: astore #18
    //   2865: iload #16
    //   2867: nop
    //   2868: iconst_2
    //   2869: goto -> 3726
    //   2872: aload #13
    //   2874: iload #8
    //   2876: iconst_3
    //   2877: iadd
    //   2878: baload
    //   2879: istore #22
    //   2881: iconst_0
    //   2882: istore #23
    //   2884: iload #22
    //   2886: istore #24
    //   2888: sipush #192
    //   2891: istore #25
    //   2893: iconst_0
    //   2894: istore #26
    //   2896: iload #24
    //   2898: iload #25
    //   2900: iand
    //   2901: sipush #128
    //   2904: if_icmpne -> 2911
    //   2907: iconst_1
    //   2908: goto -> 2912
    //   2911: iconst_0
    //   2912: ifne -> 3065
    //   2915: ldc_w 65533
    //   2918: istore #15
    //   2920: istore #16
    //   2922: iconst_0
    //   2923: istore #17
    //   2925: iload #15
    //   2927: istore #10
    //   2929: iconst_0
    //   2930: istore #11
    //   2932: iload_3
    //   2933: istore #12
    //   2935: iload #12
    //   2937: iconst_1
    //   2938: iadd
    //   2939: istore_3
    //   2940: iload #12
    //   2942: iload_1
    //   2943: if_icmpne -> 2948
    //   2946: iload_2
    //   2947: ireturn
    //   2948: iload #10
    //   2950: bipush #10
    //   2952: if_icmpeq -> 3025
    //   2955: iload #10
    //   2957: bipush #13
    //   2959: if_icmpeq -> 3025
    //   2962: iconst_0
    //   2963: istore #12
    //   2965: iconst_0
    //   2966: iload #10
    //   2968: if_icmpgt -> 2986
    //   2971: iload #10
    //   2973: bipush #32
    //   2975: if_icmpge -> 2982
    //   2978: iconst_1
    //   2979: goto -> 2987
    //   2982: iconst_0
    //   2983: goto -> 2987
    //   2986: iconst_0
    //   2987: ifne -> 3017
    //   2990: bipush #127
    //   2992: iload #10
    //   2994: if_icmpgt -> 3013
    //   2997: iload #10
    //   2999: sipush #160
    //   3002: if_icmpge -> 3009
    //   3005: iconst_1
    //   3006: goto -> 3014
    //   3009: iconst_0
    //   3010: goto -> 3014
    //   3013: iconst_0
    //   3014: ifeq -> 3021
    //   3017: iconst_1
    //   3018: goto -> 3022
    //   3021: iconst_0
    //   3022: ifne -> 3033
    //   3025: iload #10
    //   3027: ldc_w 65533
    //   3030: if_icmpne -> 3035
    //   3033: iconst_m1
    //   3034: ireturn
    //   3035: iload_2
    //   3036: iload #10
    //   3038: ldc_w 65536
    //   3041: if_icmpge -> 3048
    //   3044: iconst_1
    //   3045: goto -> 3049
    //   3048: iconst_2
    //   3049: iadd
    //   3050: istore_2
    //   3051: nop
    //   3052: nop
    //   3053: getstatic kotlin/Unit.INSTANCE : Lkotlin/Unit;
    //   3056: astore #18
    //   3058: iload #16
    //   3060: nop
    //   3061: iconst_3
    //   3062: goto -> 3726
    //   3065: ldc_w 3678080
    //   3068: iload #22
    //   3070: ixor
    //   3071: iload #21
    //   3073: bipush #6
    //   3075: ishl
    //   3076: ixor
    //   3077: iload #20
    //   3079: bipush #12
    //   3081: ishl
    //   3082: ixor
    //   3083: iload #19
    //   3085: bipush #18
    //   3087: ishl
    //   3088: ixor
    //   3089: istore #23
    //   3091: nop
    //   3092: iload #23
    //   3094: ldc_w 1114111
    //   3097: if_icmple -> 3248
    //   3100: ldc_w 65533
    //   3103: istore #15
    //   3105: istore #16
    //   3107: iconst_0
    //   3108: istore #17
    //   3110: iload #15
    //   3112: istore #10
    //   3114: iconst_0
    //   3115: istore #11
    //   3117: iload_3
    //   3118: istore #12
    //   3120: iload #12
    //   3122: iconst_1
    //   3123: iadd
    //   3124: istore_3
    //   3125: iload #12
    //   3127: iload_1
    //   3128: if_icmpne -> 3133
    //   3131: iload_2
    //   3132: ireturn
    //   3133: iload #10
    //   3135: bipush #10
    //   3137: if_icmpeq -> 3210
    //   3140: iload #10
    //   3142: bipush #13
    //   3144: if_icmpeq -> 3210
    //   3147: iconst_0
    //   3148: istore #12
    //   3150: iconst_0
    //   3151: iload #10
    //   3153: if_icmpgt -> 3171
    //   3156: iload #10
    //   3158: bipush #32
    //   3160: if_icmpge -> 3167
    //   3163: iconst_1
    //   3164: goto -> 3172
    //   3167: iconst_0
    //   3168: goto -> 3172
    //   3171: iconst_0
    //   3172: ifne -> 3202
    //   3175: bipush #127
    //   3177: iload #10
    //   3179: if_icmpgt -> 3198
    //   3182: iload #10
    //   3184: sipush #160
    //   3187: if_icmpge -> 3194
    //   3190: iconst_1
    //   3191: goto -> 3199
    //   3194: iconst_0
    //   3195: goto -> 3199
    //   3198: iconst_0
    //   3199: ifeq -> 3206
    //   3202: iconst_1
    //   3203: goto -> 3207
    //   3206: iconst_0
    //   3207: ifne -> 3218
    //   3210: iload #10
    //   3212: ldc_w 65533
    //   3215: if_icmpne -> 3220
    //   3218: iconst_m1
    //   3219: ireturn
    //   3220: iload_2
    //   3221: iload #10
    //   3223: ldc_w 65536
    //   3226: if_icmpge -> 3233
    //   3229: iconst_1
    //   3230: goto -> 3234
    //   3233: iconst_2
    //   3234: iadd
    //   3235: istore_2
    //   3236: nop
    //   3237: nop
    //   3238: getstatic kotlin/Unit.INSTANCE : Lkotlin/Unit;
    //   3241: astore #18
    //   3243: iload #16
    //   3245: goto -> 3725
    //   3248: ldc_w 55296
    //   3251: iload #23
    //   3253: if_icmpgt -> 3272
    //   3256: iload #23
    //   3258: ldc_w 57344
    //   3261: if_icmpge -> 3268
    //   3264: iconst_1
    //   3265: goto -> 3273
    //   3268: iconst_0
    //   3269: goto -> 3273
    //   3272: iconst_0
    //   3273: ifeq -> 3424
    //   3276: ldc_w 65533
    //   3279: istore #15
    //   3281: istore #16
    //   3283: iconst_0
    //   3284: istore #17
    //   3286: iload #15
    //   3288: istore #10
    //   3290: iconst_0
    //   3291: istore #11
    //   3293: iload_3
    //   3294: istore #12
    //   3296: iload #12
    //   3298: iconst_1
    //   3299: iadd
    //   3300: istore_3
    //   3301: iload #12
    //   3303: iload_1
    //   3304: if_icmpne -> 3309
    //   3307: iload_2
    //   3308: ireturn
    //   3309: iload #10
    //   3311: bipush #10
    //   3313: if_icmpeq -> 3386
    //   3316: iload #10
    //   3318: bipush #13
    //   3320: if_icmpeq -> 3386
    //   3323: iconst_0
    //   3324: istore #12
    //   3326: iconst_0
    //   3327: iload #10
    //   3329: if_icmpgt -> 3347
    //   3332: iload #10
    //   3334: bipush #32
    //   3336: if_icmpge -> 3343
    //   3339: iconst_1
    //   3340: goto -> 3348
    //   3343: iconst_0
    //   3344: goto -> 3348
    //   3347: iconst_0
    //   3348: ifne -> 3378
    //   3351: bipush #127
    //   3353: iload #10
    //   3355: if_icmpgt -> 3374
    //   3358: iload #10
    //   3360: sipush #160
    //   3363: if_icmpge -> 3370
    //   3366: iconst_1
    //   3367: goto -> 3375
    //   3370: iconst_0
    //   3371: goto -> 3375
    //   3374: iconst_0
    //   3375: ifeq -> 3382
    //   3378: iconst_1
    //   3379: goto -> 3383
    //   3382: iconst_0
    //   3383: ifne -> 3394
    //   3386: iload #10
    //   3388: ldc_w 65533
    //   3391: if_icmpne -> 3396
    //   3394: iconst_m1
    //   3395: ireturn
    //   3396: iload_2
    //   3397: iload #10
    //   3399: ldc_w 65536
    //   3402: if_icmpge -> 3409
    //   3405: iconst_1
    //   3406: goto -> 3410
    //   3409: iconst_2
    //   3410: iadd
    //   3411: istore_2
    //   3412: nop
    //   3413: nop
    //   3414: getstatic kotlin/Unit.INSTANCE : Lkotlin/Unit;
    //   3417: astore #18
    //   3419: iload #16
    //   3421: goto -> 3725
    //   3424: iload #23
    //   3426: ldc_w 65536
    //   3429: if_icmpge -> 3580
    //   3432: ldc_w 65533
    //   3435: istore #15
    //   3437: istore #16
    //   3439: iconst_0
    //   3440: istore #17
    //   3442: iload #15
    //   3444: istore #10
    //   3446: iconst_0
    //   3447: istore #11
    //   3449: iload_3
    //   3450: istore #12
    //   3452: iload #12
    //   3454: iconst_1
    //   3455: iadd
    //   3456: istore_3
    //   3457: iload #12
    //   3459: iload_1
    //   3460: if_icmpne -> 3465
    //   3463: iload_2
    //   3464: ireturn
    //   3465: iload #10
    //   3467: bipush #10
    //   3469: if_icmpeq -> 3542
    //   3472: iload #10
    //   3474: bipush #13
    //   3476: if_icmpeq -> 3542
    //   3479: iconst_0
    //   3480: istore #12
    //   3482: iconst_0
    //   3483: iload #10
    //   3485: if_icmpgt -> 3503
    //   3488: iload #10
    //   3490: bipush #32
    //   3492: if_icmpge -> 3499
    //   3495: iconst_1
    //   3496: goto -> 3504
    //   3499: iconst_0
    //   3500: goto -> 3504
    //   3503: iconst_0
    //   3504: ifne -> 3534
    //   3507: bipush #127
    //   3509: iload #10
    //   3511: if_icmpgt -> 3530
    //   3514: iload #10
    //   3516: sipush #160
    //   3519: if_icmpge -> 3526
    //   3522: iconst_1
    //   3523: goto -> 3531
    //   3526: iconst_0
    //   3527: goto -> 3531
    //   3530: iconst_0
    //   3531: ifeq -> 3538
    //   3534: iconst_1
    //   3535: goto -> 3539
    //   3538: iconst_0
    //   3539: ifne -> 3550
    //   3542: iload #10
    //   3544: ldc_w 65533
    //   3547: if_icmpne -> 3552
    //   3550: iconst_m1
    //   3551: ireturn
    //   3552: iload_2
    //   3553: iload #10
    //   3555: ldc_w 65536
    //   3558: if_icmpge -> 3565
    //   3561: iconst_1
    //   3562: goto -> 3566
    //   3565: iconst_2
    //   3566: iadd
    //   3567: istore_2
    //   3568: nop
    //   3569: nop
    //   3570: getstatic kotlin/Unit.INSTANCE : Lkotlin/Unit;
    //   3573: astore #18
    //   3575: iload #16
    //   3577: goto -> 3725
    //   3580: iload #23
    //   3582: istore #15
    //   3584: istore #16
    //   3586: iconst_0
    //   3587: istore #17
    //   3589: iload #15
    //   3591: istore #10
    //   3593: iconst_0
    //   3594: istore #11
    //   3596: iload_3
    //   3597: istore #12
    //   3599: iload #12
    //   3601: iconst_1
    //   3602: iadd
    //   3603: istore_3
    //   3604: iload #12
    //   3606: iload_1
    //   3607: if_icmpne -> 3612
    //   3610: iload_2
    //   3611: ireturn
    //   3612: iload #10
    //   3614: bipush #10
    //   3616: if_icmpeq -> 3689
    //   3619: iload #10
    //   3621: bipush #13
    //   3623: if_icmpeq -> 3689
    //   3626: iconst_0
    //   3627: istore #12
    //   3629: iconst_0
    //   3630: iload #10
    //   3632: if_icmpgt -> 3650
    //   3635: iload #10
    //   3637: bipush #32
    //   3639: if_icmpge -> 3646
    //   3642: iconst_1
    //   3643: goto -> 3651
    //   3646: iconst_0
    //   3647: goto -> 3651
    //   3650: iconst_0
    //   3651: ifne -> 3681
    //   3654: bipush #127
    //   3656: iload #10
    //   3658: if_icmpgt -> 3677
    //   3661: iload #10
    //   3663: sipush #160
    //   3666: if_icmpge -> 3673
    //   3669: iconst_1
    //   3670: goto -> 3678
    //   3673: iconst_0
    //   3674: goto -> 3678
    //   3677: iconst_0
    //   3678: ifeq -> 3685
    //   3681: iconst_1
    //   3682: goto -> 3686
    //   3685: iconst_0
    //   3686: ifne -> 3697
    //   3689: iload #10
    //   3691: ldc_w 65533
    //   3694: if_icmpne -> 3699
    //   3697: iconst_m1
    //   3698: ireturn
    //   3699: iload_2
    //   3700: iload #10
    //   3702: ldc_w 65536
    //   3705: if_icmpge -> 3712
    //   3708: iconst_1
    //   3709: goto -> 3713
    //   3712: iconst_2
    //   3713: iadd
    //   3714: istore_2
    //   3715: nop
    //   3716: nop
    //   3717: getstatic kotlin/Unit.INSTANCE : Lkotlin/Unit;
    //   3720: astore #18
    //   3722: iload #16
    //   3724: nop
    //   3725: iconst_4
    //   3726: iadd
    //   3727: istore #8
    //   3729: goto -> 21
    //   3732: ldc_w 65533
    //   3735: istore #10
    //   3737: iconst_0
    //   3738: istore #11
    //   3740: iload_3
    //   3741: istore #12
    //   3743: iload #12
    //   3745: iconst_1
    //   3746: iadd
    //   3747: istore_3
    //   3748: iload #12
    //   3750: iload_1
    //   3751: if_icmpne -> 3756
    //   3754: iload_2
    //   3755: ireturn
    //   3756: iload #10
    //   3758: bipush #10
    //   3760: if_icmpeq -> 3833
    //   3763: iload #10
    //   3765: bipush #13
    //   3767: if_icmpeq -> 3833
    //   3770: iconst_0
    //   3771: istore #12
    //   3773: iconst_0
    //   3774: iload #10
    //   3776: if_icmpgt -> 3794
    //   3779: iload #10
    //   3781: bipush #32
    //   3783: if_icmpge -> 3790
    //   3786: iconst_1
    //   3787: goto -> 3795
    //   3790: iconst_0
    //   3791: goto -> 3795
    //   3794: iconst_0
    //   3795: ifne -> 3825
    //   3798: bipush #127
    //   3800: iload #10
    //   3802: if_icmpgt -> 3821
    //   3805: iload #10
    //   3807: sipush #160
    //   3810: if_icmpge -> 3817
    //   3813: iconst_1
    //   3814: goto -> 3822
    //   3817: iconst_0
    //   3818: goto -> 3822
    //   3821: iconst_0
    //   3822: ifeq -> 3829
    //   3825: iconst_1
    //   3826: goto -> 3830
    //   3829: iconst_0
    //   3830: ifne -> 3841
    //   3833: iload #10
    //   3835: ldc_w 65533
    //   3838: if_icmpne -> 3843
    //   3841: iconst_m1
    //   3842: ireturn
    //   3843: iload_2
    //   3844: iload #10
    //   3846: ldc_w 65536
    //   3849: if_icmpge -> 3856
    //   3852: iconst_1
    //   3853: goto -> 3857
    //   3856: iconst_2
    //   3857: iadd
    //   3858: istore_2
    //   3859: nop
    //   3860: nop
    //   3861: iload #8
    //   3863: iinc #8, 1
    //   3866: pop
    //   3867: goto -> 21
    //   3870: nop
    //   3871: iload_2
    //   3872: ireturn
    // Line number table:
    //   Java source line number -> byte code offset
    //   #346	-> 0
    //   #347	-> 2
    //   #348	-> 4
    //   #381	-> 17
    //   #382	-> 21
    //   #383	-> 28
    //   #384	-> 35
    //   #385	-> 36
    //   #387	-> 41
    //   #349	-> 48
    //   #350	-> 62
    //   #353	-> 64
    //   #388	-> 81
    //   #353	-> 138
    //   #354	-> 141
    //   #356	-> 149
    //   #359	-> 151
    //   #360	-> 167
    //   #387	-> 168
    //   #389	-> 169
    //   #392	-> 175
    //   #393	-> 190
    //   #349	-> 203
    //   #350	-> 217
    //   #353	-> 219
    //   #394	-> 236
    //   #353	-> 293
    //   #354	-> 296
    //   #356	-> 304
    //   #359	-> 306
    //   #360	-> 322
    //   #393	-> 323
    //   #395	-> 326
    //   #396	-> 336
    //   #395	-> 341
    //   #397	-> 346
    //   #398	-> 355
    //   #399	-> 364
    //   #397	-> 374
    //   #349	-> 381
    //   #350	-> 395
    //   #353	-> 397
    //   #400	-> 414
    //   #353	-> 471
    //   #354	-> 474
    //   #356	-> 482
    //   #359	-> 484
    //   #360	-> 500
    //   #397	-> 501
    //   #399	-> 509
    //   #401	-> 510
    //   #404	-> 514
    //   #405	-> 521
    //   #406	-> 530
    //   #407	-> 533
    //   #408	-> 545
    //   #407	-> 550
    //   #406	-> 561
    //   #409	-> 564
    //   #397	-> 574
    //   #349	-> 581
    //   #350	-> 595
    //   #353	-> 597
    //   #410	-> 614
    //   #353	-> 671
    //   #354	-> 674
    //   #356	-> 682
    //   #359	-> 684
    //   #360	-> 700
    //   #397	-> 701
    //   #409	-> 709
    //   #411	-> 710
    //   #416	-> 714
    //   #417	-> 717
    //   #416	-> 719
    //   #418	-> 720
    //   #416	-> 725
    //   #414	-> 726
    //   #421	-> 728
    //   #422	-> 729
    //   #423	-> 737
    //   #397	-> 747
    //   #349	-> 754
    //   #350	-> 768
    //   #353	-> 770
    //   #424	-> 787
    //   #353	-> 844
    //   #354	-> 847
    //   #356	-> 855
    //   #359	-> 857
    //   #360	-> 873
    //   #397	-> 874
    //   #423	-> 882
    //   #425	-> 885
    //   #397	-> 894
    //   #349	-> 901
    //   #350	-> 915
    //   #353	-> 917
    //   #426	-> 934
    //   #353	-> 991
    //   #354	-> 994
    //   #356	-> 1002
    //   #359	-> 1004
    //   #360	-> 1020
    //   #397	-> 1021
    //   #425	-> 1029
    //   #427	-> 1030
    //   #397	-> 1031
    //   #428	-> 1037
    //   #396	-> 1047
    //   #428	-> 1052
    //   #430	-> 1057
    //   #431	-> 1066
    //   #433	-> 1075
    //   #430	-> 1085
    //   #349	-> 1092
    //   #350	-> 1106
    //   #353	-> 1108
    //   #434	-> 1125
    //   #353	-> 1182
    //   #354	-> 1185
    //   #356	-> 1193
    //   #359	-> 1195
    //   #360	-> 1211
    //   #430	-> 1212
    //   #433	-> 1220
    //   #435	-> 1221
    //   #436	-> 1242
    //   #408	-> 1254
    //   #436	-> 1259
    //   #435	-> 1270
    //   #437	-> 1273
    //   #440	-> 1277
    //   #444	-> 1281
    //   #445	-> 1288
    //   #446	-> 1297
    //   #447	-> 1300
    //   #408	-> 1312
    //   #447	-> 1317
    //   #446	-> 1328
    //   #448	-> 1331
    //   #430	-> 1341
    //   #349	-> 1348
    //   #350	-> 1362
    //   #353	-> 1364
    //   #449	-> 1381
    //   #353	-> 1438
    //   #354	-> 1441
    //   #356	-> 1449
    //   #359	-> 1451
    //   #360	-> 1467
    //   #430	-> 1468
    //   #448	-> 1476
    //   #450	-> 1477
    //   #452	-> 1481
    //   #453	-> 1490
    //   #454	-> 1493
    //   #408	-> 1505
    //   #454	-> 1510
    //   #453	-> 1521
    //   #455	-> 1524
    //   #430	-> 1534
    //   #349	-> 1541
    //   #350	-> 1555
    //   #353	-> 1557
    //   #456	-> 1574
    //   #353	-> 1631
    //   #354	-> 1634
    //   #356	-> 1642
    //   #359	-> 1644
    //   #360	-> 1660
    //   #430	-> 1661
    //   #455	-> 1669
    //   #457	-> 1670
    //   #462	-> 1674
    //   #463	-> 1677
    //   #462	-> 1679
    //   #464	-> 1680
    //   #462	-> 1685
    //   #465	-> 1686
    //   #462	-> 1691
    //   #460	-> 1692
    //   #468	-> 1694
    //   #469	-> 1695
    //   #470	-> 1703
    //   #430	-> 1713
    //   #349	-> 1720
    //   #350	-> 1734
    //   #353	-> 1736
    //   #471	-> 1753
    //   #353	-> 1810
    //   #354	-> 1813
    //   #356	-> 1821
    //   #359	-> 1823
    //   #360	-> 1839
    //   #430	-> 1840
    //   #470	-> 1848
    //   #472	-> 1851
    //   #473	-> 1879
    //   #430	-> 1889
    //   #349	-> 1896
    //   #350	-> 1910
    //   #353	-> 1912
    //   #474	-> 1929
    //   #353	-> 1986
    //   #354	-> 1989
    //   #356	-> 1997
    //   #359	-> 1999
    //   #360	-> 2015
    //   #430	-> 2016
    //   #473	-> 2024
    //   #475	-> 2027
    //   #430	-> 2036
    //   #349	-> 2043
    //   #350	-> 2057
    //   #353	-> 2059
    //   #476	-> 2076
    //   #353	-> 2133
    //   #354	-> 2136
    //   #356	-> 2144
    //   #359	-> 2146
    //   #360	-> 2162
    //   #430	-> 2163
    //   #475	-> 2171
    //   #477	-> 2172
    //   #430	-> 2173
    //   #478	-> 2179
    //   #396	-> 2189
    //   #478	-> 2194
    //   #480	-> 2199
    //   #481	-> 2208
    //   #483	-> 2217
    //   #480	-> 2227
    //   #349	-> 2234
    //   #350	-> 2248
    //   #353	-> 2250
    //   #484	-> 2267
    //   #353	-> 2324
    //   #354	-> 2327
    //   #356	-> 2335
    //   #359	-> 2337
    //   #360	-> 2353
    //   #480	-> 2354
    //   #483	-> 2362
    //   #485	-> 2363
    //   #486	-> 2384
    //   #408	-> 2396
    //   #486	-> 2401
    //   #485	-> 2412
    //   #487	-> 2415
    //   #488	-> 2419
    //   #489	-> 2440
    //   #408	-> 2452
    //   #489	-> 2457
    //   #488	-> 2468
    //   #490	-> 2471
    //   #493	-> 2475
    //   #497	-> 2479
    //   #498	-> 2486
    //   #499	-> 2495
    //   #500	-> 2498
    //   #408	-> 2510
    //   #500	-> 2515
    //   #499	-> 2526
    //   #501	-> 2529
    //   #480	-> 2539
    //   #349	-> 2546
    //   #350	-> 2560
    //   #353	-> 2562
    //   #502	-> 2579
    //   #353	-> 2636
    //   #354	-> 2639
    //   #356	-> 2647
    //   #359	-> 2649
    //   #360	-> 2665
    //   #480	-> 2666
    //   #501	-> 2674
    //   #503	-> 2675
    //   #505	-> 2679
    //   #506	-> 2688
    //   #507	-> 2691
    //   #408	-> 2703
    //   #507	-> 2708
    //   #506	-> 2719
    //   #508	-> 2722
    //   #480	-> 2732
    //   #349	-> 2739
    //   #350	-> 2753
    //   #353	-> 2755
    //   #509	-> 2772
    //   #353	-> 2829
    //   #354	-> 2832
    //   #356	-> 2840
    //   #359	-> 2842
    //   #360	-> 2858
    //   #480	-> 2859
    //   #508	-> 2867
    //   #510	-> 2868
    //   #512	-> 2872
    //   #513	-> 2881
    //   #514	-> 2884
    //   #408	-> 2896
    //   #514	-> 2901
    //   #513	-> 2912
    //   #515	-> 2915
    //   #480	-> 2925
    //   #349	-> 2932
    //   #350	-> 2946
    //   #353	-> 2948
    //   #516	-> 2965
    //   #353	-> 3022
    //   #354	-> 3025
    //   #356	-> 3033
    //   #359	-> 3035
    //   #360	-> 3051
    //   #480	-> 3052
    //   #515	-> 3060
    //   #517	-> 3061
    //   #522	-> 3065
    //   #523	-> 3068
    //   #522	-> 3070
    //   #524	-> 3071
    //   #522	-> 3076
    //   #525	-> 3077
    //   #522	-> 3082
    //   #526	-> 3083
    //   #522	-> 3088
    //   #520	-> 3089
    //   #529	-> 3091
    //   #530	-> 3092
    //   #531	-> 3100
    //   #480	-> 3110
    //   #349	-> 3117
    //   #350	-> 3131
    //   #353	-> 3133
    //   #532	-> 3150
    //   #353	-> 3207
    //   #354	-> 3210
    //   #356	-> 3218
    //   #359	-> 3220
    //   #360	-> 3236
    //   #480	-> 3237
    //   #531	-> 3245
    //   #533	-> 3248
    //   #534	-> 3276
    //   #480	-> 3286
    //   #349	-> 3293
    //   #350	-> 3307
    //   #353	-> 3309
    //   #535	-> 3326
    //   #353	-> 3383
    //   #354	-> 3386
    //   #356	-> 3394
    //   #359	-> 3396
    //   #360	-> 3412
    //   #480	-> 3413
    //   #534	-> 3421
    //   #536	-> 3424
    //   #537	-> 3432
    //   #480	-> 3442
    //   #349	-> 3449
    //   #350	-> 3463
    //   #353	-> 3465
    //   #538	-> 3482
    //   #353	-> 3539
    //   #354	-> 3542
    //   #356	-> 3550
    //   #359	-> 3552
    //   #360	-> 3568
    //   #480	-> 3569
    //   #537	-> 3577
    //   #539	-> 3580
    //   #480	-> 3589
    //   #349	-> 3596
    //   #350	-> 3610
    //   #353	-> 3612
    //   #540	-> 3629
    //   #353	-> 3686
    //   #354	-> 3689
    //   #356	-> 3697
    //   #359	-> 3699
    //   #360	-> 3715
    //   #480	-> 3716
    //   #539	-> 3724
    //   #541	-> 3725
    //   #480	-> 3726
    //   #542	-> 3732
    //   #349	-> 3740
    //   #350	-> 3754
    //   #353	-> 3756
    //   #543	-> 3773
    //   #353	-> 3830
    //   #354	-> 3833
    //   #356	-> 3841
    //   #359	-> 3843
    //   #360	-> 3859
    //   #542	-> 3860
    //   #544	-> 3861
    //   #548	-> 3870
    //   #361	-> 3871
    // Local variable table:
    //   start	length	slot	name	descriptor
    //   81	57	12	$i$f$isIsoControl	I
    //   48	120	11	$i$a$-processUtf8CodePoints--ByteString$codePointIndexToCharIndex$1	I
    //   45	123	10	c	I
    //   236	57	12	$i$f$isIsoControl	I
    //   203	120	11	$i$a$-processUtf8CodePoints--ByteString$codePointIndexToCharIndex$1	I
    //   200	123	10	c	I
    //   414	57	12	$i$f$isIsoControl	I
    //   381	120	11	$i$a$-processUtf8CodePoints--ByteString$codePointIndexToCharIndex$1	I
    //   378	123	10	c	I
    //   614	57	12	$i$f$isIsoControl	I
    //   581	120	11	$i$a$-processUtf8CodePoints--ByteString$codePointIndexToCharIndex$1	I
    //   578	123	10	c	I
    //   787	57	12	$i$f$isIsoControl	I
    //   754	120	11	$i$a$-processUtf8CodePoints--ByteString$codePointIndexToCharIndex$1	I
    //   751	123	10	c	I
    //   934	57	12	$i$f$isIsoControl	I
    //   901	120	11	$i$a$-processUtf8CodePoints--ByteString$codePointIndexToCharIndex$1	I
    //   898	123	10	c	I
    //   1125	57	12	$i$f$isIsoControl	I
    //   1092	120	11	$i$a$-processUtf8CodePoints--ByteString$codePointIndexToCharIndex$1	I
    //   1089	123	10	c	I
    //   1381	57	12	$i$f$isIsoControl	I
    //   1348	120	11	$i$a$-processUtf8CodePoints--ByteString$codePointIndexToCharIndex$1	I
    //   1345	123	10	c	I
    //   1574	57	12	$i$f$isIsoControl	I
    //   1541	120	11	$i$a$-processUtf8CodePoints--ByteString$codePointIndexToCharIndex$1	I
    //   1538	123	10	c	I
    //   1753	57	12	$i$f$isIsoControl	I
    //   1720	120	11	$i$a$-processUtf8CodePoints--ByteString$codePointIndexToCharIndex$1	I
    //   1717	123	10	c	I
    //   1929	57	12	$i$f$isIsoControl	I
    //   1896	120	11	$i$a$-processUtf8CodePoints--ByteString$codePointIndexToCharIndex$1	I
    //   1893	123	10	c	I
    //   2076	57	12	$i$f$isIsoControl	I
    //   2043	120	11	$i$a$-processUtf8CodePoints--ByteString$codePointIndexToCharIndex$1	I
    //   2040	123	10	c	I
    //   2267	57	12	$i$f$isIsoControl	I
    //   2234	120	11	$i$a$-processUtf8CodePoints--ByteString$codePointIndexToCharIndex$1	I
    //   2231	123	10	c	I
    //   2579	57	12	$i$f$isIsoControl	I
    //   2546	120	11	$i$a$-processUtf8CodePoints--ByteString$codePointIndexToCharIndex$1	I
    //   2543	123	10	c	I
    //   2772	57	12	$i$f$isIsoControl	I
    //   2739	120	11	$i$a$-processUtf8CodePoints--ByteString$codePointIndexToCharIndex$1	I
    //   2736	123	10	c	I
    //   2965	57	12	$i$f$isIsoControl	I
    //   2932	120	11	$i$a$-processUtf8CodePoints--ByteString$codePointIndexToCharIndex$1	I
    //   2929	123	10	c	I
    //   3150	57	12	$i$f$isIsoControl	I
    //   3117	120	11	$i$a$-processUtf8CodePoints--ByteString$codePointIndexToCharIndex$1	I
    //   3114	123	10	c	I
    //   3326	57	12	$i$f$isIsoControl	I
    //   3293	120	11	$i$a$-processUtf8CodePoints--ByteString$codePointIndexToCharIndex$1	I
    //   3290	123	10	c	I
    //   3482	57	12	$i$f$isIsoControl	I
    //   3449	120	11	$i$a$-processUtf8CodePoints--ByteString$codePointIndexToCharIndex$1	I
    //   3446	123	10	c	I
    //   3629	57	12	$i$f$isIsoControl	I
    //   3596	120	11	$i$a$-processUtf8CodePoints--ByteString$codePointIndexToCharIndex$1	I
    //   3593	123	10	c	I
    //   3773	57	12	$i$f$isIsoControl	I
    //   3740	120	11	$i$a$-processUtf8CodePoints--ByteString$codePointIndexToCharIndex$1	I
    //   3737	123	10	c	I
    //   336	5	15	$i$f$shr	I
    //   333	8	13	$this$shr$iv$iv	B
    //   333	8	14	other$iv$iv	I
    //   374	128	17	$i$a$-process2Utf8Bytes-Utf8$processUtf8CodePoints$1$iv	I
    //   371	131	15	it$iv	I
    //   574	128	17	$i$a$-process2Utf8Bytes-Utf8$processUtf8CodePoints$1$iv	I
    //   571	131	15	it$iv	I
    //   747	128	17	$i$a$-process2Utf8Bytes-Utf8$processUtf8CodePoints$1$iv	I
    //   744	131	15	it$iv	I
    //   894	128	17	$i$a$-process2Utf8Bytes-Utf8$processUtf8CodePoints$1$iv	I
    //   891	131	15	it$iv	I
    //   545	5	24	$i$f$and	I
    //   542	8	22	$this$and$iv$iv$iv$iv	B
    //   542	8	23	other$iv$iv$iv$iv	I
    //   533	28	21	$i$f$isUtf8Continuation	I
    //   355	676	14	$i$f$process2Utf8Bytes	I
    //   521	510	19	b0$iv$iv	B
    //   530	501	20	b1$iv$iv	B
    //   728	303	21	codePoint$iv$iv	I
    //   352	679	13	$this$process2Utf8Bytes$iv$iv	[B
    //   1047	5	15	$i$f$shr	I
    //   1044	8	13	$this$shr$iv$iv	B
    //   1044	8	14	other$iv$iv	I
    //   1085	128	17	$i$a$-process3Utf8Bytes-Utf8$processUtf8CodePoints$2$iv	I
    //   1082	131	15	it$iv	I
    //   1341	128	17	$i$a$-process3Utf8Bytes-Utf8$processUtf8CodePoints$2$iv	I
    //   1338	131	15	it$iv	I
    //   1534	128	17	$i$a$-process3Utf8Bytes-Utf8$processUtf8CodePoints$2$iv	I
    //   1531	131	15	it$iv	I
    //   1713	128	17	$i$a$-process3Utf8Bytes-Utf8$processUtf8CodePoints$2$iv	I
    //   1710	131	15	it$iv	I
    //   1889	128	17	$i$a$-process3Utf8Bytes-Utf8$processUtf8CodePoints$2$iv	I
    //   1886	131	15	it$iv	I
    //   2036	128	17	$i$a$-process3Utf8Bytes-Utf8$processUtf8CodePoints$2$iv	I
    //   2033	131	15	it$iv	I
    //   1254	5	23	$i$f$and	I
    //   1251	8	21	$this$and$iv$iv$iv$iv	B
    //   1251	8	22	other$iv$iv$iv$iv	I
    //   1242	28	20	$i$f$isUtf8Continuation	I
    //   1239	31	19	byte$iv$iv$iv	B
    //   1312	5	24	$i$f$and	I
    //   1309	8	22	$this$and$iv$iv$iv$iv	B
    //   1309	8	23	other$iv$iv$iv$iv	I
    //   1300	28	21	$i$f$isUtf8Continuation	I
    //   1505	5	25	$i$f$and	I
    //   1502	8	23	$this$and$iv$iv$iv$iv	B
    //   1502	8	24	other$iv$iv$iv$iv	I
    //   1493	28	22	$i$f$isUtf8Continuation	I
    //   1066	1107	14	$i$f$process3Utf8Bytes	I
    //   1288	885	19	b0$iv$iv	B
    //   1297	876	20	b1$iv$iv	B
    //   1490	683	21	b2$iv$iv	B
    //   1694	479	22	codePoint$iv$iv	I
    //   1063	1110	13	$this$process3Utf8Bytes$iv$iv	[B
    //   2189	5	15	$i$f$shr	I
    //   2186	8	13	$this$shr$iv$iv	B
    //   2186	8	14	other$iv$iv	I
    //   2227	128	17	$i$a$-process4Utf8Bytes-Utf8$processUtf8CodePoints$3$iv	I
    //   2224	131	15	it$iv	I
    //   2539	128	17	$i$a$-process4Utf8Bytes-Utf8$processUtf8CodePoints$3$iv	I
    //   2536	131	15	it$iv	I
    //   2732	128	17	$i$a$-process4Utf8Bytes-Utf8$processUtf8CodePoints$3$iv	I
    //   2729	131	15	it$iv	I
    //   2925	128	17	$i$a$-process4Utf8Bytes-Utf8$processUtf8CodePoints$3$iv	I
    //   2922	131	15	it$iv	I
    //   3110	128	17	$i$a$-process4Utf8Bytes-Utf8$processUtf8CodePoints$3$iv	I
    //   3107	131	15	it$iv	I
    //   3286	128	17	$i$a$-process4Utf8Bytes-Utf8$processUtf8CodePoints$3$iv	I
    //   3283	131	15	it$iv	I
    //   3442	128	17	$i$a$-process4Utf8Bytes-Utf8$processUtf8CodePoints$3$iv	I
    //   3439	131	15	it$iv	I
    //   3589	128	17	$i$a$-process4Utf8Bytes-Utf8$processUtf8CodePoints$3$iv	I
    //   3586	131	15	it$iv	I
    //   2396	5	23	$i$f$and	I
    //   2393	8	21	$this$and$iv$iv$iv$iv	B
    //   2393	8	22	other$iv$iv$iv$iv	I
    //   2384	28	20	$i$f$isUtf8Continuation	I
    //   2381	31	19	byte$iv$iv$iv	B
    //   2452	5	23	$i$f$and	I
    //   2449	8	21	$this$and$iv$iv$iv$iv	B
    //   2449	8	22	other$iv$iv$iv$iv	I
    //   2440	28	20	$i$f$isUtf8Continuation	I
    //   2437	31	19	byte$iv$iv$iv	B
    //   2510	5	24	$i$f$and	I
    //   2507	8	22	$this$and$iv$iv$iv$iv	B
    //   2507	8	23	other$iv$iv$iv$iv	I
    //   2498	28	21	$i$f$isUtf8Continuation	I
    //   2703	5	25	$i$f$and	I
    //   2700	8	23	$this$and$iv$iv$iv$iv	B
    //   2700	8	24	other$iv$iv$iv$iv	I
    //   2691	28	22	$i$f$isUtf8Continuation	I
    //   2896	5	26	$i$f$and	I
    //   2893	8	24	$this$and$iv$iv$iv$iv	B
    //   2893	8	25	other$iv$iv$iv$iv	I
    //   2884	28	23	$i$f$isUtf8Continuation	I
    //   2208	1518	14	$i$f$process4Utf8Bytes	I
    //   2486	1240	19	b0$iv$iv	B
    //   2495	1231	20	b1$iv$iv	B
    //   2688	1038	21	b2$iv$iv	B
    //   2881	845	22	b3$iv$iv	B
    //   3091	635	23	codePoint$iv$iv	I
    //   2205	1521	13	$this$process4Utf8Bytes$iv$iv	[B
    //   35	3832	9	b0$iv	B
    //   17	3854	7	$i$f$processUtf8CodePoints	I
    //   21	3850	8	index$iv	I
    //   14	3857	4	$this$processUtf8CodePoints$iv	[B
    //   14	3857	5	beginIndex$iv	I
    //   14	3857	6	endIndex$iv	I
    //   2	3871	2	charCount	I
    //   4	3869	3	j	I
    //   0	3873	0	s	[B
    //   0	3873	1	codePointCount	I
  }
}
