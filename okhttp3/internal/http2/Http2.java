package okhttp3.internal.http2;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.internal.Util;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\0004\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\b\n\002\b\002\n\002\020\016\n\002\b\005\n\002\020\013\n\002\b\005\n\002\020\021\n\002\b\002\n\002\030\002\n\002\b\031\bÆ\002\030\0002\0020\001B\t\b\002¢\006\004\b\002\020\003J\035\020\b\032\0020\0072\006\020\005\032\0020\0042\006\020\006\032\0020\004¢\006\004\b\b\020\tJ\027\020\f\032\0020\0072\006\020\005\032\0020\004H\000¢\006\004\b\n\020\013J5\020\021\032\0020\0072\006\020\016\032\0020\r2\006\020\017\032\0020\0042\006\020\020\032\0020\0042\006\020\005\032\0020\0042\006\020\006\032\0020\004¢\006\004\b\021\020\022R\032\020\024\032\b\022\004\022\0020\0070\0238\002X\004¢\006\006\n\004\b\024\020\025R\024\020\027\032\0020\0268\006X\004¢\006\006\n\004\b\027\020\030R\034\020\031\032\n\022\006\022\004\030\0010\0070\0238\002X\004¢\006\006\n\004\b\031\020\025R\024\020\032\032\0020\0048\006XT¢\006\006\n\004\b\032\020\033R\024\020\034\032\0020\0048\006XT¢\006\006\n\004\b\034\020\033R\024\020\035\032\0020\0048\006XT¢\006\006\n\004\b\035\020\033R\024\020\036\032\0020\0048\006XT¢\006\006\n\004\b\036\020\033R\024\020\037\032\0020\0048\006XT¢\006\006\n\004\b\037\020\033R\024\020 \032\0020\0048\006XT¢\006\006\n\004\b \020\033R\024\020!\032\0020\0048\006XT¢\006\006\n\004\b!\020\033R\024\020\"\032\0020\0048\006XT¢\006\006\n\004\b\"\020\033R\032\020#\032\b\022\004\022\0020\0070\0238\002X\004¢\006\006\n\004\b#\020\025R\024\020$\032\0020\0048\006XT¢\006\006\n\004\b$\020\033R\024\020%\032\0020\0048\006XT¢\006\006\n\004\b%\020\033R\024\020&\032\0020\0048\006XT¢\006\006\n\004\b&\020\033R\024\020'\032\0020\0048\006XT¢\006\006\n\004\b'\020\033R\024\020(\032\0020\0048\006XT¢\006\006\n\004\b(\020\033R\024\020)\032\0020\0048\006XT¢\006\006\n\004\b)\020\033R\024\020*\032\0020\0048\006XT¢\006\006\n\004\b*\020\033R\024\020+\032\0020\0048\006XT¢\006\006\n\004\b+\020\033R\024\020,\032\0020\0048\006XT¢\006\006\n\004\b,\020\033R\024\020-\032\0020\0048\006XT¢\006\006\n\004\b-\020\033R\024\020.\032\0020\0048\006XT¢\006\006\n\004\b.\020\033¨\006/"}, d2 = {"Lokhttp3/internal/http2/Http2;", "", "<init>", "()V", "", "type", "flags", "", "formatFlags", "(II)Ljava/lang/String;", "formattedType$okhttp", "(I)Ljava/lang/String;", "formattedType", "", "inbound", "streamId", "length", "frameLog", "(ZIIII)Ljava/lang/String;", "", "BINARY", "[Ljava/lang/String;", "Lokio/ByteString;", "CONNECTION_PREFACE", "Lokio/ByteString;", "FLAGS", "FLAG_ACK", "I", "FLAG_COMPRESSED", "FLAG_END_HEADERS", "FLAG_END_PUSH_PROMISE", "FLAG_END_STREAM", "FLAG_NONE", "FLAG_PADDED", "FLAG_PRIORITY", "FRAME_NAMES", "INITIAL_MAX_FRAME_SIZE", "TYPE_CONTINUATION", "TYPE_DATA", "TYPE_GOAWAY", "TYPE_HEADERS", "TYPE_PING", "TYPE_PRIORITY", "TYPE_PUSH_PROMISE", "TYPE_RST_STREAM", "TYPE_SETTINGS", "TYPE_WINDOW_UPDATE", "okhttp"})
public final class Http2 {
  @NotNull
  public static final Http2 INSTANCE = new Http2();
  
  @JvmField
  @NotNull
  public static final ByteString CONNECTION_PREFACE = ByteString.Companion.encodeUtf8("PRI * HTTP/2.0\r\n\r\nSM\r\n\r\n");
  
  public static final int INITIAL_MAX_FRAME_SIZE = 16384;
  
  public static final int TYPE_DATA = 0;
  
  public static final int TYPE_HEADERS = 1;
  
  public static final int TYPE_PRIORITY = 2;
  
  public static final int TYPE_RST_STREAM = 3;
  
  public static final int TYPE_SETTINGS = 4;
  
  public static final int TYPE_PUSH_PROMISE = 5;
  
  public static final int TYPE_PING = 6;
  
  public static final int TYPE_GOAWAY = 7;
  
  public static final int TYPE_WINDOW_UPDATE = 8;
  
  public static final int TYPE_CONTINUATION = 9;
  
  public static final int FLAG_NONE = 0;
  
  public static final int FLAG_ACK = 1;
  
  public static final int FLAG_END_STREAM = 1;
  
  public static final int FLAG_END_HEADERS = 4;
  
  public static final int FLAG_END_PUSH_PROMISE = 4;
  
  public static final int FLAG_PADDED = 8;
  
  public static final int FLAG_PRIORITY = 32;
  
  public static final int FLAG_COMPRESSED = 32;
  
  @NotNull
  private static final String[] FRAME_NAMES;
  
  static {
    String[] arrayOfString1 = new String[10];
    arrayOfString1[0] = "DATA";
    arrayOfString1[1] = "HEADERS";
    arrayOfString1[2] = "PRIORITY";
    arrayOfString1[3] = "RST_STREAM";
    arrayOfString1[4] = "SETTINGS";
    arrayOfString1[5] = "PUSH_PROMISE";
    arrayOfString1[6] = "PING";
    arrayOfString1[7] = "GOAWAY";
    arrayOfString1[8] = 
      "WINDOW_UPDATE";
    arrayOfString1[9] = "CONTINUATION";
    FRAME_NAMES = arrayOfString1;
  }
  
  @NotNull
  private static final String[] FLAGS = new String[64];
  
  @NotNull
  private static final String[] BINARY;
  
  static {
    String[] arrayOfString2;
    for (byte b1 = 0; b1 < 'Ā'; ) {
      byte b = b1;
      Object[] arrayOfObject = new Object[1];
      Intrinsics.checkNotNullExpressionValue(Integer.toBinaryString(b), "toBinaryString(it)");
      arrayOfObject[0] = Integer.toBinaryString(b);
      arrayOfString2[b] = StringsKt.replace$default(Util.format("%8s", arrayOfObject), ' ', '0', false, 4, null);
      b1++;
    } 
    BINARY = arrayOfString2;
    FLAGS[0] = "";
    FLAGS[1] = "END_STREAM";
    int[] arrayOfInt1 = new int[1];
    arrayOfInt1[0] = 1;
    int[] prefixFlags = arrayOfInt1;
    FLAGS[8] = "PADDED";
    byte b2;
    int j;
    for (b2 = 0, j = prefixFlags.length; b2 < j; ) {
      int prefixFlag = prefixFlags[b2];
      FLAGS[prefixFlag | 0x8] = FLAGS[prefixFlag] + "|PADDED";
      b2++;
    } 
    FLAGS[4] = "END_HEADERS";
    FLAGS[32] = "PRIORITY";
    FLAGS[36] = "END_HEADERS|PRIORITY";
    int[] arrayOfInt2 = new int[3];
    arrayOfInt2[0] = 4;
    arrayOfInt2[1] = 32;
    arrayOfInt2[2] = 36;
    int[] frameFlags = arrayOfInt2;
    int k;
    for (byte b3 = 0; b3 < k; ) {
      int frameFlag = frameFlags[b3];
      byte b;
      int m;
      for (b = 0, m = prefixFlags.length; b < m; ) {
        int prefixFlag = prefixFlags[b];
        FLAGS[prefixFlag | frameFlag] = FLAGS[prefixFlag] + '|' + FLAGS[frameFlag];
        FLAGS[prefixFlag | frameFlag | 0x8] = 
          FLAGS[prefixFlag] + '|' + FLAGS[frameFlag] + "|PADDED";
        b++;
      } 
      b3++;
    } 
    for (int i = 0; i < k; i++) {
      if (FLAGS[i] == null)
        FLAGS[i] = BINARY[i]; 
    } 
  }
  
  @NotNull
  public final String frameLog(boolean inbound, int streamId, int length, int type, int flags) {
    String formattedType = formattedType$okhttp(type);
    String formattedFlags = formatFlags(type, flags);
    String direction = inbound ? "<<" : ">>";
    Object[] arrayOfObject = new Object[5];
    arrayOfObject[0] = direction;
    arrayOfObject[1] = Integer.valueOf(streamId);
    arrayOfObject[2] = Integer.valueOf(length);
    arrayOfObject[3] = formattedType;
    arrayOfObject[4] = formattedFlags;
    return Util.format("%s 0x%08x %5d %-13s %s", arrayOfObject);
  }
  
  @NotNull
  public final String formattedType$okhttp(int type) {
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = Integer.valueOf(type);
    return (type < FRAME_NAMES.length) ? FRAME_NAMES[type] : Util.format("0x%02x", arrayOfObject);
  }
  
  @NotNull
  public final String formatFlags(int type, int flags) {
    if (flags == 0)
      return ""; 
    switch (type) {
      case 4:
      case 6:
        return (flags == 1) ? "ACK" : BINARY[flags];
      case 2:
      case 3:
      case 7:
      case 8:
        return BINARY[flags];
    } 
    Intrinsics.checkNotNull(FLAGS[flags]);
    String result = (flags < FLAGS.length) ? FLAGS[flags] : BINARY[flags];
    return (
      type == 5 && (flags & 0x4) != 0) ? 
      StringsKt.replace$default(result, "HEADERS", "PUSH_PROMISE", false, 4, null) : (
      
      (type == 0 && (flags & 0x20) != 0) ? 
      StringsKt.replace$default(result, "PRIORITY", "COMPRESSED", false, 4, null) : 
      
      result);
  }
}
