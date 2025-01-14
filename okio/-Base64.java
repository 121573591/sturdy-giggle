package okio;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\000\f\n\002\020\016\n\002\020\022\n\002\b\017\032\025\020\002\032\004\030\0010\001*\0020\000H\000¢\006\004\b\002\020\003\032\035\020\005\032\0020\000*\0020\0012\b\b\002\020\004\032\0020\001H\000¢\006\004\b\005\020\006\" \020\007\032\0020\0018\000X\004¢\006\022\n\004\b\007\020\b\022\004\b\013\020\f\032\004\b\t\020\n\" \020\r\032\0020\0018\000X\004¢\006\022\n\004\b\r\020\b\022\004\b\017\020\f\032\004\b\016\020\n¨\006\020"}, d2 = {"", "", "decodeBase64ToArray", "(Ljava/lang/String;)[B", "map", "encodeBase64", "([B[B)Ljava/lang/String;", "BASE64", "[B", "getBASE64", "()[B", "getBASE64$annotations", "()V", "BASE64_URL_SAFE", "getBASE64_URL_SAFE", "getBASE64_URL_SAFE$annotations", "okio"})
@JvmName(name = "-Base64")
public final class -Base64 {
  @NotNull
  public static final byte[] getBASE64() {
    return BASE64;
  }
  
  @NotNull
  private static final byte[] BASE64 = ByteString.Companion.encodeUtf8("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/").getData$okio();
  
  @NotNull
  public static final byte[] getBASE64_URL_SAFE() {
    return BASE64_URL_SAFE;
  }
  
  @NotNull
  private static final byte[] BASE64_URL_SAFE = ByteString.Companion.encodeUtf8("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_").getData$okio();
  
  @Nullable
  public static final byte[] decodeBase64ToArray(@NotNull String $this$decodeBase64ToArray) {
    Intrinsics.checkNotNullParameter($this$decodeBase64ToArray, "<this>");
    int limit = $this$decodeBase64ToArray.length();
    while (limit > 0) {
      char c = $this$decodeBase64ToArray.charAt(limit - 1);
      if (c != '=' && c != '\n' && c != '\r' && c != ' ' && c != '\t')
        break; 
      limit--;
    } 
    byte[] out = new byte[(int)(limit * 6L / 8L)];
    int outCount = 0;
    int inCount = 0;
    int word = 0;
    int pos = 0, i = limit;
    while (true) {
      if (pos < i) {
        char c = $this$decodeBase64ToArray.charAt(pos);
        int bits = 0;
        if (('A' <= c) ? ((c < '[')) : false) {
          bits = c - 65;
        } else if (('a' <= c) ? ((c < '{')) : false) {
          bits = c - 71;
        } else if (('0' <= c) ? ((c < ':')) : false) {
          bits = c + 4;
        } else if (c == '+' || c == '-') {
          bits = 62;
        } else if (c == '/' || c == '_') {
          bits = 63;
        } else {
          if (c != '\n' && c != '\r' && c != ' ' && c != '\t')
            return null; 
          pos++;
        } 
        word = word << 6 | bits;
        inCount++;
        if (inCount % 4 == 0) {
          out[outCount++] = (byte)(word >> 16);
          out[outCount++] = (byte)(word >> 8);
          out[outCount++] = (byte)word;
        } 
      } else {
        break;
      } 
      pos++;
    } 
    int lastWordChars = inCount % 4;
    switch (lastWordChars) {
      case 1:
        return null;
      case 2:
        word <<= 12;
        out[outCount++] = (byte)(word >> 16);
        break;
      case 3:
        word <<= 6;
        out[outCount++] = (byte)(word >> 16);
        out[outCount++] = (byte)(word >> 8);
        break;
    } 
    if (outCount == out.length)
      return out; 
    Intrinsics.checkNotNullExpressionValue(Arrays.copyOf(out, outCount), "copyOf(this, newSize)");
    return Arrays.copyOf(out, outCount);
  }
  
  @NotNull
  public static final String encodeBase64(@NotNull byte[] $this$encodeBase64, @NotNull byte[] map) {
    int b0, b1;
    Intrinsics.checkNotNullParameter($this$encodeBase64, "<this>");
    Intrinsics.checkNotNullParameter(map, "map");
    int length = ($this$encodeBase64.length + 2) / 3 * 4;
    byte[] out = new byte[length];
    int index = 0;
    int end = $this$encodeBase64.length - $this$encodeBase64.length % 3;
    int i = 0;
    while (i < end) {
      int j = $this$encodeBase64[i++];
      int k = $this$encodeBase64[i++];
      int b2 = $this$encodeBase64[i++];
      out[index++] = map[(j & 0xFF) >> 2];
      out[index++] = map[(j & 0x3) << 4 | (k & 0xFF) >> 4];
      out[index++] = map[(k & 0xF) << 2 | (b2 & 0xFF) >> 6];
      out[index++] = map[b2 & 0x3F];
    } 
    switch ($this$encodeBase64.length - end) {
      case 1:
        b0 = $this$encodeBase64[i];
        out[index++] = map[(b0 & 0xFF) >> 2];
        out[index++] = map[(b0 & 0x3) << 4];
        out[index++] = 61;
        out[index] = 61;
        break;
      case 2:
        b0 = $this$encodeBase64[i++];
        b1 = $this$encodeBase64[i];
        out[index++] = map[(b0 & 0xFF) >> 2];
        out[index++] = map[(b0 & 0x3) << 4 | (b1 & 0xFF) >> 4];
        out[index++] = map[(b1 & 0xF) << 2];
        out[index] = 61;
        break;
    } 
    return _JvmPlatformKt.toUtf8String(out);
  }
}
